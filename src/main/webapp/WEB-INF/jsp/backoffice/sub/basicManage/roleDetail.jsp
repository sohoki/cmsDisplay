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
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<title>이마트DID운영관리</title>
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<c:import url="/backoffice/inc/emart_header.do" />

<div class="pageTop">
		<div class="conIn">
			<h2>Role관리</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home"> 
				기초관리 > Role관리
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="container">
		<!--내용시작-->
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/roleUpdate.do">			   
			   <form:hidden path="roleCode" />
			   <form:hidden path="mode" />
			   <form:hidden path="pageIndex" />
			   <form:hidden path="pageSize" />
			   <form:hidden path="searchCondition" />
			   <form:hidden path="searchKeyword" />
			   <form:hidden path="menuGubun" id="menuGubun" />
			   
		<c:import url="/backoffice/inc/emart_tree.do" />
        <div class="con">			
			<div class="tableBox editTable">				
			
			   
				<table>
					<tbody>
						<tr>
							<th>Role Name</th>
							<td>
							<form:select path="authorCode" title="권한정보">
                                    <form:option value='' label="--선택하세요--" />
                                    <form:options items="${author}"   itemValue="authorCode" itemLabel="authorNm"/>
                                   </form:select>
							</td>
						</tr>
						<tr>
							<th>Role Name</th>
							<td>
							<form:input title="부서코드" path="roleNm"  />
							</td>
						</tr>
						<tr>	
							<th>사용유무</th>
							<td>
							 <select id="useYn" name="useYn">
									<option value="">선택</option>
									<option value="Y" <c:if test = "${regist.useYn == 'Y' }"> selected="selected" </c:if> >사용</option>
									<option value="N" <c:if test = "${regist.useYn == 'N' }"> selected="selected" </c:if>>사용안함</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>권한</th>
							<td>
							 <select id="roleCheck" name="roleCheck">
									<option value="">선택</option>
									<option value="R" <c:if test = "${regist.roleCheck == 'R' }"> selected="selected" </c:if> >열람만</option>
									<option value="I" <c:if test = "${regist.roleCheck == 'N' }"> selected="selected" </c:if>>입력만</option>
									<option value="E" <c:if test = "${regist.roleCheck == 'E' }"> selected="selected" </c:if>>입력/수정만</option>
									<option value="A" <c:if test = "${regist.roleCheck == 'A' }"> selected="selected" </c:if>>모든권한</option>
								</select>
							</td>
						</tr>			
					</tbody>
				</table>
				
				<div class="btnBox md_btn">						
					<a href="javascript:listPage('regist','/backoffice/sub/basicManage/roleList.do' )" class="excel boxH">목록</a> 
					<a href="javascript:checkForm();" class="modi boxH" id="btnUpdate">등록</a>
					<c:if test = "${regist.mode != 'Ins' }"> 
					
					<a href="javascript:delCheck()" class="delkey boxH">삭제</a>
					</c:if>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		
		</form:form>
        <!--내용끝-->
	</div>
	<div class="clear"></div>
    <c:import url="/backoffice/inc/emart_footer.do" />
	<script type="text/javascript">
	$(document).ready(function() {
		if (  "${status}" != "" ){
			if ("${status}" == "SUCCESS" ){
				alert("정상처리 되었습니다");				
			}else  {
				alert("처리 도중 문제가 발생 하였습니다.");				
			}			
			listPage('regist','/backoffice/sub/basicManage/roleList.do' );			
		}					
	    if ($("#mode").val() == "Ins"){   	       		 		
	 		$("#btnUpdate").text("등록");
	    }	else {		    	
	    	$("#btnUpdate").text("수정");
	    }
	});	  
	  function checkForm(){	  		  
		  if (any_empt_line_id("roleNm", "Role 명을 입력 하지 않았습니다.") == false) return;		  
		  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/roleUpdate.do").submit();
	  }
	  function delCheck(){
		  if (confirm("삭제 하시겠습니까?")== true){
			  $("#mode").val("Del");
			  $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/roleDelete.do").submit();
		  }
	  }
	  
	</script>
</body>
</html>		