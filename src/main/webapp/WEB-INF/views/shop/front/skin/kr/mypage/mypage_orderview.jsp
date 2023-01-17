<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : mypage_orderview.jsp
* 생성일 : 2017. 02. 22
* 작성자 : PMG
* 설   명 : skin default1 사용자 mypage_orderview
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170222			PMG				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<script type="text/javascript">

$(function(){

});
</script>

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script language="javascript">
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
}
/* function chkCancel(ordno){
	var f = document.ini;
	if(confirm('주문취소처리 하시겠습니까?')){
		alert('추후처리예정');
	}
} */

// remove by whpark 20191111
//function chkCancel(ordno){
//// 	var f = document.ini;
//		//f.action = '/shop/mypage/mypage_orderview_ordercancel.jsp';
//		<%-- f.ordno_c.value = ordno;
//		f.tid.value = '<%=tid.get("inis_tid")%>';
//		f.submit(); --%>

//	$('#mode').val("cancel");
//	$('#ordno').val(ordno);
//	if (confirm('주문취소처리 하시겠습니까?')){

//		/* if(!isAlreadySubmitted) {
//			isAlreadySubmitted = true; */
//			ajaxCallJsonPost("/shop/mypage/indb.dojson", "frmList", function(result){
//				location.href="/shop/mypage/mypage_orderview?ordno="+ordno;
//			});
///* 		} else {
//			window.alert("이미 실행중인 이벤트가 존재합니다. 잠시만 기다려주세요.");
//		} */
//	}
//		
//// 		alert('추후처리예정');
	
//}

//add by whpark 20191111 
function chkCancel(ordno,sno){
	$('#mode').val("cancel");
	$('#ordno').val(ordno);
	$('#sno').val(sno);
	if (confirm('주문취소처리 하시겠습니까?')){
		ajaxCallJsonPost("/shop/mypage/indb.dojson", "frmList", function(result){
			location.href=ctx+"/shop/mypage/mypage_orderview?ordno="+ordno;
		});
	}	
}


function order_confirm(ordno,sno){
	$('#mode').val("confirm");
	$('#ordno').val(ordno);
	$('#sno').val(sno);
	
	if (confirm('주문하신 상품을 수령확인 하시겠습니까?')){
		ajaxCallJsonPost("/shop/mypage/indb.dojson", "frmList", function(result){
			location.href=ctx+"/shop/mypage/mypage_orderview?ordno="+ordno;
		});
	}
}

