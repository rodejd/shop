<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 화면 시작
================================================================================ --%>
<script type="text/javascript">

function fileSubmit() {

	if($("#xlsName").val() != ""){
		var ext = $('#xlsName').val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['xls']) == -1) {

	   	 alert('xls 파일만 업로드 할 수 있습니다');
	   	 return;
	         }
		
		var formData = new FormData($("#fileForm")[0]);
		$.ajax({
			type:'post',
			url:'xlsRegister/indb',			
			data:formData,
			processData:false,
			contentType:false,
			success:function(response){
				document.getElementById('excelResult').innerHTML= response;
				return false;
			},
			error:function(error){
				document.getElementById('excelResult').innerHTML= "파일업로드에 실패하셨습니다." + error.status;
				return false;
			}
		});	
	}else{
		alert('파일을 등록해주세요');
		return false;
	}
}


</script>
<form id="fileForm" name="excel" method="post" enctype="multipart/form-data">


	<table cellpadding=0 cellspacing=0 border=0 class=small_tip width=100%>
		<tr>
			<td style="padding-left: 22px; padding-top: 12px;">&nbsp;&nbsp;
			<font size=3 color=000000><b>① 아래 샘플파일을 다운받아 엑셀에서 상품정보를 작성합니다.<br>
			<div class=noline style="padding-left: 60px; padding-top: 5px; text-align: left;">
				<a href="${ctx}/shop/admin/excel/fileDownload?fN=goodsXls_sample.xls&dir=FILE_PATH">
				<img src="/resources/shop/admin/img/btn_goodcsv_sample.gif">
				</a>
			</div>
			<div style="padding-top: 10px"></div></td>
		</tr>
	</table>

	<table class="tb">
		<col style="width:120px" class="cellC">
		<col />
		<tr>
			<td>xls파일</td>
			<td>
				<input type="file" name="xlsName" id="xlsName" accept=".xls" style="cursor:pointer">&nbsp
				<input type="button" value="전송하기"	onClick="fileSubmit()" style="cursor:pointer">
			</td>
		</tr>
	</table>
	<br><div font class=small1 color=444444 id=excelResult>
	</div>

		<div id=MSG01>
			<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
				
				<tr>
				<div style="padding: 5px 0px 2px 5px"><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>상품필드설명</div></br>
					<td>
						<div style="width:100%;margin-left:10px;">
							<style>
							#field_table { border-collapse:collapse; }
							#field_table th { padding:4; }
							#field_table td { border-style:solid;border-width:1;border-color:#EBEBEB;color:#4c4c4c;padding:4; }
							#field_table i { color:green; font:8pt dotum; }
							</style>
							<table id="field_table">
								<tr bgcolor="#eeeeee">
									<th><font class=small1 color=444444><b>한글 타이틀</th>
									<th><font class=small1 color=444444><b>영문 타이틀</th>
									<th><font class=small1 color=444444><b>설명</th>
								</tr>
								<!-- 상품분류정보 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품분류(필수)</td>
									<td><font class=ver8 color=444444>category</td>
									<td><font class=small color=444444>'상품관리>상품분류관리'에서 설정한 분류번호 입력. 다수 경우 '|' 를 구분자로 입력. <i>ex) 002|003|004001</i></td>
								</tr>
								<!-- 판매사정보 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>판매사(필수)</td>
									<td><font class=ver8 color=444444>sellerCd</td>
									<td><font class=small color=444444>판매사코드 입력 , 미입력시 관리자 등록</i></td>
								</tr>
								
								<!-- 상품기본정보 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품명(필수)</td>
									<td><font class=ver8 color=444444>goodsnm</td>
									<td><font class=small color=444444>영문 255자, 한글 127자 이내 입력.</td>
								</tr>

								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품코드</td>
									<td><font class=ver8 color=444444>goodscd</td>
									<td><font class=small color=444444>영문 20자, 한글 10자 이내 입력.</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>원산지</td>
									<td><font class=ver8 color=444444>origin</td>
									<td><font class=small color=444444>영문 50자, 한글 25자 이내 입력.</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>브랜드번호</td>
									<td><font class=ver8 color=444444>brandno</td>
									<td><font class=small color=444444>'상품관리>브랜드관리'에서 설정한 브랜드번호 입력.</td>
								</tr>			
											
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>제품상태</td>
									<td><font class=ver8 color=444444>icon</td>
									<td><font class=small color=444444>신상품(1),추천(2),기획(4),인기(8),이벤트(16),예약(32),베스트(64),세일(128)<br>여러값을 추가하고자 하는 경우는 ',' 를 구분자로 입력 <br><i>ex) 1,2 = (신상품,추천)  4,8(기획,인기)</i>
									</td>
								</tr>						
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>유사검색어</td>
									<td><font class=ver8 color=444444>keyword</td>
									<td><font class=small color=444444>영문 255자, 한글 127자 이내 입력.</td>
								</tr>				
												
								<!-- 상품추가정보 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품추가정보 제목</td>
									<td><font class=ver8 color=444444>ex_title</td>
									<td><font class=small color=444444>추가정보의 제목을 입력합니다.<br>다수 경우 '|' 를 구분자로 입력. <i>ex) 출판사|저자|원제|발행일|ISBN|역자</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품추가정보1</td>
									<td><font class=ver8 color=444444>ex1</td>
									<td><font class=small color=444444>각 상품추가정보 제목에 해당되는 정보 입력. <i>ex) 베텔스만코리아</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품추가정보2</td>
									<td><font class=ver8 color=444444>ex2</td>
									<td><font class=small color=444444>각 상품추가정보 제목에 해당되는 정보 입력. <i>ex) 댄 브라운</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품추가정보3</td>
									<td><font class=ver8 color=444444>ex3</td>
									<td><font class=small color=444444>각 상품추가정보 제목에 해당되는 정보 입력. <i>ex) The Da Vinci Code</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품추가정보4</td>
									<td><font class=ver8 color=444444>ex4</td>
									<td><font class=small color=444444>각 상품추가정보 제목에 해당되는 정보 입력. <i>ex) 2004.07.05</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품추가정보5</td>
									<td><font class=ver8 color=444444>ex5</td>
									<td><font class=small color=444444>각 상품추가정보 제목에 해당되는 정보 입력. <i>ex) 895759051X</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품추가정보6</td>
									<td><font class=ver8 color=444444>ex6</td>
									<td><font class=small color=444444>각 상품추가정보 제목에 해당되는 정보 입력. <i>ex) 양선아</i></td>
								</tr>
								<!-- 적립금 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>적립금</td>
									<td><font class=ver8 color=444444>useEmoney</td>
									<td><font class=small color=444444>적립금 적립여부 사용(1), 미사용(0)</td>
								</tr>
								<!-- 가격/재고/배송비 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>판매가</td>
									<td><font class=ver8 color=444444>price</td>
									<td><font class=small color=444444>판매가 입력 (숫자만 입력)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>정가</td>
									<td><font class=ver8 color=444444>consumer</td>
									<td><font class=small color=444444>정가 입력 (숫자만 입력)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>매입가</td>
									<td><font class=ver8 color=444444>supply</td>
									<td><font class=small color=444444>매입가 입력 (숫자만 입력)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>재고량</td>
									<td><font class=ver8 color=444444>stock</td>
									<td><font class=small color=444444>재고량 입력 (숫자만 입력)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>재고량연동</td>
									<td><font class=ver8 color=444444>usestock</td>
									<td><font class=small color=444444>주문시 재고량빠짐(영소문자 o), 무한정판매(공백) 중 택일 입력.기본값 - 무한정판매(공백)</td>
								</tr>								
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>품절상품</td>
									<td><font class=ver8 color=444444>runout</td>
									<td><font class=small color=444444>품절(1), 판매(0) 중 택일 입력. 기본값 - 판매(0)</td>
								</tr>								
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>과세/비과세</td>
									<td><font class=ver8 color=444444>tax</td>
									<td><font class=small color=444444>과세(1), 비과세(0) 중 택일 입력. 기본값 - 과세(1)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>가격대체문구</td>
									<td><font class=ver8 color=444444>strprice</td>
									<td><font class=small color=444444>가격 대신 출력할 문구 입력. 영문 20자, 한글 10자 이내 입력.</td>
								</tr>								
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>배송타입</td>
									<td><font class=ver8 color=444444>delivery_type</td>
									<td><font class=small color=444444>기본배송비 사용(0), 무료배송(1), 상품별 배송비 입력(2), 착불 배송비(3)<br>2,3을 택하시면 배송비란에 배송비를 입력해주셔야 합니다. 기본값(0)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>배송비</td>
									<td><font class=ver8 color=444444>goods_delivery</td>
									<td><font class=small color=444444>배송타입 란에 상품별 배송비 입력(2), 착불 배송비(3)을 선택한경우에만 배송비를 입력해주세요</td>
								</tr>
								<!-- 추가옵션/추가상품 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>추가상품제목</td>
									<td><font class=ver8 color=444444>addoptnm</td>
									<td><font class=small color=444444>형식) 제목^필수여부<br>필수여부는 필수(영소문자 o), 선택(공백) 중 택일하여 입력.<br>다수 경우 '|' 를 구분자로 입력.<br><i>ex) 생일소품^o|부가상품^</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>추가상품목록</td>
									<td><font class=ver8 color=444444>addopts</td>
									<td><font class=small color=444444>형식) 제목^상품명^가격.<br>주의) 제목은 추가상품제목에서 입력한 제목과 동일해야 합니다.<br>다수 경우 '|' 를 구분자로 입력.<br><i>ex) 생일소품^폭죽^1500|생일소품^생일카드^1000|부가상품^와인^10000</i></td>
								</tr>								
								<!-- 관련상품 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>관련상품방식</td>
									<td><font class=ver8 color=444444>relationis</td>
									<td><font class=small color=444444>자동(0), 수동(1) 중 택일 입력. 기본값 - 자동(0)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>관련상품번호</td>
									<td><font class=ver8 color=444444>relation</td>
									<td><font class=small color=444444>관련상품방식이 수동(1)인 경우 ',' 를 구분자로 상품번호 입력. <i>ex) 1087,1103,1111,1187</i></td>
								</tr>								
								<!-- 상품이미지 -->								
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>메인이미지</td>
									<td><font class=ver8 color=444444>img_i</td>
									<td><font class=small color=444444>메인에 출력할 이미지명 입력. <i>이미지 파일은  확장자가 jpg,png,gif,bmp 종류만 가능합니다.</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>리스트이미지</td>
									<td><font class=ver8 color=444444>img_s</td>
									<td><font class=small color=444444>리스트에 출력할 이미지명 입력. <i>이미지 파일은  확장자가 jpg,png,gif,bmp 종류만 가능합니다.</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상세이미지</td>
									<td><font class=ver8 color=444444>img_m</td>
									<td><font class=small color=444444>상세에 출력할 이미지명 입력. <i>이미지 파일은  확장자가 jpg,png,gif,bmp 종류만 가능합니다. <br> 다수 경우 '|' 를 구분자로 입력. ex) test1.gif|test2.gif</i></td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>확대이미지</td>
									<td><font class=ver8 color=444444>img_l</td>
									<td><font class=small color=444444>확대에 출력할 이미지명 입력. <i>이미지 파일은  확장자가 jpg,png,gif,bmp 종류만 가능합니다. <br> 다수 경우 '|' 를 구분자로 입력. ex) test1.gif|test2.gif</i></td>
								</tr>
								<!-- 상품설명 -->
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>짧은설명</td>
									<td><font class=ver8 color=444444>shortdesc</td>
									<td><font class=small color=444444>영문 255자, 한글 127자 이내 입력.</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품설명</td>
									<td><font class=ver8 color=444444>longdesc</td>
									<td><font class=small color=444444>상품에 대한 상세한 설명 입력.</td>
								</tr>
															
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>관리 메모</td>
									<td><font class=ver8 color=444444>memo</td>
									<td><font class=small color=444444>관리자 메모를 입력.</td>
								</tr>
								
							
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>상품출력여부</td>
									<td><font class=ver8 color=444444>open</td>
									<td><font class=small color=444444>보이기(1), 감추기(0) 중 택일 입력. 기본값 - 감추기(0)</td>
								</tr>

								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>옵션출력방식</td>
									<td><font class=ver8 color=444444>opttype</td>
									<td><font class=small color=444444>일체형(single), 분리형(double) 중 택일 입력. 기본값 - 일체형(single)</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td><font class=small1 color=444444>타이틀태그설정</td>
									<td><font class=ver8 color=444444>meta_title</td>
									<td><font class=small color=444444>상품상세페이지 타이틀 태그를 상품명으로 설정(1), 미설정(공백) 중 택일 입력. 기본값 - 미설정(공백)</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	
</form>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>