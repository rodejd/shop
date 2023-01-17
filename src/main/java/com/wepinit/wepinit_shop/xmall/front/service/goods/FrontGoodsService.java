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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Service
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.service.goods;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.dao.ShopLibFncMapper;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.dao.goods.FrontGoodsMapper;
import com.wepinit.wepinit_shop.xmall.front.dao.mypage.FrontMypageMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.goods.*;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service
@Transactional
public class FrontGoodsService {
	
	Logger logger = LoggerFactory.getLogger(FrontGoodsService.class);
	
	@Resource
	FrontGoodsMapper mapper;
	
	@Resource
	FrontMypageMapper frontMypageMapper;	

	@Resource
	ShopLibFncMapper shopLibFncMapper;
	
	/**
	 * 브랜드목록
	 * @param frontGoodsVO
	 */
	public int getGdGoodsBrandListCount(FrontGoodsBrandVO frontGoodsBrandVO) throws Exception {
		return mapper.getGdGoodsBrandListCount(frontGoodsBrandVO);
	}
	public List<GdGoodsBrand> getGdGoodsBrandList(FrontGoodsBrandVO frontGoodsBrandVO) throws Exception {
		return mapper.getGdGoodsBrandList(frontGoodsBrandVO);
	}
	
	public GdGoodsBrand getGdGoodsBrandObj(FrontGoodsVO frontGoodsVO) throws Exception {
		return mapper.getGdGoodsBrandObj(frontGoodsVO);
	}
	
	public XmCategoryBrandInfo getXmCategoryBrandInfo(FrontGoodsVO frontGoodsVO) throws Exception {
		return mapper.getXmCategoryBrandInfo(frontGoodsVO);
	}
	
