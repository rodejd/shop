/*** TAXSUGI CALCULATE METHOD (TCM) ***/
TCM = {
	init_set : function ()
	{
		this.fobj = document.forms['form'];
		this.fobj['SupComp'].value = param['compName'];
		this.fobj['SupEmployer'].value = param['ceoName'];
		this.fobj['SupNo'].value = param['compSerial'];
		this.fobj['SupCond'].value = param['service'];
		this.fobj['SupItem'].value = param['item'];
		this.fobj['SupEmail'].value = param['email'];
		this.fobj['SupAddr'].value = param['address'];
		this.fobj['SupPhone'].value = param['smsAdmin'];
		this.layout(this.fobj['TaxType'][0]);
	},

	layout: function (cobj)
	{
		if (cobj.value == 'VAT'){
			/** 세액총액, 품목별세액, 합계금액 계산 **/
			moaTot = taxTot = 0;
			moaTot = extUncomma(this.fobj.TotalMoa.value) * 1;
			for (j = 1; j <= 4; j++){
				if (this.fobj.chkCal[2].checked && this.fobj['LinTax'+j].value);
				else {
					moa = extUncomma(this.fobj['LinMoa'+j].value);
					if (moa != 0) this.fobj['LinTax'+j].value = extComma((Math.round(moa/10 - 0.5)) + "");
				}
				taxTot += extUncomma(this.fobj['LinTax'+j].value) * 1;
			}
			this.fobj.TotalTax.value = extComma(taxTot + "");
			if (taxTot != 0) this.fobj.MoaTax.value = extComma((moaTot+taxTot) + "");

			_ID('isTax').style.visibility="visible";
			for (j = 1; j <= 4; j++) _ID('isTax_l'+j).style.visibility="visible";
			_ID('display_title1').style.display = '';
			_ID('display_title2').style.display = 'none';
			_ID('display_title3').style.display = '';
			_ID('display_title4').style.display = 'none';
		}
		else if (cobj.value == 'FRE' || cobj.value == 'RCP'){
			if ( (this.fobj.TotalMoa.value * 1) == 0 ) this.fobj.MoaTax.value = "";
			else this.fobj.MoaTax.value = this.fobj.TotalMoa.value;

			this.fobj.TotalTax.value = "";
			for (j = 1; j <= 4; j++) this.fobj['LinTax'+j].value="";

			_ID('isTax').style.visibility="hidden";
			for (j = 1; j <= 4; j++) _ID('isTax_l'+j).style.visibility="hidden";
			if (cobj.value == 'FRE'){
				_ID('display_title1').style.display = '';
				_ID('display_title2').style.display = 'none';
				_ID('display_title3').style.display = '';
				_ID('display_title4').style.display = 'none';
			}
			else {
				_ID('display_title1').style.display = 'none';
				_ID('display_title2').style.display = '';
				_ID('display_title3').style.display = 'none';
				_ID('display_title4').style.display = '';
			}
		}
		for (j = 0; j < 4; j++) if (this.fobj.chkCal[j].checked) this.cal_state(this.fobj.chkCal[j].value, false);
	},

	cal_state: function (o_value, init)
	{
		if (o_value == 0){
			this.fobj.TotalMoa.readOnly = true;
			this.fobj.TotalTax.readOnly = true;
			this.fobj.t_price.readOnly = true;
			this.fobj.MoaTax.readOnly = true;

			for (j = 1; j <= 4; j++){
				this.fobj['LinQty'+j].readOnly = false;
				this.fobj['LinPri'+j].readOnly = false;
				this.fobj['LinMoa'+j].readOnly = true;
				this.fobj['LinTax'+j].readOnly = true;
			}
			if (init != false) this.ClearDoc();
			this.fobj.count_item.value=1;
			this.fobj.t_price.value="";
			this.fobj.LinQty1.focus();
		}
		else if (o_value == 1){
			this.fobj.TotalMoa.readOnly = true;
			this.fobj.TotalTax.readOnly = true;
			this.fobj.t_price.readOnly = true;
			this.fobj.MoaTax.readOnly = true;

			for (j = 1; j <= 4; j++){
				this.fobj['LinQty'+j].readOnly = true;
				this.fobj['LinPri'+j].readOnly = true;
				this.fobj['LinMoa'+j].readOnly = false;
				this.fobj['LinTax'+j].readOnly = true;
			}
			if (init != false) this.ClearDoc();
			this.fobj.count_item.value=1;
			this.fobj.t_price.value="";
			this.fobj.LinMoa1.focus();
		}
		else if (o_value == 2){
			this.fobj.TotalMoa.readOnly = true;
			this.fobj.TotalTax.readOnly = true;
			this.fobj.t_price.readOnly = false;
			this.fobj.MoaTax.readOnly = true;

			for (j = 1; j <= 4; j++){
				this.fobj['LinQty'+j].readOnly = true;
				this.fobj['LinPri'+j].readOnly = true;
				this.fobj['LinMoa'+j].readOnly = true;
				this.fobj['LinTax'+j].readOnly = true;
			}
			if (init != false) this.ClearDoc();
			this.fobj.count_item.value=1;
			this.fobj.t_price.value="";
			this.fobj.t_price.focus();
		}
		else if (o_value == 3){
			this.fobj.TotalMoa.readOnly = false;
			this.fobj.TotalTax.readOnly = false;
			this.fobj.t_price.readOnly = true;
			this.fobj.MoaTax.readOnly = false;

			for (j = 1; j <= 4; j++){
				this.fobj['LinQty'+j].readOnly = false;
				this.fobj['LinPri'+j].readOnly = false;
				this.fobj['LinMoa'+j].readOnly = false;
				this.fobj['LinTax'+j].readOnly = false;
			}
			if (init != false) this.ClearDoc();
			this.fobj.count_item.value=1;
			this.fobj.t_price.value="";
		}
	},

	ClearDoc: function ()
	{
		this.fobj.blankCnt.value = "10";
		this.fobj.TotalMoa.value = "0";
		this.fobj.TotalTax.value = "0";
		for (j = 1; j <= 4; j++ ){
			this.fobj['LinMonth'+j].value = "";
			this.fobj['LinDay'+j].value = "";
			this.fobj['LinItem'+j].value = "";
			this.fobj['LinUnit'+j].value = "";
			this.fobj['LinQty'+j].value = "";
			this.fobj['LinPri'+j].value = "";
			this.fobj['LinMoa'+j].value = "";
			this.fobj['LinTax'+j].value = "";
			this.fobj['LinRemark'+j].value = "";
		}
		this.fobj.MoaTax.value = "";
	},

	cal_Sum: function ()
	{
		var tmp_count = this.fobj.count_item.value;

		if (this.fobj.t_price.value=="") var msg = "합계금액을 입력하세요";
		else if (isNaN(this.fobj.t_price.value)) var msg = "합계금액은 숫자로 입력해야 합니다.";
		else if (tmp_count>4) var msg = "4개의 항목만 입력 가능합니다.";

		if (msg != null){
			alert(msg);
			this.fobj.t_price.value="";
			this.fobj.t_price.focus();
			return;
		}

		if (this.fobj.TaxType[0].checked)
			this.fobj['LinMoa' + tmp_count].value = Math.round(parseInt(this.fobj.t_price.value , 10) / 11 * 10);
		else
			this.fobj['LinMoa' + tmp_count].value = this.fobj.t_price.value;
		this.calculate(this.fobj['LinMoa' + tmp_count], true);
		this.fobj.count_item.value=++tmp_count;
	},

	calculate: function (obj, calLinMoa)
	{
		/** name=공급가액총액 : 공란 계산 **/
		if (obj.name == 'TotalMoa') this.fobj.blankCnt.value = 11 - extUncomma(obj.value).length;

		obj.value = extComma(obj.value);
		if (obj.name.indexOf("Lin") >= 0 && obj.value == 0) obj.value = '';

		if (!(this.fobj.chkCal[0].checked || this.fobj.chkCal[1].checked || calLinMoa == true)) return;

		/** name=공급가액 : 세액, 공급가액총액, 세액총액, 합계금액 계산 **/
		if (obj.name.indexOf("LinMoa") >= 0){
			totMoa = totTax = 0;

			/** 세액 계산(세금계산서 경우) **/
			if (this.fobj.TaxType[0].checked){
				if (this.fobj.chkCal[2].checked)
					this.fobj["LinTax" + obj.name.substring(6, 7)].value = (obj.value == '' ? '' : extComma((this.fobj.t_price.value - Math.round((this.fobj.t_price.value / 11 *10)))+ ""));
				else
					this.fobj["LinTax" + obj.name.substring(6, 7)].value = (obj.value == '' ? '' : extComma((Math.round((extUncomma(obj.value) / 10) - 0.5))+ ""));
			}

			/** 공급가액총액 계산 **/
			totMoa = extUncomma(this.fobj.LinMoa1.value) * 1;
			totMoa = totMoa + extUncomma(this.fobj.LinMoa2.value) * 1;
			totMoa = totMoa + extUncomma(this.fobj.LinMoa3.value) * 1;
			totMoa = totMoa + extUncomma(this.fobj.LinMoa4.value) * 1;

			totMoa = Math.round(totMoa - 0.5) ;
			this.fobj.TotalMoa.value = extComma(totMoa + "");
			this.fobj.blankCnt.value = 11 - (totMoa+"").length;

			/** 세액총액 계산(세금계산서 경우) **/
			if (this.fobj.TaxType[0].checked) {
				totTax = extUncomma(this.fobj.LinTax1.value) * 1;
				totTax = totTax + extUncomma(this.fobj.LinTax2.value) * 1;
				totTax = totTax + extUncomma(this.fobj.LinTax3.value) * 1;
				totTax = totTax + extUncomma(this.fobj.LinTax4.value) * 1;

				if (this.fobj.chkCal[2].checked)
					this.fobj.TotalTax.value = totTax;
				else
					this.fobj.TotalTax.value = extComma((Math.round((totMoa / 10) - 0.5)) + "");
			}

			/** 합계금액 계산 **/
			if (this.fobj.TaxType[0].checked == this.fobj.chkCal[2].checked) this.fobj.MoaTax.value = extComma((totMoa + totTax) + "");
			else if (this.fobj.TaxType[0].checked) this.fobj.MoaTax.value = extComma((totMoa+(Math.round((totMoa / 10) - 0.5))) + "");
			else this.fobj.MoaTax.value = extComma((totMoa) + "");
			if (this.fobj.MoaTax.value == 0) this.fobj.MoaTax.value = '';
		}

		/** name=수량 || name=단가 : 공급가액 계산 **/
		if (obj.name.indexOf("LinQty") >= 0 || obj.name.indexOf("LinPri") >= 0){
			if ( obj.name.indexOf("LinQty") >= 0 ) nametmp = "LinPri" + obj.name.substring(6);
			else nametmp = "LinQty" + obj.name.substring(6);
			if (this.fobj[nametmp].value != ""){
				var nameMoa = "LinMoa" + obj.name.substring(6);
				d1 = extUncomma(obj.value, ".") * 1;
				d2 = extUncomma(this.fobj[nametmp].value, ".") * 1;
				this.fobj[nameMoa].value = d1 * d2;
				this.calculate(this.fobj[nameMoa]);
			}
		}
	},

	chkDate: function (obj, flag)
	{
		var indata = String(obj.value).replace(/(,)*/g,"");
		if (indata == '' || indata == '0') indata = '';
		else if (flag == 0 && (indata < 1 || indata > 12)) var msg = "월을 잘못입력하셨습니다.";
		else if (indata < 1 || indata > 31) var msg = "일을 잘못입력하셨습니다.";

		if (msg != null){
			alert("[입력오류] " + msg);
			obj.focus();
			return "";
		}
		else if(indata.toString().length==1) return "0" + indata;
		else return indata;
	}
}





