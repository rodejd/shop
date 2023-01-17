<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<style>body {margin:0}</style>
<script language="javascript">

	/* 2017-08-22 추가 */
	function emptySub(){
		if(document.form.brandno.value == 0) {
			if(document.form.sub.value == ""){
				alert("브랜드명을 입력하세요.");
				return false;
			}	
		}
		if(document.form.myritzCd.value ==0 || document.form.myritzCd.value ==""){
			if(document.form.approvalStatus.value !='2'){
				alert("판매업체가 제공한 브랜드가 아니라면 승인으로만 가능합니다.");
				return false;
			}
		}
		return true;
	}

	function godSubmit(){
		
		if(emptySub()){
			document.form.submit();	
		}
	}

	window.onload = function(){
		parent.document.getElementById('ifrmbrand').style.height = document.body.scrollHeight;
	}
	
	
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<html>
<head>
<title>'xMall ||| Shoppingmall 관리자모드'</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>
<?=$scriptLoad?>
<script language="javascript">
if(window.addEventListener) 
{
	window.addEventListener('load',linecss,false); 
}
else 
{
	window.attachEvent('onload',linecss); 
}
</script>
<div id="dynamic"></div>
<!-- <iframe name="ifrmHidden" src="../../blank.jsp" style="display:none"></iframe> -->
<div id="jsmotion"></div>

<body class="scroll">

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">

$(function(){
	if( '${result}' == 1 ){
		parent.location.reload();
		parent.parent.location.reload();
		parent.closeLayer();
	}
});	 


</script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form name=form method=post action="myritzBrand/indb">
<input type=hidden name=mode value="mod_brand">
<input type=hidden name=brandno value="${brandVO.brandno}">
<input type=hidden name=infoyn value="${brandVO.infoyn}">
<input type=hidden name=sno value="${brandVO.brandObj.sno}">

	<div class="title_sub" style="margin:0">브랜드명 생성/수정/삭제<span>브랜드명을 추가하고 관리합니다 </span></div>

	<table class=tb>
		<col class=cellC><col class=cellL>
		<tbody style="height:26px">
		<tr>
			<td>현재브랜드</td>
			<td>
			${brandVO.brandno != 0 ? 'TOP > ' : '' }
			${brandVO.brandno != 0 ? brandVO.brandObj.brandnm : '전체브랜드'  }
			<c:if test="${brandVO.brandno != 0 }">
				<a href="${ctx}/shop/admin/myritz/goodsBrand?brandno=${brandVO.brandObj.sno}" target=_blank><img src="/resources/shop/admin/img/i_nowview.gif" border=0 align=absmiddle hspace=10></a>
			</c:if>
			</td>
		</tr>
		<tr>
			<td>이 브랜드의 상품수</td>
			<td><b>${stringUtil:getMoneyFormatInteger(brandVO.brandCnt)}</b>개</td>
		</tr>
		
		<c:choose>
			<c:when test="${brandVO.brandno != 0 }">
				<tr>
					<td>현재 브랜드명</td>
					<td>
						<input type=text name=brandnm class=lline value="${brandVO.brandObj.brandnm}"  required >
						&nbsp; 브랜드코드 : <b>${brandVO.brandObj.sno}</b>
					</td>
				</tr>
				<tr>
					<td>판매사</td>
					<td>
						<input type=hidden id=schMyritzCd name=myritzCd class=lline value="${brandVO.brandObj.myritzCd}"   >
						<input type=text id=schMyritzNm name=myritzname class=lline value="${brandVO.brandObj.myritzNm }"readonly="readonly">
