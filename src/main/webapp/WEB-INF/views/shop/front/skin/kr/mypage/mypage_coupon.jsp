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
		url : 'mypage_coupon.doJson',
		data: { pageNo: page },
		async: false,
		dataType : 'json',
		success : function (data) {
			if (data.RESULT){
	        	var html = "";
	        	$.each(data.frontMypageVO.frontCouponList, function(index, item){
					html += '<li>';
					html += '	<div class="in_bx area01">' + item.coupon + '</div>';
					html += '	<div class="in_bx area02">' + item.price + ' 할인' + (item.maxprice != '' ? ' (최대 할인액:' + item.maxprice + ')' : '') + '</div>';
					html += '	<div class="in_bx area03">' + item.expdate.substring(0, 10) + '</div>';
					html += '	<div class="in_bx area04">' + (item.excPrice == '0' ? '-' : item.excPrice + ' 이상 구매시') + '</div>';
					html += '	<div class="in_bx area05">' + (item.used == '0' ? '미사용' : '사용완료') + '</div>';
					html += '	<div style="clear:both;"></div>';
					html += '</li>';
				});
				$(".coupon_bt_list .cont").append(html);
				
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
</script>

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-mypage-coupon container_mypage">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="mypage_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="5" />
		</jsp:include>

		<div class="navi_my">혜택관리 > 쿠폰함</div>
	
		<div class="">
			<div class="coupon_tp_info">
				<dl>
					<dt>사용 가능한 쿠폰</dt>
					<dd>${frontMypageVO.rowCount}장</dd>
				</dl>
			</div>

			<div class="coupon_bt_list">
				<ul>
					<li>
						<div class="in_bx area01">쿠폰명</div>
						<div class="in_bx area02">혜택</div>
						<div class="in_bx area03">유효기간</div>
						<div class="in_bx area04">사용조건</div>
						<div class="in_bx area05">상태</div>
						<div style="clear:both;"></div>
					</li>
				</ul>
				<ul class="cont">
				</ul>
			</div>

			<div class="emoney_btm_btn_c">
				<a href="#" class="btn_more" data-page="1">더보기</a>
			</div>
		</div>
	</div>
</div>