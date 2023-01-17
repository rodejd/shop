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
package com.wepinit.wepinit_shop.xmall.seller.board.dao;

import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsQna;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsReview;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberQna;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberGoodsrevwGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberqna;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerGoodsQnaVO;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerGoodsReviewVO;
import com.wepinit.wepinit_shop.xmall.seller.board.vo.SellerMemberQnaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SellerMemberQnaMapper {

	public int getMemberQnaTotalCount(SellerMemberQnaVO memQnaVO);				//게시판 총갯수
	public int getMemberQnaCount(SellerMemberQnaVO memQnaVO);					//게시판 검색 총 건수
	public List<MemberMemberqna> getMemberQnaList(SellerMemberQnaVO memQnaVO);	//게시판 검색 총 건수
	
	public GdMemberQna getMemberQnaInfo(int sno);								//1:1문의관리 글반환	
	public List<GdMember> getMemberInfo();										//1:1문의관리 관리자 정보반환
	public GdMemberQna getMemberQnaParentInfo(int parent);						//1:1문의관리 원글 정보반환
	
	public void deleteMemberQna(int sno);										//1:1문의관리 삭제
	public void updateMemberQna(SellerMemberQnaVO memQnaVO);					//1:1문의관리 수정
	public void insertMemberQnaReply(SellerMemberQnaVO memQnaVO);				//1:1문의관리 등록

	public int getGoodsQnaTotalCount(SellerGoodsQnaVO goodsQnaVO);						//상품후기관리 총건수
	public List<String> getGoodsQnaGoodsNo(SellerGoodsQnaVO goodsQnaVO);				//상품후기관리 상품번호반환
	public List<String> getGoodsQnaGoodsParent(SellerGoodsQnaVO goodsQnaVO);			//상품후기관리 원글(부모글) 반환
	public int getGoodsQnaGoodsSearchCount(SellerGoodsQnaVO goodsQnaVO);				//상품후기관리 검색 총 건수
	public List<GdGoodsQna> getGoodsQnaGoodsSearchList(SellerGoodsQnaVO goodsQnaVO);	//상품후기관리 리스트 반환
	
	public GdGoodsQna getGoodsQnaInfo(int sno);											//상품후기관리 단건 조회
	
	public void deleteGoodsQna(int parseInt);											//상품후기관리 선택삭제
	public void updateGoodsQna(SellerGoodsQnaVO goodsQnaVO);							//상품후기관리 수정
	public void insertGoodsQnaReply(SellerGoodsQnaVO goodsQnaVO);						//상품후기관리 등록
	
	public int getGoodsReviewTotalCount(String sellerCd);											//상품평관리 총 건수
	public List<String> getGoodsReviewGoodsNo(SellerGoodsReviewVO goodsRevwVO);						//상품평관리 상품명으로 상품번호반환
	public List<String> getGoodsReviewGoodsParent(SellerGoodsReviewVO goodsRevwVO);					//상품평관리 원글(부모글) 반환
	public int getGoodsReviewSearchCount(SellerGoodsReviewVO goodsRevwVO);							//상품평관리 검색 건수
	public List<MemberGoodsrevwGoods> getGoodsReviewSearchList(SellerGoodsReviewVO goodsRevwVO);	//상품권관리 리스트 반환

	public GdGoodsReview getGoodsReviewInfo(int sno);									//상품평관리 글 조회 
	public Map<String, Object> getGoodsReviewGoodsInfo(int goodsno);					//상품평관리 상품정보 조회 
	public List<GdMember> getGoodsReviewAuthMember();									//상품평관리 권한
	public GdMember getGoodsReviewMemberInfo(int mno);									//상품평관리 회원정보 조회
	
	public void deleteGoodsReview(int parseInt);
	public void updateGoodsReview(SellerGoodsReviewVO goodsRevwVO);
	public void insertGoodsReviewReply(SellerGoodsReviewVO goodsRevwVO);

}
