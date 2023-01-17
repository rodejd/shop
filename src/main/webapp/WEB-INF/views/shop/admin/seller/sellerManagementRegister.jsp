<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<%--<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>--%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script language="javascript" src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	
	// 우편번호
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
		           document.getElementById('address_sub').focus();
		       }
		   }).open();
		}	// END postcode

	// 수정페이지 이동
	function goModify(sellerCd) {

		$("#sellerCd").val(sellerCd);
		$("#mode").val("modify");
		$("#view").val("list");
		$("#fmList").attr("action", "../seller/register");
		$("#fmList").submit();

	}
	
	// 목록 페이지 이동
	function goBack() {

		var addr = "";

		if("list" == $("#view").val()) {
			addr = "../seller/list?menuKey=122";
		} else {
			addr = "../seller/confirmList?menuKey=125";
		}

		$("#fmList").attr("onsubmit", "");
		$("#fmList").attr("action", addr);
		$("#fmList").submit();
		
	}
	
	// 저장 버튼 클릭시
	function goSave() {
		
		$("#fmList").attr("action", "../seller/indb");
		$("#fmList").submit();

	}
	
	
	$(document).ready(function() {
		// 등록시
		if($("[name='mode']").eq(0).val() == "") {
			$("input[name='status']").attr( "disabled", "disabled" );
		}
 		
		// 판매자신청목록 / 판매자목록 버튼 보임/숨김
		if("list" == $("#view").val()) {
			$("#listName").show();
			$("#confirmListName").hide();
		} else {
			$("#listName").hide();
			$("#confirmListName").show();			
		}
		
		// 아이디 중복체크 클릭시
		$("#btn_chk_id").on("click", function() {
			
			if ("" == $("[name='id']").eq(0).val() || null == $("[name='id']").eq(0).val() ) {
				alert("판매사 아이디를  먼저 입력하세요.");
				$("[name='id']").eq(0).focus();
				return false;
			}
			
			ajaxCallJsonPost("/shop/admin/seller/chkId.dojson", "fmList", function(result) {
				if(result) {
					var sResult = JSON.stringify(result);
					var pResult = JSON.parse(sResult);
	
					if(pResult.managementFM.dupliCount > 0) {
						alert('이미 등록된 아이디입니다.');
						$('#chk_id').val('0');
						$("[name='id']").eq(0).focus();
					} else {
						alert('사용가능한 아이디입니다.'); 
						$('#chk_id').val('1');
					}
					return;
				} else {
					alert("오류가 발생하였습니다.");
					return;
				}
			});
		});
		
		// 판매사코드 중복체크 클릭시
		$("#btn_chk_sellerCd").on("click", function() {
			
			if ("" == $("[name='sellerCd']").eq(0).val() || null == $("[name='sellerCd']").eq(0).val() ) {
				alert("판매사 코드를  먼저 입력하세요.");
				$("[name='sellerCd']").eq(0).focus();
				return false;
			}
			
			ajaxCallJsonPost("/shop/admin/seller/chkSellerCd.dojson", "fmList", function(result) {
				if(result) {
					var sResult = JSON.stringify(result);
					var pResult = JSON.parse(sResult);
	
					if(pResult.managementFM.dupliCount > 0) {
						alert('이미 등록된 판매사코드입니다.');
						$('#chk_sellerCd').val('0');
						$("[name='sellerCd']").eq(0).focus();
					} else {
						alert('사용가능한 판매사코드입니다.'); 
						$('#chk_sellerCd').val('1');
					}
					return;
				} else {
					alert("오류가 발생하였습니다.");
					return;
				}
			});
		});
		// 비밀번호 초기화 클릭시
		/* $("#btn_reset_pwd").on("click", function() {

			if ( confirm( "비밀번호 초기화를 하시겠습니까?" ) == false ) return;
			
			ajaxCallJsonPost("/shop/admin/seller/resetPwd.dojson", "fmList", function(result) {
				if(result) {
					alert("비밀번호가 초기화 되었습니다.");
					return;
				} else {
					alert("오류가 발생하였습니다.");
					return;
				}
			});
			
		}); */

		// 아이디 발급저장 클릭시
		$("#btn_save_id").on("click", function() {
			
			if($("[name='sellerNm']").eq(0).val() == "") {
				alert("판매사명은 필수 입력사항 입니다.");
				$("[name='sellerNm']").eq(0).focus();
				return;
			} else if($("[name='sellerCd']").eq(0).val() == "") {
				alert("판매사코드는 필수 입력사항 입니다.");
				$("[name='sellerCd']").eq(0).focus();
				return;			
			} else if($("[name='id']").eq(0).val() == "") {
				alert("판매사 아이디는 필수 입력사항 입니다.");
				$("[name='id']").eq(0).focus();
				return;
			} else if($("[name='sellerPw']").eq(0).val() == "") {
				alert("판매사 비밀번호는 필수 입력사항 입니다.");
				$("[name='sellerPw']").eq(0).focus();
				return;
			} else if($("[name='chk_sellerCd']").eq(0).val() == "" || $("[name='chk_sellerCd']").eq(0).val() == "0") {
				alert("판매사코드 중복확인을 하세요.");
				$("[name='sellerCd']").eq(0).focus();
				return;		
			} else if($("[name='chk_id']").eq(0).val() == "" || $("[name='chk_id']").eq(0).val() == "0") {
				alert("아이디 중복확인을 하세요.");
				$("[name='id']").eq(0).focus();
				return;
			
			} else {
				
				if ( confirm( "아이디 발급을 하시겠습니까?" ) == false ) return;
				
				var sellerNm = $("[name='sellerNm']").eq(0).val();
				var sellerCd = $("[name='sellerCd']").eq(0).val();
				var id       = $("[name='id']").eq(0).val();
				var status   = $("[name='status']").eq(0).val();
				
				$("#sellerCd").val(sellerCd);
				$("#sellerNm").val(sellerNm);
				$("#id").val(id);
				$("#status").val(status);
				$("#fmList").attr("action", "../seller/saveId");
				$("#fmList").attr("onsubmit", "");
				$("#fmList").submit();

			}
			
		});
		
		
		
	});
	
