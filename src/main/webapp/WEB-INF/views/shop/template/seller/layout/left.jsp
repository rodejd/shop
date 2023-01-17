<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<script id="template" type="text/x-handlebars-template">
	{{#dataSet}}
		{{#isLevel @index "0"}}

			<tr>
				<td background="/resources/shop/admin/img/left_navi_bg.gif" height="25" class="lmenu" data-idx="idx_{{@index}}">{{menuName}}</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" data-tb="{{@index}}">

		{{else}}
			{{#isLevel menuLevel "2"}}
							</table>
						</td> 
					</tr>

			<tr>
				<td background="/resources/shop/admin/img/left_navi_bg.gif" height="25" class="lmenu" id="idx_{{@index}}">{{menuName}}</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" data-tb="{{@index}}">
	
			{{else}}
				<tr>
					<td class="smenu">
						<a href="{{link}}" name="navi" id="idx_{{@index}}" data-menukey={{menuKey}}>
							{{menuName}}
						</a>	
					</td>
				</tr>
				
		
			{{/isLevel}}
	
		{{/isLevel}}
		{{#if @last}}
					</table>
				</td>
			</tr>
		{{/if}}
	{{/dataSet}}
</script>

<script type="text/javascript">
	$(function(){
		
		var _LEFT_MENU_LIST_ = {
				menuKey1 : [
								{menuKey : 120, menuName : "[기본관리]",		menuLevel : 2, link : "" },
								{menuKey : 120301, menuName : "배송정책설정", 		link : ctx+"/shop/seller/basic/delivery"},
								{menuKey : 121, menuName : "[정산관리]",		menuLevel : 2, link : "" },
								{menuKey : 120302, menuName : "정산세부내역", 		link : ctx+"/shop/seller/basic/acountList"},
								
					],
				menuKey2 : [
								{menuKey : 220, menuName : "[상품관리]",		menuLevel : 2, link : "" },
								{menuKey : 220300, menuName : "상품리스트", 		link : ctx+"/shop/seller/goods/list"},
								{menuKey : 220301, menuName : "상품등록", 		link : ctx+"/shop/seller/goods/register"},
								{menuKey : 220302, menuName : "등록신청대기목록", link : ctx+"/shop/seller/goods/registerApprovalList"},
								{menuKey : 220303, menuName : "수정신청대기목록", link : ctx+"/shop/seller/goods/modifyApprovalList"},
								{menuKey : 220304, menuName : "삭제신청대기목록", link : ctx+"/shop/seller/goods/deleteApprovalList"},
								{menuKey : 220305, menuName : "브랜드관리", 		link : ctx+"/shop/seller/goods/brandList"},
								{menuKey : 221, menuName : "[상품일괄관리]",		menuLevel : 2, link : "" },
								{menuKey : 220308, menuName : "가격/재고수정", 		link : ctx+"/shop/seller/goods/stock"},
								{menuKey : 220309, menuName : "빠른가격수정 ", 		link : ctx+"/shop/seller/goods/price"},
								{menuKey : 220310, menuName : "빠른이동/복사/삭제", link : ctx+"/shop/seller/goods/link"},
					],
				menuKey3 : [
							{menuKey : 320, menuName : "[주문관리]",		menuLevel : 2, link : "" },
							{menuKey : 320300, menuName : "주문리스트", 		link : ctx+"/shop/seller/order/list?mode=group&period=0&first=1"},
							{menuKey : 320301, menuName : "주문취소리스트", 		link : ctx+"/shop/seller/order/orderCs"}, 
							{menuKey : 320302, menuName : "반품/교환접수리스트", link : ctx+"/shop/seller/order/regoods/list"},
							{menuKey : 320303, menuName : "환불접수리스트", link : ctx+"/shop/seller/order/orderRepay"}
					],
				menuKey4 : [
							{menuKey : 420, menuName : "[회원관리]",		menuLevel : 2, link : "" },
							{menuKey : 420300, menuName : "구매회원리스트", 		link : ctx+"/shop/seller/member/orderMemberList"},
							{menuKey : 421, menuName : "[회원일괄관리]",		menuLevel : 2, link : "" },
							{menuKey : 421300, menuName : "SMS발송리스트", 		link : ctx+"/shop/seller/member/smsList"},
							{menuKey : 421301, menuName : "SMS일괄발송", 		link : ctx+"/shop/seller/member/sendSms"},
							{menuKey : 421302, menuName : "이메일발송리스트", 		link : ctx+"/shop/seller/member/emailList"},
							{menuKey : 421303, menuName : "이메일발송", 		link : ctx+"/shop/seller/member/sendEmail"},
					],
				menuKey5 : [
							{menuKey : 520, menuName : "[문의/후기관리]",		menuLevel : 2, link : "" },
							{menuKey : 520300, menuName : "상품문의관리", 		link : ctx+"/shop/seller/board/goodsQna"},
							{menuKey : 520301, menuName : "상품평관리", 		link : ctx+"/shop/seller/board/goodsReview"},
							{menuKey : 520302, menuName : "1:1문의관리", 		link : ctx+"/shop/seller/board/memberQna"},
					],
				menuKey6 : [
							{menuKey : 620, menuName : "[쿠폰관리]",		menuLevel :2, link : "" },
							//{menuKey : 620300, menuName : "이벤트리스트", 		link : ctx+"/shop/seller/event/eventList"},
							//{menuKey : 620301, menuName : "이벤트만들기", 		link : ctx+"/shop/seller/event/eventRegister"},
							{menuKey : 620302, menuName : "쿠폰리스트", 		link : ctx+"/shop/seller/event/couponList"},
							{menuKey : 620303, menuName : "쿠폰만들기", 		link : ctx+"/shop/seller/event/couponRegister"},
					],
				menuKey7 : [
							{menuKey : 720, menuName : "[정산관리]",		menuLevel : 3 },
							{menuKey : 720300, menuName : "매출관리", 		link : ctx+"/shop/seller/"},
					],
				menuKey8 : [
							{menuKey : 820, menuName : "[운영관리]",		menuLevel : 2, link : "" },
							{menuKey : 820300, menuName : "전체 공지사항", 		link : ctx+"/shop/seller/oper/noticeList"},
							{menuKey : 820301, menuName : "관리자질문내용", 		link : ctx+"/shop/seller/oper/sellerquest"},
							{menuKey : 820302, menuName : "관리자에게 메일 문의", 		link : ctx+"/shop/seller/oper/sellerEmailQuest"},
							{menuKey : 820303, menuName : "업체정보관리", 		link : ctx+"/shop/seller/oper/sellerManagement"},
					],
			};
		
		<%-- 
		// #####
		// # left 메뉴 설정
		// #####
		--%>
		var i = 0;
		var name = ["basic", "goods", "order","member","board","event","basic", "basic"];
		var menuKey = returnKey(location.href);
		var menuList =  _LEFT_MENU_LIST_["menuKey" + menuKey];
		var template = Handlebars.compile($("#template").html());
		var uri = "";
		
		Handlebars.registerHelper('isLevel', function(menuLevel, idx, opts) {
			if(menuLevel == idx){
				return opts.fn(this);
			}else{
				return opts.inverse(this);
			}
		});
		
		
		$("#sLeftMenu").html(template({dataSet : menuList }));
		$("#left_title_img").attr("src","/resources/shop/admin/img/left_"+name[menuKey-1]+"_title.gif");
		$("#navi1").html(menuList[0].menuName);
		
		if(null != menuList && 0 < menuList.length){
			for(i=0; i < menuList.length ; i++){
				
 				uri =  (-1 < menuList[i].link.indexOf('?')) 
 						? menuList[i].link.substring(0, menuList[i].link.indexOf('?')) : menuList[i].link;
 				
				if( "" != uri && location.pathname == uri ){
					menuKey = menuList[i].menuKey
					$("#navi2").html(menuList[i].menuName);
					break; 
				}
			}
		}
		
		$('[name=navi]').each(function() {
			if($(this).data("menukey") === menuKey){
				$(this).css('color', 'blue');
				$(this).css('font-weight', 'bold');
				return false;
			}
		});	 
	});
	
	<%-- // Left 메뉴 설정 함수--%>
	function returnKey(url){
		if(url.indexOf("/basic/") > 0){
			return 1;
		}else if(url.indexOf("/goods/") > 0){
			return 2;
		}else if(url.indexOf("/order/") > 0){
			return 3;
		}else if(url.indexOf("/member/") > 0){
			return 4;
		}else if(url.indexOf("/board/") > 0){
			return 5;
		}else if(url.indexOf("/event/") > 0){
			return 6;
		}else if(url.indexOf("/stat/") > 0){
			return 7;
		}else if(url.indexOf("/oper/") > 0){
			return 8;
		}else{
			return 9;
		}
	}
</script>

<table height="100%" cellpadding="0" cellspacing="0" border="0">
	<tbody>
		<tr>
			<td><img src="/resources/shop/admin/img/left_goods_title.gif" class="hand" id="left_title_img"></td>
		</tr>
		<!-------------------- 측면 관리타이틀이미지 시작 ------------------------------->
		<tr>
			<td height="100%" valign="top">
				<div>
					<table width="100%" cellpadding="0" cellspacing="0" border="0" id="sLeftMenu">
					
					
					
					</table>
					<div style="padding-top:14px"></div>
				</div>
				<div id="s2panelside" style="padding-left:7px"><script> /* panel('s2panelside', 'member'); */</script></div>
			</td>
		</tr>
	</tbody>
</table>
			