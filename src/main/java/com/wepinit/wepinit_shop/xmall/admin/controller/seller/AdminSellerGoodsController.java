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
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.admin.service.seller.AdminSellerGoodsService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.BrandVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsAdd;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/seller/*")
public class AdminSellerGoodsController {

	private static final Logger logger = LoggerFactory.getLogger(AdminSellerGoodsController.class);
	
	@Resource
	AdminSellerGoodsService serviceB;
	
	@Resource
	GoodsService service;
	
	/*
	 * 판매자 상품관리
	 * 상품 목록
	 */
	@RequestMapping(value="goodsList")
	public String goodsList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		goodsVO.setSellerYn("Y"); //판매사여부
		
		// 상품  총 건수 조회
		goodsVO.setTotalCnt(service.getGoodsListTotalCount());
		
		//상품 검색 총건수 조회
		goodsVO.setRowCount(service.getGoodsListCount(goodsVO));
		
		if(0 < goodsVO.getRowCount()){
			goodsVO.setGoodsList(service.getGoodsList(goodsVO));
		}
		
		return "/shop/admin/seller/goodsList";
	}
	
	//상품 목록
	@RequestMapping(value="goodsIframeList")
	public String goodsIframeList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("@@@list goodsVO::"+goodsVO);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		//게시판 총 건수
		goodsVO.setTotalCnt(service.getGoodsListTotalCount());
		//게시판 검색 총건수
		goodsVO.setRowCount(service.getGoodsListCount(goodsVO));		
		logger.debug("@@@list goodsVO.getRowCount()::"+goodsVO.getRowCount());
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, goodsVO.getPageSize());		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, goodsVO.getRowStart());		
		goodsVO.setGoodsList(service.getGoodsList(goodsVO));
		
		return "/shop/admin/seller/_goodslist";
	}
	
	@RequestMapping(value="goodsIndb")
	public String goodsIndb(HttpServletRequest req, HttpServletResponse res,MultipartHttpServletRequest mhsq,  Model model) throws Exception {
		service.goodsDB(req,mhsq);
		return "redirect:/shop/admin/seller/goodsList";
	}
	
	@RequestMapping(value="goodsIndb2")
	public String goodsIndb2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		HashMap<String, Object> paramsDb = null; // DB처리를 위한 map
		String mode = "";
		
		paramsDb = new HashMap<String, Object>();
		mode = req.getParameter("mode");
		
		if( "open_modify".equals(mode) ){
			
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("goodsArr", req.getParameterValues("goodsArr"));
			
			service.updateOpen(paramsDb);
			
		}else if( "delete".equals(mode) ){
			
			paramsDb.put("goodsno", req.getParameter("goodsno"));
			
			service.deleteGoods(paramsDb);
			
		}else if( "copyGoods".equals(mode) ){
			
			paramsDb.put("goodsno", req.getParameter("goodsno"));
			
			service.copyGoods(paramsDb);
			
		} else if( "approvalstatus_modify".equals(mode) ){
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("goodsArr", req.getParameterValues("goodsArr"));
			paramsDb.put("sellercodeArr", req.getParameterValues("sellercodeArr"));
			
			service.updateApproval(paramsDb);
		}
		
		return "redirect:/shop/admin/seller/goodsList";
	}
	
	//수정 등록 일 경우
	@SuppressWarnings("unused")
	@RequestMapping(value="goodsRegister")
	public String goodsRegister(@ModelAttribute("goodsVO") GoodsVO goodsVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		if (null == goodsVO.getMode()) {
			goodsVO.setMode("register");
		}
		
		String strHieen = "";	// 화면에 모드별로 출력여부를 결정
		String[][] imgsArray = new String[][]{{"l", ""}, {"i", ""}, {"s", ""}, {"m", ""}};	// 상품 이미지
		
		//원산지목록
		goodsVO.setOriginList(service.getOriginList());
		
		//브랜드목록
		goodsVO.setGoodsBrandList(service.getBrandList());
		
		// 상품 정보 가져오기			
		goodsVO.setGoodsObj(service.getGoodsInfo(goodsVO.getGoodsno()));
		if (goodsVO.getMode().equals("modify")) {
			//카테고리 목록 조회
			goodsVO.setCategoryList(service.getCategoryLinkList(goodsVO.getGoodsno()));
			
			//상품이미지 저장
			if (goodsVO.getGoodsObj() != null) {
				imgsArray[0][1] = goodsVO.getGoodsObj().getImgl();
				imgsArray[1][1] = goodsVO.getGoodsObj().getImgi();
				imgsArray[2][1] = goodsVO.getGoodsObj().getImgs();
				imgsArray[3][1] = goodsVO.getGoodsObj().getImgm();
			}
			goodsVO.setImgsArray(imgsArray);
			
			//상품추가정보
			String[] ex_title = new String[]{"", ""};	
			if (!"".equals(StringUtil.nullConv(goodsVO.getGoodsObj().getExtitle(), ""))) {
				ex_title = StringUtil.split(goodsVO.getGoodsObj().getExtitle(), "|");
				goodsVO.setExTitle(ex_title);
			}
			
			//관련 상품 리스트
			List<Map<String, Object>> relationList = null;
			int relationCnt = 0;	// 관련상품 갯수
			if (!"".equals(StringUtil.nullConv(goodsVO.getGoodsObj().getRelation(), ""))) {
				relationList = service.getGoodsRelationList(goodsVO.getGoodsObj().getRelation().split(","));
				if (null != relationList) {
					relationCnt = relationList.size();
					goodsVO.setRelationCnt(relationCnt);
					goodsVO.setRelationList(relationList);
				}
			}
			
			// 필수옵션
			goodsVO.setGoodsOptionList(service.getGoodsOption(goodsVO.getGoodsno()));
			
			// 필수옵션에서 상품 가격/재고에 사용될 데이터를 추출한다.
			String[] optNmArray = new String[]{"", ""};	// 옵션명1, 옵션명2
			if ( !"".equals(goodsVO.getGoodsObj().getOptnm())) {
				optNmArray = StringUtil.split(goodsVO.getGoodsObj().getOptnm(), "|");
				if (0 >= optNmArray.length){
					optNmArray = new String[]{"", ""};
				}
				goodsVO.setOptNmArray(optNmArray);
			}
			
			String consumer		= "";	// 리테일가
			String price		= "";	// 즉시할인가
			String priceRate	= "";	// 즉시할인율
			String supply		= "";	// 바잉원가(정책)
			String supply2		= "";	// 바잉원가(DATA)
			String margin		= "";	// 공헌이익율
			
			String[] optTitle	= null;	//사용자정의 옵션 타이틀
			int intTotalStock	= 0;	//재고량
			int i = 0;
			String[] tmpArray	= null;
			
			List<GdGoodsOption> gdGoodsOpt = goodsVO.getGoodsOptionList();
			while (null != gdGoodsOpt && i < gdGoodsOpt.size()){
				// 기본 가격 할당
				if (0 == i) {
					consumer	= StringUtil.nullConv(gdGoodsOpt.get(i).getConsumer() + "", "");
					price		= StringUtil.nullConv(gdGoodsOpt.get(i).getPrice() + "", "");
					priceRate	= StringUtil.nullConv(gdGoodsOpt.get(i).getPriceRate() + "", "");
					supply		= StringUtil.nullConv(gdGoodsOpt.get(i).getSupply() + "", "");
					supply2		= StringUtil.nullConv(gdGoodsOpt.get(i).getSupply2() + "", "");
					margin		= StringUtil.nullConv(gdGoodsOpt.get(i).getMargin() + "", "");
					optTitle	= StringUtil.split(gdGoodsOpt.get(i).getOpt2(), "|");	//옵션 타이틀
				}
				
				//총재고량 계산
				if (!"".equals(StringUtil.nullConv(gdGoodsOpt.get(i).getStock() + "", ""))) {
					tmpArray = StringUtil.split(gdGoodsOpt.get(i).getStock() + "", "|");
					for (int m = 0, len = tmpArray.length; m < len; m++) {
						intTotalStock += Integer.parseInt(tmpArray[m]);	// $stock += $tmp[stock];
					}
				}
				i++;
			}
			goodsVO.setConsumer(consumer);				// 리테일가
			goodsVO.setPrice1(price);					// 즉시할인가
			goodsVO.setPriceRate(priceRate);			// 즉시할인율
			goodsVO.setSupply(supply);					// 바잉원가(정책)
			goodsVO.setSupply2(supply2);				// 바잉원가(DATA)
			goodsVO.setMargin(margin);					// 공헌이익율
			goodsVO.setOptTitle(optTitle);				// 사용자정의 옵션 타이틀
			goodsVO.setIntTotalStock(intTotalStock);	//재고량

			//상품 추가옵션 리스트 (ShopConfig 설정에 따라 셋팅)
			List<GdGoodsAdd> optionAddList = null;
			if ( "false".equals(ShopConfig.getProperty("rental_free")) && !"".equals(StringUtil.nullConv(goodsVO.getGoodsObj().getAddoptnm(), ""))) {
				optionAddList = service.getGoodsAddOptionList(goodsVO.getGoodsno());
			}
			goodsVO.setGoodsAddList(optionAddList);
		} else {
			strHieen = "style='display:none;'";
			goodsVO.setImgsArray(imgsArray);
			goodsVO.setStrHieen(strHieen);
			goodsVO.setTax(1);
			goodsVO.setOpen("0");
			goodsVO.setRelationis(0);
			goodsVO.setMode("register");
		}

		if ("view".equals(req.getParameter("viewFlg"))) {
			// 주문관리 > 주문상세내역에서 상품수정 팝업으로 호출
			return "/shop/admin/seller/goodsPopup.register";
		} else {
			return "/shop/admin/seller/goodsRegister";
		}
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
	@RequestMapping(value="/goodsNotiRegister.dojson")
	public String goodsNotiRegister(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		service.setRegAdminGoodsNoti(goodsVO);
		
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
	@RequestMapping(value="/goodsNotiList.dojson")
	public String goodsNotiList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		goodsVO.setGoodsNotiList(service.getAdminGoodsNotiList(goodsVO));
				
		return "dojson";
	}
	
	
	/*
	 * 판매자 브랜드관리
	 */
	@RequestMapping(value="seller_brand")
	public String SellerBrand(@ModelAttribute("BrandVO") BrandVO brandVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@SellerBrand ==> get");
		}

	
		Map<String, Object> param = new HashMap<String, Object>();
		//검색타입 sBrandnm , sApprovalstatus
		param.put("skey", brandVO.getSkey());
		
		//검색어
		param.put("sword", brandVO.getSword());

		//브랜드명 , 승인상태  판매자명
		param.put("sBrandnm", brandVO.getBrandnm());
		param.put("sApprovalstatus", brandVO.getApprovalStatus());
		param.put("sSellerNm", brandVO.getsSellerNm());

		//총건수
		brandVO.setRowCount(serviceB.getSellerBrandListCount(brandVO));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, brandVO.getPageSize());
				
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, brandVO.getRowStart());
		
		// 목록조회
		brandVO.setSellerBrandList(serviceB.getSellerBrandList(param));
				
		model.addAttribute("brandVO", brandVO);
			
		return "/shop/admin/seller/seller_brand";
	}
}
