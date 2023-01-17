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
package com.wepinit.wepinit_shop.xmall.admin.vo.promotion;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPopUp;

import java.util.ArrayList;
import java.util.List;


public class PopUpVO  extends PageMaker {
	//input
	private String title;
	private String skey;
	private String sword;
	private ArrayList<String> sregdt;
	private String sregdt0;
	private String sregdt1;
	private String use;
	private String popUpType;
	private String type;
	private String mode;
	private String design_filel;
	private String tplFile;
	
	private String oldfile;
	private String content;
	
	private int sno;
	private String sub;
	private String filename;
	private String popuse;
	private int popspotw;
	private int popspoth;
	private int popsizew;
	private int popsizeh;
	private String popsdt;
	private String popedt;
	private String popsdate;
	private String popedate;
	private String repopsdt;
	private String repopedt;
	private String repopsdate;
	private String repopedate;
	private String poptype;
	private String poppik;
	
	private String popupsdttg;
	private String popupedttg;
	private String popupstimetg;
	private String popupetimetg;
	
	private String popupsdt;
	private String popupedt;
	private String popupstime;
	private String popupetime;
	
	//output
	private List<GdPopUp> popUpList = null;
	private GdPopUp popUpObject;
	private String fileContents;
	private List<String> selectOptionList;
	
