/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.admin.service.ftp;

import java.io.*;
import java.text.DateFormat;
import java.util.StringTokenizer;
import java.util.Vector;
/*파일 전송 프로토콜(File Transfer Protocol, FTP)은 TCP/IP 프로토콜을 가지고 서버와 클라이언트 사이의 파일 전송을 하기 위한 프로토콜이다.*/
public class FTPDefault {
    //FEATURES
    public static final boolean NATIVE_COMMANDS = true;
	/**
	 * true이면 파일 시스템에서 무언가를 변경하는 모든 작업 (업로드 및 원시 명령 제외)이 허용됩니다.
	*/
	public static final boolean READ_ONLY = false;
	//true이면 READ_ONLY = true 인 경우에도 업로드가 허용됩니다.
	public static final boolean ALLOW_UPLOAD = true;

    //특정 디렉토리에서만 탐색 및 파일 조작 허용
	private static final boolean RESTRICT_BROWSING = false;
    //true 인 경우 사용자는 RESTRICT_PATH에서만 검색 할 수 있습니다.,
    //false 인 경우, 사용자는 RESTRICT_PATH 이외의 모든 디렉토리를 탐색 할 수 있습니다.
    private static final boolean RESTRICT_WHITELIST = false;
    //경로를 세미콜론으로 구분
    //private static final String RESTRICT_PATH = "C:\\CODE;E:\\"; //Win32: Case important!!
	public static final String RESTRICT_PATH = "/etc;/var";

    //업로드 모니터 윈도우를 초단위로 새로 고침
	public static final int UPLOAD_MONITOR_REFRESH = 2;
	//편집 필드의 열 수입니다.
	public static final int EDITFIELD_COLS = 85;
	//The number of rows for the edit field
	public static final int EDITFIELD_ROWS = 30;
	//편집 필드의 행 수입니다.
	public static final boolean USE_POPUP = true;
	/**
	 * If USE_DIR_PREVIEW = true, then for every directory a tooltip will be
	 * created (hold the mouse over the link) with the first DIR_PREVIEW_NUMBER entries.
	 * This can yield to performance issues. Turn it off, if the directory loads to slow.
	 */
	public static final boolean USE_DIR_PREVIEW = false;
	public static final int DIR_PREVIEW_NUMBER = 10;
	/**
	 * The name of an optional CSS Stylesheet file
	 */
	public static final String CSS_NAME = "Browser.css";
	/**
	 * The compression level for zip file creation (0-9)
	 * 0 = No compression
	 * 1 = Standard compression (Very fast)
	 * ...
	 * 9 = Best compression (Very slow)
	 */
	public static final int COMPRESSION_LEVEL = 1;
	/**
	 * The FORBIDDEN_DRIVES are not displayed on the list. This can be usefull, if the
	 * server runs on a windows platform, to avoid a message box, if you try to access
	 * an empty removable drive (See KNOWN BUGS in Readme.txt).
	 */
	public static final String[] FORBIDDEN_DRIVES = {"a:\\"};

	/**
	 * Command of the shell interpreter and the parameter to run a programm
	 */
	private static final String[] COMMAND_INTERPRETER = {"cmd", "/C"}; // Dos,Windows
	//private static final String[] COMMAND_INTERPRETER = {"/bin/sh","-c"}; 	// Unix

	/**
	 * Max time in ms a process is allowed to run, before it will be terminated
	 */
	private static final long MAX_PROCESS_RUNNING_TIME = 30 * 1000; //30 seconds

	//Button names
	public static final String SAVE_AS_ZIP = "Download selected files as (z)ip";
	public static final String RENAME_FILE = "(R)ename File";
	public static final String DELETE_FILES = "(Del)ete selected files";
	public static final String CREATE_DIR = "Create (D)ir";
	public static final String CREATE_FILE = "(C)reate File";
	public static final String MOVE_FILES = "(M)ove Files";
	public static final String COPY_FILES = "Cop(y) Files";
	public static final String LAUNCH_COMMAND = "(L)aunch external program";
	public static final String UPLOAD_FILES = "Upload";

