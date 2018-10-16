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
    <title>DID운영관리</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	
	<link rel="stylesheet" type="text/css" href="/css/calendar.css">
    <script type="text/javascript" src="/js/new_calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
	<style>
		.play table{margin:0; border:0;border-right:1px solid #e1e1e1;border-left:1px solid #e1e1e1; }
	</style>
</head>
<body>
   <noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

 <span id="backgroundgif"></span>
 <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/playCenterInfo.do">
 <input type="hidden" name="pageIndex" id="pageIndex" value="${regist.pageIndex }">
 <input type="hidden" name="mode" id="mode" >
 <input type="hidden" name="centerId" id="centerId" value="${regist.centerId }">
 <input type="hidden" name="brodCode" id="brodCode" value="${regist.brodCode }">
  
   <div id="wrap">
		<c:import url="/backoffice/inc/emart_header.do" />
		<div class="left-contain">
			<c:import url="/backoffice/inc/cms_left.do" />
			<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
		</div>	
		<div class="container">
			<div class="main-content">
				<div class="content">
					<!--//상단 탭메뉴-->
					<ul class="topMenu" >
						<li class="active"><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점 관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playContentList.do" class="playMedia">음원파일관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodBasic.do" class="playMedia">기본음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentList.do" class="playContents">음원콘텐츠관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentPlayList.do" class="playContents">스케줄음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playShedule.do" class="playShedule">음원콘텐츠배포</a></li>
						<li><a href="/backoffice/sub/brodManage/playSheduleStatus.do" class="playShedule">콘텐츠배포현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--단말기관리-->
						<div class="con_title whiteBox ">
							<h2><img src="/img/centerIcon.png"> 지점별 콘텐츠 스케줄입니다.</h2>
							<div class="searchBox">
								<span>지점명 : </span>								
								<form:select path="otherCenterId" id="otherCenterId" title="재생간격" onChange="javascript:View_CeneterBrod();">
							        <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${centerCombo}" itemValue="centerId" itemLabel="centerNm"/>
							   </form:select>
								
									
								<a href="javascript:brodRest();" id="btnReset" class="blueBtn">재시작</a>								
								<!--  이쪽 부분 버튼 확인 여부  -->
								<a href="javascript:brodList()" id="btnList" class="blueBtn">방송현황 확인</a>
								
								
								<!--  이쪽 부분 버튼 확인 여부  끝 부분 -->
								<div class="footerBox">
								    
								    <form:input path="centerAnniverDay" id="centerAnniverDay" title="시작일" size="15"  onChange="javascript:changeDay()"/>
									개점시간 : <span id="centerStartTime"></span> |  폐점시간 : <span id="centerEndTime"></span>
									기념일 구분 : <span id="brodGubun"></span>
									<a href="javascript:center_Anniver();" class="grayBtn">기념일관리</a>									
								</div>
								<div class="clear"> </div>
							</div>
							<!--테이블시작-->
							<table class="play play_cont">
								<thead>								 
									<tr>
										<c:set var="codeDc" value="${regist.codeDc}"></c:set>
									    <%
									      for (int i =0; i < (Integer.parseInt(pageContext.getAttribute("codeDc").toString())/10) ; i++ ){						    	  
									    %>
										<th><%=i%>0분</th>
										<% } %>	
									</tr>								
									<tr>							
										 <c:set var="media" value="media${status.count }" ></c:set>
									    <%
									      for (int i =0; i < (Integer.parseInt(pageContext.getAttribute("codeDc").toString())/10) ; i++ ){						    	  
									    %>
										<td valign="top">
										 <span id="brodContent<%=i%>"></span>
										</td>
										<% } %>
									</tr>									
								</thead>
							</table>		
							<input type="hidden"  name="mediaCnt" id="mediaCnt" value="<%=(Integer.parseInt(pageContext.getAttribute("codeDc").toString())/10)%>" />
							<div class="footerBox">
								<a href="javascript:open_brod();" class="yellowBtn">음원변경</a>		
							</div>	
							<table>
								<thead>
									<tr>							
										<th>콘텐츠ID</th>
										<th>콘텐츠명</th>
										<th>특정방송여부</th>
										<th>적용기간</th>
										<th>반복재생</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${brodAnniver}" var="brodAnnerInfo" varStatus="status">								
								<tr>
								  <td style="text-align:center">${brodAnnerInfo.brodAnnSeq}</td>
								  <td><a href="javascript:contentSpReg('Edt','${brodAnnerInfo.brodAnnSeq}')">${brodAnnerInfo.anniverName}</a></td>
								  <td style="text-align:center">${brodAnnerInfo.codeNm}</td>
								  <td style="text-align:center">${brodAnnerInfo.anniverStartDay}~${brodAnnerInfo.anniverEndDay}</td>
								  <td style="text-align:center">${brodAnnerInfo.anniversaryTime}</td>								  
								</tr>								
								</c:forEach>
								</tbody>
							</table>	
							
							
									
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
  </form:form>
  <script type="text/javascript">
  $(document).ready(function() {
	     //******************************************************************************
	     // 상세검색 달력 스크립트
	     //******************************************************************************
		 var clareCalendar = {
				 monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
				 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
				 weekHeader: 'Wk',
				 dateFormat: 'yymmdd', //형식(20120303)
				 autoSize: false, //오토리사이즈(body등 상위태그의 설정에 따른다)
				 changeMonth: true, //월변경가능
				 changeYear: true, //년변경가능
				 showMonthAfterYear: true, //년 뒤에 월 표시
				 buttonImageOnly: true, //이미지표시
				 buttonText: '달력선택', //버튼 텍스트 표시
				 buttonImage: '/images/calendar.gif', //이미지주소
				 showOn: "both", //엘리먼트와 이미지 동시 사용(both,button)
				 yearRange: '1990:2020' //1990년부터 2020년까지
			   };			  
		  $("#centerAnniverDay").datepicker(clareCalendar);
		  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
		  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
			  
		  
		  for (var i=0; i < $("#mediaCnt").val(); i++){
				spinHtml(i);					  
		  }	
		  brod_info();
     });
	function center_Anniver(){
		  var url = "/backoffice/sub/basicManage/centerAnniList.do?centerId="+$("#centerId").val();
	      window.open(url,"기념일 정보 수정", 'width=800,height=550,top=100,left=650,scrollbars=auto');
	}
	//음원 변경 
	function open_brod(){		
		$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentView.do").submit();
	}
    //방송 리스트     
    function brodList(){
    	///popup/play_list.html;
    	 var url = "/backoffice/sub/brodManage/ContentBrodReport.do?brodCode="+$("#brodCode").val()+"&centerId="+$("#centerId").val();	      
	      window.open(url,"contentReport", 'width=800,height=550,top=100,left=650,scrollbars=auto');
    }
    //지점 변경 
    function View_CeneterBrod(){
    	location.href="/backoffice/sub/brodManage/playCenterInfo.do?centerId="+$("#otherCenterId").val();    	
    }
    //방송 재시작
    function brodRest(){
    	if (yesterDayConfirm( $("#centerAnniverDay").val(), "재시작 일을 오늘 날짜 이전 날짜를 선택 하셨습니다.") == false) return;	    	
    	var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
    	$("#backgroundgif").html(loadingimg);
    	
    	
    	apiExecute(
				"POST", 
				"/backoffice/sub/brodManage/ContentBrodConfirm.do",
				{
					brodCode : $("#brodCode").val(),
					centerId : $("#centerId").val(),
					centerSearchDay : $("#centerAnniverDay").val()
				},
				null,				
				function(result) {
					$("#backgroundgif").html('');
					if (result != null) {
						if (result == "O"){
							alert("정상적으로 입력 되었습니다.");
						}else {
							alert("작업 도중 문제가 발생 하였습니다.");
						}
					}	
					
				},
				null,
				null
			);	
    }    
    function brodResport(){
    	apiExecute(
				"POST", 
				"/backoffice/sub/brodManage/brodCenterCnt.do",
				{
					brodCode : $("#brodCode").val(),
					centerId : $("#centerId").val(),
					centerSearchDay : $("#centerAnniverDay").val()
				},
				null,				
				function(result) {				
					if (result != null) {
						if (result == "O"){
							alert("정상적으로 입력 되었습니다.");
						}else {
							alert("작업 도중 문제가 발생 하였습니다.");
						}
					}							
				},
				null,
				null
			);	
    }
    
    function brod_info(){
    	 //이 날짜 이후에 방송 현황 확인 함수로 변경
    	 apiExecute(
					"POST", 
					"/backoffice/sub/basicManage/selectCenterTimeInfo.do",
					{
						centerId : $("#centerId").val(),
						centerSearchDay : $("#centerAnniverDay").val()
					},
					null,				
					function(result) {				
						if (result != null && result !="") {
							//방송 정보 보여주기 							
							var arrayTimeInfo = result.split("/");
							if (arrayTimeInfo[0].length > 3){
								$("#centerStartTime").html(arrayTimeInfo[0].substring(0,2)+":"+arrayTimeInfo[0].substring(2,4));	
							}
							if (arrayTimeInfo[1].length > 3){
								$("#centerEndTime").html(arrayTimeInfo[1].substring(0,2)+":"+arrayTimeInfo[1].substring(2,4));	
							}
							if (arrayTimeInfo[3] == "ANN"){
								$("#brodGubun").html("  <b>기념일</b>");
								$("#btnReset").css("display","none");
								
								
							}else {
								$("#brodGubun").html("  일반");
								var toDay = getAgoDate(0,0,0);
								if (toDay == $("#centerAnniverDay").val()){
									$("#btnReset").css("display","");	
								}else {
									$("#btnReset").css("display","none");	
								}
								
							}	
							//arrayTimeInfo[2] '음원 코드
							
							
						}							
					},
					null,
					null
				);	
    	 $("#otherCenterId").val($("#centerId").val());
    }
    function changeDay(){
    	if (any_empt_line_id("centerAnniverDay", "기념일을 선택 하지 않았습니다.") == false) return;
    	//$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/playCenterInfo.do").submit();    	
    	location.href="/backoffice/sub/brodManage/playCenterInfo.do?centerId="+$("#otherCenterId").val()+"&centerAnniverDay="+$("#centerAnniverDay").val();
    }
    function spinHtml(code){
		  var code1 = "";
		  if (code != "0"){ 
			  code1 = code + "0"; 
		  }else {
			  code1 = code;
		  }
		  //삭제 부분 제거 
		  apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/timeContentLst.do",
					{
						brodCode : $("#brodCode").val(),
						brodDay : $("#centerAnniverDay").val(),
						timeInterval : code1
					},
					null,				
					function(result) {				
						if (result.contentList != null) {
							var timeHtml = "<table>"
							var totalTime = "0";						
							for (var i=0; i<result.contentList.length; i++) {
								var obj = result.contentList[i];
								timeHtml += "<tr><Td><a href='javascript:contentReg(&#39;Edt&#39;,&#39;"+ obj.brodSeq +"&#39;)'>"+ obj.orignlFileNm+"</a>&nbsp;&nbsp;재생시간:"+obj.fileThumnail+"<br>"+dayConvert(obj.contentStartDay)+"~"+dayConvert(obj.contentEndDay)+"</td><tr>";								
								totalTime = parseInt(totalTime) + parseInt(obj.playTime);
							}
							var min = parseInt(parseInt(totalTime)/60);
							var sec = parseInt(totalTime) - ( parseInt(min) * 60  );							
							timeHtml += "<tr><td style='background:#D2E1FF'>총재생시간:"+min+":"+sec+"<td></tr>";
							timeHtml += "</table>";
														
							$("#brodContent"+code).html(timeHtml);
							
						}							
					},
					null,
					null
				);	
	  }
  </script>
</body>
</html>