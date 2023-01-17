<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/join.js"></script>

<header class="page-header page-header-banner x-member-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">회원가입</h1>
        </div>
    </div>
</header>



<div class="x-join container sub">
	
	<form action="${ctx}/shop/member/join_input" method="post" id="chkForm">
	
		<section class="panel join-panel">
			<header class="panel-heading">
				<h3 class="panel-title">
					<input type="checkbox" name="chk1" id="p_agree1" title="이용약관 동의" />
					<label for="p_agree1">${agShopName } 이용약관(필수)</label>
				</h3>
			</header>
			<div class="panel-body" style="height:320px">
				<c:import url="file:////${filePath}${clause1}" charEncoding="utf-8" var="html1"/>
				${fn:replace(fn:replace(fn:replace(html1, '&lt;', '<'), '&gt;', '>'), '&#47;', '/') }
			</div>
		</section>
		
		<section class="panel join-panel">
			<header class="panel-heading">
				<h3 class="panel-title">
					<input type="checkbox" name="chk2" id="p_agree2" title="개인정보 수집 동의" />
					<label for="p_agree2">개인정보 수집 동의서(필수)</label>
				</h3>
			</header>
			<div class="panel-body" style="height:320px">
				<c:import url="file:///${filePath}${clause2}" charEncoding="utf-8" var="html2" />
				${fn:replace(fn:replace(fn:replace(html2, '&lt;', '<'), '&gt;', '>'), '&#47;', '/') }
			</div>
			
		</section>
		
		<input class="js_allCheck" type="checkbox" id="p_all_agree"/>
		<label for="p_all_agree">전체 동의</label>
		
		<div class="gap gap-small"></div>
		
		<div class="text-center">
			<input class="btn btn-primary active" type="submit" id="join_btn"  value="회원가입" />
			<input class="btn btn-primary" type="button" id="pre_btn"  value="이전으로" onclick="history.back(-1)"/>
		</div>
	</form>
	
</div>