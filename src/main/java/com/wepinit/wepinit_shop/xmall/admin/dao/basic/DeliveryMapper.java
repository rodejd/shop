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
package com.wepinit.wepinit_shop.xmall.admin.dao.basic;

import com.wepinit.wepinit_shop.xmall.admin.vo.basic.DeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdDeliveryPolicy;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliveryMapper {
	public List<GdListDelivery> getDeliveryList();
	public List<GdDeliveryPolicy> getDeliveryPolicyList();
	public void setDeliveryListUseyn(@Param("deliveryno")String deliveryno,@Param("useyn")String useyn);
	public void initDeliveryPolicy();
	public void insertDeliveryPolicy(@Param("no") int no,@Param("rDelivery")String r_delivery, @Param("rFree")String r_free, @Param("rDeliType")String r_deliType, @Param("rDefault")String r_default, @Param("rDefaultMsg")String r_default_msg);
	public List<GdListDelivery> getPopupDeliveryList(@Param("no")String no);
	public void setDeliveryListCompAndUrl(DeliveryVO deliveryVO);
	
	// 2017-08-23 : 택배사 등록을 위하여 추가
	public void insertDeliveryListCompAndUrl(DeliveryVO deliveryVO);
	// 2017-08-23 : 택배사 삭제를 위하여 추가
	public void deleteCourier(@Param("deliveryno")String deliveryno);

	GdListDelivery deliverynoCheck(@Param("deliveryno") String paramString);	
}
