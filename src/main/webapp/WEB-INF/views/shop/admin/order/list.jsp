<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language="javascript" src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	$(function(){
		var schStep1 = "${orderVO.step}";
		var schStep2 = "${orderVO.step2}";
		
		//alert(schStep1 + "/" + schStep2);
		
		if(schStep1 != ""){
			var schStepArr = schStep1.split(",");
			for(var i=0; i < schStepArr.length; i++){
				$("input[name=step][value="+schStepArr[i]+"]").prop("checked",true);
			}
		}
		if(schStep2 != ""){
			var schStepArr = schStep2.split(",");
			for(var i=0; i < schStepArr.length; i++){
				$("input[name=step2][value="+schStepArr[i]+"]").prop("checked",true);
			}
		}
	});
	

	/*
	 * php script
	 */
	function iciSelect(obj) {
		var row = obj.parentNode.parentNode;
		row.style.background = (obj.checked) ? "#F9FFF0" : row
				.getAttribute("bg");
	}

	function chkBoxAll(El, mode) {
		if (!El || !El.length)
			return;
		for (var i = 0; i < El.length; i++) {
			if (El[i].disabled)
				continue;
			El[i].checked = (mode == "rev") ? !El[i].checked : mode;
			iciSelect(El[i]);
		}
	}
	
	function chkBox2(El,s,e,mode){
		if (!El || !El.length)
			return;
		for(var i=s;i<e;i++){
			if (El[i].disabled)
				continue;
			El[i].checked = (mode == "rev") ? !El[i].checked : mode;
			iciSelect(El[i]);
		}
	}
	
	function popLayerCallback() {
		// 팝업 내의 박스 라운드 처리 호출
		cssRound("MSGPOP01","#F7F7F7");
	}
	/*
	* DOWNLOAD 항목설정 팝업 저장처리
	* popup.orderxls.jsp 에서 사용
	*/
	function submitPopForm(){
		if ( isChked(document.getElementsByName('cfgChk')) ) {
			// 체크여부에 따름 value 설정 (y/n)
			$('input:checkbox[name="cfgChk"]').each(function(){
				if ($(this).prop('checked')) {
					$(this).val('y');
				} else {
					$(this).val('n');
				}
			});
			var setVals = $('input:checkbox[name="cfgChk"]').map(function(){
				return $(this).val();
			}); 
			$('#cfgChkVal').val(setVals.get().join('^'));
			
			ajaxCallJsonPost("/shop/admin/order/popup_indb.dojson", "cfgForm", function(result){
				if (result.RESULT) {
					alert("저장했습니다.");
					closeLayer();
				}
			});
			
		} else {
			return false;
		}
		
	}
	function orderdate(mode){
		$("[name=mode]").val(mode);
		$('#orderSearchForm').attr("action","/shop/admin/order/list").submit();
	}

	function goPage(page){
		$("#pageNo").val(Number(page));
		$('#orderSearchForm').attr("action","/shop/admin/order/list").submit();
	}
	
	function searchChkForm(){
		if( $("#schSellerNm").val() == "" ){
			$("#schSellerCd").val("");
		}
		return true;
	}
	
	function excelDownload(page){
		$('#orderSearchForm').attr("action", "/shop/admin/order/orderExcelDownload").submit();
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
	주문 리스트<span>주문을 확인하고 주문상태를 변경합니다</span> 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=order&no=2',870,800)"><img src="/resources/shop/admin/img/btn_q.gif" border="0" hspace="2" align="absmiddle"></a>-->
</div>

<form name="search" id="orderSearchForm" onsubmit="return searchChkForm();" method="get">
	<input type="hidden" name="mode" value="${orderVO.mode}"> 
	<input type="hidden" name="first" value="0">
	<input type="hidden" id=pageNo name="pageNo" value="1"/>

	<table class="tb">
		<col class="cellC">
		<col class="cellL" style="width: 250">
		<col class="cellC">
		<col class="cellL">
		<tr>
			<td><font class="small1">주문검색 (통합)</font></td>
			<td>
				<select name="skey">
					<option value="all">= 통합검색 =</option>
					<option value="ord.nameOrder" ${stringUtil:selected(orderVO.skey, "ord.nameOrder")}>주문자이름</option>
					<option value="ord.mobileOrder" ${stringUtil:selected(orderVO.skey, "ord.mobileOrder")}>주문자핸드폰</option>
					<option value="ord.nameReceiver" ${stringUtil:selected(orderVO.skey, "ord.nameReceiver")}>수취인이름</option>
					<option value="ord.mobileReceiver" ${stringUtil:selected(orderVO.skey, "ord.mobileReceiver")}>수취인핸드폰</option>
					<option value="ord.ordno" ${stringUtil:selected(orderVO.skey, "ord.ordno")}>주문번호</option>
					<option value="item.goodsno" ${stringUtil:selected(orderVO.skey, "item.goodsno")}>상품번호</option>
					<option value="delivery.invoice" ${stringUtil:selected(orderVO.skey, "delivery.invoice")}>송장번호</option>
					<option value="ord.bankSender" ${stringUtil:selected(orderVO.skey, "ord.bankSender")}>입금자명</option>
				</select> 
				<input type="text" name="sword" value="${orderVO.sword}" class="line" style="width: 130px;">
			</td>
			
			<td><font class="small1">주문처</font></td>
			<td>
				<select name="sagent">
					<option value="" ${stringUtil:selected(orderVO.sagent, "")}>= 주문처 검색 =</option>
					<option value="W" ${stringUtil:selected(orderVO.sagent, "W")}>PC웹</option>
					<option value="M" ${stringUtil:selected(orderVO.sagent, "M")}>모바일웹</option>
					<option value="A" ${stringUtil:selected(orderVO.sagent, "A")}>모바일APP</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td><font class="small1">스킨</font></td>
			<td>
				<select name="schSkin">
					<option value="">= 스킨 검색 =</option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), orderVO.schSkin) }
				</select>
			</td>
			
			<td><font class="small1">배송국가</font></td>
			<td>
				<select name="scountry">
					<option value="">= 배송국가 검색 =</option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('countrytype')), orderVO.scountry) }
				</select> 
			</td>
		</tr>
		<tr>
			<td><font class="small1">주문상태</font></td>
			<td colspan="3" class="noline">
				<table class="">
					<tr>
						<td><label for="schStep1"><input type="checkbox" id="schStep1" name="step2" value="51"/><font class="small" style="padding-left: 3px;" color="5C5C5C">입금대기</font></label></td>
						<td><label for="schStep2"><input type="checkbox" id="schStep2" name="step" value="10" /><font class="small" style="padding-left: 3px;" color="5C5C5C">재고확인중</font></label></td>
						<td><label for="schStep3"><input type="checkbox" id="schStep3" name="step2" value="40"/><font class="small" style="padding-left: 3px;" color="5C5C5C">취소요청(중)</font></label></td>
						<td><label for="schStep4"><input type="checkbox" id="schStep4" name="step2" value="79"/><font class="small" style="padding-left: 3px;" color="5C5C5C">반품신청</font></label></td>
					</tr>
					<tr>
						<td><label for="schStep9"><input type="checkbox" id="schStep9" name="step" value="1"/><font class="small" style="padding-left: 3px;" color="5C5C5C">결제완료</font></label></td>
						<td><label for="schStep6"><input type="checkbox" id="schStep6" name="step" value="11"/><font class="small" style="padding-left: 3px;" color="5C5C5C">재고확인완료</font></label></td>
						<td><label for="schStep7"><input type="checkbox" id="schStep7" name="step2" value="56"/><font class="small" style="padding-left: 3px;" color="5C5C5C">취소완료</font></label></td>
						<td><label for="schStep8"><input type="checkbox" id="schStep8" name="step2" value="80"/><font class="small" style="padding-left: 3px;" color="5C5C5C">반품회수중</font></label></td>
					</tr>
					<tr>
						<td></td>
						<td><label for="schStep10"><input type="checkbox" id="schStep10" name="step" value="2" /><font class="small" style="padding-left: 3px;" color="5C5C5C">배송준비중</font></label></td>
						<td></td>
						<td><label for="schStep11"><input type="checkbox" id="schStep11" name="step2" value="81" /><font class="small" style="padding-left: 3px;" color="5C5C5C">반품검수중</font></label></td>
					</tr>
					<tr>
						<td></td>
						<td><label for="schStep12"><input type="checkbox" id="schStep12" name="step" value="12"/><font class="small" style="padding-left: 3px;" color="5C5C5C">입고완료</font></label></td>
						<td></td>
						<td><label for="schStep13"><input type="checkbox" id="schStep13" name="step2" value="82"/><font class="small" style="padding-left: 3px;" color="5C5C5C">반품(환불)확정</font></label></td>
					</tr>
					<tr>
						<td></td>
						<td><label for="schStep14"><input type="checkbox" id="schStep14" name="step" value="3"/><font class="small" style="padding-left: 3px;" color="5C5C5C">배송중</font></label></td>
						<td></td>
						<td><label for="schStep15"><input type="checkbox" id="schStep15" name="step2" value="83"/><font class="small" style="padding-left: 3px;" color="5C5C5C">반품(환불)완료</font></label></td>
					</tr>
					<tr>
						<td></td>
						<td><label for="schStep16"><input type="checkbox" id="schStep16" name="step" value="4" /><font class="small" style="padding-left: 3px;" color="5C5C5C">배송완료</font></label></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</td>
		</tr>
	<%--
		<tr>
			<td>판매사명</td>
			<td colspan="3">
				<input type="text" name="schSellerNm" id="schSellerNm" value="${orderVO.schSellerNm}" class="line" style="height:22px" />
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
				<input type="hidden" name="schSellerCd" id="schSellerCd" value="${orderVO.schSellerCd}" />
			</td>
		</tr>
	 --%>	
		<tr>
			<td><font class="small1">처리일자</font></td>
			<td colspan="3">
				<span class="noline small1" style="color: 5C5C5C; margin-right: 20px;">
					<input type="radio" name="dtkind" value="orddt" ${stringUtil:checked(orderVO.dtkind, 'orddt')} />주문일 
					<input type="radio" name="dtkind" value="cdt" ${stringUtil:checked(orderVO.dtkind, 'cdt')} />결제확인일 
					<input type="radio" name="dtkind" value="ddt" ${stringUtil:checked(orderVO.dtkind, 'ddt')} />배송일 
					<input type="radio" name="dtkind" value="a.confirmdt" ${stringUtil:checked(orderVO.dtkind, 'a.confirmdt')} />배송완료일
				</span> 
				<input type="text" name="sregdt" value="${orderVO.sregdt[0]}<%-- <%= StringUtil.nullConv(regdtArray[0], "") %> --%>"	onclick="calendar(event)" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" size="12" class="line" maxlength="8"> - 
				<input type="text" name="sregdt" value="${orderVO.sregdt[1]}<%-- <%= StringUtil.nullConv(regdtArray[1], "") %> --%>" onclick="calendar(event)" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" size="12" class="line" maxlength="8"> 
				<a href="javascript:setDate('sregdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>
			</td>
		</tr>
		<tr>
			<td><font class="small1">결제방법</font></td>
			<td colspan="3" class="noline">
				<font class="small1" color="5C5C5C">
					<input type="radio" name="settlekind" value="" ${stringUtil:checked(orderVO.settlekind, '')} />전체 
					<input type="radio" name="settlekind" value="a" ${stringUtil:checked(orderVO.settlekind, 'a')} />무통장 
					<input type="radio" name="settlekind" value="c" ${stringUtil:checked(orderVO.settlekind, 'c')} />신용카드 
				</font> 
			</td>
		</tr>
	</table>
	<div class="button_top">
		<a href="javascript:goPage(1);"><img src="/resources/shop/admin/img/btn_search2.gif" alt="" /></a>
	</div>
