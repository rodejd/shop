<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>    
<script src="/resources/shop/data/skin/season2/common.js"></script>
<script type="text/javascript">
	function checkserchbox()
	{
		document.forms['frmList']['searchWord'].value = '${boardVO.searchWord}';
/* 		<c:if test="${!empty(subject)}">
		document.getElementById("search[subject]").checked = true;
		</c:if>
		<c:if test="${empty(subject)}">
		document.getElementById("subejct").checked = false;
		</c:if>
		<c:if test="${!empty(contents)}">
		document.getElementById("contents").checked = true;
		</c:if>
		<c:if test="${empty(contents)}">
		document.getElementById("contents").checked = false;
		</c:if>
		<c:if test="${!empty(all)}">
		document.getElementById("all").checked = true;
		</c:if>
		<c:if test="${empty(all)}">
		document.getElementById("all").checked = false;
		</c:if> */
		
	}
</script>
<script type="text/javascript">
	function commentModify(fnum, nume, cmboolean, comleng)
	{
		var arrCMviewName = ["CMMODIFY_","CMMODIFYCANCEL_","CMMODIFYTXT_","CMMODIFYMEMO_"];
		
		if(cmboolean=="false")
		{
			for(i=0;i< comleng;i++){
				//alert("//"+i);
				if(i==nume){
					document.getElementById(arrCMviewName[0]+fnum+i).style.display = 'none';
					document.getElementById(arrCMviewName[1]+fnum+i).style.display = 'block';
					document.getElementById(arrCMviewName[2]+fnum+i).style.display = 'none';
					document.getElementById(arrCMviewName[3]+fnum+i).style.display = 'block';
				}else{
					document.getElementById(arrCMviewName[0]+fnum+i).style.display = 'block';
					document.getElementById(arrCMviewName[1]+fnum+i).style.display = 'none';
					document.getElementById(arrCMviewName[2]+fnum+i).style.display = 'block';
					document.getElementById(arrCMviewName[3]+fnum+i).style.display = 'none';
				}
			}
		}else{
			document.getElementById(arrCMviewName[0]+fnum+nume).style.display = 'block';
			document.getElementById(arrCMviewName[1]+fnum+nume).style.display = 'none';
			document.getElementById(arrCMviewName[2]+fnum+nume).style.display = 'block';
			document.getElementById(arrCMviewName[3]+fnum+nume).style.display = 'none';
		}
	}

	function commentGo()
	{
		alert("commentGo");
	}

	window.onload = function(){
		scrollBanner();
	}
</script>

<div class="indiv"><!-- Start indiv -->

				<%-- ${bdHeader} --%>
				
				<c:if test="${boadVO.mode == 'default'}">
					<%@ include file="default.jsp"%>
				</c:if>
				 
				<c:if test="${boadVO.mode == 'photo'}">
					<%@ include file="photo.jsp"%>
				</c:if>
				
				
				<c:if test="${boadVO.mode == 'gallery'}">
					<%@ include file="gallery.jsp"%>
				</c:if>
				
				<c:if test="${boadVO.mode == 'webzine'}">
					<%@ include file="webzine.jsp"%>
				</c:if>
				
				
				<%-- ${bdFooter} --%>
				</div>
				
				
<script src='/resources/shop/lib/js/board.js'></script>

<c:if test="${!empty(boardVO.searchWord)}">
<script type="text/javascript">checkserchbox();</script>
</c:if> 