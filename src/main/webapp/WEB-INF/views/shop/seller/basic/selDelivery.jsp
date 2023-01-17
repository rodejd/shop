<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 

<script language="javascript">
	/*
	 * php script
	 */
	 var i, z;
	var fm, selL, selR, tbOver;
	
	function move(direct)
	{
		if (direct=="right"){
			for (i=selL.options.selectedIndex;i<selL.options.length;i++){
				if (selL.options[i].selected==true){
					if (chkOption(selL.options[i].value)) selR.options[selR.options.length] = new Option(selL.options[i].text,selL.options[i].value);
				}
			}
		} else {
			for (i=selR.options.selectedIndex;i<selR.options.length;i++){
				if (selR.options[i].selected==true){
					selR.options.remove(i--);
				}
			}
		}
	}
	
	function chkOption(val)
	{
		for (z=0;z<selR.options.length;z++){
			if (selR.options[z].value==val) return false;
		}
		return true;
	}
	
	function chkForm2(obj)
	{
		if (!chkForm(obj)) return false;
		for (i=0;i<selR.options.length;i++) selR.options[i].selected = true;
		return true;
	}
	
	function registerDelivery()
	{
		popupLayer('popup_delivery?mode=registerDelivery',500,300);
	}
	
	function modifyDelivery()
	{
		var arg;
		if (selL.selectedIndex!=-1){
			arg = "mode=modifyDelivery&deliveryno=" + selL.options[selL.selectedIndex].value;
			popupLayer('popup_delivery?'+arg,500,300);
		} else {
			alert("수정할 택배사를 선택해주세요");
		}
	}
	
	<%-- 2017-08-23 : 배송사 삭제 - 삭제 버튼이 눌리면 실행 --%>
	function removeCourier() {
		if(selL.selectedIndex!=-1) {
			var url = ctx+"/shop/admin/basic/removeCourier.doJson";
			var param = {
				"deliveryno" : selL.options[selL.selectedIndex].value
			};

			$.post(url, param, removeAndRedrowCourier);
		} else {
			alert("삭제할 택배사를 선택해주세요.");
		}
	}
	<%-- 2017-08-23 : 삭제 후 리스트 재출력 --%>
	function removeAndRedrowCourier(result) {
		var courierLeftArea = $('.deliveryTmp');
		var courierRightArea = $('.delivery');
		
		courierLeftArea.children().remove();
		courierRightArea.children().remove();
		
		var courierList = result.gdListDeliveryList;
		
		for(var i in courierList) {
			var option = "<option value=\'" + courierList[i].deliveryno + "\'>" + courierList[i].deliverycomp + "</option>";
			courierLeftArea.append(option);
			
			if(courierList[i].useyn === 'y') {
				courierRightArea.append(option);
			}
		}
	}

	/**
	 * 3 지역별 배송정책 지역 추가
	 */
	function addOver()
	{
		const rowLen = tbOver.rows.length;
		var idx = rowLen / 2;
		oTr = tbOver.insertRow(rowLen);
		oTd = oTr.insertCell(0);

		/*
		var tmp = "아래 지역의 배송비를 <input type=text name=\"infoVO.addDeliveryPrices\" value=\"\" class=\"rline\"> 원으로 지정 <a href=\"javascript:postcode("+idx+");\"><img src=\"/shop/admin/img/btn_area_search.gif\" align=\"absmiddle\" value=\"지역검색하기\" /></a><div class=extext style=\"padding-top:5px\">(우편번호만 입력하세요. 각 지역을 컴마로 구분합니다)</font></div>";	
		oTd.innerHTML = tmp;
		oTd = oTr.insertCell(1);
		oTd.innerHTML = "<a href='javascript:void(0)' onClick='delOver(this)'><img src='/resources/shop/admin/img/i_del.gif'></a>";
		oTr = tbOver.insertRow(tbOver.rows.length);
		oTd = oTr.insertCell();
		oTd.colSpan = 2;
		oTd.innerHTML = "<textarea name=\"infoVO.addDeliveryCities\" style='width:100%;height:50px' required label='차등지역우편번호' id='postcode_"+idx+"'></textarea>";
		 */

		var tmp = `아래 지역의 배송비를 <input type=text name="infoVO.addDeliveryPrices" value=""> 원으로 지정
			<a href="javascript:postcode(\${idx});"><img src="/resources/shop/admin/img/btn_area_search.gif" align="absmiddle" value="지역검색하기" /></a>
			<div><span class="extext">(우편번호만 입력하세요. 각 지역을 컴마로 구분합니다)</span></div>`;
		oTd.innerHTML = tmp;
		oTd = oTr.insertCell(1);
		oTd.innerHTML = `<a href="javascript:void(0)" class="admin-i-del" onclick="delOver(this)">삭제</a>`;
		oTr = tbOver.insertRow(tbOver.rows.length);
		oTd = oTr.insertCell();
		oTd.colSpan = 2;
		oTd.innerHTML = `<textarea name="infoVO.addDeliveryCities" style='width:100%;height:50px' required label='차등지역우편번호' id='postcode_\${idx}'></textarea>`;

		/*
		const rowLen = $(tbOver).find('.row').length;
		const idx = Math.floor(rowLen / 2);
		const rowEl = document.createElement("div");
		rowEl.className = "row";
		const descEl = document.createElement("div");
		const textareaEl = $(`<textarea name='infoVO.addDeliveryCities' style='width:100%;height:50px;' id='postcode_${idx}' label='자동지역우편번호' required>`);
		descEl.innerHTML = `
			아래 지역의 배송비를 <input type="text" name="infoVO.addDeliveryPrices" value="" maxlength="9" onkeydown="onlynumber(event)" onkeyup="removeChar(event)">
			원으로 지정
			<a href="javascript:postcode(${idx});">
				<img src="/resources/shop/admin/img/btn_area_search.gif" title="지역검색하기" />
			</a>
			<span class="extext">(우편번호만 입력하세요. 각 지역을 컴마로 구분합니다)</span>
			<a href="javascript:void(0)" class="admin-i-del" onclick="delOver(this)">삭제</a>`;

		rowEl.append(descEl);
		rowEl.append(textareaEl);

		tbOver.append(rowEl);
*/
	}
	
	function delOver(obj)
	{
		var idx = obj.parentNode.parentNode.rowIndex;
		tbOver.deleteRow(idx+1);
		tbOver.deleteRow(idx);
	}

	function chkDeliveryType(obj){
		console.log(obj);
		if(obj){
			obj.parentNode.getElementsByTagName('span')[0].style.display = obj.parentNode.getElementsByTagName('span')[1].style.display =  "none";
			obj.parentNode.getElementsByTagName('span')[ obj.selectedIndex ].style.display = "inline";
		}
	}

	function initLayer(){
	
		chkDeliveryType(document.getElementsByName("infoVO.defaultPolicyPayment")[0]);
			
		fm = document.forms[0];
		selL = fm["deliveryCompanies"];
		selR = fm["infoVO.useDeliveryComp"];
		tbOver = document.getElementById("tbOver");
	
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
<form method=post action="deliveryIndb" onsubmit="return chkForm2(this)" name="form" >
	<input type=hidden name=mode value="delivery">
	<%--
	<div class="title title_top">
		배송정책
		<span>배송비용 및 배송관련 정책을 정하세요</span> 
	</div>
	
	<div style="padding: 20px 0px 5px 13px">
		<b>1. 기본 배송정책</b>
		<font class=extext>(기본 배송정책을 책정합니다.)</font>
	</div>
	
	<br>
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" id="tbl_delivery">
		<tr>
			<td class="rnd" colspan="12"></td>
		</tr>
	
		<tr class="rndbg">
			<th width="120">배송방법</th>
			<th>배송비</th>
		</tr>
	
		<tr>
			<td class="rnd" colspan="12"></td>
		</tr>
	
		<tr><td colspan=20 height=10></td></tr>
	
		<tr height=30>
			<td class="center ver81">
				<input type="text" name="infoVO.defaultPolicyNm" size="15" value="${fm.infoVO.defaultPolicyNm}" class="line" required maxlength="40" onkeydown="noSpecialCharacters()">
			</td>			
			<td class="ver81">상품가격이 <input type="text" name="infoVO.defaultPolicyFreePrice" value="${fm.infoVO.defaultPolicyFreePrice}" size=9 class="rline" maxlength="9" onkeydown="onlynumber(event)" onkeyup="removeChar(event)"> 원 이상일 때 배송비 무료, 미만일 때
				<select name="infoVO.defaultPolicyPayment" onchange="chkDeliveryType(this)">
					<c:choose>
						<c:when test="${fm.infoVO.defaultPolicyPayment eq '선불'}">
							<option value="선불" selected>선불</option>
						</c:when>
						<c:otherwise>
							<option value="선불" >선불</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fm.infoVO.defaultPolicyPayment eq '후불'}">
							<option value="후불" selected>후불</option>
						</c:when>
						<c:otherwise>
							<option value="후불" >후불</option>
						</c:otherwise>
					</c:choose>
				</select>
					<span style="display:;">
						<input type="text" name="infoVO.defaultPolicyPrice" size=8 class="rline" value="${fm.infoVO.defaultPolicyPrice }" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="9"> 
						원 배송비 부과
					</span>
					<span style="display:;"> 
						배송메세지 : 
						<input type="text" name="infoVO.defaultPolicyMsg" value="${fm.infoVO.defaultPolicyMsg}" size="20" style="width:120" class="lline" onkeydown="noSpecialCharacters()" maxlength="50">
					</span>
			</td>
		</tr>
		<tr>
			<td style="padding-bottom:10px;" class="extext" colspan="20">
				<div style="padding-top:4px">
					&nbsp; &nbsp; * 상품가격은 상품 1개에 대한 판매가격 입니다.
				</div>
			</td>
		</tr>		
		<tr>
			<td colspan="20" height="1" bgcolor="e2e2e2"></td>
		</tr>
		
	</table>

	<br>

	<div style="padding: 10px 0px 5px 13px">
		<b>2. 상품별 배송정책</b> 
		<font class=extext>(상품별로 배송비를 책정할 수 있습니다)</font>
	</div>

	<br>

	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>무료배송 상품</td>
			<td>
				<c:choose>
 					<c:when test="${fm.infoVO.freeByGoods eq 1}">
						<div>
							<input type="radio" name="infoVO.freeByGoods" value="0" class="null">
							무료배송 상품을 같이 주문했을 경우, 무료배송상품만 배송비를 무료로 합니다.
						</div>
						<div>
							<input type="radio" name="infoVO.freeByGoods" value="1" class="null" checked>
							무료배송 상품을 같이 주문했을 경우, 배송비를 무조건 무료로 합니다.
						</div>
 					</c:when>
 					<c:otherwise>
						<div>
							<input type="radio" name="infoVO.freeByGoods" value="0" class="null" checked>
							무료배송 상품을 같이 주문했을 경우, 무료배송상품만 배송비를 무료로 합니다.
						</div>
						<div>
							<input type="radio" name="infoVO.freeByGoods" value="1" class="null">
							무료배송 상품을 같이 주문했을 경우, 배송비를 무조건 무료로 합니다.
						</div>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>상품별 배송비</td>
			<td>
				<c:choose>
					<c:when test="${fm.infoVO.deliveryPriceByGoods eq 1}">
						<div>
							<input type="radio" name="infoVO.deliveryPriceByGoods" value="0" class="null">
							상품을 2개이상 주문시, 상품별 배송비와 기본배송비를 합산한 금액을 배송비로 합니다.
						</div>
						<div>
							<input type="radio" name="infoVO.deliveryPriceByGoods" value="1" class="null" checked>
							상품을 2개이상 주문시, 상품별 배송비와 기본배송비 중 더 큰 배송비로 합니다.
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<input type="radio" name="infoVO.deliveryPriceByGoods" value="0" class="null" checked>
							상품을 2개이상 주문시, 상품별 배송비와 기본배송비를 합산한 금액을 배송비로 합니다.
						</div>
						<div>
							<input type="radio" name="infoVO.deliveryPriceByGoods" value="1" class="null">
							상품을 2개이상 주문시, 상품별 배송비와 기본배송비 중 더 큰 배송비로 합니다.
						</div>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	--%>


	<!-- test -->
	<div class="sub-cont-wrap">
		<div class="title title_top">
			배송정책
			<span>배송비용 및 배송관련 정책을 정하세요</span>
		</div>
	</div>
	<div class="sub-cont-wrap">
		<div class="sub-title">
			1. 기본 배송정책 <span class="extext">(기본 배송정책을 책정합니다.)</span>
		</div>
		<table class="stripe-tbl inp-tbl">
			<colgroup>
				<col class="w-120">
			</colgroup>
			<thead>
				<tr>
					<th>배송방법</th>
					<th>배송비</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type="text" name="infoVO.defaultPolicyNm" size="15" value="${fm.infoVO.defaultPolicyNm}" class="line" required maxlength="40" onkeydown="noSpecialCharacters()">
					</td>
					<td>
						상품가격이
						<input type="text" name="infoVO.defaultPolicyFreePrice" value="${fm.infoVO.defaultPolicyFreePrice}" size=9 class="rline" maxlength="9" onkeydown="onlynumber(event)" onkeyup="removeChar(event)">
						원 이상일 때 배송비 무료,
						미만일 때
						<select name="infoVO.defaultPolicyPayment" onchange="chkDeliveryType(this)">
							<c:choose>
								<c:when test="${fm.infoVO.defaultPolicyPayment eq '선불'}">
									<option value="선불" selected>선불</option>
								</c:when>
								<c:otherwise>
									<option value="선불" >선불</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${fm.infoVO.defaultPolicyPayment eq '후불'}">
									<option value="후불" selected>후불</option>
								</c:when>
								<c:otherwise>
									<option value="후불" >후불</option>
								</c:otherwise>
							</c:choose>
						</select>
						<span>
						<input type="text" name="infoVO.defaultPolicyPrice" size=8 class="rline" value="${fm.infoVO.defaultPolicyPrice }" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="9">
						원 배송비 부과
						</span>
						<span>
						배송메세지 :
						<input type="text" name="infoVO.defaultPolicyMsg" value="${fm.infoVO.defaultPolicyMsg}" size="20" style="width:120px;" class="lline" onkeydown="noSpecialCharacters()" maxlength="50">
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">
						<span class="extext">
							* 상품가격은 상품 1개에 대한 판매가격 입니다.
						</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>	<!-- END sub-cont-wrap -->
	<div class="sub-cont-wrap">
		<div class="sub-title">
			2. 상품별 배송정책
			<span class="extext">(상품별로 배송비를 책정할 수 있습니다)</span>
		</div>
		<div class="div-tbl inp-tbl">
			<div class="row">
				<div class="th w-120">무료배송 상품</div>
				<div>
					<c:choose>
						<c:when test="${fm.infoVO.freeByGoods eq 1}">
							<ul class="empty-ul">
								<li>
									<label>
										<input type="radio" name="infoVO.freeByGoods" value="0">
										무료배송 상품을 같이 주문했을 경우, 무료배송상품만 배송비를 무료로 합니다.
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="infoVO.freeByGoods" value="1" checked>
										무료배송 상품을 같이 주문했을 경우, 배송비를 무조건 무료로 합니다.
									</label>
								</li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="empty-ul">
								<li>
									<label>
										<input type="radio" name="infoVO.freeByGoods" value="0"checked>
										무료배송 상품을 같이 주문했을 경우, 무료배송상품만 배송비를 무료로 합니다.
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="infoVO.freeByGoods" value="1">
										무료배송 상품을 같이 주문했을 경우, 배송비를 무조건 무료로 합니다.
									</label>
								</li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="row">
				<div class="th w-120">상품별 배송비</div>
				<div>
					<c:choose>
						<c:when test="${fm.infoVO.deliveryPriceByGoods eq 1}">
							<ul class="empty-ul">
								<li>
									<label>
										<input type="radio" name="infoVO.deliveryPriceByGoods" value="0" class="null">
										상품을 2개이상 주문시, 상품별 배송비와 기본배송비를 합산한 금액을 배송비로 합니다.
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="infoVO.deliveryPriceByGoods" value="1" class="null" checked>
										상품을 2개이상 주문시, 상품별 배송비와 기본배송비 중 더 큰 배송비로 합니다.
									</label>
								</li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="empty-ul">
								<li>
									<label>
										<input type="radio" name="infoVO.deliveryPriceByGoods" value="0" class="null" checked>
										상품을 2개이상 주문시, 상품별 배송비와 기본배송비를 합산한 금액을 배송비로 합니다.
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="infoVO.deliveryPriceByGoods" value="1" class="null">
										상품을 2개이상 주문시, 상품별 배송비와 기본배송비 중 더 큰 배송비로 합니다.
									</label>
								</li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

		</div>

	</div>	<!-- END sub-cont-wrap -->
	
	<jsp:include page="_deliveryByCity.jsp"></jsp:include>
	
	<jsp:include page="_deliveryCompany.jsp"></jsp:include>
	
	<div class="rndline2"></div>
	
	<div class=button> 
		<input type=image src="/resources/shop/admin/img/btn_register.gif">
	</div>

</form>
<script>
initLayer();
</script>
<script src="/resources/js/jquery/jquery-1.10.2.min.js	"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script>
    function postcode(i) {
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
                var str = $("#postcode_"+i).val();
                
                <%-- 2017-08-24 : 지역별 배송정책을 도시 기준으로 입력하기 위해 우편번호(zonecode) 를 시도(sido) 로 변경 --%>
                if(str == ""){
                	document.getElementById('postcode_'+i).value = data.sido; //5자리 새우편번호 사용
                }else{
                	document.getElementById('postcode_'+i).value = str + "," +data.sido; //5자리 새우편번호 사용
                }
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('postcode_'+i).focus();
            }
        }).open();
    }
</script>
