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
package com.wepinit.wepinit_shop.xmall.admin.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsOption;

import java.util.List;

public class GoodsOptionVO {
	
	private int sno;
	private int goodsno;
	private String opt1;
	private String opt2;
	private int consumer;
	private int price;
	private int priceRate;
	private int supply;
	private int supply2;
	private double margin;
	private int stock;
	private int link;
	private String parent = "";
	private String optno = "";
	private String optexplain = "";
	
	private List<GdGoodsOption> goodsOptionList = null;
	private GdGoodsOption goodsOptionObj = null;
	
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public int getConsumer() {
		return consumer;
	}
	public void setConsumer(int consumer) {
		this.consumer = consumer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(int priceRate) {
		this.priceRate = priceRate;
	}
	public int getSupply() {
		return supply;
	}
	public void setSupply(int supply) {
		this.supply = supply;
	}
	public int getSupply2() {
		return supply2;
	}
	public void setSupply2(int supply2) {
		this.supply2 = supply2;
	}
	public double getMargin() {
		return margin;
	}
	public void setMargin(double margin) {
		this.margin = margin;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getLink() {
		return link;
	}
	public void setLink(int link) {
		this.link = link;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getOptno() {
		return optno;
	}
	public void setOptno(String optno) {
		this.optno = optno;
	}
	public String getOptexplain() {
		return optexplain;
	}
	public void setOptexplain(String optexplain) {
		this.optexplain = optexplain;
	}
	
	public List<GdGoodsOption> getGoodsOptionList() {
		return goodsOptionList;
	}
	public void setGoodsOptionList(List<GdGoodsOption> goodsOptionList) {
		this.goodsOptionList = goodsOptionList;
	}
	public GdGoodsOption getGoodsOptionObj() {
		return goodsOptionObj;
	}
	public void setGoodsOptionObj(GdGoodsOption goodsOptionObj) {
		this.goodsOptionObj = goodsOptionObj;
	}
	
	@Override
	public String toString() {
		return "GoodsOptionVO [sno=" + sno 
				+ ", goodsno=" + goodsno 
				+ ", opt1=" + opt1 
				+ ", opt2=" + opt2 
				+ ", consumer="	+ consumer 
				+ ", price=" + price 
				+ ", priceRate=" + priceRate 
				+ ", supply=" + supply 
				+ ", supply2=" + supply2 
				+ ", margin=" + margin
				+ ", stock=" + stock 
				+ ", link=" + link 
				+ ", optno=" + optno
				+ ", optexplain=" + optexplain
				+ "]";
	}
}