	//Normally you should not change anything after this line
	//----------------------------------------------------------------------------------
	//Change this to locate the tempfile directory for upload (not longer needed)
	public static String tempdir = ".";
	public static String VERSION_NR = "1.2";
	public static DateFormat dateFormat = DateFormat.getDateTimeInstance();
	
	public static String toHangul(String _str) {
		String strRet = null;

		try {
			strRet = java.net.URLEncoder.encode(_str, "utf-8"); // , "euc-kr"
			strRet = new String(strRet.getBytes("8859_1"),"KSC5601");
		} catch(Exception ue) {// UnsupportedEncodingException
			System.out.println("[EXCEPTION] Browser.jsp toHangul()" + ue.toString());
		}

		return strRet;
	}
	
	// 깨진 한글을 복원해주는 것 같다.
	public static String fromHangul(String _str) {
		String strRet = null;

		try {
/*			URLEncoder 클래스는 일반 문자열을 웹에서 통용되는 'x-www-form-urlencoded' 형식으로 변환하는 역할을 담당
			대소문자, 숫자, 밑줄을 제외한 URL에 있는 문자를 코드화하는 것*/
//			URLDecoder 클래스는 URLEncoder로 인코딩된 결과를 디코딩하는 클래스
			strRet = java.net.URLDecoder.decode(_str, "utf-8"); // , "euc-kr"
			strRet = new String(strRet.getBytes("8859_1"),"KSC5601");
		} catch(Exception ue) {
			System.out.println("[EXCEPTION] Browser.jsp fromHangul()" + ue.toString());
		}

		return strRet;
	}
	
	public static Vector<File> expandFileList(String[] files, boolean inclDirs) {
		Vector<File> v = new Vector<File>();
		if (files == null) return v;
		
		for (int i = 0; i < files.length; i++)
			v.add(new File(fromHangul(files[i])));
		
		for (int i = 0; i < v.size(); i++) {
			File f = v.get(i);
			
			// isDirectory() 해당 패스에서 디렉토리(폴더)가 존재하는지 확인. 없으면 false 리턴
			if (f.isDirectory()) {
				// listFiles() 디렉토리의 파일목록(디렉토리 포함)을 File배열로 반환한다.
				File[] fs = f.listFiles();
				for (int n = 0; n < fs.length; n++)
					v.add(fs[n]);
				
				if (!inclDirs) {
					v.remove(i);
					i--;
				}
			}
		}
		return v;
	}

	/**
	 * 절대 경로를 만드는 방법입니다.
	 * @param dir 루트 디렉토리
	 * @param name 새로운 디렉토리의 이름
	 * @return name이 절대 디렉토리이면 name을 반환하고 그렇지 않으면 dir + name을 반환합니다.
	 */
	public static String getDir(String dir, String name) {
		if (!dir.endsWith(File.separator)) dir = dir + File.separator;
		File mv = new File(name);
		String new_dir = null;
		if (!mv.isAbsolute()) {
			new_dir = dir + name;
		}
		else new_dir = name;
		return new_dir;
	}

	/**
	 * 이 메서드는 크기에 따라 kbytes 또는 Mbytes 크기의 바이트 크기를 변환합니다.
	 *     @param size The size in bytes
	 *     @return String with size and unit
	 */
	public static String convertFileSize(long size) {
		int divisor = 1;
		String unit = "bytes";
		if (size >= 1024 * 1024) {
			divisor = 1024 * 1024;
			unit = "MB";
		}
		else if (size >= 1024) {
			divisor = 1024;
			unit = "KB";
		}
		if (divisor == 1) return size / divisor + " " + unit;
		String aftercomma = "" + 100 * (size % divisor) / divisor;
		if (aftercomma.length() == 1) aftercomma = "0" + aftercomma;
		return size / divisor + "." + aftercomma + " " + unit;
	}

