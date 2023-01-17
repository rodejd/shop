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
package com.wepinit.wepinit_shop.xmall.admin.controller.basic;

import com.wepinit.wepinit_shop.xmall.admin.vo.basic.DefaultVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.service.ShopConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/admin/basic")
public class DefaultController {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	@Resource
	ShopConfigService slfService;
	
	@RequestMapping(value="default") //쇼핑몰기본관리_기본관리_기본정보설정 디비에서 데이터 받아와서 표시
	public String defaultManage(@ModelAttribute("defaultVO") DefaultVO defaultVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String[] dim1_var0={ "shopName" ,"shopEng","adminEmail","shopUrl","compName","service"
								,"item","zipcode","address","compSerial","orderSerial","ceoName"
								,"adminName","compPhone","compFax","title","keywords","sessTime"
								,"facebook_client_id","facebook_client_secret","facebook_redirect_uri"
								,"google_client_id","google_client_secret","google_redirect_uri"
								,"kakao_client_id","kakao_client_secret","kakao_redirect_uri"
								,"apple_client_id","apply_team_id","apple_key_id","apple_redirect_uri"};
		String dim1_var = "";
		for(int j=0;j<dim1_var0.length;j++){
			if(j==0){
				dim1_var += "'";
			}else{
				dim1_var += ",'";
			}
			dim1_var += dim1_var0[j]+"'";
		}
		logger.debug("############default::dim1_var::"+dim1_var);
		List<Map<String,Object>> lst = slfService.getConfValueArry("config", "cfg", dim1_var);//gd_conf_set 뽑아옴  "config", "cfg", dim1_var 세가지를 참조하여
		Map<String, String> mapTmp = new HashMap<String, String>(); 
		for(int i=0;i<lst.size();i++){
			Map<String, Object> map = lst.get(i); 
			logger.debug("############default::map.get(dim1_var)::"+i+map.get("dim1_var"));
			logger.debug("############default::map.get(dim_val)::"+i+map.get("dim_val"));
			mapTmp.put((String)map.get("dim1_var"), (String)map.get("dim_val"));
			//dim1_var = key값 ,  dim_val=value값
		}
		defaultVO.setDbData(mapTmp);
		
        model.addAttribute("menuKey","30");
		//req.setAttribute("menuKey","30");
		
//		//1.쇼핑몰이름
//		defaultVO.setShopName(slfService.getConfValue("config", "cfg", "shopName"));
//		//2.영문이름
//		defaultVO.setShopEng(slfService.getConfValue("config", "cfg", "shopEng"));
//		//3.관리자 Email
//		slfService.getConfValue("config", "cfg", "adminEmail");
//		//4.쇼핑몰 URL
//		slfService.getConfValue("config", "cfg", "shopUrl");
//		//5.상호명
//		slfService.getConfValue("config", "cfg", "compName");
//		//6.업태
//		slfService.getConfValue("config", "cfg", "service");
//		//7.종목
//		slfService.getConfValue("config", "cfg", "item");
//		//8.사업장우편번호
//		slfService.getConfValue("config", "cfg", "zipcode");
//		//9.사업장주소
//		slfService.getConfValue("config", "cfg", "address");
//		//10.사업자번호
//		slfService.getConfValue("config", "cfg", "compSerial");
//		//11.통신판매신고번호
//		slfService.getConfValue("config", "cfg", "orderSerial");
//		//12.대표자명
//		slfService.getConfValue("config", "cfg", "ceoName");
//		//13.관리자명(개인정보)
//		slfService.getConfValue("config", "cfg", "adminName");
//		//14.전화번호
//		slfService.getConfValue("config", "cfg", "compPhone");
//		//15.팩스번호
//		slfService.getConfValue("config", "cfg", "compFax");
//		//16.쇼핑몰타이틀
//		slfService.getConfValue("config", "cfg", "title");
//		//17.검색엔진 키워드
//		slfService.getConfValue("config", "cfg", "keywords");
//		//18.자동로그아웃
//		slfService.getConfValue("config", "cfg", "sessTime");
		return "/shop/admin/basic/default";
	}
	@RequestMapping(value="indb") //쇼핑몰기본관리_기본관리_기본설정_등록클릭시 변경된사항 저장
	public String defaultUpdate(@ModelAttribute("defaultVO") DefaultVO defaultVO) throws Exception {
		logger.debug("############default_indb::defaultVO::"+defaultVO);
		//1.쇼핑몰이름
		ShopConfig.setProperty("shopName", defaultVO.getShopName());
		
		//2.영문이름
		ShopConfig.setProperty("shopEng",defaultVO.getShopEng());

		//3.관리자 Email
		ShopConfig.setProperty( "adminEmail",defaultVO.getAdminEmail());

		//4.쇼핑몰 URL
		ShopConfig.setProperty("shopUrl",defaultVO.getShopUrl());

		//5.상호명
		ShopConfig.setProperty("compName",defaultVO.getCompName());

		//6.업태
		ShopConfig.setProperty("service",defaultVO.getService());

		//7.종목
		ShopConfig.setProperty("item",defaultVO.getItem());

		//8.사업장우편번호
		ShopConfig.setProperty("zipcode",defaultVO.getZipcode());

		//9.사업장주소
		ShopConfig.setProperty("address",defaultVO.getAddress());

		//10.사업자번호
		ShopConfig.setProperty( "compSerial",defaultVO.getCompSerial());

		//11.통신판매신고번호
		ShopConfig.setProperty("orderSerial",defaultVO.getOrderSerial());

		//12.대표자명
		ShopConfig.setProperty("ceoName",defaultVO.getCeoName());

		//13.관리자명(개인정보)
		ShopConfig.setProperty("adminName",defaultVO.getAdminName());

		//14.전화번호
		ShopConfig.setProperty("compPhone",defaultVO.getCompPhone());

		//15.팩스번호
		ShopConfig.setProperty("compFax",defaultVO.getCompFax());

		//16.쇼핑몰타이틀
		ShopConfig.setProperty("title",defaultVO.getTitle());

		//17.검색엔진 키워드
		ShopConfig.setProperty("keywords",defaultVO.getKeywords());

		//18.자동로그아웃
		ShopConfig.setProperty("sessTime",defaultVO.getSessTime());
		
		//19.로그인 api설정
		ShopConfig.setProperty("facebook_client_id",defaultVO.getFacebook_client_id());
		ShopConfig.setProperty("facebook_client_secret",defaultVO.getFacebook_client_secret());
		ShopConfig.setProperty("facebook_redirect_uri",defaultVO.getFacebook_redirect_uri());
		
		ShopConfig.setProperty("google_client_id",defaultVO.getGoogle_client_id());
		ShopConfig.setProperty("google_client_secret",defaultVO.getGoogle_client_secret());
		ShopConfig.setProperty("google_redirect_uri",defaultVO.getGoogle_redirect_uri());
		
		ShopConfig.setProperty("kakao_client_id",defaultVO.getKakao_client_id());
		ShopConfig.setProperty("kakao_client_secret",defaultVO.getKakao_client_secret());
		ShopConfig.setProperty("kakao_redirect_uri",defaultVO.getKakao_redirect_uri());

		ShopConfig.setProperty("apple_client_id",defaultVO.getApple_client_id());
		ShopConfig.setProperty("apple_team_id",defaultVO.getApple_team_id());
		ShopConfig.setProperty("apple_key_id",defaultVO.getApple_key_id());
		ShopConfig.setProperty("apple_redirect_uri",defaultVO.getApple_redirect_uri());

		return "redirect:/shop/admin/basic/default";
	}
}
