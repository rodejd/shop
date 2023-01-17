<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="/WEB-INF/views/shop/admin/common/header.jsp" %>
<%@ include file="/WEB-INF/views/shop/admin/common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script language="javascript">
	$(function(){
		//저장&수정
		$(".btnSave").on("click",function(){
			if($("#binCd").val() == ""){
				alert("Bin코드를 입력해주세요.");
				$("#binCd").focus();
				return;
			}
			if($("#goodsnm").val() == ""){
				alert("상품명을 입력해주세요.");
				$("#goodsnm").focus();
				return;
			}
			if($("#price").val() == ""){
				alert("희망가를 입력해주세요.");
				$("#price").focus();
				return;
			}
			if($("#binCd").data("bincd") != $("#binCd").val()){
				$("#sno").val(0);
				$("#mode").val("register");
			}
			if($("#sno").val() != 0 ){
				$("#mode").val("modify");
			}
			
			ajaxCallJson("/shop/admin/goods/manage/manageRegister.dojson"
				, $("[name=writeForm]").serialize()
				, function(result){
					if(result.goodsManageVO.sno == 0){
						alert(result.msg);
						return;
					}
					fnSearch();
				});
		});
		//삭제
		$(".btnDelete").on("click",function(){
			if(!confirm("Bin코드를 삭제하시겠습니까?")) return;
			
			$("#sno").val($(this).data("sno"));
			$("#mode").val("delete");
			ajaxCallJson("/shop/admin/goods/manage/manageRegister.dojson"
					, $("[name=writeForm]").serialize()
					, function(result){
						if(result.goodsManageVO.sno == 0){
							alert(result.msg);
							return;
						}
						alert("삭제되었습니다.");
						fnSearch();
					});
		});
		
		//수정값 세팅
		$(".clsBinCd").on("click",function(){
			$("#sno").val($(this).data("sno"));
			$("#binCd").val($(this).data("bincd"));
			$("#binCd").data("bincd", $(this).data("bincd"));
			$("#goodsnm").val($(this).data("goodsnm"));
			$("#price").val($(this).data("price"));
		});
	});
	
	//검색
	function fnSearch(){
		$("[name=frmList]").attr("action", "/shop/admin/goods/manage/list").submit();
	}
	
	//저장
	function fnSavemange(){
		var goodsArr = new Array();
		var manageArr = new Array();
		
		$("#goodsManageList input:checkbox").each(function(){
			var manage = "N";
			
			goodsArr.push($(this).val());
			if( $(this).is(":checked") ){
				manage = "Y";
			}
			manageArr.push(manage);
		});
		
		jQuery.ajaxSettings.traditional = true;
		ajaxCallJson("/shop/admin/goods/manage/manageProc.dojson"
				, {"goodsArr" : goodsArr, "manageArr" : manageArr}
				, function(res){
					if(res.result == 0){
						alert(result.msg);
						return;
					}
					fnSearch();
				});
	}
	
	function goPage(page){
		$("#pageNo").val(page);
		fnSearch();
	}
