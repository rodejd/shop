package com.wepinit.wepinit_shop.xmall.front.controller.order;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.AgentUtil;
import com.wepinit.wepinit_shop.xcube.util.ConvertUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.login.LoginService;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.AmazonSES;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.util.BiztalkUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.CouponapplyApplymemberCouponCategoryGoodsno;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GoodsGoodsbrand;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberGrp;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderMember;
import com.wepinit.wepinit_shop.xmall.front.service.goods.FrontGoodsService;
import com.wepinit.wepinit_shop.xmall.front.service.order.FrontOrderService;
import com.wepinit.wepinit_shop.xmall.front.vo.order.*;
import lgdacom.XPayClient.XPayClient;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.*;


@Controller
@RequestMapping("/shop/order/*")
public class FrontOrderController {

	private static final Logger logger = LoggerFactory.getLogger(FrontOrderController.class); 
	
	@Autowired 
	ServletContext application;
	@Resource
	FrontOrderService service;
	@Resource
	LoginService lService;
	@Resource
	FrontGoodsService gservice;
		
	@RequestMapping(value="order")
	public String order(@ModelAttribute("frontOrderVO") FrontOrderVO frontOrderVO
			, HttpSession session
			, HttpServletRequest req
			, HttpServletResponse res
			, Model model) throws Exception {

		//Skin 설정
		frontOrderVO.setSkin(ConfigClass.getSkin(req));
		
		String result = "/shop/order/order";
		ShopSessionObject userSO = null;
		String orderitem_mode = "";
		String[] arrGoodsno = null;
		
		/* 
		 * 	asis 에서는 즉시구매 및 장바구니,비회원구매 정보를 모두 쿠키에 저장하고 있다.
		 *  tobe 에서 구분을 위해 회원 즉시구매시 쿠키가 아닌 세션에 정보를 저장하는 것으로 변경.
		 *  asis 에서의 변수명이 oriCookie 였기 때문에 변수명은 변경하지 않음.*/
		
		String oriCookie = "";
		//상품명
		String mode = StringUtil.N2S(frontOrderVO.getMode(), "list"); 

		String[] use = StringUtil.explode(ShopConfig.getProperty("set_use"),"");

		//회원정보 가져오기
		userSO = ShopSessionObject.getSessionObject(req);
		int userM_no = userSO.getUserInfo().getM_no();
		if(session.getAttribute("nonMemberKey") == null && userM_no == 0){
			gservice.getNonMemberKey(session);
		}
		//  로그인 상태인 경우
		if(this.service.isLogin(userSO)){
			// vo에 로그인 유저 정보를 세팅
			this.service.setLoginUserInfo(frontOrderVO, userSO);
			
			//배송지 관리 리스트조회
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("m_no", userM_no);
			List<FrontOrderSettleVO> deliveryList = service.getOrderDeliveryList(paramMap);
			model.addAttribute("deliveryList", deliveryList);
		}

		//res.addCookie(new Cookie("dc", frontOrderVO.getDc()));
		WebUtil.setCookies(res, "dc", frontOrderVO.getDc(), -1);

		if(mode.equals("addItem")){    //상품 페이지에서 바로구매를 했을 때
			String returnString = this.service.buyNow(session, frontOrderVO, model, req, res);
			if(!"".equals(returnString)) {
				return returnString;
			}
			oriCookie = (String)session.getAttribute("orders");
			// 즉시구매일 때 인입
			if(StringUtils.hasText(oriCookie)){
				arrGoodsno = this.service.setBuyNow(oriCookie, frontOrderVO);
			}
			
		}else if(mode.equals("list")){    //장바구니 구매, 비회원으로 구매를 했을 때
			String returnString = this.service.buyOfCartAndNonMembers(frontOrderVO, req, res, session, userSO, model);
			if(!"".equals(returnString)) {
				return returnString;
			}
		}
		
		//장바구니 쿠키 설정
		if("addItem".equals(mode) && "".equals(WebUtils.getCookie(req, "gd_isDirect"))){
			WebUtil.setCookies(res, "gd_isDirect", "1", -1);
		}

		//배송선택
		//List<GdDeliveryPolicy> dpList = service.getDeliveryPolicy();

		//주문번호 생성
		String ordno = Long.toString(System.currentTimeMillis());
		int emoney_base = (int)Math.pow(10, Integer.parseInt(ShopConfig.getProperty("emoney_cut")));
		
		//  현재 해당 컨트롤러 자체를 로그인 필수로 인터셉터이 등록한 상태이기 때문에 조건문 주석처리함.
//		if(guest==null || guest.equals("")){
		List<FrontOrderCartMapVO> cartList10 = null;

		String range = ShopConfig.getProperty("range");			// 중복할인여부(0:쿠폰할인, 회원할인 동시사용  1:회원할인만 사용 2: 쿠폰할인만 사용)
		

		List<String> listGoodsno = new ArrayList<String>();

		List<String> listCategory = new ArrayList<String>();
		String[] arrCategory = null;
		
		List<String> listSellerCd = new ArrayList<String>();
		String[] arrSellerCd = null;

		List<String> listWhere = new ArrayList<String>();
		String[] arrWhere = null;
		String coupon_use = "";
		
		int priceSum2 = 0;
		int memberDc = (int) (StringUtil.N2I(StringUtil.N2S(frontOrderVO.getDc(),"0")) * 0.01);
		int memberTotalDcPrice = 0; // 회원 할인가
		
		// 관리자 설정값이 필요할 때에는 adminSettings VO 에 변수를 추가하고
		FrontOrderAdminSettingVO adminSettings = new FrontOrderAdminSettingVO();
		this.service.setOrderAdminSettings(adminSettings);

		priceSum2 = 0;
		
		// 장바구니 일 때 인입
		if(cartList10 == null) {
			cartList10 = frontOrderVO.getCartList();
		}
		
		if(cartList10 != null) {
			
			// price : 상품원가
			// priceSum : 원가  * 개수 + 옵션가
			for(FrontOrderCartMapVO result1 : cartList10){
				int pricePlusOptPrice = result1.getPriceSum() != null ? StringUtil.N2I(result1.getPriceSum()) : 0;
				// 회원할인가
				// 옵션을 제외한 원가격에만 할인율 적용
				int memberDcPrice = (StringUtil.N2I(result1.getPrice()) * Integer.parseInt(result1.getEa()))* memberDc;
				priceSum2 += pricePlusOptPrice;
				memberTotalDcPrice += memberDcPrice;
			}
			
			frontOrderVO.setPriceSum2(String.valueOf(priceSum2));

			int grpEmoney = 0;
			ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
			if(sessObject.isShopLogin()){
				UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
				model.addAttribute("userInfo", userInfo);
				grpEmoney = (int) userInfo.getXkey().get("add_emoney");
				//예상 적립금
				int reserve = 0;				
				reserve = priceSum2 - ShopLibFunction.getDcprice(String.valueOf(priceSum2), grpEmoney + "%");
				frontOrderVO.setReserveSum(String.format("%.2f", reserve));
			}
		}
		frontOrderVO.setCartList(cartList10);
		
		// 결제 페이지마다 뜨는 정보를 보존하기 위하여 세션에 결제정보 리스트 저장.
		// 결제완료 혹은 결제실패시 리스트 세션에서 삭제 필요.
		session.setAttribute("cartList", cartList10);

		List<GdGoodsLink> glList = service.getFrontOrderCategory(frontOrderVO);
		if(glList.size()>0){
			for(GdGoodsLink goodsLink : glList){
				for(int jj=3;jj<=goodsLink.getClen();jj+=3){
					listCategory.add("'" + goodsLink.getCategory().substring(0, jj) + "'");
				}
			}
			arrCategory = listCategory.toArray(new String[listCategory.size()]);
		}
		
		List<GdGoods> slList = service.getFrontOrderSellerCd(frontOrderVO);
		if (slList.size() > 0) {
			for(GdGoods goods : slList){
				listSellerCd.add("'" + goods.getSellerCd() + "'");
			}
			arrSellerCd = listSellerCd.toArray(new String[listSellerCd.size()]);
		}

		//회원정보 가져오기
		List<OrderMember> omList= new ArrayList<OrderMember>();
		if(this.service.isLogin(userSO)){
			frontOrderVO.setM_no(String.valueOf(userSO.getUserInfo().getM_no()));
			omList = service.getFrontOrderMember(frontOrderVO);
			frontOrderVO.setGrp_sno(String.valueOf(omList.get(0).getSno()));
		}else{
			frontOrderVO.setErrorMsg("회원만 쿠폰사용이 가능합니다.");
		}
		if(null != arrGoodsno){
			listWhere.add("e.goodsno in ("+StringUtil.implode(",", arrGoodsno)+")");
		}
		if(null != arrCategory){
			listWhere.add("d.category in ("+StringUtil.implode(",", arrCategory)+")");
		}
		if(listWhere.size()>0){
			arrWhere = listWhere.toArray(new String[listWhere.size()]);
		}
		if(null != arrWhere){
			//동적 쿼리
			frontOrderVO.setStrWhere("OR (c.goodstype ='1' AND (" + StringUtil.implode(" OR ", arrWhere) + "))");
		}
		
		if (null != arrSellerCd) {
			//frontOrderVO.setStrWhere(frontOrderVO.getStrWhere() + "OR (c.sellerCd IN (" + StringUtil.implode(",", arrSellerCd) + "))");
		}
		
		if(frontOrderVO.getGrp_sno()==null){
			frontOrderVO.setGrp_sno("");
		}
		if(frontOrderVO.getM_no()==null){
			frontOrderVO.setM_no("");
		}

		List<Map<String, String>> listCou = this.service.availableCouponList(frontOrderVO, priceSum2, listGoodsno, cartList10);
		
		frontOrderVO.setRange(range);
		frontOrderVO.setCoupon_use(coupon_use);

		frontOrderVO.setListCou(listCou);
		
		/**
		* 화면단 변수
		*/
		frontOrderVO.setEmoney_base(String.valueOf(emoney_base));
		frontOrderVO.setOrdno(ordno);

		//frontOrderVO.setRtDelivery(dpList);
		frontOrderVO.setDcprice(String.valueOf(Math.round(memberTotalDcPrice)));
		frontOrderVO.setEmoney_hold(StringUtil.getMoneyFormat(ShopConfig.getProperty("emoney_hold")));
		frontOrderVO.setEmoney_min(StringUtil.getMoneyFormat(ShopConfig.getProperty("emoney_min")));
		int e_max = 0;
		if(ShopConfig.getProperty("emoney_max").contains("%")){
			e_max = (priceSum2 * StringUtil.N2I(ShopConfig.getProperty("emoney_max").split("%")[0])) / 100;
			frontOrderVO.setEmoney_max(String.valueOf(e_max));
		}else{
		   e_max = StringUtil.N2I(ShopConfig.getProperty("emoney_max"));
		   frontOrderVO.setEmoney_max(StringUtil.getMoneyFormat(ShopConfig.getProperty("emoney_max")));
		}
		frontOrderVO.setUse(use);
		frontOrderVO.setE_max(String.valueOf(e_max));
		frontOrderVO.setDelivery_goodsDelivery(ShopConfig.getProperty("delivery_goodsDelivery"));

		frontOrderVO.setOrderitem_mode(orderitem_mode);
		
		// 2017-08-24 : 주문시 추가배송비 정보를 보여주기 위해 추가함 (지은정)
		frontOrderVO.setDeliveryOverInfo(ShopLibFunction.getOverDeliveryInfo());
		
		frontOrderVO.setDelivery_shippingFee(ShopConfig.getProperty("delivery_shippingFee"));
		
		model.addAttribute("adminSettings", adminSettings);
		model.addAttribute("frontOrderVO", frontOrderVO);
		return result;
	}
	
