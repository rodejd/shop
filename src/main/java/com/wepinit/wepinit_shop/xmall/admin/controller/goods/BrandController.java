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

import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.BrandService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.CategoryService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.BrandVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.CategoryVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value="/shop/admin/goods/*")
public class BrandController {
	
	private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	@Resource
	BrandService service;
	
	@Resource
	CategoryService cateService;
	
	@RequestMapping(value="brand")
	public String brand(@ModelAttribute("brandVO") BrandVO brandVO, Model model) throws Exception{
		//????????????????????? ????????? ?????? ?????????????????? ??????????????? ????????? ???????????????
		if(null!=brandVO.getSellerCd()){
			model.addAttribute("selector",1);
		}
		brandVO.setBrandList(service.getGoodsBrandList(brandVO));
	
		return "/shop/admin/goods/brand";
	}
	
	@RequestMapping(value="iframe.brand")
	public String iframeBrand(@ModelAttribute("brandVO")BrandVO brandVO ,Model model) throws Exception{
		//????????? ?????? ???
		brandVO.setBrandCnt(service.getGoodsBrandCount(brandVO));
		//????????? ??????
		brandVO.setBrandObj(service.getGoodsBrandInfo(brandVO.getBrandno()));
			
		//????????? ????????? ??????
		brandVO.setCategoryInfoObj(service.getCategoryBrandInfo(brandVO.getBrandno()));
				
		//?????? ????????? ???????????? INSERT OR UPDATE FLAG ??????
		if(brandVO.getCategoryInfoObj() != null){
			brandVO.setInfoyn("Y");
		} else {
			brandVO.setInfoyn("N");
		}
		
		return "/shop/admin/goods/iframe.brand";
	}
	
	@RequestMapping(value="brand/indb")
	public String brandIndb(@ModelAttribute("brandVO")BrandVO brandVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		String returnUrl = "";
		//????????? ??????
		if( StringUtils.isBlank(brandVO.getApprovalStatus()) ){
			brandVO.setApprovalStatus("2");
		}
		
		AwsFileUtil awsfileUtil = new AwsFileUtil();
		String pcImg = brandVO.getOldImgPC();
		String moImg = brandVO.getOldImgMO();
		
		String awsKey = "";
		if( brandVO.getBrandImgPC() != null) {
			if( !brandVO.getBrandImgPC().isEmpty() && FileUtil.getChkAwsFile(pcImg) ){
				awsKey = "brand/" + String.valueOf(brandVO.getSno()) + "/" + FileUtil.getUrlFileName(pcImg);
				// S3 ????????????
				awsfileUtil.delete(awsKey);
			}
		}
		if( brandVO.getBrandImgMO() != null) {
			if( !brandVO.getBrandImgMO().isEmpty() && FileUtil.getChkAwsFile(moImg) ){
				awsKey = "brand/" + String.valueOf(brandVO.getSno()) + "/" + FileUtil.getUrlFileName(moImg);
				// S3 ????????????
				awsfileUtil.delete(awsKey);
			}
		}
		
		//??????&??????
		if("mod_brand".equals(brandVO.getMode())){
			//?????? ????????? ?????? update
			service.updateGoodsBrand(brandVO);
			
			if("Y".equals(brandVO.getInfoyn())){
				//??????
				service.updateGoodsCategoryBrandInfo(brandVO);
				
			}else{
				//??????
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
			
			//??????????????? ??????
			if(brandVO.getSub() != null){
				service.insertGoodsBrand(brandVO);
			}
			
			//????????? ??????
			if(brandVO.getSno() != 0) {
				//PC ????????? ??????
				if(!brandVO.getBrandImgPC().isEmpty()){
					// AWS ???????????????
					String awsPath = "brand/" + String.valueOf(brandVO.getSno()) + "/";
					pcImg = FileUtil.awsUploadFile(brandVO.getBrandImgPC().getOriginalFilename(), brandVO.getBrandImgPC().getBytes(), awsPath);
				}
				
				//MO ????????? ??????
				if(!brandVO.getBrandImgMO().isEmpty()){
					// AWS ???????????????
					String awsPath = "brand/" + String.valueOf(brandVO.getSno()) + "/";
					moImg = FileUtil.awsUploadFile(brandVO.getBrandImgMO().getOriginalFilename(), brandVO.getBrandImgMO().getBytes(), awsPath);
				}
			}
			
			BrandVO updateImgVO = new BrandVO();
			updateImgVO.setImgPC(pcImg);
			updateImgVO.setImgMO(moImg);
			updateImgVO.setSno(brandVO.getSno());
			service.updateBrandByImage(updateImgVO);
			
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/goods/iframe.brand";
			
		//??????
		} else if("del_brand".equals(brandVO.getMode())) {
			service.deleteGoodsBrand(brandVO.getSno());
			
			awsKey = "brand/" + String.valueOf(brandVO.getSno());
			// S3 ????????????
			awsfileUtil.deleteList(awsKey);
			
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/goods/iframe.brand";
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value="popup.delBrand")
	public String deleteBrand(@ModelAttribute("brandVO")BrandVO brandVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ popup delete brand");
		}
		
		//???????????? ????????????
		brandVO.setBrandObj(service.getGoodsBrandInfo(brandVO.getBrandno()));
		//???????????? ??????????????? ????????????
		brandVO.setBrandCnt(service.getGoodsBrandCount(brandVO));
		
		return "/shop/admin/goods/popup.delBrand";
	}
	
	/**
	 * ????????? ????????????
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="brand/brandCount.dojson")
	public String brandCount(Model model) throws Exception{
		
		GdGoodsBrand gdGoodsBrand = service.getGoodsBrandCountList();
		model.addAttribute("gdGoodsBrand", gdGoodsBrand);
		
		return "dojson";
	}

}
