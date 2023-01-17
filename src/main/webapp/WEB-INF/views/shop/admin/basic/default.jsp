<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<%-- ================================================================================
* HTML CONTENT 시작
================================================================================ --%>
<!-------------------- 서브 본문 본격적으로 시작  ------------------------------->
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form name="form" method="post" action="indb" onsubmit="return chkForm2(this)">
<input type="hidden" name="mode" value="config">
	
	<div class="title title_top">기본정보<span>쇼핑몰 기본정보를 입력해주세요.</span> 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=basic&no=2',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle"></a>-->
	</div>
	<table class="tb">
		<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
		<tr>
			<td>쇼핑몰이름</td>
			<td><input type="text" name="shopName" value="${defaultVO.dbData.shopName}" class="lline" style="width:200" maxlength="200"></td>
			<td>영문이름</td>
			<td><input type="text" name="shopEng" value="${defaultVO.dbData.shopEng}" class="lline" style="width:200" maxlength="200"></td>
		</tr>
		<tr>
			<td>관리자 Email</td>
			<td><input type="text" name="adminEmail"  class="lline" value="${defaultVO.dbData.adminEmail}" style="width:200" maxlength="200"></td>
			<td>쇼핑몰 URL</td>
			<td colspan="3">http://<input type="text" name="shopUrl" style="width:163px" value="${defaultVO.dbData.shopUrl}" class="lline" maxlength="200"></td>
		</tr>
	</table>
	
	<div class="title">회사정보<span>쇼핑몰 화면하단의 카피라이트 부분에 표시됩니다</span> 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=basic&no=2',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle"></a>-->
	</div>
	<table class="tb">
		<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
		<tr>
			<td>상호명</td>
			<td colspan="3"><input type="text" name="compName"  class="lline" value="${defaultVO.dbData.compName}" style="width:200" maxlength="200"></td>
		</tr>
		<tr>
			<td>업태</td>
			<td><input type="text" name="service" value="${defaultVO.dbData.service}"  class="line" maxlength="200"></td>
			<td>종목</td>
			<td><input type="text" name="item" value="${defaultVO.dbData.item}"  class="line" maxlength="200"></td>
		</tr>
		<tr>
			<td>사업장우편번호</td>
			<td colspan="3">
			<input type="text"  id="postcode" name="zipcode" size="6" readonly value="${defaultVO.dbData.zipcode}"  class="line">
			<a href="javascript:postcode();"><img src="/resources/shop/admin/img/btn_zipcode.gif" align="absmiddle"></a>
			</td>
		</tr>
		<tr>
			<td>사업장주소</td>
			<td colspan="3"><input type="text" id ="address" name="address" style="width:100%" value="${defaultVO.dbData.address}"  class="line" maxlength="200"></td>
			
		</tr>
		<tr>
			<td>사업자번호</td>
			<td><input type="text" name="compSerial" value="${defaultVO.dbData.compSerial}"  class="line" maxlength="200"></td>
			<td>통신판매신고번호</td>
			<td><input type="text" name="orderSerial" value="${defaultVO.dbData.orderSerial}"  class="line" maxlength="200"></td>
		</tr>
		<tr>
			<td>대표자명</td>
			<td><input type="text" name="ceoName" value="${defaultVO.dbData.ceoName}"  class="line" maxlength="200"></td>
			<td>관리자명(개인정보)</td>
			<td><input type="text" name="adminName" value="${defaultVO.dbData.adminName}"  class="line" maxlength="200"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="compPhone" value="${defaultVO.dbData.compPhone}"  class="line" maxlength="200"></td>
			<td>팩스번호</td>
			<td><input type="text" name="compFax" value="${defaultVO.dbData.compFax}"  class="line" maxlength="200"></td>
		</tr>
	</table>
	
	<div class="title">상단타이틀<span>브라우저 상단틀에 나오는 타이틀과 검색사이트에서 유용한 키워드를 입력하세요</span> 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=basic&no=2',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
	</div>
	<table class="tb">
		<col class="cellC"><col class="cellL">
		<tr>
			<td>쇼핑몰타이틀</td>
			<td><input type="text" name="title" value="${defaultVO.dbData.title}"  class="lline" maxlength="200"></td>
		</tr>
		<tr>
			<td>검색엔진 키워드</td>
			<td><input type="text" name="keywords" value="${defaultVO.dbData.keywords}" style="width:100%"  class="line" maxlength="200"></td>
		</tr>
	</table>
	<div class="title">로그인 api설정<span>간편로그인 관리입니다(현재는 local로 되어있음)</span> 
	</div>
	
	<br>
	<table class="tb">
		<col class="cellC">
		<col class="cellL">
		<col class="cellC">
		<col class="cellL">
		<tr>
			<td>페이스북</td>
			<td>
				<table style="width:100%">
					<tr>
						<td style="width:100px;height:26px">CLIENT_ID</td> 
						<td style="height:26px"><input type="text" name="facebook_client_id" value="${defaultVO.dbData.facebook_client_id}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">CLIENT_Secret</td> 
						<td style="height:26px"><input type="text" name="facebook_client_secret" value="${defaultVO.dbData.facebook_client_secret}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">REDIRECT_URI</td> 
						<td style="height:26px">${baseUrl}<input type="text" name="facebook_redirect_uri" value="${defaultVO.dbData.facebook_redirect_uri}" style="width:40%" ></td>
					</tr>
				</table>
			</td>
			<td>구글</td>
			<td>
				<table style="width:100%">
					<tr>
						<td style="width:100px;height:26px">CLIENT_ID</td> 
						<td style="height:26px"><input type="text" name="google_client_id" value="${defaultVO.dbData.google_client_id}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">CLIENT_Secret</td> 
						<td style="height:26px"><input type="text" name="google_client_secret" value="${defaultVO.dbData.google_client_secret}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">REDIRECT_URI</td> 
						<td style="height:26px">${baseUrl}<input type="text" name="google_redirect_uri" value="${defaultVO.dbData.google_redirect_uri}" style="width:40%" ></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>카카오</td>
			<td>
				<table style="width:100%">
					<tr>
						<td style="width:100px;height:26px">CLIENT_ID</td> 
						<td style="height:26px"><input type="text" name="kakao_client_id" value="${defaultVO.dbData.kakao_client_id}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">CLIENT_Secret</td> 
						<td style="height:26px"><input type="text" name="kakao_client_secret" value="${defaultVO.dbData.kakao_client_secret}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">REDIRECT_URI</td> 
						<td style="height:26px">${baseUrl}<input type="text" name="kakao_redirect_uri" value="${defaultVO.dbData.kakao_redirect_uri}" style="width:40%" ></td>
					</tr>
				</table>
			</td>
			<td>애플</td>
			<td>
				<table style="width:100%">
					<tr>
						<td style="width:100px;height:26px">CLIENT_ID</td> 
						<td style="height:26px" colspan="3"><input type="text" name="apple_client_id" value="${defaultVO.dbData.apple_client_id}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">TEAM_ID</td> 
						<td style="height:26px"><input type="text" name="apply_team_id" value="${defaultVO.dbData.apply_team_id}" style="width:100%"></td>
						<td style="width:100px;height:26px">KEY_ID</td> 
						<td style="height:26px"><input type="text" name="apple_key_id" value="${defaultVO.dbData.apple_key_id}" style="width:100%"></td>
					</tr>
					<tr>
						<td style="width:100px;height:26px">REDIRECT_URI</td> 
						<td style="height:26px">${baseUrl}<input type="text" name="apple_redirect_uri" value="${defaultVO.dbData.apple_redirect_uri}" style="width:40%" ></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<div class="title">기타설정<span>로그아웃타임 관리입니다</span> 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=basic&no=2',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle"></a>-->
	</div>
	<table class="tb">
		<col class="cellC"><col class="cellL">
	<!--
	<tr>
		<td>솔루션폴더명</td>
		<td><input type=text name=rootDir value="/shop">
		<span class=small><font color=#5B5B5B>원하시는 폴더명으로 변경하신후 반드시 그 폴더를 새로 생성하세요</font></span>
		</td>
	</tr>
	-->
		<tr>
			<td>자동로그아웃</td>
			<td>
				로그인 후 <input type="text" name="sessTime" value="${defaultVO.dbData.sessTime}" size="6" onkeydown="onlynumber(event)" onkeyup="removeChar(event)"  class="right line" maxlength="3"> 분간 클릭이 없으면 자동로그아웃됩니다.
				<span class="small"><font class="extext">공란으로 두면 시간제한없음</font></span>
			</td>
		</tr>
	</table>
	
	
	<div class="button">
		<input type="image" src="/resources/shop/admin/img/btn_register.gif">
		<a href="javascript:history.back()"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
	</div>

</form>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 시작 catch 부분
================================================================================ --%>

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script>
    function postcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('address').focus();
            }
        }).open();
    }
</script>
