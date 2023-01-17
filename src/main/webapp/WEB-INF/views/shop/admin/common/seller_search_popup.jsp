<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="/WEB-INF/views/shop/admin/common/header_popup.jsp" %>

		<script type="text/javascript">
				<%-- // 페이징 처리시 필수 함수 --%>
				function goPage(page){
					$("#pageNo").val(page);
					$("#sellerSearchForm").submit();
				}
	
				<%-- // 판매사코드 setting --%>
				function setSellerCode(schSellerCd, schSellerNm){
					
					$("#schSellerCd", parent.document).val(schSellerCd);
					$("#schSellerNm", parent.document).val(schSellerNm);
					
					if( undefined != parent.sellerCodeCallback){
						parent.sellerCodeCallback();
					}
					
					parent.closeLayer();
					
				}
		</script>
		
		<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
			<tr>
				<td valign="top" style="padding-left:12px">
					<div class="title title_top">판매사 검색</span></div>  
					
					<form id="sellerSearchForm">
						<input type="hidden" name="pageNo" id="pageNo" value="1"/>
						<table>
							<tr>
								<td>
									<select name="schType">
										<option value="sellerName">판매사 명</option>
										<option value="sellerId" ${adminSellerSearchFM.schType == "sellerId" ? "selected" : "" } >판매사 아이디</option>
									</select>
								</td>
								<td>
									<input type=text name="schWord" value="${adminSellerSearchFM.schWord}" class="line">
								</td>
								<td>
									<input type=image src="/resources/shop/admin/img/btn_search_s1.gif" class=null>
								</td>
							</tr>
						</table>
					</form>
					<table width=100% border=1 bordercolor=#B9B9B9 style="border-collapse:collapse">
						<col align=center span=3>
						<tr bgcolor=#F1F1F1 height=25>
							<th width=60><font class=small color=262626><b>판매사 코드</th>
							<th width=90><font class=small color=262626><b>판매사 명</th>
							<th width=90><font class=small color=262626><b>판매사 아이디</th>
						</tr>
						<c:choose>
							<c:when test="${adminSellerSearchFM.rowCount > 0 }">
								<c:forEach items="${adminSellerSearchFM.sellerSearchList}" var="sellerSearchList" varStatus="st">	
									<tr height=24 align="center">
										<td><a href="javascript:setSellerCode('${sellerSearchList.sellerCd}', '${sellerSearchList.sellerNm}');"><font class=ver8 color=0074BA>${sellerSearchList.sellerCd}</a></td>
										<td><font class=ver8 color=262626>${sellerSearchList.sellerNm}</td>
										<td><font class=ver8 color=262626>${sellerSearchList.id}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr height=25>
									<td align=center colspan=3> 조회된 데이터가 존재하지 않습니다. </td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
					
					<!-- 페이징  -->
					<tags:paginator currentPageNo="${adminSellerSearchFM.pageNo}" rowCount="${adminSellerSearchFM.rowCount}" pageSize="${adminSellerSearchFM.pageSize}"  pageGroupSize="${adminSellerSearchFM.pageGroupSize}" />
				
				</td>
			</tr>
		</table>
	</body>
</html>