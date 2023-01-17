<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
    
<script language="javascript">
	function act(target,goodsno,opt1,opt2)
	{
		var form = document.frmCharge;
		
		form.mode.value = "addItem";
		form.goodsno.value = goodsno;
		
		if(opt2) opt1 += opt2;
		document.getElementById("opt").value=opt1;
		
		form.action = target + ".php";
		form.submit();
	}
	function sort(sort)
	{
		var fm = document.frmList;
		fm.sort.value = sort;
		fm.submit();
	}
	function sort_chk(sort)
	{
		if (!sort) return;
		sort = sort.replace(" ","_");
	
		var chg = "";
		var obj = document.getElementsByName('sort_'+sort);
		
		if (obj.length){
			div = obj[0].src.split('list_');
			for (var i=0 ; i < obj.length ; i++){
				chg = ("up_off.gif" == div[1]) ? "up_on.gif" : "down_on.gif";
				obj[i].src = div[0] + "list_" + chg;
			}
		}
	}

	window.onload = function(){
		sort_chk('${ to_sort }<%//= requestSet.getProperty("sort", "price") %>');
		scrollBanner();
	}
	
	function goPage(page){
		$('.pageNo').val(page);
		$('.frmList').submit();
	}
	
	function gsort(sort){	
		$("form[name='frmList']").submit();
	}
</script>
<header class="page-header page-header-banner x-member-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title"> 최근본상품</h1>
        </div>
    </div>
</header>	
		

<form name=frmList class=frmList>

<%-- <input type=hidden name="sort" value="${ to_strSort }"> --%>
<input type="hidden" class="pageNo" name="pageNo" value="${myTodayVO.pageNo != null ? myTodayVO.pageNo : 1 }" />

<div class="x-mypage-today container">
	<div class="tabbable product-tabs">
				<!-- 고객센터 공통탭메뉴 처리 -->
				<jsp:include page="../mypage/mypage_tab_menu.jsp" flush="true">
					<jsp:param name="tab_order" value="10" />
				</jsp:include>
		<div class="col-md-9">
			<div class="row pos_r">
			
					<ul class="category-selections clearfix first">
	         			<li><span class="category-selections-sign">Sort by :</span>
                        <select onchange="javascript:gsort(this.value);" class="category-selections-select" name="sort">
                            <option value="price" <c:if test="${myTodayVO.sort eq 'price' }">selected="selected"</c:if>>가격▲</option>
                            <option value="price desc" <c:if test="${myTodayVO.sort eq 'price desc' }">selected="selected"</c:if>>가격▼</option>
                           <%--  <option value="reserve desc" <c:if test="${sort eq 'reserve desc' }">selected="selected"</c:if>>적립금▲</option>
                            <option value="reserve" <c:if test="${sort eq 'reserve' }">selected="selected"</c:if>>적립금▼</option> --%>
                            <option value="goodsnmKR" <c:if test="${myTodayVO.sort eq 'goodsnmKR' }">selected="selected"</c:if>>상품명▲</option>
                            <option value="goodsnmKR desc" <c:if test="${myTodayVO.sort eq 'goodsnmKR desc' }">selected="selected"</c:if>>상품명▼</option>
                        </select>
                    </li>
                    </ul>
                    <ul class="category-selections clearfix">
                    <li><span class="category-selections-sign">Items :</span>
						<select name=pageSize onchange="this.form.pageSize.value=this.value;this.form.submit()" class="category-selections-select">
							<option value=8 ${stringUtil:selected(myTodayVO.pageSize, "8")}>8개씩 정렬</option>
							<option value=12 ${stringUtil:selected(myTodayVO.pageSize, "12")}>12개씩 정렬</option>
							<option value=16 ${stringUtil:selected(myTodayVO.pageSize, "16")}>16개씩 정렬</option>
							<option value=20 ${stringUtil:selected(myTodayVO.pageSize, "20")}>20개씩 정렬</option>
						</select>
                    </li>
                    </ul>
            		<div class="gap gap-small"></div>
            	</div>
			</div>
		</div>
		
		
		
		<div class="col-md-9">
			<div class="row">
			<font>총 <b>${ myTodayVO.rowCount }</b>개의 상품이 있습니다.</font>
					<div class="gap gap-small"></div>
					<table class="table table table-shopping-cart">
						<colgroup>
							<col>
							<col>
							<col class="table-m3">
							<col class="table-m4">
						</colgroup>
						<tr>
							<th>상품정보</th>
							<th></th>
							<th>적립금</th>
                            <th>가격</th>
						</tr>
						<c:if test="${ !empty(myTodayVO.todayGoodsList) }" >
							<c:forEach items="${myTodayVO.todayGoodsList}"  var="to_gdList"  varStatus="status">
								<tr>
									<td class="table-shopping-cart-title">
										<a href="../goods/goods_view?goodsno=${ to_gdList.goodsno }&category=${to_gdList.category}">
										<img src='${to_gdList.imgs }' width="100px" height="100px" alt="${wi_gdList.goodsnmKR }" onerror=this.src='${shopLibFunction:webSkin(pageContext.request.requestURL) }/img/common/noimg_100.gif'>
										</a>
										</td>
										<td><a href="../goods/goods_view?goodsno=${ to_gdList.goodsno }&category=${to_gdList.category}"><font size="3">${ to_gdList.goodsnmKR }</font></a></td>
										<td>${shopLibFunction:getExchange(shopLibFunction:getReserve(to_gdList.goodsno), wskin)}</td>
									<td>
									<div class="product-caption-price-old">${shopLibFunction:getExchange(to_gdList.consumer, wskin)}</div>
		                            	<span class="product-caption-price-new">${shopLibFunction:getExchange(to_gdList.price, wskin)}</span>
		                            </div>
		                            </td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<div class="col-md-10">
	         <nav class="text-center">
				<ul class="pagination category-pagination">
					<tags:frontPaginator currentPageNo="${myTodayVO.pageNo}" rowCount="${myTodayVO.rowCount}" pageSize="${myTodayVO.pageSize}"  pageGroupSize="${myTodayVO.pageGroupSize}" />
				</ul>
	        </nav>
	        <br>
	    </div>
			</div>
		</div>
		</div>
	</div>
	</form>
	
	<form name=frmCharge method=post>
	<input type=hidden name=mode value="">
	<input type=hidden name=goodsno value="">
	<input type=hidden name=ea value="1">
	<input type=hidden name=opt[] id=opt value="">
	</form>