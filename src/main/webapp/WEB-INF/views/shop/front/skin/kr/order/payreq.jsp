<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title>LG U+ eCredit서비스 결제테스트</title>
<script language="javascript" src="http://xpay.uplus.co.kr/xpay/js/xpay_install.js" charset="euc-kr" type="text/javascript"></script>
<script type="text/javascript">
<!--
/*
 * 상점결제 인증요청후 PAYKEY를 받아서 최종결제 요청.
 */
function doPay_ActiveX(){
    ret = xpay_check(document.getElementById('LGD_PAYINFO'), '${frontOrderSettleVO.CST_PLATFORM}');

    if (ret=="00"){     //ActiveX 로딩 성공
        var LGD_RESPCODE        = dpop.getData('LGD_RESPCODE');       //결과코드
        var LGD_RESPMSG         = dpop.getData('LGD_RESPMSG');        //결과메세지

        if( "0000" == LGD_RESPCODE ) { //인증성공
            var LGD_PAYKEY      = dpop.getData('LGD_PAYKEY');         //LG유플러스 인증KEY
            var msg = "인증결과 : " + LGD_RESPMSG + "\n";
            msg += "LGD_PAYKEY : " + LGD_PAYKEY +"\n\n";
            document.getElementById('LGD_PAYKEY').value = LGD_PAYKEY;
            alert(msg);
            document.getElementById('LGD_PAYINFO').submit();
        } else { //인증실패
            alert("인증이 실패하였습니다. " + LGD_RESPMSG);
            /*
             * 인증실패 화면 처리
             */
        }
    } else {
        alert("LG U+ 전자결제를 위한 플러그인 모듈이 설치되지 않았습니다.");
        /*
         * 인증실패 화면 처리
         */
    }      
}

function isPluginOK(){
	if(hasXpayObject()) 
	{
		alert('LG U+ 전자결제를 위한 XPayClient (Plugin) 이 설치되었습니다. ');
	}else {
		xpayShowInstall();
	}
}
//-->
</script>
</head>

<body onload='javascript:isPluginOK();'>
<form method="post" name ="LGD_PAYINFO"  id="LGD_PAYINFO" action="${ctx}/shop/order/payres">
<table>
    <tr>
        <td>구매자 이름 </td>
        <td>${frontOrderSettleVO.LGD_BUYER}</td>
    </tr>
    <tr>
        <td>구매자 IP </td>
        <td>${frontOrderSettleVO.LGD_BUYERIP}</td>
    </tr>
    <tr>
        <td>구매자 ID </td>
        <td>${frontOrderSettleVO.LGD_BUYERID}</td>
    </tr>    
    <tr>
        <td>상품정보 </td>
        <td>${frontOrderSettleVO.LGD_PRODUCTINFO}</td>
    </tr>
    <tr>
        <td>결제금액 </td>
        <td>${frontOrderSettleVO.LGD_AMOUNT}</td>
    </tr>
    <tr>
        <td>구매자 이메일 </td>
        <td>${frontOrderSettleVO.LGD_BUYEREMAIL}</td>
    </tr>
    <tr>
        <td>주문번호 </td>
        <td>${frontOrderSettleVO.LGD_OID}</td>
    </tr>
    <tr>
        <td colspan="2">* 추가 상세 결제요청 파라미터는 메뉴얼을 참조하시기 바랍니다.</td>
    </tr>
    <tr>
        <td colspan="2"></td>
    </tr>    
    <tr>
        <td colspan="2">
			<input type="button" value="인증요청" onclick="doPay_ActiveX();"/>   
        </td>
    </tr>    
</table>
<br>

