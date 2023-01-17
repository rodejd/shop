<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<!-- Jquery Setting-->
<script language="javascript">

	function goPage(page){
		$("#pageNo").val(page);
		document.frmList.submit();
	}

	
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

	<div class="title title_top">
		정산세부내역리스트입니다. 
		<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=order&no=2',870,800)"><img src="/resources/shop/admin/img/btn_q.gif" border="0" hspace="2" align="absmiddle"></a>-->
	</div>
	
	<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
		<tbody>
			<tr>
				<td valign="top" style="padding-left:12px">
					<form name="frmList" action="acountList">
						<input type="hidden" id="pageNo" name="pageNo" value="1">

						<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
							<colgroup>
								<col class="cellC">
								<col class="cellL">
							</colgroup>
							<tbody>
								<tr>
									<td>검색어</td>
									<td>
									<select name="skey">
										<option value="">전체</option>
										<option value="ordno">주문번호</option>										
										<option value="goodsnm">상품명</option>
									</select>									
										<input type="text" name="search" class="line" style="height:22px">

									</td>
								</tr>								
								<tr>
									<td>기간검색</td>
									<td>								
									<input type="text" name="s_orddt" value="${acountVO.s_orddt[0]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8"> -
									<input type="text" name="s_orddt" value="${acountVO.s_orddt[1]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
									<a href="javascript:setDate('s_orddt',${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle"></a>
									<a href="javascript:setDate('s_orddt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle"></a>
									<a href="javascript:setDate('s_orddt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle"></a>
									<a href="javascript:setDate('s_orddt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle"></a>
									<a href="javascript:setDate('s_orddt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle"></a>
									<a href="javascript:setDate('s_orddt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle"></a>
									</td>
								</tr>
								<tr>
									<td>정산상태</td>
									<td>
									<select name="sflag">
										<option value="">=====</option>
										<option value="W">정산대기</option>										
										<option value="I">정 산 중</option>
										<option value="E">정산완료</option>
									</select>
									</td>	
								</tr>

							</tbody>
						</table>
						<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif"></div>
<!-- 검색 End -->	
					
						<div style="padding-top:15px"></div>
<!-- 표 상단 타이틀 -->
						<div class="title title_top">기간 내 합계</div>
<!-- 표 상단 타이틀 End -->
					</form>
		
					<form id="chkSend" method="post" action="indb" >
<!-- 표 -->
						<table id="" width="100%" cellpadding="0" cellspacing="0" border="0">
							<thead>
								<tr>
									<td class="rnd" colspan="13"></td>
								</tr>
								<tr class="rndbg">
									<th>총 상품 판매액</th>
									<th>총 수수료</th>
									<th>총 배송료</th>
									<th>총 추가배송료</th>
									<th>총 쿠폰 할인액</th>
									<th>총 정산 금액</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td height="4" colspan="13"></td>
								</tr>
								
								<tr height="25">
								<c:forEach var="i" begin="0" end="5" step="1">
									<td class="center"><fmt:formatNumber value="${acountVO.total[i]}" pattern="#,###"/> 원 </td>
								</c:forEach>
								</tr>
								<tr><td height="4"></td></tr>
								<tr><td colspan="15" class="rndline"></td></tr>
							</tbody>
						</table>
					</form>

					<div style="padding-top:15px"></div>
<!-- 표 상단 타이틀 -->
					<div class="title title_top">정산기준 안내</div>
<!-- 표 상단 타이틀 End -->
<!-- 표 상단 옵션 -->
					<table width="100%" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class=pageInfo align="left">
									<font class=ver8>
									<c:set var="pages" value="${(acountVO.rowCount*10) / (acountVO.pageSize*10)} " />
									<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
									<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
									총 <b>${acountVO.totalCnt }</b>개, 검색 <b>${acountVO.rowCount}</b>개, <b>${acountVO.pageNo }</b> of <b>${var3 }</b> Pages
								</td>
								
							</tr>
						</tbody>
					</table>
<!-- 표 상단 옵션 End -->
		
						<input type="hidden" id="statVal" name="statVal" value=""> 
						<input type="hidden" id="mode" name="mode" value=""> 
