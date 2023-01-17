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
package com.wepinit.wepinit_shop.xmall.admin.dao.seller;

import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerNoticeFM;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerNoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminSellerNoticeMapper {
	
	/**
	 * 판매자 공지사항 총 갯수
	 * @param noticeFM
	 * @return
	 */
	public int getSellerNoticeListCount(AdminSellerNoticeFM noticeFM);
	
	/**
	 * 판매자 공지사항 목록
	 * @param noticeVO
	 * @return
	 */
	public List<AdminSellerNoticeVO> getSellerNoticeList(AdminSellerNoticeFM noticeFM);
	
	/**
	 * 판매자 공지사항 정보
	 * @param noticeFM
	 * @return
	 */
	public AdminSellerNoticeVO getSellerNoticeView(AdminSellerNoticeFM noticeFM);

	/**
	 * 판매자 공지사항 insert
	 * @param noticeVO
	 * @return
	 */
	public int getSellerNoticeInsert(AdminSellerNoticeVO noticeVO);
	
	/**
	 * 판매자 공지사항 update
	 * @param noticeVO
	 * @return
	 */
	public int getSellerNoticeModify(AdminSellerNoticeVO noticeVO);
	
	/**
	 * 판매자 공지사항 delete
	 * @param noticeVO
	 * @return
	 */
	public int getSellerNoticeDelete(AdminSellerNoticeVO noticeVO);
}
