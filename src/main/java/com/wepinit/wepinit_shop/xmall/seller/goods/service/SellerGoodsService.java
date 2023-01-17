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
package com.wepinit.wepinit_shop.xmall.seller.goods.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.goods.dao.SellerGoodsMapper;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsFM;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsViewVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
@Transactional
public class SellerGoodsService {
	
	@Resource
	GoodsService goodsService;
	
	@Resource
	SellerGoodsMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(SellerGoodsService.class);
	
	private void insertSellerGoodsOption(Map<String,Object> map) {
		mapper.insertSellerGoodsOption(map);
	}
	
	public List<SellerGoodsViewVO> getGoodsOption(String goodsno){
		return mapper.getGoodsOption(goodsno);
	}
	
	/**
	 * 판매사별 상품요청 리스트 화면
	 * @return
	 */
	public void getSellerGoodsList(SellerGoodsFM sellerGoodsFM) throws Exception{
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
				
		// 세션의  판매사코드로  상품 총건수 조회
		sellerGoodsFM.setTotalCnt(mapper.getSellerGoodsListTotalCount(sellerGoodsFM));
		
		//상품 검색 총건수 조회
		sellerGoodsFM.setRowCount(mapper.getSellerGoodsListCount(sellerGoodsFM));
		
		//상품 검색 조회
		sellerGoodsFM.setSellerGoodsList(mapper.getSellerGoodsList(sellerGoodsFM));
		
	}
	
	/**
	 * 판매사별 상품 리스트 화면
	 * @return
	 */
	public void getGoodsList(SellerGoodsFM sellerGoodsFM) throws Exception{
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		// 세션의  판매사코드로  상품 총건수 조회
		sellerGoodsFM.setTotalCnt(mapper.getGoodsListTotalCount(sellerGoodsFM));
		
		//상품 검색 총건수 조회
		sellerGoodsFM.setRowCount(mapper.getGoodsListCount(sellerGoodsFM));
		
		//상품 검색 조회
		sellerGoodsFM.setSellerGoodsList(mapper.getGoodsList(sellerGoodsFM));
		
	}
	
	/**
	 * 판매사별 등록신청대기 목록
	 * @return
	 */
	public void getRegisterApprovalList(SellerGoodsFM sellerGoodsFM) throws Exception{
		
		sellerGoodsFM.setListViewCd("I");
		
		this.getSellerGoodsList(sellerGoodsFM);
			
	}
	
	/**
	 * 판매사별 등록신청 
	 * @return
	 */
	public void setRegApprovalReq(SellerGoodsFM sellerGoodsFM) throws Exception{
		SellerGoodsViewVO sellerGoodsViewVO = null;
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		sellerGoodsViewVO = mapper.getSellerGoodsInfo(sellerGoodsFM);
		
		if (null == sellerGoodsViewVO || 0 == sellerGoodsViewVO.getReqsno()){
			throw new BizException("seller.goods.00001"); // seller.goods.00001=존재하지 않는 상품입니다.
		}
		
		if (99 != sellerGoodsViewVO.getApprovalStatus()){
			throw new BizException("seller.goods.00004"); // seller.goods.00004=등록 신청 불가능한 상품입니다. 이 상품의 승인상태를 확인 하시기 바랍니다.
		}
		
		sellerGoodsFM.setSellerGoodsViewVO(sellerGoodsViewVO);
		
		// 상품 기본정보 복사
		mapper.setRegGoodsCopy(sellerGoodsFM);
		
		// 상품추가옵션항목 복사
		mapper.setRegGoodsAddCopy(sellerGoodsFM);
		
		// 상품가격정보 복사 
		mapper.setRegGoodsOptionCopy(sellerGoodsFM);
		
		// 상품분류정보 복사
		mapper.setRegGoodsLinkCopy(sellerGoodsFM);
		
		// 상품고시정보 복사
		mapper.setRegGoodsNotiCopy(sellerGoodsFM);
		
		// 판매사 추가옵션 테이블에 상품 번호 추가 작업 
		mapper.setRegSellerGoodsnoRegister(sellerGoodsFM);
		
		// 요청상품 상태 수정
		sellerGoodsFM.getSellerGoodsViewVO().setApprovalStatus(1);
		sellerGoodsFM.getSellerGoodsViewVO().setApprovalReqCd(1);
		
		mapper.setModSellerGoodsApprovalStatus(sellerGoodsFM);
		
		
	}
	
	/**
	 * 판매사별 수정신청대기 목록
	 * @param sellerGoodsFM
	 * @return
	 */
	public void getModifyApprovalList(SellerGoodsFM sellerGoodsFM) throws Exception{
		
		sellerGoodsFM.setListViewCd("U");
		
		this.getGoodsList(sellerGoodsFM);
		
	}
	
	/**
	 * 판매사별 수정 신청
	 * @return
	 */
	public void setModApprovalReq(SellerGoodsFM sellerGoodsFM, HttpServletRequest req, MultipartHttpServletRequest mhsq) throws Exception{
		SellerGoodsViewVO sellerGoodsViewVO = null;
		SellerGoodsViewVO sellerGoodsViewVO2 = null;
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		sellerGoodsViewVO = mapper.getGoodsInfo(sellerGoodsFM);
		// 등록과 똑같은 프로세스 복사	
		sellerGoodsViewVO2 = mapper.getSellerGoodsInfo(sellerGoodsFM);
		
		if (null == sellerGoodsViewVO || 0 == sellerGoodsViewVO.getGoodsno()){
			throw new BizException("seller.goods.00001"); // seller.goods.00001=존재하지 않는 상품입니다.
		}
		
		if (1 == sellerGoodsViewVO.getApprovalStatus() || 1 == sellerGoodsViewVO.getReqAprlStat()){
			throw new BizException("seller.goods.00006"); // seller.goods.00006=승인 요청 중인 상품입니다. 이 상품의 승인상태를 확인 하시기 바랍니다.
		}
		
		this.goodsDB(sellerGoodsFM, req, mhsq);
		
		sellerGoodsFM.setSellerGoodsViewVO(sellerGoodsViewVO);
		
		int goodsno = Integer.parseInt(sellerGoodsFM.getGoodsno());
		
		// goods_option delete
		mapper.deleteGoodsOption(goodsno);
				
		// 상품가격정보 복사  goods_option
		mapper.setRegGoodsOptionCopy(sellerGoodsFM);
		
		// goods_link delete
		mapper.deleteGoodsLinkAll(goodsno);
		
		// 상품분류정보 복사 goods_link
		mapper.setRegGoodsLinkCopy(sellerGoodsFM);
		
		// 상품고시정보 복사 * 현재는 사용하지 않음
		//mapper.setRegGoodsNotiCopy(sellerGoodsFM);
		
		// 요청상품 상태 수정
		sellerGoodsFM.getSellerGoodsViewVO().setApprovalStatus(1);
		sellerGoodsFM.getSellerGoodsViewVO().setApprovalReqCd(2);
		
		mapper.setModSellerGoodsApprovalStatus(sellerGoodsFM);
		
		
	}
	
