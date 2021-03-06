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

<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/didList.do">
        
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="didId" id="didId" >           
<input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
<div id="wrap">
<c:import url="/backoffice/inc/emart_header.do" />
<input type="hidden" name="author_Code" id="author_Code"  value="${authorCode}">
<input type="hidden" name="groupCode" id="groupCode"  value="${groupCode }">
<input type="hidden" name="parentGroupId" id="parentGroupId"  value="${parentGroupId }">

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
				<li class="active"><a href="/backoffice/sub/equiManage/didList.do" class="did">단말기 관리</a></li>
				
				<%-- <c:if test="${groupCode ne 'EMART_00000000000005' and authorCode == 'ROLE_ADMIN' }"> --%>
				<li><a href="/backoffice/sub/equiManage/did_groupList.do" class="didGroup">그룹 관리</a></li>
				<li><a href="/backoffice/sub/equiManage/didSendMessage.do" class="didGroup">메세지 관리</a></li>
				<li><a href="/backoffice/sub/equiManage/didSendMessageLst.do" class="didGroup">메세지 현황</a></li>
				<%-- </c:if> --%>
				<div class="clear"></div>
		</ul>
		<div class="con">			
			<div class="con_title whiteBox ">
					<h2><img src="/img/list.png"> 총 등록된 단말기리스트입니다.</h2>
					<div class="searchBox">
						<span>총 : ${totalCnt}개</span>
						<select name="pageUnit" id="pageUnit" >
						<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
						<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
						<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
						<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
					</select>
					<form:select path="centerId" id="centerId" title="지점">
				         <form:option value="" label="--선택하세요--"/>
                        <form:options items="${selectCenter}" itemValue="centerId" itemLabel="centerNm"/>
				   </form:select>
					<select name="searchCondition"  id="searchCondition">
						<option value="didNm" <c:if test="${searchVO.searchCondition == 'didNm' }"> selected="selected" </c:if>>단말기명</option>
						<option value="didId" <c:if test="${searchVO.searchCondition == 'didId' }"> selected="selected" </c:if>>단말기ID</option>
					</select>
					
						
				       <input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
				       <a href="javascript:search_form('/backoffice/sub/equiManage/didList.do')" class="blueBtn">검색</a>
				       <div class="footerBox">
				            <a href="javascript:view_Did('Ins','0')" class="yellowBtn">단말기 등록</a>
				            <a href="javascript:Restart_Did('REDOWN')" class="grayBtn">콘텐츠재전송</a>
							<a href="javascript:Restart_Did('RESTART')" class="grayBtn">재부팅</a>
						</div>
						<div class="clear"> </div>
					</div>			
				<table>
					<thead>
						<tr>
							<th>부서명</th>							
							<th>지점명</th>
							<th>단말기명</th>
							<th>IP</th>
							<th>DID스케줄</th>							
							<th>ON/OFF 현황</th>
							<th>OS구분</th>
							<th>사용유무</th>
							<th><input type="checkbox" id="checkAll" onClick="javascript:ch_all();"></th>				
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList }" var="didinfo" varStatus="status">
						<tr>
							<%-- <td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td> --%>
							<td>${didinfo.roleNm  }</td>
							<td>${didinfo.centerNm  }</td>
							<td><a href="javascript:view_Did('Edt','${didinfo.didId  }')"> ${didinfo.didNm  }</a><br>${didinfo.didId  }</td>
							<td><a href="javascript:view_Did('Edt','${didinfo.didId  }')">${didinfo.didIpaddr  }</a></td>
							<td>${didinfo.schCnt} 개</td>									
							<td>
							<c:choose>
							   <c:when test="${didinfo.didSttus eq 'ON' }">
									<img src="/img/on_icon.png" width="16px" height="16px" />
							   </c:when>
							   <c:otherwise>
							   		<img src="/img/off_icon.png" width="16px" height="16px" />
							   </c:otherwise>
							</c:choose>
							</td>
							<td>
							<c:choose>
							   <c:when test="${didinfo.didOs eq '안드로이드' }">
									<img src="/img/android_icon.png" width="16px" height="16px" />
							   </c:when>
							   <c:when test="${didinfo.didOs eq '윈도우' }">
									<img src="/img/windows_icon.png" width="16px" height="16px" />
							   </c:when>
							   <c:otherwise>
							   		<img src="/img/ios_icon.png" width="16px" height="16px" />
							   </c:otherwise>
							</c:choose>
							</td>
							<td>
							<c:choose>
							   <c:when test="${didinfo.didUseYn eq 'Y' }">
									<img src="/img/use_icon.png" width="16px" height="16px" />
							   </c:when>
							   <c:otherwise>
							   		<img src="/img/no_use_icon.png" width="16px" height="16px" />
							   </c:otherwise>
							</c:choose>
							</td>
							<td><input type="checkbox" name="didCheck" value="<c:out value="${didinfo.didId}"/>|<c:out value="${didinfo.didMac}"/>"></td>
						</tr>	
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0 }">
						<tr>
						  <td colspan="8">등록된 DID 가 없습니다</td>
						</tr>
						</c:if>		
					</tbody>
				</table>				 
				<div class="pageFooter">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
					<div class="clear"></div>	
				</div>
				
			</div>
		   </div>
		   <div class="clear"></div>
		</div>
		
        <!--내용끝-->
	</div>    
    
    </form:form>		
	<script type="text/javascript">
	  function view_Did(code, code1){	  
		  $('#mode').val(code);
		  $('#didId').val(code1);
		  if (code == "Ins"){			   
			  $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didDetail.do").submit();		       
		  }else {
			  $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didView.do").submit();			  
		  }		  
	 }
	 
	 function ch_all(){
			if ($("#checkAll").prop("checked")){
				$("input[type=checkbox]").prop("checked", true);
			}else {
				$("input[type=checkbox]").prop("checked", false);
			}
	 }
	 function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);		
		$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didList.do").submit();
	 }	 
	 //reset
	 
	 function Restart_Did(code){
		    var cnt = $("input[name=didCheck]:checkbox:checked").length;
	    	var del_atch = "";
	    	if (cnt< 1){
	    		alert("하나 이상의 체크를 선택 하셔야 합니다");
	    	}else {
	    		for (var i= 0; i < document.getElementsByName("didCheck").length; i++){
	    			if (document.getElementsByName("didCheck")[i].checked == true){
	    				del_atch = del_atch+","+document.getElementsByName("didCheck")[i].value;	
	    			}    			
	    		} 
	    		
	    			
	    		apiExecute(
	  				   "POST", 
	  				   "/backoffice/sub/equiManage/RestartDidInfo.do",
	   					{
	  					   xmlProceNm : code,
	  					   RestartDidInfo : del_atch
	   					},
	   					null,				
	   					function(result) {							
	    						if (result != null) {	         							
	    							if (result == "O"){
	    								alert("정삭적으로 재부팅 요청 하였습니다.");
	    								document.location.reload();
	    							}else {
	    								alert("재부팅 요청시 문제가 생겼습니다.");
	    								document.location.reload();
	    							}
	    						}
	    					},
	   					null,
	   					null
	 				);	    		
	    		
	    	}
	 }
		$(document).ready(function() {
			$('#author_Code').val(  $('#authorCode').val() );
			$('#role_Code').val($('#roleCode').val());
			
		});	  	 
	</script>
</body>
</html>		