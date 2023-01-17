<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header_popup.jsp" %>
<body oncontextmenu="return false">
<style type="text/css"><!--
@media print { .notprint {display: none;} } /* 인쇄시 불필요한 부분 비활성화 */
--></style>

<script language="javascript">

</script>


<DIV class="notprint">
<div style="padding-left:63px"><font color=#5B5B5B>※ <font class=small1>세금계산서 인쇄시 직인(도장이미지)도 인쇄되려면 아래와 같이 설정되어 있어야 가능합니다.</div>
<div style="padding-top:3px"></div>
<div style="padding-left:80px">1) 인터넷 익스플로러 : 브라우저의 [도구 메뉴]-[인터넷옵션]-[고급]-[인쇄] 에서 [배경색 및 이미지 인쇄] 체크</div>
<div style="padding-top:3px"></div>
<div style="padding-left:80px">2) 파이어폭스 : 브라우저의 [파일]-[인쇄화면설정]-[용지 및 설정]-[옵션]에서 [배경 인쇄(색상 및 그림)] 체크</div>
<div style="padding-top:10px"></div>
<div align=center><a href="javascript:window.print();"><img src="/resources/shop/admin/img/btn_print.gif" border="0" align="absmiddle"></a></div>
<div style="padding-top:10px"></div>
</div>

<script>
//table_design_load();
//window.print();
</script>


<c:set var="continue" value="false"/>
<c:set var="paperBr" value="0"/>
<c:forEach items="${printVO.paperOrders}" var="paperOrders">
	
	<c:if test="${'0' == paperOrders}">
		<hr class='notprint' style='border-top:dashed 1px #000000;'> <br/>
	</c:if>
	
	<c:if test="${'tax' == printVO.type}">
		
		<input type="hidden" name="ordno" value="${paperOrders}">
		
		<c:if test="${printVO.cnt == 0}">
			<div class='notprint' style='margin:0 40 20 40;'>
				<div style='background-color:#eeeeee; padding: 15px 10px; text-align:center; line-height: 20px;'>
					<font class=small color=#0074BA><strong>${paperOrders}</strong></font>
					 주문은 
					<font color=EA0095>세금계산서를 출력할 수 없습니다.</font>
					<br>
					세금계산서 발행을 신청하지 않았거나, 승인이 안된 상태입니다.
				</div>
			</div>
			<c:set var="continue" value="true"/>
		</c:if>
		
		<jsp:include page="_paper.tax.jsp" flush="true"/>
	</c:if>
		
	<c:if test="${paperBr != 0}">
		<br style='page-break-after:always;'> <br/>
	</c:if>
	
	<c:if test="${'report' == printVO.type}">
		<input type="hidden" name="ordno" value="${paperOrders}"/>
		<%@ include file="_paper.report.jsp"%>
	</c:if>
	
	<c:if test="${'reception' == printVO.type}">
		<input type="hidden" name="ordno" value="${paperOrders}"/>
		<%@ include file="_paper.reception.jsp" %>
	</c:if>
	
	<c:set var="paperBr" value="${paperBr + 1}"/>
</c:forEach>