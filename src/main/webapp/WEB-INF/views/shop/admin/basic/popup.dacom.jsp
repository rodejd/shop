<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/prototype.js"></script>
<script language="javascript">
	/*
	 * php script
	 */
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header_popup.jsp" %>

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

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<body class="scroll">
	<div id="dynamic"></div>
<!-- 	<iframe name="ifrmHidden" src="../../blank.jsp" style="display:none"></iframe> -->
	<div id="jsmotion"></div>

	<div class="title title_top">데이콤 무이자 기간 생성</div>

	<table cellpadding="1" cellspacing="0" border="0" class="small_tip" bgcolor="F7F7F7" width="100%">
		<tbody>
			<tr>
				<td height="7"></td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<font class="small1" color="0074BA"><b>[무이자할부 안내]</b></font>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">
					<font class="small1" color="666666">무이자할부는 반드시 (주)데이콤과 별도로 협의 또는 계약하셔야 합니다.</font>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">
					<font class="small1" color="666666">무이자 할부기간은 2 ~ 12개월까지 가능합니다.</font>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">
					<font class="small1" color="666666"><font color="red">붉은색 카드사</font>는 무이자 할부 불가 카드입니다.</font>
				</td>
			</tr>
			<tr>
				<td height="6"></td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<font class="small1" color="0074BA"><b>[무이자 기간코드 생성방법]</b></font>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<font class="small1" color="666666">① 삼성카드 6개월 무이자라면 먼저 카드사에서 삼성카드사를 선택하세요.</font>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<font class="small1" color="666666">② 아래 기간선택에서 6을 선택하세요.</font>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<font class="small1" color="666666">③ 무이자기간코드생성 버튼을 누르면 아래에 코드가 생성됩니다.</font>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10">
					<font class="small1" color="666666">④ 다른 카드사를 추가하려면 체크버튼을 해제하고 위와같은 방식으로 다시 생성합니다.</font>
				</td>
			</tr>
			<tr>
				<td height="7"></td>
			</tr>
		</tbody>
	</table>

	<div style="padding-top:7"></div>

	<form name="pfm" style="margin:0px;">
		<table class="tb" width="100%" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<colgroup>
				<col class="cellC" width="20%"><col class="cellL" width="80%">
			</colgroup>
			<tbody>
				<tr>
					<td><font class="small" color="666666"><b>카드사 선택</b></font></td>
					<td class="noline"><font class="small" color="444444">
						<input type="checkbox" name="card_comp" value="11">KB(11) <nobr>
						<input type="checkbox" name="card_comp" value="13"><font color="red">농협(구 축협VISA)(13)</font> </nobr><nobr>
						<input type="checkbox" name="card_comp" value="33">우리(구 평화VISA)(33) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="17"><font color="red">씨티(17)</font> </nobr><nobr>
						<input type="checkbox" name="card_comp" value="21">외환(21) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="29">산은캐피탈(29) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="31">비씨(31) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="32">하나(32) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="41">엘지(41) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="51">삼성(51) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="61">현대(61) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="71">롯데(71) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="81">신한(81) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="82">제주(82) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="83">조흥(구 강원VISA)(83) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="84">수협(84) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="85">전북(85) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="86">광주(86) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="87">한미(87) </nobr><nobr>
						<input type="checkbox" name="card_comp" value="4J"><font color="red">해외JCB(4J)</font> </nobr><nobr>
						<input type="checkbox" name="card_comp" value="4M"><font color="red">해외MASTER(4M)</font> </nobr><nobr>
						<input type="checkbox" name="card_comp" value="4V"><font color="red">해외VISA(4V)</font> </nobr><nobr>
						<input type="checkbox" name="card_comp" value="6D"><font color="red">해외DINERS(6D)</font>
						<input type="checkbox" name="card_comp" value="7A"><font color="red">해외AMEX(7A)</font>
						</nobr></font>
					</td>
				</tr>
				<tr>
					<td><font class="small" color="666666"><b>기간선택<br>(할부개월수)</b></font></td>
					<td class="noline"><font class="ver7" color="444444">
						<input type="checkbox" name="mon" value="2">2 <nobr>
						<input type="checkbox" name="mon" value="3">3 </nobr><nobr>
						<input type="checkbox" name="mon" value="4">4 </nobr><nobr>
						<input type="checkbox" name="mon" value="5">5 </nobr><nobr>
						<input type="checkbox" name="mon" value="6">6 </nobr><nobr>
						<input type="checkbox" name="mon" value="7">7 </nobr><nobr>
						<input type="checkbox" name="mon" value="8">8 </nobr><nobr>
						<input type="checkbox" name="mon" value="9">9 </nobr><nobr>
						<input type="checkbox" name="mon" value="10">10 </nobr><nobr>
						<input type="checkbox" name="mon" value="11">11 </nobr><nobr>
						<input type="checkbox" name="mon" value="12">12 </nobr><nobr>
						</nobr></font>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

	<div style="text-align:center; margin-top:10px;"><img src="/resources/shop/admin/img/btn_carddate.gif" align="absmiddle" onclick="month_add()"></div>

	<table cellpadding="1" cellspacing="0" border="0" width="100%">
		<tbody>
			<tr>
				<td height="18"></td>
			</tr>
			<tr>
				<td>
					<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">
					<font class="small" color="444444">위에서 카드사와 기간을 선택하면 아래에 코드가 생성됩니다. 복사한 후 창닫고 사용하세요.</font>
				</td>
			</tr>
			<tr>
				<td>
					<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">
					<font class="small" color="444444">KB카드 3개월, 삼성카드 6개월, 현대카드 12개월일 경우 <font color="red">11-3,51-6,61-12</font> 이렇게 됩니다. </font>
				</td>
			</tr>
			<tr>
				<td>
					<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">
					<font class="small" color="444444">즉, '<font color="red">카드사고유번호-개월수</font>'가 코드명이 됩니다.</font>
				</td>
			</tr>
			<tr>
				<td>
					<div style="background-color: #000000; color: #09FF05; padding: 5px; text-align: center; height: 25;" id="result_code"></div>
				</td>
			</tr>
		</tbody>
	</table>


	<script language="javascript">
var fobj = document.pfm;

function month_add(){
	cnt1 = fobj.card_comp.length;
	cnt2 = fobj.mon.length;

	var tmp1 = new Array();
	var itmp1 = 0;

	for ( i=0; i < cnt1; i++ ){
		if ( fobj.card_comp[i].checked == false ) continue;

		var tmp2 = new Array();
		var itmp2 = 0;
		for ( j=0; j < cnt2; j++ ){
			if ( fobj.mon[j].checked ) tmp2[ itmp2++ ] = fobj.mon[j].value;
		}

		if ( tmp2.length ) tmp1[ itmp1++ ] = fobj.card_comp[i].value + '-' + tmp2.join( ':' );
	}

	var str_mon = document.getElementById('result_code').innerText;

	if ( str_mon == '기간코드를 생성한 후 복사해서 사용하세요.' ) str_mon = '';
	if ( tmp1.length && str_mon != '' ) str_mon += ',';
	if ( tmp1.length ) str_mon += tmp1.join( ',' );

	document.getElementById('result_code').innerText = str_mon;
}
</script>



<script>table_design_load();</script></body>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
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
