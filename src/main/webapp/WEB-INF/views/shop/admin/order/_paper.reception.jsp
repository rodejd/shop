<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<style type="text/css">
td, th { font-family: 돋움; font-size: 9pt; color: 333333;}

#cssblue { width: 306px; border: solid 2px #364f9e;  }
#cssblue table { border-collapse: collapse; }
#cssblue td { border-color:#364f9e; border-width:2px; border-style:solid; }

#cssblue #head { border-color:#364f9e; border-width:2px 2px 0px 2px; border-style:solid; }
#cssblue #head td { border-width:0px; border-style:solid; }

#cssred { width: 306px; border: solid 2px #ff4633;  }
#cssred table { border-collapse: collapse; }
#cssred td { border-color:#ff4633; border-width:2px; border-style:solid; }

#cssred #head { border-color:#ff4633; border-width:2px 2px 0px 2px; border-style:solid; }
#cssred #head td { border-width:0px; border-style:solid; }
</style>

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

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0"><tr><td valign="top" style="padding-left:12px">
	<font color="#5B5B5B"><font class="small1">
	<table cellspacing="10" cellpadding="0" border="0" align="center"><tbody><tr valign="top">
		<c:forEach begin="0" end="1" step="1" varStatus="status">
			<td>
				<div id="${printVO.classids[status.index]}">
					<table id="head" cellspacing="0" cellpadding="0" width="100%" border="0">
						<tbody>
							<tr>
								<td width="23%" height="40">&nbsp;</td>
								<td align="middle" width="44%">&nbsp;<font size="4">영 수 증</font></td>
								<td width="33%"><font style="font-weight: normal" size="1">( ${printVO.headuser[status.index]} )</font></td>
							</tr>
						</tbody>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody><tr>
							<td height="100%" valign="top" style="border-width: 3px 1px 0 0; background: url(/resources/shop/data/skin/season2/img/common/seal.JPG) no-repeat; background-position: 235px 15px;">								<table cellspacing="0" cellpadding="2" width="100%" border="0">
									<colgroup>
										<col width="8%"><col width="20%"><col width="30%"><col width="12%">
									</colgroup>
									<tbody>
										<tr>
											<td valign="bottom" colspan="2">no.</td>
											<td style="border-top-width: 0px;" align="right" colspan="3">&nbsp;<font style="font-weight: normal; font-size: 12pt" color="black">${printVO.orderItem0.nameorder}&nbsp;&nbsp;</font><font size="3">귀하</font>&nbsp;</td>
										</tr>
										<tr align="middle">
											<td rowspan="4" height="100%">공<br><br>급<br><br>자</td>
											<td>사 업 자<br>등록번호</td>
											<td colspan="3" align="left" style="padding-left:10">&nbsp;<font size="3">${printVO.compSerial}</font></td>
										</tr>
										<tr align="middle" height="30">
											<td>상 호</td>
											<td>&nbsp;${printVO.compName}</td>
											<td>성명</td>
											<td>&nbsp;${printVO.ceoName}</td>
										</tr>
										<tr align="middle">
											<td>사 업 장<br>소 재 지</td>
											<td colspan="3">&nbsp;${printVO.address} </td>
										</tr>
										<tr align="middle" height="30">
											<td>업태</td>
											<td>&nbsp;${printVO.service}</td>
											<td>종목</td>
											<td>&nbsp;${printVO.item}</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr></tbody>
					</table>
					<table cellspacing="0" cellpadding="2" width="100%" border="0"><tbody>
						<tr align="middle">
							<td style="border-top-width: 0px;">작성년월일</td>
							<td style="border-left-width: 3px; border-right-width: 3px;">공급대가총액</td>
							<td style="border-top-width: 0px;">비 고</td>
						</tr>
						<tr align="middle">
							<td>&nbsp; ${printVO.orderItem0.formatOrddt}</td>
							<td style="border-left-width: 3px; border-right-width: 3px; border-bottom-width: 4px;">&nbsp;${printVO.itemPrice}</td>
							<td align="right"></td>
						</tr>
					</tbody></table>
					<table cellspacing="0" cellpadding="4" width="100%" border="0"><tbody>
						<tr align="middle">
							<td style="border-top-width: 0px; border-bottom-width: 0px;">위 금액을 정히 영수( 청구 )함</td>
						</tr>
					</tbody></table>
					<table cellspacing="0" cellpadding="2" width="100%" border="0"><tbody>
						<tr align="middle">
							<td>월</td>
							<td>일</td>
							<td>품 목</td>
							<td>수량</td>
							<td>단가</td>
							<td>금액</td>
						</tr>
						<c:if test="${fn:length(printVO.orderItemList) > 0 }">
							<c:forEach items="${printVO.orderItemList}" var="orderItem" varStatus="status"> 
								<tr>
									<td align="middle">${fn:substring(orderItem.formatOrddt, 5, 7)}</td>
									<td align="middle">${fn:substring(orderItem.formatOrddt, 8, 10)}</td>
									<td height="20">
										${orderItem.goodsnm}
										<c:if test="${fn:trim(orderItem.optname) != '' and orderItem.optname != null}">
												[상품옵션 : ${orderItem.optname}]
										</c:if>
									</td>
									<td align="middle">${orderItem.ea}</td>
									<td align="right"><fmt:formatNumber value="${printVO.price[status.index]}" pattern="#,###"/></td>
									<td align="right"><fmt:formatNumber value="${printVO.price[status.index] * orderItem.ea}" pattern="#,###"/></td>
								</tr>
							</c:forEach>
						</c:if>
						<tr>
							<td align="middle" colspan="6">*** 이 하 여 백 *** </td>
						</tr>
						<c:forEach begin="1" end="9" step="1">
							<tr align="middle">
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td align="right">&nbsp;</td>
							</tr>
						</c:forEach>
					</tbody></table>
					<table cellspacing="0" cellpadding="4" width="100%" border="0">
						<tbody>
							<tr align="middle">
								<td style="border-top-width: 0px;" height="25"><font style="font-weight: normal" size="1">부가가치세법시행규칙 제25조 규정에 의한 ( 영수증 )으로 개정.</font></td>
							</tr>
						</tbody>
					</table>
				</div>
			</td>
		</c:forEach>
	</tr></tbody></table>

<script>
table_design_load();
//window.print();
</script>
</td></tr></table>