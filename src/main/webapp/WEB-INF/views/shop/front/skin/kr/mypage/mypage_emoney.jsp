<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">
$(function(){
	fnPage(1);
	$(".btn_more").on("click", function(e) {
		e.preventDefault();
		fnPage($(this).data("page"));
	});
});
function fnPage(page){
	$.ajax({
		type : 'POST',
		url : 'mypage_emoney.doJson',
		data: { pageNo: page },
		async: false,
		dataType : 'json',
		success : function (data) {
			if (data.RESULT){
	        	var html = "";
	        	$.each(data.frontMypageVO.frontEmoneyList, function(index, item){
					html += '<article class="emoney_cont_list">';
					html += '	<div class="in_bx">';
					html += '		<div class="area01">' + item.regdate + '</div>';
					html += '		<div class="area02">' + (item.gbn == 'C' ? '취소/환불' : (item.gbn == 'E' ? '소멸' : (item.gbn == 'U' ? '사용' : '적립'))) + '</div>';
					html += '		<div class="area03">' + item.memo + '</div>';
					html += '		<div class="area04">' + item.excemoney + '</div>';
					html += '		<div style="clear:both;"></div>';
					html += '	</div>';
					html += '</article>';
				});
				$(".emoney_log_list").append(html);
				
				var btnMore = $(".btn_more");
				if (data.frontMypageVO.rowCount > page * data.frontMypageVO.pageSize) {
					btnMore.data("page", ++page);
					btnMore.show();
				} else {
					btnMore.hide();
				}
			} else {
				alert(data.RESULT_MSG);
			}
		}
	});
}

$(function(){
	$(".b_ins_ins > span").on("click", function(e){
		e.preventDefault();
		$(this).next(".b_layer").toggle();
	});
});
</script>

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-mypage-emoney container_mypage">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="mypage_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="4" />
		</jsp:include>

		<div class="navi_my">혜택관리 > 적립금관리</div>

		<div class="stit_my02">${shop_so.getUserInfo().userName} 님,</div>

		<div class="grade_wrap">
			<div class="in_bx">
				<div class="tx_area">
					<ul>
						<li${frontMypageVO.frontMemberGrp.grpnm eq 'F' ? ' class="on"' : ''}>F</li>
						<li${frontMypageVO.frontMemberGrp.grpnm eq 'V1' ? ' class="on"' : ''}>V1</li>
						<li${frontMypageVO.frontMemberGrp.grpnm eq 'V2' ? ' class="on"' : ''}>V2</li>
						<li${frontMypageVO.frontMemberGrp.grpnm eq 'V3' ? ' class="on"' : ''}>V3</li>
						<li${frontMypageVO.frontMemberGrp.grpnm eq 'V4' ? ' class="on"' : ''}>V4</li>
						<li${frontMypageVO.frontMemberGrp.grpnm eq 'V5' ? ' class="on"' : ''}>V5</li>
					</ul>
					<div style="clear:both;"></div>
				</div>
				<div class="bar_area">
					<c:set var="grpper" value="${frontMypageVO.frontMember.grpsale / frontMypageVO.frontMemberMaxGrp.kAmount * 100}" />
					<div class="bar_out" style="width:<fmt:formatNumber pattern="#0.00" value="${grpper < 6 ? 6 : grpper}"/>%;">
						<div class="b_in">
							<div class="b_ins_ins">
								<span style="cursor:pointer">₩<fmt:formatNumber pattern="#0.00" value="${frontMypageVO.frontMember.grpsale}"/></span>
								<div class="b_layer" style="display:none">
									<p><img src="/resources/shop/data/skin/kr/images/arr_prox.png" alt="" /></p>
									${shopLibFunction:getExchange(frontMypageVO.frontMember.grpsale, wskin)}
								</div>
							</div>
						</div>
					</div>
				</div>
				<p class="tx_first"><c:if test="${grpper >= 6}">0</c:if></p>
				<p class="tx_last">₩${stringUtil:getMoneyFormatInteger(frontMypageVO.frontMemberMaxGrp.kAmount)}</p>
			</div>
		</div>
		
		<div class="grade_bt_tx_bx">
			<dl>
				<dt>고객님은 <span style="font-weight:800;">${frontMypageVO.frontMemberGrp.grpnm}</span> 등급입니다.</dt>
				<dd>고객님은 ₩<span style="font-weight:600; color:#ff0000;"><fmt:formatNumber pattern="#0.00" value="${frontMypageVO.frontMemberNxtGrp.kAmount - frontMypageVO.frontMember.grpsale}"/></span> 추가 구매시 <span style="font-weight:800;">${frontMypageVO.frontMemberNxtGrp.grpnm}</span> 등급이 되십니다.</dd>
			</dl>
		</div>

		<div class="lin_my01"></div>

		<div class="stit_my01">적립내역</div>

		<div class="emoney-wrap" style="border-top:3px solid #000;">
			<div class="">
				<div class="tab-pane fade in active">
					<div class="">
						<div class="emoney_tp_tit">
							<div class="in_bx area01">적립일</div>
							<div class="in_bx area02">적립항목</div>
							<div class="in_bx area03">구매내역</div>
							<div class="in_bx area04">적립금</div>
							<div style="clear:both;"></div>
						</div>
						
						<c:choose>
							<c:when test="${frontMypageVO.rowCount > 0}">
								<div class="emoney_log_list">
								</div>
								
								<div class="emoney_btm_btn_c">
									<a href="#" class="btn_more">더보기</a>
								</div>
							</c:when>
							<c:otherwise>
								<article class="product-review">
									<div>
										<h5 class="product-review-title text-center">적립금내역이 없습니다.</h5>
										<p class="product-review-meta" style="margin-left:30px;"></p>
									</div>
								</article>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>

		<div class="emoney_btm_grade">
			<div class="stit_my01">등급혜택 안내</div>
			<div>
				<img src="/resources/shop/data/skin/kr/images/img_test01.png" style="width:100%;" class="img_pc" />
			</div>
		</div>

		<div class="cont_policy01 mob">
			<div style="padding:0 0 23px 0; margin-bottom:10px; border-bottom:1px solid #d4d4d4;">
				<ul style="margin:0 -10px;">
					<li class="r_tab01" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab01.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab02" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab02.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab03" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab03.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab04" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab04.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab05" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab05.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab06" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab06.png" alt="" style="width:100%;" /></a></li>
				</ul>
				<div style="clear:both;"></div>
			</div>
			<div class="r_mob01" style="margin:0 -20px;"><img src="/resources/shop/data/skin/kr/images/r_mob01.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob02" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob02.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob03" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob03.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob04" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob04.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob05" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob05.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob06" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob06.jpeg" alt="" style="width:100%;" /></div>
		</div>

		<script>
			$(document).ready(function(){
				$(".r_tab01").click(function(){
					$(".r_mob01").css("display", "block");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab02").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "block");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab03").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "block");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab04").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "block");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab05").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "block");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab06").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "block");
				});
			});
			</script>

	</div>
</div>