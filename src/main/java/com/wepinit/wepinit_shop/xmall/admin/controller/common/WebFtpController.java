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
package com.wepinit.wepinit_shop.xmall.admin.controller.common;

import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.ftp.FTPDefault;
import com.wepinit.wepinit_shop.xmall.admin.service.ftp.FtpService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.WebFtpVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.ftp.FtpVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.ftp.SecurityAuthVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping("/shop/admin/webFtp/*")
public class WebFtpController {

	private static final Logger logger = LoggerFactory.getLogger(WebFtpController.class); 
	
	@Resource
	private FtpService service;
	
	@RequestMapping(value="popupWebFtp")
	public String browser(
			@ModelAttribute("ftoVO") FtpVO ftpVO,
			HttpServletRequest req, 
			HttpServletResponse resp,
			Model model) throws IOException {
		
		ServletContext application = req.getSession().getServletContext();
		
		// url ???????????? ????????????
		if(!this.service.isAllowedIP(req.getRemoteAddr())) {
			
			SecurityAuthVO securityAuthVO = new SecurityAuthVO();
			securityAuthVO.setYouIP(req.getRemoteAddr());
			securityAuthVO.setServerInfo(application.getServerInfo());
			securityAuthVO.setServerName(req.getServerName());
			securityAuthVO.setServerPort(req.getServerPort());
			
			model.addAttribute("authVO", securityAuthVO);
			return "/shop/ftp/securityAuth";
		}
		
		
		/** ?????? ???????????? ???????????? ?????? ??????*/
		
		/** browserName ????????? jsp ?????? ????????? URI ??? ???????????? ??? ???????????????. ?????? ?????? ????????? ???????????????. */
		final String browserName = req.getRequestURI();
		boolean nohtml = false;
		boolean dirView = true;
		
		String dir = ftpVO.getDir();
		String uplMonitor = ftpVO.getUplMonitor();
		
		// ?????? ?????? ?????? ??? ??????.
		// request ??? ?????????????????? ??????????????? ?????????
		if(ftpVO.getJavaScript() != null) {

			ftpVO.setNoHtml(true);
			ftpVO.setDirView(false);
			
			/** ?????? ??????????????? ????????????????????? ??????????????? ????????????. */
			resp.setHeader("Cache-Control", "public");
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
			resp.setHeader("Expires", sdf.format(new Date(now.getTime() + 1000 * 60 * 60 * 24*2)));
			resp.setHeader("Content-Type", "text/javascript");
		// request ??? ?????? ??????????????? ????????? - ?????? ??????
		} else if(ftpVO.getFile() != null) {
			this.service.paramFile(ftpVO, resp);
		// ??????????????? Submit ??? ?????? ??? ?????? SAVE_AS_ZIP ??? ??????????????? - zip ?????? ????????????
		/** ????????? ????????? zip ????????? ???????????? */
		} else if((ftpVO.getSubmit() != null)
				&& (ftpVO.getSubmit().equals(FTPDefault.SAVE_AS_ZIP))) { // ????????? ????????? zip ????????? ????????????

			this.service.zipDownload(ftpVO, resp, req);
		// ???????????? ??? downfile ??? ????????? - ?????? ????????????
		} else if(ftpVO.getDownFile() != null) {

			this.service.downFile(ftpVO, req, resp);
		}
		
		// nohtml ??? ???????????? ????????? ??????????????? ??????????????? ???????????? true ??? ??????.. ??
		
		if (ftpVO.isNoHtml()) {
			return "noHtml return";
		}  
		this.service.checkNoHtml(ftpVO, req);
		dir = ftpVO.getDir();

		// css????????? ????????? ????????????
		String cssPath = null;
		String cssName = null;
		if(application.getRealPath(req.getRequestURI()) != null) {

			cssPath = new File(application.getRealPath(req.getRequestURI())).getParent() + File.separator + FTPDefault.CSS_NAME;
		}
		if(cssPath == null) {

			cssPath = application.getResource(FTPDefault.CSS_NAME).toString();
		}
		
		if(new File(cssPath).exists()) {
			cssName = FTPDefault.CSS_NAME;
		} else if(uplMonitor == null) {
			cssName = "../ftp.css";
		}
		
		//Check path
		if(!FTPDefault.isAllowed(new File(dir), false)) {

			ftpVO.setError("You are not allowed to access " + dir);
		
		//Upload monitor
		} else if(uplMonitor != null) {
			cssName = "../ftpUpl.css";
			if(!this.service.uplMonitor(ftpVO, uplMonitor)) {
				dirView = false;
				dir = null;
			}
		} else if(ftpVO.getCommand() != null) {
			
			ftpVO.setPageOrder("command.jsp");
			if(!FTPDefault.NATIVE_COMMANDS) {
				
				ftpVO.setError("Execution of native commands is not allowed!");
			} else if(!"Cancel".equalsIgnoreCase(req.getParameter("Submit"))) {

				
				String ret = "";
				String command = req.getParameter("command");
				if(!command.equalsIgnoreCase("")) {

					ret = FTPDefault.fromHangul(FTPDefault.startProcess(command, dir));
					model.addAttribute("ret", ret);
				}
				dirView = false;
				dir = null;
			}
		} else if(ftpVO.getMyFile() != null) {

			
			ftpVO.setPageOrder("fileAction/file.jsp");
			File f = new File(ftpVO.getMyFile().getName());
			if(!FTPDefault.isAllowed(f, false)) {
				
				ftpVO.setError("You are not allowed to access " + f.getAbsolutePath());
			} 
			if(FTPDefault.isPacked(f.getName(), false)) {
				this.service.noPackedFile(ftpVO, model, f);
				dirView = false;
				dir = null;
				//TODO
			} 
		}
		// Upload
		if(ftpVO.getMyFile() != null) {
			if(ftpVO.getMyFile().getSize() > 0) {
			dir = this.service.multiPartFile(ftpVO, resp, req, dir);
			
			/** ????????? ????????? ?????????????????? ?????? */
			} else {
				
				ftpVO.setError("????????? ?????? ??????");
			}
		} 
			
			
		if(ftpVO.getEditFile() != null) {
			this.service.editFile(ftpVO, model);
			
			dir = null;
			dirView = false;
			/** ?????? ??? ?????? ?????? ?????? ?????? */
		} else if(ftpVO.getnFile() != null) {
			
			dir = this.service.fileSaveOrCancel(ftpVO);

			// ?????? ??????
		} else if((ftpVO.getSubmit() != null) && (ftpVO.getSubmit().equals(FTPDefault.DELETE_FILES))) {
			this.service.fileDelete(ftpVO, req);
			// ???????????? ?????????
		} else if((ftpVO.getSubmit()) != null && (ftpVO.getSubmit().equals(FTPDefault.CREATE_DIR))) {
			dir = "" + dir;
			this.service.newDirectory(ftpVO, dir);
		}
		
		int sortMode = 0;
		
		// Directory viewer
		if(dirView && dir != null) {
			ftpVO.setLinkDir(FTPDefault.dir2linkdir(FTPDefault.fromHangul(dir), browserName, sortMode));
			sortMode = this.service.directoryViewer(browserName, dir, ftpVO, req);
		}
		
		ftpVO.setDir(dir);
		
		Map<String, Boolean> boolsMap = new HashMap<String, Boolean>();
		boolsMap.put("readOnly", FTPDefault.READ_ONLY);
		boolsMap.put("allowUpload", FTPDefault.ALLOW_UPLOAD);
		boolsMap.put("nativeCommands", FTPDefault.NATIVE_COMMANDS);
			
		Map<String, String> strMap = new HashMap<String, String>();
		strMap.put("createDir", FTPDefault.CREATE_DIR);
		strMap.put("createFile", FTPDefault.CREATE_FILE);
//		strMap.put("moveFiles", FTPDefault.MOVE_FILES);
//		strMap.put("copyFiles", FTPDefault.COPY_FILES);
//		strMap.put("renameFile", FTPDefault.RENAME_FILE);
		strMap.put("uploadFiles", FTPDefault.UPLOAD_FILES);
		strMap.put("launchCommand", FTPDefault.LAUNCH_COMMAND);
		strMap.put("versionNR", FTPDefault.VERSION_NR);
//		strMap.put("saveAsZip", FTPDefault.SAVE_AS_ZIP);
		strMap.put("deleteFiles", FTPDefault.DELETE_FILES);
		
		Map<String, Integer> intMap = new HashMap<String, Integer>();
		intMap.put("editFieldCols", FTPDefault.EDITFIELD_COLS);
		intMap.put("editFieldRows", FTPDefault.EDITFIELD_ROWS);
		intMap.put("uploadMonitorRefresh", FTPDefault.UPLOAD_MONITOR_REFRESH);
		
		model.addAttribute("cssName", cssName);
		model.addAttribute("browserName", browserName);
		model.addAttribute("sortMode", sortMode);
		model.addAttribute("boolsMap", boolsMap);
		model.addAttribute("strMap", strMap);
		model.addAttribute("intMap", intMap);
		model.addAttribute("ftpVO", ftpVO);
		
		
		
		return "/shop/admin/design/popup.webftp";
	}
	
/*	//webftp popup
	@RequestMapping(value="popupWebFtp")
	public String popupWebFtp(@ModelAttribute("webFtpVO") WebFtpVO webFtpVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("################popupWebFtp");
		}
			return "/shop/admin/design/popup.webftp";
	}*/
	//webftp list
	@RequestMapping(value="popupWebFtp/list")
	public String webFtpList(@ModelAttribute("webFtpVO") WebFtpVO webFtpVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("################WebFtp/list");
		}
		String oriCookie = WebUtil.getCookies(req, "dPath");
		String loca = oriCookie.substring(0,oriCookie.length()-1);
		int index = loca.lastIndexOf("/");
		String currnetFile = loca.substring(index+1);
		webFtpVO.setCurrnetFile(currnetFile);

			return "/shop/admin/design/webftp/webftp_list";
	}
	//webftp tree
	@RequestMapping(value="popupWebFtp/tree")
	public String webFtpTree(@ModelAttribute("webFtpVO") WebFtpVO webFtpVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("################WebFtp/tree");
		}
		String oriCookie = WebUtil.getCookies(req, "dPath");
		String loca = oriCookie.substring(0,oriCookie.length()-1);
		int index = loca.lastIndexOf("/");
		String currnetFile = loca.substring(index+1);
		webFtpVO.setCurrnetFile(currnetFile);

			return "/shop/admin/design/webftp/webftp_tree";
	}
	
	
	//webftp bar_conf
	@RequestMapping(value="popupWebFtp/bar_conf")
	public String bar_conf(@ModelAttribute("webFtpVO") WebFtpVO webFtpVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("################WebFtp/tree");
		}
		String oriCookie = WebUtil.getCookies(req, "dPath");
		String loca = oriCookie.substring(0,oriCookie.length()-1);
		int index = loca.lastIndexOf("/");
		String currnetFile = loca.substring(index+1);
		webFtpVO.setCurrnetFile(currnetFile);

			return "/shop/admin/design/webftp/3DBar_conf";
	}
}
