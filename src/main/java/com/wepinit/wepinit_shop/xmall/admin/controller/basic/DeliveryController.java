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
		
		deliveryVO.setDeliveryList(dService.getDeliveryList()); // gd_list_deliveryžóźžĄú ŽįįžÜ°žā¨,ŽįįžÜ°žā¨ url , žā¨žö©žó¨Ž∂Ä ŽĹĎžēĄžėī
		deliveryVO.setDeliveryPolicyList(dService.getDeliveryPolicyList()); // gd_delivery_policyžóźžĄú ŽĎźŽ≤ąžě¨Ž∂ÄŪĄį ž∂ĒÍįÄŪēú ŽįįžÜ°ž†ēžĪÖ Ž∂ąŽü¨žėī
		//  1 . ŽįįžÜ°Žį©Ž≤ē (ž≤ę Ž≤ąžßł)
		deliveryVO.setDeliverynm(ShopConfig.getProperty("delivery_deliverynm"));
		
		//	2 . ŽįįžÜ°Ž£Ć Ž¨īŽ£Ć Íłąžē° (ž≤ę Ž≤ąžßł)
		deliveryVO.setFree(ShopConfig.getProperty("delivery_free"));
		
		//	3 . žĄ†Ž∂ą , ŪõĄŽ∂ą (ž≤ę Ž≤ąžßł)
		deliveryVO.setDeliveryType(ShopConfig.getProperty("delivery_deliveryType"));
		
		//	4 . ŪÉĚŽįįžöĒÍłą (ž≤ę Ž≤ąžßł)
		deliveryVO.setDefault1(ShopConfig.getProperty("delivery_default"));
		
		// 	5 . ÍłįŽ≥łŽ©ĒžčúžßÄ (ž≤ę Ž≤ąžßł)
		deliveryVO.setDefaultMsg(ShopConfig.getProperty("delivery_default_msg"));
		
		// 	6 . Ž¨īŽ£ĆŽįįžÜ°žÉĀŪíą žĄ†ŪÉĚ (ž≤ę Ž≤ąžßł)
		deliveryVO.setFreeDelivery(ShopConfig.getProperty("delivery_freeDelivery"));
		
		// 	7 . žÉĀŪíąŽ≥ĄŽįįžÜ°ŽĻĄ žĄ†ŪÉĚ (ž≤ę Ž≤ąžßł)
		deliveryVO.setGoodsDelivery(ShopConfig.getProperty("delivery_goodsDelivery"));
		
		//	8 . ŪäĻžąė žßÄžó≠ ŽįįžÜ°ŽĻĄ ÍįúŽ≥Ą žßÄž†ē (ž≤ę Ž≤ąžßł)
		deliveryVO.setOver(ShopConfig.getProperty("delivery_over"));
		
		//	9 . ŪäĻžąė žßÄžó≠ (ž≤ę Ž≤ąžßł)
		deliveryVO.setOverZipcodeName(ShopConfig.getProperty("delivery_overZipcodeName"));
		

		//	1 . ÍĶ≠ÍįÄŽ≥Ą ŽįįžÜ° Íłąžē° (ž≤ę Ž≤ąžßł)
		deliveryVO.setShippingFee(ShopConfig.getProperty("delivery_shippingFee"));
		
		
		
		
		deliveryVO.setStOver(deliveryVO.getOver().split("\\|"));//ž∂ĒÍįÄŽ°ú Žď§žĖīžė® ŪäĻžąė žßÄžó≠ ŽįįžÜ°ŽĻĄ ÍįúŽ≥Ą žßÄž†ē
		deliveryVO.setStZipcodeName(deliveryVO.getOverZipcodeName().split("\\|"));//ž∂ĒÍįÄŽ°ú Žď§žĖīžė® ŪäĻžąė žßÄžó≠
		
		// žä§žúóŪäłŽěėžĽ§ API key setting
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
		   //gdConfSetÍįížĚĄ ŽĄ£ŽäĒŽč§.
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
			
			// ž£ľžĄĚž≤ėŽ¶¨ 20211002
			/*
			int i = over.length;   
			String tmpStr = "";
			for(int j=0;j<i;j++){
				tmpStr += over[j] ;
				if(j<i){tmpStr += "|";}
			}
			deliveryVO.setOver(tmpStr);// ŪäĻžąėžßÄžó≠ ŽįįžÜ°ŽĻĄ ÍĶ¨Ž∂Ąžěź | Ž°ú ŪēīžĄú confžóź ž†Äžě• 

			i = zip.length;
			tmpStr = "";
			for(int j=0;j<i;j++){  
				tmpStr += zip[j] ;
				if(j<i){tmpStr += "|";}
			}
			deliveryVO.setOverZipcodeName(tmpStr);// ŪäĻžąėžßÄžó≠ ÍĶ¨Ž∂Ąžěź | Ž°ú ŪēīžĄú confžóź ž†Äžě• 
			//}
			ShopConfig.setProperty("delivery_over", deliveryVO.getOver());
			ShopConfig.setProperty("delivery_overZipcodeName", deliveryVO.getOverZipcodeName());
			logger.debug("@@ delivery indb middle"+deliveryVO.toString());
			*/
			
			//žīąÍłįŪôĒ
			dService.initDeliveryPolicy();
			
			String[] rDelivery = deliveryVO.getrDelivery();
			String[] rFree = deliveryVO.getrFree();
			String[] rDeliType = deliveryVO.getrDeliType();
			String[] rDefault = req.getParameterValues("rDefault");
			String[] rDefaultMsg = req.getParameterValues("rDefaultMsg");
			
			// ŪÉĚŽįįžā¨ , Ž¨īŽ£Ćž£ľŽ¨łÍłąžē° , žĄ†Ž∂ąžį©Ž∂ąžó¨Ž∂Ä , ŪÉĚŽįįžöĒÍłą ,ÍłįŽ≥łŽ©ĒžĄłžßÄ 2Ž≤ąžßł Ž∂ÄŪĄį Žď§žĖīžė§ŽäĒÍįíž†Äžě• 
			if(null != deliveryVO.getrDelivery()){
				for(int s=0;s<deliveryVO.getrDelivery().length;s++){
					dService.setDeliveryPolicy((s+1),rDelivery[s],rFree[s],rDeliType[s],rDefault[s],rDefaultMsg[s]);
					logger.debug("@@ delivery indb policy"+s+" r_delivery="+rDelivery[s]+" r_free="+rFree[s]+" r_deliType="+rDeliType[s]+" r_default="+rDefault[s]+" r_defaultMsg="+(rDefaultMsg[s] !=null && !rDefaultMsg[s].equals("")?rDefaultMsg[s] :""));
				}
			}
			List<GdListDelivery> list =  dService.getDeliveryList();
			// ŪÉĚŽįįžā¨ Ž¶¨žä§Ūäł žīąÍłįŪôĒ
				for(int s =0;s<list.size();s++){
					dService.setDeliveryListUseyn(list.get(s).getDeliveryno(), "n");
					logger.debug("@@ delivery indb last tmp useyn"+list.get(s).getDeliveryno());
				}
			//žĚīžö© ŪÉĚŽįįžā¨ žĄ§ž†ē
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
	@RequestMapping(value="popup_delivery") //ŪÉĚŽįįžā¨ ŽįįžÜ° ŽßĀŪĀ¨žąėž†ē ŪĆĚžóÖ
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
	
	@RequestMapping(value="popup_delivery/indb")//ŪÉĚŽįįžā¨ ŽįįžÜ° ŽßĀŪĀ¨žąėž†ē ŪĆĚžóÖ
	public String popupIndb(@ModelAttribute("deliveryVO") DeliveryVO deliveryVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		dService.insertOrModifyGdListDelivery(deliveryVO);
		deliveryVO.setResult(1);
		if(logger.isDebugEnabled()) {
			logger.debug("@@ delviery popup indb ");
			logger.debug("@@ delivery popup indb all " + deliveryVO.toString());
		}
		
		return "/shop/admin/basic/popup_delivery";
	}
	
	// 2017-08-23 : ŪÉĚŽįįžā¨ žā≠ž†úŽ•ľ žúĄŪēėžó¨ ž∂ĒÍįÄ
	@RequestMapping(value="removeCourier.doJson")
	public List<GdListDelivery> removeCourier(DeliveryVO deliveryVO) {
		// ŪÉĚŽįįžā¨Ž•ľ žā≠ž†úŪēú ŪõĄ Ž¶¨žä§ŪäłŽ•ľ Žč§žčú ÍįÄž†łžė®Žč§.
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
