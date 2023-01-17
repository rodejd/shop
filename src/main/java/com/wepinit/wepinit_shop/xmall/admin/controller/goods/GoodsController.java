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

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.ExcelUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.common.CommonService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.CommonInsuranceRegisterVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsManageVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCmContents;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsAdd;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsOption;
import com.wepinit.wepinit_shop.xmall.seller.goods.service.SellerGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/goods/*")
public class GoodsController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Resource
	GoodsService service;
	
	@Resource
	CommonService commonService;
	
	@Resource
	SellerGoodsService sellerGoodsService;
	
	//상품 목록
	@RequestMapping(value="list")
	public String goodsList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		/*
		 * 상품관리 권한 
		 */
		ShopLibFunction.menuAuthPermissionCheck(req, res, "goods");
		
		//카테고리목록
		String schCate = "";
		if (goodsVO.getCate() != null) {
			if (goodsVO.getCate().length > 0) {
				for (int i=0; i<goodsVO.getCate().length; i++){
					if (!"".equals(StringUtil.nullConv(goodsVO.getCate()[i], ""))){
						schCate = goodsVO.getCate()[i];
					}
				}
			}
			goodsVO.setSchCate(schCate);
		}
		
		//브랜드목록
		goodsVO.setGoodsBrandList(service.getBrandList());
		
		//시즌목록
		goodsVO.setGoodsSeasonList(service.getSeasonList());
		
		// 상품  총 건수 조회
		goodsVO.setTotalCnt(service.getGoodsListTotalCount());
		
		//상품 검색 총건수 조회
		goodsVO.setRowCount(service.getGoodsListCount(goodsVO));
		
		if(0 < goodsVO.getRowCount()){
			goodsVO.setGoodsList(service.getGoodsList(goodsVO));
		}
		
		return "/shop/admin/goods/list";
	}
	
	
	//상품 목록
	@RequestMapping(value="iframeList")
	public String goodsIframeList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (logger.isDebugEnabled()) {
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
		
		//상품 승인 여부, 2:승인
		goodsVO.setApprovalStatus("2");
		goodsVO.setOpen("1");
		goodsVO.setGoodsList(service.getGoodsList(goodsVO));
		
		return "/shop/admin/goods/_goodslist";
	}
	
	@RequestMapping(value = "/goodsExcelDownload", method = RequestMethod.POST)
	public void goodsExcelDownload(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletResponse response) throws Exception {
		String[] title = {"번호","상품번호","SCM번호","카테고리명","부틱명"
						 ,"상품명(영문)","상품명(국문)","상품명(중문)","Bin","Simn"
						 ,"시즌","EU","등록일","재고","리테일가"
						 ,"즉시할인가","즉시할인율","바잉원가(정책)","바잉원가(DATA)","공헌이익율"
						 ,"최저가여부","최저가여부(가격)","판매여부","적립금여부","승인여부"
						 ,"관리상품","브랜드명(영문)","브랜드명(국문)","브랜드명(중문)","원산지"
						 ,"배송출발국가","유사검색어"
						 };
		String[] field = {"rn","goodsno","goodscd","categoryNm","sellerCd"
						 ,"goodsnm","goodsnmKR","goodsnmCN","binCd","simnCd"
						 ,"seasonNm","euYn","regdt","stock","consumer"
						 ,"price","priceRate","supply","supply2","margin"
						 ,"lowestPriceYn","lowestPrice","open","use_emoney","approvalStatus"
						 ,"manageYn","brandnm","brandnmKR","brandnmCN","origin"
						 ,"shippingOrigin","keyword"
						 };
		try {
			//리스트 조회
			List<Map<String, Object>> excelList = service.selectGoodsExcelList(goodsVO);
			
			ExcelUtil.writeExcel("상품 리스트_"+ FileUtil.getTimeStamp()+".xls", "정보", excelList, title, field, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/specProc.dojson")
	public String specProc(@ModelAttribute("goodsManageVO") GoodsManageVO goodsManageVO, Model model) throws Exception {
		int result = 0;
		try {
			String[] goodsArr = goodsManageVO.getGoodsArr();
			String[] specArr = goodsManageVO.getSpecArr();
			String spec = goodsManageVO.getSpec();
			
			if(goodsArr != null) {
				for(int i=0; i < goodsArr.length; i++) {
					HashMap<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("spec", spec);
					dataMap.put("goodsno", goodsArr[i]);
					dataMap.put("specYn", specArr[i]);
					result += service.updateGoodsBySpec(dataMap);
				}
			}
			model.addAttribute("result", result);
		}catch (Exception e) {
			model.addAttribute("result", 0);
			model.addAttribute("msg", "오류가 발생했습니다.");
			return "dojson";
		}
		return "dojson";
	}
	
	@RequestMapping(value="indb")
	public String gooodsDB(HttpServletRequest req, HttpServletResponse res,MultipartHttpServletRequest mhsq,  Model model) throws Exception {
		service.goodsDB(req,mhsq);
		return "redirect:/shop/admin/goods/list?" + StringUtil.nvl(req.getParameter("qstr"), "");
	}
	
	@RequestMapping(value="indb2")
	public String gooodsDB(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
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
			paramsDb.put("sellernoArr", req.getParameterValues("sellernoArr"));
			
			service.updateApproval(paramsDb);
		}
		
		return "redirect:/shop/admin/goods/list";
	}
	
	//수정 등록 일 경우
	@RequestMapping(value="register")
	public String register(@ModelAttribute("goodsVO") GoodsVO goodsVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		if (null == goodsVO.getMode()){
			goodsVO.setMode("register");
		}
		
		String strHieen = "";	//화면에 모드별로 출력여부를 결정
		String[][] imgsArray = new String[][]{{"l", ""}, {"i", ""}, {"s", ""}, {"m", ""}};	//상품 이미지
		
		//원산지목록
		goodsVO.setOriginList(service.getOriginList());
		//브랜드목록
		goodsVO.setGoodsBrandList(service.getBrandList());
		
		//상품 정보 가져오기
		if ("approvalRevise".equals(goodsVO.getMode())) {
			goodsVO.setGoodsObj(service.getSellerGoodsInfo(goodsVO.getGoodsno()));
			//goodsVO.setGoodsNotiList(service.getGoodsAddInfoList(String.valueOf(goodsVO.getReqsno()))); //판매자용 추가옵션 
		} else {
			goodsVO.setGoodsObj(service.getGoodsInfo(goodsVO.getGoodsno()));
		}			
		
		if (goodsVO.getMode().equals("modify") || goodsVO.getMode().equals("approvalRevise")) {
			if ("approvalRevise".equals(goodsVO.getMode())) {
				//판매자 카테고리 목록 조회
				goodsVO.setCategoryList(service.getMSellerCategoryLinkList(goodsVO.getReqsno()));
			} else {
				//카테고리 목록 조회 
				goodsVO.setCategoryList(service.getCategoryLinkList(goodsVO.getGoodsno()));
			}			
			
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
			
			//필수옵션
			goodsVO.setGoodsOptionList(service.getGoodsOption(goodsVO.getGoodsno()));
			
			//필수옵션에서 상품 가격/재고에 사용될 데이터를 추출한다.
			String[] optNmArray = new String[]{"", ""};	// 옵션명1, 옵션명2
			if (!"".equals(goodsVO.getGoodsObj().getOptnm())) {
				optNmArray = StringUtil.split(goodsVO.getGoodsObj().getOptnm(), "|");
				if (0 >= optNmArray.length){
					optNmArray = new String[]{"", ""};
				}
				goodsVO.setOptNmArray(optNmArray);
			}

			String consumer		= "";	//리테일가
			String price		= "";	//즉시할인가
			String priceRate	= "";	//즉시할인율
			String supply		= "";	//바잉원가(정책)
			String supply2		= "";	//바잉원가(DATA)
			String margin		= "";	//공헌이익율
			String priceMyRitz	= "";	//MyRitz공급가
			
			String[] optTitle	= null;	//사용자정의 옵션 타이틀
			int intTotalStock	= 0;	//재고량
			int i = 0;
			
			List<GdGoodsOption> gdGoodsOpt = goodsVO.getGoodsOptionList();
			for (Iterator<GdGoodsOption> iter = gdGoodsOpt.iterator(); iter.hasNext();) {
				GdGoodsOption ggo = iter.next();
				if ("0".equals(ggo.getParent())) {
					consumer		= StringUtil.nullConv(ggo.getConsumer() + "", "");
					price			= StringUtil.nullConv(ggo.getPrice() + "","");
					priceRate		= StringUtil.nullConv(ggo.getPriceRate() + "","");
					supply			= StringUtil.nullConv(ggo.getSupply() + "", "");
					supply2			= StringUtil.nullConv(ggo.getSupply2() + "", "");
					margin			= StringUtil.nullConv(ggo.getMargin() + "", "");
					priceMyRitz		= StringUtil.nullConv(ggo.getPriceMyRitz() + "","");
					intTotalStock	= Integer.parseInt(ggo.getStock());
					iter.remove();
					break;
				}
			}
			goodsVO.setConsumer(consumer);				//리테일가
			goodsVO.setPrice1(price);					//즉시할인가
			goodsVO.setPriceRate(priceRate);			//즉시할인율
			goodsVO.setSupply(supply);					//바잉원가(정책)
			goodsVO.setSupply2(supply2);				//바잉원가(DATA)
			goodsVO.setMargin(margin);					//공헌이익율
			goodsVO.setOptTitle(optTitle);				//사용자정의 옵션 타이틀
			goodsVO.setIntTotalStock(intTotalStock);	//재고량
			goodsVO.setPriceMyRitz(priceMyRitz);		//MyRitz공급가
			
			//상품옵션 리스트
			String [] opt2Nm = StringUtil.split(StringUtil.nvl(goodsVO.getGoodsObj().getOptnm(), "") ,"|");
			if (opt2Nm.length > 0 && null != opt2Nm) {
				String [] stocks = new String[opt2Nm.length];
				String [] opt2Stock = null;
				
				for (GdGoodsOption ggo : gdGoodsOpt) {
					opt2Stock = StringUtil.split(ggo.getStock(), "|");
					for (i = 0; i < stocks.length ; i++) {
						stocks[i] = opt2Stock[i];
					}
					ggo.setStocks(stocks);
					stocks = new String[opt2Nm.length];
					ggo.setStock("0");
				}
			}
			goodsVO.setGoodsOptionList(gdGoodsOpt);

			//상품 추가옵션 리스트 (ShopConfig 설정에 따라 셋팅)
			List<GdGoodsAdd> optionAddList = null;
			if ("false".equals(ShopConfig.getProperty("rental_free")) && !"".equals(StringUtil.nullConv(goodsVO.getGoodsObj().getAddoptnm(), ""))) {

				if ("approvalRevise".equals(goodsVO.getMode())) {
					optionAddList = service.getGoodsAddInfoList(String.valueOf(goodsVO.getReqsno())); //판매자 추가옵션
				} else {
					optionAddList = service.getGoodsAddOptionList(goodsVO.getGoodsno());
				}
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
			//주문관리 > 주문상세내역에서 상품수정 팝업으로 호출
			return "/shop/admin/goods/popup.register";
		} else {
			return "/shop/admin/goods/register";
		}
	}
		
	@RequestMapping(value="common_insurance_register", method=RequestMethod.GET)
	public String commonInsuranceRegister(HttpServletRequest req, Model model) {

		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>goods commonInsuranceRegister");
		}
		
		GdCmContents cmContentsVO = this.service.getCommonContents("insurance");
		if(cmContentsVO != null) {
			String resultImg = cmContentsVO.getImg();
			if(!"".equals(resultImg)) {
				String[] resultImgArr = resultImg.split("/");
				resultImg = resultImgArr[resultImgArr.length - 1];
			}
			model.addAttribute("img", resultImg);
		}

		return "/shop/admin/goods/common_insurance_register";
	}
	
	@RequestMapping(value="common_insurance_register/indb", method=RequestMethod.POST)
	public String commonInsuranceRegisterIndb(@ModelAttribute("cmContentsVO") CommonInsuranceRegisterVO cmContentsVO, HttpServletRequest req, Model model) throws IOException, Exception {
		String complateYn = "";
		
		AdminSessionObject sessionObject = AdminSessionObject.getSessionObject(req);
		
		GdCmContents gdCmContents = new GdCmContents("insurance", "보험인증", "Y");
		gdCmContents.setM_no(sessionObject.getUserInfo().getM_no());
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>goods commonInsuranceRegister/indb");
		}
		
		int duplicateYn = this.service.getCommonContentsCount("insurance");
		
		if(cmContentsVO.getImg() != null) {
			StringBuffer uploadPathStr = new StringBuffer();
			uploadPathStr.append(ConfigClass.value("WEB_DIR_PATH")).append(File.separator)
						 .append("shop").append(File.separator)
						 .append("data").append(File.separator)
						 .append("upload").append(File.separator)
						 .append("common_contents").append(File.separator);
			gdCmContents.setImg(FileUtil.uploadFile2(uploadPathStr.toString(), cmContentsVO.getImg().getOriginalFilename(), cmContentsVO.getImg().getBytes(), false));
			
			// 공통 보험인증이 등록되어 있음
			if(duplicateYn > 0) {
				String oldImg = cmContentsVO.getOld_img();
				// 넘어온 올드이미지가 있다면 저장폴더에서 그 이미지를 삭제함
				if(StringUtils.hasText(oldImg)) {
					FileUtil.deleteFile(uploadPathStr.toString(), oldImg);
				}
				
				this.service.updateCommonInsurance(gdCmContents);
			// 공통 보험인증이 등록되지 않음
			} else {
				this.service.insertComminInsurance(gdCmContents);
			}
			
			complateYn = "Y";
		}

		model.addAttribute("complateYn", complateYn);
		return "/shop/admin/goods/common_insurance_register";
	}
	
	// 배송정보관리 화면
	@RequestMapping(value="common_deliveryInfo_register")
	public String commonDeliveryInfoRegister(Model model) {
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>goods commonDeliveryInfoRegister");
		}
		String contents = this.service.getCommonContents("deliveryinfo").getContents();
		model.addAttribute("contents", contents != null ? contents : "" );

		return "/shop/admin/goods/common_deliveryInfo_register";
	} 
	
	// 배송정보관리 내용저장
	@RequestMapping(value="common_deliveryInfo_register/indb")
	public String commonDeliveryInfoRegister(@RequestParam String contents, HttpServletRequest req, Model model) {
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>goods commonDeliveryInfoRegister indb");
		}
		int mNo = AdminSessionObject.getSessionObject(req).getUserInfo().getM_no();
		String complateYn = "";
		GdCmContents gdCmContents = new GdCmContents("deliveryinfo", "배송정보", "Y");
		gdCmContents.setM_no(mNo);
		gdCmContents.setContents(contents);
		
		int duplicateYn = this.service.getCommonContentsCount(gdCmContents.getId());
		
		// contents 에 내용이 있는 경우
		if(StringUtils.hasText(contents)) {
			// 이미 등록된 배송정보가 있는 경우
			if(duplicateYn > 0) {
				this.service.updateCommonDeliveryInfo(gdCmContents);
			// 등록된 배송정보가 없는 경우
			} else {
				this.service.insertCommonDeliveryInfo(gdCmContents);
			}
			complateYn = "Y";
		}
	
		model.addAttribute("complateYn", complateYn);
		return "/shop/admin/goods/common_deliveryInfo_register";
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
	@RequestMapping(value="/notiList.dojson")
	public String goodsNotiList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		goodsVO.setGoodsNotiList(service.getAdminGoodsNotiList(goodsVO));
				
		return "dojson";
	}
	
	@RequestMapping(value="xlsRegister")
	public String excelUpload(Model model){
		
		logger.debug("xlsRegister Controller>>>>>>>");
		return "/shop/admin/goods/xlsRegister";
	}

	
	   @RequestMapping(value="xlsRegister/indb")
	   @ResponseBody
	   public String fileUp(HttpServletRequest req,MultipartHttpServletRequest multi) throws IOException, Exception {
		   String result = "";
	      logger.debug("public String fileUp(MultipartHttpServletRequest multi) throws IOException, Exception {");
	      
	      //저장 경로설정
	      String path = ConfigClass.value("FILE_PATH") + "goods/xls/";
	      String fileName=""; //업로드 되는 파일명
	      
	      File dir = new File(path);
	      if(!dir.isDirectory()){
	         dir.mkdir();
	      }

	      Iterator<String> files = multi.getFileNames();
	      if(files.hasNext()){
	         String uploadFile = files.next();
	         MultipartFile mFile = multi.getFile(uploadFile);
	         fileName = mFile.getOriginalFilename();
	         
	         try {
	            mFile.transferTo(new File(path+fileName));
	         } catch(Exception e) {
	            e.printStackTrace();
	         }
	      }
	      result = service.xlsUpload(req,(path+fileName));
	      
	      //fileName = fileName+"을 성공적으로 업로드 하였습니다.";
	      return result;
	   }
	   

}
