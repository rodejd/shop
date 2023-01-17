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
package com.wepinit.wepinit_shop.xmall.admin.controller.common;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.OrderService;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderExcelVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;


@Controller
@RequestMapping("/shop/admin/excel/*")
public class ExcelController {

	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class); 

	@Resource
	OrderService service;
	
	/**
	 * 관리자 > 주문관리 > 주문리스트 download
	 * => OrderController 의 list 의 검색 조건을 따라갑니다.
	 */
	@RequestMapping(value="order_excel.doxls")
	public String orderExcelDown(@ModelAttribute("orderExcelVO") OrderExcelVO orderExcelVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {

		if(logger.isDebugEnabled()) {
			logger.debug("################ orderExcelDown || " + orderExcelVO.toString());
		}

		res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Disposition","attachment;filename=GDorder_"+orderExcelVO.getMode()+"_"+ DateUtil.getDate("yyyyMMddHHmm")+".xls");
		res.setHeader("Content-Description", "JSP Generated Data");
		res.setHeader("Content-Description", "style=mso-number-format:'\\@'");
		
		/*
		 * 1.주문권한관리
		 */
		AdminSessionObject adminSO = null;
		String level = "";
		Map<String, Object> xkey = null;
		
		adminSO = AdminSessionObject.getSessionObject(req);
		
		if( adminSO.isLogin() && null != adminSO.getUserInfo() ){
		
			xkey = adminSO.getUserInfo().getXkey();
			level = String.valueOf(xkey.get("level"));
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>level" + level);
			}
			
			if( 0 > ShopConfig.getProperty("level"+level).indexOf("order")){
				throw new BizException("member.00002", CommonConstants.MAIN_ADMIN_URL);	// 주문관리 권한이 없습니다.
			}
		}
		
		String[][] ordXls = null;
		if ("order".equals(orderExcelVO.getMode())) {	
			String[] chk = StringUtil.split(ShopConfig.getProperty("orderXls"), "^");
			if (chk == null || chk.length == 0) chk = new String[19];
			int idx = 0;
			ordXls = new String[][] {
				{ "번호"			, "no"				, chk[idx++] },
				{ "주문번호"		, "ordno"			, chk[idx++] },
				{ "주문자명"		, "nameorder"		, chk[idx++] },
				{ "이메일"			, "email"			, chk[idx++] },
				{ "주문자전화번호"	, "phoneorder"		, chk[idx++] },
				{ "주문자핸드폰"	, "mobileorder"		, chk[idx++] },
				{ "받는분이름"		, "namereceiver"	, chk[idx++] },
				{ "받는분전화번호"	, "phonereceiver"	, chk[idx++] },
				{ "받는분핸드폰"	, "mobilereceiver"	, chk[idx++] },
				{ "우편번호"		, "zipcode"			, chk[idx++] },
				{ "주소"			, "address"			, chk[idx++] },
				{ "배송메세지"		, "memo"			, chk[idx++] },
				{ "결제수단"		, "settlekind"		, chk[idx++] },
				{ "결제금액"		, "settleprice"		, chk[idx++] },
				{ "주문일자"		, "orddt"			, chk[idx++] },
				{ "주문상태"		, "step"			, chk[idx++] },
				{ "배송코드"		, "deliveryno"		, chk[idx++] },
				{ "송장번호"		, "deliverycode"	, chk[idx++] },
				{ "배송일"			, "ddt"				, chk[idx++] }
			};
		} else {
			String[] chk = StringUtil.split(ShopConfig.getProperty("orderGoodsXls"), "^");
			if (chk == null || chk.length == 0) chk = new String[29];
			int idx = 0;
			ordXls = new String[][] {
				{ "번호"			, "no"				, chk[idx++] },
				{ "일련번호"		, "sno"				, chk[idx++] },
				{ "주문번호"		, "ordno"			, chk[idx++] },
				{ "주문자명"		, "nameorder"		, chk[idx++] },
				{ "이메일"		, "email"			, chk[idx++] },
				{ "주문자전화번호"	, "phoneorder"		, chk[idx++] },
				{ "주문자핸드폰"	, "mobileorder"		, chk[idx++] },
				{ "받는분이름"		, "namereceiver"	, chk[idx++] },
				{ "받는분전화번호"	, "phonereceiver"	, chk[idx++] },
				{ "받는분핸드폰"	, "mobilereceiver"	, chk[idx++] },
				{ "우편번호"		, "zipcode"			, chk[idx++] },
				{ "주소"			, "address"			, chk[idx++] },
				{ "배송메세지"		, "memo"			, chk[idx++] },
				{ "결제수단"		, "settlekind"		, chk[idx++] },
				{ "주문일자"		, "orddt"			, chk[idx++] },
				{ "주문상태"		, "step"			, chk[idx++] },
				{ "상품명(영문)"	, "goodsnm"			, chk[idx++] },
				{ "상품명(국문)"	, "goodsnmKR"		, chk[idx++] },
				{ "상품명(중문)"	, "goodsnmCN"		, chk[idx++] },
				{ "상품코드"		, "goodscd"			, chk[idx++] },
				{ "옵션"			, "addopt"			, chk[idx++] },
				{ "원산지"		, "origin"			, chk[idx++] },
				{ "브랜드"		, "brandnm"			, chk[idx++] },
				{ "수량"			, "ea"				, chk[idx++] },
				{ "매입가"		, "supply"			, chk[idx++] },
				{ "상품가격"		, "price"			, chk[idx++] },
				{ "주문결제가격"	, "sprice"			, chk[idx++] },
				{ "배송코드"		, "deliveryno"		, chk[idx++] },
				{ "송장번호"		, "deliverycode"	, chk[idx++] },
				{ "배송일"		, "ddt"				, chk[idx++] }
			};
		}
		
		// 주문관리 > 주문관리 > 주문리스트 다운로드
		orderExcelVO.setOrderList(service.getOrderXls(orderExcelVO, req.getParameterValues("step2")));
		model.addAttribute("selConfList" , ordXls);
		

		if (logger.isDebugEnabled()) {
			logger.debug("######### orderExcelVO ##########" + orderExcelVO.toString());
		}
		
		return "/shop/admin/order/dnXls";
	}

    /**
    * 파일(첨부파일, 이미지등) 다운로드.(업체견적서)
    * @throws UnsupportedEncodingException 
    */
   @RequestMapping(value = "fileDownload")
   public void fileDownload4(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
       
       String filename = request.getParameter("fN");
       String dir = request.getParameter("dir");
       String path = ConfigClass.value(dir) + "goods/xls/sample";
       String realPath = "";
       if (logger.isDebugEnabled()) {
    	   logger.debug("filename: "+filename);
       }       
       if (filename == null || "".equals(filename)) {
           return;
       }
        
       try {
           String browser = request.getHeader("User-Agent"); 
           //파일 인코딩 
           if (browser.contains("MSIE") || browser.contains("Trident")
                   || browser.contains("Chrome")) {
               filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+","%20");
           } else {
               filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
           }
       } catch (UnsupportedEncodingException ex) {
           if (logger.isDebugEnabled()) {
        	   logger.debug("UnsupportedEncodingException");
           }            
       }
       realPath = path +"/"+filename;
       logger.debug("realPath: "+realPath);
       File file1 = new File(realPath);
       if (!file1.exists()) {
           return ;
       }
        
       // 파일명 지정        
       response.setContentType("application/octer-stream");
       response.setHeader("Content-Transfer-Encoding", "binary;");
       response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
       try {
           OutputStream os = response.getOutputStream();
           FileInputStream fis = new FileInputStream(realPath);

           int ncount = 0;
           byte[] bytes = new byte[512];

           while ((ncount = fis.read(bytes)) != -1 ) {
               os.write(bytes, 0, ncount);
           }
           fis.close();
           os.close();
       } catch (FileNotFoundException ex) {
    	   logger.debug("FileNotFoundException");
       } catch (IOException ex) {
    	   logger.debug("IOException");
       }
   }	
}
