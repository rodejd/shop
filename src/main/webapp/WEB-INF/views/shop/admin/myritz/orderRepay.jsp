<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
<%-- 2017-09-06 : 함수 내에서 사용하지 않는 매개변수 price 삭제 --%>
function cal_repay(repayfee,repay,i){
	
	if(!repay) var tmp = 0;
	else var tmp = repay - repayfee;

	document.getElementsByName('repays')[i].value=tmp;
	
	//document.getElementsByName('remoneys')[i].value=price - tmp;

	
	if(tmp < 0){
		alert('실결재금액보다 환불수수료가 큰 환불건이 있습니다.');
		document.getElementsByName('repayfees')[i].value="${shopLibFunction:getProperty('minrepayfee')}";
		return;
	}
	document.getElementById('viewrepay'+i).innerHTML = '₩' + comma(tmp);
	
}
function formSubmit(){
	if (!isChked(document.getElementsByName('chks'))) return;
	if (!confirm('정말로 환불처리를 하시겠습니까?')) return;
	document.form01.submit();
}
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	
});
</script>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

		<div class="title title_top">환불접수리스트 <span>환불접수된 주문건을 조회하고 환불완료처리합니다</span> <!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=order&no=5',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle" hspace="2"></a>--></div>

		<form method="post" action="repay/indb" id="form01" name="form01">
			<input type="hidden" name="mode" value="repay">
		
			<table width="100%" cellpadding="2" cellspacing="0">
				<tr>
					<td class="rnd" colspan="10"></td>
				</tr>
				<tr class="rndbg">
					<th><a href="javascript:chkBox(document.getElementsByName('chks'),'rev')" class=white>선택</a></th>
					<th><font class="small1"><b>주문일</b></font></th>
					<th><font class="small1"><b>주문취소일</b></font></th>
					<th><font class="small1"><b>주문번호</b></font></th>
					<th><font class="small1"><b>주문자</b></font></th>
					<th><font class="small1"><b>처리자</b></font></th>
					<th><font class="small1"><b>취소수량/주문수량</b></font></th>
					<th><font class="small1"><b>결제수단</b></font></th>
				</tr>
				<c:forEach items="${repayVO.repayList}" var="repayList" varStatus="status">
				<col align="center" span="10">
				<tr><td colspan="10" class="rndline"></td></tr>
				<tr>
					<td class="noline">
						<input type="checkbox" name="chks" value="${status.index}">
					</td>
					<td>
						<font class="ver71" color="444444">${dateUtil:formatDate(repayList.oRorddt, "yy-MM-dd hh:mm:ss a")}</font>
					</td>
					<td>
						<font class="ver71" color="444444">${dateUtil:formatDate(repayList.mBregdt, "yy-MM-dd hh:mm:ss a")}</font>
					</td>
					<td>
						<a href="javascript:popup('${ctx}/shop/admin/myritz/myritzPopupOrder?ordno=${repayList.oCordno}',800,600)"><font class="ver71" color="0074BA"><b>${repayList.oCordno}</b></font></a>
					</td>
					<td>
						<span id="navig" name="navig" m_id="${repayList.mBmid }" m_no="${repayList.mBmNo }"><font class="small1" color="0074BA">${repayList.oRnameorder}</font></span>
					</td>
					<td>
						<font class="small1" color="444444">${repayList.oCname}</font>
					</td>
					<td>
						<font class="ver7" color="444444">${repayList.oIea}/${repayList.ccnt}</font>
					</td>
					<td>	
						<font class="small1" color="444444">${shopLibFunction:r_settlekind(repayList.oRsettlekind)}</font>
					</td>
				</tr>
				<tr>
					<td colspan="10" style="padding:5px 10px" align="left">
						<table width="100%" border="1" bordercolor="#dedede" style="border-collapse:collapse">
							<tr bgcolor="#f7f7f7" height="20">
								<th width="14%"><font class="small1" color="444444"><b>주문금액</b></font></th>
								<th width="14%" nowrap><font class="small1" color="444444"><b>배송료</b></font></th>
								<th width="14%" nowrap><font class="small1" color="444444"><b>회원할인</b></font></th>
								<%-- <th width="14%" nowrap><font class="small1" color="444444"><b>에누리</b></font></th> --%>
								<th width="14%" nowrap><font class="small1" color="444444"><b>쿠폰</b></font></th>
								<th width="14%" nowrap><font class="small1" color="444444"><b>결제시 사용한 적립금</b></font></th>
								<%-- <th width="14%" nowrap><font class="small1" color="444444"><b>보증보험수수료</b></font></th> --%>
								<th width="16%" nowrap><font class="small1" color="444444"><b>총 결제금액</b></font></th>
							</tr>
							<col align="center" span="10">
							<tr>
								<td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oRgoodsprice}" pattern="#,###.##"/></font></td>
								<td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oRdelivery}" pattern="#,###.##"/></font></td>
								<td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oImemberdc}" pattern="#,###.##"/></font></td>
								<%-- <td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oRenuri}" pattern="#,###.##"/></font></td> --%>
								<td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oIcoupon}" pattern="#,###.##"/></font></td>
								<td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oRemoney}" pattern="#,###.##"/></font></td>
								<%-- <td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oReggFee}" pattern="#,###.##"/></font></td> --%>
								<td><font class="ver7" color="444444">₩<fmt:formatNumber value="${repayList.oRsettleprice}" pattern="#,###.##"/></font></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td colspan="10" class="rndline"></td></tr>
				<tr>
					<td colspan="10" style="padding:5px 10px" align="left">
						<table width="100%" border="1" bordercolor="#dedede" style="border-collapse:collapse">
							<tr bgcolor="#f7f7f7" height="20">
								<th><font class="small1" color="444444"><b>상품명</b></font></th>
								<th width="80" nowrap><font class="small1" color="444444"><b>판매가격</b></font></th>
								<th width="80" nowrap><font class="small1" color="444444"><b>회원할인</b></font></th>
								<th width="80" nowrap><font class="small1" color="444444"><b>쿠폰할인</b></font></th>
								<th width="80" nowrap><font class="small1" color="444444"><b>상품결제단가</b></font></th>
								<th width="50" nowrap><font class="small1" color="444444"><b>수량</b></font></th>
							</tr>
							<col><col align="center" span="10">
							<tr>
								<td>
									<table>
										<tr>
											<td style="padding-left:3px">
											<c:set var="subCatetory" value="${fn:substring(repayList.gLcategory, 0, 3) }" />
											<c:choose>
												<c:when test="${subCatetory eq '001' }">
													<a href="${ctx}/shop/goods/goods_view?goodsno=${repayList.oIgoodsno}" target="_blank"> 
													<font class="small" color="0074BA">${repayList.oIgoodsnm} [판매사 : ${repayList.myritzNm }]</font>	
													<font class="small1" color="0074BA"><b>[보기]</b></font>
												</a>
												</c:when>
												<c:otherwise>
													<a href="${ctx}/shop/goods/goods_view?goodsno=${repayList.oIgoodsno}" target="_blank"> 
														<font class="small" color="0074BA">${repayList.oIgoodsnm} [판매사 : ${repayList.myritzNm }]</font>	
														<font class="small1" color="0074BA"><b>[보기]</b></font>
													</a>
												</c:otherwise>
											</c:choose>
											</td>
										</tr>
									</table>
								</td>
								<td><font class="ver7" color="444444"><fmt:formatNumber value="${repayList.oIprice}" pattern="#,###.##"/></font></td>
								<td><font class="ver7" color="444444"><fmt:formatNumber value="${repayList.oImemberdc}" pattern="#,###.##"/></font></td>
								<td><font class="ver7" color="444444"><fmt:formatNumber value="${repayList.oIcoupon}" pattern="#,###.##"/></font></td>
								<td><font class="ver7" color="0074BA"><b><fmt:formatNumber value="${repayList.oRgoodsprice}" pattern="#,###.##"/></b></font></td>
								<td><font class="ver7" color="444444">${repayList.oIea}</font></td>
							</tr>
						</table>
			    		<div style="padding-top:3px;"></div>
		   				<div align="center"><b>환불 계좌 : </b>
			   				<select name="bankcodes" required>
			   					<option value="">==선택==  
			   					<c:set var="codeitemList" value="${shopLibFunction:codeitemMap('bank') }"/>
			   					<c:set var="bankcode" value="${stringUtil:lpad(repayList.oCbankcode, 2, '0')}"/>
			   					${webUtil:makeSelectOption(codeitemList,'itemcd', 'itemnm', bankcode)}
							</select>
							<input type="text" name="bankaccounts" value="${repayList.oCbankaccount}" maxlength="20" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)">
							<font class="ver71" color="444444">예금주 <input type=text name="bankusers" value="${repayList.oCbankuser}" maxlength="20"></font>
						</div>
		    			<div style="padding-top:3px"></div>
						<table width="100%" border="1" bordercolor="#dedede" style="border-collapse:collapse">
							<tr bgcolor="#f7f7f7" height="20">
								<th width="33%" nowrap><font class="small1" color="444444"><b>환불예정금액(총 결제금액)</b></font></th>
								<th width="33%" nowrap><font class="small1" color="444444"><b>환불수수료</b></font> <a href="javascript:popupLayer('../basic/popup_refundFee',600,300)"><img src="/resources/shop/admin/img/btn_repay_price.gif" border="0"></a></th>
								<th width="34%" nowrap><font class="small1" color="444444"><b>최종 환불금액</b></font> ( = 실결제금액 - 환불수수료)</th>
							</tr>
							<col><col align="center" span="10">
								<input type="hidden" name="m_nos" style="background:#e3e3e3" value="${repayList.oRmNo}" readonly>
								<input type="hidden" name="snos" style="background:#e3e3e3" value="${repayList.oCsno}" readonly>
								<input type="hidden" name="ordnos" style="background:#e3e3e3" value="${repayList.oCordno}" readonly>
							<tr>
								<td align="center"><font class="ver7" color="0074BA"><b>₩<fmt:formatNumber value="${repayList.oRsettleprice}" pattern="#,###.##"/></b></font></td>
								<c:set var="minrepayfee" value="${shopLibFunction:getProperty('minrepayfee')}"/>
								<td><font class="ver7" color="424242">₩<input type="text" name="repayfees" class="noline" value="${minrepayfee eq '' ? 0 : minrepayfee}" onchange="cal_repay(this.value,${repayList.oRsettleprice},${status.index})" onkeydown="onlynumber()" style="text-align=right;background:#E9FFB3"></font></td>
								<td bgcolor="E9FFB3"><input type="hidden" name="repays" style="background:#DEFD33" value="" style="text-align=right" readonly><div style="font-weight:bold;color:#FD3C00;" id="viewrepay${status.index}"></div></td>
							</tr>
							<script>cal_repay(${minrepayfee eq '' ? 0 : minrepayfee},${repayList.oRsettleprice},${status.index});</script>
						</table>
						<div align="center" style="padding-top:5">이 주문결제시 사용한 적립금은 총 <font color="0074BA"><b>₩<fmt:formatNumber value="${repayList.oRemoney}" pattern="#,###.##"/></b></font> 입니다.&nbsp;&nbsp;결제시 사용한 적립금 중 <input type="text" name="remoneys" style="text-align=right;background:#E9FFB3" onkeydown="onlynumber();" value="0">원을 되돌려줍니다.</div>
						<c:set var="rtList3" value="${repayList.oiocObject}"/>
						<c:if test='${rtList3.remoney ne 0}'>
							<div align="center" style="padding-top:5">현재까지 이 취소주문으로 되돌려준 적립금은 총 <font color="0074BA"><b>₩<fmt:formatNumber value="${rtList3.remoney}" pattern="#,###.##"/></b></font> 입니다.</div>
						</c:if>	
					</td>
				</tr>
				<tr><td colspan="10" bgcolor="#616161" height="3"></td></tr>
				<tr><td colspan="10" height="10"></td></tr>
				</c:forEach>
			</table>
		
