/*
 * Created on 2004. 9. 16.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wepinit.wepinit_shop.xcube.util;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author cozyhill
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WebUtil {

	// 경고창후 이동 자바스크립트 생성
	public static String getAlertRedirect(String str, String url) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("alert(\"").append(str).append(" \");");
		sb.append("var url=\"").append(url).append("\";");
		// sb.append("if(url=='') url='/';");
		sb.append("location=url;");
		sb.append("</script>");
		return sb.toString();
	}

	// 경고창후 이동 자바스크립트 생성
	public static String getAlertRedirect(String str, String url, int time) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		sb.append("var url=\"").append(url).append("\";");
		// sb.append("if(url=='') url='/';");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("setTimeout('alert(\"").append(str).append(" \");");
		sb.append("location=url;'," + time + ")");
		sb.append("</script>");
		return sb.toString();
	}

	// 경고창후 이동 자바스크립트 생성
	public static String getAlertMainRedirect(String str, String url) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("alert(\"").append(str).append(" \");");
		sb.append("var url=\"").append(url).append("\";");
		sb.append("var strLocation=\"\";");
		// sb.append("if(url=='') url='/';");
		sb.append("if(window.opener!=null){strLocation=window.opener.top}");
		sb.append("else{strLocation=window.top}");
		sb.append("strLocation.location=url;");
		sb.append("if(window.opener!=null){self.close();}");
		sb.append("</script>");
		return sb.toString();
	}

	public static String getMainRedirect(String url) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		sb.append("var url=\"").append(url).append("\";");
		sb.append("var strLocation=\"\";");
		// sb.append("if(url=='') url='/';");
		sb.append("if(window.opener!=null){strLocation=window.opener.top}");
		sb.append("else{strLocation=window.top}");
		sb.append("strLocation.location=url;");
		sb.append("if(window.opener!=null){self.close();}");
		sb.append("</script>");
		return sb.toString();
	}

	public static String getMainRedirect(String str, String url) {
		return getAlertMainRedirect(str, url);
	}

	/*
	 * public static String getRedirectErrorPage(String errorType, String
	 * errorCode,String errorMsg,String strPrevPage, String errorPage){
	 * StringBuffer sb = new StringBuffer(); sb.append("<script
	 * language=\"JavaScript\">\n"); sb.append("<!--\n"); sb.append("var
	 * hostname = location.hostname;\n"); sb.append("var pgmurl =
	 * location.pathname;\n"); sb.append("var idx =
	 * location.pathname.lastIndexOf(\"/\");\n"); sb.append("var pgmid =
	 * location.pathname.substring(idx+1, pgmurl.length-4);\n"); sb.append("var
	 * errPage;\n"); sb.append("var param = \"?ERR_TYPE="+errorType);
	 * sb.append("&ERR_CODE="+errorCode); sb.append("&ERR_DETAIL="+errorMsg);
	 * sb.append("&ERR_PGM_ID=\"+pgmid");
	 * if(strPrevPage!=null&&strPrevPage.length()>0){
	 * sb.append("+\"&PREV_PAGE="+strPrevPage+"\""); }
	 * sb.append("+\"&ERR_PGM_URL=\"+pgmurl"+";\n");
	 * 
	 * if(errorPage!=null&&errorPage.length()!=0){
	 * sb.append("errPage=\"").append(errorPage).append("\";\n"); }else{ //팝업
	 * sb.append("if(window.opener!=null){\n"); sb.append("errPage =
	 * \""+SysConst.ERROR_JSP_POPPAGE+"\";\n"); //autoplan sb.append("}else
	 * if(hostname.indexOf(\"autoplan.hyundaicapital\")>-1){\n");
	 * sb.append("errPage = \""+SysConst.ERROR_JSP_PAGE_AP+"\";\n"); //현대캐피탈
	 * sb.append("}else if(hostname.indexOf(\"www.hyundaicapital\")>-1){\n");
	 * sb.append("errPage = \""+SysConst.ERROR_JSP_PAGE+"\";\n"); //드림파트너중 중고차
	 * sb.append("}else if(hostname.indexOf(\"www.dreampartners\")>-1){\n");
	 * sb.append("errPage = \""+SysConst.ERROR_JSP_PAGE_USEDCAR+"\";\n"); //배구단
	 * 에러페이지 sb.append("}else
	 * if(hostname.indexOf(\"www.hyundai-volleyball\")>-1){\n");
	 * sb.append("errPage = \""+SysConst.ERROR_JSP_PAGE_VA+"\";\n"); //클라스오토
	 * sb.append("}else if(hostname.indexOf(\"www.klassauto\")>-1){\n");
	 * sb.append("errPage = \""+SysConst.ERROR_JSP_PAGE_KA+"\";\n");
	 * sb.append("}else{\n"); sb.append("errPage =
	 * \""+SysConst.ERROR_JSP_PAGE+"\";\n"); sb.append("}\n"); }
	 * sb.append("window.top.location = errPage + param;\n");
	 * sb.append("//-->\n"); sb.append("</script>\n"); return sb.toString(); }
	 */
	// 경고창후 함수 호출;
	public static String getAlertAddScript(String str, String script) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("alert(\"").append(str).append(" \");");
		sb.append(script);
		sb.append("</script>");
		return sb.toString();
	}

	// 경고창 스크립트
	public static String getAlert(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("alert(\"").append(str).append(" \");");
		sb.append("</script>");
		return sb.toString();
	}

	/** 페이지이동 스크립트 리턴 */
	public static String getRedirect(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		sb.append("var url=\"").append(str).append("\";");
		// sb.append("if(url=='') url='/';");
		sb.append("location=url;");
		sb.append("</script>");
		return sb.toString();
	}

	public static String getRedirectTime(String str, int time) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		sb.append("var url=\"").append(str).append("\";");
		// sb.append("if(url=='') url='/';");
		sb.append("setTimeout('location=url;'," + time + ")");
		sb.append("</script>");
		return sb.toString();
	}

	// 경고창 스크립트
	public static String getScript(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append(str);
		sb.append("</script>");
		return sb.toString();
	}

	public static String getAlertTime(String str, int time) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("setTimeout(\"alert(\\\"").append(str).append(
				" \\\")\", " + time + ");");
		sb.append("</script>");
		return sb.toString();
	}

	public static String getAlertGo(String str, int num) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("alert(\"").append(str).append(" \");\n");
		sb.append("history.go(").append("" + num).append(" );");
		sb.append("</script>");
		return sb.toString();
	}

	public static String setLineChange2(String str) {

		try {
			if (str == null)
				return "";
			StringBuffer sb = new StringBuffer(str);
			for (int i = 0, intIndex = 0; (intIndex = str.indexOf('\n',
					intIndex)) > -1; intIndex++, i += 3) {
				sb.replace(intIndex + i, intIndex + i + 1, "<br>");
			}
			return sb.toString();
		} catch (NullPointerException npe) {
			return str;
		}

	}

	public static String setLineChange(String str) {

		try {
			if (str == null)
				return "";
			StringBuffer sb = new StringBuffer(str);
			for (int i = 0, intIndex = 0; (intIndex = str.indexOf('\r',
					intIndex)) > -1; intIndex++, i += 3) {
				sb.replace(intIndex + i, intIndex + i + 1, "<br>");
			}
			return sb.toString();
		} catch (NullPointerException npe) {
			return str;
		}

	}