	/**
	 * 판매사별 삭제신청대기 목록
	 * @param sellerGoodsFM
	 * @return
	 */
	public void getDeleteApprovalList(SellerGoodsFM sellerGoodsFM) throws Exception{
		
		sellerGoodsFM.setListViewCd("D");
		
		this.getGoodsList(sellerGoodsFM);
		
	}
	
	/**
	 * 판매사별 수정 신청
	 * @return
	 */
	public void setDelApprovalReq(SellerGoodsFM sellerGoodsFM) throws Exception{
		SellerGoodsViewVO sellerGoodsViewVO = null;
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		sellerGoodsViewVO = mapper.getGoodsInfo(sellerGoodsFM);
		
		if (null == sellerGoodsViewVO || 0 == sellerGoodsViewVO.getGoodsno()){
			throw new BizException("seller.goods.00001"); // seller.goods.00001=존재하지 않는 상품입니다.
		}
		
		if (1 == sellerGoodsViewVO.getApprovalStatus() || 1 == sellerGoodsViewVO.getReqAprlStat()){
			throw new BizException("seller.goods.00006"); // seller.goods.00006=승인 요청 중인 상품입니다. 이 상품의 승인상태를 확인 하시기 바랍니다.
		}
		
		sellerGoodsFM.setSellerGoodsViewVO(sellerGoodsViewVO);
		
		// 요청상품 상태 수정
		sellerGoodsFM.getSellerGoodsViewVO().setApprovalStatus(1);
		sellerGoodsFM.getSellerGoodsViewVO().setApprovalReqCd(3);
		
		mapper.setModSellerGoodsApprovalStatus(sellerGoodsFM);
		
		
	}
	
	/**
	 * 판매사별 iframe 상품 리스트 화면
	 * @return
	 */
	public void getGoodsIframeList(SellerGoodsFM sellerGoodsFM) throws Exception{
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		sellerGoodsFM.setSchAprlStat("2");	// 승인된 상품만
		
		//상품 검색 총건수 조회
		sellerGoodsFM.setRowCount(mapper.getGoodsListCount(sellerGoodsFM));
		
		//상품 검색 조회
		sellerGoodsFM.setSellerGoodsList(mapper.getGoodsList(sellerGoodsFM));
		
	}
	
