<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<div class=title title_top>
	<font color=black>스윗트래커 정보</font>
	<span>스윗트래커 API 를 이용하시려면 정보를 입력해주세요.</span> 
</div>

<table class="tb">
	<col class=cellC><col class=cellL>
	<tr>
		<td>
			API Key
		</td>
		<td>
			<input class="lline" type="text" name="sweetTrackerAPIKey" value="${deliveryVO.sweetTrackerAPIKey}" maxlength="100"/>
		</td>
	</tr>
</table>

<div style="padding-top:8px" class="extext">
	* 스윗트래커 회원가입 후 발급받으신 API Key 를 입력해주세요.
</div>
<div style="padding-top:8px" class="extext">
	* API Key 가 입력된 상태라면 스윗트래커 API 를 이용해 배송조회가 진행되며 공란일 시 배송사 등록 시 입력된 URL 을 이용하여 배송조회가 진행됩니다.
</div>
<div style="padding-top:8px" class="extext">
	* 스윗트래커의 유료회원인 경우에만 스윗트래커의 배송조회 템플릿을 사용하실 수 있습니다. 
	(무료 회원이신 경우 배송조회는 월 1000건으로 제한되며 스윗트래커 측의 템플릿이 제공되지 않습니다.)
</div>

<div style="padding-top:8px" >
	<button class="_delivery-companies-update">
		택배사 목록 업데이트
	</button>
</div>

<div style="padding-top:8px" class="extext">
	* 위 버튼을 누르시면 현재 배송사 목록에 스윗트래커의 배송사 목록이 업데이트됩니다. 
</div>

<script>
	$('._delivery-companies-update').on('click', deliveryCompaniesUpdate);
	
	function deliveryCompaniesUpdate (event) {
		event.preventDefault();
		location.href = ctx + '/shop/admin/sweetTracker/companylist';
	}
</script>