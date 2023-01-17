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

import com.wepinit.wepinit_shop.xmall.admin.dao.promotion.BannerMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.BannerVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBanner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerService {
	
	@Resource
	BannerMapper mapper;

	public int getBannerTotalCount() {
		BannerVO bannerVo = new BannerVO();
		return mapper.getBannerCount(bannerVo);
	}
	
	public List<GdBanner> getBannerList(BannerVO bannerVo) throws Exception {
		return mapper.getBannerList(bannerVo);
	}
	
	public int getBannerCount(BannerVO bannerVo) throws Exception {
		return mapper.getBannerCount(bannerVo);
	}
	
	public int insertBanner(BannerVO bannerVo) throws Exception {
		return mapper.insertBanner(bannerVo);
	}
	
	public GdBanner getBannerView(int sno) throws Exception {
		return mapper.getBannerView(sno);
	}
	
	public int updateBanner(BannerVO bannerVo) throws Exception {
		return mapper.updateBanner(bannerVo);

	}
	public int updateBannerByImage(BannerVO bannerVo) throws Exception {
		return mapper.updateBannerByImage(bannerVo);
	}
	
	public void deleteBanner(int sno) throws Exception {
		mapper.deleteBanner(sno);
	}

}
