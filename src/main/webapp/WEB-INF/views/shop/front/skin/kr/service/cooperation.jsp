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
<script type="text/javascript">
function mailSelect(){
	var email3 = $("#mailsel").val();
	$("input[name=email2]").val(email3);
}

function chkForm2(obj){
	if (chkForm(obj)){
			obj.mail.value = obj.email1.value +"@"+ obj.email2.value;
		return true;
	}else {
		return false;
	}
}

function fm_save(){
	

	if ( $('#name').val() == "" ){
		alert( "이름을 입력하지 않았습니다.\n이름을 입력하세요!!" );
		$('#name').focus() ;
		return;
	}

	if ( $('#email1').val() == ""){
		alert( "메일 아이디를 입력하지 않았습니다.\n메일을 입력하세요!!" );
		$('#email1').focus() ;
		return;
	}
	
	if ( $('#email2').val() == ""){
		alert( "메일 도메인를 입력하지 않았습니다.\n메일을 입력하세요!!" );
		$('#email2').focus() ;
		return;
	}
	
	$("#email").val($('#email1').val()+'@'+$('#email2').val());

	if ( $('#itemcd').val() == "" ){
		alert( "문의분야를 선택하지 않았습니다.\n문의분야를 선택하세요!!" );
		$('#itemcd').focus() ;
		return;
	}

	if ( $('#title').val() == ""){
		alert( "제목을 입력하지 않았습니다.\n제목을 입력하세요!!" );
		$('#title').focus() ;
		return;
	}

	if ( $('#content').val() == ""){
		alert( "내용을 입력하지 않았습니다.\n내용을 입력하세요!!" );
		$('#title').focus() ;
		return;
	}

	//$('#form01').submit();
	
	ajaxCallJsonPost("/shop/service/cooperation.dojson", "form01", function(result){
		//alert(JSON.stringify(result));
		alert('문의하신 내용이 전송되었습니다.');
		location.href=ctx+"/shop/service/cooperation";
	});
}
</script>

<header class="page-header page-header-banner x-service-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h1 class="page-title">광고/제휴 문의</h1>
		</div>
	</div>
</header>

<form name="form01" id="form01" method=post>

	<div class="x-cooperation container">
		<div class="tabbable product-tabs">


			<div class="col-md-12">
				<div class="tab-content nb">
					<div class="tab-pane fade in active">
						<div class="row">
						<!-- <div class="alert alert-success" role="alert">사업 및 제휴 문의가 있는 경우 메일을 보내주세요. 담당자가 확인 후 답변 드리도록 하겠습니다.</div> -->
							<div class="title-cooperation">
								<i class="zmdi zmdi-quote"></i>
								<div class="quote">
									<h4>사업 및 제휴 문의가 있는 경우 메일을 보내주세요. 담당자가 확인 후 답변 드리도록 하겠습니다.</h4>
								</div>
							</div>

							<div>
								<div class="form-group form-cooperation-01">
									<label>이름</label>
									<div class="input-group">
										<input type="text" name="name" id="name" class="form-control" placeholder="이름을 입력하세요." value="${cooperationVO.name}" ${not empty cooperationVO.name?'readonly':''} required />
									</div>
								</div>

								<div class="form-group form-cooperation-02">
									<label>E-mail 주소</label>
									<div class="input-group input-email">
										<input type="text" name="email1" id="email1" class="form-control" size="10" value="${fn:split(cooperationVO.email,'@')[0]}" placeholder="" required /> 
										<span class="input-group-addon" id="sizing-addon3">@</span> 
										<input type="text" name="email2" id="email2" class="form-control" size="10" value="${fn:split(cooperationVO.email,'@')[1]}" placeholder="" required />
										<input type="hidden" name="email" id="email" value="" />
										<select id="mailsel" name="" title="입력방식" onchange="javascript:mailSelect();" class="product-page-option-select" style="height:36px;">
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
								</div>

								<!-- <div class="form-group form-cooperation-01">
 
									<div class="input-group">
										
									</div>
								</div> -->

								<div class="form-group form-cooperation-01">

									<label>분야</label>
									<div class="input-group">
										<select id="itemcd" name="itemcd" class="product-page-option-select" required>
											<option value="">문의분야</option>
											<%-- <%=WebUtil.makeSelectOption(CodeUtil.getCodeList("cooperation"), "itemcd", "itemnm", "")%> --%>
											${webUtil:makeSelectCodeItem((codeUtil:codeitem("cooperation")), "") }
										</select>
									</div>
								</div>

								<div class="form-group form-cooperation-02">
									<label>제목</label>
									<div class="input-group">
										<input type="text" name="title" id="title" class="form-control" placeholder="이름을 입력하세요." maxlength="100" required />
									</div>
								</div>

								<div class="form-group form-cooperation-02">
									<label>내용</label>
									<div class="input-group txtbox02">
										<textarea name="content" id="content" class="form-control" placeholder="내용을 입력하세요." maxlength="8000" rows="5"></textarea>
									</div>
								</div>


								<div>
									<input class="btn btn-primary btn-block btn-propose" type="btn" value="문의하기" onclick="fm_save();" />
								</div>

								<div class="gap gap-small"></div>
							</div>
						</div>
					</div>
				</div>


			</div>

		</div>
	</div>

</form>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
