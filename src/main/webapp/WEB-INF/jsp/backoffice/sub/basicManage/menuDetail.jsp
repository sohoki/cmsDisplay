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
		<!--내용시작-->
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/menuUpdate.do">		
		<form:hidden path="menuId" id="menuId" />
		<form:hidden path="mode"  id="mode"/>
				
		<form:hidden path="pageIndex" />
        <form:hidden path="pageSize" />
        <form:hidden path="searchCondition" />
        <form:hidden path="searchKeyword" />
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
						<li><a href="/backoffice/sub/basicManage/selectGroupLst.do" class="selectG">부서관리</a></li>
						<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점관리</a></li>
						<li class="active"><a href="/backoffice/sub/basicManage/menuList.do" class="tmenu">메뉴관리(지점)</a></li>
						<li><a href="/backoffice/sub/basicManage/tmenuList.do" class="tmenu">메뉴관리(타입)</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--관리자관리-->
						<div class="con_title whiteBox ">
							<h2><img src="../../img/list.png"> 메뉴(지점).</h2>
							<div class="searchBox">
									

								
								<div class="footerBox">
									<a href="javascript:listPage('regist','/backoffice/sub/basicManage/menuList.do' )" class="yellowBtn">리스트</a>			
									<a href="javascript:check_form()" class="grayBtn" id="btnUpdate">등록</a>		
									<c:if test = "${regist.mode == 'Edt' }">
									<a href="javascript:view_Menu('Ins','0')" class="grayBtn">삭제</a>	
									</c:if>			
								</div>
								<div class="clear"></div>
							</div>
							<!--테이블시작-->
							<table class="allInput">
								<tbody>						
									<tr>	
										<th>메뉴명</th>
										<td>
										<form:input title="메뉴명" path="menuNm" />
										</td>
									</tr>
									<tr>
										<th>메뉴구분</th>
										<td>								
											<form:select path="menuType" title="상위그룹"  onChange="javascript:view_Combo()">
												<form:option value='' label="--선택하세요--" />
												<form:options items="${selectMenuType}"   itemValue="code" itemLabel="codeNm"/>
											</form:select>                                     	 
										</td>
									</tr>
									<tr>
										<th>상위메뉴코드</th>
										<td>								
											<form:select path="parentMenuId" title="상위그룹">
												<form:option value='' label="--선택하세요--" />
												<form:options items="${selectMenuGroup}"   itemValue="menuId" itemLabel="menuNm"/>
											</form:select>                                     	 
										</td>
									</tr>						
									<span id="sp_center">
									<tr>
										<th>센터아이디</th>
										<td>
										  <form:select path="centerId" title="상위그룹" >
												<form:option value='' label="--선택하세요--" />
												<form:options items="${centerLst}"   itemValue="centerId" itemLabel="centerNm"/>
											</form:select> 																                                     	 
										</td>
									</tr>
									</span>
									<span id="sp_did">
									<tr>
										<th>연동DID</th>
										<td>								
											<form:select path="didId" title="상위그룹" >
												<form:option value='' label="--선택하세요--" />
												<form:options items="${selectDidGroup}"   itemValue="didId" itemLabel="didNm"/>
											</form:select> 												                                     	 
										</td>
									</tr>
									</span>
									<tr>
										<th>권한</th>
										<td>								
											<form:select path="roleCode" title="상위그룹">
												<form:option value='' label="--선택하세요--" />
												<form:options items="${selectRoleGroup}"   itemValue="roleCode" itemLabel="roleNm"/>
											</form:select>                                     	 
										</td>
									</tr>											
													
									<tr>
										<th>사용여부</th>
										<td>
											<select id="menuUseYn" name="menuUseYn">
												<option value="">선택</option>
												<option value="Y" <c:if test = "${regist.menuUseYn == 'Y' }"> selected="selected" </c:if> >사용</option>
												<option value="N" <c:if test = "${regist.menuUseYn == 'N' }"> selected="selected" </c:if>>사용안함</option>
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
			listPage('regist','/backoffice/sub/basicManage/menuList.do' );			
		}
		
		$("#sp_center").css("disabled", "none");
		$("#sp_did").css("disabled", "none");
		
	    if ($("#mode").val() == "Ins"){   	       		 		
	 		$("#btnUpdate").text("등록");
	    }	else {		    	
	    	$("#btnUpdate").text("수정");
	    }
	});	  
	function view_Combo(){
		 
		if ($("#menuType").val() == "MENU_TYPE02"){
			$("#centerId").attr("disabled", true);
			$("#didId").attr("disabled", false);
		}else {			
			$("#centerId").attr("disabled", false);
			$("#didId").attr("disabled", true);
		}
		ViewCombo($("#menuType").val());
	}
	  function check_form(){	  			  
		  if (any_empt_line_id("menuNm", "메뉴명을 입력 하지 않았습니다.") == false) return;
		  if (any_empt_line_id("parentMenuId", "상위 메뉴명을 선택 하지 않았습니다.") == false) return;
		  if (any_empt_line_id("menuUseYn", "사용여부을 선택 하지 않았습니다.") == false) return;		  
		  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/menuUpdate.do").submit();
	  }	  
	  function del_form(){
		  if (confirm("삭제하시겠습니까?")== true){
		  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/menuDelete.do").submit();
		  }
	  }
	  function ViewCombo(code){		    
		  
			  apiExecute(
						"POST", 
						"/backoffice/sub/basicManage/menuCombo.do",
						{							
							menuType  : code			,
							didId : $("#didId").val()
						},
						null,				
						function(result) {				
							if (result.selectMenuGroup != null) {
								
								$("#parentMenuId").empty();
								$("#parentMenuId").append("<option value=''>--선택하세요--</option>");						
								for (var i=0; i<result.selectMenuGroup.length; i++) {
									var obj = result.selectMenuGroup[i];				
									if (code == "MENUTYPE01"){
										if (obj.menuId  == "0"){
											$("<option value='"+ obj.menuId +"'>"+ obj.menuNm +"</option>").appendTo("#parentMenuId");	
										}
									}else {
										if (obj.menuId  != "0"){
											$("<option value='"+ obj.menuId +"'>"+ obj.menuNm +"</option>").appendTo("#parentMenuId");	
										}																					
									}									
								}								
							}	
							if ( code== "MENU_TYPE01"  ){
								if (result.centerCombo != null) {
									$("#centerId").empty();
									$("#centerId").append("<option value=''>--선택하세요--</option>");						
									for (var i=0; i<result.centerCombo.length; i++) {
										var obj = result.centerCombo[i];
										$("<option value='"+ obj.centerId +"'>"+ obj.centerNm +"</option>").appendTo("#centerId");
									}						
								}	
							}else {
								if (result.selectDidGroup != null) {
									
									$("#didId").empty();
									$("#didId").append("<option value=''>--선택하세요--</option>");						
									for (var i=0; i<result.selectDidGroup.length; i++) {
										var obj = result.selectDidGroup[i];
										$("<option value='"+ obj.didId +"'>"+ obj.didNm +"</option>").appendTo("#didId");
									}								
								}	
							}
							
							
						},
						null,
						null
					);		  
		  }	   
	</script>
</body>
</html>		