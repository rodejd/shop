package com.wepinit.wepinit_shop.xmall.admin.vo.promotion;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotion;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotionGrp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PromotionVO extends PageMaker {
	private int pmno;
	private String loccd;
	private String title;
	private String sdt;
	private String stm;
	private String edt;
	private String etm;
	private String pcImg;
	private String mobileImg;
	private MultipartFile pcImgFile;
	private MultipartFile mobileImgFile;
	private String copy1;
	private String copy2;
	private String useYn;
	private String regid;
	private String regdt;
	private String modid;
	private String moddt;
	
	//검색
	private String schUseYn;
	private String schWord;
	private String schSdt;
	private String schEdt;
	private int totalCnt;
	private String mode;
	private String odby;
	
	//output
	private List<GdPromotion> promotionList = null;
	private GdPromotion promotionObj = null;
	private List<GdPromotionGrp> groupList = null;
	private List<GdPromotionGoods> goodsList = null;
	
	public int getPmno() {
		return pmno;
	}
	public void setPmno(int pmno) {
		this.pmno = pmno;
	}
	public String getLoccd() {
		return loccd;
	}
	public void setLoccd(String loccd) {
		this.loccd = loccd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getStm() {
		return stm;
	}
	public void setStm(String stm) {
		this.stm = stm;
	}
	public String getEdt() {
		return edt;
	}
	public void setEdt(String edt) {
		this.edt = edt;
	}
	public String getEtm() {
		return etm;
	}
	public void setEtm(String etm) {
		this.etm = etm;
	}
	public String getPcImg() {
		return pcImg;
	}
	public void setPcImg(String pcImg) {
		this.pcImg = pcImg;
	}
	public String getMobileImg() {
		return mobileImg;
	}
	public void setMobileImg(String mobileImg) {
		this.mobileImg = mobileImg;
	}
	public MultipartFile getPcImgFile() {
		return pcImgFile;
	}
	public void setPcImgFile(MultipartFile pcImgFile) {
		this.pcImgFile = pcImgFile;
	}
	public MultipartFile getMobileImgFile() {
		return mobileImgFile;
	}
	public void setMobileImgFile(MultipartFile mobileImgFile) {
		this.mobileImgFile = mobileImgFile;
	}
	public String getCopy1() {
		return copy1;
	}
	public void setCopy1(String copy1) {
		this.copy1 = copy1;
	}
	public String getCopy2() {
		return copy2;
	}
	public void setCopy2(String copy2) {
		this.copy2 = copy2;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	public String getSchUseYn() {
		return schUseYn;
	}
	public void setSchUseYn(String schUseYn) {
		this.schUseYn = schUseYn;
	}
	public String getSchWord() {
		return schWord;
	}
	public void setSchWord(String schWord) {
		this.schWord = schWord;
	}
	public String getSchSdt() {
		return schSdt;
	}
	public void setSchSdt(String schSdt) {
		this.schSdt = schSdt;
	}
	public String getSchEdt() {
		return schEdt;
	}
	public void setSchEdt(String schEdt) {
		this.schEdt = schEdt;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getOdby() {
		return odby;
	}
	public void setOdby(String odby) {
		this.odby = odby;
	}
	public List<GdPromotion> getPromotionList() {
		return promotionList;
	}
	public void setPromotionList(List<GdPromotion> promotionList) {
		this.promotionList = promotionList;
	}
	public GdPromotion getPromotionObj() {
		return promotionObj;
	}
	public void setPromotionObj(GdPromotion promotionObj) {
		this.promotionObj = promotionObj;
	}
	public List<GdPromotionGrp> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GdPromotionGrp> groupList) {
		this.groupList = groupList;
	}
	public List<GdPromotionGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GdPromotionGoods> goodsList) {
		this.goodsList = goodsList;
	}
}
