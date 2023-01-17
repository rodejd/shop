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
package com.wepinit.wepinit_shop.xmall.admin.service.ftp;

import java.io.File;

// 파일정보 클래스
public class FileInfo {

	public String name = null, clientFileName = null, fileContentType = null;
	private byte[] fileContents = null;
	public File file = null;
	public StringBuffer sb = new StringBuffer(100);

	// 파일 컨텐츠 세팅
	public void setFileContents(byte[] aByteArray) {
		fileContents = new byte[aByteArray.length];
		// System.arraycopy(
		//					Object src, 	원본
		//					int srcPos,		원본으로부터 읽어들일 위치
		//					Object dest,	복사하려는 대상
		//					int destPos,	복사본의 어느 위치에 복사할지에 대한 복사 위치
		//					int length);	원본에서 얼만큼 읽어들일지 크기
		System.arraycopy(aByteArray, 0, fileContents, 0, aByteArray.length);
	}
}