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
import com.wepinit.wepinit_shop.xmall.admin.dao.log.StatMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.*;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatService {
	
	private static final Logger logger = LoggerFactory.getLogger(StatService.class);
	
	@Resource
	StatMapper mapper;
	
	/**
	 * 매출분석 > 매출통계
	 * 
	 * @param statSaleVO
	 * @return
	 * @throws Exception
	 */
	public List<StatSaleOutVO> getStatSaleList(StatSaleVO statSaleVO) throws Exception {
		List<StatSaleOutVO> list = new ArrayList<StatSaleOutVO>();
		
		List<StatSaleOutVO> list1 = mapper.getStatSaleList1(statSaleVO);
		List<StatSaleOutVO> list2 = mapper.getStatSaleList2(statSaleVO);
		List<StatSaleOutVO> list3 = mapper.getStatSaleList3(statSaleVO);
		StatSaleOutVO vo = null;
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>getStatSaleList Service");
		}
		
		int last = Integer.parseInt(DateUtil.getLastDay(statSaleVO.getYear()+statSaleVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new StatSaleOutVO();
			String day = statSaleVO.getYear()+"-"+statSaleVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setDt(statSaleVO.getYear()+statSaleVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list1.size(); r1++){
				if(day.equals(list1.get(r1).getDt())){
					vo.setRtList1_cnt( list1.get(r1).getCnt() );
					vo.setRtList1_sum_prn_settleprice( list1.get(r1).getSumPrnSettleprice() );
					break;
				}
			}
			
			for(int r2=0; r2 < list2.size(); r2++){
				if(day.equals(list2.get(r2).getDt())){
					vo.setRtList2_cnt( list2.get(r2).getCnt() );
					vo.setRtList2_sum_prn_settleprice( list2.get(r2).getSumPrnSettleprice() );
					vo.setRtList2_sum_supply( list2.get(r2).getSumSupply() );
					vo.setRtList2_net_sales( list2.get(r2).getSumPrnSettleprice() - list2.get(r2).getSumSupply() );
					
					break;
				}
			}
			
			for(int r3=0; r3 < list3.size(); r3++){
				if(day.equals(list3.get(r3).getDt())){
					vo.setRtList3_cnt( list3.get(r3).getCnt() );
					vo.setRtList3_sum_prn_settleprice( list3.get(r3).getSumPrnSettleprice() );
					
					break;
				}
			}
			
			list.add(vo);
		}
		
		return list;
		
	}
	
	/**
	 * 매출분석 > 결제수단분석
	 * 
	 * @param statSettlekindVO
	 * @return
	 * @throws Exception
	 */
	public List<StatSettlekindOutVO> getStatSettlekindList(StatSettlekindVO statSettlekindVO) throws Exception {
		
		List<StatSettlekindOutVO> resultlist = new ArrayList<StatSettlekindOutVO>();
		List<StatSettlekindOutVO> list = mapper.getStatSettlekindList(statSettlekindVO);
		StatSettlekindOutVO vo = null;
		
		int last = Integer.parseInt(DateUtil.getLastDay(statSettlekindVO.getYear()+statSettlekindVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new StatSettlekindOutVO();
			String day = statSettlekindVO.getYear()+"-"+statSettlekindVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setOdt(statSettlekindVO.getYear()+statSettlekindVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list.size(); r1++){
				if(day.equals(list.get(r1).getOdt())){
					//무통장
					if("a".equals(list.get(r1).getSettlekind())){
						vo.setRtList_a_cnt( list.get(r1).getCnt() );
						vo.setRtList_a_price( list.get(r1).getPrice() );
					//신용카드
					}else if("c".equals(list.get(r1).getSettlekind())){
						vo.setRtList_c_cnt( list.get(r1).getCnt() );
						vo.setRtList_c_price( list.get(r1).getPrice() );
					//계좌이체
					}else if("o".equals(list.get(r1).getSettlekind())){
						vo.setRtList_o_cnt( list.get(r1).getCnt() );
						vo.setRtList_o_price( list.get(r1).getPrice() );
					//가상계좌
					}else if("v".equals(list.get(r1).getSettlekind())){
						vo.setRtList_v_cnt( list.get(r1).getCnt() );
						vo.setRtList_v_price( list.get(r1).getPrice() );
					//핸드폰
					}else if("h".equals(list.get(r1).getSettlekind())){
						vo.setRtList_h_cnt( list.get(r1).getCnt() );
						vo.setRtList_h_price( list.get(r1).getPrice() );
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
	 * 매출분석 > 연령별매출분석
	 * 
	 * @param statAgeVO
	 * @return
	 * @throws Exception
	 */
	public List<StatAgeOutVO> getStatAgeList(StatAgeVO statAgeVO) throws Exception {
		
		List<StatAgeOutVO> resultlist = new ArrayList<StatAgeOutVO>();		
		List<StatAgeOutVO> list = mapper.getStatAgeList(statAgeVO);
		StatAgeOutVO vo = null;
		
		int last = Integer.parseInt(DateUtil.getLastDay(statAgeVO.getYear()+statAgeVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new StatAgeOutVO();
			String day = statAgeVO.getYear()+"-"+statAgeVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setOdt(statAgeVO.getYear()+statAgeVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list.size(); r1++){
				if(day.equals(list.get(r1).getOdt())){
					//10대
					if( list.get(r1).getAge() < 20 ){
						vo.setRtList_10_cnt( list.get(r1).getCnt() );
						vo.setRtList_10_price( list.get(r1).getPrice() );
					//20대
					}else if( list.get(r1).getAge() >= 20 && list.get(r1).getAge() < 30  ){
						vo.setRtList_20_cnt( list.get(r1).getCnt() );
						vo.setRtList_20_price( list.get(r1).getPrice() );
					//30대
					}else if( list.get(r1).getAge() >= 30 && list.get(r1).getAge() < 40  ){
						vo.setRtList_30_cnt( list.get(r1).getCnt() );
						vo.setRtList_30_price( list.get(r1).getPrice() );
					//40대
					}else if( list.get(r1).getAge() >= 40 && list.get(r1).getAge() < 50  ){
						vo.setRtList_40_cnt( list.get(r1).getCnt() );
						vo.setRtList_40_price( list.get(r1).getPrice() );
					//50대
					}else if( list.get(r1).getAge() >= 50 && list.get(r1).getAge() < 60  ){
						vo.setRtList_50_cnt( list.get(r1).getCnt() );
						vo.setRtList_50_price( list.get(r1).getPrice() );
					//60대
					}else if( list.get(r1).getAge() >= 60){
						vo.setRtList_60_cnt( list.get(r1).getCnt() );
						vo.setRtList_60_price( list.get(r1).getPrice() );
					}
					break;
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
	 * 매출분석 > 지역별매출분석
	 * 
	 * @param statAreaVO
	 * @return
	 * @throws Exception
	 */
	public List<StatAreaOutVO> getStatAreaList(StatAreaVO statAreaVO) throws Exception {
		
		List<StatAreaOutVO> resultlist = new ArrayList<StatAreaOutVO>();		
		List<StatAreaOutVO> list = mapper.getStatAreaList(statAreaVO);
		StatAreaOutVO vo = null;
		
		int last = Integer.parseInt(DateUtil.getLastDay(statAreaVO.getYear()+statAreaVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new StatAreaOutVO();
			String day = statAreaVO.getYear()+"-"+statAreaVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setOdt(statAreaVO.getYear()+statAreaVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list.size(); r1++){
				if(day.equals(list.get(r1).getOdt())){
					//서울
					if( list.get(r1).getZip() < 200 ){
						vo.setRtList_su_cnt( list.get(r1).getCnt() );
						vo.setRtList_su_price( list.get(r1).getPrice() );
					//충남
					}else if( list.get(r1).getZip() >= 200 && list.get(r1).getZip() < 358  ){
						vo.setRtList_cn_cnt( list.get(r1).getCnt() );
						vo.setRtList_cn_price( list.get(r1).getPrice() );
					//충북
					}else if( list.get(r1).getZip() >= 358 && list.get(r1).getZip() < 396  ){
						vo.setRtList_cb_cnt( list.get(r1).getCnt() );
						vo.setRtList_cb_price( list.get(r1).getPrice() );
					//경기
					}else if( list.get(r1).getZip() >= 396 && list.get(r1).getZip() < 488  ){
						vo.setRtList_gg_cnt( list.get(r1).getCnt() );
						vo.setRtList_gg_price( list.get(r1).getPrice() );
					//전남
					}else if( list.get(r1).getZip() >= 488 && list.get(r1).getZip() < 551  ){
						vo.setRtList_jn_cnt( list.get(r1).getCnt() );
						vo.setRtList_jn_price( list.get(r1).getPrice() );
					//전북
					}else if( list.get(r1).getZip() >= 551 && list.get(r1).getZip() < 600  ){
						vo.setRtList_jb_cnt( list.get(r1).getCnt() );
						vo.setRtList_jb_price( list.get(r1).getPrice() );
					//경남
					}else if( list.get(r1).getZip() >= 600 && list.get(r1).getZip() < 679  ){
						vo.setRtList_gn_cnt( list.get(r1).getCnt() );
						vo.setRtList_gn_price( list.get(r1).getPrice() );
					//제주
					}else if( list.get(r1).getZip() >= 679 && list.get(r1).getZip() < 698  ){
						vo.setRtList_jj_cnt( list.get(r1).getCnt() );
						vo.setRtList_jj_price( list.get(r1).getPrice() );
					//경북
					}else if( list.get(r1).getZip() >= 698 && list.get(r1).getZip() < 800  ){
						vo.setRtList_gb_cnt( list.get(r1).getCnt() );
						vo.setRtList_gb_price( list.get(r1).getPrice() );
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
	 * 매출분석 > 성별매출분석
	 * 
	 * @param statSexVO
	 * @return
	 * @throws Exception
	 */
	public List<StatSexOutVO> getStatSexList(StatSexVO statSexVO) throws Exception {
		
		List<StatSexOutVO> resultlist = new ArrayList<StatSexOutVO>();
		List<StatSexOutVO> list = mapper.getStatSexList(statSexVO);
		StatSexOutVO vo = null;
		
		int last = Integer.parseInt(DateUtil.getLastDay(statSexVO.getYear()+statSexVO.getMonth()));
		
		for(int i=1; i<=last;i++){
			vo = new StatSexOutVO();
			String day = statSexVO.getYear()+"-"+statSexVO.getMonth()+"-"+StringUtil.lpad(i,2,'0');
			
			vo.setOdt(statSexVO.getYear()+statSexVO.getMonth()+StringUtil.lpad(i,2,'0'));
			vo.setDay(day);
			
			for(int r1=0; r1 < list.size(); r1++){
				if(day.equals(list.get(r1).getOdt())){
					//입금확인
					if( list.get(r1).getStep() == 1){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_m1_cnt( list.get(r1).getCnt() );
							vo.setRtList_m1_price( list.get(r1).getPrice() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_w1_cnt( list.get(r1).getCnt() );
							vo.setRtList_w1_price( list.get(r1).getPrice() );
						}
					//배송준비
					}else if( list.get(r1).getStep() == 2){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_m2_cnt( list.get(r1).getCnt() );
							vo.setRtList_m2_price( list.get(r1).getPrice() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_w2_cnt( list.get(r1).getCnt() );
							vo.setRtList_w2_price( list.get(r1).getPrice() );
						}
					//배송중
					}else if( list.get(r1).getStep() == 3){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_m3_cnt( list.get(r1).getCnt() );
							vo.setRtList_m3_price( list.get(r1).getPrice() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_w3_cnt( list.get(r1).getCnt() );
							vo.setRtList_w3_price( list.get(r1).getPrice() );
						}
					//배송완료
					}else if( list.get(r1).getStep() == 4){
						if( "m".equals(list.get(r1).getSex()) ){
							vo.setRtList_m4_cnt( list.get(r1).getCnt() );
							vo.setRtList_m4_price( list.get(r1).getPrice() );
						}else if( "f".equals(list.get(r1).getSex()) ){
							vo.setRtList_w4_cnt( list.get(r1).getCnt() );
							vo.setRtList_w4_price( list.get(r1).getPrice() );
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
