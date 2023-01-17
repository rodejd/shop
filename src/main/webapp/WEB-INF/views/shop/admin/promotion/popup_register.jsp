<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resources/shop/admin/common.js"></script>
<script src="/resources/shop/admin/design/codi/_codi.js"></script>





<form method="post" name="fm" action="indb" onsubmit="return chkForm( this );">
	<input type="hidden" name="mode" value="${popUpVO.mode}""/>
	<c:if test="${popUpVO.mode eq 'modify' }">
	<input type="hidden" name="sno" value="${popUpVO.popUpObject.sno}">
	</c:if>
	
	<input type="hidden" name="oldfile" value="${popUpVO.popUpObject.filename}"/>
	<div class="title title_top">메인팝업창 등록<span>메인 팝업창에 대한 설정을 추가 변경하실 수 있습니다</span> 
		<!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=design&no=7',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle" /></a> -->
	</div>
	
	<c:if test="${fn:length(popUpVO.errorMsg) >0 }">
		<script>
		$( document ).ready(function() {
			alert("${popUpVO.errorMsg}");
		});
	  	</script>
	</c:if>
	
	<table class="tb">
	<col class="cellC"><col class="cellL">
	<tr>
		<td>팝업제목</td>
		<td><input type="text" name="sub" size="60" value="${popUpVO.popUpObject.sub}" class="line"></td>
	</tr>
	<tr>
		<td>팝업파일명</td>
		<td>
		<font class=ver811><input type="text" id="filename" name="filename" value="${popUpVO.popUpObject.filename}" style="ime-mode:disabled;">.html</font> ※ 사용하고자하는 팝업화일의 화일명을 넣으셔야합니다.(영문만 가능)
		</td>
	</tr>
	<tr>
		<td>출력여부</td>
		<td class="noline">
			<input type="radio" name="popuse" value="Y"  ${popUpVO==null ? '' : popUpVO.popUpObject.popuse eq 'Y' ? 'checked' : '' }/>출력
			<input type="radio" name="popuse" value="N" ${popUpVO==null ? '' : popUpVO.popUpObject.popuse eq 'N' ? 'checked' : '' }/>미출력
		</td>
	</tr>
	<tr>
		<td>창위치</td>
		<td>
		상단에서 : <input type="text" name="popspotw" size="6" class="right line" value="${popUpVO.popUpObject.popspotw}" onkeydown="onlynumber();" required/> <font class=ver811>pixel</font><br />
		좌측에서 : <input type="text" name="popspoth" size="6" class="right line" value="${popUpVO.popUpObject.popspoth}" onkeydown="onlynumber();" required/> <font class=ver811>pixel</font>
		</td>
	</tr>
	<tr>
		<td>창크기</td>
		<td>
		가로크기 : <input type="text" name="popsizew" size="6" class="right line" value="${popUpVO.popUpObject.popsizew}" onkeydown="onlynumber();" required/> <font class=ver811>pixel</font><br />
		세로크기 : <input type="text" name="popsizeh" size="6" class="right line" value="${popUpVO.popUpObject.popsizeh}" onkeydown="onlynumber();" required/> <font class=ver811>pixel</font>
		</td>
	</tr>
	
	<tr>
		<td>특정기간동안<br>무조건 노출</td>
		<td>
		<div class="noline"><input type="radio" name="poppik"  id="poppik" value="term"  ${popUpVO==null ? '' : popUpVO.popUpObject.poppik eq 'term' ? 'checked' : '' } > 특정기간동안 팝업창이 열립니다.</div>
		<div>	
		<input type="hidden" name="popsdt" id="popsdt">
		<input type="hidden" name="popedt" id="popedt">
		<c:set var="repopsdt" value="${popUpVO.popUpObject.repopsdt}"/>
		<c:set var="repopedt" value="${popUpVO.popUpObject.repopedt}"/>
		시작일 : <input type="text" name="popupsdttg" size="10" maxlength="8" class="js_popupsdttg line" value="${fn:substring(repopsdt,0,8) }" onkeydown="onlynumber();" onclick="calendar(event);" /> 
		시작시간 : <input type="text" name="popupstimetg" size="6" maxlength="4" class="line" value="${fn:substring(repopsdt,8,12) }" onkeydown="onlynumber();" /> <font color=627dce class=ver8>ex) 20080415</font>
		<div>
		</div>
		종료일 : <input type="text" name="popupedttg" size="10" maxlength="8" class=" js_popupsdttg line" value="${fn:substring(repopedt,0,8) }" onkeydown="onlynumber();" onclick="calendar(event);" />
		종료시간 : <input type="text" name="popupetimetg" size="6" maxlength="4" class="line" value="${fn:substring(repopedt,8,12) }" onkeydown="onlynumber();" /> <font color=627dce class=ver8>ex) 오전6시: 0600, 밤12시: 2400</font>
		</div>
		</td>
	</tr>
	
	<tr>
		<td>특정기간내에<br>특정시간에만 노출</td>
		<td>
		<div class="noline"><input type="radio" name="poppik"  id="poppik"  value="time"  ${popUpVO==null ? '' : popUpVO.popUpObject.poppik eq 'time' ? 'checked' : '' } > 특정기간동안 특정한 시간에만 팝업창이 열립니다.</div>
		<div>
		
		<input type="hidden" name="popsdate" id="popsdate">
		<input type="hidden" name="popedate" id="popedate">
		<c:set var="repopsdate" value="${popUpVO.popUpObject.repopsdate}"/>
		<c:set var="repopedate" value="${popUpVO.popUpObject.repopedate}"/>
		시작일 : <input type="text" name="popupsdt" size="10" maxlength="8" class="line" value="${fn:substring(repopsdate,0,8) }" onkeydown="onlynumber();" onclick="calendar(event);" />
		종료일 : <input type="text" name="popupedt" size="10" maxlength="8" class="line" value="${fn:substring(repopedate,0,8) }" onkeydown="onlynumber();" onclick="calendar(event);" />
		</div>
		<div>
		오픈시간 : <input type="text" name="popupstime" size="6" maxlength="4" class="line" value="${fn:substring(repopsdate,8,12) }" onkeydown="onlynumber();" />
		클로즈시간 : <input type="text" name="popupetime" size="6" maxlength="4" class="line" value="${fn:substring(repopedate,8,12) }" onkeydown="onlynumber();" /> <font color=627dce class=ver8>ex) 오전6시: 0600, 밤12시: 2400</font>
		</div>
		</td>
	</tr>
	<tr>
		<td>창타입</td>
		<td class="noline">
		<input type="radio" name="poptype" value="noname" checked ${popUpVO==null ? '' : popUpVO.popUpObject.poptype eq 'noname' ? 'checked' : '' } >일반 윈도우팝업창
		<input type="radio" name="poptype" value="move" ${popUpVO==null ? '' : popUpVO.popUpObject.poptype eq 'move' ? 'checked' : '' }>이동레이어
		<input type="radio" name="poptype" value="fix" ${popUpVO==null ? '' : popUpVO.popUpObject.poptype eq 'fix' ? 'checked' : '' }>고정레이어
		</td>
	</tr>
	</table>
	
	<div style="padding-top:10;">
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr valign="top">
		<td>
	<script>
	var filename=$("#filename").val();
	if(filename!=""){
	DCTM.write('content', '100%', '20', ' required label="HTML 소스"', filename);
	}
	
	</script> 
		</td>
	</tr>
	</table>
	
	<div style="padding:20px" align="center" class="noline">
	<input type="image" src="/resources/shop/admin/img/btn_${popUpVO.mode}.gif" alt="저장하기"  onclick="myFunction()" />
	<!-- onclick="myFunction()" -->
	<a href="list"><img src="/resources/shop/admin/img/btn_list.gif" border="0" /></a>
	</div>

