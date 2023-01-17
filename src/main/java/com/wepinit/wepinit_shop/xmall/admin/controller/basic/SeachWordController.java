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
package com.wepinit.wepinit_shop.xmall.admin.controller.basic;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.basic.ExchangeService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.SearchWordVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class SeachWordController {
	
	@Resource
	ExchangeService service;
	
	@RequestMapping(value = "sword")
	public String exchange(@ModelAttribute("searchWordVO") SearchWordVO searchWordVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		List<SearchWordVO> recommendList = new ArrayList<SearchWordVO>();
		List<SearchWordVO> popularList = new ArrayList<SearchWordVO>();
		for (int i = 0; i < 10; i++) {
			SearchWordVO recommend = new SearchWordVO();
			recommend.setWord(ShopConfig.getProperty("sword_recommend" + i));
			recommendList.add(recommend);
			
			SearchWordVO popular = new SearchWordVO();
			popular.setWord(ShopConfig.getProperty("sword_popular" + i));
			popular.setMark(ShopConfig.getProperty("sword_popular_mark" + i));
			popularList.add(popular);
		}
		searchWordVO.setRecommendList(recommendList);
		searchWordVO.setPopularList(popularList);
		
		return "/shop/admin/basic/sword";
	}
	
	@RequestMapping(value = "sword/indb")
	public String exchangeIndb(@ModelAttribute("searchWordVO") SearchWordVO searchWordVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		for (int i = 0; i < 10; i++) {
			SearchWordVO recommend = searchWordVO.getRecommendList().get(i);
			ShopConfig.setProperty("sword_recommend" + i, recommend.getWord());
			
			SearchWordVO popular = searchWordVO.getPopularList().get(i);
			ShopConfig.setProperty("sword_popular" + i, popular.getWord());
			ShopConfig.setProperty("sword_popular_mark" + i, popular.getMark());
		}
		
		ShopConfig.setProperty("sword_update", DateUtil.getFormatToday("yyyy-MM-dd HH:mm:ss"));
		
		return "redirect:/shop/admin/basic/sword?menuKey=191";
	}
	
}
