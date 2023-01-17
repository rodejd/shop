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
package com.wepinit.wepinit_shop.xmall.common;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.PopupOrderService;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.util.ApplicationContextUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.ShopLibSetStock1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;


public class ShopLibFunction {
//		
//	/**
//	 * 멤버 함수용도의 log
//	 */
	public static final Logger logger = LoggerFactory.getLogger(ShopLibFunction.class);
//	
//	/**
//	 * [설명] 상품이미지 설정
//	 * 
//	 * @param src
//	 * @param size
//	 * @param tmp
//	 * @param hidden
//	 * @return
//	 */
//	
	public static String goodsimg(String src, String size, String tmp, int hidden) {

		String str = "";
		String path = "";
		String vsize = "";
		int nosize = 100; //이미지가 없을경우
//		LogUtil log = new LogUtil();
//		ResultTable rtList = null;

		boolean b = (hidden != 0);
		if( !(src.startsWith("http://") || src.startsWith("https://"))) {
			if (b)
				path = "../";
			path += "../data/goods/";
			if (hidden == 3) {
				path = "http://" + ShopConfig.getProperty("shopUrl")
						+ ShopConfig.getProperty("rootDir") + "/data/goods/";
			} else if (hidden == 4) {
				path = "/resources/shop/data/upload/goods/";
			}
		}

		if (!"".equals(size)) {
			String[] size_arr = StringUtil.explode(size, ",");
			vsize = " width=" + size_arr[0];
			if (size_arr.length == 2)
				vsize += " height=" + size_arr[1];

			if (Integer.parseInt(size_arr[0]) > 300)
				nosize = 500;
			else if (Integer.parseInt(size_arr[0]) > 130)
				nosize = 300;
			else if (Integer.parseInt(size_arr[0]) > 100)
				nosize = 130;
			else
				nosize = 100;

		}

		String webSkin = ConfigClass.WEB_SKIN;
		String onerror = "onerror=this.src='/resources/shop/data/skin/" + webSkin + "/img/common/noimg_500.gif';";
		/*	String onerror = " onerror=this.src='"
		+ ShopConfig.getProperty("rootDir") + "/data/skin/"
		+ ConfigClass.WEB_SKIN + "/img/common/noimg_"
		+ nosize + ".gif';"; */
		//String onerror =  ( 2 > hidden ) ? " onerror=this.src='" +ShopConfig.getProperty("rootDir")+ "/data/skin/"+ShopConfig.getProperty("tplSkin")+"/img/common/noimg_"+nosize+".gif';" : "onerror=this.style.display='none'";
		//$onerror = ($hidden<2) ? "onerror=this.src='".$GLOBALS[cfg][rootDir]."/data/skin/".$GLOBALS[cfg][tplSkin]."/img/common/noimg_$nosize.gif'" : "onerror=this.style.display='none'";

		if ("".equals(tmp))
			tmp = " " + tmp;

		str = "<img src='" + path + src + "'" + vsize + tmp + onerror + ">";
		//str = path+" || "+src;

		return str;
	
	}
	
	/*
	 * [설명] 상품카테고리별 게시여부
	 * 
	 * @param cate
	 * @return
	 * @throws Exception
	 */
	public static int getCateHideCnt(String category) throws Exception {

		int i = 0;
		int hiddenCnt = 0;
		String tmp = "";
		Map<String, Object> map = null;
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		if (null != category && !"".equals(category)) {
			for (i = 0; i < category.length() / 3; i++) {
				tmp += "'" + category.substring(0, i * 3) + "',";
				i++;
			}			
			map = shopLibFncService.getGoodsCategoryHiddenState(category);
			hiddenCnt = Integer.parseInt(String.valueOf(map.get("hiddenCnt")));
		}
		return hiddenCnt;
	}
	
	//코드반환
	public static List<GdCode> codeitem(String groupcd) throws Exception {
		List<GdCode> resultList = null;
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		resultList = shopLibFncService.getCodeItem(groupcd);

		return resultList;
	}

	//코드반환
	public static List<HashMap> codeitemMap(String groupcd) throws Exception {
		List<HashMap> resultList = null;
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		resultList = shopLibFncService.getCodeItemMap(groupcd);

		return resultList;
	}
	
	
	
