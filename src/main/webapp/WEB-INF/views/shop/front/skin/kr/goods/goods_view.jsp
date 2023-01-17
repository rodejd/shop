<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.option-head').on('click', function() {
			event.preventDefault();
			var $th = $(this);
				
			if ($th.find('input').attr('disabled')) {
				return;
			}
				
			$('.option-choice > li').removeClass('on');
			$('.option-radio input').prop('checked', false);
			$th.closest('li').addClass('on');
			$th.find('.option-radio input').prop('checked', true).trigger("change");
		});
		
		if ("${frontGoodsVO.goodsView.usegoodsadd}" == "0") {
			setTotalPrice();
		}
		
		//$('.option-choice > li:eq(0) .option-head').trigger('click');
		//$("input:radio[name='option-group']").on("change", function(){
		//});
	});

	//페이스북 공유
	function sharefacebook(url) {
		window.open("http://www.facebook.com/sharer/sharer.php?u=" + url);
	}
	//트위터 공유
	function sharetwitter(PAGE_URL, TEXT) {
		window.open("https://twitter.com/intent/tweet?text=" + TEXT + "&url=" + PAGE_URL);
	}
	//네이버공유
	function sharenaver(url, title) {
		//     var url = encodeURI(encodeURIComponent(myform.url.value));
		//     var title = encodeURI(myform.title.value);
		var shareURL = "http://share.naver.com/web/shareView.nhn?url=" + url + "&title=" + title;
		document.location = shareURL;
	}
	//핀터레스트 공유  
	//지금 포트번호로 연결되어서 나중에 도메인이 나오면 변경해야함.
	function sharePin(url, text) {
		var sImg = "https://s.pstatic.net/static/www/mobile/edit/2017/1025/mobile_180716594733.png";
		//var sTxt = "텍스트";
		var href = "http://www.pinterest.com/pin/create/button/?url="
				+ encodeURIComponent(url) + "&media="
				+ encodeURIComponent(sImg) + "&description="
				+ encodeURIComponent(text);
		var a = window.open(href, 'pinterest', 'width=800, height=500');
		if (a) {
			a.focus();
		}
	}
	
	function copyUrl() {
	    var IE = (document.all) ? true : false,
	        url= location.href;
	    if (IE) {
	        window.clipboardData.setData('Text', url);
	        alert("현재 상품의 URL이 복사되었습니다.");
	    }
	    else
	    {
	        var tmpTextarea = document.createElement('textarea');
	        tmpTextarea.value = url;
	        document.body.appendChild(tmpTextarea);
	        tmpTextarea.select();
	        tmpTextarea.setSelectionRange(0, 9999);  // 셀렉트 범위 설정

	        document.execCommand('copy');
	        document.body.removeChild(tmpTextarea);
	        alert("URL 복사가 완료되었습니다.");
	    }
	}

	$(function() {
		reviewLoad('1', '${frontGoodsVO.goodsno}', 'reviewimg');
		qnaLoad('1', '${frontGoodsVO.goodsno}');
		
		$("#rsort").on("change", function(){
			reviewLoad('1', '${frontGoodsVO.goodsno}', $(this).val());
		});
	});
	<%-- 상품평 조회 --%>
	function reviewLoad(pageNo, goodsno, sort) {
		var data = "goodsno=" + goodsno + "&pageNo=" + pageNo + "&sort=" + sort;
		ajaxCallGet('/shop/goods/ajaxMarketReview?' + data, 'goodsReview');
	}
	<%-- QnA 조회 --%>
	function qnaLoad(pageNo, goodsno) {
		var data = "goodsno=" + goodsno + "&pageNo=" + pageNo;
		ajaxCallGet('/shop/goods/ajaxGoodsQna?' + data, 'goodsQna');
	}
	<%-- 즉시구매 --%>
	function order(target) {
		<c:choose>
			<c:when test="${frontGoodsVO.goodsView.vipYn eq 'Y' && (empty userInfo.xkey.level || userInfo.xkey.level eq '1')}">
				alert('VIP 판매 상품 입니다.');
				return;
			</c:when>
			<c:otherwise>
				var form = document.frmView;
				if ("${frontGoodsVO.goodsView.usegoodsadd}" == "0") {
					if (typeof (form.ea) == "undefined") {
						alert('판매 상품이 아닙니다.');
						return;
					}
			
					if (!(form.ea.value > 0)) {
						alert("구매수량은 1개 이상만 가능합니다");
						return;
					}
				}
				
				if($(".option-data").length == 0){
					alert("옵션을 선택해주세요.");
					return;
				}
		
				var m_no = "${userInfo.m_no}";
				if (m_no == 0) {
					alert("비회원으로 구매 가능합니다.\n단, 회원주문시 할인/쿠폰/포인 등의 혜택이 제공됩니다.")
				}
		
				setParams0('${frontGoodsVO.goodsno}');
				
				form.action = "/shop/order/order";
				if (chkForm(form))
					form.submit();
			</c:otherwise>
		</c:choose>
	}
	
	function setParams0(num) {
		var options_list = '';

		var goodsno = num;
		var opt = '';
		if (document.getElementsByName('opt[]')[0]) {
			opt = document.getElementsByName('opt[]')[0].value;
		} else {
			opt = 'NULL';
		}

		var addopt = '';
		if (document.getElementsByName('addopt')[0]) {
			var addopts = document.getElementsByName('addopt');
			for (var i = 0; i < addopts.length; i++) {
				if (addopt == '') {
					addopt += document.getElementsByName('addopt')[i].value;
				} else {
					if (document.getElementsByName('addopt')[i].value == '') {
					} else {
						addopt += '|' + document.getElementsByName('addopt')[i].value;
					}
				}
			}
		} else {
			addopt = 'NULL';
		}
		
		var ea = 1;
		//상품 옵션을 사용하지 않는 경우
		if ("${frontGoodsVO.goodsView.usegoodsadd}" == "0") {
	 		opt ='${frontGoodsVO.goodsView.sno}';
	 		ea = $(".option-count.dev-optCount").val();
	 	 	options_list = goodsno + '|||' + opt + '|||' + addopt + '|||' + ea;
	 	//상품옵션을 사용하는 경우
	 	}else{
	 		$(".option-data").each(function(i){
	 			ea = $(this).find(".option-count.dev-optCount").val();
	 			opt = $(this).find("input[name=optionOptnoArr]").val();
	 			if(i == 0 ){
	 				options_list += goodsno + '|||' + opt + '|||' + addopt + '|||' + ea + ',';	
	 			}else{
	 				options_list += goodsno + '|||' + opt + '|||' + '' + '|||' + ea + ',';
	 			}
	 		});
	 	}
		frmView.optionsList.value = options_list;
	}

	function wishlist() {
		var form = document.frmView;
		var goodsno = form.goodsno.value;

		if (chkForm(form)) {
	 		var opt = "${frontGoodsVO.goodsView.sno}";
	 	 	var options_list = goodsno + '|||' + opt + '||||||1';
			form.optionsList.value = options_list;
			
			ajaxCallJsonPost("/shop/mypage/ajaxWishListAdd.doJson", "frmView", function(rst){
			if (rst.code == "1"){
				if (confirm('위시리스트에 추가되었습니다.\n\n위시리스트로 이동하시겠습니까?')){
					location.href = ctx + "/shop/mypage/mypage_wishlist";
				}
			}else{
				alert("동일한 상품이 위시리스트에 있습니다.");
			}
			});
		}
	}

	function goodscart(target) {
		<c:choose>
			<c:when test="${frontGoodsVO.goodsView.vipYn eq 'Y' && (empty userInfo.xkey.level || userInfo.xkey.level eq '1')}">
				alert('VIP 판매 상품 입니다.');
				return;
			</c:when>
			<c:otherwise>
				var form = document.frmView;
				var goodsno = form.goodsno.value;
				
				<c:if test="${frontGoodsVO.goodsView.stock == 0 || frontGoodsVO.goodsView.runout != 0}">
					alert('판매 상품이 아닙니다.');
					return;
				</c:if>
			
				var ea = 0;
				if ("${frontGoodsVO.goodsView.usegoodsadd}" == "0") {
					ea = document.getElementById('ea').value;
				}else{
			 		$(".option-data").each(function(i){
			 			ea += Number($(this).find(".option-count.dev-optCount").val());
			 		});
				}
				
				if (ea == 0) {
					alert("구매수량은 1개 이상만 가능합니다");
					return;
				}
			
				setParams();
					
				if (chkForm(form)) {
					ajaxCallJsonPost("/shop/goods/ajaxGoodsCartAdd.doJson", "frmView", function(rst){
						if (confirm('장바구니에 추가되었습니다.\n\n장바구니로 이동하시겠습니까?')){
							location.href = ctx + "/shop/goods/goods_cart";
						}
					});
				}
			</c:otherwise>
		</c:choose>
	}

	function setParams() {
		var options_list = '';
		var goodsno = $('#goodsno').val();
		var opt = '';
		if (document.getElementsByName('opt[]')[0]) {
			opt = document.getElementsByName('opt[]')[0].value;
		} else {
			opt = 'NULL';
		}

		var addopt = '';
		if (document.getElementsByName('addopt')[0]) {
			var addopts = document.getElementsByName('addopt');
			for (var i = 0; i < addopts.length; i++) {
				if (addopt == '') {
					addopt += document.getElementsByName('addopt')[i].value;
				} else {
					if (document.getElementsByName('addopt')[i].value == '') {
					} else {
						addopt += '|' + document.getElementsByName('addopt')[i].value;
					}

				}
			}
		} else {
			addopt = 'NULL';
		}
		
		var ea = 1;
		//상품옵션을 사용하지 않는 경우 
	 	if ("${frontGoodsVO.goodsView.usegoodsadd}" == "0") {
	 		opt ='${frontGoodsVO.goodsView.sno}';
	 		ea = document.getElementById('ea').value;
	 	 	options_list = goodsno + '|||' + opt + '|||' + addopt + '|||' + ea;
	 	//상품옵션을 사용하는 경우
	 	} else {
	 		$(".option-data").each(function(i){
	 			ea = $(this).find(".option-count.dev-optCount").val();
	 			opt = $(this).find("input[name=optionOptnoArr]").val();
	 			if (i == 0) {
	 				options_list += goodsno + '|||' + opt + '|||' + addopt + '|||' + ea + ',';	
	 			} else {
	 				options_list += goodsno + '|||' + opt + '|||' + '' + '|||' + ea + ',';
	 			}
	 		});
	 	}
		frmView.optionsList.value = options_list;
	}

	/* 쿠폰 직접 다운로드 ajax */
	function ajaxDownCoupon(couponcd, goodsno, goodsnm) {
		//로그인 여부 확인
		ajaxCallJson("checkLogin.dojson", {
		}, function(data) {
			if (data.result == 1) {
				//쿠폰 발급
				ajaxCallJson("downCoupon.dojson", {
					'couponcd' : couponcd,
					'goodsno' : goodsno,
					'goodsnm' : goodsnm
				}, function(data) {
					if (data.result == 1) {
						alert("쿠폰이 정상적으로 발급되었습니다.");
					} else {
						alert("쿠폰의 제한 다운횟수를 초과하였습니다.")
					}
				}, function(e) {
					console.log(e);
				});
			} else {
				alert("로그인 후에 이용 가능합니다");
			}
		}, function(e) {
			console.log(e);
		});
	}

	function setOptionStock(obj, optVal) {
		var price = Number($(obj).siblings("input[name=optionPriceArr]").val());
		var currentVal = parseInt($(obj).siblings(".option-count.dev-optCount").text(), 10);
		var opt = eval(currentVal + optVal + 1);
		var totalPrice = (price * opt) / currentVal;

		if (!currentVal || currentVal == "" || currentVal == "NaN") {
			currentVal = 1;
		}
		if (opt > 0) {
			$(obj).parent().find(".option-count.dev-optCount").text(opt);
			$(obj).parent().find(".dev-optPrice").text(numberWithCommas(totalPrice));
			$(obj).parent().find("input[name=optionPriceArr]").val(totalPrice);
			setTotalPrice();
		}
	}

	function deleteOption(obj) {
		$(obj).parent().parent().parent().remove();
		setTotalPrice();
		var optNo = $(obj).data("optno");
		$("input[name='opt2No'][value='" + optNo + "']").prop("checked", false);
	}
	
	function deleteOption2(obj) {
		if($(obj).is(":checked")) {
			return false;
		} else {
			$(".dev_"+ $(obj).val()).remove();
			setTotalPrice();
		}
	}
	
	function setAddOption(obj) {
		//var goodsPrice = "${frontGoodsVO.goodsOptionList[0].price}";
		var goodsPrice = "${frontGoodsVO.goodsView.price}";
		var option2Name = $(obj).data("name") == undefined ? '' : ' ' + $(obj).data("name");
		var optionOptno = $(obj).val();
		var optionName;
		if ("${frontGoodsVO.goodsView.opttype}" == 'single' ) {
			optionName = option2Name;
		} else {
			optionName = $("input[name='option-group']:checked").data("name") + option2Name;
		}
		
		var optionPrice = Number(goodsPrice)+ Number($(obj).data("price"));
		var valiCheck = false;
		var type = $(obj).attr("type");
		
		if (type == "radio") {
			$("input[name='optionOptnoArr']").each(function() {
				if ($(this).val() == optionOptno) {
					valiCheck = true;
					return false;
				}
			});
		}
		
		if (valiCheck) {
			alert("이미 추가한 옵션입니다.");
			return false;
		}
		
		var html = "";
		html += "	<li class='dev_" + optionOptno + "'>";
		html += "		<div class='product-order-list'>";
		html += "			<strong class='product-title-new'>" + optionName + "</strong>";
		html += "			<div class='product-num-control'>";
		html += "				<input type='hidden' name='optionOptnoArr' value='" + optionOptno + "'>";
		html += "				<input type='hidden' name='optionPriceArr' value='" + optionPrice + "'>";
		html += "				<button type='button' class='option-cont-item option-minus' onclick='setOptionStock(this, \"-\");return false;'>-</button>";
		html += "				<span class='option-count dev-optCount'>1</span>";
		html += "				<button type='button' class='option-cont-item option-plus' onclick='setOptionStock(this, \"+\");return false;'>+</button>";
		html += "				<span class='dev-optPrice'>" + numberWithCommas(optionPrice) + "</span>";
		html += "				<button class='del' data-optno='" + optionOptno + "' onclick='deleteOption(this);'>x</button>";
		html += "			</div>";
		html += "		</div>";
		html += "	</li>";
		
		$("ul.option-goods").append(html);
		setTotalPrice();
	}
	
	/*
	function setTotalPrice() {
		var totalPrice = 0;
		var totalCnt = 0;
		var addOptPrice = Number($("#optionValue").val());
		alert(addOptPrice);
		if ($(".product-num-control").length > 0) {
			$(".product-num-control").each(function(i) {
				totalPrice += Number($(this).find("input[name=optionPriceArr]").val());
				totalCnt += Number($(this).find(".option-count.dev-optCount").text());
			});
		} else {
			totalPrice = Number($("#totalValue").val());	
			totalCnt = $("#ea").val() == undefined ? "0" : $("#ea").val();
		}

		totalPrice += addOptPrice;
		$("#sum_cnt > strong").text(numberWithCommas(totalCnt));
		$("#sum_price").text(numberWithCommas(totalPrice));
	}
	*/
	
	/**************************
	 * 신규 옵션 변경
	 **************************/
	$(function(){
		<c:if test="${frontGoodsVO.goodsView.opttype eq 'single' and empty frontGoodsVO.goodsOptionList}">
			var html = '';
			html += '<div class="bx_result02 option-data">';
			html += "	<input type='hidden' name='optionOptnoArr' value='0'>";
			html += "	<input type='hidden' name='optionPriceArr' value='${shopLibFunction:getExchange(frontGoodsVO.goodsView.price, wskin)}'>";
			html += '	<a href="javascript:;" class="btn_close_res_n"></a>';
			html += "	<p class='tx01'>${frontGoodsVO.goodsView.goodsnmKR} ${not empty frontGoodsVO.goodsView.binCd ? frontGoodsVO.goodsView.binCd : ''}</p>";
			html += '	<p class="tx02"></p>';
			html += '	<div class="in_bx">';
			html += '		<div class="sel_l">';
			html += '			<div class="bx_sel">';
			html += '				<a href="javascript:;" data-type="-" class="btn_option btn_minus">-</a>';
			html += '				<input type="text" class="option-count dev-optCount" value="1">';
			html += '				<a href="javascript:;" data-type="+" class="btn_option btn_plus">+</a>';
			html += '			</div>';
			html += '		</div>';
			html += '		<p class="price_r" data-price="${shopLibFunction:getExchange(frontGoodsVO.goodsView.price, wskin)}">${shopLibFunction:getExchange(frontGoodsVO.goodsView.price, wskin)}</p>';
			html += '	</div>';
			html += '	<div style="clear:both;"></div>';
			html += '</div>';
			$(".option_list").append(html);
			
			setTotalPrice();
		
		</c:if>
		
		// 옵션 수량별 가격설정
		$(document).on("click", ".btn_option", function(){
			var $prt = $(this).parents(".option-data");
			var $count = $prt.find(".dev-optCount");
			var $price = $prt.find(".price_r");
			var type = $(this).data("type");
			
			var count = "1";
			if(type == "-"){
				count = getNumber($count.val()) - 1 == 0 ? 1 :getNumber($count.val()) - 1 ;
			}else{
				count = getNumber($count.val()) + 1;
			}
			var price = getNumber($price.data("price"));
			$count.val( count );
			$price.html( comma(price * count) );
			$prt.find("[name=optionPriceArr]").val(comma(price * count));
			
			setTotalPrice();
		});
		
		//옵션 삭제
		$(document).on("click", ".btn_close_res_n", function(){
			$(this).parents(".option-data").remove();
			setTotalPrice();
		});
		
	});
	
	function setAddOptionNew(){
		var optionOptno = $("[name=option-group]").val();
		if (optionOptno != ""){
			var option2Name = $("[name=option-group]>option:selected").data("name") == undefined ? '' : ' ' + $("[name=option-group]>option:selected").data("name");
			var optionName;
			if ("${frontGoodsVO.goodsView.opttype}" == 'single' ) {
				optionName = option2Name;
			} else {
				optionName = $("[name=option-group]>option:selected").data("name") + option2Name;
			}
			
			var optionPrice = $("[name=option-group]>option:selected").data("price");
			var valiCheck = false;
			
			$("input[name='optionOptnoArr']").each(function() {
				if ($(this).val() == optionOptno) {
					valiCheck = true;
					return false;
				}
			});
			
			if (valiCheck) {
				alert("이미 추가한 옵션입니다.");
				return false;
			}
			
			var html = '';
			html += '<div class="bx_result02 option-data">';
			html += "	<input type='hidden' name='optionOptnoArr' value='" + optionOptno + "'>";
			html += "	<input type='hidden' name='optionPriceArr' value='" + optionPrice + "'>";
			html += '	<a href="javascript:;" class="btn_close_res_n">X</a>';
			html += "	<p class='tx01'>${frontGoodsVO.goodsView.goodsnmKR} ${not empty frontGoodsVO.goodsView.binCd ? frontGoodsVO.goodsView.binCd : ''}</p>";
			html += '	<p class="tx02">'+optionName+'</p>';
			html += '	<div class="in_bx">';
			html += '		<div class="sel_l">';
			html += '			<div class="bx_sel">';
			html += '				<a href="javascript:;" data-type="-" class="btn_option btn_minus">-</a>';
			html += '				<input type="text" class="option-count dev-optCount" value="1">';
			html += '				<a href="javascript:;" data-type="+" class="btn_option btn_plus">+</a>';
			html += '			</div>';
			html += '		</div>';
			html += '		<p class="price_r" data-price="'+comma(getNumber(optionPrice))+'">'+comma(getNumber(optionPrice))+'</p>';
			html += '	</div>';
			html += '	<div style="clear:both;"></div>';
			html += '</div>';
			$(".option_list").append(html);
		}
		
		setTotalPrice();
	}
	
	function setTotalPrice() {
		var totalPrice = 0;
		var addOptPrice = Number($("#optionValue").val());
		$("[name=optionPriceArr]").each(function(){
			totalPrice += getNumber($(this).val());
		});
		
		totalPrice += addOptPrice;
		$(".tot_price").text( comma(totalPrice) );
	}
	
	function getNumber(_value){
		if(_value == "") return 0;
		var result = _value.replace(/[^0-9]/g,"");
		return Number(result);
	}
