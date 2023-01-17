/*
 * Created on 2004. 10. 6.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wepinit.wepinit_shop.xcube.util;

import com.wepinit.wepinit_shop.common.ConfigClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author cozyhill
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DateUtil {

	  	
	/**
	 * 입력된 날자가 오늘인지 여부 판다.
	 * 
	 * @param String
	 *            crtdate 기본 날짜 (yyyyMMddhhmiss)타입
	 * 
	 * @return boolean 오늘 등록여부
	 */
	static public boolean isToday(String crtdate) {
		String crtdate_yyyyMMdd = crtdate.substring(0, 8);

		TimeZone tz = TimeZone.getDefault();
		// tz.setRawOffset((60 * 60 * 1000) * 9);
		// TimeZone.setDefault(tz);
		Calendar cal = Calendar.getInstance(tz);
		Date date = cal.getTime();
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");

		String now = formater.format(date);

		if (now.equals(crtdate_yyyyMMdd)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 시스템 날자를 yyyyMMdd형식으로 읽어온다.
	 * 
	 * @return String yyyyMMdd형식의 문자열
	 */
	/*
	static public String getDate() {
		TimeZone tz = TimeZone.getDefault();
		// tz.setRawOffset((60 * 60 * 1000) * 9);
		// TimeZone.setDefault(tz);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance(tz);
		java.util.Date currentTime_1 = cal.getTime();
		String dateString = formatter.format(currentTime_1);

		return dateString;
	}
	*/
	
	/**
	 * 시스템 시간을 HHmmss 형식으로 읽어온다.
	 * 
	 * @return String HHmmss 형식 (ex. 135010 )
	 */
	/*
	static public String getTime() {
		TimeZone tz = TimeZone.getDefault();
		// tz.setRawOffset((60 * 60 * 1000) * 9);
		// TimeZone.setDefault(tz);

		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		Calendar cal = Calendar.getInstance(tz);
		java.util.Date currentTime_1 = cal.getTime();
		String dateString = formatter.format(currentTime_1);

		return dateString;
	}
	*/

	/**
	 * 시스템 날자를 yyyyMMdd형식으로 읽어온다.
	 * 
	 * @return String yyyyMMdd형식의 문자열
	 */
	static public String getDateYesterday() {
		TimeZone tz = TimeZone.getDefault();
		// tz.setRawOffset((60 * 60 * 1000) * 9);
		// TimeZone.setDefault(tz);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance(tz);
		Date currentTime_1 = cal.getTime();
		String dateString = formatter.format(currentTime_1);

		return dateString;
	}

	/**
	 * 전달된 String ('yyyymmddhhmmss')을 날자 표시형식에 맞게 변환
	 * 
	 * @param String
	 *            str 변환시킬 문자열
	 * @param String
	 *            format 변환형식
	 * 
	 * @return String 변환된 문자열
	 */
	static public String dateFilter(String src, String format) {
		if (src == null || (format.length() < 2)) {
			return "";
		}

		String FmtStr, stryyyy, strmm, strdd, strhh, strmin, strss;
		int istrmm = 0;

		stryyyy = src.substring(0, 4);

		strmm = src.substring(4, 6);
		istrmm = Integer.parseInt(strmm);

		strdd = src.substring(6, 8);
		strhh = src.substring(8, 10);
		strmin = src.substring(10, 12);
		strss = src.substring(12, 14);

		char c = format.charAt(1);

		switch (c) {
		// YYYY/MM/DD 형식
		case '0':
			FmtStr = stryyyy + "/" + strmm + "/" + strdd;
			break;

		// YYYY/MM/DD HH:mm:SS 형식
		case '1':
			FmtStr = stryyyy + "/" + strmm + "/" + strdd + " " + strhh + ":"
					+ strmin + ":" + strss;
			break;

		// YYYY/MM/DD HH:mm 형식
		case '2':
			FmtStr = stryyyy + "/" + strmm + "/" + strdd + " " + strhh + ":"
					+ strmin;
			break;

		// YY/MM/DD 형식
		case '3':
			FmtStr = stryyyy.substring(2, 4) + "/" + strmm + "/" + strdd;
			break;

		// YY/MM/DD HH:mm 형식
		case '4':
			FmtStr = stryyyy.substring(2, 4) + "/" + strmm + "/" + strdd + " "
					+ strhh + ":" + strmin;
			break;

		// YYYY-MM-DD 형식
		case '5':
			FmtStr = stryyyy + "-" + strmm + "-" + strdd;
			break;

		// YYYY-MM-DD HH:mm:SS 형식
		case '6':
			FmtStr = stryyyy + "-" + strmm + "-" + strdd + " " + strhh + ":"
					+ strmin + ":" + strss;
			break;

		// YYYY-MM-DD HH:mm 형식
		case '7':
			FmtStr = stryyyy + "-" + strmm + "-" + strdd + " " + strhh + ":"
					+ strmin;
			break;

		// YYYY.MM.DD 형식
		case '8':
			FmtStr = stryyyy + "." + strmm + "." + strdd;
			break;

		// YYYY.MM.DD HH:mm:SS 형식
		case '9':
			FmtStr = stryyyy + "." + strmm + "." + strdd + " " + strhh + ":"
					+ strmin + ":" + strss;
			break;

		// YYYY.MM.DD HH:mm 형식
		case 'A':
			FmtStr = stryyyy + "." + strmm + "." + strdd + " " + strhh + ":"
					+ strmin;
			break;

		// YYYY년 MM월 DD일 형식
		case 'B':
			FmtStr = stryyyy + "년" + strmm + "월" + strdd + "일";
			break;

		// YYYY년 MM월 DD일 HH시 mm분 형식
		case 'C':
			FmtStr = stryyyy + "년" + strmm + "월" + strdd + "일" + " " + strhh
					+ "시" + strmin + "분";
			break;

		// MM/DD HH:mm 형식
		case 'E':
			FmtStr = istrmm + "/" + strdd + " " + strhh + ":" + strmin;
			break;

		// MM월 DD일 HH:mm 형식
		case 'F':
			FmtStr = istrmm + "월" + strdd + "일 " + strhh + ":" + strmin;
			break;

		// MM-DD 형식
		case 'G':
			FmtStr = strmm + "-" + strdd;
			break;

		default:
			FmtStr = stryyyy + "/" + strmm + "/" + strdd;
		}

		return FmtStr;
	}

	static public String formatDate(Date dt, String frmt) {
		if (dt == null) {
			return "<<정보없음>>";
		}

		SimpleDateFormat df = new SimpleDateFormat(frmt);

		return df.format(dt);
	}
	
	// yyyyMMdd형식의 날짜를 원하는 날짜폼으로 만들어 준다
	static public String formatDate(String str, String frmt)
			throws ParseException {
		if (str == null || str.length() == 0) {
			return "<<정보없음>>";
		}
		if (str.length() == 8) {
			str += "000000";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = sdf.parse(str);
		SimpleDateFormat sdf2 = new SimpleDateFormat(frmt);
		return sdf2.format(date);
	}

	// yyyymmdd을 출력시 공통으로 사용할 yyyy/mm/dd로 변환
	public static String commonFormatDate(String str) throws ParseException {
		return commonFormatDate(str, 1);
	}

	// yyyymmdd을 출력시 공통으로 사용할 yyyy/mm/dd로 변환
	public static String commonFormatDate(String str, int iType)
			throws ParseException {
		String strFormat;
		switch (iType) {
		case 0:
			strFormat = "yyyy-MM-dd";
			break;
		case 1:
			strFormat = "yyyy-MM-dd (E)";
			break;
		default:
			strFormat = "yyyy-MM-dd";
		}
		return formatDate(str, strFormat);
	}

	// 공통으로 사용할 날짜 포맷 yyyy-mm-dd로 변환
	static public String commonFormatDate(Date dt) {
		return commonFormatDate(dt, 0);
	}

	// 공통으로 사용할 날짜 포맷 yyyy-mm-dd로 변환
	static public String commonFormatDate(Date dt, int iType) {
		String strFormat;
		switch (iType) {
		case 0:
			strFormat = "yyyy-MM-dd";
			break;
		case 1:
			strFormat = "yyyy-MM-dd (E)";
			break;
		case 2:
			strFormat = "yyyy-MM-dd HH:mm:ss";
			break;
		case 3:
			strFormat = "yyyy-MM-dd hh:mm";
			break;
		default:
			strFormat = "yyyy-MM-dd";
		}
		return formatDate(dt, strFormat);
	}

	static public String getFormatToday(String frmt) {
		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat(frmt);

		return df.format(today);
	}

	/***************************************************************************
	 * Argument 중의 "p_year", "p_month", "p_day" 프로퍼터를 이용하여 "yyyymmdd" 형식의 문자열을
	 * 작성
	 * 
	 * @since 2000/11/22
	 * @return "yyyymmdd" 형식의 문자열?
	 **************************************************************************/
//	static public String getYyyyMmDdStr(RequestSet Argument) {
//		return getYyyyMmDdStr(Argument, "");
//	}

	/***************************************************************************
	 * Argument 중의 "p_year[postfix]", "p_month[postfix]", "p_day[postfix]" 프로퍼터를
	 * 이용하여 "yyyymmdd" 형식의 문자열을 작성
	 * 
	 * @since 2000/11/22
	 * @return "yyyymmdd" 형식의 문자열?
	 **************************************************************************/
//	static public String getYyyyMmDdStr(RequestSet Argument, String postfix) {
//		int yyyy = Argument.getPropertyAsInt("p_year" + postfix, 2000);
//		int mm = Argument.getPropertyAsInt("p_month" + postfix, 0) + 1;
//		int dd = Argument.getPropertyAsInt("p_day" + postfix, 1);
//		String yyyy_str, mm_str, dd_str;
//		yyyy_str = "" + yyyy;
//		mm_str = "" + mm;
//		dd_str = "" + dd;
//		while (yyyy_str.length() < 4)
//			yyyy_str = "0" + yyyy_str;
//		while (mm_str.length() < 2)
//			mm_str = "0" + mm_str;
//		while (dd_str.length() < 2)
//			dd_str = "0" + dd_str;
//		return yyyy_str + mm_str + dd_str;
//	}

	/***************************************************************************
	 * yyyy, mm, dd 값을 이용해 "yyyymmdd" 형식의 문자열을 작성
	 * 
	 * @since 2000/11/22
	 * @return "yyyymmdd" 형식의 문자열
	 **************************************************************************/
	static public String getYyyyMmDdStr(int yyyy, int mm, int dd) {
		mm++;
		String yyyy_str, mm_str, dd_str;
		yyyy_str = "" + yyyy;
		mm_str = "" + mm;
		dd_str = "" + dd;
		while (yyyy_str.length() < 4)
			yyyy_str = "0" + yyyy_str;
		while (mm_str.length() < 2)
			mm_str = "0" + mm_str;
		while (dd_str.length() < 2)
			dd_str = "0" + dd_str;
		return yyyy_str + mm_str + dd_str;
	}

	// 다음주 월요일의 일자를 구함
	public static Date getFirstDateOfWeek() {
		Calendar cal = Calendar.getInstance();
		int intAddDay = 9 - cal.get(Calendar.DAY_OF_WEEK);
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			intAddDay = 1;
		}
		cal.add(Calendar.DATE, intAddDay);
		return cal.getTime();
	}

	// 다음주 월요일의 일자를 구함
	public static String getStringFirstDateOfWeek() {
		return DateUtil.formatDate(getFirstDateOfWeek(), "yyyyMMdd");
	}

	// 다음주 1주간의 시작일과 종료일
	public static String[] getPeriodOfNextWeek() {
		String[] astrDate = new String[2];
		Calendar cal = Calendar.getInstance();
		int intAddDay = 9 - cal.get(Calendar.DAY_OF_WEEK);
		if (cal.get(Calendar.DAY_OF_WEEK) == 1)
			intAddDay = 1;
		cal.add(Calendar.DATE, intAddDay);
		astrDate[0] = DateUtil.formatDate(cal.getTime(), "yyyyMMdd");
		cal.add(Calendar.DATE, 6);
		astrDate[1] = DateUtil.formatDate(cal.getTime(), "yyyyMMdd");
		return astrDate;
	}

	// 해당일이 속한 다음주 1주간의 시작일과 종료일
	public static String[] getPeriodOfWeek(String strDate) {
		String[] astrDate = new String[2];
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(strDate.substring(0, 4)), Integer
				.parseInt(strDate.substring(4, 6)) - 1, Integer
				.parseInt(strDate.substring(6)), 0, 0, 0);
		int intAddDay = 2 - cal.get(Calendar.DAY_OF_WEEK);
		if (cal.get(Calendar.DAY_OF_WEEK) == 1)
			intAddDay = -6;
		cal.add(Calendar.DATE, intAddDay);
		astrDate[0] = DateUtil.formatDate(cal.getTime(), "yyyyMMdd");
		cal.add(Calendar.DATE, 6);
		astrDate[1] = DateUtil.formatDate(cal.getTime(), "yyyyMMdd");
		return astrDate;
	}

	/**
	 * 일주일 이전의 날짜(YYYYMMDD)
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static String addDaysBeforeOneWeek(String s) throws ParseException {
		return addDays(s, -7);
	}

	/**
	 * 15일 이전의 날짜(YYYYMMDD)
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static String addDaysBeforeFifthDays(String s) throws ParseException {
		return addDays(s, -15);
	}

	/**
	 * 한달 이전의 날짜(YYYYMMDD)
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static String addDaysBeforeOneMonth(String s) throws ParseException {
		return addDays(s, -30);
	}

	/**
	 * 두달 이전의 날짜(YYYYMMDD)
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static String addDaysBeforeTwoMonth(String s) throws ParseException {
		return addDays(s, -30*2);
	}

	/**
	 * return add day to date strings
	 * 
	 * @param String
	 *            date string
	 * @param int
	 *            더할 일수
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 더하기 형식이 잘못 되었거나 존재하지 않는 날짜:
	 *         ParseException 발생
	 */
	public static String addDays(String s, int day) throws ParseException {
		return addDays(s, day, "yyyyMMdd");
	}

	/**
	 * return add day to date strings with user defined format.
	 * 
	 * @param String
	 *            date string
	 * @param int
	 *            더할 일수
	 * @param format
	 *            string representation of the date format. For example,
	 *            "yyyy/MM/dd".
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 더하기 형식이 잘못 되었거나 존재하지 않는 날짜:
	 *         ParseException 발생
	 */
	public static String addDays(String s, int day, String format)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		Date date = check(s, format);

		date.setTime(date.getTime() + ((long) day * 1000 * 60 * 60 * 24));
		return formatter.format(date);
	}

	/**
	 * check date string validation with the default format "yyyyMMdd".
	 * 
	 * @param s
	 *            date string you want to check with default format "yyyyMMdd".
	 * @return date Date
	 */
	public static Date check(String s) throws ParseException {
		return check(s, "yyyyMMdd");
	}

	/**
	 * check date string validation with an user defined format.
	 * 
	 * @param s
	 *            date string you want to check.
	 * @param format
	 *            string representation of the date format. For example,
	 *            "yyyy/MM/dd".
	 * @return date Date
	 */
	public static Date check(String s, String format) throws ParseException {
		if (s == null)
			throw new ParseException("date string to check is null", 0);
		if (format == null)
			throw new ParseException("format string to check date is null", 0);

		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		Date date = null;
		try {
			date = formatter.parse(s);
		} catch (ParseException e) {
			/*
			 * throw new ParseException( e.getMessage() + " with format \"" +
			 * format + "\"", e.getErrorOffset() );
			 */
			throw new ParseException(" wrong date:\"" + s + "\" with format \""
					+ format + "\"", 0);
		}

		if (!formatter.format(date).equals(s))
			throw new ParseException("Out of bound date:\"" + s
					+ "\" with format \"" + format + "\"", 0);
		return date;
	}

	public static boolean checkNewDate(Date date) {
		if (date == null) {
			return false;
		}
		Date today = new Date();
		return today.getTime() - date.getTime() < (1000 * 60 * 60 * 24);
	}

	
	
	  
	  /** 일요일 */
	  public final static int SUN = 1;
	  /** 월요일 */
	  public final static int MON = 2;
	  /** 화요일 */
	  public final static int TUE = 3;
	  /** 수요일 */
	  public final static int WED = 4;
	  /** 목요일 */
	  public final static int THU = 5;
	  /** 금요일 */
	  public final static int FRI = 6;
	  /** 토요일 */
	  public final static int SAT = 7;

	  public final static int BIG = 2;
	  public final static int EQUAL = 1;
	  public final static int SMALL = 0;

	  /** Default 날짜 포맷  : yyyy.MM.dd */
	  public final static String DFT_FORMAT = "yyyy-MM-dd";

	  /** 입력용 Default 날짜 포맷  : yyyyMMdd */
	  public final static String DFT_INPUT_FORMAT = "yyyyMMdd";

	  /** Default 시간 포맷  : HH:mm:ss */
	  public final static String DFT_TIME_FORMAT = "HH:mm:ss";


	  /**
	   * Default Format 으로 현재 날짜를 반환
	   *
	   * @return   yyyy.MM.dd
	   */
	  public static String getDate() {
	    return getDate(DFT_FORMAT);
	  }

	  /**
	   * Default 입력용 Format 으로 현재 날짜를 반환
	   *
	   * @return   yyyyMMdd
	   */
	  public static String getDateString() {
	    return getDate(DFT_INPUT_FORMAT);
	  }

	  /**
	   * Default Format 으로 현재 시간을 반환
	   *
	   * @return   HH:mm:ss
	   */
	  public static String getTime() {
	    return getDate(DFT_TIME_FORMAT);
	  }

	  /**
	   * 입력한 포맷으로 현재일자/시간을 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDate(String format) {
	    try {

	      Date now = new Date();
	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(now);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	   * 현재일로부터 지정된 날수 이전/이후의 일자/시간을 Default Fromat 으로 반환
	   *
	   * @param   dayCount   현재일로 부터의 지정된 날수 (+/-)
	   * @return  Default 표현형식에 따른 일자/시간
	   */
	  public static String getDateFrom(int dayCount) {
	    return getDateFrom(DFT_INPUT_FORMAT, dayCount);
	  }

	  /**
	   * 현재일로부터 지정된 날수 이전/이후의 일자/시간을 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   dayCount   현재일로 부터의 지정된 날수 (+/-)
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDateFrom(String format, int dayCount) {
	    try {

	      Calendar cdate = Calendar.getInstance();
	      cdate.add(Calendar.DATE, dayCount);
	      Date date = cdate.getTime();

	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	   * 현재일로부터 지정된 날수 이전/이후의 일자/시간을  Default Fromat 으로 반환
	   *
	   * @param   dayFormat   현재일로 부터의 지정된 날수 , m: 월, y: 년, d: day, w:week (+/-)
	   * @return  Default  표현형식에 따른 일자/시간
	   */
	  public static String getDateFrom(String dayFormat) {
	    return getDateFrom(DFT_INPUT_FORMAT, dayFormat);
	  }

	  /**
	   * 현재일로부터 지정된 날수 이전/이후의 일자/시간을 지정한 Format 으로 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   dayFormat   현재일로 부터의 지정된 날수 , m: 월, y: 년, d: day, w:week (+/-)
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDateFrom(String format, String dayFormat) {
	    try {
	      int dayCount = Integer.parseInt(dayFormat.substring(0,dayFormat.length()-1));
	      char ymd = dayFormat.charAt(dayFormat.length()-1);

	      Calendar cdate = Calendar.getInstance();
	      switch(ymd){
	        case 'y' : cdate.add(Calendar.YEAR, dayCount);break;
	        case 'm' : cdate.add(Calendar.MONTH, dayCount);break;
	        case 'w' : cdate.add(Calendar.DATE, dayCount*7);break;
	        case 'd' : cdate.add(Calendar.DATE, dayCount);break;
	        default : break;
	      }
	      if(dayCount<0)  {
	        cdate.add(Calendar.DATE, 1);
	      }
	      else if(dayCount>0)  {
	        cdate.add(Calendar.DATE, -1);
	      }

	      Date date = cdate.getTime();

	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return ex.toString();
	      //return "00000000";
	    }
	  }

	  /**
	   * 지정된 일자를 지정한 표현형식으로 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   data   지정일자 (yyyyMMdd, yyyyMMddHHmmss)
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDateFormat(String format, String data) {
	    try {

	      int year = 0;
	      int month = 0;
	      int day = 0;
	      int hour = 0;
	      int minute = 0;
	      int second = 0;

	      if(data == null || data.length() < 8) return "00000000";
	      Calendar cdate = null;

	      try {
	        year = Integer.parseInt(data.substring(0, 4));
	        month = Integer.parseInt(data.substring(4, 6)) - 1;
	        day = Integer.parseInt(data.substring(6, 8));
	      } catch(NumberFormatException nfe) {
	        return "00000000";
	      }
	      cdate = new GregorianCalendar(year, month, day);
	      if(data.length() > 8){
	        try {
	          hour = Integer.parseInt(data.substring(8, 10));
	          minute = Integer.parseInt(data.substring(10, 12));
	          second = Integer.parseInt(data.substring(12));
	        } catch(NumberFormatException nfe) {
	          return "00000000";
	        }
	        cdate = new GregorianCalendar(year, month, day, hour, minute, second);
	      }

	      Date date = cdate.getTime();

	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	   * 특정일자의 지정된 날수 이전/이후의 일자/시간을  Default Fromat 으로 반환
	   *
	   * @param   data   지정일자 (yyyyMMdd)
	   * @param   dayCount   현재일로 부터의 지정된 날수 (+/-)
	   * @return  Default 표현형식에 따른 일자/시간
	   */
	  public static String getDateFormatFrom(String data, int dayCount) {
	    return getDateFormatFrom(DFT_FORMAT, data, dayCount);
	  }
	  /**
	   * 특정일자의 지정된 날수 이전/이후의 일자/시간을 표현형식대로 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   data   지정일자 (yyyyMMdd)
	   * @param   dayCount   현재일로 부터의 지정된 날수 (+/-)
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDateFormatFrom(String format, String data, int dayCount) {

	    try {

	      int year = 0;
	      int month = 0;
	      int day = 0;

	      if(data == null || data.length() < 8) return "00000000";

	      try {
	        year = Integer.parseInt(data.substring(0, 4));
	        month = Integer.parseInt(data.substring(4, 6)) - 1;
	        day = Integer.parseInt(data.substring(6, 8));
	      } catch(NumberFormatException nfe) {
	        return "00000000";
	      }

	      Calendar cdate = new GregorianCalendar(year, month, day);
	      cdate.add(Calendar.DATE, dayCount);
	      Date date = cdate.getTime();

	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	   * 특정일자의 지정된 날수 이전/이후의 일자/시간을 Default Fromat 으로 반환
	   *
	   * @param   data   지정일자 (yyyyMMdd)
	   * @param   monCount   현재일로 부터의 지정된 월수 (+/-)
	   * @return  Default 표현형식에 따른 일자/시간
	   */
	  public static String getDateFormatFromMonth(String data, int monCount) {
	    return getDateFormatFromMonth(DFT_INPUT_FORMAT, data, monCount);
	  }
	  /**
	   * 특정일자의 지정된 날수 이전/이후의 일자/시간을 표현형식대로 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   data   지정일자 (yyyyMMdd)
	   * @param   monCount   현재일로 부터의 지정된 월수 (+/-)
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDateFormatFromMonth(String format, String data, int monCount) {

	    try {

	      int year  = 0;
	      int month = 0;
	      int day   = 0;

	      if(data == null || data.length() < 8) return "00000000";

	      try {
	        year = Integer.parseInt(data.substring(0, 4));
	        month = Integer.parseInt(data.substring(4, 6)) - 1;
	        day = Integer.parseInt(data.substring(6, 8));
	      } catch(NumberFormatException nfe) {
	        return "00000000";
	      }

	      Calendar cdate = new GregorianCalendar(year, month, day);
	      cdate.add(Calendar.MONTH, monCount);
	      Date date = cdate.getTime();

	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	   * 특정일자의 지정된 날수 이전/이후의 일자/시간을 Default Fromat 으로 반환
	   *
	   * @param   data   지정일자 (yyyyMMdd)
	   * @param   yearCount   현재일로 부터의 지정된 월수 (+/-)
	   * @return  Default 표현형식에 따른 일자/시간
	   */
	  public static String getDateFormatFromYear(String data, int yearCount) {
	    return getDateFormatFromYear(DFT_INPUT_FORMAT, data, yearCount);
	  }

	  /**
	   * 특정일자의 지정된 날수 이전/이후의 일자/시간을 표현형식대로 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   data   지정일자 (yyyyMMdd)
	   * @param   yearCount   현재일로 부터의 지정된 월수 (+/-)
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDateFormatFromYear(String format, String data, int yearCount) {

	    try {

	      int year = 0;
	      int month = 0;
	      int day = 0;

	      if(data == null || data.length() < 8) return "00000000";

	      try {
	        year = Integer.parseInt(data.substring(0, 4));
	        month = Integer.parseInt(data.substring(4, 6)) - 1;
	        day = Integer.parseInt(data.substring(6, 8));
	      } catch(NumberFormatException nfe) {
	        return "00000000";
	      }

	      Calendar cdate = new GregorianCalendar(year, month, day);
	      cdate.add(Calendar.YEAR, yearCount);
	      Date date = cdate.getTime();

	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	   * Date type에 지정된 일자/시간을 Default Fromat 으로 반환
	   *
	   * @param   date Date  지정일자
	   * @return   Default 표현형식에 따른 일자/시간
	   */
	  public static String getDate(Date date) {
	    return getDate(DFT_FORMAT, date);
	  }

	  /**
	   * Date type에 지정된 일자/시간을 표현형식으로 반환
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   date   지정일자
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDate(String format, Date date) {
	    try {

	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	   * Allocates a <code>Date</code> object and initializes it so that
	   * it represents the time at which it was allocated, measured to the
	   * nearest millisecond. And then return date/time with specified format
	   *  Default Fromat 으로 return
	   *
	   * @see     System#currentTimeMillis()
	   *
	   * @param   time long  the milliseconds since January 1, 1970, 00:00:00 GMT.
	   * @return  Default 표현형식에 따른 일자/시간
	   */
	  public static String getDate(long time) {
	    return getDate(DFT_FORMAT, time);
	  }

	  /**
	   * Allocates a <code>Date</code> object and initializes it so that
	   * it represents the time at which it was allocated, measured to the
	   * nearest millisecond. And then return date/time with specified format
	   *
	   * @see     System#currentTimeMillis()
	   *
	   * @param   format   표현하고자 하는 형식을 지정
	   * @param   time   the milliseconds since January 1, 1970, 00:00:00 GMT.
	   * @return   표현형식에 따른 일자/시간
	   */
	  public static String getDate(String format, long time) {
	    try {

	      Date date = new Date(time);
	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(date);

	    } catch(Exception ex) {
	      return "00000000";
	    }
	  }

	  /**
	     * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
	     * represented by today
	     *
	     * @return  the number of milliseconds since January 1, 1970, 00:00:00 GMT
	     *          represented by today
	     */
	  public static long getLongTime() {
	    try {

	      return new Date().getTime();

	    } catch(Exception ex) {
	      return 0;
	    }
	  }

	  public static int getIntTimeOver(Date date) {
		  Calendar cnow = Calendar.getInstance();
		  Calendar cend = Calendar.getInstance();
		  
		  cnow.setTime(new Date());
		  cend.setTime(date);
		  /*
		  LogUtil log = new LogUtil();
		  
		  log.debug("$$$$$$$$ : " + cnow.getTimeInMillis() );
		  log.debug("@@@@@@@ : " + cend.getTimeInMillis() );

		  log.debug("%%%%%%% : " + (cnow.getTimeInMillis() - cend.getTimeInMillis()) );
		  */
		  try {
	
		      //return (new Date().getSeconds() - date.getSeconds())/60/60;
			  return (int)(cnow.getTimeInMillis() - cend.getTimeInMillis())/(1000*60*60);
	
		    } catch(Exception ex) {
		      return 0;
		    }
	  }	  
	  
	  /**
	   * 지정된 일자의 요일을 반환
	   *
	   * @param   data   지정일자 (yyyyMMdd)
	   * @return   요일정보
	   */
	  public static int getDayOfWeek(String data) {
	    try {

	      int year = 0;
	      int month = 0;
	      int day = 0;

	      if(data == null || data.length() < 8) return -1;

	      try {
	        year = Integer.parseInt(data.substring(0, 4));
	        month = Integer.parseInt(data.substring(4, 6)) - 1;
	        day = Integer.parseInt(data.substring(6, 8));
	      } catch(NumberFormatException nfe) {
	        return -1;
	      }

	      Calendar cdate = new GregorianCalendar(year, month, day);
	      return cdate.get(Calendar.DAY_OF_WEEK);

	    } catch(Exception ex) {
	      return -1;
	    }
	  }
	  
	  /**
	   * 지정된 일자의 요일을 반환
	   *
	   * @param   data   지정일자 (yyyyMMdd)
	   * @return   요일정보
	   */
	  public static String getDayOfWeek(String data, String lang) {
		  String tmp_lang = "ko";
		  String return_str = "";
		  
		  if("".equals(data)){
			  return return_str;
		  }
		  
		  // 한글 Type 인 경우
		  if(tmp_lang.equals(lang)){
			  switch (getDayOfWeek(data)) {
				case 1:
					return_str = "일";
					break;
				case 2:
					return_str = "월";
					break;
				case 3:
					return_str = "화";
					break;
				case 4:
					return_str = "수";
					break;
				case 5:
					return_str = "목";
					break;
				case 6:
					return_str = "금";
					break;
				case 7:
					return_str = "토";
					break;
			  }			  
		  }else{
			  switch (getDayOfWeek(data)) {
				case 1:
					return_str = "SUN";
					break;
				case 2:
					return_str = "MON";
					break;
				case 3:
					return_str = "TUE";
					break;
				case 4:
					return_str = "WED";
					break;
				case 5:
					return_str = "THU";
					break;
				case 6:
					return_str = "FRI";
					break;
				case 7:
					return_str = "SAT";
					break;
			  }	
		  }
		  
		  return return_str;
	    
	  }

	  /**
	   * 현재일자의 요일을 반환
	   *
	   * @return   요일정보
	   */
	  public static int getDayOfWeek() {
	    try {

	      Calendar cdate = Calendar.getInstance();
	      return cdate.get(Calendar.DAY_OF_WEEK);

	    } catch(Exception ex) {
	      return -1;
	    }
	  }

	  /**
	   * 현재일과 지정된 날짜의 선후를 비교
	   *
	   * @param   cDate   비교하려는 지정일자 (yyyyMMdd)
	   * @return   선후결과값
	   */
	  public static int compare(String cDate) {
	    try {

	      int cdt = Integer.parseInt(cDate);
	      int odt = Integer.parseInt(getDate("yyyyMMdd"));

	      if(cdt > odt) return BIG;
	      else if(cdt == odt) return EQUAL;
	      else if(cdt < odt) return SMALL;
	      else return -1;

	    } catch(Exception ex) {
	      return -1;
	    }
	  }

	  /**
	   * 특정일과 지정된 날짜의 선후를 비교
	   *
	   * @param   cDate   비교하려는 지정일자 (yyyyMMdd)
	   * @param   oDate   특정일 (yyyyMMdd)
	   * @return   선후결과값
	   */
	  public static int compare(String cDate, String oDate) {
	    try {

	      int cdt = Integer.parseInt(cDate);
	      int odt = Integer.parseInt(oDate);

	      if(cdt > odt) return BIG;
	      else if(cdt == odt) return EQUAL;
	      else if(cdt < odt) return SMALL;
	      else return -1;

	    } catch(Exception ex) {
	      return -1;
	    }
	  }

	    //sdate - 기간시작
	  //edate - 기간종료
	  // hdayGbn - SUN:일요일, MON:월요일, TUE:화요일, WED:수요일, THU:목요일, FRI:금요일, SAT:토요일
	  public static Vector getDayArray(String sdate, String edate, int hdayGbn) {

	    Vector rslt = new Vector();


	    int sday = 0;

	    while(true) {

	      sday = DateUtil.getDayOfWeek(sdate);

	      // (원하는 요일)과 (기간 중 특정일)의 요일이 같으면, 그 일자가 최초의 원하는 요일이 된다.
	      if(hdayGbn == sday) {
	        rslt.addElement(sdate);
	        break;
	      }

	      // 다음날...
	      sdate = DateUtil.getDateFormatFrom("yyyyMMdd", sdate, +1);

	      // 다음날이 기간의 마지막날보다 크면 빠져나간다.
	      if(2 == DateUtil.compare(sdate, edate)) {
	        //break;
	        return rslt;  // 원하는 요일이 하나도 없음.
	      }
	    }

	    while(true) {

	      // 최초일 다음에 오는 원하는 요일을 구한다.
	      sdate = DateUtil.getDateFormatFrom("yyyyMMdd", sdate, +7);

	      // 다음의 원하는 요일이 기간의 마지막날보다 크면 빠져나간다.
	      if(BIG == DateUtil.compare(sdate, edate)) {
	        //break;
	        return rslt;  // 구할만큼 다 구했으니...나가자...
	      }

	      rslt.addElement(sdate); // 아직 기간내에 있으니, 요일을 등록하고 계속 돌자...

	    }

	    //return rslt;

	  }

	  /**
	   * return days between two date strings with user defined format.
	   * @param from String from date string
	   * @param to String to date string
	   * @return 일수 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 일자 리턴
	   *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
	   */
	    public static int daysBetween(String from, String to, String format) throws ParseException {
	        Date d1 = check(from, format);
	        Date d2 = check(to, format);

	        long duration = d2.getTime() - d1.getTime();

	        return (int)( duration/(1000 * 60 * 60 * 24) );
	        // seconds in 1 day
	    }

	  /**
	   * 해당월의 말일을 return 함,
	   * @param yyyymm String yyyyMM 형식의 년,월 문자열
	   * @return 말일의 날짜 , String
	   */
	    public static String getLastDay(String yyyymm) {
	      if(yyyymm.length() != 6){
	    	  ConfigClass.consoleOutPrint(DateUtil.class, 1136 , "잘못된 날짜입니다.  yyyymm=[" + yyyymm + "]");
	        return "잘못된 날짜입니다.";
	      } else {
	        return getLastDay(yyyymm.substring(0,4), yyyymm.substring(4));
	      }
	    }

	  /**
	   * 해당월의 말일을 return 함,
	   * @param yyyy String yyyy 형식의 년
	   * @param mm String 월
	   * @return 말일의 날짜 , String
	   */
	    public static String getLastDay(String yyyy, String mm) {
	      try{
	        GregorianCalendar gcal = new GregorianCalendar(Integer.parseInt(yyyy), Integer.parseInt(mm)-1, 1);
	        int lday = gcal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        if(lday < 10){
	          return "0" + lday;
	        } else {
	          return "" + lday;
	        }
	      } catch (NumberFormatException ne){
	    	  ConfigClass.consoleOutPrint(DateUtil.class, 1133 ,"잘못된 날짜입니다.  yyyy=[" + yyyy + "], mm=[" + mm + "]");
	        return "잘못된 날짜입니다.";
	      }

	    }

	    
	    private final static String preString = "h";

	    
	    /**
	     * selected 문자열을 리턴하는 Method<br>
	     * str1 과 str2 가 동일하면 rtn 을 return, 아니면 "" 를 return
	     * checked, selected 등을 표현할 때 사용
	     * @param str1 String 비교할 값1
	     * @param str2 String 비교할 값2
	     * @param rtn  String return 할 값 , default:selected, checked
	     * @return String객체 selected/checked
	     */
	     public static String getSelected(String str1, String str2, String rtn){
	       if(str1.equals(str2))
	         return rtn;
	       else
	         return "";
	     }

	     /**
	     * selected 문자열을 리턴하는 Method<br>
	     * str1 과 str2 가 동일하면 " selected" 을 return, 아니면 "" 를 return
	     * @param str1 String 비교할 값1
	     * @param str2 String 비교할 값2
	     * @return String객체 selected/checked
	     */
	     public static String getSelected(String str1, String str2){
	       return getSelected(str1, str2, " selected");
	     }

	     /**
	     * selected 문자열을 리턴하는 Method<br>
	     * val1 과 val2 가 동일하면 " selected" 을 return, 아니면 "" 를 return
	     * @param val1 int 비교할 값1
	     * @param val2 int 비교할 값2
	     * @return String객체 selected/checked
	     */
	     public static String getSelected(int val1, int val2){
	       if(val1 == val2)
	         return " selected";
	       else
	         return "";
	     }

	     /**
	     * 일을 표현하는 select 문을 반환
	     * @param nameStart String select name 의 pre string
	     * @param setDate String 초기 Default Select 될 연도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='day'&gt;&lt;option&gt;...
	     */
	     public static String getDaySelect(String nameStart, String setDate, int langGbn){
	       StringBuffer sf = new StringBuffer();
	       int setYear = Integer.parseInt(setDate.substring(0,4));
	       int setMon  = Integer.parseInt(setDate.substring(4,6));
	       int setDay  = Integer.parseInt(setDate.substring(6));
	       // 윤년 계산
	       int[] end = { 31,28,31,30,31,30,31,31,30,31,30,31 };
	       if ((setYear % 4 == 0 && setYear % 100 != 0) || setYear % 400 == 0)
	         end[1] = 29;

	       sf.append("<select name='").append(preString).append(nameStart).append("day'  datatype='date3' onFocus='chkComboDateDD();' onChange='chkComboDateDD();'>\n");
	       for(int i=1;i<=end[setMon-1];i++){
	         sf.append("<option value='").append(StringUtil.getPadZero(i)).append("'")
	           .append(getSelected(i, setDay)).append(">").append(StringUtil.getPadZero(i))
	           .append("\n");
	       }
	       sf.append("</select>");
	       if(langGbn > 1) sf.append("dd");
	       else sf.append("일");

	       return sf.toString();
	     }

	     /**
	     * 월을 표현하는 select 문을 반환
	     * @param nameStart String select name 의 pre string
	     * @param setDate String 초기 Default Select 될 연도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='month'&gt;&lt;option&gt;...
	     */
	     public static String getMonthSelect(String nameStart, String setDate, int langGbn){
	       return getMonthSelect(nameStart, setDate, langGbn, "");
	     }

	     /**
	     * 월을 표현하는 select 문을 반환
	     * @param nameStart String select name 의 pre string
	     * @param setDate String 초기 Default Select 될 연도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='month'&gt;&lt;option&gt;...
	     */
	     public static String getMonthSelect(String nameStart, String setDate, int langGbn, String maxinterval){
	       StringBuffer sf = new StringBuffer();
	       // month print
	       int setMon  = Integer.parseInt(setDate.substring(4,6));
	       //sf.append("<select name='h").append(nameStart).append("month' onchange='parent.chgdate(window,this)'>\n");
	       sf.append("<select name='").append(preString).append(nameStart).append("month' datatype='date2' onFocus='chkComboDateMM();' onChange='chkComboDateMM();'");
	       if(!maxinterval.equals("")){
	         sf.append(" maxinterval='").append(maxinterval).append("'");
	       }
	       sf.append(">\n");
	       for(int i=1;i<13;i++){
	         sf.append("<option value='").append(StringUtil.getPadZero(i)).append("'")
	           .append(getSelected(i, setMon)).append(">").append(StringUtil.getPadZero(i))
	           .append("\n");
	       }
	       sf.append("</select>");
	       if(langGbn > 1) sf.append("mm");
	       else sf.append("월");
	       return sf.toString();
	     }

	     /**
	     * 년도를 표현하는 select 문을 반환
	     * @param nameStart String select name 의 pre string
	     * @param setDate String 초기 Default Select 될 연도
	     * @param yearRange int  년도 select 에 보여질 년도의 수, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getYearSelect(String nameStart, String setDate, int yearRange, int langGbn){
	       int fromYear = 0;
	       int toYear = 0;
	       if(yearRange < 0){
	         return getYearSelect(nameStart, setDate, yearRange, toYear, langGbn, "");
	       } else {
	         return getYearSelect(nameStart, setDate, fromYear, yearRange, langGbn, "");
	       }
	     }

	     /**
	     * 년도를 표현하는 select 문을 반환
	     * @param nameStart String select name 의 pre string
	     * @param setDate String 초기 Default Select 될 연도
	     * @param yearRange int  년도 select 에 보여질 년도의 수, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getYearSelect(String nameStart, String setDate, int yearRange, int langGbn, String maxinterval){
	       int fromYear = 0;
	       int toYear = 0;
	       if(yearRange < 0){
	         return getYearSelect(nameStart, setDate, yearRange, toYear, langGbn, maxinterval);
	       } else {
	         return getYearSelect(nameStart, setDate, fromYear, yearRange, langGbn, maxinterval);
	       }
	     }

	     /**
	     * 년도를 표현하는 select 문을 반환
	     * @param nameStart String select name 의 pre string
	     * @param setDate String 초기 Default Select 될 연도
	     * @param fromYear int  년도 select 에 보여질 년도의 시작 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param toYear int  년도 select 에 보여질 년도의 종료 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getYearSelect(String nameStart, String setDate, int fromYear, int toYear, int langGbn, String maxinterval){
	       StringBuffer sf = new StringBuffer();
	       int setYear = Integer.parseInt(setDate.substring(0,4));
	       int fYear  = 0;
	       int tYear  = 0;

	       fYear  = Integer.parseInt(DateUtil.getDate("yyyy"));
	       fYear = fYear + fromYear;
	       tYear  = Integer.parseInt(DateUtil.getDate("yyyy"));
	       tYear = tYear + toYear;

	       // year print
	       //sf.append("<select name='h").append(nameStart).append("year' onchange='parent.chgdate(window,this)'>\n");
	       sf.append("<select name='").append(preString).append(nameStart).append("year' datatype='date1'");
	       if(!maxinterval.equals("")){
	         sf.append(" maxinterval='").append(maxinterval).append("'");
	       }
	       sf.append(">\n");
	       for(int i=fYear;i<=tYear;i++){
	         sf.append("<option value='").append(StringUtil.getPadZero(i)).append("'")
	           .append(getSelected(i, setYear)).append(">").append(StringUtil.getPadZero(i))
	           .append("\n");
	       }
	       sf.append("</select>");
	       if(langGbn > 1) sf.append("yy");
	       else sf.append("년");
	       return sf.toString();
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method
	     * 3개의 select 를 그린다.
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(){
	       return DateUtil.getYMDSelect(0, "", DateUtil.getDate( "yyyyMMdd"), -3, 1);
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method
	     * 3개의 select 를 그린다.
	     * @param setDate String 로드 시 선택될 날짜 YYYYMMDD
	     * @return String &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(String setDate){
	       return DateUtil.getYMDSelect(0, "", setDate, -3, 1);
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method
	     * 3개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , s:start/e:end
	     * @param setDate String date to set select YYYYMMDD
	     * @return String &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(String nameStart, String setDate){
	       return DateUtil.getYMDSelect(0, nameStart, setDate, -3, 1);
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method
	     * 3개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , s:start/e:end
	     * @param setDate String date to set select YYYYMMDD
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(String nameStart, String setDate, int yearRange){
	       return DateUtil.getYMDSelect(0, nameStart, setDate, yearRange, 1);
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method
	     * 3개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , s:start/e:end
	     * @param setDate String date to set select YYYYMMDD
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(String nameStart, String setDate, int yearRange, int langGbn){
	       return getYMDSelect(0, nameStart, setDate, yearRange, langGbn);
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method<br>
	     * 3개의 select , javascript function, hidden input 을 그림
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start , s:start/e:end
	     * @param setDate String date to set select YYYYMMDD
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(int dateGbn, String nameStart, String setDate, int yearRange, int langGbn){
	       return getYMDSelect(dateGbn, nameStart, setDate, yearRange, langGbn, "");
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method<br>
	     * 3개의 select , javascript function, hidden input 을 그림
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start , s:start/e:end
	     * @param setDate String date to set select YYYYMMDD
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(int dateGbn, String nameStart, String setDate, int yearRange, int langGbn, String maxinterval){
	       int fromYear = 0;
	       int toYear = 0;

	       if(yearRange < 0){
	         return getYMDSelect(dateGbn, nameStart, setDate, yearRange, toYear, langGbn, maxinterval);
	       } else {
	         return getYMDSelect(dateGbn, nameStart, setDate, fromYear, yearRange, langGbn, maxinterval);
	       }
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method<br>
	     * 3개의 select , javascript function, hidden input 을 그림
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start , s:start/e:end
	     * @param setDate String date to set select YYYYMMDD
	     * @param fromYear int  년도 select 에 보여질 년도의 시작 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param toYear int  년도 select 에 보여질 년도의 종료 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(int dateGbn, String nameStart, String setDate, int fromYear, int toYear, int langGbn, String maxinterval){
	       return getYMDSelect(dateGbn, nameStart, setDate, fromYear, toYear, langGbn, maxinterval, "");
	     }

	     /**
	     * 년 월 일 을 선택하는 select 를 그리는 method<br>
	     * 3개의 select , javascript function, hidden input 을 그림
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start , s:start/e:end
	     * @param setDate String date to set select YYYYMMDD
	     * @param fromYear int  년도 select 에 보여질 년도의 시작 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param toYear int  년도 select 에 보여질 년도의 종료 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @param APPGBN String P:개인, B:기업, C:개인카드, D:기업카드, 이미지 디렉토리 선택을 위한 구분값
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDSelect(int dateGbn, String nameStart, String setDate, int fromYear, int toYear, int langGbn, String maxinterval, String APPGBN){
	       try{
	         StringBuffer sf = new StringBuffer();
	         String imgdir = "";

	         if(setDate.trim().length() != 8){
	           setDate = DateUtil.getDateString();
	         }
	         if(APPGBN.equals("B")){
	           imgdir = "bimg/";
	         } else if(APPGBN.equals("C") || APPGBN.equals("D")){
	           imgdir = "cimg/";
	         } else {
	           imgdir = "";
	         }

	         sf.append(DateUtil.getYearSelect(nameStart, setDate, fromYear, toYear, langGbn, maxinterval));
	         sf.append(DateUtil.getMonthSelect(nameStart, setDate, langGbn));
	         sf.append(DateUtil.getDaySelect(nameStart, setDate, langGbn));
	         sf.append("<input type='hidden' name='").append(preString).append(nameStart).append("date' value='")
	           .append(setDate).append("'>");
	         if(!imgdir.equals("")){
		         sf.append("<span id='").append(preString).append(nameStart).append("_calspan'><a tabindex='-1' href='javascript:void(0);' onClick='viewCal("+dateGbn+");'><img src='/docs/img/")
		           .append(imgdir)
		           .append("btn1_calendar.gif' hspace='1' vspace='5' border='0' align='absmiddle'></a></span>");
	         }
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1475,  e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 년 월 일 을 선택하는 select  두개를 그리는 method<br>
	     * 6개의 select , javascript function, hidden input, 기간 선택 버튼을 그림<br>
	     * 첫번째 select 의 이름은 "hf" + namestart, 두번째 select 의 이름은 "ht" + namestart 로 출력됨
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start
	     * @param setDate1 String date to set select YYYYMMDD1
	     * @param setDate2 String date to set select YYYYMMDD2
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param btn String 버튼 이름 , 로 분리
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDRangeSelect(int dateGbn, String nameStart, String setDate1, String setDate2, int yearRange, int langGbn, String btn){
	       return getYMDRangeSelect(dateGbn, nameStart, setDate1, setDate2, yearRange, langGbn, btn, "", "P");
	     }

	     /**
	     * 년 월 일 을 선택하는 select  두개를 그리는 method<br>
	     * 6개의 select , javascript function, hidden input, 기간 선택 버튼을 그림<br>
	     * 첫번째 select 의 이름은 "hf" + namestart, 두번째 select 의 이름은 "ht" + namestart 로 출력됨
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start
	     * @param setDate1 String date to set select YYYYMMDD1
	     * @param setDate2 String date to set select YYYYMMDD2
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param btn String 버튼 이름 , 로 분리
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDRangeSelect(int dateGbn, String nameStart, String setDate1, String setDate2, int yearRange, int langGbn, String btn, String maxinterval){
	       return getYMDRangeSelect(dateGbn, nameStart, setDate1, setDate2, yearRange, langGbn, btn, maxinterval, "P");
	     }

	     /**
	     * 년 월 일 을 선택하는 select  두개를 그리는 method<br>
	     * 6개의 select , javascript function, hidden input, 기간 선택 버튼을 그림<br>
	     * 첫번째 select 의 이름은 "hf" + namestart, 두번째 select 의 이름은 "ht" + namestart 로 출력됨
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start
	     * @param setDate1 String date to set select YYYYMMDD1
	     * @param setDate2 String date to set select YYYYMMDD2
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param btn String 버튼 이름 , 로 분리
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @param APPGBN String P:개인, B:기업, C:개인카드, D:기업카드, 이미지 디렉토리 선택을 위한 구분값
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDRangeSelect(int dateGbn, String nameStart, String setDate1, String setDate2, int yearRange, int langGbn, String btn, String maxinterval, String APPGBN){
	       String n1 = "f" + nameStart;
	       String n2 = "t" + nameStart;
	       try{
	         StringBuffer sf = new StringBuffer();

	         sf.append(DateUtil.getRangeButton(n1, n2, btn, yearRange, APPGBN));
	         sf.append("<br>");
	         sf.append(DateUtil.getYMDSelect(dateGbn, n1, setDate1, yearRange, langGbn, maxinterval));
	         sf.append("~");
	         sf.append(DateUtil.getYMDSelect(dateGbn, n2, setDate2, yearRange, langGbn));
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1543, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 년 월 일 을 선택하는 select  두개를 그리는 method<br>
	     * 6개의 select , javascript function, hidden input, 기간 선택 버튼을 그림<br>
	     * 첫번째 select 의 이름은 "hf" + namestart, 두번째 select 의 이름은 "ht" + namestart 로 출력됨<br>
	     * 기간을 과거부터 미래까지 지정 가능함, 단 기간선택 버튼(3일, 1개월 등) 은 사용 불가
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart String character to set select name Start
	     * @param setDate1 String date to set select YYYYMMDD1
	     * @param setDate2 String date to set select YYYYMMDD2
	     * @param fromYear int  년도 select 에 보여질 년도의 시작 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param toYear int  년도 select 에 보여질 년도의 종료 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMDRangeSelect(int dateGbn, String nameStart, String setDate1, String setDate2, int fromYear, int toYear, int langGbn, String maxinterval){
	       String n1 = "f" + nameStart;
	       String n2 = "t" + nameStart;
	       try{
	         StringBuffer sf = new StringBuffer();

	         sf.append(DateUtil.getYMDSelect(dateGbn, n1, setDate1, fromYear, toYear, langGbn, maxinterval));
	         sf.append("~");
	         sf.append(DateUtil.getYMDSelect(dateGbn, n2, setDate2, fromYear, toYear, langGbn, ""));
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1574,  e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 년 월 일 을 선택하는 select  두개를 그리는 method<br>
	     * 6개의 select , javascript function, hidden input, 기간 선택 버튼을 그림
	     * @param dateGbn int  : 0: 모든날짜 선택 가능, 1: 공휴일 제외 선택 가능, 2: 은행 영업일만 선택 가능
	     * @param nameStart1 String character to set select name Start1 , s:start/e:end
	     * @param setDate1 String date to set select YYYYMMDD1
	     * @param nameStart2 String character to set select name Start2 , s:start/e:end
	     * @param setDate2 String date to set select YYYYMMDD2
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param btn String 버튼 이름 , 로 분리
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     * @deprecated , getYMDRangeSelect(int dateGbn, String nameStart, String setDate1, String setDate2, int yearRange, int langGbn, String btn) 를 사용할것
	     */
	     @Deprecated
		public static String getYMDRangeSelect(int dateGbn, String nameStart1, String setDate1, String nameStart2, String setDate2, int yearRange, int langGbn, String btn){
	       try{
	         StringBuffer sf = new StringBuffer();

	         sf.append(DateUtil.getRangeButton(nameStart1, nameStart2, btn, yearRange, "P"));
	         sf.append("<br>");
	         sf.append(DateUtil.getYMDSelect(dateGbn, nameStart1, setDate1, yearRange, langGbn));
	         sf.append("~");
	         sf.append(DateUtil.getYMDSelect(dateGbn, nameStart2, setDate2, yearRange, langGbn));
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1604, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 년 월 을 선택하는 select  두개를 그리는 method<br>
	     * 4개의 select , javascript function, hidden input, 기간 선택 버튼을 그림<br>
	     * 첫번째 select 의 이름은 "hf" + namestart, 두번째 select 의 이름은 "ht" + namestart 로 출력됨
	     * @param nameStart String character to set select name Start1 , s:start/e:end
	     * @param setDate1 String date to set select YYYYMMDD1
	     * @param setDate2 String date to set select YYYYMMDD2
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param btn String 버튼 이름 , 로 분리
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @param APPGBN String P:개인, B:기업, C:개인카드, D:기업카드, 이미지 디렉토리 선택을 위한 구분값
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMRangeSelect(String nameStart, String setDate1, String setDate2, int yearRange, int langGbn, String btn, String maxinterval, String APPGBN){
	       String n1 = "f" + nameStart;
	       String n2 = "t" + nameStart;
	       try{
	         StringBuffer sf = new StringBuffer();

	         sf.append(DateUtil.getRangeButton(n1, n2, btn, yearRange, APPGBN));
	         sf.append("<br>");
	         sf.append(DateUtil.getYMSelect(n1, setDate1, yearRange, langGbn, maxinterval));
	         sf.append("~");
	         sf.append(DateUtil.getYMSelect(n2, setDate2, yearRange, langGbn));
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1636, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 년 을 선택하는 select  두개를 그리는 method<br>
	     * 2개의 select , javascript function, hidden input, 기간 선택 버튼을 그림<br>
	     * 첫번째 select 의 이름은 "hf" + namestart, 두번째 select 의 이름은 "ht" + namestart 로 출력됨
	     * @param nameStart String character to set select name Start1 , s:start/e:end
	     * @param setDate1 String date to set select YYYYMMDD1
	     * @param setDate2 String date to set select YYYYMMDD2
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param btn String 버튼 이름 , 로 분리
	     * @param APPGBN String P:개인, B:기업, C:개인카드, D:기업카드, 이미지 디렉토리 선택을 위한 구분값
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYearRangeSelect(String nameStart, String setDate1, String setDate2, int yearRange, int langGbn, String btn, String APPGBN){
	       String n1 = "f" + nameStart;
	       String n2 = "t" + nameStart;
	       try{
	         StringBuffer sf = new StringBuffer();

	         sf.append(DateUtil.getRangeButton(n1, n2, btn, yearRange, APPGBN));
	         sf.append("<br>");
	         sf.append(DateUtil.getYearSelect(n1, setDate1, yearRange, langGbn));
	         sf.append("~");
	         sf.append(DateUtil.getYearSelect(n2, setDate2, yearRange, langGbn));
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1667, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 기간을 선택하는 버튼들을 그리는  method<br>
	     * @param nameStart1 String character to set select name Start1 , s:start/e:end
	     * @param nameStart2 String character to set select name Start2 , s:start/e:end
	     * @param btn String 버튼 이름 , 로 분리
	     * @param yearRange int  년도 select 에 보여질 년도의 수, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     /*
	     public static String getRangeButton(String nameStart1, String nameStart2, String btn, int yearRange){
	       return getRangeButton(nameStart1, nameStart2, btn, yearRange, "P");
	     }
	     */

	     /**
	     * 기간을 선택하는 버튼들을 그리는  method<br>
	     * @param nameStart1 String character to set select name Start1 , s:start/e:end
	     * @param nameStart2 String character to set select name Start2 , s:start/e:end
	     * @param btn String 버튼 이름 , 로 분리
	     * @param yearRange int  년도 select 에 보여질 년도의 수, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐
	     * @param APPGBN String P:개인, B:기업, C:개인카드, D:기업카드, 이미지 디렉토리 선택을 위한 구분값
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getRangeButton(String nameStart1, String nameStart2, String btn, int yearRange, String APPGBN){
	       try{
	         StringBuffer sf = new StringBuffer();
	         StringTokenizer b = new StringTokenizer(btn, ",");
	         String imgdir = "";

	         if(APPGBN.equals("B")){
	           imgdir = "bimg/";
	         } else if(APPGBN.equals("C") || APPGBN.equals("D")){
	           imgdir = "cimg/";
	         } else {
	           imgdir = "pimg/";
	         }

	         String s  = "<img src='/docs/img/" + imgdir + "line_vert_01.gif' hspace='5' align='absmiddle'>";
	         String ih = "<a tabindex='-1' href='javascript:setRange(\"";
	         String im = "\")'><img src='/docs/img/" + imgdir + "btn1_";
	         String id = ".gif' align='absmiddle' vspace='1'>";
	         String t = "";

	         while (b.hasMoreTokens()){
	           t = b.nextToken();
	           sf.append(ih).append(preString).append(nameStart1).append("\",\"")
	             .append(preString).append(nameStart2).append("\",\"");
	           if(yearRange < 0)
	             sf.append("-");

	           sf.append(t).append(im).append(t).append(id).append("</a>").append(s);
	         }
	         return sf.substring(0, sf.length() - s.length());
	         //return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1728, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 년 월 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getYMSelect(){
	       return DateUtil.getYMSelect("", DateUtil.getDate( "yyyyMMdd"), 3, 1);
	     }

	     /**
	     * 년 월 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getYMSelect(String nameStart, String setDate){
	       return DateUtil.getYMSelect(nameStart, setDate, 3, 1);
	     }

	     /**
	     * 년 월 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMSelect(String nameStart, String setDate, int yearRange){
	       return DateUtil.getYMSelect(nameStart, setDate, yearRange, 1);
	     }

	     /**
	     * 년 월 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMSelect(String nameStart, String setDate, int yearRange, int langGbn){
	       return getYMSelect(nameStart, setDate, yearRange, langGbn, "");
	     }

	     /**
	     * 년 월 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMSelect(String nameStart, String setDate, int yearRange, int langGbn, String maxinterval){
	       int fromYear = 0;
	       int toYear = 0;

	       if(yearRange < 0){
	         return getYMSelect(nameStart, setDate, yearRange, toYear, langGbn, maxinterval);
	       } else {
	         return getYMSelect(nameStart, setDate, fromYear, yearRange, langGbn, maxinterval);
	       }
	     }
	     /**
	     * 년 월 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @param fromYear int  년도 select 에 보여질 년도의 시작 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param toYear int  년도 select 에 보여질 년도의 종료 년, 0보다 작은 수를 주면 현재년도에서 과거로 보여지고 0보다 큰수를 주면 현재년도보다 미래로 보여짐, 0은 현재년도
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getYMSelect(String nameStart, String setDate, int fromYear, int toYear, int langGbn, String maxinterval){
	       try{
	         StringBuffer sf = new StringBuffer();
	         String sDate = setDate;

	         if(sDate.trim().length() != 8 && sDate.trim().length() != 6){
	           sDate = DateUtil.getDateString();
	         }
	         if(sDate.length() > 6){
	           sDate = sDate.substring(0,6);
	         }
	         sf.append(DateUtil.getYearSelect(nameStart, setDate, fromYear, toYear, langGbn, maxinterval));
	         sf.append(DateUtil.getMonthSelect(nameStart, setDate, langGbn));
	         sf.append("<input type='hidden' name='").append(preString).append(nameStart).append("date' value='").append(sDate).append("'>");
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1825, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 년 월 을 선택하는 하나의 select 를 그리는 method<br>
	     * monthRange 를 주면 해당하는 년월만 그린다<br>
	     * monthRange=-6 을 주면 현재월이 7월이면 당년 2월부터 나오고 현재월이 3월이면 작년 10월부터 나온다<br>
	     * select 의 이름이 종료년월일이 되고 hidden 이 시작 년월일이 된다
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM 또는 YYYYMMDD
	     * @param monthRange int year range  - : 현재월 이전 + : 현재월 이후, 0 현재월
	     * @return String객체 &lt;select name='ftym'&gt;&lt;option&gt;...
	     */
	     public static String getYMOneSelect(String nameStart, String setDate, int monthRange){
	       try{
	         StringBuffer sb = new StringBuffer();
	         sb.append("<select name='ht").append(nameStart).append("date' onchange='setFirstDate(this)'>\n");

	         int fYear  = Integer.parseInt(DateUtil.getDate("yyyy"));
	         int fMonth = Integer.parseInt(DateUtil.getDate("MM"));
 
	         int range = 0;
	         int p = 0;
	         String sDate = setDate.substring(0,6);
	         String curDate = DateUtil.getDateString();
	         String optVal = "";
	         String selVal = "";

	         if(monthRange < 0){
	           range = 0 - monthRange;
	           p = -1;
	         } else if(monthRange > 0){
	           range = monthRange;
	           p = +1;
	         }

	         for(int i=0;i<range;i++){
	           optVal = getLastYMD(fYear, fMonth);
	           if(optVal.substring(0,6).equals(curDate.substring(0,6))){
	             optVal = curDate;
	           }
	           selVal = optVal.substring(0,6);

	           sb.append("<option value='").append(optVal).append("'").append(getSelected(selVal, sDate ))
	             .append(">").append(String.valueOf(fYear)).append("년 ").append(String.valueOf(fMonth)).append("월\n");

	           fMonth = fMonth + p;
	           if(fMonth < 1){
	             fMonth = 12;
	             fYear = fYear + p;
	           } else if(fMonth > 12){
	             fMonth = 1;
	             fYear = fYear + p;
	           }
	         }

	         sb.append("</select><input type='hidden' name='hf").append(nameStart).append("date' value='")
	           .append(curDate.substring(0,6)).append("01'>\n");

	         return sb.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1888, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     * 입력한 년, 월의 마지막 날짜를 찾아서 yyyyMMdd 포맷으로 반환
	     * @param year String 년도 yyyy
	     * @param month String 월 mm
	     * @return String객체 yyyyMMdd
	     */
	     public static String getLastYMD(String year, String month){
	       try{
	         return getLastYMD(Integer.parseInt(year), Integer.parseInt(month));
	       } catch (Throwable e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1903, e.toString() + ":year=[" + year + "]month=[" + month + "]");
	         return (year + month);
	       }
	     }

	     /**
	     * 입력한 년, 월의 마지막 날짜를 찾아서 yyyyMMdd 포맷으로 반환
	     * @param year int 년도 yyyy
	     * @param month int 월 mm
	     * @return String객체 yyyyMMdd
	     */
	     public static String getLastYMD(int year, int month){
	       try{
	       	int [] end = {31,28,31,30,31,30,31,31,30,31,30,31};
	       	if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
	       		end[1] = 29;

	       	return String.valueOf(year) + StringUtil.getPadString(String.valueOf(month), 2, "0") + String.valueOf(end[month-1]);
	       } catch (Throwable e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1922, e.toString() + ":year=[" + year + "]month=[" + month + "]");
	         return "000000";
	       }
	     }

	     /**
	     * 월 년 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다. 신용카드에서 사용
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getMYSelect(){
	       return DateUtil.getMYSelect("", DateUtil.getDate( "yyyyMMdd"), 3, 1);
	     }

	     /**
	     * 월 년 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다. 신용카드에서 사용
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @return String객체 &lt;select name='year'&gt;&lt;option&gt;...
	     */
	     public static String getMYSelect(String nameStart, String setDate){
	       return DateUtil.getMYSelect(nameStart, setDate, 3, 1);
	     }

	     /**
	     * 월 년 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다. 신용카드에서 사용
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getMYSelect(String nameStart, String setDate, int yearRange){
	       return DateUtil.getMYSelect(nameStart, setDate, yearRange, 1);
	     }

	     /**
	     * 월 년 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다. 신용카드에서 사용
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getMYSelect(String nameStart, String setDate, int yearRange, int langGbn){
	       return getMYSelect(nameStart, setDate, yearRange, langGbn, "");
	     }

	     /**
	     * 월 년 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다. 신용카드에서 사용
	     * @param nameStart String character to set select name Start , F:from/T:to
	     * @param setDate String date to set select YYYYMM
	     * @param yearRange int year range  - : 현재년 이전 + : 현재년 이후
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fyear'&gt;&lt;option&gt;...
	     */
	     public static String getMYSelect(String nameStart, String setDate, int yearRange, int langGbn, String maxinterval){
	       try{
	         StringBuffer sf = new StringBuffer();

	         if(setDate.trim().length() != 8 && setDate.trim().length() != 6){
	           setDate = DateUtil.getDateString();
	         }

	         sf.append(DateUtil.getMonthSelect(nameStart, setDate, langGbn));
	         sf.append(DateUtil.getYearSelect(nameStart, setDate, yearRange, langGbn, maxinterval));
	         sf.append("<input type='hidden' name='").append(preString).append(nameStart).append("date' value='").append(setDate).append("'>");
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 1995, e.toString());
	         return e.toString();
	       }
	     }

	     /**
	     *  월 일 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @return String객체 &lt;select name='month'&gt;&lt;option&gt;...
	     */
	     public static String getMDSelect(){
	       return DateUtil.getMDSelect("", DateUtil.getDate( "yyyyMMdd"), 1);
	     }

	     /**
	     *  월 일 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start ,
	     * @param setDate String date to set select YYYYMMDD
	     * @return String객체 &lt;select name='fmonth'&gt;&lt;option&gt;...
	     */
	     public static String getMDSelect(String nameStart, String setDate){
	       return DateUtil.getMDSelect(nameStart, setDate, 1);
	     }

	     /**
	     *  월 일 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start ,
	     * @param setDate String date to set select YYYYMMDD
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @return String객체 &lt;select name='fmonth'&gt;&lt;option&gt;...
	     */
	     public static String getMDSelect(String nameStart, String setDate, int langGbn){
	       return getMDSelect(nameStart, setDate, langGbn, "");
	     }

	     /**
	     *  월 일 을 선택하는 select 를 그리는 method
	     * 2개의 select 를 그린다.
	     * @param nameStart String character to set select name Start ,
	     * @param setDate String date to set select YYYYMMDD
	     * @param langGbn int language gubun  - : 1:한글 2~:영어
	     * @param maxinterval String 조회 최대 기간을 설정, 1d, 3w, 5m, 2y 등 사용 가능
	     * @return String객체 &lt;select name='fmonth'&gt;&lt;option&gt;...
	     */
	     public static String getMDSelect(String nameStart, String setDate, int langGbn, String maxinterval){
	       try{
	         StringBuffer sf = new StringBuffer();

	         if(setDate.trim().length() != 8){
	           setDate = DateUtil.getDateString();
	         }

	         sf.append(DateUtil.getMonthSelect(nameStart, setDate, langGbn, maxinterval));
	         sf.append(DateUtil.getDaySelect(nameStart, setDate, langGbn));
	         sf.append("<input type='hidden' name='").append(preString).append(nameStart).append("date' value='").append(setDate).append("'>");
	         return sf.toString();
	       }catch(Exception e){
	    	   ConfigClass.consoleOutPrint(DateUtil.class, 2054, e.toString());
	         return e.toString();
	       }
	     }	    
	    

	     
	 	// 공통으로 사용할 날짜 포맷 yyyy-mm-dd로 변환
	 	static public String dateFormat(Date dt) {
	 		return formatDate(dt, "yyyy-MM-dd");
	 	}	     

	 	// 공통으로 사용할 날짜 포맷 yyyy-mm-dd로 변환
	 	static public String dateFormat(Date dt, String format) {
	 		return formatDate(dt, format);
	 	}		
	 	
	 	public static String getDateColumn(String strCol, String format){
	 		if(strCol == null || strCol.trim().equals("")){
	 			return null;
	 		}
	 		
	 		long lngCol = 0L;
	 		lngCol = Long.parseLong(strCol);

	 		Date dt = new Date(lngCol);
	 		
	 		return  formatDate(dt, format);
	 	}
	 	
	 	// 공통으로 사용할 날짜 포맷 yyyy-mm-dd로 변환
		static public String getBetween(Date dt) {
			Date now = new Date();
			return (now.getTime() -  dt.getTime())/ (1000 * 60 * 60)+"" ;
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			int idx = 0;
			int total = 3;
			int ici_perc = 0;
			int pre_perc = 0;
			
			for(int i = 0; i < 3; i++){
				int imsi = ++ idx / total * 100;
				
				System.out.println("idx>>>>>>>>>>>>>>>>>" + idx);
				System.out.println("imsi>>>>>>>>>>>>>>>>>" + imsi);
				
				ici_perc = (int) Math.floor(imsi);
				
				System.out.println("ici_perc>>>>>>>>>>>" + ici_perc);
				
				if (pre_perc!=ici_perc){
					pre_perc = ici_perc;
					
					System.out.println("pre_perc, ici_perc>>>>>>>>>>>" +pre_perc + "@" + ici_perc);
				}
			}

		} 
}
