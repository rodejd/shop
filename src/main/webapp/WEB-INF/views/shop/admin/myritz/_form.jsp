<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 
<style>
	.title2 {
		font-weight:bold;
		padding-bottom:5px;
	}
	.small1 {
		padding-top:0px !important;
	    font-size: 11px;
	    letter-spacing: -1px;
	}
</style>
<script>
    function Postcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('address_sub').focus();
            }
        }).open();
    }
</script>

<script>
	function cal_repay(repay,price,i){
		var tmp = price - repay;
		document.getElementsByName('repay')[0].value=tmp;
	}
	
	function chkCancel(m)
	{
		var sno = new Array();
		var el = document.getElementsByName('chk[]');
		for (var i=0;i<el.length;i++) {
			if (el[i].checked) {
				sno[sno.length] = el[i].value;
			}
		}
		if (sno.length==0){
			alert("선택취소할 상품을 선택해주세요");
			return;
		}
// 		_ID('layer_cancel').style.display = "block";
// 		document.all["ifrmCancel"].src = "/shop/admin/order/orderCancel?m="+m+"&ifrmScroll=1&ordno=${popupVO.ordno}&sno=" + sno.join();
		var url = "/shop/admin/order/orderCancel?m="+m+"&ordno=${popupVO.ordno}&sno=" + sno.join();
		var viewFlg = '${param.viewFlg}';
		ajaxCallGet(url, 'layer_cancel', function(data){
			$("#layer_cancel").empty().append(data);
			$("#layer_cancel").show();
			table_design_load();
			$('#cancelConfirmBtn').on('click', function(){
				if ( chkForm($('#orderCancelForm')[0]) ) {
					ajaxCallJsonPost("/shop/admin/order/indb.cancel", 'orderCancelForm', function(data){
						if (data.RESULT) {
							alert('선택하신 상품이 취소/반품 처리되었습니다.');
						} else {
							alert(data.RESULT_MSG);
							return false;
						}
					});
					setTimeout(function(){window.location.reload();}, 500);
				}
			});
		});
	}
	function orderPrint(ordno,type)
	{
		if (!type){
			alert("인쇄할 문서 종류를 선택하세요");
			return;
		}
 		var orderPrint = window.open("_paper?ordnos=" + ordno + "&type=" + type+"&listType=list","orderPrint","width=750,height=600,scrollbars=1");
 		orderPrint.window.print();
	}
	function escrow_confirm()
	{
		alert("확인해야함");
// 		var obj = document.ifrmHidden;
// 		obj.location.href = "../../order/card/<?=$cfg[settlePg]?>/escrow_gate.php?ordno=<?=$ordno?>";
	}
	function chk_step(val){
	
		var ea =  document.getElementsByName('ea');
		var price =  document.getElementsByName('price');
		var supply =  document.getElementsByName('supply');
	
		for(var i=0;i<ea.length;i++){
			if(val == 0){
				ea[i].style.background = "#ffffff";
				ea[i].readOnly = false;
	
				price[i].style.background = "#ffffff";
				price[i].readOnly = false;
	
				supply[i].style.background = "#ffffff";
				supply[i].readOnly = false;
			}else{
				ea[i].style.background = "#e3e3e3";
				ea[i].readOnly = true;
	
				price[i].style.background = "#e3e3e3";
				price[i].readOnly = true;
	
				supply[i].style.background = "#e3e3e3";
				supply[i].readOnly = true;
	
			}
		}
	}
	function registerDelivery()
	{
		var sno = new Array();
		var el = document.getElementsByName('chk[]');
		for (var i=0;i<el.length;i++) if (el[i].checked) sno[sno.length] = el[i].value;
		if (sno.length==0){
			alert("배송정보를 입력할 상품을 선택해주세요");
			return;
		}
		_ID('layer_cancel').style.display = "block";
		ifrmCancel.location.href = "ifrm.delivery.jsp?ifrmScroll=1&ordno=${popupVO.ordno}&chk=" + sno.join();
	}
	
	/*** Taxbill 정보 출력 ***/
	function getTaxbill(doc_number)
	{
		var print = function(){
			var req = ajax.transport;
			if (req.status == 200){
				var jsonData = eval( '(' + req.responseText + ')' );
				document.getElementById('tax1').innerHTML += (jsonData.status_txt != null ? ' - ' + jsonData.status_txt : '');
				if (jsonData.tax_path != null) document.getElementById('tax1').innerHTML +=" <a href=\"javascript:popup('" + jsonData.tax_path + "',750,600);\">[세금계산서 인쇄]</a>";
				document.getElementById('tax2').innerHTML += (jsonData.mtsid != null ? '<br>식별번호 : ' + jsonData.mtsid : '');
			}
			else {
				var msg = req.getResponseHeader("Status");
				document.getElementById('tax1').title = msg;
				document.getElementById('tax1').innerHTML += '<font class=small color=444444> - 로딩중에러</font>';
			}
		}
		var ajax = new Ajax.Request("../order/tax_indb.jsp?mode=getTaxbill&doc_number=" + doc_number + "&dummy=" + new Date().getTime(), { method: "get", onComplete: print });
	}
	
	function deleteOrder(ordno) {
		var msg = '주문삭제는 이 주문데이타를 단순히 삭제만 하는 기능입니다.\n\n따라서 바로 삭제를 하면 이 주문에 따른 재고, 적립금, 쿠폰은 환원이 안됩니다.\n\n\재고, 적립금, 쿠폰을 환원하려면 반드시 주문취소(선택한 상품취소)를 먼저 해주세요.\n\n주문취소가 되면 원래대로 재고, 적립금, 쿠폰이 환원됩니다.\n\n그리고 주문삭제를 하시기 바랍니다.\n\n한번 삭제된 주문은 복구가 불가능합니다. 신중히 삭제하세요.\n\n선택하신 주문[' + ordno +']을 정말로 삭제하시겠습니까?';
		if ( confirm(msg) ){
			var paramData = {
				ordno : ordno
			,	mode : 'delOrder'
			};
			var viewFlg = '${param.viewFlg}';
			ajaxCallJson("/shop/admin/order/indb.cancel", paramData, function(data){
				if (data.RESULT) {
					alert('선택하신 주문이 정상적으로 삭제되었습니다.');
					if ('view' == viewFlg) {
						window.location.href = ctx+"/shop/admin/order/list?mode=group&period=0&first=1";
					} else {
						opener.window.location.reload(true);
						window.close();
					}
				} else {
					alert(data.RESULT_MSG);
					return false;
				}
			});
		} else {
			return false;
		}

	}
