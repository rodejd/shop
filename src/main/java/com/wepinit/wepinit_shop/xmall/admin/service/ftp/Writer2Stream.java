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

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
* Writer 주위에 OutputStream를 랩하는 Wrapperclass입니다.
*/
public class Writer2Stream extends OutputStream {

	Writer out;

	public Writer2Stream(Writer w) {
		super();
		out = w;
	}

	@Override
	public void write(int i) throws IOException {
		out.write(i);
	}

	@Override
	public void write(byte[] b) throws IOException {
		for (int i = 0; i < b.length; i++) {
			int n = b[i];
			//Convert byte to ubyte
			n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
			out.write(n);
		}
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		for (int i = off; i < off + len; i++) {
			int n = b[i];
			n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
			out.write(n);
		}
	}
} //End of class Writer2Stream

