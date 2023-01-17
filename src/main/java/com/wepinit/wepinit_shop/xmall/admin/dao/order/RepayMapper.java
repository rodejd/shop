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
package com.wepinit.wepinit_shop.xmall.admin.dao.order;

import com.wepinit.wepinit_shop.xmall.admin.vo.order.RepayVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrdercancelOrderitemMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderitemOrdercancel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface RepayMapper {

	public int getRepayCount();

	public List<OrdercancelOrderitemMember> getRepayList(RepayVO repayVO);

	public int getRepayList2(OrdercancelOrderitemMember oom);

	public OrderitemOrdercancel getRepayList3(@Param("oCordno")String oCordno);

	public void updateOrderRepay1(RepayVO repayVO);

	public void updateOrderRepay2(RepayVO repayVO);

	public String updateOrderRepay3(RepayVO repayVO);

	public void updateOrderRepay4(RepayVO repayVO);

	public List<HashMap> updateOrderRepay5(RepayVO repayVO);

	public void couponYnStatus(RepayVO repayVO);

	public HashMap libfuncCtlStep1(RepayVO repayVO);
	
	public Map<String,Object> totalDeliveryPrice(Map<String,Object> param);
	
	public String [] getAllSellerCdForRefund(String ordno);
	
	//2020-01-17 이현빈 환불후 쿠폰상태 변경
	public void couponYnStatus1(Map<String,Object> param);
	
	//2020-01-20 이현빈 환불 후 적립금 변경
	public void repayEmoneyPrice(Map<String,Object> param);
	
	//2020-01-29 이현빈 환불 후 적립금 변경
	public void repayReservePrice(Map<String,Object> param);
	
	//2020-01-17 이현빈 사용쿠폰정보 조회
	public String [] getCouponApplySno(Map<String,Object> param);
	
	//2020-01-21 이현빈 전체쿠폰 조회
	public String [] getAllCouponApplySno(String ordno);
	

}
