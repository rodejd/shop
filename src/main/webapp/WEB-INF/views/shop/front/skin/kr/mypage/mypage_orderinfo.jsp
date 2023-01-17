<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : mypage_orderlist.jsp
* 생성일 : 2017. 02. 22
* 작성자 : PMG
* 설   명 : skin default1 사용자 mypage_orderlist
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170222			PMG				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<script type="text/javascript">

$(function(){

});
</script>
<script language="javascript">
function goPage(page){
	$('#pageNo').val(page);
	document.frmList.submit();
}
function goSubmit(){
	$('#pageNo').val(1);
	document.frmList.submit();
}
function clickDeliRadio(status){
	$("input[name=sear]").val(status);
	$("#frmList").submit();
}

function clickPeriodRadio(status){
	$("input[name=sear2]").val(status);
	$("#frmList").submit();
}

function order_confirm(ordno){
	/* var fm = document.frmList;
	fm.mode.value = "confirm";
	fm.ordno.value = ordno;
	fm.submit();
	if (confirm('주문하신 상품을 수령확인 하시겠습니까?')){
		fm.submit();
	} */
	
	$('#mode').val("confirm");
	$('#ordno').val(ordno);
	if (confirm('주문하신 상품을 수령확인 하시겠습니까?')){
		ajaxCallJsonPost("/shop/mypage/indb.dojson", "frmList", function(result){
			//alert(JSON.stringify(result));
			location.href=ctx+"/shop/mypage/mypage_orderlist";
		});
	}
}

function order_sendback(ordno){
	
	if (confirm('주문하신 상품을 반품 하시겠습니까?')){
		window.open(ctx+"/shop/mypage/popup_mypage_refund", "sendback", "width=1090, height=440, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		var fm = document.frmList;
		fm.mode.value = "sendback";
		fm.ordno.value = ordno;
		fm.target = "sendback";
		fm.action = ctx+"/shop/mypage/popup_mypage_refund";
		fm.method = "post";
		fm.submit();
	}
}

function order_trade(ordno){
	
	if (confirm('주문하신 교환 하시겠습니까?')) {
		window.open(ctx+"/shop/mypage/popup_mypage_refund", "trade", "width=1090, height=410, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		//window.open("mypage_refund.jsp", "trade", "width=850, height=900, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		var fm = document.frmList;
		fm.mode.value = "trade";
		fm.ordno.value = ordno;
		fm.target = "trade";
		fm.action = ctx+ "/shop/mypage/popup_mypage_refund";
		fm.method = "post";
		fm.submit();
	}
}
/* $("#search_btn").on("click", function(){
	$("input[name=sear2]").val('use');
	$("#search").submit();
}); */

</script>

<!-- <header class="page-header page-header-banner x-member-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">주문내역/배송조회</h1>
        </div>
    </div>
</header> -->

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-mypage-orderlist container_mypage">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="mypage_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="3" />
		</jsp:include>
	
		<div class="">
			<div class="">
				<div class="tab-pane fade in active">
					<div class="my_order_wrap02">
						<div class="stit_my03">결제정보</div>
						<div class="my_list_ty01 type3">
							<ul class="cont_area">
								<c:set var="eaTotal" value="0" />
								<c:forEach var="item" items="${orderItemList}" varStatus="status">
									<c:set var="eaTotal" value="${eaTotal + item.ea}" />
									<li>
										<div class="wrap_in">
											<div class="area02_01">
												<a href="/shop/goods/goods_view?goodsno=${item.goodsno}">
													<img src="${item.imgs}" onerror="this.src='/resources/shop/data/skin/kr/img/common/noimg_500.gif';">
												</a>
											</div>
											<div class="area02_02">
											<div class="tx01">
												<a href="/shop/goods/goods_view?goodsno=${item.goodsno}">
													<dl>
														<dt>${shopLibFunction:getGoodsBrandName(item.brandno)}</dt>
														<dd>${item.goodsnmKR}</dd>
													</dl>
												</a>
											</div>
											<div class="tx02"><c:if test="${not empty list.optOpt1}">${list.optOpt1}, </c:if> ${empty list.optOpt2 ? 'ONE SIZE' : list.optOpt2 }</div>
											<div class="tx03">${item.ea}개</div>
										</div>
										<div class="area03">
											<div class="tb_out">
												<div class="tb_in">
													<c:if test="${item.priceRate >= 3}">
														<p class="tx_gray">${shopLibFunction:getExchange(item.consumer, 'kr')}</p>
													</c:if>
													${shopLibFunction:getExchange(item.price, 'kr')}
												</div>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="bx_r">
							<div class="my_result_bx01">
								<dl>
									<dt>상품수</dt>
									<dd>${eaTotal}개</dd>
								</dl>
								<dl>
									<dt>상품 금액</dt>
									<dd>${shopLibFunction:getExchange(orderInfo.goodsprice, 'kr')}</dd>
								</dl>
								<dl>
									<dt>할인금액</dt>
									<dd>${shopLibFunction:getExchange(orderInfo.coupon + orderInfo.emoney, 'kr')}</dd>
								</dl>
								<dl class="last">
									<dt>총 배송비</dt>
									<dd>${shopLibFunction:getExchange(orderInfo.delivery, 'kr')}</dd>
								</dl>
								<div style="clear:both;"></div>
							</div>
							<div class="my_result_bx02">
								<dl class="line01">
									<dt>총 결제 금액</dt>
									<dd>
										${shopLibFunction:getExchange(orderInfo.prn_settleprice, 'kr')}
									</dd>
								</dl>
								<dl class="line02">
									<dt>지급예상 적립금  </dt>
									<dd>${shopLibFunction:getExchange(orderInfo.addemoney, 'kr')}</dd>
								</dl>
							</div>
						</div>
						<div style="clear:both;"></div>
					</div>
					<!-- //주문취소신청 / 반품신청 -->
				</div>
			</div>
		</div>
	</div>
</div>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



