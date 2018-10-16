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
			    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodContentDetailUpdate.do">
			    <form:hidden path="mode" id="mode"/>
			    <form:hidden path="brodCode" id="brodCode"/>
			    <form:hidden path="brodSeq" id="brodSeq"/>
			    <input type="hidden" name="timeIntervalResult" id="timeIntervalResult">
				<table>
					<!--내용시작-->
					<tbody class="text_left">
						<tr>
							<th>콘텐츠명</th>
							<td>
							  <input type="text" name="brodSearch" id="brodSearch" style="width:120px;">
							  <a href="javascript:search_form();" class="grayBtn" id="btnUpdate">검색</a>	
							  <form:select path="atchFileId" id="atchFileId" title="재생간격" style="width:250px;">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${fileInfo}" itemValue="atchFileId" itemLabel="orignlFileNm"/>
							   </form:select>
							</td>
						</tr>
						<tr>
							<th>운영스케줄</th>
							<td>
							 <form:select path="intervalSection" id="intervalSection" title="재생위치">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${timeInfo}" itemValue="timeCode" itemLabel="timeCode"/>
							 </form:select>
							 분 부터 
							 
							 <form:select path="contentInsertInterval" id="contentInsertInterval" title="재생위치">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${timeInfo}" itemValue="timeCode" itemLabel="timeCode"/>
							 </form:select>간격으로
							 <% 
							  String[] insertLsts = {"1","2","3","4","5","6"};
							 %> 
							 <select id="contentInsert" name="contentInsert">
							    <option value="">선택</option>
							    <c:forEach var="insertLst" items="<%=insertLsts%>">
							      <option value='<c:out value="${insertLst}" />' ><c:out value="${insertLst}" /></option>
							    </c:forEach>							   
							 </select>
							 번 입력
							</td>
						</tr>
						<tr>
							<th>정렬순서</th>
							<td>
							 <% 
							  String[] orderLsts = {"01","02","03","04","05","06","07","08","09","10"};
							 %>
							 <select id="contentOrders" name="contentOrder">
							    <option value="">선택</option>
							    <c:forEach var="orderLst" items="<%=orderLsts%>">
							      <option value='<c:out value="${orderLst}" />' <c:if test='${regist.contentOrder eq orderLst }' >selected </c:if> ><c:out value="${orderLst}" /></option>
							    </c:forEach>							   
							 </select>
							</td>
						</tr>	
						<tr>
							<th>콘텐츠운영날짜</th>
							<td>
							<form:input path="contentStartDay" id="contentStartDay" title="시작일" size="15" value="${regist.contentStartDay }"/>~
							<form:input path="contentEndDay" id="contentEndDay" title="종료일" size="15"  value="${regist.contentEndDay }"/>
							</td>
						</tr>
					</tbody>
				</table>			
				</form:form>	
			<div class="footerBox">
				<a href="javascript:check_form();" class="yellowBtn" id="btnUpdate">등록</a>			
			</div>		
		</div>
	</div>
	<script type="text/javascript">
			$(document).ready(function() {

				if ("${status}" != "" ){
					if ("${status}" == "SUCCESS" ){
						alert("정상처리 되었습니다");		
						//상위 함수 오픈 하기						
						opener.document.location.reload();
						self.close();
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
				 yearRange: '1990:2020' //1990년부터 2020년까지
			   };			  
			  $("#contentStartDay").datepicker(clareCalendar);
			  $("#contentEndDay").datepicker(clareCalendar);
			  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
			  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
		  });
		
		 
		  function search_form(){
			  if (any_empt_line_id("brodSearch", "검색할 콘텐츠를 을 입력 하지 않았습니다.") == false) return;
			  
			  apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodFileSearch.do",
						{
							orgFileNm : $("#brodSearch").val()
						},
						null,				
						function(result) {				
							if (result.atchFileLst != null) {
								
								$("#atchFileId").empty();
        						$("#atchFileId").append("<option value=''>--선택하세요--</option>");						    						
            					for (var i=0; i<result.atchFileLst.length; i++) {
            						var obj = result.atchFileLst[i];        						
            						$("<option value='"+ obj.atchFileId +"'>"+ obj.orignlFileNm +"</option>").appendTo("#atchFileId");
            					}   
							}							
						},
						null,
						null
					);	
		  }	
		  function check_form(){
			  if (any_empt_line_id("contentStartDay", "시작일을 입력 하지 않았습니다.") == false) return;
			  if (yesterDayConfirm( $("#contentStartDay").val(), "시작일이 오늘 날짜 보다 이전을 선택 하셨습니다.") == false) return;			  
			  if (any_empt_line_id("contentEndDay", "종료일을 입력 하지 않았습니다.") == false) return;
			  if (yesterDayConfirm( $("#contentEndDay").val(), "종료일이 오늘 날짜 보다 이전을 선택 하셨습니다.") == false) return;
			  if ($("#contentInsertInterval").val() == "000") { alert("간격시간은 0이 될수 없습니다."); return;}
			  //check_file(); 
			  check_time();
		  }	 
		  function check_file(){
			  apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodFileCheck.do",
						{
							brodCode : $("#brodCode").val(),
							timeInterval : $("#intervalSection").val(),
							atchFileId : $("#atchFileId").val()
						},
						null,				
						function(result) {				
							if (result != null) {
								if (result !="F"){
									check_time();
								}else {
									if (confirm("중복된 파일이 있는데 다시 등록 하시겠습니까?")){
										check_time();
									}
								}
								
							}							
						},
						null,
						null
					);	
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