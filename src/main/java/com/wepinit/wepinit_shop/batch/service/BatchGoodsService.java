package com.wepinit.wepinit_shop.batch.service;

import com.wepinit.wepinit_shop.batch.BatchGoods;
import com.wepinit.wepinit_shop.batch.dao.BatchGoodsMapper;
import com.wepinit.wepinit_shop.batch.vo.BatchGoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BatchGoodsService {
	@Resource
	BatchGoodsMapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(BatchGoods.class);

	public void deleteAllGoodsno() {
		this.mapper.deleteAllGoodsno();
	}

	public void insertBatchGoodsno(BatchGoodsVO batchGoodsVO) {
		this.mapper.insertBatchGoodsno(batchGoodsVO);
	}

	public void updateBatchCategoryGoodsCnt() {
		this.mapper.updateBatchCategoryGoodsCnt();
	}
	
}
