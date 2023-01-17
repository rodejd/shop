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
package com.wepinit.wepinit_shop.xmall.front.vo.order;

import java.util.Arrays;
import java.util.List;

// 2017-08-25 : 추가배송비 리턴시 필요한 VO 추가
public class FrontAddOverDeliveryVO {
	private String addressSido;
	private String[] goodsnos;
	
	private List<OverDeliveryVO> overDeliveryVO;
	
	public String getAddressSido() {
		return addressSido;
	}
	public void setAddressSido(String addressSido) {
		this.addressSido = addressSido;
	}
	public String[] getGoodsnos() {
		return goodsnos;
	}
	public void setGoodsnos(String goodsnos) {
		this.goodsnos = goodsnos.split(",");
	}
	public List<OverDeliveryVO> getOverDeliveryVO() {
		return overDeliveryVO;
	}
	public void setOverDeliveryVO(List<OverDeliveryVO> overDeliveryVO) {
		this.overDeliveryVO = overDeliveryVO;
	}
	public void setGoodsnos(String[] goodsnos) {
		this.goodsnos = goodsnos;
	}
	
	@Override
	public String toString() {
		return "FrontAddOverDeliveryVO [addressSido=" + addressSido
				+ ", goodsnos=" + Arrays.toString(goodsnos)
				+ ", overDeliveryVO=" + overDeliveryVO + "]";
	}
	
}

