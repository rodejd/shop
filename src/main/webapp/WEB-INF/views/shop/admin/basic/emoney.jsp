<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 
	
<c:set var="kMax" value="0"></c:set>
<c:if test="${fn:indexOf(emoneyVO.max[0],'%') >-1}">
	<c:set var="kMax" value="1"></c:set>
</c:if>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 화면 시작
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	function chkval(){
		if($("input[name=chkGoodsEmoney]").is(":checked")){
			var emoney = $("input[type=radio][name=chkGoodsEmoney]:checked").next().val();
			
			if(!emoney){
				alert("적립금액을 입력해주세요");
				$("input[type=radio][name=chkGoodsEmoney]:checked").next().focus();
				return false;
			}
		}	
	}
 
	function chkGoodsEmoney(obj1, obj2){
		var obj = document.getElementsByName(obj1);
		var txt = document.getElementsByName(obj2);
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked == true){
				txt[i].style.background = "#ffffff";
				txt[i].readOnly = false;			
			}else{
				txt[i].style.background = "#e3e3e3";
				txt[i].readOnly = true;
				txt[i].value = '';
			}
		}
	}

	$(document).ready(function(){
		$("input[name=chkGoodsEmoney]").click(function(){
			$("input[name=chkGoodsEmoney]").each(function(){
					/* alert($(this).attr("checked")); */
				if($(this).attr("checked") == "checked"){
					$(this).attr("checked",false);
				}else{
					$(this).attr("checked",true);
				}
				if($(this).attr("checked") == "checked"){
					
					$(this).parent().children("input[name=goodsEmoney]").css("background","#ffffff");
					$(this).parent().children("input[name=goodsEmoney]").attr("readonly",false);
					
					/* alert($(this).attr("name")+"Click checked"+$(this).parent().children("input[name=goodsEmoney]").val());  */
				}else{
					
					$(this).parent().children("input[name=goodsEmoney]").css("background","#e3e3e3");
					$(this).parent().children("input[name=goodsEmoney]").attr("readonly",true);
					$(this).parent().children("input[name=goodsEmoney]").val("");
					/* alert($(this).attr("name")+"Click unchecked"+$(this).parent().children("input[name=goodsEmoney]").val()); */ 
				}
			});
		});
		
		$("input:radio[name='useyn']").on('change', function(){
			var useyn = $("input:radio[name='useyn']:checked").val();
			if(useyn == 'n'){
				$("#trEmoney").hide();
				$("#tableEmoney").hide();
				$("#titleEmoney").hide();
			}else{
				$("#trEmoney").show();
				$("#tableEmoney").show();
				$("#titleEmoney").show();
			}
		});
	});
 
	$(function(){
		
	});
