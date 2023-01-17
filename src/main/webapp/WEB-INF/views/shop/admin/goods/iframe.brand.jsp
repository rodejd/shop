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
	$(function(){
		ajaxCallJson("/shop/admin/goods/brand/brandCount.dojson"
				, ''
				, function(result){
					var gdGoodsBrand = result.gdGoodsBrand;
					parent.fnSetlistCount(gdGoodsBrand.tCount, gdGoodsBrand.uCount, gdGoodsBrand.nCount);
				});
	});
	
	var btnSubmit = false;
	/* 2017-08-22 추가 */
	function emptySub(){
		if(document.form.brandno.value == 0) {
			if(document.form.sub.value == ""){
				alert("브랜드명을 입력하세요.");
				return false;
			}	
		}
		//관리자가 생성한 브랜드 수정 막는 이유를 몰라 주석처리
		/* if(document.form.sellerCd.value ==0 || document.form.sellerCd.value ==""){
			if(document.form.approvalStatus.value !='2'){
				alert("판매업체가 제공한 브랜드가 아니라면 승인으로만 가능합니다.");
				return false;
			}
		} */
		return true;
	}

	function godSubmit(){
		if(btnSubmit){
			alert("저장중입니다...");
			return;
		}
		if(emptySub()){
			btnSubmit = true;
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

<form name=form method=post enctype="multipart/form-data" action="brand/indb">
	<input type=hidden name=mode value="mod_brand">
	<input type=hidden name=brandno value="${brandVO.brandno}">
	<input type=hidden name=infoyn value="${brandVO.infoyn}">
	<input type=hidden name=sno value="${empty brandVO.brandObj.sno ? 0 : brandVO.brandObj.sno}">
	<input type=hidden name=oldImgPC value="${brandVO.brandObj.imgPC}">
	<input type=hidden name=oldImgMO value="${brandVO.brandObj.imgMO}">

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
				<a href="../../goods/goods_brand?brandno=${brandVO.brandObj.sno}" target=_blank><img src="/resources/shop/admin/img/i_nowview.gif" border=0 align=absmiddle hspace=10></a>
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
					<td>브랜드명(영문)</td>
					<td>
						<input type=text name=brandnm class=lline value="${brandVO.brandObj.brandnm}"  required >
						&nbsp; 브랜드코드 : <b>${brandVO.brandObj.sno}</b>
					</td>
				</tr>
				<tr>
					<td>브랜드명(국문)</td>
					<td>
						<input type=text name=brandnmKR class=lline value="${brandVO.brandObj.brandnmKR}" >
					</td>
				</tr>
				<tr>
					<td>브랜드명(중문)</td>
					<td>
						<input type=text name=brandnmCN class=lline value="${brandVO.brandObj.brandnmCN}" >
					</td>
				</tr>
				<%-- 
				<tr>
					<td>판매사</td>
					<td>
						<input type=hidden id=schSellerCd name=sellerCd class=lline value="${brandVO.brandObj.sellerCd}"   >
						<input type=text id=schSellerNm name=sellername class=lline value="${brandVO.brandObj.sellerNm }"readonly="readonly">
					</td>
				</tr>
				--%>
				<tr>
					<td>사용여부</td>
					<td>
						<label for="approvalStatus1"> 사용 <input type="radio" id="approvalStatus1" name='approvalStatus' value="2" ${brandVO.brandObj.approvalStatus eq 2 ? 'checked' : ''} > </label> 
						<label for="approvalStatus2"> 미사용 <input type="radio" id="approvalStatus2" name='approvalStatus' value="3" ${brandVO.brandObj.approvalStatus eq 3 ? 'checked' : ''}> </label>
					</td>
				</tr>
				<tr>
					<td>연관 키워드</td>
					<td>
						<input type="text" name='brandMemo' value="${brandVO.brandObj.brandMemo }" class='lline' > 
					</td>
				</tr>
				<tr>
					<td>PC 브랜드 배너</td>
					<td>
						<input type="file" id="brandImgPC" name="brandImgPC" ${empty brandVO.brandObj.imgPC ? 'required' : '' } accept=".gif, .jpg, .png, .jpge, .bmp" >
						<c:if test="${not empty brandVO.brandObj.imgPC}">
							<div style="margin: 5px 0;">
								<a href="${brandVO.brandObj.imgPC}" target="_blank">${brandVO.brandObj.imgPC}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>MO 브랜드 배너</td>
					<td>
						<input type="file" id="brandImgMO" name="brandImgMO" ${empty brandVO.brandObj.imgMO ? 'required' : '' } accept=".gif, .jpg, .png, .jpge, .bmp" >
						<c:if test="${not empty brandVO.brandObj.imgMO}">
							<div style="margin: 5px 0;">
								<a href="${brandVO.brandObj.imgMO}" target="_blank">${brandVO.brandObj.imgMO}</a>
							</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>베스트 여부</td>
					<td>
						<label for="bestY"> Y <input type="radio" id="bestY" name='bestYn' value="Y" ${brandVO.brandObj.bestYn eq 'Y' ? 'checked' : ''}> </label> 
						<label for="bestN"> N <input type="radio" id="bestN" name='bestYn' value="N" ${brandVO.brandObj.bestYn eq 'N' ? 'checked' : ''}> </label>
					</td>
				</tr>
				<tr>
					<td>브랜드 삭제 </td>
					<td><a href="javascript:popupLayer('popup.delBrand?brandno='+${brandVO.brandno});"><img src="/resources/shop/admin/img/i_del.gif" border=0 align=absmiddle></a>
							<!-- <a href="javascript:if (document.form.brand.value) popupLayer('popup.delBrand?brand='+document.form.brand.value);else alert('전체브랜드는 삭제대상이 아닙니다');"><img src="../img/i_del.gif" border=0 align=absmiddle></a> --></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>브랜드명(영문)</td>
					<td><input type='text' name='sub' class='lline' /></td>
				</tr>
				<tr>
					<td>브랜드명(국문)</td>
					<td><input type='text' name='subKR' class='lline' /></td>
				</tr>
				<tr>
					<td>브랜드명(중문)</td>
					<td><input type='text' name='subCN' class='lline' /></td>
				</tr>
				<%--
				<tr>
					<td>판매사</td>
					<td>
						<input type=hidden id='schSellerCd' name='sellerCd' class='lline' />
						<input type=text id='schSellerNm' name=sellerNm class=lline value="">
						<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
					</td>
				</tr>
				 --%>
				<tr>
					<td>사용여부</td>
					<td>
						<label for="approvalStatus1"> 사용 <input type="radio" id="approvalStatus1" name='approvalStatus' value="2" > </label> 
						<label for="approvalStatus2"> 미사용 <input type="radio" id="approvalStatus2" name='approvalStatus' value="3" > </label>
					</td>
				</tr>
				<tr>
					<td>연관 키워드</td>
					<td><input type="text" name='brandMemo' value="${brandVO.brandObj.brandMemo }" class='lline' > </td>
				</tr>
				<tr>
					<td>PC 브랜드 배너</td>
					<td><input type="file" id="brandImgPC" name="brandImgPC" ${empty brandVO.brandObj.brandImgPC ? 'required' : '' } accept=".gif, .jpg, .png, .jpge, .bmp" ></td>
				</tr>
				<tr>
					<td>MO 브랜드 배너</td>
					<td><input type="file" id="brandImgMO" name="brandImgMO" ${empty brandVO.brandObj.brandImgMO ? 'required' : '' } accept=".gif, .jpg, .png, .jpge, .bmp" ></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<div class="button"><img src="/resources/shop/admin/img/btn_modify.gif" onclick="godSubmit();" style="cursor:hand;"></div>

</form>

	<div id=MSG01 >
		<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
			<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>상품브랜드탐색기에서 TOP (최상위)를 누르면 브랜드를 생성 할 수 있습니다.</td></tr>
			<!-- tr><td><img src="../img/icon_list.gif" align=absmiddle>브랜드페이지상단에 특성에 맞는 이벤트나 배너를 배치하여 차별화될 수 있게 디자인 해보세요.</td></tr>
			<tr><td><img src="../img/icon_list.gif" align=absmiddle>브랜드 순서변경은 해당브랜드를 선택후 키보드의 상하이동키↓↑로 조정하고 수정을 눌러 저장합니다.</td></tr-->
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

