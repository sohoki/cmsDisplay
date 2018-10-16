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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/dashboardState.do">
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
						<li class="active"><a href="/backoffice/sub/equiManage/dashboardState.do" class="monitoring">통합모니터링</a></li>
						<li><a href="/backoffice/sub/equiManage/dashboardState1.do" class="did">단말기 모니터링</a></li>
						<li><a href="/backoffice/sub/equiManage/dashboardState2.do" class="playContents">음원방송 모니터링</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--통합모니터링-->
						<div class="con_title whiteBox ">
							<h2><img src="img/mediaText.png"> 단말기 및 음원방송 통합 모니터링현황입니다.</h2>
							<!--차트시작-->
							<div id="total" class="chart"></div>
							<div id="total_02" class="chart"></div>
							<div class="clear"></div>		
										
						</div>
					</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>

    <input type="hidden" name="onCnt" id="onCnt" value="${resultListDid.onCnt }">
    <input type="hidden" name="offdid" id="offdid" value="${resultListDid.offCnt }">
    
    <input type="hidden" name="brodonCnt" id="brodonCnt" value="${resultListBrod.onCnt }">
    <input type="hidden" name="brodoffdid" id="brodoffdid" value="${resultListBrod.offCnt }">

<!--chart-->
<script type="text/javascript">
//단말기모니터링
	anychart.onDocumentReady(function() {

    // create data
    var data = [
      {x: "정상", value: $("#onCnt").val()},
      {x: "장애", value: 0},
      {x: "오프라인", value: $("#offdid").val()}
    ];

    // create a chart and set the data
    chart = anychart.pie(data);

    // set the chart title
    chart.title("단말기 모니터링현황");

    // set the container id
    chart.container("total");

    // initiate drawing the chart
    chart.draw();
});

//음원방송모니터링
	anychart.onDocumentReady(function() {

    // create data
    var data = [
      {x: "정상", value: $("#brodonCnt").val()},
      {x: "장애", value: 0},
      {x: "오프라인", value: $("#brodoffdid").val()}
    ];

    // create a chart and set the data
    chart = anychart.pie(data);

    // set the chart title
    chart.title("음원방송 모니터링현황");

    // set the container id
    chart.container("total_02");

    // initiate drawing the chart
    chart.draw();
});	
</script>
</form:form>
</body>
</html>