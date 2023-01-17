<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/* 

	int menu_i = 0;
	request.setAttribute("menu_i", menu_i);
	String menu_length = "0";
	
	String menu_cate_code = "";
	String left_brandNo	= requestSet.getProperty("sno", "");			// 브랜드 NO	
	
	request.setAttribute("left_brandNo", left_brandNo);
	
	// 게시판메뉴 호출
	aMenuNmLn = Menu.getMenuNameLinks("board");
	
	request.setAttribute("aMenuNmLn", aMenuNmLn);
	
	List appBankList = null;		// 무통장입금 목록
	
	List appBrandList = null;	// 브랜드 목록
	
	List appCategoryList = null;	// 카테고리 목록
	
	List appBoardList = null;	// 커뮤니티 게시판 목록
	
	try {
		RequestSet menu_tmp_rs = (RequestSet)requestSet.clone();
		menu_tmp_rs.setProperty("category_length", "length(category) <= 6");

		request.setAttribute("appCategoryList",appCategoryList);
	}catch (Exception e) {
		log.WriteErrorLog("카테고리 추출시 오류발생");
	}
	
	try {

		appBankList = dbHandler.selectXmlQuery("xmall/bank_use_LIST", requestSet);
		request.setAttribute("appBankList", appBankList);
	}catch (Exception e) {
		log.WriteErrorLog("무통장입금 정보 추출시 오류발생");
	}
	
	try {

		appBrandList = dbHandler.selectXmlQuery("xmall_goods/goods_brand_LIST", requestSet);
		request.setAttribute("appBrandList",appBrandList);
	}catch (Exception e) {
		log.WriteErrorLog("브랜드 목록 추출시 오류발생");
	}
	
	try {
		String query = dbHandler.getXmlQuery("xmall_board/board_LIST_INFO", requestSet);
	
		appBoardList = dbHandler.selectQuery(query);	
		request.setAttribute("appBoardList", appBoardList);
	}catch (Exception e) {
		log.WriteErrorLog("커뮤니티 게시판 목록 추출시 오류발생");
	}
	
	String left = "/shop/skin/"+wskin+"/common/left.jsp";
	
	request.setAttribute("shopAlign",ShopConfig.getProperty("shopAlign", "left"));
	request.setAttribute("compPhone",ShopConfig.getProperty("compPhone", ""));
	request.setAttribute("adminEmail",ShopConfig.getProperty("adminEmail", ""));
	if(oUserInfoLogin != null){
		request.setAttribute("sessMemberName",(String)sess.get("name"));
	}
	
*/
%>
		
<jsp:include page="/WEB-INF/views/shop/front/skin/${wskin}/common/left.jsp" flush="true">
	<jsp:param name="skin" value="${wskin}" />
</jsp:include>
