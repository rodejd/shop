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
package com.wepinit.wepinit_shop.xmall.seller.event.service;

import com.wepinit.wepinit_shop.xmall.admin.vo.event.CouponVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApply;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApplyMember;
import com.wepinit.wepinit_shop.xmall.seller.event.dao.SellerEventMapper;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerCouponFM;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerEventFM;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerEventVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerEventService {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerEventService.class);
	
	@Resource
	SellerEventMapper mapper;
	
	public SellerEventFM getEventList(SellerEventFM eventFM) throws Exception {
		//총 갯수
		eventFM.setRowCount(mapper.getEventCount(eventFM));
		// 목록
		eventFM.setEventList(mapper.getEventList(eventFM));
		return eventFM;
	}
	
	public void updateEvent(SellerEventVO eventVO) throws Exception {
		mapper.updateEvent(eventVO);
	}
	
	public void insertEvent(SellerEventVO eventVO) throws Exception {
		mapper.insertEvent(eventVO);
	}
	
	public SellerEventVO detailEvent(SellerEventFM sellerEventFM) throws Exception {
		return mapper.detailEvent(sellerEventFM);
	}
	
	/** 관리자 > 이벤트/쿠폰관리 > 쿠폰 **/
	// 쿠폰리스트조회
	public SellerCouponFM getCouponList(SellerCouponFM couponFM) throws Exception {
		//총 갯수
		couponFM.setRowCount(mapper.getCouponCount(couponFM));
		// 목록
		couponFM.setCouponList(mapper.getCouponList(couponFM));
		return couponFM;
	}

	// 쿠폰발급/조회 - 전체회원수
	public SellerCouponFM getCouponApply1TotalCount(SellerCouponFM couponFM) throws Exception {
		couponFM.setCouponGrpTotal(mapper.getCouponApply1TotalCount());
		return couponFM;
	}
	
	// 쿠폰발급/조회 - 그룹조회
	public SellerCouponFM getCouponApply1(SellerCouponFM couponFM) throws Exception {
		couponFM.setCouponGrpList(mapper.getCouponApply1());
		return couponFM;
	}
	
	// 쿠폰발급/조회 - 쿠폰 단건 조회
	public SellerCouponFM getCouponApply2(SellerCouponFM couponFM) throws Exception {
		couponFM.setCouponVO(mapper.getCouponApply2(couponFM));
		return couponFM;
	}
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원수 
	public SellerCouponFM getCouponMemberCount(SellerCouponFM couponFM) throws Exception {
		couponFM.setCouponApplyGrpTotal(mapper.getCouponMemberCount(couponFM));
		return couponFM;
	}
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원리스트 
	public SellerCouponFM getCouponMemberNew(SellerCouponFM couponFM) throws Exception {
		couponFM.setCouponApplyGrpList(mapper.getCouponMemberNew(couponFM));
		return couponFM;
	}

	// 쿠폰발급/조회 - 등록처리
	@SuppressWarnings("rawtypes")
	public void insertCouponApply(Map<String, Object> param) throws Exception {
		int maxSno = mapper.getCouponApplyMax(param);
		param.put("sno", maxSno + 1);
		mapper.insertCouponApply(param); 
		
		//membertype(회원타입) 0: 전체회원발급, 1:그룹별발급, 2:회원개별발급
		List memberList = new ArrayList<Integer>();
		if("1".equals(param.get("membertype"))){
			//그룹별 발급일 경우 그룹 회원 정보 조회
			memberList = mapper.getGroupMember(param);
		} else if("0".equals(param.get("membertype"))) {
			//전체 발급일 경우 전체 회원 정보 조회
			memberList = mapper.getTotalMember(param);
		} else {
			throw new BizException("common.00004");
		}
		if(memberList.size() > 0) {
			for(int i=0; i<memberList.size(); i++){
				param.put("mno", memberList.get(i));
				mapper.insertCouponApplyMember(param);	
			}	
		}
	}
	
	public void deleteCouponApplyMember(Map<String, Object> param) throws Exception {
		int maxSno = mapper.getCouponApplyMax(param);
		param.put("sno", maxSno + 1);
		mapper.deleteCouponApplyMember(param);
	}

	public void insertCouponApplyMember(Map<String, Object> param) throws Exception {
		int maxSno = mapper.getCouponApplyMax(param);
		param.put("sno", maxSno + 1);
		mapper.insertCouponApply(param);
		mapper.insertCouponApplyMember(param);
	}

	// 쿠폰발급/조회 - 삭제처리
	public void deleteCouponApply(Map<String, Object> param) throws Exception {
		mapper.deleteCouponApply(param);
	}

	// 쿠폰만들기 - 쿠폰카테고리 조회
	public String[] getCouponCategory(SellerCouponFM sellerCouponFM) throws Exception {
		return mapper.getCouponCategory(sellerCouponFM);
	}
	
	// 쿠폰만들기 - 쿠폰 상품조회
	public List<GdCouponApply> getCouponGoods(SellerCouponFM sellerCouponFM) throws Exception {
		return mapper.getCouponGoods(sellerCouponFM);
	}
	
	// 쿠폰만들기 - 저장/수정
	public int insertCoupon(SellerCouponFM sellerCouponFM) throws Exception {
		int newCouponcd = 0;
		if (sellerCouponFM.getCouponVO() != null && sellerCouponFM.getCouponVO().getCouponcd() > 0 ) {
			mapper.updateCoupon(sellerCouponFM.getCouponVO());
			newCouponcd = sellerCouponFM.getCouponVO().getCouponcd();
		} else {
			mapper.insertCoupon(sellerCouponFM.getCouponVO());
			newCouponcd = mapper.getNewCouponcd();
		}
		logger.info("@@@@@@@@@@@@@@ new couponcd : " + newCouponcd);
		logger.info("@@@@@@@@@@@@@@ sellerCouponFM : " + sellerCouponFM);

		//Map<String, Object> param = new HashMap<String, Object>();
		SellerCouponFM paramVO = new SellerCouponFM();  
		paramVO.setCouponcd(newCouponcd);
		mapper.deleteCouponGoodsno(paramVO);
		mapper.deleteCouponCategory(paramVO);
		if(sellerCouponFM.getRefer()!=null){
			for(String sno : sellerCouponFM.getRefer()) {
				if (sno != null && sno != "") {
					paramVO.setGoodsno(sno);
					mapper.insertCouponGoodsno(paramVO);
				}
			}
		}
		if(sellerCouponFM.getCategoryArr()!=null){
			for(String cate : sellerCouponFM.getCategoryArr()) {
				if (cate != null && cate != "") {
					paramVO.setCategory(cate);
					mapper.insertCouponGoodCategory(paramVO);
				}
			}
		}
		
		return newCouponcd;
	}
	
	// 쿠폰삭제
	public void deleteCoupon(Map<String, Object> param)throws Exception {
		mapper.deleteCouponApplymemberByCoupon(param);
		mapper.deleteCoupon(param);
	}
	
	/**
	 * 쿠폰발급 상세 회원내역
	 * @param couponVO
	 * @return
	 * @throws Exception
	 */
	public List<GdCouponApplyMember> getCouponApplyMember(SellerCouponFM sellerCouponFM) throws Exception{
		//총 건수
		sellerCouponFM.setRowCount(mapper.getCouponApplyMemberCount(sellerCouponFM.getSno()));
		return mapper.getCouponApplyMember(sellerCouponFM);
	}
	
	/**
	 * 발급 쿠폰 사용가능여부 수정
	 * @param param
	 * @throws Exception
	 */
	public void updateCouponApply(CouponVO couponVO) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		
		for(int i=0; i<couponVO.getSnoGroup().length; i++){
			param.put("sno", couponVO.getSnoGroup()[i]);
			param.put("status", couponVO.getStatusGroup()[i]);	
			//발급내역 사용가능여부 수정
			mapper.updateCouponApply(param);
			//회원별발급내역 사용가능여부 수정
			mapper.updateCouponApplyMember(param);
		}
	}

	/**
	 * 판매사 게시여부 수정
	 * @param param
	 * @throws Exception
	 * 2017-11-16 추가
	 */
	@SuppressWarnings("rawtypes")
	public void updateOpen(HashMap paramsDb) throws Exception {
		mapper.updateOpen(paramsDb);
	}
	
	/**
	 * 판매사 승인여부 수정
	 * @param param
	 * @throws Exception
	 * 2017-11-16 추가
	 */
	@SuppressWarnings("rawtypes")
	public void updateApproval(HashMap paramsDb) throws Exception {
		mapper.updateApproval(paramsDb);
	}
	
	/**
	 * 판매사 게시여부 수정(쿠폰)
	 * @param param
	 * @throws Exception
	 * 2017-11-16 추가
	 */
	@SuppressWarnings("rawtypes")
	public void updateCouponOpen(HashMap paramsDb) throws Exception {
		mapper.updateCouponOpen(paramsDb);
	}
	
	/**
	 * 판매사 승인여부 수정(쿠폰)
	 * @param param
	 * @throws Exception
	 * 2017-11-16 추가
	 */
	@SuppressWarnings("rawtypes")
	public void updateCouponApproval(HashMap paramsDb) throws Exception {
		mapper.updateCouponApproval(paramsDb);
	}
	
}
