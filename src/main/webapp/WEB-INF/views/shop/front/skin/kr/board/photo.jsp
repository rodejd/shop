<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 
    
<header class="page-header page-header-banner">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">${boardVO.brdSetupObj.bdName}</h1>
        </div>
    </div>
</header>

<div class="x-list-photo container">
	<div class="tabbable product-tabs">
	    <div class="tab-content nb">
	        <div class="row">
	            <div class="col-md-12">
	                <!-- 게시글 검색 -->
					<form action="" id="frmList" class="frmList">
					<input type="hidden" name="mode" id="mode" value="${boardVO.mode }"/>
					<input type="hidden" name="id" value="${boardVO.id }"/>
					<input type="hidden" class="pageNo" name="pageNo" value="${boardVO.pageNo != null ? boardVO.pageNo : 1 }" />
					<fieldset id="" class="clear-default mt40">
						<div class="sub-sch-area">
							<p class="count-txt">
								<span class="i-list-count">총 <strong class="txt-dark-gray under-line">${boardVO.totalCount}건</strong> 의 게시물이 있습니다.</span>
							</p>
							<div class="rel">
							<ul class="category-selections clearfix">
							<c:if test="${boardVO.brdSetupObj.bdUseSubSpeech ne ''}">
							<c:set var="crlf" value="
							" />
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
			                        <select class="category-selections-select" id="searchMode" name="searchMode" style="height:36px;">
			                            <option value="subject" ${('subject' eq boardVO.searchMode)? 'selected': ''}>제목</option>
										<option value="contents" ${('contents' eq boardVO.searchMode)? 'selected': ''}>내용</option>
										<option value="all" ${('all' eq boardVO.searchMode)? 'selected': ''}>제목+내용</option>
			                        </select>
			                    </li>
			                    <li class="m-input-box">
			                    	<span class="data-div-wrap inline-block w205 ml5">
										<span class="data-box">
											<input class="form-control" type="text" id="search" name="searchWord" placeholder="검색어를 입력하세요." title="검색어 입력" value="${ boardVO.searchWord }"/>
										</span>
									</span>
			                    </li>
			                    <li>
			                    	<button type="button" class="btn btn-primary" onclick="fn_submit()"><span>검색</span></button>
			                    </li>
			                    <c:if test="${boardVO.brdSetupObj.bdLvlW eq '' or (boardVO.level >= boardVO.brdSetupObj.bdLvlW)}">
			                    <li>
									<a href="write?id=${boardVO.id }&from=${boardVO.mode }&mode=write" class="btn btn-primary">글쓰기</a>
			                    </li>
								</c:if>
			                </ul>
							</div>
						</div>
					</fieldset>
					</form>
					
					<hr />
					
					<c:if test="${(boardVO.pageNo eq '1') or (boardVO.brdSetupObj.bdNoticeList eq 'o')}">
					<c:forEach var="list" items="${boardVO.defaultBdNotiList }" varStatus="status">
	                <ul class="product-accessory-list">
	                    <li class="product-accessory">
	                        <div class="row">
	                            <div class="col-md-2">
                                    <img class="product-accessory-img" src="/resources/shop/data/upload/gd_bd_${boardVO.id}/${list.newfile}" alt="Image Alternative text" title="Image Title" />
	                            </div>
	                            <div class="col-md-7">
	                            	<p class="product-review-meta"><fmt:formatDate value="${list.regdt}" pattern="yyyy.MM.dd HH:mm"/></p>
	                                <h5 class="product-accessory-title">
	                                	<c:choose>
						                <c:when test="${list.secret eq 'o'}"><a href="#nav-dialog" data-effect="mfp-move-from-top" class="popup-text" onclick="$('#rtlno').val('${list.no}');"></c:when>
						                <c:otherwise><a href="view?id=${boardVO.id}&no=${list.no}&pageNo=${boardVO.pageNo}&mode=${boardVO.mode}&searchMode=${boardVO.searchMode}&searchWord=${boardVO.searchWord}"></c:otherwise>
						                </c:choose>
										<c:choose>
										<c:when test="${fn:length(list.subject) > 100  }">
											<c:out value="${fn:substring(list.subject, 0, 100)}"/>...
										</c:when>
										<c:otherwise>
											<c:out value="${list.subject}"/>
										</c:otherwise>
										</c:choose>
										</a>
										<c:if test="${list.secret eq 'o'}"><i class="fa fa-key" style="margin-left:10px;"></i></c:if>
										<c:if test="${(boardVO.brdSetupObj.bdHot ne '') and (list.hit > boardVO.brdSetupObj.bdHot)}"><i class="fa fa-heart" style="margin-left:10px;"></i></c:if>
										<c:if test="${boardVO.brdSetupObj.bdUseComment ne '' and list.comment > 0}"> (${list.comment})</c:if>
										<c:if test="${(boardVO.brdSetupObj.bdNew ne null) and (boardVO.brdSetupObj.bdNew > dateUtil:getIntTimeOver(list.regdt))}"><i class="fa fa-pencil-square-o"></i></c:if>
	                                </h5>
	                                <ul class="list-inline product-review-actions">
		                                <li><a href="#">작성자 : ${list.name}</a>
		                                </li>
		                                <li><a href="#">조회수 : ${list.hit}</a>
		                                </li>
		                            </ul>
	                            </div>
	                        </div>
	                    </li>
	                </ul>
	                </c:forEach>
					</c:if>
					
					<c:forEach var="list" items="${boardVO.defaultBdList }" varStatus="status">
					<ul class="product-accessory-list">
	                    <li class="product-accessory">
	                        <div class="row">
	                            <div class="col-md-2">
                                    <img class="product-accessory-img" src="/resources/shop/data/upload/gd_bd_${boardVO.id}/${list.newfile }" alt="Image Alternative text" title="Image Title" />
	                            </div>
	                            <div class="col-md-7">
	                            	<p class="product-review-meta"><fmt:formatDate value="${list.regdt}" pattern="yyyy.MM.dd HH:mm"/></p>
	                                <h5 class="product-accessory-title">
	                                	<c:if test="${boardVO.brdSetupObj.bdUseSubSpeech ne ''}">[${list.category}] </c:if>
	                                	<c:choose>
						                <c:when test="${list.secret eq 'o'}"><a href="#nav-dialog" data-effect="mfp-move-from-top" class="popup-text" onclick="$('#rtlno').val('${ list.no}');"></c:when>
						                <c:otherwise><a href="view?id=${boardVO.id}&no=${ list.no}&pageNo=${boardVO.pageNo}&mode=${boardVO.mode}&searchMode=${boardVO.searchMode}&searchWord=${boardVO.searchWord}"></c:otherwise>
						                </c:choose>
										<c:choose>
										<c:when test="${fn:length(list.subject) > 100  }">
											<c:out value="${fn:substring(list.subject, 0, 100)}"/>...
										</c:when>
										<c:otherwise>
											<c:out value="${list.subject}"/>
										</c:otherwise>
										</c:choose>
										</a>
										<c:if test="${list.secret eq 'o'}"><i class="fa fa-key" style="margin-left:10px;"></i></c:if>
										<c:if test="${(boardVO.brdSetupObj.bdHot ne '') and (list.hit > bdHot)}"><i class="fa fa-heart" style="margin-left:10px;"></i></c:if>
										<c:if test="${boardVO.brdSetupObj.bdUseComment ne '' and list.comment > 0}"> (${list.comment})</c:if>
										<c:if test="${(boardVO.brdSetupObj.bdNew ne null) and (boardVO.brdSetupObj.bdNew > dateUtil:getIntTimeOver(list.regdt))}"><i class="fa fa-pencil-square-o"></i></c:if>
	                                </h5>
	                                <ul class="list-inline product-review-actions">
		                                <li><a href="#">작성자 : ${list.name}</a>
		                                </li>
		                                <li><a href="#">조회수 : ${list.hit}</a>
		                                </li>
		                            </ul>
	                            </div>
	                        </div>
	                    </li>
	                </ul>
					</c:forEach>
						<!-- paging -->
					<c:if test="${boardVO.totalCount > 0}">
					<nav class="text-center">
						<ul class="pagination category-pagination">
							<tags:frontPaginator currentPageNo="${boardVO.pageNo}" rowCount="${boardVO.rowCount}" pageSize="${boardVO.pageSize}"  pageGroupSize="${boardVO.pageGroupSize}" />
						</ul>
					</nav>
					</c:if>
	            </div>
	        </div>
        </div>
    </div>
	<div class="gap"></div>
