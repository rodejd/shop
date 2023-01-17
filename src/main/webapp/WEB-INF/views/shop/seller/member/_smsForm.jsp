<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<script language="JavaScript" type="text/JavaScript">
function insChr(str)
{
	
	if(document.forms[1]){
		var fm = document.forms[1];
	}else{
		var fm = document.forms[0];
	}
	fm.msg.value = fm.msg.value + (str.trim());
	chkLength(fm.msg);
	
}
function chkLength(obj){
	
	str = obj.value;
	document.getElementsByName('vLength')[0].value = chkByte(str);
	if (chkByte(str)>80){
		alert("80byte까지만 입력이 가능합니다");
		obj.value = strCut(str,80);
	}
	
}
</script>

<table cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top">

	<table>
	<tr>
		<td>

		<table width="146" cellpadding="0" cellspacing="0" border="0">
		<tr><td><img src="/resources/shop/admin/img/sms_top.gif" /></td></tr>
		<tr>
 			<td background="/resources/shop/admin/img/sms_bg.gif" align="center" height="81">
 				<textarea id="msg" name="msg" style="font:9pt 굴림체;overflow:hidden;border:0;background-color:transparent;width:98px;height:74px;" onkeydown="chkLength(this);" onkeyup="chkLength(this);" onchange="chkLength(this);" required msgR="메세지를 입력해주세요"></textarea>
 			</td>
		</tr>
		<tr><td height="31" background="/resources/shop/admin/img/sms_bottom.gif" align="center"><font class="ver8" color="262626"><input name="vLength" type="text" style="width:20px;text-align:right;border:0;font-size:8pt;font-style:verdana;" value="0">/80 Bytes</td></tr>
		</table>

		</td>
	</tr>
	<tr>
		<td>

		<table>
				<tr>
			<td><font class="small1" color="262626">보내는사람</td>
			<td><input type="text" id="callbackphone" name="callbackphone" value="${fn:replace( codeUtil:getConfValue("smsRecall"), "-", "") }" size="12"  class="line"  onkeydown="onlynumber();"/></td>
		</tr>
		<%-- <tr>
			<td><font class="small1" color="262626">남은건수</td>
			<td><span id="span_sms" style="font-weight:bold"><font class="ver9" color="0074ba"><b>	<fmt:formatNumber value="${codeUtil:getConfValue('smsPt')}" pattern="#,###,###,###"/></b></font></span><font color="262626">건</font></td>
		</tr>	 --%>																								
		</table>

		<div style="background:#D7D7D7;border:0 solid #C5C5C5;width:146px;height:10px;font-size:0;margin-bottom:10px;">
		<div id="sms_bar" style="width:0;height:10px;font-size:0;background:#ff0000;"></div>
		</div>

		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="image" onclick="javascript:goSend(); return false;" src="/resources/shop/admin/img/btn_smssend.gif" class="null" />
		</td>
	</tr>
	</table>

	</td>
	<td valign="top">
    <div style="padding-top:13px;"></div>

	<div style="padding:0px 7px 3px 7px;">
	<table>
	<tr>
		<td><a href="javascript:openLayer('special');" onfocus="blur();"><img src="/resources/shop/admin/img/btn_smstext.gif" align="absmiddle" /></a></td>
	</tr>
	<tr>
		<td>

		<table id="special" style="position:absolute;border:1px solid #cccccc;background:#f7f7f7;padding:5px;display:none;">
		<tr>
						<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			＃			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			＆			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			＊			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			＠			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			§			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			※			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			☆			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			★			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			○			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			●			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◎			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◇			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◆			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			□			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			■			</td>
			</tr><tr>						<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			△			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▲			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▽			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▼			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			→			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			←			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↑			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↓			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↔			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			〓			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◁			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◀			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▷			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▶			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♤			</td>
			</tr><tr>						<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♠			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♡			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♥			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♧			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♣			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			⊙			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◈			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▣			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◐			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			◑			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▒			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▤			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▥			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▨			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▧			</td>
			</tr><tr>						<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▦			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			▩			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♨			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			☏			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			☎			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			☜			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			☞			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			¶			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			†			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			‡			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↕			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↗			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↙			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↖			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			↘			</td>
			</tr><tr>						<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♭			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♩			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♪			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			♬			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			㉿			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			㈜			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			№			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			㏇			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			™			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			㏂			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			㏘			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			℡			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			?			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			ª			</td>
									<td style="border:1px solid #dddddd;width:20px;height:20px;background:#ffffff" align="center" onClick="insChr(this.innerHTML);" class="hand" onmouseover="this.style.background='#FFC0FF'" onmouseout="this.style.background=''">
			º			</td>
			</tr><tr>					</tr>
		</table>

		</td>
	</tr>
	</table>
	</div>

	&nbsp;&nbsp;&nbsp;<font color="262626"><b><font size="1" face="helvetica">▼</font> 문자메세지예제</b></font> &nbsp;<span class="small"><font class="small" color="444444">메세지를 클릭하면 메세지창에 바로 입력이 됩니다</font></span>&nbsp;&nbsp;

	<div style="height:5;font-size:0"></div>
	
	<a href="${ctx }/shop/seller/member/examList?ifrmScroll=1" target="ifrmSms"><font color="262626"><b>전체보기</b></font></a>
	<table border="1" bordercolor="#dddddd" style="border-collapse:collapse;">
	<col align="center" span="10">
	<tr>
		<c:forEach items="${codeUtil:codeitem3()}" var="codeList" varStatus="st">
			<td width="100" height="25">
				<a href="${ctx }/shop/seller/member/examList?ifrmScroll=1&category=${codeList.itemnm}" target="ifrmSms"><font class="small" color="161616">${codeList.itemnm}</a> 
				(<font color="0074ba"><b>${codeList.cnt}</b></font>)
			</td>
			<c:if test="${ (st.count % 6) == 0  }">
				</tr><tr>
			</c:if>
		</c:forEach>
	</tr>
	</table>

	<iframe id="ifrmSms" name="ifrmSms" src="${ctx }/shop/seller/member/examList?ifrmScroll=1" style="width:100%;height:100px" frameborder="0" scrolling="no"></iframe>
	</td>
</tr>
</table>
</div>