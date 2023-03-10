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

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.basic.AdminGroupService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.GroupService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.AdminGroupVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.GroupVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class AdminGroupController {
	private static final Logger logger = LoggerFactory.getLogger(AdminGroupController.class); 
	@Resource
	AdminGroupService AGService;
	
	@Resource
	GroupService service;
	
	@RequestMapping(value = "adminGroup")
	public String adminGroup(@ModelAttribute("AGVO") AdminGroupVO AGVO
			,@ModelAttribute("groupVO") GroupVO groupVO,
			HttpServletRequest req, HttpServletResponse res, Model s) throws Exception {
		logger.debug("@@@@@@ adminGroup indb GrpType ="+AGVO.getGrpType()+"skey= "+AGVO.getSkey()+" sword="+AGVO.getSword());
		// ???????????? > ?????????
		if ( "all".equals(AGVO.getSkey())  ) {
			// ???????????? > ????????? or ??????
			if ( "0".equals(AGVO.getGrpType()) ) {
				AGVO.setGrpList(AGService.getAdminMemberGroupMemberLIST1(AGVO));
			}else{
				AGVO.setGrpList(AGService.getUserMemberGroupMemberLIST1(AGVO));
			}
			
		}else if(!"all".equals(AGVO.getSkey()) ){
			// ???????????? > ????????? or ??????
			if ( "0".equals(AGVO.getGrpType()) ) {
				AGVO.setGrpList(AGService.getAdminMemberGroupMemberLIST2(AGVO));
			}else{
				AGVO.setGrpList(AGService.getUserMemberGroupMemberLIST2(AGVO));
			}
			
		}
		
		// select box??? ????????????
		AGVO.setGrpSelectList(AGService.getMemberGroupSelectboxLIST());
		
		
		// ???????????????
		AGVO.setTotalCount(AGService.getMemberCount());
		
		String skey=AGVO.getSkey();
		
		if(skey.equals("all"))
			skey="";
		
		AGVO.setSkey(skey);
		//???????????????
		//??????
		if(AGVO.getGrpType().equals("1")){
			AGVO.setRowCount(AGService.getUserMemberSearchCount(AGVO));
		//?????????
		}else{
			AGVO.setRowCount(AGService.getAdminMemberSearchCount(AGVO));
		}
		//?????? ?????? ?????????
		if ( "".equals(AGVO.getRowCount())) {
			AGVO.setRowCount(0);
		}
		
		//////////////////?????????????????????//////////////
		int max=0;
		max= AGVO.getRowCount();  // ????????? ????????? ?????? ?????????
		int top = AGVO.getRowStart();
		int[] vnum = new int[max];
		for(int i=0;i<max;i++){
			vnum[i] = max-top  - i;
		}
		AGVO.setVnum(vnum);
		
		
		
		//???????????? ????????????
		AGVO.setNow(DateUtil.getDate());
		////////////////////////////////////////////////////////////////////////////////
		//GroupController ????????? 
		
		int adminAuth = 1;
		
		if(adminAuth >0){
			groupVO.setMemberList(service.groupAdminList());	
		}else{
			groupVO.setMemberList(service.groupList());
		}
		groupVO.setAdminAuth(adminAuth);
		if(groupVO.getFirstmenu()  != null && groupVO.getFirstmenu() != ""){
			groupVO.setFirstmenu(groupVO.getFirstmenu());
			groupVO.setSecondmenu(groupVO.getSecondmenu());
		} else{
			//?????? default
			groupVO.setFirstmenu("????????????");
			groupVO.setSecondmenu("??????????????????");
		}
		List<GdMemberGrp> list= groupVO.getMemberList();
		String[] arrTmp = new String[list.size()];
		List<String[]> listTmp= new ArrayList<String[]>();
		
		for(GdMemberGrp a :list){
			arrTmp = ShopConfig.getProperty("level"+(a.getkLevel() != 0 ? a.getkLevel() : 0)).split(",");
			listTmp.add(arrTmp);
		}
		groupVO.setMenunmList(listTmp);
		
		s.addAttribute("AGVO", AGVO);
		s.addAttribute("groupVO",groupVO);

		
		return "/shop/admin/basic/adminGroup";
	}
	
	@RequestMapping(value = "adminGroup/indb")
	public String adminGroupIndb(@ModelAttribute("AGVO") AdminGroupVO AGVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//adminModify
		if(AGVO.getMode().equals("adminModify")){
			int[] arrMNo = AGVO.getMno();
			String[] arrLevel = AGVO.getLevel();
			if ( null != arrMNo && arrLevel.length >0) {
				for(int i=0;i<arrMNo.length;i++){
					AGService.setMemberLevelUPDATE(Integer.parseInt(arrLevel[i]),arrMNo[i]);
					logger.debug("@@@@ adminGroupIndb level "+arrLevel[i] +" mno ="+arrMNo[i]);
				}
			}
		}
		/*//delete
		if(AGVO.getMode().equals("adminModify")){
			//
		}*/
		logger.debug("@@ adminGroupIndb mode = "+AGVO.getMode()+ " mno =" +AGVO.getMno() + " level =" +AGVO.getLevel());
		return"redirect:/shop/admin/basic/adminGroup";
	}
	
}