	public int getFrontGoodsBrandCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsBrandCount(param);
	}
	
	public List<GdGoodsGoodsoptionGoodslink> getFrontGoodsBrandList(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsBrandList(param);
	}
	
	
	/**
	 * 카테고리목록
	 * @param param
	 */
	public int getFrontGoodsGoodsCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsGoodsCount(param);
	}
	
	public List<GdGoodsGoodsoptionGoodslinkGoodsbrand> getFrontGoodsGoodsList(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsGoodsList(param);
	}
	
	public List<GdCategory> getFrontGoodsGoodsCategoryList(Map<String, Object> param) throws Exception {
		List<GdCategory> categoryList = mapper.getFrontGoodsGoodsCategoryList(param);
		
		GdCategory current = new GdCategory();
		current.setCategory((String) param.get("category"));
		List<GdCategory> depth1List = this.setFrontGoodsGoodsCategoryList(current, categoryList);
		if (depth1List != null && depth1List.size() > 0) {
			for (GdCategory depth1 : depth1List) {
				List<GdCategory> depth2List = this.setFrontGoodsGoodsCategoryList(depth1, categoryList);
				depth1.setCategoryList(depth2List);
				if (depth2List != null && depth2List.size() > 0) {
					for (GdCategory depth2 : depth2List) {
						List<GdCategory> depth3List = this.setFrontGoodsGoodsCategoryList(depth2, categoryList);
						depth2.setCategoryList(depth3List);
					}
				}
			}	
		}
		
		return depth1List;
	}
	
	public List<GdCategory> setFrontGoodsGoodsCategoryList(GdCategory category, List<GdCategory> categoryList) throws Exception {
		List<GdCategory> subList = null;
		
		if (categoryList.size() > 0) {
			for (GdCategory gdCategory : categoryList) {
				if (gdCategory.getCategory().length() == category.getCategory().length() + 3 &&
					gdCategory.getCategory().substring(0, category.getCategory().length()).equals(category.getCategory())) {
					if (subList == null)
						subList = new ArrayList<GdCategory>();
					
					subList.add(gdCategory);
				} 
			}
		}
		
		return subList;
	}
	
	public GdCategory getFrontGoodsGoodsCategoryInfo(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsGoodsCategoryInfo(param);
	}
	
	public List<GdGoodsBrand> getFrontGoodsGoodsBrandList(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsGoodsBrandList(param);
	}
	
	/**
	 * 상품번호로 대표카테고리 조회
	 * @param goodsno
	 * @return
	 * @throws Exception
	 */
	public GdCategory getFrontGoodsGoodsCategoryByGoodsno(String goodsno) throws Exception {
		return mapper.getFrontGoodsGoodsCategoryByGoodsno(goodsno);
	}
	
	/**
	 * 상품 목록
	 * @param frontGoodsVO
	 */
	public int getFrontGoodsTotCnt(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsTotCnt(param);
	}
	
	public List<GdGoodsGoodsoptionGoodslink> getFrontGoodsList(Map<String, Object> param) throws Exception {
		return mapper.getFrontGoodsList(param);
	}
	
	/**
	 * 상품 상세 정보 조회
	 * @param frontGoodsVO
	 */
	public void getFrontGoodsView(FrontGoodsVO frontGoodsVO) throws Exception{
		/********************************************
		 * 상품 상세정보 조회
		 * - input : goodsno
		 ********************************************/
		GdGoodsGoodsoptionGoodslink gdGoodsGoodsoptionGoodslink = mapper.getFrontGoodsView(frontGoodsVO);
		frontGoodsVO.setGoodsView(gdGoodsGoodsoptionGoodslink);
		//적립금 추가
		//frontGoodsVO.getGoodsView().setReserve(ShopLibFunction.getReserve(Integer.parseInt(frontGoodsVO.getGoodsno())));
		// 상품 정책이 기본배송정책 사용이라면 기본배송정책 세팅 (판매사인 경우 판매사의 정책, 아닌경우 관리자 기본배송정책을 세팅합니다.)
		if(gdGoodsGoodsoptionGoodslink.getDeliverytype() == 0) {
			frontGoodsVO.setDeliveryInfoList(ShopLibFunction.getDefaultDeliveryPolicy(frontGoodsVO.getGoodsView().getSellerCd()));
		}
		
		/******************************************** 
		 * 상품 브랜드 목록 조회
		 * - input : brandno
		 ********************************************/
		frontGoodsVO.setBrandno(gdGoodsGoodsoptionGoodslink.getBrandno());
		List<GdGoodsBrand> brandList = mapper.getFrontGoodsViewBrandList(frontGoodsVO);
		frontGoodsVO.setBrandList(brandList);
		
		
		/********************************************
		 * 관련 상품 목록 조회
		 * - input : collection="relationList"
		 ********************************************/
		String relationStr = gdGoodsGoodsoptionGoodslink.getRelation();			//관련상품 str
		if(logger.isDebugEnabled()){
			logger.debug("bh3211>>>>>>>>>relationStr :{}",relationStr);	
		}
		
		List<GdGoods> relationGoodsList =null;
		//관련상품 설정이 안되어 있을 경우 random 조회
		if(relationStr == null || "".equals(relationStr) || "null".equals(relationStr)){
			relationGoodsList = mapper.getFrontGoodsRelationRandomList(frontGoodsVO);
		//관련 상품 설정이 되어 있을경우
		}else{
			String[] relationArray = relationStr.split(",");
			frontGoodsVO.setRelationArray(relationArray);
			relationGoodsList = mapper.getFrontGoodsRelationList(frontGoodsVO);
		}
		
		frontGoodsVO.setRelationGoodsList(relationGoodsList);
		
		/********************************************
		 * 상품평 POINT 평균 조회
		 * - input : goodsno
		 ********************************************/
		frontGoodsVO.setGoodsReviewAvg(mapper.getFrontGoodsReviewPointAvg(frontGoodsVO));
		
		/********************************************
		 * 상품 필수 옵션 리스트 조회
		 * - input : goodsno
		 ********************************************/
		//AS-IS 상품 금액 조회
		List<GdGoodsOption> goodsOptionList = mapper.getFrontGoodsOptionList(frontGoodsVO);
		frontGoodsVO.setGoodsOptionList(goodsOptionList);
		
		/********************************************
		 * 상품 필수 추가 옵션 리스트 조회
		 * - input : goodsno
		 ********************************************/
		// 상품 상세정보 중 추가옵션명이 있을경우 조회
		String[] addOptNmArray = gdGoodsGoodsoptionGoodslink.getAddoptnm().split("\\|");
		List<GdGoodsAdd> goodsAddOptionList = null;				//추가 옵션 정보 List
		if ( 0 < addOptNmArray.length ) {
			goodsAddOptionList = mapper.getFrontGoodsAddOptionList(frontGoodsVO);
		}
		
		//추가옵션정보가 있을 경우 옵션정보 설정
		if(goodsAddOptionList != null && goodsAddOptionList.size() > 0 && !"".equals(frontGoodsVO.getGoodsView().getAddoptnm())){
			List<Map<String,Object>> addGoodsOptList = new ArrayList<Map<String,Object>>();	//추가 옵션 List
			StringTokenizer strToken = null;
			String addOptTitle	= "";	//추가 옵션 이름
			String optValue		= "";	//옵션 value
			String optText		= "";	//옵션 text
			
			//상품정보에 추가 옵션명 이 있을 경우
			for(int i=0 ; i < addOptNmArray.length ; i++){
				strToken = new StringTokenizer(addOptNmArray[i], "^");
				addOptTitle = strToken.nextToken();
				Map<String,Object> addGoodsOptMap = new HashMap<String,Object>();	//추가 옵션 상세설정 Map 생성
				
				if(logger.isDebugEnabled()){
					logger.debug("bh3211>>>>>>>>>>>>>>>>getFrontGoodsView >>>>>>>>>>>>>>>");
					logger.debug("bh3211>>>>>>>>>i 				:{}",i);
					logger.debug("bh3211>>>>>>>>>addOptTitle :{}",addOptTitle);
					logger.debug("bh3211>>>>>>>>>>>>>>>>getFrontGoodsView >>>>>>>>>>>>>>>");	
				}
				
				//추가 옵션명 설정
				addGoodsOptMap.put("addOptTitle", addOptTitle);
				
				//추가 옵션 List 설정
				List<Map<String,String>>	addOptContents = new ArrayList<Map<String,String>>();
				for(int j=0 ; j<goodsAddOptionList.size() ; j++){
					Map<String,String> optMap = new HashMap<String,String>();		//select 박스 옵션 값을 담을 map
					if(i == goodsAddOptionList.get(j).getStep()){
						//옵션 value 설정
						optValue = goodsAddOptionList.get(j).getSno()+"^"+addOptTitle+"^"+goodsAddOptionList.get(j).getOpt()+ "^"+goodsAddOptionList.get(j).getAddprice();
						optText	= goodsAddOptionList.get(j).getOpt() + "(" + StringUtil.getMoneyFormat(goodsAddOptionList.get(j).getAddprice()) + "원 추가)"; 
						optMap.put("optValue", optValue);
						optMap.put("optText", optText);
						
						addOptContents.add(optMap);
					}
				}
				
				//추가 옵션 내용 설정
				addGoodsOptMap.put("addOptContents",addOptContents);
				
				//추가 옵션 List 설정
				addGoodsOptList.add(addGoodsOptMap);
			}
			
			//추가 옵션 정보 설정
			frontGoodsVO.setGoodsAddOptionList(addGoodsOptList);
		}
		
		//회원 직접 다운로드 쿠폰 조회
		if(logger.isDebugEnabled()){
			logger.debug("@@ coupon >> category"+frontGoodsVO.getGoodsView().getCategory()+" goodsno >>"+frontGoodsVO.getGoodsno());
		}
		Map<String,Object> param  = new HashMap<String,Object>();
		
		//2017-08-18 상품쿠폰을 위한 category 목록 가져오기 추가
		List<String> cateList = mapper.getFrontGoodsLinkList(frontGoodsVO.getGoodsView().getGoodsno());

		List<String> category = new ArrayList<String>();
		for(int j=0; j< cateList.size(); j++){
			for(int i=3; cateList.get(j).length() >= i; i=i+3){
				category.add(cateList.get(j).substring(0, i));
			}
		}
		//2017-08-18 추가 끝
		
//		if(frontGoodsVO.getGoodsView().getCategory().length() >= 3){
//			if(logger.isDebugEnabled()){
//				logger.debug("@@ length >> "+ frontGoodsVO.getGoodsView().getCategory().length() + "  substring >>" + frontGoodsVO.getGoodsView().getCategory().substring(0, 3));
//			}
//			int j=0;
//			for(int i=3; frontGoodsVO.getGoodsView().getCategory().length() >= i; i=i+3){
//				
//				category[j++] = frontGoodsVO.getGoodsView().getCategory().substring(0,i);
//				
//				if(logger.isDebugEnabled()){
//					logger.debug("@@ coupon >> category length >> "+frontGoodsVO.getGoodsView().getCategory().length());
//					logger.debug("@@ coupon >> j >> "+j+" i >> "+i);
//					logger.debug("@@ coupon >> arr >> "+category[0]+" category[j] >> "+category[j-1]);
//				}
//			}
//			
//		}
		
		param.put("category", category);
		param.put("goodsno", frontGoodsVO.getGoodsno());
		param.put("skin", frontGoodsVO.getSkin());
		frontGoodsVO.setCouponList(mapper.getFrontDownCouponList(param));
	
		/********************************************
		 * 상품 QNA 목록 조회
		 ********************************************/
		/*
		FrontGoodsQnAVO frontGoodsQnAVO = new FrontGoodsQnAVO();
		frontGoodsQnAVO.setGoodsno(frontGoodsVO.getGoodsno());
		
		// 상품 QNA 총카운트 조회
		// - input : goodsno
		frontGoodsQnAVO.setRowCount(mapper.getFrontGoodsQNATotCnt(frontGoodsQnAVO));
		
		 // 상품평 목록 조회
		 // - input : goodsno, rowStart, pageSize
		frontGoodsQnAVO.setGoodsQnAList(mapper.getFrontGoodsQNAList(frontGoodsQnAVO));
		
		frontGoodsVO.setFrontGoodsQnAVO(frontGoodsQnAVO);
		*/
		
		/********************************************
		 * 상품평 목록 조회
		 ********************************************/
		/*
		FrontGoodsReviewVO frontGoodsReviewVO = new FrontGoodsReviewVO();
		frontGoodsReviewVO.setGoodsno(frontGoodsVO.getGoodsno());
		
		// 상품평 총카운트 조회
		// - input : goodsno
		frontGoodsReviewVO.setRowCount(mapper.getFrontGoodsReviewTotCnt(frontGoodsReviewVO));
		
		 // 상품평 목록 조회
		 // - input : goodsno, rowStart, pageSize
		frontGoodsReviewVO.setGoodsReviewList(mapper.getFrontGoodsReviewList(frontGoodsReviewVO));
		
		frontGoodsVO.setFrontGoodsReviewVO(frontGoodsReviewVO);
		*/
	}
	
	/**
	 * 상품평 임시 이미지 등록
	 * @param frontGoodsReviewVO
	 */
	public int insertReviewTempFile(FrontGoodsReviewVO frontGoodsReviewVO){
		return mapper.insertReviewTempFile(frontGoodsReviewVO);
	}
	
	/**
	 * 상품별 상품평 조회
	 * @param frontGoodsReviewVO
	 */
	public void getFrontMarketReview(FrontGoodsReviewVO frontGoodsReviewVO){
		
		/********************************************
		 * 상품평 목록 조회
		 ********************************************/
		// 상품평 총카운트 조회
		// - input : goodsno
		frontGoodsReviewVO.setRowCount(mapper.getFrontGoodsReviewTotCnt(frontGoodsReviewVO));
		
		 // 상품평 목록 조회
		 // - input : goodsno, rowStart, pageSize
		frontGoodsReviewVO.setGoodsReviewList(mapper.getFrontGoodsReviewList(frontGoodsReviewVO));
		
	}
	
	/**
	 * 상품별 상품평 포토 조회
	 * @param frontGoodsReviewVO
	 */
	public List<FrontGoodsReviewVO> getFrontGoodsReviewFileList(FrontGoodsReviewVO frontGoodsReviewVO){
		return mapper.getFrontGoodsReviewFileList(frontGoodsReviewVO);
	}
	
	/**
	 * 상품별 상품평 등록,수정,삭제
	 * @param frontGoodsReviewVO
	 */
	public int setFrontGoodsReviewWrite(FrontGoodsReviewVO frontGoodsReviewVO, HttpServletRequest request){
		
		GdGoodsReview gdGoodsReview = new GdGoodsReview();
		ShopSessionObject shop_so	= ShopSessionObject.getSessionObject(request);
		
		int resultCnt = 0;
		
		//등록
		if("add_review".equals(frontGoodsReviewVO.getMode())){
			/************************************************************
			 * 상품별 이용후기 작성
			 * input : goodsno,subject, contents, point, mno, password, ip			
			 ************************************************************/
			gdGoodsReview.setGoodsno(Integer.parseInt(frontGoodsReviewVO.getGoodsno()));
			gdGoodsReview.setSubject("");
			gdGoodsReview.setContents(frontGoodsReviewVO.getContents());
			gdGoodsReview.setPoint(Integer.parseInt(frontGoodsReviewVO.getPoint()));
			gdGoodsReview.setMno(shop_so.getUserInfo().getM_no());
			gdGoodsReview.setPassword("");
			gdGoodsReview.setIp(request.getRemoteAddr());
			gdGoodsReview.setSkin(ConfigClass.getSkin(request));
			gdGoodsReview.setOrdno(frontGoodsReviewVO.getOrdno());
			
			//insert
			resultCnt = frontMypageMapper.setGoodsReviewInsert(gdGoodsReview);
			
			/************************************************************
			 * 이용후기작성 sno 업데이트
			 ************************************************************/
			resultCnt = frontMypageMapper.setGoodsReviewSnoUpdate();
			
			if(resultCnt > 0) {
				String[] fileArr = frontGoodsReviewVO.getFnoArr();
				Boolean isEmpty = ArrayUtils.isEmpty(fileArr);
				if(!isEmpty) {
					List<FrontGoodsReviewVO> fileList = mapper.selectReviewTempFileList(frontGoodsReviewVO);
					if( !fileList.isEmpty() ) {
						AwsFileUtil awsfileUtil = new AwsFileUtil();
						for(FrontGoodsReviewVO reviewVO : fileList) {
							// 임시파일->파일 등록
							FrontGoodsReviewVO insertReviewVO = new FrontGoodsReviewVO();
							insertReviewVO.setSno( String.valueOf(gdGoodsReview.getSno()) );
							insertReviewVO.setImg(reviewVO.getImg().replaceAll("/review_temp/", "/review/"));
							insertReviewVO.setRegid(reviewVO.getRegid());
							mapper.insertReviewFile(insertReviewVO);
							
							String imgUrl = FileUtil.getUrlFileName(reviewVO.getImg());
							//파일 복사
							awsfileUtil.copy("review_temp/" + imgUrl, "review/" + imgUrl);
							
							//파일 삭제
							awsfileUtil.delete("review_temp/" + imgUrl);
							
							//임시파일 삭제
							FrontGoodsReviewVO deleteVO = new FrontGoodsReviewVO();
							deleteVO.setFno(reviewVO.getFno());
							mapper.deleteReviewTempFile(deleteVO);
						}
					}
					
				}
				
				

				/**
				 * 상품평 등록에 따른 적립금 등록 셋팅
				 */
				DecimalFormat form = new DecimalFormat("0.00");
				Map<String, Object> pMap = new HashMap<String, Object>();
				pMap.put("mno", shop_so.getUserInfo().getM_no());
				Map<String, Object> memberInfo = shopLibFncMapper.selectShopLibMemberInfo(pMap);
				Map<String, Object> paramMap = new HashMap<String, Object>();
				BigDecimal memberEmoney = new BigDecimal(StringUtil.nvl(memberInfo.get("emoney"),"0")); //회원정보에 남은 적립금
				BigDecimal reviewEmoney = new BigDecimal("0"); // 일반상품평 1유로, 포토상품평 5유로
				paramMap = new HashMap<String, Object>();
				if(isEmpty) { //일반 상품평
					reviewEmoney = new BigDecimal("1"); // 일반상품평 1유로, 포토상품평 5유로
					paramMap.put("memo", "일반상품평 등록에 따른 적립");
				} else { //포토 상품평
					reviewEmoney = new BigDecimal("5"); // 일반상품평 1유로, 포토상품평 5유로
					paramMap.put("memo", "포토상품평 등록에 따른 적립");
				}
				//적립금 로그등록 (gd_log_emoney)
				paramMap.put("m_no", shop_so.getUserInfo().getM_no()); //회원번호
				paramMap.put("gbn", "S"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
				paramMap.put("ordno", frontGoodsReviewVO.getOrdno()); //주문번호
				paramMap.put("emoney", form.format(reviewEmoney));
				shopLibFncMapper.insertShopLibEmoneyLog(paramMap);
				
				//회원테이블에 적립금 복구처리 (gd_member)
				paramMap = new HashMap<String, Object>();
				paramMap.put("m_no", shop_so.getUserInfo().getM_no());
				paramMap.put("emoney", form.format(memberEmoney.add(reviewEmoney)));
				shopLibFncMapper.updateShopLibMemberEmoney(paramMap);
				
			}
		//수정	
		}else if("mod_review".equals(frontGoodsReviewVO.getMode())){
			
			/************************************************************
			 * 나의 상품후기 수정
			 * - input : sno,subject,contents,point
			 ************************************************************/
			gdGoodsReview.setSno(Integer.parseInt(frontGoodsReviewVO.getSno()));
			gdGoodsReview.setSubject("");
			gdGoodsReview.setContents(frontGoodsReviewVO.getContents());
			gdGoodsReview.setPoint(Integer.parseInt(frontGoodsReviewVO.getPoint()));
		
			//update
			resultCnt = frontMypageMapper.setGoodsReviewUpdate(gdGoodsReview);

		
		//삭제
		}else if("del_review".equals(frontGoodsReviewVO.getMode())){
			
			/************************************************************
			 * 나의 상품후기 삭제
			 * - input : sno
			 ************************************************************/
			gdGoodsReview.setSno(Integer.parseInt(frontGoodsReviewVO.getSno()));
			
			//delete
			resultCnt = frontMypageMapper.setGoodsReviewDelete(gdGoodsReview);
			
		}
		
		return resultCnt;
	}
	
	/**
	 * 상품별 QnA 리스트 조회
	 * @param frontGoodsQnAVO
	 */
	public void getFrontGoodsQnAList(FrontGoodsQnAVO frontGoodsQnAVO){
		
		/********************************************
		 * 상품 QNA 목록 조회
		 ********************************************/
		// 상품 QNA 총카운트 조회
		// - input : goodsno
		frontGoodsQnAVO.setRowCount(mapper.getFrontGoodsQnATotCnt(frontGoodsQnAVO));
		
		 // 상품평 목록 조회
		 // - input : goodsno, rowStart, pageSize
		frontGoodsQnAVO.setGoodsQnAList(mapper.getFrontGoodsQnAList(frontGoodsQnAVO));
		
		
	}
	
	/**
	 * 상품별 QnA 등록,수정,삭제
	 * @param frontGoodsQnAVO
	 * @param request
	 */
	public int setFrontGoodsQnAWrite(FrontGoodsQnAVO frontGoodsQnAVO, HttpServletRequest request){
		
		GdGoodsQna gdGoodsQna = new GdGoodsQna();
		ShopSessionObject shop_so	= ShopSessionObject.getSessionObject(request);
		
		int resultCnt = 0;
		
		//등록
		if("add_qna".equals(frontGoodsQnAVO.getMode())){

			/************************************************************
			 * 상품별 QnA 작성
			 * input : goodsno,subject, contents, mno, password, ip			
			 ************************************************************/
			gdGoodsQna.setGoodsno(Integer.parseInt(frontGoodsQnAVO.getGoodsno()));
			gdGoodsQna.setSubject(frontGoodsQnAVO.getSubject());
			gdGoodsQna.setContents(frontGoodsQnAVO.getContents());
			gdGoodsQna.setMno(shop_so.getUserInfo().getM_no());
			gdGoodsQna.setPassword("");
			gdGoodsQna.setIp(request.getRemoteAddr());
			gdGoodsQna.setSkin(ConfigClass.getSkin(request));
			
			//insert
			resultCnt = frontMypageMapper.setGoodsQnAInsert(gdGoodsQna);
			
			/************************************************************
			 * 상품별 QnA sno 업데이트
			 ************************************************************/
			resultCnt = frontMypageMapper.setGoodsQnASnoUpdate();
		//수정	
		}else if("mod_qna".equals(frontGoodsQnAVO.getMode())){
			
			/************************************************************
			 * 나의 상품별 QnA 수정
			 * - input : subject,contents,mno,sno
			 ************************************************************/
			
			gdGoodsQna.setSubject(frontGoodsQnAVO.getSubject());
			gdGoodsQna.setContents(frontGoodsQnAVO.getContents());
			gdGoodsQna.setMno(shop_so.getUserInfo().getM_no());
			gdGoodsQna.setSno(Integer.parseInt(frontGoodsQnAVO.getSno()));
		
			//update
			resultCnt = frontMypageMapper.setGoodsQnAUpdate(gdGoodsQna);
			
		//삭제
		}else if("del_qna".equals(frontGoodsQnAVO.getMode())){
			
			/************************************************************
			 * 나의 상품별 QnA 삭제
			 * - input : sno
			 ************************************************************/
			gdGoodsQna.setSno(Integer.parseInt(frontGoodsQnAVO.getSno()));
			
			//delete
			resultCnt = frontMypageMapper.setGoodsQnADelete(gdGoodsQna);
		}
		
		return resultCnt;
	}
	
	// controller use method
	public int goodsCartsAddItem(FrontGoodsCartsVO cartVO, HttpServletRequest req, HttpServletResponse resp, HttpSession session, UserInfo userInfo) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsAddItem " + cartVO.getOptionsList());
		}
		logger.debug("옵션맨처음"+cartVO.getOptionsList());
		// 새로 cookie에 저장될 상품정보
		String[] optionsArray = cartVO.getOptionsList().split("\\,");
		
		List<String> cookieOptionList = new ArrayList<String>();
		
		for(String option : optionsArray) {
			logger.debug("옵션값"+option);
			// optionList 는 ' 12|||NULL|||NULL|||1 ' 이런 형태로 인입되고
			// 매칭되는 데이터는 ' goodsno|||opt|||addopt|||ea ' 이다.
			String[] optionsItem = option.split("\\|\\|\\|");
			for(String s : optionsItem){
				logger.debug("optionsItem > " + s);
			}
			/*String[] optionsItem = option.split("^");*/
			String goodsno = optionsItem[0];
			String opt1 = optionsItem[1];
			//logger.debug("옵션에뭐들었어"+opt);
			String addopt = optionsItem[2];
			// 상품수량이 지정되지 않았다면 무조건 1로 지정
			String ea = StringUtils.hasText(optionsItem[3]) ? optionsItem[3] : "1";
			
			//String opt1 = StringUtils.hasText(opt) && !opt.equals("NULL") ? opt.split("\\|")[0] : "";
			//String opt2 = StringUtils.hasText(opt) && !opt.equals("NULL") ? opt.split("\\|")[1] : "";
			//String opt1 = "";
			String opt2 = "";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("goodsno", goodsno);
			paramMap.put("ea", ea);
			paramMap.put("opt1", opt1);
			paramMap.put("opt2", opt2);
			paramMap.put("no", cartVO.getDeliveryNo());
			cartVO.setOpt1(opt1);
			cartVO.setOpt2(opt2);
			paramMap.put("addopt", StringUtils.hasText(addopt) && !addopt.equals("NULL") ? addopt : "");
			
			// 장바구니 목록 select
			FrontCheckStockVO checkStockVO = this.frontCheckStock(paramMap);
			
			int state = 0;
			int stock = 0;
			String goodsnm = "";
			String goodsnmKR = "";
			String goodsnmCN = "";
			if(checkStockVO != null) {
				if(StringUtils.hasText(checkStockVO.getRunout()) && !checkStockVO.getRunout().equals("0")) {
					state = 1;
				}
				stock = Integer.parseInt(checkStockVO.getStock());
				goodsnm = checkStockVO.getGoodsnm();
				goodsnmKR = checkStockVO.getGoodsnmKR();
				goodsnmCN = checkStockVO.getGoodsnmCN();
				if(checkStockVO.getUsestock() != null && Integer.parseInt(ea) > stock) {
					state = stock > 0 ? 2 : 3;
				}
			}
			Map<String, Object> alertMap = new HashMap<String, Object>();
			alertMap.put("goodsno", goodsno);
			alertMap.put("state", state);
			alertMap.put("goodsnm", goodsnm);
			alertMap.put("goodsnmKR", goodsnmKR);
			alertMap.put("goodsnmCN", goodsnmCN);
			alertMap.put("stock", stock);
			int result = this.setCartsAlertMessage(alertMap, cartVO, req);
			if(result > 0) {
				return result;
			}

//			2020-02-05 이현빈 비회원도 상품 장바구니 등록 추가
			paramMap.put("m_no", userInfo.getM_no());
			paramMap.put("ukey", userInfo.getUkey());
			List<GdGoodsCart> gdGoodsCartList = this.goodsMemberCartChkcnt(paramMap);
			if(gdGoodsCartList.size() == 0){
				this.goodsMemberCartInsert(paramMap);
			} else{
				mapper.updateGoodsCartEA(paramMap);
			}

			cookieOptionList.add(option);
		}
		
		//기존 cookie에 저장되어 있던 상품정보
		String oriCookie = WebUtil.getCookies(req, "carts");
		if(StringUtils.hasText(oriCookie)) {
			String[] lists = oriCookie.split("\\,");
			if(lists != null) {
				for(String list : lists) {
					if(!cookieOptionList.contains(list)) {
						cookieOptionList.add(list);
					}
				}
			}
		}
		
		//DB조회
		String cookieOptionStr = this.goodsCartSearchDB(cookieOptionList, cartVO, null);
		logger.debug("####[FrontGoodsService] cookieOptionStr : "+cookieOptionStr );
		session.setAttribute("carts", cookieOptionStr);
		//WebUtil.setCookies(resp, "carts", cookieOptionStr);
		return 0;

	}
	
	public void goodsCartsList(FrontGoodsCartsVO cartVO, HttpServletRequest req) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsList");
		}
		String oriCookie = WebUtil.getCookies(req, "carts");
		if(StringUtils.hasText(oriCookie)) {
			String[] optionArr = oriCookie.split("\\,");
			this.goodsCartSearchDB(Arrays.asList(optionArr), cartVO, null);
		}

	}
	
	public void goodsCartsEmpty(HttpServletRequest req, HttpServletResponse resp, UserInfo userInfo) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsEmpty");
		}
		
		if(userInfo.getM_no() != 0) {
			this.goodsMemberCartEmpty(userInfo.getM_no());
		}
		WebUtil.setCookies(resp, "carts", "", -1);
	}
	
	public void goodsCartsDelItem(UserInfo userInfo, FrontGoodsCartsVO cartVO, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsDelItem");
		}
		
		if(userInfo.getM_no() != 0 || !"".equals(userInfo.getUkey())) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("m_no", userInfo.getM_no());
			paramMap.put("ukey", userInfo.getUkey());
			paramMap.put("sno", cartVO.getOptionSno());
			
			this.goodsMemberCartDelete(paramMap);
		}
		String idx = cartVO.getIdx();
		String[] arrIdx = cartVO.getArrIdx().split(",");
		String oriCookie = WebUtil.getCookies(req, "carts");
		List<String> cookieOptionList = new ArrayList<String>();

		if(StringUtils.hasText(oriCookie)) {
			String[] cookieArr = oriCookie.split("\\,");
			if(cookieArr != null) {
				if(idx.equals("")) {
					for(int i = 0, size = cookieArr.length; i < size; i++) {
						for(String arridx : arrIdx) {
							if(arridx.equals("n")) {
								cookieOptionList.add(cookieArr[i]);
								break;
							}
							if(i != Integer.parseInt(arridx)) {
								cookieOptionList.add(cookieArr[i]);
							}
							break;
						}
					}
				} else {
					for(int i = 0, size = cookieArr.length; i < size; i++) {
						if(i != Integer.parseInt(idx)) {
							cookieOptionList.add(cookieArr[i]);
						}
					}
				}
			}
		}

		String cookieOptionStr = this.goodsCartSearchDB(cookieOptionList, cartVO, null);
		WebUtil.setCookies(resp, "carts", cookieOptionStr);
	}
	
	public void goodsCartsModItem(UserInfo userInfo, FrontGoodsCartsVO cartVO, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsModItem");
		}
		
		if(userInfo.getM_no() != 0 || !"".equals(userInfo.getUkey())) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("m_no", userInfo.getM_no());
			paramMap.put("ukey", userInfo.getUkey());
			paramMap.put("sno", cartVO.getIdx());
			paramMap.put("opt", cartVO.getOptionSno());
			this.goodsMemberCartOptUpdate(paramMap);
		}
		
		String oriCookie = WebUtil.getCookies(req, "carts");
		List<String> cookieOptionList = new ArrayList<String>();
		if(StringUtils.hasText(oriCookie)) {
			String[] lists = oriCookie.split("\\,");
			if(lists != null) {
				for(String list : lists) {
					cookieOptionList.add(list);
				}
			}
		}
		
		String cookieOptionStr = this.goodsCartSearchDB(cookieOptionList, cartVO, cartVO.getIdx());
		
		WebUtil.setCookies(resp, "carts", cookieOptionStr);
		
	}
	
	public void goodsCartsSelItem(FrontGoodsCartsVO cartVO, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsSelItem");
		}
		
		String idx = cartVO.getIdx();
		String[] arrIdx = cartVO.getArrIdx().split(",");
		
		String oriCookie = WebUtil.getCookies(req, "carts");
		List<String> cookieOptionList = new ArrayList<String>();
		
		if(StringUtils.hasText(oriCookie)) {
			String[] lists = oriCookie.split("\\,");
			
			if(lists != null) {
				if(idx.equals("")) {
					for(int i = 0, size = lists.length; i < size; i++) {
						for(String idxStr : arrIdx) {
							if(idxStr.equals("n")) {
								break;
							}
							if(i != Integer.parseInt(idxStr)) {
								break;
							}
							cookieOptionList.add(lists[i]);
							break;
						}
					}
				} else {
					for(int i = 0, size = lists.length; i < size; i++) {
						if(i == Integer.parseInt(lists[i])) {
							cookieOptionList.add(lists[i]);
						}
					}
				}
			}
		}
	
		String cookieOptionStr = this.goodsCartSearchDB(cookieOptionList, cartVO, idx);

		WebUtil.setCookies(resp, "selectOrders", cookieOptionStr);
		if(cartVO.getGuestSel().equals("")) {
			cartVO.setRedirectPage("/shop/order/order?mode2=selectOrder");
		} else {
			cartVO.setRedirectPage("/shop/order/order?guest=1&mode2=selectOrder");
		}

	}
	
	public void goodsCartsLoginY(UserInfo userInfo, FrontGoodsCartsVO cartVO) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsLoginY");
		}
		
		int grpEmeony = 0;
		if (userInfo.getM_no() == 0 && userInfo.getUkey() != "") {
			this.updateCartList(userInfo);
		} else {
			grpEmeony = (int) userInfo.getXkey().get("add_emoney");
		}
		
		// 로그인된 아이디 기준으로 DB에 저장된 카트리스트 정보 가져오기
		List<GdGoodsCart> goodsCartList 		= this.goodsCartList(userInfo);
		List<FrontGoodsCartsInnerVO> cartList	= new ArrayList<FrontGoodsCartsInnerVO>();
		
		int totalPrice = 0;
		
		// 장바구니에 담겨진 물품이 있다면 실행
		if(goodsCartList.size() > 0) {
			for(GdGoodsCart goodsCart : goodsCartList) {
				String addopt 	= goodsCart.getAddopt();
				int goodsno 	= goodsCart.getGoodsno();
				int ea 			= goodsCart.getEa();
				int sno 		= goodsCart.getSno();
				String opt1		= goodsCart.getOpt1();
				
				// DB에서 데이터 가져올 때 필요한 파라미터 세팅 (opt1, opt2 는 현재 시점에서 쓰이지 않으므로 나중에 확인하여 제거하여도 될 것.)
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("goodsno", goodsno);
				paramMap.put("opt1", opt1);
				//paramMap.put("opt1", cartVO.getOpt1());
				paramMap.put("opt2", cartVO.getOpt2());
				
				// 장바구니에 담았던 상품번호로 해당 상품정보 가져오기
				GoodsMemberCartListVO listVO = this.goodsMemberCartList(paramMap);
				if(listVO == null) {
					listVO = new GoodsMemberCartListVO();
				}
				listVO.setEa(ea);
				
				int priceSum = this.getPriceSum(listVO, addopt); // 상품가격 * 개수 + 옵션들 가격  = 총가격
				int reserve = this.getReserve(priceSum, grpEmeony); //상품 할인가 적용된 가격
				
				String delivery = "";
				//String deliveryType = listVO.getDeliveryType(); // 상품별 배송비 세팅 0, 1, 2, 3
				//배송비 세팅값이 없을경우 세팅해주고 하는게없다 그래서 제거
				/*if(goodsCart.getDeliveryPolicyNo() == null) {
					DecimalFormat format = new DecimalFormat("#,###");
					delivery = format.format(Integer.parseInt(listVO.getGoodsDelivery())) + " 원";
				} else {
					GdDeliveryPolicy deliveryPolicy = ShopLibFunction.getDefaultDeliveryPolicy(goodsCart.getDeliveryPolicyNo()).get(0);
					delivery = deliveryPolicy.getDeliveryInfo();			
				}*/
				
				//판매자 코드가있으면 판매자 배송설정 정보 없으면 총관리자 배송설정 값을 가져온다
				GdDeliveryPolicy deliveryPolicy = ShopLibFunction.getDefaultDeliveryPolicy(listVO.getSellerCd()).get(0);
				delivery = deliveryPolicy.getDeliveryInfo();	
				
				//추가 배송비 세팅
				//frontGoodsVO.setOverDeliveryInfo(ShopLibFunction.getOverDeliveryInfo(listVO.getSellerCd()));
				
				FrontGoodsCartsInnerVO inVO = new FrontGoodsCartsInnerVO();
				inVO.setBrandnm(listVO.getBrandnm());
				inVO.setGoodsnm(listVO.getGoodsnm());
				inVO.setGoodsnmKR(listVO.getGoodsnmKR());
				inVO.setGoodsnmCN(listVO.getGoodsnmCN());
				inVO.setImg(listVO.getImg());
				inVO.setConsumer(listVO.getConsumer());
				inVO.setPrice(listVO.getPrice());
				inVO.setPriceRate(listVO.getPriceRate());
				inVO.setPriceSum(String.valueOf(priceSum));
				inVO.setReserve(String.valueOf(reserve));
//				inVO.setDeliveryType(deliveryType);	// 상품별 배송비 세팅 0, 1, 2, 3
//				if("0".equals(deliveryType)) {
//					String deliveryPolicyNo = goodsCart.getDeliveryPolicyNo();	// 상품 배송정책이 기본배송정책 사용이라면 장바구니에 넣을때 고객이 선택한 배송비정책을 가져온다.
//					List<GdDeliveryPolicy> deliveryPolicy = ShopLibFunction.getDeliveryDefaultPolicy(deliveryPolicyNo);
//				}
//				inVO.setGoodsDelivery(listVO.getGoodsDelivery());
				inVO.setUseEmoney(listVO.getUseEmoney());
				inVO.setGoodsno(String.valueOf(goodsno));
				inVO.setAddopt(addopt);
				inVO.setEa(String.valueOf(ea));
				inVO.setGoodsCategory(listVO.getCategory());
				inVO.setGoodsDelivery(delivery);
				inVO.setDeliveryPolicyNo(goodsCart.getDeliveryPolicyNo());
				inVO.setSno(sno);
				inVO.setOptno(opt1);
				inVO.setOptNm(listVO.getOptNm());
				inVO.setOpttype(listVO.getOpttype());
				inVO.setVipYn(listVO.getVipYn());
				
				FrontGoodsVO frontGoodsVO = new FrontGoodsVO();
				frontGoodsVO.setGoodsno(Integer.toString(goodsno));
				List<GdGoodsOption> optList = mapper.getFrontGoodsOptionList(frontGoodsVO);
				for (Iterator<GdGoodsOption> iter = optList.iterator(); iter.hasNext();) {
					GdGoodsOption ggo = iter.next();
					if ("0".equals(ggo.getParent())) {
						iter.remove();
						break;
					}
				}
				
				List<GdGoodsOption> opt2List = new ArrayList<GdGoodsOption>();
				if ("1".equals(listVO.getUsegoodsadd()) && optList.size() > 0) {
					if ("int".equals(listVO.getOpttype())) {
						String optNm = "";
						String optNm2 = "";
						for (GdGoodsOption list : optList) { 
							optNm = list.getOpt1();
							if (!optNm.equals(optNm2)) {
								optNm2 = optNm;
								opt2List.add(list);
							}
						}
					}
				}
				inVO.setOptionList(optList);
				inVO.setOption2List(opt2List);
				
				cartList.add(inVO);
				totalPrice += priceSum;
			}
		}
		cartVO.setTotalPrice(totalPrice);
		cartVO.setInVOList(cartList);
		
	}
	
	public void goodsCartsLoginN(FrontGoodsCartsVO cartVO, HttpServletRequest req) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartsLoginN");
		}
		List<String> cookieOptionList = new ArrayList<String>();
		String oriCookie = WebUtil.getCookies(req, "carts");
		
		if(StringUtils.hasText(oriCookie)) {
			String[] lists = oriCookie.split("\\,");
			if(lists != null) {
				for(int i = 0, size = lists.length; i < size; i++) {
					if(!cookieOptionList.contains(lists[i])) {
						cookieOptionList.add(lists[i]);
					}
				}
			}
		}
		
		this.goodsCartSearchDB(cookieOptionList, cartVO, null);