//	public static String makePaging(RequestSet requestSet, DBHandler dbHandler,
//			String xmlID, String jspName) throws SQLException, Exception {
//
//		return makePaging(requestSet, dbHandler, xmlID, jspName,
//				ConfigClass.PAGE_SIZE);
//	}

//	public static String makePaging(RequestSet orgRequestSet,
//			DBHandler dbHandler, String xmlID, String jspName, int page_size)
//			throws SQLException, Exception {
//
//		// /글목록 총갯수
//		ResultTable rtCnt = dbHandler.executeXmlQuery(xmlID, orgRequestSet);
//		PageUtil pageUtil = new PageUtil(rtCnt.asInt(), page_size,
//				orgRequestSet, jspName);
//		String page_list = pageUtil.getPageLinks();
//		return page_list;
//	}

//	public static String makePaging(RequestSet orgRequestSet, String jspName,
//			int total_items, int page_size) throws SQLException, Exception {
//		PageUtil pageUtil = new PageUtil(total_items, page_size, orgRequestSet,
//				jspName);
//		String page_list = pageUtil.getPageLinks();
//		return page_list;
//	}

//	public static String makePaging(RequestSet requestSet, String jspName,
//			int total_items) throws SQLException, Exception {
//
//		return makePaging(requestSet, jspName, total_items,
//				ConfigClass.PAGE_SIZE);
//	}

