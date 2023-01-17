<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!-- Jquery Setting-->
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
<!-- //Jquery Setting-->

<html>
<head>
<title>'Xmall 관리자 모드'</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>
<script language="javascript">
if(window.addEventListener) 
{
	window.addEventListener('load',linecss,false); 
}
else 
{
	window.attachEvent('onload',linecss); 
}
</script>
<script language="JavaScript" type="text/JavaScript">
if( '${result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}

function chkLength(obj){
	str = obj.value;
	document.getElementById('vLength').innerHTML = chkByte(str);
	if (chkByte(str)>80){
		alert("80byte까지만 입력이 가능합니다");
		obj.value = strCut(str,80);
	}
}
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll" >

<form name=form method=post action="indb" onsubmit="return chkForm(this)">
<input type=hidden name=mode value=${goodsRevwVO.mode }>
<input type=hidden name=sno value=${goodsRevwVO.sno }>
<input type=hidden name=writer_mno value=${goodsRevwVO.mno }>
<input type=hidden name=goodsno value=${goodsRevwVO.goodsno }>
<input type=hidden name='page' value="">
<div class="title title_top">상품평 ${'modify' eq goodsRevwVO.mode ? '수정' : '답변' }<span></span></div>

<table class=tb>
<col class=cellC><col class=cellL>
<tr height=26>
	<td>상품</td>
	<td>
	<div style="float:left">
	<img src="${goodsRevwVO.goodsObj.img_s }" width="40px" height="40px" style="border:1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" />
	</div>
	<div style="float:left;color:#0074BA;" class=def>${goodsRevwVO.goodsObj.goodsnm }</div>
	</td>
</tr>
<tr>
	<td>${'modify' eq goodsRevwVO.mode ? '작성자' : '답변관리자' }</td>
	<td>
		<font class=ver8>
				${goodsRevwVO.memberObj.mid } [ ${goodsRevwVO.memberObj.name } ]
		</font>
	</td>
</tr>
<tr>
	<td>작성일</td>
	<td><font class=ver8><fmt:formatDate value="${goodsRevwVO.goodsRevwObj.regdt}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;</font></td>
</tr>
<tr>
	<td>평점</td>
	<td>${stringUtil:rpad("",goodsRevwVO.goodsRevwObj.point,'★')}</td>
</tr>

<c:if test="${'Y' eq goodsRevwVO.goodsRevwObj.apply && 'reply' eq goodsRevwVO.mode }">
	<c:if test="${'Y' eq goodsRevwVO.goodsRevwObj.apply2 }">
	
		<tr>
			<td>적립금지급이유</td>
			<td>
			<select name="memo" required label="지급이유" onchange="openLayer('direct', (this.value=='direct' ? 'block' : 'none') )" style="float:left;">
				
				<option value="">- 선택하세요 -</option>
					${webUtil:makeSelectCodeItem2((codeUtil:codeitem('point')), "상품후기 작성 포인트 적립") }
<%-- 				<%
				for(int i=0; i<rtList_point.getRowCount(); i++)
				{
				%>
					<option value='<%=rtList_point.get(i,"itemcd") %>' <%=StringUtil.selected("상품후기 작성 포인트 적립",rtList_point.get(i,"itemnm")) %> ><%=rtList_point.get(i,"itemnm") %></option>
				<%
				}
				%> --%>
				<option value="direct">☞ 직접입력</option>
			</select>
			<div id="direct" style="display:none;"><input type="text" name="direct_memo" size="30" /></div>
			</td>
		</tr>
		<tr>
			<td>적립금지급</td>
			<td>
			<input type="hidden" name="emoneyPut" value="Y" />
			₩<input type="text" name="emoney" value='${goodsRevwVO.memberObj.emoney }'  size="6" class="rline" onkeydown="onlynumber();" />
			※${goodsRevwVO.memberObj.mid } [ ${goodsRevwVO.memberObj.name } ] 회원에게 지급
			</td>
		</tr>
	
	</c:if>
</c:if>
	
<tr>
	<td>평</td>
	<td><textarea name="contents" cols=60 rows=9 style="width:90%;" class=tline>${goodsRevwVO.goodsRevwObj.contents }</textarea></td>
</tr>
</table>

<div class="button_popup">
<input type=image src="/resources/shop/admin/img/btn_confirm_s.gif">
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>








<script>table_design_load();</script>