package com.wepinit.wepinit_shop.xmall.admin.service.goods;

import com.wepinit.wepinit_shop.xmall.admin.dao.goods.GoodsManageMapper;
import com.wepinit.wepinit_shop.xmall.admin.service.common.AdminSellerSearchService;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsManageVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsManage;
import com.wepinit.wepinit_shop.xmall.seller.goods.dao.SellerGoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsManageService {
	
	@Resource
	GoodsManageMapper mapper;
	
	@Resource
	BrandService brandService;
	
	@Resource
	SellerGoodsMapper sellerMapper;
	
	@Resource
	AdminSellerSearchService adminService;
	
	@Resource
	CategoryService categoryService;
	
	public List<GdGoodsManage> selectGoodsManageList(GoodsManageVO goodsManageVO) {
		return mapper.selectGoodsManageList(goodsManageVO);
	}
	
	public int selectGoodsListCount(GoodsManageVO goodsManageVO) throws Exception {
		return mapper.selectGoodsListCount(goodsManageVO);
	}
	
	public List<GoodsManageVO> selectGoodsDataList(GoodsManageVO goodsManageVO) {
		return mapper.selectGoodsDataList(goodsManageVO);
	}
	
	public int selectGoodsManageCountByBinCd(GoodsManageVO goodsManageVO) throws Exception {
		return mapper.selectGoodsManageCountByBinCd(goodsManageVO);
	}
	
	public int insertGoodsManage(GoodsManageVO goodsManageVO) throws Exception {
		return mapper.insertGoodsManage(goodsManageVO);
	}
	
	public int updateGoodsManage(GoodsManageVO goodsManageVO) throws Exception {
		return mapper.updateGoodsManage(goodsManageVO);
	}
	
	public int deleteGoodsManage(GoodsManageVO goodsManageVO) throws Exception {
		return mapper.deleteGoodsManage(goodsManageVO);
	}
	
	public int updateGoodsManageBymanageYn(HashMap<String, Object> dataMap) throws Exception {
		return mapper.updateGoodsManageBymanageYn(dataMap);
	}


}
