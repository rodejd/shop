<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">

function godSubmit(){

	if(confirm("브랜드 수정/추가시 승인을 받아야 이용가능합니다.")){
		document.form.submit();	
	}
}
</script>

<form name=form method=post action="brand/indb">
<input type=hidden name=mode value="mod_brand">
<input type=hidden name=brandno value="${brandVO.brandno}">
<input type=hidden name=infoyn value="${brandVO.infoyn}">
<input type=hidden name=sno value="${brandVO.brandObj.sno}">
<input type="hidden" mame="approvalStatus" value="${brandVO.brandObj.approvalStatus }">
	<div class="title_sub" style="margin:0">브랜드명 생성/수정/삭제<span>브랜드명을 추가하고 관리합니다 </span></div>

	<table class=tb>
		<col class=cellC><col class=cellL>
		<tbody style="height:26px">
		<tr>
			<td>현재브랜드</td>
			<td>
			${brandVO.brandno != 0 ? 'TOP > ' : '' }
			${brandVO.brandno != 0 ? brandVO.brandObj.brandnm : '전체브랜드'  }
			<c:if test="${brandVO.brandno != 0 }">
				<a href="../../goods/goods_brand?brandno=${brandVO.brandObj.sno}" target=_blank><img src="/resources/shop/admin/img/i_nowview.gif" border=0 align=absmiddle hspace=10></a>
			</c:if>
			</td>
		</tr>
		<tr>
			<td>이 브랜드의 상품수</td>
			<td><b>${stringUtil:getMoneyFormatInteger(brandVO.brandCnt)}</b>개</td>
		</tr>
		
		<c:choose>
			<c:when test="${brandVO.brandno != 0 }">
				<tr>
					<td>현재 브랜드명</td>
					<td>
						<input type=text name="brandnm"  class=lline value="${brandVO.brandObj.brandnm}"  readonly="readonly" >
						&nbsp; 브랜드코드 : <b>${brandVO.brandObj.sno}</b>
					</td>
				</tr>
				<tr>
					<td>판매사</td>
					<td>
						<input type=hidden id=schSellerCd name=sellerCd class=lline value="${brandVO.brandObj.sellerCd}"   >
						<input type=text id=schSellerNm name=sellername class=lline value="${brandVO.brandObj.sellerNm }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>승인 상태</td>
					<td>
					 ${brandVO.brandObj.approvalStatus == 1 ? '승인요청' : ''}
					 ${brandVO.brandObj.approvalStatus == 2 ? '승인' : ''} 
				     ${brandVO.brandObj.approvalStatus == 3 ? '반려' : ''} 
					</td>
				</tr>
				
			</table>
			
				<div class="title_sub">브랜드페이지 상단부분 꾸미기<span>브랜드페이지 상단부분을 디자인합니다</span></div>
				<table class=tb>
					<col class=cellC><col class=cellL>
					<tr>
						<td>상단HTML 
						</td>
						<td>
							<textarea name=body type=editor style="width:100%;height:500px">${brandVO.categoryInfoObj.body }</textarea>
								<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
							<script>mini_editor("/resources/shop/lib/meditor/");</script>
						</td>
					</tr>
				</table>
			
		</c:when>
			
			<c:otherwise>
			
				<tr>
					<td>브랜드 생성</td>
					<td><input type='text' name='sub' class='lline' /></td>
				</tr>
								
			</table>
			</c:otherwise>
		</c:choose>
		
	<div class="button"><img src="/resources/shop/admin/img/btn_modify.gif" onclick="godSubmit();" style="cursor:hand;"></div>

</form>

	