/*** TAXSUGI TRANSMIT METHOD (TTM) ***/
TTM = {

	init_set : function ()
	{
		if (document.getElementById('avoidSubmit') && !document.getElementById('avoidMsg') )
		{
			sendDiv = document.getElementById('avoidSubmit');
			msgDiv = sendDiv.parentNode.insertBefore( sendDiv.cloneNode(true), sendDiv );
			msgDiv.id = 'avoidMsg';
			msgDiv.style.letterSpacing = '0px';
			msgDiv.innerHTML = '세금계산서 발행요청 중입니다. 잠시만 기다려주세요.';
		}
		sendDiv.style.display = 'none';
		msgDiv.style.display = 'block';
	},

	nowdate : function ()
	{
		var nowdate = new Date().getDate().toString();
		if (nowdate.length < 2) nowdate = '0' + nowdate;
		nowdate = (new Date().getMonth() + 1).toString() + nowdate;
		if (nowdate.length < 4) nowdate = '0' + nowdate;
		nowdate = new Date().getFullYear().toString() + nowdate;
		return nowdate;
	},

	chk : function (fobj)
	{
		/** 공급자 & 공급받는자 체크 **/
		if (chkForm(fobj) === false) return false;

		/** 작성일자 체크 **/
		if ((fobj.TaxYear.value + fobj.TaxMonth.value + fobj.TaxDay.value) > this.nowdate()){
			if (!confirm("작성일자가 금일보다 큽니다 \n\n 계속 진행하시려면 '확인' 버튼을 누르시고 \n\n 진행을 그만두시려면 '취소' 버튼을 누르세요! ")) return false;
		}

		/** 품목 체크 **/
		flag_1 = 0, flag_2 = 1;
		for (i = 1;i < 5;i++){
			month	= String(fobj["LinMonth"+i].value).replace(/(,)*/g,"") == 0 ? '' : String(fobj["LinMonth"+i].value).replace(/(,)*/g,"");
			day		= String(fobj["LinDay"+i].value).replace(/(,)*/g,"") == 0 ? '' : String(fobj["LinDay"+i].value).replace(/(,)*/g,"");
			itemnm	= fobj["LinItem"+i].value;
			unit	= fobj["LinUnit"+i].value;
			qty		= extUncomma(fobj["LinQty"+i].value) == 0 ? '' : extUncomma(fobj["LinQty"+i].value);
			pri		= extUncomma(fobj["LinPri"+i].value) == 0 ? '' : extUncomma(fobj["LinPri"+i].value);
			moa		= extUncomma(fobj["LinMoa"+i].value) == 0 ? '' : extUncomma(fobj["LinMoa"+i].value);
			tax		= extUncomma(fobj["LinTax"+i].value) == 0 ? '' : extUncomma(fobj["LinTax"+i].value);
			remark	= fobj["LinRemark"+i].value;
			lindata	= month + day + itemnm + unit + qty + pri + moa + tax + remark;

			if (itemnm.length > 0 && !chkPatten(fobj["LinItem"+i],'/^[가-힣a-zA-Z0-9]+$/','특수기호를 &,>,<,%은 품목란에 사용할 수 없습니다')) return false;
			if (remark.length > 0 && !chkPatten(fobj["LinRemark"+i],'/^[가-힣a-zA-Z0-9]+$/','특수기호를 &,>,<,%은 비고란에 사용할 수 없습니다')) return false;

			if (lindata.length == 0){
				flag_2 = 0;
			}
			else {
				if	(flag_2 == 0) {
					alert("품목정보는 빈라인 없이 순서대로 기재하셔야 합니다.");
					fobj["LinMonth"+i].focus();
					return false;
				}
				flag_1 = 1;
				if (month.length == 0) {
					alert("라인의 월은 필수입니다");
					fobj["LinMonth"+i].focus();
					return false;
				}
				else if ((month*1) != (fobj.TaxMonth.value*1)) {
					alert("작성월과 라인의 월은 같아야합니다");
					fobj["LinMonth"+i].focus();
					return false;
				}
				else if (day.length == 0) {
					alert("라인의 일은 필수입니다");
					fobj["LinDay"+i].focus();
					return false;
				}
				else if (itemnm.length == 0) {
					alert("라인의 품목명은 필수입니다");
					fobj["LinItem"+i].focus();
					return false;
				}
				else if (moa.length == 0) {
					alert("라인의 공급가액은 필수입니다");
					fobj["LinMoa"+i].focus();
					return false;
				}
			}
		}
		if (flag_2 == 0 && flag_1 == 0) {
			alert("품목정보는 하나이상 순서대로 기재하셔야 합니다.");
			fobj.LinMonth1.focus();
			return false;
		}

		/** 합계금액 체크 **/
		totMoa = extUncomma(fobj.LinMoa1.value) * 1;
		totMoa = totMoa + extUncomma(fobj.LinMoa2.value) * 1;
		totMoa = totMoa + extUncomma(fobj.LinMoa3.value) * 1;
		totMoa = totMoa + extUncomma(fobj.LinMoa4.value) * 1;

		totTax = extUncomma(fobj.LinTax1.value) * 1;
		totTax = totTax + extUncomma(fobj.LinTax2.value) * 1;
		totTax = totTax + extUncomma(fobj.LinTax3.value) * 1;
		totTax = totTax + extUncomma(fobj.LinTax4.value) * 1;

		if (fobj.MoaTax.value == ''){
			alert("합계금액을 입력하세요!");
			fobj.MoaTax.focus();
			return false;
		}
		else if (totMoa != extUncomma(fobj.TotalMoa.value)){
			totMoa = Math.round(totMoa - 0.5) ;
			if (totMoa != extUncomma(fobj.TotalMoa.value)){
				alert("공급가액 합계오류입니다!");
				fobj.TotalMoa.focus();
				return false;
			}
		}
		else if (totTax != extUncomma(fobj.TotalTax.value)){
			if (Math.round((totMoa / 10) - 0.5 -1)  <= extUncomma(fobj.TotalTax.value));
			else if (extUncomma(fobj.TotalTax.value) <= Math.round((totMoa / 10) - 0.5 + 1));
			else {
				alert("세액 합계오류입니다!");
				fobj.TotalMoa.focus();
				return false;
			}
		}

		var totMoaTax = extUncomma(fobj.MoaTax.value);  // 합계금액
		var LiaPlus = extUncomma(fobj.TotalMoa.value)*1+extUncomma(fobj.TotalTax.value)*1; // 공급가액총액 + 세액총액
		if ( totMoaTax != LiaPlus ) {
			alert("합계금액 합계오류입니다!");
			if (fobj.TaxType[0].checked && fobj.chkCal[3].checked) fobj.MoaTax.focus();
			else fobj.TotalTax.focus();
			return false;
		}

		return true;
	},

	dispConf : function (fobj)
	{
		var msg = "";
		var taxType = "";

		if (fobj.TaxType[0].checked){
			taxType = "VAT";
			msg = "         === 매출 세금계산서 ==\n";
		}
		else if (fobj.TaxType[1].checked){
			taxType = "FRE";
			msg = "         === 매출 계산서 ==\n";
		}
		else if (fobj.TaxType[2].checked){
			taxType = "RCP";
			msg = "         === 매출 영수증 ==\n";
		}

		msg = msg + "\n======================================";
		msg = msg + "\n  >> 공 급 자";
		msg = msg + "\n\t등록번호 : " + fobj.SupNo.value;
		msg = msg + "\n\t  상  호   : " + fobj.SupComp.value;

		msg = msg + "\n  >> 공급받는자";
		msg = msg + "\n\t등록번호 : " + fobj.BuyNo.value;
		msg = msg + "\n\t  상  호   : " + fobj.BuyComp.value;

		msg = msg + "\n--------------------------------------";
		msg = msg + "\n   작성일자 : " + fobj.TaxYear.value + "/" + fobj.TaxMonth.value + "/" + fobj.TaxDay.value;
		if(taxType =="RCP")
			msg = msg + "\n   공급금액 : " + fobj.TotalMoa.value;
		else
			msg = msg + "\n   공급가액 : " + fobj.TotalMoa.value;

		if (taxType =="VAT")
			msg = msg + "\n     세  액   : " + fobj.TotalTax.value;
		msg = msg + "\n--------------------------------------";
		msg = msg + "\n   합계금액 : " + fobj.MoaTax.value;
		msg = msg + "\n======================================              ";
		msg = msg + "\n\n♠ 작성한 내역을 확인하시기 바랍니다.";
		msg = msg + "\n\n    작성한 내용을 입력하시겠습니까?";

		if (!confirm(msg)){
	        alert ( " 발행요청이 취소되었습니다...." );
	        return false;
	    }
	    return true;
	},

	register : function (fobj)
	{
		if (this.chk(fobj) === false) return false;
		if (this.dispConf(fobj) === false) return false;

		this.init_set();

		var urlStr = "../order/tax_indb.php?mode=putSugiTaxbill&dummy=" + new Date().getTime();
		var ajax = new Ajax.Request( urlStr,
		{
			method: "post",
			parameters: decodeURIComponent( Form.serialize(fobj) ),
			onComplete: function ()
			{
				var req = ajax.transport;
				if ( req.status == 200 )
				{
					msgDiv.innerHTML = req.responseText;
					parent.setTimeout('TLM.list()', 100);
					parent.closeLayer();
				}
				else {
					var msg = req.getResponseHeader("Status");
					if ( msg == null || msg.length == null || msg.length <= 0 ) msg = 'Error! Request status is ' + req.status;
					msgDiv.innerHTML = msg;
					sendDiv.style.display = 'block';
				}
			}
		} );
		return false;
	}
}





