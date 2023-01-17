	/*******************************************************************
	                     PMGroupKorea Co., Ltd.
	Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
	No part of this work covered by the copyright hereon may be reproduced,
	stored in a retrieval system, in any form or by any means, electronic,
	mechanical, photocopying, recording or otherwise, without the prior
	written permission of PMGroupKorea Co., Ltd.
	SOLUTION    :   XMaLL4 for Spring
	FILE_NAME   :
	DATE        :   2018.1.29
	AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
	DESCRIPTION :
	FUNCTIONS   :
	HISTORY     :
	REMARKS     :
	******************************************************************/
	package com.wepinit.wepinit_shop.xmall.admin.service.goods;

    import com.amazonaws.services.s3.model.PutObjectResult;
	import com.wepinit.wepinit_shop.common.ConfigClass;
	import com.wepinit.wepinit_shop.xcube.ExcelDataLoad;
    import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
    import com.wepinit.wepinit_shop.xcube.util.FileUtil;
    import com.wepinit.wepinit_shop.xcube.util.JpegReader;
    import com.wepinit.wepinit_shop.xcube.util.StringUtil;
    import com.wepinit.wepinit_shop.xmall.admin.dao.common.CommonMapper;
    import com.wepinit.wepinit_shop.xmall.admin.dao.goods.GoodsMapper;
    import com.wepinit.wepinit_shop.xmall.admin.service.common.AdminSellerSearchService;
    import com.wepinit.wepinit_shop.xmall.admin.vo.common.AdminSellerSearchFM;
    import com.wepinit.wepinit_shop.xmall.admin.vo.common.AdminSellerSearchListVO;
    import com.wepinit.wepinit_shop.xmall.admin.vo.goods.*;
    import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
    import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
    import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
    import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
    import com.wepinit.wepinit_shop.xmall.common.vo.*;
    import com.wepinit.wepinit_shop.xmall.seller.goods.dao.SellerGoodsMapper;
    import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsViewVO;
    import org.apache.commons.lang.StringUtils;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.multipart.MultipartHttpServletRequest;

    import javax.annotation.Resource;
    import javax.imageio.ImageIO;
    import javax.servlet.http.HttpServletRequest;
    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.InputStream;
    import java.net.URL;
    import java.net.URLDecoder;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.List;
    import java.util.*;
	
@Service
public class GoodsService {
	
	@Resource
	GoodsMapper mapper;
	
	@Resource
	BrandService brandService;
	
	@Resource
	SellerGoodsMapper sellerMapper;
	
	@Resource
	AdminSellerSearchService adminService;
	
	@Resource
	CategoryService categoryService;
	
