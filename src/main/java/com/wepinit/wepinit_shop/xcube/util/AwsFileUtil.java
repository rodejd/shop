package com.wepinit.wepinit_shop.xcube.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  AWS 파일 관련 클래스
 * </pre>
 * 
 * <p>
 */
public class AwsFileUtil {
	// Amazon-s3-sdk
	private AmazonS3 s3Client;
	final private String accessKey = "AKIAR54DXEPGC275BLWF";
	final private String secretKey = "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";
	private Regions clientRegion = Regions.AP_NORTHEAST_2;
	private String bucket = "wepinit-web";

	public AwsFileUtil() {
		createS3Client();
	}

	// singleton pattern
	static private AwsFileUtil instance = null;

	public static AwsFileUtil getInstance() {
		if (instance == null) {
			return new AwsFileUtil();
		} else {
			return instance;
		}
	}

	// aws S3 client 생성
	private void createS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(clientRegion).build();
	}

	public PutObjectResult upload(File file, String key) {
		return uploadToS3(new PutObjectRequest(this.bucket, key, file));
	}

	public PutObjectResult upload(InputStream is, String key, String contentType, long contentLength) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(contentType);
		objectMetadata.setContentLength(contentLength);

		return uploadToS3(new PutObjectRequest(this.bucket, key, is, objectMetadata));
	}

	// PutObjectRequest는 Aws S3 버킷에 업로드할 객체 메타 데이터와 파일 데이터로 이루어져있다.
	private PutObjectResult uploadToS3(PutObjectRequest putObjectRequest) {
		PutObjectResult result = new PutObjectResult();
		try {
			putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // file permission
			result = this.s3Client.putObject(putObjectRequest);
			
			System.out.println(String.format("[%s] upload complete", putObjectRequest.getKey()));
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public void copy(String orgKey, String copyKey) {
		try {
			// Copy 객체 생성
			CopyObjectRequest copyObjRequest = new CopyObjectRequest(this.bucket, URLDecoder.decode(orgKey), this.bucket, URLDecoder.decode(copyKey));
			// 파일 권한
			copyObjRequest.setCannedAccessControlList(CannedAccessControlList.PublicRead);
			// Copy
			this.s3Client.copyObject(copyObjRequest);

			System.out.println(String.format("Finish copying [%s] to [%s]", orgKey, copyKey));
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void delete(String key) {
		try {
			// Delete 객체 생성
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, URLDecoder.decode(key));
			// Delete
			this.s3Client.deleteObject(deleteObjectRequest);
			
			System.out.println(String.format("[%s] deletion complete", URLDecoder.decode(key)));
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void deleteList(String key) {
		try {
			List<String> awsFileList = list(key);
			if(awsFileList != null) {
				for(String fileName : awsFileList) {
					// Delete 객체 생성
					DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, URLDecoder.decode(fileName));
					// Delete
					this.s3Client.deleteObject(deleteObjectRequest);
				}
			}
			System.out.println(String.format("[%s] deletion complete", key));
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> list(String key) {
		List<String> awsFileList = new ArrayList<String>();
		try {
			ListObjectsRequest listObject = new ListObjectsRequest();
			listObject.setBucketName(this.bucket);
			listObject.setPrefix(key);
			
			ObjectListing objects = this.s3Client.listObjects(listObject);
			objects = this.s3Client.listObjects(listObject);
			
			if(!objects.getObjectSummaries().isEmpty()) {
				for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
					awsFileList.add(objectSummary.getKey());
				}
			}else {
				awsFileList = null;
			}
		}catch (Exception e) {
			return null;
		}
		return awsFileList;
	}
	
	//파일 URL
	public String getFileURL(String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		return this.s3Client.generatePresignedUrl(new GeneratePresignedUrlRequest(bucket, imgName)).toString();
	}
}