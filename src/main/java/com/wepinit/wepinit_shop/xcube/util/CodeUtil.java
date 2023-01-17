package com.wepinit.wepinit_shop.xcube.util;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.util.ApplicationContextUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import com.wepinit.wepinit_shop.xmall.common.vo.join.CodeSmssample;

import java.util.List;

public class CodeUtil {
	
	//코드 반환
	public static List<GdCode> codeitem(String groupcd) throws Exception {
		List<GdCode> resultList = null;
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService) ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		resultList = shopLibFncService.getCodeItem(groupcd);

		return resultList;
	}
	
	//코드 반환(대메뉴만 조회)
	public static List<GdCode> codeitem2() throws Exception {
		List<GdCode> resultList = null;
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		resultList = shopLibFncService.getCodeItem2();

		return resultList;
	}
	
	//SMS그룹샘플 조회(건수 포함)
	public static List<CodeSmssample> codeitem3() throws Exception {
		List<CodeSmssample> resultList = null;
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		resultList = shopLibFncService.getCodeItem3();

		return resultList;
	}
	
	/**
	 * [설명] 코드명을 String으로 반환
	 * @param grpNm
	 * @param itemCd
	 * @return
	 * @throws Exception
	 */
	public static String getCodeName(String grpNm, String itemCd) throws Exception {
		String resultstr = "";
		try {
			ShopLibFncService shopLibFncService = null;
			shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
			
			resultstr = shopLibFncService.getCodeName(grpNm, itemCd);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return resultstr;
	}
	
	/**
	 * [설명] 코드명을 String으로 반환
	 * @param dim1Var
	 * @return
	 * @throws Exception
	 */
	public static String getConfValue(String dim1Var) throws Exception {
		
		return ShopConfig.getProperty(dim1Var);
	}
	
	//회원그룹정보조회
	public static List<GdMemberGrp> getMemberGrp(){
		
		List<GdMemberGrp> resultList = null;
		ShopLibFncService shopLibFncService = null;
		
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		try{
		
			resultList = shopLibFncService.getMemberGrp();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * [설명] ConfigClass value 가져오기
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String getConfigClassValue(String value) throws Exception {
		return ConfigClass.value(value);
	}
}
