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
package com.wepinit.wepinit_shop.xmall.admin.controller.goods;

import com.wepinit.wepinit_shop.xmall.admin.vo.goods.ImgsizeVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop/admin/goods/*")
public class ImgsizeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImgsizeController.class);
	
	@RequestMapping("imgsize")
	public String ImgSize(@ModelAttribute("imgsizeVO") ImgsizeVO imgsizeVO,
	HttpServletRequest req, HttpServletResponse res) throws Exception {
		imgsizeVO.setImgL(ShopConfig.getProperty("img_l"));
		imgsizeVO.setImgM(ShopConfig.getProperty("img_m"));
		imgsizeVO.setImgS(ShopConfig.getProperty("img_s"));
		imgsizeVO.setImgI(ShopConfig.getProperty("img_i"));
		logger.debug("@@ imgsize "+imgsizeVO.toString());
		return "/shop/admin/goods/imgsize";
	}
	@RequestMapping("imgsize/indb")
	public String ImgSizeIndb(@ModelAttribute("imgsizeVO") ImgsizeVO imgsizeVO, 
	HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ShopConfig.setProperty("img_l", imgsizeVO.getImgL());
		ShopConfig.setProperty("img_m", imgsizeVO.getImgM());
		ShopConfig.setProperty("img_s",imgsizeVO.getImgS());
		ShopConfig.setProperty("img_i", imgsizeVO.getImgI());
		
		logger.debug("@@ imgsize indb "+imgsizeVO.toString());
	return "redirect:/shop/admin/goods/imgsize";
	}
	
}
