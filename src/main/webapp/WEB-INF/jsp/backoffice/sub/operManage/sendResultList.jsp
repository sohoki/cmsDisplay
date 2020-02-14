<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
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
	<script type="text/javascript" src="/js/leftMenu.js"></script>
	<script type="text/javascript">
	jQuery.browser = {};
	(function () {
	    jQuery.browser.msie = false;
	    jQuery.browser.version = 0;
	    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
	        jQuery.browser.msie = true;
	        jQuery.browser.version = RegExp.$1;
	    }
	})();
	</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/operManage/sendResultList.do">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">


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
					    <% LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
					    if (loginVO.getAuthorCode().equals("ROLE_ADMIN") &&  loginVO != null  ){  %>					
						<li ><a href="/backoffice/sub/operManage/xmlList.do" class="conMutiList">전문관리</a></li>
						<% } %>
						<li class="active"><a href="/backoffice/sub/operManage/sendResultList.do" class="xmlList">전문통신현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--전문통신현황-->
						<div class="con_title whiteBox ">
							<h2><img src="/img/mediaText.png"> 전체 전문통신현황 리스트입니다.</h2>
							<div class="searchBox">
									<span>총 : ${totalCnt}개</span>
									<select name="pageUnit" id="pageUnit" >
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
									</select>
									<form:select path="centerId" id="centerId" title="전문구분">
											<form:option value="" label="--선택하세요--"/>
											<form:options items="${selectCenter}" itemValue="centerId" itemLabel="centerNm"/>
									</form:select>	
									<form:select path="xmlProcessName" id="xmlProcessName" title="전문구분">
											<form:option value="" label="--선택하세요--"/>
											<form:options items="${selectProcess}" itemValue="xmlProcessName" itemLabel="processRemark"/>
									</form:select>	
									<span class="blan">
										<form:input path="schStartDay" id="schStartDay" title="스케줄명" size="15" />~
             							<form:input path="schEndDay" id="schEndDay" title="스케줄명" size="15" />
									</span>
									<a href="javascript:search_form()" class="yellowBtn">검색</a>					
			
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										<th>번호</th>
										<th>그룹명</th>
										<th>DID ID</th>
										<th>전문명</th>
										<th>통신시간</th>		
										<th>결과</th>	
										<th>삭제</th>	
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${resultList }" var="sendInfo" varStatus="status">
									<tr>
										<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
										<td>${sendInfo.groupNm }</td>
										<td>${sendInfo.didNm }</td>
										<td>${sendInfo.processRemark }</td>
										<td>
										요청시간:${sendInfo.sendRegDate}&nbsp;
										<c:if test="${sendInfo.didPlayTime ne null}">
									   	<br>응답시간:${sendInfo.didPlayTime}
									   	</c:if>
										</td>
										<td>${sendInfo.sendResult }</td>
										<td class="md_btn"><a href="javascript:del_Msg(''${sendInfo.msgSeq }')" class="delkey">삭제</a></td>
									</tr>			
									</c:forEach>
								</tbody>
							</table>											
							<div class="pageFooter">
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
								<div class="clear"></div>	
							</div>				
						</div>		
					</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
</form:form>
    
    
    
    <script language="javascript" type="text/javascript">
			$(document).ready(function () {
				
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
			function linkPage(pageNo) {
				$(":hidden[name=pageIndex]").val(pageNo);				
				$("form[name=regist]").submit();
			}
			function search_form(){
				if ($("#schStartDay").val() != ""){
					if ($("#schEndDay").val() ==""){
						alert("시작일 입력시 종료일 입력 해야 합니다");
						return ;
					}
				}
				$("form[name=regist]").attr("action", "/backoffice/sub/operManage/sendResultList.do").submit();
			}
		  </script>
</body>
</html>		