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

import com.wepinit.wepinit_shop.xmall.admin.vo.ftp.ConvertFileVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.ftp.EntryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.ftp.FtpVO;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;



@Service
public class FtpService {
	public boolean isAllowedIP(String ipAddress) {
		final String[] REGISTER_IP = {
			"119.69.131.87","192.168.0.254", "127.0.0.1"
		};

		boolean isTrustedIP = false;
		
		for(String ip : REGISTER_IP) {
			if(ip.equals(ipAddress)) {
				isTrustedIP = true;
			}
		}
		if(isTrustedIP == false) {
			return false;
		}
		return true;
	}
	
	public void paramFile(FtpVO ftpVO, HttpServletResponse resp) throws IOException {
		String param = ftpVO.getFile();
		String dir = ftpVO.getDir();
		PrintWriter out = resp.getWriter();
		param = FTPDefault.fromHangul(param);
		
		// 입력된 값을 모두 디코딩하여 파일 생성
		File f = new File(param);
		
		// 제한여부를 판단한다 ?? 
		if(FTPDefault.isAllowed(f, false)) { // 경로에 접근할 수 없으면 ?
			// 경로와 에러메세지 세팅
			dir = f.getParent();
			ftpVO.setError("You are not allowed to access "+f.getAbsolutePath());
		// exists() : 파일의 존재여부 리턴
		// canRead() : 파일을 읽을 수 있는지 리턴
		} else if(f.exists() && f.canRead()) { // 파일도 있고 읽을수도 있으면?
			if(FTPDefault.isPacked(f.getName(), false)) {
				//압축파일이라면 아무것도 하지 않는다.
			} else {
				// 확장명에 따른 마임타입 얻어오기
				String mimeType = FTPDefault.getMimeType(f.getName());
				resp.setContentType(mimeType);
				
				if(mimeType.equals("text/plain")) {
					//"Content-disposition: inline"은 브라우저 인식 파일확장자를 가진 파일들에 대해서는 
					//웹브라우저 상에서 바로 파일을 자동으로 보여줄 수 있어서 의미상인 멀티파트 메시지를 표현하는데 있다. 
					//그외의 파일들에 대해서는 "파일 다운로드" 대화상자가 뜨도록 하는 헤더속성이다.
					resp.setHeader("Content-Disposition", "inline;filename=\"temp.txt\"" + f.getName() + "\"");
					BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
					byte[] buffer = new byte[8 * 1024];
					
					// jsp 내장객체 out 은 JspWriter 클래스타입이라고 한다. 매개변수로 받아질 수 있는건지는 실해해봐야 알 것 같다.
					
					OutputStream os = new Writer2Stream(out); // OutputStream 을 extends 받고 있다.
					// 인풋스트림(1)에서 아웃풋스트림(2)으로 버퍼(3)를 복사
					FTPDefault.copyStreamsWithoutClose(fileInput, os, buffer);
					fileInput.close();
					os.flush();
					ftpVO.setNoHtml(true);
					ftpVO.setDirView(false);
				}
			}
		} else { // 경로에 접근은 가능한데 파일이 없는 경우 에러메세지 세팅
			dir = f.getParent();
			ftpVO.setError("File " + f.getAbsolutePath() + " does not exist or is not readable on the server");
		}
		ftpVO.setDir(dir);
	}
	
