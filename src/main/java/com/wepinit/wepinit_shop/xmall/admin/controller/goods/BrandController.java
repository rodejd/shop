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
		//판매자관리에서 들어올 경우 프렌드페이지 리스트부분 꾸미기 보여지않게
		if(null!=brandVO.getSellerCd()){
			model.addAttribute("selector",1);
		}
		brandVO.setBrandList(service.getGoodsBrandList(brandVO));
	
		return "/shop/admin/goods/brand";
	}
	
	@RequestMapping(value="iframe.brand")
	public String iframeBrand(@ModelAttribute("brandVO")BrandVO brandVO ,Model model) throws Exception{
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
		
		return "/shop/admin/goods/iframe.brand";
	}
	
	@RequestMapping(value="brand/indb")
	public String brandIndb(@ModelAttribute("brandVO")BrandVO brandVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		String returnUrl = "";
		//기본값 승인
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
				// S3 파일삭제
				awsfileUtil.delete(awsKey);
			}
		}
		if( brandVO.getBrandImgMO() != null) {
			if( !brandVO.getBrandImgMO().isEmpty() && FileUtil.getChkAwsFile(moImg) ){
				awsKey = "brand/" + String.valueOf(brandVO.getSno()) + "/" + FileUtil.getUrlFileName(moImg);
				// S3 파일삭제
				awsfileUtil.delete(awsKey);
			}
		}
		
		//등록&수정
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
				service.insertGoodsBrand(brandVO);
			}
			
			//이미지 등록
			if(brandVO.getSno() != 0) {
				//PC 브랜드 배너
				if(!brandVO.getBrandImgPC().isEmpty()){
					// AWS 파일업로드
					String awsPath = "brand/" + String.valueOf(brandVO.getSno()) + "/";
					pcImg = FileUtil.awsUploadFile(brandVO.getBrandImgPC().getOriginalFilename(), brandVO.getBrandImgPC().getBytes(), awsPath);
				}
				
				//MO 브랜드 배너
				if(!brandVO.getBrandImgMO().isEmpty()){
					// AWS 파일업로드
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
			
		//삭제
		} else if("del_brand".equals(brandVO.getMode())) {
			service.deleteGoodsBrand(brandVO.getSno());
			
			awsKey = "brand/" + String.valueOf(brandVO.getSno());
			// S3 폴더삭제
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
		
		//브랜드명 가져오기
		brandVO.setBrandObj(service.getGoodsBrandInfo(brandVO.getBrandno()));
		//브랜드별 관련상품수 가져오기
		brandVO.setBrandCnt(service.getGoodsBrandCount(brandVO));
		
		return "/shop/admin/goods/popup.delBrand";
	}
	
	/**
	 * 브랜드 건수조회
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