</script>

<c:if test="${fn:length(popupVO.formTarget) >0 }">
	<script>
		$( document ).ready(function() {
			alert("${popupVO.formTarget}");
		});
  	</script>
</c:if>

<div class="title title_top">주문상세내역<span>이 주문에 대한 상세한 내역을 조회하고 수정하실 수 있습니다</span></div>

<table width=100% cellpadding=0 cellspacing=0>
	<tr>
		<td style="padding:5px 10px;background:#f7f7f7;margin:10px 0;border:3px solid #627dce;">
			<table width=100%>
				<tr>
					<td id="orderInfoBox">
						<font class=def>주문번호:</font> <span style="color:#4f67af;font:bold 11px verdana">${popupVO.ordno}</span>
					</td>
					<td align=right>
						<select name="order_print" class="Select_Type1" style="font:8pt 돋움">
							<option value=""> - 인쇄선택 - </option>
							<option value="report"> 주문내역서  </option>
							<option value="reception"> 간이영수증  </option>
							<!-- <option value="tax"> 세금계산서  </option> -->
						</select>
						<a href="javascript:orderPrint(${popupVO.ordno}, document.getElementsByName('order_print')[0].value);">
							<img src="/resources/shop/admin/img/btn_print.gif" border="0" align="absmiddle">
						</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height=8></td>
	</tr>
</table>

<form name=frmOrder action="/shop/admin/myritz/updateOrder" method=post>
	<input type=hidden name=mode value="modOrder">
	<input type=hidden name=ordno value="${popupVO.ordno}">
	<input type=hidden name=referer value="${popupVO.referer}">
	<input type=hidden name=step2 value="${popupVO.rtData.step2}">
	<input type=hidden name=viewFlg value="${param.viewFlg}">

	<%-- 주문 별 상품 정보 테이블 --%>
	<jsp:include page="_order_detail/_item_table.jsp"></jsp:include>

	<table cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=60% style="padding:5px 0 0 12px">
				<a href="javascript:chkCancel(0);"><img src="/resources/shop/admin/img/btn_cancelorder.gif" border=0></a>
				<c:if test="${popupVO.rtData.step > 1 }">
					<a href="javascript:chkCancel(1);"><img src="/resources/shop/admin/img/btn_exchangeorder.gif" border=0></a>
				</c:if>
			</td>
			<td width=40% align=right style="padding-right:5px">
				<!-- dfffff원래 주석 <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=order&no=3',870,800)"><img src="/resources/shop/admin/img/btn_cancel_manual.gif" border=0></a> -->
			</td>
		</tr>
	</table>


	<!-- 개별송장입력 시작 -->
	<c:if test="${'0' != popupVO.deliveryBasis }">
		<div style="padding:5px 0 0 12px">
		<c:if test="${popupVO.rtData.step > 0 }">
			<a href="javascript:registerDelivery()"><img src="/resources/shop/admin/img/btn_input_delinumber.gif" border=0></a>
		</c:if>
		</div>
	</c:if>
	<!-- 개별송장입력 끝 -->
	<div id=layer_cancel style="display:none;padding-top:10px">
