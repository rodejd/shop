	<META HTTP-EQUIV="Refresh" CONTENT="${intMap.uploadMonitorRefresh};URL=${browserName}?uplMonitor=${ftpVO.hangulFname}">
	</head>
	<body>
		<b>Upload of[2] ${ftpVO.fName}</b><br><br>
		<center>
		<table height="20px" width="90%" bgcolor="#eeeeee" style="border:1px solid #cccccc"><tr>
		<td bgcolor="blue" width="${uplInfo.percent}%"></td><td width="${100 - uplInfo.percent}%"></td>
		</tr></table></center>
		${ftpVO.uplInfo.convertCurrSize} from ${ftpVO.uplInfo.convertTotalSize}
		(${ftpVO.uplInfo.percent} %) uploaded (Speed: ${ftpVO.uplInfo.uprate}).<br>
		Time: ${ftpVO.uplInfo.tileElapsed} from ${ftpVO.uplInfo.timeEstimated}
	</body>
</html>