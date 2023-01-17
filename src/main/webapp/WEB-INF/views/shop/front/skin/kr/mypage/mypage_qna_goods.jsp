<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">
$(function(){
	$('.qnaCon').on('click', function(e){
		e.preventDefault();
		
		var sno = $(this).data("no");
		if ($("#test_" + sno).is(':visible')){
			$("#test_" + sno).hide();
		}else{	
			$("#test_" + sno).show();
		}
	});
});

function goPage(page){
	$('#pageNo').val(page);
	$('#frmList').submit();
}

function popup_register(mode, goodsno, sno){
	if ( mode == 'del_qna' ) var win = window.open("../goods/goods_qna_del.jsp?mode=" + mode + "&sno=" + sno,"qna_register","width=400,height=200");
	else var win = window.open("../goods/goods_qna_register.jsp?mode=" + mode + "&goodsno=" + goodsno + "&sno=" + sno,"qna_register","width=600,height=500");
	win.focus();
}

var preContent;
function view_content(obj){
	var div = obj.parentNode;

	if ( document.all ) obj = div.childNodes[ 1 ]; else obj = div.childNodes[ 3 ]; // 모질라 경우 줄내림도 #text 임

	if (preContent && obj!=preContent){
		obj.style.display = "block";
		preContent.style.display = "none";
	}
	else if (preContent && obj==preContent) preContent.style.display = ( preContent.style.display == "none" ? "block" : "none" );
	else if (preContent == null ) obj.style.display = "block";

	preContent = obj;
}
</script>

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-mypage-qna-goods container_mypage">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="../mypage/mypage_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="9" />
		</jsp:include>

		<div class="navi_my">나의 쇼핑 > 나의 문의내역</div>
	
		<div class="">
			<div class="">
				<div class="tab-pane fade in active">
					<div class="">
						<form name="frmList" id="frmList">
							<input type="hidden" id="pageNo" name="pageNo" value="${myBoardVO.pageNo}" />
							<div class="box-line"></div>
							
							<c:if test="${empty myBoardVO.goodsQnaList}">
								<article class="product-review" style="height:86px;">
									<div>
										<h5 class="product-review-title text-center">상품문의가 없습니다.</h5>
										<p class="product-review-meta" style="margin-left:30px;"></p>
									</div>
								</article>
							</c:if>
							
							<c:forEach var="qna_rtList" items="${myBoardVO.goodsQnaList}" varStatus="status">
								<article class="product-review my_qna">
									<div class="product-review-author">${(myBoardVO.rowCount - status.index) - myBoardVO.rowStart}</div>
									<div class="cont_bx">
										<h5 class="product-review-title">
											<a href="${ctx}/shop/goods/goods_view?goodsno=${qna_rtList.goodsno}&category=${qna_rtList.category}" class="bx_thum">
												<c:set var="idx_img_s" value="${fn:indexOf(qna_rtList.imgs, '|')+ 1}" />
												<c:set var="img_len" value="${fn:length(qna_rtList.imgs)}"/>
												<c:set var="sub_img_s" value="${fn:substring(qna_rtList.imgs, idx_img_s, img_len)}" />
												<img class="product-review-author-img" src="${qna_rtList.imgs}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'">
											</a>
											<div class="tx01">
												<p>${qna_rtList.recnt > 0 ? '답변완료' : '미답변'}</p>
												<fmt:formatDate value="${qna_rtList.regdt}" pattern="yyyy-MM-dd"/>
											</div>
											<div class="tx02">
												<span>${qna_rtList.goodsnmKR}</span>&nbsp;&nbsp;&nbsp;<a href="${ctx}/shop/goods/goods_view?goodsno=${qna_rtList.goodsno}&category=${qna_rtList.category}"><img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/btn_goodview2.gif" align=absmiddle></a>
											</div>
											<a href="#" class="qnaCon tx03" data-no="${qna_rtList.sno}">${qna_rtList.subject}</a>
										</h5>
										<p class="product-review-meta">
											제목 : <%-- ${qna_rtList.subject} --%><a id="${qna_rtList.sno}" class="qnaCon" style="cursor:pointer">${qna_rtList.subject}</a> <span>[${qna_rtList.recnt}]</span>
											작성자 : ${qna_rtList.mid}
											작성일 : <fmt:formatDate value="${qna_rtList.regdt}" pattern="yyyy-MM-dd"/>
										</p>
									</div>
									<!-- 컨텐츠 -->
									<div id="test_${qna_rtList.sno}" class="testall" style="display:none;">
										<div class="ct_bx">
											<div class="text-left">
												<font>${qna_rtList.contents}&nbsp;</font> 
												<div class="date_r">
													<div class="tb_out">
														<div class="tb_in"></div>
													</div>
												</div>
											</div>
										</div>
										<c:forEach var="replyList" items="${qna_rtList.replyList}">
											<div class="ct_bx">
												<div class="text-left">
													<font>${replyList.contents}&nbsp;</font> 
													<div class="date_r">
														<div class="tb_out">
															<div class="tb_in">
																<fmt:formatDate value="${replyList.regdt}" pattern="yyyy-MM-dd"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</article>
							</c:forEach>
							
							<!-- paging -->
							<nav class="text-center">
								<ul class="pagination category-pagination">
									<tags:frontPaginator currentPageNo="${myBoardVO.pageNo}" rowCount="${myBoardVO.rowCount}" pageSize="${myBoardVO.pageSize}"  pageGroupSize="${myBoardVO.pageGroupSize}" />
								</ul>
							</nav>
						</form>
				
					</div>
				</div>
			</div>
		</div>
	</div>
</div>