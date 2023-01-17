package com.wepinit.wepinit_shop.xmall.front.dao.promotion;

import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotion;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGrp;
import com.wepinit.wepinit_shop.xmall.front.vo.promotion.FrontPromotionGoodsVO;
import com.wepinit.wepinit_shop.xmall.front.vo.promotion.FrontPromotionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FrontPromotionMapper {

	public List<GdPromotion> selectFrontPromotionList(FrontPromotionVO vo);
	
	public GdPromotion selectFrontPromotion(FrontPromotionVO vo);
	
	public List<GdPromotionGrp> selectFrontPromotionGrpList(int pmno);
	
	public int selectFrontPromotionGoodsCount(FrontPromotionGoodsVO vo);
	
	public List<GdPromotionGoods> selectFrontPromotionGoodsList(FrontPromotionGoodsVO vo);
	
}