	/**
	 * 모든 데이터를에서 밖으로 복사합니다.
	 * 	@param in the input stream
	 *	@param out the output stream
	 *	@param buffer copy buffer
	 */
	public static void copyStreams(InputStream in, OutputStream out, byte[] buffer) throws IOException {
		copyStreamsWithoutClose(in, out, buffer);
		in.close();
		out.close();
	}

	/**
	 * 모든 데이터를에서 밖으로 복사합니다.
	 * 	@param in the input stream		인풋스트림으로부터
	 *	@param out the output stream	아웃풋스트림으로
	 *	@param buffer copy buffer		입력된 byte크기만큼의 buffer 복사
	 */
	public static void copyStreamsWithoutClose(InputStream in, OutputStream out, byte[] buffer)
			throws IOException {
		int b;
		while ((b = in.read(buffer)) != -1)
			out.write(buffer, 0, b);
	}

	/**
	 * 파일 이름의 확장명에 따라 파일의 MIME 유형을 반환합니다.
	 */
	public static String getMimeType(String fName) {
		fName = fName.toLowerCase();
		if (fName.endsWith(".jpg") || fName.endsWith(".jpeg") || fName.endsWith(".jpe")) return "image/jpeg";
		else if (fName.endsWith(".gif")) return "image/gif";
		else if (fName.endsWith(".pdf")) return "application/pdf";
		else if (fName.endsWith(".htm") || fName.endsWith(".html") || fName.endsWith(".shtml")) return "text/html";
		else if (fName.endsWith(".avi")) return "video/x-msvideo";
		else if (fName.endsWith(".mov") || fName.endsWith(".qt")) return "video/quicktime";
		else if (fName.endsWith(".mpg") || fName.endsWith(".mpeg") || fName.endsWith(".mpe")) return "video/mpeg";
		else if (fName.endsWith(".zip")) return "application/zip";
		else if (fName.endsWith(".tiff") || fName.endsWith(".tif")) return "image/tiff";
		else if (fName.endsWith(".rtf")) return "application/rtf";
		else if (fName.endsWith(".mid") || fName.endsWith(".midi")) return "audio/x-midi";
		else if (fName.endsWith(".xl") || fName.endsWith(".xls") || fName.endsWith(".xlv")
				|| fName.endsWith(".xla") || fName.endsWith(".xlb") || fName.endsWith(".xlt")
				|| fName.endsWith(".xlm") || fName.endsWith(".xlk")) return "application/excel";
		else if (fName.endsWith(".doc") || fName.endsWith(".dot")) return "application/msword";
		else if (fName.endsWith(".png")) return "image/png";
		else if (fName.endsWith(".xml")) return "text/xml";
		else if (fName.endsWith(".svg")) return "image/svg+xml";
		else if (fName.endsWith(".mp3")) return "audio/mp3";
		else if (fName.endsWith(".ogg")) return "audio/ogg";
		else return "text/plain";
	}

	/**
	 * 
일부 중요한 문자 (int)를 해당 html 문자열로 변환합니다.
	 */
	public static String conv2Html(int i) {
		if (i == '&') return "&amp;";
		else if (i == '<') return "&lt;";
		else if (i == '>') return "&gt;";
		else if (i == '"') return "&quot;";
		else return "" + (char) i;
	}