	@Resource
	CommonMapper commonMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);
	
	
	/**
	 * 판매사별 상품고시정보 조회
	 * @param goodsVO
	 * @return
	 * @throws Exception
	 */
	public List<SellerGoodsViewVO> getAdminGoodsNotiList(GoodsVO goodsVO) throws Exception{
		
		return mapper.getAdminGoodsNotiList(goodsVO);
	}
	
	/**
	 * 판매사별 상품고시정보 등록
	 * @param goodsVO
	 * @throws Exception
	 */
	public void setRegAdminGoodsNoti(GoodsVO goodsVO) throws Exception{
		mapper.setRegAdminGoodsNoti(goodsVO);
	}
	
	public GdGoods getSellerGoodsInfo(int goodsno) throws Exception {
		return mapper.getSellerGoodsInfo(goodsno);
	}

	public GdGoods getGoodsInfo(int goodsno) throws Exception {
		return mapper.getGoodsInfo(goodsno);
	}
	
	public void insertGoods(GoodsVO vo) throws Exception {
		mapper.insertGoods(vo);
	}
	
	public void deleteGoodsLinkAll(int goodsno) throws Exception {
		mapper.deleteGoodsLinkAll(goodsno);
	}
	
	public void deleteGoodsOption(int goodsno) throws Exception {
		mapper.deleteGoodsOption(goodsno);
	}
	
	public int getGoodsGoodsNoMax() throws Exception {
		return mapper.getGoodsGoodsNoMax();
	}
	
	public void insertGoodsLink(Map<String, Object> map) throws Exception {
		mapper.insertGoodsLink(map);
	}
	private void insertSellerGoodsLink(Map<String, Object> map) throws Exception{
		// TODO Auto-generated method stub
		mapper.insertSellerGoodsLink(map);
	}
	public int insertGoodsAddOption(Map<String, Object> map) throws Exception {
		return mapper.insertGoodsAddOption(map);
	}
	
	public List<Map<String, Object>> getGoodsCategoryList(Map<String, Object> map) throws Exception {
		return mapper.getGoodsCategoryList(map);
	}
	
	public int getGoodsSearchCount(Map<String, Object> map) throws Exception {
		return mapper.getGoodsSearchCount(map);
	}
	
	public List<Map<String, Object>> getGoodsSearchList(SearchGoodsVO searchGoodsVO) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", searchGoodsVO.getName());
		map.put("category", searchGoodsVO.getCategory());
		map.put("goodsnm", searchGoodsVO.getGoodsnm());
		
		searchGoodsVO.setPageSize(6);

		//총건수
		searchGoodsVO.setRowCount(mapper.getGoodsSearchCount(map));	
		//페이지 목록 사이즈
		map.put(CommonConstants.PAGE_SIZE, searchGoodsVO.getPageSize());
		//시작 ROW 번호
		map.put(CommonConstants.ROW_START, searchGoodsVO.getRowStart());
		
		if(logger.isDebugEnabled()) {
			logger.debug("### pageSize :: " + map.get("pageSize") + " pageNo :: " + map.get("pageNo") + " rowStart :: " + map.get("rowStart"));
		}
				
		return mapper.getGoodsSearchList(map);
	}
	
	public void insertGoodsOption(GoodsOptionVO vo) throws Exception {
		mapper.insertGoodsOption(vo);
	}

	public void insertGoodsOption(Map<String, Object> map) throws Exception {
		mapper.insertGoodsOption(map);
	}
	
	public void updateGoodsOptionOptno(Map<String, Object> map) throws Exception {
		mapper.updateGoodsOptionOptno(map);
	}
	private void updateSellerGoodsOptionOptno(Map<String, Object> map) {
		// TODO Auto-generated method stub
		mapper.insertSellerGoodsOption(map);
	}

	private void insertSellerGoodsOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		mapper.updateSellerGoodsOptionOptno(map);
	}

	public void deleteGoodsAddOption(int goodsno) throws Exception {
		mapper.deleteGoodsAddOption(goodsno);
	}
	
	public List<Map<String, Object>> getGoodsLinkEventConnectList(int goodsno) throws Exception {
		return mapper.getGoodsLinkEventConnectList(goodsno);
	}
	
	public List<Map<String, Object>> getGoodsDisplayCount(int goodsno) throws Exception {
		return mapper.getGoodsDisplayCount(goodsno);
	}
	
	public List<Map<String, Object>> getGoodsDisplayCount(Map<String, Object> map) throws Exception {
		return mapper.getGoodsDisplayCount(map);
	}
	
	public void insertGoodsDisplay(Map<String, Object> map) throws Exception {
		mapper.insertGoodsLink(map);
	}
	
	public void updateGoods(HashMap params) throws Exception {
		mapper.updateGoods(params);
	}
	public void updateSellerGoods(HashMap params) throws Exception {
		mapper.updateSellerGoods(params);
	}
	
	public int getCommonContentsCount(String id) {
		return mapper.getCommonContentsCount(id);
	}
	
	public GdCmContents getCommonContents(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", "sno");
		map.put("id", id);
		return mapper.getCommonContents(map);
	}
	
	public void insertComminInsurance(GdCmContents cmContents) {
		mapper.insertComminInsurance(cmContents);
	}
	
	public void updateCommonInsurance(GdCmContents cmContents) {
		mapper.updateCommonInsurance(cmContents);	
	}
	
	public void insertCommonDeliveryInfo(GdCmContents cmContents) {
		mapper.insertCommonDeliveryInfo(cmContents);
	};
	
	public void updateCommonDeliveryInfo(GdCmContents cmContents) {
		mapper.updateCommonDeliveryInfo(cmContents);
		
	}
	
	public List<GdGoods> getGoodsList(GoodsVO goodsVO) throws Exception {
		return mapper.getGoodsList(goodsVO);
	}

	public int getGoodsListCount(GoodsVO goodsVO) throws Exception {
		return mapper.getGoodsListCount(goodsVO);
	}

	public int getGoodsListTotalCount() {
		return mapper.getGoodsListTotalCount();
	}
	
	public List<Map<String, Object>> selectGoodsExcelList(GoodsVO goodsVO) {
		commonMapper.selectRownum();
		return mapper.selectGoodsExcelList(goodsVO);
	}
	
	public int updateGoodsBySpec(HashMap<String, Object> dataMap) throws Exception {
		return mapper.updateGoodsBySpec(dataMap);
	}
	
	public void updateOpen(GoodsVO goodsVO) {
		mapper.updateOpen(goodsVO);
	}
	
	/**
	 * 판매자 추가옵션 조회
	 * @param reqsno
	 * @return
	 */
	public List<GdGoodsAdd> getGoodsAddInfoList(String reqsno) {
		return mapper.getGoodsAddInfoList(reqsno);
	}

	public void copyGoods(HashMap paramsDb) {
		GdGoods gdGoods = mapper.getGoodsInfo(Integer.parseInt((String) paramsDb.get("goodsno")));
		if (null != gdGoods) {
			// 상품번호
			int newGoodsNo = mapper.getGoodsMaxCount() + 1;
			gdGoods.setNewGoodsNo(newGoodsNo + "");
			gdGoods.setOldGoodsNo(gdGoods.getGoodsno() + "");

			// AWS 이미지 복사
			AwsFileUtil awsfileUtil = new AwsFileUtil();
			List<String> img_i = new ArrayList<>();
			for (String img : StringUtil.nvl(gdGoods.getImgi()).split("\\|")) {
				if (!"".equals(img)) {
					String imgnm = FileUtil.getUrlFileName(img);
					awsfileUtil.copy("goods/" + gdGoods.getGoodscd() + "/" + imgnm, "goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm);
					img_i.add(StringUtil.getNotParamURL(awsfileUtil.getFileURL("goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm)));
				}
			}
			//gdGoods.setImgi(String.join("|", img_i));
			gdGoods.setImgi(String.join("", img_i));
			List<String> img_s = new ArrayList<>();
			for (String img : StringUtil.nvl(gdGoods.getImgs()).split("\\|")) {
				if (!"".equals(img)) {
					String imgnm = FileUtil.getUrlFileName(img);
					awsfileUtil.copy("goods/" + gdGoods.getGoodscd() + "/" + imgnm, "goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm);
					img_s.add(StringUtil.getNotParamURL(awsfileUtil.getFileURL("goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm)));
				}
			}
			//gdGoods.setImgs(String.join("|", img_s));
			gdGoods.setImgs(String.join("", img_s));
			List<String> img_l = new ArrayList<>();
			for (String img : StringUtil.nvl(gdGoods.getImgl()).split("\\|")) {
				if (!"".equals(img)) {
					String imgnm = FileUtil.getUrlFileName(img);
					awsfileUtil.copy("goods/" + gdGoods.getGoodscd() + "/" + imgnm, "goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm);
					img_l.add(StringUtil.getNotParamURL(awsfileUtil.getFileURL("goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm)));
				}
			}
			//gdGoods.setImgl(String.join("|", img_l));
			gdGoods.setImgl(String.join("", img_l));
			List<String> img_m = new ArrayList<>();
			for (String img : StringUtil.nvl(gdGoods.getImgm()).split("\\|")) {
				if (!"".equals(img)) {
					String imgnm = FileUtil.getUrlFileName(img);
					awsfileUtil.copy("goods/" + gdGoods.getGoodscd() + "/" + imgnm, "goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm);
					img_m.add(StringUtil.getNotParamURL(awsfileUtil.getFileURL("goods/N" + gdGoods.getNewGoodsNo() + "/" + imgnm)));
				}
			}
			gdGoods.setImgm(String.join("|", img_m));
			
			// 임시 SCM 상품코드
			gdGoods.setGoodscd("N" + newGoodsNo);
			
			//상품정보 복사
			gdGoods.setOpen(0);
			mapper.insertGoodsViewCopy(gdGoods);
			
			//상품추가정보 coyp
			mapper.insertGoodsAddCopy2(gdGoods);
			//상품옵션정보 coyp
			mapper.insertGoodsOptionCopy2(gdGoods);
			//상품카테고리정보 coyp
			mapper.insertGoodsLinkCopy2(gdGoods);
		}
	}
	
	/**
	 * 상품일괄관리->빠른이동복사삭제에서 사용
	 * @param paramsDb
	 * @return
	 */
	public int copyGoods2(HashMap paramsDb) {
		List<GdGoods> gdGoodsList = mapper.getGoodsView2(Integer.parseInt((String) paramsDb.get("goodsno")));
		int maxCnt = 0;
		if (null != gdGoodsList) {
			GdGoods gdGoods = gdGoodsList.get(0);
			gdGoods.setGoodsno(Integer.parseInt((String) paramsDb.get("goodsno")));
			
			//상품정보 복사
			gdGoods.setOpen(0);
			mapper.insertGoodsViewCopy(gdGoods);
			
			//상품 no
			maxCnt = mapper.getGoodsMaxCount();
			gdGoods.setNewGoodsNo(maxCnt+"");
			gdGoods.setOldGoodsNo(gdGoods.getGoodsno()+"");
			
			//상품추가정보 coyp
			mapper.insertGoodsAddCopy2(gdGoods);
			
			//상품옵션정보 coyp
			mapper.insertGoodsOptionCopy2(gdGoods);
			
			//상품카테고리정보 coyp
			mapper.insertGoodsLinkCopy2(gdGoods);
		}
		return maxCnt;
	}

	public void deleteGoods(HashMap paramsDb) throws Exception {
		String filePath = ConfigClass.FILE_PATH + "goods/";
		String filePath2 = ConfigClass.FILE_PATH + "recipeimg/";
		String goodsNo = paramsDb.get("goodsno")+"";
		String[] arrGoodsNo = null;
		String[] arrImg_i = null;
		String[] arrImg_m = null;
		String[] arrImg_s = null;
		String[] arrImg_l = null;
		String[] arrImgKey = new String[] { "i", "m", "s", "l" };
	
		int i = 0;
		int j = 0;
		int k1 = 0;
		int k2 = 0;
		int k3 = 0;
		int k4 = 0;
		
		List<GdGoods> gdGoodsList = mapper.getGoodsView((String)paramsDb.get("goodsno"));
		if (null != gdGoodsList) {
			GdGoods gdGoods = gdGoodsList.get(0);
			FileUtil.deleteFile(filePath2, gdGoods.getImgl());
			FileUtil.deleteFile(filePath2, gdGoods.getImgs());
			FileUtil.deleteFile(filePath2, gdGoods.getImgm());
	
			//이벤트 연결 삭제
			mapper.deleteGoodsDisplayAll2(gdGoods);
			//추가옵션 삭제
			mapper.deleteGoodsAddAll(gdGoods);
			//옵션 삭제
			mapper.deleteGoodsOptionAll2(gdGoods);
			//상품 카테고리 연결 삭제
			mapper.deleteGoodsLinkAll(gdGoods);
			//상품정보 삭제
			mapper.deleteGoodsInfo2(gdGoods);
			//상품별 wishlist 삭제
			mapper.deleteMemberWishlistGoods2(gdGoods);
			//판매사 상품정보 삭제
			mapper.deleteSellerGoodsInfo2(gdGoods);
		}
		
		//상품이미지 삭제
		if (null != gdGoodsList) {
			i = 0;
			while (i < gdGoodsList.size()) {
				GdGoods gdGoods = gdGoodsList.get(i);
				// 등록된 이미지 별로 삭제를 수행한다.
				for (j = 0; j < arrImgKey.length; j++) {
					arrImg_i = StringUtil.split(gdGoods.getImgi(), "|");
					arrImg_s = StringUtil.split(gdGoods.getImgs(), "|");
					arrImg_m = StringUtil.split(gdGoods.getImgm(), "|");
					arrImg_l = StringUtil.split(gdGoods.getImgl(), "|");
					
					if (null != arrImg_i) {
						for (k1 = 0; k1 < arrImg_i.length; k1++) {
							logger.debug("arrImg_i["+k1+"]"+filePath+ arrImg_i[k1]);
							FileUtil.deleteFile(filePath, arrImg_i[k1]);
						}
					}
					if (null != arrImg_s) {
						for (k2 = 0; k2 < arrImg_s.length; k2++) {
							logger.debug("arrImg_s["+k2+"]"+filePath+ arrImg_s[k2]);
							FileUtil.deleteFile(filePath, arrImg_s[k2]);
						}
					}
					if (null != arrImg_m) {
						for (k3 = 0; k3 < arrImg_m.length; k3++) {
							logger.debug("arrImg_m["+k3+"]"+filePath+ arrImg_m[k3]);
							FileUtil.deleteFile(filePath, arrImg_m[k3]);
						}
					}
					if (null != arrImg_l) {
						for (k4 = 0; k4 < arrImg_l.length; k4++) {
							logger.debug("arrImg_l["+k4+"]"+filePath+ arrImg_l[k4]);
							FileUtil.deleteFile(filePath, arrImg_l[k4]);
						}
					}
				}
				i++;
			}
		}
		//mapper.deleteGoodsRecipe((String)paramsDb.get("goodsno"));
		//mapper.deleteGoodsTip((String)paramsDb.get("goodsno"));
	}
	
	
	public List<GdGoods> getOriginList() throws Exception {
		return mapper.getOriginList();
	}
	
	public List<GdGoodsBrand> getBrandList() throws Exception {
		return mapper.getBrandList();
	}
	
	public List<String> getSeasonList() throws Exception {
		return mapper.getSeasonList();
	}
	
	public List<GdCategory> getCategoryLinkList(int goodsno) throws Exception {
		return mapper.getCategoryLinkList(goodsno);
	}
	
	public List<GdCategory> getMSellerCategoryLinkList(int goodsno) throws Exception {
		return mapper.getMSellerCategoryLinkList(goodsno);
	}
	
	public List<SellerGoodsViewVO> getSellerCategoryLinkList(String goodsno) throws Exception {
		return sellerMapper.getCategoryLinkList(goodsno);
	}
	
	public List<GdGoodsOption> getGoodsOption(int goodsno) throws Exception {
		return mapper.getGoodsOption(goodsno);
	}
	
	public List<GdGoodsAdd> getGoodsAddOptionList(int goodsno) throws Exception {
		return mapper.getGoodsAddOptionList(goodsno);
	}
	
	public List<Map<String,Object>> getGoodsRelationList(String[] relation) throws Exception {
		return mapper.getGoodsRelationList(relation);
	}

	public int getGoodsMaxCount() throws Exception {
		return mapper.getGoodsMaxCount();
	}

	public void insertGoods(HashMap paramsDb) throws Exception {
		mapper.insertGoods(paramsDb);
		
	}
	public void insertSellerGoods(HashMap paramsDb) throws Exception {
		mapper.insertSellerGoods(paramsDb);
	}

	public void updateOpen(HashMap paramsDb) throws Exception {
		mapper.updateOpen(paramsDb);
	}
	public void updateApproval(HashMap paramsDb) throws Exception {
		List<SellerGoodsViewVO> list = mapper.getSellerGoods(paramsDb);
		List<SellerGoodsViewVO> addoptList = mapper.getSellerAddopt(paramsDb); //판매자가 등록한  추가옵션 가져오기  
		
		if (addoptList.size() > 0) {
			mapper.deleteGoodsAddopt(paramsDb); //상품에 등록된 추가옵션 등록전 제거
		}
		for (int i = 0; i < list.size(); i++) {
			mapper.acceptUpdateGoods(list.get(i));
		}
		
		//판매자가 등록한 추가옵션을 관리자 추가옵션에 넣음
		for (int i = 0; i < addoptList.size(); i++) {
			mapper.insertGoodsAddopt(addoptList.get(i));
		}
		
		mapper.updateApproval(paramsDb);
		mapper.updateSellerApproval(paramsDb);
	}

	public void goodsDB(HttpServletRequest req, MultipartHttpServletRequest mhsq) throws Exception {
		List<Map<String, Object>> rstList = null;	//이벤트 연결상품 조회 결과
		List<Map<String, Object>> cntList = null;	//이벤트 연결상품 count
		List<String> deleteTempFileList = new ArrayList<String>();
		AwsFileUtil awsfileUtil = new AwsFileUtil();
		
		String useSeller = StringUtil.nvl(req.getParameter("useSeller"), "0");	//관리자가 일반적으로 등록하면 0  판매사로 등록하면 1
		String sellerCd = StringUtil.nvl(req.getParameter("schSellerCd"), "0");	//들어온 sellerCd
		logger.debug("useSeller>>" + useSeller + ", sellerCd>>" + sellerCd);
		
		int rstCnt 	= 0;
		int sort	= 0;

		String tmp		= "";	//등록할 상품이미지 파일명 임시
		String imgNm	= "";
		String hiddenFL	= "0";	// hidden 여부
		String optNm	= "";
		String addOptNm	= "";	// 추가옵션명

		String[] arrTmp		= null;
		String[] arrTmp1	= null;
		String[][] arrImg	= new String[][]{{"img_l", ""}, {"img_m", ""}, {"img_s", ""}, {"img_i", ""}}; // 상품이미지 타입
		String[] category	= null;	// 상품카테고리
		String[] eRefer		= null;	// 관련상품

		// 필수 옵션
		String[] option1		= null;
		String[] option2		= null;
		String[] optionConsumer	= null;
		String[] optionPriceMyRitz	= null;
		String[] optionPrice	= null;
		String[] optionPriceRate= null;
		String[] optionSupply	= null;
		String[] optionSupply2	= null;
		String[] optionMargin	= null;
		String[] optionReserve	= null;
		String[] optionStock	= null;
		//1차옵션 재고량 추가 
		String[] option1Stock	= null;
		//상품 설명
		String[] optionExplain	= null;
		// 추가옵션
		String[] addOptionNm	= null;
		String[] addOptionOpt	= null;
		String[] addOptionPrice	= null;
		String stockTmp			= "";

		HashMap<String, Object> paramsDb = new HashMap<String, Object>(); // DB처리를 위한 map
		String orgImgNames = ""; //기등록된 상품 이미지 명
		int goodsNo = Integer.parseInt(req.getParameter("goodsno"));
		String goodscd = StringUtil.nvl(req.getParameter("goodscd"));
		String goodsnm = req.getParameter("goodsnm");
		
		AdminSessionObject adminSO = null;
		
		adminSO = AdminSessionObject.getSessionObject(req);
		String m_id = String.valueOf(adminSO.getUserInfo().getUserId());
		
		//파라미터를 로깅으로 확인하기 위한 처리
		boolean isRegisterParamLogging = false;
		if (isRegisterParamLogging) {
			Enumeration enumber = req.getParameterNames();
			while (enumber.hasMoreElements()) {
				String key = enumber.nextElement().toString();
				String value = req.getParameter(key);
				logger.debug("log.enumber.hasMoreElements key: " + key + " value: " + value);
			}
		}

		if ("register".equals(req.getParameter("mode"))) {
			//등록수 제한 체크
			String maxGoods = ShopConfig.getProperty("maxGoods");
			if (!"unlimited".equals(maxGoods) && Integer.parseInt(maxGoods) < getGoodsListTotalCount()) {
				throw new Exception("상품수 등록이 제한되었습니다");	
			}
		
			//상품일련번호
			goodsNo = getGoodsMaxCount() + 1;
			goodscd = (!"".equals(goodscd) ? goodscd : "N" + goodsNo);
			
			paramsDb.clear();
			
			paramsDb.put("goodsno", goodsNo);
			paramsDb.put("goodscd", goodscd);
			paramsDb.put("goodsnm", "");
			paramsDb.put("goodsnmKR", "");
			paramsDb.put("goodsnmCN", "");
			paramsDb.put("brandno", StringUtil.nvl(req.getParameter("brandno"), "0"));
			paramsDb.put("origin", StringUtil.nvl(req.getParameter("origin"), ""));
			paramsDb.put("binCd", StringUtil.nvl(req.getParameter("binCd"), ""));
			paramsDb.put("simnCd", StringUtil.nvl(req.getParameter("simnCd"), ""));
			paramsDb.put("seasonNm", StringUtil.nvl(req.getParameter("seasonNm"), ""));
			paramsDb.put("euYn", StringUtil.nvl(req.getParameter("euYn"), "Y"));
			paramsDb.put("shippingOrigin", "");
			paramsDb.put("metaTitle", "");
			paramsDb.put("keyword", "");
			paramsDb.put("open", StringUtil.nvl(req.getParameter("open"), "1"));
			paramsDb.put("exTitle", "");
			paramsDb.put("ex1", "");
			paramsDb.put("ex2", "");
			paramsDb.put("ex3", "");
			paramsDb.put("ex4", "");
			paramsDb.put("ex5", "");
			paramsDb.put("ex6", "");
			paramsDb.put("useEmoney", "");
			paramsDb.put("usestock", "");
			paramsDb.put("runout", StringUtil.nvl(req.getParameter("runout"), "0"));
			paramsDb.put("tax", StringUtil.nvl(req.getParameter("tax"), "1"));
			paramsDb.put("strprice", "");
			paramsDb.put("deliveryType", StringUtil.nvl(req.getParameter("deliveryType"), "0"));
			paramsDb.put("usegoodsadd", "");
			paramsDb.put("optnm", "");
			paramsDb.put("opttype", StringUtil.nvl(req.getParameter("opttype"), "single"));
			paramsDb.put("useadd", "");
			paramsDb.put("addoptnm", "");
			paramsDb.put("relationis", StringUtil.nvl(req.getParameter("relationis"), "0"));
			paramsDb.put("relation", "");
			paramsDb.put("imgI", "");
			paramsDb.put("imgS", "");
			paramsDb.put("imgL", "");
			paramsDb.put("imgM", "");
			paramsDb.put("shortdesc", "");
			paramsDb.put("longdesc", "");
			paramsDb.put("memo", "");
			paramsDb.put("mId", StringUtil.nvl(m_id, "0"));
			paramsDb.put("manageYn", StringUtil.nvl(req.getParameter("manageYn"), "N"));
			paramsDb.put("coupon", "");
			paramsDb.put("couponEa", StringUtil.nvl(req.getParameter("couponEa"), "0"));
			paramsDb.put("couponUsecnt", "");
			paramsDb.put("couponDate", "");
			
			if ("approvalRevise".equals(req.getParameter("mode"))) {
				paramsDb.put("statVal", req.getParameter("statVal"));	//승인 값
				paramsDb.put("goodsArr", req.getParameterValues("goodsArr"));
				paramsDb.put("sellercodeArr", req.getParameterValues("sellercodeArr"));
				updateApproval(paramsDb);
			}
			
			logger.debug("@@@paramsDb::" + paramsDb);
			//관리자가 상품등록할때 판매자를 추가했을경우 무조건 승인, 판매자 코드 입력해 저장
			if ("1".equals(useSeller)) {
				logger.debug("sellerCd>>" + sellerCd);
				paramsDb.put("sellerCd", sellerCd);
				paramsDb.put("approvalStatus", "2");
				paramsDb.put("approvalReqCd", "1");

				insertSellerGoods(paramsDb);
				insertGoods(paramsDb);
			} else {
				paramsDb.put("approvalStatus", "2");
				paramsDb.put("approvalReqCd", "1");

				insertGoods(paramsDb);
			}
		} else if ("modify".equals(req.getParameter("mode")) || "approvalRevise".equals(req.getParameter("mode"))) {
			//수정
			int goodsno = Integer.parseInt(req.getParameter("goodsno"));
			goodscd = (!"".equals(goodscd) ? goodscd : "N" + goodsNo);

			//상품 카테고리 - 모두 삭제후 재등록
			deleteGoodsLinkAll(goodsno);
			
			//가격옵션 - 모두 삭제후 재등록
			deleteGoodsOption(goodsno);
		} else if (req.getParameter("mode").equals("open_modify")) {
			//승인변경
			paramsDb.clear();
			paramsDb.put("openVal",req.getParameter("openVal"));
			paramsDb.put("goodsArr",req.getParameter("openVal"));
			updateOpen(paramsDb);
		
		} else if (req.getParameter("mode").equals("delete")) {
			//삭제
			paramsDb.clear();
			paramsDb.put("goodsno",req.getParameter("goodsno"));
			deleteGoods(paramsDb);
		} else if (req.getParameter("mode").equals("copyGoods")) {
			//복사
			paramsDb.clear();
			paramsDb.put("goodsno",req.getParameter("goodsno"));
			copyGoods(paramsDb);
		}
			
		if (!"open_modify".equals(req.getParameter("mode")) || "approvalRevise".equals(req.getParameter("mode"))) {
			//상품 카테고리 링크 연결 등록
			category = req.getParameterValues("category[]");
			if (null != category && 0 < category.length) {
				int categoryLen = category.length;
				for (int i = 0; i < categoryLen; i++) {
					hiddenFL = (ShopLibFunction.getCateHideCnt(category[i]) > 0) ? "1" : "0";
					paramsDb.clear();
					paramsDb.put("goodsno", goodsNo);
					paramsDb.put("category", category[i]);
					paramsDb.put("hidden", hiddenFL);
					if ("1".equals(useSeller)) {
						insertSellerGoodsLink(paramsDb);
					}
					insertGoodsLink(paramsDb);
				}
			}
			
			//상품추가정보
			 
			//적립금
			 
			//가격/재고/배송비
		
			//가격옵션 추가하기 - 옵션명 세팅
			arrTmp = req.getParameterValues("optnm[]");
			if (null != arrTmp) {
				for (int i = 0; i < arrTmp.length; i++ ) {
					optNm += arrTmp[i] + "|";
				}
				optNm = optNm.substring(0, optNm.length() - 1);
			} else {
				optNm = "";
			}
			
			//가격옵션 추가하기
			option1			= req.getParameterValues("opt1[]");
			option2			= req.getParameterValues("opt2[]");
			optionConsumer	= req.getParameterValues("option[consumer][]");
			optionPriceMyRitz		= req.getParameterValues("option[priceMyRitz][]");
			optionPrice		= req.getParameterValues("option[price][]");
			optionPriceRate	= req.getParameterValues("option[priceRate][]");
			optionSupply	= req.getParameterValues("option[supply][]");
			optionSupply2	= req.getParameterValues("option[supply2][]");
			optionMargin	= req.getParameterValues("option[margin][]");
			optionReserve	= req.getParameterValues("option[reserve][]");
			optionStock		= req.getParameterValues("option[stock][]");
			optionExplain	= req.getParameterValues("option[optexplain][]");
			option1Stock	= req.getParameterValues("option[opt1Stock][]");
			
			arrTmp = req.getParameterValues("opt2[]");
			String opttype = StringUtil.nvl(req.getParameter("opttype"), "single");
			int optIdx = 0;
			String goodsOpt2Nm = "";
			String goodsOptNm = "";
			
			if (null != arrTmp) {
				for (String s : arrTmp) {
					goodsOpt2Nm += s + "|";
				}
				goodsOpt2Nm = goodsOpt2Nm.substring(0, goodsOpt2Nm.length() - 1);
			} else {
				goodsOpt2Nm = "";
			}
			
			if (null != optionPrice) {
				//빈값은 제거하기위해넣음
				for (int i = 0; i < option1.length; i++) {
					//추가가격 옵션의 각 로우마다 넘어오는 재고필드의 배열을 추출
					//옵션2가 있는경우와 없는경우로 나눈다.
					if (null != option2) {
						//옵션2 반복문 시작 
						for (int j = 0; j < option2.length; j++) {
							paramsDb.clear();
							goodsOptNm = option1[i];
							//옵션이름에 option2 이름 추가
							goodsOptNm += ("".equals(StringUtil.nvl(option2[j], ""))) ? "" : "/" + option2[j];
							paramsDb.put("goodsno",		goodsNo);
							paramsDb.put("opt1",		option1[i]);
							paramsDb.put("opt2",		option2[j]);	 
							//paramsDb.put("priceMyRitz",		("".equals(StringUtil.nvl(optionPriceMyRitz[i], ""))) ? "0" : optionPriceMyRitz[i]);
							paramsDb.put("consumer",	("".equals(StringUtil.nvl(optionConsumer[i], ""))) ? "0" : optionConsumer[i]); 
							paramsDb.put("price",		("".equals(StringUtil.nvl(optionPrice[i], ""))) ? "0" : optionPrice[i]);
							paramsDb.put("priceRate",	("".equals(StringUtil.nvl(optionPriceRate[i], ""))) ? "0" : optionPriceRate[i]);
							paramsDb.put("supply",		("".equals(StringUtil.nvl(optionSupply[i], ""))) ? "0" : optionSupply[i]);
							paramsDb.put("supply2",		("".equals(StringUtil.nvl(optionSupply2[i], ""))) ? "0" : optionSupply2[i]);
							paramsDb.put("margin",		("".equals(StringUtil.nvl(optionMargin[i], ""))) ? "0" : optionMargin[i]);
							paramsDb.put("reserve",		"0");
							paramsDb.put("optexplain",	("".equals(StringUtil.nvl(optionExplain[i], ""))) ? "" : optionExplain[i]);
							paramsDb.put("parent",		"1");
							if (i == j)
								paramsDb.put("link",	"1");
							else
								paramsDb.put("link",	"0");
							
							stockTmp = StringUtil.nvl(optionStock[optIdx] , "");
							paramsDb.put("optno",		"");
							paramsDb.put("stock",		("".equals(stockTmp) ? 0 : stockTmp));
							
							if (optIdx < optionStock.length) {
								optIdx ++;
							}
							
							//필수옵션등록
							insertGoodsOption(paramsDb);
							
							//필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
							updateGoodsOptionOptno(paramsDb);
							
							if ("1".equals(useSeller)) {
								//판매자로 등록시 필수옵션등록
								insertSellerGoodsOption(paramsDb);
								//판매자로 등록시 필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
								updateSellerGoodsOptionOptno(paramsDb);
							}
						}
						
					} else {
						//일체형 분리형 체크를 하더라도 옵션2가없는경우에는 single형으로 저장 
						opttype = "single";
						//옵션1의 재고량만 저장 .
						paramsDb.clear();
						paramsDb.put("goodsno",		goodsNo);
						paramsDb.put("opt1",		("".equals(StringUtil.nvl(option1[i], ""))) ? "" : option1[i]);
						paramsDb.put("opt2",		"");
						//paramsDb.put("priceMyRitz",		("".equals(StringUtil.nvl(optionPriceMyRitz[i], ""))) ? "0" : optionPriceMyRitz[i]);
						paramsDb.put("consumer",	("".equals(StringUtil.nvl(optionConsumer[i], ""))) ? "0" : optionConsumer[i]);
						paramsDb.put("price",		("".equals(StringUtil.nvl(optionPrice[i], ""))) ? "0" : optionPrice[i]);
						paramsDb.put("priceRate",	("".equals(StringUtil.nvl(optionPriceRate[i], ""))) ? "0" : optionPriceRate[i]);
						paramsDb.put("supply",		("".equals(StringUtil.nvl(optionSupply[i], ""))) ? "0" : optionSupply[i]);
						paramsDb.put("supply2",		("".equals(StringUtil.nvl(optionSupply2[i], ""))) ? "0" : optionSupply2[i]);
						paramsDb.put("margin",		("".equals(StringUtil.nvl(optionMargin[i], ""))) ? "0" : optionMargin[i]);
						paramsDb.put("stock",		StringUtil.nvl(option1Stock[i], "0"));
						//paramsDb.put("reserve",		("".equals(StringUtil.nvl(optionReserve[i], ""))) ? "0" : optionReserve[i]);
						paramsDb.put("reserve",		"0");
						paramsDb.put("link",		"1");
						paramsDb.put("parent",		"1");
						paramsDb.put("optno",		"");
						paramsDb.put("optexplain",	("".equals(StringUtil.nvl(optionExplain[i], ""))) ? "" : optionExplain[i]);
						
						// 필수옵션등록
						insertGoodsOption(paramsDb);
						
						// 필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
						updateGoodsOptionOptno(paramsDb);
						
						if ("1".equals(useSeller)) {
							// 판매자로 등록시 필수옵션등록
							insertSellerGoodsOption(paramsDb);
							
							// 판매자로 등록시 필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
							updateSellerGoodsOptionOptno(paramsDb);
						}
					}
				}
			}
						
			//배송비 데이터 수정
			String deliverytype = StringUtil.nvl(req.getParameter("deliveryType"), "");
			String goodsdelivery = "0";
			if ("3".equals(deliverytype)) {
				goodsdelivery = StringUtil.nvl(req.getParameter("goodsDelivery2"), "0");
			} else if ("2".equals(deliverytype)) {
				goodsdelivery = StringUtil.nvl(req.getParameter("goodsDelivery"), "0");
			}
			logger.debug(">>>>>>type>>>>>>>>>>>" + deliverytype);
			
			//추가옵션/추가상품/사은품
			addOptionNm = req.getParameterValues("addoptnms");
			addOptNm = "";
			
			//추가옵션 사용 여부
			String useadd = req.getParameter("useadd");
			//추가 옵션 사용에 체크되어 있더라도 option의 값이 없으면 사용 안함
			if (null == addOptionNm) {
				useadd = "0";
			}
			
			if (null != addOptionNm && 0 < addOptionNm.length) {
				//삭제 - 사용/사용안함으로 존재하므로 무조건 지우지 말고 입력데이터가 없을 경우만 삭제하도록 한다.
				deleteGoodsAddOption(goodsNo);
				
				//등록
				int k = 0;
				int addOptionNmLen = addOptionNm.length;
				logger.debug(">>>>>>>>>addOptionNmLen>>>>>>>>>>" + addOptionNmLen);
				for (int i = 0; i < addOptionNmLen; i++) {
					rstCnt = 0;
					addOptionOpt = req.getParameterValues("addopt[opt][" + i + "][]");
					addOptionPrice = req.getParameterValues("addopt[addprice][" + i + "][]");
					logger.debug(">>>>>>>>>addOptionOpt>>>>>>>>>>addopt[opt][" + i + "][]@@" + addOptionOpt);
					logger.debug(">>>>>>>>>addOptionOpt>>>>>>>>>>addopt[addprice][" + i + "][]@@" + addOptionPrice);
					if (null != addOptionNm[i] && !"".equals(addOptionNm[i])) {
						logger.debug(">>>>>>>>addOptionNm[" + i + "]>>>>>>>>::" + addOptionNm[i]);
						int addOptionOptLen = addOptionOpt.length;
						for (int j = 0; j < addOptionOptLen; j++) {
							paramsDb.clear();
							paramsDb.put("goodsno", goodsNo);
							paramsDb.put("step", String.valueOf(i));
							paramsDb.put("opt", ("".equals(StringUtil.nvl(addOptionOpt[j], ""))) ? "" : addOptionOpt[j]);
							paramsDb.put("addprice", ("".equals(StringUtil.nvl(addOptionPrice[j], ""))) ? "0" : addOptionPrice[j]);
							rstCnt = insertGoodsAddOption(paramsDb);
						}
						
						if (0 < rstCnt)
							addOptNm += addOptionNm[i] + "^" + StringUtil.nvl(req.getParameter("addoptreq[" + i + "]"), "") + "|";
					}
				}
				
				if (!"".equals(addOptNm))
					addOptNm = addOptNm.substring(0, addOptNm.length() - 1);
			}
			logger.debug(">>>>addOptNm>>>>>>>>>>::" + addOptNm);
			
			// 필수옵션등록 (기본 상품가격등록)
			paramsDb.put("goodsno", goodsNo);
			paramsDb.put("opt1", "");
			paramsDb.put("opt2", "");
			paramsDb.put("priceMyRitz", StringUtil.N2I(req.getParameter("priceMyRitz")));
			paramsDb.put("consumer", StringUtil.N2D(req.getParameter("consumer")));
			paramsDb.put("price", StringUtil.N2D(req.getParameter("price")));
			paramsDb.put("priceRate", StringUtil.N2D(req.getParameter("priceRate")));
			paramsDb.put("supply", StringUtil.N2D(req.getParameter("supply")));
			paramsDb.put("supply2", StringUtil.N2D(req.getParameter("supply2")));
			paramsDb.put("margin", StringUtil.N2D(req.getParameter("margin")));
			paramsDb.put("stock", StringUtil.N2I(req.getParameter("stock")));
			paramsDb.put("reserve", StringUtil.N2D(req.getParameter("reserve")));
			paramsDb.put("link"	, "1");
			paramsDb.put("parent", "0");
			paramsDb.put("optno", "");
			paramsDb.put("optexplain", "");
			insertGoodsOption(paramsDb);
			
			// 필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
			updateGoodsOptionOptno(paramsDb);
			if ("1".equals(useSeller)) {
				// 판매자로 등록시 필수옵션등록
				insertSellerGoodsOption(paramsDb);
				
				// 판매자로 등록시 필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
				updateSellerGoodsOptionOptno(paramsDb);
			}
			
			//상품추가정보 세팅
			tmp = "";
			for (int i = 0; i < 6; i++) {
				tmp += StringUtil.nvl(req.getParameter("title" + (i + 1)), "") + "|";
			}
			String ex_title = "".equals(tmp) ? "" : tmp.substring(0, tmp.length() - 1);
				
			//관련상품
			eRefer = req.getParameterValues("e_refer[]");
			String tmpERefer = "";
			String relation = "";
			if (null != eRefer && 0 < eRefer.length) {
				for (int i = 0; i < eRefer.length; i++ ) {
					tmpERefer += eRefer[i] + ",";
				}
				tmpERefer = ("".equals(tmpERefer)) ? "" : tmpERefer.substring(0, tmpERefer.length() - 1);
				relation = tmpERefer;
			}
				
			//상품 이미지
			
			//업로드 이미지 세팅
			//삭제할 파일명을 map데이터로 담는다.	
			//상품 이미지 타입 별로 삭제 파일명을 취한다.
			int deleteIdx = 0; //삭제 할 파일명의 element's name이 순차적이지 않아 정렬을 위한 변수 선언
			Enumeration enumber = req.getParameterNames();
			HashMap<String, Object> deletFileMap = new HashMap<String, Object>(); //삭제할 이미지명 
			while (enumber.hasMoreElements()) {
				String key = enumber.nextElement().toString();
				String value = req.getParameter(key);
				for (int i = 0; i < 4; i++) {
					tmp = "";
					imgNm = arrImg[i][0] + "[]";
					int j = 0;
					if (key.matches(".*del\\[" + arrImg[i][0] + "\\].*")) {
						value = req.getParameter(key);
						String delFiletmp = key.substring(key.length() - 1, key.length());
						deletFileMap.put(arrImg[i][0] + deleteIdx, value);
						deleteIdx++;
					}
				}
			}
			
			//업로드할 상품 이미지 파일을 Map데이터로 담는다.
			java.util.Iterator<String> fileNames = mhsq.getFileNames();
			Map<String, ArrayList<String>> uploadFileMap = new HashMap<String, ArrayList<String>>();
			while (fileNames.hasNext()) {
				String fileName = fileNames.next();
				MultipartFile mf = mhsq.getFile(fileName);
				ArrayList<String> orgFileName = new ArrayList<String>();
				orgFileName.add(mf.getOriginalFilename());
				uploadFileMap.put(fileName, orgFileName);
			}
			
			//상품이미지 타입은 4종
			for (int i = 0; i < 4; i++) { 
				tmp = "";
				imgNm = arrImg[i][0] + "[]";	//_form.jsp에서 file element's name 형식 imgNm+숫자(순차증가)
				int j = 0;
				orgImgNames = req.getParameter("orgNames_" + arrImg[i][0]);	//기등록된 상품이미지 데이터
				for (int k = 0; k < deletFileMap.size(); k++) {
					//기등록된 상품 이미지에서 삭제이미지 파일명을 제거한다.
					if (deletFileMap.get(arrImg[i][0] + k) != null) {
						orgImgNames = orgImgNames.replaceAll(((String) deletFileMap.get(arrImg[i][0] + k)).replaceAll("\\[", "\\\\["), "");
					}
				}
				if (!"".equals(orgImgNames)) {
					tmp = orgImgNames;
				}
				
				//상품 이미지 업로드 데이터 생성((기존데이터-삭제데이터)+추가데이터)
				for (int k = 0; k < uploadFileMap.size(); k++) {
					if (uploadFileMap.get(imgNm + k) != null && uploadFileMap.get(imgNm + k).get(0) != "") {
						String fileName = ShopLibFunction.getUniqueKey() + uploadFileMap.get(imgNm + k).get(0);
						uploadFileMap.get(imgNm + k).add(fileName);
						tmp += "|" + fileName;
					}
				}
				
				String[] tempOrgImgName = tmp.split("\\|");
				String tempOrgImgNAme = "";
				for (int k = 0; k < tempOrgImgName.length; k++) {
					if (!tempOrgImgName[k].equals("")) {
						tempOrgImgNAme += "|" + tempOrgImgName[k];
					}
				}
				if (!"".equals(tempOrgImgNAme)) {
					tmp = tempOrgImgNAme;
				}
				arrImg[i][1] = tmp;
			}
			
			//최종 상품 등록 및 수정
			List<String> img_m = new ArrayList<>();
			if (uploadFileMap != null) {
				if (!arrImg[1][1].isEmpty()) {
					for (String fl : arrImg[1][1].split("\\|")) {
						if (fl.indexOf("http://") != -1 || fl.indexOf("https://") != -1) {
							img_m.add(fl);
						}
					}
				}
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < uploadFileMap.size(); j++) {
						if (uploadFileMap.get(arrImg[i][0] + "[]" + j) != null && uploadFileMap.get(arrImg[i][0] + "[]" + j).get(0) != null && uploadFileMap.get(arrImg[i][0] + "[]" + j).get(0) != "") {
							List<MultipartFile> mf = mhsq.getFiles(arrImg[i][0] + "[]" + j);
							for (int k = 0; k < mf.size(); k++) {
								if (uploadFileMap.get(arrImg[i][0] + "[]" + j).get(1) != null && !"".equals(uploadFileMap.get(arrImg[i][0] + "[]" + j).get(1))) {
									//파일 임시경로에 업로드
									File tempFile = FileUtil.tempUploadFile(uploadFileMap.get(arrImg[i][0] + "[]" + j).get(1), mf.get(k).getBytes());
									if (tempFile.exists()) {
										String key = "goods/" + goodscd + "/" + tempFile.getName();
										PutObjectResult awsResult = awsfileUtil.upload(tempFile, key);
										if (StringUtils.isNotBlank(awsResult.getVersionId())) {
											String imgUrl = StringUtil.getNotParamURL(awsfileUtil.getFileURL(key));
											if (StringUtils.equals("img_m", arrImg[i][0])) {
												img_m.add(imgUrl);
											} else {
												//이미지 URL 조회
												arrImg[i][1] = imgUrl;
											}
											deleteTempFileList.add(ConfigClass.UPLOAD_PATH + "temp/" + tempFile.getName());
										}
									}
								}
							}
						}
					}
				}
			}

			
			//원본이미지만 업로드시 나머지 리사이즈 삽입
			if (!arrImg[0][1].isEmpty() && arrImg[1][1].isEmpty() && arrImg[2][1].isEmpty() && arrImg[3][1].isEmpty()) {
				for (String fl : arrImg[0][1].split("\\|")) {
					if (fl.indexOf("http://") != -1 || fl.indexOf("https://") != -1) {
						String imgLnm = FileUtil.getUrlFileName(fl);
						
						//{"img_l", ""}, {"img_m", ""}, {"img_s", ""}, {"img_i", ""}
						if (arrImg[1][1].isEmpty()) {
							String imgMnm = FileUtil.getFileName(imgLnm) + "_0."+ FileUtil.getFileExt(imgLnm);
							awsfileUtil.copy("goods/" + goodscd + "/" + imgLnm, "goods/" + goodscd + "/" + imgMnm);
							img_m.add(URLDecoder.decode(StringUtil.getNotParamURL(awsfileUtil.getFileURL("goods/" + goodscd + "/" + imgMnm)), ConfigClass.ENCODING));
						}
						if (arrImg[2][1].isEmpty() || arrImg[3][1].isEmpty()) {
							String tempFile = ConfigClass.UPLOAD_PATH + "/temp/" + imgLnm;
														
							// URL로 파일 로컬에 다운로드
							InputStream in = new URL(fl).openStream();
							Path imagePath = Paths.get(tempFile);
							Files.copy(in, imagePath);
							
							// 다운로드된 파일을 리사이징
							JpegReader jpegReader = new JpegReader();
							BufferedImage image = jpegReader.readImage(new File(tempFile));
							if (image != null) {
								if (arrImg[2][1].isEmpty())
									arrImg[2][1] = getImageResize(image, goodscd, FileUtil.getFileName(imgLnm) + "_s."+ FileUtil.getFileExt(imgLnm), 300, 300);
								
								if (arrImg[3][1].isEmpty())
									arrImg[3][1] = getImageResize(image, goodscd, FileUtil.getFileName(imgLnm) + "_i."+ FileUtil.getFileExt(imgLnm), 700, 700);
							}
							
							FileUtil.deleteFile(tempFile);
						}
					}
				}
			}
			
		
			//최종 세팅 및 수정
			logger.debug(">>>>>>>>>goodsdelivery>>>>>>>>>>" + goodsdelivery);
			paramsDb.clear();
			
			paramsDb.put("goodsno", goodsNo);
			paramsDb.put("goodscd", goodscd);
			paramsDb.put("sellerCd", sellerCd);
			paramsDb.put("goodsnm", goodsnm);
			paramsDb.put("goodsnmKR", req.getParameter("goodsnmKR"));
			paramsDb.put("goodsnmCN", req.getParameter("goodsnmCN"));
			paramsDb.put("brandno", req.getParameter("brandno"));
			paramsDb.put("origin", req.getParameter("origin"));
			paramsDb.put("binCd", req.getParameter("binCd"));
			paramsDb.put("simnCd", req.getParameter("simnCd"));
			paramsDb.put("seasonNm", req.getParameter("seasonNm"));
			paramsDb.put("euYn", StringUtil.nvl(req.getParameter("euYn"), "Y"));
			paramsDb.put("shippingOrigin", req.getParameter("shippingOrigin"));
			paramsDb.put("metaTitle", StringUtil.nvl(req.getParameter("meta_title"), ""));
			paramsDb.put("keyword", req.getParameter("keyword"));
			paramsDb.put("open", StringUtil.nvl(req.getParameter("open"), "1"));
			paramsDb.put("exTitle", ex_title);
			paramsDb.put("ex1", req.getParameter("ex1"));
			paramsDb.put("ex2", req.getParameter("ex2"));
			paramsDb.put("ex3", req.getParameter("ex3"));
			paramsDb.put("ex4", req.getParameter("ex4"));
			paramsDb.put("ex5", req.getParameter("ex5"));
			paramsDb.put("ex6", req.getParameter("ex6"));
			paramsDb.put("useEmoney", req.getParameter("use_emoney"));
			paramsDb.put("usestock", StringUtil.nvl(req.getParameter("usestock")));
			paramsDb.put("runout", StringUtil.nvl(req.getParameter("runout"), "0"));
			paramsDb.put("tax", req.getParameter("tax"));
			paramsDb.put("strprice", req.getParameter("strprice"));
			paramsDb.put("deliveryType", StringUtil.nvl(req.getParameter("deliveryType"), "0"));
			paramsDb.put("goodsDelivery", goodsdelivery);
			paramsDb.put("usegoodsadd", StringUtil.nvl(req.getParameter("usegoodsadd"), "0"));
			paramsDb.put("optnm", goodsOpt2Nm);
			paramsDb.put("opttype", StringUtil.nvl(req.getParameter("opttype"), "single"));
			paramsDb.put("useadd", useadd);
			paramsDb.put("addoptnm", addOptNm);
			paramsDb.put("relationis", req.getParameter("relationis"));
			paramsDb.put("relation", relation);
			paramsDb.put("imgI", arrImg[3][1].replace("|", ""));
			paramsDb.put("imgS", arrImg[2][1].replace("|", ""));
			paramsDb.put("imgL", arrImg[0][1].replace("|", ""));
			paramsDb.put("imgM", img_m.isEmpty() ? arrImg[1][1] : String.join("|", img_m));
			paramsDb.put("shortdesc", req.getParameter("shortdesc"));
			paramsDb.put("longdesc", req.getParameter("longdesc"));
			paramsDb.put("memo", StringUtil.nvl(req.getParameter("memo")));
			paramsDb.put("approvalStatus", "2");
			
			updateGoods(paramsDb);
			if ("1".equals(useSeller)) {
				updateSellerGoods(paramsDb);
			}
			
			//이벤트 연결상품의 display 저장
			paramsDb.clear();
			paramsDb.put("goodsno", goodsNo);
			rstList = getGoodsLinkEventConnectList(goodsNo);
			Map rtListMap = new HashMap(); 
			if (null != rstList) {
				for(int i = 0; i < rstList.size(); i++ ) {
					rtListMap = rstList.get(i);
					
					if (0 < Integer.parseInt(String.valueOf(rtListMap.get("dis_count")))) {
						paramsDb.clear();
						paramsDb.put("goodsno", rtListMap.get("goodsno"));
						paramsDb.put("mode", rtListMap.get("mode"));
						cntList = getGoodsDisplayCount(paramsDb);
						sort = Integer.parseInt((String) cntList.get(0).get("maxSort"));
						
						if (0 == Integer.parseInt((String) cntList.get(0).get("maxSort"))) {
							paramsDb.put("sort", String.valueOf(++sort));
							insertGoodsDisplay(paramsDb);
						}
					}
				}
			}
		
			/*
			 * ===========================================================================
			 * 파일 이동
			 * 현재 이미지의 경로가 정확하지 않다.
			 * 따라서 config.property에 정의된 경로를 재확정한 후에 로직이 수정되어야 한다.
			 * config.property에 보면 web, was 및 일반 파일 / 이미지 파일 등의 경로명이
			 * 분리되어 있는듯 하다. 정확한 정의와 조정이 필요하다.
			 * ===========================================================================
			 */
			//임시 파일 삭제
			if (!deleteTempFileList.isEmpty()) {
				try {
					FileUtil.deleteFileList(deleteTempFileList);
				} catch (Exception e) {
					logger.error("File Delete Error!!");
					e.printStackTrace();
				}
			}
			
			//AWS파일 삭제
			if (deletFileMap != null && deletFileMap.size() > 0) {
				for (int j = 0; j < deletFileMap.size(); j++) {
					for (int i = 0; i < 4; i++) {
						if (deletFileMap.get(arrImg[i][0] + j) != null && deletFileMap.get(arrImg[i][0] + j) != "") {
							String fileUrl = String.valueOf(deletFileMap.get(arrImg[i][0] + j));
							String awsFileName = FileUtil.getUrlFileName(fileUrl);
							String key = "goods/" + req.getParameter("goodscd") + "/" + awsFileName;
							awsfileUtil.delete(key);
						}
					}
				}
			}
			
			/* for ( i=0 ; i < 4 ; i++ ) {
				for(int idx1=0; idx1<uploadFileMap.size(); idx1++) {
					if(uploadFileMap.get(arrImg[i][0]+"[]"+idx1)!=null&&uploadFileMap.get(arrImg[i][0]+"[]"+idx1).get(0)!=null&&uploadFileMap.get(arrImg[i][0]+"[]"+idx1).get(0)!="") {
						List<MultipartFile> mf = mhsq.getFiles(arrImg[i][0]+"[]"+idx1);
						for (int idx5 = 0; idx5 < mf.size(); idx5++) {
							if(uploadFileMap.get(arrImg[i][0]+"[]"+idx1).get(1)!=null&&!"".equals(uploadFileMap.get(arrImg[i][0]+"[]"+idx1).get(1))) {
								FileUtil.uploadFile3(uploadFileMap.get(arrImg[i][0]+"[]"+idx1).get(1), mf.get(idx5).getBytes());
							}
						}
					}
				}
			}
			
			String[] imgArrTmp = null; 
			for ( i=0 ; i < 4 ; i++ ) {
				
				logger.debug("choilee-arrImg[" + i + "][0]=[" + arrImg[i][0] + "]");
				logger.debug("choilee-arrImg[" + i + "][1]=[" + arrImg[i][1] + "]");
				ArrayList<HashMap<String, String>> list		= null;
				HashMap<String, String>	hm			= null;
				if ( !"".equals(arrImg[i][1]) ) {
					 // 정규식 표현이므로 |를 [] 로 감싸야 한다.
					imgArrTmp = arrImg[i][1].split("[|]");
					if ( !"img_l".equals(arrImg[i][0]) && null != imgArrTmp ) {
						
						for ( j=0 ; j < imgArrTmp.length ; j++ ) {
							list = new ArrayList();
							if ( null != imgArrTmp[j] && !"".equals(imgArrTmp[j])) {
								arrTmp1 = imgArrTmp[j].split("\\.");
							
								logger.debug("choilee-arrTmp1[0]=[" + arrTmp1[0] + "], arrTmp1[1]=[" + arrTmp1[1] + "]");
								
								hm = new HashMap();
								hm.put("FILE_PATH"	, ConfigClass.value("WEB_DIR_PATH") + "shop/data/goods/");
								hm.put("FILE_NAME"	, arrTmp1[0] + "_" + arrImg[i][0]);
								hm.put("FILE_EXT"	, arrTmp1[1]);
								hm.put("FILE_WIDTH"	, "290");
								hm.put("FILE_HEIGHT", "290");
								list.add(hm);
							}
							FileUtil.ThumbNailImageCreate(ConfigClass.value("FILE_PATH") + "goods/", imgArrTmp[j], list);
						}
					}
				}
			} */
		}
	}

	public String xlsUpload(HttpServletRequest req, String fileName)
			throws Exception {
		String result = "";
		HashMap paramsDb = new HashMap();

		Map<String, Object> data = new HashMap();
		List<AdminSellerSearchListVO> sellerList;
		AdminSellerSearchFM adminSellerSearchFM = new AdminSellerSearchFM();
		sellerList = adminService.getSellerSearchList(adminSellerSearchFM);
		String sellerCd;

		// 관리자 등록정보
		AdminSessionObject adminSO = null;
		adminSO = AdminSessionObject.getSessionObject(req);
		// Map<String, Object> xkey = adminSO.getUserInfo().getXkey();
		String m_id = String.valueOf(adminSO.getUserInfo().getUserId());

		// 브랜드 정보
		List<GdGoodsBrand> brandList;
		BrandVO brandVO = null;
		brandList = brandService.getGoodsBrandList(brandVO);

		List<GdCategory> categoryList;
		CategoryVO categoryVO = null;
		categoryList = categoryService.getCategoryList(categoryVO);

		data.put("categoryList", categoryList);
		data.put("brandList", brandList);
		data.put("fileName", fileName);
		// 판매자 코드
		data.put("sellerList", sellerList);
		List<Map> list = new ExcelDataLoad().getExcelDataInfo(data);
		int success = 0;

		for (int i = 0; i < list.size() - 1; i++) {

			Map map = list.get(i);
			int goodsNo = getGoodsMaxCount() + 1;
			sellerCd = (String) map.get("판매사");

			// 입력한 값이 있으면 옵션사용(1) 없으면 옵션사용하지않음(0)
			String useadd = "".equals(map.get("추가상품제목")) ? "0" : "1";

			// 관련상품방식이 수동인경우
			if ("0".equals(map.get("관련상품방식"))) {
				map.put("관련상품번호", "");
			}

			String deliveryType = (String) map.get("배송타입");
			String opttype = "".equals(map.get("옵션출력방식")) ? "single" : (String) map.get("옵션출력방식");
			String brandno = (String) map.get("브랜드번호");
			String goodsDelivery = (String) map.get("배송비");
			
			if("0".equals(deliveryType) || "1".equals(deliveryType)) {
				goodsDelivery = "0";
			}

			paramsDb.clear();
			paramsDb.put("goodsno", goodsNo); 
			paramsDb.put("sellerCd", sellerCd);
			paramsDb.put("goodsnm", map.get("상품명(영문)"));
			paramsDb.put("goodsnmKR", map.get("상품명(국문)"));
			paramsDb.put("goodsnmCN", map.get("상품명(중문)"));
			paramsDb.put("metaTitle", map.get("타이틀태그설정"));
			paramsDb.put("goodscd", map.get("상품코드"));
			paramsDb.put("origin", map.get("원산지"));
			paramsDb.put("brandno", brandno); 
			paramsDb.put("open", map.get("상품출력여부"));
			paramsDb.put("runout", map.get("품절상품"));
			paramsDb.put("deliveryType", map.get("배송타입"));
			paramsDb.put("goodsDelivery", goodsDelivery); 
			paramsDb.put("keyword", map.get("유사검색어"));
			paramsDb.put("strprice", map.get("가격대체문구"));
			paramsDb.put("tax", map.get("과세/비과세"));
			paramsDb.put("shortdesc", map.get("짧은설명"));
			paramsDb.put("longdesc", map.get("상품설명"));
			paramsDb.put("imgI", map.get("메인이미지"));
			paramsDb.put("imgS", map.get("리스트이미지"));
			paramsDb.put("imgL", map.get("확대이미지"));
			paramsDb.put("imgM", map.get("상세이미지"));
			paramsDb.put("opttype", opttype);
			paramsDb.put("useEmoney", map.get("적립금"));
			paramsDb.put("addoptnm", map.get("추가상품제목"));
			paramsDb.put("memo", map.get("관리메모"));
			paramsDb.put("exTitle", map.get("상품추가정보제목"));
			paramsDb.put("ex1", map.get("상품추가정보1"));
			paramsDb.put("ex2", map.get("상품추가정보2"));
			paramsDb.put("ex3", map.get("상품추가정보3"));
			paramsDb.put("ex4", map.get("상품추가정보4"));
			paramsDb.put("ex5", map.get("상품추가정보5"));
			paramsDb.put("ex6", map.get("상품추가정보6"));
			paramsDb.put("relationis", map.get("관련상품방식"));
			paramsDb.put("relation", map.get("관련상품번호"));
			paramsDb.put("usestock", map.get("재고량연동"));
			paramsDb.put("couponEa", "0");
			paramsDb.put("mId", StringUtil.nvl(m_id, "0"));
			paramsDb.put("coupon", "");
			paramsDb.put("couponDate", "");
			paramsDb.put("couponUsecnt", "");
			paramsDb.put("useadd", useadd);
			paramsDb.put("optnm", "");
			paramsDb.put("goodsDelivery", goodsDelivery);
			paramsDb.put("usegoodsadd", "0");

			logger.debug("@@@paramsDb::" + paramsDb);
			// 관리자가 상품등록할때 판매자를 추가했을경우 무조건 승인 , 판매자 코드 입력해 저장
			if (!"".equals(sellerCd)) {
				logger.debug("sellerCd>>" + sellerCd);
				paramsDb.put("sellerCd", sellerCd);
				paramsDb.put("approvalStatus", "2");
				paramsDb.put("approvalReqCd", "1");

				insertSellerGoods(paramsDb);
				insertGoods(paramsDb);
			} else {
				insertGoods(paramsDb);
			}

			 // ========= 가격옵션 추가하기 - 옵션명 세팅
			
			paramsDb.clear();
			paramsDb.put("goodsno", goodsNo);
			paramsDb.put("opt1", "");
			paramsDb.put("opt2", "");
			paramsDb.put("consumer", map.get("리테일가"));
			paramsDb.put("price", map.get("즉시할인가"));
			paramsDb.put("priceRate", map.get("즉시할인율"));
			paramsDb.put("supply", map.get("바잉원가(정책)"));
			paramsDb.put("supply2", map.get("바잉원가(DATA)"));
			paramsDb.put("margin", map.get("공헌이익율"));
			paramsDb.put("stock", map.get("재고량"));
			paramsDb.put("link", "1");
			paramsDb.put("parent", "0");
			paramsDb.put("optno", "");
			paramsDb.put("optexplain", "");

			// 필수옵션등록
			insertGoodsOption(paramsDb);
			// 필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
			updateGoodsOptionOptno(paramsDb);

			if (!"".equals(sellerCd)) {
				// 판매자로 등록시 필수옵션등록
				insertSellerGoodsOption(paramsDb);
				// 판매자로 등록시 필수옵션의 optno update = 등록한 SNO와 같은 값을 입력한다.
				updateSellerGoodsOptionOptno(paramsDb);
			}

			// * ========= 상품 카테고리 링크 연결 등록 등록 / 수정 모두 상품 카테고리 링크를 등록한다.

			paramsDb.clear();
			paramsDb.put("goodsno", goodsNo);
			paramsDb.put("category", map.get("상품분류"));
			paramsDb.put("hidden", "0"); // insert 작업 진행중 ???? 
			insertGoodsLink(paramsDb);
			if (!"".equals(sellerCd)) {
				insertSellerGoodsLink(paramsDb);
			}
			
			// =============================== 상품추가정보
			// add 옵션
			// 추가상품목록에 입력값이 있을 시
			if (!"".equals(map.get("추가상품목록"))) {
				int step = 0;
				String addopts = (String) map.get("추가상품목록");
				String[] addopts1 = addopts.split("\\|");
				String[] addopts2 = addopts1[0].split("\\^");
				// 상품이름 가져오기
				String optName = addopts2[0];

				for (int idx = 0; idx < addopts1.length; idx = idx + 1) {
					paramsDb.clear();
					logger.debug("addopts1>>" + addopts1[idx]);
					addopts2 = addopts1[idx].split("\\^");

					paramsDb.put("goodsno", goodsNo);
					logger.debug("optnm>>" + addopts2[0] + ">>" + step
							+ ">>opt>>" + addopts2[1] + ">>addprice>>"
							+ addopts2[2]);
					if (optName.equals(addopts2[0])) {
						paramsDb.put("step", step);
						step = step + 1;
					} else {
						step = 0;
						paramsDb.put("step", step);
						optName = addopts2[0];
					}
					paramsDb.put("opt", addopts2[1]);
					paramsDb.put("addprice", addopts2[2]);
					
					insertGoodsAddOption(paramsDb);
				}
				// 추가상품목록에 값 입력을 하지 않은 경우
			} else {
				paramsDb.clear();
				paramsDb.put("goodsno", goodsNo);
				paramsDb.put("step", "0");
				paramsDb.put("opt", "");
				paramsDb.put("addprice", "0");
				insertGoodsAddOption(paramsDb);
			}

			//=============================== 최종 상품 등록 및 수정

			paramsDb.clear();
			paramsDb.put("goodsno", goodsNo); 
			paramsDb.put("sellerCd", sellerCd);
			paramsDb.put("goodsnm", map.get("상품명(영문)"));
			paramsDb.put("goodsnmKR", map.get("상품명(국문)"));
			paramsDb.put("goodsnmCN", map.get("상품명(중문)"));
			paramsDb.put("metaTitle", map.get("타이틀태그설정"));
			paramsDb.put("goodscd", map.get("상품코드"));
			paramsDb.put("origin", map.get("원산지"));
			paramsDb.put("brandno", brandno); 
			paramsDb.put("open", map.get("상품출력여부"));
			paramsDb.put("runout", map.get("품절상품"));
			paramsDb.put("deliveryType", map.get("배송타입"));
			paramsDb.put("goodsDelivery", goodsDelivery); 
			paramsDb.put("keyword", map.get("유사검색어"));
			paramsDb.put("strprice", map.get("가격대체문구"));
			paramsDb.put("tax", map.get("과세/비과세"));
			paramsDb.put("shortdesc", map.get("짧은설명"));
			paramsDb.put("longdesc", map.get("상품설명"));
			paramsDb.put("imgI", map.get("메인이미지"));
			paramsDb.put("imgS", map.get("리스트이미지"));
			paramsDb.put("imgL", map.get("확대이미지"));
			paramsDb.put("imgM", map.get("상세이미지"));
			paramsDb.put("opttype", opttype);
			paramsDb.put("useEmoney", map.get("적립금"));
			paramsDb.put("addoptnm", map.get("추가상품제목"));
			paramsDb.put("memo", map.get("관리메모"));
			paramsDb.put("exTitle", map.get("상품추가정보제목"));
			paramsDb.put("ex1", map.get("상품추가정보1"));
			paramsDb.put("ex2", map.get("상품추가정보2"));
			paramsDb.put("ex3", map.get("상품추가정보3"));
			paramsDb.put("ex4", map.get("상품추가정보4"));
			paramsDb.put("ex5", map.get("상품추가정보5"));
			paramsDb.put("ex6", map.get("상품추가정보6"));
			paramsDb.put("relationis", map.get("관련상품방식"));
			paramsDb.put("relation", map.get("관련상품번호"));
			paramsDb.put("usestock", map.get("재고량연동"));
			paramsDb.put("couponEa", "0");
			paramsDb.put("mId", StringUtil.nvl(m_id, "0"));
			paramsDb.put("coupon", "");
			paramsDb.put("couponDate", "");
			paramsDb.put("couponUsecnt", "");
			paramsDb.put("useadd", useadd);
			paramsDb.put("optnm", "");
			paramsDb.put("goodsDelivery", goodsDelivery);
			paramsDb.put("usegoodsadd", "0");
			updateGoods(paramsDb);
			if ("1".equals(sellerCd)) {
				paramsDb.put("approvalStatus", "2");
				updateSellerGoods(paramsDb);
			}
			success = success + 1;
		}

		result = result + Integer.toString(success) + "건 성공!\n";

		int lastidx = list.size() - 1;

		// 유효성검사에서 실패한 데이터 목록
		if (list.get(lastidx).get("valiList") != null) {
			String failList = (String) list.get(lastidx).get("valiList")
					.toString();
			logger.debug("failList>>" + list.get(lastidx).get("valiList"));
			result = result + "업로드에 실패한 목록 "
					+ failList.replace("insertfail>", "") + "을 다시 확인해보세요";
		}

		return result;
	}
	
	/**
	 * 이미지 리사이즈
	 * @param image
	 * @param goodsCd
	 * @param fileName
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getImageResize(Image image, String goodsCd, String fileName, int width, int height) {

		String imgUrl = "";
				
		// 원본 이미지 사이즈 가져오기
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);

		//넓이 기준
		double ratio;
		if (imageWidth > imageHeight) {
			ratio = (double) width/(double)imageWidth;
			width = (int)(imageWidth * ratio);
			height = (int)(imageHeight * ratio);
		//높이기준
		}else if(imageWidth < imageHeight) {
			ratio = (double) height/(double)imageHeight;
			width = (int)(imageWidth * ratio);
			height = (int)(imageHeight * ratio);
		}
		
		if (width != 0 && height != 0) {
			String savePath = ConfigClass.UPLOAD_PATH + "/temp/" + fileName;
			try {
				// 이미지 리사이즈
				// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
				// Image.SCALE_FAST : 이미지 부드러움보다 속도 우선
				// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
				// Image.SCALE_SMOOTH : 속도보다 이미지 부드러움을 우선
				// Image.SCALE_AREA_AVERAGING : 평균 알고리즘 사용
				Image resizeImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	
				// 새 이미지 저장하기
				BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				ImageIO.write(newImage, FileUtil.getFileExt(fileName), new File(savePath));
				
				File file = new File(savePath);
				if (file.exists()) {
					AwsFileUtil awsfileUtil = new AwsFileUtil();
					String key = "goods/" + goodsCd + "/" + fileName;
					PutObjectResult awsResult = awsfileUtil.upload(file, key);

					if (StringUtils.isNotBlank(awsResult.getVersionId()) ) {
						//이미지 URL 조회
						imgUrl = StringUtil.getNotParamURL(awsfileUtil.getFileURL(key));
						
						//로컬 파일 삭제
						FileUtil.deleteFile(savePath);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return imgUrl;
	}
}
