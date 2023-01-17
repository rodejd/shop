<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/* 

	String menu_shop_size = ShopConfig.getProperty("shopSize", "900");
	menu_shop_size = ( !"".equals(menu_shop_size) ) ? menu_shop_size : "900";

	String meta_title 		= ShopConfig.getProperty("title");
	String meta_keywords 	= ShopConfig.getProperty("keywords");

	String sessTime = ShopConfig.getProperty("sessTime");

 */	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="<%//= meta_title %>">
<meta name="keywords" content="<%//= meta_keywords %>">

<link rel="styleSheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/style.css">
<link rel="stylesheet" type="text/css" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/default.css" media="all" />
<link rel="stylesheet" type="text/css" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/jquery-ui.css" media="all" />
<script type="text/javascript" src="/resources/js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.aw-showcase.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ui-selectBox.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/datepicker-ko.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-1.8.1.min.js"></script>
<script type="text/javascript">
	//<![CDATA[
		var NJ = $.noConflict(true);
	//]]>
</script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/resources/js/masonry.pkgd.js"></script>
<!-- <script type="text/javascript">
	$(function() {
		$('.reivew-list > dd').hide();
		$(".user-txt").click(function(){
			var ddStatus = $(this).parents("dt").next("dd").attr("ddStatus");
			if(ddStatus == "block"){
				$(this).parents("dt").next("dd").attr("style","display:none;").attr("ddStatus","none");
			}else{
				$(this).parents("dt").next("dd").attr("style","display:block;").attr("ddStatus","block");
			}
		});
	});
</script> -->
<title><%//= meta_title %></title>
</head>
