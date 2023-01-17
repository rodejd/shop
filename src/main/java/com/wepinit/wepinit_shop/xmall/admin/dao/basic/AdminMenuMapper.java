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
package com.wepinit.wepinit_shop.xmall.admin.dao.basic;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.AdminMenuVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdAdminMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMenuMapper {

	int getAdminMenuCount(String type);

	List<GdAdminMenu> getAdminMenuList(AdminMenuVO adminMenuVO);

	List<GdAdminMenu> getAdminMenuList2(AdminMenuVO adminMenuVO);

	List<GdAdminMenu> getAdminMenuList3(AdminMenuVO adminMenuVO);

	void updateAdminMenu(AdminMenuVO adminMenuVO);

	void insertAdminMenu(AdminMenuVO adminMenuVO);

	int getAdminMenuMaxCount();

	void deleteAdminMenu(AdminMenuVO adminMenuVO);

	void deleteAdminMenuLevel2(AdminMenuVO adminMenuVO);

	void deleteAdminMenuLevel4(AdminMenuVO adminMenuVO);

	void deleteAdminMenuLevel24(AdminMenuVO nAdminMenuVO);

	List<GdAdminMenu> getAdminMenuLink(AdminMenuVO adminMenuVO);
	
	String getAdminMenuSubLink(int menuKey);
	
	void updateAdminMunuSubUse(Map<String, Object> param);
}
