<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 


<c:if test="${fn:length(groupVO.errorMsg) >0  and groupVO.errorMsg != null and groupVO.errorMsg != ''}">
	<script>
	$( document ).ready(function() {
		alert("${groupVO.errorMsg}");
	});
  	</script>
</c:if>

<%-- <c:if test="${groupVO.result == 1} ">
	<script>parent.location.reload();</script>
</c:if>
 --%>
<!-- 초기화 -->
<c:set var="adminAuth" value="${groupVO.adminAuth}"></c:set>
<c:set var="feeName" value=""></c:set>
<c:set var="grpLevel" value=""></c:set>
<%-- 
  groupController에 구현

<c:choose>
	<c:when test="0 < adminAuth">
		<c:set var = "grpLevel" value="a.kLevel >=80"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var = "grpLevel" value="a.kLevel <80"></c:set>   <!-- 그룹레벨이 80을 기준으로 구분, 쿼리를 변경하는 구간.. Controller에서 변경 -->
	</c:otherwise>
</c:choose>
  --%>
<c:if test="${0 < adminAuth}">
<%--	<div style="padding:10 0 5 5;color:#fe5400;"><font color="000000"><b>1. 먼저 아래에서 관리자의 그룹과 권한을 정합니다.</b></font></div>--%>

	<div class="sub-title">
		1. 먼저 아래에서 관리자의 그룹과 권한을 정합니다.
	</div>
</c:if> 

<%--
<table width="100%" cellpadding="0" cellspacing="0" border=0>
		<tr><td class="rnd" colspan="10"></td></tr>
		<tr class="rndbg" style="padding-top: 2px;">
			<th><font class="small1"><b>번호</b></font></th>
			<th><font class="small1"><b>그룹명</b></font></th>
			<th><font class="small1"><b>그룹레벨</b></font></th>
			 <c:choose>
				<c:when test="${0<adminAuth}"> 
					<th><font class="small1"><b>권한</b></font></th>
				</c:when>
				<c:otherwise> 
					<th>회원수</th>
					<th>할인율혜택</th>
					<th>추가적립금</th>
					<th>무료배송 적용여부</th>
					<th>기준금액</th>
				</c:otherwise>
			</c:choose> 
			<th><font class="small1"><b>권한수정</b></font></th>
			<th><font class="small1"><b>삭제</b></font></th>
		</tr>
		<tr><td class="rnd" colspan="10"></td></tr>
		<col align="center" span="10">
		<c:forEach items="${groupVO.memberList}" var="memberList" varStatus="status">
			<c:set var ="feeName"  value= "${memberList.freeDeliveryfee eq 'Y' ? '무료배송' : '배송비정책' }"></c:set>
			<c:set var ="grpLevel" value="${memberList.kLevel}" ></c:set> 
			<tr><td height="4" colspan="10"></td></tr>
			<tr align="center">
			    <!-- 번호 -->
				<td width="50"><font class="ver8" color="444444">${status.index+1 }</font></td>
				<td width="120">
				<!-- 그룹명 -->
				${memberList.grpnm}
				<!-- 그룹레벨이 100일 경우 (전체) 아닐경우 ' ' -->
				${grpLevel eq '100' ? '&nbsp;<font class=\"small1\" color=\"777777\">(전체)</font>' : ''}  
				</td>
				<!-- 그룹레벨 -->
				 <td width="80"><font class="ver8" color="444444"> ${grpLevel} </font> </td>  
			
				<c:if test="${adminAuth == 0}"> 
				     <!-- 회원수 -->
				    <td><font class="ver8" color="444444">${memberList.cnt}</font></td>
				    <!-- 제공할인율 --> 
					<td><font class="ver8" color="444444">${memberList.dc}%</font></td>
					<!-- 추가적립금 -->
					<td><font class="ver8" color="444444">${memberList.addEmoney}%</font></td>
					<!-- 배송비무료유무 -->
					<td><font class="ver8" color="444444">${feeName}</font></td>  
					<!-- 기준금액 -->
					<td><font class="ver8" color="444444">${memberList.kAmount}</font></td>  
				 </c:if>
				<!-- 권한 -->
				<c:choose>
				<!--
 				<c:when test="${grpLevel ==100}">
					<td>
						<div style="float:left"><div style="text-overflow:ellipsis;overflow:hidden;width:95px;height:20;padding-top:5" nowrap><font class="small1" color="777777">쇼핑몰기본관리</font></div></div>	
					</td>	
				</c:when>
				-->
				<c:when test="${adminAuth >0}">
			<!-- String menunm[] = StringUtil.split(ShopConfig.getProperty("level"+grpLevel), ","); -->
				<c:set var="menunm" value="${groupVO.menunmList[status.index]}"></c:set>
				<c:set var="menuname" value=""></c:set>
					<c:choose>
					<c:when test="${fn:length(menunm) >0}">
					
					<td>
					<c:forEach items="${menunm}" var="menu" varStatus="ii">
						<c:if test="${grpLevel == 100}"><c:set var="menuname" value="쇼핑몰기본관리"></c:set></c:if>
						<c:if test="${menu eq 'design'}"><c:set var="menuname" value="디자인관리"></c:set></c:if>
						<c:if test="${menu eq 'goods'}"><c:set var="menuname" value="상품관리"></c:set></c:if>
						<c:if test="${menu eq 'order'}"><c:set var="menuname" value="주문관리"></c:set></c:if>
						<c:if test="${menu eq 'member'}"><c:set var="menuname" value="회원관리"></c:set></c:if>
						<c:if test="${menu eq 'board'}"><c:set var="menuname" value="게시판관리"></c:set></c:if>
						<c:if test="${menu eq 'event'}"><c:set var="menuname" value="쿠폰관리"></c:set></c:if>
						<c:if test="${menu eq 'marketing'}"><c:set var="menuname" value="마케팅관리"></c:set></c:if>
						<c:if test="${menu eq 'seller'}"><c:set var="menuname" value="판매사관리"></c:set></c:if>
						<c:if test="${menu eq 'log'}"><c:set var="menuname" value="통계/데이터관리"></c:set></c:if>
						<c:if test="${menu eq 'myritz'}"><c:set var="menuname" value="MyRitz관리"></c:set></c:if>
					
						<div style="float:left">
							<div style="text-overflow:ellipsis;overflow:hidden;width:95px;heigth:20;padding-top:5" nowrap>
								<font class="small1" color="777777">${menuname}</font>
							</div>
						</div>
					</c:forEach>
					</td>
					</c:when>
					<c:otherwise>
						<td> - </td>
					</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
				
				 <!-- 권한수정 -->
				<c:if test="${not(grpLevel eq '100') and not(grpLevel eq '1')}"> 
					<td width="60"><a href="javascript:popupLayer('${ctx}/shop/admin/member/group/popup.register?mode=modGrp&sno=${memberList.sno} &adminAuth=${adminAuth} ')"><img src="/resources/shop/admin/img/i_edit.gif" border="0" /></a></td>
				</c:if>
				<!-- 삭제 -->
				<td width="50">

				 <c:if test="${not(grpLevel eq '100') and not(grpLevel eq '1')}"> 
				<fmt:parseNumber var = "grpLevel"  type="number" value="${grpLevel}"></fmt:parseNumber>   <!-- grpLevel = String - > int -->
				<a href="${ctx}/shop/admin/member/group/indb?mode=delGrp&sno=${memberList.sno}&kLevel=${grpLevel}&adminAuth=${adminAuth}"  onclick="return confirm('정말로 ${memberList.grpnm} 그룹을 삭제하시겠습니까?')"><img src="/resources/shop/admin/img/i_del.gif" border="0" /></a>
				</c:if>
			
			</td>       
			</tr>
			<tr><td height="4" colspan="10"></td></tr>
			<tr><td colspan="10" class="rndline"></td></tr>

		</c:forEach>
		
