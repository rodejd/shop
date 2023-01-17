<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>

<c:set var = "i"  value="0"></c:set>
<c:set var = "deliverynm" value="${deliveryVO.deliverynm}"></c:set>
<c:set var = "free" value="${deliveryVO.free}"></c:set>
<c:set var = "deliveryType" value="${deliveryVO.deliveryType}"></c:set>
<c:set var = "default1" value="${deliveryVO.default1}"></c:set>
<c:set var = "defaultMsg" value="${deliveryVO.defaultMsg}"></c:set>
<c:set var = "freeDelivery" value="${deliveryVO.freeDelivery}"></c:set>
<c:set var = "goodsDelivery" value="${deliveryVO.goodsDelivery}"></c:set>
<c:set var = "shippingFee" value="${deliveryVO.shippingFee}"></c:set>
<c:set var = "stOver" value="${deliveryVO.stOver}"></c:set>
<c:set var = "stZipcodeName" value="${deliveryVO.stZipcodeName}"></c:set>
<c:set var = "rtListExtraPolicy" value="${deliveryPolicyList}"></c:set>
<c:set var = "rtListDelivery" value="${deliveryList }"></c:set>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
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
			var url = ctx + "/shop/admin/basic/removeCourier.doJson";
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
	
	function addOver()
	{
		var idx = tbOver.rows.length / 2;
		oTr = tbOver.insertRow(tbOver.rows.length);
		oTd = oTr.insertCell(0);	
		var tmp = "아래 지역의 배송비를 <input type=text name=\"stOver\" value=\"\" class=\"rline\"> 원으로 지정 <a href=\"javascript:postcode("+idx+");\"><img src=\"/shop/admin/img/btn_area_search.gif\" align=\"absmiddle\" value=\"지역검색하기\" /></a><div class=extext style=\"padding-top:5px\">(우편번호만 입력하세요. 각 지역을 컴마로 구분합니다)</font></div>";	
		oTd.innerHTML = tmp;
		oTd = oTr.insertCell(1);
		oTd.innerHTML = "<a href='javascript:void(0)' onClick='delOver(this)'><img src='/resources/shop/admin/img/i_del.gif'></a>";
		oTr = tbOver.insertRow(tbOver.rows.length);
		oTd = oTr.insertCell();
		oTd.colSpan = 2;
		oTd.innerHTML = "<textarea name=\"stZipcodeName\" style='width:100%;height:50px' required label='차등지역우편번호' id='postcode_"+idx+"'></textarea>";
	}
	
	function delOver(obj)
	{
		var idx = obj.parentNode.parentNode.rowIndex;
		tbOver.deleteRow(idx+1);
		tbOver.deleteRow(idx);
	}
	
<%-- 2017-08-22 주석처리 : 1번 기본배송정책을 제외한 이외의 기본배송정책 리스트는 사용하지 않는 것으로 논의 후 주석처리 함.
	function addDelivery(){
		var tbl_delivery = document.getElementById("tbl_delivery");
		oTr = tbl_delivery.insertRow(tbl_delivery.rows.length-1);
		oTr.height = "30";
		
		oTd = oTr.insertCell(0);
		oTd.className = "center";
		oTd.innerHTML = tbl_delivery.rows.length - 6;
	
		oTd = oTr.insertCell(1);
		oTd.className = "center";	
		oTd.innerHTML = "<input type=text name=\"rDelivery\" size=10 required>";
	
		oTd = oTr.insertCell(2);
		oTd.className="ver81";
		oTd.innerHTML = "총 구매액이 ₩<input type=text name=\"rFree\" size=9 class=right onkeydown=\"onlynumber();\"> 이상일 때 배송비 무료, 미만일 때 <select name=\"rDeliType\" onchange=\"chkDeliveryType(this)\"><option value=\"선불\">선불</option><option value=\"후불\">착불</option></select><span> ₩<input type=text name=\"rDefault\" size=8 class=right onkeydown=\"onlynumber()\"> 배송비 부과</span><span style=\"display:none;\"> 배송 메시지 : <input type=\"text\" name=\"rDefaultMsg\" size=\"18\" class=\"lline\"></span>";
	
		oTd = oTr.insertCell(3);
		oTd.className = 'center';
		oTd.innerHTML = "<a href=\"javascript:void(0)\" onClick=\"delDelivery(this)\"><img src=\"../img/btn_delete_new.gif\"></a>";
	
	}
