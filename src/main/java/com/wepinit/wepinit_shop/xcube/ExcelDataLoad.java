/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xcube;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.vo.common.AdminSellerSearchListVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [설명] 엑셀파일 관련 함수
 * 
 * @author Administrator
 *
 */
public class ExcelDataLoad implements Serializable {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelDataLoad.class);
	

	/**
	 * ExcelDataLoad 생성자
	 */
	public ExcelDataLoad(){
	}


	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
	public List<Map> getExcelDataInfo(Map data) throws BizException, Exception {
		List<Map> list = null;		
		List<String> valiList = new ArrayList<String>(); 
		String vali = "";
		String content;
		Boolean validation = true;
		int flag = 0;
		List<AdminSellerSearchListVO> sellerList = (List<AdminSellerSearchListVO>) data.get("sellerList");
		List<GdGoodsBrand> brandList = (List<GdGoodsBrand> ) data.get("brandList");
		List<GdCategory> categoryList = (List<GdCategory>) data.get("categoryList");
		
		ExcelValidationCheck evc = new ExcelValidationCheck();
		
		
		HashMap<String, Object> hm 	= null;
		
		int i 	= 0; //행
		int j 	= 0; //열
				
		try {
			log.debug("<br>--------" + String.valueOf(data.get("fileName")));			
			/*
			 * Workbook workbook = Workbook.getWorkbook(new
			 * File(String.valueOf(data.get("fileName"))));
			 * 
			 * Sheet sheet = workbook.getSheet(0);
			 * 
			 * int col = sheet.getColumns(); // 시트의 컬럼의 수를 반환한다.
			 */
			int rowindex=1;
			int columnindex=0;
			FileInputStream fis = new FileInputStream(String.valueOf(data.get("fileName")));
			HSSFWorkbook workbook=new HSSFWorkbook(fis);
			//시트 수 (첫번째에만 존재하므로 0을 준다)
			HSSFSheet sheet = workbook.getSheetAt(0);
			//행의 수 
			int rows=sheet.getPhysicalNumberOfRows();
			
			HSSFRow row=sheet.getRow(rowindex);

			HSSFRow mainRow = sheet.getRow(rowindex);
			//셀의 수
			int col=row.getPhysicalNumberOfCells();

			list = new ArrayList(col);	// list 생성
			
			i = 1;
			
			//셀값을 읽는다
            HSSFCell cell = row.getCell(columnindex);
            /*
			while(j < col){ 
				hm = new HashMap();
				hm.put(sheet.getCell(j, 1).getContents(), StringUtil.nullConv(sheet.getCell(j, i).getContents(), ""));
				log.debug("<br>-------"+i+"-"+j+"--" + sheet.getCell(j, 1).getContents() + "----->" + StringUtil.nullConv((String)hm.get(sheet.getCell(j, 1).getContents()), ""));
				j++;
			}
			*/
        	hm = new HashMap<String,Object>();
            for(j=0 ; j<col ; j++) {
            	//셀값을 읽는다
                cell = row.getCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                content = cell.getStringCellValue() + "";
            	//hm.put(content, StringUtil.nullConv(content, ""));
            	log.debug("------- content > {}" , content);
            }
			//list.add(hm);
			//i++;
			rowindex ++;
			
			
			//while ( i < sheet.getRows()) {
			while(rowindex < rows) {
				
				hm = new HashMap();
				j = 0;
				flag = 0;
				row=sheet.getRow(rowindex);
				//엑셀의 인덱스로 접근 j 
				while ( j < col){
					
					//i행 j열의 입력값
					//content = StringUtil.nullConv(sheet.getCell(j, i).getContents(), "");
					cell = row.getCell(j);
					if(null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType()) {
						content = "";
					}else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						content = cell.getStringCellValue();
					}
									
					if(j==0){
						String category;
						int categoryListSize = categoryList.size();
						
						String [] categoryArr = content.split("\\|");
						
						for(int cidx=0; cidx<categoryArr.length; cidx++){
							int idx = 0;
							do{
								category = categoryList.get(idx).getCategory();
								if(category.equals(categoryArr[cidx])){
									break;
								}
								idx = idx + 1;
							}while(idx < categoryListSize);
						
							if(idx == categoryListSize){
								validation = false;
							}
						}
					}
					//validPattern(문자열 , switch 1번 = 숫자만 입력했는지 2번 = 문자byte 계산 , size)	
					else if(j==1){
						validation = evc.lengthValidation(content, 12);
						if(validation == true && !"".equals(content)){
							String sellerCd;
							int sellerSize = sellerList.size();
							int idx =0;
								do{								
									sellerCd= sellerList.get(idx).getSellerCd();
									validation = content.equals(sellerCd);
									idx = idx + 1;
								}while(validation == false && idx < sellerSize);
							
						}
						
					//브랜드번호
					}else if(j==6){
						int brandcode;
						int brandSize = brandList.size();
						int idx = 0;
						do{
							brandcode = brandList.get(idx).getSno();
							if(brandcode == Integer.parseInt(content)){
								validation = true;
								break;
							}else if("".equals(content)){
								content = "0";
								validation = true;
								break;
							}
							idx=idx+1;
						}while(idx < brandSize);
						
						if(idx == brandSize){
							validation = false;
						}
					//추가상품목록 addopts
					}else if(j==29 && !"".equals(content)){
						String [] addopts1 = content.split("\\|");
						String [] addopts2 = addopts1[0].split("\\^");
						validation = addopts2.length == 3 ? true : false;
					}
					else if("".equals(content) && (j==0 || j==2) ){
						validation = false; 
					}else if(j==2 || j==9 || (10<j && j<17) || j==36){
						validation = evc.lengthValidation(content, 255);
								
					}else if(j==7 || j==27 ||(17<=j && j<=21)){
						validation = evc.lengthValidation(content, 10);
						validation = evc.validPattern(content, 1);
						content = "".equals(content) ? "0" : content;
					}
					else if(j==3 || j==25){
						validation =  evc.lengthValidation(content, 20);
						
					}else if(j==4 || j==5){
						validation = evc.lengthValidation(content, 50);
						
					}else if(j==23 || j==30 || j==39){
						validation = evc.validPattern(content,2);
						content = "".equals(content) ? "0" : content;
						
					//과세/비과세 validation
					}else if(j==24){
						validation = evc.validPattern(content,2);
						content = "".equals(content) ? "1" : content;
					}else if(j==26){
						validation = evc.validPattern(content, 5);
						content = "".equals(content) ? "0" : content;
					}
					else if(32<=j && j<=35){
						validation = evc.validPattern(content,4);
					}else if(j==22){
						validation = evc.validPattern(content,3);
					}else if(j==10){
						validation = evc.lengthValidation(content, 255);
						content = evc.ex_Title(content);
					}
					
					//유효성 검사를 실패했을 경우 
					if(validation == false){
						//log.debug("입력오류:"+i+"-"+j+"-" + sheet.getCell(j, 1).getContents() + "->" + content);
						//vali = String.valueOf(i)+"row"+String.valueOf(j)+"collum "+ content;
						log.debug("입력오류 : {}", content);
						vali = "insertfail>"+String.valueOf(i+1)+"행 "+String.valueOf(j)+"열";
						valiList.add(vali);
						j++;
						flag = 1;
						continue;
					}
					cell = mainRow.getCell(j);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					hm.put(cell.getStringCellValue(), content);
					//hm.put(sheet.getCell(j, 1).getContents(), content);
					
					//log.debug("<br>-------"+i+"-"+j+"--" + sheet.getCell(j, 1).getContents() + "----->" + StringUtil.nullConv((String)hm.get(sheet.getCell(j, 1).getContents()), ""));
					j++;
				}
				if(flag == 1){
					rowindex++;
					i++;
					continue;
				}

				list.add(hm);
				rowindex++;
				i++;
				
			}
			workbook.close();
		} catch(Exception e) {
			throw e;
		}
		if(valiList.size() > 0){
			hm = new HashMap();
			hm.put("valiList", valiList);
		}
		list.add(hm);
		return list;
	}
	
	public List getExcelDataInfo2(String fileFullPath) throws Exception {
		List list = null;
		
		HashMap hm 	= null;
		
		int i 	= 0;
		int j 	= 0;
				
		try {
			Workbook workbook = Workbook.getWorkbook(new File(fileFullPath));
			log.debug("<br>--------" + fileFullPath);
			Sheet sheet = workbook.getSheet(0);
			
			int col = sheet.getColumns();  // 시트의 컬럼의 수를 반환한다.

			list = new ArrayList(col);	// list 생성
			
			i = 1;
			while ( i < sheet.getRows()) {
				hm = new HashMap();
				j = 0;
				while ( j < col){
					hm.put(sheet.getCell(j, 0).getContents(), StringUtil.nullConv(sheet.getCell(j, i).getContents(), ""));
					log.debug("<br>-------"+i+"-"+j+"--" + sheet.getCell(j, 1).getContents() + "----->" + StringUtil.nullConv((String)hm.get(sheet.getCell(j, 1).getContents()), ""));
					j++;
				}
				list.add(hm);
				i++;
			}
		} catch(Exception e) {
			throw e;
		}
		
		return list;
	}
}