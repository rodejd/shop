<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : popup_mypage_refund.jsp
* 생성일 : 2017. 02. 22
* 작성자 : PMG
* 설   명 : skin default1 사용자 popup_mypage_refund
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170222			PMG				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
<script type="text/javascript" src="/resources/shop/data/skin/default2/common.js"></script>
<link rel="styleSheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/style.css">
<link rel="stylesheet" type="text/css" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/default.css" media="all" />
<link rel="stylesheet" type="text/css" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/jquery-ui.css" media="all" />
</head>
<script type="text/javascript">

$(function(){

});
</script>
<script language="javascript">
function btn_Refund(){
	var mode = '${frontMypageVO.mode}';
	
	var fm = document.frmList;
	fm.mode.value = mode;
	
	if("sendback"==mode){
		
		if(document.getElementsByName("code")[0].value<1){
			alert("사유를 선택해주세요");
			document.getElementsByName("code")[0].focus();
			return;
		}else if(!(document.getElementsByName("memo")[0].value).trim()){
			alert("상세사유를 입력해주세요");
			document.getElementsByName("memo")[0].focus();
			return;
		}else if (document.getElementsByName("bankcode")[0].value<1){
			alert("환불받으실 은행을 선택해주세요");
			document.getElementsByName("bankcode")[0].focus();
			return;
		}else if (!document.getElementsByName("bankaccount")[0].value){
			alert("환불받으실 계좌번호를 입력해주세요");
			document.getElementsByName("bankaccount")[0].focus();
			return;
		}else if (document.getElementsByName("bankuser")[0].value<1){
			alert("환불받으실 계좌 예금주를 입력해주세요");
			document.getElementsByName("bankuser")[0].focus();
			return;
		}else{
			/* var fm = document.frmRefundList;
			fm.mode.value = "${frontMypageVO.mode}";
			fm.ordno.value = "${frontMypageVO.ordno}";
			fm.target = "pareReFund";
			fm.action = "mypage_orderlist.jsp";
			fm.method = "post";
			fm.submit();
			window.open('','_self');
			window.close(); */
			
			ajaxCallJsonPost("/shop/mypage/indb.dojson", "frmList", function(result){
				//alert(JSON.stringify(result));
				opener.parent.location.reload();
				window.close();
			});
		}
	}else{
		if(document.getElementsByName("code")[0].value<1){
			alert("사유를 선택해주세요");
			document.getElementsByName("code")[0].focus();
			return;
		}else if(!document.getElementsByName("memo")[0].value){
			alert("상세사유를 입력해주세요");
			document.getElementsByName("memo")[0].focus();
			return;
		}else{
			ajaxCallJsonPost("/shop/mypage/indb.dojson", "frmList", function(result){
				opener.parent.location.reload();
				window.close();
			});
			
			/* var fm = document.frmRefundList;
			fm.mode.value = '${frontMypageVO.mode}';
			fm.ordno.value = '${frontMypageVO.ordno}'; */
			/* var fm = document.frmRefundList;
			fm.mode.value = "${frontMypageVO.mode}";
			fm.ordno.value = "${frontMypageVO.ordno}";
			fm.target = "pareReFund";
			fm.action = "mypage_orderlist.jsp";
			fm.method = "post";
			fm.submit();
			window.open('','_self');
			window.close(); */
		}
	}
}
</script>

<body>

<form name=frmList mathod=post id=frmList>
<input type=hidden name=mode id=mode> 
<input type=hidden name=ordno value="${frontMypageVO.ordno}">
<input type=hidden name=sno value="${frontMypageVO.sno}">

