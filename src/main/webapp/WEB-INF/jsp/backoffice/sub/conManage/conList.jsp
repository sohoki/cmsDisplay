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
	<title>이마트</title>
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/paragraph.css">
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<!--subPop -->
	<script src="/js/popup.js"></script>
	<!--leftMenu-->
	<script src="/js/leftMenu.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/conList.do">				    
	    
        <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
        <input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
        <input type="hidden" name="mode" id="mode" >
        <input type="hidden" name="conSeq" id="conSeq" >     
        
        
        
<div id="wrap">
<!--//HEADER-->
<c:import url="/backoffice/inc/cms_header.do" />
<!--HEADER//-->
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
							<li class="active"><a href="/backoffice/sub/conManage/mediaLst.do" class="media">미디어 관리</a></li>
							<li><a href="/backoffice/sub/conManage/conMutiList.do" class="conMutiList">화면 구성</a></li>
							<div class="clear"></div>
						</ul>
			            <div class="con">			
				              <div class="con_title whiteBox ">
					          <h2><img src="/img/list.png"> 총 등록된 화면리스트입니다.</h2>
								<div class="searchBox">
										<span>총 : ${totalCnt}개</span>
										<select name="pageUnit" id="pageUnit" >
											<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
											<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
											<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
											<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
										</select>	
										<select name="searchCondition"  id="searchCondition">
											<option value>선택</option>
											<option value="conFile" <c:if test="${searchVO.searchCondition == 'conFile' }"> selected="selected" </c:if>>파일명</option>
											<option value="conNm" <c:if test="${searchVO.searchCondition == 'conNm' }"> selected="selected" </c:if>>콘텐츠명</option>
										</select>																	
										<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
										<a href="javascript:search_form()" class="yellowBtn">검색</a>
									<div class="footerBox">
										<a href="javascript:view_Con('Ins','0');" class="yellowBtn">등록</a></a>			
									</div>
									<div class="clear"></div>
								</div>
					          <!-- 내용 넣을 자리 시작 -->
					          <table>
						          <thead>
										<tr>							
											<th>번호</th>							
											<th>콘텐츠명</th>
											<th>콘텐츠 형식</th>									
											<th>재생형식</th>
											<th>연동콘텐츠</th>							
											<th>유지시간</th>		
										</tr>
									</thead>
									<tbody>
									   <c:forEach items="${resultList }" var="coninfo" varStatus="status">
										<tr>
											<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
											<td><a href="javascript:view_Con('Edt', '${coninfo.conSeq}')">  ${coninfo.conNm}</a></td>
											<td><a href="javascript:con_View('${coninfo.conSeq}')">${coninfo.codeNm}</a></td>
											<td>${coninfo.conPlayType}</td>
											<td>${coninfo.conNextConSeq}</td>							
											<td>${coninfo.conTimeInterval}분</td>
										</tr>			
										</c:forEach>
										<c:if test="${fn:length(resultList) == 0 }">
										<tr>
										  <td colspan="6">등록된 콘텐츠 가 없습니다</td>
										</tr>
										</c:if>
								</tbody>
							  </table>
							  <div class="pageFooter">
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
								<div class="clear"></div>	
							 </div>	
					          
					          <!-- 내용 넣을 자리 끝 부분 -->
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
			$("form[name=regist]").submit();
		   }	
		  function view_Con(code, code1){	  
			  $('#mode').val(code);
			  $('#conSeq').val(code1);
			  $("form[name=regist]").attr("action", "/backoffice/sub/conManage/conDetail.do").submit();		       			  			 			  		 
		  }
		 function search_form(){
			 $("form[name=regist]").attr("action", "/backoffice/sub/conManage/conList.do").submit();		 
		 }
		 function con_View(code){
			 
		 }
		</script>
</body>
</html>		