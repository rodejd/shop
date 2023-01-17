package com.wepinit.wepinit_shop.xcube.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
	 
	/**
	 * Excel 다운로드
	 * @param fileName  : 파일명
	 * @param sheetName : 시트명
	 * @param dataList  : Data List
	 * @param title     : 타이틀명
	 * @param fieldNm   : 데이터 필드명
	 * @param response 
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void writeExcel(String fileName, String sheetName, List<Map<String, Object>> dataList, String[] title, String[] fieldNm, HttpServletResponse response) throws Exception {
		try {
			int rowCnt = 0;
			int fieldSize = fieldNm.length;
			Map<String,Object> data = null;
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(sheetName);
			HSSFRow row = null;
			HSSFCell cell = null;
			HSSFCellStyle style = workbook.createCellStyle();
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setLeftBorderColor(HSSFColor.BLACK.index);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setRightBorderColor(HSSFColor.BLACK.index);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setTopBorderColor(HSSFColor.BLACK.index);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
			row = sheet.createRow(rowCnt++);
			for(int i = 0; i < fieldSize; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
				cell.setCellValue(title[i]);
			}
			if(dataList != null) {
				for (int i = 0, size = dataList.size(); i < size; i++){
					row = sheet.createRow(rowCnt++);
					data = (Map<String,Object>)dataList.get(i);
					for (int j = 0; j < fieldSize; j++){
						cell = row.createCell(j);
						cell.setCellStyle(style);
						
						if(cell != null){
							Object obj = data.get(fieldNm[j]);
							if(obj instanceof Date) cell.setCellValue((Date)obj);
							else if(obj instanceof Boolean) cell.setCellValue((Boolean)obj);
							else if(obj instanceof String) cell.setCellValue(new HSSFRichTextString( isDefined(String.valueOf(obj)) == true ? String.valueOf(obj) : "" ));
							else if(obj instanceof Double) cell.setCellValue(toDoubleFormat((Double) obj) );
							else if(obj instanceof Integer) cell.setCellValue(toNumFormat( (Integer)obj));
							else if(obj instanceof Long) cell.setCellValue(toNumFormat( (Long)obj));
							else if(obj instanceof Float) cell.setCellValue(toFloatFormat((Float)obj));
							else if(obj instanceof BigDecimal) cell.setCellValue(toDoubleFormat((BigDecimal) obj) );
						}
					}
				}
			}

			int colNum = fieldSize;
			for( int i = 0; i < colNum; i++){
				sheet.autoSizeColumn((short)i);
				sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000);
			}
			
			try {
				fileName = URLEncoder.encode(fileName, "UTF-8");
				fileName = fileName.replaceAll("\\+", "%20");
				response.setHeader("Content-Disposition","attachment;filename=" + fileName + ";");
				workbook.write(response.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (response.getOutputStream() != null) {
					response.getOutputStream().flush();
					response.getOutputStream().close();
				}
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	public static String toNumFormat(int num) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(num);
	}
	
	public static String toNumFormat(long num) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(num);
	}
	
	public static String toDoubleFormat(double num) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(num);
	}
	
	public static String toDoubleFormat(BigDecimal num) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(num.doubleValue());
	}
	
	public static String toFloatFormat(float num) {
		return String.format("%.2f" , num);
	}

	public static boolean isDefined(String paramString) {
		return (!"null".equals(paramString)) && (paramString != null) && (paramString.length() != 0);
	}
}
