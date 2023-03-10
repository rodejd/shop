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
package com.wepinit.wepinit_shop.xmall.admin.service.basic;

import com.wepinit.wepinit_shop.xmall.admin.dao.basic.AdminMenuMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.AdminMenuVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdAdminMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminMenuService {
	
	@Resource
	AdminMenuMapper mapper;
	
	public List<GdAdminMenu> getAdminMenuList(AdminMenuVO adminMenuVO) throws Exception {
		return mapper.getAdminMenuList(adminMenuVO);
	}
	
	public int getAdminMenuCount(String type) throws Exception {
		return mapper.getAdminMenuCount(type);
	}

	public List<GdAdminMenu> getAdminMenuList2(AdminMenuVO adminMenuVO) {
		// TODO Auto-generated method stub
		return mapper.getAdminMenuList2(adminMenuVO);
	}

	public List<GdAdminMenu> getAdminMenuList3(AdminMenuVO adminMenuVO) {
		// TODO Auto-generated method stub
		return mapper.getAdminMenuList3(adminMenuVO);
	}

	public void updateAdminMenu(AdminMenuVO adminMenuVO) {
		mapper.updateAdminMenu(adminMenuVO);
		
	}

	public void insertAdminMenu(AdminMenuVO adminMenuVO) {
		mapper.insertAdminMenu(adminMenuVO);
	}

	public int getAdminMenuMaxCount() {
		return mapper.getAdminMenuMaxCount();
	}

	public void deleteAdminMenu(AdminMenuVO adminMenuVO) {
		//level1 ?????? ??????
		if(adminMenuVO.getMenulevel()==1){
			throw new BizException("adminMenu.00001");
		}else if(adminMenuVO.getMenulevel()==2){
			//level2 ??????
			//0.?????? ?????? ??????
			List<GdAdminMenu> lsgam = mapper.getAdminMenuList2(adminMenuVO);
			AdminMenuVO nAdminMenuVO = new AdminMenuVO();
			for(int i =0;i<lsgam.size();i++){
				GdAdminMenu gam = lsgam.get(i);
				nAdminMenuVO.setMenukey(gam.getMenukey());
				nAdminMenuVO.setParentkey(gam.getParentkey());
				nAdminMenuVO.setMenuname(gam.getMenuname());
				nAdminMenuVO.setLink(gam.getLink());
				nAdminMenuVO.setMenulevel(gam.getMenulevel());
				nAdminMenuVO.setMenunum(gam.getMenunum());
			}
			if(nAdminMenuVO.getLink().length()>0){
				mapper.deleteAdminMenuLevel24(nAdminMenuVO);
				//1.link??? ????????? ???????????? ??????
				mapper.deleteAdminMenuLevel2(nAdminMenuVO);
			}
			
			//2.?????? ???????????? ??????
			mapper.deleteAdminMenu(adminMenuVO);
		}else if(adminMenuVO.getMenulevel()==3){
			//level3 ??????
			//1.?????? ????????? link?????? level3 ????????? ??????
			List<GdAdminMenu> lsgam = mapper.getAdminMenuLink(adminMenuVO);
			AdminMenuVO nAdminMenuVO = new AdminMenuVO();
			for(int i =0;i<lsgam.size();i++){
				GdAdminMenu gam = lsgam.get(i);
				nAdminMenuVO.setMenukey(gam.getMenukey());
				nAdminMenuVO.setParentkey(gam.getParentkey());
				nAdminMenuVO.setMenuname(gam.getMenuname());
				nAdminMenuVO.setLink(gam.getLink());
				nAdminMenuVO.setMenulevel(gam.getMenulevel());
				nAdminMenuVO.setMenunum(gam.getMenunum());
			}
			if(nAdminMenuVO.getLink().length()>0){
				String tmp[] = nAdminMenuVO.getLink().split(",");
				String link ="";
				for(int i=0;i<tmp.length;i++){
					if(!tmp[i].equals(adminMenuVO.getMenukey()+"")){
						if(i!=0){
							link +=","+tmp[i]; 
						}else{
							link +=tmp[i];
						}
					}
				}
				nAdminMenuVO.setLink(link);
				mapper.updateAdminMenu(nAdminMenuVO);
			}
			//2.?????? ???????????? ??????
			mapper.deleteAdminMenu(adminMenuVO);
			//0.?????? ?????? ??????
			mapper.deleteAdminMenuLevel4(adminMenuVO);
		}else if(adminMenuVO.getMenulevel()>3){
			//level4 ?????? ??????
			//1.parentkey??? ?????? ?????? ???????????? ?????? ??????
			mapper.deleteAdminMenuLevel4(adminMenuVO);
			//2.?????? ???????????? ??????
			mapper.deleteAdminMenu(adminMenuVO);
		}
	}
	
	public void updateAdminMenuSubUse(AdminMenuVO adminMenuVO){
		//??????????????? 1,2??? ?????? ?????? ????????? ?????????????????? ??????
		String linkArr[] = mapper.getAdminMenuSubLink(adminMenuVO.getMenukey()).split(",");
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("linkArr", linkArr);
		param.put("usemenu", adminMenuVO.getUsemenu());
		mapper.updateAdminMunuSubUse(param);
		
	}
}
