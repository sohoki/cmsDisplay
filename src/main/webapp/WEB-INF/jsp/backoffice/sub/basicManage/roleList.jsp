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
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<title>이마트DID운영관리</title>
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<c:import url="/backoffice/inc/emart_header.do" />

<div class="pageTop">
		<div class="conIn">
			<h2>Role관리</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home"> 
				기초관리 > Role관리
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="container">
		<!--내용시작-->
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/roleList.do">					
		
	   <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
        <input type="hidden" name="mode" id="mode" >
        <input type="hidden" name="roleCode" id="roleCode" >
        <input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >		
        		
					
		<c:import url="/backoffice/inc/emart_tree.do" />
        <div class="con">			
			<div class="tableBox">				
				<table>
					
					<div class="appli">
						<div class="veiw_box">
							<p>총 : ${totalCnt }</p>
							<select name="pageUnit" id="pageUnit" onChange="javascript:show_page()">								
								<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
								<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
								<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
								<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
							</select>
						</div>
						<div class="srch_box">
							<select name="searchCondition"  id="searchCondition">
								<option value>선택</option>
								<option value="roleCode" <c:if test="${searchVO.searchCondition == 'roleCode' }"> selected="selected" </c:if>>Role코드</option>
								<option value="roleNm" <c:if test="${searchVO.searchCondition == 'roleNm' }"> selected="selected" </c:if>>Role이름</option>
							</select>
							
							<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
							<span class="roomimg"><a href="javascript:search_form()">검 색</a></span>
						</div>						
					</div>
					
					<thead>
						<tr>							
							<th>번호</th>
							<th>권한 정보</th>
							<th>Role Name</th>
							<th>사용여부</th>
							<th>등록일</th>					
						</tr>
					</thead>
					<tbody>
					<c:forEach  items="${resultList }" var="roleInfo" varStatus="status">
						<tr>
							<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
							<td>${roleInfo.authorNm  }</td>
							<td><a href="javascript:view_Code('Edt','${roleInfo.roleCode }')"> ${roleInfo.roleNm  }</a></td>
							<td>
								<c:choose>
							   <c:when test="${roleInfo.useYn eq 'Y' }">사용</c:when>
							   <c:otherwise>사용 안함</c:otherwise>
							</c:choose>
							</td>
							<td>   ${roleInfo.creatDt  }</td>
						</tr>			
					</c:forEach>
					</tbody>
				</table>
				<div class="btnBox">						
					<a href="javascript:view_Code('Ins','0')" class="excel boxH" >등록</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
			</div>
		</div>
		
		</form:form>
        <!--내용끝-->
	</div>
	<div class="clear"></div>
    <c:import url="/backoffice/inc/emart_footer.do" />
	<script type="text/javascript">
	 function view_Code(code, code1){
		  $('#mode').val(code);
		  $('#roleCode').val(code1);		  
		  
		  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/roleDetail.do").submit();
		  
		  return;
	  }
	  //검색
	  function search_form(){	  
		  if (any_empt_line_id("searchCondition", "검색필드를 선택 하지 않았습니다.") == false) return;
		  if (any_empt_line_id("searchKeyword", "검색어 입력 하지 않았습니다.") == false) return;
		  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/roleList.do").submit();		  
	  }
	 function show_page(){
		 $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/roleList.do").submit();		 
	 }
	  function linkPage(pageNo) {
			$(":hidden[name=pageIndex]").val(pageNo);				
			$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/roleList.do").submit();
	   }	 
	</script>
</body>
</html>		