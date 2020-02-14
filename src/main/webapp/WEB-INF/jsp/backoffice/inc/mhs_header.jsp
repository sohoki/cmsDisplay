<%@ page language="java" contentType="text/do; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="egovframework.com.cmm.LoginVO" %>

<%
	LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
    if(loginVO == null ){
%>
		<script type="text/javascript">
			location.href="/backoffice/login.do";
		</script>
<%         
	}else{ 
%>
		<c:set var="loginName" value="<%= loginVO.getMberNm()%>" scope="session"/>
		<c:set var="groupCode" value="<%= loginVO.getGroupId()%>"  scope="session"/>
		<c:set var="groupNm" value="<%= loginVO.getGroupNm()%>"  scope="session"/>
		<c:set var="authorCode" value="<%= loginVO.getAuthorCode()%>"  scope="session"/>
		<c:set var="centerId" value="<%= loginVO.getCenterId()%>"  scope="session"/>
		<c:set var="parentGroupId" value="<%= loginVO.getParentGroupId() %>"  scope="session"/>
		<div id="mhs_header">
			<div class="mhs_header_wrap">
				<h1><img src="/img/emart_logo.png" alt="이마트"></h1>
				<div class="mhs_menu">
					<ul>
						<li class="active"><a href="/backoffice/sub/roomManage/monitorList.do">모니터정보</a></li>
						<li><a href="/backoffice/sub/roomManage/classList.do">강의정보</a></li>
						<c:if test="${authorCode eq 'ROLE_MHS_ADMIN'}">
						<li><a href="/backoffice/sub/roomManage/centerList.do">조직/점포관리</a></li>
						</c:if>
					</ul>
				</div>
				<p class="headerText">
					<span><c:out value="${loginName}"/>님 환영합니다.</span>
					<a style="margin-left:8px;" href="<c:url value='/backoffice/actionLogout.do'/>">로그아웃</a>
				</p>
			</div>
		</div>
<% 
	} 
%>	