	/**
	 *  결제처리
	 * @param paramVO
	 * @param session
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="order/indb")
	@ResponseBody
	public int orderIndb(@ModelAttribute("paramVO") FrontOrderSettleVO paramVO, HttpSession session, HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		int resCode = 0;
		try {
			DecimalFormat form = new DecimalFormat("0.00");
			
			if( StringUtils.isEmpty(paramVO.getOrdno()) ) {
				return -1; // 주문번호가 없습니다.
			}
			
			//주문번호 중복 체크
			FrontOrderSettleVO frontOrderSettleVO = new FrontOrderSettleVO();
			frontOrderSettleVO.setOrdno(paramVO.getOrdno());
			List<GdOrder> onoList = service.getFrontOrderCheck(frontOrderSettleVO);
			if(onoList.size() > 0){
				return -2; // 동일한 주문번호가 존재합니다.
			}
			
			if( !StringUtil.getNotEmptyStringArr(paramVO.getGoodsnoArr()) ) {
				return -3; // 상품이 없습니다.
			}
			
			String useEmoney = ""; //사용한 적립금
			String rmEmoney = ""; // 회원정보 적립금 - 사용한 적립금
			String dcCouponNm = ""; //사용한 쿠폰명
			String dcCodeCouponNm = ""; //사용한 할인코드 쿠폰명
			int memAddEmoney = 0; //회원그룹별 추가적립금 퍼센트	
			String addEmoney = "0"; //추가적립금 금액
			
			//적립금 사용유무(true: 사용, false:미사용)
			boolean isEmoney = !StringUtils.isEmpty(paramVO.getEmoney()) && !"0".equals(paramVO.getEmoney());
			//쿠폰 사용유무(true: 사용, false:미사용)
			boolean isCoupon = !StringUtils.isEmpty(paramVO.getDcCouponAmount()) && !"0".equals(paramVO.getDcCouponAmount());
			//할인코드 사용유무(true: 사용, false:미사용)
			boolean isDcCode = !StringUtils.isEmpty(paramVO.getDcCodeAmount()) && !"0".equals(paramVO.getDcCodeAmount());
			
			MemberMemberGrp mmg = new MemberMemberGrp();
			if( !StringUtils.isEmpty(paramVO.getM_no()) ) {
				//회원정보 조회 
				FrontOrderVO frontOrderVO = new FrontOrderVO();
				frontOrderVO.setM_no(paramVO.getM_no());
				mmg = service.getOrderMember(frontOrderVO);
				if(mmg == null) {
					return -5; //로그인정보가 잘못되었습니다.
				}
				
				int emoney =  StringUtil.N2I(paramVO.getEmoney()); //사용하는 적립금
				int selEmoney = mmg.getEmoney(); // 회원정보에 등록된 적립금
				if(selEmoney < emoney) {
					return -6; //사용가능한 적립금이 초과하였습니다.
				}
				
				useEmoney = form.format(emoney);
				rmEmoney = form.format(selEmoney-emoney);
				memAddEmoney = mmg.getAddEmoney();
				if(memAddEmoney > 0) {
					memAddEmoney = memAddEmoney / 100; // 추가적립금 / 100
				}
			}
			
			//사용한 쿠폰이 유효한지 체크
			if( isCoupon ) {
				if( StringUtils.isEmpty(paramVO.getApplysno()) || StringUtils.isEmpty(paramVO.getCouponcd()) ) {
					return -7; //사용할 수 없는 쿠폰입니다.
				}
				
				GdCoupon couponCodeInfo = service.getFrontOrderCouponCodeByCouponcd(paramVO.getCouponcd());
				if(couponCodeInfo == null) {
					return -7; //사용할 수 없는 쿠폰입니다.
				}
				
				dcCouponNm = couponCodeInfo.getCoupon();
			}
			
			//할인코드가 유효한지 체크
			if(isDcCode) {
				if( StringUtils.isEmpty(paramVO.getCouponDcCode()) ) {
					return -8; //사용할 수 없는 할인코드입니다.
				}
				
				GdCoupon couponCodeInfo = service.getFrontOrderCouponCodeByCouponcd(paramVO.getCouponDcCode());
				if(couponCodeInfo == null) {
					return -8; //사용할 수 없는 할인코드입니다.
				}
				
				dcCodeCouponNm = couponCodeInfo.getCoupon();
			}
			
			//회원그룹별 적립금 총금액 계산
			if(memAddEmoney != 0) {
				BigDecimal goodsPrice = new BigDecimal(paramVO.getGoodsprice());
				BigDecimal bgAddEmoney = goodsPrice.multiply(new BigDecimal(memAddEmoney)); // 상품금액 * 추가적립금 퍼센트
				addEmoney = StringUtil.N2S(form.format(bgAddEmoney.intValue()),"0");
			}
			
			frontOrderSettleVO = new FrontOrderSettleVO();
			frontOrderSettleVO.setOrdno(paramVO.getOrdno()); // 주문번호
			frontOrderSettleVO.setNameOrder(paramVO.getNameOrder2() +  paramVO.getNameOrder1()); // 주문명
			frontOrderSettleVO.setNameOrder1(paramVO.getNameOrder1()); // 주문명(이름)
			frontOrderSettleVO.setNameOrder2(paramVO.getNameOrder2()); // 주문명(성)
			frontOrderSettleVO.setDeliverycode(paramVO.getDeliverycode()); // 배송원장번호
			frontOrderSettleVO.setEmail(paramVO.getEmail1() + "@" + paramVO.getEmail2()); // 이메일
			frontOrderSettleVO.setMobileOrder(paramVO.getMobileReceiver()); // 휴대폰
			frontOrderSettleVO.setNameReceiver(paramVO.getNameReceiver2() + paramVO.getNameReceiver1()); // 받는사람
			frontOrderSettleVO.setNameReceiver1(paramVO.getNameReceiver1()); // 받는사람(이름)
			frontOrderSettleVO.setNameReceiver2(paramVO.getNameReceiver2()); // 받는사람(성)
			frontOrderSettleVO.setEmailReceiver(paramVO.getEmailReceiver1() +  "@" + paramVO.getEmailReceiver2() ); // 받는사람 이메일
			frontOrderSettleVO.setMobileReceiverType(paramVO.getMobileReceiverType()); // 받는사람 휴대폰 구분
			frontOrderSettleVO.setMobileReceiver(paramVO.getMobileReceiver()); // 받는사람휴대폰
			frontOrderSettleVO.setCountry(paramVO.getCountry()); // 국가
			frontOrderSettleVO.setZipcode(paramVO.getZipcode()); // 우편번호(ZIPCODE)
			frontOrderSettleVO.setAddress(paramVO.getAddress()); // 주소(ADDRESS1)
			frontOrderSettleVO.setAddress2(paramVO.getAddress2()); //상세주소(ADDRESS2)
			frontOrderSettleVO.setCity(StringUtil.N2S(paramVO.getCity(),null)); // CITY
			frontOrderSettleVO.setState(StringUtil.N2S(paramVO.getState(),null)); // STATE
			frontOrderSettleVO.setCustoms_num(paramVO.getCustoms_num()); //통관고유부호
			frontOrderSettleVO.setSettlekind(paramVO.getSettlekind()); // 결제종류
			frontOrderSettleVO.setSettleprice(StringUtil.N2S(form.format(paramVO.getAmount()),"0.00")); // 결제금액
			frontOrderSettleVO.setPrn_settleprice(StringUtil.N2S(form.format(paramVO.getAmount()),"0.00")); // 구매금액
			frontOrderSettleVO.setGoodsprice(StringUtil.N2S(paramVO.getGoodsprice(),"0.00")); // 상품금액
			frontOrderSettleVO.setDelivery(StringUtil.N2S(paramVO.getDelivery(),"0.00")); // 배송료
			frontOrderSettleVO.setCoupon(StringUtil.N2S(paramVO.getDcCouponTotal(), "0.00")); // 쿠폰
			frontOrderSettleVO.setEmoney(StringUtil.N2S(paramVO.getDcEmoneyAmount(), "0.00")); // 적립금
			frontOrderSettleVO.setAddemoney(StringUtil.N2S(addEmoney, "0.00")); //적립금(회원그룹별로 적립)
			frontOrderSettleVO.setMemberdc(StringUtil.N2S(paramVO.getMemberdc(), "0")); // 회원DC
			frontOrderSettleVO.setReserve(StringUtil.N2S(paramVO.getReserve(),"0")); // 예약
			frontOrderSettleVO.setEggFee(StringUtil.N2S(paramVO.getEggFee(),"0")); // 전바보증보험발급수수료
			frontOrderSettleVO.setBankAccount(StringUtil.N2S(paramVO.getBankAccount(),"0")); // 은행계좌
			frontOrderSettleVO.setBankSender(StringUtil.N2S(paramVO.getBankSender(),"")); // 송금인
			frontOrderSettleVO.setM_no(StringUtils.isEmpty(paramVO.getM_no()) ? "0" : paramVO.getM_no()); // 회원번호
			frontOrderSettleVO.setIp(req.getRemoteAddr()); // IP
			frontOrderSettleVO.setReferer(paramVO.getReferer()); // 참조페이지
			frontOrderSettleVO.setCoupon_emoney(StringUtil.N2S(paramVO.getCoupon_emoney(), "0")); // 쿠폰적립금
			frontOrderSettleVO.setEmailRecceiver(paramVO.getEmail1() + "@" + paramVO.getEmail2());  //수신이메일
			frontOrderSettleVO.setAddDelivery(StringUtil.N2S(paramVO.getAddDelivery(), "0")); // ???
			frontOrderSettleVO.setAgent(AgentUtil.getAgent(req)); // 접속 기기(W:Web, M:Mobile, A:App)
			frontOrderSettleVO.setSkin(ConfigClass.getSkin(req));
			frontOrderSettleVO.setStep("1");
			frontOrderSettleVO.setStep2("0"); //결제완료
			frontOrderSettleVO.setCyn("y"); //결제여부
			
			//무통장입금
			if( "a".equals(paramVO.getSettlekind()) ) {
				if(paramVO.getAmount() > 0) {
					frontOrderSettleVO.setStep("0"); //주문접수
					frontOrderSettleVO.setStep2("51"); //입금대기
					frontOrderSettleVO.setCyn("n"); //결제여부
				}else {
					frontOrderSettleVO.setStep("1");
					frontOrderSettleVO.setStep2("0"); //결제완료
					frontOrderSettleVO.setCyn("y"); //결제여부
				}
			}
			
			// 주문 기본정도 등록(gd_order)
			resCode = service.insertFrontOrder(frontOrderSettleVO);
			
			if(resCode == 0) {
				return -4; // 정삭적으로 주문 접수가 되지 않았습니다.
			}
			
			// 주문 상품별 배송정보 등록(gd_order_delivery)
			for(int i=0; i <  paramVO.getGoodsnoArr().length; i++) {
				frontOrderSettleVO = new FrontOrderSettleVO();
				frontOrderSettleVO.setOrdno(paramVO.getOrdno()); // 주문 별 주문번호
				frontOrderSettleVO.setGoodsno(paramVO.getGoodsnoArr()[i]); // 상품 별 상품번호
				frontOrderSettleVO.setDeliveryPrice(0); // 배송비
				frontOrderSettleVO.setPaymentTerms(0); // 0:무료/1:선불/2:착불
				frontOrderSettleVO.setAddDeliveryPrice(0); // 판매사별 추가배송료
				frontOrderSettleVO.setOpt1(paramVO.getOptSnoArr()[i]); // 옵션번호
				service.insertFrontOrderDelivery(frontOrderSettleVO);
			}
			
			int itemResult = 0;
			List<FrontOrderCartMapVO> cartList = (List<FrontOrderCartMapVO>)session.getAttribute("cartList");
			List<FrontOrderCartMapVO> cartDeleteList = new ArrayList<FrontOrderCartMapVO>(); // 장바구니 삭제 리스트
			if (cartList != null) {
				for (FrontOrderCartMapVO result : cartList) {
					//장바구니에서 삭제할 상품정보 설정
					cartDeleteList.add(result);
					
					//상품정보 설정
					frontOrderSettleVO = new FrontOrderSettleVO();
					frontOrderSettleVO.setOrdno(paramVO.getOrdno()); // 주문번호
					frontOrderSettleVO.setGoodsno(result.getGoodsno()); // 상품번호
					frontOrderSettleVO.setGoodsnm(result.getGoodsnm()); // 상품명(영문)
					frontOrderSettleVO.setGoodsnmKR(result.getGoodsnmKR()); // 상품명(국문)
					frontOrderSettleVO.setGoodsnmCN(result.getGoodsnmCN()); // 상품명(중문)
					frontOrderSettleVO.setOpt1(result.getOpt1()); // 옵션1
					frontOrderSettleVO.setOpt2(result.getOpt2()); // 옵션2
					frontOrderSettleVO.setAddopt(result.getAddopt()); // 추가옵션
					frontOrderSettleVO.setPrice(result.getPrice()); // 판매가격
					frontOrderSettleVO.setReserve(result.getReserve()); // 할인가격
					frontOrderSettleVO.setMemberdc("0"); // 회원DC
					frontOrderSettleVO.setEa(result.getEa()); // 수량
					frontOrderSettleVO.setCoupon("0"); // 쿠폰가격
					frontOrderSettleVO.setIstep("0"); //결제완료
					frontOrderSettleVO.setCyn("y"); //결제여부
					
					//무통장입금
					if( "a".equals(paramVO.getSettlekind()) ) {
						if(paramVO.getAmount() > 0) {
							frontOrderSettleVO.setIstep("51"); //입금대기
							frontOrderSettleVO.setCyn("n"); //결제여부
						}else {
							frontOrderSettleVO.setIstep("0"); //결제완료
							frontOrderSettleVO.setCyn("y"); //결제여부
						}
					}
					
					//공급가격 조회
					List<GdGoodsOption> rtSupply = service.getFrontGoodsSupply(frontOrderSettleVO);
					frontOrderSettleVO.setSupply(String.valueOf(rtSupply.get(0).getSupply())); // 공급가격
					
					//브랜드 정보 조회
					List<GoodsGoodsbrand> rtBrandMaker = service.getFrontGoodsBrand(frontOrderSettleVO);
					frontOrderSettleVO.setBrandnm(rtBrandMaker.get(0).getBrandnm()); // 브랜드명(영문)
					frontOrderSettleVO.setBrandnmCN(rtBrandMaker.get(0).getBrandnmCN()); // 브랜드명(중문)
					frontOrderSettleVO.setBrandnmKR(rtBrandMaker.get(0).getBrandnmKR()); // 브랜드명(국문)
					frontOrderSettleVO.setTax(String.valueOf(rtBrandMaker.get(0).getTax())); // 부가세
					
					//회원그룹별 적립금 상품별 금액 계산
					addEmoney = "0";
					if(memAddEmoney != 0) {
						BigDecimal goodsPrice = new BigDecimal(result.getPrice());
						BigDecimal bgAddEmoney = goodsPrice.multiply(new BigDecimal(memAddEmoney)); // 상품금액 * 추가적립금 퍼센트
						addEmoney = StringUtil.N2S(form.format(bgAddEmoney.intValue()),"0");
					}
					frontOrderSettleVO.setAddemoney(addEmoney);
					
					//주문상품 등록(gd_order_item)
					itemResult += service.insertFrontOrderGoods(frontOrderSettleVO);
					
					/**
					 * 쿠폰사용 처리
					 */
					if(isCoupon) {
						// 쿠폰발급회원정보 등록 (gd_coupon_applymember)
						Map<String, Object> dcCodeMemberMap = new HashMap<String, Object>();
						dcCodeMemberMap.put("applysno", paramVO.getApplysno()); // 쿠폰적용일련번호
						dcCodeMemberMap.put("m_no", paramVO.getM_no()); // 회원일련번호
						dcCodeMemberMap.put("used", "1"); // 사용여부(0:미사용, 1:사용)
						dcCodeMemberMap.put("ordno", paramVO.getOrdno()); //주문번호
						service.updateCouponApplymember(dcCodeMemberMap);
						
						//쿠폰결제사용내역 등록 (gd_coupon_order) 
						Map<String, Object> couponOrderMap = new HashMap<String, Object>();
						couponOrderMap.put("ordno", paramVO.getOrdno()); // 주문번호
						couponOrderMap.put("goodsno", result.getGoodsno()); // 상품일련번호
						couponOrderMap.put("applysno", paramVO.getApplysno()); // 적용일련번호
						couponOrderMap.put("couponcd", paramVO.getCouponcd()); // 쿠폰번호
						couponOrderMap.put("coupon", dcCouponNm); // 쿠폰명
						couponOrderMap.put("dc", paramVO.getDcCouponAmount()); // DC
						couponOrderMap.put("emoney", StringUtil.N2S(paramVO.getCoupon_emoney(), "0")); // 적립금
						couponOrderMap.put("m_no", paramVO.getM_no()); // 회원번호
						service.insertCouponOrder(couponOrderMap);
					}
					
