/**
 * Supported Browser : MSIE, Chrome , FireFox
 * 
 * Object       : jquery.ajax.js.js
 * Description  : js 파일 설명을 기술합니다.
 * Author       : bhLee
 * Since        : 2012. 11. 9.
 * Version      : 1.0
 * 
 * Modification Information
 *     since          author              description
 *  ===========    =============    ===========================
 *  bhLee     2012. 11. 9.     최초 생성
 */
(function($) {
    
    
    var defaultTargetAreaID = "changeDiv";
    var defaultAjaxErrorMessage = "일시적인 시스템장애로 요청하신 페이지에 접근하실 수 없습니다.\n관련 문의사항은 담당자에게 문의해 주시기 바랍니다.";
    var defaultAjax404ErrorMessage = "페이지를 찾을수 없습니다.";
    var defaultContentType = "application/x-www-form-urlencoded;charset=UTF-8";
    //var defaultContentType = "application/x-www-form-urlencoded;charset=EUC-KR";
    
    //----------------------------------------------------------------------------------
    // ajaxCall Object 생성
    //----------------------------------------------------------------------------------
    $.ajaxCall = {

        //----------------------------------------------------------------------------------
        // post + html 방식
        //----------------------------------------------------------------------------------    
        post: function(url, formName, targetAreaID, successHandler, errorHandler, completeHandler) {
            if (!(targetAreaID == undefined || targetAreaID == null || targetAreaID == "")) {
                defaultTargetAreaID = targetAreaID;
            }
            if(formName == 'string') {
            	var ajaxParam = $("#" + formName).serialize();
            } else {
            	var ajaxParam = formName;
            }
            $.ajaxCall._ajaxHtml(url, ajaxParam, defaultTargetAreaID, successHandler, errorHandler, completeHandler);
        },
                
        //----------------------------------------------------------------------------------
        // get + html 방식
        //----------------------------------------------------------------------------------    
        get : function(urlData, targetAreaID,successHandler, errorHandler, completeHandler) {
            if (!(targetAreaID == undefined || targetAreaID == null || targetAreaID == "")) {
                defaultTargetAreaID = targetAreaID;
            }
            
            var idx = urlData.indexOf('?');
            var url = '';
            var ajaxParam = '';
            if(idx > 0){
                ajaxParam = urlData.substring(idx+1, urlData.length);        
                url = urlData.substring(0, idx);        
            }else{
                ajaxParam = '';
                url = urlData;
            }
            
            $.ajaxCall._ajaxHtml(url, ajaxParam, defaultTargetAreaID,successHandler,errorHandler, completeHandler);
        },

        //----------------------------------------------------------------------------------
        // post + json 방식
        //----------------------------------------------------------------------------------    
        jsonPost: function(url, formName, callback, exceptionCallback) {
            var ajaxParam = $("#" + formName).serialize();
            
            $.ajaxCall._ajaxJson(url, ajaxParam, callback, exceptionCallback);
        },
        
        //----------------------------------------------------------------------------------
        // post + json 방식
        //----------------------------------------------------------------------------------    
        json: function(url, data, callback, exceptionCallback) {
            $.ajaxCall._ajaxJson(url, data, callback, exceptionCallback);
        },

        //----------------------------------------------------------------------------------
        // html 데이터방식의 실질 함수
        //----------------------------------------------------------------------------------    
        _ajaxHtml : function( url , data, targetAreaID, successHandler, errorHandler, completeHandler){
        	if(/\/shop\/[a-zA-Z]+/.test(url)){
        		url = ctx + url;
        	}
        	
            $.ajax({
                type            :   "POST",
                url             :   url ,
                dataType        :   "html",
                contentType     :   defaultContentType,
                data            :   data,   
                success         :   successHandler || function(htmlString){
                                        //----------------------------------------------------------------------------------
                                        // ajax html 방식은 에러시 에러페이지가 그대로 로딩됨
                                        //----------------------------------------------------------------------------------                                                
                                        $("#" + targetAreaID).empty().append(htmlString);
                                    },
                error           :   function(xhr, ajaxOptions, thrownError){
                						
                						//var test_obj = xhr.responseText.replace(/\\'/g, "'");
                						//alert('test_obj:'+test_obj);
                						//alert("test:"+typeof(JSON.parse(test_obj)));
                						
                			 			//alert(xhr.responseText);
                			 			//alert(typeof(xhr.responseText));
                			         	//console.log("xhr:"+JSON.stringify(xhr));
                			           	//console.log("ajaxOptions:"+JSON.stringify(ajaxOptions));
                			           	//console.log("thrownError:"+JSON.stringify(thrownError));
                						//사용자 정의 Error 처리 - 개별처리 할것
                						if (!(errorHandler == undefined || errorHandler == "" || errorHandler == null)) {
                							
                							var error_result = null;
                							
                							try{
                    							error_result = JSON.parse(xhr.responseText);
                    							
                    							//errorHandler 가 함수일 경우
                                            	if(typeof(errorHandler) == 'function'){
                                            		errorHandler(error_result);	
                                            	// errorHandler 명이 string 으로 왔을경우
                                            	}else{
                                            		window[errorHandler](error_result);
                                            	}
                    						}catch(e){
                    							
                    							// 404 error 시 JSON.parse 오류남.
                        						if( '404' == xhr.status ){
                        							alert(defaultAjax404ErrorMessage);
                        						} else {
                        							alert(defaultAjaxErrorMessage);
                        						}
                    							
                    							return false;
                    						}
                						//default Error 처리
                						}else{
                							alert(defaultAjaxErrorMessage);
                						}
                						
                                    },
                complete        :   completeHandler || function(){
                }
            });
        },
                
        //----------------------------------------------------------------------------------
        // json 데이터방식의 실질 함수
        //----------------------------------------------------------------------------------    
        _ajaxJson : function( url, data, callback, exceptionCallback,completeHandler  ){
        	if(/\/shop\/[a-zA-Z]+/.test(url)){
        		url = ctx + url;
        	}
        	
            $.ajax({
                type            :   "POST",
                url             :   url ,
                dataType        :   "json",
                contentType     :   defaultContentType,
                data            :   data,   
                success         :   function(response,status,request){
                							var result = JSON.parse(request.responseText);
	                                        // callback 함수가 있을경우
	                                        if (!(callback == undefined || callback == "" || callback == null)) {
	                                            //callback 이 함수일 경우
	                                        	if(typeof(callback) == 'function'){
	                                        		callback(result);
	                                        	//callback 명이 string 으로 왔을경우
	                                        	}else{ 
	                                        		window[callback](result);
	                                        	}
	                                        //callback 함수가 없을경우	
	                                        }else{
	                                            //alert(result.page_Num);
	                                        }
                                    },
                error           :  function(xhr, ajaxOptions, thrownError){
                	
                						var error_result = null;
                	
					                	try{
											error_result = JSON.parse(xhr.responseText);
											
											 //사용자 정의 Error 처리 - 개별처리 할것
	                						if (!(exceptionCallback == undefined || exceptionCallback == "" || exceptionCallback == null)) {
	                							//exceptionCallback 이 함수일 경우
	                                        	if(typeof(exceptionCallback) == 'function'){
	                                        		exceptionCallback(error_result)
	                                        	//callback 명이 string 으로 왔을경우 	
	                                        	}else{
	                                        		window[exceptionCallback](error_result);
	                                        	}                							
	                                        //default Error 처리    	
	                                        }else{
	                                        	alert(error_result.exceptionMessage);
	                                        }
										}catch(e){
											// 404 error 시 JSON.parse 오류남.
											if( '404' == xhr.status ){
												alert(defaultAjax404ErrorMessage);
											} else {
												alert(defaultAjaxErrorMessage);
											}
										}
                                       
                },
                complete        :   completeHandler || function(){
                }
            });
        }
        
    },
    
    //----------------------------------------------------------------------------------
    // wrapper 함수들
    //----------------------------------------------------------------------------------    
    ajaxCallPost = function(url, formName, targetAreaID, successHandler, errorHandler, completeHandler) {
        $.ajaxCall.post(url, formName, targetAreaID, successHandler, errorHandler, completeHandler);
    };
    
    ajaxCallGet = function(urlData, targetAreaID,successHandler,errorHandler, completeHandler) {
        $.ajaxCall.get(urlData, targetAreaID,successHandler,errorHandler, completeHandler);
    };
    
    ajaxCallJsonPost = function(url, formName, callback, exceptionCallback) {
        $.ajaxCall.jsonPost(url, formName, callback, exceptionCallback);
    };
    
    ajaxCallJson = function(url, data, callback, exceptionCallback) {
        $.ajaxCall.json(url, data, callback, exceptionCallback);
    };
    
    //----------------------------------------------------------------------------------
    // private 함수들
    //----------------------------------------------------------------------------------
    //function makeAjaxErrorMessage (defaultAjaxErrorMessage, xhr, ajaxOptions, thrownError){
    //   return defaultAjaxErrorMessage;
    //}
    
    
})(jQuery);