
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/common/error_jsp.jsp"%>
<%-- <%@ include file="/common/common.jsp" %> --%>
<%-- <% --%>
// 	/* DB 연결을 필요로 하는 페이지일 경우는 true 그외에는 false;
// 	 * 기본값은 false;
// 	 */
// 	dbConnectFlag=true;
	 
<%-- %> --%>
<%-- <%@ include file="/common/try.jsp" %> --%>
<%-- 	var oForm = eval("document.forms['<%= requestSet.getProperty("formnm") %>']"); --%>
<!-- 	if ( oForm == null ) oForm = eval("document.forms[0]"); -->
	
<%-- 	var obj = oForm['<%= requestSet.getProperty("obj") %>']; --%>
<%-- 	var st = <%= requestSet.getPropertyAsInt("idx", 0) %> + 1; --%>
<!-- 	for (i=st;i<obj.length;i++){ -->
<!-- 		for (j=obj[i].options.length;j>0;j--) obj[i].remove(j); -->
<!-- 		obj[i].options.selectedIndex = 0; -->
<!-- 	} -->
	
<!-- function category_update(ob, ret, category, val) -->
<!-- 	{  -->
<!-- 		var idx = category.length / 3; -->
<!-- 		var obj = oForm[ob][idx]; -->
<!-- 		if (typeof(obj)=="object" && ret){ -->
<!-- 			div = ret.split("||"); -->
<!-- 			for (i=obj.options.length;i>0;i--) obj.remove(1); -->
<!-- 			for (i=0;i < div.length;i++){ -->
<!-- 				div2 = div[i].split("|"); -->
<!-- 				obj.options[i+1] = new Option(div2[0],div2[1]); -->
<!-- 				if (div2[1]==val.substring(0,div2[1].length)) obj.selectedIndex = i+1; -->
<!-- 			} -->
<!-- 		} -->
<!-- 	} -->
<%-- <% --%>
// 	//DBHandler dbHandlerCate = null;
// 	int i = 0;
	
// 	String strWhere 	= "";
// 	String strResult	= "";
// 	String tmp			= "";
	
// 	String[] category	=  null;
	
// 	ResultTable rtList	= null;

// 	try{
// 		String val = requestSet.getPropertyAsDef("val", "");	// $_GET[val];
	
// 		if ( !"".equals(val) ){
// 			i = 0;
// 			category = new String[val.length()/3];	// $category = array();
// 			for (i=0; i < val.length()/3; i++ ) {
// 				category[i] = val.substring(0, i*3);
// 			}
// 		}else{
// 			category = new String[]{requestSet.getProperty("category", "")};
// 		}
// 		String idx = requestSet.getProperty("idx");
// 		//dbHandlerCate = new DBHandler(ConfigClass.DATASOURCE);
// 		//dbHandlerCate.connect();
	
// 		// 정렬순서 셋팅
// 		requestSet.setProperty("order_by", "sort");
		
// 		for ( i=0 ; i < category.length ; i++){
// 			strResult = "";
// 			if ("0".equals(idx) || !"".equals(category[i])) {
// 				if ( "user".equals(requestSet.getProperty("mode")) ){
// 					 requestSet.setProperty("andHidden"	, "hidden=0");
// 				}
				
// 				requestSet.setProperty("category_like"	, "category like '" + category[i] + "%'");
// 				requestSet.setProperty("category_lendth", "length(category)= " + ((!"".equals(category[i])) ? String.valueOf(category[i].length() + 3) : "3"));
// 				//out.println("<br><br>");
// 				//out.println("alert('" + requestSet.getProperty("category_like") + "-------" + requestSet.getProperty("category_lendth") + "');");
// 				//out.println("<br><br>");
// 				//out.println("console.log('"+dbHandler.getXmlQuery("xmall_goods/goods_category_LIST", requestSet)+"')");
// 				rtList = dbHandler.executeXmlQuery("xmall_goods/goods_category_LIST", requestSet);
				
// 				for ( int j=0 ; j < rtList.getRowCount() ; j++ ){
// 					strResult += rtList.get(j , "catnm") + "|" + rtList.get(j , "category") + "||";
// 				}
				
// 				if ( !"".equals(strResult)) {
// 					strResult = strResult.substring(0, strResult.length()-2);
// 				}
// 				//out.println("alert('" + requestSet.getProperty("obj", "") + "-------" + strResult + "-------" + category[i] + "-------" + val + "');");
// 				out.println("category_update('" + requestSet.getProperty("obj", "") + "','" + strResult + "','" + category[i] + "','" + val + "','" + requestSet.getProperty("flag") + "');");
// 			}
// 		}
// 	}catch (Exception e) {
// 		for (i=0 ; i < e.getStackTrace().length ; i++ ) {
// 			out.println("//" + e.getStackTrace()[i] + "<br>");
// 		}
// 		out.println("//" + e.getMessage());
// 	}finally{
// 		out.println("//directconnect");
		
		
// 		//if ( null != dbHandlerCate ) {
// 		//	dbHandlerCate.disconnect();
// 		//dbHandler.directconnect();
			  
// 	}
	
<%-- %> --%>

<%-- <%@ include file="/common/catch.jsp" %> --%>