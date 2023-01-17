<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

 <div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>수령자정보</font></div>
<table class=tb>
	<col class=cellC><col class=cellL>
	<tr>
		<td>수령자</td>
		<td>
		<input type=text name=nameReceiver value="${popupVO.rtData.namereceiver}" style="width:115px" class=line onkeydown="noSpecialCharacters()" maxlength="20">
		</td>
	</tr>
	<tr>
		<td>개인통관고유부호</td>
		<td>
		<input type=text name=customs_num value="${popupVO.rtData.customsNum}" style="width:115px" class=line maxlength="20">
		</td>
	</tr>
	<tr>
		<td>연락처</td>
		<td>
			<select class="sel_ty03" id="mobileReceiverType" name="mobileReceiverType">
				${webUtil:makeSelectCodeItem((codeUtil:codeitem('teltype')), popupVO.rtData.mobileReceiverType) }
			</select>
			<input type=text name="mobileReceiver" value="${popupVO.rtData.mobilereceiver}" style="width:115px" class="line w20" maxlength="15" onkeydown="phoneNumber(event)">
		</td>
	</tr>
	<tr>
		<td rowspan="2">주소</td>
		<td>
			<font color=444444>
				<select class="sel_ty03" id="country" name="country">
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('countrytype')), popupVO.rtData.country) }
				</select>
				<input type=text name=zipcode size=6 readonly value="${popupVO.rtData.zipcode}" class="line w30" id="postcode">
			</font>
			<a href="javascript:postcode();"><img src="/resources/shop/admin/img/btn_zipcode.gif" align=absmiddle></a>
		</td>
	</tr>
	<tr>
		<td colspan=3>
			<input type=text id="address" name=address style="width:100%" value="${popupVO.rtData.address}" class=line>
			<input type=text id="address2" name=address2 style="width:100%" value="${popupVO.rtData.address2}" class=line>
			<input type=text id="city" name=city style="width:100%" value="${popupVO.rtData.city}" class=line>
			<input type=text id="state" name=state style="width:100%" value="${popupVO.rtData.state}" class=line>
		</td>
	</tr>
</table>