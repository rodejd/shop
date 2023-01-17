package com.wepinit.wepinit_shop.xcube.util;

import com.wepinit.wepinit_shop.xmall.api.vo.ProductImageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.services.s3.model.PutObjectResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  API File 관련 클래스
 * </pre>
 * 
 * <p>
 */
public class ApiFileUtil {
	private static final Logger logger = LoggerFactory.getLogger(ApiFileUtil.class);
	
	public static void main(String[] args) {
		/*
		try {
			List<ProductImageVO> testList = new ArrayList<ProductImageVO>();
			
			ProductImageVO testVO = new ProductImageVO();
			
			testVO = new ProductImageVO();
			testVO.setSeq(1);
			testVO.setImgUrl("https://www.abcd.com/img/1.png");
			testList.add(testVO);
			
			testVO = new ProductImageVO();
			testVO.setSeq(1);
			testVO.setImgUrl("https://ritzmall-web.s3.ap-northeast-2.amazonaws.com/goods/516827/pwbb023s21fle0091068.jpg");
			testList.add(testVO);
			
			String fileTempPath = "D:/eclipse-egov/workspace-ritzmall/Ritzmall/src/main/webapp/upload/images/temp/";
			getUrlImage("987987", testList, fileTempPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
//		String id = "acjms@naver.com";
//		String pwd = "*4E4E885DEC94C218F0B796EDB3C47FD4EBBBC703";
//		
//		String token =  "/shop/member/change_pwd?token=" + Aes128Util.encode(id + "|" + pwd + "|" +  DateUtil.getFormatToday("yyyy-MM-dd HH:mm:ss"));
//		System.out.println(token);
//		
////		String time = DateUtil.getFormatToday("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.MINUTE, -50);
//		String test =  "/shop/member/change_pwd?token=" + Aes128Util.encode(id + "|" + pwd + "|" + sdformat.format(cal.getTime()));
//		System.out.println(test);
		
		
		String str = "ghdrlfehd123";
		
		int num = 0;
		if( str.indexOf("@") == -1) {
			num = Math.round(str.length() / 2);
			str = str.replaceAll("(?<=.{"+num+"})." , "*");
		}else {
			String id = str.substring(0 , str.indexOf("@") );
			num = Math.round(id.length() / 2);
			id = id.replaceAll("(?<=.{"+num+"})." , "*");
			System.out.println(str.substring( str.indexOf("@") , str.length()));
			
			str = id +  str.substring( str.indexOf("@") , str.length() );
		}
		
		System.out.println(str);
		
		
	}
	
	/**
	 * 이미지 URL 설정
	 * @param goodsCd : 상품코드
	 * @param images : 이미지 리스트
	 * @param fileTempPath : 임시 업로드 경로명
	 * @return
	 * @throws IOException
	 */
	public static List<ProductImageVO> getUrlImage(String goodsCd, List<ProductImageVO> images, String fileTempPath) throws IOException {
		List<ProductImageVO> resultList = new ArrayList<ProductImageVO>();
		
		if( images.isEmpty() ) {
			return null;
		}
		
		List<String> deleteFileList = new ArrayList<String>();
		try {
			List<ProductImageVO> fileList = new ArrayList<ProductImageVO>();
			
			AwsFileUtil awsfileUtil = new AwsFileUtil();
			for(ProductImageVO imgVO : images) {
				String fileName = FileUtil.getUrlFileName(imgVO.getImgUrl());
				String fileFullPath = fileTempPath + fileName;
				
				//폴더 생성
				FileUtil.createFolder(fileTempPath);
				
				// URL로 파일 로컬에 다운로드
				InputStream in = null;
				String imgUrl = imgVO.getImgUrl().replace("https://", "http://");
				try {
					in = new URL(imgUrl).openStream();
					Path imagePath = Paths.get(fileFullPath);
					Files.copy(in, imagePath);
				}catch (Exception e) {
					logger.error("URL FileDownload Read Error!!");
					e.printStackTrace();
				}finally {
					if (in != null) {
						in.close();
					}
				}
				
				// 다운로드된 파일을 리사이징
				JpegReader jpegReader = new JpegReader();
				BufferedImage image = jpegReader.readImage(new File(fileFullPath));
				if(image != null) {
					File file = new File(fileFullPath);
					if(file.exists()){
						ProductImageVO productImageVO = new ProductImageVO();
						productImageVO.setSeq(imgVO.getSeq());
						productImageVO.setFilePath(fileFullPath);
						productImageVO.setFileType("L");
						fileList.add(productImageVO);
						
						// seq가 1이면 리사이징
						if( imgVO.getSeq() == 1) {
							List<ProductImageVO> resizeList = getImageResize(imgVO.getSeq() , image, fileTempPath, fileName);
							if(resizeList != null) {
								fileList.addAll( resizeList );
							}
						}
					}else {
						return null;
					}
					
				}else {
					logger.debug("URL FileDownload Error!!");
					logger.debug("URL FileDownload Error Link : " + imgUrl);
					//비정상적인 파일 삭제 리스트에 추가
					deleteFileList.add(fileFullPath);
				}
			}
			
			//파일 AWS S3에 업로드
			if(!fileList.isEmpty()) {
				// AWS폴더 삭제
				awsfileUtil.deleteList("goods/" + goodsCd);
				
				for(ProductImageVO productImageVO : fileList) {
					File file = new File(productImageVO.getFilePath());
					if(file.exists()){
						//AWS S3 파일업로드
						String fn = FileUtil.getUrlFileName(productImageVO.getFilePath());
						String key = "goods/" + goodsCd + "/" + fn ;
						PutObjectResult awsResult = awsfileUtil.upload(file, key);
						
						//AWS 파일업로드 후 로컬파일 삭제
						//if( StringUtils.isNotBlank(awsResult.getVersionId()) ) {
							//이미지 URL 조회
							String imgUrl = StringUtil.getNotParamURL(awsfileUtil.getFileURL(key));
							
							//로컬 파일 삭제 리스트에 추가
							deleteFileList.add(productImageVO.getFilePath());
							
							// 리턴정보 설정
							ProductImageVO imgVO = new ProductImageVO();
							imgVO.setSeq(productImageVO.getSeq());
							imgVO.setFilePath(productImageVO.getFilePath());
							imgVO.setImgUrl(imgUrl);
							imgVO.setFileType(productImageVO.getFileType());
							resultList.add(imgVO);
						//}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//파일 삭제
		try {
			FileUtil.deleteFileList(deleteFileList);
		}catch (Exception e) {
			logger.error("File Delete Error!!");
			e.printStackTrace();
		}
		
		logger.debug("File Upload S3 Path : Amazon S3 > ritzmall-web > goods > " +  goodsCd);
		logger.debug("Upload File List Start");
		for(ProductImageVO productImageVO : resultList) {
			logger.debug(productImageVO.getImgUrl());
		}
		logger.debug("Upload File List End");
		logger.debug("==== File Upload Complete ====");
		
		return resultList;
	}
	
	/**
	 * 이미지 리사이즈
	 * @param seq : 이미지 순번
	 * @param image : 이미지
	 * @param filePath : 파일경로
	 * @param fileName : 파일명
	 * @return
	 */
	public static List<ProductImageVO> getImageResize(int seq, Image image, String filePath, String  fileName) {
		List<ProductImageVO> resultList = new ArrayList<ProductImageVO>();
		String[] filetTypeList = {"i","s"};
		for(String fileType : filetTypeList) {
			int width = 0; // 변경 할 넓이
			int height = 0; // 변경 할 높이
			double ratio;
			
			//메인이미지
			if(fileType == "i") {
				width = 700;
				height = 700;
			//리스트이미지
			}if(fileType == "s") {
				width = 300;
				height = 300;
			}
			
			// 원본 이미지 사이즈 가져오기
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			
			//넓이 기준
			if(imageWidth > imageHeight) {
				ratio = (double) width/(double)imageWidth;
				width = (int)(imageWidth * ratio);
				height = (int)(imageHeight * ratio);
			//높이기준
			}else if(imageWidth < imageHeight) {
				ratio = (double) height/(double)imageHeight;
				width = (int)(imageWidth * ratio);
				height = (int)(imageHeight * ratio);
			}
			
			if(width != 0 && height != 0) {
				String saveFilelPath = filePath + FileUtil.getFileName(fileName) + "_" + fileType +"."+ FileUtil.getFileExt(fileName);
				try {
					// 이미지 리사이즈
					// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
					// Image.SCALE_FAST : 이미지 부드러움보다 속도 우선
					// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
					// Image.SCALE_SMOOTH : 속도보다 이미지 부드러움을 우선
					// Image.SCALE_AREA_AVERAGING : 평균 알고리즘 사용
					Image resizeImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
					// 새 이미지 저장하기
					BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					Graphics g = newImage.getGraphics();
					g.drawImage(resizeImage, 0, 0, null);
					g.dispose();
					ImageIO.write(newImage, FileUtil.getFileExt(fileName), new File(saveFilelPath));
					
					File file = new File(saveFilelPath);
					if(file.exists()){
						ProductImageVO productImageVO = new ProductImageVO();
						productImageVO.setSeq(seq);
						productImageVO.setFilePath(saveFilelPath);
						productImageVO.setFileType(fileType.toUpperCase());
						resultList.add(productImageVO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultList;
	}
}