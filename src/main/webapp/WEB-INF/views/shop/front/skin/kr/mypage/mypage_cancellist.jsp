<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<script type="text/javascript">
$(function(){
	// 주문&환불요청
	$(".btn_cancel").on("click",function(){
		var mode = $(this).data("mode");
		
		var title = "취소사유";
		var comment = "취소를";
		
		//취소요청
		if(mode == "cancelReq"){
			title = "취소요청";
			comment = "취소요청을";
			
		//환불요청
		}else if(mode == "refund"){
			title = "환불사유";
			comment = "환불을";
			$(".cancelBank").show(); //환불정보
		//반품/환불
		}else if(mode == "return"){
			title = "반품/환불";
			comment = "반품/환불을";
			//무통장입금
			if($(this).data("kind") == "a"){
				$(".cancelBank").show(); //환불정보
			}
			$(".cancelDelivery").show(); //반품 택배정보
			$("#layer_settlekind").val($(this).data("kind"));
			$("#layer_opt").val($(this).data("opt")); //옵션번호
		}
		
		$(".cancelTitle").text(title);
		$(".cancelCommnet").text(comment);
		$("#layer_ordno").val($(this).data("ordno"));
		$("#layer_mode").val(mode);
		
		$(".bx_cancel_reason").css("display", "block");
		
		$("#code").focus();
	});
	
	//취소처리
	$(".btn_cancel_submit").on("click",function(){
		if( $("#code").val() == "" ){
			alert("취소사유를 선택해주세요.");
			$("#code").focus();
			return;
		}
		
		if( $("#memo").val() == "" ){
			alert("내용을 입력해주세요.");
			$("#memo").focus();
			return;
		}
		
		var msg = "취소처리 하시겠습니까?";
		if($("#layer_mode").val() == "refund" || ($("#layer_mode").val() == "return" && $("#layer_settlekind").val() == "a") ){
			if( $("#bankcode").val() == "" ){
				alert("환불받을 은행을 선택해주세요.");
				$("#bankcode").focus();
				return;
			}
			
			if( $("#bankaccount").val() == "" ){
				alert("환불받을 계좌번호를 입력해주세요.");
				$("#bankaccount").focus();
				return;
			}
			
			if( $("#bankuser").val() == "" ){
				alert("환불받을 예금주를 입력해주세요.");
				$("#bankuser").focus();
				return;
			}
			
			msg = "환불요청을 하시겠습니까?";
		}
		
		if( $("#layer_mode").val() == "return" ){
			if( $("#returnDeliveryCompCd").val() == "" ){
				alert("반품 택배사를 선택해주세요.");
				$("#returnDeliveryCompCd").focus();
				return;
			}
			
			if( $("#returnInvoice").val() == "" ){
				alert("반품 송장번호를 입력해주세요.");
				$("#returnInvoice").focus();
				return;
			}
			
			msg = "반품요청을 하시겠습니까?";
		}
		
		//반품/환불
		if($("#layer_mode").val() == "return"){
			msg = "반품/환불을 요청 하시겠습니까?";
		}
		
		
		if( !$("#chk_cancel").is(":checked") ){
			alert("주문취소 정책 및 안내에 동의해 주세요.");
			$("#chk_cancel").focus();
			return;
		}
		
		if(!confirm(msg)) return;
		$.ajax({
			type : 'POST',
			url : '/shop/mypage/mypage_cancel.doJson',
			data: $("#frmLayer").serialize(),
			async: false,
			dataType : 'json',
			success : function (data) {
				if (data.resCode){
					alert("처리 되었습니다.");
					goPage($('#pageNo').val());
				} else {
					var msg = "";
					if(data.resMsg == "M01"){
						msg = "로그인후 이용가능합니다.";
					}else if(data.resMsg == "M02"){
						msg = "잘못된 접근입니다.";
					}else if(data.resMsg == "M03"){
						msg = "오류가 발생했습니다.";
					}
					alert(msg);
				}
			}
		});
	});
	
	// 배송조회
	$(".btn_order").on("click",function(){
		
	});
	
	$(".bx_cancel_reason .btn_pop_close_n").click(function(){
		 $('#frmLayer')[0].reset();
		$(".cancelBank").hide(); // 환불정보
		$(".cancelDelivery").hide(); //반품 택배정보
		$(".bx_cancel_reason").css("display", "none");
	});
});


function goPage(page){
	$('#pageNo').val(page);
	document.frmList.submit();
}
function goSubmit(){
	$('#pageNo').val(1);
	document.frmList.submit();
}
function clickDeliRadio(status){
	$("input[name=sear]").val(status);
	$("#frmList").submit();
}

function clickPeriodRadio(status){
	$("input[name=sear2]").val(status);
	$("#frmList").submit();
}

