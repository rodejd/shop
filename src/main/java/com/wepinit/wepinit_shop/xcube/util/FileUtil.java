/*
 * @(#)FileUtil.java	
 * 
 *
 */

package com.wepinit.wepinit_shop.xcube.util;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import org.apache.commons.lang.StringUtils;




/**
 * <pre>
 *  file 관련 클래스
 * </pre>
 * 
 * <p>
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	// 화일 삭제
	public static void deleteFile(String filePath, String fileName)
			throws Exception {
		File delFile;
		if (fileName != null && fileName.length() != 0) {
			delFile = new File(filePath, fileName);
			if (delFile.isFile()) {
				if (!delFile.delete())
					throw new Exception();
			}
		}
	}
	
	public static int deleteFile(String s)
    {
        try {
			File file;
	        if(s == null || "".equals(s.trim()))
	            return -1;
	        file = new File(s);
	        if(!file.exists())
	            //break MISSING_BLOCK_LABEL_52;
	        	return -2;
	        if(file.canWrite())
	        {
	            file.delete();
	            //break MISSING_BLOCK_LABEL_60;'
	            
	        }
        } catch (Exception e ) {
        //return -2;
        //return -1;
        //Exception exception;
        //exception;
        	return -1;
        
        }
        return 0;
    }
	
	public static int deleteFile(File file) {
		try {
			if(file.canWrite()){
				file.delete();
			}
		}catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	
	public static int deleteFileList(List<String> fileList) {
		int result = 0;
		try {
			for(String filePath : fileList) {
				File file = new File(filePath);
				if( file.exists() ) {
					if( file.delete() ){
						result++;
					}
				}
			}
		}catch (Exception e) {
			result = -1;
			// TODO: handle exception
		}
		return result;
	}
	
	public static boolean deleteUrlFile(String path, String name) {
        Path filePath = FileSystems.getDefault().getPath(path, name);
        try {
            Files.delete(filePath);
        } catch (IOException | SecurityException e) {
            return false;
        }
        return true;
    }

	/**
	 * 파일이 존재하는지 여부
	 * 
	 * @param filePath
	 * @param fileName
	 * @return boolean
	 */
	public static boolean existsFile(String filePath, String fileName) {
		File file;
		boolean result = false;
		if (fileName != null && fileName.length() != 0) {
			file = new File(filePath, fileName);
			result = file.isFile();
		}
		return result;
	}

	/**
	 * [설명] 파일내용을 읽어 반환한다.
	 * @param s
	 * @return ArrayList
	 */
	public static ArrayList getOneLineListToFile(String s) {
        ArrayList arraylist = new ArrayList();
        FileReader filereader = null;
        BufferedReader bufferedreader = null;
        try
        {
            filereader = new FileReader(new File(s));
            bufferedreader = new BufferedReader(filereader);
        }
        catch(Throwable throwable)
        {
            try
            {
                if(filereader != null)
                    filereader.close();
                if(bufferedreader != null)
                    bufferedreader.close();
            }
            catch(Exception exception) { }
            return null;
        }
        try
        {
            String s1;
            while((s1 = bufferedreader.readLine()) != null) 
                arraylist.add(s1);
            if(filereader != null)
                filereader.close();
            if(bufferedreader != null)
                bufferedreader.close();
        }
        catch(IOException ioexception)
        {
            try
            {
                if(filereader != null)
                    filereader.close();
                if(bufferedreader != null)
                    bufferedreader.close();
            }
            catch(IOException ioexception1) { }
            //System.out.println("Error(Util) in reading=[" + ioexception + "]");
        }
        return arraylist;
    }
	
    
    /**
     * 이미지의 사이즈를 설정하여 썸네일 이미지로 copy함
     * @param path
     * @param fileName
     * @param thumList
     */
    public static void ThumbNailImageCreate(String path, String fileName, ArrayList<HashMap<String, String>> thumList) {
    	File originalFile 	= null;
    	File createFile 	= null;
    	
    	Image srcImg 	= null;
    	
    	String fileExt	= "";
    	
    	int i 	= 0;
    	
    	int RATIO 	= 0;
    	int width 	= 0;
		int height 	= 0;
		int srcWidth 	= 0;
		int srcHeight 	= 0;
		int destWidth 	= 0;
		int destHeight	= 0;
		
		int[] pixels 	= null;
		
		double ratio 	= 0;
		
    	try {
    		originalFile = new File(path + File.separatorChar + fileName);
    		
    		if ( isCheckFileImgExt(originalFile.getName()) ) {
				srcImg = ImageIO.read(originalFile);
			} else {
				// BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성
				// 이렇게 하는 이유는 getScaledInstance를 통해 구한 이미지를
				// PixelGrabber.grabPixels로 리사이즈 할때
				// 빠르게 처리하기 위함
				srcImg = new ImageIcon(originalFile.toURL()).getImage();
			}
    		
    		for(i = 0; i < thumList.size(); i++) {
    			RATIO 	= 0;
    			
    			fileName 	= thumList.get(i).get("FILE_NAME");
    			path		= thumList.get(i).get("FILE_PATH");
    			fileExt		= thumList.get(i).get("FILE_EXT");
				width 		= Integer.parseInt(thumList.get(i).get("FILE_WIDTH"));
				height 		= Integer.parseInt(thumList.get(i).get("FILE_HEIGHT"));
				
				// 생성할 이미지의 위치경로
				if ( "".equals(StringUtil.nullConv(path, "")) ) {
					throw new IOException("저장경로가 지정되지 않았음.");
				}
				// 생성할 이미지의 확장자
				if ( "".equals(StringUtil.nullConv(fileExt, "")) ) {
					fileExt = "jpg";
				}
				
				srcWidth 	= srcImg.getWidth(null);
    			srcHeight 	= srcImg.getHeight(null);
            
    			destWidth 	= -1;
    			destHeight 	= -1;
            
    			if ( 0 < width ) {
    				destWidth = width;
    			}else destWidth = srcWidth;
            
    			if ( 0 < height) {
    				destHeight = height;
    			}else destHeight = srcHeight;
           
    			if(width == RATIO && height == RATIO) {
    				destWidth 	= srcWidth;
    				destHeight 	= srcHeight;
    			} else if (width == RATIO) {
    				ratio = ((double)destHeight) / ((double)srcHeight);
    				destWidth = (int)(srcWidth * ratio);
    			} else if (height == RATIO) {
    				ratio = ((double)destWidth) / ((double)srcWidth);
    				destHeight = (int)(srcHeight * ratio);
    			}
            
    			Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
    			pixels = new int[destWidth * destHeight]; 
    			PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
    			try {
    				pg.grabPixels();
    			} catch (InterruptedException e) {
    				throw new IOException(e.getMessage());
    			} 
    			BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB); 
    			destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
            
    			// 생성할 이미지 파일명
				if ( "".equals(StringUtil.nullConv(fileName, ""))) {
					createFile = new File(path + File.separatorChar + "t_"+width+"_" +fileName);
				}else{
					createFile = new File(path + File.separatorChar + fileName + "." + fileExt);
				}
				System.out.println("@@@path + File.separatorChar::"+path + File.separatorChar);
				System.out.println("@@@fileName + . + fileExt::"+fileName + "." + fileExt );
    			ImageIO.write(destImg, "jpg", createFile);
    		}
    	}catch(IOException e){
    		e.getMessage();
    	}
    }
        
