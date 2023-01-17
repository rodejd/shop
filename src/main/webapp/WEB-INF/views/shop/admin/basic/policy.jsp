<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<script type="text/javascript">
function skinChange(){
	$("#dataForm").attr("action","/shop/admin/basic/policy").submit();
}
</script>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left:12px">
			<form id="dataForm" method="post" action="${ctx}/shop/admin/basic/policyRegist" onsubmit="return chkForm(this)">
				<input type="hidden" name="no" value="${item.no}" />
				<input type="hidden" id="type" name="type" value="${policyVO.type}" />
				<input type="hidden" id="menuKey" name="menuKey" value="${policyVO.menuKey}" />
				
		    	<div class="title title_top">
			    	<c:choose>
			    		<c:when test="${policyVO.type eq '1'}">
							품질보증정책
							<span>품질보증정책을 수정하고 관리합니다.</span>
						</c:when>
			    		<c:when test="${policyVO.type eq '2'}">
							주문배송정책
							<span>주문배송정책을 수정하고 관리합니다.</span>
						</c:when>
			    		<c:when test="${policyVO.type eq '3'}">
							교환반품정책
							<span>교환반품정책을 수정하고 관리합니다.</span>
						</c:when>
			    		<c:when test="${policyVO.type eq '4'}">
							고객등급정책
							<span>고객등급정책을 수정하고 관리합니다.</span>
						</c:when>
			    		<c:when test="${policyVO.type eq '5'}">
							결제/가격/적립금정책
							<span>결제/가격/적립금정책을 수정하고 관리합니다.</span>
						</c:when>
			    		<c:when test="${policyVO.type eq '6'}">
							A/S정책
							<span>A/S정책을 수정하고 관리합니다.</span>
						</c:when>
			    		<c:when test="${policyVO.type eq '7'}">
							개인정보취급방침
							<span>개인정보취급방침을 수정하고 관리합니다.</span>
						</c:when>
			    	</c:choose>
		    	</div>

				<!-- 여기서부터 -->
				<%--
				<table width=100% class=tb>
					<col class=cellC><col class=cellL>
					<tr>
						<td>약관내용</td>
						<td style="padding:5px">
							<select id="skin" name="skin" required label="분류" onchange="skinChange();">
								${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), policyVO.skin)}
							</select>
							<textarea name="policy" id="policy" type="editor" style="width:100%;height:500px">${policyVO.policy}</textarea>
							<!--
							<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
							<script>mini_editor("/resources/shop/lib/meditor/");</script>
							-->
						</td>
					</tr>
				</table>
			--%>

				<div class="div-tbl inp-tbl">
					<div class="th w-120">약관내용</div>
					<div>
						<select id="skin" name="skin" required title="분류" onchange="skinChange();">
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), policyVO.skin)}
						</select>
<%--						<textarea name="policy" id="policy" type="editor" style="width:100%;height:500px">${policyVO.policy}</textarea>--%>
						<textarea name="policy" id="policy" type="editor" style="width:100%;height:500px">${item.content}</textarea>
					</div>
				</div>
		
				<div class="button">
<%--					<input type=image src="/resources/shop/admin/img/btn_save.gif">--%>
					<input type="submit" class="admin-btn" value="저장" />
				</div>
			</form>
			<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>
