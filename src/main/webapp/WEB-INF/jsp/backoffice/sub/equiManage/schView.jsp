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
	<link rel="stylesheet" type="text/css" href="/css/calendar.css">
	<script type="text/javascript" src="/js/new_calendar.js"></script>    	
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/schList.do">		
		<form:hidden path="mode" id="mode"/>
	    <form:hidden path="schCode" id="schCode"/>	    
	    <form:hidden path="pageIndex" />
	    <form:hidden path="pageUnit" id="pageUnit"/>
	    <form:hidden path="searchCondition" id="searchCondition"/>
	    <form:hidden path="searchKeyword" id="searchKeyword"/>
	    <form:hidden path="menuGubun" id="menuGubun" />
	    
<div id="wrap">
		<c:import url="/backoffice/inc/emart_header.do" />
		<div class="left-contain">
			<c:import url="/backoffice/inc/cms_left.do" />
			<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
		</div>		
		<div class="container">	
			<div class="main-content">
				<div class="content">
					<ul class="topMenu" >
						<li class="active"><a href="/backoffice/sub/equiManage/schList.do" class="schedule">그룹&화면 매칭</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--스케줄 관리-->
						<div class="con_title whiteBox ">
						    <h2><img src="/img/mediaText.png"> 단말기 그룹 및 화면 매칭.</h2>
						    <div class="searchBox">									
								<div class="footerBox">
									
									<a href="javascript:view_list();" class="yellowBtn">리스트</a>
									<a href="javascript:view_Sch();" class="grayBtn">수정</a>
									<a href="javascript:del_Sch('${regist.groupCode }');" class="grayBtn">삭제</a>
								</div>
								<div class="clear"></div>
							</div>
							<table>
								<tbody>
									<tr>
										<th>스케줄 ID</th>
										<td style="text-align:left">${regist.schCode }</td>
										<th>스케줄명</th>
										<td style="text-align:left">${regist.schName }</td>
										<th>그룹</th>
										<td style="text-align:left">${regist.groupNm }										
										</td>
										<th>콘텐츠 명</th>
										<td style="text-align:left">${regist.conNm }</td>
									</tr>	
									<tr>
										<th>긴급</th>
										<td style="text-align:left">
										<c:choose>
										   <c:when test="${regist.schEmerGubun eq 'Y' }">긴급</c:when>
										   <c:otherwise>일반</c:otherwise>
										</c:choose>
										</td>
										<th>사용유무</th>
										<td style="text-align:left">
										<c:choose>
										   <c:when test="${regist.schUseYn eq 'Y' }">사용</c:when>
										   <c:otherwise>사용안함</c:otherwise>
										</c:choose>
										</td>
										<th>기간</th>
										<td colspan="3">${regist.schStartDay } ~ ${regist.schEndDay }</td>
									</tr>																
							</tbody>
						</table>
									
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
    </form:form>
    <script type="text/javascript">
    function view_Sch(){
 	   $('#mode').val('Edt');
 	   $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schDetail.do").submit(); 	   
    }
    function view_list(){  	   
  	   $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schList.do").submit(); 	   
     }    
    function del_Sch(grpCode){
    	if (confirm("그룹 내 단말이 존재시 삭제가 되지 않습니다.\n\n스케줄을 삭제 하시겠습니까?")== true){
    		apiExecute(
   				"POST", 
   				"/backoffice/sub/equiManage/DidgroupLst.do",
   				{
   					groupCode : grpCode
   				},
   				null,				
   				function(result) {
   					if (result != null) {
   						if (result.didLst != null) {
   							
   							if(result.didLst.length > 0){
   								if (confirm("그룹 내 ["+result.didLst[0].didNm+"]를 포함 "+result.didLst.length+"개의 단말이 존재합니다.\n그룹 내 단말이 존재시 삭제가 불가능합니다.\n\n그룹페이지로 이동하시겠습니까?")== true){
   									var reqLink = "/backoffice/sub/equiManage/did_groupList.do?searchCondition=GROUP_ID&searchKeyword="+grpCode;
   									window.open(reqLink, "_blank");
   								}
   							}else{
   								// 그룹 내 단말이 없으므로 삭제 진행 가능
   								$('#mode').val('Del');
   				    	 		$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schDelete.do").submit();
   							}
   						}    
   					}
   				},
   				function(request){
   							
   				},
   				null
   			);   
    		   	
    	} 	      
    }
    </script>
</body>
</html>		