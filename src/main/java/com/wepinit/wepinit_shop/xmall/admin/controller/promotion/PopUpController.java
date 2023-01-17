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
package com.wepinit.wepinit_shop.xmall.admin.controller.promotion;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.promotion.PopUpService;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PopUpVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPopUp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/admin/promotion/popup_manager/*")
public class PopUpController {

	private static final Logger logger = LoggerFactory.getLogger(PopUpController.class); 
	
	@Resource
	PopUpService service;
	
	@RequestMapping(value = "list")
	public String mainBanner(@ModelAttribute("popUpVO") PopUpVO popUpVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("popup_manager list!!!");
		}
		logger.debug("popup_manager!!!popUpVO:"+popUpVO);
		Map<String, Object> param = new HashMap<String, Object>();
		ArrayList<String> sregdt = popUpVO.getSregdt();
		
		if(sregdt!=null && sregdt.size()>0){
			logger.debug(">>>>>sregdt.size()"+sregdt.size());
			for(int i=0;i<sregdt.size();i++){
				if(i==0){
					if(sregdt.get(i).length()>0){
						popUpVO.setSregdt0(sregdt.get(i));	
					}
				}else{
					if(sregdt.get(i).length()>0){
					popUpVO.setSregdt1(sregdt.get(i));
					}
				}
			}
		}
		logger.debug("popup_manager!!!popUpVO2:"+popUpVO);
		//총건수
		popUpVO.setRowCount(service.popUpCount(popUpVO));		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, popUpVO.getPageSize());
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, popUpVO.getRowStart());		

		popUpVO.setPopUpList(service.popUpList(popUpVO));
		
		return "/shop/admin/promotion/popup_manager";
	}
	
	//수정 등록 일 경우
	@RequestMapping(value="register")
	public String mainBannerRegister(@ModelAttribute("popUpVO") PopUpVO popUpVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("################mode....get");
		}
		
		//수정 페이지
		if(popUpVO.getMode().equals("modify")) {			
			if(logger.isDebugEnabled()) {
				logger.debug("modify!!!");	
			}
			popUpVO.setPopUpObject(service.getPopUpListView(popUpVO.getSno()));

			if(logger.isDebugEnabled()) {
				logger.debug("popUpVO title {}", popUpVO.getTitle());	
			}
			
			return "/shop/admin/promotion/popup_register";
			
		}else if(popUpVO.getMode().equals("register")) {
			if(logger.isDebugEnabled()) {
				logger.debug("popup_manager_register!!!");	
			}
			return "/shop/admin/promotion/popup_register";
		}else{
			return "/shop/admin/promotion/popup_register";
		}
	}
	
	@RequestMapping(value="indb")
	public String popUpRegisterModify(@ModelAttribute("popUpVO") PopUpVO popUpVO, RedirectAttributes redirectAttr) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("popUpManager_register: ");
		}

		
		if(logger.isDebugEnabled()) {
			logger.debug("########mode::"+popUpVO.getMode());
			logger.debug("########update/reg::"+popUpVO.toString());	
		}
		if(popUpVO.getMode().equals("modify") || popUpVO.getMode().equals("register")) {
			List<GdPopUp> rtList1 = service.checkPopType(popUpVO);
			if(rtList1.size()>=1){
				if(!(popUpVO.getSno()==(rtList1.get(0).getSno()))){
				popUpVO.setErrorMsg("등록할 수 있는 팝업은 윈도우, 이동레이어, 고정레이어 각각 하나씩 입니다. 나머지 팝업의 사용 유무를 변경 후 등록, 수정이 가능합니다.");
				redirectAttr.addFlashAttribute("popUpVO", popUpVO);
				return "redirect:/shop/admin/promotion/popup_manager/register?mode="+popUpVO.getMode();
				}
			}
			List<GdPopUp> rtList2 = service.checkFileName(popUpVO);
			
			if(rtList2.size()>=1){
				if(!(popUpVO.getSno()==(rtList2.get(0).getSno()))){
				popUpVO.setErrorMsg("해당 파일명이 이미 존재합니다.");
				redirectAttr.addFlashAttribute("popUpVO", popUpVO);
				return "redirect:/shop/admin/promotion/popup_manager/register?mode="+popUpVO.getMode();
				}
			}
			
			/** 특정 기간 */
			String popup_sdt_tg = popUpVO.getPopupsdttg();				// 시작일
			String popup_stime_tg = popUpVO.getPopupstimetg();	  		// 시작시간
			String popup_edt_tg = popUpVO.getPopupedttg();					// 종료일
			String popup_etime_tg = popUpVO.getPopupetimetg();				// 종료시간
			/** 값이 있을 경우 */
			if(!"".equals(popup_sdt_tg)){ // 시작일 + 시작시간
				if("".equals(popup_stime_tg)){
					popup_stime_tg="0000";
				}
				popUpVO.setPopsdt(popup_sdt_tg+" "+popup_stime_tg+"00");
			}else{
				
				popUpVO.setPopsdt(null);
			}
			if(!"".equals(popup_edt_tg)){ // 종료일 + 종료시간
				if("".equals(popup_etime_tg)){
					popup_etime_tg="0000";
				}
				popUpVO.setPopedt(popup_edt_tg+" "+popup_etime_tg+"00");
			}else{ // 시작일 + 시작시간
				popUpVO.setPopedt(null);
			}
			
			/** 특정기간 특정시간 */
			String popup_sdt = popUpVO.getPopupsdt();							// 시작일
			String popup_edt = popUpVO.getPopupedt();						// 종료일
			String popup_stime = popUpVO.getPopupstime();						// 오픈시간
			String popup_etime = popUpVO.getPopupetime();					// 클로즈시간
			/** 값이 있을 경우 */
			if(!"".equals(popup_sdt)){ // 
				if("".equals(popup_stime)){
					popup_stime="0000"; 
				}
				popUpVO.setPopsdate(popup_sdt+" "+popup_stime+"00");
			}else{
				popUpVO.setPopsdate(null);
			}
			if(!"".equals(popup_edt)){
				if("".equals(popup_etime)){
					popup_etime="0000";
				}
				popUpVO.setPopedate(popup_edt+" "+popup_etime+"00");
			}else{
				popUpVO.setPopedate(null);
			}
			if(logger.isDebugEnabled()) {
				logger.debug("########popUpVO."+popUpVO.getMode()+"::"+popUpVO.toString());
			}
		}
		//팝업 내용  수정
		if(popUpVO.getMode().equals("modify")) {	
			
			String old_file = popUpVO.getOldfile(); // 이전 파일명
			String path = ConfigClass.FILE_PATH+"popup/"+old_file+".html";
			String txtarea = popUpVO.getContent();
			String encoding = "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n";
			txtarea = encoding + txtarea;
			if(logger.isDebugEnabled()) {
				logger.debug("########path::   "+path);
				logger.debug("########txtarea::   "+txtarea);
				logger.debug("########txtarea.getBytes()::   "+txtarea.getBytes());
			}
			String filename = popUpVO.getFilename();
			
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter ouputStreamWriter = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bufferedWriter = new BufferedWriter(ouputStreamWriter);
			bufferedWriter.write(txtarea);
			bufferedWriter.close();
			ouputStreamWriter.close();
			fos.close();
//			fos.write(txtarea.getBytes()); 
//			fos.close();
			// 파일명 수정
			if(!filename.equals(old_file)){
				File file2 = new File(path);
				File reFile = new File(ConfigClass.FILE_PATH+"popup/"+filename+".html");
				file2.renameTo(reFile);
			}
			service.updatePopUp(popUpVO);
			
			//팝업등록
		}else if(popUpVO.getMode().equals("register")){
			
			String fileName = popUpVO.getFilename()+".html";
			String path = ConfigClass.FILE_PATH+"popup/";
			FileOutputStream fos = new FileOutputStream(path+fileName);
			fos.write(28);
			fos.close();
			service.insertPopUp(popUpVO);
			
			//팝업 삭제
		}else{
			// 여기서부터 파일 네임 다시작성
			String path = ConfigClass.FILE_PATH+"popup/";
			
			FileUtil.deleteFile(path+popUpVO.getFilename()+".html");
			service.deletePopUp(popUpVO.getSno());
			
		}
		return "redirect:/shop/admin/promotion/popup_manager/list";
	}
	   @RequestMapping(value = "popUp")
	   public String openPopUp(@ModelAttribute("popUpVO") PopUpVO popUpVO,
				HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
			String path = ConfigClass.FILE_PATH+"popup/";
			String filename = popUpVO.getFilename();
			StringBuffer contents = FileUtil.getFileContents(path,filename,"html");
			if(logger.isDebugEnabled()) {
				logger.debug("############openPopUp");
				logger.debug("############openPopUp::path::"+path);
				logger.debug("############openPopUp::filename::"+filename);
				logger.debug("############file contents::"+ contents);
			}
			popUpVO.setFileContents(contents.toString());
		   return "/shop/admin/promotion/popup_preview";
	   }
		@RequestMapping(value="fileContents.dojson" )
		public String readFileContents(@ModelAttribute("popUpVO") PopUpVO popUpVO
				, HttpServletRequest req 
				, HttpServletResponse res
				, Model model) throws Exception {
			String path = ConfigClass.FILE_PATH+"popup/";
			String filename = popUpVO.getTplFile();
			StringBuffer contents = FileUtil.getFileContents(path,filename,"html");
			contents.replace(0,70, ""); // html utf-8 인코딩 화면 제거 
			byte[] contents1 = 	contents.toString().getBytes("utf-8");
			for(int i =0; i < contents1.length; i++){
				Integer.toHexString(contents1[i]);
			}
			if(logger.isDebugEnabled()) {
				logger.debug("############readFileContents");
				logger.debug("############readFileContents::path::"+path);
				logger.debug("############readFileContents::filename::"+filename);
				logger.debug("############file contents::"+ contents);
			}
			popUpVO.setFileContents(contents.toString());
			
			return "dojson";
		}
}
