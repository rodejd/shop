<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<style>
	.title2 {
		font-weight:bold;
		padding-bottom:5px;
	}
</style>
<script src="/resources/shop/admin/prototype.js"></script>

<table width=100% cellpadding=0 cellspacing=0>
	<tr>
		<td style="padding:5px 10px;background:#f7f7f7;margin:10px 0;border:3px solid #C6C6C6;">
			<table width=100%>
				<tr>
					<td id="orderInfoBox">
						<font class=def>주문번호:</font>
						<span style="color:#000000;font:bold 14px verdana">${printVO.ordno}</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height=8></td>
	</tr>
</table>
<form name=frmOrder method=post>
	<table class="tb" cellpadding="5" cellspacing="0" border="1" bordercolor="#e6e6e6" style="width: 100%; border-collapse: collapse;">
		<tbody>
			<tr height="25" bgcolor="#2E2B29" class="small4" style="padding-top:8px">
				<th><font color="white">번호</font></th>
				<th colspan="2"><font color="white">상품명 / 제품코드</font></th>
				<th><font color="white">수량</font></th>
				<th><font color="white">상품가격</font></th>
				<th><font color="white">판매사<br/>결제가격</font></th>
				<th><font color="white">처리상태</font></th>
			</tr>
		</tbody>
		<colgroup>
			<col align="center" span="2"><col>
			<col align="center" span="9">
		</colgroup>
		<c:choose>
			<c:when test="${printVO.rtList != null && printVO.rtListSize > 0 }">
				<c:forEach items="${printVO.rtList}" var="rt" varStatus="status">
					<tr bgcolor="#ffffff">
						<td width=35 nowrap>
							<font class=ver8 color=444444>
								${status.count}
							</font>
						</td>

						<td width=50 nowrap>
							<img src='/resources/shop/data/upload/goods/${rt.subImgs}' width="40px" height="40px" style="border:1px solid cccccc;" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'"/>
						</td>
						
						<td width=100%>
							<font class=small color=0074BA>${rt.goodsnm}</font>
							<c:set var="addopt" value="${rt.addopt }" />
							<c:if test="${addopt != null and addopt != '' and addopt != 'NULL'}">							
								<%@ include file="./_order_detail/orderitem_opt.jsp"%>
							</c:if>
							
							<div style="padding-top:3">
								<font class=small1 color=6d6d6d>
									브랜드 : ${rt.brandnm}
								</font>
							</div>
						</td>
		
						<!-- 개수 -->
						<td nowrap>${rt.ea}</td>
						
						<!-- 상품가격 -->
						<td nowrap><fmt:formatNumber value="${rt.price}" pattern="#,###"/></td>						
						
						<td width=60 nowrap><!-- 판매사결제가격 -->
							<%-- 기존 소계 ${rt.supply} 이거를 상품+추가옵션 으로 소계를 수정함 --%>
							<fmt:formatNumber value="${(printVO.goodsPrice + printVO.rtData.delivery + printVO.rtData.addDelivery)-(printVO.memberDC+printVO.coupon) }" pattern="#,###"/>
						</td>
						<!-- rt.addoptPrice 추가옵션 가격 가져왔다-->
						<td width=70 nowrap>
							<font class=small4>
								${rt.rIstep}
							</font>
						</td>
						
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr bgcolor="">
					<td colspan="13"> 해당 항목의 주문상품이 존재하지 않습니다. </td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<!-- 개별송장입력 시작 -->
	<c:if test="${'0' != printVO.deliveryBasis }">
		<div style="padding:5px 0 0 12px">
			<c:if test="${printVO.rtData.step > 0}">
				<a href="javascript:registerDelivery()"><img src="/resources/shop/admin/img/btn_input_delinumber.gif" border=0></a>
			</c:if>
		</div>
	</c:if>
	<!-- 개별송장입력 끝 -->
	<div id=layer_cancel style="display:none;padding-top:10px">
		<iframe id=ifrmCancel name=ifrmCancel style="width:100%;height:0;" frameborder=0></iframe>
	</div><p>

	<div class="title2">&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle"><font color="494949">결제금액정보</font></div>

	<table class="tb" cellpadding="5" cellspacing="0" border="1" bordercolor="#e6e6e6" style="width: 100%; border-collapse: collapse;">
	<!--<col class=cellC><col class=cellL width=120><col class=cellL>-->
		<tr>
			<td width=110 align=center bgcolor=#F6F6F6>주문금액</td>
			<td width=110 align=right>
				<font class=ver90><fmt:formatNumber value="${printVO.goodsPrice + printVO.rtData.delivery + printVO.rtData.addDelivery}" pattern="#,###"/></font>원
			</td>
			<td><img src="/resources/shop/admin/img/arrow_gray.gif" align=absmiddle><font class=small color=444444>상품가격</font> (<font color=0074BA class=ver81><fmt:formatNumber value="${printVO.goodsPrice}" pattern="#,###"/></font>원)
				<c:if test="${printVO.rtData.delivery != 0}">
					+ 배송비 (<font color=0074BA class=ver81><fmt:formatNumber value="${printVO.rtData.delivery}" pattern="#,###"/></font>원)
				</c:if>
				<c:if test="${printVO.rtData.addDelivery != 0}">
					+ 추가배송비 (<font color=0074BA class=ver81><fmt:formatNumber value="${printVO.rtData.addDelivery}" pattern="#,###"/></font>원)
				</c:if>
			</td>
		</tr>
		<tr>
			<td align=center bgcolor=#F6F6F6>할인액</td>
			<td align=right><font class=ver90>- <fmt:formatNumber value="${printVO.discount}" pattern="#,###"/></font>원</td>
			<td>
				<img src="/resources/shop/admin/img/arrow_gray.gif" align=absmiddle>
				<font class=small color=444444>
					<c:if test="${printVO.memberDC > 0 }">	
						회원할인 (<font color=0074BA class=ver81><fmt:formatNumber value="${printVO.memberDC}" pattern="#,###"/></font>원)
					</c:if>
	
					<c:if test="${printVO.rtData.coupon > 0}">
						+ 쿠폰할인 (<font color=0074BA class=ver81><fmt:formatNumber value="${printVO.coupon}" pattern="#,###"/></font>원)
					</c:if>
					 <%-- 에누리 <input type=text name=enuri value="${printVO.rtData.enuri }" size=6 class='ver81 right' style='color:#0074BA'> 원 --%>
				</font>
			</td>
		</tr>
		<tr>
			<td align=center bgcolor=#F6F6F6><b>주문상태</b></td>
			<td align=right>
				${shopLibFunction:r_istep(printVO.rtData.istep)}
			</td>
			<td>
			</td>
		</tr>
	</table>
	<p>

	<c:if test="${printVO.tmpRt != null && printVO.tmpRtSize > 0 }">
	
		<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=494949>환불내역정보</font> <font class=small1 color=6d6d6d>아래는 이미 환불완료된 내역입니다</font></div>
		
		<table border=2 bordercolor=#F43400 style="border-collapse:collapse" width=100--%>
			<tr>
				<td>
					<table class=tb cellpadding=4 cellspacing=0>
						<tr>
							<td width=5% align=center bgcolor=#F6F6F6><font class=small1 color=444444><b>번호</b></font></td>
							<td width=20% align=center bgcolor=#F6F6F6><font class=small1 color=444444><b>환불수수료</b></font></td>
							<td width=20% align=center bgcolor=#F6F6F6><font class=small1 color=444444><b>환불금액</b></font></td>
							<td width=20% align=center bgcolor=#F6F6F6><font class=small1 color=444444><b>적립금환불금액</b></font></td>
							<td width=20% align=center bgcolor=#F6F6F6><font class=small1 color=444444><b>환불완료 처리일</b></font></td>
							<td width=15% align=center bgcolor=#F6F6F6><font class=small1 color=444444><b>처리상태</b></font></td>
						</tr>
					<c:forEach items="${printVO.tmpRt}" var="tmpRt" varStatus="status">
						<tr>
							<td  style="padding:2px 10px" rowspan=2 align=center><font class=ver7 color=444444>${status.count}</font></td>
							<td align=center><font class=ver8>${tmpRt.rfee}원</font></td>
					
							<td align=center><font class=ver8 color=EA0095><b>${tmpRt.rprice}</b></font>원</td>
					
							<td align=center><font class=ver8>${tmpRt.remoney}</font>원</td>
							<td align=center><font class=ver81>${tmpRt.ccdt}</font></td>
							<td align=center><font class=small1 color=0074BA><b>환불완료</b></font></td>
						</tr>
					
						<tr>
							<td colspan=3>
								<div style='float:left'>
									<table>
										<c:if test="${tmpRt.orderItemList != null && tmpRt.orderItemListSize > 0}">
											<!--  여기 조건문 다시 확인해볼 필요가 있다. -->
											<c:forEach items="${printVO.tmpRt.orderItemList}" var="orderList">
												<tr>
													<td width=200>
														<div style='text-overflow:ellipsis;overflow:hidden;width:200px' nowrap>
															<font class=small1 color=444444>${orderList.goodsnm }</font>
														</div>
													</td>
													<td width=50 style=padding-left:10>
														<font class=small1 color=444444>${orderList.ea}개</font>
													</td>
												</tr>					
											</c:forEach>
										</c:if>
									</table>
								</div>
							</td>
							<td colspan=2 align=center>
								<font class=small1 color=444444>
									<b>환불계좌</b>: ${tmpRt.bankPro }&nbsp;
									${tmpRt.bankaccount}&nbsp;&nbsp;
									<b>예금주</b>: ${tmpRt.bankuser}
								</font>			
							</td>
						</tr>
					</c:forEach>
					</table>
				</td>
			</tr>
		</table>
		<p>
	</c:if>


	<table width=100% cellpadding=0 cellspacing=0>
		<colgroup>
			<col span="3" valign="top">
		</colgroup>
		<tr>
			<td width="50%">
				<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=494949>주문자정보</font></div>
				<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<col class=cellC>
				<col class=cellL>
					<tr>
						<td>구분/주문자(ID)</td>
						<td>
							${printVO.rtData.nameorder }
							<c:if test="${'' != printVO.rtData.nameorder }">
								<c:if test="${'' != printVO.rtData.mId }">
									/ <font color=0074BA><b>${printVO.rtData.mId}</b></font>
								</c:if>						
							</c:if>
						</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>
							<font class=ver8>${printVO.rtData.email}</font> 
						</td>
					</tr>
					<tr>
						<td>연락처</td>
						<td class=ver8>
							<!--  여기서 php 코드 뺐음 -->
							${printVO.rtData.phoneorder}
							<c:if test="${'' != printVO.rtData.phoneorder and '' != printVO.rtData.mobileorder}">
								/
							</c:if> 
							<c:if test="${'' != printVO.rtData.mobileorder }">
								${printVO.rtData.mobileorder }
							</c:if>
						</td>
					</tr>
					<tr>
						<td>주문일</td>
						<td><font class=ver8><fmt:formatDate value="${printVO.rtData.orddt}" pattern="yy/MM/dd hh:mm a"/></font></td>
					</tr>
				</table>		
			</td>
			<td width=10 nowrap></td>
			<td width="50%">
		
			<div class="title2">&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle"><font color="494949">수령자정보</font></div>
				<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<colgroup>
					<col class="cellC"><col class="cellL">
				</colgroup>
					<tr>
						<td>수령자</td>
						<td>${printVO.rtData.namereceiver}</td>
					</tr>
					<tr>
						<td>연락처</td>
						<td>
							${printVO.rtData.phonereceiver}
							<c:if test="${'' != printVO.rtData.phonereceiver and '' != printVO.rtData.mobilereceiver}">
								/
							</c:if> 
							<c:if test="${'' != printVO.rtData.mobilereceiver }">
								${printVO.rtData.mobilereceiver }
							</c:if>
						</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>
							<!--  두번째 substring 매개변수 확인하기 -->
							<font color="444444">${fn:substring(printVO.rtData.zipcode, 0, 3)} - ${fn:substring(printVO.rtData.zipcode, 3, fn:length(printVO.rtData.zipcode))}</font>
						</td>
					</tr>
					<tr>
						<td colspan="2">${printVO.rtData.address}
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height=15></td>
		</tr>
		<tr>
			<td>
				<div class="title2">&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle"><font color="494949">결제정보</font></div>
				<font color="494949">
					<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
					<colgroup>
						<col class="cellC">
						<col class="cellL">
					</colgroup>			
						<tr>
							<td>결제종류</td>
							<td>${printVO.rtData.rSettlekind}</td>
						</tr>
						<c:choose>
							<c:when test="${'a' == printVO.rtData.settlekind }">
								<tr>
									<td>입금계좌</td>
									<td>
										<select name=bankAccount>
											<c:if test="${printVO.tmpBank != null && printVO.tmpBankSize > 0 }">
												<c:forEach items="${printVO.tmpBank}" var="tmpBank">											
													<option value='${tmpBank.sno}' > 
														${tmpBank.bank} 
														${tmpBank.account} 
														${tmpBank.name}
													</option>
												</c:forEach>
											</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td>입금자</td>
									<td><input type=text name=bankSender value=""></td>
								</tr>
							</c:when>
							<c:when test="${'v' == printVO.rtData.settlekind }">
								<tr>
									<td>가상계좌</td>
									<td>${printVO.rtData.vAccount}</td>
								</tr>
							</c:when>
						</c:choose>
						<tr>
							<td>결제확인일</td>
							<td>
								<font class=ver8><fmt:formatDate value="${printVO.rtData.cdt}" pattern="yy/MM/dd hh:mm a"/></font>
							</td>
						</tr>
						<c:if test="${'' != printVO.rtData.cashreceipt }">
							<tr>
								<td>현금영수증번호</td>
								<td>${printVO.rtData.cashreceipt}</td>
							</tr>
						</c:if>
						
						<c:if test="${printVO.rtTaxSize > 0 }">
							<c:forEach items="${printVO.rtTax }" var="rtTax">
								<tr>
									<td>세금계산서</td>
									<td>
										<!-- eunnjung: 여기서 for문이 여러번 도는지 확인. (원래 for문이 없으나 rtTax 가 List 이므로 foreach  를 사용함.) -->
											<c:if test="${'' != rtTax.step}">
												<c:choose>
													<c:when test="${'0' == rtTax.step}">
														<font color=#007FC8>발행신청</font> - 신청일 : {${rtTax.regdt}}
													</c:when>
													<c:when test="${'1' == rtTax.step}">
														<font color="#587E06">발행승인</font><br>발행액 : ${rtTax.price}, 승인일 : ${rtTax.agreedt}
													</c:when>
													<c:when test="${'2' == rtTax.step}">
														<font color="#2266BB">발행완료</font><br>발행액 : ${rtTax.price}, 완료일 : ${rtTax.printdt}
														<div id="tax1"><font color="#2266BB">전자발행</font></div><div id="tax2">발행액 : ${rtTax.price}<%-- 포매팅 필요 --%> , 요청일 : ${rtTax.agreedt}</div>											
														<script>getTaxbill('${rtTax.docNumber}')</script>
													</c:when>
												</c:choose>
											</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</font>
			</td>
			<td></td>
			<td>
				<div class="title2">&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle"><font color="494949">배송정보</font></div>
				<table border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
					<colgroup><col class="cellC"><col class="cellL"></colgroup>			
					<tr>
						<td class="cellC cellM">판매사</td>
						<td class="cellC cellM">상품명</td>
						<td class="cellC cellM">송장번호</td>
						<td class="cellC cellM">배송일</td>
					</tr>
					<c:forEach items="${printVO.delivery}" var="delivery" varStatus="status">
						<tr>
							<td>
								${delivery.sellerNm}
							</td>
							<td>
								${delivery.goodsnm }
							</td>
							<td>
								<c:choose>
									<c:when
										test="${39 < delivery.istep && delivery.istep ne 55 && delivery.istep ne 60 && delivery.istep ne 61 && delivery.istep ne 62}">
										<font class=small1 color=444444>주문상태 :${shopLibFunction:r_istep(delivery.istep)}</font>
									</c:when>
									<c:otherwise>
										<c:forEach items="${delivery.deliveryCompList}" var="deliCompList" varStatus="status">
											<c:if test="${delivery.deliveryCompCd eq deliCompList.deliveryno}">
												${deliCompList.deliverycomp}&nbsp;:&nbsp;${delivery.invoice}
											</c:if>
										</c:forEach>
									</c:otherwise>
									
								</c:choose>
							</td>
							<td>
								${dateUtil:formatDate(delivery.deliveryEdt, "yy-MM-dd HH:mm:ss a")}
							</td>
						</tr>
					</c:forEach>
				</table>
				<%-- <font color="494949">
					<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
					<col class=cellC><col class=cellL>
						<tr>
							<td>송장번호</td>
							<td>
								<c:forEach items="${printVO.delivery}" var="delivery" varStatus="status">
									${delivery.goodsnm}&nbsp;${delivery.deliveryCompCd}&nbsp;:&nbsp;${delivery.invoice}<br>
								</c:forEach>
								<c:choose>
									<c:when test="${printVO.rtData.istep >= 1 && printVO.rtData.istep < 4}">
										<select name=deliveryno>
											<option value="">==택배사==
											${printVO.deliSelectOption}
										</select>
										<input type=text name=deliverycode value="${printVO.rtData.deliverycode}" class=line>
									</c:when>
									<c:otherwise>
										<font class=small1 color=444444>아래 배송상태추적 버튼을 눌러 확인하세요.</font>
										<input type=hidden name='deliveryno' value='${printVO.rtData.deliveryno}'>
										<input type=hidden name='deliverycode' value='${printVO.rtData.deliverycode}'>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
												
						<tr>
							<td>배송일(출고일)</td>
							<td><font class=ver8><fmt:formatDate value="${printVO.rtData.ddt}" pattern="yy/MM/dd hh:mm a"/></font></td>
						</tr>
						
						<c:if test="${'' != printVO.rtData.confirmdt && null != printVO.rtData.confirmdt}">
							<tr>
								<td>배송완료일</td>
								<td><fmt:formatDate value="${printVO.rtData.confirmdt}" pattern="yy/MM/dd hh:mm a"/>(${printVO.rtData.confirm})</td>
							</tr>
						</c:if>
						<c:if test="${'y' == printVO.rtData.escrowyn}"><!-- 에스크로 배송 확인 -->
							<tr>
								<td>에스크로</td>
								<td>
									<c:choose>
										<c:when test="${'' != printVO.rtData.escrowconfirm }">
											배송확인요청
										</c:when>
										<c:when test="${'1' == printVO.rtData.escrowconfirm }">
											배송요청중
										</c:when>
										<c:when test="${'2' == printVO.rtData.escrowconfirm }">
											배송완료
										</c:when>							
									</c:choose>
								</td>
							</tr>
						</c:if>
					</table>
				</font>	 --%>
			</td>
		</tr>
		<tr>
			<td height=15></td>
		</tr>
		<tr>
			<td>	
				<div class=title2>
				<span style="padding-right:10px">&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle"><font color="494949">요청사항/상담메모</font></span>
				<font color="494949"></font>
				</div>
				<font color="494949">
				<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
					<colgroup>
							<col class="cellC">
							<col class="cellL">
					</colgroup>
					<tr height=25>
						<td>고객요청사항</td>
						<td>${printVO.rtData.memo}</td>
					</tr>
					<tr height=25>
						<td>고객상담메모</td>
						<td>${printVO.rtData.adminmemo}</td>
					</tr>
					<tr height=25>
						<td>결제로그</td>
						<td><textarea style="width:100%;height:100px;overflow:visible;font:9pt 굴림체;padding:10px 0 0 8px">${printVO.rtData.settlelog}</textarea></td>
					</tr>
				</table>
		
			</td>
			<td></td>
			<td>
				<div class="title2">&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle"><font color="494949">취소요청 리스트</font></div><font color="494949">
				<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
					<colgroup>
						<col class="cellC">
						<col class="cellL">
					</colgroup>
					
					<c:if test="${printVO.orderCancelList != null && printVO.orderCancelListSize > 0}">
						<c:forEach items="${printVO.orderCancelList}" var="orderCancel">
							<tr>
								<td><font class=small>${orderCancel.name}</font></td>
								<td>
									<div style="float:left" class=ver8><font color="444444"><fmt:formatDate value="${orderCancel.regdt}" pattern="yy/MM/dd hh:mm a"/></font></div>
									<div style="float:right">
										<c:choose>
											<c:when test="${0 == orderCancel.code}">
												주문취소복원
											</c:when>
											<c:otherwise>
												${orderCancel.cancelCode}
											</c:otherwise>
										</c:choose>
									</div>
								</td>
							</tr>
						
							<tr>
								<td colspan=2 bgcolor=#ffffff align=left style="padding:5px">
									<table width=100%> 
										<c:forEach items="${orderCancel.tmpRt12}" var="tmpRt12">
											<tr bgcolor=#f7f7f7>
												<td>- ${tmpRt12.goodsnm} <%-- <%//=tmp_rt_1.get(j, "ea")%> --%>개</td>
												<td align=right>${tmpRt12.rStepi}</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan=2 bgcolor=#ffffff align=left style="padding:5px">
									<c:if test="${'' != orderCancel.memo}">
										<font color=555555>${orderCancel.memo}</font>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</td>
		</tr>
	</table>

</form>