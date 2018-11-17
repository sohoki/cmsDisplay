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
    <title>운영관리</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/paragraph.css">
	<!-- <link rel="stylesheet" href="/new/css/reset.css">
    <link rel="stylesheet" href="/new/css/layout.css">    
    <link rel="stylesheet" href="/new/css/paragraph.css"> --> 
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<!--subPop -->
	<script src="/js/popup.js"></script>
	<!--leftMenu-->
	<script src="/js/leftMenu.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

      <div id="wrap">
		<!--//HEADER-->
		<%-- <c:import url="/backoffice/inc/cms_header.do" /> --%>
		<c:import url="/backoffice/inc/emart_header.do" />
		<!--HEADER//-->
		<div class="left-contain">
			<c:import url="/backoffice/inc/cms_left.do" />
			<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
		</div>	
		<div class="container">
			<div class="main-content">
				<div class="content">
					<ul class="topMenu" >
						<li><a href="/backoffice/sub/conManage/mediaLst.do" class="media">미디어 관리</a></li>
						<li class="active"><a href="/backoffice/sub/conManage/conMutiList.do" class="conMutiList">화면 구성</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/conMutiList.do">				    	    
					<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
					<input type="hidden" name="mode" id="mode" >
					<input type="hidden" name="conSeq" id="conSeq" >       
					<input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
					<div class="con">
						<!--스케줄 관리-->
						<div class="con_title whiteBox ">
							<h2><img src="/img/list.png"> 단말기 그룹 및 화면 매칭 리스트입니다.</h2>
							<div class="searchBox">
									<span>총 : ${totalCnt}개</span>
									<select name="pageUnit" id="pageUnit" >
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
									</select>	
									<select name="searchCondition"  id="searchCondition">
										<option value="conNm" <c:if test="${searchVO.searchCondition == 'conNm' }"> selected="selected" </c:if>>콘텐츠명</option>
										<option value="conFile" <c:if test="${searchVO.searchCondition == 'conFile' }"> selected="selected" </c:if>>파일명</option>
									</select>
                                     <input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
									<a href="javascript:search_form()" class="yellowBtn">검색</a>
								<div class="footerBox">
									<a href="javascript:view_Con('Ins','0')" class="yellowBtn">편성 등록</a></a>			
								</div>
								<div class="clear"></div>
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										<th>번호</th>
										<th>편성명칭</th>
										<th>편성형태(화면설명)</th> <!-- 사이니지 / 음원POP -->
										<th>송출정보(사이즈)</th> <!-- 가로전체(사이즈)/세로전체(사이즈)/가로분할(사이즈)/세로분할(사이즈) /// -->
										<th>이용스케줄</th>
										<th>등록일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${resultList }" var="conMutiinfo" varStatus="status">
									<tr onclick="javascript:view_Con('Edt', '${conMutiinfo.conSeq }')" style="cursor: pointer;">
										<td>${conMutiinfo.conSeq}</td>
										<td>${conMutiinfo.conNm }</td>
										<td>
										<c:choose>
											<c:when test="${conMutiinfo.codeNm eq '음원POP'}">
											음원POP
											</c:when>
											<c:otherwise>
											사이니지
											</c:otherwise>
										</c:choose>
										</td> <%--  ${conMutiinfo.codeNm} --%>
										<td>
										${conMutiinfo.conScreen }
										<c:choose>
											<c:when test="${conMutiinfo.codeNm ne '음원POP'}">
											<br>(${conMutiinfo.conWidth } * ${conMutiinfo.conHeight })
											</c:when>
											<c:otherwise>
											<br>(${conMutiinfo.conWidth } * ${conMutiinfo.conHeight })
											</c:otherwise>
										</c:choose>
										
										</td>
										<td>${conMutiinfo.schCnt } 개</td>
										<td>${conMutiinfo.frstRegistPnttm }</td>
									</tr>			
									</c:forEach>
									<c:if test="${fn:length(resultList) == 0 }">
									<tr>
									  <td colspan="5">등록된 콘텐츠 가 없습니다</td>
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
					</form:form>
					<!--//하단 콘텐츠 끝 부분 -->
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
    <script type="text/javascript">
		function linkPage(pageNo) {
			$(":hidden[name=pageIndex]").val(pageNo);				
			$("form[name=regist]").submit();
		}	
		function view_Con(code, code1){	  
			$('#mode').val(code);
			$('#conSeq').val(code1);
			if ( $('#mode').val()  == "Ins" ){
				$("form[name=regist]").attr("action", "/backoffice/sub/conManage/conMutiDetail.do").submit();
			}else {
				//$("form[name=regist]").attr("action", "/backoffice/sub/conManage/conMutiView.do").submit();  
				$("form[name=regist]").attr("action", "/backoffice/sub/conManage/conMutiView_back.do").submit();
			}
		}
		function search_form(){
			$("form[name=regist]").attr("action", "/backoffice/sub/conManage/conMutiList.do").submit();		 
		}
		function con_View(code){
			 // ?
		}
	</script>
</body>
</html>		