					/**
					 * 할인코드 사용처리
					 * 할인코드는 사용시점에 DB에 등록
					 */
					if( isDcCode ) {
						Map<String, Object> dcCodeMap = new HashMap<String, Object>();
						// 쿠폰발급내역 등록 (gd_coupon_apply)
						dcCodeMap.put("couponcd", paramVO.getCouponDcCode()); // 쿠폰번호
						dcCodeMap.put("membertype", "2"); // 회원타입(0:전체회원발급/1:그룹별발급/2:회원개별발급)
						dcCodeMap.put("member_grp_sno", mmg.getSno()); // 회원그룹번호
						dcCodeMap.put("goodsno", result.getGoodsno()); // 상품일련번호
						dcCodeMap.put("goodsnm", result.getGoodsnm()); // 상품명(영문)
						dcCodeMap.put("goodsnmKR", result.getGoodsnmKR()); // 상품명(국문)
						dcCodeMap.put("goodsnmCN", result.getGoodsnmCN()); // 상품명(중문)
						dcCodeMap.put("status", "1"); // 상태(0:사용불가능/1:사용가능)
						int applySno = service.insertCouponApply(dcCodeMap);
						
						if(applySno > 0 ) {
							// 쿠폰발급회원정보 등록 (gd_coupon_applymember)
							Map<String, Object> dcCodeMemberMap = new HashMap<String, Object>();
							dcCodeMemberMap.put("applysno", applySno); // 쿠폰적용일련번호
							dcCodeMemberMap.put("m_no", paramVO.getM_no()); // 회원일련번호
							dcCodeMemberMap.put("status", "1"); // 상태(0:사용불가능,1:사용가능)
							dcCodeMemberMap.put("used", "1"); // 사용여부(0:미사용, 1:사용)
							dcCodeMemberMap.put("ordno", paramVO.getOrdno()); //주문번호
							service.insertCouponApplymember(dcCodeMemberMap);
							
							//쿠폰결제사용내역 등록 (gd_coupon_order) 
							Map<String, Object> couponOrderMap = new HashMap<String, Object>();
							couponOrderMap.put("ordno", paramVO.getOrdno()); // 주문번호
							couponOrderMap.put("goodsno", result.getGoodsno()); // 상품일련번호
							couponOrderMap.put("applysno", applySno); // 적용일련번호
							couponOrderMap.put("couponcd", paramVO.getCouponDcCode()); // 쿠폰번호
							couponOrderMap.put("coupon", dcCodeCouponNm); // 쿠폰명
							couponOrderMap.put("dc", paramVO.getDcCodeAmount()); // DC
							couponOrderMap.put("emoney", StringUtil.N2S(paramVO.getCoupon_emoney(), "0")); // 적립금
							couponOrderMap.put("m_no", paramVO.getM_no()); // 회원번호
							service.insertCouponOrder(couponOrderMap);
						}
					}
				}
				
				if(itemResult > 0) {
					/**
					 * 적립금 사용일경우 회원테이블에서 적립금 마이너스 처리
					 */
					if(!StringUtils.isEmpty(paramVO.getM_no()) && isEmoney) {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						//적립금 로그등록 (gd_log_emoney)
						paramMap = new HashMap<String, Object>();
						paramMap.put("m_no", paramVO.getM_no()); //회원번호
						paramMap.put("gbn", "U"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
						paramMap.put("ordno", paramVO.getOrdno()); //주문번호
						paramMap.put("emoney", "-" + useEmoney);
						paramMap.put("memo", "차감");
						service.insertEmoneyLog(paramMap);
						
						//회원테이블에 적립금 사용처리 (gd_member)
						paramMap = new HashMap<String, Object>();
						paramMap.put("m_no", paramVO.getM_no());
						paramMap.put("emoney", rmEmoney);
						service.updateMemberEmoney(paramMap);
					}
					
					/*
					 * 장바구니에서 구매내역 삭제
					 */
					if(cartDeleteList != null && cartDeleteList.size() != 0) {
						ShopSessionObject userSO = ShopSessionObject.getSessionObject(req);
						String cartMno = String.valueOf(userSO.getUserInfo().getM_no()); ///회원번호
						String cartUkey = StringUtil.nvl(session.getAttribute("nonMemberKey"),""); //비회원키
						
						for(FrontOrderCartMapVO cartVO : cartDeleteList) {
							FrontOrderVO frontOrderVO = new FrontOrderVO();
							frontOrderVO.setGoodsno(cartVO.getGoodsno());
							frontOrderVO.setOpt1(cartVO.getOpt1());
							if( userSO.isShopLogin() ) {
								frontOrderVO.setM_no(cartMno);
							}else {
								frontOrderVO.setUkey(cartUkey);
							}
							
							if( !StringUtils.isEmpty(cartMno) || !StringUtils.isEmpty(cartUkey) ) {
								service.deleteGoodsMemberCartList(frontOrderVO);
							}
						}
					}

					/* 비즈톡 발송 */
					BiztalkUtil biztalk = new BiztalkUtil();
					biztalk.sendAlimTalk(paramVO.getOrdno(), "ritzmall_01");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resCode;
	}
	
	/**
	 * 할인코드
	 * @param paramVO
	 * @param session
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="order/dcCode.dojson")
	public String dcCode(@ModelAttribute("paramVO") GdCoupon paramVO, HttpSession session, HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		try {
			
			if( StringUtils.isEmpty(paramVO.getDncode()) ) {
				model.addAttribute("RESULT", -1); //할인코드를 입력해주세요.
				return "dojson";
			}
			
			ShopSessionObject sessionObject = ShopSessionObject.getSessionObject(req);
			paramVO.setM_no(sessionObject.getUserInfo().getM_no());
			
			GdCoupon dcCodeInfo = service.getFrontOrderDcCodeInfo(paramVO);
			if(dcCodeInfo == null) {
				model.addAttribute("RESULT", -2); //사용할수 없는 코드입니다.
				return "dojson";
			}
			
			int couponUseCnt =  service.getFrontOrderCouponCodeCnt(paramVO);
			if(couponUseCnt > 0) {
				model.addAttribute("RESULT", -3); //이미 사용한 코드입니다.
				return "dojson";
			}
			
			model.addAttribute("RESULT", 0);
			model.addAttribute("dcCodeInfo", dcCodeInfo);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("RESULT", -99); //오류가 발생했습니다.
			return "dojson";
		}
		return "dojson";
	}
	
	/**
	 * 참고용 결제금액(환율적용) 
	 * @param paramVO
	 * @param session
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="order/setExchange.dojson")
	public String dcCode(@ModelAttribute("paramVO") FrontOrderSettleVO paramVO, HttpSession session, HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		try {
			String chgPrice = ShopLibFunction.getExchange(StringUtil.N2I(paramVO.getPrice()), paramVO.getSkin());
			
			model.addAttribute("RESULT", 0);
			model.addAttribute("chgPrice", chgPrice);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("RESULT", -99); //오류가 발생했습니다.
			return "dojson";
		}
		return "dojson";
	}
	
	
	@RequestMapping(value="order/settle")
	public String settle(@ModelAttribute("frontOrderVO") FrontOrderVO frontOrderVO
			, HttpSession session
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model
			) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("order/settle >> settle ");
			logger.debug(frontOrderVO.toString());
		}

		 
		//세션정보를 찍어보자
		Enumeration se = session.getAttributeNames();
		      while(se.hasMoreElements()){
		          String getse = se.nextElement()+"";
		          System.out.println("@@@@@@@ session : "+getse+" : "+session.getAttribute(getse));
		         }
		
		// 사용자가 선택한 쿠폰의 sno 를 세션에 저장.
		session.setAttribute("usedCouponSno", frontOrderVO.getCouponSno());
		
		// 사용한 쿠폰에 대한 정보 값 세션에 저장.
		session.setAttribute("usedCouponInfoList", frontOrderVO.getUsedCouponInfoList());
		
	    /* ============================================================================== */
	    /* =   PAGE : 결제 요청 PAGE                                                    = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   이 페이지는 Payplus Plug-in을 통해서 결제자가 결제 요청을 하는 페이지    = */
	    /* =   입니다. 아래의 ※ 필수, ※ 옵션 부분과 매뉴얼을 참조하셔서 연동을        = */
	    /* =   진행하여 주시기 바랍니다.                                                = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
	    /* =   접속 주소 : http://testpay.kcp.co.kr/pgsample/FAQ/search_error.jsp       = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   Copyright (c)  2010.02   KCP Inc.   All Rights Reserved.                 = */
	    /* ============================================================================== */
	    /* ============================================================================== */
	    /* =   환경 설정 파일 Include                                                   = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   ※ 필수                                                                  = */
	    /* =   테스트 및 실결제 연동시 site_conf_inc.jsp 파일을 수정하시기 바랍니다.    = */
	    /* = -------------------------------------------------------------------------- = */
//	@ include file="/kcp/cfg/site_conf_inc.jsp"<---------- property 나 DB로 빼야 할듯
	    /* ============================================================================== */
	    /* =   PAGE : 결제 정보 환경 설정 PAGE                                          = */
	    /* =----------------------------------------------------------------------------= */
	    /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
	    /* =   접속 주소 : http://kcp.co.kr/technique.requestcode.do			        = */
	    /* =----------------------------------------------------------------------------= */
	    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
	    /* ============================================================================== */

	    /* ============================================================================== */
	    /* = ※ 주의 ※                                                                 = */
	    /* = * g_conf_home_dir 변수 설정                                                = */
	    /* =----------------------------------------------------------------------------= */
	    /* =   BIN 절대 경로 입력 (bin전까지 설정						                = */
	    /* ============================================================================== */
	    String g_conf_home_dir  = "/home/dev/kcp_bin";       // BIN 절대경로 입력 (bin전까지) 
	    
	    /* ============================================================================== */
	    /* = ※ 주의 ※                                                                 = */
	    /* = * g_conf_gw_url 설정                                                       = */
	    /* =----------------------------------------------------------------------------= */
	    /* = 테스트 시 : testpaygw.kcp.co.kr로 설정해 주십시오.                         = */
	    /* = 실결제 시 : paygw.kcp.co.kr로 설정해 주십시오.                             = */
	    /* ============================================================================== */
	    String g_conf_gw_url    = "testpaygw.kcp.co.kr";

	    /* ============================================================================== */
	    /* = ※ 주의 ※                                                                 = */
	    /* = * g_conf_js_url 설정                                                       = */
	    /* =----------------------------------------------------------------------------= */
		/* = 테스트 시 : src="http://pay.kcp.co.kr/plugin/payplus_test.js"              = */
		/* =             src="https://pay.kcp.co.kr/plugin/payplus_test.js"             = */
	    /* = 실결제 시 : src="http://pay.kcp.co.kr/plugin/payplus.js"                   = */
		/* =             src="https://pay.kcp.co.kr/plugin/payplus.js"                  = */
	    /* =                                                                            = */
		/* = 테스트 시(UTF-8) : src="http://pay.kcp.co.kr/plugin/payplus_test_un.js"    = */
		/* =                    src="https://pay.kcp.co.kr/plugin/payplus_test_un.js"   = */
	    /* = 실결제 시(UTF-8) : src="http://pay.kcp.co.kr/plugin/payplus_un.js"         = */
		/* =                    src="https://pay.kcp.co.kr/plugin/payplus_un.js"        = */
	    /* ============================================================================== */
	    String g_conf_js_url    = "https://pay.kcp.co.kr/plugin/payplus_test_un.js";

	    /* ============================================================================== */
	    /* = 스마트폰 SOAP 통신 설정                                                    = */
	    /* =----------------------------------------------------------------------------= */
	    /* = 테스트 시 : false                                                          = */
	    /* = 실결제 시 : true                                                           = */
	    /* ============================================================================== */
	    boolean g_conf_server    = false;

	    /* ============================================================================== */
	    /* = g_conf_site_cd, g_conf_site_key 설정                                       = */
	    /* = 실결제시 KCP에서 발급한 사이트코드(site_cd), 사이트키(site_key)를 반드시   = */
	    /* = 변경해 주셔야 결제가 정상적으로 진행됩니다.                                = */
	    /* =----------------------------------------------------------------------------= */
	    /* = 테스트 시 : 사이트코드(T0000)와 사이트키(3grptw1.zW0GSo4PQdaGvsF__)로      = */
	    /* =            설정해 주십시오.                                                = */
	    /* = 실결제 시 : 반드시 KCP에서 발급한 사이트코드(site_cd)와 사이트키(site_key) = */
	    /* =            로 설정해 주십시오.                                             = */
	    /* ============================================================================== */
	    String g_conf_site_cd   = "T0000";
	    String g_conf_site_key  = "3grptw1.zW0GSo4PQdaGvsF__";

	    /* ============================================================================== */
	    /* = g_conf_site_name 설정                                                      = */
	    /* =----------------------------------------------------------------------------= */
	    /* = 사이트명 설정(한글 불가) : 반드시 영문자로 설정하여 주시기 바랍니다.       = */
	    /* ============================================================================== */
	    String g_conf_site_name = "KCP TEST SHOP";

	    /* ============================================================================== */
	    /* = 지불 데이터 셋업 (변경 불가)                                               = */
	    /* ============================================================================== */
	    String g_conf_log_level = "3";
	    String g_conf_gw_port   = "8090";        // 포트번호(변경불가)
		String module_type      = "01";          // 변경불가
	    int    g_conf_tx_mode   = 0;             // 변경불가
//		request.setCharacterEncoding("UTF-8");
	    /* = -------------------------------------------------------------------------- = */
	    /* =   환경 설정 파일 Include END                                               = */
	    /* ============================================================================== */
		List<FrontOrderCartMapVO> cartList = null;

		String emoney = StringUtil.N2S(frontOrderVO.getEmoney(),"0").replace(",", "");
		String coupon = StringUtil.N2S (String.valueOf(frontOrderVO.getCoupon()),"0").replace(",", "");
		String coupon_emoney = StringUtil.N2S(frontOrderVO.getCoupon_emoney(),"0").replace(",", "");
		String nameOrder = StringUtil.N2S(frontOrderVO.getNameOrder(),"");
		String phoneOrder = StringUtil.implode("-", frontOrderVO.getPhoneOrder());
		String mobileOrder = StringUtil.implode("-", frontOrderVO.getMobileOrder());
		
		String email = frontOrderVO.getEmail_1() + "@" + frontOrderVO.getEmail_2();
		frontOrderVO.setEmail(email);
		
		String nameReceiver = StringUtil.N2S(frontOrderVO.getNameReceiver(),"");
		String phoneReceiver = StringUtil.implode("-", frontOrderVO.getPhoneReceiver());
//		String mobileReceiver = StringUtil.implode("-", frontOrderVO.getMobileReceiver());
		String zipcode = frontOrderVO.getZipcode();
		String address = frontOrderVO.getAddress();
		String address_sub = frontOrderVO.getAddress_sub();
		String memo = frontOrderVO.getMemo();
		String deliPoli = StringUtil.N2S(String.valueOf(frontOrderVO.getDeliPoli()), "0");
		String emoney_max = StringUtil.N2S(frontOrderVO.getEmoney_max(), "0");
		String escrow = StringUtil.N2S(frontOrderVO.getEscrow(), "N");
		String settlekind = StringUtil.N2S(frontOrderVO.getSettlekind(), "a");
		String[] arrcoupon = frontOrderVO.getApply_coupon();
		String apply_coupon = StringUtil.implode("", arrcoupon);
		String delimsg = StringUtil.N2S(frontOrderVO.getDelimsg(), "");
		
		String settleprice = StringUtil.N2S(frontOrderVO.getPaper_settlement(),"0");
		
		// 최종 결제할 금액
		String totalPrice = "";
		
		//상품명
		String goodsnm = frontOrderVO.getGoodsnm();
		String goodsnmKR = frontOrderVO.getGoodsnmKR();
		String goodsnmCN = frontOrderVO.getGoodsnmCN();
		int priceSum = 0;
		int deliprice = StringUtil.N2I(StringUtil.N2S(frontOrderVO.getDeliprice(), "0"));
		int addDelivery = StringUtil.N2I(StringUtil.N2S(frontOrderVO.getAddDelivery(), "0"));
		int totalDeliprice = deliprice + addDelivery;
		int addreserve = 0;
		
		List<GdListBank> tmp_bank	= null;
			
		String ordno = StringUtil.N2S(frontOrderVO.getOrdno(), "");
		if("".equals(ordno)){
			model.addAttribute("msg", "주문번호가 존재하지 않습니다."); 
			model.addAttribute("url", "/shop/order"); 
			return "/shop/common/goods_alertMessage";
		}
		
		tmp_bank = service.getOrdersPopupOrder4(frontOrderVO);

		int dcprice = StringUtil.N2I(StringUtil.N2S(frontOrderVO.getDcprice(), "0"));
		String dc = WebUtils.getCookie(req, "dc").getValue();
		
		priceSum = StringUtil.N2I(frontOrderVO.getTotal_price()); 
		totalPrice = String.valueOf(priceSum - StringUtil.N2D(emoney) - dcprice - StringUtil.N2D(coupon) + totalDeliprice ); //상품 결제가격
		
		cartList = (List<FrontOrderCartMapVO>)session.getAttribute("cartList");
		frontOrderVO.setCartList(cartList);
		session.setAttribute("mode", "memCart");
			
		/**
		* 화면단 변수
		*/
		String email2 = frontOrderVO.getE1()+"@"+frontOrderVO.getE2();
		frontOrderVO.setEmailRecceiver(email2);
		
		String order_info_1 = nameOrder + " / " + mobileOrder + " / " + email;
		String order_info_2 = "(" + StringUtil.N2S(frontOrderVO.getZip(),"") + ") " + StringUtil.N2S(frontOrderVO.getAdd(),"") + " " + StringUtil.N2S(frontOrderVO.getAdd_sub());

		session.setAttribute("order_info_1", order_info_1);
		session.setAttribute("order_info_2", order_info_2);
		
		
		String receiver_info_1 = nameReceiver + " / " + mobileOrder + " / " + email2;
		String receiver_info_2 = "(" + zipcode + ") " + address + " " + address_sub;

		session.setAttribute("receiver_info_1", receiver_info_1);
		session.setAttribute("receiver_info_2", receiver_info_2);
		session.setAttribute("memo", memo);
		session.setAttribute("delivery", totalDeliprice);
		
		frontOrderVO.setSettlekind(settlekind);
		frontOrderVO.setOrdno(ordno);
		frontOrderVO.setGoodsnm(goodsnm);
		frontOrderVO.setGoodsnmKR(goodsnmKR);
		frontOrderVO.setGoodsnmCN(goodsnmCN);
		frontOrderVO.setNameOrder(nameOrder);
		frontOrderVO.setPhoneOrder_0(phoneOrder);
		frontOrderVO.setMobileOrder_0(mobileOrder);
		frontOrderVO.setEmail(email);
		frontOrderVO.setNameReceiver(nameReceiver);
		frontOrderVO.setPhoneReceiver_0(phoneReceiver);
//		frontOrderVO.setMobileReceiver_0(mobileReceiver);
		frontOrderVO.setZipcode(zipcode);
		frontOrderVO.setAddress(address);
		frontOrderVO.setAddress_sub(address_sub);
		frontOrderVO.setMemo(memo);
		frontOrderVO.setDeliPoli(Integer.parseInt(deliPoli));
		frontOrderVO.setEmoney_max(emoney_max);
		frontOrderVO.setEscrow(escrow);
		frontOrderVO.setDeliprice(String.valueOf(deliprice));
		frontOrderVO.setAddDelivery(String.valueOf(addDelivery));
		frontOrderVO.setCoupon(StringUtil.N2I(coupon));
		frontOrderVO.setCoupon_emoney(coupon_emoney);
		frontOrderVO.setSettlekind(settlekind);
		frontOrderVO.setEmoney(emoney);
		frontOrderVO.setApply_coupon_0(apply_coupon);
		frontOrderVO.setPriceSum(String.valueOf(priceSum));
		frontOrderVO.setDcprice(String.valueOf(dcprice));
		frontOrderVO.setDelimsg(delimsg);
		frontOrderVO.setAddreserve(String.valueOf(addreserve));
		frontOrderVO.setTmp_bank(String.valueOf(tmp_bank));
		frontOrderVO.setSettleprice(settleprice);
		frontOrderVO.setDc(dc);

		
		frontOrderVO.setCartList(cartList);
		
		frontOrderVO.setTotal_price(totalPrice);
		
		String ua=req.getHeader("User-Agent").toLowerCase();
		//settle.jsp include java source
		//지우면 안됨, 결제 완료시 db에 없는 값 계산. 
		int settle_dcprice = StringUtil.N2I(String.valueOf(frontOrderVO.getDcprice()));
		String settle_dc = String.valueOf(frontOrderVO.getDc());
		// 실결제금액..?
		int settle_priceSum = StringUtil.N2I(String.valueOf(frontOrderVO.getTotal_price()));	//TODO
		
//		application 객체에 저장중이었던 정보를 session 에 담는 것으로 변경함.
		session.setAttribute("dcprice", settle_dcprice);
		session.setAttribute("dc", settle_dc);
		session.setAttribute("priceSum", settle_priceSum);
		
		
		
		String mid = ShopConfig.getProperty("pg_id");		// 가맹점 ID(가맹점 수정후 고정)
		String oid = frontOrderVO.getOrdno();
		
		//결제가격 계산
		int settle_emoney = StringUtil.N2I(String.valueOf(frontOrderVO.getEmoney()));
		int settle_coupon = StringUtil.N2I(String.valueOf(frontOrderVO.getCoupon()));
		int settle_deliprice = StringUtil.N2I(String.valueOf(frontOrderVO.getDeliprice()));
		
		// 할인을 제외한 결제금액..(인 듯?)
		int settle_price = settle_priceSum - settle_emoney - settle_dcprice - settle_coupon;
		
		
		/* 기타 */
		//String siteDomain = "http://localhost"; //가맹점 도메인 입력
		String siteDomain = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();

		//인증
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 가맹점에 제공된 웹 표준 사인키(가맹점 수정후 고정)
		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		
		//###############################################
		// 2.signature 생성
		//###############################################
		Map<String, String> signParam = new HashMap<String, String>();

		signParam.put("oid", oid); 							// 필수
		signParam.put("price", String.valueOf(settle_price));							// 필수
		signParam.put("timestamp",	timestamp);		// 필수

		// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
		
		//String signature = SignatureUtil.makeSignature(signParam);
		String signature="";
		

		String cardQuotaBase		= ShopConfig.getProperty("pg_quota");		// 가맹점에서 사용할 할부 개월수 설정
		
		//###############################################
			// 2. 가맹점 확인을 위한 signKey를 해시값으로 변경 (SHA-256방식 사용)
			//###############################################
		String mKey = SignatureUtil.hash(signKey, "SHA-256"); 
		
		//for settle.jsp front page
		frontOrderVO.setSettle_dcprice(settle_dcprice);
		frontOrderVO.setSettle_dc(settle_dc);
		frontOrderVO.setSettle_priceSum(settle_priceSum);
		frontOrderVO.setMid(mid);
		frontOrderVO.setOid(oid);
		frontOrderVO.setSettle_emoney(settle_emoney);
		frontOrderVO.setSettle_coupon(settle_coupon);
		frontOrderVO.setSettle_deliprice(settle_deliprice);
		frontOrderVO.setSettle_price(settle_price);
		frontOrderVO.setSiteDomain(siteDomain);
		frontOrderVO.setSignKey(signKey);
		frontOrderVO.setTimestamp(timestamp);
		frontOrderVO.setSignParam(signParam);
		frontOrderVO.setSignature(signature);
		frontOrderVO.setCardQuotaBase(cardQuotaBase);
		frontOrderVO.setmKey(mKey);
		String submitStr="";

		if("h".equals(String.valueOf(settlekind))){
			if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")){
				 submitStr="$('#frmSettle').attr('action','/kcp/mobile_sample/order_mobile.jsp').submit()";
			}else{
				submitStr="$('#frmSettle').attr('action','/kcp/sample/order.jsp').submit()";
			}
		//$("#frmSettle").attr("action","/kcp/sample/pp_ax_hub.jsp").submit();
		}else if("c".equals(String.valueOf(settlekind))){
			if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")){
				submitStr="$('#frmSettle').attr('action','/kcp/mobile_sample/order_mobile.jsp').submit()";
			}else{
				submitStr="$('#frmSettle').attr('action','/kcp/sample/order.jsp').submit()";
			}
		}else if("o".equals(String.valueOf(settlekind))){
			if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")){
				submitStr="$('#frmSettle').attr('action','/kcp/mobile_sample/order_mobile.jsp').submit()";
			}else{
				submitStr="$('#frmSettle').attr('action','/kcp/sample/order.jsp').submit()";
			}
		}
		
		// 사용한 쿠폰 정보를 결제 페이지에서 확인할 수 있도록 세션에 저장.
		session.setAttribute("useCouponApplySno", frontOrderVO.getCouponSno());
		
		frontOrderVO.setSubmitStr(submitStr);
		frontOrderVO.setTotalDeliveryInfoVO((TotalDeliveryInfoVO) session.getAttribute("totalDeliveryInfoVO"));
		
		frontOrderVO.setSettleVOParams(req);
		session.setAttribute("frontOrderVO", frontOrderVO);

		return "/shop/order/settle";
	} 
	
	@RequestMapping(value="inipaypopup")
	public String inipaypopup(@ModelAttribute("frontOrderVO") FrontOrderPayresVO payresVO,@RequestParam Map<String, String> paramMap, HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {

		return "/shop/order/popup_inipay";
		
	}
	
	@RequestMapping(value="inipayclose")
	public String inipayclose(@ModelAttribute("frontOrderVO") FrontOrderPayresVO payresVO,@RequestParam Map<String, String> paramMap, HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Enumeration se = session.getAttributeNames();
		while(se.hasMoreElements()){
			String getse = se.nextElement()+"";
			System.out.println("@@@@@@@ session : "+getse+" : "+session.getAttribute(getse));
		}

		return "/shop/order/inipay_close";
	}
	
	
	@RequestMapping(value="inipayreturn")
	public String inipayrelay(@ModelAttribute("frontOrderVO") FrontOrderPayresVO payresVO,@RequestParam Map<String, String> paramMap, HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {

		model.addAttribute("iniMap", paramMap);
		return "/shop/order/popup_inipay_return";
	}
	
	/**
	 * 이니시스 결제요청 응답 URL 
	 * 
	 * @param payresVO
	 * @param session
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="inipayres", method = {RequestMethod.POST})
	public String inipayres(@ModelAttribute("frontOrderVO") FrontOrderPayresVO payresVO, HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {

		Enumeration se = session.getAttributeNames();
		while(se.hasMoreElements()){
			String getse = se.nextElement()+"";
			System.out.println("@@@@@@@ session : "+getse+" : "+session.getAttribute(getse));
		}
		
		String returnUrl = "redirect:order_fail";
		StringBuffer result = new StringBuffer();
		//#####################
		// 인증이 성공일 경우만
		//#####################
		if("0000".equals(payresVO.getIni_resultCode())){
			
			
			//############################################
			// 1.전문 필드 값 설정(***가맹점 개발수정***)
			//############################################
			
			String mid 		= payresVO.getIni_mid();						// 가맹점 ID 수신 받은 데이터로 설정
			String signKey	= "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";		// 가맹점에 제공된 키(이니라이트키) (가맹점 수정후 고정) !!!절대!! 전문 데이터로 설정금지
			String timestamp= SignatureUtil.getTimestamp();				// util에 의해서 자동생성
			String charset 	= "UTF-8";								    // 리턴형식[UTF-8,EUC-KR](가맹점 수정후 고정)
			String format 	= "JSON";								    // 리턴형식[XML,JSON,NVP](가맹점 수정후 고정)
			String authToken= StringEscapeUtils.unescapeHtml(payresVO.getIni_authToken());			    // 취소 요청 tid에 따라서 유동적(가맹점 수정후 고정)
			String authUrl	= StringEscapeUtils.unescapeHtml(payresVO.getIni_authUrl());				    // 승인요청 API url(수신 받은 값으로 설정, 임의 세팅 금지)
			String netCancel= StringEscapeUtils.unescapeHtml(payresVO.getIni_netCancelUrl());			 	// 망취소 API url(수신 받은 값으로 설정, 임의 세팅 금지)
			String ackUrl 	= StringEscapeUtils.unescapeHtml(payresVO.getIni_checkAckUrl());			    // 가맹점 내부 로직 처리후 최종 확인 API URL(수신 받은 값으로 설정, 임의 세팅 금지)		
			String merchantData = payresVO.getIni_merchantData();			// 가맹점 관리데이터 수신
			logger.debug("authToken : {}",authToken);
			logger.debug("authUrl : {}",authUrl);
			logger.debug("netCancel : {}",netCancel);
			logger.debug("ackUrl : {}",ackUrl);
			//#####################
			// 2.signature 생성
			//#####################
			Map<String, String> signParam = new HashMap<String, String>();

			signParam.put("authToken",	authToken);		// 필수
			signParam.put("timestamp",	timestamp);		// 필수

			// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
			String signature = SignatureUtil.makeSignature(signParam);

      		String price = "";  // 가맹점에서 최종 결제 가격 표기 (필수입력아님)
      		
		    // 1. 가맹점에서 승인시 주문번호가 변경될 경우 (선택입력) 하위 연결.  
		    // String oid = "";             
      
			//#####################
			// 3.API 요청 전문 생성
			//#####################
			Map<String, String> authMap = new Hashtable<String, String>();

			authMap.put("mid"			    ,mid);			  // 필수
			authMap.put("authToken"		,authToken);	// 필수
			authMap.put("signature"		,signature);	// 필수
			authMap.put("timestamp"		,timestamp);	// 필수
			authMap.put("charset"		  ,charset);		// default=UTF-8
			authMap.put("format"		  ,format);		  // default=XML
//      		authMap.put("price" 		,price);		    // 가격위변조체크기능 (선택사용)
      
			HttpUtil httpUtil = new HttpUtil();

			try{
				//#####################
				// 4.API 통신 시작
				//#####################

				String authResultString = "";

				authResultString = httpUtil.processHTTP(authMap, authUrl);
				
				//############################################################
				//5.API 통신결과 처리(***가맹점 개발수정***)
				//############################################################
//				out.println("## 승인 API 결과 ##");
				
				String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
				
				//out.println("<pre>"+authResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</pre>");
				
				Map<String, String> resultMap = new HashMap<String, String>();
				
				resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
								
				/*************************  결제보안 강화 2016-05-18 START ****************************/ 
				Map<String , String> secureMap = new HashMap<String, String>();
				secureMap.put("mid"			, mid);								//mid
				secureMap.put("tstamp"		, timestamp);						//timestemp
				secureMap.put("MOID"		, resultMap.get("MOID"));			//MOID
				secureMap.put("TotPrice"	, resultMap.get("TotPrice"));		//TotPrice
				
				// signature 데이터 생성 
				String secureSignature = SignatureUtil.makeSignatureAuth(secureMap);
				/*************************  결제보안 강화 2016-05-18 END ****************************/
				if("0000".equals(resultMap.get("resultCode")) && secureSignature.equals(resultMap.get("authSignature")) ){	//결제보안 강화 2016-05-18
				   /*****************************************************************************
			       * 여기에 가맹점 내부 DB에 결제 결과를 반영하는 관련 프로그램 코드를 구현한다.  
				   
					 [중요!] 승인내용에 이상이 없음을 확인한 뒤 가맹점 DB에 해당건이 정상처리 되었음을 반영함
							  처리중 에러 발생시 망취소를 한다.
			       ******************************************************************************/
					payresVO.setInicisTid((String)ConvertUtil.convertJSONstringToMap(authResultString).get("tid"));
					this.service.indb(payresVO, session, req, resp, model);
					
					this.service.orderEnd(session, req, resp, payresVO);
					
					result.append("결제에 성공하엿습니다.");
					//메일발송  
					AmazonSES mailConfig = new AmazonSES();
					Map<String,Object> mailMap = new HashMap<String,Object>();
					
					mailMap.put("ordno", StringUtil.N2S(payresVO.getOrdno(), ""));
					mailMap.put("name", payresVO.getNameOrder());
					//수신자이메일 , mode 메일종류 , mailMap  
					//mailConfig.mailSender(payresVO.getEmail(), "0", mailMap);
					
				} else {
					//성공코드가 "0000" 이 아닌경우는 모두 취소로 간주
					result.append("결제에 실패하였습니다.<br>");
					String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);
					result.append("취소 내용 :" + netcancelResultString + ".<br>");
					throw new Exception(netcancelResultString);
//					
//					//결제보안키가 다른 경우
//					if (!secureSignature.equals(resultMap.get("authSignature")) && "0000".equals(resultMap.get("resultCode"))) {
//						//망취소
//						if ("0000".equals(resultMap.get("resultCode"))) {
//							throw new Exception("데이터 위변조 체크 실패");
//						}
//					}
				}
				
				
				// 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패
				// 가맹점에서 스스로 파싱후 내부 DB 처리 후 화면에 결과 표시

				// payViewType을 popup으로 해서 결제를 하셨을 경우
				// 내부처리후 스크립트를 이용해 opener의 화면 전환처리를 하세요

				//throw new Exception("강제 Exception");
				returnUrl = "redirect:order_end";
				
			} catch (Exception ex) {

				//####################################
				// 실패시 처리(***가맹점 개발수정***)
				//####################################

				//#####################
				// 망취소 API
				//#####################
				String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)
				result.append("결제에 실패하였습니다.<br>");
				result.append("취소 내용 :" + netcancelResultString + ".<br>");
				logger.error(">>>", ex);
				session.removeAttribute("iniAuthMap");
				throw new BizException(ex);
			}
			
		}else{

			//#############
			// 인증 실패시
			//#############
			
			returnUrl = "redirect:order_fail";

		}
		
		payresVO.setResultMessage(result.toString());
		session.setAttribute("payresVO", payresVO);
		model.addAttribute("payresVO", payresVO);
		
		return returnUrl;
	}
	
	/**
	 * U+ Xpay 결제 모듈 Return URL
	 * 
	 * @param payresVO
	 * @param session
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="payres")
	public String payres(@ModelAttribute("frontOrderVO") FrontOrderPayresVO payresVO, HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
//	    /*
//	     * [최종결제요청 페이지(STEP2-2)]
//	     *
//	     * LG유플러스으로 부터 내려받은 LGD_PAYKEY(인증Key)를 가지고 최종 결제요청.(파라미터 전달시 POST를 사용하세요)
//	     */
//
//		/* ※ 중요
//		* 환경설정 파일의 경우 반드시 외부에서 접근이 가능한 경로에 두시면 안됩니다.
//		* 해당 환경파일이 외부에 노출이 되는 경우 해킹의 위험이 존재하므로 반드시 외부에서 접근이 불가능한 경로에 두시기 바랍니다. 
//		* 예) [Window 계열] C:\inetpub\wwwroot\lgdacom ==> 절대불가(웹 디렉토리)
//		*/
		
		//  XMall4\svc\workspace\xMall\src\main\webapp\WEB-INF\lgdacom 경로에 lgdacom 폴더 복사 해놓음
		//			 개발자 서버에서 확인하려면 경로 변경 필요
