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
/********************************************************************************
 * <pre>
 * 업무구분명			:	Community
 * 세부업무구분명	: 	사용자 > Community
 * 작성자 				: 	이병환
 * 설명 				: 	사용자 Community Service
 * ----------------------------------------------
 * 변경이력
 * ----------------------------------------------
 * NO	 	날짜				작성자			내용
 * 1			20170208		이병환			최초작성
 * 
 * </pre>
 ********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.service.order;

import com.inicis.std.util.SignatureUtil;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.AgentUtil;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.dao.order.FrontOrderMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.order.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class FrontOrderService {

	Logger logger = LoggerFactory.getLogger(FrontOrderService.class);

	@Resource
	FrontOrderMapper mapper;
	
	public List<FrontOrderSettleVO> getOrderDeliveryList(Map<String, Object> paramMap) {
		return mapper.getOrderDeliveryList(paramMap);
	}
	
	// 로그인 유저 정보 세팅
	public void setLoginUserInfo(FrontOrderVO frontOrderVO, ShopSessionObject userSO) {
		frontOrderVO.setM_no(String.valueOf(userSO.getUserInfo().getM_no())); //  vo에 세션에 있는 mno를 세팅
		
		MemberMemberGrp mmg = this.getOrderMember(frontOrderVO);

		if (mmg != null) { //  로그인된 사용자의 정보
													// 세팅
			frontOrderVO.setName(mmg.getName());
			frontOrderVO.setName1(mmg.getName1());
			frontOrderVO.setName2(mmg.getName2());
			frontOrderVO.setAddress(mmg.getAddress());
			frontOrderVO.setAddress2(mmg.getAddresssub());
			frontOrderVO.setAddress_sub(mmg.getAddresssub());
			frontOrderVO.setEmoney(String.valueOf(mmg.getEmoney()));
			frontOrderVO.setDc(String.valueOf(mmg.getDc()));
			frontOrderVO.setPhone(String.valueOf(StringUtil.explode(mmg.getPhone(), "-")));
			frontOrderVO.setMobile(StringUtil.explode(mmg.getMobile(), "-"));
			frontOrderVO.setMobileReceiver(mmg.getMobile());
			frontOrderVO.setZipcode(mmg.getZipcode());
			frontOrderVO.setGrp_sno(String.valueOf(mmg.getSno()));
			frontOrderVO.setCustomsnum(mmg.getCustomsnum());
			frontOrderVO.setDiszipcode(mmg.getDiszipcode());
			frontOrderVO.setDisaddress(mmg.getDisaddress());
			frontOrderVO.setDisaddresssub(mmg.getDisaddresssub());
			String email = mmg.getEmail();
			if (StringUtils.hasText(email)) {
				frontOrderVO.setEmail(email);
				String[] emailArr = email.split("@");
				frontOrderVO.setEmail_1(emailArr[0]);
				frontOrderVO.setEmail_2(emailArr[1]);
			}

		}
	}

	/**재고량을 체크하여 구매할 수 있는 수량이 있는지 없는지를 반환한다.
	 * */
	private boolean isInGoodsStock(FrontOrderVO frontOrderVO, Model model, int ea, HttpServletRequest req) {
		if (logger.isDebugEnabled()) {
			logger.debug(" >> isInGoodsStock ");
		}
		
		//  ggOption : 상품 재고정보 등을 가져옴
		GoodsGoodsption ggOption = this.getFrontMypageCheckStock(frontOrderVO);
		
		int runout = 0;
		String useStock = null;
		int stock = 0;
		String goodsnm = null;
		if (ggOption != null) { //  ggpOption 이 있으면 각종 변수들 설정
			if (!"".equals(StringUtil.N2S(String.valueOf(ggOption.getRunout()), ""))) {
				runout = ggOption.getRunout();
			}

			useStock = ggOption.getUsestock();
			stock = ggOption.getStock();
			goodsnm = ggOption.getGoodsnm();
			if ("kr".equals(ConfigClass.getSkin(req))) {
				goodsnm = ggOption.getGoodsnmKR();
			} else if ("cn".equals(ConfigClass.getSkin(req))) {
				goodsnm = ggOption.getGoodsnmCN();
			}
		}

		if (runout != 0) {
			model.addAttribute("msg", goodsnm + "은 품절된 상품입니다.");
			return false;
		}
		if (useStock != null && ea > stock) {
			if (stock > 0) {
				model.addAttribute("msg", goodsnm + "상품의 잔여 재고는 " + stock + "개입니다.");
			} else {
				model.addAttribute("msg", goodsnm + "상품의 잔여 재고가 존재하지 않습니다.");
			}
			return false;
		}

		return true;
	}
	
	private boolean buyNowOrderInformationExtract(String[] optionsArray, List<String> cookieOptionList, FrontOrderVO frontOrderVO, Model model, HttpServletRequest req) {
		if (logger.isDebugEnabled()) {
			logger.debug(" >> buyNowOrderInformationExtract");
		}
		
		for (String options : optionsArray) {
			String[] optionsItem = options.split("\\|\\|\\|"); // 옵션을 각 항목별로 추출

			String ea = "1";
			
			int optionItemLength = optionsItem.length;

			switch (optionItemLength) {
			case 4:
				ea = StringUtils.hasText(optionsItem[3]) ? optionsItem[3] : ea;
			case 3:
				frontOrderVO.setAddopt(optionsItem[2]);
			case 2:
				 frontOrderVO.setOpt1(optionsItem[1]);// 
			case 1:
				frontOrderVO.setGoodsno(optionsItem[0]);
				break;
			default:
			}

			if(!this.isInGoodsStock(frontOrderVO, model, Integer.parseInt(ea), req)){
				return false;
			}
			cookieOptionList.add(options);
		}
		return true;

	}
	
	// 상품에서 즉시구매 선택시 // TODO
	public String buyNow(HttpSession session, FrontOrderVO frontOrderVO,
			Model model, HttpServletRequest req, HttpServletResponse res) throws NumberFormatException,
			Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(" >> buyNow ");
		}
		String goodsnm = "";
		String goodsnmKR = "";
		String goodsnmCN = "";
		// 새로 cookie에 저장될 상품정보
		String options_list = frontOrderVO.getOptionsList(); // 구매할 상품정보 가져오기

		//  options_list : 선택한 상품의 정보 (번호, 옵션값, 수량). 리스트가 있으면 split 하여 정보 추출
		String[] optionsArray = options_list != null ? options_list.split("\\,") : null;

		List<String> cookieOptionList = new ArrayList<String>();
		if(!this.buyNowOrderInformationExtract(optionsArray, cookieOptionList, frontOrderVO, model, req)) {
			model.addAttribute("url", "/shop/goods/goods_view?goodsno="+ frontOrderVO.getGoodsno());
			return "/shop/common/goods_alertMessage";
		}

		String cookieOptionStr = "";
		String[] optionArr = null;
		List<FrontOrderCartMapVO> cartList = new ArrayList<FrontOrderCartMapVO>();
		
		if (cookieOptionList.size() > 0) {
			
			for(int i=0; i<cookieOptionList.size(); i++) {
				cookieOptionStr += cookieOptionList.get(i);
				// DB조회
				optionArr = cookieOptionList.get(i).split("\\|\\|\\|");

				if (optionArr != null) {
					String goodsno = optionArr[0];
					frontOrderVO.setGoodsno(optionArr[0]);
					String addopt = frontOrderVO.getAddopt();
					String ea = "";
					String opt1 = optionArr[1];
					frontOrderVO.setOpt1(opt1);
		
					if (optionArr.length == 4) {
						ea = optionArr[3];
					}
					
					GdGoodsGoodsoptionGoodslink ggpgl = getFrontMypageCartlist(frontOrderVO);
				
					// 배송비 설정
					String deliveryPolicyNo = frontOrderVO.getDeliveryNo();	
					int deliveryPay = 0;	// 배송비
					String delivery = "";	// 배송비 멘트
					if(deliveryPolicyNo == null) {	// 상품별 배송비 사용시
						DecimalFormat format = new DecimalFormat("#,###");
						deliveryPay = ggpgl.getGoodsdelivery();
						delivery = format.format(deliveryPay) + " 원";
					} else {	// 기본배송정책 사용시
						GdDeliveryPolicy deliveryPolicy = ShopLibFunction.getDefaultDeliveryPolicy(ggpgl.getSellerCd()).get(0);
						deliveryPay = StringUtil.N2I(deliveryPolicy.getrDefault().replace(",", ""));
						delivery = deliveryPolicy.getDeliveryInfo();			
					}
					
					TotalDeliveryInfoVO totalDeliveryInfoVO = this.calcTotalDelivery(deliveryPolicyNo, ggpgl, frontOrderVO);
					this.setMemberGroupFreeDelivery(req, totalDeliveryInfoVO);
					frontOrderVO.setTotalDeliveryInfoVO(totalDeliveryInfoVO);
					
					session.setAttribute("totalDeliveryInfoVO", totalDeliveryInfoVO); // TODO 뭐야 이건?
					
					String price = "0";
					String orgPrice = "0";
					if (ggpgl != null) {
						price = StringUtil.N2S(String.valueOf(ggpgl.getPrice()), "0");
						orgPrice = StringUtil.N2S(String.valueOf(ggpgl.getPrice()), "0");
						if(ggpgl.getCpCouponprice() != null && ConfigClass.getSkin(req).equals("en")) {
							String couponprice =  ggpgl.getCpCouponprice().replace("%", "");
							float f = Float.parseFloat(price) - (Float.parseFloat(price) * Float.parseFloat(couponprice) / 100);
							price =  Float.toString(f);
						}
					}

					int priceInt = StringUtil.N2I(price);
					int orgPriceInt = StringUtil.N2I(orgPrice);
		
					int addPriceSum = 0;
					if (addopt != null && !addopt.equals("") && !addopt.equals("NULL")) {
						String[] addoptArr = addopt.split("\\|");
						for (int k = 0; k < addoptArr.length; k++) {
							if (addoptArr[k].split("\\^")[3] != null) {
								addPriceSum += StringUtil.N2I(addoptArr[k].split("\\^")[3]);
							}
						}
					}
		
					int priceSum = priceInt * Integer.parseInt(ea)+ addPriceSum;
					int orgPriceSum = orgPriceInt * Integer.parseInt(ea)+ addPriceSum;
					// 적립금
					int reserve = 0;
					//적립금 지급여부 Y,N
					if("y".equals(ShopConfig.getProperty("emoney_useyn"))){
						//TODO 회원 등급에 따른 적립금 지급으로 수정필요.
						
						/*if(ggpgl.getReserve() > 0){
							//상품등록시 적립금을 따로 입력한 경우 정책을 따르지않고 상품등록시 입력했던 적립금이 지급됩니다.
							reserve = ggpgl.getReserve() * Integer.parseInt(ea);
						}else if(ggpgl.getReserve() == 0){
							// 상품 적립금 지급에 대한 정책 > 상품적립금 기본설정 > 정율/정액 선택 > 0 : 정율 | 1 : 정액
							if ("0".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
								//상품적립금 기본설정 > 주문 상품금액의  %를 주문 시 적립합니다.
								reserve = priceSum - ShopLibFunction.getDcprice(new Double(priceSum).toString(),ShopConfig.getProperty("emoney_goods_emoney")+ "%");
							}else if("1".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
								// 상품적립금 기본설정 > 주문 상품 당  원을 주문 시 적립합니다.
								reserve = StringUtil.N2D(ShopConfig.getProperty("emoney_goods_emoney")) * Integer.parseInt(ea);
							}
						}*/
					}
					
		
					frontOrderVO.setPrice(price);
					frontOrderVO.setPriceSum(String.valueOf(priceSum));
					frontOrderVO.setOrgPriceSum(String.valueOf(orgPriceSum));
		
					FrontOrderCartMapVO frontOrderCartMapVO = new FrontOrderCartMapVO();
					frontOrderCartMapVO.setGoodsnm(ggpgl.getGoodsnm());
					frontOrderCartMapVO.setGoodsnmKR(ggpgl.getGoodsnmKR());
					frontOrderCartMapVO.setGoodsnmCN(ggpgl.getGoodsnmCN());
					frontOrderCartMapVO.setOpt1(opt1);
					frontOrderCartMapVO.setOpt2(ggpgl.getOpt2());
					frontOrderCartMapVO.setImg(ggpgl.getImg().replace("|", ""));
					frontOrderCartMapVO.setDcPrice(String.valueOf(ggpgl.getDcPrice()));
					frontOrderCartMapVO.setPrice(price);
					frontOrderCartMapVO.setConsumer(String.valueOf(ggpgl.getConsumer()));
					frontOrderCartMapVO.setReserve(new Integer(reserve).toString());			
					frontOrderCartMapVO.setDelivery_type(String.valueOf(ggpgl.getDeliverytype()));
					frontOrderCartMapVO.setGoods_delivery(String.valueOf(ggpgl.getGoodsdelivery()));
					frontOrderCartMapVO.setAddPriceSum(new Integer(addPriceSum).toString());
					frontOrderCartMapVO.setUse_emoney(ggpgl.getUseemoney());	
					frontOrderCartMapVO.setGoodsno(goodsno);	
					frontOrderCartMapVO.setAddopt(addopt);
					frontOrderCartMapVO.setEa(ea);
					frontOrderCartMapVO.setGoods_category(ggpgl.getCategory());
					frontOrderCartMapVO.setUsestock(ggpgl.getUsestock());	
					frontOrderCartMapVO.setPriceSum(String.valueOf(priceSum));	
					frontOrderCartMapVO.setDelivery(delivery);	
					frontOrderCartMapVO.setDeliveryNo(deliveryPolicyNo);
					frontOrderCartMapVO.setCategory(ggpgl.getCategory());
					frontOrderCartMapVO.setSellerCd(ggpgl.getSellerCd());
					frontOrderCartMapVO.setOptName(ggpgl.getOptnm());
					frontOrderCartMapVO.setBrandno(ggpgl.getBrandno());
					frontOrderCartMapVO.setCpCouponcd((ggpgl.getCpCouponcd()!=null)?ggpgl.getCpCouponcd().toString():"0");
					frontOrderCartMapVO.setCpCouponprice((ggpgl.getCpCouponprice()!=null)?ggpgl.getCpCouponprice().toString():"0");
					frontOrderCartMapVO.setCpCouponskin((ggpgl.getCpCouponskin()!=null)?ggpgl.getCpCouponskin().toString():"");
					frontOrderCartMapVO.setCpMaxprice((ggpgl.getCpMaxprice()!=null)?ggpgl.getCpMaxprice().toString():"0");
					frontOrderCartMapVO.setCpOrgPrice(orgPrice);
					frontOrderCartMapVO.setCpOrgPriceSum(String.valueOf(orgPriceSum));

					int grpEmoney = 0;
					ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
					if(sessObject.isShopLogin()){
						UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
						model.addAttribute("userInfo", userInfo);
						grpEmoney = (int) userInfo.getXkey().get("add_emoney");
						//예상 적립금
						reserve = priceSum - ShopLibFunction.getDcprice(String.valueOf(priceSum), grpEmoney + "%");
						frontOrderCartMapVO.setReserve(String.format("%.2f", reserve));
					}
					
					cartList.add(frontOrderCartMapVO);
		
				}
			}
		}


		model.addAttribute("mode", "addItem");
		// 상품정보 orders 쿠키에 담기
		if(logger.isDebugEnabled()) {
			logger.debug(" buyNow add cookie cookieOptionStr >>>>>>>>> "+ cookieOptionStr);
		}
		//  즉시구매시 쿠키에 담았던 구매정보를 세션으로 옮김
		session.setAttribute("orders", cookieOptionStr);
		
		frontOrderVO.setGoodsnm(goodsnm);
		frontOrderVO.setGoodsnmKR(goodsnmKR);
		frontOrderVO.setGoodsnmCN(goodsnmCN);
		frontOrderVO.setCartList(cartList);

		return "";
	}

	
	
	// 장바구니 구매 혹은 비회원 구매
	public String buyOfCartAndNonMembers(FrontOrderVO frontOrderVO,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, ShopSessionObject userSO, Model model)
			throws NumberFormatException, Exception {

		if(logger.isDebugEnabled()) logger.debug("####[FrontOrderService] > buyOfCartAndNonMembers 비회원구매 ,장바구니구매 ");
		
		String oriCookie = "";
		List<FrontOrderCartMapVO> cartList = new ArrayList<FrontOrderCartMapVO>();
		// 비회원 구매일 때
		if ("1".equals(frontOrderVO.getGuest()) || "1".equals(WebUtils.getCookie(req, "gd_isDirect"))) {
			if (logger.isDebugEnabled()) {
				logger.debug("비회원 구매");
			}
			// 만약 Cookie 때문에 값이 없다거나 에러가 나면 변경 필요할 것.
			if (null != WebUtils.getCookie(req, "orders")) {
				oriCookie = WebUtils.getCookie(req, "orders").getValue();
			}
			// oriCookie = getCookies(request, "orders");
		} else {// 장바구니 구매일 때
			if (logger.isDebugEnabled()) {
				logger.debug("장바구니 구매");
			}
			String carts = (String) session.getAttribute("carts");
			if (null != carts) {
				oriCookie = carts;
			}
		}
		oriCookie = URLDecoder.decode(oriCookie, "UTF-8");

		//장바구니		
		String result = this.buyOfCart(frontOrderVO, userSO, model, cartList, session, req);
		if ("".equals(result)) {
			// 쿠키에 담았던 orders 정보를 세션으로 바꿈
			session.setAttribute("orders", oriCookie);
		} else {
			return result;
		}
		//비로그인 즉시구매 (미구현)
		//this.buyOfNonMembers(oriCookie, frontOrderVO, cartList);

		frontOrderVO.setCartList(cartList);
		// 상품 정보 orders 쿠키에 담기

		// 쿠키에 담았던 정보를 세션으로 바꿈
		session.setAttribute("orders", oriCookie);
		return "";
	}

	// 장바구니 구매 혹은 비회원 구매 > 회원 장바구니
	public String buyOfCart(FrontOrderVO frontOrderVO, ShopSessionObject userSO, Model model,
			List<FrontOrderCartMapVO> cartList, HttpSession session, HttpServletRequest req) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("FrontOrderController >> FrontOrderService >> buyOfCart()");
		}

		frontOrderVO.setM_no(String.valueOf(userSO.getUserInfo().getM_no()));
		//2020.02.07 이현빈 비회원 주문을 위해 비회원키 조회
		frontOrderVO.setUkey(StringUtil.nvl(session.getAttribute("nonMemberKey"),""));
		List<GdGoodsCart> rtList = this.getGoodsMemberCartList(frontOrderVO); // rtList : 장바구니 목록

		if (rtList.size() > 0) {
			
			for (GdGoodsCart cart : rtList) {
				String goodsno 			= String.valueOf(cart.getGoodsno());	// 상품번호
				String opt1 			= cart.getOpt1();						// 상품옵션번호
				String opt2 			= cart.getOpt2();						// 현재 미사용
				String addopt 			= cart.getAddopt();						// 옵션정보
				String ea 				= String.valueOf(cart.getEa());			// 상품갯수
				String deliveryPolicyNo = cart.getDeliveryPolicyNo();			// 배송비정보
				String delivery 		= "";									
				frontOrderVO.setGoodsno(goodsno);
				frontOrderVO.setOpt1(opt1);
				GdGoodsGoodsoptionGoodslink gdList = this.getFrontMypageCartlist(frontOrderVO);	// 상품정보 가져오기
				
				// 배송비 설정
				if(deliveryPolicyNo == null) {	// 기본배송정책 사용시
					DecimalFormat format = new DecimalFormat("#,###");
					delivery = format.format(gdList.getGoodsdelivery()) + " 원";
				} else {	// 상품별 배송비 사용
					GdDeliveryPolicy deliveryPolicy = ShopLibFunction.getDefaultDeliveryPolicy(gdList.getSellerCd()).get(0);
					delivery = deliveryPolicy.getDeliveryInfo();	
				}
				
				
				int priceInt = gdList.getPrice(); // 원가 int
				String price = String.valueOf(priceInt); // 원가 String
				int addPriceSum = 0; // 옵션가 int

				// 옵션가 계산
				if (addopt != null && !addopt.equals("") && !addopt.equals("NULL")) {
					String[] addoptArr = addopt.split("\\|");
					for (int k = 0; k < addoptArr.length; k++) {
						if (addoptArr[k].split("\\^")[3] != null) {

							addPriceSum += StringUtil.N2D(addoptArr[k].split("\\^")[3]);

						}
					}
				}

				GoodsGoodsption ggd = this.getFrontMypageCheckStock(frontOrderVO);
				
				int runout = 0;
				String usestock = "";
				int stock = 0;
				if (!"".equals(StringUtil.N2S(String.valueOf(ggd.getRunout()),""))) {
					runout = ggd.getRunout();
				}

				usestock = gdList.getUsestock();
				if (!"".equals(StringUtil.N2S(String.valueOf(ggd.getStock()),""))) {
					stock = ggd.getStock();
				}

				// goodsnm =
				// URLEncoder.encode(ggdList.get(0).getGoodsnm(),"UTF-8");
				String goodsnm = ggd.getGoodsnm();
				String goods_type = frontOrderVO.getGoodsType();
				String goods_category = StringUtil.N2S(frontOrderVO.getGoods_category(), "");
				
				if ("kr".equals(ConfigClass.getSkin(req))) {
					goodsnm = ggd.getGoodsnmKR();
				} else if ("cn".equals(ConfigClass.getSkin(req))) {
					goodsnm = ggd.getGoodsnmCN();
				}

				int eaInt = Integer.parseInt(ea);

				// 재고상태 체크
				if (runout != 0) {
					model.addAttribute("msg", goodsnm + "은 품절된 상품입니다.");
					model.addAttribute("url", "/shop/goods/goods_view?goodsno="+ goodsno);
					return "/shop/common/goods_alertMessage";
				}
				if (usestock != null && eaInt > stock) {
					if (stock > 0) {
						model.addAttribute("msg", goodsnm + "상품의 잔여 재고는 "+ stock + "개입니다.");
						model.addAttribute("url", "/shop/goods/goods_view?goodsno=" + goodsno);
						return "/shop/common/goods_alertMessage";
					} else {
						model.addAttribute("msg", goodsnm + "상품의 잔여 재고가 존재하지 않습니다.");
						model.addAttribute("url", "/shop/goods/goods_view?goodsno=" + goodsno);
						return "/shop/common/goods_alertMessage";
					}
				}
				// //////////////////////////////////////////////////////////////////////////
				// //////////////////////////////////////////////////////////////////////////

				//  옵션가
				int optionSum =  addPriceSum;
				int priceSum = StringUtil.N2I(price) * Integer.parseInt(ea) + (optionSum);
				
				// 적립금
				int reserve = 0;

				//적립금 지급여부 Y,N
				if("y".equals(ShopConfig.getProperty("emoney_useyn"))){
					//TODO 회원 등급에 따른 적립금 지급으로 수정필요.
					
					/*if(gdList.getReserve() > 0){
						//상품등록시 적립금을 따로 입력한 경우 정책을 따르지않고 상품등록시 입력했던 적립금이 지급됩니다.
						reserve = gdList.getReserve() * Integer.parseInt(ea);
					}else if(gdList.getReserve() == 0){
						// 상품 적립금 지급에 대한 정책 > 상품적립금 기본설정 > 정율/정액 선택 > 0 : 정율 | 1 : 정액
						if ("0".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
							// 상품적립금 기본설정 > 주문 상품금액의  %를 주문 시 적립합니다.
							reserve = priceSum - ShopLibFunction.getDcprice(new Double(priceSum).toString(),ShopConfig.getProperty("emoney_goods_emoney")+ "%");
						}else if("1".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
							//상품적립금 기본설정 > 주문 상품 당  원을 주문 시 적립합니다.
							reserve = StringUtil.N2D(ShopConfig.getProperty("emoney_goods_emoney")) * Integer.parseInt(ea);
						}
					}*/
				}
				

				frontOrderVO.setPrice(price);
				frontOrderVO.setPriceSum(String.valueOf(priceSum));

				FrontOrderCartMapVO cartMap = new FrontOrderCartMapVO();
				cartMap.setGoodsnm(gdList.getGoodsnm());
				cartMap.setImg(gdList.getImg().replace("|", ""));
				cartMap.setPrice(new Integer(price).toString());
				cartMap.setReserve(new Integer(reserve).toString());
				cartMap.setUse_emoney(gdList.getUseemoney());
				cartMap.setGoodsno(goodsno);
				cartMap.setAddopt(addopt);
				cartMap.setEa(ea);
				cartMap.setGoods_category(gdList.getCategory());
				cartMap.setOpt1(opt1);
				cartMap.setOpt2(opt2);
				cartMap.setUsestock(usestock);
				cartMap.setPriceSum(new Integer(priceSum).toString()); // 상품총가격
				cartMap.setOptionSum(new Integer(optionSum).toString()); // 옵션가
				cartMap.setUsestock(gdList.getUsestock());
				cartMap.setDelivery(delivery);
				cartMap.setDeliveryNo(deliveryPolicyNo);
				cartMap.setCategory(gdList.getCategory());
				cartMap.setSellerCd(gdList.getSellerCd());
				cartMap.setOptName(gdList.getOptnm());
				
				cartList.add(cartMap);
			}
			TotalDeliveryInfoVO totalDeliveryInfoVO = this.calcTotalDelivery(rtList, frontOrderVO);
			this.setMemberGroupFreeDelivery(req, totalDeliveryInfoVO);
			frontOrderVO.setTotalDeliveryInfoVO(totalDeliveryInfoVO);
			session.setAttribute("totalDeliveryInfoVO", totalDeliveryInfoVO);// TODO ???
		}
		model.addAttribute("mode", "memCart");
		frontOrderVO.setCartList(cartList);
		return "";
	}

	// 장바구니 구매 혹은 비회원 구매 > 비회원 즉시구매
	public void buyOfNonMembers(
			String oriCookie, 
			FrontOrderVO frontOrderVO,
			List<FrontOrderCartMapVO> cartList) throws NumberFormatException, Exception {
		
		if (StringUtils.hasText(oriCookie)) {

			String[] optionArr = oriCookie.split("\\,");

			cartList = new ArrayList<FrontOrderCartMapVO>();

			for (int i = 0; i < optionArr.length; i++) {

				String goodsno = optionArr[i].split("\\|\\|\\|")[0];
				String opt1 = "";
				String opt2 = "";

				if (optionArr[i].split("\\|\\|\\|")[1] != null
						&& !optionArr[i].split("\\|\\|\\|")[1].equals("")
						&& !optionArr[i].split("\\|\\|\\|")[1].equals("NULL")) {
					opt1 = optionArr[i].split("\\|\\|\\|")[1].split("\\|")[0];
					opt2 = optionArr[i].split("\\|\\|\\|")[1].split("\\|")[1];
				} else {
					opt1 = "";
					opt2 = "";
				}

				String addopt = optionArr[i].split("\\|\\|\\|")[2];
				String ea = optionArr[i].split("\\|\\|\\|")[3];

				frontOrderVO.setGoodsno(goodsno);
//				frontOrderVO.setOpt1(opt1);
//				frontOrderVO.setOpt2(opt2);
				GdGoodsGoodsoptionGoodslink ggpglList = this.getFrontMypageCartlist(frontOrderVO);
				
				// goodsnm,img_s
				// img,price,reserve,delivery_type,goods_delivery,use_emoney
				// 적립금
				int reserve = 0;
				String price = String.valueOf(ggpglList.getPrice());
				int priceInt = StringUtil.N2I(price);
				int addPriceSum = 0;
				if (addopt != null && !addopt.equals("") && !addopt.equals("NULL")) {
					String[] addoptArr = addopt.split("\\|");
					for (int k = 0; k < addoptArr.length; k++) {
						if (addoptArr[k].split("\\^")[3] != null) {
							addPriceSum += StringUtil.N2D(addoptArr[k].split("\\^")[3]);
						}
					}
				}

				int priceSum = priceInt + addPriceSum;

				if ("0".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
					reserve = StringUtil.N2I(price) - ShopLibFunction.getDcprice(new Integer(priceSum).toString(),ShopConfig.getProperty("emoney_goods_emoney")+ "%");
				}
				if ("1".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
					reserve = StringUtil.N2I(ShopConfig.getProperty("emoney_goods_emoney"));
				}

				FrontOrderCartMapVO cartMap = new FrontOrderCartMapVO();
				cartMap.setGoodsnm(ggpglList.getGoodsnm());
				cartMap.setImg(ggpglList.getImg().replace("|", ""));
				cartMap.setPrice(new Integer(priceSum).toString());
				cartMap.setReserve(new Integer(reserve).toString());
				cartMap.setDelivery_type(String.valueOf(ggpglList.getDeliverytype()));
				cartMap.setGoods_delivery(String.valueOf(ggpglList.getGoodsdelivery()));
				cartMap.setAddPriceSum(new Integer(addPriceSum).toString());
				cartMap.setUse_emoney(ggpglList.getUseemoney());
				cartMap.setGoodsno(goodsno);
				cartMap.setAddopt(addopt);
				cartMap.setEa(ea);
				cartMap.setGoods_category(ggpglList.getCategory());
				cartMap.setOpt1(opt1);
				cartMap.setOpt2(opt2);
				cartMap.setUsestock(ggpglList.getUsestock());
				cartMap.setSellerCd(ggpglList.getSellerCd());
				
				cartList.add(cartMap);
			}
		}
	}

	// 결제완료
	public void orderEnd(HttpSession session, HttpServletRequest req,
			HttpServletResponse resp, FrontOrderPayresVO payresVO)
			throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("FrontOrderService >> orderEnd");
		}
		payresVO.setCartList((List<FrontOrderCartMapVO>) session.getAttribute("cartList"));
		payresVO.setDcprice(String.valueOf(session.getAttribute("dcprice")));
		payresVO.setDc(String.valueOf(session.getAttribute("dc")));
		payresVO.setPriceSum(String.valueOf(session.getAttribute("priceSum")));
		payresVO.setOrder_info_1(String.valueOf(session.getAttribute("order_info_1")));
		payresVO.setOrder_info_2(String.valueOf(session.getAttribute("order_info_2")));
		payresVO.setReceiver_info_1(String.valueOf(session.getAttribute("receiver_info_1")));
		payresVO.setReceiver_info_2(String.valueOf(session.getAttribute("receiver_info_2")));
		payresVO.setMemo(String.valueOf(session.getAttribute("memo")));
		payresVO.setDelivery(String.valueOf(session.getAttribute("delivery")));
		payresVO.setTotalDeliveryInfoVO((TotalDeliveryInfoVO)session.getAttribute("totalDeliveryInfoVO"));
		payresVO.setUsedCouponInfoList((List<CouponapplyApplymemberCouponCategoryGoodsno>) session.getAttribute("usedCouponInfoList"));
				
		Long ordno = Long.parseLong(payresVO.getOrdno());

		session.removeAttribute("cartList");
		session.removeAttribute("dc");
		session.removeAttribute("priceSum");
		session.removeAttribute("dcprice");
		session.removeAttribute("order_info_1");
		session.removeAttribute("order_info_2");
		session.removeAttribute("receiver_info_1");
		session.removeAttribute("receiver_info_2");
		session.removeAttribute("memo");
		session.removeAttribute("delivery");
		session.removeAttribute("totalDeliveryInfoVO");
		session.removeAttribute("usedCouponInfoList");
		//  장바구니에서 인입시 처리될 부분 미구현. 구현 필요.