	/**
	 * 상품고시 등록
	 * @param sellerGoodsFM
	 * @return
	 * @throws Exception
	 */
	public void getGoodsRegister(SellerGoodsFM sellerGoodsFM) throws Exception {
		int i = 0;
		String procType = "";
		String paramReqsno = "";
		String paramGoodsno = "";
		
		SellerGoodsViewVO sellerGoodsViewVO = null;
		
		procType = "I";
		paramReqsno = sellerGoodsFM.getReqsno();
		paramGoodsno = sellerGoodsFM.getGoodsno();
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
				
		// 상품일련번호 != "" 인 경우 상품 수정모드로 판단 
		if (!"".equals(paramGoodsno)) {
			procType = "U";
			
			sellerGoodsViewVO = mapper.getGoodsInfo(sellerGoodsFM);
			if (null == sellerGoodsViewVO || 0 == sellerGoodsViewVO.getGoodsno()) {
				throw new BizException("seller.goods.00001"); // seller.goods.00001=존재하지 않는 상품입니다.
			}
			//if (2 == sellerGoodsViewVO.getApprovalStatus()){
			//	throw new BizException("seller.goods.00006"); // seller.goods.00006=승인 요청 중인 상품입니다. 이 상품의 승인상태를 확인 하시기 바랍니다.
			//}
			
			// 관련 상품 세팅
			if ("1".equals(sellerGoodsViewVO.getRelationis()) && !"".equals(StringUtil.N2S(sellerGoodsViewVO.getRelation()))) {
				sellerGoodsFM.setGoodsRelationList(mapper.getGoodsRelationList(sellerGoodsViewVO.getRelation().split(", ")));
			}
			
			//상품 추가 옵션 목록
			if ("false".equals(ShopConfig.getProperty("rental_free")) && !"".equals(StringUtil.nullConv(sellerGoodsViewVO.getAddoptnm()))) {
				sellerGoodsFM.setGoodsAddInfoList(mapper.getGoodsAddInfoList(paramGoodsno));
			}
			
			// 상품요청기본정보
			sellerGoodsFM.setSellerGoodsViewVO(sellerGoodsViewVO);
			
			// 상품요청고시정보 조회
			sellerGoodsFM.setGoodsNotiList(mapper.getReqGoodsNotiList(sellerGoodsFM));
						
			// 상품분류정보 조회
			sellerGoodsFM.setCategoryList(mapper.getCategoryLinkList(paramGoodsno));
			
			String[] optNmArray = new String[]{"", ""};	// 옵션명1, 옵션명2
			if (!"".equals(sellerGoodsViewVO.getOptnm())) {
				optNmArray = StringUtil.split(sellerGoodsViewVO.getOptnm(), "|");
				if (0 >= optNmArray.length) {
					optNmArray = new String[]{"", ""};
				}
				sellerGoodsViewVO.setOptNmArray(optNmArray);
			}
			
			List<SellerGoodsViewVO> sellerGoodsOptionList = mapper.getGoodsOption(paramGoodsno);
			String [] opt2Nm = StringUtil.split(StringUtil.nvl(sellerGoodsViewVO.getOptnm(), "") , "|");
			if (opt2Nm.length > 0 && null != opt2Nm && sellerGoodsOptionList.size() > 0) {
				String [] stocks = new String[opt2Nm.length];
				String [] opt2Stock = null;
				
				for (SellerGoodsViewVO ggo : sellerGoodsOptionList) {
					opt2Stock = StringUtil.split(ggo.getStock(), "|");
					for (i = 0; i < stocks.length; i++) {
						stocks[i] = opt2Stock[i];
					}
					ggo.setStocks(stocks);
					stocks = new String[opt2Nm.length];
					ggo.setStock("0");
				}
			}
			sellerGoodsFM.setGoodsOptionList(sellerGoodsOptionList);
		
		// 상품요청일련번호 != "" 인 경우 상품요청 수정모드로 판단
		} else if (!"".equals(paramReqsno)) {
			procType = "U";
			
			sellerGoodsViewVO = mapper.getSellerGoodsInfo(sellerGoodsFM);
			if (null == sellerGoodsViewVO || 0 == sellerGoodsViewVO.getReqsno()) {
				throw new BizException("seller.goods.00001"); // seller.goods.00001=존재하지 않는 상품입니다.
			}
			//if (99 != sellerGoodsViewVO.getApprovalStatus()) {
			//	throw new BizException("seller.goods.00005"); // seller.goods.00005=등록 신청 중이거나 이미 승인/반려/승인취소 된 상품 입니다. 이 상품의 승인상태를 확인 하시기 바랍니다.
			//}
			
			// 관련 상품 세팅
			if ("1".equals(sellerGoodsViewVO.getRelationis()) && !"".equals(StringUtil.N2S(sellerGoodsViewVO.getRelation()))) {
				sellerGoodsFM.setGoodsRelationList(mapper.getGoodsRelationList(sellerGoodsViewVO.getRelation().split(", ")));
			}
			
			//상품 추가 옵션 목록
			if ("false".equals(ShopConfig.getProperty("rental_free")) && !"".equals(StringUtil.nullConv(sellerGoodsViewVO.getAddoptnm()))) {
				sellerGoodsFM.setGoodsAddInfoList(mapper.getSellerGoodsAddInfoList(paramReqsno));
			}
			
			// 상품요청기본정보
			sellerGoodsFM.setSellerGoodsViewVO(sellerGoodsViewVO); //추가옵션 기본정보
			
			// 상품요청고시정보 조회
			sellerGoodsFM.setGoodsNotiList(mapper.getReqGoodsNotiList(sellerGoodsFM));
			
			// 상품분류정보 조회
			sellerGoodsFM.setCategoryList(mapper.getSellerCategoryLinkList(paramReqsno));
			
			String[] optNmArray = new String[]{"", ""};	// 옵션명 1, 2
			if (!"".equals(sellerGoodsViewVO.getOptnm())) {
				optNmArray = StringUtil.split(sellerGoodsViewVO.getOptnm(), "|");
				if (0 >= optNmArray.length){
					optNmArray = new String[]{"", ""};
				}
				sellerGoodsViewVO.setOptNmArray(optNmArray);
			}
			
			List<SellerGoodsViewVO> sellerGoodsOptionList = mapper.getSellerGoodsOption(paramReqsno);
			String [] opt2Nm = StringUtil.split(StringUtil.nvl(sellerGoodsViewVO.getOptnm(), "") , "|");
			if (opt2Nm.length > 0 && null != opt2Nm  && sellerGoodsOptionList.size() > 0) {
				String [] stocks = new String[opt2Nm.length];
				String [] opt2Stock = null;
				
				for (SellerGoodsViewVO ggo : sellerGoodsOptionList) {
					opt2Stock = StringUtil.split(ggo.getStock(), "|");
					for (i = 0; i < stocks.length; i++) {
						stocks[i] = opt2Stock[i];
					}
					ggo.setStocks(stocks);
					stocks = new String[opt2Nm.length];
					ggo.setStock("0");
					
				}
			}
			sellerGoodsFM.setGoodsOptionList(sellerGoodsOptionList);
		}
		
		// Process type
		sellerGoodsFM.setProcType(procType);
		
		// 원산지목록
		sellerGoodsFM.setOriginList(goodsService.getOriginList());
		
		// 브랜드목록
		sellerGoodsFM.setBrandList(goodsService.getBrandList());
	}
	
	
	/**
	 * 판매사별고시맵핑정보 등록
	 * @param sellerGoodsFM
	 * @return
	 * @throws Exception
	 */
	public void setSellerGoodsNoti(SellerGoodsFM sellerGoodsFM) throws Exception{
		int result = 0;
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		result = mapper.setRegGoodsNotiSeller(sellerGoodsFM);
		
		if (0 >= result){
			throw new BizException("common.00004");
		}
	}
	
	/**
	 * 판매사별 상품고시정보 조회
	 * @param sellerGoodsFM
	 * @return
	 * @throws Exception
	 */
	public void getSellerGoodsNoti(SellerGoodsFM sellerGoodsFM) throws Exception{
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		sellerGoodsFM.setGoodsNotiList(mapper.getSellerGoodsNotiList(sellerGoodsFM));
	}
	
	/**
	 * 요청 상품 복사
	 * @param sellerGoodsFM
	 * @return
	 * @throws Exception
	 */
	public void setRegSellerGoodsCopy(SellerGoodsFM sellerGoodsFM) throws Exception{
		SellerGoodsViewVO sellerGoodsViewVO = null;
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		sellerGoodsViewVO = mapper.getSellerGoodsInfo(sellerGoodsFM);
		
		if (null == sellerGoodsViewVO || 0 == sellerGoodsViewVO.getReqsno()){
			throw new BizException("seller.goods.00001"); // seller.goods.00001=존재하지 않는 상품입니다.
		}
		sellerGoodsFM.setGoodsno("".equals(sellerGoodsFM.getGoodsno()) ? "0" : sellerGoodsFM.getGoodsno());
		// 요청 상품 기본정보 복사
		mapper.setRegSellerGoodsCopy(sellerGoodsFM);
		
		// 판매사요청추가옵션항목 복사
		mapper.setRegSellerGoodsAddCopy(sellerGoodsFM);
		
		// 판매사요청상품가격정보 복사 
		mapper.setRegSellerGoodsOptionCopy(sellerGoodsFM);
		
		// 판매사요청상품분류정보 복사
		mapper.setRegSellerGoodsLinkCopy(sellerGoodsFM);
		
		// 상품고시정보 복사
		mapper.setRegSellerGoodsNotiCopy(sellerGoodsFM);
		
	}
	
