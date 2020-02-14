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
    <link href="/css/layout.css" rel="stylesheet" type="text/css" >
    <link href="/css/paragraph.css" rel="stylesheet" type="text/css" >
    <link href="/css/reset.css" rel="stylesheet" type="text/css" >    
    <link rel="stylesheet" type="text/css" href="/css/calendar.css">
    <script type="text/javascript" src="/js/new_calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/leftMenu.js"></script>

<style>
		#wrap { min-height :1080px;}
</style>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodContentUpdate.do">
		<form:hidden path="mode" id="mode" />	
		<form:hidden path="pageUnit" id="pageUnit" />
		<form:hidden path="searchCondition" id="searchCondition" />
		<form:hidden path="searchKeyword" id="searchKeyword" />
		<form:hidden path="pageIndex" id="pageIndex" />
		<form:hidden path="brodCode" id="brodCode" />
		
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
						<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점 관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playContentList.do" class="playMedia">음원파일관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodBasic.do" class="playMedia">기본음원관리</a></li>	
						<li><a href="/backoffice/sub/brodManage/brodPlayInfo.do" class="playShedule">기본음원재생현황</a></li>					
						<li class="active"><a href="/backoffice/sub/brodManage/brodContentList.do" class="playContents">음원콘텐츠관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentPlayList.do" class="playContents">스케줄음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playShedule.do" class="playShedule">음원콘텐츠배포</a></li>
						<li><a href="/backoffice/sub/brodManage/playSheduleStatus.do" class="playShedule">콘텐츠배포현황</a></li>
						<div class="clear"></div>					
					</ul>
					<!--//하단 콘텐츠-->
					
					<div class="con">
						<!--음원파일관리-->
						<div class="con_title whiteBox ">
						   <div class="searchBox">									
								<div class="footerBox">
								<a href="/backoffice/sub/equiManage/didList.do" class="yellowBtn">목록</a>			
								<a href="javascript:check_form()" class="grayBtn">등록</a>	
								<div class="clear"></div>			
							    </div>
						   </div>
						   <!--  테이블 넣을 자리  -->		
						   <table>
								<tbody>
									<tr>
										<th colspan="10">콘텐츠 정보</th>
									</tr>
									<tr>	
										<th>콘텐츠명</th>
										<td style="text-align:left"><form:input path="brodName" id="brodName" title="brodName" size="15" value="${regist.brodName }"/></td>										
										<th>반복재생간격</th>
										<td style="text-align:left">
										  <form:select path="brodInterval" id="brodInterval" title="재생간격">
										         <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${brodInterval}" itemValue="code" itemLabel="codeNm"/>
										   </form:select>
										</td>
										<th>기본음원선택</th>
										<td style="text-align:left">
										  <form:select path="basicFileId" id="basicFileId" title="기본음원선택">
										         <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${basicInfo}" itemValue="basicCode" itemLabel="basicGroupNm"/>
										   </form:select>
										</td>
									</tr>
									<tr>		
									    <th>이벤트유무</th>
										<td style="text-align:left">
										  <input type="radio" name="secGubun" value="SECGUBUN01" <c:if test="${regist.secGubun == 'SECGUBUN01' }"> checked </c:if> />일반스케줄
										  <input type="radio" name="secGubun" value="SECGUBUN02" <c:if test="${regist.secGubun == 'SECGUBUN02' }"> checked </c:if> />이벤트스케줄
										</td>
										<th>시작일/종료일</th>
										<td style="text-align:left">
										  <form:input path="brodStartDay" id="brodStartDay" title="brodStartDay" size="15" />
										  ~<form:input path="brodEndDay" id="brodEndDay" title="brodEndDay" size="15" />
										</td>										
										<th>사용유무</th>
										<td style="text-align:left">
										<input type="radio" name="brodUseYn" value="Y" <c:if test="${regist.brodUseYn == 'Y' }"> checked </c:if> />사용
										<input type="radio" name="brodUseYn" value="N" <c:if test="${regist.brodUseYn == 'N' }"> checked </c:if> />사용안함
										</td>
									</tr>
								</tbody>
							</table>
						   <!--  테이블 넣을 자리  끝 부분-->
		
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
			if ("${status}" != ""){
				if ("${status}" == "SUCCESS") {
					alert("정상 처리 되었습니다");    			    			
				}else{
					alert("작업 도중 문제가 발생 하였습니다.");
				}		
				 location.href = "/backoffice/sub/brodManage/brodContentList.do";	
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
			 yearRange: '1990:2099' //1990년부터 2020년까지
		   };			  
		  $("#brodStartDay").datepicker(clareCalendar);
		  $("#brodEndDay").datepicker(clareCalendar);
		  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
		  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
		});    	   
      function check_form(){
    	  $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentUpdate.do").submit();		           	 
      }
     
    </script>
</body>
</html>		