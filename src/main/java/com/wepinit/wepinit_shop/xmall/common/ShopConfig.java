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
package com.wepinit.wepinit_shop.xmall.common;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.common.service.ShopConfigService;
import com.wepinit.wepinit_shop.xmall.common.util.ApplicationContextUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopConfig{
	
	private static Logger logger = LoggerFactory.getLogger(ShopConfig.class);
	
	private static Map conf= new HashMap();	
	 
	private static ShopConfig shopConfig;
	
	private ShopConfig(){}
	
	public static ShopConfig getInstance() throws Exception{
		
		if(shopConfig ==null){
			shopConfig = new ShopConfig();
//			shopConfig.load("", "", "", "");
		}
		
		return shopConfig;
	}

	private boolean containsKeyConfCd(String conf_cd){
		return conf.containsKey(conf_cd);
	}
	
	
	
	private Map getConfCd(String conf_cd){		
		return (Map) conf.get(conf_cd);		
	}
		
	
	private boolean containsKeyDimSet(String conf_cd, String dim_set){
		
		if(containsKeyConfCd(conf_cd)){
			return getConfCd(conf_cd).containsKey(dim_set);			
		}else{
			return false;
		}
		
	}
	
	private Map getDimSet(String conf_cd, String dim_set){
		Map map = null;
		if(containsKeyConfCd(conf_cd)){
			map = (Map) getConfCd(conf_cd).get(dim_set);
		}
		return map;
	}

/*
 *  Map 정보가 get
 */
	
	public static Map getConfig(){
		Map map = null;
//		if(containsKeyConfCd("config")){
//			map = (Map) getConfCd("config").get("cfg");
//		}
		map = (Map) conf.get("config");
		return (Map) map.get("cfg");
	}	
	
	private boolean containsKeyDim1Var(String conf_cd, String dim_set, String dim1_var){
		
		if(containsKeyDimSet(conf_cd, dim_set)){
			return getDimSet(conf_cd, dim_set).containsKey(dim1_var);			
		}else{
			return false;
		}
		
	}
	
	private Map getDim1Var(String conf_cd, String dim_set,  String dim1_var){
		Map map = null;
		if(containsKeyDimSet(conf_cd, dim_set)){
			map = (Map) getDimSet(conf_cd, dim_set).get(dim1_var);
		}
		return map;
	}
	
	private boolean containsKeyDim2Var(String conf_cd, String dim_set, String dim1_var, String dim2_var){
		
		if(dim2_var == null || dim2_var.equals("N/A")){			
			
			return containsKeyDim1Var(conf_cd, dim_set, dim1_var);		
			
		}else if(containsKeyDim1Var(conf_cd, dim_set, dim1_var)){	
			
			return getDim1Var(conf_cd, dim_set, dim1_var).containsKey(dim2_var);
			
		}else{			
			return false;			
		}
		
	}
	
	private String getDimVal(String conf_cd, String dim_set,  String dim1_var){
		return getDimVal(conf_cd, dim_set, dim1_var, null);
	}
	
	private String getDimVal(String conf_cd, String dim_set,  String dim1_var, String dim2_var){
		
		String str = "";
		if(dim2_var == null || dim2_var.equals("N/A")){
			if(containsKeyDimSet(conf_cd, dim_set)){
				str = (String) getDimSet(conf_cd, dim_set).get(dim1_var);
			}
		}else if(containsKeyDim1Var(conf_cd, dim_set, dim1_var)){
			str = (String) getDim1Var(conf_cd, dim_set, dim1_var).get(dim2_var);
		}
		return str;
	}
	
	private String setDimVal(String conf_cd, String dim_set, String dim1_var, String dim2_var, String dim_val){
		
		if(!containsKeyConfCd(conf_cd)){
			conf.put(conf_cd, new HashMap());			
		}
		
		if(!containsKeyDimSet(conf_cd, dim_set)){
			getConfCd(conf_cd).put(dim_set, new HashMap());			
			
		}
		
		if(dim2_var == null || dim2_var.equals("N/A")){
			getDimSet(conf_cd, dim_set).put(dim1_var, dim_val);
		}else{
			if(!containsKeyDim1Var(conf_cd, dim_set, dim1_var)){
				getDimSet(conf_cd, dim_set).put(dim1_var, new HashMap());
			}
			
			getDim1Var(conf_cd, dim_set, dim1_var).put(dim2_var, dim_val);
		}
		
		//System.out.println("##########################: 메모리설후 읽어오기: "+getDimVal(conf_cd, dim_set, dim1_var,dim2_var));
		
		return getDimVal(conf_cd, dim_set, dim1_var, dim2_var);
	}
	
	public static String getProperty(String key){
		try {
			String val = StringUtil.nvl(ShopConfig.getInstance().shopConfig.getDimVal("config", "cfg", key), "");
			if ( "".equals(val) ) {
				ShopConfig.getInstance();
				val = ShopConfig.shopConfig.getProperty("config", "cfg", key, "N/A");
			}
			return val;
		} catch (Exception e) {			
			e.printStackTrace();
			return "";
		}
		
	}
	
	public static String getProperty(String key, String defaultValue){
		
		String value = null;		
			
		value = getProperty(key);
		
		if(null == value || "".equals(value)){
			value = defaultValue;
		}
		
		return value;
	}
	
	public static String getProperty(String conf_cd, String dim_set, String key){
		String l_conf_cd = "config";
		String l_dim_set = "cfg";
		
		if( !"".equals(StringUtil.nvl(dim_set, "")) ) l_dim_set = dim_set;
		if( !"".equals(StringUtil.nvl(conf_cd, "")) ) 	l_conf_cd = conf_cd;
		
		try {
			String val = StringUtil.nvl(ShopConfig.getInstance().shopConfig.getDimVal(l_conf_cd, l_dim_set, key), "");
			if ( "".equals(val) ) {
				ShopConfig.getInstance();
				val = ShopConfig.shopConfig.getProperty(l_conf_cd, l_dim_set, key, "N/A");
			}
			return val;
		} catch (Exception e) {			
			e.printStackTrace();
			return "";
		}
		
	}
	
	
	public String getProperty(String conf_cd, String dim_set, String dim1_var, String dim2_var) throws Exception{
		ShopConfigService shopConfigService = null;
		GdConfSet gdConfSet = null;
		
		shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");
		gdConfSet = shopConfigService.getShopConfigProperty(conf_cd, dim_set, dim1_var);
		
		
		setDimVal(conf_cd, dim_set, dim1_var, dim2_var, gdConfSet == null ? "" : gdConfSet.getDimVal());
		
		return gdConfSet == null ? "" : gdConfSet.getDimVal();
	}	
	
	public static String setProperty(String dim1_var,String dim_val) throws Exception{
		ShopConfigService shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");
		
		ShopConfig.getInstance();
		return ShopConfig.shopConfig.setProperty("config", "cfg", dim1_var, "N/A", dim_val, shopConfigService);
	}

	public static String setProperty(String dim1_var, String dim_val, String dim_set) throws Exception{
		ShopConfigService shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");
		
		ShopConfig.getInstance();
		return ShopConfig.shopConfig.setProperty("config", dim_set, dim1_var, "N/A", dim_val, shopConfigService);
	}
	
	public String setProperty(String dim1_var,String dim_val, ShopConfigService shopConfigService) throws Exception{
		return setProperty("config", "cfg", dim1_var, "N/A", dim_val, shopConfigService);
	}
	
	public String setProperty(String conf_cd, String dim_set, String dim1_var, String dim_val, ShopConfigService shopConfigService) throws Exception{	
		ShopConfig.getInstance();
		return ShopConfig.shopConfig.setProperty(conf_cd, dim_set, dim1_var, "N/A", dim_val, shopConfigService);
		
	}
	
	public String setProperty(String conf_cd, String dim_set, String dim1_var, String dim2_var, String dim_val) throws Exception{
		ShopConfigService shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");
		
		return setProperty(conf_cd, dim_set, dim1_var, dim2_var, dim_val, shopConfigService);
	}
	
	public String setProperty(String conf_cd, String dim_set, String dim1_var, String dim2_var, String dim_val, ShopConfigService shopConfigService) throws Exception{		
			
		Map<String, String> param = new HashMap();
			
		param.put("conf_cd", conf_cd);
		param.put("dim_set", dim_set);
		param.put("dim1_var", dim1_var);
		param.put("dim2_var", StringUtil.nvl(dim2_var, "N/A"));
		param.put("dim_val", dim_val);
		
		shopConfigService.setShopConfigProperty(param);
		
		setDimVal(conf_cd, dim_set, dim1_var, dim2_var, dim_val);

		return getDimVal(conf_cd, dim_set, dim1_var, dim2_var);		
	}
	
	public static void setProperty2(String conf_cd, String dim_set, String dim1_var, String dim_val) throws Exception{
		ShopConfig.getInstance();
		ShopConfig.shopConfig.setProperty3(conf_cd, dim_set, dim1_var, dim_val);
	}
	
	public void setProperty3(String conf_cd, String dim_set, String dim1_var, String dim_val) throws Exception{
		GdConfSet gdConfSet = new GdConfSet();
		Map<String, String> param = new HashMap();
		ShopConfigService shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");
		
		param.put("conf_cd", conf_cd);
		param.put("dim_set", dim_set);
		param.put("dim1_var", dim1_var);
		param.put("dim_val", dim_val);
		
		shopConfigService.setShopConfigProperty3(param);
		
		setDimVal(conf_cd, dim_set, dim1_var, "N/A", dim_val);
	}
	
	private boolean load(String conf_cd, String dim_set, String dim1_var, String dim2_var) throws Exception{		
		int i =0;
		List<GdConfSet> shopConfigList = null;
		GdConfSet gdConfSet = null;
		Map<String, String> param = null;
		ShopConfigService shopConfigService = null;
		
		param = new HashMap<String, String>();
		shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");	
					
		shopConfigList = shopConfigService.load(param);
		
		if( null != shopConfigList ){
			for( i=0 ; i < shopConfigList.size() ; i++ ){
				gdConfSet = shopConfigList.get(i);
				setDimVal( gdConfSet.getConfCd()
								, gdConfSet.getDimSet()
								, gdConfSet.getDim1Var()
								, gdConfSet.getDim2Var()
								, gdConfSet.getDimVal() );
			}
		}
		
		return true;
	}
	
	private void loadTest(String conf_cd, String dim_set, String dim1_var, String dim2_var) throws Exception{
		int i =0;
		int success = 0;
		int fail = 0;
		boolean isContainsKeyDim2Var = false;
		String dim_val = "";
		Map<String, String> param = null;
		List<GdConfSet> shopConfigList = null;
		GdConfSet gdConfSet = null;
		ShopConfigService shopConfigService = null;
		
		param = new HashMap<String, String>();
		shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");	
					
		shopConfigList = shopConfigService.load(param);
		
		if( null != shopConfigList ){
			
			for( i=0; i < shopConfigList.size(); i++ ){
				gdConfSet = shopConfigList.get(i);
				isContainsKeyDim2Var = containsKeyDim2Var(gdConfSet.getConfCd(), gdConfSet.getDimSet(), gdConfSet.getDim1Var(), gdConfSet.getDim2Var());
				
				dim_val = getDimVal(  gdConfSet.getConfCd(), gdConfSet.getDimSet(), gdConfSet.getDim1Var(), gdConfSet.getDim2Var());				
				System.out.println("DB: "+ gdConfSet.getConfCd() +" : "+ gdConfSet.getDimSet() +" : "+ gdConfSet.getDim1Var() +" : "+ gdConfSet.getDim2Var() +":"+ gdConfSet.getDimVal());
				System.out.println("MP: "+ containsKeyConfCd(gdConfSet.getConfCd())+" : "+ containsKeyDimSet(gdConfSet.getConfCd(), gdConfSet.getDimSet())+" : "
						+ containsKeyDim1Var(gdConfSet.getConfCd(), gdConfSet.getDimSet(), gdConfSet.getDim1Var())+" : "
						+ isContainsKeyDim2Var +":"+dim_val);
				
				if( !gdConfSet.getDimVal().equals(dim_val) || !isContainsKeyDim2Var ){
					fail ++;
					System.out.println("CK: 실패");
				}else{
					success ++;
					System.out.println("CK: 성공");
				}
			}		
		}
	}
	
	public static List<Map<String, Object>> getProperties(String conf_cd, String dim_set) throws Exception{
		try{
			ShopConfig.getInstance();
			return ShopConfig.shopConfig.getProperties2(conf_cd, dim_set);
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Map<String, Object>> getProperties2(String conf_cd, String dim_set) throws Exception{		
		Map<String, String> param = null;
		List<Map<String, Object>> shopConfigList = null;
		ShopConfigService shopConfigService = null;
		
		param = new HashMap<String, String>();
		shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");	
		
		param.put("conf_cd", conf_cd);
		param.put("dim_set", dim_set);
		
		shopConfigList = shopConfigService.getProperties2(param);
		
		return shopConfigList;
	}	
	
	public static List<Map<String, Object>> getProperties3(String conf_cd, String dim_set) throws Exception{
		try{
			ShopConfig.getInstance();
			return ShopConfig.shopConfig.getProperties4(conf_cd, dim_set);
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Map<String, Object>> getProperties4(String conf_cd, String dim_set) throws Exception{		
		Map<String, String> param = null;
		List<Map<String, Object>> shopConfigList = null;
		ShopConfigService shopConfigService = null;
		
		param = new HashMap<String, String>();
		shopConfigService = (ShopConfigService)ApplicationContextUtil.getApplicationContext().getBean("shopConfigService");	
		
		param.put("conf_cd", conf_cd);
		param.put("dim_set", dim_set);
		param.put("isOrderBy", "Y");
		
		shopConfigList = shopConfigService.getProperties2(param);
		
		return shopConfigList;
	}
	
//	public static void setProperties3(String conf_cd, String dim_set, String dim_val, String queryString) throws Exception{
//		try{
//			ShopConfig.getInstance().shopConfig.setProperties4(conf_cd, dim_set, dim_val, queryString);
//		} catch (Exception e) {			
//			e.printStackTrace();
//		}
//	}
	
//	public void setProperties4(String conf_cd, String dim_set, String dim_val, String queryString) throws Exception{		
//		
//		DBHandler dbHandler = null;
//		
//		try{
//			dbHandler = new DBHandler(ConfigClass.DATASOURCE);		
//
//			if(ConfigClass.DBPOOL_FLAG){
//				dbHandler.connect();		
//			}else{
//				dbHandler.directconnect();
//			}
//			String query1 = "UPDATE gd_conf_set SET dim_val = '' WHERE conf_cd = :conf_cd and dim_set = :dim_set "; 
//		
//			String query2 = "UPDATE gd_conf_set SET dim_val = :dim_val WHERE conf_cd = :conf_cd and dim_set = :dim_set $( and )queryString";
//			
//			RequestSet param = new RequestSet();		
//			param.setProperty("conf_cd", conf_cd);		
//			param.setProperty("dim_set", dim_set);		
//			param.setProperty("dim_val", dim_val);		
//			param.setProperty("queryString", queryString);		
//			
//			dbHandler.executeUpdate(query1, param);
//			
//			dbHandler.executeUpdate(query2, param);
//			
//			System.out.println("---------------------->>>>" + queryString);
////			setDimVal(conf_cd, dim_set, dim1_var, "N/A", dim_val);
////			dim1_var in ( 'phone','email','marriyn','nickname','birth','mobile','job','marridate','address' )
//		} catch (Exception e){
//			if( null != dbHandler ){
//				dbHandler.rollback();
//			}
//		} finally{
//			if ( null != dbHandler ) dbHandler.disconnect();
//		}
//	}
	
	public static void main(String[] args) throws Exception{
		
		ShopConfig shopConfig = ShopConfig.getInstance();
		//load(null, null, null, null);
		//loadTest(null, null, null, null);
		/*
		System.out.println(setDimVal("conf_cd", "dim_set", "dim1_var", "dim2_var", "dim_val"));
		System.out.println(getDimVal("conf_cd", "dim_set", "dim1_var", "dim2_var"));
		System.out.println(setDimVal("conf_cd", "dim_set", "dim1_var", "dim2_var", "dim_val2"));
		System.out.println(getDimVal("conf_cd", "dim_set", "dim1_var", "dim2_var"));
		*/
		/*
		System.out.println(getProperty("config", "cfg", "service", "N/A"));
		System.out.println(getProperty("config", "cfg", "service", "N/A"));
		System.out.println(getProperty("config", "cfg", "service", "N/A"));
		*/
		
		/*
		System.out.println(getProperty("m1", "m2", "m3", "a2"));
		System.out.println(getProperty("m1", "m2", "m3", "a2"));
		System.out.println(getProperty("m1", "m2", "m3", "a2"));
		*/
		
		//System.out.println(getProperty("m1", "m2", "m3", "a1"));
		
		
		//System.out.println("원본############ "+getProperty("m1", "m2", "m3", "a2"));
		//System.out.println("원본############ "+shopConfig.getProperty("m1", "m2", "m4", "N/A"));
		
		
//		//System.out.println("설정############ "+setProperty("m1", "m2", "m3", "a2","테스트1"));
//		System.out.println("설정############ "+shopConfig.setProperty("m1", "m2", "m4", "N/A","테스트2"));
//		
//		//System.out.println("설정후############ "+getProperty("m1", "m2", "m3", "a2"));
//		System.out.println("설정후############ "+shopConfig.getProperty("m1", "m2", "m4", "N/A"));
//		
//		//System.out.println("재설정############ "+setProperty("m1", "m2", "m3", "a2", "v2"));
//		System.out.println("재설정############ "+shopConfig.setProperty("m1", "m2", "m4", "N/A","안녕하세요"));
//		
//		//System.out.println("재설정후############ "+getProperty("m1", "m2", "m3", "a2"));
//		System.out.println("재설정후############ "+shopConfig.getProperty("m1", "m2", "m4", "N/A"));
		
		
	}
	
}