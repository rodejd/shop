<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<script src='/resources/shop/lib/js/board.js'></script>
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/board/list.js"></script>
<%-- <script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/board/default.js"></script> --%>

<!-- <header class="page-header page-header-banner x-board-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">${boardVO.brdSetupObj.bdName}</h1>
        </div>
    </div>
</header> -->

<div class="top_tit_ty01">
	<div class="tit_dp01">USEFUL INFORMATION</div>
</div>

<div class="x-list container_service">
	<!-- 고객센터 공통탭메뉴 처리 -->
	<jsp:include page="../service/service_tab_menu.jsp" flush="true">
		<jsp:param name="tab_order" value="7" />
	</jsp:include>

	<div class="service_wrap01">
		<div class="service_stit01">공지사항</div>

		<div class="tabbable product-tabs">
			<div class="tab-content nb">
				<!-- 서브 컨텐츠 -->
				<div id="content-area" class="content-wrap">
					<!-- 컨텐츠 -->
					<div class="content">
						
						<c:if test="${(boardVO.pageNo eq '1') or (boardVO.brdSetupObj.bdNoticeList eq 'o')}">
						<c:forEach var="list" items="${boardVO.defaultBdNotiList }" varStatus="status">
						<article class="product-review">
							<div class="product-review-author">공지</div>
							<div class="list_notice_n01">
								<h5 class="product-review-title">
									<c:if test="${ boardVO.brdSetupObj.bdUseSubSpeech != '' }">[${list.category}] </c:if>
									<c:choose>
									<c:when test="${(level ne '99') and (list.secret eq 'o')}"><a href="#nav-dialog" data-effect="mfp-move-from-top" class="popup-text" onclick="$('#rtlno').val('${list.no}');"></c:when>
									<c:otherwise><a href="view?id=${boardVO.id}&no=${list.no}&pageNo=${boardVO.pageNo}&mode=${boardVO.mode}&searchMode=${searchMode}&searchWord=${boardVO.searchWord}"></c:otherwise>
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
									<c:if test="${boardVO.brdSetupObj.bdUseComment ne '' and list.comment > 0}"> (${list.comment})</c:if>
									<c:if test="${list.secret eq 'o'}"><i class="fa fa-key" style="margin-left:10px;"></i></c:if>
									<c:if test="${(boardVO.brdSetupObj.bdHot ne '') and (list.hit > boardVO.brdSetupObj.bdHot)}"><i class="fa fa-heart" style="margin-left:10px;"></i></c:if>
									<c:if test="${(boardVO.brdSetupObj.bdNew ne null) and (boardVO.brdSetupObj.bdNew > ((dateUtil:getIntTimeOver(list.regdt) < 0) ? -dateUtil:getIntTimeOver(list.regdt) : dateUtil:getIntTimeOver(list.regdt)) )}"><i class="fa fa-pencil-square-o"></i></c:if>
									<c:if test="${list.newfile ne ''}"><i class="fa fa-file-text"></i></c:if>
								</h5>
								<ul class="list-inline product-review-actions add_cla01">
									<li>
										<a href="#"><i class="fa fa-user"></i> ${list.name}</a>
									</li>
									<li>
										<a href="#"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${list.regdt}" pattern="yyyy.MM.dd HH:mm"/></a>
									</li>
									<li>
										<a href="#"><i class="fa fa-hand-o-up"></i> ${list.hit}</a>
									</li>
								</ul>
							</div>
						</article>
						</c:forEach>
						</c:if>
						
						<c:forEach var="list" items="${boardVO.defaultBdList }" varStatus="status">
						<article class="product-review add_cla02">
							<div class="in_bx">
								<div class="product-review-author">${(boardVO.rowCount - status.index) - ( boardVO.rowStart ) }</div>
								<h5 class="product-review-title">
									<c:if test="${ boardVO.brdSetupObj.bdUseSubSpeech != ''}">[${list.category}] </c:if>
									<c:choose>
									<c:when test="${(level ne '99') and (list.secret eq 'o')}"><a href="#nav-dialog" data-effect="mfp-move-from-top" class="popup-text" onclick="$('#rtlno').val('${ list.no}');"></c:when>
									<c:otherwise><a href="view?id=${boardVO.id}&no=${list.no}&pageNo=${boardVO.pageNo}&mode=${boardVO.mode}&searchMode=${boardVO.searchMode}&searchWord=${boardVO.searchWord}" style="color:#2b2b2b"></c:otherwise>
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
									<c:if test="${boardVO.brdSetupObj.bdUseComment ne '' and list.comment > 0}"> (${list.comment})</c:if>
									<c:if test="${list.secret eq 'o'}"><i class="fa fa-key" style="margin-left:10px;"></i></c:if>
									<c:if test="${(boardVO.brdSetupObj.bdHot ne '') and (list.hit > boardVO.brdSetupObj.bdHot)}"><i class="fa fa-heart" style="margin-left:10px;"></i></c:if>
									<c:if test="${(boardVO.brdSetupObj.bdNew ne null) and (boardVO.brdSetupObj.bdNew > ((dateUtil:getIntTimeOver(list.regdt) < 0) ? -dateUtil:getIntTimeOver(list.regdt) : dateUtil:getIntTimeOver(list.regdt)) )}"><i class="fa fa-pencil-square-o"></i></c:if>
									<c:if test="${boardVO.brdSetupObj.bdUseFile eq 'on'}">
									<c:if test="${list.newfile ne ''}"><i class="fa fa-file-text"></i></c:if>
									</c:if>
								</h5>
								<div class="date_n"><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></div>
								<!-- <ul class="list-inline product-review-actions" style="margin-left:50px;margin-top:20px;">
									<li><a href="#"><i class="fa fa-user"></i> ${list.name}</a></li>
									<li><a href="#"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${list.regdt}" pattern="yyyy.MM.dd HH:mm"/></a></li> 
									<li><a href="#"><i class="fa fa-hand-o-up"></i> ${list.hit}</a></li> 
								</ul> -->
							</div>
						</article>
						</c:forEach>
						<!-- paging -->
						<%-- <c:if test="${getM_iTotalItems > 0}"> --%>
						<c:if test="${boardVO.totalCount > 0}">
						
						<nav class="text-center pt_tp_01">
							<ul class="pagination category-pagination">
								<tags:frontPaginator currentPageNo="${boardVO.pageNo}" rowCount="${boardVO.rowCount}" pageSize="${boardVO.pageSize}"  pageGroupSize="${boardVO.pageGroupSize}" />
							</ul>
						</nav>
						</c:if>

