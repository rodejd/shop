<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../common/left.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<script language="javascript">
$(function(){
	// 숫자만
    $(".onlynum").css("ime-mode", "disabled").keyup(function(){
        $(this).val( $(this).val().replace(/[^0-9]/g,"") );
    });
});
</script>


<c:set var="total" value="${fn:length(codeVO.dataCodeList)}" />

<form name=frmList>
<div class="title title_top">각종 코드관리<span>회원관심분야항목, 1:1문의항목, FAQ항목 등 각종 코드항목을 관리합니다</span> <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=data&no=7',870,800)"><img src="../../img/btn_q.gif" border=0 align=absmiddle></a> --></div>
<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>분류선택</td>
	<td>
		<SELECT NAME="groupcd" onchange="this.form.submit();">
			<option value="">분류을 선택하세요.</option>
			<c:forEach items="${codeUtil:codeitem2()}" var="codelist" varStatus="st">
				<option value="${codelist.itemcd}" ${stringUtil:selected(codelist.itemcd, fn:trim(codeVO.groupcd))}>${codelist.itemnm} (${codelist.itemcd})</option>
			</c:forEach>
		</SELECT>
	</td>
</tr>
</table>
<div class=button_top><!--<input type=image src="../../img/btn_search2.gif">--></div>

<table width=100%>
<tr>
	<td class=pageInfo>총 <b>${total}</b>개</td>
</tr>
</table>
</form>


<form method="post" action="" name="fmList">
<INPUT TYPE="hidden" name="allmodify"/>
<INPUT TYPE="hidden" name="groupcd" value="${codeVO.groupcd}"/>
<table width=100% cellpadding=0 cellspacing=0>
<tr><td class=rnd colspan=10></td></tr>
<tr class=rndbg>
	<th width="100">대분류코드번호</th>
	<th width="100">소분류코드번호</th>
	<th>코드명</th>
	<th width="80">순서</th>
	<th width="50">수정</th>
	<th width="50">삭제</th>
</tr>
<tr><td class=rnd colspan=10></td></tr>

<c:choose>
<c:when test="${codeVO.dataCodeList != null &&  fn:length(codeVO.dataCodeList) >0 }">
<c:forEach items="${codeVO.dataCodeList}" var="dataCodeList" varStatus="st">

<tr><td height=4 colspan=10></td></tr>
<tr height=25 align="center">
	<td><b>${dataCodeList.groupcd}</b></td>
	<td><b>${dataCodeList.itemcd}</b></td>
	<td align="left">${dataCodeList.itemnm}</td>
	<td align="center">
	<input TYPE="hidden" name="code" value="${dataCodeList.sno}"/>
	<table border="0" cellspacing="0" cellpadding="0" style="padding:0 3 0 3;">
		<tr>
			<c:set var="pri_code" value="${dataCodeList.groupcd},${dataCodeList.sno}" />
			<c:set var="uprow" value="${dataCodeList.uprow}" />
			<c:set var="downrow" value="${dataCodeList.downrow}" />
			
			<c:if test="${dataCodeList.groupcd != ''}">		
			<td width="25%">
				<c:if test="${(-1 != uprow) && (st.index != 0) }">
					<a href="javascript:act_modSort( 'sort_up', '${pri_code}', '${dataCodeList.sort}' );"><img src="/resources/shop/admin/img/ico_arrow_up.gif" alt="상위 이동" border="0" align="absmiddle" hspace="1"></a>
				</c:if>
			</td>
			<td width="25%">
				<c:if test="${(-1 != downrow) && (st.index != fn:length(codeVO.dataCodeList)-1 ) }">
					<a href="javascript:act_modSort( 'sort_down', '${pri_code}', '${dataCodeList.sort}' );"><img src="/resources/shop/admin/img/ico_arrow_down.gif" alt="하위 이동" border="0" align="absmiddle" hspace="1"></a>
				</c:if>
			</td>
			</c:if>
	
			<td width="50%" align="center"><input type="text" size="25" name="sort" value="${dataCodeList.sort}" style="width:30;text-align:center" onkeyPress="if(event.keyCode == 13){ act_modSort( 'sort_direct', '<?=$pri_code?>', this.value ); }" class=onlynum></td>
		</tr>
	</table>
	</td>
	<td STYLE="PADDING-TOP:3PX;">
		<a href="javascript:popupLayer('register?mode=modify&sno=${dataCodeList.sno}&groupcd=${codeVO.groupcd}&itemcd=${dataCodeList.itemcd}&itemnm=${dataCodeList.itemnm}')"><img src="/resources/shop/admin/img/i_edit.gif"></a>
	</td>
	<td class="noline"><input type=checkbox name=confirmyn value="${dataCodeList.sno}"></td>
