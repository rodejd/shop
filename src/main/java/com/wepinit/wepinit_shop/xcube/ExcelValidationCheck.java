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


//엑셀 유효성검사 클래스
public class ExcelValidationCheck {

	//숫자만
	String pattern1 = "^[./s0-9]*$";
	//0,1 공백만
	String pattern2 = "^[./s0-1]*$";
	//소문자 o, 공백만
	String pattern3 = "^[./s./o]*$";
	//0,1,2,3 만
	String pattern4 = "^[./s0-3]*$";
	
	//영어 숫자만
	String englishAndNumber = "^[./s0-1a-zA-z]*$";

	
	Boolean validation;
	//이미지 확장자 정규표현식
	String regExp = "^([\\S]+(\\.(?i)(jpg|png|gif|bmp))$)";
	
	public boolean lengthValidation(String parameter, int size){
		validation = false; 
		//문자열의 크기 계산	
		if(parameter.getBytes().length <= size){
			validation = true;
		}
		return validation;
	}
	
	
	public boolean validPattern(String parameter, int patternNo){
		validation = false;
		switch(patternNo){
		//case1 >> 숫자만 입력받은지 확인
		case 1:
			validation = parameter.matches(pattern1);
			break;
		//case2 >> 0,1, 공백만 입력받는지 확인하는 validation
		case 2:
			validation = parameter.matches(pattern2);
			break;
		//case 3 >> o, 공백만 입력받는지 확인하는 validation
		case 3:
			validation = parameter.matches(pattern3);
			break;
		//이미지 정규표현식 validation	
		case 4:
			//입력받은 값이 없을 경우
			if(parameter == ""){
				validation = true;
				break;
			}
			
			//이미지가 1개이상 인경우 | 로 구분
			else if(parameter.contains("\\|")){
				String[] arr = parameter.split("\\|");
				for(int i=0; i<arr.length; i++){
					validation = arr[i].matches(regExp);
					if(validation == false){
						break;
					}
				}
			}else {
				validation = parameter.matches(regExp);
			}
			break;
		case 5:
			validation = parameter.matches(pattern4);
			break;

		}
		return validation;
	}
	
	public String ex_Title(String parameter){
		String[] exTitle = parameter.split("\\|", 5);
		String title = parameter;
		int len = exTitle.length;
		for(; len<6 ;len++){
			title = title + "|";
		}
		return title;
	}
	
}
	

