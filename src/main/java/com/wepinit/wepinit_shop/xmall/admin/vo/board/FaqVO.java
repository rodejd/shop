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
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdFaq;

import java.util.Date;
import java.util.List;

public class FaqVO extends PageMaker {

	private int sno = 0;
	private String itemcd;
	private String question;
	private String descant;
	private String answer;
	private int sort = 0;
	private Date regdt;
	private String best;
	private int bestsort;

	private int totalCount;		//총 건수
	
	private String[] sregdt;	//검색 날짜
	private String sregdt_0;
	private String sregdt_1;

	private String searchsort;		//정렬
	private String skey;			//선택된 키워드 검색 key값
	private String sitemcd;		//선택된 분류선택
	private String sword;			//키워드 겁색 value값
	private String sbest;
	
	private String allmodify;
	private String code;
	private String nolist;

	private String mode;
	private String returnUrl;
	
	private List<GdFaq> faqList;
	private GdFaq faqObj;

	private List<GdCode> codeList;

	public List<GdCode> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<GdCode> codeList) {
		this.codeList = codeList;
	}
	public List<GdFaq> getFaqList() {
		return faqList;
	}
	public void setFaqList(List<GdFaq> faqList) {
		this.faqList = faqList;
	}
	public GdFaq getFaqObj() {
		return faqObj;
	}
	public void setFaqObj(GdFaq faqObj) {
		this.faqObj = faqObj;
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String[] getSregdt() {
		return sregdt;
	}
	public void setSregdt(String[] sregdt) {
		this.sregdt = sregdt;
	}
	public String getSearchsort() {
		return searchsort;
	}
	public void setSearchsort(String searchsort) {
		this.searchsort = searchsort;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getSitemcd() {
		if (sitemcd == null) {
			sitemcd = "all";
		}
		return sitemcd;
	}
	public void setSitemcd(String sitemcd) {
		this.sitemcd = sitemcd;
	}

	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
	}
	public String getSbest() {
		return sbest;
	}
	public void setSbest(String sbest) {
		this.sbest = sbest;
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
	public String getNolist() {
		return nolist;
	}
	public void setNolist(String nolist) {
		this.nolist = nolist;
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
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescant() {
		return descant;
	}

	public void setDescant(String descant) {
		this.descant = descant;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getRegdt() {
		return regdt;
	}

	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}

	public String getBest() {
		if(best == null || "".equals(best)){
			best = "N";
		}
		return best;
	}

	public void setBest(String best) {
		this.best = best;
	}

	public int getBestsort() {
		return bestsort;
	}

	public void setBestsort(Object tmp) {
		if(tmp == null || "".equals(tmp)){
			bestsort = 0;
		} else {
			bestsort = Integer.valueOf(String.valueOf(tmp));
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSregdt_0() {
		if(sregdt != null ) {
			if(sregdt.length >0) {
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

}