<style>
input::placeholder {color:#b2b2b2;}

/* input */
.login_wrap01 input.inp_ty01::placeholder {font-family: 'NSKL'; color:#a0a0a0;}

input.ck_ty02 {width:22px; height:22px; margin:4px 8px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_off.png') no-repeat left top; background-size:22px 22px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_ty02:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_on.png') no-repeat left top; background-size:22px 22px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}

input.ck_rad01 {width:20px; height:20px; margin:4px 8px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_rad01_off.png') no-repeat left top; background-size:20px 20px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_rad01:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_rad01_on.png') no-repeat left top; background-size:20px 20px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
</style>

						<!-- 게시글 검색 -->
						<form action="" id="frmList" class="frmList">
						<input type="hidden" name="mode" id="mode" value="${boardVO.mode }"/>
						<input type="hidden" name="id" value="${boardVO.id }"/>
						<input type="hidden" class="pageNo" name="pageNo" value="${boardVO.pageNo != null ? boardVO.pageNo : 1 }" />
							<div class="sub-sch-area ty_n01">
								<!-- <p class="count-txt">
									<span class="i-list-count">총 <strong class="txt-dark-gray under-line">${boardVO.totalCount}건</strong> 의 게시물이 있습니다.</span>
								</p> -->
								<div class="rel">
								<ul class="category-selections clearfix" style="position:relative;">
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
										<select class="sel_s_ty01" id="searchMode" name="searchMode">
											<option value="subject" ${('subject' eq boardVO.searchMode)? 'selected': ''}>제목</option>
											<option value="contents" ${('contents' eq boardVO.searchMode)? 'selected': ''}>내용</option>
											<option value="all" ${('all' eq boardVO.searchMode)? 'selected': ''}>제목+내용</option>
										</select>
										<!-- <p class="" style="float:left; padding:0 35px 0 0; margin:0;"><input type="checkbox" class="ck_ty02" id="ty01"><label for="ty01" style="font-size:15px; color:#424242; line-height:30px; padding:0 0 0 14px; vertical-align:top;">제목</label></p>
										<p class="" style="float:left;  padding:0 35px 0 0; margin:0;"><input type="checkbox" class="ck_ty02" id="ty02"><label for="ty02" style="font-size:15px; color:#424242; line-height:30px; padding:0 0 0 14px;  vertical-align:top;">내용</label></p> -->
									</li>
									<li class="m-input-box">
										<span class="data-div-wrap inline-block w205 ml5">
											<span class="data-box">
												<input class="inp_s_ty01" type="text" id="search" name="searchWord" placeholder="검색" title="검색" value="${ boardVO.searchWord }" />
											</span>
										</span>
									</li>
									<li>
										<button type="button" class="btn_search_pg01" onclick="fn_submit()"><span>검색</span></button>
									</li>
									<%-- 
									<c:if test="${boardVO.brdSetupObj.bdLvlW eq '' or (boardVO.level >= boardVO.brdSetupObj.bdLvlW)}">
									<li>
										<a href="write?id=${boardVO.id }&from=${boardVO.mode }&mode=write" class="btn btn-primary">글쓰기</a>
									</li>
									</c:if>
									--%>
								</ul>
								</div>
							</div>
						</fieldset>
						</form>

					</div>
				</div>  		
			</div>
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