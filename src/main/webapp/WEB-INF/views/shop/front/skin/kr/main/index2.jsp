<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
						
<c:if test="${null != frontMainVO.fixPopList}">
	<c:set var="fixPopList" value="${frontMainVO.fixPopList}"/>
	<script>
		$(function() {
			/* var layerAttr = "top:${fixPopList.popspotw}px; left:${fixPopList.popspoth}px; display:block;";
			var layerConAttr = 'width:${fixPopList.popsizew}px; height:${fixPopList.popsizeh}px; display:block'; */

			var layerAttr = {
				top: '${fixPopList.popspotw}px',
				left: '${fixPopList.popspoth}px',
				display: 'block'
			};
			var layerConAttr = {
				width: '${fixPopList.popsizew}px',
				height: '${fixPopList.popsizeh}px',
				display: 'block'
			}
			// device detection
			if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|ipad|iris|kindle|Android|Silk|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(navigator.userAgent) 
				|| /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0,4))) { 
				layerAttr.top = '0px';
				layerAttr.left = '0px';
				layerConAttr.width = screen.width || $('body').width();
				layerConAttr.height = 'auto';
				
			}
			$('#fixedLayer').css(layerAttr);
			$('#fixed-container').css(layerConAttr);
			$('#fixedLayer_cont').load("/resources/shop/data/upload/popup/${fixPopList.filename}.html");
		
			$('#fixedLayer_exit').on('click', function() {
				$('#fixed-container').hide();
				$('#fixedLayer').hide();
			});
		});
	</script>
</c:if>	 
			 
<c:if test="${frontMainVO.movePopList != null }">
	<c:set var="movePopList" value="${frontMainVO.movePopList}"/>
	<script>
		$(function() {
			var mLayerAttr = {
				top: '${movePopList.popspotw}px',
				left: '${movePopList.popspoth}px',
				display: 'block'
			};
			var mLayerConAttr = {
				width: '${movePopList.popsizew}px',
				height: '${movePopList.popsizeh}px',
				display: 'block'
			};
			// device detection
			if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|ipad|iris|kindle|Android|Silk|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(navigator.userAgent) 
				|| /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0,4))) { 
				mLayerAttr.top = '0px';
				mLayerAttr.left = '0px';
				mLayerConAttr.width = screen.width || $('body').width();
				mLayerConAttr.height = 'auto';
			} else {
				$('#movingWrapper').draggable();	
			}
			
			$('#movingLayer').css(mLayerAttr);
			$('#moving-container').css(mLayerConAttr)
			$('#movingLayer_cont').load("/resources/shop/data/upload/popup/${movePopList.filename}.html");
		
			$('#movingLayer_exit').on('click', function() {
				$('#moving-container').hide();
				$('#movingLayer').hide();
			})
		});
	</script>
</c:if> 
			
<c:if test="${frontMainVO.winPopList != null }">
	<c:set var="winPopList" value="${frontMainVO.winPopList}"/>
	<%-- <c:forEach items="${frontMainVO.winPopList }" var="winPopList" varStatus="status"> --%>
	<script>
		function obtainCookie(name){ // fixedLayer, no, flowLayer
			var found = false;
			var i = 0;
			var cookieArr = document.cookie.split(';');
			var str = '';
			while( i<= cookieArr.length-1){ 
				start = i;
				end = start + name.length;
				if(cookieArr[i].trim().split("=")[0] == name){
					found = true;
					str = cookieArr[i].trim();
					break;
				};
				i++;
			}
			if(found){
				return str.split('=')[1];
			}else{
				return "";
			};
		}
			
		function openMsgBox(){
			var eventCookie = obtainCookie("popup");
			if(eventCookie != "no"){
				var features = "status=no, menubar=no, scrollbars=no, resizable=no, toolbar=no, location=no, directories=no, Width=${winPopList.popsizew}, Height=${winPopList.popsizeh}, left=${winPopList.popspoth}, top=${winPopList.popspotw}, scrollbars=1";
				var windowOpen = window.open('/resources/shop/data/upload/popup/${winPopList.filename}.html', '${winPopList.filename}', features);		
			};
		}
		openMsgBox();
	</script>
	<%-- </c:forEach>  --%>
</c:if>