//		String configPath = "/data/xmall3.1/webapps/WebContent/lgdacom";
		String addDelivery = payresVO.getAddDelivery();
		this.service.indb(payresVO, session, req, resp, model);
		
		InetAddress local = InetAddress.getLocalHost();

		String ip = local.getHostAddress();
		
		String configPath = "C://XMall4//svc//workspace//xMall_mall_in_mall//src//main//webapp//lgdacom//";
		
		//add by whpark Useing for Error Processor 20191105
		// 0 : 실패, -1 : API 초기화 실패 , 1 : 성공
		int procResult =1;
		
		
		if(logger.isDebugEnabled()){
			if("127.0.1.1".equals(ip)){
				configPath = "/data/xmall5_in_mall/was/lgdacom/";
				logger.debug("@@@@@@configPath"+configPath);
			}
		}

		
		//String configPath = "C://XMall4//svc//workspace//xMall//src//main//webapp//WEB-INF//lgdacom//";  //LG유플러스에서 제공한 환경파일("/conf/lgdacom.conf,/conf/mall.conf") 위치 지정.
//	    //C:\XMall2\svc\workspace\webapp\WebContent\lgdacom\conf\mall.conf
//	    /*
//	     *************************************************
//	     * 1.최종결제 요청 - BEGIN
//	     *  (단, 최종 금액체크를 원하시는 경우 금액체크 부분 주석을 제거 하시면 됩니다.)
//	     *************************************************
//	     */
//
		if(logger.isDebugEnabled()) {
			logger.debug("in payres ");
		}
		
	    XPayClient xpay = new XPayClient();
	   	boolean isInitOK = xpay.Init(configPath, payresVO.getCST_PLATFORM());   	
	   	
	   	// 추가 
	   	StringBuffer result = new StringBuffer();
	   	String returnUrl = "";
	   	
	   	if( !isInitOK ) {
//	    	//API 초기화 실패 화면처리
	        result.append( "결제요청을 초기화 하는데 실패하였습니다.<br>");
	        result.append( "LG유플러스에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다.<br>");        
	        result.append( "mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.<br><br>");
	        result.append( "문의전화 LG유플러스 1544-7772<br>");
	        returnUrl = "";
	        procResult =-1;
//	   	
	   	}else{      
	   		try{
//	   			/*
//	   	   	     *************************************************
//	   	   	     * 1.최종결제 요청(수정하지 마세요) - END
//	   	   	     *************************************************
//	   	   	     */
		    	xpay.Init_TX(payresVO.getLGD_MID());
		    	xpay.Set("LGD_TXNAME", "PaymentByKey");
		    	xpay.Set("LGD_PAYKEY", payresVO.getLGD_PAYKEY());
//		    
//		    	//금액을 체크하시기 원하는 경우 아래 주석을 풀어서 이용하십시요.
		    	//String DB_AMOUNT = "DB나 세션에서 가져온 금액"; //반드시 위변조가 불가능한 곳(DB나 세션)에서 금액을 가져오십시요.
		    	//xpay.Set("LGD_AMOUNTCHECKYN", "Y");
		    	//xpay.Set("LGD_AMOUNT", DB_AMOUNT);
//		    	
	    	}catch(Exception e) {
	    		result.append("LG유플러스 제공 API를 사용할 수 없습니다. 환경파일 설정을 확인해 주시기 바랍니다. ");
	    		result.append(""+e.getMessage());
	    		payresVO.setResultMessage(result.toString());
	    		
	    		
	    		procResult =0;
	    		//return "/shop/order/order_fail"; // 임의로 지정. exeption 발생시의 경로는 추후 따로 확인 필요
	    	}
	   	}
