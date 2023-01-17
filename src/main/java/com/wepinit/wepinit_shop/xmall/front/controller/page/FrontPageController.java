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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Controller
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.controller.page;

import com.wepinit.wepinit_shop.xmall.admin.service.board.BoardService;
import com.wepinit.wepinit_shop.xmall.front.service.board.FrontBoardService;
import com.wepinit.wepinit_shop.xmall.front.vo.boutique.FrontBoutiqueVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/shop/page/")
public class FrontPageController {

	private static final Logger logger = LoggerFactory.getLogger(FrontPageController.class); 
	
	@Resource
	FrontBoardService service;
	
	@Resource
	BoardService boardService;
	
	/**
	 * 리스트 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "open")
	public String boutique(@ModelAttribute("frontBoutiqueVO") FrontBoutiqueVO frontBoutiqueVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		
	
		return "/shop/page/open";
	}
	
}