	/**
	 * 요청 상품 삭제
	 * @param sellerGoodsFM
	 * @return
	 * @throws Exception
	 */
	public void setDelSellerGoodsDel(SellerGoodsFM sellerGoodsFM) throws Exception{
		SellerGoodsViewVO sellerGoodsViewVO = null;
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD"));
		
		sellerGoodsViewVO = mapper.getSellerGoodsInfo(sellerGoodsFM);
		
		if (null == sellerGoodsViewVO || 0 == sellerGoodsViewVO.getReqsno()){
			throw new BizException("seller.goods.00001"); // seller.goods.00001=존재하지 않는 상품입니다.
		}
		
		if (99 != sellerGoodsViewVO.getApprovalStatus()){
			throw new BizException("seller.goods.00003"); // seller.goods.00003=삭제 불가능한 상품입니다. 이 상품의 승인상태를 확인 하시기 바랍니다.
		}
		
		mapper.setDelSellerGoods(sellerGoodsFM.getReqsno());
	}
	
	
	/**
	 * 상품 등록/수정
	 * @param sellerGoodsFM
	 * @param req
	 * @param mhsq
	 * @throws Exception
	 */
	public void goodsDB(SellerGoodsFM sellerGoodsFM, HttpServletRequest req, MultipartHttpServletRequest mhsq) throws Exception {
		List<String> deleteTempFileList = new ArrayList<String>();
		AwsFileUtil awsfileUtil = new AwsFileUtil();
		
		int rstCnt 	= 0;

		String tmp 			= "";	//등록할 상품이미지 파일명 임시
		String imgNm		= "";
		String hiddenFL		= "0";	//hidden 여부
		String optNm		= "";
		String addOptNm		= "";	//추가옵션명

		String[] arrTmp			= null;
		String[] arrTmp1		= null;
		String[][] arrImg		= new String[][]{{"img_l", ""}, {"img_m", ""}, {"img_s", ""}, {"img_i", ""}};	//상품이미지 타입
		String[] category		= null;	//상품카테고리
		String[] eRefer			= null;	//관련상품

		// 추가옵션
		String[] addOptionNm	= null;
		String[] addOptionOpt	= null;
		String[] addOptionPrice	= null;

		HashMap<String, Object> paramsDb = new HashMap<String,Object>();	//DB처리를 위한 map
		String orgImgNames = "";	//기등록된 상품 이미지 명
		
		int reqsno = StringUtil.N2I(req.getParameter("reqsno"));
		int goodsNo = StringUtil.N2I(req.getParameter("goodsno"));
		String goodsnm = req.getParameter("goodsnm");
		
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject();
		String m_id = String.valueOf(sellerSO.getUserInfo().getUserId());
		
		// 세션의  판매사 코드 세팅
		sellerGoodsFM.setSsSellerCd(sellerSO.getLoginValue("SELLER_CD"));
		
		//파라미터를 로깅으로 확인하기 위한 처리
		boolean isRegisterParamLogging = false;
		if (isRegisterParamLogging) {
			Enumeration enumber = req.getParameterNames();
			logger.debug("choilee-파라미터 로깅");
			
			int deleteIdx = 0;
			while (enumber.hasMoreElements()) {
				String key = enumber.nextElement().toString();
				String value = req.getParameter(key);

				logger.debug("log.enumber.hasMoreElements key :"  + key + " : " +value);
			}
		}
		
		if ("I".equals(req.getParameter("procType"))) {
			//등록수 제한 체크
			String maxGoods = ShopConfig.getProperty("maxGoods");
			if (!"unlimited".equals(maxGoods) && Integer.parseInt(maxGoods) < mapper.getSellerGoodsListTotalCount(sellerGoodsFM)) {
				throw new Exception("상품수 등록이 제한되었습니다");	
			}
			
			//상품일련번호
			reqsno = mapper.getReqsnoMaxCount() +1;
			paramsDb.clear();

			paramsDb.put("reqsno", reqsno);
			paramsDb.put("goodsno", goodsNo);
			paramsDb.put("goodscd", "");
			paramsDb.put("goodsnm", "");
			paramsDb.put("goodsnmKR", "");
			paramsDb.put("goodsnmCN", "");
			paramsDb.put("brandno", StringUtil.nullConv(req.getParameter("brandno"), "0"));
			paramsDb.put("origin", "");
			paramsDb.put("binCd", "");
			paramsDb.put("simnCd", "");
			paramsDb.put("seasonNm", "");
			paramsDb.put("euYn", StringUtil.nullConv(req.getParameter("euYn"), "Y"));
			paramsDb.put("shippingOrigin", "");
			paramsDb.put("metaTitle", "");
			paramsDb.put("keyword", "");
			paramsDb.put("open", StringUtil.nullConv(req.getParameter("open"), "0"));
			paramsDb.put("exTitle", "");
			paramsDb.put("ex1", "");
			paramsDb.put("ex2", "");
			paramsDb.put("ex3", "");
			paramsDb.put("ex4", "");
			paramsDb.put("ex5", "");
			paramsDb.put("ex6", "");
			paramsDb.put("useEmoney", "");
			paramsDb.put("usestock", "");
			paramsDb.put("runout", StringUtil.nullConv(req.getParameter("runout"), "0"));
			paramsDb.put("tax", StringUtil.nullConv(req.getParameter("tax"), "1"));
			paramsDb.put("strprice", "");
			paramsDb.put("deliveryType", StringUtil.nullConv(req.getParameter("deliveryType"), "0"));
			paramsDb.put("goodsDelivery", "");
			paramsDb.put("usegoodsadd", "");
			paramsDb.put("optnm", "");
			paramsDb.put("opttype", StringUtil.nullConv(req.getParameter("opttype"), "single"));
			paramsDb.put("useadd", "");
			paramsDb.put("addoptnm", "");
			paramsDb.put("relationis", StringUtil.nullConv(req.getParameter("relationis"), "0"));
			paramsDb.put("relation", "");
			paramsDb.put("imgI", "");
			paramsDb.put("imgS", "");
			paramsDb.put("imgL", "");
			paramsDb.put("imgM", "");
			paramsDb.put("shortdesc", "");
			paramsDb.put("longdesc", "");
			paramsDb.put("memo", "");
			paramsDb.put("mId", StringUtil.nullConv(m_id, "0"));
			paramsDb.put("sellerCd", sellerGoodsFM.getSsSellerCd());
			paramsDb.put("coupon", "");
			paramsDb.put("couponEa", StringUtil.nullConv(req.getParameter("couponEa"), "0"));
			paramsDb.put("couponDate", "");
			paramsDb.put("couponUsecnt", "");
			
			if (logger.isDebugEnabled()) {
				logger.debug("#uclee param >>>>>>>>>> {}", paramsDb);
			}
			
			mapper.setRegSellerGoods(paramsDb);
		} else if ("U".equals(req.getParameter("procType"))) {
			//상품 카테고리 - 모두 삭제후 재등록
			mapper.setDelSellerGoodsLinkAll(reqsno);
			
			//가격옵션 - 모두 삭제후 재등록
			mapper.setDelSellerGoodsOptionAll(reqsno);
		} 
		
		//상품 카테고리 링크 연결 등록
		category=req.getParameterValues("category[]");
		if (null != category && 0 < category.length) {
			int categoryLen = category.length;
			for (int i = 0; i < categoryLen; i++) {
				hiddenFL = (0 < ShopLibFunction.getCateHideCnt(category[i])) ? "1" : "0";
				paramsDb.clear();
				paramsDb.put("goodsno", goodsNo);
				paramsDb.put("reqsno", reqsno);
				paramsDb.put("category", category[i]);
				paramsDb.put("hidden", hiddenFL);
				mapper.setRegSellerGoodsLink(paramsDb);
			}
		}
		
		//가격옵션 추가하기 - 옵션명 세팅
		arrTmp = req.getParameterValues("optnm[]");
		if (null != arrTmp) {
			for (int i = 0; i < arrTmp.length; i++) {
				optNm += arrTmp[i] + "|";
			}
			optNm = optNm.substring(0, optNm.length() - 1);
		} else {
			optNm = "";
		}
		
		//가격옵션 추가하기
		String stockTmp = "";
		String [] option1			= req.getParameterValues("opt1[]");
		String [] option2			= req.getParameterValues("opt2[]");
		String [] optionConsumer	= req.getParameterValues("option[consumer][]");
		String [] optionPrice		= req.getParameterValues("option[price][]");
		String [] optionPriceRate	= req.getParameterValues("option[priceRate][]");
		String [] optionSupply		= req.getParameterValues("option[supply][]");
		String [] optionSupply2		= req.getParameterValues("option[supply2][]");
		String [] optionMargin		= req.getParameterValues("option[margin][]");
		String [] optionStock		= req.getParameterValues("option[stock][]");
		String [] optionExplain		= req.getParameterValues("option[optexplain][]");
		String [] option1Stock		= req.getParameterValues("option[opt1Stock][]");
		
		arrTmp = req.getParameterValues("opt2[]");
		String opttype = StringUtil.nullConv(req.getParameter("opttype"), "single");
		int optIdx = 0;
		String goodsOpt2Nm = "";
		String goodsOptNm = "";
		
		if (null != arrTmp) {
			for (String s : arrTmp) {
				goodsOpt2Nm += s + "|";
			}
			goodsOpt2Nm = goodsOpt2Nm.substring(0, goodsOpt2Nm.length() - 1);
		} else {
			goodsOpt2Nm = "";
		}
		
		if (null != optionPrice) {
			//빈값은 제거하기위해넣음
			for (int i = 0; i < option1.length; i++) {
				//추가가격 옵션의 각 로우마다 넘어오는 재고필드의 배열을 추출
				//옵션2가 있는경우와 없는경우로 나눈다.
				if (null != option2) {
					//옵션2 반복문 시작 
					for (int j = 0; j < option2.length; j++) {
						paramsDb.clear();
						goodsOptNm = option1[i];
						//옵션이름에 option2 이름 추가
						goodsOptNm += ("".equals(StringUtil.nullConv(option2[j], ""))) ? "" : "/" + option2[j];
						paramsDb.put("goodsno",		0 == (goodsNo) ? reqsno : goodsNo);
						paramsDb.put("opt1",		option1[i]);
						paramsDb.put("opt2",		option2[j]);	
						paramsDb.put("consumer",	("".equals(StringUtil.nullConv(optionConsumer[i], ""))) ? "0" : optionConsumer[i]);
						paramsDb.put("price",		("".equals(StringUtil.nullConv(optionPrice[i], ""))) ? "0" : optionPrice[i]); 
						paramsDb.put("priceRate",	("".equals(StringUtil.nullConv(optionPriceRate[i], ""))) ? "0" : optionPriceRate[i]); 
						paramsDb.put("supply",		("".equals(StringUtil.nullConv(optionSupply[i], ""))) ? "0" : optionSupply[i]);
						paramsDb.put("supply2",		("".equals(StringUtil.nullConv(optionSupply2[i], ""))) ? "0" : optionSupply2[i]);
						paramsDb.put("margin",		("".equals(StringUtil.nullConv(optionMargin[i], ""))) ? "0" : optionMargin[i]);
						paramsDb.put("optexplain",	("".equals(StringUtil.nullConv(optionExplain[i], ""))) ? "" : optionExplain[i]);
						paramsDb.put("parent",		"1");
						if (i == j)
							paramsDb.put("link",	"1");
						else
							paramsDb.put("link",	"0");
						
						stockTmp = StringUtil.nvl(optionStock[optIdx] , "");
						
						paramsDb.put("optno",		"");
						paramsDb.put("stock",		("".equals(stockTmp) ? 0 : stockTmp));
						
						if (optIdx < optionStock.length) {
							optIdx ++;
						}
						
						// 필수옵션등록
						insertSellerGoodsOption(paramsDb);
					}
				} else {
					//일체형 분리형 체크를 하더라도 옵션2가없는경우에는 single형으로 저장 
					opttype = "single";
					//옵션1의 재고량만 저장 .
					paramsDb.clear();
					paramsDb.put("goodsno",		0 == (goodsNo) ? reqsno : goodsNo);
					paramsDb.put("opt1",		("".equals(StringUtil.nullConv(option1[i], ""))) ? "" : option1[i]);
					paramsDb.put("opt2",		"");
					paramsDb.put("consumer",	("".equals(StringUtil.nullConv(optionConsumer[i], ""))) ? "0" : optionConsumer[i]);
					paramsDb.put("price",		("".equals(StringUtil.nullConv(optionPrice[i], ""))) ? "0" : optionPrice[i]);
					paramsDb.put("priceRate",	("".equals(StringUtil.nullConv(optionPriceRate[i], ""))) ? "0" : optionPriceRate[i]);
					paramsDb.put("supply",		("".equals(StringUtil.nullConv(optionSupply[i], ""))) ? "0" : optionSupply[i]);
					paramsDb.put("supply2",		("".equals(StringUtil.nullConv(optionSupply2[i], ""))) ? "0" : optionSupply2[i]);
					paramsDb.put("margin",		("".equals(StringUtil.nullConv(optionMargin[i], ""))) ? "0" : optionMargin[i]);
					paramsDb.put("stock",		option1Stock[i]);
					paramsDb.put("link",		"1");
					paramsDb.put("parent",		"1");
					paramsDb.put("optno",		"");
					paramsDb.put("optexplain",	("".equals(StringUtil.nullConv(optionExplain[i], ""))) ? "" : optionExplain[i]);
					
					//필수옵션등록
					insertSellerGoodsOption(paramsDb);
				}
			}
		}
		
		//가격옵션 추가하기
		sellerGoodsFM.getSellerGoodsViewVO().setReqsno(reqsno);
		mapper.setRegSellerGoodsOption(sellerGoodsFM);
		
		//배송비 데이터 수정
		String deliverytype = StringUtil.nullConv(req.getParameter("deliveryType"), "");
		String goodsdelivery = "0";
		if ("3".equals(deliverytype)) {
			goodsdelivery= StringUtil.nullConv(req.getParameter("goodsDelivery2"), "0");
		} else if ("2".equals(deliverytype)) {
			goodsdelivery=StringUtil.nullConv(req.getParameter("goodsDelivery"), "0");
		}
		logger.debug(">>>>>>type>>>>>>>>>>>"+deliverytype);
			
		//추가옵션/추가상품/사은품
		addOptionNm = req.getParameterValues("addoptnms");
		addOptNm = "";

		//추가옵션 사용 여부
		String useadd = req.getParameter("useadd");
		//추가 옵션 사용에 체크되어 있더라도 option의 값이 없으면 사용 안함
		if (null == addOptionNm){
			useadd = "0";	
		} 
		
		if (null != addOptionNm && 0 < addOptionNm.length) {
			//삭제 - 사용/사용안함으로 존재하므로 무조건 지우지 말고 입력데이터가 없을 경우만 삭제하도록 한다.
			mapper.setDelSellerGoodsAddAll(reqsno);
			
			//등록
			int addOptionNmLen = addOptionNm.length;
			logger.debug(">>>>>>>>>addOptionNmLen>>>>>>>>>>" + addOptionNmLen);
			for (int i = 0; i < addOptionNmLen; i++) {
				rstCnt = 0;
				addOptionOpt = req.getParameterValues("addopt[opt][" + i + "][]");
				addOptionPrice = req.getParameterValues("addopt[addprice][" + i + "][]");
				logger.debug(">>>>>>>>>addOptionOpt>>>>>>>>>>addopt[opt][" + i + "][]@@" + addOptionOpt);
				logger.debug(">>>>>>>>>addOptionOpt>>>>>>>>>>addopt[addprice][" + i + "][]@@" + addOptionPrice);
				if (null != addOptionNm[i] && !"".equals(addOptionNm[i])) {
					logger.debug(">>>>>>>>addOptionNm[" + i + "]>>>>>>>>::" + addOptionNm[i]);
					int addOptionOptLen = addOptionOpt.length;
					for (int j = 0; j < addOptionOptLen; j++) {
						paramsDb.clear();
						paramsDb.put("reqsno", reqsno);
						paramsDb.put("step", String.valueOf(i));
						paramsDb.put("opt", ("".equals(StringUtil.nullConv(addOptionOpt[j], ""))) ? "" : addOptionOpt[j]);
						paramsDb.put("addprice", ("".equals(StringUtil.nullConv(addOptionPrice[j], ""))) ? "0" : addOptionPrice[j]);
						paramsDb.put("goodsno", goodsNo);
						rstCnt = mapper.setRegSellerGoodsAdd(paramsDb);
					}
					
					if (0 < rstCnt)
						addOptNm += addOptionNm[i] + "^" + StringUtil.nullConv(req.getParameter("addoptreq[" + i + "]"), "") + "|";
				}
			}
			
			if (!"".equals(addOptNm))
				addOptNm = addOptNm.substring(0, addOptNm.length() - 1);
		}
		logger.debug(">>>>addOptNm>>>>>>>>>>::" + addOptNm);
		
		//상품 고시 등록
		sellerGoodsFM.setReqsno(String.valueOf(reqsno));
		
		//상품추가정보 세팅
		tmp = "";
		for (int i = 0; i < 6; i++) {
			tmp += StringUtil.nullConv(req.getParameter("title" + (i + 1)), "") + "|";
		}
		String ex_title = "".equals(tmp) ? "" : tmp.substring(0, tmp.length() - 1);
			
		//관련상품
		eRefer = req.getParameterValues("e_refer[]");
		String tmpERefer = "";
		String relation = "";
		if (null != eRefer && 0 < eRefer.length) {
			for (int i = 0; i < eRefer.length; i++ ) {
				tmpERefer += eRefer[i] + ",";
			}
			tmpERefer = ("".equals(tmpERefer)) ? "" : tmpERefer.substring(0, tmpERefer.length() - 1);
			relation = tmpERefer;
		}
			
		//상품 이미지
		
		//업로드 이미지 세팅
		//삭제할 파일명을 map데이터로 담는다.	
		//상품 이미지 타입 별로 삭제 파일명을 취한다.
		int deleteIdx = 0; //삭제 할 파일명의 element's name이 순차적이지 않아 정렬을 위한 변수 선언
		Enumeration enumber = req.getParameterNames();
		HashMap<String, Object> deletFileMap = new HashMap<String, Object>(); //삭제할 이미지명 
		while (enumber.hasMoreElements()) {
			String key = enumber.nextElement().toString();
			String value = req.getParameter(key);
			for (int i = 0; i < 4; i++) {
				tmp = "";
				imgNm = arrImg[i][0] + "[]";
				int j = 0;
				if (key.matches(".*del\\[" + arrImg[i][0] + "\\].*")) {
					value = req.getParameter(key);
					String delFiletmp = key.substring(key.length() - 1, key.length());
					deletFileMap.put(arrImg[i][0] + deleteIdx, value);
					deleteIdx++;
				}
			}
		}
		
		//업로드할 상품 이미지 파일을 Map데이터로 담는다.
		java.util.Iterator<String> fileNames = mhsq.getFileNames();
		Map<String, ArrayList<String>> uploadFileMap = new HashMap<String, ArrayList<String>>();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mf = mhsq.getFile(fileName);
			ArrayList<String> orgFileName = new ArrayList<String>();
			orgFileName.add(mf.getOriginalFilename());
			uploadFileMap.put(fileName, orgFileName);
		}
		
