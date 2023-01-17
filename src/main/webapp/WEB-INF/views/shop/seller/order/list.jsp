<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language="javascript">
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
	
	function dnXls(mode){
		// 엑셀 다운로드의 검색 조건은 주문내역의 검색 조건값을 사용합니다.
		$('form[name=frmDnXls]').empty();
		var domTarget = $('#orderSearchForm').serializeArray();
		$(domTarget).each(function(i, obj){
			$('<input>', {
			    type	: 'hidden',
			    name	: obj.name,
			    value	: obj.value
			}).appendTo('form[name=frmDnXls]');
		});
		$('form[name=frmDnXls]').find('input[name="mode"]').val(mode);
		$('form[name=frmDnXls]').attr('action', ctx + "/shop/seller/order/excel/orderExcelDown");
		$('form[name=frmDnXls]').submit();
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
			
			ajaxCallJsonPost("/shop/seller/order/popup_indb.dojson", "cfgForm", function(result){
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
		var fm1 = document.search;
		
		fm1.mode.value = mode;		
		fm1.submit();
	}

	function goPage(page){
		$("#pageNo").val(Number(page));
		$('#orderSearchForm').submit();
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

<form name="search" id="orderSearchForm">
	<input type="hidden" name="mode" value="${orderFM.mode}"> 
	<input type="hidden" name="first" value="0">
	<input type=hidden id=pageNo name="pageNo" value="1"/>

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
					<option value="a.ordno"	${stringUtil:selected(orderFM.skey, "a.ordno")}>주문번호</option>
					<option value="nameOrder" ${stringUtil:selected(orderFM.skey, "nameOrder")}>주문자명</option>
					<option value="bankSender" ${stringUtil:selected(orderFM.skey, "bankSender")}>입금자명</option>
					<option value="mid" ${stringUtil:selected(orderFM.skey, "mid")}>아이디</option>
				</select> 
				<input type="text" name="sword" value="${orderFM.sword}" class="line">
			</td>
			<td><font class="small1">상품검색 (선택)</font></td>
			<td>
				<select name="sgkey">
					<option value="gdGoods.goodsnm" ${stringUtil:selected(orderFM.sgkey, "goodsnm")}>상품명</option>
					<option value="brandnm" ${stringUtil:selected(orderFM.sgkey, "brandnm")}>브랜드</option>
				</select> 
				<input type="text" name="sgword" value="${orderFM.sgword}" class="line">
			</td>
		</tr>
		<tr>
			<td><font class="small1">주문상태${orderFM.step2 } ${orderFM.step }</font></td>
			<td colspan="3" class="noline">
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step" value="0" ${ fn:contains(orderFM.step,'0') ? 'checked' : ''} /><font class="small" color="5C5C5C">주문접수</font>
				</div>							
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step" value="1" ${ fn:contains(orderFM.step,'1') ? 'checked' : ''} /><font class="small" color="5C5C5C">입금확인</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step" value="2" ${ fn:contains(orderFM.step,'2') ? 'checked' : ''} /><font class="small" color="5C5C5C">배송준비중</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step" value="3" ${ fn:contains(orderFM.step,'3') ? 'checked' : ''} /><font class="small" color="5C5C5C">배송중</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step" value="4" ${ fn:contains(orderFM.step,'4') ? 'checked' : ''} /><font class="small" color="5C5C5C">배송완료</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step2" value="7" ${ fn:contains(orderFM.step2,'7') ? 'checked' : ''} /><font class="small" color="5C5C5C">주문취소</font>
				</div>
				<div style="clear: both;"></div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step2" value="2" ${ fn:contains(orderFM.step2,'2') ? 'checked' : ''} /><font class="small" color="5C5C5C">환불관련</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step2" value="3" ${ fn:contains(orderFM.step2,'3') ? 'checked' : ''} /><font class="small" color="5C5C5C">반품관련</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step2" value="60" ${ fn:contains(orderFM.step2,'60') ? 'checked' : ''} /><font class="small" color="5C5C5C">교환완료</font>
				</div>
				<div style="float: left; padding-right: 10px"> 
					<input type="checkbox" name="step2" value="61" ${ fn:contains(orderFM.step2,'61') ? 'checked' : ''} /><font class="small" color="5C5C5C">재주문</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step2" value="50" ${ fn:contains(orderFM.step2,'50') ? 'checked' : ''} /><font class="small" color="5C5C5C">결제시도</font>
				</div>
				<div style="float: left; padding-right: 10px">
					<input type="checkbox" name="step2" value="54" ${ fn:contains(orderFM.step2,'54') ? 'checked' : ''} /><font class="small" color="5C5C5C">결제실패</font>
				</div>
			</td>
		</tr>

		<%-- <tr>
			<td>판매사명</td>
			<td>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${orderFM.schSellerNm}" class="line" style="height:22px" />
				<input type="button" value="판매사 조회" onclick="popupLayer('/shop/admin/common/sellerSearchPopup', 600, 500);" />
				<input type="hidden" name="schSellerCd" id="schSellerCd" value="${orderFM.schSellerCd}" />
			</td>
		</tr> --%>
		
		<tr>
			<td><font class="small1">처리일자</font></td>
			<td colspan="3">
				<span class="noline small1" style="color: 5C5C5C; margin-right: 20px;">
					<input type="radio" name="dtkind" value="orddt" ${stringUtil:checked(orderFM.dtkind, 'orddt')} />주문일 
					<input type="radio" name="dtkind" value="cdt" ${stringUtil:checked(orderFM.dtkind, 'cdt')} />결제확인일 
					<input type="radio" name="dtkind" value="ddt" ${stringUtil:checked(orderFM.dtkind, 'ddt')} />배송일 
					<input type="radio" name="dtkind" value="confirmdt" ${stringUtil:checked(orderFM.dtkind, 'confirmdt')} />배송완료일
				</span> 
				<input type="text" name="sregdt" value="${orderFM.sregdt[0]}<%-- <%= StringUtil.nullConv(regdtArray[0], "") %> --%>"	onclick="calendar(event)" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" size="12" class="line" maxlength="8"> - 
				<input type="text" name="sregdt" value="${orderFM.sregdt[1]}<%-- <%= StringUtil.nullConv(regdtArray[1], "") %> --%>" onclick="calendar(event)" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" size="12" class="line" maxlength="8"> 
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
					<input type="radio" name="settlekind" value="" ${stringUtil:checked(orderFM.settlekind, '')} />전체 
					<input type="radio" name="settlekind" value="a" ${stringUtil:checked(orderFM.settlekind, 'a')} />무통장 
					<input type="radio" name="settlekind" value="c" ${stringUtil:checked(orderFM.settlekind, 'c')} />신용카드 
					<input type="radio" name="settlekind" value="o" ${stringUtil:checked(orderFM.settlekind, 'o')} />계좌이체 
					<input type="radio" name="settlekind" value="v" ${stringUtil:checked(orderFM.settlekind, 'v')} />가상계좌 
					<input type="radio" name="settlekind" value="h" ${stringUtil:checked(orderFM.settlekind, 'h')} />핸드폰 
					<input type="radio" name="settlekind" value="d" ${stringUtil:checked(orderFM.settlekind, 'd')} />전액할인 
					<input type="checkbox" name=couponyn value="1" ${stringUtil:checked(orderFM.couponyn, '1')}/>쿠폰사용
					<input type="checkbox" name=cashreceipt value="1" ${stringUtil:checked(orderFM.cashreceipt, '1')}/>현금영수증
				</font> 
				<img src="/resources/shop/admin/img/icon_cash_receipt.gif">
			</td>
		</tr>
	</table>
	<div class="button_top">
		<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
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
					<option value="0">주문접수 처리</option>
					<option value="1">입금확인 처리</option>
					<option value="2">배송준비중 처리</option>
					<option value="3">배송중 처리</option>
					<option value="4">배송완료 처리</option>
				</select> 합니다. 
				<font class="extext">(변경후 하단 수정버튼 꼭 클릭)</font>
			</td> 
			<td align="right">
				<c:if test="${ orderFM.mode == 'group' }">
				<a href="javascript:orderdate('')"><img src="/resources/shop/admin/img/btn_orderdate_off.gif" align="absmiddle"></a> 
				<img src="/resources/shop/admin/img/btn_orderprocess_on.gif" align="absmiddle">
				</c:if> 
				<c:if test="${ orderFM.mode != 'group' }">
				<img src="/resources/shop/admin/img/btn_orderdate_on.gif" align="absmiddle"> 
				<a href="javascript:orderdate('group')"><img src="/resources/shop/admin/img/btn_orderprocess_off.gif" align="absmiddle"></a>
				</c:if> 
			</td>
		</tr>
		<tr>
			<td height="3"></td>
		</tr>
	</table>


	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="25">
		<col width="30">
		<col width="100">
		<col width="120">
		<col>
		<col width="95">
		<col width="50">
		<col width="50">
		<col>
		<col width="55">
		<tr>
			<td class="rnd" colspan="20"></td>
		</tr>
		<tr class="rndbg">
			<th>
				<a href="javascript:void(0)" onClick="chkBoxAll(document.getElementsByName('ordno'),'rev')" class="white">선택</a>
			</th>
			<th>번호</th>
			<th>주문일시</th>
			<th colspan=2>주문번호 (주문상품)</th>
			<th>주문자</th>
			<th>받는분</th>
			<th>결제</th>
			<th>금액</th>
			<th colspan="6">처리상태</th>
		</tr>
		<tr>
			<td class="rnd" colspan="20"></td>
		</tr>
		<tr>
			<td colspan="13" bgcolor="#E8E7E7" height="1"></td>
		</tr>

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
<c:when test="${orderFM.orderList != null &&  fn:length(orderFM.orderList) >0 }">
<c:forEach items="${orderFM.orderList}" var="list" varStatus="i">

	<c:if test="${ list.istep > '4' }">
		<c:set var="bgcolor"  value="#F0F4FF"/>
		<c:set var="disabled"  value="disabled"/>
	</c:if>
	<c:if test="${ list.istep <= '4' }">
		<c:set var="bgcolor"  value="#ffffff"/>
		<c:set var="disabled"  value=""/>
	</c:if>
	
	<!-- 2020-02-25 이현빈 판매사 주문스텝 istep 으로 적용 -->
	<c:set var="stepMsg"  value="${shopLibFunction:r_istep(list.istep)}"/>
	
	<c:set var="tmp_amt"  value="${fn:trim(list.seller_price)+tmp_amt }"/>
	
	<!-- 주문흐름보기 시작 -->
	<c:if test="${ orderFM.mode == 'group' }">
	
	<c:if test="${ i.index == 0 }">
		<c:set var="ist"  value="${ list.istep }"/>
	<tr align="center">
		<td colspan="13" bgcolor="#f7f7f7" height="30" style="padding-left:15px">
		<b><img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle">${stepMsg}</b>
		</td>
	</tr>
	</c:if>
	
	<c:if test="${ist != list.istep }">
	
	<tr>
		<td><a href="javascript:chkBox2(document.getElementsByName('ordno'),${i.index - j},${i.index},'rev')"><img src="/resources/shop/admin/img/btn_allchoice.gif" border="0"></a></td>
		<td height="30" colspan="9" align="right" style=padding-right:8>합계: <font class="ver9"><b>${stringUtil:getMoneyFormatInteger( totmoney )}원</b></font></td>
		<td colspan="3"></td>
	</tr>
	<tr><td colspan="13" height="15"></td></tr>
	<tr align="center">
		<td colspan="13" bgcolor="#f7f7f7" height="30" style="padding-left:15px">
	<b><img src="/resources/shop/admin/img/icon_process.gif" align="absmiddle">${stepMsg }</b>
		</td>
	</tr>
	
	<c:set var="totmoney"  value="0"/>
	<c:set var="j"  value="0"/>
	
	</c:if>
	
	</c:if>
	<!-- 주문흐름보기 끝 -->


		<tr height="25" bgcolor="${bgcolor}" bg="${bgcolor}" align="center">
			<td class="noline">
				<input type="checkbox" name="ordno" value="${list.ordno}" onclick="iciSelect(this)"  ${disabled} />
			</td>
			<td>
				<font class="ver8" color="616161">${orderFM.mode == 'group' ? (j+1) : (orderFM.rowCount - i.index) - ( (orderFM.pageNo - 1)  *  10 ) }</font>
			</td>
			<td>
				<font class="ver81" color="616161"><fmt:formatDate value="${list.orddt}" pattern="yyyy-MM-dd hh:mm"/></font>
			</td>
			<td align="left">
				<a href="${ctx }/shop/seller/order/popupOrder?ordno=${list.ordno}&viewFlg=view"><font class="ver81" color="0074BA"><b>${list.ordno}</b></font></a>
				<a href="javascript:popup('${ctx }/shop/seller/order/popupOrder?ordno=${list.ordno}',800,600)"><img src="/resources/shop/admin/img/btn_newwindow.gif" border="0" align="absmiddle"></a>
			</td>
			<td align="left">
				<div style="height: 13px; overflow-y: hidden;">
					<c:if test="${ list.oldordno != '' }">
						<a href="javascript:popup('${ctx }/shop/seller/order/popupOrder?ordno=${list.ordno}',800,600)"><img src="/resources/shop/admin/img/icon_twice_order.gif"></a>
					</c:if>
					<c:if test="${ list.escrowyn == 'y' }">
						<a href="javascript:popup('${ctx }/shop/seller/order/popupOrder?ordno=${list.ordno}',800,600)"><img src="/resources/shop/admin/img/btn_escrow.gif"></a>
					</c:if>
					<c:if test="${ list.eggyn == 'y' }">
						<a href="javascript:popup('${ctx }/shop/seller/order/popupOrder?ordno=${list.ordno}',800,600)"><img src="/resources/shop/admin/img/icon_guar_order.gif"></a>
					</c:if>
					<c:if test="${ list.cashreceipt != '' }">
						<img src="/resources/shop/admin/img/icon_cash_receipt.gif">
					</c:if>
					<font class="small1" color="444444">
						${list.goodsnm}
						<c:if test="${ list.goodsnmcnt > 1 }">
							외 ${ list.goodsnmcnt - 1}건
						</c:if>
						<c:choose>
							<c:when test="${ list.goodsnmcnt > 1 }">
								[판매사 : ${list.sellerNm }] 외  ${ list.goodsnmcnt - 1}
							</c:when>
							<c:otherwise>
								[판매사 : ${list.sellerNm }]
							</c:otherwise>
						</c:choose>
					</font>
				</div>

			</td>
			<td>
				<c:choose>
					<c:when test="${list.mid != '' }">
						<span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno }"/>
						<font class="small1" color="0074BA"><b>${list.nameorder }</b>( ${list.mid})</font>						
					</c:when>
					<c:otherwise>
						<font class="small1" color=0074BA"><b>${list.nameorder}</b></font>
					</c:otherwise>
				</c:choose>
			</td>
			<td><font class="small1" color="444444">${list.namereceiver}</font></td>
			<td class="small4">${shopLibFunction:r_settlekind( list.settlekind )}</td>
			<td class="ver81"><b>${stringUtil:getMoneyFormatInteger( fn:trim(list.seller_price) )}</b></td>
			<td class="small4" width="60">
				${stepMsg}
			</td>
		</tr>
		<tr>
			<td colspan="20" bgcolor="E4E4E4"></td>
		</tr>

		<c:set var="ist" value="${list.istep }"/>
		<c:set var="totmoney"  value="${fn:trim(list.seller_price) + totmoney }"/>
		<c:set var="j"  value="${j + 1}"/>
</c:forEach>
</c:when>
<c:otherwise>
		<tr>
			<td height="10" colspan="10" align="center">조회내역이 없습니다.</td>
		</tr>
</c:otherwise>
</c:choose>

		<tr>
			<td>
				<c:if test="${ orderFM.mode == 'group' }">
					<a href="javascript:chkBox2(document.getElementsByName('ordno'),${j},${i},'rev')"><img src='/resources/shop/admin/img/btn_allchoice.gif' border=0></a>
				</c:if> 
				<c:if test="${ orderFM.mode != 'group' }">
					<a href="javascript:chkBoxAll(document.getElementsByName('ordno'),'rev')"><img src="/resources/shop/admin/img/btn_allchoice.gif" border=0></a>
				</c:if>
			</td>
			<td align="right" height="30" colspan="9" style="padding-right: 8">
			</td>
			<td colspan="3"></td>
		</tr>
		<tr bgcolor="#f7f7f7" height="30">
			<td colspan="10" align="right" style="padding-right: 8">합계 : <span class="ver9"><b>${stringUtil:getMoneyFormatInteger( totmoney )}원</b></span></td>
			<td colspan="3"></td>
		</tr>
		<tr bgcolor="#f7f7f7" height="30">
			<td colspan="10" align="right" style="padding-right:8">전체합계 : <span class="ver9"><b>${stringUtil:getMoneyFormatInteger( tmp_amt )}원</b></span></td>
			<td colspan="3"></td>
		</tr>

		<tr>
			<td height="4"></td>
		</tr>
		<tr>
			<td colspan="13" class="rndline"></td>
		</tr>

	</table>

	<%-- 페이징 시작 --%>
<c:if test="${ orderFM.mode != 'group' }">
<tags:paginator currentPageNo="${orderFM.pageNo}" rowCount="${orderFM.rowCount}" pageSize="${orderFM.pageSize}"  pageGroupSize="${orderFM.pageGroupSize}" />
</c:if> 
	<%-- 페이징 끝 --%>

	<div class="button">
		<input type="image" src="/resources/shop/admin/img/btn_modify.gif" onclick="return isChked(document.getElementsByName('ordno'),'정말로 수정 하시겠습니까?')">
	</div>

</form>

<form name="frmDnXls" method="post">
</form>

 <!--  주문내역 프린트&다운로드 : Start -->
<table width="100%" border="0" cellpadding="10" cellspacing="0" style="border:1px #dddddd solid;">
<tr>
	<td width="50%" align="center" bgcolor="#f6f6f6" style="font:16pt tahoma;" colspan="2"><img src="/resources/shop/admin/img/icon_down.gif" border="0" align="absmiddle"><b>download</b></td>
</tr>
<tr>
	<td align="center">
		<form method="get" name="frmPrint">
			<input type="hidden" name="ordnos">
		</form>	
		<table border="0" cellpadding="4" cellpadding="0" border="0">
			<tr align="center">
				<td><a href="javascript:dnXls('order')"><img src="/resources/shop/admin/img/btn_order_data_order.gif" border="0"></a></td>
				<td><a href="javascript:dnXls('goods')"><img src="/resources/shop/admin/img/btn_order_data_goods.gif" border="0"></a></td>
<!-- 				<td><a href="javascript:dnXls('erp')"><img src="dss" border="0"></a></td> -->
			</tr>
			<tr align="center">
				<td><a href="javascript:popupLayer('${ctx }/shop/seller/order/popup.orderxls?mode=orderXls',550,700,'popLayerCallback');"><img src="/resources/shop/admin/img/btn_order_data_order_ot.gif" border="0"></a></td>
				<td><a href="javascript:popupLayer('${ctx }/shop/seller/order/popup.orderxls?mode=orderGoodsXls',550,700, 'popLayerCallback');"><img src="/resources/shop/admin/img/btn_order_data_goods_ot.gif" border="0"></a></td>
			</tr>
		</table>
	</td>
</tr>
</table>


<div style="display:none">
			<table border="0" cellpadding="4" cellpadding="0" border="0">
				<tr align="center">
					<td>
						<select NAME="type">
							<option value="report">주문내역서</option>
							<option value="reception">간이영수증</option>
							<option value="tax">세금계산서</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><strong class="noline"><label for="r1"><input class="no_line" type="radio" name="list_type" value="list" id="r1" onclick="openLayer('psrch','none')" checked>목록선택</label>&nbsp;&nbsp;&nbsp;<label for="r2"><input class="no_line" type="radio" name="list_type" value="term" id="r2" onclick="openLayer('psrch','block')">기간선택</label></strong></td>
				</tr>
				<tr>
					<td align="center">
						<div style="float:left; display:none;" id="psrch">
						<input type="text" name="regdt1" onclick="calendar(event)" size="12" class="line"> -
						<input type="text" name="regdt2" onclick="calendar(event)" size="12" class="line">
						<select name="settlekind">
							<option value=""> - 결제방법 - </option>
							<option value="a">무통장</option>
							<option value="c">신용카드</option>
							<option value="o">계좌이체</option>
							<option value="v">가상계좌</option>
							<option value="d">전액할인</option>
							<option value="h">핸드폰</option>
							<option value="p">포인트</option>
						</select>
						<select name="step">
							<option value=""> - 단계선택 - </option>
							<option value="step_0">주문접수</option>
							<option value="step_1">입금확인</option>
							<option value="step_2">배송준비중</option>
							<option value="step_3">배송중</option>
							<option value="step_4">배송완료</option>
							<option value="step2_1">주문취소</option>
							<option value="step2_2">환불관련</option>
							<option value="step2_3">반품관련</option>
							<option value="step2_50">결제시도</option>
							<option value="step2_54">결제실패</option>
						</select>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href="javascript:order_print('frmPrint', 'frmList');" style="padding-top:20px"><img src="/resources/shop/admin/img/btn_print.gif" border="0" align="absmiddle"></a>
					</td>
				</tr>
			</table>	
			</div>
<!-- 주문내역 프린트 : End -->



<div id="MSG01">
	<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">주문일 또는 주문처리흐름 방식으로 주문내역을 정렬하실 수 있습니다.</td>
		</tr>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">주문상태를 변경하시려면 주문건 선택 - 처리단계선택 후 수정버튼을 누르세요.</td>
		</tr>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">주문상태변경을 통해 각 주문처리단계 (주문접수, 입금확인, 배송준비, 배송중, 배송완료) 로 빠르게 처리하실 수 있습니다.</td>
		</tr>

		<tr>
			<td height="8"></td>
		</tr>
		<tr>
			<td><font class="def1" color="ffffff"><b>- 카드결제주문은 아래와 같은 경우가 발생할 수 있습니다. (필독하세요!) -</b></font></td>
		</tr>

		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">해당 PG사 관리자모드에는 승인이 되었으나, 주문리스트에서 주문상태가 '입금확인'이 아닌 '결제시도'로 되어 있는 경우가 발생될 수 있습니다.</td>
		</tr>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">이는 중간에 통신상의 문제로 리턴값을 제대로 받지 못해 주문상태가 변경이 되지 않은 것입니다.</td>
		</tr>
	</table>
</div> 
<script>
	cssRound("MSG01");
</script>
<div style="display:none">
	<div style="padding-top:15px"></div>
	<div class="title title_top">송장등록<span>대량의 주문에 송장번호를 빠르게 등록하실 수 있습니다</span></div>
	<div style="padding-top:15px"></div>

	<form name="deliveryfm" method="post" action="data_delivery_indb.jsp" target="ifrmHidden"  enctype="multipart/form-data" onsubmit="return chkForm(this)">

		<div style="padding-top:5px;padding-left:10px;"><font class="extext">* 작성완료된 송장XLS파일을 올리세요.</font></div>
		<table class="tb">
			<col class="cellC"><col class="cellL">
			<tr>
				<td width="240" height="35">송장 XLS 파일 올리기</td>
				<td><input type="file" name="file_excel" size="45" required label="XLS 파일"> &nbsp;&nbsp; <span class="noline"><input type="image" src="/resources/shop/admin/img/btn_regist_s.gif" align="absmiddle" onclick="document.deliveryfm.submit();"></span></td>
			</tr>
		</table>

	</form>
	<div style="padding-top:15px"></div>
	<div id="MSG02">
		<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><b>엑셀파일다운 주문별을 다운받아 송장번호를 입력합니다.</b></td>
			</tr>
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">엑셀파일다운 주문별 설정시 주문번호, 송장번호, 배송사코드는 필수 항목입니다.</td>
			</tr>
			<!-- <tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><b>엑셀파일다운 상품별을 다운받아 송장번호를 입력합니다.</td>
			</tr>
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">엑셀파일다운 상품별 설정시 주문번호, 송장번호, 배송사코드, 일련번호는 필수 항목입니다.</td>
			</tr> -->
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">배송사코드는 쇼핑몰기본관리 > 배송/택배정책에서 해당 택배사를 선택후 수정버튼을 클릭하면 고유번호로 표시되는 번호입니다.</td>
			</tr>
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">입력이 완료된 엑셀파일을 csv 형식으로 저장하여 송장 CSV 파일 올리기를 이용해 송장파일을 올립니다.</td>
			</tr>
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">주문관리를 이용해 송장정보가 정확히 입력되었는지 확인하실 수 있습니다..</td>
			</tr>
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">주문자, 받는이 정보는 이 기능을 이용해 수정하실 수 있습니다.</td>
			</tr>
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">결제정보는 이 기능을 이용해 수정하실 수 없습니다.</td>
			</tr>
		</table>
	</div>
</div>
<script>cssRound("MSG02");</script>