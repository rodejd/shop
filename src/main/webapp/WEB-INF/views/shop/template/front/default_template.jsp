<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : default_template.jsp
* 생성일 : 2017. 02. 06
* 작성자 : 이병환
* 설   명 : 사용자 default template
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170206			이병환				최초작성
 ----------------------------------------------------------------------------------------------%>
<%@ taglib prefix="tiles"     uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="left"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="bottom"/>