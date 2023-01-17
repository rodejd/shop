<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!-- <script language="javascript" src="http://xpay.uplus.co.kr/xpay/js/xpay_install.js" charset="euc-kr" type="text/javascript"></script> -->

<%-- <script language="javascript" src="${pageContext.request.scheme}://xpay.uplus.co.kr${frontOrderSettleVO.CST_PLATFORM eq 'test' ?(pageContext.request.scheme eq 'https' ? ':7443' : ':7080'):''}/xpay/js/xpay_ub_utf-8.js" type="text/javascript"></script> --%>



<!-- 이니시스 표준결제 js -->
  <!--
    연동시 유의 사항!!
    1) 테스트 URL(stgstdpay.inicis.com) - 샘플에 제공된 테스트 MID 전용으로 실제 가맹점 MID 사용 시 에러가 발생 할 수 있습니다.
    2) 상용 URL(stdpay.inicis.com) - 실제 가맹점 MID 로 테스트 및 오픈 시 해당 URL 변경하여 사용합니다.
    3) 가맹점의 URL이 http: 인경우 js URL도 https://stgstdpay.inicis.com/stdjs/INIStdPay.js 로 변경합니다.   
    4) 가맹점에서 사용하는 케릭터셋이 EUC-KR 일 경우 charset="UTF-8"로 UTF-8 일 경우 charset="UTF-8"로 설정합니다.
  -->   
  
  <!-- 상용 JS(가맹점 MID 변경 시 주석 해제, 테스트용 JS 주석 처리 필수!) -->
   <!--script language="javascript" type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script-->
  
  <!-- 테스트 JS(샘플에 제공된 테스트 MID 전용) -->
   <script language="javascript" type="text/javascript" src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
   
<script>
/* 플러그인 설치(확인) */
//StartSmartUpdate();

/*  해당 스크립트는 타브라우져에서 적용이 되지 않습니다.
if( document.Payplus.object == null )
{
    openwin = window.open( "chk_plugin.html", "chk_plugin", "width=420, height=100, top=300, left=300" );
}
*/

/* Payplus Plug-in 실행 */
function  jsf__pay( form )
{
    var RetVal = false;
	
    /* Payplus Plugin 실행 */
    if ( MakePayMessage( form ) == true )
    {
        openwin = window.open( "proc_win.html", "proc_win", "width=449, height=209, top=300, left=300" );
        RetVal = true ;
    }
    
    else
    {
        /*  res_cd와 res_msg변수에 해당 오류코드와 오류메시지가 설정됩니다.
            ex) 고객이 Payplus Plugin에서 취소 버튼 클릭시 res_cd=3001, res_msg=사용자 취소
            값이 설정됩니다.
        */
        res_cd  = document.order_info.res_cd.value ;
        res_msg = document.order_info.res_msg.value ;

    }

    return RetVal ;
}

// Payplus Plug-in 설치 안내 
function init_pay_button()
{
    if ((navigator.userAgent.indexOf('MSIE') > 0) || (navigator.userAgent.indexOf('Trident/7.0') > 0))
    {
        try
        {
            if( document.Payplus.object == null )
            {
                document.getElementById("display_setup_message").style.display = "block" ;
            }
            else{
                document.getElementById("display_pay_button").style.display = "block" ;
            }
        }
        catch (e)
        {
            document.getElementById("display_setup_message").style.display = "block" ;
        }
    }
    else
    {
        try
        {
            if( Payplus == null )
            {
                document.getElementById("display_setup_message").style.display = "block" ;
            }
            else{
                document.getElementById("display_pay_button").style.display = "block" ;
            }
        }
        catch (e)
        {
            document.getElementById("display_setup_message").style.display = "block" ;
        }
    }
}

