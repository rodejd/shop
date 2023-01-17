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
package com.wepinit.wepinit_shop.xmall.admin.service.log;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.log.MemMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemAgeVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemAreaVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemNewVO;
import com.wepinit.wepinit_shop.xmall.common.vo.MemAgeOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.MemAreaOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.MemNewOutVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemService.class);
	
	@Resource
	MemMapper mapper;
	
	
	/**
	 * 회원분석 > 신규회원분석
	 * 
	 * @param memNewVO
	 * @return
	 * @throws Exception
	 */
	public List<MemNewOutVO> getMemNewList(MemNewVO memNewVO) throws Exception {
		List<MemNewOutVO> resultlist = new ArrayList<MemNewOutVO>();
		List<MemNewOutVO> list = mapper.getMemNewList(memNewVO);
		MemNewOutVO vo = null;
		
		int last = Integer.parseInt(DateUtil.getLastDay(memNewVO.getYear()+memNewVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new MemNewOutVO();
			String day = memNewVO.getYear()+"-"+memNewVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setRdt(memNewVO.getYear()+memNewVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list.size(); r1++){
				if(day.equals(list.get(r1).getRdt())){
					//남성
					if( "m".equals(list.get(r1).getSex()) ){
						vo.setM_cnt( list.get(r1).getCnt() );
						vo.setM_login( list.get(r1).getLogin() );
						vo.setM_sale_cnt( list.get(r1).getSaleCnt() );
					//여성
					}else if( "f".equals(list.get(r1).getSex()) ){
						vo.setW_cnt( list.get(r1).getCnt() );
						vo.setW_login( list.get(r1).getLogin() );
						vo.setW_sale_cnt( list.get(r1).getSaleCnt() );
					}
				}
			}
			
			resultlist.add(vo);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>resultlist" + resultlist);
		}
		
		return resultlist;
	}
	
	/**
	 * 회원분석 > 연령별매출분석
	 * 
	 * @param statAgeVO
	 * @return
	 * @throws Exception
	 */
	public List<MemAgeOutVO> getMemAgeList(MemAgeVO memAgeVO) throws Exception {
		
		List<MemAgeOutVO> resultlist = new ArrayList<MemAgeOutVO>();		
		List<MemAgeOutVO> list = mapper.getMemAgeList(memAgeVO);
		MemAgeOutVO vo = null;
		
		int last = Integer.parseInt(DateUtil.getLastDay(memAgeVO.getYear()+memAgeVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new MemAgeOutVO();
			String day = memAgeVO.getYear()+"-"+memAgeVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setRdt(memAgeVO.getYear()+memAgeVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list.size(); r1++){
				if(day.equals(list.get(r1).getRdt())){
					//10대
					if( list.get(r1).getAge() < 20 ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_10_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_10_w_cnt( list.get(r1).getCnt() );
						}
					//20대
					}else if( list.get(r1).getAge() >= 20 && list.get(r1).getAge() < 30  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_20_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_20_w_cnt( list.get(r1).getCnt() );
						}
					//30대
					}else if( list.get(r1).getAge() >= 30 && list.get(r1).getAge() < 40  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_30_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_30_w_cnt( list.get(r1).getCnt() );
						}
					//40대
					}else if( list.get(r1).getAge() >= 40 && list.get(r1).getAge() < 50  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_40_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_40_w_cnt( list.get(r1).getCnt() );
						}
					//50대
					}else if( list.get(r1).getAge() >= 50 && list.get(r1).getAge() < 60  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_50_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_50_w_cnt( list.get(r1).getCnt() );
						}
					//60대
					}else if( list.get(r1).getAge() >= 60){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_60_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_60_w_cnt( list.get(r1).getCnt() );
						}
					}
				}
			}
			
			resultlist.add(vo);
		}
		
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>resultlist" + resultlist);
		}
		
		return resultlist;
	}
	
	/**
	 * 
	 * 회원분석 > 지역별회원분석
	 * 
	 * @param statAreaVO
	 * @return
	 * @throws Exception
	 */
	public List<MemAreaOutVO> getMemAreaList(MemAreaVO memAreaVO) throws Exception {
		
		List<MemAreaOutVO> resultlist = new ArrayList<MemAreaOutVO>();		
		List<MemAreaOutVO> list = mapper.getMemAreaList(memAreaVO);
		MemAreaOutVO vo = null;
		
		int last = Integer.parseInt(DateUtil.getLastDay(memAreaVO.getYear()+memAreaVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new MemAreaOutVO();
			String day = memAreaVO.getYear()+"-"+memAreaVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setRdt(memAreaVO.getYear()+memAreaVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list.size(); r1++){
				if(day.equals(list.get(r1).getRdt())){
					//서울
					if( list.get(r1).getZip() <= 9 ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_su_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_su_w_cnt( list.get(r1).getCnt() );
						}
					//충남
					}else if( list.get(r1).getZip() >= 30 && list.get(r1).getZip() <= 35  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_cn_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_cn_w_cnt( list.get(r1).getCnt() );
						}
					//충북
					}else if( list.get(r1).getZip() >= 27 && list.get(r1).getZip() <= 29  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_cb_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_cb_w_cnt( list.get(r1).getCnt() );
						}
					//경기
					}else if( list.get(r1).getZip() >= 10 && list.get(r1).getZip() <= 23  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_gg_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_gg_w_cnt( list.get(r1).getCnt() );
						}
					//전남
					}else if( list.get(r1).getZip() >= 57 && list.get(r1).getZip() <= 62  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_jn_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_jn_w_cnt( list.get(r1).getCnt() );
						}
					//전북
					}else if( list.get(r1).getZip() >= 54 && list.get(r1).getZip() <= 56  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_gg_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_gg_w_cnt( list.get(r1).getCnt() );
						}
					//경남
					}else if( list.get(r1).getZip() >= 44 && list.get(r1).getZip() <= 53  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_gn_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_gn_w_cnt( list.get(r1).getCnt() );
						}
					//제주
					}else if( list.get(r1).getZip() >= 63 && list.get(r1).getZip() <= 63  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_jj_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_jj_w_cnt( list.get(r1).getCnt() );
						}
					//경북
					}else if( list.get(r1).getZip() >= 36 && list.get(r1).getZip() <= 43  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_gb_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_gb_w_cnt( list.get(r1).getCnt() );
						}
					//강원
					}else if( list.get(r1).getZip() >= 24 && list.get(r1).getZip() <= 26  ){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_gw_m_cnt( list.get(r1).getCnt() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_gw_w_cnt( list.get(r1).getCnt() );
						}
					}
				}
			}
			
			resultlist.add(vo);
		}
		
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>resultlist" + resultlist);
		}
		
		return resultlist;
	}
}
