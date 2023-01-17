<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script>
function isEmpty(s) {
    if(typeof s == 'string')
        s = $.trim(s);

    if(s == undefined || s == null || s == '' || s.length == 0)
        return true;

    return false;
}

var alertMessage = $('.js_alertMessage').val();
if(!isEmpty(alertMessage)) {
	alert(alertMessage);
	location.href = $('.js_redirectPage').val()
}
</script>

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-goods-cart container_mypage"> 
	<jsp:include page="../mypage/mypage_tab_menu.jsp" flush="true">
		<jsp:param name="tab_order" value="10" />
	</jsp:include>

	<div class="navi_my">나의 쇼핑 > 장바구니</div>

	<div class="wishlist_wrap">
		<div class="tp_area">
			<div class="bx_l">
				<input type="checkbox" class="ck_my_ty01" id="checkAll" /><label for="checkAll" style="margin:0; cursor:pointer;">전체선택</label>
			</div>
			<div class="bx_r"><a class="btn btn-default btn_del_all" href="#">선택상품삭제</a></div>
			<div style="clear:both;"></div>
		</div>

		<input class="js_alertMessage" type="hidden" value="${cartVO.alertMessage}">
		<input class="js_redirectPage" type="hidden" value="${cartVO.redirectPage}">
		<input class="js_cartListSize" type="hidden" value="${fn:length(cartVO.inVOList)}">

		<form name="frmCart" method="post">
			<input type="hidden" name="mode" value="modItem">
			<input type="hidden" name="paramEa" value="0">
			<input type="hidden" name="idx" value="">
			<input type="hidden" name="arrIdx" value="">
			<input type="hidden" name="goodsno" value="">
			<input type="hidden" name="optionSno" value="">
			<input type="hidden" name="referer" value="${referer}" />
			<input type="hidden" name="guestSel" id="guest_sel" value="">
		
			<div class="my_cart_list">
				<div class="in_bx">
					<ul>
						<c:set var="eaSum"  value="0"/>
						<c:set var="priceSum"  value="0"/>
						<c:set var="reserveSum"  value="0"/>
						
						<c:if test="${not empty cartVO.inVOList}">
							<c:forEach var="cartList" items="${cartVO.inVOList}" varStatus="status">
								<c:set var="ea" value="0"/>
								<c:set var="price" value="0"/>
								<c:set var="excprice" value="0"/>
								<c:set var="reserve" value="0"/>
								
								<c:if test="${not empty cartList.ea}">
									<c:set var="ea" value="${cartList.ea}"/>
								</c:if>
								<c:if test="${not empty cartList.reserve}">
									<c:set var="reserve" value="${cartList.reserve}"/>
								</c:if>
								<c:if test="${not empty cartList.price}">
									<c:set var="price" value="${cartList.price}"/>
									<fmt:parseNumber var="excprice" pattern="₩#,##0" value="${shopLibFunction:getExchange(cartList.price, wskin)}" />
								</c:if>
								
								<c:set var="eaSum"  value="${eaSum + ea}"/>
								<c:set var="priceSum"  value="${(price * ea) + priceSum}"/>
								<c:set var="reserveSum"  value="${(reserve * ea) + reserveSum}"/>
								<li>
									<div class="in_wp">
										<p class="ck_l">
											<input type="checkbox" class="ck_my_ty01" id="check_${status.index}" name="chk" value="${cartList.sno}" data-opt="${cartList.goodsno}|||${cartList.optno}|||NULL|||${cartList.ea}" data-ea="${ea}" data-price="${price * ea}" data-excprice="${excprice * ea}" data-reserve="${reserve}"/>
										</p>
										<div class="area02_01">
											<a href="${ctx}/shop/goods/goods_view?goodsno=${cartList.goodsno}&category=${cartList.goodsCategory}" class="pr_img">
												<img src="${cartList.img}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'">
											</a>
										</div>
										<div class="area02_02">
											<div class="tx01">
												<a href="${ctx}/shop/goods/goods_view?goodsno=${cartList.goodsno}&category=${cartList.goodsCategory}">
													<dl>
														<dt>${cartList.brandnm}</dt>
														<dd>${cartList.goodsnmKR}</dd>
													</dl>
												</a>
											</div>
											<div class="tx02">
												
												<c:set var="optList" value="" />
												<c:choose>
													<c:when test="${cartList.opttype eq 'single'}">
														<c:set var="optList" value="${cartList.optionList}" />
													</c:when>
													<c:otherwise>
														<c:set var="optList" value="${cartList.option2List}" />
													</c:otherwise>
												</c:choose>
												<c:if test="${not empty optList}">
													<select id="option-group${cartList.sno}" data-ori="${cartList.optno}">
														<c:forEach var="opt2" items="${optList}" varStatus="status">
															<option ${opt2.stock eq 0 ? 'style="color: red;"' : ''} value="${opt2.sno}" ${opt2.stock eq 0 ? 'disabled' : ''} ${opt2.sno eq cartList.optno ? ' selected' : ''}>${opt2.opt1}<c:if test="${not empty opt2.opt2}"> ${opt2.opt2}</c:if> ${opt2.stock eq 0 ? '품절' : ''}</option>
														</c:forEach>
													</select>
													<a href="javascript:void(0);" class="btn_option" data-id="${cartList.sno}">옵션변경</a>
												</c:if>
												<div style="clear:both;"></div>
											</div>
											<div class="tx03">${cartList.ea}개</div>
										</div>
										<div class="area03">
											<fmt:parseNumber var="priceRate" type="number" value="${cartList.priceRate}" />
											<dl>
												<c:if test="${priceRate >= 3}">
													<dt>${shopLibFunction:getExchange(cartList.consumer, wskin)}</dt>
												</c:if>
												<dd>${shopLibFunction:getExchange(cartList.price, wskin)}</dd>
											</dl>
										</div>
										<div class="area04">예상적립: ${shopLibFunction:getExchange(cartList.reserve, 'kr')}</div>
										<div class="area05">
											<c:choose>
												<c:when test="${cartList.vipYn eq 'Y' && (empty userInfo.xkey.level || userInfo.xkey.level eq '1')}">
													<a class="btn btn-default btn_ty01" href="javascript:void(0);" onclick="alert('VIP 판매 상품 입니다.');">바로주문</a>
												</c:when>
												<c:otherwise>
													<a class="btn btn-default btn_ty01" href="javascript:void(0);" onclick="order('${cartList.goodsno}|||${cartList.optno}|||NULL|||${cartList.ea}')">바로주문</a>
												</c:otherwise>
											</c:choose>
											
											<a class="btn btn-default btn_ty02" href="javascript:void(0);" onclick="wishlist('${cartList.goodsno}|||${cartList.optno}||||||1');">위시리스트</a>
										</div>
									</div>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>

				<div class="cart_btm_info">
					<div class="bx_l">
						<ul>
							<li>- 장바구니에 담긴 상품은 최대 30일간 보관됩니다. (최대 200개까지 보관 가능)</li>
							<li>- 장기간 보관을 원하시는 경우 ‘위시리스트’로 등록해주시기 바랍니다.</li>
							<li>- 장바구니의 총 예상결제금액과 최종 결제 시 금액은 상이할 수 있습니다.</li>
							<li>- 실 결제 금액은주문서 내 회원님의 쿠폰/적립금 적용에 따라 달라집니다.</li>
						</ul>
					</div>
					<div class="bx_r">
						<div class="my_result_bx01">
							<dl>
								<dt>상품수</dt>
								<dd class="ea_sum">${eaSum}개</dd>
							</dl>
							<dl class="last">
								<dt>상품 금액</dt>
								<dd class="exc_price_sum">${shopLibFunction:getExchange(priceSum, wskin)}</dd>
							</dl>
							<div style="clear:both;"></div>
						</div>
						<div class="my_result_bx02">
							<dl class="line01">
								<dt>총 예상결제 금액</dt>
								<dd class="price_sum">${shopLibFunction:getExchange(priceSum, 'kr')}</dd>
							</dl>
							<dl class="line02 ty02">
								<dt>참고용 결제금액(환율적용)</dt>
								<dd class="exc_price_sum">${shopLibFunction:getExchange(priceSum, wskin)}</dd>
							</dl>
							<dl class="line02">
								<dt>지급예상 적립금 </dt>
								<dd class="reserve_sum">${shopLibFunction:getExchange(reserveSum, 'kr')}</dd>
							</dl>
						</div>
						<div class="my_result_btns">
							<a href="javascript:void(0);" class="btn_my_order">선택상품 주문</a>
							<a href="${ctx}/shop/main/index" class="btn_keep_shop">계속 쇼핑 </a>
						</div>
					</div>
					<div style="clear:both;"></div>
				</div>
			</div>
		</form>
		
		<form id="frmOrder" name="frmOrder" method="post" action="/shop/order/order">
			<input type="hidden" name="mode" value="addItem" />
			<input type="hidden" id="optionsList" name="optionsList" value="" />
		</form>
	</div>
