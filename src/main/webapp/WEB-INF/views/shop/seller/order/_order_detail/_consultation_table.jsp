<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<div class=title2>
	<span style="padding-right:10px">&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>요청사항/상담메모</font></span>
	<a href="javascript:popupLayer('${ctx }/shop/admin/order/popup.log?ordno=${popupVO.ordno}', 600, 400, 'table_design_load');"><img src="/resources/shop/admin/img/btn_orderlog.gif" align=absmiddle border=0></a>
</div>
<table class=tb>
	<col class=cellC><col class=cellL>
	<tr height=25>
		<td>고객요청사항</td>
		<td><textarea name=memo style="width:100%;height:100px">${popupVO.rtData.memo}</textarea></td>
	</tr>
	<tr height=25>
		<td>고객상담메모</td>
		<td><textarea name=adminmemo style="width:100%;height:100px">${popupVO.rtData.adminmemo}</textarea></td>
	</tr>
	<tr height=25>
		<td>결제로그</td>
		<td><textarea name=settlelog style="width:100%;height:100px;overflow:visible;font:9pt 굴림체;padding:10px 0 0 8px">${popupVO.rtData.settlelog}</textarea></td>
	</tr>
</table>