</script>

<style type="text/css">
	.test {
		background-color : aliceblue;
		color : maroon;
		font-family: arial;  
		font-weight: bold;
		border: 1px solid silver;
	}
	.aster {
		color : red;
		padding-left : 2px;
	}
	.btn {
	  display: inline-block;
	  padding: 6px 12px;
	  margin-bottom: 0;
	  font-size: 14px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
	      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
	     -moz-user-select: none;
	      -ms-user-select: none;
	          user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
	}
	.btn:hover {
	  -webkit-transition: 0.2s;
	  -moz-transition: 0.2s;
	  -o-transition: 0.2s;
	  -ms-transition: 0.2s;
	  transition: 0.2s;
	}
	.btn > .fa,
	.btn > .im {
	  margin: 0 5px;
	}
	.btn-xs {
	  font-size: 11px;
	  letter-spacing: 0;
	}
	.btn-primary {
	  color: #fff !important;
	  background: #486d97;
	  border-color: #416288;
	}
	.btn-primary:hover {
	  background: #416288;
	  border-color: #3a5779;
	}
	.btn-primary:focus {
	  background: #416288;
	  border-color: #3a5779;
	}
	.btn-primary-invert {
	  background: #b79268;
	  border-color: #ae8455;
	  color: #fff;
	}
	.form-control {
	  display: block;
	  width: 100%;
	  height: 34px;
	  padding: 6px 12px;
	  font-size: 14px;
	  line-height: 1.42857143;
	  color: #555;
	  background-color: #fff;
	  background-image: none;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	          box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
	       -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	          transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	}
	.form-control:focus {
	  border-color: #66afe9;
	  outline: 0;
	  -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, .6);
	          box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, .6);
	}
	.form-control::-moz-placeholder {
	  color: #999;
	  opacity: 1;
	}
	.form-control:-ms-input-placeholder {
	  color: #999;
	}
	.form-control::-webkit-input-placeholder {
	  color: #999;
	}
	.form-control[disabled],
	.form-control[readonly],
	fieldset[disabled] .form-control {
	  background-color: #eee;
	  opacity: 1;
	}
	.form-control[disabled],
	fieldset[disabled] .form-control {
	  cursor: not-allowed;
	}
	textarea.form-control {
	  height: auto;
	}
</style>


<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<div class="title title_top">판매사정보<span></span></div>