	/**
	 * Converts a normal string to a html conform string
	 */
	public static String conv2Html(String st) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < st.length(); i++) {
			buf.append(conv2Html(st.charAt(i)));
		}
		return buf.toString();
	}

	/**
	 * Starts a native process on the server
	 * 	@param command the command to start the process
	 *	@param dir the dir in which the process starts
	 */
	public static String startProcess(String command, String dir) throws IOException {
		StringBuffer ret = new StringBuffer();
		String[] comm = new String[3];
		comm[0] = COMMAND_INTERPRETER[0];
		comm[1] = COMMAND_INTERPRETER[1];
		comm[2] = command;
		long start = System.currentTimeMillis();
		try {
			//Start process
			Process ls_proc = Runtime.getRuntime().exec(comm, null, new File(dir));
			//Get input and error streams
			BufferedInputStream ls_in = new BufferedInputStream(ls_proc.getInputStream());
			BufferedInputStream ls_err = new BufferedInputStream(ls_proc.getErrorStream());
			boolean end = false;
			while (!end) {
				int c = 0;
				while ((ls_err.available() > 0) && (++c <= 1000)) {
					ret.append(conv2Html(ls_err.read()));
				}
				c = 0;
				while ((ls_in.available() > 0) && (++c <= 1000)) {
					ret.append(conv2Html(ls_in.read()));
				}
				try {
					ls_proc.exitValue();
					//if the process has not finished, an exception is thrown
					//else
					while (ls_err.available() > 0)
						ret.append(conv2Html(ls_err.read()));
					while (ls_in.available() > 0)
						ret.append(conv2Html(ls_in.read()));
					end = true;
				}
				catch (IllegalThreadStateException ex) {
					//Process is running
				}
				//The process is not allowed to run longer than given time.
				if (System.currentTimeMillis() - start > MAX_PROCESS_RUNNING_TIME) {
					ls_proc.destroy();
					end = true;
					ret.append("!!!! Process has timed out, destroyed !!!!!");
				}
				try {
					Thread.sleep(50);
				}
				catch (InterruptedException ie) {}
			}
		}
		catch (IOException e) {
			ret.append("Error: " + e);
		}
		return ret.toString();
	}

	/**
	 * Converts a dir string to a linked dir string
	 * 	@param dir the directory string (e.g. /usr/local/httpd)
	 *	@param browserLink web-path to Browser.jsp
	 */
	public static String dir2linkdir(String dir, String browserLink, int sortMode) {
//		System.out.println("(dir2linkdir() dir:-1>" + dir);
//		System.out.println("(dir2linkdir() dir:-2>" + toHangul(dir));
//		System.out.println("(dir2linkdir() dir:-3>" + fromHangul(dir));

		File f = new File(dir);
		StringBuffer buf = new StringBuffer();
		while (f.getParentFile() != null) {
			if (f.canRead()) {
				String encPath = toHangul(f.getAbsolutePath());
				buf.insert(0, "<a href=\"" + browserLink + "?sort=" + sortMode + "&amp;dir="
						+ encPath + "\">" + conv2Html(f.getName()) + File.separator + "</a>");
			}
			else buf.insert(0, conv2Html(f.getName()) + File.separator);
			f = f.getParentFile();
		}
		if (f.canRead()) {
			String encPath = toHangul(f.getAbsolutePath());
			buf.insert(0, "<a href=\"" + browserLink + "?sort=" + sortMode + "&amp;dir=" + encPath
					+ "\">" + conv2Html(f.getAbsolutePath()) + "</a>");
		}
		else buf.insert(0, f.getAbsolutePath());
		return buf.toString();
	}

	/**
	 *	지정된 파일명이 압축 된 파일에 가까울 경우 true를 리턴합니다.
	 */
	public static boolean isPacked(String name, boolean gz) {
		return (name.toLowerCase().endsWith(".zip") 
				|| name.toLowerCase().endsWith(".jar")
				|| (gz && name.toLowerCase().endsWith(".gz")) 
				|| name.toLowerCase().endsWith(".war"));
	}

	/**
	 *	RESTRICT_BROWSING = true의 경우,이 메소드는 패스가 허가되어 있는지 어떤지를 확인합니다.
	 */
	public static boolean isAllowed(File path, boolean write) throws IOException{
		if (READ_ONLY && write) return false;
		
		if (RESTRICT_BROWSING) {
            StringTokenizer stk = new StringTokenizer(RESTRICT_PATH, ";");
            while (stk.hasMoreTokens()){
			    if (path!=null && path.getCanonicalPath().startsWith(stk.nextToken()))
                    return RESTRICT_WHITELIST;
            }
            return !RESTRICT_WHITELIST;
		}
		else return true;
	}
}
