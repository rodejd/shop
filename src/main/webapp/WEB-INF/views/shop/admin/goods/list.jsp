<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script language="javascript">
	$(function(){
		//관리상품
		$(".chkManage").on("click", function(){
			var goodsArr = new Array();
			var manageArr = new Array();
			
			goodsArr.push($(this).val());
			var manage = "N";
			if( $(this).is(":checked") ){
				manage = "Y";
			}
			manageArr.push(manage);
			
			jQuery.ajaxSettings.traditional = true;
			ajaxCallJson("/shop/admin/goods/manage/manageProc.dojson"
					, {"goodsArr" : goodsArr, "manageArr" : manageArr}
					, function(res){
						if(res.result == 0){
							alert(result.msg);
							return;
						}
						$("[name=frmList]").attr("action", "/shop/admin/goods/list").submit();
					});
		});
		//특별구좌
		$(".chkSpec").on("click", function(){
			var goodsArr = new Array();
			var specArr = new Array();
			var spec = $(this).data("type");
			
			goodsArr.push($(this).val());
			var specYn = "N";
			if( $(this).is(":checked") ){
				specYn = "Y";
			}
			specArr.push(specYn);
			
			jQuery.ajaxSettings.traditional = true;
			ajaxCallJson("/shop/admin/goods/specProc.dojson"
					, {"goodsArr" : goodsArr, "specArr" : specArr, "spec" : spec}
					, function(res){
						if(res.result == 0){
							alert(result.msg);
							return;
						}
						$("[name=frmList]").attr("action", "/shop/admin/goods/list").submit();
					});
		});
		
		//검색
		$(".btn_search").on("click",function(){
			goPage(1);
		});
	});
	
	function eSort(obj,fld) {
		var form = document.frmList;
		if (obj.innerText.charAt(1)=="▲") fld += " desc";
		form.sort.value = fld;
		$("[name=frmList]").attr("action", "/shop/admin/goods/list").submit();
	}
	
	function sort(sort)	{
		var fm = document.frmList;
		fm.sort.value = sort;
		$("[name=frmList]").attr("action", "/shop/admin/goods/list").submit();
	}
	function sort_chk(sort)	{
		if (!sort) return;
		sort = sort.replace(" ","_");
		var obj = document.getElementsByName('sort_'+sort);
		if (obj.length){
			div = obj[0].src.split('list_');
			for (var i=0, len=obj.length; i<len; i++){
				chg = (div[1]=="up_off.gif") ? "up_on.gif" : "down_on.gif";
				obj[i].src = div[0] + "list_" + chg;
			}
		}
	}
	
	window.onload = function(){ sort_chk(''); };
	
	
	<%-- // 게시 여부/승인여부 변경 --%>
	function chk_status_modify(statVal, mode) {
		var i = 0;
		var goodsChks = null;
		var statusModifyFrm = null;
		
		goodsChks = $("#goodsList input:checkbox:checked");
		statusModifyFrm = $("#statusModifyFrm");
		
		if(0 >= goodsChks.length){
			alert(("open_modify" == mode) ? "게시/게시취소 상품을 선택하세요." : "승인요청 상품을 선택하세요.");
			return false;
		}
		
		/* if("approvalstatus_modify" == mode){
			for(i = 0; i < goodsChks.length ; i++){
				if( "" == $.trim($(goodsChks[i]).data("sellercd")) || 1 != $(goodsChks[i]).data("aprlstat") ){
					alert("승인요청 상품을 선택하세요.");
					return false;
				}
			}
		} */
		
		if(confirm(("open_modify" == mode) ? "게시/게시취소 하시겠습니까?" : "승인/반려 하시겠습니까?")){
			goodsChks.each(function(){
				statusModifyFrm.append("<input type='hidden' name='goodsArr' value='"+$(this).val()+"'/>");
				statusModifyFrm.append("<input type='hidden' name='sellercodeArr' value='"+$(this).data("sellercd")+"'/>");
				statusModifyFrm.append("<input type='hidden' name='sellernoArr' value='"+$(this).data("sellerno")+"'/>");
			});
			
			$("#statVal").val(statVal);
			$("#mode").val(mode);
			
			statusModifyFrm.submit();
		}
		
	}

	function goPage(page) {
		$("#pageNo").val(page);
		$("[name=frmList]").attr("action", "/shop/admin/goods/list").submit();
	}
	
	function excelDownload(){
		$("[name=frmList]").attr("action", "/shop/admin/goods/goodsExcelDownload").submit();
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
<form id="frmList" name="frmList" action="list" method="post">
	<input type="hidden" id="pageNo" name="pageNo" value="${goodsVO.pageNo}"/>
	<input type="hidden" name="sort" value="">
	
	<div class="title title_top">
		전체상품리스트<span>등록하신 상품을 한눈에 살펴보시고 편리하게 수정하실 수 있습니다</span> 
		<!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=product&no=2',870,800)"><img src="../img/btn_q.gif" border=0 align="absmiddle" hspace=2></a> -->
	</div>
	<%-- 검색영역시작 --%>
	<%--
	<table class="tb">
		<colgroup>
			<col class="cellC">
			<col class="cellL">
			<col class="cellC">
			<col class="cellL">
			<col class="cellC">
			<col class="cellL">
		</colgroup>
		<tbody>
			<tr>
				<td>구분</td>
				<td>
					<select name="schRegty">
						<option value="">전체</option>
						<option value="scmapi" ${goodsVO.schRegty eq 'scmapi' ? 'selected' : ''}>SCM 연동</option>
						<option value="direct" ${goodsVO.schRegty eq 'direct' ? 'selected' : ''}>직등록</option>
					</select>
				</td>
				<td>상품등록일</td>
				<td colspan="3">
					<input type="text" name="schRegdt" value="${goodsVO.schRegdt[0]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8"> -
					<input type="text" name="schRegdt" value="${goodsVO.schRegdt[1]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
					<!--
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDate('yyyyMMdd')}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle"></a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle"></a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle"></a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle"></a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle"></a>
					<a href="javascript:setDate('schRegdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle"></a>
					-->

					<a href="javascript:setDate('schRegdt', ${dateUtil:getDate('yyyyMMdd')}, ${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">오늘</a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -7)}, ${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">일주일</a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -15)}, ${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">15일</a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">한달</a>
					<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">두달</a>
					<a href="javascript:setDate('schRegdt')" class="admin-sicon">전체</a>
				</td>
			</tr>
			<tr>
				<td>부틱</td>
				<td>
					<input type="text" name="schSellerNm" id="schSellerNm" value="${goodsVO.schSellerNm}" class="line" style="height:22px" />
					<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
					<input type="hidden" name="schSellerCd" id="schSellerCd" value="${goodsVO.schSellerCd}" />
				</td>
				<td>카테고리</td>
				<td colspan="3"><script>new categoryBox('cate', 4, '${goodsVO.schCate}');</script></td>
			</tr>
			<tr>
				<td>브랜드</td>
				<td>
					<select name="schBrand">
						<option value="">전체</option>
						<c:if test="${!empty goodsVO.goodsBrandList && fn:length(goodsVO.goodsBrandList)>0}">
							<c:forEach items="${goodsVO.goodsBrandList}" var="list">
								<option value="${list.sno}" ${goodsVO.schBrand eq list.sno ? 'selected' : ''}>${list.brandnm}</option>
							</c:forEach>
						</c:if>					
					</select>
				</td>
				<td>시즌</td>
				<td colspan="3">
					<select name="schSeasonNm" style="width:134px">
						<option value="">전체</option>
						<c:if test="${!empty goodsVO.goodsSeasonList && fn:length(goodsVO.goodsSeasonList)>0}">
							<c:forEach items="${goodsVO.goodsSeasonList}" var="season">
								<option value="${season}" ${goodsVO.schSeasonNm eq season ? 'selected' : ''}>${season}</option>
							</c:forEach>
						</c:if>					
					</select>
				</td>
			</tr>
			<tr>
				<td>검색어</td>
				<td>
					<select name="schKey">
						<option value="goodsnm" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsnm' ? 'selected' : ''}>상품명 영문</option>
						<option value="goodsnmKR" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsnmKR' ? 'selected' : ''}>상품명 국문</option>
						<option value="goodsnmCN" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsnmCN' ? 'selected' : ''}>상품명 중문</option>

						<option value="simnCd" ${goodsVO == null ? '' : goodsVO.schKey eq 'simnCd' ? 'selected' : ''}>Simn</option>
						<option value="binCd" ${goodsVO == null ? '' : goodsVO.schKey eq 'binCd' ? 'selected' : ''}>Bin</option>

						<option value="goodscd" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodscd' ? 'selected' : ''}>상품코드</option>
						<option value="goodsno" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsno' ? 'selected' : ''}>상품번호</option>
					</select>
					<input type="text" name="schWord" value="${goodsVO.schWord}" class="line" style="height:22px">
				</td>
				<td>판매상태</td>
				<td>
					<select name="schOpen">
						<option value="">전체</option>
						<option value="1" ${goodsVO == null ? '' : goodsVO.schOpen eq '1' ? 'selected' : ''}>판매중</option>
						<option value="0" ${goodsVO == null ? '' : goodsVO.schOpen eq '0' ? 'selected' : ''}>전시대기</option>
						<option value="2" ${goodsVO == null ? '' : goodsVO.schOpen eq '2' ? 'selected' : ''}>일시중지</option>
						<option value="3" ${goodsVO == null ? '' : goodsVO.schOpen eq '3' ? 'selected' : ''}>영구중지</option>
					</select>
				</td>
				<td>재고</td>
				<td>
					<select name="schSoldOut">
						<option value="">전체</option>
						<option value="Y" ${goodsVO == null ? '' : goodsVO.schSoldOut eq 'Y' ? 'selected' : ''}>유</option>
						<option value="N" ${goodsVO == null ? '' : goodsVO.schSoldOut eq 'N' ? 'selected' : ''}>무</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>특별구좌</td>
				<td>
					<select name="schSpec">
						<option value="">구좌선택</option>
						<option value="H" ${goodsVO == null ? '' : goodsVO.schSpec eq 'H' ? 'selected' : ''}>Hot100</option>
						<option value="V" ${goodsVO == null ? '' : goodsVO.schSpec eq 'V' ? 'selected' : ''}>VIP Shop</option>
					</select>
				</td>
				<td>관리상품</td>
				<td colspan="3">
					<select name="schManageYn">
						<option value=""  ${empty goodsVO.schManageYn ? 'selected' : '' }>전체상품</option>
						<option value="Y" ${goodsVO.schManageYn eq 'Y' ? 'selected' : '' }>관리상품</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	--%>

	<div class="div-tbl inp-tbl">
		<div class="row">
			<div class="th w-120">구분</div>
			<div style="width: 20%">
				<select name="schRegty">
					<option value="">전체</option>
					<option value="scmapi" ${goodsVO.schRegty eq 'scmapi' ? 'selected' : ''}>SCM 연동</option>
					<option value="direct" ${goodsVO.schRegty eq 'direct' ? 'selected' : ''}>직등록</option>
				</select>
			</div>
			<div class="th w-120">상품등록일</div>
			<div style="width: 35%;">
				<input type="text" name="schRegdt" value="${goodsVO.schRegdt[0]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
				-
				<input type="text" name="schRegdt" value="${goodsVO.schRegdt[1]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
				<a href="javascript:setDate('schRegdt', ${dateUtil:getDate('yyyyMMdd')}, ${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">오늘</a>
				<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -7)}, ${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">일주일</a>
				<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -15)}, ${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">15일</a>
				<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">한달</a>
				<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})" class="admin-sicon">두달</a>
				<a href="javascript:setDate('schRegdt')" class="admin-sicon">전체</a>
			</div>
		</div>

		<div class="row">
			<div class="th">부틱</div>
			<div>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${goodsVO.schSellerNm}" />
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
				<input type="hidden" name="schSellerCd" id="schSellerCd" value="${goodsVO.schSellerCd}" />
			</div>
			<div class="th w-120">카테고리</div>
			<div>
				<script>
					new categoryBox('cate', 4, '${goodsVO.schCate}');
				</script>
			</div>
		</div>

		<div class="row">
			<div class="th w-120">브랜드</div>
			<div>
				<select name="schBrand">
					<option value="">전체</option>
					<c:if test="${!empty goodsVO.goodsBrandList && fn:length(goodsVO.goodsBrandList)>0}">
						<c:forEach items="${goodsVO.goodsBrandList}" var="list">
							<option value="${list.sno}" ${goodsVO.schBrand eq list.sno ? 'selected' : ''}>${list.brandnm}</option>
						</c:forEach>
					</c:if>
				</select>
			</div>
			<div class="th w-120">시즌</div>
			<div>
				<select name="schSeasonNm">	<!--  style="width:134px" -->
					<option value="">전체</option>
					<c:if test="${!empty goodsVO.goodsSeasonList && fn:length(goodsVO.goodsSeasonList)>0}">
						<c:forEach items="${goodsVO.goodsSeasonList}" var="season">
							<option value="${season}" ${goodsVO.schSeasonNm eq season ? 'selected' : ''}>${season}</option>
						</c:forEach>
					</c:if>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="th w-120">검색어</div>
			<div>
				<select name="schKey">
					<option value="goodsnm" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsnm' ? 'selected' : ''}>상품명 영문</option>
					<option value="goodsnmKR" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsnmKR' ? 'selected' : ''}>상품명 국문</option>
					<option value="goodsnmCN" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsnmCN' ? 'selected' : ''}>상품명 중문</option>

					<option value="simnCd" ${goodsVO == null ? '' : goodsVO.schKey eq 'simnCd' ? 'selected' : ''}>Simn</option>
					<option value="binCd" ${goodsVO == null ? '' : goodsVO.schKey eq 'binCd' ? 'selected' : ''}>Bin</option>

					<option value="goodscd" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodscd' ? 'selected' : ''}>상품코드</option>
					<option value="goodsno" ${goodsVO == null ? '' : goodsVO.schKey eq 'goodsno' ? 'selected' : ''}>상품번호</option>
				</select>
				<input type="text" name="schWord" value="${goodsVO.schWord}">
			</div>
			<div class="th w-120">판매상태</div>
			<div>
				<select name="schOpen">
					<option value="">전체</option>
					<option value="1" ${goodsVO == null ? '' : goodsVO.schOpen eq '1' ? 'selected' : ''}>판매중</option>
					<option value="0" ${goodsVO == null ? '' : goodsVO.schOpen eq '0' ? 'selected' : ''}>전시대기</option>
					<option value="2" ${goodsVO == null ? '' : goodsVO.schOpen eq '2' ? 'selected' : ''}>일시중지</option>
					<option value="3" ${goodsVO == null ? '' : goodsVO.schOpen eq '3' ? 'selected' : ''}>영구중지</option>
				</select>
			</div>
			<div class="th w-120">재고</div>
			<div>
				<select name="schSoldOut">
					<option value="">전체</option>
					<option value="Y" ${goodsVO == null ? '' : goodsVO.schSoldOut eq 'Y' ? 'selected' : ''}>유</option>
					<option value="N" ${goodsVO == null ? '' : goodsVO.schSoldOut eq 'N' ? 'selected' : ''}>무</option>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="th w-120">특별구좌</div>
			<div>
				<select name="schSpec">
					<option value="">구좌선택</option>
					<option value="H" ${goodsVO == null ? '' : goodsVO.schSpec eq 'H' ? 'selected' : ''}>Hot100</option>
					<option value="V" ${goodsVO == null ? '' : goodsVO.schSpec eq 'V' ? 'selected' : ''}>VIP Shop</option>
				</select>
			</div>
			<div class="th w-120">관리상품</div>
			<div>
				<select name="schManageYn">
					<option value=""  ${empty goodsVO.schManageYn ? 'selected' : '' }>전체상품</option>
					<option value="Y" ${goodsVO.schManageYn eq 'Y' ? 'selected' : '' }>관리상품</option>
				</select>
			</div>
		</div>

	</div>


	<div class=button_top>