</tr>
<tr><td height=4 colspan=10></td></tr>
<tr><td colspan=10 class=rndline></td></tr>

</c:forEach>
</c:when>
<c:otherwise>
	<tr><td height=4 colspan=10></td></tr>
	<tr height=25><td align=center colspan=10> 코드가 존재하지 않습니다. </td></tr>
	<tr><td colspan=10 class=rndline></td></tr>
</c:otherwise>
</c:choose>

</table>


<INPUT TYPE="hidden" style="width:300" NAME="nolist">
</form>

<div style="float:left;margin-top:10px;">
<c:if test="${0 != total }">

	<img src="/resources/shop/admin/img/btn_allselect_s.gif" alt="전체선택"  border="0" align='absmiddle' style="cursor:hand" onclick="javascript:PubAllSordes( 'select', fmList['confirmyn'] );">
	<img src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전"  border="0" align='absmiddle' style="cursor:hand" onclick="javascript:PubAllSordes( 'reflect', fmList['confirmyn'] );">
	<img src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제"  border="0" align='absmiddle' style="cursor:hand" onclick="javascript:PubAllSordes( 'deselect', fmList['confirmyn'] );">
	<img src="/resources/shop/admin/img/btn_alldelet_s.gif" alt="선택삭제" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:act_delete();">
</c:if>
<c:if test="${ 0 == total }">
	<img src="/resources/shop/admin/img/btn_allselect_s.gif" alt="전체선택"  border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
	<img src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전"  border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
	<img src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제"  border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
	<img src="/resources/shop/admin/img/btn_alldelet_s.gif" alt="선택삭제" border="0" align='absmiddle' style="cursor:hand" onclick="javascript:alert( '데이타가 존재하지 않습니다.' );">
</c:if>

</div>

<div style="float:right;margin-top:10px;">
<A HREF="javascript:act_allmodify();"><img src="/resources/shop/admin/img/btn_allmodify_s.gif" alt="일괄수정" border=0 align=absmiddle></A>
<a href="javascript:popupLayer('register?mode=register&groupcd=${codeVO.groupcd}')"><img src="/resources/shop/admin/img/btn_regist_s.gif" alt="등록" border=0 align=absmiddle></a>
</div>


<div style="padding-top:60px"></div>


<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/arrow_blue.gif" align=absmiddle>항목순서바꾸기 (항목번호 순서대로 사용자화면에서 보입니다)</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>방법1) <img src="/resources/shop/admin/img/ico_arrow_down.gif" align=absmiddle> <img src="/resources/shop/admin/img/ico_arrow_up.gif" align=absmiddle> 화살표 : 오름과 내림화살표를 눌러 순서를 바꾸세요.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>방법2) 개별순서 수정: 번호칸에 순서숫자를 입력하고 'Enter key' 누르면 순서가 저장됩니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>방법3) 전체순서 수정 : 각 '번호칸'에 순서숫자를 입력하고 [일괄수정]을 클릭하시면 됩니다.</td></tr>
</table>
</div>
<script>cssRound('MSG01')</script>

