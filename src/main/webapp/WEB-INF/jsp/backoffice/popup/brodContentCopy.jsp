<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/pops.css">
		
    
    <link rel="stylesheet" type="text/css" href="/css/calendar.css">
    <script type="text/javascript" src="/js/new_calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<div class="contents">
		<div class="header">
			<h2>이마트 기본음원 등록</h2>
		</div>
		<div class="textT">
			<!--테이블시작-->
			    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodContentDetailCenterUpdate.do">
			    <form:hidden path="mode" id="mode"/>
			    <form:hidden path="insert_brodCode" id="insert_brodCode"/>
			    <form:hidden path="atchFileId" id="atchFileId"/>			    
				<table>
					<!--내용시작-->
					<tbody class="text_left">
						<tr>
							<th>콘텐츠명</th>
							<td>
							  ${fileInfo}
							</td>
						</tr>
						<tr>
						  <th>등록구분</th>
						  <Td>
						     <input type="radio" id="regGubun" name="regGubun" value="D" checked onClick="javascript:viewPage('D')">콘텐츠등록
						     <input type="radio" id="regGubun" name="regGubun" value="A" onClick="javascript:viewPage('A')">특정방송등록
						  </Td>
						</tr>
						
						<tr id="regSchedule">
							<th>운영스케줄</th>
							<td>
							 <form:select path="intervalSection" id="intervalSection" title="재생위치">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${timeInfo}" itemValue="timeCode" itemLabel="timeCode"/>
							 </form:select>							 
							</td>
						</tr>
						<tr id="regScheduleOrder">
							<th>정렬순서</th>
							<td>
							 <% 
							  String[] orderLsts = {"01","02","03","04","05","06","07","08","09","10"};
							  String[] orderLst_txt = {"[01]최우선송출","[02]우선송출광고","[03]상업광고","[04]상업광고최종","[05]점포단독방송","[06]쇼핑뉴스상위","[07]쇼핑뉴스","[08]쇼핑뉴스최종","[09]추가방송","[10]최종방송"};
							  int order_i = 0;
							 %>
							 <select id="contentOrders" name="contentOrder">
							 	<option value="">--선택하세요--</option>
						   		<c:forEach var="orderLst" items="<%=orderLsts%>" varStatus="orderLst_status">
							      <option value='<c:out value="${orderLst}" />' <c:if test='${regist.contentOrder eq orderLst }' >selected </c:if> ><c:out value="<%=orderLst_txt[order_i]%>" /></option>
							      <% order_i++; %>
							    </c:forEach>
							 </select>
							</td>
						</tr>	
						<tr id="annverTitle">
							<th style="width:180px;">스케줄명</th>
							<td>
							 <form:input path="anniverName" id="anniverName" size="25"></form:input>
							</td>
						</tr>	
						<tr id="annverGubun">
							<th>특정방송구분</th>
							<td>
							 <form:select path="anniversaryGubun" id="anniversaryGubun" title="재생위치" onClick="javascript:view_Interval();">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${anniversaryGubun}" itemValue="code" itemLabel="codeNm"/>
							 </form:select>							
							</td>
						</tr>
						<tr id="annverRepeter">
							<th>시간별주기</th>
							<td>
							<form:select path="anniversaryTime" id="anniversaryTime" title="재생위치">
							        <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${timeInfo1}" itemValue="timeCode" itemLabel="timeCode"/>
							</form:select>시간당
							<%
						    String[] minutes = {"00","10","20","30","40","50"};
						    %>
							 <select id="anniversaryStartTime" name="anniversaryStartTime">
							    <option value="">선택</option>							      
							    <c:forEach var="minute" items="<%=minutes%>">
							      <option value='<c:out value="${minute}" />' <c:if test='${regist.anniversaryStartTime eq minute }' >selected </c:if> ><c:out value="${minute}" /></option>
							    </c:forEach>								    
							 </select>분
							</td>
						</tr>
						<tr id="annverTime">
						  <th>방송시간</th>
						  <td>
						    <%
						    String[] HourLsts = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
						    %>
						     
							 <select id="anniversaryTimeHour" name="anniversaryTimeHour">
							    <option value="">선택</option>
							    <c:forEach var="HourLst" items="<%=HourLsts%>">
							      <option value='<c:out value="${HourLst}" />' <c:if test='${regist.anniversaryTimeHour eq HourLst }' >selected </c:if> ><c:out value="${HourLst}" /></option>
							    </c:forEach>							   
							 </select>
						        시
						     <select id="anniversaryTimeTime" name="anniversaryTimeTime">
							    <option value="">선택</option>							      
							    <c:forEach var="minute" items="<%=minutes%>">
							      <option value='<c:out value="${minute}" />' <c:if test='${regist.anniversaryTimeTime eq minute }' >selected </c:if> ><c:out value="${minute}" /></option>
							    </c:forEach>								    
							 </select>분
						  </td>
						</tr>
						<tr id="annverOrder">
							<th>정렬순서</th>
							<td>	
							 <%
							 	String[] an_orderLsts = {"01","02","03","04","05","06","07","08","09","10"};
							 %>
							 <select id="anniverOrder" name="anniverOrder">
							 	<!-- 음원 파일 추가 삽입시 특정방송 non selected -->
							    <option value="">--선택하세요--</option>
							    <c:forEach var="orderLst" items="<%=an_orderLsts%>">
							      <option value='<c:out value="${an_orderLsts}" />'><c:out value="${orderLst}" /></option>
							    </c:forEach>
							 </select>
							</td>
						</tr>
						<tr>
							<th>콘텐츠운영날짜</th>
							<td>
							<form:input path="contentStartDay" id="contentStartDay" title="시작일" size="15"/>~
							<form:input path="contentEndDay" id="contentEndDay" title="종료일" size="15" />
							</td>
						</tr>
					</tbody>
				</table>			
				</form:form>	
			<div class="footerBox">
				<a href="javascript:check_form();" class="yellowBtn" id="btnUpdate">등록</a>			
			</div>		
			<a href="javascript:self_close()"><span id="resultMessage"></span></a>
		</div>
	</div>
	<script type="text/javascript">
			$(document).ready(function() {
				/*
				$("#contentOrders").on("change", function(){
					alert($("#contentOrders").val());
				});
				*/
				if ("${status}" != "" ){
					if ("${status}" == "SUCCESS" ){
						$("#resultMessage").html("${message}");
						
						//상위 함수 오픈 하기						
						
					}else  {
						alert("처리 도중 문제가 발생 하였습니다.");
						document.form.reset();
					}	
				}	
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
				 yearRange: '1990:2099' //1990년부터 2099년까지
			   };			  
			  $("#contentStartDay").datepicker(clareCalendar);
			  $("#contentEndDay").datepicker(clareCalendar);
			  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
			  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
			  viewPage('D');
		  });		
		  function self_close(){
			  //추후 확인 
			  window.opener.fileCenterView($("#atchFileId").val());
			  self.close();
		  }
		  function viewPage(code){
			  if (code == "A"){
			     
			     $("#regSchedule").css("display","none").css("width","100%" );
			     $("#regScheduleOrder").css("display","none").css("width","100%" );
			     $("#annverTitle").css("display","").css("width","100%" ); 
			     $("#annverGubun").css("display","").css("width","100%" );
			     $("#annverRepeter").css("display","").css("width","100%" );
			     $("#annverOrder").css("display","").css("width","100%" );
			     $("#annverTime").css("display","none").css("width","100%" );
			     $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentAnnDetailCenterUpdate.do")
			  }else {
			     $("#regSchedule").css("display","").css("width","100%" );
			     $("#regScheduleOrder").css("display","").css("width","100%" );			     
			     $("#annverTitle").css("display","none").css("width","100%" );
			     $("#annverGubun").css("display","none").css("width","100%" ); 
			     $("#annverRepeter").css("display","none").css("width","100%" );			     
			     $("#annverTime").css("display","none").css("width","100%" );
			     $("#annverOrder").css("display","none").css("width","100%" );			     
			     $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentDetailCenterUpdate.do")
			  }
		  }
		  function view_Interval(){
			  if ($("#anniversaryGubun").val() == "ANNGUBUN01"){				  
				  $("#annverRepeter").css("display","").css("width","100%" ); 
				  $("#annverTime").css("display","none").css("width","100%" );
			  }else {				  
				  $("#annverRepeter").css("display","none").css("width","100%" ); 
				  $("#annverTime").css("display","").css("width","100%" );				  
			  }				
		  }
		  function check_form(){
			  if (any_empt_line_id("contentStartDay", "시작일을 입력 하지 않았습니다.") == false) return;
			  if (yesterDayConfirm( $("#contentStartDay").val(), "시작일이 오늘 날짜 보다 이전을 선택 하셨습니다.") == false) return;			  
			  if (any_empt_line_id("contentEndDay", "종료일을 입력 하지 않았습니다.") == false) return;
			  if (yesterDayConfirm( $("#contentEndDay").val(), "종료일이 오늘 날짜 보다 이전을 선택 하셨습니다.") == false) return; 			  			  
			  $("form[name=regist]").submit();
		  }	 
		  
		  function check_time(){
			    apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodFileTimeCheck.do",
						{
							brodCode : $("#brodCode").val(),
							timeInterval : $("#intervalSection").val(),
							timeIntervalInsertCnt : $("#contentInsert").val(),
							contentInsertInterval : $("#contentInsertInterval").val(),
							contentStartDay :$("#contentStartDay").val(),
							contentEndDay :$("#contentEndDay").val(),
							atchFileId : $("#atchFileId").val()
						},
						null,				
						function(result) {				
							if (result != null) {
								if (result ==""){
									alert("총 10분을 초과 하는 곳이 있습니다. .");
									
								}else {
									//alert("총 10분을 초과 하였습니다.");
									$("#timeIntervalResult").val(result);
									$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentDetailUpdate.do").submit();
									
								}
								
							}							
						},
						null,
						null
					);	
		  }
	</script>
</body>
</html>