<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<script src='/resources/shop/lib/js/board.js'></script>
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/board/list.js"></script>
<%-- <script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/board/gallery.js"></script> --%>

<header class="page-header page-header-banner x-board-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">${boardVO.brdSetupObj.bdName}</h1>
        </div>
    </div>
</header>

<div class="container x-list-gal">
	<div class="product-tabs" style="margin-top:20px;">
	<div class="tab-content nb">
    <header class="page-header" style="margin-top:10px;margin-bottom:0px;">
        <span class="i-list-count">
        	총 <strong class="txt-dark-gray under-line">${boardVO.totalCount }건</strong> 의 게시물이 있습니다.
        </span>                                                                    
        <form name="frmList" id="frmList" >
		<input type="hidden" name="id" id="id" value="${boardVO.id }">
		<input type="hidden" name="pageViewNum_" id="pageViewNum_" value="${pageViewNum }">
		<input type="hidden" name="page_totalCnt_" id="page_totalCnt_" value="${page_totalCnt }">
		<input type="hidden" name="from" id="from" value="${boardVOmode }">
		<input type="hidden" class="pageNo" name="pageNo" value="${boardVO.pageNo != null ? boardVO.pageNo : 1 }" />
        <ul class="category-selections clearfix" style="margin-bottom:30px;">
			<c:if test="${boardVO.brdSetupObj.bdUseSubSpeech ne ''}">
			<c:set var="crlf" value="" />
			<c:set var="arr" value="${fn:split(boardVO.brdSetupObj.bdSubSpeech, crlf ) }"/>
				<li>
				<select class="category-selections-select" name="subSpeech" onChange="this.form.submit();">
					<option value='all'>${boardVO.brdSetupObj.bdSubSpeechTitle }</option>
					<c:forEach var="arr" items="${arr }" >
						<option value="${arr }" ${boardVO.subSpeech== arr ? 'selected' : '' }>${arr }</option>
					</c:forEach>
				</select>
				</li>
			</c:if>
            
            <li>
            	<span class="category-selections-sign"></span>
                <select class="category-selections-select" id="searchMode" name="searchMode" style="height:36px;">
                    <option value="subject" ${('subject' eq boardVO.searchMode)? 'selected': ''}>제목</option>
					<option value="contents" ${('contents' eq boardVO.searchMode)? 'selected': ''}>내용</option>
					<option value="all" ${('all' eq boardVO.searchMode)? 'selected': ''}>제목+내용</option>
                </select>
            </li>
            <li class="m-input-box">
            	<input class="form-control" type="text" id="searchWord" name="searchWord" value="${ boardVO.searchWord }" placeholder="검색어를 입력하세요." title="검색어 입력" />
            </li>
            <li>
            	<button type="button" class="btn btn-primary" onclick="fn_submit()"><span>검색</span></button>
            </li>
			<c:if test="${boardVO.brdSetupObj.bdLvlW eq '' or (boardVO.level >= boardVO.brdSetupObj.bdLvlW)}">
			<li>
				<a href="${ctx }/shop/board/write?id=${boardVO.id }&mode=write&from=${mode}" class="btn btn-primary">글쓰기</a>
			</li>
			</c:if>
        </ul>
        </form>
    </header>
    
    </div></div>
    
    <c:if test="${not empty boardVO.boardList }">
    
	    <c:forEach var="list" items="${boardVO.boardList }" varStatus="status">
	    
 	    <c:if test="${(status.index%4) eq '0'}">
	    	<div class="row" data-gutter="15">
		</c:if>
	        <div class="col-md-3">
	            <div class="product ">
	                <div class="product-img-wrap" style="height:auto">
	                    <img class="product-img-primary" style="display: block;max-width: 100%; height: auto;" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/500x500.png';" class="product-img-primary2" src="/resources/shop/data/upload/gd_bd_${boardVO.id }/${list.newfile}" alt="Image Alternative text" title="Image Title" />
	                </div>
	                <a class="product-link" href="${ctx }/shop/board/view?id=${boardVO.id }&no=${list.lno }&mode=${boardVO.mode}&searchMode=${boardVO.searchMode}&searchWord=${boardVO.searchWord}"></a>
	            	<div class="product-caption">
	                    <h5 class="product-caption-title" style="height:auto;">
	                    	<c:if test="${boardVO.brdSetupObj.bdUseSubSpeech ne ''}">[${list.category}] </c:if>
	                    	<c:choose>
								<c:when test="${fn:length(list.subject) > 20 }">
									${fn:substring(list.subject ,0 , 20)}...
								</c:when>
								<c:otherwise>
									${list.subject}
								</c:otherwise>
							</c:choose>
							<c:if test="${boardVO.brdSetupObj.bdUseComment ne '' and list.comment > 0}"> (${list.comment})</c:if>
	                    </h5>
	                </div>
	            </div>
	        </div>
	    <c:if test="${(status.index%4) eq '3' }">
	    	</div>
	    </c:if>
<%-- 	    <c:if test="${(status.index%4) eq '3' or status.index eq (fn:length(list)-1)}">
	    	</div>
	    </c:if> --%>
	    </c:forEach>
	    </div>
    </c:if>

</div>
<c:if test="${boardVO.totalCount > 0}">
<div>
	<nav class="text-center">
	    <ul class="pagination category-pagination">
        	<tags:frontPaginator currentPageNo="${boardVO.pageNo}" rowCount="${boardVO.rowCount}" pageSize="${boardVO.pageSize}"  pageGroupSize="${boardVO.pageGroupSize}" />
	    </ul>
	</nav>
</div>

</c:if>

<input type="hidden" name="bdLvlL" value="${boardVO.brdSetupObj.bdLvlL}"> 
<input type="hidden" name="level" value="${'' eq boardVO.level ? 0 : boardVO.level}">

<script language="javascript">

$(function(){
	var settingBoardLevel = $('[name=bdLvlL]').val();		//관리자 설정 게시판 레벨
	var userLevel = $('[name=level]').val();	//사용자 레벨
	
	if (0 != settingBoardLevel & userLevel < settingBoardLevel) {
		history.go(-1);
		alert("접근 권한이 없습니다");
	}
	
	/* 2017-09-19 이미지 리사이즈 추가 */
	$('.product-img-primary').on('load', function() {
		$('.product-img-primary').each(function() {
			var width = 	$(this).width();
			$(this).height(width);
		});
	});
});

/* 2017-09-19 height 정렬 추가 */
$(window).load(function(){
	var maxHeight = -1;
	$('.product').each(function(){
		maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
	});
	$('.product').height(maxHeight);
	
});

$(window).resize(function (){
	var maxHeight = -1;
	$('.product').css('height', 'auto');
	$('.product').each(function(){
		maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
	});
	$('.product').height(maxHeight);
})

function goPage(page){
	$('.pageNo').val(page);
	$('#frmList').submit();
}

</script>