</script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
	
			<form id="frm" method="post" action="emoney/indb" onsubmit="return chkForm(this);">
				<input type=hidden name=mode value="emoney">
			
				<div class="title title_top" style="display:none">적립금 지급에 대한 정책<span>회원에게 지급되는 적립금에 대한 정책입니다</span> 
				<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
				</div>
				<table class=tb style="display:none">
					<col class=cellC><col class=cellL>
					<tr height=30>
						<td>적립금 지급여부</td>
						<td class=noline>
						<c:choose>
							<c:when test="${emoneyVO.useyn eq 'y' }">
								<input type=radio name=useyn value='y' checked> 사용
							</c:when>
							<c:otherwise>
								<input type=radio name=useyn value='y' > 사용
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not(emoneyVO.useyn eq 'y' )}">
								<input type=radio name=useyn value='n' checked> 사용안함
							</c:when>
							<c:otherwise>
								<input type=radio name=useyn value='n' > 사용안함
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr id="trEmoney">
						<td>적립금 사용시<br>상품적립금 지급여부</td>
						<td class=noline height=50>
						<c:choose>
							<c:when test="${emoneyVO.limit eq '0' }">
								<div><input type=radio name=limit value='0' checked> 적립금을 사용해도 구매하려는 상품의 적립금을 그대로 지급합니다.</div>
							</c:when>
							<c:otherwise>
								<div><input type=radio name=limit value='0' > 적립금을 사용해도 구매하려는 상품의 적립금을 그대로 지급합니다.</div>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${emoneyVO.limit eq '1' }">
								<div><input type=radio name=limit value='1' checked> 적립금을 사용하면 구매하려는 상품의 적립금을 지급하지 않습니다.</div>
							</c:when>
							<c:otherwise>
								<div><input type=radio name=limit value='1' > 적립금을 사용하면 구매하려는 상품의 적립금을 지급하지 않습니다.</div>
							</c:otherwise>
						</c:choose>
							
							<div class="extext_t">* 회원이 적립금으로 결제하려 할 때 구매할 상품의 적립금도 적립해 줄 것인지를 선택하는 항목입니다. <br>
								예를 들어, 가격이 ₩10,000인 상품(구매하면 ₩100 적립)을 어떤 회원이 적립금 ₩5,000를 이용해서 이 상품을 구매하려 한다면, 그 회원에게 ₩100의 적립을 해줄것인가 정하는 정책입니다. <br>
								* 참고로, 회원의 적립금은 구매 또는 기타 혜택으로 받은 현금성 포인트이므로, 현금과 동일하게 대우하는 것이 좋습니다. 
							</div>
						</td>
					</tr>
				</table>
				<br style="display:none">
				<br style="display:none">
				<div class="title title_top" id="titleEmoney" style="display:none">상품 적립금 지급에 대한 정책<span>회원에게 지급되는 적립금에 대한 정책입니다</span> 
				<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
				</div>
			
				<table class=tb id="tableEmoney" style="display:none">
					<col class=cellC><col class=cellL>
					<tr height=30>
						<td>상품적립금 기본설정</td>
						<td>
							<div style='height:25;padding-top:2'>
							
								<c:choose>
									<c:when test="${emoneyVO.chkGoodsEmoney eq '0' }">
										<input type="radio" name="chkGoodsEmoney" value="0" class="null" checked/> 주문 상품금액의  
										<input type="text" name="goodsEmoney" style="text-align:right" value="${emoneyVO.goodsEmoney[0]}" size=2 label="상품적립금" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" readonly class=rline maxlength="3"> 
										% 를 주문 시 적립합니다.
									</c:when>
									<c:otherwise>
										<input type="radio" name="chkGoodsEmoney" value="0" class="null"  /> 주문 상품금액의 
										<input type="text" name="goodsEmoney" style="text-align:right" value="" size=2 label="상품적립금" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" readonly class=rline maxlength="3"> 
										% 를 주문 시 적립합니다.
									</c:otherwise>
								</c:choose>
								
							</div>
							<div style='height:25;padding-top:2'>
							<c:choose>
								<c:when test="${not(emoneyVO.chkGoodsEmoney eq '0')}">
									<input type="radio" name="chkGoodsEmoney" value="1" class="null"  checked> 주문 상품 당 
									₩<input type="text" name="goodsEmoney" style="text-align:right" value="${emoneyVO.goodsEmoney[0]}" size=7 label="상품적립금" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" readonly class=rline maxlength="10"> 주문 시 적립합니다.
								</c:when>
								<c:otherwise>
									<input type="radio" name="chkGoodsEmoney" value="1" class="null"  > 주문 상품 당 
									₩<input type="text" name="goodsEmoney" style="text-align:right" maxlength="10" value="" size=7 label="상품적립금" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" readonly class=rline> 주문 시 적립합니다.
								</c:otherwise>
							</c:choose>
							</div>
							<div style="padding-top:3"><font class=extext>* 상품등록시 상품마다 각각 개별적인 적립금을 입력할 수도 있습니다.</font></div>
						</td>
					</tr>
				</table>
				<br style="display:none">
				<br style="display:none">
				<div class="title title_top">적립금 사용에 대한 정책<span>회원에게 지급되는 적립금에 대한 정책입니다</span> 
				<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
				</div>

				<!-- 여기부터 수정 -->
				<%--
				<table class=tb>
					<col class=cellC><col class=cellL>
					<tr height=30>
						<td>사용가능 보유적립금</td>
						<td>
							회원이 보유한 적립금이 ₩<input type=text name="hold" value="${emoneyVO.hold}" size=10 option="regNum" label="적립금사용가능액" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class=rline maxlength="10"> 이상이 되어야 결제시 적립금 사용을 가능하게 합니다.
							<div class="extext_t">* 회원의 적립금이 일정액 이상 쌓여 있어야만 적립금 사용이 가능하도록 설정하는 정책입니다.<br>보유 적립금을 너무 높게 책정하게 되면 회원들의 불만이 될 수 있는 소지가 있으므로 합리적으로 정하시기 바랍니다.</div>
						</td>
					</tr>
					<tr height=50>
						<td>적립금사용<br>최소한도액</td>
						<td>
							회원이 적립금으로 결제할 경우 ₩<input type=text name=min value="${emoneyVO.min}" size=10 option="regNum" label="최소한도액" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class=rline maxlength="10"> 미만 금액은 사용하지 못하도록 합니다. <span class=extext>(숫자입력)</span>
						</td>
					</tr>
					
					<tr height=50>
						<td>적립금사용<br>최대한도액</td>
						<td>
							<input type=radio name=kMax value=0 class=null  ${kMax == '0' ? 'checked' : '' }> 회원이 적립금으로 결제시  최대 ₩<input type=text name=max size=10 value="${kMax == '0' ? emoneyVO.max[0] : '' }" option="regNum" label="적립금사용한도" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class=rline maxlength="10"> 까지 사용가능하게 합니다.<span class=extext> (숫자입력)</span><br>
							<input type=radio name=kMax value=1 class=null  ${kMax != '0' ? 'checked' : '' }> 회원이 적립금으로 결제시 구매할 상품가격의 <input type=text name=max size=3 value="${kMax != '0' ? fn:replace(emoneyVO.max[0],'%','') : '' }" option="regNum" label="적립금사용한도" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class=rline maxlength="3"> % 까지 사용가능하게 합니다. <span class=extext>(숫자입력)</span>
							<div class="extext_t">* 적립금 결제시 최소 사용금액과 최대 사용금액을 정합니다. 적립금으로 전액을 결제하게 하려면 100%로 입력하세요. <br>
							* 최대한도액을 %로 할 경우 최소한도액과의 상관관계를 고려하여 신중하게 설정하세요. <br>
							예) 적립금 결제 최소한도액을 ₩10,000으로 하고 최대한도액을 상품가격의 40%로 설정했을 때, 구매할 상품이 ₩20,000라면 적립금으로 결제할 수 있는 최대한도액(40%)은 ₩8,000가 됩니다. <br>
							이 경우 최소한도액(₩10,000)보다 최대한도액(₩8,000)이 적게 되므로 고객은 적립금을 사용할 수 없는 상황이 발생됩니다. <br>
							따라서 고객에게 오해의 소지가 없도록 최소한도액과 최대한도액의 상관관계를 고려하여 설정하시기 바랍니다. </div>
						</td>
					</tr>
			
					<tr height=50>
						<td>적립금 사용기준</td>
						<td>
						<c:choose>
							<c:when test="${emoneyVO.delivery eq '0'}">
								<div><input type=radio name="emoneyDelivery" value="0" class=null checked> 적립금으로 주문시 결제금액에 적립금 주문금액 포함</div>
							</c:when>
							<c:otherwise>
								<div><input type=radio name="emoneyDelivery" value="0" class=null > 적립금으로 주문시 결제금액에 적립금 주문금액 포함</div>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
						<c:when test="${emoneyVO.delivery ne '0'}">
							<div><input type=radio name="emoneyDelivery" value="1" class=null checked> 적립금으로 주문시 결제금액에 적립금 주문금액 비포함</div>
						</c:when>
						<c:otherwise>
							<div><input type=radio name="emoneyDelivery" value="1" class=null > 적립금으로 주문시 결제금액에 적립금 주문금액 비포함</div>
						</c:otherwise>
						</c:choose>
							<div class="extext_t">* 적립금사용기준을 "적립금으로 주문시 결제금액에 적립금 주문금액 비포함"으로 선택 시 배송비 계산시 적립금은 결제금액에 포함되지 않습니다. <br>
							예) 배송비 정책을 결제금액 ₩50,000이상 무료, 미만일 경우에는 ₩2,500의 배송비부과로 설정하였고, 적립금사용기준을 적립금으로 주문시 결제금액에 적립금 주문금액 비포함으로 설정하였고, 주문 총구매금액이 ₩51,000이고 적립금 ₩2,000를 사용하였을 경우 
							실결제금액은 ₩49,000이며 결제금액에 적립금이 비포함되므로 배송비가 부과됩니다.</div>
						</td>
					</tr>
				</table>
				<br>
				<br>
				--%>

				<div class="sub-cont-wrap">
					<div class="div-tbl inp-tbl">
						<div class="row">
							<div class="th w-120">사용가능 보유적립금</div>
							<div>
								회원이 보유한 적립금이 ₩<input type="text" name="hold" value="${emoneyVO.hold}" size=10 option="regNum" label="적립금사용가능액" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="rline" maxlength="10">
								이상이 되어야 결제시 적립금 사용을 가능하게 합니다.
								<div class="extext_t">
									<ul class="list-ul">
										<li>
											회원의 적립금이 일정액 이상 쌓여 있어야만 적립금 사용이 가능하도록 설정하는 정책입니다.
										</li>
										<li>
											보유 적립금을 너무 높게 책정하게 되면 회원들의 불만이 될 수 있는 소지가 있으므로 합리적으로 정하시기 바랍니다.
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="th w-120">적립금사용 최소한도액</div>
							<div>
								회원이 적립금으로 결제할 경우 ₩<input type="text" name="min" value="${emoneyVO.min}" size="10" option="regNum" label="최소한도액" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="rline" maxlength="10">
								미만 금액은 사용하지 못하도록 합니다. <span class="extext">(숫자입력)</span>
							</div>
						</div>
						<div class="row">
							<div class="th w-120">적립금사용 최대한도액</div>
							<div>
								<ul style="list-style: none; padding: 0; line-height: 2em; margin: 0;">
									<li>
										<input type="radio" name="kMax" value="0" class="null"  ${kMax == '0' ? 'checked' : '' }>
										회원이 적립금으로 결제시  최대 ₩<input type="text" name="max" size="10" value="${kMax == '0' ? emoneyVO.max[0] : '' }" option="regNum" label="적립금사용한도" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="rline" maxlength="10">
										까지 사용가능하게 합니다.
										<span class="extext"> (숫자입력)</span>
									</li>
									<li>
										<input type="radio" name="kMax" value="1" class="null"  ${kMax != '0' ? 'checked' : '' }>
										회원이 적립금으로 결제시 구매할 상품가격의
										<input type="text" name="max" size="3" value="${kMax != '0' ? fn:replace(emoneyVO.max[0],'%','') : '' }" option="regNum" label="적립금사용한도" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class="rline" maxlength="3">
										% 까지 사용가능하게 합니다.
										<span class="extext">(숫자입력)</span>
									</li>
								</ul>
								<div class="extext_t">
									<ul class="list-ul">
										<li>
											적립금 결제시 최소 사용금액과 최대 사용금액을 정합니다. 적립금으로 전액을 결제하게 하려면 100%로 입력하세요.
										</li>
										<li>
											최대한도액을 %로 할 경우 최소한도액과의 상관관계를 고려하여 신중하게 설정하세요.
										</li>
										<li>
											예) 적립금 결제 최소한도액을 ₩10,000으로 하고 최대한도액을 상품가격의 40%로 설정했을 때, 구매할 상품이 ₩20,000라면 적립금으로 결제할 수 있는 최대한도액(40%)은 ₩8,000가 됩니다. <br>
											이 경우 최소한도액(₩10,000)보다 최대한도액(₩8,000)이 적게 되므로 고객은 적립금을 사용할 수 없는 상황이 발생됩니다.<br>
											따라서 고객에게 오해의 소지가 없도록 최소한도액과 최대한도액의 상관관계를 고려하여 설정하시기 바랍니다.
										</li>
									</ul>

								</div>
							</div>
						</div>
						<div class="row">
							<div class="th w-120">적립금 사용기준</div>
							<div>
								<ul style="list-style: none; padding: 0; line-height: 2em; margin: 0;">
									<li>
										<label>
											<input type="radio" name="emoneyDelivery" value="0" class="null" ${emoneyVO.delivery eq '0' ? 'checked' : ''}>
											적립금으로 주문시 결제금액에 적립금 주문금액 포함
										</label>
									</li>
									<li>
										<label>
											<input type="radio" name="emoneyDelivery" value="1" class="null" ${emoneyVO.delivery ne '0' ? 'checked' : ''}>
											적립금으로 주문시 결제금액에 적립금 주문금액 비포함
										</label>
									</li>
								</ul>
								<div class="extext_t">
									<ul class="list-ul">
										<li>
											적립금사용기준을 "적립금으로 주문시 결제금액에 적립금 주문금액 비포함"으로 선택 시 배송비 계산시 적립금은 결제금액에 포함되지 않습니다.
										</li>
										<li>
											예) 배송비 정책을 결제금액 ₩50,000이상 무료, 미만일 경우에는 ₩2,500의 배송비부과로 설정하였고, 적립금사용기준을 적립금으로 주문시 결제금액에 적립금 주문금액 비포함으로 설정하였고, 주문 총구매금액이 ₩51,000이고 적립금 ₩2,000를 사용하였을 경우<br>
											실결제금액은 ₩49,000이며 결제금액에 적립금이 비포함되므로 배송비가 부과됩니다.
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>	<!-- END table -->
				</div>	<!-- END sub-cont-wrap -->

				<div class=title>금액절삭관리<span>판매상품의 가격, 적립금, 할인쿠폰, 결제금액 등의 끝자리 단위를 정합니다</span> 
				<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=basic&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a>-->
				</div>

				<!-- 여기부터 수정 -->
				<%--
				<table class=tb>
					<col class=cellC><col class=cellL>
					<tr>
						<td>기본자리수</td>
						<td>
							<input type=text name=cut size=1 value="${emoneyVO.cut}" option="regNum" label="기본자리수" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class=rline maxlength="4"> 자리 이하 절삭 처리
							<div class="extext_t">
								* 숫자로만 입력하세요<br>
								ex) 1자리: ₩0.1, 2자리: ₩0.01, 3자리: ₩0.001
							</div>
						</td>
					</tr>
				</table>
				--%>

				<div class="sub-cont-wrap">
					<div class="div-tbl inp-tbl" style="font-size: 1.1em;">
						<div class="th w-120">기본자리수</div>
						<div>
							<label>
								<input type="text" name="cut" size="1" value="${emoneyVO.cut}" option="regNum" title="기본자리수" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="4"> 자리 이하 절삭 처리
							</label>
							<div class="extext_t">
								<ul class="list-ul">
									<li>
										숫자로만 입력하세요<br>
										ex) 1자리: ₩0.1, 2자리: ₩0.01, 3자리: ₩0.001
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>



				<div class=button>
					<!--
					<input type=image src="/resources/shop/admin/img/btn_register.gif"  onclick =" return chkval();">
					<a href="javascript:history.back()"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
					-->
					<button type="submit" class="admin-btn">등록</button>
					<a href="javascript:history.back()" class="admin-btn cancel">취소</a>
				</div>

			</form>
					
			<div id=MSG03>
				<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
					<tr>
						<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">적립금정책의 <font class=small_ex_point>사용가능한 금액, 사용한도설정</font>은 <font class=small_ex_point>'이용안내페이지'</font>에 고지하시기 바랍니다.</td>
					</tr>
					<tr>
						<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><font class=small_ex_point>금액절삭관리</font>는 적립금, 할인쿠폰의 적용으로 발생되는 결제금액 끝자리 단위를 관리하기 위함입니다.</td>
					</tr>
				</table>
			</div>
			<script>cssRound('MSG03');chkGoodsEmoney('chkGoodsEmoney', 'goodsEmoney');</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>

<script>
var test = $("input:radio[name='useyn']:checked").val();
if(test == 'n'){
	$("#trEmoney").hide();
	$("#tableEmoney").hide();
	$("#titleEmoney").hide();
}
</script>