		//상품이미지 타입은 4종
		for (int i = 0; i < 4; i++) { 
			tmp = "";
			imgNm = arrImg[i][0] + "[]";	//_form.jsp에서 file element's name 형식 imgNm+숫자(순차증가)
			int j = 0;
			orgImgNames = req.getParameter("orgNames_" + arrImg[i][0]);	//기등록된 상품이미지 데이터
			for (int k = 0; k < deletFileMap.size(); k++) {
				//기등록된 상품 이미지에서 삭제이미지 파일명을 제거한다.
				if (deletFileMap.get(arrImg[i][0] + k) != null) {
					orgImgNames = orgImgNames.replaceAll(((String) deletFileMap.get(arrImg[i][0] + k)).replaceAll("\\[", "\\\\["), "");
				}
			}
			if (!"".equals(orgImgNames)) {
				tmp = orgImgNames;
			}
			
			//상품 이미지 업로드 데이터 생성((기존데이터-삭제데이터)+추가데이터)
			for (int k = 0; k < uploadFileMap.size(); k++) {
				if (uploadFileMap.get(imgNm + k) != null && uploadFileMap.get(imgNm + k).get(0) != "") {
					String fileName = ShopLibFunction.getUniqueKey() + uploadFileMap.get(imgNm + k).get(0);
					uploadFileMap.get(imgNm + k).add(fileName);
					tmp += "|" + fileName;
				}
			}
			
			String[] tempOrgImgName = tmp.split("\\|");
			String tempOrgImgNAme = "";
			for (int k = 0; k < tempOrgImgName.length; k++) {
				if (!tempOrgImgName[k].equals("")) {
					tempOrgImgNAme += "|" + tempOrgImgName[k];
				}
			}
			if (!"".equals(tempOrgImgNAme)) {
				tmp = tempOrgImgNAme;
			}
			arrImg[i][1] = tmp;
		}
		