</table>
--%>

<div class="sub-cont-wrap">
	<table class="stripe-tbl">
		<colgroup>
			<col style="width: 50px;">
			<col style="width: 120px;">
			<col style="width: 80px;">
			<c:if test="${adminAuth > 0}">
				<col>
				<col style="width: 60px">
				<col style="width: 50px">
			</c:if>
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>그룹명</th>
				<th>그룹레벨</th>
				<c:choose>
					<c:when test="${adminAuth > 0}">
						<th>권한</th>
					</c:when>
					<c:otherwise>
						<th>회원수</th>
						<th>할인율혜택</th>
						<th>추가적립금</th>
						<th>무료배송 적용여부</th>
						<th>기준금액</th>
					</c:otherwise>
				</c:choose>
				<th>권한수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${groupVO.memberList}" var="memberList" varStatus="status">
				<c:set var ="feeName"  value= "${memberList.freeDeliveryfee eq 'Y' ? '무료배송' : '배송비정책' }" />
				<c:set var ="grpLevel" value="${memberList.kLevel}" />
				<tr>
					<!-- 번호 -->
					<td>${status.index+1 }</td>

					<!-- 그룹명 -->
					<td>
						${memberList.grpnm}
						<!-- 그룹레벨이 100일 경우 (전체) 아닐경우 ' ' -->
						${grpLevel eq '100' ? '&nbsp;<font class=\"small1\" color=\"777777\">(전체)</font>' : ''}
					</td>

					<!-- 그룹레벨 -->
					<td>${grpLevel}</td>

					<c:if test="${adminAuth == 0}">
						<!-- 회원수 -->
						<td><font class="ver8" color="444444">${memberList.cnt}</font></td>
						<!-- 제공할인율 -->
						<td><font class="ver8" color="444444">${memberList.dc}%</font></td>
						<!-- 추가적립금 -->
						<td><font class="ver8" color="444444">${memberList.addEmoney}%</font></td>
						<!-- 배송비무료유무 -->
						<td><font class="ver8" color="444444">${feeName}</font></td>
						<!-- 기준금액 -->
						<td><font class="ver8" color="444444">${memberList.kAmount}</font></td>
					</c:if>

					<!-- 권한 -->
					<c:choose>
						<c:when test="${adminAuth >0}">
							<!-- String menunm[] = StringUtil.split(ShopConfig.getProperty("level"+grpLevel), ","); -->
							<c:set var="menunm" value="${groupVO.menunmList[status.index]}" />
							<c:set var="menuname" value="" />

							<td>
								<c:choose>
									<c:when test="${fn:length(menunm) >0}">
										<ul class="list-ul" style="text-align: left;">
											<c:forEach items="${menunm}" var="menu" varStatus="ii">
												<!-- 추후 영문메뉴 - 한글메뉴 매핑하여 DB 처리해야할 듯, 이게 모야.... -->
												<c:if test="${grpLevel == 100}"><c:set var="menuname" value="쇼핑몰기본관리" /></c:if>
												<c:if test="${menu eq 'design'}"><c:set var="menuname" value="디자인관리" /></c:if>
												<c:if test="${menu eq 'goods'}"><c:set var="menuname" value="상품관리" /></c:if>
												<c:if test="${menu eq 'order'}"><c:set var="menuname" value="주문관리" /></c:if>
												<c:if test="${menu eq 'member'}"><c:set var="menuname" value="회원관리" /></c:if>
												<c:if test="${menu eq 'board'}"><c:set var="menuname" value="게시판관리" /></c:if>
												<c:if test="${menu eq 'event'}"><c:set var="menuname" value="쿠폰관리" /></c:if>
												<c:if test="${menu eq 'marketing'}"><c:set var="menuname" value="마케팅관리" /></c:if>
												<c:if test="${menu eq 'seller'}"><c:set var="menuname" value="판매사관리" /></c:if>
												<c:if test="${menu eq 'log'}"><c:set var="menuname" value="통계/데이터관리" /></c:if>
												<c:if test="${menu eq 'myritz'}"><c:set var="menuname" value="MyRitz관리" /></c:if>

												<li style="display: inline-block;">${menuname}</li>
											</c:forEach>
										</ul>
									</c:when>
									<c:otherwise>
									-
									</c:otherwise>
								</c:choose>
							</td>
						</c:when>
					</c:choose>

					<!-- 권한수정 -->
					<c:if test="${not(grpLevel eq '100') and not(grpLevel eq '1')}">
						<td width="60">
