<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 


<c:set var="grpList" value="${AGVO.grpList !=null ? AGVO.grpList : ''}" />
<c:set var="grpSelectList" value="${AGVO.grpSelectList != null ? AGVO.grpSelectList : ''}" />
<c:set var="vnum" value="${AGVO.vnum !=null ? AGVO.vnum:''}" />
<c:set var="strGrpType" value="${AGVO.grpType != null ? AGVO.grpType : '0'}" />
<c:set var="strkey" value="${AGVO.skey != null ? AGVO.skey : 'all'}" />
<c:set var="strSword" value="${AGVO.sword != null ? AGVO.sword : '' }" />
<c:set var="strSort" value="${AGVO.sort != null ? AGVO.sort : 'regdt2 desc' }" />
<c:set var="pageNum" value="${AGVO.pageNum != null ? AGVO.pageNum : 10}" />

<script>
	function iciSelect(obj)
	{
		var row = obj.parentNode.parentNode;
		row.style.background = (obj.checked) ? "#F0F4FF" :"#FFFFFF";
	}
/* 	function delMember(fm)
	{
		if (!isChked(document.getElementsByName('chk[]'))) return;
		if (!confirm('정말로 삭제 하시겠습니까?')) return;
		fm.target = "_self";
		fm.mode.value = "delete";
		fm.action = "../adminGroup/indb";
		fm.submit();
	} */
	function modiMember(fm)
	{

		if (!confirm('정말로 수정 하시겠습니까?')) return;
		fm.target = "_self";
		fm.mode.value = "adminModify";
		fm.action = "./adminGroup/indb";
		fm.submit();
	}

</script>

<form class="fm" id="fm" method="post">
	<input type="hidden" class="pageNo" name="pageNo"  value="${pageNo != '' ? AGVO.pageNo : '1' }"/>

	<!-- 여기서부터 수정 -->
	<%--
	<div style="padding:10 0 5 5;color:#fe5400;"><font color="000000"><b>2. 관리자로 설정할 회원을 검색하고 관리자그룹으로 변경합니다. </b></font><font class="extext" color="#fe5400">(관리자로 설정될 사람은 반드시 미리 회원으로 가입되어 있어야 합니다)</font></div>

	<table class="tb">
		<col class="cellC" /><col class="cellL" style="width:330" />
		<col class="cellC" /><col class="cellL" />
		<tr height="30">
			<td>선택</td>
			<td>
				<input type="radio" name="grpType" value="0" class="null" ${strGrpType ne '1' ? 'checked' : ''}/>관리자그룹에서 검색
				&nbsp;&nbsp;&nbsp;<input type="radio" name="grpType" value="1" class="null" ${strGrpType eq '1' ? 'checked' : ''}/>일반회원그룹에서 검색
			</td>
			<td>키워드</td>
			<td>
				<select name="skey">
					<option value="all" ${strkey eq 'all' ? 'selected' : ''}> 통합검색 </option>
					<option value="name" ${strkey eq 'name' ? 'selected' : ''}> 회원명 </option>
					<option value="m_id" ${strkey eq 'm_id' ? 'selected' : ''}> 아이디 </option>
					<option value="email" ${strkey eq 'email' ? 'selected' : ''}> 이메일 </option>
					<option value="phone" ${strkey eq 'phone' ? 'selected' : ''}> 전화번호 </option>
					<option value="mobile" ${strkey eq 'mobile' ? 'selected' : ''} > 핸폰번호 </option>
				</select> <input type="text" name="sword" value="${strSword}" style="width:200px" class="line" onkeydown="noSpecialCharacters()" maxlength="50"/>
			</td>
		</tr>
	</table>

	<div class="button_top"><input type="image"  onclick="go()" src="/resources/shop/admin/img/btn_search2.gif"  /></div>
	--%>

	<div class="sub-title">
		2. 관리자로 설정할 회원을 검색하고 관리자그룹으로 변경합니다.
		<span class="extext">(관리자로 설정될 사람은 반드시 미리 회원으로 가입되어 있어야 합니다)</span>
	</div>

	<div class="sub-cont-wrap">
		<div class="div-tbl inp-tbl">
			<div class="th">선택</div>
			<div>
				<ul class="ver-ul" style="padding: 0 0 0 5px;">
					<li>
						<label>
							<input type="radio" name="grpType" value="0" class="null" ${strGrpType ne '1' ? 'checked' : ''}/>
							관리자그룹에서 검색
						</label>
					</li>
					<li>
						<label>
							<input type="radio" name="grpType" value="1" class="null" ${strGrpType eq '1' ? 'checked' : ''}/>
							일반회원그룹에서 검색
						</label>
					</li>
				</ul>
			</div>

			<div class="th">키워드</div>
			<div>
				<select name="skey">
					<option value="all" ${strkey eq 'all' ? 'selected' : ''}> 통합검색 </option>
					<option value="name" ${strkey eq 'name' ? 'selected' : ''}> 회원명 </option>
					<option value="m_id" ${strkey eq 'm_id' ? 'selected' : ''}> 아이디 </option>
					<option value="email" ${strkey eq 'email' ? 'selected' : ''}> 이메일 </option>
					<option value="phone" ${strkey eq 'phone' ? 'selected' : ''}> 전화번호 </option>
					<option value="mobile" ${strkey eq 'mobile' ? 'selected' : ''} > 핸폰번호 </option>
				</select>
				<input type="text" name="sword" value="${strSword}" style="width:200px" class="line" onkeydown="noSpecialCharacters()" maxlength="50"/>
			</div>
		</div>
	</div>

	<div class="sub-cont-wrap" style="text-align: center;">
