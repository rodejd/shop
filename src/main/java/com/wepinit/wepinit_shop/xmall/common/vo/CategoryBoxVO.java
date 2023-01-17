package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.List;
import java.util.Map;




public class CategoryBoxVO {
//	?mode=user&idx=" + idx + "&obj=" + obj.name + "&formnm=" + obj.form.name + "&val=" + val + "&category=" + obj.value
	
	private String mode;
	private int idx;
	private String obj;
	private String formnm;
	private String val;
	private String category;
	
	private List<Map<String, String>> retCategoryList;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public String getFormnm() {
		return formnm;
	}

	public void setFormnm(String formnm) {
		this.formnm = formnm;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Map<String, String>> getRetCategoryList() {
		return retCategoryList;
	}

	public void setRetCategoryList(List<Map<String, String>> retCategoryList) {
		this.retCategoryList = retCategoryList;
	}
}
