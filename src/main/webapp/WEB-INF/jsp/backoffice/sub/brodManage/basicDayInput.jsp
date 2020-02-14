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
<title>이마트 기본음원 등록</title>
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
			    <form:form name="regist" commandName="regist" method="post" action="">
			    <form:hidden path="mode" id="mode"/>
			    <form:hidden path="atchFileId" id="atchFileId"/>
				<table>
					<!--내용시작-->
					<tbody class="text_left">
						<tr>
							<th>기본음원운영날짜</th>
							<td>
							<input type="text" id="basicStartDay" title="시작일" size="15" readonly/> ~ 
							<input type="text" id="basicEndDay" title="종료일" size="15" readonly/>
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
				 showAnim: "slide",
				 yearRange: '1990:2099', //1990년부터 2099년까지
				 beforeShow: function () { 
					 window.resizeTo(550,500);
				 },
				 beforeHide: function () { 
					 window.resizeTo(550,180);
				 }
			   };			  
			  $("#basicStartDay").datepicker(clareCalendar);
			  $("#basicEndDay").datepicker(clareCalendar);
			  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
			  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
		  });
		

		  function check_form(){
			  if (any_empt_line_id("basicStartDay", "시작일을 입력 하지 않았습니다.") == false) return;
			  if (yesterDayConfirm( $("#basicStartDay").val(), "시작일을 오늘 날짜 보다 이전을 선택 하셨습니다.") == false) return;			  
			  if (any_empt_line_id("basicEndDay", "종료일을 입력 하지 않았습니다.") == false) return;
			  if (yesterDayConfirm( $("#basicEndDay").val(), "종료일을 오늘 날짜 보다 이전을 선택 하셨습니다.") == false) return;
			  
			  var startDay = $("#basicStartDay").val();
			  var endDay = $("#basicEndDay").val();
		      var mode=$("#mode").val();
			  var atchFileId=$("#atchFileId").val();
			  $("#brodStartday", opener.document).val(startDay); //jquery 이용
			  $("#brodEndday", opener.document).val(endDay); //jquery 이용
			  if (atchFileId != ""){
				  opener.parent.basicFileIn(atchFileId,mode);  
			  }else {
				  opener.parent.fn_move('L');
				  
			  }
			  self.close();
			  
		  }	 
	</script>
</body>
</html>