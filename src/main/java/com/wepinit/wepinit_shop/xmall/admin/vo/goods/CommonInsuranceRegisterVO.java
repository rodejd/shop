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

import org.springframework.web.multipart.MultipartFile;

public class CommonInsuranceRegisterVO {
	private String imgFlag;
	private MultipartFile img;
	private String old_img;
	
	public String getImgFlag() {
		return imgFlag;
	}
	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	public String getOld_img() {
		return old_img;
	}
	public void setOld_img(String old_img) {
		this.old_img = old_img;
	}
	@Override
	public String toString() {
		return "CommonInsuranceRegisterVO [imgFlag=" + imgFlag + ", img=" + img
				+ ", old_img=" + old_img + "]";
	}

	
	
}
