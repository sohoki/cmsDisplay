<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="ko" >
<title>이마트 회의실 정보</title>
<link rel="stylesheet" href="/css/meet/reset.css">
<link rel="stylesheet" href="/css/meet/emart-meetroom-paragraph.css"> 
<script type="text/javascript" src="/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/js/meet/emart-meetroom-view.js"></script>
<link rel="stylesheet" href="/css/meet/swiper.css">
<script>
	function Request(){
	 var requestParam ="";
	 
	 //getParameter 펑션
	  this.getParameter = function(param){
	  //현재 주소를 decoding
	  var url = unescape(location.href); 
	  //파라미터만 자르고, 다시 &그분자를 잘라서 배열에 넣는다. 
	   var paramArr = (url.substring(url.indexOf("?")+1,url.length)).split("&"); 
	 
	   for(var i = 0 ; i < paramArr.length ; i++){
	     var temp = paramArr[i].split("="); //파라미터 변수명을 담음
	 
	     if(temp[0].toUpperCase() == param.toUpperCase()){
	       // 변수명과 일치할 경우 데이터 삽입
	       requestParam = paramArr[i].split("=")[1]; 
	       break;
	     }
	   }
	   return requestParam;
	 }
	}
	$("document").ready(function(){
		var dt = new Date();
		var today = dt.getFullYear() + zeroAdd(dt.getMonth()+1) + zeroAdd(dt.getDate());
		var time = zeroAdd(dt.getHours()) + zeroAdd(dt.getMinutes());
		
		var request = new Request();
		var mode = request.getParameter("mode");
		var roomCd = request.getParameter("roomcode1");
		var roomCd2 = request.getParameter("roomcode2");
		// console.log(mode + " / " + roomCd);
		
		var action = "PrimeRoom_2000001";
		var param = "{'roomcode':'"+roomCd+"','resvdt':'"+today+"','resvfrom':'"+time+"'}";

		$.ajax({
			//url : '/test/datacall.do',
			url : '/meet/meetRoomDataCall.do',
			type : 'POST',
			data : {
				'action' : action, 
				'param'	 : param
			},
			dataType : 'text/plain',
			success : function(result) {
				if(action == "PrimeRoom_1000001"){
					console.log(result.responseText);
					//listCreate(jsonData);
				} else {
					console.log(result.responseText);
					// pageMake(mode, data, data2); // 이것을 호출 해야 함
				}
			},
			error : function(e) {
				//console.log("fail");
				//console.log(e);
				if(e.status == 200){
					if(action == "PrimeRoom_1000001"){
						//console.log(e.responseText);
						//listCreate(jsonData);
					} else {
						//console.log(e.responseText);
						if(mode == "S"){
							pageMake(mode, e.responseText, ''); // 이것을 호출 해야 함	
							// alert("F11을 눌러 전체화면으로 확인하세요 !");
						}
					}
				}
			}
		});
		
		
		function roomDataCall(){
			
		}
		
	});
// 
</script>
</head>
<body>
	<div id="wrap">
		<div class="header">
			<div class="contents">
			</div>
		</div>
	</div>
	<div class="main_slider"></div>
	<div class="footer">
		<div class="contents">
			<h1><img class="foot_logo" src='/img/meet/logo.png' alt='이마트로고'/></h1>
		</div>
	</div>
	<script type="text/javascript" src="/js/meet/swiper.js"></script>
</body>
</html>