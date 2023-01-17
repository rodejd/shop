<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">

</script>

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-mypage-review container_mypage">
		<div class="tabbable product-tabs">
			<!-- 고객센터 공통탭메뉴 처리 -->
			<jsp:include page="../mypage/mypage_tab_menu.jsp" flush="true">
				<jsp:param name="tab_order" value="8" />
			</jsp:include>

			<div class="navi_my">나의쇼핑 > 상품리뷰</div>
		
			<div class="">
				<div class="detail_cont_wrap">
					<div class="tab-pane fade in active" >
						<form method="post" action="indb" name="rfm" id="rfm" >
							<input type="hidden"  name="mode" id="mode" value="">
							<input type="hidden" name="goodsno" id="goodsno" value="">
							<input type="hidden" name="contents" id="contents" value="">
							<input type="hidden" name="point" id="point" value="">
							<input type="hidden" name="mno" id="mno" value="${myBoardVO.mno }">
							<input type="hidden" name="sno" id="sno" value="">
							
							<div class="tit_bx_review">내가 쓴 리뷰</div>
							<div class="bx_review ty_n01">
								<ul>
									<c:if test="${empty myBoardVO.reviewList }">
										<li>상품평이 없습니다</li>
									</c:if>
									<c:if test="${not empty myBoardVO.reviewList }">
										<c:forEach var="rList" items="${myBoardVO.reviewList }" varStatus="status" >
											<li>
												<div class="bx_thum01" style="text-align: center;">
													<img src="${rList.imgs}" alt="${rList.goodsnmKR}"  onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'" />
												</div>
												<div class="in_ct01">
													<p class="tx_brand">${rList.brandnm}</p>
													<p class="tx_tit">${rList.goodsnmKR}</p>
													<p class="tx_num">${frontGoodsVO.goodsView.binCd}</p>
													<p class="bx_star">
														<c:forEach begin="0" end="4" step="1" varStatus="status">
															<c:if test="${status.index < rList.point }">
																<i class="fa fa-star"></i>
															</c:if>
														</c:forEach>
													</p>
												</div>
												<div class="in_ct02">
													<!-- <div class="tb_out">
														<div class="tb_in"> -->
															${rList.contents }
														<!-- </div>
													</div> -->
												</div>
												<div class="in_ct03">
													<div class="tb_out">
														<div class="tb_in">
															<fmt:formatDate value="${rList.regdt }" pattern="yyyy-MM-dd"/></p>
														</div>
													</div>
												</div>
												<c:if test="${not empty rList.reviewimg}">
													<div class="bx_thum02">
														<a href="#" class="p_r_view" data-no="${rList.sno}"><img src="${rList.reviewimg}" alt="" style="width: 100%; height: 100%;"/></a>
														<span style="position:absolute; right:0; bottom:0; width:20px; height:20px; color:#fff; line-height:20px; background:#000; text-align:center;">+</span>
													</div>
												</c:if>
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
							
							<nav class="text-center">
								<ul class="pagination category-pagination">
									<tags:frontPaginator currentPageNo="${myBoardVO.pageNo}" rowCount="${myBoardVO.rowCount}" pageSize="${myBoardVO.pageSize}"  pageGroupSize="${myBoardVO.pageGroupSize}" />
								</ul>
							</nav>
						
							<%-- 
							<div class="my_order_wrap01 ty02">
								<div class="my_list_ty01">
									<ul class="tit_area">
										<li>
											<div class="in_bx area01">
												<p class="tx01">주문일자</p>
												<p class="tx02">(주문번호)</p>
											</div>
											<div class="in_bx area02">상품정보</div>
											<div class="in_bx area03">결제금액</div>
											<div class="in_bx area04">주문상태</div>
											<div class="in_bx area05"></div>
											<div style="clear:both;"></div>
										</li>
									</ul>
									<ul class="cont_area">
										<c:set var="img" value="${cartList.img }" scope="request"/>
										<li>
											<div class="wrap_in">
												<div class="area01">
													<p class="tx01">2021-03-09</p>
													<p class="tx02">(123456789)</p>
												</div>
												<div class="area02_01">
													<a href="${ctx }/shop/goods/goods_view?goodsno=${cartList.goodsno }&category=${cartList.goodsCategory}">
														${shopLibFunction:goodsimg(cartList.img, "100,100", "", 4) }
													</a>
												</div>
												<div class="area02_02">
													<div class="tx01">
														<a href="${ctx }/shop/goods/goods_view?goodsno=${cartList.goodsno }&category=${cartList.goodsCategory}">
															<dl>
																<dt>GUCCI</dt>
																<dd>구찌 가죽 숄더백 구찌 가죽 숄더백</dd>
															</dl>
														</a>
													</div>
													<div class="tx02">BROWN , ONE SIZE</div>
													<div class="tx03">1개</div>
												</div>
												<div class="area03">
													<div class="tb_out">
														<div class="tb_in">
															4,230,000
														</div>
													</div>
												</div>
												<div class="area04">
													<div class="tb_out">
														<div class="tb_in">
															배송완료
														</div>
													</div>
												</div>
												<div class="area05">
													<div class="tb_out">
														<div class="tb_in">
															<a class="btn btn-default btn_write_review btn_ty02" href="#">리뷰 작성</a>
														</div>
													</div>
												</div>
												<a href="#" class="btn_more_mob_n">상세보기  ></a>
											</div>
										</li>
									</ul>
								</div>
							</div>
							 --%>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form name="frmList" class="frmList">
		<input type="hidden" class="pageNo" name="pageNo" value="1" />
	</form>


