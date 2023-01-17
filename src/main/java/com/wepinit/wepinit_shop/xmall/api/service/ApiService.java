package com.wepinit.wepinit_shop.xmall.api.service;

import com.wepinit.wepinit_shop.xmall.api.dao.ApiMapper;
import com.wepinit.wepinit_shop.xmall.api.vo.OrdApiVO;
import com.wepinit.wepinit_shop.xmall.api.vo.OrdListVO;
import com.wepinit.wepinit_shop.xmall.api.vo.ProductOptionVO;
import com.wepinit.wepinit_shop.xmall.api.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ApiService {
	
	Logger logger = LoggerFactory.getLogger(ApiService.class);
	
	@Resource
	ApiMapper mapper;
	
	public int insertGoods(ProductVO productVO) {
		return mapper.insertGoods(productVO);
	}
	
	public int insertGoodsLink(ProductVO productVO) {
		return mapper.insertGoodsLink(productVO);
	}
	
	public int getGoodsOptionCount(ProductOptionVO productOptionVO) {
		return mapper.getGoodsOptionCount(productOptionVO);
	}
	
	public int insertGoodsOption(ProductOptionVO productOptionVO) {
		return mapper.insertGoodsOption(productOptionVO);
	}
	
	public int updateGoods(ProductVO productVO) {
		return mapper.updateGoods(productVO);
	}
	
	public Integer getGoodsOptionSno(ProductOptionVO productOptionVO) {
		return mapper.getGoodsOptionSno(productOptionVO);
	}

	public int updateGoodsOption(ProductOptionVO productOptionVO) {
		return mapper.updateGoodsOption(productOptionVO);
	}
	
	public int updateGoodsOptionOptno(int sno) {
		return mapper.updateGoodsOptionOptno(sno);
	}
	
	public Integer getGoodsNoByCd(String goodsCd) {
		return mapper.getGoodsNoByCd(goodsCd);
	}
	
	public int updateGoodsStock(ProductOptionVO productOptionVO) {
		return mapper.updateGoodsStock(productOptionVO);
	}
	
	public int updateGoodsStatus(ProductVO productVO) {
		return mapper.updateGoodsStatus(productVO);
	}
	
	public int updateGoodsLowest(ProductVO productVO) {
		return mapper.updateGoodsLowest(productVO);
	}
	
	public int updateGoodsPrice(ProductOptionVO productOptionVO) {
		return mapper.updateGoodsPrice(productOptionVO);
	}
	
	public List<OrdListVO> getOrderSearchList(OrdApiVO ordApiVO) {
		return mapper.getOrderSearchList(ordApiVO);
	}
	
	public OrdListVO getOrderSearchInfo(OrdApiVO ordApiVO) {
		return mapper.getOrderSearchInfo(ordApiVO);
	}
	
	public int updateOrderStatus(OrdApiVO ordApiVO) {
		return mapper.updateOrderStatus(ordApiVO);
	}
	
	public int updateOrderItemStatus(OrdApiVO ordApiVO) {
		return mapper.updateOrderItemStatus(ordApiVO);
	}
}
