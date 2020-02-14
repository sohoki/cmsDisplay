<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="ko" >
<title>이마트 회의실 정보</title>
<link rel="stylesheet" href="/new/css/reset.css">
<link rel="stylesheet" href="/new/css/layout.css">    
<link rel="stylesheet" href="/new/css/paragraph.css"> 
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/aten-emartcms-loginpage.js"></script>
<!--[if lte IE 8]>
<script src="js/poly-checked.min.js"></script> 
<![endif]-->
<!--popup-->
<link rel="stylesheet" href="/new/css/needpopup.css">
<script src="/new/js/needpopup.js"></script> 
<!--data-->
<script src="/new/js/jquery-ui.js"></script>
<script src="/new/js/datepipck.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="header">
		 <h1><img src="/img/logo.png" alt="이마트"></h1>
	</div>	
	<div class="pageTop">
		<div class="conIn">
			<h2>DID현황</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home">
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="container">
	<!--내용시작-->
	
	<%
		request.setCharacterEncoding("UTF-8");	
		request.getParameter("fileNm");
		
		// 파일 업로드된 경로
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "upload/";
		
		// 서버에 실제 저장된 파일명
		String filename = "paragraph.css" ;
		 
		// 실제 내보낼 파일명
		String orgfilename = "paragraph.css" ;
		    
		System.out.println("savePath : " + savePath);
		
		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";
		
		
		try{
			// 파일을 읽어 스트림에 담기
			try{
				file = new File(savePath, filename);
				in = new FileInputStream(file);
			}catch(FileNotFoundException fe){
				skip = true;
				System.out.println("FileNotFoundException ! - " + fe.toString());
			}
			 
			client = request.getHeader("User-Agent");
			
			// 파일 다운로드 헤더 지정
			response.reset() ;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			if(!skip){
				// I
				if(client.indexOf("MSIE") != -1){
					response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));
				}else{
				// 한글 파일명 처리
					orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");
					
					response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				} 
				 
				response.setHeader ("Content-Length", ""+file.length() );
				
				os = response.getOutputStream();
				byte b[] = new byte[(int)file.length()];
				int leng = 0;
				while( (leng = in.read(b)) > 0 ){
					os.write(b,0,leng);
				}
			}else{
				response.setContentType("text/html;charset=UTF-8");
				out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
			}
			if(in != null){
				in.close();
				in = null;
			}
			if(os != null){
				os.flush();
				os.close();
				pageContext.pushBody();
				os = null;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Meetroom file download error ! - " + e.toString());
		}finally{
			if(in != null){
				in.close();
				in = null;
			}
			if(os != null){
				os.close();
				os = null;
			}
		}
	%>	 
	</div>
	<div class="clear"></div>
	<div id="footer">
		<div class="conIn ">
			<span><img src="/img/emartLogo.png"></span>
			<address><span>서울특별시 성동구 뚝섬로 377(성수동2가)</span></address>
		</div>
	</div>
</body>
</html>		