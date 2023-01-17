<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : goods_view.jsp
* 생성일 : 2017. 02. 17
* 작성자 : 이병환
* 설   명 : skin default1 사용자 상품 상세 조회
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170217			이병환				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ taglib uri="/WEB-INF/tlds/function_stringUtil.tld" prefix="stringUtil"%>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/magnific.js"></script>
<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/custom.js"></script>

<script type="text/javascript">
$(function(){
	
});
</script>
	<c:forEach var="list" items="${frontGoodsReviewVO.goodsReviewList}" varStatus="status">
		<li ${status.last ? 'class="last"' : ''}>
			<div class="bx_thum01"><img src="${list.imgs}" alt="" style="width: 100%; height: 100%;"/></div>
			<div class="in_ct01">
				<p class="tx_brand">
					<c:if test="${list.brandno ne 0 }">
						${shopLibFunction:getGoodsBrandName(list.brandno)}
					</c:if>
				</p>
				<p class="tx_tit">${list.goodsnmKR}</p>
				<p class="tx_num">${list.binCd}</p>
				<p class="bx_star">
					<c:forEach begin="1" end="${list.point}" step="1">
						<i class="fa fa-star"></i>
					</c:forEach>
				</p>
			</div>
			<div class="in_ct02">
				<div class="tb_out">
					<div class="tb_in">
						${list.contents}
					</div>
				</div>
			</div>
			<div class="in_ct03">
				<div class="tb_out">
					<div class="tb_in">
						${stringUtil:getMask(list.mid,7)}<br/>
						<fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/>
					</div>
				</div>
			</div>
			<c:if test="${not empty list.reviewimg}">
				<div class="bx_thum02">
					<a href="#" class="p_r_view" data-no="${list.sno}"><img src="${list.reviewimg}" alt="" style="width: 100%; height: 100%;"/></a>
					<span style="position:absolute; right:0; bottom:0; width:20px; height:20px; color:#fff; line-height:20px; background:#000; text-align:center;">+</span>
				</div>
			</c:if>
			<div style="clear:both;"></div>
		</li>
	</c:forEach>