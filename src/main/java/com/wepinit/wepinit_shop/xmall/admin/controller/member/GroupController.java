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
package com.wepinit.wepinit_shop.xmall.admin.controller.member;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.member.GroupService;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/shop/admin/member/group/*")
public class GroupController {
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class); 
	
	@Resource
	GroupService service;
	//회원그룹관리
	@RequestMapping(value="list")
	public String Group(@ModelAttribute("groupVO") GroupVO groupVO,
	HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		int adminAuth = 0;
		
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
			groupVO.setFirstmenu("회원관리");
			groupVO.setSecondmenu("회원그룹관리");
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ list ==> "+groupVO.toString());
		}
		return "/shop/admin/member/group/list";
	}
	//팝업창
	@RequestMapping(value="popup.register")
	public String PopupGroup(@ModelAttribute("groupVO") GroupVO groupVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
			//sno, adminAuth, mode
			if(groupVO.getSno() != 0){
				groupVO.setGroupList(service.popupGroupList(groupVO.getSno()));
				
				List<GdMemberGrp> list= groupVO.getGroupList();
				String[] arrTmp = new String[list.size()];
				List<String[]> listTmp= new ArrayList<String[]>();
				
				for(GdMemberGrp a :list){
					arrTmp = ShopConfig.getProperty("level"+(a.getkLevel() != 0 ? a.getkLevel() : 0)).split(",");
					listTmp.add(arrTmp);
				}
				groupVO.setMenunmList(listTmp);
			}
			//selectBoxList ---->selectBoxMap
			HashMap<Integer, String> selectBoxMap = new HashMap<Integer, String>();
			for(GdMemberGrp g : service.popupSelectBoxList()){
				selectBoxMap.put(g.getkLevel(), g.getGrpnm());
			}
			groupVO.setSelectBoxMap(selectBoxMap);
			
			if(logger.isDebugEnabled()){
				logger.debug("@@ list popup register==> "+groupVO.toString());
			}
			
			/*model.addAttribute("groupVO", groupVO);
			model.addAttribute("groupData", groupVO.getGroupList().get(0));*/
			
		return "/shop/admin/member/group/popup.register";
	}
	
	@RequestMapping(value="indb")
	public String PopupGroupIndb(@ModelAttribute("groupVO") GroupVO groupVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		logger.debug("@@ groupVo indb" + groupVO.toString());
			//grpnm,kLevel,dc,addEmoney,freeDeliveryfee,moddt
		if(groupVO.getMode().equals("modGrp")){   // 수정
			groupVO.setGroupList(service.popupGroupList(groupVO.getSno()));
			
			
			String[] modGrpMenuCd=null;
			if(groupVO.getMenunm() !=null && groupVO.getMenunm().length >0){
				logger.debug("@@ groupVO indb modGrp"+groupVO.getMenunm().toString());
			 	modGrpMenuCd = groupVO.getMenunm();
			}
			
			String modGrpTmpCd="";
			/*String modGrpTmpNm="";*/
			
			if ( null != modGrpMenuCd && 0 < modGrpMenuCd.length ) {
				/*HashMap<String, String> prop = service.getCodeGroupItemLIST("adminmenu","","");*/
				/*	for(String cd : modGrpMenuCd){*/
						modGrpTmpCd = StringUtil.implode(",", modGrpMenuCd);
						/*modGrpTmpNm += prop.get(cd) + ", ";	*/
					/*}*/
			}
			groupVO.setDc(groupVO.getDc() != 0 ? groupVO.getDc() : 0);
			groupVO.setAddEmoney(groupVO.getAddEmoney() != 0 ? groupVO.getAddEmoney() : 0);
			groupVO.setFreeDeliveryfee(groupVO.getFreeDeliveryfee() != null && !groupVO.getFreeDeliveryfee().equals("") ? groupVO.getFreeDeliveryfee() : "N");
			if(groupVO.getAdminAuth() !=0){
//				requestSet.setProperty("menu_cd", modGrpTmpCd.substring(0, modGrpTmpCd.length()-2));
//				requestSet.setProperty("menu_nm", modGrpTmpNm.substring(0, modGrpTmpNm.length()-2));
			}
			
			service.updateGroup(groupVO);
			ShopConfig.setProperty("level"+groupVO.getkLevel(), modGrpTmpCd);
			groupVO.setResult(1);
			logger.debug("@@@ groupVo bottom"+groupVO.toString());
			return "/shop/admin/member/group/popup.register";
			
			//kLevel
		}else if(groupVO.getMode().equals("delGrp")){  // 삭제
			//회원이 존재할경우 
			
			List<GdMemberGrp> gdMemberGrpList = service.getLevelCount(groupVO.getkLevel());
			logger.debug("@@ list gdMemberGrpList size ==> "+ gdMemberGrpList.size());
			groupVO.setErrorMsg("");  //초기화
			PrintWriter out = null;
			
			for(GdMemberGrp l :  gdMemberGrpList){
				
				if(l.getkLevel() == groupVO.getkLevel()){
					try{
						res.setCharacterEncoding("UTF-8");
						res.setContentType("text/html;charset=UTF-8");
						out = res.getWriter();
						out.print(WebUtil.getAlertRedirect("현재 그룹에 속한 회원이 있습니다. 삭제 후 진행하시기 바랍니다.", "/shop/admin/member/list"));
						out.flush();
					}catch(Exception e){
						throw e;
					} finally{
						if( null != out ){
							out.close();
						}
					}
					return "redirect:/shop/admin/member/group/list?result="+groupVO.getResult()+"&error1="+groupVO.getErrorMsg();
					
				}
			}
				service.deleteGroup(groupVO.getSno());
				
				
			//grpnm,kLevel,dc,addEmoney,freeDeliveryfee,regdt
		}else{   // 생성

			String[] modGrpMenuCd=null;
			if(groupVO.getMenunm() !=null && groupVO.getMenunm().length >0){
				logger.debug("@@ groupVO indb modGrp"+groupVO.getMenunm().toString());
			 	modGrpMenuCd = groupVO.getMenunm();
			}
			
			String modGrpTmpCd="";
			/*String modGrpTmpNm="";*/
			
			if ( null != modGrpMenuCd && 0 < modGrpMenuCd.length ) {
				/*HashMap<String, String> prop = service.getCodeGroupItemLIST("adminmenu","","");*/
				/*	for(String cd : modGrpMenuCd){*/
						modGrpTmpCd = StringUtil.implode(",", modGrpMenuCd);
						/*modGrpTmpNm += prop.get(cd) + ", ";	*/
					/*}*/
			}
			groupVO.setDc(groupVO.getDc() != 0 ? groupVO.getDc() : 0);
			groupVO.setAddEmoney(groupVO.getAddEmoney() != 0 ? groupVO.getAddEmoney() : 0);
			groupVO.setFreeDeliveryfee(groupVO.getFreeDeliveryfee() != null && !groupVO.getFreeDeliveryfee().equals("") ? groupVO.getFreeDeliveryfee() : "N");
			
			service.insertGroup(groupVO);
			ShopConfig.setProperty("level"+groupVO.getkLevel(), modGrpTmpCd);
			groupVO.setResult(1);
			logger.debug("@@@ groupVo bottom"+groupVO.toString());
			return "/shop/admin/member/group/popup.register";
		}
		
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ list popup indb==> "+groupVO.toString());
		}
		if(groupVO.getAdminAuth() >0){
			return "redirect:/shop/admin/basic/adminGroup";
		}else{
			return "redirect:/shop/admin/member/group/list";
		}
	}
	   
}

