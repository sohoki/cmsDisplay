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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/updateGroup.do">
<form:hidden path="mode" id="mode" />
<form:hidden path="xmlSeq" id="xmlSeq" />	    
<form:hidden path="pageUnit" id="pageUnit" />
<form:hidden path="pageIndex" id="pageIndex" />
<form:hidden path="workGubun" id="workGubun" />
<form:hidden path="searchCondition" id="searchCondition" />
<form:hidden path="searchKeyword" id="searchKeyword" />
<form:hidden path="menuGubun" id="menuGubun" />
<form:hidden path="workGubun" id="workGubun" />
	    
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
							<h2><img src="/img/mediaText.png">전문관리.</h2>
							<div class="searchBox">
									<a href="javascript:pageList()" class="yellowBtn">목록</a> 
									<a href="javascript:check_form()" class="grayBtn">수정</a>
									<a href="javascript:del_check()" class="grayBtn">삭제</a>	
									
									
									<div class="footerBox">
									<a href="javascript:open_JsonView('I',' ${regist.xmlSeq  }' )" class="brownBtn">요청전문</a>
									<a href="javascript:open_JsonView('O',' ${regist.xmlSeq  }' )" class="brownBtn">결과전문</a>
									</div>
									<div class="clear"></div>									
							</div>
							<table>
								<tbody>
									<tr>
										<th>프로세스명</th>
										<td style="text-align:left"><c:out value="${regist.xmlProcessName}" /></td>
										<th>전문설명</th>		
										<td style="text-align:left"><c:out value="${regist.processRemark}" /></td>				
									</tr>
									<tr>
										<th>In 파라미터</th>
										<td style="text-align:left"><c:out value="${regist.xmlInputParam}" /></td>
										<th>Out 파라미터</th>		
										<td style="text-align:left"><c:out value="${regist.xmlOutputParam}" /></td>				
									</tr>	
									<tr>
										<th>In 파라미터Sample</th>
										<td style="text-align:left"><c:out value="${regist.xmlInputParamSample}" /></td>
										<th>Input설명</th>		
										<td style="text-align:left"><c:out value="${regist.xmlExplain}" /></td>				
									</tr>	
									<tr>
										<th>전문구분</th>
										<td style="text-align:left"><c:out value="${regist.codeNm}" /></td>
										<th>Result값</th>		
										<td style="text-align:left"><c:out value="${regist.resultCodeExample}" /></td>				
									</tr>
									<tr>
										<th>리스트 여부</th>
										<td style="text-align:left"><c:out value="${regist.etc1}" /></td>
										<th>테스트 확인</th>		
										<td style="text-align:left">
										<c:choose>
										   <c:when test="${regist.testOk eq 'Y' }">확인</c:when>
										   <c:otherwise>미확인</c:otherwise>
										 </c:choose>
										</td>			
									</tr>																										
								</tbody>
							</table>
				      </div>			
				  </div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
    </form:form>
    <script type="text/javascript">
       $(document).ready(function () {
    	   if ("${status}" != "" ){
				if ("${status}" == "SUCCESS" ){
					alert("정상처리 되었습니다");					
				}else  {
					alert("처리 도중 문제가 발생 하였습니다.");				
				}
    	   }
	   });
       function check_form(){
    	   $("#mode").val("Edt");
    	   $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlDetail.do").submit();    	   
       }
       function pageList(){
    	   $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlList.do").submit();
       }
       function del_check(){
    	   $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlDel.do").submit();
       }
       function open_XmlView(code, code1){
  	      var url = "";
 		  if (code == "I")
 	      {
 	           url = "/backoffice/sub/operManage/xmlAuthView.do?xmlSeq="+code1;
 	      }else {
 	           url = "/backoffice/sub/operManage/xmlAuthReq.do?xmlSeq="+code1;
 		  }
           window.open(url,"xml전문확인", 'width=600,height=860,top=100,left=650,scrollbars=auto')	   
     	   
       }
       function open_JsonView(code, code1){
   	      var url = "";
  		  if (code == "I")
  	      {
  	           url = "/backoffice/sub/operManage/jsonAuthView.do?xmlSeq="+code1;
  	      }else {
  	           url = "/backoffice/sub/operManage/jsonAuthReq.do?xmlSeq="+code1;
  		  }
            window.open(url,"xml전문확인", 'width=600,height=860,top=100,left=650,scrollbars=auto');        	   
       }
    </script>
</body>
</html>		