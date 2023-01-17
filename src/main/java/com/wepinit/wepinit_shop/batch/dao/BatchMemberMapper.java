package com.wepinit.wepinit_shop.batch.dao;

import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BatchMemberMapper {
	/**
	 * 최종 로그인 시점이 1년이 지난 유저 목록
	 * @return List<GdMember>
	 * */
	public List<GdMember> getLastLoginExceedPeriodMembers();
}
