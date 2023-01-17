<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script language=javascript src="/resources/shop/admin/common.js"></script>
<script>
	function sort(sort){
		var fm = document.frmList;
		fm.odby.value = sort;
		fm.submit();
	}
	
	function grpMove(){
		var grno = $("#grno").val();
		if (grno == ""){
			alert("이동할 구분을 선택하세요.");
			$("#grno").focus();
			return false;
		}
		
		var pgno = [];
		$("input[name='chk[]']:checked").each(function(i){
			pgno.push($(this).val());
		});
		
		if (pgno.length == 0){
			alert("이동할 상품을 선택하세요.");
			return false;
		}
		
		$.ajax({
			type: "post",
			url: "groupMove",
			headers: { "Content-Type": "application/json" },
			data: JSON.stringify({"grno": grno, "pgno": pgno.join(",")}),
			dataType: "json",
			success: function(rst){
				//alert(rst.cnt + "건 구분이동");
				parent.location.reload();
			},
			error: function(request, status, error){
				console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
	}
	
	function goodsDel(){
		var pgno = [];
		$("input[name='chk[]']:checked").each(function(i){
			pgno.push($(this).val());
		});
		
		if (pgno.length == 0){
			alert("삭제할 상품을 선택하세요.");
			return false;
		}
		
		if (confirm("정말로 삭제하시겠습니까?")){
			$.ajax({
				type: "post",
				url: "goodsDel",
				headers: { "Content-Type": "application/json" },
				data: JSON.stringify({"pgno": pgno.join(",")}),
				dataType: "json",
				success: function(rst){
					//alert(rst.cnt + "건 삭제");
					parent.location.reload();
				},
				error: function(request, status, error){
					console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			});
		}
	}
	
	function sortSave(frm){
		var list = [];
		$("[name='chk[]']").each(function(i){
			if ($(this).val().trim() != ""){
				var pmg = {};
				pmg.pgno = $("[name='chk[]']").eq(i).val();
				pmg.sort = $("[name='sort[]']").eq(i).val();
				list.push(pmg);
			}
		});

		$.ajax({
			type: "post",
			url: "saveSortChange",
			headers: { "Content-Type" : "application/json" },
			data: JSON.stringify({"list": list}),
			dataType: "json",
			success: function(rst){
				//alert("총 " + rst.tot + "건, 추가:" + rst.ins + ", 삭제:" + rst.del);
				parent.location.reload();
			},
			error: function(request, status, error){
				console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
		
		return false;
	}
	
</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
	
		<div class="title title_top">
			기획전 상품 리스트
			<div style="float: right;">
				<button type="button" onclick="popupLayer('/shop/admin/promotion/promotion/groupPopup?pmno=${promotionGoodsVO.pmno}', 600, 500);">구분편집</button>
				<button type="button" onclick="popupLayer('/shop/admin/promotion/promotion/goodsBulkPopup?pmno=${promotionGoodsVO.pmno}', 600, 500);">대량등록</button>
				<button type="button" onclick="popupLayer('/shop/admin/promotion/promotion/goodsSrchPopup?pmno=${promotionGoodsVO.pmno}', 1200, 600);">검색등록</button>
			</div>
		</div>

		<form id="frmList" name="frmList" method="post" action="goods">
			<input type="hidden" id="schUseYn" name="schUseYn" value="${promotionGoodsVO.schUseYn}"/>
			<input type="hidden" id="schSkin" name="schSkin" value="${promotionGoodsVO.schSkin}"/>
			<input type="hidden" id="schWord" name="schWord" value="${promotionGoodsVO.schWord}"/>
			<input type="hidden" id="schSdt" name="schSdt" value="${promotionGoodsVO.schSdt}"/>
			<input type="hidden" id="schEdt" name="schEdt" value="${promotionGoodsVO.schEdt}"/>
			<input type="hidden" id="pageNo" name="pageNo" value="${pageNo != '' ? promotionGoodsVO.pageNo : '1'}"/>
			<input type="hidden" id="schUseYn" name="schUseYn" value="${promotionGoodsVO.schUseYn}"/>
			<input type="hidden" id="odby" name="odby" value="${promotionGoodsVO.odby}"/>
			<input type="hidden" id="pmno" name="pmno" value="${promotionGoodsVO.pmno}"/>
		
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="pageInfo">
						총 <font class="ver8"><b>${promotionGoodsVO.totalCnt}</b>개</font>
					</td> 
					<td align="right">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td valign="bottom">
									<span style="padding-left: 10px;">구분 <a href="javascript:sort('j.sort desc')"><img src="/resources/shop/admin/img/list_up_${promotionGoodsVO.odby eq 'j.sort desc' ? 'on' : 'off'}.gif"></a> <a href="javascript:sort('j.sort asc')"><img src="/resources/shop/admin/img/list_down_${promotionGoodsVO.odby eq 'j.sort asc' ? 'on' : 'off'}.gif"></a></span>
									<span style="padding-left: 10px;">재고순 <a href="javascript:sort('stock desc')"><img src="/resources/shop/admin/img/list_up_${promotionGoodsVO.odby eq 'stock desc' ? 'on' : 'off'}.gif"></a> <a href="javascript:sort('stock asc')"><img src="/resources/shop/admin/img/list_down_${promotionGoodsVO.odby eq 'stock asc' ? 'on' : 'off'}.gif"></a></span>
									<span style="padding-left: 10px;">리테일가순 <a href="javascript:sort('consumer desc')"><img src="/resources/shop/admin/img/list_up_${promotionGoodsVO.odby eq 'consumer desc' ? 'on' : 'off'}.gif"></a> <a href="javascript:sort('consumer asc')"><img src="/resources/shop/admin/img/list_down_${promotionGoodsVO.odby eq 'consumer asc' ? 'on' : 'off'}.gif"></a></span>
									<span style="padding-left: 10px;">즉시할인율순 <a href="javascript:sort('priceRate desc')"><img src="/resources/shop/admin/img/list_up_${promotionGoodsVO.odby eq 'priceRate desc' ? 'on' : 'off'}.gif"></a> <a href="javascript:sort('priceRate asc')"><img src="/resources/shop/admin/img/list_down_${promotionGoodsVO.odby eq 'priceRate asc' ? 'on' : 'off'}.gif"></a></span>
									<span style="padding-left: 10px;">공헌이익율순 <a href="javascript:sort('margin desc')"><img src="/resources/shop/admin/img/list_up_${promotionGoodsVO.odby eq 'margin desc' ? 'on' : 'off'}.gif"></a> <a href="javascript:sort('margin asc')"><img src="/resources/shop/admin/img/list_down_${promotionGoodsVO.odby eq 'margin asc' ? 'on' : 'off'}.gif"></a></span>
								</td>
								<td style="padding-left:20px">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	
			<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top:30px">
				<tr>
					<td height="35" style="padding-left:13px">
						<c:if test="${!empty promotionGoodsVO.goodsList && fn:length(promotionGoodsVO.goodsList) > 0}">
							<button type="button" onclick="sortSave()">순서 저장</button>
						</c:if>
					</td>
				</tr>
			</table>
			
			<table class="listTable" style="table-layout: unset;">
				<tr>
					<th><a href="javascript:chkBox(document.getElementsByName('chk[]'),'rev')" class="white">선택</a></th>
					<th>구분</th>
					<th>순서</th>
					<th>SCM번호</th>
					<th>부틱명</th>
					<th>카테고리명</th>
					<th>브랜드명</th>
					<th>시즌</th>
					<th>EU</th>
					<th>상품명</th>
					<th>재고</th>
					<th>리테일가</th>
					<th>즉시할인가</th>
					<th>즉시할인율</th>
					<th>공헌이익율</th>
					<th>최저가여부</th>
					<th>상품상태</th>
					<th>관리상품여부</th>
					<th>적립미부여</th>
					<th>Hot100</th>
					<th>Vip Shop</th>
				</tr>
				<c:forEach items="${promotionGoodsVO.goodsList}" var="list" varStatus="status">
					<tr class="trClass">
						<td><input type="checkbox" name="chk[]" value="${list.pgno}" /></td>
						<td>${list.grnm}</td>
						<td><input type="input" name="sort[]" value="${list.sort}" style="width:50px"/></td>
						<td>${list.goodscd}</td>
						<td>${list.sellerCd}</td>
						<td>${list.categoryNm}</td>
						<td>${list.brandnm}</td>
						<td>${list.seasonNm}</td>
						<td>${list.euYn eq 'Y' ? 'EU' : '非EU'}</td>
						<td style="padding: 5px; text-align: left;">
							<div><font color=303030>${list.goodsnm}</font></div>
							<div style="padding-top:3px"><font color=303030>${list.goodsnmKR}</font></div>
							<div style="padding-top:3px"><font color=303030>${list.goodsnmCN}</font></div>
						</td>
						<td>${stringUtil:getMoneyFormatString(list.stock)}</td>
						<td>${list.consumer}</td>
						<td><fmt:formatNumber value="${list.price}" pattern="0.00"/></td>
						<td>${list.priceRate}%</td>
						<td>${list.margin}%</td>
						<td>
							<c:choose>
								<c:when test="${list.lowestPriceYn eq 'Y'}">최저가</c:when>
								<c:when test="${list.lowest2PriceYn eq 'Y'}">차저가</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
							    <c:when test="${list.open eq '3'}">영구중지</c:when>
							    <c:when test="${list.open eq '2'}">일시중지</c:when>
							    <c:when test="${list.open eq '1'}">판매중</c:when>
							    <c:otherwise>전시대기</c:otherwise>
							</c:choose>
						</td>
						<td>${list.manageYn eq 'Y' ? '관리상품' : '일반상품' }</td>
						<td>${list.useemoney eq '1' ? 'Y' : 'N'}</td>
						<td>${list.hotYn eq '1' ? 'Y' : ''}</td>
						<td>${list.vipYn eq '1' ? 'Y' : ''}</td>
					</tr>
				</c:forEach>
			</table>
			
			<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top:30px">
				<tr>
					<td width="20%" height="35" style="padding-left:13px">
						<c:if test="${!empty promotionGoodsVO.goodsList && fn:length(promotionGoodsVO.goodsList) > 0}">
							<select id="grno" style="padding:3px">
								<option value="">선택</option>
								<c:forEach items="${promotionGoodsVO.groupList}" var="list">
									<option value="${list.grno}">${list.grnm}</option>
								</c:forEach>
							</select>
							<button type="button" onclick="grpMove()">구분이동</button>
							
							<button type="button" onclick="goodsDel()" style="margin-left:10px">선택삭제</button>
						</c:if>
					</td>
					<td width="60%" align="center">
						<a href="list?schUseYn=${promotionGoodsVO.schUseYn}&schSkin=${promotionGoodsVO.schSkin}&schWord=${promotionGoodsVO.schWord}&schSdt=${promotionGoodsVO.schSdt}&schEdt=${promotionGoodsVO.schEdt}&pageNo=${promotionGoodsVO.pageNo}"><img src="/resources/shop/admin/img/btn_list.gif"></a>
					</td>
					<td width="20%"></td>
				</tr>
			</table>

		</form>	
		
		<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>