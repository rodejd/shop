package com.wepinit.wepinit_shop.xmall.front.controller.promotion;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGrp;
import com.wepinit.wepinit_shop.xmall.front.service.promotion.FrontPromotionService;
import com.wepinit.wepinit_shop.xmall.front.vo.promotion.FrontPromotionGoodsVO;
import com.wepinit.wepinit_shop.xmall.front.vo.promotion.FrontPromotionVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/shop/promotion/*")
public class FrontPromotionController {

	@Resource
	FrontPromotionService service;

	@Resource
	GoodsService goodsService;
	
	/**
	 * 리스트 페이지
	 * @param frontPromotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "promotion")
	public String promotion(@ModelAttribute("frontPromotionVO") FrontPromotionVO frontPromotionVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		frontPromotionVO.setSkin(ConfigClass.getSkin(req));
		
		// 기획전 정보
		frontPromotionVO.setPromotionObj(service.selectFrontPromotion(frontPromotionVO));
		
		// 구분 정보
		List<GdPromotionGrp> groupList = service.selectFrontPromotionGrpList(frontPromotionVO.getPmno());
		
		// 무지정구분
		GdPromotionGrp gdPromotionGrp = new GdPromotionGrp();
		gdPromotionGrp.setGrno(0);
		gdPromotionGrp.setPmno(frontPromotionVO.getPmno());
		gdPromotionGrp.setGrnm("ETC");
		gdPromotionGrp.setSort(0);
		gdPromotionGrp.setUseYn("Y");
		groupList.add(gdPromotionGrp);
		
		// 상품 정보
		FrontPromotionGoodsVO frontPromotionGoodsVO = new FrontPromotionGoodsVO();
		frontPromotionGoodsVO.setPmno(frontPromotionVO.getPmno());

		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		frontPromotionGoodsVO.setM_no(userInfo.getM_no());
		
		List<GdPromotionGoods> goodsList = service.selectFrontPromotionGoodsList(frontPromotionGoodsVO);
		
		// 구분별 상품 정리
		for (int i = 0; i < groupList.size(); i++) {
			for (GdPromotionGoods goods : goodsList) {
				if (groupList.get(i).getGrno() == goods.getGrno()) {
					groupList.get(i).getGoodsList().add(goods);
				}
			}
		}
		frontPromotionVO.setGroupList(groupList);
	
		return "/shop/promotion/promotion";
	}
}
