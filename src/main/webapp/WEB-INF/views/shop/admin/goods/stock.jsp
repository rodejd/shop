<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<c:set var="rtList" value="${stockVO.goodsList}"></c:set>
<c:set var="i" value="0"></c:set>
<c:set var="j" value="0"></c:set>
<c:set var="tmp" value="0"></c:set>
<c:set var="disabled" value=""></c:set>
<c:set var="dataLink" value=""></c:set>
<c:set var="strCategory" value="${stockVO.strCategory !=null ? stockVO.strCategory: '' }"></c:set>
<c:set var="b" value="false"></c:set>
<c:set var="sort" value="${stockVO.sort}"></c:set>
<c:set var="arrTmp" value=""></c:set>
<c:set var="vnum" value="${stockVO.vnum}"></c:set>
<c:set var="cate" value="${stockVO.cate}"></c:set>



<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">


	/*
	 * php script
	 */
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	
	/*
	 * jQuery ready
	 */
	
	$(function(){
		cssRound('MSG01');
	});
	
	var selOption = null;
	
	/* 2017-08-30 추가 - 페이징 유지 */
	function makeSearchParam(){
		$('#search').val($('#searchform').serialize());
	}
	
	function modify(){
		var fm = document.getElementById("form1");
		fm.submit();
	}

	function goPage(page){
		$("#pageNo").val(page);
		document.searchform.submit();
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
 <form id="searchform" name="searchform" action="stock">
 		<input type=hidden id=pageNo name="pageNo" value="1"/>
		<input type="hidden" class="cate1"value="${cate[0]}"/>
		<input type="hidden" class="cate2"value="${cate[1]}"/>
		<input type="hidden" class="cate3"value="${cate[2]}"/>
		<input type="hidden" class="cate4"value="${cate[3]}"/>
	<div class=title style="margin-top:0">가격/적립금/재고수정<span>상품의 가격 및 적립금, 재고를 수동으로 일괄수정할 수 있습니다</span> 
		<%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=4',870,800) --><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>%>
	</div>
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>판매사명</td>
			<td>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${stockVO.schSellerNm}" class="line" style="height:22px" />
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
			</td>
		</tr>
		<tr>
			<td>분류선택</td>
			<td>
				
				<script >new categoryBox('cate', 4, '${strCategory}');</script>  <!-- /StaticContent/shop/admin/common.js에 존재 -->
			</td>
		</tr>
		<tr>
			<td>상품명</td>
			<td><input type=text name=schGoodsNm class=line value="${stockVO.schGoodsNm !=null?stockVO.schGoodsNm:''}" style="width:200px"></td>  <!-- 재귀전송값 -->
		</tr>
		<tr>
			<td>정렬</td>
			<td class=noline>
				<c:choose>
					<c:when test="${not(sort eq 'b.stock')}">
						<input type=radio name=sort value="a.goodsno desc" checked> 최근등록순
					</c:when>
					<c:otherwise>
						<input type=radio name=sort value="a.goodsno desc" > 최근등록순
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sort eq 'b.stock' }">
						<input type=radio name=sort value="b.stock" checked> 잔여재고순
					</c:when>
					<c:otherwise>
						<input type=radio name=sort value="b.stock" > 잔여재고순
					</c:otherwise>
					
				</c:choose>
				
			</td>
		</tr>
	</table>
<!-- 		<div class=button_top><img src="../img/btn_search2.gif" id="btn_search" onclick="goSearch();"></div> -->
	<div class=button_top><input type=image src="/resources/shop/admin/img/btn_search2.gif" ></div>
</form> 
		
