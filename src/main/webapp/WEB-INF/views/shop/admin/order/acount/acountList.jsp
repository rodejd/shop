<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../common/left.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language="javascript" src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	/*
	 * php script
	 */
	function iciSelect(obj) {
		var row = obj.parentNode.parentNode;
		row.style.background = (obj.checked) ? "#F9FFF0" : row
				.getAttribute("bg");
	}

	function chkBoxAll(El, mode) {
		if (!El || !El.length)
			return;
		for (var i = 0; i < El.length; i++) {
			if (El[i].disabled)
				continue;
			El[i].checked = (mode == "rev") ? !El[i].checked : mode;
			iciSelect(El[i]);
		}
	}
	
	function chkBox2(El,s,e,mode){
		if (!El || !El.length)
			return;
		for(var i=s;i<e;i++){
			if (El[i].disabled)
				continue;
			El[i].checked = (mode == "rev") ? !El[i].checked : mode;
			iciSelect(El[i]);
		}
	}
	
	function dnXls(mode){
		// 엑셀 다운로드의 검색 조건은 주문내역의 검색 조건값을 사용합니다.
		$('form[name=frmDnXls]').empty();
		var domTarget = $('#orderSearchForm').serializeArray();
		$(domTarget).each(function(i, obj){
			$('<input>', {
			    type	: 'hidden',
			    name	: obj.name,
			    value	: obj.value
			}).appendTo('form[name=frmDnXls]');
		});
		$('form[name=frmDnXls]').find('input[name="mode"]').val(mode);
		$('form[name=frmDnXls]').attr('action', ctx+"/shop/admin/excel/order_excel.doxls");
		$('form[name=frmDnXls]').submit();
	}

	function popLayerCallback() {
		// 팝업 내의 박스 라운드 처리 호출
		cssRound("MSGPOP01","#F7F7F7");
	}
	/*
	* DOWNLOAD 항목설정 팝업 저장처리
	* popup.orderxls.jsp 에서 사용
	*/
	function submitPopForm(){
		if ( isChked(document.getElementsByName('cfgChk')) ) {
			// 체크여부에 따름 value 설정 (y/n)
			$('input:checkbox[name="cfgChk"]').each(function(){
				if ($(this).prop('checked')) {
					$(this).val('y');
				} else {
					$(this).val('n');
				}
			});
			var setVals = $('input:checkbox[name="cfgChk"]').map(function(){
				return $(this).val();
			}); 
			$('#cfgChkVal').val(setVals.get().join('^'));
			
			ajaxCallJsonPost("/shop/admin/order/popup_indb.dojson", "cfgForm", function(result){
				if (result.RESULT) {
					alert("저장했습니다.");
					closeLayer();
				}
			});
			
		} else {
			return false;
		}
		
	}
	function orderdate(mode){
		var fm1 = document.search;
		
		fm1.mode.value = mode;		
		fm1.submit();
	}

	function goPage(page){
		$("#pageNo").val(Number(page));
		$('#orderSearchForm').submit();
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

	<div class="title title_top">
		정산세부내역리스트입니다. 
		<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=order&no=2',870,800)"><img src="/resources/shop/admin/img/btn_q.gif" border="0" hspace="2" align="absmiddle"></a>-->
	</div>
	
	<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
		<tbody>
			<tr>
				<td valign="top" style="padding-left:12px">
					<form name="frmList" action="list">
						<input type="hidden" id="pageNo" name="pageNo" value="1">
						<input type="hidden" name="sort" value="">
						
						<div class="title title_top">전체상품리스트<span>등록하신 상품을 한눈에 살펴보시고 편리하게 수정하실 수 있습니다</span> 
							<!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=product&no=2',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> -->
						</div>
<!-- 검색 영역 Start -->
						<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
							<colgroup>
								<col class="cellC">
								<col class="cellL">
							</colgroup>
							<tbody>
								<tr>
									<td>검색어</td>
									<td>
									<select name="skey">
										<option value="goodsnm">상품명</option>
										
										<option value="goodscd">상품코드</option>
										<option value="keyword">유사검색어</option>
									</select>
									<input type="text" name="sword" value="" class="line" style="height:22px">
									</td>
								</tr>
								<tr>
									<td>판매사명</td>
									<td>
										<input type="text" name="schSellerNm" id="schSellerNm" value="" class="line" style="height:22px">
										<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);">
										<input type="hidden" name="schSellerCd" id="schSellerCd" value="">
									</td>
								</tr>
								<tr>
									<td>상품가격</td>
									<td>
										<font class="small" color="444444">
											₩<input type="text" name="price" value="" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" class="rline"> -
											₩<input type="text" name="price" value="" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" class="rline">
										</font>
									</td>
								</tr>
								<tr>
									<td>상품등록일</td>
									<td>
									<input type="text" name="sregdt" value="" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8"> -
									<input type="text" name="sregdt" value="" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
									<a href="javascript:setDate('sregdt',20190822,20190822)"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle"></a>
									<a href="javascript:setDate('sregdt',20190815,20190822)"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle"></a>
									<a href="javascript:setDate('sregdt',20190807,20190822)"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle"></a>
									<a href="javascript:setDate('sregdt',20190722,20190822)"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle"></a>
									<a href="javascript:setDate('sregdt',20190622,20190822)"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle"></a>
									<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle"></a>
									</td>
								</tr>
								<tr>
									<td>게시여부</td>
									<td>
									<input type="radio" name="open" value="" checked="">전체
									<input type="radio" name="open" value="1"> 게시된 상품
									<input type="radio" name="open" value="0"> 미게시된 상품
									</td>
								</tr>
								<tr>
									<td>승인여부</td>
									<td class="noline">
									<input type="radio" name="schApprovalStatus" value="" checked=""> 전체
									<input type="radio" name="schApprovalStatus" value="1"> 승인요청
									<input type="radio" name="schApprovalStatus" value="2"> 승인
									<input type="radio" name="schApprovalStatus" value="3"> 반려
									<input type="radio" name="schApprovalStatus" value="4"> 승인취소
									</td>
								</tr>
							</tbody>
						</table>
						<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif"></div>
