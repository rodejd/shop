<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<link rel="styleSheet" href="/resources/shop/admin/style.css">
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
$(document).ready(function() {
	$("#surveyForm").submit(function(){
		
		//중복투표가능한경우에는 조건을 추가해야함
		
		if(confirm("투표후에는 수정 할 수 없습니다. 이대로 투표하시겠습니까?")){
			
		}
		
		if(! ${shop_so.isShopLogin()} ){
			alert("로그인 후에 이용하실 수 있습니다.")
			return false;
		}
		
		var obj = $("input[name=sNum]");
		$.each(obj, function (index, item){
			if(item.checked){
				$("input[type=hidden][name=surveyNumber]").val(item.value);
			}
		})
		var surveyNum = $("input[type=hidden][name=surveyNumber]").val();
		if(surveyNum == ""){
			alert("질문 체크 후 투표하기 버튼을 눌러주세요");
			return false;
		}
	
	});
	
});


function cmtDel(no){
	//document.getElementById("mode").value = "cmtDel";
	//document.getElementById("eno").value = no;
	//document.getElementById("fm01").submit();
	
	$('#mode').val("cmtDel");
	$('#sno').val(no);
	
	ajaxCallJsonPost("/shop/event/surveyIndb.dojson", "fm01", function(result){
		//alert(JSON.stringify(result));
		alert('선택하신 댓글이 삭제되었습니다.');
		location.href=ctx+"/shop/event/surveyDetail?sno="+result.sno;
	});
}

function cmtAdd(){
	alert("cmtAdd");
	if( $('#memo').val() == '' ){
		alert('댓글 내용을 작성하세요.');
		return;
	}
	
	$('#mode').val("cmtAdd");
	
	ajaxCallJsonPost("/shop/event/surveyIndb.dojson", "fm01", function(result){
		alert('작성하신 댓글이 등록되었습니다.');
		location.href=ctx+"/shop/event/surveyDetail?sno="+result.sno;
	});
}

function cmtMod(no){
	alert("no > " + no);
	if(document.getElementById("modCk").value==""){
		document.getElementById("mod1_"+no).style.display = "none";
		document.getElementById("mod2_"+no).style.display = "block";
		document.getElementById("modCk").value="cmtMod";
	}else{
		document.getElementById("mode").value = "cmtMod";
		ajaxCallJsonPost("/shop/event/surveyIndb.dojson", "fm01", function(result){
			alert('수정하신 댓글이 변경되었습니다.');
			location.href=ctx+"/shop/event/surveyDetail?sno="+result.sno;
		});
	}
}

function goPage(page){
	$('#pageNo').val(page);
	document.fm01.submit();
}

</script>
	<script>
		$(function(){

			var total = $('.total').text();

		setTimeout(function(){
				$('.pp').each(function(){
					var findPerson = $(this).find('.person').text();
					var per = (findPerson / total) * 100;
					if(findPerson == 0){
						per = 0;
					}
					$(this).find('.gage').css('width', per + '%');
					$(this).find('.per').text( per.toFixed(1) + '%');	
					
				})
		},1000)
		})
	</script>

<header class="page-header page-header-banner x-service-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">SURVEY</h1>
        </div>
    </div>
</header>