</script>

<%
	//옵션 select box 데이터 생성
/* 
Properties memberInfo = null;
memberInfo = (Properties)request.getAttribute("memberInfo");
String tmp = String.valueOf(request.getAttribute("tmp"));
String tmp1 = String.valueOf(request.getAttribute("tmp1"));
String tmp2 = String.valueOf(request.getAttribute("tmp2"));
Properties rstProp = null;
String[][] optInfo       = null;   // 옵션정보
StringTokenizer st = null;
String[] arrOpt1      = null;
String goodsNo = "";
int i = 0;
int j = 0;


optInfo = (String[][])request.getAttribute("optInfo");
st = (StringTokenizer)request.getAttribute("st");
arrOpt1 = (String[])request.getAttribute("arrOpt1");
rstProp = (Properties)request.getAttribute("rstProp");
goodsNo = (String)request.getAttribute("goodsNo");

if ( null!=optInfo &&  !"|".equals(optInfo[0][0]) ) {
   i = 0;
   j = 1;
   while( i < optInfo.length ){
      st = new StringTokenizer(optInfo[i][0], "|");
      if ( st.hasMoreTokens() ) {
         tmp1 = st.nextToken();
         if ( 0 > tmp.indexOf(tmp1) ) {
            tmp += tmp1 + ",";
            if ( 0 < i ) tmp2 += ");\n";
            tmp2 += "opt['" + (j++) + "'] = new Array(\"('== 옵션선택 ==','','')\"";
         }
         tmp1 = st.nextToken();
         tmp2 += ",\"('" + tmp1 + "','" + tmp1 + "','" + ((0 < Integer.parseInt(optInfo[i][5])) ? optInfo[i][5] : "soldout") + "')\"";
      }
      i++;
   }
   if ( 0 < i ) tmp2 += ");";
   tmp = tmp.substring(0, tmp.length()-1);
}

arrOpt1 = tmp.split(",");
 */
