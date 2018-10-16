<%@ page language="java" contentType="text/do; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  
<div id="sidebar">
	<ul>
		<li><div><a class="leftMenuTxt" href="/backoffice/sub/equiManage/dashboardState.do">모니터링</a><a class="leftMenuImg" onclick="menuOpen('dash')"><img class="leftMenuImg_dash" src="/img/list_open_icon.png"/></a></div></li>
			<ol class="leftMenu_dash leftChildMenu"><a href="/backoffice/sub/equiManage/dashboardState.do">&nbsp;&nbsp;>&nbsp;&nbsp;통합 모니터링</a></ol>
			<ol class="leftMenu_dash leftChildMenu"><a href="/backoffice/sub/equiManage/dashboardState1.do">&nbsp;&nbsp;>&nbsp;&nbsp;단말기 모니터링</a></ol>
			<ol class="leftMenu_dash leftChildMenu"><a href="/backoffice/sub/equiManage/dashboardState2.do">&nbsp;&nbsp;>&nbsp;&nbsp;음원방송 모니터링</a></ol>
		<li><div><a class="leftMenuTxt" href="/backoffice/sub/equiManage/didList.do">단말기관리</a><a class="leftMenuImg" onclick="menuOpen('device')"><img class="leftMenuImg_device" src="/img/list_open_icon.png"/></a></div></li>
			<ol class="leftMenu_device leftChildMenu"><a href="/backoffice/sub/equiManage/didList.do">&nbsp;&nbsp;>&nbsp;&nbsp;단말기 관리</a></ol>
			<ol class="leftMenu_device leftChildMenu"><a href="/backoffice/sub/equiManage/did_groupList.do">&nbsp;&nbsp;>&nbsp;&nbsp;그룹 관리</a></ol>
			<ol class="leftMenu_device leftChildMenu"><a href="/backoffice/sub/equiManage/didSendMessage.do">&nbsp;&nbsp;>&nbsp;&nbsp;메시지 관리</a></ol>
			<ol class="leftMenu_device leftChildMenu"><a href="/backoffice/sub/equiManage/didSendMessageLst.do">&nbsp;&nbsp;>&nbsp;&nbsp;메시지 현황</a></ol>
		<c:if test="${authorCode eq 'ROLE_ADMIN' &&  groupCode ne 'EMART_00000000000009' }">
		<li><div><a class="leftMenuTxt" href="/backoffice/sub/conManage/conMutiList.do">콘텐츠관리</a><a class="leftMenuImg" onclick="menuOpen('content')"><img class="leftMenuImg_content" src="/img/list_open_icon.png"/></a></div></li>
			<ol class="leftMenu_content leftChildMenu"><a href="/backoffice/sub/conManage/mediaLst.do">&nbsp;&nbsp;>&nbsp;&nbsp;미디어 관리</a></ol>
			<ol class="leftMenu_content leftChildMenu"><a href="/backoffice/sub/conManage/conMutiList.do">&nbsp;&nbsp;>&nbsp;&nbsp;화면 구성</a></ol>
		<li><a class="leftMenuTxt80" href="/backoffice/sub/equiManage/schList.do" >스케줄관리</a></li>
		<li><div><a class="leftMenuTxt" href="/backoffice/sub/brodManage/brodContentList.do" >음원방송</a><a class="leftMenuImg" onclick="menuOpen('music')"><img class="leftMenuImg_music" src="/img/list_open_icon.png"/></a></div></li>
			<ol class="leftMenu_music leftChildMenu"><a href="/backoffice/sub/basicManage/centerList.do">&nbsp;&nbsp;>&nbsp;&nbsp;지점 관리</a></ol>
			<ol class="leftMenu_music leftChildMenu"><a href="/backoffice/sub/brodManage/playContentList.do">&nbsp;&nbsp;>&nbsp;&nbsp;음원파일관리</a></ol>
			<ol class="leftMenu_music leftChildMenu"><a href="/backoffice/sub/brodManage/brodBasic.do">&nbsp;&nbsp;>&nbsp;&nbsp;기본음원관리</a></ol>
			<ol class="leftMenu_music leftChildMenu"><a href="/backoffice/sub/brodManage/brodContentList.do">&nbsp;&nbsp;>&nbsp;&nbsp;음원콘텐츠관리</a></ol>
			<ol class="leftMenu_music leftChildMenu"><a href="/backoffice/sub/brodManage/brodContentPlayList.do">&nbsp;&nbsp;>&nbsp;&nbsp;스케줄음원관리</a></ol>
			<ol class="leftMenu_music leftChildMenu"><a href="/backoffice/sub/brodManage/playShedule.do">&nbsp;&nbsp;>&nbsp;&nbsp;음원콘텐츠배포</a></ol>
			<ol class="leftMenu_music leftChildMenu"><a href="/backoffice/sub/brodManage/playSheduleStatus.do">&nbsp;&nbsp;>&nbsp;&nbsp;콘텐츠배포현황</a></ol>	
		</c:if>
		<li><div><a class="leftMenuTxt" href="/backoffice/sub/operManage/sendResultList.do" >전문통신관리</a><a class="leftMenuImg" onclick="menuOpen('network')"><img class="leftMenuImg_network" src="/img/list_open_icon.png"/></a></div></li>
			<ol class="leftMenu_send leftChildMenu"><a href="/backoffice/sub/operManage/xmlList.do">&nbsp;>&nbsp;&nbsp;전문관리</a></ol>
			<ol class="leftMenu_send leftChildMenu"><a href="/backoffice/sub/operManage/sendResultList.do">&nbsp;>&nbsp;&nbsp;전문통신현황</a></ol>	
        <c:if test="${authorCode eq 'ROLE_ADMIN' }">
		<li><a class="leftMenuTxt80" href="/backoffice/sub/basicManage/manageList.do" >설정</a></li>
		</c:if>
	</ul>
</div>