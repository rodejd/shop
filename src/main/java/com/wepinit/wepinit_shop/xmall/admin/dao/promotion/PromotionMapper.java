package com.wepinit.wepinit_shop.xmall.admin.dao.promotion;

import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionGoodsVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionGrpVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotion;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGrp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PromotionMapper {

	public int selectPromotionCount(PromotionVO vo);
	
	public List<GdPromotion> selectPromotionList(PromotionVO vo);
	
	public GdPromotion selectPromotion(int pmno);
	
	public int insertPromotion(PromotionVO vo);
	
	public int updatePromotion(PromotionVO vo);
	
	public int updatePromotionByImage(PromotionVO vo);
	
	public int updatePromotionByUseYn(PromotionVO vo);
	
	public int deletePromotion(int pmno);
	
	public List<GdPromotionGrp> selectPromotionGrpList(int pmno);
	
	public int insertPromotionGrp(PromotionGrpVO vo);
	
	public int updatePromotionGrp(PromotionGrpVO vo);
	
	public int deletePromotionGrp(PromotionGrpVO vo);
	
	public int selectPromotionGoodsCount(PromotionGoodsVO vo);
	
	public List<GdPromotionGoods> selectPromotionGoodsList(PromotionGoodsVO vo);
	
	public int selectGoodsCount(String goodscd);
	
	public int insertPromotionGoods(PromotionGoodsVO vo);

	public int updatePromotionGoodsGrnoInit(int grno);
	
	public int updatePromotionGoodsGrnoMove(Map<String, Object> map);

	public int deletePromotionGoods(Map<String, Object> map);
	
	public int updatePromotionGoodsSort(PromotionGoodsVO vo);
	
	public List<GdPromotionGoods> selectPromotionGoodsSrch(PromotionGoodsVO vo);
	
}
