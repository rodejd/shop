<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script language="JavaScript" type="text/JavaScript">
if( '${sellerBoardFM.result}' == 1 ){
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
$(function(){
	$('#btn_save').on('click', function(){
		
		 if(""== $('#contents').val()||undefined == $('#contents').val()){
			alert("내용을 입력해주세요")
			return false;
		}else{
			$('#form')
			.attr('action', ctx+'/shop/seller/board/goodsReviewIndb')
			.submit();
		}
		
	});
})

</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll" >

<form name=form method=post action="goodsReviewIndb" onsubmit="return chkForm(this)">
<input type=hidden name=sellerGoodsReviewVO.mode value=${sellerBoardFM.sellerGoodsReviewVO.mode }>
<input type=hidden name=sellerGoodsReviewVO.sno value=${sellerBoardFM.sellerGoodsReviewVO.sno }>
<input type=hidden name=sellerGoodsReviewVO.writer_mno value=${sellerBoardFM.sellerGoodsReviewVO.mno }>
<input type=hidden name=sellerGoodsReviewVO.goodsno value=${sellerBoardFM.sellerGoodsReviewVO.goodsno }>
<input type=hidden name='sellerGoodsReviewVO.page' value="">
<div class="title title_top">상품평 ${'modify' eq sellerBoardFM.sellerGoodsReviewVO.mode ? '수정' : '답변' }<span></span></div>

<table class=tb>
<col class=cellC><col class=cellL>
<tr height=26>
	<td>상품</td>
	<td>
	<div style="float:left">
	<img src="/resources/shop/data/upload/goods/${fn:substringAfter(sellerBoardFM.sellerGoodsReviewVO.goodsObj.img_s, '|') }" width="40px" height="40px" style="border:1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" />
	</div>
	<div style="float:left;color:#0074BA;" class=def>${sellerBoardFM.sellerGoodsReviewVO.goodsObj.goodsnm }</div>
	</td>
</tr>
<tr>
	<td>${'modify' eq sellerBoardFM.sellerGoodsReviewVO.mode ? '작성자' : '답변관리자' }</td>
	<td>
		<font class=ver8>
				${sellerBoardFM.sellerGoodsReviewVO.memberObj.mid } [ ${sellerBoardFM.sellerGoodsReviewVO.memberObj.name } ]
		</font>
	</td>
</tr>
<tr>
	<td>작성일</td>
	<td><font class=ver8><fmt:formatDate value="${sellerBoardFM.sellerGoodsReviewVO.goodsRevwObj.regdt}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;</font></td>
</tr>
<tr>
	<td>평점</td>
	<td>${stringUtil:rpad("",sellerBoardFM.sellerGoodsReviewVO.goodsRevwObj.point,'★')}</td>
</tr>

	
<tr>
	<td>평</td>
	<td><textarea name="sellerGoodsReviewVO.contents" id="contents" cols=60 rows=9 style="width:90%;" class=tline>${sellerBoardFM.sellerGoodsReviewVO.goodsRevwObj.contents }</textarea></td>
</tr>
</table>

<div class="button_popup">
<input type=image src="/resources/shop/admin/img/btn_confirm_s.gif" id="btn_save">
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>
