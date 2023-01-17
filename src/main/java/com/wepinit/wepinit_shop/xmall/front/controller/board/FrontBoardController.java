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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Controller
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.controller.board;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.board.BoardService;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.front.service.board.FrontBoardService;
import com.wepinit.wepinit_shop.xmall.front.vo.board.FrontBoardCmtVO;
import com.wepinit.wepinit_shop.xmall.front.vo.board.FrontBoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.InetAddress;


@Controller
@RequestMapping("/shop/board/")
public class FrontBoardController {

	private static final Logger logger = LoggerFactory.getLogger(FrontBoardController.class); 
	
	@Resource
	FrontBoardService service;
	
	@Resource
	BoardService boardService;
	
	
	@RequestMapping(value="list")
	public String main(@ModelAttribute("boardVO") FrontBoardVO boardVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		String returnUrl = "";
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		
		if(sessObject.isShopLogin()){
			boardVO.setLevel(String.valueOf(sessObject.getUserInfo().getXkey().get("level")));
		}
		
		//게시판 환경설정 확인
		boardVO.setBrdSetupObj(service.getFrontBoardConfig(boardVO.getId()));
		boardVO.setMode(boardVO.getBrdSetupObj().getBdSkin());
		boardVO.setPageSize(boardVO.getBrdSetupObj().getBdPageNum());
		//게시판 존재 여부 확인
		if(service.getFrontBoardListCheckDesc(boardVO.getId()) != null){
			boardVO.setSkin(ConfigClass.getSkin(req));
			
			//게시판 총건수
			boardVO.setTotalCount(service.getFrontBoardTotalCount(boardVO));
			if("gallery".equals(boardVO.getMode())){
				//모드가 갤러리인 경우
				boardVO.setBoardList(service.getFrontBoardListWithRecentReply(boardVO));
				//xss 추가
				for(int i=0; i < boardVO.getBoardList().size();  i++){
					 String tempContents = StringUtil.xssReturn(boardVO.getBoardList().get(i).getContents());
					 boardVO.getBoardList().get(i).setContents(tempContents);
				}
				returnUrl = "/shop/board/gallery";
			} else {
				//공지 글 조회
				boardVO.setNotice("o");
				boardVO.setDefaultBdNotiList(service.getFrontBoardNotiList(boardVO));
				//공지가 아닌 글 조회
				boardVO.setNotice("");
				boardVO.setDefaultBdList(service.getFrontBoardList(boardVO));
				
				//xss 추가 
				for(int i=0; i < boardVO.getDefaultBdList().size();  i++){
					 String tempContents = StringUtil.xssReturn(boardVO.getDefaultBdList().get(i).getContents());
					 boardVO.getDefaultBdList().get(i).setContents(tempContents);
				}
				if("default".equals(boardVO.getMode())){
					returnUrl="/shop/board/default";
				}else if("photo".equals(boardVO.getMode())){
					returnUrl="/shop/board/photo";
				}else if("webzine".equals(boardVO.getMode())){
					returnUrl="/shop/board/webzine";
				}
			}
		}
		return returnUrl;
	}
	
	@RequestMapping(value="view")
	public String view(@ModelAttribute("boardVO")FrontBoardVO boardVO, HttpServletRequest req, HttpServletResponse res) throws Exception{
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			boardVO.setLevel(String.valueOf(sessObject.getUserInfo().getXkey().get("level")));
			boardVO.setName(String.valueOf(sessObject.getUserInfo().getUserName()));
			boardVO.setUserId(String.valueOf(sessObject.getUserInfo().getUserId()));
			boardVO.setEmail(String.valueOf(sessObject.getUserInfo().getXkey().get("email")));
		}

		//게시판 설정 확인
		boardVO.setBrdSetupObj(service.getFrontBoardConfig(boardVO.getId()));
		boardVO.setMode(boardVO.getBrdSetupObj().getBdSkin());
		//HIT 값 증가
		service.updateAddHitCount(boardVO);
		//게시판 상세 정보
		boardVO.setBoardObj(service.getFrontBoardInfo(boardVO));
		//게시판 댓글 리스트
		boardVO.setBoardReList(service.getFrontBoardReplyList(boardVO));
		
		boardVO.setFilePath((ConfigClass.value("FILE_PATH") + "gd_bd_"+boardVO.getId()+"/").replaceAll(ConfigClass.value("WEB_DIR_PATH"),""));
		
		String tempContents = StringUtil.xssReturn(boardVO.getBoardObj().getContents());
		
		boardVO.getBoardObj().setContents(tempContents);
		
