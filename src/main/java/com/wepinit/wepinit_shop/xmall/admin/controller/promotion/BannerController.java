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
package com.wepinit.wepinit_shop.xmall.admin.controller.promotion;

import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.promotion.BannerService;

import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.BannerVO;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBanner;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/shop/admin/promotion/*")
public class BannerController {

	@Resource
	BannerService service;
	
	/**
	 * 리스트 페이지
	 * @param bannerVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "banner/list")
	public String banner_list(@ModelAttribute("bannerVO") BannerVO bannerVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//총건수
		bannerVO.setTotalCnt(service.getBannerTotalCount());
		
		//검색 총건수 조회
		bannerVO.setRowCount(service.getBannerCount(bannerVO));
		
		if (bannerVO.getRowCount() > 0) {
			bannerVO.setBannerList(service.getBannerList(bannerVO));
		}
		
		return "/shop/admin/promotion/banner";
	}
	
	/**
	 * 등록 & 수정 페이지
	 * @param bannerVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="banner/register")
	public String banner_register(@ModelAttribute("bannerVO") BannerVO bannerVO, 
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		//수정 페이지
		if (bannerVO.getMode().equals("modify")) {			
			bannerVO.setBannerObj(service.getBannerView(bannerVO.getSno()));
			return "/shop/admin/promotion/banner_register";
		//등록 페이지	
		}else if(bannerVO.getMode().equals("register")) {
			return "/shop/admin/promotion/banner_register";
		}else {
			return "redirect:/shop/admin/promotion/banner/list";
		}
	}
	
	/**
	 * DB처리
	 * @param bannerVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="banner/indb")
	public String mainBannerRegisterModify(@ModelAttribute("bannerVO") BannerVO bannerVO) throws Exception {
		AwsFileUtil awsfileUtil = new AwsFileUtil();
		String awsKey = "";
		String img = "";
		String imgMobile = "";
				
		//삭제
		if (StringUtils.equals("delete", bannerVO.getMode())){
			service.deleteBanner(bannerVO.getSno());
			
			// S3 파일삭제
			awsKey = "banner/" + String.valueOf(bannerVO.getSno());
			awsfileUtil.deleteList(awsKey);
			
			return "redirect:/shop/admin/promotion/banner/list";
		} else {
			//등록
			if( StringUtils.equals("register", bannerVO.getMode()) ) {
				bannerVO.setRegid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
				service.insertBanner(bannerVO);
				
			//수정
			}else if( StringUtils.equals("modify", bannerVO.getMode()) ) {
				GdBanner bannerInfo = service.getBannerView(bannerVO.getSno());
				
				bannerVO.setModid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
				service.updateBanner(bannerVO);
				
				//이미지 삭제
				if( bannerVO.getImgFile() != null) {
					if( !bannerVO.getImgFile().isEmpty() && FileUtil.getChkAwsFile(bannerInfo.getImg()) ){
						awsKey = "banner/" + String.valueOf(bannerVO.getSno()) + "/" + FileUtil.getUrlFileName(bannerInfo.getImg());
						// S3 파일삭제
						awsfileUtil.delete(awsKey);
					}
				}
				
				if( bannerVO.getImgMobileFile() != null) {
					if( !bannerVO.getImgMobileFile().isEmpty() && FileUtil.getChkAwsFile(bannerInfo.getImgMobile()) ){
						awsKey = "banner/" + String.valueOf(bannerVO.getSno()) + "/" + FileUtil.getUrlFileName(bannerInfo.getImgMobile());
						// S3 파일삭제
						awsfileUtil.delete(awsKey);
					}
				}
				
				//이미지 변경이 아닐경우 기존이미지로 값설정
				if( StringUtils.isBlank(img) ) img = bannerInfo.getImg();
				if( StringUtils.isBlank(imgMobile) ) imgMobile = bannerInfo.getImgMobile();
			}
			
			//PC 이미지
			if(!bannerVO.getImgFile().isEmpty()){
				// AWS 파일업로드
				String awsPath = "banner/" + String.valueOf(bannerVO.getSno()) + "/";
				img = FileUtil.awsUploadFile(bannerVO.getImgFile().getOriginalFilename(), bannerVO.getImgFile().getBytes(), awsPath);
			}
			
			//MO 이미지
			if(!bannerVO.getImgMobileFile().isEmpty()){
				// AWS 파일업로드
				String awsPath = "banner/" + String.valueOf(bannerVO.getSno()) + "/";
				imgMobile = FileUtil.awsUploadFile(bannerVO.getImgMobileFile().getOriginalFilename(), bannerVO.getImgMobileFile().getBytes(), awsPath);
			}
				
			BannerVO updateImgVO = new BannerVO();
			updateImgVO.setSno(bannerVO.getSno());
			updateImgVO.setImg(img);
			updateImgVO.setImgMobile(imgMobile);
			service.updateBannerByImage(updateImgVO);
			
			return "redirect:/shop/admin/promotion/banner/list?schSkin=" + bannerVO.getSchSkin() + "&schLoccd=" + bannerVO.getSchLoccd() + "&schKey=" + bannerVO.getSchKey() + "&schWord=" + bannerVO.getSchWord() + "&schUsed=" + bannerVO.getSchUsed() + "&schSort=" + bannerVO.getSchSort() + "&pageNo=" + bannerVO.getPageNo();
		}
	}
}
