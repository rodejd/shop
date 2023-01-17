var priceCouponDetail = undefined;
var percentCouponDetail = undefined;
var createDivFlag = false;	// div data 값 세팅 제어
var usedCouponArr = 0;	// 쿠폰적용된 리스트

function calcuCoupon(obj){
	var useCouponBool	= true,
		couponDetail = $(obj).data(),
		couponSno = couponDetail.couponSno,
		goodsno = couponDetail.goodsno;
	
	// 쿠폰한개 적용 시 만들어진 hidden 필드와 div 부분을 remove 한 후 진행
	if(!isUseCouponMultiple()){
		resetCouponSno();
	}
	
	if(!isCouponUsed(couponSno)) {
		// 사용한 적 없는 쿠폰이라면 쿠폰 사용 진행
		calcuAndApplyCoupon(couponDetail);
	} else {
		// 해당 쿠폰이 이미 사용한 쿠폰이라면  alert 를 띄워준다.
		alert('이미 적용된 쿠폰입니다.');
	}
}

function isCouponPriceCheck(couponDetail){
	var goodsno = couponDetail.goodsno;
	var couponType = couponDetail.couponGoodsType;
	var category = couponDetail.couponCategory;
	
	var $useCouponArr = $('._couponInfo');
	var tempGoodsCoupon = "";
	var currentCouponPrice = 0;
	var totalGoodsPrice = 0;
	
	// 지정상품 할인쿠폰일 경우 해당상품의 할인 금액
	if(couponType != 0){
		
		for (var i = 0; i < $useCouponArr.length; i++) {
			// 사용한 쿠폰 정보
			//tempGoodsCoupon = $useCouponArr[i];
			
			var usedCouponGoodsno = $('input[name="usedCouponInfoList[' + i + '].goodsno"]').val();
			var usedCouponCategory = $('input[name="usedCouponInfoList[' + i + '].category"]').val();
			var usedCouponPrice = $('input[name="usedCouponInfoList[' + i + '].dcPrice"]').val();
			var usedCouponType = $('input[name="usedCouponInfoList[' + i + '].coupontype"]').val();
			
			if((usedCouponGoodsno == goodsno || usedCouponCategory == category) && usedCouponType != 0){
				currentCouponPrice += parseInt(usedCouponPrice);
			}
		}
		
		if(category == "" || typeof category == "undefined"){
			// 특정 상품 금액
			totalGoodsPrice = getGoodsPrice(couponDetail);
			// 특정상품 쿠폰을 사용하려는데 해당주문건에 쿠폰에 해당하는 상품이 없을 경우 totalGoodsPrice가 0
			// 사용불가 메세지
			if(totalGoodsPrice == 0){
				alert("선택하신 쿠폰의 상품이 해당 주문건에 존재하지않습니다.");
				return false;
			}
		}else{
			// 카테고리별 금액
			totalGoodsPrice = getCategoryPrice(couponDetail);
			
			// 카테고리 쿠폰을 사용하려는데 해당주문건에 쿠폰에 해당하는 카테고리가 없을 경우 totalGoodsPrice가 0
			// 사용불가 메세지
			if(totalGoodsPrice == 0){
				alert("선택하신 쿠폰의 카테고리가 주문건에 존재하지않습니다.");
				return false;
			}
		}
	
	// 전체 할인쿠폰일 경우 현재 적용된 할인금액
	}else{
		currentCouponPrice = parseInt($('._coupon').val());
		// 전체 상품 금액
		totalGoodsPrice = getTotalGoodsPrice(couponDetail);
	}
	
	var addCouponPrice = parseInt(getMultipleCouponPrice());		// 추가할 쿠폰할인금액
	var totalCouponPrice = currentCouponPrice + addCouponPrice;		// 총 쿠폰합산금액
	
	// 회원할인율 적용금액
	var memberDc = $("#dc").val();
	if(memberDc != 0){
		totalGoodsPrice = totalGoodsPrice - (totalGoodsPrice * (memberDc*0.01));
	}
	
	if(totalGoodsPrice < totalCouponPrice){
		alert("쿠폰사용금이 상품금액보다 클 수 없습니다.");
		return false;
	}
	
	return true;
}


// 사용자가 선택한 쿠폰이 이미 사용된 쿠폰인지 확인한다.
function isCouponUsed(couponSno) {
	// 이미 사용된 쿠폰 목록을 가져와 일치하는 쿠폰이 있는지 검사
	var $useCouponArr = $('._use-coupon-sno');
	var useCouponArrLength = $useCouponArr.length;
	for (var i = 0; i < useCouponArrLength; i++) {
		var useCouponSno = $useCouponArr[i].value;

		// 사용자가 선택한 쿠폰이 이미 사용한 쿠폰이라면 return true
		if (couponSno == useCouponSno) {
			return true;
		}
	}
	// 사용자가 선택한 쿠폰이 사용한 쿠폰이 아니라면 false
	return false;
}