		return "/shop/board/view";
	}
	
	@RequestMapping(value="cmt.dojson")
	public String indbAjax(@ModelAttribute("cmtVO")FrontBoardCmtVO cmtVO ,Model model) throws Exception{
		if(cmtVO.getOrder() != null){
			if("insertMemo".equals(cmtVO.getOrder())){
				cmtVO.setMno(0);
				cmtVO.setProfile("");
				//게시물 댓글 등록
				service.insertFrontBoardReply(cmtVO);
			} else if("deleteMemo".equals(cmtVO.getOrder())){
				//게시물 댓글 삭제
				service.deleteFrontBoardReply(cmtVO);
			} else if("checkPasswordMemo".equals(cmtVO.getOrder())){
				model.addAttribute("result", service.getFrontBoardReplyPassword(cmtVO));
			} else if("checkPasswordBoard".equals(cmtVO.getOrder())){
				model.addAttribute("result", service.getFrontBoardPasswordChk(cmtVO));
			}
		}
		
		return "dojson";
	}
	
	@RequestMapping(value="write")
	public String write(@ModelAttribute("boardVO")FrontBoardVO boardVO, HttpServletRequest req, HttpServletResponse res) throws Exception{
		//게시판 환경설정 조회
		boardVO.setBrdSetupObj(service.getFrontBoardConfig(boardVO.getId()));
		//게시판 상세정보 조회
		boardVO.setBoardObj(service.getFrontBoardInfo(boardVO));
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			boardVO.setName(String.valueOf(sessObject.getUserInfo().getUserName()));
			boardVO.setEmail(String.valueOf(sessObject.getUserInfo().getXkey().get("email")));
		}
		
		//리스트 보기권한이 필요한 경우 로그인 페이지로 이동
		if(Integer.valueOf(boardVO.getBrdSetupObj().getBdLvlW()) != 0){
			//로그인이 필요합니다
		}
		
		if(sessObject.isShopLogin()){
			boardVO.setMno(sessObject.getUserInfo().getM_no());
			boardVO.setLevel(String.valueOf(sessObject.getUserInfo().getXkey().get("level")));
		}
		
		return "/shop/board/write";
	}
	
	@RequestMapping(value="indb")
	public String writeIndb(@ModelAttribute("boardVO")FrontBoardVO boardVO, HttpServletRequest req, HttpServletResponse res, MultipartHttpServletRequest mhsq) throws Exception{
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			boardVO.setLevel(String.valueOf(sessObject.getUserInfo().getXkey().get("level")));
			boardVO.setIciAdmin(79 < Integer.parseInt(boardVO.getLevel()) ? true : false);
			boardVO.setEmail(String.valueOf(sessObject.getUserInfo().getXkey().get("email")));
		}
		
		boardVO.setIp(InetAddress.getLocalHost().getHostAddress());
//		int gap = 0;
		
		//게시판 설정 확인
		boardVO.setBrdSetupObj(service.getFrontBoardConfig(boardVO.getId()));
		boardVO.setFrom(boardVO.getBrdSetupObj().getBdSkin());
		
//		boardVO.setBoardObj(service.getFrontBoardIndex(boardVO));
		boardVO.setBoardObj(service.getFrontBoardInfo(boardVO));
		
		if(boardVO.getBoardObj() != null){
			boardVO.setIdx(!"".equals(boardVO.getBoardObj().getIdx()) ? boardVO.getBoardObj().getIdx().substring(1) : "999" );
			boardVO.setMain(boardVO.getBoardObj().getMain()-1);
			boardVO.setSub(boardVO.getBoardObj().getSub());
		}else{
			boardVO.setIdx("999");
			boardVO.setMain(-1);
		}
		
		boardService.getDirectoryMkdir(boardVO.getId()); //폴더 생성합니다
		
		//공지글 입력의 경우
		boardVO.setRtCnt(service.getFrontBoardIndexCount(boardVO));
		
		
		//공지글의 경우
		if("o".equals(boardVO.getNotice())){
			if("modify".equals(boardVO.getMode())){
				if(!("o".equals(boardVO.getNotice()) && "o".equals(boardVO.getBoardObj().getNotice()))){
					boardVO.setBoardObj(service.getFrontBoardIndex(boardVO));
					
					boardVO.setIdx(boardVO.getBoardObj().getIdx().substring(1));
					boardVO.setMain(boardVO.getBoardObj().getMain()-1);	
				}
			}
			
			if("o".equals(boardVO.getNotice())){
				boardVO.setRtCnt(service.getFrontBoardNotiCount(boardVO));
				if( 0 >= boardVO.getRtCnt()){
					boardVO.setMain(-10000);
				}

				//공지글이 첫글일때 inf테이블데이타 저장
//				boardVO.setNotice("");
//				boardVO.setRtCnt(service.getFrontBoardNotiCount(boardVO));
				if( 0 == boardVO.getRtCnt()){
				}
			}
			
		} 
