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
package com.wepinit.wepinit_shop.xmall.seller.goods.controller;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.seller.goods.service.SellerGoodsService;
import com.wepinit.wepinit_shop.xmall.seller.goods.service.SellerLinkService;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerLinkVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/shop/seller/goods/*")
public class SellerLinkController {

	@Resource
	SellerLinkService lService;
	
	@Resource
	SellerGoodsService gService;
	
 	private static final Logger logger = LoggerFactory.getLogger(SellerLinkController.class); 
 	
	@RequestMapping(value = "link")
	public String Link(@ModelAttribute("linkVO") SellerLinkVO linkVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//총 건수
		linkVO.setTotalCount(lService.getLinkListTotalCount());
		
		//카테고리
		if(linkVO.getCate() != null){
			if(linkVO.getCate().length > 0){
				for(int i=0; i<linkVO.getCate().length; i++){
					if(!"".equals(StringUtil.nullConv(linkVO.getCate()[i], ""))){
						linkVO.setStrCategory(linkVO.getCate()[i]);
					}
				}
			}
		}
		linkVO.setLinkList(lService.getLinkList(linkVO));
		
		return "seller/goods/link";
	}
	
	@RequestMapping(value = "link/indb")
	public String LinkIndb(@ModelAttribute("linkVO") SellerLinkVO linkVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ link/indb >>"+ linkVO.getMode());
		}
		
		HashMap param = new HashMap(); // DB처리를 위한 map
		
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 연결
		 * ===========================================================================================
		 */
		if("link".equals(linkVO.getMode())){
			
			String goodsNo = "";
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						goodsNo += linkVO.getChk()[i] + ",";
					}
				}
			}
			if(logger.isDebugEnabled()){
				logger.debug("@@ goodsNo Array >> "+goodsNo);
			}
			
			linkVO.setLinkEventList(lService.getGoodsLinkEventConnectLIST(goodsNo.substring(0, goodsNo.length()-1)));
			
			if(linkVO.getCateSub() != null){
				if(linkVO.getCateSub().length > 0){
					for(int i=0; i<linkVO.getCateSub().length; i++){
						if(!"".equals(StringUtil.nullConv(linkVO.getCateSub()[i], ""))){
							linkVO.setStrCategorySub(linkVO.getCateSub()[i]);
						}
					}
				}
			}
			
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						
						if(logger.isDebugEnabled()){
							logger.debug("@@ print Parameter chk >> "+linkVO.getChk()[i]+" category sub >> "+ linkVO.getStrCategorySub() + " hidden >> "+ lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()));
						}
						
						// 이벤트 연결이 되어있는 상품의 개수를 추출한다.
						lService.setGoodsCategoryINSERT01(linkVO.getChk()[i], linkVO.getStrCategorySub(), lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()));
						
						if(logger.isDebugEnabled()){
							logger.debug("@@ isToday >>"+ linkVO.getIsToday());
						}
						
						// 상품등록일 수정
						if("Y".equals(linkVO.getIsToday())){
							lService.setGoodsInfoUPDATE(linkVO.getChk()[i]);
						}
						
						// 이벤트 카테고리 연결이 없을 경우만 등록한다.
						int j=0;
						while (linkVO.getLinkEventList() != null && linkVO.getLinkEventList().size() > j ){
							if(linkVO.getChk()[i] == linkVO.getLinkEventList().get(j).getGoodsno()
									&& linkVO.getLinkEventList().get(j).getSno() != 0
									&& !"".equals(linkVO.getLinkEventList().get(j).getMode())
									&& linkVO.getLinkEventList().get(j).getDisCount() != 0){
								
								if(logger.isDebugEnabled()){
									logger.debug("@@ goodsno, mode, sort >> ");
								}
								lService.setGoodsDisplayINSERT(linkVO.getChk()[i],  linkVO.getLinkEventList().get(j).getMode(), linkVO.getLinkEventList().get(j).getSort());
								break;
							}
							j++;
						}
					
					}
				}
			}
			
		}
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 이동
		 * ===========================================================================================
		 */
		else if("move".equals(linkVO.getMode())){
			
			// 연결/이동/복사 에 사용되는 카테고리 셋팅
			if(linkVO.getCateSub() != null){
				if(linkVO.getCateSub().length > 0){
					for(int i=0; i<linkVO.getCateSub().length; i++){
						if(!"".equals(StringUtil.nullConv(linkVO.getCateSub()[i], ""))){
							linkVO.setStrCategorySub(linkVO.getCateSub()[i]);
						}
					}
				}
			}
			
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						lService.updateGoodsLink(linkVO.getStrCategorySub(), lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()), 
								linkVO.getChk()[i], req.getParameter("category_"+linkVO.getChk()[i]));
						
						// 상품등록일 수정
						if("Y".equals(linkVO.getIsToday())){
							lService.setGoodsInfoUPDATE(linkVO.getChk()[i]);
						}
					}
				}
			}
			
		}
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 복사
		 * ===========================================================================================
		 */
		else if("copyGoodses".equals(linkVO.getMode())){
			
			String goodsNo = "";
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						goodsNo += linkVO.getChk()[i] + ",";
					}
				}
			}
			if(logger.isDebugEnabled()){
				logger.debug("@@ goodsNo Array >> "+goodsNo);
			}
			
			linkVO.setLinkEventList(lService.getGoodsLinkEventConnectLIST(goodsNo.substring(0, goodsNo.length()-1)));
			
			if(linkVO.getCateSub() != null){
				if(linkVO.getCateSub().length > 0){
					for(int i=0; i<linkVO.getCateSub().length; i++){
						if(!"".equals(StringUtil.nullConv(linkVO.getCateSub()[i], ""))){
							linkVO.setStrCategorySub(linkVO.getCateSub()[i]);
						}
					}
				}
			}
			
			int copyGoodsNo = 0;
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						
						// 상품복사를 완료한뒤 상품번호를 받는다.
						param.clear();
						param.put("goodsno", String.valueOf(linkVO.getChk()[i]));
						copyGoodsNo = gService.copyGoods2(param);
						
						// 복사된 상품번호로 변경하여 update수행
						lService.updateGoodsLink(linkVO.getStrCategorySub(), lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()), 
								copyGoodsNo, req.getParameter("category_"+linkVO.getChk()[i]));
						
						// 이벤트 카테고리 연결이 없을 경우만 등록한다.
						int j=0;
						while (linkVO.getLinkEventList() != null && linkVO.getLinkEventList().size() > j ){
							if(linkVO.getChk()[i] == linkVO.getLinkEventList().get(j).getGoodsno()
									&& linkVO.getLinkEventList().get(j).getSno() != 0
									&& !"".equals(linkVO.getLinkEventList().get(j).getMode())
									&& linkVO.getLinkEventList().get(j).getDisCount() != 0){
								
								if(logger.isDebugEnabled()){
									logger.debug("@@ goodsno, mode, sort >> ");
								}
								
								lService.setGoodsDisplayINSERT(linkVO.getChk()[i],  linkVO.getLinkEventList().get(j).getMode(), linkVO.getLinkEventList().get(j).getSort());
								break;
							}
							j++;
						}
					}
				}
			}
			
		}
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 삭제
		 * ===========================================================================================
		 */
		else if("delGoodses".equals(linkVO.getMode())){
			
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						param.clear();
						param.put("goodsno", String.valueOf(linkVO.getChk()[i]));
						gService.deleteGoods(param);
					}
				}
			}
		}
		
		return "redirect:/shop/seller/goods/link"+"?"+linkVO.getSearch();
	}

}
