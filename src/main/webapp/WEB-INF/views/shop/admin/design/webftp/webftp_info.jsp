<%-- <% --%>
// /************************************************************************************
// * 프로그램명 				: 
// * 관련 SERVICE명 			: 
// * 프로그램 내용 			:  XMall>게시판>list>관리자 아이콘view
// * 작성자	   		 		: 홍웅선
// * 작성일자 				: 2011.02.10
// * 수정자  				: 
// * 수정 내용				: 
// * 수정일자				: 
// ************************************************************************************/
<%-- %> --%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ page info="register.jsp"  errorPage="/common/error_jsp.jsp"  %> --%>
<%-- <%@ include file="/common/common.jsp" %> --%>
<%-- <% --%>
// 	/* DB 연결을 필요로 하는 페이지일 경우는 true 그외에는 false;
// 	 * 기본값은 false;
// 	 */
// 	dbConnectFlag=false;
<%-- %> --%>
<%-- <%@ include file="/common/try.jsp" %> --%>


<%-- <%@page import="java.awt.image.BufferedImage"%> --%>
<%-- <%@page import="javax.imageio.ImageIO"%> --%>
<%-- <% --%>
// 	String checkStr =requestSet.getProperty("file_root");
// 	String filename = checkStr.substring(checkStr.lastIndexOf("/")+1,checkStr.length());//"admin.gif";
// 	String f_kind =checkStr.substring(checkStr.lastIndexOf(".")+1,checkStr.length());//"GIF";
// 	//out.println("my:"+request.getRequestURI()+"//");
		
// 	HashMap<String,String> filesetMap = FileUtil.fileInfo(checkStr);//request.getRequestURI()
// //	out.println();
// 	String img_widht 	= filesetMap.get("imge_width");
// 	String img_height 	= filesetMap.get("imge_height");
// 	String f_time 		= filesetMap.get("lastModified");
// 	String file_size	= filesetMap.get("file_size");
		
// 	ServletContext context = getServletContext();
// /* 	out.print("[");
// 	out.print(context.getRealPath(checkStr));
// 	out.print("]"); */
<%-- %> --%>
<!-- <html> -->
<!-- <head> -->
<!-- <title>Webftp Info</title> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <link rel="styleSheet" href="/shop/admin/design/webftp/../../style.css"> -->
<!-- <script src="/resources/shop/admin/design/webftp/../../common.js"></script> -->
<!-- <link rel="styleSheet" href="/shop/admin/design/webftp/webftp.css"> -->
<!-- <SCRIPT LANGUAGE="JavaScript"> var curr_path = '/shop/admin/design/webftp/'; var webftpid = 'default'; </SCRIPT> -->
<!-- <SCRIPT LANGUAGE="JavaScript" SRC="/shop/admin/design/webftp/webftp.js"></SCRIPT> -->
<!-- </head> -->
<!-- <body bgcolor="#7D746E" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> -->



<!-- <!-- 전체보기 : Start --> -->
<!-- <div class="allview"><a href="javascript:;" onclick="frame_list_dpath('/');"><font color="ffffff">파일 상세 정보</font></a></div> -->
<!-- <!-- 전체보기 : End --> -->


<!-- <!-- 파일 상세 정보 : Start --> -->
<!-- <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="7D746E"> -->

<!--   <tr> -->
<!--     <td align="center" height="160"> -->
<%--     	<img src='/resources/shop<%=checkStr %>' width="150" height="150" border="0" style="border:0 solid f0f0f0;" align="absmiddle" id=""><br><!--  onload="img_preview(this)" --> --%>
<!--     </td> -->
<!--   </tr> -->

<!--   <tr> -->
<!--     <td style="color:D8CDC5;line-height:16px;font:8pt tahoma;padding-left:10px;"> -->
<%--     <b><%=filename%></b><br> --%>
<%--     <div>Type : <%=f_kind%></div> --%>
<%--     <div id="img_size">Image Size : <%=img_widht%> x <%=img_height%></div> --%>
<%--     <div id="file_size">Size : <%=file_size%></div> --%>
<%--     <div id="img_modified">Modified : <%=f_time%></div> --%>
<!--     Url : <A HREF="javascript:;" onclick ="urlCopyact( document.fm_url.link );"><img src="/resources/shop/admin/design/webftp/../../img/webftp/bu_addcopy2.gif" border="0" align="absmiddle"></A><br>/<nobr>shop/<nobr>data/<nobr>skin/<nobr>season2/<nobr>admin.gif<br> -->
<%--     <form name="fm_url"><input type="hidden" name="link" value='<%=checkStr%>'></form>  </tr> --%>
<!--   <tr> -->
<!--     <td height="10"></td> -->
<!--   </tr> -->
<!-- </table> -->
<!-- <!-- 파일 상세 정보 : End --> -->
<!-- <script type="text/javascript"> -->
// 	//자바스크립트로 width,height,fileSize를 가져올수 있다.
// 	function img_preview(img)
// 	{
// 		var img_width = img.width;
// 		var img_width = img.height;
// 		var file_size = img.fileSize;//Math.round(this.fileSize/1024); 로하면 -> kb
// 		document.getElementById('file_size').innerHTML = "Size : "+file_size+"Byte";
// 	}
<!-- </script> -->
	
<!-- </body> -->
<!-- </html> -->
<%-- <%@ include file="/common/catch.jsp" %> --%>