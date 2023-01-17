package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;



public class MemberGroup extends GdMemberGrp{
	
	private int cnt = 0;
	
	@Override
	public int getCnt() {
		return cnt;
	}
	@Override
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
