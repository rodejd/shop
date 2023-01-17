<%
/************************************************************************************
* 프로그램명 				: 
* 관련 SERVICE명 			: XMall>lib>meditor>popup.image.jsp 에디터
* 프로그램 내용 			: XMall>lib>meditor>indb.jsp
* 작성자	   		 		: 홍웅선
* 작성일자 				: 2011.01.10
* 수정자  				: 
* 수정 내용				: 
* 수정일자				: 
************************************************************************************/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page info="indb.jsp"  errorPage="/common/error_jsp.jsp"  %> --%>
<%-- <%@ include file="/common/common.jsp" %> --%>
<%-- <%! --%>
// 	private enum Mode{ InsertImage;
// 		public static Mode fromString(String Str){
// 			try {
// 				return valueOf(Str);
// 			} catch (Exception ex){
// 				return null;
// 			}
// 		}
// 	};
<%-- %> --%>
<%-- <%	 --%>
// 	/* DB 연결을 필요로 하는 페이지일 경우는 true 그외에는 false;
// 	 * 기본값은 false;
// 	 */
// 	dbConnectFlag=true; 
<%-- %> --%>
<%-- <%@ include file="/common/try.jsp" %> --%>
<%-- <%  --%>
// 	int idx = Integer.parseInt(requestSet.getProperty("idx"));
// 	switch(Mode.fromString(requestSet.getProperty("mode")))
// 	{
	
// 		case InsertImage:
// 			//화일업로드 결과
// 			boolean uploadRes = true;
// 			String src ="";
// 			String size = "";
// 			int widthNum = 0;
// 			int heightNum = 0;
// 			String filePath =ConfigClass.FILE_PATH+"editor"+FILE_SEP;
// 			//String src = dirname($_SERVER[PHP_SELF])."/".$dir.$filename;
// 			if(!FileUtil.isCheckFileImgExt(requestSet.getProperty("src")))
// 			{
<%-- 				%><script>alert('이미지 파일만 업로드가 가능합니다');</script><% --%>
// 				return;
// 			}else{
// 				//첨부파일폼네임
// 				String fileParam ="mini_file";
// 				//이전파일 값을 가진고 있는 폼네임(여러개일경운 위의 첨부파일폼네임과 동일)
// 				String oldParam ="old_mini_file";
// 				//삭제여부 폼 네임(여러개일경운 위의 첨부파일폼네임과 동일)
// 				String delParam = "del_flag";
// 				//업로드할 화일갯수
// 				int count = 1;
				
// 				//db에 저장될 이름
// 				String fileName ="";
				 
// 				uploadRes = FileUtil.uploadFiles(mpUpload, request, requestSet, filePath, fileParam,oldParam,delParam,count);
// // 				src = filePath+requestSet.getProperty("mini_file0");
// // 				src = WebUtil.confirmDownloadURL("editor", mpUpload.getFileName("mini_file"));
// 				String web_path = ConfigClass.value("WEB_DIR_PATH");
// 				String editorFilePath = (ConfigClass.FILE_PATH + "editor").replaceAll(web_path,"") ;
// 				String upFileName = mpUpload.getFileName("mini_file").split("/")[mpUpload.getFileName("mini_file").split("/").length -1];
// 				src = FILE_SEP + editorFilePath + FILE_SEP + upFileName;
// 				if(requestSet.getPropertyAsInt("imgWidth",0) != 0) widthNum = requestSet.getPropertyAsInt("imgWidth",100);
// 				if(requestSet.getPropertyAsInt("imgHeight",0) != 0) heightNum = requestSet.getPropertyAsInt("imgHeight",100);
// 				size = "width="+widthNum+" height="+heightNum+"";
// 			}
// 			if(uploadRes)
// 			{
<%-- 			%><script>parent.opener.mini_set_html(<%=idx%>,"<img src='<%=src%>' <%if(widthNum!=0 ||heightNum!=0){%><%=size%><%}%> >");</script>;<% --%>
// 			}
<%-- 			%><script>parent.window.close();</script>;<% --%>
// 			break;
// 	}
<%-- %> --%>  
<!-- <!-- --> 
<!-- --> -->
<%-- <%@ include file="/common/catch.jsp" %> --%>