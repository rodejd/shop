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
package com.wepinit.wepinit_shop.xmall.admin.vo.board;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCooperation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CooperationVO extends PageMaker {
	
	private int sno;
	private String itemcd;
	private String name;
	private String email;
	private String title;
	private String content;
	private String reply;
	private Date regdt;
	private String replydt;
	private Date maildt;
	
	private String[] sregdt;		//검색 날짜
	private String[] sreplydt;
	private String[] smaildt;
	
	private String sregdt_0;
	private String sregdt_1;
	private String sreplydt_0;
	private String sreplydt_1;
	private String smaildt_0;
	private String smaildt_1;
	
	private String sreplyyn;		//답변 여부
	private String smailyn;			//메일 여부
	
	private String skey;			//선택된 검색 키워드 key
	private String sword;			//선택된 검색 키워드 value
	private String sitemcd;		//선택된 검색 분류
	private String sort;				//정렬
	
	private String allmodify;
	private String code;

	private String nolist;
	private String returnUrl;
	private String mode;
	private int totalCount;			//총 건수(화면 출력용)
	
	private List<GdCooperation> cooperList; 
	private GdCooperation cooperObj;

	
	public List<GdCooperation> getCooperList() {
		return cooperList;
	}
	public void setCooperList(List<GdCooperation> cooperList) {
		this.cooperList = cooperList;
	}
	public GdCooperation getCooperObj() {
		return cooperObj;
	}
	public void setCooperObj(GdCooperation cooperObj) {
		this.cooperObj = cooperObj;
	}

	public String[] getSregdt() {
		return sregdt;
	}
	public void setSregdt(String[] sregdt) {
		this.sregdt = sregdt;
	}
	public String[] getSreplydt() {
		return sreplydt;
	}
	public void setSreplydt(String[] sreplydt) {
		this.sreplydt = sreplydt;
	}
	public String[] getSmaildt() {
		return smaildt;
	}
	public void setSmaildt(String[] smaildt) {
		this.smaildt = smaildt;
	}
	public String getNolist() {
		return nolist;
	}
	public void setNolist(String nolist) {
		this.nolist = nolist;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	public String getSitemcd() {
		return sitemcd;
	}
	public void setSitemcd(String sitemcd) {
		this.sitemcd = sitemcd;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getAllmodify() {
		return allmodify;
	}
	public void setAllmodify(String allmodify) {
		this.allmodify = allmodify;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getSreplyyn() {
		return sreplyyn;
	}
	public void setSreplyyn(String sreplyyn) {
		this.sreplyyn = sreplyyn;
	}
	public String getSmailyn() {
		return smailyn;
	}
	public void setSmailyn(String smailyn) {
		this.smailyn = smailyn;
	}
	public String getSreplydt_0() {
		if(sreplydt != null) {
			if(sreplydt.length > 0) {
				return sreplydt[0];
			}
			return sreplydt_0;
		}
		return sreplydt_0;
	}
	public void setSreplydt_0(String sreplydt_0) {
		this.sreplydt_0 = sreplydt_0;
	}
	public String getSreplydt_1() {
		if(sreplydt != null) {
			if(sreplydt.length > 0) {
				return sreplydt[1];
			}
			return sreplydt_1;
		}
		return sreplydt_1;
	}
	public void setSreplydt_1(String sreplydt_1) {
		this.sreplydt_1 = sreplydt_1;
	}
	public String getSmaildt_0() {
		if(smaildt != null){
			if(smaildt.length > 0) {
				return smaildt[0];
			}
			return smaildt_0;
		}
		return smaildt_0;
	}
	public void setSmaildt_0(String smaildt_0) {
		this.smaildt_0 = smaildt_0;
	}
	public String getSmaildt_1() {
		if(smaildt != null) {
			if(smaildt.length > 0) {
				return smaildt[1];
			}
			return smaildt_1;
		}
		return smaildt_1;
	}
	public void setSmaildt_1(String smaildt_1) {
		this.smaildt_1 = smaildt_1;
	}
	public String getSregdt_0() {
		if(sregdt != null) {
			if(sregdt.length > 0) {
				return sregdt[0];
			}
			return sregdt_0;
		}
		return sregdt_0;
	}
	public void setSregdt_0(String sregdt_0) {
		this.sregdt_0 = sregdt_0;
	}
	public String getSregdt_1() {
		if(sregdt != null) {
			if(sregdt.length > 0) {
				return sregdt[1];
			}
			return sregdt_1;
		}
		return sregdt_1;
	}
	public void setSregdt_1(String sregdt_1) {
		this.sregdt_1 = sregdt_1;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getItemcd() {
		return itemcd;
	}
	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getReplydt() {
		return replydt;
	}
	public void setReplydt(String replydt) {
		this.replydt = replydt;
	}
	public Date getMaildt() {
		return maildt;
	}
	public void setMaildt(Date maildt) {
		this.maildt = maildt;
	}
	
	@Override
	public String toString() {
		return "CooperationVO [sno=" + sno + ", itemcd=" + itemcd + ", name="
				+ name + ", email=" + email + ", title=" + title + ", content="
				+ content + ", reply=" + reply + ", regdt=" + regdt
				+ ", replydt=" + replydt + ", maildt=" + maildt + ", sregdt="
				+ Arrays.toString(sregdt) + ", sreplydt="
				+ Arrays.toString(sreplydt) + ", smaildt="
				+ Arrays.toString(smaildt) + ", sregdt_0=" + sregdt_0
				+ ", sregdt_1=" + sregdt_1 + ", sreplydt_0=" + sreplydt_0
				+ ", sreplydt_1=" + sreplydt_1 + ", smaildt_0=" + smaildt_0
				+ ", smaildt_1=" + smaildt_1 + ", sreplyyn=" + sreplyyn
				+ ", smailyn=" + smailyn + ", skey=" + skey + ", sword="
				+ sword + ", sitemcd=" + sitemcd + ", sort=" + sort
				+ ", allmodify=" + allmodify + ", code=" + code + ", nolist="
				+ nolist + ", returnUrl=" + returnUrl + ", mode=" + mode
				+ ", totalCount=" + totalCount + ", cooperList=" + cooperList
				+ ", cooperObj=" + cooperObj + "]";
	}
}
