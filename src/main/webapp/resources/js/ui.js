/* gnb */
$(function(){
    $(window).scroll(function(){ 
       if($(window).scrollTop()>10) { 
			$("#fixNav").addClass("fix"); 
        }else {
			$("#fixNav").removeClass("fix"); 
		}
    }); 
 }); 

/* 셀렉트박스 */
$(document).ready( function() {
	$("SELECT").select().focus( function(e) {		
		$("#console").prepend('Focus on ' + $(this).attr('name') + '<br />');		
	}).blur( function(e) {		
		$("#console").prepend('Blur on ' + $(this).attr('name') + '<br />');		
	}).change( function(e) {		
		$("#console").prepend('Change on ' + $(this).attr('name') + ': ' + $(this).val() + '<br />');		
	});
});

/* 탭버튼 */
$(document).ready(function(){

	$(".sub-tab li a").click(function(){
		$(".sub-tab li").removeClass("active");
		$(this).parent().addClass("active");
	});

	$(".sub-tab-type02 li a").click(function(){
		$(".sub-tab-type02 li").removeClass("active");
		$(this).parent().addClass("active");
	});

	$(".tab-sort li a").click(function(){
		$(".tab-sort li").removeClass("active");
		$(this).parent().addClass("active");
		return false;
	});

	$(".tab-sort-btn li a").click(function(){
		$(".tab-sort-btn li").removeClass("active");
		$(this).parent().addClass("active");
		return false;
	});

});

/* 상품 리스트 댓글 더보기 */
$(document).ready(function(){

	$(".review-sample-more a").click(function(){
		if($(this).parents('.review-pre-box').find('.review-pre-list').css("display")=="none"){
			$(this).parents('.review-pre-box').find('.review-pre-list').css({"display":"block"});
			return false;
		}else{
			$(this).parents('.review-pre-box').find('.review-pre-list').css({"display":"none"});
			return false;
		}
	});

	$(".review-pre-close").click(function(){
		$(this).parents('.review-pre-list').css({"display":"none"});
		return false;
	});

});

/* 관심등록 */
$(document).ready(function(){

	$(".list-box .img-area .inter").click(function(){
		if($(this).parent().find('.inter-quest').css("display")=="none"){
			$(this).parent().find('.inter-quest').css({"display":"block"});
			return false;
		}else{
			$(this).parent().find('.inter-quest').css({"display":"none"});
			return false;
		}
	});

	$(".btn-inter-ok").click(function(){
		$(this).parents('.inter-txt').css({"display":"none"});
		$(this).parents('.img-area').find('.inter-ok').css({"display":"block"});
		return false;
	});

	$(".btn-inter-cancel").click(function(){
		$(this).parents('.inter-txt').css({"display":"none"});
		return false;
	});

});

/* 상품평 - jquery-1.8.1.min.js 구 라이브러리 전용 */
NJ(document).ready(function(){
	NJ('.reivew-list > dd').hide();
	NJ('.reivew-list > dt').toggle(
		function(){
		NJ(this).next('dd').css({"display":"block"});
	},
	function(){
		NJ(this).next('dd').css({"display":"none"});
	});
});

