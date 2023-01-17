<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
 
 <script type="text/javascript">
	function delItem(num){
		if (confirm("정말 삭제하시겠습니까??")){
			var frm = document.frmWish;
			frm.snoList.value = num;
		    frm.action = 'mypage_wishlist_delete';
		    frm.submit();
		}else{  //취소
		    return false;
		}
	}
	
	function cartItem(sno){
		<c:choose>
			<c:when test="${empty userInfo.xkey.level || userInfo.xkey.level eq '1'}">
				alert('VIP 판매 상품 입니다.');
				return;
			</c:when>
			<c:otherwise>
				var frm = document.frmWish;
				
				var goodsno = document.getElementById('goodsno_' + sno).value;
				
				var opt = document.getElementById('opt_' + sno).value;					
				if (opt == null || opt == "|") opt = 'NULL';
				
				var addopt = document.getElementById('addopt_'+sno).value;
				if(addopt == null || addopt == "") addopt = 'NULL';
				
				frm.optionsList.value = goodsno + '|||' + opt + '|||' + addopt + '|||' + '1';
				frm.mode.value = 'addItem';
				frm.action = ctx+'/shop/goods/goods_cart';
				frm.submit();
			</c:otherwise>
		</c:choose>
	}
	
	function act(mode){
		var frm = document.frmWish;
		if (isChked('sno')){
			frm.mode.value = mode;
			var checkboxes = document.getElementsByName("sno");
			
			if (mode == 'delItem'){
				if (confirm("정말 삭제하시겠습니까??")){
					var snoList = '';
					for(var i=0;i<checkboxes.length;i++){
						if(checkboxes[i].checked){
							if(snoList == ''){
				        		snoList += checkboxes[i].value;	    
				        	}else{
				        		snoList += ',' + checkboxes[i].value;
				        	}
						}
					}
					
					frm.snoList.value = snoList;
					frm.action = 'mypage_wishlist_delete';
					frm.submit();
				}
			}
		}
	}
	
	function goPage(page){
		$('#pageNo').val(page);
		$('#frmWish').submit();
	}
