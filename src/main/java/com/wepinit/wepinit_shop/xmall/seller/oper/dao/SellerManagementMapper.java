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
package com.wepinit.wepinit_shop.xmall.seller.oper.dao;

import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerManagementFM;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerManagementVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerManagementMapper {
	
	/**
	 * 판매자 상세보기
	 * @param managementFM
	 * @return
	 */
	public SellerManagementVO getSellerManagementView(SellerManagementFM managementFM);
	
	/**
	 *  비밀번호 초기화
	 * @param managementFM
	 */
	public void updatePwd(SellerManagementFM managementFM);
	
	/**
	 * 판매사담당자정보관리 Insert
	 * @param managementFM
	 */
	public void insertGdSellerMng(SellerManagementFM managementFM);
	
	/**
	 * 판매사계좌정보 Insert
	 * @param managementFM
	 */
	public void insertGdSellerAcc(SellerManagementFM managementFM);
	
	/**
	 * 판매사정보관리 Update
	 * @param managementFM
	 */
	public void updateGdSeller(SellerManagementFM managementFM);
	
	/**
	 * 판매사담당자정보관리 Update
	 * @param managementFM
	 */
	public void updateGdSellerMng(SellerManagementFM managementFM);
	
	/**
	 * 판매사계좌정보 Update
	 * @param managementFM
	 */
	public void updateGdSellerAcc(SellerManagementFM managementFM);
	
	/**
	 * 판매사 수정 
	 * @param managementFM
	 * @return
	 */
	public void getSellerManagementModify(SellerManagementFM managementFM);

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
}
