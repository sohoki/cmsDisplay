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
                        <li><a href="/backoffice/sub/equiManage/integrate.do">통합관리</a></li>
                        <li><a href="/backoffice/sub/equiManage/dashboardState.do">모니터링</a></li>
                        <li><a href="/backoffice/sub/equiManage/did_groupList.do">그룹관리</a></li>
                        <li><a href="/backoffice/sub/conManage/conMutiList.do">사이니지
                            <span class="dropIcon"></span></a>
                            <ul>
                                <li><a href="/backoffice/sub/equiManage/schList.do">스케줄관리</a></li>
                                <li><a href="/backoffice/sub/conManage/conMutiList.do">화면구성관리</a></li>
                                <li><a href="/backoffice/sub/conManage/mediaLst.do">미디어파일관리</a></li>
                            </ul>
                        </li>
                        <li><a href="/backoffice/sub/brodManage/playSheduleStatus.do">음원방송
                            <span class="dropIcon"></span></a>
                            <ul>
                                <li><a href="/backoffice/sub/brodManage/playContentList.do">음원파일관리</a></li>
                                <li><a href="/backoffice/sub/brodManage/brodBasic.do">기본음원관리</a></li>
                                <li><a href="/backoffice/sub/brodManage/brodContentList.do">음원콘텐츠관리</a></li>
                                <li><a href="/backoffice/sub/brodManage/brodContentPlayList.do">스케줄음원관리</a></li>
                                <li><a href="/backoffice/sub/brodManage/playShedule.do">음원콘텐츠배포</a></li>
                                <li><a href="/backoffice/sub/brodManage/playSheduleStatus.do">콘텐츠배포현황</a></li>
                            </ul>
                        </li>
                        <li><a href="/backoffice/sub/basicManage/manageList.do">시스템
                            <span class="dropIcon"></span></a>
                            <ul>
                                <li><a href="/backoffice/sub/basicManage/manageList.do">계정관리</a></li>
                                <li><a href="/backoffice/sub/basicManage/codeList.do">코드관리</a></li>
                                <li><a href="/backoffice/sub/basicManage/selectGroupLst.do">부서관리</a></li>
                                <li><a href="/backoffice/sub/basicManage/centerList.do">점포관리</a></li>
                                <li style="display:none;"><a href="/backoffice/sub/basicManage/menuList.do">메뉴관리(점포)</a></li>
                                <li style="display:none;"><a href="/backoffice/sub/basicManage/tmenuList.do">메뉴관리(타입)</a></li>
                            </ul>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
				<div class="loginInfo">
                    <span class="loginBar"><c:out value="${loginName}"/>[관리포인트 추가] </span>
                    <a href="<c:url value='/backoffice/actionLogout.do'/>" class="header_logout">로그아웃</a>
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
        