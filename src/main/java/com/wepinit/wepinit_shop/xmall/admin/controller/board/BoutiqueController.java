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
package com.wepinit.wepinit_shop.xmall.admin.controller.board;

import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.board.BoutiqueService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.BoutiqueVO;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBoutique;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/shop/admin/board/*")
public class BoutiqueController {

	@Resource
	BoutiqueService service;
	
	/**
	 * 리스트 페이지
	 * @param boutiqueVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "boutique/list")
	public String boutique_list(@ModelAttribute("boutiqueVO") BoutiqueVO boutiqueVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//총건수
		boutiqueVO.setTotalCnt(service.getBoutiqueTotalCount());
		
		//검색 총건수 조회
		boutiqueVO.setRowCount(service.getBoutiqueCount(boutiqueVO));
		
		if (boutiqueVO.getRowCount() > 0) {
			boutiqueVO.setBoutiqueList(service.getBoutiqueList(boutiqueVO));
		}
		
		return "/shop/admin/board/boutique";
	}
	
	/**
	 * 등록 & 수정 페이지
	 * @param boutiqueVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="boutique/register")
	public String boutique_register(@ModelAttribute("boutiqueVO") BoutiqueVO boutiqueVO, 
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		//수정 페이지
		if (boutiqueVO.getMode().equals("modify")) {			
			boutiqueVO.setBoutiqueObj(service.getBoutiqueView(boutiqueVO.getSno()));
			return "/shop/admin/board/boutique_register";
		//등록 페이지	
		}else if(boutiqueVO.getMode().equals("register")) {
			return "/shop/admin/board/boutique_register";
		}else {
			return "redirect:/shop/admin/board/boutique/list";
		}
	}
	
	/**
	 * DB처리
	 * @param boutiqueVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="boutique/indb")
	public String mainBoutiqueRegisterModify(@ModelAttribute("boutiqueVO") BoutiqueVO boutiqueVO) throws Exception {
		AwsFileUtil awsfileUtil = new AwsFileUtil();
		String awsKey = "";
		String img = "";
		String imgm = "";
				
		//삭제
		if (StringUtils.equals("delete", boutiqueVO.getMode())){
			service.deleteBoutique(boutiqueVO.getSno());
			
			// S3 파일삭제
			awsKey = "boutique/" + String.valueOf(boutiqueVO.getSno());
			awsfileUtil.deleteList(awsKey);
			
			return "redirect:/shop/admin/board/boutique/list";
		} else {
			//등록
			if( StringUtils.equals("register", boutiqueVO.getMode()) ) {
				boutiqueVO.setRegid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
				service.insertBoutique(boutiqueVO);
				
			//수정
			}else if( StringUtils.equals("modify", boutiqueVO.getMode()) ) {
				GdBoutique boutiqueInfo = service.getBoutiqueView(boutiqueVO.getSno());
				
				boutiqueVO.setModid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
				service.updateBoutique(boutiqueVO);
				
				//이미지 삭제
				if( boutiqueVO.getImgFile() != null) {
					if( !boutiqueVO.getImgFile().isEmpty() && FileUtil.getChkAwsFile(boutiqueInfo.getImg()) ){
						awsKey = "boutique/" + String.valueOf(boutiqueVO.getSno()) + "/" + FileUtil.getUrlFileName(boutiqueInfo.getImg());
						// S3 파일삭제
						awsfileUtil.delete(awsKey);
					}
				}
				
				if( boutiqueVO.getImgmFile() != null) {
					if( !boutiqueVO.getImgmFile().isEmpty() && FileUtil.getChkAwsFile(boutiqueInfo.getImgm()) ){
						awsKey = "boutique/" + String.valueOf(boutiqueVO.getSno()) + "/" + FileUtil.getUrlFileName(boutiqueInfo.getImgm());
						// S3 파일삭제
						awsfileUtil.delete(awsKey);
					}
				}
				
				//이미지 변경이 아닐경우 기존이미지로 값설정
				if( StringUtils.isBlank(img) ) img = boutiqueInfo.getImg();
				if( StringUtils.isBlank(imgm) ) imgm = boutiqueInfo.getImgm();
			}
			
			//PC 이미지
			if(!boutiqueVO.getImgFile().isEmpty()){
				// AWS 파일업로드
				String awsPath = "boutique/" + String.valueOf(boutiqueVO.getSno()) + "/";
				img = FileUtil.awsUploadFile(boutiqueVO.getImgFile().getOriginalFilename(), boutiqueVO.getImgFile().getBytes(), awsPath);
			}
			
			//MO 이미지
			if(!boutiqueVO.getImgmFile().isEmpty()){
				// AWS 파일업로드
				String awsPath = "boutique/" + String.valueOf(boutiqueVO.getSno()) + "/";
				imgm = FileUtil.awsUploadFile(boutiqueVO.getImgmFile().getOriginalFilename(), boutiqueVO.getImgmFile().getBytes(), awsPath);
			}
				
			BoutiqueVO updateImgVO = new BoutiqueVO();
			updateImgVO.setSno(boutiqueVO.getSno());
			updateImgVO.setImg(img);
			updateImgVO.setImgm(imgm);
			service.updateBoutiqueByImage(updateImgVO);
			
			return "redirect:/shop/admin/board/boutique/list?schSkin=" + boutiqueVO.getSchSkin() + "&schKey=" + boutiqueVO.getSchKey() + "&schWord=" + boutiqueVO.getSchWord() + "&schUsed=" + boutiqueVO.getSchUsed() + "&schSort=" + boutiqueVO.getSchSort() + "&pageNo=" + boutiqueVO.getPageNo();
		}
	}
}
