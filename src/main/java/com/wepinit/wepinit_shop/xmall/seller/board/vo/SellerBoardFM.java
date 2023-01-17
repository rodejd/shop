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
package com.wepinit.wepinit_shop.xmall.seller.board.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

public class SellerBoardFM extends PageMaker {
	
	private String sellerNm="";
	private String result="";	//팝업페이지 닫기 구분자
	
	SellerMemberQnaVO sellerMemberQnaVO = new SellerMemberQnaVO();
	SellerGoodsQnaVO sellerGoodsQnaVO = new SellerGoodsQnaVO();
	SellerGoodsReviewVO sellerGoodsReviewVO =new SellerGoodsReviewVO();
	
	public SellerGoodsReviewVO getSellerGoodsReviewVO() {
		return sellerGoodsReviewVO;
	}

	public void setSellerGoodsReviewVO(SellerGoodsReviewVO sellerGoodsReviewVO) {
		this.sellerGoodsReviewVO = sellerGoodsReviewVO;
	}

	public SellerMemberQnaVO getSellerMemberQnaVO() {
		return sellerMemberQnaVO;
	}

	public void setSellerMemberQnaVO(SellerMemberQnaVO sellerMemberQnaVO) {
		this.sellerMemberQnaVO = sellerMemberQnaVO;
	}

	public SellerGoodsQnaVO getSellerGoodsQnaVO() {
		return sellerGoodsQnaVO;
	}

	public void setSellerGoodsQnaVO(SellerGoodsQnaVO sellerGoodsQnaVO) {
		this.sellerGoodsQnaVO = sellerGoodsQnaVO;
	}

	public String getSellerNm() {
		return sellerNm;
	}

	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
