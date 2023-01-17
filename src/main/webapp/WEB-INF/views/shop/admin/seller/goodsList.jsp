<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script language="javascript">

	function eSort(obj,fld){
		var form = document.frmList;
		if (obj.innerText.charAt(1)=="▲") fld += " desc";
		form.sort.value = fld;
		form.submit();
	}
	
	function sort(sort){
		var fm = document.frmList;
		fm.sort.value = sort;
		fm.submit();
	}
	
	function sort_chk(sort){
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
	function chk_status_modify(statVal, mode){
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
			});
			
			$("#statVal").val(statVal);
			$("#mode").val(mode);
			
			statusModifyFrm.submit();
		}
	}
	
</script>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
<form name="frmList" action="${ctx}/shop/admin/seller/goodsList">
	<input type="hidden" id="pageNo" name="pageNo" value="1"/>
	<input type="hidden" name="sort" value="">
	
	<div class="title title_top">전체상품리스트<span>등록하신 상품을 한눈에 살펴보시고 편리하게 수정하실 수 있습니다</span></div>
	<%-- 검색영역시작 --%>
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>검색어</td>
			<td>
				<select name=skey>
					<option value="goodsnm"  ${goodsVO==null ? '' : goodsVO.skey eq 'goodsnm' ? 'selected' : '' }>상품명</option>
					<option value="goodscd"  ${goodsVO==null ? '' : goodsVO.skey eq 'goodscd' ? 'selected' : '' }>상품코드</option>
					<option value="keyword"  ${goodsVO==null ? '' : goodsVO.skey eq 'keyword' ? 'selected' : '' }>유사검색어</option>
				</select>
				<input type="text" NAME="sword" value="${goodsVO.sword}" class="line" style="height:22px">
			</td>
		</tr>
		<tr>
			<td>판매사명</td>
			<td>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${goodsVO.schSellerNm}" class="line" style="height:22px" />
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
				<input type="hidden" name="schSellerCd" id="schSellerCd" value="${goodsVO.schSellerCd}" />
			</td>
		</tr>
		<tr>
			<td>상품가격</td>
			<td><font class=small color=444444>
				₩<input type=text name=price value="${goodsVO.price_0 }" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" class="rline"> -
				₩<input type=text name=price value="${goodsVO.price_1 }" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" class="rline">
			</font></td>
		</tr>
		<tr>
			<td>상품등록일</td>
			<td>
				<input type=text name=sregdt value='${goodsVO.sregdt[0]}' onclick="calendar(event)" class=line onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8"> -
				<input type=text name=sregdt value='${goodsVO.sregdt[1]}' onclick="calendar(event)" class=line onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
				<a href="javascript:setDate('sregdt',${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
				<a href="javascript:setDate('sregdt',${dateUtil:getDateFrom('yyyyMMdd', -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
				<a href="javascript:setDate('sregdt',${dateUtil:getDateFrom('yyyyMMdd', -15)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
				<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
				<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
				<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
			</td>
		</tr>
		<tr>
			<td>게시여부</td>
			<td>
				<input type=radio name=open value="" checked>전체
				<input type="radio" name="open" value="1"  ${goodsVO==null ? '' : goodsVO.open eq '1' ? 'checked' : '' }/> 게시된 상품
				<input type="radio" name="open" value="0" ${goodsVO==null ? '' : goodsVO.open eq '0' ? 'checked' : '' }/> 미게시된 상품
			</td>
		</tr>
		<tr>
			<td>승인여부</td>
			<td class="noline">
				<input type="radio" name="schApprovalStatus" value="" ${goodsVO.schApprovalStatus eq '' ? 'checked' : '' } > 전체
				<input type="radio" name="schApprovalStatus" value="1"  ${goodsVO.schApprovalStatus eq '1' ? 'checked' : '' } /> ${codeUtil:getCodeName('AS', '1')}
				<input type="radio" name="schApprovalStatus" value="2" ${goodsVO.schApprovalStatus eq '2' ? 'checked' : '' } /> ${codeUtil:getCodeName('AS', '2')}
				<input type="radio" name="schApprovalStatus" value="3" ${goodsVO.schApprovalStatus eq '3' ? 'checked' : '' } /> ${codeUtil:getCodeName('AS', '3')}
				<input type="radio" name="schApprovalStatus" value="4" ${goodsVO.schApprovalStatus eq '4' ? 'checked' : '' } /> ${codeUtil:getCodeName('AS', '4')}
			</td>
		</tr>
	</table>
	<div class=button_top><input type=image src="/resources/shop/admin/img/btn_search2.gif"></div>
	<%-- 검색영역끝--%>

	<div style="padding-top:15px"></div>
	
	<table width=100% cellpadding=0 cellspacing=0>
		<tr>
			<td class=pageInfo><font class=ver8>
				<c:set var="pages" value="${(goodsVO.rowCount*10) / (goodsVO.pageSize*10)} " />
				<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
				<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
				총 <b>${goodsVO.rowCount }</b>개, <b>${goodsVO.pageNo }</b> of <b>${var3 }</b> Pages
			</td>
			<td align=right>
	
				<table cellpadding=0 cellspacing=0 border=0>
					<tr>
						<td valign=bottom>
							<img src="/resources/shop/admin/img/sname_date.gif">
							<a href="javascript:sort('a.regdt desc')"><img name=sort_regdt_desc src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('a.regdt asc')"><img name=sort_regdt src="/resources/shop/admin/img/list_down_off.gif"></a>
							<img src="/resources/shop/admin/img/sname_dot.gif"><img src="/resources/shop/admin/img/sname_product.gif">
							<a href="javascript:sort('a.goodsnm desc') "><img name=sort_goodsnm_desc src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('a.goodsnm asc' )"><img name=sort_goodsnm src="/resources/shop/admin/img/list_down_off.gif"></a>
							<img src="/resources/shop/admin/img/sname_dot.gif"><img src="/resources/shop/admin/img/sname_price.gif">
							<a href="javascript:sort('b.price desc')"><img name=sort_price_desc src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('b.price asc')"><img name=sort_price src="/resources/shop/admin/img/list_down_off.gif"></a>
						</td>
						<td style="padding-left:20px">
							<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
							<select name=pageSize onchange="this.form.submit()">
								<option value="10" ${stringUtil:selected(goodsVO.pageSize, "10")} >10개 출력
								<option value="20" ${stringUtil:selected(goodsVO.pageSize, "20")} >20개 출력
								<option value="40" ${stringUtil:selected(goodsVO.pageSize, "40")} >40개 출력
								<option value="60" ${stringUtil:selected(goodsVO.pageSize, "60")} >60개 출력
								<option value="100" ${stringUtil:selected(goodsVO.pageSize, "100")} >100개 출력
							</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>

