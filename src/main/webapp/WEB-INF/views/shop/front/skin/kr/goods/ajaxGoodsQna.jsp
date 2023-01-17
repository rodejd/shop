<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : goods_view.jsp
* 생성일 : 2017. 02. 17
* 작성자 : 이병환
* 설   명 : skin default1 사용자 상품 상세 조회
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170217			이병환				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/magnific.js"></script>
<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/custom.js"></script>

<script type="text/javascript">
$(function(){
	$('.qnaCon').on('click', function(){
		var sno=$(this).attr("id");
		if($("#test_"+sno).css("display")=="block"){
			$("#test_"+sno).hide();
		}else{	
			$("#test_"+sno).show();
		};
	});
	
	/* 질문 등록 */
	$('#btn_qna_write').on("click",function(){
		var blank_pattern = /^\s+|\s+$/g;
			 
		if(!$("#qna_subject").val()){
			alert("제목을 입력해주세요");
			$('#qna_subject').focus();
			return false;
		}
		 
		if($('#qna_subject').val().replace( blank_pattern, '' ) == ""){
			alert('제목을 공백만 입력하셨습니다 ');
			$('#qna_subject').focus();
			return false;
		}
		 
		if(!$("#qna_contents").val()){
			alert("내용을 입력해주세요");
			$('#qna_contents').focus();
			return false;
		}
		 
		if($('#qna_contents').val().replace( blank_pattern, '' ) == ""){
			alert('내용을 공백만 입력하셨습니다 ');
			$('#qna_contents').focus();
			return false;
		}
		 
		if( 2000 <= $("#qna_contents").val().length ){
			alert("2000자 이하로 입력해주세요");
			return false;	 
		}
		 
		qnaIns('add_qna','');
		return;
	 });
	
	/* 질문 수정 */ 
	$('#btn_qna_modify').on("click",function(){
		var blank_pattern = /^\s+|\s+$/g;
		
		if(!$("#qna_fix_subject").val()){
			alert("제목을 입력해주세요");
			$('#qna_fix_subject').focus();
			return false;
		}
		 
		if($('#qna_fix_subject').val().replace( blank_pattern, '' ) == ""){
			alert('제목을 공백만 입력하셨습니다 ');
			$('#qna_fix_subject').focus();
			return false;
		}
		 
		if(!$("#qna_fix_contents").val()){
			alert("내용을 입력해주세요");
			$('#qna_fix_contents').focus();
			return false;
		}
		 
		if($('#qna_fix_contents').val().replace( blank_pattern, '' ) == ""){
			alert('내용을 공백만 입력하셨습니다 ');
			$('#qna_fix_contents').focus();
			return false;
		}
		 
		if( 2000 <= $("#qna_fix_contents").val().length ){
			alert("2000자 이하로 입력해주세요");
			return false;	 
		}
		qnaIns('mod_qna','');
	});
	
	/* 질문 삭제 */
	$('.btn_qna_delete').on("click",function(){
		var sno = $(this).attr("data-sno");
		qnaIns('del_qna',sno);
	});
});

<%-- 페이징 처리 --%>
function goPage(page){
	//$('#pageNo').val(page);
	qnaLoad(page,$("#goodsno").val());
}

function qnaIns(actmode,num){
	var mode = actmode;
	var page_no = "${frontGoodsQnAVO.pageNo}";
	
	if(mode=="add_qna"){
		var goodsno = '${frontGoodsQnAVO.goodsno}';
		var subject = $("#qna_subject").val();
		var contents = $("#qna_contents").val();
		var sno = num;
	}
	
	if(mode=="mod_qna"){
		var goodsno = '${frontGoodsQnAVO.goodsno}';
		var subject = $("#qna_fix_subject").val();
		var contents = $("#qna_fix_contents").val();
		var sno = $("#fix_sno").val();
	}
	
	if(mode=="del_qna"){
		if (confirm("정말 삭제하시겠습니까??") == true){
			var goodsno = $("#goodsno").val();
			var sno=num;
		}else{ 
			return;
		}
	}
	if(mode=="reply_qna"){
		var goodsno = $("#goodsno").val();
		var subject = $("#dsubject").val();
		var contents = $("#dcontents").val();
		var sno = $("#sno").val();
	}
	
	var data = {
		"mode"		: mode,           
		"goodsno"	: goodsno,    
		"contents"	: contents,  
		"subject"		: subject,         
		"sno"			: sno             
	};
	
	ajaxCallJson("/shop/goods/ajaxGoodsQnaWrite.doJson", data, function(data){
		alert("정상적으로 처리되었습니다"); 
		qnaLoad(page_no,goodsno);
	});
	$.magnificPopup.close();
}

