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
package com.wepinit.wepinit_shop.xmall.admin.dao.seller;

import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerManagementFM;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerManagementVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminSellerManagementMapper {
	
	/**
	 * 총 승인 / 오늘 승인된 건수
	 * @return
	 */
	public AdminSellerManagementFM getDivAppCount();

	/**
	 * 판매자 탈퇴 건수
	 * @param managementFM
	 * @return
	 */
	public AdminSellerManagementFM getLeaveCount();
	
	/**
	 * 판매자 신청목록 (카운트)
	 * @param managementFM
	 * @return
	 */
	public int getSellerManagementListCount(AdminSellerManagementFM managementFM);

	/**
	 * 판매자 신청목록
	 * @param managementFM
	 * @return
	 */
	public List<AdminSellerManagementVO> getSellerManagementList(AdminSellerManagementFM managementFM);

	/**
	 * 판매자 상세보기
	 * @param managementFM
	 * @return
	 */
	public AdminSellerManagementVO getSellerManagementView(AdminSellerManagementFM managementFM);
	
	/**
	 *  비밀번호 초기화
	 * @param managementFM
	 */
	public void updatePwd(AdminSellerManagementFM managementFM);
	
	/**
	 * 판매사정보관리 Insert
	 * @param managementFM
	 */
	public void insertGdSeller(AdminSellerManagementFM managementFM);
	
	/**
	 * 판매사담당자정보관리 Insert
	 * @param managementFM
	 */
	public void insertGdSellerMng(AdminSellerManagementFM managementFM);
	
	/**
	 * 판매사계좌정보 Insert
	 * @param managementFM
	 */
	public void insertGdSellerAcc(AdminSellerManagementFM managementFM);
	
	/**
	 * 판매사정보관리 Update
	 * @param managementFM
	 */
	public void updateGdSeller(AdminSellerManagementFM managementFM);
	
	/**
	 * 판매사담당자정보관리 Update
	 * @param managementFM
	 */
	public void updateGdSellerMng(AdminSellerManagementFM managementFM);
	
	/**
	 * 판매사계좌정보 Update
	 * @param managementFM
	 */
	public void updateGdSellerAcc(AdminSellerManagementFM managementFM);
	
	/**
	 * 아이디 발급 저장
	 * @param managementFM
	 * @return
	 */
	public String getSaveId(AdminSellerManagementFM managementFM);
	
	/**
	 * 판매사 등록/수정/삭제 
	 * @param managementFM
	 * @return
	 */
	public String getSellerManagementProc(AdminSellerManagementFM managementFM);
	
	/**
	 * 아이디 중복체크
	 * @param id
	 * @return
	 */
	public int getDuplicationIdCount(AdminSellerManagementFM managementFM);
	
	/**
	 * 아이디 중복체크
	 * @param id
	 * @return
	 */
	public int getDuplicationSellerCdCount(AdminSellerManagementFM managementFM);

	/**
	 * 삭제시 타입변경
	 * @param sellerCode
	 */
	public void updateSellerType(String sellerCode);
	
	/**
	 * 상태 변경
	 * @param sellerCode
	 */
	public void updateSellerStatus(String sellerCode);
	
	/**
	 * 판매사정보관리 중복체크
	 * @param sellerCode
	 * @return
	 */
	public int getGdSellerCount(String sellerCode);
	
	/**
	 * 판매사담당자정보관리 중복체크
	 * @param managementFM
	 */
	public int getGdSellerMngCount(String sellerCode);
	
	/**
	 * 판매사계좌정보 중복체크
	 * @param managementFM
	 */
	public int getGdSellerAccCount(String sellerCode);
	
	/**
	 * 엑셀 다운로드
	 * @param managementFM
	 * @return
	 */
	public List<AdminSellerManagementVO> getSellerXls(AdminSellerManagementFM managementFM);
}
