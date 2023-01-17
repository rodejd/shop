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
import com.wepinit.wepinit_shop.xmall.admin.service.board.GoodsQnaService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.GoodsQnaVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="shop/admin/board/goods_qna/*")
public class GoodsQnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsQnaController.class);
	
	@Resource
	GoodsQnaService service;
	
	/*
	 * 게시판관리
	 * 상품문의관리 리스트
	 */
	@RequestMapping(value="list")
	public String GoodsQna(@ModelAttribute("goodsQnaVO") GoodsQnaVO goodsQnaVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@goods qna ==> get");
		}
		
		
		List<String> goodsNoList = new ArrayList<String>();		//검색 상품번호 반환 리스트
		String[] goodsNoArr = null;
		List<String> goodsParentList = new ArrayList<String>();		//검색 상품 부모글 번호 반환 리스트
		String[] goodsParentArr = null;
		
		String cateVal = "";		//카테고리 VALUE
		
		String subtable = "";		//검색 분류 서브테이블 쿼리문
		String schcate = "";		//검색 분류 카테고리 쿼리
		String schparent = "";		//검색 부모글 쿼리문
		
		//총 건수
		goodsQnaVO.setTotalCount(service.getGoodsQnaTotalCount());

		/* >>>>>>>>>>> dynamic query >>>>>>>>>> */
		if(goodsQnaVO.getCate() != null) {
			if(goodsQnaVO.getCate().length > 0){
				for(int i=0; i<goodsQnaVO.getCate().length; i++){
					if(!"".equals(StringUtil.nullConv(goodsQnaVO.getCate()[i], ""))){
						cateVal = goodsQnaVO.getCate()[i];
					}
				}
				if(!"".equals(cateVal)){
					subtable = "Y";
					schcate = cateVal;
				}
				goodsQnaVO.setCateVal(cateVal);
			}
		}
		
		if(!"".equals(goodsQnaVO.getSkey()) && !"".equals(goodsQnaVO.getSword())
				&& goodsQnaVO.getSkey() != null && goodsQnaVO.getSword() != null ){
			//상품검색시 상품번호 반환
			if("goodnm".equals(goodsQnaVO.getSkey()) || "all".equals(goodsQnaVO.getSword()) ){
				goodsNoList = service.getGoodsQnaGoodsNo(goodsQnaVO.getSword());
				if(goodsNoList.size()>0){
					goodsNoArr = new String[goodsNoList.size()];
					for(int i=0; i<goodsNoList.size(); i++){
						goodsNoArr[i] = goodsNoList.get(i);
					}
					goodsQnaVO.setSchword("a.goodsno in("+StringUtil.implode(",", goodsNoArr)+ ")");
				} else {
					goodsQnaVO.setSchword("0");
				}
			}
		}
		
		//부모글 번호 반환
		goodsParentList = service.getGoodsQnaGoodsParent(goodsQnaVO, schcate, subtable);
		
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
		goodsQnaVO.setRowCount(service.getGoodsQnaGoodsSearchCount(schparent));
		goodsQnaVO.setGoodsQnaList(service.getGoodsQnaGoodsSearchList(goodsQnaVO, schparent));
		
		return "/shop/admin/board/goods_qna";
	}
	
	/*
	 * 게시판관리
	 * 상품문의관리 수정/답변 페이지
	 */
	@RequestMapping(value="register")
	public String GoodsQnaRegister(@ModelAttribute("goodsQnaVO")GoodsQnaVO goodsQnaVO,
			HttpServletRequest req) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@goods qna register >> ");
		}
		
		if("modify".equals(goodsQnaVO.getMode()) || "reply".equals(goodsQnaVO.getMode()) ) {
			//수정/답변 페이지
			goodsQnaVO.setGoodsQnaObj(service.getGoodsQnaInfo(goodsQnaVO.getSno()));
			
			if("reply".equals(goodsQnaVO.getMode())){
				Date date = new Date();
				goodsQnaVO.setRegdt(date);
				goodsQnaVO.setIp(req.getRemoteAddr());
				
				goodsQnaVO.setAdminMemList(service.getGoodsQnaAdminMemberList());
			}
		}
		return "/shop/admin/board/goods_qna_register";
	}
	
	/*
	 *게시판관리
	 *상품문의관리 수정/답변 처리 
	 */
	@RequestMapping(value="indb")
	public String GoodsQnaIndb(@ModelAttribute("goodsQnaVO")GoodsQnaVO goodsQnaVO,
			HttpServletRequest req, Model model) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@goods qna indb");
		}
		
		String returnUrl = "";
		
		if("delete".equals(goodsQnaVO.getMode())){
			//데이터 삭제
			String[] deleteNum = goodsQnaVO.getNolist().split(";");
			for(int i=0; i<deleteNum.length; i++){
				service.deleteGoodsQna(Integer.parseInt(deleteNum[i]));
			}
			returnUrl = "redirect:/shop/admin/board/goods_qna/list";
		} else if("modify".equals(goodsQnaVO.getMode())) {
			//데이터 수정
			service.updateGoodsQna(goodsQnaVO);
			
			//POPUP CLOSE를 위한 FLAG
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/board/goods_qna_register";			
		} else if("reply".equals(goodsQnaVO.getMode())) {
			//데이터 답글
			goodsQnaVO.setIp(req.getRemoteAddr());
			service.insertGoodsQnaReply(goodsQnaVO);
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/board/goods_qna_register";	
		} else {
			throw new BizException("goods.00001");
		}
		
		return returnUrl;
	}

}
