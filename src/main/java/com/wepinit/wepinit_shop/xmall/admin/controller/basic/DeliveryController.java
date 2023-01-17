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

import com.wepinit.wepinit_shop.xmall.admin.service.basic.DeliveryService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.DeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class DeliveryController {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	
	@Resource
	DeliveryService dService;
	
	@RequestMapping(value="delivery")
	public String deliveryManage(@ModelAttribute("deliveryVO") DeliveryVO deliveryVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		deliveryVO.setDeliveryList(dService.getDeliveryList()); // gd_list_delivery에서 배송사,배송사 url , 사용여부 뽑아옴
		deliveryVO.setDeliveryPolicyList(dService.getDeliveryPolicyList()); // gd_delivery_policy에서 두번재부터 추가한 배송정책 불러옴
		//  1 . 배송방법 (첫 번째)
		deliveryVO.setDeliverynm(ShopConfig.getProperty("delivery_deliverynm"));
		
		//	2 . 배송료 무료 금액 (첫 번째)
		deliveryVO.setFree(ShopConfig.getProperty("delivery_free"));
		
		//	3 . 선불 , 후불 (첫 번째)
		deliveryVO.setDeliveryType(ShopConfig.getProperty("delivery_deliveryType"));
		
		//	4 . 택배요금 (첫 번째)
		deliveryVO.setDefault1(ShopConfig.getProperty("delivery_default"));
		
		// 	5 . 기본메시지 (첫 번째)
		deliveryVO.setDefaultMsg(ShopConfig.getProperty("delivery_default_msg"));
		
		// 	6 . 무료배송상품 선택 (첫 번째)
		deliveryVO.setFreeDelivery(ShopConfig.getProperty("delivery_freeDelivery"));
		
		// 	7 . 상품별배송비 선택 (첫 번째)
		deliveryVO.setGoodsDelivery(ShopConfig.getProperty("delivery_goodsDelivery"));
		
		//	8 . 특수 지역 배송비 개별 지정 (첫 번째)
		deliveryVO.setOver(ShopConfig.getProperty("delivery_over"));
		
		//	9 . 특수 지역 (첫 번째)
		deliveryVO.setOverZipcodeName(ShopConfig.getProperty("delivery_overZipcodeName"));
		

		//	1 . 국가별 배송 금액 (첫 번째)
		deliveryVO.setShippingFee(ShopConfig.getProperty("delivery_shippingFee"));
		
		
		
		
		deliveryVO.setStOver(deliveryVO.getOver().split("\\|"));//추가로 들어온 특수 지역 배송비 개별 지정
		deliveryVO.setStZipcodeName(deliveryVO.getOverZipcodeName().split("\\|"));//추가로 들어온 특수 지역
		
		// 스윗트래커 API key setting
		deliveryVO.setSweetTrackerAPIKey(ShopConfig.getProperty("sweetTrackerAPIKey"));
		
		if(logger.isDebugEnabled()) {
			logger.debug("@@ delviery ");
			logger.debug("@@ delivery all " + deliveryVO.toString());
			/*logger.debug("@@ delivery list " + deliveryVO.getDeliveryList().get(0));
			logger.debug("@@ delivery policy list " + deliveryVO.getDeliveryPolicyList().get(0));*/
		}
		
		model.addAttribute("deliveryVO", deliveryVO);
		model.addAttribute("deliveryList", deliveryVO.getDeliveryList());
		model.addAttribute("deliveryPolicyList", deliveryVO.getDeliveryPolicyList());
	

		
		return "/shop/admin/basic/delivery";
	}
	@RequestMapping(value="delivery/indb")
	public String deliveryIndb(@ModelAttribute("deliveryVO") DeliveryVO deliveryVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		   //gdConfSet값을 넣는다.
			logger.debug("@@ delivery indb");
			logger.debug("@@ delivery indb"+deliveryVO.toString());
			if(deliveryVO.getDeliverynm() !=null && !deliveryVO.getDeliverynm().equals(""))
				ShopConfig.setProperty("delivery_deliverynm", deliveryVO.getDeliverynm());
			if(deliveryVO.getFree() !=null && !deliveryVO.getFree().equals(""))
				ShopConfig.setProperty("delivery_free", deliveryVO.getFree());
			if(deliveryVO.getDeliveryType() !=null && !deliveryVO.getDeliveryType().equals(""))
				ShopConfig.setProperty("delivery_deliveryType", deliveryVO.getDeliveryType());
			if(deliveryVO.getDefault1() !=null && !deliveryVO.getDefault1().equals(""))
				ShopConfig.setProperty("delivery_default", deliveryVO.getDefault1());
			if(deliveryVO.getDefaultMsg() !=null && !deliveryVO.getDefaultMsg().equals(""))
				ShopConfig.setProperty("delivery_default_msg", deliveryVO.getDefaultMsg());
			if(deliveryVO.getFreeDelivery() !=null && !deliveryVO.getFreeDelivery().equals(""))
				ShopConfig.setProperty("delivery_freeDelivery", deliveryVO.getFreeDelivery());
			if(deliveryVO.getGoodsDelivery() !=null && !deliveryVO.getGoodsDelivery().equals(""))
				ShopConfig.setProperty("delivery_goodsDelivery", deliveryVO.getGoodsDelivery());
			if(deliveryVO.getShippingFee() !=null && !deliveryVO.getShippingFee().equals(""))
				ShopConfig.setProperty("delivery_shippingFee", deliveryVO.getShippingFee());
		    
			ShopConfig.setProperty("sweetTrackerAPIKey", deliveryVO.getSweetTrackerAPIKey());
			
			// mergeStringArrayValues(String spliter , String[] strArray){
			String[] over = deliveryVO.getStOver();
			String[] zip = deliveryVO.getStZipcodeName();
			
			// 주석처리 20211002
			/*
			int i = over.length;   
			String tmpStr = "";
			for(int j=0;j<i;j++){
				tmpStr += over[j] ;
				if(j<i){tmpStr += "|";}
			}
			deliveryVO.setOver(tmpStr);// 특수지역 배송비 구분자 | 로 해서 conf에 저장 

			i = zip.length;
			tmpStr = "";
			for(int j=0;j<i;j++){  
				tmpStr += zip[j] ;
				if(j<i){tmpStr += "|";}
			}
			deliveryVO.setOverZipcodeName(tmpStr);// 특수지역 구분자 | 로 해서 conf에 저장 
			//}
			ShopConfig.setProperty("delivery_over", deliveryVO.getOver());
			ShopConfig.setProperty("delivery_overZipcodeName", deliveryVO.getOverZipcodeName());
			logger.debug("@@ delivery indb middle"+deliveryVO.toString());
			*/
			
			//초기화
			dService.initDeliveryPolicy();
			
			String[] rDelivery = deliveryVO.getrDelivery();
			String[] rFree = deliveryVO.getrFree();
			String[] rDeliType = deliveryVO.getrDeliType();
			String[] rDefault = req.getParameterValues("rDefault");
			String[] rDefaultMsg = req.getParameterValues("rDefaultMsg");
			
			// 택배사 , 무료주문금액 , 선불착불여부 , 택배요금 ,기본메세지 2번째 부터 들어오는값저장 
			if(null != deliveryVO.getrDelivery()){
				for(int s=0;s<deliveryVO.getrDelivery().length;s++){
					dService.setDeliveryPolicy((s+1),rDelivery[s],rFree[s],rDeliType[s],rDefault[s],rDefaultMsg[s]);
					logger.debug("@@ delivery indb policy"+s+" r_delivery="+rDelivery[s]+" r_free="+rFree[s]+" r_deliType="+rDeliType[s]+" r_default="+rDefault[s]+" r_defaultMsg="+(rDefaultMsg[s] !=null && !rDefaultMsg[s].equals("")?rDefaultMsg[s] :""));
				}
			}
			List<GdListDelivery> list =  dService.getDeliveryList();
			// 택배사 리스트 초기화
				for(int s =0;s<list.size();s++){
					dService.setDeliveryListUseyn(list.get(s).getDeliveryno(), "n");
					logger.debug("@@ delivery indb last tmp useyn"+list.get(s).getDeliveryno());
				}
			//이용 택배사 설정
			if(deliveryVO.getDelivery() !=null && deliveryVO.getDelivery().length >0 ){
				for(String s: deliveryVO.getDelivery()){
					dService.setDeliveryListUseyn(s,"y");
					logger.debug("@@ delivery indb last useyn"+s);
				}
			}
			
			if(logger.isDebugEnabled()) {
				logger.debug("@@ delviery indb ");
				logger.debug("@@ delivery indb last all " + deliveryVO.getDeliveryno());
				logger.debug("@@ delivery indb list " + deliveryVO.getDeliveryList());
				logger.debug("@@ delivery indb policy list " + deliveryVO.getDeliveryPolicyList());
			}
		return "redirect:/shop/admin/basic/delivery";
	}
	@RequestMapping(value="popup_delivery") //택배사 배송 링크수정 팝업
	public String popupDelivery(@ModelAttribute("deliveryVO") DeliveryVO deliveryVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
			
			deliveryVO.setDeliveryList(dService.getPopupDeliveryList(deliveryVO.getDeliveryno()));
		
		if(logger.isDebugEnabled()) {
			logger.debug("@@ delviery popup ");
			logger.debug("@@ delivery popup no " + deliveryVO.getDeliveryno());
			logger.debug("@@ delivery popup mode " + deliveryVO.getMode());
		}
		model.addAttribute("deliveryVO",deliveryVO);
		model.addAttribute("deliveryList", (deliveryVO.getDeliveryList() !=null && deliveryVO.getDeliveryList().size() >0 ? deliveryVO.getDeliveryList().get(0):""));
		return "/shop/admin/basic/popup_delivery";
	}
	
	@RequestMapping(value="popup_delivery/indb")//택배사 배송 링크수정 팝업
	public String popupIndb(@ModelAttribute("deliveryVO") DeliveryVO deliveryVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		dService.insertOrModifyGdListDelivery(deliveryVO);
		deliveryVO.setResult(1);
		if(logger.isDebugEnabled()) {
			logger.debug("@@ delviery popup indb ");
			logger.debug("@@ delivery popup indb all " + deliveryVO.toString());
		}
		
		return "/shop/admin/basic/popup_delivery";
	}
	
	// 2017-08-23 : 택배사 삭제를 위하여 추가
	@RequestMapping(value="removeCourier.doJson")
	public List<GdListDelivery> removeCourier(DeliveryVO deliveryVO) {
		// 택배사를 삭제한 후 리스트를 다시 가져온다.
		dService.deleteCourier(deliveryVO.getDeliveryno());
		List<GdListDelivery> courierList =  dService.getDeliveryList();

		return courierList;
	}

	@RequestMapping({"deliverynoCheck"})
	@ResponseBody
	public int deliverynoCheck(@RequestParam("deliveryno") String deliveryno) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("@@ delviery deliverynoCheck ");
			logger.debug("@@ delivery deliverynoCheck " + deliveryno);
		} 
    
		return this.dService.deliverynoCheck(deliveryno);
	}	
	
}
