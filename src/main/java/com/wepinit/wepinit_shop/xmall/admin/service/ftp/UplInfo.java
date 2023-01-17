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

public class UplInfo {
	public long totalSize;
	public long currSize;
	public long starttime;
	public boolean aborted;
	public String convertCurrSize;
	public String convertTotalSize;
	
	// 생성자 초기화블럭에서 시작시간은 무조건 현재시간으로 설정 (클래스가 생성되는 순간)
	public UplInfo() {
		totalSize = 0l;
		currSize = 0l;
		starttime = System.currentTimeMillis();
		aborted = false;
		convertCurrSize = FTPDefault.convertFileSize(currSize);
		convertTotalSize = FTPDefault.convertFileSize(totalSize);
	}

	public UplInfo(int size) {
		totalSize = size;
		currSize = 0;
		starttime = System.currentTimeMillis();
		aborted = false;
		convertCurrSize = FTPDefault.convertFileSize(currSize);
		convertTotalSize = FTPDefault.convertFileSize(totalSize);
	}

	
	public String getUprate() {
		long time = System.currentTimeMillis() - starttime;
		if (time != 0) {
			long uprate = currSize * 1000 / time;
			return FTPDefault.convertFileSize(uprate) + "/s";
		}
		else return "n/a";
	}

	// 퍼센트 구하기?
	public int getPercent() {
		if (totalSize == 0) return 0;
		else return (int) (currSize * 100 / totalSize);
	}

	// 경과시간 구하기?
	public String getTimeElapsed() {
		long time = (System.currentTimeMillis() - starttime) / 1000l;
		if (time - 60l >= 0){
			if (time % 60 >=10) return time / 60 + ":" + (time % 60) + "m";
			else return time / 60 + ":0" + (time % 60) + "m";
		}
		else return time<10 ? "0" + time + "s": time + "s";
	}

	// 예상시간 구하기?
	public String getTimeEstimated() {
		if (currSize == 0) return "n/a";
		long time = System.currentTimeMillis() - starttime;
		time = totalSize * time / currSize;
		time /= 1000l;
		if (time - 60l >= 0){
			if (time % 60 >=10) return time / 60 + ":" + (time % 60) + "m";
			else return time / 60 + ":0" + (time % 60) + "m";
		}
		else return time<10 ? "0" + time + "s": time + "s";
	}
}