<div class="x-index clearfix main_vis_pc" id=""><!-- global-wrapper -->
	<c:if test="${fn:length(frontMainVO.slideBannerList) > 1}">
	<div class="owl-carousel owl-loaded owl-nav-dots-inner main_vis_wrap" data-options='{"items":1,"loop":true,"autoplay":false,"autoplayTimeout":5000}'>
	</c:if>
		<c:forEach var="resultList" items="${frontMainVO.slideBannerList}" varStatus="status">
			<div class="owl-item">
				<a href="${resultList.linkaddr}" target="${resultList.target}">
					<img class="ifrm_prdc_detail banner-o-hid" style="width:100%;height:auto;" src="${resultList.img}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'"/>
				</a>
			</div>
		</c:forEach>
	<c:if test="${fn:length(frontMainVO.slideBannerList) > 1}">
	</div>
	</c:if>
</div>

<div class="x-index clearfix main_vis_mob" id=""><!-- global-wrapper -->
	<c:if test="${fn:length(frontMainVO.slideBannerList) > 1}">
	<div class="owl-carousel owl-loaded owl-nav-dots-inner main_vis_wrap" data-options='{"items":1,"loop":true,"autoplay":true,"autoplayTimeout":5000}'>
	</c:if>
		<c:forEach var="resultList" items="${frontMainVO.slideBannerList}" varStatus="status">
			<div class="owl-item">
				<a href="${resultList.linkaddr}" target="${resultList.target}">
					<img class="ifrm_prdc_detail banner-o-hid" style="width:100%;height:auto;" src="${resultList.imgMobile}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'"/>
				</a>
			</div>
		</c:forEach>
	<c:if test="${fn:length(frontMainVO.slideBannerList) > 1}">
	</div>
	</c:if>
</div>

