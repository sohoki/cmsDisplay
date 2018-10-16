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
<script type="text/javascript" src="/js/leftMenu.js"></script>

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
        <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/tmenuList.do">									
	   <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
        <input type="hidden" name="mode" id="mode" >
        <input type="hidden" name="menuId" id="menuId" >				
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
						<li><a href="/backoffice/sub/basicManage/selectGroupLst.do" class="selectG">부서관리</a></li>
						<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점관리</a></li>
						<li><a href="/backoffice/sub/basicManage/menuList.do" class="tmenu">메뉴관리(지점)</a></li>
						<li class="active"><a href="/backoffice/sub/basicManage/tmenuList.do" class="tmenu">메뉴관리(타입)</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--관리자관리-->
						<div class="con_title whiteBox ">
							<h2><img src="../../img/list.png"> 총 등록된 메뉴(타입) 리스트입니다.</h2>
							<div class="searchBox">
									<span>총 :  ${totalCnt}개</span>
									<select name="pageUnit" id="pageUnit">								
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
									</select>	
									<select name="searchCondition"  id="searchCondition">
										<option value>선택</option>
										<option value="mberId" <c:if test="${searchVO.searchCondition == 'mberId' }"> selected="selected" </c:if>>아이디</option>
										<option value="mberNm" <c:if test="${searchVO.searchCondition == 'mberNm' }"> selected="selected" </c:if>>이름</option>
									</select>
									<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
									<a href="javascript:search_form();" class="yellowBtn">검색</a>
								<div class="footerBox">
									<a href="javascript:view_MBER('Ins','0')" class="yellowBtn">등록</a>			
								</div>
								<div class="clear"></div>
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										<th>번호</th>
										<th>메뉴코드</th>
										<th>메뉴명</th>
										<th>상위메뉴코드</th>
										<th>연동DID</th>
										<th>사용여부</th>
										<th>수정/삭제</th>					
									</tr>
								</thead>
								<tbody>
								   <c:forEach items="${resultList }" var="menu_Info" varStatus="status">
									<tr>
										<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
										<td>${menu_Info.menuNm  }</td>
										<td>${menu_Info.menuNm }</td>
										<td>${menu_Info.parentMenuNm }</td>
										<td>${menu_Info.didId }</td>
										<td>
										<c:choose>
										   <c:when test="${menu_Info.menuUseYn eq 'Y' }">사용</c:when>
										   <c:otherwise>사용 안함</c:otherwise>
										</c:choose>
										
										</td>										
										<td style="text-align:center"><a href="javascript:view_Menu('Edt',  '${menu_Info.menuId }')" class="grayBtn">수정</a> 
										<a href="javascript:view_Menu('Del',  '${menu_Info.menuId }')" class="grayBtn">삭제</a></td>
									</tr>	
									</c:forEach>		
									<c:if test="${fn:length(resultList) == 0 }">
									<tr>
									  <td colspan="7">등록된 지점이 없습니다.</td>
									</tr>
									</c:if>
								</tbody>
							</table>	
							<div class="pageFooter">
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />	
								<div class="clear"></div>	
							</div>											
						</div>
					</div>
				<div class="clear"></div>	
		</div>	
	</div>
    </form:form>
	
	<script type="text/javascript">
	  function view_Menu(code, code1){
		  $('#mode').val(code);
		  $('#menuId').val(code1);
		  if (code == "Del"){
			  if (confirm("삭제 하시겠습니까?")== true){
				  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/tmenuDeletel.do").submit();  				  
			  }			    
		  }else {
		      $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/tmenuDetail.do").submit();
		  }
	  }
	  //검색
	  function search_form(){	  
		  if (any_empt_line_id("searchCondition", "검색필드를 선택 하지 않았습니다.") == false) return;
		  if (any_empt_line_id("searchKeyword", "검색어 입력 하지 않았습니다.") == false) return;
		  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/tmenuList.do").submit();
	  }
	  function del_Menu(code){
		  $('#menuId').val(code);
		  document.frm.action = "/backoffice/sub/basicManage/tmenuDeletel.do";
		  document.frm.submit();
		  
	  }
	  function linkPage(pageNo) {
			$(":hidden[name=pageIndex]").val(pageNo);				
			$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/tmenuList.do").submit();
	   }		  
	</script>
</body>
</html>		