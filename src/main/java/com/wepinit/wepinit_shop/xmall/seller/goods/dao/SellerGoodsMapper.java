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
package com.wepinit.wepinit_shop.xmall.seller.goods.dao;

import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsFM;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface SellerGoodsMapper {
	
	// ######
	// # 판매사별 상품 요청 관련
	// #####
	public int getSellerGoodsListTotalCount(SellerGoodsFM sellerGoodsFM);					// 상품요청 총건수
	public int getSellerGoodsListCount(SellerGoodsFM sellerGoodsFM);						// 상품요청 검색 총건수
	public List<SellerGoodsViewVO> getSellerGoodsList(SellerGoodsFM sellerGoodsFM);			// 상품요청 검색 목록

	public SellerGoodsViewVO getSellerGoodsInfo(SellerGoodsFM sellerGoodsFM);				// 상품요청 정보
	public List<SellerGoodsViewVO> getSellerGoodsAddInfoList(String reqsno);				// 상품요청 추가정보 목록
	public List<SellerGoodsViewVO> getSellerCategoryLinkList(String reqsno);				// 상품요청 카테고리 목록
	
	// ######
	// # 판매사 별 상품  목록
	// #####
	public int getGoodsListTotalCount(SellerGoodsFM sellerGoodsFM);					// 상품 총건수
	public int getGoodsListCount(SellerGoodsFM sellerGoodsFM);						// 상품 검색 총건수
	public List<SellerGoodsViewVO> getGoodsList(SellerGoodsFM sellerGoodsFM);		// 상품 검색 목록
	
	public SellerGoodsViewVO getGoodsInfo(SellerGoodsFM sellerGoodsFM);				// 상품 정보
	public List<SellerGoodsViewVO> getGoodsAddInfoList(String goodsno);				// 상품 추가정보 목록
	public List<SellerGoodsViewVO> getCategoryLinkList(String goodsno);				// 상품 카테고리 목록
	
	
	public List<SellerGoodsViewVO> getReqGoodsNotiList(SellerGoodsFM sellerGoodsFM);
	public List<SellerGoodsViewVO> getGoodsRelationList(String[] relationReqsnoArr);
	
	
	public int setRegGoodsNotiSeller(SellerGoodsFM sellerGoodsFM);
	public List<SellerGoodsViewVO> getSellerGoodsNotiList(SellerGoodsFM sellerGoodsFM);
	

	public int setRegSellerGoodsOption(SellerGoodsFM sellerGoodsFM);
	public int setDelSellerGoodsNotiAll(int reqsno);
	public int setRegSellerGoodsNoti(SellerGoodsFM sellerGoodsFM);
	
	public int setRegSellerGoods(HashMap paramsDb);
	public int setDelSellerGoods(String reqsno);
	public int getReqsnoMaxCount();
	public int setDelSellerGoodsLinkAll(int reqsno);
	public int setDelSellerGoodsOptionAll(int reqsno);
	public int setDelSellerGoodsAddAll(int reqsno);
	
	public int setRegSellerGoodsLink(Map<String, Object> map);
	public int setRegSellerGoodsAdd(Map<String, Object> map);
	public int setModSellerGoods(HashMap<String,Object> params);
	
	public int setRegSellerGoodsCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegSellerGoodsAddCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegSellerGoodsOptionCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegSellerGoodsLinkCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegSellerGoodsNotiCopy(SellerGoodsFM sellerGoodsFM);
	
	public int setRegGoodsCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegGoodsAddCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegGoodsOptionCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegGoodsLinkCopy(SellerGoodsFM sellerGoodsFM);
	public int setRegGoodsNotiCopy(SellerGoodsFM sellerGoodsFM);
	public int setModSellerGoodsApprovalStatus(SellerGoodsFM sellerGoodsFM);
	public int setRegSellerGoodsnoRegister(SellerGoodsFM sellerGoodsFM);
	
	public int deleteGoodsOption(int goodsno); //추가옵션 삭제
	public int deleteGoodsLinkAll(int goodsno); //상품분류정보 삭제
	
	public void insertSellerGoodsOption(Map<String,Object> map);
	
	public List<SellerGoodsViewVO> getGoodsOption(String no);
	
	public List<SellerGoodsViewVO> getSellerGoodsOption(String no);
	
	
	
} 
