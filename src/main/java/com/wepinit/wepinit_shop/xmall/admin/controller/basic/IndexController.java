/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.admin.controller.basic;

import com.wepinit.wepinit_shop.xmall.admin.service.basic.IndexService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.IndexVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/shop/admin/basic")
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Resource
	IndexService service;
	
	@RequestMapping(value="index")
	public String main(@ModelAttribute("indexVO") IndexVO indexVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 매출액, 주문건수, 입금확인, 배송완료, 취소/환불/반품
		indexVO.setIndexSalepr(service.getIndexSalepr());
		
		// 상품후기 (건)
		indexVO.setIndexReview(service.getIndexReview());

		// 상품문의 (건)
		indexVO.setIndexQna(service.getIndexQna());
		
		// 1:1문의 (건)
		indexVO.setIndexMqna(service.getIndexMqna());
		
		// 회원가입 (명)
		indexVO.setIndexMember(service.getIndexMember());
		
		// 1:1게시판
		indexVO.setIndexMqnaList(service.getIndexMqnaList());
		
		// 문의게시판
		indexVO.setIndexCooperationList(service.getIndexCooperationList());
		
		// 후기게시판
		indexVO.setIndexGoodsReviewList(service.getIndexGoodsReviewList());
		
		// 최근 등록한 상품
		indexVO.setIndexRecentGoodsRegList(service.getIndexRecentGoodsRegList());
		
		// 진행중인 이벤트
		indexVO.setIndexEventList(service.getIndexEventList());
		
		// 한주간 많이 팔린 상품
		indexVO.setIndexWeekSoldGoodsList(service.getIndexWeekSoldGoodsList());
		
		// 단골고객
		indexVO.setIndexRegularMemberList(service.getIndexRegularMemberList());
		
		//자동문취소
		indexVO.setAutoCancel(service.getAutoCancel());
		
		//$mainpage = 1;
		//
		//# 현재 위치 표시
		//$location = "관리자메인";
		//
		//include "../_header.jsp";
		//@include "../../lib/pgchk.jsp";
		//
		//## pg체크 by aceofcom
		////if (function_exists('pgChk')) pgChk();
		//
		//# 계정용량체크
		//if (function_exists('disk')){
	//		list( $disk_errno, $disk_msg ) = disk();
	//		if ( !empty( $disk_errno ) ){
	//			echo "
	//			<script language='javascript'>
	//			if ( !getCookie( 'blnCookie_disk' ) ) {
	//				var win=popup_return( '../proc/warning_disk_pop.jsp', 'disk_err', 320, 260, 100, 100, 'no' );
	//				win.focus();
	//			}
	//			</script>
	//			";
	//		}
		//}
		//
		////debug($godo);
		//
		//TODO] ### 회원 생일자 SMS => 추후 확인후 진행
		//include "./birth_sms.jsp";
		
		return "shop/admin/basic/index";
	}

}