<!-- 		<iframe id=ifrmCancel name=ifrmCancel style="width:100%;height:0;" frameborder=0></iframe> -->
	</div>
	
	<p>

	<%-- 현주문상태 테이블 --%>	
	<jsp:include page="_order_detail/_order_status_table.jsp"></jsp:include>
	
	<p>
	
	<c:if test="${popupVO.tmpRt8 != null and popupVO.tmpRt8Size > 0}">
		<div class=title2>
			&nbsp;
			<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle>
			<font color=508900>쿠폰사용정보</font>
		</div>
		<table class=tb cellpadding=4 cellspacing=0>
			<tr height=25 bgcolor=#2E2B29>
				<td bgcolor="#F6F6F6" align=center>쿠폰번호</td>
				<td bgcolor="#F6F6F6" align=center>쿠폰명</td>
				<td bgcolor="#F6F6F6" align=center>할인/적립</td>
				<td bgcolor="#F6F6F6" align=center>사용일시</td>
			</tr>
			<col align=center><col align=center><col align=center><col align=center>
			<c:forEach items="${popupVO.tmpRt8}" var="tmpRt">
				<tr>
					<td nowrap>${tmpRt.couponcd}</td>
					<td nowrap>${tmpRt.coupon}</td>
					<td nowrap><font class=ver8 color=444444></font></td>
					<td nowrap>${tmpRt.regdt}</td>
				</tr>
			</c:forEach>
		</table><p>
	</c:if>

	<c:if test="${popupVO.tmpRt9 != null and popupVO.tmpRt9Size > 0 }">
		<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>환불내역정보</font> <font class=small1 color=6d6d6d>아래는 이미 환불완료된 내역입니다</font></div>
		<table border=2 bordercolor=#F43400 style="border-collapse:collapse" width=100%>
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
						<c:forEach items="${popupVO.tmpRt9}" var="tmpRt9" varStatus="status">
							<tr>
								<td  style="padding:2px 10px" rowspan=2 align=center><font class=ver7 color=444444>${status.count}</font></td>
								<td align=center><font class=ver8>₩<fmt:formatNumber value="${tmpRt9.rfee}" pattern="#,###"/></font></td>
								<td align=center><font class=ver8 color=EA0095><b>₩<fmt:formatNumber value="${tmpRt9.rprice}" pattern="#,###"/></b></font></td>
								<td align=center><font class=ver8>₩<fmt:formatNumber value="${tmpRt9.remoney}" pattern="#,###"/></font></td>
								<td align=center><font class=ver81><fmt:formatDate value="${tmpRt9.ccdt}" pattern="yyyy.MM.dd HH:mm:ss"/></font></td>
								<td align=center><font class=small1 color=0074BA><b>환불완료</b></font></td>
							</tr>
							<tr>
								<td colspan=3>
									<div style='float:left'></div>
								</td>
								<td colspan=2 align=center>
									<font class=small1 color=444444>
										<b>환불계좌</b>:${tmpRt9.bankPro}
										&nbsp;${tmpRt9.bankaccount}
										&nbsp;&nbsp;<b>예금주</b>: ${tmpRt9.bankuser}
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
		<col span=3 valign=top>
		<tr>
			<td width=50%>	
				<%-- 주문자정보 테이블 --%>		
				<jsp:include page="_order_detail/_order_member_table.jsp"></jsp:include>
			</td>
			<td width=10 nowrap></td>
			<td width=50%>
				<%-- 수령자정보 테이블 --%>	
				<jsp:include page="_order_detail/_order_receiver_table.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height=15></td>
		</tr>
		<tr>
			<td>
				<%-- 결제정보 테이블	 --%>
				<jsp:include page="_order_detail/_billing_table.jsp"></jsp:include>
			</td> 
			<td></td>

 			<td>
				<%-- 결제금액정보 테이블 --%>	
				<jsp:include page="_order_detail/_payment_info.jsp"></jsp:include>
			</td> 

		</tr>
		<tr>
			<td height=15> </td>
		</tr>
		<tr>
			<td colspan="3">
				<%-- 배송정보 테이블 --%>	
				<jsp:include page="_order_detail/_delivery_table.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height=15></td>
		</tr>
		<tr>
			<td>
				<%-- 요청사항/상담메모 테이블 --%>		
				<jsp:include page="_order_detail/_consultation_table.jsp"></jsp:include>
			</td>
			<td></td>
			<td>
				<%-- 취소내역리스트 테이블 --%>	
				<jsp:include page="_order_detail/_order_cancel_history_table.jsp"></jsp:include>
			</td>
		</tr>	
	</table>
	
	<div class=button>
	<input type=image src="/resources/shop/admin/img/btn_modify.gif" class="_submit-btn">
	<c:choose>
		<c:when test="${ 'view' == param.viewFlg }">
			<a href="/shop/admin/myritz/myritzOrderList?mode=group&period=0&first=1"><img src="/resources/shop/admin/img/btn_list.gif"></a>
		</c:when>
		<c:otherwise>
			<a href='javascript:void(0);'><img src='/resources/shop/admin/img/btn_list.gif' onclick="window.close();"></a>
		</c:otherwise>
	</c:choose>
	</div>

