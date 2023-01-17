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
package com.wepinit.wepinit_shop.xmall.admin.vo.ftp;

import com.wepinit.wepinit_shop.xmall.admin.service.ftp.FTPDefault;
import com.wepinit.wepinit_shop.xmall.admin.service.ftp.UplInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FtpVO {
	private String dir;
	private String dirDefault;
	private String uplMonitor;
	private String command;
	private String submit;
	private String file;
	private String editFile;
	private String editFileName;
	private String newName;
	private String sort;
	private boolean dos;
	private String cr;
	private List<String> editFileHtmlList;
	private String first;
	private String nFile;
	private String backUp;
	private String text;
	private String lineFormat;
	private String includePage;
	private String pageOrder;
	private String unpackfile;
	private String crDir;
	private String message;
	private String error;
	private String fName;
	private String hangulFname;
	private MultipartFile myFile;
	private UplInfo uplInfo;
	private String totalSize;
	
	private String javaScript;
	private boolean noHtml = false;
	private boolean dirView = true;
	private String downFile;
	private String cmd;
	private int[] sortArr;
	private int sortMode;
	private List<EntryVO> innerVO;
	private String fileParent;
	private List<EntryVO> entry;
	private String elink;
	private String dlink;
	private String link;
	private String date;
	private String linkDir;
	private String fileCount;
	private String olddir;
	
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getFirst() {
		return first;
	}
	public String getUplMonitor() {
		return uplMonitor;
	}
	public void setUplMonitor(String uplMonitor) {
		this.uplMonitor = uplMonitor;
		this.fName = FTPDefault.fromHangul(uplMonitor);
	}
	public void setIncludePage(String includePage) {
		this.includePage = includePage;
	}
	public String getIncludePage() {
		return includePage;
	}
	public String getPageOrder() {
		return pageOrder;
	}
	public void setPageOrder(String pageOrder) {
		this.pageOrder = pageOrder;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCommand() {
		return command;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getSubmit() {
		return submit;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getEditFile() {
		return editFile;
	}
	public void setEditFile(String editFile) {
		this.editFile = editFile;
	}
	public List<String> getEditFileHtmlList() {
		return editFileHtmlList;
	}
	public void setEditFileHtmlList(List<String> editFileHtmlList) {
		this.editFileHtmlList = editFileHtmlList;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setDos(boolean dos) {
		this.dos = dos;
	}
	public boolean isDos() {
		return dos;
	}
	public void setCr(boolean cr) {
		this.cr = cr ? "checked" : "";
	}
	public String getCr() {
		return cr;
	}
	public void setEditFileName(String editFileName) {
		this.editFileName = editFileName;
	}
	public String getEditFileName() {
		return editFileName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public void setnFile(String nFile) {
		this.nFile = nFile;
	}
	public String getnFile() {
		return nFile;
	}
	public String getBackUp() {
		return backUp;
	}
	public void setBackUp(String backUp) {
		this.backUp = backUp;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLineFormat() {
		return lineFormat;
	}
	public void setLineFormat(String lineFormat) {
		this.lineFormat = lineFormat;
	}
	public String getUnpackfile() {
		return unpackfile;
	}
	public void setUnpackfile(String unpackfile) {
		this.unpackfile = unpackfile;
	}
	public void setCrDir(String crDir) {
		this.crDir = crDir;
	}
	public String getCrDir() {
		return crDir;
	}
	public void setOlddir(String olddir) {
		this.olddir = olddir;
	}
	public String getOlddir() {
		return olddir;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getJavaScript() {
		return javaScript;
	}
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
	public void setNoHtml(boolean noHtml) {
		this.noHtml = noHtml;
	}
	public boolean isNoHtml() {
		return noHtml;
	}
	public void setDirView(boolean dirView) {
		this.dirView = dirView;
	}
	public boolean isDirView() {
		return dirView;
	}
	public void setDownFile(String downFile) {
		this.downFile = downFile;
	}
	public String getDownFile() {
		return downFile;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getCmd() {
		return cmd;
	}
	public void setSortArr(int[] sortArr) {
		this.sortArr = sortArr;
	}
	public int[] getSortArr() {
		return sortArr;
	}
	public void setSortMode(int sortMode) {
		this.sortMode = sortMode;
	}
	public int getSortMode() {
		return sortMode;
	}
	public void setInnerVO(List<EntryVO> innerVO) {
		this.innerVO = innerVO;
	}
	public List<EntryVO> getInnerVO() {
		return innerVO;
	}
	public void setFileParent(String fileParent) {
		this.fileParent = fileParent;
	}
	public String getFileParent() {
		return fileParent;
	}
	public void setEntry(List<EntryVO> entry) {
		this.entry = entry;
	}
	public List<EntryVO> getEntry() {
		return entry;
	}
	public String getElink() {
		return elink;
	}
	public void setElink(String elink) {
		this.elink = elink;
	}
	public String getDlink() {
		return dlink;
	}
	public void setDlink(String dlink) {
		this.dlink = dlink;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public void setUplInfo(UplInfo uplInfo) {
		this.uplInfo = uplInfo;
	}
	public UplInfo getUplInfo() {
		return uplInfo;
	}
	public String getLinkDir() {
		return linkDir;
	}
	public void setLinkDir(String linkDir) {
		this.linkDir = linkDir;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
		this.hangulFname = FTPDefault.toHangul(fName);
	}
	public String getHangulFname() {
		return hangulFname;
	}
	public void setDirDefault(String dirDefault) {
		this.dirDefault = dirDefault;
	}
	public String getDirDefault() {
		return dirDefault;
	}
	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	}
	public MultipartFile getMyFile() {
		return myFile;
	}
	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}
	public String getTotalSize() {
		return totalSize;
	}
	public void setFileCount(String fileCount) {
		this.fileCount = fileCount;
	}
	public String getFileCount() {
		return fileCount;
	}
}
