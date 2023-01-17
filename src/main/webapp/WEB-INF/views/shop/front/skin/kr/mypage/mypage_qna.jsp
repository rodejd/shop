<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>    
    
	<!-- <header class="page-header page-header-banner x-member-h">
	    <div class="container">
	        <div class="page-header-banner-inner">
	            <h1 class="page-title">1:1문의게시판</h1>
	        </div>
	    </div>
	</header> -->
	
	<div class="top_tit_ty01">
		<div class="tit_dp01">USEFUL INFORMATION</div>
	</div>

	<div class="x-mypage-qna container_service">
		<div class="tabbable product-tabs">
			<!-- 고객센터 공통탭메뉴 처리 -->
			<jsp:include page="../service/service_tab_menu.jsp" flush="true">
				<jsp:param name="tab_order" value="3" />
			</jsp:include>

			<div class="service_wrap01">
				<div class="service_stit01">1:1문의게시판</div>
		
				<div class="tab-content nb">
					<div class="tab-pane fade in active">
						<div class="row">
						
							<c:if test="${fn:length(myBoardVO.qnaList) == 0 }">
						        <article class="product-review">
						            <div>
						                <h5 class="product-review-title" style="text-align:center;">1:1문의 내용이 없습니다.</h5>
						                <p class="product-review-meta" style="margin-left:30px;"></p>
						            </div>
						        </article>
							</c:if>
							
							<c:if test="${fn:length(myBoardVO.qnaList) != 0 }">
								<c:forEach var="qna_rtList1" items="${myBoardVO.qnaList }" varStatus="status">
								
									<article class="product-review q_list" onclick="view_content(${status.index});">
							            <div class="in_bx">
											<div class="product-review-author">${(myBoardVO.rowCount - status.index) - ( myBoardVO.rowStart ) }</div>
							                <h5 class="product-review-title">
							                	<c:if test="${ qna_rtList1.asno ne qna_rtList1.sno }" >
													<span class="tx_answer">[답변]</span>
												</c:if>
												<c:if test="${ qna_rtList1.asno eq qna_rtList1.sno }" >
													<c:if test="${qna_rtList1.itemcd eq '00' }">
														<c:set var="itemcd" value="10" />
													</c:if>
													<b>[${codeUtil:getCodeName("boardtype", qna_rtList1.itemcd)}]</b>
												</c:if>
												
												${ qna_rtList1.subject } 
												<span style="color:#007FC8;" >
													<c:if test="${ qna_rtList1.asno ne qna_rtList1.sno }"></c:if>
													<c:if test="${ qna_rtList1.asno eq qna_rtList1.sno }">[${ qna_rtList1.recnt }]</c:if>
												</span>
							                </h5>
							                <!-- <p class="product-review-meta" style="margin-left:30px;">
												작성자 : ${ qna_rtList1.name } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												작성일 : <fmt:formatDate value="${ qna_rtList1.regdt }" pattern="yyyy-MM-dd"/>
							                </p> -->
											<div class="date_n"><!-- 작성일 :  --><fmt:formatDate value="${ qna_rtList1.regdt }" pattern="yyyy-MM-dd"/></div>
							            </div>
							        </article>
						        
							        <article class="product-review detail a_list" id="detail_${status.index }">
							            <!-- <div class="product-review-author">&nbsp;</div> -->
							            <div>
							                <h5 class="product-review-title desc">
							                	<c:if test="${ '0' ne qna_rtList1.ordno }">
													[ 주문번호 ${ qna_rtList1.ordno } 문의 ]
												</c:if>
												${ qna_rtList1.contents }
							                </h5>
							                <p class="product-review-meta">
							                	<!-- 답변기능 주석처리 -->
												<c:if test="${ myBoardVO.mno eq qna_rtList1.mno }">
												<a href="mypage_qna_register?mode=mod_qna&sno=${ qna_rtList1.sno }" class="btn btn-primary btn_11_modify" >수정</a>
												</c:if>
												<c:if test="${ myBoardVO.mno eq qna_rtList1.mno && ((qna_rtList1.recnt eq '0') || (qna_rtList1.asno ne qna_rtList1.sno)) }">
												<a href="javascript:popup_register( 'del_qna', '${ qna_rtList1.sno }' );" class="btn btn-primary btn_11_delete" >삭제</a>
												</c:if>
							                </p>
							            </div>
							        </article>
						        
								</c:forEach>
							</c:if>
							
							
							<!-- paging -->
							<%-- <div class="list-page mb40" style="margin-bottom:40px;"><%//=page_list %>${page_list }</div> --%>
							<nav class="text-center" style="padding-top:60px;">
								<ul class="pagination category-pagination">
									<tags:frontPaginator currentPageNo="${myBoardVO.pageNo}" rowCount="${myBoardVO.rowCount}" pageSize="${myBoardVO.pageSize}"  pageGroupSize="${myBoardVO.pageGroupSize}" />
								</ul>
							</nav>
							<div style="text-align:right;">
								<a href="mypage_qna_register?mode=add_qna&sno=0"><input class="btn btn-primary btn_write_n01" type="button" id="join_btn"  value="글쓰기" /></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form name="frmList" class="frmList">
	<input type="hidden" name="pageNo" id="pageNo" value=1>
	</form>


<script language="javascript">
function goPage(page){
	$('#pageNo').val(page);
	$('.frmList').submit();
}


function popup_register( mode, sno ){
	if ( mode == 'del_qna' ){
		var win = window.open("../mypage/mypage_qna_del_popup?mode=" + mode + "&sno=" + sno,"qna_register","width=400,height=160");
	}else{
		var win = window.open("../mypage/popup_mypage_qna_register?mode=" + mode + "&sno=" + sno,"qna_register","width=700,height=600");
	}
	win.focus();
}

function view_content(num){
	$('.detail').hide();
	$('#detail_'+num).show();
}
</script>

</div><!-- End indiv -->