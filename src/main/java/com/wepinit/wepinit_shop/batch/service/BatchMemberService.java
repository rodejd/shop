package com.wepinit.wepinit_shop.batch.service;

import com.wepinit.wepinit_shop.batch.BatchOrder;
import com.wepinit.wepinit_shop.batch.dao.BatchMemberMapper;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchMemberService {
	private static final Logger logger = LoggerFactory.getLogger(BatchOrder.class);
	
	@Resource
	BatchMemberMapper mapper;
	
	/**
	 * 최종 로그인 시점이 1년 지난 유저의 목록을 로그로 출력합니다.
	 * */
	public void printLogLoginExceedPeriodMembers() {
		List<GdMember> members = new ArrayList<GdMember>();
		members = this.getLastLoginExceedPeriodMembers();
		
		for(GdMember member : members) {
			logger.debug(member.toString());
		}
	}
	
	/**
	 * 최종 로그인 시점이 1년이 지난 유저 목록
	 * @return List<GdMember>
	 * */
	public List<GdMember> getLastLoginExceedPeriodMembers() {
		return this.mapper.getLastLoginExceedPeriodMembers();
	}
}
