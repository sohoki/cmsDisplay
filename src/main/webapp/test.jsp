<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Qcon</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/layout.css">
	<link rel="stylesheet" href="../css/paragraph.css">
	<!--alert -->
	<link rel="stylesheet" href="../css/alertify.core.css" />
	<link rel="stylesheet" href="../css/alertify.default.css" id="toggleCSS" />	

	<!--alert -->
	<script src="../js/alertify.min.js"></script>	
	<script src="../js/jquery-1.9.1.js"></script>	
	
	<!--bootstrap -->
    <script src="../js/bootstrap/bootstrap.min.js"></script>

</head>
<body>
	<div class="wrap">
		<div>
			<a href="logout.asp">로그아웃</a>
		</div>
		<div>
			<input type="button" value="logout" onclick="javascript:logout();"/>
		</div>
		<div>
			<div onClick="change_logout()"><span>변경 로그아웃</span></div>
		</div>
		
		<div onClick="file_browse()"><span>사진추가</span></div>
		
	</div>
	<script type="text/javascript">
	function logout(){
		
		if(window.SKHonorsAndroidApp){
			window.SKHonorsAndroidApp.logoutCall();
		}else{
			alert('logout call');
			//webkit.messageHandlers.logoutCall.postMessage('logout');
			document.location = "swiftcall:logoutCall";
			location.href="logout.asp";

		}
		
		
		// if(window.SKHonorsAndroidApp){
		// 	window.SKHonorsAndroidApp.logoutCall();
		// }else{
		// 	document.location="swiftcall:logoutCall";
		// }
		
	}
	
	function change_logout(){
		if(window.SKHonorsAndroidApp){
			window.SKHonorsAndroidApp.logoutCall();
		}else{
			alert('logout call');
			//webkit.messageHandlers.logoutCall.postMessage('logout');
			document.location = "swiftcall:logoutCall";
			location.href="logout.asp";

		}

	}
	
	function file_browse(){
		if(window.SKHonorsAndroidApp){
			window.SKHonorsAndroidApp.galleryCall();
		}else{
			//alert('아이폰! ');
			//webkit.messageHandlers.galleryCall.postMessage('pic');
			document.location="swiftcall:galleryCall";
		}
	}
	</script>
</body>
</html>