package com.wepinit.wepinit_shop.xmall.api.controller;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.ApiFileUtil;
import com.wepinit.wepinit_shop.xmall.api.service.ApiService;
import com.wepinit.wepinit_shop.xmall.api.vo.*;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.util.BiztalkUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/v1/*")
public class ApiController {

	private static final Logger logger = LoggerFactory.getLogger(ApiController.class); 
	
	@Resource
	ApiService service;

	/**
	 * 상품 추가연동
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="product_add.do", method=RequestMethod.POST)
	public String productAdd(@RequestBody ProductVO productVO, HttpServletRequest req, Model model) {
		try {
			if (productVO.getKey() != null && productVO.getKey().equals(CommonConstants.API_KEY)) {
				logger.debug("productVO : {}", productVO);

				Integer goodsno = service.getGoodsNoByCd(productVO.getGoodsCd());
				if (goodsno != null) {
					model.addAttribute("res_code", "404");
				    model.addAttribute("res_message", "FAIL");
				    model.addAttribute("res_desc", "이미 등록된 상품코드입니다.");
				} else {
					// 이미지 처리
					List<String> img_m = new ArrayList<>();
					if (productVO.getImages().size() > 0) {
						List<ProductImageVO> imgList = ApiFileUtil.getUrlImage(productVO.getGoodsCd(), productVO.getImages(), ConfigClass.UPLOAD_PATH + "/temp/");
						if(!imgList.isEmpty()) {
							for(ProductImageVO imageVO : imgList) {
								if( imageVO.getSeq() == 0 ) {
									if( StringUtils.equals("I", imageVO.getFileType()) ) {
										productVO.setImg_i(imageVO.getImgUrl().replaceAll("\\|", ""));
									}else if( StringUtils.equals("S", imageVO.getFileType()) ) {
										productVO.setImg_s(imageVO.getImgUrl().replaceAll("\\|", ""));
									}else if( StringUtils.equals("L", imageVO.getFileType()) ) {
										productVO.setImg_l(imageVO.getImgUrl().replaceAll("\\|", ""));
									}
								}
								if( StringUtils.equals("L", imageVO.getFileType()) ) {
									img_m.add(imageVO.getImgUrl());
								}
							}
						}
						productVO.setImg_m(String.join("|", img_m));
					}
					
					if (productVO.getOptions().size() > 0) {
						productVO.setUsegoodsadd("1");
						List<String> optnm = new ArrayList<>();
						for (ProductOptionVO optionVO : productVO.getOptions()) {
							if (!optnm.contains(optionVO.getOpt2())) {
								optnm.add(optionVO.getOpt2());
							}
						}
						productVO.setOptnm(String.join("|", optnm));
					} else {
						productVO.setUsegoodsadd("0");
					}
					
					String open = "1";
					//이미지가 없을경우 비게시처리
					if(StringUtils.isBlank(productVO.getImg_i()) || StringUtils.isBlank(productVO.getImg_s()) || StringUtils.isBlank(productVO.getImg_l()) ) {
						open = "0";
					}
					//재고가 없을경우 비게시처리
					if(productVO.getStock() == 0) {
						open = "0";
					}
					//가격이 0일경우 비게시처리
					productVO.setPrice(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRate() / 100));
					if(productVO.getConsumer() == 0 || productVO.getPrice() == 0) {
						open = "0";
					}					
					productVO.setOpen(open);
					productVO.setPriceB2b(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRateB2b() / 100));
					
					service.insertGoods(productVO);
					if (productVO.getGoodsno() > 0) {
						if (!StringUtils.equals("", productVO.getCategoryCd())) {
							service.insertGoodsLink(productVO);
						}
						
						if (productVO.getOptions().size() > 0) {
							for (ProductOptionVO optionVO : productVO.getOptions()) {
								//if (optionVO.getPrice() == 0) {
									optionVO.setPrice(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRate() / 100));
									optionVO.setPriceB2b(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRateB2b() / 100));
								//}
								
								optionVO.setGoodsno(productVO.getGoodsno());
								optionVO.setParent("1");
								int cnt = service.getGoodsOptionCount(optionVO);
								if (cnt > 0) {
									optionVO.setLink(0);
								} else {
									optionVO.setLink(1);
								}
								optionVO.setOptexplain("Color / Size");
								service.insertGoodsOption(optionVO);
								service.updateGoodsOptionOptno(optionVO.getSno());
							}
						}
						
						ProductOptionVO optionVO = new ProductOptionVO();
						optionVO.setGoodsno(productVO.getGoodsno());
						optionVO.setParent("0");
						optionVO.setConsumer(productVO.getConsumer());
						optionVO.setPrice(productVO.getPrice());
						optionVO.setPriceRate(productVO.getPriceRate());
						optionVO.setPriceB2b(productVO.getPriceB2b());
						optionVO.setPriceRateB2b(productVO.getPriceRateB2b());
						optionVO.setSupplyPrice1(productVO.getSupplyPrice1());
						optionVO.setSupplyPrice2(productVO.getSupplyPrice2());
						optionVO.setMargin(productVO.getMargin());
						optionVO.setPriceMyRitz(productVO.getPriceMyRitz());
						optionVO.setStock(productVO.getStock());
						optionVO.setLink(1);
						service.insertGoodsOption(optionVO);
						service.updateGoodsOptionOptno(optionVO.getSno());
						
						
						model.addAttribute("res_code", "200");
					    model.addAttribute("res_message", "SUCCESS");
					    model.addAttribute("res_desc", "성공");
					    model.addAttribute("goodsno", productVO.getGoodsno());
					}
				}
			} else {
				model.addAttribute("res_code", "401");
			    model.addAttribute("res_message", "Unauthorized");
			    model.addAttribute("res_desc", "API 인증키가 유효하지 않습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("res_code", "404");
		    model.addAttribute("res_message", "FAIL");
		    model.addAttribute("res_desc", "등록중 오류가 발생했습니다.");
		}
		
		return "dojson";
	}

	/**
	 * 상품 변경연동(상품정보)
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="product_change.do", method=RequestMethod.POST)
	public String productChange(@RequestBody ProductVO productVO, HttpServletRequest req, Model model) {
		try {
			if (productVO.getKey() != null && productVO.getKey().equals(CommonConstants.API_KEY)) {
				logger.debug("productVO : {}", productVO);
				
				Integer goodsno = service.getGoodsNoByCd(productVO.getGoodsCd());
				if (goodsno != null) {
					productVO.setGoodsno(goodsno);
				
					// 이미지 처리
					List<String> img_m = new ArrayList<>();
					if (productVO.getImages().size() > 0) {
						List<ProductImageVO> imgList = ApiFileUtil.getUrlImage(productVO.getGoodsCd(), productVO.getImages(), ConfigClass.UPLOAD_PATH + "/temp/");
						if(!imgList.isEmpty()) {
							for(ProductImageVO imageVO : imgList) {
								if( imageVO.getSeq() == 0 ) {
									if( StringUtils.equals("I", imageVO.getFileType()) ) {
										productVO.setImg_i(imageVO.getImgUrl().replaceAll("\\|", ""));
									}else if( StringUtils.equals("S", imageVO.getFileType()) ) {
										productVO.setImg_s(imageVO.getImgUrl().replaceAll("\\|", ""));
									}else if( StringUtils.equals("L", imageVO.getFileType()) ) {
										productVO.setImg_l(imageVO.getImgUrl().replaceAll("\\|", ""));
									}
								}
								if( StringUtils.equals("L", imageVO.getFileType()) ) {
									img_m.add(imageVO.getImgUrl());
								}
							}
						}
						productVO.setImg_m(String.join("|", img_m));
					}
					
					if (productVO.getOptions().size() > 0) {
						productVO.setUsegoodsadd("1");
						List<String> optnm = new ArrayList<>();
						for (ProductOptionVO optionVO : productVO.getOptions()) {
							if (!optnm.contains(optionVO.getOpt2())) {
								optnm.add(optionVO.getOpt2());
							}
						}
						productVO.setOptnm(String.join("|", optnm));
					} else {
						productVO.setUsegoodsadd("0");
					}

					String open = "1";
					//이미지가 없을경우 비게시처리
					if(StringUtils.isBlank(productVO.getImg_i()) || StringUtils.isBlank(productVO.getImg_s()) || StringUtils.isBlank(productVO.getImg_l()) ) {
						open = "0";
					}
					//재고가 없을경우 비게시처리
					if(productVO.getStock() == 0) {
						open = "0";
					}
					//가격이 0일경우 비게시처리
					productVO.setPrice(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRate() / 100));
					if(productVO.getConsumer() == 0 || productVO.getPrice() == 0) {
						open = "0";
					}
					productVO.setOpen(open);
					
					//b2b
					productVO.setPriceB2b(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRateB2b() / 100));

					service.updateGoods(productVO);
					
					if (productVO.getGoodsno() > 0) {
						if (productVO.getOptions().size() > 0) {
							for (ProductOptionVO optionVO : productVO.getOptions()) {
								//if (optionVO.getPrice() == 0) {
									optionVO.setPrice(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRate() / 100));
									//b2b
									optionVO.setPriceB2b(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRateB2b() / 100));
								//}
								
								optionVO.setGoodsno(productVO.getGoodsno());
								optionVO.setParent("1");
								int cnt = service.getGoodsOptionCount(optionVO);
								if (cnt > 0) {
									optionVO.setLink(0);
								} else {
									optionVO.setLink(1);
								}
								optionVO.setOptexplain("Color / Size");
								
								Integer sno = service.getGoodsOptionSno(optionVO);
								if (sno != null) {
									optionVO.setSno(sno);
									service.updateGoodsOption(optionVO);
								} else {
									service.insertGoodsOption(optionVO);
									service.updateGoodsOptionOptno(optionVO.getSno());
								}
							}
						}
						
						ProductOptionVO optionVO = new ProductOptionVO();
						optionVO.setGoodsno(productVO.getGoodsno());
						optionVO.setConsumer(productVO.getConsumer());
						optionVO.setPrice(productVO.getPrice());
						optionVO.setPriceRate(productVO.getPriceRate());
						optionVO.setPriceB2b(productVO.getPriceB2b());
						optionVO.setPriceRateB2b(productVO.getPriceRateB2b());
						optionVO.setSupplyPrice1(productVO.getSupplyPrice1());
						optionVO.setSupplyPrice2(productVO.getSupplyPrice2());
						optionVO.setMargin(productVO.getMargin());
						optionVO.setPriceMyRitz(productVO.getPriceMyRitz());
						optionVO.setStock(productVO.getStock());
						optionVO.setParent("0");
						optionVO.setLink(1);
						
						Integer sno = service.getGoodsOptionSno(optionVO);
						if (sno != null) {
							optionVO.setSno(sno);
							service.updateGoodsOption(optionVO);
						} else {
							service.insertGoodsOption(optionVO);
							service.updateGoodsOptionOptno(optionVO.getSno());
						}
						
						model.addAttribute("res_code", "200");
					    model.addAttribute("res_message", "SUCCESS");
					    model.addAttribute("res_desc", "성공");
					} else {
						model.addAttribute("res_code", "404");
					    model.addAttribute("res_message", "FAIL");
					    model.addAttribute("res_desc", "조회중 오류가 발생했습니다.");
					}
				}
			} else {
				model.addAttribute("res_code", "401");
			    model.addAttribute("res_message", "Unauthorized");
			    model.addAttribute("res_desc", "API 인증키가 유효하지 않습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("res_code", "404");
		    model.addAttribute("res_message", "FAIL");
		    model.addAttribute("res_desc", "등록중 오류가 발생했습니다.");
		}
		
		return "dojson";
	}

	/**
	 * 상품 변경연동(옵션재고)
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="product_stock.do", method=RequestMethod.POST)
	public String productStock(@RequestBody ProductVO productVO, HttpServletRequest req, Model model) {
		try {
			if (productVO.getKey() != null && productVO.getKey().equals(CommonConstants.API_KEY)) {
				logger.debug("productVO : {}", productVO);
				
				Integer goodsno = service.getGoodsNoByCd(productVO.getGoodsCd());
				if (goodsno != null) {
					if (productVO.getOptions().size() > 0) {
						for (ProductOptionVO optionVO : productVO.getOptions()) {
							optionVO.setGoodsno(goodsno);
							optionVO.setParent("1");
							service.updateGoodsStock(optionVO);

							//if (optionVO.getPrice() == 0) {
								optionVO.setPrice(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRate() / 100));
								optionVO.setPriceB2b(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRateB2b() / 100));
							//}
							service.updateGoodsPrice(optionVO);
						}
					}
					
					ProductOptionVO optionVO = new ProductOptionVO();
					optionVO.setGoodsno(goodsno);
					optionVO.setParent("0");
					optionVO.setStock(productVO.getStock());
					service.updateGoodsStock(optionVO);
					
					//if (productVO.getPrice() == 0) {
						productVO.setPrice(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRate() / 100));
						productVO.setPriceB2b(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRateB2b() / 100));
					//}

					optionVO.setConsumer(productVO.getConsumer());
					optionVO.setPrice(productVO.getPrice());
					optionVO.setPriceRate(productVO.getPriceRate());
					optionVO.setPriceB2b(productVO.getPriceB2b());
					optionVO.setPriceRateB2b(productVO.getPriceRateB2b());
					optionVO.setSupplyPrice1(productVO.getSupplyPrice1());
					optionVO.setSupplyPrice2(productVO.getSupplyPrice2());
					optionVO.setMargin(productVO.getMargin());
					optionVO.setPriceMyRitz(productVO.getPriceMyRitz());
					service.updateGoodsPrice(optionVO);
						
					model.addAttribute("res_code", "200");
				    model.addAttribute("res_message", "SUCCESS");
				    model.addAttribute("res_desc", "성공");
				} else {
					model.addAttribute("res_code", "404");
				    model.addAttribute("res_message", "FAIL");
				    model.addAttribute("res_desc", "조회중 오류가 발생했습니다.");
				}
			} else {
				model.addAttribute("res_code", "401");
			    model.addAttribute("res_message", "Unauthorized");
			    model.addAttribute("res_desc", "API 인증키가 유효하지 않습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("res_code", "404");
		    model.addAttribute("res_message", "FAIL");
		    model.addAttribute("res_desc", "등록중 오류가 발생했습니다.");
		}
		
		return "dojson";
	}

	/**
	 * 상품 변경연동(판매상태)
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="product_status.do", method=RequestMethod.POST)
	public String productStatus(@RequestBody ProductVO productVO, HttpServletRequest req, Model model) {
		try {
			if (productVO.getKey() != null && productVO.getKey().equals(CommonConstants.API_KEY)) {
				logger.debug("productVO : {}", productVO);
				
				Integer goodsno = service.getGoodsNoByCd(productVO.getGoodsCd());
				if (goodsno != null) {
					productVO.setGoodsno(goodsno);
					service.updateGoodsStatus(productVO);
						
					model.addAttribute("res_code", "200");
				    model.addAttribute("res_message", "SUCCESS");
				    model.addAttribute("res_desc", "성공");
				} else {
					model.addAttribute("res_code", "404");
				    model.addAttribute("res_message", "FAIL");
				    model.addAttribute("res_desc", "조회중 오류가 발생했습니다.");
				}
			} else {
				model.addAttribute("res_code", "401");
			    model.addAttribute("res_message", "Unauthorized");
			    model.addAttribute("res_desc", "API 인증키가 유효하지 않습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("res_code", "404");
		    model.addAttribute("res_message", "FAIL");
		    model.addAttribute("res_desc", "등록중 오류가 발생했습니다.");
		}
		
		return "dojson";
	}

	/**
	 * 상품 변경연동(가격)
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="product_price.do", method=RequestMethod.POST)
	public String productPrice(@RequestBody ProductVO productVO, HttpServletRequest req, Model model) {
		try {
			if (productVO.getKey() != null && productVO.getKey().equals(CommonConstants.API_KEY)) {
				logger.debug("productVO : {}", productVO);
				
				Integer goodsno = service.getGoodsNoByCd(productVO.getGoodsCd());
				if (goodsno != null) {
					productVO.setGoodsno(goodsno);
					service.updateGoodsLowest(productVO);
					
					if (productVO.getOptions().size() > 0) {
						for (ProductOptionVO optionVO : productVO.getOptions()) {
							//if (optionVO.getPrice() == 0) {
								optionVO.setPrice(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRate() / 100));
								optionVO.setPriceB2b(optionVO.getConsumer() - (optionVO.getConsumer() * optionVO.getPriceRateB2b() / 100));
							//}
							
							optionVO.setGoodsno(goodsno);
							optionVO.setParent("1");
							service.updateGoodsPrice(optionVO);
						}
					}
					
					//if (productVO.getPrice() == 0) {
					productVO.setPrice(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRate() / 100));
					productVO.setPriceB2b(productVO.getConsumer() - (productVO.getConsumer() * productVO.getPriceRateB2b() / 100));
					//}
					
					ProductOptionVO optionVO = new ProductOptionVO();
					optionVO.setGoodsno(goodsno);
					optionVO.setParent("0");
					optionVO.setConsumer(productVO.getConsumer());
					optionVO.setPrice(productVO.getPrice());
					optionVO.setPriceRate(productVO.getPriceRate());
					optionVO.setPriceB2b(productVO.getPriceB2b());
					optionVO.setPriceRateB2b(productVO.getPriceRateB2b());
					optionVO.setSupplyPrice1(productVO.getSupplyPrice1());
					optionVO.setSupplyPrice2(productVO.getSupplyPrice2());
					optionVO.setMargin(productVO.getMargin());
					optionVO.setPriceMyRitz(productVO.getPriceMyRitz());
					service.updateGoodsPrice(optionVO);
						
					model.addAttribute("res_code", "200");
				    model.addAttribute("res_message", "SUCCESS");
				    model.addAttribute("res_desc", "성공");
				} else {
					model.addAttribute("res_code", "404");
				    model.addAttribute("res_message", "FAIL");
				    model.addAttribute("res_desc", "조회중 오류가 발생했습니다.");
				}
			} else {
				model.addAttribute("res_code", "401");
			    model.addAttribute("res_message", "Unauthorized");
			    model.addAttribute("res_desc", "API 인증키가 유효하지 않습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("res_code", "404");
		    model.addAttribute("res_message", "FAIL");
		    model.addAttribute("res_desc", "등록중 오류가 발생했습니다.");
		}
		
		return "dojson";
	}


	/**
	 * API GET 오류처리
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"product_add.do", "product_change.do", "product_stock.do", "product_status.do", "product_price.do", "order_list.do", "order_status_change.do"}, method=RequestMethod.GET)
	public String getApi(HttpServletRequest req, Model model) throws Exception {
		
		model.addAttribute("res_code", "405");
	    model.addAttribute("res_message", "FAIL");
	    model.addAttribute("res_desc", "Request method 'GET' not supported");

		return "dojson";
	}

	/**
	 * 주문데이터 연동
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="order_list.do", method=RequestMethod.POST)
	public String order_list(@RequestBody OrdApiVO ordApiVO, HttpServletRequest req, Model model) {
		try {
			if (ordApiVO.getKey() != null && ordApiVO.getKey().equals(CommonConstants.API_KEY)) {
				logger.debug("ordApiVO : {}", ordApiVO);

				if ("10".equals(ordApiVO.getOrdTypCd())) {
					ordApiVO.setStep("1");
					ordApiVO.setIstep("0"); // 결제완료
				} else if ("15".equals(ordApiVO.getOrdTypCd())) {
					ordApiVO.setStep("1");
					ordApiVO.setIstep("10"); //결제완료 수집완료
				} else if ("90".equals(ordApiVO.getOrdTypCd())) {
					ordApiVO.setIstep("56"); // 취소완료
				} else if ("95".equals(ordApiVO.getOrdTypCd())) {
					ordApiVO.setIstep("58"); // 취소완료 수집완료
				} else {
					model.addAttribute("res_code", "404");
				    model.addAttribute("res_message", "FAIL");
				    model.addAttribute("res_desc", "조회중 오류가 발생했습니다.");
				    return "dojson";
				}
					
				List<OrdListVO> orderList = service.getOrderSearchList(ordApiVO);

				model.addAttribute("data", orderList);
				model.addAttribute("res_code", "200");
			    model.addAttribute("res_message", "SUCCESS");
			    model.addAttribute("res_desc", "성공");			
			} else {
				model.addAttribute("res_code", "401");
			    model.addAttribute("res_message", "Unauthorized");
			    model.addAttribute("res_desc", "API 인증키가 유효하지 않습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("res_code", "404");
		    model.addAttribute("res_message", "FAIL");
		    model.addAttribute("res_desc", "조회중 오류가 발생했습니다.");
		}
		
		return "dojson";
	}
	
	/**
	 * 주문 상태 연동
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="order_status_change.do", method=RequestMethod.POST)
	public String order_status_change(@RequestBody OrdApiVO ordApiVO, HttpServletRequest req, Model model) {
		try {
			if (ordApiVO.getKey() != null && ordApiVO.getKey().equals(CommonConstants.API_KEY)) {
				logger.debug("ordApiVO : {}", ordApiVO);
				
				OrdListVO ordInfo = service.getOrderSearchInfo(ordApiVO);
				if (ordInfo != null) {
					if ("15".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep("1");
						ordApiVO.setIstep("10"); //결제완료 수집완료
					} else if ("20".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep("10");
						ordApiVO.setIstep("0"); // 재고확인중
					} else if ("25".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep("11");
						ordApiVO.setIstep("0"); // 재고확인완료
					} else if ("30".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep("2");
						ordApiVO.setIstep("0"); //배송준비중
					} else if ("40".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep("12");
						ordApiVO.setIstep("0"); // 입고완료
					} else if ("50".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep("3");
						ordApiVO.setIstep("0"); // 배송중
						
						/* 비즈톡 발송 */
						BiztalkUtil biztalk = new BiztalkUtil();
						biztalk.sendAlimTalk(ordInfo.getOrdno(), "ritzmall_02");

					} else if ("60".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep("4");
						ordApiVO.setIstep("0"); // 배송완료
					} else if ("95".equals(ordApiVO.getOrdTypCd())) {
						ordApiVO.setStep(ordInfo.getStep());
						ordApiVO.setIstep("58"); // 취소완료 수집완료
					} else {
						model.addAttribute("res_code", "404");
					    model.addAttribute("res_message", "FAIL");
					    model.addAttribute("res_desc", "등록중 오류가 발생했습니다.");
					    return "dojson";
					}
					
					service.updateOrderStatus(ordApiVO);
					service.updateOrderItemStatus(ordApiVO);
						
					model.addAttribute("res_code", "200");
				    model.addAttribute("res_message", "SUCCESS");
				    model.addAttribute("res_desc", "성공");
				} else {
					model.addAttribute("res_code", "404");
				    model.addAttribute("res_message", "FAIL");
				    model.addAttribute("res_desc", "조회중 오류가 발생했습니다.");
				}
			} else {
				model.addAttribute("res_code", "401");
			    model.addAttribute("res_message", "Unauthorized");
			    model.addAttribute("res_desc", "API 인증키가 유효하지 않습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("res_code", "404");
		    model.addAttribute("res_message", "FAIL");
		    model.addAttribute("res_desc", "등록중 오류가 발생했습니다.");
		}
		
		return "dojson";
	}
}
