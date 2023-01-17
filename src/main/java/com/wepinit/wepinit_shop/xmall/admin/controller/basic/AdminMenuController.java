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
package com.wepinit.wepinit_shop.xmall.admin.controller.basic;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.basic.AdminMenuService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.AdminMenuVO;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.GdAdminMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/shop/admin/basic/*")
public class AdminMenuController {

	private static final Logger logger = LoggerFactory.getLogger(AdminMenuController.class); 
	
	@Resource
	AdminMenuService service;
	
	@RequestMapping(value = "adminMenu") //쇼핑몰기본관리_기본관리_관리자메뉴관리 
	public String adminMenu(@ModelAttribute("adminMenuVO") AdminMenuVO adminMenuVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		/*
		 * 1.쇼핑몰 권한관리
		 * 
		 * */
		ShopLibFunction.menuAuthPermissionCheck(req, res,"basic");
		
		if(logger.isDebugEnabled()) {
			logger.debug("################adminMenu:: ");
		}
		return "/shop/admin/basic/adminMenu";
	}
	@RequestMapping(value="adminMenuSub.doJson", method = RequestMethod.GET) //adminMenu.jsp에서 넘기는 json jsp로표시
	@ResponseBody
	public List adminMenuSub(@ModelAttribute("adminMenuVO")AdminMenuVO adminMenuVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		logger.debug("=========adminMenuSub.doJson");
		String key = req.getParameter("key");
		String[] keys= key.split("_");
		int parentkey =0;
		int menulevel =Integer.parseInt(keys[2]);
		boolean levelChk=false;
		logger.debug("=========adminMenuSub.doJson::key-->"+key);
		parentkey =Integer.parseInt(keys[0]);
		
		List<GdAdminMenu> lsgam = null;
		if(menulevel==2){
			levelChk=true;
			adminMenuVO.setMenukey(Integer.parseInt(keys[0]));
			lsgam = service.getAdminMenuList2(adminMenuVO);
			String tmp = null;
			int tmp2 = 0;
			for(int i =0;i<lsgam.size();i++){
				GdAdminMenu gam = lsgam.get(i);
				tmp=gam.getLink();
				tmp2=gam.getMenulevel();
			}
			adminMenuVO.setMenulevel(tmp2+1);
			adminMenuVO.setMenuname(tmp);
			if(tmp!=null && !tmp.equals("")){
				lsgam = service.getAdminMenuList3(adminMenuVO);	
			}else{
				lsgam=null;
			}
		}else{
			adminMenuVO.setParentkey(parentkey);
			adminMenuVO.setMenulevel(menulevel+1);
			logger.debug("=========adminMenuSub.doJson::adminMenuVO.getMenulevel-->"+adminMenuVO.getMenulevel());
			logger.debug("=========adminMenuSub.doJson::adminMenuVO-->"+adminMenuVO);
			lsgam = service.getAdminMenuList(adminMenuVO);
		}
		
		List lt = new ArrayList();
		if(lsgam != null){
			for(int i =0;i<lsgam.size();i++){
				GdAdminMenu gam = lsgam.get(i);
				HashMap hm = new HashMap();
				hm.put("key",gam.getMenukey()+"_"+gam.getParentkey()+"_"+gam.getMenulevel());
				hm.put("title",gam.getMenuname());
				hm.put("isFolder",true);
				hm.put("isLazy",true);
				hm.put("upperFolder",gam.getParentkey());
				
				hm.put("menukey",gam.getMenukey());
				hm.put("parentkey",gam.getParentkey());
				hm.put("menuname",gam.getMenuname());
				hm.put("link",gam.getLink());
				hm.put("menulevel",gam.getMenulevel());
				hm.put("menunum",gam.getMenunum());
				hm.put("parentmenuname", StringUtil.nullConv(gam.getParentmenuname(), "") );
				hm.put("usemenu", gam.getUsemenu());
				lt.add(hm);
			}
		}
		return lt;
	}
	@RequestMapping(value="adminMenuIndb.doJson") //adminMenu.jsp에서 받아온 값 db로 저장
	public String adminMenuIndb(@ModelAttribute("adminMenuVO")AdminMenuVO adminMenuVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		logger.debug("=========adminMenuIndb.doJson");
		logger.debug("==adminMenuVO::::"+adminMenuVO);
		logger.debug("=========adminMenuIndb.doJson::mode==>"+req.getParameter("mode"));
		if(req.getParameter("mode").equals("update")){
			//수정
			service.updateAdminMenu(adminMenuVO);
			//2017-08-21 추가 - 메뉴사용여부 수정
			if(adminMenuVO.getMenulevel() == 1 || adminMenuVO.getMenulevel() == 2){
				if(logger.isDebugEnabled()){
					logger.debug("@@ menuLevel >> " + adminMenuVO.getMenulevel());
				}
				service.updateAdminMenuSubUse(adminMenuVO);
			}
			//2017-08-21 추가 끝
		}else if(req.getParameter("mode").equals("register")){
			//등록
			int menulevel = adminMenuVO.getMenulevel();
			int menukey = service.getAdminMenuMaxCount();
			int parentkey = adminMenuVO.getParentkey();
			
			AdminMenuVO nAdminMenuVO = new AdminMenuVO();
			nAdminMenuVO.setMenukey(menukey+1);
			if(menulevel>=3){
				parentkey = adminMenuVO.getMenukey();
			}
			if(menulevel==1){
				parentkey = adminMenuVO.getMenukey();
			}
			nAdminMenuVO.setParentkey(parentkey);
			nAdminMenuVO.setMenulevel(menulevel+1);
			nAdminMenuVO.setMenuname(adminMenuVO.getNewmenuname());
			nAdminMenuVO.setLink(adminMenuVO.getNewlink());
			nAdminMenuVO.setMenunum(adminMenuVO.getMenunum()+1);
			nAdminMenuVO.setUsemenu(adminMenuVO.getUsemenu());
			service.insertAdminMenu(nAdminMenuVO);
			if(menulevel==2){
				if(adminMenuVO.getLink() != null && adminMenuVO.getLink().length()>0){
					adminMenuVO.setLink(adminMenuVO.getLink()+","+nAdminMenuVO.getMenukey());	
				}else{
					adminMenuVO.setLink(nAdminMenuVO.getMenukey()+"");
				}
				service.updateAdminMenu(adminMenuVO);
			}

		}else{
			//삭제
			service.deleteAdminMenu(adminMenuVO);
		}
		return "doJson";
	}
}
