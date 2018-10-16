<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
  
</body>
<script>
function batchConfirm(){
	  
	  apiExecute(
				"POST", 
				"http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4128159000",
				{
					
				},
				null,				
				function(result) {				
					if (result != null) {
						alert(result);
					}							
				},
				null,
				null
			);	
}
</script>

</html>