<%--		<input type="image"  onclick="go()" src="/resources/shop/admin/img/btn_search2.gif"  />--%>
<%--		<a href="javascript:go();" class="admin-group-btn3">검색</a>--%>
		<a href="javascript:go();" class="admin-btn-search2">검색</a>
	</div>


	<table width="100%">
		<tr>
			<td class="pageInfo">
				<div class="pageInfo ver8">총 <b>${AGVO.totalCount}</b>개, 검색 <b>${AGVO.rowCount}</b>개, <b>${AGVO.pageNo} Pages</b> of <fmt:formatNumber value="${(AGVO.rowCount*10)/(AGVO.pageSize*10)+(1-((AGVO.rowCount*10)/(AGVO.pageSize*10)%1))%1}" pattern="0"/> </div>
			</td>
			<td align=right>
				<select name="sort" onchange="this.form.submit();">
					<option value="regdt2 desc" ${strSort eq 'regdt2 desc' ? 'selected' : ''}>- 가입일 정렬↑</option>
					<option value="regdt2 asc" ${strSort eq 'regdt2 asc' ? 'selected' : ''}>- 가입일 정렬↓</option>
					<option value="last_login desc" ${strSort eq 'last_login desc' ? 'selected' : ''}>- 최종로그인 정렬↑</option>
					<option value="last_login asc" ${strSort eq 'last_login asc' ? 'selected' : ''}>- 최종로그인 정렬↓</option>
					<option value="cnt_login desc" ${strSort eq 'cnt_login desc' ? 'selected' : ''}>- 방문수 정렬↑</option>
					<option value="cnt_login asc" ${strSort eq 'cnt_login asc'? 'selected' : ''}>- 방문수 정렬↓</option>
					<optgroup label="------------"></optgroup>
					<option value="name desc" ${strSort eq 'name desc'? 'selected' : ''}>- 이름 정렬↑</option>
					<option value="name asc" ${strSort eq 'name asc'? 'selected' :''}>- 이름 정렬↓</option>
					<option value="m_id desc" ${strSort eq 'm_id desc' ? 'selected' :''}>- 아이디 정렬↑</option>
					<option value="m_id asc" ${strSort eq 'm_id asc' ? 'selected' :''}>- 아이디 정렬↓</option>
					<optgroup label="------------"></optgroup>
					<option value="emoney desc" ${strSort eq 'emoney_desc' ? 'selected' :''}>- 적립금 정렬↑</option>
					<option value="emoney asc" ${strSort eq 'emoney asc' ? 'selected':''}>- 적립금 정렬↓</option>
					<option value="sum_sale desc" ${strSort eq 'sum_sale_desc' ? 'selected' :''}>- 구매금액 정렬↑</option>
					<option value="sum_sale asc" ${strSort eq 'sum_sale asc'? 'selected':'' }>- 구매금액 정렬↓</option>
				</select>&nbsp;
				<select name=pageSize onchange="go()">
					<option value="10" ${stringUtil:selected(AGVO.pageSize, "10")}>10개 출력
					<option value="20" ${stringUtil:selected(AGVO.pageSize, "20")}>20개 출력
					<option value="40" ${stringUtil:selected(AGVO.pageSize, "40")}>40개 출력
					<option value="60" ${stringUtil:selected(AGVO.pageSize, "60") }>60개 출력
					<option value="100" ${stringUtil:selected(AGVO.pageSize, "100")}>100개 출력
				</select>
			</td>
		</tr>
	</table>
