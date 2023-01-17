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

import com.wepinit.wepinit_shop.xmall.seller.goods.service.SellerGoodsService;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
@RequestMapping("/shop/seller/goods")
public class SellerGoodsController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerGoodsController.class);
	
	@Resource
	SellerGoodsService sellerGoodsService;
	
	/**
	 * 판매사별 상품 리스트 화면
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String goodsList(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("uclee contoeller >>>>>>> popView : {} ", sellerGoodsFM.getPopView());
		}
		
		sellerGoodsService.getGoodsList(sellerGoodsFM);
		
		return "seller/goods/list";
	}
	
	/**
	 * 판매사별 등록신청대기 목록
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/registerApprovalList")
	public String goodsRegisterApprovalList(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.getRegisterApprovalList(sellerGoodsFM);
		
		
		return "seller/goods/list";
	}
	
	/**
	 * 요청상품 등록신청
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param mhsq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/registerApprovalReq.dojson")  
	public String goodsRegisterApprovalReq(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.setRegApprovalReq(sellerGoodsFM);
		
		return "dojson";
	}
	
	/**
	 * 판매사별 수정신청대기 목록
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifyApprovalList")
	public String goodsModifyApprovalList(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.getModifyApprovalList(sellerGoodsFM);
		
		return "seller/goods/list";
	}
	
	/**
	 * 요청상품 수정신청
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param mhsq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifyApprovalReq")
	public String goodsModifyApprovalReq(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res, MultipartHttpServletRequest mhsq) throws Exception {
		
		sellerGoodsService.setModApprovalReq(sellerGoodsFM, req, mhsq);
		
		return "redirect:/shop/seller/goods/modifyApprovalList";
	}
	
	/**
	 * 요청상품 삭제신청
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param mhsq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteApprovalReq.dojson")
	public String goodsDeleteApprovalReq(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.setDelApprovalReq(sellerGoodsFM);
		
		return "dojson";
	}
	
	/**
	 * 판매사별 삭제신청대기 목록
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteApprovalList")
	public String goodsDeleteApprovalList(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.getDeleteApprovalList(sellerGoodsFM);
		
		return "seller/goods/list";
	}
	
	/**
	 * 상품 목록 iframe
	 * @param goodsVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/iframeList")
	public String goodsIframeList(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.getGoodsIframeList(sellerGoodsFM);
		
		return "seller/goods/iframeList";
	}
	
	/**
	 * 상품 등록/수정 화면
	 * @param goodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/register")
	public String goodsRegister(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.getGoodsRegister(sellerGoodsFM);
		String[][] imgsArray			= new String[][]{{"l", ""}, {"i", ""}, {"s", ""}, {"m", ""}};	// 상품 이미지
		// 상품이미지 저장
		if((sellerGoodsFM.getSellerGoodsViewVO())!=null){
			//2017-08-24 수정 - 이미지등록 순서 변경
			imgsArray[0][1] = sellerGoodsFM.getSellerGoodsViewVO().getImgl();
			imgsArray[1][1] = sellerGoodsFM.getSellerGoodsViewVO().getImgi();
			imgsArray[2][1] = sellerGoodsFM.getSellerGoodsViewVO().getImgs();
			imgsArray[3][1] = sellerGoodsFM.getSellerGoodsViewVO().getImgm();	
			logger.debug("sellerGoodsFM.getSellerGoodsViewVO().getImgm() ", sellerGoodsFM.getSellerGoodsViewVO().getImgm());
		}
		sellerGoodsFM.setImgsArray(imgsArray);
		
		
		return "seller/goods/register";
	}
	
	
	/**
	 * 상품 등록/수정 프로세스
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param mhsq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/indb")
	public String goodsProc(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res, MultipartHttpServletRequest mhsq) throws Exception {
		
		sellerGoodsService.goodsDB(sellerGoodsFM, req, mhsq);
		
		return "redirect:/shop/seller/goods/registerApprovalList";
	}
	
	/**
	 * 상품요청 복사
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param mhsq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sellerGoodsCopy")
	public String goodsSellerGoodsCopy(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.setRegSellerGoodsCopy(sellerGoodsFM);
		
		return "redirect:/shop/seller/goods/registerApprovalList";
	}
	
	/**
	 * 요청상품 삭제
	 * @param sellerGoodsFM
	 * @param req
	 * @param res
	 * @param mhsq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sellerGoodsDel")
	public String goodsSellerGoodsDel(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.setDelSellerGoodsDel(sellerGoodsFM);
		
		return "redirect:/shop/seller/goods/registerApprovalList";
	}
	
	
	/**
	 * 판매사별 상품고시 등록
	 * @param goodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notiRegister.dojson")
	public String goodsNotiRegister(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.setSellerGoodsNoti(sellerGoodsFM);
		
		return "dojson";
	}
	
	/**
	 * 판매사별 상품고시 조회
	 * @param goodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notiList.dojson")
	public String goodsNotiList(@ModelAttribute SellerGoodsFM sellerGoodsFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerGoodsService.getSellerGoodsNoti(sellerGoodsFM);
				
		return "dojson";
	}
}
