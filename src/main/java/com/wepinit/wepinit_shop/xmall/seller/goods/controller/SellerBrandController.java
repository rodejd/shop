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
import com.wepinit.wepinit_shop.xmall.admin.service.goods.BrandService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.CategoryService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.BrandVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.CategoryVO;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.goods.service.SellerBrandService;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerBrandVO;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsFM;
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
@RequestMapping("/shop/seller/goods")
public class SellerBrandController {

	private static final Logger logger = LoggerFactory.getLogger(SellerBrandController.class);

	@Resource
	SellerBrandService service;
	@Resource
	BrandService bservice;
	@Resource
	CategoryService cateService;
	
	
	@RequestMapping(value="/brandList")
	public String brandlist(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		logger.debug("@@brandlist ==> get");
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		SellerBrandVO brandVO =	sellerGoodsFM.getSellerBrandVO();
		brandVO.setPageNo(sellerGoodsFM.getPageNo());
		//???????????? ??????????????? ????????????
//		sellerGoodsFM.getSellerBrandVO().setSellerCd(sellerSO.getUserInfo().getSellerCd());
		brandVO.setSellerCd(sellerSO.getUserInfo().getSellerCd());
		
		//?????????
//		sellerGoodsFM.getSellerBrandVO().setRowCount(service.getSellerBrandListCount(sellerGoodsFM.getSellerBrandVO()));
		brandVO.setRowCount(service.getSellerBrandListCount(brandVO));

		// ????????????
//		sellerGoodsFM.getSellerBrandVO().setSellerBrandList(service.getSellerBrandList(sellerGoodsFM.getSellerBrandVO()));
		brandVO.setSellerBrandList(service.getSellerBrandList(brandVO));
		
		model.addAttribute("BrandVO", brandVO);
			
		return "seller/goods/brandList";
	}
	//SellerGoodsFM ???????????? admin???BrandVO ??????????????? BrandService ??? ?????????????????? ?????????
	@RequestMapping(value="/brand")
	public String brand(@ModelAttribute BrandVO brandVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		
		if(logger.isDebugEnabled()){
			logger.debug("@@ iframe.brand ");
		}
		
		//????????? ?????? ???
		brandVO.setBrandCnt(bservice.getGoodsBrandCount(brandVO));
		//????????? ??????
		brandVO.setBrandObj(bservice.getGoodsBrandInfo(brandVO.getBrandno()));
			
		//????????? ????????? ??????
		brandVO.setCategoryInfoObj(bservice.getCategoryBrandInfo(brandVO.getBrandno()));
				
		//?????? ????????? ???????????? INSERT OR UPDATE FLAG ??????
		if(brandVO.getCategoryInfoObj() != null){
			brandVO.setInfoyn("Y");
		} else {
			brandVO.setInfoyn("N");
		}
		return "seller/goods/brand";
	}
	//SellerGoodsFM ???????????? admin???BrandVO ??????????????? BrandService ??? CategoryService??? ?????????????????? ?????????
	@RequestMapping(value="brand/indb")
	public String brandindb(@ModelAttribute BrandVO brandVO , HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ brand/indb ");
		}
		//???????????? ????????? ???????????? ?????????????????? ?????????.
		brandVO.setApprovalStatus("1");
		//???????????? ??????????????? ????????????
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		brandVO.setSellerCd(sellerSO.getUserInfo().getSellerCd());
		
		if ("mod_brand".equals(brandVO.getMode())) {
			// ?????? ????????? ?????? update
			bservice.updateGoodsBrand(brandVO);

			if ("Y".equals(brandVO.getInfoyn())) {
				// ??????
				bservice.updateGoodsCategoryBrandInfo(brandVO);

			} else {
				// ??????
				CategoryVO vo = new CategoryVO();
				vo.setCategory(brandVO.getCategory());

				brandVO.setCategoryMax(cateService.getGoodsCategoryMax(vo));

				int k = 0;
				String maxCate = String.valueOf(brandVO.getCategoryMax().get(
						"maxcategory"));
				String tmp = "";
				if (brandVO.getCategoryMax().get("maxcategory") != null
						&& "".equals(brandVO.getCategoryMax()
								.get("maxcategory"))) {
					k = Integer.parseInt(maxCate.substring(
							maxCate.length() - 3, maxCate.length()));
					k += 1;
					tmp = StringUtil.lpad(String.valueOf(k), 3, '0');
					maxCate = maxCate.substring(0, maxCate.length() - 3) + tmp;
				} else {
					maxCate = brandVO.getCategory();
				}
				brandVO.setCategory(maxCate);

				bservice.insertGoodsCategoryBrandInfo(brandVO);

			}

			// ??????????????? ??????
			if (brandVO.getSub() != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("@@ brand sub ");
				}
				bservice.insertGoodsBrand(brandVO);

			}

		}
		return "redirect:/shop/seller/goods/brandList";

	}
	
}