function order_confirm(ordno){
	$('#mode').val("confirm");
	$('#ordno').val(ordno);
	if (confirm('주문하신 상품을 수령확인 하시겠습니까?')){
		ajaxCallJsonPost("/shop/mypage/indb.dojson", "frmList", function(result){
			//alert(JSON.stringify(result));
			location.href=ctx+"/shop/mypage/mypage_orderlist";
		});
	}
}

function order_sendback(ordno){
	
	if (confirm('주문하신 상품을 반품 하시겠습니까?')){
		window.open(ctx+"/shop/mypage/popup_mypage_refund", "sendback", "width=1090, height=440, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		var fm = document.frmList;
		fm.mode.value = "sendback";
		fm.ordno.value = ordno;
		fm.target = "sendback";
		fm.action = ctx+"/shop/mypage/popup_mypage_refund";
		fm.method = "post";
		fm.submit();
	}
}

function order_trade(ordno){
	
	if (confirm('주문하신 교환 하시겠습니까?')) {
		window.open(ctx+"/shop/mypage/popup_mypage_refund", "trade", "width=1090, height=410, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		//window.open("mypage_refund.jsp", "trade", "width=850, height=900, toolbar=no, menubar=no, scrollbars=no, resizable=no");
		var fm = document.frmList;
		fm.mode.value = "trade";
		fm.ordno.value = ordno;
		fm.target = "trade";
		fm.action = ctx+ "/shop/mypage/popup_mypage_refund";
		fm.method = "post";
		fm.submit();
	}
}
</script>


<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="x-mypage-orderlist container_mypage">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="mypage_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="3" />
		</jsp:include>
	
		<div class="">
			<div class="">
				<div class="tab-pane fade in active">
					<div class="navi_my">주문관리 > 주문배송조회</div>

					<div class="order_step">
						<div class="in_bx">
							<dl>
								<dt>결제완료</dt>
								<dd>${orderStepInfo.cnt1}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>재고확인중</dt>
								<dd>${orderStepInfo.cnt2}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>배송준비중</dt>
								<dd>${orderStepInfo.cnt3}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>배송중</dt>
								<dd>${orderStepInfo.cnt4}</dd>
							</dl>
						</div>
						<div class="st_arr">></div>
						<div class="in_bx">
							<dl>
								<dt>배송완료</dt>
								<dd>${orderStepInfo.cnt5}</dd>
							</dl>
						</div>
					</div>

					<div class="my_tab_ty01">
						<ul>
							<li><a href="/shop/mypage/mypage_orderlist">주문내역 (${orderStepInfo.cnt6})</a></li>
							<li><a href="#" class="on">취소/환불내역 (${orderStepInfo.cnt7})</a></li>
						</ul>
						<div style="clear:both;"></div>
					</div>

					<form name="frmList" id="frmList" method="post" action="${ctx}/shop/mypage/mypage_cancellist">
						<input type="hidden" name="pageNo" id="pageNo" value="${frontMypageVO.pageNo }">
						
						<div class="my_order_wrap01">
							<div class="sel_r">
								<select id="sear2" name="sear2" onchange="javascript:goSubmit();">
									<option value="week" ${frontMypageVO.sear2 eq 'week' ? 'selected' : ''}>최근 1주일</option>
									<option value="month_1"  ${frontMypageVO.sear2 eq 'month_1' ? 'selected' : ''}>최근 1개월</option>
									<option value="month_3"  ${frontMypageVO.sear2 eq 'month_3' ? 'selected' : ''}>최근 3개월</option>
									<option value="month_6"  ${frontMypageVO.sear2 eq 'month_6' ? 'selected' : ''}>최근 6개월</option>
									<option value="month_12" ${frontMypageVO.sear2 eq 'month_12' ? 'selected' : ''}>최근 12개월</option>
								</select>
							</div>
							<div class="my_list_ty01">
								<ul class="tit_area">
									<li>
										<div class="in_bx area01">
											<p class="tx01">주문일자</p>
											<p class="tx02">(주문번호)</p>
										</div>
										<div class="in_bx area02">상품정보</div>
										<div class="in_bx area03">결제금액</div>
										<div class="in_bx area04">주문상태</div>
										<div class="in_bx area05"></div>
										<div style="clear:both;"></div>
									</li>
								</ul>
								<ul class="cont_area">
									<c:choose>
										<c:when test="${not empty frontMypageVO.frontOrderList }">
											<c:forEach var="list" items="${frontMypageVO.frontOrderList }" varStatus="st">
												<li>
													<div class="wrap_in">
														<div class="area01">
															<p class="tx01"><fmt:formatDate value="${list.orddt}" pattern="yyyy-MM-dd"/></p>
															<p class="tx02">(${ list.ordno })</p>
														</div>
														<div class="area02_01">
															<a href="/shop/goods/goods_view?goodsno=${list.goodsno}">
																<img src="${list.imgs }" onerror="this.src='/resources/shop/data/skin/kr/img/common/noimg_500.gif';">
															</a>
														</div>
														<div class="area02_02">
															<div class="tx01">
																<a href="/shop/goods/goods_view?goodsno=${list.goodsno}">
																	<dl>
																		<dt>${shopLibFunction:getGoodsBrandName(list.brandno)}</dt>
																		<dd>${list.goodsnmKR}
																			<c:if test="${ list.goodsnmcnt > 1 }">
																			<b>외 ${ list.goodsnmcnt - 1}건</b>
																		</c:if>
																		</dd>
																	</dl>
																</a>
															</div>
															<div class="tx02"><c:if test="${not empty list.opt1}">${list.opt1}, </c:if> ${empty list.opt2 ? 'ONE SIZE' : list.opt2 }</div>
															<div class="tx03">${list.ea}개</div>
														</div>
														<div class="area03">
															<div class="tb_out">
																<div class="tb_in">
																	<c:set var="price" value="${list.price * list.ea}" />
																	${shopLibFunction:getExchange(list.prnsettleprice, en)}
																</div>
															</div>
														</div>
														<div class="area04">
															<div class="tb_out">
																<div class="tb_in">
																	${shopLibFunction:front_r_stepi(list.step, list.istep) }
																</div>
															</div>
														</div>
														<div class="area05">
															<div class="tb_out">
																<div class="tb_in">
																	<p><a class="btn btn-default btn_write_review btn_ty01" href="/shop/mypage/mypage_orderinfo?ordno=${list.ordno}">상세보기</a></p>
																	<c:choose>
																		<c:when test="${(list.step eq 0 and list.istep eq 51) 
																						or (list.step eq 1 and list.istep eq 0 and list.settlekind eq 'c' ) 
																						or (list.step eq 10 and list.istep eq 0 and list.settlekind eq 'c' ) 
																						}">
																			<%--입금대기 or 결제완료 or (재고확인중 and 카드결제) : 주문취소 --%>
																			<p><a class="btn btn-default btn_write_review btn_ty01 btn_cancel" data-mode="cancel" data-ordno="${list.ordno}" href="javascript:;">주문취소</a></p>
																		</c:when>
																		<c:when test="${ (list.step eq 1 and list.istep eq 0)
																						or (list.step eq 1 and list.istep eq 10)
																						or (list.step eq 10 and list.istep eq 0)
																						or (list.step eq 11 and list.istep eq 0)
																						or (list.step eq 2 and list.istep eq 0 )
																						or (list.step eq 12 and list.istep eq 0)
																						}">
																				<c:choose>
																					<c:when test="${list.settlekind eq 'c'}">
																						<%-- (결제완료 or 결제완료 수집완료 or 재고확인중 or 재고확인완료 or 배송준비중 or 입고완료) and 카드결제 --%>
																						<p><a class="btn btn-default btn_write_review btn_ty01 btn_cancel" data-mode="cancelReq" data-ordno="${list.ordno}" href="javascript:;">취소요청</a></p>
																					</c:when>
																					<c:when test="${list.settlekind eq 'a'}">
																						<%-- (결제완료 or 결제완료 수집완료 or 재고확인중 or 재고확인완료 or 배송준비중 or 입고완료) and 무통장입금 --%>
																						<p><a class="btn btn-default btn_write_review btn_ty01 btn_cancel" data-mode="refund" data-ordno="${list.ordno}" href="javascript:;">취소요청</a></p>
																					</c:when>
																				</c:choose>
																		</c:when>
																	</c:choose>
																	
																	<%--입고완료 or 배송중 : 배송조회 --%>
																	<c:if test="${list.step eq 12 and list.istep eq 0 or list.step eq 3 and list.istep eq 0}">
																		<p><a class="btn btn-default btn_write_review btn_ty01 btn_order" href="javascript:;">배송조회</a></p>
																	</c:if>
																	<%-- 배송중 or 배송완료 : 반품/환불 --%>
																	<c:if test="${list.step eq 3 and list.istep eq 0 or list.step eq 4 and list.istep eq 0 }">
																		<p><a class="btn btn-default btn_write_review btn_ty01 btn_cancel" data-mode="return" data-ordno="${list.ordno}" data-kind="${list.settlekind}" data-opt="${list.opt1}" href="javascript:;">반품/환불</a></p>
																	</c:if>
																</div>
															</div>
														</div>
													</div>
												</li>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<li><div style="text-align: center;">주문내역이 없습니다.</div></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
					</form>
					
					<div class="col-md-12 text-center">
						<nav>
							<ul class="pagination category-pagination">
								<tags:frontPaginator currentPageNo="${frontMypageVO.pageNo}" rowCount="${frontMypageVO.rowCount}" pageSize="${frontMypageVO.pageSize}"  pageGroupSize="${frontMypageVO.pageGroupSize}" />
							</ul>
						</nav>
					</div>
					
					<!-- 주문취소신청 / 반품신청 -->
					<div class="pop_wrap bx_cancel_reason" style="position:absolute; top:0; left:0; width:100%; height:100%; z-index:100000000; display:none;">
						<form name="frmLayer" id="frmLayer" method="post" action="${ctx}/shop/mypage/mypage_orderlist">
							<input type="hidden" id="layer_ordno" name="ordno" />
							<input type="hidden" id="layer_mode" name="mode" />
							<input type="hidden" id="layer_settlekind" name="settlekind" />
							<input type="text" id="layer_opt" name="opt" />
							
							<div style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:0.7;"></div>
							<div style="position:absolute; top:0; left:0; width:100%;">
								<div style="width:1256px; margin:30px auto 0 auto;">
									<div style="position:relative; height:70px; font-size:25px; color:#fff; line-height:70px; background:#292929; text-align:center;">
										<span class="cancelTitle">주문 취소</span>
										<a href="javascript:void(0);" class="btn_pop_close_n" style="position:absolute; top:28px; right:70px; font-size:25px; color:#fff; line-height:25px; text-decoration:none;">X</a>
									</div>
									<div style="background:#fff; padding:60px 100px 60px 100px;">
										<dl>
											<dt class="cancelTitle">취소사유</dt>
											<dd class="linetp">
												<select id="code" name="code" >
													<option value="">사유를 선택하세요.</option>
													${webUtil:makeSelectCodeItem((codeUtil:codeitem('cancel')), "") }
												</select>
											</dd>
											<dd>
												<textarea id="memo" name="memo" placeholder="내용을 입력하세요."></textarea>
												<div class="cancelDelivery" style="display: none;">
													<div>
														<label style="width: 100px;">반품 택배사</label>
														<select id="returnDeliveryCompCd" name="returnDeliveryCompCd" style="margin: 5px 0 5px 2px; padding: 0;">
															<option value="">택배사를 선택하세요.</option>
															<c:forEach var="delivery" items="${deliveryList}" varStatus="status">
																<option value="${delivery.deliveryno}">${delivery.deliverycomp}</option>
															</c:forEach>
														</select>
													</div>
													<div><label style="width: 100px;">반품 송장번호</label><input type="text" id="returnInvoice" name="returnInvoice" style="width: 538px; height: 50px; font-size: 18px; color: #282828; margin: 5px; border: 1px solid #d8d8d8;" onkeyup="removeChar(event);" onkeydown="removeChar(event);" ></div>
												</div>
												<div class="cancelBank" style="display: none;">
													<div>
														<label style="width: 100px;">환불 은행</label>
														<select id="bankcode" name="bankcode" style="margin: 5px 0 5px 2px; padding: 0;">
															<option value="">은행을 선택하세요.</option>
															${webUtil:makeSelectCodeItem((codeUtil:codeitem('bank')), "") }
														</select>
													</div>
													<div><label style="width: 100px;">환불 계좌번호</label><input type="text" id="bankaccount" name="bankaccount" style="width: 538px; height: 50px; font-size: 18px; color: #282828; margin: 5px; border: 1px solid #d8d8d8;" onkeyup="removeChar(event);" onkeydown="removeChar(event);" ></div>
													<div><label style="width: 100px;">환불 예금주</label><input type="text" id="bankuser" name="bankuser" style="width: 538px; height: 50px; font-size: 18px; color: #282828; margin: 5px; border: 1px solid #d8d8d8;"></div>
												</div>
												
												<div class="bt_tx">
													<ul>
														<li>- 구매대행 상품 특성상 자동 취소가 불가능합니다.</li>
														<li>- 취소 신청 당시에 상품의 재고 확보가 이미 완료된 경우 취소 수수료(30,000원)가 부과됩니다.</li>
														<li>- 주문취소 신청부터 주문취소 완료까지는 평균적으로 영업일 기준 2~3일이 소요됩니다.</li>
														<li>- 결제 시 사용하신 쿠폰의 유효기간이 소멸된 경우, 사용하셨던 쿠폰은 재발행되지 않습니다.</li>
														<li>- 취소완료시 결제 당시 사용하신 적립금은 전액 반환됩니다.</li>
												</div>
												<div>
													<input type="checkbox" class="ck_ty01" id="chk_cancel">
													<label for="chk_cancel">주문취소 정책 및 안내에 동의하며, <span class="cancelCommnet">취소를</span> 신청합니다.</label>
												</div>
											</dd>
										</dl>
										<div class="bt_btn">
											<a href="javascript:;" class="btn_my_cancel btn_cancel_submit">주문취소 신청</a>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<!-- //주문취소신청 / 반품신청 -->
				</div>
			</div>
		</div>
	</div>
</div>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



