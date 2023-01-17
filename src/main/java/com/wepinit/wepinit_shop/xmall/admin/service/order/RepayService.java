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
package com.wepinit.wepinit_shop.xmall.admin.service.order;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.controller.order.RepayController;
import com.wepinit.wepinit_shop.xmall.admin.dao.order.OrderMapper;
import com.wepinit.wepinit_shop.xmall.admin.dao.order.RepayMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.RepayVO;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.util.ApplicationContextUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrderItem;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrdercancelOrderitemMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderitemOrdercancel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepayService {
	
	private static final Logger logger = LoggerFactory.getLogger(RepayController.class);
	
	@Resource
	RepayMapper mapper;
	
	@Resource
	OrderMapper orderMapper;
	
	@Resource
	PopupOrderService poService;
	
	@Resource
	OrderService oService;
	
	public void repayEmoneyPrice(Map<String,Object> param){
		mapper.repayEmoneyPrice(param);
	}
	public void repayReservePrice(Map<String,Object> param){
		mapper.repayReservePrice(param);
	}
	
	public void couponYnStatus1(Map<String,Object> param){
		this.mapper.couponYnStatus1(param);
	}

	//2020-01-17 이현빈 사용쿠폰정보 가져오기
	public String [] getCouponApplySno(Map<String,Object> param){
		return mapper.getCouponApplySno(param);
	}
	
	//2020-01-21 이현빈 전체쿠폰 가져오기
	public String[] getAllCouponApplySno(String ordno){
		return mapper.getAllCouponApplySno(ordno);
	}
	
	public int getRepayCount() {
		return mapper.getRepayCount();
	}

	public List<OrdercancelOrderitemMember> getRepayList(RepayVO repayVO) {
		return mapper.getRepayList(repayVO);
	}

	public int getRepayList2(OrdercancelOrderitemMember oom) {
		return mapper.getRepayList2(oom);
	}

	public OrderitemOrdercancel getRepayList3(String oCordno) {
		return mapper.getRepayList3(oCordno);
	}

	public void updateOrderRepay1(RepayVO repayVO) {
		mapper.updateOrderRepay1(repayVO);
	}

	public void updateOrderRepay2(RepayVO repayVO) {
		mapper.updateOrderRepay2(repayVO);
	}

	public String updateOrderRepay3(RepayVO repayVO) {
		// TODO Auto-generated method stub
		return mapper.updateOrderRepay3(repayVO);
	}

	public void updateOrderRepay4(RepayVO repayVO) {
		mapper.updateOrderRepay4(repayVO);
	}

	public List<HashMap> updateOrderRepay5(RepayVO repayVO) {
		return mapper.updateOrderRepay5(repayVO);
	}

	public void couponYnStatus(RepayVO repayVO) {
		mapper.couponYnStatus(repayVO);
	}

	public HashMap libfuncCtlStep1(RepayVO repayVO) {
		return mapper.libfuncCtlStep1(repayVO);
	}

	public void repaydb(RepayVO repayVO,HttpServletRequest req) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("repaydb>>>>>>>>>>>");
		}
		String[] arrSNO = req.getParameterValues("chks");
		String[] rprice=req.getParameterValues("repays");
		String[] rfee = req.getParameterValues("repayfees");
		String[] remoney = req.getParameterValues("remoneys");
		String[] m_no = req.getParameterValues("m_nos");
		String[] ordno = req.getParameterValues("ordnos");
		String[] bankcode = req.getParameterValues("bankcodes");
		String[] bankaccount = req.getParameterValues("bankaccounts");
		String[] bankuser = req.getParameterValues("bankusers");
		String[] sno = req.getParameterValues("snos");
		String[] oIsno = req.getParameterValues("oisno");
		String[] oIea = req.getParameterValues("oiea");
		String[] reserve = req.getParameterValues("reserve");
		
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		int j;
		
		for(int i=0;i<arrSNO.length;i++){
			
			j=Integer.parseInt(arrSNO[i]);
			
			List<GdOrderItem> item = shopLibFncService.setPrnSettleprice1(Long.parseLong(ordno[j]));
					
			String ono=null;
			
			Map<String,Object> m = new HashMap<String,Object>();
			
			//gd_order_item 정보
			GdOrderItem orderItemInfo = oService.orderChkCancel3(oIsno[j]);
			//gd_order 정보
			Map<String,Object> orderInfo = oService.getOrderInfo(Long.parseLong(ordno[j]));
	
			repayVO.setRprice(rprice[j]);
			repayVO.setRfee(rfee[j]);
			repayVO.setRemoney(remoney[j]);
			repayVO.setM_no(m_no[j]);
			repayVO.setOrdno(ordno[j]);
			repayVO.setBankcode(bankcode[j]);
			repayVO.setBankaccount(bankaccount[j]);
			repayVO.setBankuser(bankuser[j]);
			repayVO.setSno(sno[j]);
			
			updateOrderRepay1(repayVO);
			updateOrderRepay2(repayVO);
			ono = updateOrderRepay3(repayVO);
			repayVO.setOrdno(ono);
			updateOrderRepay4(repayVO);
		
			//chkFlag 주문상태가 전부 취소나 반품임을 확인하는 Flag
			int icnt = orderMapper.orderChkCancel10(ordno[j]);
			boolean chkFlag = icnt == 74 ? true : false;
			
			//재고 조정
			ShopLibFunction.setStock(Long.parseLong(ono));
			
			Map<String,Object> recoveryCoupon = new HashMap<String,Object>();
			recoveryCoupon.put("ordno", ordno[j]);
			//판매사 쿠폰 복원
			recoveryCoupon.put("goodsno", orderItemInfo.getGoodsno());
			String [] sellerCoupon = getCouponApplySno(recoveryCoupon);
			if(sellerCoupon.length > 0 && sellerCoupon != null){
				for(String s : sellerCoupon){
					recoveryCoupon.put("applysno", s);
					this.couponYnStatus1(recoveryCoupon);
				}
			}
			
			if(Integer.parseInt(reserve[j]) > 0){
				String msg = "환불에 의한 적립금 회수";
				ShopLibFunction.setEmoney(-Integer.parseInt(reserve[j]),Integer.parseInt(m_no[j]),Long.parseLong(ono),msg);
				//orderInfo.put("reserve", "0");
				//this.repayReservePrice(orderInfo);
			}

			if (chkFlag) {			
				
				//전체 쿠폰 복원 
				String[] allCoupon = getAllCouponApplySno(ordno[j]);
				if(allCoupon.length > 0 && allCoupon != null){
					recoveryCoupon.clear();
					recoveryCoupon.put("ordno", ordno[j]);
					for (String s : allCoupon) {
						recoveryCoupon.put("applysno", s);
						this.couponYnStatus1(recoveryCoupon);
					}
				}
				
				int emoney = StringUtil.N2I(StringUtil.nvl(orderInfo.get("emoney"),"0"));
				if(emoney > 0){
					ShopLibFunction.setEmoney(emoney,Integer.parseInt(m_no[i]),Long.parseLong(ordno[i]),"주문 환불로 인한 사용적립금 환원");
					orderInfo.put("emoney", "0") ;
					this.repayEmoneyPrice(orderInfo);
				}
			}else{
				//환불로 인한 적립금 환불
				int emoney = StringUtil.N2I(String.valueOf(orderInfo.get("emoney")));
				if(m_no[i]!=null&&m_no[i]!="" && !"0".equals(m_no[i]) && remoney[i]!=null&&remoney[i]!="" && emoney >= StringUtil.N2D(remoney[i])){
					ShopLibFunction.setEmoney(StringUtil.N2I(remoney[i]),Integer.parseInt(m_no[i]),Long.parseLong(ordno[i]),"주문 환불로 인한 사용적립금 환원");
					emoney = emoney - StringUtil.N2I(remoney[i]);
					orderInfo.put("emoney", emoney) ;
					this.repayEmoneyPrice(orderInfo);
				}
			}
			ShopLibFunction.setPrnSettleprice(Long.parseLong(ordno[i]));
		}
	}
 
	//2020.01.13 이현빈 환불예정금액 계산
	public Map<String,Object> calculateRefundPrice(OrdercancelOrderitemMember repay, Map<String,Object> flagMap) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		//상품에 해당하는 판매사코드 
		String sellerCd = poService.getSellerCdForCancel(String.valueOf(repay.getoIsno()));
		int addopt = ShopLibFunction.getTotalOptionPrice(String.valueOf(repay.getoIaddopt()));
		param.put("sellerCd", sellerCd);
		param.put("ordno", repay.getoIordno());
		
		//판매사 배송비 중복 적용 방지를 위한 flag
		//판매사에 배송비 적용이 되면 flag 에 판매사 정보를 저장해 다음에 조회할 때 중복적용이 되는지 판별한다.
		String sellerCancel = StringUtil.nvl(flagMap.get(sellerCd),"");
		boolean flag = sellerCd.equals(sellerCancel) ? false : true;
		
		String ordno = StringUtil.nvl(flagMap.get("ordno"), "");
		boolean ordnoFlag = repay.getoIordno().equals(ordno) ? false : true;
		
		String[] allCoupon = oService.getAllCouponPrice(repay.getoIordno());
		if(allCoupon.length > 0){
			repay.setoIcoupon(repay.getoIcoupon() + oService.getAllCouponPercentPrice(allCoupon, repay.getoIprice() * repay.getoIea()));
		}
		
		int allCancel = poService.checkAllCancelOfSellerGoods(param); //allCancel 0이면 해당 판매사 상품이 모두 취소됨
		int icnt = orderMapper.getCancelOrRefundCnt(repay.getoIordno());
		int allCouponPrice = 0;
		if(icnt == 0 && ordnoFlag){
			flagMap.put("ordno", repay.getoIordno());
			if(allCoupon.length > 0){
				allCouponPrice = oService.addAllCouponPrice(repay.getoIordno(), allCoupon);
				repay.setAllCouponPrice(String.valueOf(allCouponPrice));
			}
 		}
		
		//환불 금액
		int refundPrice = 0;
		
		if(allCancel == 0 && flag){
				//취소된 상품에 배송비를 조회
				param = poService.getRecoveryDelivery(String.valueOf(repay.getoIsno()));
				repay.setlCdelivery(StringUtil.N2I(param.get("deliveryPrice").toString()));
				repay.setlCaddDelivery(StringUtil.N2I(param.get("addDeliveryPrice").toString()));
				flagMap.put(sellerCd, sellerCd);
		}else{
			repay.setlCaddDelivery(0);
			repay.setlCdelivery(0);
		}
		
		
		//환불 금액
		refundPrice = (repay.getoIprice() * repay.getoIea() + addopt /* 배송비 전체쿠폰은 사용자가 직접 차감하도록 환불금액 계산에선 제외했습니다. ()
																		+ repay.getlCdelivery() + repay.getlCaddDelivery()*/)
				- (repay.getoImemberdc() + repay.getoIcoupon() /*+ allCouponPrice*/);
		flagMap.put("refundPrice", refundPrice);
		
		return flagMap;
	}
	
	/**
	 * 2020-01-17 이현빈 전체 배송비를 구하기 위한 메소드 주문테이블 배송비 + 주문취소로그테이블 배송비  
	 * @param ordno
	 * @return
	 * 
	 */
	public Map<String,Object> deliverySetting(String ordno){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ordno", ordno);
		String [] sellerCds = mapper.getAllSellerCdForRefund(ordno);
		
		List<String> sellerCd = new ArrayList<String>();
		for(String s : sellerCds){
			if("admin".equals(s)){
				continue;
			}
			map.put("sellerCd", s);
			//allCancel 0이면 해당 판매사 상품이 모두 취소됨
			int allCancel = poService.checkAllCancelOfSellerGoods(map); 
			if(allCancel == 0){
				sellerCd.add(s);
			}
		}
		if(sellerCd.size() > 0){
			map.clear();
			map.put("sellerCd", sellerCd);
			map.put("ordno", ordno);
			map = mapper.totalDeliveryPrice(map);
			//map에 delivery=배송비 , addDelivery=추가배송비 값 조회
		}else{
			map.put("delivery", "0");
			map.put("addDelivery", "0");
		}
		return map;
	}
}

