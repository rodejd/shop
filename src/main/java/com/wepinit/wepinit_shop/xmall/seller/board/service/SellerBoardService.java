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
package com.wepinit.wepinit_shop.xmall.seller.board.service;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.seller.board.dao.SellerMemberQnaMapper;
import com.wepinit.wepinit_shop.xmall.common.AmazonSES;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberQna;
import com.wepinit.wepinit_shop.xmall.seller.board.dao.SellerMemberQnaMapper;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerBoardFM;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerGoodsQnaVO;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerGoodsReviewVO;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerMemberQnaVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class SellerBoardService {

	@Resource
	SellerMemberQnaMapper mapper;


	public String getMemberQnaList(SellerBoardFM sellerBoardFM) {
		// TODO Auto-generated method stub
		SellerSessionObject sellerSessionObject = SellerSessionObject.getSessionObject();
		String sellerCd = sellerSessionObject.getUserInfo().getSellerCd();
		
		SellerMemberQnaVO memQnaVO = sellerBoardFM.getSellerMemberQnaVO();
		
		memQnaVO.setSellerCd(sellerCd);

		memQnaVO.setTotalCnt(mapper.getMemberQnaTotalCount(memQnaVO));
		//게시판 검색 총건수
		memQnaVO.setRowCount(mapper.getMemberQnaCount(memQnaVO));
		memQnaVO.setMemQnaList(mapper.getMemberQnaList(memQnaVO));
		
		return "seller/board/memberQna";
	}


	public String memberQnaRegister(SellerBoardFM sellerBoardFM, HttpServletRequest req) {
		// TODO Auto-generated method stub
		SellerSessionObject sellerSessionObject = SellerSessionObject.getSessionObject();
		String sellerCd = sellerSessionObject.getUserInfo().getSellerCd();
		String sellerNm = sellerSessionObject.getUserInfo().getUserName();
		
		
		SellerMemberQnaVO memQnaVO = sellerBoardFM.getSellerMemberQnaVO();
		memQnaVO.setSellerNm(sellerNm);		//판매자이름세팅
		
		if("modify".equals(sellerBoardFM.getSellerMemberQnaVO().getMode()) || "reply".equals(sellerBoardFM.getSellerMemberQnaVO().getMode()) ){
			memQnaVO.setMemQnaObj(mapper.getMemberQnaInfo(memQnaVO.getSno()));
			
			if("reply".equals(memQnaVO.getMode())){
				memQnaVO.setIp(req.getRemoteAddr());
				Date date = new Date();
				
				memQnaVO.setRegdt(date);
				
				memQnaVO.setMemberList(mapper.getMemberInfo());
			}
			
			if(memQnaVO.getMemQnaObj().getParent() != memQnaVO.getMemQnaObj().getSno() ){
				memQnaVO.setFormType("reply");	//입력항목제어
				memQnaVO.setMemQnaPrtObj(mapper.getMemberQnaParentInfo(memQnaVO.getMemQnaObj().getParent()));
			}
		}
		return "seller/board/memberQnaRegister";
	}


	public String memberQnaRegisterPost(SellerBoardFM sellerBoardFM, HttpServletRequest req , String nolist) throws Exception {
		// TODO Auto-generated method stub
		SellerSessionObject sellerSessionObject = SellerSessionObject.getSessionObject();
		String sellerCd = sellerSessionObject.getUserInfo().getSellerCd();
		String sellerNm = sellerSessionObject.getUserInfo().getUserName();
		
		
		SellerMemberQnaVO memQnaVO = sellerBoardFM.getSellerMemberQnaVO();
		memQnaVO.setSellerNm(sellerNm);		//판매자이름세팅
		memQnaVO.setSellerCd(sellerCd);		//판매자코드세팅
		memQnaVO.setName(sellerNm);			//게시한판매자이름
		System.out.println("memQnaVO.getMode()"+memQnaVO.getMode());
		if("delete".equals(memQnaVO.getMode())){
			//데이터 삭제
			String[] deleteNume = nolist.split(";");
			for(int i=0; i < deleteNume.length; i++){
				mapper.deleteMemberQna(Integer.parseInt(deleteNume[i]));

			}
			return "redirect:/shop/seller/board/memberQna";

		}else if("modify".equals(memQnaVO.getMode())){
			//데이터 수정
			memQnaVO.setIp(req.getRemoteAddr());
			mapper.updateMemberQna(memQnaVO);
			return "/shop/seller/board/memberQnaRegister";

			//POPUP CLOSE를 위한 FLAG
//			model.addAttribute("result", 1);
		}else if("reply".equals(memQnaVO.getMode())){
			//데이터 답글
			memQnaVO.setIp(req.getRemoteAddr());
			mapper.insertMemberQnaReply(memQnaVO);
//			//POPUP CLOSE를 위한 FLAG
//			model.addAttribute("result", 1);
			SellerSessionObject sellerSession = new SellerSessionObject();
			
			//메일발송  
			AmazonSES mailConfig = new AmazonSES();
			Map<String,Object> mailMap = new HashMap<String,Object>();
			GdMemberQna gmq = mapper.getMemberQnaInfo(memQnaVO.getSno());
			mailMap.put("adminEmail", sellerSession.getUserInfo().getXkey().get("managerEmail"));
			mailMap.put("name", memQnaVO.getName());
			mailMap.put("questiontitle", gmq.getSubject());
			mailMap.put("question", gmq.getContents());
			mailMap.put("answertitle", memQnaVO.getSubject());
			mailMap.put("answer", memQnaVO.getContents());
			//수신자이메일 , mode 메일종류 , mailMap  
			mailConfig.mailSender(memQnaVO.getEmail(), "20", mailMap);
			
			return "/shop/seller/board/memberQnaRegister";

		}else{
			throw new BizException("goods.00001");
		}
	}


	public String getgoodsQnaList(SellerBoardFM sellerBoardFM) {
		// TODO Auto-generated method stub
		SellerGoodsQnaVO goodsQnaVO = sellerBoardFM.getSellerGoodsQnaVO();
		SellerSessionObject sellerSessionObject = SellerSessionObject.getSessionObject();
		String sellerCd = sellerSessionObject.getUserInfo().getSellerCd();
		
		// 자신의 글이 아니라면 수정할수 없게할 구분자
		sellerBoardFM.setSellerNm(sellerSessionObject.getUserInfo().getUserName());
		
		List<String> goodsNoList = new ArrayList<String>();		//검색 상품번호 반환 리스트
		String[] goodsNoArr = null;
		List<String> goodsParentList = new ArrayList<String>();		//검색 상품 부모글 번호 반환 리스트
		String[] goodsParentArr = null;
		
		String cateVal = "";		//카테고리 VALUE
		
		String subtable = "";		//검색 분류 서브테이블 쿼리문
		String schcate = "";		//검색 분류 카테고리 쿼리
		String schparent = "";		//검색 부모글 쿼리문
		
		//총 건수
		goodsQnaVO.setSellerCd(sellerCd);
		goodsQnaVO.setTotalCount(mapper.getGoodsQnaTotalCount(goodsQnaVO));

		/* >>>>>>>>>>> dynamic query >>>>>>>>>> */
		if(goodsQnaVO.getCate() != null) {
			if(goodsQnaVO.getCate().length > 0){
				for(int i=0; i<goodsQnaVO.getCate().length; i++){
					if(!"".equals(StringUtil.nullConv(goodsQnaVO.getCate()[i], ""))){
						cateVal = goodsQnaVO.getCate()[i];
					}
				}
				if(!"".equals(cateVal)){
					subtable = "left join gd_goods_link d on a.goodsno=d.goodsno";
					schcate = "category like '"+cateVal+"%'";
				}
				goodsQnaVO.setCateVal(cateVal);
			}
		}
		
		if(!"".equals(goodsQnaVO.getSkey()) && !"".equals(goodsQnaVO.getSword())
				&& goodsQnaVO.getSkey() != null && goodsQnaVO.getSword() != null ){
			//상품검색시 상품번호 반환
			if("goodnm".equals(goodsQnaVO.getSkey()) || "all".equals(goodsQnaVO.getSword()) ){
				goodsNoList = mapper.getGoodsQnaGoodsNo(goodsQnaVO);
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
		goodsQnaVO.setSchcate(schcate);
		goodsQnaVO.setSubtable(subtable);
		//부모글 번호 반환
		System.out.println("schcateschcate"+schcate);
		System.out.println("subtablesubtable"+subtable);

		goodsParentList = mapper.getGoodsQnaGoodsParent(goodsQnaVO);
		
		if(goodsParentList.size() > 0){
			goodsParentArr = new String[goodsParentList.size()];
			for(int i=0; i<goodsParentList.size(); i++){
				goodsParentArr[i] = goodsParentList.get(i);
			}
			schparent = "parent in ('" + StringUtil.implode("','", goodsParentArr) +"')";
		} else {
			schparent = "0";
		}
		
		goodsQnaVO.setSchparent(schparent);
		//검색 총 건수
		goodsQnaVO.setRowCount(mapper.getGoodsQnaGoodsSearchCount(goodsQnaVO));
		goodsQnaVO.setGoodsQnaList(mapper.getGoodsQnaGoodsSearchList(goodsQnaVO));
		
		return "seller/board/goodsQna";
	}


	public String goodsQnaRegister(SellerBoardFM sellerBoardFM , HttpServletRequest req) {
		// TODO Auto-generated method stub
		SellerGoodsQnaVO goodsQnaVO = sellerBoardFM.getSellerGoodsQnaVO();
		SellerSessionObject sellerSessionObject = SellerSessionObject.getSessionObject();

		
		if("modify".equals(goodsQnaVO.getMode()) || "reply".equals(goodsQnaVO.getMode()) ) {
			//수정/답변 페이지
			goodsQnaVO.setGoodsQnaObj(mapper.getGoodsQnaInfo(goodsQnaVO.getSno()));
			
			if("reply".equals(goodsQnaVO.getMode())){
				String name = sellerSessionObject.getUserInfo().getUserName();	// 답변등록자 이름 세션에서 판매자이름받아온다
				Date date = new Date();
				goodsQnaVO.setRegdt(date);
				goodsQnaVO.setIp(req.getRemoteAddr());
				goodsQnaVO.setName(name);
			}
		}
		return "seller/board/goodsQnaRegister";
	}


	public String goodsQnaIndb(SellerBoardFM sellerBoardFM ,HttpServletRequest req ,String nolist) {
		// TODO Auto-generated method stub
		SellerGoodsQnaVO goodsQnaVO = sellerBoardFM.getSellerGoodsQnaVO();
		
		if("delete".equals(goodsQnaVO.getMode())){
			//데이터 삭제
			String[] deleteNum = nolist.split(";");
			for(int i=0; i<deleteNum.length; i++){
				mapper.deleteGoodsQna(Integer.parseInt(deleteNum[i]));
			}
			return  "redirect:/shop/seller/board/goodsQna";
		} else if("modify".equals(goodsQnaVO.getMode())) {
			//데이터 수정
			mapper.updateGoodsQna(goodsQnaVO);
			
			//POPUP CLOSE를 위한 FLAG
//			model.addAttribute("result", 1);
			return  "seller/board/goodsQnaRegister";			
		} else if("reply".equals(goodsQnaVO.getMode())) {
			//데이터 답글
			goodsQnaVO.setIp(req.getRemoteAddr());
			mapper.insertGoodsQnaReply(goodsQnaVO);
//			model.addAttribute("result", 1);
			return  "seller/board/goodsQnaRegister";	
		} else {
			throw new BizException("goods.00001");
		}
		
//		return "seller/board/goodsQnaRegister";
	}


	public String goodsReviewList(SellerBoardFM sellerBoardFM) {
		
		SellerGoodsReviewVO goodsRevwVO=sellerBoardFM.getSellerGoodsReviewVO();
		SellerSessionObject sellerSessionObject = SellerSessionObject.getSessionObject();
		String sellerCd=sellerSessionObject.getUserInfo().getSellerCd();
		
		goodsRevwVO.setSellerCd(sellerCd);						//판매자 코드 
		
		List<String> goodsNoList = new ArrayList<String>();		//검색 상품번호 반환 리스트
		String[] goodsNoArr = null;
		List<String> goodsParentList = new ArrayList<String>();		//검색 상품 부모글 번호 반환 리스트
		String[] goodsParentArr = null;
		
		String subtable = "";	//검색 분류 서브테이블 쿼리문
		String schcate = "";	//검색 분류 카테고리 쿼리문
		String schparent = "";  //검색 부모글 쿼리문
		
		String cateVal = "";
		
		//총 건수
		goodsRevwVO.setTotalCount(mapper.getGoodsReviewTotalCount(sellerCd));

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
			
			goodsRevwVO.setCateVal(cateVal);
		}
		
		if(!"".equals(goodsRevwVO.getSkey()) && !"".equals(goodsRevwVO.getSword()) 
				&& goodsRevwVO.getSkey() != null && goodsRevwVO.getSword() != null) {
			//상품명검색시 상품번호 반환
			if("goodnm".equals(goodsRevwVO.getSkey()) || "all".equals(goodsRevwVO.getSkey()) ) {
				goodsNoList = mapper.getGoodsReviewGoodsNo(goodsRevwVO);
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
		goodsRevwVO.setSubtable(subtable);
		goodsRevwVO.setSchcate(schcate);
		//부모글 번호 반환
		goodsParentList = mapper.getGoodsReviewGoodsParent(goodsRevwVO);
		
		if(goodsParentList.size() > 0){
			goodsParentArr = new String[goodsParentList.size()];
			for(int i=0; i<goodsParentList.size(); i++){
				goodsParentArr[i] = goodsParentList.get(i);
			}
			schparent = "parent in ('" + StringUtil.implode("','", goodsParentArr) +"')";
		} else {
			schparent = "0";
		}
		goodsRevwVO.setSchparent(schparent);
		//검색 총 건수
		goodsRevwVO.setRowCount(mapper.getGoodsReviewSearchCount(goodsRevwVO));
		goodsRevwVO.setGoodsRevwList(mapper.getGoodsReviewSearchList(goodsRevwVO));
		
		return "seller/board/goodsReview";
	}


	public String goodsReviewRegister(SellerBoardFM sellerBoardFM) {
		// TODO Auto-generated method stub
		SellerGoodsReviewVO goodsRevwVO=sellerBoardFM.getSellerGoodsReviewVO();
		
		if("modify".equals(goodsRevwVO.getMode()) || "reply".equals(goodsRevwVO.getMode()) ){
			//수정/답변 화면
			
			goodsRevwVO.setGoodsRevwObj(mapper.getGoodsReviewInfo(goodsRevwVO.getSno()));
			goodsRevwVO.setGoodsObj(mapper.getGoodsReviewGoodsInfo(goodsRevwVO.getGoodsRevwObj().getGoodsno()));
			goodsRevwVO.setMemberList(mapper.getGoodsReviewAuthMember());
			
			if(goodsRevwVO.getGoodsRevwObj().getMno() != 0){
				//회원일 경우
				goodsRevwVO.setMemberObj(mapper.getGoodsReviewMemberInfo(goodsRevwVO.getGoodsRevwObj().getMno()));
			} else {
				//비회원일 경우
				if(!"".equals(goodsRevwVO.getGoodsRevwObj().getName()) && goodsRevwVO.getGoodsRevwObj().getName() != null) {
					goodsRevwVO.getMemberObj().setMid(goodsRevwVO.getGoodsRevwObj().getName());
				}
			}
					
		}
		return "seller/board/goodsReviewRegister";

	}


	public String goodsReviewIndb(SellerBoardFM sellerBoardFM,HttpServletRequest req ,String nolist) {
		SellerGoodsReviewVO goodsRevwVO=sellerBoardFM.getSellerGoodsReviewVO();
		
		String returnUrl ="";
		
		if("delete".equals(goodsRevwVO.getMode())){
			//데이터 삭제
			String[] deleteNum = nolist.split(";");
			for(int i=0; i<deleteNum.length; i++){
				mapper.deleteGoodsReview(Integer.parseInt(deleteNum[i]));
			}
			returnUrl = "redirect:/shop/seller/board/goodsReview";
			
		}else if("modify".equals(goodsRevwVO.getMode())){
			//데이터 수정
			mapper.updateGoodsReview(goodsRevwVO);
			
			//POPUP CLOSE를 위한 FLAG
//			model.addAttribute("result", 1);
			returnUrl = "seller/board/goodsReviewRegister";
		}else if("reply".equals(goodsRevwVO.getMode())){
			//데이터 답글
			goodsRevwVO.setIp(req.getRemoteAddr());
			mapper.insertGoodsReviewReply(goodsRevwVO);
			
//			model.addAttribute("result", 1);
			returnUrl = "seller/board/goodsReviewRegister";
		}else{
			throw new BizException("goods.00001");
		}
		
		return returnUrl;
	}
	
}