//팝업 modal open
$(document).ready(function(){
	// Lighbox text
	$('.popup-text-another').magnificPopup({
		removalDelay: 500,
		closeBtnInside: true,
		callbacks: {
			beforeOpen: function() {
				this.st.mainClass = this.st.el.attr('data-effect');
			}
		},
		midClick: true,
		modal: true
	});
});
</script>

<form action="indb.jsp" method="post" id="fm01">
	<input type="hidden" name="sno" id="sno" value="">
	<input type="hidden" name="mode" id="mode" value="">
	<input type="hidden" name="goodsno" value="${frontGoodsQnAVO.goodsno}">
	<input type="hidden" name="eno" id="eno" value="">
	<input type="hidden" name="modCk" id="modCk" value="">
	<input type="hidden" name="pageNo" value="${frontGoodsQnAVO.pageNo}">

	<body style="margin-top:10px"><!-- onLoad="resizeFrame()" -->

	<!-- <div> <font size="5">질문 답변</font></div> -->
	<hr/>
	<c:if test="${not empty frontGoodsQnAVO.goodsQnAList }">
		<c:forEach var="rList" items="${frontGoodsQnAVO.goodsQnAList }" varStatus="status" >
			<!-- /************************************************
			*	해당글에 대한 답변,수정,삭제 버튼 노출&비노출 처리로직
			*************************************************/
			<!-- 해당글이 부모글일 경우에만 답변버튼 노출처리 -->
			<c:choose>
				<c:when test="${(rList.sno eq rList.parent) && (level eq '99')}">
					<c:set var="isAnswer_display" value="true"/>
				</c:when>
				<c:otherwise>
					<c:set var="isAnswer_display" value="false"/>
				</c:otherwise>
			</c:choose>
		
			<c:if test="${rList.mno eq shop_so.userInfo.m_no or level>=99}">
				<div width="100%" style="padding-left: 17">
					<div style="text-align: left;">
						<div class="" style="text-align: right;">
							<a href="#nav-dialog4" data-effect="mfp-move-from-top" class="popup-text"
								onclick="$('#mode').val('mod_qna');$('#fix_sno').val('${rList.sno}');$('#qna_fix_subject').val('${rList.subject}');$('#qna_fix_contents').val('${rList.contents}');">
								<input type="button" class="btn btn-lg btn-primary" value="수정">
							</a> 
							<a href="javascript:void(0);" data-effect="mfp-move-from-top" class="btn_qna_delete"
								data-sno="${rList.sno}"> <input type="button"
								class="btn btn-lg btn-primary" value="삭제">
							</a>
						</div>
					</div>
				</div>
			</c:if>
			
			<article class="product-review${rList.sno ne rList.parent ? ' product-review-reply' : ''}"${rList.sno ne rList.parent ? ' style="background-color:#F8FFFF;"' : ''}>
		        <div class="product-review-author">
		        	<div class="product-page-qa-${rList.sno ne rList.parent ? 'answer' : 'question'}">
		    		</div>
				</div>
				<div>
	                <h5 class="product-review-title">
	                <c:if test="${rList.sno eq rList.parent }">
						<span class="qna-titles"><a id="${rList.sno}" class="qnaCon" style="cursor:pointer">${rList.subject}</a></span>
					</c:if>
					<c:if test="${rList.sno ne rList.parent }">
							<!-- &nbsp;&nbsp;&nbsp;&nbsp; --><a herf="#" id="${rList.sno}" class="qnaCon" style="cursor:pointer">${rList.subject}</a>
					</c:if>
					</h5>
					<br>
					<div id="test_${rList.sno}" style="display:none;padding:10;" class="testall">
						<div width="100%" style="padding-left:17">
							<div style="text-align:left;">
								<font class="qna-body" size="3">${rList.contents}&nbsp;</font> 
								<div class="" style="text-align:right;">		
									<c:if test="${isAnswer_display }">
									<a href="#nav-dialog3" data-effect="mfp-move-from-top" class="popup-text" onclick="$('#mode').val('reply_qna');$('#sno').val('${rList.sno}');">
										<input type="button" class="btn btn-lg btn-primary" value="답변">
									</a>
									</c:if>
									<%-- 수정 할 것 20170223_이병환
									<c:if test="${rList.isDelete_yn }">
										<input type="button" class="btn btn-lg btn-primary" value="삭제" onclick="qnaIns( 'del_qna','${rList.sno}' );">
									</c:if>
									 --%>
								</div>
							</div>
						</div>
					</div>
						
              			<ul class="list-inline product-review-actions" style="margin-left:50px;margin-top:20px;">
                            <li><i class="fa fa-user"></i> 
                             <c:choose>
								<c:when test="${not empty rList.mname }">
									${rList.mname }
								</c:when>
								<c:otherwise>
									${rList.name }
								</c:otherwise>
							 </c:choose>
                            </li>
                            <li><i class="fa fa-clock-o"></i> ${dateUtil:formatDate(rList.regdt, "yyyy-MM-dd")}
                            </li>
                       </ul>
	            </div>
	        </article>	
		</c:forEach>
	</c:if>
	<c:if test="${empty frontGoodsQnAVO.goodsQnAList }">
		<div align='center'><b>등록된 상품문의가 없습니다.</b></div>
	</c:if>
	
