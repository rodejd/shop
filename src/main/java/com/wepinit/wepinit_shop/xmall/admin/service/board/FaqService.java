package com.wepinit.wepinit_shop.xmall.admin.service.board;

import com.wepinit.wepinit_shop.xmall.admin.dao.board.FaqMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.FaqSortVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.FaqVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdFaq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FaqService {
	
	private static final Logger logger = LoggerFactory.getLogger(FaqService.class);
	
	@Resource
	FaqMapper mapper;
	
	public int getFaqTotalCount() {
		if(logger.isDebugEnabled()) {
			logger.debug("faqService!!! :: ");
		}
		return mapper.getFaqTotalCount();
	}
	public int getFaqSearchCount(FaqVO vo) {
		return mapper.getFaqSearchCount(vo);
	}
	public List<GdFaq> getFaqList(FaqVO vo) {
		return mapper.getFaqList(vo);
	}
	public int getSortMax(String itemcd) {
		return mapper.getSortMax(itemcd);
	}
	public void insertFapReply(int lastSort, FaqVO faqVO) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("faqVO", faqVO);
		param.put("sort", lastSort);
		
		mapper.insertFapReply(param);
	}
	public int getSnoMax(String itemcd){
		return mapper.getSnoMax(itemcd);
	}
	public void updateFaqModify(FaqVO faqVO){
		mapper.updateFaqModify(faqVO);
	}
	public GdFaq getFaqDetail(int sno){
		return mapper.getFaqDetail(sno);
	}
	public void updateAllFaqModify(FaqVO faqVO){
		mapper.updateAllFaqModify(faqVO);
	}
	public void updateFaqSortModify(FaqVO faqVO){
		mapper.updateFaqSortModify(faqVO);
	}
	public List<GdFaq> getFaqSortSno(String itemcd){
		return mapper.getFaqSortSno(itemcd);
	}
	public void deleteFaqRegister(int sno){
		mapper.deleteFaqRegister(sno);
	}
	private void updateFaqListSort(int sno, int sort){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sno", sno);
		param.put("sort", sort);
		mapper.updateFaqListSort(param);
	}
	private List<Integer> getFaqExistingSort(int[] sno, int[] sort) throws Exception {
		return mapper.getFaqExistingSort(sno, sort);
	}
	
	//update전 faq 순서(sort) 중복 검사
	public List<Integer> checkFaqSort(int[] sno, int[] sort) throws Exception {
		List<Integer> existingSort = new ArrayList<Integer>();		//FAQ 순서 중복값 조회결과 map
		existingSort = this.getFaqExistingSort(sno, sort);

		ArrayList<FaqSortVO> params = new ArrayList<FaqSortVO>();
		//paramList setting
		for (int i=0; i<sno.length; i++) {
			FaqSortVO sortVO = new FaqSortVO();
			sortVO.setSno(sno[i]);
			sortVO.setSort(sort[i]);
			params.add(sortVO);
		}
		
		//순서 중복 값 paramList에서 삭제
		if (null != existingSort && 0 < existingSort.size()) {
			for (int i=0; i<existingSort.size(); i++) {
				for (int j=0; j<params.size(); j++) {
					if (existingSort.get(i) == params.get(j).getSort()) {
						params.remove(j);
						break;
					}
				}
			}
		}
		
		//순서 update
		if (null != params && 0 < params.size()) {
			for (int i=0; i<params.size(); i++) {
				this.updateFaqListSort(params.get(i).getSno(), params.get(i).getSort());
			}	
		}
		
		return existingSort;
	}

}
