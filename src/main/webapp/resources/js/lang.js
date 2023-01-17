    var ip = "";
    var hostname = "";
    var city = "";
    var region = "";
    var country = "";
    var loc = "";
    var org = "";
 
    /*$.getJSON("https://ipinfo.io", function(data) {
        ip = data.ip // 접속자 ip
        hostname = data.hostname // 접속자 hostname
        city = data.city // 접속자 도시
        region = data.region // 접속자 지역
        country = data.country // 접속자 국가
        loc = data.loc // 접속 위도, 경도
        org = data.org // ISP (인터넷 서비스 제공사업자)
        
 		if(window.location.hostname == "www.ritzmall.com" || window.location.hostname == "ritzmall.com"){
	 		if(country == "KR"){
	            location.href="https://kr.ritzmall.com/shop/main/index";
	        }else{
	            location.href="https://en.ritzmall.com/shop/main/index";
	        }
 		}else{
 			location.href="/shop/main/index";
 		}
        
    });*/

    location.href="/shop/main/index";