<div style="float:right;padding:10px 5px">
<%-- $('#hpoint').val('${rList.point}');$('#hcontents').val('${rList.contents}');$('#hsno').val('${rList.sno}'); --%>
<c:if test="${ shop_so.isShopLogin() }">
	<a href="#nav-dialog2" data-effect="mfp-move-from-top" class="popup-text-another">
		<input type="button" class="btn btn-lg btn-primary" value="질문작성">
	</a>
</c:if>
</div>
				<div class="mfp-with-anim mfp-hide mfp-dialog clearfix" id="nav-dialog2">
			        <div class="form-group">
			            <div class="question-wrap">
							<label>질문제목</label>
							<input type="text" class="form-control" id="qna_subject" name="qna_subject">
							<input type="hidden" name="qna_sno" id="qna_sno">
				        </div>
				        <div class="content-wrap">
				        	<label>내용</label>
				          	<!-- <input type="text" class="form-control" id="hcontents" name="hcontents"> -->
				          	<textarea class="form-control" id="qna_contents" name="qna_contents" rows="5" style="resize:none"></textarea>
						</div>
			        </div>
			        	<input class="btn btn-primary" id="btn_qna_write" type="button" value="확인" />
			        <div class="gap gap-small"></div>
			        <button title="Close (Esc)" type="button" class="mfp-close" style="color:#333">×</button>
				</div>
		
				<div class="mfp-with-anim mfp-hide mfp-dialog clearfix"
					id="nav-dialog4">
					<div class="form-group">
						<div class="question-wrap">
							<label>수정하기</label> <input type="text" class="form-control"
								id="qna_fix_subject" name="qna_fix_subject" > <input
								type="hidden" name="fix_sno" id="fix_sno">
						</div>
						<div class="content-wrap">
							<label>내용</label>
							<!-- <input type="text" class="form-control" id="qna_fix_contents" name="qna_fix_contents"> -->
							<textarea class="form-control" id="qna_fix_contents"
								name="qna_fix_contents" rows="5" style="resize: none"></textarea>
						</div>
					</div>
					<input class="btn btn-primary" id="btn_qna_modify" type="button"
						value="수정" />
					<div class="gap gap-small"></div>
					<button title="Close (Esc)" type="button" class="mfp-close"
						style="color: #333">×</button>
				</div>
		
		
				<div class="mfp-with-anim mfp-hide mfp-dialog clearfix" id="nav-dialog3">
			        <div class="form-group">
			            <label>답변제목</label>
				          <input type="text" class="form-control" id="dsubject" name="dsubject">
				          		<input type="hidden" name="dsno" id="hsno">
				        <label>내용</label>
				          <!-- <input type="text" class="form-control" id="dcontents" name="dcontents"> -->
				          <textarea class="form-control" id="dcontents" name="dcontents" rows="5">
						  </textarea>
			        </div>
			        	<input class="btn btn-primary" id="" type="button" onclick="qnaIns('reply_qna','')" value="확인" />
			        <div class="gap gap-small"></div>
			    </div>
			    
				<nav class="text-center">
					<ul class="pagination category-pagination">
						<tags:frontPaginator currentPageNo="${frontGoodsQnAVO.pageNo}" rowCount="${frontGoodsQnAVO.rowCount}" pageSize="${frontGoodsQnAVO.pageSize}"  pageGroupSize="${frontGoodsQnAVO.pageGroupSize}" />
					</ul>
				</nav>
				
				
</body>

</form>

