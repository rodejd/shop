<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script language=javascript src="/resources/shop/admin/common.js"></script>
<script>
	function goMove(url){
		document.frmList.action = url;
		document.frmList.submit();
	}
	
	function goPage(page){
		document.frmList.pageNo.value = page;
		document.frmList.submit();
	}
	
	function goStop(idx){
		if (confirm("정말 중지하시겠습니까?")){
			document.frmList.action = 'indb?&mode=useyn&pmno=' + idx;
			document.frmList.submit();
		}else{
			return;
		}
	}
	
	function goDelete(idx){
		if (confirm("정말 삭제하시겠습니까?")){
			location.href = 'indb?&mode=delete&pmno=' + idx;
		}else{
			return;
		}
	}
	
	function setPeriod(sdt, edt){
		if ( null != sdt ){
			document.frmSrch.schSdt.value = sdt;
		} else {
			document.frmSrch.schSdt.value = "";
		}
		
		if ( null != edt ){
			document.frmSrch.schEdt.value = edt;
		} else {
			document.frmSrch.schEdt.value = "";
		}
	}
</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left:12px">
		
			<div class="title title_top">기획전리스트<%-- <span>현재 내 쇼핑몰의 전체회원을 파악하고 관리하실 수 있습니다</span> --%></div>
	
			<form id="frmSrch" name="frmSrch" method="post" action="list">
				<input type="hidden" name="pageNo" value="1"/>
	
				<table class="tb">
					<col class="cellC" />
					<col class="cellL" style="width:716" />
					<col class="cellC" />
					<col class="cellL" style="width:716" />
					<tr>
						<td>전시여부</td>
						<td>
							<select name="schUseYn">
								<option value="">전체</option>
								<option value="0" ${stringUtil:checked(promotionVO.schUseYn, '0')} >전시중</option>
								<option value="1" ${stringUtil:checked(promotionVO.schUseYn, '1')} >전시대기</option>
								<option value="2" ${stringUtil:checked(promotionVO.schUseYn, '2')} >전시중지</option>
							</select>
						</td>
						<td>노출스킨</td>
						<td>
							<select name="schSkin">
								<option value="">전체</option>
								${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), promotionVO.schSkin)}
							</select>
						</td>
					</tr>
					<tr>
						<td>제목검색</td>
						<td colspan="3">
							<input type="text" name="schWord" value="${promotionVO.schWord}" class="line" />
						</td>
					</tr>
					<tr>
						<td>전시기간</td>
						<td colspan="3">
							<input type="text" name="schSdt" value="${promotionVO.schSdt }" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/> ~
							<input type="text" name="schEdt" value="${promotionVO.schEdt }" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/>
						 	<a href="javascript:setPeriod(${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
							<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
							<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
							<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
							<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
							<a href="javascript:setPeriod()"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>  
						</td>
					</tr>
				</table>
				
				<div class="button_top">
					<input type="image" src="/resources/shop/admin/img/btn_search2.gif" />
				</div>
			</form>
		
			<div style="padding-top:15px"></div>
	
			<form id="frmList" name="frmList" method="post">
				<input type="hidden" id="schUseYn" name="schUseYn" value="${promotionVO.schUseYn}"/>
				<input type="hidden" id="schSkin" name="schSkin" value="${promotionVO.schSkin}"/>
				<input type="hidden" id="schWord" name="schWord" value="${promotionVO.schWord}"/>
				<input type="hidden" id="schSdt" name="schSdt" value="${promotionVO.schSdt}"/>
				<input type="hidden" id="schEdt" name="schEdt" value="${promotionVO.schEdt}"/>
				<input type="hidden" id="pageNo" name="pageNo" value="${pageNo != '' ? promotionVO.pageNo : '1'}"/>
			
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="pageInfo">
							<c:set var="pages" value="${(promotionVO.rowCount * 10) / (promotionVO.pageSize * 10)} " />
							<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
							<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
							총 <font class="ver8"><b>${promotionVO.totalCnt}</b>개, 검색 <b>${promotionVO.rowCount}</b>개, <b>${promotionVO.pageNo}</b> of ${var3} Pages</font>
						</td> 
						<td align="right">
							<table cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td valign="bottom">
									</td>
									<td style="padding-left:20px">
										<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
										<select name=pageSize onchange="this.form.submit()">
											<option value="10" ${stringUtil:selected(promotionVO.pageSize, "10")} >10개 출력</option>
											<option value="20" ${stringUtil:selected(promotionVO.pageSize, "20")} >20개 출력</option>
											<option value="40" ${stringUtil:selected(promotionVO.pageSize, "40")} >40개 출력</option>
											<option value="60" ${stringUtil:selected(promotionVO.pageSize, "60")} >60개 출력</option>
											<option value="100" ${stringUtil:selected(promotionVO.pageSize, "100")} >100개 출력</option>
										</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
		
				<table class="listTable">
					<tr>
						<th width="60">No.</th>
						<th width="70">노출스킨</th>
						<th width="150">위치</th>
						<th>기획전제목</th>
						<th width="400">기획전URL</th>
						<th width="180">기간</th>
						<th width="80">전시상태</th>
						<th width="120">등록일</th>
						<th width="80">전시관리</th>
						<th width="100">수정/삭제</th>
						<th width="140">상품</th>
					</tr>
					<c:forEach items="${promotionVO.promotionList}" var="list" varStatus="status">
						<tr class="trClass">
							<td>${(promotionVO.rowCount - status.index) - ((promotionVO.pageNo - 1) * 10)}</td>
							<td>${codeUtil:getCodeName("skin", list.skin)}</td>
							<td>${codeUtil:getCodeName("pmloccd", list.loccd)}</td>
							<td style="text-align: left;">${list.title}</td>
							<td style="text-align: left;"><a href="https://${list.skin}.ritzmall.com/shop/promotion/promotion?pmno=${list.pmno}" target="_blank">https://${list.skin}.ritzmall.com/shop/promotion/promotion?pmno=${list.pmno}</a></td>
							<td>${dateUtil:getDateFormat("yyyy-MM-dd", list.sdt)} ~ ${dateUtil:getDateFormat("yyyy-MM-dd", list.edt)}</td>
							<td>${list.status}</td>
							<td><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></td>
							<td>
								<c:choose>
									<c:when test="${list.status eq '전시중'}">
										<a href="javascript:void(0);" onclick="goStop('${list.pmno}');" style="background-color: #4472c4;border: 1px solid #355791;border-radius: 2px;color: #fff;padding: 2px 4px;">강제중지</a>
									</c:when>
									<c:when test="${list.status eq '전시중지' and list.useYn eq 'Y'}">
										자동중지
									</c:when>
									<c:when test="${list.status eq '전시중지' and list.useYn ne 'Y'}">
										강제중지
									</c:when>
								</c:choose>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="goMove('register?mode=modify&pmno=${list.pmno}');"><img src="/resources/shop/admin/img/i_edit.gif"></a>
								<a href="javascript:void(0);" onclick="goDelete('${list.pmno}');"><img src="/resources/shop/admin/img/i_del.gif"></a>
							</td>
							<td>
								${list.goodsCnt}
								<a href="javascript:void(0);" onclick="goMove('goods?pmno=${list.pmno}');"><img src="/resources/shop/admin/img/i_add.gif"></a>
							</td>
						</tr>
					</c:forEach>
				</table>
							
				<!-- 페이징  -->
				<tags:paginator currentPageNo="${promotionVO.pageNo}" rowCount="${promotionVO.rowCount}" pageSize="${promotionVO.pageSize}"  pageGroupSize="${promotionVO.pageGroupSize}" />
			
				<div class="button">
					<a href="javascript:void(0);" onclick="goMove('register?mode=register');"><img src="/resources/shop/admin/img/btn_register.gif"></a>
				</div>
			</form>	
			
			<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>