<!-- 페이징  -->
	<tags:paginator currentPageNo="${repayVO.pageNo}" rowCount="${repayVO.rowCount}" pageSize="${repayVO.pageSize}"  pageGroupSize="${repayVO.pageGroupSize}" />
		
			<div class="button">
				<a href="javascript:formSubmit()"><img src="/resources/shop/admin/img/btn_refund.gif"></a>
			</div>
		</form>
		
		<div id="MSG01">
			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">입금한 상태에서 주문을 취소하거나 이미 배송되어 반품시 발생하는 환불건에 대해 완료처리하는 영역입니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">주문취소와 반품완료처리를 통해 환불접수된 주문건이 환불접수리스트에 보입니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">환불접수건을 확인하고 고객의 통장으로 환불금액을 입금합니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">환불입금이 완료된 해당 주문건을 선택한 후 환불완료처리합니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">최종 환불금액이란 실결제금액에서 환불수수료를 제한 금액을 말합니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">환불수수료란 반품으로 인해 발생된 반송비용 및 기타 수수료를 의미하며, 기본값을 설정할 수 있습니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">적립금으로 결제한 경우 환불적립금을 정산하여 재적립 해주어야 합니다.</td></tr>
			</table>
		</div>
		<script>cssRound("MSG01");</script>
		
		
		<script>window.onload = function(){ UNM.inner();};</script>
		
		<script>
		linecss();
		table_design_load();
		</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>