--%>
	function chkDeliveryType(obj){
		if(obj){
		obj.parentNode.getElementsByTagName('span')[0].style.display = obj.parentNode.getElementsByTagName('span')[1].style.display =  "none";
		obj.parentNode.getElementsByTagName('span')[ obj.selectedIndex ].style.display = "inline";
		}
	}

<%-- 2017-08-22 주석처리 : 1번 기본배송정책을 제외한 이외의 기본배송정책 리스트는 사용하지 않는 것으로 논의 후 주석처리 함.
	function delDelivery(obj){
		var tbl_delivery = document.getElementById("tbl_delivery");
		var idx = obj.parentNode.parentNode.rowIndex;
		tbl_delivery.deleteRow(idx);
	}
--%>
	function initLayer(){
	
		chkDeliveryType(document.getElementsByName("deliveryType")[0]);
			
		chkDeliveryType(document.getElementsByName("rDeliType")[0]);
		fm = document.forms[0];
		selL = fm["deliveryTmp"];
		selR = fm["delivery"];
		tbOver = document.getElementById("tbOver");
	
	}
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

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
<form method=post action="delivery/indb" onsubmit="return chkForm2(this)" name="form" >
	<input type=hidden name=mode value="delivery">

	<div class="sub-cont-wrap">
		<div class="title title_top">
			배송정책<span>배송비용 및 배송관련 정책을 정하세요</span>
		<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=3',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
		</div>
	</div>
<%-- 	<div style="padding: 20px 0px 5px 13px">
		<b>1. 기본 배송정책</b><font class=extext>(기본 배송정책을 책정합니다.)</font>
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
				<input type="text" name="deliverynm" size="15" value="${deliverynm}" class="line" required maxlength="40" onkeydown="noSpecialCharacters()">
			</td>
			<td class="ver81">상품가격이 ₩<input type="text" name="free" value="${free}" size=9 class="rline" maxlength="9" onkeydown="onlynumber(event)" onkeyup="removeChar(event)"> 이상일 때 배송비 무료, 미만일 때
				<select name="deliveryType" onchange="chkDeliveryType(this)">
				<c:choose>
					<c:when test="${deliveryType eq '선불'}">
						<option value="선불" selected>선불</option>
					</c:when>
					<c:otherwise>
						<option value="선불" >선불</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${deliveryType eq '후불'}">
						<option value="후불" selected>후불</option>
					</c:when>
					<c:otherwise>
						<option value="후불" >후불</option>
					</c:otherwise>
				</c:choose>
				</select>
				<c:if test="${deliveryType eq '선불' }">
					<span style="display:;"> ₩<input type="text" name="default1" size=8 class="rline" value="${default1}" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="9"> 배송비 부과</span>
					<span style="display:none;"> 배송메세지 : <input type="text" name="defaultMsg" value="${defaultMsg}" size="20" style="width:120" class="lline" onkeydown="noSpecialCharacters()" maxlength="50"></span>
				</c:if>
				<c:if test="${deliveryType eq '후불' }">
					<span style="display:none;"> ₩<input type="text" name="default1" size=8 class="rline" value="${default1}" onkeydown="onlynumber(event)" onkeyup="removeChar(event)"> 배송비 부과</span>
					<span style="display:;"> 배송메세지 : <input type="text" name="defaultMsg" value="${defaultMsg}" size="20" style="width:120" class="lline" onkeydown="noSpecialCharacters()" maxlength="50"></span>
				</c:if>		
			</td>
		</tr>
		<tr>
			<td style="padding-bottom:10px;" class="extext" colspan="12">
				<div style="padding-top:4px">
					&nbsp; &nbsp; * 상품가격은 상품 1개에 대한 판매가격 입니다.
				</div>
			</td>
		</tr>	
		
