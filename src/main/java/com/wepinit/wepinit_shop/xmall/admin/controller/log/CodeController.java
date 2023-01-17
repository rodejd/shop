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
package com.wepinit.wepinit_shop.xmall.admin.controller.log;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.log.CodeService;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.CodeVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/shop/admin/log/code/*")
public class CodeController {

	private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

	@Resource
	CodeService service;
	
	@RequestMapping(value = "list")
	public String list(@ModelAttribute("codeVO") CodeVO codeVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>data.code!!!");
		}
		
		codeVO.setDataCodeList(service.getDataCodeList(codeVO));					// 연령별매출분석LIST

		return "/shop/admin/log/code/list";
	}
	
	@RequestMapping(value="indb")
	public String indb(@ModelAttribute("codeVO") CodeVO codeVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String returnUrl = "";
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>data_code_indb!!!" + codeVO);
		}
		
		// 등록
		if( "register".equals(codeVO.getMode()) ){
			GdCode gdCode = null;
			
			//등록완료
			service.insertGroupcd(codeVO);
			
			List<GdCode> resultList = service.getGroupcdSnoListSelect(codeVO); 												// GroupCd로 조회
			
			if(resultList.size() > 0){
				for(int i=0; i<resultList.size(); i++){
					gdCode = resultList.get(i);
					gdCode.setSort(i+1);																	// SORT번호(재정렬)
					gdCode.setGroupcd( codeVO.getGroupcd() );								// GroupCd
					service.updateGroupcdSort(gdCode); 												// GroupCd(순서)로 조회
				}
			}
			
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/log/code/register";
		}
		
		// 수정
		if( "modify".equals(codeVO.getMode()) ){
			service.updateGroupcd(codeVO);
			
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/log/code/register";
		}
		
		// 삭제
		if( "delete".equals(codeVO.getMode()) ){
			service.deleteGroupcd(codeVO);
			returnUrl = "redirect:/shop/admin/log/code/list?groupcd="+codeVO.getGroupcd();
		}
		
		// 일괄수정
		if( "allmodify".equals(codeVO.getMode()) ){
			
			String allmodify = codeVO.getAllmodify();
			String[] allmodifyList = allmodify.split("\\|\\|");
			String[] codeList = allmodifyList[0].split("==")[1].split(";");
			String[] sortList = allmodifyList[1].split("==")[1].split(";");
			
			if(codeList.length>0 && codeList.length == sortList.length){
				for(int i=0; i<codeList.length; i++){
					
					codeVO.setSort( Integer.parseInt( sortList[i] ) );
					codeVO.setSno( Integer.parseInt( codeList[i] ) );
					
					service.updateGroupcdSortAll(codeVO);
				}
			}
			
			returnUrl = "redirect:/shop/admin/log/code/list?groupcd="+codeVO.getGroupcd();
		}
		
		// 순서변경(버튼)
		if("sort_up".equals(codeVO.getMode()) ||  "sort_down".equals(codeVO.getMode())){
			//code = groupcd | sno
			String[] bscCode = StringUtil.split(StringUtil.nvl(codeVO.getCode(),""), ",");
			
			if(bscCode.length != 2){
				//break;
			}
			
			codeVO.setGroupcd(bscCode[0]);
			codeVO.setSno( Integer.parseInt(bscCode[1]) );
			
			GdCode gdCodeVo = service.getDataSortUpDownSelect(codeVO);					// 선택한 코드 Sort값 조회
			
			int change_sort 	= gdCodeVo.getSort();								// 현재 Sort번호(3)
			//String changFromSort= codeVO.getSort(); 
			
			// Up/Down Sort조회
			gdCodeVo = service.getDataSortUpDownSelect2(codeVO);
			
			if ( !"".equals(gdCodeVo.getSort()) && !"".equals(gdCodeVo.getSno())){
				
				//자신의 sort변경하기
				codeVO.setSort( gdCodeVo.getSort() );
				codeVO.setGroupcd(bscCode[0]);
				codeVO.setSno( Integer.parseInt(bscCode[1]) );
				service.updateCodeSortUpDown(codeVO);
				
				//자신의 sort를 변경한 sort 변경하기
				codeVO.setSort( change_sort );
				codeVO.setGroupcd(bscCode[0]);
				codeVO.setSno( gdCodeVo.getSno() );
				service.updateCodeSortUpDown(codeVO);
				
				returnUrl = "redirect:/shop/admin/log/code/list?groupcd="+codeVO.getGroupcd();
			}
		}
		return returnUrl;		
		
	}
	
	@RequestMapping(value="register")
	public String register(@ModelAttribute("codeVO") CodeVO codeVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>data_code_register!!!");
		}
		
		return "/shop/admin/log/code/register";
	}
}
