<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<script src="/resources/shop/data/skin/season2/common.js"></script>
<script type="text/javascript">
	window.onload = function(){
		scrollBanner();
	}
</script>
<div class="x-delete">
	<table width=100% align=center cellpadding=0 cellspacing=0>
	<tr>
		<td>
			<form name=frmDelete action="indb/delete" method=post onSubmit="return chkForm(this)">
			<input type=hidden name=id value="${boardVO.id }"> 
			<input type=hidden name=no value=${boardVO.no }>
			<input type=hidden name=mode value="delete"> 
			<input type="hidden" name="oldfile" value="${boardVO.oldfile }">
			<input type=hidden name=returnUrl value="list?id=${boardVO.id}">
	
			<table width=100%>
				<tr>
					<td class="del">
						<b>정말로 삭제하시겠습니까?</b>
						<p>글을 삭제합니다. 데이터 삭제시 복구가 불가능 합니다<br>
						<font>또한 이 게시물의 작성자가 <b>업로드한 이미지도 같이 삭제</b>됩니다.<br>
						업로드 되어 있던 이미지는 다른 곳에서도 사용되고 있을 수 있으므로
						<b>신중히 확인하시고 삭제하세요.</b></font>
					</td>
				</tr>
				<tr>
					<td align=center><br>
						<button type="submit" value="확인" class="btn btn-primary active">확인</button>
						<!-- <button onclick="javascript:history.back()" class="btn btn-primary">취소</button> -->
						<button onclick="window.history.go(-1); return false;" class="btn btn-primary">취소</button>
					</td>
				</tr>
			</table>
			</form>
		</td>
	</tr>
	</table>
</div>