<div class="x-index main_container">
	<!-- Today, HOT 100 -->
	<c:set var="mgd_01" value='${fn:split( shopLibFunction:getProperty("main_goods_display_01"), "|") }'/>
	<c:if test="${mgd_01[0] == 'on' }">
		<div class="main_tit">
			<h3>
				${mgd_01[1]}
				<a href="/shop/goods/hot?schType=bags" class="btn_main_more">MORE</a>
			</h3>
			<!-- <p class="bt_tx01">Enjoy Petit Shopping Under $150</p> -->
		</div>
		
		<link rel="stylesheet" href="/resources/shop/data/skin/kr/css/swiper.min.css">
		<script src="/resources/shop/data/skin/kr/js/swiper.min.js"></script>
		<div class="mn_sc sc_hot100_1" data-options='{"items":${mgd_01[4]},"loop":true,"nav":true}' style="position:relative; margin:0 -9px 0 -9px;"><!-- owl-carousel owl-loaded owl-nav-out -->
			<div class="swiper-wrapper">
				<!-- 반복문 forEach -->
				<c:forEach var="hot_gd" items="${frontMainVO.hotGoods1List}" varStatus="status">
				<div class="main_list01 swiper-slide"> 
					<div class="" style="position:relative;"> 
						<div class="in_bx">
							<ul class="product-labels"></ul>
							<div class="product-img-wrap">
								<c:if test="${mgd_01[3] == 'img_i' }">
								<img class="product-img" src="${hot_gd.imgi}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
								<c:if test="${mgd_01[3] == 'img_s' }">
								<img class="product-img" src="${hot_gd.imgs}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
							</div>
						</div>
						<!-- 문제의 반복되는 상품! -->
						<a class="product-link" href="${ctx}/shop/goods/goods_view?goodsno=${hot_gd.goodsno}&category=${hot_gd.category}"></a>
						<div class="product-caption">
							<div class="tx_brand">${shopLibFunction:getGoodsBrandName(hot_gd.brandno)}</div>
							<div class="tx_tit">${hot_gd.goodsnmKR}</div>
							<div class="tx_price01">${shopLibFunction:getExchange(hot_gd.price, wskin)}<span class="tx_per"><c:if test="${hot_gd.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${hot_gd.priceRate}"/>%</c:if></span></div>
							<div class="tx_price02"><c:if test="${hot_gd.priceRate >= 3}">${shopLibFunction:getExchange(hot_gd.consumer, wskin)}</c:if></div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="swiper-pagination" style="display:none;"></div>
			<div class="button arrs">
				<span class="swiper-button-prev" style="cursor:pointer;">이전</span>
				<span class="line"></span>
				<span class="swiper-button-next" style="cursor:pointer;">다음</span>
			</div>
			<script>
			var swiper = new Swiper('.sc_hot100_1', {
				slidesPerView: 'auto',
				spaceBetween: 0,
				pagination: {
					el: '.swiper-pagination',
				clickable: true,
				},
				navigation: {   // 버튼 사용자 지정
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
			});
			</script>
			<div class="cb"></div>
		</div>
	</c:if>
	<!-- //Today, HOT 100 -->
	<!-- Today, HOT 100 -->
	<c:set var="mgd_02" value='${fn:split( shopLibFunction:getProperty("main_goods_display_02"), "|") }'/>
	<c:if test="${mgd_02[0] == 'on' }">
		<div class="main_tit mob_bk">
			<h3>
				<a href="/shop/goods/hot?schType=shoes" class="btn_main_more">MORE</a>
			</h3>
		</div>
		
		<link rel="stylesheet" href="/resources/shop/data/skin/kr/css/swiper.min.css">
		<script src="/resources/shop/data/skin/kr/js/swiper.min.js"></script>
		<div class="mn_sc sc_hot100_2" data-options='{"items":${mgd_02[4]},"loop":true,"nav":true}' style="position:relative; margin:0 -9px 0 -9px;"><!-- owl-carousel owl-loaded owl-nav-out -->
			<div class="swiper-wrapper">
				<!-- 반복문 forEach -->
				<c:forEach var="hot_gd" items="${frontMainVO.hotGoods2List}" varStatus="status">
				<div class="main_list01 swiper-slide"> 
					<div class="" style="position:relative;"> 
						<div class="in_bx">
							<ul class="product-labels"></ul>
							<div class="product-img-wrap">
								<c:if test="${mgd_02[3] == 'img_i' }">
								<img class="product-img" src="${hot_gd.imgi}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
								<c:if test="${mgd_02[3] == 'img_s' }">
								<img class="product-img" src="${hot_gd.imgs}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
							</div>
						</div>
						<!-- 문제의 반복되는 상품! -->
						<a class="product-link" href="${ctx}/shop/goods/goods_view?goodsno=${hot_gd.goodsno}&category=${hot_gd.category}"></a>
						<div class="product-caption">
							<div class="tx_brand">${shopLibFunction:getGoodsBrandName(hot_gd.brandno)}</div>
							<div class="tx_tit">${hot_gd.goodsnmKR}</div>
							<div class="tx_price01">${shopLibFunction:getExchange(hot_gd.price, wskin)}<span class="tx_per"><c:if test="${hot_gd.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${hot_gd.priceRate}"/>%</c:if></span></div>
							<div class="tx_price02"><c:if test="${hot_gd.priceRate >= 3}">${shopLibFunction:getExchange(hot_gd.consumer, wskin)}</c:if></div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="swiper-pagination" style="display:none;"></div>
			<div class="button arrs">
				<span class="swiper-button-prev" style="cursor:pointer;">이전</span>
				<span class="line"></span>
				<span class="swiper-button-next" style="cursor:pointer;">다음</span>
			</div>
			<script>
			var swiper = new Swiper('.sc_hot100_2', {
				slidesPerView: 'auto',
				spaceBetween: 0,
				pagination: {
					el: '.swiper-pagination',
				clickable: true,
				},
				navigation: {   // 버튼 사용자 지정
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
			});
			</script>
			<div class="cb"></div>
		</div>
	</c:if>
	<!-- //Today, HOT 100 -->
	<!-- Today, HOT 100 -->
	<c:set var="mgd_03" value='${fn:split( shopLibFunction:getProperty("main_goods_display_03"), "|") }'/>
	<c:if test="${mgd_03[0] == 'on' }">
		<div class="main_tit mob_bk">
			<h3>
				<a href="/shop/goods/hot?schType=clothing" class="btn_main_more">MORE</a>
			</h3>
		</div>
		
		<link rel="stylesheet" href="/resources/shop/data/skin/kr/css/swiper.min.css">
		<script src="/resources/shop/data/skin/kr/js/swiper.min.js"></script>
		<div class="mn_sc sc_hot100_3" data-options='{"items":${mgd_03[4]},"loop":true,"nav":true}' style="position:relative; margin:0 -9px 0 -9px;"><!-- owl-carousel owl-loaded owl-nav-out -->
			<div class="swiper-wrapper">
				<!-- 반복문 forEach -->
				<c:forEach var="hot_gd" items="${frontMainVO.hotGoods3List}" varStatus="status">
				<div class="main_list01 swiper-slide"> 
					<div class="" style="position:relative;"> 
						<div class="in_bx">
							<ul class="product-labels"></ul>
							<div class="product-img-wrap">
								<c:if test="${mgd_03[3] == 'img_i' }">
								<img class="product-img" src="${hot_gd.imgi}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
								<c:if test="${mgd_03[3] == 'img_s' }">
								<img class="product-img" src="${hot_gd.imgs}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
							</div>
						</div>
						<!-- 문제의 반복되는 상품! -->
						<a class="product-link" href="${ctx}/shop/goods/goods_view?goodsno=${hot_gd.goodsno}&category=${hot_gd.category}"></a>
						<div class="product-caption">
							<div class="tx_brand">${shopLibFunction:getGoodsBrandName(hot_gd.brandno)}</div>
							<div class="tx_tit">${hot_gd.goodsnmKR}</div>
							<div class="tx_price01">${shopLibFunction:getExchange(hot_gd.price, wskin)}<span class="tx_per"><c:if test="${hot_gd.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${hot_gd.priceRate}"/>%</c:if></span></div>
							<div class="tx_price02"><c:if test="${hot_gd.priceRate >= 3}">${shopLibFunction:getExchange(hot_gd.consumer, wskin)}</c:if></div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="swiper-pagination" style="display:none;"></div>
			<div class="button arrs">
				<span class="swiper-button-prev" style="cursor:pointer;">이전</span>
				<span class="line"></span>
				<span class="swiper-button-next" style="cursor:pointer;">다음</span>
			</div>
			<script>
			var swiper = new Swiper('.sc_hot100_3', {
				slidesPerView: 'auto',
				spaceBetween: 0,
				pagination: {
					el: '.swiper-pagination',
				clickable: true,
				},
				navigation: {   // 버튼 사용자 지정
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
			});
			</script>
			<div class="cb"></div>
		</div>
	</c:if>
	<!-- //Today, HOT 100 -->

	<section class="main_banner_ty01">
		<div class="">
			<div class="row">
			    <div class="col-md-6">
					<c:if test="${not empty frontMainVO.promotion1}">
				    	<div class="banner banner-o-hid">
					        <a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion1.pmno}">
				        		<img class="" style="width:100%;height:auto;" src="${frontMainVO.promotion1.pcImg}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/560x200.png'"/>
				        	</a>
							<div class="bt_info">
								<p class="tx_brand">${frontMainVO.promotion1.copy1}</p>
								<p class="tx_tit">${frontMainVO.promotion1.copy2}</p>
								<a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion1.pmno}" class="btn_shop_now">Shop more</a>
							</div>
			        	</div>
					</c:if>
			    </div>
			    <div class="col-md-6">
					<c:if test="${not empty frontMainVO.promotion2}">
				    	<div class="banner banner-o-hid">
					        <a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion2.pmno}">
				        		<img class="" style="width:100%;height:auto;" src="${frontMainVO.promotion2.pcImg}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/560x200.png'"/>
				        	</a>
							<div class="bt_info">
								<p class="tx_brand">${frontMainVO.promotion2.copy1}</p>
								<p class="tx_tit">${frontMainVO.promotion2.copy2}</p>
								<a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion2.pmno}" class="btn_shop_now">Shop more</a>
							</div>
			        	</div>
					</c:if>
			    </div>
			</div>
		</div>
	</section>
	
	<!-- What’s New -->
	<c:set var="mgd_04" value='${fn:split( shopLibFunction:getProperty("main_goods_display_04"), "|") }'/>
	<c:if test="${mgd_04[0] == 'on' }">
		<div class="main_tit ty02">
			<h3>
				${mgd_04[1]}
				<a href="/shop/goods/new" class="btn_main_more">Shop more</a>
			</h3>
		</div>
		<div class="mn_sc sc_whatnew" data-options='{"items":${mgd_04[4]},"loop":true,"nav":true}' style="position:relative; margin:0 -9px 0 -9px;">
			<div class="swiper-wrapper">
				<c:forEach var="new_gd" items="${frontMainVO.newGoodsList}" varStatus="status">
				<div class="main_list01 swiper-slide"> 
					<div class="" style="position:relative;"> 
						<div class="in_bx">
							<ul class="product-labels"></ul>
							<div class="product-img-wrap">
								<c:if test="${mgd_04[3] == 'img_i' }">
								<img class="product-img" src="${new_gd.imgi}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
								<c:if test="${mgd_04[3] == 'img_s' }">
								<img class="product-img" src="${new_gd.imgs}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
							</div>
						</div>
						<a class="product-link" href="${ctx}/shop/goods/goods_view?goodsno=${new_gd.goodsno}&category=${new_gd.category}"></a>
						<div class="product-caption">
							<div class="tx_brand">${shopLibFunction:getGoodsBrandName(new_gd.brandno)}</div>
							<div class="tx_tit">${new_gd.goodsnmKR}</div>
							<div class="tx_price01">${shopLibFunction:getExchange(new_gd.price, wskin)}<span class="tx_per"><c:if test="${new_gd.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${new_gd.priceRate}"/>%</c:if></span></div>
							<div class="tx_price02"><c:if test="${new_gd.priceRate >= 3}">${shopLibFunction:getExchange(new_gd.consumer, wskin)}</c:if></div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="swiper-pagination" style="display:none;"></div>
			<div class="button arrs">
				<span class="swiper-button-prev" style="cursor:pointer;">이전</span>
				<span class="line"></span>
				<span class="swiper-button-next" style="cursor:pointer;">다음</span>
			</div>
			<script>
			var swiper = new Swiper('.sc_whatnew', {
				slidesPerView: 'auto',
				spaceBetween: 0,
				pagination: {
					el: '.swiper-pagination',
				clickable: true,
				},
				navigation: {   // 버튼 사용자 지정
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
			});
			</script>
			<div class="cb"></div>
		</div>
	</c:if>
	<!-- //What’s New -->

	<div class="main_gap01"></div>
	
	<!-- Sale this week -->
	<c:set var="mgd_05" value='${fn:split( shopLibFunction:getProperty("main_goods_display_05"), "|") }'/>
	<c:if test="${mgd_05[0] == 'on' }">
		<div class="main_tit ty02">
			<h3>
				${mgd_05[1]}
				<a href="/shop/goods/sale" class="btn_main_more">Shop more</a>
			</h3>
		</div>
		<div class="mn_sc sc_saleweek" data-options='{"items":${mgd_05[4]},"loop":true,"nav":true}' style="position:relative; margin:0 -9px 0 -9px;">
			<div class="swiper-wrapper">
				<c:forEach var="sale_gd" items="${frontMainVO.saleGoodsList}" varStatus="status">
				<div class="main_list01 swiper-slide"> 
					<div class="" style="position:relative;"> 
						<div class="in_bx">
							<ul class="product-labels"></ul>
							<div class="product-img-wrap">
								<c:if test="${mgd_05[3] == 'img_i' }">
								<img class="product-img" src="${sale_gd.imgi}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
								<c:if test="${mgd_05[3] == 'img_s' }">
								<img class="product-img" src="${sale_gd.imgs}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/100x100.png'" alt="Image Alternative text" title="Image Title" />
								</c:if>
							</div>
						</div>
						<a class="product-link" href="${ctx}/shop/goods/goods_view?goodsno=${sale_gd.goodsno}&category=${sale_gd.category}"></a>
						<div class="product-caption">
							<div class="tx_brand">${shopLibFunction:getGoodsBrandName(sale_gd.brandno)}</div>
							<div class="tx_tit">${sale_gd.goodsnmKR}</div>
							<div class="tx_price01">${shopLibFunction:getExchange(sale_gd.price, wskin)}<span class="tx_per"><c:if test="${sale_gd.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${sale_gd.priceRate}"/>%</c:if></span></div>
							<div class="tx_price02"><c:if test="${sale_gd.priceRate >= 3}">${shopLibFunction:getExchange(sale_gd.consumer, wskin)}</c:if></div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="swiper-pagination" style="display:none;"></div>
			<div class="button arrs">
				<span class="swiper-button-prev" style="cursor:pointer;">이전</span>
				<span class="line"></span>
				<span class="swiper-button-next" style="cursor:pointer;">다음</span>
			</div>
			<script>
			var swiper = new Swiper('.sc_saleweek', {
				slidesPerView: 'auto',
				spaceBetween: 0,
				pagination: {
					el: '.swiper-pagination',
				clickable: true,
				},
				navigation: {   // 버튼 사용자 지정
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
			});
			</script>
			<div class="cb"></div>
		</div>
	</c:if>
	<!-- //Sale this week -->

	<div class="main_gap02"></div>
	
	<section class="main_banner_ty01 pt_n">
		<div class="banner-content">
			<div class="row">
			    <div class="col-md-6">
					<c:if test="${not empty frontMainVO.promotion3}">
				    	<div class="banner banner-o-hid" style="height:auto;">
					        <a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion3.pmno}">
				        		<img class="" style="width:100%;height:auto;" src="${frontMainVO.promotion3.pcImg}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/560x200.png'"/>
				        	</a>
							<div class="bt_info">
								<p class="tx_brand">${frontMainVO.promotion3.copy1}</p>
								<p class="tx_tit">${frontMainVO.promotion3.copy2}</p>
								<a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion3.pmno}" class="btn_shop_now">Shop more</a>
							</div>
			        	</div>
					</c:if>
			    </div>
			    <div class="col-md-6">
					<c:if test="${not empty frontMainVO.promotion4}">
				    	<div class="banner banner-o-hid" style="height:auto;">
					        <a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion4.pmno}">
				        		<img class="" style="width:100%;height:auto;" src="${frontMainVO.promotion4.pcImg}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/560x200.png'"/>
				        	</a>
							<div class="bt_info">
								<p class="tx_brand">${frontMainVO.promotion4.copy1}</p>
								<p class="tx_tit">${frontMainVO.promotion4.copy2}</p>
								<a href="${ctx}/shop/promotion/promotion?pmno=${frontMainVO.promotion4.pmno}" class="btn_shop_now">Shop more</a>
							</div>
			        	</div>
					</c:if>
			    </div>
			</div>
		</div>
	</section>

	<!-- 메인 하단 배너 -->
	<c:if test="${not empty frontMainVO.bottomBannerList}">
	<div class="m_banner_bl">
		<a href="${frontMainVO.bottomBannerList[0].linkaddr}" target="${frontMainVO.bottomBannerList[0].target}">
			<div class="tit_tl"><img src="/resources/shop/data/skin/kr/images/tit_bl.png" alt="" /></div>
			<div class="pr_img bt_banner_pc"><img src="${frontMainVO.bottomBannerList[0].img}" alt="" /></div>
			<div class="pr_img bt_banner_mob"><img src="${frontMainVO.bottomBannerList[0].imgMobile}" alt="" /></div>
			<div>
				<ul>
					<!-- <li>
						<p class="tx01">${frontMainVO.bottomBannerList[0].copy1}</p>
						<p class="tx02">${frontMainVO.bottomBannerList[0].copy2}</p>
					</li> -->
					<li class="last">
						<p class="tx03">${frontMainVO.bottomBannerList[0].copy1}</p>
						<p class="tx04">${frontMainVO.bottomBannerList[0].copy2}</p>
					</il>
				</ul>
				<div style="clear:both;"></div>
			</div>
		</a>
	</div>
	</c:if>
	<!-- //메인  하단 배너 -->
