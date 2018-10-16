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
<title>이마트DID운영관리</title>
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/popup.js"></script>
<script type="text/javascript" src="/js/leftMenu.js"></script>	
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form commandName="regist" name="regist" method="post">											   	
<form:hidden path="mode"  id="mode"/>        
<form:hidden path="searchCondition" id="searchCondition" />
<form:hidden path="searchKeyword"  id="searchKeyword"/>
<form:hidden path="pageIndex"  id="pageIndex"/>
<form:hidden path="pageUnit"  id="pageUnit"/>
<form:hidden path="menuGubun" id="menuGubun" />
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
							<li class="active"><a href="/backoffice/sub/basicManage/manageList.do" class="manage">관리자관리</a></li>
							<li><a href="/backoffice/sub/basicManage/codeList.do" class="code">기초코드관리</a></li>
							<c:if test="${groupCode ne 'EMART_00000000000005' }">
							<li><a href="/backoffice/sub/basicManage/selectGroupLst.do" class="selectG">부서관리</a></li>
							</c:if>
							<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점관리</a></li>				
							<c:if test="${groupCode ne 'EMART_00000000000005' }">
							<li><a href="/backoffice/sub/basicManage/menuList.do" class="tmenu">메뉴관리(지점)</a></li>
							<li><a href="/backoffice/sub/basicManage/tmenuList.do" class="tmenu">메뉴관리(타입)</a></li>
							</c:if>
							<div class="clear"></div>
						</ul>
			            <div class="con">			
				          <div class="tableBox">		
					          <div class="con_title whiteBox ">
					                            <div class="header">
													<h2>관리자 등록</h2>
												</div>	
												                           
					              				<table>
													<tbody>
														<tr>
															<th>*아이디</th>
															<td style="text-align:left">
															<form:input  path="mberId" size="10" maxlength="20" id="mberId"   value="${regist.mberId }" />
															<span id="uniCheck" ></span>
															</td>
															<th>*관리자명</th>
															<td style="text-align:left"><form:input path="mberNm" title="회원이름"    maxlength="20" size="20"  value="${regist.mberNm }" /></td>
														</tr>
														<tr>
															<th>*비밀번호</th>
															<td style="text-align:left"><form:password path="password" id="password" title="비밀번호" size="20" maxlength="20" /></td>
															<th>*비밀번호 확인</th>
															<td style="text-align:left"><input name="password2" id="password2" title="비밀번호확인" type="password" size="20" maxlength="20" /></td>
														</tr>
														
														<!-- 
														<tr>
															<th>*이메일</th>
															<td><form:input path="mberEmailAdres" title="이메일"    maxlength="60" size="40"   value="${regist.mberEmailAdres }"/></td>
														</tr>
														 -->
														<tr>
															<th>*부서</th>
															<td style="text-align:left">
															<form:select path="groupId" id="groupId" title="소속">
															         <form:option value="" label="--선택하세요--"/>
											                        <form:options items="${selectGroup}" itemValue="groupId" itemLabel="groupNm"/>
															</form:select>								
															</td>
															<th>*Author</th>
															<td style="text-align:left">
															   <form:select path="authorCode" id="authorCode" title="소속"  onChange="javascript:ViewRole();">
															         <form:option value="" label="--선택하세요--"/>
											                        <form:options items="${selectAuthor}" itemValue="authorCode" itemLabel="authorNm"/>
															   </form:select>											
															</td>
														</tr>
														<tr>
														    <!-- 
															<th>Role</th>
															<td style="text-align:left">
															   <form:select path="roleCode" id="roleCode" title="소속">
															         <form:option value="" label="--선택하세요--"/>
											                        <form:options items="${selectRole}" itemValue="roleCode" itemLabel="roleNm"/>
															   </form:select>											
															</td>
															 -->
															<th>지점 선택</th>
															<td style="text-align:left">
															   <form:select path="centerId" id="centerId" title="소속">
															         <form:option value="" label="--선택하세요--"/>
											                        <form:options items="${selectCenter}" itemValue="centerId" itemLabel="centerNm"/>
															   </form:select>											
															</td>
															<th>상태</th>
															<td colspan="3" style="text-align:left">
															  <form:select path="mberSttus" id="mberSttus" title="소속">
															         <form:option value="" label="--선택하세요--"/>
											                        <form:options items="${selectState}" itemValue="code" itemLabel="codeNm"/>
															  </form:select>										
															</td>
														</tr>	
																
															
														<!-- 
														<tr>
															<th>*연락처</th>
															<td class="textBoxs">
															<form:input path="mbtlnum1" title="연락처"    maxlength="3" size="3"  />-
															<form:input path="mbtlnum2" title="연락처"    maxlength="4" size="4" />-
															<form:input path="mbtlnum3" title="연락처"    maxlength="4" size="4" />
															</td>
														</tr>
														 -->						
													</tbody>
												</table>
												
												<div class="footerBox">
													<a href="javascript:listPage('regist','/backoffice/sub/basicManage/manageList.do' )" class="yellowBtn">목록</a>
													<a href="javascript:check_form()" class="yellowBtn" id="btnBt">입력</a>
													<c:if test = "${regist.mode != 'Ins' }">	
													<a href="javascript:del_form()" class="grayBtn">삭제</a>
													</c:if>			
					                            </div> 												
					          
				              </div>
				              
				            
					  
			              </div> 
			              
		               </div>
		               
					</div>
				</div>
		  </div>
   <div class="clear"></div>
