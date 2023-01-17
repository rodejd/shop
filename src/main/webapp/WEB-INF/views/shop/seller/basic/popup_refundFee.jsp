<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header_popup.jsp" %>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script language="javascript">
		if(window.addEventListener) 
		{
			window.addEventListener('load',linecss,false); 
		}
		else 
		{
			window.attachEvent('onload',linecss); 
		}
		</script>
	</head>
	<body class="scroll">
		<div id="dynamic"></div>
		<iframe name="ifrmHidden" src="../../blank.jsp" style="display:none"></iframe>
		<div id="jsmotion"></div>
	
		<div class="title">환불수수료 설정<span>환불수수료의 기본값을 설정합니다.</span><!--  <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=basic&no=2',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle"></a> --></div>
		<form action="${ctx }/shop/seller/basic/popupRefundFeeIndb" method="post" onsubmit="return chkForm(this)">
		<input type="hidden" name="mode" value="cfgemoney">
			<table class="tb" width="100%">
				<colgroup>
					<col class="cellC">
					<col class="cellL">
				</colgroup>
				<tbody>
					<tr>
						<td colspan="20" height="1" bgcolor="DDDDDD"></td>
					</tr>
					<tr>
						<td>환불수수료</td>
						<td>
							<input type="hidden" name="repayfee" size="3" maxlength="3" value="" onkeydown="onlynumber()" class="line" style="text-align:right;">
							<div style="padding-top:2">기본수수료 <input type="text" name="minrepayfee" size="8" maxlength="8" value="${minrepayfee}" class="line" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" style="text-align:right;"> 원&nbsp;<span class="small1"><font color="#5B5B5B">(공란으로 두면 기본수수료는 0원이 됩니다)</font></span></div>
							<input type="hidden" name="minpos" size="1" maxlength="1" value="" class="line" onkeydown="onlynumber()" style="text-align: right;">
						</td>
					</tr>
					<tr>
						<td colspan="20" height="1" bgcolor="DDDDDD"></td>
					</tr>
					<!--
					<tr>
						<td>사용자주문취소</td>
						<td class=noline>
							<input type='radio'  name='userCancel' value='1' > 사용 <input type='radio'  name='userCancel' value='0' > 비사용
							&nbsp;<span class=small><font color=#5B5B5B>사용에 체크시 주문접수단계에서 구매자가 주문취소 가능합니다.</font></span>
						</td>
					</tr>
					-->
				</tbody>
			</table>
			<div class="button">
				<input type="image" src="/resources/shop/admin/img/btn_save.gif">
			</div>
		</form>
		<div id="MSG01" style="background-color: rgb(147, 160, 166); margin: 5px 0px 0px; color: rgb(76, 76, 76); font-style: normal; font-variant: normal; font-weight: normal; font-size: 8pt; line-height: normal; font-family: dotum; padding-top: 0px; padding-bottom: 0px;">
			<div style="padding:8px 13px;">
				<table cellpadding="1" cellspacing="0" border="0" class="small_tip">
					<tbody>
						<tr>
							<td>
								<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><font color="0074BA">환불수수료</font> : 환불시 발생되는 반송비용 및 기타 수수료 등을 정하실 수 있습니다.</td>
							</tr>
					</tbody>
				</table>
			</div>
		</div>
<script>cssRound('MSG01','#F7F7F7')</script>
	</body>
</html>