</form>

<form name="fmList" method="post">
	<input type="hidden" name="mode" />
	<input type="hidden" name="query" value="<%//=substr($pg->query,0,strpos($pg->query,"limit"))%>" />

	<!-- 여기부터 -->
	<%--
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr><td class=rnd colspan=14></td></tr>
		<tr class=rndbg style="padding-top:2">
			<th width=60><font class="small1"><b>번호</b></font></th>
			<th width=100><font class="small1"><b>이름</b></font></th>
			<th><font class="small1"><b>아이디</b></font></th>
			<!-- <th width=60><font class="small1"><b>CRM</th> -->
			<th><font class="small1"><b>그룹</b></font></th>
			<th><font class="small1"><b>방문수</b></font></th>
			<th><font class="small1"><b>가입일</b></font></th>
			<th><font class="small1"><b>최근로그인</b></font></th>
			<th><font class="small1"><b>승인</b></font></th>
			<th><font class="small1"><b>수정</b></font></th>
		</tr>
		<tr><td class=rnd colspan=14></td></tr>
		<c:forEach items="${grpList}" var="list" varStatus="status">
			
		<input type="hidden" name="mno" value="${list.mno}"/>
		<tr height="30" align="center">
			<td><font class=ver71 color=616161>${vnum[status.index]}</font></td>
			<td><span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}"><font class="small1" color=0074BA><b>${list.name}</b></font></span></td>
			<td><span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}"><font class=ver811 color=0074BA><b>${list.mid}</b></font></span></td>
			<!-- <td><a href="javascript:popupLayer('../member/Crm_view.jsp?m_id=<%= grpList.get(gi, "m_id") %>',780,600)"><img src="../img/icon_crmlist<%= grpList.get(gi, "sex") %>.gif"></a></td> -->
			<td>
				<font class=def>
				<select name='level' >
				<c:forEach items="${grpSelectList}" var="slist" varStatus="sstatus">
				<!-- grpSelectList = gd_member_grp 사용 kLevel   -->
				<!-- grpList = gd_member,gd_member_grp 사용 klevel -->
					<option value="${slist.kLevel}" ${slist.kLevel eq list.klevel ? 'selected' : ''}>${slist.grpnm}</option> 
				</c:forEach>
				</select>
				</font>
			</td>
			<td><font class=ver81 color=616161><fmt:formatNumber value="${list.cntlogin }" pattern="#,###,###"/></font></td>
			<td><font class=ver81 color=616161>
				<fmt:parseDate value="${list.regdt2}" var ="regdt2" pattern="yyyy.MM.dd"/>
			    <fmt:formatDate value="${regdt2}" pattern="yyyy-MM-dd"/>
			 </font></td>
			<td><font class=ver81>
				<!--strLastLogin-->
					<fmt:formatDate value="${list.lastlogin}" var="dateL" pattern="yyyy-MM-dd"/>
					<c:choose>
					<c:when test="${dateL eq AGVO.now}">
						<font color='#f54500'> <fmt:formatDate value="${list.lastlogin}" pattern="HH:mm:ss"/></font>
					</c:when>
					<c:otherwise>
						<font color='616161'><fmt:formatDate value="${list.lastlogin}" pattern ="yyyy-MM-dd"/></font>
					</c:otherwise>
					</c:choose>
				<!--strLastLogin-->
			</font></td>
			<td><font class=small color=616161>${list.status eq '1' ? '승인' :'미승인'}</font></td>
			<td><a href="../member/register?mid=${list.mid}&mode=modify"><img src="/resources/shop/admin/img/i_edit.gif"></a></td>
		</tr>
		<tr><td colspan=14 class=rndline></td></tr>
		</c:forEach>

	</table>
	--%>


	<div class="sub-cont-wrap">
		<table class="stripe-tbl inp-tbl">
			<colgroup>
				<col style="width: 60px;">
				<col style="width: 100px;">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>그룹</th>
					<th>방문수</th>
					<th>가입일</th>
					<th>최근로그인</th>
					<th>승인</th>
					<th>수정</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${grpList}" var="list" varStatus="status">
					<input type="hidden" name="mno" value="${list.mno}"/>
					<tr>
						<td>${vnum[status.index]}</td>
						<td>
							<span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}">
								<span style="color: #0074BA; font-weight: bold;">${list.name}</span>
							</span>
						</td>
						<td>
							<span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}">
								<span style="color: #0074BA; font-weight: bold;">${list.mid}</span>
							</span>
						</td>
						<td>
							<select name='level'>
								<c:forEach items="${grpSelectList}" var="slist" varStatus="sstatus">
									<option value="${slist.kLevel}" ${slist.kLevel eq list.klevel ? 'selected' : ''}>${slist.grpnm}</option>
								</c:forEach>
							</select>
						</td>
						<td><fmt:formatNumber value="${list.cntlogin }" pattern="#,###,###" /></td>
						<td>
							<fmt:parseDate value="${list.regdt2}" var ="regdt2" pattern="yyyy.MM.dd"/>
							<fmt:formatDate value="${regdt2}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<!--strLastLogin-->
							<fmt:formatDate value="${list.lastlogin}" var="dateL" pattern="yyyy-MM-dd"/>
							<c:choose>
								<c:when test="${dateL eq AGVO.now}">
									<span style="color: #f54500">
										<fmt:formatDate value="${list.lastlogin}" pattern="HH:mm:ss"/>
									</span>
								</c:when>
								<c:otherwise>
									<span style="color: #616161">
										<fmt:formatDate value="${list.lastlogin}" pattern ="yyyy-MM-dd"/>
									</span>
								</c:otherwise>
							</c:choose>
							<!--strLastLogin-->
						</td>
						<td>${list.status eq '1' ? '승인' :'미승인'}</td>
						<td>
