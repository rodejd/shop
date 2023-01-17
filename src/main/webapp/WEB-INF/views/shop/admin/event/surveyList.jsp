<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
 $(document).ready(function() {
	$("select[name='pageSize']").val("${surveyVO.pageSize}");
	
	$("#searchBtn").click(function(){
		$("#pageNo").val("1");
	})
 })

 
  
 /**
  *	설문 게시글 게시여부조사
  * 게시글 공개,비공개 여부 선택
  * 게시글을 선택한게 없을 경우 게시상품을 선택하라는 알람 
  */
 function chk_status_modify(statVal, mode){	
	var surveyChks = null;			//게시글 선택한 checkBox
	var statusModifyFrm = null; 	//form 을 담을 변수
	
	surveyChks = $("#surveyList input:checkbox:checked"); //선택한 checkBox 값 
	statusModifyFrm = $("#surveyForm");	//현재 form값
	
	if(0 >= surveyChks.length){
		alert("게시/게시취소 상품을 선택하세요.");
		return false;
	}
	
	if(confirm("게시/게시취소 하시겠습니까?")){
		surveyChks.each(function(){
			statusModifyFrm.append("<input type='hidden' name='surveyArr' value='"+$(this).val()+"'/>");
		});
		
		$("#statVal").val(statVal);
		$("#mode").val(mode);
		statusModifyFrm.attr("action", ctx + "/shop/admin/event/surveyIndb");
		statusModifyFrm.submit();
	}	
}
//선택한 페이지 번호값에따른 나열 
function goPage(page){
	$("#pageNo").val(Number(page)); //선택한 페이지 값
	$("#surveyForm").attr("action", ctx + "/shop/admin/event/surveyList").submit(); //설문조사리스트 컨트롤러에 페이지값을 전송
}

//정렬순 나열
function sort(sort){
	$('#schSort').val(sort);	//선택한 정렬값
	//$("#surveySchForm").attr("method","get");
	$("#surveyForm").attr("action", ctx + "/shop/admin/event/surveyList").submit();	//설문조사리스트 컨트롤러에 정렬 값을 전송
}

//설문조사 수정페이지 이동 
/*
 * 설문조사 수정페이지 
 * 1.설문시작일과 오늘날짜와 비교
 * 2.설문시작일이 오늘날짜를 지난경우 : 설문시작후에는 수정할 수 없다는 알람창을 띄움
 *   설문시작일이 시작이 되지 않은경우에 수정페이지 이동
 */
