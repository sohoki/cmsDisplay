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
<div class="con">			
  <div class="tableBox editTable sizeTable" style='vertical-align:top'>
  			
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/conPageUpdate.do">						
<form:hidden path="conSeq" id="conSeq"/>
<form:hidden path="mode" id="mode"/>
<form:hidden path="detailSeq" id="detailSeq"/>
<form:hidden path="pageGubun" id="pageGubun"/>
<table>
  <tr>
    <td>
      <form:select path="conType" id="conType" title="전문구분" onChange="javascript:imageGubun()">
         <form:option value="" label="--선택하세요--"/>
                    <form:options items="${selectConType}" itemValue="code" itemLabel="codeNm"/>
      </form:select>	
    </td>
    <td>
       <select name="imageSlidtype" id="imageSlidtype">
          <option value="">선택</option>
          <option value="1" <c:if test="${regist.imageSlidtype == '1' }"> selected </c:if>  >1번 스크롤</option>
          <option value="2" <c:if test="${regist.imageSlidtype == '2' }"> selected </c:if>>1번 스크롤</option>
       </select>
    </td>    
    <td>
     <form:input path="timeInterval" id="timeInterval" title="콘텐츠명" size="5"/>
    </td>    
    <td><a href="javascript:open_pop()">업로드</a></td>
    <td><a href="javascript:FileLst()">파일리스트</a></td>
    <td><a href="javascript:check_form()">업데이트</a></td>    
  </tr>
  <tr>
    <td colspan="6">
    <!--  내용 정리  -->  
    <div id="fileLst"></div>
    <!--  내용 정리  끝 부분 -->
    </td>
  </tr>
</table>
</form:form>
</div>
</div>
<script type="text/javascript">
	  $(document).ready(function() {		
			if ($("#detailSeq").val() != "" ){
				$("#mode").val("Edt");
				FileLst();
			}
			if (  "${status}" != "" ){
				if ("${status}" == "SUCCESS" ){
					alert("정상처리 되었습니다");				
				}else  {
					alert("처리 도중 문제가 발생 하였습니다.");				
				}					
			}							    
	  });	  	
	  function check_form(){
		  if (any_empt_line_id("conType", "콘텐츠 타입을  선택 하지 않았습니다.") == false) return;
		  if ($("#conType").val() == "CONTYPE02"){
			  if (any_empt_line_id("imageSlidtype", "이미지 슬라이드를 선택 하지 않았습니다.") == false) return;
			  if (any_empt_line_id("timeInterval", "슬라이드 타임을  선택 하지 않았습니다.") == false) return;
		  }
		  $("form[name=regist]").attr("action", "/backoffice/sub/conManage/conPageUpdate.do").submit();	
	  }
	  function imageGubun(){
		  if ($("#conType").val() == "CONTYPE02"){
			  $("select[name=imageSlidtype]").attr("disabled", false);
			  $("input[name=timeInterval]").attr("disabled", false);
		  }else {
			  $("select[name=imageSlidtype]").attr("disabled", true);
			  $("input[name=timeInterval]").attr("disabled", true);
		  }
		  
	  }
	  function open_pop(){
		   
		  var url = "/backoffice/sub/conManage/fileIupload.do?conSeq="+$("#conSeq").val()+"&detailSeq="+$("#detailSeq").val();	  
		  window.open(url,"파일 업로드", 'width=600,height=550,top=100,left=650,scrollbars=auto')	;
	  }
  function FileLst(){
		$("#fileLst").html('');        	
	    $("#conSeq").val();
	    $("#detailSeq").val();
	    
	    if ($("#conSeq").val() != "" && $("#detailSeq").val() != ""){
	    	
	    	var didDetailHtm = "";
	    	
	    	apiExecute(
					  "POST", 
					  "/backoffice/sub/conManage/imageTb.do",
	   					{						  
						  detailSeq :  $("#detailSeq").val()
	   					},
	   					null,				
	   					function(result) {
	   						a = 1;
	   						didDetailHtm = "<table><tbody><tr>";
	   						if (result != null) {
	   							
	 							if (result.conFileLst != null) {
	 								
	 							
	 								for (var i=0; i<result.conFileLst.length; i++) {
	 									var obj = result.conFileLst[i];
	 									if (a > 4){
	 										didDetailHtm += "</tr><tr><td>["+obj.orignlFileNm+"]</td><td><a href='javascript:del_file(&#39;"+obj.atchFileId + "&#39;)'>[삭제]</a></td>";
	 										a = 1;
	 									}else {
	 										didDetailHtm += "<td>["+obj.orignlFileNm+"]</td><td><a href='javascript:del_file(&#39;"+obj.atchFileId + "&#39;)'>[삭제]</a></td>";	
	 									}	 									
	 									a += 1;
	 								}	    
	 								
	 							}    
	 							
	 						didDetailHtm += "</tr></tbody></table>";
	 						
	   						$("#fileLst").html(didDetailHtm);
	   						}
	   					},
	   					function(request){
	   						alert(request.status );	       						
	   					},
	   					null
	 				);		    	
	         }  else {
	        	 alert("페이지 저장이 안되었습니다.");
	         } 
      }
     //파일 삭제 
       function del_file(code){
    	 apiExecute(
				  "POST", 
				  "/backoffice/sub/conManage/imageFileDel.do",
  					{
					  atchFileId : code
  					},
  					null,				
  					function(result) {							
   						if (result != null) {	         							
   							if (result == "O"){
   								alert("정삭적으로 삭제 되었습니다.");
   								FileLst();		   								
   							}else {
   								alert("삭제시 문제가 생겼습니다.");
   								FileLst();		 
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