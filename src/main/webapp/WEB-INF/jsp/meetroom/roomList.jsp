<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="ko" >
<title>이마트 회의실 정보</title>
<link rel="stylesheet" href="/new/css/reset.css">
<link rel="stylesheet" href="/new/css/layout.css">    
<link rel="stylesheet" href="/new/css/paragraph.css"> 
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/aten-emartcms-loginpage.js"></script>
<!--[if lte IE 8]>
<script src="js/poly-checked.min.js"></script> 
<![endif]-->
<!--popup-->
<link rel="stylesheet" href="/new/css/needpopup.css">
<script src="/new/js/needpopup.js"></script> 
<!--data-->
<script src="/new/js/jquery-2.2.4.min.js"></script>
<script src="/new/js/jquery-ui.js"></script>
<script src="/new/js/datepipck.js"></script>
<!--popup-->
<link rel="stylesheet" href="/new/css/needpopup.css"> 
<script type="text/javascript">
	$("document").ready(function(){
		dataCall("PrimeRoom_1000001", "{'companycode':'ae0'}");
	});
	function zeroAdd(data){
		if(data.toString().length == 1){
			data = "0"+data;
		} else {
			data = data.toString();
		}
		return data;
	}
	function dateView(data){
		return data.substring(0,4) +"."+ data.substring(4,6)+"."+ data.substring(6,8);
	}
	function timeView(data){
		return data.substring(0,2) +":"+ data.substring(2,4);
	}
	function roomDetail(roomCd, roomNm){
		var dt = new Date();
		var today = dt.getFullYear() + zeroAdd(dt.getMonth()+1) + zeroAdd(dt.getDate());
		var time = zeroAdd(dt.getHours()) + zeroAdd(dt.getMinutes());
		$("#roomDetailInfo .popHead h2").text(roomNm + " 예약정보");	
		dataCall("PrimeRoom_2000001", "{'roomcode':'"+roomCd+"','resvdt':'"+today+"','resvfrom':'"+time+"'}");
	}
	function dataCall(action, param){
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
				var xml = $.parseXML(result.data);
				var jsonData = JSON.parse($(xml).find("string").text());
				if(action == "PrimeRoom_1000001"){
					//console.log(jsonData);
					listCreate(jsonData);
				} else {
					// console.log(jsonData.RoomDetail);
					detailInfoView(jsonData.RoomDetail);
				}
			},
			error : function(e) {
				console.log("fail");
				console.log(e);
				if(e.status == 200){
					var xml = $.parseXML(e.responseText);
					var jsonData = JSON.parse($(xml).find("string").text());
					if(action == "PrimeRoom_1000001"){
						// console.log(jsonData);
						listCreate(jsonData);
					} else {
						// console.log(jsonData.RoomDetail);
						detailInfoView(jsonData.RoomDetail);
					}
				}
				
			}
		});
	}
	function listCreate(data){
		var listCnt = data.RoomList.cnt_list;
		var listData = data.RoomList.room_list;
		var createHTML = "";
		for(var i = 0; i < listCnt; i++){
			if((i%8) == 0) createHTML += "<tr>";
			//createHTML += "<td><p class='deptNm'>"+listData[i].deptname+"</p><p class='roomNm'>"+listData[i].roomname+"</p><p class='roomCd'>("+listData[i].roomcode+")</p><a class='meet_preview' onclick='prePage(&#39;"+listData[i].roomcode+"&#39;)'>미리보기</a></td>";
			createHTML += "<td onclick='roomDetail(&#39;"+listData[i].roomcode+"&#39;,&#39;"+listData[i].roomname+"&#39;)' data-needpopup-show='#roomDetailInfo'><p class='deptNm'>"+listData[i].deptname+"</p><p class='roomNm'>"+listData[i].roomname+"</p><p class='roomCd'>("+listData[i].roomcode+")</p><a class='meet_preview' onclick='prePage(&#39;"+listData[i].roomcode+"&#39;)'>미리보기</a></td>";
			if((i%8) == 7) createHTML += "</tr>";
		}
		$("#iistBody").append(createHTML);
	}
	function detailInfoView(data){
		
		var htmlCreate = "";
		
		if(data.resvstatus == 1){
			htmlCreate += "<p class='pop_resevStatus reserv_true'>회의 진행중</p>";
			htmlCreate += "<p>회의명 : "+data.resvtitle+"</p>";
			htmlCreate += "<p>진행자 : "+data.resvusrdeptname+" "+data.resvusrname+" "+data.resvposname+"</p>";
			htmlCreate += "<p>예정시간 : " + dateView(data.resvusedatefrom) + " <span style='font-weight:bold; color:#477eff;'>" +timeView(data.resvusetimefrom)+" ~ "+timeView(data.resvusetimeto)+"</span></p>";
		} else if(data.resvstatus == 2){
			htmlCreate += "<p class='pop_resevStatus reserv_false'>현재 회의실을 이용중이지 않습니다.</p>";
		}
		
		$("#roomDetailInfo .popCon").html(htmlCreate);
	}
	function prePage(code){
		window.open("/meetroom/roomExam.do?mode=S&roomcode1="+code+"&roomcode2=no", "_blank", "width=1280,height=800,toolbar=no,titlebar=no,status=no,scrollbars=yes,resizable=yes,menubar=no,location=no", false);
	}
</script>
<style>
#contents{
	position: relative;
    width: 1200px;
    margin: 0 auto;
    padding-bottom: 30px;
    box-sizing: border-box;
}
.top_text{
    color: #fff;
    font-weight: bold;
    font-size: 20px;
    margin-right: 188px;
}
#iistBody td{
	padding	: 8px 0px;
	width : 12.5%;
	border : 1px solid #dfdfdf;
	border-collapse: collapse;
}
.deptNm{
    font-size: 14px;
    margin: 8px 0px;
    color: #0ea7e4;
    font-weight: bold;
}
.roomNm{
	font-size : 16px;
	font-weight: bold;
	word-break: keep-all;
	margin: 4px 0px;
}
.roomCd{
	font-size : 14px;
	margin: 4px 0px;
}
.popCon p{
	margin:8px 0px;
	font-size: 16px;
}
.pop_resevStatus{
    font-weight: bold;
    font-size: 18px;
    border-radius: 7px;
    padding: 4px 12px;
    width: fit-content;
}
.reserv_true{
	color: #477eff;
    border: 2px solid #477eff;
}
.reserv_false{
	color: #ff7a47;
    border: 2px solid #ff7a47;
}
</style>
</head>
<body>
	<div id="header">
		 <h1><img src="/img/logo.png" alt="이마트"></h1>
		 <div style="text-align:center;">
		 	<span class="top_text">이마트 회의실 정보</span>
		 </div>
	</div>	
	<div class="pageTop">
		<div class="conIn">
			<h2>DID현황</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home"> 
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="contents">
	<!--내용시작-->
	<table>
		<tbody id="iistBody">
		</tbody>
	</table>
	</div>
	<div class="clear"></div>
	<div id='roomDetailInfo' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2></h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
        </div>
        <div class="pop_footer">
        </div>
    </div>
	<div id="footer">
		<div class="conIn ">
			<span><img src="/img/emartLogo.png"></span>
			<address><span>서울특별시 성동구 뚝섬로 377(성수동2가)</span></address>
		</div>
	</div>
	<script src="/new/js/needpopup.js"></script> 
    <!--data-->
    <script src="/new/js/jquery-ui.js"></script>
</body>
</html>