//	public static PageUtil makePageUtil(RequestSet requestSet,
//			DBHandler dbHandler, String xmlID, String jspName)
//			throws SQLException, Exception {
//
//		return makePageUtil(requestSet, dbHandler, xmlID, jspName,
//				ConfigClass.PAGE_SIZE);
//	}

//	public static PageUtil makePageUtil(RequestSet orgRequestSet,
//			DBHandler dbHandler, String xmlID, String jspName, int page_size)
//			throws SQLException, Exception {
//
//		// /글목록 총갯수
//		ResultTable rtCnt = dbHandler.executeXmlQuery(xmlID, orgRequestSet);
//		return new PageUtil(rtCnt.asInt(), page_size, orgRequestSet, jspName);
//
//	}

//	public static PageUtil makePageUtil(RequestSet orgRequestSet,
//			String jspName, int total_items, int page_size)
//			throws SQLException, Exception {
//		return new PageUtil(total_items, page_size, orgRequestSet, jspName);
//	}

//	public static PageUtil makePageUtil(RequestSet requestSet, String jspName,
//			int total_items) throws SQLException, Exception {
//
//		return makePageUtil(requestSet, jspName, total_items,
//				ConfigClass.PAGE_SIZE);
//	}

	/**
	 * 해쉬테이블을 가지고 html hidden폼을 만든다.
	 * 
	 * @return String
	 */
	public static String makeHiddenForm(Hashtable oht) {
		String str = "";
		if (oht == null) {
			return str;
		}
		Object[] oa = oht.keySet().toArray();
		for (int i = 0; i < oa.length; i++) {
			str += "<input type='hidden' name='" + oa[i].toString() + "' ";
			str += " value='" + oht.get(oa[i]).toString() + "'>\n";
		}
		return str;
	}

	/** 해쉬테이블을 가지고 html select폼에 들어갈 option 태그를 생성한다. */
	public static String makeSelectOption(HashMap olhm, String strValue) {
		if (olhm == null) {
			return "";
		}
		Object[] oa = olhm.keySet().toArray();
		String str = "";
		for (int i = 0; i < oa.length; i++) {
			str += "<option value='" + oa[i].toString() + "' ";
			if (oa[i].toString().equals(strValue)) {
				str += " selected ";
			}
			str += " >" + olhm.get(oa[i]).toString() + "</option>\n";
		}
		return str;
	}

	public static String makeSelectOption(LinkedHashMap olhm, String strValue) {
		if (olhm == null) {
			return "";
		}
		Object[] oa = olhm.keySet().toArray();
		String str = "";
		for (int i = 0; i < oa.length; i++) {
			str += "<option value='" + oa[i].toString() + "' ";
			if (oa[i].toString().equals(strValue)) {
				str += " selected ";
			}
			str += " >" + olhm.get(oa[i]).toString() + "</option>\n";
		}
		return str;
	}

