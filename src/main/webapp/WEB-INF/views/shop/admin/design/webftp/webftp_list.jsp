<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<script>
$(document).ready(function(){
	var loca = get_nowDir().trim();
	$("#loca").text(loca);
});
</script>


<html>
<head>
<title>Webftp List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/shop/admin/design/webftp/../../style.css">
<link rel="styleSheet" href="/shop/admin/design/webftp/webftp.css">
<script src="/resources/shop/admin/cssRound.js"></script>
<SCRIPT LANGUAGE="JavaScript"> var curr_path = '/shop/admin/design/webftp/'; var webftpid = 'default'; </SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shop/admin/design/webftp/webftp.js"></SCRIPT>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="resizeFrame();">

<table width="100%" border="0" cellpadding="0" cellspacing="0" id="info">
  <caption>&#149;&nbsp;&nbsp;총 <b>3</b> 건 &nbsp;&nbsp; &#149 현재경로 : <b><a id="loca"></a></b></caption>
  <tr>
    <th>
      <FORM METHOD=get ACTION="">
      <input type="hidden" name="webftpid" value="default">
      <input type="hidden" name="srch_value" value="">
      <select name="totSort" onchange="this.form.submit();">
        <option value="name DESC">- 이름 정렬↑</option>
        <option value="name ASC" selected>- 이름 정렬↓</option>
        <option value="size DESC">- 크기 정렬↑</option>
        <option value="size ASC">- 크기 정렬↓</option>
        <option value="date DESC">- 날짜 정렬↑</option>
        <option value="date ASC">- 날짜 정렬↓</option>
      </select>
      <select name="totViewnum" onchange="this.form.submit();">
        <option value="10">10</option>
        <option value="15" selected>15</option>
        <option value="20">20</option>
        <option value="30">30</option>
        <option value="50">50</option>
        <option value="100">100</option>
        <option value="150">150</option>
        <option value="200">200</option>
        <option value="250">250</option>
      </select>
      </FORM>
    </th>
  </tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" id="list">
<FORM METHOD=POST ACTION="" name="WFlist">
  <tr>
	<td colspan="7" class="Round"></td>
  </tr>
  <tr>
	<th nowrap width="50"><span class="noline"><input type="checkbox" onclick="javascript:PubAllSordes( ( this.checked ? 'select' : 'deselect' ), WFlist['sepchkbox[]'] );"></span></th>
	<th>파일명</th>
	<th nowrap width="70">크기</th>
	<th nowrap width="60">파일받기</th>
	<th nowrap width="60">주소복사</th>
	<th nowrap width="120">마지막수정날짜</th>
	<th nowrap width="80">그림크기</th>
  </TR>
  <tr>
    <td colspan="7" class="Round"></td>
  </tr>
  <tr>
    <td nowrap class="Parent" colspan="7"><img src="/resources/shop/admin/design/webftp/../../img/webftp/up.gif" align=absmiddle border="0">&nbsp;<a href="javascript:;" onclick="dPathCookie( get_nowhighDir() );">..</a></td>
  </tr>
  <tr>
    <td colspan="7" class="Lines"></td>
  </tr>

  <tr>
    <td nowrap><span class="noline"><input type="checkbox" name="sepchkbox[]" value="data"></span></td>
    <td style="text-align:left"><img src="/resources/shop/admin/design/webftp/../../img/webftp/dir.gif" align=absmiddle border="0">&nbsp;&nbsp;<a href="javascript:;" onclick ="dPathCookie( '/' );">${popUpVO.currnetFile}</a></td>
    <td nowrap style="text-align:right">&nbsp;&nbsp;&nbsp;</td>
    <td nowrap></td>
    <td nowrap></td>
    <td nowrap>2010-04-08 00:18:24</td>
    <td nowrap></td>
  </tr>

  <tr>
    <td colspan="7" class="Lines"></td>
  </tr>
</FORM>
</table>

<div align=center class=pageNavi><font class=ver8> <b>1</b> </font></div>


<SCRIPT LANGUAGE="JavaScript">
</SCRIPT>



<SCRIPT LANGUAGE="JavaScript">

function resizeFrame(){

	var oBody = document.body;
	var oFrame = parent.document.getElementById("folder_frame");
	var i_height = oBody.scrollHeight + (oFrame.offsetHeight-oFrame.clientHeight);
	oFrame.style.height = i_height;

}

</SCRIPT>


<!-- 이미지 폴더 사이즈 체크 -->

<div style="padding-left:15">※ <font class=small><font color=EA0095>상품상세정보</font>에 들어가는 이미지들은 <font color=EA0095>data/editor</font> 폴더에 있습니다. 이곳을 자주 체크하세요.</font></div>
<div style="padding-left:15">※ <font class=small><font color=EA0095>data/editor</font> 폴더를 열어 리스트 상단의  '<font color=EA0095>크기 정렬↑</font>' 로 선택하고, 필요없는 이미지들을 확인 후 삭제하세요.</font></div>



<table border=0 cellpadding=0 cellspacing=0>
<tr><td style="padding-left:15px">※ <font class=small color=333333>고객님이 현재 쓰고 계신 총 용량은 <font class=ver8 color=#FF5A00><b>15.92M</b></font>입니다.</td></tr>
<tr><td height=4></td></tr>
</table>

