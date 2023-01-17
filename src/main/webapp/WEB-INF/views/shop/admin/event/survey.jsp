<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script language=javascript  src="/resources/shop/admin/common.js"></script>
<script language="javascript">
/*
 * jQuery ready
 */
 
 	$(document).ready(function() {
 		
 		//공개 ,비공개 값 세팅
 		$("select[name='open']").val("${surveyVO.detailSurveyVO.open}" == "" ? 0 : "${surveyVO.detailSurveyVO.open}");
 		
 		//form 전송 validation check
 		$("#surveyIndb").on("submit", function(event){
 			//오늘 날짜 구하기
			var nowDate = 0;	//오늘날짜
 			var today = new Date();
			var year = today.getFullYear();		//년
			var month = today.getMonth() + 1;	//월	
			var day = today.getDate();			//일
			
 			var sdate = $("input[name='sdate']").val();	//시작날짜
 			var edate = $("input[name='edate']").val();	//종료날짜
 			
 			month = month >= 10 ? month : "0" + month;
			day = day >= 10 ? day : "0" + day;
			nowDate = Number(year + month + day);
 			
 			//설문제목입력 유효성검사
 			if($.trim($("input[name='surveyTitle']").val()) == ""){
 				alert("설문 제목을 입력해주세요");
 				$("input[name='surveyTitle']").focus();
 				event.preventDefault();
 				return false;
 			}
 			
 			//설문기간 유효성검사
 			if(sdate == "" || edate == ""){
 				alert("설문기간을 선택해주세요.");
 				event.preventDefault();
 				return false;
 			}
 			
 			if(Number(sdate) < nowDate || Number(edate) < nowDate ){
 				alert("설문기간은 현재날짜보다 뒤로 설정해주어야 합니다.");
 				event.preventDefault();
 				return false;
 			}
 			
 			//설문기간 유효성검사
 			if(Number(sdate) > Number(edate)){
 				alert("종료날짜는 시작날짜와 같거나 뒤로 설정해주세요.")
 				event.preventDefault();
 				return false;
 			}
 			
 			//설문조사 질문 보기 유효성검사
 			if($("input[name='question']").length < 2){
 				alert("설문조사 질문은 최소 2개 이상등록해주세요.");
 				$("input[name='question']").focus();
 				event.preventDefault();
 				return false;
 			}
 			
 			//질문보기 입력 유효성검사
 			$("input[name='question']").each(function(){
 				if($.trim($(this).val()) == ""){
 					alert("보기 내용을 입력해주세요.");
 					$(this).focus();
 	 				event.preventDefault();
 	 				return false;
 				}
 			})
 			
 		})
 		
 	});

		//옵션추가
		function add_survey(){
			var textQuestion = '<tr><td>보기'
							  +'</td><td><input type="text" size="100" name="question" maxlength="100" value="">&nbsp;' 
							  +'<a href="javascript:;" onclick="del_survey(this)"><img src="/resources/shop/admin/img/i_deloption.gif" align=absmiddle ></a></td></tr>';
							  
			if($("input[name='question']").length < 10){
				$('#tbAdd').append(textQuestion);
			}

		}
		
		//옵션삭제
		function del_survey(obj){
			if(confirm("해당 항목을 삭제하시겠습니까?")){
				$(obj).parent().parent().remove();	
			}
		}

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
	
	<div class="title title_top">설문조사만들기<span>설문조사페이지를 직접 디자인하고 설문조사 질문들을 설정하실 수 있습니다 </span></div>
		<form method="post" id="surveyIndb" action="${ctx}/shop/admin/event/surveyIndb">
			<input type="hidden" name="mode" value="${ 'modify' eq surveyVO.mode ? 'modify' : 'insert'}">
			<input type="hidden" name="surveySno" value="${surveyVO.detailSurveyVO.surveySno}">
			<table width="100%" class="tb" border="1">
				<col class="cellC"><col class="cellL">	
					<tr>
						<td>설문제목</td>
						<td>
							<input type="text" size="100" name="surveyTitle" value="${surveyVO.detailSurveyVO.surveyTitle}" maxlength="100">
							<select name="open">
								<option value="0">비공개</option>
								<option value="1">공개</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>설문기간</td>
						<td>
							<input type="text" name="sdate" value="${surveyVO.detailSurveyVO.sdate}" onclick="calendar(event);" readonly class="line" > ~
							<input type="text" name="edate" value="${surveyVO.detailSurveyVO.edate}" onclick="calendar(event);" readonly class="line" >
						</td>
					</tr>
			</table>
			
					<div class="title title_top">설문조사 질문<span>질문들을 작성합니다. 옵션추가로 질문개수를 지정할 수 있습니다. &nbsp;&nbsp;
						<a href="javascript:add_survey()"><img src="/resources/shop/admin/img/i_addoption.gif" align=absmiddle></a>
					</span></div>
					
					<table width="100%" class="tb" id="tbAdd" border="1">
						<col class="cellC"><col class="cellL">
						<c:choose>
							<c:when test="${!empty surveyVO.detailSurveyVO.questions}">
								<c:forEach items="${surveyVO.detailSurveyVO.questions}" var="list" varStatus="status">
									<tr>
										<td>보기</td>
										<td>
											<input type="text" size="100" name="question" value="${list.surveySubName}" maxlength="100"/>
											<a href="javascript:;" onclick="del_survey(this)"><img src="/resources/shop/admin/img/i_deloption.gif" align=absmiddle ></a>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
							 <tr>
								<td>보기</td>
								<td>
									<input type="text" size="100" name="question" value="" maxlength="100" >
								</td>
							</tr>
							</c:otherwise>
						</c:choose>
					</table>
			
				<div class="button">
					<input type=image src="/resources/shop/admin/img/btn_${'modify' eq surveyVO.mode ? 'modify' : 'register'}.gif"  class="submitImg" />
					<a href="${ctx}/shop/admin/event/surveyList"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
				</div>
		</form>
<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>