//		

	}
	
	public FrontCheckStockVO frontCheckStock(Map<String, Object> map) {
		return this.mapper.frontCheckStock(map);
	}
	
	// mapper
	public List<GdGoodsCart> goodsMemberCartChkcnt(Map<String, Object> map) {
		return this.mapper.goodsMemberCartChkcnt(map);
	}
	
	public void goodsMemberCartInsert(Map<String, Object> map) {
		this.mapper.goodsMemberCartInsert(map);
	}
	
	public GoodsMemberCartListVO goodsMemberCartList(Map<String, Object> map) {
		return this.mapper.goodsMemberCartList(map);
	}
	
	public void goodsMemberCartEmpty(int m_no) {
		this.mapper.goodsMemberCartEmpty(m_no);
	}
	
	public void goodsMemberCartDelete(Map<String, Object> map) {
		this.mapper.goodsMemberCartDelete(map);
	}
	
	public void goodsMemberCartOptUpdate(Map<String, Object> map) {
		this.mapper.goodsMemberCartOptUpdate(map);
	}
	
	public void goodsMemberCartEaUpdate(Map<String, Object> map) {
		this.mapper.goodsMemberCartEaUpdate(map);
	}
	
	/*public List<GdGoodsCart> goodsCartList(int m_no) {
		return this.mapper.goodsCartList(m_no);
	}*/
	public List<GdGoodsCart> goodsCartList(UserInfo userInfo) {
		return this.mapper.goodsCartList(userInfo);
	}
	public void updateCartList(UserInfo userInfo) {
		this.mapper.updateCartList(userInfo);
	}
	
	// service use method
	public int setCartsAlertMessage(Map<String, Object> map, FrontGoodsCartsVO cartVO, HttpServletRequest req) {
		Integer state = (Integer) map.get("state");	       
		String goodsno = (String) map.get("goodsno");
		String goodsnm = (String) map.get("goodsnm");
		String goodsType = cartVO.getGoodsType();
		String goodsCategory = cartVO.getCategory();
		String returnUrl = req.getHeader("referer");

		if ("kr".equals(ConfigClass.getSkin(req))) {
			goodsnm = (String) map.get("goodsnmKR");
		} else if ("cn".equals(ConfigClass.getSkin(req))) {
			goodsnm = (String) map.get("goodsnmCN");
		}
		
		switch(state) {
		case 1 :
			cartVO.setAlertMessage(goodsnm + " 은 품절된 상품입니다.");
			if(goodsType.equals("recipe")) {
				cartVO.setRedirectPage("../goods/goods_view?goodsno=" + goodsno);
			} else {
				cartVO.setRedirectPage("../goods/goods_view?goodsno=" + goodsno + "&category=" + goodsCategory);
			}
			break;
			
		case 2 : 
			String stock = (String) map.get("stock");
			if(goodsType.equals("recipe")) {
				cartVO.setAlertMessage(goodsnm + " 상품의 잔여 재고는 " + stock + " 개입니다.");
				cartVO.setRedirectPage("../goods/goods_view?goodsno=" + goodsno);
			} else {
				cartVO.setRedirectPage("../goods/goods_view?goodsno=" + goodsno + "&category=" + goodsCategory);
			}
			break;
			
		case 3 :
			cartVO.setAlertMessage("상품의 잔여 재고가 존재하지 않습니다.");
			cartVO.setRedirectPage(returnUrl);
			break;
		}
		return state;
		
	}
	
	public int getPriceSum(GoodsMemberCartListVO cartListVO, String addopt) {
		int price = StringUtil.N2I(cartListVO.getPrice());
		int addPriceSum = 0;
		logger.debug("addopt > " + addopt);
		if(StringUtils.hasText(addopt) && !addopt.equals("NULL")) {
			String[] addoptArr = addopt.split("\\|");
			for(String add : addoptArr) {
				String addoptResult = add.split("\\^")[3];
				if(addoptResult != null) {
					addPriceSum += StringUtil.N2D(addoptResult);
				}
			}
		}
		return price * cartListVO.getEa() + addPriceSum;
	}
	
	public int getReserve(int priceSum, int grpEmoney) throws Exception {
		int reserve = 0;
		/*
		String config = ShopConfig.getProperty("emoney_chk_goods_emoney");
		if("0".equals(config)) {
			reserve = priceSum - ShopLibFunction.getDcprice(String.valueOf(priceSum), ShopConfig.getProperty("emoney_goods_emoney") + "%");
		}
		
		if("1".equals(config)) {
			reserve = StringUtil.N2D(ShopConfig.getProperty("emoney_goods_emoney"));
		}
		*/
		reserve = priceSum - ShopLibFunction.getDcprice(String.valueOf(priceSum), grpEmoney + "%");
		return reserve;
	}
	
	public String goodsCartSearchDB(List<String> cookieOptionList, FrontGoodsCartsVO cartVO, String idx) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsService >> goodsCartSearchDB");
		}
		logger.debug("FrontGoodsService >> goodsCartSearchDB >> cookieOptionList size === "+ cookieOptionList.size());

		int totalPrice = 0;
		List<FrontGoodsCartsInnerVO> cartList = new ArrayList<FrontGoodsCartsInnerVO>();
		String cookieOptionStr = "";
		int i = 0;
		for(String option : cookieOptionList) {
			// DB 조회
			String[] optionArr = option.split("\\|\\|\\|");
			if(optionArr != null) {
				String goodsno = optionArr[0];
				String opt1 = optionArr[1];
				String opt2 = "";
				
				/*
				 * opt2 현재 사용하지 않음
				if(StringUtils.hasText(optionArr[1]) && !optionArr[1].equals("NULL")) {
					opt1 = optionArr[1].split("\\|")[0];
					opt2 = optionArr[1].split("\\|")[1];
				} 
				*/
				
				String addopt = optionArr[2];
				String ea = optionArr[3];
				if((idx != null) && (i == Integer.parseInt(idx))) {
					ea = cartVO.getParamEa();
				}
				if(StringUtils.hasText(cookieOptionStr)) {
					cookieOptionStr += idx == null ? "," + option : (optionArr[0] + "|||" + optionArr[1] + "|||" + optionArr[2] + "|||" + ea);
				} else {
					cookieOptionStr += idx == null ?  option : (optionArr[0] + "|||" + optionArr[1] + "|||" + optionArr[2] + "|||" + ea);
				}
				
				Map<String, Object> listMap = new HashMap<String, Object>();
				listMap.put("goodsno", goodsno);
				// 현재 받아오는 옵션값이 없으나 DB에 옵션값으로 조회시 opt1 에 옵션값1 이라는 데이터가 있어 조회가 안되고 있음.
				//			 추후 상품개발시 참고하여 수정 요.
				listMap.put("opt1", opt1);
				listMap.put("opt2", opt2);
				cartVO.setOpt1(opt1);
				//cartVO.setOpt2(opt2);
				
				
				GoodsMemberCartListVO cartListVO = this.goodsMemberCartList(listMap);
				if(cartListVO == null) {
					cartListVO = new GoodsMemberCartListVO();
				}
				
				if("0".equals(cartListVO.getDeliveryType())) {
					
				};
				// 적립금
				int priceSum = this.getPriceSum(cartListVO, addopt);
				//int reserve = this.getReserve(priceSum);
				int reserve = 0;
				
				FrontGoodsCartsInnerVO inVO = new FrontGoodsCartsInnerVO();
				inVO.setGoodsnm(cartListVO.getGoodsnm());
				inVO.setImg(cartListVO.getImg());
				inVO.setDeliveryType(cartListVO.getDeliveryType());
				inVO.setUseEmoney(cartListVO.getUseEmoney());
				inVO.setGoodsDelivery(cartListVO.getGoodsDelivery());
				inVO.setPrice(cartListVO.getPrice());
				inVO.setPriceSum(String.valueOf(priceSum));
				inVO.setReserve(String.valueOf(reserve));
				inVO.setGoodsno(goodsno);
				inVO.setOpt1(opt1);
				inVO.setOpt2(opt2);
				inVO.setAddopt(addopt);
				inVO.setEa(ea);
				inVO.setGoodsCategory(cartListVO.getCategory());
				inVO.setOptNm(cartListVO.getOptNm());
				
				cartList.add(inVO);
				totalPrice += priceSum;
			}
			i++;
		}
		cartVO.setInVOList(cartList);
		cartVO.setTotalPrice(totalPrice);
		return cookieOptionStr;
	}
	
	public int getFrontTopGoodsSearchListCount(Map<String, Object> param){
		return mapper.getFrontTopGoodsSearchListCount(param);
	}
	
	public List<GdGoodsGoodsoptionGoodslink> getFrontTopGoodsSearchList(Map<String, Object> param){
		return mapper.getFrontTopGoodsSearchList(param);
	}
	
	/**
	 * 회원 직접 다운로드 쿠폰 발급
	 * @param mno
	 * @param grp
	 * @param couponcd
	 * @return
	 * @throws Exception
	 */
	public int setFrontCouponApply(FrontGoodsCouponVO couponVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ front downCoupon ajax >> service >> coupon apply >>");
			logger.debug("@@ mno >>"+couponVO.getMno()+" couponcd >> "+couponVO.getCouponcd());
		}
		//쿠폰 정보 조회(쿠폰다운로드횟수제한 : dncnt)
		
		//회원 해당 쿠폰 발급 내역 조회
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("mno", couponVO.getMno());
//		param.put("couponcd", couponVO.getCouponcd());
//		param.put("membertype", 2);			//회원타입(0:전체회원발급/1:그룹별발급/2:회원개별발급)
//		param.put("membergrpsno", 0);		//회원그룹번호(그룹별발급일 경우)
//		param.put("goodsno", couponVO.getGoodsno());
//		param.put("goodsnm", couponVO.getGoodsnm());
		
		couponVO.setMembertype(2);
		couponVO.setMembergrpsno(0);
		
		//쿠폰다운제한, 쿠폰발급내역 비교
		if(mapper.getFrontDownCouponCount(couponVO.getCouponcd()) > mapper.getFrontDownCouponCountForMember(couponVO)){
			//쿠폰 다운로드 횟수제한 > 해당 회원 쿠폰발급내역횟수	
			
			//쿠폰발급내역 일련번호 최대값 조회
			couponVO.setSno(mapper.getFrontCouponApplyMax()+1);
			if(logger.isDebugEnabled()){
				logger.debug("@@ coupon apply insert >> sno >>"+couponVO.getSno());
			}
			
			//쿠폰 발급
			mapper.insertFrontCouponApply(couponVO);
			mapper.insertFrontCouponApplyMember(couponVO);
			
			return 1;
		} else {
			//쿠폰 발급 제한
			return 0;
		}
	}
	/*
	 * 2020-02-11 이현빈 비회원키 생성
	 */
	public void getNonMemberKey(HttpSession session) throws Exception {
		//2020-02-11 이현빈 비회원 장바구니기능 세션키 추가
		long nano = System.currentTimeMillis();
		session.setAttribute("nonMemberKey", nano);
	}
	
	public void likeGoods(String goodsno, Boolean like ,int m_no){
		if(like){
			//insert
			mapper.insertLikeGoods(goodsno, m_no);
		}else{
			//delete
			mapper.deleteLikeGoods(goodsno, m_no);
		}
	}
	
	public List<Map<String,Object>> getGoodsInfo(){
		return mapper.getGoodsInfo();
	}
	
	public String[] getGoodsNameInfo() {
		return mapper.getGoodsNameInfo();
	};
	public String[] getBrandNameInfo(){
		return mapper.getBrandNameInfo();
	}
	
	public List<GdGoodsOption> getGoodsOptionList(FrontGoodsVO frontGoodsVO) {
		List<GdGoodsOption> opt2List = new ArrayList<GdGoodsOption>();
		for (Iterator<GdGoodsOption> iter = frontGoodsVO.getGoodsOptionList().iterator(); iter.hasNext();) {
			GdGoodsOption ggo = iter.next();
			if ("0".equals(ggo.getParent())) {
				frontGoodsVO.getGoodsView().setSno(ggo.getSno());
				frontGoodsVO.getGoodsView().setConsumer(ggo.getConsumer());
				frontGoodsVO.getGoodsView().setPrice(ggo.getPrice());
				frontGoodsVO.getGoodsView().setPriceRate(ggo.getPriceRate());
				frontGoodsVO.getGoodsView().setStock(Integer.parseInt(ggo.getStock()));
				iter.remove();
				break;
			}
		}
		
		if("1".equals(frontGoodsVO.getGoodsView().getUsegoodsadd()) && frontGoodsVO.getGoodsOptionList().size() > 0 ) {
			
			if("single".equals(frontGoodsVO.getGoodsView().getOpttype())) {
				
				for(GdGoodsOption list : frontGoodsVO.getGoodsOptionList()) {
					list.setOpt1(list.getOpt1() + " " + list.getOpt2());
				}
				
			}else if("int".equals(frontGoodsVO.getGoodsView().getOpttype())) {
				String optNm = "";
				String optNm2 = "";
				for(GdGoodsOption list : frontGoodsVO.getGoodsOptionList()) { 
					optNm = list.getOpt1();
					if(!optNm.equals(optNm2)) {
						optNm2 = optNm;
						opt2List.add(list);
					}
				}
				
			}
		}
		return opt2List;
	}
	
	public List<GdGoodsOption> getOpt2List(GdGoodsOption gdGoodsOption){
		return mapper.getOpt2List(gdGoodsOption);
	}
	
	public OrderMember selectFrontReviewInfo(Map<String, Object> param){
		return mapper.selectFrontReviewInfo(param);
	}
	
	
}