<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/pops.css">
	<script src="/js/jquery-1.12.3.min.js"></script>	
    <script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<div class="contents">
		<div class="header">
			<h2>이마트 기본음원 등록</h2>
		</div>
		<div class="textT">
			<!--테이블시작-->
			    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/agentManage/mp3Upload.do">
			    <form:hidden path="mode" id="mode"/>
			    <form:hidden path="atchFileId" id="atchFileId"/>			    			    
			    <form:hidden path="streFileNm" id="streFileNm"/>
			    <form:hidden path="fileStreCours" id="fileStreCours"/>			    
				<table>
					<!--내용시작-->
					<tbody class="text_left">
						<tr>
							<th>음원명</th>
							<td><input type="text" id="orignlFileNm" name="orignlFileNm" title="음원명" value="${regist.orignlFileNm }">
							&nbsp;&nbsp;&nbsp;재생시간:${regist.fileThumnail }
							</td>
						</tr>
						<tr>
						  <th>사용구분</th>
						  <td>
						    <input type="radio" name="fileGubun" value="fileGubun01" <c:if test="${regist.fileGubun eq 'fileGubun01'}"> checked</c:if> > 음원파일 
							<input type="radio" name="fileGubun" value="fileGubun02" <c:if test="${regist.fileGubun eq 'fileGubun02'}"> checked</c:if>> 기초음원
						  </td>
						</tr>
						<c:if test="${regist.mode eq 'Ins'}" >
						<tr>
							<th>파일등록</th>
							<td><input type="file" id="fileNm" name="fileNm" title="스케줄명" ></td>
						</tr>	
						</c:if>
						
					    <c:if test="${regist.mode ne 'Ins'}" >
						<tr>
							<th>사용유무</th>
							<td>
							<input type="radio" name="UseYn" value="Y" <c:if test="${regist.useYn eq 'Y'}"> checked</c:if> > 사용 
							<input type="radio" name="UseYn" value="N" <c:if test="${regist.useYn eq 'N'}"> checked</c:if>> 사용안함
							</td>
						</tr>
						<tr>
						  <td colspan="2">
						  <span id="mp3Pre"></span>
						  <span id="mp3CurrentTime"></span>
						  </td>
						</tr>
						</c:if>
					</tbody>
				</table>			
				</form:form>	
			<div class="footerBox">
				<a href="javascript:check_form();" class="yellowBtn" id="btnUpdate">등록</a>			
			</div>		
		</div>
	</div>
	<script type="text/javascript">
            $("#fileNm").change( function(){            	
            	var fileValue = $("#fileNm").val().split("\\");
            	var fileName = fileValue[fileValue.length-1].substring(0, parseInt(fileValue[fileValue.length-1].lastIndexOf('.'))  );            	
            	if($("#orignlFileNm").val() == ""){
            		// 아무런 내용을 입력하지 않았을 때에만 이름을 설정 해주도록
            		$("#orignlFileNm").val(fileName);
            	}
            });
			$(document).ready(function() {
				if ("${status}" != "" ){
					if ("${status}" == "SUCCESS" ){
						alert("정상처리 되었습니다");		
						opener.parent.document.location.reload();
						self.close();
					}else  {
						alert("처리 도중 문제가 발생 하였습니다.");		
						document.form.reset();
					}	
				}	
				$("#mp3Pre").html("[재생]");
			    if ($("#mode").val() == "Ins"){   	       		 		
			 		$("#btnUpdate").text("등록");		 				 		
			    }	else {		    	
			    	$("#btnUpdate").text("수정");
			    	$("input[name=fileNm]").attr("disabled", true);
			    }
		  });
		
		  var mp3File = new Audio();
		  mp3File.src = $("#fileStreCours").val()+$("#streFileNm").val();		  
		  $("#mp3Pre").click(function(){
			  
			  if (!mp3File.paused ){				  
				  $("#mp3Pre").html("[재생]");
				  mp3File.pause();
			  }else {
				  $("#mp3Pre").html("[정지]");
				  mp3File.play();  
			  }
		  });	
		  	
		  function check_form(){
			  
			  if ($("#mode").val() == "Ins"){
				  if (any_empt_line_id("fileNm", "MP3 파일을 선택 하지 않았습니다.") == false) return;
		  		  
				  var FileFilter = /\.(mp3|wav|mid)$/i;
				  var extArray = new Array (".mp3", ".wav", ".mid");
				  
				  if ($("#fileNm").val().match(FileFilter)){			  
					  document.regist.encoding = "multipart/form-data";			  
				      document.regist.submit();  
				  }else {
					  alert("올바른 음원 파일이 아닙니다");
					  return false;
				  }   				  
			  }else {				  
				  
				  apiExecute(
		  				   "POST", 
		  				   "/backoffice/sub/brodManage/playContentUpdate.do",
		   					{
		 					   atchFileId : $("#atchFileId").val(),
		 					   useYn : $('input[name=UseYn]:checked', '#regist').val(),
		 					   orignlFileNm  : $("#orignlFileNm").val()		  
		   					},
		   					null,				
		   					function(result) {							
		    						if (result != null) {	         							
		    							if (result == "O"){
		    								alert("정삭적으로 수정 되었습니다.");
		    								opener.parent.document.location.reload();
		    								self.close();
		    							}else {
		    								alert("수정시 문제가 생겼습니다.");
		    								
		    							}
		    						}
		    					},
		   					null,
		   					null
		 				);	 				  
			  }
			  
			  
		  }
	 
	</script>
</body>
</html>