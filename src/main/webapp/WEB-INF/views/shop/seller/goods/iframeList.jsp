<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">

	function move(idx){
		var i = 0;
		var objName = '${sellerGoodsFM.name}';
		var tb0 = document.getElementById('tb0');
		var tb = parent.document.getElementById('tb_${sellerGoodsFM.name}');
		var parentTb$tr = $("#tb_${sellerGoodsFM.name} tr",  parent.document);
		
		for(i = 0; i < parentTb$tr.length ; i++){
			if($(parentTb$tr[i]).find("input:hidden[name='e_${sellerGoodsFM.name}[]']").val()
					== $($("#tb0 tr")[idx]).find("input:hidden[name='e_${sellerGoodsFM.name}[]']").val() ){
				alert("이미 추가한 상품입니다.");
				return false;
			}
			
		}
		
		oTr = tb.insertRow(0);
		oTd = oTr.insertCell(-1);
		oTd.innerHTML = tb0.rows[idx].cells[0].innerHTML;
		oTd = oTr.insertCell(-1);
		oTd.innerHTML = tb0.rows[idx].cells[1].innerHTML;
	
		tb.rows[0].className = "hand";
		tb.rows[0].onclick = function(){ parent.spoit(objName, this); }
		tb.rows[0].ondblclick = function(){ parent.remove_goods(objName, this); }
		parent.react_goods('${sellerGoodsFM.name}');
		
		if('refer' =='${sellerGoodsFM.name}'){
			$("#relationCnt", parent.document).text(parentTb$tr.length + 1);
		}
	}
	
	function goPage(page){
		//model값 가져오기
		var category = '${sellerGoodsFM.schCategory}';
		var name = '${sellerGoodsFM.name}';
		var goodsnm = '${sellerGoodsFM.schWord}';
		
		window.location.href=ctx+"/shop/admin/goods/iframeList?popView=Y&pageNo=" + page + "&name=" + name + "&schCategory=" + category + "&schKey=goodsnm&schWord=" + goodsnm;
	}
</script>
	<div id="register_goods" style="padding:3px">
		<div class="boxTitle">- 상품리스트 ${sellerGoodsFM.name} <font class=small color=#F2F2F2>(등록하려면 더블클릭)</font></div>

			<c:if test="${!empty sellerGoodsFM.sellerGoodsList}">
				<table id=tb0 class=tb>
					<c:forEach items="${sellerGoodsFM.sellerGoodsList}" var="list" varStatus="status">
						<c:set var="bgColor" value="${0 < ((status.index) % 2) ? '#f7f7f7' : '#ffffff' }"></c:set>
						<tr bgcolor="${bgColor}" ondblclick="move(this.rowIndex)" class=hand>
							<td width="50" nowrap>
									${shopLibFunction:goodsimg( list.imgs ,"40","",4)}
							</td>
							<td width=100% nowrap>
								<div style="overflow:hidden;">${list.goodsnm}</div>
								<b><fmt:formatNumber value="${list.price}" pattern="\#,###.##"/></b>
								<input type="hidden" name="e_${sellerGoodsFM.name}[]" value="${list.goodsno}">
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>

		<!-- 페이징  -->
		<tags:paginator currentPageNo="${sellerGoodsFM.pageNo}" rowCount="${sellerGoodsFM.rowCount}" pageSize="${sellerGoodsFM.pageSize}"  pageGroupSize="${sellerGoodsFM.pageGroupSize}" />
	</div>