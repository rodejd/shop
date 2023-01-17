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

import com.wepinit.wepinit_shop.xmall.admin.vo.board.FaqVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdFaq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqMapper {
	
	public int getFaqTotalCount();
	public int getFaqSearchCount(@Param("vo") FaqVO vo);
	public List<GdFaq> getFaqList(@Param("vo")FaqVO vo);
	public int getSortMax(@Param("itemcd")String itemcd);
	public void insertFapReply(Map<String, Object> param);
	public int getSnoMax(@Param("itemcd")String itemcd);
	public void updateFaqModify(FaqVO faqVO);
	public GdFaq getFaqDetail(int sno);
	public void updateAllFaqModify(FaqVO faqVO);
	public void updateFaqSortModify(FaqVO faqVO);
	public List<GdFaq> getFaqSortSno(String itemcd);
	public void deleteFaqRegister(@Param("sno")int sno);
	public void updateFaqListSort(Map<String,Object> param);
	public List<Integer> getFaqExistingSort(@Param("sno")int[] sno, @Param("sort")int[] sort);
}
