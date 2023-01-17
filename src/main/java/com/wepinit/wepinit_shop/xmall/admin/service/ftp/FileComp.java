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
import java.util.Comparator;

/**
 * 이 클래스는 파일 이름과 디렉토리를 비교하는 비교 자입니다.
 */
public class FileComp implements Comparator {

	int mode;
	int sign;

	public FileComp() {
		this.mode = 1;
		this.sign = 1;
	}

	/**
	 * @param mode sort by 1=Filename, 2=Size, 3=Date, 4=Type
	 * 기본 정렬 방법은 이름입니다.
	 * 음수 모드는 내림차순 정렬을 의미합니다.
	 */
	public FileComp(int mode) {
		if (mode < 0) {
			this.mode = -mode;
			sign = -1;
		}
		else {
			this.mode = mode;
			this.sign = 1;
		}
	}

	@Override
	public int compare(Object o1, Object o2) {
		File f1 = (File) o1;
		File f2 = (File) o2;
		if (f1.isDirectory()) {
			if (f2.isDirectory()) {
				switch (mode) {
				//Filename or Type
				case 1:
				case 4:
					return sign
							* f1.getAbsolutePath().toUpperCase().compareTo(
									f2.getAbsolutePath().toUpperCase());
				//Filesize
				case 2:
					return sign * (new Long(f1.length()).compareTo(new Long(f2.length())));
				//Date
				case 3:
					return sign
							* (new Long(f1.lastModified())
									.compareTo(new Long(f2.lastModified())));
				default:
					return 1;
				}
			}
			else return -1;
		}
		else if (f2.isDirectory()) return 1;
		else {
			switch (mode) {
			case 1:
				return sign
						* f1.getAbsolutePath().toUpperCase().compareTo(
								f2.getAbsolutePath().toUpperCase());
			case 2:
				return sign * (new Long(f1.length()).compareTo(new Long(f2.length())));
			case 3:
				return sign
						* (new Long(f1.lastModified()).compareTo(new Long(f2.lastModified())));
			case 4: { // Sort by extension
				int tempIndexf1 = f1.getAbsolutePath().lastIndexOf('.');
				int tempIndexf2 = f2.getAbsolutePath().lastIndexOf('.');
				if ((tempIndexf1 == -1) && (tempIndexf2 == -1)) { // Neither have an extension
					return sign
							* f1.getAbsolutePath().toUpperCase().compareTo(
									f2.getAbsolutePath().toUpperCase());
				}
				// f1 has no extension
				else if (tempIndexf1 == -1) return -sign;
				// f2 has no extension
				else if (tempIndexf2 == -1) return sign;
				// Both have an extension
				else {
					String tempEndf1 = f1.getAbsolutePath().toUpperCase()
							.substring(tempIndexf1);
					String tempEndf2 = f2.getAbsolutePath().toUpperCase()
							.substring(tempIndexf2);
					return sign * tempEndf1.compareTo(tempEndf2);
				}
			}
			default:
				return 1;
			}
		}
	}
}
