<%@ page language="java" contentType="text/do; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="egovframework.com.cmm.LoginVO" %>

		<%
		LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
	     System.out.println(loginVO.getMberNm() + "," + loginVO.getGroupId());
		if(loginVO == null ){
		%>
			<script type="text/javascript">
				location.href="/backoffice/login.do";
			</script>
		<%         
		}else{ 
		%>
		
		<c:set var="loginName" value="<%= loginVO.getMberNm()%>" scope="session"/>
		<c:set var="groupId" value="<%= loginVO.getGroupId()%>"  scope="session"/>
		<c:set var="groupNm" value="<%= loginVO.getGroupNm()%>"  scope="session"/>
		<c:set var="authorCode" value="<%= loginVO.getAuthorCode()%>"  scope="session"/>
		<c:set var="centerId" value="<%= loginVO.getCenterId()%>"  scope="session"/>
		<c:set var="parentGroupId" value="<%= loginVO.getParentGroupId() %>"  scope="session"/>
		
        <div class="header">
            <div class="content">
                <h1 class="logoB">
                    <a href="integrate.do"><img src="/new/img/logo.png" align="이마트CMS"></a>
                </h1>
                <div class="nav">
                    <ul>
                        <li><a href="">통합관리</a>

                        </li>
                        <li><a href="">모니터링</a></li>
                        <li><a href="">그룹관리</a></li>
                        <li><a href="">사이니지
                            <span class="dropIcon"></span></a>
                            <ul>
                                <li><a href="">스케줄관리</a></li>
                                <li><a href="">화면구성관리</a></li>
                                <li><a href="">미디어파일관리</a></li>
                            </ul>
                        </li>
                        <li><a href="">음원방송
                            <span class="dropIcon"></span></a>
                            <ul>
                                <li><a href="">음원파일관리</a></li>
                                <li><a href="">기본음원관리</a></li>
                                <li><a href="">음원콘텐츠관리</a></li>
                                <li><a href="">스케줄음원관리</a></li>
                                <li><a href="">음원콘텐츠배포</a></li>
                                <li><a href="">콘텐츠배포현황</a></li>
                            </ul>
                        </li>
                        <li><a href="">기초관리
                            <span class="dropIcon"></span></a>
                            <ul>
                                <li><a href="">음원파일관리</a></li>
                                <li><a href="">기본음원관리</a></li>
                                <li><a href="">음원콘텐츠관리</a></li>
                                <li><a href="">스케줄음원관리</a></li>
                                <li><a href="">음원콘텐츠배포</a></li>
                                <li><a href="">콘텐츠배포현황</a></li>
                            </ul>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
				<div class="loginInfo">
                    <span class="loginBar"><c:out value="${loginName}"/> [관리포인트 추가] <a href="<c:url value='/backoffice/actionLogout.do'/>">[ 로그아웃 ]</a></span>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>

          <%-- <div id="header">
			<h1><img src="/img/logo.png" alt="이마트"></h1>

			<p class="headerText">
				<!--<span class="newDid"><a href="sub/new/didNew.html" ><img src="/img/new.png">신규등록</a></span>-->
				<c:set var="loginName" value="<%= loginVO.getMberNm()%>" scope="session"/>
				<c:set var="groupCode" value="<%= loginVO.getGroupId()%>"  scope="session"/>
				<c:set var="groupNm" value="<%= loginVO.getGroupNm()%>"  scope="session"/>
				<c:set var="authorCode" value="<%= loginVO.getAuthorCode()%>"  scope="session"/>
				<c:set var="centerId" value="<%= loginVO.getCenterId()%>"  scope="session"/>
				<c:set var="parentGroupId" value="<%= loginVO.getParentGroupId() %>"  scope="session"/>
				    
				<span><c:out value="${loginName}"/>님 환영합니다. 				
				</span>
				<a href="<c:url value='/backoffice/actionLogout.do'/>">[ 로그아웃 ]</a>
			</p>			
	      </div> --%>
	
        <% } %>	
        