</form>

<script>
table_design_load();
setHeight_ifrmCodi();


function myFunction()
{
	
	   size = document.getElementsByName("poppik").length;
	
		var popupsdttg=document.getElementsByName("popupsdttg")[0].value;
		var popupedttg=document.getElementsByName("popupedttg")[0].value;
		var popupstimetg=document.getElementsByName("popupstimetg")[0].value;
		var popupetimetg=document.getElementsByName("popupetimetg")[0].value;
		
		var popupsdt=document.getElementsByName("popupsdt")[0].value;
		var popupedt=document.getElementsByName("popupedt")[0].value;
		var popupstime=document.getElementsByName("popupstime")[0].value;
		var popupetime=document.getElementsByName("popupetime")[0].value;
		
	
       for(var i = 0; i < size; i++) {
            if(document.getElementsByName("poppik")[i].checked) {
                var poppik=document.getElementsByName("poppik")[i].value;
            }
       }
       if(poppik!="time")
    	   if(poppik!="term")
    	   {
    	   alert("특정기간동안 무조건 노출 or 특정기간동안 특정시간 노출 선택하세요")
    		event.preventDefault();
			event.returnValue = false;
    	   }
   if(poppik=="time")
		{
	    	if(popupsdt=="" || popupedt=="" || popupstime=="" || popupetime=="" )
	    		{
	    		alert("시작일 , 종료일 , 시간을 정확하게 입력하세요")
	    		event.preventDefault(); 	// 익스버전 이벤트정지
	    		event.returnValue = false; // 크롬 사파리등 이벤트정지
	    		}
		}
   
       if(poppik=="term")
		{	
			if(popupsdttg=="" || popupedttg=="" || popupstimetg=="" || popupetimetg=="" )
				{
				alert("시작일 , 종료일 , 시간을 정확하게 입력하세요")		
				event.preventDefault();
				event.returnValue = false;
				}
		} 

       
       
       
    
}
</script>
<%@ include file="../common/bottom.jsp" %>