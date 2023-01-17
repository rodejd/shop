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

if( '${result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll" >

<form name=form method=post action="indb" onsubmit="return chkForm(this)">
<input type=hidden name=mode value="${goodsQnaVO.mode }">
<input type=hidden name=sno value="${goodsQnaVO.sno }"}>
<input type=hidden name=goodsno value="${goodsQnaVO.goodsno }">
<input type=hidden name=pageNo value="${goodsQnaVO.pageNo }">
<div class="title title_top">상품문의에 대한 ${'modify' eq goodsQnaVO.mode ? '수정' : '답변' }<span></span></div>

<table class=tb>
<col class=cellC><col class=cellL>
<tr height=26>
	<td>상품</td>
	<td>
	<div style="float:left"><img src='${goodsQnaVO.goodsQnaObj.imgs}' width="40px" height="40px" style="border: 1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" /></div>
	<div style="float:left;color:#0074BA;" class=def>${goodsQnaVO.goodsQnaObj.goodsnm }</div>
	</td>
</tr>
<tr>
	<td>작성자</td>
	<td>
	<c:if test="${goodsQnaVO.mode == 'reply' }">
			<select name="mno">
			<c:forEach items="${goodsQnaVO.adminMemList }" var="list">
				<option value="${list.mno}">${list.mid}[${list.name}]</option>
			</c:forEach>
		</select>
	</c:if>

	<c:if test="${goodsQnaVO.mode != 'reply' }">
		${goodsQnaVO.goodsQnaObj.mid != null ? goodsQnaVO.goodsQnaObj.mid : goodsQnaObj.goodsQnaObj.name }
	</c:if>
	</td>
</tr>
<tr>
	<td>작성일</td>
	<td><font class=ver8><fmt:formatDate value="${goodsQnaVO.mode != 'reply' ? goodsQnaVO.goodsQnaObj.regdt : goodsQnaVO.regdt }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;IP: (${goodsQnaVO.goodsQnaObj.ip })</font></td>
</tr>
<tr>
	<td>제목</td>
	<td><input type="text" name="subject" value="${'reply' eq goodsQnaVO.mode ? '' : goodsQnaVO.goodsQnaObj.subject }" style="width:90%;" class=line maxlength='50'></td>
</tr>
<tr>
	<td>문의</td>
	<td><textarea name="contents" cols=60 rows=9 style="width:90%;" class=tline maxlength='2000'>${'reply' eq goodsQnaVO.mode ? '' : goodsQnaVO.goodsQnaObj.contents }</textarea></td>
</tr>
</table>

<div class="button_popup">
<input type=image src="/resources/shop/admin/img/btn_confirm_s.gif">
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>

<script>
linecss();
table_design_load();
</script>