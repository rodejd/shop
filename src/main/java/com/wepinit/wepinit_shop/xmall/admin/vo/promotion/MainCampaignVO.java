package com.wepinit.wepinit_shop.xmall.admin.vo.promotion;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMainCampaign;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MainCampaignVO extends PageMaker {
	private int sno;
	private String title;
	private String sdt;
	private String stm;
	private String edt;
	private String etm;
	private String gbn;
	private String sort;
	private String pcImg;
	private String mobileImg;
	private MultipartFile pcImgFile;
	private MultipartFile mobileImgFile;
	private String regid;
	private String regdt;
	private String modid;
	private String moddt;
	private String useYn;
	
	//검색
	private String mode;
	private String skey;
	private String sword;
	private String ssdt;
	private String sstm;
	private String sedt;
	private String setm;
	private int totalCnt;
	
	//output
	private List<GdMainCampaign> campaignList = null;
	private GdMainCampaign campaignObj = null;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public String getGbn() {
		return gbn;
	}
	public void setGbn(String gbn) {
		this.gbn = gbn;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
	}
	public String getSsdt() {
		return ssdt;
	}
	public void setSsdt(String ssdt) {
		this.ssdt = ssdt;
	}
	public String getSstm() {
		return sstm;
	}
	public void setSstm(String sstm) {
		this.sstm = sstm;
	}
	public String getSedt() {
		return sedt;
	}
	public void setSedt(String sedt) {
		this.sedt = sedt;
	}
	public String getSetm() {
		return setm;
	}
	public void setSetm(String setm) {
		this.setm = setm;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public List<GdMainCampaign> getCampaignList() {
		return campaignList;
	}
	public void setCampaignList(List<GdMainCampaign> campaignList) {
		this.campaignList = campaignList;
	}
	public GdMainCampaign getCampaignObj() {
		return campaignObj;
	}
	public void setCampaignObj(GdMainCampaign campaignObj) {
		this.campaignObj = campaignObj;
	}
}
