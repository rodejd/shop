package com.wepinit.wepinit_shop.xcube.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ConvertUtil {
	
	/**
	 * JSONObject를 HashMap으로 반환한다.
	 * @param jsonObj
	 * @return map
	 */
	public static HashMap jsonObjToHashMap(JSONObject jsonObj) throws Exception  {
		return jsonObjToHashMap(jsonObj, new HashMap());
	}
	public static HashMap jsonObjToHashMap(JSONObject jsonObj, HashMap map) throws Exception  {
		if (null == jsonObj) return null;
		
		map = (null == map) ? new HashMap() : map;
		
		String key = "";
	
		Iterator itr = jsonObj.keySet().iterator();
		while ( itr.hasNext() ) {
			key = String.valueOf(itr.next());
			if ( jsonObj.get(key) instanceof JSONArray ) {
				// JSONArray 일 경우 셋팅
				map.put(key, ConvertUtil.jsonArrayToList((JSONArray)jsonObj.get(key)));
			}else{
				// 단건의 경우일 경우 셋팅
				map.put(key, String.valueOf(jsonObj.get(key)));
			}
		}
		
		return map;
	}
	
	/**
	 * HashMap 데이터를 JSONObject로 반환한다.
	 * @param map
	 * @return jsonObj
	 */
	public static JSONObject hashMapToJsonObj(HashMap map) throws Exception  {
		return hashMapToJsonObj(map, new JSONObject());
	}
	public static JSONObject hashMapToJsonObj(HashMap map, JSONObject jsonObj) throws Exception  {
		if ( null == map ) return null;
		
		jsonObj = (null == jsonObj) ? new JSONObject() : jsonObj;
		
		String key = "";

		Iterator itr = map.keySet().iterator();
		while ( itr.hasNext() ) {
			key = String.valueOf(itr.next());
			if ( map.get(key) instanceof List ) {
				// List 데이터 셋팅
				jsonObj.put(key, ConvertUtil.listToJsonArray((List)map.get(key)));
			}else{
				// 단건 데이터 셋팅
				jsonObj.put(key, String.valueOf(map.get(key)));
			}
		}
		return jsonObj;
	}
	
	/**
	 * JSONArray<JSONObject>를 List<HashMap>로 반환한다.
	 * @param jsonArr
	 * @return list
	 */
	public static List jsonArrayToList(JSONArray jsonArr) throws Exception  {
		return jsonArrayToList(jsonArr, new ArrayList());
	}
	public static List jsonArrayToList(JSONArray jsonArr, List list) {
		if (null == jsonArr) return null;
		
		list = (null == list) ? new ArrayList() : list;
		
		HashMap map = null;
		JSONObject jsonObj = null;
		
		String key = "";
		String val = "";
		
		Iterator jItr = null; 
		Iterator itr = jsonArr.iterator();
		while( itr.hasNext() ){
			// JSONArray row setting
			jsonObj = (JSONObject)itr.next();
			jItr = jsonObj.keySet().iterator();
			
			// JSONObject data setting
			map = new HashMap();
			while ( jItr.hasNext() ){
				key = (String)jItr.next();
				val = String.valueOf(jsonObj.get(key));
				
				map.put(key, val);	// HashMap setting
			}
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * List<HashMap>를 JSONArray<JSONObject>로 반환한다.
	 * @param list
	 * @return jsonArr
	 */
	public static JSONArray listToJsonArray(List list) throws Exception {
		return listToJsonArray(list, new JSONArray());
	}
	public static JSONArray listToJsonArray(List list, JSONArray jsonArr) throws Exception {
		if (null == list) return null;
		
		jsonArr = (null == jsonArr) ? new JSONArray() : jsonArr;
		
		JSONObject jsonObj = null;
		
		HashMap map = null;
		Iterator itr = null;
		
		String key = "";
		String val = "";
		int i = 0;
		
		if ( null != list && 0 < list.size() ) {
			while ( i < list.size() ) {
				// row data
				map = (HashMap) list.get(i);
				if ( null != map ) {
					jsonObj = new JSONObject();
					
					// key, value를 JSONObject에 셋팅
					itr = map.keySet().iterator();
					while( itr.hasNext() ) {
						key = (String) itr.next();
						val = String.valueOf(map.get(key));
						
						jsonObj.put(key, val);
					}
					jsonArr.add(jsonObj);
				}
				
				i++;
			}
		}
		
		return jsonArr;
	}
	
	/**
	 * VO의 데이터를 JSONObject에 저장하여 반환한다.
	 * @param obj
	 * @return jsonObj
	 * @throws Exception
	 */
	public static JSONObject voToJsonObj(Object obj) throws Exception {
		return voToJsonObj(obj, new JSONObject());
	}
	public static JSONObject voToJsonObj(Object obj, JSONObject jsonObj) throws Exception {
		if ( null == obj ) return jsonObj;
		
		String keyNm = "";
		String returnType = "";
		
		jsonObj = (null == jsonObj) ? new JSONObject() : jsonObj;	// jsonObj 초기화
		
		BeanInfo info = Introspector.getBeanInfo(obj.getClass());
		
		for ( PropertyDescriptor pd : info.getPropertyDescriptors() ) {
			Method reader = pd.getReadMethod();
			if ( null != reader ) {
				keyNm = pd.getName();	// 저장할 데이터의 key값
				if ( null != reader.invoke(obj) ) {
					returnType = reader.getReturnType().toString();
					// list일 경우 JSONArray로 변환하여 셋팅한다.
					if ( "Interface java.util.list".equals(returnType) ) {
						jsonObj.put(keyNm, ConvertUtil.listVoToJsonArry((List)reader.invoke(obj)));
					}else{
						jsonObj.put(keyNm, reader.invoke(obj));
					}
				}else{
					// key의 데이터가 없을 경우 빈값으로 셋팅해준다.  
					jsonObj.put(keyNm, "");
				}
			}
		}
		
		return jsonObj;
	}
	
	/**
	 * List<VO>의 데이터를 JSONArray<JSONObject>에 저장하여 반환한다.
	 * @param list
	 * @return JSONArray
	 * @throws Exception
	 */
	public static JSONArray listVoToJsonArry(List list) throws Exception{
		return listVoToJsonArry(list, new JSONArray());
	}
	public static JSONArray listVoToJsonArry(List list, JSONArray jsonArr) throws Exception {
		if ( null == list || 1 > list.size() ) return null;
		
		jsonArr = (null == jsonArr) ? new JSONArray() : jsonArr;
		
		JSONObject jsonObj = null;
		Object obj = null;
		BeanInfo info = null;
		
		String keyNm = "";
		String returnType = "";
		
		for (int i=0 ; i < list.size() ; i++ ) {
			obj = (Object)list.get(i);	// list row object
			info = Introspector.getBeanInfo(obj.getClass());
			jsonObj = new JSONObject();
			
			for ( PropertyDescriptor pd : info.getPropertyDescriptors() ) {
				Method reader = pd.getReadMethod();
				if ( null != reader ) {
					keyNm = pd.getName();	// 저장할 데이터의 key값
					if ( null != reader.invoke(obj) ) {
						returnType = reader.getReturnType().toString();
						// class나 list type은 제외한다.
						if ( !"class java.lang.Class".equals(returnType) 
								&& !"Interface java.util.list".equals(returnType) ) {
							jsonObj.put(keyNm, reader.invoke(obj));
						}
					}else{
						// key의 데이터가 없을 경우 빈값으로 셋팅해준다.  
						jsonObj.put(keyNm, "");
					}
				}
			}
			
			jsonArr.add(jsonObj);	// JsonArray에 추가한다.
		}
		
		return jsonArr;
	}
	
	/**
	 * HashMap 데이터를 VO로 반환한다.
	 * @return
	 * @throws Exception
	 */
	public static Object hashMapToVo(HashMap map, Object obj) throws Exception {
		if (null == obj) return null;
		
		String keyNm = "";
		
		Object newObj = obj.getClass().newInstance();
		Method[] methods = obj.getClass().getMethods();
		
		for ( Method m : methods ) {
			keyNm = m.getName();
			// VO에서 실제 setting되어 사용하는 변수만 셋팅하기 위함.
			if ( "set".equals(keyNm.substring(0, 3)) ) {
				keyNm = keyNm.substring(3);
				if ( !"".equals(StringUtil.nvl(map.get(keyNm))) ) {
					// VO Object에 데이터를 셋팅
					m.invoke(newObj, new Object[]{ String.valueOf(map.get(keyNm))});
				}
			}
		}
		
		return newObj;
	}
	
	/**
	 * VO 데이터를 HashMap으로 반환한다.
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Object> voToHashMap(Object obj) throws Exception {
		return voToHashMap(obj, new HashMap<String, Object>());
	}
	
	public static HashMap<String, Object> voToHashMap(Object obj, HashMap<String, Object> map) throws Exception {
		int i = 0;
		Field[] fields = null;
		
		if (null == obj || null == map) return null;
		
		fields = obj.getClass().getDeclaredFields();

		for (i = 0; i < fields.length ; i++) {
			fields[i].setAccessible(true);
			map.put(fields[i].getName(), fields[i].get(obj));
		}
		
		return map;
	}
	
	/**
	 * List<HashMap>를 List<VO>로 반환한다. 
	 * @param list
	 * @param obj
	 * @return returnList
	 * @throws Exception
	 */
	public static List listHashMapToListVo(List list, Object obj) throws Exception{
		if (null == obj) return null;
		int i = 0;
		String keyNm = "";
		HashMap map = null;
		List returnList = null;
		Object newObj = null;
		Method[] methods = null;
		
		if ( null != list ) {
			returnList = new ArrayList();
			i = 0;
			while ( i < list.size() ) {
				map = (HashMap)list.get(i);
				newObj = obj.getClass().newInstance();
				methods = obj.getClass().getMethods();
				for ( Method m : methods ) {
					keyNm = m.getName();
					// VO에서 실제 setting되어 사용하는 변수만 셋팅하기 위함.
					if ( "set".equals(keyNm.substring(0, 3)) ) {
						keyNm = keyNm.substring(3);
						if ( !"".equals(StringUtil.nvl(map.get(keyNm))) ) {
							// VO Object에 데이터를 셋팅
							m.invoke(newObj, new Object[]{ String.valueOf(map.get(keyNm))});
						}
					}
				}
				returnList.add(newObj);
				i++;
			}
		}
	
		return returnList;
	}
	
	/**
	 * JsonString을 Map<String,Object>으로 반환한다. 
	 * @param json
	 * @return Map
	 * @throws Exception
	*/
	public static Map<String,Object> convertJSONstringToMap(String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
			System.out.println(e);
		}
        return map;
    }
	
}
