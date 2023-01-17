<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script language="javascript">
if(window.addEventListener) 
{
	window.addEventListener('load',linecss,false); 
}
else 
{
	window.attachEvent('onload',linecss); 
}

if( '${sellerBoardFM.result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}
$(function(){
	$('#btn_save').on('click', function(){
		
		if(""== $('#subject').val()||undefined == $('#subject').val()){
			alert("제목을 입력해주세요")
			return false;
		}else if(""== $('#contents').val()||undefined == $('#contents').val()){
			alert("내용을 입력해주세요")
			return false;
		}else{
			$('#form')
			.attr('action', ctx+'/shop/seller/board/goodsQnaIndb')
			.submit();
		
		}
		
	});
})
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll" >

<form id=form name=form method=post  action="goodsQnaIndb" onsubmit="return chkForm(this)">
<input type=hidden name=sellerGoodsQnaVO.mode value="${sellerBoardFM.sellerGoodsQnaVO.mode }">
<input type=hidden name=sellerGoodsQnaVO.sno value="${sellerBoardFM.sellerGoodsQnaVO.sno }"}>
<input type=hidden name=sellerGoodsQnaVO.goodsno value="${sellerBoardFM.sellerGoodsQnaVO.goodsno }">
<input type=hidden name=sellerGoodsQnaVO.pageNo value="${sellerBoardFM.sellerGoodsQnaVO.pageNo }">
<input type=hidden name=sellerGoodsQnaVO.name value="${sellerBoardFM.sellerGoodsQnaVO.name }">

<div class="title title_top">상품문의에 대한 ${'modify' eq goodsQnaVO.mode ? '수정' : '답변' }<span></span></div>

<table class=tb>
<col class=cellC><col class=cellL>
<tr height=26>
	<td>상품</td>
	<td>
	<div style="float:left"><img src='/resources/shop/data/upload/goods/${fn:substringAfter(sellerBoardFM.sellerGoodsQnaVO.goodsQnaObj.imgs, "|")}' width="40px" height="40px" style="border: 1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" /></div>
	<div style="float:left;color:#0074BA;" class=def>${sellerBoardFM.sellerGoodsQnaVO.goodsQnaObj.goodsnm }</div>
	</td>
</tr>
<tr>
	<td>작성자</td>
	<td>
	<c:if test="${sellerBoardFM.sellerGoodsQnaVO.mode == 'reply' }">
		[${sellerBoardFM.sellerGoodsQnaVO.name}]
	</c:if>

	<c:if test="${sellerBoardFM.sellerGoodsQnaVO.mode != 'reply' }">
		[${sellerBoardFM.sellerGoodsQnaVO.goodsQnaObj.name}]
	</c:if>
	</td>
</tr>
<tr>
	<td>작성일</td>
	<td><font class=ver8><fmt:formatDate value="${sellerBoardFM.sellerGoodsQnaVO.mode != 'reply' ? sellerBoardFM.sellerGoodsQnaVO.goodsQnaObj.regdt : sellerBoardFM.sellerGoodsQnaVO.regdt }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;IP: (${sellerBoardFM.sellerGoodsQnaVO.goodsQnaObj.ip })</font></td>
</tr>
<tr>
	<td>제목</td>
	<td><input type="text" name="sellerGoodsQnaVO.subject" id="subject" maxlength="100" value="${'reply' eq sellerBoardFM.sellerGoodsQnaVO.mode ? '' : sellerBoardFM.sellerGoodsQnaVO.goodsQnaObj.subject }" style="width:90%;" class=line></td>
</tr>
<tr>
	<td>문의</td>
	<td><textarea name="sellerGoodsQnaVO.contents" id="contents" cols=60 rows=9 style="width:90%;" class=tline>${'reply' eq sellerBoardFM.sellerGoodsQnaVO.mode ? '' : sellerBoardFM.sellerGoodsQnaVO.goodsQnaObj.contents }</textarea></td>
</tr>
</table>

<div class="button_popup">
	<input id="btn_save" type=image src="/resources/shop/admin/img/btn_confirm_s.gif">
	<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>

<script>
linecss();
table_design_load();
</script>