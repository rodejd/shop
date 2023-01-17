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
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.seller.goods.service.SellerReserveService;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerReserveVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shop/seller/goods/*")
public class SellerReserveController {

	@Resource
	SellerReserveService rService;
	private List<HashMap<String,String>> rst; 
	
	private static final Logger logger = LoggerFactory.getLogger(SellerReserveController.class); 
	@RequestMapping(value = "reserve")
	public String Reserve(@ModelAttribute("reserveVO") SellerReserveVO reserveVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String[] arrTmp;
		String tmp;
		HashMap<String, String> list = null;
		rst = new ArrayList<HashMap<String, String>>();
		boolean b = true;
		for(int i=1;i<=5; i++){
			tmp = ShopConfig.getProperty("main_goods_display_0"+i);
			if(!tmp.equals("")){
				arrTmp = tmp.split("\\|");
				if(arrTmp != null){
						list = new HashMap<String, String>();
						list.put("chk", arrTmp[0]);
						list.put("title", arrTmp[1]);
						list.put("tpl", arrTmp[2]);
						list.put("img", arrTmp[3]);
						list.put("size", arrTmp[4]);
						list.put("pageNum", arrTmp[5]);
						list.put("cols", arrTmp[6]);
				}else{
					b=false;
				}
			}else{
				b=false;
			}
			if(!b){
				list = new HashMap<String, String>();
				list.put("chk", "");
				list.put("title","");
				list.put("tpl", "");
				list.put("img", "");
				list.put("size", "");
				list.put("pageNum", "");
				list.put("cols", "");
			}
			rst.add(list);
		}
		reserveVO.setGoodsTypeList(rst);
		
		/* >>>>>>>>>>> dynamic query >>>>>>>>>> */
		logger.debug("@@@@@@@ reserveVO category");
		if(reserveVO.getIndicate().equals("search")){
			arrTmp = reserveVO.getCate();
			for(int i =0; i< arrTmp.length ; i++){
				logger.debug("@@@@@@@ reserveVO" + arrTmp[i]);
				if(!((arrTmp[i] !=null ? arrTmp[i] : "").equals(""))){
					reserveVO.setStrCategory(arrTmp[i]);
				}
				
			}
			if(reserveVO.getStrCategory() != null && !reserveVO.getStrCategory().equals("")){
				reserveVO.setSchCategory("c.category like '" + reserveVO.getStrCategory() + "%'");
			}
			
			reserveVO.setJoinTable(" left join (select goodsno,category from gd_goods_link c group by c.goodsno) c on a.goodsno= c.goodsno");
	
			// 검색조건 > 검색어
			if (!reserveVO.getSword().equals("") ) {
				reserveVO.setSchWord(reserveVO.getSkey()+" like '%"+reserveVO.getSword()+"%'");
			}

			// 검색조건 > 상품출력여부
			if ( !reserveVO.getOpen().equals("")) {
				reserveVO.setSchOpen("a.open = '"+reserveVO.getOpen()+"'");
			}

			// order by
			reserveVO.setOrderBy("-a.goodsno, b.link desc");
		}else if (reserveVO.getIndicate().equals("main") ) {
			if ( !reserveVO.getSmain().equals("") ) {
				reserveVO.setSchEtc("c.mode ='"+reserveVO.getSmain()+"'");
			}
			reserveVO.setJoinTable(" left join gd_goods_display c on b.goodsno = c.goodsno");
	
			// order by
			reserveVO.setOrderBy("c.sort, b.sno");
	
		}else if(reserveVO.getIndicate().equals("event")){
			if ( !reserveVO.getSevent().equals("") ) {
				reserveVO.setSchEtc( " c.mode = 'e" +reserveVO.getSevent() + "'");
			}
			reserveVO.setJoinTable(" left join gd_goods_display c on b.goodsno = c.goodsno");
			
			reserveVO.setOrderBy("c.sort, b.sno");
		}
		
		if ( !reserveVO.getIndicate().equals("")) {
			// 상품전체개수
//	 		strTotalCnt =  (dbHandler.executeXmlQuery("xmall/goods_COUNT_01", new RequestSet())).get(0, "totalCount");
			
			reserveVO.setRowCount(rService.getGoodsAllCount(reserveVO));
			if (reserveVO.getRowCount() ==0) {
				reserveVO.setRowCount(0);
			}
			
			reserveVO.setPageSize(20);
			int max= reserveVO.getRowCount();  // 페이지 목록 사이즈
			int top = reserveVO.getRowStart();  // 시작 row번호
			int[] vnum = new int[max];
			for(int i=0;i<max;i++){
				vnum[i] = max - top - i;
			}
			reserveVO.setVnum(vnum);
			reserveVO.setReserveList(rService.getReserveList(reserveVO));
			reserveVO.setTotalCnt(rService.getReserveTotalList());
		} else {
			//처음 페이지 로딩시
			if(logger.isDebugEnabled()){
				logger.debug("@@ reserve first loading ");
			}
			reserveVO.setPageSize(20);
			reserveVO.setRowCount(rService.getGoodsAllCount(reserveVO));
			reserveVO.setReserveList(rService.getReserveList(reserveVO));
		}
		
		
			logger.debug("@@ reserve"+reserveVO.toString());
		return"seller/goods/reserve";
	}
	@RequestMapping(value = "reserve/indb")
	public String ReserveIndb(@ModelAttribute("reserveVO") SellerReserveVO reserveVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		int[] arrSNO = reserveVO.getChk();
		
		if ( null != arrSNO && 0 < arrSNO.length ) {
			
			// 적용금액셋팅
			int dbAmt = StringUtil.N2I("0");  // 초기화
			if ( "direct".equals(reserveVO.getMethod()) ) {
				dbAmt = StringUtil.N2I(reserveVO.getReserve() !="0"? reserveVO.getReserve() : "0");
				logger.debug("@@ dbAmt direct"+dbAmt);
			}
			
			for (int  i = 0 ; i < arrSNO.length ; i++ ) {
				if ( !"direct".equals(reserveVO.getMethod()) ) {
					dbAmt = StringUtil.N2I(Integer.toString(reserveVO.getPriceAmt2(i))) * (StringUtil.N2I(reserveVO.getPercent())/100);
					logger.debug("@@ dbAmt"+dbAmt);
					// 내림/반올림/올림
					
					//2017-08-22 계산 수정
					if ( "down".equals(reserveVO.getRoundtype()) ) {
						dbAmt = (int) Math.floor(StringUtil.N2I(String.valueOf((dbAmt/StringUtil.N2I(reserveVO.getRoundunit())) * StringUtil.N2I(reserveVO.getRoundunit()))) );
						logger.debug("@@ dbAmt down"+dbAmt);
					}else if ( "halfup".equals(reserveVO.getRoundtype()) ) {
//						dbAmt = this.round(dbAmt, -(reserveVO.getRoundunit().length()) - 1);
						dbAmt = Math.round(dbAmt / (StringUtil.N2I(reserveVO.getRoundunit())*10)) * (StringUtil.N2I(reserveVO.getRoundunit())*10);
						logger.debug("@@ dbAmt halfup"+dbAmt);
					}else{
//						dbAmt = Math.ceil(dbAmt/StringUtil.N2D(reserveVO.getRoundunit()) * Integer.parseInt(reserveVO.getRoundunit()));
						dbAmt = (int) (Math.ceil(dbAmt / (StringUtil.N2I(reserveVO.getRoundunit())*10)) * (StringUtil.N2I(reserveVO.getRoundunit())*10));
						logger.debug("@@ dbAmt else"+dbAmt);
					}
					//2017-08-22 계산 수정 끝
				}
				logger.debug("@@ dbAmt"+dbAmt);
				// DB update 수행
				rService.setReserveGoodsOptionUpdate(arrSNO[i], "reserve ="+(int)(dbAmt));
			}
		}
			
			logger.debug("@@ reserveIndb"+reserveVO.toString());
		return "redirect:/shop/seller/goods/reserve"+"?"+reserveVO.getSearch();
	}
	
	public Double round(Double d, int decimalPlace){
	    BigDecimal bd = new BigDecimal(Double.toString(d));
	    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
}
