package com.wepinit.wepinit_shop.batch.service;

import com.wepinit.wepinit_shop.batch.BatchOrder;
import com.wepinit.wepinit_shop.batch.dao.BatchOrderMapper;
import com.wepinit.wepinit_shop.batch.vo.BatchOrderVO;
import com.wepinit.wepinit_shop.xmall.admin.service.order.PopupOrderService;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderCancelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BatchOrderService {
	@Resource
	BatchOrderMapper mapper;
	
	@Resource
	PopupOrderService popupOrderService;
	
	private static final Logger logger = LoggerFactory.getLogger(BatchOrder.class);
	
	/**
	 * 입금 기한 초과 주문건의 주문 취소를 처리합니다.
	 * */
	public void updateDepositExceedPeriodOrderCancel() throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("BatchService >> getDepositExceedPeriodOrderList()");
		}
		List<BatchOrderVO> depositExceedPeriodOrders = this.getDepositExceedPeriodOrderList();
		for(BatchOrderVO depositExceedPeriodOrder : depositExceedPeriodOrders) {
			OrderCancelVO orderCancel = this.getOrderCancel(depositExceedPeriodOrder);
			this.popupOrderService.orderCancel(orderCancel);
		}
	}
	
	/**
	 * 조회된 BatchOrderVO를 OrderCancelVO에 알맞게 세팅하여 return 합니다.
	 * */
	private OrderCancelVO getOrderCancel(BatchOrderVO depositExceedPeriodOrder) {
		OrderCancelVO orderCancel = new OrderCancelVO();
		
		orderCancel.setOrdno(depositExceedPeriodOrder.getOrdno());
		
		String[] sno = {depositExceedPeriodOrder.getSno()};
		orderCancel.setSno(sno);
		
		String[] ea = {depositExceedPeriodOrder.getEa()};
		orderCancel.setEa(ea); 
		orderCancel.setpName("batch");
		orderCancel.setCode("10");
		orderCancel.setMemo("입금 기간 초과 주문 취소건");
		
		return orderCancel;
	}
	
	/**
	 * 입금 기간 초과 주문 리스트 조회
	 * @return List<BatchOrderVO>
	 * */
	public List<BatchOrderVO> getDepositExceedPeriodOrderList() {
		return this.mapper.getDepositExceedPeriodOrderList();
	}
	
	public List<BatchOrderVO> selectBatchGoodsList() {
		return this.mapper.selectBatchGoodsList();
	}
	
	public int updateBatchGoods(BatchOrderVO batchOrderVO) {
		return this.mapper.updateBatchGoods(batchOrderVO);
	}
	
	public int insertBatchGoodsImgLog(BatchOrderVO batchOrderVO) {
		return this.mapper.insertBatchGoodsImgLog(batchOrderVO);
	}
	
	public List<BatchOrderVO> selectBatchGoodsListByLog() {
		return this.mapper.selectBatchGoodsListByLog();
	}
}
