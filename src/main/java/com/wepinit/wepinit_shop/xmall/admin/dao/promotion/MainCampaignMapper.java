package com.wepinit.wepinit_shop.xmall.admin.dao.promotion;

import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.MainCampaignVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMainCampaign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainCampaignMapper {

	public int selectMainCampaignCount(MainCampaignVO vo);
	
	public List<GdMainCampaign> selectMainCampaignList(MainCampaignVO vo);
	
	public GdMainCampaign selectMainCampaign(int sno);
	
	public int insertMainCampaign(MainCampaignVO vo);
	
	public int updateMainCampaign(MainCampaignVO vo);
	
	public int updateMainCampaignByImage(MainCampaignVO vo);
	
	public int deleteMainCampaign(int sno);
	
}
