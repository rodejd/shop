<%--
/************************************************************************************
* 프로그램명 			: 공통 에러 페이지 
* 관련 SERVICE명 		: 
* 프로그램 내용 		: XMall > 관리자 > 상품관리 > 리스트 
* 작성자	   		 	: 이균
* 작성일자 				: 2014-01-10
*************************************************************************************
* 수정자  	수정일자	수정내용
*************************************************************************************
* 이균		2014-01-10	화면스펙조정
************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%-- ================================================================================
* 공통변수의 할당 부분
================================================================================ --%>
<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script language="javascript" src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	/*
	 * php script
	 */
</script>
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<c:if test="${fn:length(errorMsg) >0 }">
	<script>
	$( document ).ready(function() {
		alert("${errorMsg}");
	});
  	</script>
</c:if>
<script type="text/javascript">
/*
 * jQuery ready
 */

 $(function(){
		$('#form01').on('submit', function(){
			checkForm;
		});  
 })
 
	var checkForm = function() {
		/* var check = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/; 비밀번호 정규식 */
		var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if($('input[name=toEmail]').val() == ""){
			alert('이메일을 확인해 주세요');
			return false;
		}else if($('input[name=subject]').val() == ""){
			alert('제목을 확인해 주세요');
			return false;
		}else if($('input[name=contents]').val() == ""){
			alert('내용을 확인해 주세요');
			return false;
		}else{
			var splitedArr = $('input[name=toEmail]').val().split(',');
			var cnt = 0;
			for(var i=0; i<splitedArr.length; i++){
				if(re.test(splitedArr[i])){
					cnt++;
				}
			}
			if( cnt != splitedArr.length){
				alert("이메일 형식이 맞지 않습니다.");
				return false;
			}
		}
	} 
	
		/* $('.submitBtn').on("click", function(event){
			alert('###########');
			event.preventDefault ? event.preventDefault() : event.returnValue = false;
		}); */
	
	
</script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form method="post" action="testMail" id="form01">
<input type="hidden" name="mode" value="0">
<input type="hidden" name="type" value="${type }">
<input type="hidden" name="size" value="${size }">

<div class="title title_top">메일보내기<span>회원들에게 메일을 전송합니다</span></div>

<table width=100%>
<col class=cellC><col class=cellL><col class=cellL>
<tr>
	<td>전송완료 리스트<br><font class=small color=6d6d6d>메일전송이 시작되면<br>이곳에 메일리스트가<br>보여집니다</font></td>
	<td >
		<c:if test="${fn:length(toEmail) >0 }">
		<c:set var="toEmails" value="${fn:split(toEmail,',')}"/>
		<c:forEach items="${toEmails}" var="toEmails1" varStatus="status">
		${toEmails1},
		</c:forEach>
		</c:if>
	</td>
	<td>
	<table width=100% cellpadding=0 cellspacing=0>
	<tr>
		<td style="padding-right:3px"><iframe name="boxEmail" style="width:100%;height:100px;border:'1px solid #cccccc';" frameborder=0></iframe></td>
		<td width=100><input type="submit" style="width:100%;height:104px;background:#4A3F38;color:#ffffff" id="submitBtn" value="메일발송하기"></td>
	</tr>
	<tr>
		<td style="padding-top:3px" colspan=2>
		<div style="height:8px;font:0;background:#f7f7f7;border:1 solid #cccccc">
		<div id=progressBar style="height:8px;background:#ff0000;width:0"></div>
		</div>
		</td>
	</tr>
	</table>

	</td>
</tr>
<c:choose>
	<c:when test="${type eq 'direct' }">
	<tr>
		<td>받는분 이메일</td>
		<td colspan="2"><input type="text" name="toEmail" id="toEmail" value="" style="width:100%" class="lline" required placeholder="예시 : example@example.com,example2@example.com(콤마(,)로 구분해서 띄어쓰기 없이 입력바랍니다.)" onclick="this.placeholder='';"></td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="toEmail"  id="toEmail" value="${ListToString }" class="lline" required>
		<tr>
			<td>수신대상</td>
			<td><b>${size}명</b></td>
		</tr>
	</c:otherwise>
</c:choose>
<tr>
	<td>제목</td>
	<td colspan="2"><input type="text" name="subject" style="width:100%" required class="lline" value="${subject }"></td>
</tr>
<tr>
	<td>내용</td>
	<td colspan="2">
	<textarea name="contents" style="width:100%;height:480px" type="editor" class="tline">${contents }</textarea>
	<script src=/resources/shop/lib/meditor/mini_editor.js></script>
    <script>mini_editor("/resources/shop/lib/meditor/");</script>
	</td>
</tr>
</table>

</form>



<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>
