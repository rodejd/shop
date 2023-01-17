<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script language=javascript src="/resources/shop/admin/common.js"></script>
<!-- Jquery Setting-->
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
<!-- //Jquery Setting-->
<script language="javascript">

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

<body marginwidth="0" marginheight="0" class="scroll"><div id="dynamic"></div>
<!-- 	<iframe name="ifrmHidden" src="../../blank.jsp" style="display:none"></iframe> -->
	<div id="jsmotion"></div>


<script language="javascript">
var arr=new Array('c','v','o','h');
function chkSettleKind(){
	var f = document.forms[0];

	var ret = false; var sk = false;
	for(var i=0;i < arr.length;i++)
	{
		sk = document.getElementsByName('set_use')[i].checked;
		if(sk == true)ret=true;
	}
	var robj =  new Array('pg_id','pg_key','pg_quota');

	for(var i=0;i < robj.length;i++){
		var obj = document.getElementsByName(robj[i])[0];
		if(ret){
			obj.style.background = "#ffffff";
			obj.readOnly = false;
		}else{
			obj.style.background = "#e3e3e3";
			obj.readOnly = true;
			obj.value = '';
		}
	}
}
function chkFormThis(f){

	var ret = false;
	var sk = false;
	var p_id = document.getElementsByName('pg_id')[0];
	var p_key = document.getElementsByName('pg_key')[0];
	var p_quota = document.getElementsByName('pg_quota')[0];

	for(var i=0;i < arr.length;i++)
	{
		sk = document.getElementsByName('set_use')[i].checked;
		if(sk == true)ret=true;
	}

	if(!p_id.value && ret){
		p_id.focus();
		alert('KCP Code는 필수항목입니다.');
		return false;
	}
	if(!p_key.value && ret){
		p_key.focus();
		alert('KCP Key는 필수항목입니다.');
		return false;
	}
	if(!p_quota.value && ret){
		p_quota.focus();
		alert('할부기간은 필수항목입니다.');
		return false;
	}

	return chkForm(f);
}
var IntervarId;

function resizeFrame()
{

    var oBody = document.body;
    var oFrame = parent.document.getElementById("pgifrm");
    var i_height = oBody.scrollHeight + (oFrame.offsetHeight-oFrame.clientHeight);
    oFrame.style.height = i_height;

    if ( IntervarId ) clearInterval( IntervarId );
}