2017-08-22 주석처리 : 1번 기본배송정책을 제외한 이외의 기본배송정책 리스트는 사용하지 않는 것으로 논의 후 주석처리 함.
		<c:if test="${rtListExtraPolicy !=null and (fn:length(rtListExtraPolicy) !=0) }">
			<c:forEach items="${rtListExtraPolicy}" var = "rtList" varStatus="i">
					<tr height=30>
			<td class="center">${i.index + 2}</td>
			<td class="center">
				<input type=text name="rDelivery" size=10 value="${rtList.rDelivery}" class="line" required>
			</td>
			<td class="ver81">총 구매액이 ₩<input type=text name="rFree" size=9 class="rline" value="${rtList.rFree}"> 이상일 때 배송비 무료, 미만일 때
				<select name="rDeliType" onchange="chkDeliveryType(this)">
					<c:choose>
						<c:when test="${rtList.rDeliType eq '선불'}">
							<option value="선불" selected>선불</option>
						</c:when>
						<c:otherwise>
							<option value="선불" >선불</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${rtList.rDeliType eq '후불'}">
							<option value="후불" selected>착불</option>
						</c:when>
						<c:otherwise>
							<option value="후불" >착불</option>
						</c:otherwise>
					</c:choose>
					
				</select>
				<c:if test="${rtList.rDeliType eq '선불' }">
					<span style="display:;"> ₩<input type=text name="rDefault" size=8 class="rline" value="${rtList.rDefault}" onkeydown="onlynumber()"> 배송비 부과</span>
					<span style="display:none;"> 배송메세지 : <input type="text" name="rDefaultMsg" value="${rtList.rDefaultMsg}" size="20" style="width:120" class="lline"></span>
				</c:if>
				<c:if test="${rtList.rDeliType eq '후불'}">
				<span style="display:none;"> ₩<input type=text name="rDefault" size=8 class="rline" value="${rtList.rDefault}" onkeydown="onlynumber()"> 배송비 부과</span>
				<span style="display:;"> 배송메세지 : <input type="text" name="rDefaultMsg" value="${rtList.rDefaultMsg}" size="20" style="width:120" class="lline"></span>
				</c:if>
			</td>
			<td class="center"><a href="javascript:void(0)" onClick="delDelivery(this)"><img src="../img/btn_delete_new.gif"></a></td>
		</tr>
			</c:forEach>
		</c:if>


		<tr><td colspan=20 height=10></td></tr>
		<tr><td colspan=20 height=1 bgcolor=e2e2e2></td></tr>
	</table>
 --%>
<%-- 2017-08-22 주석처리 : 1번 기본배송정책을 제외한 이외의 기본배송정책 리스트는 사용하지 않는 것으로 논의 후 주석처리 함.	
	 <div style="padding:10px 0px 20px 0px" align="center"><a href="javascript:addDelivery();"><img align="absmiddle" src="../img/btn_delivery_plus.gif"  class="null" /></a></div> 
--%>

<%-- 	<br>
	<div style="padding: 10px 0px 5px 13px"><b>2. 상품별 배송정책</b> <font class=extext>(상품별로 배송비를 책정할 수 있습니다)</font></div>
	<br>
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>무료배송 상품</td>
			<td>
				<c:choose>
					<c:when test="${ freeDelivery eq '0'}">
					<div><input type="radio" name="freeDelivery" value="0" class="null" checked>무료배송 상품을 같이 주문했을 경우, 무료배송상품만 배송비를 무료로 합니다.</div>
					</c:when>
					<c:otherwise>
					<div><input type="radio" name="freeDelivery" value="0" class="null" >무료배송 상품을 같이 주문했을 경우, 무료배송상품만 배송비를 무료로 합니다.</div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ freeDelivery eq '1'}">
					<div><input type="radio" name="freeDelivery" value="1" class="null" checked>무료배송 상품을 같이 주문했을 경우, 배송비를 무조건 무료로 합니다.</div>
					</c:when>
					<c:otherwise>
					<div><input type="radio" name="freeDelivery" value="1" class="null" >무료배송 상품을 같이 주문했을 경우, 배송비를 무조건 무료로 합니다.</div>
					</c:otherwise>
				</c:choose>
			
			</td>
		</tr>
		<tr>
			<td>상품별 배송비</td>
			<td>
				<c:choose>
					<c:when test="${ goodsDelivery eq '0'}">
					<div><input type="radio" name="goodsDelivery" value="0" class="null" checked>상품을 2개이상 주문시, 상품별 배송비와 기본배송비를 합산한 금액을 배송비로 합니다.</div>
					</c:when>
					<c:otherwise>
					<div><input type="radio" name="goodsDelivery" value="0" class="null" >상품을 2개이상 주문시, 상품별 배송비와 기본배송비를 합산한 금액을 배송비로 합니다.</div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ goodsDelivery eq '1'}">
					<div><input type="radio" name="goodsDelivery" value="1" class="null" checked>상품을 2개이상 주문시, 상품별 배송비와 기본배송비 중 더 큰 배송비로 합니다.</div>
					</c:when>
					<c:otherwise>
					<div><input type="radio" name="goodsDelivery" value="1" class="null" >상품을 2개이상 주문시, 상품별 배송비와 기본배송비 중 더 큰 배송비로 합니다.</div>
					</c:otherwise>
				</c:choose>
				<input type=hidden name='basis' value='0' />
			</td>
		</tr>
		
	</table>
 --%>



