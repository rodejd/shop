<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">
function goPage(page){
	$('#pageNo').val(page);
	document.frmList.submit();
}

function gsort(sort){	
	var frm = document.frmList;
	frm.sort.value = sort;
	goPage(1);
}

function category(cate){	
	var frm = document.frmList;
	frm.category.value = cate;
	goPage(1);
}

function sale(sale){	
	var frm = document.frmList;
	frm.sale.value = sale;
	goPage(1);
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
</script>

<script>
$(document).ready(function(){
	$(".in_bx_ft").click(function(){
		if(!$(this).data("click")){
			$(".ft_n_wrap").css("display", "block");
			$(this).data("click", true);
		}else{
			$(".ft_n_wrap").css("display", "none");
			$(this).data("click", false);
		}
	});
	$(".stit_1").click(function(){
		if ($(this).hasClass("open")){
			$(this).removeClass("open");
			$(this).next(".pr_bx_in").hide();
		}else{
			$(".stit_1").removeClass("open");
			$(".pr_bx_in").hide();
			$(this).addClass("open");
			$(this).next(".pr_bx_in").show();
		}
	});
	$(".s_tit_n03 span").click(function(){
		if ($(this).parent().hasClass("open")){
			$(this).parent().removeClass("open");
			$(this).parent().next(".bx_dp3").hide();
		}else{
			$(".s_tit_n03").removeClass("open");
			$(".bx_dp3").hide();
			$(this).parent().addClass("open");
			$(this).parent().next(".bx_dp3").show();
		}
	});
	
	$(".alph_r a").on("click", function(e){
		e.preventDefault();
		$(".alph_r a").removeClass("on");
		$(this).addClass("on");
		$(".sc_y_Wrap").animate({scrollTop : $("#ap_b_" + $(this).text()).offset().top - $(".sc_y_Wrap").offset().top + $(".sc_y_Wrap").scrollTop()}, 400);
	});
	
	$("#dsearch").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			var txt = $("#dsearch").val().trim();
			if (txt == "") {
				$(".list_n li").show();
				$(".ap_b").show();
				$(".alph_r li").show();
				$(".alph_r a").eq(0).click();
				return;
			}
			
			$(".list_n").each(function(){
				$(this).find("li").each(function(){
					if ($(this).text().toUpperCase().indexOf(txt.toUpperCase()) > -1) {
						$(this).show();
					} else {
						$(this).hide();
					}
				});
				
				if ($(this).find("li:visible").length > 0) {
					$(this).prev(".ap_b").show();
					$(".alph_r li:contains('" + $(this).prev(".ap_b").text() + "')").show();
				}else{
					$(this).prev(".ap_b").hide();
					$(".alph_r li:contains('" + $(this).prev(".ap_b").text() + "')").hide();
				}
			});
		}
	});
	
	$("#btnInit").on("click", function(e){
		e.preventDefault();
		$("#brands").val("");
		$("#options").val("");
		goPage(1);
	});
	
	$("#btnFltr").on("click", function(e){
		e.preventDefault();
		var brands = [];
		$(".ckbrand:checked").each(function(){
			brands.push($(this).val());
		});
		$("#brands").val(brands.join(","));
		var options = [];
		$(".ckoption:checked").each(function(){
			options.push($(this).val());
		});
		$("#options").val(options.join(","));
		goPage(1);
	});
});
</script>