<!-- 검색 End -->	
					
						<div style="padding-top:15px"></div>
<!-- 표 상단 타이틀 -->
						<div class="title title_top">기간 내 합계</div>
<!-- 표 상단 타이틀 End -->
<!-- 표 상단 옵션 -->
						<table width="100%" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td class="pageInfo">
										
									</td>
									<td align="right">
										
									</td>
								</tr>
							</tbody>
						</table>
<!-- 표 상단 옵션 End -->
					</form>
		
					<form id="statusModifyFrm" method="post" action="indb2">
						<input type="hidden" id="statVal" name="statVal" value=""> 
						<input type="hidden" id="mode" name="mode" value=""> 
<!-- 표 -->
						<table id="goodsList" width="100%" cellpadding="0" cellspacing="0" border="0">
							<thead>
								<tr>
									<td class="rnd" colspan="13"></td>
								</tr>
								<tr class="rndbg">
									<th>총 상품 판매액</th>
									<th>총 수수료</th>
									<th>총 배송료</th>
									<th>총 지급 적립금</th>
									<th>총 쿠폰 할인액</th>
									<th>총 정산 금액</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td height="4" colspan="13"></td>
								</tr>
								<tr height="25">
									<td class="center">총 상품 판매액1</td>
									<td class="center">총 수수료1</td>
									<td class="center">총 배송료1</td>
									<td class="center">총 지급 적립금1</td>
									<td class="center">총 쿠폰 할인액1</td>
									<td class="center">총 정산 금액1</td>
								</tr>
								<tr height="25">
									<td class="center">총 상품 판매액2</td>
									<td class="center">총 수수료2</td>
									<td class="center">총 배송료2</td>
									<td class="center">총 지급 적립금2</td>
									<td class="center">총 쿠폰 할인액2</td>
									<td class="center">총 정산 금액2</td>
								</tr>
								<tr><td height="4"></td></tr>
								<tr><td colspan="15" class="rndline"></td></tr>
							</tbody>
						</table>
						<!-- 페이징 -->
						<div class="pageNavi">
							<span class="current">1</span>
							<a href="javascript:goPage(2);">2</a>
							<a href="javascript:goPage(3);">3</a>
							<a href="javascript:goPage(4);">4</a>
							<a href="javascript:goPage(5);">5</a>
							<a href="#" onclick="javascript:goPage(6);"> ▶ </a>
							<a href="#" onclick="javascript:goPage(15);"> ▷ </a>
						</div>
						<!--// 페이징 -->
					</form>
					
					
					<div style="padding-top:15px"></div>
