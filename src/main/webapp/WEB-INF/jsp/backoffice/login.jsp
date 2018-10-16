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
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
	<div id="header">
		<!-- 
		<div class="login">
			<p><a>&nbsp;&nbsp;&nbsp;</a></p>
		</div>
		 -->	
		 <h1><img src="/img/logo.png" alt="이마트"></h1>
		<!-- <div class="conIn topmenu" id="header">
			<h1><img src="/img/logo.png" alt="이마트"></h1>
		</div> -->
	</div>	
	<div class="pageTop">
		<div class="conIn">
			<h2>DID현황</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home"> 
				DID현황
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="container">
		<!--내용시작-->
		<form name="regist" method="post" action="/backoffice/sub/SecurityLogin.do" onsubmit="return form_check();">
		<input name="userSe" type="hidden" value="GNR"/>		
		<div class="mbLogin">			
			<p class="loginPageTitle">EMART CMS</p>
			<div> 
				<div class="mainLoginBox">
					<div class="loginInputBox">
						<div class="idBox">
							<p class="login_input_id_display">아이디</p>
							<input type="text" name="mberId" id="mberId" class="loginInputTag" size="30" tabindex="1" autofocus>
						</div>
						<div class="pwBox">
							<p class="login_input_pw_display">비밀번호</p>
							<input type="password" name=password id="password" size="30" class="loginInputTag" tabindex="2">
						</div>
						<div class="btnBox">
							<input type="submit" value="로그인" class="loginBtn loginPageBtn"/>
							<a class="idSearchBtn loginPageBtn">비밀번호 찾기</a>
							<a class="joinBtn loginPageBtn">이용신청</a>
						</div>
					</div>
				</div>
			</div>
			   
				<!-- <table class="loginTable">
					<tbody>
						<tr>
							<td style="text-align:left"><input type="text" name="mberId" id="mberId" size="30" tabindex="1"></td>
						</tr>
						<tr>
							<td style="text-align:left"><input type="password" name=password id="password" size="30" tabindex="2"></td>
						</tr>
						<tr>
							<td style="text-align:left">
								<input type="submit" value="LOGIN"  class="loginB yellowBtn" />
								<a class="blueBtn" style="padding: 8px 24px;">이용신청</a>
							</td>							
						</tr>
					</tbody>
					<div class="btnLogin">
						
					</div>
				
					
					<tbody>
						<th>Username</th>
						<td style="text-align:left"><input type="text" name="mberId" id="mberId" size="15"></td>
						<tr></tr>
						<th>Password</th>
						<td style="text-align:left"><input type="password" name=password id="password" size="15"></td>

						
					</tbody>
					
				</table> -->
				
<!-- 				<div class="btnLogin">
					<input type="submit" value="LOGIN"  class="loginB"  >
				</div> -->
			<div class="clearfix"></div>
		</div>
		</form>
		<!--내용끝-->
	</div>
	<div class="clear"></div>
	<div id="footer">
		<div class="conIn ">
			<span><img src="/img/emartLogo.png"></span>
			<address><span>서울특별시 성동구 뚝섬로 377(성수동2가)</span></address>
		</div>
	</div>
    <script type="text/javascript">
       function form_check(){
    	   if (any_empt_line_id("mberId", "아이디를 입력 하지 않았습니다.") == false) return;
    	   if (any_empt_line_id("password", "패스워드를 입력 하지 않았습니다.") == false) return;
    	   $("form[name=regist]").attr("action", "/backoffice/sub/SecurityLogin.do").submit();
       }       
       $(document).ready(function() {
    	   
	    	if ("${message}" != "") {
	    		  if ("${message}" == "login_ok"){
	    			  location.href="/backoffice/sub/equiManage/didList.do";  
	    		  }else {
	    			  alert("아이디 또는 패스워드가 잘못 입력 하였습니다.");
		    		  $("#mberId").focus() ;	    			  
	    		  }				
	    	}    	           	    	
       });       
    </script>
</body>
</html>		