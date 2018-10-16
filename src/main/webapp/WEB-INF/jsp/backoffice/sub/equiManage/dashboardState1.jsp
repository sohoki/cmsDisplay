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
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/popup.js"></script>
<script type="text/javascript" src="/js/leftMenu.js"></script>
<script src="/js/anychart.js"></script>
<link rel="stylesheet" href="/css/anychart.css">
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/dashboardState1.do">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
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
						<li><a href="/backoffice/sub/equiManage/dashboardState.do" class="monitoring">통합모니터링</a></li>
						<li class="active"><a href="/backoffice/sub/equiManage/dashboardState1.do" class="did">단말기 모니터링</a></li>
						<li><a href="/backoffice/sub/equiManage/dashboardState2.do" class="playContents">음원방송 모니터링</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--통합모니터링-->
						<div class="con_title whiteBox ">
							<h2><img src="../../img/didIconsmall.png"> 총 단말기 모니터링현황입니다.</h2>
							<table>
								<tbody>
									<tr>
										<th>총단말 : </th>
										<td>${resultListDid.didCnt }대</td>
										<th>장애 : </th>
										<td>0대</td>
										<th>오프라인 : </th>
										<td>${resultListDid.offCnt }대</td>
									</tr>
								</tbody>
							</table>		
							<table>
								<thead>
									<tr>
										<th>상태</th>
										<th>지점</th>
										<th>단말수</th>
										<th>단말명</th>
										<th>최종 접속시간</th>
									</tr>
								</thead>
								<tbody>
                                  <c:forEach items="${resultList }" var="dashboard" varStatus="status">
									<tr>
										<td>${dashboard.didStatus  }</td>
										<td>${dashboard.centerNm  }</td>
										<td>1대</td>
										<td>${dashboard.didNm  }:${dashboard.didId  }</td>
										<td>${dashboard.didEndContime  }</td>
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
function linkPage(pageNo) {
	$(":hidden[name=pageIndex]").val(pageNo);		
	$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/dashboardState1.do").submit();
 }
</script>
</body>
</html>