<form name=fmList id="fmList" method=post action="indb" enctype="multipart/form-data" onsubmit="return chkForm(this)" >

	<input type="hidden" name="mode" id="mode" value="${managementFM.mode }" />
	<input type="hidden" name="view" id="view" value="${managementFM.view }" />

	<%-- 등록화면 --%>
	<c:choose>
		<c:when test="${managementFM.mode == '' || managementFM.mode == null }">

	<div>
		<table class=tb border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<colgroup>
				<col class="cellC">
				<col class="cellL">
				<col class="cellL">
				<col class="cellC">
				<col class="cellL">
				<col class="cellL">
			</colgroup>
			<tbody>
			<tr>
				<th>판매사명<span class="aster">*</span></th>
				<td colspan=2><input type="text" style="width : 100%;" label="판매사명" name="sellerNm" required maxlength="25"/></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>판매사코드<span class="aster">*</span></th>
				<td style="border-right : 0px;"><input type="text" style="width : 70%;" label="판매사코드" name="sellerCd" required maxlength="18"/></td>
				<td style="border-left : 0px; text-align:right;">
					<input type=hidden name=chk_sellerCd id=chk_sellerCd label="아이디중복체크" required/>
					<input type="button" class="btn btn-primary" id="btn_chk_sellerCd" value="중복확인" />
				</td>
				<th>상태</th>
				<td>
					<span style="marigin-right:10px"></span>
					<!-- 상태(S:승인, R:승인요청, W:대기, X:탈퇴) -->
					<label for="statusS"><input type="radio" name="status" id="statusS" value="S" />승인</label>
					<label for="statusR"><input type="radio" name="status" id="statusR" value="R" />승인요청</label>
					<label for="statusW"><input type="radio" name="status" id="statusW" value="W" checked="checked"/>대기</label>
					<label for="statusX"><input type="radio" name="status" id="statusX" value="X" />탈퇴</label>
				</td>
			</tr>
			<tr>
				<th>판매사 아이디<span class="aster">*</span></th>
				<td style="border-right : 0px;"><input type="text" align="left" style="width : 70%;" label="판매사아이디" name="id" required maxlength="20" /></td>
				<td style="border-left : 0px; text-align:right;">
					<input type=hidden name=chk_id id=chk_id label="아이디중복체크" required/>
					<input type="button" class="btn btn-primary" id="btn_chk_id" value="중복확인" />
				</td>
				<th>비밀번호<span class="aster">*</span></th>
				<td>
					<!-- <input type="button" class="btn btn-primary" id="btn_reset_pwd" value="비밀번호 초기화" /> -->
					<input type="text" name="sellerPw" id="sellerPw" value="" maxlength ="20" required/>
				</td>
			</tr>
			</tbody>
		</table>
	
	</div>

	<div style="padding-top:10px"></div>
	
	<div style="text-align : right;">
		<input type="button" class="btn btn-primary" id="btn_save_id" value="아이디 발급 저장" />
	</div>
	
	<div style="padding-top:15px"></div>
	
	<div class="title title_top">판매사 기본정보<span></span></div>
	
	<div>
		<table class=tb border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<colgroup>
				<col class="cellC">
				<col class="cellL">
				<col class="cellC">
				<col class="cellL">
			</colgroup>
			<tbody>
			<tr>
				<th>대표자명<span class="aster">*</span></th>
				<td><input type="text" style="width : 70%;" label="대표자명" name="agentNm" required maxlength="50"/></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>사업자등록번호<span class="aster">*</span></th>
				<td colspan=3><input type="text" style="width : 10%;" label="사업자등록번호" name="companyRegNo" required maxlength="12"/> (예: 119-02-29983)</td>
			</tr>
			<tr>
				<th>업태<span class="aster">*</span></th>
				<td><input type="text" style="width : 20%;" label="업태" name="businessConditions" required maxlength="50"/></td>
				<th>종목<span class="aster">*</span></th>
				<td><input type="text" style="width : 20%;" label="종목" name="event" required maxlength="50"/></td>
			</tr>
			<tr>
				<th>결제계좌명의<span class="aster">*</span></th>
				<td colspan=3><input type="text" style="width : 10%;" label="결제계좌명의" name="accNm" required maxlength="10"/> </td>
			</tr>
			<tr>
				<th>결제은행<span class="aster">*</span></th>
				<td colspan=3>
					<select name="bankCd" style="width : 10%; ">
						<!-- KB:국민은행 WR:우리은행 SB:신한은행 HN:KEB하나은행 IB:기업은행 NH:농협 SH:수협 -->
						<option value="KB" selected="selected">KB국민은행</option>
						<option value="WR" >우리은행</option>
						<option value="SB" >신한은행</option>
						<option value="HN" >KEB하나은행</option>
						<option value="IB" >기업은행</option>
						<option value="NH" >NH농협</option>
						<option value="SH" >수협</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>은행계좌번호<span class="aster">*</span></th>
				<td colspan=3><input type="text" style="width : 14%;" label="은행계좌번호" name="accNo" required maxlength="15" onkeyup="removeChar(event)"/> "-" 없이 입력해주세요 (100010010000)</td>
			</tr>
			<tr>
				<th>담당자명<span class="aster">*</span></th>
				<td><input type="text" style="width : 20%;" label="담당자명" name="managerNm" required maxlength="50"/></td>
				<th>담당자 직위<span class="aster">*</span></th>
				<td><input type="text" style="width : 20%;" label="담당자 직위" name="managerPosition" required maxlength="10"/></td>		
			</tr>
			<tr>
				<th>전화번호<span class="aster">*</span></th>
				<td colspan=3><input type="text" style="width : 12%;" label="전화번호" name="managerTel" required maxlength="15" onkeyup="phoneNumber(event)"/> (예: 02-1234-5678)</td>
			</tr>
			<tr>
				<th>휴대폰번호</th>
				<td><input type="text" style="width : 22%;" name="managerHp" maxlength="15" onkeyup="phoneNumber(event)"/> (예: 010-1234-5678)</td>
				<th>팩스번호</th>
				<td><input type="text" style="width : 22%;" name="managerFax" maxlength="15" onkeyup="phoneNumber(event)"/> (예: 02-1234-5678)</td>
			</tr>
			<tr>
				<th>이메일<span class="aster">*</span></th>
				<td colspan=3><input type="text" style="width : 10%;" label="이메일" name="managerEmail" required maxlength="30"/> (예: abc@aaa.com)</td>
			</tr>
			<tr>
				<th>홈페이지</th>
				<td colspan=3>http://<input type="text" style="width : 10%;" name="homepage" maxlength="50"/> (예: www.stename.co.kr)</td>
			</tr>
			<tr>
				<th>주소<span class="aster">*</span></th>
				<td colspan=3>
		        	<div class="row">
		            	<p>
		                	<div class="col-md-3" style="padding-left:0px;">
						    	<input type="text" class="form-control" id="postcode" name="zipcode" readonly="readonly" placeholder="우편번호"  title="우편번호" label="우편번호" required />
						    	<a class="btn btn-primary" href="javascript:postcode();" role="button">우편번호 검색</a>
					    	</div>
		                </p>
		      			<p>
				      		<div class="col-md-6" style="padding-left:0px;">
				      			<input type="text" id="address" name="sellerAddr" readonly="readonly" class="form-control" placeholder="상세주소" title="상세주소" label="주소" required />
				      		</div>
				      		<div class="col-md-6" style="padding-left:0px;">
				      			<input type="text" id="address_sub" name="sellerAddrSub" class="form-control" placeholder="나머지주소" title="나머지 주소" label="세부주소" maxlength="100" />
				      		</div>
		      			</p>
		        	</div>
				</td>
			</tr>
			<tr>
				<th>기타사항</th>
				<td colspan=3>
		        	<textarea style="width : 90%; height : 120%;" name="etc" type="editor" maxlength="50"></textarea>
				</td>
			</tr>
			<tr>
				<th>관리자메모</th>
				<td colspan=3>
		        	<textarea style="width : 90%; height : 120%;" name="adminMemo" type="editor" maxlength="100"></textarea>
				</td>
			</tr>
			<tr>
				<th>정산주기<span class="aster">*</span></th>
				<td colspan=3>
					<select style="width : 10%;" name="settlementCycle">
						<!-- option value="D" selected="selected">일정산</option-->
						<option value="W" selected="selected">주정산</option>
						<option value="H">15일정산</option>
						<option value="M">월정산</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>수수료<span class="aster">*</span></th>
				<td colspan=3>
					<div class="row">
						<p>
							<!-- label for="feeG"><input type="radio" name="feeDiv" id="feeG" value="S" checked="checked" />카테고리당 수수료</label-->
							<label for="feeC"><input type="radio" name="feeDiv" id="feeC" value="X" checked="checked" />판매사당 수수료</label>
							<input type="text" style="width : 5%; text-align : right;" label="수수료" name="fees" value="0.00" required maxlength="7"/> %
						</p>
						
						<!-- p>
							<span class="aster">
								※ 공급업체당 수수료로 변경을 하거나 카테고리당 수수료로 변경을 하게되면 <br/>
								그 변경시점부터 등록된 상품들부터 수수료정책이 새롭게 적용됩니다.
							</span>
						</p-->
					</div>
				</td>
			</tr>
			</tbody>
		</table>
		
	</div>
	
	<div style="padding-top:15px"></div>
	
	<div style="text-align : center;">
	
		<a class="btn btn-primary" href="javascript:goBack();" role="button">판매자 신청목록</a>
		<input type=image src="/resources/shop/admin/img/btn_register.gif" class="submitImg" />
