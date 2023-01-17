package com.wepinit.wepinit_shop.batch.dao;

import com.wepinit.wepinit_shop.batch.vo.BatchOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BatchOrderMapper {
	/**
	 * 입금 기간 초과 주문 리스트 조회
	 * @return List<BatchOrderVO>
	 * */
	public List<BatchOrderVO> getDepositExceedPeriodOrderList();
	
	public List<BatchOrderVO> selectBatchGoodsList();
	
	public int updateBatchGoods(BatchOrderVO batchOrderVO);
	
	public int insertBatchGoodsImgLog(BatchOrderVO batchOrderVO);
	
	public List<BatchOrderVO> selectBatchGoodsListByLog();
}
