package com.wepinit.wepinit_shop.xmall.front.service.promotion;

import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotion;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGrp;
import com.wepinit.wepinit_shop.xmall.front.dao.promotion.FrontPromotionMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.promotion.FrontPromotionGoodsVO;
import com.wepinit.wepinit_shop.xmall.front.vo.promotion.FrontPromotionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FrontPromotionService {
	
	@Resource
	FrontPromotionMapper mapper;
	
	
	public List<GdPromotion> selectFrontPromotionList(FrontPromotionVO vo) throws Exception {
		return mapper.selectFrontPromotionList(vo);
	}
	
	public GdPromotion selectFrontPromotion(FrontPromotionVO vo) throws Exception {
		return mapper.selectFrontPromotion(vo);
	}
	
	public List<GdPromotionGrp> selectFrontPromotionGrpList(int pmno) throws Exception {
		return mapper.selectFrontPromotionGrpList(pmno);
	}
	
	public int selectFrontPromotionGoodsCount(FrontPromotionGoodsVO vo) throws Exception {
		return mapper.selectFrontPromotionGoodsCount(vo);
	}

	public List<GdPromotionGoods> selectFrontPromotionGoodsList(FrontPromotionGoodsVO vo) throws Exception {
		return mapper.selectFrontPromotionGoodsList(vo);
	}
		
}