</form> 
<%-- 검색영역 끝 --%>


<div style="padding-top: 15px"></div>

<form name="frmList" method="post" action="indb" onsubmit="return chkForm(this)">
	<input type="hidden" name="mode" value="chgAll">


	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>아래에서 선택한 주문건을 
				<select name="changeStep" required label="수정사항">
					<option value="">- 주문상태 변경처리 -</option>
					<option value="0">입금대기 처리</option>
					<option value="1">결제완료 처리</option>
					<option value="10">재고확인중 처리</option>
					<option value="2">배송준비중 처리</option>
					<option value="3">배송중 처리</option>
					<option value="4">배송완료 처리</option>
				</select> 합니다. 
				<font class="extext">(변경후 하단 수정버튼 꼭 클릭)</font>
			</td>
			<td align="right">
				<a href="javaScript:excelDownload();"><img class="button_top" src="/resources/shop/admin/img/btn_excel_download.gif" alt="엑셀다운로드" border="0" align="absmiddle" style="cursor:hand; height: 26px; margin-bottom: 6px; margin-left: 5px; border: none;"></a>
				
				<c:if test="${ orderVO.mode == 'group' }">
					<a href="javascript:orderdate('')"><img src="/resources/shop/admin/img/btn_orderdate_off.gif" align="absmiddle"></a> 
					<img src="/resources/shop/admin/img/btn_orderprocess_on.gif" align="absmiddle">
				</c:if> 
				<c:if test="${ orderVO.mode != 'group' }">
					<img src="/resources/shop/admin/img/btn_orderdate_on.gif" align="absmiddle"> 
					<a href="javascript:orderdate('group')"><img src="/resources/shop/admin/img/btn_orderprocess_off.gif" align="absmiddle"></a>
				</c:if> 
			</td>
		</tr>
		<tr>
			<td height="3"></td>
		</tr>
	</table>

	<c:set var="colspan" value="24" />
	<div style="width: 100%; overflow: auto;">
		<table width="150%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="rnd" colspan="${colspan}"></td>
			</tr>
			<tr class="rndbg">
				<th width=60><a href="javascript:void(0)" onClick="chkBoxAll(document.getElementsByName('ordno'),'rev')" class="white">선택</a></th>
				<th width=60>번호</th>
				<th width=60>스킨</th>
				<th width=60>주문처</th>
				<th width=150>주문일</th>
				<th width=150>주문번호</th>
				<th width=400>주문상품</th>
				<th width=100>주문자명</th>
				<th width=120>통관고유부호</th>
				<th width=120>판매금액</th>
				<th width="60">결제방법</th>
				<th width=120>결제금액</th>
				
				<th width=120>사용 적립금</th>
				<th width=120>적립 적립금</th>
				
				<th width=150>사용쿠폰</th>
				<th width=120>사용쿠폰액</th>
				<th width=150>사용할인코드</th>
				<th width=120>할인코드 사용액</th>
				<th width=120>배송국가</th>
				<th width=60>판매처관리</th>
				<th width=150>추정공헌이익율</th>
				<th width=150>B2B위탁<br>추정공헌이익율</th>
				<th width=150>B2B위탁<br>정산예정액</th>
				<th width=100>처리상태</th>
			</tr>
			<tr><td class="rnd" colspan="${colspan}"></td></tr>
			<tr><td colspan="${colspan}" bgcolor="#E8E7E7" height="1"></td></tr>
	
			<c:set var="bgcolor"  value=""/>
			<c:set var="bgcolor"  value=""/>
			<c:set var="grp_tot_amt"  value="0"/>
			<c:set var="tot_amt"  value="0"/>
			<c:set var="tmp_amt"  value="0"/>
			<c:set var="totmoney"  value="0"/>
			<c:set var="st1"  value=""/>
			<c:set var="st2"  value=""/>
			<c:set var="stMsg"  value=""/>
			<c:set var="stepMsg"  value=""/>
			<c:set var="j"  value="0"/>
			
			<c:choose>
				<c:when test="${not empty orderVO.orderList}">
					<c:forEach items="${orderVO.orderList}" var="list" varStatus="i">
						<c:choose>
							<c:when test="${ list.istep eq '40' or list.istep eq '56' or list.istep eq '58' or list.istep >= 79 }">
								<c:set var="bgcolor"  value="#F0F4FF"/>
								<c:set var="disabled"  value="disabled"/>
							</c:when>
							<c:otherwise>
								<c:set var="bgcolor"  value="#ffffff"/>
								<c:set var="disabled"  value=""/>
							</c:otherwise>
						</c:choose>
						<c:set var="stepMsg"  value=" ${shopLibFunction:getStepMsg(list.step, list.istep, list.ordno, list.sno) } " />
						<c:set var="stMsg"  value=" ${shopLibFunction:r_stepi(list.step, list.istep) } " />
						<c:set var="tmp_amt"  value="${fn:trim(list.prnsettleprice)+tmp_amt }"/>
				
				
						<!-- 주문흐름보기 시작 -->
						<c:if test="${ orderVO.mode == 'group' }">
							<c:if test="${ i.index == 0 }">
								<c:set var="st1"  value="${ list.step }"/>
								<c:set var="st2"  value="${ list.step2 }"/>
								<c:set var="ist"  value="${ list.istep }"/>
								
								<tr align="center">
									<td colspan="${colspan}" bgcolor="#f7f7f7" height="30" style="padding-left:15px">
									<b><img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle">${stepMsg}</b>
									</td>
								</tr>
							</c:if>
						
							<c:if test="${ st1 ne list.step or ist ne list.istep }">
								<tr>
									<td height="30" colspan="${colspan}" align="right" style=padding-right:8>합계: <font class="ver9"><b>${shopLibFunction:getExchange(totmoney, 'kr')}</b></font></td>
									<td colspan="3"></td>
								</tr>
								<tr><td colspan="${colspan}" height="15"></td></tr>
								<tr align="center">
									<td colspan="${colspan}" bgcolor="#f7f7f7" height="30" style="padding-left:15px">
										<b><img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle">${stepMsg }</b>
									</td>
								</tr>
						
								<c:set var="totmoney"  value="0"/>
								<c:set var="j"  value="0"/>
							</c:if>
						</c:if>
						<!-- 주문흐름보기 끝 -->
			
			
						<tr height="25" bgcolor="${bgcolor}" bg="${bgcolor}" align="center">
							<td class="noline"><input type="checkbox" name="ordno" value="${list.ordno}" onclick="iciSelect(this)"  ${disabled} /></td>
							<td><font class="ver8" color="616161">${orderVO.mode == 'group' ? (j+1) : (orderVO.rowCount - i.index) - ( (orderVO.pageNo - 1)  *  10 ) }</font></td>
							<td><font class="ver81" color="616161">${codeUtil:getCodeName('skin', list.skin)}</font></td>
							<td>
								<font class="ver81" color="616161">
									<c:choose>
										<c:when test="${list.agent eq 'W' }">PC웹</c:when>
										<c:when test="${list.agent eq 'M' }">모바일웹</c:when>
										<c:when test="${list.agent eq 'A' }">모바일APP</c:when>
									</c:choose>
								</font>
							</td>
							<td><font class="ver81" color="616161"><fmt:formatDate value="${list.orddt}" pattern="yyyy-MM-dd hh:mm"/></font></td>
							<td>
								<a href="${ctx}/shop/admin/order/popup.order?ordno=${list.ordno}&viewFlg=view"><font class="ver81" color="0074BA"><b>${list.ordno}</b></font></a>
								<a href="javascript:popup('${ctx}/shop/admin/order/popup.order?ordno=${list.ordno}',1500,1300,'order')"><img src="/resources/shop/admin/img/btn_newwindow.gif" border="0" align="absmiddle"></a>
							</td>
							<td align="left">
								<div style="height: 13px; overflow-y: hidden;">
									<c:if test="${ list.oldordno != '' }">
										<a href="javascript:popup('${ctx}/shop/admin/order/popup.order?ordno=${list.ordno}',800,600)"><img src="/resources/shop/admin/img/icon_twice_order.gif"></a>
									</c:if>
									<c:if test="${ list.escrowyn == 'y' }">
										<a href="javascript:popup('${ctx}/shop/admin/order/popup.order?ordno=${list.ordno}',800,600)"><img src="/resources/shop/admin/img/btn_escrow.gif"></a>
									</c:if>
									<c:if test="${ list.eggyn == 'y' }">
										<a href="javascript:popup('${ctx}/shop/admin/order/popup.order?ordno=${list.ordno}',800,600)"><img src="/resources/shop/admin/img/icon_guar_order.gif"></a>
									</c:if>
									<c:if test="${ list.cashreceipt != '' }">
										<img src="/resources/shop/admin/img/icon_cash_receipt.gif">
									</c:if>
									<font class="small1" color="444444">
										${list.goodsnm}
										<c:if test="${ list.goodsnmcnt > 1 }">
											외 ${ list.goodsnmcnt - 1}건
										</c:if>
									</font>
								</div>
							</td>
							<td><font class="ver81" color="616161">${list.nameorder}</font></td>
							<td><font class="ver81" color="616161">${list.customsNum}</font></td>
							<td><font class="ver81" color="616161">${shopLibFunction:getExchange(list.goodsprice, 'kr')}</font></td>
							<td class="small4">${shopLibFunction:r_settlekind( list.settlekind )}</td>
							<td><font class="ver81" color="616161">${shopLibFunction:getExchange(list.prnsettleprice, 'kr')}</font></td>
							<td><font class="ver81" color="616161">${list.emoney > 0 ? shopLibFunction:getExchange(list.emoney, 'kr') : '-'}</font></td>
							<td><font class="ver81" color="616161">${shopLibFunction:getExchange(list.addemoney, 'kr')}</font></td>
							<td><font class="ver81" color="616161">${not empty list.couponNm ? list.couponNm : '-'}</font></td>
							<td><font class="ver81" color="616161">${list.couponPrice > 0 ? shopLibFunction:getExchange(list.couponPrice, 'kr') : '-'}</font></td>
							<td><font class="ver81" color="616161">${not empty list.dcCodeNm ? list.dcCodeNm : '-'}</font></td>
							<td><font class="ver81" color="616161">${list.dcCodePrice > 0 ? shopLibFunction:getExchange(list.dcCodePrice, 'kr') : '-'}</font></td>
							<td><font class="ver81" color="616161">${codeUtil:getCodeName('countrytype', list.country)}</font></td>
							<td><font class="ver81" color="616161">일반</font></td>
							<td><font class="ver81" color="616161">${list.estimate}%</font></td>
							<td><font class="ver81" color="616161">-</font></td>
							<td><font class="ver81" color="616161">-</font></td>
							<td class="small4" width="60">${stMsg}</td>
						</tr>
						<tr><td colspan="${colspan}" bgcolor="E4E4E4"></td></tr>
			
						<c:set var="st1"  value="${list.step}"/>
						<c:set var="st2"  value="${list.step2}"/>
						<c:set var="ist"  value="${ list.istep }"/>
						<c:set var="totmoney"  value="${fn:trim(list.prnsettleprice) + totmoney }"/>
						<c:set var="j"  value="${j + 1}"/>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td height="10" colspan="${colspan}" align="center">조회내역이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
			<tr>
				<td>
					<c:if test="${ orderVO.mode != 'group' }">
						<a href="javascript:chkBoxAll(document.getElementsByName('ordno'),'rev')"><img src="/resources/shop/admin/img/btn_allchoice.gif" border=0></a>
					</c:if>
				</td>
				<td align="right" height="30" colspan="${colspan}" style="padding-right: 8">
				</td>
				<td colspan="${colspan}"></td>
			</tr>
			<tr bgcolor="#f7f7f7" height="30">
				<td colspan="${colspan}" align="right" style="padding-right: 8">합계 : <span class="ver9"><b>${shopLibFunction:getExchange(totmoney, 'kr')}</b></span></td>
			</tr>
			<tr bgcolor="#f7f7f7" height="30">
				<td colspan="${colspan}" align="right" style="padding-right:8">전체합계 : <span class="ver9"><b>${shopLibFunction:getExchange(tmp_amt, 'kr')}</b></span></td>
			</tr>
		
			<tr>
				<td height="4"></td>
			</tr>
			<tr>
				<td colspan="${colspan}" class="rndline"></td>
			</tr>
		</table>
	</div>

	<%-- 페이징 시작 --%>
	<c:if test="${ orderVO.mode != 'group' }">
		<tags:paginator currentPageNo="${orderVO.pageNo}" rowCount="${orderVO.rowCount}" pageSize="${orderVO.pageSize}"  pageGroupSize="${orderVO.pageGroupSize}" />
	</c:if> 
	<%-- 페이징 끝 --%>

	<div class="button">
		<input type="image" src="/resources/shop/admin/img/btn_modify.gif" onclick="return isChked(document.getElementsByName('ordno'),'정말로 수정 하시겠습니까?')">
		<!-- <a href="javascript:history.back()"><img src="/resources/shop/admin/img/btn_cancel.gif"></a> -->
	</div>
</form>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>