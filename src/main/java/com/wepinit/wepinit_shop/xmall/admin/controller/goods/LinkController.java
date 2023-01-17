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
package com.wepinit.wepinit_shop.xmall.admin.controller.goods;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.LinkService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.LinkVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/shop/admin/goods/*")
public class LinkController {

	@Resource
	LinkService lService;
	
	@Resource
	GoodsService goodsService;
	
 	private static final Logger logger = LoggerFactory.getLogger(LinkController.class); 
 	
	@RequestMapping(value = "link")
	public String Link(@ModelAttribute("linkVO") LinkVO linkVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//총 건수
		linkVO.setTotalCount(lService.getLinkListTotalCount());
		
		//카테고리
		if(linkVO.getCate() != null){
			if(linkVO.getCate().length > 0){
				for(int i=0; i<linkVO.getCate().length; i++){
					if(!"".equals(StringUtil.nullConv(linkVO.getCate()[i], ""))){
						linkVO.setStrCategory(linkVO.getCate()[i]);
					}
				}
			}
		}
		linkVO.setLinkList(lService.getLinkList(linkVO));
		
		/*String[] arrTmp;
		
		// 연결/이동/복사 에 사용되는 카테고리 셋팅
		arrTmp = linkVO.getCate();
		if ( null != arrTmp ) {
			for ( int i=0 ; i < arrTmp.length ; i++ ) {
				if ( !"".equals(arrTmp[i] !=null ? arrTmp[i] : "") ) {
					linkVO.setStrCategorySub(arrTmp[i]); // select box setting value
				}
			}
		}
		
		if(linkVO.getIndicate().equals("search")){
			// 검색조건 > 분류선택 데이터 셋팅
			if(linkVO.getCate() !=null){
				arrTmp = linkVO.getCate();
					for(int i =0; i< arrTmp.length ; i++){
						if(!(arrTmp[i] !=null ? arrTmp[i] : "").equals("")){
							linkVO.setStrCategory(arrTmp[i]);
						}
					}
			}
			
			 >>>>>>>>>>> dynamic query >>>>>>>>>> 
			if(!linkVO.getStrCategory().equals("") || linkVO.getUnlink().equals("Y") ){
				linkVO.setAddColumn("c.category");
				linkVO.setSchCategory("category like '" + linkVO.getStrCategory() + "%' or category like '%,"+ linkVO.getStrCategory() +"'");
				linkVO.setJoinTable(" left outer join (select goodsno,group_concat(category,'') as category from gd_goods_link group by goodsno) c on a.goodsno = c.goodsno");
				if(linkVO.getUnlink().equals("Y")){
					linkVO.setSchEtc("isnull(c.goodsno)");
					linkVO.setSchCategory("");
				}
			}
			// 검색조건 > 분류가 연결되지 않는 상품 조회
			if(!linkVO.getStrCategory().equals("") && linkVO.getUnlink().equals("Y")){
				linkVO.setSchEtc("isnull(c.goodsno)");
				linkVO.setSchCategory("");
			}
			// 검색조건 > 검색어
			if(!linkVO.getSword().equals("")){
				linkVO.setSchWord(linkVO.getSkey()+" like '%" +linkVO.getSword() + "%'");
			}
			// 검색조건 > 상품출력여부
			if ( !"".equals(linkVO.getOpen())) {
				linkVO.setSchOpen("a.open = '" + linkVO.getOpen()+ "'");
			}

			// order by
			linkVO.setOrderBy("-a.goodsno");
			
			// 상품전체개수
			linkVO.setRowCount(lService.goodsCount(linkVO));
			if ( linkVO.getRowCount() ==0) {
				linkVO.setRowCount(0);
			}
			//검색된 갯수
			linkVO.setTotalCount(lService.goodsAllCount());
			
			 * ===============================================================================================
			 * 페이징과 리스트의 조회시 페이징이 먼저 수행이 되어야 한다. 
			 * 페이징 처리가 되면서 파라미터의 requestSet에 페이지 리스트를 가지고 오기 위한 변수가 세팅된다. 
			 * ===============================================================================================
			 

			 //////////////////페이징처리부분//////////////
			
			if(linkVO.getSearchCount() <= linkVO.getPageSize()){
				linkVO.setPageSize(linkVO.getRowCount());
			}else{
				linkVO.setPageSize(20);
			}
			
			int s =linkVO.getSearchCount()/linkVO.getPageSize();
			logger.debug("@@@@ s="+s);
			if( linkVO.getSearchCount()%linkVO.getPageSize() ==0){
				linkVO.setPageGroupSize(s);	
			}else{
				linkVO.setPageGroupSize(s+1);
			}
			
			int max= linkVO.getRowCount();  // 페이지 목록 사이즈
			int top = linkVO.getRowStart();  // 시작 row번호
			int[] vnum = new int[max];
			for(int i=0;i<max;i++){
				vnum[i] = max - top -i;
			}
			linkVO.setVnum(vnum);
			linkVO.setLinkList(lService.getLinkList(linkVO));
			
		}*/
		
		return "/shop/admin/goods/link";
	}
	
	@RequestMapping(value = "link/indb")
	public String LinkIndb(@ModelAttribute("linkVO") LinkVO linkVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ link/indb >>"+ linkVO.getMode());
		}
		
		HashMap param = new HashMap(); // DB처리를 위한 map
		
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 연결
		 * ===========================================================================================
		 */
		if("link".equals(linkVO.getMode())){
			
			String goodsNo = "";
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						goodsNo += linkVO.getChk()[i] + ",";
					}
				}
			}
			if(logger.isDebugEnabled()){
				logger.debug("@@ goodsNo Array >> "+goodsNo);
			}
			
			linkVO.setLinkEventList(lService.getGoodsLinkEventConnectLIST(goodsNo.substring(0, goodsNo.length()-1)));
			
			if(linkVO.getCateSub() != null){
				if(linkVO.getCateSub().length > 0){
					for(int i=0; i<linkVO.getCateSub().length; i++){
						if(!"".equals(StringUtil.nullConv(linkVO.getCateSub()[i], ""))){
							linkVO.setStrCategorySub(linkVO.getCateSub()[i]);
						}
					}
				}
			}
			
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						
						if(logger.isDebugEnabled()){
							logger.debug("@@ print Parameter chk >> "+linkVO.getChk()[i]+" category sub >> "+ linkVO.getStrCategorySub() + " hidden >> "+ lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()));
						}
						
						// 이벤트 연결이 되어있는 상품의 개수를 추출한다.
						lService.setGoodsCategoryINSERT01(linkVO.getChk()[i], linkVO.getStrCategorySub(), lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()));
						
						if(logger.isDebugEnabled()){
							logger.debug("@@ isToday >>"+ linkVO.getIsToday());
						}
						
						// 상품등록일 수정
						if("Y".equals(linkVO.getIsToday())){
							lService.setGoodsInfoUPDATE(linkVO.getChk()[i]);
						}
						
						// 이벤트 카테고리 연결이 없을 경우만 등록한다.
						int j=0;
						while (linkVO.getLinkEventList() != null && linkVO.getLinkEventList().size() > j ){
							if(linkVO.getChk()[i] == linkVO.getLinkEventList().get(j).getGoodsno()
									&& linkVO.getLinkEventList().get(j).getSno() != 0
									&& !"".equals(linkVO.getLinkEventList().get(j).getMode())
									&& linkVO.getLinkEventList().get(j).getDisCount() != 0){
								
								if(logger.isDebugEnabled()){
									logger.debug("@@ goodsno, mode, sort >> ");
								}
								lService.setGoodsDisplayINSERT(linkVO.getChk()[i],  linkVO.getLinkEventList().get(j).getMode(), linkVO.getLinkEventList().get(j).getSort());
								break;
							}
							j++;
						}
					
					}
				}
			}
			
		}
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 이동
		 * ===========================================================================================
		 */
		else if("move".equals(linkVO.getMode())){
			
//			String goodsNo = "";
//			if(linkVO.getChk() != null){
//				if(linkVO.getChk().length > 0){
//					for(int i=0; i<linkVO.getChk().length; i++){
//						goodsNo += linkVO.getChk()[i] + ",";
//					}
//				}
//			}
//			if(logger.isDebugEnabled()){
//				logger.debug("@@ goodsNo Array >> "+goodsNo);
//			}
//			
//			linkVO.setLinkEventList(lService.getGoodsLinkEventConnectLIST(goodsNo.substring(0, goodsNo.length()-1)));
			
			// 연결/이동/복사 에 사용되는 카테고리 셋팅
			if(linkVO.getCateSub() != null){
				if(linkVO.getCateSub().length > 0){
					for(int i=0; i<linkVO.getCateSub().length; i++){
						if(!"".equals(StringUtil.nullConv(linkVO.getCateSub()[i], ""))){
							linkVO.setStrCategorySub(linkVO.getCateSub()[i]);
						}
					}
				}
			}
			
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						lService.updateGoodsLink(linkVO.getStrCategorySub(), lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()), 
								linkVO.getChk()[i], req.getParameter("category_"+linkVO.getChk()[i]));
						
						// 상품등록일 수정
						if("Y".equals(linkVO.getIsToday())){
							lService.setGoodsInfoUPDATE(linkVO.getChk()[i]);
						}
					}
				}
			}
			
		}
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 복사
		 * ===========================================================================================
		 */
		else if("copyGoodses".equals(linkVO.getMode())){
			
			String goodsNo = "";
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						goodsNo += linkVO.getChk()[i] + ",";
					}
				}
			}
			if(logger.isDebugEnabled()){
				logger.debug("@@ goodsNo Array >> "+goodsNo);
			}
			
			linkVO.setLinkEventList(lService.getGoodsLinkEventConnectLIST(goodsNo.substring(0, goodsNo.length()-1)));
			
			if(linkVO.getCateSub() != null){
				if(linkVO.getCateSub().length > 0){
					for(int i=0; i<linkVO.getCateSub().length; i++){
						if(!"".equals(StringUtil.nullConv(linkVO.getCateSub()[i], ""))){
							linkVO.setStrCategorySub(linkVO.getCateSub()[i]);
						}
					}
				}
			}
			
			int copyGoodsNo = 0;
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						
						// 상품복사를 완료한뒤 상품번호를 받는다.
						param.clear();
						param.put("goodsno", String.valueOf(linkVO.getChk()[i]));
						copyGoodsNo = goodsService.copyGoods2(param);
						
						// 복사된 상품번호로 변경하여 update수행
						lService.updateGoodsLink(linkVO.getStrCategorySub(), lService.getGoodsCategoryHiddenState(linkVO.getStrCategorySub()), 
								copyGoodsNo, req.getParameter("category_"+linkVO.getChk()[i]));
						
						// 이벤트 카테고리 연결이 없을 경우만 등록한다.
						int j=0;
						while (linkVO.getLinkEventList() != null && linkVO.getLinkEventList().size() > j ){
							if(linkVO.getChk()[i] == linkVO.getLinkEventList().get(j).getGoodsno()
									&& linkVO.getLinkEventList().get(j).getSno() != 0
									&& !"".equals(linkVO.getLinkEventList().get(j).getMode())
									&& linkVO.getLinkEventList().get(j).getDisCount() != 0){
								
								if(logger.isDebugEnabled()){
									logger.debug("@@ goodsno, mode, sort >> ");
								}
								
								lService.setGoodsDisplayINSERT(linkVO.getChk()[i],  linkVO.getLinkEventList().get(j).getMode(), linkVO.getLinkEventList().get(j).getSort());
								break;
							}
							j++;
						}
					}
				}
			}
			
		}
		/*
		 * ===========================================================================================
		 * 상품일괄관리 > 빠른이동/복사/삭제 > 삭제
		 * ===========================================================================================
		 */
		else if("delGoodses".equals(linkVO.getMode())){
			
			if(linkVO.getChk() != null){
				if(linkVO.getChk().length > 0){
					for(int i=0; i<linkVO.getChk().length; i++){
						param.clear();
						param.put("goodsno", String.valueOf(linkVO.getChk()[i]));
						goodsService.deleteGoods(param);
					}
				}
			}
		}
		
		/*logger.debug("@@@ link indb"+linkVO.toString());
		//paging처리
		if(linkVO.getSearchCount() <= linkVO.getPageSize()){
			linkVO.setPageSize(linkVO.getRowCount());
			linkVO.setPageGroupSize(1);
		}else{
			linkVO.setPageSize(20);
		}
		int max= linkVO.getSearchCount();  // 페이지 목록 사이즈
		int top = linkVO.getRowStart();  // 시작 row번호
		int[] vnum = new int[max];
		for(int i=0;i<max;i++){
			vnum[i] = max - top -i;
		}
		linkVO.setVnum(vnum);
		
		
				// 연결/이동/복사 에 사용되는 카테고리 셋팅
				String[] arrTmp = linkVO.getCate();
				int intTmp=0;
				String tmp="";
				String category="";
				
				if ( null != arrTmp ) {
					for ( int i=0 ; i < arrTmp.length ; i++ ) {
						if ( !"".equals(arrTmp[i] !=null ? arrTmp[i] : "") ) {
							linkVO.setStrCategorySub(arrTmp[i]); // select box setting value
						}
					}
					
					if (null != linkVO.getStrCategorySub() && !"".equals(linkVO.getStrCategorySub())) {
						for (int i = 0; i < linkVO.getStrCategorySub().length() / 3; i++) {
							tmp += "'" + linkVO.getStrCategorySub().substring(0, i * 3) + "',";
							i++;
						}
						category = tmp.substring(0, tmp.length() - 1);
						intTmp =lService.getGoodsCategoryHiddenState(category);
					
					}
					
					int[] arrGoodsNo = linkVO.getChk();
					String goodsNo="";
					if ( null != arrGoodsNo ) {
						
						for( int i=0 ; i < arrGoodsNo.length ; i++ ) {
							goodsNo += arrGoodsNo[i] + ",";
						}
						
						// 이벤트 연결이 되어있는 상품의 개수를 추출한다.
						List<GdEventGoodslinkGoodsdisplay> rtList =lService.getGoodsLinkEventConnectLIST(goodsNo.substring(0, goodsNo.length()-1));
						
						for(int  i=0 ; i < arrGoodsNo.length ; i++ ) {
							lService.setGoodsCategoryINSERT01(arrGoodsNo[i], category, intTmp);
							rs.clear();
							rs.setProperty("goodsno", arrGoodsNo[i]);
							rs.setProperty("category", category);
							rs.setProperty("hidden", String.valueOf(intTmp));
							rs.setProperty("bold_flag",  requestSet.getProperty("bold_flag", "0"));
							dbHandler.executeXmlUpdate("xmall_goods/goods_category_INSERT01", rs);
							
							// 상품등록일 수정
							
							if( "Y".equals(linkVO.getIsToday()) ){
								lService.setGoodsInfoUPDATE(arrGoodsNo[i]);;
							}
							
							// 이벤트 카테고리 연결이 없을 경우만 등록한다.
							int j = 0;
							int k = rtList.get(j).getSort();
							while ( null != rtList && j < rtList.size()) {
								if ( arrGoodsNo[i]==rtList.get(j).getGoodsno()
										&& !"0".equals(rtList.get(j).getSno())
										&& !"".equals(rtList.get(j).getMode())
										&& "0".equals(rtList.get(j).getDisCount()) ) {
									GdGoodsDisplay vo = new GdGoodsDisplay();
									vo.setMode(rtList.get(j).getMode());
									vo.setSort(++k);
									lService.setGoodsDisplayINSERT(vo);
									break;
								}
								j++;
							}
						}
					}

				}*/
		
				
		return "redirect:/shop/admin/goods/link"+"?"+linkVO.getSearch();
	}

}
