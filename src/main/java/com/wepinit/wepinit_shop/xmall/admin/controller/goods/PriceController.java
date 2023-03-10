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

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.PriceService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.PriceVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
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
@RequestMapping("/shop/admin/goods/*")
public class PriceController {

	
	@Resource
	PriceService pService;
	@Resource
	GoodsService gService;
	
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

	private List<HashMap<String,String>> rst; 
	
	@RequestMapping(value = "price")
	public String Price(@ModelAttribute("priceVO") PriceVO priceVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("@@ price "+priceVO.toString());
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
		priceVO.setGoodsTypeList(rst);
		
		/* >>>>>>>>>>> dynamic query >>>>>>>>>> */
		priceVO.setOrderBy("-a.goodsno, b.link desc");
		if(priceVO.getIndicate().equals("search")){
			arrTmp = priceVO.getCate();
			for(int i =0; i< arrTmp.length ; i++){
				if(!(arrTmp[i] !=null ? arrTmp[i] : "").equals("")){
					priceVO.setStrCategory(arrTmp[i]);
				}
			}
			if(priceVO.getStrCategory() != null && !priceVO.getStrCategory().equals("")){
				priceVO.setSchCategory("c.category like '" + priceVO.getStrCategory() + "%'");
			}

			priceVO.setJoinTable(" left join (select goodsno,category from gd_goods_link c group by c.goodsno) c on a.goodsno= c.goodsno");
	
			// ???????????? > ?????????
			if (!priceVO.getSword().equals("") ) {
				priceVO.setSchWord(priceVO.getSkey()+" like '%"+priceVO.getSword()+"%'");
			}

			// ???????????? > ??????????????????
			if ( !priceVO.getOpen().equals("")) {
				priceVO.setSchOpen("a.open = '"+priceVO.getOpen()+"'");
			}

			// order by
			priceVO.setOrderBy("-a.goodsno, b.link desc");
		}else if (priceVO.getIndicate().equals("main") ) {
			if ( !priceVO.getSmain().equals("") ) {
				priceVO.setSchEtc("c.mode ='"+priceVO.getSmain()+"'");
			}
			priceVO.setJoinTable(" left join gd_goods_display c on b.goodsno = c.goodsno");
	
			// order by
			priceVO.setOrderBy("c.sort, b.sno");
	
		}else if(priceVO.getIndicate().equals("event")){
			if ( !priceVO.getSevent().equals("") ) {
				priceVO.setSchEtc( " c.mode = 'e" +priceVO.getSevent() + "'");
			}
			priceVO.setJoinTable(" left join gd_goods_display c on b.goodsno = c.goodsno");
			
			priceVO.setOrderBy("c.sort, b.sno");
		}
		
//		if ( !priceVO.getIndicate().equals("")) {
			// ??????????????????
//	 		strTotalCnt =  (dbHandler.executeXmlQuery("xmall/goods_COUNT_01", new RequestSet())).get(0, "totalCount");
			
			priceVO.setRowCount(pService.getGoodsAllCount(priceVO));
			if (priceVO.getRowCount() ==0) {
				priceVO.setRowCount(0);
			}
			
			priceVO.setPageSize(20);
			int max= priceVO.getRowCount();  // ????????? ?????? ?????????
			int top = priceVO.getRowStart();  // ?????? row??????
			int[] vnum = new int[max];
			for(int i=0;i<max;i++){
				vnum[i] = max-top - i;
			}
			priceVO.setVnum(vnum);
			priceVO.setPriceList(pService.getPriceList(priceVO));
//		}
		priceVO.setTotalCnt(gService.getGoodsListTotalCount());
		logger.debug("@@ price "+priceVO.toString());
		
		return "/shop/admin/goods/price";
	}
	
	
	
	
	
	@RequestMapping(value = "price/indb")
	public String PriceIndb(@ModelAttribute("priceVO") PriceVO priceVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int[] arrSNO = priceVO.getChk();
		int tmp=0;
//		double tmp = 0.0;
		int amt=0;
		int dbAmt=0;
//		double dbAmt=0.0;
		if(arrSNO != null && arrSNO.length > 0){
			tmp = priceVO.getPmnum(); //????????????
			for(int i=0;i<arrSNO.length;i++){
//				amt = requestSet.getPropertyAsInt(requestSet.getProperty("dtprice", "") + "_amt_" + arrSNO[i]);
				req.getParameter("dtprice");// ?????????
				amt = Integer.parseInt(req.getParameter(req.getParameter("dtprice")+ "_amt_" + arrSNO[i]));		// ?????????
				
				//2017-08-21 ????????????
//				if(priceVO.getPmtype().equals("down")){
//					if(priceVO.getPmmark().equals("???") && amt < priceVO.getPmnum()){
//						
//					}
//					if(priceVO.getPmmark().equals("%")){
//						tmp = tmp -1;
//					}
//				}else{
//					if(priceVO.getPmmark().equals("%")){
//						tmp = tmp +1;
//					}
//				}
				//2017-08-21 ?????? ???
				
				if(priceVO.getPmmark().equals("%")){
					//2017-08-21 ??????
					//?????? ????????? '%'??? ??????
					if("down".equals(priceVO.getPmtype())){
						//????????? ?????? ?????? ??????
						amt = (int) (amt - (amt*(tmp/100)));
					} else {
						//????????? ?????? ?????? ??????
						amt = (int) (amt + (amt*(tmp/100)));
					}
					//2017-08-21 ?????? ???
//					amt = amt * (tmp/100);
				}else{
					if(priceVO.getPmtype().equals("down")){
						amt = (int) (amt - tmp);
					}else{
						amt = (int) (amt + tmp);
					}
				}
				
				if(priceVO.getRoundtype().equals("down")){
					dbAmt = (int) Math.floor(StringUtil.N2I(String.valueOf(amt/Integer.parseInt(priceVO.getRoundunit()) * Integer.parseInt(priceVO.getRoundunit()))));
				}else if(priceVO.getRoundtype().equals("halfup")){
					dbAmt = StringUtil.N2I(String.valueOf(amt));
//					dbAmt = this.round(dbAmt, -(priceVO.getRoundunit().length()) -1);
					//2017-08-22 ?????? ??????
					dbAmt = Math.round(dbAmt / (StringUtil.N2I(priceVO.getRoundunit())*10) ) * (StringUtil.N2I(priceVO.getRoundunit())*10);
				}else{
					dbAmt = StringUtil.N2I(String.valueOf(amt));
//					dbAmt = Math.ceil(amt / Integer.parseInt(priceVO.getRoundunit()) * Integer.parseInt(priceVO.getRoundunit()));
					//2017-08-22 ?????? ??????
					dbAmt = (int) (Math.ceil(dbAmt / (StringUtil.N2I(priceVO.getRoundunit())*10)) * (StringUtil.N2I(priceVO.getRoundunit())*10));
				}
				
				if(dbAmt<0){
					throw new BizException("goods.00002");
				}
				String priceColumn = priceVO.getTgprice() + "="+dbAmt;
				logger.debug("@@ priceIndb amt"+amt);
				logger.debug("@@ priceIndb dbAmt"+dbAmt);
				logger.debug("@@ priceIndb priceColumn"+priceColumn);
				logger.debug("@@ priceIndb amt"+amt);
				pService.setPriceColumnUpdate(arrSNO[i],priceColumn);
			}
		}
		
		
		return "redirect:/shop/admin/goods/price"+"?"+priceVO.getSearch();
	}
	
	public Double round(Double d, int decimalPlace){
	    BigDecimal bd = new BigDecimal(Double.toString(d));
	    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
	
	
}
