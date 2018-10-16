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
<div class="pageTop">
		<div class="conIn">
			<h2>콘텐츠 그룹관리</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home"> 
				장비관리 > 콘텐츠 그룹관리
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="container">
		<!--내용시작-->
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/didManagerList.do">				   					
		<form:hidden path="mode" id="mode"/>
		<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
		<input type="hidden" name="author_Code" id="author_Code"  value="${searchVO.author_Code }">
        <input type="hidden" name="role_Code" id="role_Code"  value="${searchVO.role_Code }">
					
		<c:import url="/backoffice/inc/emart_tree.do" />
        <div class="con">			
			<div class="tableBox">				
				
				<table>
					
					<div class="appli">
						<div class="veiw_box">
							<p>총 : ${totalCnt}개</p>
							<select name="pageUnit" id="pageUnit" >
								<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
								<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
								<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
								<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
							</select>
						</div>
						<div class="onOff">
							<span class="roomimg"><a href="javascript:did_statusCk('ON')">ON</a></span>
							<span class="roomimg"><a href="javascript:did_statusCk('OFF')">OFF</a></span>
						</div>
						<div class="srch_box">
							 <form:select path="searchschCode" id="searchschCode" title="소속" onChange="javascript:check_form();">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectschLst}" itemValue="schCode" itemLabel="schName"/>
							   </form:select>
							<span class="roomimg"><a href="javascript:did_statusCk('SCH')">스케줄 Send</a></span>
						</div>
						<div class="select_box">
							 <form:select path="searchdidSwver" id="searchdidSwver" title="소속"  onChange="javascript:check_form();">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectSwver}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							<span class="roomimg"><a href="javascript:did_statusCk('SWF')">업데이트</a></span>
						</div>
						<div class="allsel right_box">
							<span><input type="checkbox" name="all_check" id="all_check"  style="width:15px"> 전체선택</a></span> 
						</div>
					</div>
					</form:form>
					<thead>
					    
						<tr>							
							<th>번호</th>
							<th>단말기명</th>
							<th>Group명</th>
							<th>현운영스케줄</th>
							<th>DID 상태</th>
							<th>Softwar VER</th>
							<th>상태</th>					
						</tr>
						
					</thead>
					<tbody>
					  <c:forEach items="${resultList }" var="didManageinfo" varStatus="status">
						<tr>
							<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
							<td>${didManageinfo.didNm  }</td>
							<td>${didManageinfo.groupNm  }</td>
							<td>${didManageinfo.schName  }</td>
							<td>
							<c:choose>
							   <c:when test="${centerInfo.didSttus eq 'Y' }">ON</c:when>
							   <c:otherwise>OFF</c:otherwise>
							</c:choose>	
							</td>
							<td>${didManageinfo.codeNm  }</td>
							<td><input type="checkbox" name="didId" id="didId"  value="${didManageinfo.didId }" /></td>
						</tr>		
						</c:forEach>	
					</tbody>
				</table>				
				<div class="clear"></div>
			</div>
			<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_brdMstr"  />
			</div>
		</div>
	</div>
	<div class="clear"></div>
    <c:import url="/backoffice/inc/emart_footer.do" />
    <script type="text/javascript">
     function search_form(){
    	 
     }
      function did_statusCk(code){
    	  var check_del ="";
    	  var url = "";
    	  var codeValue = "";
    	  var proces_gubunTxt = "";
    	  
    	  $("input[name=didId]:checked").each(function(){
    		  check_del += $(this).val()+","; 
    	  });    	
    	  
    	  
    	  
    	  if (check_del == ""){
    		  alert("체크 하신 값이 없습니다.");
    	  }else {   
		    		  if (code == "ON" || code == "OFF"){
		    			  proces_gubunTxt = code;
		    			  codeValue = code;
		    		  }else if (code == "SCH"){
		    			  if ($("#searchschCode").val() == ""){
		    				  alert ("스케줄을 선택하신것이 없습니다.");
		    				  return;
		    			  }else {
		    				  proces_gubunTxt = "SCH";
		    				  codeValue = $("#searchschCode").val();
		    			  }    			  
		    		  }else if (code == "SWF"){
		    			  if ($("#searchdidSwver").val() == ""){
		    				  alert ("software 버전을 선택하신것이 없습니다.");
		    				  return;
		    			  }else {
		    				  proces_gubunTxt = "SWF";
		    				  codeValue = $("#searchdidSwver").val();
		    			  }    			  
		    		  }
		    		  
		    		  apiExecute(
		     					"POST", 
		     					"/backoffice/sub/equiManage/didChange.do",     					
		     					{
		     						sch_code : check_del.substring(0, check_del.length-1)     					  
		     					    , update_code : 	codeValue 
		     					    , proces_gubun : proces_gubunTxt
		     					},
		     					null,				
		     					function(result) {							
		     						if (result != null) {	       					
		     							if (result == "T"){
		     								alert("정삭적으로 등록 되었습니다.");     															
		     							}else {
		     								alert("등록시 문제가 생겼습니다.");     							
		     							}     							
		     							search_form();
		     						}
		     					},
		     					null,
		     					null
		     				); 		    		
    		  
    	  }
      }
      $(document).ready(function () {		  
    	  $('#author_Code').val(  $('#authorCode').val() );
		  $('#role_Code').val($('#roleCode').val())
    	  
          $("#all_check").click(function () {
              if ($("#all_check").is(":checked")) {
                  $('input:checkbox[id^=didId]:not(checked)').prop("checked", true);
              } else {
                  $('input:checkbox[id^=didId]:checked').prop("checked", false);
              }
          });
      });
    </script>
</body>
</html>		