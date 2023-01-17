<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%--  <%@ include file="/common/common.jsp" %>
<%@ include file="/shop/conf/config.jsp"%>
<%@ include file="/shop/common/inc/jspResource.jsp" %> 

<%@ include file="/common/try.jsp"%>
<%@ include file="/shop/admin/common/LibFunction.jsp" %>
<%@ include file="/shop/common/header.jsp"%>
<%@ include file="/shop/common/left.jsp"%> 
<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script language="javascript">
var preContent;

function goPage(page){
	$('#pageNo').val(page);
	document.frmList.submit();
}

function view_content(obj)
{
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

{ // 초기출력
	var no = "faq_${faq_ssno}<%//= requestSet.getProperty("ssno", "") %>";
	if ( document.getElementById( no ) ) view_content( document.getElementById( no ) );
}

// search form submit function(검색 폼 전달 함수)
function formSubmit(){
	document.getElementsByName("frmList")[0].submit();
}

$(document).ready(function() {
	$('.product-page-qa-question').on('click', function() {
		$(this).parent().parent().find(".product-page-qa-answer").css('display', 'none');
		$('#a' + $(this).attr('dataIndex')).css('display', 'block');
		/*$('#a' + $(this).attr('dataIndex')).toggle(function() {
			$('#a' + $(this).attr('dataIndex')).css('display', 'block');
		}, function() {
			$('#a' + $(this).attr('dataIndex')).css('display', 'none');
		})*/
	});
});
</script>

<%-- <jsp:include page="<%=contents%>" flush="true">
	<jsp:param name="skin" value="<%=wskin%>" />
</jsp:include> --%>




<!-- <header class="page-header page-header-banner x-service-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h1 class="page-title">FAQ</h1>
		</div>
	</div>
</header> -->

<div class="top_tit_ty01">
	<div class="tit_dp01">USEFUL INFORMATION</div>
</div>

<div class="x-faq container container_service">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="service_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="1" />
		</jsp:include>

		<div class="service_wrap01">
			<div class="service_stit01">FAQ</div>
			
			<div class="faq_tab">
				<c:if test="${ !empty(codeList) }">
					<ul>
						<c:forEach items="${codeList}" var="codeList" varStatus="status">
							<li style="display:inline-block;"><a href="faq?sitemcd=${codeList.itemcd}">${codeList.itemnm}</a></li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="tab-content nb">
				<!-- 서브 컨텐츠 -->
				<div id="content-area" class="content-wrap">
					<!-- 컨텐츠 -->
					<div class="content">
	
						<!-- 검색 
						<form name=frmList id="form" class="product-page-qa-form" action="${ctx }/shop/service/faq">
							<div class="row" data-gutter="10">
								<div class="col-md-10">
									<div class="form-group">
										<input class="form-control" id="sword" name="sword" type="text" placeholder="검색어 입력" value="${faqVO.sword}" />
									</div>
								</div>
								<div class="col-md-2">
									<input class="btn btn-primary btn-block" type="submit" onsubmit="formSubmit();" value="검색" />
								</div>
							</div>
							<input type="hidden" id="pageNo" name="pageNo" value="1">
							<input type="hidden" name="sitemcd" value="${faqVO.sitemcd}">
							<input type="hidden" name="cname" value="${faqVO.cname}">							
						</form>
					
						<table class="table table table-shopping-cart table-faq">
							<caption class="caption-faq">자주하는 질문 분류입니다.</caption>
							<colgroup>
								<col style="width: 120px;" />
								<col style="width: 130px;" />
								<col style="width: 124px;" />
								<col style="width: 124px;" />
								<col style="width: 124px;" />
								<col style="width: 130px;" />
								<col />
							</colgroup>
							<thead>
								<c:if test="${ !empty(codeList) }">
									<tr>
										<c:forEach items="${codeList}" var="codeList" varStatus="status">
											<th><a href="faq?sitemcd=${codeList.itemcd}">${codeList.itemnm}</a></th>
										</c:forEach>
									</tr>
								</c:if>
							</thead>
							<tbody>
								<c:if test="${ !empty(codeList) }">
									<tr>
										<c:forEach items="${codeList}" var="codeList" varStatus="status">
											<td>
												<c:forEach items="${faqCategory }" var="faqCategory" varStatus="status">
													<c:if test="${codeList.itemcd == faqCategory.groupcd }">
														<a href="?cname=${ faqCategory.itemnm }">${ faqCategory.itemnm }</a>
														<br />
													</c:if>
												</c:forEach>
											</td>
										</c:forEach>
									</tr>
								</c:if>
							</tbody>
						</table> -->

						<div class="faq_list_n01">
							<c:if test="${ !empty(faqList) }">
								<c:forEach items="${faqList}" var="faqList" varStatus="status">
									<article class="product-page-qa q_list">
										<div class="product-page-qa-question" dataIndex="${status.index}">
											<div class="in_bx">
												<p class="product-page-qa-text">
													<!-- ${(faqVO.rowCount - status.index) - (faqVO.rowStart) } &nbsp; -->
													[${codeUtil:getCodeName('faq', faqList.itemcd) }]
													<c:choose>
														<c:when test="${fn:length(faqList.question) > 100  }">
															<c:out value="${fn:substring(faqList.question, 0, 100)}" />...
														</c:when>
														<c:otherwise>
															<c:out value="${faqList.question}" />
														</c:otherwise>
													</c:choose>
												</p>
											</div>
										</div>
										<div class="product-page-qa-answer" id="a${status.index}">
											<p class="product-page-qa-text;">
												<c:if test="${faqList.descant != '' }">
													${stringUtil:replaceAll(faqList.descant, "\\n", "<br>") }
													<br>
													<br>
													<hr />
													<br>
													<br>
												</c:if>
												${stringUtil:replaceAll(faqList.answer, "\\n", "<br>") }
											</p>
										</div>
									</article>
								</c:forEach>
							</c:if>
							<c:if test="${ empty(faqList) }">
								<article class="product-page-qa">
									<p class="product-page-qa-text">검색결과가 없습니다. 다시 검색하여 주세요.</p>
								</article>
							</c:if>
						</div>
						<!-- 페이징 -->
						<div class="text-center">
							<nav>
								<ul class="pagination category-pagination">
									<tags:frontPaginator currentPageNo="${faqVO.pageNo}" rowCount="${faqVO.rowCount}" pageSize="${faqVO.pageSize}"  pageGroupSize="${faqVO.pageGroupSize}" />

								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<div class="gap gap-small"></div>