%>

<%
	/* 
ResultTable optionAddList	= null;	// 필수옵션목록
String[] arrOptNm		= new String[]{"", ""};
String[] addOptNmArray 	= null;	// 추가옵션명

optionAddList = (ResultTable)request.getAttribute("optionAddList");
arrOptNm = (String[])request.getAttribute("arrOptNm");
addOptNmArray = (String[])request.getAttribute("addOptNmArray");
 */
%>

<!-- <header class="page-header page-header-banner x-goods-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h1 class="page-title">MARKET</h1>
		</div>
	</div>
</header> -->
<!-- <div class="product-page-area"> -->

<form id="frmView" name="frmView" method="post">
	<input type="hidden" name="mode" value="addItem" />
	<input type="hidden" name="goodsno" id="goodsno" value="${frontGoodsVO.goodsno }" />
	<input type="hidden" name="goodsCoupon" value="0" />
	<input type="hidden" name="optionsList" value="" />
	<input type="hidden" name="goodsType" value="market" />
	<%-- <input type="hidden" name="goods_category" value="${category}" /> --%>
	<input type="hidden" name="category" id="category" value="${frontGoodsVO.category }" />
	<input type="hidden" name="returnUrl" value="${returnUrl}" />
	<input type="hidden" id="totalValue" name="totalValue" value="${frontGoodsVO.goodsView.price}" />
	<input type="hidden" id="goodsPrice" name="goodsPrice" value="${frontGoodsVO.goodsView.price}" />
	<input type="hidden" id="optionValue" name="optionValue" value="" />
	<input type="hidden" id="ea" name="ea" value="1" />
	<input type="hidden" name="ordno" id="ordno" value="${frontGoodsVO.goodsView.ordno }" /><%-- 리뷰작성시 사용 --%>

	<div class="x-goods-view container_view">
		<div class="location_n">${currPosition}</div>

		<div class="detail_wrap">
			<div class="bx_l">
				<c:if test="${('' eq frontGoodsVO.goodsView.imgs) }">
					<img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/500x330.png" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/500x330.png'" />
				</c:if>
				<c:set var="imgArr" value="${fn:split(frontGoodsVO.goodsView.imgm,'|') }" />
				<c:set var="images" value="${imgArr[0] }" />
				<c:set var="img_idx" value="${fn:indexOf(images,'|') + 1 }" />
				<c:set var="img_len" value="${fn:length(images) }" />
				<c:if test="${!('' eq images) }">
					<div class="bx_pr_img"><!-- product-page-product-wrap jqzoom-stage jqzoom-stage-lg -->
						<div class="clearfix">
							<a href="${fn:substring( images, img_idx, img_len) }" id="jqzoom" data-rel="gal-1" rel="gal-1"> <img class="full-width" src="${fn:replace(images,'|','')}" noImagePath='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/500x330.png' />
							</a>
						</div>
					</div>
				</c:if>
				<div class="bx_pr_s_list">
					<ul class="jqzoom-list">
						<c:forEach var="imagess" items="${imgArr }" varStatus="status">
							<c:if test="${!('' eq imagess) }">
								<li style="float:none; width:100%;">
									<a href="javascript:void(0)" data-rel="{gallery:'gal-1', smallimage: '${imagess}', largeimage: '${imagess}'}" style="width:100%;">
										<img src="${fn:replace(imagess,'|','')}" alt="Image Alternative text" title="Image Title" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/50x50.png'"  style="width:100%; padding:0; box-shadow:none;" />
									</a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="bx_r">
				<div class="position_mob">
					<div class="tx_brand">
						<c:if test="${frontGoodsVO.brandno ne 0 }">
							${shopLibFunction:getGoodsBrandName(frontGoodsVO.brandno)}
						</c:if>
					</div>
					<div class="tx_tit">${frontGoodsVO.goodsView.goodsnmKR}<c:if test="${frontGoodsVO.goodsView.binCd ne ''}"><%--${frontGoodsVO.goodsView.binCd}--%></c:if></div>
					<div class="detail_info_n">
						<div class="tx_price02"><c:if test="${frontGoodsVO.goodsView.priceRate >= 3}">${shopLibFunction:getExchange(frontGoodsVO.goodsView.consumer, wskin)}</c:if></div>
						<div class="tx_price01">
							<span class="tx01">${shopLibFunction:getExchange(frontGoodsVO.goodsView.price, wskin)}</span>
							<span class="tx02"><c:if test="${frontGoodsVO.goodsView.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${frontGoodsVO.goodsView.priceRate}"/>%</c:if></span>
							<c:if test="${frontGoodsVO.goodsView.reserve > 0}">
							<span class="tx03">예상 적립금 : ${shopLibFunction:getExchange(frontGoodsVO.goodsView.reserve, wskin)}</span>
							<a href="javascript:void(0);" class="icon_alert icon_ly_n" style="position:relative;"><!-- ${ctx}/shop/service/ratingPolicy -->
								i
								<div class="layer_alert01">
									지급예상 적립금은 기본 유로화로 지급되며<br/>
									원화는 환율이 적용된 “참고용” 입니다. 
									<p><img src="/resources/shop/data/skin/kr/images/icon_b_b.png" alt="" /></p>
								</div>
							</a>
							</c:if>
							<script>
							$(document).ready(function(){
								$(".icon_ly_n").click(function(){
									if(!$(this).data("click")){
										$(".layer_alert01").css("display", "block");
										$(this).data("click", true);
									}else{
										$(".layer_alert01").css("display", "none");
										$(this).data("click", false);
									}
								});

								$(".size_guide").click(function(){
									if(!$(this).data("click")){
										$(".ly_size").css("display", "block");
										$(this).data("click", true);
									}else{
										$(".ly_size").css("display", "none");
										$(this).data("click", false);
									}
								});
							});
							</script>
							<span class="tx04">
								<c:if test="${frontGoodsVO.goodsView.stock == 0 }">
									[품절]
								</c:if>
							</span>
						</div>
						<!--  
						<div class="tx_info01">
							<c:if test="${frontGoodsVO.goodsView.euYn eq 'Y'}">
								<p class="bx_gray">FTA</p>
								관세 면제, 관련 세금 포함가($150이상, EU 원산지)
							</c:if>
						</div> -->
						<div class="tx_info02">
							<p class="in_tx">
								${frontGoodsVO.goodsView.shippingOrigin eq 'KR' ? '무료배송 (빠른배송)' : '무료배송 (일반배송)'}
								<a href="${ctx}/shop/service/orderPolicy" class="icon_alert ty01">i</a>
							</p>
							<p class="in_tx">${frontGoodsVO.goodsView.goodscd}</p>
						</div>
						<div class="tx_info03">
							<!-- 반품/교환 여부 : 
							고객 수령 후 반품/교환이 불가능한 상품입니다. -->
							<a href="${ctx}/shop/service/exchangePolicy">반품/교환 안내 <span>▶</span></a>
						</div>
					</div>
				</div>
				<div class="detail_info_n ty02">
					<div class="tx_size">
						<%--리스트 설정 --%>
						<c:set var="optList" value="" />
						<c:choose>
							<c:when test="${frontGoodsVO.goodsView.opttype eq 'single'}">
								<c:set var="optList" value="${frontGoodsVO.goodsOptionList}" />
							</c:when>
							<c:otherwise>
								<c:set var="optList" value="${opt2List}" />
							</c:otherwise>
						</c:choose>
						<c:if test="${not empty optList}">
							<select id="option-group" name="option-group" onchange="javascript:setAddOptionNew();">
								<option value="">Color / Size</option>
								<c:forEach var="opt2" items="${optList}" varStatus="status">
									<option ${opt2.stock eq 0 ? 'style="color: red;"' : ''} value="${opt2.sno}" data-price="${shopLibFunction:getExchange(opt2.price, wskin)}" data-name="${opt2.opt1 }" ${opt2.stock eq 0 ? 'disabled' : '' }>${opt2.opt1} ${opt2.stock eq 0 ? '품절' : ''}</option>
								</c:forEach>
							</select>
							
							<c:set var="sizeGuide" value="" />
							<c:choose>
								<c:when test="${fn:substring(frontGoodsVO.category, 0, 6) eq '001003'}">
									<c:set var="sizeGuide" value="man_cloth" />
								</c:when>
								<c:when test="${fn:substring(frontGoodsVO.category, 0, 6) eq '001004'}">
									<c:set var="sizeGuide" value="man_shoes" />
								</c:when>
								<c:when test="${fn:substring(frontGoodsVO.category, 0, 6) eq '002003'}">
									<c:set var="sizeGuide" value="women_cloth" />
								</c:when>
								<c:when test="${fn:substring(frontGoodsVO.category, 0, 6) eq '002004'}">
									<c:set var="sizeGuide" value="women_shoes" />
								</c:when>
								<c:when test="${fn:substring(frontGoodsVO.category, 0, 6) eq '002003'}">
									<c:set var="sizeGuide" value="women_cloth" />
								</c:when>
								<c:when test="${fn:substring(frontGoodsVO.category, 0, 9) eq '003001001' or fn:substring(frontGoodsVO.category, 0, 9) eq '003002001'}">
									<c:set var="sizeGuide" value="kids_cloth" />
								</c:when>
								<c:when test="${fn:substring(frontGoodsVO.category, 0, 9) eq '003001002' or fn:substring(frontGoodsVO.category, 0, 9) eq '003002002'}">
									<c:set var="sizeGuide" value="kids_shoes" />
								</c:when>
							</c:choose>
							
							<c:if test="${sizeGuide ne ''}">
								<a href="javascript:;" class="size_guide" style="position:relative; z-index:1000;">
									사이즈 가이드
									<div class="ly_size"><img src="/resources/shop/data/skin/kr/images/${sizeGuide}.jpg" alt="" /></div>
								</a>
							</c:if>
						</c:if>
					</div>
					<div class="option_list"></div>
					<div class="bx_result">
						<p class="tx01">총 예상 결제금액</p>
						<p class="price_r tot_price">0</p>
					</div>
					<div class="bx_btns">
						<a href="javascript:order()" class="btn_cart">주문하기</a>
						<ul>
							<li style="float:left; width:50%; padding-right:7px;">
								<a href="javascript:goodscart()" class="btn_wish_list">장바구니 담기</a>
							</li>
							<li style="float:left; width:50%; padding-left:8px;">
								<a href="javascript:wishlist()" class="btn_wish_list">위시리스트 추가</a>
							</li>
						</ul>
						<div style="clear:both;"></div>
					</div>
					<div class="bx_sns">
						<ul>
							<li><div id="kakaostory-share-button">카카오톡</div></li>
							<li><a href="javascript:sharefacebook('${baseUrl}/shop/goods/goods_view?goodsno=${frontGoodsVO.goodsno}')">페이스북</a></li>
							<li><a href="javascript:sharetwitter('${baseUrl}/shop/goods/goods_view?goodsno=${frontGoodsVO.goodsno}','${frontGoodsVO.goodsView.goodsnmKR}')">트위터</a></li>
							<li><a href="javascript:copyUrl()">Copy URL</a></li>
						</ul>
					</div>
				</div>
				<%
					//if ( !"0".equals(memberInfo.getProperty("dc", "0")) ) {
				%>
				<%-- <p class="product-page-desc-lg"><span id=obj_realprice>회원 할인가 : ${shopLibFunction:getExchange(realprice, wskin)}</span></p> --%>
				<%
					//}
				%>

				<c:if test="${frontGoodsVO.goodsView.coupon !=''} }">
					<p class="product-page-desc-lg">쿠폰적용가 : ${frontGoodsVO.goodsView.coupon}원</p>
				</c:if>

				<p class="product-page-desc-lg">${frontGoodsVO.goodsView.shortdesc }</p>
				<ul class="product-page-option-list">
					<!-- eunjung
					<c:if test="${frontGoodsVO.goodsView.sellerCd ne '' }">
						<li>판매사 : ${frontGoodsVO.goodsView.sellerCd}</li>
					</c:if> -->


					<%-- <c:if test="${frontGoodsVO.goodsView.reserve !='0' }">
						<li>적립금 :
							${shopLibFunction:getExchange(frontGoodsVO.goodsView.reserve, wskin)}
							원</li>
					</c:if> --%>

					<%--
					<li>상품코드 : ${frontGoodsVO.goodsView.goodscd}</li>
					<c:if test="${frontGoodsVO.goodsView.origin ne '' }">
						<li>원산지 : ${frontGoodsVO.goodsView.origin}</li>
					</c:if> --%>

					<!-- <c:if test="${frontGoodsVO.brandno ne 0 }">
						<li>브랜드 :
							${shopLibFunction:getGoodsBrandName(frontGoodsVO.brandno)} <a
							href="${ctx}/shop/goods/goods_brand?brandno=${frontGoodsVO.brandno }">[브랜드바로가기]</a>
						</li>
					</c:if> -->

					<!-- 상품 추가 정보가 있을 경우
					<c:if test="${'|||||' ne frontGoodsVO.goodsView.extitle}">
						<c:forEach var="goodsEx"
							items="${fn:split(frontGoodsVO.goodsView.extitle , '|') }"
							varStatus="status">
							<li class="clearfix">
								<%-- <c:set var="i" value="${status.index+1 }" /> --%> <i>${goodsEx }
									: </i> <c:choose>
									<c:when test="${status.index+1 eq 1 }">
										<i>${frontGoodsVO.goodsView.ex1 }</i>
										<%-- <div style="height:40px; margin-top:11px;">${frontGoodsVO.goodsView.ex1 }</div> --%>
									</c:when>
									<c:when test="${status.index+1 eq 2 }">
										<i>${frontGoodsVO.goodsView.ex2 }</i>
									</c:when>
									<c:when test="${status.index+1 eq 3 }">
										<i>${frontGoodsVO.goodsView.ex3 }</i>
									</c:when>
									<c:when test="${status.index+1 eq 4 }">
										<i>${frontGoodsVO.goodsView.ex4 }</i>
									</c:when>
									<c:when test="${status.index+1 eq 5 }">
										<i>${frontGoodsVO.goodsView.ex5 }</i>
									</c:when>
									<c:otherwise>
										<i>${frontGoodsVO.goodsView.ex6 }</i>
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
					</c:if>  -->

					<%-- 2017-08-24 : 추가배송비 보이기, 감추기 ▼ 추가 --%>
					<!-- <li class="clearfix">
						<h5 class="product-page-option-title" style="width: 60px">배송비</h5>
						<c:if test="${frontGoodsVO.goodsView.deliverytype eq '0' }">
							<c:forEach items="${frontGoodsVO.deliveryInfoList}"
								var="defaultDeliveryInfo">
								<input type="hidden" name="deliveryNo"
									value="${defaultDeliveryInfo.no}" />
								<div style="height: 40px; margin-top: 11px;">
									[${defaultDeliveryInfo.rDelivery}-${defaultDeliveryInfo.rDeliType }]
									${defaultDeliveryInfo.rDefault}원
									<%--(${defaultDeliveryInfo.free} 원 이상 구매시 무료) --%>
								</div>
							</c:forEach>
						</c:if> <c:if test="${frontGoodsVO.goodsView.deliverytype eq '1' }">
							<div style="height: 40px; margin-top: 11px;">무료배송</div>
						</c:if> <c:if test="${frontGoodsVO.goodsView.deliverytype eq '2' }">
							<div style="height: 40px; margin-top: 11px;">
								${shopLibFunction:getExchange(frontGoodsVO.goodsView.goodsdelivery, wskin)}
							</div>
						</c:if> <c:if test="${frontGoodsVO.goodsView.deliverytype eq '3' }">
							<div style="height: 40px; margin-top: 11px;">착불
								${shopLibFunction:getExchange(frontGoodsVO.goodsView.goodsdelivery, wskin)}
							</div>
						</c:if> <%-- 2017-08-24 : 상품에 추가배송비 출력부 추가 --%> <c:if
							test="${frontGoodsVO.overDeliveryInfo != null}">
							<div class="js_overdeliveryList">
								<div style="height: 20px; margin-bottom: 5px; margin-left: 70px">아래
									지역에는 추가배송비가 부과됩니다.</div>
								<c:forEach items="${frontGoodsVO.overDeliveryInfo}"
									var="overDeliveryInfo">
									<div
										style="height: 20px; margin-bottom: 5px; margin-left: 70px">${overDeliveryInfo.key}
										:
										${overDeliveryInfo.value }