/* 주문번호 생성 예제 */
function init_orderid()
{
    var today = new Date();
    var year  = today.getFullYear();
    var month = today.getMonth() + 1;
    var date  = today.getDate();
    var time  = today.getTime();

    if(parseInt(month) < 10) {
        month = "0" + month;
    }

    if(parseInt(date) < 10) {
        date = "0" + date;
    }

    var order_idxx = "TEST" + year + "" + month + "" + date + "" + time;

    document.order_info.ordr_idxx.value = order_idxx;

    /*
     * 인터넷 익스플로러와 파이어폭스(사파리, 크롬.. 등등)는 javascript 파싱법이 틀리기 때문에 object 가 인식 전에 실행 되는 문제
     * 기존에는 onload 부분에 추가를 했지만 setTimeout 부분에 추가
     * setTimeout 300의 의미는 플러그인 인식속도에 따른 여유시간 설정
     * - 20101018 -
     */
    setTimeout("init_pay_button();",300);
}

/* onLoad 이벤트 시 Payplus Plug-in이 실행되도록 구성하시려면 다음의 구문을 onLoad 이벤트에 넣어주시기 바랍니다. */
function onload_pay()
{
     if( jsf__pay(document.order_info) )
        document.order_info.submit();
}
</script>
<script type="text/javascript">
function submitSettleForm()
{   
   alert("submitSettleForm");
   var settlek = "${fronOrderVO.settlekind}";
   $("#frmSettle").attr("target","");
   var fm = document.frmSettle;

   if (!chkForm(fm)) return;
   /*** 주문필수정보 체크 ***/
   if (!fm.nameOrder.value) return;
   if (!fm.ordno.value) return;

   if (document.getElementById('avoidDblPay')) document.getElementById('avoidDblPay').innerHTML = "--- 현재 결제처리중입니다. 잠시만 기다려주세요. ---";

   ${frontOrderVO.submitStr}
} 
</script>

<!-- 모바일 결제 시 필요한 스크립트 -->
<script>
function setOid()
{
    document.ini.P_OID.value = ""+"${frontOrderVO.ordno}";
    if('${settlekind}' == 'o'){
       $("#p_next").val('');
       $("#p_noti").val('http://localhost/mshop/order/order_end_noti.jsp');
       $("#p_return").val('http://localhost/mshop/order/order_end.jsp?ordno=${ordno}&bank=yoon');
       $("#p_reserved").val('apprun_check=Y&bank_receipt=N');
       $("#p_char").val('utf8');
    }
}

function on_web()
{
   /* var defCharSet = document.charset;
   alert(defCharSet); */
   var order_form = document.ini;
   var paymethod = order_form.paymethod.value;
   var wallet = window.open("", "BTPG_WALLET", features);

   if (wallet == null) 
   {
      if ((webbrowser.indexOf("Windows NT 5.1")!=-1) && (webbrowser.indexOf("SV1")!=-1)) 
      {    // Windows XP Service Pack 2
         alert("팝업이 차단되었습니다. 브라우저의 상단 노란색 [알림 표시줄]을 클릭하신 후 팝업창 허용을 선택하여 주세요.");
      } 
      else 
      {
         alert("팝업이 차단되었습니다.");
      }
      return false;
   }
   
   order_form.target = "BTPG_WALLET";
   order_form.action = "https://mobile.inicis.com/smart/" + paymethod + "/";

   order_form.submit();
   }

   function onSubmit()
   {
      var order_form = document.ini;
      var inipaymobile_type = order_form.inipaymobile_type.value;
     if( inipaymobile_type == "web" ){
         return on_web();
     }
   }

</script>

<style>
#orderbox {
	border: 5px solid #F3F3F3;
	padding: 5px 10px;
}

#orderbox table th {
	width: 100;
}
</style>
<header class="page-header page-header-banner" style="background-image:url(${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/visual_payment.jpg);">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">결제하기</h1>
            <ol class="breadcrumb page-breadcrumb">
                <li><a href="#">Home</a></li>
            <li><a href="#">결제하기</a></li>
            </ol>
        </div>
    </div>
</header>
<c:set var="gopaymethod" value=""></c:set>

