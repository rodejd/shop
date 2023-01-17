<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : eventEndList.jsp
* 생성일 : 2017. 02. 21
* 작성자 : PMG
* 설   명 : skin default1 사용자 eventEndList
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
	/* 2017-09-19 수정 */
	$('.btn-pop-winner').on('click', function() {
		//딤 팝업 display 막기
		$('.dim-bg-wrap').css('display', 'none');
	});
	$('.btn-layer-close').on('click', function() {
		// close 선택 시 popup 닫기
		$('.layer-pop').css('display', 'none');
	});
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
	<input type=hidden name="pageNo" id="pageNo" value="${frontEventVO.pageNo }">
	<input type=hidden name="pageSize" value="${frontEventVO.pageSize }">

<div class="x-event-end-list container">
	<div class="tabbable product-tabs">
		<!-- 이벤트탭메뉴 공통처리 -->
		<jsp:include page="event_tab_menu.jsp" flush="true" >
			<jsp:param name="tab_order" value="2"/>
		</jsp:include>
		
		<div class="tab-content nb">
			<div class="tab-pane fade in active">
				<div class="row">
					<div class="col-md-12"><i class="fa fa-play"></i> 총 <strong class="txt-dark-gray under-line">${frontEventVO.rowCount }건</strong> 의 종료된 이벤트가 있습니다.</div>
				</div>
				
				<div class="gap"></div>
				
				<c:if test="${fn:length(frontEventVO.frontEventEndList) == 0}">
				<div class="row">
					<div class="row" data-gutter="15">
		                <div class="col-md-12 text-center">
		                    <div>
		                        <p>종료된 이벤트가 존재하지 않습니다.</p>
		                    </div>
		                </div>
		            </div>
				</div>
				</c:if>
				
				<c:if test="${fn:length(frontEventVO.frontEventEndList) > 0}">
				<div class="row">
					
					<c:forEach var="rtList" items="${frontEventVO.frontEventEndList }"  varStatus="status">
					<%-- <c:if test="${rtList.wview eq 'y' }"> --%>
					
					<div class="row event_list" data-gutter="15">
		                <div class="col-md-12 text-center">
		                	<div class="banner banner-o-hid">
			                	<a href="eventEndDetail?sno=${rtList.sno}">
									<img src="/resources/shop/data/upload/eventimg/${rtList.attach}" alt="${rtList.subject}" style="width:100%;height:100%;"/>
									<!-- <span class="event-end-bg">종료</span> -->
								</a>
								<c:if test="${rtList.win != ''}">
								<a href="#" class="btn-pop-winner" id="${rtList.sno}">
									<img src="/images/btn_event_win.png" alt="당첨자 발표" />
									<div id="pop_${rtList.sno}" style="display:none" name="pop_win">${rtList.win}</div>
								</a>
								</c:if>
								<!-- 당첨자 발표 되는 이벤트 시 -->
							</div>
								
		                </div>
		            </div>
						
					<%-- </c:if> --%>
					</c:forEach>
					
					<!-- paging -->
					<div class="col-md-12 text-center">
						<nav>
							<ul class="pagination category-pagination">
								<tags:frontPaginator currentPageNo="${frontEventVO.pageNo}" rowCount="${frontEventVO.rowCount}" pageSize="${frontEventVO.pageSize}"  pageGroupSize="${frontEventVO.pageGroupSize}" />
							</ul>
						</nav>
					</div>
				
				</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
</form>

<!-- 이벤트 당첨자 발표 레이어 팝업 -->
				
<div class="layer-pop pop-event-winner" style="position:fixed; top:160px; left:50%; width:450px; margin-left:-225px; padding:20px;">
<div class="pop-tit-wrap1">
<h1>당첨자 발표!!</h1>
<a href="#" class="btn-layer-close">닫기</a>
</div>
<div class="pop-content">

</div>
</div>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



