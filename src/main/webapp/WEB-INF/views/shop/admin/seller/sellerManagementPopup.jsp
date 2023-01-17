<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header_popup.jsp" %>
<link rel="styleSheet" href="/resources/shop/admin/style.css">

<form>

	<div class="title title_top">판매사정보<span></span></div>
		
		<table class="tb" cellpadding="5" cellspacing="0" border="1" bordercolor="#e6e6e6" style="width: 90%; border-collapse: collapse;">
			<colgroup>
				<col class="cellC">
				<col class="cellL">
				<col class="cellL">
				<col class="cellC">
				<col class="cellL">
				<col class="cellL">
			</colgroup>
			<tbody>
			<tr>
				<th>판매사명</th>
				<td colspan=2>
					${managementFM.managementVO.sellerNm}
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th width="15%">판매사코드</th>
				<td colspan=2 width="25%">${managementFM.managementVO.sellerCd}</td>
				<th width="15%">상태</th>
				<td width="25%">
					<span style="marigin-right:10px"></span>
					<!-- 상태(S:승인, R:승인요청, W:대기, X:탈퇴) -->
					<c:if test="${managementFM.managementVO.status eq 'S'}">
						승인
					</c:if>
					<c:if test="${managementFM.managementVO.status eq 'R'}">
						승인요청
					</c:if>
					<c:if test="${managementFM.managementVO.status eq 'W'}">
						대기
					</c:if>
					<c:if test="${managementFM.managementVO.status eq 'X'}">
						탈퇴
					</c:if>
				</td>
			</tr>
			<tr>
				<th width="15%">판매사 아이디</th>
				<td width="25%" style="border-right : 0px;">${managementFM.managementVO.id}</td>
				<td style="border-left : 0px; text-align:right;">
				</td>
				<th></th>
				<td></td>
			</tr>
			</tbody>
		</table>
		
		
		<div style="padding-top:15px"></div>
		
		<div class="title title_top">판매사 기본정보<span></span></div>
		
		<div>
			<table class=tb border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<colgroup>
					<col class="cellC">
					<col class="cellL">
					<col class="cellC">
					<col class="cellL">
				</colgroup>
				<tbody>
				<tr>
					<th width="15%">대표자명</th>
					<td width="25%">${managementFM.managementVO.agentNm}</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th width="15%">사업자등록번호</th>
					<td width="25%" colspan=3>
						${managementFM.managementVO.companyRegNo}
					</td>
				</tr>
				<tr>
					<th width="15%">업태</th>
					<td width="25%">
						${managementFM.managementVO.businessConditions}
					</td>
					<th width="15%">종목</th>
					<td width="25%">
						${managementFM.managementVO.event}
					</td>		
				</tr>
				<tr>
					<th width="15%">결제계좌명의</th>
					<td width="25%" colspan=3>${managementFM.managementVO.accNm}</td>
				</tr>
				<tr>
					<th width="15%">결제은행</th>
					<td width="25%" colspan=3>
							<!-- KB:국민은행 WR:우리은행 SB:신한은행 HN:KEB하나은행 IB:기업은행 NH:농협 SH:수협 -->
							<c:if test="${managementFM.managementVO.bankCd eq 'KB'}">
								KB국민은행
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'WR'}">
								우리은행
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'SB'}">
								신한은행
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'HN'}">
								KEB하나은행
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'IB'}">
								기업은행
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'NH'}">
								NH농협
							</c:if>
							<c:if test="${managementFM.managementVO.bankCd eq 'SH'}">
								수협
							</c:if>
					</td>
				</tr>
				<tr>
					<th width="15%">은행계좌번호</th>
					<td width="25%" colspan=3>
						${managementFM.managementVO.accNo}
					</td>
				</tr>
				<tr>
					<th width="15%">담당자명</th>
					<td width="25%">
						${managementFM.managementVO.managerNm}
					</td>
					<th width="15%">담당자 직위</th>
					<td width="25%">
						${managementFM.managementVO.managerPosition}
					</td>		
				</tr>
				<tr>
					<th width="15%">전화번호</th>
					<td width="25%" colspan=3>
						${managementFM.managementVO.managerTel}
					</td>
				</tr>
				<tr>
					<th width="15%">휴대폰번호</th>
					<td width="25%">
						${managementFM.managementVO.managerHp}
					</td>
					<th width="15%">팩스번호</th>
					<td width="25%">
						${managementFM.managementVO.managerFax}
					</td>
				</tr>
				<tr>
					<th width="15%">이메일</th>
					<td width="25%" colspan=3>
						${managementFM.managementVO.managerEmail}
					</td>
				</tr>
				<tr>
					<th width="15%">홈페이지</th>
					<td width="25% "colspan=3>
						${managementFM.managementVO.homepage}
					</td>
				</tr>
				<tr>
					<th width="15%">주소</th>
					<td width="25%" colspan=3>
			        	<div class="row">
			            	<p>
			                	<div class="col-md-3" style="padding-left:0px;">
							    	${managementFM.managementVO.zipcode}
						    	</div>
			                </p>
			      			<p>
					      		<div class="col-md-6" style="padding-left:0px;">
					      			${managementFM.managementVO.sellerAddr}
					      		</div>
					      		<div class="col-md-6" style="padding-left:0px;">
					      			${managementFM.managementVO.sellerAddrSub}
					      		</div>
			      			</p>
			        	</div>
					</td>
				</tr>
				<tr>
					<th width="15%">기타사항</th>
					<td width="25%" colspan=3>
			        	${managementFM.managementVO.etc}
					</td>
				</tr>
				<tr>
					<th width="15%">관리자메모</th>
					<td width="25%" colspan=3>
			        	${managementFM.managementVO.adminMemo}
					</td>
				</tr>
				<tr>
					<th width="15%">정산주기</th>
					<td width="25%" colspan=3>
						<c:if test="${managementFM.managementVO.settlementCycle eq 'D'}">
							일정산
						</c:if>
						<c:if test="${managementFM.managementVO.settlementCycle eq 'W'}">
							주정산
						</c:if>
						<c:if test="${managementFM.managementVO.settlementCycle eq 'H'}">
							15일정산
						</c:if>
						<c:if test="${managementFM.managementVO.settlementCycle eq 'M'}">
							월정산
						</c:if>
					</td>
				</tr>
				<tr>
					<th width="15%">수수료</th>
					<td width="25%" colspan=3>
						<div class="row">
							<p>
								<c:if test="${managementFM.managementVO.feeDiv eq 'C'}">
									카테고리당 수수료
								</c:if>
								<c:if test="${managementFM.managementVO.feeDiv eq 'S'}">
									판매사당 수수료
								</c:if>
								${managementFM.managementVO.fees} %
							</p>
							
							<p>
								<span class="aster">
									※ 판매사당 수수료로 변경을 하거나 카테고리당 수수료로 변경을 하게되면 <br/>
									그 변경시점부터 등록된 상품들부터 수수료정책이 새롭게 적용됩니다.
								</span>
							</p>
						</div>
					</td>
				</tr>
				</tbody>
			</table>
			
		</div>
		
		<div style="padding-top:15px"></div>
	
	<div style="padding-top:15px"></div>
</form>

<script>
linecss();
table_design_load();
</script>