package com.wepinit.wepinit_shop.xmall.common;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

public class MapDeserializer implements JsonDeserializer<Map<String, Object>> {

	@SuppressWarnings("unchecked")
	public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return (Map<String, Object>) read(json);
	}

	public Object read(JsonElement in) {
		if (in.isJsonArray()) { //JsonArray인 경우
			List<Object> list = new ArrayList<Object>();
			JsonArray arr = in.getAsJsonArray();
			for (JsonElement anArr : arr) {
				list.add(read(anArr));
			}
			return list;
		} else if (in.isJsonObject()) {
			Map<String, Object> map = new HashMap<String, Object>();
			JsonObject obj = in.getAsJsonObject();
			Set<Entry<String, JsonElement>> entitySet = obj.entrySet();
			for (Entry<String, JsonElement> entry: entitySet) {
				map.put(entry.getKey(), read(entry.getValue()));
			}
			return map;
		} else if (in.isJsonPrimitive()) {
			JsonPrimitive prim = in.getAsJsonPrimitive();
			if (prim.isBoolean()) { //Boolean
				return prim.getAsBoolean();
			} else if (prim.isString()) { //String
				return prim.getAsString();
			} else if (prim.isNumber()) {
				Number num = prim.getAsNumber();
				if (Math.ceil(num.doubleValue()) == num.longValue()) { //Int
					return num.longValue();
				} else { //Double
					return num.doubleValue();
				}
			}
		}
		return null;
	}
}