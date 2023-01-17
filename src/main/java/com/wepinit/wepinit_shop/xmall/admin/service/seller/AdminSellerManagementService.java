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
package com.wepinit.wepinit_shop.xmall.admin.service.seller;

import com.wepinit.wepinit_shop.xcube.util.CryptoUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.seller.AdminSellerManagementMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerManagementFM;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerManagementVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AdminSellerManagementService {
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerManagementService.class);
	
	@Resource
	AdminSellerManagementMapper mapper;

	/**
	 * 판매자 신청목록
	 * @param managementFM
	 * @return
	 */
	public AdminSellerManagementFM getSellerManagementList(AdminSellerManagementFM managementFM) {

		if("W".equals(managementFM.getStatus())) {

		} else if ("S".equals(managementFM.getStatus())) {
			managementFM.setSellerObj(mapper.getDivAppCount()); // 승인된 건수
		} else if ("X".equals(managementFM.getStatus())) {
			managementFM.setSellerObj(mapper.getLeaveCount()); // 탈퇴된 건수
		}

		managementFM.setRowCount(mapper.getSellerManagementListCount(managementFM)); //총 갯수

		managementFM.setSellerManagementList(mapper.getSellerManagementList(managementFM)); // 목록
		
		return managementFM;
	}
	
	/**
	 * 판매자 상세보기
	 * @param managementFM
	 * @return
	 */
	public AdminSellerManagementFM getSellerManagementView(AdminSellerManagementFM managementFM) {
		
		if (logger.isDebugEnabled()) logger.debug("1111111111111111111===" + managementFM.getMode() + " " + managementFM.getSellerCd());

		// 수정 페이지시에는 상세 조회
		if("modify".equals(managementFM.getMode()) || !"".equals(managementFM.getSellerCd()) ) {
			
			managementFM.setManagementVO(mapper.getSellerManagementView(managementFM));
		} 

		return managementFM;
	}
	
	/**
	 * 비밀번호 초기화
	 * @param managementFM
	 */
	public void updatePwd(AdminSellerManagementFM managementFM){

		// 최초가입시 비밀번호는 아이디 SHA256 방식
		if(managementFM.getId() != null) {
			String getPw = CryptoUtil.getSHA256(managementFM.getId());
			
			managementFM.setPwd(getPw);
			
			mapper.updatePwd(managementFM);
			
			// 아이디만 저장시 메인 테이블만 Insert
			if("W".equals(managementFM.getStatus())) {
				mapper.insertGdSeller(managementFM);
			}
			
		} else {
			throw new BizException("common.00001");
		}

	}
	
	/**
	 * 판매자 등록/수정/삭제
	 * @param managementFM
	 * @return
	 */
	public String getSellerManagementProc(AdminSellerManagementFM managementFM) {
		
		String returnUrl = "";
		
		if("delete".equals(managementFM.getMode())){
			
			//데이터 삭제
			String[] deleteNum = managementFM.getNolist().split(";");

			for(int i=0; i<deleteNum.length; i++){
				mapper.updateSellerType(deleteNum[i]);
			}
			
			returnUrl = "redirect:/shop/admin/seller/list"; 
			return returnUrl;
		}
		
		// 탈퇴처리
		if("exit".equals(managementFM.getMode())){
			
			//데이터 상태
			String[] statusNum = managementFM.getNolist().split(";");

			for(int i=0; i<statusNum.length; i++){
				mapper.updateSellerStatus(statusNum[i]);
			}
			
			returnUrl = "redirect:/shop/admin/seller/confirmList?menuKey=125";
			return returnUrl;
		}
		
		if("modify".equals(managementFM.getMode())) {
			
			int sCount = mapper.getGdSellerCount(managementFM.getSellerCd());
			int mCount = mapper.getGdSellerMngCount(managementFM.getSellerCd());
			int aCount = mapper.getGdSellerAccCount(managementFM.getSellerCd());
			
			// 수정시 기존에 등록된 부분이 있으면 Update 아니면 Inset ( 판매사정보만 저장 후 추후 기본저장도 가능해서 )
			if(sCount > 0) {
				// 비밀번호 암호화 SHA256 방식
				String getPw = CryptoUtil.getSHA256(managementFM.getSellerPw());
				managementFM.setSellerPw(getPw);
				
				mapper.updateGdSeller(managementFM);
			} else {
				mapper.insertGdSeller(managementFM);
			}
			
			if(mCount > 0) {
				mapper.updateGdSellerMng(managementFM);
			} else {
				mapper.insertGdSellerMng(managementFM);
			}
			
			if(aCount > 0) {
				mapper.updateGdSellerAcc(managementFM);
			} else {
				mapper.insertGdSellerAcc(managementFM);
			}
			
			if("list".equals(managementFM.getView())) {
				returnUrl = "redirect:/shop/admin/seller/list?menuKey=122";
			} else {
				returnUrl = "redirect:/shop/admin/seller/confirmList?menuKey=125";
			}

			return returnUrl;
		}

		int dCount = mapper.getDuplicationIdCount(managementFM);
		
		if(dCount > 0) {
			returnUrl = String.valueOf(dCount);
			return returnUrl;
		} else {
			
			if("".equals(managementFM.getMode())) {
				
				// 비밀번호 암호화 SHA256 방식
				String getPw = CryptoUtil.getSHA256(managementFM.getSellerPw());
				managementFM.setPwd(getPw);
				
				managementFM.setStatus("W"); // 최초등록시 대기
				
				// 판매사정보관리
				mapper.insertGdSeller(managementFM);
				
				// 판매사담당자정보관리, 판매사계좌정보
				mapper.insertGdSellerMng(managementFM);
				mapper.insertGdSellerAcc(managementFM);
				
				returnUrl = "redirect:/shop/admin/seller/list?menuKey=122";
				return returnUrl;
			} else {
				throw new BizException("common.00001");
			}
			
		}
		
	}
	
	
	/**
	 * 아이디 발급 저장
	 * @param managementFM
	 */
	public String getSaveId(AdminSellerManagementFM managementFM) {

		if(mapper.getDuplicationIdCount(managementFM) > 0 ) {
			throw new BizException("common.00001");
		} else {

			// 비밀번호암호화는 SHA256 방식
			if(managementFM.getSellerPw() != null) {
				
				String returnUrl = "";
				String getPw = CryptoUtil.getSHA256(managementFM.getSellerPw());
				
				managementFM.setPwd(getPw);
				managementFM.setStatus("W"); // 최초등록시 대기
				
				managementFM.setReguser(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
				managementFM.setModuser(AdminSessionObject.getSessionObject().getUserInfo().getUserId());

				mapper.insertGdSeller(managementFM);
								
				returnUrl = "redirect:/shop/admin/seller/list?menuKey=122";
				return returnUrl;
			} else {
				throw new BizException("common.00001");
			}
		}

	}
	
	/**
	 * 아이디 중복체크
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getDuplicationIdCount(AdminSellerManagementFM managementFM) throws Exception {

		if(!"".equals(managementFM.getId())) {

			managementFM.setDupliCount(mapper.getDuplicationIdCount(managementFM));
			
			return String.valueOf(managementFM);
		} else {
			throw new BizException("common.00001");
		}
		
	}
	
	/**
	 * 판매자코드 중복체크
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getDuplicationSellerCdCount(AdminSellerManagementFM managementFM) throws Exception {

		if(!"".equals(managementFM.getSellerCd())) {

			managementFM.setDupliCount(mapper.getDuplicationSellerCdCount(managementFM));
			
			return String.valueOf(managementFM);
		} else {
			throw new BizException("common.00001");
		}
		
	}

	/**
	 * @param orderExcelVO
	 * @param parameterValues
	 * @return
	 * 주문별or상품별 엑셀 다운로드
	 */
	public List<AdminSellerManagementVO> getSellerXls(AdminSellerManagementFM managementFM) {

		return mapper.getSellerXls(managementFM);
		
	}
	
}
