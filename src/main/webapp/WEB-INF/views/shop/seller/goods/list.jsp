<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	$(function(){
		var i =0;
		var obj = "";
		var obj = null;
		var schSort = "";
		
		schSort = $("#schSort").val();
		
		if ("" != schSort){
			$("#" + schSort).attr("src", $("#" + schSort).attr("src").replace("off", "on"));
		} 
		
		cssRound('MSG01');
		
		$("#searchBtn").on("click", function(){
			// 검색어
			$("#schKey").val($("#tmpKey").val());
			$("#schWord").val($("#tmpWord").val());
			
			// 상품가격
			$("#schMinPrice").val($("#tmpMinPrice").val());
			$("#schMaxPrice").val($("#tmpMaxPrice").val());
			
			// 등록일
			$("#schStRegdt").val($('#form01 input:text[name="tmpRegdt" ]')[0].value);
			$("#schEdRegdt").val($('#form01 input:text[name="tmpRegdt" ]')[1].value);
			
			// 승인상태
			$("#schAprlStat").val($('#form01 input:radio[name="tmpAprlstat"]:checked').val());
			
			document.form01.submit();
		});
	});
	
	function goPage(page){
		$("#pageNo").val(page);
		document.form01.submit();
	}
	
	function sort(sort){
		var fm = document.form01;
		fm.schSort.value = sort;
		fm.submit();
	}
	
	// 삭제 신청 
	function reqApprovalDel(goodsno){
		if(confirm("삭제 신청 하시겠습니까?")){
			ajaxCallJson("/shop/seller/goods/deleteApprovalReq.dojson"
					, {goodsno : goodsno}
					, function(result){
						alert("삭제 신청이 완료되었습니다.");
						window.location.href='/shop/seller/goods/deleteApprovalList';
					});
		}
	}
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

	<c:choose>
		<c:when test="${'I' == sellerGoodsFM.listViewCd}">
			<form name="form01" id="form01" method="get" action="${ctx }/shop/seller/goods/registerApprovalList">
		</c:when>
		<c:when test="${'U' == sellerGoodsFM.listViewCd}">
			<form name="form01" id="form01" method="get" action="${ctx }/shop/seller/goods/modifyApprovalList">
		</c:when>
		<c:when test="${'D' == sellerGoodsFM.listViewCd}">
			<form name="form01" id="form01" method="get" action="${ctx }/shop/seller/goods/deleteApprovalList">
		</c:when>
		<c:otherwise>
			<form name="form01" id="form01" method="get" action="${ctx }/shop/seller/goods/list">
		</c:otherwise>
	</c:choose>
	<input type="hidden" id="pageNo" name="pageNo" value="1"/>
	<input type="hidden" id="schSort" name="schSort" value="${sellerGoodsFM.schSort}" />
	<input type="hidden" id="schKey" name="schKey" value="${sellerGoodsFM.schKey}" />
	<input type="hidden" id="schWord" name="schWord" value="${sellerGoodsFM.schWord}" />
	<input type="hidden" id="schMinPrice" name="schMinPrice" value="${sellerGoodsFM.schMinPrice}" />
	<input type="hidden" id="schMaxPrice" name="schMaxPrice" value="${sellerGoodsFM.schMaxPrice}" />
	<input type="hidden" id="schStRegdt" name="schStRegdt" value="${sellerGoodsFM.schStRegdt}" />
	<input type="hidden" id="schEdRegdt" name="schEdRegdt" value="${sellerGoodsFM.schEdRegdt}" />
	<input type="hidden" id="schAprlStat" name="schAprlStat" value="${sellerGoodsFM.schAprlStat}" />
	
	<div class="title title_top">
		<c:choose>
			<c:when test="${'I' == sellerGoodsFM.listViewCd}">
					등록신청대기목록
				</c:when>
			<c:when test="${'U' == sellerGoodsFM.listViewCd}">
					수정신청대기목록
				</c:when>
			<c:when test="${'D' == sellerGoodsFM.listViewCd}">
					삭제신청대기목록
				</c:when>
			<c:otherwise>
					전체상품리스트
				</c:otherwise>
		</c:choose> 
		

		<!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=product&no=2',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> -->
	</div>
	
	<%-- 검색영역시작 --%>
	<table class=tb>
		<col class=cellC><col class=cellL>
		<%-- <tr>
			<td>분류선택</td>
			<td><script>new categoryBox('cate[]', 4, '<%= strCategory %>');</script></td>
		</tr> --%>
		<tr>
			<td>검색어</td>
			<td>
				<select id="tmpKey">
					<option value="goodsnm"  ${sellerGoodsFM.schKey eq 'goodsnm' ? 'selected="selected"' : '' }>상품명</option>
					<option value="goodscd"  ${sellerGoodsFM.schKey eq 'goodscd' ? 'selected="selected"' : '' }>상품코드</option>
					<option value="keyword"  ${sellerGoodsFM.schKey eq 'keyword' ? 'selected="selected"' : '' }>유사검색어</option>
				</select>
				<input type="text" id="tmpWord" value="${sellerGoodsFM.schWord}" class="line" style="height:22px">
			</td>
		</tr>
		<tr>
			<td>상품가격</td>
			<td><font class=small color=444444>
			<input type=text id="tmpMinPrice" value="${sellerGoodsFM.schMinPrice }" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" class="rline"> 원 -
			<input type=text id="tmpMaxPrice" value="${sellerGoodsFM.schMaxPrice }" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" class="rline"> 원
			</font>
			</td>
		</tr>
		<tr>
			<td>상품등록일</td>
			<td>
				<input type=text name="tmpRegdt" value='${sellerGoodsFM.schStRegdt}' onclick="calendar(event);" class=line onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8"> -
				<input type=text name="tmpRegdt" value='${sellerGoodsFM.schEdRegdt}' onclick="calendar(event);" class=line onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
				<a href="javascript:setDate('tmpRegdt',${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
				<a href="javascript:setDate('tmpRegdt',${dateUtil:getDateFrom('yyyyMMdd', -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
				<a href="javascript:setDate('tmpRegdt',${dateUtil:getDateFrom('yyyyMMdd', -15)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
				<a href="javascript:setDate('tmpRegdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
				<a href="javascript:setDate('tmpRegdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
				<a href="javascript:setDate('tmpRegdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
			</td>
		</tr>
		<c:if test="${empty sellerGoodsFM.listViewCd}">
			<tr>
				<td>상품승인상태</td>
				<td>
					<input type=radio name="tmpAprlstat" value="" 	${empty sellerGoodsFM.schAprlStat ? 'checked="checked"' : '' } >전체
					<input type="radio" name="tmpAprlstat" value="1" ${sellerGoodsFM.schAprlStat eq '1' ? 'checked="checked"' : '' } /> ${codeUtil:getCodeName('AS', '1')}
					<input type="radio" name="tmpAprlstat" value="2" ${sellerGoodsFM.schAprlStat eq '2' ? 'checked="checked"' : '' } /> ${codeUtil:getCodeName('AS', '2')}
					<input type="radio" name="tmpAprlstat" value="4" ${sellerGoodsFM.schAprlStat eq '4' ? 'checked="checked"' : '' } /> ${codeUtil:getCodeName('AS', '4')}
				</td>
			</tr>
		</c:if>
	</table>
	
	<div class=button_top><img src="/resources/shop/admin/img/btn_search2.gif" id="searchBtn" style='cursor:hand;' /></div>
	<%-- 검색영역끝--%>

	<div style="padding-top:15px"></div>
	
	<table width=100% cellpadding=0 cellspacing=0>
		<tr>
			<td class=pageInfo><font class=ver8>
				<c:set var="pages" value="${(sellerGoodsFM.rowCount * 10) / (sellerGoodsFM.pageSize * 10)} " />
				<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
				<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
				총 <b>${sellerGoodsFM.totalCnt}</b>개, 검색 <b>${sellerGoodsFM.rowCount}</b>개, <b>${sellerGoodsFM.pageNo }</b> of <b>${var3 }</b> Pages
			</td>
			<td align=right>
	
				<table cellpadding=0 cellspacing=0 border=0>
					<tr>
						<td valign=bottom>
							<img src="/resources/shop/admin/img/sname_date.gif">
							<a href="javascript:sort('regdtDESC')"><img id="regdtDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('regdtASC')"><img id="regdtASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
							
							<img src="/resources/shop/admin/img/sname_dot.gif"><img src="/resources/shop/admin/img/sname_product.gif">
							<a href="javascript:sort('goodsnmDESC') "><img id="goodsnmDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('goodsnmASC' )"><img id="goodsnmASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
							
							<img src="/resources/shop/admin/img/sname_dot.gif"><img src="/resources/shop/admin/img/sname_price.gif">
							<a href="javascript:sort('priceDESC')"><img id="priceDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('priceASC')"><img id="priceASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
						</td>
						<td style="padding-left:20px">
							<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
								<select name=pageSize onchange="this.form.submit()">
									<option value="10" ${stringUtil:selected(sellerGoodsFM.pageSize, "10")} >10개 출력</option>
									<option value="20" ${stringUtil:selected(sellerGoodsFM.pageSize, "20")} >20개 출력</option>
									<option value="40" ${stringUtil:selected(sellerGoodsFM.pageSize, "40")} >40개 출력</option>
									<option value="60" ${stringUtil:selected(sellerGoodsFM.pageSize, "60")} >60개 출력</option>
									<option value="100" ${stringUtil:selected(sellerGoodsFM.pageSize, "100")} >100개 출력</option>
								</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>


	<table id="goodsList" width=100% cellpadding=0 cellspacing=0 border=0>
		<tr><td class="rnd" colspan="12"></td></tr>
		<tr class="rndbg">
			<th width="60">번호</th>
			<th></th>
			<th>상품명</th>
			<th>등록자 아이디</th>
			<th>등록일</th>
			<th>가격</th>
			<th>재고</th>
			<th width="10%">승인상태</th>
			<c:if test="${'I' == sellerGoodsFM.listViewCd}">
				<th>복사</th>
			</c:if>
			<c:if test="${'I' == sellerGoodsFM.listViewCd || empty sellerGoodsFM.listViewCd}">
				<th>수정</th>
				<th>삭제</th>
			</c:if>
		</tr>
			<tr><td colspan="12" class="rnd"></td></tr>
			<tr><td height="4" colspan="12"></td></tr>
			
			<c:choose>
				<c:when test="${!empty sellerGoodsFM.sellerGoodsList}">
					<c:forEach items="${sellerGoodsFM.sellerGoodsList}" var="sellerGoodsList" varStatus="status">
						<tr height="25" >
							<td align="center">
								<font class=ver8 color=616161>${(sellerGoodsFM.rowCount - status.index) - ( sellerGoodsFM.rowStart ) }</font>
							</td>				
							<td style="border:1px #e9e9e9 solid;width:50px" align="center">
								<c:set var="imgSpl" value='${fn:split(sellerGoodsList.imgs,"|")}'/>
								<c:choose>
									<c:when test="${0 < sellerGoodsList.goodsno}">
										<a href="javascript:popup('${ctx }/shop/seller/goods/register?popView=Y&amp;goodsno=${sellerGoodsList.goodsno}',900,600);">
											<img src='${codeUtil:getConfigClassValue('FRONT_GOODS_IMG_PATH')}${imgSpl[0]}' width="40" onerror="onErrorImg(this, 'noimg_100.gif', 'kr');">
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:popup('${ctx }/shop/seller/goods/register?popView=Y&amp;reqsno=${sellerGoodsList.reqsno}',900,600);">
											<img src='${codeUtil:getConfigClassValue('FRONT_GOODS_IMG_PATH')}${imgSpl[0]}' width="40" onerror="onErrorImg(this, 'noimg_100.gif', 'kr');">
										</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<font color=303030>${sellerGoodsList.goodsnm}</font>
								<c:if test="${'U' == sellerGoodsFM.listViewCd}">
									<a href="javascript:popup('${ctx }/shop/seller/goods/register?popView=Y&amp;reqsno=${sellerGoodsList.reqsno}',900,600);">
										<font class="ver811" color="0074BA">
											<b>[수정신청 상품 정보]</b>
										</font>
									</a>
								</c:if>
							</td>
							<td align=center><font class=ver81 color=444444>${sellerGoodsList.mid }</td>
							<td align=center><font class=ver81 color=444444>${dateUtil:getDateFormat("yyyy-MM-dd", sellerGoodsList.regdt)}</td>
							<td align=center>
								<font color=4B4B4B><font class=ver81 color=444444><b>${stringUtil:getMoneyFormatString(sellerGoodsList.price)}</b></font>
							</td>
							<td align=center><font class=ver81 color=444444>${stringUtil:getMoneyFormatString(sellerGoodsList.stock)}</td>
							<td align=center>
								${codeUtil:getCodeName('AS', sellerGoodsList.approvalStatus)}<br />
								<c:if test="${'I' != sellerGoodsFM.listViewCd }" >
									(${codeUtil:getCodeName('AC', sellerGoodsList.reqAprlCd)} ${codeUtil:getCodeName('AS', sellerGoodsList.reqAprlStat)})
								</c:if>
							</td>
							<c:if test="${'I' == sellerGoodsFM.listViewCd}">
								<td align=center>
									<a href="${ctx }/shop/seller/goods/sellerGoodsCopy?reqsno=${sellerGoodsList.reqsno }" onclick="return confirm('동일한 상품을 하나 더 자동등록합니다');"><img src="/resources/shop/admin/img/i_copy.gif"></a>
								</td>
							</c:if>
							<c:if test="${'I' == sellerGoodsFM.listViewCd || empty sellerGoodsFM.listViewCd}">
								<td align=center>
									<c:choose>
										<c:when test="${'I' == sellerGoodsFM.listViewCd}">
											<c:choose>
												<c:when test="${sellerGoodsList.approvalStatus != '99' }">
													<span title="승인상태가 임시 상태가 아닌 경우 수정할 수 없습니다.">×</span>
												</c:when>
												<c:otherwise>
													<a href="${ctx }/shop/seller/goods/register?reqsno=${sellerGoodsList.reqsno }"><img src="/resources/shop/admin/img/i_edit.gif"></a>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${sellerGoodsList.approvalStatus == '1' || sellerGoodsList.reqAprlStat == '1' }">
													<span title="승인요청 중인 상품은 수정신청 할 수 없습니다.">×</span>
												</c:when>
												<c:otherwise>
													<a href="${ctx }/shop/seller/goods/register?goodsno=${sellerGoodsList.goodsno }"><img src="/resources/shop/admin/img/i_edit.gif"></a>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
								<td align=center>
									<c:choose>
										<c:when test="${'I' == sellerGoodsFM.listViewCd}">
											<c:choose>
												<c:when test="${sellerGoodsList.approvalStatus != '99' }">
													<span title="승인요청인 경우 삭제할 수 없습니다.">×</span>
												</c:when>
												<c:otherwise>
													<a href="${ctx }/shop/seller/goods/sellerGoodsDel?reqsno=${sellerGoodsList.reqsno}" 
														onclick="return confirm('정말로 삭제하시겠습니까?')"><img src="/resources/shop/admin/img/i_del.gif"></a>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${sellerGoodsList.approvalStatus == '1' || sellerGoodsList.reqAprlStat == '1' }">
													<span title="승인요청 중인 상품은 삭제 신청 할 수 없습니다.">×</span>
												</c:when>
												<c:otherwise>
													<a href="#none" onclick="reqApprovalDel('${sellerGoodsList.goodsno}')"><img src="/resources/shop/admin/img/i_del.gif"></a>
												</c:otherwise>
											</c:choose>
													
										</c:otherwise>
									</c:choose>
								</td>
							</c:if>
						</tr>
						<tr>
							<td height="4" colspan="12">
						</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td align="center" colspan="12" style="padding-bottom:9;padding-top:9"><font class="ver81">검색된 상품이 없습니다.</font></td></tr>
				</c:otherwise>
			</c:choose>
			<tr><td height="4" colspan="11"></td></tr>
			<tr><td colspan="11" class="rndline"></td></tr>
	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${sellerGoodsFM.pageNo}" rowCount="${sellerGoodsFM.rowCount}" pageSize="${sellerGoodsFM.pageSize}"  pageGroupSize="${sellerGoodsFM.pageGroupSize}" />
	
	<div id=MSG01>
		<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
			<c:if test="${empty sellerGoodsFM.listViewCd}">
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">현재까지 등록한 상품의 전체상품리스트입니다.</td></tr>
			</c:if>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">복사버튼을 누르면 자동으로 똑같은 상품이 생성됩니다.</td></tr>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">상품정보를 수정하려면 수정버튼을 누르세요.</td></tr>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">상품이미지를 클릭하시면 해당 상품의 상세페이지</a>를 새창으로 보실 수 있습니다.</td></tr>
		</table>
	</div>
</form>


