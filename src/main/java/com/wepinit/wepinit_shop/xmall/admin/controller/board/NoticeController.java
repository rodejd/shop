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
package com.wepinit.wepinit_shop.xmall.admin.controller.board;

import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.board.NoticeService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.NoticeVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdNotice;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/shop/admin/board/notice/*")
public class NoticeController {
	@Resource
	NoticeService service;
	
	/*
	 * 공지사항
	 */
	@RequestMapping(value="list")
	public String notice(@ModelAttribute("noticeVO") NoticeVO noticeVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//TOTAL COUTN(총 건수 출력용)
		noticeVO.setTotalCount(service.getNoticeCount());
		
		//SEARCH COUNT
		noticeVO.setRowCount(service.getNoticeSearchCount(noticeVO));
		noticeVO.setNoticeList(service.getNoticeList(noticeVO));
		
		return "/shop/admin/board/notice";
	}
	
	/*
	 * 공지사항 등록 페이지
	 */
	@RequestMapping(value="register")
	public String noticeRegister(@ModelAttribute("noticeVO")NoticeVO noticeVO, Model model) throws Exception {
		//수정 화면
		if("modify".equals(noticeVO.getMode())){
			noticeVO.setFilePath("gd_bd_notice"); //폴더 경로
			
			noticeVO.setNoticeObj(service.getNoticeDetail(noticeVO.getNo()));
			model.addAttribute("noticeVO.noticeObj", noticeVO.getNoticeObj());
		}
		return "/shop/admin/board/notice_register";
	}
	
	/*
	 * 공지사항 등록 POST
	 */
	@RequestMapping(value="indb")
	public String noticeRegister(@ModelAttribute("noticeVO")NoticeVO noticeVO, HttpServletRequest req, HttpServletResponse res, MultipartHttpServletRequest mhsq) throws Exception {
		int lastSort = 0;
		
		AwsFileUtil awsfileUtil = new AwsFileUtil();
		//등록 페이지
		if("".equals(noticeVO.getMode()) || "register".equals(noticeVO.getMode())){
			noticeVO.setIp(req.getRemoteAddr());
			noticeVO.setContents(WebUtil.setLineChange2(noticeVO.getContents()));
			
			noticeVO.setReturnUrl(req.getHeader("referer"));
			lastSort = service.getSortMax(noticeVO.getItemcd());
			
			service.insertNotice(lastSort, noticeVO);
			int no = service.getNoMax(noticeVO.getItemcd());
			
			//파일 업로드
			if(noticeVO.getAttach() != null){
				if(!noticeVO.getAttach().isEmpty()){
					String awsPath = "notice/" + String.valueOf(no) + "/";
					String awsFile = FileUtil.awsUploadFile(noticeVO.getAttach().getOriginalFilename(), noticeVO.getAttach().getBytes(), awsPath);
					
					NoticeVO updateVO = new NoticeVO();
					updateVO.setNo(no);
					updateVO.setNewfile(awsFile);
					service.updateNoticeByFile(updateVO);
				}
			}
			
			noticeVO.setReturnUrl("./register?mode=modify&no="+no+"&returnUrl="+ StringUtil.replace(noticeVO.getReturnUrl(),"&","||"));
		//수정 페이지
		} else if("modify".equals(noticeVO.getMode())) {
			noticeVO.setContents(WebUtil.setLineChange2(noticeVO.getContents()));
			noticeVO.setIp(req.getRemoteAddr());
			
			service.updateNoticeModify(noticeVO);
			
			if(noticeVO.getAttach() != null){
				if(!noticeVO.getAttach().isEmpty()){
					GdNotice noticeInfo = service.getNoticeDetail(noticeVO.getNo());
					
					if( StringUtils.isNotBlank(noticeInfo.getNewfile()) ) {
						if( FileUtil.getChkAwsFile(noticeInfo.getNewfile()) ){
							String awsFileName = FileUtil.getUrlFileName(noticeInfo.getNewfile());
							String awsKey = "notice/" + String.valueOf(noticeInfo.getNo()) + "/" + awsFileName;
							awsfileUtil.delete(awsKey);
						}
					}
					String awsPath = "notice/" + String.valueOf(noticeInfo.getNo()) + "/";
					String awsFile = FileUtil.awsUploadFile(noticeVO.getAttach().getOriginalFilename(), noticeVO.getAttach().getBytes(), awsPath);
					
					NoticeVO updateVO = new NoticeVO();
					updateVO.setNo(noticeInfo.getNo());
					updateVO.setNewfile(awsFile);
					service.updateNoticeByFile(updateVO);
				}
			}
			noticeVO.setReturnUrl("./register?mode=modify&no="+ noticeVO.getNo()+"&returnUrl="+StringUtil.replace(noticeVO.getReturnUrl(),"&","||"));
		} else if("allmodify".equals(noticeVO.getMode())) {
			String[] allmodiArr = StringUtil.split(noticeVO.getAllmodify().trim() ,"||");
			String[] catchArrName = StringUtil.split(allmodiArr[0],"==");
			String[] catchArrData = StringUtil.split(catchArrName[1],";");
			String[][] 	alldatalist = new String[allmodiArr.length][catchArrData.length];
			
			for(int i=0; i < allmodiArr.length; i++)
			{
				catchArrName = StringUtil.split(allmodiArr[i],"==");
				catchArrData = StringUtil.split(catchArrName[1],";");
				
				for(int j=0; j<catchArrData.length ; j++)
				{
					alldatalist[i][j] = catchArrData[j];
				}
			}
			for(int i=0; i < alldatalist[0].length; i++)
			{
				service.updateNoticeListSort(Integer.parseInt(alldatalist[0][i]), Integer.parseInt(alldatalist[1][i]));
			}
		} else if("delete".equals(noticeVO.getMode())) {
			String[] deleteNume = noticeVO.getNolist().split(";");
			for(int i=0; i < deleteNume.length; i++){
				service.deleteNoticeRegister(Integer.parseInt(deleteNume[i]));
				
				// S3 파일삭제
				String awsKey = "notice/" + String.valueOf(deleteNume[i]);
				awsfileUtil.deleteList(awsKey);
			}
		} else {
			throw new BizException("goods.00001");
		}
		return "redirect:/shop/admin/board/notice/list";
	}
	
	@RequestMapping(value="sort.dojson")
	public String sortAjax(@RequestParam(value="no")int[] no, @RequestParam(value="sort")int[] sort, Model model) throws Exception{
		List<Integer> existingSort = new ArrayList<Integer>();
		existingSort = service.checkNoticeSort(no, sort);
		
		if (null != existingSort && 0 < existingSort.size()) {
			model.addAttribute("existingSort", existingSort);
		}
		
		return "dojson";
	}
	
}
