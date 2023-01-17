<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/* 

	String menu_shop_size = ShopConfig.getProperty("shopSize", "900");
	menu_shop_size = ( !"".equals(menu_shop_size) ) ? menu_shop_size : "900";
	request.setAttribute("menu_shop_size", menu_shop_size);

	String meta_title 		= ShopConfig.getProperty("title");
	String meta_keywords 	= ShopConfig.getProperty("keywords");

	request.setAttribute("meta_title", meta_title);
	request.setAttribute("meta_keywords", meta_keywords);

	int sessTime = 0;
	if(!"".equals(ShopConfig.getProperty("sessTime"))){
		sessTime = Integer.parseInt(ShopConfig.getProperty("sessTime"))*60;
	}else{
		sessTime = -1;
	}
	
	session.setMaxInactiveInterval(sessTime);


	String header = "/shop/skin/"+wskin+"/common/header.jsp";
	
	request.setAttribute("shopLineWidthL", ShopConfig.getProperty("shopLineWidthL", ""));
	request.setAttribute("shopLineWidthR", ShopConfig.getProperty("shopLineWidthR", ""));
	request.setAttribute("shopLineColorL", ShopConfig.getProperty("shopLineColorL", ""));
	request.setAttribute("shopLineColorR", ShopConfig.getProperty("shopLineColorR", ""));
	request.setAttribute("shopLineWidthC", ShopConfig.getProperty("shopLineWidthC", ""));
	request.setAttribute("shopLineColorC", ShopConfig.getProperty("shopLineColorC", ""));
	request.setAttribute("shopAlign", ShopConfig.getProperty("shopAlign", "left"));
	request.setAttribute("sword", requestSet.getProperty("sword", ""));
	String cookie = getCookies(request, "carts");
	System.out.println("oriCookie---->"+cookie);
	String cartEA = "";
	if(cookie.equals("")){
		cartEA = "0";
	}else{
		cartEA = String.valueOf(cookie.split(",").length);
				
	}
	request.setAttribute("cartEA", cartEA);
	
	
	String wishEA = "";
	if(oUserInfoLogin==null){
		wishEA = "-1";
	}else{
		if(null != oUserInfoLogin.getUserId()){
			requestSet.setProperty("m_id", oUserInfoLogin.getUserId());
		}
		ResultTable m_nom = dbHandler.executeXmlQuery("xmall/front_m_no_FIND",requestSet);
		requestSet.setProperty("m_no", m_nom.get("m_no"));
		ResultTable wish = dbHandler.executeXmlQuery("xmall/front_wishlist_CNT",requestSet );
		wishEA = wish.get("cnt");
		
	}
	request.setAttribute("wishEA", wishEA);
	
	// Header 메뉴
	List appMenuCategoryList = null;			// 카테고리 목록
	List appMenuBrandList = null;				// 브랜드 목록
	List appMenuBoardList = null;				// 커뮤니티 게시판 목록
	
	try {
		RequestSet menu_tmp_rs = (RequestSet)requestSet.clone();
		menu_tmp_rs.setProperty("category_length", "length( a.category ) <= 6");
		appMenuCategoryList = dbHandler.selectXmlQuery("xmall_goods/goods_category_LIST01", menu_tmp_rs);
		request.setAttribute("appMenuCategoryList",appMenuCategoryList);
	}catch (Exception e) {
		log.WriteErrorLog("카테고리 추출시 오류발생");
	}
	
	// Brand 메뉴
	try {
		RequestSet brand_menu_tmp_rs = (RequestSet)requestSet.clone();
		brand_menu_tmp_rs.setProperty("andBrandNo", "sno != '' ");
		appMenuBrandList = dbHandler.selectXmlQuery("xmall_goods/goods_brand_LIST", brand_menu_tmp_rs);
		request.setAttribute("appMenuBrandList",appMenuBrandList);
	}catch (Exception e) {
		log.WriteErrorLog("브랜드 목록 추출시 오류발생");
	}
	
	// 커뮤니티 메뉴
	try {
		String query = dbHandler.getXmlQuery("xmall_board/board_LIST_INFO", requestSet);
		appMenuBoardList = dbHandler.selectQuery(query);	
		request.setAttribute("appMenuBoardList", appMenuBoardList);
	}catch (Exception e) {
		log.WriteErrorLog("커뮤니티 게시판 목록 추출시 오류발생");
	}
	
	ArrayList header_todayGoodsList = WebUtil.getTodayGoodsCookie(request,response);			// 쿠키에 담긴 최근본 상품
	ArrayList header_todayGoodsList1 = new ArrayList();
	
	int header_list_length = header_todayGoodsList.size()-1;									// 총갯수	
	
	//최근 본 상품부터 보여지기 위해 for문 거꾸로 돌리기
	for(int today_temp=header_list_length ; today_temp >= 0 ; today_temp--){
		header_todayGoodsList1.add(header_todayGoodsList.get(today_temp));
	}
	
	request.setAttribute("header_todayGoodsList", header_todayGoodsList1);

*/
%>


<jsp:include page="/WEB-INF/views/shop/front/skin/${wskin}/common/header.jsp" flush="true">
	<jsp:param name="skin" value="${wskin}" />
</jsp:include>