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
import com.wepinit.wepinit_shop.xmall.admin.service.goods.CategoryService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.CategoryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.SortVO;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/shop/admin/goods/*")
public class CategoryController {
//	@Resource(name="uploadPath")
//	private String uploadPath;
	
	@Resource
	CategoryService service;
	
	/*
	 * 상품관리
	 * 카테고리 관리
	 */
	@RequestMapping(value="category")
	public String category(@ModelAttribute("cateVO") CategoryVO cateVO) throws Exception{
		return "/shop/admin/goods/category";
	}
	
	/**
	 * 카테고리 엑셀다운로드
	 * @param cateVO
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/category/excelDownload", method = RequestMethod.POST)
	public void excelDownload(@ModelAttribute("cateVO")CategoryVO cateVO, HttpServletResponse response) throws Exception {
		
		String[] title = {"분류코드","영문","국문","중문","연관키워드","사용여부","판매중 상품수"};
		String[] fieldNm = {"category","categoryDepthNm","categoryDepthKRNm","categoryDepthCNNm","catMemo","hiddenNm","cnt"};
		try {
			//리스트 조회
			List<Map<String, Object>> excelList = service.getGoodsCategoryExcelList(cateVO);
			
			ExcelUtil.writeExcel("카테고리 관리_"+ FileUtil.getTimeStamp()+".xls", "정보", excelList, title, fieldNm, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 상품관리
	 * 카테고리 관리
	 * iframe
	 */
	@RequestMapping(value="iframe.category")
	public String iframeCategory(@ModelAttribute("cateVO")CategoryVO cateVO) throws Exception{
		String category = cateVO.getCategory();
		/*
		 * 카테고리 리스트
		 */
		cateVO.setCategoryObj(service.getGoodsCategoryList(cateVO));
		
		/*
		 * 카테고리로 분류된 상품의 총 개수
		 */
		cateVO.setGoodsCnt(service.getGoodsLinkCount(category));
		
		/*
		 * 추천상품정보
		 */
		cateVO.setHitList(service.getGoodsDisplayList(category));
		
		/*
		 * 카테고리 정보
		 */
		cateVO.setCategoryInfoObj(service.getGoodsCategoryBrandInfo(category));
		//상품 리스트 레이아웃 INSERT OR UPDAET FLAG SETTING
		if(cateVO.getCategoryInfoObj() != null){
			cateVO.setInfoyn("Y");
		} else {
			cateVO.setInfoyn("N");
		}
		
		/*
		 * 카테고리 이미지 정보
		 */
		//카테고리 분류 이미지명
		ShopLibFunction shop = new ShopLibFunction();
		HashMap<String, Object> categoryImg = shop.getCategoryImg(category);
		if(categoryImg != null){
			if(categoryImg.get("basic") != null){
				cateVO.setOldattach0(String.valueOf(categoryImg.get("basic")));	
			}
			if(categoryImg.get("over") != null){
				cateVO.setOldattach1(String.valueOf(categoryImg.get("over")));	
				
			}
		}
		
		return "/shop/admin/goods/iframe.category";
	}
	
	/*
	 * 상품관리
	 * 카테고리 관리 등록/수정/삭제 처리
	 */
	@RequestMapping(value="category/indb")
	public String indb(@ModelAttribute("cateVO")CategoryVO cateVO,	MultipartHttpServletRequest mhsq, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		String returnUrl ="";
		
		if("mod_category".equals(cateVO.getMode())){
			String[] arrTmp = null;
			/*
			 * 하위 분류 추가 및 현분류명 수정
			 */
			if(cateVO.getSub() != null & !"".equals(cateVO.getSub())){
				//INSERT
				cateVO.setCategoryMax(service.getGoodsCategoryMax(cateVO));
				try{
					int k = 0;
					/*
					 * maxCategory의 값이 없을 경우에는 빈스트링이 발생하여 에러가 발생되므로
					 * 최초 하위카테고리의 생성일 경우로 분기 처리한다.
					 */
					String maxCate = "";
					String tmp = "";
					if(cateVO.getCategoryMax() != null){
						maxCate = String.valueOf(cateVO.getCategoryMax().get("maxcategory"));
						
						k = Integer.parseInt(maxCate.substring(maxCate.length()-3, maxCate.length()));
						k += 1;
						tmp	= StringUtil.lpad(String.valueOf(k), 3, '0');
						maxCate = maxCate.substring(0, maxCate.length()-3) + tmp;
					} else {
						maxCate = cateVO.getCategory() + "001";
					}
					cateVO.setCategory(maxCate);
				
				}catch(Exception e){
					throw new BizException("category.00001");
				}
				
				//상품 카테고리 등록(gd_category)
				service.insertGoodsCategory(cateVO);
			} else {
				//상품 카테고리 수정(gd_category)
				service.updateGoodsCategory(cateVO);
			}
			
			if("Y".equals(cateVO.getInfoyn())){
				//상품 카테고리/브랜드 정보 수정(xm_category_brand_info)
				service.updateGoodsCategoryBrandInfo(cateVO);
			}else{
				//상품 카테고리/브랜드 정보 등록(xm_category_brand_info)
				service.insertGoodsCategoryBrandInfo(cateVO);
			}
			
			/*
			 * 연결상품 삭제 / 등록
			 */
			service.deleteGoodsDisplay(cateVO);
			
			if(cateVO.getErefer() != null) {
				arrTmp = cateVO.getErefer();
				if(null != arrTmp && 0 < arrTmp.length){
					for(int i=0; i<arrTmp.length; i++){
						if(arrTmp[i] != null){
							cateVO.setGoodsno(Integer.parseInt(arrTmp[i]));
							service.insertGoodsDisplay(cateVO);
						}
					}
				}				
			}

			
			/*
			 * 하위분류 동일 적용시 업데이트
			 * 추천상품 선정의 이미지 정보는 적용하지 않는다.
			 */
			if("1".equals(cateVO.getChkdesign())){
				cateVO.setCategoryList(service.getCategoryList(cateVO));
				
				String prtcategory = cateVO.getCategory();		//상위 카테고리
				if(cateVO.getCategoryList().size() > 0){
					for(int i=0; i<cateVO.getCategoryList().size(); i++){
						cateVO.setCategory(cateVO.getCategoryList().get(i).getCategory());
						/*
						 * 하위 카테고리 정보를 UPDATE
						 */
						service.updateGoodsCategorySub(prtcategory, cateVO.getCategory());
						
						/*
						 * 하위 카테고리 상세정보의 유무에 따라서 분기 처리
						 * 하위 카테고리 상세정보를 INSERT OR UPDATE	
						 */
						if(service.getGoodsCategoryBrandInfoSub(cateVO.getCategory()) == 0){
							//하위 카테고리 상세정보를 INSERT
							service.insertGoodsCategoryBrandInfoSub(prtcategory, cateVO.getCategory());
						} else {
							//하위 카테고리 상세정보를 UPDATE
							service.updateGoodsCategoryBrandInfoSub(prtcategory, cateVO.getCategory());
						}
						
						//상품에 매핑되는 카테고리의 hidden여부를 변경한다.
						service.updateGoodsCategoryHidden(cateVO.getCategory(), cateVO.getHidden());
					}
				}
			}
			
			/*
			 * 분류 이미지 삭제 및 업로드
			 */

			//이미지 업로드
			String savedNameImg0 = cateVO.getOldattach0();
			String savedNameImg1 = cateVO.getOldattach1();
			
			//확장자
			int pos = 0;
			String ext = "";
			
			if(cateVO.getAttachFile0() != null){
				if(!cateVO.getAttachFile0().isEmpty()){
					pos = cateVO.getAttachFile0().getOriginalFilename().lastIndexOf(".");
					ext = cateVO.getAttachFile0().getOriginalFilename().substring(pos);
					/*savedNameImg0 = FileUtil.uploadFile2(ConfigClass.value("WEB_DIR_PATH")+"shop"+File.separator+"data"+File.separator+"category"+File.separator ,cateVO.getCategory()+"_basic", cateVO.getAttachFile0().getBytes(), false);*/
					savedNameImg0 = FileUtil.uploadFile4(ConfigClass.value("WEB_DIR_PATH")+"shop"+File.separator+"data"+File.separator+"category"+File.separator ,cateVO.getCategory()+"_basic"+ext, cateVO.getAttachFile0().getBytes());
				}
			}
			cateVO.setOldattach0(savedNameImg0);
			
			if(cateVO.getAttachFile1() != null){
				if(!cateVO.getAttachFile1().isEmpty()){
					pos = cateVO.getAttachFile1().getOriginalFilename().lastIndexOf(".");
					ext = cateVO.getAttachFile1().getOriginalFilename().substring(pos);
					/*savedNameImg1 = FileUtil.uploadFile2(ConfigClass.value("WEB_DIR_PATH")+"shop"+File.separator+"data"+File.separator+"category"+File.separator ,cateVO.getCategory()+"_over", cateVO.getAttachFile1().getBytes(), false);*/
					savedNameImg1 = FileUtil.uploadFile4(ConfigClass.value("WEB_DIR_PATH")+"shop"+File.separator+"data"+File.separator+"category"+File.separator ,cateVO.getCategory()+"_over"+ext, cateVO.getAttachFile1().getBytes());
				}
			}
			cateVO.setOldattach1(savedNameImg1);
			
			//이미지 삭제 선택시 기존파일 삭제
			if("1".equals(cateVO.getChkimg0())){
				FileUtil.deleteFile(ConfigClass.value("WEB_DIR_PATH")+"shop"+File.separator+"data"+File.separator+"category"+File.separator, cateVO.getOldattach0());	
			}
			if("1".equals(cateVO.getChkimg1())){
				FileUtil.deleteFile(ConfigClass.value("WEB_DIR_PATH")+"shop"+File.separator+"data"+File.separator+"category"+File.separator, cateVO.getOldattach1());
			}
//			model.addAttribute("result", 1);
			//returnUrl = "/shop/admin/goods/iframe.category";
			returnUrl = "redirect:/shop/admin/goods/iframe.category";
			
		}
		
		return returnUrl;
	}
	
	@RequestMapping("category/indb2")
	public String categoryDelete(@ModelAttribute("cateVO")CategoryVO cateVO,
			Model model) throws Exception {
		
		String returnUrl = "";
		
		if("del_category".equals(cateVO.getMode())){
			/*
			 * 삭제시 카테고리, 카테고리상품 링크, 카테고리상품 노출 테이블의 모든 데이터를 지운다.
			 * 하위 카테고리도 지워야 하기때문에 like 검색을 한다.
			 * gd_goods_display 테이블의 삭제는 mode를 like 검색을 하는데 이해가 안된다.(php 소스도 마찬가지)
			 * 이 부분은 업무적으로 다시 협의 필요하다.
			 * 파일을 삭제하는 로직이 존재한다. 확인 필요하다.
			 */
			service.deleteCategory(cateVO.getCategory());
			model.addAttribute("result", 1);
			returnUrl ="/shop/admin/goods/iframe.category";
			
		}else if("sortGoods".equals(cateVO.getMode())){
			
			if(cateVO.getArrSno() != null){
				long sort = 0;
				String overlapYN = "N";		//검색내역 중 중복이 된 sort 값이 있는지에 대한 여부
				Map<String, Object> param = new HashMap<String, Object>();
				
				//sort 값이 중복된 값이 있는 지 확인
				for(int i=0; i<cateVO.getArrSno().length; i++){
					//sort 최소 값
					if(sort > Long.parseLong(cateVO.getArrSno()[i])){
						sort = Long.parseLong(cateVO.getArrSno()[i]);
					}
					
					for(int j=0; "N".equals(overlapYN) && j<cateVO.getArrSno().length; j++){
						//중복되는 sort가 있을 경우
						if(cateVO.getArrSno()[i].equals(cateVO.getArrSno()[j])){
							overlapYN = "Y";
							break;
						}
					}
				}
				
				//정렬된 sort를 입력
				for(int i=0; i<cateVO.getArrSno().length; i++){
					param.put("sno", cateVO.getArrSno()[i]);
					param.put("sort", ("N".equals(overlapYN) ? cateVO.getArrSort()[i] : String.valueOf(sort)));
					
					service.updateGoodsLinkSort(param);
					sort = sort+1;
				}
			}
			returnUrl = "redirect:/shop/admin/goods/sort";
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value="category.dojson")
	public String categoryAjax(@ModelAttribute("cateVO")CategoryVO cateVO) throws Exception{
		/*
		 * ===========================================================================================
		 * 상품분류(카테고리)관리 > 카테고리관리 > 카테고리 조회
		 * ===========================================================================================
		 */
		cateVO.setCategoryList(service.getCategoryList(cateVO));
		
		return "dojson";
	}
	
	@RequestMapping(value="categoryHid.dojson")
	public String categoryHiddingAjax(@ModelAttribute("cateVO")CategoryVO cateVO) throws Exception{
		
		service.updateGoodsCategoryHiddenUpdate(cateVO.getCategory(), cateVO.getHidden());
		
		return "dojson";
	}
	
	@RequestMapping(value="popup.delCategory")
	public String popdeleteCategory(@ModelAttribute("cateVO")CategoryVO cateVO) throws Exception{
		/*
		 * 카테고리로 분류된 상품의 총 개수
		 */
		cateVO.setGoodsCnt(service.getGoodsLinkCount(cateVO.getCategory()));
		
		return "/shop/admin/goods/popup.delCategory";
	}
	
	@RequestMapping(value="sort")
	public String sort(@ModelAttribute("sortVO") SortVO sortVO) throws Exception{
		//검색조건 > 분류선택 데이터 셋팅
		if(sortVO.getCate() != null){
			for(int i=0; i<sortVO.getCate().length; i++){
				if(!"".equals(StringUtil.nullConv(sortVO.getCate()[i], ""))){
					sortVO.setCategory(sortVO.getCate()[i]);		//select box setting value
				}
			}
			//상품 목록조회
			sortVO.setGoodsList(service.getGoodsLinkSortList(sortVO));
		}
		
		return "/shop/admin/goods/sort";
	}

}
