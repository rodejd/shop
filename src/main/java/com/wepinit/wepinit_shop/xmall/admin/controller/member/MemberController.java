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

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.CodeUtil;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.EmailCfgVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberHackVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogHack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


@Controller
@RequestMapping("/shop/admin/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Resource
	MemberService service;
	
	/*
	 * ????????????
	 * ???????????????
	 */
	@RequestMapping(value="list")
	public String list(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		// ???????????? ?????? ?????? ??????
		ShopLibFunction.menuAuthPermissionCheck(req, res, "member");

		Map<String, Object> param = new HashMap<String, Object>();

		/*
		 * ????????????(1)
		 * 
		 * */
		// ????????????
		param.put("skey", memberVO.getSkey());
		// ?????????
		param.put("sword", memberVO.getSword());
		// ??????(????????????????????????)
		if("resno".equals(memberVO.getSkey())){
			String tmp = StringUtil.fillSpace(memberVO.getSword().replaceAll("-", ""),13);
			param.put("resno1", tmp.substring(0,6));
			param.put("resno2", tmp.substring(6,13));
		}
		// ????????????
		param.put("sstatus", memberVO.getSstatus());
		// ??????
		param.put("slevel", memberVO.getSlevel());
		
		/*
		 * ????????????(2)
		 * 
		 * */
		// ?????????
		param.put("ssum_salemin", memberVO.getSsum_salemin());
		param.put("ssum_salemax", memberVO.getSsum_salemax());
		//?????????
		param.put("semoneymin", memberVO.getSemoneymin());
		param.put("semoneymax", memberVO.getSemoneymax());
		
		/*
		 * ????????????(3)
		 * 
		 * */
		
		//?????????
		param.put("sregdt_0", memberVO.getSregdt_0());
		param.put("sregdt_1", memberVO.getSregdt_1());
		//???????????????
		param.put("slastdt_0", memberVO.getSlastdt_0());
		param.put("slastdt_1", memberVO.getSlastdt_1());
		// ????????????
		param.put("sorderdt_0", memberVO.getSorderdt_0());
		param.put("sorderdt_1", memberVO.getSorderdt_1());
		
		/*
		 * ????????????(4)
		 * 
		 * */
		// ??????, ??????, SMS ????????????
		param.put("sex", memberVO.getSex());
		param.put("mailling", memberVO.getSmailling());
		
		// ????????????, ??????????????????
		param.put("scnt_login", memberVO.getScnt_login());				// ???????????? ??????
		param.put("scnt_loginmin", memberVO.getScnt_loginmin());		// ???????????? min
		param.put("scnt_loginmax", memberVO.getScnt_loginmax());		// ???????????? max
		
		if(!"".equals(memberVO.getDormancy()) && null != memberVO.getDormancy() ){
			param.put("dormancy", DateUtil.getDateFrom("yyyyMMdd", "-" + memberVO.getDormancy()+ "d"));		// ????????????
		}
		
		// ????????????, ????????????/???????????????
		param.put("birthtype", memberVO.getBirthtype());
		param.put("birthdatemin", memberVO.getBirthdatemin());
		param.put("birthdatemax", memberVO.getBirthdatemax());
		
		param.put("marriyn", memberVO.getSmarriyn());
		param.put("marridatemin", memberVO.getMarridatemin());
		param.put("marridatemax", memberVO.getMarridatemax());
		
		param.put("schSkin", memberVO.getSchSkin()); //??????
		param.put("scustomsnum", memberVO.getScustomsnum()); // ??????????????????
		param.put("spush", memberVO.getSpush()); // Push????????????
		
		// sort
		param.put("sort", memberVO.getSort());
		
		//??? ??????
		memberVO.setTotalCount(service.getMemberTotalCount());
		//?????? ??? ??????
		memberVO.setRowCount(service.getMemberCount(param));
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, memberVO.getPageSize());
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, memberVO.getRowStart());
		// ?????? ????????? ??????
		memberVO.setGdMemberList(service.getMemberList(param)); 	// ???????????? > ?????????????????? > SMS????????????(???????????????)
		
		return "shop/admin/member/list";
	}
	
	/**
	 * ????????????
	 * ??????????????? ????????????
	 */
	@RequestMapping(value="delete.dojson", method=RequestMethod.POST)
	public String delete(@ModelAttribute("memberVO") MemberVO memberVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
			// ???????????? ?????? ?????? ??????
			service.memberMenuAuth(req, res);
		
			service.deleteMember(req, memberVO.getDeleteMember());
		
		return "dojson";
	}
	
	/*
	 * ????????????
	 * ??????????????? ???????????????
	 */
	@RequestMapping(value="register")
	public String memberModify(@ModelAttribute("memberVO")MemberVO memberVO) throws Exception{
		/*if(logger.isDebugEnabled()){
			logger.debug("@@ member register >> " + memberVO);
			logger.debug("@@ member register  id >> " + memberVO.getMid());
		}*/
		
		if("modify".equals(memberVO.getMode())){
			memberVO.setMemberObj(service.getMemberInfo(memberVO.getMid()));
		}
		
		/*if(logger.isDebugEnabled()){
			logger.debug("@@ info11 >>" + memberVO);
			logger.debug("@@ info22 >> "+ memberVO.getMemberObj());
			logger.debug("@@ info33 >> "+ memberVO.getPageSize() +" >> "+ memberVO.getPageNo());
		}*/
		
		return "/shop/admin/member/info";
	}
	
	/*
	 * ????????????
	 * ??????????????? ??????/?????? ??????
	 */
	@RequestMapping(value="indb")
	public String memberIndb(@ModelAttribute("memberVO")MemberVO memberVO, Model model , HttpServletRequest req) throws Exception{
		String returnUrl = "";
		
		if("modify".equals(memberVO.getMode())){
			if (memberVO.getInterest() != null) {
				if (memberVO.getInterest().length > 0) {
					for (int i = 0; i < memberVO.getInterest().length; i++) {
						memberVO.setValinterest(memberVO.getValinterest()+ new Double(Math.pow(2.0,StringUtil.N2D(memberVO.getInterest()[i]))).intValue());
					}
				}
			}
			//????????? ??????
			service.updateMemberInfo(memberVO);
			model.addAttribute("result", "success");
			returnUrl = "/shop/admin/member/info";
		} else if("delete".equals(memberVO.getMode())){
			//????????? ??????
			String[] deleteNum = memberVO.getNolist().split(";");
			for(int i=0; i<deleteNum.length; i++){
				service.deleteMemberInfo(Integer.parseInt(deleteNum[i]), req);
			}
			returnUrl = "redirect:/shop/admin/member/list";
		} else if("emoney_add".equals(memberVO.getMode())) {
			//????????? ??????
			if("direct".equals(memberVO.getMemo()) & memberVO.getDirectmemo() != null){
				memberVO.setMemo(memberVO.getDirectmemo());
			}
			// ??????&?????? ????????? ??????
			if( memberVO.getEmoney() >= 0 ) {
				memberVO.setGbn("S");
			}else {
				memberVO.setGbn("E");
			}
			service.insertMemberLogEmoney(memberVO);
			service.updateMemberEmoney(memberVO.getEmoney(), memberVO.getMno());
			returnUrl = "redirect:/shop/admin/member/popup.emoney?mno="+memberVO.getMno();
		} else if("emoney_delete".equals(memberVO.getMode())) {
			//????????? ?????? ??????
			memberVO.setEmoneyObj(service.getMemberLogEmoneyInfo(memberVO.getSno()));
			service.deleteMemberLogEmoney(memberVO.getSno());
			service.updateMemberEmoney(-Integer.parseInt(String.valueOf(memberVO.getEmoneyObj().get("emoney"))), Integer.valueOf(String.valueOf(memberVO.getEmoneyObj().get("mno"))));
			returnUrl = "redirect:/shop/admin/member/popup.emoney?mno="+memberVO.getEmoneyObj().get("mno");
		} else {
			throw new BizException("common.000041");
		}
		
		return returnUrl;
	}
	
	/*
	 * ????????????
	 * crm_view
	 * ?????? ?????? ??????
	 */
	@RequestMapping(value="crm_view")
	public String crmView(@ModelAttribute("memberVO")MemberVO memberVO){
		if(logger.isDebugEnabled()){
			logger.debug("@@ member crmview ");
		}
		memberVO.setMemberObj(service.getMemberInfo(memberVO.getMid()));
		return "/shop/admin/member/crm_view";
	}
	
	/*
	 * ????????????
	 * popup.emoney
	 * ????????? ?????? ??????
	 */
	@RequestMapping(value="popup.emoney")
	public String popupEmoney(@ModelAttribute("memberVO")MemberVO memberVO){
		//?????? 
		memberVO.setEmoneyObj(service.getMemberEmoneyInfo(memberVO.getMno()));
		//?????? ????????? ?????? ??????
		memberVO.setEmoneyLogList(service.getMemberLogEmoney(memberVO.getMno(), memberVO));
		
		return "/shop/admin/member/popup.emoney";
	}
	
	/*
	 * ????????????
	 * orderlist
	 * ???????????? ??????
	 */
	@RequestMapping(value="orderlist")
	public String memberOrderList(@ModelAttribute("memberVO")MemberVO memberVO){
		if(logger.isDebugEnabled()){
			logger.debug("@@ member orderList");
		}
		memberVO.setMemberObj(service.getMemberInfoMno(memberVO.getMno()));
		memberVO.setOrderList(service.getMemberOrderList(memberVO.getMno(), memberVO));
		
		return "/shop/admin/member/orderlist";
	}
	
	/**
	 * ?????? ??????/?????? ??????
	 * @param hackVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="hack", method={RequestMethod.POST, RequestMethod.GET})
	public String hack(@ModelAttribute("hackVO") MemberHackVO hackVO, Model model) throws Exception {
		//??? ??????
		hackVO.setTotalCount(service.memberHackTotalCnt());
		//?????? ??? ??????
		hackVO.setRowCount(service.memberHackCnt(hackVO));
		// ?????? ????????? ??????
		hackVO.setHackList(service.memberHackList(hackVO));
		
		return "shop/admin/member/hack";
	}
	
	@RequestMapping(value="hack/indb", method={RequestMethod.POST, RequestMethod.GET})
	public String hackIndb(@ModelAttribute("hackVO") MemberHackVO hackVO, Model model) {
		String returnUrl = "";
		model.addAttribute("hackVO", hackVO);

		switch(hackVO.getMode().charAt(0)) {
		
		// ?????????
		case 'd' :
			String[] noListArr = hackVO.getNolist().split(";");
			for(int i = 0, size = noListArr.length; i < size; i++) {
				service.memberHackDelete(Integer.parseInt(noListArr[i]));
			}
			returnUrl = "redirect:../hack";
			break; 
			
		// ?????? ??????????????? ???????????? ?????????. ????????? ????????? ??????
		case 'm' :
			model.addAttribute("result", 1);
			this.service.memberHackUpdate(hackVO);
			returnUrl = "shop/admin/member/hack_register";
		default :
		}
		
		return returnUrl;
	}
	
	// ?????? ?????? ????????????
	@RequestMapping(value="hack_register")
	public String hackRegister(@ModelAttribute("hackVO") MemberHackVO hackVO, Model model) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>member hack_register ");
		}
		
		GdLogHack logHack = this.service.memberHackQuery(Integer.parseInt(hackVO.getSno()));
		
		List<GdCode> hackCodeList = CodeUtil.codeitem("hack");
		List<String> checked = new ArrayList<String>();
		
		for(int i = 0, size = hackCodeList.size(); i < size; i++) {
			if((logHack.getItemcd() & ((int)Math.pow(2.0d, i))) > 0) {
				checked.add(hackCodeList.get(i).getItemnm());
			}
		}

		model.addAttribute("gdLogHack", logHack);
		model.addAttribute("checkedList", checked);
		return "shop/admin/member/hack_register";
	}
	
	@RequestMapping("email.cfg")
	public String emailCfg(@RequestParam String mode, Model model) throws IOException {
		String subject = "";
		String emailCfgUploadPath = this.service.getEmailCfgUploadPath(ConfigClass.value("WEB_DIR_PATH"));

		// ????????????
		Properties nmProp = this.service.getNmProp();
		subject = StringUtil.toConvert(this.service.getEmailSubjectProp(emailCfgUploadPath, mode).getProperty("subject_" + mode, "").trim(), "8859_1", "UTF-8");
		
		// ????????????
		StringBuffer mainContents = new StringBuffer();
		ArrayList list = FileUtil.getOneLineListToFile(emailCfgUploadPath.concat("contents_" + mode + ".html"));
		
		if(null != list && 0 < list.size()) {
			for(int i = 0, size = list.size(); i < size; i++) {
				mainContents.append(list.get(i) + "\n");
			}
		}
		
		EmailCfgVO emailVO = new EmailCfgVO();
		emailVO.setContents(mainContents.toString());
		emailVO.setMode(mode);
		emailVO.setSubject(subject);
		emailVO.setTitle(nmProp.getProperty(mode, ""));
		emailVO.setUseYn(ShopConfig.getProperty("mailyn_" + mode));
		
		model.addAttribute("emailCfgVO", emailVO);
		
		return "shop/admin/member/emailCfg";
	}
	
	@RequestMapping("email/indb")
	public String indbEmail(@ModelAttribute("emailVO") EmailCfgVO emailVO, @RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		String mode = emailVO.getMode();
		String emailCfgUploadPath =  this.service.getEmailCfgUploadPath(ConfigClass.value("WEB_DIR_PATH"));
				
				
//				this.service.getEmailCfgUploadPath(this.uploadPath);
		
		// ???????????? ???????????? ??????
		ShopConfig.setProperty("mailyn_" + mode, (String)paramMap.get("cfg[mailyn_" + emailVO.getMode() + "]"));

		// ????????????
		Properties prop = this.service.getEmailSubjectProp(emailCfgUploadPath, mode);
		prop.setProperty("subject_" + mode, StringUtil.toConvert((String)paramMap.get("headers[Subject]"), "UTF-8", "8859_1"));
		OutputStream outputStream = new FileOutputStream(emailCfgUploadPath.concat("subject.html"));
		prop.store(outputStream, "");
		outputStream.close();

		// ????????????
		FileUtil.savefile(emailCfgUploadPath.concat("contents_" + mode + ".html"), emailVO.getBody(), false);
		
		model.addAttribute("save", true);
		return "shop/admin/member/emailCfg";	
	}
}