<input type="hidden" name="CST_PLATFORM"                id="CST_PLATFORM"		value="${frontOrderSettleVO.CST_PLATFORM}">                   <!-- 테스트, 서비스 구분 -->
<input type="hidden" name="CST_MID"                     id="CST_MID"			value="${frontOrderSettleVO.CST_MID}">                        <!-- 상점아이디 -->
<input type="hidden" name="LGD_MID"                     id="LGD_MID"			value="${frontOrderSettleVO.LGD_MID}">                        <!-- 상점아이디 -->
<input type="hidden" name="LGD_OID"                     id="LGD_OID"			value="${frontOrderSettleVO.LGD_OID}">                        <!-- 주문번호 -->
<input type="hidden" name="LGD_BUYER"                   id="LGD_BUYER"			value="${frontOrderSettleVO.LGD_BUYER}">                      <!-- 구매자 -->
<input type="hidden" name="LGD_PRODUCTINFO"             id="LGD_PRODUCTINFO"	value="${frontOrderSettleVO.LGD_PRODUCTINFO}">                <!-- 상품정보 -->
<input type="hidden" name="LGD_AMOUNT"                  id="LGD_AMOUNT"			value="${frontOrderSettleVO.LGD_AMOUNT}">                     <!-- 결제금액 -->
<input type="hidden" name="LGD_BUYEREMAIL"              id="LGD_BUYEREMAIL"		value="${frontOrderSettleVO.LGD_BUYEREMAIL}">                 <!-- 구매자 이메일 -->
<input type="hidden" name="LGD_CUSTOM_SKIN"             id="LGD_CUSTOM_SKIN"	value="${frontOrderSettleVO.LGD_CUSTOM_SKIN}">                <!-- 결제창 SKIN -->
<input type="hidden" name="LGD_WINDOW_VER"              id="LGD_WINDOW_VER" 	value="${frontOrderSettleVO.LGD_WINDOW_VER}">                 <!-- 결제창 버젼정보 -->
<input type="hidden" name="LGD_CUSTOM_PROCESSTYPE"      id="LGD_CUSTOM_PROCESSTYPE"		value="${frontOrderSettleVO.LGD_CUSTOM_PROCESSTYPE}">         <!-- 트랜잭션 처리방식 -->
<input type="hidden" name="LGD_TIMESTAMP"               id="LGD_TIMESTAMP"		value="${frontOrderSettleVO.LGD_TIMESTAMP}">                  <!-- 타임스탬프 -->
<input type="hidden" name="LGD_HASHDATA"                id="LGD_HASHDATA"		value="${frontOrderSettleVO.LGD_HASHDATA}">                   <!-- MD5 해쉬암호값 -->
<input type="hidden" name="LGD_PAYKEY"                  id="LGD_PAYKEY">   							   <!-- LG유플러스 PAYKEY(인증후 자동셋팅)-->
<input type="hidden" name="LGD_VERSION"         		id="LGD_VERSION"		value="JSP_OpenXPay_2.5">
<input type="hidden" name="LGD_BUYERIP"                 id="LGD_BUYERIP"		value="${frontOrderSettleVO.LGD_BUYERIP}">           			<!-- 구매자IP -->
<input type="hidden" name="LGD_BUYERID"                 id="LGD_BUYERID"		value="${frontOrderSettleVO.LGD_BUYERID}">           			<!-- 구매자ID -->

<!-- 가상계좌(무통장) 결제연동을 하시는 경우  할당/입금 결과를 통보받기 위해 반드시 LGD_CASNOTEURL 정보를 LG 유플러스에 전송해야 합니다 . -->
<input type="hidden" name="LGD_CASNOTEURL"         	id="LGD_CASNOTEURL"		value="${frontOrderSettleVO.LGD_CASNOTEURL}" >                 <!-- 가상계좌 NOTEURL -->

</form>
</body>
<!--  xpay.js는 반드시 body 밑에 두시기 바랍니다. -->
<!--  UTF-8 인코딩 사용 시는 xpay_ub.js 대신 xpay_ub_utf-8.js 을  호출하시기 바랍니다.-->
<script language="javascript" src="${pageContext.request.scheme}://xpay.uplus.co.kr${frontOrderSettleVO.CST_PLATFORM eq 'test' ?(pageContext.request.scheme eq 'https' ? ':7443' : ':7080'):''}/xpay/js/xpay_ub_utf-8.js" type="text/javascript">
</script>
</html>
