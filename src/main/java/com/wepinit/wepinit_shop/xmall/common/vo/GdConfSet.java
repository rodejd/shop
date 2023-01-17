package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdConfSet {
	
	private String confCd;
	private String dimSet;
	private String dim1Var;
	private String dim2Var;
	private String dimVal;
	private Date regdt;
	private Date uptdt;
	
	public String getConfCd() {
		return confCd;
	}
	public void setConfCd(String confCd) {
		this.confCd = confCd;
	}
	public String getDimSet() {
		return dimSet;
	}
	public void setDimSet(String dimSet) {
		this.dimSet = dimSet;
	}
	public String getDim1Var() {
		return dim1Var;
	}
	public void setDim1Var(String dim1Var) {
		this.dim1Var = dim1Var;
	}
	public String getDim2Var() {
		return dim2Var;
	}
	public void setDim2Var(String dim2Var) {
		this.dim2Var = dim2Var;
	}
	public String getDimVal() {
		return dimVal;
	}
	public void setDimVal(String dimVal) {
		this.dimVal = dimVal;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public Date getUptdt() {
		return uptdt;
	}
	public void setUptdt(Date uptdt) {
		this.uptdt = uptdt;
	}
	
	@Override
	public String toString() {
		return "GdConfSet [confCd=" + confCd + ", dimSet=" + dimSet
				+ ", dim1Var=" + dim1Var + ", dim2Var=" + dim2Var + ", dimVal="
				+ dimVal + ", regdt=" + regdt + ", uptdt=" + uptdt + "]";
	}
}
