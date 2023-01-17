package com.wepinit.wepinit_shop.xmall.front.dao.boutique;

import com.wepinit.wepinit_shop.xmall.common.vo.GdBoutique;
import com.wepinit.wepinit_shop.xmall.front.vo.boutique.FrontBoutiqueVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FrontBoutiqueMapper {
	
	public int getFrontBoutiqueCount(FrontBoutiqueVO vo);

	public List<GdBoutique> getFrontBoutiqueList(FrontBoutiqueVO vo);
	
}
