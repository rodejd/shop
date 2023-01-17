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
package com.wepinit.wepinit_shop.xmall.front.vo.member;

public class FrontMemberSnsVO {

	private String mno;
	private String snsid;
	private String snsemail;
	private String snsnickname;
	private String snstype;
	
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getSnsid() {
		return snsid;
	}
	public void setSnsid(String snsid) {
		this.snsid = snsid;
	}
	public String getSnsemail() {
		return snsemail;
	}
	public void setSnsemail(String snsemail) {
		this.snsemail = snsemail;
	}
	public String getSnsnickname() {
		return snsnickname;
	}
	public void setSnsnickname(String snsnickname) {
		this.snsnickname = snsnickname;
	}
	public String getSnstype() {
		return snstype;
	}
	public void setSnstype(String snstype) {
		this.snstype = snstype;
	}
}
