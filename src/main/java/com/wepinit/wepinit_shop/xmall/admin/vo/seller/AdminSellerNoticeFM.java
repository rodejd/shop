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
package com.wepinit.wepinit_shop.xmall.admin.vo.seller;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;

import java.util.List;

public class AdminSellerNoticeFM extends PageMaker {

	// 검색조건
	private String schType = "";
	private String schText = "";
	private String tmpType = "";
	private String tmpText = "";
	
	private String procType = "";	// 처리구분(I/M/D)
	private String targetURL = "";	// 처리후 리턴 url
	
	// 판매자공지사항 정보
	private AdminSellerNoticeVO noticeVO = new AdminSellerNoticeVO();
	
	// 판매자 공지사항 목록
	private List<AdminSellerNoticeVO> noticeList;

	
	public String getTmpType() {
		return tmpType;
	}

	public void setTmpType(String tmpType) {
		this.tmpType = tmpType;
	}

	public String getTmpText() {
		return tmpText;
	}

	public void setTmpText(String tmpText) {
		this.tmpText = tmpText;
	}

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

	public List<AdminSellerNoticeVO> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<AdminSellerNoticeVO> noticeList) {
		this.noticeList = noticeList;
	}

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	public AdminSellerNoticeVO getNoticeVO() {
		return noticeVO;
	}

	public void setNoticeVO(AdminSellerNoticeVO noticeVO) {
		this.noticeVO = noticeVO;
	}
	
	/**
	 * View 페이지 validation check
	 */
	public void validNoticeView() {
		// 고유번호가 없을 경우 오류처리
		if ( "".equals(noticeVO.getSno()) ) {
			throw new BizException("필수입력 누락");
		}
	}
	
	/**
	 * Proc validation check
	 */
	public void validNoticeProc(){
		if ( !"I".equals(procType) ) {
			// 고유번호가 없을 경우 오류처리
			if ( "".equals(noticeVO.getSno()) ) {
				throw new BizException("필수입력 누락 - sno");
			}
		}
		
		// 등록, 수정일 경우 제목, 내용 체크
		if ( !"D".equals(procType) ) {
			if ( "".equals(noticeVO.getTitle()) ) {
				throw new BizException("필수입력 누락 - title");
			}
			if ( "".equals(noticeVO.getContents()) ) {
				throw new BizException("필수입력 누락 - contents");
			}
		}
	}
}
