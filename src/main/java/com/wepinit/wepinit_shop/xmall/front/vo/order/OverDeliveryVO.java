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

public class OverDeliveryVO {
	private String sellerCd;
	private String goodsno;
	private String sido;
	private String price;
	
	public OverDeliveryVO() {}
	
	public OverDeliveryVO(String goodsno, String sido, String price) {
		super();
		this.goodsno = goodsno;
		this.sido = sido;
		this.price = price;
	}

	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	@Override
	public String toString() {
		return "OverDeliveryVO [sellerCd=" + sellerCd + ", goodsno=" + goodsno
				+ ", sido=" + sido + ", price=" + price + "]";
	}
}