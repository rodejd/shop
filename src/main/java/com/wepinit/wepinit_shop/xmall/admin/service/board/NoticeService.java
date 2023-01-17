package com.wepinit.wepinit_shop.xmall.admin.service.board;

import com.wepinit.wepinit_shop.xmall.admin.dao.board.NoticeMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.NoticeSortVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.NoticeVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdNotice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService {
	
	@Resource
	NoticeMapper mapper;
	
	public int getNoticeCount() {
		return mapper.getNoticeCount();
	}
	public int getNoticeSearchCount(NoticeVO vo) {
		return mapper.getNoticeSearchCount(vo);
	}
	public List<GdNotice> getNoticeList(NoticeVO vo) {
		return mapper.getNoticeList(vo);
	}
	public int getSortMax(String itemcd) {
		return mapper.getSortMax(itemcd);
	}
	public void insertNotice(int lastSort, NoticeVO noticeVO) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("noticeVO", noticeVO);
		param.put("sort", lastSort);
		
		mapper.insertNotice(param);
	}
	public int getNoMax(String itemcd){
		return mapper.getNoMax(itemcd);
	}
	public void updateNoticeModify(NoticeVO noticeVO){
		mapper.updateNoticeModify(noticeVO);
	}
	public GdNotice getNoticeDetail(int no){
		return mapper.getNoticeDetail(no);
	}
	public void updateNoticeSortModify(NoticeVO noticeVO){
		mapper.updateNoticeSortModify(noticeVO);
	}
	public void deleteNoticeRegister(int no){
		mapper.deleteNoticeRegister(no);
	}
	public void updateNoticeListSort(int no, int sort){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("no", no);
		param.put("sort", sort);
		mapper.updateNoticeListSort(param);
	}
	public List<Integer> getNoticeExistingSort(int[] no, int[] sort) throws Exception {
		return mapper.getNoticeExistingSort(no, sort);
	}
	
	//update전 Notice 순서(sort) 중복 검사
	public List<Integer> checkNoticeSort(int[] no, int[] sort) throws Exception {
		List<Integer> existingSort = new ArrayList<Integer>();		//Notice 순서 중복값 조회결과 map
		existingSort = this.getNoticeExistingSort(no, sort);

		ArrayList<NoticeSortVO> params = new ArrayList<NoticeSortVO>();
		//paramList setting
		for (int i=0; i<no.length; i++) {
			NoticeSortVO sortVO = new NoticeSortVO();
			sortVO.setNo(no[i]);
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
				this.updateNoticeListSort(params.get(i).getNo(), params.get(i).getSort());
			}	
		}
		
		return existingSort;
	}
	
	public void updateNoticeByFile(NoticeVO noticeVO){
		mapper.updateNoticeByFile(noticeVO);
	}

}
