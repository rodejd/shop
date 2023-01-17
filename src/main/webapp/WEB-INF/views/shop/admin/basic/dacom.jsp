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

<body marginwidth="0" marginheight="0" class="scroll">
	<div id="dynamic"></div>
<!-- 	<iframe name="ifrmHidden" src="../../blank.jsp" style="display:none"></iframe> -->
	<div id="jsmotion"></div>

<script language="javascript">

var arr=new Array('c','v','o','h');

function chkSettleKind(){
	var f = document.forms[0];

	var ret = false;
	for(var i=0;i < arr.length;i++)
	{
		var sk = document.getElementsByName('set_use')[i].checked;
		if(sk == true)ret=true;
	}
	var robj =  new Array('pg_id','pg_mertkey','pg_quota');

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
	var p_key =  document.getElementsByName('pg_mertkey')[0];
	var p_quota = document.getElementsByName('pg_quota')[0];
	for(var i=0;i < arr.length;i++)
	{
		sk = document.getElementsByName('set_use')[i].checked;
		if(sk == true)ret=true;
	}

	if(!p_id.value && ret){
		p_id.focus();
		alert('Dacom ID는 필수항목입니다.');
		return false;
	}
	if(!p_key.value && ret){
		p_key.focus();
		alert('Dacom mertkey는 필수항목입니다.');
		return false;
	}
	if(!p_quota.value && ret){
		p_quota.focus();
		alert('일반할부기간은 필수항목입니다.');
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
	LG데이콤PG 설정<span>신용카드 결제 및 기타결제방식은 반드시 전자결제서비스 업체와 계약을 맺으시기 바랍니다</span> 
	</div>

	<form method="post" action="indb" enctype="multipart/form-data" onsubmit="return chkFormThis(this)">
		<input type="hidden" name="mode" value="dacom">
		<input type="hidden" name="settlePg" value="dacom">

		<div id="MSG01">
			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tbody>
					<tr>
						<td>LG데이콤에서 제공하는 신용카드,계좌이체,가상계좌,핸드폰의 결제수단을 방문자(소비자)에게 제공하기 위해서</td>
					</tr>
					<tr><td>LG데이콤에서 <b>메일로 받으신 Dacom ID와 mertkey를 입력</b>하신후 본 페이지 하단의 저장버튼을 클릭해 주세요.</td></tr>
					<tr><td>아직 LG데이콤과 계약을 하지 않으셨다면</td></tr>
					<tr><td style="padding-left:10">①<u>온라인신청 하신후</u></td></tr>
					<tr><td style="padding-left:10">②<u>계약서류를 우편으로 KCP에 보내주세요.</u></td></tr>
				</tbody>
			</table>
		</div>
		<script>cssRound('MSG01')</script>
		<div style="padding-top:15"></div>
		<table border="1" bordercolor="#e1e1e1" style="border-collapse:collapse" width="100%">
			<colgroup>
				<col class="cellC"><col class="cellL">
			</colgroup>
			<tbody>
				<tr>
					<td>PG사</td>
					<td><b>LG데이콤 (Noteurl_Link_PHP) ${pgVO.spot}</b></td>
				</tr>
				<tr>
					<td>결제수단 설정</td>
					<td class="noline">
						<input type="checkbox" name="set_use" value="c"  ${stringUtil:checkedOption2(pgVO.set_use,"c")} onclick="chkSettleKind()"> 신용카드
						<input type="checkbox" name="set_use" value="o"  ${stringUtil:checkedOption2(pgVO.set_use,"o")} onclick="chkSettleKind()"> 계좌이체
						<input type="checkbox" name="set_use" value="v"  ${stringUtil:checkedOption2(pgVO.set_use,"v")} onclick="chkSettleKind()"> 가상계좌
						<input type="checkbox" name="set_use" value="h"  ${stringUtil:checkedOption2(pgVO.set_use,"h")} onclick="chkSettleKind()"> 휴대폰	&nbsp;&nbsp;&nbsp;<font class="extext"><b>(반드시 LG데이콤PG사와 계약한 결제수단만 체크하세요)</b></font>
					</td>
				</tr>
				<tr>
					<td class="ver8"><b>Dacom ID</b></td>
					<td>
						<input type="text" name="pg_id" class="lline" value="${pgVO.pg_id}" >
					</td>
				</tr>
				<tr>
					<td class="ver8"><b>Dacom mertkey</b></td>
					<td>
						<input type="text" name="pg_mertkey" class="lline" value="${pgVO.pg_mertkey}" >
					</td>
				</tr>
				<tr>
					<td>일반할부기간</td>
					<td>
						<input type="text" name="pg_quota" value="${pgVO.pg_quota}" class="lline" >
						<span class="extext">ex) 0,2,3,4,5,6,7,8,9,10,11,12</span>
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
						<a href="javascript:popupLayer('${ctx}/shop/admin/basic/pg/popUp?target=dacom',500,470)" style="color:#616161;" class="ver8"><img src="/resources/shop/admin/img/btn_carddate.gif" align="absmiddle"></a>
						<div class="extext" style="padding-top:4px">오른쪽에 있는 '무이자기간코드생성' 버튼을 눌러 코드를 생성한후 복사하여 사용하세요</div>
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

			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tbody>
					<tr><td height="20"></td></tr>
					<tr><td><font class="def1" color="white"><b>※ LG데이콤 PG사 계약후 설정시 유의사항 (필독!)</b></font></td></tr>
					<tr><td height="8"></td></tr>
					<tr><td><font class="def1" color="white">- 이곳에서 LG데이콤 PG 설정시 유의사항 -</font></td></tr>
					<tr><td>① 계약후 메일로 받은 'LG데이콤 ID' 와 'Dacom mertkey'를 상단입력란에 정확하게 입력하세요.</td></tr>
					<tr><td>② LG데이콤PG사와 계약한 후 반드시 계약정보와 일치하도록 위 상단의 '결제수단설정'을 해주셔야 합니다.</td></tr>
					<tr><td>(즉, 신용카드, 계좌이체만 계약체결했다면 반드시 두가지만 체크해야 합니다. 만일 가상계좌까지 체크하면 결제에러가 발생됩니다)</td></tr>
					<tr><td height="8"></td></tr>
					<tr><td><font class="def1" color="white">- LG데이콤PG사에서 제공하는 관리자모드 설정시 유의사항 -</font></td></tr>
					<tr><td>① LG데이콤 관리자모드에 가서 '승인결과전송여부'와 '서버OS타입'을 아래와 같이 수정하세요.</td></tr>
					<tr><td>'승인결과전송여부' 설정은  '전송(웹전송)' 으로 설정하시고,	'서버OS타입'은  'LINUX계열'로 설정을 수정해 주시기 바랍니다.</td></tr>
					<tr><td>② 에스크로거래 사용시에는 반드시 '에스크로거래처리결과수신url' 란에 url을 입력해야 합니다.</td></tr>
					<tr><td>③ 위 사항을 모두 수정하고 1시간 후에 쇼핑몰에서 신용카드결제 테스트를 해보셔야 수정된 결과가 반영되어 정상적으로 결제가 이루어집니다.</td></tr>
				</tbody>
			</table>
		</div>
		<script>cssRound('MSG02')</script>
		
		<div style="display:none;">
		<div class="title">에스크로 설정 <span>현금성 결제시 의무적으로 에스크로결제를 허용해야 합니다. 에스크로란?</span>		
		<input type="hidden" name="escrow_comp" value="PG">	<!-- 에스크로 기관설정 -->

		<div class="extext">아직 LG데이콤 에스크로를 신청하지 않으셨다면 <a href="http://pgweb.dacom.net" target="_blank" class="extext" style="font-weight:bold">LG데이콤 상점관리자(http://pgweb.dacom.net)에서 신청</a>해 주세요.</div>

		<table border="1" bordercolor="#e1e1e1" style="border-collapse:collapse" width="100%">
			<colgroup>
				<col class="cellC"><col class="cellL">
			</colgroup>
			<tbody>
				<tr>
					<td>사용여부</td>
					<td class="noline">
						<input type="radio" name="escrow_use" value="Y"   ${pgVO.escrow_use  eq "Y"?"checked":""}> 사용
						<input type="radio" name="escrow_use" value="N"   ${pgVO.escrow_use  eq "N"?"checked":""}> 사용안함&nbsp;&nbsp;&nbsp;<font class="extext"><b>(LG데이콤 에스크로를 신청하셨다면 사용으로 체크하세요)</b></font>
					</td>
				</tr>
				<tr>
					<td>결제 수단</td>
					<td class="noline">
						<input type="checkbox" name="escrow" value="c"  ${stringUtil:checkedOption2(pgVO.escrow,"c")}> 신용카드
						<input type="checkbox" name="escrow" value="o"  ${stringUtil:checkedOption2(pgVO.escrow,"o")}> 계좌이체
						<input type="checkbox" name="escrow" value="v"  ${stringUtil:checkedOption2(pgVO.escrow,"v")}> 가상계좌
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
<!--
<tr>
	<td>고객 수수료 부담</td>
	<td>
	<input type=text name=escrow[fee] value="0" size=5 class=right> %
	</td>
</tr>
-->
				<tr>
					<td>구매 안전 표시<div style="padding-top:3"></div></td>
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
						<table cellpadding="15" cellspacing="0" border="0" bgcolor="white" width="100%">
							<tbody>
								<tr>
									<td>
										<div style="padding:0 0 5 0">* 구매안전서비스 표기 적용방법 (에스크로 사용시 위에서 구매안전표시를 체크하고, 아래 표기방법에 따라 반영하세요)</div>
										<table width="100%" height="100" class="tb" style="border:1px solid #cccccc;" bgcolor="white">
											<tbody>
												<tr>
													<td width="30%" style="border:1px solid #cccccc;padding-left:20">① [메인페이지 하단] 표기방법</td>
													<td width="70%" style="border:1px solid #cccccc;padding-left:40"><font class="extext"><a href="../design/codi.jsp?design_file=outline/footer/standard.htm" target="_blank"><font class="extext"><b>[디자인관리 &gt; 전체레이아웃 디자인 &gt; 하단디자인 &gt; html소스 직접수정]</b></font></a> 을 눌러<br> 치환코드 <font class="ver8" color="000000"><b>{=displayEggBanner()}</b></font> 를 삽입하세요. <a href="../design/codi.jsp?design_file=outline/footer/standard.htm" target="_blank"><font class="extext_l">[바로가기]</font></a></font></td>
												</tr>
												<tr>
													<td width="30%" style="border:1px solid #cccccc;padding-left:20">② [결제수단 선택페이지] 표기방법</td>
													<td width="70%" style="border:1px solid #cccccc;padding-left:40">
														<a href="../design/codi.jsp?design_file=order/order.htm" target="_blank"><font class="extext"><font class="extext_l">[디자인관리 &gt; 기타페이지 디자인 &gt; 주문하기 &gt; order.htm]</font></font></a><font class="extext"> 을 눌러<br> 치환코드 <font class="ver8" color="000000"><b>{=displayEggBanner(1)}</b></font> 를 삽입하세요.</font>
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</table>

						<div style="padding-top:15"></div>

						<table cellpadding="1" cellspacing="0" border="0" class="small_tip">
							<tbody>
								<tr>
									<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><font class="extext"><b>구매안전서비스 가입 표기 의무화 안내 (2007년 9월 1일 시행)</b></font></td>
								</tr>
								<tr><td style="padding-top:4px">&nbsp;&nbsp;<font class="extext">① 표시·광고 또는 고지의 위치를 사이버몰 초기화면과 소비자의 결제수단 선택화면 두 곳으로 정함.</font></td></tr>
								<tr><td style="padding-left:16"><font class="extext">- 사이버몰 초기화면 상법 제10조제1항의 사업자의 신원 등 표기사항 게재부분의 바로 좌측 또는 우측에 구매안전서비스 관련 사항을 표시하도록 함.</font></td></tr>
								<tr><td style="padding-left:16"><font class="extext">- 소비자가 정확한 이해를 바탕으로 구매안전서비스 이용을 선택할 수 있도록, 결제수단 선택부분의 바로 위에 구매안전서비스 관련사항을 알기 쉽게 고지하여야  함.</font></td></tr>
								<tr><td style="padding-top:4px">&nbsp;&nbsp;<font class="extext">② 표시·광고 또는 고지 사항으로 다음의 세 가지를 규정함.</font></td></tr>
								<tr><td style="padding-left:16"><font class="extext">- 현금 등으로 10만원 이상 결제시 소비자가 구매안전서비스의 이용을 선택할 수 있다는 사항</font></td></tr>
								<tr><td style="padding-left:16"><font class="extext">- 통신판매업자 자신이 가입한 구매안전서비스의 제공사업자명 또는 상호</font></td></tr>
								<tr><td style="padding-left:16"><font class="extext">- 소비자가 구매안전서비스 가입사실의 진위를 확인 또는 조회할 수 있다는 사항</font></td></tr>
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
						<br><font class="extext" style="padding-left:5px">LG데이콤 전자지불서비스를 계약하신 상점에서는 현금영수증 발급을 위한 별도 계약이 필요 없으십니다.</font>
						<br><font class="extext" style="padding-left:5px">단, LG데이콤을 사용하지 않으신 상점에서 현금영수증 발급하기 위해서는 별도 계약이 필요하십니다.</font>
					</td>
				</tr>
			</tbody>
		</table><p>

		</p>
		<div id="MSG03">
			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tbody>
					<tr>
						<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">에스크로</td>
					</tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">구매안전서비스(에스크로 또는 전자보증)는 전자상거래소비자보호법 및 시행령 개정에 따라 2006년 4월1일부터 10만원 이상 현금성 결제시 의무 시행됩니다.</td></tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">에스크로 사용범위 및 사용금액에 대한것은 신청한 PG사나 은행에 따라 다를 수 있으므로 협의를 하셔야 합니다.</td></tr>
					<tr><td height="8"></td></tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">현금영수증</td>
					</tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">소비자는 2008. 7. 1일부터 현금영수증 발급대상금액이 5천원이상에서 1원이상으로 변경되어
						5천원 미만의 현금거래도 현금영수증을 요청하여 발급 받을 수 있습니다.</td>
					</tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">현금영수증 사용 체크시 무통장, 계좌이체, 가상계좌 결제에 대해서 소비자가 신청한 현금영수증이 발급 됩니다</td></tr>
				</tbody>
			</table>
		</div>
		</div>
		
		
		
		<script>cssRound('MSG03')</script>

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