<!-- 표 -->
						<table class=tb id="acountList" width="100%" cellpadding="4" cellspacing="0" border="0">
								<tr class="rndbg">							
									<th>주문번호</th>
									<th>주문일자</th>
									<th>상품명</th>
									<th>수량</th>
									<th>판매가</th>
									<th>배송료</th>
									<th>추가배송료</th>									
									<th>수수료(%)</th>
									<th>쿠폰할인</th>
									<th>판매금액</th>
									<th>정산금액</th>
									<th>정산상태</th>
									<th>정산일자</th>
								</tr>
								<c:forEach items="${acountVO.acountList}" var="acountList" varStatus="status">
									<tr height="25">					
										<td class="center"><fo-nt class=ver81 color=444444>${acountList.ordno}</td>
										<td class="center"><font class=ver81 color=444444>${acountList.orddt }</td>
										<td class="center"><font class=ver81 color=444444>${acountList.goodsnm}
										<c:set var="addopt" value="${acountList.addopt}" />
											<c:if test="${addopt != null and addopt != '' and addopt != 'NULL'}">
												<%@ include file="../order/_order_detail/orderitem_opt.jsp"%>
											</c:if>
										</td>
										<td class="center"><font class=ver81 color=444444><fmt:formatNumber value="${acountList.ea}" pattern="#,###"/></td>
										<td class="center"><font class=ver81 color=444444><fmt:formatNumber value="${acountList.price }" pattern="#,###"/></td>
										<td class="center"><font class=ver81 color=444444><fmt:formatNumber value="${acountList.deliveryPrice}" pattern="#,###"/></td>
										<td class="center"><font class=ver81 color=444444><fmt:formatNumber value="${acountList.addDeliveryPrice}" pattern="#,###"/></td>									
										<td class="center"><font class=ver81 color=444444>${acountList.fees }% -><fmt:formatNumber value="${acountList.applyfees }" pattern="#,###"/></td>
										<td class="center"><font class=ver81 color=444444><fmt:formatNumber value="${acountList.coupon}" pattern="#,###"/></td>
										<td class="center"><font class=ver81 color=444444><fmt:formatNumber value="${acountList.salePrice }" pattern="#,###"/></td>
										<td class="center"><font class=ver81 color=444444><fmt:formatNumber value="${acountList.accountPrice }" pattern="#,###"/></td>
										<td class="center"><font class=ver81 color=444444>${acountList.accountFlag }</td>
										<td class="center"><font class=ver81 color=444444>${acountList.accountDate}</td>
									</tr>
									</c:forEach>					
								<tr><td colspan="15" class="rndline"></td></tr>
						</table>
						
						<!--// 페이징 -->
						<tags:paginator currentPageNo="${acountVO.pageNo}" rowCount="${acountVO.rowCount}" 
						pageSize="${acountVO.pageSize}"  pageGroupSize="${acountVO.pageGroupSize}" />
				</td>
			</tr>
		</tbody>
	</table>

<div style="padding-top:15px"></div>
<div id="MSG01">
	<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
		<tr><td><img src="/resources/shop/admin/img/arrow_blue.gif" align="absmiddle">위 정산내역자료는 배송완료후 +7일이후 기준입니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/arrow_blue.gif" align="absmiddle">판매금액 : (상품금액*갯수)+옵션가격 + 배송료+추가배송료</td></tr>
		<tr><td><img src="/resources/shop/admin/img/arrow_blue.gif" align="absmiddle">수수료 금액 = ((상품금액*갯수)+옵션가격)x판매사 수수료</td></tr>
		<tr><td><img src="/resources/shop/admin/img/arrow_blue.gif" align="absmiddle">정산금액 : 정산금액 산정기준이 [ 판매금액 - (수수료+판매사 쿠폰할인) ]</td></tr>
		<tr><td><img src="/resources/shop/admin/img/arrow_blue.gif" align="absmiddle">
			정산 기준<br/>
			&nbsp; &nbsp; - 주 : 일~토요일 까지의 배송완료건에 대해서 다음주 일요일 새벽 00시에 정산<br/>
			&nbsp; &nbsp; - 15일 : 1~15일:22일 새벽00시에 정산 , 16~월말: 익월 7일 새벽00시에 정산<br/>
			&nbsp; &nbsp; - 월정산 : 1~월말:익월 7일 새벽00시 정산
		</td></tr>
	</table>
</div>
<script>cssRound('MSG01')</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


		</td>
	</tr>
</table>