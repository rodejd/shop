<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
 
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>

<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/join_input.js"></script>

<input class="js_message" type="hidden" value="${message }">

<script>
	function isEmpty(s) {
	    if(typeof s == 'string')
	        s = $.trim(s);
	
	    if(s == undefined || s == null || s == '' || s.length == 0)
	        return true;
	
	    return false;
	}

	var message = $('.js_message').val();
	if(!isEmpty(message)) {
		alert(message);
		location.href = ctx+ '/shop/main/index';
	}
	
</script>


<header class="page-header page-header-banner x-member-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">회원가입</h1>
        </div>
    </div>
</header>

<form class="js_form" name=frmMember id="frmMember" method=post action="${ctx}/shop/member/joinIndb" enctype="multipart/form-data" onsubmit="return chkForm(this)" >
	<div class="x-join-input container sub">
		<section class="panel join-panel">
			<div class="panel-body nb">
				<input type=hidden name=mode value="joinMember"  />
				<table class="table join_table">
				<colgroup> 
					<col class="col_mobile_th">
					<col class="col_mobile_td">
				</colgroup>
	               <tbody>
	  				<c:if test="${empty sns_id}">
	               		<tr>
	                        <th><span class="required">* </span>아이디    </th>
	                        <td>
	                        	<div class="row">
	                        	  	<input type="text" name="mid" id=m_id value="" maxlength="20" placeholder="아이디" class="js_joinID form-control" required option=regId label="아이디" title="아이디" data-reg="/^[A-Za-z]+[A-Za-z0-9+]*$/"  />
								    <input type=hidden name=chk_id id=chk_id label="아이디중복체크" required/> 
									<input type="hidden" name = "sns_id" value="">
						      		<a class="btn btn-primary" href="javascript:chkId()" role="button">중복확인</a>
	                        		<p class="form-control-static">&nbsp;※ 숫자로 시작하지 않는 영문/숫자 조합</p>
	                        	</div>
	                        	
							</td>
	                    </tr>
	                    
	                    <tr>
	                    	<th></th>
		                    <td>
		                    	<div class="row">
		                    		<ul class="product-page-option-list">
		                    			<li>① 최소 8자 ~ 최대 20자 이내로 입력합니다.</li>
		                    			<li>② 반드시 영문, 숫자, 특수문자가 각 1자리 이상 포함되어야 합니다.</li>
		                    			<li>③ 특수문자 중 <, >, (, ), #, ', /, | 는 사용할수 없습니다.</li>
		                    			<li>④ 3자리 이상 연속되는 숫자 또는 문자열은 사용할 수 없습니다. (예:123, 321, 012, abc, cba)</li>
		                    			<li>⑤ 3자리 이상 동일한 숫자 또는 문자열은 사용할 수 없습니다. (예:000, 111, 222, ,aaa, bbb)</li>
		                    			<li>⑥ 아래와 같은 문자는 사용할 수 없습니다. (love, happy, qwer, asdf, zxcv, test, gpin, ipin)</li>
		                    			<li>⑦ 아이디와 연속한 3자리 이상 일치하는 비밀번호는 사용할 수 없습니다.</li>
		                    		</ul>
		                    	</div>
		                    </td>
						</tr>
	                    
	                     <tr>
	                        <th><span class="required">* </span>비밀번호 입력</th>
	                        <td>
	                        	<div class="row">
	                        		<div class="">
	                        			<input type=password id=password name=password  onkeyup="regPassword();" class="form-control js_join_pw" placeholder="비밀번호" required option=regPass label="비밀번호" title="비밀번호"/>
	                        		</div>
	                        		<div class="form-control-static">
	                        			※ 상단의 비밀번호 생성규칙을 참고하세요.
	                        		</div>
	                        	</div>
							</td>
	                    </tr>
	                    
	                    <tr>
	                        <th><span class="required">* </span>비밀번호 확인</th>
	                        <td>
	                        	<div class="row">
	                        		<div class="">
	                        			<input type=password name=password2 onkeyup="regPassword();" class="form-control js_join_pw_re" placeholder="비밀번호 확인" required option=regPass label="비밀번호" title="비밀번호 확인" />
	                        		</div>
	                        		<div class="form-control-static">
	                        			<span id="msg" class="required"></span>
	                        		</div>
	                        	</div>
							</td>
	                    </tr>
	                 </c:if>
	                     <tr>
	                        <th><span class="required">* </span>이름</th>
	                        <td>
	                        	<div class="row">
	                        		<div>
	                        			<input type=text name=name id=name class="form-control js_join_name" placeholder="이름" required label="이름" title="이름" maxlength="20"  data-reg="/^[가-힣a-zA-Z]+$/"/>
	                        		</div>
	                        	</div>
							</td>
	                    </tr>
	                    <c:if test="${infoVO.nicknameUse eq 'checked'}">
		                    <tr>
		                        <th>
		                        	<c:if test="${infoVO.nicknameReq eq 'checked'}">
		                        		<span class="required">* </span>
	                        		</c:if>
	                        		<c:if test="${infoVO.nicknameReq ne 'checked'}">
	                        			<span>&nbsp; </span>
	                        		</c:if>닉네임
                        		</th>
		                        <td>
		                        	<div class="row">
									    <input type="text" class="form-control js_join_nick" id="nickname" name="nickname" placeholder="닉네임"  label="닉네임" title="닉네임"  maxlength="20" <c:if test="${infoVO.nicknameReq eq 'checked'}">required</c:if> />
									    <input type="hidden" name="nicknameUse" id="nicknameUse" value="${infoVO.nicknameUse eq 'checked' ? (infoVO.nicknameReq eq 'checked' ? '2' : '1') : '' }">
									    <input type="hidden" name="chk_nickname" id="chk_nickname" > 
									    <a class="btn btn-primary" href="javascript:chkNicknameJoin()" role="button">닉네임 중복확인</a>
		                        	</div>
								</td>
		                    </tr>
		                    <tr>
		                    	<th>프로필 이미지</th>
		                    	<td>
		                    		<div class="row">
		                    			<div class="col-md-6 form-control-static">
		                    				<input type="file" id="profile" name="mpUpload" title="파일찾기"  accept=".gif, .jpg, .png, .jpge, .bmp" />
		                    			</div>
		                    		</div>
		                    	</td>
		                    </tr>
	                    </c:if>
	                    <%--
	                    	20160322 김경훈 - 현재 관리자페이지에서 성별사용여부를 관리하지 않고 있어서 필수 사용으로 하드코딩
	                    <c:if test="${sex_use eq 'checked'}">
	                     --%>
	                    <tr>
	                    	<th>
	                    	<%--
	                    		<c:if test="${sex_req eq 'checked'}"><span style="color:red;">* </span></c:if><c:if test="${sex_req ne 'checked'}"><span>&nbsp; </span></c:if>성별
	                    	 --%>
	                    	 <span class="required">* </span>성별
	                    	</th>
	                    	<td>
	                    		<div class="row form-control-static">
	                    			<input type=radio id="sexm" name=sex value="m" label="성별" title="성별"  checked="checked" required />
		                            <label for="sexm" >남자</label>
									<input type=radio id="sexw" name=sex value="f" label="성별" title="성별"  required />
									<label for="sexw" >여자</label>
	                    		</div>
	                    	</td>
	                    </tr>
	                    <%--
	                    </c:if>
	                     --%>
	                    
	                    <c:if test="${infoVO.birthUse eq 'checked'}">
	                    <tr>
	                        <th><c:if test="${infoVO.birthReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.birthReq ne 'checked'}"><span>&nbsp; </span></c:if>생년월일</th>
	                        <td>
	                        	<div class="row">
	                        		<div class="col-xs-4">
	                                    <select id=""  name="birthyear"  class="js_birthYear form-control" style="height:34px;" title="년도 선택" label="생년월일" <c:if test="${infoVO.birthReq eq 'checked'}">required</c:if> >
											<option value="" selected="selected">년도를 선택하세요.</option>
											<c:forEach var="i" begin="1920" end="2016">
												<option value="${i }">${i }</option>
											</c:forEach>
										</select>
		                            </div>
		                            <div class="col-xs-4">
	                                    <select id=""  name="birthMonth" class="js_birthMonth form-control" style="height:34px;" title="월 선택" label="생년월일" <c:if test="${infoVO.birthReq eq 'checked'}">required</c:if> >
											<option value="" selected="selected">월을 선택하세요.</option>
											<c:forEach var="i" begin="1" end="12">
												<option value="${i }">${i }</option>
											</c:forEach>
										</select>
		                            </div>
		                            <%--  join_input.js 페이지에서 년, 월 입력시 날짜 맞추어 세팅되게끔 설정. --%>
		                            <div class="col-xs-4">
	                                    <select id=""  name="birthDate"  class="js_birthDate form-control" style="height:34px;" title="월 선택" label="생년월일" <c:if test="${infoVO.birthReq eq 'checked'}">required</c:if> >
											<option value="" selected="selected">날짜를 선택하세요.</option>
										</select>
		                            </div>
		                            
	                        	</div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    
	                    <c:if test="${infoVO.phoneUse eq 'checked'}">
	                	<tr>
	                        <th><c:if test="${infoVO.phoneReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.phoneReq ne 'checked'}"><span>&nbsp; </span></c:if>전화번호</th>
	                        <td>
	                        	<div class="row">
	                        		<div class="col-sm-3 col-xs-4">
	                           			<input type="text" name="phone" id="phone1" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" onblur="removeChar(event)" class="form-control form-number" placeholder="앞자리" label="전화번호 앞자리" title="휴대폰 앞자리"  <c:if test="${infoVO.phoneReq eq 'checked'}">required</c:if> maxlength="3"/>
	                           		</div>
									<div class="col-sm-3 col-xs-4">
	                           			<input type="text" name="phone" id="phone2" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" onblur="removeChar(event)" class="form-control form-number" placeholder="중간자리" label="전화번호 중간자리" title="휴대폰 중간자리" <c:if test="${infoVO.phoneReq eq 'checked'}">required</c:if> maxlength="4"/>
	                           		</div>
	                           		<div class="col-sm-3 col-xs-4"> 
										<input type="text" name="phone" id="phone3" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" onblur="removeChar(event)" class="form-control form-number" placeholder="끝자리" label="전화번호 끝자리" title="휴대폰 끝자리" <c:if test="${infoVO.phoneReq eq 'checked'}">required</c:if> maxlength="4" />
									</div>
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	               		
	               		
	               		<c:if test="${infoVO.mobileUse eq 'checked'}">
	                	<tr>
	                        <th><c:if test="${infoVO.mobileReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.mobileReq ne 'checked'}"><span>&nbsp; </span></c:if>휴대폰번호</th>
	                        <td>
	                        	<div class="row">
	                        		<div class="col-sm-3 col-xs-4">
	                                    <select id="mobile0" name="mobile"  class="form-control" style="height:34px;" title="휴대폰 첫자리" label="휴대폰 첫자리" <c:if test="${infoVO.mobileReq eq 'checked'}">required</c:if> >
	                                    	<option value="" >선택하세요.</option>
											<option value="010">010</option>
											<option value="011">011</option>
											<option value="016">016</option>
											<option value="017">017</option>
											<option value="018">018</option>
											<option value="019">019</option>
											<option value="0130">0130</option>
										</select>
									</div>
									<div class="col-sm-3 col-xs-4">
	                           			<input type="text" id="mobile1" name="mobile"  onkeydown="onlynumber(event)" onkeyup="removeChar(event)" onblur="removeChar(event)" class="form-control form-number" placeholder="중간자리" label="휴대폰 중간자리" title="휴대폰 중간자리" <c:if test="${infoVO.mobileReq eq 'checked'}">required</c:if> maxlength="4"/>
	                           		</div>
	                           		<div class="col-sm-3 col-xs-4"> 
										<input type="text" id="mobile2" name="mobile"  onkeydown="onlynumber(event)" onkeyup="removeChar(event)" onblur="removeChar(event)" class="form-control form-number" placeholder="끝자리" label="휴대폰 끝자리" title="휴대폰 끝자리" <c:if test="${infoVO.mobileReq eq 'checked'}">required</c:if> maxlength="4" />
									</div>
									
									<c:if test="${infoVO.smsUse eq 'checked' }">
										<div class="col-md-3 form-control-static">
											<input type="checkbox" id="sms" name="sms" title="문자서비스수신" label="문자서비스수신" <c:if test="${infoVO.smsReq eq 'checked'}">required</c:if>  checked="checked" />
											<label for="sms">문자서비스수신</label>
										</div>
									</c:if>
									
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    
	                    
	                    <c:if test="${infoVO.emailUse eq 'checked'}">
	                    <tr>
	                        <th><c:if test="${infoVO.emailReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.emailReq ne 'checked'}"><span>&nbsp; </span></c:if>이메일</th>
	                        <td>
	                        	<div class="row">
	                        		<div class="col-md-3">
	                                   	<input type="text"  name="email1" placeholder="이메일 아이디" class="form-control" title="이메일 아이디" <c:if test="${infoVO.emailReq eq 'checked'}">required</c:if> data-reg="/^[A-Za-z]+[A-Za-z0-9+]*$/"  />
	                                    <input type="hidden" name="chk_email" id="chk_email" >
	                                    <input type="hidden" name="emailUse" id="emailUse" value="${infoVO.emailUse eq 'checked' ? (infoVO.emailReq eq 'checked' ? '2' : '1') : '' }">
	                                   </div>  
	                                   <div class="col-md-1 form-control-static at_sign">
	                                   	@
	                                   </div>
		                            <div class="col-md-3">
	                            		<input type="text" name="email2"  placeholder="도메인" class="form-control" title="도메인" <c:if test="${infoVO.emailReq eq 'checked'}">required</c:if> />
	                            	</div>
	                            	<div class="col-md-3">
		                                <select id="mailsel" name=""  onchange="javascript:mailSelect();" class="form-control" style="height:34px;" title="입력방식" >
											<option value="" selected="selected">직접입력</option>
											<option value="gmail.com">gmail.com</option>
											<option value="dreamwiz.com">dreamwiz.com</option>
											<option value="freechal.com">freechal.com</option>
											<option value="hanmail.net">hanmail.net</option>
											<option value="hanmir.com">hanmir.com</option>
											<option value="hotmail.com">hotmail.com</option>
											<option value="korea.com">korea.com</option>
											<option value="nate.com">nate.com</option>
											<option value="naver.com">naver.com</option>
											<option value="paran.com">paran.com</option>
										</select>
									</div>
	                                <c:if test="${infoVO.maillingUse eq 'checked' }">
	                               	<div class="col-md-2 form-control-static">
										<input type="checkbox" id="mailling" name="mailling" title="정보메일수신" label="정보메일수신" <c:if test="${infoVO.maillingReq eq 'checked' }">required</c:if> checked="checked"  />
										<label for="mailling">정보메일수신</label>
									</div>
									</c:if>
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    
	                    <c:if test="${infoVO.addressUse eq 'checked'}">
	                    <tr>
	                        <th><c:if test="${infoVO.addressReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.addressReq ne 'checked'}"><span>&nbsp; </span></c:if>주소</th>
	                        <td>
	                        	<div class="row">
	                        		<p>
		                        		<div>
										    <input type="text" class="form-control js_join_post" id="postcode" name="zipcode" readonly="readonly" placeholder="우편번호"  title="우편번호" label="우편번호"  <c:if test="${infoVO.addressReq eq 'checked'}">required</c:if> />
										    <a class="btn btn-primary" href="javascript:postcode();" role="button">우편번호 검색</a>
									    </div>
							      		
	                        		</p>
						      		<p>
								      	<div class="col-md-6">
								      		<input type="text" id="address" name="address" readonly="readonly" id="yoon_address" class="form-control" placeholder="상세주소" title="상세주소" label="주소" <c:if test="${infoVO.addressReq eq 'checked'}">required</c:if> />
								      	</div>
								      	<div class="col-md-6">
								      		<input type="text" id="address_sub" name="addresssub" class="form-control" placeholder="나머지주소" title="나머지 주소" label="세부주소" <c:if test="${infoVO.addressReq eq 'checked'}">required</c:if> />
								      	</div>
						      		</p>
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    
	                    <c:if test="${infoVO.marriynUse eq 'checked'}">
	                    <tr>
	                    	<th><c:if test="${infoVO.marriynReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.marriynReq ne 'checked'}"><span>&nbsp; </span></c:if>결혼여부</th>
	                    	<td>
	                    		<div class="row form-control-static">
		                            <input type=radio id="marrin" name=marriyn value="n" label="결혼여부" title="결혼여부" <c:if test="${infoVO.marriynReq eq 'checked'}">required</c:if> checked="checked"  />
		                            <label for="marrin" >미혼</label>
									<input type=radio id="marriy" name=marriyn value="y" label="결혼여부" title="결혼여부"  <c:if test="${infoVO.marriynReq eq 'checked'}">required</c:if> />
									<label for="marriy" >기혼</label>
	                    		</div>
	                    	</td>
	                    </tr>
	                    </c:if>
	                    
	                    <c:if test="${infoVO.marridateUse eq 'checked'}">
	                    <tr>
	                        <th><c:if test="${infoVO.marridateReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.marridateReq ne 'checked'}"><span>&nbsp; </span></c:if>결혼기념일</th>
	                        <td>
	                        
	                        	<div class="row">
	                        		<div class="col-xs-4">
	                                   <select id="marry_year"  name="marridate"  class="form-control" style="height:34px;" title="년도 선택" label="결혼기념일" <c:if test="${marridate_req eq 'checked'}">required</c:if> >
										<option value="" selected="selected" >년도를 선택하세요.</option>                                                                                           
										<c:forEach var="i" begin="1920" end="2016">
											<option value="${i }">${i }</option>
										</c:forEach>
									</select>
									</div>
									<div class="col-xs-4">
	                                   <select id="marry_month"  name="marridate" class="form-control" style="height:34px;"  title="월 선택" label="결혼기념일" <c:if test="${marridate_req eq 'checked'}">required</c:if> >
										<option value="" selected="selected">월을 선택하세요.</option>
										<c:forEach var="i" begin="1" end="12">
											<option value="${i }">${i }</option>
										</c:forEach>
									</select>
									</div>
									<div class="col-xs-4">
	                                   <select id="marry_date"  name="marridate" class="form-control" style="height:34px;" title="일 선택" label="결혼기념일" <c:if test="${infoVO.marridateReq eq 'checked'}">required</c:if> >
										<option value="" selected="selected">날짜를 선택하세요.</option>
										<c:forEach var="i" begin="1" end="31">
											<option value="${i }">${i }</option>
										</c:forEach>
									</select>
		                            </div>
	                        	</div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    
	                    <c:if test="${infoVO.jobUse eq 'checked'}">
	                    <tr>
	                    	<th><c:if test="${infoVO.jobReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.jobReq ne 'checked'}"><span>&nbsp; </span></c:if>직업</th>
	                    	<td>
	                    		<div class="row">
	                    			<div class="col-md-3">
	                    				 <select id="" name="job"  required class="form-control" style="height:34px;" title="직업" label="직업"  >
	                    				 	${infoVO.jobSelect }
	                    				 </select>
	                    			</div>
	                    		</div>
	                    	</td>
	                    </tr>
	                    </c:if>
	                    
						<c:if test="${infoVO.recommidUse eq 'checked'}">
	                    <tr>
	                    	<th><c:if test="${infoVO.recommidReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.jobReq ne 'checked'}"><span>&nbsp; </span></c:if>추천인</th>
	                    	<td>
	                    		<div class="row">
	                    			<div>
	                    				<input type="text" name="recommid" id="recommid" value="" maxlength="20" placeholder="추천인" class="form-control js_join_recom" label="추천인" title="추천인" data-reg="/^[A-Za-z]+[A-Za-z0-9+]*$/"  />
	                    				<input type="hidden" name="recommidUse" id="recommidUse" value="${infoVO.recommidUse eq 'checked' ? (infoVO.recommidReq eq 'checked' ? '2' : '1')  : '' }">	                    				
	                    				<input type="hidden" name="checkRecommid" id="checkRecommid" >
	                    				<a class="btn btn-primary" id="checkRecommidButton" role="button">아이디확인</a>
	                    			</div>
	                    		</div>
	                    	</td>
	                    </tr>
	                    </c:if>
	                    
	                    <c:if test="${infoVO.interestUse eq 'checked'}">
							<tr>
								<th>
									<c:if test="${infoVO.interestReq eq 'checked'}">
										<span class="required">* </span>
									</c:if>
									<c:if test="${infoVO.interestReq ne 'checked'}">
										<span>&nbsp; </span>
									</c:if>관심분야
								</th>
								<td>
									<div class="row js_interestReq" data-interest-req="${infoVO.interestReq}">
										<c:if test="${infoVO.interestUse eq 'checked'}">
											<c:forEach items="${infoVO.codeList}" var="interest" varStatus="status">
												<c:if test="${status.index % 4 == 0 }">
			                    					<p>
			                    				</c:if>											
												<input type="checkbox" name="interest" value="${interest.val}" >
												${interest.itemnm}&nbsp;&nbsp;&nbsp;
	
												<c:if test="${status.index % 4 == 3 }">
													</p>
												</c:if> 
											</c:forEach>
										</c:if>
									</div>
								</td>
							</tr>
	                    </c:if>
	                    
	                    <c:if test="${infoVO.memoUse eq 'checked'}">
							<tr>
								<th><c:if test="${infoVO.memoReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${infoVO.memoReq ne 'checked'}"><span>&nbsp; </span></c:if>남기는말씀</th>
								<td>
									<div class="row">
										<textarea name="memo" class="form-control" rows="5" label="남기는말씀" title="남기는말씀" style="resize: none;" <c:if test="${infoVO.memoReq eq 'checked'}">required</c:if>></textarea>
									</div>
								</td>
							</tr>
	                    </c:if>

	                    
						<tr>
							<td colspan="2">
								<div class="gap gap-small"></div>
							</td>
						</tr>
	                    <tr>
	                    	<td colspan="2" class="text-center">
	                    	  <c:if test="${!empty sns_id}">
	                    		<input class="js_submitBtn btn btn-primary active" type="button" id="join_btn"  value="회원가입" onclick="submitJoinSns();"/>
	                    	  </c:if>
	                    	  
	                    	   <c:if test="${empty sns_id}">
	                    		<input class="js_submitBtn btn btn-primary" type="button" id="join_btn"  value="회원가입" onclick="submitJoin();"/>
	                    	  </c:if>
	                    	  
	                    	  	<input class="btn btn-primary" type="button" id="pre_btn"  value="이전으로"  onclick="history.back();"/>
	                    	  
	                    	</td>
	                    </tr>
	                    
	                </tbody>
	            </table>
	            
			</div>
			
		</section>	
	</div>
</form>