/* 레이어 팝업 */
$(document).ready(function(){

	$(".layer-pop .btn-layer-close").click(function(){
		$('.dim-bg-wrap').css({"display":"none"});
		$(this).parents('.layer-pop').css({"display":"none"});
		return false;
	});

	// 레시피 구독신청 팝업
	$(".open-recipe-pop").click(function(){
		if($('.pop-recipe-app').css("display")=="none"){
			$('.dim-bg-wrap').css({"display":"block"});
			$('.pop-recipe-app').css({"display":"block"});
			return false;
		}else{
			$('.dim-bg-wrap').css({"display":"none"});
			$('.pop-recipe-app').css({"display":"none"});
			return false;
		}
	});

	// 쿠폰리스트 팝업
	$(".open-coupon-pop").click(function(){
		if($('.pop-coupon-app').css("display")=="none"){
			$('.dim-bg-wrap').css({"display":"block"});
			$('.pop-coupon-app').css({"display":"block"});
			return false;
		}else{
			$('.dim-bg-wrap').css({"display":"none"});
			$('.pop-coupon-app').css({"display":"none"});
			return false;
		}
	});

	//이벤트 당첨자 팝업
	$(".btn-pop-winner").click(function(){
		if($('.pop-event-winner').css("display")=="none"){
			$('.dim-bg-wrap').css({"display":"block"});
			$('.pop-event-winner').css({"display":"block"});
			$('.pop-content').html( $("#pop_" +$(this).attr("id") ).html());
			return false;
		}else{
			$('.dim-bg-wrap').css({"display":"none"});
			$('.pop-event-winner').css({"display":"none"});
			return false;
		}
	});
	
	

	//로그인 팝업
	$(".btn-pop-login").click(function(){
		if($('.pop-login').css("display")=="none"){
			$('.dim-bg-wrap').css({"display":"block"});
			$('.pop-login').css({"display":"block"});
			$('.pop-find').css({"display":"none"});
			return false;
		}else{
			$('.dim-bg-wrap').css({"display":"none"});
			$('.pop-login').css({"display":"none"});
			$('.pop-find').css({"display":"block"});
			return false;
		}
	});

	//아이디비번찾기 팝업
	$(".btn-pop-find").click(function(){
		if($('.pop-find').css("display")=="none"){
			$('.dim-bg-wrap').css({"display":"block"});
			$('.pop-find').css({"display":"block"});
			$('.pop-login').css({"display":"none"});
			return false;
		}else{
			$('.dim-bg-wrap').css({"display":"none"});
			$('.pop-find').css({"display":"none"});
			$('.pop-login').css({"display":"block"});
			return false;
		}
	});


});

/* 마켓 상품 컨트롤러 */
$(document).ready(function(){

	// 레시피 구독신청 팝업
	$(".controller a").click(function(){
		$(".controller a").removeClass("active");
		$(this).addClass("active");
		$('#goods-img').attr('src',$(this).find('img').attr("src"));
		return false;
	});

});

/* 상품 상세 탭 */
$(document).ready(function(){

	$(".btn-detail01").click(function(){
		$(".goods-detail01").css({"display":"block"});
		$(".goods-detail02").css({"display":"none"});
		$(".goods-detail03").css({"display":"none"});
		$(".goods-detail04").css({"display":"none"});
		return false;
	});

	$(".btn-detail02").click(function(){
		$(".goods-detail01").css({"display":"none"});
		$(".goods-detail02").css({"display":"block"});
		$(".goods-detail03").css({"display":"none"});
		$(".goods-detail04").css({"display":"none"});
		return false;
	});

	$(".btn-detail03").click(function(){
		$(".goods-detail01").css({"display":"none"});
		$(".goods-detail02").css({"display":"none"});
		$(".goods-detail03").css({"display":"block"});
		$(".goods-detail04").css({"display":"none"});
		return false;
	});

	$(".btn-detail04").click(function(){
		$(".goods-detail01").css({"display":"none"});
		$(".goods-detail02").css({"display":"none"});
		$(".goods-detail03").css({"display":"none"});
		$(".goods-detail04").css({"display":"block"});
		return false;
	});

});

/* 마이페이지 - 주문결제 내역 검색 */
$(document).ready(function(){

	$(".sub-sch-area .input-open").click(function(){
			$(".user-choice-sch").css({"display":"block"});
		});

});

/* FAQ */
NJ(document).ready(function(){
	NJ('.faq-list dd').hide();
	NJ('.faq-list dt').toggle(
		function(){
		NJ(this).next('.faq-list dd').fadeIn();
		NJ(this).addClass('on');
	},
	function(){
		NJ(this).next('.faq-list dd').fadeOut();
		NJ(this).removeClass('on');
	});
});