<form name="frmList" id="frmList" method="get" action="${ctx}/shop/goods/sale">
	<input type="hidden" name="category" value="${frontGoodsVO.category}">
	<input type="hidden" name="sale" value="${frontGoodsVO.sale}">
	<input type="hidden" id="brands" name="brands" value="${frontGoodsVO.brands}">
	<input type="hidden" id="options" name="options" value="${frontGoodsVO.options}">
	<input type="hidden" name="sort" value="${frontGoodsVO.sort}">
	<input type="hidden" name="pageNo" id="pageNo" value="${frontGoodsVO.pageNo}">
	<input type="hidden" name="pageSize" value="${frontGoodsVO.pageSize}">
	
	 <div class="global-wrapper clearfix" id="global-wrapper">
	
	 	<div class="top_tit_ty01">
			<div class="tit_dp01 tit_mob_n">SALE</div>
	
			<div class="bx_banner01">
				<img src="/resources/shop/data/skin/kr/images/banner_sale.jpg" alt="" style="width:100%;" class="img_pc" />
				<img src="/resources/shop/data/skin/kr/images/banner_sale_mob.jpg" alt="" style="width:100%;" class="img_mob" />
			</div>
		</div>
 
		<div class="x-goods-list container_list">
			<div class="bx_filter01">
				<div style="">
					<a href="javascript:category('002');" class="fl_btn_ty01<c:if test="${fn:substring(frontGoodsVO.category, 0, 3) eq '002'}"> on</c:if>">WOMEN</a>
					<a href="javascript:category('001');" class="fl_btn_ty01<c:if test="${fn:substring(frontGoodsVO.category, 0, 3) eq '001'}"> on</c:if>">MEN</a>
					<a href="javascript:category('003');" class="fl_btn_ty01<c:if test="${fn:substring(frontGoodsVO.category, 0, 3) eq '003'}"> on</c:if>">KIDS</a>
				</div>
				<div class="line_l">
					<a href="javascript:sale('0');" class="fl_btn_ty02<c:if test="${frontGoodsVO.sale eq '0'}"> on</c:if>">전체</a>
					<a href="javascript:sale('50');" class="fl_btn_ty02<c:if test="${frontGoodsVO.sale eq '50'}"> on</c:if>">50% ~</a>
					<a href="javascript:sale('30');" class="fl_btn_ty02<c:if test="${frontGoodsVO.sale eq '30'}"> on</c:if>">30% ~</a>
					<a href="javascript:sale('10');" class="fl_btn_ty02<c:if test="${frontGoodsVO.sale eq '10'}"> on</c:if>">10% ~</a>
				</div>
         		<ul>
         			<li>
         				<!-- <span class="category-selections-sign">Sort by :</span> -->
                       	<select onchange="javascript:gsort(this.value);" class="category-selections-select form-control">
                        	<option value="priceRate desc" <c:if test="${frontGoodsVO.sort eq 'priceRate desc' }">selected="selected"</c:if>>할인율순</option>
                        	<option value="ordCnt desc" <c:if test="${frontGoodsVO.sort eq 'ordCnt desc' }">selected="selected"</c:if>>판매순</option>
                            <option value="price" <c:if test="${frontGoodsVO.sort eq 'price' }">selected="selected"</c:if>>낮은가격순</option>
                            <option value="price desc" <c:if test="${frontGoodsVO.sort eq 'price desc' }">selected="selected"</c:if>>높은가격순</option>
                        	<option value="goodsno desc" <c:if test="${frontGoodsVO.sort eq 'goodsno desc' }">selected="selected"</c:if>>신규등록순</option>
                        </select>
					</li>
					<li>
                        <div class="in_bx_ft">필터</div>
                   	</li>
				</ul>
				<div style="clear:both;"></div>
				
				<!-- 필터 -->
				<div class="ft_n_wrap">
					<p class="s_tit_n01">필터</p>
					<ul>
						<c:if test="${frontGoodsVO.frontGoodsGoodsCategoryList ne '' and fn:length(frontGoodsVO.frontGoodsGoodsCategoryList) > 0 and fn:length(category.category) <= 6}">
							<li class="dp1">
								<div class="stit_1">
									카테고리
									<span class="icon_minus">-</span>
									<span class="icon_plus">+</span>
								</div>
								<div class="in_cate_list pr_bx_in">
									<p class="s_tit_n02">${cateInfo.catnmKR} (${stringUtil:getMoneyFormatInteger(cateInfo.goodsCnt)})</p>
									<ul>
										<c:forEach var="ctList" items="${frontGoodsVO.frontGoodsGoodsCategoryList }" varStatus="status">
											<li class="dp2">
												<div class="s_tit_n03">
													<a href="${ctx}/shop/goods/sale?category=${ctList.category}">${ctList.catnmKR} (${stringUtil:getMoneyFormatInteger(ctList.goodsCnt)})</a>
													<c:if test="${ctList.categoryList ne '' and fn:length(ctList.categoryList) > 0}">
														<span class="icon_minus">-</span>
														<span class="icon_plus">+</span>
													</c:if>
												</div>
												<c:if test="${ctList.categoryList ne ''}">
													<ul class="bx_dp3">
														<c:forEach var="subCtList" items="${ctList.categoryList}" varStatus="substatus">
															<li class="dp3"><a href="${ctx}/shop/goods/sale?category=${subCtList.category}">${subCtList.catnmKR} (${stringUtil:getMoneyFormatInteger(subCtList.goodsCnt)})</a></li>
														</c:forEach>
													</ul>
												</c:if>
											</li>
										</c:forEach>
									</ul>
								</div>
							</li>
						</c:if>
						
						<c:if test="${frontGoodsVO.frontGoodsGoodsBrandList ne '' and fn:length(frontGoodsVO.frontGoodsGoodsBrandList) > 0}">
							<li class="dp1">
								<div class="stit_1">
									디자이너 (${stringUtil:getMoneyFormatInteger(fn:length(frontGoodsVO.frontGoodsGoodsBrandList))})
									<span class="icon_minus">-</span>
									<span class="icon_plus">+</span>
								</div>
								<div class="in_alpha_list pr_bx_in">
									<div class="bx_inp">
										<input type="text" id="dsearch" placeholder="DESIGNER SEARCH" />
									</div>
									<div class="sc_y_Wrap">
										<div class="list_n_wrap">
											<c:set var="spell" value="" />
											<c:forEach var="brList" items="${frontGoodsVO.frontGoodsGoodsBrandList}" varStatus="status">
												<c:if test="${spell ne brList.spell}">
													<c:set var="spell" value="${brList.spell}" />
													<c:if test="${not status.first}">
														</ul>
													</c:if>
												
													<p class="ap_b" id="ap_b_${spell eq '0' ? 'ETC' : spell}">${spell eq '0' ? 'ETC' : spell}</p>
													<ul class="list_n">
												</c:if>
												
												<li><input type="checkbox" class="ck_ty01 ckbrand" id="brand_${status.index}" value="${brList.sno}" ${shopLibFunction:isContains(fn:split(frontGoodsVO.brands, ','), brList.sno) ? ' checked' : ''}/><label for="brand_${status.index}">${brList.brandnm}(${stringUtil:getMoneyFormatInteger(brList.goodsCnt)})${brList.brandnmKR}</label></li>
												
												<c:if test="${status.last}">
													</ul>
												</c:if>
											</c:forEach>
										</div>
										<div class="alph_r">
											<ul>
												<c:set var="spell" value="" />
												<c:forEach var="brList" items="${frontGoodsVO.frontGoodsGoodsBrandList}" varStatus="status">
													<c:if test="${spell ne brList.spell and brList.spell ne '0'}">
														<li><a href="#"${spell eq '' ? ' class="on"' : ''}>${brList.spell}</a></li>
														<c:set var="spell" value="${brList.spell}" />
													</c:if>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</c:if>
						<li class="dp1 last">
							<div class="stit_1">
								사이즈
								<span class="icon_minus">-</span>
								<span class="icon_plus">+</span>
							</div>
							<div class="in_size_list pr_bx_in">
								<p class="s_tit_n02">CLOTHING (TOPS)</p>
								<div class="li_ty01">
									<ul>
										<li>			 <input type="checkbox" class="ck_ty01 ckoption" id="test03_01" value="XXXS" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'XXXS') ? ' checked' : ''}/><label for="test03_01">XXXS</label></li>
										<li class="last"><input type="checkbox" class="ck_ty01 ckoption" id="test03_02" value="XXS"  ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'XXS' ) ? ' checked' : ''}/><label for="test03_02">XXS</label></li>
										<li>			 <input type="checkbox" class="ck_ty01 ckoption" id="test03_03" value="XS"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'XS'  ) ? ' checked' : ''}/><label for="test03_03">XS</label></li>
										<li class="last"><input type="checkbox" class="ck_ty01 ckoption" id="test03_04" value="S"    ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'S'   ) ? ' checked' : ''}/><label for="test03_04">S</label></li>
										<li>			 <input type="checkbox" class="ck_ty01 ckoption" id="test03_05" value="M"    ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'M'   ) ? ' checked' : ''}/><label for="test03_05">M</label></li>
										<li class="last"><input type="checkbox" class="ck_ty01 ckoption" id="test03_06" value="L"    ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'L'   ) ? ' checked' : ''}/><label for="test03_06">L</label></li>
										<li>			 <input type="checkbox" class="ck_ty01 ckoption" id="test03_07" value="XL"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'XL'  ) ? ' checked' : ''}/><label for="test03_07">XL</label></li>
										<li class="last"><input type="checkbox" class="ck_ty01 ckoption" id="test03_08" value="XXL"  ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'XXL' ) ? ' checked' : ''}/><label for="test03_08">XXL</label></li>
										<li>			 <input type="checkbox" class="ck_ty01 ckoption" id="test03_09" value="XXXL" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'XXXL') ? ' checked' : ''}/><label for="test03_09">XXXL</label></li>
										<li class="last"><input type="checkbox" class="ck_ty01 ckoption" id="test03_10" value="4XL"  ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '4XL' ) ? ' checked' : ''}/><label for="test03_10">4XL</label></li>
										<li>			 <input type="checkbox" class="ck_ty01 ckoption" id="test03_11" value="FREE" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'FREE') ? ' checked' : ''}/><label for="test03_11">FREE</label></li>
									</ul>
									<div style="clear:both;"></div>
								</div>
								<div class="li_ty02">
									<p class="s_tit_n02">CLOTHING (BOTTOMS)</p>
									<ul>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_01" value="23" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '23') ? ' checked' : ''}/><label for="test04_01">23</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_02" value="24" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '24') ? ' checked' : ''}/><label for="test04_02">24</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_03" value="25" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '25') ? ' checked' : ''}/><label for="test04_03">25</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_04" value="26" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '26') ? ' checked' : ''}/><label for="test04_04">26</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_05" value="27" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '27') ? ' checked' : ''}/><label for="test04_05">27</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_06" value="28" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '28') ? ' checked' : ''}/><label for="test04_06">28</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_07" value="29" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '29') ? ' checked' : ''}/><label for="test04_07">29</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_08" value="30" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '30') ? ' checked' : ''}/><label for="test04_08">30</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_09" value="32" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '32') ? ' checked' : ''}/><label for="test04_09">32</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_10" value="34" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '34') ? ' checked' : ''}/><label for="test04_10">34</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_11" value="36" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '36') ? ' checked' : ''}/><label for="test04_11">36</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_12" value="38" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '38') ? ' checked' : ''}/><label for="test04_12">38</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_13" value="49" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '49') ? ' checked' : ''}/><label for="test04_13">49</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_14" value="42" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '42') ? ' checked' : ''}/><label for="test04_14">42</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_15" value="44" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '44') ? ' checked' : ''}/><label for="test04_15">44</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test04_16" value="FREE" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), 'FREE') ? ' checked' : ''}/><label for="test04_16">FREE</label></li>
									</ul>
									<div style="clear:both;"></div>
								</div>
								<div class="li_ty02">
									<p class="s_tit_n02">SHOES</p>
									<ul>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_01" value="34"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '34'  ) ? ' checked' : ''}/><label for="test05_01">34</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_02" value="34.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '34.5') ? ' checked' : ''}/><label for="test05_02">34.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_03" value="35"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '35'  ) ? ' checked' : ''}/><label for="test05_03">35</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_04" value="35.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '35.5') ? ' checked' : ''}/><label for="test05_04">35.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_05" value="36"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '36'  ) ? ' checked' : ''}/><label for="test05_05">36</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_06" value="36.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '36.5') ? ' checked' : ''}/><label for="test05_06">36.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_07" value="37"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '37'  ) ? ' checked' : ''}/><label for="test05_07">37</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_08" value="37.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '37.5') ? ' checked' : ''}/><label for="test05_08">37.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_09" value="38"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '38'  ) ? ' checked' : ''}/><label for="test05_09">38</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_10" value="39"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '39'  ) ? ' checked' : ''}/><label for="test05_10">39</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_11" value="39.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '39.5') ? ' checked' : ''}/><label for="test05_11">39.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_12" value="40"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '40'  ) ? ' checked' : ''}/><label for="test05_12">40</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_13" value="40.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '40.5') ? ' checked' : ''}/><label for="test05_13">40.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_14" value="41"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '41'  ) ? ' checked' : ''}/><label for="test05_14">41</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_15" value="41.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '41.5') ? ' checked' : ''}/><label for="test05_15">41.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_16" value="42"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '42'  ) ? ' checked' : ''}/><label for="test05_16">42</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_17" value="42.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '42.5') ? ' checked' : ''}/><label for="test05_17">42.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_18" value="43"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '43'  ) ? ' checked' : ''}/><label for="test05_18">43</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_19" value="43.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '43.5') ? ' checked' : ''}/><label for="test05_19">43.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_20" value="44"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '44'  ) ? ' checked' : ''}/><label for="test05_20">44</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_21" value="44.5" ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '44.5') ? ' checked' : ''}/><label for="test05_21">44.5</label></li>
										<li><input type="checkbox" class="ck_ty01 ckoption" id="test05_22" value="45"   ${shopLibFunction:isContains(fn:split(frontGoodsVO.options, ','), '45'  ) ? ' checked' : ''}/><label for="test05_22">45</label></li>
									</ul>
									<div style="clear:both;"></div>
								</div>
							</div>
						</li>
					</ul>
					<div class="btm_btns">
						<a href="#" class="btn_01" id="btnInit">초기화</a>
						<a href="#" class="btn_02" id="btnFltr">적용하기</a>
					</div>
				</div>
				<!-- //필터 -->
			</div>

			<div class="bx_total_num">
				총 ${stringUtil:getMoneyFormatString(frontGoodsVO.rowCount)}개의 상품 검색
			</div>

			<div class="sub_list02">
				<ul>
					<c:forEach var="gdList" items="${frontGoodsVO.frontGoodsList }" varStatus="status">   
						<li>
							<div class="in_bx">
								<div class="bx_thum">
									<div class="">
										<a href="javascript:like(this, ${gdList.goodsno});" class="btn_heart"><img src="/resources/shop/data/skin/kr/images/icon_heart.png" alt="" /></a>
										<img class="product-img-primary product-img" src="${fn:split(gdList.imgs,'|')[0]}" alt="${gdList.goodsnmKR}"  onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/200x200.png'" />
									</div>
								</div>
								<a class="product-link" href="${ctx}/shop/goods/goods_view?goodsno=${gdList.goodsno}&category=${gdList.category}"></a>
								<div class="bx_info">
									<div class="tx_brand">${gdList.brandnm}</div>
									<div class="tx_tit">${gdList.goodsnmKR}</div>
									<c:choose>
										<c:when test="${0 != gdList.stock && 0 == gdList.runout}">
											<div class="tx_price01">${shopLibFunction:getExchange(gdList.price, wskin)}<span class="tx_per"><c:if test="${gdList.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${gdList.priceRate}"/>%</c:if></span></div>
											<div class="tx_price02"><c:if test="${gdList.priceRate >= 3}">${shopLibFunction:getExchange(gdList.consumer, wskin)}</c:if></div>
										</c:when>
										<c:otherwise>
											<span class="sold-out">품절</span>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</li>
						<c:if test="${status.count % 4 eq 0 or status.last}">
							<div style="clear:both;"></div>
						</c:if>
					</c:forEach>
				</ul>
			</div>
				
			<div class="col-md-12 text-center">
				<nav>
					<ul class="pagination category-pagination">
						<tags:frontPaginator currentPageNo="${frontGoodsVO.pageNo}" rowCount="${frontGoodsVO.rowCount}" pageSize="${frontGoodsVO.pageSize}"  pageGroupSize="${frontGoodsVO.pageGroupSize}" />
					</ul>
				</nav>
			</div>
		</div>
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