function order_sendback(ordno,sno){
	
	if (confirm('주문하신 상품을 반품 하시겠습니까?')){
		window.open(ctx+"/shop/mypage/popup_mypage_refund", "sendback", "width=1090, height=440, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		var fm = document.frmList;
		fm.mode.value = "sendback";
		fm.ordno.value = ordno;
		fm.sno.value = sno;
		fm.target = "sendback";
		fm.action = ctx+ "/shop/mypage/popup_mypage_refund";
		fm.method = "post";
		fm.submit();
	}
}

function order_trade(ordno,sno){
	
	if (confirm('주문하신 교환 하시겠습니까?')) {
		window.open(ctx+"/shop/mypage/popup_mypage_refund", "trade", "width=1090, height=410, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		var fm = document.frmList;
		fm.mode.value = "trade";
		fm.ordno.value = ordno;
		fm.sno.value = sno;
		fm.target = "trade";
		fm.action = ctx+"/shop/mypage/popup_mypage_refund";
		fm.method = "post";
		fm.submit();
	}
}

function goList(){
	location.href = ctx+"/shop/mypage/mypage_orderlist";
}
</script>
</script>

<header class="page-header page-header-banner x-member-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">주문배송조회</h1>
        </div>
    </div>
</header>

<!-- 서브 컨텐츠 -->
<div id="content-area" class="x-mypage-orderlist x-mypage-orderview container">
	<!-- 컨텐츠 -->
	<div class="tabbable product-tabs">
		
		<!-- 고객센터 공통탭메뉴 처리 -->
		<c:if test="${m_no != 0}">
			<jsp:include page="mypage_tab_menu.jsp" flush="true">
				<jsp:param name="tab_order" value="3" />
			</jsp:include>
		</c:if>

		<div class="col-md-9">
			<!-- 주문내역 -->
			<table summary="" class="table table table-shopping-cart">
				<colgroup>
					<col />
					<col style="width:15%;" />
					<col style="width:15%;" />
					<col style="width:15%;" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">주문번호: ${frontMypageVO.ordno}</th>
						<th scope="col">수량</th>
						<th scope="col">금액</th>
						<th scope="col">배송상태</th>
						<th scope="col">수령확인</th>
					</tr>
				</thead>
				<tbody>
				
				<c:set var="optsum2" value="0" />
					<c:if test="${ !empty(frontMypageVO.frontOrderViewList) }" >
						<c:forEach items="${frontMypageVO.frontOrderViewList}"  var="order_rTable1"  varStatus="status" >
							<tr>
								<td class="row">
									<div class="buy-info-wrap">
										<div class="col-md-5">
											<c:set var="img" value="${order_rTable1.imgs }" scope="request"/>
											<c:set var="img_idx" value="${fn:indexOf(img,'|') + 1 }"></c:set>
											<c:set var="img_len" value="${fn:length(img) }"/>
											<%-- <c:choose>
												<c:when test="${fn:substring(order_rTable1.category,0,3) eq '001' }">
													<div class="buy-goods-img"><img src="/resources/shop/data/upload/recipeimg/${ order_rTable1.imgs}" alt="${ order_rTable1.goodsnmKR}" width="138px" height="138px"/></div>
												</c:when>
												<c:otherwise> --%>
													<div class="buy-goods-img"><img src="/resources/shop/data/upload/goods/${fn:substring( img, img_idx, img_len) }" alt="${ order_rTable1.goodsnmKR}" width="138px" height="138px"/></div>
												<%-- </c:otherwise>
											</c:choose> --%>
										</div>	
										<div class="col-md-7">
											<h4>${ order_rTable1.goodsnmKR }</h4>
											<c:if test="${fn:trim(order_rTable1.optname) != '' and order_rTable1.optname != null}">
												<p>[상품옵션 : ${order_rTable1.optname}]</p>
											</c:if>
											<p class="price mt0"><strong>${shopLibFunction:getExchange(order_rTable1.price, wskin)}</strong> </p>
											<c:set var="addopt" value="${order_rTable1.addopt}"/>
											<c:if test="${order_rTable1.addopt ne 'NULL' }">
												<%@ include file="../proc/orderitem_opt.jsp"%>	
											</c:if>
											<c:set var="optsum2" value="${optsum + optsum2 }"/>
										</div>
									</div>
								</td>
								<td>${stringUtil:getMoneyFormatInteger(order_rTable1.ea) } EA</td>
								<td>${shopLibFunction:getExchange(order_rTable1.price, wskin)}</td>
								
								<td>
									<p class="txt-red">
										${shopLibFunction:r_istep(order_rTable1.istep)}
										<c:if test = "${order_rTable1.istep > '1'}">
											<br/>${order_rTable1.invoice}
										</c:if>
										<%-- **${shopLibFunction:r_stepi(order_rTable1.step, order_rTable1.step2)}** --%>

										<!-- whpark 20191108 상품상태 start -->
										<%-- ${shopLibFunction:r_stepi_detail(order_rTable1.istep,order_rTable1.invoice,order_rTable1.deliveryCompNm,order_rTable1.deliveryStatus)} --%>
										
										<%-- **${order_rTable1.deliveryCompUrl}** --%>	
										<!-- whpark 20191108 end -->
										
										<!-- whpark 20191108 삭제 start -->
										<%-- **${order_rTable1.invoice}<br/>** --%>
										<%-- **${order_rTable1.deliveryStatus}** --%>
										<!-- whpark 20191108 end -->
									</p>
									<!-- whpark 20191108 start -->
									<c:if test="${ order_rTable1.istep eq '0'}">
									<a href="javascript:chkCancel('${frontMypageVO.ordno}','${order_rTable1.sno}');" class="btn btn-size-03 bg-black w100 mt10" ><span class="btn btn-warning">주문취소</span></a>
									</c:if>
									<!-- whpark 20191108 end -->

									<!-- whpark 20191108 start -->
									<c:if test="${order_rTable1.invoice != '' and order_rTable1.invoice != null and order_rTable1.deliveryCompCd != '' and order_rTable1.deliveryCompCd != null and order_rTable1.step > '1'}" >
										<c:choose>
											<c:when test="${order_rTable1.invoice == '' or order_rTable1.invoice == null and order_rTable1.deliveryCompCd == '' or order_rTable1.deliveryCompCd == null}">
												<a href="javascript:alert('배송정보가 존재하지 않습니다. 판매사에 문의하여 주십시오.');">
													<img src="/resources/shop/admin/img/btn_delifind.gif" border=0/>
												</a>
											</c:when>
											<c:otherwise>
												<a href="javascript:popup('${ctx }/shop/admin/sweetTracker/trackingInfo?deliverycode=${order_rTable1.invoice}&deliveryno=${order_rTable1.deliveryCompCd}',550,600)">
													<img src="/resources/shop/admin/img/btn_delifind.gif" border=0/>
												</a>
											</c:otherwise>
										</c:choose>
									</c:if>
									<!-- whpark 20191108 start -->
								</td>

								<td>
									<c:choose>
										<c:when test="${order_rTable1.istep eq '4'}">
												<a href="javascript:order_confirm('${frontMypageVO.ordno}','${order_rTable1.sno}');" class="btn btn-primary">수령확인</a>
										</c:when>
										<c:otherwise>
											<c:if test="${ order_rTable1.escrowconfirm eq '2'}">
												<p class="txt-red">수령</p>
											</c:if>
											<c:if test="${order_rTable1.istep eq '55'}">
												<a href="javascript:order_trade('${frontMypageVO.ordno}','${order_rTable1.sno}');" class="btn btn-size-03 bg-black w48 mt10">
													<span class="btn btn-warning">교환</span>
												</a>
												<a href="javascript:order_sendback('${frontMypageVO.ordno}','${order_rTable1.sno}');" class="btn btn-size-03 bg-black w48 mt10">
													<span class="btn btn-warning">반품</span>
												</a>
											</c:if>
										</c:otherwise>
									
									</c:choose>
								</td>
								
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
	
			<br><br><br>
			<!-- 결제금액 -->
			<h3 class="sub-title"><span class="dot-yellow-s">결제금액</span></h3>
			<table summary="" class="table table table-shopping-cart">
				<colgroup>
					<col style="width:25%;" />
					<col style="width:25%;" />
					<col style="width:25%;" />
					<col />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">금액합계</th>
						<th scope="col">쿠폰할인</th>
						<th scope="col">적립금 사용</th>
						<th scope="col">최종 결제 금액</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<%-- <p>상품금액 : ${shopLibFunction:getExchange(frontMypageVO.frontOrderViewObj[0].goodsprice, wskin)}</p> --%>
							<p>상품금액 : ${shopLibFunction:getExchange(frontMypageVO.frontOrderViewObj[0].goodsprice, wskin)}</p>
							<%-- 선불배송비가 없으면 무료 표시, 있으면 금액 표시 --%>
							<c:if test="${frontMypageVO.frontOrderViewObj[0].delivery != null}">
								<p>배송비 : 
									<c:choose>
										<c:when test="${frontMypageVO.frontOrderViewObj[0].delivery == '0'}">
											무료
										</c:when>
										<c:otherwise>
											<span id="paper_delivery">
												<fmt:formatNumber pattern="#,###">${frontMypageVO.frontOrderViewObj[0].delivery }</fmt:formatNumber>
											</span> 원
										</c:otherwise>
									</c:choose>
								</p>
							</c:if>
							<div>
								추가배송비 : <fmt:formatNumber pattern="#,###">${frontMypageVO.frontOrderViewObj[0].addDelivery != 0 and frontMypageVO.frontOrderViewObj[0].addDelivery != null ? frontMypageVO.frontOrderViewObj[0].addDelivery : 0 }</fmt:formatNumber> 원
							</div>
							<c:if test="${0 < frontMypageVO.frontOrderViewObj[0].memberdc}">
								<p>회원할인금액 : <span id="paper_goodsprice">- <fmt:formatNumber value="${frontMypageVO.frontOrderViewObj[0].memberdc}" pattern="#,###"/></span> 원</p>
								<p style="font-size: 10; color: red;">상품가격에만 회원할인율이 적용됩니다.</p>
							</c:if>
						</td>
						<td>
							- ${shopLibFunction:getExchange(frontMypageVO.frontOrderViewObj[0].coupon, wskin)}
							<c:if test="${not empty frontMypageVO.frontCouponList}">
								<c:set var="couponCd" value=""/>
								<c:forEach var="couponList" items="${frontMypageVO.frontCouponList}" varStatus="status" >
									<c:if test="${status.current.couponcd != couponCd}">
										<c:set var="couponCd" value="${status.current.couponcd}"/>
										<div style="margin-top: 10px;">
											<c:choose>
												<c:when test="${couponList.coupontype == 0}">
													[전체쿠폰]
												</c:when>
												<c:otherwise>
													<c:if test="${'' != couponList.category}">
														[카테고리]
													</c:if>
													<c:if test="${'' == couponList.category}">
														[상품쿠폰]
													</c:if>
												</c:otherwise>
											</c:choose>
											${couponList.coupon}
											<!-- 정액쿠폰 / 정률쿠폰 -->
											<c:if test="${fn:indexOf(couponList.price, '%') == -1}">
												<fmt:formatNumber pattern="#,###">${couponList.price}</fmt:formatNumber> 원
											</c:if> 
											<c:if test="${fn:indexOf(couponList.price, '%') > -1}">
												${couponList.price}
											</c:if>
											<!-- 할인 / 적립 구분 -->
											<c:choose>
												<c:when test="${couponList.couponAbi == 0}">
													할인
												</c:when>
												<c:otherwise>
													적립
												</c:otherwise>
											</c:choose>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
							
							
						</td>
						<td>
							- ${shopLibFunction:getExchange(frontMypageVO.frontOrderViewObj[0].emoney, wskin)}
						</td>
						<!-- 20191223 이현빈 결제금액 prnsettleprice 로 변경 -->
						<td><em class="total-price">${shopLibFunction:getExchange(frontMypageVO.frontOrderViewObj[0].prnsettleprice, wskin)}</em></td>
					</tr>
				</tbody>
			</table>
			<br><br><br>
			<!-- 주문자 배송 정보 -->
			<h3 class="sub-title">
				<span class="dot-yellow-s">
					주문자 및 배송정보
				</span>
			</h3>
			
			<table summary="" class="table table table-shopping-cart">
				<colgroup>
					<col style="width:50%;" />
					<col />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">주문자정보</th>
						<th scope="col">
							배송정보
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="left">
							<p>${ frontMypageVO.frontOrderViewObj[0].nameorder } / ${ frontMypageVO.frontOrderViewObj[0].mobileorder } / ${ frontMypageVO.frontOrderViewObj[0].email }</p>
							<p>(${frontMypageVO.frontOrderViewAddressObj.zipcode }) ${frontMypageVO.frontOrderViewAddressObj.address } ${frontMypageVO.frontOrderViewAddressObj.addresssub }</p>
						</td>
						<td class="left">
							<p>${ frontMypageVO.frontOrderViewObj[0].namereceiver } / ${ frontMypageVO.frontOrderViewObj[0].mobilereceiver } / ${ frontMypageVO.frontOrderViewObj[0].emailrecceiver }</p>
							<p>(${ frontMypageVO.frontOrderViewObj[0].zipcode }) ${ frontMypageVO.frontOrderViewObj[0].address }</p>
							<p>배송 시 요청사항: 
								<c:if test="${ frontMypageVO.frontOrderViewObj[0].memo != null && frontMypageVO.frontOrderViewObj[0].memo ne '' }">
									${ frontMypageVO.frontOrderViewObj[0].memo }
								</c:if>
							</p>
						</td>
					</tr>
				</tbody>
			</table>
			<br><br>
			<!-- 버튼영역 -->
			<c:if test = "${m_no != 0}">
				<nav class="text-center">
					<div class="over-wrap btn-wrap center">
						<a href="javascript:goList();" class="btn btn-size-02 bg-gray w200"><span class="btn btn-primary">목록으로</span></a>
					</div>
				</nav>
			</c:if>
			
		</div>
	</div>
</div>
<form name=frmList id=frmList method=post>
   <input type=hidden name=mode id=mode> 
   <input type=hidden name=ordno id=ordno>
   <input type=hidden name=sno id=sno>
</form>


 <form name=ini id=ini method=post action="/INIpayStdSample/INIStdCancel.jsp">
 	<input type=hidden name=mid value="<%-- <%=cfg("pg_id") %> --%>" size=40>
  	<input type=hidden name=tid size=40 >
  	<input type=hidden name=msg size=40 value="주문취소">
  	<input type=hidden name=cancelreason value="1">
  	<input type=hidden name=ordno_c >
</form>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