</div>		
</form:form>    
	<script type="text/javascript">
	$(document).ready(function() {     
		//alert("${status}");
		
		if ("${status}" != ""){
			if ("${status}" == "SUCCESS") {
				alert("정상 처리 되었습니다");  
				$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/manageList.do").submit();
			}else{
				alert("작업 도중 문제가 발생 하였습니다.");
			}						
		}		
	    if ($("#mode").val() == "Ins"){   	       
	 		$("form[name=regist]").append("<input type='hidden'  id='idCheck' name='idCheck' />");	 		
	 		$("#uniCheck").html("<a href='javascript:check_id();' class='reCheck'>중복검사</a>");	 		
	 		$("input[name=centerId]").attr("disabled", true);
	    }	else {
	   	 $("#uniCheck").html();
	   	$("input[name=mberId]").attr("readOnly", true);
	    }
	});
	function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);		
		$("form[name=regist]").submit();
	}
	function check_id(){	  
	    if ( $("#mberId").val()!= ""   ){
	    apiExecute(
				"POST", 
				"/backoffice/sub/basicManage/IdCheck.do",
				{
					userID : $("#mberId").val()
				},
				null,				
				function(result) {							
					if (result != null) {					
						if (result == "0"){
							alert("사용 하실수 있는 아이디 입니다.");
							$("#idCheck").val("Y");							
						}else {
							alert("사용 하실수 없는 아이디 입니다.");
							$("#idCheck").val("N");
						}
						
					}
				},
				null,
				null
			);
	    }else {
	    	alert ("아이디를 입력해 주세요");
	    	$("#mberId").focus();
	    	return;
	    }
	}
	function check_form(){	  
		   if ($("#mode").val() == "Ins"){
			   if ($("#idCheck").val() == "N"){
				   alert("중복 아이디 체크를 하지 않았습니다");
				   return ;			   
			   }
			   
			   if(!chkPwd( $.trim($('#password').val()))){ 
				   alert('비밀번호를 확인하세요.₩n(영문,숫자를 혼합하여 10~20자 이내)');    
				   $('#password').val('');
				   $('#password').focus(); return ;
				   }
			   if ( $.trim($('#password').val()) !=   $.trim($('#password').val())  ){
				   alert("비밀 번호가 일치 하지 않습니다.")
			   }
		   }
		   if (any_empt_line_id("mberId", "아이디를 입력 하지 않았습니다.") == false) return;
		   if (any_empt_line_id("mberNm", "이름을 입력 하지 않았습니다.") == false) return;	   	
		   if (any_empt_line_id("authorCode", "권한을 선택 하지 않았습니다.") == false) return;	   		   	
		   $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/managerUpdate.do").submit();
		   return;
	}
	function del_form(){
		
	    if (confirm("삭제 하시겠습니까?")== true){
	    	apiExecute(
					"POST", 
					"/backoffice/sub/basicManage/managerDelete.do",
					{
						mberId : $("#mberId").val()
					},
					null,				
					function(result) {				
						if (result != null) {
							if (result == "O"){
								alert("정상적으로 삭제되었습니다.");
							}else {
								alert("삭제시 문제가 생겼습니다");
							}					
						}else {
						    alert("삭제시 문제가 생겼습니다");
						  
						}
						$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/manageList.do").submit();					
					},
					null,
					null
				);	
	    }else {
	    	return;
	    }
	   	  	   
	}
	function chkPwd(str){
		 var reg_pwd = /^.*(?=.{10,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		 if(!reg_pwd.test(str)){
		  return false;
		 }
		 return true;
	}
	function ViewRole(){
		  if ( $("#authorCode").val() !=  "ROLE_ADMIN "  ){
			  
			  apiExecute(
						"POST", 
						"/backoffice/sub/basicManage/roleCombo.do",
						{
							authorCode : $("#authorCode").val()
						},
						null,				
						function(result) {				
							if (result.centerCombo != null) {
								$("#centerId").empty();
								$("#centerId").append("<option value=''>--선택하세요--</option>");						
								for (var i=0; i<result.centerCombo.length; i++) {
									var obj = result.centerCombo[i];
									$("<option value='"+ obj.centerId +"'>"+ obj.centerNm +"</option>").appendTo("#centerId");
								}						
							}
							/* if (result.roleCombo != null) {
								$("#roleCode").empty();
								$("#roleCode").append("<option value=''>--선택하세요--</option>");						
								for (var i=0; i<result.roleCombo.length; i++) {
									var obj = result.roleCombo[i];
									$("<option value='"+ obj.roleCode +"'>"+ obj.roleNm +"</option>").appendTo("#roleCode");
								}
								
							} */
						},
						null,
						null
					);		  
		  }
	   }
</script>



</body>
</html>