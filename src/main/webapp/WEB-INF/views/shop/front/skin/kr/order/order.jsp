 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<link rel="stylesheet" type="text/css" href="/resources/shop/front/order/order.css?v=1">
<style>
#postcode-layer-popup {
	position: fixed;
	top: 240px;
	left: 50%;
	width: 700px;
	height: 500px;
	margin-left: -370px;
	border: 1px solid #ddd;
	z-index: 10000000000000000000000000000000000000 !important;
}
#postcode-layer-popup .btn_pop_close_n {
    position: absolute;
    top: -35px;
    right: 0;
    font-size: 25px;
    color: #fff;
    line-height: 25px;
    text-decoration: none;
    padding: 5px 10px;
}
#postcode-layer-popup .pop-content {
	height: 500px;
    padding-top: 0;
    overflow: auto;
}
@media only screen and (max-width : 721px) {
	#postcode-layer-popup {
		top: 100px;
		left: 0;
		width: 100%;
		height: 490px;
		margin: 0 auto;
		border: none;
		z-index: 10000000000000000000000000000000000000 !important;
		background:none !important;
	}
	#postcode-layer-popup .btn_pop_close_n {
		right: 10px;
	}
	#postcode-layer-popup .pop-content {
		height: 490px;
		margin: 0 10px;
	}
}
</style>

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="/resources/shop/front/order/calcuCoupon.js"></script>
<!-- THE SCRIPT TO BE RECOVERED VIA CDN -->
<script src="https://pay.axepta.it/sdk/axepta-pg-sdk.js"></script>

<!-- test -->
<!-- <script src="//pay-sandbox.axepta.it/sdk/axepta-pg-sdk.js"></script>  -->

