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
package com.wepinit.wepinit_shop.xmall.admin.service.promotion;

import com.wepinit.wepinit_shop.xmall.admin.dao.promotion.PopUpMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PopUpVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPopUp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PopUpService {
	
	@Resource
	PopUpMapper mapper;
	
	public List<GdPopUp> popUpList(PopUpVO popUpVO) throws Exception {
		return mapper.getPopUpList(popUpVO);
	}
	
	public int popUpCount(PopUpVO popUpVO) throws Exception {
		return mapper.getPopUpCount(popUpVO);
	}
	
	public void insertPopUp(PopUpVO vo) throws Exception {
		mapper.insertPopUp(vo);
	}
	
	public GdPopUp getPopUpListView(int sno) throws Exception {
		return mapper.getPopUpListView(sno);
	}
	
	public void updatePopUp(PopUpVO vo) throws Exception {
		mapper.updatePopUp(vo);
	}
	
	public void deletePopUp(int sno) throws Exception {
		mapper.deletePopUp(sno);
	}

	public List<GdPopUp> checkPopType(PopUpVO popUpVO) {
		return mapper.checkPopType(popUpVO);
	}

	public List<GdPopUp> checkFileName(PopUpVO popUpVO) {
		return mapper.checkFileName(popUpVO);
	}

}
