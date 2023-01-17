<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>${zFilePath}</title>
	</head>
	<body>
		<h2>Content of ${convertFileName}</h2>
		<br />
		
		<table class="filelist" cellspacing="1px" cellpadding="0px">

			<tr>
				<th>Name</th>
				<th>Uncompressed size</th>
				<th>Compressed size</th>
				<th>Compr. ratio</th>
				<th>Date</th>
			</tr>
		<c:forEach items="${fileHtmlList}" var="fileVO">
			<tr class="mouseout">
				<td>${fileVO.html}</td>
				<td>${fileVO.fileSize}</td>
				<td>${fileVO.compressedSize}</td>
				<td>${fileVO.ratio}%</td>
				<td>${fileVO.date}</td>
			</tr>
		</c:forEach>
		
		</table>
		<p align=center>
			<b>
				${totalSize} in ${fileCount} files in ${zFileName}. Compression ratio: ${(zFileLegnth * 100) / totalSize}%
			</b>
		</p>
	</body>
</html>