<%--							<img src="/resources/shop/admin/img/i_edit.gif" border="0" />--%>
							<a href="javascript:popupLayer('${ctx}/shop/admin/member/group/popup.register?mode=modGrp&sno=${memberList.sno} &adminAuth=${adminAuth} ')"
							   class="admin-i-edit">수정</a>
						</td>
					</c:if>
					<!-- 삭제 -->
					<td width="50">
						<c:if test="${not(grpLevel eq '100') and not(grpLevel eq '1')}">
							<fmt:parseNumber var = "grpLevel"  type="number" value="${grpLevel}" />
<%--							<img src="/resources/shop/admin/img/i_del.gif" border="0" />--%>
							<a href="${ctx}/shop/admin/member/group/indb?mode=delGrp&sno=${memberList.sno}&kLevel=${grpLevel}&adminAuth=${adminAuth}"
								class="admin-i-del"
							   onclick="return confirm('정말로 ${memberList.grpnm} 그룹을 삭제하시겠습니까?')">삭제</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>	<!-- END sub-cont-wrap -->

<%--<div style="padding-top:10px"></div>--%>
<!-- 그룹추가 -->
<div style="text-align: center;">
<c:choose>
	<c:when test="${0 < adminAuth}">
<%--				<img src="/resources/shop/admin/img/btn_add_admingroup.gif" border="0" vspace="5" hspace="40" />--%>
		<a href="javascript:popupLayer('${ctx}/shop/admin/member/group/popup.register?mode=addGrp&adminAuth=${adminAuth}');"
			class="admin-btn-add-admingroup">관리자 그룹 추가</a>
	</c:when>
	<c:otherwise>
<%--		<img src="/resources/shop/admin/img/btn_addgroup.gif" border="0" vspace="5" hspace="40" />--%>
		<a href="javascript:popupLayer('${ctx}/shop/admin/member/group/popup.register?mode=addGrp&adminAuth=${adminAuth}');"
		   class="admin-btn-add-admingroup">그룹 추가</a>
	</c:otherwise>
</c:choose>
</div>


		
		