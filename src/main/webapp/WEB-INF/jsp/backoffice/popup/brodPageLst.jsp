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
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/pops.css">
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="">

<input type="hidden" name="centerId" id="centerId" value="${centerId }" />
<form:hidden path="brodCode" id="brodCode"/>
<div class="popcontents">
		<div class="header">
			<h2>방송 편성표</h2>
		</div>
		<div class="textT">
			<table class="journal manage_box">
				<thead>
					<tr>
					  	<th>재생시간</th>
					  	<th>재생콘텐츠</th>
					  	<th>구분</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList }" var="brodOrg" varStatus="status">
						<tr>
							<td>${brodOrg.brodTime }</td>
							<td style="text-align:left">${brodOrg.orignlFileNm }</td>
							<td>
							 <c:choose>
								   <c:when test="${brodOrg.brodSeq eq '0' }">특정방송</c:when>
								   <c:otherwise>일반방송</c:otherwise>
								</c:choose>	
							</td>
						</tr>			
						</c:forEach>
				</tbody>
			</table>			
		</div>
		<div class="footerBox">
		        <a href="javascript:excel_down();" class="yellowBtn" id="btnUpdate">EXECL DOWN</a>
				<a href="javascript:self.close();" class="grayBtn" id="btnUpdate">닫기</a>			
		</div>	
		
	</div>
</form:form>
    <script type="text/javascript">
       function excel_down(){
    	    $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/ContentBrodExcel.do").submit();    	   
       }
    </script>
</body>
</html>		