<form id="statusModifyFrm" method="post" action="goodsIndb2">
	<input type="hidden" id="statVal" 	name="statVal"		value="" /> 
	<input type="hidden" id="mode" 		name="mode"			value="" /> 
	
	<table id="goodsList" width=100% cellpadding=0 cellspacing=0 border=0>
		<tr><td class=rnd colspan=13></td></tr>
		<tr class=rndbg>
			<th>선택</th>
			<th width=60>번호</th>
			<th></th>
			<th width=10></th>
			<th>상품명</th>
			<th>등록자 아이디</th>
			<th>판매사</th>
			<th>등록일</th>
			<th>가격</th>
			<th>재고</th>
			<th>게시여부</th>
			<th>승인여부</th>
			<th>복사</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<col width=40 span=2 align=center>
			<tr><td height=4 colspan=13></td></tr>
			<c:forEach items="${goodsVO.goodsList}" var="goodsList" varStatus="status">
			<tr height="25" >
				<td class="noline" align="center">
					<input type="checkbox" name="chk[]" value="${goodsList.goodsno}" data-sellercd="${goodsList.sellerCd}" data-aprlstat="${goodsList.approvalStatus}" />
				</td>
				<td align="center"><font class=ver8 color=616161>${(goodsVO.rowCount - status.index) - ( goodsVO.rowStart ) }</font></td>				
				<td style="border:1px #e9e9e9 solid;width:50px" align="center">
					<c:set var="imgSpl" value='${fn:split(goodsList.imgs,"|")}'/>
					<a href="goodsRegister?mode=modify&goodsno=${goodsList.goodsno }" target="_blank">
						<img src='/resources/shop/data/upload/goods/${imgSpl[0] }' width="40" onerror="onErrorImg(this, 'noimg_100.gif', 'kr');">
					</a>
				</td>
				<td></td>
				<td>
					<font color=303030>${goodsList.goodsnm}</font>
				</td>
				<td align=center><font class=ver81 color=444444>${goodsList.mid }</td>
				<td align=center><font class=ver81 color=444444>${goodsList.sellerNm }</td>
				<td align=center><font class=ver81 color=444444>${goodsList.regdt }</td>
				<td align=center>
					<font color=4B4B4B><font class=ver81 color=444444><b>${stringUtil:getMoneyFormatString(goodsList.price)}</b></font>
					<div style="padding-top:2px"></div>
					<img src="/resources/shop/admin/img/good_icon_point.gif" align=absmiddle><font class=ver8>${stringUtil:getMoneyFormatString(goodsList.reserve)}</font>
				</td>
				<td align=center><font class=ver81 color=444444>${stringUtil:getMoneyFormatString(goodsList.stock)}</td>
				<td align=center><img src="/resources/shop/admin/img/icn_${goodsList.open}.gif"></td>
				<td align=center>
					<c:if test="${!empty goodsList.sellerCd}">
						<c:if test="${'1' eq goodsList.approvalStatus || '2' eq goodsList.approvalStatus || '3' eq goodsList.approvalStatus || '4' eq goodsList.approvalStatus}">
							${codeUtil:getCodeName('AS', goodsList.approvalStatus)}
						</c:if>
					</c:if>
				</td>
				<td align=center>
					<a href="goodsIndb2?mode=copyGoods&goodsno=${goodsList.goodsno }" onclick="return confirm('동일한 상품을 하나 더 자동등록합니다')"><img src="/resources/shop/admin/img/i_copy.gif"></a>
				</td>
				<td align=center>
					<a href="goodsRegister?mode=modify&goodsno=${goodsList.goodsno }"><img src="/resources/shop/admin/img/i_edit.gif"></a>
				</td>
				<td align=center>
					<a href="goodsIndb2?mode=delete&goodsno=${goodsList.goodsno }" onclick="return confirm('정말로 삭제하시겠습니까?\n\n업로드된 상품이미지는 자동삭제됩니다.\n단, 상세정보에 쓰인 이미지는 다른 곳에서도 사용하고 있을 수 있으므로 자동 삭제되지 않습니다. \n\'디자인관리 > webFTP이미지관리 > data > editor\'에서 이미지체크 후 삭제관리하세요.')"><img src="/resources/shop/admin/img/i_del.gif"></a>
				</td>
			</tr>
			</c:forEach>
			<tr><td height=4></td></tr>
			<tr><td colspan=15 class=rndline></td></tr>
	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${goodsVO.pageNo}" rowCount="${goodsVO.rowCount}" pageSize="${goodsVO.pageSize}"  pageGroupSize="${goodsVO.pageGroupSize}" />
	
<div class="button">
		<input type="button" class="btn btn-primary" value="게시" onclick="chk_status_modify('1', 'open_modify');" />
		<input type="button" class="btn btn-primary" value="게시취소" onclick="chk_status_modify('0', 'open_modify');"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class="btn btn-primary" value="승인" onclick="chk_status_modify('2', 'approvalstatus_modify');"/>
		<input type="button" class="btn btn-primary" value="반려" onclick="chk_status_modify('3', 'approvalstatus_modify');" />
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

<script>
function goPage(page){
	$("#pageNo").val(page);
	document.frmList.submit();
}
</script>

	</td>
</tr>
</table>

<%@ include file="../common/bottom.jsp" %>
