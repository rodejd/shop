
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="/WEB-INF/views/shop/admin/common/header_popup.jsp" %>

		<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
			<tr>
				<td valign="top" style="padding-left:12px">
					<div class="title title_top">상품 검색등록</span></div>  
					
					<c:choose>
						<c:when test="${promotionGoodsVO.mode eq 'search'}">
							<script type="text/javascript">
								function chkForm(frm){
									var scmcd = [];
									$("input[name='chk[]']:checked").each(function(i){
										scmcd.push($(this).val());
									});
									
									if (scmcd.length == 0){
										alert("등록할 상품을 선택하세요.");
										return false;
									}
									
									$.ajax({
										type: "post",
										url: "goodsIns",
										headers: { "Content-Type" : "application/json" },
										data: JSON.stringify({"pmno": frm.pmno.value, "scmcd": scmcd.join(","), "grno": frm.grno.value}),
										dataType: "json",
										success: function(rst){
											alert("총 " + (rst.tot) + "건, 성공:" + rst.cnt + ", 실패:" + rst.err);
											parent.location.reload();
										},
										error: function(request, status, error){
											console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
										}
									});
									
									return false;
								}
							</script>
							
							<form name="frmList" method="post" action="goodsIns" onsubmit="return chkForm(this);">
								<input type="hidden" name="pmno" value="${promotionGoodsVO.pmno}">
							
								<div style="width: 588px; height: 260px;overflow: auto;">
									<table class="listTable" style="table-layout: unset;width: 1700px;">
										<tr>
											<th>선택</th>
											<th>구분</th>
											<th>SCM번호</th>
											<th>부틱명</th>
											<th>카테고리명</th>
											<th>브랜드명</th>
											<th>시즌</th>
											<th>EU</th>
											<th>상품명</th>
											<th>재고</th>
											<th>리테일가</th>
											<th>즉시할인가</th>
											<th>즉시할인율</th>
											<th>공헌이익율</th>
											<th>최저가여부</th>
											<th>상품상태</th>
											<th>관리상품여부</th>
											<th>적립미부여</th>
											<th>Hot100</th>
											<th>Vip Shop</th>
										</tr>
										<c:forEach items="${promotionGoodsVO.goodsList}" var="list" varStatus="status">
											<tr class="trClass">
												<td><input type="checkbox" name="chk[]" value="${list.goodscd}" /></td>
												<td>${list.grnm}</td>
												<td>${list.goodscd}</td>
												<td>${list.sellerCd}</td>
												<td>${list.categoryNm}</td>
												<td>${list.brandnm}</td>
												<td>${list.seasonNm}</td>
												<td>${list.euYn eq 'Y' ? 'EU' : '非EU'}</td>
												<td style="padding: 5px; text-align: left;">
													<div><font color=303030>${list.goodsnm}</font></div>
													<div style="padding-top:3px"><font color=303030>${list.goodsnmKR}</font></div>
													<div style="padding-top:3px"><font color=303030>${list.goodsnmCN}</font></div>
												</td>
												<td>${stringUtil:getMoneyFormatString(list.stock)}</td>
												<td>${list.consumer}</td>
												<td><fmt:formatNumber value="${list.price}" pattern="0.00"/></td>
												<td>${list.priceRate}%</td>
												<td>${list.margin}%</td>
												<td>
													<c:choose>
														<c:when test="${list.lowestPriceYn eq 'Y'}">최저가</c:when>
														<c:when test="${list.lowest2PriceYn eq 'Y'}">차저가</c:when>
													</c:choose>
												</td>
												<td>
													<c:choose>
													    <c:when test="${list.open eq '3'}">영구중지</c:when>
													    <c:when test="${list.open eq '2'}">일시중지</c:when>
													    <c:when test="${list.open eq '1'}">판매중</c:when>
													    <c:otherwise>전시대기</c:otherwise>
													</c:choose>
												</td>
												<td>${list.manageYn eq 'Y' ? '관리상품' : '일반상품' }</td>
												<td>${list.useemoney eq '1' ? 'Y' : 'N'}</td>
												<td>${list.hotYn eq '1' ? 'Y' : ''}</td>
												<td>${list.vipYn eq '1' ? 'Y' : ''}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
								
								<c:if test="${!empty promotionGoodsVO.goodsList && fn:length(promotionGoodsVO.goodsList)>0}">
									<div>
										<a href="javascript:PubAllSordes( 'select', frmList['chk[]'] );"><img src="/resources/shop/admin/img/btn_allselect_s.gif" alt="전체선택" align='absmiddle'></a>
										<a href="javascript:PubAllSordes( 'reflect', frmList['chk[]'] );"><img src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" align='absmiddle'></a>
										<a href="javascript:PubAllSordes( 'deselect', frmList['chk[]'] );"><img src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" align='absmiddle'></a>
									</div>
								</c:if>
						
								<c:choose>
									<c:when test="${!empty promotionGoodsVO.groupList && fn:length(promotionGoodsVO.groupList) > 0}">
										<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse; margin-top: 10px;">
											<col class="cellC" />
											<col class="cellL" />
											<tr>
												<td>구분</td>
												<td>
													<select name="grno">
														<c:forEach items="${promotionGoodsVO.groupList}" var="list">
															<option value="${list.grno}">${list.grnm}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
										</table>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="grno" value="0" />
									</c:otherwise>
								</c:choose>
								
								<div class="button">
									<input type="image" src="/resources/shop/admin/img/btn_register.gif">
									<a href="goodsSrchPopup?pmno=${promotionGoodsVO.pmno}"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								function chkForm(frm){
									if ($("[name='cate']").eq(0).val() == "" &&
										$("[name='schSellerNm']").val() == "" &&
										$("[name='schBrand']").val() == "" &&
										$("[name='schSoldOut']").val() == "" &&
										$("[name='schOpen']").val() == "" &&
										$("[name='schSeasonNm']").val() == "" &&
										$("[name='schEuYn']").val() == "" &&
										$("[name='schWord']").val() == ""){
										alert("검색 조건을 하나이상 입력해 주세요.");
										return false;
									}else{
										return true;
									}
								}
							</script>
							
							<form id="frmSrch" name="frmSrch" method="get" onsubmit="return chkForm();">
								<input type="hidden" name="pmno" value="${promotionGoodsVO.pmno}">
								<input type="hidden" name="mode" value="search">
					
								<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
									<col class="cellC" style="width:80px" />
									<col class="cellL" />
									<col class="cellC" style="width:80px" />
									<col class="cellL" />
									<tr>
										<td>카테고리</td>
										<td colspan="3">
											<script>new categoryBox('cate', 4, '${promotionGoodsVO.schCate}');</script>
										</td>
									</tr>
									<tr>
										<td>부틱</td>
										<td>
											<input type="text" name="schSellerNm" id="schSellerNm" value="${promotionGoodsVO.schSellerNm}" class="line" style="height:22px" />
											<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
											<input type="hidden" name="schSellerCd" id="schSellerCd" value="${promotionGoodsVO.schSellerCd}" />
										</td>
										<td>브랜드</td>
										<td>
											<select name="schBrand" style="width:134px">
												<option value="">전체</option>
												<c:if test="${!empty promotionGoodsVO.goodsBrandList && fn:length(promotionGoodsVO.goodsBrandList)>0}">
													<c:forEach items="${promotionGoodsVO.goodsBrandList}" var="list">
														<option value="${list.sno}" ${promotionGoodsVO.schBrand eq list.sno ? 'selected' : ''}>${list.brandnm}</option>
													</c:forEach>
												</c:if>					
											</select>
										</td>
									</tr>
									<tr>
										<td>재고</td>
										<td>
											<select name="schSoldOut">
												<option value="">전체</option>
												<option value="Y" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schSoldOut eq 'Y' ? 'selected' : ''}>유</option>
												<option value="N" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schSoldOut eq 'N' ? 'selected' : ''}>무</option>
											</select>
										</td>
										<td>판매상태</td>
										<td>
											<select name="schOpen">
												<option value="">전체</option>
												<option value="1" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schOpen eq '1' ? 'selected' : ''}>판매중</option>
												<option value="0" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schOpen eq '0' ? 'selected' : ''}>전시대기</option>
												<option value="2" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schOpen eq '2' ? 'selected' : ''}>일시중지</option>
												<option value="3" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schOpen eq '3' ? 'selected' : ''}>영구중지</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>시즌</td>
										<td>
											<select name="schSeasonNm" style="width:134px">
												<option value="">전체</option>
												<c:if test="${!empty promotionGoodsVO.goodsSeasonList && fn:length(promotionGoodsVO.goodsSeasonList)>0}">
													<c:forEach items="${promotionGoodsVO.goodsSeasonList}" var="season">
														<option value="${season}" ${promotionGoodsVO.schSeasonNm eq season ? 'selected' : ''}>${season}</option>
													</c:forEach>
												</c:if>					
											</select>
										</td>
										<td>EU</td>
										<td>
											<select name="schEuYn">
												<option value="">전체</option>
												<option value="Y" ${promotionGoodsVO.schEuYn eq 'Y' ? 'selected' : '' }>EU</option>
												<option value="N" ${promotionGoodsVO.schEuYn eq 'N' ? 'selected' : '' }>非EU</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>검색어</td>
										<td colspan="3">
											<select name="schKey">
												<option value="goodsnm" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schKey eq 'goodsnm' ? 'selected' : ''}>상품명 영문</option>
												<option value="goodsnmKR" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schKey eq 'goodsnmKR' ? 'selected' : ''}>상품명 국문</option>
												<option value="goodsnmCN" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schKey eq 'goodsnmCN' ? 'selected' : ''}>상품명 중문</option>
						
												<option value="simnCd" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schKey eq 'simnCd' ? 'selected' : ''}>Simn</option>
												<option value="binCd" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schKey eq 'binCd' ? 'selected' : ''}>Bin</option>
						
												<option value="goodscd" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schKey eq 'goodscd' ? 'selected' : ''}>상품코드</option>
												<option value="goodsno" ${promotionGoodsVO == null ? '' : promotionGoodsVO.schKey eq 'goodsno' ? 'selected' : ''}>상품번호</option>ㅅ
											</select>
											<input type="text" name="schWord" value="${promotionGoodsVO.schWord}" class="line" style="height:22px">
										</td>
									</tr>
								</table>
								
								<div class="button_top">
									<input type="image" src="/resources/shop/admin/img/btn_search2.gif" />
								</div>
							</form>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</body>
</html>