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
    <script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/operManage/xmlUpdate.do">
<input type="hidden"  name="idCheck" id="idCheck" >		
<form:hidden path="xmlSeq" id="xmlSeq" />		
<form:hidden path="mode" id="mode" />
<form:hidden path="pageUnit" id="pageUnit" />
<form:hidden path="pageIndex" id="pageIndex" />
<form:hidden path="searchCondition" id="searchCondition" />
<form:hidden path="searchKeyword" id="searchKeyword" />
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
					<!--//상단 탭메뉴-->
					<ul class="topMenu" >
						<li class="active"><a href="/backoffice/sub/operManage/xmlList.do" class="conMutiList">전문관리</a></li>
						<li><a href="/backoffice/sub/operManage/sendResultList.do" class="xmlList">전문통신현황</a></li>
						<div class="clear"></div>						
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--전문관리-->
						<div class="con_title whiteBox ">
							<h2><img src="/img/mediaText.png"> 전문관리.</h2>
							<div class="searchBox">
									<a href="/backoffice/sub/operManage/xmlList.do" class="yellowBtn">목록</a> 
									<a href="javascript:check_form()" class="grayBtn" id="btnUpdate">등록</a>									
							</div>	
							<table>
								<tbody>
									<tr>
										<th>프로세스ID</th>
										<td style="text-align:left">
										<form:input path="xmlProcessName" id="xmlProcessName" title="프로세스ID이피" size="15" value="${regist.xmlProcessName }"/>							
										 <a href="javascript:processCheck();" class="grayBtn">중복검사</a></td>
										<th>전문설명</th>		
										<td style="text-align:left">
										<form:input path="processRemark" id="processRemark" title="전문설명" size="25" value="${regist.processRemark }"/>
										</td>				
									</tr>
									<tr>
										<th>In 파라미터</th>
										<td style="text-align:left">
										<textarea name="xmlInputParam" id="xmlInputParam" style="width:250px; height:50px;">${regist.xmlInputParam }</textarea>
										</td>
										<th>Out 파라미터</th>		
										<td style="text-align:left">
										<textarea name="xmlOutputParam" id="xmlOutputParam" style="width:250px; height:50px;">${regist.xmlOutputParam }</textarea>
										</td>				
									</tr>	
									<tr>
										<th>In 파라미터Sample</th>
										<td style="text-align:left">
										<textarea name="xmlInputParamSample" id="xmlInputParamSample" style="width:250px; height:50px;">${regist.xmlInputParamSample }</textarea>
																
										</td>
										<th>Input설명</th>		
										<td style="text-align:left">
										  <textarea name="xmlExplain" id="xmlExplain" style="width:250px; height:50px;">${regist.xmlExplain }</textarea>
										</td>				
									</tr>	
									<tr>
										<th>전문구분</th>
										<td style="text-align:left">
											<form:select path="workGubun" id="workGubun" title="전문구분">
										         <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${selectWorkgubun}" itemValue="code" itemLabel="codeNm"/>
										   </form:select>
										</td>
										<th>Result값</th>		
										<td style="text-align:left">
										<form:input path="resultCodeExample" id="resultCodeExample" title="전문설명" size="15" value="${regist.resultCodeExample }"/>
										</td>				
									</tr>
									<tr>
										<th>리스트 여부</th>
										<td style="text-align:left">
										<form:input path="etc1" id="etc1" title="전문설명" size="15" value="${regist.etc1 }"/>
										</td>
										<th>테스트 확인</th>		
										<td style="text-align:left">
										<input type="radio" name="testOk" value="Y" <c:if test="${regist.testOk == 'Y' }"> checked </c:if> />확인
										<input type="radio" name="testOk" value="N" <c:if test="${regist.testOk == 'N' }"> checked </c:if> />미확인
										</td>				
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
	   $(document).ready(function () {
		   
		  if ($("#mode").val() == "Ins"){
			  $("btnUpdate").text("등록");
		  } else {
			  $("btnUpdate").text("수정");
		  }		   
	   });
       function check_form(){
    	   $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlUpdate.do").submit();
       }
       //전문 체크
       function processCheck(){
    	   if ( $("#xmlProcessName").val()!= ""   ){
   		    
     		     apiExecute(
     					"POST", 
     					"/backoffice/sub/operManage/processCheck.do",
     					{
     						xmlProcessName : $("#xmlProcessName").val()
     					},
     					null,				
     					function(result) {							
     						if (result != null) {	       					
     							if (result == "0"){
     								alert("사용 하실수 있는 코드입니다.");
     								$("#idCheck").val("Y");							
     							}else {
     								alert("사용 하실수 없는 코드입니다.");
     								$("#idCheck").val("N");
     							}
     						}
     					},
     					null,
     					null
     				); 
  		    
     	  }else {
     		    	alert ("그룹코드를 입력해 주세요");
     		    	$("#xmlProcessName").focus();
     		    	return;
     	 }        	
    	   
       }
    </script>
</body>
</html>		