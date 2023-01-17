<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<script src="https://collection.sauceflex.com/static/js/SauceWebLib.js"></script>
<!-- <script src="https://stage.collection.sauceflex.com/static/js/SauceWebLib.js"></script> -->

<script type="text/javascript">
	$(function(){
		var sauceFlex = SauceWebLib({
			id: 'sauce_collect',
			accessToken: "${token}",
			partnerId: '${partnerId}',
			autoPlay:true,
			});
	});
</script>


	<div class="global-wrapper clearfix" id="global-wrapper">
	 	<div class="top_tit_ty01">
			<!-- <div class="tit_dp01"><img src="/resources/images/ritz_live.png" style="height:35px"></div> -->
	
			<div class="" style="padding-bottom:0;">
				<form id="sfForm" name="sfForm">
					<div id="sauce_collect"></div>
				</form>
			</div>
		</div>
	</div>
