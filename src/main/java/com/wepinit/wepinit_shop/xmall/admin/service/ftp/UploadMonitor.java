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

import java.util.Hashtable;

public class UploadMonitor {

	static Hashtable<String, UplInfo> uploadTable = new Hashtable<String, UplInfo>();

	//  파일명과 업로드정보를 받아 hashtable 에 저장한다.
	static void set(String fName, UplInfo info) {

		uploadTable.put(fName, info);
	}

	//  업로드 테이블에서 입력받은 파일이름 정보를 삭제한다.
	public static void remove(String fName) {
		uploadTable.remove(fName);
	}

	//  파일이름으로 업로드정보를 찾아 반환.
	public static UplInfo getInfo(String fName) {
		UplInfo info = uploadTable.get(fName);
		return info;
	}
}