<table border=0 cellpadding=0 cellspacing=0>
<tr><td style="padding-left:15px">
<iframe src="/resources/shop/admin/webFtp/popupWebFtp/bar_conf?G_Width=300&G_Height=30" width="300" height="30" frameborder=0 marginwidth=0 marginheight=0 scrolling=no></iframe>
</td></tr></table>

<div style="padding-top:5px"></div>

<table border=0 cellpadding=0 cellspacing=0>
<tr><td style="padding-left:15px">

<table border=1 bordercolor=#C3C2C2 style="border-collapse:collapse" cellpadding=3 cellspacing=0>
<tr>
	<td width=100 height=23 align=center bgcolor=white><font class=ver8><font class=ver8><b>editor</b></td>
	<td width=70 align=center bgcolor=white><font class=ver8>3.43M</td>
</tr>
<tr>
	<td width=100 height=23 align=center bgcolor=white><font class=ver8><font class=ver8><b>board</b></td>
	<td width=70 align=center bgcolor=white><font class=ver8>0K</td>
</tr>
<tr>
	<td width=100 height=23 align=center bgcolor=white><font class=ver8><font class=ver8><b>goods</b></td>
	<td width=70 align=center bgcolor=white><font class=ver8>2.65M</td>
</tr>
<tr>
	<td width=100 height=23 align=center bgcolor=white><font class=ver8><font class=ver8><b>skin</b></td>
	<td width=70 align=center bgcolor=white><font class=ver8>9.83M</td>
</tr>
<tr bgcolor=#F1F1F1>
	<td height=25 align=center><font class=small>총 용량</td>
	<td align=center><font class=ver8 color=#FF5A00><b>15.92M</b></td>
</table>
</td>

<td valign=top bgcolor=white>
<table border=1 bordercolor=#C3C2C2 style="border-collapse:collapse"  cellpadding=3 cellspacing=0>
<tr><td height=23 width=310 style="padding-left:5px"><font class=small1 color=444444>상품상세정보 등 에디터를 통해 등록된 이미지 및 파일용량</td></tr>
<tr><td height=23 style="padding-left:5px"><font class=small1 color=444444>게시판을 통해 등록된 이미지 및 파일용량</td></tr>
<tr><td height=23 style="padding-left:5px"><font class=small1 color=444444>현재 등록된 상품이미지의 총 용량</td></tr>
<tr><td height=23 style="padding-left:5px"><font class=small1 color=444444>디자인 관련 이미지 및 기타 이미지들의 용량</td></tr>
<tr><td height=25 style="padding-left:5px"><font class=small1 color=444444>현재 이용하고 계신 이미지 및 파일의 총 용량입니다</td></tr>
</table>
</td></tr></table>


<div style="padding-left:15px">
<table cellpadding=1 cellspacing=0 border=0 class=small_tip width=97%>
<tr><td height=10></td></tr>
<tr><td><font class=small color=333333>* 유의사항</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle">각종 이미지 파일 <font class=ver8 color=EA0095>(jpg, gif, swf</font><font color=EA0095> 만 가능)</font> 을 등록/복사/삭제 관리 할 수 있습니다.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=EA0095>이미지파일 1개당 최대 2MB까지</font> 등록 할 수 있습니다. </td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=EA0095>bmp 파일</font>은 화질에 비해 용량이 지나치게 크므로 <font color=EA0095>jpg나 gif로 변경하신 후 사용</font>하세요.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=EA0095>상품상세정보</font>에 들어가는 이미지들은 <font color=EA0095>data/editor</font> 폴더에 있습니다. 이곳을 자주 체크하세요.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=EA0095>data/editor</font> 폴더를 열어 '<font color=EA0095>크기 정렬↑</font>' 로 정렬한 다음, 필요없는 이미지들은 확인하여 삭제하세요.</td></tr>
</table>


<table cellpadding=1 cellspacing=0 border=0 class=small_tip width=97%>
<tr><td height=10></td></tr>
<tr><td bgcolor=BBBBBB></td></tr>
<tr><td height=10></td></tr>

<tr><td><font class=small color=333333>* 기능설명</td></tr>


<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=0074BA>파일올리기</font>: 저장폴더를 잘 선택한 후 파일을 올리세요.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=0074BA>자동등록</font>: 한개의 상품원본이미지만 등록하면 여러개의 이미지가 사이즈조정되어 자동등록되는 기능입니다.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=0074BA>새폴더</font>: 새로운 폴더를 생성합니다.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=0074BA>이름바꾸기</font>: 파일이름을 바꾸고자 할때 파일을 선택한 후 이름을 바꿉니다.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=0074BA>삭제하기</font>: 삭제할 파일/폴더를 선택하고 삭제합니다. 폴더를 삭제하면 폴더안의 파일도 삭제되므로 유의하세요.</td></tr>
<tr><td><img src="/resources/shop/admin/design/webftp/../../img//icon_list.gif" align="absmiddle"><font color=0074BA>파일명을 클릭하면 파일의 상세정보</font>가 보여집니다.</td></tr>
</table>
</div>

</body>
</html>
