<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>   
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/hack.js"></script>
    
<!-- <header class="page-header page-header-banner x-member-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">회원탈퇴</h1>
        </div>
    </div>
</header> -->

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-hack container_mypage">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="../mypage/mypage_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="2" />
		</jsp:include>
	
		<div class="loin_out_wrap">
			<div class="">
				<div class="tab-pane fade in active">
					<div class="">
						<input class="js_redirectUrl" type="hidden" value="${memberHackVO.redirectUrl}">
						<input class="js_alertMessage" type="hidden" value="${memberHackVO.alertMessage}">
						<c:choose>
							<c:when test="${memberHackVO.redirectUrl ne '/shop/main/index'}">
								<form method="post" action="${ctx }/shop/member/hack/indb" id="form">
									<!-- 이부분은 필요없다 판단되면 삭제하자. -->
									<input type="hidden" name="act" value="Y">
									<input type="hidden" name="facebook_id" value="${facebook_id }">
									<input type="hidden" name="naver_id" value="${naver_id }">
									
									<div class="stit01">회원탈퇴</div>
									<div class="tx01">
										리츠몰은 평일 <span style="font-weight:600;">오전 10시부터 오후 5시</span>까지 고객센터를 운영하고 있습니다.<br/>
										불편사항에 대해 연락 주시면 최선을다해 해결 하도록 노력하겠습니다.<br/><br/>
										(고객센터 467-6797)<br/><br/><br/>
										회원 탈퇴 시, 회원님의 모든 개인정보와 주문내역이 삭제되며<br/>
										<span>동일한 계정으로는 재가입이 불가능합니다.</span>
									</div>
									
									<div class="stit02">비밀번호 재확인</div>
									<div>
										<div class="tx02">* 고객님 개인정보를 안전하게 보호하기 위해 비밀번호를 다시 한 번 확인합니다.</div>
										<c:if test="${empty snscheck}">									
											<div class="bx_inp">
												<dl>
													<dt>로그인 계정</dt>
													<dd>${shop_so.getUserInfo().userId}</dd>
												</dl>
												<dl>
													<dt>기존 비밀번호</dt>
													<dd><input class="form-control" type="password" id="password" name="password" title="비밀번호" />
												</dl>
											</div>
									   </c:if>  
									</div>
		
									<div class="stit02">탈퇴 안내</div>
									<div class="tx03">
										<ul>
											<li>- 회원탈퇴 하시면 보유하고 계신 쿠폰 및 포인트는 자동 소멸됩니다.</li>
											<li>- 주문이력이 있는 고객님의 정보는 회원탈퇴 이후에도 [전자상거래등에서의 소비자보호에 관한 법률]에서 정한 일정기간동안 보존됩니다.</li>
											<li>- SMS, E-MAIL 광고를 수신하셨다면 탈퇴 이후 약 2일~3일 정도까지 광고가 전송될 수 있사오니, 이 점 양해 부탁드립니다.</li>
										</ul>
										<p><input type="checkbox" class="ck_my_ty01" id="out01" /><label for="out01">회원탈퇴안내를 모두 확인하였으며 탈퇴에 동의합니다.(필수)</label></p>
										<p><input type="checkbox" class="ck_my_ty01" id="out02" /><label for="out02">고객님의 쿠폰 및 적립금 자동소멸에 동의합니다.(필수)</label></p>
									</div>
		
									<div class="btm_btns">									
										<a href="myinfo" onfocus="this.blur();"><input class="btn btn-primary btn_ty_b" type="button" value="취소하기" /></a>
										<input class="btn btn-primary btn_ty_w" type="submit" value="회원탈퇴" />
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<!-- 탈퇴 완료 -->
								<div class="out_end">
									<dl>
										<dt>회원 탈퇴가 완료되었습니다.</dt>
										<dd>회원님의 개인정보는 탈퇴 즉시 삭제되며, 쿠폰 및 적립금은 모두 소멸됩니다.</dd>
									</dl>
									<p>동일한 계정으로 3개월 간 재가입이 불가능합니다.</p>
	
									<div class="btm_btns">
										<a href="/shop/main/index" class="btn_ty_w">메인으로 가기</a>
									</div>
								</div>
								<!-- //탈퇴 완료 -->
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<c:if test="${memberHackVO.redirectUrl ne '/shop/main/index'}">
	<script type="text/javascript">
	$(function(){
		$("#form").on("submit",function(e){
			<c:if test="${empty snscheck}">
				if ($("#password").val().trim() == ""){
					alert('비밀번호를 입력하여 주십시요.');
					$("#password").focus();
					return false;
				}
			</c:if>
			
			if (!$("#out01").prop("checked")){
				alert('회원탈퇴안내를 모두 확인하였으며 탈퇴에 동의해 주십시요.');
				$("#out01").focus();
				return false;
			}
			
			if (!$("#out02").prop("checked")){
				alert('고객님의 쿠폰 및 적립금 자동소멸에 동의해 주십시요.');
				$("#out02").focus();
				return false;
			}
	
			if (!confirm('회원탈퇴를 하시면 회원님의 모든 데이타( 개인정보, 포인트 등 )가 삭제 되어집니다.\n그래도 회원을 탈퇴하시겠습니까?')){
				return false;
			}	
		});
	});
	
	function isEmpty(s) {
	    if(typeof s == 'string')
	        s = $.trim(s);
	
	    if(s == undefined || s == null || s == '' || s.length == 0)
	        return true;
	
	    return false;
	}
	
	var alertMessage = $('.js_alertMessage').val();
	var redirectUrl  = $('.js_redirectUrl').val();
	if(!isEmpty(alertMessage)) {
		alert(alertMessage);
		location.href = redirectUrl;
	}
	</script>
</c:if>