</div>
			
<script>
var cartListSize = $('.js_cartListSize').val();
$(document).ready(function(){
	$("#checkAll").on("click", function(){
		var checkLen = cartListSize;
		var chk = $("#checkAll").is(":checked");
		if(chk){
				$("input[name=chk]").prop("checked",true);
		}else{
			for(var i=0; i<checkLen; i++){
				$("input[name=chk]").prop("checked",false);
			}
		}
		
	});
	
	$(".btn_del_all").on("click", function(e){
		e.preventDefault();
		var del = cartListSize;
		if(del == 0){
			alert("장바구니에 담긴 상품이 없습니다.");
			return false;
		}
		var del_idx = [];
		var del_sno = [];
		var cnt = 0;
		for(var i = 0; i < del; i++){
			if(true == $("#check_"+i).is(":checked")){
				del_idx[i] = i;
				del_sno.push($("#check_"+i).val());
				cnt++;
			}else{
				del_idx[i] = "n";
			};
		}
		if(cnt==0){
			alert("선택하신 상품이 없습니다.");
			return false;
		}
		
		frmCart.arrIdx.value = del_idx; 
		frmCart.optionSno.value = del_sno;
		frmCart.mode.value = 'delItem';
		frmCart.action = 'goods_cart';
		frmCart.submit();
	});
	
	$(".btn_option").on("click", function(e){
		e.preventDefault();
		var id = $(this).data("id");
		if ($("#option-group" + id).val() != $("#option-group" + id).data("ori")) {
			frmCart.idx.value = id;
			frmCart.optionSno.value = $("#option-group" + id).val();
			frmCart.mode.value = 'modItem';
			frmCart.action = 'goods_cart';
			frmCart.submit();
		}
	});

	$(".btn_my_order").on("click",function(){
		var sel = cartListSize;
		if(sel == 0){
			alert("장바구니에 담긴 상품이 없습니다.");
			return false;
		}
		
		var opt = [];
		$("input[name=chk]:checked").each(function(){
			opt.push($(this).data("opt"));
		});
		if (opt.length == 0){
			alert("선택하신 상품이 없습니다.");
			return false;
		}
		
		order(opt.join(","));
	});
	
	$(".ck_my_ty01").on("change", function(){
		var ea_sum = 0;
		var price_sum = 0;
		var exc_price_sum = 0;
		var reserve_sum = 0;
		if ($("input[name=chk]:checked").length > 0){
			$("input[name=chk]").each(function(){
				if ($(this).prop("checked")){
					ea_sum += $(this).data("ea");
					price_sum += $(this).data("price");
					exc_price_sum += $(this).data("excprice");
					reserve_sum += $(this).data("reserve");
				}
			});
		}else{
			$("input[name=chk]").each(function(){
				ea_sum += $(this).data("ea");
				price_sum += $(this).data("price");
				exc_price_sum += $(this).data("excprice");
				reserve_sum += $(this).data("reserve");
			});	
		}
		
		$(".ea_sum").text(ea_sum + "개");
		$(".price_sum").text("₩" + comma(price_sum.toFixed(2)));
		$(".exc_price_sum").text("₩" + comma(exc_price_sum));
		$(".reserve_sum").text("₩" + comma(reserve_sum.toFixed(2)));
	});
});
</script>

<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/common.js"></script>
<script type='text/javascript'>
	function order(option){
		var m_no = "${userInfo.m_no}";
		if(m_no == 0){
			alert("비회원으로 구매 가능합니다. 단, 회원주문시 할인/쿠폰/포인 등의 혜택이 제공됩니다.")
		}
		
		$("#optionsList").val(option);
		$("#frmOrder").submit();
	}
	
	function wishlist(option) {
		$("#optionsList").val(option);
		ajaxCallJsonPost("/shop/mypage/ajaxWishListAdd.doJson", "frmOrder", function(rst){
			if (rst.code == "1"){
				if (confirm('위시리스트에 추가되었습니다.\n\n위시리스트로 이동하시겠습니까?')){
					location.href = ctx + "/shop/mypage/mypage_wishlist";
				}
			}else{
				alert("동일한 상품이 위시리스트에 있습니다.");
			}
		});
	}
</script>

 