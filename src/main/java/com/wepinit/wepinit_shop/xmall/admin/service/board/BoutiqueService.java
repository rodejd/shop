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
package com.wepinit.wepinit_shop.xmall.admin.service.board;

import com.wepinit.wepinit_shop.xmall.admin.dao.board.BoutiqueMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.BoutiqueVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBoutique;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoutiqueService {
	
	@Resource
	BoutiqueMapper mapper;

	public int getBoutiqueTotalCount() {
		BoutiqueVO boutiqueVo = new BoutiqueVO();
		return mapper.getBoutiqueCount(boutiqueVo);
	}
	
	public List<GdBoutique> getBoutiqueList(BoutiqueVO boutiqueVo) throws Exception {
		return mapper.getBoutiqueList(boutiqueVo);
	}
	
	public int getBoutiqueCount(BoutiqueVO boutiqueVo) throws Exception {
		return mapper.getBoutiqueCount(boutiqueVo);
	}
	
	public int insertBoutique(BoutiqueVO boutiqueVo) throws Exception {
		return mapper.insertBoutique(boutiqueVo);
	}
	
	public GdBoutique getBoutiqueView(int sno) throws Exception {
		return mapper.getBoutiqueView(sno);
	}
	
	public int updateBoutique(BoutiqueVO boutiqueVo) throws Exception {
		return mapper.updateBoutique(boutiqueVo);

	}
	public int updateBoutiqueByImage(BoutiqueVO boutiqueVo) throws Exception {
		return mapper.updateBoutiqueByImage(boutiqueVo);
	}
	
	public void deleteBoutique(int sno) throws Exception {
		mapper.deleteBoutique(sno);
	}

}