<%-- 
	<div style="padding: 25px 0px 5px 13px"><b>3. 지역별 배송정책</b> <font class=extext>(도서산간 등 지역별로 배송금액을 지정할 수 있습니다)</font></div>
	<br>
	<table class="tb">
		<col class=cellC><col class=cellL>
		<tr>
			<td>지역별 배송금액</td>
			<td>
				<table id="tbOver" width="100%">
					<col><col align="right">
					<c:set var="tmpOverValue"  value=""></c:set>
					<c:set var="tmpZipcodeNameValue"  value=""></c:set>
					
					<c:set var = "i" value="0"></c:set>
					<c:if test="${fn:length(stZipcodeName)==0 or fn:length(stZipcodeName)==0 }">
						<tr>
							<td>
								아래 지역의 배송비를 <input type="text" name="over" value="" class="rline"> 원으로 지정 <a href="javascript:postcode(${i});" align="absmiddle" value="지역검색하기" /></a>
								<div class=extext style="padding-top:5px"><font>(반드시 <b>'지역검색하기'</b>를 눌러서 지역을 추가하세요)</font></div>
							</td>
							<td>
							<c:choose>
								<c:when test="${i==0}">
									<a href="javascript:addOver()"><img src="/resources/shop/admin/img/btn_placeadd.gif"></a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" onClick="delOver(this)"><img src="/resources/shop/admin/img/i_del.gif"></a>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<td colspan=2><textarea name="overZipcodeName" style="width:100%;height:50px" class="tline"  id="postcode_${i}"></textarea>
						</tr>
					</c:if>
			${fn:length(stOver)}
			<c:forEach items="${stOver}" var = "stOver" varStatus="status" >   <!-- warning - length -->
					<c:set var="tmpOverValue" value="${stOver}"></c:set>
					<c:set var="tmpZipcodeNameValue" value="${stZipcodeName[status.index]}"></c:set>
					<c:out value="${stOver}"></c:out>
					<tr>
							<td>
								아래 지역의 배송비를 <input type="text" name="stOver" value="${tmpOverValue}" class="rline" maxlength="9" onkeydown="onlynumber(event)" onkeyup="removeChar(event)"> 원으로 지정 <a href="javascript:postcode(${status.index});"><img src="/resources/shop/admin/img/btn_area_search.gif" align="absmiddle" value="지역검색하기" /></a>
								<div class=extext style="padding-top:5px"><font>(반드시 <b>'지역검색하기'</b>를 눌러서 지역을 추가하세요)</font></div>
							</td>
							<td>
								<c:choose>
								
								<c:when test="${status.index==0}">
									<a href="javascript:addOver()"><img src="/resources/shop/admin/img/btn_placeadd.gif"></a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" onClick="delOver(this)"><img src="/resources/shop/admin/img/i_del.gif"></a>
								</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td colspan=2><textarea name="stZipcodeName" readonly="readonly" style="width:100%;height:50px" class="tline" id="postcode_${status.index}">${tmpZipcodeNameValue}</textarea>
						</tr>
							
			</c:forEach>
			
		
				</table>
	
				<div class="extext_t">* 일반적으로 지역별 배송금액을 차별화하는 경우는 섬, 도서산간 등에 해당됩니다. (제주도,울릉도 등)</div>
	
			</td>
		</tr>
	</table> --%>



	<!--
	<div style="padding: 25px 0px 5px 5px"><b>1. 국가별 배송정책</b> <font class=extext>(한국, 이탈리아를 제외한 나라는 아래의 배송비가 추가됩니다.)</font></div>
	<br>
	<div style="padding: 0px 0px 5px 13px">
		<table class="tb" >
			<col class=cellC><col class=cellL>
			<tr>
				<td>국가별 배송금액</td>
				<td>
					<table id="tbShippingFee" width="100%">
						<col><col align="right">
						<tr>
							<td>
								한국, 이탈리아를 제외한 모든 국가의 배송비를 <input type="text" name="shippingFee" value="${shippingFee}" class="rline"> 유로로 지정 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	-->

	<div class="sub-cont-wrap">
		<div class="sub-title">
			1. 국가별 배송정책
			<span class="extext">(한국, 이탈리아를 제외한 나라는 아래의 배송비가 추가됩니다.)</span>
		</div>
		<div class="div-tbl inp-tbl">
			<div class="th w-120">
				국가별 배송금액
			</div>
			<div style="padding: 10px;">
				한국, 이탈리아를 제외한 모든 국가의 배송비를
				<input type="text" name="shippingFee" value="${shippingFee}" style="text-align: right;"> 유로로 지정
			</div>
		</div>
	</div>


	<!-- 여기서부터 편집할것임 -->
	<%--
	<div class="title title_top"><font color=black>택배사/배송추적 설정</font><span>사용하는 택배사를 선택하고 배송추적 주소를 넣으세요</span>
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=basic&no=3',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
	</div>
	<div class="rndline2"></div>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<table cellpadding="0" cellspacing="10" border="0">
					<tr>
						<td>&nbsp;&nbsp;<img src="/resources/shop/admin/img/arrow_downorg.gif" align=absmiddle> <font class=man>택배사 전체리스트 </font><font class=small1>(더블클릭하세요)</font></td>
						<td></td>
						<td>&nbsp;&nbsp;<img src="/resources/shop/admin/img/arrow_downorg.gif" align=absmiddle> <font class=man>이용 택배사</font> <font class=small1>(삭제시 더블클릭)</font></td>
					</tr>
					<tr>
						<td>
							<select class="deliveryTmp" name="deliveryTmp" multiple style="width:200px;height:156px" ondblclick="move('right')">
							<c:if test="${ rtListDelivery !=null and not(rtListDelivery eq '')}">
								<c:forEach begin="0"  end = "${fn:length(rtListDelivery)}" var  ="k" step = "1">
									<option value="${rtListDelivery[k].deliveryno}">${rtListDelivery[k].deliverycomp }</option>
								</c:forEach>
							</c:if>

							</select>
						</td>
						<td style="font-size:36px">
							<a href="javascript:move('right')"><font class="color_r">▶</font></a><p>
							<a href="javascript:move('left')"><font class="color_l">◀</font></a>
						</td>
						<td>
							<select class="delivery" name="delivery" multiple style="width:200px;height:156px" ondblclick="move('left')">
							<c:if test="${fn:length(rtListDelivery) > 0}">
								<c:forEach begin="0" end="${fn:length(rtListDelivery)}" var = "j">
									<c:if test="${rtListDelivery[j].useyn eq 'y' }">
										<option value="${rtListDelivery[j].deliveryno }">${rtListDelivery[j].deliverycomp }</option>
									</c:if>
								</c:forEach>
							</c:if>

							</select>
						</td>
					</tr>
					
					<!-- 2017-08-23 : 택배사 신규등록 및 등록된 택배사 삭제를 위한 등록 삭제 버튼 추가 -->
					<tr>
						<td>
							<div>
								<a href="javascript:registerDelivery()"><img alt="등록" src="/resources/shop/admin/img/btn_daily_regist.gif"></a>
								<a href="javascript:removeCourier()"><img alt="삭제" src="/resources/shop/admin/img/btn_daily_del.gif"></a>
							</div>
						</td>
					</tr>
				</table>
	
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td style="padding-left:3px;" class="extext">
							<!-- 2017-08-23 : 안내문구 추가 -->
							<div style="padding-top:4px">
								*새로운 택배사를 등록하시려면 '등록' 버튼을, 등록된 택배사를 삭제하시려면 '삭제' 버튼을 누르세요.
							</div>
							<div style="padding-top:4px">
								* 배송추적주소를 수정하려면 왼쪽 택배사 전체리스트에서 택배사를 선택하고&nbsp; <a href="javascript:modifyDelivery()"><img src="/resources/shop/admin/img/btn_deliedit.gif" border=0 vspace=2 align=absmiddle></a> 을 누르세요.
							</div>
							<div style="padding-top:4px">
								* 배송추적이란 주문한 고객이 마이페이지에서 직접 배송상태를 확인하는 것입니다.
							</div>
							<div style="padding-top:8px">
								* 맨 처음 선택되어진 배송사가 기본 배송사 입니다.
							</div>
						</td>
					</tr>
				</table>
				<div style="padding-top:10px"></div>
			</td>
		</tr>
	</table>
	<div class="rndline2"></div>
	--%>

	<div class="title">
		택배사/배송추적 설정
		<span>사용하는 택배사를 선택하고 배송추적 주소를 넣으세요</span>
	</div>

	<div class="sub-cont-wrap" style="padding-left: 10px;">
		<div style="display: flex; flex-direction: row; flex-wrap: nowrap; justify-content: flex-start; align-items: center;" class="mg-bottom-15">
			<!-- 택배사 전체 리스트 -->
			<div>
				<div class="mg-bottom-15">
					<img src="/resources/shop/admin/img/arrow_downorg.gif" align=absmiddle>
					<span class="man">택배사 전체리스트 </span>
					<span class=small1>(더블클릭하세요)</span>
				</div>
				<div>
					<select class="deliveryTmp" name="deliveryTmp" multiple style="width:200px;height:156px" ondblclick="move('right')">
						<c:if test="${ rtListDelivery !=null and not(rtListDelivery eq '')}">
							<c:forEach begin="0"  end = "${fn:length(rtListDelivery)}" var  ="k" step = "1">
								<option value="${rtListDelivery[k].deliveryno}">${rtListDelivery[k].deliverycomp }</option>
							</c:forEach>
						</c:if>

					</select>
				</div>

			</div>

			<!-- 화살표 -->
			<div style="font-size:36px; width: 33px;">
				<a href="javascript:move('right')"><span class="color_r">▶</span></a>
				<br>
				<a href="javascript:move('left')"><span class="color_l">◀</span></a>
			</div>

			<!-- 이용 택배사 -->
			<div>
				<div class="mg-bottom-15">
					<img src="/resources/shop/admin/img/arrow_downorg.gif" align=absmiddle>
					<span class="man">이용 택배사</span>
					<span class="small1">(삭제시 더블클릭)</span>
				</div>

				<div>
					<select class="delivery" name="delivery" multiple style="width:200px;height:156px" ondblclick="move('left')">
						<c:if test="${fn:length(rtListDelivery) > 0}">
							<c:forEach begin="0" end="${fn:length(rtListDelivery)}" var = "j">
								<c:if test="${rtListDelivery[j].useyn eq 'y' }">
									<option value="${rtListDelivery[j].deliveryno }">${rtListDelivery[j].deliverycomp }</option>
								</c:if>
							</c:forEach>
						</c:if>

					</select>
				</div>

			</div>

		</div>	<!-- END flex -->
		<!-- 등록 삭제 버튼 -->
		<div class="mg-bottom-15">
			<!--
			<a href="javascript:registerDelivery()"><img alt="등록" src="/resources/shop/admin/img/btn_daily_regist.gif"></a>
			<a href="javascript:removeCourier()"><img alt="삭제" src="/resources/shop/admin/img/btn_daily_del.gif"></a>
			-->
			<a href="javascript:registerDelivery()" class="admin-btn-delivery">등록</a>
			<a href="javascript:removeCourier()" class="admin-btn-delivery">삭제</a>

		</div>

		<!-- 안내문구 -->
		<ul class="list-ul extext">
			<li>
				새로운 택배사를 등록하시려면 &nbsp;<span class="admin-btn-delivery">등록</span>&nbsp;버튼을,
				등록된 택배사를 삭제하시려면 &nbsp;<span class="admin-btn-delivery">삭제</span>&nbsp;버튼을 누르세요.
			</li>
			<li>
				배송추적주소를 수정하려면 왼쪽 택배사 전체리스트에서 택배사를 선택하고&nbsp;
<%--				<a href="javascript:modifyDelivery()"><img src="/resources/shop/admin/img/btn_deliedit.gif" border=0 vspace=2 align=absmiddle></a>--%>
				<a href="javascript:modifyDelivery()" class="admin-btn-blue">택배사 배송추적링크수정</a>&nbsp;
				을 누르세요.</li>
			<li>
				배송추적이란 주문한 고객이 마이페이지에서 직접 배송상태를 확인하는 것입니다.
			</li>
			<li>
				맨 처음 선택되어진 배송사가 기본 배송사 입니다.
			</li>
		</ul>
	</div>

	<div class="rndline2"></div>

	<div class=button>
<%--		<input type=image src="/resources/shop/admin/img/btn_register.gif">--%>
		<!-- <a href="javascript:history.back()"><img src="../img/btn_cancel.gif"></a> -->
		<input type="submit" class="admin-btn" value="등록" />
	</div>

</form>
<script>
initLayer();
</script>
<script src="/resources/js/jquery/jquery-1.10.2.min.js	"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
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

<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>


