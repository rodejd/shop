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
package com.wepinit.wepinit_shop.xmall.common.dao;

import com.wepinit.wepinit_shop.xmall.admin.vo.event.CouponVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.CodeSmssample;
import com.wepinit.wepinit_shop.xmall.common.vo.join.ShopLibSetStock1;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryOverPolicyVO;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryPolicyInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mapper
public interface ShopLibFncMapper {
	
	public Map<String, String> getQnaSearch(@Param("sch_loadfrom")String sch_loadfrom, @Param("table_name")String table_name);
	public List<GdCode> getCodeItem(String groupcd);
	public List<GdCode> getCodeItem2();
	public List<CodeSmssample> getCodeItem3();
	public Map<String, Object> getGoodsCategoryHiddenState(@Param("category")String category);
	public GoodsVO getGoodsInfo(@Param("goodsno")String goodsno);
	public void insertGoodsInfoCopy(GoodsVO vo);
	public int getGoodsNoMax();
	public int getBirthCongratsCnt(Map<String, Object> map);
	public void insertGoodsAddCopy(Map<String, Object> map);
	public void insertGoodsOptionCopy(Map<String, Object> map);
	public void insertGoodsLinkCopy(Map<String, Object> map);
//	public void insertGoodsTipCopy(Map<String, Object> map);
//	public void insertGoodsRecipeCopy(Map<String, Object> map);
	public GoodsVO getGoodsInfoAll(@Param("goodsno")String goodsno);
	public String getGoodsBrandName(int sno);			//?????? ???????????? ??????
	public Map<String, Object> getGoodsUseEmoney(int goodsno);	//?????? ????????? ?????? ???????????? ??????
//	public void deleteGoodsDisplayAll(@Param("goodsno")String goodsno);
//	
//	public void deleteGoodsAdd(@Param("goodsno")String goodsno);
//	
//	public void deleteGoodsOptionAll(@Param("goodsno")String goodsno);
//	
//	public void deleteGoodsdLinkAll(@Param("goodsno")String goodsno);
//	
//	public void deleteGoodsInfo(@Param("goodsno")String goodsno);
//	
//	public void deleteMemberWishListGoods(@Param("goodsno")String goodsno);
	
	public List<Map<String,Object>> getGoodsCategoryList(Map map);
	
	
	public Map<String,Object> getCodeName(@Param("grpNm")String grpNm, @Param("itemCd")String itemCd);
	
//	public Map<String,Object> getConfValue(@Param("confCd")String confCd, @Param("dimSet")String dimSet, @Param("dim1Var")String dim1Var);
	
//	public void setConfValue(@Param("confCd")String confCd, @Param("dimSet")String dimSet, @Param("dim1Var")String dim1Var, @Param("dimVal")String dimVal);
	
	public List<GdMemberGrp> getMemberGrp();
	
	public List<Map<String,Object>> getConfValueArry(@Param("confCd")String confCd, @Param("dimSet")String dimSet, @Param("dim1Var")String dim1Var);
	
	public List<Map<String,String>> getStepMsg(@Param("ordno")String ordno, @Param("itemsno")String itemsno);

	public List<String> getStepMsg(GdOrderItem orderItem);
	
	public void setEmoney1(Map<String,Object> paramMap);
	
	public void setEmoney2(Map<String,Object> paramMap);
	
	public List<ShopLibSetStock1> setStock1(long ordno);
	
	public GdGoodsOption setStock2(Map<String,Object> paramMap);
	
	public void setStock3(Map<String,Object> paramMap);
	
	public void setStock4(Map<String,Object> paramMap);
	
	public List<GdOrderItem> setPrnSettleprice1(long ordno);
	
	public GdOrder setPrnSettleprice2(long ordno);
	
	public void setPrnSettleprice3(Map<String,Object> paramMap); 
	
	public PrnSettlePrice4VO setPrnSettleprice4(int mno);
	
	public void setPrnSettleprice5(Map<String,Object> paramMap);
	
	
	//reorder
	public Map<String, Object> getOrderInfo(@Param("ordno")long ordno);
	public int getOrderItemCount(@Param("ordno")long ordno);
	public List<Map<String, Object>> getOrderItemList(@Param("cancel")int cancel);
	public List<Map<String, Object>> getOrderItemDeliveryList(@Param("ordno")long ordno);
	public void insertReorderItem(Map<String, Object> param);
	public void insertReorderInfo(Map<String, Object> param);
	public void insertReorderDelivery(Map<String, Object> param);
	
	// ?????? ?????? ??????
	public GdOrder ctlStep1(Map<String,Object> paramMap);	// SELECT gd_order
	public int ctlStep2(Map<String,Object> paramMap);	// SELECT gd_order_item
	public void ctlStep3(Map<String,Object> paramMap);	// UPDATE gd_order
	public void ctlStep4(Map<String,Object> paramMap);	// UPDATE gd_order_item
	public void ctlStep5(Map<String,Object> paramMap);	// UPDATE gd_order
	public void ctlStep6(Map<String,Object> paramMap);	// UPDATE gd_order_item
	public void ctlStep7(Map<String,Object> paramMap);	// UPDATE gd_order
	public void ctlStep8(Map<String,Object> paramMap);	// UPDATE gd_order_item
	public void ctlStep9(Map<String,Object> paramMap);	// UPDATE gd_order
	public void ctlStep10(Map<String,Object> paramMap);	// UPDATE gd_order_item
	public void ctlStep11(Map<String,Object> paramMap);	// UPDATE gd_order
	public void ctlStep12(Map<String,Object> paramMap);	// UPDATE gd_order_item
	//20191128 ????????? ???????????? ????????? ????????? goodsno ??? 
	public String[] detailGoods(Map<String,Object> paramMap);
	