//TODO
		//  세팅했던 주문정보 삭제
		WebUtil.setCookies(resp, "orders", "", -1);
		session.removeAttribute("orders");
		//
		GdOrderListBank orderListBank = this.frontOrderResult(ordno);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("step2", "0"); // step 2 결졔완료로 변경
		paramMap.put("cyn", "y"); // cyn 결제완료로 변경.
		paramMap.put("inis_tid", payresVO.getInis_tid());
		paramMap.put("ordno", ordno);

		// 이니시스 모바일 계좌이체가 아닐 경우에만 실행해야함.
		//  현재 신용카드 결제만 가능.
		if (!StringUtils.hasText((String) req.getAttribute("bank"))) {
			// 변경할 쿼리 실행
			this.frontOrderEndUpdate(paramMap);
			this.frontOrderItemUpdate(paramMap);
		}

		// 적립금 사용시
		int m_no = orderListBank.getMno();
		int emoney = orderListBank.getEmoney();
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		if (userInfo != null && emoney > 0) {
			ShopLibFunction.setEmoney(-emoney, m_no, ordno, "상품구입시 적립금 결제 사용");
		}
		
		//배송완료시에 적립금 적립을 위하여 구매완료에는 주석처리
/*
		// 적립금 적립
		List<Integer> goodsnoArr = mapper.getFrontGoodsNo(ordno);

		ShopConfig.getProperty("emoney_limit");
		
		int reserveSum = 0;
		for (int i = 0; i < goodsnoArr.size(); i++) {
			reserveSum += ShopLibFunction.getReserve(goodsnoArr.get(i));

		}
		if (logger.isDebugEnabled()) {
			logger.debug("@@ orderService >> reserve >> " + reserveSum);
		}
		
		paramMap.put("ordno", ordno);
		paramMap.put("reserve", reserveSum);
		
//		적립금 처리 관리자 OrderService > changeOrderStep > ShopLibFunction.ctlStep 에서 관리함
		if("0".equals(ShopConfig.getProperty("emoney_limit")) && userInfo.getM_no() != 0){
			//적립금 사용시 상품적립금 지급여부
			//적립금을 사용해도 구매하는 상품의 적립금을 그대로 지급합니다.
			
			//회원 적립금 적립 & 회원 적립 내역 INSERT
			ShopLibFunction.setEmoney(reserveSum, userInfo.getM_no(), ordno, "구매완료로 인한 구매적립금 적립");
			//주문 정보 적립 적립금 UPDATE
			mapper.updateFrontReserve(paramMap);
		} else {
			//적립금을 사용하면 구매하는 상품의 적립금을 지급하지 않습니다.
			if(emoney == 0 && userInfo.getM_no() != 0){
				//적립금 사용하지 않음

				//회원 적립금 적립 & 회원 적립 내역 INSERT
				ShopLibFunction.setEmoney(reserveSum, userInfo.getM_no(), ordno, "구매완료로 인한 구매적립금 적립");
				//주문 정보 적립 적립금 UPDATE
				mapper.updateFrontReserve(paramMap);
			} else {
				//적립금 사용
				if(logger.isDebugEnabled()){
					logger.debug("@@ emoney_limit >> "+ShopConfig.getProperty("emoney_limit")+" use reserve >>" + emoney);
				}
			}
		}
*/
		
		// 재고처리
		ShopLibFunction.setStock(ordno);
		
		// 쿠폰 사용처리 및 적립금 쿠폰의 적립금 적립 처리
		this.usedCouponAndReserveCouponEmoney(session, req, orderListBank, StringUtil.N2I(payresVO.getPriceSum()), ordno);
		// 화면단 변수
		payresVO.setOrderListBank(orderListBank);
		
	}
	
	private void usedCouponAndReserveCouponEmoney(HttpSession session, HttpServletRequest req, GdOrderListBank orderListBank, int goodsTotalPrice, Long ordno) throws SQLException, Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("frontOrderService >> usedCouponAndReserveCouponEmoney");
		}
		// 쿠폰 사용시 쿠폰 상태 변경.
		String usedCouponSno = (String) session.getAttribute("usedCouponSno");
		ShopSessionObject sessionObject = ShopSessionObject.getSessionObject(req);
		UserInfo userInfo = sessionObject.getUserInfo();

		if (this.isUserLoginAndUseCoupon(userInfo, usedCouponSno)) {
			String [] usedCouponSnos = usedCouponSno.split(",");

			// 적립금 지급 쿠폰의 적립금 지급처리
			this.saveCouponReserves(orderListBank, usedCouponSnos, goodsTotalPrice);
			// 쿠폰 사용처리
			this.frontCouponStatusUpdate(usedCouponSnos, orderListBank.getMno(), ordno);
		}

	}
	
	private boolean isUserLoginAndUseCoupon(UserInfo userInfo, String usedCouponSno) {
		return userInfo != null && usedCouponSno != null;
	}
	
	private void saveCouponReserves(GdOrderListBank orderListBank, String[] usedCouponSnos, int goodsTotalPrice) throws Exception {
		List<GdCoupon> reserveCoupons = this.mapper.selectReserveCouponInUsedCoupon(usedCouponSnos);
		for(GdCoupon gdCoupon : reserveCoupons) {
			String reservePrice = gdCoupon.getPrice();
			int reserveCouponPrice = 0;
			if(reservePrice.contains("%")) {
				int percent = Integer.parseInt(reservePrice.substring(0, reservePrice.length() - 1));
				reserveCouponPrice = (goodsTotalPrice / 100) * percent;
			} else {
				reserveCouponPrice = Integer.parseInt(gdCoupon.getPrice());
			}
			String message = gdCoupon.getCoupon() + " 쿠폰 사용으로 인한 적립금 적립";
			ShopLibFunction.setEmoney(reserveCouponPrice, orderListBank.getMno(), orderListBank.getOrdno(), message);
		}
	}

	//  미검증 상태. 일단 코드만 service 단으로 이동시킴.
	// 사용가능 쿠폰 리스트
	public List<Map<String, String>> availableCouponList(
			FrontOrderVO frontOrderVO, 
			int priceSum2, 
			List<String> listGoodsno,
			List<FrontOrderCartMapVO> cartList10) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("FrontOrderController >> FrontOrderService >> availableCouponList()");
		}
		List<CouponapplyApplymemberCouponCategoryGoodsno> caccgList = this.getFrontOrderCoupon(frontOrderVO);
		List<Map<String, String>> listCou = null;
		int apr = 0;

		if (caccgList.size() > 0) {
			listCou = new ArrayList<Map<String, String>>();
			for (CouponapplyApplymemberCouponCategoryGoodsno caccg : caccgList) {
				if (priceSum2 < StringUtil.N2D(caccg.getExcPrice())) {
					continue;
				}

				Map<String, String> couMap = new HashMap<String, String>();
				couMap.put("couponcd", String.valueOf(caccg.getCouponcd()));
				logger.debug("couponcd:::::::"+ String.valueOf(caccg.getCouponcd()));
				couMap.put("sno", String.valueOf(caccg.getSno()));
				couMap.put("ability", caccg.getAbility());
				couMap.put("coupon", caccg.getCoupon());
				couMap.put("goodsnm", caccg.getGoodsnm());
				couMap.put("goodsnmKR", caccg.getGoodsnmKR());
				couMap.put("goodsnmCN", caccg.getGoodsnmCN());
				couMap.put("priodtype", caccg.getPriodtype());
				couMap.put("sdate", caccg.getSdate());
				couMap.put("edate", caccg.getEdate());
				couMap.put("regdt", DateUtil.commonFormatDate(caccg.getRegdt(), 0));
				couMap.put("ability", caccg.getAbility());
				couMap.put("price", caccg.getPrice());
				couMap.put("excPrice", String.valueOf(caccg.getExcPrice()));
				couMap.put("maxprice", caccg.getMaxprice());
				frontOrderVO.setApplysno(String.valueOf(caccg.getSno()));

				// 쿠폰타입이 0 운영자 발급 / 2 회원가입자동발급 / 3 구매후 자동발급 인 경우
				if (!"1".equals(caccg.getCoupontype())) {
					List<GdCouponOrder> coList = this.getFrontCouponOrder(frontOrderVO);
					if ("0".equals(coList.get(0).getCnt())) {
						int dcprice = ShopLibFunction.getDcprice(Integer.toString(priceSum2), caccg.getPrice());
						if (!"".equals(caccg.getMaxprice()) && dcprice > StringUtil.N2D(caccg.getMaxprice()))
							dcprice = StringUtil.N2I(caccg.getMaxprice());
						
						apr = priceSum2 - dcprice;
						couMap.put("apr", Integer.toString(apr));
						listCou.add(couMap);

					}

					// 쿠폰타입이 1 회원직접다운로드 인 경우
				} else {
					if (listGoodsno.contains(caccg.getGoodsno())) {
						String gPrice = "";
						int gEa = 1;

						if (!"".equals(caccg.getEactl())) {
							for (int jj = 0; jj < cartList10.size(); jj++) {
								if (String.valueOf(caccg.getGoodsno()).equals(cartList10.get(jj).getGoodsno())) {

									gPrice = cartList10.get(jj).getPrice();
									gEa = Integer.parseInt(cartList10.get(jj).getEa());
									
									int dcprice = ShopLibFunction.getDcprice(gPrice, caccg.getPrice());
									if (!"".equals(caccg.getMaxprice()) && dcprice > StringUtil.N2D(caccg.getMaxprice()))
										dcprice = StringUtil.N2I(caccg.getMaxprice());
																		
									apr += (StringUtil.N2D(gPrice) - dcprice) * gEa;
									
									couMap.put("apr", Integer.toString(apr));
									listCou.add(couMap);

								}
							}
						} else {
							for (int jj = 0; jj < cartList10.size(); jj++) {
								if (String.valueOf(caccg.getGoodsno()).equals(cartList10.get(jj).getGoodsno())) {
									int dcprice = ShopLibFunction.getDcprice(Integer.toString(priceSum2), caccg.getPrice());
									if (!"".equals(caccg.getMaxprice()) && dcprice > StringUtil.N2D(caccg.getMaxprice()))
										dcprice = StringUtil.N2I(caccg.getMaxprice());
									
									apr = priceSum2 - dcprice;
									couMap.put("apr", Integer.toString(apr));
									listCou.add(couMap);
								}
							}
						}
					}
				}
			}
		}
		return listCou;
	}

	private void setOpt1AndOpt2(String opt1AndOpt2, FrontOrderVO frontOrderVO) {
		if(logger.isDebugEnabled()) {
			logger.debug(" >> setOpt1AndOpt2 ");
		}
		
		String opt1 = "";
		String opt2 = "";
		if (opt1AndOpt2 != null && !opt1AndOpt2.equals("")
				&& !opt1AndOpt2.equals("NULL")) {
			opt1 = opt1AndOpt2.split("\\|")[0];
			//opt2 = opt1AndOpt2.split("\\|")[1];
		} else {
			opt1 = "";
			opt2 = "";
		}
//		frontOrderVO.setOpt1(opt1);
//		frontOrderVO.setOpt2(opt2);
	}
	
	// 옵션이 있다면 옵션가를 addPriceSum 에 합산.
	private int addOptionPrice(String addOpt, FrontOrderVO frontOrderVO) {
		if (logger.isDebugEnabled()) {
			logger.debug(" >> addOptionPrice ");
		}
		int addPriceSum = 0;
		if (addOpt != null && !addOpt.equals("") && !addOpt.equals("NULL")) {
			String[] addoptArr = addOpt.split("\\|");
			for (String addoptStr : addoptArr) {
				if (addoptStr.split("\\^")[3] != null) {
					addPriceSum += StringUtil.N2D(addoptStr.split("\\^")[3]);
				}
			}
		}
		return addPriceSum;
	}
	
	// 적립금 설정
	private int getSavedMoney(int goodsSellingPrice) {
		if (logger.isDebugEnabled()) {
			logger.debug(" >> getSavedMoney ");
		}
		
		int savedMoney = 0;
		
		if ("0".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
			savedMoney = goodsSellingPrice / 100 * StringUtil.N2I(ShopConfig.getProperty("emoney_goods_emoney"));
		}

		if ("1".equals(ShopConfig.getProperty("emoney_chk_goods_emoney"))) {
			savedMoney = StringUtil.N2I(ShopConfig.getProperty("emoney_goods_emoney"));
		}
		
		return savedMoney;
	}
	/**
	 * addopt 의 옵션 문자열을 파싱하여 옵션 정보를 얻어냄.
	 * */
	public String[] setBuyNow(String oriCookie, FrontOrderVO frontOrderVO)
			throws NumberFormatException, Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(" >> setBuyNow ");
			logger.debug("oriCookie >> " + oriCookie);
		}
		
		/*** 우선 선언이 필요한 변수 선언 ***/
		// 상품의 옵션 데이터를 문자열 배열로 저장. (한 개의 옵션이 하나의 방에 저장됨.)
		String[] optionArr = oriCookie.split("\\,");

		// 상품이 저장될 리스트
		List<Map<String, String>> cartList10 = new ArrayList<Map<String, String>>();

		// 상품번호가 저장될 리스트
		List<String> listGoodsno = new ArrayList<String>();

		// 원가 + 옵션가가 계산되어 저장될 변수
		int priceSum2 = 0;

		/*** 데이터 파싱 ***/
		for (String option : optionArr) {

			// 옵션 데이터 나누기
			String[] optionData = option.split("\\|\\|\\|");
			String goodsno = optionData[0]; // 상품번호
			String opt1 = optionData[1];	// 상품옵션번호
			//String opt1Opt2 = optionData[1]; // 옵션1, 옵션2 정보 (현재 미사용)
			// 옵션가 합산 구하기
			int totalOptionPrice = this.addOptionPrice(optionData[2], frontOrderVO);
			String ea = optionData[3]; // 수량정보

			// 상품번호 세팅
			frontOrderVO.setGoodsno(goodsno);
			frontOrderVO.setOpt1(opt1);
			listGoodsno.add(goodsno);

			// opt1, opt2 구하기 - 현재 opt1, opt2 는 사용하지 않음.
			//this.setOpt1AndOpt2(opt1Opt2, frontOrderVO);
			
			// DB에서 상품금액정보 가져와 금액계산에 사용될 변수에 세팅.
			GdGoodsGoodsoptionGoodslink ggpglList = this.getFrontMypageCartlist(frontOrderVO);
			int goodsSellingPrice = ggpglList.getPrice(); // 원가
			// 상품 적립금 지급에 대한 정책 > 상품적립금 기본설정 > 정율/정액 선택 > 0 : 정율 | 1 : 정액
			int savedMoney = this.getSavedMoney(goodsSellingPrice);
			
			// priceSum2(원가 + 옵션가) = priceInt2(원가) + totalOptionPrice(옵션가);
			priceSum2 = goodsSellingPrice + totalOptionPrice;


			Map<String, String> cartMap = new HashMap<String, String>();
			cartMap.put("goodsnm", ggpglList.getGoodsnm()); // 상품명
			cartMap.put("goodsnmKR", ggpglList.getGoodsnmKR()); // 상품명
			cartMap.put("goodsnmCN", ggpglList.getGoodsnmCN()); // 상품명
			cartMap.put("addPriceSum", new Integer(totalOptionPrice).toString()); // 옵션가
			cartMap.put("img", ggpglList.getImg().replace("|", "")); // 상품이미지파일명
			cartMap.put("price", String.valueOf(goodsSellingPrice)); // 상품원가
			cartMap.put("consumer", new Integer(ggpglList.getConsumer()).toString()); // 리테일가
			cartMap.put("priceSum", new Integer(priceSum2).toString()); // 상품원가 + 옵션가
			cartMap.put("reserve", new Integer(savedMoney).toString());
			cartMap.put("delivery_type", String.valueOf(ggpglList.getDeliverytype())); //
			cartMap.put("goods_delivery", String.valueOf(ggpglList.getGoodsdelivery()));
			cartMap.put("use_emoney", ggpglList.getUseemoney());
			cartMap.put("goodsno", goodsno);
			cartMap.put("opt1", opt1);
//			cartMap.put("opt2", opt2);
			cartMap.put("addopt", optionData[2]); // 옵션 내용(ex>1330^기술^몸통박치기^1000)
			cartMap.put("ea", ea); // 물품 갯수
			cartMap.put("goods_category", ggpglList.getCategory());
			cartMap.put("usestock", ggpglList.getUsestock());

			cartMap.put("cpCouponcd", String.valueOf((ggpglList.getCpCouponcd()!=null)?ggpglList.getCpCouponcd().toString():0)); // 쿠폰번호
			cartMap.put("cpCouponprice", String.valueOf((ggpglList.getCpCouponprice()!=null)?ggpglList.getCpCouponprice().toString():0)); // 쿠폰금액
			cartMap.put("cpMaxprice", String.valueOf((ggpglList.getCpMaxprice()!=null)?ggpglList.getCpMaxprice().toString():0)); // 최대 할인액
			cartMap.put("cpCouponskin", String.valueOf((ggpglList.getCpCouponskin()!=null)?ggpglList.getCpCouponskin().toString():"")); // 쿠폰적용 스킨
			
			cartList10.add(cartMap);

		}
		String[] arr_goodsno = listGoodsno.toArray(new String[listGoodsno.size()]);
		frontOrderVO.setPriceSum2(String.valueOf(priceSum2));
		return arr_goodsno;
	}

	public int getReserveAmount(String price) {
		if (logger.isDebugEnabled()) {
			logger.debug(" >> getReserveAmount");
		}
		String emoneyChkGoodsEmoney = ShopConfig.getProperty("emoney_chk_goods_emoney");
		String emoneyGoodsEmoney = ShopConfig.getProperty("emoney_goods_emoney");

		int reserve = 0;
		if ("0".equals(emoneyChkGoodsEmoney)) {
			reserve = StringUtil.N2I(price) / 100 * StringUtil.N2I(emoneyGoodsEmoney);

			// ShopLibFunction.getDcprice(
			// new Integer(priceSum2).toString(),
			// ShopConfig.getProperty("emoney_goods_emoney")+"%"
			// );
		} else if ("1".equals(emoneyChkGoodsEmoney)) {
			reserve = StringUtil.N2I(emoneyGoodsEmoney);
		}
		return reserve;
	}

	/**선불 및 착불 각각의 총 배송비를 계산하여 TotalDeliveryInfoVO 형태로 리턴합니다.*/
	public TotalDeliveryInfoVO calcTotalDelivery(List<GdGoodsCart> gdGoodsCartList, FrontOrderVO frontOrderVO) {
		// 판매사별 기본배송정책 기준 선불배송비 합산용 Map
		// input data : ("sellerCd", 배송비)
		Map<String, Integer> defaultPrepaymentMap = new HashMap<String, Integer>();
		
		// 상품별 선불배송비 합산용 Map
		// input data : ("goodsno", 배송비)
		Map<String, Integer> goodsPrepaymentMap = new HashMap<String, Integer>();
		
		// 판매사별 상품 총 구매액 합산용 Map
		// input data : ("sellerCd", 구매액 합산)
		Map<String, Integer> totalGoodsPriceMap = new HashMap<String, Integer>();
		
		// 판매사별 선불배송비 총 합산용 Map
		// input data : ("sellerCd", 상품별배송비 적용정책에 따른 선불배송비 합산)
		Map<String, Integer> totalPrepaymentMap = new HashMap<String, Integer>();
		
		// 판매사별 착불배송비 총 합산용 Map
		// input data : ("sellerCd", 착불배송비 합산)
		Map<String, Integer> postPaymentMap = new HashMap<String, Integer>();
		
		// 착불배송비 합산용 변수
		int postPay = 0;
		// 선불배송비 총 합산용 변수
		int prePay = 0;
		
		for (GdGoodsCart cart : gdGoodsCartList) {			
			frontOrderVO.setGoodsno(String.valueOf(cart.getGoodsno()));
			frontOrderVO.setOpt1(String.valueOf(cart.getOpt1()));
			GdGoodsGoodsoptionGoodslink gdList = this.getFrontMypageCartlist(frontOrderVO);	// 상품정보 가져오기
			String sellerCd = gdList.getSellerCd();
			String goodsno = String.valueOf(gdList.getGoodsno());
			// 판매사가 설정한 기본배송정책 가져오기
			GdDeliveryPolicy deliveryPolicy = ShopLibFunction.getDefaultDeliveryPolicy(sellerCd).get(0);
			switch (gdList.getDeliverytype()) {
			// 상품이 기본배송정책 사용중
			case 0:
				// 판매사별 기본배송정책이 선불인 경우 동일 판매사의 기본정책 배송비를 합산하여 저장한다.
				if ("선불".equals(deliveryPolicy.getrDeliType())) {
					// 판매사코드
					// 판매사별 기본배송정책 중 무료 배송비 적용 기준금액
					int freeDeliveryPrice = StringUtil.N2I(deliveryPolicy.getrFree());
					// 판매사별 기본배송정책 중 배송비
					int defaultPrepayment = StringUtil.N2I(deliveryPolicy.getrDefault());  
					// 상품금액
					int goodsPrice = gdList.getPrice();
					// 기존에 저장되어 있던 상품금액
					int beforeGoodsPrice = defaultPrepaymentMap.get(sellerCd) == null ? 0 : defaultPrepaymentMap.get(sellerCd);
					
					// 배송비 맵 세팅
					defaultPrepaymentMap.put(sellerCd, defaultPrepayment);
					// 상품 구입 총액 맵 세팅
					totalGoodsPriceMap.put(sellerCd, goodsPrice + beforeGoodsPrice);
					// 구매한 기본배송정책 적용 상품의 총금액이 기본배송정책 무료적용 금액보다 크다면 배송비 무료 적용
					if (totalGoodsPriceMap.get(sellerCd) >= freeDeliveryPrice) {
						defaultPrepaymentMap.put(sellerCd, 0);
					}
				}
				
				break;
			// 상품이 무료배송
			case 1:
				//무료배송 상품을 같이 주문했을 경우, 배송비를 무조건 무료로 합니다.
				if ("1".equals(deliveryPolicy.getFreeDelivery())) {
					defaultPrepaymentMap.put(sellerCd, 0);
					goodsPrepaymentMap.put(goodsno, 0);
				}
				break;
			// 상품이 상품별 배송비 사용중 (선불)
			case 2:
				int beforeDelPrice = goodsPrepaymentMap.get(goodsno) == null ? 0 : goodsPrepaymentMap.get(goodsno); 
				goodsPrepaymentMap.put(goodsno, beforeDelPrice + gdList.getGoodsdelivery());
				break;
			// 상품이 상품별 배송비 사용중 (착불)
			case 3:
				int savedPostPay = postPaymentMap.get(sellerCd) == null ? 0 : postPaymentMap.get(sellerCd);
				int deliveryPrice = gdList.getGoodsdelivery();
				postPay += deliveryPrice;
				postPaymentMap.put(sellerCd, savedPostPay + deliveryPrice);
				break;
			default:
				break;
			}
			
			int defaultPrepay = defaultPrepaymentMap.get(sellerCd) == null ? 0 : defaultPrepaymentMap.get(sellerCd);
			int goodsPrepay = goodsPrepaymentMap.get(goodsno) == null ? 0 : goodsPrepaymentMap.get(goodsno);
			// 상품을 2개이상 주문시, 상품별 배송비와 기본배송비를 합산한 금액을 배송비로 합니다.
			if ("0".equals(deliveryPolicy.getGoodsDelivery())) {
				totalPrepaymentMap.put(sellerCd, defaultPrepay + goodsPrepay);
			// 상품을 2개이상 주문시, 상품별 배송비와 기본배송비 중 더 큰 배송비로 합니다.
			} else {
				int prePayment = defaultPrepay > goodsPrepay ? defaultPrepay : goodsPrepay;
				totalPrepaymentMap.put(sellerCd, prePayment);
			}
		}
		
		// 최종 선불 배송비 합산
		for (String key : totalPrepaymentMap.keySet()) {
			prePay += totalPrepaymentMap.get(key);
		}
		TotalDeliveryInfoVO deliveryInfoVO = new TotalDeliveryInfoVO();
		deliveryInfoVO.setPostpaidDelivery(String.valueOf(postPay));
		deliveryInfoVO.setPrepaidDelivery(String.valueOf(prePay));
		deliveryInfoVO.setTotalPrepaymentMap(totalPrepaymentMap);
		deliveryInfoVO.setPostPaymentMap(postPaymentMap);
		
		return deliveryInfoVO;

	}
	
	/**선불 및 착불 각각의 총 배송비를 계산하여 TotalDeliveryInfoVO 형태로 리턴합니다.*/
	public TotalDeliveryInfoVO calcTotalDelivery(String deliveryPolicyNo, GdGoodsGoodsoptionGoodslink ggpgl, FrontOrderVO frontOrderVO) {
		GdGoodsCart gdGoodsCart = new GdGoodsCart();
		gdGoodsCart.setDeliveryPolicyNo(deliveryPolicyNo);
		gdGoodsCart.setGoodsno(ggpgl.getGoodsno());
		gdGoodsCart.setSellerCd(ggpgl.getSellerCd());
		gdGoodsCart.setOpt1(frontOrderVO.getOpt1());
		List<GdGoodsCart> gdGoodsCartList = new ArrayList<GdGoodsCart>();
		gdGoodsCartList.add(gdGoodsCart);
		
		return this.calcTotalDelivery(gdGoodsCartList, frontOrderVO);
	}
	
	public Set<String> getDetailCategories(List<String> goodsCategories) {
		Set<String> detailCategories = new LinkedHashSet<String>();
		for(String category : goodsCategories) {
			String [] categories = this.getCategoryArr(category);
			detailCategories.addAll(Arrays.asList(categories));
		}
		return detailCategories;
	}
	
	/**카테고리 정보로 모든 카테고리 정보를 담고있는 배열을 반환합니다.*/
	public String [] getCategoryArr(String category) {
		String [] categoryArr = new String[category.length() / 3];
		int categoryArrSize = categoryArr.length;
		for(int i = 0; i < categoryArrSize; i++) {
			categoryArr[i] = category.substring(0, (i + 1) * 3);
		}
		return categoryArr;
	}
	
	public void setOrderAdminSettings(FrontOrderAdminSettingVO adminSettings) {
		//쿠폰 적용여부
		adminSettings.setCouponUseYn(ShopConfig.getProperty("use_yn"));
		//쿠폰 중복사용여부 프론트 미구현
		adminSettings.setCouponUseMultiple(ShopConfig.getProperty("int"));
	}

	
	public void setMemberGroupFreeDelivery(HttpServletRequest request, TotalDeliveryInfoVO totalDelivery) {
		// 배송비 무료 회원인지 체크하여 배송비 무료 설정
		ShopSessionObject sessionObject = ShopSessionObject.getSessionObject(request);
		GdMemberGrp memberGroup = this.getUserGroup(sessionObject);
		if(memberGroup.getFreeDeliveryfee().equals("Y")) {
			totalDelivery.setPrepaidDelivery("0");
		}
	}

	public GdMemberGrp getUserGroup(ShopSessionObject sessionObject) {
		GdMemberGrp memberGroup = new GdMemberGrp();
		if(this.isLogin(sessionObject)){
			String level = String.valueOf(sessionObject.getUserInfo().getXkey().get("level"));
			memberGroup = this.getFrontDeliveryGrp(level); 
		}
		return memberGroup;
	}
	
	public boolean isLogin(ShopSessionObject userSO) {
		return null != userSO.getUserInfo() && userSO.isShopLogin();
	}
	
	public String indb(FrontOrderSettleVO frontOrderSettleVO
			, HttpSession session
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model
			) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("frontOrderService >> indb");
		}
		ShopSessionObject shopSessionObject = ShopSessionObject.getSessionObject(req);
		UserInfo userInfo = shopSessionObject.getUserInfo();
		
		String resultPage = "";
		List<GdDeliveryPolicy> rtDelivery= null; 
		List<FrontOrderCartMapVO> cartList = null;
		List<GdGoodsOption> rtSupply = null;
		List<GoodsGoodsbrand> rtBrandMaker = null;
		String ordno = StringUtil.N2S(frontOrderSettleVO.getOrdno(), "");
		String nameOrder = StringUtil.N2S(frontOrderSettleVO.getNameOrder(), "");
		String phoneOrder = StringUtil.N2S(frontOrderSettleVO.getPhoneOrder(), "");
		String mobileOrder = StringUtil.N2S(frontOrderSettleVO.getMobileOrder(), "");
		String email = StringUtil.N2S(frontOrderSettleVO.getEmail(), "");
		String nameReceiver = StringUtil.N2S(frontOrderSettleVO.getNameReceiver(), "");
		String phoneReceiver = frontOrderSettleVO.getPhoneReceiver();
		String mobileReceiver = frontOrderSettleVO.getMobileReceiver();
		String zipcode = frontOrderSettleVO.getZipcode();
		String address = frontOrderSettleVO.getAddress();
		//String address_sub = frontOrderSettleVO.getAddress_sub();
		String memo = frontOrderSettleVO.getMemo();
		int deliPoli = Integer.parseInt(frontOrderSettleVO.getDeliPoli() == null ? "0" : frontOrderSettleVO.getDeliPoli());
		String settleprice = StringUtil.N2S(frontOrderSettleVO.getSettleprice(),"0");
		String timestamp = StringUtil.N2S(frontOrderSettleVO.getTimestemp(),DateUtil.getDateString());
		String deliprice = StringUtil.N2S(frontOrderSettleVO.getDeliprice(), "0");
		String sellerAddDelivery [] = StringUtil.N2S(frontOrderSettleVO.getSellerAddDelivery(), "0").split(",");		// 추가배송비 추가	japark 20190805
		String bankAccount = StringUtil.N2S(frontOrderSettleVO.getBankAccount(), "0");
		String bankSender = StringUtil.N2S(frontOrderSettleVO.getBankSender(), "");
		String m_no = String.valueOf(userInfo.getM_no());
		String ip = req.getRemoteAddr();
		String referer = StringUtil.N2S(frontOrderSettleVO.getReferer(), "");
		String coupon = StringUtil.N2S(frontOrderSettleVO.getCoupon(), "0");
		String coupon_emoney = StringUtil.N2S(frontOrderSettleVO.getCoupon_emoney(), "0");
		String couponSno [] = StringUtil.N2S(frontOrderSettleVO.getCoupon_sno(), "0").split(",");
		String settlekind = frontOrderSettleVO.getSettlekind();
		String emoney = StringUtil.N2S(frontOrderSettleVO.getEmoney(),"0");
		String cashbagcard = StringUtil.N2S(frontOrderSettleVO.getCashbag(), "");
				
		//** 추가 항목들*//
		String goodsnm = StringUtil.N2S(frontOrderSettleVO.getGoodsnm(),"Order");
		String goodsnmKR = StringUtil.N2S(frontOrderSettleVO.getGoodsnmKR(),"주문");
		String goodsnmCN = StringUtil.N2S(frontOrderSettleVO.getGoodsnmCN(),"Order");
		String totalPrice = StringUtil.N2S(frontOrderSettleVO.getTotalPrice(),"");
		//** 추가 항목들*//
		String deli_title = "";
		String deli_msg = "";
		String deli_type = "";
		
		int totalAddDelivety = 0;		// 추가배송비 총금액

		// 판매사별 추가배송료 합산
		if(null != sellerAddDelivery){
			for (int i = 0; i < sellerAddDelivery.length; i++) {
				totalAddDelivety += StringUtil.N2D(sellerAddDelivery[i].substring(sellerAddDelivery[i].indexOf("_")+1));
			}
		}
		int i = 0;
			
		//주문번호 체크
		if("".equals(ordno)){
			frontOrderSettleVO.setErrorMsg("주문번호가 없습니다.");
			return "";
		}

		//주문번호 중복 체크
		frontOrderSettleVO.setOrdno(ordno);
		List<GdOrder> onoList = this.getFrontOrderCheck(frontOrderSettleVO);
		if(onoList.size()>0){
			frontOrderSettleVO.setErrorMsg("동일한 주문번호가 존재합니다");
		}
		
		//배송정보 가져오기
		rtDelivery = this.getDeliveryPolicy();
		
		if(0 == deliPoli){
			deli_title = "기본 배송";
			deli_type = ShopConfig.getProperty("delivery_deliveryType");
			if("후불".equals(deli_type)){
				deli_msg = ShopConfig.getProperty("delivery_default_msg");
			}
		}else{
			deli_title = rtDelivery.get(deliPoli-1).getrDelivery();
			deli_type = rtDelivery.get(deliPoli-1).getrDeliType();
			deli_msg = rtDelivery.get(deliPoli-1).getrDefaultMsg();
		}
		
		int reserve = 0;
		int priceInt = 0;
		int dcprice = StringUtil.N2I(StringUtil.N2S(frontOrderSettleVO.getDcprice(), "0"));
		String dc = StringUtil.N2S(WebUtils.getCookie(req, "dc").getValue(), "0");

		if(StringUtil.nullConv((String)session.getAttribute("mode")).equals("memCart")){
			cartList = (List<FrontOrderCartMapVO>)session.getAttribute("cartList");
			for(i=0;i<cartList.size();i++){
				FrontOrderCartMapVO result = cartList.get(i);
				if(i==0){
					goodsnm = result.getGoodsnm();
					goodsnmKR = result.getGoodsnmKR();
					goodsnmCN = result.getGoodsnmCN();
				}else{
					goodsnm += "," + result.getGoodsnm();
					goodsnmKR += "," + result.getGoodsnmKR();
					goodsnmCN += "," + result.getGoodsnmCN();
				}
			}
		}
		
		int reserveSum = 0;
		int priceSum = 0;

		if(cartList != null) { 
			int priceItem = 0;
			for(i = 0; i < cartList.size(); i++) {
				
				FrontOrderCartMapVO cartMapVO = cartList.get(i);

				int reserveInt = 0;
				priceInt = 0;
				int eaInt = 0;
				int addpriceInt = 0;
				
				if(cartMapVO.getReserve() != null){
					reserveInt = StringUtil.N2I(cartMapVO.getReserve());
				}
				if(cartMapVO.getPrice() != null){
					priceInt = StringUtil.N2I(cartMapVO.getPrice());
				}
				if(cartMapVO.getEa() != null){
					eaInt = Integer.parseInt(cartMapVO.getEa());
				}
				// 옵션가격 수정
				if(cartMapVO.getAddopt() != null && !(cartMapVO.getAddopt().equals("") || "NULL".equals(cartMapVO.getAddopt()))) {
					String[] addoptArr = cartMapVO.getAddopt().split("\\|");
					for (int k = 0; k < addoptArr.length; k++) {
						if (addoptArr[k].split("\\^")[3] != null) {
							addpriceInt += StringUtil.N2D(addoptArr[k].split("\\^")[3]);
						}
					}
				}
				reserveSum += reserveInt;
				priceItem = (priceInt * eaInt) + addpriceInt ;		// 상품가격 * 수량 + 옵션가격
				priceSum += priceItem;
				
				/*if(!"0".equals(dc)){
					dcprice = priceSum - ShopLibFunction.getDcprice(Integer.toString(priceSum), dc+"%");
				}*/
			}
		}
		//적립금 사용시 상품적립금 지급여부 - 적립금을 사용하면 구매하려는 상품의 적립금을 지급하지 않습니다.
		if("1".equals(ShopConfig.getProperty("emoney_limit")) && StringUtil.N2D(emoney) > 0){
			reserveSum = 0;
		}
		
		//주문 정보 저장
		frontOrderSettleVO.setOrdno(ordno);
		frontOrderSettleVO.setNameOrder(nameOrder);
		frontOrderSettleVO.setEmail(email);
		frontOrderSettleVO.setPhoneOrder(phoneOrder);
		frontOrderSettleVO.setMobileOrder(mobileOrder);
		frontOrderSettleVO.setNameReceiver(nameReceiver);
		frontOrderSettleVO.setPhoneReceiver(phoneReceiver);
		frontOrderSettleVO.setMobileReceiver(mobileReceiver);
		frontOrderSettleVO.setZipcode(zipcode);
		//frontOrderSettleVO.setAddress(address +" "+address_sub);
		frontOrderSettleVO.setSettlekind(settlekind);
		frontOrderSettleVO.setSettleprice(settleprice);
		frontOrderSettleVO.setPrn_settleprice(settleprice);
		frontOrderSettleVO.setGoodsprice(frontOrderSettleVO.getPriceSum());
		frontOrderSettleVO.setDeli_title(deli_title);
		frontOrderSettleVO.setDelivery(deliprice);
		frontOrderSettleVO.setAddDelivery(Integer.toString(totalAddDelivety));
		frontOrderSettleVO.setDeli_type(deli_type);
		frontOrderSettleVO.setDeli_msg(deli_msg);
		frontOrderSettleVO.setCoupon(coupon);
		frontOrderSettleVO.setEmoney(emoney);
		frontOrderSettleVO.setMemberdc(Integer.toString(dcprice));
		frontOrderSettleVO.setReserve(Integer.toString(reserveSum));
		frontOrderSettleVO.setEggFee(StringUtil.N2S(frontOrderSettleVO.getEggFee(), "0"));
		frontOrderSettleVO.setBankAccount(bankAccount);
		frontOrderSettleVO.setBankSender(bankSender);
		frontOrderSettleVO.setM_no(m_no);
		frontOrderSettleVO.setIp(ip);
		frontOrderSettleVO.setReferer(referer);
		frontOrderSettleVO.setMemo(memo);
		frontOrderSettleVO.setCoupon_emoney(coupon_emoney);
		frontOrderSettleVO.setCashbagcard(cashbagcard);
		frontOrderSettleVO.setTimestamp(timestamp);
		frontOrderSettleVO.setAgent(AgentUtil.getAgent(req));


		logger.debug("==================주문정보확인=====================");
		logger.debug("ordno--------------------->"+ordno);
		logger.debug("nameOrder----------------->"+nameOrder);
		logger.debug("email--------------------->"+email);
		logger.debug("mobileOrder--------------->"+mobileOrder);
		logger.debug("nameReceiver-------------->"+nameReceiver);
		logger.debug("mobileReceiver------------>"+mobileReceiver);
		logger.debug("zipcode------------------->"+zipcode);
		logger.debug("address------------------->"+address);
		//logger.debug("address_sub--------------->"+address_sub);
		logger.debug("settlekind---------------->"+settlekind);
		logger.debug("settleprice--------------->"+settleprice);
		logger.debug("prn_settleprice----------->"+settleprice);
		logger.debug("goodsprice---------------->"+Integer.toString(priceSum));
		logger.debug("deli_title---------------->"+deli_title);
		logger.debug("delivery------------------>"+deliprice);
		logger.debug("deli_type----------------->"+deli_type);
		logger.debug("deli_msg------------------>"+deli_msg);
		logger.debug("coupon-------------------->"+coupon);
		logger.debug("emoney-------------------->"+emoney);
		logger.debug("reserve------------------->"+reserve);
		logger.debug("eggFee-------------------->"+StringUtil.N2S(frontOrderSettleVO.getEggFee(), "0"));
		logger.debug("bankAccount--------------->"+bankAccount);
		logger.debug("bankSender---------------->"+bankSender);
		logger.debug("m_no---------------------->"+m_no);
		logger.debug("ip------------------------>"+ip);
		logger.debug("referer------------------->"+referer);
		logger.debug("memo---------------------->"+memo);
		logger.debug("coupon_emoney------------->"+coupon_emoney);
		logger.debug("cashbagcard--------------->"+cashbagcard);
		logger.debug("==================주문정보확인 끝!!=====================");

		try{
			this.insertFrontOrder(frontOrderSettleVO);
		}catch (Exception e){
			e.printStackTrace();
			frontOrderSettleVO.setErrorMsg("정삭적으로 주문 접수가 되지 않았습니다.");
			return null;
		}
		// 사용한 쿠폰내역
		List<CouponapplyApplymemberCouponCategoryGoodsno> usedCouponInfoList = (List<CouponapplyApplymemberCouponCategoryGoodsno>) session.getAttribute("usedCouponInfoList");
		
		//주문상품 및 배송비 저장
		if (cartList != null) {
			for (FrontOrderCartMapVO result : cartList) {

				// 주문건 별로 사용 된 쿠폰내역을 가져와서 저장
				int usedCouponPrice = 0;
				if(null != usedCouponInfoList && 0 < usedCouponInfoList.size()){
					for (int j = 0; j < usedCouponInfoList.size(); j++) {
						// 특정상품이나 카테고리 할인쿠폰일경우
						if(usedCouponInfoList.get(j).getCoupontype().equals("1")){
							if(result.getGoodsno().equals(String.valueOf(usedCouponInfoList.get(j).getGoodsno()))){
								// 해당주문건 상품에 쿠폰할인이 적용된 가격
								usedCouponPrice += StringUtil.N2D(usedCouponInfoList.get(j).getDcPrice());
							}
						}
					}
				}
				String cartReserve = result.getReserve();
				//적립금 사용시 상품적립금 지급여부 - 적립금을 사용하면 구매하려는 상품의 적립금을 지급하지 않습니다.
				if("1".equals(ShopConfig.getProperty("emoney_limit")) && StringUtil.N2D(emoney) > 0){
					cartReserve = "0";
				}
				
				priceInt = 0;
				int eaInt =  Integer.parseInt(result.getEa());
				frontOrderSettleVO.setGoodsno(result.getGoodsno());
				frontOrderSettleVO.setOpt1(result.getOpt1());
				frontOrderSettleVO.setOpt2(result.getOpt2());
				frontOrderSettleVO.setGoodsnm(result.getGoodsnm());
				frontOrderSettleVO.setPrice(result.getPrice());
				frontOrderSettleVO.setReserve(cartReserve);
				frontOrderSettleVO.setEa(result.getEa());
				if(result.getUsestock().equals("o")){
					frontOrderSettleVO.setStockyn("y");
				}else{
					frontOrderSettleVO.setStockyn("n");
				}
				rtSupply = this.getFrontGoodsSupply(frontOrderSettleVO);
				frontOrderSettleVO.setSupply(String.valueOf(rtSupply.get(0).getSupply()));
				rtBrandMaker = this.getFrontGoodsBrand(frontOrderSettleVO);
				frontOrderSettleVO.setBrandnm(rtBrandMaker.get(0).getBrandnm());
				frontOrderSettleVO.setTax(String.valueOf(rtBrandMaker.get(0).getTax()));
				frontOrderSettleVO.setCoupon(String.valueOf(usedCouponPrice));
				if(result.getPrice() != null){
					priceInt = StringUtil.N2I(result.getPrice());
				}
				// 각 판매상품에 대한 회원할인율 적용 
				if(!"0".equals(dc)){
					dcprice = (int) (priceInt * eaInt * (Integer.parseInt(dc) * 0.01));
				}
				frontOrderSettleVO.setMemberdc( Integer.toString(dcprice));
				String addopt = result.getAddopt();

				frontOrderSettleVO.setAddopt(addopt);
				this.insertFrontOrderGoods(frontOrderSettleVO);
				
				
				// gd_order_delivery 배송비테이블 저장용 변수 세팅
				TotalDeliveryInfoVO deliveryInfo = (TotalDeliveryInfoVO)session.getAttribute("totalDeliveryInfoVO");
				String sellerCd = result.getSellerCd();
				Map<String, Integer> prePaymentMap = deliveryInfo.getTotalPrepaymentMap();
				Map<String, Integer> postPaymentMap = deliveryInfo.getPostPaymentMap();
				int deliveryPrePay = prePaymentMap.get(sellerCd) == null ? 0 : prePaymentMap.get(sellerCd);
				int deliveryPostPay = postPaymentMap.get(sellerCd) == null ? 0 : postPaymentMap.get(sellerCd);
				int addDelivery = 0;
				
				// 판매사별 추가배송료
				if(null != sellerAddDelivery && null != sellerCd){
					for (int j = 0; j < sellerAddDelivery.length; j++) {
						if(sellerAddDelivery[j].contains(sellerCd)){
							addDelivery = StringUtil.N2I(sellerAddDelivery[j].substring(sellerAddDelivery[j].indexOf("_")+1));
							break;
						}
					}
				}
				frontOrderSettleVO.setAddDeliveryPrice(addDelivery);
				
				// 선불 착불 모두 0원이면 배송비 무료
				if (deliveryPrePay == 0 && deliveryPostPay == 0) {
					//20191210 이현빈 배송비세팅 
					frontOrderSettleVO.setDeliveryPrice(deliveryPrePay);
					frontOrderSettleVO.setPaymentTerms(0);
				// 선불배송비가 있으면 선불로 세팅
				} else if (deliveryPrePay != 0) {
					frontOrderSettleVO.setPaymentTerms(1);
					frontOrderSettleVO.setDeliveryPrice(deliveryPrePay);
				// 아니면 착불로 세팅
				} else {
					frontOrderSettleVO.setPaymentTerms(2);
					frontOrderSettleVO.setDeliveryPrice(deliveryPostPay);
				}
				
				
				this.insertFrontOrderDelivery(frontOrderSettleVO);
			}
		}

		String ua=req.getHeader("User-Agent").toLowerCase();
		logger.debug("@@@@@@@@@settlekind::"+settlekind);
		logger.debug("@@@@@@@@@ShopConfig.getProperty('settlePg').::"+ShopConfig.getProperty("settlePg"));
		if("a".equals(settlekind)){
			//결제 완료 페이지 이동
			resultPage="redirect:/shop/order/order_end?ordno="+ordno;
		}else{ 
			if(ShopConfig.getProperty("settlePg").equals("kcp")){ //개발 안됨
				if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")){
					resultPage="redirect:/kcp/mobile_sample/order_mobile?settlekind="+settlekind+"&ordno="+ordno+"&goodsnm="+goodsnm+"&nameOrder="+nameOrder+"&email="+email+"&phoneOrder="+phoneOrder+"&mobileOrder="+mobileOrder+"&totalPrice="+totalPrice;
				}else{
					resultPage="redirect:/kcp/sample/order?settlekind="+settlekind+"&ordno="+ordno+"&goodsnm="+goodsnm+"&nameOrder="+nameOrder+"&email="+email+"&phoneOrder="+phoneOrder+"&mobileOrder="+mobileOrder+"&totalPrice="+totalPrice;
				}

			}else if(ShopConfig.getProperty("settlePg").equals("inicis")){ // 개발 안됨
				//KCP연동페이지 이동	
				//out.println("<script>parent.location.replace('/kcp/sample/order.jsp?settlekind="+settlekind+"&ordno="+ordno+"&goodsnm="+goodsnm+"&nameOrder="+nameOrder+"&email="+email+"&phoneOrder="+phoneOrder+"&mobileOrder="+mobileOrder+"&totalPrice="+totalPrice+"');</script>");		// 로컬
				//out.println("<script>parent.location.replace('/kcp/sample/order.jsp?settlekind="+settlekind+"&ordno="+ordno+"&goodsnm="+goodsnm+"&nameOrder="+nameOrder+"&email="+email+"&phoneOrder="+phoneOrder+"&mobileOrder="+mobileOrder+"&totalPrice="+totalPrice+"');</script>");	// 개발서버
				if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")){
	//				out.println("<script>    parent.onSubmit();</script>");
				}else{
	//				out.println("<script>parent.INIStdPay.pay('SendPayForm_id');</script>");
				}
			}else if(ShopConfig.getProperty("settlePg").equals("dacom")){
				if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")){
				}else{
					resultPage = this.requestingPaymentStep2_1(frontOrderSettleVO, req);
				}
			}
		}
		logger.debug("@@@@@@@@@resultPage::"+resultPage);
		return resultPage;
	}

	public String requestingPaymentStep2_1(FrontOrderSettleVO frontOrderSettleVO, HttpServletRequest req) throws Exception {
		ShopSessionObject userSO = ShopSessionObject.getSessionObject(req);
		String resultPage = "";
		if(this.isLogin(userSO)){
			String userId = userSO.getUserInfo().getUserId();
			resultPage ="/shop/order/payreq";//?CST_PLATFORM=test&CST_MID=autoshop&LGD_MID=tautoshop&LGD_OID="+ordno+"&LGD_PRODUCTINFO="+goodsnm+"&LGD_BUYER="+nameOrder+"&LGD_BUYEREMAIL="+email+"&LGD_BUYERIP="+ip+"&LGD_BUYERID="+userId+"&LGD_TIMESTAMP="+timestamp+"&LGD_AMOUNT="+totalPrice;

		    /*
		     * [결제 인증요청 페이지(STEP2-1)]
		     *
		     * 샘플페이지에서는 기본 파라미터만 예시되어 있으며, 별도로 필요하신 파라미터는 연동메뉴얼을 참고하시어 추가 하시기 바랍니다.
		     */
		    /*
		     * 1. 기본결제 인증요청 정보 변경
		     *
		     * 기본정보를 변경하여 주시기 바랍니다.(파라미터 전달시 POST를 사용하세요)
		     */
		     
		 	String decoded1 		= URLDecoder.decode(frontOrderSettleVO.getNameOrder(), "8859_1");
		 	String BUYER 			= new String(decoded1.getBytes("8859_1"),"UTF-8");
		 	
		 	String decoded2			= URLDecoder.decode(StringUtil.N2S(frontOrderSettleVO.getGoodsnm(),"주문"), "8859_1");
		 	String PRODUCTINFO		= new String(decoded2.getBytes("8859_1"),"UTF-8");
		 	/* String han = new String(  URLDecoder.decode(request.getParameter("LGD_BUYER"),"euc-kr").getBytes("8859_1"),"UTF-8"); */
		 	
		 	
		    String CST_PLATFORM		= "test";                                                                 //LG유플러스 결제서비스 선택(test:테스트, service:서비스)
		    String CST_MID			= "autoshop";                                                         //LG유플러스으로 부터 발급받으신 상점아이디를 입력하세요.
		    String LGD_MID			= ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  //테스트 아이디는 't'를 제외하고 입력하세요.
		                                                                                                                  //상점아이디(자동생성)
		    String LGD_OID			= frontOrderSettleVO.getOrdno();                                                                 //주문번호(상점정의 유니크한 주문번호를 입력하세요)
		    String LGD_AMOUNT		= StringUtil.N2S(frontOrderSettleVO.getTotalPrice(),"");                                                            //결제금액("," 를 제외한 결제금액을 입력하세요)
		    String LGD_MERTKEY		= "09c2757fc17ee47d8ea08831201b5498";					  //상점MertKey(mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
		   
		    /* URLDecoder.decode(request.getParameter("LGD_BUYER"),"euc-kr"); */
		    String LGD_BUYER           = StringUtil.N2S(frontOrderSettleVO.getNameOrder(), "");; 												         //구매자명
		    String LGD_PRODUCTINFO = StringUtil.N2S(frontOrderSettleVO.getGoodsnm(),"주문");              							                 //상품명
		    String LGD_BUYEREMAIL   = StringUtil.N2S(frontOrderSettleVO.getEmail(), "");                                                                //구매자 이메일
		    String LGD_TIMESTAMP    = SignatureUtil.getTimestamp();                                                         //타임스탬프
		    String LGD_CUSTOM_SKIN = "red";                                                                //상점정의 결제창 스킨(red, purple, yellow)
		    String LGD_WINDOW_VER  = "2.5";                                                                //결제창 버젼정보
		    String LGD_BUYERID          = userId;       		                                                  //구매자 아이디
		    String LGD_BUYERIP          = req.getRemoteAddr();       			                                                  //구매자IP

		    /*
		     * 가상계좌(무통장) 결제 연동을 하시는 경우 아래 LGD_CASNOTEURL 을 설정하여 주시기 바랍니다. 
		     */    
		    String LGD_CASNOTEURL		= "http://xmall.gnujava.com/shop/order/cas_noteurl.jsp";    

		    /*
		     *************************************************
		     * 2. MD5 해쉬암호화 (수정하지 마세요) - BEGIN
		     *
		     * MD5 해쉬암호화는 거래 위변조를 막기위한 방법입니다.
		     *************************************************
		     *
		     * 해쉬 암호화 적용( LGD_MID + LGD_OID + LGD_AMOUNT + LGD_TIMESTAMP + LGD_MERTKEY )
		     * LGD_MID          : 상점아이디
		     * LGD_OID          : 주문번호
		     * LGD_AMOUNT       : 금액
		     * LGD_TIMESTAMP    : 타임스탬프
		     * LGD_MERTKEY      : 상점MertKey (mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
		     *
		     * MD5 해쉬데이터 암호화 검증을 위해
		     * LG유플러스에서 발급한 상점키(MertKey)를 환경설정 파일(lgdacom/conf/mall.conf)에 반드시 입력하여 주시기 바랍니다.
		     */
		    StringBuffer sb = new StringBuffer();
		    sb.append(LGD_MID);
		    sb.append(LGD_OID);
		    sb.append(LGD_AMOUNT);
		    sb.append(LGD_TIMESTAMP);
		    sb.append(LGD_MERTKEY);

		    byte[] bNoti = sb.toString().getBytes();
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] digest = md.digest(bNoti);

		    StringBuffer strBuf = new StringBuffer();
		    for (int ix=0 ; ix < digest.length ; ix++) {
		        int c = digest[ix] & 0xff;
		        if (c <= 15){
		            strBuf.append("0");
		        }
		        strBuf.append(Integer.toHexString(c));
		    }

		    String LGD_HASHDATA = strBuf.toString();
		    String LGD_CUSTOM_PROCESSTYPE = "TWOTR";
		    /*
		     *************************************************
		     * 2. MD5 해쉬암호화 (수정하지 마세요) - END
		     *************************************************
		     */
		    frontOrderSettleVO.setCST_PLATFORM(CST_PLATFORM);                  
		    frontOrderSettleVO.setLGD_BUYER(LGD_BUYER);                     
		    frontOrderSettleVO.setLGD_BUYERIP(LGD_BUYERIP);                   
		    frontOrderSettleVO.setLGD_BUYERID(LGD_BUYERID);                   
		    frontOrderSettleVO.setLGD_PRODUCTINFO(LGD_PRODUCTINFO);               
		    frontOrderSettleVO.setLGD_AMOUNT(LGD_AMOUNT);                    
		    frontOrderSettleVO.setLGD_BUYEREMAIL(LGD_BUYEREMAIL);                
		    frontOrderSettleVO.setLGD_OID(LGD_OID);                       
		    frontOrderSettleVO.setCST_MID(CST_MID);                       
		    frontOrderSettleVO.setLGD_MID(LGD_MID);                      
		    frontOrderSettleVO.setLGD_CUSTOM_SKIN(LGD_CUSTOM_SKIN);               
		    frontOrderSettleVO.setLGD_WINDOW_VER(LGD_WINDOW_VER);                
		    frontOrderSettleVO.setLGD_CUSTOM_PROCESSTYPE(LGD_CUSTOM_PROCESSTYPE);        
		    frontOrderSettleVO.setLGD_TIMESTAMP(LGD_TIMESTAMP);                 
		    frontOrderSettleVO.setLGD_HASHDATA(LGD_HASHDATA);                  
		    frontOrderSettleVO.setLGD_CASNOTEURL(LGD_CASNOTEURL);                
		}else{
		}
		
		return resultPage;
	}
	
	/** 입력되어 있는 주소와 상품코드 배열로 추가배송비 적용 정보를 가져옵니다.
	 * @param
	 * @return List<OverDeliveryVO>
	 * */
	public List<OverDeliveryVO> getOverDeliveryPolicy(String [] goodsno, String addressSido) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("addressSido", addressSido);
		params.put("goodsnos", goodsno);
		
		
		// 판매사 배송비정책
		List<OverDeliveryVO> overDeliveries = this.getSellerOverDelivery(params);
		
		// 관리자 배송비정책
		Map<String, String> overDeliveryInfo = ShopLibFunction.getOverDeliveryInfo();
		for (String sido : overDeliveryInfo.keySet()) {
			if(sido.contains(addressSido)) {
				OverDeliveryVO deliveryVO = new OverDeliveryVO();
				deliveryVO.setSellerCd("admin");
				deliveryVO.setGoodsno("admin");
				deliveryVO.setPrice(overDeliveryInfo.get(sido));
				deliveryVO.setSido(sido);
				overDeliveries.add(deliveryVO);
			}
		}
		
		return overDeliveries;
	}
	
	/** 입력되어 있는 주소와 상품코드 배열로 추가배송비 적용 정보를 가져옵니다.
	 * 관리자와 판매자가 같은 장소를 등록한경우 이중으로 요금이 부과되어 분리시켰다
	 * @param
	 * @return List<OverDeliveryVO>
	 * */
	public List<OverDeliveryVO> getAdminOverDelivery(String addressSido) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("addressSido", addressSido);
		
		List<OverDeliveryVO> overDeliveries = new ArrayList<OverDeliveryVO>();
		
		// 관리자 배송비정책
		Map<String, String> overDeliveryInfo = ShopLibFunction.getOverDeliveryInfo();
		for (String sido : overDeliveryInfo.keySet()) {
			if(sido.contains(addressSido)) {
				OverDeliveryVO deliveryVO = new OverDeliveryVO();
				deliveryVO.setSellerCd("admin");
				deliveryVO.setGoodsno("admin");
				deliveryVO.setPrice(overDeliveryInfo.get(sido));
				deliveryVO.setSido(sido);
				overDeliveries.add(deliveryVO);
			}
		}
		
		return overDeliveries;
	}
	
	/** 입력되어 있는 주소와 상품코드 배열로 추가배송비 적용 정보를 가져옵니다.
	 *  관리자와 판매자가 같은 장소를 등록한경우 이중으로 요금이 부과되어 분리시켰다
	 * @param
	 * @return List<OverDeliveryVO>
	 * */
	public List<OverDeliveryVO> getsellerOverDelivery(String [] goodsno, String addressSido) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("addressSido", addressSido);
		params.put("goodsnos", goodsno);
		
		// 판매사 배송비정책
		List<OverDeliveryVO> overDeliveries = this.getSellerOverDelivery(params);
				
		return overDeliveries;
	}
	
	/** 상품을 관리자가 등록한 상품과 판매자가 등록한 상품을 분리시켰다
	 *  판매자 코드가있으면 판매자가 등록한 상품 없으면 관리자가 등록한 상품
	 * */
	public List<OrderDeliveryVO> getDivisionOverDeliveryPolicy(String [] goodsno) {

		List<OrderDeliveryVO> division = new ArrayList<OrderDeliveryVO>();
		for(int i=0; i < goodsno.length; i++){
			division.add(mapper.getDivisionOverDeliveryPolicy(goodsno[i]));
		}

		return division;
	
	}
	
	public MemberMemberGrp getOrderMember(FrontOrderVO frontOrderVO) {
		return mapper.getOrderMember(frontOrderVO);
	}

	public GoodsGoodsption getFrontMypageCheckStock(FrontOrderVO frontOrderVO) {
		return mapper.getFrontMypageCheckStock(frontOrderVO);
	}

	public GdGoodsGoodsoptionGoodslink getFrontMypageCartlist(FrontOrderVO frontOrderVO) {
		return mapper.getFrontMypageCartlist(frontOrderVO);
	}

	public List<GdDeliveryPolicy> getDeliveryPolicy() {
		return mapper.getDeliveryPolicy();
	}

	public List<GdGoodsLink> getFrontOrderCategory(FrontOrderVO frontOrderVO) {
		return mapper.getFrontOrderCategory(frontOrderVO);
	}

	public List<GdGoods> getFrontOrderSellerCd(FrontOrderVO frontOrderVO) {
		return mapper.getFrontOrderSellerCd(frontOrderVO);
	}

	public List<OrderMember> getFrontOrderMember(FrontOrderVO frontOrderVO) {
		return mapper.getFrontOrderMember(frontOrderVO);
	}

	public List<GdCouponOrder> getFrontCouponOrder(FrontOrderVO frontOrderVO) {
		return mapper.getFrontCouponOrder(frontOrderVO);
	}

	public List<CouponapplyApplymemberCouponCategoryGoodsno> getFrontOrderCoupon(
			FrontOrderVO frontOrderVO) {
		return mapper.getFrontOrderCoupon(frontOrderVO);
	}

	public List<GdDeliveryPolicy> getDelivery(FrontOrderVO frontOrderVO) {
		return mapper.getDelivery(frontOrderVO);
	}

	public GdMemberGrp getFrontDeliveryGrp(String level) {
		return mapper.getFrontDeliveryGrp(level);
	}

	public List<GdListBank> getOrdersPopupOrder4(FrontOrderVO frontOrderVO) {
		return mapper.getOrdersPopupOrder4(frontOrderVO);
	}

	public List<GdGoodsCart> getGoodsMemberCartList(FrontOrderVO frontOrderVO) {
		return mapper.getGoodsMemberCartList(frontOrderVO);
	}
	
	public int deleteGoodsMemberCartList(FrontOrderVO frontOrderVO) {
		return mapper.deleteGoodsMemberCartList(frontOrderVO);
	}

	public List<GoodsGoodsption> getFrontOrderGoods(FrontOrderVO frontOrderVO) {
		return mapper.getFrontOrderGoods(frontOrderVO);
	}

	public List<GdOrder> getFrontOrderCheck(FrontOrderVO frontOrderVO) {
		return mapper.getFrontOrderCheck(frontOrderVO);
	}

	public int insertFrontOrder(FrontOrderVO frontOrderVO) {
		return mapper.insertFrontOrder(frontOrderVO);
	}

	public List<couponapplyCoupon> getFrontOrderCouponCheck(FrontOrderVO frontOrderVO) {
		return mapper.getFrontOrderCouponCheck(frontOrderVO);
	}

	public void updateFrontCoupon(FrontOrderVO frontOrderVO) {
		mapper.updateFrontCoupon(frontOrderVO);
	}

	public void insertFrontOrderCoupon(FrontOrderVO frontOrderVO) {
		mapper.insertFrontOrderCoupon(frontOrderVO);
	}

	public List<GdGoodsOption> getFrontGoodsSupply(FrontOrderVO frontOrderVO) {
		return mapper.getFrontGoodsSupply(frontOrderVO);
	}

	public List<GoodsGoodsbrand> getFrontGoodsBrand(FrontOrderVO frontOrderVO) {
		return mapper.getFrontGoodsBrand(frontOrderVO);
	}

	public void insertFrontOrderGoods(FrontOrderVO frontOrderVO) {
		mapper.insertFrontOrderGoods(frontOrderVO);
	}

	public List<MemberMemberGrp> getOrderMember(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.getOrderMember(frontOrderSettleVO);
	}

	public List<GdOrder> getFrontOrderCheck(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.getFrontOrderCheck(frontOrderSettleVO);
	}

	public List<GdGoodsGoodsoptionGoodslink> getFrontMypageCartlist(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.getFrontMypageCartlist(frontOrderSettleVO);
	}

	public List<couponapplyCoupon> getFrontOrderCouponCheck(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.getFrontOrderCouponCheck(frontOrderSettleVO);
	}

	public void updateFrontCoupon(FrontOrderSettleVO frontOrderSettleVO) {
		mapper.updateFrontCoupon(frontOrderSettleVO);
	}

	public void insertFrontOrderCoupon(FrontOrderSettleVO frontOrderSettleVO) {
		mapper.insertFrontOrderCoupon(frontOrderSettleVO);
	}

	public List<GdGoodsOption> getFrontGoodsSupply(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.getFrontGoodsSupply(frontOrderSettleVO);
	}

	public List<GoodsGoodsbrand> getFrontGoodsBrand(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.getFrontGoodsBrand(frontOrderSettleVO);
	}

	public int insertFrontOrderGoods(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.insertFrontOrderGoods(frontOrderSettleVO);
	}

	public int insertFrontOrder(FrontOrderSettleVO frontOrderSettleVO) {
		return mapper.insertFrontOrder(frontOrderSettleVO);
	}

	public GdOrderListBank frontOrderResult(Long ordno) {
		return mapper.frontOrderResult(ordno);
	}

	public void frontOrderEndUpdate(Map<String, Object> map) {
		this.mapper.frontOrderEndUpdate(map);
	}

	public void frontOrderItemUpdate(Map<String, Object> map) {
		this.mapper.frontOrderItemUpdate(map);
	}

	public void frontCouponStatusUpdate(String [] usedCouponSnos, int mno, Long ordno) {
		this.mapper.frontCouponStatusUpdate(usedCouponSnos, mno, ordno);
	}
	public List<Long> selectApplysnoFrontCouponStatus(String ordno){
	  return	this.mapper.selectApplysnoFrontCouponStatus(ordno);
	}
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getFrontCouponList (Map<String, Object> paramMap) {
		return this.mapper.getFrontCouponList(paramMap);
	}
	
	public List<String> getGoodsCategory(@Param("goodsnos") String [] goodsnos) {
		return this.mapper.getGoodsCategory(goodsnos);
	}
	
	/** gd_order_delivery 배송정보 테이블 insert */
	public void insertFrontOrderDelivery(FrontOrderSettleVO frontOrderSettleVO) {
		this.mapper.insertFrontOrderDelivery(frontOrderSettleVO);
	}
	
	/** 주문페이지 상품번호로 주문사별 추가배송비 정책 가져오기 */
	public List<OverDeliveryVO> getSellerOverDelivery(Map<String, Object> map) {
		return this.mapper.getSellerOverDelivery(map);
	}
	
	public FrontOrderSettleVO getOrderEndInfo(Map<String, Object> map) {
		return this.mapper.getOrderEndInfo(map);
	}
	
	public int insertEmoneyLog(Map<String, Object> map) {
		return this.mapper.insertEmoneyLog(map);
	}
	
	public int updateMemberEmoney(Map<String, Object> map) {
		return this.mapper.updateMemberEmoney(map);
	}
	
	public GdCoupon getFrontOrderCouponCodeByCouponcd(String couponcd) {
		return this.mapper.getFrontOrderCouponCodeByCouponcd(couponcd);
	}
	
	public GdCoupon getFrontOrderDcCodeInfo(GdCoupon gdCoupon) {
		return this.mapper.getFrontOrderDcCodeInfo(gdCoupon);
	}
	
	public int getFrontOrderCouponCodeCnt(GdCoupon gdCoupon) {
		return this.mapper.getFrontOrderCouponCodeCnt(gdCoupon);
	}
	
	public int insertCouponApply(Map<String, Object> map) {
		int sno = this.mapper.selectCouponApplySno();
		map.put("sno", sno);
		this.mapper.insertCouponApply(map);
		return sno;
	}
	
	public int updateCouponApplymember(Map<String, Object> map) {
		return this.mapper.updateCouponApplymember(map);
	}
	public int insertCouponApplymember(Map<String, Object> map) {
		return this.mapper.insertCouponApplymember(map);
	}
	
	public int insertCouponOrder(Map<String, Object> map) {
		return this.mapper.insertCouponOrder(map);
	}
	
	public int insertOrderPayLog(Map<String, Object> map) {
		return this.mapper.insertOrderPayLog(map);
	}
	
}