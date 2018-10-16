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
			    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/ContentSpRegUpdate.do">
			    <form:hidden path="mode" id="mode"/>
			    <form:hidden path="brodCode" id="brodCode"/>
			    <form:hidden path="brodAnnSeq" id="brodAnnSeq"/>
				<table>
					<!--내용시작-->
					<tbody class="text_left">
						<tr>
							<th>스케줄명</th>
							<td>
							 <form:input path="anniverName" id="anniverName" size="25" value="${regist.anniverName }"></form:input>
							</td>
						</tr>	
						<tr>
							<th>콘텐츠명</th>
							<td>
							  <form:select path="atchFileId" id="atchFileId" title="재생간격" style="width:250px;">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${fileInfo}" itemValue="atchFileId" itemLabel="orignlFileNm"/>
							   </form:select>
							</td>
						</tr>		
						<tr>
							<th>콘텐츠운영날짜</th>
							<td>
							<form:input path="anniverStartDay" id="anniverStartDay" title="시작일" size="15"  value="${regist.anniverStartDay }"/>~
							<form:input path="anniverEndDay" id="anniverEndDay" title="종료일" size="15"  value="${regist.anniverEndDay }"/>
							</td>
						</tr>
						<tr>
							<th>특정방송여부</th>
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
			                        <form:options items="${timeInfo}" itemValue="timeCode" itemLabel="timeCode"/>
							</form:select>시간당
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
						    <%
						    String[] minutes = {"00","10","20","30","40","50"};
						    %>
							 <select id="anniversaryTimeTime" name="anniversaryTimeTime">
							    <option value="">선택</option>
							      <option value="">선택</option>
							    <c:forEach var="minute" items="<%=minutes%>">
							      <option value='<c:out value="${minute}" />' <c:if test='${regist.anniversaryTimeTime eq minute }' >selected </c:if> ><c:out value="${minute}" /></option>
							    </c:forEach>								    
							 </select>분
						  </td>
						</tr>
						<tr>
							<th>정렬순서</th>
							<td>							 
							 <%
							   String[] orderLsts = {"01","02","03","04","05","06","07","08","09","10"};
							 %>
							 <select id="anniverOrder" name="anniverOrder">
							    <option value="">선택</option>
							    <c:forEach var="orderLst" items="<%=orderLsts%>">
							      <option value='<c:out value="${orderLst}" />' <c:if test='${regist.anniverOrder eq orderLst }' >selected </c:if> ><c:out value="${orderLst}" /></option>
							    </c:forEach>							   
							 </select>
							 
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
</body>
</html>