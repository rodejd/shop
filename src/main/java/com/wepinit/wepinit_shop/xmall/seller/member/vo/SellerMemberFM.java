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
package com.wepinit.wepinit_shop.xmall.seller.member.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.List;

public class SellerMemberFM extends PageMaker {
	// 검색조건
	private String schType = "";
	private String schText = "";
	
	private SellerOrderMemeberVO sellerOrderMemeberVO;
	private String sellerCd;
	//gd_member의 리스트
	private List<SellerOrderMemeberVO> sellerordermemberlist;
		
	
	public String getSchType() {
		return schType;
	}

	public void setSchType(String schType) {
		this.schType = schType;
	}

	public String getSchText() {
		return schText;
	}

	public void setSchText(String schText) {
		this.schText = schText;
	}

	public SellerOrderMemeberVO getSellerOrderMemeberVO() {
		return sellerOrderMemeberVO;
	}

	public void setSellerOrderMemeberVO(SellerOrderMemeberVO sellerOrderMemeberVO) {
		this.sellerOrderMemeberVO = sellerOrderMemeberVO;
	}

	
	public List<SellerOrderMemeberVO> getSellerordermemberlist() {
		return sellerordermemberlist;
	}

	public void setSellerordermemberlist(List<SellerOrderMemeberVO> sellerordermemberlist) {
		this.sellerordermemberlist = sellerordermemberlist;
	}

	public String getSellerCd() {
		return sellerCd;
	}

	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}

	@Override
	public String toString() {
		return "SellerMemberFM [schType=" + schType + ", schText=" + schText
				+ ", sellerOrderMemeberVO=" + sellerOrderMemeberVO
				+ ", sellerordermemberlist=" + sellerordermemberlist + "]";
	}
	
	
}