	/** 
	 * [설명] 카테고리 단계
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int cateStep() throws Exception {
		int catestep = 0;
		Map<String, String> map = null;
		ShopLibFncService shopLibFncService = null;
		
//		ServletContext sc = req.getSession().getServletContext();
//		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
//		ShopService shopService = (ShopService) ac.getBean("shopService");

		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		map = shopLibFncService.getQnaSearch("max(length(category)) as catestep", "gd_category");

		if (null != map && 0 < map.size()) {
			catestep = Integer.parseInt(StringUtil.N2S(String.valueOf((map.get("catestep"))), "0")) / 3;
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ catestep>>> " +catestep);
		}
		
		return catestep;
	}
	
	/**
	 * [설명] 분류이미지
	 * @param category
	 * @return
	 */
	public HashMap<String, Object> getCategoryImg(String category) {
		int i = 0;
//		int j = 0;
		int intKey = 0;
		
		logger.debug("categoryImg category>> " + category);

		HashMap<String, Object> map = new HashMap<String, Object>();

		String[] arrTmp = null;
		String[] arrTmp1 = null;
//		String[] arrMap = null;
		String[] arrFileName = null;

		category = StringUtil.nullConv(category, "");

		// 카테고리 이미지 목록을 추출
		String[] fileList = new File(ConfigClass.value("WEB_DIR_PATH")
				+ "/resources/shop/data/category/").list();

		if (null != fileList) {
			for (i = 0; i < fileList.length; i++) {
				arrTmp = fileList[i].split("\\.");
				// 이미지 파일인지 확인한다.
				if ("JPG".equals(arrTmp[1].toUpperCase())
						|| "GIF".equals(arrTmp[1].toUpperCase())) {
					arrTmp1 = arrTmp[0].split("\\_");

					if ("basic".equals(arrTmp1[1])) {
						intKey = 0;
					} else if ("over".equals(arrTmp1[1])) {
						intKey = 1;
					}

					// 카테고리 분류관리에서 등록한 파일인지 확인한다.
					if ("basic".equals(arrTmp1[1]) || "over".equals(arrTmp1[1])) {
						if (null == map) {
							arrFileName = new String[] { "", "" };
							arrFileName[intKey] = fileList[i];
						} else {
							arrFileName = (String[]) map.get(category);
							logger.debug("arrFileName >> " + arrFileName);
							if (null == arrFileName) {
								arrFileName = new String[] { "", "" };
								arrFileName[intKey] = fileList[i];
								logger.debug("arrFile >> if"+arrFileName[intKey]+" :: "+intKey);
							} else {
								arrFileName[intKey] = fileList[i];
								logger.debug("arrFile >> else"+arrFileName[intKey]+" :: "+intKey);
							}
						}
						map.put(arrTmp1[1], arrFileName[intKey]);
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		}
		map.toString();
		return map;
	}
	
	public static String getCurrPosition(String category, String mode) throws Exception {
		return getCurrPosition(category, mode, ConfigClass.WEB_SKIN);
	}
	public static String getCurrPosition(String category, String mode, String skin) throws Exception {
		String result = "";

		if (category != null && !"".equals(category)) {
			if ("0" == mode)
				result += "전체분류 <span>></span> ";
			
			String[] tmp = new String[] { "", "", "", "" };
	
			try {
				int i = 0;
				int j = 0;
	
				for (i = 0; i < category.length(); i += 3) {
					tmp[j++] = category.substring(0, (i + 3));
				}
				
				if(logger.isDebugEnabled()){
					logger.debug(">> tmp[] :: "+tmp[0]+" :: "+tmp[1]+" :: "+tmp[2]);
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				List<Map<String, Object>> resultList = null;
			
				map.put("category", "'"+tmp[0]+ "', '" + tmp[1] + "', '"+ tmp[2] + "', '" + tmp[3]+"'");
				map.put("order", "category");
				
				ShopLibFncService shopLibFncService = null;
				shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
				
				resultList = shopLibFncService.getGoodsCategoryList(map);
				
				if (null != resultList) {
					for (i = 0; i < resultList.size(); i++) {
						String catnm = resultList.get(i).get("catnm").toString();
						if ("kr".equals(skin))
							catnm = resultList.get(i).get("catnmKR").toString();
						else if ("cn".equals(skin))
							catnm = resultList.get(i).get("catnmCN").toString();
						
						if ("0" == mode || "2" == mode)
							result += catnm;
						else
							result += "<a href='../goods/goods_list?category=" + resultList.get(i).get("category") + "'>" + catnm + "</a>";
						
						if ((i + 1) < resultList.size()) {
							result += " <span>></span> ";
						}
					}
				} else {
					result = "";
				}
	
			} catch (Exception e) {
				throw e;
			}
		}
		
		if(logger.isDebugEnabled()){
			logger.debug(">> strResult :: "+result);
		}
		
		return result;
	}
	

	/**
	 * [설명] 스텝별 주문 단계 가져오기
	 * 
	 * @param step
	 * @param step2
	 * @param ordno
	 * @param itemsno
	 * @return r_settlekind
	 */
	public static String getStepMsg(String step, String step2, String ordno, String itemsno) throws Exception {
		String stepMsg = "";
		int stepInt = Integer.parseInt(step);
		int stepInt2 = Integer.parseInt(step2);
		stepMsg = r_stepi(step, step2);
		//취소완료
		if (stepInt2 == 56) {
			if(stepInt == 0) {
				stepMsg = "입금대기⇒ " + stepMsg;
			}else if(stepInt == 1) {
				stepMsg = "결제완료⇒ " + stepMsg;
				
			}else if(stepInt == 10) {
				stepMsg = "재고확인중⇒ " + stepMsg;
				
			}else if(stepInt == 11) {
				stepMsg = "재고확인완료⇒ " + stepMsg;
				
			}else if(stepInt == 2) {
				stepMsg = "배송준비중⇒ " + stepMsg;
				
			}else if(stepInt == 12) {
				stepMsg = "입고완료⇒ " + stepMsg;
				
			}else if(stepInt == 3) {
				stepMsg = "배송중⇒ " + stepMsg;
				
			}else if(stepInt == 4) {
				stepMsg = "배송완료⇒ " + stepMsg;
			}
		}
		return stepMsg;
	}
	
	public static String r_stepi(String step, String step2) {
		String r_stepi = "";
		if ("0".equals(step)) {
			if ("51".equals(step2)) {
				r_stepi = "입금대기";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료 수집완료";
			}
		} else if ("1".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "결제완료";
			} else if ("10".equals(step2)) {
				r_stepi = "결제완료 수집완료";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료 수집완료";
			}
		} else if ("10".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "재고확인중";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료 수집완료";
			}
		} else if ("11".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "재고확인완료";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료 수집완료";
			}
		} else if ("2".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송준비중";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료 수집완료";
			}
		} else if ("12".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "입고완료";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료 수집완료";
			}
		} else if ("3".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송중";
			} else if ("79".equals(step2)) {
				r_stepi = "반품신청";
			}else if ("80".equals(step2)) {
				r_stepi = "반품회수중";
			}else if ("81".equals(step2)) {
				r_stepi = "반품검수중";
			}else if ("82".equals(step2)) {
				r_stepi = "반품(환불)확정";
			}else if ("83".equals(step2)) {
				r_stepi = "반품(환불)완료";
			}
		} else if ("4".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송완료";
			} else if ("79".equals(step2)) {
				r_stepi = "반품신청";
			}else if ("80".equals(step2)) {
				r_stepi = "반품회수중";
			}else if ("81".equals(step2)) {
				r_stepi = "반품검수중";
			}else if ("82".equals(step2)) {
				r_stepi = "반품(환불)확정";
			}else if ("83".equals(step2)) {
				r_stepi = "반품(환불)완료";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료 수집완료";
			}
		}
		return r_stepi;
	}
	
	/**
	 * 프론트 스텝별 주문 단계 가져오기
	 * @param step
	 * @param step2
	 * @return
	 */
	public static String front_r_stepi(String step, String step2) {
		String r_stepi = "";
		if ("0".equals(step)) {
			if ("51".equals(step2)) {
				r_stepi = "입금대기";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료";
			}
		} else if ("1".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "결제완료";
			} else if ("10".equals(step2)) {
				r_stepi = "결제완료 수집완료";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료";
			}
		} else if ("10".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "재고확인중";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료";
			}
		} else if ("11".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송준비중";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료";
			}
		} else if ("2".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송준비중";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료";
			}
		} else if ("12".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송중";
			} else if ("40".equals(step2)) {
				r_stepi = "취소요청";
			}else if ("56".equals(step2)) {
				r_stepi = "취소완료";
			}else if ("58".equals(step2)) {
				r_stepi = "취소완료";
			}
		} else if ("3".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송중";
			} else if ("79".equals(step2)) {
				r_stepi = "반품신청";
			}else if ("80".equals(step2)) {
				r_stepi = "반품회수중";
			}else if ("81".equals(step2)) {
				r_stepi = "반품검수중";
			}else if ("82".equals(step2)) {
				r_stepi = "반품(환불)확정";
			}else if ("83".equals(step2)) {
				r_stepi = "반품(환불)완료";
			}
		} else if ("4".equals(step)) {
			if ("0".equals(step2)) {
				r_stepi = "배송완료";
			} else if ("79".equals(step2)) {
				r_stepi = "반품신청";
			}else if ("80".equals(step2)) {
				r_stepi = "반품회수중";
			}else if ("81".equals(step2)) {
				r_stepi = "반품검수중";
			}else if ("82".equals(step2)) {
				r_stepi = "반품(환불)확정";
			}else if ("83".equals(step2)) {
				r_stepi = "반품(환불)완료";
			}
		}
		return r_stepi;
	}
	
	/**
	 * [설명] 결제수단 한글명
	 * 
	 * @param settlekind
	 * @return r_settlekind
	 */
	public static String r_settlekind(String settlekind) {
		String r_settlekind = "";

		/*
		 * "a"	=> "무통장",
		"c"	=> "신용카드",
		"o"	=> "계좌이체",
		"v"	=> "가상계좌",
		"d"	=> "전액할인",
		"h"	=> "핸드폰",
		"p"	=> "포인트",
		 */
		if ("".equals(settlekind)) {

		} else {
			if ("a".equals(settlekind)) {
				r_settlekind = "무통장";
			} else if ("c".equals(settlekind)) {
				r_settlekind = "신용카드";
			} else if ("o".equals(settlekind)) {
				r_settlekind = "계좌이체";
			} else if ("v".equals(settlekind)) {
				r_settlekind = "가상계좌";
			} else if ("d".equals(settlekind)) {
				r_settlekind = "전액할인";
			} else if ("h".equals(settlekind)) {
				r_settlekind = "핸드폰";
			} else if ("p".equals(settlekind)) {
				r_settlekind = "포인트";
			} else if ("z".equals(settlekind)) {
				r_settlekind = "ARS";
			} else {
				r_settlekind = "구분없음";
			}
		}

		return r_settlekind;
	}
	public static String getUniqueKey() {
		return "["+System.currentTimeMillis()+"]";
	}
	
	/**
	 * [설명] 스텝별 주문 단계 가져오기
	 * 
	 * @param step
	 * @param step2
	 * @param ordno
	 * @param itemsno
	 * @return r_settlekind
	 */
	public static LinkedHashMap<String, String> getArrStep() throws Exception {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("0", "입금대기");
		lhm.put("1", "결제완료");
		lhm.put("10", "재고확인중");
		lhm.put("11", "재고확인완료");
		lhm.put("2", "배송준비중");
		lhm.put("12", "입고완료");
		lhm.put("3", "배송중");
		lhm.put("4", "배송완료");

		return lhm;
	}
	
	/**
	 * [설명] 스텝별 주문 단계 가져오기
	 * 
	 * @param step
	 * @param step2
	 * @param ordno
	 * @param itemsno
	 * @return r_settlekind
	 */
	public static String r_istep(String step) {
		String rIstep = "";

		if ("0".equals(step)) {
			rIstep = "주문접수";
		} else if ("1".equals(step)) {
			rIstep = "입금확인";
		} else if ("2".equals(step)) {
			rIstep = "배송준비중";
		} else if ("3".equals(step)) {
			rIstep = "배송중";
		} else if ("4".equals(step)) {
			rIstep = "배송완료";
		} else if ("40".equals(step)) {
			rIstep = "취소요청";			
		} else if ("41".equals(step)) {
			rIstep = "취소접수";
		} else if ("42".equals(step)) {
			rIstep = "취소진행";
		} else if ("44".equals(step)) {
			rIstep = "취소완료";
		} else if ("50".equals(step)) {
			rIstep = "결제시도";
		} else if ("51".equals(step)) {
			rIstep = "입금대기";
		} else if ("54".equals(step)) {
			rIstep = "결제실패";
		} else if ("55".equals(step)) {
			rIstep = "수령완료";
		}else if ("56".equals(step)) {
			rIstep = "취소완료";
		} else if ("60".equals(step)) {
			rIstep = "교환신청";
		} else if ("61".equals(step)) {
			rIstep = "교환접수";
		} else if ("62".equals(step)) {
			rIstep = "교환완료";
		} else if ("70".equals(step)) {
			rIstep = "반품신청";
		} else if ("71".equals(step)) {
			rIstep = "반품접수";
		} else if ("72".equals(step)) {
			rIstep = "반품완료";
		} else if ("74".equals(step)) {
			rIstep = "환불완료";
		}
		return rIstep;
	}
	
