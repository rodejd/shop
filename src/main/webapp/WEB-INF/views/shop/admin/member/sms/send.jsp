<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../common/left.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<script language="JavaScript" type="text/JavaScript">

function goSend() {
	alert("관리자에게 문의해주시기 바랍니다.");
	return false;
	
	// 개별발송인 경우, 핸드폰번호 필수
	if($('#type1').is(":checked")){
		if( $('#phone').val() == '' ){
			alert("개별 발송에 필요한 핸드폰번호를 입력하세요.");
			$('#phone').focus();
			return;
		}
	}
	
	// 회신번호 validation
	if( $('#callbackphone').val() == '' ){
		alert("회신번호를 입력하셔야 합니다.");
		$('#callbackphone').focus();
		return;
	}
	
	// 문자내용 validation
	if($('#msg').val() ==""){			
		alert("메세지를 입력해주세요.");
		$("#msg").focus();
		return;
	}
	
	/* var data =  {
		msg: $('#msg').val()
		, phone:$('#phone').val()
		, type: $(':input:radio[name=type]:checked').val()
		, callback: $('#callback').val()
	}; */
	ajaxCallJsonPost("/shop/admin/member/sms/send.dojson", "frm2", function(result){		
		alert(JSON.stringify(result));
		alert(result.total + '건 SMS가 발송되었습니다.');
		location.href=ctx+"/shop/admin/member/sms/send";
	});
}

var isOpenSearch = 0;
function vSearch()
{
	openLayer('srch_member');
	if (!isOpenSearch){
		ifrmSearch.location.href = "popup.srch_member.jsp?ifrmScroll=1";
	}
	isOpenSearch++;
}
</script>

<div class="title title_top"><font face="굴림" color="black">
	<b>SMS</b></font> 보내기<span>SMS 문자메세지를 통해 이벤트, 공지, 배송정보 등을 효과적으로 알리세요.</span>
	<!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=member&no=8',870,800);"><img src="../img/btn_q.gif" align="absmiddle" hspace="2" /></a> -->
</div>

<form name="frm2" id="frm2"> <!-- onsubmit="return chkForm(this);"> -->
<input type="hidden" name="mode" value="send_sms">
<input type="hidden" name="returnUrl" value="${ctx}/shop/admin/index.jsp">

<table class="tb">
<col class="cellC"><col class="cellL">
<tr>
	<td align="left"><input type="radio" name="type" id="type1" value="1" class="null" checked = "checked"><font color="262626">개별 발송</font></td>
	<td>
	<a href="javascript:popupLayer('popup_srch_member?ifrmScroll=1',380,500)" onfocus="blur();"><img src="/resources/shop/admin/img/btn_member_search.gif" align="absmiddle" /></a>
	<a href="javascript:popupLayer('popup_srch_address?ifrmScroll=1',380,500)" onfocus="blur();"><img src="/resources/shop/admin/img/btn_smsaddress_search.gif" align="absmiddle" /></a>
	<a href="javascript:popupLayer('popup_srch_seller?ifrmScroll=1',380,500)" onfocus="blur();"><img src="/resources/shop/admin/img/btn_selleraddress_search.gif" align="absmiddle" /></a>

	<!-- 회원검색 아이프레임 -->
	<div id="srch_member" style="position:absolute;border:1 solid #cccccc;display:none;">
	<iframe id="ifrmSearch" frameborder="0" style="width:380;height:500;"></iframe>
	</div>
	<font class="small1" color="262626">핸드폰번호 : </font>
	<textarea id="phone" name="phone" style="overflow:visible;width:126px;" class="tline"></textarea> <font class="extext">번호입력후 엔터키를 누르면 추가됩니다</font>
	</td>
</tr>
<tr>
	<td align="left"><input type="radio" name="type" id="type2" value="2" class="null" ><font color="262626">회원 그룹별 발송</font></td>
	<td>
		<select id="klevel" name="klevel">
			
			<c:forEach items="${ smsSendVO.gdMemberGrpList }" var="gdMemberGrpList" varStatus="st">
				<c:set var="totalSmsCnt"  value="${(gdMemberGrpList.smsCnt)+totalSmsCnt }"/><!-- 발송인원 총명수 -->
				<option value="${ gdMemberGrpList.kLevel }" > ${ gdMemberGrpList.grpnm } (${ gdMemberGrpList.smsCnt } 명)
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td align="left"><input type="radio" name="type" id="type3" value="3" class="null"><font color="262626">전체회원 발송</font></td>
	<td>발송인원 총 (${totalSmsCnt}명)</td>
</tr>
<!-- <tr>
	<td><font color="262626">SMS 발송현황</td>
	<td>
	<div style="background:#D7D7D7;border:0 solid #C5C5C5;width:600px;height:10px;font:0;">
	<div id="sms_bar" style="width:0;height:10px;font:0;background:#ff0000;"></div>
	</div>
	</td>
</tr> -->
</table>

<div id="MSG01">
<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />개별발송시 받는 사람 입력란에 번호를 넣고 Enter키를 누르면 전화번호를 추가 할 수 있습니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />SMS 발송시 SMS 발송현황에 진행사항이 보여집니다.</td></tr>
</table>
</div>
<script language="JavaScript" type="text/JavaScript">cssRound('MSG01');</script>

<div style="padding-top:10px"></div>

<!-- SMS보내기 : Start -->
<div class="title title_top"><font face="굴림" color="black"><b>SMS</b></font> 발송하기<span>검색된 회원들에게 SMS를 전송합니다 </span></div>
<%-- <% requestSet.setProperty("required", "required"); %> --%>
<%@ include file="_smsForm.jsp" %>
<!-- SMS보내기 : End -->

</form>



<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>