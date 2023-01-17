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
package com.wepinit.wepinit_shop.xmall.admin.service.event;

import com.wepinit.wepinit_shop.xmall.admin.dao.event.EventMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.CouponVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.EventVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.SurveyVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class EventService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventService.class);
	
	@Resource
	EventMapper mapper;
	
	public void setSurveyQuestion(SurveyVO surveyVO) {
		mapper.setSurveyQuestion(surveyVO);
	}
	
	public int getEventCount(EventVO eventVO) throws Exception {
		return  mapper.getEventCount(eventVO);
		
	}
	
	public List<GdEvent> getEventList(Map<String, Object> param) throws Exception {
		
		return mapper.getEventList(param);
	}
	
	public void deleteEvent(EventVO eventVO) throws Exception {
		mapper.deleteEvent(eventVO);
	}
	
	public void updateEvent(EventVO eventVO) throws Exception {
		mapper.updateEvent(eventVO);
	}
	
	public void insertEvent(EventVO eventVO) throws Exception {
		mapper.insertEvent(eventVO);
	}
	
	public GdEvent detailEvent(EventVO eventVO) throws Exception {
		return mapper.detailEvent(eventVO);
	}
	
	/** 관리자 > 이벤트/쿠폰관리 > 쿠폰 **/
	// 쿠폰리스트건수
	public int getCouponCount(Map<String, Object> param)throws Exception {
		return  mapper.getCouponCount(param);
	}
	
	// 쿠폰리스트조회
	public List<GdCoupon> getCouponList(Map<String, Object> param) throws Exception {
		return mapper.getCouponList(param);
	}

	// 쿠폰발급/조회 - 전체회원수
	public int getCouponApply1TotalCount() throws Exception {
		return mapper.getCouponApply1TotalCount();
	}
	
	// 쿠폰발급/조회 - 그룹조회
	public List<GdMemberGrp> getCouponApply1() throws Exception {
		return mapper.getCouponApply1();
	}
	
	// 쿠폰발급/조회 - 쿠폰 단건 조회
	public GdCoupon getCouponApply2(Map<String, Object> param) throws Exception {
		return mapper.getCouponApply2(param);
	}
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원수
	public int getCouponMemberCount(Map<String, Object> param) throws Exception {
		return mapper.getCouponMemberCount(param);
	}
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원리스트
	public List<GdCouponApply> getCouponMemberNew(Map<String, Object> param) throws Exception {
		return mapper.getCouponMemberNew(param);
	}
//	public List<GdCouponApply> getCouponMember1(Map<String, Object> param) throws Exception {
//		return mapper.getCouponMember1(param);
//	}
//	// 쿠폰발급/조회 - 개별로 발급 받은 회원정보
//	public GdCouponApply getCouponMember2(Map<String, Object> param) throws Exception {
//		return mapper.getCouponMember2(param);
//	}	

	// 쿠폰발급/조회 - 등록처리
	@SuppressWarnings("rawtypes")
	public void insertCouponApply(Map<String, Object> param) throws Exception {
		int maxSno = mapper.getCouponApplyMax(param);
		param.put("sno", maxSno + 1);
		mapper.insertCouponApply(param); 
		
		//2017-09-04 추가 - 전체, 그룹발급시 gd_coupon_applymember에 등록 추가
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
	public String[] getCouponCategory(Map<String, Object> param) throws Exception {
		return mapper.getCouponCategory(param);
	}
	
	// 쿠폰만들기 - 쿠폰 상품조회
	public List<GdCouponApply> getCouponGoods(Map<String, Object> param) throws Exception {
		return mapper.getCouponGoods(param);
	}
	
	// 쿠폰만들기 - 저장/수정
	public int insertCoupon(CouponVO couponVO) throws Exception {
		if ("register".equals(couponVO.getMode())) {
			mapper.insertCoupon(couponVO);
		} else {
			mapper.updateCoupon(couponVO);
		}
		int newcouponcd = Integer.parseInt(couponVO.getCouponcd());
		logger.info("@@@@@@@@@@@@@@ new couponcd : " + newcouponcd);
		logger.info("@@@@@@@@@@@@@@ new couponcd : " + couponVO);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("couponcd", newcouponcd);
		mapper.deleteCouponGoodsno(param);
		mapper.deleteCouponCategory(param);
		if(couponVO.getRefer()!=null){
			for(String sno : couponVO.getRefer()) {
				if (sno != null && sno != "") {
					param.put("goodsno", sno);
					mapper.insertCouponGoodsno(param);
				}
			}
		}
		
		if(couponVO.getCategoryArr()!=null){
			for(String cate : couponVO.getCategoryArr()) {
				if (cate != null && cate != "") {
					param.put("category", cate);
					mapper.insertCouponGoodCategory(param);
				}
			}
		}
		
		return newcouponcd;
	}
	
	/**
	 * 쿠폰 이미지 업데이트
	 * @param couponVO
	 * @return
	 * @throws Exception
	 */
	public int updateCouponImage(CouponVO couponVO) throws Exception {
		return mapper.updateCouponImage(couponVO);
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
	public List<GdCouponApplyMember> getCouponApplyMember(CouponVO couponVO) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		//총 건수
		couponVO.setRowCount(mapper.getCouponApplyMemberCount(couponVO.getSno()));
		
		param.put("sno", couponVO.getSno());
		param.put("rowStart", couponVO.getRowStart());
		param.put("pageSize", couponVO.getPageSize());

		return mapper.getCouponApplyMember(param);
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
	
	public void setSurvey(SurveyVO surveyVO) {
		//gd_survey 테이블 
		//설문조사 등록 
		mapper.setSurvey(surveyVO);
		mapper.setSurveyQuestion(surveyVO);
	}
	public void modifySurvey(SurveyVO surveyVO) throws Exception {
		//gd_survey
		//설문조사 수정
		mapper.modifySurvey(surveyVO);
		//설문조사 질문 삭제
		mapper.deleteSurveyQuestion(surveyVO.getSurveySno());
		//gd_survey_question
		//설문조사 질문 등록 
		mapper.setSurveyQuestion(surveyVO);
	}
	
	public int getSurveyCount(SurveyVO surveyVO){
		return mapper.getSurveyCount(surveyVO);
	}
	
	public List<SurveyVO> getSurveyList(SurveyVO surveyVO){
		return mapper.getSurveyList(surveyVO);
	}
	
	public void updateSurveyOpen(SurveyVO surveyVO){
		mapper.updateSurveyOpen(surveyVO);
	}
	
	public void deleteSurvey(String sno){
		mapper.deleteSurvey(sno);
		mapper.deleteSurveyQuestion(sno);
	}
	public void deleteSurveyQuestion(String sno){
		mapper.deleteSurveyQuestion(sno);
	}
	
	public SurveyVO getDetailSurvey(String sno){		
		return mapper.getDetailSurvey(sno);
	}
	
}
