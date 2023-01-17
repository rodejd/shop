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
package com.wepinit.wepinit_shop.xmall.admin.controller.seller;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.BrandService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.CategoryService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.BrandVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.CategoryVO;
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
@RequestMapping("/shop/admin/seller/*")
public class AdminSellerBrandController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerBrandController.class);
	
	@Resource
	BrandService service;
	
	@Resource
	CategoryService cateService;
	
	@RequestMapping(value="goodsBrand")
	public String sellerBrand(@ModelAttribute("brandVO") BrandVO brandVO, Model model) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ brand ");
		}
		//판매자관리에서 들어올 경우 프렌드페이지 리스트부분 꾸미기 보여지않게
		if(null!=brandVO.getSellerCd()){
			model.addAttribute("selector",1);
		}
		brandVO.setBrandList(service.getGoodsBrandList(brandVO));
	
		return "/shop/admin/seller/goodsBrand";
	}
	
	@RequestMapping(value="iframe.sellerBrand")
	public String iframeBrand(@ModelAttribute("brandVO")BrandVO brandVO ,Model model) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ iframe.sellerBrand ");
		}
		
		//브랜드 상품 수
		brandVO.setBrandCnt(service.getGoodsBrandCount(brandVO));
		//브랜드 정보
		brandVO.setBrandObj(service.getGoodsBrandInfo(brandVO.getBrandno()));
			
		//브랜드 페이지 정보
		brandVO.setCategoryInfoObj(service.getCategoryBrandInfo(brandVO.getBrandno()));
				
		//상품 리스트 레이아웃 INSERT OR UPDATE FLAG 세팅
		if(brandVO.getCategoryInfoObj() != null){
			brandVO.setInfoyn("Y");
		} else {
			brandVO.setInfoyn("N");
		}
		
		return "/shop/admin/seller/goodsIframeBrand";
	}
	
	@RequestMapping(value="sellerBrand/indb")
	public String brandIndb(@ModelAttribute("brandVO")BrandVO brandVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
//		if(logger.isDebugEnabled()){
//			logger.debug("@@ brand indb >> "+brandVO.getMode()+" >> "+brandVO.getBrandno()+" >> "+brandVO.getInfoyn());
//		}
		
		String returnUrl = "";
		//브랜드 판매자 승인상태가 승인/반려 아닐때 승인요청
		if("".equals(brandVO.getApprovalStatus())||null==brandVO.getApprovalStatus()){
			brandVO.setApprovalStatus("1");
		}
		//브랜드 판매자 코드가 입력되지 않을시 승인으로 이동
		if("".equals(brandVO.getSellerCd())||null==brandVO.getSellerCd()){
			brandVO.setApprovalStatus("2");
		}
		
		
		if("mod_brand".equals(brandVO.getMode())){
			//현재 브랜드 정보 update
			service.updateGoodsBrand(brandVO);
			
			if("Y".equals(brandVO.getInfoyn())){
				//수정
				service.updateGoodsCategoryBrandInfo(brandVO);
				
			}else{
				//등록
				CategoryVO vo = new CategoryVO();
				vo.setCategory(brandVO.getCategory());
				
				brandVO.setCategoryMax(cateService.getGoodsCategoryMax(vo));
				
				int k = 0;
				String maxCate = String.valueOf(brandVO.getCategoryMax().get("maxcategory"));
				String tmp = "";
				if(brandVO.getCategoryMax().get("maxcategory") != null && "".equals(brandVO.getCategoryMax().get("maxcategory"))){
					k = Integer.parseInt(maxCate.substring(maxCate.length()-3, maxCate.length()));
					k += 1;
					tmp	= StringUtil.lpad(String.valueOf(k), 3, '0');
					maxCate = maxCate.substring(0, maxCate.length()-3) + tmp;
				} else {
					maxCate = brandVO.getCategory();
				}
				brandVO.setCategory(maxCate);
				
				service.insertGoodsCategoryBrandInfo(brandVO);
				
			}
			
			//하위브랜드 추가
			if(brandVO.getSub() != null){
				if(logger.isDebugEnabled()){
					logger.debug("@@ brand sub ");
				}
				service.insertGoodsBrand(brandVO);
				
			}
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/seller/goodsIframeBrand";
			
		} else if("del_brand".equals(brandVO.getMode())) {
			service.deleteGoodsBrand(brandVO.getBrandno());
			
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/seller/goodsIframeBrand";
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value="popup.sellerDelBrand")
	public String deleteBrand(@ModelAttribute("brandVO")BrandVO brandVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ popup delete brand");
		}
		
		//브랜드명 가져오기
		brandVO.setBrandObj(service.getGoodsBrandInfo(brandVO.getBrandno()));
		//브랜드별 관련상품수 가져오기
		brandVO.setBrandCnt(service.getGoodsBrandCount(brandVO));
		
		return "/shop/admin/seller/goodsPopupDelBrand";
	}

}
