<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>

<%-- div 위치이동 --%>
<script language="javascript" src="/resources/shop/admin/basic/divmove_table.js"></script>
<%--  다이어리 호출 --%>
<!--
<script language="javascript">
	//영문 도메인 체크 부분
	function check_reg_eng_form(){
		var f = document.regist_engine;
		var alpha		= 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
		var numeric		= '1234567890';
		var special		= ' ~!@#$%^&*()_=+|\\{}[];:"\'<>,.?\/';
		var i;
		var chk_checkbox = false;
		var gtld		= document.regist_engine['gtld[]'];
		var cctld		= document.regist_engine['cctld[]'];
		
		if (f.eng_domain.value.charAt(f.eng_domain.value.length-1) == '.') {
			f.eng_domain.value = f.eng_domain.value.substr(0, f.eng_domain.value.length-1);
		}
		
		if (f.eng_domain.value.charAt(0) == '-') {
			alert ("도메인 명은 '-'로 시작할 수 없습니다.");
			f.eng_domain.focus();
			return false;
		}
		if (f.eng_domain.value.charAt(f.eng_domain.value.length-1) == '-') {
			alert ("도메인 명은 '-'로 끝날 수 없습니다.");
			f.eng_domain.focus();
			return false;
		}
		if (f.eng_domain.value.length < 2 || f.eng_domain.value.length > 63) {
			alert ("도메인 명은 2자 이상 63자 이하로 구성됩니다.");
			f.eng_domain.focus();
			return false;
		}
		
		if (checknorm_nomsg(f.eng_domain,  '도메인명', alpha, 63) == false) {
			alert("도메인 명은 소문자로만 넣어주시기 바랍니다.");
			f.eng_domain.focus();
			return false;
		}
		
		if (checknorm_nomsg(f.eng_domain,  '도메인명', special, 63) == false) {
			alert("도메인 명에 특수문자는 '-' 외에는 허용하지 않습니다");
			f.eng_domain.focus();
			return false;
		}
	
		if(f.eng_domain.value.length > 3) {
			if(f.eng_domain.value.substring(0, 4).indexOf('bq--') >= 0) {
				alert("bq--는 예약어 도메인명 입니다.");
				f.eng_domain.focus();
				return false;
			}
			if(f.eng_domain.value.substring(0, 4).indexOf('xn--') >= 0) {
				alert("xn--는 부적합한 도메인명 입니다.");
				f.eng_domain.focus();
				return false;
			}
		}
		
		for(i = 0; i < gtld.length ; i++) {
			if (gtld[i].checked)
			{
				chk_checkbox = true;
			}
		}
		for(i = 0; i < cctld.length ; i++) {
			if (cctld[i].checked)
			{
				chk_checkbox = true;
			}
		}
		
		if(chk_checkbox == true) {
			f.action = "http://domain.godo.co.kr/regist/domain_search.jsp";
			f.target = "_blank";
		} else {
			alert("등록을 원하는 도메인 종류를 선택해 주시기 바랍니다..");
			return false;
		}
	}
	
	// 유효성 체크
	function checknorm_nomsg(target, cmt, astr, lmax) {
		var i;
		var t = target.value;
		
		if (t.length >= 1) {
			for (i=0; i<astr.length; i++){
				if(t.indexOf(astr.charAt(i)) >= 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	// 도메인만 입력하도록 체크하는 함수(KEY EVENT)
	function onlyDom(){
		if( (event.keyCode>=65 && event.keyCode<=90) || (event.keyCode>=48 && event.keyCode<=57) || (event.keyCode>=96 && event.keyCode<=105) || (event.keyCode==8) || (event.keyCode==9) || (event.keyCode==37) || (event.keyCode==39) || (event.keyCode==46) || (event.keyCode==13) || (event.keyCode==108) || (event.keyCode==189) || (event.keyCode==109));
		else event.returnValue=false;
	}
	
	function reset_a(obj)
	{
		if (obj.value=="영문 도메인 입력") obj.value = "";
	}
	
	function domain_hidden(id){
		var obj =  document.getElementById(id);
		if( obj.style.display == 'block' ) obj.style.display = 'none';
		else obj.style.display = 'block';
	}
</script>
-->


<!-------------------- 서브 본문 본격적으로 시작  ------------------------------->
<%--
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding:30px;">
	
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="100%" valign="top" align="center">

					<!------------  메인본문 시작 -------------------------------------->

					<div id="s2maxtop"><!-- <script>panel('s2maxtop', 'basic');</script> --></div>

					<!--------------  가운데 백그라운드 시작 ------------------->

					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="100%" align="center">

								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td width="100%" align="center" bgcolor="white">
											<c:if test="${'Y' == indexVO.birthDayYn}" >
											<!--------------  오늘 생일자 시작 ------------------->
												<table cellpadding="0" cellspacing="0" border="0" width="100%">
													<tr>
														<td style="padding-bottom:10px; padding-top:8px" align="center">
															<table cellpadding="0" cellspacing="0" border="0">
																<tr>
																	<td width="10px"></td>
																	<td><img src="/resources/shop/admin/img/icon_birthday.gif" align="absmiddle" /></td>
																	<td style="padding-left:5px;padding-top:2px" class="end1">오늘 생일자 : <font class="ta8"><b><?=$birth['total']['cnt']?></b></font> 명</td>
																	<td width="20px"></td>
																	<td><img src="/resources/shop/admin/img/icon_smsok.gif" align="absmiddle" /></td>
																	<td style="padding-left:5px;padding-top:2px" class="end1"><font class="ta8">SMS</font> 전송가능 회원 : <font class="ta8"><b><?=($birth['total']['cnt']-$birth['sendN']['cnt'])?></b></font> 명</td>
																	<td width="20px"></td>
																	<td><img src="/resources/shop/admin/img/icon_smsno.gif" align="absmiddle" /></td>
																	<td style="padding-left:5px;padding-top:2px" class="end1"><font class="ta8">SMS</font> 전송불가능 회원 : <font class="ta8"><b><?=$birth['sendN']['cnt']?></b></font> 명</td>
																	<td width="20px"></td>
																	<td><a href="../member/list?smsyn=y&birthtype=s&birthdate[0]=<?=date('md')?>&mobileYN=y"><img src="/resources/shop/admin/img/btn_smsmember.gif" align="absmiddle" /></a></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</c:if>
											<!--------------  메인현황 시작 ------------------->
											<script language="javascript" src="/resources/shop/admin/basic/main.state.js"></script>
											<div id="Main_State_DisplayID" style="width:100%;">
											<!-- <img src="../img/loading.gif" /> -->
												<table cellpadding="0" cellspacing="0" border="0" width="100%">
													<tr>
														<td width="100%" colspan="10" style="background:#666;">
														
															<table cellpadding="0" cellspacing="0" border="0" width="100%">
																<tr align="center">
																	<td width="20%" class="left" style="padding:5px 10px"></td>
																	<td width="13%" class="ta8 white">${dateUtil:getDateFrom("MM/dd", -2)}</td>
																	<td width="13%" class="ta8 white">${dateUtil:getDateFrom("MM/dd", -1)}</td>
																	<td width="13%" class="ta8 white">${dateUtil:getDate("MM/dd")}<font class="greenp ta7"><b>today</b></font></td>
																	<td width="13%" class="end1 white">최근<font class="ta8">1</font>주</td>
																	<td width="13%" class="end1 white"><font class="ta8">30</font>일</td>
																	<td width="15%" class="end1 white">전체</td>	
																</tr>
															</table>
														</td>
													</tr>
											
												    <tr>
												    	<td>
															<table cellpadding="4" cellspacing="0" border="0" class="mcont" width="100%">
																<c:if test="${'on' == indexVO.arrState[0]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[0]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales3)}</td>
														 				<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.salest)}</td>
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[1]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[1]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.ordert)}</td>	
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[2]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[2]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.depositt)}</td>	
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[3]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[3]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deliveryt)}</td>	
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[4]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[4]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancelt)}</td>	
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[5]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[5]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexReview.reviewt)}</td>	
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[6]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[6]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qnat)}</td>	
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[7]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[7]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqnat)}</td>	
																	</tr>
																</c:if>
																<c:if test="${'on' == indexVO.arrState[8]}">
																	<tr align="center">
																		<td width="20%" style="padding-top:6px; padding-left:8px" class="end1 left">${indexVO.arrTitle[8]}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member3)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member2)}</td>
																		<td width="13%" class="ta8"><b>${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member1)}</b></td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member4)}</td>
																		<td width="13%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member5)}</td>
																		<td width="15%" class="ta8">${stringUtil:getMoneyFormatInteger(indexVO.indexMember.membert)}</td>	
																	</tr>
																</c:if>
															</table>
														</td>
													</tr> 
												</table>
											</div>
											<script>
											//	NowMainDisplay.inData();
											</script>
											<!--------------  메인현황 끝 ------------------->

											<!--------------  부가서비스 현황 시작 ------------------->
											<!-- <div style="height:20px"></div>
											<script>
												function SubMenu(mode,type) {
													var dnm = 'hint_' + mode;
													var div = eval("document.all." + dnm);
													if(type=='open'){
														div.style.display = "block";
													}else if (type == 'over') {
														div.style.display = "block";
													}else if (type == 'out') {
														div.style.display = "none";
													}
												}
											</script> -->

											<!-- BANNER 부가서비스 확인불가<div id="s2maxservice"><script>panel('s2maxservice', 'basic');</script></div> -->
											<!-- <div style="height:20px"></div> -->
											<!--------------  부가서비스 현황 끝 ------------------->
										</td>
									</tr>
								</table>

								<div style="height:30px"></div>


								<!--------------  매출퀵뷰에서 부가서비스까지 끝 ------------------->

								<!----------------------- 1:1게시판, 문의게시판, 후기게시판 ------------------------>
								 <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
									<tr>
										<td valign="top">
											<table width="100%" cellpadding="0" cellspacing="0" border="0" class="mcont">
												<tr>
													<td width=50% class="tit"><a href="${ctx}/shop/admin/board/member_qna/list">1:1문의</a></td>
													 <td width=33% class="tit"><a href="${ctx}/shop/admin/board/cooperation/list">제휴문의</a></td>
													<td width=33% class="tit"><a href="${ctx}/shop/admin/board/goods_review/list">상품평</a></td>
												</tr>
												<tr>
													<td style="padding:5px">
													<c:if test="${!empty indexVO.indexMqnaList and 0 < fn:length( indexVO.indexMqnaList ) }">
														<table border="0" cellpadding="0" cellspacing="0">
															<c:forEach items="${indexVO.indexMqnaList}" var="indexMqna" >
																<tr><td height="5" colspan="2"></td></tr>
																<tr>
																	<td valign="top"><img src="/resources/shop/admin/img/icon_list1.gif" align="absmiddle" /></td>
																	<td>
																		<a href="../board/member_qna/list"><font class="small1" color="#676767">${stringUtil:strlen(indexMqna.subject, 37)}</a>
																	</td>
																</tr>
															</c:forEach>
														</table>
													</c:if>
													</td>
													<td style="padding:5px">
														<c:if test="${!empty indexVO.indexCooperationList and 0 < fn:length( indexVO.indexCooperationList ) }">
															<table border="0" cellpadding="0" cellspacing="0">
																<c:forEach items="${indexVO.indexCooperationList}" var="indexCooperation" >
																	<tr><td height="5" colspan="2"></td></tr>
																	<tr>
																		<td valign="top"><img src="/resources/shop/admin/img/icon_list1.gif" align="absmiddle" /></td>
																		<td><a href="../board/cooperation/list"><font class="small1" color="#676767">${stringUtil:strlen(indexCooperation.title, 37)}</a></td>
																	</tr>
																</c:forEach>
															</table>
														</c:if>
													</td>
													
													<td style="padding:5px">
														<c:if test="${!empty indexVO.indexGoodsReviewList and 0 < fn:length( indexVO.indexGoodsReviewList ) }">
															<table border="0" cellpadding="0" cellspacing="0">
																<c:forEach items="${indexVO.indexGoodsReviewList}" var="indexGoodsReview" >
																	<tr><td height="5" colspan="2"></td></tr>
																	<tr>
																		<td valign="top"><img src="/resources/shop/admin/img/icon_list1.gif" align="absmiddle" /></td>
																		<td><a href="../board/goods_review/list"><font class="small1" color="#676767">${stringUtil:strlen(indexGoodsReview.contents, 37)}</a></td>
																	</tr>
																</c:forEach>
															</table>
														</c:if>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<div style="height:30px"></div>

								<!-------- 최근 등록한 상품, 진행중인 이벤트 ------------------------>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
									<tr>
										<td valign="top">
											<table width="100%" cellpadding="0" cellspacing="0" border="0"  class="mcont">
												<tr>
													<td width=100% class="tit"><a href="${ctx}/shop/admin/goods/list">최근 등록한 상품</a></td>
													<td width=50% class="tit"><a href="${ctx}/shop/admin/event/list">진행중인 이벤트</a></td>
												</tr>
												<tr>
													<td valign="top" style="padding:5px">
														<c:if test="${!empty indexVO.indexRecentGoodsRegList and 0 < fn:length( indexVO.indexRecentGoodsRegList ) }">
															 <table border="0" cellpadding="0" cellspacing="0">
																 <c:forEach items="${indexVO.indexRecentGoodsRegList}" var="indexRecentGoodsReg" >
																	<tr><td height="5" colspan="2"></td></tr>
																	<tr>
																		<td valign="top"><img src="/resources/shop/admin/img/icon_list1.gif" align="absmiddle" /></td>
																		<td>
																			<a href="${ctx}/shop/admin/goods/register?mode=modify&goodsno=${indexRecentGoodsReg.goodsno}" target="_blank">
																				${stringUtil:strlen(indexRecentGoodsReg.goodsnm, 57)}
																			</a>
																		</td>
																	</tr>
																</c:forEach>
															</table>
														</c:if>
													</td>
												</tr>
											</table>

										</td>
									</tr>
								</table>

								<div style="height:30px"></div>

								<!-------- 한주간 많이 팔린 상품, 단골고객 ------------------------>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
									<tr>
										<td valign="top">
											<table width="100%" cellpadding="0" cellspacing="0" border="0" class="mcont">
												<tr>
													<td width="50%" class="tit"><a href="${ctx}/shop/admin/log/popu/goods">한주간 많이 팔린 상품</a></td>
													<td width="50%" class="tit"><a href="${ctx}/shop/admin/member/list">단골고객</a></td>
												</tr>
												<tr>
													<td valign="top" style="padding:5px" align="center">
														<c:if test="${!empty indexVO.indexWeekSoldGoodsList and 0 < fn:length( indexVO.indexWeekSoldGoodsList ) }">
															<table width="96%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<c:forEach items="${indexVO.indexWeekSoldGoodsList}" var="indexWeekSoldGoods" varStatus="status">
																		<c:set var="goodsimg" value="${fn:substring(indexWeekSoldGoods.imgs, fn:indexOf(indexWeekSoldGoods.imgs, '|') + 1, fn:length(indexWeekSoldGoods.imgs))}" />
																		
																		<td valign="top" align="center" width=304>
																			<table cellpadding=0 cellpadding=0 border=0>
																				<tr>
																					<td align="center" width="33%" style="font:13px tahoma;letter-spacing:0px">${status.count}</td>
																				</tr>
																				<tr>
																					<td align="center" width="33%"><a href="javascript:popup('../goods/register?mode=modify&viewFlg=view&goodsno=${indexWeekSoldGoods.goodsno}',825,600)">${shopLibFunction:goodsimg(goodsimg, '50', '', 4)}</a></td>
																				</tr>
																				<tr>
																					<td align="center" style="padding-top:7px"><font class="small1" color="#676767">${indexWeekSoldGoods.goodsnm}</font></td>
																				</tr>
																			</table>
																		</td>
																	</c:forEach>
																</tr>
															</table>
														</c:if>
													</td>
													<td valign="top" style="padding:5px">
														<c:if test="${!empty indexVO.indexWeekSoldGoodsList and 0 < fn:length( indexVO.indexWeekSoldGoodsList ) }">
															<table width="100%" border="0" cellpadding="0" cellspacing="0">
																<c:forEach items="${indexVO.indexRegularMemberList}" var="indexRegularMember" varStatus="status">
																	<tr><td height="5" colspan="2"></td></tr>
																	<tr>
																		<td>
																			<table width="100%" border="0" cellpadding="0" cellspacing="0">
																				<tr>
																					<td width="50%">
																						<img src="../img/icon_list1.gif" align="absmiddle" /><a href="javascript:popupLayer('../member/crm_view?mid=${indexRegularMember.mid}',780,600);"><font class="small1" color="#676767">${indexRegularMember.name}</font></a> <font class="ver71" color="#676767">(${indexRegularMember.mid})</font>
																					</td>
																					<td width="30%" align="right"><font class="ver71" color="#676767"><font class=small1>₩</font>${stringUtil:getMoneyFormatInteger(indexRegularMember.price)}</font></td>
																					<td width="20%" align="right"><font class="ver71" color="#676767">${indexRegularMember.cnt}<font class=small1> 회주문&nbsp;</font></font></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</c:forEach>
															</table>
														</c:if>
													</td>
												</tr>
											</table>

										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<script>// calender('not','f.now'); //일정관리 호출!!	</script>

					<!-- BANNER 디자인샵 리스트 6개 하드코딩 <div id="s2maxbottom"><script>panel('s2maxbottom', 'basic');</script></div> -->

				</td>
				<!-------------------------------- 본문 끝 ------------------------------->

				<!------------------------------ 오른쪽 시작 ------------------------------->
				<td valign="top" height="100%">
					<!-- BANNER 좌측 통합배너 <div id="s2maxright"><script>panel('s2maxright', 'basic');</script></div> -->
				</td>

				<form name="fm_memo" method="post" action="indb.jsp" target="ifrmHidden">
					<input type="hidden" name="mode" value="memo" />

					<td valign="top" width="100%" height="100%" background="/resources/shop/admin/img/note_center_back.gif">

						<!------------------------------ 메모 시작 ------------------------------->
						<!-- <table width="245" height="100%" cellpadding="0" cellspacing="0" border="0" background="../img/note_in_back.gif">
							<tr valign="top">
								<td valign="top" colspan="3" height="100%">


									<table cellpadding="0" cellspacing="0" border="0" height="100%">
										<tr>
											<td colspan="3"><img src="../img/top_note.gif" /></td>
										</tr>
										<tr>
											<td><img src="../img/note_no.gif" /></td>
											<td><img src="../img/note_del.gif" onclick="document.getElementsByName('miniMemo')[0].value='';" style="cursor:pointer;" /></td>
											<td><input type="image" src="../img/note_save.gif" class="null" /></td>
										</tr>
										<tr>
											<td colspan="3"><img src="../img/note_memo.gif" /></td>
										</tr>
										<tr>
											<td colspan="3" align="center" valign="top" height="100%" style="padding-top:4px">
											- 최종저장 : <font style="font:10px tahoma;letter-spacing:0px"><b><? if ( @filectime("../../conf/mini_memo.jsp") ) echo " ".date("Y-m-d H:i:s",@filectime("../../conf/mini_memo.jsp"))." ";?></b></font>-
											<textarea name="miniMemo" style="background-color:transparent;overflow-y:hidden;border:0px;color:#333333;line-height:23px;width:200px;height:100%;"><? @include "../../conf/mini_memo.jsp";?></textarea>
											</td>
										</tr>
									</table>

								</td>
							</tr>
							<tr>
								<td colspan="3" valign="bottom" height="15"><img src="../img/note_b01.gif" /></td>
							</tr>
							<tr valign="bottom" height="15">
								<td><img src="../img/note_del_bottom.gif" onclick="document.getElementsByName('miniMemo')[0].value='';" style="cursor:pointer;" /></td>
								<td><input type="image" src="../img/note_save_bottom.gif" class="null" /></td>
								<td><img src="../img/note_b02.gif" /></td>
							</tr>
						</table> -->
					</td>
				</form>
			</tr>
		</table>
		
		<!-- // 자동문취소 팝업 -->
		<c:if test="${0 < indexVO.autoCancel}">
			<!-- <script>popupLayer('../proc/popup.autoCancel.jsp',500,300);</script> -->
		</c:if>

	<!-- TODO ] AS-IS 소스 -->
