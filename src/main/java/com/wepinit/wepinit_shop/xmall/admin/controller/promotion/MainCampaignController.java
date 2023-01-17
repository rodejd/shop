package com.wepinit.wepinit_shop.xmall.admin.controller.promotion;

import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.promotion.MainCampaignService;
import com.wepinit.wepinit_shop.xmall.admin.vo.promotion.MainCampaignVO;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMainCampaign;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 메인 캠페인 관리
 */
@Controller
@RequestMapping("/shop/admin/promotion/*")
public class MainCampaignController {

	@Resource
	MainCampaignService service;
	
	/**
	 * 리스트 페이지
	 * @param mainCampaignVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "campaign/list")
	public String mainCampaign(@ModelAttribute("mainCampaignVO") MainCampaignVO mainCampaignVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//총건수
		mainCampaignVO.setTotalCnt(service.selectMainCampaignTotalCount());
		
		//검색 총건수 조회
		mainCampaignVO.setRowCount(service.selectMainCampaignCount(mainCampaignVO));
		
		if (mainCampaignVO.getRowCount() > 0) {
			mainCampaignVO.setCampaignList(service.selectMainCampaignList(mainCampaignVO));
		}
		
		return "/shop/admin/promotion/maincampaign";
	}
	
	/**
	 * 등록 & 수정 페이지
	 * @param mainCampaignVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="campaign/register")
	public String mainCampaignRegister(@ModelAttribute("mainCampaignVO") MainCampaignVO mainCampaignVO, HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		//수정일 경우
		if( StringUtils.equals("modify", mainCampaignVO.getMode()) ) {
			mainCampaignVO.setCampaignObj(service.selectMainCampaign(mainCampaignVO.getSno()));
		}
		return "/shop/admin/promotion/maincampaign_register";
	}
	
	/**
	 * DB처리
	 * @param mainCampaignVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="campaign/indb")
	public String mainCampaignIndb(@ModelAttribute("mainCampaignVO") MainCampaignVO mainCampaignVO) throws Exception {
		try {
			AwsFileUtil awsfileUtil = new AwsFileUtil();
			String awsKey = "";
			String pcImg = "";
			String moImg = "";
			//삭제
			if( StringUtils.equals("delete", mainCampaignVO.getMode()) ) {
				service.deleteMainCampaign(mainCampaignVO.getSno());
				
				// S3 파일삭제
				awsKey = "campaign/" + String.valueOf(mainCampaignVO.getSno());
				awsfileUtil.deleteList(awsKey);
			}else {
				//등록
				if( StringUtils.equals("register", mainCampaignVO.getMode()) ) {
					mainCampaignVO.setRegid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
					service.insertMainCampaign(mainCampaignVO);
					
				//수정
				}else if( StringUtils.equals("modify", mainCampaignVO.getMode()) ) {
					GdMainCampaign campaignInfo = service.selectMainCampaign(mainCampaignVO.getSno());
					
					mainCampaignVO.setModid(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
					service.updateMainCampaign(mainCampaignVO);
					
					//이미지 삭제
					if( mainCampaignVO.getPcImgFile() != null) {
						if( !mainCampaignVO.getPcImgFile().isEmpty() && FileUtil.getChkAwsFile(campaignInfo.getPcImg()) ){
							awsKey = "campaign/" + String.valueOf(mainCampaignVO.getSno()) + "/" + FileUtil.getUrlFileName(campaignInfo.getPcImg());
							// S3 파일삭제
							awsfileUtil.delete(awsKey);
						}
					}
					
					if( mainCampaignVO.getMobileImgFile() != null) {
						if( !mainCampaignVO.getMobileImgFile().isEmpty() && FileUtil.getChkAwsFile(campaignInfo.getMobileImg()) ){
							awsKey = "campaign/" + String.valueOf(mainCampaignVO.getSno()) + "/" + FileUtil.getUrlFileName(campaignInfo.getMobileImg());
							// S3 파일삭제
							awsfileUtil.delete(awsKey);
						}
					}
					
					//이미지타입에서 텍스트타입으로 변경시 업로드한 이미지 전체 삭제
					if( StringUtils.equals("I", campaignInfo.getGbn()) && StringUtils.equals("T", mainCampaignVO.getGbn()) ) {
						// S3 파일삭제
						awsKey = "campaign/" + String.valueOf(mainCampaignVO.getSno());
						awsfileUtil.deleteList(awsKey);
					}
					
					//이미지 변경이 아닐경우 기존이미지로 값설정
					if( StringUtils.isBlank(pcImg) ) pcImg = campaignInfo.getPcImg();
					if( StringUtils.isBlank(moImg) ) moImg = campaignInfo.getMobileImg();
				}
				
				//이미지 타입
				if( StringUtils.equals("I", mainCampaignVO.getGbn()) ) { 
					//PC 배너이미지
					if(!mainCampaignVO.getPcImgFile().isEmpty()){
						// AWS 파일업로드
						String awsPath = "campaign/" + String.valueOf(mainCampaignVO.getSno()) + "/";
						pcImg = FileUtil.awsUploadFile(mainCampaignVO.getPcImgFile().getOriginalFilename(), mainCampaignVO.getPcImgFile().getBytes(), awsPath);
					}
					
					//MO 배너 이미지
					if(!mainCampaignVO.getMobileImgFile().isEmpty()){
						// AWS 파일업로드
						String awsPath = "campaign/" + String.valueOf(mainCampaignVO.getSno()) + "/";
						moImg = FileUtil.awsUploadFile(mainCampaignVO.getMobileImgFile().getOriginalFilename(), mainCampaignVO.getMobileImgFile().getBytes(), awsPath);
					}
				}
				
				MainCampaignVO updateCampaignVO = new MainCampaignVO();
				updateCampaignVO.setSno(mainCampaignVO.getSno());
				updateCampaignVO.setPcImg(pcImg);
				updateCampaignVO.setMobileImg(moImg);
				service.updateMainCampaignByImage(updateCampaignVO);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/shop/admin/promotion/campaign/list";
	}
}