<!-- 표 상단 타이틀 -->
					<div class="title title_top">정산기준 안내</div>
<!-- 표 상단 타이틀 End -->
<!-- 표 상단 옵션 -->
					<table width="100%" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<select name="" onchange="">
										<option value="10" selected="">구입결정일</option>
										<option value="20">구입결정일1</option>
										<option value="40">구입결정일2</option>
										<option value="60">구입결정일3</option>
										<option value="100">구입결정일4</option>
									</select>
								</td>
								<td align="right" class="pageInfo">
									<table cellpadding="0" cellspacing="0" border="0">
										<tbody>
											<tr>
												총 주문수 : 0건 현재 1/0 페이지
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
<!-- 표 상단 옵션 End -->
		
					<form id="statusModifyFrm" method="post" action="indb2">
						<input type="hidden" id="statVal" name="statVal" value=""> 
						<input type="hidden" id="mode" name="mode" value=""> 
<!-- 표 -->
						<table id="goodsList" width="100%" cellpadding="0" cellspacing="0" border="0">
							<thead>
								<tr>
									<td class="rnd" colspan="13"></td>
								</tr>
								<tr class="rndbg">
									<th>입점업체</th>
									<th>구입결정일/주문코드</th>
									<th>주문일자</th>
									<th>상품명</th>
									<th>수량</th>
									<th>판매금액</th>
									<th>수수료 ->금액</th>
									<th>적립금</th>
									<th>배송료</th>
									<th>쿠폰할인</th>
									<th>결제금액</th>
									<th>정산상태</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td height="4" colspan="13"></td>
								</tr>
								<tr height="25">
									<td class="center">입점업체1</td>
									<td class="center">구입결정일/주문코드1</td>
									<td class="center">주문일자1</td>
									<td class="center">상품명1</td>
									<td class="center">수량1</td>
									<td class="center">판매금액1</td>
									<td class="center">수수료 ->금액1</td>
									<td class="center">적립금1</td>
									<td class="center">배송료1</td>
									<td class="center">쿠폰할인1</td>
									<td class="center">결제금액1</td>
									<td class="center">정산상태1</td>
								</tr>
								<tr height="25">
									<td class="center">입점업체2</td>
									<td class="center">구입결정일/주문코드2</td>
									<td class="center">주문일자2</td>
									<td class="center">상품명2</td>
									<td class="center">수량2</td>
									<td class="center">판매금액2</td>
									<td class="center">수수료 ->금액2</td>
									<td class="center">적립금2</td>
									<td class="center">배송료2</td>
									<td class="center">쿠폰할인2</td>
									<td class="center">결제금액2</td>
									<td class="center">정산상태2</td>
								</tr>
								<tr><td height="4"></td></tr>
								<tr><td colspan="15" class="rndline"></td></tr>
							</tbody>
						</table>
						<!-- 페이징 -->
						<div class="pageNavi">
							<span class="current">1</span>
							<a href="javascript:goPage(2);">2</a>
							<a href="javascript:goPage(3);">3</a>
							<a href="javascript:goPage(4);">4</a>
							<a href="javascript:goPage(5);">5</a>
							<a href="#" onclick="javascript:goPage(6);"> ▶ </a>
							<a href="#" onclick="javascript:goPage(15);"> ▷ </a>
						</div>
						<!--// 페이징 -->
					</form>
				</td>
			</tr>
		</tbody>
	</table>



<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>