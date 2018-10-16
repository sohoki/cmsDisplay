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
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<c:import url="/backoffice/inc/emart_header.do" />

	
	<div id="container">
		<!--내용시작-->
   <form name="regist" id="regist" action="/backoffice/sub/equiManage/Capture.do" method="post" enctype="multipart/form-data">
       <input type="hidden" name="didId" id="didId" value="C17013103002">
       <input type="hidden" name="didMac" id="didMac" value="52-54-00-12-34-56">
       <input type="hidden" name="msgSeq" id="msgSeq" value="3826">

	   <input type="file" name="DID_PICTURE" >
	   <a href="javascript:check_form()">[실행]</a>
   </form>
   <script type="text/javascript">
      function check_form(){
	    document.regist.submit();
	  }
   </script>
        
        <!--내용끝-->
	</div>
	<div class="clear"></div>


</body>
</html>		