<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : eventList.jsp
* 생성일 : 2017. 02. 21
* 작성자 : PMG
* 설   명 : skin default1 사용자 eventList
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170221			PMG				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<style type="text/css">
.btn-pop-winner img {width:auto; height:auto;}
.btn-pop-winner {display:block; position:absolute; top:50px; left:50%; margin-left:-50px; z-index:2;}
.layer-pop {display:none; background-color:#fff; z-index:6;}
.layer-pop .pop-tit-wrap1 {position:relative;border-bottom:4px solid #393939; padding-bottom:20px;}
.layer-pop .pop-tit-wrap1 h1 {font-size:1.3em;}
.layer-pop .pop-tit-wrap1 > p {color:#9c9c9c; padding-top:10px;}
.layer-pop .btn-layer-close{position:absolute; top:3px; right:0; display:inline-block; width:17px; height:17px; text-indent:-9999px; background:url('../../images/btn_layer_close.png') no-repeat center center; overflow:hidden;}
.layer-pop .pop-content {padding-top:20px; overflow:hidden;}
.layer-pop .pop-content img {max-width:100%;}

.btn-pop-winner1 img {width:auto; height:auto;}
.btn-pop-winner1 {display:block; position:absolute; top:10%; left:80%; margin-left:-50px; z-index:2;}
</style>
<script type="text/javascript">
$(function(){

});
</script>

<script language="javascript">

function goPage(page){
	$('#pageNo').val(page);
	document.frmList.submit();
}
</script>

<header class="page-header page-header-banner x-service-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">EVENT</h1>
        </div>
    </div>
</header>

<form name=frmList method="post">
	<input type=hidden name="pageNo" id="pageNo" value="${surveyVO.pageNo }">
	<input type=hidden name="pageSize" value="${surveyVO.pageSize }">

<div class="x-event-list container">
	<div class="tabbable product-tabs">
		<!-- 이벤트탭메뉴 공통처리 -->
		<jsp:include page="event_tab_menu.jsp" flush="true" >
			<jsp:param name="tab_order" value="3"/>
		</jsp:include>
		
		<div class="tab-content nb">
			<div class="tab-pane fade in active">
				<div class="row">
					<div class="col-md-12"><i class="fa fa-play"></i> 총 <strong class="txt-dark-gray under-line">${surveyVO.rowCount }건</strong> 의 진행중인 설문조사가 있습니다.</div>
				</div>
				
				<div class="gap"></div>
				
				<c:if test="${fn:length(surveyVO.surveyList) == 0}">
					<div class="row">
						<div class="row" data-gutter="15">
			                <div class="col-md-12 text-center">
			                    <div>
			                        <p>진행중인 설문조사가 존재하지 않습니다.</p>
			                    </div>
			                </div>
			            </div>
					</div>
				</c:if>
				
				<c:if test="${fn:length(surveyVO.surveyList) > 0}">
				<div class="row">
					<c:forEach var="rtList" items="${surveyVO.surveyList }"  varStatus="status">
					<div class="row event_list" data-gutter="15">
		                <div class="col-md-12 text-center">
		                    <div class="banner banner-o-hid">
		                        <a href="surveyDetail?sno=${rtList.surveySno}">
									${rtList.surveyTitle}
									<!-- <span class="event-end-bg">종료</span> -->
								</a>
		                    </div>
		                    
		                </div>
		            </div>
					
					</c:forEach>
					</c:if>
					<!-- paging -->
					<div class="col-md-12 text-center">
						<nav>
							<ul class="pagination category-pagination">
								<tags:frontPaginator currentPageNo="${surveyVO.pageNo}" rowCount="${surveyVO.rowCount}" pageSize="${surveyVO.pageSize}"  pageGroupSize="${surveyVO.pageGroupSize}" />
							</ul>
						</nav>
					</div>
				
				</div>
				
				
			</div>
			
		</div>
	</div>
</div>
</form>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



