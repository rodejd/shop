package com.wepinit.wepinit_shop.xmall.admin.controller.basic;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.admin.repository.TermsAndConditionsRepository;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.PolicyVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.TermsAndConditions;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


@Controller
@RequestMapping("/shop/admin/basic/*")
public class PolicyController {
	@Autowired
	private TermsAndConditionsRepository termsAndConditionsRepository;

	/**
	 * Policy 관리
	 * @param policyVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "policy")
	public String getClause(@ModelAttribute("policyVO") PolicyVO policyVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		if (StringUtils.isBlank(policyVO.getSkin())) {
			policyVO.setSkin("kr");
		}
		
		/* type
		1: 품질보증정책
		2: 주문배송정책
		3: 교환반품정책
		4: 고객등급정책
		5: 결제/가격/적립금정책
		6: A/S정책
		7: 개인정보 보호 */
		/*
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		StringBuffer sb = new StringBuffer();
		ArrayList<?> list = FileUtil.getOneLineListToFile(filePath.concat("policy_" + policyVO.getType() + "_" + policyVO.getSkin()+".html"));
		int i = 0;
		if (null != list && 0 < list.size()) {
			i = 0;
			while (i < list.size()) {
				sb.append((String)list.get(i) + "\n");
				i++;
			}
		}
		policyVO.setPolicy(sb.toString());
		 */

		TermsAndConditions item = termsAndConditionsRepository.findTopByCategoryAndTypeAndSkin("policy", policyVO.getType(), policyVO.getSkin());
		model.addAttribute("item", item);

		return "/shop/admin/basic/policy";
	}
	
	@RequestMapping(value = "policyRegist")
	public String setClause(@ModelAttribute("policyVO") PolicyVO policyVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		// 내용저장
		/*
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		FileUtil.savefile(filePath.concat("policy_" + policyVO.getType() + "_" + policyVO.getSkin() + ".html"), policyVO.getPolicy(), false);
		 */

		String content = policyVO.getPolicy();

		TermsAndConditions item = null;
		if (req.getParameter("no") != null && !req.getParameter("no").equals("")) {
			item = termsAndConditionsRepository.findByNo(Integer.parseInt(req.getParameter("no")));
		} else {
			item = TermsAndConditions.builder()
				.category("policy")
				.type(policyVO.getType())
				.skin(policyVO.getSkin())
				.build();
		}

		item.setContent(content);

		termsAndConditionsRepository.save(item);

		return "redirect:/shop/admin/basic/policy?type=" + policyVO.getType() + "&menuKey=" + policyVO.getMenuKey();
	}	
}