// 쿠폰 사용 진행   couponDetail : 사용자가 선택한 쿠폰의 정보
function calcuAndApplyCoupon(couponDetail) {
	var coupon_emoney = 0,
		sno = couponDetail.couponSno,
		dc = couponDetail.couponDc,
		abi = couponDetail.couponAbi;

	if(abi != 0) {
        // 적립을 위한 쿠폰이라면 coupon_emoney 를 구한다.
        coupon_emoney += parseInt(dc);
	}

	applyCoupon(couponDetail);
	
	chk_emoney(document.frmOrder.emoney.value);

	calcu_settle();
	$('.btn-layer-close').trigger('click');
}

function couponDelete() {
	$("._use-coupon").children().html("");
	$("._goods-price-all").children().html("");
	$("._goods-percent-all").children().html("");
	$("._coupon").val(0);
	$('._use-coupon-sno').remove();
	$('._couponInfo').remove();
	usedCouponArr = 0;
	calcu_settle();
}

function applyCoupon(couponDetail) {
    // 관리자 설정값 중 쿠폰 중복적용이 가능한가?
	if(isUseCouponMultiple()) {
		// 화면에 쿠폰정보 추가 (중복적용 가능)
		applyMultipleCoupon(couponDetail)
	} else {
		// 화면에 쿠폰정보 추가 (중복적용 불가능)
		applyOnlyOneCoupon(couponDetail);
	}

}

function isUseCouponMultiple() {
	return !Boolean(Number($('._coupon-use-multiple').val()));
}

//중복적용 가능(정액쿠폰, 정률쿠폰 각 하나씩 사용 가능)
function applyMultipleCoupon(couponDetail) {
	// 할인쿠폰이 정률이라면
	if(!Boolean(Number(couponDetail.couponDc))) {
		percentCouponDetail = couponDetail;
		
		// 쿠폰금액확인
		if(!isCouponPriceCheck(couponDetail)){
			// 쿠폰적용금액이 상품금액보다 클경우, 특정상품 및 카테고리 쿠폰인데 주문내역에 해당하는 상품이 없을경우 종료
			resetCouponDetail(); // 초기화
			return;
		}
		
		var selectors = {
				applyCouponSnoHiddenSelector : '._coupon-add-div-percent',
				deleteGoodsCouponSelector : '[class^="_goodsno-percent-"]',
				allCouponAreaSelector : '._used-percent-coupon',
				goodsCouponAreaSelector : '._goodsno-percent-'
		}
		
		createDivFlag = true;
		
		applyCouponElements(selectors, couponDetail, 0);
	// 할인쿠폰이 정액이라면
	} else {
        priceCouponDetail = couponDetail;
        
        // 쿠폰금액확인
		if(!isCouponPriceCheck(couponDetail)){
			// 쿠폰적용금액이 상품금액보다 클경우, 특정상품 및 카테고리 쿠폰인데 주문내역에 해당하는 상품이 없을경우 종료
			resetCouponDetail(); // 초기화
			return;
		}
        
		var selectors = {
				applyCouponSnoHiddenSelector : '._coupon-add-div-price',
				deleteGoodsCouponSelector : '[class^="_goodsno-price-"]',
				allCouponAreaSelector : '._used-price-coupon',
				goodsCouponAreaSelector : '._goodsno-price-'
		}
		
		createDivFlag = true;
		
		applyCouponElements(selectors, couponDetail, 0);
	}
	
	var currentCouponPrice = parseInt($('._coupon').val());
	var addCouponPrice = parseInt(getMultipleCouponPrice());
	$('._coupon').val(currentCouponPrice + addCouponPrice);
	
	// 초기화
	resetCouponDetail();
	
}

