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
package com.wepinit.wepinit_shop.xmall.seller.board.controller;

import com.wepinit.wepinit_shop.xmall.seller.board.service.SellerBoardService;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerBoardFM;
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
@RequestMapping("/shop/seller/board")
public class SellerBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerBoardController.class);
	@Resource
	SellerBoardService service;
	@RequestMapping(value="/list")
	public String boardList(@ModelAttribute SellerBoardFM sellerBoardFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		return "seller/board/list";
	}

	
	/**
	 * @param sellerBoardFM
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 * 1:1문의관리 리스트
	 */
	@RequestMapping(value="/memberQna")
	public String memberQnaList(@ModelAttribute SellerBoardFM sellerBoardFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String returnUrl = service.getMemberQnaList(sellerBoardFM);
		return returnUrl;
	}

	/**
	 * @param sellerBoardFM
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 * 1:1 문의관리 수정/등록 view
	 */
	@RequestMapping(value="/memberQnaRegister")
	public String memberQnaRegister(@ModelAttribute SellerBoardFM sellerBoardFM, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String returnUrl = service.memberQnaRegister(sellerBoardFM,req);
		
		return returnUrl;
	}
	
	/**
	 * @param sellerBoardFM
	 * @param nolist
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 * 1:1문의관리 db연결
	 */
	@RequestMapping(value="/memberQnaIndb")
	public String memberQnaRegisterPost(@ModelAttribute SellerBoardFM sellerBoardFM ,String nolist, HttpServletRequest req, HttpServletResponse res) throws Exception {
		//POPUP CLOSE를 위한 FLAG
//		sellerBoardFM.getSellerMemberQnaVO().setResult("1");
		sellerBoardFM.setResult("1");

		String returnUrl = service.memberQnaRegisterPost(sellerBoardFM,req,nolist);
		
		return returnUrl;
	}
	/**
	 * @param sellerBoardFM
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 * 상품문의관리 리스트
	 */
	@RequestMapping(value="/goodsQna")
	public String goodsQnaList(@ModelAttribute SellerBoardFM sellerBoardFM , HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String returnUrl = service.getgoodsQnaList(sellerBoardFM);
		
		return returnUrl;
	}
	
	@RequestMapping(value="/goodsQnaRegister")
	public String goodsQnaRegister(@ModelAttribute SellerBoardFM sellerBoardFM , HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String returnUrl = service.goodsQnaRegister(sellerBoardFM,req);
		
		return returnUrl;
	}
	@RequestMapping(value="/goodsQnaIndb")
	public String goodsQnaIndb(@ModelAttribute SellerBoardFM sellerBoardFM , String nolist,HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		sellerBoardFM.setResult("1");

		String returnUrl = service.goodsQnaIndb(sellerBoardFM , req , nolist);
		
		return returnUrl;
	}
	@RequestMapping(value="/goodsReview")
	public String goodsReview(@ModelAttribute SellerBoardFM sellerBoardFM , HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String returnUrl = service.goodsReviewList(sellerBoardFM);

		return returnUrl;
	}
	@RequestMapping(value="/goodsReviewRegister")
	public String goodsReviewRegister(@ModelAttribute SellerBoardFM sellerBoardFM , HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String returnUrl = service.goodsReviewRegister(sellerBoardFM);
		
		return returnUrl;
	}
	@RequestMapping(value="/goodsReviewIndb")
	public String goodsReviewIndb(@ModelAttribute SellerBoardFM sellerBoardFM ,String nolist, HttpServletRequest req, HttpServletResponse res) throws Exception {

		sellerBoardFM.setResult("1");

		String returnUrl = service.goodsReviewIndb(sellerBoardFM,req,nolist);
						
		return returnUrl;
	}
}
