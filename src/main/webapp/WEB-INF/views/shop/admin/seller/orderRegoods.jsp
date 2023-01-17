<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">

</script>

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

<div class="title title_top">반품/교환접수리스트<span>반품/교환 접수된 주문건을 조회하고 반품/교환완료처리를 진행합니다.</span> <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=order&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> -->
</div>

<form method="post" action="orderIndb">
	<input type="hidden" name="mode" value="regoods">

	<table width="100%" cellpadding="2" cellspacing="0">
		<tr>
			<td class="rnd" colspan="10"></td>
		</tr>
		<tr class="rndbg">
			<th><a href="javascript:chkBox(document.getElementsByName('chk'),'rev')" class="white">선택</a></th>
			<th><font class="small1"><b>주문일</b></font></th>
			<th><font class="small1"><b>반품요청일</b></font></th>
			<th><font class="small1"><b>주문번호</b></font></th>
			<th><font class="small1"><b>반품사유</b></font></th>
			<th><font class="small1"><b>주문자</b></font></th>
			<th><font class="small1"><b>담당자</b></font></th>
		</tr>
		<col align="center" span="10">
		
		<c:forEach items="${regoodsVO.regoodsList }" var="list" varStatus="vnum">
		
		<tr>
			<td class="noline"><input type="checkbox" name="chk" value="${list.sno}"></td>
			<td><font class="ver7" color="444444"><fmt:formatDate value="${list.orddt}" pattern="yyyy-MM-dd hh:mm"/></font></td>
			<td><font class="ver7" color="444444"><fmt:formatDate value="${list.canceldt}" pattern="yyyy-MM-dd hh:mm"/></font></td>
			<td><a href="javascript:popup('${ctx}/shop/admin/seller/sellerPopupOrder?ordno=${list.ordno}',800,600)"><font class="ver71" color="0074BA"><b>${list.ordno}</b></font></a></td>
			<td>
				<font class="small1" color="444444">
				${codeUtil:getCodeName('cancel',list.code) }
				</font>
			</td>
			<td>
			
			<c:choose>
				<c:when test="${list.mid != null }">
					<span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}">
					<font class="small1" color="0074BA">${list.nameOrder}</font></span>
				</c:when>
				<c:otherwise>
					<font class="small1">${list.nameOrder}</font>
				</c:otherwise>
			</c:choose>
				
			</td>
			<td><font class="small1" color="444444">${list.nameCancel}</font></td>
		</tr>
		<tr>
			<td colspan="10" class="rndline"></td>
		</tr>
		<tr>
			<td colspan="10" style="padding:5px 10px" align="left">
				<table width="100%" border="1" bordercolor="#dedede" style="border-collapse:collapse">
					<tr bgcolor="#f7f7f7" height="22">
						<th><font class="small1" color="444444"><b>상품명</b></font></th>
						<th width="80" nowrap><font class="small1" color="444444"><b>상품가격</b></font></th>
						<th width="80" nowrap><font class="small1" color="444444"><b>회원할인</b></font></th>
						<th width="80" nowrap><font class="small1" color="444444"><b>쿠폰할인</b></font></th>
						<th width="80" nowrap><font class="small1" color="444444"><b>상품결제단가</b></font></th>
						<th width="50" nowrap><font class="small1" color="444444"><b>수량</b></font></th>
					</tr>
					<col><col align="center" span="10">
					
					<c:forEach items="${list.infoList }" var="infoList">
					<tr>
						<td>
							<table>
								<tr>
									<td>
										<a href="${ctx}/shop/goods/goods_view?goodsno=${infoList.goodsno}" target="_blank">
										<img src='/resources/shop/data/upload/goods/${fn:substringAfter(infoList.img_s, "|")}' width="20px" height="20px" style="border: 1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" />
										</a>
									</td>
									<td style="padding-left:3px"><font class="small" color="444444">
										<a href="${ctx}/shop/goods/goods_view?goodsno=${infoList.goodsno}" target="_blank">
										<font color=0074BA><b>${infoList.goodsnm} [판매사 : ${infoList.sellerNm }]</b></font></a></font>
											<c:if test="${infoList.opt1 }">
												[ ${infoList.opt1 }
												<c:if test="${infoList.opt2 }">
												/ ${infoList.opt2 }
												</c:if>
												]
											</c:if>
											
											<c:if test="${infoList.addopt }">
											<div>[ ${infoList.addopt } ]</div>
											</c:if>
									</td>
								</tr>
							</table>
						</td>
						<td><font class="ver7" color="444444">${stringUtil:getMoneyFormatInteger(infoList.price)}</font></td>
						<td><font class="ver7" color="444444">${stringUtil:getMoneyFormatInteger(infoList.memberdc)}</font></td>
						<td><font class="ver7" color="444444">${stringUtil:getMoneyFormatInteger(infoList.coupon)}</font></td>
						<td><font class="ver7" color="444444">${stringUtil:getMoneyFormatInteger(infoList.price-list.memberdc-list.coupon)}</font></td>
						<td><font class="ver7" color="444444">${stringUtil:getMoneyFormatInteger(infoList.ea)}</font></td>
					</tr>
					</c:forEach>

				</table>
			</td>
		</tr>
		<tr>
			<td colspan="10" class="rndline"></td>
		</tr>
		</c:forEach>
	</table>


	<!-- 페이징  -->
