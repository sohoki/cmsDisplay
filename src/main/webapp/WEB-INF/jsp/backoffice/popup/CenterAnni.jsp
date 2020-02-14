<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>지점 기념일 관리</title>
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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/centerAnniList.do">
<form:hidden path="centerId" id="centerId" />
<form:hidden path="centerAnniday" id="centerAnniday" />
<form:hidden path="mode" id="mode" />
		<div class="header">
			<h2>기념일 관리</h2>
		</div>
		<div class="textT">
			<!--테이블시작-->
			    <table>
					<!--내용시작-->
					<tbody class="text_left">
						<tr>
							<th>기념일</th>
							<th>오픈시간</th>
							<th>패점시간</th>								
							<th>연동콘텐츠</th>							
							<th>삭제</th>
						</tr>
						<c:forEach items="${resultList }" var="centerAnniInfo" varStatus="status">
						<tr>
							<td>${centerAnniInfo.centerAnniStartDay } ~  ${centerAnniInfo.centerAnniEndDay }</td>
							<td>${fn:substring(centerAnniInfo.startTime, 0,2)}:${fn:substring(centerAnniInfo.startTime, 2,4)}</td>
							<td>${fn:substring(centerAnniInfo.endTime, 0,2)}:${fn:substring(centerAnniInfo.endTime, 2,4)}</td>								
							<td>${centerAnniInfo.brodName }</td>
							<td><a href="javascript:center_anniEdt('${centerAnniInfo.centerAnniday }')">수정</a>|
							<a href="javascript:del_anni('${centerAnniInfo.centerAnniday }')">삭제</a></td>
						</tr>		
						</c:forEach>	
						<tr>
							<td>
							 <form:input path="centerAnniStartDay" id="centerAnniStartDay" title="시작일" size="10" />~
							<form:input path="centerAnniEndDay" id="centerAnniEndDay" title="종료일" size="10" />
							</td>
							<td><form:input path="startTime" id="startTime" title="시작시간" size="4"  numberonly="true"/></td>
							<td><form:input path="endTime" id="endTime" title="종료시간" size="4"  numberonly="true"/></td>								
							<td>
							<form:select path="brodCode" id="brodCode" title="재생위치" onChange="javascript:dayChange();">
							        <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${brodLst}" itemValue="brodCode" itemLabel="brodName"/>
							</form:select>
							</td>
							<td><a href="javascript:check_form()" class="yellowBtn" id="btnUpdate">입력</a></td>
						</tr>							
					</tbody>
				</table>	
				<!--  페이지 리스트  -->				
		</div>
	</div>
    </form:form>		
	<script type="text/javascript">
			$(document).ready(function() {
				if ("${status}" != "" ){
					if ("${status}" == "SUCCESS" ){
						alert("정상처리 되었습니다");								
				        document.location.href="/backoffice/sub/basicManage/centerAnniList.do?centerId="+$("#centerId").val(); 
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
			  $("#centerAnniStartDay").datepicker(clareCalendar);
			  $("#centerAnniEndDay").datepicker(clareCalendar);
			  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
			  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
		  });	
		  function dayChange(){
			  
			  if ($("#brodCode").val() != ""){
				  apiExecute(
							"POST", 
							"/backoffice/sub/basicManage/centerAnniDetailInfo.do",
							{
								brodCode : $("#brodCode").val()
							},
							null,				
							function(result) {				
								if (result != null &&  result != "") {
									var eventDay =  result.split("/");
									
									$("#centerAnniStartDay").val(eventDay[0]);
									$("#centerAnniEndDay").val(eventDay[1]);
								}
							},
							null,
							null
						);
			  }
			  
			  
		  }
		  function center_anniEdt(code){
			  var centerAnniday = document.getElementById("centerAnniday").value;
			  apiExecute(
						"POST", 
						"/backoffice/sub/basicManage/centerAnniDetail.do",
						{
							centerAnniday : code
						},
						null,				
						function(result) {				
							if (result.anniInfo != null ) {
								obj = result.anniInfo;
								if (obj != ""){
									$("#mode").val('Edt');
									$("#centerAnniStartDay").val(obj.centerAnniStartDay);
									$("#centerAnniEndDay").val(obj.centerAnniEndDay);
									$("#startTime").val(obj.startTime);
									$("#endTime").val(obj.endTime);
									$("#brodCode").val(obj.brodCode);
									$("#centerAnniday").val(code);
									$("#btnUpdate").text("수정");
								}
							}
						},
						null,
						null
					);
		  }
		  function del_anni(code){
			  apiExecute(
						"POST", 
						"/backoffice/sub/basicManage/centerAnniDel.do",
						{
							centerAnniday : code
						},
						null,				
						function(result) {				
							if (result != null && result != "0") {
								alert('<spring:message code="success.common.delete" />');
								$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/centerAnniList.do").submit();
							}else {
								alert('<spring:message code="fail.common.delete" />');
							}
						},
						null,
						null
					);	
		  }
		  function check_form(){
			  if (any_empt_line_id("centerAnniStartDay", "시작일을 입력 하지 않았습니다.") == false) return;
			  if (any_empt_line_id("centerAnniEndDay", "종료일을 입력 하지 않았습니다.") == false) return;
			  regCheck();
			  //$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/centerAnniUpdate.do").submit();
		  }	 	
		  function regCheck(){
			      apiExecute(
							"POST", 
							"/backoffice/sub/basicManage/centerAnniCntCheck.do",
							{
								centerId : $("#centerId").val(),
								centerAnniStartDay : $("#centerAnniStartDay").val(),
								centerAnniEndDay : $("#centerAnniEndDay").val(),
								centerAnniday : $("#centerAnniday").val()
								
							},
							null,				
							function(result) {				
								if (result != null && result != "0") {
									alert("이미 등록된 기념일이  존재 합니다.");									
								}else {
									$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/centerAnniUpdate.do").submit();
								}
							},
							null,
							null
						);			  
		  }
	</script>
</body>
</html>