function reqUsedCouponInfoList(couponDetail, goodsno, price){
	
	// 해당 상품에 대한 할인금액 따로 계산
	var dc = couponDetail.couponDc;
	var couponPrice = 0;
	// 할인 금액이 정률이라면 금액을 계산해여 쿠폰할인금엑을 계산함.
	if(!Boolean(Number(dc))) {
		couponPrice = getPercentCouponPrice(price, dc);
	// 할인 금액이 정액이라면 금액 자체를 반환.
	} else {
		couponPrice = parseInt(dc);
	}
	
	// 사용한 쿠폰에대한 정보를 controller로 넘기기위해 상품번호/쿠폰번호/할인금액/카테고리를 넣어준다
	var couponAttribute = new Array('goodsno', 'couponcd', 'category', 'dcPrice', 'coupon', 'price', 'couponAbi', 'coupontype');
	
	for(var i = 0; i < couponAttribute.length; i++){
		
		var infoValue = "";
		
		switch(couponAttribute[i]){
			case 'goodsno' 		: infoValue = goodsno; 						break;
			case 'couponcd' 	: infoValue = couponDetail.couponSno; 		break;
			case 'category' 	: infoValue = couponDetail.couponCategory; 	break;
			case 'dcPrice'		: infoValue = couponPrice; 					break;	// 상품 쿠폰할인 적용금액
			case 'price' 		: infoValue = couponDetail.couponDc; 		break;	// 쿠폰할인금액
			case 'coupon' 		: infoValue = couponDetail.couponName; 		break;
			case 'couponAbi'	: infoValue = couponDetail.couponAbi; 		break;	// 적립금쿠폰 / 할인쿠폰	
			case 'coupontype'	: infoValue = couponDetail.couponGoodsType; break;	// 쿠폰 전체적용/ 특정상품이나 카테고리 
		}
		
		if(typeof infoValue == 'undefined'){
			infoValue = 0;
		}
		
		var couponInput = document.createElement('input');
		$(couponInput)
		.attr('type', 'hidden')
		.attr('class', '_couponInfo')
		.attr('name', 'usedCouponInfoList[' + usedCouponArr + '].' + couponAttribute[i])
		.attr('value', infoValue);
		// g = goodsno , n = couponsno , p = price , c = category
		$('._coupon-add-div').append(couponInput);
		
	}
	
	usedCouponArr++;
	
}

function resetCouponDetail(){
	priceCouponDetail = undefined;
	percentCouponDetail = undefined;
}

function applyCouponElements(selectors, couponDetail, type) {
	// 컨트롤러에 사용한 쿠폰 sno 를 전달하기 위해 hidden input 태그를 추가해준다.
	// 여러건을 담을 수 있게 끔 html() > append() 로 수정
	$(selectors.applyCouponSnoHiddenSelector).append(createHiddenCouponSno(couponDetail));

	// 쿠폰을 한개만 적용 시 기존에 텍스트를 초기화시켜준다.
	if(type == 1){
		$(selectors.deleteGoodsCouponSelector).html('');
		$(selectors.allCouponAreaSelector).html('');
	}
	// 전체쿠폰이나 상품 및 카테고리 쿠폰 구별없이 사용쿠폰에 표시되도록 if-else 주석처리함.
	// 추후 상품 쿠폰은 상품부분에만 표시하고 싶다면 주석 해제 필요.
	//if (couponDetail.couponGoodsType == 0) {
		$(selectors.allCouponAreaSelector).append(createDivCouponView(couponDetail));
	//} else {
		var couponCategory = couponDetail.couponCategory,
			couponSno = couponDetail.goodsno,
			$goods = $('._goodsno'),
			goodsLength = $goods.length;

		for (var i = 0; i < goodsLength; i++) {
			var goodsnoDatas = $($('._goodsno')[i]).data();

			// 쿠폰이 카테고리 적용 쿠폰인 경우
			if(Boolean(couponCategory)) {
				if(goodsnoDatas.goodsCategory == couponCategory) {
					$(selectors.goodsCouponAreaSelector + couponCategory).append(createDivCouponView(couponDetail));
					break;
				}
			// 쿠폰이 상품 전용 쿠폰인 경우
			} else if (Boolean(couponSno)) {
				if (goodsnoDatas.goodsno == couponSno) {
					$(selectors.goodsCouponAreaSelector + couponSno).append(createDivCouponView(couponDetail));
					break;
				}
			}
		}
	//}
}


