package com.wepinit.wepinit_shop.xmall.front.controller.mypage;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.OrderService;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.util.ApplicationContextUtil;
import com.wepinit.wepinit_shop.xmall.common.util.BiztalkUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogEmoney;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.CouponapplyApplymemberCouponCategoryGoodsno;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderOrderitem;
import com.wepinit.wepinit_shop.xmall.front.service.goods.FrontGoodsService;
import com.wepinit.wepinit_shop.xmall.front.service.member.FrontMemberService;
import com.wepinit.wepinit_shop.xmall.front.service.mypage.FrontMypageService;
import com.wepinit.wepinit_shop.xmall.front.vo.goods.FrontGoodsCartsVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageBoardVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageOrderVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageTodayVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/mypage/")
public class FrontMypageController {

	private static final Logger logger = LoggerFactory.getLogger(FrontMypageController.class); 
	
	@Resource
	FrontMypageService service;
	
	@Resource
	FrontMemberService frontMemberService;
	
	@Resource
	OrderService orderService;
	
	@Resource
	FrontGoodsService frontGoodsService;
	
	@Resource
	ShopLibFncService shopLibFncService;
	
	/**
	 * 마이페이지 > 메인
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="main_index")
	public String main_index(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("main" + frontMypageVO);
		}
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		
		
		// 회원정보
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("m_id", shopSO.getUserInfo().getUserId());
		param.put("mno",  shopSO.getUserInfo().getM_no());
		GdMember memberInfo = frontMemberService.frontMemberInfo(param);
		frontMypageVO.setFrontMember(memberInfo);
		
		//주문내역
		OrderOrderitem orderStepInfo = service.getFrontOrderCountStep(param);
		model.addAttribute("orderStepInfo", orderStepInfo);
				
		//등급정보 (MAX:6)
		frontMypageVO.setFrontMemberGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel()));
		frontMypageVO.setFrontMemberMaxGrp(frontMemberService.frontMemberGrpByLevel(6));
		if (memberInfo.getKlevel() < 6) {
			frontMypageVO.setFrontMemberNxtGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel() + 1));
		}
		
		//쿠폰갯수
		param = new HashMap<String, Object>();
		param.put("mno", shopSO.getUserInfo().getM_no());
		param.put("skin", ConfigClass.getSkin(req));
		param.put("status", "1");
		frontMypageVO.setFrontCouponCount(service.getFrontCouponCount(param));
		
		//나의 리뷰
		FrontMypageBoardVO myBoardVO = new FrontMypageBoardVO();
		myBoardVO.setMno(shopSO.getUserInfo().getM_no());
		frontMypageVO.setFrontReviewList(service.getFrontMyPageReviewList(myBoardVO));
		frontMypageVO.setFrontReviewCount(myBoardVO.getRowCount());
		
		//위시리스트
		myBoardVO.setPageSize(4);
		frontMypageVO.setFrontWishCount(service.getFrontMyPageWishCount(myBoardVO));
		frontMypageVO.setFrontWishList(service.getFrontMyPageWishList(myBoardVO));
		
		//장바구니
		FrontGoodsCartsVO cartVO = new FrontGoodsCartsVO();
		frontGoodsService.goodsCartsLoginY(shopSO.getUserInfo(), cartVO);
		frontMypageVO.setFrontGoodsCartList(cartVO.getInVOList());
		
		//나의 문의내역
		myBoardVO.setPageSize(3);
		frontMypageVO.setFrontGoodsQnaList(service.getFrontMyPageGoodsQnaList(myBoardVO));
		
		return "/shop/mypage/main_index";
	}

	/**
	 * 주문배송조회 > 주문내역
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_orderlist")
	public String mypage_orderlist(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		//주문내역/배송조회 목록
		Map<String, Object> param = new HashMap<String, Object>();
		
		//로그인회원번호
		param.put("mno",  shopSO.getUserInfo().getM_no());
		param.put("stype", "order");
		
		//주문상태( 전체주문내역(all), 배송준비중(deli_1), 배송중(deli_2), 배송완료(deli_3), 취소/교환/반품(cancel) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear(), "") ) ){
			frontMypageVO.setSear("all");
		}
		param.put("sear",  frontMypageVO.getSear() );
		
		//주문기간( 1주일(week), 1개월(month_1), 3개월(month_3), 6개월(month_6), 12개월(month_12) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear2(), "") ) ){
			frontMypageVO.setSear2("week");
		}
		param.put("sear2",  frontMypageVO.getSear2() );
		
		
		//주문관리 > 주문배송조회
		OrderOrderitem orderStepInfo = service.getFrontOrderCountStep(param);
		model.addAttribute("orderStepInfo", orderStepInfo);
		
		//반품/환불 택배사 리스트
		List<GdListDelivery> deliveryList = service.getUseDeliveryCompList(param);
		model.addAttribute("deliveryList", deliveryList);
		
		//총건수
		frontMypageVO.setRowCount( service.getFrontOrderCount(param) );
		
		//페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize() );
		
		//시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
		
		//주문내역/배송조회 목록
		frontMypageVO.setFrontOrderList( service.getFrontOrderList(param) );
		
		return "/shop/mypage/mypage_orderlist";
	}
	
	/**
	 * 주문배송조회 > 취소/환불 내역
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_cancellist")
	public String mypage_cancellist(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		//주문내역/배송조회 목록
		Map<String, Object> param = new HashMap<String, Object>();
		
		//로그인회원번호
		param.put("mno",  shopSO.getUserInfo().getM_no());
		param.put("stype", "cancel");
		
		//주문상태( 전체주문내역(all), 배송준비중(deli_1), 배송중(deli_2), 배송완료(deli_3), 취소/교환/반품(cancel) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear(), "") ) ){
			frontMypageVO.setSear("all");
		}
		param.put("sear",  frontMypageVO.getSear() );
		
		//주문기간( 1주일(week), 1개월(month_1), 3개월(month_3), 6개월(month_6), 12개월(month_12) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear2(), "") ) ){
			frontMypageVO.setSear2("week");
		}
		param.put("sear2",  frontMypageVO.getSear2() );
		
		//주문관리 > 주문배송조회
		OrderOrderitem orderStepInfo = service.getFrontOrderCountStep(param);
		model.addAttribute("orderStepInfo", orderStepInfo);
		
		//총건수
		frontMypageVO.setRowCount( service.getFrontOrderCount(param) );
		
		//페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize() );
		
		//시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
		
		//주문내역/배송조회 목록
		frontMypageVO.setFrontOrderList( service.getFrontOrderList(param) );
		
		return "/shop/mypage/mypage_cancellist";
	}
	
	/**
	 * 주문배송조회 > 상세보기
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_orderinfo")
	public String mypage_orderinfo(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//주문상세내역1(주문상품정보)
		frontMypageVO.setFrontOrderViewList( service.getFrontOrderViewList(frontMypageVO) );
		
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", frontMypageVO.getOrdno());
		param.put("mno", userInfo.getM_no());
		
		//주문상세내역(주문정보)
		Map<String, Object> orderInfo = service.getFrontOrderView(param);
		model.addAttribute("orderInfo", orderInfo);
		
		if(orderInfo == null) {
			return "redirect:/shop/mypage/mypage_orderlist";
		}
		
		List<Map<String, Object>> orderItemList = service.getFrontOrderItemList(param);
		model.addAttribute("orderItemList", orderItemList);
		
		//사용쿠폰정보
		frontMypageVO.setFrontCouponList( service.getUsedCouponList(frontMypageVO.getOrdno()));
		
		model.addAttribute("m_no", userInfo.getM_no());
		
		return "/shop/mypage/mypage_orderinfo";
	}
	
	/**
	 * 주문배송조회 > 주문취소&환불요청 처리
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value="mypage_cancel.doJson")
	public String mypage_cancel_dojson(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) {
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		Map<String, Object> param = new HashMap<String, Object>();
		
		if( !shopSO.isShopLogin() ) {
			model.addAttribute("resCode", false);
			model.addAttribute("resMsg", "M01"); //로그인후 이용가능합니다.
			return "dojson";
		}
		try {
			String tmpltCode = "";
			param.put("ordno", frontMypageVO.getOrdno());
			param.put("code", frontMypageVO.getCode());
			param.put("memo", frontMypageVO.getMemo());
			param.put("name", shopSO.getUserInfo().getUserName());
			param.put("mno", shopSO.getUserInfo().getM_no());
			
			Map<String, Object> orderInfo = service.getFrontOrderView(param);
			if(orderInfo == null) {
				model.addAttribute("resCode", false);
				model.addAttribute("resMsg", "M02");// 잘못된 접근입니다.
				return "dojson";
			}
			
			// 주문취소(취소완료)
			if("cancel".equals(frontMypageVO.getMode())) {
				param.put("step2", "56"); //취소완료
				param.put("istep", "56"); //취소완료
				
				// 주문취소시 쿠폰,할인코드,적립금 취소 처리
				shopLibFncService.orderCancelProc(param);
				
				tmpltCode = "ritzmall_04";
			//취소요청 [ (결제완료 or 결제완료 수집완료 or 재고확인중 or 재고확인완료 or 배송준비중 or 입고완료) and 카드결제 ]
			}else if("cancelReq".equals(frontMypageVO.getMode())) {
				param.put("step2", "40"); //취소요청
				param.put("istep", "40"); //취소요청
				
				tmpltCode = "ritzmall_03";
			//취소요청 [ (결제완료 or 결제완료 수집완료 or 재고확인중 or 재고확인완료 or 배송준비중 or 입고완료) and 무통장입금 ]
			}else if("refund".equals(frontMypageVO.getMode())) {
				param.put("step2", "40"); //취소요청
				param.put("istep", "40"); //취소요청
				param.put("mode", "sendback"); //취소요청
				param.put("bankcode", frontMypageVO.getBankcode() ); //환불은행코드
				param.put("bankaccount", frontMypageVO.getBankaccount() ); //환불은행계좌번호
				param.put("bankuser", frontMypageVO.getBankuser() ); //환불계좌예금주
				
				tmpltCode = "ritzmall_03";
			// 반품/환불
			}else if("return".equals(frontMypageVO.getMode())){
				param.put("step2", "79"); //반품신청
				param.put("istep", "79"); //반품신청
				
				//무통장입금
				if("a".equals(frontMypageVO.getSettlekind())) {
					param.put("mode", "sendback"); //환불요청
					param.put("bankcode", frontMypageVO.getBankcode() ); //환불은행코드
					param.put("bankaccount", frontMypageVO.getBankaccount() ); //환불은행계좌번호
					param.put("bankuser", frontMypageVO.getBankuser() ); //환불계좌예금주
				}
				
				tmpltCode = "ritzmall_03";
			}
			
			//order테이블에 step2 수정
			shopLibFncService.updateShopLibOrderStep(param);
			
			//Item테이블에 istep 수정
			shopLibFncService.updateShopLibOrderIStep(param);
			
			//취소내역 등록
			int result = service.insertOrderCancel(param);
			
			//주문정보업데이트
			if(result > 0) {
				Map<String, Object> itemParam = new HashMap<String, Object>();
				itemParam.put("sno", param.get("cancel"));
				itemParam.put("ordno", frontMypageVO.getOrdno());
				itemParam.put("itemsno", frontMypageVO.getSno());
				service.updateOrderItem(itemParam);
				
				// 반품/환불일경우 반품택배사, 반품 송장번호 등록
				if("return".equals(frontMypageVO.getMode())){
					Map<String, Object> deliveryParam = new HashMap<String, Object>();
					deliveryParam.put("returnDeliveryCompCd", frontMypageVO.getReturnDeliveryCompCd());
					deliveryParam.put("returnInvoice", frontMypageVO.getReturnInvoice());
					deliveryParam.put("ordno", frontMypageVO.getOrdno());
					service.updateDeliveryReturn(deliveryParam);
				}
				
				/* 비즈톡 발송 */
				BiztalkUtil biztalk = new BiztalkUtil();
				biztalk.sendAlimTalk(frontMypageVO.getOrdno(), tmpltCode);
			}
			
			model.addAttribute("resCode", true);
			model.addAttribute("result", result);
		} catch (Exception e) {
			model.addAttribute("resCode", false);
			model.addAttribute("resMsg", "M03");// 오류가 발생했습니다.
			e.printStackTrace();
		}
		return "dojson";
	}

