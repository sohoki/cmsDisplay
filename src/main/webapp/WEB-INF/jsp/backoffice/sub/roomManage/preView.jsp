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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>이마트 회의실알림이</title>
    <link rel="stylesheet" href="/css/paragraph_M.css">    
    <link rel="stylesheet" href="/css/reset_M.css">    
    <script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
    <!-- swiper CSS-->
    <link rel="stylesheet" href="/css/swiper.css">
</head>
<body>
<form:form name="regist" commandName="regist" method="post">
<input type="hidden" id="mhsMonitorcd" name="mhsMonitorcd" value="${regist.mhsMonitorcd }" >
<input type="hidden" id="hid_time">
<input type="hidden" id="searchDay" name="searchDay" value="${searchDay}">
 <div class="wrap">
        <div class="header">
            <div class="contents">
                <div class="float_left">
                    <span class="room_name">${regist.mhsMonitornm }</span>
                    <span class="floor">${regist.mhsCenternm }</span>
                </div>
                <div class="float_right">
                    <ul class="date">
                        <li><span id="day">4월 25일</span></li>
                        <li><span id="sp_week">목요일</span></li>
                    </ul>
                    <p class="time" id="time">12:35</p>
                </div>    
                <div class="clear"></div>
            </div>            
        </div>
		</div>
        <!-- Swiper -->
        <div class="main_slider">   
            <!-- Add Arrows -->                
            <div class="swiper-container">
                <div class="swiper-wrapper">                    
                    <!-- // slide01 -->
                    <div class="swiper-slide slide01">
                        <div class="contents">
                            <h2 class="slider_tit">문화센터 강의 현황</h2>
                            <table class="meeting_list" id="meeting_list">
                                <thead>
                                    <tr>
                                        <th>시간</th>
                                        <th>강의명</th>
                                        <th>강사명</th>
                                        <th>비고</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="meeting_active">
                                        <td>12:00~13:00</td>
                                        <td><span class="meeting_on">회의 진행중</span></td>
                                        <td>고객의 마음을 얻는 서비스 혁신 전략</td>
                                        <td>서비스 기획팀</td>                                        
                                    </tr>
                                    <tr>
                                        <td>13:00~13:30</td>
                                        <td><span class="meeting_none">회의 없음</span></td>
                                        <td>예약된 회의가 없습니다.</td>
                                        <td>-</td>
                                        <td>-</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                   <div class="swiper-slide slide02">
                        <div class="contents">
                            <div class="margin-top90">
                                 <!--회의 제목 -->
                                <div class="meeting_tit">
                                    <p class="meeting_noti">강의 제목</p>
                                    <h3 class="meeting_txt"><span id="sp_classNm"></span></h3>
                                </div>
                                <!-- 회의진행상태-->
                                <div class="float_right">
                                    <span class="onMeeting" id="onMeeting"></span>
                                </div>
                                <div class="clear"></div>          
                            </div>                                                 
                            <!-- 회의 예약자 -->
                            <div class="user_nam text-right" id="user_nam_text-right">
                                <p class="meeting_noti">강사명</p>
                                <p class="meeting_part">비고</p>                                
                            </div>
                            <!-- 다음예약자 -->
                            <div class="next_reser">
                                <ul>
                                    <li>다음 강의</li>
                                    <li><span id="sp_nextInfo"></span></li>
                                </ul>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide slide03">
                        <div class="contents">
                            <h2 class="slider_tit">문화센터 강의 현황</h2>
                            <table class="meeting_list" id="meeting_list01">
                                <thead>
                                    <tr>
                                        <th>시간</th>
                                        <th>강의</th>
                                        <th>강사명</th>
                                        <th>비고</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="meeting_active">
                                        <td>12:00~13:00</td>
                                        <td><span class="meeting_on">회의 진행중</span></td>
                                        <td>고객의 마음을 얻는 서비스 혁신 전략</td>
                                        <td>서비스 기획팀</td>                                        
                                    </tr>
                                    <tr>
                                        <td>13:00~13:30</td>
                                        <td><span class="meeting_none">회의 없음</span></td>
                                        <td>예약된 회의가 없습니다.</td>
                                        <td>-</td>
                                        <td>-</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                   <div class="swiper-slide slide04">
                        <div class="contents">
                            <div class="margin-top90">
                                 <!--회의 제목 -->
                                <div class="meeting_tit">
                                    <p class="meeting_noti">강의 제목</p>
                                    <h3 class="meeting_txt"><span id="sp_classNm_1"></span></h3>
                                </div>
                                <!-- 회의진행상태-->
                                <div class="float_right">
                                    <span class="onMeeting" id="onMeeting_1"></span>
                                </div>
                                <div class="clear"></div>          
                            </div>                                                 
                            <!-- 회의 예약자 -->
                            <div class="user_nam text-right" id="user_nam_text-right_1">
                                <p class="meeting_noti">강사명</p>
                                <p class="meeting_part">비고</p>                                
                            </div>
                            <!-- 다음예약자 -->
                            <div class="next_reser">
                                <ul>
                                    <li>다음 강의</li>
                                    <li><span id="sp_nextInfo_1"></span></li>
                                </ul>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>                    
            </div>                
        </div>               
         <!--// Swiper -->
        <div class="footer">
            <div class="contents">
                <h1><img src="/img/logo.png" alt="이마트로고" width="180" height="100"></h1>    
            </div>            
        </div>
    

  <script src="/js/swiper.js"></script>
  <script type="text/javascript">
	  var dayCnt = 0;
	  var reloadCnt =0;
	  
	  $(document).ready(function () {
		  day_view();
		  classInfo();
		  setInterval(function() {			 
			  day_view();
			  dayCnt++;
			 
		  	  if(dayCnt==60){
		  		 classInfo();
		  		 dayCnt = 0;
				 reloadCnt++;
		  	  }
			  if(reloadCnt == 60){
				location.reload();
			  }
		  },1000);	 
	  });
	  function day_view(){
		  var d = new Date();
		  var week = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
		  var dayOfWeek = week[ new Date().getDay()];
		  $("#sp_week").text(dayOfWeek);
	      var currentDate = ( d.getMonth() + 1 ) + "월 " + d.getDate() + "일";
	      var currentTime = d.getHours() + "시 " + d.getMinutes() + "분";
	      
	      if(d.getMinutes()< 10){
	    	  $("#hid_time").val(String(d.getHours()) + "0" + String(d.getMinutes()));  
	      }else{
	    	  $("#hid_time").val(String(d.getHours()) + String(d.getMinutes()));
	      }
	      
	      $("#day").html(currentDate);
	      $("#time").text(currentTime);
	      
	      $("#searchDay").val( $("#searchDay").val());
	  }
	  
	  function classInfo(){
		  var data = { mhsMonitorcd : $("#mhsMonitorcd").val() , searchDay :  $("#searchDay").val()};
		  $.ajax({
				url : '/backoffice/sub/roomManage/preViewJson.do?mhsMonitorcd='+  $("#mhsMonitorcd").val() + "&searchDay=" + $("#searchDay").val(),
				type : 'POST',				
				data : data ,
				dataType : 'json',
				success : function(result) {					
					if (result.status == "SUCCESS"){
			    	    var data = result.resultList;
			    	    $("#meeting_list > tbody").empty();
			    	    $("#meeting_list01 > tbody").empty();
			    	    setHtml = "";
						var cnt = false;
						
						if(data.length > 0){
							for (var i = 0; i < data.length; i++ ){
								if ( parseInt($("#hid_time").val()) >=  parseInt(data[i].mhsClassstarttime)
								         && parseInt(data[i].mhsClassendtime) > parseInt($("#hid_time").val())){
										 cnt = true;
										
										setHtml += "<tr class='meeting_active'><td>"+ fn_timeSplit(data[i].mhsClassstarttime) +"~"+ fn_timeSplit(data[i].mhsClassendtime) +"</td>";
										setHtml += "<td><span class='meeting_on'>"+ data[i].mhsClassnm+"</span></td>";
										setHtml += "<td>"+ data[i].mhsTeachernm+"</td>";
										setHtml += "<td>"+ data[i].mhsClassintro+"</td></tr>";
										$("#meeting_list >  tbody:last").append(setHtml);
										$("#meeting_list01 >  tbody:last").append(setHtml);
										
									}else if ( parseInt($("#hid_time").val()) ==  parseInt(data[i].mhsClassstarttime)){
									    cnt = true;
										setHtml += "<tr class='meeting_active'><td>"+ fn_timeSplit(data[i].mhsClassstarttime) +"~"+ fn_timeSplit(data[i].mhsClassendtime) +"</td>";
										setHtml += "<td><span class='meeting_on'>"+ data[i].mhsClassnm+"</span></td>";
										setHtml += "<td>"+ data[i].mhsTeachernm+"</td>";
										setHtml += "<td>"+ data[i].mhsClassintro+"</td></tr>";
										$("#meeting_list >  tbody:last").append(setHtml);
										$("#meeting_list01 >  tbody:last").append(setHtml);
									}else if (parseInt($("#hid_time").val()) <  parseInt(data[i].mhsClassstarttime)){
									    cnt = true;
										setHtml += "<tr><td>"+ fn_timeSplit(data[i].mhsClassstarttime) +"~"+ fn_timeSplit(data[i].mhsClassendtime) +"</td>";
										setHtml += "<td>"+ data[i].mhsClassnm+"</td>";
										setHtml += "<td>"+ data[i].mhsTeachernm+"</td>";
										setHtml += "<td>"+ data[i].mhsClassintro+"</td></tr>";
										$("#meeting_list >  tbody:last").append(setHtml);
										$("#meeting_list01 >  tbody:last").append(setHtml);
									}
									setHtml = "";
							}
							
						} 	
						
						if (cnt == false){								
								setHtml += "<tr><td colspan='4' style='text-align : center'>예정된 강의가 없습니다</td></tr>";
								$("#meeting_list >  tbody:last").append(setHtml).trigger("create");
								$("#meeting_list01 >  tbody:last").append(setHtml).trigger("create");
								setHtml = "";
						}
							
						var pageData = result.pageInfo;
						
						if(pageData.length > 0){
							for (var a = 0; a < pageData.length; a++ ){
								if (pageData[0].mhsNowgubun == "NOW" && a == 0){
                                    cnt = true; 
							   		$("#sp_classNm").html(pageData[0].mhsClassnm);
							   		$("#sp_classNm_1").html(pageData[0].mhsClassnm);
							   		$("#onMeeting").html(fn_timeSplit(pageData[0].mhsClassstarttime) +"~"+ fn_timeSplit(pageData[0].mhsClassendtime)).attr("css", "onMeeting");
							   		$("#onMeeting_1").html(fn_timeSplit(pageData[0].mhsClassstarttime) +"~"+ fn_timeSplit(pageData[0].mhsClassendtime)).attr("css", "onMeeting");
							   		var meetingInfo = "<p class='meeting_noti'>강사명</p>"
							   		                + "<p class='meeting_part'>"+ pageData[0].mhsClassintro+"</p>"
							   		                + "<p class='meeting_user'>"+ pageData[0].mhsTeachernm+"</p>";
							   		$("#user_nam_text-right").html(meetingInfo);
							   		$("#user_nam_text-right_1").html(meetingInfo);
							   			
							   	}else if (pageData[0].mhsNowgubun == "NEXT" && a == 0) {
								    cnt = true;
							   		$("#sp_classNm").html("현재 진행중인 강의가 없습니다");
							   		$("#sp_classNm_1").html("현재 진행중인 강의가 없습니다");
							   		$("#onMeeting").html(
							   		parseInt($("#hid_time").val())+":00" +"~"+ 
							   		        fn_timeSplit(pageData[0].mhsClassstarttime)).attr("css", "offMeeting");
							   		$("#onMeeting_1").html(
									   		parseInt($("#hid_time").val())+":00" +"~"+ 
									   		fn_timeSplit(pageData[0].mhsClassstarttime)).attr("css", "offMeeting");
							   		$("#user_nam_text-right").html("");
							   		$("#user_nam_text-right_1").html("");
							   		$("#sp_nextInfo").html(fn_timeSplit(pageData[0].mhsClassstarttime) +"~"+ fn_timeSplit(pageData[0].mhsClassendtime) + " " + pageData[0].mhsTeachernm );
							   		$("#sp_nextInfo_1").html(fn_timeSplit(pageData[0].mhsClassstarttime) +"~"+ fn_timeSplit(pageData[0].mhsClassendtime) + " " + pageData[0].mhsTeachernm );
							   	}
								
							   	if (pageData.length > 1){
							   		$("#sp_nextInfo").html(fn_timeSplit(pageData[a].mhsClassstarttime) +"~"+ fn_timeSplit(pageData[a].mhsClassendtime) + " " + pageData[a].mhsTeachernm );
							   		$("#sp_nextInfo_1").html(fn_timeSplit(pageData[a].mhsClassstarttime) +"~"+ fn_timeSplit(pageData[a].mhsClassendtime) + " " + pageData[a].mhsTeachernm );
							   	}
								
								if (pageData.length == 1){
							   		$("#sp_nextInfo").html("예정된 강의가 없습니다.");
							   		$("#sp_nextInfo_1").html("예정된 강의가 없습니다.");
							   	}
							}
							
							
							
						}
						if (pageData.length ==  0){								
						
								$("#sp_classNm").html("현재 진행중인 강의가 없습니다");
							   	$("#sp_classNm_1").html("현재 진행중인 강의가 없습니다");
							   	$("#onMeeting").html(
							   		parseInt($("#hid_time").val())+":00" +"~23:00").attr("css", "offMeeting");
							   		$("#onMeeting_1").html(
									   		parseInt($("#hid_time").val())+":00" +"~23:00").attr("css", "offMeeting");
							   		$("#user_nam_text-right").html("");
							   		$("#user_nam_text-right_1").html("");							   		
									$("#sp_nextInfo").html("예정된 강의가 없습니다.");
							   		$("#sp_nextInfo_1").html("예정된 강의가 없습니다.");
								
					  }

						
				     }else{
				    	 //로그인 fail 날때 처리 
				    	 
				     }	
				},
				error : function(e) {
					console.log(e);
				}
			});	
	  }
	  function fn_timeSplit(time){
		  var timeSplit = "";
		  if (time.length> 3){
			  timeSplit = time.substring(0,2)+":"+time.substring(2,4);
		  }else{
			  timeSplit = time;
		  }
		  return timeSplit;
	  }
  </script>
  
  </script>
</form:form>  
</body>
</html>