// 이벤트 등록

addEvent();

function addEvent() {
	addEventPhoneNumberElements();
	addEventInputEmoneyElement();
}

function addEventPhoneNumberElements() {
	var events = {
			'keydown' : onlynumber,
			'keyup' : removeChar
	};
	addEventToSelector('._phone-number', events);
}

function addEventInputEmoneyElement() {
	var events = {
		'blur' : chk_emoney,
		'keyup' : function(event) {
			calcu_settle(event);
			removeChar(event);
		},
		'keydown' : onlynumber
	};
	addEventToSelector('._emoney', events);
}

function addEventToSelector(selector, eventAndFunctionList) {
	var $elements = $(selector);
	for (var i = 0; i < $elements.length; i++) {
		$.each(eventAndFunctionList, function (event, callBackFunction) {
			$($elements[i]).on(event, callBackFunction);
		})
	}
}