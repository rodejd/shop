<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script type="text/javascript">
function questsubmit(){
	var schSellerCd = document.getElementById("schSellerNm").value;
	if(''==schSellerCd){
		alert("판매사명을 선택해 주세요");
	}else{
		if(confirm("등록 하시겠습니까?")){
		document.form.submit();	
		}
		return false;
	}
}


</script>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left: 12px">
			<div class="title title_top">
				관리자 질문
				<ul>
					<li><span>판매자에게 궁금한 사항을 질문합니다.</span></li>
				</ul>
			</div><br/><br/>
			<form id=form name=form action="adminQuestindb">
			<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<colgroup>
				
					<col class="cellC">
					<col class="cellL">
					<col class="cellC">
					<col class="cellL">
				</colgroup>
				<tbody>
					<tr>
						<td>판매자</td>
						<td colspan="3">
						<input type=hidden id=schSellerCd name=questionVO.sellerCd class=lline  readonly="readonly" >
						<input type=text id=schSellerNm  class=lline  readonly="readonly" >
						<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
					</td>
					</tr>
					<tr>
						<td>제목</td>
						<td colspan="3"> 
						<input type="text" id=title name=questionVO.title class=lline maxlength="200">
						</td>
					</tr>
					
					<tr>
						<td>질문사항</td>
						<td colspan="3">
							<textarea id="questionVO.contents" name="questionVO.contents"
							 style="width:100%;height:150px" maxlength="200"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
				
			<div style="padding:20px" align="center" class="noline">
				<div class="button">
					<img id="btn_register" src="/resources/shop/admin/img/btn_regist.gif" onClick="questsubmit()" style="cursor:pointer">					
				</div>
			</div>
			
			</form>
		</td>
	</tr>
</table>

<script>
linecss();
table_design_load();
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>