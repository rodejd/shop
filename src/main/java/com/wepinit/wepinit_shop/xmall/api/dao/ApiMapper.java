package com.wepinit.wepinit_shop.xmall.api.dao;

import com.wepinit.wepinit_shop.xmall.api.vo.OrdApiVO;
import com.wepinit.wepinit_shop.xmall.api.vo.OrdListVO;
import com.wepinit.wepinit_shop.xmall.api.vo.ProductOptionVO;
import com.wepinit.wepinit_shop.xmall.api.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiMapper {
	
	public int insertGoods(ProductVO productVO);
	
	public int insertGoodsLink(ProductVO productVO);
	
	public int getGoodsOptionCount(ProductOptionVO productOptionVO);
	
	public int insertGoodsOption(ProductOptionVO productOptionVO);
	
	public int updateGoods(ProductVO productVO);
	
	public Integer getGoodsOptionSno(ProductOptionVO productOptionVO);

	public int updateGoodsOption(ProductOptionVO productOptionVO);
	
	public int updateGoodsOptionOptno(int sno);
	
	public Integer getGoodsNoByCd(String goodsCd);
	
	public int updateGoodsStock(ProductOptionVO productOptionVO);
	
	public int updateGoodsStatus(ProductVO productVO);
	
	public int updateGoodsLowest(ProductVO productVO);
	
	public int updateGoodsPrice(ProductOptionVO productOptionVO);
	
	public List<OrdListVO> getOrderSearchList(OrdApiVO ordApiVO);
	
	public OrdListVO getOrderSearchInfo(OrdApiVO ordApiVO);
	
	public int updateOrderStatus(OrdApiVO ordApiVO);
	
	public int updateOrderItemStatus(OrdApiVO ordApiVO);

}
