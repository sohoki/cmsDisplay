<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/updateGroup.do">						
	    <form:hidden path="mode" id="mode"/>
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
							<h2><img src="../../img/list.png"> 부서 정보.</h2>							
							<div class="searchBox">								
								<div class="footerBox">
									<a href="javascript:checkForm();" class="yellowBtn">등록</a>				
									<a href="/backoffice/sub/basicManage/selectGroupLst.do" class="yellowBtn">리스트</a>				
								</div>	
								<div class="clear"></div>
							</div>
							<!--테이블시작-->
							<table class="allInput">
								<tbody>
									<tr>
										<th>부서코드</th>
										<td style="text-align:left"><form:input title="부서코드" path="groupId"  value="${regist.groupId }"/></td>
										<th>부서명</th>
										<td style="text-align:left"><form:input title="부서코드" path="groupNm"   value="${regist.groupNm }"/></td>
									</tr>									
									<tr>	
										<th>부서설명</th>
										<td style="text-align:left"><form:input title="부서설명" path="groupDc"   value="${regist.groupDc }"/></td>
										<th>상위부서코드</th>
										<td style="text-align:left">								
												<form:select path="parentGroupId" title="상위그룹">
													<form:option value='' label="--선택하세요--" />
													<form:options items="${selectGroup}"   itemValue="groupId" itemLabel="groupNm"/>
												</form:select>      
											
										</td>
									</tr>
									
									<tr>
										<th>사용여부</th>
										<td style="text-align:left" colspan="3">
											<select id="useYn" name="useYn">
												<option value="">선택</option>
												<option value="Y" <c:if test = "${regist.useYn == 'Y' }"> selected="selected" </c:if> >사용</option>
												<option value="N" <c:if test = "${regist.useYn == 'N' }"> selected="selected" </c:if>>사용안함</option>
											</select>
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
		$(document).ready(function() {
			if (  "${status}" != "" ){
				if ("${status}" == "SUCCESS" ){
					alert("정상처리 되었습니다");				
				}else  {
					alert("처리 도중 문제가 발생 하였습니다.");				
				}			
				location.href="/backoffice/sub/basicManage/selectGroupLst.do";	
			}					
		    if ($("#mode").val() == "Ins"){   	       
		 		$("input[name=groupId]").attr("disabled", true);
		 		$("#btnUpdate").text("등록");
		    }	else {
		    	$("input[name=groupId]").attr("readonly", true);
		    	$("#btnUpdate").text("수정");
		    }
		});
	   function checkForm(){	   	   
		   	if (any_empt_line_id("groupNm", "부서명를 입력 하지 않았습니다.") == false) return;
		   	if (any_empt_line_id("parentGroupId", "상위부서을 선택 하지 않았습니다.") == false) return;		   	
			$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/updateGroup.do").submit();
			return;
			
	   }
	</script>    
</body>
</html>		