<form id="form1" name="form1" method=post action="stock/indb" >
	<input type=hidden name=mode value="stock">
	<input type="hidden" name="search" id="search" value="" />
 	
	<div class=pageInfo>총 <b>${stockVO.totalCnt}</b>개, 검색 <b>${stockVO.rowCount}</b>개, <b> ${stockVO.pageNo} </b> of <fmt:formatNumber value="${(stockVO.rowCount/stockVO.pageSize)+(1-((stockVO.rowCount/stockVO.pageSize)%1))%1}" pattern="#"/> Pages</div>
		
	<table width=100% cellpadding=2 cellspacing=0 border=0>
		<tr>
			<td class=rnd colspan=8></td>
		</tr>
		<tr class=rndbg align=center>
			<th width=45><font class=small1><b>번호</b></font></th>
			<th><font class=small1><b>상품명</b></font></th>
			<!-- <th><font class=small1><b>옵션1</b></font></th>
			<th><font class=small1><b>옵션2</b></font></th> -->
			<th width=100><font class=small1><b>판매자</b></font></th>
			<th width=80><font class=small1><b>정가</b></font></th>
			<th width=80><font class=small1><b>판매가</b></font></th>
			<th width=80><font class=small1><b>매입가</b></font></th>
			<th width=40><font class=small1><b>재고</b></font></th>
		</tr>
		<c:set var ="i" value="0"></c:set>
		<c:set var ="j" value="0"></c:set>
		
		
		<c:set var ="isHaveList" value="${rtList !=null and i < fn:length(rtList)? true:false }"></c:set>
		<c:choose>
			<c:when test="${isHaveList}">
			<%-- 	<c:set var="rtListLen" value="${rtList.length }"></c:set> --%>
				<fmt:parseNumber var="tmp" value="${tmp}"></fmt:parseNumber>
				<c:forEach items="${rtList}" var="list"  varStatus="status">
					<%-- <c:choose>
						<c:when test="${list.goodsno != tmp}">
							<c:set var="b" value="true"></c:set>
							<c:set var="tmp" value="${list.goodsno}"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="b" value="false"></c:set>			
						</c:otherwise>
					</c:choose>  --%>
			
					<c:set var="arrTmp1" value=""></c:set> 
					<c:set var="arrTmp2" value=""></c:set>
					<c:set var="arrTmp3" value=""></c:set>
					
					<c:set var ="dataLink" value="${sort eq 'b.stock'? '1' : '' }"></c:set>
					<c:set var="disabled " value="${(arrTmp1 eq list.goodsno )and (arrTmp2 eq list.opt1) ? 'disabled' : ''}"></c:set>
					<c:set var ="arrTmp1" value = "${list.goodsno}"></c:set>
					<c:set var ="arrTmp2"  value= "${list.opt1}"></c:set> 
					
					<c:if test="${not(dataLink eq '1') and not(arrTmp3 eq list.goodsnm)}">
						<c:set var="dataLink" value="1"></c:set>
						 <c:set var="arrTmp3" value="${list.goodsnm}"></c:set>
					</c:if>
					<c:if test="${not(disabled eq 'disabled') }">
						<input type='hidden' name='goodsno' value='${arrTmp1}'>
						<input type='hidden' name='opt1' value='${arrTmp2}'>
						<input type='hidden' name='modeKey' value='${status.index}'>
					</c:if> 
					 <input type="hidden" name="sno" value="${list.sno}"/>
					<tr>
						<td align=center><font class=ver71 color=616161>${ vnum[status.index] }</font></td> 
						<%-- 
							<c:choose>
							<c:when test="${dataLink eq '1' }">
							
							<c:choose>
							<c:when test="${list.category eq '001'}">
								<td><a href="/shop/admin/goods/recipe_register.jsp?mode=modify&goodsno=${list.goodsno}"><font class=small1 color=0074BA>${list.goodsnm !=null? list.goodsnm : ''}</a></td>
							</c:when>
							<c:otherwise>
								<td><a href="/shop/admin/goods/register.jsp?mode=modify&goodsno=${list.goodsno}"><font class=small1 color=0074BA>${list.goodsnm !=null? list.goodsnm:''}</a></td>
							</c:otherwise>
							</c:choose> 
							</c:when>
							<c:otherwise>
								<td width=250><font class=small1>${list.goodsnm !=null ? list.goodsnm : ''}</font></td>
							</c:otherwise>
							</c:choose>
						--%>
			
						<td><a href="${ctx}/shop/admin/goods/register?mode=modify&goodsno=${list.goodsno }"><font class=small1 color=0074BA>${list.goodsnm !=null ? list.goodsnm : ''}</font></a></td>
						<%-- <td align=center><font class=small color=555555>${list.opt1}</font></td>
						<td align=center><font class=small color=555555>${list.opt2}</font></td> --%>
						<td width=80 align=center>${list.sellerNm}</td>
						<td width=80><input type=text name=consumer value="${list.consumer}" style="text-align:right;width:80px" ${disabled} class=rline onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength='9'></td>
						<td width=80><input type=text name=price value="${list.price}" style="text-align:right;width:80px" ${disabled} class=rline onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength='9'></td>
						<td width=80><input type=text name=supply value="${list.supply}" style="text-align:right;width:80px" ${disabled} class=rline onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength='9'></td>
						<td width=40><input type=text name=stock value="${list.stock}" style="text-align:right;width:60px" class=rline onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength='7'></td>
					</tr>
					<tr><td colspan=10 class=rndline></td></tr>
				</c:forEach>
			</c:when>
		<c:otherwise>
			<tr>
				<td colspan=10 align=center>조회내역이 없습니다.</td>
			</tr>	
			<tr><td colspan=10 class=rndline></td></tr>
		</c:otherwise>
		</c:choose>

	</table>
	<tags:paginator currentPageNo="${stockVO.pageNo}" rowCount="${stockVO.rowCount}" pageSize="${stockVO.pageSize}"  pageGroupSize="${stockVO.pageGroupSize}" />
	
	<c:choose>
	<c:when test="${isHaveList}">
	<!--  리스트가 있을 경우는 저장과 목록 버튼 활성화 -->
		<div class=button>
		<input type=image src="/resources/shop/admin/img/btn_save.gif" onclick="makeSearchParam();">
		<!-- <a href='#'onclick="javascript:"><img src='../img/btn_list.gif'></a> -->
		</div>
	</c:when>
	<c:otherwise>
	<!--  리스트가 없을 경우는 목록 버튼만 활성화 -->
		<!-- <div class=button>
		<a href='../goods/stock'><img src='../img/btn_list.gif'></a>
		</div> -->
	</c:otherwise>
	</c:choose>
</form>

<div id=MSG01>
	<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">상품의 가격 및 재고를 수정하시려면 숫자를 변경후 일괄수정 버튼을 누르시면 됩니다.</td>
		</tr>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">각 상품명을 클릭하면 상품정보를 수정하실 수 있습니다.</td>
		</tr>
	</table>
</div>



<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
	
<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>

