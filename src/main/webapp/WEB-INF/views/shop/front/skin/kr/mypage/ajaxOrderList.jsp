<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">
	
	<%-- 페이징 처리 --%>
	function goPage(page){
		orderLoad(page,$("#ordno").val());
	}

</script>
    
    <c:if test="${empty myOrderVO.orderList }">
    			   
			   		<article class="product-review" onclick="view_content(${status.index});">
			   			<div>
		   	   			<h5 class="product-review-title" style="text-align:center;">
	   		            주문하신 주문번호가 존재하지 않습니다.
	   		            </h5>
	   		        	</div>
	   		   		</article>
    </c:if>
    
    <c:if test="${not empty myOrderVO.orderList}">
    
    	<c:forEach items="${myOrderVO.orderList }" var="list" >
					<article class="product-review" >
					   <div class="product-review-author"><input type="radio" name="ord" onclick="order_put('${list.ordno}')"/></div>
					   <div>
					   <h5 class="product-review-title">
					   ${list.ordno } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   ${list.goodsnmKR } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   </h5>
					   <p class="product-review-meta" style="margin-left:30px;">
					   수량 : ${list.ea }개&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   주문금액 : ${list.settleprice }원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   주문일 : ${list.orddt } 
	               	   </p>
					   </div>
				   </article>    	
    	</c:forEach>

    </c:if>
    
    
	<div class="row">
		<div class="col-md-12">
			<nav class="text-center">
				<ul class="pagination category-pagination">
					<tags:frontPaginator currentPageNo="${myOrderVO.pageNo}" rowCount="${myOrderVO.rowCount}" pageSize="${myOrderVO.pageSize}"  pageGroupSize="${myOrderVO.pageGroupSize}" />
				</ul>	
			</nav>
		</div>
	</div>    
    		

