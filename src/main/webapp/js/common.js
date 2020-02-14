//엔터키이벤트
$(document).ready(function(){
    $("input[name=searchKeyword]").keydown(function (key) {
        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
        	search_form();
        }
    });
});

//우편번호 
function zipCode(){
	 window.open("/common/zipcode.jsp", "zip_code", "width=500, height=400, toolbar=no, menubar=no, scrollbars=no, resizable=auto" );	
}
//페이지 이동 
function view_Page(code, code1, code_value, action_page, frm_nm){
	document.getElementById("mode").value = code;	
	code.value =code_value;	
	frm_nm.action = action_page;
	frm_nm.submit();	
}
//페이지로 가기 
function listPage(code, code1){	
	$("form[name="+code+"").attr("action", code1).submit();
	return;
}
//페이징 스크립트;
function ajaxPaging(currentPageNo, firstPageNo, recordCountPerPage, firstPageNoOnPageList, lastPageNoOnPageList, totalPageCount, pageSize, pageScript){
    var pageHtml = "";
    pageHtml += "<div class=pagination>";
	 if (currentPageNo == firstPageNo ){
      pageHtml += "<a href='#' >&laquo;</a>";
	 }else {
      pageHtml += "<a href='#' onclick='"+pageScript+"("+ firstPageNo +")';return false; '>&laquo;</a>";
	 }
	 if (parseInt(currentPageNo) > parseInt(firstPageNo)){
      pageHtml += "<a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) -1)+");return false;'>&lt;</a>"
	 }else {
      pageHtml += "<a href='#' >&lt;</a>"
	 }
    for(var  i = firstPageNoOnPageList; i<= lastPageNoOnPageList; i++){
		 if (i == currentPageNo){
            pageHtml += "<a class=active>"+i+"</a>";
		 }else {
            pageHtml += "<a href='#' onclick='"+pageScript+"("+i+");return false; '>"+i+"</a>";
		 }
    }

	 if (parseInt(totalPageCount) > parseInt(pageSize) ){
        pageHtml += "<a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) + 1)+");return false;'>&gt;</a>"
	 }else {
        pageHtml += "<a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) + 1)+");return false;'>&gt;</a>"
	 }
    if (parseInt(totalPageCount) > parseInt(pageSize)  ){
      pageHtml += "<a href='#' onclick='"+pageScript+"("+ totalPageCount +");return false;'>&raquo;</a>";
	 }else{
      pageHtml += "<a href='#' >&raquo;</a>";
	 }	
    return pageHtml;
  }