</div>

    <div class="mfp-with-anim mfp-hide mfp-dialog clearfix" id="nav-dialog">
        <div class="form-group">
            <label>비밀번호</label>
            <input class="form-control" type="password" name="confirmPassword" />
            <input type="hidden" name="rtlno" id="rtlno"/>
        </div>
        <input class="btn btn-primary" id="lPopup" type="button" onclick="doCheck('${boardVO.id}');" value="확인" />
        <div class="gap gap-small"></div>
    </div>
</div>

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
}); 

function goPage(page){
	$('.pageNo').val(page);
	$('.frmList').submit();
}

function doCheck(id) {
	if ($('input[name=confirmPassword]').val().trim() == '') {
		alert('비밀번호를 입력해 주세요');
		$('input[name=confirmPassword]').focus();
		
		return false;
	}
	
	var password =  $('input[name=confirmPassword]').val().trim();
	
	// 비밀번호 체크
	ajaxCallJson("/shop/board/cmt.dojson"
			, {	'id' : id,
				'no' : $('#rtlno').val(),
				'password' : password,
				'order' : 'checkPasswordBoard'}
			,function (data) {
				if (data.result == 1) {
					$.magnificPopup.close();
					location.href='view?id=' + id + '&no=' + $('#rtlno').val();
					
				} else {
					alert('비밀번호가 틀렸습니다');
					$('input[name=confirmPassword]').val('');
					$('input[name=confirmPassword]').focus();
					return false;
				}
			}
			, function(e){
				alert(e);
			});
	
}
</script>