<!-- 서브 컨텐츠 -->
<div id="content-area" class="container x-settle">
   <!-- 로케이션 -->
   <!-- 컨텐츠 -->
   <div class="content">
      <!-- 주문내역 -->
      <div class="sub-tit-wrap">
         <h3 class="sub-title mT0"><span class="dot-yellow-s">주문내역</span></h3>
      </div>
      
      <jsp:include page="../proc/orderitem.jsp"></jsp:include>
      
      <c:set var="gopaymethod" value=""></c:set>
      
      <c:if test="${frontOrderVO.settlekind eq 'c' }">
      <c:set var="gopaymethod" value="Card"></c:set>
      </c:if>
      
      <c:if test="${frontOrderVO.settlekind eq 'o' }">
      <c:set var="gopaymethod" value="DirectBank"></c:set>
      </c:if>
      
      <c:if test="${frontOrderVO.settlekind eq 'h' }">
      <c:set var="gopaymethod" value="HPP"></c:set>
      </c:if>
      <form name="_payres-form" id="_payres-form" method="post" action="${ctx }/shop/order/payres" >
         <input type="hidden" name="dbChk" value="false">
         <input type="hidden" name="ordno" value="${frontOrderVO.ordno }">
         <input type=hidden name="goodsnm" value="${frontOrderVO.goodsnm }"/>
         <input type=hidden name="goodsnmKR" value="${frontOrderVO.goodsnmKR }"/>
         <input type=hidden name="goodsnmCN" value="${frontOrderVO.goodsnmCN }"/>
         <input type="hidden" name="item_apply_coupon" value="${frontOrderVO.item_apply_coupon }"> 
      
         <input type="hidden" name="nameOrder" value="${frontOrderVO.nameOrder }">
         <input type="hidden" name="phoneOrder" value="${frontOrderVO.phoneOrder_0 }">
         <input type="hidden" name="mobileOrder" value="${frontOrderVO.mobileOrder_0 }">
         <input type="hidden" name="email" value="${frontOrderVO.email }">
         <input type="hidden" name="nameReceiver" value="${frontOrderVO.nameReceiver }">
         <input type="hidden" name="phoneReceiver" value="${frontOrderVO.phoneReceiver_0 }">
         <input type="hidden" name="mobileReceiver" value="${frontOrderVO.mobileReceiver_0 }">
         <input type="hidden" name="zipcode" value="${frontOrderVO.zipcode }">
         <input type="hidden" name="address" value="${frontOrderVO.address }">
         <input type="hidden" name="address_sub" value="${frontOrderVO.address_sub }">
         <input type="hidden" name="memo" value="${frontOrderVO.memo }">
         <input type="hidden" name="emoney_max" value="${frontOrderVO.emoney_max }">
         <input type="hidden" name="escrow" value="${frontOrderVO.escrow }">
         <input type="hidden" name="deliprice" value="${frontOrderVO.deliprice }">
         <input type="hidden" name="addDelivery" value="${frontOrderVO.addDelivery }">
         <input type="hidden" name="sellerAddDelivery" value="${frontOrderVO.sellerAddDelivery }">
         <input type="hidden" name="coupon" value="${frontOrderVO.coupon }">
         <input type="hidden" name="coupon_emoney" value="${frontOrderVO.coupon_emoney }">
         <input type="hidden" name="coupon_sno" value="${frontOrderVO.couponSno }">      <!-- couponSno 추가 -->
         
         <input type="hidden" name="emoney" value="${frontOrderVO.emoney }">
         <c:forEach items="${frontOrderVO.apply_coupon }" var="apply_coupon">
         <input type="hidden" name="apply_coupon" value="${ apply_coupon}"> 
         </c:forEach>
         
         <input type="hidden" name="deliverycode" value="${deliverycode}">
         <input type="hidden" name="inpk_ordno" value="${inpk_ordno }">
         <input type="hidden" name="dcprice" value="${frontOrderVO.dcprice }">
         <input type="hidden" name="dc" value="${frontOrderVO.dc }">
         <input type="hidden" name="priceSum" value="${frontOrderVO.priceSum }">
         <input type="hidden" name="settlekind" value="${frontOrderVO.settlekind }">
         <input type="hidden" name="emailRecceiver" value="${frontOrderVO.emailRecceiver }">
         <input type="hidden" name="CST_PLATFORM"                id="CST_PLATFORM"      value="${frontOrderVO.settleVO.CST_PLATFORM}">                   <!-- 테스트, 서비스 구분 -->
         <input type="hidden" name="CST_MID"                     id="CST_MID"         value="${frontOrderVO.settleVO.CST_MID}">                        <!-- 상점아이디 -->
         <input type="hidden" name="LGD_MID"                     id="LGD_MID"         value="${frontOrderVO.settleVO.LGD_MID}">                        <!-- 상점아이디 -->
         <input type="hidden" name="LGD_OID"                     id="LGD_OID"         value="${frontOrderVO.settleVO.LGD_OID}">                        <!-- 주문번호 -->
         <input type="hidden" name="LGD_BUYER"                   id="LGD_BUYER"         value="${frontOrderVO.settleVO.LGD_BUYER}">                      <!-- 구매자 -->
         <input type="hidden" name="LGD_PRODUCTINFO"             id="LGD_PRODUCTINFO"   value="${frontOrderVO.settleVO.LGD_PRODUCTINFO}">                <!-- 상품정보 -->
         <input type="hidden" name="LGD_AMOUNT"                  id="LGD_AMOUNT"         value="${frontOrderVO.settleVO.LGD_AMOUNT}">                     <!-- 결제금액 -->
         <input type="hidden" name="LGD_BUYEREMAIL"              id="LGD_BUYEREMAIL"      value="${frontOrderVO.settleVO.LGD_BUYEREMAIL}">                 <!-- 구매자 이메일 -->
         <input type="hidden" name="LGD_CUSTOM_SKIN"             id="LGD_CUSTOM_SKIN"   value="${frontOrderVO.settleVO.LGD_CUSTOM_SKIN}">                <!-- 결제창 SKIN -->
         <input type="hidden" name="LGD_WINDOW_VER"              id="LGD_WINDOW_VER"    value="${frontOrderVO.settleVO.LGD_WINDOW_VER}">                 <!-- 결제창 버젼정보 -->
         <input type="hidden" name="LGD_TIMESTAMP"               id="LGD_TIMESTAMP"      value="${frontOrderVO.settleVO.LGD_TIMESTAMP}">                  <!-- 타임스탬프 -->
         <input type="hidden" name="LGD_HASHDATA"                id="LGD_HASHDATA"      value="${frontOrderVO.settleVO.LGD_HASHDATA}">                   <!-- MD5 해쉬암호값 -->
         <input type="hidden" name="LGD_BUYERIP"                 id="LGD_BUYERIP"      value="${frontOrderVO.settleVO.LGD_BUYERIP}">                    <!-- 구매자IP -->
         <input type="hidden" name="LGD_BUYERID"                 id="LGD_BUYERID"      value="${frontOrderVO.settleVO.LGD_BUYERID}">                    <!-- 구매자ID -->
         <input type="hidden" name="LGD_CUSTOM_PROCESSTYPE"      id="LGD_CUSTOM_PROCESSTYPE"      value="${frontOrderVO.settleVO.LGD_CUSTOM_PROCESSTYPE}">         <!-- 트랜잭션 처리방식 -->
         <input type="hidden" name="LGD_PAYKEY"                  id="LGD_PAYKEY"><!-- LG유플러스 PAYKEY(인증후 자동셋팅)-->
         <input type="hidden" name="LGD_VERSION"               id="LGD_VERSION"      value="JSP_OpenXPay_2.5">
         
         <!-- 가상계좌(무통장) 결제연동을 하시는 경우  할당/입금 결과를 통보받기 위해 반드시 LGD_CASNOTEURL 정보를 LG 유플러스에 전송해야 합니다 . -->
         <input type="hidden" name="LGD_CASNOTEURL"            id="LGD_CASNOTEURL"      value="${frontOrderVO.settleVO.LGD_CASNOTEURL}" >                 <!-- 가상계좌 NOTEURL -->
               
         <br><br>
         <!-- 결제금액 -->
         <h3 class="sub-title"><span class="dot-yellow-s">결제금액</span></h3>
         <div class="box">
            <table summary="" class="table table table-shopping-cart">
               <colgroup>
                  <col style="width:25%;" />
                  <col style="width:25%;" />
                  <col style="width:25%;" />
                  <col />
               </colgroup>
               <thead>
                  <tr>
                     <th scope="col">금액합계</th>
                     <th scope="col">쿠폰할인</th>
                     <th scope="col">적립금 사용</th>
                     <th scope="col">최종 결제 금액</th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td>
                        <p>상품금액 : <fmt:formatNumber value="${frontOrderVO.priceSum}" pattern="#,###.##"/> 원</p>
                        <%-- <p>배송비 : 
                           <c:choose>
                              <c:when test="${frontOrderVO.settle_deliprice eq '0'}">
                                 무료
                              </c:when>
                              <c:otherwise>
                                 <span id="paper_delivery">
                                    <fmt:formatNumber pattern="#,###">${frontOrderVO.settle_deliprice}</fmt:formatNumber>
                                 </span> 원
                              </c:otherwise>
                           
                           </c:choose>
                        </p> --%>
                        
                        <%-- 배송비 표시화면 include --%>
                        <%@ include file="order_delivery.jsp" %>
                        <c:if test="${0 < frontOrderVO.dc}">
                           <p>회원할인율 : <span id="paper_goodsprice"><fmt:formatNumber value="${frontOrderVO.dc}" pattern="#,###"/>% (-<fmt:formatNumber value="${frontOrderVO.dcprice }" pattern="#,###"/></span> 원)</p>
                           <p style="font-size: 10; color: red;">상품가격에만 회원할인율이 적용됩니다.</p>
                        </c:if>
                     </td>
                     <td class="text-center">
                        - <fmt:formatNumber value="${frontOrderVO.settle_coupon}" pattern="#,###.##"/> 원
                        <div class="_use-coupon">
                           <c:if test="${not empty frontOrderVO.usedCouponInfoList }">
                              <c:set var="couponCd" value=""/>
                              <c:forEach var="couponList" items="${frontOrderVO.usedCouponInfoList}" varStatus="status" >
                                 <c:if test="${status.current.couponcd != couponCd}">
                                    <c:set var="couponCd" value="${status.current.couponcd}"/>
                                    <div style="margin-top: 10px;">
                                       <c:choose>
                                          <c:when test="${couponList.coupontype == 0}">
                                             [전체쿠폰]
                                          </c:when>
                                          <c:otherwise>
                                             <c:if test="${'' != couponList.category}">
                                                [카테고리]
                                             </c:if>
                                             <c:if test="${'' == couponList.category}">
                                                [상품쿠폰]
                                             </c:if>
                                          </c:otherwise>
                                       </c:choose>
                                       ${couponList.coupon}
                                       <!-- 정액쿠폰 / 정률쿠폰 -->
                                       <c:if test="${fn:indexOf(couponList.price, '%') == -1}">
                                          <fmt:formatNumber pattern="#,###">${couponList.price}</fmt:formatNumber> 원
                                       </c:if> 
                                       <c:if test="${fn:indexOf(couponList.price, '%') > -1}">
                                          ${couponList.price}
                                       </c:if>
                                       <!-- 할인 / 적립 구분 -->
                                       <c:choose>
                                          <c:when test="${couponList.couponAbi == 0}">
                                             할인
                                          </c:when>
                                          <c:otherwise>
                                             적립
                                          </c:otherwise>
                                       </c:choose>
                                    </div>
                                 </c:if>
                              </c:forEach>
                           </c:if>
                           <p class="_used-price-coupon">
                           </p>
                        </div>
                     </td>
                     <td class="text-center">
                        - <fmt:formatNumber value="${frontOrderVO.settle_emoney}" pattern="#,###.##"/> 원
                     </td>
                     <td class="text-center">
                        <em class="total-price lead">
                           <%-- 2017-08-29 : 추가배송비 내용 추가 --%>
                           <fmt:formatNumber value="${frontOrderVO.total_price}" pattern="#,###.##"/> 원
                        </em>
                     </td>
                  </tr>
               </tbody>
            </table>
         </div>
         <br><br>
         <!-- 주문자 배송 정보 -->
         <h3 class="sub-title"><span class="dot-yellow-s">주문자 및 배송정보</span></h3>
         <div class="box">
            <table summary="" class="table table table-shopping-cart">
               
               <colgroup>
                  <col style="width:50%;" />
                  <col />
               </colgroup>
               <thead>
                  <tr>
                     <th scope="col">주문자정보</th>
                     <th scope="col">배송정보</th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td class="left">
                        <p>${order_info_1}</p>
                        <p>${order_info_2 }</p>
                     </td>
                     <td class="left">
                        <p>${receiver_info_1 }</p>
                        <p>${receiver_info_2 }</p>
                        <p>${memo }</p>
                     </td>
                  </tr>
               </tbody>
            </table>
         </div>
         <input type="hidden" name="settleprice" value = "${priceSum}">
         <input type="hidden" name="reserveSum" value="${reserveSum }">         
         <br><br>
         <!-- 버튼영역 -->
         <nav class="text-center">
            <div class="over-wrap btn-wrap center">
               <a href="javascript:history.back();" class="btn btn-size-02 bg-black w200"><span class="btn btn-warning">이전으로</span></a>
               <a href="javascript:$.noop();" class="btn btn-size-02 bg-yellow w200" id="charge"><span class="btn btn-primary">결제하기</span></a>
            </div>
         </nav>
      </form>

   </div>