<div align=center class=pageNavi>
	<b><tags:paginator currentPageNo="${regoodsVO.pageNo}" rowCount="${regoodsVO.rowCount}" pageSize="${regoodsVO.pageSize}"  pageGroupSize="${regoodsVO.pageGroupSize}" /></b>
</div>

<div class="button">
<input type="image" src="/resources/shop/admin/img/btn_returngood.gif" onclick="return isChked(document.getElementsByName('chk'),'정말로 반품처리를 하시겠습니까?')">
<input type="image" src="/resources/shop/admin/img/btn_exchangegood.gif" onclick="document.getElementsByName('mode')[0].value='exc_ok';return isChked(document.getElementsByName('chk'),'정말로 교환처리를 하시겠습니까?')">
</div>

</form>


<div id="MSG01">
	<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
		<tr><td><b>:: 반품완료 처리하기 ::</b></td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">반품주문이란 '배송중'이거나 '배송완료'인 상태에서 고객의 요청으로 주문취소가 된 주문을 말합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">주문취소가 되었으므로 배송된 상품을 다시 반송받은 후 상품을 확인하고 반품완료처리를 실행합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">이곳에서 반품완료처리된 주문은 환불접수리스트'로 이동하게 되고 최종적으로 환불을 완료해야만 반품건에 대한 마무리가 이루어집니다. </td></tr>

		<tr><td height="10"></td></tr>

		<tr><td><b>:: 교환완료 후 재주문넣기 ::</b></td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">교환은 맞교환만 가능합니다. 맞교환이란 파손된 불량상품이거나 하자가 있는 상품의 경우 같은 상품으로의 교환처리를 말합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">교환을 요청한 고객으로부터 배송된 상품을 반송받아 이곳에서 '교환완료 후 재주문넣기' 버튼을 눌러 처리합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">'교환완료 후 재주문넣기' 버튼을 누르게되면 자동으로 동일 상품, 동일 구매자의 재주문이 생성됩니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">이렇게 생성된 재주문은 주문리스트에서 <img src="/resources/shop/admin/img/icon_twice_order.gif"> (재주문아이콘)으로 표시되어 주문접수되며, 바로 입금확인 처리후 재배송을 진행하면 됩니다.</td></tr>
	</table>
</div>
<script>cssRound("MSG01")</script>


<script>window.onload = function(){ UNM.inner();};</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
		</td>
	</tr>
</table>