<%--		<a href="javascript:;" class="btn_search"><img src="/resources/shop/admin/img/btn_search2.gif" alt="" /></a>--%>
		<button type="submit" class="admin-btn-search2">검색</button>
	</div>
	<%-- 검색영역끝--%>

	<div style="padding-top:15px"></div>

	<!--
	<table width=100% cellpadding=0 cellspacing=0>
		<tr>
			<td class=pageInfo><font class=ver8>
				<c:set var="pages" value="${(goodsVO.rowCount*10) / (goodsVO.pageSize*10)} " />
				<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
				<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
				총 <b>${goodsVO.totalCnt}</b>개, 검색 <b>${goodsVO.rowCount}</b>개, <b>${goodsVO.pageNo}</b> of <b>${var3}</b> Pages
			</td>
			<td align=right>
	
				<table cellpadding=0 cellspacing=0 border=0>
					<tr>
						<td valign=bottom>
							<span>판매금액순 <a href="javascript:sort('b.price desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('b.price asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
							<span style="padding-left: 10px;">브랜드순 <a href="javascript:sort('h.brandnm desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('h.brandnm asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
							<span style="padding-left: 10px;">재고순 <a href="javascript:sort('d.stock desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('d.stock asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
							<span style="padding-left: 10px;">리테일가순 <a href="javascript:sort('b.consumer desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('b.consumer asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
							<span style="padding-left: 10px;">판매가순 <a href="javascript:sort('b.price desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('b.price asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
							<span style="padding-left: 10px;">즉시할인율순 <a href="javascript:sort('b.priceRate desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('b.priceRate asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
							<span style="padding-left: 10px;">공헌이익율순 <a href="javascript:sort('b.margin desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('b.margin asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
							<span style="padding-left: 10px;">등록일순 <a href="javascript:sort('a.regdt desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a> <a href="javascript:sort('a.regdt asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a></span>
						</td>
						<td style="padding-left:20px">
							<img src="/resources/shop/admin/img/sname_output.gif" align="absmiddle">
							<select name=pageSize onchange='javascript:$("[name=frmList]").attr("action", "/shop/admin/goods/list").submit();'>
								<option value="10" ${stringUtil:selected(goodsVO.pageSize, "10")} >10개 출력
								<option value="20" ${stringUtil:selected(goodsVO.pageSize, "20")} >20개 출력
								<option value="40" ${stringUtil:selected(goodsVO.pageSize, "40")} >40개 출력
								<option value="60" ${stringUtil:selected(goodsVO.pageSize, "60")} >60개 출력
								<option value="100" ${stringUtil:selected(goodsVO.pageSize, "100")} >100개 출력
							</select>
						</td>
						<td>
							<a href="javaScript:excelDownload();"><img class="button_top" src="/resources/shop/admin/img/btn_excel_download.gif" alt="엑셀다운로드" border="0" align="absmiddle" style="cursor:hand; height: 26px; margin-bottom: 6px; margin-left: 5px; border: none;"></a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	-->

	<div class="sub-cont-wrap" style="display: flex; flex-direction: row; flex-wrap: nowrap; justify-content: space-between; align-items: center; margin-bottom: 7px;">
		<!-- 페이지 정보 -->
		<div>
			<c:set var="pages" value="${(goodsVO.rowCount*10) / (goodsVO.pageSize*10)} " />
			<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
			<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
			총 <b>${goodsVO.totalCnt}</b>개, 검색 <b>${goodsVO.rowCount}</b>개, <b>${goodsVO.pageNo}</b> of <b>${var3}</b> Pages
		</div>

		<!-- 정렬 및 유틸 -->
		<div class="inp-tbl goods-list-panel-util">
			<span>판매금액순
				<a href="javascript:sort('b.price desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('b.price asc')" class="admin-list-down-off"></a>
			</span>
			<span>브랜드순
				<a href="javascript:sort('h.brandnm desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('h.brandnm asc')" class="admin-list-down-off"></a>
			</span>
			<span>재고순
				<a href="javascript:sort('d.stock desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('d.stock asc')" class="admin-list-down-off"></a>
			</span>
			<span>리테일가순
				<a href="javascript:sort('b.consumer desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('b.consumer asc')" class="admin-list-down-off"></a>
			</span>
			<span>판매가순
				<a href="javascript:sort('b.price desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('b.price asc')" class="admin-list-down-off"></a>
			</span>
			<span>즉시할인율순
				<a href="javascript:sort('b.priceRate desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('b.priceRate asc')" class="admin-list-down-off"></a>
			</span>
			<span>공헌이익율순
				<a href="javascript:sort('b.margin desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('b.margin asc')" class="admin-list-down-off"></a>
			</span>
			<span>등록일순
				<a href="javascript:sort('a.regdt desc')" class="admin-list-up-off"></a>
				<a href="javascript:sort('a.regdt asc')" class="admin-list-down-off"></a>
			</span>

			<span>출력수
				<select name="pageSize" onchange='javascript:$("[name=frmList]").attr("action", "/shop/admin/goods/list").submit();'>
					<option value="10" ${stringUtil:selected(goodsVO.pageSize, "10")} >10개 출력
					<option value="20" ${stringUtil:selected(goodsVO.pageSize, "20")} >20개 출력
					<option value="40" ${stringUtil:selected(goodsVO.pageSize, "40")} >40개 출력
					<option value="60" ${stringUtil:selected(goodsVO.pageSize, "60")} >60개 출력
					<option value="100" ${stringUtil:selected(goodsVO.pageSize, "100")} >100개 출력
				</select>
			</span>
			<span>
				<a href="javascript:excelDownload();" class="admin-btn-excel-download">엑셀 다운로드</a>
			</span>
		</div>
	</div>
</form>

<form id="statusModifyFrm" method="post" action="indb2">
	<input type="hidden" id="statVal" 	name="statVal"		value="" /> 
	<input type="hidden" id="mode" 		name="mode"			value="" /> 
	
	<div style="width: 100%; overflow: auto;">
		<table id="goodsList" style="width: 160%; font-size: unset;" class="stripe-tbl inp-tbl" style="">
<%--			<tr><td class=rnd colspan=13></td></tr>--%>
			<colgroup>
				<col style="width: 60px">
				<col style="width: 40px">
			</colgroup>
			<thead>
			<tr>
				<th><a href="javascript:chkBox(document.getElementsByName('chk[]'),'rev')" class="white">선택</a></th>
				<th>번호</th>
				<th>이미지</th>
				<th>상품번호</th>
				<th>SCM번호</th>
				<th>카테고리명</th>
				<th>부틱명</th>
				<th>상품명</th>
				<th>Bin</th>
				<th>Simn</th>
				<th>시즌</th>
				<th>EU</th>
				<th>등록일</th>
				<th>재고</th>
				<th>리테일가</th>
				<th>즉시할인가</th>
				<th>즉시할인율</th>
				<th>바잉원가(정책)</th>
				<th>바잉원가(DATA)</th>
				<th>공헌이익율</th>
				<th colspan="2">최저가여부</th>
				<th>판매여부</th>
				<th>적립금여부</th>
				<th>승인여부</th>
				<th>관리상품체크</th>
				<th>관리상품</th>
				<th>Hot100</th>
				<th>Vip Shop</th>
				<th>복사</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			</thead>
			<tbody>
<%--			<col width=40 span=2 align=center>--%>
<%--				<tr><td height=4 colspan=17></td></tr>--%>
				<c:forEach items="${goodsVO.goodsList}" var="goodsList" varStatus="status">
				<tr height="25" >
					<td class="noline" align="center">
						<input type="checkbox" name="chk[]" value="${goodsList.goodsno}" data-sellerno="${goodsList.reqsno}" data-sellercd="${goodsList.sellerCd}" data-aprlstat="${goodsList.approvalStatus}" />
					</td>
<%--					<td align="center"><font class=ver8 color=616161>${(goodsVO.rowCount - status.index) - ( goodsVO.rowStart )}</font></td>--%>
					<td>${(goodsVO.rowCount - status.index) - ( goodsVO.rowStart )}</td>
					<td style="border:1px #e9e9e9 solid;width:50px" align="center">
						<c:set var="imgSpl" value='${fn:split(goodsList.imgs,"|")}'/>
						<a href="register?mode=modify&goodsno=${goodsList.goodsno}&pageNo=${goodsVO.pageNo}&pageSize=${goodsVO.pageSize}&sort=${goodsVO.sort}&schRegty=${goodsVO.schRegty}&schRegdt=${goodsVO.schRegdt[0]}&schRegdt=${goodsVO.schRegdt[1]}&schSellerNm=${goodsVO.schSellerNm}&schSellerCd=${goodsVO.schSellerCd}&cate=${goodsVO.cate[0]}&cate=${goodsVO.cate[1]}&cate=${goodsVO.cate[2]}&cate=${goodsVO.cate[3]}&schBrand=${goodsVO.schBrand}&schSeasonNm=${goodsVO.schSeasonNm}&schKey=${goodsVO.schKey}&schWord=${goodsVO.schWord}&schOpen=${goodsVO.schOpen}&schSoldOut=${goodsVO.schSoldOut}&schSpec=${goodsVO.schSpec}&schManageYn=${goodsVO.schManageYn}">
							<img src='${imgSpl[0]}' width="40" onerror="onErrorImg(this, 'noimg_100.gif', 'kr');">
						</a>
					</td>
					<td>${goodsList.goodsno}</td>
					<td>${goodsList.goodscd}</td>
					<td>${goodsList.categoryNm}</td>
					<td>${goodsList.sellerCd}</td>
					<td style="text-align: left; line-height: 1.5em;">
						<!--
						<div>${goodsList.goodsnm}</div>
						<div style="padding-top:3px">${goodsList.goodsnmKR}</div>
						<div style="padding-top:3px">${goodsList.goodsnmCN}</div>
						-->
						<span style="color: #303030">
							${goodsList.goodsnm}<br>
							${goodsList.goodsnmKR}<br>
							${goodsList.goodsnmCN}
						</span>
					</td>
					<td>${goodsList.binCd}</td><!-- Bin -->
					<td>${goodsList.simnCd}</td><!-- Simn -->
					<td>${goodsList.seasonNm}</td><!-- 시즌 -->
					<td>${goodsList.euYn eq 'Y' ? 'EU' : '非EU'}</td><!-- EU -->
					<td><span style="color: #444">${goodsList.regdt}</span></td><!-- 등록일 -->
					<td>${stringUtil:getMoneyFormatString(goodsList.stock)}</td><!-- 재고 -->
					<td>${goodsList.consumer}</td><!-- 리테일가 -->
					<td><fmt:formatNumber value="${goodsList.price}" pattern="0.00"/></td><!-- 즉시할인가 -->
					<td>${goodsList.priceRate}%</td><!-- 즉시할인율 -->
					<td>${goodsList.supply}</td><!-- 바잉원가(정책) -->
					<td>${goodsList.supply2}</td><!-- 바잉원가(DATA) -->
					<td>${goodsList.margin}%</td><!-- 공헌이익율 -->
					<td>${goodsList.lowestPriceYn eq 'Y'  ? '최저가' : '차저가'}</td><!-- 최저가여부 -->
					<td>${goodsList.lowestPrice}</td><!-- 최저가여부 : 가격 -->
					<td>
						<c:choose>
						    <c:when test="${goodsList.open eq '3'}">영구중지</c:when>
						    <c:when test="${goodsList.open eq '2'}">일시중지</c:when>
						    <c:when test="${goodsList.open eq '1'}">판매중</c:when>
						    <c:otherwise>전시대기</c:otherwise>
						</c:choose>
					</td>
					<td>${goodsList.useemoney eq '1' ? 'Y' : 'N'}</td>
					<td> <!-- 승인여부 -->
						<c:if test="${'1' eq goodsList.approvalStatus || '2' eq goodsList.approvalStatus || '3' eq goodsList.approvalStatus || '4' eq goodsList.approvalStatus}">
							${codeUtil:getCodeName('AS', goodsList.approvalStatus)} <!--  1번 승인요청     2번 승인     3번 반려      4번 승인취소 -->
							<br />
						</c:if>  
						<c:if test="${!empty goodsList.reqAprlCd && !empty goodsList.reqAprlStat}" > 
							(${codeUtil:getCodeName('AC', goodsList.reqAprlCd)} ${codeUtil:getCodeName('AS', goodsList.reqAprlStat)})  <!-- 판매자의 상태를 나타내주는 상태 -->
							<br />
						</c:if> 
						<c:if test="${!empty goodsList.reqsno  && goodsList.reqAprlCd =='2' && goodsList.reqAprlStat == '1'}">
							<a href="javascript:popup('${ctx}/shop/admin/goods/register?mode=approvalRevise&goodsno=${goodsList.goodsno}&reqsno=${goodsList.reqsno}',900,600);">
								<font class="ver811" color="0074BA">
									<b>[수정신청 상품 정보]</b>
								</font>
							</a>
						</c:if>
					</td>
					<td class="noline">
						<input type="checkbox" class="chkManage" value="${goodsList.goodsno}" ${goodsList.manageYn eq 'Y' ? 'checked' : ''}/>
					</td>
					<td class="noline">
						${goodsList.manageYn eq 'Y' ? '관리상품' : '일반상품' }
					</td>
					<td class="noline">
						<input type="checkbox" class="chkSpec" data-type="hot_yn" value="${goodsList.goodsno}" ${goodsList.hotYn eq 'Y' ? 'checked' : ''}/>
					</td>
					<td class="noline">
						<input type="checkbox" class="chkSpec" data-type="vip_yn" value="${goodsList.goodsno}" ${goodsList.vipYn eq 'Y' ? 'checked' : ''}/>
					</td>
					<td>
<%--						<a href="indb2?mode=copyGoods&goodsno=${goodsList.goodsno}" onclick="return confirm('동일한 상품을 하나 더 자동등록합니다')"><img src="/resources/shop/admin/img/i_copy.gif"></a>--%>
						<a href="indb2?mode=copyGoods&goodsno=${goodsList.goodsno}" onclick="return confirm('동일한 상품을 하나 더 자동등록합니다')" class="admin-i-copy">복사</a>
					</td>
					<td>
<!--						<a href="register?mode=modify&goodsno=${goodsList.goodsno}&pageNo=${goodsVO.pageNo}&pageSize=${goodsVO.pageSize}&sort=${goodsVO.sort}&schRegty=${goodsVO.schRegty}&schRegdt=${goodsVO.schRegdt[0]}&schRegdt=${goodsVO.schRegdt[1]}&schSellerNm=${goodsVO.schSellerNm}&schSellerCd=${goodsVO.schSellerCd}&cate=${goodsVO.cate[0]}&cate=${goodsVO.cate[1]}&cate=${goodsVO.cate[2]}&cate=${goodsVO.cate[3]}&schBrand=${goodsVO.schBrand}&schSeasonNm=${goodsVO.schSeasonNm}&schKey=${goodsVO.schKey}&schWord=${goodsVO.schWord}&schOpen=${goodsVO.schOpen}&schSoldOut=${goodsVO.schSoldOut}&schSpec=${goodsVO.schSpec}&schManageYn=${goodsVO.schManageYn}"><img src="/resources/shop/admin/img/i_edit.gif"></a>-->
						<a href="register?mode=modify&goodsno=${goodsList.goodsno}&pageNo=${goodsVO.pageNo}&pageSize=${goodsVO.pageSize}&sort=${goodsVO.sort}&schRegty=${goodsVO.schRegty}&schRegdt=${goodsVO.schRegdt[0]}&schRegdt=${goodsVO.schRegdt[1]}&schSellerNm=${goodsVO.schSellerNm}&schSellerCd=${goodsVO.schSellerCd}&cate=${goodsVO.cate[0]}&cate=${goodsVO.cate[1]}&cate=${goodsVO.cate[2]}&cate=${goodsVO.cate[3]}&schBrand=${goodsVO.schBrand}&schSeasonNm=${goodsVO.schSeasonNm}&schKey=${goodsVO.schKey}&schWord=${goodsVO.schWord}&schOpen=${goodsVO.schOpen}&schSoldOut=${goodsVO.schSoldOut}&schSpec=${goodsVO.schSpec}&schManageYn=${goodsVO.schManageYn}" class="admin-i-edit">수정</a>
					</td>
					<td>
<%--						<a href="indb2?mode=delete&goodsno=${goodsList.goodsno}" onclick="return confirm('정말로 삭제하시겠습니까?\n\n업로드된 상품이미지는 자동삭제됩니다.\n단, 상세정보에 쓰인 이미지는 다른 곳에서도 사용하고 있을 수 있으므로 자동 삭제되지 않습니다.')"><img src="/resources/shop/admin/img/i_del.gif"></a>--%>
						<a href="indb2?mode=delete&goodsno=${goodsList.goodsno}" onclick="return confirm('정말로 삭제하시겠습니까?\n\n업로드된 상품이미지는 자동삭제됩니다.\n단, 상세정보에 쓰인 이미지는 다른 곳에서도 사용하고 있을 수 있으므로 자동 삭제되지 않습니다.')" class="admin-i-del">삭제</a>
					</td>
				</tr>
				</c:forEach>
				</tbody>
<%--				<tr><td height=4></td></tr>--%>
<%--				<tr><td colspan=17 class=rndline></td></tr>--%>
		</table>
	</div>
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${goodsVO.pageNo}" rowCount="${goodsVO.rowCount}" pageSize="${goodsVO.pageSize}"  pageGroupSize="${goodsVO.pageGroupSize}" />
	<div class="button">
		<!--
		<input type="button" class="btn btn-primary" value="게시" onclick="chk_status_modify('1', 'open_modify');" />
		<input type="button" class="btn btn-primary" value="게시취소" onclick="chk_status_modify('0', 'open_modify');"/>
		-->

		<input type="button" class="admin-btn" value="게시" onclick="chk_status_modify('1', 'open_modify');" />
		<input type="button" class="admin-btn" value="게시취소" onclick="chk_status_modify('0', 'open_modify');"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<%-- 
		<input type="button" class="btn btn-primary" value="승인" onclick="chk_status_modify('2', 'approvalstatus_modify');"/>
		<input type="button" class="btn btn-primary" value="반려" onclick="chk_status_modify('3', 'approvalstatus_modify');" />
		--%>
	<!-- <input type="image" src="/resources/shop/admin/img/btn_modify.gif" onclick="return isChked(document.getElementsByName('ordno'),'정말로 수정 하시겠습니까?')">
	<a href="javascript:history.back()"><img src="/resources/shop/admin/img/btn_cancel.gif"></a> -->
	</div>
</form>

<div id=MSG01>
	<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">현재까지 등록한 상품의 전체상품리스트입니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">복사버튼을 누르면 자동으로 똑같은 상품이 생성됩니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">상품정보를 수정하려면 수정버튼을 누르세요.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">상품이미지를 클릭하시면 해당 상품의 상세페이지</a>를 새창으로 보실 수 있습니다.</td></tr>
	</table>
</div>
<script>cssRound('MSG01')</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
	</td>
</tr>
</table>

<%@ include file="../common/bottom.jsp" %>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>