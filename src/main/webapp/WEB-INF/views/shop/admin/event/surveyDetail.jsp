<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

<script type="text/javascript">
/*
 * jQuery ready
 */
 </script>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left:12px">
	
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>


	<div class="title title_top">설문조사현황<span>사용자가 설문조사한 상태를 확인할 수 있습니다.</span></div>
	<div class="title title_top" align=center>${surveyVO.detailSurveyVO.surveyTitle}<span>${surveyVO.detailSurveyVO.sdate } ~ ${surveyVO.detailSurveyVO.edate }</span></div>
	<table width="100%" class="gage_table">
		<colgroup>
			<col style="width:15%">
			<col>
		</colgroup>
		<c:forEach items="${surveyVO.detailSurveyVO.questions}" var="list" varStatus="status">
			<tr>
				<th>${list.surveySubName}</th>
				<td>
					<div class="pp">
						<div class="grp">
							<span class="gage"></span>
							<p><span class="person" >${list.surveyCount}</span>명 /  <span class="per"></span></p>
						</div>
						
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>


	<p>결과 : <span class="total">${surveyVO.detailSurveyVO.sumSurveyCnt}</span> / 100%</p>
	<p align="center"><a href="${ctx}/shop/admin/event/surveyList"><img src="/resources/shop/admin/img/btn_list.gif"></a></p>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>

	<script>
	 	//설문조사 게이지 
		$(function(){
			var total = $('.total').text();
			$('.pp').each(function(){
				var findPerson = $(this).find('.person').text();
				var per = (findPerson / total) * 100;
				if(findPerson == 0){
					per = 0;
				}
				$(this).find('.gage').css('width', per + '%');
				$(this).find('.per').text( per.toFixed(1) + '%');
			})
		})
	</script>