function getMultipleCouponPrice() {
    var couponPrice = 0;
    // 정액쿠폰이 있다면 먼저 적용
    if(Boolean(priceCouponDetail)) {
    	// 정액쿠폰이 적립금 쿠폰이 아닐 때
    	if(priceCouponDetail.couponAbi == 0) {
    		couponPrice = getCouponPrice(priceCouponDetail);
    	}
    }

    // 정률쿠폰이 있다면
    if(Boolean(percentCouponDetail)) {
        var totalPrice = 0;
        // 정률쿠폰 타입이 전체상품 대상이라면
        if(percentCouponDetail.couponGoodsType == 0) {
            // 전체 상품가격 합산 - 정액쿠폰을 제외한 가격 기준
            totalPrice = getTotalGoodsPrice(percentCouponDetail) - couponPrice;
        // 정률쿠폰 타입이 상품이나 카테고리 대상이라면
        } else {
            // 정액쿠폰 가격을 뺀 다음 정률쿠폰의 계산이 들어가서 해당 부분 주석처리  japark 20190806
        	/*//정액쿠폰이 사용되었고 그 정액쿠폰의 타입이 상품이나 카테고리라면
            if(isUsedPriceCoupon && isPriceCouponGoodsEqualsPercentCouponGoods()) {
                // 쿠폰이 적용된 상품의 합산가에서 정액쿠폰 가격을 뺀 가격 기준
                totalPrice = getGoodsOrCategoryPrice(percentCouponDetail) - couponPrice;
            //정액쿠폰이 사용되지 않았다면
            } else {*/
        	
            // 정률쿠폰의 카테고리 및 상품 가격 합산가 기준
            totalPrice = getGoodsOrCategoryPrice(percentCouponDetail);
            
            /*}*/
        }

        //정률쿠폰이 적립금 쿠폰이 아닐 때 정해진 기준 가격으로 정률 할인액을 구함.
        if(percentCouponDetail.couponAbi == 0) {
        	
        	couponPrice = getPercentCouponPrice(totalPrice, percentCouponDetail.couponDc);        	
        }
        
    }

    return couponPrice;
}

// 적용된 정액쿠폰과 정률쿠폰이 카테고리 및 상품 적용 쿠폰일 때 같은 카테고리 혹은 같은 상품에 적용되는 대상인지 return
function isPriceCouponGoodsEqualsPercentCouponGoods() {
    return (percentCouponDetail.category == priceCouponDetail.category) || (percentCouponDetail.goodsno == priceCouponDetail.goodsno);
}

// 쿠폰 적용할 금액을 구한다.
function getCouponPrice(couponDetail) {
	var price = getPriceOfCouponApplies(couponDetail),
		dc = couponDetail.couponDc,
        couponPrice = 0;

	// 할인 금액이 정률이라면 금액을 계산해여 쿠폰할인금엑을 계산함.
	if(!Boolean(Number(dc))) {
		couponPrice = getPercentCouponPrice(price, dc);
	// 할인 금액이 정액이라면 금액 자체를 반환.
	} else {
		couponPrice = parseInt(dc);
		
		// 쿠폰이 카테고리 쿠폰이라면 카테고리에 해당하는 수만큼 couponPrice를 곱해준다
		if("" != couponDetail.couponCategory){
			categoryEa = getCategoryEa(couponDetail.couponCategory);
			couponPrice = couponPrice * categoryEa;
		}
	}
	return couponPrice;
}

function getCategoryEa(category){
	var ea = 0,
	$goods = $('._goodsno'),
	goodsLength = $goods.length;

	for (var i = 0; i < goodsLength; i++) {
		var goodsDatas = $($goods[i]).data();
		if (goodsDatas.goodsCategory == category) {
			ea++;
		}
	}
	
	return ea;
}
function getPriceOfCouponApplies(couponDetail) {
	//0 : 전체상품 발급 / 1:특정상품 및 특정카테고리 발급
	var totalPrice = 0;
	if (couponDetail.couponGoodsType == 0) {
		totalPrice = getTotalGoodsPrice(couponDetail);
	} else {
		totalPrice = getGoodsOrCategoryPrice(couponDetail);
	}
	return totalPrice;
}


function getPercentCouponPrice(price, dc) {
    dc = dc.replace('%','');
    return price * dc / 100;
}

function getTotalGoodsPrice(couponDetail) {
	var totalPrice = 0,
		$goods = $('._goodsno'),
		goodsLength = $goods.length;

	for (var i = 0; i < goodsLength; i++) {
		totalPrice += $($goods[i]).data().goodsPrice * $($goods[i]).data().goodsEa;
	}
	
	// 할인적용된 쿠폰들의 정보 값 세팅
	if(createDivFlag){
		reqUsedCouponInfoList(couponDetail, 0, totalPrice);
	}
	createDivFlag = false;		// 생성하는 div에 한번씩만 쿠폰 값 세팅
	
	return totalPrice;
}

function getGoodsOrCategoryPrice(couponDetail) {
	var price = 0,
		couponCategory = couponDetail.couponCategory,
		couponGoodsSno = couponDetail.goodsno;

	if (Boolean(couponCategory)) {
		price = getCategoryPrice(couponDetail);

	} else if (Boolean(couponGoodsSno)) {
		price = getGoodsPrice(couponDetail);
	}

	return price;
}

