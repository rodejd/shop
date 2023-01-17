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

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.PgVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class PgController {
	
	private static final Logger logger = LoggerFactory.getLogger(PgController.class);
	
	
	// 통합전자결제설정 main page
	@RequestMapping(value="pg")
	public String pg(@ModelAttribute("pgVO") PgVO pgVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("PgController.pg::::::::::::::::::::::::::::::::::::::::::");
			logger.debug("PgController.pg::::::::::::::::::::::::::::::::::::::::::"+pgVO);
		}
		pgVO.setSettlePg(ShopConfig.getProperty("settlePg"));
		return "/shop/admin/basic/pg";
	}
	// 통합전자결제설정 상세
	@RequestMapping(value="pg/pgSelect")
	public String pgSelect(@ModelAttribute("pgVO") PgVO pgVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("PgController.pgSelect::::::::::::::::::::::::::::::::::::::::::");
			logger.debug("PgController.pgSelect::::::::::::::::::::::::::::::::::::::::::"+pgVO);
		}
		String pg_id = ShopConfig.getProperty("pg_id");
		String settlePg = ShopConfig.getProperty("settlePg");
		String target = "";
		if(pgVO.getTarget().equals("")){
			target=settlePg;
		}else{
			target=pgVO.getTarget();
		}
		logger.debug("PgController.pgSelect::::::::::::::::::::::::::::::::::::::::::pgVO.getTarget()>"+pgVO.getTarget());
		logger.debug("PgController.pgSelect::::::::::::::::::::::::::::::::::::::::::target>"+target);
		String[] setUses = null;
		String[] escrows = null;
		String spot = "";
		pgVO.setSpot(spot);
		
		logger.debug("PgController.pgSelect::::::::::::::::::::::::::::::::::::::::::settlePg>"+settlePg);
		if("inicis".equals(settlePg)){
			if(!"".equals(pg_id)){
				spot = "<b style='color:#ff0000;padding-left:10px'><img src=/resources/shop/admin/img/btn_on_func.gif align=absmiddle></b>";
			}
		}
		if("dacom".equals(settlePg)){
			spot = "<b style='color:#ff0000;padding-left:10px'>[사용중]</b>";
		}		
		if("kcp".equals(settlePg)){
			spot = "<b style='color:#ff0000;padding-left:10px'>[사용중]</b>";
		}		
		if("allat".equals(settlePg)){
			spot = "<b style='color:#ff0000;padding-left:10px'><img src=../img/btn_on_func.gif align=absmiddle></b>";
		}
		if(settlePg.equals(target)){
			pgVO.setSettlePg(settlePg);
			pgVO.setPg_id(pg_id);
			pgVO.setPg_quota(ShopConfig.getProperty("pg_quota"));
			pgVO.setPg_zerofee(ShopConfig.getProperty("pg_zerofee"));
			pgVO.setEscrow_use(ShopConfig.getProperty("escrow_use"));
			pgVO.setEscrow_min(ShopConfig.getProperty("escrow_min"));
			pgVO.setPg_receipt(ShopConfig.getProperty("pg_receipt"));
			pgVO.setCfg_display_egg(ShopConfig.getProperty("cfg[displayEgg]"));
			//inicis,dacom,kcp
			pgVO.setPg_zerofee_period(ShopConfig.getProperty("pg_zerofee_period"));
			//inicis
			pgVO.setEscrow_id(ShopConfig.getProperty("escrow_id"));
			//dacom 
			pgVO.setPg_mertkey(ShopConfig.getProperty("pg_mertkey"));
			//kcp
			pgVO.setPg_key(ShopConfig.getProperty("pg_key"));
			//allat
			pgVO.setPg_formkey(ShopConfig.getProperty("pg_formkey"));
			pgVO.setPg_crosskey(ShopConfig.getProperty("pg_crosskey"));
			pgVO.setPg_cert(ShopConfig.getProperty("pg_cert"));
			pgVO.setPg_bonus(ShopConfig.getProperty("pg_bonus"));
			
			String setUse = ShopConfig.getProperty("set_use");
			String escrow = ShopConfig.getProperty("escrow");
			
			if(null != setUse){
				setUses = StringUtil.split(setUse, ",");
			}
			if(null != escrow){
				escrows = StringUtil.split(escrow, ",");
			}
			pgVO.setSpot(spot);	
		}
		
		pgVO.setSet_use(setUses);
		pgVO.setEscrow(escrows);
		return "/shop/admin/basic/"+target;
	}	
	// 통합전자결제설정 intro
	@RequestMapping(value="pg/intro")
	public String intro(@ModelAttribute("pgVO") PgVO pgVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("PgController.intro::::::::::::::::::::::::::::::::::::::::::");
		}
		return "/shop/admin/basic/pg.intro";
	}
	// 통합전자결제설정 popUp
	@RequestMapping(value="pg/popUp")
	public String popUp(@ModelAttribute("pgVO") PgVO pgVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("PgController.popUp::::::::::::::::::::::::::::::::::::::::::");
		}
		return "/shop/admin/basic/popup."+pgVO.getTarget();
	}
	// 통합전자결제설정 update
	@RequestMapping(value="pg/indb")
	public String indb(@ModelAttribute("pgVO") PgVO pgVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::");
		}
		String settlePg = pgVO.getSettlePg();
		logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::settlePg_ST>>"+settlePg);
		if(!"".equals(settlePg)){
			ShopConfig.setProperty("settlePg",  pgVO.getSettlePg());
			ShopConfig.setProperty("pg_id", pgVO.getPg_id());
			ShopConfig.setProperty("pg_quota", pgVO.getPg_quota());
			ShopConfig.setProperty("pg_zerofee", pgVO.getPg_zerofee());
			ShopConfig.setProperty("escrow_use", pgVO.getEscrow_use());
			ShopConfig.setProperty("escrow_min", pgVO.getEscrow_min());
			ShopConfig.setProperty("pg_receipt", pgVO.getPg_receipt());
			ShopConfig.setProperty("cfg_display_egg", pgVO.getCfg_display_egg());
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_id>>"+pgVO.getPg_id());
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_quota>>"+pgVO.getPg_quota());
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_zerofee>>"+pgVO.getPg_zerofee());
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::escrow_use>>"+pgVO.getEscrow_use());
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::escrow_min>>"+pgVO.getEscrow_min());
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_receipt>>"+pgVO.getPg_receipt());
			logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::cfg_display_egg>>"+pgVO.getCfg_display_egg());
			if(pgVO.getSet_use() != null){
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::set_use>>"+pgVO.getSet_use().length);
				String set_use_tmp = "";
				int set_use_length = pgVO.getSet_use().length;
				for(int i=0;i<set_use_length;i++){
					if(i==0){
						set_use_tmp += pgVO.getSet_use()[i];	
					}else{
						set_use_tmp += ","+pgVO.getSet_use()[i];
					}
					
				}
				ShopConfig.setProperty("set_use",set_use_tmp);
			}
			if(pgVO.getEscrow() != null){
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::escrow>>"+pgVO.getEscrow().length);
				String escrow_tmp = "";
				int escrow_length = pgVO.getEscrow().length;
				for(int i=0;i<escrow_length;i++){
					if(i==0){
						escrow_tmp += pgVO.getEscrow()[i];	
					}else{
						escrow_tmp += ","+pgVO.getEscrow()[i];
					}
					
				}
				ShopConfig.setProperty("set_use",escrow_tmp);
			}
			if("inicis".equals(settlePg)){
				//inicis
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::escrow_id>>"+pgVO.getEscrow_id());
				ShopConfig.setProperty("escrow_id", pgVO.getEscrow_id());
			}else if("dacom".equals(settlePg)){
				//dacom 
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_mertkey>>"+pgVO.getPg_mertkey());
				ShopConfig.setProperty("pg_mertkey", pgVO.getPg_mertkey());
			}else if("kcp".equals(settlePg)){
				//kcp
				logger.debug("pg_key>>"+pgVO.getPg_key());
				ShopConfig.setProperty("pg_key", pgVO.getPg_key());
			}else if("allat".equals(settlePg)){
				//allat
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_formkey>>"+pgVO.getPg_formkey());
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_crosskey>>"+pgVO.getPg_crosskey());
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_cert>>"+pgVO.getPg_cert());
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_bonus>>"+pgVO.getPg_bonus());
				ShopConfig.setProperty("pg_formkey", pgVO.getPg_formkey());
				ShopConfig.setProperty("pg_crosskey", pgVO.getPg_crosskey());
				ShopConfig.setProperty("pg_cert", pgVO.getPg_cert());
				ShopConfig.setProperty("pg_bonus", pgVO.getPg_bonus());
			}	
			if("inicis".equals(settlePg) && "dacom".equals(settlePg) && "kcp".equals(settlePg) ){
				//inicis,dacom,kcp
				logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::pg_zerofee_period>>"+pgVO.getPg_zerofee_period());
				ShopConfig.setProperty("pg_zerofee_period", pgVO.getPg_zerofee_period());
			}
		}
		logger.debug("PgController.indb::::::::::::::::::::::::::::::::::::::::::settlePg_ED>>"+settlePg);
		return "redirect:/shop/admin/basic/pg/pgSelect?target="+settlePg;
	}
}