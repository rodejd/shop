/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonConstants {
	//request
	private static String ctx = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getContextPath();
	
	// Session KEY 관련 상수
	public final static String _FRONT_SESSION_KEY_ = "xmall_user";
	public final static String _ADMIN_SESSION_KEY_ = "xmall_admin";
	public final static String _SELLER_SESSION_KEY_ = "xmall_seller";
	public final static String _MYRITZ_SESSION_KEY_ = "xmall_myritz";
	
	// 로그인 URL 관련 상수
	public final static String LOGIN_ADMIN_URL = ctx +"/shop/admin/login/login";
	public final static String LOGIN_FRONT_URL = ctx +"/shop/member/login";
	public final static String MAIN_ADMIN_URL = ctx +"/shop/admin/basic/index";
	public final static String LOGIN_SHOP_RETURN_URL = ctx +"/shop/main";
	
	// 페이징 상수
	public final static String PAGE_NO = "pageNo";
	public final static String PAGE_SIZE = "pageSize";
	public final static String ROW_START = "rowStart";
	
	//sns request URL
	public final static String KAKAO_ACCESSTOKEN_RequestUrl = "https://kauth.kakao.com/oauth/token";
	public final static String KAKAO_USERINFO_RequestUrl = "https://kapi.kakao.com/v2/user/me";
    public final static String FACEBOOK_ACCESSTOKEN_RequestUrl = "https://graph.facebook.com/v2.11/oauth/access_token?";
	public final static String FACEBOOK_USERINFO_RequestUrl = "https://graph.facebook.com/me?fields=id,name,email&access_token=";
    public final static String GOOGLE_ACCESSTOKEN_RequestUrl = "https://accounts.google.com/o/oauth2/token?";
	public final static String GOOGLE_USERINFO_RequestUrl = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public final static String APPLE_PUBLICKEY_RequestUrl = "https://appleid.apple.com/auth/keys";
    public final static String APPLE_AUDIENCE_RequestUrl = "https://appleid.apple.com";
	public final static String APPLE_USERINFO_RequestUrl = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	
	public final static String BIZTALK_ACCESSTOKEN_RequestUrl = "https://www.biztalk-api.com/v2/auth/getToken";
	public final static String BIZTALK_SENDALIMTALK_RequestUrl = "https://www.biztalk-api.com/v2/kko/sendAlimTalk";
	
 	public final static String NAVER_ACCESSTOKEN_RequestUrl = "https://nid.naver.com/oauth2.0/token";
	public final static String NAVER_USERINFO_RequestUrl = "https://openapi.naver.com/v1/nid/me";
	public final static String INSTAGRAM_ACCESSTOKEN_RequestUrl = "https://api.instagram.com/oauth/access_token";
	public final static String INSTAGRAM_USERINFO_RequestUrl = "https://api.instagram.com/v1/users/self/?access_token=";
		
	// 관리자에서 로그인 제외 URL
	public static final String[] ADMIN_LOGIN_EXCEPT_URL = {
		ctx +"/shop/admin/login/login"					// 로그인
		, ctx +"/shop/admin/order/orderCancel"
		, ctx +"/shop/admin/order/indb.cancel"
		, ctx +"/shop/admin/login/login_check.dojson"	// 로그인 프로세스
		, ctx +"/shop/admin/sweetTracker/trackingInfo"	// 운송장번호 조회 (관리자와 회원 모두 사용)
	};
	
	// SHOP에서 로그인 필수 URL
	public static final String[] SHOP_LOGIN_URL = {
		ctx +"/login/loginProcess_ajax"				//로그인
		, ctx +"/shop/mypage/main_index"			//마이페이지(메인)
		, ctx +"/shop/mypage/mypage_orderlist"		//마이페이지(주문내역/배송조회)
		, ctx +"/shop/mypage/mypage_cancellist"		//마이페이지(주문내역/배송조회)
		, ctx +"/shop/mypage/mypage_orderinfo"		//마이페이지(주문내역/배송조회)
		, ctx +"/shop/mypage/mypage_coupon"			//마이페이지(할인쿠폰내역)
		, ctx +"/shop/mypage/mypage_emoney"			//마이페이지(적립금내역)
		, ctx +"/shop/mypage/mypage_qna_goods"		//마이페이지(나의 상품문의)
		, ctx +"/shop/member/myinfo"				//마이페이지(정보수정)
		, ctx +"/shop/member/hack"					//마이페이지(회원탈퇴)
		, ctx +"/shop/mypage/mypage_review"			//마이페이지(나의 상품후기)
		, ctx +"/shop/mypage/mypage_wishlist"		//마이페이지(위시리스트)
		, ctx +"/shop/mypage/ajaxWishListAdd.doJson"//마이페이지(위시리스트 추가)
		, ctx +"/shop/mypage/mypage_today"			//최근본상품
	    , ctx +"/shop/mypage/mypage_qna"			//마이페이지(1:1문의게시판)
	  //, ctx +"/shop/goods/goods_cart"				//장바구니
	  //, ctx +"/shop/order/order"					//구매페이지
	};
	
	// #####
	// # 판매사 추가
	// #####
	public final static String LOGIN_SELLER_URL = ctx +"/shop/seller/login";
	public final static String MAIN_SELLER_URL = ctx +"/shop/seller/basic/default";
	
	// 판매사에서 로그인 제외 URL
	public static final String[] SELLER_LOGIN_EXCEPT_URL = {
		ctx +"/shop/seller", ctx +"/shop/seller/login"			// 로그인
		, ctx +"/shop/seller/login_check.dojson"		// 로그인 프로세스
	};
	
	// API 인증키
	public final static String API_KEY = "792c6312-e3de-4e98-be68-bacf802321d1";
}
