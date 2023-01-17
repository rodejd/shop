<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">
function goodsCart(addopt, goodsno, stock, usegoodsadd, optno){
	if(addopt > 0 && usegoodsadd > 0){
		alert("이 상품은 옵션이 있는 상품 입니다. 상품상세페이지에서 옵션을 선택해주세요.");
		return;
	}else if(stock < 1){
		alert("죄송합니다 현재 상품은 품절되었습니다.");
		return;
	}else{
		// 장바구니 추가 기능 . ajax 
		setParams(goodsno,optno);
		var formData = new FormData($("#frmList")[0]);
		$.ajax({
			url: 'goods_cart',
			type:"POST",
			data: formData,
			processData:false,
			contentType:false,
			success:function(data){
				var goCart = confirm('상품이 장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?');
				if(goCart){
					frmList.mode.value ='list';
					$('#frmList').attr("action", "goods_cart");
					document.frmList.submit();
				}
			},
			error:function(request, status, error){
				alert("잠시후 다시 시도해주세요.");
			}
		});
	}
}

function like(obj, goodsno) {
	var checkLogin = ${shop_so.isShopLogin()};
	if (checkLogin) {
		var like = false;
		var src = $(obj).children('img').attr('src');
		if (src.indexOf("icon_heart.png") > -1) {
			$(obj).children("img").attr("src", src.replace("icon_heart.png", "icon_heart_on.png"));
			like = true;
		} else {
			$(obj).children("img").attr("src", src.replace("icon_heart_on.png", "icon_heart.png"));
		}
		
		ajaxCallJson("likeGoods.dojson", {
			'like': like,
			'goodsno': goodsno
		}, function(data){
		}, function(e){
			console.log(e);
		});
	} else {
		alert("로그인 후에 이용해 주시기 바랍니다.");
		return;
	}
}

<%-- param 설정 --%>
function setParams(num, optno){
	var options_list = '';
	
	var goodsno = num;
	var opt = optno;
	var addopt = 'NULL';
	var ea = '1';
	options_list = goodsno + '|||' + opt + '|||' + addopt + '|||' + ea;
	
	frmList.optionsList.value = options_list;

}
</script>

<form name="frmList" id="frmList" method="post" action="${ctx}/shop/promotion/promotion?pmno=${frontPromotionVO.pmno}" style="margin-bottom:0;">
	<input type="hidden" name="mode" value="addItem">
	<input type="hidden" name="optionsList" value="">
	
 	<div class="global-wrapper clearfix" id="global-wrapper">
	 	<div class="top_tit_ty01">
			<div class="tit_dp01">${frontPromotionVO.promotionObj.copy1}</div>
			<!-- <div class="">${frontPromotionVO.promotionObj.copy2}</div> -->
		</div>

		<c:forEach var="group" items="${frontPromotionVO.groupList}">
			<c:if test="${!empty group.goodsList && fn:length(group.goodsList) > 0}">
				<div class="x-goods-list container_list">
					<div class="tab_ty01">
						<ul>
							<c:forEach var="titGroup" items="${frontPromotionVO.groupList}">
								<c:if test="${!empty titGroup.goodsList && fn:length(titGroup.goodsList) > 0}">
									<li><a href="#${titGroup.grnm}" class='<c:if test="${titGroup.grnm eq group.grnm}">on</c:if>'>${titGroup.grnm}</a></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					
					<div id="${group.grnm}">
						<div class="bx_total_num">
							<p class="tit_ty01">${group.grnm}</p><!-- 총 ${stringUtil:getMoneyFormatInteger(fn:length(group.goodsList))}개의 상품 -->
						</div>

						<div class="sub_list02">
							<ul data-gutter="15">
								<c:forEach var="gdList" items="${group.goodsList}" varStatus="status">   
									<li>
										<div class="in_bx">
											<div class="bx_thum">
												<div class="">
													<a href="javascript:like(this, ${gdList.goodsno});" class="btn_heart"><img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/icon_heart${gdList.likes > 0 ? '_on' : ''}.png" alt="" /></a>
													<img class="product-img-primary product-img" src="${fn:split(gdList.imgs,'|')[0]}" alt="${gdList.goodsnmKR}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/200x200.png'" />
												</div>
											</div>
											<a class="product-link" href="${ctx}/shop/goods/goods_view?goodsno=${gdList.goodsno}&category=${gdList.category}"></a>
											<div class="bx_info">
												<div class="bx_ticket">
													<span class="tk_ty01">NEW</span>
													<!--  <c:if test="${gdList.euYn eq 'Y'}"><span class="tk_ty02">FTA</span></c:if> -->
													<c:if test="${gdList.hotYn eq 'Y'}"><span class="tk_ty01">HOT</span></c:if>
													<c:if test="${gdList.vipYn eq 'Y'}"><span class="tk_ty03">VIP</span></c:if>
												</div>
												<div class="tx_brand">${gdList.brandnm}</div>
												<div class="tx_tit">${gdList.goodsnmKR}</div>
												<c:choose>
													<c:when test="${0 != gdList.stock && 0 == gdList.runout}">
														<fmt:parseNumber var="priceRate" type="number" value="${gdList.priceRate}" />
														<div class="tx_price01">${shopLibFunction:getExchange(gdList.price, wskin)}<span class="tx_per"><c:if test="${priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${gdList.priceRate}"/>%</c:if></span></div>
														<div class="tx_price02"><c:if test="${priceRate >= 3}">${shopLibFunction:getExchange(gdList.consumer, wskin)}</c:if></div>
													</c:when>
													<c:otherwise>
														<span class="sold-out">품절</span>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
							<div style="clear:both;"></div>
						</div>
					</div>
				</div>
	        </c:if>
		</c:forEach>
	</div>
</form>
<br>
 
<script type="text/javascript">
$(function() {
	/* 2017-09-19 이미지 리사이즈 추가 */
	$('.product-img').on('load', function() {
		$('.product-img').each(function() {
			var width = 	$(this).width();
			$(this).height(width);
		});
	});	
});

/* 2017-08-22 추가 div class height 수정 */
$(window).load(function(){
	//height 최대값으로 class height을 맞춰준다
	var maxHeight = -1;
	$('.product').each(function(){
		maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
	});
	$('.product').height(maxHeight);
	
});

$(window).resize(function (){
	var maxHeight = -1;
	$('.product').css('height', 'auto');	//초기화
	$('.product').each(function(){
		maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
	});
	$('.product').height(maxHeight);
})
</script>