		//최종 상품 등록 및 수정
		List<String> img_m = new ArrayList<>();
		if (uploadFileMap != null) {
			if (!arrImg[1][1].isEmpty()) {
				for (String fl : arrImg[1][1].split("\\|")) {
					if (fl.indexOf("http://") != -1 || fl.indexOf("https://") != -1) {
						img_m.add(fl);
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < uploadFileMap.size(); j++) {
					if (uploadFileMap.get(arrImg[i][0] + "[]" + j) != null && uploadFileMap.get(arrImg[i][0] + "[]" + j).get(0) != null && uploadFileMap.get(arrImg[i][0] + "[]" + j).get(0) != "") {
						List<MultipartFile> mf = mhsq.getFiles(arrImg[i][0] + "[]" + j);
						for (int k = 0; k < mf.size(); k++) {
							if (uploadFileMap.get(arrImg[i][0] + "[]" + j).get(1) != null && !"".equals(uploadFileMap.get(arrImg[i][0] + "[]" + j).get(1))) {
								//파일 임시경로에 업로드
								File tempFile = FileUtil.tempUploadFile(uploadFileMap.get(arrImg[i][0] + "[]" + j).get(1), mf.get(k).getBytes());
								if (tempFile.exists()) {
									String key = "goods/" + req.getParameter("goodscd") + "/" + tempFile.getName();
									PutObjectResult awsResult = awsfileUtil.upload(tempFile, key);
									if (StringUtils.isNotBlank(awsResult.getVersionId())) {
										String imgUrl = StringUtil.getNotParamURL(awsfileUtil.getFileURL(key));
										if (StringUtils.equals("img_m", arrImg[i][0])) {
											img_m.add(imgUrl);
										} else {
											//이미지 URL 조회
											arrImg[i][1] = imgUrl;
										}
										deleteTempFileList.add(ConfigClass.UPLOAD_PATH + "temp/" + tempFile.getName());
									}
								}
							}
						}
					}
				}
			}
		}
	
		//최종 세팅 및 수정
		logger.debug(">>>>>>>>>goodsdelivery>>>>>>>>>>" + goodsdelivery);
		paramsDb.clear();
		
		paramsDb.put("goodsno", goodsNo);
		paramsDb.put("goodscd", req.getParameter("goodscd"));
		paramsDb.put("reqsno", reqsno);
		paramsDb.put("goodsnm", goodsnm);
		paramsDb.put("goodsnmKR", req.getParameter("goodsnmKR"));
		paramsDb.put("goodsnmCN", req.getParameter("goodsnmCN"));
		paramsDb.put("brandno", req.getParameter("brandno"));
		paramsDb.put("origin", req.getParameter("origin"));
		paramsDb.put("binCd", req.getParameter("binCd"));
		paramsDb.put("simnCd", req.getParameter("simnCd"));
		paramsDb.put("seasonNm", req.getParameter("seasonNm"));
		paramsDb.put("euYn", StringUtil.nullConv(req.getParameter("euYn"), "Y"));
		paramsDb.put("shippingOrigin", req.getParameter("shippingOrigin"));
		paramsDb.put("metaTitle", StringUtil.nullConv(req.getParameter("meta_title"), ""));
		paramsDb.put("keyword", req.getParameter("keyword"));
		paramsDb.put("open", StringUtil.nullConv(req.getParameter("open"), "0"));
		paramsDb.put("exTitle", ex_title);
		paramsDb.put("ex1", req.getParameter("ex1"));
		paramsDb.put("ex2", req.getParameter("ex2"));
		paramsDb.put("ex3", req.getParameter("ex3"));
		paramsDb.put("ex4", req.getParameter("ex4"));
		paramsDb.put("ex5", req.getParameter("ex5"));
		paramsDb.put("ex6", req.getParameter("ex6"));
		paramsDb.put("useEmoney", req.getParameter("use_emoney"));
		paramsDb.put("usestock", req.getParameter("usestock"));
		paramsDb.put("runout", StringUtil.nullConv(req.getParameter("runout"), "0"));
		paramsDb.put("tax", req.getParameter("tax"));
		paramsDb.put("strprice", req.getParameter("strprice"));
		paramsDb.put("deliveryType", StringUtil.nullConv(req.getParameter("deliveryType"), "0"));
		paramsDb.put("goodsDelivery", goodsdelivery);
		paramsDb.put("usegoodsadd", StringUtil.nullConv(req.getParameter("usegoodsadd"), "0"));
		paramsDb.put("optnm", goodsOpt2Nm);
		paramsDb.put("opttype", StringUtil.nullConv(req.getParameter("opttype"), "single"));
		paramsDb.put("useadd", useadd);
		paramsDb.put("addoptnm", addOptNm);
		paramsDb.put("relationis", req.getParameter("relationis"));
		paramsDb.put("relation", relation);
		paramsDb.put("imgI", arrImg[3][1]);
		paramsDb.put("imgS", arrImg[2][1]);
		paramsDb.put("imgL", arrImg[0][1]);
		paramsDb.put("imgM", arrImg[1][1]);
		paramsDb.put("shortdesc", req.getParameter("shortdesc"));
		paramsDb.put("longdesc", req.getParameter("longdesc"));
		paramsDb.put("memo", StringUtil.nullConv(req.getParameter("memo")));
		paramsDb.put("approvalMemo", req.getParameter("approvalMemo"));
		
		int result = mapper.setModSellerGoods(paramsDb);
		
		/*
		 * ===========================================================================
		 * 파일 이동
		 * 현재 이미지의 경로가 정확하지 않다.
		 * 따라서 config.property에 정의된 경로를 재확정한 후에 로직이 수정되어야 한다.
		 * config.property에 보면 web, was 및 일반 파일 / 이미지 파일 등의 경로명이
		 * 분리되어 있는듯 하다. 정확한 정의와 조정이 필요하다.
		 * ===========================================================================
		 */
		//임시 파일 삭제
		if (!deleteTempFileList.isEmpty()) {
			try {
				FileUtil.deleteFileList(deleteTempFileList);
			} catch (Exception e) {
				logger.error("File Delete Error!!");
				e.printStackTrace();
			}
		}
		
		//AWS파일 삭제
		if (deletFileMap != null && deletFileMap.size() > 0) {
			for (int j = 0; j < deletFileMap.size(); j++) {
				for (int i = 0; i < 4; i++) {
					if (deletFileMap.get(arrImg[i][0] + j) != null && deletFileMap.get(arrImg[i][0] + j) != "") {
						String fileUrl = String.valueOf(deletFileMap.get(arrImg[i][0] + j));
						String awsFileName = FileUtil.getUrlFileName(fileUrl);
						String key = "goods/" + req.getParameter("goodscd") + "/" + awsFileName;
						awsfileUtil.delete(key);
					}
				}
			}
		}
		
		/* for (int i = 0; i < 4; i++) {
			for (int k = 0; k<uploadFileMap.size(); k++) {
				if (uploadFileMap.get(arrImg[i][0] + "[]" + k) != null && uploadFileMap.get(arrImg[i][0] + "[]" + k).get(0) != null && uploadFileMap.get(arrImg[i][0] + "[]" + k).get(0) != "") {
					List<MultipartFile> mf = mhsq.getFiles(arrImg[i][0] + "[]" + k);
					for (int j = 0; j < mf.size(); j++) {
						if (uploadFileMap.get(arrImg[i][0] + "[]" + k).get(1) != null && !"".equals(uploadFileMap.get(arrImg[i][0] + "[]" + k).get(1))) {
							FileUtil.uploadFile3(uploadFileMap.get(arrImg[i][0] + "[]" + k).get(1), mf.get(j).getBytes());
						}
					}
				}
			}
		}
		
		for (int k = 0; k < deletFileMap.size(); k++) {
			for (int i = 0; i < 4; i++) {
				if (deletFileMap.get(arrImg[i][0] + k) != null && deletFileMap.get(arrImg[i][0] + k) != "") {
					logger.debug("deletFileMap.get(" + arrImg[i][0] + k + ")::" + deletFileMap.get(arrImg[i][0] + k));
					FileUtil.deleteFile(ConfigClass.value("FILE_PATH") + "goods/", (String)deletFileMap.get(arrImg[i][0] + k));
				}
			}
		}
		String[] imgArrTmp = null; 
		for (i = 0; i < 4; i++) {
			
			logger.debug("choilee-arrImg[" +i + "][0]=[" +arrImg[i][0] + "]");
			logger.debug("choilee-arrImg[" +i + "][1]=[" +arrImg[i][1] + "]");
			ArrayList<HashMap<String, String>> list		= null;
			HashMap<String, String>	hm			= null;
			if (!"".equals(arrImg[i][1])){
				// 정규식 표현이므로 |를 [] 로 감싸야 한다.
				imgArrTmp = arrImg[i][1].split("[|]");
				if (!"img_l".equals(arrImg[i][0]) && null != imgArrTmp) {
					
					for (j = 0; j < imgArrTmp.length; j++) {
						list = new ArrayList();
						if (null != imgArrTmp[j] && !"".equals(imgArrTmp[j])) {
							arrTmp1 = imgArrTmp[j].split("\\.");
						
							logger.debug("choilee-arrTmp1[0]=[" +arrTmp1[0] + "], arrTmp1[1]=[" +arrTmp1[1] + "]");
							
							hm = new HashMap();
							hm.put("FILE_PATH"	, ConfigClass.value("WEB_DIR_PATH") + "shop/data/goods");
							hm.put("FILE_NAME"	, arrTmp1[0] + "_" +arrImg[i][0]);
							hm.put("FILE_EXT"	, arrTmp1[1]);
							hm.put("FILE_WIDTH"	, "290");
							hm.put("FILE_HEIGHT", "290");
							list.add(hm);
						}
						FileUtil.ThumbNailImageCreate(ConfigClass.value("FILE_PATH") + "goods", imgArrTmp[j], list);
					}
				}
			}
		} */
	}

	public int getGoodsListTotalCount() {
		// TODO Auto-generated method stub
		return goodsService.getGoodsListTotalCount();
	}

	public int copyGoods2(HashMap param) {
		// TODO Auto-generated method stub
		return goodsService.copyGoods2(param);
	}

	public void deleteGoods(HashMap param) throws Exception {
		// TODO Auto-generated method stub
		goodsService.deleteGoods(param);
	}
	
}
