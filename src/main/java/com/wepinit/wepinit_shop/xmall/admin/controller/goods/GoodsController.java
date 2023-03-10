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
	
	//?????? ??????
	@RequestMapping(value="list")
	public String goodsList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		/*
		 * ???????????? ?????? 
		 */
		ShopLibFunction.menuAuthPermissionCheck(req, res, "goods");
		
		//??????????????????
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
		
		//???????????????
		goodsVO.setGoodsBrandList(service.getBrandList());
		
		//????????????
		goodsVO.setGoodsSeasonList(service.getSeasonList());
		
		// ??????  ??? ?????? ??????
		goodsVO.setTotalCnt(service.getGoodsListTotalCount());
		
		//?????? ?????? ????????? ??????
		goodsVO.setRowCount(service.getGoodsListCount(goodsVO));
		
		if(0 < goodsVO.getRowCount()){
			goodsVO.setGoodsList(service.getGoodsList(goodsVO));
		}
		
		return "/shop/admin/goods/list";
	}
	
	
	//?????? ??????
	@RequestMapping(value="iframeList")
	public String goodsIframeList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("@@@list goodsVO::"+goodsVO);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		//????????? ??? ??????
		goodsVO.setTotalCnt(service.getGoodsListTotalCount());
		//????????? ?????? ?????????
		goodsVO.setRowCount(service.getGoodsListCount(goodsVO));		
		logger.debug("@@@list goodsVO.getRowCount()::"+goodsVO.getRowCount());
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, goodsVO.getPageSize());
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, goodsVO.getRowStart());	
		
		//?????? ?????? ??????, 2:??????
		goodsVO.setApprovalStatus("2");
		goodsVO.setOpen("1");
		goodsVO.setGoodsList(service.getGoodsList(goodsVO));
		
		return "/shop/admin/goods/_goodslist";
	}
	
	@RequestMapping(value = "/goodsExcelDownload", method = RequestMethod.POST)
	public void goodsExcelDownload(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletResponse response) throws Exception {
		String[] title = {"??????","????????????","SCM??????","???????????????","?????????"
						 ,"?????????(??????)","?????????(??????)","?????????(??????)","Bin","Simn"
						 ,"??????","EU","?????????","??????","????????????"
						 ,"???????????????","???????????????","????????????(??????)","????????????(DATA)","???????????????"
						 ,"???????????????","???????????????(??????)","????????????","???????????????","????????????"
						 ,"????????????","????????????(??????)","????????????(??????)","????????????(??????)","?????????"
						 ,"??????????????????","???????????????"
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
			//????????? ??????
			List<Map<String, Object>> excelList = service.selectGoodsExcelList(goodsVO);
			
			ExcelUtil.writeExcel("?????? ?????????_"+ FileUtil.getTimeStamp()+".xls", "??????", excelList, title, field, response);
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
			model.addAttribute("msg", "????????? ??????????????????.");
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
		
		HashMap<String, Object> paramsDb = null; // DB????????? ?????? map
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
	
	//?????? ?????? ??? ??????
	@RequestMapping(value="register")
	public String register(@ModelAttribute("goodsVO") GoodsVO goodsVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		if (null == goodsVO.getMode()){
			goodsVO.setMode("register");
		}
		
		String strHieen = "";	//????????? ???????????? ??????????????? ??????
		String[][] imgsArray = new String[][]{{"l", ""}, {"i", ""}, {"s", ""}, {"m", ""}};	//?????? ?????????
		
		//???????????????
		goodsVO.setOriginList(service.getOriginList());
		//???????????????
		goodsVO.setGoodsBrandList(service.getBrandList());
		
		//?????? ?????? ????????????
		if ("approvalRevise".equals(goodsVO.getMode())) {
			goodsVO.setGoodsObj(service.getSellerGoodsInfo(goodsVO.getGoodsno()));
			//goodsVO.setGoodsNotiList(service.getGoodsAddInfoList(String.valueOf(goodsVO.getReqsno()))); //???????????? ???????????? 
		} else {
			goodsVO.setGoodsObj(service.getGoodsInfo(goodsVO.getGoodsno()));
		}			
		
		if (goodsVO.getMode().equals("modify") || goodsVO.getMode().equals("approvalRevise")) {
			if ("approvalRevise".equals(goodsVO.getMode())) {
				//????????? ???????????? ?????? ??????
				goodsVO.setCategoryList(service.getMSellerCategoryLinkList(goodsVO.getReqsno()));
			} else {
				//???????????? ?????? ?????? 
				goodsVO.setCategoryList(service.getCategoryLinkList(goodsVO.getGoodsno()));
			}			
			
			//??????????????? ??????
			if (goodsVO.getGoodsObj() != null) {
				imgsArray[0][1] = goodsVO.getGoodsObj().getImgl();
				imgsArray[1][1] = goodsVO.getGoodsObj().getImgi();
				imgsArray[2][1] = goodsVO.getGoodsObj().getImgs();
				imgsArray[3][1] = goodsVO.getGoodsObj().getImgm();	
			}
			goodsVO.setImgsArray(imgsArray);
			
			//??????????????????
			String[] ex_title = new String[]{"", ""};	
			if (!"".equals(StringUtil.nullConv(goodsVO.getGoodsObj().getExtitle(), ""))) {
				ex_title = StringUtil.split(goodsVO.getGoodsObj().getExtitle(), "|");
				goodsVO.setExTitle(ex_title);
			}
			
			//?????? ?????? ?????????
			List<Map<String, Object>> relationList = null;
			int relationCnt = 0;	// ???????????? ??????
			if (!"".equals(StringUtil.nullConv(goodsVO.getGoodsObj().getRelation(), ""))) {			
				relationList = service.getGoodsRelationList(goodsVO.getGoodsObj().getRelation().split(","));
				if (null != relationList) {
					relationCnt = relationList.size();
					goodsVO.setRelationCnt(relationCnt);
					goodsVO.setRelationList(relationList);
				}
			}
			
			//????????????
			goodsVO.setGoodsOptionList(service.getGoodsOption(goodsVO.getGoodsno()));
			
			//?????????????????? ?????? ??????/????????? ????????? ???????????? ????????????.
			String[] optNmArray = new String[]{"", ""};	// ?????????1, ?????????2
			if (!"".equals(goodsVO.getGoodsObj().getOptnm())) {
				optNmArray = StringUtil.split(goodsVO.getGoodsObj().getOptnm(), "|");
				if (0 >= optNmArray.length){
					optNmArray = new String[]{"", ""};
				}
				goodsVO.setOptNmArray(optNmArray);
			}

			String consumer		= "";	//????????????
			String price		= "";	//???????????????
			String priceRate	= "";	//???????????????
			String supply		= "";	//????????????(??????)
			String supply2		= "";	//????????????(DATA)
			String margin		= "";	//???????????????
			String priceMyRitz	= "";	//MyRitz?????????
			
			String[] optTitle	= null;	//??????????????? ?????? ?????????
			int intTotalStock	= 0;	//?????????
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
			goodsVO.setConsumer(consumer);				//????????????
			goodsVO.setPrice1(price);					//???????????????
			goodsVO.setPriceRate(priceRate);			//???????????????
			goodsVO.setSupply(supply);					//????????????(??????)
			goodsVO.setSupply2(supply2);				//????????????(DATA)
			goodsVO.setMargin(margin);					//???????????????
			goodsVO.setOptTitle(optTitle);				//??????????????? ?????? ?????????
			goodsVO.setIntTotalStock(intTotalStock);	//?????????
			goodsVO.setPriceMyRitz(priceMyRitz);		//MyRitz?????????
			
			//???????????? ?????????
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

			//?????? ???????????? ????????? (ShopConfig ????????? ?????? ??????)
			List<GdGoodsAdd> optionAddList = null;
			if ("false".equals(ShopConfig.getProperty("rental_free")) && !"".equals(StringUtil.nullConv(goodsVO.getGoodsObj().getAddoptnm(), ""))) {

				if ("approvalRevise".equals(goodsVO.getMode())) {
					optionAddList = service.getGoodsAddInfoList(String.valueOf(goodsVO.getReqsno())); //????????? ????????????
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
			//???????????? > ???????????????????????? ???????????? ???????????? ??????
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
		
		GdCmContents gdCmContents = new GdCmContents("insurance", "????????????", "Y");
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
			
			// ?????? ??????????????? ???????????? ??????
			if(duplicateYn > 0) {
				String oldImg = cmContentsVO.getOld_img();
				// ????????? ?????????????????? ????????? ?????????????????? ??? ???????????? ?????????
				if(StringUtils.hasText(oldImg)) {
					FileUtil.deleteFile(uploadPathStr.toString(), oldImg);
				}
				
				this.service.updateCommonInsurance(gdCmContents);
			// ?????? ??????????????? ???????????? ??????
			} else {
				this.service.insertComminInsurance(gdCmContents);
			}
			
			complateYn = "Y";
		}

		model.addAttribute("complateYn", complateYn);
		return "/shop/admin/goods/common_insurance_register";
	}
	
	// ?????????????????? ??????
	@RequestMapping(value="common_deliveryInfo_register")
	public String commonDeliveryInfoRegister(Model model) {
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>goods commonDeliveryInfoRegister");
		}
		String contents = this.service.getCommonContents("deliveryinfo").getContents();
		model.addAttribute("contents", contents != null ? contents : "" );

		return "/shop/admin/goods/common_deliveryInfo_register";
	} 
	
	// ?????????????????? ????????????
	@RequestMapping(value="common_deliveryInfo_register/indb")
	public String commonDeliveryInfoRegister(@RequestParam String contents, HttpServletRequest req, Model model) {
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>goods commonDeliveryInfoRegister indb");
		}
		int mNo = AdminSessionObject.getSessionObject(req).getUserInfo().getM_no();
		String complateYn = "";
		GdCmContents gdCmContents = new GdCmContents("deliveryinfo", "????????????", "Y");
		gdCmContents.setM_no(mNo);
		gdCmContents.setContents(contents);
		
		int duplicateYn = this.service.getCommonContentsCount(gdCmContents.getId());
		
		// contents ??? ????????? ?????? ??????
		if(StringUtils.hasText(contents)) {
			// ?????? ????????? ??????????????? ?????? ??????
			if(duplicateYn > 0) {
				this.service.updateCommonDeliveryInfo(gdCmContents);
			// ????????? ??????????????? ?????? ??????
			} else {
				this.service.insertCommonDeliveryInfo(gdCmContents);
			}
			complateYn = "Y";
		}
	
		model.addAttribute("complateYn", complateYn);
		return "/shop/admin/goods/common_deliveryInfo_register";
	}
	
	
	/**
	 * ???????????? ???????????? ??????
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
	 * ???????????? ???????????? ??????
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
	      
	      //?????? ????????????
	      String path = ConfigClass.value("FILE_PATH") + "goods/xls/";
	      String fileName=""; //????????? ?????? ?????????
	      
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
	      
	      //fileName = fileName+"??? ??????????????? ????????? ???????????????.";
	      return result;
	   }
	   

}
