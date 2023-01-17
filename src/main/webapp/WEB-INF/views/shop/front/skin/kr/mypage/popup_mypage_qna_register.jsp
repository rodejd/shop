<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>

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
	<link rel="stylesheet" type="text/css" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/jquery-ui.css" media="all" />
    <link href='http://fonts.googleapis.com/css?family=Roboto:500,300,700,400italic,400' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/bootstrap.css">
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/font-awesome.css">
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/styles.css">
    <link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/mystyles.css">
    
    <script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/common.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.aw-showcase.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.ui-selectBox.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/datepicker-ko.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"></script>

<script type="text/javascript">
	//<![CDATA[
		var NJ = $.noConflict(true);
	//]]>
</script>
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/masonry.pkgd.js"></script>

<!--[if lt IE 9]>
<![endif]-->

<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/mypage/mypage_qna_register_popup.js"></script>
<script type="text/javascript">

if( '${result}' == 1 ){
	window.opener.location.reload();
	window.close();
}

function orderLoad(pageNo){
	var data = "pageNo=" + pageNo;
	ajaxCallGet('/shop/mypage/ajaxOrderList?'+data, 'list');
}

function order_put(ordno){
	$('#ordno').val(ordno);
	var data = "ordno=" + ordno;
	/* $.magnificPopup.close(); */ //클릭시 닫는걸 하지않게 막았다
	ajaxCallGet('/shop/mypage/ajaxOrderGoddsList?'+data, 'list');
}

function orderGoods_put(sellerCd){
	$('#sellerCd').val(sellerCd);
	$.magnificPopup.close();
}

/* function searchOrderno(page_no){
	
	if(!page_no) page_no = 1;
	
	$.ajax({
	   type: "post",
	   url : "orderPopup.dojson",
	   data : {'pageNo' : page_no},
	   dataType : 'json',
	   success: function(data){
		   var html = '';
		   
		   if(data.orderList.length > 0){
			   
			   for(var i=0;i<data.orderList.length;i++){
				   
				   var result = data.orderList[i];
				   
				   html += '<article class="product-review" >';
				   html += '     <div class="product-review-author"><input type="radio" name="ord" onclick="order_put('+result.ordno+')"/></div>';
				   html += '     <div>';
				   html += '         <h5 class="product-review-title">';
				   html += 			result.ordno + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   html += 			result.goodsnm + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   html += '         </h5>';
				   html += '			<p class="product-review-meta" style="margin-left:30px;">'
				   html += '				수량 : '+result.ea+'개&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   html += '				주문금액 : '+result.settleprice+'원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   html += '				주문일 : ' +result.orddt 
               	   html += '			</p>';
				   html += '     </div>';
				   html += ' </article>';
				   
			   }
			   
		   }else{
			   
			   html += '<article class="product-review" onclick="view_content(${status.index});">';
			   html += '     <div>';
		   	   html += '         <h5 class="product-review-title" style="text-align:center;">';
	   		   html += '         주문하신 주문번호가 존재하지 않습니다.';
	   		   html += '         </h5>';
	   		   html += '     </div>';
	   		   html += ' </article>';
	   		   
		   }
		   $('#list').html(html);
		   // 페이징
		   $('#paging').html(data.qna_page_list);
	   },
	   error: function(request,status,error){
		   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	   }
	});
	
} */

function goPage(page){
	$('#pageNo').val(page);
	$('.frmList').submit();
}

</script>
</head>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<body>
	<br/>	
	<div class="x-popup-mypage-qna-register container">
		<div class="tabbable product-tabs">
		
			<div class="tb_wrap_n01">
				<div class="tab-content nb">
					<div class="tab-pane fade in active">
						<div class="row">
							<form name=fm method=post action="indb" onSubmit="return chkForm(this)">
							<input type=hidden name=mode value="${ myBoardVO.mode }<%//= mode %>">
							<%-- <input type=text name=itemcd value="<%= itemcd %>"> --%>
							<input type=hidden name=sno value="${ myBoardVO.sno }<%//= sno %>">
							<input type=hidden name=mailling value="${ myBoardVO.qnaObj.mailling }">
							<input type=hidden name=sms value="${ myBoardVO.qnaObj.sms }">

							<div class="sub-tit-wrap">
								<h3 class="sub-title">
									<span>1:1문의게시판</span>
								</h3>
							</div>

