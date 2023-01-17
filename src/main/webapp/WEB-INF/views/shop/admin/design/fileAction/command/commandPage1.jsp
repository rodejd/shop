	<title>Launch commands in ${dir }</title>
</head>
<body>
	<center>
		<h2>${strMap.launchCommand}</h2>
		<br />
		<form action="${browserName }" method="post">
			<textarea name="text" wrap="off" cols="${intMap.editFieldCols}" rows="${intMap.editFieldRows}" readonly="readonly">
				<c:if test="${ftpVO.command != '' }">
					${ret }
				</c:if>
			</textarea>
			<input type="hidden" name="dir" value="<%= request.getAttribute("dir")%>">
			<br /><br />
			<table class="formular">
				<tr>
					<td title="Enter your command">
						Command: <input size="${intMap.editFieldCols - 5}" type="text" name="command" value="">
					</td>
				</tr>
				<tr>
					<td>
						<input class="button" type="Submit" name="Submit" value="Launch">
						<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
						<input type="Submit" class="button" name="Submit" value="Cancel">
					</td>
				</tr>
			</table>
		</form>
		<br />
		<hr>
		<center>
			<small>jsp File Browser version ${strMap.versionNR} by <a href="http://www.vonloesch.de">www.vonloesch.de</a></small>
		</center>
	</center>
</body>
</html>