<%--
/************************************************************************************
* 프로그램명 			: 공통 에러 페이지 
* 관련 SERVICE명 		: 
* 프로그램 내용 		: XMall > 관리자 > 약관관리 > 회원가입약관1,2,이용약관관리
* 작성자	   		 	: 김경훈
* 작성일자 				: 2016-04-4
*************************************************************************************
* 수정자  	수정일자	수정내용
*************************************************************************************
* 
************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<script type="text/javascript">
/*
 * jQuery ready
 */
 $( document ).ready(function() {
	 /*
	var type=${clauseVO.type};
	if(type==1){
		$("#titleMsg").text('회원가입약관1을 수정하고 관리합니다.');
	}else if(type==2){
		$("#titleMsg").text('회원가입약관2을 수정하고 관리합니다.');
	}else if(type==3){
		$("#titleMsg").text('이용약관을 수정하고 관리합니다.');
	}else{
		$("#titleMsg").text('회사소개를 수정하고 관리합니다.');
	}
	 */

	 // TODO - 언어 변경시 clauseVO.type 값 없는 경우 핸들링
	var type="${clauseVO.type != null ? clauseVO.type : "none"}";
	let titleTxt = "회사소개";
	let descTxt = "를";

	switch (Number(type)) {
		case 1:
			titleTxt = "회원가입약관1";
			descTxt = "을";
			break;
		case 2:
			titleTxt = "회원가입약관2";
			descTxt = "을";
			break;
		case 3:
			titleTxt = "이용약관";
			descTxt = "을";
			break;
	}

	 const descEl = $("<span>").html(titleTxt + descTxt + " 수정하고 관리합니다.");
	$("#titleMsg").append(titleTxt).append(descEl);
});

function save(){
	$.ajax({
		type:'post',
		url: ctx+'/shop/admin/basic/clauseRegist',
		headers : {
	            "Content-Type" : "application/json"},
	    data:JSON.stringify({url:urlS}),
	    dataType:'json',
	    success:function(result){
	    	$("#navi1").html(result[0].menuName);
	    	$("#navi2").html(result[1].menuName);
	    
	    } 
	}); 
}

function skinChange(){
	// 현재 kr 만 있으므로 주석
	// $("#dataForm").attr("action","/shop/admin/basic/clause").submit();
}
</script>

<!-- 여기서부터 -->
<%--
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
		<form id="dataForm" method=post action="${ctx}/shop/admin/basic/clauseRegist" onsubmit="return chkForm(this)">
		<input type="hidden" id="type" name="type" value="${clauseVO.type}" />
    	<div class="title title_top" id="titleMsg"></div>
		

		<table width=100% class=tb>
			<col class=cellC><col class=cellL>
			<tr>
				<td>약관내용</td>
				<td style="padding:5px">
					<SELECT ID="skin" NAME="skin" required label="분류" onchange="javascript:skinChange();">
						${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), clauseVO.skin) }
					</SELECT>
					<textarea name=clause id=clause type=editor style="width:100%;height:500px">${clauseVO.clause}</textarea>
					<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
					<script>mini_editor("/resources/shop/lib/meditor/");</script>
				</td>
			</tr>
		</table>

		<div class=button>
			<input type=image src="/resources/shop/admin/img/btn_save.gif">
		</div>
	</form>
	--%>

	<div class="sub-cont-wrap">
		<form id="dataForm" method="post" action="${ctx}/shop/admin/basic/clauseRegist" onsubmit="return chkForm(this)">
			<div>
				<div class="title title_top" id="titleMsg"></div>

				<div class="div-tbl inp-tbl">
					<div class="th w-120">약관내용</div>
					<div>
						<input type="hidden" name="type" value="${clauseVO.type}" >
						<input type="hidden" name="no" value="${item.no}" >
						<!-- shopLibFucMapper.xml getCodeItem -->
						<select ID="skin" NAME="skin" required title="분류" onchange="skinChange();">
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), clauseVO.skin) }
						</select>
<%--						<textarea name="clause" id="clause" type="editor" style="width:100%;height:500px">${clauseVO.clause}</textarea>--%>
						<textarea name="clause" id="clause" type="editor" style="width:100%;height:500px">${item.content}</textarea>
						<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
						<script>mini_editor("/resources/shop/lib/meditor/");</script>
					</div>

				</div>

			</div>

			<div class="button">
				<input type="submit" class="admin-btn" value="저장" />
			</div>
		</form>	<!-- END form -->
	</div>

<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