	// ???????????? ????????? ??????
	public GdOrder setGoodsEmoney1(Map<String,Object> paramMap);// SELECT gd_order
	public int setGoodsEmoney2(Map<String,Object> paramMap);	// SELECT gd_order_item
	public void setGoodsEmoney3(Map<String,Object> paramMap);	// UPDATE gd_order
	public void setGoodsEmoney4(Map<String,Object> paramMap);	// INSERT gd_order_item
	
	// ???????????? ?????? ???????????????
	public List<GdCouponOrder> getFrontCouponOrderList(Map<String, Object> param);
	public void updateMemberEmoney(GdCouponOrder couponOrderParam);
	public void insertLogEmoney(GdCouponOrder couponOrderParam);
	
	// ????????? ????????? ?????? ??????
	public int setGoodsCoupon1(Map<String,Object> paramMap);// SELECT gd_order
	public List<Integer> setGoodsCoupon2(Map<String,Object> paramMap);	// SELECT gd_order_item
	public List<Map<String, Object>> setGoodsCoupon3(Map<String,Object> paramMap);	// SELECT gd_goods_link
	public List<GdCoupon> setGoodsCoupon4(Map<String,Object> paramMap);	// SELECT gd_coupon
	public int setGoodsCoupon5(Map<String,Object> paramMap);	// SELECT gd_coupon_apply
	public void setGoodsCoupon6(Map<String,Object> paramMap);	// INSERT gd_coupon_apply
	public void setGoodsCoupon7(Map<String,Object> paramMap);	// INSERT gd_coupon_applymember
	public List<HashMap> getCodeItemMap(String groupcd);
	public List<GdDeliveryPolicy> getDeliveryDefaultPolicy(@Param("no") String no);	// SELECT ???????????????????????????

	public List<GdCoupon> getCouponOrder();
	public void GoodsOrderCouponApplyInsert(Map<String, Object> couponMap);
	public void GoodsOrderCouponApplymemberInsert(Map<String, Object> couponMap);
	public int tableMaxFieldSelect(Map<String, Object>tableMaxFieldMap);
	public List<CouponVO> selectCouponcategory(Map<String, Object> couponcd);
	public List<CouponVO> selectCoupongoodsno(Map<String, Object> couponcd);
	
	
	public List<Map<String,String>> orderModifyDC1(long ordno);
	
	/** sellerCd ??? ???????????? ??????????????? ?????? ???????????? */
	public List<SellerDeliveryOverPolicyVO> getSellerDeliveryOverPolicy(@Param("sellerCd") String sellerCd);
	
	/** sellerCd ??? ???????????? ????????? ?????? ???????????? */
	public SellerDeliveryPolicyInfoVO getSellerDeliveryPolicy(@Param("sellerCd") String sellerCd);
	
	
	/* 20191203 ????????? ????????? ???????????? ?????? */
	public int checkDyn(@Param("ordno")String ordno);
	
	/* 20200224 ????????? step2 ?????? */
	public void changeStep2(@Param("ordno")String ordno);
	public int getOrderStep2(@Param("ordno")String ordno);
	
	/* 20200116 ????????? 
	 * getCancelLogDeliveryList
	 * ??????/???????????? ??????????????? > ????????????(?????????) ???????????? ???????????? ??????
	 */
	public List<Map<String,Object>> getCancelLogDeliveryList(Map<String,Object> param);
	
	/**
	 *  ??????????????? ??????
	 */
	public Map<String,Object> selectShopLibMemberInfo(Map<String,Object> param);
	
	public Map<String,Object> selectShopLibOrderInfo(Map<String,Object> param);
	
	public Map<String, Object> selectShopLibOrderPayTranLog(Map<String, Object> pMap);
	
	public List<Map<String,Object>> selectShopLibCouponOrderInfo(Map<String,Object> param);
	
	public int updateShopLibOrderStep(Map<String,Object> param);
	
	public int updateShopLibOrderIStep(Map<String,Object> param);
	
	public int insertShopLibEmoneyLog(Map<String,Object> param);
	
	public int updateShopLibMemberEmoney(Map<String,Object> param);
	
	public int updateShopLibCouponApplymember(Map<String,Object> param);
	
	public int deleteShopLibCouponApply(Map<String,Object> param);
	
	public int deleteShopLibCouponApplymember(Map<String,Object> param);
	
	public int deleteShopLibCouponOrder(Map<String,Object> param);
	
	/**
	 * ??????(??????)?????? ??????
	 */
	public Map<String,Object> selectShopLibOrderMemberReturnInfo(Map<String,Object> param);
	
	public int updateShopLibOrderByReturnyn(Map<String,Object> param);
	
	/**
	 * ?????? ????????? ?????? ??????
	 */
	public Map<String,Object> selectShopLibOrderMemberInfo(String ordno);
	
	public int updateShopLibOrderByAddemoneyyn(Map<String,Object> param);
	
	public int updateShopLibMemberSale(Map<String,Object> param);
	
	/**
	 * ????????? ?????? ????????????
	 */
	public Map<String,Object> selectShopLibTalkInfo(String ordno);

}

