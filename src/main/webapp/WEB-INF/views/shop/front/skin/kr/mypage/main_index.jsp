<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">

$(function(){

});
</script>
<script language="javascript">
function goPage(page){
	$('#pageNo').val(page);
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

$(function(){
	$(".b_ins_ins > span").on("click", function(e){
		e.preventDefault();
		$(this).next(".b_layer").toggle();
	});
});
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
			<jsp:param name="tab_order" value="" />
		</jsp:include>
	
		<div class="">
			<div class="">
				<div class="tab-pane fade in active">
					<div class="stit_my01">
						${shop_so.getUserInfo().userName} 님,
						<a href="${ctx}/shop/member/logout" class="btn_mob_logout">LOGOUT</a>
					</div>

					<div class="tx_my01">
						적립금의 기준 구매액은 금액 클릭 시 환율이 적용된 원화가격을 확인하실 수 있습니다.
						<a href="${ctx}/shop/mypage/mypage_emoney"><span>i</span></a>
					</div>

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

					<div class="grade_gray_bx">
						<div class="in_bx">
							<dl>
								<dt>나의 적립금</dt>
								<dd>
									<c:choose>
										<c:when test="${frontMypageVO.frontMember.emoney > 0}">
											<a href="${ctx}/shop/mypage/mypage_emoney">${shopLibFunction:getExchange(frontMypageVO.frontMember.emoney, 'kr')}</a>
										</c:when>
										<c:otherwise>${shopLibFunction:getExchange(frontMypageVO.frontMember.emoney, 'kr')}</c:otherwise>
									</c:choose>
								</dd>
							</dl>
						</div>
						<div class="in_bx">
							<dl>
								<dt>할인쿠폰</dt>
								<dd style="line-height:19px;">
									<c:choose>
										<c:when test="${frontMypageVO.frontCouponCount > 0}">
											<a href="${ctx}/shop/mypage/mypage_coupon">${stringUtil:getMoneyFormatInteger(frontMypageVO.frontCouponCount)}장</a>
										</c:when>
										<c:otherwise>${stringUtil:getMoneyFormatInteger(frontMypageVO.frontCouponCount)}장</c:otherwise>
									</c:choose>
								</dd>
							</dl>
						</div>
						<div class="in_bx">
							<dl>
								<dt>나의 리뷰</dt>
								<dd>
									<c:choose>
										<c:when test="${frontMypageVO.frontReviewCount > 0}">
											<a href="${ctx}/shop/mypage/mypage_review">${stringUtil:getMoneyFormatInteger(frontMypageVO.frontReviewCount)}건</a>
										</c:when>
										<c:otherwise>${stringUtil:getMoneyFormatInteger(frontMypageVO.frontReviewCount)}건</c:otherwise>
									</c:choose>
								</dd>
							</dl>
						</div>
						<div class="in_bx">
							<dl>
								<dt>위시리스트</dt>
								<dd>
									<c:choose>
										<c:when test="${frontMypageVO.frontWishCount > 0}">
											<a href="${ctx}/shop/mypage/mypage_wishlist">${stringUtil:getMoneyFormatInteger(frontMypageVO.frontWishCount)}건</a>
										</c:when>
										<c:otherwise>${stringUtil:getMoneyFormatInteger(frontMypageVO.frontWishCount)}건</c:otherwise>
									</c:choose>
								</dd>
							</dl>
						</div>
						<div class="in_bx">
							<dl class="last">
								<dt>장바구니</dt>
								<dd>
									<c:choose>
										<c:when test="${fn:length(frontMypageVO.frontGoodsCartList) > 0}">
											<a href="${ctx}/shop/goods/goods_cart">${stringUtil:getMoneyFormatInteger(fn:length(frontMypageVO.frontGoodsCartList))}건</a>
										</c:when>
										<c:otherwise>${stringUtil:getMoneyFormatInteger(fn:length(frontMypageVO.frontGoodsCartList))}건</c:otherwise>
									</c:choose>
								</dd>
							</dl>
						</div>
					</div>

					<div class="lin_my01"></div>

					<div class="stit_my01" style="position:relative;">나의 주문상태 <span style="position:absolute; top:0; right:0; font-size:11px; line-height:11px;"><a href="/shop/mypage/mypage_orderlist" style="color:#787878;">주문 / 배송조회 ></a></span></div>

					<div class="order_step">
						<div class="in_bx">
							<dl>
								<dt>결제완료</dt>
								<dd>${orderStepInfo.cnt1}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>재고확인중</dt>
								<dd>${orderStepInfo.cnt2}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>배송준비중</dt>
								<dd>${orderStepInfo.cnt3}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>배송중</dt>
								<dd>${orderStepInfo.cnt4}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>배송완료</dt>
								<dd>${orderStepInfo.cnt5}</dd>
							</dl>
						</div>
					</div>

					<div class="mypage_menu_pc_view">
						<div class="my_index_bx_l my_ty_list">
							<div class="in_line_bx">
								<dl>
									<dt>
										나의 문의 
										<a href="${ctx}/shop/mypage/mypage_qna_goods" class="btn_more">(더보기)</a>
									</dt>
									<dd>
										<c:choose>
											<c:when test="${fn:length(frontMypageVO.frontGoodsQnaList) > 0}">
												<ul>
													<c:forEach var="qna_rtList" items="${frontMypageVO.frontGoodsQnaList}" varStatus="status">
														<li>
															<a href="${ctx}/shop/mypage/mypage_qna_goods">
																<span class="date"><fmt:formatDate value="${qna_rtList.regdt}" pattern="yyyy-MM-dd"/></span>
																${qna_rtList.subject}
																<span class="arr_r">></span>
															</a>
														</li>
													</c:forEach>
												</ul>	
											</c:when>
											<c:otherwise>
												<div class="no_review">
													작성된 문의가 없습니다.
												</div>
											</c:otherwise>
										</c:choose>
									</dd>
								</dl>
							</div>
						</div>
						<div class="my_index_bx_r my_ty_list">
							<div class="in_line_bx">
								<dl>
									<dt>
										상품 리뷰
										<a href="${ctx}/shop/mypage/mypage_review" class="btn_more">(더보기)</a>
									</dt>
									<dd>
										<c:choose>
											<c:when test="${fn:length(frontMypageVO.frontReviewList) > 0}">
												<ul>
													<c:forEach var="rList" items="${frontMypageVO.frontReviewList}" begin="0" end="2" varStatus="status">
														<li>
															<a href="${ctx}/shop/mypage/mypage_review">
																<span class="date"><fmt:formatDate value="${rList.regdt}" pattern="yyyy-MM-dd"/></span>
																${rList.contents}
																<span class="arr_r">></span>
															</a>
														</li>
													</c:forEach>
												</ul>	
											</c:when>
											<c:otherwise>
												<div class="no_review">
													작성된 리뷰가 없습니다.
												</div>
											</c:otherwise>
										</c:choose>
									</dd>
								</dl>
							</div>
						</div>
						<div class="my_index_bx_l my_ty_thum">
							<div class="in_line_bx">
								<dl>
							<!-- <div style="height:274px; border:1px solid #dcdcdc;">
								<dl style="padding:24px 30px 25px 30px;"> -->
									<dt>
										위시리스트
										<a href="${ctx}/shop/mypage/mypage_wishlist" class="btn_more">(더보기)</a>
									</dt>
									<dd>
										<c:choose>
											<c:when test="${fn:length(frontMypageVO.frontWishList) > 0}">
												<div class="sub_list02">
													<ul>
														<c:forEach var="wi_gdList" items="${frontMypageVO.frontWishList}" varStatus="status">
															<li>
																<div class="in_bx">
																	<div class="bx_thum">
																		<img class="product-img-primary product-img" src="${wi_gdList.imgs}" alt="${wi_gdList.goodsnmKR }" />
																	</div>
																</div>
															</li>
														</c:forEach>
													</ul>
												</div>
											</c:when>
											<c:otherwise>
												<div class="no_review">
													등록된 데이터가 없습니다.
												</div>
											</c:otherwise>
										</c:choose>
									</dd>
								</dl>
							</div>
						</div>
						<div class="my_index_bx_r my_ty_thum">
							<div class="in_line_bx">
								<dl>
									<dt>
										장바구니
										<a href="${ctx}/shop/goods/goods_cart" class="btn_more">(더보기)</a>
									</dt>
									<dd>
										<c:choose>
											<c:when test="${fn:length(frontMypageVO.frontGoodsCartList) > 0}">
												<div class="sub_list02">
													<ul>
														<c:forEach var="ct_rtList" items="${frontMypageVO.frontGoodsCartList}" begin="0" end="3" varStatus="status">
															<li>
																<div class="in_bx">
																	<div class="bx_thum">
																		<img class="product-img-primary product-img" src="${ct_rtList.img}" alt="${ct_rtList.goodsnmKR }" />
																	</div>
																</div>
															</li>
														</c:forEach>
													</ul>
												</div>
											</c:when>
											<c:otherwise>
												<div class="no_review">
													등록된 데이터가 없습니다.
												</div>
											</c:otherwise>
										</c:choose>
									</dd>
								</dl>
							</div>
						</div>
						<div style="clear:both;"></div>
					</div>

					<!-- 모바일 메뉴 -->
					<div class="mypage_menu_mob_view" style="border-top:1px solid #acacac;">
						<ul>
							<li style="border-bottom:1px solid #acacac;">
								<div class="m_s_tit">혜택관리</div>
								<ul style="padding:12px 0 10px 10px; display:none;">
									<li style="padding:0 0 9px 0;"><a href="/shop/mypage/mypage_coupon" style="font-size:14px; color:#313131; line-height:14px;">쿠폰함</a></li>
									<li style="padding:0 0 9px 0;"><a href="/shop/mypage/mypage_emoney" style="font-size:14px; color:#313131; line-height:14px;">적립금관리 </a></li>
								</ul>
							</li>
							<li style="border-bottom:1px solid #acacac;">
								<div class="m_s_tit">나의쇼핑</div>
								<ul style="padding:12px 0 10px 10px; display:none;">
									<li style="padding:0 0 9px 0;"><a href="/shop/mypage/mypage_wishlist" style="font-size:14px; color:#313131; line-height:14px;">위시리스트</a></li>
									<li style="padding:0 0 9px 0;"><a href="/shop/goods/goods_cart" style="font-size:14px; color:#313131; line-height:14px;">장바구니</a></li>
									<li style="padding:0 0 9px 0;"><a href="/shop/mypage/mypage_qna_goods" style="font-size:14px; color:#313131; line-height:14px;">나의 문의내역</a></li>
									<li style="padding:0 0 9px 0;"><a href="/shop/mypage/mypage_review" style="font-size:14px; color:#313131; line-height:14px;">상품리뷰</a></li>
								</ul>
							</li>
							<li style="border-bottom:1px solid #acacac;">
								<div class="m_s_tit">회원정보</div>
								<ul style="padding:12px 0 10px 10px; display:none;">
									<li style="padding:0 0 9px 0;"><a href="/shop/member/myinfo" style="font-size:14px; color:#313131; line-height:14px;">회원정보수정</a></li>
								</ul>
							</li>
						</ul>
					</div>
					<script>
					$(document).ready(function(){
						$(".mypage_menu_mob_view li .m_s_tit").click(function(){
							if(!$(this).data("click")){
								$(this).addClass("on");
								$(this).parent().find("ul").css("display", "block");
								$(this).data("click", true);
							}else{
								$(this).removeClass("on");
								$(this).parent().find("ul").css("display", "none");
								$(this).data("click", false);
							}
						});
					});
					</script>
					<!--//모바일 메뉴 -->

				</div>
			</div>
		</div>
	</div>
</div>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