	public void zipDownload(FtpVO ftpVO, HttpServletResponse resp, HttpServletRequest req) throws IOException {

		Vector<File> v = FTPDefault.expandFileList(req.getParameterValues("selfile"), false);
		PrintWriter out = resp.getWriter();
		/** 벡터에있는 모든 파일이 허용되는지 확인합니다.*/
		String notAllowedFile = null;
		for(int i = 0; i < v.size(); i++) {
			File f = v.get(i);
			// 경로에 접근불가한 경우 파일명까지의 경로를 가져온다.
			if(!FTPDefault.isAllowed(f, false)) {
				// getAbsolutePath() 파일명까지의 절대경로를 표현한다.
				notAllowedFile = f.getAbsolutePath();
				break;
			}
		}
		
		if (notAllowedFile != null) { // 경로에 접근불가한 파일이 존재하는 경우 에러메세지 세팅
			ftpVO.setError("You are not allowed to access " + notAllowedFile);
		} else if(v.size() == 0) { // 벡터에 파일이 없는 경우 에러메세지 세팅
			ftpVO.setError("No files selected");
		} else { // 경로 접근가능, 벡터에 파일 존재
			File dirFile = new File("" + ftpVO.getDir());
			int dirLength = dirFile.getAbsolutePath().length();
			// 이거 마임타입이구나
			resp.setContentType("application/zip");
			resp.setHeader("Content-Disposition", "attachment;filename=\"rename_me.zip\"");
			// java.util.zip.ZipOutputStream 이건또첨봄
			ZipOutputStream zipout = new ZipOutputStream(new Writer2Stream(out));
			// setComment() zip 파일 주석을 설정합니다.
			zipout.setComment("Created by jsp File Browser v. " + FTPDefault.VERSION_NR);
			// setLevel() 0~9까지 압축 레벨 설정
			zipout.setLevel(FTPDefault.COMPRESSION_LEVEL);
			
			for (int i = 0; i < v.size(); i++) {
				File f = v.get(i);
				
				// 파일을 읽어들일 수 있다면
				if (f.canRead()) {
					// putNextEntry() 새로운 ZIP 파일 항목 쓰기를 시작하고 스트림을 항목 데이터의 시작 부분에 놓습니다. 
					zipout.putNextEntry(new ZipEntry(f.getAbsolutePath().substring(dirLength + 1)));
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					byte[] buffer = new byte[0xffff];
					FTPDefault.copyStreamsWithoutClose(bis, zipout, buffer);

					bis.close();
					zipout.closeEntry();
				}
			}
			// finish() 기본이되는 스트림을 닫지 않고 압축 된 데이터를 출력 스트림에 기록하는 것을 완료합니다.
			zipout.finish();
			out.flush();
			ftpVO.setNoHtml(true);
			ftpVO.setDirView(false);
		}
	}