<!-- 		<a href="indb?mode=register"><span class="test">저장</span></a> -->
	
	</div>
	
	</c:when>
	
	<c:otherwise>
	
		<div>
			<table class=tb border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<colgroup>
					<col class="cellC">
					<col class="cellL">
					<col class="cellL">
					<col class="cellC">
					<col class="cellL">
					<col class="cellL">
				</colgroup>
				<tbody>
				<tr>
					<th>판매사명<span class="aster">*</span></th>
					<td colspan=2 style="width : 30%">
						${managementFM.managementVO.sellerNm}
						<input type="hidden" style="width : 100%;" name="sellerNm" required value="${managementFM.managementVO.sellerNm}" maxlength="25"/>
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>판매사코드<span class="aster">*</span></th>
					<td colspan=2>
						${managementFM.managementVO.sellerCd}
						<input type="hidden" style="width : 30%;" name="sellerCd" required value="${managementFM.managementVO.sellerCd}" maxlength="18"/>
					</td>
					<th>상태</th>
					<td>
						<span style="marigin-right:10px"></span>
						<!-- 상태(S:승인, R:승인요청, W:대기, X:탈퇴) -->
						<c:if test="${managementFM.managementVO.status eq 'S'}">
							<label for="statusS"><input type="radio" name="status" id="statusS" value="S" checked="checked"/>승인</label>
							<label for="statusR"><input type="radio" name="status" id="statusR" value="R" />승인요청</label>
							<label for="statusW"><input type="radio" name="status" id="statusW" value="W" />대기</label>
							<label for="statusX"><input type="radio" name="status" id="statusX" value="X" />탈퇴</label>
						</c:if>
						<c:if test="${managementFM.managementVO.status eq 'R'}">
							<label for="statusS"><input type="radio" name="status" id="statusS" value="S" />승인</label>
							<label for="statusR"><input type="radio" name="status" id="statusR" value="R" checked="checked"/>승인요청</label>
							<label for="statusW"><input type="radio" name="status" id="statusW" value="W" />대기</label>
							<label for="statusX"><input type="radio" name="status" id="statusX" value="X" />탈퇴</label>
						</c:if>
						<c:if test="${managementFM.managementVO.status eq 'W'}">
							<label for="statusS"><input type="radio" name="status" id="statusS" value="S" />승인</label>
							<label for="statusR"><input type="radio" name="status" id="statusR" value="R" />승인요청</label>
							<label for="statusW"><input type="radio" name="status" id="statusW" value="W" checked="checked"/>대기</label>
							<label for="statusX"><input type="radio" name="status" id="statusX" value="X" />탈퇴</label>
						</c:if>
						<c:if test="${managementFM.managementVO.status eq 'X'}">
							<label for="statusS"><input type="radio" name="status" id="statusS" value="S" />승인</label>
							<label for="statusR"><input type="radio" name="status" id="statusR" value="R" />승인요청</label>
							<label for="statusW"><input type="radio" name="status" id="statusW" value="W" />대기</label>
							<label for="statusX"><input type="radio" name="status" id="statusX" value="X" checked="checked"/>탈퇴</label>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>판매사 아이디<span class="aster">*</span></th>
					<td style="border-right : 0px;">
						${managementFM.managementVO.id}
						<input type="hidden" align="left" style="width : 70%;" name="id" required value="${managementFM.managementVO.id}"/>
					</td>
					<td style="border-left : 0px; text-align:right;">
