<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
    
    
    
<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<!DOCTYPE HTML>
<html>
<head>
<title>${meta_title}<%//= meta_title %></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="description" content="${meta_title}<%//= meta_title %>">
	<meta name="keywords" content="${meta_keywords}<%//= meta_keywords %>">
    <!-- <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta content="utf-8" http-equiv="encoding">
    <meta name="keywords" content="Template, html, premium, themeforest" />
    <meta name="description" content="TheBox - premium e-commerce template">
    <meta name="author" content="Tsoy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->

	<%-- <link rel="styleSheet" href="<%=WEB_PATH%>/css/default.css"> --%>
	<link rel="stylesheet" type="text/css" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/css/jquery-ui.css" media="all" />
    <link href='http://fonts.googleapis.com/css?family=Roboto:500,300,700,400italic,400' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/css/bootstrap.css">
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/css/font-awesome.css">
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/css/styles.css">
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/css/mystyles.css">
    
    <script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/common.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/jquery.aw-showcase.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/jquery.ui-selectBox.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/datepicker-ko.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"></script>

<script type="text/javascript">
	//<![CDATA[
		var NJ = $.noConflict(true);
	//]]>
</script>
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/masonry.pkgd.js"></script>

<!--[if lt IE 9]>
<![endif]-->

<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/mypage/mypage_qna_register_popup.js"></script>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	
});

</script>
</head>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<body>

	<form method=post action="indb" onSubmit="return chkForm(this)">
	<input type=hidden name=mode value="${ myBoardVO.mode }<%//= mode%>">
	<input type=hidden name=sno value="${ myBoardVO.sno }<%//= sno%>">
		<div class="popup-mypage-qna-del">
			<table width=100% cellpadding=0 cellspacing=0 border=0 style="margin-top:30px;">
				<tr>
					<td>
						<div style="text-align:center;font-weight:bold;">
						해당 글을 삭제하시겠습니까?<br>데이터 삭제시 복구가 불가능 합니다.
						</div>
					</td>
				</tr>
				<tr>
					<td>
					<div style="text-align:center;font-weight:bold;margin-top:10px;">
						<input class="btn btn-primary" type="submit" value="삭제하기" />
						<a href="javascript:self.close();" class="btn btn-default">닫기</a>
					</div>
					</td>
				</tr>
			</table>
		</div>
	
	
	</form>
	
</body>
</html>
    