<%--										<fmt:formatNumber value="${overDeliveryInfo.value }" --%>
<%--											pattern="#,### " /> --%>
										원
									</div>
								</c:forEach>
							</div>
						</c:if>
					</li> -->
					<%-- 
					<c:if test="${(frontGoodsVO.goodsView.usegoodsadd eq '1') and (frontGoodsVO.goodsOptionList ne null) and (!empty frontGoodsVO.goodsOptionList)}">
						<tr class="product-option-list-tab">
							<th colspan="2"></th>
						</tr>
						<c:choose>
							<c:when test="${frontGoodsVO.goodsView.opttype eq 'double'}">
								<tr>
									<th><span class="product-page-option-detail-title">상품옵션</span>
									</th>
									<td>
										<c:forEach var="opt2" items="${opt2List }" varStatus="status">
											<ul class="option-choice">
												<li>
													<div class="option-head">
														<strong class="price">+ ${shopLibFunction:getExchange(opt2.price, wskin)}</strong>
														<span class="name">${opt2.opt1 } </span>
														<label class="option-radio"><input type="radio" name="option-group" value="${opt2.sno}" onchange="goodsoptValue();" data-name="${opt2.opt1 }"><span></span></label>
													</div>
													<div class="option-body">
														<strong>상품옵션</strong>
														<p>${opt2.optexplain}</p>
														<strong>2차옵션</strong>
														<ul class="detail-option dev-${opt2.sno }">
															
														</ul>
													</div>
												</li>
											</ul>
										</c:forEach>
									</td>
								</tr>
							</c:when>
							<c:when test="${frontGoodsVO.goodsView.opttype eq 'single'}">
								<c:set var="optList" value="${frontGoodsVO.goodsOptionList}" />
								<tr>
									<th><span class="product-page-option-detail-title">상품옵션</span>
									</th>
									<td>
										<c:forEach var="opt2" items="${optList}" varStatus="status">
											<ul class="option-choice">
												<li>
													<div class="option-head">
														<strong class="price">+ ${shopLibFunction:getExchange(opt2.price, wskin)}</strong>
														<span class="name <c:if test="${opt2.stock == 0}">soldout</c:if>">${opt2.opt1 } </span>
														<label class="option-radio"><input type="radio" name="option-group" <c:if test="${opt2.stock == 0}">disabled</c:if> value="${opt2.sno}" data-price="${opt2.price}" data-name="${opt2.opt1 }" onchange="setAddOption(this);" ><span></span></label>
													</div>
													<!--<div class="option-body">
														<strong>상품옵션</strong>
														<p>${opt2.optexplain}</p>
													</div> -->
												</li>
											</ul>
										</c:forEach>
									</td>
								</tr>
							</c:when>
						</c:choose>
					</c:if>
					--%>

					<!-- 상품 추가 옵션이 있을 경우 -->

					<c:if test="${frontGoodsVO.goodsView.useadd eq '1' }">

						<c:set var="addOptNmArr"
							value="${fn:split(frontGoodsVO.goodsView.addoptnm, '|') }" />
						<c:forEach var="addOptNmArr" items="${addOptNmArr }"
							varStatus="status">
							<c:set var="addOptNmReqArr" value="${fn:split(addOptNmArr, '^')}" />

							<c:if
								test="${frontGoodsVO.goodsAddOptionList != null && fn:length(frontGoodsVO.goodsAddOptionList) > 0}">
								<c:forEach var="goodsAddOptionList"
									items="${frontGoodsVO.goodsAddOptionList }"
									varStatus="status01">
									<c:if
										test="${addOptNmReqArr[0] eq goodsAddOptionList.addOptTitle }">
										<li class="clearfix">
											<h5 class="product-page-option-title" style="width: 60px">${goodsAddOptionList.addOptTitle}</h5>
											<select id="addopt[]" class="product-page-option-select"
											name="addopt" title="옵션선택" onchange="addoptValue(this);"
											label="${goodsAddOptionList.addOptTitle }"
											${addOptNmReqArr[1]  == 'o' ? 'required':''}>
												<option value="" selected="selected">옵션선택</option>
												<c:forEach var="addOptContents"
													items="${goodsAddOptionList.addOptContents }"
													varStatus="status02">
													<option value="${addOptContents.optValue }">${addOptContents.optText }</option>
												</c:forEach>
										</select>
										</li>
									</c:if>

								</c:forEach>
							</c:if>

						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</form>
