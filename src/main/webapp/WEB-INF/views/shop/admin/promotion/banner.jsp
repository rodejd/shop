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
	
	function sort(sort){
		document.frmList.schSort.value = sort;
		document.frmList.submit();
	}
	
	function goDelete(idx, img, imgMobile){
		if (confirm("정말 삭제하시겠습니까?")){
			location.href = 'indb?&mode=delete&sno='+idx +'&oldImg='+img;
		}else{
			return;
		}
	}
</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
	
		<div class="title title_top">배너리스트<%-- <span>현재 내 쇼핑몰의 전체회원을 파악하고 관리하실 수 있습니다</span> --%></div>

		<form id="frmSrch" name="frmSrch" method="post" action="list">
			<input type="hidden" name="pageNo" value="1"/>

			<table class="tb">
				<col class="cellC" />
				<col class="cellL" style="width:716" />
				<col class="cellC" />
				<col class="cellL" style="width:716" />
				<tr>
					<td>노출스킨</td>
					<td>
						<select name="schSkin">
							<option value="">전체</option>
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), bannerVO.schSkin)}
						</select>
					</td>
					<td>위치</td>
					<td>
						<select name="schLoccd">
							<option value="">전체</option>
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('bnloccd')), bannerVO.schLoccd)}
						</select>
					</td>
				</tr>
				<tr>
					<td>배너제목</td>
					<td>
						<input type="text" name="schWord" value="${bannerVO.schWord}" class="line" />
					</td>
					<td>사용여부</td>
					<td>
						<input type="radio" name="schUsed" value="" ${stringUtil:checked(bannerVO.schUsed, '')} />전체
						<input type="radio" name="schUsed" value="Y" ${stringUtil:checked(bannerVO.schUsed, 'Y')} />사용
						<input type="radio" name="schUsed" value="N" ${stringUtil:checked(bannerVO.schUsed, 'N')}/>중지
					</td>
				</tr>
			</table>
			
			<div class="button_top">
				<input type="image" src="/resources/shop/admin/img/btn_search2.gif" />
			</div>
		</form>
	
		<div style="padding-top:15px"></div>

		<form id="frmList" name="frmList" method="post">
			<input type="hidden" id="schSkin" name="schSkin" value="${bannerVO.schSkin}"/>
			<input type="hidden" id="schLoccd" name="schLoccd" value="${bannerVO.schLoccd}"/>
			<input type="hidden" id="schKey" name="schKey" value="${bannerVO.schKey}"/>
			<input type="hidden" id="schWord" name="schWord" value="${bannerVO.schWord}"/>
			<input type="hidden" id="schUsed" name="schUsed" value="${bannerVO.schUsed}"/>
			<input type="hidden" id="schSort" name="schSort" value="${bannerVO.schSort}"/>
			<input type="hidden" id="pageNo" name="pageNo" value="${pageNo != '' ? bannerVO.pageNo : '1'}"/>
		
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td class="pageInfo">
						<c:set var="pages" value="${(bannerVO.rowCount * 10) / (bannerVO.pageSize * 10)} " />
						<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
						<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
						총 <font class="ver8"><b>${bannerVO.totalCnt}</b>개, 검색 <b>${bannerVO.rowCount}</b>개, <b>${bannerVO.pageNo}</b> of ${var3} Pages</font>
					</td> 
					<td align="right">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td valign="bottom">
									등록일
									<a href="javascript:sort('snoDESC')"><img id="snoDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
									<a href="javascript:sort('snoASC')"><img id="snoASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
									우선순위
									<a href="javascript:sort('sdateDESC') "><img id="sdateDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
									<a href="javascript:sort('sdateASC' )"><img id="sdateASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
								</td>
								<td style="padding-left:20px">
									<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
									<select name=pageSize onchange="this.form.submit()">
										<option value="10" ${stringUtil:selected(bannerVO.pageSize, "10")} >10개 출력</option>
										<option value="20" ${stringUtil:selected(bannerVO.pageSize, "20")} >20개 출력</option>
										<option value="40" ${stringUtil:selected(bannerVO.pageSize, "40")} >40개 출력</option>
										<option value="60" ${stringUtil:selected(bannerVO.pageSize, "60")} >60개 출력</option>
										<option value="100" ${stringUtil:selected(bannerVO.pageSize, "100")} >100개 출력</option>
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
					<th width="100" colspan="2">배너이미지</th>
					<th>배너제목</th>
					<th width="100">타겟</th>
					<th width="100">우선순위</th>
					<th width="120">등록일</th>
					<th width="100">사용여부</th>
					<th width="140">수정/삭제</th>
				</tr>
				<c:forEach items="${bannerVO.bannerList}" var="list" varStatus="status">
					<tr class="trClass">
						<td>${(bannerVO.rowCount - status.index) - ((bannerVO.pageNo - 1) * 10)}</td>
						<td>${codeUtil:getCodeName("skin", list.skin)}</td>
						<td>${codeUtil:getCodeName("bnloccd", list.loccd)}</td>
						<td align="center">
							<a href="${list.img}" target="_blank"><img src='${list.img}' width="40" onerror="onErrorImg(this, 'noimg_100.gif', 'kr');"></a>
						</td>
						<td align="center">
							<a href="${list.imgMobile}" target="_blank"><img src='${list.imgMobile}' width="40" onerror="onErrorImg(this, 'noimg_100.gif', 'kr');"></a>
						</td>
						<td style="text-align: left;">${list.title}</td>
						<td>${list.target eq '_self' ? '현재창':'새창'}</td>
						<td>${list.sort}</td>
						<td><fmt:formatDate value="${list.regdt}" pattern="yyyy.MM.dd"/></td>
						<td>${list.used eq 'Y' ? '사용':'중지'} </td>
						<td>
							<a href="javascript:void(0);" onclick="goMove('register?mode=modify&sno=${list.sno}');"><img src="/resources/shop/admin/img/i_edit.gif"></a>
							<a href="javascript:void(0);" onclick="goDelete('${list.sno}','${list.img}', '${list.imgMobile}');"><img src="/resources/shop/admin/img/i_del.gif"></a>
						</td>
					</tr>
				</c:forEach>
			</table>
						
			<!-- 페이징  -->
			<tags:paginator currentPageNo="${bannerVO.pageNo}" rowCount="${bannerVO.rowCount}" pageSize="${bannerVO.pageSize}"  pageGroupSize="${bannerVO.pageGroupSize}" />
		
			<div class="button">
				<a href="javascript:void(0);" onclick="goMove('register?mode=register');"><img src="/resources/shop/admin/img/btn_register.gif"></a>
			</div>
		</form>	
		
		<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>