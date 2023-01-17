<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left:12px">

			<form name="frmList" class="frmList">
				<input type="hidden" class="pageNo" name="pageNo" value="1" />
				<div class="title title_top">1:1문의</span></div>
				<table class="tb">
					<colgroup>
						<col class="cellC">
						<col class="cellL" style="width:540px">
						<col class="cellC">
						<col class="cellL" style="width:400px">
						<col class="cellC">
						<col class="cellL" style="width:380px">
					</colgroup>
					<tr>
						<td>키워드검색전송</td>
						<td>
							<select name="skey">
								<option value="all" ${stringUtil:selected(memQnaVO.skey, "all")} > 통합검색 </option>
								<option value="subject" ${stringUtil:selected(memQnaVO.skey, "subject")}> 제목 </option>
								<option value="contents" ${stringUtil:selected(memQnaVO.skey, "contents")}> 문의 </option>
								<option value="m_id" ${stringUtil:selected(memQnaVO.skey, "m_id")}> 작성자 </option>
							</select>
							<input type="text" name="sword" value="${memQnaVO.sword != null ? memQnaVO.sword : '' }" class="line">
						</td>
						<td>문의분야</td>
						<td colspan="3">
							<select name="sbtype">
								<option value="">전체</option>
								${webUtil:makeSelectCodeItem((codeUtil:codeitem('boardtype')), memQnaVO.sbtype) }
							</select>
						</td>
					</tr>
					<tr>
						<td>등록일</td>
						<td>
							<input type=text name="sregdt" value="${memQnaVO.sregdt_0 }" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
							<input type=text name="sregdt" value="${memQnaVO.sregdt_1 }" onclick="calendar(event)" class=line onkeydown="onlynumber()">
							<a href="javascript:setDate('sregdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle"></a>
							<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle"></a>
							<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle"></a>
							<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle"></a>
							<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle"></a>
							<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle"></a>
						</td>
						<td>노출스킨</td>
						<td>
							<select name="schSkin">
								<option value="">전체</option>
								${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), memQnaVO.schSkin) }
							</select>
						</td>
						<td>답변여부</td>
						<td>
							<select name="schReply">
								<option value="">전체</option>
								<option value="Y"${stringUtil:selected(memQnaVO.schReply, "Y")}>답변</option>
								<option value="N"${stringUtil:selected(memQnaVO.schReply, "N")}>미답변</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif"></div>

				<table width="100%">
					<tr>
						<td class="pageInfo">
							<font class="ver8">
								<c:set var="pages" value="${(memQnaVO.rowCount*10) / (memQnaVO.pageSize*10)} " />
								<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
								<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
								총 <b>${memQnaVO.totalCnt }</b>개, 검색 <b>${memQnaVO.rowCount}</b>개, <b>${memQnaVO.pageNo }</b> of <b>${var3 }</b> Pages
							</font>
						</td>
						<td align="right">
							<%-- 
							<select name="sort" onchange="this.form.submit();">
								<option value="ask desc" ${stringUtil:selected(memQnaVO.sort, "ask desc")}>- 문답순 정렬↑</option>
								<option value="regdt desc" ${stringUtil:selected(memQnaVO.sort, "regdt desc")}>- 등록일 정렬↑</option>
								<option value="regdt asc" ${stringUtil:selected(memQnaVO.sort, "regdt asc")}>- 등록일 정렬↓</option>
							    <optgroup label="------------"></optgroup>
								<option value="subject desc" ${stringUtil:selected(memQnaVO.sort, "subject desc")}>- 제목 정렬↑</option>
								<option value="subject asc" ${stringUtil:selected(memQnaVO.sort, "subject asc")}>- 제목 정렬↓</option>
							</select>&nbsp;
							--%>
							<select name="pageSize" onchange="this.form.submit()">
								<option value="10" ${stringUtil:selected(memQnaVO.pageSize, "10")} >10개 출력
								<option value="20" ${stringUtil:selected(memQnaVO.pageSize, "20")} >20개 출력
								<option value="40" ${stringUtil:selected(memQnaVO.pageSize, "40")} >40개 출력
								<option value="60" ${stringUtil:selected(memQnaVO.pageSize, "60")} >60개 출력
								<option value="100" ${stringUtil:selected(memQnaVO.pageSize, "100")} >100개 출력
							</select>
						</td>
					</tr>
				</table>
			</form>

			<form method="post" action="" name="fmList">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr><td class="rnd" colspan="10"></td></tr>
					<tr class="rndbg">
						<th width="60">번호</th>
						<th>제목</th>
						<th width="80">노출스킨</th>
						<th width="80">문의분야</th>
						<th width="180">작성자</th>
						<th width="80">작성일</th>
						<th width="50">답변</th>
						<th width="50">삭제</th>
					</tr>
					<tr><td class="rnd" colspan="10"></td></tr>
				</table>
				
				<c:forEach items="${memQnaVO.memQnaList }" var="list" varStatus="vnum">
					<c:if test="${list.sno == list.parent}">
						<div style="border-top-width:1; border-top-style:solid; border-top-color:#DCD8D6;">
							<table width="100%" cellpadding="0" cellspacing="0" onclick="view_content(this, event);" class="hand">
								<tr><td height="4" colspan="10"></td></tr> 
								<tr height="25" align="center" onmouseover="this.style.background='#F7F7F7';" onmouseout="this.style.background='';">
									<td width="60"><font class=ver8 color=616161>${(memQnaVO.rowCount - vnum.index) - ( memQnaVO.rowStart ) }</td>
									<td align="left" style="line-height:17px"><font color=333333>${list.subject}</font> <font class=ver8 color=FF6709>( ${list.recnt} )</font></td>
									<td width="80" align="center"><font class=small color=444444>${not empty list.skin ? codeUtil:getCodeName("skin", list.skin) : '' }</font></td>
									<td width="80" align="center"><font class=small color=444444>${not empty list.itemcd ? codeUtil:getCodeName("boardtype", list.itemcd) : '' }</font></td>
									<td width="180"><span id="navig" name="navig" m_id='${list.mid}' m_no='${list.mno}'><font color=0074BA class=ver8><b>${list.name}</b></font></span></td>
									<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
									<td width="50"><a href="javascript:popupLayer('../../board/member_qna/register?mode=reply&sno=${list.sno }',630,600)"><img src="/resources/shop/admin/img/i_reply.gif"></a></td>
									<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno }"></td>
								</tr>
								<tr><td height=4 colspan=10></td></tr>
							</table>
							
							<div style="display:none;padding:5 10 10 63;">
								<c:if test="${ordno != 0 && ordcnt == 1 }">
									<div><font class=small color=444444>[주문번호 <a href="javascript:popup('../../order/popup.order?ordno=${list.ordno }',800,600)"><font class=ver81 color=0074BA>${list.ordno }</font></a><a href="javascript:popup('../../order/popup.order.jsp?ordno=1237515434764',800,600)"><img src="/resources/shop/admin/img/btn_vieworder.gif" border=0 align="absmiddle" hspace=2></a>]</font></div>
								</c:if>
								<c:if test="${ordno == 0 && ordcnt == 0 }">
									<div></div>
								</c:if>
					
								<div><font color="484848">${webUtil:simpleEncode(list.contents)}</font></div>
							</div>
						</div>	
					</c:if>
		
					<c:if test="${list.sno != list.parent}">
						<div style="border-top-width:1; border-top-style:dotted; border-top-color:#DCD8D6;">
							<table width=100% cellpadding=0 cellspacing=0 onclick="view_content(this, event);" class=hand>
								<tr><td height=4 colspan=10></td></tr>
								<tr height=25 align="center" onmouseover=this.style.background="#F7F7F7" onmouseout=this.style.background="">
									<td width="60"><font class=ver8 color=616161>${memQnaVO.totalCnt - vnum.index}</td>
									<td align="left" style="line-height:17px"><img src="/resources/shop/admin/img/btn_reply.gif" border=0 align="absmiddle"> <font color=333333>${webUtil:simpleEncode(list.subject)}</font></td>
									<td width="80" align="left"></td>
									<td width="180"><span id="navig" name="navig" mid='${list.mid}' mno='${list.mno}'><font style="color:#616161;" class=ver8>${list.name }</font></span></td>
									<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
									<!-- <td width="50"></td> -->
									<td width="50"><a href="javascript:popupLayer('../../board/member_qna/register?mode=modify&sno=${list.sno}',630,600)"><img src="/resources/shop/admin/img/i_edit.gif"></a></td>
									<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno }"></td>
								</tr>
								<tr><td height=4 colspan=11></td></tr>
							</table>
							<div style="display:none;padding:5 10 10 97;"><font color=484848>${webUtil:simpleEncode(list.contents)}</font></div>
						</div>
					</c:if>
				</c:forEach>

				<div style="border-bottom-width:1; border-bottom-style:solid; border-bottom-color:#DCD8D6;width:100%;height:1px;font-size:0px;"></div>
				<input type="hidden" style="width:300" name="nolist">
			</form>

			<!-- 페이징  -->
			<div align=center class=pageNavi>
				<b><tags:paginator currentPageNo="${memQnaVO.pageNo}" rowCount="${memQnaVO.rowCount}" pageSize="${memQnaVO.pageSize}"  pageGroupSize="${memQnaVO.pageGroupSize}" /></b>
			</div>

			<div>
				<c:if test="${fn:length(memQnaVO.memQnaList)!=0}">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allselect_s.gif" alt="전체선택" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:PubAllSordes( 'select', fmList['confirmyn'] );">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:PubAllSordes( 'reflect', fmList['confirmyn'] );">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:PubAllSordes( 'deselect', fmList['confirmyn'] );">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
				</c:if>
				<c:if test="${fn:length(memQnaVO.memQnaList)==0}">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allselect_s.gif" alt="전체선택" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
					<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
				</c:if>
			</div>

			<div style="padding-top:15px"></div>

			<div id=MSG01>
				<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">문의제목을 클릭하면 글내용이 열리며, 다시 제목을 클릭하면 내용이 닫히게됩니다.</td></tr>
					<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">작성자를 클릭하시면 회원정보와 함께 회원주문내역 등을 보실 수 있습니다.</td></tr>
				</table>
			</div>
			
			<script>cssRound('MSG01')</script>
			<script language="javascript">
				function goPage(page){
					$('.pageNo').val(page);
					$('.frmList').submit();
				}
	
				var preContent;
				function view_content(obj, e)
				{
					if ( document.all && ( e.srcElement.tagName == 'A' || e.srcElement.tagName == 'IMG' || e.srcElement.tagName == 'INPUT' ) ) return;
					else if ( !document.all && ( e.target.tagName == 'A' || e.target.tagName == 'IMG' || e.srcElement.tagName == 'INPUT' ) ) return;
				
					var div = obj.parentNode;
				
					if ( document.all ) obj = div.childNodes[ 1 ]; else obj = div.childNodes[ 3 ]; // 모질라 경우 줄내림도 #text 임
				
					if (preContent && obj!=preContent){
						obj.style.display = "block";
						preContent.style.display = "none";
					}
					else if (preContent && obj==preContent) preContent.style.display = ( preContent.style.display == "none" ? "block" : "none" );
					else if (preContent == null ) obj.style.display = "block";
				
					preContent = obj;
				}
				
				function act_delete(){
				
					if ( PubChkSelect( fmList['confirmyn'] ) == false ){
						alert( "삭제하실 내역을 선택하여 주십시요." );
						return;
					}
				
					if ( confirm( "선택한 아이템을 정말 삭제하시겠습니까?\n삭제 후 복구할 수 없습니다." ) == false ) return;
				
					var idx = 0;
					var codes = new Array();
					var count = fmList['confirmyn'].length;
				
					if ( count == undefined ) codes[ idx++ ] = fmList['confirmyn'].value;
					else {
				
						for ( i = 0; i < count ; i++ ){
							if ( fmList['confirmyn'][i].checked ) codes[ idx++ ] = fmList['confirmyn'][i].value;
						}
					}
				
					fmList.nolist.value = codes.join( ";" );
					fmList.action = "../../board/member_qna/indb?mode=delete" ;
					fmList.submit() ;
				}
			</script>
			<script>window.onload = function(){ UNM.inner();};</script>
			<script>
			linecss();
			table_design_load();
			</script>
			
			<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>