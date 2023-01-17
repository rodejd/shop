package com.wepinit.wepinit_shop.xmall.admin.controller.promotion;

import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.admin.service.promotion.PromotionService;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionGoodsVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionGrpVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.PromotionVO;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotion;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 기획전 관리
 */
@Controller
@RequestMapping("/shop/admin/promotion/*")
public class PromotionController {

	@Resource
	PromotionService service;

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
	@RequestMapping(value = "promotion/list")
	public String promotion(@ModelAttribute("promotionVO") PromotionVO promotionVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//총건수
		promotionVO.setTotalCnt(service.selectPromotionTotalCount());
		
		//검색 총건수 조회
		promotionVO.setRowCount(service.selectPromotionCount(promotionVO));
		
		if (promotionVO.getRowCount() > 0) {
			promotionVO.setPromotionList(service.selectPromotionList(promotionVO));
		}
		
		return "/shop/admin/promotion/promotion";
	}
	
	/**
	 * 등록 & 수정 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="promotion/register")
	public String promotionRegister(@ModelAttribute("promotionVO") PromotionVO promotionVO,
			HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		//수정일 경우
		if( StringUtils.equals("modify", promotionVO.getMode()) ) {
			promotionVO.setPromotionObj(service.selectPromotion(promotionVO.getPmno()));
		}
		
		if (StringUtils.equals("", promotionVO.getMode()) || promotionVO.getPromotionObj() == null) {
			promotionVO.setMode("register");
			promotionVO.setPmno(0);
		}
		
		return "/shop/admin/promotion/promotion_register";
	}
	
	/**
	 * DB처리
	 * @param promotionVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="promotion/indb")
	public String promotionIndb(@ModelAttribute("promotionVO") PromotionVO promotionVO) throws Exception {
		
		//삭제
		if( StringUtils.equals("delete", promotionVO.getMode()) ) {
			service.deletePromotion(promotionVO.getPmno());
			
			// S3 파일삭제
			AwsFileUtil awsfileUtil = new AwsFileUtil();
			String awsKey = "promotion/" + String.valueOf(promotionVO.getPmno());
			awsfileUtil.deleteList(awsKey);
			
			return "redirect:/shop/admin/promotion/promotion/list";
			
		//강제종료
		}else if( StringUtils.equals("useyn", promotionVO.getMode()) ) {
			PromotionVO updatePromotionVO = new PromotionVO();
			updatePromotionVO.setPmno(promotionVO.getPmno());
			updatePromotionVO.setUseYn("N");
			service.updatePromotionByUseYn(updatePromotionVO);
			
			return "redirect:/shop/admin/promotion/promotion/list?schSkin=" + promotionVO.getSchSkin() + "&schUseYn=" + promotionVO.getSchUseYn() + "&schWord=" + promotionVO.getSchWord() + "&schSdt=" + promotionVO.getSchSdt() + "&schEdt=" + promotionVO.getSchEdt() + "&pageNo=" + promotionVO.getPageNo();
		}else {
			String pcImg = "";
			String moImg = "";
			
			//등록
			if( StringUtils.equals("register", promotionVO.getMode()) ) {
				promotionVO.setRegid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
				service.insertPromotion(promotionVO);
				
			//수정
			}else if( StringUtils.equals("modify", promotionVO.getMode()) ) {
				GdPromotion promotionInfo = service.selectPromotion(promotionVO.getPmno());
				
				promotionVO.setModid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
				service.updatePromotion(promotionVO);
				
				//이미지 삭제
				AwsFileUtil awsfileUtil = new AwsFileUtil();
				String awsKey = "";
				if( promotionVO.getPcImgFile() != null) {
					if( !promotionVO.getPcImgFile().isEmpty() && FileUtil.getChkAwsFile(promotionInfo.getPcImg()) ){
						awsKey = "promotion/" + String.valueOf(promotionVO.getPmno()) + "/" + FileUtil.getUrlFileName(promotionInfo.getPcImg());
						// S3 파일삭제
						awsfileUtil.delete(awsKey);
					}
				}
				
				if( promotionVO.getMobileImgFile() != null) {
					if( !promotionVO.getMobileImgFile().isEmpty() && FileUtil.getChkAwsFile(promotionInfo.getMobileImg()) ){
						awsKey = "promotion/" + String.valueOf(promotionVO.getPmno()) + "/" + FileUtil.getUrlFileName(promotionInfo.getMobileImg());
						// S3 파일삭제
						awsfileUtil.delete(awsKey);
					}
				}
				
				//이미지 변경이 아닐경우 기존이미지로 값설정
				if( StringUtils.isBlank(pcImg) ) pcImg = promotionInfo.getPcImg();
				if( StringUtils.isBlank(moImg) ) moImg = promotionInfo.getMobileImg();
			}
			
			//PC 이미지
			if(!promotionVO.getPcImgFile().isEmpty()){
				// AWS 파일업로드
				String awsPath = "promotion/" + String.valueOf(promotionVO.getPmno()) + "/";
				pcImg = FileUtil.awsUploadFile(promotionVO.getPcImgFile().getOriginalFilename(), promotionVO.getPcImgFile().getBytes(), awsPath);
			}
			
			//MO 이미지
			if(!promotionVO.getMobileImgFile().isEmpty()){
				// AWS 파일업로드
				String awsPath = "promotion/" + String.valueOf(promotionVO.getPmno()) + "/";
				moImg = FileUtil.awsUploadFile(promotionVO.getMobileImgFile().getOriginalFilename(), promotionVO.getMobileImgFile().getBytes(), awsPath);
			}
			
			PromotionVO updatePromotionVO = new PromotionVO();
			updatePromotionVO.setPmno(promotionVO.getPmno());
			updatePromotionVO.setPcImg(pcImg);
			updatePromotionVO.setMobileImg(moImg);
			service.updatePromotionByImage(updatePromotionVO);
			
			return "redirect:/shop/admin/promotion/promotion/list?schSkin=" + promotionVO.getSchSkin() + "&schUseYn=" + promotionVO.getSchUseYn() + "&schWord=" + promotionVO.getSchWord() + "&schSdt=" + promotionVO.getSchSdt() + "&schEdt=" + promotionVO.getSchEdt() + "&pageNo=" + promotionVO.getPageNo();
		}
	}
	
	/**
	 * 기획상품 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "promotion/goods")
	public String promotionGoods(@ModelAttribute("promotionGoodsVO") PromotionGoodsVO promotionGoodsVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//총건수
		promotionGoodsVO.setTotalCnt(service.selectPromotionGoodsCount(promotionGoodsVO));
		if (promotionGoodsVO.getTotalCnt() > 0) {
			promotionGoodsVO.setGoodsList(service.selectPromotionGoodsList(promotionGoodsVO));
		}
		promotionGoodsVO.setGroupList(service.selectPromotionGrpList(promotionGoodsVO.getPmno()));
		
		return "/shop/admin/promotion/promotion_goods";
	}
	
	/**
	 * 구분편집 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="promotion/groupPopup", method=RequestMethod.GET)
	public String promotionGroupPopup(@ModelAttribute("promotionGrpVO") PromotionGrpVO promotionGrpVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		promotionGrpVO.setGroupList(service.selectPromotionGrpList(promotionGrpVO.getPmno()));
		
		return "/shop/admin/promotion/promotion_group_popup";
	}
	
	/**
	 * 구분저장
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="promotion/groupPopup", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> promotionGroupPopupIns(@RequestBody Map<String, Object> params) {

		Map<String, String> rst = new HashMap<String, String>();
		int ins = 0;
		int udt = 0;
		int del = 0;
		
		List<Map<String, String>> list = (List<Map<String, String>>) params.get("list");
		for (Map<String, String> grp : list) {
			if (!"".equals(grp.get("grnm"))) {
				try {
					PromotionGrpVO grpVO = new PromotionGrpVO();
					grpVO.setGrno(StringUtil.N2I(grp.get("grno")));
					grpVO.setPmno(StringUtil.N2I(StringUtil.nvl(params.get("pmno"))));
					grpVO.setGrnm(grp.get("grnm"));
					grpVO.setSort(StringUtil.N2I(grp.get("sort")));
					grpVO.setUseYn(grp.get("useYn"));
					
					if ("Y".equals(grp.get("gdel"))) {
						service.deletePromotionGrp(grpVO);
						service.updatePromotionGoodsGrnoInit(grpVO.getGrno());
						del++;
					} else if (grpVO.getGrno() > 0) {
						service.updatePromotionGrp(grpVO);
						udt++;
						System.out.println("!!!");
					} else {
						service.insertPromotionGrp(grpVO);
						ins++;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

		rst.put("ins", Integer.toString(ins));
		rst.put("udt", Integer.toString(udt));
		rst.put("del", Integer.toString(del));
		rst.put("tot", Integer.toString(ins + udt + del));
		
		return rst;
	}
	
	/**
	 * 구분이동
	 * @param params
	 * @return
	 */
	@RequestMapping(value="promotion/groupMove", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> promotionGroupMove(@RequestBody Map<String, Object> params) {

		Map<String, String> rst = new HashMap<String, String>();
		int cnt = 0;
		
		try {
			cnt = service.updatePromotionGoodsGrnoMove(params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rst.put("cnt", Integer.toString(cnt));
		return rst;
	}
	
	/**
	 * 기획전 상품 삭제
	 * @param params
	 * @return
	 */
	@RequestMapping(value="promotion/goodsDel", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> promotionGoodsDel(@RequestBody Map<String, Object> params) {

		Map<String, String> rst = new HashMap<String, String>();
		int cnt = 0;
		
		try {
			cnt = service.deletePromotionGoods(params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rst.put("cnt", Integer.toString(cnt));
		return rst;
	}
	
	
	/**
	 * 상품 순서 저장
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="promotion/saveSortChange", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> promotionGoodsSortChange(@RequestBody Map<String, Object> params) {

		Map<String, String> rst = new HashMap<String, String>();
		int udt = 0;
		
		List<Map<String, String>> list = (List<Map<String, String>>) params.get("list");
		for (Map<String, String> pmg : list) {
			if (!"".equals(pmg.get("pgno"))) {
				try {
					PromotionGoodsVO pGoodsVO = new PromotionGoodsVO();
					pGoodsVO.setPgno(StringUtil.N2I(pmg.get("pgno")));
					pGoodsVO.setSort(StringUtil.N2I(pmg.get("sort")));

					service.updatePromotionGoodsSort(pGoodsVO);
					udt++;
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

		rst.put("udt", Integer.toString(udt));
		
		return rst;
	}
	
	
	/**
	 * 상품대량등록 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="promotion/goodsBulkPopup", method=RequestMethod.GET)
	public String promotionGoodsBulkPopup(@ModelAttribute("promotionGoodsVO") PromotionGoodsVO promotionGoodsVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		promotionGoodsVO.setGroupList(service.selectPromotionGrpList(promotionGoodsVO.getPmno()));
		
		return "/shop/admin/promotion/promotion_goods_bulk_popup";
	}
	
	/**
	 * 상품검색등록 페이지
	 * @param promotionVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="promotion/goodsSrchPopup")
	public String promotionGoodsSrchPopup(@ModelAttribute("promotionGoodsVO") PromotionGoodsVO promotionGoodsVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if ("search".equals(promotionGoodsVO.getMode())) {
			String schCate = "";
			if (promotionGoodsVO.getCate() != null) {
				if (promotionGoodsVO.getCate().length > 0) {
					for (int i=0; i<promotionGoodsVO.getCate().length; i++){
						if (!"".equals(StringUtil.nullConv(promotionGoodsVO.getCate()[i], ""))){
							schCate = promotionGoodsVO.getCate()[i];
						}
					}
				}
				promotionGoodsVO.setSchCate(schCate);
			}
			promotionGoodsVO.setGoodsList(service.selectPromotionGoodsSrch(promotionGoodsVO));
			promotionGoodsVO.setGroupList(service.selectPromotionGrpList(promotionGoodsVO.getPmno()));
		} else {
			promotionGoodsVO.setGoodsBrandList(goodsService.getBrandList());
			promotionGoodsVO.setGoodsSeasonList(goodsService.getSeasonList());
		}
		
		return "/shop/admin/promotion/promotion_goods_srch_popup";
	}
	
	/**
	 * 상품 등록 저장
	 * @param params
	 * @return
	 */
	@RequestMapping(value="promotion/goodsIns", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> promotionGoodsIns(@RequestBody Map<String, String> params) {

		Map<String, String> rst = new HashMap<String, String>();
		int tot = 0;
		int cnt = 0;
		
		String[] scmcd = StringUtil.nullConv(params.get("scmcd").replaceAll("\\n", ","), "").split(",");
		if (scmcd.length > 0) {
			
			PromotionGoodsVO goodsVO = new PromotionGoodsVO();
			goodsVO.setPmno(StringUtil.N2I(params.get("pmno")));
			goodsVO.setGrno(StringUtil.N2I(params.get("grno")));
			goodsVO.setSort(0);
			
			for (int i = 0; i < scmcd.length; i++) {
					if (!"".equals(scmcd[i])) {
						tot++;

						try {
							if (service.selectGoodsCount(scmcd[i]) > 0) {
								goodsVO.setGoodscd(scmcd[i]);
								service.insertPromotionGoods(goodsVO);
								if (goodsVO.getPgno() > 0)
									cnt++;
							}
						} catch (Exception e) {
						}
					}
			}	
		}
		
		rst.put("tot", Integer.toString(tot));
		rst.put("cnt", Integer.toString(cnt));
		rst.put("err", Integer.toString(tot - cnt));
		
		return rst;
	}
}