//    /**
//     * 파일 확장자 확인
//     * 	-> 2010.12.31 홍웅선 추가 받아온 String이 checkupStr의 포함되어있는 String과 겹치는 언어가있는지 확인하여 겹치면 true를 던짐 
//     */
    public static boolean isCheckFileImgExt(String str)
    {
    	boolean b = false;
    	String strForbidden = ".*((.jpeg)|(.jpg)|(.gif)|(.png)|(.bmp)).*";
    	if ( str.toLowerCase().matches(strForbidden)){
    		b = true;
    	}
    	return b;
//    	boolean flag = false;
//    	String checkupStr 	= "jpg,gif,png";
//    	String chengingStr 	= checkStr.substring(checkStr.lastIndexOf(".")+1,checkStr.length()).toLowerCase();
//    	if(checkupStr.indexOf(chengingStr) > -1)
//    	{
//    		flag = true;
//    	}
//    	return flag;
    }
      
    //indb.jsp jsp 파일 생성 util - HashpMap 변수 생성
    public static void savefile(String filename, String text, boolean overwrite){
    	try {
    		BufferedWriter out = new BufferedWriter(new FileWriter(filename, overwrite));
    		out.write(text);
    		//out.newLine();
    		out.flush();
    		out.close();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
	public static String uploadFile(String originalName, byte[] fileData) throws Exception {
		return uploadFile(originalName, fileData, null) ;
	}
	
	public static String uploadFile(String originalName, byte[] fileData, String dir) throws Exception {
		String directory = "goods";
		if(dir != null && !"".equals(dir)){
			directory = dir;
		}
		return uploadFile(originalName, fileData, directory, false) ;
	}
	/*
	 * 파일 업로드
	 * parameter : originalName(파일명), fileData(파일실 데이터), dir(저장 경로), overWirte(파일 덮어 쓰기 여부)
	 * return : 파일명
	 */
	public static String uploadFile(String originalName, byte[] fileData, String dir, boolean overWrite) throws Exception {
		String directory = "goods";
		if(dir != null && !"".equals(dir)){
			directory = dir;
		}
		String savedName = originalName;
		if(!overWrite){
			savedName = "[" + ShopLibFunction.getUniqueKey() + "]" + originalName;
		}else{}
		String uploadPath = "";	
		File target = new File(uploadPath+File.separator+"shop"+File.separator+"data"+File.separator+"upload"+File.separator+directory, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	public static String uploadFile2(String directory, String originalName, byte[] fileData, boolean overWrite) throws Exception {
		String savedName = originalName;
		if(!overWrite){
			savedName = "[" + ShopLibFunction.getUniqueKey() + "]" + originalName;
		}else{}
		File target = new File(directory, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	/*
	 * 파일 업로드(unique key 제외)
	 * parameter : originalName(파일명), fileData(파일실 데이터), dir(저장 경로), overWirte(파일 덮어 쓰기 여부)
	 * return : 파일명
	 */
	public static String uploadFile3(String originalName, byte[] fileData) throws Exception {
		File target = new File(ConfigClass.value("FILE_PATH") + "goods/", originalName);
		FileCopyUtils.copy(fileData, target);
		return originalName;
	}
	public static String uploadFile4(String path,String originalName, byte[] fileData) throws Exception {
		File uploadDir = new File(path);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		File target = new File(path, originalName);
		FileCopyUtils.copy(fileData, target);
		return originalName;
	}
	
	/**
	 * 임시경로 생성 및 파일업로드
	 * @param fileName
	 * @param fileData
	 * @return
	 * @throws Exception
	 */
	public static File tempUploadFile(String fileName, byte[] fileData) throws Exception {
		//폴더생성
		createFolder(ConfigClass.UPLOAD_PATH + "temp");
		
		File target = new File(ConfigClass.UPLOAD_PATH + "temp/", fileName);
		FileCopyUtils.copy(fileData, target);
		return target;
	}
	
	/**
	 * AWS S3 파일업로드
	 * @param fileName : 파일명
	 * @param fileData : 파일 데이터
	 * @param awsPath : s3 업로드 경로
	 * @return
	 * @throws Exception
	 */
	public static String awsUploadFile(String fileName, byte[] fileData, String awsPath) throws Exception {
		String imgUrl = "";
		File tempFile = null;
		try {
			String savedName = "[" + ShopLibFunction.getUniqueKey() + "]" + fileName;
			//임시경로에 파일업로드
			tempFile = tempUploadFile(savedName, fileData);
			
			if( tempFile.exists() ) {
				//S3 파일업로드
				AwsFileUtil awsfileUtil = new AwsFileUtil();
				String key = awsPath + savedName;
				PutObjectResult awsResult = awsfileUtil.upload(tempFile, key);
				
				if(StringUtils.isNotBlank(awsResult.getVersionId()) ) {
					//이미지 URL 조회
					imgUrl = StringUtil.getNotParamURL(awsfileUtil.getFileURL(key));
				}
			}
		}catch (Exception e) {
			logger.error("awsUploadFile Upload Error!!");
		}
		
		//임시 파일 삭제
		try {
			if(tempFile != null) {
				deleteFile(tempFile);
			}
		}catch (Exception e) {
			logger.error("awsUploadFile Delete Error!!");
		}
		
		return imgUrl;
	}
	
	public static StringBuffer getFileContents(String path, String fileName, String fileType){
		String fileNameTmp = StringUtil.nullConv(fileName);
		StringBuffer sb = new StringBuffer();
		ArrayList list = FileUtil.getOneLineListToFile(path.concat(fileNameTmp+"."+fileType));
		int i = 0;
		if ( null != list && 0 < list.size() ) {
			i = 0;
			while ( i < list.size() ) {
				sb.append((String)list.get(i) + "\n");
				i++;
			}
		}
		return sb;
	}
	
	/**
	 * 파일 단건 업로드
	 * @param mtfRequest
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> uploadFileData(MultipartHttpServletRequest mtfRequest, String filePath) throws Exception {
		Map<String, Object> fileMap = new HashMap<String,Object>();
		Iterator<String> files = mtfRequest.getFileNames();
		
		while(files.hasNext()){
			String fileName = files.next();
			List<MultipartFile> multipartFileList = mtfRequest.getFiles(fileName);
			
			for(MultipartFile multipartFile : multipartFileList) {
				if(multipartFile.getSize() != 0){
					String orgFile = multipartFile.getOriginalFilename(); //원본파일명
					String fileExt = FileUtil.getFileExt(orgFile); //확장자
					long fileSize = multipartFile.getSize(); //파일크기
					String saveFileName = FileUtil.getTimeStamp() + "." + fileExt; //저장파일명
					String uploadFile = filePath + "/" + saveFileName; //업로드 파일경로 + 저장파일명
					
					//폴더 생성
					FileUtil.createFolder(filePath);
					
					try {
						// 파일업로드
						File file = new File(uploadFile);
						multipartFile.transferTo(file);
						
						//리턴데이터 설정
						fileMap.put("org_file_name", orgFile);
						fileMap.put("save_file_name", saveFileName);
						fileMap.put("content_type", multipartFile.getContentType());
						fileMap.put("save_path", filePath);
						fileMap.put("file_size", fileSize);
						fileMap.put("file_name", fileName);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return fileMap;
	}
	
	/**
	 * 파일 다중 업로드
	 * @param mtfRequest
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String,Object>> uploadFileList(MultipartHttpServletRequest mtfRequest, String filePath) throws Exception {
		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
		Iterator<String> files = mtfRequest.getFileNames();
		
		while(files.hasNext()){
			String fileName = files.next();
			List<MultipartFile> multipartFileList = mtfRequest.getFiles(fileName);
			
			for(MultipartFile multipartFile : multipartFileList) {
				if(multipartFile.getSize() != 0){
					String orgFile = multipartFile.getOriginalFilename(); //원본파일명
					String fileExt = FileUtil.getFileExt(orgFile); //확장자
					long fileSize = multipartFile.getSize(); //파일크기
					String saveFileName = FileUtil.getTimeStamp() + "." + fileExt; //저장파일명
					String uploadFile = filePath + "/" + saveFileName; //업로드 파일경로 + 저장파일명
					
					//폴더 생성
					FileUtil.createFolder(filePath);
					
					try {
						// 파일업로드
						File file = new File(uploadFile);
						multipartFile.transferTo(file);
						
						//리턴데이터 설정
						Map<String, Object> fileMap = new HashMap<String,Object>();
						fileMap.put("org_file_name", orgFile);
						fileMap.put("save_file_name", saveFileName);
						fileMap.put("content_type", multipartFile.getContentType());
						fileMap.put("save_path", filePath);
						fileMap.put("file_size", fileSize);
						fileMap.put("file_name", fileName);
						fileList.add(fileMap);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return fileList;
	}
	
	/**
	 * 폴더 생성
	 * @param filePath
	 * @return
	 */
	public static void createFolder(String filePath) {
		try {
			if( StringUtils.isNotBlank(filePath) ) {
				File file = new File(filePath);
				if(!file.exists()){
					file.mkdirs();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 파일생성시간
	 * @return
	 */
	public static String getTimeStamp() {
		String rtnStr = null;
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnStr;
	}
	
	/**
	 * 파일명 추출(확장자 제거)
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		String returnVal = "";
		try {
			if( StringUtils.isNotBlank(fileName) ) {
				returnVal = fileName.substring(0, fileName.lastIndexOf("."));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}
	
	/**
	 * 파일 확장자 추출
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		String returnVal = "";
		try {
			if( StringUtils.isNotBlank(fileName) ) {
				returnVal = fileName.substring(fileName.lastIndexOf(".") + 1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}
	
	/**
	 * 이미지가 URL로 등로되어있는지 체크
	 * @param imgUrl
	 * @return
	 */
	public static boolean getChkAwsFile(String imgUrl) {
		boolean returnVal = false;
		try {
			if( StringUtils.isNotBlank(imgUrl) ) {
				if( imgUrl.indexOf("http://") != -1 || imgUrl.indexOf("https://") != -1 ) {
					returnVal = true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}
	
	/**
	 * 이미지 URL에서 파일명 추출
	 * @param imgUrl
	 * @return
	 */
	public static String getUrlFileName(String imgUrl) {
		String returnVal = "";
		try {
			if( StringUtils.isNotBlank(imgUrl) ) {
				returnVal = imgUrl.substring( imgUrl.lastIndexOf("/")+1, imgUrl.length() );
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}

	
}