<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>    
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/board/write.js"></script>

<header class="page-header page-header-banner x-board-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">${boardVO.brdSetupObj.bdName}</h1>
        </div>
    </div>
</header>

<div class="x-write container">
	<div class="tabbable product-tabs">
    	<div class="tab-content nb">
    		<form  id="frm01" name=frmWrite action="indb" method="post" enctype="multipart/form-data" class="mt30">
    			<input type="hidden" name="tmp"/>
				<input type="hidden" name="id" value="${boardVO.id}"/>
				<input type="hidden" name=category_pre value=""/>
				<input type="hidden" name="no" value="${boardVO.no}"/>
				<input type="hidden" name="mode" value="${boardVO.mode}"/>
				<input type="hidden" name="mno" value="${boardVO.mno}"/>
				<input type="hidden" name="page" value=""/>
				<input type="hidden" name="chkSpamKey"/>
				<input type="hidden" name="oldfile" value="${boardVO.boardObj.newfile}"/>
				<input type="hidden" id="Subcontents" name="Subcontents"/>
				<c:if test="${bdIp eq 'on'}">
				<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>"/>
				</c:if>
	        	<table class="table">
	               <tbody>
	                	<tr>
	                        <th><span class="required">*</span> 이름</th>
	                        <td>
	                        	<div class="row">
		                            <div class="col-md-2">
	                                    <input class="form-control"  type="text" id="" name="name" title="이름" label="이름" value="${boardVO.name}" required ${ (mode == "modify") ? "readonly style='border:0;font-weight:bold'" : "" }  maxlength="6"/>
		                            </div>
		                        </div>
	                        </td>
	                    </tr>
	                    <c:if test="${boardVO.mode eq 'write'}">
	                    <tr>
	                        <th><span class="required">*</span> 비밀번호</th>
	                        <td>
	                        	<div class="row">
		                            <div class="col-md-2">
	                                    <input class="form-control"  type="password" id="" name="password" title="비밀번호" label="비밀번호" value="" required ${ (mode == "modify") ? "readonly style='border:0;font-weight:bold'" : "" } maxlength="10"/>
		                            </div>
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${boardVO.brdSetupObj.bdEmailNo == ''}">
	                    <tr>
	                        <th>이메일</th>
	                        <td>
	                        	<div class="row">
		                            <div class="col-md-5">
	                                    <input class="form-control"  type="text" id="" name="email" title="이메일" label="이메일" value="${boardVO.email}" ${ (mode == "modify") ? "readonly style='border:0;font-weight:bold'" : "" } maxlength="25"/>
		                            </div>
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${boardVO.brdSetupObj.bdHomepageNo == ''}">
	                    <tr>
	                        <th>홈페이지</th>
	                        <td>
	                        	<div class="row">
		                            <div class="col-md-5">
	                                    <input class="form-control"  type="text" id="" name="homepage" title="홈페이지" label="홈페이지" value="${boardVO.boardObj.homepage}" ${ (mode == "modify") ? "readonly style='border:0;font-weight:bold'" : "" } maxlength="50"/>
		                            </div>
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${ boardVO.brdSetupObj.bdUseSubSpeech != '' }">
	                    <tr>
	                        <th><span class="required">*</span> 말머리</th>
	                        <td>
	                        	<div class="row">
		                            <div class="col-md-12">
		                            <c:set var="crlf" value="
									" />
	                                    <select name="category" class="category-selections-select" required>
											<option value="">${boardVO.brdSetupObj.bdSubSpeechTitle}</option>
											<c:forTokens var="st" items="${boardVO.brdSetupObj.bdSubSpeech}" delims="${crlf }">
											<option value='${st}' ${stringUtil:selected(boardVO.boardObj.category, st)}>${st}</option>
											</c:forTokens>
										</select>
		                            </div>
		                        </div>
	                        </td>
	                    </tr>
	                    </c:if>
	                    <tr>
	                        <th><span class="required">*</span> 제목</th>
	                        <td>
	                        	<div class="row">
		                            <div class="col-md-12">
	                                    <input type="text" id="subject" name="subject"
	                                    <%-- <c:if test="${boardVO.brdSetupObj.bdStrlen ne null}"> maxlength="${bdStrlen}"</c:if>  --%>
	                                    label="제목" title="제목" maxlength="30" class="form-control" value="${boardVO.boardObj.subject}" required  />
		                            </div>
		                        </div>
	                        </td>
	                    </tr>
	                    <c:if test="${((boardVO.mode eq 'write') or (boardVO.mode eq 'modify')) and (boardVO.brdSetupObj.bdSkin eq 'default')}">
	                    <tr>
	                    	<th>공지글</th>
	                    	<td><input type="checkbox" name="notice" value="o"  /></td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${boardVO.brdSetupObj.bdSecretChk ne '2'}">
	                    <tr>
	                    	<th>비밀글</th>
	                    	<td>
	                    		<c:if test="${boardVO.mode eq 'write'}">
		                    		<c:if test="${boardVO.brdSetupObj.bdSecretChk eq '0'}"><input type="checkbox" name="secret" value="o" /></c:if>
		                    		<c:if test="${boardVO.brdSetupObj.bdSecretChk eq '1'}"><input type="checkbox" name="secret" value="o" checked="checked" /></c:if>
		                    		<c:if test="${boardVO.brdSetupObj.bdSecretChk eq '3'}">해당글은 비밀글로만 작성이 됩니다<input type="hidden" name="secret" value="o" /></c:if>
	                    		</c:if>
	                    		<c:if test="${boardVO.mode eq 'modify'}">
	                    			<c:if test="${(boardVO.brdSetupObj.bdSecretChk eq '0') or (boardVO.brdSetupObj.bdSecretChk eq '1')}"><input type="checkbox" name="secret" value="o" ${boardVO.boardObj.secret == 'o' ? 'checked': ''} /></c:if>
	                    			<c:if test="${boardVO.brdSetupObj.bdSecretChk eq '3'}">해당글은 비밀글로만 작성이 됩니다<input type="hidden" name="secret" value="o" /></c:if>
	                    		</c:if>
	                    	</td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${boardVO.brdSetupObj.bdUseLink eq 'on'}">
	                    <tr>
	                    	<th>링크</th>
	                    	<td>
	                    		<div class="row">
		                            <div class="col-md-5">
	                    				<input type=text class="form-control" name=urlLink value="${boardVO.boardObj.urlLink}" maxlength="80"/>
	                    			</div>
	                    		</div>
	                    	</td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${boardVO.brdSetupObj.bdUseFile eq 'on'}">
	                    <tr>
	                    	<th><c:choose><c:when test="${'gallery' == boardVO.from}">목록<br>이미지</c:when><c:otherwise>첨부파일</c:otherwise></c:choose></th>
	                    	<td>
	                    		<div class="row">
	                    			<div class="col-md-12">
			                    		<div class="data-div-wrap inline-block w290">
											<span class="data-box">
												<input type="file" id="attach" class="form-control" name="attach" title="파일" value=""/>
												<c:if test="${arrfile[0] ne null}">
												<div style="margin-top:10px;">현재 등록된 파일 : <span style="color:red;" id="cFile">${arrfile[0]}</span></div>
												</c:if>
											</span>
										</div>
									</div>
								</div>
	                    	</td>
	                    </tr>
	                    </c:if>
	               </tbody>
	            </table>
	            
	            <div class="mt20">
					<textarea name="contents"  id="contents" class="h400" type="editor" style="width:100%; height:500px;">${boardVO.boardObj.contents}</textarea> <!-- 에디터 필요함 -->
					<script src=/resources/shop/lib/meditor/mini_editor.js></script>
					<script>mini_editor("/resources/shop/lib/meditor/")</script>
				</div>
				
				<div class="gap gap-small"></div>
				
				<!-- 버튼 -->
				<div class="btn-wrap text-center">
					<button type="submit" form="frm01" value="등록" id="checkButton" class="btn btn-primary"><span class="txt-white">확인</span></button>
					 <button type="reset" id="resetCall" class="btn btn-primary"><span class="txt-white">취소</span></button>
					<button onclick="javascript:history.back()" class="btn btn-primary"><span class="txt-white">뒤로</span></button>
				</div>
          </form>
		</div> 
	</div>
</div>
<!--// 서브 컨텐츠 -->
