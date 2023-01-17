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
package com.wepinit.wepinit_shop.xmall.admin.service.acount;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.acount.AcountMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.acount.AcountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AcountService {

	private static final Logger logger = LoggerFactory.getLogger(AcountService.class);
	
	@Resource
	AcountMapper mapper;
	
	public int getAcountListTotalCount(){
		return mapper.getAcountListTotalCount();
	}
	
	public int getAcountListCount(AcountVO acountVO){
		return mapper.getAcountListCount(acountVO);
	}
	
	
	public List<AcountVO> getAcountService(AcountVO acountVO){
		List<AcountVO> list = mapper.getAcountInfo(acountVO);
		int applyfees = 0;
		int salePrice = 0;
		int fees = 0;
		int coupon =0;
		int price =0;
		int deliveryPrice = 0;
		int addDeliveryPrice = 0;
		int ea = 0;
		String [] addopt1 ;
		String [] addopt2 ;
		int optPrice = 0;
		int accountPrice = 0;
		String accountFlag = "";

		for(AcountVO vo : list){
			fees = vo.getFees();
			coupon = vo.getCoupon();
			price = vo.getPrice();
			deliveryPrice = vo.getDeliveryPrice();
			addDeliveryPrice = vo.getAddDeliveryPrice();
			ea = vo.getEa();
			accountFlag = vo.getAccountFlag();
			
			accountFlag = vo.getAccountFlag();
			
			switch(accountFlag){
				case "I" :
					accountFlag = "정산중";
					break;
				case "W" :
					accountFlag = "정산대기";
					break;
				case "E" :
					accountFlag = "정산완료";
					break;
			}
			
			addopt1 = vo.getAddopt().split("\\|");
			for(String idx: addopt1){
				addopt2 = idx.split("\\^");
				if(addopt2.length == 4){
					optPrice = optPrice + StringUtil.N2I(addopt2[3]);
				}
			}
			//수수료 계산
			applyfees = (int) (((price*ea)+optPrice) * (fees*0.01));
			//판매금액
			salePrice = (price*ea) + optPrice + deliveryPrice + addDeliveryPrice;
			//정산금액
			accountPrice = salePrice - (applyfees+coupon);
			
			vo.setApplyfees(applyfees);
			vo.setSalePrice(salePrice);
			vo.setAccountPrice(accountPrice);
			vo.setAccountFlag(accountFlag);
			
		}
		
		return list;
	}
	
	public void updateFlag(Map<String, Object> paramsDB){
		String [] goodsnoArr = (String[]) paramsDB.get("acountArr_goodsno");
		String [] ordnoArr = (String[]) paramsDB.get("acountArr_ordno");
		String acountFlag = (String) paramsDB.get("acountFlag");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("acountFlag", acountFlag);
		
		for(int i=0; i<goodsnoArr.length; i=i+1){
			
			map.put("goodsno", goodsnoArr[i]);
			map.put("ordno", ordnoArr[i]);
			mapper.updateFlag(map);
		}
	}
	
	public int[] getAcountListData(AcountVO acountVO){
		List<AcountVO> list = mapper.getAcountListData(acountVO);
		int applyfees = 0;
		int salePrice = 0;
		int fees = 0;
		int coupon =0;
		int price =0;
		int deliveryPrice = 0;
		int addDeliveryPrice = 0;
		int ea = 0;
		String [] addopt1 ;
		String [] addopt2 ;
		int optPrice = 0;
		int accountPrice = 0;
		
		int totalSalePrice = 0;
		int totalFees = 0;
		int totalDeliveryPrice = 0;
		int totalAddDeliveryPrice = 0;
		int totalCoupon = 0;
		int totalAccountPrice = 0;
		
		for(AcountVO vo : list){
			fees = vo.getFees();
			coupon = vo.getCoupon();
			price = vo.getPrice();
			deliveryPrice = vo.getDeliveryPrice();
			addDeliveryPrice = vo.getAddDeliveryPrice();
			ea = vo.getEa();

			addopt1 = vo.getAddopt().split("\\|");
			for(String idx: addopt1){
				addopt2 = idx.split("\\^");
				if(addopt2.length == 4){
					optPrice = optPrice + StringUtil.N2I(addopt2[3]);
				}
			}
			//수수료 계산
			applyfees = (int) (((price*ea)+optPrice) * (fees*0.01));
			//판매금액
			salePrice = (price*ea) + optPrice + deliveryPrice + addDeliveryPrice;
			//정산금액
			accountPrice = salePrice - (applyfees+coupon);
			
			
			totalSalePrice = totalSalePrice + salePrice;
			totalFees = totalFees + applyfees ;
			totalDeliveryPrice = totalDeliveryPrice + vo.getDeliveryPrice(); 
			totalAddDeliveryPrice = totalAddDeliveryPrice + vo.getAddDeliveryPrice();
			totalCoupon = totalCoupon + vo.getCoupon(); 
			totalAccountPrice = totalAccountPrice + accountPrice;
		}
		
		int total[] = new int[6];
		total[0] = totalSalePrice;
		total[1] = totalFees;
		total[2] = totalDeliveryPrice;
		total[3] = totalAddDeliveryPrice;
		total[4] = totalCoupon;
		total[5] = totalAccountPrice;
		
		return total;
	}
	

}