</script>

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<form id="frmWish" name="frmWish" method="post">
	<input type="hidden" name="mode" value="${myBoardVO.mode}"/>
	<input type="hidden" name="snoList" value="" />
	<input type="hidden" name="optionsList" value="" />
	<input type="hidden" id="pageNo" name="pageNo" value="${myBoardVO.pageNo != null ? myBoardVO.pageNo : 1}" />

	<div class="x-mypage-wishlist container_mypage">
		<div class="tabbable product-tabs">
			<!-- 고객센터 공통탭메뉴 처리 -->
			<jsp:include page="../mypage/mypage_tab_menu.jsp" flush="true">
				<jsp:param name="tab_order" value="6" />
			</jsp:include>

			<div class="navi_my">나의 쇼핑 > 위시리스트</div>

			<div class="tx_my02">
				품절된 상품은 위시리스트에서 삭제 될 수 있습니다.  
			</div>
	
			<div class="wishlist_wrap">
				<div class="tp_area">
					<div class="bx_l">
						<input type="checkbox" class="ck_my_ty01" id="all" onclick="javascript:chkBox('sno','rev')" /><label for="all" style="margin:0; cursor:pointer;">전체선택</label>
					</div>
					<div class="bx_r"><a class="btn btn-default btn_del_all" href="javascript:act('delItem')">선택상품삭제</a></div>
					<div style="clear:both;"></div>
				</div>
		
				<div class="sub_list02 ty02">
					<ul>
						<c:if test="${!empty(myBoardVO.wishList)}" >
							<c:forEach items="${myBoardVO.wishList}"  var="wi_gdList"  varStatus="status">	
								<c:if test="${!empty(addopt)}" ><c:forEach items="${addopt}"  var="var_addopt"  varStatus="status1">${var_addopt[status1.index]}</c:forEach></c:if>
								<input type="hidden" id="goodsno_${wi_gdList.sno}" name="goodsno" value="${wi_gdList.goodsno}" />
								<%-- <input type="hidden" id="opt_${wi_gdList.sno}" name="opt" value="${wi_gdList.opt1}|${wi_gdList.opt2}" /> --%>
								<input type="hidden" id="opt_${wi_gdList.sno}" name="opt" value="${wi_gdList.opt1}" />
								<input type="hidden" id="addopt_${wi_gdList.sno}" name="addopt" value="${wi_gdList.addopt}" />
								<input type="hidden" id="sno_${wi_gdList.sno}" name="sno" value="${wi_gdList.sno}" />
								
								<li>
									<div class="in_bx">
										<div class="bx_thum">
											<div class="ck_l"><input type=checkbox name="sno" value="${wi_gdList.sno}" class="ck_my_ty01" /></div>
											<c:set var="idx" value="${fn:indexOf(wi_gdList.imgs, '|')}" />
											<c:set var="sub" value="${fn:substring(wi_gdList.imgs, 0, idx+1)}" />
											<a href="../goods/goods_view?goodsno=${wi_gdList.goodsno}&category=${wi_gdList.category}">
										        <c:if test="${not empty wi_gdList.imgs}">
					              					  <img src="${wi_gdList.imgs}" alt="${wi_gdList.goodsnmKR}"  onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'" class="product-img" />
													  <span class="control-active"></span>
												</c:if>
											</a>
										</div>
										<div class="bx_info">
											<div class="tx_brand">${wi_gdList.brandnm}</div>
											<div class="tx_tit">
												<a href="../goods/goods_view?goodsno=${wi_gdList.goodsno}&category=${wi_gdList.category}">
													${wi_gdList.goodsnmKR}
												</a> 
												<c:if test="${wi_gdList.opt1 ne ''}" >
													[${wi_gdList.opt1}/${wi_gdList.opt2}]
												</c:if>
				
												<c:forEach var="addopt" items="${wi_gdList.addopt}" varStatus="addStatus" >
													<c:set var="addoptArr" value="${fn:split(addopt,'|')}"/>
													
													<c:forEach var="addoptArr" items="${addoptArr}" varStatus="addStatus" >
														<c:if test="${not empty addoptArr}">
															<c:set var="addoptTemp" value="${fn:split(addoptArr,'^')}"/>
															<c:set var="addopt1" value="${addoptTemp[1]}"/>
															<c:set var="addopt2" value="${addoptTemp[2]}"/>
															<c:set var="addopt3" value="${addoptTemp[3]}"/>
															<div><span class="dot-yellow-s">${addopt1} : ${addopt2} [${addopt3}원]</span></div>
															<%-- <div>${addopt1}</div>
															<div>${addopt2}<div>
															<div>${addopt3}<div> --%>
														</c:if>
														
													</c:forEach>
												</c:forEach>
												<%-- ${shopLibFunction:addopt(wi_gdList.addopt)} --%>
											</div>
											<div class="tx_price01">
												<c:set var="priceSum" value="${wi_gdList.price + addpriceSum}" />
												<%-- 		<c:if test="${shopLibFunction:getProperty('emoney_chk_goods_emoney') == 0}">
													${shopLibFunction:getExchange(wi_gdList.reserve, wkin)}
												</c:if>
												<c:if test="${shopLibFunction:getProperty('emoney_chk_goods_emoney') == 1}">
													${shopLibFunction:getExchange(wi_gdList.reserve, wskin)}
												</c:if>
												${shopLibFunction:getExchange(shopLibFunction:getReserve(wi_gdList.goodsno), wskin)} --%>
												${shopLibFunction:getExchange(wi_gdList.price, wskin)}
												<span class="tx_per"><c:if test="${wi_gdList.priceRate >= 3}"><fmt:formatNumber pattern="#0" value="${wi_gdList.priceRate}"/>%</c:if></span>
											</div>
										   <div class="tx_price02"><c:if test="${wi_gdList.priceRate >= 3}">${shopLibFunction:getExchange(wi_gdList.consumer, wskin)}</c:if></div>
										   <!-- <div>${wi_gdList.rdt}</div> -->
										</div>
									</div>
									<div class="btm_btns">
										<c:choose>
											<c:when test="${wi_gdList.stock > 0}">
												<c:choose>
													<c:when test="${wi_gdList.vipYn eq 'Y' && (empty userInfo.xkey.level || userInfo.xkey.level eq '1')}">
														<a class="btn btn-default btn_ty01" href="javascript:alert('VIP 판매 상품 입니다.');">장바구니 담기</a>
													</c:when>
													<c:otherwise>
														<a class="btn btn-default btn_ty01" href="javascript:cartItem('${wi_gdList.sno}')">장바구니 담기</a>
													</c:otherwise>
												</c:choose>
												<a class="btn btn-default btn_ty02" href="javascript:delItem('${wi_gdList.sno}')">삭제</a>
												<div style="clear:both;"></div>
											</c:when>
											<c:otherwise>
												<span class="btn btn-default btn_ty03">SOLD OUT</span>
											</c:otherwise>
										</c:choose>
									</div>
								</li>
							</c:forEach>
						</c:if>
						<c:if test="${empty(myBoardVO.wishList)}" >
							<div>등록된 상품이 없습니다.</div>
						</c:if>
					</ul>
					<div style="clear:both;"></div>
				</div>
			</div>
		
			<div class="col-md-10">
				 <nav class="text-center">
					<ul class="pagination category-pagination">
						<tags:frontPaginator currentPageNo="${myBoardVO.pageNo}" rowCount="${myBoardVO.rowCount}" pageSize="${myBoardVO.pageSize}"  pageGroupSize="${myBoardVO.pageGroupSize}" />
					</ul>
				</nav>
			</div>
			<br>
			
			</div>
		</div>
	</div>
</form>