<!-- </div> -->

<!-- 회원 직접 다운로드 쿠폰  -->
<%-- <div class="container">
               <div class="row">
				<div>
						<c:forEach var="couponList"  items="${frontGoodsVO.couponList }"  varStatus="status">
							<c:if test="${status.index % 6 == 0 }">
								<br/>
							</c:if>
							<div class="col-md-2">
								<a href="#" onclick="ajaxDownCoupon('${couponList.couponcd}', '${frontGoodsVO.goodsno }','${frontGoodsVO.goodsView.goodsnmKR }')"><img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/coupon0${couponList.couponimg == 0 ? '1' : couponList.couponimg+1 }.gif"></a>
								<br/>${couponList.price } ${fn:indexOf(couponList.price, '%') == -1 ? '원' : '' }
								<br/>${couponList.ability == 0 ? '할인': '적립' } 쿠폰
							</div>
						</c:forEach>
				</div>
			</div>
		</div> --%>

<!-- <div class="container">
	<c:if test="${!empty frontGoodsVO.couponList }">
		<h3 class="widget-title-lg">Coupon</h3>
	</c:if>
	<div class="row row-sm-gap coupon-wrap" data-gutter="10">
		<c:forEach var="couponList" items="${frontGoodsVO.couponList }"
			varStatus="status">
			<div class="col-md-2">
				<a class="banner-category" href="#"
					onclick="ajaxDownCoupon('${couponList.couponcd}', '${frontGoodsVO.goodsno }','${frontGoodsVO.goodsView.goodsnmKR }')">
					<img class="banner-category-img"
					src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/coupon0${couponList.couponimg == 0 ? '1' : couponList.couponimg+1 }.gif"
					alt="Image Alternative text" title="Image Title" />
					<h5 class="banner-category-title">${couponList.coupon}<%-- ${couponList.ability == 0 ? '할인': '적립' } 쿠폰 --%>
					</h5>
					<p class="banner-category-desc">${fn:indexOf(couponList.price, '%') == -1 ? (shopLibFunction:getExchange(couponList.price, wskin)) : couponList.price }
						${fn:indexOf(couponList.price, '%') == -1 ? '원' : '' }
						<%-- <p class="banner-category-desc">${shopLibFunction:getExchange(couponList.price, wskin) } ${fn:indexOf(couponList.price, '%') == -1 ? '원' : '' } --%>
					</p>
				</a>
			</div>
		</c:forEach>
	</div>