<style>
@media only screen and (min-width : 721px) {
.tb_wrap_n01 {padding:0 30px;}

.tb_mem_modify th {font-weight:600 !important; font-size:16px !important; color:#313131 !important; line-height:57px !important; padding:0 !important; border-top:none !important; text-align:left;}
.tb_mem_modify td {font-size:16px !important; line-height:16px !important; padding:6px 0 !important; vertical-align:middle !important; border-top:none !important;}
.tb_mem_modify td input.inp_ty01 {width:210px; height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td input.inp_ty02 {width:134px; height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td input.inp_ty03 {width:80px; height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td input.inp_ty04 {width:280px; height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td textarea {width:380px; height:144px; padding:10px; border:1px solid #dbdbdb;}
.tb_mem_modify td select {height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb; vertical-align:top;}
.tb_mem_modify td select.sel_ty01 {width:210px; height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td select.sel_ty02 {width:134px; height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb;}

.tb_mem_modify .bx_th {width:140px;}
.tb_mem_modify th {}
.tb_mem_modify td {}
.tb_mem_modify td input.ck_my_ty01 {}
.tb_mem_modify td label {}
.tb_mem_modify td .pr_ty01 {float:left; padding-right:16px;}
.tb_mem_modify td .tx_tb01 {font-size:15px; color:#272727; line-height:15px; padding:18px 0 0 0; margin:0;}
.tb_mem_modify .tx01 {font-size:14px; margin-left:10px; color:#333; line-height:44px;}
.tb_mem_modify .tx02 {font-size:14px; color:#333; line-height:44px;}

.btn_pop_ask {width: 230px; height: 39px; font-size: 16px; color: #fff; line-height: 39px; padding: 0; background: #2d2d2d; border: 0; border-radius: 0; text-align: center;}

input.ck_ty01 {width:16px; height:16px; margin:13px 4px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_off.png') no-repeat left top; background-size:16px 16px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_ty01:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_on.png') no-repeat left top; background-size:16px 16px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}

.product-tabs .tab-content {border:none;}
}

@media only screen and (max-width : 721px) {
.tb_wrap_n01 {padding:0;}

.tb_mem_modify th {font-weight:600 !important; font-size:13px !important; color:#313131 !important; line-height:47px !important; padding:0 !important; border-top:none !important; text-align:left;}
.tb_mem_modify td {font-size:13px !important; line-height:13px !important; padding:6px 0 !important; vertical-align:middle !important; border-top:none !important;}
.tb_mem_modify td input.inp_ty01 {width:100%; height:34px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td input.inp_ty02 {width:120px; height:34px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td input.inp_ty03 {width:80px; height:43px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td input.inp_ty04 {width:100%; height:34px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td textarea {width:100%; height:84px; padding:10px; border:1px solid #dbdbdb;}
.tb_mem_modify td select {height:44px; padding:0 0 0 10px; border:1px solid #dbdbdb; vertical-align:top;}
.tb_mem_modify td select.sel_ty01 {width:100%; height:34px; padding:0 0 0 10px; border:1px solid #dbdbdb;}
.tb_mem_modify td select.sel_ty02 {width:100%; height:34px; padding:0 0 0 10px; border:1px solid #dbdbdb;}

.tb_mem_modify .bx_th {width:70px;}
.tb_mem_modify th {}
.tb_mem_modify td {}
.tb_mem_modify td input.ck_my_ty01 {}
.tb_mem_modify td label {}
.tb_mem_modify td .pr_ty01 {float:left; padding-right:16px;}
.tb_mem_modify td .tx_tb01 {font-size:15px; color:#272727; line-height:15px; padding:18px 0 0 0; margin:0;}
.tb_mem_modify .tx01 {font-size:14px; margin-left:10px; color:#333; line-height:44px;}
.tb_mem_modify .tx02 {font-size:14px; color:#333; line-height:44px;}

.btn_pop_ask {width: 230px; height: 39px; font-size: 16px; color: #fff; line-height: 39px; padding: 0; background: #2d2d2d; border: 0; border-radius: 0; text-align: center;}

input.ck_ty01 {width:16px; height:16px; margin:13px 4px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_off.png') no-repeat left top; background-size:16px 16px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_ty01:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_on.png') no-repeat left top; background-size:16px 16px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}

.product-tabs .tab-content {border:none;}

.ly_p_ty01 {margin:10px;}

.widget-title, .widget-title-sm, .widget-title-lg {font-size:18px;}
.ly_p_ty01 .product-review {padding-left:25px;}
.ly_p_ty01 .product-review-title {font-size:12px;}
.ly_p_ty01 .product-review-meta {font-size:11px; letter-spacing:-0.5;}
.mfp-dialog {padding:20px 10px;}
}
</style>
							<div class="gap"></div>
							<table class="table table tb_mem_modify">
								<colgroup> 
									<col class="bx_th">
									<col style="width:*;">
								</colgroup>
				               <tbody>
				               		<tr>
				                        <th>아이디</th>
				                        <td>
		                                    <input class="inp_ty01" type="text" name="name" maxlength="20" title="이름" required label="이름" value="${myBoardVO.mid}" <c:if test="${not empty myBoardVO.mid}">readonly </c:if> />
				                        </td>
				                    </tr>
				                    <c:if test="${myBoardVO.mode ne 'reply_qna' }">
				                    <tr>
				                        <th>질문유형</th>
				                        <td>
		                                    <select name="itemcd"   title="질문유형" label="질문유형" required class="sel_ty01">
												<option value="">상담내용을 선택하세요.</option>
												${webUtil:makeSelectCodeItem((codeUtil:codeitem('boardtype')), myBoardVO.qnaObj.itemcd) }
											</select>
				                        </td>
				                    </tr>
				                    <tr>
										<th>주문번호</th>
										<td>
											<input type=text  id="ordno" name=ordno value="${myBoardVO.qnaObj.ordno != null ? myBoardVO.qnaObj.ordno : '' }" class="inp_ty02" >
											<input type=hidden id="sellerCd" name="sellerCd" value="${myBoardVO.qnaObj.sellerCd}"/>
											<!-- <a href="#nav-login-dialog" class="btn btn-default popup-text" onclick="searchOrderno();">주문조회</a> -->
											<a href="#nav-login-dialog" data-effect="mfp-move-from-top" class="popup-text tx01" onclick="orderLoad('1');">주문조회</a>
										</td>
									</tr>
				                    </c:if>
				                    <tr>
				                        <th>제목</th>
				                        <td>
				                        	<div class="row">
					                            <div class="col-md-12">
				                                    <input class="inp_ty04" type="text" name="subject"  maxlength="100" required label="제목" maxlength="100" title="제목" class="form-control"  value="${myBoardVO.qnaObj.subject }" />
					                            </div>
					                        </div>
				                        </td>
				                    </tr>
				                    <tr>
				                        <th>내용</th>
				                        <td><textarea name="contents"  maxlength="8000" required label="문의내용"  title="문의내용" class="form-control" rows="5" style="resize:none">${myBoardVO.qnaObj.contents }</textarea></td>
				                    </tr>
				               </tbody>
			              	</table>
							<div style="padding:20px 0 0 0; text-align:center;">
								<input class="btn_pop_ask" type="submit" value="문의하기" />
							</div>
			              	</form>
			             </div>
			         </div>
		         </div>
		     </div>
		 </div>
	</div>
	
	<div class="pop_onon">
		<div class="mfp-with-anim mfp-hide mfp-dialog clearfix ly_p_ty01" id="nav-login-dialog">
			 <h3 class="widget-title">문의하실 주문번호를 선택하세요.</h3>
			 <hr />
			 <form id="list" class="list">
			 <input type="hidden" name="pageNo" id="pageNo" value="1" />
			 </form>
			 <nav class="text-center">
				<ul class="pagination category-pagination" id="paging">
					<%-- <tags:paginator currentPageNo="${myBoardVO.pageNo}" rowCount="${myBoardVO.rowCount}" pageSize="${myBoardVO.pageSize}"  pageGroupSize="${myBoardVO.pageGroupSize}" /> --%>
				</ul>
			 </nav>
<style>
.ly_p_ty01 {max-width:100%; margin:40px;}
.ly_p_ty01 hr {margin-bottom:15px;}
.ly_p_ty01 .product-review {position:relative; padding:3px 0 15px 30px; margin-bottom:15px; border-bottom:1px solid #eee;}
.ly_p_ty01 .product-review-author {position:absolute; top:0; left:0;}
.ly_p_ty01 .product-review-title {padding:0; margin:0; font-size:13px; line-height:18px;}
.ly_p_ty01 p {padding:0; margin:0;}
.ly_p_ty01 .product-review-meta {font-size:13px; color:#777; padding:8px 0 0 0; margin-left:0 !important;}
</style>
		 </div>
		</div>
     
    <script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/jquery.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/bootstrap.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/icheck.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/ionrangeslider.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/jqzoom.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/card-payment.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/owl-carousel.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/magnific.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL) }/js/custom.js"></script>
	
</body>
</html>    