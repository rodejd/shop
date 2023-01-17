<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>결제정보</div>
<table class=tb>
	<col class=cellC><col class=cellL>

	<tr>
		<td>결제종류</td>
		<td>${popupVO.rtData.rSettlekind}</td>
	</tr>
	
	<c:choose>
		<c:when test="${popupVO.rtData.settlekind == 'a' }">
			<tr>
				<td>입금계좌</td>
				<td>
					<select name=bankAccount>
						<c:if test="${popupVO.tmpBank != null and popupVO.tmpBankSize > 0 }">
							<c:forEach items="${popupVO.tmpBank }" var="tmpBank">
								<option value='${tmpBank.sno}'> ${tmpBank.bank} ${tmpBank.account} ${tmpBank.name}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td>입금자</td>
				<td><input type=text name=bankSender value="${popupVO.rtData.banksender}" maxlength="20"></td>
			</tr>
		</c:when>
		<c:when test="${popupVO.rtData.settlekind == 'v'}">
			<tr>
				<td>가상계좌</td>
				<td>${popupVO.rtData.vAccount}</td>
			</tr>
		</c:when>
	</c:choose>
	<tr>
		<td>결제확인일</td>
		<td>
			<font class=ver8><fmt:formatDate value="${popupVO.rtData.cdt}" pattern="yyyy-MM-dd hh:mm aa"/></font>
		</td>
	</tr>
	<c:if test="${popupVO.rtData.cashreceipt != '' }">
		<tr>
			<td>현금영수증번호</td>
			<td>${popupVO.rtData.cashreceipt}</td>
		</tr>
	</c:if>
	<c:if test="${popupVO.rtTaxSize > 0 }">
		<tr>
			<td>세금계산서</td>
			<td>
				<c:choose>
					<c:when test="${popupVO.rtTax.step != '' and popupVO.rtTax.step == '0' }">
						<font color=#007FC8>발행신청</font> - 신청일 : {&{popupVO.rtTax.regdt}}
					</c:when>
					<c:when test="${popupVO.rtTax.step != '' and popupVO.rtTax.step == '1' }">
						<font color="#587E06">발행승인</font>
						<a href="javascript:orderPrint('${popupVO.ordno}','tax')">[세금계산서 인쇄]</a>
						<br>발행액 : ${popupVO.rtTax.price} , 승인일 : ${popupVO.rtTax.agreedt}
					</c:when>
					<c:when test="${popupVO.rtTax.step != '' and popupVO.rtTax.step == '2' }">
						<font color="#2266BB">발행완료</font>
						<a href="javascript:orderPrint('${popupVO.ordno}','tax')">[세금계산서 인쇄]</a>
						<br>발행액 : ${popupVO.rtTax.price} , 완료일 : ${popupVO.rtTax.printdt}
					</c:when>
					<c:when test="${popupVO.rtTax.step != '' and popupVO.rtTax.step == '2' }">
						<div id="tax1"><font color="#2266BB">전자발행</font></div>
						<div id="tax2">발행액 : ${popupVO.rtTax.price} , 요청일 : ${popupVO.rtTax.agreedt}</div>
						<script>getTaxbill('${popupVO.rtTax.docNumber}')</script>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:if>
	<c:if test="${popupVO.rtData.eggyn != 'n'}">
	
		<tr>
			<td>전자보증보험</td>
			<td>
				<c:if test="${popupVO.rtData.eggno != '' }">
					<a href="javascript:popupEgg('<?=$egg['usafeid']?>', '${popupVO.ordno}')"><font class=ver71 color=0074BA><b>${popupVO.rtData.eggno} <font class=small1>(내역서 보기)</b></font></a>
				</c:if>
			aceofmath 결과메세지 미구현....
			<? if ($data[eggno]=="" && $r_settlelog['결과메세지']){ ?><font class=small1 color=FD4700><b>[<?=$r_settlelog['결과메세지']?>]</b></font><? } ?>
			</td>
		</tr>
	</c:if>
</table>