<%

	// 자동문취소 팝업
	sb = new StringBuffer()
	.append("select count(*) from gd_order where date_format(orddt,'%Y%m%d') <= date_format(now(),'%Y%m%d') and step='0' and step2='0'");
	rtList = dbHandler.executeQuery(sb.toString());
	if ( !rtList.hasNoEntity() ) {
		//out.println("<script>popupLayer('../proc/popup.autoCancel.jsp',500,300);</script>");
	}
//		include "../_footer.jsp"; ?>
//		<?if(isset($_COOKIE['maxpopup']) === false && file_exists('../../conf/license.cfg.jsp') ){?>
//		<div id="s2maxpopup"><script>panel('s2maxpopup', 'basic');</script><div>
//		<?}?>
%>
<%@ include file="../common/popup.bottom.jsp" %>
	</td>
</tr>
</table>
--%>


<%--최상위 wrapper--%>
<div class="main-wrap">
	<!-- 본문 wrapper -->
	<div class="cont-wrap">
		<!-- 날짜 -->
		<div class="sub-cont-wrap">
			<table class="stat-tbl">
				<thead>
					<tr>
						<th></th>
						<th>${dateUtil:getDateFrom("MM/dd", -2)}</th>
						<th>${dateUtil:getDateFrom("MM/dd", -1)}</th>
						<th>
							<span class="today">${dateUtil:getDate("MM/dd")}</span>
						</th>
						<th>최근 <span style="color: var(--strong-color); padding: 0 2px;">1</span>주</th>
						<th><span style="color: var(--strong-color); padding: 0 2px;">30</span>일</th>
						<th>전체</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${'on' == indexVO.arrState[0]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[0]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.sales5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.salest)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[1]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[1]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.order5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.ordert)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[2]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[2]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deposit5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.depositt)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[3]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[3]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.delivery5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.deliveryt)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[4]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[4]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancel5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexSalepr.cancelt)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[5]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[5]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexReview.review5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexReview.reviewt)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[6]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[6]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qna5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexQna.qnat)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[7]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[7]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqna5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMqna.mqnat)}</td>
					</tr>
				</c:if>
				<c:if test="${'on' == indexVO.arrState[8]}">
					<tr>
						<td class="title-left">${indexVO.arrTitle[8]}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member3)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member2)}</td>
						<td><b>${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member1)}</b></td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member4)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMember.member5)}</td>
						<td>${stringUtil:getMoneyFormatInteger(indexVO.indexMember.membert)}</td>
					</tr>
				</c:if>
				</tbody>
			</table>

		</div>	<!-- END sub-cont -->

		<!-- 문의, 상품평 -->
		<div class="sub-cont-wrap">
			<table class="stat-tbl">
				<thead>
					<tr>
						<th><a href="${ctx}/shop/admin/board/member_qna/list">1:1 문의</a></th>
						<th>제휴문의</th>
						<th><a href="${ctx}/shop/admin/board/goods_review/list">상품평</a></th>
					</tr>
				</thead>
				<tbody style="text-align: left;">
				<tr>
					<!-- 1:1 문의 -->
					<td>
						<c:if test="${!empty indexVO.indexMqnaList and 0 < fn:length( indexVO.indexMqnaList ) }">
							<ul class="list-ul board-list">
								<c:forEach items="${indexVO.indexMqnaList}" var="indexMqna" >
									<li class="list-ul board-list">
										<a href="../board/member_qna/list">${stringUtil:strlen(indexMqna.subject, 37)}</a>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</td>

					<!-- 제휴문의 -->
					<td>
						<c:if test="${!empty indexVO.indexCooperationList and 0 < fn:length( indexVO.indexCooperationList ) }">
							<ul class="list-ul board-list">
								<c:forEach items="${indexVO.indexCooperationList}" var="indexCooperation" >
									<li>
										<a href="#">${stringUtil:strlen(indexCooperation.title, 37)}</a>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</td>

					<!-- 상품평 -->
					<td>
						<c:if test="${!empty indexVO.indexGoodsReviewList and 0 < fn:length( indexVO.indexGoodsReviewList ) }">
							<ul class="list-ul board-list">
								<c:forEach items="${indexVO.indexGoodsReviewList}" var="indexGoodsReview" >
									<li>
										<a href="../board/goods_review/list">${stringUtil:strlen(indexGoodsReview.contents, 37)}</a>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</td>
				</tr>
				</tbody>
			</table>
		</div>	<!-- END sub-cont -->

		<!-- 최근 등록한 상품 -->
		<div class="sub-cont-wrap">
			<table class="stat-tbl">
				<thead>
					<tr>
						<th class="title-left">최근 등록한 상품</th>
					</tr>
				</thead>
				<tbody style="text-align: left;">
				<tr>
					<td>
						<c:if test="${!empty indexVO.indexRecentGoodsRegList and 0 < fn:length( indexVO.indexRecentGoodsRegList )}">
							<ul class="list-ul board-list" style="line-height: 1.3;">
								<c:forEach items="${indexVO.indexRecentGoodsRegList}" var="indexRecentGoodsReg" >
									<li>
										<a href="${ctx}/shop/admin/goods/register?mode=modify&goodsno=${indexRecentGoodsReg.goodsno}" target="_blank">
												${stringUtil:strlen(indexRecentGoodsReg.goodsnm, 57)}
										</a>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</td>
				</tr>
				</tbody>
			</table>
		</div>	<!-- END sub-cont -->

		<!-- 한주간 많이 팔린 상품, 단골고객 -->
		<div class="sub-cont-wrap">
			<table class="stat-tbl" style="text-align: left;">
				<thead>
					<tr>
						<th class="title-left">한주간 많이 팔린 상품</th>
						<th class="title-left">단골고객</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<!-- 주간 베스트 -->
					<td>
						<c:if test="${!empty indexVO.indexWeekSoldGoodsList and 0 < fn:length( indexVO.indexWeekSoldGoodsList ) }">
							<ul>
								<c:forEach items="${indexVO.indexWeekSoldGoodsList}" var="indexWeekSoldGoods" varStatus="status">
									<c:set var="goodsimg" value="${fn:substring(indexWeekSoldGoods.imgs, fn:indexOf(indexWeekSoldGoods.imgs, '|') + 1, fn:length(indexWeekSoldGoods.imgs))}" />
									<li>
										<span>${status.count}</span>
										<a href="javascript:popup('../goods/register?mode=modify&viewFlg=view&goodsno=${indexWeekSoldGoods.goodsno}',825,600)">${shopLibFunction:goodsimg(goodsimg, '50', '', 4)}</a>
										<span><font class="small1" color="#676767">${indexWeekSoldGoods.goodsnm}</font></span>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</td>

					<!-- 단골 -->
					<td>
						<c:if test="${!empty indexVO.indexWeekSoldGoodsList and 0 < fn:length( indexVO.indexWeekSoldGoodsList ) }">
							<ul>
								<c:forEach items="${indexVO.indexRegularMemberList}" var="indexRegularMember" varStatus="status">
									<li>
										<img src="../img/icon_list1.gif" alt=""/>
										<a href="javascript:popupLayer('../member/crm_view?mid=${indexRegularMember.mid}',780,600);">
											<font class="small1" color="#676767">${indexRegularMember.name}</font>
										</a>
										<font class="ver71" color="#676767">(${indexRegularMember.mid})</font>
										<font class="ver71" color="#676767"><font class=small1>₩</font>${stringUtil:getMoneyFormatInteger(indexRegularMember.price)}</font>
										<font class="ver71" color="#676767">${indexRegularMember.cnt}<font class="small1"> 회주문&nbsp;</font></font>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</td>
				</tr>
				</tbody>

			</table>
		</div>	<!-- END sub-cont -->

	</div>	<!-- END cont -->

	<!-- 오른쪽이라는데 무슨 데이터인지, 어떻게 나오는지 확인 필요 -->
	<%--
	<div>
		<!-- BANNER 좌측 통합배너 -->
		<div id="s2maxright"><script>panel('s2maxright', 'basic');</script></div>
	</div>
	--%>

	<!-- 메모라는데 무슨 기능인지 확인 필요 -->
	<!--
	<form name="fm_memo" method="post" action="indb.jsp" target="ifrmHidden">
		<input type="hidden" name="mode" value="memo" />
	</form>
	-->

	<div class="footer-wrap">
		<%@ include file="../common/popup.bottom.jsp" %>
	</div>	<!-- END footer -->

</div>	<!-- END cont-wrap -->