</div>


<div class="gap"></div> -->

<div class="container_view">
	<div class="tabbable product-tabs">
		<!-- <ul class="nav nav-tabs" id="myTab">
			<li class="active"><a href="#tab-1" data-toggle="tab">상세정보</a></li>
			<li><a href="#tab-2" data-toggle="tab">상품평</a></li>
			<li><a href="#tab-3" data-toggle="tab">관련상품</a></li>
			<li><a href="#tab-4" data-toggle="tab">질문답변</a></li>
		</ul> -->
		<link rel="stylesheet" href="/resources/shop/data/skin/kr/css/swiper.min.css">
		<script src="/resources/shop/data/skin/kr/js/swiper.min.js"></script>
		<div class="detail_cont_wrap"><!-- tab-content -->
			<div class="in_cont01" id="tab-3">
				<p class="detail_stit01">추천상품</p>
				<div class=""><!-- mob_scroll -->
					<div class="sub_list01 view_remom" style="overflow:hidden;">
							<c:if test="${fn:length(frontGoodsVO.relationGoodsList) > 0}">
								<!-- 관련상품 -->
								<ul class="swiper-wrapper">
									<!-- 자동 수동 여부 주석처리 0:자동 1:수동  -->
									<%-- <c:if test="${frontGoodsVO.goodsView.relationis eq '0' }"> --%>
									<c:forEach var="randomRecipe" items="${frontGoodsVO.relationGoodsList }" varStatus="status">
										<li class="swiper-slide">
											<div class="in_bx"> 
												<div class="bx_thum">
													<c:if test="${not empty randomRecipe.imgs}">
														<a href="${ctx}/shop/goods/goods_view?goodsno=${randomRecipe.goodsno }&category=${randomRecipe.category}"><img class="" src="${fn:replace(randomRecipe.imgs,'|','')}" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'" /></a>
													</c:if>
												</div>
												<div class="bx_info">
													<div class="tx_brand">${randomRecipe.brandnm}</div>
													<div class="tx_tit">
														<a href="${ctx}/shop/goods/goods_view?goodsno=${randomRecipe.goodsno }&category=${randomRecipe.category}">${randomRecipe.goodsnmKR }</a>
													</div>
													<fmt:parseNumber var="priceRate" type="number" value="${randomRecipe.priceRate}" />
													<div class="tx_price01">${shopLibFunction:getExchange(randomRecipe.price, wskin)}<span class="tx_per"><c:if test="${priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${randomRecipe.priceRate}"/>%</c:if></span></div>
													<div class="tx_price02"><c:if test="${priceRate >= 3}">${shopLibFunction:getExchange(randomRecipe.consumer, wskin)}</c:if></div>
												</div>
											</div>
										</li>
									</c:forEach>
									<%-- </c:if> --%>
								</ul>
								<!-- //관련상품 -->
								<div class="swiper-pagination" style="display:none;"></div>
							</c:if>
							<c:if test="${(fn:length(frontGoodsVO.relationGoodsList) <= 0)}">
								관련상품이 없습니다
							</c:if>
						<script>
							var swiper = new Swiper('.view_remom', {
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
						<div style="clear:both;"></div>
					</div>
				</div>
			</div>

			<div class="" id="tab-1"><!-- tab-pane fade in active -->
				<div class="in_cont02" style="border-bottom:0;">
					<p class="detail_stit02">상품상세정보</p>
					<div style="padding:60px 0 0 0;">
						${fn:replace(fn:replace(fn:replace(frontGoodsVO.goodsView.longdesc, '&lt;', '<'), '&gt;', '>'), '&#47;', '/') }
					</div>
					
					<div style="text-align: center;">
						<c:forEach var="imagess" items="${imgArr }" varStatus="status">
							<c:if test="${!('' eq imagess) }">
								<img src="${fn:replace(imagess,'|','')}" style="max-width:100%; padding:20px 20px 0;" />
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="tab-4" style="display:none">
				<%-- <iframe id="inqna1" src="goods_qna_list?goodsno=${goodsNo }" frameborder="0" marginwidth="0" marginheight="0" width="100%" height="900px" scrolling="no"></iframe> --%>
				<p class="goodsQna" id="goodsQna"></p>
			</div>
			
			<c:if test="${not empty frontGoodsVO.brandList}">
				<c:if test="${not empty frontGoodsVO.brandList[0].imgPC and not empty frontGoodsVO.brandList[0].imgMO}">
					<div class="img_bx01" style="border-top:2px solid #d0d0d0;">
						<c:if test="${not empty frontGoodsVO.brandList[0].imgPC}">
							<a href="${ctx}/shop/goods/goods_brand?brandno=${frontGoodsVO.brandList[0].sno}"><img src="${frontGoodsVO.brandList[0].imgPC}" alt="${frontGoodsVO.brandList[0].brandnmKR}" style="width:100%;" class="img_pc" /></a>
						</c:if>
						<c:if test="${not empty frontGoodsVO.brandList[0].imgMO}">
							<a href="${ctx}/shop/goods/goods_brand?brandno=${frontGoodsVO.brandList[0].sno}"><img src="${frontGoodsVO.brandList[0].imgMO}" alt="${frontGoodsVO.brandList[0].brandnmKR}" style="width:100%;" class="img_mob" /></a>
						</c:if>
					</div>
				</c:if>
			</c:if>

			<div class="in_cont02">
				<p class="detail_stit02">배송안내</p>
				<div class="in_tx01">
					- FREE Worldwide shipping<br/>
					- limited time only
				</div>
			</div>

			<div class="in_cont03">
				<p class="detail_stit02">상품문의</p>
				<div class="bx_qna">
					<p class="tx01">1:1 상품문의</p>
					<p class="tx02">궁금하신 점을 남겨주시면 최선을 다해 답변드리겠습니다.</p>
					<a href="javascript:;" class="btn_qna">상품 문의하기</a>
				</div>
			</div>

			<div class="in_cont04" >
				<p class="detail_stit02">상품리뷰</p>
				<div class="bx_review">
					<div class="sel_r">
						<select id="rsort" class="category-selections-select form-control">
							<option value="regdt">최신순</option>
							<option value="reviewimg">포토순</option>
							<option value="point">별점순</option>
						</select>
					</div>
					<ul id="goodsReview"></ul>
				</div>
				<a href="#" class="btn_write_review">리뷰 작성하기</a>
			</div>
		</div>
	</div>
</div>

<!-- 팝업 상품 문의하기 -->
<script>
$(document).ready(function(){
	$(".btn_qna").click(function(e){
		e.preventDefault();
		$.ajax({
			dataType : 'json',
			type : 'POST',
			async: false,
			url : '/shop/member/ajaxfrontLogin.doJson',
			success : function (result) {
				if( result.isLogin ){
					$("#gq_subject").val("");
					$("#gq_contents").val("");
					$(".pop_qna_good").css("display", "block");
					$(window).scrollTop(0);
				}else{
					alert("로그인 후 상품문의가 가능합니다.");
					return;
				}
			}
		});
	});
	$(".pop_qna_good .btn_pop_close_n").click(function(){
		$(".pop_qna_good").css("display", "none");
	});
	
	$("#qna_submit").on("click",function(){
		if( $.trim($("#gq_subject").val()) == "" ){
			alert("문의하실 제목을 입력해주세요.");
			$("#gq_subject").focus();
			return;
		}
		if( $.trim($("#gq_contents").val()) == "" ){
			alert("문의하실 내용을 입력해주세요.");
			$("#gq_contents").focus();
			return;
		}
		
		var data = {
			"mode"		: "add_qna",
			"goodsno"	: "${frontGoodsVO.goodsno}",
			"subject"	: $("#gq_subject").val(),
			"contents"	: $("#gq_contents").val(),  
		};
		
		ajaxCallJson("/shop/goods/ajaxGoodsQnaWrite.doJson", data, function(data){
			alert("문의가 등록되었습니다."); 
			$(".pop_qna_good").css("display", "none");
		});
	});
	
});
</script>
<div class="pop_wrap pop_qna_good">
	<div class="bx_bg_op"></div>
	<div class="bx_in_pop">
		<div class="in_bx">
			<div class="tit_bx">
				상품 문의하기
				<a href="javascript:void(0);" class="btn_pop_close_n">X</a>
			</div>
			<div class="cont_bx">
				<div class="tp_info">
					<ul>
						<li>
							<div class="bx_thum">
								<img class="full-width" src="${fn:replace(images,'|','')}" noImagePath='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/500x330.png' />
							</div>
							<dl>
								<dt>
									<c:if test="${frontGoodsVO.brandno ne 0 }">
										${shopLibFunction:getGoodsBrandName(frontGoodsVO.brandno)}
									</c:if>
								</dt>
								<dd>
									${frontGoodsVO.goodsView.goodsnmKR}<c:if test="${frontGoodsVO.goodsView.binCd ne ''}"> ${frontGoodsVO.goodsView.binCd}</c:if>
								</dd>
							</dl>
							<p class="tx01"><c:if test="${frontGoodsVO.goodsView.priceRate >= 3}">${shopLibFunction:getExchange(frontGoodsVO.goodsView.consumer, wskin)}</c:if></p>
							<div>
								<p class="tx02">${shopLibFunction:getExchange(frontGoodsVO.goodsView.price, wskin)}</p>
								<p class="tx03"><c:if test="${frontGoodsVO.goodsView.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${frontGoodsVO.goodsView.priceRate}"/>%</c:if></p>
							</div>
						</li>
					</ul>
				</div>
				<div class="bx_sel">
					<select class="sel_ty01">
						<option>상품문의</option>
						<option>옵션, 사이즈, 색상 등</option>
						<option>배송문의</option>
						<option>기타(직접입력)</option>
					</select>
				</div>
				<div class="bx_inp">
					<input type="text" id="gq_subject" name="gq_subject" class="inp_ty01" placeholder="문의하실 제목을 입력해주세요."/>
				</div>
				<div class="bx_txa">
					<textarea id="gq_contents" name="gq_contents" rows*="" cols="*" placeholder="문의하실 내용을 입력해주세요."></textarea>
				</div>
				<div class="bt_tx01">- 상품문의 답변은 <span style="font-weight:500;">마이페이지 > 나의쇼핑 > 나의 문의내역</span> 에서 확인 할 수 있습니다.</div>
				<div style="text-align:center;">
					<a href="javascript:;" id="qna_submit" class="btn_pop_ask">문의하기</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- //팝업 상품 문의하기 -->




<!-- 팝업 리뷰 작성하기 -->
<script>
$(document).ready(function(){
	$(".btn_write_review").click(function(e){
		e.preventDefault();
		<c:choose>
			<c:when test="${empty userInfo}">
				alert("로그인 후 리뷰작성이 가능합니다.");
			</c:when>
			<c:when test="${frontGoodsVO.goodsView.buycnt gt frontGoodsVO.goodsView.reviewcnt}">
				$.ajax({
					data : {"goodsno" : $("#goodsno").val(), "ordno" : "${frontGoodsVO.goodsView.ordno}"},
					dataType : 'html',
					type : 'POST',
					async: false,
					url : '/shop/goods/popup_goods_review',
					success : function (result) {
						$(".pop_good_review").html(result);
					}
				});
				$(".pop_good_review").css("display", "block");
				$(window).scrollTop(0);
			</c:when>
			<c:otherwise>
				alert("리뷰작성이 가능한 구매내역이 없습니다.");
			</c:otherwise>
		</c:choose>
	});
	
	$(document).on('click', ".pop_good_review .btn_pop_close_n", function(){
		$(".pop_good_review").css("display", "none");
	});
});
</script>
<style>
/* input file */
.filebox input[type="file"] {position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px; overflow: hidden; clip:rect(0,0,0,0); border: 0;}
.filebox label {display:block; width:130px; height:130px; color:#252525; vertical-align:top; text-align:center; text-decoration:none; cursor: pointer;}

/* named upload */
.filebox .upload-name {float:left; display:block; width:493px; height:36px; font-size:14px; line-height:36px; background:#fff; border:1px solid #ced4da; border-radius:3px; padding:0 0 0 20px; margin:0 6px 0 0; vertical-align:top;
-webkit-appearance: none;
-moz-appearance: none;
appearance: none;
}

/* imaged preview */
.filebox .upload-display {/*margin-bottom: 5px;*/}
</style>
	<!-- 팝업 리뷰 작성하기 -->
	<div class="pop_wrap pop_good_review"></div>
	<!-- //팝업 리뷰 작성하기 -->

<script>
$(document).on('click', ".p_r_view", function(e){
	e.preventDefault();
	$(".mySwiper .swiper-wrapper, .mySwiper2 .swiper-wrapper").empty();
	
	var sno = $(this).data("no");
	ajaxCallJson('/shop/goods/ajaxReviewFileList.dojson',
	{
		'sno' : sno
	},
	function(data) {
		var reviewFileList = data.reviewFileList;
		var fileList = "";
		if (reviewFileList.length > 0) {
			for (var i = 0; i < reviewFileList.length; i++) {					
				fileList += '<div class="swiper-slide">';
				fileList += '	<img src="' + reviewFileList[i].img + '" alt="" style="width:100%;" />';
				fileList += '</div>';
			}
			$(".mySwiper .swiper-wrapper").html(fileList);
			$(".mySwiper2 .swiper-wrapper").html(fileList);
			
			$(".pop_photo_review").css("display", "block");
		    var swiper = new Swiper(".mySwiper", {
		  	  spaceBetween: 10,
		  	  slidesPerView: 3,
		  	  freeMode: true,
		  	  watchSlidesVisibility: true,
		  	  watchSlidesProgress: true,
		    });
		    var swiper2 = new Swiper(".mySwiper2", {
		  	  spaceBetween: 10,
		  	  navigation: {
		  	    nextEl: ".swiper-button-next",
		  	    prevEl: ".swiper-button-prev",
		  	  },
		  	  thumbs: {
		  	    swiper: swiper,
		  	  },
		    });
		}

	},
	function(e) {
		console.log("조회에 실패하였습니다");
	});
	
	$(".pop_photo_review").css("display", "block");

    var swiper = new Swiper(".mySwiper", {
  	  spaceBetween: 10,
  	  slidesPerView: 3,
  	  freeMode: true,
  	  watchSlidesVisibility: true,
  	  watchSlidesProgress: true,
    });
    var swiper2 = new Swiper(".mySwiper2", {
  	  spaceBetween: 10,
  	  navigation: {
  	    nextEl: ".swiper-button-next",
  	    prevEl: ".swiper-button-prev",
  	  },
  	  thumbs: {
  	    swiper: swiper,
  	  },
    });
});
$(document).on('click', ".pop_photo_review .btn_pop_close_n", function(){
	$(".pop_photo_review").css("display", "none");
});
</script>
<style>
.mySwiper2 .swiper-button-next, .swiper-button-prev {top: 50% !important;}
/*.mySwiper .swiper-slide {background:#000;}
.mySwiper .swiper-slide img {opacity:0.7}*/
.mySwiper .swiper-slide-thumb-active img {border:1px solid #ddd;}
</style>
	<!-- 팝업 포토리뷰 -->
	<div class="pop_wrap pop_photo_review" style="position:fixed;display:none;">
		<div class="bx_bg_op"></div>
		<div class="bx_in_pop">
			<div class="in_bx">
				<div class="tit_bx">
					포토 리뷰
					<a href="javascript:void(0);" class="btn_pop_close_n">X</a>
				</div>
				<div class="cont_bx">
					<div class="swiper-container mySwiper2" style="margin-bottom:20px;">
						<div class="swiper-wrapper">
						</div>
						<div class="swiper-button-next"></div>
						<div class="swiper-button-prev"></div>
					</div>
					<div class="swiper-container mySwiper">
						 <div class="swiper-wrapper">
						</div>
						<div style="clear:both;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //팝업 포토리뷰 -->

<script>
	$(document).ready(function() {
		
		var goodsno = $("#goodsno").val();
		//reviewLoad(1,goodsno);
		//qnaLoad(1,goodsno);
	
		if ('${frontGoodsVO.goodsView.metatitle}' == '1') {
			document.title = "${frontGoodsVO.goodsView.goodsnmKR}";
		}
	
		// 상품 이미지 로딩이 느려 오류가 날 수 있으므로 다시 적용
		$('.full-width').attr('src', $('.full-width').attr('src'));
	
	});

	if("${frontGoodsVO.goodsView.stock }" != 0 ) {
		if("${frontGoodsVO.goodsView.runout }" == 0 ) {
			Kakao.init('3058966aa2a1af2e919b16acb34227b5');
			
			Kakao.Link.createDefaultButton({
				container : "#kakaostory-share-button",
				objectType: "feed",
				content: {
					title: "${frontGoodsVO.goodsView.goodsnmKR}",
					description: "${frontGoodsVO.goodsView.shortdesc}",
					imageUrl: "${fn:split(frontGoodsVO.goodsView.imgm,'|')[0]}",
					link: {
						webUrl: "${baseUrl}/shop/goods/goods_view?goodsno=${frontGoodsVO.goodsno}",
						mobileWebUrl: "${baseUrl}/shop/goods/goods_view?goodsno=${frontGoodsVO.goodsno}"
					}
				}
			});
		}
	}
	
	//상품의 가격 3자리마다 콤마를 찍어준다
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	// 상품값, 상품 총합 , 옵션 가격 3개가 있고
	// 증가 클릭시  상품 총합+상품값에  옵션 전체 선택된 값을 더해  텍스트에 뿌린다	
	$(".product-page-qty-plus").on('click',function() {
		var currentVal = parseInt($(this).prev(".product-page-qty-input").val(), 10);
		if (!currentVal || currentVal == "" || currentVal == "NaN") currentVal = 0;
		$(this).prev(".product-page-qty-input").val(currentVal + 1);

		var value = Number($("#goodsPrice").val());
		var totalValue = Number($("#totalValue").val());
		var optionValue = Number($("#optionValue").val());
		totalValue = totalValue + value;
		$("#totalValue").val(totalValue);

		var result = totalValue + optionValue;
		var resultComma = numberWithCommas(result);

		$("#estimatedPrice").text('( ' + resultComma + '원 )');
		setTotalPrice();

	});

	//상품값, 상품 총합 , 옵션 가격 3개가 있고
	//증가 클릭시  상품 총합-상품값에  옵션 전체 선택된 값을 더해  텍스트에 뿌린다
	$(".product-page-qty-minus").on('click',function() {
		var currentVal = parseInt($(this).next(".product-page-qty-input").val(), 10);
		if (currentVal == "NaN") currentVal = 1;
		if (currentVal > 1) {
			$(this).next(".product-page-qty-input").val(currentVal - 1);

			var value = Number($("#goodsPrice").val());
			var totalValue = Number($("#totalValue").val());
			var optionValue = Number($("#optionValue").val());
			totalValue = totalValue - value;
			$("#totalValue").val(totalValue);

			var result = totalValue + optionValue;
			var resultComma = numberWithCommas(result);

			$("#estimatedPrice").text('( ' + resultComma + '원 )');
			setTotalPrice();
		}

	});

	//상품값, 상품 총합 , 옵션 가격 3개가 있고
	//옵션 선택시 전체 옵션 선택된 값들을 가져와 합해 상품 총합+ 옵션가격 으로 화면에 뿌린다
	function addoptValue(e) {

		var a = $(".product-page-option-select");

		var optionTotal = Number('0');
		for (var i = 0; i < a.size(); i++) {
			var optionValue = null;
			var selectValue = $(".product-page-option-select").eq(i).val();
			if (selectValue) {
				optionValue = selectValue.split('^')[3];
			}
			if (optionValue) {
				optionTotal = Number(optionTotal) + Number(optionValue);
			}
		}
		$("#optionValue").val(optionTotal);
		var result = Number($("#totalValue").val())
				+ Number($("#optionValue").val());
		$("#estimatedPrice").text('( ' + numberWithCommas(result) + '원 )');
		setTotalPrice();
	};
	
	function goodsoptValue() {
		//var val = obj.options[obj.selectedIndex].value;
		var chkValue = $("input[name='option-group']:checked").val();
		var chkOption = $("input[name='option-group']:checked").data("name");
		var goodsno = '${frontGoodsVO.goodsno}';
		ajaxCallJson(
				'/shop/goods/ajaxOpt2List.dojson',
				{
					'goodsno' : goodsno,
					'opt1' : chkOption
				},
				function(data) {
					var secondOption = data.secondOption;
					var option = "";
					if (secondOption.length > 0) {
						for (var i = 0; i < secondOption.length; i++) {
							var disabledClass = secondOption[i].stock != 0 ? "" : "soldout";
							var disabled = secondOption[i].stock != 0 ? "" : "disabled";
							console.log("disabled > " + disabled);
							
							option += "<li>";
							option += "	<label class='option-check "+ disabledClass +"'>";
							option += "		<input type='checkbox' name='opt2No' value='"+ secondOption[i].sno +"' data-price='"+ secondOption[i].price +"' data-name='"+ secondOption[i].opt2 +"' onchange='setAddOption(this); deleteOption2(this);' "+ disabled +"/>";
							option += "		<span>"+ secondOption[i].opt2 +"</span>";
							option += "	</label>";
							option += "</li>";
						}

						$(".dev-"+ chkValue).html(option);
					} else {

					}

				}, function(e) {
					console.log("조회에 실패하였습니다");
				});
	}
</script>

