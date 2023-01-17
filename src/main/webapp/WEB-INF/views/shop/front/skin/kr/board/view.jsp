<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<script language="javascript">

		$(function(){
			$('#new_comm').on('click', function(){
					if($('#new_comment').attr("readonly") == "readonly"){
					$("#nonmember_buy_1").attr('style','display:none');
					$("#nonmember_buy_2").attr('style','display:block');
					$(".btn-pop-login").trigger("click");
				};		
			});
			
		}); 

		function commentModify(fnum, nume, cmboolean, comleng)
		{
			var arrCMviewName = ["CMMODIFY_","CMMODIFYCANCEL_","CMMODIFYTXT_","CMMODIFYMEMO_"];
		
		if(cmboolean=="false")
		{
			for(i=0; i < comleng; i++)
			{
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
		function imgReSize(img)
		{
		var width = img.width;
		var maxWidth = 600;
		if(width > maxWidth)
		{
			 img.width = maxWidth;
		}	
		}
		
		window.onload = function(){
		scrollBanner();
		}
		
		//<![CDATA[
		$('.grid').masonry({
		// options
		itemSelector: '.grid-item',
		columnWidth: 300
		});
		//]]>
		
		//댓글 등록
		function addComment(id, no) {
		var name = '';
		var password = '';
		var memo = '';
		
		// 이름 체크
		if ($('input[name=name]').val().trim() == '') {
			alert('이름을 입력해 주세요');
			$('input[name=name]').focus();
			
			return false;
		} else {
			name = $('input[name=name]').val().trim();
		}
		
		// 비밀번호 체크
		if ($('input[name=password]').val().trim() == '') {
			alert('비밀번호를 입력해 주세요');
			$('input[name=password]').focus();
			
			return false;
		} else {
			password = $('input[name=password]').val().trim();
		}
		
		// 메모 체크
		if ($('textarea[name=memo]').val().trim() == '') {
			alert('메모를 입력해 주세요');
			$('textarea[name=memo]').focus();
			
			return false;
		} else {
			memo = $('textarea[name=memo]').val().trim();
		}
		
		ajaxCallJson("cmt.dojson"
				, { 'id' : id,
					'no' : no,
					'name' : name,
					'memo' : memo,
					'password' : password,
					'order' : 'insertMemo'}
				,function(result) {
					location.reload();
				}, function(e){
					console.log(e);
				});
		}
		
		
		var tmpFile = '';
		var tmpPattern = '';
		function doAction(id, no, mode) {
		if ($('input[name=confirmPassword]').val().trim() == '') {
			alert('비밀번호를 입력해 주세요');
			$('input[name=confirmPassword]').focus();
			
			return false;
		}
		
		var password =  $('input[name=confirmPassword]').val().trim();
		var pattern = $('#pattern').val();
		
		if (pattern == 'deleteComment') {
			var sno = $('#sno').val();
			
			if (confirm('삭제하시겠습니까?')) {
				// 비밀번호 체크
				
				ajaxCallJson("cmt.dojson"
						, {	'sno' : sno,
							'password' : password,
							'order' : 'checkPasswordMemo'}
						,function (data) {
							if (data.result == 1) {
								$.magnificPopup.close();
								
								// 메모 삭제
								ajaxCallJson("cmt.dojson"
										, {	'id' : id,
											'no' : no,
											'sno' : sno,
											'password' : password,
											'order' : 'deleteMemo'}
										,function(result) {
											location.reload();
										}, function(e){
											console.log(e);
										});
							} else {
								alert('비밀번호가 틀렸습니다');
								$('input[name=confirmPassword]').val('');
								$('input[name=confirmPassword]').focus();
								
								return false;
							}
						}
						, function(e){
							console.log(e);
						});
				
		/*			$.ajax({
					data : {
						'sno' : sno,
						'password' : password,
						'order' : 'checkPasswordMemo'
					},
					dataType : 'json',
					type : 'POST',
					url : 'indbAjax.jsp',
					success : function (data) {
						if (data.result == 1) {
							$.magnificPopup.close();
							
							// 메모 삭제
							$.ajax({
								data : {
									'id' : id,
									'no' : no,
									'sno' : sno,
									'password' : password,
									'order' : 'deleteMemo'
								},
								dataType : 'json',
								type : 'POST',
								url : 'indbAjax.jsp',
								success : function (data) {
									window.location.reload();
								}
							});
						} else {
							alert('비밀번호가 틀렸습니다');
							$('input[name=confirmPassword]').val('');
							$('input[name=confirmPassword]').focus();
							
							return false;
						}
					}
				});*/
			}
		} else {
			tmpFile = $('#nfile').val();
			tmpPattern = pattern;
			
			// 비밀번호 체크
			ajaxCallJson("cmt.dojson"
						, {	'id' : id,
							'no' : no,
							'password' : password,
							'order' : 'checkPasswordBoard'}
						,function (data) {
							if (data.result == 1) {
								$.magnificPopup.close();
								
								if (tmpPattern == 'updateBoard') {
									var encodeUrl = 'write?id=' + id + '&no=' + no + '&mode=modify&from=' + mode ;
									location.href= encodeUrl;
								} else if (tmpPattern == 'deleteBoard') {
									var encodeUrl = 'delete?id=' + id + '&no=' + no + '&mode=delete';
									location.href= encodeUrl;
								}
								
							} else {
								alert('비밀번호가 틀렸습니다');
								$('input[name=confirmPassword]').val('');
								$('input[name=confirmPassword]').focus();
								
								return false;
							}
						}
						, function(e){
							console.log(e);
						});
/* 			$.ajax({
				data : {
					'id' : id,
					'no' : no,
					'password' : password,
					'order' : 'checkPasswordBoard'
				},
				dataType : 'json',
				type : 'POST',
				url : 'cmt.dojson',
				success : function (data) {
					if (data.result == 1) {
						$.magnificPopup.close();
						
						if (tmpPattern == 'updateBoard') {
							location.href='write?id=' + id + '&no=' + no + '&mode=modify&from=' + mode + '&oldfile=' + tmpFile;
						} else if (tmpPattern == 'deleteBoard') {
							location.href='delete?id=' + id + '&no=' + no + '&mode=delete&oldfile=' + tmpFile;
						}
					} else {
						alert('비밀번호가 틀렸습니다');
						$('input[name=confirmPassword]').val('');
						$('input[name=confirmPassword]').focus();
						
						return false;
					}
				}
			}); */
		}
		}
		
		function fileDownload(fileName) {
		if (fileName != '') {
			$('input[name=fileName]').val(fileName);
			$('#downloadForm').submit();
		}
		}
		
</script>

<form action="" method="post" id="fm01">
<input type="hidden" name="no" id="no" value="${boardVO.no}">
<input type="hidden" name="mode" id="mode" value="${boardVO.mode}">
<input type="hidden" name="id" id="id" value="${boardVO.id}">
<input type="hidden" name="oldfile" value="${boardVO.boardObj.newfile}" >

<!-- <header class="page-header page-header-banner x-board-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">${boardVO.brdSetupObj.bdName}</h1>
            <ol class="breadcrumb page-breadcrumb">
                <li><a href="javascript:void(0);">Home</a></li>
				<li><a href="javascript:void(0);">Community</a></li>
            </ol>
        </div>
    </div>
</header> -->

<div class="top_tit_ty01">
	<div class="tit_dp01">USEFUL INFORMATION</div>
</div>

<div class="x-list container_service">
	<!-- 고객센터 공통탭메뉴 처리 -->
	<jsp:include page="../service/service_tab_menu.jsp" flush="true">
		<jsp:param name="tab_order" value="7" />
	</jsp:include>

	<div class="service_wrap01">
		<div class="service_stit01">공지사항</div>
			<!-- 서브 컨텐츠 -->
			<div id="content-area" class="content-wrap">
				<!-- 컨텐츠 -->
				<%-- <c:forEach var="rt" items="${boardObj}" varStatus="var_rtView"> --%>
				<div class="content">
					<article class="blog-post">
						<div class="tit_area">
							<div class="in_tit">제목이 나오는 곳입니다.</div>
							<ul class="blog-post-meta date_r">
								<li>${boardVO.boardObj.name}</li>
								<li><fmt:formatDate value="${boardVO.boardObj.regdt}" pattern="yyyy.MM.dd HH:mm"/>
								<c:if test="${boardVO.brdSetupObj.bdIp eq 'on' and boardVO.boardObj.ip ne ''}">
								/ IP: ${(boardVO.brdSetupObj.bdIpAsterisk eq 'on') ? stringUtil:getMask(boardVO.boardObj.ip,6) : boardVO.boardObj.ip}
								</c:if>
								</li>
							</ul>
							<div style="clear:both;"></div>
						</div>
                        <c:if test="${boardVO.brdSetupObj.bdUseFile eq 'on'}">
	                        <c:if test="${boardVO.boardObj.newfile != ''}">
	                        <c:forTokens var="st" items="${boardVO.boardObj.newfile}" delims="|">
	                        <%-- FILE : <a href="${FILE_SEP}${filePath}${st}" target="_blank">${st}</a> --%>
	                     	<div class="file_n">
								FILE : <a href="${st}" target="_blank">${st}</a>
							</div>
	                     	<c:if test="${fn:contains(st, '.jpg') or fn:contains(st, '.png') or fn:contains(st, '.gif') or fn:contains(st, '.jpeg')}">
	                        <div>
	                        	<img class="product-overview-img" src="/resources/shop/data/upload/gd_bd_${boardVO.id}/${st}"/>
	                        </div>
	                     	</c:if>
	                        </c:forTokens>
	                        </c:if>
                        </c:if>
                        
						<c:if test="${boardVO.brdSetupObj.bdUseLink eq 'on'}">
							<c:if test="${boardVO.boardObj.urlLink ne '' }">
								<div>
									<p class="blog-post-caption">
										링크 : <a href="http://${fn:substringAfter(boardVO.boardObj.urlLink, '://') eq '' ? boardVO.boardObj.urlLink : fn:substringAfter(boardVO.boardObj.urlLink, '://')}" target="_blank">${boardVO.boardObj.urlLink}</a>
									</p>
								</div>
							</c:if>
	                    </c:if>
                        
                        <p class="blog-post-caption">${boardVO.boardObj.contents}</p>
                    </article>
				</div>
				
				<%--
				<c:if test="${boardVO.brdSetupObj.bdUseComment != ''}">
				<c:if test="${fn:length(boardVO.boardReList) > 0}"><hr />
				<h3 class="widget-title">${fn:length(boardVO.boardReList) }개의 메모가 있습니다</h3>
				</c:if>
				
				<c:forEach var="list" items="${boardVO.boardReList}" varStatus="var_rtComment">
                <!-- START COMMENTS -->
                <ul class="comments-list">
                    <li>
                        <article class="comment">
                            <div class="comment-inner"><span class="comment-author-name">${list.name}</span>
                                <p class="comment-content">${list.memo}</p><span class="comment-time"><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd HH:mm"/></span>
                                <a href="#nav-dialog" data-effect="mfp-move-from-top" class="popup-text" onclick="$('#sno').val('${list.sno}');$('#pattern').val('deleteComment');">삭제</a>
                            </div>
                        </article>
            		</li>
           		</ul>
				</c:forEach>
				
				
				<c:if test="${boardVO.brdSetupObj.bdLvlC eq '' or (boardVO.level >= boardVO.brdSetupObj.bdLvlC)}">
				<hr />	
				<div style="width:41.66666667%;">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>이름</label>
                                <input class="form-control" type="text" name="name" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>비밀번호</label>
                                <input class="form-control" type="password" name="password" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                        <label>메모</label>
                        <textarea class="form-control" style="height:200px;" name="memo"></textarea>
                    </div>
                    <input class="btn btn-primary" type="button" onclick="addComment('${boardVO.id}', '${boardVO.no}');" value="작성완료" />
                 </c:if>   
                <hr />
				</c:if>
				 --%>
				
				<!-- 버튼 -->
				<div class="btn-wrap text-center">
					<a href="${ctx }/shop/board/list?id=${boardVO.id}&pageNo=${boardVO.pageNo}&mode=${boardVO.mode}&searchMode=${boardVO.searchMode}&searchWord=${boardVO.searchWord}" class="btn btn-primary btn_write_n01"><span class="txt-white">목록</span></a>
					<%--
					<c:choose>
					<c:when test="${boardVO.level eq '100'}">
						<a href="#nav-dialog" onclick="$('#sno').val(${no});$('#pattern').val('updateBoard');" data-effect="mfp-move-from-top" class="btn btn-primary popup-text">수정</a>
						<a href="delete?id=${boardVO.id}&no=${boardVO.no}&mode=delete&bdLvlW=${boardVO.brdSetupObj.bdLvlW}" class="btn btn-primary">삭제</a>
					</c:when>
					
					<c:when test="${boardVO.brdSetupObj.bdLvlW eq '' or (boardVO.level >= boardVO.brdSetupObj.bdLvlW)}">
						<a href="#nav-dialog" onclick="$('#sno').val(${no});$('#pattern').val('updateBoard');" data-effect="mfp-move-from-top" class="btn btn-primary popup-text">수정</a> 
						<a href="#nav-dialog" onclick="$('#sno').val(${no});$('#pattern').val('deleteBoard');" data-effect="mfp-move-from-top" class="btn btn-primary popup-text">삭제</a>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
					</c:choose>
					 --%>
				</div>
				<%-- </c:forEach> --%>
			</div> 
    	</div>
    </div>
		    <div class="mfp-with-anim mfp-hide mfp-dialog clearfix" id="nav-dialog">
		        <div class="form-group">
		            <label>비밀번호</label>
		            <input class="form-control" type="password" name="confirmPassword" />
		            <input type="hidden" name="sno" id="sno"/>
		            <input type="hidden" name="nfile" id="nfile"/>
		            <input type="hidden" name="pattern" id="pattern" />
		        </div>
		        <input class="btn btn-primary" id="lPopup" type="button" onclick="doAction('${boardVO.id}', '${boardVO.no}', '${boardVO.mode}');" value="확인" />
		        <div class="gap gap-small"></div>
		    </div>
</div>
</form>
<!--// 서브 컨텐츠 -->

<form id="downloadForm" method="post" action="../common/fileDownload">
	<input type="hidden" name="filePath" value="${boardVO.filePath}"/>
	<input type="hidden" name="fileName"/>
</form>



<input type="hidden" name="bdLvlR" value="${boardVO.brdSetupObj.bdLvlR}"> 
<input type="hidden" name="level" value="${'' eq boardVO.level ? 0 : boardVO.level}">

<script language="javascript">

$(function(){
	var settingBoardLevel = $('[name=bdLvlR]').val();		//관리자 설정 게시판 레벨
	var userLevel = $('[name=level]').val();	//사용자 레벨
	
	if (0 != settingBoardLevel & userLevel < settingBoardLevel) {
		history.go(-1);
		alert("접근 권한이 없습니다");
	}
}); 

</script>