<SCRIPT LANGUAGE="JavaScript">
/*-------------------------------------
 일괄수정
-------------------------------------*/
function act_allmodify(){
	var fs = document.fmList; // 리스트폼
	var form_check_yn = true;
	
	if( fs['code'] == null ) return; // 레코드가 1미만인 경우

	var fieldnm = new Array( 'code', 'sort' ); // 필드명
	var csField = new Array(); // 필드데이타저장
	
	// 필드데이타초기화
	$.each( fieldnm, function( item ) {
		csField[item] = '';
	});

	var count = fs['code'].length;	// 레코드수
	
	if( count == undefined ){ // 레코드수가 1개 인 경우

		$.each( fieldnm, function( item, value ) {
			var Obj = eval( "fs['" + value + "']" );
			
			if('sort' == value){
				if(! $.isNumeric( Obj.value )){
					alert( '순서는 숫자만 입력가능합니다!' );
					Obj.focus();
					form_check_yn = false;
					return false;
				}
				
				if( '' == Obj.value ){
					alert( '순서를 입력하세요!' );
					Obj.focus();
					form_check_yn = false;
					return false;
				}
			}
			
			if( Obj.type != 'checkbox' ){
				csField[item] += Obj.value + ";"; 
			}else{
				csField[item] += Obj.checked + ";";
			}
		});

	}else { // 레코드수가 2개 이상인 경우

		for( var i = 0; i < count; i++ ){
			$.each( fieldnm, function( item, value ) {
				var Obj = eval( "fs['" + value + "']" );
				
				if('sort' == value){
					if(! $.isNumeric( Obj[i].value )){
						alert( '순서는 숫자만 입력가능합니다!' );
						Obj[i].focus();
						form_check_yn = false;
						return false;
					}
					
					if( '' == Obj[i].value ){
						alert( '순서를 입력하세요!' );
						Obj[i].focus();
						form_check_yn = false;
						return false;
					}
				}
				
				if( Obj[i].type != 'checkbox' ){
					csField[item] += Obj[i].value + ";"; 
				}else{
					csField[item] += Obj[i].checked + ";";
				}
			});
		}
	}
	
	if(!form_check_yn){
		return;
	}

	$.each( fieldnm, function( item ) {
		fs.allmodify.value += item + '==' + csField[item] + '||';
	});
	
	fmList.action = "indb?mode=allmodify";
	fmList.submit() ;
}
</SCRIPT>



<SCRIPT LANGUAGE="JavaScript">
/*-------------------------------------
 순서수정
-------------------------------------*/
function act_modSort( mode, code, sort ){
	fmList.action = "indb?mode=" + mode + "&code=" + code + "&sort=" + sort;
	fmList.submit() ;
}
</SCRIPT>



<SCRIPT LANGUAGE=JavaScript>
/*-------------------------------------
 삭제
-------------------------------------*/
function act_delete(){
	if ( PubChkSelect( fmList['confirmyn'] ) == false ){
		alert( "삭제하실 내역을 선택하여 주십시요." );
		return;
	}

	if ( confirm( "선택한 아이템을 정말 삭제하시겠습니까?\n삭제 후 복구할 수 없습니다." ) == false ) return;

	/*
	var idx = 0;
	var codes = new Array();
	var count = fmList['confirmyn'].length;
	

	if ( count == undefined ){
		codes[ idx++ ] = fmList['confirmyn'].value;
	} else {

		for ( i = 0; i < count ; i++ ){
			if ( fmList['confirmyn'][i].checked ) codes[ idx++ ] = fmList['confirmyn'][i].value;
		}
	}

	fmList.nolist.value = codes.join( "," );
	*/
	
	
	// 배열 선언
	var arrayParam = new Array();

	//each로 loop를 돌면서 checkbox의 check된 값을 가져와 담아준다.
	$("input:checkbox[name=confirmyn]:checked").each(function(){
		arrayParam.push($(this).val());
	});
    
	$("input:text[name=sort]").each(function(){
		$(this).val(0);
	});
	
	fmList.nolist.value = arrayParam;
	
	fmList.action = "indb?mode=delete";
	fmList.submit() ;
}
</SCRIPT>


<script>
//linecss();
//table_design_load();
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>