function getCategoryPrice(couponDetail) {
	var price = 0,
		$goods = $('._goodsno'),
		goodsLength = $goods.length;
	
	
	for (var i = 0; i < goodsLength; i++) {
		var goodsDatas = $($goods[i]).data();
		
		// 상품가격 * 수량
		var goodsEa = 1;
		if(typeof goodsDatas.goodsEa != "undefined"){
			goodsEa = goodsDatas.goodsEa;
		}
		
		if (goodsDatas.goodsCategory == couponDetail.couponCategory) {
			price += goodsDatas.goodsPrice * goodsEa;
			
			// 할인적용된 쿠폰들의 정보 값 세팅
			if(createDivFlag){
				reqUsedCouponInfoList(couponDetail, goodsDatas.goodsno, goodsDatas.goodsPrice * goodsEa);
			}
		}
	}
	createDivFlag = false;		// 생성하는 div에 한번씩만 쿠폰 값 세팅

	return price;
}

function getGoodsPrice(couponDetail) {
	var price = 0,
	$goods = $('._goodsno'),
	goodsLength = $goods.length;

	for (var i = 0; i < goodsLength; i++) {
		var goodsDatas = $($goods[i]).data();
		
		// default2만 orderitem.jsp에 goodsEa를 추가 해서 undefined 체크 20190802 japark
		var goodsEa = 1;
		if(typeof goodsDatas.goodsEa != "undefined"){
			goodsEa = goodsDatas.goodsEa;
		}
		
		if (goodsDatas.goodsno == couponDetail.goodsno) {
			price = goodsDatas.goodsPrice * goodsEa;	// 가격에 수량을 곱해서 전체금액에서 할인되도록 수정 20190802 japark
			// 할인적용된 쿠폰들의 정보 값 세팅
			if(createDivFlag){
				reqUsedCouponInfoList(couponDetail, goodsDatas.goodsno, price);
			}
			break;
		}
	}
	
	createDivFlag = false;

	return price;
}


//중복적용 불가능(단 하나의 쿠폰만 사용 가능)
function applyOnlyOneCoupon(couponDetail) {
	// 할인쿠폰이 정률이라면
	if(!Boolean(Number(couponDetail.couponDc))) {
		percentCouponDetail = couponDetail;
	// 할인쿠폰이 정액이라면
	} else {
        priceCouponDetail = couponDetail;
	}
    // 쿠폰금액확인
    if(!isCouponPriceCheck(couponDetail.goodsno, couponDetail.couponGoodsType, couponDetail.couponCategory)){
      	// 쿠폰적용금액이 상품금액보다 클경우, 특정상품 및 카테고리 쿠폰인데 주문내역에 해당하는 상품이 없을경우 종료
       	resetCouponDetail(); // 초기화
       	return;
    }
    
	var selectors = {
			applyCouponSnoHiddenSelector : '._coupon-add-div',
			deleteGoodsCouponSelector : '[class^="_goodsno-td-"]',
			allCouponAreaSelector : '._use-coupon',
			goodsCouponAreaSelector : '._goodsno-td-'
	};
	
	applyCouponElements(selectors, couponDetail, 1);
	
	// 적립쿠폰이 아닐 때에만 쿠폰 가격 세팅
	if(couponDetail.couponAbi == 0) {		
		$('._coupon').val(getCouponPrice(couponDetail));
	}
	
	// 초기화
	resetCouponDetail();
}

function createHiddenCouponSno(couponDetail) {
	var couponInput = document.createElement("input");
	$(couponInput)
		.attr('type', 'hidden')
		.attr('name', 'couponSno')
		.attr('class', '_use-coupon-sno')
		.attr('value', couponDetail.couponSno);

	return couponInput;
}

function resetCouponSno(){
	$('input[name=couponSno]').remove();
	$('._couponInfo').remove();
}

function createDivCouponView(couponDetail) {
	var useCouponView = document.createElement("div"),
		couponText = getCouponText(couponDetail);
	/*$(useCouponView).css('margin-top', '10px');*/
	$(useCouponView).text(couponText);
	
	return useCouponView;
}

function getCouponText(couponDetail) {
	var couponText =
		(couponDetail.couponGoodsType == 0 ? '[전체쿠폰] ' :
		Boolean(couponDetail.couponCategory) ? '[카테고리쿠폰] ' : '[상품쿠폰] ')
		+ couponDetail.couponName
		+ '\t'
		+ comma(couponDetail.couponDc)
		+ (Boolean(Number(couponDetail.couponDc)) ? '원 ' : '% ')
		+ (couponDetail.couponAbi == 0 ? ' 할인' : ' 적립');
	return couponText;
}