<!-- 						<input type="button" value="판매사 조회" onclick="popupLayer('/shop/admin/common/myritzSearchPopup', 600, 500);" /> -->
					</td>
				</tr>
				<tr>
					<td>승인/반려</td>
					<td>
					 승인 <input type="radio" name='approvalStatus' value="2" ${brandVO.brandObj.approvalStatus == 2 ? 'checked' : ''} > 
					 반려 <input type="radio" name='approvalStatus' value="3" ${brandVO.brandObj.approvalStatus == 3 ? 'checked' : ''}>
					</td>
					
					<tr>
						<td>관리자메모</td>
						<td>
							<input type="text" name='brandMemo' value="${brandVO.brandObj.brandMemo }" class='lline' > 
						</td>
					</tr>
				</tr>
				<tr>
					<td>브랜드 삭제 </td>
					<td><a href="javascript:popupLayer('popup.myritzDelBrand?brandno='+${brandVO.brandno});"><img src="/resources/shop/admin/img/i_del.gif" border=0 align=absmiddle></a>
				</tr>
			</table>
			
				<div class="title_sub">브랜드페이지 상단부분 꾸미기<span>브랜드페이지 상단부분을 디자인합니다</span></div>
				<table class=tb>
					<col class=cellC><col class=cellL>
					<tr>
						<td>상단HTML 
						</td>
						<td>
							<textarea name=body type=editor style="width:100%;height:500px">${brandVO.categoryInfoObj.body }</textarea>
								<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
							<script>mini_editor("/resources/shop/lib/meditor/");</script>
						</td>
					</tr>
				</table>
			<c:if test="${param.selector ne 1}">	
				<div class="title_sub">브랜드페이지 리스트부분 꾸미기<span>브랜드페이지 하단의 리스트부분을 디자인합니다</span></div>
			
				<table class=tb>
					<col class=cellC><col class=cellL>
					<tr>
						<td>디스플레이유형</td>
						<td>
			
							<table>
								<col align=center span=3>
								<tr>
									<td><img src="/resources/shop/admin/img/goodalign_style_01.gif"></td>
									<td><img src="/resources/shop/admin/img/goodalign_style_02.gif"></td>
									<td><img src="/resources/shop/admin/img/goodalign_style_03.gif"></td>
								</tr>
								<tr class=noline>
									<td><input type=radio name=tpl value="tpl_01" ${'tpl_01' eq brandVO.categoryInfoObj.tpl ? 'checked' : ''}></td>
									<td><input type=radio name=tpl value="tpl_02" ${'tpl_02' eq brandVO.categoryInfoObj.tpl ? 'checked' : ''}></td>
									<td><input type=radio name=tpl value="tpl_03" ${'tpl_03' eq brandVO.categoryInfoObj.tpl ? 'checked' : ''}></td>
								</tr>
							</table>
						</td>
					</tr>
					<!-- <tr>
						<td>이미지사이즈</td>
						<td><input type=text name=size value="<%//= rtList1.get(0, "size") %>"> <font class=ver8>pixel</font></td>
					</tr> -->
					<tr>
						<td>상품개수</td>
						<td><input type=text name=pagenum value="${brandVO.categoryInfoObj.pagenum}"  onkeydown="onlynumber();" class=lline></td>
					</tr>
					<tr>
						<td>라인당 상품수</td>
						<td><input type=text  name=cols value="${brandVO.categoryInfoObj.cols != null ? brandVO.categoryInfoObj.cols : '' }" onkeydown="onlynumber();" > 개</td>
					</tr>
				</table>
			</c:if>
		</c:when>
			
			<c:otherwise>
			
				<tr>
					<td>하위브랜드 생성</td>
					<td><input type='text' name='sub' class='lline' /></td>
				</tr>
				
				<tr>
					<td>판매사</td>
					<td>
					<input type=hidden id='schMyritzCd' name='myritzCd' class='lline' />
					<input type=text id='schMyritzNm' name=myritzNm class=lline value="">
					
					<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/myritzSearchPopup', 600, 500);" />
					
					</td>
					
				</tr>
				<tr>
					<td>승인/반려</td>
					<td>
					 승인 <input type="radio" name='approvalStatus' value="2" > 
					 반려 <input type="radio" name='approvalStatus' value="3" >
					</td>
				</tr>
				<tr>
					<td>관리자메모</td>
					<td><input type="text" name='brandMemo' value="${brandVO.brandObj.brandMemo }" class='lline' > </td>
				</tr>
			</table>
			</c:otherwise>
		</c:choose>
		
	<div class="button"><img src="/resources/shop/admin/img/btn_modify.gif" onclick="godSubmit();" style="cursor:hand;"></div>

</form>

	<div id=MSG01 >
		<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>상품브랜드탐색기에서 TOP (최상위)를 누르면 브랜드를 생성 할 수 있습니다.</td></tr>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>브랜드페이지상단에 특성에 맞는 이벤트나 배너를 배치하여 차별화될 수 있게 디자인 해보세요.</td></tr>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>브랜드 순서변경은 해당브랜드를 선택후 키보드의 상하이동키↓↑로 조정하고 수정을 눌러 저장합니다.</td></tr>
		</table>
	</div>
	
	<script>cssRound('MSG01')</script>

	<script>
		table_design_load();
		
		if("sub" == '${brandVO.focus}' ){
			out.println("document.form.sub.focus();");
		}
	</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>