function surveyEdit(sno, sdate ,mode){
	var today = new Date();
	$("input[name='mode']").val(mode);	//삭제모드
	$("input[name='surveySno']").val(sno);	//설문번호
	
	if(mode == "modify"){
	
		//설문조사 기간이 시작되면 수정할 수 없도록 날짜비교 
		var year = today.getFullYear();		//년
		var month = today.getMonth() + 1;	//월	
		var day = today.getDate();			//일
		month = month >= 10 ? month : "0" + month;
		day = day >= 10 ? day : "0" + day;
		var nowDate = ""+ year + month + day;	//오늘날짜
		
		sdate = Number(sdate);				//설문기간 시작날짜
		nowDate = Number(nowDate);			//오늘날짜 형변환
		if(sdate <= nowDate){				
			alert("설문기간이 시작한후로는 수정할 수 없습니다.");
			return false;
		}else{
			$("#surveyForm").attr("action", ctx + "/shop/admin/event/survey").submit();	//설문수정페이지
		}
	}else if(mode == "delete" && confirm("정말로 삭제하시겠습니까?")){
		$("#surveyForm").attr("action", ctx + "/shop/admin/event/surveyIndb").submit();	//설문수정페이지
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
<form name="surveyForm" id="surveyForm" action="${ctx}/shop/admin/event/surveyList">
	<input type="hidden" id="pageNo" name="pageNo" value="${surveyVO.pageNo}"/>
	<input type="hidden" id="schSort" name="schSort" value="${surveyVO.schSort}"/>
	<input type="hidden" name="mode"    id="mode"    value="" />	<!-- mode 수정, 삭제, 등록 -->
	<input type="hidden" name="statVal" id="statVal" value="" />
	<input type="hidden" name="surveySno" value=""/>	
	
	<div class="title title_top">설문조사리스트<span>설문조사페이지를 관리합니다. </span></div>
	<table width=100% cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td>
				<table class=tb>
					<col class=cellC><col class=cellL style="width:250"><col class=cellC><col class=cellL>
					<tr>
						<td><font class=small1>설문제목</font></td>
						<td colspan=3>
							<input type="text" value="${surveyVO.schTitle}" name="schTitle" id="schTitle" maxlength="100" class="line" style="height:22px" />
						</td>
					</tr>
				</table>
				<div class="button_top" style="float:center"><input type=image src="/resources/shop/admin/img/btn_search2.gif" id="searchBtn"></div>
				
				<table width=100% cellpadding=0 cellspacing=0>
					<tr>
						<td align=right>
							<table cellpadding=0 cellspacing=0 border=0>
								<tr>
									<td valign=bottom>
										설문번호
										<a href="javascript:sort('snoDESC')"><img id="snoDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
										<a href="javascript:sort('snoASC')"><img id="snoASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
										설문조사시작일
										<a href="javascript:sort('sdateDESC') "><img id="sdateDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
										<a href="javascript:sort('sdateASC') "><img id="sdateASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
										설문조사종료일
										<a href="javascript:sort('edateDESC')"><img id="edateDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
										<a href="javascript:sort('edateASC')"><img id="edateASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
									</td>
									<td style="padding-left:20px">
										<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
										<select name=pageSize onchange="sort();">
												<option value="10" >10개 출력</option>
												<option value="20" >20개 출력</option>
												<option value="40" >40개 출력</option>
												<option value="60" >60개 출력</option>
												<option value="100">100개 출력</option>
										</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr> 
	</table>
	
	<table id="surveyList" width="100%" cellpadding=0 cellspacing=0>
		<tr class=rndbg>
			<th width=30>선택</th>
			<th width=100>설문번호</th>
			<th width=100>설문제목</th>
			<th width=100>설문시작일</th>
			<th width=100>설문종료일</th>
			<th width=100>게시여부</th>
			<th width=100>수정/삭제</th>
		</tr>
		<tr><td class=rnd colspan=10></td></tr>
		<tr><td height=10 colspan=15></td></tr>
		<c:choose>
			<c:when test="${empty surveyVO.surveyList}">
				<tr><td align="center" colspan="12" style="padding-bottom:9;padding-top:9"><font class="ver81">검색 결과가 없습니다.</font></td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${surveyVO.surveyList}" var="list" varStatus="status">
					<tr height=25>
						<td align=center style="padding-bottom:9">
							<font class=ver81>
								<input type="checkbox" name="chk[]" value="${list.surveySno }">
							</font>
						</td>
						<td align=center stype="padding-bottom:9"><font class=ver81>${list.surveySno}</font></td>
						<td class=cell align=center>
							<a href="${ctx}/shop/admin/event/surveyDetail?surveySno=${list.surveySno}"><font class=small1 color=555555><b>${list.surveyTitle}</b></font></a>
						</td>
						<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>
							${list.sdate}
						</font></td>
						<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>
							${list.edate }
						</font></td>
						<td align=center style="padding-bottom:9"><font class=ver81><img src="/resources/shop/admin/img/icn_${list.open}.gif"></font></td>
						<td align=center style="padding-bottom:9">
							<img src="/resources/shop/admin/img/i_edit.gif" onclick="surveyEdit('${list.surveySno}', '${list.sdate}' ,'modify')">
							<img src="/resources/shop/admin/img/i_del.gif" onclick="surveyEdit('${list.surveySno}', '${list.sdate}' ,'delete')">
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td width="20%" height=35 style="padding-left:13px">
				<input type="image" class="button_top" src="/resources/shop/admin/img/i_display.gif" alt="게시" border="0" align='absmiddle' style="cursor:hand"
				   onclick="javaScript:chk_status_modify('1', 'open_modify'); return false;">
				<input type="image" class="button_top" src="/resources/shop/admin/img/i_display_cancel.gif" alt="게시취소" border="0" align='absmiddle' style="cursor:hand"
				   onclick="javaScript:chk_status_modify('0', 'open_modify'); return false;">
			</td>
		</tr>
		</table>
	<%-- 페이징 시작 --%>
		<tags:paginator currentPageNo="${surveyVO.pageNo}" rowCount="${surveyVO.rowCount}" pageSize="${surveyVO.pageSize}"  pageGroupSize="${surveyVO.pageGroupSize}" />
	<%-- 페이징 끝 --%>
	<div class="button" align="center">
		<a href="${ctx}/shop/admin/event/survey"><img src="/resources/shop/admin/img/btn_register.gif"></a>
	</div>
	
</form>
<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>