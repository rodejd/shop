package com.wepinit.wepinit_shop.xmall.admin.dao.goods;

import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsManageVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface GoodsManageMapper {
	
	public List<GdGoodsManage> selectGoodsManageList(GoodsManageVO goodsManageVO);
	
	public int selectGoodsListCount(GoodsManageVO goodsManageVO);
	
	public List<GoodsManageVO> selectGoodsDataList(GoodsManageVO goodsManageVO);
	
	public int selectGoodsManageCountByBinCd(GoodsManageVO goodsManageVO);
	
	public int insertGoodsManage(GoodsManageVO goodsManageVO);
	
	public int updateGoodsManage(GoodsManageVO goodsManageVO);
	
	public int deleteGoodsManage(GoodsManageVO goodsManageVO);
	
	public int updateGoodsManageBymanageYn(HashMap<String, Object> dataMap);
	
} 
