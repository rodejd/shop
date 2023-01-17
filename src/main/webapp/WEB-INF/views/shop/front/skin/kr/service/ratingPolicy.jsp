<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : index.jsp
* 생성일 : 2017. 02. 07
* 작성자 : 이병환
* 설   명 : skin default1 사용자 index
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170208			이병환				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<script type="text/javascript">

$(function(){

});

</script>

<!-- <header class="page-header page-header-banner x-service-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h1 class="page-title">고객등급정책</h1>
		</div>
	</div>
</header> -->

<div class="top_tit_ty01">
	<div class="tit_dp01">POLICY</div>
</div>

<div class="x-agreement container_service">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="../service/service_tab_menu02.jsp" flush="true">
			<jsp:param name="tab_order" value="4" />
		</jsp:include>

		<!-- <div class="cont_policy01">
			<div class="tp_tit">고객등급 정책</div>
			<div>
				<img src="/resources/shop/data/skin/kr/images/img_test01.png" alt="" style="width:100%;" />
			</div>
		</div> -->

		<div class="cont_policy01 pc">
			<div class="tp_tit">고객등급 정책</div>
			<A name=00></A> 
			<c:import url="file:////${filePath}${policy}" charEncoding="utf-8" var="html"/>
			${fn:replace(fn:replace(fn:replace(html, '&lt;', '<'), '&gt;', '>'), '&#47;', '/') }
		</div>

		<div class="cont_policy01 mob">
			<div class="tp_tit">고객등급 정책</div>
			<div style="padding:0 0 23px 0; margin-bottom:10px; border-bottom:1px solid #d4d4d4;">
				<ul style="margin:0 -10px;">
					<li class="r_tab01" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab01.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab02" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab02.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab03" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab03.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab04" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab04.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab05" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab05.png" alt="" style="width:100%;" /></a></li>
					<li class="r_tab06" style="float:left; width:16.6666%; padding:0 10px;"><a href="javascript:void(0);"><img src="/resources/shop/data/skin/kr/images/r_tab06.png" alt="" style="width:100%;" /></a></li>
				</ul>
				<div style="clear:both;"></div>
			</div>
			<div class="r_mob01" style="margin:0 -20px;"><img src="/resources/shop/data/skin/kr/images/r_mob01.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob02" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob02.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob03" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob03.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob04" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob04.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob05" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob05.jpeg" alt="" style="width:100%;" /></div>
			<div class="r_mob06" style="margin:0 -20px; display:none;"><img src="/resources/shop/data/skin/kr/images/r_mob06.jpeg" alt="" style="width:100%;" /></div>
		</div>

		<script>
			$(document).ready(function(){
				$(".r_tab01").click(function(){
					$(".r_mob01").css("display", "block");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab02").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "block");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab03").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "block");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab04").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "block");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab05").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "block");
					$(".r_mob06").css("display", "none");
				});
				$(".r_tab06").click(function(){
					$(".r_mob01").css("display", "none");
					$(".r_mob02").css("display", "none");
					$(".r_mob03").css("display", "none");
					$(".r_mob04").css("display", "none");
					$(".r_mob05").css("display", "none");
					$(".r_mob06").css("display", "block");
				});
			});
			</script>

	</div>
</div>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