/*	//whpark 20191108 
	public static String r_stepi_detail(String istep, String invoice, String deliveryCompNm, String deliveryStatus) {
		String rIstep = "";
		
		String statusNm = r_istep(istep);
		String sDelivery = "";
		
		rIstep=statusNm ;
		
		rIstep = r_istep(istep);
		//if 송장번호가 있으면 배송사 송장 번호
		if(invoice != "" && invoice != null) {
			int is = Integer.parseInt(istep);
			if (is < 40) {
				sDelivery = "<br>"+deliveryCompNm+"/"+invoice+"["+deliveryStatus+"]";
			}
			
			rIstep =rIstep + sDelivery;
			
		}
		
		return rIstep;
	}*/
		
	/**
	 * [설명]  일반 적립금
	 * 
	 * @param m_no
	 * @param emoney
	 * @param msg
	 * @param ordno
	 * @return void
	 */
	public static void setEmoney(int emoney, int mno, long ordno, String msg) throws SQLException, Exception {
		ShopLibFncService shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//2020_02_14 이현빈 비회원은 적립금 대상에서 제외 
		if(mno == 0){
			return ; 
		}
		paramMap.put("emoney", emoney);
		paramMap.put("mno", mno);
		paramMap.put("ordno", ordno);
		paramMap.put("msg", msg);
		
		shopLibFncService.setEmoney1(paramMap);
		shopLibFncService.setEmoney2(paramMap);
	}
	
	/**
	 * [설명]  상품구매 적립금
	 */
	public static void setGoodsEmoney(String ordno, int mode) throws SQLException, Exception {
		ShopLibFncService shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ordno", ordno);
		
		int rt_emoney = 0;
		int rt_m_no = 0;
		int rt_reserve = 0;
		int rt_gap = 0;
		int reserve = 0;
		String msg = "";
		
		GdOrder order = shopLibFncService.setGoodsEmoney1(paramMap);	// SELECT gd_order
		//비회원 주문시 적립금 제외
		if(order.getMno() == 0){
			return;
		}
		
		if (order != null) {
			rt_emoney = order.getEmoney();
			rt_m_no = order.getMno();
			rt_reserve = order.getReserve();
		}
		
		if ("n".equals(ShopConfig.getProperty("emoney_useyn"))) {
			return;
		}
		if ("1".equals(ShopConfig.getProperty("emoney_limit")) && 0 != rt_emoney) {
			return;
		}
		if ("".equals(rt_m_no)) {
			return;
		}
		// ### 취소상품 적립금 제외
		rt_gap = shopLibFncService.setGoodsEmoney2(paramMap);	// SELECT gd_order_item

		reserve = (rt_reserve - rt_gap) * mode;

		if (reserve == 0) {
			return;
		}

		msg = (mode > 0) ? "구매완료로 인해 구매적립금 적립" : "구매취소로 인해 구매적립금 환원";

		paramMap.put("reserve", reserve);
		paramMap.put("mno", rt_m_no);
		paramMap.put("msg", msg);
		
		shopLibFncService.setGoodsEmoney3(paramMap);
		shopLibFncService.setGoodsEmoney4(paramMap);
	}
	
	/**
	 * [설명]  상품구매 적립금
	 */
	public static void setGoodsCoupon(String ordno) throws SQLException, Exception {
		ShopLibFncService shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		int rt_m_no = 0;
		int[] arr_goodsno = null;
		String[] arrCategory = null;
		
		paramMap.put("ordno", ordno);
		rt_m_no = shopLibFncService.setGoodsCoupon1(paramMap);	// SELECT gd_order
		paramMap.put("mno", rt_m_no);
		
		if ( rt_m_no != 0 ) {
			// 해당주문의 상품번호에 해당되는 distinct한 상품링크정보가져오기
			arr_goodsno = shopLibFncService.setGoodsCoupon2(paramMap);	// SELECT gd_order_item
			paramMap.put("arrgoodsno", arr_goodsno);
			List<Map<String, Object>> linkList = shopLibFncService.setGoodsCoupon3(paramMap);	// SELECT gd_goods_link

			int rt_clen = 0;
			String rt_category = "";
			int ii = 0;
			arrCategory = new String[6];
			if (linkList != null) {
				for (int i = 0; i < linkList.size(); i++) {
					Map<String, Object> link = linkList.get(i);
					rt_category = String.valueOf(link.get("category"));
					rt_clen = Integer.parseInt(String.valueOf(link.get("clen")));
					for (int j = 3; j <= rt_clen; j += 3) {
						arrCategory[i++] = rt_category.substring(0, j);
					}
				}
			}
			paramMap.put("arrcategory", arr_goodsno);
			List<GdCoupon> couponList = shopLibFncService.setGoodsCoupon4(paramMap);	// SELECT gd_coupon
			if (couponList != null) {
				for (int i = 0; i < couponList.size(); i++) {
					GdCoupon coupon = couponList.get(i);
					paramMap.put("couponcd", coupon.getCouponcd());
					int cnt = shopLibFncService.setGoodsCoupon5(paramMap);	// SELECT gd_coupon_apply
					if (logger.isDebugEnabled()) {
						logger.debug(" ##### coupon : " + coupon.toString());
						logger.debug(" ##### cnt : " + cnt);
					}
					if (cnt != 0) {
						shopLibFncService.setGoodsCoupon6(paramMap);	// INSERT gd_coupon_apply
						shopLibFncService.setGoodsCoupon7(paramMap);	// INSERT gd_coupon_applymember
					}
				}
			}
		}
	}
	
	/**
	 * [설명]  구분자로 구분되어진 문자열에서 특정 문자열이 존재하는지 체크
	 * 
	 * @param m_no
	 * @param emoney
	 * @param msg
	 * @param ordno
	 * @return void
	 */

	public static boolean findInSet(String str, String strlist, String division)
			throws Exception {
		boolean findInSet = false;

		String[] tmp_arr = StringUtil.explode(strlist, division);

		if (tmp_arr != null) {
			for (int i = 0; i < tmp_arr.length; i++) {
				if (str.equals(tmp_arr[i])) {
					findInSet = true;
					break;
				}
			}
		}

		return findInSet;

	}
	
	/**
	 * 재고조정
	 */
	public static void setStock(long ordno) throws Exception {
		ShopLibFncService shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		String stepStock = ShopConfig.getProperty("stepStock");
		String xIstep = ("1".equals(stepStock)) ? "0,44,74" : "44,74"; 
		
		List<ShopLibSetStock1> stockList = shopLibFncService.setStock1(ordno);
		
		// stockList
		if(stockList != null && stockList.size() > 0) {
			for(ShopLibSetStock1 stock1 : stockList) {
				if (logger.isDebugEnabled()) {
					logger.debug( "#####[setStock] usestock: " + stock1.getUsestock() + " || stockyn : " + stock1.getStockyn() );
				}
				
				if("".equals(stock1.getUsestock())) {
					continue;
				}
				
				int mode = 0;
			 
				if("y".equals(stock1.getStockyn()) && findInSet(String.valueOf(stock1.getIstep()), xIstep, ",")) {
					mode = 1;
				} else if("n".equals(stock1.getStockyn()) && !(findInSet(String.valueOf(stock1.getIstep()), xIstep, ","))) {
			
					mode = -1;
				}
				if (logger.isDebugEnabled()) logger.debug("#####[setStock] mode : " + mode);
				
				if(mode == 0){
					continue;
				}
				
				int rtEa = stock1.getEa();
				int cStock = 0;
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("goodsno", stock1.getGoodsno());
				paramMap.put("opt1"	  , stock1.getOpt1());
				paramMap.put("opt2"	  , stock1.getOpt2());
				paramMap.put("sno"	  , stock1.getSno());

				GdGoodsOption goodsOption = shopLibFncService.setStock2(paramMap);
				
				if(goodsOption != null) {
					if(logger.isDebugEnabled()) logger.debug("####[setStock] stock update before : " + goodsOption.getStock());
					cStock = Integer.parseInt(goodsOption.getStock()) + (mode * rtEa);
					if(logger.isDebugEnabled()) logger.debug("####[setStock] stock update after  : " + cStock);
				}
				
				if(cStock < 0) {
					cStock = 0;
				}
				
				paramMap.put("cstock", cStock);
				shopLibFncService.setStock3(paramMap);
				
				if(mode != 0) {
					paramMap.put("stockyn", mode > 0 ? "n" : "y");
					shopLibFncService.setStock4(paramMap);
				}
			}
		}
	}
	
	public static void setPrnSettleprice(long ordno) throws SQLException, Exception {
		ShopLibFncService service = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", ordno);
		List<GdOrderItem> orderItemList = service.setPrnSettleprice1(ordno);	// SELECT gd_order_item
		
		int settleprice = 0;	//결제 수정금액
		int totalAddopt = 0;	//총 옵션가
		int price = 0;			//상품가격
		int ea = 0;				//개수
		int totalMemberdc = 0;	//총회원할인가
		int totalCoupon = 0;	//총쿠폰할인가
		
		if (orderItemList != null && orderItemList.size() > 0) {
			for(GdOrderItem item : orderItemList) {
				price = item.getPrice();
				ea = item.getEa();
				
				int istep = Integer.parseInt(String.valueOf(item.getIstep()));
				if (getNormalIstep(istep)) {
					settleprice += (price * ea);
					totalAddopt += ShopLibFunction.getTotalOptionPrice(StringUtil.nvl(item.getAddopt(), "0"));
				} else {
					//취소,환불상품은 금액계산에서 제외
					continue;
				}
			} 
		}
		settleprice = settleprice + totalAddopt;
		param.put("csettleprice", settleprice);
		param.put("memberdc", totalMemberdc);
		param.put("coupon", totalCoupon);
		
		//최종 구매금액 업데이트
		service.setPrnSettleprice3(param);	// UPDATE gd_order
		
		GdOrder order = service.setPrnSettleprice2(ordno);	// SELECT gd_order
		if(order != null) {
			int m_no = order.getMno();
			
			if (m_no > 0) { //회원일경우 회원 구매 금액 업데이트(배송완료건에 대해서만 조회함)
				param.put("mno", m_no);
				PrnSettlePrice4VO calcOrder = service.setPrnSettleprice4(m_no);	// SELECT gd_order
				if (null != calcOrder) {
					param.put("membersum", calcOrder.getMemberSum());
					param.put("membercnt", calcOrder.getMemberCnt());
					service.setPrnSettleprice5(param);	// UPDATE gd_member
				}
			}
		}
	}
	
	public static String wskin(String url) {
		return ConfigClass.getSkin(url);
	}
	
	public static String webSkin(String url) {
		return "/shop/data/skin/" + ConfigClass.getSkin(url);
	}
	
	public static String mskin(String url) {
		return ConfigClass.getSkin(url);
	}
	
	public static String mobSkin(String url) {
		return "/mshop/data/skin/" + ConfigClass.getSkin(url);
	}
	
	public static String goodsImgPath() {
		return ConfigClass.value("FRONT_GOODS_IMG_PATH");
	}
	
	
	/**
	 * [설명]  재주문
	 * 
	 * @param ordno
	 * @param cancel 
	 * @return long
	 */
	public static long reorder(long ordno, int cancel) throws Exception {
		
		long newOrderNo = 0;
		
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		PopupOrderService popupOrderService = null;
		popupOrderService = (PopupOrderService)ApplicationContextUtil.getApplicationContext().getBean("popupOrderService");
		
		
		try {
			
			Map<String, Object> orderObj = null;
			List<Map<String, Object>> itemList = null;
			//주문번호 생성
			newOrderNo = System.currentTimeMillis();
			//주문 정보 조회
			orderObj = shopLibFncService.getOrderInfo(ordno);
			//주문 상품 조회
			itemList = shopLibFncService.getOrderItemList(cancel);					
			
			Map<String, Object> param = new HashMap<String, Object>();
			List<Map<String,Object>> deliveryInfo = null;
			
			for(Map<String,Object> m : itemList){
				//cancel log 에 저장된 배송정보 조회
				deliveryInfo = shopLibFncService.getCancelLogDeliveryList(m);
				Map<String,Object> deliveryMap = new HashMap<String,Object>(); 
				for(Map<String,Object> map : deliveryInfo){
					//배송정보 세팅
					deliveryMap.put("ordno", newOrderNo);
					deliveryMap.put("goodsno", map.get("goodsno"));
					deliveryMap.put("paymentTerms", map.get("paymentTerms"));
					deliveryMap.put("deliveryPrice", map.get("deliveryPrice"));
					deliveryMap.put("addDeliveryPrice", map.get("addDeliveryPrice"));
					deliveryMap.put("opt", map.get("opt"));
					
					//gd_order_delivery에 새로운 주문번호와 같이 배송정보 입력
					shopLibFncService.insertReorderDelivery(deliveryMap);
				}
			}
			int i = 0;
			int reserve = 0;
			int goodsprice = 0;
			int memberdc = 0;
			int coupon = 0;
			int settleprice = 0;
			int addopt = 0;
			param.put("ordno", newOrderNo); //새 주문번호
			//주문상품 저장
			if(itemList != null){
				if(itemList.size() >0){
					for(i=0; i<itemList.size(); i++){
						param.put("goodsno", itemList.get(i).get("goodsno"));
						param.put("goodsnm", itemList.get(i).get("goodsnm"));
						param.put("goodsnmKR", itemList.get(i).get("goodsnmKR"));
						param.put("goodsnmCN", itemList.get(i).get("goodsnmCN"));
						param.put("opt1", itemList.get(i).get("opt1"));
						param.put("opt2", itemList.get(i).get("opt2"));
						param.put("addopt", itemList.get(i).get("addopt"));
						param.put("price", itemList.get(i).get("price"));
						param.put("supply", itemList.get(i).get("supply"));
						param.put("reserve", itemList.get(i).get("reserve"));
						param.put("coupon", itemList.get(i).get("coupon"));
						param.put("memberdc", itemList.get(i).get("memberdc"));
						param.put("ea", itemList.get(i).get("ea"));
						param.put("brandnm", itemList.get(i).get("brandnm"));
						logger.debug("ShopLibFunction reorder>>> shopLibFncService.insertReorderItem >> "+i);
						shopLibFncService.insertReorderItem(param);
						// 상품가격
						reserve += StringUtil.N2D(String.valueOf(itemList.get(i).get("reserve")))
								* StringUtil.N2D(String.valueOf(itemList.get(i).get("ea")));
						goodsprice += StringUtil.N2D(String.valueOf(itemList.get(i).get("price")))
								* StringUtil.N2D(String.valueOf(itemList.get(i).get("ea")));
						memberdc += StringUtil.N2D(String.valueOf(itemList.get(i).get("memberdc")))
								* StringUtil.N2D(String.valueOf(itemList.get(i).get("ea")));
						coupon += StringUtil.N2D(String.valueOf(itemList.get(i).get("coupon")))
								* StringUtil.N2D(String.valueOf(itemList.get(i).get("ea")));
						addopt += getTotalOptionPrice(String.valueOf(itemList.get(i).get("addopt")));
						
					}
				}
			}
			//배송 정보 조회
			Map<String,Object> deliverys = popupOrderService.getDeliveryPriceInfo(param);
			int delivery = StringUtil.N2I(String.valueOf(deliverys.get("delivery")));
			int addDelivery = StringUtil.N2I(String.valueOf(deliverys.get("addDelivery")));
			
			settleprice = (goodsprice + addopt + delivery + addDelivery) - (memberdc + coupon);

			param.put("ordno", newOrderNo);
			param.put("deli_title", orderObj.get("deli_title"));
			param.put("deli_type", orderObj.get("deli_type"));
			param.put("nameOrder", orderObj.get("nameOrder"));
			param.put("email", orderObj.get("email"));
			param.put("phoneOrder", orderObj.get("phoneOrder"));
			param.put("mobileOrder", orderObj.get("mobileOrder"));
			param.put("nameReceiver", orderObj.get("nameReceiver"));
			param.put("phoneReceiver", orderObj.get("phoneReceiver"));
			param.put("mobileReceiver", orderObj.get("mobileReceiver"));
			param.put("zipcode", orderObj.get("zipcode"));
			param.put("address", orderObj.get("address"));
			param.put("prn_settleprice", settleprice);
			param.put("goodsprice", orderObj.get("goodsprice"));
			param.put("coupon", coupon);
			param.put("emoney", 0);
			param.put("delivery", delivery);
			param.put("memberdc", orderObj.get("memberdc"));
			param.put("reserve", reserve);			
			param.put("addDelivery", addDelivery);
			param.put("bankSender", orderObj.get("bankSender"));
			param.put("m_no", orderObj.get("m_no"));
			param.put("ip", orderObj.get("ip"));
			param.put("referer", orderObj.get("referer"));
			param.put("memo", orderObj.get("memo"));
			param.put("bankAccount", orderObj.get("bankAccount"));
			param.put("oldordno", ordno);
			logger.debug("ShopLibFunction reorder>>> shopLibFncService.insertReorderInfo ");
			shopLibFncService.insertReorderInfo(param);

		} catch (Exception e) {

		} finally {
		}

		return newOrderNo;

	}
	
	/**
	 * 주문 스텝 처리
	 * @param ordno
	 * @param step
	 * @param stock
	 * @throws Exception
	 */
	public static void ctlStep(String ordno, String step) throws Exception {
		ShopLibFncService service = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		/** 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("ordno", ordno);
		param.put("step", step);
		if("0".equals(step)) {
			param.put("step2", "51"); //입금대기
			param.put("istep", "51"); //입금대기
		}else {
			param.put("step2", "0");
			param.put("istep", "0");
		}
		
		GdOrder order = service.ctlStep1(param);
		String rtList_cyn = order.getCyn(); // 결제여부
		String rtList_dyn = order.getDyn(); // 주문단계
		String rtList_confirm = StringUtil.N2S(order.getConfirm()); // 배송확인
		
		//입금대기
		if ("0".equals(step)) {
			// 배송확인, 배송확인완료일시 n처리 유무
			if (!"".equals(rtList_confirm)) {
				param.put("execconfirmn", true);
			}
			//UPDATE gd_order( 결제여부=n, 결제확인일=null, 주문단계=n, 배송일=null )
			service.ctlStep3(param); 
			//UPDATE gd_order_item( 결제여부=n, 주문단계=n )
			service.ctlStep4(param); 
			
		//결제완료 or 재고확인중 or 재고확인완료 or 배송준비중 or 입고완료
		} else if ("1".equals(step) || "10".equals(step) || "11".equals(step) || "2".equals(step) || "12".equals(step)  ) {
			//( 결제여부=y, 결제확인일=now() )
			if ("n".equals(rtList_cyn)) {
				//UPDATE gd_order( 결제여부=y, 결제확인일=now() )
				service.ctlStep5(param); 
				//UPDATE gd_order_item ( 결제여부=y )
				service.ctlStep6(param); 
			}
			//주문단계=n, 배송일=null
			if ("y".equals(rtList_dyn)) {
				//UPDATE gd_order( 주문단계=n, 배송일=null )
				service.ctlStep7(param); 
				//UPDATE gd_order_item( 주문단계=n )
				service.ctlStep8(param); 
			}
			
		//배송중
		} else if ("3".equals(step)) {
			// 배송확인, 배송확인완료일시 n처리 유무
			if (!"".equals(rtList_confirm)) {
				param.put("execconfirmn", true);
			}
			
			//( 결제여부=y, 결제확인일=now() )
			if ("n".equals(rtList_cyn)) {
				//UPDATE gd_order( 결제여부=y, 결제확인일=now() )
				service.ctlStep5(param); 
				//UPDATE gd_order_item ( 결제여부=y )
				service.ctlStep6(param); 
			}
			// 주문단계=y, 배송일=now()
			if ("n".equals(rtList_dyn)) {
				//UPDATE gd_order( 주문단계=y, 배송일=now() )
				service.ctlStep9(param); 
				//UPDATE gd_order_item( 주문단계=y )
				service.ctlStep10(param);
			}
		//배송완료
		} else if ("4".equals(step)) {
			// 배송확인, 배송확인완료일시 y처리 유무
			if ("".equals(rtList_confirm)) {
				param.put("execconfirmy", true); 
			}
			
			//( 결제여부=y, 결제확인일=now() )
			if ("n".equals(rtList_cyn)) {
				//UPDATE gd_order( 결제여부=y, 결제확인일=now() )
				service.ctlStep5(param); 
				//UPDATE gd_order_item ( 결제여부=y )
				service.ctlStep6(param); 
			}
			//주문단계=y, 배송일=now()
			if ("n".equals(rtList_dyn)) {
				//UPDATE gd_order( 주문단계=y, 배송일=now() )
				service.ctlStep9(param); 
				//UPDATE gd_order_item( 주문단계=y )
				service.ctlStep10(param);
			}
			
		}
		//UPDATE gd_order (step변경)
		service.ctlStep11(param);
		//UPDATE gd_order_item (istep 변경)
		service.ctlStep12(param);
	}
	
	/**
	 * 20191128 이현빈 판매사페이지 주문 스텝처리
	 * 주문 스텝 처리
	 * @param ordno
	 * @param step
	 * @param stock
	 * @throws Exception
	 */
	public static void ctlStep(String ordno, String step, String sellerCd) throws Exception {
		ShopLibFncService service = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		/** 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		
		//20191129 이현빈 판매사코드 goodsno 추가 주문스텝 개별 변경 
		param.put("sellerCd", sellerCd);
		param.put("ordno", ordno);
		String[] goodsno = service.detailGoods(param);
		param.put("goodsno", goodsno);
		
		String order_confirm = "";
		int rtList_step = 0;
		int rtList_deliveryno = 0;
		String rtList_deliverycode = "";
		String rtList_confirm = "";
		String rtList_cyn = "";
		String rtList_dyn = "";
		int rtList_coupon_emoney = 0;
		int rtList_m_no = 0;
		String addQr = "";

		boolean exec_cyn_y = false;
		boolean exec_dyn_n = false;
		boolean exec_dyn_y = false;
		boolean exec_confirm_n = false;
		boolean exec_confirm_y = false;
		
		
		GdOrder order = service.ctlStep1(param);
		if (order != null) {
			//rtList_step = order.getStep();
			rtList_step = 0;
			if("0".equals(step)) {
				rtList_step = 51; //입금대기
			}
			rtList_deliveryno = order.getDeliveryno();
			rtList_deliverycode = order.getDeliverycode();
			rtList_confirm = StringUtil.N2S(order.getConfirm());
			rtList_coupon_emoney = order.getCouponemoney();
			rtList_cyn = order.getCyn();
			rtList_dyn = order.getDyn();
			rtList_m_no = order.getMno();
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("##### rtList_step : " + rtList_step + " || step : " + step);
		}
		
		param.put("istep", rtList_step);		
		
		if (!step.equals(rtList_step)) {
			if ("".equals(order_confirm)) {
				order_confirm = "admin";
			}	
			if ("0".equals(step)) {
				service.ctlStep3(param);	// UPDATE gd_order
				service.ctlStep4(param);	// UPDATE gd_order_item
				if (!"".equals(rtList_confirm)) {
					exec_confirm_n = true;
				}
			} else if ("1".equals(step) || "2".equals(step)) {
				if ("n".equals(rtList_cyn)) {
					exec_cyn_y = true;
				}
				if ("y".equals(rtList_dyn)) {
					exec_dyn_n = true;
				}
				if (!"".equals(rtList_confirm)) {
					exec_confirm_n = true;
				}
			} else if ("3".equals(step)) {
				if ("n".equals(rtList_cyn)) {
					exec_cyn_y = true;
				}
				if ("n".equals(rtList_dyn)) {
					exec_dyn_y = true;
				}
				if (!"".equals(rtList_confirm)) {
					exec_confirm_n = true;
				}
			} else if ("4".equals(step)) {
				if ("n".equals(rtList_cyn)) {
					exec_cyn_y = true;
				}
				if ("n".equals(rtList_dyn)) {
					exec_dyn_y = true;
				}
				if ("".equals(rtList_confirm)) {
					exec_confirm_y = true;
				}

				// 주문 적립금 적립
				setGoodsEmoney(ordno, 1);
				if ( 0 != rtList_coupon_emoney ) {
					// 적립쿠폰 적립금 적립
					setEmoney(rtList_coupon_emoney, rtList_m_no, Long.parseLong(ordno), "쿠폰 적립금 적립");
				}
				// 구매완료 쿠폰 발급
				setGoodsCoupon(ordno);
			}
			
			//실행코드
			if (exec_cyn_y) {
				service.ctlStep5(param);	// UPDATE gd_order
				service.ctlStep6(param);	// UPDATE gd_order_item
			}
			if (exec_dyn_n) {
				service.ctlStep7(param);	// UPDATE gd_order
				service.ctlStep8(param);	// UPDATE gd_order_item
			}
			if (exec_dyn_y) {
				service.ctlStep10(param);	// UPDATE gd_order_item
				
				//20191203 이현빈 각판매사 주문단계가 동일할때 업데이트
				int dyn = service.checkDyn(ordno);
				if(dyn == 0){
					service.ctlStep9(param);	// UPDATE gd_order
				}
			}

			param.put("execconfirmn", false);
			param.put("execconfirmy", false);
			if (exec_confirm_y) {
				param.put("execconfirmy", true);
				param.put("orderconfirm", order_confirm);
			} else {
				if (exec_confirm_n) {
					param.put("execconfirmn", true);
				}
			}
			param.put("step", step);
			param.put("istep", rtList_step);
			service.ctlStep12(param);	// UPDATE gd_order_item
			service.ctlStep11(param);	// UPDATE gd_order
		}
		
	}
	
	
	/**
	 * 메인페이지 상품진열 설명(타이틀) 반환
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public static String getGoodsDisplayTitle(int i) throws Exception {
		String tmp = "";
		String result = "";
		String[] arrTmp = null;
		
		tmp = ShopConfig.getProperty("main_goods_display_0" + i);
		if (!"".equals(tmp)) {
			arrTmp = tmp.split("\\|");
		}
		
		result = arrTmp[1] != null ? arrTmp[1]  : "" ;
		
		return result;
	}
	
	public static List getGoodsType() throws Exception {
		Properties prop = null;
		List rst = new ArrayList();
		String tmp = "";
		String[] arrTmp = null;
		boolean b = true;

		for (int i = 1; i <= 5; i++) {
			tmp = ShopConfig.getProperty("main_goods_display_0" + i);
			if (!"".equals(tmp)) {
				arrTmp = tmp.split("\\|");
				if (null != arrTmp) {
					prop = new Properties();

					prop.put("chk", arrTmp[0]);
					prop.put("title", arrTmp[1]);
					prop.put("tpl", arrTmp[2]);
					prop.put("img", arrTmp[3]);
					prop.put("size", arrTmp[4]);
					prop.put("page_num", arrTmp[5]);
					prop.put("cols", arrTmp[6]);
				} else {
					b = false;
				}
			} else {
				b = false;
			}

			// 없거나 null일 경우 빈값을 채운 Properties를 생성한다.
			if (!b) {
				prop = new Properties();
				prop.put("chk", "");
				prop.put("title", "");
				prop.put("tpl", "");
				prop.put("img", "");
				prop.put("size", "");
				prop.put("page_num", "");
				prop.put("cols", "");
			}

			rst.add(prop);
		}

		return rst;
	}
	public static List getGoodsTypeMap() throws Exception {
		HashMap prop = null;
		List rst = new ArrayList();
		String tmp = "";
		String[] arrTmp = null;
		boolean b = true;

		for (int i = 1; i <= 5; i++) {
			tmp = ShopConfig.getProperty("main_goods_display_0" + i);
			if (!"".equals(tmp)) {
				arrTmp = tmp.split("\\|");
				if (null != arrTmp) {
					prop = new HashMap();

					prop.put("chk", arrTmp[0]);
					prop.put("title", arrTmp[1]);
					prop.put("tpl", arrTmp[2]);
					prop.put("img", arrTmp[3]);
					prop.put("size", arrTmp[4]);
					prop.put("page_num", arrTmp[5]);
					prop.put("cols", arrTmp[6]);
				} else {
					b = false;
				}
			} else {
				b = false;
			}

			// 없거나 null일 경우 빈값을 채운 Properties를 생성한다.
			if (!b) {
				prop = new HashMap();
				prop.put("chk", "");
				prop.put("title", "");
				prop.put("tpl", "");
				prop.put("img", "");
				prop.put("size", "");
				prop.put("page_num", "");
				prop.put("cols", "");
			}

			rst.add(prop);
		}

		return rst;
	}
	public static String getProperties(Properties prop, String key){
		return prop.getProperty(key);
	}
	/**
	 * [설명] 판매가에 dc가 적용된 금액을 반환
	 * @param price
	 * @param dc
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static int getDcprice(String price, String discount) throws Exception {
		return getDcprice(price, discount, 100);
	}

	public static int getDcprice(String price, String discount, String percent)
			throws Exception {
		return getDcprice(price, discount, StringUtil.N2I(percent));
	}

	public static int getDcprice(String price, String discount, int percent)
			throws Exception {
	
		int ret = 0;
		int amt = StringUtil.N2I(price);	
		int emoney_cut = Integer.parseInt(ShopConfig.getProperty("emoney_cut", "0"));	// 절삭자리수
		int result = 0;
//		if (0 < emoney_cut)
//			percent = emoney_cut;
		if (0 < emoney_cut) {
			percent = 1; // 3
			for(int i = 0; i < emoney_cut; i++) {
				percent *= 10;
			}
		}
		// 할인액 및 할인율이 없으면 할인금액 0원으로 설정
		if(discount.equals("") || discount.equals("%")){
			discount = "0";
		}
		// 할인이 '정율' 계산이면 입력받은 금액에서 정해진 정율 %만큼을 , 할인이 정액이라면 정해진 금액을 '할인금액' 으로 책정한다.
		ret = (-1 < discount.indexOf("%")) ? (int)(amt * (StringUtil.N2I(discount.replace("%", "")) / (float)100)) : StringUtil.N2I(discount);
		// 입력받은 금액에서 할인율을 먼저 뺀 후 절삭을 진행.
		//amt = amt - ((ret / percent) * percent);

		//amt = (amt - ret) / percent * percent ;	
		//result = (int) Math.round(amt);
		result = Math.round((amt - ret) * percent) /percent;
		return result;
	}
	
	
	/**
	 * [설명] 상품의 브랜드 명을 추출한다.
	 * @param strBrandCd
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static String getGoodsBrandName(int brandNo) throws Exception {
		String strResult = "";
		
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		strResult = shopLibFncService.getGoodsBrandName(brandNo);

		return strResult;
	}
	

	
	//************************************* 쇼핑몰 기본관리 
	// 배송료 기본정책 가져오기
	public static List<GdDeliveryPolicy> getDeliveryDefaultPolicy(String no) {
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		return shopLibFncService.getDeliveryDefaultPolicy(no);
		
	}
	public static List<GdDeliveryPolicy> getDeliveryDefaultPolicy() {
		return getDeliveryDefaultPolicy("");
	}
	
	/** 판매사가 등록한 상품이면 판매사의 기본배송정책 정보를, 아니면 관리자의 기본배송정책 정보를 반환합니다. */
	public static List<GdDeliveryPolicy> getDefaultDeliveryPolicy(String sellerCd) {
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		return StringUtil.N2B(sellerCd) ? 
				ShopLibFunction.getDeliveryDefaultPolicy() : 
				shopLibFncService.getSellerDeliveryPolicy(sellerCd);
	}
	
	/**
	 * [설명] 상품의 적립금을 반환한다.
	 * @param goodsno
	 * @return
	 * @throws Exception
	 */
	public static int getReserve(int goodsno) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ ShopLibFunction getReserve >> "+goodsno);
		}
		
		int reserve = 0;
		
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		Map<String, Object> rtMap = new HashMap<String,Object>();
		
		//적립금 지급여부 사용 여부(사용:y, 사용안함:n) 
		if("y".equals(ShopConfig.getProperty("emoney_useyn"))){
			//TODO 회원 등급에 따른 적립금 지급으로 수정필요.
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ shopLibFunction >> getReserve >> "+ reserve);
		}
		
		return reserve;
	}
	
	//******************************************** end
	
	
	
	
	
	
	//****************************************** 상품관리
		
	//******************************************** end
		
	
	
	
	
		
	//****************************************** 주문관리
		
	//******************************************** end
		
	
	
	
		
		
	//****************************************** 회원관리
	
	//******************************************** end
		
	
	
	
	
		
	//***************************************** 게시판관리
	
	//******************************************** end
		
		
		
	
	
	
	//************************************** 이벤트/쿠폰관리
	
	//상품 주문시 쿠폰발급


	public static void insertGoodsOrderCoupon(String mno)
			throws Exception {
		//2020_02_14 이현빈 비회원 상품구매시 쿠폰제공 제외
		if("0".equals(mno)){
			return;
		}
		ShopLibFncService shopLibFncService = (ShopLibFncService) ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		// 상품주문시 쿠폰조회
		List<GdCoupon> couponList = shopLibFncService.getCouponOrder();

		int couponCnt = 0;

		if (couponList.size() > 0) {
			for (GdCoupon coupon : couponList) {
				Map<String, Object> couponMap = new HashMap<String, Object>();
					couponMap.put("couponcd", coupon.getCouponcd());
					couponMap.put("m_no", mno);
					String newApplySno = "";
					Map<String, Object> tableMaxFieldMap = new HashMap<String, Object>();
					tableMaxFieldMap.put("tbl_name", "gd_coupon_apply");
					tableMaxFieldMap.put("col_name", "sno");
					int maxInt = shopLibFncService.tableMaxFieldSelect(tableMaxFieldMap);
					if (maxInt > 0) {
						newApplySno = (maxInt + 1) + "";
						couponMap.put("newapplysno", newApplySno);
						shopLibFncService.GoodsOrderCouponApplyInsert(couponMap);
						shopLibFncService.GoodsOrderCouponApplymemberInsert(couponMap);
					}
					couponCnt++;
			}
		}
	}
	// ******************************************** end

	// ************************************** 통계/데이터관리

	// ******************************************** end
	
	// 2017-08-24 : 프론트 상품상세 및 주문페이지 배송비 정보 출력부에 추가 배송비 정보를 출력해주기 위해 추가 (지은정)
	public static Map<String, String> getOverDeliveryInfo() {
		// 추가배송비-금액
		String deliveryOver = ShopConfig.getProperty("delivery_over");
		// 추가배송비-지역정보
		String deliveryOverZipcodeName = ShopConfig.getProperty("delivery_overZipcodeName");
		
		String[] deliveryOverArray = deliveryOver.split("\\|");
		String[] deliveryOverZipcodeNameArray = deliveryOverZipcodeName.split("\\|");
		
		Map<String, String> deliveryOverInfo = new HashMap<String, String>();
		
		for(int i = 0, size = deliveryOverArray.length; i < size; i++) {
			deliveryOverInfo.put(deliveryOverZipcodeNameArray[i], deliveryOverArray[i]);
		}
		
		return deliveryOverInfo;
	}
	
	public static Map<String, String> getOverDeliveryInfo(String sellerCd) {
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		return StringUtil.N2B(sellerCd) ? 
				ShopLibFunction.getOverDeliveryInfo() : 
				shopLibFncService.getSellerDeliveryOverPolicy(sellerCd);
	}
	
	/**2017-09-05 추가
	 * @param 1644^진화^이상해풀^2500|1647^크기^30cm^3000|1649^기술^덩쿨채찍^2000 형태의 옵션 파라미터가 전달됨.
	 * @return 파라미터 옵션 데이터 중 옵션 가격을 모두 합산하여 return 함.
	 * */
	public static int getTotalOptionPrice(String addOption) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> getTotalOptionPrice");
		}
		
		// 전달된 여러개의 옵션을 각 옵션별로 split. ex) 1644^진화^이상해풀^2500
		String [] addOptions = addOption.split("\\|");
		int addOptioinsLength = addOptions.length;
		
		int totalOptionPrice = 0;
		
		// 위에서 배열형태가 된 옵션리스트 만큼 for문을 돌며 옵션가를 합산함.
		// (옵션 정보 구분자인 ^ 기준으로 split 하여 옵션가가 들어있는 3번째 데이터를 모두 더한다.)
		for(int i = 0; i < addOptioinsLength; i++) {
			if(addOptions[i].contains("^")) {
				String [] addOptionDetails = addOptions[i].split("\\^");
				totalOptionPrice += StringUtil.N2D(addOptionDetails[3]);
			}
		}
		
		// 합산된 옵션가 return.
		return totalOptionPrice;
	}
	/**2020-02-18
	 * @param int istep 
	 * @return istep이 환불혹은 취소상태면 false 그외의 상태는 true를 return  
	 * */
	public static boolean getNormalIstep(int istep){
		boolean result = false;
		if(istep != 56){
			result = true;
		}
		return result;
	}
	

	//20200305 이현빈 관리자 메뉴 권한 체크 메소드 공통부분에 배치 
	public static void menuAuthPermissionCheck(HttpServletRequest req, HttpServletResponse res, String code) throws Exception {
		AdminSessionObject adminSO = AdminSessionObject.getSessionObject(req);
		PrintWriter out = null;
		if( adminSO.isLogin() && null != adminSO.getUserInfo() ){
			Map<String, Object> xkey = adminSO.getUserInfo().getXkey();
			String level = String.valueOf(xkey.get("level"));
			int permission = ShopConfig.getProperty("level"+level).indexOf(code);
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>level" + level);
			}
			// 로그인시 권한체크
			if( 0 > permission){
				/* ajax 일 경우 
				 * if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
					throw new BizException("member.00001", CommonConstants.MAIN_ADMIN_URL);	// 회원관리 권한이 없습니다.
				}*/
				try{
					res.setCharacterEncoding("UTF-8");
					res.setContentType("text/html;charset=UTF-8");
					out = res.getWriter();
					out.print(WebUtil.getAlertRedirect("메뉴관리 권한이 없습니다.", CommonConstants.MAIN_ADMIN_URL));
					out.flush();
				}catch(Exception e){
					throw e;
				} finally{
					if( null != out ){
						out.close();
					}
				}			
			}
		}
	}
	
	public static String getExchange(int price, String skin) {
		DecimalFormat formatter = new DecimalFormat("\u20AC#,##0.00");
		String exchange = formatter.format(price);
		
		if (skin.indexOf("kr") != -1) {
			price = price * StringUtil.N2I(ShopConfig.getProperty("exchange_kr"));
			formatter = new DecimalFormat("\u20A9#,##0");
			exchange = "" + formatter.format(Math.round(price / 10) * 10);
		} else if (skin.indexOf("cn") != -1) {
			price = price * StringUtil.N2I(ShopConfig.getProperty("exchange_cn"));
			formatter = new DecimalFormat("\uFFE5#,##0.00");
			exchange = formatter.format(Math.round(price * 100) / 100.0);
		}
		
		return exchange;
	}
	

	/**
	 * 배열에 값이 있는지 확인
	 * @param array
	 * @param str
	 * @return
	 */
	public static boolean isContains(String[] array, String str) {
		if (array == null || array.length == 0) {
			return false;
		}
		if (str == null || "".equals(str)) {
			return false;
		}
		
		return Arrays.asList(array).contains(str);
	}
}