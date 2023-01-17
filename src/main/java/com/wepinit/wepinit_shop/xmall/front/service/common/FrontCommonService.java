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
* 설명 				: 	사용자 Community Service
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.service.common;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMainCampaign;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import com.wepinit.wepinit_shop.xmall.front.dao.common.FrontCommonMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.common.FrontExchangeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class FrontCommonService {
	
	Logger logger = LoggerFactory.getLogger(FrontCommonService.class);
	
	@Resource
	FrontCommonMapper mapper;	

	
	/**
	 * 카테고리 목록(front 상품 카테코리 left menu)
	 * @return
	 */
	public List<GdCategory> getFrontCommonCategoryList(){
		
		return mapper.getFrontCommonCategoryList();
	}
	
	/**
	 * 브랜드 목록(front 상품 브랜드 목록)
	 * @return
	 */
	public List<GdGoodsBrand> getFrontCommonBrandList(){
		
		return mapper.getFrontCommonBrandList();
	}
	
	/**
	 * 커뮤니티 게시판 목록(front 게시판 Id, Name 가져오기)
	 * @return
	 */
	public List<XmBoardSetup> getFrontCommonBoardList(){
	
		return mapper.getFrontCommonBoardList();
	}
	
	public List<GdMainCampaign> getFrontCommonMainCampaignList(String skin){
		
		return mapper.getFrontCommonMainCampaignList(skin);
	}


	/**
	 * 환율 API test
	 * 달러 > 원
	 */
	/*
	public String convertToDollar() {
		String API_URL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON";
		String API_KEY = "C9QdYGrPqjslJWc2vX5tr3eAGqjbF0Qm";

		String dollar = "0";

		List<FrontExchangeVO> list = new ArrayList<>();

		HttpURLConnection conn = null;
		StringBuilder sb = null;
		try {
			StringBuilder urlBuilder = new StringBuilder(API_URL); // URL
			urlBuilder.append("?" + URLEncoder.encode("authkey","UTF-8") + "=" + API_KEY); // Service Key
			urlBuilder.append("&" + URLEncoder.encode("data","UTF-8") + "=" + URLEncoder.encode("AP01", "UTF-8")); // AP01 : 환율, AP02 : 대출금리, AP03 : 국제금리
			URL url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

//			logger.info("............... dollar :: {}", sb);

			// vo 리스트 형태로 변환
			Gson gson = new Gson();
			Type listType = new TypeToken<List<FrontExchangeVO>>(){}.getType();
			list = gson.fromJson(sb.toString(), listType);

			Stream<FrontExchangeVO> stream = list.stream().filter((v) -> v.getCur_unit().equals("USD") && v.getResult() == 1);
			list = stream.collect(Collectors.toList());

			if (list.size() > 0) {
				logger.info("........ USD :: {}", list.get(0).toString());
				dollar = list.get(0).getDeal_bas_r().replaceAll(",", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

//		return sb.toString();
//		return list;
		return dollar;

	}
	*/

	
}