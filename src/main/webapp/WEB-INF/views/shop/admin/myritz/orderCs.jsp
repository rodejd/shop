<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 상단 include
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
		
	});

	var setPeriod = function(dt1, dt2){
		if ( null != dt1 ){
			$('[name=regdt1]').val(dt1);
		}else{
			$('[name=regdt1]').val('');
		}
		if ( null != dt2 ){
			$('[name=regdt2]').val(dt2);
		}else{
			$('[name=regdt2]').val('');
		}
	};
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
				주문취소리스트 [조회] <span> 주문취소접수/주문취소완료, 교환접수/재주문, 반품접수/반품완료, 환불접수/환불완료 주문건을 조회합니다. </span><!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=order&no=3',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle" hspace="2"></a> -->
			</div>
			
			<form class="js_csForm" method="post">
				<input type="hidden" name="mode" value="${csVO.mode}">
				<input type="hidden" class="js_hiddenSkey" value="${csVO.skey }">
				<input type="hidden" class="js_hiddenType" value="${csVO.typeStr }">	
				<input type="hidden" class="js_pageNo" name="pageNo" value="1"/>
					
				<table class="tb">
					<col class="cellC"><col class="cellL">
					<tr>
						<td><font class="small1">키워드검색</font></td>
						<td>
							<select class="js_selectSkey" name="skey">
								<option value="all"> 통합검색
								<option value="a.ordno"> 주문번호
								<option value="nameOrder"> 주문자명
								<option value="bankSender"> 입금자명
								<option value="m_id"> 아이디	
							</select>
							<input type="text" name="sword" value="${csVO.sword }" class="line">
						</td>
					</tr>
					<tr>
						<td><font class="small1">주문상태</font></td>
						<td class="noline">
					
							<table>
								<tr>
								<!-- 배열로 잘 들어가는지 확인 -->
									<td><input class="js_type" type="checkbox" name="type" value="1"><font class="small1" color="5C5C5C">취소완료</font></td>
									<td><input class="js_type" type="checkbox" name="type" value="2"><font class="small1" color="5C5C5C">환불접수</font></td>
									<td><input class="js_type" type="checkbox" name="type" value="3"><font class="small1" color="5C5C5C">환불완료</font></td>
									<td><input class="js_type" type="checkbox" name="type" value="4"><font class="small1" color="5C5C5C">반품접수</font></td>
									<td><input class="js_type" type="checkbox" name="type" value="6"><font class="small1" color="5C5C5C">반품완료</font></td>
									<td><input class="js_type" type="checkbox" name="type" value="7"><font class="small1" color="5C5C5C">교환완료</font></td>
								</tr>
							</table>
					
						</td>
					</tr>
					<tr>
						<td>판매사명</td>
						<td>
							<input type="text" name="schMyritzNm" id="schMyritzNm" value="${csVO.schMyritzNm}" class="line" style="height:22px" />
							<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/myritzSearchPopup', 600, 500);" />
							<input type="hidden" name="schMyritzCd" id="schMyritzCd" value="${csVO.schMyritzCd}" />
						</td>
					</tr>
					<tr>
						<td><font class="small1">주문일</font></td>
						<td>
						
						<input type="text" name="regdt1" value="${csVO.regdt1 }" onclick="calendar(event)" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" size="12" class="line"> -
						<input type="text" name="regdt2" value="${csVO.regdt2 }" onclick="calendar(event)" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" size="12" class="line">
						<a href="javascript:setPeriod(${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle"></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle"></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle"></a>
						<a href="javascript:setPeriod()"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle"></a>
						
						</td>
					</tr>
					<tr>
						<td><font class="small1">결제방법</font></td>
						<td class="noline">
							<font class="small1" color="5C5C5C">	
								<input type="radio" name="settlekind" value=""  ${stringUtil:checked(csVO.settlekind, '')}/>전체
								<input type="radio" name="settlekind" value="a" ${stringUtil:checked(csVO.settlekind, 'a')}/>무통장
								<input type="radio" name="settlekind" value="c" ${stringUtil:checked(csVO.settlekind, 'c')}/>카드
							</font>
						</td>
					</tr>
				</table>
				<div class="button_top">
					<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
				</div>
			</form>
			
			<%-- 검색영역 끝 --%>
			
			<div style="padding-top:15"></div>

			<table width="100%" cellpadding="2" cellspacing="0">
				<tr><td class="rnd" colspan="15"></td></tr>
				<tr class="rndbg">
					<!--<th>선택</th>-->
					<th width="25"><font class="small1"><b>번호</b></font></th>
					<th><font class="small1"><b>주문일</b></font></th>
					<th><font class="small1"><b>취소일</b></font></th>
					<th><font class="small1"><b>취소사유</b></font></th>
					<th><font class="small1"><b>주문번호</b></font></th>
					<th width="50"><font class="small1"><b>주문자명</b></font></th>
					<th><font class="small1"><b>상품명</b></font></th>
					<th><font class="small1"><b>수량</b></font></th>
					<th><font class="small1"><b>결제방법</b></font></th>
					<th><font class="small1"><b>판매가</b></font></th>
					<!--
					<th>환불수수료</th>
					<th>환불금액</th>
					-->
					<th><font class="small1"><b>상태</b></font></th>
				</tr>
	<c:choose>
		<c:when test="${listSize > 0 }">
			<c:forEach items="${cancelList}" var="cancelData" varStatus="status">
				<tr bgcolor="${cancelData.bgColor}" height="25">
					
					<!-- 번호 -->
					<td align="center"><font class="ver71" color="444444">${(csVO.rowCount - status.index) - (csVO.rowStart) }</font></td>
					
					<!-- 주문일 -->
					<td align="center">
						<font class="ver71" color="444444">
 							<fmt:formatDate value="${cancelData.orddt}"  type="both" dateStyle="short" timeStyle="short" pattern="yyyy-MM-dd hh:mm aa"/>
						</font>
					</td>
					
					<!-- 취소일 -->
				    <td align="center">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    		<font class="ver71" color="444444">
							<fmt:formatDate value="${cancelData.canceldt}"  type="both" dateStyle="short" timeStyle="short" pattern="yyyy-MM-dd hh:mm aa"/>
						</font>
					</td>
					
					<!-- 취소사유 -->
					<td align="center">
						<font class="small1" color="444444">
							${cancelData.codeItem}
						</font>
					</td>
					
					<!-- 주문번호 -->
					<td align="center">
						<a href="javascript:popup('${ctx}/shop/admin/myritz/myritzPopupOrder?ordno=${cancelData.ordno}',800,600)">
							<font class=ver811 color=0074BA>
								<b>${cancelData.ordno}</b>
							</font>
						</a>
					</td>
					
					<!-- 주문자명 -->
					<td align="center">				
					<c:choose>
						<c:when test="${cancelData.mId != '' and cancelData.mId != null}">
					 		<span id="navig" name="navig" m_id="${cancelData.mId}" m_no="${cancelData.mno}">
								<font class="small1" color="0074BA">${cancelData.nameorder}</font>
							</span>
						</c:when>
						<c:otherwise>					
							<font class="small1">${cancelData.nameorder}</font>
						</c:otherwise>
					</c:choose>
					</td>
					
					<!-- 상품명 -->
					<td style="padding-left:10px">
						<a href="javascript:popup('../goods/register?viewFlg=view&mode=modify&goodsno=${cancelData.goodsno}',825,600)">
							<font class="small1" color="444444">
								<div style="float:left;text-overflow:ellipsis;overflow:hidden;width:150px" nowrap>
									${cancelData.goodsnm} [판매사 : ${cancelData.myritzNm }]
								</div>
							</font>
						</a>
						<c:if test="${cancelData.cnt - 1 > 0}">
							외 ${cancelData.cnt - 1} 건
						</c:if>
					</td>
					
					<!-- 수량 -->
					<td align="center"><font class="ver71" color="444444">${cancelData.sea}</font></td>
					
					<!-- 결제방법 -->
					<td align="center"><font class="small1" color="444444">${cancelData.settlekind}</font></td>
					
					<!-- 판매가 -->
					<td align="center">
						<font class="ver71">
							<b><fmt:formatNumber value="${cancelData.pay}" pattern="#,###"/></b> 
						</font>
					</td>
					
					<!-- 상태 -->
					<td align="center">
						<a href="javascript:popup('${ctx}/shop/admin/myritz/myritzPopupOrder?ordno=${cancelData.ordno}',800,600)">
							<font class="small1" color="0074BA">
								${cancelData.stepMsg}
							</font>
						</a>
					</td>
					
				</tr>
		
				<tr><td colspan="15" class="rndline"></td></tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
				<tr bgcolor="#FFFFFF" height="25">
					<td align="center" colspan="13"> 주문취소 내역이 존재하지 않습니다. </td>
				</tr>
		</c:otherwise>
	</c:choose>
			</table>
			
			
			<%-- 페이징 시작 --%>
			<c:if test="${listSize > 0}">
				<div align=center class=pageNavi>
					<b><tags:paginator currentPageNo="${csVO.pageNo}" rowCount="${csVO.rowCount}" pageSize="${csVO.pageSize}"  pageGroupSize="${csVO.pageGroupSize}" /></b>
				</div>
			</c:if>			
			<%-- 페이징 끝 --%>
			
<script>
	window.onload = function(){ UNM.inner();};

	function optionSelect($selectHidden, $selectTag) {
		var selectHidden = $selectHidden.val();
		var selectTag = $selectTag.select()[0];
		for(i = 0, size = selectTag.length; i < size; i++) {
			if(selectTag[i].value == selectHidden) {
				selectTag[i].selected = true;
				break;
			}
		}
	}
	
	function checkbox() {
		var checkStr = $('.js_hiddenType').val();
		var $type = $('.js_type');
		for(var i = 0; i < $type.size(); i++) {
			if(checkStr.indexOf($type[i].value) > -1) {
				console.log(checkStr.hasOwnProperty($type[i].value));
				console.log(checkStr + ' / ' + $type[i].value);
				$type[i].setAttribute('checked', 'checked');
			}
		}
	}
	
	function goPage(page) {
		$('.js_pageNo').val(page);
		$('.js_csForm').submit();
	}
	
	checkbox();
	optionSelect($('.js_hiddenSkey'), $('.js_selectSkey'));
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>

