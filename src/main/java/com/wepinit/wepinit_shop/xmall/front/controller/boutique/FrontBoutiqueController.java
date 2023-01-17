package com.wepinit.wepinit_shop.xmall.front.controller.boutique;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.common.ApiHttpUtil;
import com.wepinit.wepinit_shop.xmall.front.service.boutique.FrontBoutiqueService;
import com.wepinit.wepinit_shop.xmall.front.vo.boutique.FrontBoutiqueVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shop/*")
public class FrontBoutiqueController {

	@Resource
	FrontBoutiqueService service;

	@Resource
	GoodsService goodsService;
	
	/**
	 * 리스트 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "boutique")
	public String boutique(@ModelAttribute("frontBoutiqueVO") FrontBoutiqueVO frontBoutiqueVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		frontBoutiqueVO.setSkin(ConfigClass.getSkin(req));
		
		frontBoutiqueVO.setRowCount(service.getFrontBoutiqueCount(frontBoutiqueVO));
		
		if (frontBoutiqueVO.getRowCount() > 0) {
			frontBoutiqueVO.setBoutiqueList(service.getFrontBoutiqueList(frontBoutiqueVO));
		}
	
		return "/shop/boutique/boutique";
	}
	
	/**
	 * 리스트 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "ritz_live")
	public String ritz_live(@ModelAttribute("frontBoutiqueVO") FrontBoutiqueVO frontBoutiqueVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		frontBoutiqueVO.setSkin(ConfigClass.getSkin(req));
		
		//get accessToken 
		///authorize?partnerId=${partnerId}&id=${userId}&password=${password}
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		//개발
//		String partnerId = "rizmall";
//		String id = "rizmall";
//		String password = "rizmall1!";
		//운영
		String partnerId = "ritzmall";
		String id = "ritzmall";
		String password = "ritzmall1!";
				
		paramsMap.put("partnerId", partnerId);
		paramsMap.put("id", id);
		paramsMap.put("password", password);
		
		model.addAttribute("partnerId", partnerId);
		
		Map<String, Object> result = ApiHttpUtil.get("/authorize", paramsMap);

		//ReturnMap
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if ("SU0000".equals(result.get("code"))) {
			returnMap = (Map<String, Object>) result.get("response");
			model.addAttribute("token", returnMap.get("accessToken"));
		}
	
		return "/shop/boutique/ritz_live";
	}
}
