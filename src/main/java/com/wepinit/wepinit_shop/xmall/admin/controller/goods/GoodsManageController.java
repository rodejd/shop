package com.wepinit.wepinit_shop.xmall.admin.controller.goods;

import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsManageService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsManageVO;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/shop/admin/goods/manage/*")
public class GoodsManageController {
	
	@Resource
	GoodsManageService service;

	/**
	 * 상품 관리목록
	 * @param goodsManageVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="list")
	public String goodsManageList(@ModelAttribute("goodsManageVO") GoodsManageVO goodsManageVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		/*
		 * 상품관리 권한 
		 */
		ShopLibFunction.menuAuthPermissionCheck(req, res,"goods");
		
		//관리상품 리스트
		goodsManageVO.setGoodsManageList(service.selectGoodsManageList(goodsManageVO));
		
		//상품 검색 총건수 조회
		goodsManageVO.setRowCount(service.selectGoodsListCount(goodsManageVO));
		
		//상품 리스트 조회
		goodsManageVO.setGoodsDataList(service.selectGoodsDataList(goodsManageVO));
		
		return "/shop/admin/goods/manage/list";
	}
	
	/**
	 * 상품 관리 등록
	 * @param goodsManageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manageRegister.dojson")
	public String manageRegister(@ModelAttribute("goodsManageVO") GoodsManageVO goodsManageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		try {
			// 중복체크
			int dupCnt = service.selectGoodsManageCountByBinCd(goodsManageVO);
			
			//신규등록
			if(StringUtils.equals("register", goodsManageVO.getMode())) {
				if(dupCnt > 0) {
					model.addAttribute("msg", "이미 등록된 Bin코드 입니다.");
					return "dojson";
				}
				AdminSessionObject sessionObject = AdminSessionObject.getSessionObject(req);
				goodsManageVO.setM_id(sessionObject.getUserInfo().getUserId());
				
				int sno = service.insertGoodsManage(goodsManageVO);
				goodsManageVO.setSno(sno);
			//수정
			}else if(StringUtils.equals("modify", goodsManageVO.getMode())) {
				if(dupCnt > 1) {
					model.addAttribute("msg", "이미 등록된 Bin코드 입니다.");
					return "dojson";
				}
				service.updateGoodsManage(goodsManageVO);
			//삭제
			}else if(StringUtils.equals("delete", goodsManageVO.getMode())) {
				service.deleteGoodsManage(goodsManageVO);
			}
		}catch (Exception e) {
			goodsManageVO.setSno(0);
			model.addAttribute("msg", "오류가 발생했습니다.");
			return "dojson";
		}
		return "dojson";
	}
	
	/**
	 * 관리상품 확정
	 * @param goodsManageVO
	 * @param redirectModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manageProc.dojson")
	public String manageDB(@ModelAttribute("goodsManageVO") GoodsManageVO goodsManageVO, Model model) throws Exception {
		int result = 0;
		try {
			String[] goodsArr = goodsManageVO.getGoodsArr();
			String[] manageArr = goodsManageVO.getManageArr();
			
			if(goodsArr != null) {
				for(int i=0; i < goodsArr.length; i++) {
					HashMap<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("goodsno", goodsArr[i]);
					dataMap.put("manageYn", manageArr[i]);
					result += service.updateGoodsManageBymanageYn(dataMap);
				}
			}
			model.addAttribute("result", result);
		}catch (Exception e) {
			model.addAttribute("result", 0);
			model.addAttribute("msg", "오류가 발생했습니다.");
			return "dojson";
		}
		return "dojson";
	}
	
}