</script>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

		<form name="writeForm" action="list">
			<input type="hidden" id="sno" name="sno" value="0"/>
			<input type="hidden" id="mode" name="mode" value=""/>
			
			<table class=tb>
				<col class=cellC><col class=cellC><col class=cellC><col class=cellC>
				<tr>
					<th>Bin코드</th>
					<th>상품명</th>
					<th>희망가</th>
					<th></th>
				</tr>
				<tr>
					
					<td style="text-align: center;"><input type="text" id="binCd" name="binCd" value="${goodsManageVO.binCd}" class="line" style="width: 300px; height:22px;"></td>
					<td style="text-align: center;"><input type="text" id="goodsnm" name="goodsnm" value="${goodsManageVO.goodsnm}" class="line" style="width: 300px; height:22px;"></td>
					<td style="text-align: center;"><input type="text" id="price" name="price" value="${goodsManageVO.price}" class="line" style="width: 300px; height:22px;" class="line"  maxlength=10 data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)"></td>
					<td style="text-align: center;"><a href="javascript:;" class="btnSave"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a></td>
				</tr>
				
				<c:forEach items="${goodsManageVO.goodsManageList}" var="goodsManageList" varStatus="status">
					<tr>
						<td style="text-align: center;"><a href="javascript:;" class="clsBinCd" data-sno="${goodsManageList.sno}" data-bincd="${goodsManageList.binCd}" data-goodsnm="${goodsManageList.goodsnm}" data-price="<fmt:formatNumber value="${goodsManageList.price}" pattern="0.00"/>">${goodsManageList.binCd}</a></td>
						<td style="text-align: center;">${goodsManageList.goodsnm}</td>
						<td style="text-align: center;"><fmt:formatNumber value="${goodsManageList.price}" pattern="0.00"/></td>
						<td style="text-align: center;"><a href="javascript:;" class="btnDelete" data-sno="${goodsManageList.sno}"><img src="/resources/shop/admin/img/i_del.gif" align="absmiddle"></td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="button_top">
				<img src="/resources/shop/admin/img/btn_search2.gif" onclick="javascript:fnSearch();">
			</div>
		</form>
		
		<div style="padding-top:15px"></div>
		
		<form name="frmList" method="post">
			<input type=hidden id=pageNo name="pageNo" value="1"/>
			
			<table width=100% cellpadding=0 cellspacing=0>
				<tr>
					<td class=pageInfo><font class=ver8>
						<c:set var="pages" value="${(goodsManageVO.rowCount*10) / (goodsManageVO.pageSize*10)} " />
						<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
						<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
						총 <b>${goodsManageVO.rowCount }</b>개, <b>${goodsManageVO.pageNo }</b> of <b>${var3 }</b> Pages
					</td>
					<td align=right>
			
						<table cellpadding=0 cellspacing=0 border=0>
							<tr>
								<td style="padding-left:20px">
									<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
									<select name=pageSize onchange="this.form.submit()">
										<option value="10" ${stringUtil:selected(goodsManageVO.pageSize, "10")} >10개 출력
										<option value="20" ${stringUtil:selected(goodsManageVO.pageSize, "20")} >20개 출력
										<option value="40" ${stringUtil:selected(goodsManageVO.pageSize, "40")} >40개 출력
										<option value="60" ${stringUtil:selected(goodsManageVO.pageSize, "60")} >60개 출력
										<option value="100" ${stringUtil:selected(goodsManageVO.pageSize, "100")} >100개 출력
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<table id="goodsManageList" width=100% cellpadding=0 cellspacing=0 border=0>
				<tr><td class=rnd colspan=15></td></tr>
				<tr class=rndbg>
					<th width=60>번호</th>
					<th>Bin코드</th>
					<th>등록일</th>
					<th>이미지</th>
					<th>Bin</th>
					<th>SCM번호</th>
					<th>카테고리</th>
					<th>상품명</th>
					<th>부틱명</th>
					<th>즉시할인가</th>
					<th>재고</th>
					<th>판매여부</th>
					<th>관리상품확정</th>
				</tr>
				<c:forEach items="${goodsManageVO.goodsDataList}" var="goodsDataList" varStatus="status">
					<tr height="25" >
						<td align="center"><font class=ver8 color=616161>${(goodsManageVO.rowCount - status.index) - ( goodsManageVO.rowStart ) }</font></td>
						<td align=center><font class=ver81 color=444444>${goodsDataList.binCd }</td>
						<td align=center><font class=ver81 color=444444><fmt:formatDate value="${goodsDataList.regdt}" pattern="yyyy-MM-dd"/></td>
						<td style="border:1px #e9e9e9 solid;width:50px" align="center">
							<a href="/shop/admin/goods/register?mode=modify&goodsno=${goodsDataList.goodsno}">
								<img src='${fn:replace(goodsDataList.imgs, '|', '')}' width="40" onerror="onErrorImg(this, 'noimg_100.gif', 'kr');">
							</a>
						</td>
						<td align=center><font class=ver81 color=444444>${goodsDataList.optionBinCd}</td>
						<td align=center><font class=ver81 color=444444>${goodsDataList.goodscd }</td>
						<td align=center><font class=ver81 color=444444>${goodsDataList.categoryNm }</td>
						<td><font color=303030>${goodsDataList.goodsnm }</td>
						<td align=center><font class=ver81 color=444444>${goodsDataList.sellerCd }</td>
						<td align=center><font class=ver81 color=444444>${goodsDataList.optionPrice }</td>
						<td align=center><font class=ver81 color=444444>${goodsDataList.stock }</td>
						<td align=center>
							<c:choose>
								<c:when test="${goodsDataList.open eq '3'}">영구중지</c:when>
								<c:when test="${goodsDataList.open eq '2'}">일시중지</c:when>
								<c:when test="${goodsDataList.open eq '1'}">판매중</c:when>
								<c:otherwise>전시대기</c:otherwise>
							</c:choose>
						</td>
						<td class="noline" align="center">
							<input type="checkbox" name="chk[]" value="${goodsDataList.goodsno}" ${goodsDataList.manageYn eq 'Y' ? 'checked' : ''}/>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<!-- 페이징  -->
			<tags:paginator currentPageNo="${goodsManageVO.pageNo}" rowCount="${goodsManageVO.rowCount}" pageSize="${goodsManageVO.pageSize}"  pageGroupSize="${goodsManageVO.pageGroupSize}" />
	
			<div class="button">
				<input type="button" class="btn btn-primary" value="저장" onclick="javascript:fnSavemange();" />
			</div>
		</form>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


	</td>
</tr>
</table>

<%@ include file="/WEB-INF/views/shop/admin/common/bottom.jsp" %>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

