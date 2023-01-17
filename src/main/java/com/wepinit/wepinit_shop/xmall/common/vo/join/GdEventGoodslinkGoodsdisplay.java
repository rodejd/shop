package com.wepinit.wepinit_shop.xmall.common.vo.join;

public class GdEventGoodslinkGoodsdisplay {

	//gd_event
	private int sno = 0;
	private String subject = "";
	private String body = "";
	private String sdate = "";
	private String edate = "";
	private String tpl = "";
	private int size = 0;
	private String page_num= "";
	private int cols = 0;
	private String category = "";
	private String rcategory = "";
	private String rbrand = "";
	private String wview = "";
	private String win = "";
	private String attach = "";
	
	//gd_goods_link
	
	/*private int sno;*/
	private int goodsno;
	/*private String category;*/
	private int sort;
	private int hidden;
	
	//gd_goods_display
	private int no;
	/*private int goodsno;*/
	private String mode;
	/*private int sort;*/
	
	//linkContorller indb에서 사용
	private int disCount=0;
	
	public int getDisCount() {
		return disCount;
	}
	public void setDisCount(int disCount) {
		this.disCount = disCount;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getTpl() {
		return tpl;
	}
	public void setTpl(String tpl) {
		this.tpl = tpl;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getPage_num() {
		return page_num;
	}
	public void setPage_num(String page_num) {
		this.page_num = page_num;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRcategory() {
		return rcategory;
	}
	public void setRcategory(String rcategory) {
		this.rcategory = rcategory;
	}
	public String getRbrand() {
		return rbrand;
	}
	public void setRbrand(String rbrand) {
		this.rbrand = rbrand;
	}
	public String getWview() {
		return wview;
	}
	public void setWview(String wview) {
		this.wview = wview;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
}
