package com.wepinit.wepinit_shop.xmall.front.vo.boutique;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBoutique;

import java.util.List;

public class FrontBoutiqueVO extends PageMaker {
	
	private List<GdBoutique> boutiqueList = null;

	public List<GdBoutique> getBoutiqueList() {
		return boutiqueList;
	}
	public void setBoutiqueList(List<GdBoutique> boutiqueList) {
		this.boutiqueList = boutiqueList;
	}
	
}
