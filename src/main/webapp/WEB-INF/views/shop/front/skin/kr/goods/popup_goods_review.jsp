<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.form.min.js"></script>
<script type="text/javascript">
var rbyte = 0;
$(function(){
	// 별핸들링
	$(".ul_review > li > a").on("click", function(){
		var num = $(this).data("num");
		
		$(".ul_review > li > a").html("☆");
		$(".ul_review > li > a").removeClass("on");
		for( var i=0; i < parseInt(num); i++){
			$(".ul_review > li:eq("+i+") > a").html("★");
			$(".ul_review > li:eq("+i+") > a").addClass("on");
		}
	});
	
	// 파일업로드
	$(".reviewFile").on("change", function(){
		var obj = $(this);
		if( $(obj).val() == "" ){
			return;
		}
		
		var ext = $(obj).val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg', 'bmp']) == -1) {
			alert('gif,png,jpg,jpeg,bmp 파일만 업로드 할수 있습니다.');
			$(obj).val("");
			return;
		}
		
		$("#reviewForm").ajaxForm({
			type: "post",
			enctype: 'multipart/form-data',
			url: "/shop/goods/popup_goods_review_file.doJson",
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			success : function(rst){
				if(rst.result ){
					var divTag = $(obj).parent();
					$(divTag).parent("li.fileupload").css({"background-image": "url("+rst.img+")", "background-size": "100% 100%"});
					$(divTag).find("[name=fnoArr]").val(rst.fno);
					$(divTag).find("[type=file]").val('');
					$(divTag).find(".btn_add_p").html("")
				}else{
					alert("오류가 발생했습니다.");
					$(divTag).find("[type=file]").val('');
				}
			},
			error : function(){
				alert("오류가 발생했습니다.");
			}
		});
		$("#reviewForm").submit();
	});
	
	//저장
	$("#btn_review").on("click", function(){
		if($(".ul_review > li a.on").length == 0){
			alert("별점을 등록해주세요.");
			return;
		}
		
		if($.trim($("#review_contents").val()) == ""){
			alert("내용을 입력해주세요.");
			return;
		}
		
// 		if(rbyte < 1000){
// 			alert("내용은 1000byte이상 입력해주세요.");
// 			return;
// 		}
		
		var fnoArr = new Array();
		$("[name=fnoArr]").each(function(){
			if( this.value != "" ){
				fnoArr.push(String(this.value));
			}
		});
		
		var data = {
						"mode"		:'add_review',
						"goodsno"	: $("#goodsno").val(),
						"ordno"		: $("#ordno").val(),
						"contents"	: $("#review_contents").val(),  
						"point"		: $(".ul_review > li a.on").length,
						"fnoArr"	: fnoArr,
						"fno"		: 0,
					};
		$.ajaxSettings.traditional = true;
		$.ajax({
			data  : data,
			dataType : 'json',
			type : 'POST',
			enctype: 'multipart/form-data',
			url : '/shop/goods/ajaxGoodsReviewWrite.doJson',
			success : function (rst) {
				console.log(rst);
				if( rst.result > 0 ){
					alert("리뷰가 등록되었습니다.");
					reviewLoad(1, $("#goodsno").val());
					var offset =$("#goodsReview").offset();
					$(window).scrollTop(offset.top);
				}else{
					alert("리뷰등록중 오류가 발생했습니다.\n다시 시도해주세요.");
				}
				$(".pop_good_review").css("display", "none");
			}
		});
	});
});	
	
// Byte 수 체크 제한
function fnChkByte(obj){
	rbyte = 0;
	var str = obj.value;
	var str_len = str.length;
	var rlen = 0;
	var one_char = "";
	var str2 = "";

	for (var i = 0; i < str_len; i++) {
		one_char = str.charAt(i);
		if (escape(one_char).length > 4) {
			rbyte += 2; //한글2Byte
		} else {
			rbyte++; //영문 등 나머지 1Byte
		}
		if (rbyte <= 2000) {
			rlen = i + 1; //return할 문자열 갯수
		}
	}
	if (rbyte > 2000) {
		alert("내용은 2000byte를 초과할 수 없습니다.")
		str2 = str.substr(0, rlen); //문자열 자르기
		obj.value = str2;
		fnChkByte(obj, maxByte);
	}
}
</script>