</form>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script>
	// item_table과 delivery_table의 배송, 추가배송비를 동일하게 처리하기 위해 스크립트 위치 변경
	addDelivery();
	function addDelivery() {
		var $addDeliveryTags = $('._add-delivery-price');
		var $deliveryTags = $('._delivery-price');
		var $addDeliveryTags2 = $('._add-delivery-price2');
		var $deliveryTags2 = $('._delivery-price2');	
		var flag = false;
		var myritzCd2 = "";
		for (var i = 0; i < $addDeliveryTags.length; i++) {
			
			var addDeliveryTag = $addDeliveryTags[i];
			var deliveryTag = $deliveryTags[i];
			var addDeliveryTag2 = $addDeliveryTags2[i];
			var deliveryTag2 = $deliveryTags2[i];
			
			var goodsno = addDeliveryTag.dataset.goodsno;			
			var myritzCd1 = deliveryTag.dataset.myritzcd;
			// 같은 판매사의 상품인 경우 배송비, 추가배송비는 한번만 적용
			if (myritzCd1 == myritzCd2){
				addDeliveryTag.innerHTML = '0';
				deliveryTag.innerHTML = '0';
				addDeliveryTag2.innerHTML = '0';
				deliveryTag2.innerHTML = '0';			
			}
			/*
			if ($('._add-delivery-price' + goodsno).text() != 0 && flag) {
				addDeliveryTag.innerHTML = '0';
				deliveryTag.innerHTML = '0';
				flag = false;
			} else {
				flag = true;
			}
			*/
			myritzCd2 = myritzCd1;
		}
		
	}

    function postcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('address').focus();
            }
        }).open();
    }
    
    <%-- 2017-09-01 관리자페이지 배송상태추적 링크 처리 
    	셀렉트 박스에 선택된 옵션이 바뀌면 해당 옵션의 data 속성에 있는 url 주소를 배송상태추적 페이지 url 로 지정한다.
    --%>
    
    adminMyritzOrderAddEvent();
    
    function adminMyritzOrderAddEvent() {
	    $('._delivery-select').on('change', deliveryUrlChange);
	    $('._delivery-code').on('keydown', onlynumber).on('keyup', removeHangul);
		$('._submit-btn').on('click', checkInvoiceInput);
    }

    function deliveryUrlChange() {
    	var selectDeliveryUrl = $('._delivery-select option:selected').data('deliveryUrl');
    	$('._delivery-tracking').attr('href', selectDeliveryUrl);
    }
    
    function checkInvoiceInput() {
    	var invoiceInputFlag = true;
    	var $deliverySelect = $('._delivery-select');
    	var $deliveryCode = $('._delivery-code');
    	
    	var deliverySelect;
    	var deliveryCode;
    	for (var i = 0; i < $deliverySelect.length; i++) {
    		deliverySelect = $deliverySelect[i];
    		deliveryCode = $deliveryCode[i];
    		
    		// 배송사와 송장번호 중 하나만 입력되어 있으면 처리불가
    		if (deliverySelect.value != '00' && deliveryCode.value == '') {
    			invoiceInputFlag = false;
    			break;
    		} 
    		if (deliverySelect.value == '00' && deliveryCode.value != '') {
    			invoiceInputFlag = false;
    			break;
    		} 

    	}
    	
    	if (!invoiceInputFlag) {
    		alert('택배사와 송장번호를 모두 입력해주셔야 합니다.');
    	}
    	
    	return invoiceInputFlag;
    	
    }
</script>