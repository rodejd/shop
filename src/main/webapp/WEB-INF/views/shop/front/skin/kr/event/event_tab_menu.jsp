<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String tab_order = request.getParameter("tab_order");%>
<ul class="nav nav-tabs" id="myTab">
	<li <%= "1".equals(tab_order) ? "class='active'" : "" %>><a href="eventList"><i class="fa fa-list nav-tab-icon"></i>진행중인 이벤트</a></li>
	<li <%= "2".equals(tab_order) ? "class='active'" : "" %>><a href="eventEndList"><i class="fa fa-cogs nav-tab-icon"></i>종료된 이벤트</a></li>
	<li <%= "3".equals(tab_order) ? "class='active'" : "" %>><a href="surveyList"><i class="fa fa-list nav-tab-icon"></i>진행중인 설문조사</a></li>
	<li <%= "4".equals(tab_order) ? "class='active'" : "" %>><a href="surveyEndList"><i class="fa fa-cogs nav-tab-icon"></i>종료된 설문조사</a></li>
</ul>


