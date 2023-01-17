<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page info="indb.jsp"  errorPage="/common/error_jsp.jsp"  %>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="/common/common.jsp" %>
<%
	/* 
	 * DB 연결을 필요로 하는 페이지일 경우는 true 그외에는 false; 기본값은 false;
	 */
	dbConnectFlag = true;
	LOGIN_PAGE = true;
%>
<%@ include file="/common/try.jsp" %> 
<%@ include file="/shop/common/inc/jspResource.jsp" %>
<%

	String type = requestSet.getProperty("type");
	String toEmail = requestSet.getProperty("toEmail");
	String subject = requestSet.getProperty("subject");
	String contents = requestSet.getProperty("contents");
	ResultTable rtTable = null;
		
	if("direct".equals(type)){
		String[] emailArr = toEmail.split(",");
		for(int i = 0; i<emailArr.length; i++){
			rtTable = dbHandler.executeQuery("select email from gd_member where email in ('"+emailArr[i]+"')");	
			if (rtTable.getRowCount() < 1){
				out.println("<script>");
				out.println("alert('"+emailArr[i]+"은 유효하지 않은 메일입니다')");
				out.println("history.go(-1)");
				out.println("</script>");
				return;
			}
			// toEmail(CSV) - hyunsoo@naver.com,naiive@hotmail.com,hyunsoo@gmail.com (검증된 회원이메일)
		}
	}else{
		//회원번호 받음
		//회원번호 이메일 조회
		//이메일로 이메일 발생
		rtTable = dbHandler.executeQuery("select email from gd_member where m_no in ("+toEmail+")");
		int i = rtTable.getRowCount()-1; // 1부터 시작
		toEmail = "";
		while(i >= 0){
			if(i == 0){
				toEmail += rtTable.get(i, "email"); // 0부터 시작
			}else{
				toEmail += rtTable.get(i, "email")+",";	
			}
			i--;
		}
		// toEmail(CSV) - hyunsoo@naver.com,naiive@hotmail.com,hyunsoo@gmail.com (검증된 회원이메일)
	}
	// subject, contents 를 받음
	
	System.out.println(toEmail);
	
	

%>
<%@ include file="/common/catch.jsp" %>