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
package com.wepinit.wepinit_shop.xmall.admin.dao.promotion;

import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.BannerVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBanner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

	public List<GdBanner> getBannerList(BannerVO bannerVo);
	
	public int getBannerCount(BannerVO bannerVo);
	
	public int insertBanner(BannerVO bannerVo);
	
	public GdBanner getBannerView(int sno);
	
	public int updateBanner(BannerVO bannerVo);
	
	public int updateBannerByImage(BannerVO bannerVo);
	
	public void deleteBanner(int sno);
	
}
