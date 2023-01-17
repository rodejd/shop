package com.wepinit.wepinit_shop.xmall.common.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Date;

public class GdFaq extends PageMaker {
	
	private int sno = 0;
	private String itemcd;
	private String question;
	private String descant;
	private String answer;
	private int sort = 0;
	private Date regdt;
	private String best;
	private int bestsort;
	private String itemnm;
	
	private int uprow;
	private int downrow;
	private int lastsort;
	
	
	
	public String getItemnm() {
		return itemnm;
	}
	public void setItemnm(String itemnm) {
		this.itemnm = itemnm;
	}
	public int getUprow() {
		return uprow;
	}
	public void setUprow(int uprow) {
		this.uprow = uprow;
	}
	public int getDownrow() {
		return downrow;
	}
	public void setDownrow(int downrow) {
		this.downrow = downrow;
	}
	public int getLastsort() {
		return lastsort;
	}
	public void setLastsort(int lastsort) {
		this.lastsort = lastsort;
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getItemcd() {
		return itemcd;
	}
	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDescant() {
		return descant;
	}
	public void setDescant(String descant) {
		this.descant = descant;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getBest() {
		return best;
	}
	public void setBest(String best) {
		this.best = best;
	}
	public int getBestsort() {
		return bestsort;
	}
	public void setBestsort(int bestsort) {
		this.bestsort = bestsort;
	}

}