/*		else if(0 > boardVO.getMain()){
			//main이 음수의 경우도 공지글
			boardVO.setIdx(String.valueOf(Integer.parseInt(boardVO.getIdx())));
			boardVO.setMain(5000);
			
			if(service.getFrontBoardInfCount(boardVO) == 0){
				service.insertFrontBoardInf(boardVO);
			}
			
			boardVO.setNotice("o");
			boardVO.setRtCnt(service.getFrontBoardNotiCount(boardVO));
			
			if(0 < boardVO.getRtCnt()){
				service.updateFrontBoardNotiIdx(boardVO);
				service.updateFrontBoardInfNum(boardVO);
				boardVO.setIdx(String.valueOf(Integer.parseInt(boardVO.getIdx())));
				service.updateFrontBoardInfNum2(boardVO);
			}
		}*/
		
		if("modify".equals(boardVO.getMode())){
			if(boardVO.isIciAdmin()){
				boardVO.setPrivModify(1);
			}
			if(String.valueOf(boardVO.getBoardObj().getMno()) != null){
				if(String.valueOf(sessObject.getUserInfo().getM_no()).equals(String.valueOf(boardVO.getBoardObj().getMno()))){
					boardVO.setPrivModify(1);
				}
			}else if(boardVO.getPassword() != null){
				if(service.getFrontBoardSearchNo(boardVO) != 0){
					boardVO.setPrivModify(1);
				}
			}
			if(boardVO.getAttach() != null){
				if(!boardVO.getAttach().isEmpty()){
					if(logger.isDebugEnabled()){
						logger.debug("@@ board oldfile >>"+boardVO.getOldfile());
					}
					if(!"".equals(boardVO.getOldfile())){
						FileUtil.deleteFile(ConfigClass.value("FILE_PATH") + "gd_bd_"+ boardVO.getId() +File.separator, boardVO.getOldfile());						
					}
					boardVO.setNewfile(FileUtil.uploadFile2(ConfigClass.value("FILE_PATH") + "gd_bd_"+ boardVO.getId() +File.separator, boardVO.getAttach().getOriginalFilename(),boardVO.getAttach().getBytes(), false));
				}
			}
			//게시글 수정
			service.updateFrontBoardInfo(boardVO);
		}else if("write".equals(boardVO.getMode())){
			
			if(boardVO.getAttach() != null){
				if(!boardVO.getAttach().isEmpty()){
					boardVO.setNewfile(FileUtil.uploadFile2(ConfigClass.value("FILE_PATH") + "gd_bd_"+ boardVO.getId() +File.separator, boardVO.getAttach().getOriginalFilename(),boardVO.getAttach().getBytes(), false));
				}
			}
			
			
			if(service.getFrontBoardInfCount(boardVO) == 0){
				service.insertFrontBoardInf(boardVO);
			}
			service.updateFrontBoardInfAddNum(boardVO);
			service.insertFrontBoardInfo(boardVO);
		}
		
		return "redirect:/shop/board/list?id="+boardVO.getId();
	}
	
	@RequestMapping(value="delete")
	public String delete(@ModelAttribute("boardVO")FrontBoardVO boardVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ board delete page ");
		}
		
		return "/shop/board/delete";
	}
	
	
	@RequestMapping(value="indb/delete")
	public String deleteIndb(@ModelAttribute("boardVO")FrontBoardVO boardVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ board indb delete ");
		}
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			boardVO.setLevel(String.valueOf(sessObject.getUserInfo().getXkey().get("level")));
			boardVO.setIciAdmin(79 < Integer.parseInt(boardVO.getLevel()) ? true : false);
			boardVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
		if(!"".equals(boardVO.getOldfile())){
			if(logger.isDebugEnabled()){
				logger.debug("@@ board delete file >> "+boardVO.getOldfile());
			}
			FileUtil.deleteFile(ConfigClass.value("FILE_PATH") + "gd_bd_"+ boardVO.getId() +File.separator, boardVO.getOldfile());
		}
		
		//게시물 삭제
		service.deleteFrontBoardInfo(boardVO);
		//게시물 댓글삭제
		service.deleteBoardMemo(Integer.parseInt(boardVO.getNo()));
		//게시판 INF에 num값 빼기
		service.updateBoardInfSubNum(boardVO.getId());
		
//		boardVO.setBoardObj(service.getFrontBoardInfo(boardVO));
//		if(boardVO.getBoardObj() != null){
//			FrontBoardCmtVO cmtVO = new FrontBoardCmtVO();
//			cmtVO.setId(boardVO.getId());
//			cmtVO.setNo(Integer.parseInt(boardVO.getNo()));
//			cmtVO.setPassword(boardVO.getPassword());
//			boardVO.setRtCnt(service.getFrontBoardPasswordChk(cmtVO));
//			
//			if(boardVO.isIciAdmin() || boardVO.getMno()==boardVO.getBoardObj().getMno() || 0 < boardVO.getRtCnt() ){
//
//			}
//		}
		
		return "redirect:/shop/board/list?id="+boardVO.getId();
	}
	
}
