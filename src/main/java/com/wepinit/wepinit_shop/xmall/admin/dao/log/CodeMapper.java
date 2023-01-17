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
package com.wepinit.wepinit_shop.xmall.admin.dao.log;

import com.wepinit.wepinit_shop.xmall.admin.vo.log.CodeVO;
import com.wepinit.wepinit_shop.xmall.common.vo.DataCodeOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {
	
	/*
	 * 4.기타관리
	 * 
	 * */
	
	//각종코드관리(리스트조회)
	public List<DataCodeOutVO> getDataCodeList(CodeVO codeVO);
	
	//Sort값 조회
	public GdCode getDataSortUpDownSelect(CodeVO codeVO);
	
	//Sort값 조회
	public GdCode getDataSortUpDownSelect2(CodeVO codeVO);
	
	//코드순서변경
	public void updateCodeSortUpDown(CodeVO codeVO);
	
	//코드수정
	public void updateGroupcd(CodeVO codeVO);
	
	//코드등록
	public void insertGroupcd(CodeVO codeVO);	
	
	//Groupcd값 조회
	public List<GdCode> getGroupcdSnoListSelect(CodeVO codeVO);
	
	//코드순서(단건)
	public void updateGroupcdSort(GdCode gdCode);
	
	//코드삭제
	public void deleteGroupcd(CodeVO codeVO);
	
	//코드순서(ALL)
	public void updateGroupcdSortAll(CodeVO gdCode);
	
	
}
