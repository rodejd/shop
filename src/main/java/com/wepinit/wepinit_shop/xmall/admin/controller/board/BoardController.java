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

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.board.BoardService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.GroupService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.BoardVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import com.wepinit.wepinit_shop.xmall.common.vo.join.BoardBoardinf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource
	BoardService service;
	
	@Resource
	GroupService groupService;
	
	@RequestMapping(value="list")
	public String list(@ModelAttribute("boardVO") BoardVO boardVO, HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		/*
		 * 게시판관리 권한
		 */
		ShopLibFunction.menuAuthPermissionCheck(req, res,"board");
		
		/* 공통 session 사용하지 않고 임의로 값을 넣어주고 있음 */
		//session null값만 나와 event를 임시로 넣었다.
		Map<?, ?> sess = (Map<?, ?>) session.getAttribute("Xkey");
		boardVO.setoUserInfoLogin((String) session.getAttribute("oUserInfoLogin")); 
		if(boardVO.getoUserInfoLogin() != null){
			String level =  String.valueOf(sess != null ? sess.get("level") : "event");
			if( 0 > ShopConfig.getProperty("level"+level).indexOf("event")){
				boardVO.setEvent(true);
			}
		}
		// 관리자 아이콘
		String adminicon ="";

		if ( FileUtil.existsFile( ConfigClass.value("WEB_DIR_PATH")+"shop/data/skin/" + ShopConfig.getProperty("tplSkin"), "admin.gif" ) ){
			adminicon = "admin.gif";
		}
		boardVO.setAdminicon(adminicon);
		
		//페이징
		boardVO.setRowCount(service.getBoardCount());
		boardVO.setPageSize(20);
		boardVO.setPageGroupSize(1);
		int max= boardVO.getPageSize();  // 페이지 목록 사이즈
		int top = boardVO.getRowStart();  // 시작 row번호
		int[] vnum = new int[max];
		for(int i=0;i<max;i++){
			vnum[i] = top +1+ i;
		}
		
		List<BoardBoardinf> tmp =(service.getboardListJoinBDINF(boardVO));
		boardVO.setBoardList(tmp);
		List<XmBoardSetup> list =  new ArrayList<XmBoardSetup>();
		
		//현재 언어
		boardVO.setSkin(ConfigClass.getSkin(req));
		
		//언어 리스트  조회
		HashMap<String, String> languageList = groupService.getCodeGroupItemLIST("language", "", "sort");
		boardVO.setLanguageList(languageList);
		
		String id="";
		for(int i=0;i<tmp.size();i++){
			id =tmp.get(i).getId();
			if(id.equals("")||id ==null){
				id = "xm_bd_bssset";
			}
			list.add(service.getBoardCONFIG(id));
		}
		boardVO.setBoardSetupList(list);
		return "/shop/admin/board/list";
	}
	@RequestMapping(value="board/indb")
	public String boardIndb(@ModelAttribute("boardVO") BoardVO boardVO,
			HttpSession session,HttpServletRequest req, HttpServletResponse res) throws Exception {
			
			if(boardVO.getMode().equals("drop")){
				//검색조건
				String id = boardVO.getId();
				if(id!=null&&id.length()>0)	{
					//파라미터에 Table 설정	
					File file = new File(ConfigClass.FILE_PATH+"gd_bd_" + id);
					if(file.exists()){
						file.delete();
					}
					
					service.setBoardLISTDELETE(id);//게시판 리스트 목록에서 삭제
					service.setBoardLISTNFDELETE(id);//게시판 boar_inf에 대이타 삭제
					service.setBoardXMBOARDDELETE(id);//게시판 기본세팅 삭제
					service.setBoardDROP(id);
					//글목록
					boardVO.setDropEvent(true);
					
				}
			}
		return "redirect:/shop/admin/board/list";
	}
	
	@RequestMapping(value="indb")
	public String boardRegisterIndb(@ModelAttribute("boardVO") BoardVO boardVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ board Indb");
		}
		
		
		if("register".equals(boardVO.getMode()))
		{
			//아이디 중복확인 추가
			if(logger.isDebugEnabled()){
				logger.debug("@@ board register >> "+boardVO.getId());
			}
			if(service.getBoardCheckId(boardVO.getId()) > 0){
				return "redirect:/shop/admin/board/register?alert=1";
			}
			//기본값이 아닐경우에만 추가
			if(!boardVO.getId().equals("xm_bd_bssset")){
				
				service.getDirectoryMkdir(boardVO.getId()); //폴더 생성합니다
				service.setBoardCREATE(boardVO.getId());
				service.setBoardLISTADD(boardVO);
				service.setBoardXMBOARDSETADD(boardVO); 
			}
			
		}else if("modify".equals(boardVO.getMode())){
			//data를 옮겨 올때 게시판 세팅이 빠져 있을 경우를 대비하여 insert문으로 빠질 것도 대비하여 만들어 둠//
			service.setBoardXMBOARDSETUPDATE(boardVO);
		}
		
		return "redirect:/shop/admin/board/list";
	}
	
	@RequestMapping(value="register")
	public String boardRegister(@ModelAttribute("boardVO") BoardVO boardVO,
			HttpSession session,HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		
		String id = StringUtil.nullConv(boardVO.getId());
		String mode = StringUtil.nullConv(boardVO.getMode(), "register");
		boolean hasStart=true;
		if("0".equals(boardVO.getStart())){
			hasStart = true;
		}else{
			hasStart = false;
		}
		if("".equals(id))	{
			//rtList = BoardConfig.getBoardConfig("xm_bd_bssset");
			id = "xm_bd_bssset";
			/*requestSet.setProperty("id", "xm_bd_bssset");*/
		}
		
		if("modify".equals(mode)){
			/*rtList = BoardConfig.getBoardConfig(id);*/
			/*boardVO.setBoardSetupList2(service.getBoardCONFIG(id));*/
		}else if("register".equals(mode)){
			/*id= "xm_bd_bssset";*/
			//rtList = BoardConfig.getBoardConfig("xm_bd_bssset");
			
//			//아이디 중복확인 추가
//			if(logger.isDebugEnabled()){
//				logger.debug("@@ board register >> "+id);
//			}
//			if(service.getBoardCheckId(id) > 0){
//				return "redirect:/shop/admin/board/register?alert=1";
//			}
			
		}
		
		boardVO.setBoardSetupList(service.getBoardCONFIG(id));
		/*String query = dbHandler.getXmlQuery("xmall_board/board_CONFIG", requestSet);
		
		rtList = dbHandler.executeQuery(query);*/	
		//mode= ""or "register" 게시판만들기   mode="modify" 게시판리스트 수정
		if(boardVO.getMode().equals("modify") ||boardVO.getMode().equals("register")||boardVO.getMode().equals("")){
			
			if(hasStart){//list ->register
					XmBoardSetup list = service.getBoardCONFIG(id) !=null ? service.getBoardCONFIG(id) : new XmBoardSetup();
				logger.debug("@@@@@@@@@@ board modify&register "+list.toString());
				logger.debug("@@@@@@@@@@ board modify&register "+list.toString());
				
				boardVO.setId(list.getId() !=null ? list.getId() : "");
				boardVO.setBdName(list.getBdName() !=null ? list.getBdName() : "");
				boardVO.setBdGroup(list.getBdGroup() !=null ? list.getBdGroup() : "");
				boardVO.setBdSkin(list.getBdSkin() !=null ? list.getBdSkin() : "");
				boardVO.setBdAlign(list.getBdAlign() !=null ? list.getBdAlign() : "");
				boardVO.setBdWidth(list.getBdWidth() !=null ? list.getBdWidth() : "");
				boardVO.setBdStrlen(list.getBdStrlen() !=null ? list.getBdStrlen() : "");
				boardVO.setBdPageNum(list.getBdPageNum() !=0 ? list.getBdPageNum() : 0);
			
				if ("gallery".equals(boardVO.getBdSkin() != null ? boardVO.getBdSkin() :"")) {
					int tempNum = (list.getBdListImgCntW() !=0 ? list.getBdListImgCntW() : 0) * (list.getBdListImgCntH() !=0 ? list.getBdListImgCntH() : 0);
					boardVO.setBdPageNum(list.getBdPageNum() !=0 ? list.getBdPageNum() : tempNum);
				}
				boardVO.setBdNew(list.getBdNew() != null ? list.getBdNew() : "");
				boardVO.setBdHot(list.getBdHot() != null ? list.getBdHot() : "");
				boardVO.setBdNoticeList(list.getBdNoticeList() != null ? list.getBdNoticeList() : "");
				boardVO.setBdLvlL(list.getBdLvlL() != null ? list.getBdLvlL() : "");
				boardVO.setBdLvlR(list.getBdLvlR() != null ? list.getBdLvlR() : "");
				boardVO.setBdLvlC(list.getBdLvlC() != null ? list.getBdLvlC() : "");
				boardVO.setBdLvlW(list.getBdLvlW() != null ? list.getBdLvlW() : "");
				boardVO.setBdIp(list.getBdIp() != null ? list.getBdIp() : "");
				boardVO.setBdIpAsterisk(list.getBdIpAsterisk() != null ? list.getBdIpAsterisk() : "");
				boardVO.setBdTypeView(list.getBdTypeView() != 0 ? list.getBdTypeView() : 0);
				boardVO.setBdUseLink(list.getBdUseLink() != null ? list.getBdUseLink() : "");
				boardVO.setBdUseFile(list.getBdUseFile() != null ? list.getBdUseFile() : "");
				boardVO.setBdMaxSize(list.getBdMaxSize() != null ? list.getBdMaxSize() : "0");
				boardVO.setBdTypeMail(list.getBdTypeMail() != null ? list.getBdTypeMail() : "");
				boardVO.setBdHeader(list.getBdHeader() != null ? list.getBdHeader() : "");
				boardVO.setBdFooter(list.getBdFooter() != null ? list.getBdFooter() : "");
				boardVO.setBdUseSubSpeech(list.getBdUseSubSpeech() != null ? list.getBdUseSubSpeech() : "");
				boardVO.setBdSubSpeechTitle(list.getBdSubSpeechTitle() != null ? list.getBdSubSpeechTitle() : "");
				boardVO.setBdSubSpeech(list.getBdSubSpeech() != null ? list.getBdSubSpeech() : "");
				boardVO.setBdUseComment(list.getBdUseComment() != null ? list.getBdUseComment() : "");
				boardVO.setBdSearchMode(list.getBdSearchMode() != 0 ? list.getBdSearchMode() : 0);
				//requestSet.setProperty("bdField", requestSet.getProperty("bdField",""));//""
				
				
				boardVO.setBdImg(list.getBdImg());
				boardVO.setBdColor(list.getBdColor());
				boardVO.setBdPrnType(list.getBdPrnType());
				boardVO.setBdColor(list.getBdColor());
				boardVO.setBdListImgCntW(list.getBdListImgCntW());
				boardVO.setBdListImgCntH(list.getBdListImgCntH());
				boardVO.setBdListImgSizeW(list.getBdListImgSizeW());
				boardVO.setBdListImgSizeH(list.getBdListImgSizeH());
				boardVO.setBdListImg(list.getBdListImg());
				boardVO.setBdUserDsp(list.getBdUserDsp());
				boardVO.setBdAdminDsp(list.getBdAdminDsp());
				
				//requestSet.setProperty("bdSpamComment", requestSet.getProperty("bdSpamComment","0"));////""
				int bdSpamCommentNum=0;
				String bdSpamComment = "";
				if(!"".equals(list.getBdSpamComment() !=null ? "pass"  : ""))
				{
					for(int i =0; i< list.getBdSpamComment().length; i++){
						bdSpamCommentNum += Integer.parseInt(list.getBdSpamComment()[i]);
					}
					bdSpamComment = bdSpamCommentNum+"";
				}else{
					bdSpamComment = "";
				}
				boardVO.setBdSpamComment(bdSpamComment);
				
				//requestSet.setProperty("bdSpamBoard", requestSet.getProperty("bdSpamBoard",""));////""
				int bdSpamBoardNum =0;
				String bdSpamBoard= "";
				if(!"".equals(list.getBdSpamBoard() != null ? "pass" :""))
				{
					for(int i =0; i< list.getBdSpamBoard().length; i++)
					{
						bdSpamBoardNum += Integer.parseInt(list.getBdSpamBoard()[i]);
					}
					bdSpamBoard = bdSpamBoardNum+"";
				}else{
					bdSpamBoard = "";
				}
				boardVO.setBdSpamBoard(bdSpamBoard);
				
				boardVO.setBdSecretChk(list.getBdSecretChk() != 0 ? list.getBdSecretChk() : 1);
				boardVO.setBdTitleCChk(list.getBdTitleCChk() != "" ? list.getBdTitleCChk() : "");
				boardVO.setBdTitleSChk(list.getBdTitleSChk()!= "" ? list.getBdTitleSChk() : "") ;
				boardVO.setBdTitleBChk(list.getBdTitleBChk()!= "" ? list.getBdTitleBChk() : "");
				boardVO.setBdEmailNo(list.getBdEmailNo()!= "" ? list.getBdEmailNo() : "");
				boardVO.setBdHomepageNo(list.getBdHomepageNo()!= "" ? list.getBdHomepageNo() : "");
			}else{// register -> list
				boardVO.setId(id);
				int bdFieldNum=0;
				String bdFieldStr = "";
				if(!"".equals(boardVO.getBdField2() !=null ? "pass" : ""))
				{
					for(int i =0; i< boardVO.getBdField2().length; i++)
					{
						bdFieldNum += Integer.parseInt(boardVO.getBdField2()[i]);
					}
					bdFieldStr =bdFieldNum+"";
				}else{
					bdFieldStr ="";
				}
				boardVO.setBdField(bdFieldStr);
			}
			/*if(bdMaxSize > 2 * 1024000)
			{
				
			}*/
			if(id!=null&&id.length()>0)	{
				//파라미터에 Table 설정	
				
	/*			boardVO.setTableName("gd_bd_"+id);
	*/			//글목록
				if("register".equals(mode))
				{
					
//					List cnt = service.getBoardINFSEARCH(boardVO);
//					logger.debug("@@ board register register "+boardVO.toString());
//					if(cnt.size() > 0 && cnt != null){
//						return "/shop/admin/board/register?alert=1";
//					}
//					//기본값이 아닐경우에만 추가
//					if(!id.equals("xm_bd_bssset")){
//						service.setBoardCREATE(id);
//						service.setBoardLISTADD(boardVO);
//						service.setBoardXMBOARDSETADD(boardVO);
//					}
					
				}else if("modify".equals(mode)){
					//data를 옮겨 올때 게시판 세팅이 빠져 있을 경우를 대비하여 insert문으로 빠질 것도 대비하여 만들어 둠//
					
					//tableName = xm_board_setup
					boardVO.setBoardSetupList(service.getBoardSEARCHCORRECT1(id));
					
//					if("".equals(boardVO.getBoardSetupList().get(0).getId()))	{
//						service.setBoardXMBOARDSETADD(boardVO);
//					}else{
//						service.setBoardXMBOARDSETUPDATE(boardVO);
//					}
					logger.debug("@@ board register modify"+boardVO.toString());
					
				}
			}
		}
		return "/shop/admin/board/register";
	}


}