//	modify by whpark 20191111
	@RequestMapping(value="indb.dojson", method=RequestMethod.POST)
	public String indb(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO
			, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String mode = frontMypageVO.getMode();
		
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");

		//주문상태처리
		Map<String, Object> param = new HashMap<String, Object>();

		//주문상태변경(교환[trade],반품[sendback])
		param.put("mode", mode );
		
		// 교환/반품 요청자의 이름을 셋팅한다.
		// 비로그인 상태에서의 구매자는 비회원으로 셋팅한다.
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		frontMypageVO.setUsrNm((shopSO.isShopLogin()) ? shopSO.getUserInfo().getUserName(): "비회원"); 
 
		if(logger.isDebugEnabled()){
			logger.debug("indb>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>frontMypageVO>>" + frontMypageVO);
		}
		
		//수령확인(55)
		if("confirm".equals(mode)){
			//주문번호
			param.put("ordno", frontMypageVO.getOrdno() );
			//주문Step
			param.put("step", "" );

			//주문Step2
			param.put("step2", 55 );
			param.put("istep", 55);
			param.put("sno", frontMypageVO.getSno());

			//service.updateOrderStep(param);
			
			//주문 스텝 update
			service.checkReceive(param);
			
			// 주문 적립금 적립
			
			/* 관리자에서 배송완료시 주는 쿠폰과적립금 중복 적용 차단하기 위해 주석처리  
			 * ShopLibFunction.setGoodsEmoney(frontMypageVO.getOrdno(), 1);
			List<GdCouponOrder> couponOrder = null;
			if ("y".equals(ShopConfig.getProperty("emoney_useyn"))) {
				couponOrder = shopLibFncService.getFrontCouponOrderList(param);
				shopLibFncService.insertLogEmoney(couponOrder); 
				shopLibFncService.updateMemberEmoney(couponOrder);
			}
			if ( 0 != rtList_coupon_emoney ) {
				// 적립쿠폰 적립금 적립
				setEmoney(rtList_m_no, rtList_coupon_emoney, Long.parseLong(ordno), "쿠폰 적립금 적립");
			}
			// 구매완료 쿠폰 발급
			ShopLibFunction.setGoodsCoupon(frontMypageVO.getOrdno());
			
			//주문 로그처리 배송완료 > 수령확인
	       	service.insertOrderLog(frontMypageVO.getOrdno() ,"4","55" ,req); 
	       	
	       	*/
	   		
		
		//환불처리
		}else if("sendback".equals(mode)){
			if(logger.isDebugEnabled()){
				logger.debug("### 환불처리 ###");
			}
			service.sendback(frontMypageVO);
	
			//주문 로그처리 배송완료 > 반품접수
        	//service.insertOrderLog(frontMypageVO.getOrdno() ,"4","41" ,req);
         	
         	//여기에 문자 전송 
			//주문번호 리스트에 담아서
		/*	OrderListStepChangeVO ordno = new OrderListStepChangeVO();
			String[] ordnoList =  new String[1];
			ordnoList[0] = frontMypageVO.getOrdno();
			ordno.setOrdnoList(ordnoList); 
			
			List<FrontOrderSettleVO> orderList = orderService.getOrderInfo3(ordno);*/
			
			//문자 발송 부분 order문자만 발송한다
			/*List<GdSmsAutoSet> list = new ArrayList<GdSmsAutoSet>();
			GdSmsAutoSet vo = new GdSmsAutoSet();
			vo.setSmsType("repay");
			list.add(vo);*/
		
			//자동설정 문자 내용
			/*List<GdSmsAutoSet> tmpList = smsService.getGdSmsAutoList(vo);
			String sendCheck ="on";
			if(sendCheck.equals(tmpList.get(0).getSendC())){
				//문자 발송
				for(int i=0; i<orderList.size();i++){
					smsService.smsSend(StringUtil.replaceAll(orderList.get(i).getMobileOrder(), "-", ""), tmpList.get(0).getMsgC());
					logger.debug(">>>>>>>>> 반품후 설정후 문자발송");
				};
			}*/
			// 여기까지 문자 발송

		//교환처리
		}else if("trade".equals(mode)){
			if(logger.isDebugEnabled()){
				logger.debug("### 교환처리 ###");
			}
			service.trade(frontMypageVO);
		
			//주문 로그처리 배송완료 > 교환접수
         //	service.insertOrderLog(frontMypageVO.getOrdno() ,"6","61" ,req);
	
		//취소처리
		}else if("cancel".equals(mode)){
			if(logger.isDebugEnabled()){
				logger.debug("### 취소처리 ###");
			}

			service.cancelback(frontMypageVO);
			
			//주문 로그처리 주문접수 > 취소요청
			//service.insertOrderLog(frontMypageVO.getOrdno() ,"0","40" ,req);
			
			//여기에 문자 전송 
			//주문번호 리스트에 담아서
			/*OrderListStepChangeVO ordno = new OrderListStepChangeVO();
			String[] ordnoList =  new String[1];
			ordnoList[0] = frontMypageVO.getOrdno();
			ordno.setOrdnoList(ordnoList); */
			
			//List<FrontOrderSettleVO> orderList = orderService.getOrderInfo3(ordno);
			
			//문자 발송 부분 order문자만 발송한다
			/*List<GdSmsAutoSet> list = new ArrayList<GdSmsAutoSet>();
			GdSmsAutoSet vo = new GdSmsAutoSet();
			vo.setSmsType("cancle");
			list.add(vo);*/
			
			//자동설정 문자 내용
			/*List<GdSmsAutoSet> tmpList = smsService.getGdSmsAutoList(vo);
			String sendCheck ="on";
			if(sendCheck.equals(tmpList.get(0).getSendC())){
				//문자 발송
				for(int i=0; i<orderList.size();i++){
					smsService.smsSend(StringUtil.replaceAll(orderList.get(i).getMobileOrder(), "-", ""), tmpList.get(0).getMsgC());
					logger.debug(">>>>>>>>> 취소후 설정후 문자발송");
				};
			}*/
			// 여기까지 문자 발송
		}

		return "dojson";
	}
		
	@RequestMapping(value="popup_mypage_refund")
	public String popup_mypage_refund(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("popup_mypage_refund>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + frontMypageVO);
		}
		
		//1.주문상세
		frontMypageVO.setFrontOrderItemObj( service.getFrontOrderItemObj(frontMypageVO) );
		
		return "/shop/mypage/popup_mypage_refund";
	}
	
	@RequestMapping(value="mypage_orderview")
	public String mypage_orderview(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("mypage_orderview" + frontMypageVO);
		}
		
		//주문상세내역1(주문상품정보)
		frontMypageVO.setFrontOrderViewList( service.getFrontOrderViewList(frontMypageVO) );
		
		if(logger.isDebugEnabled()){
			logger.debug("setFrontOrderViewList >> {}" + frontMypageVO.getFrontOrderViewList());
		}
		
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("m_id", userInfo.getUserId());
		
		//주문상세내역(주문자정보)
		frontMypageVO.setFrontOrderViewAddressObj( frontMemberService.frontMemberInfo(param) );
		
		
		//주문상세내역2(주문정보)
		frontMypageVO.setFrontOrderViewObj( service.getFrontOrderViewObj(frontMypageVO) );
		
		//사용쿠폰정보
		frontMypageVO.setFrontCouponList( service.getUsedCouponList(frontMypageVO.getOrdno()));
		
		model.addAttribute("m_no", userInfo.getM_no());
		
		return "/shop/mypage/mypage_orderview";
	}
	
	
	

	/**
	 * 마이페이지 > 혜택관리 > 쿠폰함
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_coupon")
	public String mypage_coupon(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		Map<String, Object> param = new HashMap<String, Object>();
		
		//스킨
		param.put("skin", ConfigClass.getSkin(req));
		//로그인회원번호
		param.put("mno", shopSO.getUserInfo().getM_no());
		//로그인회원그룹번호
		param.put("grp_sno", shopSO.getUserInfo().getXkey().get("grp_sno"));
		//사용가능한 쿠폰
		param.put("status", "1");
		
		//총건수
		frontMypageVO.setRowCount(service.getFrontCouponCount(param));
		
		return "/shop/mypage/mypage_coupon";
	}
	
	/**
	 * 마이페이지 > 혜택관리 > 쿠폰함 Ajax
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value="mypage_coupon.doJson")
	public String mypage_coupon_dojson(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) {
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		Map<String, Object> param = new HashMap<String, Object>();
		
		//스킨
		param.put("skin",  ConfigClass.getSkin(req));
		//로그인회원번호
		param.put("mno",  shopSO.getUserInfo().getM_no());
		//로그인회원그룹번호
		param.put("grp_sno",  shopSO.getUserInfo().getXkey().get("grp_sno"));
		
		try {
			//총건수
			frontMypageVO.setRowCount(service.getFrontCouponCount(param));
			//페이지 목록 사이즈
			param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize());
			//시작 ROW 번호
			param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
			
			//쿠폰목록
			List<CouponapplyApplymemberCouponCategoryGoodsno> couponList = service.getFrontCouponList(param);
			for (int i = 0; i < couponList.size(); i++) {
				if (couponList.get(i).getPrice().contains("%")) {
					if (!"".equals(couponList.get(i).getMaxprice())) {
						couponList.get(i).setMaxprice(ShopLibFunction.getExchange(StringUtil.N2I(couponList.get(i).getMaxprice()), ConfigClass.getSkin(req)));
					}
				} else {
					couponList.get(i).setPrice(ShopLibFunction.getExchange(StringUtil.N2I(couponList.get(i).getPrice()), ConfigClass.getSkin(req)));
				}
				
				if (!"0".equals(couponList.get(i).getExcPrice())) {
					couponList.get(i).setExcPrice(ShopLibFunction.getExchange(StringUtil.N2I(couponList.get(i).getExcPrice()), ConfigClass.getSkin(req)));
				}
			}
			frontMypageVO.setFrontCouponList(couponList);

			model.addAttribute("RESULT", true);
			model.addAttribute("frontMypageVO", frontMypageVO);
		} catch (Exception e) {
			model.addAttribute("RESULT", false);
			model.addAttribute("RESULT_MSG", e.getMessage());
			e.printStackTrace();
		}		
		
		return "dojson";
	}
	
	/**
	 * 마이페이지 > 혜택관리 > 적립금관리
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_emoney")
	public String mypage_emoney(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		
		// 회원정보
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("m_id", shopSO.getUserInfo().getUserId());
		GdMember memberInfo = frontMemberService.frontMemberInfo(param);
		frontMypageVO.setFrontMember(memberInfo);
		
		//등급정보 (MAX:6)
		frontMypageVO.setFrontMemberGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel()));
		frontMypageVO.setFrontMemberMaxGrp(frontMemberService.frontMemberGrpByLevel(6));
		if (memberInfo.getKlevel() < 6) {
			frontMypageVO.setFrontMemberNxtGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel() + 1));
		}		
		
		//로그인회원번호
		param.put("mno", shopSO.getUserInfo().getM_no());
		
		//총건수
		frontMypageVO.setRowCount(service.getFrontEmoneyCount(param));
		
		return "/shop/mypage/mypage_emoney";
	}

	/**
	 * 마이페이지 > 혜택관리 > 적립금관리 Ajax
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value="mypage_emoney.doJson")
	public String mypage_emoney_dojson(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) {
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		Map<String, Object> param = new HashMap<String, Object>();
		
		//로그인회원번호
		param.put("mno", shopSO.getUserInfo().getM_no());
		
		try {
			//총건수
			frontMypageVO.setRowCount(service.getFrontEmoneyCount(param));
			//페이지 목록 사이즈
			param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize());
			//시작 ROW 번호
			param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
			
			//적립금목록
			List<GdLogEmoney> emoneyList = service.getFrontEmoneyList(param);
			for (int i = 0; i < emoneyList.size(); i++) {
				emoneyList.get(i).setExcemoney(ShopLibFunction.getExchange(emoneyList.get(i).getEmoney(), "en"));
			}
			frontMypageVO.setFrontEmoneyList(emoneyList);

			model.addAttribute("RESULT", true);
			model.addAttribute("frontMypageVO", frontMypageVO);
		} catch (Exception e) {
			model.addAttribute("RESULT", false);
			model.addAttribute("RESULT_MSG", e.getMessage());
			e.printStackTrace();
		}		
		
		return "dojson";
	}

	/**
	 * 마이페이지 > 나의쇼핑 > 위시리스트
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_wishlist")
	public String mypageWishList(@ModelAttribute("myBoardVO") FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@ mypage wishlist >> ");
		}
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myBoardVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
		if("addItem".equals(myBoardVO.getMode())){
			if (myBoardVO.getOptionsList() != null && myBoardVO.getOptionsList().length() > 0) {
				logger.debug("상품옵션값"+myBoardVO.getOptionsList());
				String[] optionsArray = myBoardVO.getOptionsList().split("\\|\\|\\|");
				String goodsno = optionsArray[0];
				String opt1 = optionsArray[1];
				String addopt = optionsArray[2];
				myBoardVO.setGoodsno(StringUtil.nvl(myBoardVO.getGoodsno(), goodsno));
				myBoardVO.setOpt1(opt1);
				myBoardVO.setAddopt(StringUtils.hasText(addopt) && !addopt.equals("NULL") ? addopt : "");
			}
			//상품보관함 추가
			if (service.getFrontMyPageWishListCheckCount(myBoardVO) == 0) {
				service.insertFrontMyPageWishList(myBoardVO);
			}
		}
		
		myBoardVO.setRowCount(service.getFrontMyPageWishCount(myBoardVO));
		myBoardVO.setWishList(service.getFrontMyPageWishList(myBoardVO));
		
		return "/shop/mypage/mypage_wishlist";
	}
	
	/**
	 * 위시리스트 추가
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxWishListAdd.doJson")
	public String ajaxWishListAdd(@ModelAttribute("myBoardVO") FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myBoardVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
		if (myBoardVO.getOptionsList() != null && myBoardVO.getOptionsList().length() > 0) {
			logger.debug("상품옵션값"+myBoardVO.getOptionsList());
			String[] optionsArray = myBoardVO.getOptionsList().split("\\|\\|\\|");
			String goodsno = optionsArray[0];
			String opt1 = optionsArray[1];
			String addopt = optionsArray[2];
			myBoardVO.setGoodsno(StringUtil.nvl(myBoardVO.getGoodsno(), goodsno));
			myBoardVO.setOpt1(opt1);
			myBoardVO.setAddopt(StringUtils.hasText(addopt) && !addopt.equals("NULL") ? addopt : "");
		}
		//상품보관함 추가
		if (service.getFrontMyPageWishListCheckCount(myBoardVO) == 0) {
			service.insertFrontMyPageWishList(myBoardVO);
			model.addAttribute("code", "1");
		} else {
			model.addAttribute("code", "0");
		}
		
		return "doJson";
	}
	
	/**
	 * 마이페이지 > 나의쇼핑 > 위시리스트 삭제
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_wishlist_delete")
	public String mypageWishListDelete(@ModelAttribute("myBoardVO") FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		myBoardVO.setMno(sessObject.getUserInfo().getM_no());
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ mypage snoList >> "+myBoardVO.getSnoList());
		}
		service.deleteFrontMyPageWishList(myBoardVO.getMno(), myBoardVO.getSnoList());
		
		return "redirect:/shop/mypage/mypage_wishlist";
	}
	
	/**
	 * 마이페이지 > 나의쇼핑 > 나의 문의내역
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_qna_goods")
	public String mypageQnaGoods(@ModelAttribute("myBoardVO") FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		myBoardVO.setMno(sessObject.getUserInfo().getM_no());
		
		//총건수
		myBoardVO.setRowCount(service.getFrontMyPageGoodsQnaCount(myBoardVO));
		
		//목록
		myBoardVO.setGoodsQnaList(service.getFrontMyPageGoodsQnaList(myBoardVO));
		
		return "/shop/mypage/mypage_qna_goods";
	}
	
	/**
	 * 마이페이지 > 나의쇼핑 > 상품리뷰
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_review")
	public String mypageReview(@ModelAttribute("myBoardVO")FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myBoardVO.setMno(sessObject.getUserInfo().getM_no());
		}
		myBoardVO.setReviewList(service.getFrontMyPageReviewList(myBoardVO));
		
		return "/shop/mypage/mypage_review";
	}
	
	/**
	 * 1:1 문의 게시판
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_qna")
	public String mypageQna(@ModelAttribute("myBoardVO")FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res)throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@mypage_qna list :: "+myBoardVO.getMno());
		}
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		myBoardVO.setMno(sessObject.getUserInfo().getM_no());
		
		myBoardVO.setQnaList(service.getFrontMemberQnaList(myBoardVO));
		
		return "/shop/mypage/mypage_qna";
	}
	
	/**
	 * 1:1 문의 게시판 등록팝업
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_qna_register")
	public String mypageQnaRegister(@ModelAttribute("myBoardVO")FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res)throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@mypage_qna_register ");
		}
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myBoardVO.setMno(sessObject.getUserInfo().getM_no());
			myBoardVO.setMid(String.valueOf(sessObject.getUserInfo().getUserId()));
			myBoardVO.setName(String.valueOf(sessObject.getUserInfo().getUserName()));
			myBoardVO.setEmail(String.valueOf(sessObject.getUserInfo().getXkey().get("email")));
		}
		
		if("mod_qna".equals(myBoardVO.getMode())){
			//1:1 문의 수정 조회
			myBoardVO.setQnaObj(service.getFrontMemberQnaInfo(myBoardVO.getSno()));
			myBoardVO.setEmail(myBoardVO.getQnaObj().getEmail());
		}
		
		if("reply_qna".equals(myBoardVO.getMode())){
			//입력항목 제어
			myBoardVO.setFormtype("reply");
		}
		
		if(myBoardVO.getQnaObj() != null){
			if(myBoardVO.getSno()!=0 && myBoardVO.getQnaObj().getParent()!=0 
					&& myBoardVO.getSno()!=myBoardVO.getQnaObj().getParent() ){
				myBoardVO.setFormtype("reply");
			}
		}
		
		return "/shop/mypage/mypage_qna_register";
	}
	
	/**
	 * 1:1 문의 게시판 등록팝업
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="popup_mypage_qna_register")
	public String popupMypageQnaRegister(@ModelAttribute("myBoardVO")FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res)throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@popup_mypage_qna_register ");
		}
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myBoardVO.setMno(sessObject.getUserInfo().getM_no());
			myBoardVO.setMid(String.valueOf(sessObject.getUserInfo().getUserId()));
			myBoardVO.setName(String.valueOf(sessObject.getUserInfo().getUserName()));
			myBoardVO.setEmail(String.valueOf(sessObject.getUserInfo().getXkey().get("email")));
		}
		
		if("mod_qna".equals(myBoardVO.getMode())){
			//1:1 문의 수정 조회
			myBoardVO.setQnaObj(service.getFrontMemberQnaInfo(myBoardVO.getSno()));
			myBoardVO.setEmail(myBoardVO.getQnaObj().getEmail());
		}
		
		if("reply_qna".equals(myBoardVO.getMode())){
			//입력항목 제어
			myBoardVO.setFormtype("reply");
		}
		
		if(myBoardVO.getQnaObj() != null){
			if(myBoardVO.getSno()!=0 && myBoardVO.getQnaObj().getParent()!=0 
					&& myBoardVO.getSno()!=myBoardVO.getQnaObj().getParent() ){
				myBoardVO.setFormtype("reply");
			}
		}
		
		return "/shop/mypage/popup_mypage_qna_register";
	}
	
	/**
	 * 1:1 문의 삭제팝업
	 * @param myBoardVO
	 * @return
	 */
	@RequestMapping(value="mypage_qna_del_popup")
	public String mypageQnaDelete(@ModelAttribute("myBoardVO")FrontMypageBoardVO myBoardVO){
		if(logger.isDebugEnabled()){
			logger.debug("@@mypage_qna_del_popup ");
		}
		
		return "/shop/mypage/popup_mypage_qna_del";
	}
	
	/**
	 * 마이페이지 > DB처리
	 * @param myBoardVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="indb")
	public String mypageIndb(@ModelAttribute("myBoardVO")FrontMypageBoardVO myBoardVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@mypage indb :: ");
		}
		
		String returnUrl = "";
		
		//언어 설정
		myBoardVO.setSkin(ConfigClass.getSkin(req));
		
		//주문번호로 상품 판매자 코드 가져오는건 여러상품을 동시 주문시 문제가되어 수정함
		//글쓸때 상품을 선택해 판매자 코드를 가져오게 수정함 
		//myBoardVO.setSellerCd(service.getsellerCd(myBoardVO.getOrdno())); 
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		
		if("mod_qna".equals(myBoardVO.getMode())){
			//수정
			service.updateFrontMemberQna(myBoardVO);
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/mypage_qna";
		}else if("reply_qna".equals(myBoardVO.getMode())){
			//답변 등록
			myBoardVO.setIp(InetAddress.getLocalHost().getHostAddress());
			service.insertFrontMemberQnaReply(myBoardVO);
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/mypage_qna";
		}else if("del_qna".equals(myBoardVO.getMode())){
			//삭제
			service.deleteFrontMemberQna(myBoardVO.getSno());
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/popup_mypage_qna_register";
		}else if("add_qna".equals(myBoardVO.getMode())){
			//등록
			if(sessObject.isShopLogin()){
				myBoardVO.setMno(sessObject.getUserInfo().getM_no());
			}else{
				myBoardVO.setMno(0);
			}
			myBoardVO.setIp(InetAddress.getLocalHost().getHostAddress());
			//문의 등록
			service.insertFrontMemberQna(myBoardVO);
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/popup_mypage_qna_register";
		}else if("mod_review".equals(myBoardVO.getMode())){
			//리뷰 수정
			service.updateFrontMyPageReview(myBoardVO);
			returnUrl = "redirect:/shop/mypage/mypage_review";
		}else if("del_review".equals(myBoardVO.getMode())){
			//리뷰 삭제
			service.deleteFrontMyPageReview(myBoardVO.getSno());
			returnUrl = "redirect:/shop/mypage/mypage_review";
		}
		
		return returnUrl;
	}
	
	/**
	 * 최근본상품
	 * @param myTodayVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_today")
	public String mypageToday(@ModelAttribute("myTodayVO")FrontMypageTodayVO myTodayVO,
			HttpServletRequest req, HttpServletResponse res)throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@ mypage today >> {}", myTodayVO);
		}
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myTodayVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
		ArrayList todayGoodsList = WebUtil.getTodayGoodsCookie(req,res);
		String goodsno="";
		for(int i=todayGoodsList.size()-1; i>=0; i--){
			myTodayVO.setGoodsMap((Map<String, String>)todayGoodsList.get(i));
			goodsno += myTodayVO.getGoodsMap().get("goodsno");
			
			if(i != 0){
				goodsno += ",";
			}
		}
		myTodayVO.setGoodsno(goodsno);
		
		if("".equals(myTodayVO.getSort())) {
			myTodayVO.setSort("price");
		}
		
		if(todayGoodsList.size() > 0){
			myTodayVO.setTodayGoodsList(service.getFrontMyPageTodayGoodsList(myTodayVO));	
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("@@ today GoodsList >> "+todayGoodsList);
		}
		
		return "/shop/mypage/mypage_today";
	}
	
	@RequestMapping(value="orderPopup.dojson")
	public String mypageOrderPopup(@ModelAttribute("myBoardVO")FrontMypageBoardVO myBoardVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@ mypage order popup");
		}
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myBoardVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
//		model.addAttribute("orderList", service.getFrontMyPageMemberQnaOrderList(myBoardVO));
		model.addAttribute("myBoardVO", myBoardVO);
		
		return "dojson";
	}
	
	@RequestMapping(value="ajaxOrderList")
	public String ajaxOrderList(@ModelAttribute("myOrderVO")FrontMypageOrderVO myOrderVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@ mypage ajaxOrderList ");
		}
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myOrderVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
		myOrderVO.setOrderList(service.getFrontMyPageMemberQnaOrderList(myOrderVO));
		
		return "/shop/mypage/ajaxOrderList";
	}
	
	@RequestMapping(value="ajaxOrderGoddsList")
	public String ajaxOrderGoddsList(@ModelAttribute("myOrderGoodsVO")FrontMypageBoardVO myOrderGoodsVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@ mypage ajaxOrderGoddsList ");
		}
		
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			myOrderGoodsVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
		myOrderGoodsVO.setOrderGoodsList(service.getFrontMyPageOrderGoddsList(myOrderGoodsVO));
		
		return "/shop/mypage/ajaxOrderGoodsList";
	}
	
}