	public void downFile(FtpVO ftpVO, HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String filePath = ftpVO.getDownFile();
		String filePathEng = filePath;
		filePath = FTPDefault.fromHangul(filePath);
		
		File f = new File(filePath);
		File f2 = new File(filePathEng);
		
		// 경로에 접근이 허용되지 않았다면 에러메세지 세팅
		if(!FTPDefault.isAllowed(f, false)) {
			ftpVO.setDir(f.getParent());
			ftpVO.setError("You are not allowed to access " + f.getAbsoluteFile());
		// 파일이 존재하고 읽어들일 수 있다면 
		} else if(f.exists() && f.canRead()) {
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + f2.getName() + "\"");
			resp.setContentLength((int) f.length());
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			byte[] buffer = new byte[8 * 1024];
			
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			int read = 0;
			
			while((read = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, read);
			}
			
			bis.close();
			bos.close();
			ftpVO.setNoHtml(true);
			ftpVO.setDirView(false);
		} else { // 경로에 접근은 가능한데 파일이 없는 경우 에러메세지 세팅
			ftpVO.setDir(f.getParent());
			ftpVO.setError("File " + f.getAbsolutePath() + " does not exist or is not readable on the server");
		}
	}

	//If no parameter is submitted, it will take the path from jsp file browser
	public void checkNoHtml(FtpVO ftpVO, HttpServletRequest req) throws IOException {
		/** dir 매개 변수가 없다면 jsp 파일 브라우저에서 경로를 가져옵니다.*/
		ServletContext application = req.getSession().getServletContext();
		if(!StringUtils.hasText(ftpVO.getDir())) {
			String path = null;
			// 만약 매개변수로 ServletContext 를 받아오지 못한다면 req.getServletContext() 로 가져올 수 있을 듯.
			//request로 넘어온 URI 까지의 경로가 있다면 ..?
			if(application.getRealPath(req.getRequestURI()) != null) {

				File f = new File(application.getRealPath(req.getRequestURI())).getParentFile();
				// 톰캣에 필요한 부분이다??
				// f가 null 은 아닌데 파일이 존재하지 않는다면..??????
				while(f != null && !f.exists()) {
					f = f.getParentFile(); // 파일을 계속 파일의 parentFile 로 대체한다.
				}
				
				// while 종료 후 f가 null이 아니라면
				if(f != null) {
					path = f.getAbsolutePath(); // path 를 절대경로로.
				}
			}
			
			// 위 if문 종료 후 path 가 null 이라면
			if(path == null) { // 우리가 디렉토리에 있지 않은 경우를 처리하십시오 (예 : war 파일).(???)
				path = new File(".").getAbsolutePath();
			}
			
			//Check path
			if(!FTPDefault.isAllowed(new File(path), false)) {
				if(FTPDefault.RESTRICT_PATH.indexOf(";") < 0) {
					path = FTPDefault.RESTRICT_PATH;
				} else {
					path = FTPDefault.RESTRICT_PATH.substring(0, FTPDefault.RESTRICT_PATH.indexOf(";"));
				}
			}
			ftpVO.setDir(path);
		}
	}
	
	public int directoryViewer(String browserName, String dir, FtpVO ftpVO, HttpServletRequest req) throws IOException {
		ServletContext application = req.getSession().getServletContext();
		final String FOL_IMG = "";

		File f = new File("" + FTPDefault.fromHangul(dir));
		
		//Check, whether the dir exists
		if(!f.exists() || !FTPDefault.isAllowed(f, false)) {
			if(!f.exists()) {
				ftpVO.setError("Directory " + f.getAbsolutePath() + " does not exist.");
			} else {
				ftpVO.setError("You are not allowed to access " + f.getAbsolutePath());
			}
			
			
			
			
			//if attribute olddir exists, it will change to olddir
			if(ftpVO.getOlddir() != null && FTPDefault.isAllowed(new File(ftpVO.getOlddir()), false)) {
				f = new File("" + ftpVO.getOlddir());
			//try to go to the parent dir
			} else {
				if(f.getParent() != null && FTPDefault.isAllowed(f, false)) {
					f = new File(f.getParent());
				}
			}
			
			
			//If this dir also do also not exist, go back to browser.jsp root path
			if(!f.exists()) {
				String path = null;
				if(application.getRealPath(req.getRequestURI()) != null) {
					path = new File(application.getRealPath(req.getRequestURI())).getParent();
				}
				
				// handle the case were we are not in a directory (ex: war file)
				if(path == null) {
					path = new File(".").getAbsolutePath();
				}
				
				f = new File(path);
			}
			
			if(FTPDefault.isAllowed(f, false)) { 
				dir = f.getAbsolutePath();
			} else {
				dir = null;
			}
		}
		
		int sortMode = 1;
		if(StringUtils.hasText(dir)) {
			// Output the table, starting with the headers.
			String cmd = browserName + "?dir=" + dir;
			ftpVO.setDirDefault(dir);
			dir = FTPDefault.toHangul(dir);

			ftpVO.setCmd(cmd);
			
			if(StringUtils.hasText(ftpVO.getSort())) {
				sortMode = Integer.parseInt(ftpVO.getSort());
			}
			
			int[] sortArr = new int[] {1, 2, 3, 4};
			for(int i = 0, size = sortArr.length; i < size; i++) {
				if(sortArr[i] == sortMode) {
					sortArr[i] = -sortArr[i];
				}
			}
			ftpVO.setSortArr(sortArr);
			char trenner = File.separatorChar;
			// Output the Root-Dirs, without FORBIDDEN_DRIVES
			File[] entry = File.listRoots(); // 시스템 상의 루트경로에 해당하는 배열을 반환한다.
			List<EntryVO> innerVO = new ArrayList<EntryVO>();
			
			
			for(int i = 0, size = entry.length; i < size; i++) {
				boolean forbidden = false;
				
				for(int j = 0, jSize = FTPDefault.FORBIDDEN_DRIVES.length; j < jSize; j++) {
					if(entry[i].getAbsolutePath().toLowerCase().equals(FTPDefault.FORBIDDEN_DRIVES[j])) {
						forbidden = true;
					}
				}
				
				if(!forbidden) {
					innerVO.add(new EntryVO(entry[i])); 
				}
			}
			ftpVO.setInnerVO(innerVO);
			
			// Output the parent directory link ".."
			ftpVO.setFileParent(f.getParent() != null ? FTPDefault.toHangul(f.getParent()) : null);
			
			// Output all files and dirs and calculate the number of files and total size
			
			entry = f.listFiles();
			if(entry == null) {
				entry = new File[] {};
			}
			
			long totalSize = 0; // The total size of the files in the current directory
			long fileCount = 0; // The count of files in the current working directory
			if(entry != null && entry.length > 0) {

				Arrays.sort(entry, new FileComp(sortMode));

				List<EntryVO> entryList = new ArrayList<EntryVO>();
				for(int i = 0, size = entry.length; i < size; i++) {
					
					String name = FTPDefault.toHangul(entry[i].getAbsolutePath());
					String type = "File"; // This String will tell the extension of the file
					
					EntryVO entryVO = new EntryVO(entry[i]);
					if(entry[i].isDirectory()) {
						type = "DIR";
					} else {
						String tempName = entry[i].getName().replace(' ', '_');
						if(tempName.lastIndexOf('.') != -1) {
							type = tempName.substring(tempName.lastIndexOf('.')).toLowerCase();
						}
					}
					entryVO.setType(type);
					
					String dlink = "&nbsp;"; // The "Download" link
					String elink = "&nbsp;"; // The "Edit" link
					String buf = FTPDefault.conv2Html(entry[i].getName());
					
					if(!entry[i].canWrite()) {
						buf = "<i>" + buf + "</i>";
					}
					
					String link = buf; // The standard view link, uses Mime-type
					
					
					if(entry[i].isDirectory()) {
						if(entry[i].canRead() && FTPDefault.USE_DIR_PREVIEW) {
							//Show the first DIR_PREVIEW_NUMBER directory entries in a tooltip
							File[] fs = entry[i].listFiles();
							if(fs == null) {
								fs = new File[] {};
							}
							Arrays.sort(fs, new FileComp());
							StringBuffer fileNames = new StringBuffer();
							for(int j = 0; (j < fs.length) && (j < 10); j++) {
								String fname = FTPDefault.conv2Html(fs[j].getName());
								if(fs[j].isDirectory()) {
									fileNames.append("[" + fname + "];");
								} else {
									fileNames.append(fname + ";");
								}
							}
							if(fs.length > FTPDefault.DIR_PREVIEW_NUMBER) {
								fileNames.append("...");
							} else if(fileNames.length() > 0) {
								fileNames.setLength(fileNames.length() - 1);
							}
							link = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;dir=" + name + "\" title=\"" + fileNames + "\">"
									+ FOL_IMG + "[" + buf + "]</a>";
						} else if(entry[i].canRead()) {
							link = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;dir=" + name + "\">" + FOL_IMG + "[" + buf + "]</a>";
						} else {
							link = FOL_IMG + "[" + buf + "]";
						}
					} else if(entry[i].isFile()) {//Entry is file
						
						totalSize = totalSize + entry[i].length();
						fileCount = fileCount + 1;
						if(entry[i].canRead()) {
							dlink = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;downfile=" + name + "\">Download</a>";
							//If you click at the filename
							if(FTPDefault.USE_POPUP) {
								link = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;file=" + name + "\" target=\"_blank\">" + buf + "</a>";
							} else {
								link = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;file=" + name + "\">" + buf + "</a>";
							}
							
							if(entry[i].canWrite()) {// The file can be edited
								//If it is a zip or jar File you can unpack it
								if(FTPDefault.isPacked(name, true)) {
									elink = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;unpackfile=" + name + "\">Unpack</a>";
								} else {
									elink = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;editfile=" + name + "\">Edit</a>";
								}
							} else {// If the file cannot be edited
								if(FTPDefault.isPacked(name, true)) {
									elink = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;unpackfile=" + name + "\">Unpack</a>";
								} else {
									elink = "<a onmousedown=\"dis()\" href=\"" + browserName + "?sort=" + sortMode + "&amp;editfile=" + name + "\">View</a>";
								}
							}
						} else {
							link = buf;
						}
					}
					
					
					if(!FTPDefault.READ_ONLY) {
						entryVO.setLink(elink);
					}
					
					entryVO.setLink(link);
					entryVO.setBuf(buf);
					entryList.add(entryVO);
				}
				ftpVO.setEntry(entryList);
				ftpVO.setFileCount(fileCount + "");
				ftpVO.setTotalSize(FTPDefault.convertFileSize(totalSize) + "");
				
			}
		}
		return sortMode;
	}
	
	public void noPackedFile(FtpVO ftpVO, Model model, File f) {
		try{
			ZipFile zf = new ZipFile(f);
			Enumeration entries = zf.entries();
			ftpVO.setIncludePage("file/filePage1.jsp");
			long totalSize = 0;
			long fileCount = 0;
			
			List<ConvertFileVO> fileHtmlList = new ArrayList<ConvertFileVO>();
			while(entries.hasMoreElements()) {
				
				ZipEntry entry = (ZipEntry)entries.nextElement();
				if(!entry.isDirectory()) {
					fileCount++;
					totalSize += entry.getSize();
					long ratio = 0;
					if (entry.getSize() != 0) {

						ratio = (entry.getCompressedSize() * 100) / entry.getSize();
					}
					ConvertFileVO fileVO = new ConvertFileVO();
					fileVO.setHtml(FTPDefault.conv2Html(entry.getName()));
					fileVO.setFileSize(FTPDefault.convertFileSize(entry.getSize()));
					fileVO.setCompressedSize(FTPDefault.convertFileSize(entry.getCompressedSize()));
					fileVO.setRatio(ratio);
					fileVO.setDate(FTPDefault.dateFormat.format(new Date(entry.getTime())));
					
					fileHtmlList.add(fileVO);
				}
			}
			model.addAttribute("fileHtmlList", fileHtmlList);
			model.addAttribute("totalSize", FTPDefault.convertFileSize(totalSize));
			model.addAttribute("fileCount", fileCount);
			model.addAttribute("zFileName" + f.getName());
			model.addAttribute("zFileLegnth", f.length());
			model.addAttribute("convertFileName", FTPDefault.conv2Html(f.getName()));
			model.addAttribute("zFilePath", f.getAbsolutePath());
			zf.close();
		} catch(ZipException ex) {
			ftpVO.setError("Cannot read " + f.getName() + ", no valid zip file");
		} catch(IOException ex) {
			ftpVO.setError("Reading of " + f.getName() + " aborted. Error: " + ex);
		}
	}
	
	@SuppressWarnings("resource")
	public String multiPartFile(FtpVO ftpVO, HttpServletResponse resp, HttpServletRequest req, String dir) {
		MultipartFile multipartFile = ftpVO.getMyFile();
		
		if(!FTPDefault.ALLOW_UPLOAD) {
			ftpVO.setError("Upload is forbidden!");
		}			
		resp.setContentType("text/html");
//		HttpMultiPartParser parser = new HttpMultiPartParser();
		boolean error = false;
		
		
		try {
			int bStart = multipartFile.getContentType().lastIndexOf("oundary=");
			String bound = multipartFile.getContentType().substring(bStart + 8);
			int cLength = (int)multipartFile.getSize();
			
//			Hashtable hashTable = parser.processData(req.getInputStream(), bound, FTPDefault.tempdir, cLength);
					
			/*if(!FTPDefault.isAllowed(new File((String)hashTable.get("dir")), false)) { // 이 상황은 해킹.
				System.out.println("service multiPartFile 해킹상황");
				ftpVO.setError("You are not allowed to access " + hashTable.get("dir"));
				error = true;
			} else */
			if(multipartFile != null) {
				
				OutputStream out = null;
				out = new FileOutputStream(dir + "/" + multipartFile.getOriginalFilename());
				BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
				byte[] buffer = new byte[8106];
				int read;
				while((read = bis.read(buffer)) > 0) {
					out.write(buffer, 0, read);
				}
//				
//				FileInfo fInfo = (FileInfo) hashTable.get("myFile");
//				File file = fInfo.file;
//				UplInfo upInfo = UploadMonitor.getInfo(fInfo.clientFileName);
//				ftpVO.setUplInfo(upInfo);
//				
//				if(upInfo != null && upInfo.aborted) {
//					System.out.println(" service multiPartFile (hashTable.get(myFile) != null // upInfo != null && upInfo.aborted");
//					
//					file.delete();
//					ftpVO.setError("Upload aborted");
//				} else {
//					System.out.println(" service multiPartFile (hashTable.get(myFile) != null // else ");
//
//					// 임시 디렉토리에서 오른쪽 디렉토리로 파일 이동
//					String path = (String) hashTable.get("dir");
//					if(!path.endsWith(File.separator)) {
//
//						path = path + File.separator;
//					}
//					if(!f.renameTo(new File(path + f.getName()))) {
//
//						ftpVO.setError("Cannot upload file.");
//						error = true;
//						f.delete();
//					}
//				}
				
			}
//			dir = (String)hashTable.get("dir");
			
			
		} catch(Exception e) {
			ftpVO.setError("Error " + e + ". Upload aborted");
		}
		
		
		if(!error) {
			ftpVO.setMessage("파일 업로드 완료");
		}
		return dir;
	}

	public void editFile(FtpVO ftpVO, Model model ) throws IOException {
		
		ftpVO.setPageOrder("editfile.jsp");
		String editFileStr = ftpVO.getEditFile();
		File editFile = new File(editFileStr);
		if(!FTPDefault.isAllowed(editFile, true)) {
			
			ftpVO.setError("You are not allowed to access " + editFile.getAbsolutePath());
		} else {
			
			ftpVO.setEditFile(FTPDefault.conv2Html(editFileStr));
			BufferedReader br = new BufferedReader(new FileReader(editFile));
			String disable = "";
			if(!editFile.canWrite()) {

				
				disable = " readonly";
				model.addAttribute("editFileDisable", disable);
			}
			String c;
			
			// 파일작성 후 win 인지 unix 인지 판단
			int i;
			boolean dos = false;
			boolean cr  = false;
			List<String> editFileHtmlList = new ArrayList<String>();
			while((i = br.read()) >= 0) {
				
				editFileHtmlList.add(FTPDefault.conv2Html(i));
				if(i == '\r') {
					cr = true;
				} else if(cr && (i == '\n')) {
					dos = true;
				} else {
					cr = false;
				}
			}
			br.close();
			// 파일 디렉토리가 표시되지 않음
			ftpVO.setEditFileHtmlList(editFileHtmlList);
			ftpVO.setDos(dos);
			ftpVO.setCr(cr);
			ftpVO.setEditFileName(editFile.getName());
		}
	}
	
	public String fileSaveOrCancel(FtpVO ftpVO) throws IOException {

		ftpVO.setPageOrder("nFile.jsp");
		File file = new File(ftpVO.getnFile());
		
		if(ftpVO.getSubmit().equals("Save")) {
			
			File newFile = new File(FTPDefault.getDir(file.getParent(), ftpVO.getNewName()));
			
			if(FTPDefault.isAllowed(newFile, true)) {
				ftpVO.setError("You are not allowed to access " + newFile.getAbsolutePath());
			}
			
			if(newFile.exists() && !newFile.canWrite() && ftpVO.getBackUp() != null) {

				File bak = new File(newFile.getAbsolutePath() + ".bak");
				bak.delete();
				newFile.renameTo(bak);
			}
			
			if(newFile.exists() && !newFile.canWrite()) {

				ftpVO.setError("Cannot write to " + newFile.getName() + ", file is write protected.");
			} else {

				BufferedWriter outs = new BufferedWriter(new FileWriter(newFile));
				StringReader text = new StringReader(ftpVO.getText());
				
				int i;
				boolean cr = false;
				String lineEnd = "\n";
				
				if(ftpVO.getLineFormat().equals("dos")) {

					lineEnd = "\r\n";
				}
				
				while((i = text.read()) >= 0) {

					
					if(i == '\r') {
						cr = true;
					} else if(i == '\n') {
						outs.write(lineEnd);
						cr = false;
					} else if(cr) {
						outs.write(lineEnd);
						cr = false;
					} else {
						outs.write(i);
						cr = false;
					}
				}
				outs.flush();
				outs.close();
			}
		
		}
		return file.getParent();

	}

	public void fileDelete(FtpVO ftpVO, HttpServletRequest req) throws IOException {

		Vector v = FTPDefault.expandFileList(req.getParameterValues("selfile"), true); 
		boolean error = false;
		for(int i = v.size() - 1; i >= 0; i--) {

			File vFile = (File)v.get(i);
			if(!FTPDefault.isAllowed(vFile, true)) {
				ftpVO.setError("You are not allowed to access " + vFile.getAbsolutePath());
				error = true;
				break;
			}
			if(!vFile.canWrite() || !vFile.delete()) {
				ftpVO.setError("디렉토리는 삭제가 불가능합니다.");
				error = true;
				break;
			}
		}
		
		if((!error) && (v.size() > 1)) {

			ftpVO.setMessage("모든 파일 삭제 완료");
		} else if((!error) && (v.size() > 0)) {
			
			ftpVO.setMessage("파일 삭제 완료");
		} else if(!error) {
			ftpVO.setError("선택된 파일 없음");
		}
	}

	public void newDirectory(FtpVO ftpVO, String dir) throws IOException {

		String dirName = ftpVO.getCrDir();
		String newDir = FTPDefault.getDir(dir, dirName);
		
		if(!FTPDefault.isAllowed(new File(newDir), true)) {
			ftpVO.setError("You are not allowed to access " + newDir);
		} else if(new File(newDir).mkdirs()) {
			ftpVO.setMessage("[" + newDir + "] 폴더 생성 완료");
		} else {
			ftpVO.setError("[" + newDir + "] 폴더 생성 실패");
		}
	}
	
	public boolean uplMonitor(FtpVO ftpVO, String uplMonitor ) {
		
		ftpVO.setPageOrder("fileAction/uplMonitor.jsp");
		String fname = FTPDefault.fromHangul(uplMonitor);
		boolean first = false;
		if(StringUtils.hasText(ftpVO.getFirst())) {
			first = true;
		}
		
		UplInfo info = new UplInfo();
		if(!first) {
			info = UploadMonitor.getInfo(fname);
			
			if(info == null) {				// windows, unix
				String[] posiArr = {"\\", "/"};
				
				for(String posi : posiArr) {

					int p = fname.lastIndexOf(posi);
					if(p != -1) {

						info = UploadMonitor.getInfo(fname.substring(p + 1));
					};
				}
			}
			
		}
			
		ftpVO.setfName(fname);
		if(info != null && info.aborted) {
			
			UploadMonitor.remove(fname);
			ftpVO.setUplInfo(info);
			ftpVO.setIncludePage("uplMonitor/uplMonitorResult1.jsp");
		} else if(info != null && (info.totalSize != info.currSize || info.currSize == 0)) {
			ftpVO.setUplInfo(info);
			ftpVO.setIncludePage("uplMonitor/uplMonitorResult2.jsp");
		} else {
			ftpVO.setUplInfo(info);
			UploadMonitor.remove(fname);
			ftpVO.setIncludePage("uplMonitor/uplMonitorResult3.jsp");
		}
	
		return first;
	}
}
