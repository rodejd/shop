<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- //Output message -->
	<c:if test="${ftpVO.message != null && ftpVO.message != '' }">
		<jsp:include page="directoryViewer/outputMessage.jsp"></jsp:include>
	</c:if>
	<!-- Output error -->
	<c:if test="${ftpVO.error != null && ftpVO.error != '' }">
		<jsp:include page="directoryViewer/outputError.jsp"></jsp:include>
	</c:if>
	<c:if test="${ftpVO.dir != null && ftpVO.dir != '' }">

		<dir class="js_fileUPloadTop">
			<c:if test="${boolsMap.allowUpload }">
				<form class="formular2" action="${browserName}" enctype="multipart/form-data" method="POST">
					<input type="hidden" name="dir" value="${ftpVO.dir}">
					<input type="hidden" name="sort" value="${sortMode}">
					<input type="file" class="textfield" onKeypress="event.cancelBubble=true;" name="myFile">
					
					<input TYPE="IMAGE" name="submit" value="${strMap.uploadFiles}" src="/resources/shop/admin/design/webftp/../../img/webftp/top_menu_01.gif"   align="absmiddle" onClick="javascript:popUp('${browserName}')">
				
				</form>
			</c:if>
			
			<span class="js_fileNameFilter">Filename filter: <input name="filt" onKeypress="event.cancelBubble=true;" onkeyup="filter(this)" type="text"></span>
			
		</dir>
		<form class="formular" action="${browserName}" method="Post" name="FileList">
			<table id="filetable" class="filelist" cellspacing="1px" cellpadding="0px">
				<tr>
					<th>&nbsp;</th>
					<th title="Sort files by name" align=left>
						<a href="${ftpVO.cmd}&amp;sort=${ftpVO.sortArr[0]}">Name</a>
					</th>
					<th title="Sort files by size" align="right">
						<a href="${ftpVO.cmd}&amp;sort=${ftpVO.sortArr[1]}">Size</a>
					</th>
					<th title="Sort files by type" align="center">
						<a href="${ftpVO.cmd}&amp;sort=${ftpVO.sortArr[3]}">Type</a>
					</th>
					<th title="Sort files by date" align="left">
						<a href="${ftpVO.cmd}&amp;sort=${ftpVO.sortArr[2]}">Date</a>
					</th>
					
					<c:if test="${!boolsMap.readOnly}">
						<th>&nbsp;</th>
					</c:if>
				</tr>
				<c:forEach items="${ftpVO.innerVO}" var="innerVO">
					<tr class="mouseout" onmouseover="this.className='mousein'" onmouseout="this.className='mouseout'">	
						<td>&nbsp;</td>
						<td align=left >	
							&nbsp;
							<a href="${browserName}?sort=${sortMode}&amp;dir=${innerVO.name}">[${innerVO.buf}]</a>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td></td></tr>
				</c:forEach>
				<!-- Output the parent directory link ".." -->
				<c:if test="${ftpVO.fileParent != null }">
					<tr class="mouseout" onmouseover="this.className='mousein'" onmouseout="this.className='mouseout'">
						<td></td>
						<td align=left>
							&nbsp;
							<a href="${browserName }?sort=${sortMode}&amp;dir=${ftpVO.fileParent }">
								[..]
							</a>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td></td>
					</tr>
				</c:if>
			
				<c:forEach items="${ftpVO.entry}" var="entry">
			
					<tr class="mouseout" onmouseup="selrow(this, 2)" onmouseover="selrow(this, 0);" onmouseout="selrow(this, 1)">
						<c:choose>
							<c:when test="${entry.canRead}">
								<td align=center>
									<input type="checkbox" name="selfile" value="${entry.name}" onmousedown="dis()">
								</td>
							</c:when>
							<c:otherwise>
								<td align=center><input type="checkbox" name="selfile" disabled></td>
							</c:otherwise>
						</c:choose>
						
						<td align=left> &nbsp;${entry.link} </td>
						
						<c:choose>
							<c:when test="${entry.directory }">
								<td>&nbsp; </td>
							</c:when>
							<c:otherwise>
								<td align=right title="${entry.length} bytes">
									${entry.convertFileSize}
								</td>
							</c:otherwise>
						</c:choose>
						
						<td align="center">${entry.type}</td>
						<td align=left> &nbsp; ${entry.date}</td>
						<td></td>
						<c:if test="${boolsMap.readOnly }">
							<td>${ftpVO.elink}</td>
						</c:if>
						<td>${elink}</td>
					</tr>
			
				</c:forEach>
			</table>
			<div class="js_selectAll">
				<input type="checkbox" name="selall" onClick="AllFiles(this.form)"> 전체선택
				<c:if test="${!boolsMap.readOnly }">
					<input id="but_Del" TYPE="IMAGE" name="submit" value="${strMap.deleteFiles}" src="/resources/shop/admin/design/webftp/../../img/webftp/top_menu_05.gif"   align="absmiddle" onclick="return confirm('선택한 파일을 삭제하시겠습니까?')">
				</c:if>
				<c:if test="${!boolsMap.readOnly}">
					<input title="Enter new dir or filename or the relative or absolute path" class="textfield" type="text" onKeypress="event.cancelBubble=true;" id="text_Dir" name="crDir">
					<input id="but_NDi" type="image" name="submit" value="${strMap.createDir }" src="/resources/shop/admin/design/webftp/../../img/webftp/top_menu_03.gif" align="absmiddle">
				</c:if>
			</div>
			<p align=center>
				<b title="${ftpVO.uplInfo.totalSize} bytes">
				${ftpVO.totalSize}</b><b> in ${ftpVO.fileCount } files in ${ftpVO.linkDir }
				</b>
			</p>
			<input type="hidden" name="dir" value="${ftpVO.dir}">
			<input type="hidden" name="sort" value="${ftpVO.sortMode}">
			
		</form>
		<br />
				
		</c:if>	
	</body>
