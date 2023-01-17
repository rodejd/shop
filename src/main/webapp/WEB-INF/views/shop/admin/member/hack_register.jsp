<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>


<html>
	<head>
		<title>'Xmall 관리자 모드'</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="styleSheet" href="/resources/shop/admin/style.css">
		<script src="/resources/shop/admin/common.js"></script>
		<script src="/resources/shop/admin/prototype.js"></script>
		<script type="text/JavaScript">
			if(window.addEventListener) 
			{
				window.addEventListener('load',linecss,false); 
			}
			else 
			{
				window.attachEvent('onload',linecss); 
			}
		</script>
		<script language="JavaScript" type="text/JavaScript">
			if( ${result != null && result != '' ? result : 0} == 1 ){
				parent.location.reload();
				parent.closeLayer();
			}
			
			function chkLength(obj){
				str = obj.value;
				document.getElementById('vLength').innerHTML = chkByte(str);
				if (chkByte(str)>80){
					alert("80byte까지만 입력이 가능합니다");
					obj.value = strCut(str,80);
				}
			}
		</script>
		<div id="dynamic"></div>
		<iframe name="ifrmHidden" src="../../../blank.jsp" style="display:none"></iframe>
		<div id="jsmotion"></div>
	</head>
	<body class="scroll" >
	
		<form class="js_hackRegisterForm" name=form method=post action="hack/indb" onsubmit="return chkForm(this)">
			<input type=hidden name=mode value="modify">
			<input type=hidden name=sno value="${hackVO.sno}">
			
			<div class="title title_top"><span>회원탈퇴내역 상세내용</span></div>
			
			<table class=tb>
				<col class=cellC><col class=cellL>
				<tr>
					<td>아이디</td>
					<td>
						<font class=ver8>${gdLogHack.mId}</font>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<font class=ver8>${gdLogHack.name}</font>
					</td>
				</tr>
				<tr>
					<td>처리형태</td>
					<td>
						<font class=extext><b>${gdLogHack.actor == 1 ? "본인탈퇴" : "강제삭제"}</b></font>
					</td>
				</tr>
				<tr>
					<td>탈퇴일</td>
					<td>
						<font class=ver8>
							<fmt:formatDate value="${gdLogHack.regdt}" type="both" pattern="yy/MM/dd hh:mm aa"/> &nbsp;&nbsp;
							(${gdLogHack.ip})
						</font>
					</td>
				</tr>
				<tr>
					<td>불편사항</td>
					<td>
						<font class=small>
							<ol style="margin-left:23;margin-bottom:5;margin-top:10;">
								<c:forEach items="${checkedList }" var="checked">
									<li>${checked}</li>
								</c:forEach>
							</ol>
						</font>
					</td>
				</tr>
				<tr>
					<td>충고말씀</td>
					<td>
						<textarea name="reason" cols=60 rows=6 style="width:90%;" class=tline>${gdLogHack.reason}</textarea>
					</td>
				</tr>
				<tr>
					<td>관리메모</td>
					<td>
						<textarea name="adminMemo" cols=60 rows=5 style="width:90%;" class=tline>${gdLogHack.adminMemo}</textarea>
					</td>
				</tr>
			</table>
			
			<div class="button_popup">
				<input type=image src="/resources/shop/admin/img/btn_confirm_s.gif">
				<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
			</div>
		
		</form>
<script type="text/JavaScript">
	table_design_load();
</script>