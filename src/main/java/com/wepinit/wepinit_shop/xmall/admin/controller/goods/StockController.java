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

import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.StockService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.StockVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop/admin/goods/*")
public class StockController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Resource
	StockService sService;
	
	@Resource
	GoodsService gService;
	
	@RequestMapping("stock")
	public String StockList(@ModelAttribute("stockVO") StockVO stockVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		/* >>>>>>>>>>> dynamic query >>>>>>>>>> */
		if(stockVO.getCate() !=null && stockVO.getCate().length !=0){
			String[] arrTmp = stockVO.getCate();
			for(String arr:arrTmp){
				if( !(arr != null? arr : "").equals("") ){
					stockVO.setSchCategory(arr);
					stockVO.setStrCategory(arr);
				}
			}
			if (logger.isDebugEnabled()) logger.debug("@@ arrTmp = "+ stockVO.getSchCategory());
			if(!stockVO.getSchCategory().equals("") && stockVO.getJoinTable().equals("")){
				stockVO.setJoinTable("Y");
			}
		}
		
//		stockVO.setOrderBy("a.goodsno");
		if(!stockVO.getSort().equals("")){
			stockVO.setOrderBy(stockVO.getSort());
		}
		
		//총 페이지수
		stockVO.setRowCount(sService.getGoodsCount(stockVO));
		//각각 페이지를 생성
		stockVO.setPageSize(20);
		int max= stockVO.getRowCount();  // 페이지 목록 사이즈
		int top = stockVO.getRowStart();  // 시작 row번호
		int[] vnum = new int[max];
		for(int i=0;i<max;i++){
			vnum[i] = max - top- i;
		}
		//한 페이지 번호
		stockVO.setVnum(vnum);
			
//		stockVO.setGoodsList(sService.getGoodsList(stockVO.getAddColumn(),stockVO.getSchCategory() , stoc kVO.getSchWord()  ,stockVO.getSchOpen(), stockVO.getSchStock(),stockVO.getSchEtc() , stockVO.getOrderBy(), stockVO.getGroupBy() ,  stockVO.getRowStart(), stockVO.getPageSize()));
		stockVO.setGoodsList(sService.getGoodsList(stockVO));
			
		if (logger.isDebugEnabled()) logger.debug("@@ stock "+ stockVO.toString());
		if (logger.isDebugEnabled()) logger.debug("@@ stock goodsList "+ stockVO.getGoodsList());
		stockVO.setTotalCnt(gService.getGoodsListTotalCount());
		 
	
		 
		return "/shop/admin/goods/stock";
	}
	@RequestMapping("stock/indb")
	public String StockIndb(@ModelAttribute("stockVO") StockVO stockVO, 
			HttpServletRequest req, HttpServletResponse res) throws Exception {
			logger.debug("@@ stock indb "+stockVO.toString());
			logger.debug("@@ stock indb sno = "+stockVO.getSno()+" godosno = " +stockVO.getGoodsno()+" modeKey = "+stockVO.getModeKey());
			logger.debug("@@ stock indb price ="+stockVO.getPrice()+" consumer ="+stockVO.getConsumer()+" supply = "+stockVO.getSupply()+" reserve ="+stockVO.getReserve());
			
			int[] arrSno = stockVO.getSno();
			int[] arrGoodsno = stockVO.getGoodsno();
			/*String[] arrOpt1 = stockVO.getOpt1();*/
			int[] modeKey = stockVO.getModeKey();
			//price update
			for(int i=0;i<arrSno.length;i++){
				logger.debug("@@ stock indb arrSno = "+arrSno[i]);
			}
			for(int i=0;i<arrGoodsno.length;i++){
				logger.debug("@@ stock indb arrGoodsno = "+arrGoodsno[i]);
			}
			for(int i=0;i<modeKey.length;i++){
				logger.debug("@@ stock indb modeKey = "+modeKey[i]);
			}
			for(int i =0;i<arrGoodsno.length && arrGoodsno !=null;i++ ) {
				sService.setStockPriceUpdate(stockVO.getPrice(modeKey[i]),stockVO.getConsumer(modeKey[i]),stockVO.getSupply(modeKey[i]),arrGoodsno[i]);
			}
			//stock udpate
			for(int i=0; i<arrSno.length && arrSno !=null;i++){
				sService.setStockStockUpdate(stockVO.getStock(i), arrSno[i]);
			}
			
			logger.debug("@@@@@@ search" +stockVO.getSearch());
			
		return "redirect:/shop/admin/goods/stock"+"?"+stockVO.getSearch();
	}
	
}
