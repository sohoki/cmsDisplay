<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<title>이마트DID운영관리</title>
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/popup.js"></script>
<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/schList.do">	    	    
	    <input type="hidden" name="mode" id="mode" >
        <input type="hidden" name="groupId" id="groupId" >
        <input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
        
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
						<li><a href="/backoffice/sub/basicManage/manageList.do" class="manage">관리자관리</a></li>
						<li><a href="/backoffice/sub/basicManage/codeList.do" class="code">기초코드관리</a></li>
						<li class="active"><a href="/backoffice/sub/basicManage/selectGroupLst.do" class="selectG">부서관리</a></li>
						<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점관리</a></li>
						<li><a href="/backoffice/sub/basicManage/menuList.do" class="tmenu">메뉴관리(지점)</a></li>
						<li><a href="/backoffice/sub/basicManage/tmenuList.do" class="tmenu">메뉴관리(타입)</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--관리자관리-->
						<div class="con_title whiteBox ">
							<h2><img src="../../img/list.png"> 총 등록된 부서 리스트입니다.</h2>
							<span>총 : ${totalCnt}개</span>
							<div class="searchBox">								
								<div class="footerBox">
									<a href="javascript:viewGroup('Ins', '0')" class="yellowBtn">등록</a>				
								</div>	
								<div class="clear"></div>
							</div>
							<!--테이블시작-->
							<table>			
								<thead>					
									<tr>							
										<th>번호</th>
										<th>조직명</th>
										<th>상위부서</th>							
										<th>사용여부</th>
										<th>수정/삭제</th>					
									</tr>
								</thead>
								<tbody>
								   <c:forEach items="${resultList }" var="groupinfo" varStatus="status">
									<tr>
										<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
										<td style="text-align:left">
										
									    <c:if test="${groupinfo.lv ne '1' }">
									      <c:forEach var="i" begin="1" end="${groupinfo.lv}" step="1">
											      &nbsp;&nbsp;&nbsp;
											 </c:forEach>     
											 └ 
									    </c:if>
										<c:out value="${groupinfo.groupNm }"/></td>
										<td><c:out value="${groupinfo.parentGroupNm }"/></td>							
										<td>
										<c:choose>
										   <c:when test="${groupinfo.useYn eq 'Y' }">
										   사용 
										   </c:when>
										   <c:otherwise>
										   사용 안함
										   </c:otherwise>
										</c:choose>
										</td>
										<td style="text-align:center"><a href="javascript:viewGroup('Edt', '${ groupinfo.groupId }')" class="grayBtn">수정</a> 
										<a href="javascript:delGroup(' ${ groupinfo.groupId } ')" class="grayBtn">삭제</a></td>
									</tr>			
									</c:forEach>
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
	   function viewGroup(code, code1){
		   $('#mode').val(code);
		   $('#groupId').val(code1);		   
		   $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/groupDetail.do").submit();		   
		   return;
	   }
	   function delGroup(code){
		   if (confirm("삭제 하시겠습니까?")== true){
			   $('#groupId').val(code);		  
			   $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/deleteGroup.do").submit();						   			  
	       }else {
	    	   return;       	   
	       }
	   }	
	   $(document).ready(function() {        
   	   
   	        	    
	    	if ("${status}" != "") {
	    		
	    		if ("${status}" == "SUCCESS") {
	    			alert("정상 처리 되었습니다");
	    			location.href="/backoffice/sub/basicManage/selectGroupLst.do";
	    		}else{
	    			alert("작업 도중 문제가 발생 하였습니다.");
	    			location.href="/backoffice/sub/basicManage/selectGroupLst.do";
	    		}
	    		
	    	}    	
           
       });
	</script>     
</body>
</html>		