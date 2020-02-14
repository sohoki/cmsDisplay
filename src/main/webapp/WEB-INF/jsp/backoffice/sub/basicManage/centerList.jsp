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
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/centerList.do">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="centerId" id="centerId" >
<input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
        
        
        
<div id="wrap">
<c:import url="/backoffice/inc/emart_header.do" />	
<div class="left-contain">
	<c:import url="/backoffice/inc/cms_left.do" />
	<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
</div>	
	<div class="container">
		<!--내용시작-->
		
		<div class="main-content">
		    <div class="content">
			<!--//상단 탭메뉴-->
			<ul class="topMenu" >
				        <li><a href="/backoffice/sub/basicManage/manageList.do" class="manage">관리자관리</a></li>
						<li><a href="/backoffice/sub/basicManage/codeList.do" class="code">기초코드관리</a></li>
						<li><a href="/backoffice/sub/basicManage/selectGroupLst.do" class="selectG">부서관리</a></li>
						<li class="active"><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점관리</a></li>				
						<li><a href="/backoffice/sub/basicManage/menuList.do" class="tmenu">메뉴관리(지점)</a></li>
						<li><a href="/backoffice/sub/basicManage/tmenuList.do" class="tmenu">메뉴관리(타입)</a></li>
				<div class="clear"></div>
			</ul>
	        <div class="con">			
				<div class="tableBox">		
				    <div class="con_title whiteBox ">
						<h2><img src="/img/list.png"> 총 등록된 이마트 지점 리스트입니다.</h2>
						<div class="searchBox">
								<span>총 :  ${totalCnt}개  </span>
								<select name="pageUnit" id="pageUnit">								
									<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
									<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
									<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
									<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
								</select>
								<select name="searchCondition"  id="searchCondition">
									<option value>선택</option>
									<option value="centerId" <c:if test="${searchVO.searchCondition == 'centerId' }"> selected="selected" </c:if>>지점ID</option>
									<option value="centerNm" <c:if test="${searchVO.searchCondition == 'centerNm' }"> selected="selected" </c:if>>지점명</option>
								</select>									
								<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
								
								<a href="javascript:search_form('/backoffice/sub/basicManage/centerList.do')" class="yellowBtn">검색</a>
								<div class="footerBox">
									<a href="javascript:view_Center('Ins','0')" class="yellowBtn">등록</a>
									<a href="javascript:excel_Center();" class="grayBtn">excel upload</a>			
								</div>
								<div class="clear"></div>
						</div>		
					<table>
						<thead>
							<tr>							
								<th>번호</th>
								<th>점포명</th>
								<th>개점시간</th>
								<th>폐점시간</th>
								<th>세부사항</th>
								<th>등록일</th>
								<th>사용여부</th>				
							</tr>
						</thead>
						<tbody>
						  <c:forEach items="${resultList }" var="centerInfo" varStatus="status">
							<tr>
								<td style="text-align:center"><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
								<td><a href="javascript:view_Center('Edt','${centerInfo.centerId }')">${centerInfo.centerNm }</a></td>
								<td>${centerInfo.centerStartTime}</td>
								<td>${centerInfo.centerEndTime}</td>
								<td>
								<c:choose>
								   <c:when test="${centerInfo.centerGubun eq 'BRANCH01' }"><a href="javascript:branchView('${centerInfo.centerId }')">음원전용</a></c:when>
								   <c:otherwise>DID전용</c:otherwise>
								</c:choose>		
								</td>
								<td style="text-align:center">
								<c:out value="${fn:substring(centerInfo.centerRegdate,0,10) }"/>
								</td>
								<td style="text-align:center">
								<c:choose>
								   <c:when test="${centerInfo.centerUseYn eq 'Y' }">사용</c:when>
								   <c:otherwise>사용 안함</c:otherwise>
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
		
        <!--내용끝-->
	</div>    
    <script type="text/javascript">
    $(document).ready(function() {
		if ("${status}" != "" ){
			if ("${status}" == "SUCCESS" ){
				alert("정상처리 되었습니다");					
			}else  {
				alert("처리 도중 문제가 발생 하였습니다.");				
			}	
		}						   
	});	  
    function excel_Center(){
    	
    }
     function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	   }
	  function view_Center(code, code1){
		  $('#mode').val(code);
		  $('#centerId').val(code1);
		  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/centerDetail.do").submit();		  
	  }
	  function branchView(code){
		  $('#centerId').val(code);
		  $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/playCenterInfo.do").submit();			  
	  }
	 
	</script>
 </div>	
</form:form> 
</body>
</html>		