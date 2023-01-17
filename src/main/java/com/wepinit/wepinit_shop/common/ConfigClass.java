
package com.wepinit.wepinit_shop.common;
import com.wepinit.wepinit_shop.xcube.ConfingValidator;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

public class ConfigClass {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigClass.class);

//	private final static Properties props;
//
//	public final static boolean DEBUG_FLAG;
//	public final static boolean TIMESTAMP_FLAG;
//	public final static boolean DBPOOL_FLAG;
//	public final static boolean LOG4J_FLAG;
//
//	public final static boolean TOMCAT_FLAG;
	
	public final static boolean MOB_FLAG;		
	
	//Default 로그경로
	public final static String LOG_PATH;	
	//System 로그경로
	public final static String SYS_LOG_PATH;    
	//TimeStamp 로그경로	
	public final static String TIMESTAMP_LOG_PATH;
    //batch 작업 로그경로
    public final static String BATCH_LOG_PATH;   
    
    //WEB HOME URL
    public final static String 	HOME_URL;
        
	//커넥션풀 연결을 위한 DataSource명
//	public final static String DATASOURCE;
//	//Direct DB 연결을 위한 계정 정보
//	public final static String JDBC_URL;
//	public final static String DBID;
//	public final static String DBPW;
//	public final static String JDBC_DRIVER;

	//Upload 화일 기준 경로
	public final static String UPLOAD_PATH;
	//Upload화일 임시 경로
	public final static String UPLOAD_TMP_PATH;
	//html 에디터 이미지업로드 디렉토리
	public final static String UPLOAD_IMG_PATH;
	//한페이지 보여줄 목록 수
	public final static int PAGE_SIZE;
	public final static int PAGE_GROUP_SIZE;
	
	//File 화일 기준 경로
	public final static String FILE_PATH;
	//Root Path
	public final static String ROOT_PATH;
	//File 인코딩
	public final static String ENCODING;

	//Design 수정 jsp 파일 경로
	public final static String DESIGN_PATH;
	
	//Skin 명 - web mobile
	public final static String WEB_SKIN;
	public final static String MOB_SKIN;
	
	//언어 타입
	public final static String SKIN_KR;
	public final static String SKIN_EN;
	public final static String SKIN_CN;

	static{		
		
		new ConfingValidator();

//		try{
//			InetAddress local = InetAddress.getLocalHost();
//			String ip = local.getHostAddress();
			
			// 20171219 김경훈 - local에서는 config.properties 파일의 내용을 읽어오도록 수정
			// System property에서 값을 읽어와서 처리하기 위해서는 Local WAS가 구동될 때 파라미터명=파라미터값 형태로 추가(-DserverMode="local")
			// Servers탭 > 서버를 더블클릭 > Open launch configuration 클릭 > Arguments탭 클릭 >  VM arguments 내용에 -DserverMode="local" 추가
//			String serverMode = System.getProperty("serverMode");

//			InputStream in = configPath.getInputStream();
//			props.load(in);
//			in.close();
//
//			if(logger.isDebugEnabled()){
//				logger.debug("Properties loading success!!");
//			}

//		}catch(Exception ex){
//			logger.error("Properties loading fail!!!", ex);
//		}

		if(logger.isDebugEnabled()){
			logger.debug("Properties Initializing start!!");
		}

//		DEBUG_FLAG = booleanValue("DEBUG_FLAG");
//		TIMESTAMP_FLAG = booleanValue("TIMESTAMP_FLAG");
//		DBPOOL_FLAG = booleanValue("DBPOOL_FLAG");
//		LOG4J_FLAG = booleanValue("LOG4J_FLAG");
//		TOMCAT_FLAG = booleanValue("TOMCAT_FLAG");
		
		MOB_FLAG	= checkBoolean(PropertyUtils.getProperty("MOB_FLAG"));
		
//		JDBC_DRIVER = value("JDBC_DRIVER");
//		DATASOURCE = value("DATASOURCE");
//		JDBC_URL = value("JDBC_URL");
//		DBID = value("DBID");
//		DBPW = value("DBPW");
		
		UPLOAD_PATH = PropertyUtils.getProperty("UPLOAD_PATH");
		UPLOAD_IMG_PATH = PropertyUtils.getProperty("UPLOAD_IMG_PATH");
		UPLOAD_TMP_PATH = PropertyUtils.getProperty("UPLOAD_TMP_PATH");
		
		HOME_URL = PropertyUtils.getProperty("HOME_URL");
		
		LOG_PATH = PropertyUtils.getProperty("LOG_PATH");
		SYS_LOG_PATH = PropertyUtils.getProperty("SYS_LOG_PATH");
		TIMESTAMP_LOG_PATH = PropertyUtils.getProperty("TIMESTAMP_LOG_PATH");
		BATCH_LOG_PATH = PropertyUtils.getProperty("BATCH_LOG_PATH");
		
		PAGE_SIZE = Integer.parseInt(PropertyUtils.getProperty("PAGE_SIZE"));
		
		PAGE_GROUP_SIZE = Integer.parseInt(PropertyUtils.getProperty("PAGE_GROUP_SIZE"));
		
		FILE_PATH = PropertyUtils.getProperty("BATCH_LOG_PATH");
		
		ROOT_PATH = PropertyUtils.getProperty("BATCH_LOG_PATH");
		
		DESIGN_PATH = PropertyUtils.getProperty("BATCH_LOG_PATH");
		
		ENCODING = (PropertyUtils.getProperty("ENCODING").equals(""))? "euc-kr" : value("ENCODING");

		SKIN_EN = PropertyUtils.getProperty("SKIN_EN");
		SKIN_KR = PropertyUtils.getProperty("SKIN_KR");
		SKIN_CN = PropertyUtils.getProperty("SKIN_CN");
		
		WEB_SKIN = (PropertyUtils.getProperty("WEB_SKIN").equals(""))? SKIN_EN : PropertyUtils.getProperty("WEB_SKIN");
		MOB_SKIN = (PropertyUtils.getProperty("MOB_SKIN").equals(""))? SKIN_EN : PropertyUtils.getProperty("MOB_SKIN");
		
		if(logger.isDebugEnabled()){
			logger.debug("Properties Initializing end!!");
		}
	}


	public static boolean checkBoolean(String v){
		return v.equals("true") ? true : false;
	}

	/**
	 * URL 체크해서 언어타입 리턴
	 * @param req
	 * @return
	 */
	public static String getSkin(HttpServletRequest req) {
		return getSkin( String.valueOf( req.getRequestURL()) );
	}

	/**
	 * URL 체크해서 언어타입 리턴
	 * @param url
	 * @return
	 */
	public static String getSkin(String url) {
		String skinType = "SKIN_EN"; //default
		
		if( url.indexOf("kr")  != -1 || url.indexOf("localhost")  != -1 || url.indexOf("127.0.0.1")  != -1) {
			skinType = "SKIN_KR";
		}else if( url.indexOf("cn")  != -1) {
			skinType = "SKIN_CN";
		}
		return value(skinType);
	}
	
	public static String value(String propername){
		try{
			String value = (StringUtil.nvl(PropertyUtils.getProperty(propername), "")).trim();
			if(value==null)
				return "";
			else
				return value;
		}catch(Exception e){
			logger.error("Properties loading Fail key="+propername, e);
			return "";
		}
	}
	
	public static int intValue(String propername){
		try{
		int value = Integer.parseInt(value(propername));
		return value;
		}catch(Exception e){
			return 0;
		}
	}
	
	public static boolean booleanValue(String propername){
		return "true".equals(value(propername));
	}
			
	// AS-IS : 시스템출력 디버깅
	// TO-BE : logger 디버깅
	public static void consoleOutPrint(Object obj, int line, String msg){
//		if(DEBUG_FLAG){
//			System.out.println(obj.getClass().getName()+":Line ID("+line+") "+msg);
//		}
		if(logger.isDebugEnabled()){
			logger.debug("{} : Line ID({}) {}", obj.getClass().getName(), line, msg);
		}
			
	}

	// AS-IS : 시스템출력 디버깅
	// TO-BE : logger 디버깅
	public static void consoleOutPrint(String objName, int line, String msg){
//		if(DEBUG_FLAG){
//			System.out.println(objName+":Line ID("+line+") "+msg);			
//		}
		if(logger.isDebugEnabled()){
			logger.debug("{} : Line ID({}) {}", objName, line, msg);
		}
	}
}