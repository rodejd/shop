<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : bottom.jsp
* 생성일 : 2017. 02. 07
* 작성자 : 이병환
* 설   명 : skin default1 하단 layout
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170207			이병환				최초작성
 ----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
		<footer class="footer_wrap">
			<div class="in_bx">
				<div class="bx_l">
					<dl class="ty01">
						<dt>고객센터</dt>
						<dd>
							${shopLibFunction:getProperty("compPhone")}<!-- +82 467-6797 -->&nbsp;
						</dd>
					</dl>
					<dl class="ty02">
						<dt>OPEN</dt>
						<dd>AM 10:00~PM 17:00</dd>
					</dl>
					<dl class="ty02">
						<dt>OFF-TIME</dt>
						<dd>PM 12:00 ~ 13:00</dd>
					</dl>
					<dl class="ty02">
						<dt>DAY OFF</dt>
						<dd>SAT,SUN,HOLIDAY</dd>
					</dl>
					<div style="clear:both;"></div>
				</div>
				<div class="bx_r">
					<dl>
						<dt class="mob_tit_n01">USEFUL INFORMATION</dt>
						<dd class="mob_cont_n01">
							<p><a href="/shop/mypage/mypage_qna">1:1 문의</a></p>
							<p><a href="${ctx}/shop/service/faq?sitemcd=01">FAQ</a></p>
							<p><a href="${ctx}/shop/board/list?id=notice">공지사항</a></p>
							<p><a href="${ctx}/shop/service/agreement">이용약관</a></p>
							<p><a href="${ctx}/shop/service/company">회사소개</a></p>
						</dd>
					</dl>
					<dl>
						<dt class="mob_tit_n02">POLICY</dt>
						<dd class="mob_cont_n02">
							<!-- <p><a href="${ctx}/shop/service/qualityPolicy">품질보증정책</a></p> -->
							<p><a href="${ctx}/shop/service/privacyPolicy">개인정보취급방침</a></p>
							<p><a href="${ctx}/shop/service/orderPolicy">주문배송정책</a></p>
							<p><a href="${ctx}/shop/service/exchangePolicy">교환반품정책</a></p>
							<p><a href="${ctx}/shop/service/ratingPolicy">고객등급정책</a></p>
							<p><a href="${ctx}/shop/service/payPolicy">결제/가격/적립금 정책</a></p>
						</dd>
					</dl>
					<!-- <dl>
						<dd class="last">
							<p><a href="${ctx}/shop/service/asPolicy">A/S 정책</a></p>
							<p><a href="${ctx}/shop/service/privacyPolicy">개인정보취급방침</a></p>
						</dd>
					</dl> -->
					<div style="clear:both;"></div>
<%--					<div class="btns_r_n">--%>
<%--						<a href="https://play.google.com/store/apps/details?id=com.ritzmall.app" target="_blank"><img src="/resources/images/btn_ft_google.png" alt="" /></a>--%>
<%--						<a href="http://itunes.apple.com/kr/app/id1574291120?mt=8" target="_blank"><img src="/resources/images/btn_ft_apple.png" alt="" /></a>--%>
<%--						<a href="http://pf.kakao.com/_AyKxls" target="_blank"><img src="/resources/images/btn_ft_kakao.png" alt="" /></a>--%>
<%--					</div>--%>
					<div class="bx_copy">
						<p class="tx01">ⓒ 2022 WEPINIT</p>
						<p class="tx02">서울시 강남구</p>
					</div>
				</div>
			</div>

<script>
$(document).ready(function(){
	if($(window).width() < 1209){
	$(".mob_tit_n01").click(function(){
		if(!$(this).data("click")){
			$(".mob_cont_n01").css("display", "block");
			$(this).data("click", true);
		}else{
			$(".mob_cont_n01").css("display", "none");
			$(this).data("click", false);
		}
	});
	$(".mob_tit_n02").click(function(){
		if(!$(this).data("click")){
			$(".mob_cont_n02").css("display", "block");
			$(this).data("click", true);
		}else{
			$(".mob_cont_n02").css("display", "none");
			$(this).data("click", false);
		}
	});
	}
});
</script>

<!-- top 버튼 -->
<a id="button" class="btn_top"></a>
<script>
var btn = $('#button');

$(window).scroll(function() {
  if ($(window).scrollTop() > 300) {
    btn.addClass('show');
  } else {
    btn.removeClass('show');
  }
});

