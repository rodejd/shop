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
package com.wepinit.wepinit_shop.xmall.front.service.mypage;

import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.dao.mypage.FrontMypageMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageBoardVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageOrderVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageTodayVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FrontMypageService {
	
	Logger logger = LoggerFactory.getLogger(FrontMypageService.class);
	
	@Resource
	FrontMypageMapper mapper;
	
	@Resource
	ShopLibFncService shopLibFncService;
	
	

	
	/**
	 * 할인쿠폰내역
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int getFrontCouponCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontCouponCount(param);
	}
	
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getFrontCouponList(Map<String, Object> param) throws Exception {
		return mapper.getFrontCouponList(param);
	}
	
	/**
	 * 적립금내역
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int getFrontEmoneyCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontEmoneyCount(param);
	}

	public List<GdLogEmoney> getFrontEmoneyList(Map<String, Object> param) throws Exception {
		return mapper.getFrontEmoneyList(param);
	}
	
	/**
	 * 위시리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int getFrontMyPageWishCount(FrontMypageBoardVO vo) throws Exception {
		return mapper.getFrontMyPageWishCount(vo.getMno());
	}
	public List<MemberwishlistGoodsGoodsoption> getFrontMyPageWishList(FrontMypageBoardVO vo) throws Exception {
		return mapper.getFrontMyPageWishList(vo);
	}
	public void deleteFrontMyPageWishList(int mno, String snoList){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mno", mno);
		param.put("snoList", snoList);
		
		mapper.deleteFrontMyPageWishList(param);
	}
	public void insertFrontMyPageWishList(FrontMypageBoardVO vo)throws Exception {
		mapper.insertFrontMyPageWishList(vo);
	}
	public int getFrontMyPageWishListCheckCount(FrontMypageBoardVO vo)throws Exception {
		return mapper.getFrontMyPageWishListCheckCount(vo);
	}
	
	
	
	/**
	 * 문의내역
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int getFrontMyPageGoodsQnaCount(FrontMypageBoardVO vo) throws Exception {
		return mapper.getFrontMyPageGoodsQnaCount(vo);
	}
	
	public List<GdGoodsQna> getFrontMyPageGoodsQnaList(FrontMypageBoardVO vo) throws Exception {
		List<GdGoodsQna> qnaList = mapper.getFrontMyPageGoodsQnaList(vo);
		for (GdGoodsQna qna : qnaList) {
			vo.setSno(qna.getSno());
			qna.setReplyList(mapper.getFrontMyPageGoodsQnaReply(vo));
		}
		return qnaList;
	}
	
//	public int getFrontMyPageGoodsQnaReplyCount(int parent) throws Exception {
//		return mapper.getFrontMyPageGoodsQnaReplyCount(parent);
//	}
//	public Map<String, Object> getFrontMyPageGoodsInfo(int goodsno) throws Exception {
//		return mapper.getFrontMyPageGoodsInfo(goodsno);
//	}
	
	
	
	
	
	
	/**
	 * 주문내역/배송조회
	 * @param param
	 */
	public OrderOrderitem getFrontOrderCountStep(Map<String, Object> param) throws Exception {
		return mapper.getFrontOrderCountStep(param);
	}
	
	public List<GdListDelivery> getUseDeliveryCompList(Map<String, Object> param) throws Exception {
		return mapper.getUseDeliveryCompList(param);
	}
	
	public int getFrontOrderCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontOrderCount(param);
	}

	public List<OrderMember> getFrontOrderList(Map<String, Object> param) throws Exception {
		return mapper.getFrontOrderList(param);
	}
	
	/**
	 * 반품
	 * @param frontMypageVO
	 * @throws Exception
	 */
	public void sendback(FrontMypageVO frontMypageVO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		String mode = frontMypageVO.getMode();
		
		//주문상태변경(교환[trade],반품[sendback])
		param.put("mode", mode );
		
		//주문번호
		param.put("ordno", frontMypageVO.getOrdno() );
		
		//gd_order_item sno
		param.put("sno", frontMypageVO.getSno());
		
		//주문Step
		param.put("step", 7);
		
		//주문Step2
		param.put("step2", 70 );
		
		//주문istep
		param.put("istep", 70);
		
		//1.환불처리
		//mapper.updateOrderStep(param);
		mapper.updateOrderItemIstep(param);
		
		//사유코드
		param.put("code", frontMypageVO.getCode() );
		
		//환불은행코드
		param.put("bankcode", frontMypageVO.getBankcode() );
		
		//환불은행계좌번호
		param.put("bankaccount", frontMypageVO.getBankaccount() );
		
		//환불계좌예금주
		param.put("bankuser", frontMypageVO.getBankuser() );
		
		//상세사유
		param.put("memo", frontMypageVO.getMemo() );
		
		//2.취소테이블 환불 추가
		mapper.insertOrderCancel(param);
		
		if(logger.isDebugEnabled()){
			//gd_order_cancel sno
			logger.debug("sendback>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>param" + param.get("sno"));
		}
		String ordno = String.valueOf(param.get("ordno"));
		shopLibFncService.changeStep2(ordno);
		int step2 = shopLibFncService.getOrderStep2(ordno);
		if(step2 == 70){
			mapper.updateOrderStep(param);
		}
		
		param.put("itemsno", frontMypageVO.getSno());
		//3.주문정보업데이트
		mapper.updateOrderItem(param);
		
		
		//4.주문정보업데이트(반품/환불 접수 상품리스트>반품신청)
		//mapper.updateOrderItem2(param);
		
	}
	
	/**
	 * 교환
	 * @param frontMypageVO
	 * @throws Exception
	 */
	public void trade(FrontMypageVO frontMypageVO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		String mode = frontMypageVO.getMode();
		
		//주문상태변경(교환[trade],반품[sendback])
		param.put("mode", mode );
		
		//주문번호
		param.put("ordno", frontMypageVO.getOrdno() );
		
		//주문Step
		param.put("step", 6);
		
		//주문Step2
		param.put("step2", 60 );
		
		param.put("istep", 60);
		
		param.put("sno", frontMypageVO.getSno());
		
		//1.교환처리
		//mapper.updateOrderStep(param);
		
		mapper.updateOrderItemIstep(param);
		
		//사유코드
		param.put("code", frontMypageVO.getCode() );
		
		//상세사유
		param.put("memo", frontMypageVO.getMemo() );
		
		//2.취소테이블 환불 추가
		mapper.insertOrderCancel(param);
		
		if(logger.isDebugEnabled()){
			logger.debug("trade>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>param" + param.get("sno"));
		}
		String ordno = String.valueOf(param.get("ordno"));
		shopLibFncService.changeStep2(ordno);
		int step2 = shopLibFncService.getOrderStep2(ordno);
		if(step2 == 60){
			mapper.updateOrderStep(param);
		}
		param.put("itemsno", frontMypageVO.getSno());
		//3.주문정보업데이트
		mapper.updateOrderItem(param);
		
		//4.주문정보업데이트(반품/환불 접수 상품리스트>반품신청)
		//mapper.updateOrderItem2(param);
	}
	
	/** 
	 * 	수령 확인
	 * @param frontMypageVO
	 */
	public void checkReceive(Map<String,Object> param)throws Exception {
		//istep update
		mapper.updateOrderItemIstep(param);
		//전체 주문 취소
		String ordno = String.valueOf(param.get("ordno"));
		shopLibFncService.changeStep2(ordno);
	}
	
	public void updateOrderStep(Map<String, Object> param) throws Exception {
		mapper.updateOrderStep(param);
	}
	
	//whpark 20191111
	public void updateOrderItemIstep(Map<String, Object> param) throws Exception {
		mapper.updateOrderItemIstep(param);
	}
	
	public int getOtherItemCnt(Map<String, Object> param) throws Exception {
		return mapper.getOtherItemCnt(param);
	}
	
	public int updateOrderItem(Map<String, Object> param) throws Exception {
		return mapper.updateOrderItem(param);
	}
	public int updateDeliveryReturn(Map<String, Object> param) throws Exception {
		return mapper.updateDeliveryReturn(param);
	}

	
		
	
	public List<GdOrderItem> getFrontOrderItemObj(FrontMypageVO frontMypageVO) throws Exception {
		return mapper.getFrontOrderItemObj(frontMypageVO);
	}
	
	public int insertOrderCancel(Map<String, Object> param) throws Exception {
		return mapper.insertOrderCancel(param);
	}
	
	public List<OrderitemGoods> getFrontOrderViewList(FrontMypageVO frontMypageVO) throws Exception {
		return mapper.getFrontOrderViewList(frontMypageVO);
	}
	
	public Map<String, Object> getFrontOrderView(Map<String, Object> map) throws Exception {
		return mapper.getFrontOrderView(map);
	}
	
	public List<Map<String, Object>> getFrontOrderItemList(Map<String, Object> map) throws Exception {
		return mapper.getFrontOrderItemList(map);
	}
	
	public List<GdOrder> getFrontOrderViewObj(FrontMypageVO frontMypageVO) throws Exception {
		return mapper.getFrontOrderViewObj(frontMypageVO);
	}
	
	/*
	 * Mypage > 1:1문의
	 */
	public List<GdMemberQna> getFrontMemberQnaList(FrontMypageBoardVO vo) throws Exception {
		//총건수
		vo.setRowCount(mapper.getFrontMemberQnaListCount(vo.getMno()));
		return mapper.getFrontMemberQnaList(vo);
	}
	public MemberMemberqna getFrontMemberQnaInfo(int sno) throws Exception {
		return mapper.getFrontMemberQnaInfo(sno);
	}
	public void updateFrontMemberQna(FrontMypageBoardVO vo) throws Exception {
		mapper.updateFrontMemberQna(vo);
	}
	public void insertFrontMemberQnaReply(FrontMypageBoardVO vo) throws Exception {
		mapper.insertFrontMemberQnaReply(vo);
	}
	public void insertFrontMemberQna(FrontMypageBoardVO vo) throws Exception {
		//문의 등록
		mapper.insertFrontMemberQna(vo);
		vo.setSno(mapper.getFrontMemberQnaSnoMax());
		//부모글 업데이트
		mapper.updateFrontMemberQnaParent(vo.getSno());
	}
	public void deleteFrontMemberQna(int sno) throws Exception {
		mapper.deleteFrontMemberQna(sno);
	}
	
	public List<Map<String, Object>> getFrontMyPageMemberQnaOrderList(FrontMypageOrderVO vo) throws Exception {
		//총건수
		vo.setRowCount(mapper.getFrontMyPageMemberQnaOrderListCount(vo.getMno()));
		return mapper.getFrontMyPageMemberQnaOrderList(vo);
	}
	
	public List<FrontMypageBoardVO> getFrontMyPageOrderGoddsList(FrontMypageBoardVO vo) throws Exception {
		//총건수
		vo.setRowCount(mapper.getFrontMyPageOrderGoddsListCount(vo.getOrdno()));
		return mapper.getFrontMyPageOrderGoddsList(vo);
	}
	/*
	 * Mypage >상품후기
	 */
	public List<MemberGoodsrevwGoods> getFrontMyPageReviewList(FrontMypageBoardVO vo) throws Exception {
		//총건수
		vo.setRowCount(mapper.getFrontMyPageReviewCount(vo.getMno()));
		return mapper.getFrontMyPageReviewList(vo);
	}
	public void updateFrontMyPageReview(FrontMypageBoardVO vo) throws Exception {
		mapper.updateFrontMyPageReview(vo);
	}
	public void deleteFrontMyPageReview(int sno) throws Exception {
		mapper.deleteFrontMyPageReview(sno);
	}
	
	/*
	 * Mypage > 최근 본 상품
	 */
	public List<GdGoodsGoodsoptionGoodslink> getFrontMyPageTodayGoodsList(FrontMypageTodayVO vo) throws Exception {
		vo.setRowCount(mapper.getFrontMyPageTodayGoodsListCount(vo.getGoodsno()));
		return mapper.getFrontMyPageTodayGoodsList(vo);
	}
	public String getsellerCd(String ordno) {
		// TODO Auto-generated method stub
		return mapper.getsellerCd(ordno);
	}
	
	public void insertOrderLog(String ordno, String step, String step2,	HttpServletRequest req) {
		String memo="";
		String idx = "";
		ShopSessionObject shopSessionObject = ShopSessionObject.getSessionObject(req);

		idx = shopSessionObject.getUserInfo().getUserId();
		memo = ShopLibFunction.r_stepi(step ,step2); 	// 여기서 스텝 번호와 memo 확인가능

		if("".equals(ordno)||"".equals(idx)||"".equals(memo)||null == shopSessionObject){
			throw new BizException("common.00001");
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ordno", ordno);
		map.put("memo",memo);
		map.put("step",step);
		map.put("step2",step2);
		map.put("idx",idx);
		
		mapper.insertOrderLog(map);		
	}
	
	/**
	 * 취소요청 2018-08-28 추가
	 *
	 * @param frontMypageVO
	 */
	public void cancelback(FrontMypageVO frontMypageVO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String mode = frontMypageVO.getMode();

		//주문상태변경(교환[trade],반품[sendback],취소[cancel])
		param.put("cancel", mode );

		//주문번호
		param.put("ordno", frontMypageVO.getOrdno() );

		//주문Step
		//param.put("step", 0);
		//주문Step2
		//param.put("step2", 40 );
		//1.취소요청
		//mapper.updateOrderStep(param);
		
		//whpark 20191111
		param.put("istep", "40" );
		param.put("sno", frontMypageVO.getSno());
		mapper.updateOrderItemIstep(param);	
		
		shopLibFncService.changeStep2(String.valueOf(param.get("ordno")));

	}

	public List<CouponapplyApplymemberCouponCategoryGoodsno> getUsedCouponList(	String ordno) {
		return mapper.getUsedCouponList(ordno);
	}
}
