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
package com.wepinit.wepinit_shop.xmall.seller.basic.dao;

import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryOverPolicyVO;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryPolicyInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SellerDeliveryPolicyMapper {
	/**sellerCd 를 이용하여 판매사의 기본배송정책을 가져옵니다.*/
	public SellerDeliveryPolicyInfoVO getSellerDeliveryPolicy(@Param("sellerCd") String sellerCd);

	/**sellerCd 를 이용하여 판매사가 설정한 지역별 배송비정책 리스트를 가져옵니다.*/
	public List<SellerDeliveryOverPolicyVO> getSellerDeliveryOverPolicy(@Param("sellerCd") String sellerCd);
	
	/**useDeliveryComp(판매사별 이용 택배사 코드) 를 이용하여 판매사가 이용중인 택배사 정보 리스트를 가져옵니다.*/
	public List<GdListDelivery> getSellerUseDeliveryCompList(@Param("useDeliveryCompanies") String[] useDeliveryCompanies); 

	/**판매사 배송정책을 저장합니다.*/
	public void updateSellerDeliveryPolicy(SellerDeliveryPolicyInfoVO infoVO);
	public void deleteSellerDeliveryOverPolicy(@Param("sellerCd") String sellerCd);
	public void insertSellerDeliveryOverPolicy(SellerDeliveryPolicyInfoVO infoVO);
}