$(".btn_top").click(function() {
	window.scrollTo(0, 0);
  //$("html, body").animate({ scrollTop: 0 }, "slow");
  //return false;
});
</script>
<!-- //top 버튼 -->

		</footer>
		

        <!-- <footer class="main-footer" style="background:#f4f4f4; border-top:none; display:none;">
            <div class="container" style="width:1458px; padding-left:0; padding-right:0;">
				<div class="row">
					<div class="col-md-12">
						<ul class="main-footer-links-list-lg">
							<li><a href="${ctx}/shop/service/company">회사소개</a></li>
							<li><a href="${ctx}/shop/service/private">개인정보취급방침</a></li>
							<li><a href="${ctx}/shop/service/agreement">이용약관</a></li>
							<li><a href="${ctx}/shop/service/customer">고객센터</a></li>
							<li><a href="${ctx}/shop/service/cooperation">광고/제휴 안내</a></li>
							<li><a href="${ctx}/shop/service/guide">이용안내</a></li>
							<li><a href="${ctx}/shop/service/sitemap">사이트맵</a></li>
							<li><a href="${ctx}/shop/member/find_id">아이디/비밀번호찾기</a></li>
		                  </ul>
		              </div>              
		          </div>
                <div class="row">
					<div class="col-md-12">
						<p>주소 : ${shopLibFunction:getProperty("address")} | 사업자등록번호 : ${shopLibFunction:getProperty("compSerial")}</p>
						<p>통신판매업신고번호 : ${shopLibFunction:getProperty("orderSerial")} | 개인정보관리자 : ${shopLibFunction:getProperty("adminName")} | 대표 : ${shopLibFunction:getProperty("ceoName")} | 상호명 : ${shopLibFunction:getProperty("compName")}</p>
						<p>전화번호 : ${shopLibFunction:getProperty("compPhone")} | 팩스번호 : ${shopLibFunction:getProperty("compFax")} | 메일 : ${shopLibFunction:getProperty("adminEmail")}</p>
		              </div>
             </div>
          <div class="gap gap-small"></div>
                <div class="gap gap-small"></div>
            </div>
        </footer>
        <div class="copyright-area" style="display:none;">
            <div class="container" style="width:1458px; padding-left:0; padding-right:0;">
                <div class="row">
                    <div class="col-md-12">
                        <p class="copyright-text">Copyright &copy; XMaLL. All Rights Reserved.</p>
                    </div>
                </div>
            </div>
        </div> -->
    </div>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/bootstrap.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/icheck.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/ionrangeslider.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jqzoom.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/card-payment.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/owl-carousel.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/magnific.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/custom.js"></script>


<!-- 퀵메뉴 백업
<div class="quick-wrap">
	<!-- 로그인 전
	<% if(session.getAttribute("oUserInfoLogin") != null){%>
	<a href="#" class="login-toggle btn-pop-login">LOGIN</a>
	<% }else{%>
	<!-- 로그인 후
	<a href="../member/logout.jsp" class="login-toggle" id="logout_btn">LOGOUT</a>
	<% } %>
	 <!--
	<ul class="quick-menu">
		<!-- 로그인 전 회원가입 노출
		<% if(session.getAttribute("oUserInfoLogin") != null){%>
		<li><a href="../member/join.jsp" class="join">회원가입</a></li>
		<!-- 로그인 후 회원 이름 노출
		<% }else{%>
		<li><span>${sessMemberName}님</span></li>
		<% } %>
		<li><a href="../mypage/mypage_wishlist.jsp" class="recipe">관심레시피</a></li>
		<li><a href="../member/myinfo.jsp" class="mypage">마이페이지</a></li>
		<li>
			<a href="../goods/goods_cart.jsp" class="cart">
				장바구니
				<span class="count txt-yellow">3</span>
			</a>
		</li>
		<li><a href="../mypage/mypage_orderlist.jsp" class="deliver">주문배송조회</a></li>
		<li><a href="../service/customer.jsp" class="help">HELP</a></li>
		<li><a href="../service/sitemap.jsp" class="sitemap">SITEMAP</a></li>
	</ul>

	<ul class="quick-banner">
		<li>
			<a href="#" class="open-recipe-pop"><img src="/resources/shop/data/skin/season2/images/quick_banner01.jpg" alt="레시피 구독신청" /></a>
		</li>
		<li>
			<a href="#"><img src="/resources/shop/data/skin/season2/images/quick_banner02.jpg" alt="회원가입 1,000P 적립!" /></a>
		</li>
	</ul>
	<a href="#" class="top-btn"><img src="/resources/shop/data/skin/season2/images/btn_top.png" alt="위로" /></a>
</div>
-->
<!-- 딤 팝업 -->
<div class="dim-bg-wrap"></div>
<!-- 공통 적용 스크립트 , 모든 페이지에 노출되도록 설치. 단 전환페이지 설정값보다 항상 하단에 위치해야함 --> 
<script type="text/javascript" src="//wcs.naver.net/wcslog.js"> </script> 
<script type="text/javascript"> 
if (!wcs_add) var wcs_add={};
wcs_add["wa"] = "s_445cae5b75b0";
if (!_nasa) var _nasa={};
if(window.wcs){
wcs.inflow();
wcs_do(_nasa);
}
</script>
<!-- 절대! 지우지마세요 : Start -->
<!-- <iframe name="ifrmHidden" src='/resources/shop/blank.jsp' style="display:none;width:100%;height:600"></iframe> -->
<!-- 절대! 지우지마세요 : End -->

</body>
</html>