<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script src="/resources/shop/admin/dynatree/jquery/jquery.js" type="text/javascript"></script>
<script src="/resources/shop/admin/dynatree/jquery/jquery-ui.custom.js" type="text/javascript"></script>
<script src="/resources/shop/admin/dynatree/jquery/jquery.cookie.js" type="text/javascript"></script>

<link href="/resources/shop/admin/dynatree/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
<script src="/resources/shop/admin/dynatree/jquery.dynatree.js" type="text/javascript"></script>

<style>
	#dataTable1 * {
		vertical-align: middle;
	}
</style>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<script>
$(document).ready(function(){
	$("#tree").dynatree({
		initAjax: {
			url: "adminMenuSub.doJson"
			,data:{key:"0_0_0"}
			},

		onActivate: function(node) {
			$("#downmenunm").val("");
			$("#downmenulink").val("");
			$("#menukey").val(node.data.menukey);
			$("#menukeySpan").text(node.data.menukey);
			$("#parentkey").val(node.data.parentkey);
			$("#parentkeySpan").text(node.data.parentmenuname);
			$("#menuname").val(node.data.menuname);
			$("#link").val(node.data.link);
			$("#menunum").val(node.data.menunum);
			$("#menulevel").val(node.data.menulevel);
			$("#menulevelSpan").text(node.data.menulevel);
			$("#dataTable").hide();
			$("#dataTable1").show();
			$("#subBtn").show();
			//2017-08-21 추가 - 메뉴 사용여부
			if(node.data.usemenu == 'y'){
				$("input:radio[name='usemenu'][value='y']").attr("checked", true);	
			} else {
				$("input:radio[name='usemenu'][value='n']").attr("checked", true);	
			}
			//2017-08-21 추가 끝
			
			if(node.data.menulevel==2){
				$(".rowHide1").hide();
			}else{
				$(".rowHide1").show();
			}
			if(node.data.menulevel==1){
				$(".rowHide").show();
				$(".rowHide2").hide();
				$("#downmenulinkDvi").hide();
				
			}else{
				$(".rowHide2").show();
				if(node.data.menulevel>3){
					$(".rowHide").hide();
				}else{
					$(".rowHide").show();
					$("#downmenulinkDvi").show();
				}
			}
			
// 			$("#state").text("" + node + " (" + node.getKeyPath()+ ")"+"("+node.data.key+")");
		},
		onLazyRead: function(node){
			 
			node.appendAjax({
			url: "adminMenuSub.doJson",
			//현재 선택한 항목의 key값(폴더아이디)으로 하위 폴더를 불러옵니다.
			data: { key : node.data.key }
			});
		},
		onPostInit: function(isReloading, isError) {
// 		    console.log(isReloading+"postInit"+isError);
		},
	        postProcess: function () {
// 	            console.log("postProcess");
	        }
	});

});
function indb(type){
	var menukey = $("#menukey").val();
	var parentkey = $("#parentkey").val();
	var menuname = $("#menuname").val();
	var link = $("#link").val();
	var menunum = $("#menunum").val();
	var menulevel = $("#menulevel").val();
	var mode = "update";
	var newmenuname = $("#downmenunm").val();
	var newlink = $("#downmenulink").val();
	//2017-08-21 추가 - 메뉴사용여부
	var usemenu = $("input:radio[name=usemenu]:checked").val();
	//2017-08-21 추가 끝
	if($("#downmenunm").val()!=""){
		mode="register";
	}
	if(type=="delete"){
		if (confirm("하위 메뉴도 함깨 삭제됩니다. 삭제하시겠습니까") == false){ 
		    return;
		}
		mode=type;
	}
	if(menukey==""){
		alert("메뉴를 선택해 주세요.");
		return false;
	}
	ajaxCallJson("adminMenuIndb.doJson"
			, {	menukey : menukey,
				parentkey : parentkey,
				menuname : menuname,
				link : link,
				menunum : menunum,
				menulevel : menulevel,
				mode : mode,
				newmenuname : newmenuname,
				newlink : newlink,
				usemenu : usemenu}
	        , function(result) {
			// 				location.href="adminMenu";
			alert("변경완료 되었습니다.");
			$("#tree").dynatree("getTree").reload();
			//navi 정보
			var url = location.href;
			var urlS = url.split("shop")[1];
			if (urlS.indexOf('func') > -1) {
				if (urlS.indexOf('&') > -1) {
					urlS = urlS.substring(0, urlS.indexOf('&'));
				}
			} else if (urlS.indexOf('type') > -1) {
				if (urlS.indexOf('&') > -1) {
					urlS = urlS.substring(0, urlS.indexOf('&'));
				}
			} else if (urlS.indexOf('?') > -1) {
				urlS = urlS.substring(0, urlS.indexOf('?'));
			}
			$("#downmenunm").val("");
			$("#downmenulink").val("");
			$.ajax({
				type : 'post',
				url : ctx + '/shop/admin/common/navi',
				headers : {
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({
					url : urlS
				}),
				dataType : 'json',
				success : function(result) {
					if (result.length > 0) {
						$("#navi1").html(result[0].menuName);
						$("#navi2").html(result[1].menuName);
					}

				}
			});
		}, function(e) {
			alert("error")
			alert(e.resopnseText);
		});
	}
</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<input type="hidden" name="mode" value="config">
	<div class="title title_top">관리자메뉴관리<span>관리자메뉴관리를 할 수 있습니다.</span> 
	<!--<a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=basic&no=2',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle"></a>-->
	</div>

	<!-- 여기서부터 수정 -->
	<!--
	<table width=100%>
		<tr>
			<td valign=top>
				<div id="tree" style="height:400px;width:300px"></div>
				<div><span id="state">-</span></div>
			</td>
			<td valign=top width=100% style="padding-left:10px">
			<div class="title_sub" style="margin: 0">
				메뉴만들기/수정/삭제<span>메뉴명을 생성하고 수정, 삭제합니다. <font class=extext>(입력후 반드시 아래 수정버튼을 누르세요)</font></span></div>
				<table  id="dataTable" style="display:block">
					<tbody style="height: 26px">
						<tr>
							<td ></td>
						</tr>
						<tr>
							<td >메뉴를 선택해 주세요</td>
						</tr>
					</tbody>
				</table>
				<table class=tb  id="dataTable1" style="display:none">
					<col class=cellC>
					<col class=cellL>
					<tbody style="height: 26px">
						<tr style="display:none">
							<td>메뉴키</td>
							<td><input type=hidden name=menukey id=menukey class=lline required value="" label="메뉴키" maxlen="30"><span id="menukeySpan">-</span></td>
						</tr>
						<tr style="display:none">
							<td>상위 메뉴키</td>
							<td><input type=hidden name=parentkey id=parentkey class=lline required value="" label="상위메뉴키" maxlen="30"><span id="parentkeySpan">-</span></td>
						</tr>
						<tr>
							<td>메뉴명</td>
							<td><input type=text name=menuname id=menuname class=lline required value="" label="메뉴명" maxlen="30"></td>
						</tr>
						<tr class="rowHide1" style="display:none">
							<td>link</td>
							<td><input type=text name=link id=link class=lline required value="" label="link" maxlen="30"><br/></td>
						</tr>
						<tr style="display:none">
							<td>메뉴레벨</td>
							<td><input type=hidden name=menulevel id=menulevel class=lline required value="" label="메뉴레벨" maxlen="30"><span id="menulevelSpan">-</span></td>
						</tr>
						<tr>
							<td>순서</td>
							<td><input type=text name=menunum id=menunum class=lline required value="" label="순서" maxlen="30"></td>
						</tr>
						<tr class="rowHide" style="display:none">
							<td>하위메뉴 만들기</td>
							<td>
								메뉴명&nbsp;:&nbsp;<input type=text name=downmenunm id=downmenunm class=lline required value="" label="하위메뉴명" maxlen="30"><br/>
								<div id="downmenulinkDvi" style="display:none">link&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<input type=text name=downmenunm id=downmenulink class=lline required value="" label="하위메뉴링크" maxlen="30"></div>
							</td>
						</tr>
						<!-- 2017-08-21 추가
						<tr class="rowHide2" style="display:none">
							<td>사용여부</td>
							<td>
							<input type=radio name=usemenu id=usemenu  value="y" label="사용여부" > 사용
							<input type=radio name=usemenu id=usemenu  value="n" label="사용여부" > 사용안함
							</td>
						</tr>
						<!-- 2017-08-21 추가 끝
						<tr class="rowHide2" style="display:none">
							<td>메뉴삭제</td>
							<td><a href="#" onclick="indb('delete');return false;">
							<img src="/resources/shop/admin/img/i_del.gif" border=0 align=absmiddle></a> <font class=extext>메뉴삭제시 하위메뉴도 함께 삭제됩니다. 신중히 삭제하세요.</font></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<div class="button" style="padding-top:20px;width:100%; text-align:center;display:none" id="subBtn">
					<img src="/resources/shop/admin/img/btn_modify.gif" onclick="indb();"style="cursor: hand;">
				</div>
			</td>
		</tr>
	</table>
	-->

	<div class="sub-cont-wrap">
		<div class="sub-cont-wrap" style="display: flex; flex-direction: row; flex-wrap: nowrap; justify-content: flex-start; align-items: flex-start;">
			<!-- tree -->
			<div>
				<div id="tree" style="height:400px;width:300px"></div>
				<div><span id="state">-</span></div>
			</div>
			<!-- 메뉴만들기 -->
			<div style="width: 100%; margin-left: 15px;">
				<div class="sub-title">
					메뉴만들기/수정/삭제
					<span class="title-desc">메뉴명을 생성하고 수정, 삭제합니다.</span>
					<span class="extext">(입력후 반드시 아래 수정버튼을 누르세요)</span>
				</div>
				<!-- 구분선 -->
				<div class="mg-bottom-15" style="border-bottom: 3px solid #cccccc;"></div>

				<div>
					<div id="dataTable">메뉴를 선택해 주세요</div>
					<div id="dataTable1" class="div-tbl inp-tbl" style="display: none;">
						<div class="row" style="display: none;">
							<div class="th w-120">메뉴키</div>
							<div>
								<input type="hidden" name="menukey" id="menukey" class="lline" required value="" label="메뉴키" maxlen="30">
								<span id="menukeySpan">-</span>
							</div>
						</div>

						<div class="row" style="display: none;">
							<div class="th w-120">상위 메뉴키</div>
							<div>
								<input type="hidden" name="parentkey" id="parentkey" class="lline" required value="" label="상위메뉴키" maxlen="30">
								<span id="parentkeySpan">-</span>
							</div>
						</div>

						<div class="row">
							<div class="th w-120">메뉴명</div>
							<div>
								<input type="text" name="menuname" id="menuname" class="lline" required value="" label="메뉴명" maxlen="30">
							</div>
						</div>

						<div class="row rowHide1" style="display: none;">
							<div class="th w-120">link</div>
							<div>
								<input type="text" name="link" id="link" class="lline" required value="" label="link" maxlen="30">
							</div>
						</div>

						<div class="row" style="display: none;">
							<div class="th w-120">메뉴레벨</div>
							<div>
								<input type="hidden" name="menulevel" id="menulevel" class="lline" required value="" label="메뉴레벨" maxlen="30">
								<span id="menulevelSpan">-</span>
							</div>
						</div>

						<div class="row">
							<div class="th w-120">순서</div>
							<div>
								<input type="text" name="menunum" id="menunum" class="lline" required value="" label="순서" maxlen="30">
							</div>
						</div>

						<div class="row rowHide" style="display: none;">
							<div class="th w-120">하위메뉴 만들기</div>
							<div>
								<div style="display: block;">
									메뉴명 : <input type="text" name="downmenunm" id="downmenunm" class="lline" required value="" label="하위메뉴명" maxlen="30"><br/>
								</div>
								<div id="downmenulinkDvi" style="display:none; display: block;">
									link : <input type="text" name="downmenunm" id="downmenulink" class="lline" required value="" label="하위메뉴링크" maxlen="30">
								</div>
							</div>
						</div>

						<!-- 2017-08-21 추가 -->
						<div class="row rowHide2" style="display:none;">
							<div class="th w-120">사용여부</div>
							<div>
								<label><input type="radio" name="usemenu" id="usemenu"  value="y" label="사용여부" > 사용</label>
								<label><input type="radio" name="usemenu" id="usemenu"  value="n" label="사용여부" > 사용안함</label>
							</div>
						</div>
						<!-- 2017-08-21 추가 끝 -->

						<div class="row rowHide2" style="display: none;">
							<div class="th w-120">메뉴삭제</div>
							<div>
<%--								<a href="#" onclick="indb('delete');return false;">--%>
<%--									<img src="/resources/shop/admin/img/i_del.gif" border=0 align=absmiddle>--%>
<%--								</a>--%>
								<a href="#" class="admin-i-del" onclick="indb('delete');return false;">삭제</a>
								<span class="extext">메뉴삭제시 하위메뉴도 함께 삭제됩니다. 신중히 삭제하세요.</span>
							</div>
						</div>

					</div>
				</div>
			</div>	<!-- END 메뉴만들기 -->
		</div>
		<div class="button" style="padding-top:20px;width:100%; text-align:center;display:none" id="subBtn">
<%--			<img src="/resources/shop/admin/img/btn_modify.gif" onclick="indb();" style="cursor: hand;">--%>
			<a href="javascript:indb();" class="admin-btn">수정</a>
		</div>
	</div>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
		</td>
	</tr>
</table>