<div class="x-event-detail container">
	<div class="tabbable product-tabs">
		<!-- 이벤트탭메뉴 공통처리 -->
			<c:choose>
				<c:when test="${surveyMode eq 'endSurvey'}">
					<jsp:include page="event_tab_menu.jsp" flush="true" >
						<jsp:param name="tab_order" value="4"/>
					</jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="event_tab_menu.jsp" flush="true" >
						<jsp:param name="tab_order" value="3"/>
					</jsp:include>
				</c:otherwise>
			</c:choose>
			
		<form id="surveyForm" name="surveyForm" action="sureyIndb" method="post">
		<input type="hidden" name="mode" value=""/>
		<input type="hidden" name="surveyNumber" value=""/>
		<input type="hidden" name="surveySno" value="${surveyVO.surveySno}"/>
		<div class="tab-content nb">
			<div class="tab-pane fade in active">
				<div class="row">
	                <div class="col-md-8">
	                    <h5 class="product-accessory-price">${surveyVO.surveyTitle}</h5>
	                </div>
	                <div class="col-md-4">
	                    <p class="product-accessory-title">설문조사기간 :  ${surveyVO.sdate } ~ ${surveyVO.edate }</p>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-md-12">
						<table width="100%" class="gage_table">
							<colgroup>
								<col style="width: 15%">
								<col>
							</colgroup>
							<c:forEach items="${surveyVO.questions}" var="list" varStatus="status">
								<tr>
									<th>${list.surveySubName}
										<c:if test="${surveyMode eq 'detail'}">
											<span align="right"><input type="radio" name="sNum" id="sNum" value="${list.surveyNum}"/></span>
										</c:if>
									</th>
									<td>
										<div class="pp">
											<div class="grp">
												<span class="gage"></span>
												<p>
													&nbsp;<span class="person">${list.surveyCount}</span>명 / <span class="per"></span>
												</p>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
						<p>결과 : 총&nbsp;<span class="total">${surveyVO.sumSurveyCnt}</span> 명 / 100%</p>
					</div>
	            </div>
			</div>
			<div class="gap"></div>
			<c:choose>
				<c:when test="${surveyOnOff and surveyMode == 'detail'}">
					<div align="center"><input type="submit" value="투표하기" /></div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			</form>
			
            <hr />
            
            <h3 class="widget-title">총 <strong class="under-line">${surveyVO.rowCount } 건</strong> 의 comment 가 있습니다.</h3>
            
            <form action="surveyDetail" method="post" id="fm01" name="fm01">
            
            <c:if test="${fn:length(surveyVO.frontSurveyCommentList) > 0}">
            <!-- START COMMENTS -->
            <ul class="comments-list">
            	<c:forEach var="memoList" items="${surveyVO.frontSurveyCommentList }"  varStatus="status">
            	
                <li>
                    <article class="comment">
                        <div class="comment-author">
                        <%-- 이미지 추가해야 할 경우 테이블에 컬럼 추가 후 해야됌 --%>
 <%--                        	<c:choose>
                        		<c:when test="${not empty memoList.pic}">
									<img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/member/profile/${memoList.pic}" onerror='this.onerror=null;this.src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/user.gif";' />
								</c:when>
								<c:when test="${empty memoList.pic}">
									<img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/user.gif"' />
								</c:when>
								<c:otherwise>
									<img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/user.gif"' />
								</c:otherwise>
							</c:choose> --%>
							<img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/user.gif"' />
                        </div>
                        <div class="comment-inner" id="mod1_${memoList.sno}">
                        	<span class="comment-author-name">${memoList.id}</span>
                            <p class="comment-content">${memoList.memo}</p>
                            <span class="comment-time">${memoList.regdt}</span>
                            <c:if test="${ (shop_so.isShopLogin() && memoList.id eq shop_so.getUserInfo().userId) || ( shop_so.isShopLogin() && (shop_so.getUserInfo().getXkey().level >=80) ) }">
	                            <span class="comment-reply" onclick="javascript:cmtMod('${memoList.sno}');" style="cursor:pointer"><i class="fa fa-pencil-square"></i> Modify</span>&nbsp;
								<span class="comment-reply del" onclick="javascript:cmtDel('${memoList.sno}');"><i class="fa fa-minus-circle"></i> Delete</span>
	                        </c:if>
                        </div>
                        <div class="user-txt bg-light-gray" id="mod2_${memoList.sno}" style="display:none" >
							<span class="data-box">
								<textarea id="modifyMemo_${memoList.sno}" class="form-control" name="modifyMemo_${memoList.sno}" title="코멘트 입력">${memoList.memo}</textarea><br>
							</span>
							<span class="comment-time">${memoList.regdt}</span>
							<c:if test="${ (shop_so.isShopLogin() && memoList.id eq shop_so.getUserInfo().userId ) || ( shop_so.isShopLogin() && (shop_so.getUserInfo().getXkey().level >=80) ) }">
	                            <span style="cursor:pointer" class="comment-reply" onclick="javascript:cmtMod('${memoList.sno}');"><i class="fa fa-pencil-square"></i> Modify</span>&nbsp;
	                            <span style="color:#ff0000; cursor:pointer;" onclick="javascript:cmtDel('${memoList.sno}');"><i class="fa fa-minus-circle"></i> Delete</span>
                            </c:if>
						</div>
                    </article>
				</li>
				
				</c:forEach>
				 
				<!-- paging -->
				
				<nav class="text-center">
					<tags:frontPaginator currentPageNo="${surveyVO.pageNo}" rowCount="${surveyVO.rowCount}" pageSize="${surveyVO.pageSize}"  pageGroupSize="${surveyVO.pageGroupSize}" />
				</nav>
            </ul>
            </c:if>
            
            <input type="hidden" name="surveySno" id="surveySno" value="${surveyVO.surveySno}">
            <input type="hidden" name="pageNo" id="pageNo" value="${surveyVO.pageNo}">
            <input type="hidden" name="body1" id="body1" value="">
            <c:choose>
			<c:when test="${ shop_so.isShopLogin() }">
			<!-- 코멘트 쓰기 -->
			<input type="hidden" name="mode" id="mode" value="cmtIns">
			<input type="hidden" name="sno" id="sno" value="0">
			<input type="hidden" name="modCk" id="modCk" value="">
			
			
			<hr />
			<h3 class="widget-title">댓글 등록</h3>
			<div class="row">
                 <div class="col-md-12">
                     <div class="form-group profile">
                        <img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/member/profile/${shop_so.getUserInfo().getXkey().profile}" onerror='this.onerror=null;this.src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/user.gif";' />${shop_so.getUserInfo().userId}
                     </div>
                 </div>
             </div>
                
			<div class="form-group">
               	<label>Comment</label>
                <textarea id="memo" name="memo" class="form-control" placeholder="코멘트를 입력하세요." title="코멘트 입력"></textarea>
			</div>
			<div class="text-right">
				<a href="#" onclick="javascript:cmtAdd();">
					<input class="btn btn-primary" type="button" value="댓글 등록" />
				</a>
			</div>
			</c:when>
			
			<c:otherwise>
			<hr />
			<h3 class="widget-title">Leave a Comment</h3>
			<div class="form-group">
               	<label>Comment</label>
                <textarea id="" name="" class="form-control" placeholder="코멘트를 입력하려면 로그인해주세요." title="코멘트 입력" readonly="readonly"></textarea>
			</div>
			
			</c:otherwise>
			</c:choose>
				
			</form>
            
            <!-- END COMMENTS -->
            <div class="gap"></div>
            
            <div class="row text-center">
                 <div class="col-md-12">
                     <div>
                        <a class="btn btn-lg btn-primary" href="surveyList"><i class="fa fa-list"></i>목록으로</a>
                     </div>
                 </div>
             </div>
            
		</div>
		
	</div>
</div>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