</div>



<form id="SendPayForm_id" name="" method="POST" >
   <!-- 필수!! -->
   <input type="hidden" style="width:100%;" name="version" value="1.0" >
   
   <!--  가맹점 ID(가맹점 수정후 고정) -->
   <input type="hidden" style="width:100%;" name="mid" value="${frontOrderVO.mid}" >
   
   <input type="hidden" style="width:100%;" name="goodname" value="${frontOrderVO.settleVO.LGD_PRODUCTINFO}" >
   <input type="hidden" style="width:100%;" name="oid" value="${frontOrderVO.oid}" >
   <%-- <input type="hidden" style="width:100%;" name="price" value="${frontOrderVO.priceSum-frontOrderVO.emoney-frontOrderVO.dcprice-frontOrderVO.coupon+frontOrderVO.deliprice }" > --%>
   <input type="hidden" style="width:100%;" name="price" value="1400" >
   
   <input type="hidden" style="width:100%;" name="currency" value="WON" >   
   <input type="hidden" style="width:100%;" name="buyername" value="${frontOrderVO.nameOrder }" >
   <input type="hidden" style="width:100%;" name="buyertel" value="${frontOrderVO.mobileOrder[0]}-${frontOrderVO.mobileOrder[1]}-${frontOrderVO.mobileOrder[2]}" >
   <input type="hidden" style="width:100%;" name="buyeremail" value="${frontOrderVO.email }" >
   <input type="hidden" style="width:100%;" name="timestamp" value="${frontOrderVO.timestamp}" >
   <input type="hidden" style="width:100%;" name="signature" value="${frontOrderVO.signature}" >
   
   <input type="hidden" style="width:100%;" name="returnUrl" value="${frontOrderVO.siteDomain}/shop/order/inipayreturn" >
   <input type="hidden" style="width:100%;" name="closeUrl" value="${frontOrderVO.siteDomain}/shop/order/inipayclose" >
   <input type="hidden" style="width:100%;" name="popupUrl" value="${frontOrderVO.siteDomain}/shop/order/inipaypopup" >
   
   <input type="hidden"  name="mKey" value="${frontOrderVO.mKey}" >
   
   
   <!-- 기본 옵션 -->
   <input type="hidden" style="width:100%;" name="gopaymethod" value="" >
   <!-- <input type="hidden" style="width:100%;" name="offerPeriod" value="20160101-20161231" > -->
   <input type="hidden" style="width:100%;" name="acceptmethod" value="CARDPOINT:HPP(1):no_receipt:va_receipt:vbanknoreg(0):below1000" >


   <!-- 표시 옵션 -->
   <input type="hidden" style="width:100%;" name="languageView" value="" >
   <input type="hidden" style="width:100%;" name="charset" value="" >
   <input type="hidden" style="width:100%;" name="payViewType" value="popup" >

   
   
   <!-- 결제 수단별 옵션 -->
   <input type="hidden" style="width:100%;" name="quotabase" value="${frontOrderVO.cardQuotaBase}" >
   <input type="hidden" style="width:100%;" name="ini_cardcode" value="" >
   <input type="hidden" style="width:100%;" name="ansim_quota" value="" >
   <input type="hidden" style="width:100%;" name="vbankRegNo" value="" >
   
   
   <!-- 추가 옵션 -->
   <input type="hidden" style="width:100%;" name="merchantData" value="" >
   