//오늘날짜 가지고 오기 
function getAgoDate(yyyy, mm, dd)
{
 var today = new Date();
 var year = today.getFullYear();
 var month = today.getMonth();
 var day = today.getDate();
 
 var resultDate = new Date(yyyy+year, month+mm, day+dd);
 
 
       year = resultDate.getFullYear();
       month = resultDate.getMonth() + 1;
       day = resultDate.getDate();

       if (month < 10)
           month = "0" + month;
       if (day < 10)
           day = "0" + day;

       return year + "" + month + "" + day;
}
function apiExecute(type, url, data, beforeSend, done_callback, fail_callback, always_callback) {
	return _apiExecute(type, url, data, beforeSend, done_callback, fail_callback, always_callback);
}
//특정 길이 대체 문자
function stringLength (str, strlength, replaceTxt){
	if (str.length < parseInt(strlength)){
		for (var i =0; i < (parseInt(strlength) - str.length); i++ ){
			str = replaceTxt + str;
		}
	}else {
		str = str;
	}
	return str;
}
function dayConvert(day){
	if(day.length == 8){
		day = day.substring(0,4)+"-"+day.substring(4,6)+"-"+day.substring(6,8);
	}
	return day 
}
//숫자만 입력 
function only_num() {
    if (((event.keyCode < 48) || (event.keyCode > 57)) && (event.keyCode != 190)) event.returnValue = false;
}
function _apiExecute(type, url, data, beforeSend, done_callback, fail_callback, always_callback) {
	var contentType = "application/x-www-form-urlencoded; charset=UTF-8";
	var jqXHR = $.ajax({
		type : type,
		contentType : contentType,
		url : url,
		data : data,
		beforeSend : beforeSend
	}).done(done_callback).fail(fail_callback).always(always_callback);

	return jqXHR;
}
//지난 일자는 등록 하지 못하게 하기 
function yesterDayConfirm(res_day, alert_message){
	var day = new Date();
    var dateNow = fnLPAD(String(day.getDate()), "0", 2); //일자를 구함
    var monthNow = fnLPAD(String((day.getMonth() + 1)), "0", 2); // 월(month)을 구함    
    var yearNow = String(day.getFullYear()); //년(year)을 구함
    var today = yearNow + monthNow + dateNow;
    
    if (parseInt(res_day) < today){
    	alert(alert_message);
    	return false;
    }else {
    	return true;
    }

	
}
function today_get(){
	var now = new Date();
    var today_day = fnLPAD(String(now.getDate()), "0", 2); //일자를 구함
    var today_month = fnLPAD(String((now.getMonth() + 1)), "0", 2); // 월(month)을 구함    
    var today_year = String(now.getFullYear()); //년(year)을 구함
    var today = today_year + today_month + today_day;
    
    return today;
}
function fnLPAD(val, set, cnt) {
    if (!set || !cnt || val.length >= cnt) {
        return val;
    }
    var max = (cnt - val.length) / set.length;
    for (var i = 0; i < max; i++) {
        val = set + val;
    }
    return val;
}
function any_empt_line_id(frm_nm, alert_message){
	 var form_nm = eval("document.getElementById('"+frm_nm+"')");
	 if (form_nm.value.length < 1)
	 {
		 alert(alert_message);
		 form_nm.focus();
		 return false;
	 }else{
         return true;
	 }
}
function search_form(url){
	$(":hidden[name=pageIndex]").val("1");	
	$("form[name=regist]").attr("action",url).submit();
}
function checkbox_value(ckbok_name){
	var ckValue ="";
	 $("input:checkbox[name='"+ckbok_name+"']").each(function() {
	      if(this.checked == true){//checked 처리된 항목의 값
	    	  ckValue += this.value +",";
	      }
	 });
	 if (ckValue.length > 0){
		 ckValue = ckValue.substring(0, ckValue.length-1);
	 }
	 return ckValue;
}
function value_checkbox(ckbok_name, value){
	var ckValue = null;	
	if (value.length > 0){
		 var splitValue =  value.split(",");
		 $("input:checkbox[name='"+ckbok_name+"']").each(function() {
		      for (var i =0; i < splitValue.length; i++){
		    	  if(this.value == splitValue[i]){ //값 비교
			            this.checked = true; //checked 처리
			      } 
		      }
		      
		 });	 
	}else {
		
		$("input:checkbox[name='"+ckbok_name+"']").each(function() {
			 this.checked = false; //checked 처리
		 });	
	}
}

function fn_emptyReplace(ckValue, replaceValue){
	return  (ckValue == "" || ckValue == undefined ) ? replaceValue : ckValue;
}


function uniAjax(url, param, done_callback, fail_callback){
	var jxFax =  $.ajax({
		        type : 'POST',
		        url : url,
		        contentType : "application/json; charset=utf-8",
		        data : JSON.stringify(param)
		    }).done(done_callback).fail(fail_callback);
	
    return jxFax;
}
function fn_timeSplit(timeInfo){
	return (timeInfo.length == 4) ?  timeInfo.substring(0,2)+":" + timeInfo.substring(2,4) : timeInfo;
}
function uniAjaxSerial(url, param, done_callback, fail_callback){
	var jxFax =  $.ajax({
		        type : 'POST',
		        url : url,
		        contentType : "application/json; charset=utf-8",
		        data : param,
		    }).done(done_callback).fail(fail_callback);
	
    return jxFax;
}


jQuery.fn.serializeObject = function() {
    var obj = null;
    try {
        //if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });
            }//if ( arr ) {
        //}
    } catch (e) {
        alert(e.message);
    } finally {
    }
 
    return obj;
};