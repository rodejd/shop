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

import com.wepinit.wepinit_shop.xmall.admin.service.goods.DispMainService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.DispMainVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GoodsdisplayGoodsGoodsoption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/shop/admin/goods/*")
public class DispMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(DispMainController.class);
	
	@Resource
	DispMainService service;
	
	@RequestMapping(value="dispMain")
	public String dispMain(@ModelAttribute("dispMainVO") DispMainVO dispMainVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ dispMain ");
		}
		Map<String, ArrayList> arrMap = new HashMap<String, ArrayList>();
		List list = ShopLibFunction.getGoodsType();	// 메인페이지 상품진열 기본 데이터
		List<GoodsdisplayGoodsGoodsoption> rtList = service.getGoodsDisplayList(dispMainVO);
		
		// 진열할 상품정보를 가지고 온다. 루핑에 맞게 데이터를 재조합하여 세팅한다.
		if (rtList != null && rtList.size() > 0) {
			for (GoodsdisplayGoodsGoodsoption gdggs : rtList){
				String key = gdggs.getMode();
				if( arrMap.get(key) == null ){
					arrMap.put(key, new ArrayList());
				}
				int listSize = arrMap.get(key).size();
					
				Map<String, String> subMap = new HashMap<String, String>();
				subMap.put("mode", gdggs.getMode());
				subMap.put("goodsno", gdggs.getGoodsno());
				subMap.put("goodsnm", gdggs.getGoodsnm());
				subMap.put("goodsnmKR", gdggs.getGoodsnmKR());
				subMap.put("goodsnmCN", gdggs.getGoodsnmCN());
				subMap.put("img_s", gdggs.getImgs());
				subMap.put("price", gdggs.getPrice());
				arrMap.get(key).add(listSize, subMap);
			}
		}
		dispMainVO.setDispMainList(rtList);
		dispMainVO.setArrMap(arrMap);
		dispMainVO.setList(list);
		
		return "/shop/admin/goods/disp_main";
	}
	
	
	@RequestMapping(value="dispMain/indb")
	public String dispMainIndb(@ModelAttribute("dispMainVO")DispMainVO dispMainVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ dispMain indb >> "+dispMainVO);
		}
		// chk, title, tpl, img, size, page_num, cols
				String[] arrTitle 	= dispMainVO.getTitle();
				String[] arrImg 	= dispMainVO.getImg();
				String[] arrPageNum = dispMainVO.getPage_num();
				String[] arrCols 	= dispMainVO.getCols();
				String[] arrGoodsNo		= null;
				int k = 0;
				int j = 0;
				
				logger.debug("<br>================================================================");
				logger.debug("<br>---title[]-" + arrTitle.length + "----img[]-" + arrImg.length + "----page_num[]-" + arrPageNum.length + "-----cols[]-" + arrCols.length + "||||||||||||||||");
				logger.debug("<br>================================================================");
				StringBuffer sb = null;
				for (int i=0 ; i < 5 ; i++ ) {
					sb = new StringBuffer()
					.append(req.getParameter("chk["+i+"]") + "|")
					.append(arrTitle[i] + "|")
					.append(req.getParameter("tpl["+i+"]") + "|")
					.append(arrImg[i] + "|")
					.append(""+"|")
					.append(arrPageNum[i] + "|")
					.append(arrCols[i]);
					
					logger.debug("<br>================================================================");
					logger.debug("<br>" + sb.toString());
					logger.debug("<br>================================================================");
					
					// 디비에 저장된 메인상품진열 정보를 삭제한다.
					dispMainVO.setMode(String.valueOf(i));
					service.deleteGoodsDisplay(dispMainVO);
					
					arrGoodsNo = req.getParameterValues("e_step"+i+"[]");
					if ( null != arrGoodsNo) {
						k = 0;
						for ( j=0 ; j < arrGoodsNo.length ; j++ ) {
							dispMainVO.setGoodsno(arrGoodsNo[j]);
							dispMainVO.setSort(String.valueOf(++k));
							service.insertGoodsDisplay(dispMainVO);
							logger.debug("dispMainVO::["+j+"]"+dispMainVO);
						}
					}
					logger.debug("dispMainVO::"+dispMainVO);
					// ShopConfig 데이터 수정
					ShopConfig.setProperty("main_goods_display_0" + (i+1) , sb.toString());
				}
		String returnUrl = "redirect:/shop/admin/goods/dispMain";
		
		return returnUrl;
	}

}
