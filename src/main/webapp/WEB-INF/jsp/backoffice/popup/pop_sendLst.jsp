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
<title>이마트DID운영관리</title>
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
<div class="popcontents">
		<div class="header">
			<h2>전문 현황</h2>
		</div>
		<div class="textT">
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/operManage/xmlUpdate.do">
		<input type="hidden" name="didId" id="didId"  value="${regist.didId }">
		<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">   
		</form:form>
			<table class="journal manage_box">
				<thead>
					<tr>
						<th>번호</th>
					  	<th>DID명</th>
					  	<th>IP</th>					  	
					  	<th>전문명</th>
					  	<th>등록시간</th>
					  	<th>처리여부</th>
					  	<th>처리시간</th>					  	
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList }" var="sendInfo" varStatus="status">
						<tr>
							<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>							
							<td>${sendInfo.didNm }</td>
							<td>${sendInfo.didIpAddr }</td>
							<td>${sendInfo.processRemark }</td>
							<td>${sendInfo.sendRegDate }</td>
							<td>${sendInfo.sendResult }</td>
							<td>${sendInfo.didPlayTime }</td>							
						</tr>			
						</c:forEach>
				</tbody>
			</table>			
		</div>
		<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
		</div>	
		
	</div>
	<script type="text/javascript">
	 function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);		
		$("form[name=regist]").attr("action", "/backoffice/sub/popup/pop_sendLst.do").submit();
	 }	 
	 </script>
</body>
</html>		