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
package com.wepinit.wepinit_shop.xmall.admin.vo.basic;

import java.util.List;

public class SearchWordVO {
	private String word = "";
	private String mark = "";
	
	private List<SearchWordVO> recommendList = null;
	private List<SearchWordVO> popularList = null;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public List<SearchWordVO> getRecommendList() {
		return recommendList;
	}
	public void setRecommendList(List<SearchWordVO> recommendList) {
		this.recommendList = recommendList;
	}
	public List<SearchWordVO> getPopularList() {
		return popularList;
	}
	public void setPopularList(List<SearchWordVO> popularList) {
		this.popularList = popularList;
	}
	
	@Override
	public String toString() {
		return "SearchWordVO [word=" + word + ", mark=" + mark + ", recommendList=" + recommendList + ", popularList=" + popularList + "]";
	}
}