//
//	    /*
//	     * 2. 최종결제 요청 결과처리
//	     *
//	     * 최종 결제요청 결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
//	     */
	     if ( xpay.TX() ) {
	         //1)결제결과 화면처리(성공,실패 결과 처리를 하시기 바랍니다.)
	    	 
	         result.append( "결제요청이 완료되었습니다.  <br>");
	         result.append( "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>");
	         result.append( "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>");
	         result.append("거래번호 : " + xpay.Response("LGD_TID",0) + "<br>");
	         result.append("상점아이디 : " + xpay.Response("LGD_MID",0) + "<br>");
	         result.append("상점주문번호 : " + xpay.Response("LGD_OID",0) + "<br>");
	         result.append("결제금액 : " + xpay.Response("LGD_AMOUNT",0) + "<br>");
	         result.append("결과코드 : " + xpay.Response("LGD_RESPCODE",0) + "<br>");
	         result.append("결과메세지 : " + xpay.Response("LGD_RESPMSG",0) + "<p>");
	         
	         for (int i = 0; i < xpay.ResponseNameCount(); i++) {
	        	 result.append(xpay.ResponseName(i) + " = ");
	             for (int j = 0; j < xpay.ResponseCount(); j++) {
	            	 result.append("\t" + xpay.Response(xpay.ResponseName(i), j) + "<br>");
	             }
	         }
	         result.append("<p>");
	         
	         if( "0000".equals( xpay.m_szResCode ) ) {
	         	//최종결제요청 결과 성공 DB처리
	         	result.append("최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");
	         	
	         	// 결제요청 성공시 returnUrl 지정, 파라미터 지정
	         	payresVO.setOrdno(xpay.Response("LGD_OID",0));
	         	payresVO.setInis_tid(xpay.Response("LGD_TID",0));
	         	this.service.orderEnd(session, req, resp, payresVO);
	         	//returnUrl = "/shop/order/order_end";
	         	procResult =1;
	         	
				//메일발송  
	         	AmazonSES mailConfig = new AmazonSES();
				Map<String,Object> mailMap = new HashMap<String,Object>();
				
				mailMap.put("ordno", StringUtil.N2S(payresVO.getOrdno(), ""));
				mailMap.put("name", payresVO.getNameOrder());
				//수신자이메일 , mode 메일종류 , mailMap  
				mailConfig.mailSender(payresVO.getEmail(), "0", mailMap);
	         	
	         	//최종결제요청 결과 성공 DB처리 실패시 Rollback 처리 	
	         	boolean isDBOK = true; //DB처리 실패시 false로 변경해 주세요.
	         	if( !isDBOK ) {
	         		xpay.Rollback("상점 DB처리 실패로 인하여 Rollback 처리 [TID:" +xpay.Response("LGD_TID",0)+",MID:" + xpay.Response("LGD_MID",0)+",OID:"+xpay.Response("LGD_OID",0)+"]");
	         		
	                result.append( "TX Rollback Response_code = " + xpay.Response("LGD_RESPCODE",0) + "<br>");
	                result.append( "TX Rollback Response_msg = " + xpay.Response("LGD_RESPMSG",0) + "<p>");
	         		
	                if( "0000".equals( xpay.m_szResCode ) ) {
	                	result.append("자동취소가 정상적으로 완료 되었습니다.<br>");
	                }else{
	                	result.append("자동취소가 정상적으로 처리되지 않았습니다.<br>");
	                }
	         	}
	         }else{
	         	//최종결제요청 결과 실패 DB처리
	         	result.append("최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");        	
	         }
	     }else {
			//2)API 요청실패 화면처리
//			결제요청 실패(API)시 returnUrl 지정, 에러코드 및 메세지 지정	
	    	payresVO.setOrdno(xpay.Response("LGD_OID", 0));
			payresVO.setErrCode(xpay.m_szResCode);
			payresVO.setErrMsg(xpay.m_szResMsg);
			//returnUrl = "/shop/order/order_fail";
			procResult =0;
	         
	     	//최종결제요청 결과 실패 DB처리
	     	result.append("최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");            	            
	     	payresVO.setResultMessage(result.toString());
	     }
	     
	     logger.debug("payres result :::::::::::::::::::::: " + result.toString());
	     logger.debug("returnUrl >> " + returnUrl);
	     payresVO.setAddDelivery(addDelivery);
	     model.addAttribute("payresVO", payresVO);
	     
	     
	   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<For Test whpark	     
	     if(procResult > 0) {
	    	 HttpSession ses = req.getSession();
	    	 ses.setAttribute("payresVO", payresVO);
	    	 return "redirect:order_end";
	     }else if(procResult==0) {
	    	 return "redirect:order_fail";
	     }else { //-1
	    	 return returnUrl;
	     }
	   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<For Test whpark	
	     //return returnUrl;

	}
	
	/**
	 * 주문 완료페이지
	 * @param frontOrderVO
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="order_end" , method=RequestMethod.POST)	
	public String order_end(@ModelAttribute("frontOrderVO") FrontOrderVO frontOrderVO, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", frontOrderVO.getOrdno());
		FrontOrderSettleVO orderInfo = service.getOrderEndInfo(param);
		model.addAttribute("orderInfo", orderInfo);
		
		return "/shop/order/order_end";
	}
	
	@RequestMapping(value="order_fail")	
	public String order_fail(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		String rtUrl="";
		if(null != session.getAttribute("payresVO")) {
			model.addAttribute("payresVO", session.getAttribute("payresVO"));
			model.addAttribute("frontOrderVO", session.getAttribute("frontOrderVO"));
			session.removeAttribute("payresVO");
			session.removeAttribute("frontOrderVO");
			session.removeAttribute("cartList");
			session.removeAttribute("totalDeliveryInfoVO");
			rtUrl = "/shop/order/order_fail";
		}
		return rtUrl;
	}	
// For Test whpark ->>>>>>>>>>>>>>>>>>>	
	
	
	@RequestMapping(value="ajaxcouponinquery")
	public String couponInquiry(FrontOrderVO frontOrderVO, Model model, HttpServletRequest req) {
		
		ShopSessionObject sessionObject = ShopSessionObject.getSessionObject(req);
		
		if(this.service.isLogin(sessionObject)){
			// vo에 로그인 유저 정보를 세팅
			this.service.setLoginUserInfo(frontOrderVO, sessionObject);
		}
		
		
		String [] goodsNos = frontOrderVO.getGoodsno().split(",");
		List<String> goodsCategories = this.service.getGoodsCategory(goodsNos);
		Set<String> detailCategories = this.service.getDetailCategories(goodsCategories);

		String price = frontOrderVO.getPrice();
//		// 즉시구매 상품 정보 가져오기
//		GdGoodsGoodsoptionGoodslink gdList = this.service.getFrontMypageCartlist(frontOrderVO);	// 상품정보 가져오기

//		// 상품정보의 카테고리를 카테고리별 배열로 파싱
//		String [] category 	= this.service.getCategoryArr(gdList.getCategory());
//		int price 			= gdList.getPrice();
//		int goodsno 		= gdList.getGoodsno();
//
		// 쿠폰 정보를 가져올 쿼리에 사용할 파라미터를 맵에 세팅
		Map<String, Object> couponParamMap = new HashMap<String, Object>();
		couponParamMap.put("grp_sno", frontOrderVO.getGrp_sno());
		couponParamMap.put("m_no", frontOrderVO.getM_no());
		couponParamMap.put("priceSum", price);
		couponParamMap.put("goodsno", goodsNos);
		couponParamMap.put("category", detailCategories);
		couponParamMap.put("categoryCount", detailCategories.size());
		couponParamMap.put("skin", ConfigClass.getSkin(req));
//		// 맵을 사용하여 쿠폰정보 가져옴
		List<CouponapplyApplymemberCouponCategoryGoodsno> couponInfoVO = this.service.getFrontCouponList(couponParamMap);
//		// 쿠폰이 적용될 상품의 가격정보를 추가로 세팅
//		for(CouponapplyApplymemberCouponCategoryGoodsno couponInfo : couponInfoVO) {
//			couponInfo.setGoodsPrice(price);
//			couponInfo.setGoodsno(goodsno); 
//		}
//		
		
		model.addAttribute("couponList", couponInfoVO);

		return "/shop/order/ajax_order_coupon_layerpopup";
	}

	// 2017-08-25 : 추가배송비 적용을 위하여 추가
	// 사용자가 입력한 배송지의 시/도 정보를 받아 추가 배송비가 부과되는 시/도라면 해당 지역의 추가 배송비 정보를 리턴.
	@RequestMapping(value="addOverDeliveryCheck.dojson")
	public FrontAddOverDeliveryVO addOverDeliveryCheck(FrontAddOverDeliveryVO vo) {
		if (logger.isDebugEnabled()) {
			logger.debug("addOverDeliveryCheck() >> " + vo.toString());
		}
		
		// 주문고객의 시도 정보가 넘어오지않았다면 빈값으로 데이터를 내려준다.
		if(null == vo.getAddressSido() || vo.getAddressSido().equals("")){
			return vo;
		}
		List<OrderDeliveryVO> deliveryList = new ArrayList<OrderDeliveryVO>();
		//상품이 판매자상품인지 총관리자 상품인지 구분해준다
		deliveryList =this.service.getDivisionOverDeliveryPolicy(vo.getGoodsnos());
		
		String[] sellerGoods = new String[deliveryList.size()];
		List<String> adminGoods  = new ArrayList<String>();
		
		//상품코드가 있는건 판매자가올린 상품 없는건 관리자가 올린 상품입니다
		for(int i = 0; i < deliveryList.size(); i++){
			String goodsDivision = deliveryList.get(i).getSellerCd();
			if (goodsDivision == null || goodsDivision.length() == 0) {
				adminGoods.add(deliveryList.get(i).getGoodsno());
			}else{
				sellerGoods[i] = deliveryList.get(i).getGoodsno();
			}
		}
		
		List<OverDeliveryVO> overDeliveries = new ArrayList<OverDeliveryVO>();
		
		//관리자 추가배송비
		//관리자는 제품번호가 필요없어 arrayList를 사용
		if (adminGoods != null && adminGoods.size() != 0) {
			overDeliveries.addAll(this.service.getAdminOverDelivery(vo.getAddressSido()));
		}
		
		
		//판매자 추가배송비
		List<OverDeliveryVO> sellerDelivery = this.service.getsellerOverDelivery(sellerGoods, vo.getAddressSido());
		if (sellerDelivery != null && sellerDelivery.size() != 0) {
			overDeliveries.addAll(sellerDelivery);
		}
				
		vo.setOverDeliveryVO(overDeliveries);
		
		return vo;
	}
	
	@RequestMapping(value="iniSignature.dojson")
	public String addOverDeliveryCheck(@ModelAttribute("frontOrderVO") FrontOrderVO frontOrderVO, Model model) throws Exception {
		Map<String, String> signParam = new HashMap<String, String>();
		
		signParam.put("oid", frontOrderVO.getOid()); 							// 필수
		signParam.put("price", frontOrderVO.getPrice());							// 필수
		signParam.put("timestamp",	frontOrderVO.getTimestamp());		// 필수
		
		// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
		String signature = SignatureUtil.makeSignature(signParam);
		
		model.addAttribute("signature", signature);
		return "dojson";
	}
	
	
}