	//otherData
	private String errorMsg;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
	}
	public ArrayList<String> getSregdt() {
		return sregdt;
	}
	public void setSregdt(ArrayList<String> sregdt) {
		this.sregdt = sregdt;
	}
	public String getSregdt0() {
		return sregdt0;
	}
	public void setSregdt0(String sregdt0) {
		this.sregdt0 = sregdt0;
	}
	public String getSregdt1() {
		return sregdt1;
	}
	public void setSregdt1(String sregdt1) {
		this.sregdt1 = sregdt1;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getPopUpType() {
		return popUpType;
	}
	public void setPopUpType(String popUpType) {
		this.popUpType = popUpType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDesign_filel() {
		return design_filel;
	}
	public void setDesign_filel(String design_filel) {
		this.design_filel = design_filel;
	}
	public String getTplFile() {
		return tplFile;
	}
	public void setTplFile(String tplFile) {
		this.tplFile = tplFile;
	}
	public String getOldfile() {
		return oldfile;
	}
	public void setOldfile(String oldfile) {
		this.oldfile = oldfile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPopuse() {
		return popuse;
	}
	public void setPopuse(String popuse) {
		this.popuse = popuse;
	}
	public int getPopspotw() {
		return popspotw;
	}
	public void setPopspotw(int popspotw) {
		this.popspotw = popspotw;
	}
	public int getPopspoth() {
		return popspoth;
	}
	public void setPopspoth(int popspoth) {
		this.popspoth = popspoth;
	}
	public int getPopsizew() {
		return popsizew;
	}
	public void setPopsizew(int popsizew) {
		this.popsizew = popsizew;
	}
	public int getPopsizeh() {
		return popsizeh;
	}
	public void setPopsizeh(int popsizeh) {
		this.popsizeh = popsizeh;
	}
	public String getPopsdt() {
		return popsdt;
	}
	public void setPopsdt(String popsdt) {
		this.popsdt = popsdt;
	}
	public String getPopedt() {
		return popedt;
	}
	public void setPopedt(String popedt) {
		this.popedt = popedt;
	}
	public String getPopsdate() {
		return popsdate;
	}
	public void setPopsdate(String popsdate) {
		this.popsdate = popsdate;
	}
	public String getPopedate() {
		return popedate;
	}
	public void setPopedate(String popedate) {
		this.popedate = popedate;
	}
	public String getRepopsdt() {
		return repopsdt;
	}
	public void setRepopsdt(String repopsdt) {
		this.repopsdt = repopsdt;
	}
	public String getRepopedt() {
		return repopedt;
	}
	public void setRepopedt(String repopedt) {
		this.repopedt = repopedt;
	}
	public String getRepopsdate() {
		return repopsdate;
	}
	public void setRepopsdate(String repopsdate) {
		this.repopsdate = repopsdate;
	}
	public String getRepopedate() {
		return repopedate;
	}
	public void setRepopedate(String repopedate) {
		this.repopedate = repopedate;
	}
	public String getPoptype() {
		return poptype;
	}
	public void setPoptype(String poptype) {
		this.poptype = poptype;
	}
	public String getPoppik() {
		return poppik;
	}
	public void setPoppik(String poppik) {
		this.poppik = poppik;
	}
	public String getPopupsdttg() {
		return popupsdttg;
	}
	public void setPopupsdttg(String popupsdttg) {
		this.popupsdttg = popupsdttg;
	}
	public String getPopupedttg() {
		return popupedttg;
	}
	public void setPopupedttg(String popupedttg) {
		this.popupedttg = popupedttg;
	}
	public String getPopupstimetg() {
		return popupstimetg;
	}
	public void setPopupstimetg(String popupstimetg) {
		this.popupstimetg = popupstimetg;
	}
	public String getPopupetimetg() {
		return popupetimetg;
	}
	public void setPopupetimetg(String popupetimetg) {
		this.popupetimetg = popupetimetg;
	}
	public String getPopupsdt() {
		return popupsdt;
	}
	public void setPopupsdt(String popupsdt) {
		this.popupsdt = popupsdt;
	}
	public String getPopupedt() {
		return popupedt;
	}
	public void setPopupedt(String popupedt) {
		this.popupedt = popupedt;
	}
	public String getPopupstime() {
		return popupstime;
	}
	public void setPopupstime(String popupstime) {
		this.popupstime = popupstime;
	}
	public String getPopupetime() {
		return popupetime;
	}
	public void setPopupetime(String popupetime) {
		this.popupetime = popupetime;
	}
	public List<GdPopUp> getPopUpList() {
		return popUpList;
	}
	public void setPopUpList(List<GdPopUp> popUpList) {
		this.popUpList = popUpList;
	}
	public GdPopUp getPopUpObject() {
		return popUpObject;
	}
	public void setPopUpObject(GdPopUp popUpObject) {
		this.popUpObject = popUpObject;
	}
	public String getFileContents() {
		return fileContents;
	}
	public void setFileContents(String fileContents) {
		this.fileContents = fileContents;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Override
	public String toString() {
		return "PopUpVO \n[title=" + title + ", skey=" + skey + ", sword="
				+ sword + ", sregdt=" + sregdt + ", sregdt0=" + sregdt0
				+ ", sregdt1=" + sregdt1 + ", use=" + use + ", popUpType="
				+ popUpType + ", type=" + type + ", mode=" + mode
				+ ", design_filel=" + design_filel + ", tplFile=" + tplFile
				+ ", oldfile=" + oldfile + ", content=" + content + ", sno="
				+ sno + ", sub=" + sub + ", filename=" + filename + ", popuse="
				+ popuse + ", popspotw=" + popspotw + ", popspoth=" + popspoth
				+ ", popsizew=" + popsizew + ", popsizeh=" + popsizeh
				+ ", popsdt=" + popsdt + ", popedt=" + popedt + ", popsdate="
				+ popsdate + ", popedate=" + popedate + ", repopsdt="
				+ repopsdt + ", repopedt=" + repopedt + ", repopsdate="
				+ repopsdate + ", repopedate=" + repopedate + ", poptype="
				+ poptype + ", poppik=" + poppik + ", popupsdttg=" + popupsdttg
				+ ", popupedttg=" + popupedttg + ", popupstimetg="
				+ popupstimetg + ", popupetimetg=" + popupetimetg
				+ ", popupsdt=" + popupsdt + ", popupedt=" + popupedt
				+ ", popupstime=" + popupstime + ", popupetime=" + popupetime
				+ ", popUpList=" + popUpList + ", popUpObject=" + popUpObject
				+ ", fileContents=" + fileContents + ", errorMsg=" + errorMsg
				+ "]";
	}
}
