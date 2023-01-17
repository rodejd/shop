package com.wepinit.wepinit_shop.batch.dao;

import com.wepinit.wepinit_shop.batch.vo.BatchGoodsVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BatchGoodsMapper {

	public int deleteAllGoodsno();

	public int insertBatchGoodsno(BatchGoodsVO batchGoodsVO);

	public int updateBatchCategoryGoodsCnt();
	
}