<%--							<img src="/resources/shop/admin/img/i_edit.gif" />--%>
							<a href="../member/register?mid=${list.mid}&mode=modify" class="admin-i-edit">수정</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<div class="sub-cont-wrap" style="text-align: center;">
		<!-- pagination -->
		<tags:paginator currentPageNo="${AGVO.pageNo}" rowCount="${AGVO.rowCount}" pageSize="${AGVO.pageSize}"  pageGroupSize="${AGVO.pageGroupSize}" />

		<!-- save -->
<%--		<a href="javascript:modiMember(document.fmList)"><img src="/resources/shop/admin/img/btn_save.gif"></a>--%>
		<a href="javascript:modiMember(document.fmList)" class="admin-btn">저장</a>
	</div>

	<%--
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td><tags:paginator currentPageNo="${AGVO.pageNo}" rowCount="${AGVO.rowCount}" pageSize="${AGVO.pageSize}"  pageGroupSize="${AGVO.pageGroupSize}" /></td>
		</tr>
		<tr>
			<td align=center style="padding:25 0 20 0"><a href="javascript:modiMember(document.fmList)"><img src="/resources/shop/admin/img/btn_save.gif"></a></td>
		</tr>
	</table>
	--%>
	<div id=MSG01>
		<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">이름과 아이디를 클릭하면 회원정보를 볼 수 있습니다.</td></tr>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>관리자는 기본적으로 쇼핑몰의 회원이 되며, 관리자를 추가하려면 회원가입후 해당회원을 관리자 그룹으로 지정하시면 됩니다.</td></tr>
		</table>
	</div>
<script>cssRound('MSG01')</script>

</form>

<script>window.onload = function(){ UNM.inner();};</script>
<script type="text/javascript">
function goPage(page){
// 	window.location.href="adminGroup?pageNo="+page+"&grpType="+${strGrpType};
	$('.pageNo').val(page);
	$('.fm').submit();
}
function go(){
// 	window.location.href="adminGroup?pageNo="+page+"&grpType="+${strGrpType};
	var page=1;
	$('.pageNo').val(page);
	$('.fm').submit();
}
</script>
