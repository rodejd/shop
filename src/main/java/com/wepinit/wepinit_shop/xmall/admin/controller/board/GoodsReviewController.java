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

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.board.GoodsReviewService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.GoodsReviewVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="shop/admin/board/goods_review/*")
public class GoodsReviewController {
	
	@Resource
	GoodsReviewService service;
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsReviewController.class);
	
	/*
	 * 게시판관리
	 * 상품평관리 리스트
	 */
	@RequestMapping(value="list")
	public String GoodsReview(@ModelAttribute("goodsRevwVO") GoodsReviewVO goodsRevwVO) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@GoodsReview ");
		}
		
		List<String> goodsNoList = new ArrayList<String>();		//검색 상품번호 반환 리스트
		String[] goodsNoArr = null;
		List<String> goodsParentList = new ArrayList<String>();		//검색 상품 부모글 번호 반환 리스트
		String[] goodsParentArr = null;
		

		String subtable = "";	//검색 분류 서브테이블 쿼리문
		String schcate = "";	//검색 분류 카테고리 쿼리문
		String schparent = ""; //검색 부모글 쿼리문
		
		String cateVal = "";
		
		//총 건수
		goodsRevwVO.setTotalCount(service.getGoodsReviewTotalCount(goodsRevwVO));
		
		/* >>>>>>>>>>> dynamic query >>>>>>>>>> */
		if(goodsRevwVO.getCate() != null) {
			if(goodsRevwVO.getCate().length > 0) {
				for(int i=0; i<goodsRevwVO.getCate().length; i++){
					if(!"".equals(StringUtil.nullConv(goodsRevwVO.getCate()[i], ""))){
						cateVal = goodsRevwVO.getCate()[i];
					}
				}
				if(!"".equals(cateVal)){
					subtable = " left join gd_goods_link c on a.goodsno = c.goodsno";
					schcate = "category like '"+cateVal+"%'";
				}
			}
			if(logger.isDebugEnabled()) {
				logger.debug("cate ==> " + cateVal);
			}
			goodsRevwVO.setCateVal(cateVal);
		}
		
		if(!"".equals(goodsRevwVO.getSkey()) && !"".equals(goodsRevwVO.getSword()) 
				&& goodsRevwVO.getSkey() != null && goodsRevwVO.getSword() != null) {
			//상품명검색시 상품번호 반환
			if("goodnm".equals(goodsRevwVO.getSkey()) || "all".equals(goodsRevwVO.getSkey()) ) {
				goodsNoList = service.getGoodsReviewGoodsNo(goodsRevwVO.getSword());
				if(goodsNoList.size()>0){
					goodsNoArr = new String[goodsNoList.size()];
					for(int i=0; i<goodsNoList.size(); i++){
						goodsNoArr[i] = goodsNoList.get(i);
					}
					goodsRevwVO.setSchword("a.goodsno in("+StringUtil.implode(",", goodsNoArr)+ ")");
				} else {
					goodsRevwVO.setSchword("0");
				}
			}
		}

		//부모글 번호 반환
		goodsParentList = service.getGoodsReviewGoodsParent(goodsRevwVO, schcate, subtable);
		
		if(goodsParentList.size() > 0){
			goodsParentArr = new String[goodsParentList.size()];
			for(int i=0; i<goodsParentList.size(); i++){
				goodsParentArr[i] = goodsParentList.get(i);
			}
			schparent = "parent in ('" + StringUtil.implode("','", goodsParentArr) +"')";
		} else {
			schparent = "0";
		}
		
		//검색 총 건수
		goodsRevwVO.setRowCount(service.getGoodsReviewSearchCount(goodsRevwVO, schparent));
		goodsRevwVO.setGoodsRevwList(service.getGoodsReviewSearchList(goodsRevwVO, schparent));
		
		return "/shop/admin/board/goods_review";
	}
	
	/*
	 * 게시판괸리
	 * 상품평관리 등록/수정 페이지
	 */
	@RequestMapping(value="register")
	public String GoodsReviewRegister(@ModelAttribute("goodsRevwVO")GoodsReviewVO goodsRevwVO,
			HttpServletRequest req) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@GoodsRegister ==> get");
		}
		
		if("modify".equals(goodsRevwVO.getMode()) || "reply".equals(goodsRevwVO.getMode()) ){
			//수정/답변 화면
			
			goodsRevwVO.setGoodsRevwObj(service.getGoodsReviewInfo(goodsRevwVO.getSno()));
			goodsRevwVO.setGoodsObj(service.getGoodsReviewGoodsInfo(goodsRevwVO.getGoodsRevwObj().getGoodsno()));
			goodsRevwVO.setMemberList(service.getGoodsReviewAuthMember());
			
			if(goodsRevwVO.getGoodsRevwObj().getMno() != 0){
				//회원일 경우
				goodsRevwVO.setMemberObj(service.getGoodsReviewMemberInfo(goodsRevwVO.getGoodsRevwObj().getMno()));
			} else {
				//비회원일 경우
				if(!"".equals(goodsRevwVO.getGoodsRevwObj().getName()) && goodsRevwVO.getGoodsRevwObj().getName() != null) {
					goodsRevwVO.getMemberObj().setMid(goodsRevwVO.getGoodsRevwObj().getName());
				}
			}
			
//			if("reply".equals(goodsRevwVO.getMode())){
//				//답변 화면
//			}
		}
		
		return "/shop/admin/board/goods_review_register";
	}
	
	/*
	 * 게시판관리
	 * 상품평관리 등록/수정 처리
	 */
	@RequestMapping(value="indb")
	public String GoodsReviewIndb(@ModelAttribute("goodsRevwVO")GoodsReviewVO goodsRevwVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@GoodsRegister ==> post");
		}
		String returnUrl ="";
		
		if("delete".equals(goodsRevwVO.getMode())){
			//데이터 삭제
			String[] deleteNum = goodsRevwVO.getNolist().split(";");
			for(int i=0; i<deleteNum.length; i++){
				service.deleteGoodsReview(Integer.parseInt(deleteNum[i]));
			}
			returnUrl = "redirect:/shop/admin/board/goods_review/list?btype=" + goodsRevwVO.getBtype();
			
		}else if("modify".equals(goodsRevwVO.getMode())){
			//데이터 수정
			service.updateGoodsReview(goodsRevwVO);
			
			//POPUP CLOSE를 위한 FLAG
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/board/goods_review_register";
		}else if("reply".equals(goodsRevwVO.getMode())){
			//데이터 답글
			goodsRevwVO.setIp(req.getRemoteAddr());
			service.insertGoodsReviewReply(goodsRevwVO);
			
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/board/goods_review_register";
		}else{
			throw new BizException("goods.00001");
		}
		
		return returnUrl;
	}

}