/*** TAXSUGI LIST METHOD (TLM) ***/
TLM = {

	init_set : function ()
	{
		/** 커버 활성화 **/
		this.listcoverObj = document.getElementById('listcover');
		with (this.listcoverObj.style)
		{
			display = "block";
			border = "solid 1px blue";
			backgroundColor = "#000000";
			filter = "Alpha(Opacity=20)";
			opacity = "0.2";
			textAlign = "center";
		}

		/** 예외처리 : 모질라 **/
		if ( !document.all ) this.listcoverObj.parentNode.style.height = '';

		/** 리스팅 영역정의 **/
		this.listingObj		= document.getElementById('listing');

		/** 페이징정보 영역정의 **/
		this.pageObj		= new Array();
		this.pageObj['rtotal']	= document.getElementById('page_rtotal');
		this.pageObj['recode']	= document.getElementById('page_recode');
		this.pageObj['now']	= document.getElementById('page_now');
		this.pageObj['total']	= document.getElementById('page_total');
		this.pageObj['navi']	= document.getElementById('page_navi');
	},

	list : function (query)
	{
		this.init_set();

		// Create Query
		if ( query == undefined )
		{
			var tmp = new Array();
			var fObj = document.frmList;
			var eleLen = fObj.length;
			for ( i = 0; i < eleLen; i++ )
				if ( fObj[i].value != '' )
					tmp.push( fObj[i].name + "=" + fObj[i].value );
			var query = tmp.join("&");
		}

		// AJAX 실행
		var urlStr = "../order/tax_indb.php?mode=getTaxsugiList&" + query + "&dummy=" + new Date().getTime();
		var ajax = new Ajax.Request( urlStr,
		{
			method: "get",
			onComplete: function()
			{
				var req = ajax.transport;
				if ( req.status == 200 )
				{
					var response = req.responseText;
					var jsonData = eval( '(' + response + ')' );
					TLM.list_init();

					// 리스팅 실행
					try{
						TLM.listing( jsonData.lists );
					}
					catch(err)
					{
						TLM.list_msg( '<span style="color:#FF6600; font-weight:bold; font-size:10pt;">요청하신 목록이 정상적으로 출력되지 않았습니다. 다시 시도하세요.</span>' );
						TLM.listcoverObj.style.display = "none";
						return;
					}

					// 페이징정보 출력
					try{
						for ( var n in TLM.pageObj )
							if ( TLM.pageObj[n] && n == 'navi' )
							{
								var navi = jsonData.page[n];
								var len = navi[0].length;
								var pageHtml = new Array();

								for ( i = 0; i < len; i++ )
									if ( navi[0][i] == '' ) // 현재 페이지번호
										pageHtml.push( '<b>' + navi[1][i] + '</b>' );
									else  // 이동할 페이지번호
										pageHtml.push( '<a href="javascript:TLM.list(\'' + navi[0][i] + '\');">' + navi[1][i] + '</a>' );
								TLM.pageObj[n].innerHTML = pageHtml.join('&nbsp;');
							}
							else if ( TLM.pageObj[n] ) TLM.pageObj[n].innerHTML = comma(jsonData.page[n]);
					}
					catch(err){
						TLM.listcoverObj.style.display = "none";
						return;
					}
				}
				else {
					var msg = req.getResponseHeader("Status");
					if ( msg == null || msg.length == null || msg.length <= 0 ) msg = 'Error! Request status is ' + req.status;
					TLM.list_init();
					TLM.list_msg( '<span style="color:#FF6600; font-weight:bold; font-size:10pt;">' + msg + '</span>' );
				}

				TLM.listcoverObj.style.display = "none";
			}
		} );
	},

	/** 리스트 초기화 함수 **/
	list_init : function ()
	{
		if ( this.listingObj )
			while ( this.listingObj.rows.length > 2 ) this.listingObj.deleteRow( this.listingObj.rows.length - 1); // 결과 rows 초기화

		for ( var n in this.pageObj )
			if ( this.pageObj[n] && n == 'navi' ) this.pageObj[n].innerHTML =' ';
			else if ( this.pageObj[n] ) this.pageObj[n].innerHTML = '0';
	},

	/** 리스팅 함수 **/
	listing : function (lists)
	{
		var len = lists.length;
		for ( n = 0; n < len; n++ )
		{
			l_row = lists[n];

			// 첫째 라인
			newTr = this.listingObj.insertRow(-1);
			newTr.height = '25';
			newTr.align = 'center';

			newTd = newTr.insertCell(-1);
			newTd.rowSpan = 2;
			newTd.innerHTML = '<font class=ver81 color=444444>' + l_row.no + '</font>';

			newTd = newTr.insertCell(-1);
			newTd.colSpan = 3;
			newTd.align = 'left';
			newTd.style.padding = '5px 0 5px 7px';
			newTd.innerHTML = '<font class=small color=444444>\
				사업자번호 : ' + l_row.buy_regnum + '&nbsp;&nbsp;\
				회사명 : ' + l_row.buy_company + '<br>\
				대표자명 : ' + l_row.buy_employer + '&nbsp;&nbsp;\
				업태 : ' + l_row.buy_bizcond + '&nbsp;&nbsp;\
				종목 : ' + l_row.buy_bizitem + '<br>\
				사업장주소 : ' + l_row.buy_address + '</font>';

			newTd = newTr.insertCell(-1);
			newTd.innerHTML = '<font class=ver8 color=444444>' + l_row.gen_tm + '</font>';

			newTd = newTr.insertCell(-1);
			newTd.innerHTML = '<font class=small color=444444>' + l_row.doc_number + '</font>';

			newTd = newTr.insertCell(-1);
			newTd.innerHTML = (l_row.mtsid != null ? '<font class=small color=444444>' + l_row.mtsid + '</font>' : '―');

			newTd = newTr.insertCell(-1);
			newTd.rowSpan = 2;
			newTd.style.lineHeight = '15pt';
			if (l_row.err_msg == null || l_row.status != 'ERR') newTd.innerHTML = '<font color=EA0095><b>' + l_row.status_txt + '</b></font>';
			else {
				newTd.innerHTML = '<a href="javascript:alert(\'' + l_row.err_msg + '\')"><font color=EA0095><b>' + l_row.status_txt + '</b></font></a>';
				newTd.title = l_row.err_msg;
			}

			// 둘째 라인
			newTr = this.listingObj.insertRow(-1);
			newTr.height = '25';
			newTr.align = 'center';

			newTd = newTr.insertCell(-1);
			newTd.align = 'left';
			newTd.style.padding = '5px 0 5px 7px';
			newTd.innerHTML = '<font class=small color=444444>' + l_row.item + '</font>';

			newTd = newTr.insertCell(-1);
			newTd.style.padding = '5px 0 5px 0px';
			newTd.innerHTML = '\
				<table width=92% border=0 cellspacing=0 cellpadding=0 style="line-height:15pt;">\
				<col width=44%>\
				<tr><td><font class=small color=444444>발행액 :</td><td style="text-align:right;"><font class=ver8 color=444444>' + extComma(l_row.pay_totalprice) + '</td></tr>\
				<tr><td><font class=small color=444444>공급액 :</td><td style="text-align:right;"><font class=ver8 color=444444>' + extComma(l_row.tax_supprice) + '</td></tr>\
				<tr><td><font class=small color=444444>부가세 :</td><td style="text-align:right;"><font class=ver8 color=444444>' + extComma(l_row.tax_taxprice) + '</td></tr>\
				</table>\
				';

			newTd = newTr.insertCell(-1);
			newTd.innerHTML = l_row.tax_text;

			newTd = newTr.insertCell(-1);
			newTd.innerHTML = l_row.bill_text;

			newTd = newTr.insertCell(-1);
			newTd.innerHTML = '<font class=ver8 color=444444>' + l_row.sbm_tm + '</font>';

			newTd = newTr.insertCell(-1);
			newTd.innerHTML = (l_row.act_tm != null ? '<font class=small color=444444>' + l_row.act_tm.replace(/ /,'<br>') + '</font>' : '―');
		}
	},

	/** 리스팅 메시지출력 함수 **/
	list_msg : function (msg)
	{
		if ( this.listingObj == undefined ) return;

		newTr = this.listingObj.insertRow(-1);
		newTr.align='center';

		newTd = newTr.insertCell(-1);
		newTd.style.padding='20px 0 20px 0';
		newTd.colSpan = 12;
		newTd.innerHTML = msg;
	}
}