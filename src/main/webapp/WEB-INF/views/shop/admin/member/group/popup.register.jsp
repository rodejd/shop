<%
/************************************************************************************
* 프로그램명 				: 
* 관련 SERVICE명 			: 
* 프로그램 내용 			: XMall > 관리자 > 그룹정보입력
* 작성자	   		 		: 
* 작성일자 				: 
* 수정자  				: 
* 수정 내용				: 
* 수정일자				: 
************************************************************************************/

%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<script type="text/javascript">
if( '${groupVO.result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}
</script>
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>
<script src="/resources/shop/admin/prototype.js"></script>

<c:set var="stLevel" value="1" ></c:set>
<c:set var="edLevel" value="79" ></c:set>
<fmt:parseNumber var = "stLevel"  type="number" value="${stLevel}"></fmt:parseNumber>
<fmt:parseNumber var = "edLevel"  type="number" value="${edLevel}"></fmt:parseNumber>
<c:set var="adminAuth" value="${groupVO.adminAuth}"></c:set>
<c:set var="mode" value="${groupVO.mode}"></c:set>
<c:set var="titleNm" value="${mode eq 'modGrp' ? '수정' : '추가'}"></c:set>
<c:set var="groupNm" value=""></c:set>
<c:set var="fixDisabled" value=""></c:set>

<c:set var="rtInfo" value="${groupVO.groupList[0] != null? groupVO.groupList[0] : ''}"></c:set>
<c:set var="selList" value="${groupVO.selectBoxMap }"></c:set>
<c:set var="chkProp" value=""></c:set>


<c:if test="${adminAuth >0}">
	<c:set var="chkProp" value=""></c:set>
	
	<c:set var="stLevel" value="80" ></c:set>
	<c:set var="edLevel" value="100" ></c:set>
</c:if>





 	<div class="title title_top">
		<c:choose>
			<c:when test="adminAuth >0">관리자그룹 ${titleNm} <span>관리자그룹을 설정하세요</span></c:when>
			<c:otherwise>회원그룹 ${titleNm } <span>회원그룹을 설정하세요</span></c:otherwise>
		</c:choose>
		
	</div>

<form method="post" action="indb" onsubmit="return chkForm(this);">
<input type="hidden" name="mode" 		value="${mode}">
<input type="hidden" name="sno" 		value="${groupVO.sno }">
<input type="hidden" name="adminAuth" 	value="${adminAuth}">

	<table cellpadding="0" cellspacing="0" border="0" bgcolor="ebebeb">
		<tr>
			<td bgcolor="e8e8e8">
				<table cellpadding="2" cellspacing="1" border="0" bgcolor="e8e8e8">
					<tr>
						<td bgcolor="f6f6f6" width="160" align="center">그룹명</td>
						<td bgcolor="ffffff" width="400">&nbsp;&nbsp;<input type="text" name="grpnm" value="${mode eq 'modGrp'? rtInfo.grpnm : ''}" required class="line" onkeydown="noSpecialCharacters()" maxlength="10"></td> <!-- warning -->
					</tr>
					<tr>
						<td bgcolor="f6f6f6" align="center">그룹레벨</td>
					
						<td bgcolor="ffffff">&nbsp;&nbsp;
						
						<select name = "KLevel">
						<c:forEach begin="${stLevel }" end="${edLevel}"  var="level" step="1">
							<c:set var="groupNm" value="${selList[level]}" />
						  
							<c:choose>
								<c:when test="${groupNm != null and groupNm != ''}">
									<c:choose>
										<c:when test="${level == (mode eq 'modGrp'? rtInfo.kLevel : 0)}">
											<option value="${level}"  selected >  ${level} - [${groupNm}]</option>
										</c:when>
										<c:otherwise>
											<c:forEach items = "${selList}" var ="i">
												<c:if test="${level == i.key}">
													<optgroup label="${level}- [ ${groupNm}] "></optgroup>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<option value="${level}">${level} </option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>

					</tr>
					<tr>
						<td bgcolor="f6f6f6" align="center">할인율혜택</td>
						<td bgcolor="ffffff">
							<label>구매액의
								<input type="text" name="dc" value="${mode eq 'modGrp' ? rtInfo.dc : 0 }" style="width:35px;" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" maxlength="3"> %
								할인혜택을 제공합니다
							</label>
							<div style="padding:3px 0px 0px 10px;"><font class="extext">(할인혜택을 안주려면 빈공란 또는 "0"으로 두세요)</font></div>
						</td>
					</tr>
					<tr>
						<td bgcolor="f6f6f6" align="center">추가적립금</td>
						<td bgcolor="ffffff">
							<label>구매액의
								<input type="text" name="addEmoney" value="${mode eq 'modGrp' ? rtInfo.addEmoney : 0 }" style="width:35px" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" maxlength="3"> %
								를 추가로 적립을 해줌
							</label>
							<div style="padding:3px 0px 0px 10px;"><font class="extext">(추가적립금을 안주려면 빈공란 또는 "0"으로 두세요)</font></div>
						</td>
					</tr>
					<tr>      
						<td bgcolor="f6f6f6" align="center">무료배송 적용여부</td>
						<td bgcolor="ffffff">
							<label>
								<span class="noline">
									<input type="checkbox"  class="check" onchange="listener(this)" ${mode == 'modGrp' and rtInfo.freeDeliveryfee eq 'Y'? 'checked' : ''}>
								</span> 배송비 무료
							</label>
							<input type="hidden" class ="free" name="freeDeliveryfee" value="${mode == 'modGrp' ? rtInfo.freeDeliveryfee : 'N' }">
							<div style="padding:3px 0px 0px 10px;"><font class="extext">(해당 그룹이 무조건 배송비가 없을 경우 체크를 하세요.)</font></div>
						</td>
					</tr>
					<tr>
						<td bgcolor="f6f6f6" align="center">기준금액</td>
						<td bgcolor="ffffff">
							<label>
								구매액이 ₩<input type="text" name="kAmount" value="${mode eq 'modGrp' ? rtInfo.kAmount : 0 }" style="width:80px" class="rline" maxlength="10" data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)">
								이상 구매시 등급
							</label>
						</td>
					</tr>
				</table>
				<c:if test="${adminAuth > 0}">
					<c:if test="${rtInfo !=null and rtInfo ne ''}">
						<c:if test="${rtInfo.kLevel eq '100'}">
							<c:set var ="fixDisabled" value="disabled" />
						</c:if>
					</c:if>
					<div style="padding-top:10px;background:#ffffff"></div>
					<table cellpadding="2" cellspacing="1" border="0" bgcolor="e8e8e8">
						<tr>
							<td bgcolor="f6f6f6" width="160" align="center">관리 권한설정</td>
							<td bgcolor="ffffff" width="400">
								<c:set var = "menunm" value="${groupVO.menunmList[0]}" />
								<c:if test="${rtInfo !=null and rtInfo ne ''}">
									<%-- <c:set var = " design" value="${rtInfo.kLevel eq '100' ? 'checked' : '' }"></c:set> --%>
									<c:set var = " seller" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " goods" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " order" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " mb" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " board" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " event" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " marketing" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " log2" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
									<c:set var = " myritz" value="${rtInfo.kLevel eq '100'? 'checked' : '' }" />
								</c:if>
								<c:if test="${menunm !=null and fn:length(menunm) >0}">
									<c:forEach begin="0" end="${fn:length(menunm)}" step="1" var ="i">
										<%-- <c:if test="${menunm[i] eq 'design'}">
											<c:set var="design" value="checked"></c:set>
										</c:if> --%>
										<c:if test="${menunm[i] eq 'seller'}">
											<c:set var="seller" value="checked" />
										</c:if>
										<c:if test="${menunm[i] eq 'goods'}">
											<c:set var="goods" value="checked" />
										</c:if>
										<c:if test="${menunm[i] eq 'order'}">
											<c:set var="order" value="checked" />
										</c:if>
										<c:if test="${menunm[i] eq 'member'}">
											<c:set var="mb" value="checked" />
										</c:if>
										<c:if test="${menunm[i] eq 'event'}">
											<c:set var="event" value="checked" />
										</c:if>
										<c:if test="${menunm[i] eq 'marketing'}">
											<c:set var="marketing" value="checked" />
										</c:if>
										<c:if test="${menunm[i] eq 'log'}">
											<c:set var="log2" value="checked" />
										</c:if>
										<c:if test="${menunm[i] eq 'board'}">
											<c:set var="board" value="checked" />
										</c:if>
										<%--
										<c:if test="${menunm[i] eq 'myritz'}">
											<c:set var="myritz" value="checked" />
										</c:if>
										--%>
									</c:forEach>
								</c:if>
								<c:if test="${rtInfo !=null and rtInfo ne ''}">
									<c:set var="levelTmp" value="${rtInfo.kLevel eq '100' ? 'checked' : ''}" />
								</c:if>
								<div style="padding-left:15px;padding-top:5px">
									<label>
										<input type="checkbox" name="menu" value="basic" class="null" disabled ${levelTmp}/> 쇼핑몰기본관리
										<font class="extext" color="ea0095">(전체관리자에게만 권한이 부여됩니다)</font>
									</label>
								</div>

						<%-- 	<div style="padding-left:15;padding-top:3px;float:left;width:170px"><input type="checkbox" name="menunm" value="design" class="null" ${fixDisabled } ${design}/>디자인관리</div> --%>
								<div style="padding-left:15px;padding-top:3px;float:left;width:170px">
									<label>
										<input type="checkbox" name="menunm" value="goods" class="null" ${fixDisabled } ${goods} /> 상품관리
									</label>
								</div>
								<div style="padding-left:15px;padding-top:3px;float:left;width:170px">
									<label>
										<input type="checkbox" name="menunm" value="order" class="null" ${fixDisabled } ${order} /> 주문관리
									</label>
								</div>
								<div style="padding-left:15px;padding-top:3px;float:left;width:170px">
									<label>
										<input type="checkbox" name="menunm" value="member" class="null" ${fixDisabled } ${mb} /> 회원관리
									</label>
								</div>
								<div style="padding-left:15px;padding-top:3px;float:left;width:170px">
									<label>
										<input type="checkbox" name="menunm" value="board" class="null" ${fixDisabled } ${board} /> 게시판관리
									</label>
								</div>
								<div style="padding-left:15px;padding-top:3px;float:left;width:170px">
									<label>
										<input type="checkbox" name="menunm" value="event" class="null" ${fixDisabled } ${event} /> 쿠폰관리
									</label>
								</div>
								<div style="padding-left:15px;padding-top:3px;float:left;width:170px">
									<label>
										<input type="checkbox" name="menunm" value="seller" class="null" ${fixDisabled } ${seller} /> 판매사관리
									</label>
								</div>
<%--							<div style="padding-left:15;padding-top:3px;float:left;width:170px"><input type="checkbox" name="menunm" value="myritz" class="null" ${fixDisabled } ${myritz} />MyRitz관리</div>--%>
								<div style="padding-left:15px;padding-top:3px;padding-bottom:7px;float:left;width:170px">
									<label>
										<input type="checkbox" name="menunm" value="log" ${fixDisabled } ${log2} /> 통계/데이터관리
									</label>
								</div>
							</td>
						</tr>
					</table>
				</c:if>
			</td>
		</tr>
	</table>
	<div class="button_popup">
<%--		<input type="image"  src="/resources/shop/admin/img/btn_confirm_s.gif" />--%>
		<input type="submit" class="admin-btn" value="확인" />

	</div>
</form>


<script>
linecss(document.form);
</script>

<script language="javascript">   /*result값을 어디서 받는지?  팝업 끄고 다시 로드해주는 작업인듯  */

		
function listener(element){
	var a = document.getElementsByClassName('free');
	
	if(element.checked) {
		a[0].value = 'Y';
	} else {
		a[0].value = 'N';
	}
}
</script>