//	public static String makeSelectOption(ResultTable rtList, String keyCol,
//			String valueCol, String selectValue) {
//
//		if (rtList == null || rtList.hasNoEntity()) {
//			return "";
//		}
//		StringBuffer sb = new StringBuffer();
//		int key=0;
//		int selValue=0;
//		for (int i = 0; i < rtList.getRowCount(); i++) {
//			key = Integer.parseInt(rtList.get(i, keyCol));
//			
//			if(selectValue==null||"".equals(selectValue)){
//				selValue=0;
//			}else{
//				selValue = Integer.parseInt(selectValue);
//			}
//			sb.append("<option value='").append(rtList.get(i, keyCol)).append(
//					"'");
//			if (key==selValue) {
//				sb.append(" selected ");
//			}
//			sb.append(">");
//			sb.append(rtList.get(i, valueCol)).append("</option>\n");
//		}
//		return sb.toString();
//	}
	
//	public static String makeSelectOption3(ResultTable rtList, String keyCol,
//			String valueCol, String selectValue) {
//		
//		if (rtList == null || rtList.hasNoEntity()) {
//			return "";
//		}
//		StringBuffer sb = new StringBuffer();
//		int key=0;
//		int selValue=0;
//		
//		sb.append("<option value='' selected=\"selected\">직업을 선택하세요.</option>");
//		
//		for (int i = 0; i < rtList.getRowCount(); i++) {
//			key = Integer.parseInt(rtList.get(i, keyCol));
//			
//			if(selectValue==null||"".equals(selectValue)){
//				selValue=0;
//			}else{
//				selValue = Integer.parseInt(selectValue);
//			}
//			sb.append("<option value='").append(rtList.get(i, keyCol)).append(
//					"'");
//			if (key==selValue) {
//				sb.append(" selected ");
//			}
//			sb.append(">");
//			sb.append(rtList.get(i, valueCol)).append("</option>\n");
//		}
//		return sb.toString();
//	}
	
	
//	public static String makeSelectOption2(ResultTable rtList, String keyCol,
//			String valueCol, String selectValue) {
//
//		if (rtList == null || rtList.hasNoEntity()) {
//			return "";
//		}
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < rtList.getRowCount(); i++) {
//			sb.append("<option value='").append(rtList.get(i, keyCol)).append("'");
//			if (selectValue.equals(rtList.get(i, keyCol))) {
//				sb.append(" selected ");
//			}
//			sb.append(">");
//			sb.append(rtList.get(i, valueCol)).append("</option>\n");
//		}
//		return sb.toString();
//	}
//	
	
	public static String makeSelectOption(List list, String keyCol, String valueCol, String selectValue) {
	
		if (list == null || list.size() <= 0 ) {
			return "";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			map = (Map)list.get(i);
			
			sb.append("<option value='").append(map.get(keyCol)).append("'");
			if (selectValue.equals(map.get(keyCol))) {
				sb.append(" selected ");
			}
			sb.append(">");
			sb.append(map.get(valueCol)).append("</option>\n");
		}
		return sb.toString();
	}
	
	// List 인입시 작동하게 하기 위해 추가함
	public static String makeSelectOption(List<GdCode> list, String selectValue) {
		
		if (list == null || list.size() <= 0 ) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();

		for(GdCode code : list) {
			sb.append("<option value='").append(code.getItemnm()).append("'");
			if (selectValue.equals(code.getItemnm())) {
				sb.append(" selected ");
			}
			sb.append(">");
			sb.append(code.getItemcd()).append("</option>\n");
		}
		
		return sb.toString();
	}
	
	/***************************************************************************
	 * 인수로 받은 스트링을 비교하여 'selected'로 반환한다
	 **************************************************************************/
	public static String getSelected(String str1, String str2) {
		String res = "";
		if (str1 != null && str2 != null) {
			if (str1.equals(str2)) {
				res = "selected";
			} else {
				res = "";
			}
		} else {
			res = "";
		}
		return res;
	}

	/***************************************************************************
	 * 인수로 받은 스트링을 비교하여 'checked'로 반환한다
	 **************************************************************************/
	public static String getChecked(String str1, String str2) {
		String res = "";
		if (str1 != null && str2 != null) {
			if (str1.equals(str2)) {
				res = " checked";
			} else {
				res = "";
			}
		} else {
			res = "";
		}
		return res;
	}

	public static String getChecked(String str, HashMap olhm) {
		if (olhm == null) {
			return "";
		}
		String value = (String) olhm.get(str);
		if (value != null) {
			value = " checked ";
		} else {
			value = "";
		}
		return value;
	}

	/**
	 * 사용자가 download.jsp나 웹url을 지정하여 사용할 경우 사용
	 * 
	 * @param url
	 *            다운로드 URL
	 * @param realPath
	 *            실제시스템 전체 경로
	 * @param fileName
	 *            화일명
	 * @return String 다운로드 URL
	 */
	public static String confirmDownloadURL(String url, String realPath,
			String fileName) {
		
		if (!FileUtil.existsFile(realPath, fileName)) {
			url = "javascript:alert('파일이 존재하지 않습니다.')";
		}
		
		return url;
	}

	/**
	 * 시스템의 기본 업로드 디렉토리에 저장할 경우 하위디렉토리만 명시함 (download.jsp를 사용할경우만 사용)
	 * 
	 * @param subPath
	 * @param fileName
	 * @return String 다운로드 URL
	 */
	public static String confirmDownloadURL(String subPath, String fileName)
			throws UnsupportedEncodingException {
		//String filePath = ConfigClass.UPLOAD_PATH + subPath;
		String filePath = ConfigClass.FILE_PATH + subPath;
		String url = getDownloadURL(subPath, fileName);
		return confirmDownloadURL(url, filePath, fileName);
	}

	public static String getDownloadURL(String subPath, String fileName)
			throws UnsupportedEncodingException {

		String url = ConfigClass.HOME_URL
				+ "download?attach_path=" + subPath;
		url += "&attach=" + WebUtil.getEncode(fileName);
		return url;
	}

	public static String getEncode(String str)
			throws UnsupportedEncodingException {
		if (str == null || str.length() == 0) {
			return "";
		}
		return URLEncoder.encode(str);
	}

	/***************************************************************************
	 * HTML의 모든 특수 문자를 "&amp;xxx;"와 같이 변환
	 * 
	 * @since 2000/11/22
	 * @return 변환된 문자열
	 **************************************************************************/
	public static String simpleEncode(String src) {
		String res = "";
		char ch;

		for (int i = 0; i < src.length(); i++) {
			switch (ch = src.charAt(i)) {
			case '"':
				res += "&quot;";
				break;
			case '&':
				res += "&amp;";
				break;
			case '<':
				res += "&lt;";
				break;
			case '>':
				res += "&gt;";
				break;
			case '\n':
				res += "\n<br>";
				break;
			default:
				res += ch;
			}
		}

		return res;
	}

	public static String getNewMark(Date date) {
		return getNewMark(date, date);
	}

	public static String getNewMark(Date date1, Date date2) {

		String s = "";

		if (DateUtil.checkNewDate(date1) || DateUtil.checkNewDate(date2)) {
			s = "<img src=\"../board/images/new.gif\"  border=0 align=\"absmiddle\">";
		}
		return s;
	}
	
	public static HashMap parseURL(String strUrl){ 
		HashMap pareUrl = new HashMap();
		try {
			URL url = new URL(strUrl);
			pareUrl.put("protocol", url.getProtocol());
			pareUrl.put("host", url.getHost());
			pareUrl.put("port", url.getPort()+"");
			pareUrl.put("authority", url.getAuthority());
			pareUrl.put("userInfo", url.getUserInfo());
			pareUrl.put("path", url.getPath());
			pareUrl.put("query", url.getQuery());
			pareUrl.put("ref", url.getRef());
			pareUrl.put("file", url.getFile());
		}catch (MalformedURLException me){
			me.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	
		return pareUrl;
	}


	public static String urlEncode(String strUrl){
		
		return URLEncoder.encode(strUrl);
		
	}
	
	public static String urlDecode(String strUrl){
		
		return URLDecoder.decode(strUrl);
		
	}
	

	/*
	 * md5를 사용하기 위하여 만든 메서드 
	 */
	public static String md5D(String input) throws NoSuchAlgorithmException {

		  Base64 base64 = new Base64();
		  MessageDigest md = MessageDigest.getInstance("MD5");
		  md.update(input.getBytes());

		  return new String(base64.encode(md.digest()));
	}
	
	/**
	 * [설명] 쿠키를 셋팅한다.
	 * @param response
	 * @param key
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public static boolean setCookies(HttpServletResponse response, String key, String val) throws Exception{
		return setCookies(response, key, val, 60*60*24);
	}
	public static boolean setCookies(HttpServletResponse response, String key, String val, int lifeTime) throws Exception{
		boolean b = false;
		try {
			Cookie cooki = new Cookie(key, URLEncoder.encode(val, ConfigClass.ENCODING));
			cooki.setMaxAge(lifeTime);
			cooki.setPath("/");
			response.addCookie(cooki);
			b = true;
		}catch (Exception e){
			b = false;
		}
		return b;
	}
		
	/**
	 * [설명] 쿠키의 value를 추출한다.
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getCookies(HttpServletRequest request, String key) throws Exception{
		int i = 0;
		String val = "";
		Cookie cooki = null;
		Cookie cookis[] = request.getCookies();
		
		if(null == key || "".equals(key)){
			return val;
		}
		
		if (  null != cookis && 0 < cookis.length ) {
			i = 0;
			while(i < cookis.length){
				cooki = cookis[i];
				if ( key.equals(cooki.getName()) ) {
					val = URLDecoder.decode(cooki.getValue(), ConfigClass.ENCODING);
				}
				i++;
			}			
		}
		
		return val;
	}
	
	// 
	public static String go_ajax(String type, String url, String success_callback){
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='JavaScript'>");
		// 밑에 \앞에 공백을 지우면 스크립트 오류가 나 에러 메세지를 확인 할 수 없는 경우가 생긴다.
		sb.append("$.ajax(	{");
		sb.append("	type: '"+type +"', ");
		sb.append("	async : true ,");
		sb.append("	url : '" + url+ "' ,");
		sb.append("	dataType : xml , ");
		sb.append("	timeout : 30000 , ");
		sb.append("	cache : false , ");
		sb.append("	contentType : 'application/x-www-form-urlendcoded; charset=UTF-8' , ");
		sb.append("	error : function(request, status, error){alert('error : ' + request.status + '\n message' + request.responseText);}, ");
		sb.append("	success : " + success_callback + " , ");
		sb.append("	complete : function(){} ");
		sb.append("	}) ");
		return sb.toString();
	}
	
	/**
	 * 오늘본 상품 cookie 담기 - JSON 형태로 cookie 에 저장
	 * @param request
	 * @param response
	 * @param goodsInfo_map
	 * @throws Exception
	 */
	public static void setTodayGoodsCookie(HttpServletRequest request,HttpServletResponse response, Map goodsInfo_map) throws Exception{
		
		String strCookieNm	 =	"todayGoods";
		String strTodayGoods = getCookies(request, strCookieNm);
		
		JSONObject todayGoods_json = null;
		JSONArray list = null;
				
		//오늘본 상품 정보가 있을경우
		if(!"".equals(strTodayGoods)){
			todayGoods_json = (JSONObject)JSONValue.parse(strTodayGoods);
			
			list = (JSONArray)todayGoods_json.get(strCookieNm);
			
			Map<String,String> goods_map = new HashMap();
			
			//기존 쿠키데이터에 존재하는 상품은 상품순서 재정렬을 위해 삭제하고 다시 저장한다.
			int list_length = list.size()-1;
			for(int i=list_length ; i >= 0 ; i--){
				goods_map = (Map<String,String>)list.get(i);
				
				//기존 쿠키 데이터에 동일한 상품이 있으면 list 삭제후 재설정(오늘본상품 순서 재정렬을 위해)
				if(goodsInfo_map.get("goodsno").equals(goods_map.get("goodsno"))){
					list.remove(i);
				}
			}
			
			//오늘본 상품에 cookie 추가
			list.add(goodsInfo_map);	
			
		//오늘본 상품 정보가 없을 경우	
		}else{
			list = new JSONArray();
			list.add(goodsInfo_map);	
		}
		
		todayGoods_json = new JSONObject();
		
		//오늘본 상품 최종 json 데이터 생성 및 문자열로 변환
		todayGoods_json.put(strCookieNm, list);
		strTodayGoods = todayGoods_json.toJSONString();
		
		//cookie 에 저장 
		setCookies(response, strCookieNm, strTodayGoods);

	}

	/**
	 * 오늘본 상품 ArrayList로 반환 - Map<String,String> 데이터를 ArrayList에 담아서 반환
	 * @param request
	 * @param response
	 * @return ArrayList - Map<String,String>
	 * @throws Exception
	 */
	public static ArrayList getTodayGoodsCookie(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String strCookieNm	 =	"todayGoods";
		String strTodayGoods = getCookies(request, strCookieNm);
		
		ArrayList arrayList = new ArrayList();
		JSONObject todayGoods_json = null;
		JSONArray json_list = null;
		
		//오늘본 상품 정보가 있을경우
		if(!"".equals(strTodayGoods)){
			todayGoods_json = (JSONObject)JSONValue.parse(strTodayGoods);
			
			json_list = (JSONArray)todayGoods_json.get(strCookieNm);
			
			Map<String,String> goods_map = new HashMap();
			for(int i=0 ; i < json_list.size() ; i++){
				goods_map = (Map<String,String>)json_list.get(i);
				arrayList.add(goods_map);
			}	
		}
		
		return arrayList;
	}
	
	/**
	 * 최근 검색어 cookie 담기
	 * @param request
	 * @param response
	 * @param word
	 * @throws Exception
	 */
	public static void setRecentWordCookie(HttpServletRequest request, HttpServletResponse response, String word) throws Exception {
		
		String cookieNm = "recentWord";
		String recentWord = getCookies(request, cookieNm);
		
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
				
		//최근 검색어가 있을경우
		if (!"".equals(recentWord)){
			jsonObj = (JSONObject)JSONValue.parse(recentWord);
			jsonArr = (JSONArray)jsonObj.get(cookieNm);
			
			int list_length = jsonArr.size()-1;
			for(int i=list_length ; i >= 0 ; i--){
				String wd = (String)jsonArr.get(i);
				if(word.equals(wd)){
					jsonArr.remove(i);
				}
			}
			
			//검색어 cookie 추가
			jsonArr.add(word);	
			
		//최근 검색어가 없을 경우	
		}else{
			jsonArr = new JSONArray();
			jsonArr.add(word);	
		}
		
		jsonObj = new JSONObject();
		
		//최근검색어 최종 json 데이터 생성 및 문자열로 변환
		jsonObj.put(cookieNm, jsonArr);
		recentWord = jsonObj.toJSONString();
		
		//cookie 에 저장 
		setCookies(response, cookieNm, recentWord);
	}

	/**
	 * 최근검색어 ArrayList로 반환
	 * @param request
	 * @param response
	 * @return ArrayList - Map<String,String>
	 * @throws Exception
	 */
	public static ArrayList<String> getRecentWordCookie(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String cookieNm	 =	"recentWord";
		String recentWord = getCookies(request, cookieNm);
		
		ArrayList<String> arrayList = new ArrayList<String>();
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		
		//오늘본 상품 정보가 있을경우
		if (!"".equals(recentWord)){
			jsonObj = (JSONObject)JSONValue.parse(recentWord);
			jsonArr = (JSONArray)jsonObj.get(cookieNm);
			
			for(int i=0 ; i < jsonArr.size() ; i++){
				String wd = (String)jsonArr.get(i);
				arrayList.add(wd);
			}	
		}
		
		return arrayList;
	}
	
	/**
	 * 최근 검색어 cookie 삭제
	 * @param request
	 * @param response
	 * @param word
	 * @throws Exception
	 */
	public static void delRecentWordCookie(HttpServletRequest request, HttpServletResponse response, String word) throws Exception {
		
		String cookieNm = "recentWord";
		String recentWord = getCookies(request, cookieNm);
		
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
				
		//최근 검색어가 있을경우
		if (!"".equals(recentWord)){
			jsonObj = (JSONObject)JSONValue.parse(recentWord);
			jsonArr = (JSONArray)jsonObj.get(cookieNm);
			
			int list_length = jsonArr.size()-1;
			for(int i=list_length ; i >= 0 ; i--){
				String wd = (String)jsonArr.get(i);
				if(word.equals(wd)){
					jsonArr.remove(i);
				}
			}	
		}
		
		jsonObj = new JSONObject();
		
		//최근검색어 최종 json 데이터 생성 및 문자열로 변환
		jsonObj.put(cookieNm, jsonArr);
		recentWord = jsonObj.toJSONString();
		
		//cookie 에 저장 
		setCookies(response, cookieNm, recentWord);
	}
	
	// 경고창후 이동 자바스크립트 생성
	public static String getBrandIcon(String sort) {
		String str = "home";
		
		if(sort.equals("1")){
			str = "android";
		}else if(sort.equals("2")){
			str = "btc";
		}else if(sort.equals("3")){
			str = "css3";
		}else if(sort.equals("4")){
			str = "empire";
		}else if(sort.equals("5")){
			str = "facebook";
		}else if(sort.equals("6")){
			str = "cc-visa";
		}else if(sort.equals("7")){
			str = "meanpath";
		}else if(sort.equals("8")){
			str = "linux";
		}else if(sort.equals("9")){
			str = "git";
		}else if(sort.equals("10")){
			str = "adn";
		}else if(sort.equals("11")){
			str = "android";
		}else if(sort.equals("12")){
			str = "ge";
		}else if(sort.equals("13")){
			str = "ra";
		}else if(sort.equals("14")){
			str = "qq";
		}else if(sort.equals("15")){
			str = "skype";
		}else if(sort.equals("16")){
			str = "windows";
		}else if(sort.equals("17")){
			str = "steam";
		}else if(sort.equals("18")){
			str = "vine";
		}
		
		return str;
	}
	
	//CodeItem Selected( option value=itemcd )
	public static String makeSelectCodeItem(List<GdCode> list, String option) throws Exception {
		
		if(list == null || list.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		
		//<option value='${list.itemcd}' ${stringUtil:selected(faqVO.itemcd, faqVO.sitemcd)} >${list.itemnm}</option>
		for(int i=0; i<list.size(); i++){
			sb.append("<option value='").append(list.get(i).getItemcd()).append("'");
			if(list.get(i).getItemcd().equals(option)) {
				sb.append(" selected ");
			}
			sb.append(">");
			sb.append(list.get(i).getItemnm()).append("</option>\n");
		}
		return sb.toString();
	}
	
	//CodeItem Selected( option value=itemnm )
	public static String makeSelectCodeItem2(List<GdCode> list, String option) throws Exception {
		
		if(list == null || list.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		
		//<option value='${list.itemcd}' ${stringUtil:selected(faqVO.itemcd, faqVO.sitemcd)} >${list.itemnm}</option>
		for(int i=0; i<list.size(); i++){
			sb.append("<option value='").append(list.get(i).getItemnm()).append("'");
			if(list.get(i).getItemnm().equals(option)) {
				sb.append(" selected ");
			}
			sb.append(">");
			sb.append(list.get(i).getItemnm()).append("</option>\n");
		}
		return sb.toString();
	}
	
}