<!-- 						<input type=hidden name=chk_id id=chk_id label="아이디중복체크" required/> -->
<!-- 						<a class="btn btn-primary" href="javascript:chkId()" role="button">중복확인</a> -->
					</td>
					<th>비밀번호<span class="aster">*</span></th>
					<td>
						<input type="text" name="sellerPw" id="sellerPw" value="" maxlength ="20"/>
						<!-- <input type="button" class="btn btn-primary" id="btn_reset_pwd" value="비밀번호 초기화" /> -->
<!-- 						<a class="btn btn-primary" href="javascript:resetPwd()" role="button">비밀번호 초기화</a> -->
					</td>
				</tr>
				</tbody>
			</table>
		
		</div>
	
		<div style="padding-top:10px"></div>
		
		<div style="text-align : right;">
<!-- 			<input type="button" class="btn btn-primary" id="btn_save_id" value="아이디 발급 저장" /> -->
		</div>
		
		<div style="padding-top:15px"></div>
		
		<div class="title title_top">판매사 기본정보<span></span></div>
		
		<div>
			<table class=tb border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<colgroup>
					<col class="cellC">
					<col class="cellL">
					<col class="cellC">
					<col class="cellL">
				</colgroup>
				<tbody>
				<tr>
					<th>대표자명<span class="aster">*</span></th>
					<td><input type="text" style="width : 70%;" label="대표자명" name="agentNm" required value="${managementFM.managementVO.agentNm}" maxlength="50"/></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>사업자등록번호<span class="aster">*</span></th>
					<td colspan=3>
						<input type="text" style="width : 10%;" label="사업자등록번호" name="companyRegNo" required value="${managementFM.managementVO.companyRegNo}" maxlength="12"/> (예: 119-02-29983)
					</td>
				</tr>
				<tr>
					<th>업태<span class="aster">*</span></th>
					<td>
						<input type="text" style="width : 20%;" label="업태" name="businessConditions" required value="${managementFM.managementVO.businessConditions}" maxlength="50"/>
					</td>
					<th>종목<span class="aster">*</span></th>
					<td>
						<input type="text" style="width : 20%;" label="종목" name="event" required value="${managementFM.managementVO.event}" maxlength="50"/>
					</td>		
				</tr>
				<tr>
					<th>결제계좌명의<span class="aster">*</span></th>
					<td colspan=3><input type="text" style="width : 10%;" label="결제계좌명의" name="accNm" required value="${managementFM.managementVO.accNm}" maxlength="10"/> </td>
				</tr>
				<tr>
					<th>결제은행<span class="aster">*</span></th>
					<td colspan=3>
						<select name="bankCd" style="width : 10%;" value="${managementFM.managementVO.bankCd}">
							<!-- KB:국민은행 WR:우리은행 SB:신한은행 HN:KEB하나은행 IB:기업은행 NH:농협 SH:수협 -->
							<c:if test="${managementFM.managementVO.bankCd == null || managementFM.managementVO.bankCd == ''}">
								<option value="KB" selected="selected">KB국민은행</option>
								<option value="WR" >우리은행</option>
								<option value="SB" >신한은행</option>
								<option value="HN" >KEB하나은행</option>
								<option value="IB" >기업은행</option>
								<option value="NH" >NH농협</option>
								<option value="SH" >수협</option>
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'KB'}">
								<option value="KB" selected="selected">KB국민은행</option>
								<option value="WR" >우리은행</option>
								<option value="SB" >신한은행</option>
								<option value="HN" >KEB하나은행</option>
								<option value="IB" >기업은행</option>
								<option value="NH" >NH농협</option>
								<option value="SH" >수협</option>
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'WR'}">
								<option value="KB" >KB국민은행</option>
								<option value="WR" selected="selected">우리은행</option>
								<option value="SB" >신한은행</option>
								<option value="HN" >KEB하나은행</option>
								<option value="IB" >기업은행</option>
								<option value="NH" >NH농협</option>
								<option value="SH" >수협</option>
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'SB'}">
								<option value="KB" >KB국민은행</option>
								<option value="WR" >우리은행</option>
								<option value="SB" selected="selected">신한은행</option>
								<option value="HN" >KEB하나은행</option>
								<option value="IB" >기업은행</option>
								<option value="NH" >NH농협</option>
								<option value="SH" >수협</option>
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'HN'}">
								<option value="KB" >KB국민은행</option>
								<option value="WR" >우리은행</option>
								<option value="SB" >신한은행</option>
								<option value="HN" selected="selected">KEB하나은행</option>
								<option value="IB" >기업은행</option>
								<option value="NH" >NH농협</option>
								<option value="SH" >수협</option>
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'IB'}">
								<option value="KB" >KB국민은행</option>
								<option value="WR" >우리은행</option>
								<option value="SB" >신한은행</option>
								<option value="HN" >KEB하나은행</option>
								<option value="IB" selected="selected">기업은행</option>
								<option value="NH" >NH농협</option>
								<option value="SH" >수협</option>
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'NH'}">
								<option value="KB" >KB국민은행</option>
								<option value="WR" >우리은행</option>
								<option value="SB" >신한은행</option>
								<option value="HN" >KEB하나은행</option>
								<option value="IB" >기업은행</option>
								<option value="NH" selected="selected">NH농협</option>
								<option value="SH" >수협</option>
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'SH'}">
								<option value="KB" >KB국민은행</option>
								<option value="WR" >우리은행</option>
								<option value="SB" >신한은행</option>
								<option value="HN" >KEB하나은행</option>
								<option value="IB" >기업은행</option>
								<option value="NH" >NH농협</option>
								<option value="SH" selected="selected">수협</option>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<th>은행계좌번호<span class="aster">*</span></th>
					<td colspan=3>
						<input type="text" style="width : 14%;" label="은행계좌번호" name="accNo" required value="${managementFM.managementVO.accNo}" maxlength="15"/> "-" 없이 입력해주세요 (100010010000)
					</td>
				</tr>
				<tr>
					<th>담당자명<span class="aster">*</span></th>
					<td>
						<input type="text" style="width : 20%;" label="담당자명" name="managerNm" required value="${managementFM.managementVO.managerNm}" maxlength="50"/>
					</td>
					<th>담당자 직위<span class="aster">*</span></th>
					<td>
						<input type="text" style="width : 20%;" label="담당자 직위" name="managerPosition" required value="${managementFM.managementVO.managerPosition}" maxlength="10"/>
					</td>		
				</tr>
				<tr>
					<th>전화번호<span class="aster">*</span></th>
					<td colspan=3>
						<input type="text" style="width : 12%;" label="전화번호" name="managerTel" required value="${managementFM.managementVO.managerTel}" maxlength="15"/> (예: 02-1234-5678)
					</td>
				</tr>
				<tr>
					<th>휴대폰번호</th>
					<td>
						<input type="text" style="width : 22%;" name="managerHp" value="${managementFM.managementVO.managerHp}" maxlength="15"/> (예: 010-1234-5678)
					</td>
					<th>팩스번호</th>
					<td>
						<input type="text" style="width : 22%;" name="managerFax" value="${managementFM.managementVO.managerFax}" maxlength="15"/> (예: 02-1234-5678)
					</td>
				</tr>
				<tr>
					<th>이메일<span class="aster">*</span></th>
					<td colspan=3>
						<input type="text" style="width : 10%;" label="이메일" name="managerEmail" required value="${managementFM.managementVO.managerEmail}" maxlength="30"/> (예: abc@aaa.com)
					</td>
				</tr>
				<tr>
					<th>홈페이지</th>
					<td colspan=3>
						http://<input type="text" style="width : 10%;" name="homepage" value="${managementFM.managementVO.homepage}" maxlength="50"/> (예: www.stename.co.kr)
					</td>
				</tr>
				<tr>
					<th>주소<span class="aster">*</span></th>
					<td colspan=3>
			        	<div class="row">
			            	<p>
			                	<div class="col-md-3" style="padding-left:0px;">
							    	<input type="text" class="form-control" id="postcode" name="zipcode" readonly="readonly" placeholder="우편번호"  
							    	       title="우편번호" label="우편번호"  value="${managementFM.managementVO.zipcode}" required/>
							    	<a class="btn btn-primary" href="javascript:postcode();" role="button">우편번호 검색</a>
						    	</div>
			                </p>
			      			<p>
					      		<div class="col-md-6" style="padding-left:0px;">
					      			<input type="text" id="address" name="sellerAddr" readonly="readonly" class="form-control" placeholder="상세주소" 
					      			title="상세주소" label="주소" value="${managementFM.managementVO.sellerAddr}" required />
					      		</div>
					      		<div class="col-md-6" style="padding-left:0px;">
					      			<input type="text" id="address_sub" name="sellerAddrSub" class="form-control" placeholder="나머지주소" 
					      			title="나머지 주소" label="세부주소" value="${managementFM.managementVO.sellerAddrSub}" required maxlength="100"/>
					      		</div>
			      			</p>
			        	</div>
					</td>
				</tr>
				<tr>
					<th>기타사항</th>
					<td colspan=3>
			        	<textarea style="width : 90%; height : 120%;" name="etc" type="editor" maxlength="50">${managementFM.managementVO.etc}</textarea>
					</td>
				</tr>
				<tr>
					<th>관리자메모</th>
					<td colspan=3>
			        	<textarea style="width : 90%; height : 120%;" name="adminMemo" type="editor" maxlength="100">${managementFM.managementVO.adminMemo}</textarea>
					</td>
				</tr>
				<tr>
					<th>정산주기<span class="aster">*</span></th>
					<td colspan=3>
						<select style="width : 10%;" name="settlementCycle">
							<c:if test="${managementFM.managementVO.settlementCycle == null || managementFM.managementVO.settlementCycle == ''}">
								<!-- option value="D" selected="selected">일정산</option-->
								<option value="W">주정산</option>
								<option value="H">15일정산</option>
								<option value="M">월정산</option>
							</c:if>
							<c:if test="${managementFM.managementVO.settlementCycle eq 'D'}">
								<!-- option value="D" selected="selected">일정산</option-->
								<option value="W">주정산</option>
								<option value="H">15일정산</option>
								<option value="M">월정산</option>
							</c:if>
							<c:if test="${managementFM.managementVO.settlementCycle eq 'W'}">
								<!-- option value="D">일정산</option-->
								<option value="W" selected="selected">주정산</option>
								<option value="H">15일정산</option>
								<option value="M">월정산</option>
							</c:if>
							<c:if test="${managementFM.managementVO.settlementCycle eq 'H'}">
								<!-- option value="D">일정산</option-->
								<option value="W">주정산</option>
								<option value="H" selected="selected">15일정산</option>
								<option value="M">월정산</option>
							</c:if>
							<c:if test="${managementFM.managementVO.settlementCycle eq 'M'}">
								<!-- option value="D">일정산</option-->
								<option value="W">주정산</option>
								<option value="H">15일정산</option>
								<option value="M" selected="selected">월정산</option>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<th>수수료<span class="aster">*</span></th>
					<td colspan=3>
						<div class="row">
							<p>
								<%--
								<c:if test="${managementFM.managementVO.feeDiv == null || managementFM.managementVO.feeDiv == '' }">
									<label for="feeG"><input type="radio" name="feeDiv" id="feeG" value="C" checked="checked" />카테고리당 수수료</label>
									<label for="feeC"><input type="radio" name="feeDiv" id="feeC" value="S"/>판매사당 수수료</label>								
								</c:if>
								<c:if test="${managementFM.managementVO.feeDiv eq 'C'}">
									<label for="feeG"><input type="radio" name="feeDiv" id="feeG" value="C" checked="checked" />카테고리당 수수료</label>
									<label for="feeC"><input type="radio" name="feeDiv" id="feeC" value="S"/>판매사당 수수료</label>
								</c:if>
								<c:if test="${managementFM.managementVO.feeDiv eq 'S'}">
									<label for="feeG"><input type="radio" name="feeDiv" id="feeG" value="C" />카테고리당 수수료</label>
									<label for="feeC"><input type="radio" name="feeDiv" id="feeC" value="S" checked="checked" />판매사당 수수료</label>								
								</c:if>
								--%>
								<label for="feeC"><input type="radio" name="feeDiv" id="feeC" value="S" checked="checked" />판매사당 수수료</label>
								<input type="text" style="width : 5%; text-align : right;" label="수수료" name="fees" required value="${managementFM.managementVO.fees}" maxlength="7"/> %
							</p>
							
							<!-- p>
								<span class="aster">
									※ 공급업체당 수수료로 변경을 하거나 카테고리당 수수료로 변경을 하게되면 <br/>
									그 변경시점부터 등록된 상품들부터 수수료정책이 새롭게 적용됩니다.
								</span>
							</p-->
						</div>
					</td>
				</tr>
				</tbody>
			</table>
			
		</div>
		
		<div style="padding-top:15px"></div>
		
		<div style="text-align : center;">
		
			<a class="btn btn-primary" href="javascript:goBack();" role="button" id="listName" style="display:none;">판매자 신청목록</a>
			<a class="btn btn-primary" href="javascript:goBack();" role="button" id="confirmListName" style="display:none;">판매자 목록</a>
			<a class="btn btn-primary" href="javascript:goSave();" role="button">수정</a>
<%-- 			<input type=image src="../img/btn_modify.gif" class="submitImg" onclick="javascript:goModify('${managementFM.managementVO.sellerCd}'); return false;" /> --%>
<!-- 			<input type=image src="../img/btn_modify.gif" class="submitImg" /> -->
		
		</div>
	
	</c:otherwise>
	
	</c:choose>
	
	<div style="padding-top:15px"></div>

</form>

<script>
linecss();
table_design_load();
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>