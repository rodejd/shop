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
	 * ??????????????? > ??????
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
		
		// ?????? ????????? ?????? if(logger.isDebugEnabled()) ??????
		if(logger.isDebugEnabled()){
			logger.debug("main" + frontMypageVO);
		}
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		
		
		// ????????????
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("m_id", shopSO.getUserInfo().getUserId());
		param.put("mno",  shopSO.getUserInfo().getM_no());
		GdMember memberInfo = frontMemberService.frontMemberInfo(param);
		frontMypageVO.setFrontMember(memberInfo);
		
		//????????????
		OrderOrderitem orderStepInfo = service.getFrontOrderCountStep(param);
		model.addAttribute("orderStepInfo", orderStepInfo);
				
		//???????????? (MAX:6)
		frontMypageVO.setFrontMemberGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel()));
		frontMypageVO.setFrontMemberMaxGrp(frontMemberService.frontMemberGrpByLevel(6));
		if (memberInfo.getKlevel() < 6) {
			frontMypageVO.setFrontMemberNxtGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel() + 1));
		}
		
		//????????????
		param = new HashMap<String, Object>();
		param.put("mno", shopSO.getUserInfo().getM_no());
		param.put("skin", ConfigClass.getSkin(req));
		param.put("status", "1");
		frontMypageVO.setFrontCouponCount(service.getFrontCouponCount(param));
		
		//?????? ??????
		FrontMypageBoardVO myBoardVO = new FrontMypageBoardVO();
		myBoardVO.setMno(shopSO.getUserInfo().getM_no());
		frontMypageVO.setFrontReviewList(service.getFrontMyPageReviewList(myBoardVO));
		frontMypageVO.setFrontReviewCount(myBoardVO.getRowCount());
		
		//???????????????
		myBoardVO.setPageSize(4);
		frontMypageVO.setFrontWishCount(service.getFrontMyPageWishCount(myBoardVO));
		frontMypageVO.setFrontWishList(service.getFrontMyPageWishList(myBoardVO));
		
		//????????????
		FrontGoodsCartsVO cartVO = new FrontGoodsCartsVO();
		frontGoodsService.goodsCartsLoginY(shopSO.getUserInfo(), cartVO);
		frontMypageVO.setFrontGoodsCartList(cartVO.getInVOList());
		
		//?????? ????????????
		myBoardVO.setPageSize(3);
		frontMypageVO.setFrontGoodsQnaList(service.getFrontMyPageGoodsQnaList(myBoardVO));
		
		return "/shop/mypage/main_index";
	}

	/**
	 * ?????????????????? > ????????????
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
		//????????????/???????????? ??????
		Map<String, Object> param = new HashMap<String, Object>();
		
		//?????????????????????
		param.put("mno",  shopSO.getUserInfo().getM_no());
		param.put("stype", "order");
		
		//????????????( ??????????????????(all), ???????????????(deli_1), ?????????(deli_2), ????????????(deli_3), ??????/??????/??????(cancel) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear(), "") ) ){
			frontMypageVO.setSear("all");
		}
		param.put("sear",  frontMypageVO.getSear() );
		
		//????????????( 1??????(week), 1??????(month_1), 3??????(month_3), 6??????(month_6), 12??????(month_12) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear2(), "") ) ){
			frontMypageVO.setSear2("week");
		}
		param.put("sear2",  frontMypageVO.getSear2() );
		
		
		//???????????? > ??????????????????
		OrderOrderitem orderStepInfo = service.getFrontOrderCountStep(param);
		model.addAttribute("orderStepInfo", orderStepInfo);
		
		//??????/?????? ????????? ?????????
		List<GdListDelivery> deliveryList = service.getUseDeliveryCompList(param);
		model.addAttribute("deliveryList", deliveryList);
		
		//?????????
		frontMypageVO.setRowCount( service.getFrontOrderCount(param) );
		
		//????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize() );
		
		//?????? ROW ??????
		param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
		
		//????????????/???????????? ??????
		frontMypageVO.setFrontOrderList( service.getFrontOrderList(param) );
		
		return "/shop/mypage/mypage_orderlist";
	}
	
	/**
	 * ?????????????????? > ??????/?????? ??????
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
		//????????????/???????????? ??????
		Map<String, Object> param = new HashMap<String, Object>();
		
		//?????????????????????
		param.put("mno",  shopSO.getUserInfo().getM_no());
		param.put("stype", "cancel");
		
		//????????????( ??????????????????(all), ???????????????(deli_1), ?????????(deli_2), ????????????(deli_3), ??????/??????/??????(cancel) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear(), "") ) ){
			frontMypageVO.setSear("all");
		}
		param.put("sear",  frontMypageVO.getSear() );
		
		//????????????( 1??????(week), 1??????(month_1), 3??????(month_3), 6??????(month_6), 12??????(month_12) )
		if( "".equals( StringUtil.nvl(frontMypageVO.getSear2(), "") ) ){
			frontMypageVO.setSear2("week");
		}
		param.put("sear2",  frontMypageVO.getSear2() );
		
		//???????????? > ??????????????????
		OrderOrderitem orderStepInfo = service.getFrontOrderCountStep(param);
		model.addAttribute("orderStepInfo", orderStepInfo);
		
		//?????????
		frontMypageVO.setRowCount( service.getFrontOrderCount(param) );
		
		//????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize() );
		
		//?????? ROW ??????
		param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
		
		//????????????/???????????? ??????
		frontMypageVO.setFrontOrderList( service.getFrontOrderList(param) );
		
		return "/shop/mypage/mypage_cancellist";
	}
	
	/**
	 * ?????????????????? > ????????????
	 * @param frontMypageVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mypage_orderinfo")
	public String mypage_orderinfo(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//??????????????????1(??????????????????)
		frontMypageVO.setFrontOrderViewList( service.getFrontOrderViewList(frontMypageVO) );
		
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", frontMypageVO.getOrdno());
		param.put("mno", userInfo.getM_no());
		
		//??????????????????(????????????)
		Map<String, Object> orderInfo = service.getFrontOrderView(param);
		model.addAttribute("orderInfo", orderInfo);
		
		if(orderInfo == null) {
			return "redirect:/shop/mypage/mypage_orderlist";
		}
		
		List<Map<String, Object>> orderItemList = service.getFrontOrderItemList(param);
		model.addAttribute("orderItemList", orderItemList);
		
		//??????????????????
		frontMypageVO.setFrontCouponList( service.getUsedCouponList(frontMypageVO.getOrdno()));
		
		model.addAttribute("m_no", userInfo.getM_no());
		
		return "/shop/mypage/mypage_orderinfo";
	}
	
	/**
	 * ?????????????????? > ????????????&???????????? ??????
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
			model.addAttribute("resMsg", "M01"); //???????????? ?????????????????????.
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
				model.addAttribute("resMsg", "M02");// ????????? ???????????????.
				return "dojson";
			}
			
			// ????????????(????????????)
			if("cancel".equals(frontMypageVO.getMode())) {
				param.put("step2", "56"); //????????????
				param.put("istep", "56"); //????????????
				
				// ??????????????? ??????,????????????,????????? ?????? ??????
				shopLibFncService.orderCancelProc(param);
				
				tmpltCode = "ritzmall_04";
			//???????????? [ (???????????? or ???????????? ???????????? or ??????????????? or ?????????????????? or ??????????????? or ????????????) and ???????????? ]
			}else if("cancelReq".equals(frontMypageVO.getMode())) {
				param.put("step2", "40"); //????????????
				param.put("istep", "40"); //????????????
				
				tmpltCode = "ritzmall_03";
			//???????????? [ (???????????? or ???????????? ???????????? or ??????????????? or ?????????????????? or ??????????????? or ????????????) and ??????????????? ]
			}else if("refund".equals(frontMypageVO.getMode())) {
				param.put("step2", "40"); //????????????
				param.put("istep", "40"); //????????????
				param.put("mode", "sendback"); //????????????
				param.put("bankcode", frontMypageVO.getBankcode() ); //??????????????????
				param.put("bankaccount", frontMypageVO.getBankaccount() ); //????????????????????????
				param.put("bankuser", frontMypageVO.getBankuser() ); //?????????????????????
				
				tmpltCode = "ritzmall_03";
			// ??????/??????
			}else if("return".equals(frontMypageVO.getMode())){
				param.put("step2", "79"); //????????????
				param.put("istep", "79"); //????????????
				
				//???????????????
				if("a".equals(frontMypageVO.getSettlekind())) {
					param.put("mode", "sendback"); //????????????
					param.put("bankcode", frontMypageVO.getBankcode() ); //??????????????????
					param.put("bankaccount", frontMypageVO.getBankaccount() ); //????????????????????????
					param.put("bankuser", frontMypageVO.getBankuser() ); //?????????????????????
				}
				
				tmpltCode = "ritzmall_03";
			}
			
			//order???????????? step2 ??????
			shopLibFncService.updateShopLibOrderStep(param);
			
			//Item???????????? istep ??????
			shopLibFncService.updateShopLibOrderIStep(param);
			
			//???????????? ??????
			int result = service.insertOrderCancel(param);
			
			//????????????????????????
			if(result > 0) {
				Map<String, Object> itemParam = new HashMap<String, Object>();
				itemParam.put("sno", param.get("cancel"));
				itemParam.put("ordno", frontMypageVO.getOrdno());
				itemParam.put("itemsno", frontMypageVO.getSno());
				service.updateOrderItem(itemParam);
				
				// ??????/??????????????? ???????????????, ?????? ???????????? ??????
				if("return".equals(frontMypageVO.getMode())){
					Map<String, Object> deliveryParam = new HashMap<String, Object>();
					deliveryParam.put("returnDeliveryCompCd", frontMypageVO.getReturnDeliveryCompCd());
					deliveryParam.put("returnInvoice", frontMypageVO.getReturnInvoice());
					deliveryParam.put("ordno", frontMypageVO.getOrdno());
					service.updateDeliveryReturn(deliveryParam);
				}
				
				/* ????????? ?????? */
				BiztalkUtil biztalk = new BiztalkUtil();
				biztalk.sendAlimTalk(frontMypageVO.getOrdno(), tmpltCode);
			}
			
			model.addAttribute("resCode", true);
			model.addAttribute("result", result);
		} catch (Exception e) {
			model.addAttribute("resCode", false);
			model.addAttribute("resMsg", "M03");// ????????? ??????????????????.
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

		//??????????????????
		Map<String, Object> param = new HashMap<String, Object>();

		//??????????????????(??????[trade],??????[sendback])
		param.put("mode", mode );
		
		// ??????/?????? ???????????? ????????? ????????????.
		// ???????????? ??????????????? ???????????? ??????????????? ????????????.
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		frontMypageVO.setUsrNm((shopSO.isShopLogin()) ? shopSO.getUserInfo().getUserName(): "?????????"); 
 
		if(logger.isDebugEnabled()){
			logger.debug("indb>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>frontMypageVO>>" + frontMypageVO);
		}
		
		//????????????(55)
		if("confirm".equals(mode)){
			//????????????
			param.put("ordno", frontMypageVO.getOrdno() );
			//??????Step
			param.put("step", "" );

			//??????Step2
			param.put("step2", 55 );
			param.put("istep", 55);
			param.put("sno", frontMypageVO.getSno());

			//service.updateOrderStep(param);
			
			//?????? ?????? update
			service.checkReceive(param);
			
			// ?????? ????????? ??????
			
			/* ??????????????? ??????????????? ?????? ?????????????????? ?????? ?????? ???????????? ?????? ????????????  
			 * ShopLibFunction.setGoodsEmoney(frontMypageVO.getOrdno(), 1);
			List<GdCouponOrder> couponOrder = null;
			if ("y".equals(ShopConfig.getProperty("emoney_useyn"))) {
				couponOrder = shopLibFncService.getFrontCouponOrderList(param);
				shopLibFncService.insertLogEmoney(couponOrder); 
				shopLibFncService.updateMemberEmoney(couponOrder);
			}
			if ( 0 != rtList_coupon_emoney ) {
				// ???????????? ????????? ??????
				setEmoney(rtList_m_no, rtList_coupon_emoney, Long.parseLong(ordno), "?????? ????????? ??????");
			}
			// ???????????? ?????? ??????
			ShopLibFunction.setGoodsCoupon(frontMypageVO.getOrdno());
			
			//?????? ???????????? ???????????? > ????????????
	       	service.insertOrderLog(frontMypageVO.getOrdno() ,"4","55" ,req); 
	       	
	       	*/
	   		
		
		//????????????
		}else if("sendback".equals(mode)){
			if(logger.isDebugEnabled()){
				logger.debug("### ???????????? ###");
			}
			service.sendback(frontMypageVO);
	
			//?????? ???????????? ???????????? > ????????????
        	//service.insertOrderLog(frontMypageVO.getOrdno() ,"4","41" ,req);
         	
         	//????????? ?????? ?????? 
			//???????????? ???????????? ?????????
		/*	OrderListStepChangeVO ordno = new OrderListStepChangeVO();
			String[] ordnoList =  new String[1];
			ordnoList[0] = frontMypageVO.getOrdno();
			ordno.setOrdnoList(ordnoList); 
			
			List<FrontOrderSettleVO> orderList = orderService.getOrderInfo3(ordno);*/
			
			//?????? ?????? ?????? order????????? ????????????
			/*List<GdSmsAutoSet> list = new ArrayList<GdSmsAutoSet>();
			GdSmsAutoSet vo = new GdSmsAutoSet();
			vo.setSmsType("repay");
			list.add(vo);*/
		
			//???????????? ?????? ??????
			/*List<GdSmsAutoSet> tmpList = smsService.getGdSmsAutoList(vo);
			String sendCheck ="on";
			if(sendCheck.equals(tmpList.get(0).getSendC())){
				//?????? ??????
				for(int i=0; i<orderList.size();i++){
					smsService.smsSend(StringUtil.replaceAll(orderList.get(i).getMobileOrder(), "-", ""), tmpList.get(0).getMsgC());
					logger.debug(">>>>>>>>> ????????? ????????? ????????????");
				};
			}*/
			// ???????????? ?????? ??????

		//????????????
		}else if("trade".equals(mode)){
			if(logger.isDebugEnabled()){
				logger.debug("### ???????????? ###");
			}
			service.trade(frontMypageVO);
		
			//?????? ???????????? ???????????? > ????????????
         //	service.insertOrderLog(frontMypageVO.getOrdno() ,"6","61" ,req);
	
		//????????????
		}else if("cancel".equals(mode)){
			if(logger.isDebugEnabled()){
				logger.debug("### ???????????? ###");
			}

			service.cancelback(frontMypageVO);
			
			//?????? ???????????? ???????????? > ????????????
			//service.insertOrderLog(frontMypageVO.getOrdno() ,"0","40" ,req);
			
			//????????? ?????? ?????? 
			//???????????? ???????????? ?????????
			/*OrderListStepChangeVO ordno = new OrderListStepChangeVO();
			String[] ordnoList =  new String[1];
			ordnoList[0] = frontMypageVO.getOrdno();
			ordno.setOrdnoList(ordnoList); */
			
			//List<FrontOrderSettleVO> orderList = orderService.getOrderInfo3(ordno);
			
			//?????? ?????? ?????? order????????? ????????????
			/*List<GdSmsAutoSet> list = new ArrayList<GdSmsAutoSet>();
			GdSmsAutoSet vo = new GdSmsAutoSet();
			vo.setSmsType("cancle");
			list.add(vo);*/
			
			//???????????? ?????? ??????
			/*List<GdSmsAutoSet> tmpList = smsService.getGdSmsAutoList(vo);
			String sendCheck ="on";
			if(sendCheck.equals(tmpList.get(0).getSendC())){
				//?????? ??????
				for(int i=0; i<orderList.size();i++){
					smsService.smsSend(StringUtil.replaceAll(orderList.get(i).getMobileOrder(), "-", ""), tmpList.get(0).getMsgC());
					logger.debug(">>>>>>>>> ????????? ????????? ????????????");
				};
			}*/
			// ???????????? ?????? ??????
		}

		return "dojson";
	}
		
	@RequestMapping(value="popup_mypage_refund")
	public String popup_mypage_refund(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		// ?????? ????????? ?????? if(logger.isDebugEnabled()) ??????
		if(logger.isDebugEnabled()){
			logger.debug("popup_mypage_refund>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + frontMypageVO);
		}
		
		//1.????????????
		frontMypageVO.setFrontOrderItemObj( service.getFrontOrderItemObj(frontMypageVO) );
		
		return "/shop/mypage/popup_mypage_refund";
	}
	
	@RequestMapping(value="mypage_orderview")
	public String mypage_orderview(@ModelAttribute("frontMypageVO") FrontMypageVO frontMypageVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// ?????? ????????? ?????? if(logger.isDebugEnabled()) ??????
		if(logger.isDebugEnabled()){
			logger.debug("mypage_orderview" + frontMypageVO);
		}
		
		//??????????????????1(??????????????????)
		frontMypageVO.setFrontOrderViewList( service.getFrontOrderViewList(frontMypageVO) );
		
		if(logger.isDebugEnabled()){
			logger.debug("setFrontOrderViewList >> {}" + frontMypageVO.getFrontOrderViewList());
		}
		
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("m_id", userInfo.getUserId());
		
		//??????????????????(???????????????)
		frontMypageVO.setFrontOrderViewAddressObj( frontMemberService.frontMemberInfo(param) );
		
		
		//??????????????????2(????????????)
		frontMypageVO.setFrontOrderViewObj( service.getFrontOrderViewObj(frontMypageVO) );
		
		//??????????????????
		frontMypageVO.setFrontCouponList( service.getUsedCouponList(frontMypageVO.getOrdno()));
		
		model.addAttribute("m_no", userInfo.getM_no());
		
		return "/shop/mypage/mypage_orderview";
	}
	
	
	

	/**
	 * ??????????????? > ???????????? > ?????????
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
		
		//??????
		param.put("skin", ConfigClass.getSkin(req));
		//?????????????????????
		param.put("mno", shopSO.getUserInfo().getM_no());
		//???????????????????????????
		param.put("grp_sno", shopSO.getUserInfo().getXkey().get("grp_sno"));
		//??????????????? ??????
		param.put("status", "1");
		
		//?????????
		frontMypageVO.setRowCount(service.getFrontCouponCount(param));
		
		return "/shop/mypage/mypage_coupon";
	}
	
	/**
	 * ??????????????? > ???????????? > ????????? Ajax
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
		
		//??????
		param.put("skin",  ConfigClass.getSkin(req));
		//?????????????????????
		param.put("mno",  shopSO.getUserInfo().getM_no());
		//???????????????????????????
		param.put("grp_sno",  shopSO.getUserInfo().getXkey().get("grp_sno"));
		
		try {
			//?????????
			frontMypageVO.setRowCount(service.getFrontCouponCount(param));
			//????????? ?????? ?????????
			param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize());
			//?????? ROW ??????
			param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
			
			//????????????
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
	 * ??????????????? > ???????????? > ???????????????
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
		
		// ????????????
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("m_id", shopSO.getUserInfo().getUserId());
		GdMember memberInfo = frontMemberService.frontMemberInfo(param);
		frontMypageVO.setFrontMember(memberInfo);
		
		//???????????? (MAX:6)
		frontMypageVO.setFrontMemberGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel()));
		frontMypageVO.setFrontMemberMaxGrp(frontMemberService.frontMemberGrpByLevel(6));
		if (memberInfo.getKlevel() < 6) {
			frontMypageVO.setFrontMemberNxtGrp(frontMemberService.frontMemberGrpByLevel(memberInfo.getKlevel() + 1));
		}		
		
		//?????????????????????
		param.put("mno", shopSO.getUserInfo().getM_no());
		
		//?????????
		frontMypageVO.setRowCount(service.getFrontEmoneyCount(param));
		
		return "/shop/mypage/mypage_emoney";
	}

	/**
	 * ??????????????? > ???????????? > ??????????????? Ajax
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
		
		//?????????????????????
		param.put("mno", shopSO.getUserInfo().getM_no());
		
		try {
			//?????????
			frontMypageVO.setRowCount(service.getFrontEmoneyCount(param));
			//????????? ?????? ?????????
			param.put(CommonConstants.PAGE_SIZE, frontMypageVO.getPageSize());
			//?????? ROW ??????
			param.put(CommonConstants.ROW_START, frontMypageVO.getRowStart());
			
			//???????????????
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
	 * ??????????????? > ???????????? > ???????????????
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
				logger.debug("???????????????"+myBoardVO.getOptionsList());
				String[] optionsArray = myBoardVO.getOptionsList().split("\\|\\|\\|");
				String goodsno = optionsArray[0];
				String opt1 = optionsArray[1];
				String addopt = optionsArray[2];
				myBoardVO.setGoodsno(StringUtil.nvl(myBoardVO.getGoodsno(), goodsno));
				myBoardVO.setOpt1(opt1);
				myBoardVO.setAddopt(StringUtils.hasText(addopt) && !addopt.equals("NULL") ? addopt : "");
			}
			//??????????????? ??????
			if (service.getFrontMyPageWishListCheckCount(myBoardVO) == 0) {
				service.insertFrontMyPageWishList(myBoardVO);
			}
		}
		
		myBoardVO.setRowCount(service.getFrontMyPageWishCount(myBoardVO));
		myBoardVO.setWishList(service.getFrontMyPageWishList(myBoardVO));
		
		return "/shop/mypage/mypage_wishlist";
	}
	
	/**
	 * ??????????????? ??????
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
			logger.debug("???????????????"+myBoardVO.getOptionsList());
			String[] optionsArray = myBoardVO.getOptionsList().split("\\|\\|\\|");
			String goodsno = optionsArray[0];
			String opt1 = optionsArray[1];
			String addopt = optionsArray[2];
			myBoardVO.setGoodsno(StringUtil.nvl(myBoardVO.getGoodsno(), goodsno));
			myBoardVO.setOpt1(opt1);
			myBoardVO.setAddopt(StringUtils.hasText(addopt) && !addopt.equals("NULL") ? addopt : "");
		}
		//??????????????? ??????
		if (service.getFrontMyPageWishListCheckCount(myBoardVO) == 0) {
			service.insertFrontMyPageWishList(myBoardVO);
			model.addAttribute("code", "1");
		} else {
			model.addAttribute("code", "0");
		}
		
		return "doJson";
	}
	
	/**
	 * ??????????????? > ???????????? > ??????????????? ??????
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
	 * ??????????????? > ???????????? > ?????? ????????????
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
		
		//?????????
		myBoardVO.setRowCount(service.getFrontMyPageGoodsQnaCount(myBoardVO));
		
		//??????
		myBoardVO.setGoodsQnaList(service.getFrontMyPageGoodsQnaList(myBoardVO));
		
		return "/shop/mypage/mypage_qna_goods";
	}
	
	/**
	 * ??????????????? > ???????????? > ????????????
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
	 * 1:1 ?????? ?????????
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
	 * 1:1 ?????? ????????? ????????????
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
			//1:1 ?????? ?????? ??????
			myBoardVO.setQnaObj(service.getFrontMemberQnaInfo(myBoardVO.getSno()));
			myBoardVO.setEmail(myBoardVO.getQnaObj().getEmail());
		}
		
		if("reply_qna".equals(myBoardVO.getMode())){
			//???????????? ??????
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
	 * 1:1 ?????? ????????? ????????????
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
			//1:1 ?????? ?????? ??????
			myBoardVO.setQnaObj(service.getFrontMemberQnaInfo(myBoardVO.getSno()));
			myBoardVO.setEmail(myBoardVO.getQnaObj().getEmail());
		}
		
		if("reply_qna".equals(myBoardVO.getMode())){
			//???????????? ??????
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
	 * 1:1 ?????? ????????????
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
	 * ??????????????? > DB??????
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
		
		//?????? ??????
		myBoardVO.setSkin(ConfigClass.getSkin(req));
		
		//??????????????? ?????? ????????? ?????? ??????????????? ??????????????? ?????? ????????? ??????????????? ?????????
		//????????? ????????? ????????? ????????? ????????? ???????????? ????????? 
		//myBoardVO.setSellerCd(service.getsellerCd(myBoardVO.getOrdno())); 
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		
		if("mod_qna".equals(myBoardVO.getMode())){
			//??????
			service.updateFrontMemberQna(myBoardVO);
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/mypage_qna";
		}else if("reply_qna".equals(myBoardVO.getMode())){
			//?????? ??????
			myBoardVO.setIp(InetAddress.getLocalHost().getHostAddress());
			service.insertFrontMemberQnaReply(myBoardVO);
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/mypage_qna";
		}else if("del_qna".equals(myBoardVO.getMode())){
			//??????
			service.deleteFrontMemberQna(myBoardVO.getSno());
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/popup_mypage_qna_register";
		}else if("add_qna".equals(myBoardVO.getMode())){
			//??????
			if(sessObject.isShopLogin()){
				myBoardVO.setMno(sessObject.getUserInfo().getM_no());
			}else{
				myBoardVO.setMno(0);
			}
			myBoardVO.setIp(InetAddress.getLocalHost().getHostAddress());
			//?????? ??????
			service.insertFrontMemberQna(myBoardVO);
			model.addAttribute("result", 1);
			returnUrl = "/shop/mypage/popup_mypage_qna_register";
		}else if("mod_review".equals(myBoardVO.getMode())){
			//?????? ??????
			service.updateFrontMyPageReview(myBoardVO);
			returnUrl = "redirect:/shop/mypage/mypage_review";
		}else if("del_review".equals(myBoardVO.getMode())){
			//?????? ??????
			service.deleteFrontMyPageReview(myBoardVO.getSno());
			returnUrl = "redirect:/shop/mypage/mypage_review";
		}
		
		return returnUrl;
	}
	
	/**
	 * ???????????????
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
