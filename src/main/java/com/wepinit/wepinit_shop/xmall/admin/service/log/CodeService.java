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
package com.wepinit.wepinit_shop.xmall.admin.service.log;

import com.wepinit.wepinit_shop.xmall.admin.dao.log.CodeMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.CodeVO;
import com.wepinit.wepinit_shop.xmall.common.vo.DataCodeOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CodeService {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeService.class);
	
	@Resource
	CodeMapper mapper;
	
	public List<DataCodeOutVO> getDataCodeList(CodeVO codeVO) throws Exception {
		
		return mapper.getDataCodeList(codeVO);
	}
	
	
	public GdCode getDataSortUpDownSelect(CodeVO codeVO) throws Exception {
		
		return mapper.getDataSortUpDownSelect(codeVO);
	}
	
	public GdCode getDataSortUpDownSelect2(CodeVO codeVO) throws Exception {
		
		return mapper.getDataSortUpDownSelect2(codeVO);
	}
	
	public void updateCodeSortUpDown(CodeVO codeVO) throws Exception {
		mapper.updateCodeSortUpDown(codeVO);
	}
	
	public void updateGroupcd(CodeVO codeVO) throws Exception {
		mapper.updateGroupcd(codeVO);
	}
	
	public void insertGroupcd(CodeVO codeVO) throws Exception {
		mapper.insertGroupcd(codeVO);
	}
	
	public List<GdCode> getGroupcdSnoListSelect(CodeVO codeVO) throws Exception {
		
		return mapper.getGroupcdSnoListSelect(codeVO);
	}
	
	public void updateGroupcdSort(GdCode gdCode) throws Exception {
		mapper.updateGroupcdSort(gdCode);
	}
	
	public void deleteGroupcd(CodeVO codeVO) throws Exception {
		mapper.deleteGroupcd(codeVO);
	}
	
	public void updateGroupcdSortAll(CodeVO codeVO) throws Exception {
		mapper.updateGroupcdSortAll(codeVO);
	}
}