</div>

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

/* 2017-09-01 추가 - main 상품리스트 이미지 height 정렬 */
$(window).load(function(){
	var maxHeight = -1;
	$('.owl-item-slide').css('height', 'auto');	//초기화
	for(var i=1; i<6; i++){
		maxHeight = -1;
		$('.grp0'+i).each(function(){
			maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
		});	
		$('.grp0'+i).height(maxHeight);
	}
});

 $(window).resize(function(){
	$('.owl-item-slide').css('height', 'auto');	//초기화
	for(var i=1; i<6; i++){
		maxHeight = -1;
		$('.grp0'+i).each(function(){
			maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
		});	
		$('.grp0'+i).height(maxHeight);
	}
});

</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<script>
$(document).ready(function(){
/*Slider Revolution For Hp-1*/
/* initialize the slider based on the Slider's ID attribute FROM THE WRAPPER above */
	/* jQuery('#rev_slider_1').show().revolution({
		responsiveLevels: [1200, 992, 768, 576],
		autoHeight: 'on',
		sliderLayout: 'fullscreen',
			
		 [DESKTOP, LAPTOP, TABLET, SMARTPHONE]       
		navigation: {

			arrows: {

				enable: true,
				style: 'hesperiden',
				tmp: '',
				rtl: false,
				hide_onleave: false,
				hide_onmobile: true,
				hide_under: 992,
		 
				left: {
					container: 'slider',
					h_align: 'right',
					v_align: 'bottom',
					h_offset: 187,
					v_offset: 38,
				},
		 
				right: {
					container: 'slider',
					h_align: 'right',
					v_align: 'bottom',
					h_offset: 35,
					v_offset: 38,
				}
		 
			},

			bullets: {
				enable: true,
				style: 'uranus',
				tmp: '<span class="tp-bullet-inner"></span>',
				hide_onleave: false,
				h_align: "left",
				v_align: "bottom",
				h_offset: 50,
				v_offset: 30,
				space: 10,
			}
		}
	});    */
/*End Slider Revolution For Hp-1*/
})
</script>