<table border=4 bordercolor=#000000 style="border-collapse:collapse" width=100%>
	<tr>
		<td>
			<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tr>
					<td colspan="4">
						<table class=tb></table>
					</td>
				</tr>
				<tr>

					<th rowspan="${frontMypageVO.frontOrderItemObjLength + 1}" bgcolor=#f7f7f7><font class=small1 color=434343><b>상품정보</th>
								
					<th bgcolor=#f7f7f7><font class=small1 color=434343><b>주문번호</th>
					<th bgcolor=#f7f7f7><font class=small1 color=434343><b>상품명</th>
					<th bgcolor=#f7f7f7><font class=small1 color=434343><b>상품가격</th>
				</tr>
				<c:forEach items="${frontMypageVO.frontOrderItemObj}" var="itemObj">
					<tr>
						<td>${frontMypageVO.ordno}</td>
						<td>${itemObj.goodsnmKR}</td>
						<td>${shopLibFunction:getExchange(itemObj.price, wskin)}&nbsp;&nbsp; (${itemObj.ea} 개)</td>
					</tr>
				</c:forEach>
						
				<tr>
					<th  bgcolor=#f7f7f7 height=22>반송정보</th>
					<td><font class=small1 color=ED00A2><b>${ 'trade' == frontMypageVO.mode ? '교환' : '환불'} <%-- <%="trade".equals(mode) ? "교환" : "환불" %> --%></td>
					<th bgcolor=#f7f7f7 height=22>사유</th>
					<td>
						<select name=code required>
						<option value="">= 선택하세요 =
						<%-- <%=WebUtil.makeSelectOption(codeitem(dbHandler, "cancel"), "itemcd", "itemnm", "")%> --%>
						${webUtil:makeSelectCodeItem((codeUtil:codeitem('cancel')), "") }
						</select>
					</td>
				</tr>
				<tr>
					<th bgcolor=#f7f7f7 height=22>상세사유</th>
					<td colspan=3>
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
							<tr>
								<td><textarea name=memo style="width:100%;height:65px" required  class="tline"></textarea></td>
							</tr>
						</table>
					</td>
				</tr>
				<%-- <%if("sendback".equals(mode)){%> --%>
				<c:if test="${ 'sendback' == frontMypageVO.mode }">
				<tr>
					<th bgcolor=#f7f7f7 height=22>환불계좌정보</th>
					<td colspan=3>
						<font class=small1 color=434343>은행</font>
						<select name=bankcode >
							<option value="" style="font: 8pt 돋움;">= 선택하세요 =
							<%-- <%=WebUtil.makeSelectOption(codeitem(dbHandler, "bank"), "itemcd", "itemnm", "")%> --%>	
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('bank')), "") }		
						</select>&nbsp;&nbsp;
						<font class=small1>계좌번호</font><input type="text" name="bankaccount" id="" value="" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" onblur="removeChar(event)" maxlength="15">
						<font class=small1> 예금주 </font><input type="text" name="bankuser" id="" value="" maxlength="10">
					</td>
				</tr>
				<%-- <%
				}
				%> --%>
				</c:if>
				<tr bgcolor=#f7f7f7 height=22>
					<td colspan=4 align="center"><a href="javascript:btn_Refund();"><img src="/resources/shop/admin/img/btn_confirm_o.gif"></a></td>
				</tr>
				<tr bgcolor=#f7f7f7 height=22>
					<td colspan=4 class=noline align=left>
						<table cellpadding=5 cellspacing=5>
							<tr>
								<td>
									<div style="padding:10 0 0 67"><font class=small1 color=444444><b>1) 무통장, 계좌이체, 가상계좌로 결제한 주문을 취소하는 경우</b></div>
									<div style="padding:3 0 0 82">환불해줄 고객계좌가 필요하므로, 환불계좌정보를 입력하세요.</div>
									<div style="padding:10 0 0 67"><b>2) 카드로 결제한 주문을 취소하는 경우</b></div>
									<div style="padding:3 0 0 67"><font class=def color=444444>①</font> 카드승인취소를 해야하므로 환불계좌정보는 입력하지 않습니다.</div>
												
									<div style="padding:13 0 6 67"><font color=black><b>- 상품교환인 경우 -</b></font> &nbsp;&nbsp;</div> 
									<div style="padding:3 0 0 67"><font class=small1 color=444444>현 주문상태가 <font color=ED00A2>배송중 또는 배송완료</font>일때만 <font color=ED00A2>상품교환처리가 가능</font>합니다.</font></div>
									<div style="padding:3 0 0 67"><font class=small1 color=444444><font color=ED00A2>주문접수, 입금확인 상태</font>에서는 <font color=ED00A2>배송되지 않은 상태</font>이기 때문에 교환처리가 아닌 <font color=ED00A2>바로  주문취소</font>가 됩니다.</font></div>
								</td>
							</tr>
						</table>
					</td> 
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>