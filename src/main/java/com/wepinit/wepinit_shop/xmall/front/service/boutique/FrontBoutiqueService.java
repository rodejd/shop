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
package com.wepinit.wepinit_shop.xmall.front.service.boutique;

import com.wepinit.wepinit_shop.xmall.common.vo.GdBoutique;
import com.wepinit.wepinit_shop.xmall.front.dao.boutique.FrontBoutiqueMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.boutique.FrontBoutiqueVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FrontBoutiqueService {
	
	@Resource
	FrontBoutiqueMapper mapper;
	
	public int getFrontBoutiqueCount(FrontBoutiqueVO vo) throws Exception {
		return mapper.getFrontBoutiqueCount(vo);
	}
	
	public List<GdBoutique> getFrontBoutiqueList(FrontBoutiqueVO vo) throws Exception {
		return mapper.getFrontBoutiqueList(vo);
	}

}
