package com.wepinit.wepinit_shop.xmall.admin.dao.goods;

import com.wepinit.wepinit_shop.xmall.admin.vo.goods.PriceVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEvent;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PriceMapper {
	public List<GdGoodsGoodsoptionGoodslink> getPriceList(PriceVO vo);
	public int getGoodsAllCount(PriceVO vo);
	public void setPriceColumnUpdate(@Param("sno")int sno, @Param("priceColumn")String priceColumn);
	public List<GdEvent> getEventSELECT(PriceVO vo);
	public int getEventCOUNT();
}
