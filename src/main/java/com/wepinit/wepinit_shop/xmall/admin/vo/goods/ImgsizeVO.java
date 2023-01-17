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
package com.wepinit.wepinit_shop.xmall.admin.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;

import java.util.List;

public class ImgsizeVO {
	private String mode;
	private String imgI;
	private String imgS;
	private String imgL;
	private String imgM;
	private List<GdConfSet> getImgList;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getImgI() {
		return imgI;
	}
	public void setImgI(String imgI) {
		this.imgI = imgI;
	}
	public String getImgS() {
		return imgS;
	}
	public void setImgS(String imgS) {
		this.imgS = imgS;
	}
	public String getImgL() {
		return imgL;
	}
	public void setImgL(String imgL) {
		this.imgL = imgL;
	}
	public String getImgM() {
		return imgM;
	}
	public void setImgM(String imgM) {
		this.imgM = imgM;
	}
	public List<GdConfSet> getGetImgList() {
		return getImgList;
	}
	public void setGetImgList(List<GdConfSet> getImgList) {
		this.getImgList = getImgList;
	}
	
	@Override
	public String toString() {
		return "ImgsizeVO [mode=" + mode
				+ ", imgI=" + imgI
				+ ", imgS=" + imgS
				+ ", imgL=" + imgL
				+ ", imgM="	+ imgM 
				+ ", getImgList=" + getImgList + "]";
	}	
}
