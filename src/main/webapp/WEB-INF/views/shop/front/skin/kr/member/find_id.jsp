<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<header class="page-header page-header-banner x-member-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h1 class="page-title">ID찾기</h1>
		</div>
	</div>
</header>

<c:choose>
	<c:when test="${memberVO.act != 'Y'}">

		<form method=post name=fm action="" onsubmit="return chkForm( this );" id=form>
			<input type="hidden" name="act" value="Y">

			<div class="x-find-id container sub">
				<div class="tabbable product-tabs">
					<!-- 고객센터 공통탭메뉴 처리 -->
					<jsp:include page="../service/service_tab_menu.jsp" flush="true">
						<jsp:param name="tab_order" value="4" />
					</jsp:include>


					<div class="col-md-10">
						<div class="tab-content nb">
							<div class="tab-pane fade in active">
								<div class="row">
									<!-- <div class="col-md-12"> -->
									<div>
										<div class="form-group form-group-cc-name">
											<label>이름</label>
											<div>
												<input type="text" name="srchName" class="form-control" placeholder="이름을 입력하세요." maxlength="20" required />
											</div>
										</div>
										<c:if test="${memberVO.resnoChk == 'checked'}">
											<div class="form-group form-group-cc-name">
												<label>주민등록번호</label>
												<div class="input-group">
													<span class="input-group-addon"><span class="fa fa-user"></span></span>
													<span>
														<input type="text" name="srchNum1" maxlength=6 class="form-control" onkeyup="if (this.value.length==6) this.nextSibling.nextSibling.focus()" onkeydown="onlynumber()" size="50" required/>- 
														<input type="password" name="srchNum2" maxlength=7 class="form-control" onkeydown="onlynumber()" size="50" required/>
													</span>
												</div>
											</div>
										</c:if>

										<c:if test="${memberVO.emailChk == 'checked'}">
											<div class="form-group form-group-cc-name">
												
												<label>가입 메일주소</label>
												<div>
													<input type="text" name="srchMail" class="form-control" size="29" placeholder="이메일을 입력하세요." maxlength="50" required />
												</div>
											</div>
										</c:if>
									</div>
									
									<div class="gap gap-small"></div>

									<div>
										<input class="btn btn-primary btn-block active" type="submit" onsubmit="formSubmit();" value="찾기" />
									</div>


									<div class="gap gap-small"></div>
									
									<div class="text-right">
										<a href="../member/find_pwd" class="btn btn-primary">비밀번호찾기</a>&nbsp;
										<a href="../member/login" class="btn btn-primary">로그인하기</a>
									</div>
									
									<!-- </div> -->
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

		</form>
	</c:when>
	<c:when test="${memberVO.act == 'Y' && memberVO.m_id == ''}">
		<div class="container sub">
			<div class="tabbable product-tabs">
				<!-- 고객센터 공통탭메뉴 처리 -->
				<jsp:include page="../service/service_tab_menu.jsp" flush="true">
					<jsp:param name="tab_order" value="4" />
				</jsp:include>

				<div class="col-md-10">
					<div class="tab-content nb">
						<div class="tab-pane fade in active">
							<div class="row">
								<div class="sub-tit-wrap">
									<h3 class="sub-title">
										<span>ID찾기 결과</span>
									</h3>
								</div>
								<div class="gap gap-small"></div>
								<div class="id alert alert-danger" role="alert">
        							<strong>${memberVO.srchName}</strong> 님의 아이디는 존재하지 않습니다.<br>입력정보가 정확한지 확인 후 다시 한번 시도해주세요.
      							</div>
      							<div class="text-right">
      								<a href="find_id" class="btn btn-primary">재검색하기</a>
      							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</c:when>

	<c:when test="${memberVO.act == 'Y' && memberVO.m_id != ''}">
		<div class="container sub">
			<div class="tabbable product-tabs">
				<!-- 고객센터 공통탭메뉴 처리 -->
				<jsp:include page="../service/service_tab_menu.jsp" flush="true">
					<jsp:param name="tab_order" value="4" />
				</jsp:include>

				<div class="col-md-10">
					<div class="tab-content nb">
						<div class="tab-pane fade in active">
							<div class="row">
								<div class="sub-tit-wrap">
									<h3 class="sub-title">
										<span>ID찾기 결과</span>
									</h3>
								</div>
								<div class="gap gap-small"></div>
								<%-- <p class="checkout-login-text"><font color="808080">&quot;${srch_name}회원님의 아이디는 <font color="#FF791F">${m_id}</font>입니다.&quot;</font></p> --%>
								<div class="id alert alert-success" role="alert">
        						&quot;<strong>${memberVO.srchName}</strong> 회원님의 아이디는 
        						<c:forEach items="${memberVO.idList}" var="id" varStatus="status">
        							[<strong>${id}</strong>]
      							</c:forEach>
      							입니다.&quot;<br>
      							</div>
      							
							</div>
							<div class="text-right">
								<a href="../member/login" class="btn btn-primary active">로그인하기</a>&nbsp;<a href="../member/find_pwd" class="btn btn-primary">비밀번호찾기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:when>

</c:choose>