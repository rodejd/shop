package com.wepinit.wepinit_shop.xmall.admin.service.promotion;

import com.wepinit.wepinit_shop.xmall.admin.dao.promotion.MainCampaignMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.MainCampaignVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMainCampaign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MainCampaignService {
	
	@Resource
	MainCampaignMapper mapper;
	
	public int selectMainCampaignTotalCount() throws Exception {
		MainCampaignVO vo = new MainCampaignVO();
		return mapper.selectMainCampaignCount(vo);
	}	
	
	public int selectMainCampaignCount(MainCampaignVO vo) throws Exception {
		return mapper.selectMainCampaignCount(vo);
	}
	
	public List<GdMainCampaign> selectMainCampaignList(MainCampaignVO vo) throws Exception {
		return mapper.selectMainCampaignList(vo);
	}
	
	public GdMainCampaign selectMainCampaign(int sno) throws Exception {
		return mapper.selectMainCampaign(sno);
	}
	
	public int insertMainCampaign(MainCampaignVO vo) throws Exception {
		return mapper.insertMainCampaign(vo);
	}
	
	public int updateMainCampaign(MainCampaignVO vo) throws Exception {
		return mapper.updateMainCampaign(vo);

	}
	public int updateMainCampaignByImage(MainCampaignVO vo) throws Exception {
		return mapper.updateMainCampaignByImage(vo);
	}
	
	public int deleteMainCampaign(int sno) throws Exception {
		return mapper.deleteMainCampaign(sno);
	}

}