<!-- 팝업 리뷰 작성하기 -->
<script>
$(document).ready(function(){
	$(".btn_write_review").click(function(){
		$(".pop_good_review").css("display", "block");
	});
	$(".pop_good_review .btn_pop_close_n").click(function(){
		$(".pop_good_review").css("display", "none");
	});
});
</script>
<style>
/* input file */
.filebox input[type="file"] {position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px; overflow: hidden; clip:rect(0,0,0,0); border: 0;}
.filebox label {display:block; width:130px; height:130px; color:#252525; vertical-align:top; text-align:center; text-decoration:none; cursor: pointer;}

/* named upload */
.filebox .upload-name {float:left; display:block; width:493px; height:36px; font-size:14px; line-height:36px; background:#fff; border:1px solid #ced4da; border-radius:3px; padding:0 0 0 20px; margin:0 6px 0 0; vertical-align:top;
-webkit-appearance: none;
-moz-appearance: none;
appearance: none;
}

/* imaged preview */
.filebox .upload-display {/*margin-bottom: 5px;*/}
</style>
<div class="pop_wrap pop_good_review" style="position:absolute; top:0; left:0; width:100%; height:100%; z-index:100000000; display:none;">
	<div style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.7;"></div>
	<div style="position:absolute; top:0; left:0; width:100%;">
		<div style="width:1256px; margin:30px auto 0 auto;">
			<div style="position:relative; height:70px; font-size:25px; color:#fff; line-height:70px; background:#292929; text-align:center;">
				리뷰 작성하기
				<a href="javascript:void(0);" class="btn_pop_close_n" style="position:absolute; top:28px; right:70px; font-size:25px; color:#fff; line-height:25px; text-decoration:none;">X</a>
			</div>
			<div style="background:#fff; padding:60px 100px 60px 100px;">
				<div style="border-bottom:2px solid #d0d0d0;">
					<ul>
						<li style="position:relative; height:193px; padding:0 0 0 245px;">
							<div style="position:absolute; top:0; left:30px; width:160px; height:160px; border:1px solid #e5e5e5;"></div>
							<dl style="padding:0; margin:0;">
								<dt style="font-weight:600; font-size:22px; color:#000; line-height:22px; padding:25px 0 22px 0;">MASION MARGIELA</dt>
								<dd style="font-size:17px; color:#2b2b2b; line-height:17px; padding:0 0 13px 0;">
									남성 스니커즈
								</dd>
							</dl>
							<p style="font-size:20px; color:#949494; line-height:20px; padding:0 0 3px 0; margin:0;">BLUE, 7 SIZE</p>
							<div style="font-size:20px; color:#1d1d1d; line-height:20px; padding:0; margin:0 0 0 0;">1개</div>
						</li>
					</ul>
				</div>
				<div style="padding:0 30px 25px 30px; border-bottom:2px solid #d0d0d0;">
					<p style="font-weight:500; font-size:17px; color:#000; line-height:17px; padding:15px 0 23px 0; margin:0;">별점*</p>
					<ul>
						<li style="float:left; padding:0 12px 0 0;"><a href="#" style="font-size:25px; line-height:25px; color:#252525;">☆</a></li>
						<li style="float:left; padding:0 12px 0 0;"><a href="#" style="font-size:25px; line-height:25px; color:#252525;">☆</a></li>
						<li style="float:left; padding:0 12px 0 0;"><a href="#" style="font-size:25px; line-height:25px; color:#252525;">☆</a></li>
						<li style="float:left; padding:0 12px 0 0;"><a href="#" style="font-size:25px; line-height:25px; color:#252525;">☆</a></li>
						<li style="float:left; padding:0 12px 0 0;"><a href="#" style="font-size:25px; line-height:25px; color:#252525;">☆</a></li>
					</ul>
					<div style="clear:both;"></div>
				</div>
				<div style="padding:0 30px 25px 30px;">
					<p style="font-weight:500; font-size:17px; color:#000; line-height:17px; padding:15px 0 23px 0; margin:0;">이미지 업로드</p>
					<ul>
						<li style="float:left; width:130px; height:130px; margin:0 7px 0 0;"><img src="https://betterstudio.com/wp-content/uploads/2019/05/1-1-instagram-1024x1024.jpg" alt="" style="width:100%;" /></li>
						<li style="float:left; width:130px; height:130px; font-size:58px; line-height:130px; margin:0 7px 0 0; border:1px solid #b8b8b8; text-align:center;">
							<div class="filebox preview-image">
								<input class="upload-name" placeholder="파일선택" value="" disabled="disabled" style="display:none;">
								<input type="file" name="profile" id="profile" class="upload-hidden">
								<label for="profile" class="btn_add_p">+</label>
							</div>
						</li>
						<li style="float:left; width:130px; height:130px; font-size:58px; line-height:130px; margin:0 7px 0 0; border:1px solid #b8b8b8; text-align:center;">
							<div class="filebox preview-image">
								<input class="upload-name" placeholder="파일선택" value="" disabled="disabled" style="display:none;">
								<input type="file" name="profile" id="profile" class="upload-hidden">
								<label for="profile" class="btn_add_p">+</label>
							</div>
						</li>
						<li style="float:left; width:130px; height:130px; font-size:58px; line-height:130px; margin:0 7px 0 0; border:1px solid #b8b8b8; text-align:center;">
							<div class="filebox preview-image">
								<input class="upload-name" placeholder="파일선택" value="" disabled="disabled" style="display:none;">
								<input type="file" name="profile" id="profile" class="upload-hidden">
								<label for="profile" class="btn_add_p">+</label>
							</div>
						</li>
					</ul>
					<div style="clear:both;"></div>
					<div style="padding:20px 0 13px 0;">
						<textarea style="width:100%; height:260px; padding:20px; border:1px solid #d0d0d0;">
내용을 입력하세요 (최소 1000byte 이상, 최대 2000byte 미만)
						</textarea>
					</div>
					<div style="font-size:16px; color:#383838; line-height:22px; padding:15px 0 55px; 0;">
						- 욕설, 근거없는 비방, 검증되지 않는 가품시비 등은 예고없이 삭제될 수 있습니다.<br/>
						- 업로드한 리뷰는 수정, 삭제가 불가능합니다. 
					</div>
					<div style="text-align:center;">
						<a href="#" style="display:inline-block; width:250px; height:57px; font-size:19px; color:#fff; line-height:57px; background:#292929; text-align:center;">리뷰 작성</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- //팝업 리뷰 작성하기 -->

<script>
function doMod(){
	$("#mode").val("mod_review");
	$("#contents").val($("#hcontents").val());
	$("#point").val($("#hpoint").val());
	$("#sno").val($("#hsno").val());
	$("#rfm").attr('action',ctx +'/shop/mypage/indb');
	$("#rfm").submit();
}
function doDel(){
	$("#mode").val("del_review");
	$("#contents").val($("#hcontents").val());
	$("#point").val($("#hpoint").val());
	$("#sno").val($("#hsno").val());
	$("#rfm").attr('action', ctx+'/shop/mypage/indb');
	$("#rfm").submit();
}
function goPage(page){
	$('.pageNo').val(page);
	$('.frmList').submit();
}
</script>

<link rel="stylesheet" href="/resources/shop/data/skin/kr/css/swiper.min.css">
<script src="/resources/shop/data/skin/kr/js/swiper.min.js"></script>
<script>
$(document).on('click', ".p_r_view", function(e){
	e.preventDefault();
	$(".mySwiper .swiper-wrapper, .mySwiper2 .swiper-wrapper").empty();
	
	var sno = $(this).data("no");
	ajaxCallJson('/shop/goods/ajaxReviewFileList.dojson',
	{
		'sno' : sno
	},
	function(data) {
		var reviewFileList = data.reviewFileList;
		var fileList = "";
		if (reviewFileList.length > 0) {
			for (var i = 0; i < reviewFileList.length; i++) {					
				fileList += '<div class="swiper-slide">';
				fileList += '	<img src="' + reviewFileList[i].img + '" alt="" style="width:100%;" />';
				fileList += '</div>';
			}
			$(".mySwiper .swiper-wrapper").html(fileList);
			$(".mySwiper2 .swiper-wrapper").html(fileList);
			
			$(".pop_photo_review").css("display", "block");
		    var swiper = new Swiper(".mySwiper", {
		  	  spaceBetween: 10,
		  	  slidesPerView: 3,
		  	  freeMode: true,
		  	  watchSlidesVisibility: true,
		  	  watchSlidesProgress: true,
		    });
		    var swiper2 = new Swiper(".mySwiper2", {
		  	  spaceBetween: 10,
		  	  navigation: {
		  	    nextEl: ".swiper-button-next",
		  	    prevEl: ".swiper-button-prev",
		  	  },
		  	  thumbs: {
		  	    swiper: swiper,
		  	  },
		    });
		}

	},
	function(e) {
		console.log("조회에 실패하였습니다");
	});
	
	$(".pop_photo_review").css("display", "block");

    var swiper = new Swiper(".mySwiper", {
  	  spaceBetween: 10,
  	  slidesPerView: 3,
  	  freeMode: true,
  	  watchSlidesVisibility: true,
  	  watchSlidesProgress: true,
    });
    var swiper2 = new Swiper(".mySwiper2", {
  	  spaceBetween: 10,
  	  navigation: {
  	    nextEl: ".swiper-button-next",
  	    prevEl: ".swiper-button-prev",
  	  },
  	  thumbs: {
  	    swiper: swiper,
  	  },
    });
});
$(document).on('click', ".pop_photo_review .btn_pop_close_n", function(){
	$(".pop_photo_review").css("display", "none");
});
</script>
<style>
.mySwiper2 .swiper-button-next, .swiper-button-prev {top: 50% !important;}
/*.mySwiper .swiper-slide {background:#000;}
.mySwiper .swiper-slide img {opacity:0.7}*/
.mySwiper .swiper-slide-thumb-active img {border:1px solid #ddd;}
</style>
	<!-- 팝업 포토리뷰 -->
	<div class="pop_wrap pop_photo_review" style="position:fixed;display:none;">
		<div class="bx_bg_op"></div>
		<div class="bx_in_pop">
			<div class="in_bx">
				<div class="tit_bx">
					포토 리뷰
					<a href="javascript:void(0);" class="btn_pop_close_n">X</a>
				</div>
				<div class="cont_bx">
					<div class="swiper-container mySwiper2" style="margin-bottom:20px;">
						<div class="swiper-wrapper">
						</div>
						<div class="swiper-button-next"></div>
						<div class="swiper-button-prev"></div>
					</div>
					<div class="swiper-container mySwiper">
						 <div class="swiper-wrapper">
						</div>
						<div style="clear:both;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //팝업 포토리뷰 -->