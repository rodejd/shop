 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>
<c:set var = "stepStock" value="${(orderVO.stepStock == null or orderVO.stepStock eq '')? '0' : orderVO.stepStock }"></c:set>
<c:set var = "deliveryBasis" value="${(orderVO.deliveryBasis == null or orderVO.deliveryBasis eq '')? '0' : orderVO.deliveryBasis }"></c:set>
<c:set var = "autoCancel" value="${(orderVO.autoCancel == null or orderVO.autoCancel eq '')? '0' : orderVO.autoCancel  }"></c:set>
<c:set var = "autoCancelFail" value="${(orderVO.autoCancelFail == null or orderVO.autoCancelFail eq '')? '0' : orderVO.autoCancelFail  }"></c:set>
<c:set var = "orderPeriod" value="${(orderVO.orderPeriod == null or orderVO.orderPeriod eq '')? '0' : orderVO.orderPeriod  }"></c:set>
<c:set var = "orderPageNum" value="${(orderVO.orderPageNum == null or orderVO.orderPageNum eq '')? '0' : orderVO.orderPageNum  }"></c:set>
<%-- <c:out value="${stepStock} ${deliveryBasis} ${autoCancel} ${autoCancelFail} ${orderPeriod} ${orderPageNum}"></c:out>
<%
	// 재고삭감단계
	String stepStock = cfg("stepStock") == null || "".equals(cfg("stepStock"))? "0" : cfg("stepStock") ;

	// 한개의 송장번호만 입력
	String basis = cfg("delivery_basis") == null || "".equals(cfg("delivery_basis")) ? "0" : cfg("delivery_basis") ;
%>  --%>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
		
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<form method="post" action="order_set/indb">
<input type="hidden" name="mode" value="orderSet">
	<div class="title title_top">재고삭감/자동주문취소 설정 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=17',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
	</div>
	<table class=tb>
	<col class=cellC><col class=cellL>
	<tr>
		<td>재고삭감단계</td>
		<td class=noline>  <c:if test = ""></c:if>
		<c:choose>
			<c:when test="${stepStock eq '0' }">
				<input type=radio name=stepStock value="0"  checked> 주문시 <font class=extext>(주문접수단계)</font>
			</c:when>
			<c:otherwise>
				<input type=radio name=stepStock value="0"  > 주문시 <font class=extext>(주문접수단계)</font>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${stepStock eq '1' }">
				<input type=radio name=stepStock value="1"  checked> 입금시 <font class=extext>(입금확인단계)</font>
			</c:when>
			<c:otherwise>
				<input type=radio name=stepStock value="1"  > 입금시 <font class=extext>(입금확인단계)</font>
			</c:otherwise>
		</c:choose>
		
		
		</td>
	</tr>
	<tr>
		<td>주문자동취소 설정</td>
		<td>
		주문 이후 <input type="text" name="autoCancel" size="2" value="${autoCancel}" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="right line" maxlength="3"> 일동안 입금하지 않은 주문을 자동으로 주문취소합니다.
		<span class=small><font class=extext>'0'이나 공백으로 설정시 기능사용 않함</font></span>
		</td>
	</tr>
	<tr>
		<td>결제 시도/실패<br>재결제 가능시간</td>
		<td>
		주문 이후 <input type="text" name="autoCancelFail" size="2" value="${autoCancelFail}" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="right line" required maxlength="5"> 시간동안 재결제 가능합니다.
		<span class=small><font class=extext>'1'이상의 값을 넣어야합니다. (기본 24시간. '0'이나 공백입력시 재결제 사용않함)</font></span>
		</td>
	</tr>
	</table>
	
	<div class=title>주문리스트 조회일자 설정 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=17',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
	</div>
	<table class=tb>
	<col class=cellC><col class=cellL>
	<tr>
		<td>주문리스트<br>기본조회기간 설정</td>
		<td>
		'주문관리 > 주문리스트'를 열었을 때 기본조회 기간을 <input type="text" name="orderPeriod" size="2" value="${orderPeriod}" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="right line" maxlength="3"> 일간으로  설정합니다.
		<div class=extext style="padding-top:3px">너무 긴 기간을 입력하면 주문리스트를 열 때마다 많은 부하가 걸릴 수 있습니다. 1일~5일 이내를 권장합니다</div>
		</td>
	</tr>
	</table>
	<div class=title style="display:none">주문리스트의 주문일 출력수 설정 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=17',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
	
	<table class=tb>
	<col class=cellC><col class=cellL>
	<tr>
		<td>한 페이지당<br>주문건 출력수</td>
		<td>주문일로 볼 때 한 페이지당 주문건 <input type="text" name="orderPageNum" size="2" value="${orderPageNum}" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="right line"> 개를 출력합니다.</td>
	</tr>
	</table>
	</div>
	<div class=title>송장입력방법 설정 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=17',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
	</div>
	<table class=tb>
	<col class=cellC><col class=cellL>
	<tr height=40>
		<td>상품별 송장</td>
		<td>한 주문에 상품이 여러개일 경우 &nbsp;&nbsp;
		<c:choose>
			<c:when test ="${deliveryBasis eq '0' }">
				<input type=radio name="deliveryBasis" value='0' class=null checked>한개의 송장번호만 입력&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				<input type=radio name="deliveryBasis" value='0' class=null >한개의 송장번호만 입력&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test ="${deliveryBasis eq '1' }">
				<input type=radio name="deliveryBasis" value='1' class=null checked>상품별로 송장번호를 입력
			</c:when>
			<c:otherwise>
				<input type=radio name="deliveryBasis" value='1' class=null >상품별로 송장번호를 입력
			</c:otherwise>
		</c:choose>
		
		</td>
	</tr>
	</table>
	<div class=button><input type=image src="/resources/shop/admin/img/btn_save.gif"></div>
</form>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
<%@ include file="../common/bottom.jsp" %>
    	</td>
</tr>
</table>
