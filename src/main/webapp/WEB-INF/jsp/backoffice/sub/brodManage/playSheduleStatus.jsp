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
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/reset.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
	<style>
		#wrap { min-height :1080px;}
	</style>
</head>
<body>

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/playSheduleStatus.do">
	<form:hidden path="pageIndex" id="pageIndex"/>
    <input type="hidden" name="mode" id="mode" >
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
						<li><a href="/backoffice/sub/brodManage/brodContentList.do" class="playContents">음원콘텐츠관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentPlayList.do" class="playContents">스케줄음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playShedule.do" class="playShedule">음원콘텐츠배포</a></li>
						<li class="active"><a href="/backoffice/sub/brodManage/playSheduleStatus.do" class="playShedule">콘텐츠배포현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--음원파일관리-->
						<div class="con_title whiteBox ">
							<h2><img src="/img/list.png"> 배포 현황 입니다..</h2>	
							<div class="searchBox">
									<span>총 : ${totalCnt }개</span>
									<select name="pageUnit" id="pageUnit">								
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
									</select>	
									<select id="searchCondition" name="searchCondition" class="blan">
										<option value="centerNm" <c:if test="${searchVO.searchCondition == 'centerNm' }"> selected="selected" </c:if>>지점명</option>
  									    <option value="brodName" <c:if test="${searchVO.searchCondition == 'brodName' }"> selected="selected" </c:if>>콘텐츠명</option>
									</select>	
									<select id="createCheck" name="createCheck" class="blan">
										<option value="Y" <c:if test="${searchVO.createCheck == 'Y' }"> selected="selected" </c:if>>배포</option>
  									    <option value="N" <c:if test="${searchVO.createCheck == 'N' }"> selected="selected" </c:if>>미배포</option>
									</select>		
									<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">

									<a href="javascript:search_form()" class="yellowBtn">검색</a>
								<div class="footerBox">								
								<div class="clear"></div>			
							</div>
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										
										<th>콘텐츠명</th>
										<th>지점명</th>
										<th>재생일자</th>
										<th>기념일여부</th>
										<th>지점오픈시간|종료시간</th>
										<th>배포여부</th>
										<th>배포생성일자</th>
										<th>다운로드여부</th>
										<th>다운로드일자</th>										
									</tr>
								</thead>
								<tbody>
								
								  <c:forEach items="${resultList}" var="brodSchedule" varStatus="status">
									<tr>
										
										<td style="text-align:center;"><a href="javascript:brodView('${brodSchedule.brodCode}','${brodSchedule.centerId}','${brodSchedule.brodDay}')">${brodSchedule.brodName}</a></td>
										<td style="text-align:center;"><a href="javascript:brodView('${brodSchedule.brodCode}','${brodSchedule.centerId}','${brodSchedule.brodDay}')">${brodSchedule.centerNm}</a></td>
										<td style="text-align:center;">
										<c:choose>
										   <c:when test="${brodSchedule.brodDay eq '20991231' }">평상시</c:when>
										   <c:otherwise>${brodSchedule.brodDay }</c:otherwise>
										</c:choose>
										</td>
										<td style="text-align:center;">
										<c:choose>
										   <c:when test="${brodSchedule.brodDay eq '20991231' }">일반</c:when>
										   <c:otherwise>기념일음원</c:otherwise>
										</c:choose>	
										</td>
										<td style="text-align:center;">${brodSchedule.centerStartTime }</td>
										<td style="text-align:center;">							
										<c:choose>
										   <c:when test="${brodSchedule.createCheck eq 'Y' }">배포</c:when>
										   <c:otherwise>미배포</c:otherwise>
										</c:choose>							
										</td>
										<td>
										<c:choose>
										   <c:when test="${brodSchedule.createRegDate eq '00000000' }">&nbsp;</c:when>
										   <c:otherwise>${brodSchedule.createRegDate}</c:otherwise>
										</c:choose>	
										</td>
										<td style="text-align:center;">							
										<c:choose>
										   <c:when test="${brodSchedule.didDownCheck eq 'Y' }">다운완료</c:when>
										   <c:otherwise>대기중</c:otherwise>
										</c:choose>							
										</td>
										<td>
										<c:choose>
										   <c:when test="${brodSchedule.didDownLoadDate eq '' }">&nbsp;</c:when>
										   <c:otherwise>${brodSchedule.didDownLoadDate}</c:otherwise>
										</c:choose>	
										</td>
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
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
</form:form>
    <script type="text/javascript">
    function search_form(){
    	$("form[name=regist]").submit();
    	$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/playSheduleStatus.do").submit();
    }
    function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	}   
    function brodView(code, code1, code2){
    	if (code2 == "20991231") {
    	  code2 = getAgoDate(0,0,0);	
    	}
    	var url = "/backoffice/sub/brodManage/playCenterInfo.do?centerId="+code1+"&centerAnniverDay="+code2;    	
    	location.href=url;
    }
    
	</script>

</body>
</html>