<script type="text/javascript">
	var msgCnt = 0;
	window.addEventListener('message', function(e) {
		if(e.data == "axepta_SUCCESS_message"){
			$('.dim-layer').hide();
			
			if(msgCnt == 0){
				order_submit();
				msgCnt++;
			}
		}
	});
	
	var isCoupon = false; //쿠폰 적용유무
	var isCouponCode = false; //할인코드 적용 유무
	$(function(){
		/************** 결제관련 스크립트_Start ***********/
		// Smart LICENSE
		var axeptaClient = new AxeptaSDKClient("https://pay.axepta.it", "GYGWSJR-YNB4BJR-HRRD787-A0XFM3F");
		//test
		//var axeptaClient = new AxeptaSDKClient("https://pay-sandbox.axepta.it", "EVRC04C-347M3R9-KVP9FAR-J4G50YR");
		
		//무통장체크시 계좌정보 표시
		$("[name=settlekind]").on("change", function(){
			if($("[name=settlekind]:checked").val() == "a"){
				$(".bankInfo").show();
			}else{
				$(".bankInfo").hide();
			}
		});
		
		//결제
		var paymentID = "";
		$(".btn_my_order").on("click",function(){
			if( !valid() ) return;
			
			//무통장입금
			if($("[name=settlekind]:checked").val() == "a"){
				order_submit();
				return;
			}
			
			//결제금액이 0원일경우 결제모듈없이 진행
			if($("#amount").val() == "0.00"){
				order_submit();
				return;
			}
			
			if(paymentID == ""){
				$.ajax({
					data : $("#frmOrder").serialize(),
					dataType : 'json',
					type : 'POST',
					async: false,
					url : "/shop/pay/initPayment",
					success : function (result) {
						var res = $.parseJSON(result);
						if(res.code == "200"){
							paymentID = res.paymentID;
							axeptaClient.preparePayment(paymentID, "compact");
		 					dimlayerPoup();
						}else{
							var msg = "";
							if(res.code == "-99"){
								msg = "오류가 발생했습니다.";
							}else{
								msg = res.message;
							}
							alert(msg);
						}
					},
					error: function(request, status, error){
						console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
				});
			}else{
				axeptaClient.preparePayment(paymentID, "compact");
				dimlayerPoup();
			}
		});
		
		//결제 창닫음
		$(document).on("click", ".axepta-modal-content > .close",function(){
			$('.dim-layer').hide();
		});
		/************** 결제관련 스크립트_End ***********/
		
		/************ 적립금 적용_Start ************/
		//적립금 적용
		$(".btnEmoney").on("click",function(){
			var poEmoney = setFloat($("#poEmoney").val()); //사용가능 적립금
			var emoney = setFloat($("#emoney").val()); //사용할 적립금
			var amount = setFloat($("#amount").data("price")); //총 결제금액
			var amountVal = setFloat($("#amount").val()); //총 결제금액
			
			if(poEmoney < emoney){
				alert("사용가능한 적립금은 ₩" + setFloat(poEmoney,"Y") + " 입니다.");
				return;
			}
			
			if(emoney > amount){
				alert("상품가격은  ₩" + setFloat(amount,"Y") + " 입니다.");
				return;
			}
			
			if(emoney > (amountVal + emoney)){
				alert("최대 사용가능한 적립금은 ₩" + setFloat(amountVal,"Y") + " 입니다.");
				return;
			}
			
			$("#dcEmoneyAmount").val(emoney);
			var rmEmoney = poEmoney - emoney; //잔여적립금
			$(".tagRmEmoney").html("₩" + setFloat(rmEmoney,"Y")); //잔여 적립금 텍스트
			$(".tagTotEmoney").html("₩" + setFloat(emoney,"Y")); //적립금사용 텍스트
			//총결제금액 설정
			setAmount();
		});
		
		$('#emoney').off('blur').on('blur',function(e){ 
			var value = $(this).val(); 
			var regExp = /^\.|\.$/;
			if(regExp.test(this.value)){
				$(this).val(value.replace('.',''));
			}
		}); 
		$('#emoney').off('input').on('input',function(e){
			var value = $(this).val();
			var regExp = /^\d*.?\d{0,2}$/;
			if(!regExp.test(this.value)){
				$(this).val(value.substring(0,value.length-1));
			}
		});
		$('#emoney').off('keypress').on('keypress',function(e){
			e = e || window.event;
			var charCode = e.which || e.keyCode;
			if (!((charCode >= 48 && charCode <= 57) || charCode === 46)){ 
				return false;
			} 
		});
		/************ 적립금 적용_End ************/
		
		/************ 쿠폰 적용_Start ************/
		//쿠폰 레이어 오픈
		$(".layerCoupon").click(function(){
			if(isCouponCode){
				alert("할인쿠폰,할인코드 중 하나만 적용가능합니다.");
				return;
			}
			
			var $goods = $('[name=goodsnoArr]');
			var goodsNos = [];
		
			for(var i = 0; i < $goods.length; i++) {
				contains(goodsNos, $($goods[i]).val());
			}
			
			$.ajax({
				data : {
					goodsno : goodsNos.toString(),
					price : $('#amount').data("price"),
				},
				dataType : 'html',
				type : 'POST',
				async: false,
				url : "/shop/order/ajaxcouponinquery",
				success : function (rst) {
					$("#coupon-layer-popup").html(rst)
					$('.bx_bg_op').css({"display":"block"}); /* dim-bg-wrap */
					$('.pop-coupon-app').css({"display":"block"});
				},
				error: function(request, status, error){
					console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			});
		});
		
		//쿠폰 적용
		$(document).on("click", "#coupon-layer-popup .btnCouponAdd",function(){
			if(isCouponCode){
				alert("할인쿠폰,할인코드 중 하나만 적용가능합니다.");
				return;
			}
			
			var applysno = $(this).data("applysno"); // 적용일련번호
			var couponcd = $(this).data("couponcd"); // 쿠폰번호
			var couponnm = $(this).data("couponnm"); // 쿠폰명
			var price = $(this).data("price"); // 쿠폰금액
			var maxprice = $(this).data("maxprice"); // 최대 할인액
			var excPrice = $(this).data("excPrice"); // 쿠폰사용제한(이상 구매시에만 사용가능)
			var amount = setFloat($("#amount").data("price")); //총 결제금액
			
			//퍼센트할인
			if(isNaN(price)){
				var per = price.replace("%", "");
				var dcCoupon = setFloat( (amount * per) / 100);
				
				if(maxprice != "" && !isNaN(maxprice)){
					if(dcCoupon > maxprice){
						dcCoupon = setFloat(maxprice);
					}
				}
				price = dcCoupon;
			}
			
			//쿠폰사용 코드값 설정
			$(".textCoupon").html("할인금액 : ₩" + setFloat(price,"Y"));
			$(".textCoupon").show();
			$("#couponnm").val(couponnm);
			$("#applysno").val(applysno);
			$("#couponcd").val(couponcd);
			$("#dcCouponAmount").val( setFloat(price) );
			
			//레이어 닫음
			$('.dim-bg-wrap').css({"display":"none"});
			$('.bx_bg_op').css({"display":"none"});
			$('.pop-coupon-app').css({"display":"none"});
			
			//쿠폰적용
			isCoupon = true;
			
			//총결제금액 설정
			setAmount();
		});
		
		//레이어 닫기
		$(document).on("click", ".layer-pop .btn-layer-close",function(){
			$('.dim-bg-wrap').css({"display":"none"});
			$('.bx_bg_op').css({"display":"none"});
			$(this).parents('.layer-pop').css({"display":"none"});
			return false;
		});
		
		/************ 쿠폰 적용_End ************/
		
		/************ 할인코드 적용_Start ************/
		$(".btnDcCode").on("click",function(){
			if(isCoupon){
				alert("할인쿠폰,할인코드 중 하나만 적용가능합니다.");
				return;
			}
			
			if( $.trim($("#dncode").val()) == "" ){
				alert("할인코드를 입력해주세요.");
				$("#dncode").focus();
				return;
			}
			
			$.ajax({
				data : {"dncode" : $("#dncode").val()},
				dataType : 'json',
				type : 'POST',
				url : "/shop/order/order/dcCode.dojson",
				success : function (rst) {
					if(rst.RESULT == 0 ){
						var dcCodeInfo = rst.dcCodeInfo;
						var amount = setFloat($("#amount").data("price")); //총 결제금액
						var excPrice = setFloat(dcCodeInfo.excPrice); // 쿠폰사용제한 금액 (이상 구매시에만 사용가능)
						var price = dcCodeInfo.price; //쿠폰금액
						var maxprice = dcCodeInfo.maxprice; //최대 할인액
						
						//퍼센트할인
						if(isNaN(price)){
							var per = price.replace("%", "");
							if(per <= 0){
								alert("적용할수 없는 쿠폰입니다.");
								$(".textDcCode").html("");
								$(".textDcCode").hide();
								$("#couponDcCode").val("");
								$("#dcCodeAmount").val("0");
								$("#dncode").val("");
								return;
							}
							
							per = per / 100;
							var dcCoupon = setFloat( amount * per);
							
							if(maxprice != "" && !isNaN(maxprice)){
								if(dcCoupon > maxprice){
									dcCoupon = setFloat(maxprice);
								}
							}
							price = dcCoupon;
						}
						
						//할인코드 적용유무
						isCouponCode = true;
						$(".textDcCode").html("할인금액 : ₩" + setFloat(price,"Y"));
						$(".textDcCode").show();
						$("#couponDcCode").val(dcCodeInfo.couponcd);
						$("#dcCodeAmount").val( setFloat(price) );
					}else{
						$(".textDcCode").html("");
						$(".textDcCode").hide();
						$("#couponDcCode").val("");
						$("#dcCodeAmount").val("0");
						$("#dncode").val("");
						
						if(rst.RESULT == -1 ){
							alert("할인코드를 입력해주세요.");
						}else if(rst.RESULT == -2 ){
							alert("사용할수 없는 할인코드입니다.");
						}else if(rst.RESULT == -3 ){
							alert("이미 사용한 할인코드입니다.");
						}else if(rst.RESULT == -99 ){
							alert("오류가 발생했습니다.");
						}
					}
					
					//총결제금액 설정
					setAmount();
				},
				error: function(request, status, error){
					alert("오류가 발생했습니다.");
					$("#dncode").val("");
				}
			});
		});
		/************ 할인코드 적용_End ************/
		
		//로그인하지않고 쿠폰 및 적립금 사용할경우 alert
		$(".loginAlert").on("click",function(){
			alert("로그인후 이용가능합니다.");
			return;
		})
		
		//이메일 selectbox값 설정
		$(".selectEmail").on("change", function(){
			$(this).parents("dd").find(".ipnutEmail").val( $(this).val() );
		});
		
		//배송지 관리
		$(document).on("click", ".place_manage",function(){
			$(".pop_place_manage, .pop_place_manage .bx_bg_op").css("display", "block");
		});
		$(document).on("click", ".pop_place_manage .btn_pop_close_n",function(){
			$(".pop_place_manage, .pop_place_manage .bx_bg_op").css("display", "none");
		});
		
		$(document).on("click", ".pop_place_manage .btn_choose",function(){
			var nameReceiver1  = $(this).data("nr1");
			var nameReceiver2  = $(this).data("nr2");
			var emailReceiver  = $(this).data("er"); 
			var mobileReceiverType = $(this).data("mt"); 
			var mobileReceiver = $(this).data("mr"); 
			var zipcode        = $(this).data("zip");
			var address        = $(this).data("ad1");
			var address2       = $(this).data("ad2");
			var city           = $(this).data("ct");
			var state          = $(this).data("st");
			var customsNum     = $(this).data("cn");
			
			
			$("#nameReceiver1").val(nameReceiver1);
			$("#nameReceiver2").val(nameReceiver2);
			if(emailReceiver != ""){
				$("#emailReceiver1").val(emailReceiver.split("@")[0]);
				$("#emailReceiver2").val(emailReceiver.split("@")[1]);
			}
			$("#mobileReceiverType").val(mobileReceiverType);
			$("#mobileReceiver").val(mobileReceiver);
			$("#zipcode").val(zipcode);
			$("#address").val(address);
			$("#address2").val(address2);
			$("#customs_num").val(customsNum);
			
			if( $("#country").val() != "korea" ){
				$("#city").val(city);
				$("#state").val(state);
			}
			$(".pop_place_manage").css("display", "none");
		});
		
		
		$("#country").on("change",function(){
			var html ='';
			var isLogin = "${shop_so.isShopLogin()}";
			if( $(this).val() == "South Korea" ){
				html +='<div>';
				html +='	<div class="inp_wp mob_l">';
				html +='		<input type="text" class="inp_ty03" name="zipcode" id="zipcode" title="우편번호" readonly="readonly" value="" placeholder="우편번호" />';
				html +='		<div style="float:left; padding:5px 0 0 1px;">';
				html +='			<a href="javascript:postcode(2);" class="btn_ty01">주소검색</a>';
				if(isLogin == "true"){
					html +='			<a href="#" class="btn_ty02 place_manage">배송지 관리</a>';
				}
				html +='		</div>';
				html +='		<div style="clear:both;"></div>';
				html +='	</div>';
				html +='	<div class="inp_wp mob_l">';
				html +='		<input readonly="readonly" type="text" class="inp_ty04" id="address" name=address title="상세주소" value="" required onchange="javascript:onChangeAddressArea();" />';
				html +='	</div>';
				html +='	<div class="inp_wp mob_l last">';
				html +='		<input type="text" class="inp_ty04" name=address2 id="address2" title="나머지 주소" value="" required />';
				html +='	</div>';
				html +='	<div style="clear:both;"></div>';
				html +='</div>';
			}else{
				html +='<div>';
				html +='	<div class="inp_wp mob_l">';
				html +='		<input type="text" class="inp_ty03" name="zipcode" id="zipcode" title="ZIP CODE" placeholder="ZIP CODE" value="" />';
				html +='		<div style="float:left; padding:5px 0 0 1px;">';
				if(isLogin  == "true"){
					html +='		<a href="#" class="btn_ty02 place_manage">배송지 관리</a>';
				}
				html +='		</div>';
				html +='		<div style="clear:both;"></div>';
				html +='	</div>';
				html +='	<div class="inp_wp mob_l">';
				html +='		<input type="text" class="inp_ty04" id="address" name=address title="ADDRESS1" placeholder="ADDRESS1" value="" required />';
				html +='	</div>';
				html +='	<div class="inp_wp mob_l">';
				html +='		<input type="text" class="inp_ty04" id="address2" name=address2 title="ADDRESS2" placeholder="ADDRESS2" value="" required />';
				html +='	</div>';
				html +='	<div class="inp_wp mob_l">';
				html +='		<input type="text" class="inp_ty04" id="city" name=city title="CITY" placeholder="CITY" value="" required />';
				html +='	</div>';
				html +='	<div class="inp_wp mob_l last">';
				html +='		<input type="text" class="inp_ty04" name=state id="state" title="STATE" placeholder="STATE" value="" required />';
				html +='	</div>';
				html +='	<div style="clear:both;"></div>';
				html +='</div>';
			}
			
			$(".addressTag").html(html);
		});
	});
	
	function setAmount(){
		var amount = setFloat($("#amount").data("price")); //총금액
		var dcEmoneyAmount = setFloat($("#dcEmoneyAmount").val()); //적립금 사용금액
		var dcCouponAmount = setFloat($("#dcCouponAmount").val()); //쿠폰 사용금액
		var dcCodeAmount = setFloat($("#dcCodeAmount").val()); //할인코드 사용금액
		
		var shippingFee = setFloat(${frontOrderVO.delivery_shippingFee});
		$("#delivery").val(shippingFee);
		var shippingCountry = $("#country").val(); //배송국가
		if(shippingCountry == "South Korea" || shippingCountry == "Italy"){
			shippingFee = 0.00;
			$("#delivery").val(shippingFee);
		}
		
		var dcCouponTotal = dcCouponAmount + dcCodeAmount; // 쿠폰 사용금액 + 할인코드 사용금액
		var totPrice = amount + shippingFee - (dcEmoneyAmount + dcCouponTotal);//총금액 + 배송비 - (적립금 + 쿠폰 사용금액 + 할인코드 사용금액)
		
		if(totPrice < 0){
			alert("사용 적립금이 결제금액보다 많이 사용되었습니다.\n사용 적립금을 다시 입력해주세요.");
			$("#emoney").val(0);
			$("#dcEmoneyAmount").val("0");
			$(".tagRmEmoney").html("₩" + setFloat( $("#poEmoney").val() ,"Y")); //잔여 적립금 텍스트
			$(".tagTotEmoney").html("₩" + setFloat(0,"Y")); //적립금사용 텍스트
			setAmount();
			return;
		}
		
		$("#amount").val(setFloat(totPrice,"Y")); //총결제금액
		$("#dcCouponTotal").val(setFloat(dcCouponTotal,"Y")); //쿠폰 사용금액 + 할인코드 사용금액
		
		$(".tagTotPriceKr").html(setExchange(setFloat(totPrice,"Y"),'kr')); //참고용 결제금액(환율적용) 텍스트
		$(".tagTotPrice").html("₩" + setFloat(totPrice,"Y")); //총 예상결제 금액 텍스트
		$(".tagTotCoupon").html("₩" + setFloat(dcCouponTotal,"Y")); //적립금사용 텍스트
		$(".tagAddDeliveryPrice").html("₩" + setFloat(shippingFee,"Y")); //배송비 텍스트
		
	}
	
	function setExchange(price, skin){
		var chgPrice = 0;
		$.ajax({
			data : {"price" : price, "skin" : skin},
			dataType : 'json',
			type : 'POST',
			async: false,
			url : "/shop/order/order/setExchange.dojson",
			success : function (rst) {
				if(rst.RESULT == 0 ){
					chgPrice = rst.chgPrice;
				}
			},
			error: function(request, status, error){
				chgPrice = 0;
			}
		});
		return chgPrice;
	}
	
	// 적립금 소수점 2자리 처리
	function setFloat(value, fixed){
		var returnVal = Math.round(value * 100) / 100;
		if(fixed == "Y"){
			returnVal = (Math.round(value * 100) / 100).toFixed(2); //return String
		}
		return returnVal;
	}
	
	//결제처리
	function order_submit(){
		$.ajax({
			data : $("#frmOrder").serialize(),
			dataType : 'json',
			type : 'POST',
			url : "/shop/order/order/indb",
			success : function (result) {
				//console.log(result);
				if(result > 0){
					var _form = $('<form></form>'); 
					_form.attr("method","post");
					_form.attr("action","/shop/order/order_end");
					_form.append($('<input/>', {type: 'hidden', name: 'ordno', value: $("[name=ordno]").val() }));
					_form.appendTo('body'); 
					_form.submit();
				}else{
					var msg = "오류가 발생했습니다.\n다시 시도해주세요.";
					if(result == -1){
						msg = "주문번호가 없습니다.";
					}else if(result == -2){
						msg = "동일한 주문번호가 존재합니다.";
					}else if(result == -3){
						msg = "주문가능한 상품이 없습니다.";
					}else if(result == -4){
						msg = "정삭적으로 주문 접수가 되지 않았습니다.";
					}else if(result == -5){
						msg = "로그인정보가 잘못되었습니다.";
					}else if(result == -6){
						msg = "사용가능한 적립금이 초과하였습니다.";
					}else if(result == -7){
						msg = "사용할 수 없는 쿠폰입니다.";
						
						$(".textCoupon").html("");
						$(".textCoupon").hide();
						$("#couponnm").val("");
						$("#applysno").val("");
						$("#couponcd").val("");
						$("#dcCouponAmount").val(0);
					}else if(result == -8){
						msg = "사용할 수 없는 할인코드입니다.";
						
						$(".textDcCode").html("");
						$(".textDcCode").hide();
						$("#couponDcCode").val("");
						$("#dcCodeAmount").val("0");
						$("#dncode").val("");
					}
					alert(msg);
					return;
				}
			},
			error: function(request, status, error){
				console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
	}

	//주문자 정보 동일
	function ctrl_field(_chk){
		if (_chk){
			$("#nameReceiver1").val( $("#nameOrder1").val() );
			$("#nameReceiver2").val( $("#nameOrder2").val() );
			$("#emailReceiver1").val( $("#email1").val() );
			$("#emailReceiver2").val( $("#email2").val() );
		}else{
			$("#nameReceiver1").val("");
			$("#nameReceiver2").val("");
			$("#emailReceiver1").val("");
			$("#emailReceiver2").val("");
		}
	}
	
	function postcode(idx) {
		new daum.Postcode({
			oncomplete: function(data) {
				var fullAddr = ''; // 최종 주소 변수
				var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
				if(idx==1){
					 // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('zip').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('add').value = fullAddr;

	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById('add_sub').focus();
				}else{
					 // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('address').value = fullAddr;
	                //addOverDelivery(data.sido);
	                
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById('address2').focus();
				}

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                document.getElementById('postcode-layer-popup').style.display = 'none';
                $('.bx_bg_op').hide();
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(document.getElementById('postcode-pop-content'));

        // iframe을 넣은 element를 보이게 한다.
        document.getElementById('postcode-layer-popup').style.display = 'block';
        $('.bx_bg_op').show();
    }
	
	function closeDaumPostcode() {
        $("#postcode-layer-popup").hide();
        $('.bx_bg_op').hide();
    }

	// 레이어 팝업
	function dimlayerPoup(){
		var $el = $("#layer2");    //레이어의 id를 $el 변수에 저장
		var isDim = $el.prev().hasClass('dimBg'); //dimmed 레이어를 감지하기 위한 boolean 변수

		isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

		var $elWidth = ~~($el.outerWidth()),
			$elHeight = ~~($el.outerHeight()),
			docWidth = $(document).width(),
			docHeight = $(document).height();

		// 화면의 중앙에 레이어를 띄운다.
		if ($elHeight < docHeight || $elWidth < docWidth) {
			$el.css({
				marginTop: -$elHeight /2,
				marginLeft: -$elWidth/2
			})
		} else {
			$el.css({top: 0, left: 0});
		}
		
		$el.find('a.btn-layerClose').click(function(){
			isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
			return false;
		});

		$('.layer .dimBg').click(function(){
			$('.dim-layer').fadeOut();
			return false;
		});
	}
	
	//필수값 체크
	function valid(){
		if($("#nameOrder1").val() == ""){
			alert("주문자 이름을 입력해주세요.");
			$("#nameOrder1").focus();
			return false;
		}
		
		if($("#nameOrder2").val() == ""){
			alert("주문자 성을 입력해주세요.");
			$("#nameOrder2").focus();
			return false;
		}
		
		if($("#email1").val() == "" || $("#email2").val() == ""){
			alert("주문자 이메일을 입력해주세요.");
			$("#email1").focus();
			return false;
		}
		
		var email = $("#email1").val() + "@" + $("#email2").val();
		var regEmail = /^[^"'@]+@[._a-zA-Z0-9-]+\.[a-zA-Z]+$/;
		if (!regEmail.test(email)){
			alert("잘못된 이메일 형식입니다");
			$("#email1").focus();
			return false;
		}
		
		if($("#nameReceiver1").val() == ""){
			alert("수령인 이름을 입력해주세요.");
			$("#nameReceiver1").focus();
			return false;
		}
		
		if($("#nameReceiver2").val() == ""){
			alert("수령인 성을 입력해주세요.");
			$("#nameReceiver2").focus();
			return false;
		}
		
		if($("#emailReceiver1").val() == "" || $("#emailReceiver2").val() == ""){
			alert("주문자 이메일을 입력해주세요.");
			$("#emailReceiver1").focus();
			return false;
		}
		
		var emailReceiver = $("#emailReceiver1").val() + "@" + $("#emailReceiver2").val();
		var regEmail = /^[^"'@]+@[._a-zA-Z0-9-]+\.[a-zA-Z]+$/;
		if (!regEmail.test(emailReceiver)){
			alert("잘못된 이메일 형식입니다");
			return false;
		}
		
		if($("#zipcode").val() == "" || $("#address").val() == ""){
			alert("주소를 입력해주세요.");
			$("#zipcode").focus();
			return false;
		}
		
		if($("#customs_num").val() == ""){ 
			alert("개인통관번호를 입력해주세요.");
			$("#customs_num").focus();
			return false;
		}
		/* if(!$("#customs_num").val().startsWith("P") || $("#customs_num").val().length != 13){
			alert("개인통관고유부호를 확인해주세요.");
			$("#customs_num").focus();
			return false;
		} */
		
		if(!$("#agree").is(":checked")){
			alert("결제동의를 하셔야 결제가 가능합니다.");
			$("#agree").focus();
			return false;
		}
		
		//사용 적립금 설정
		if( $("#emoney").val() == "" ){
			$("#emoney").val(0);
		}
		return true;
	}
</script>

<%-- ================================================================================
* HTML CONTENT 시작
================================================================================ --%>
<div class="top_tit_ty01">
	<div class="tit_dp01">ORDER</div>
</div>

<!-- 서브 컨텐츠 -->
<div id="content-area" class="container x-order checkout_wrap">
	<!-- 컨텐츠 -->
	<div class="content">
		<!-- 주문 상품 확인 및 할인 적용 -->
		<form id="frmOrder" name="frmOrder" action="order/settle" method=post onsubmit="return chkForm2(this)">
			<!-- 주문자 배송 정보 -->
			<div class="">
				<div class="stit01">주문자 정보</div>
				<div class="my_check_list01">
					<c:choose>
						<c:when test="${shop_so.isShopLogin()}">
							<ul>
								<li>
									<dl>
										<dt>이름 / 성</dt>
										<dd>
											${frontOrderVO.name1 }
											<span class="blk_lr">/</span>
											${frontOrderVO.name2 }
											<input type="hidden" id="m_no" name="m_no" value="${frontOrderVO.m_no }"/>
											<input type="hidden" id="nameOrder1" name="nameOrder1" value="${frontOrderVO.name1 }" />
											<input type="hidden" id="nameOrder2" name="nameOrder2" value="${frontOrderVO.name2 }" />
										</dd>
									</dl>
								</li>
								<li>
									<dl>
										<dt>이메일</dt>
										<dd>
											${frontOrderVO.email_1 }@${frontOrderVO.email_2 }
											<input type="hidden" id="email1" name="email1" value="${frontOrderVO.email_1 }" />
											<input type="hidden" id="email2" name="email2" value="${frontOrderVO.email_2 }" />
										</dd>
									</dl>
									<p class="bt_tx01">*주문과 관련된 안내를 받게 되는 정보이니 정확히 입력하여 주시기 바랍니다.</p>
								</li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul>
								<li>
									<dl>
										<dt>이름 / 성 *</dt>
										<dd style="padding: 7px 0;">
											<input type="text" class="inp_ty01 notLoginInput" id="nameOrder1" name="nameOrder1" title="이름" required msgR="주문하시는분의 이름을 적어주세요" maxlength="10" />
											<span style="float:left; width:58px; font-size:16px; color:#272727; line-height:44px; text-align:center;">/</span>
											<input type="text" class="inp_ty01 notLoginInput" id="nameOrder2" name="nameOrder2" title="성" required msgR="주문하시는분의 이름을 적어주세요" maxlength="10" />
											<div style="clear:both;"></div>
										</dd>
									</dl>
								</li>
								<li>
									<dl>
										<dt>이메일 *</dt>
										<dd style="padding: 7px 0;">
											<input type="text" class="inp_ty01 notLoginInput" id="email1" name="email1" title="이메일 아이디" />
											<span class="li_sl">@</span>
											<input type="text" class="inp_ty01 notLoginInput ipnutEmail" id="email2" name="email2" title="이메일 호스팅" />
											<select class="sel_ty01 notLoginSelectBox selectEmail"title="입력방식">
												<option value="" selected="selected">직접입력</option>
												<option value="gmail.com">gmail.com</option>
												<option value="dreamwiz.com">dreamwiz.com</option>
												<option value="freechal.com">freechal.com</option>
												<option value="hanmail.net">hanmail.net</option>
												<option value="hotmail.com">hotmail.com</option>
												<option value="korea.com">korea.com</option>
												<option value="nate.com">nate.com</option>
												<option value="naver.com">naver.com</option>
												<option value="paran.com">paran.com</option>
												<option value="hotmail.com">hotmail.com</option>
											</select>
											<div style="clear:both;"></div>
										</dd>
									</dl>
									<p class="bt_tx01">*주문과 관련된 안내를 받게 되는 정보이니 정확히 입력하여 주시기 바랍니다.</p>
								</li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>

				<fieldset>
					<div class="stit02">
						수령인 정보
						<div class="tx_r"><input type="checkbox" id="chkCopy" class="ck_ty01" title="동일체크" onclick="javascript:ctrl_field(this.checked);" <c:if test="${shop_so.isShopLogin()}">checked="checked" </c:if>/> <label for="chkCopy">주문자 정보 동일</label> </div>
					</div>
					<div class="my_check_list02">
						<ul>
							<li>
								<dl>
									<dt>이름 / 성 *</dt>
									<dd>
										<input type="text" class="inp_ty01" id="nameReceiver1" name="nameReceiver1" value="${frontOrderVO.name1 }" title="이름" required msgR="주문하시는분의 이름을 적어주세요" maxlength="10" />
										<!-- <input type="text" class="inp_ty01" value="samjang" /> -->
										<span class="li_sl">/</span>
										<input type="text" class="inp_ty01" id="nameReceiver2" name="nameReceiver2" value="${frontOrderVO.name2 }" title="이름" required msgR="주문하시는분의 이름을 적어주세요" maxlength="10" />
										<!-- <input type="text" class="inp_ty01" value="Jung" /> -->
										<div style="clear:both;"></div>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>이메일 *</dt>
									<dd>
										<input type="text" class="inp_ty01" id="emailReceiver1" name="emailReceiver1" title="이메일 아이디" value="${frontOrderVO.email_1 }" />
										<span class="li_sl">@</span>
										<input type="text" class="inp_ty01 ipnutEmail" id="emailReceiver2" name="emailReceiver2" title="이메일 호스팅" value="${frontOrderVO.email_2 }" />
										<select class="sel_ty01 selectEmail" title="입력방식">
											<option value="${frontOrderVO.email_2 }" selected="selected">직접입력</option>
											<option value="gmail.com">gmail.com</option>
											<option value="dreamwiz.com">dreamwiz.com</option>
											<option value="freechal.com">freechal.com</option>
											<option value="hanmail.net">hanmail.net</option>
											<option value="hotmail.com">hotmail.com</option>
											<option value="korea.com">korea.com</option>
											<option value="nate.com">nate.com</option>
											<option value="naver.com">naver.com</option>
											<option value="paran.com">paran.com</option>
											<option value="hotmail.com">hotmail.com</option>
										</select>
										<!-- <input type="text" style="width:413px; height:44px; font-size:16px; color:#353535; padding:0 0 0 15px; border:1px solid #dbdbdb;" value="samjang8282@naver.com" /> -->
										<div style="clear:both;"></div>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>전화번호 *</dt>
									<dd>
										<c:set var="mobileReceiverType" value="${frontOrderVO.skin eq 'kr' ? '+82' : '' }" />
										<select  class="sel_ty02" id="mobileReceiverType" name="mobileReceiverType" required>
											${webUtil:makeSelectCodeItem((codeUtil:codeitem('teltype')), mobileReceiverType) }
										</select>
										<input type="text" class="inp_ty03" style="margin-left: 25px;" id="mobileReceiver" name="mobileReceiver" title="휴대폰 번호" value="${frontOrderVO.mobileReceiver}" maxlength="20" required />
										<div style="clear:both;"></div>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>배송지 정보 *</dt>
									<dd>
										<div class="inp_wp">
											<c:set var="countrytype" value="${frontOrderVO.skin eq 'kr' ? 'South Korea' : '' }" />
											<select class="sel_ty03" id="country" name="country" onchange="setAmount();">
												${webUtil:makeSelectCodeItem((codeUtil:codeitem('countrytype')), countrytype) }
											</select>
										</div>
									</dd>
									<dd style="padding-top: 0;" class="addressTag">
										<div>
											<div class="inp_wp mob_l">
												<input type="text" class="inp_ty03" name="zipcode" id="zipcode" title="우편번호" readonly="readonly" value="${frontOrderVO.zipcode }" placeholder="우편번호" />
												<div style="float:left; padding:5px 0 0 1px;">
													<a href="javascript:postcode(2);" class="btn_ty01">주소검색</a>
													<c:if test="${shop_so.isShopLogin()}">
														<a href="#" class="btn_ty02 place_manage">배송지 관리</a>
													</c:if>
												</div>
												<div style="clear:both;"></div>
											</div>
											<div class="inp_wp mob_l">
												<input readonly="readonly" type="text" class="inp_ty04" id="address" name=address title="상세주소" value="${frontOrderVO.address }" required onchange="javascript:onChangeAddressArea();" />
											</div>
											<div class="inp_wp mob_l last">
												<input type="text" class="inp_ty04" name=address2 id="address2" title="나머지 주소" value="${frontOrderVO.address2 }" required />
											</div>
											<div style="clear:both;"></div>
										</div>
									</dd>
								</dl>
							</li>
							<li>
								<dl class="last">
									<dt>개인통관고유번호 *</dt>
									<dd>
										<input type="text" class="inp_ty05" id="customs_num" name="customs_num" title="나머지 주소" value="${frontOrderVO.customsnum }" maxlength="13" />
									</dd>
								</dl>
								<p class="btm_tx">*세관 통관을 위해서는 수령인의 이름과 개인통관고유번호가 반드시 일치해야 합니다.</p>
							</li>
						</ul>
					</div>

					<div class="stit03">결제상품정보</div>
					<div class="check_inc_wrap">
						<jsp:include page="../proc/orderitem.jsp"/>
					</div>
					

					<div class="my_check_list03">
						<div class="bx_l">
							<div class="stit01">적립금 및 쿠폰 적용</div>
							<div class="my_check_list04">
								<ul>
									<li>
										<dl class="ty01">
											<dt>할인쿠폰</dt>
											<dd class="s_t01">
												<input type="text" class="inp_ty01" id="couponnm" name="couponnm" disabled />
												<input type="hidden" class="inp_ty01" id="applysno" name="applysno" />
												<input type="hidden" class="inp_ty01" id="couponcd" name="couponcd" />
												
												<div class="inp_wp">
													<a href="javascript:;" class="btn_ty01 layerCoupon">쿠폰사용</a>
												</div>
												<span class="in_tx01">(보유 ${ shop_so.isShopLogin() ? fn:length(frontOrderVO.listCou) : '0'}장)</span>
												<div style="clear:both;"></div>
												<div style="display: none;" class="textCoupon"></div>
											</dd>
										</dl>
									</li>
									<li>
										<dl class="ty01">
											<dt>할인코드</dt>
											<dd class="s_t01">
												<input type="text" class="inp_ty01" id="dncode" name="dncode" ${!shop_so.isShopLogin() ? 'disabled' : ''}/>
												<input type="hidden" class="inp_ty01" id="couponDcCode" name="couponDcCode" />
												<div class="inp_wp">
													<a href="javascript:;" class="btn_ty01 ${!shop_so.isShopLogin() ? 'loginAlert' : 'btnDcCode'}">코드입력</a>
												</div>
												<div style="clear:both;"></div>
												<div style="display: none;" class="textDcCode"></div>
											</dd>
										</dl>
									</li>
									<li>
										<dl class="ty02">
											<dt>사용 가능한 적립금</dt>
											<dd class="s_t02">
												₩${empty frontOrderVO.emoney ? '0' : frontOrderVO.emoney }
												<input type="hidden" id="poEmoney" value="${frontOrderVO.emoney }" />
											</dd>
										</dl>
									</li>
									<li>
										<dl class="ty02">
											<dt>
												사용 적립금
											</dt>
											<dd class="s_t01">
												<input type="text" id="emoney" name="emoney" size="10" value="0" class="inp_ty02" maxlength="10" data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)" ${!shop_so.isShopLogin() ? 'disabled' : ''}>
											</dd>
										</dl>
									</li>
									<li>
										<dl class="ty02">
											<dt>잔여 적립금</dt>
											<dd class="s_t02 tagRmEmoney">
												₩${empty frontOrderVO.emoney ? '0' : frontOrderVO.emoney}
											</dd>
										</dl>
									</li>
								</ul>
							</div>
							<div class="m_c_btm_btn_c" style="position:relative;">
								<div class="tx_alert_n01">
									지급예상 적립금은 기본 유로화로 지급되며<br/>
									원화는 환율이 적용된 “참고용” 입니다.
								</div>
								<a href="javascript:;" class="btn_app ${!shop_so.isShopLogin() ? 'loginAlert' : 'btnEmoney'}">적용하기</a>
							</div>
							<div class="my_check_list05">
								<dl>
									<dt>결제수단</dt>
									<dd>
										<input type="radio" class="ck_ty01" id="settlekind1" name="settlekind" value="c" disabled/><label for="settlekind1">신용카드 결제</label>
										<input type="radio" class="ck_ty01 last" id="settlekind2" name="settlekind" value="a" checked="checked" /><label for="settlekind2">무통장 입금 </label>
									</dd>
								</dl>
							</div>
							<div class="my_check_list06">
								<p class="tx01 bankInfo" style="display: none;">입금계좌 : IK COMMERCE (아이케이커머스)  /  계좌번호 : 929037-01-012299</p>
								<p class="tx02">
									사이트상 표기 된 원화 가격은 고객님의 쇼핑 편의를 돕기 위해 변동 된 환율이 반영됩니다.<br/>
									실 결제 시에는 유로화로 결제 되는 만큼 표기 원화 금액 과 결제 유로 금액이 따로 보이는 점 유의해 주시기 바랍니다.
								</p>
								<p class="tx03"><input type="checkbox" class="ck_ty01" id="agree"/><label for="agree">동의하기</label></p>
							</div>
						</div>
						
						<%--상품금액, 할인금액 계산 --%>
						<c:set var="consumerSum" value="0"/><%-- 리테일가 --%>
						<c:set var="dcPriceSum" value="0"/><%--리테일가-즉시할인가 --%>
						<c:if test="${not empty frontOrderVO.cartList }">
							<c:forEach var="cartList" items="${frontOrderVO.cartList }" varStatus="status" >
								<c:set var="consumerSum" value="${consumerSum + cartList.consumer}"/>
								<c:set var="dcPriceSum" value="${dcPriceSum + cartList.dcPrice}"/>
							</c:forEach>
						</c:if>
						
						<div class="bx_r">
							<div class="my_result_bx01" style="height:auto;">
								<dl>
									<dt>상품 금액</dt>
									<dd>${shopLibFunction:getExchange(consumerSum, 'kr')}</dd>
								</dl>
								<dl>
									<dt>상품할인액</dt>
									<dd>${shopLibFunction:getExchange(dcPriceSum, 'kr')}</dd>
								</dl>
								<dl>
									<dt>쿠폰사용</dt>
									<dd class="tagTotCoupon">${shopLibFunction:getExchange(0, 'kr')}</dd>
								</dl>
								<dl>
									<dt>적립금사용</dt>
									<dd class="tagTotEmoney">₩0.00</dd>
								</dl>
								<dl class="last">
									<dt>배송비</dt>
									<dd class="tagAddDeliveryPrice">₩0.00</dd>
								</dl>
								<div style="clear:both;"></div>
							</div>
							<div class="my_result_bx02">
								<dl class="line01">
									<dt>총 예상결제 금액</dt>
									<dd class="tagTotPrice">
										${shopLibFunction:getExchange(frontOrderVO.priceSum2, 'kr')}
									</dd>
								</dl>
								<dl class="line02 ty02">
									<dt>참고용 결제금액(환율적용)</dt>
									<dd class="tagTotPriceKr">${shopLibFunction:getExchange(frontOrderVO.priceSum2, 'kr')}</dd>
								</dl>
								<dl class="line02">
									<dt>지급예상 적립금  </dt>
									<dd>${shopLibFunction:getExchange(frontOrderVO.reserveSum, 'kr')}</dd>
								</dl>
							</div>
							<div class="my_result_btns">
								<a href="javascript:;" class="btn_my_order">결제하기</a>
							</div>
						</div>
						<div style="clear:both;"></div>
					</div>
				</fieldset>
			</div>
			 
			<input type=hidden name=ordno value="${frontOrderVO.ordno }">
			<input type=hidden name=item_apply_coupon>
			<input type="hidden" name="ck[]">
			<input class="_coupon-use-multiple" type="hidden" value="${adminSettings.couponUseMultiple}"/>
			<input type="hidden" id="dc" name="dc" value="${frontOrderVO.dc}">
			<input type="hidden" id="dcprice" name="dcprice" value="${frontOrderVO.dcprice}">
			<input type="hidden" name="priceSum" value="${priceSum }">
			
			<!--##TODO 확인 필요한 부분 20150907!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
			<input type="hidden" name="reserveSum" value="${reserveSum }">
			<input type="hidden" id="deliprice" name="deliprice" value="${frontOrderVO.totalDeliveryInfoVO.prepaidDelivery}"/>
			<input type="hidden" id="delimsg" name="delimsg" value=""/>
			<input type="hidden" id="delivery" name="delivery" value="0"><%-- 추가배송비 --%>
			<input type="hidden" id="addDelivery" name="addDelivery" value="0"><%-- 추가배송비 --%>
			<input type="hidden" name="prn_settleprice" value="${frontOrderVO.priceSum2}"><%-- 구매금액 --%>
			<input type="hidden" name="goodsprice" value="${frontOrderVO.priceSum2}"><%-- 상품금액 --%>
			
			<input type="hidden" id="amount" name="amount" data-price="${frontOrderVO.priceSum2}" value="${frontOrderVO.priceSum2}"><%-- 총금액 --%>
			<input type="hidden" id="dcEmoneyAmount" name="dcEmoneyAmount" value="0" /><%-- 적립금 사용금액 --%>
			<input type="hidden" id="dcCouponAmount" name="dcCouponAmount" value="0" /><%-- 쿠폰 사용금액 --%>
			<input type="hidden" id="dcCodeAmount" name="dcCodeAmount" value="0" /><%-- 할인코드 사용금액 --%>
			<input type="hidden" id="dcCouponTotal" name="dcCouponTotal" value="0" /><%-- 쿠폰+할인코드 사용금액 --%>
			
		</form>
	</div>
</div>
<!--// 서브 컨텐츠 -->
<div id=dynamic></div>

<!-- 결제 레이어 팝업 -->
<div class="dim-layer">
	<div class="dimBg"></div>
	<div id="layer2" class="pop-layer">
		<div class="pop-container">
			<div class="pop-conts">
				<!--content -->
				<p class="ctxt mb20">
					<!-- THE TAG WHERE THE HOSTED FORM WILL BE DISPLAYED -->
					<div id="my-axepta-sdk-pg"></div>
				</p>
				<div class="btn-r">
					<a href="#" class="btn-layerClose">Close</a>
				</div>
				<!--// content-->
			</div>
		</div>
	</div>
</div>

<div class="pop_wrap pop_place_manage">
	<div class="bx_bg_op"></div>
	<div class="bx_in_pop">
		<div class="in_bx">
			<div class="tit_bx">
				배송지 관리
				<a href="javascript:void(0);" class="btn_pop_close_n">X</a>
			</div>
			<div class="cont_bx">
				<ul class="mn_list">
					<c:forEach var="delivery" items="${deliveryList}" varStatus="status">
						<li>
							<p class="tx01">${delivery.nameReceiver} <!-- <span class="basic">(기본)</span> --></p>
							<p class="tx02">${delivery.zipcode}</p>
							<p class="tx02">${delivery.address} ${delivery.address2}</p>
							<p class="tx02">${delivery.mobileReceiver}</p>
							<a href="#" class="btn_choose" 
								data-nr1="${delivery.nameReceiver1}"
								data-nr2="${delivery.nameReceiver2}"
								data-er="${delivery.emailReceiver}"
								data-mt="${delivery.mobileReceiverType}"
								data-mr="${delivery.mobileReceiver}"
								data-zip="${delivery.zipcode}"
								data-ad1="${delivery.address}"
								data-ad2="${delivery.address2}"
								data-ct="${delivery.city}"
								data-st="${delivery.state}"
								data-cn="${delivery.customsNum}"
							>선택</a>
						</li>
					</c:forEach>
				</ul>
				<div style="clear:both;"></div>
			</div>
		</div>
	</div>
</div>

<div class="bx_bg_op" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.7;  z-index:10000000000; display:none;"></div>
<div id="coupon-layer-popup" class="layer-pop pop-coupon-app">
</div>

<div id="postcode-layer-popup" class="layer-pop">
	<a href="javascript:closeDaumPostcode()" class="btn_pop_close_n">X</a>
	<div id="postcode-pop-content" class="pop-content"></div>
</div>