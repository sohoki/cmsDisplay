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
    <title>DID운영관리</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >	
	
	<link rel="stylesheet" type="text/css" href="/css/calendar.css">
    <script type="text/javascript" src="/js/new_calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/aten-common.js"></script>
    <script type="text/javascript" src="/js/leftMenu.js"></script>

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/schList.do">		
        <form:hidden path="mode" id="mode"/>	    			      
	    <form:hidden path="pageIndex" />
	    <form:hidden path="pageUnit" id="pageUnit"/>
	    <form:hidden path="searchCondition" id="searchCondition"/>
	    <form:hidden path="searchKeyword" id="searchKeyword"/>
	    <form:hidden path="menuGubun" id="menuGubun" />
	    
	    
<div id="wrap">
		<c:import url="/backoffice/inc/emart_header.do" />
		<div class="left-contain">
			<c:import url="/backoffice/inc/cms_left.do" />
			<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
		</div>
		<div class="container">
			
			<div class="main-content">
				<div class="content">
					<ul class="topMenu" >
						<li class="active"><a href="/backoffice/sub/equiManage/schList.do" class="schedule">그룹&화면 매칭</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--스케줄 관리-->
						<div class="con_title whiteBox ">
						     <h2><img src="../../img/list.png"> 단말기 그룹 및 화면 매칭.</h2>
						    <div class="searchBox">									
								<div class="footerBox">
									<a href="javascript:view_list();" class="yellowBtn">리스트</a>
									<a href="javascript:check_form();" class="grayBtn">등록</a>
								</div>
								<div class="clear"></div>
							</div>
							<table>
								<tbody>
									<tr>
										<th>스케줄 ID</th>
										<td style="text-align:left">
											<form:input path="schCode" id="schCode" title="스케줄 ID" size="15" placeholder="자동할당"/>
										</td>
										<th>스케줄명</th>
										<td style="text-align:left">
										<form:input path="schName" id="schName" title="스케줄명" size="15"/>
										</td>
									</tr>						
									<tr>
										<th>송출 기간</th>
										<td style="text-align:left">
										<form:input path="schStartDay" id="schStartDay" title="스케줄명" size="15" />~
										<form:input path="schEndDay" id="schEndDay" title="스케줄명" size="15" />
										</td>
									</tr>	
									<tr>
										<th>적용 그룹</th>
										<td style="text-align:left">
											<input id="grp_searchKeyword" name="grp_searchKeyword"  placeholder="그룹명 검색" size="15" />&nbsp;<a onclick="grpSearchCall()" class="blueBtn fontSize12">검색</a>&emsp;
											<form:select path="groupCode" id="groupCode" name="groupCode" title="소속">
												<form:option value="" label="--선택하세요--"/>
												<form:options items="${selectGroup}" itemValue="groupCode" itemLabel="groupNm"/>
										   </form:select>
										</td>
										<th>연동 콘텐츠</th>
										<td style="text-align:left">
										    <input type="text" id="conStr" name="conStr" >
										    <a href="javascript:search_form();" class="grayBtn">검색</a>
											<form:select path="contentCode" id="contentCode" title="콘텐츠명">
												 <form:option value="" label="--선택하세요--"/>
												<form:options items="${selectContent}" itemValue="conSeq" itemLabel="conNm"/>
										   </form:select>
										</td>
									</tr>
									<tr>
										<th>긴급 송출</th>
										<td style="text-align:left">
										<c:choose>
											<c:when test="${regist.schEmerGubun == 'Y' }">
												<input type="radio" name="schEmerGubun" value="Y" checked />적용
												<input type="radio" name="schEmerGubun" value="N" />일반
											</c:when>
											<c:otherwise>
											<input type="radio" name="schEmerGubun" value="Y" />적용
												<input type="radio" name="schEmerGubun" value="N" checked />일반
											</c:otherwise>
										</c:choose>
										</td>
										<th>사용유무</th>
										<td style="text-align:left">
										<c:choose>
											<c:when test="${regist.schUseYn == 'N' }">
												<input type="radio" name="schUseYn" value="Y" />사용
												<input type="radio" name="schUseYn" value="N" checked />사용안함	
											</c:when>
											<c:otherwise>
												<input type="radio" name="schUseYn" value="Y" checked />사용
												<input type="radio" name="schUseYn" value="N" />사용안함
											</c:otherwise>
										</c:choose>
										
										

										</td>
									</tr>
																																		
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
     function check_form(){
        	$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schUpdate.do").submit();
        }
     function view_list(){    	 
    	   $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schList.do").submit(); 	   
       } 
     function search_form(){
    	 //if (any_empt_line_id("conStr", "검색할 콘텐츠 명을 입력 하지  않았습니다.") == false) return;
    	 apiExecute(
			"POST", 
			"/backoffice/sub/equiManage/schDetailSearch.do",
			{
				searchKeyword : $("#conStr").val()
			},
			null,				
			function(result) {				
				if (result.schList != null) {
					
					$("#contentCode").empty();
					$("#contentCode").append("<option value=''>--선택하세요--</option>");						    						
   					for (var i=0; i<result.schList.length; i++) {   						
   						var obj = result.schList[i];
   						$("<option value='"+ obj.conSeq +"'>"+ obj.conNm +"</option>").appendTo("#contentCode");
   					}   
				}							
			},
			null,
			null
		);	
     }
     </script>
	 <script language="javascript" type="text/javascript">
			$(document).ready(function () {
				if ("${status}" != "") {
		    		if ("${status}" == "SUCCESS") {
		    			alert("정상 처리 되었습니다");    					    			
		    		}else {
		    			alert("작업 도중 문제가 발생 하였습니다.");
		    		}
		    	}
			    if ($("#mode").val() == "Ins"){   	       
			 		$("input[name=schCode]").attr("disabled", true);		 		
			    }	else {
			    	$("input[name=schCode]").attr("readonly", true);
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
			  $("#schStartDay").datepicker(clareCalendar);
			  $("#schEndDay").datepicker(clareCalendar);
			  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
			  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
			});
			function grpSearchCall(){
				grp_search($("select[name=groupCode]"), $("#grp_searchKeyword").val(), 1);
			}
	 </script>
</body>
</html>		