<form id="reviewForm" method="post" enctype="multipart/form-data">
	<div class="bx_bg_op"></div>
	<div class="bx_in_pop">
		<div class="in_bx">
			<div class="tit_bx">
				리뷰 작성하기
				<a href="javascript:void(0);" class="btn_pop_close_n">X</a>
			</div>
			<div class="cont_bx">
				<div class="tp_info">
					<ul>
						<li>
							<div class="bx_thum"><img src="${reviewInfo.imgs}" onerror="this.src='/resources/shop/data/skin/kr/img/common/noimg_500.gif';"></div>
							<dl>
								<dt>${reviewInfo.brandnm}</dt>
								<dd>
									${reviewInfo.goodsnmKR}
								</dd>
							</dl>
							<p class="tx01"><c:if test="${not empty reviewInfo.opt1}">${reviewInfo.opt1}, </c:if> ${empty reviewInfo.opt2 ? 'ONE SIZE' : reviewInfo.opt2 }</p>
							<div class="tx02">${reviewInfo.ea}개</div>
						</li>
					</ul>
				</div>
				<div class="mid_info">
					<p class="s_tx">별점*</p>
					<ul class="ul_review">
						<li><a href="javascript:;" data-num="1">☆</a></li>
						<li><a href="javascript:;" data-num="2">☆</a></li>
						<li><a href="javascript:;" data-num="3">☆</a></li>
						<li><a href="javascript:;" data-num="4">☆</a></li>
						<li><a href="javascript:;" data-num="5">☆</a></li>
					</ul>
					<div style="clear:both;"></div>
				</div>
				<div class="bt_info">
					<p class="s_tx">이미지 업로드</p>
					<ul>
						<li class="fileupload">
							<div class="filebox preview-image">
								<input class="upload-name" placeholder="파일선택" value="" disabled="disabled" style="display:none;">
								<input type="file" name="file1" id="file1" class="reviewFile upload-hidden">
								<input type="hidden" name="fnoArr">
								<label for="file1" class="btn_add_p">+</label>
							</div>
						</li>
						<li class="fileupload">
							<div class="filebox preview-image">
								<input class="upload-name" placeholder="파일선택" value="" disabled="disabled" style="display:none;">
								<input type="file" name="file2" id="file2" class="reviewFile upload-hidden">
								<input type="hidden" name="fnoArr">
								<label for="file2" class="btn_add_p">+</label>
							</div>
						</li>
						<li class="fileupload">
							<div class="filebox preview-image">
								<input class="upload-name" placeholder="파일선택" value="" disabled="disabled" style="display:none;">
								<input type="file" name="file3" id="file3" class="reviewFile upload-hidden">
								<input type="hidden" name="fnoArr">
								<label for="file3" class="btn_add_p">+</label>
							</div>
						</li>
						<li class="fileupload">
							<div class="filebox preview-image">
								<input class="upload-name" placeholder="파일선택" value="" disabled="disabled" style="display:none;">
								<input type="file" name="file4" id="file4" class="reviewFile upload-hidden">
								<input type="hidden" name="fnoArr">
								<label for="file4" class="btn_add_p">+</label>
							</div>
						</li>
					</ul>
					<div style="clear:both;"></div>
					<div class="bx_txa">
						<textarea id="review_contents" name="review_contents" onkeyup="javascript:fnChkByte(this);" placeholder="내용을 입력하세요 (최소 1000byte 이상, 최대 2000byte 미만)"></textarea>
					</div>
					<div class="bt_tx01">
						- 욕설, 근거없는 비방, 검증되지 않는 가품시비 등은 예고없이 삭제될 수 있습니다.<br/>
						- 업로드한 리뷰는 수정, 삭제가 불가능합니다. 
					</div>
					<div style="text-align:center;">
						<a href="javascript:;" id="btn_review" class="btn_pop_review">리뷰 작성</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