</form>




<!-- 모바일결제 -->
<script>

 $(document).ready(function(){
   setOid();
}); 

</script>

<form id="form1" name="ini" method="post" action="" accept-charset="euc-kr">
   <input type="hidden" name="inipaymobile_type" id="select" value="web">
   
   
   <input type="hidden" name="P_GOODS" value="${frontOrderVO.goodsnmKR }"  />
   <input type="hidden" name="P_AMT" value="${priceSum-emoney-dcprice-coupon+deliprice }"  />
   <input type="hidden" name="P_UNAME" value="${frontOrderVO.nameOrder }" />
   <input type="hidden" name="P_MNAME" value="Xmall"  />
   <input type="hidden" name="P_MOBILE"  value="${frontOrderVO.mobileOrder_0 }"  />
   <input type="hidden" name="P_EMAIL" value="${frontOrderVO.email }"  />
   <input type="hidden" name="paymethod" value="${gopaymethod }"   />
   <input type="hidden" name="P_MID" value="${frontOrderVO.mid}"> 
     
   <!-- <input type=hidden name="P_NEXT_URL" value="https://mobile.inicis.com/smart/testmall/next_url_test.php">
   <input type=hidden name="P_NOTI_URL" value="http://ts.inicis.com/~esjeong/mobile_rnoti/rnoti.php"> -->
   
   
   
   <!-- 동적추가,수정 -->
   <input type=hidden name="P_NEXT_URL" id="p_next" value="http://localhost/mshop/order/order_end_next.jsp">
   <input type="hidden" name="P_OID" id="hiddenfield2" />
   <input type=hidden name="P_NOTI_URL" id="p_noti" value="">
   <input type="hidden" name="P_RETURN_URL" id="p_return" value="">
   <input type="hidden" name="P_RESERVED" id="p_reserved" value="twotrs_isp=Y&block_isp=Y&twotrs_isp_noti=N&apprun_check=Y">
   <input type="hidden" name="P_CHARSET" id="p_char" value="" />
   
   
   
   <input type=hidden name="P_HPP_METHOD" value="1">
   

</form> 

<script>
// iniPay
$("#charge").on("click", function(){
	//시그니쳐키 조회
	ajaxCallJson("/shop/order/iniSignature.dojson", {
		'timestamp' : $("input[name='timestamp']").val(),
		'oid' : $("input[name='oid']").val(),
		'price' : $("input[name='price']").val()
	}, function(data) {
		$("input[name='signature']").val(data.signature);
	}, function(e) {
		console.log(e);
	});
   INIStdPay.pay('SendPayForm_id');
});

function getIniSignature(){
	
}

</script>