package com.wepinit.wepinit_shop.xmall.admin.service.promotion;

import com.wepinit.wepinit_shop.xmall.admin.dao.promotion.PromotionMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionGoodsVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionGrpVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotion;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGrp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PromotionService {
	
	@Resource
	PromotionMapper mapper;
	
	public int selectPromotionTotalCount() throws Exception {
		PromotionVO vo = new PromotionVO();
		return mapper.selectPromotionCount(vo);
	}
	
	public int selectPromotionCount(PromotionVO vo) throws Exception {
		return mapper.selectPromotionCount(vo);
	}
	
	public List<GdPromotion> selectPromotionList(PromotionVO vo) throws Exception {
		return mapper.selectPromotionList(vo);
	}
	
	public GdPromotion selectPromotion(int pmno) throws Exception {
		return mapper.selectPromotion(pmno);
	}
	
	public int insertPromotion(PromotionVO vo) throws Exception {
		return mapper.insertPromotion(vo);
	}
	
	public int updatePromotion(PromotionVO vo) throws Exception {
		return mapper.updatePromotion(vo);
	}
	
	public int updatePromotionByImage(PromotionVO vo) throws Exception {
		return mapper.updatePromotionByImage(vo);
	}

	public int updatePromotionByUseYn(PromotionVO vo) throws Exception {
		return mapper.updatePromotionByUseYn(vo);
	}
	
	public int deletePromotion(int pmno) throws Exception {
		return mapper.deletePromotion(pmno);
	}

	public List<GdPromotionGrp> selectPromotionGrpList(int pmno) throws Exception {
		return mapper.selectPromotionGrpList(pmno);
	}
	
	public int insertPromotionGrp(PromotionGrpVO vo) throws Exception {
		return mapper.insertPromotionGrp(vo);
	}
	
	public int updatePromotionGrp(PromotionGrpVO vo) throws Exception {
		return mapper.updatePromotionGrp(vo);
	}
	
	public int deletePromotionGrp(PromotionGrpVO vo) throws Exception {
		return mapper.deletePromotionGrp(vo);
	}
	
	public int selectPromotionGoodsCount(PromotionGoodsVO vo) throws Exception {
		return mapper.selectPromotionGoodsCount(vo);
	}

	public List<GdPromotionGoods> selectPromotionGoodsList(PromotionGoodsVO vo) throws Exception {
		return mapper.selectPromotionGoodsList(vo);
	}
	
	public int selectGoodsCount(String goodscd) throws Exception {
		return mapper.selectGoodsCount(goodscd);
	}
	
	public int insertPromotionGoods(PromotionGoodsVO vo) throws Exception {
		return mapper.insertPromotionGoods(vo);
	}
	
	public int updatePromotionGoodsGrnoInit(int grno) throws Exception {
		return mapper.updatePromotionGoodsGrnoInit(grno);
	}
	
	public int updatePromotionGoodsGrnoMove(Map<String, Object> map) throws Exception {
		return mapper.updatePromotionGoodsGrnoMove(map);
	}

	public int deletePromotionGoods(Map<String, Object> map) throws Exception {
		return mapper.deletePromotionGoods(map);
	}
	
	public int updatePromotionGoodsSort(PromotionGoodsVO vo) throws Exception {
		return mapper.updatePromotionGoodsSort(vo);
	}

	public List<GdPromotionGoods> selectPromotionGoodsSrch(PromotionGoodsVO vo) throws Exception {
		return mapper.selectPromotionGoodsSrch(vo);
	}
	
}
