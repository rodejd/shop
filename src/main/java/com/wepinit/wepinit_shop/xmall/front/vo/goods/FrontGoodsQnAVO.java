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
/********************************************************************************
* <pre>
* 업무구분명			:	상품 상세
* 세부업무구분명	: 	사용자 > 상품 상세 > 상품별 QNA 
* 작성자 				: 	이병환
* 설명 				: 	사용자 상품별 QNA VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170217		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsQna;

import java.util.List;


public class FrontGoodsQnAVO extends CommonVO{
	
	/******************************************
	 * input 
	 ******************************************/
	private String goodsno;									// 상품번호
	private String mode;										// mode(등록:add_qna, 수정:mod_qna , 삭제:del_qna)
	private String subject;
	private String contents;
	private String sno;
	

	/******************************************
	 * output 
	 ******************************************/
	private List<GdGoodsQna> goodsQnAList;			//상품 QNA 목록

	public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	public List<GdGoodsQna> getGoodsQnAList() {
		return goodsQnAList;
	}

	public void setGoodsQnAList(List<GdGoodsQna> goodsQnAList) {
		this.goodsQnAList = goodsQnAList;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	@Override
	public String toString() {
		return "FrontGoodsQnAVO [goodsno=" + goodsno + ", mode=" + mode
				+ ", subject=" + subject + ", contents=" + contents + ", sno="
				+ sno + ", goodsQnAList=" + goodsQnAList + "]";
	}
	
	
	
}
