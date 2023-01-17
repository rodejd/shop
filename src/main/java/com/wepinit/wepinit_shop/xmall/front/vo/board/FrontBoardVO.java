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
* 설명 				: 	사용자 Community VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.board;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBdBoard;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBoardMemo;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import com.wepinit.wepinit_shop.xmall.common.vo.join.BdBoardmemo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class FrontBoardVO extends PageMaker{
	
	private String mode;				//게시판 Type
	private String id;					//게시판 ID
	private String no;					//게시글 No
	private String name;
	private String userId;
	private String email;
	private int mno;
	private String from;				//게시판 mode
	private String idx;
	private long main=0;
	private String sub ="";
	private String password;
	private String homepage="";
	private String subject="";
	private String contents="";
	private String urlLink="";
	private String oldfile="";
	private String newfile="";
	private String secret="";
	private String category="";
	private String ip="";
	
	
	private String searchWord;		//검색어
	private String searchMode;		//검색 Key
	private String subSpeech;		//말머리
	private String notice="";			//공지여부
	private String speechBox;
	private String level="";				//사용자 권한 레벨
	private int rtCnt;					//게시물 건수 조회
	private boolean iciAdmin = false;
	private int privModify = 0;		//chkPrivilege 관련 변수
	private String filePath;
	private MultipartFile attach;
	private int totalCount;
	
	
	private XmBoardSetup brdSetupObj;			//게시판 환경 설정
	private List<BdBoardmemo> boardList;		//게시판+댓글
	private List<GdBdBoard> defaultBdList;		//게시판 리스트
	private List<GdBdBoard> defaultBdNotiList;	//게시판 공지 리스트
	private GdBdBoard boardObj;					//게시판 상세 정보
	private List<GdBoardMemo> boardReList;	//게시판 댓글 리스트
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public XmBoardSetup getBrdSetupObj() {
		return brdSetupObj;
	}
	public void setBrdSetupObj(XmBoardSetup brdSetupObj) {
		this.brdSetupObj = brdSetupObj;
	}
	public List<BdBoardmemo> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<BdBoardmemo> boardList) {
		this.boardList = boardList;
	}
	public List<GdBdBoard> getDefaultBdList() {
		return defaultBdList;
	}
	public void setDefaultBdList(List<GdBdBoard> defaultBdList) {
		this.defaultBdList = defaultBdList;
	}
	public List<GdBdBoard> getDefaultBdNotiList() {
		return defaultBdNotiList;
	}
	public void setDefaultBdNotiList(List<GdBdBoard> defaultBdNotiList) {
		this.defaultBdNotiList = defaultBdNotiList;
	}
	public GdBdBoard getBoardObj() {
		return boardObj;
	}
	public void setBoardObj(GdBdBoard boardObj) {
		this.boardObj = boardObj;
	}
	public List<GdBoardMemo> getBoardReList() {
		return boardReList;
	}
	public void setBoardReList(List<GdBoardMemo> boardReList) {
		this.boardReList = boardReList;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public long getMain() {
		return main;
	}
	public void setMain(long main) {
		this.main = main;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	public String getOldfile() {
		return oldfile;
	}
	public void setOldfile(String oldfile) {
		this.oldfile = oldfile;
	}
	public String getNewfile() {
		return newfile;
	}
	public void setNewfile(String newfile) {
		this.newfile = newfile;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getSubSpeech() {
		return subSpeech;
	}
	public void setSubSpeech(String subSpeech) {
		this.subSpeech = subSpeech;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getSpeechBox() {
		return speechBox;
	}
	public void setSpeechBox(String speechBox) {
		this.speechBox = speechBox;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getRtCnt() {
		return rtCnt;
	}
	public void setRtCnt(int rtCnt) {
		this.rtCnt = rtCnt;
	}
	public boolean isIciAdmin() {
		return iciAdmin;
	}
	public void setIciAdmin(boolean iciAdmin) {
		this.iciAdmin = iciAdmin;
	}
	public int getPrivModify() {
		return privModify;
	}
	public void setPrivModify(int privModify) {
		this.privModify = privModify;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public MultipartFile getAttach() {
		return attach;
	}
	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
}