window.onload = function(){
	resizeFrame()
}
</script>
	<div class="title title_top">
	KCP PG 설정<span>신용카드 결제 및 기타결제방식은 반드시 전자결제서비스 업체와 계약을 맺으시기 바랍니다</span> 	
	</div>

	<form method="post" action="indb" enctype="multipart/form-data" onsubmit="return chkFormThis(this)">
		<input type="hidden" name="mode" value="kcp">
		<input type="hidden" name="settlePg" value="kcp">

		<div id="MSG01">
			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tbody>
					<tr><td>KCP에서 제공하는 신용카드,계좌이체,가상계좌,핸드폰의 결제수단을 방문자(소비자)에게 제공하기 위해서</td></tr>
					<tr><td>KCP에서 <b>메일로 받으신 KCP Code와 KCP Key를 입력</b>하신후 본 페이지 하단의 저장버튼을 클릭해 주세요.</td></tr>
					<tr><td>아직 KCP와 계약을 하지 않으셨다면</td></tr> 
					<tr><td style="padding-left:10">①<u>온라인신청 하신 후</u></td></tr>
					<tr><td style="padding-left:10">②<u>계약서류를 우편으로 KCP에 보내주세요.</u></td></tr>
				</tbody>
			</table>
		</div>
		<script>cssRound('MSG01')</script>
		<div style="padding-top:15px"></div>
		<table border="1" bordercolor="#e1e1e1" style="border-collapse:collapse" width="100%">
			<colgroup>
				<col class="cellC"><col class="cellL">
			</colgroup>
			<tbody>
				<tr>
					<td>PG사</td>
					<td><b>KCP (ESCROW AX-HUB V6) ${pgVO.spot}</b></td>
				</tr>
				<tr>
					<td>결제수단 설정</td>
					<td class="noline">
						<input type="checkbox" name="set_use" value="c" ${stringUtil:checkedOption2(pgVO.set_use,"c")}  onclick="chkSettleKind()"> 신용카드
						<input type="checkbox" name="set_use" value="o" ${stringUtil:checkedOption2(pgVO.set_use,"o")}  onclick="chkSettleKind()"> 계좌이체
						<input type="checkbox" name="set_use" value="v" ${stringUtil:checkedOption2(pgVO.set_use,"v")}  onclick="chkSettleKind()"> 가상계좌
						<input type="checkbox" name="set_use" value="h" ${stringUtil:checkedOption2(pgVO.set_use,"h")}  onclick="chkSettleKind()"> 휴대폰	&nbsp;&nbsp;&nbsp;
						<font class="extext"><b>(반드시 KCP와 계약한 결제수단만 체크하세요)</b></font>
					</td>
				</tr>
				<tr>
					<td class="ver8"><b>KCP Code</b></td>
					<td>
						<input type="text" name="pg_id" class="lline" value="${pgVO.pg_id}" >
					</td>
				</tr>
				<tr>
					<td class="ver8"><b>KCP Key</b></td>
					<td>
						<input type="text" name="pg_key" class="lline" value="${pgVO.pg_key}" >
					</td>
				</tr>
				<tr>
					<td>할부기간</td>
					<td>
						<input type="text" name="pg_quota" value="${pgVO.pg_quota}" class="lline" >
						<div class="extext" style="padding-top:4">결제자가 할부 결제시 선택한 할부개월 수 입니다. 00 부터 12 의 값을 가집니다.(예: 3개월의 경우 : 03 , 일시불의 경우 : 00) </div>
						<div class="extext" style="padding-top:3">최대 할부개월수만을 입력해 주시기 바랍니다 (예: 6개월할부까지만을 적용시 06 만을 입력 =&gt; 일시불~6개월할부만이 노출) </div>
					</td>
				</tr>
				<tr>
					<td>무이자 여부</td>
					<td class="noline">
						<input type="radio" name="pg_zerofee" value="no" ${pgVO.pg_zerofee eq "no"?"checked":""}> 일반결제
						<input type="radio" name="pg_zerofee" value="yes" ${pgVO.pg_zerofee  eq "yes"?"checked":""}> 무이자결제 <font class="extext"><b>(무이자결제는 반드시 PG사와 계약체결 후에 사용해야 합니다!)</b></font>
					</td>
				</tr>
				<tr>
					<td>무이자 기간</td>
					<td>
						<input type="text" name="pg_zerofee_period" value="${pgVO.pg_zerofee_period}" class="lline" style="width:500px">
						<a href="javascript:popupLayer('${ctx}/shop/admin/basic/pg/popUp?target=kcp',500,470)" style="color:#616161;" class="ver8"><img src="/resources/shop/admin/img/btn_carddate.gif" align="absmiddle"></a>
						<div class="extext" style="padding-top:4px">옆에 있는 '무이자기간코드생성' 버튼을 눌러 코드를 생성한후 복사하여 사용하세요</div>
					</td>
				</tr>
			</tbody>
		</table>

		<div style="padding-top:15px"></div>

		<div id="MSG02">
			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tbody>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">PG사와 계약을 맺은 이후에는 메일로 받으신 실제 ID, Key를 넣으시면 됩니다.</td></tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">PG사의 결제정보 설정후 고객님께서 카드결제 테스트를 꼭 해보시기 바랍니다.</td></tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">간혹 PG사를 통해 카드승인된 값을 받지못하여 주문관리페이지에서 입금확인으로 자동변경되지 않을수 있습니다.</td></tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">반드시 주문관리페이지의 주문상태와 PG사에서 제공하는 관리자화면내의 카드승인내역도 동시에 확인해 주십시요.</td></tr>
				</tbody>
			</table>
		</div>
		<script>cssRound('MSG02')</script>

	<div style="display:none;">
		<div class="title">에스크로 설정 <span>현금성 결제시 의무적으로 에스크로결제를 허용해야 합니다. 에스크로란?</span> 		
		</div>
		<input type="hidden" name="escrow_comp" value="PG">	<!-- 에스크로 기관설정 -->
		<div class="extext">아직 KCP 에스크로를 신청하지 않으셨다면 <a href="http://admin.kcp.co.kr" target="_blank" class="extext"><b>KCP 상점관리자(http://admin.kcp.co.kr)에서 신청</b></a>해 주세요.</div>
		<table border="1" bordercolor="#e1e1e1" style="border-collapse:collapse" width="100%">
			<colgroup>
				<col class="cellC"><col class="cellL">
			</colgroup>
			<tbody>
				<tr>
					<td>사용여부</td>
					<td class="noline">
						<input type="radio" name="escrow_use" value="Y" ${pgVO.escrow_use  eq "Y"?"checked":""}> 사용
						<input type="radio" name="escrow_use" value="N" ${pgVO.escrow_use  eq "N"?"checked":""}> 사용안함 &nbsp;&nbsp;&nbsp;
						<font class="extext"><b>(KCP 에스크로를 신청하셨다면 사용으로 체크하세요)</b></font>
					</td>
				</tr>
				<tr>
					<td>결제 수단</td>
					<td class="noline">
						<input type="checkbox" name="escrow" value="c" ${stringUtil:checkedOption2(pgVO.escrow,"c")}> 신용카드
						<input type="checkbox" name="escrow" value="o" ${stringUtil:checkedOption2(pgVO.escrow,"o")}> 계좌이체
						<input type="checkbox" name="escrow" value="v" ${stringUtil:checkedOption2(pgVO.escrow,"v")}> 가상계좌
					</td>
				</tr>
				<tr>
					<td>사용 금액</td>
					<td class="noline">
						<input type="radio" name="escrow_min" value="100000"  ${pgVO.escrow_min  eq "100000"?"checked":""}> 10만원 이상 결제금액에 대해 적용
						<input type="radio" name="escrow_min" value="0"  ${pgVO.escrow_min  eq "0"?"checked":""}> 모든 금액에 대해 적용
						<div class="extext" style="padding-top:4px">PG사마다 에스크로 결제가 모든 금액에 적용이 안될수도 있으므로, 반드시 계약맺은 PG사의 에스크로 계약내용을 꼭 확인하세요.</div>
					</td>
				</tr>
				<tr>
					<td>구매 안전 표시<div style="padding-top:3">					
					</div></td>
					<td class="noline">
						<input type="radio" name="cfg_display_egg" value="0"  ${pgVO.cfg_display_egg  eq "0"?"checked":""}> 메인하단과 결제수단 선택페이지에만 표시 
						<input type="radio" name="cfg_display_egg" value="1"  ${pgVO.cfg_display_egg  eq "1"?"checked":""}> 전체페이지에 표시 
						<input type="radio" name="cfg_display_egg" value="2"  ${pgVO.cfg_display_egg  eq "2"?"checked":""}> 표시하지 않음
					</td>
				</tr>
			</tbody>
		</table>

		<div style="padding-top:10"></div>

		<table border="1" bordercolor="#e1e1e1" style="border-collapse:collapse" width="100%">
			<tbody>
				<tr>
					<td>
						<div style="padding-top:15"></div>

						<table cellpadding="1" cellspacing="0" border="0" class="small_tip">
							<tbody>
								<tr>
									<td>
										<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><font class="extext"><b>구매안전서비스 가입 표기 의무화 안내 (2007년 9월 1일 시행)</b></font>
									</td>
								</tr>
								<tr>
									<td style="padding-top:4px">&nbsp;&nbsp;<font class="extext">① 표시·광고 또는 고지의 위치를 사이버몰 초기화면과 소비자의 결제수단 선택화면 두 곳으로 정함.</font></td>
								</tr>
								<tr>
									<td style="padding-left:16"><font class="extext">- 사이버몰 초기화면 상법 제10조제1항의 사업자의 신원 등 표기사항 게재부분의 바로 좌측 또는 우측에 구매안전서비스 관련 사항을 표시하도록 함.</font></td>
								</tr>
								<tr>
									<td style="padding-left:16"><font class="extext">- 소비자가 정확한 이해를 바탕으로 구매안전서비스 이용을 선택할 수 있도록, 결제수단 선택부분의 바로 위에 구매안전서비스 관련사항을 알기 쉽게 고지하여야  함.</font></td>
								</tr>
								<tr>
									<td style="padding-top:4px">&nbsp;&nbsp;<font class="extext">② 표시·광고 또는 고지 사항으로 다음의 세 가지를 규정함.</font></td>
								</tr>
								<tr>
									<td style="padding-left:16"><font class="extext">- 현금 등으로 10만원 이상 결제시 소비자가 구매안전서비스의 이용을 선택할 수 있다는 사항</font></td>
								</tr>
								<tr>
									<td style="padding-left:16"><font class="extext">- 통신판매업자 자신이 가입한 구매안전서비스의 제공사업자명 또는 상호</font></td>
								</tr>
								<tr>
									<td style="padding-left:16"><font class="extext">- 소비자가 구매안전서비스 가입사실의 진위를 확인 또는 조회할 수 있다는 사항</font></td>
								</tr>
								<tr><td height="10"></td></tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>

		<div class="title">현금영수증</div>
		<table border="1" bordercolor="#e1e1e1" style="border-collapse:collapse" width="100%">
			<colgroup>
				<col class="cellC"><col class="cellL">
			</colgroup>
			<tbody>
				<tr>
					<td>현금영수증</td>
					<td class="noline">
						<input type="radio" name="pg_receipt" value="N" ${pgVO.pg_receipt  eq "0"?"checked":""}> 사용안함
						<input type="radio" name="pg_receipt" value="Y" ${pgVO.pg_receipt  eq "0"?"checked":""}> 사용
						<br><font class="extext" style="padding-left:5px">KCP와 전자지불서비스를 계약하신 상점에서는 현금영수증 발급을 위한 별도 계약이 필요 없으십니다.</font>
						<br><font class="extext" style="padding-left:5px">단, KCP를 사용하지 않으신 상점에서 현금영수증 발급하기 위해서는 별도 계약이 필요하십니다.</font>
					</td>
				</tr>
			</tbody>
		</table><p>

		</p>
		<div id="MSG03">
			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tbody>
					<tr>
						<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">구매안전서비스(에스크로 또는 전자보증)는 전자상거래소비자보호법 및 시행령 개정에 따라 2006년 4월1일부터 10만원 이상 현금성 결제시 의무 시행됩니다.</td>
					</tr>
					<tr>
						<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">에스크로 사용범위 및 사용금액에 대한것은 신청한 PG사나 은행에 따라 다를 수 있으므로 협의를 하셔야 합니다.</td>
					</tr>
					<tr>
						<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">소비자는 2008. 7. 1일부터 현금영수증 발급대상금액이 5천원이상에서 1원이상으로 변경되어 5천원 미만의 현금거래도 현금영수증을 요청하여 발급 받을 수 있습니다.</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
		
		<script>cssRound('MSG03','#F7F7F7')</script>

		<div class="button">
			<input type="image" src="/resources/shop/admin/img/btn_save.gif">
			<a href="javascript:history.back()"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
		</div>
	</form>
	<script>chkSettleKind();</script>
</body>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
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

<%-- ================================================================================
* 업무 시작 catch 부분
================================================================================ --%>
