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
package com.wepinit.wepinit_shop.xmall.admin.dao.board;

import com.wepinit.wepinit_shop.xmall.admin.vo.board.NoticeVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {
	
	public int getNoticeCount();
	public int getNoticeSearchCount(@Param("vo") NoticeVO vo);
	public List<GdNotice> getNoticeList(@Param("vo")NoticeVO vo);
	public int getSortMax(@Param("itemcd")String itemcd);
	public void insertNotice(Map<String, Object> param);
	public int getNoMax(@Param("itemcd")String itemcd);
	public void updateNoticeModify(NoticeVO noticeVO);
	public GdNotice getNoticeDetail(int sno);
	public void updateNoticeSortModify(NoticeVO noticeVO);
	public void deleteNoticeRegister(@Param("no")int no);
	public void updateNoticeListSort(Map<String,Object> param);
	public List<Integer> getNoticeExistingSort(@Param("no")int[] no, @Param("sort")int[] sort);
	public void updateNoticeByFile(NoticeVO noticeVO);
}
