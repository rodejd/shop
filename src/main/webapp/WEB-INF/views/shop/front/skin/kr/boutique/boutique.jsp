<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<script type="text/javascript">
function goPage(page){
	$('#pageNo').val(page);
	document.frmList.submit();
}
</script>

<form name="frmList" id="frmList" action="${ctx}/shop/boutique">
	<input type="hidden" name="pageNo" id="pageNo" value="${pageNo != '' ? frontBoutiqueVO.pageNo : '1'}">
	<input type="hidden" name="pageSize" value="${frontBoutiqueVO.pageSize }">
	
	<div class="global-wrapper clearfix" id="global-wrapper">
	 	<div class="top_tit_ty01">
			<div class="tit_dp01">BOUTIQUE LIVE</div>
	
			<div class="boutique_list" style="padding-bottom:0;">
				<ul>
					<c:forEach items="${frontBoutiqueVO.boutiqueList}" var="list" varStatus="status">
						<li>
							<div class="bx_thum">
								<img src="${list.img}" class="img_pc" alt=""/>
								<img src="${list.imgm}" class="img_mob" alt=""/>
							</div>
							<dl>
								<dt>${list.title}</dt>
								<dd>${fn:replace(list.content, newLineChar, "<br/>")}</dd>
							</dl>
							<a href="${list.linkaddr}" target="${list.target}">${list.linkaddr}</a>
						</li>
					</c:forEach>
				</ul>
				<div style="clear:both;"></div>
			</div>
				
			<!-- 페이징 -->
			<div class="text-center pg_pd">
				<nav>
					<ul class="pagination category-pagination">
						<tags:frontPaginator currentPageNo="${frontBoutiqueVO.pageNo}" rowCount="${frontBoutiqueVO.rowCount}" pageSize="${frontBoutiqueVO.pageSize}"  pageGroupSize="${frontBoutiqueVO.pageGroupSize}" />

					</ul>
				</nav>
			</div>
		</div>
	</div>
</form>
<br>