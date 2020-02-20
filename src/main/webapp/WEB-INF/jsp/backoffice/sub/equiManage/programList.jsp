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

<link rel="stylesheet" href="/new/css/needpopup.css">
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/didList.do">
        
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="progCode" id="progCode" >           
<input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
<div id="wrap">
<c:import url="/backoffice/inc/emart_header.do" />

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
						<option value="progTitle" <c:if test="${searchVO.searchCondition == 'progTitle' }"> selected="selected" </c:if>>제목</option>
						<option value="fileInfo" <c:if test="${searchVO.searchCondition == 'fileInfo' }"> selected="selected" </c:if>>파일명</option>
					</select>
					
						
				       <input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
				       <a href="javascript:search_form()" class="blueBtn">검색</a>
				       <div class="footerBox">
				            <a href="javascript:fn_Porg('Ins','0')" class="yellowBtn" data-needpopup-show="#small-popup-reg">프로그램 등록</a>
						</div>
						<div class="clear"> </div>
					</div>			
				<table>
					<thead>
						<tr>
							<th>프로그램</th>							
							<th>OS 구분</th>
							<th>업데이트내용</th>
							<th>등록일</th>
							<th>등록자</th>							
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
    
    <div id='small-popup-reg' class="needpopup">
	   
	   <div class="contents">
			<div class="header">
				<h2><span id="spTitle" style="color: #fff;font-size: 20px;">프로그램 등록</span></h2>				
			</div>
			<div class="textT">
				<!--테이블시작-->	    
					<table>
						<!--내용시작-->
						<tbody class="text_left" style="background-color:#fff;">
							<tr>
								<th>프로그램 버전</th>
								<td colspan="3"><input type="text" id="progTitle" name="progTitle" max="200" size="80" title="내용"></td>
							</tr>
							<tr>
								<th>OS Type</th>
								<td>
								   <form:select path="progOstype" id="progOstype" title="기본음원선택">
										         <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${groupTimegubun}" itemValue="code" itemLabel="codeNm"/>
								    </form:select>
								</td>
								<th>업데이트 프로그램</th>
								<td>
								  <span id="fileInfoTxt"></span>
								  <input type="hidden" id="fileInfo" />
								</td>
							</tr>
							<tr>
								<th>업데이트 내역</th>
								<td colspan="3"><input type="text" id="progRemark" name="progRemark" max="2000" size="80" title="내용"></td>
							</tr>
							<tr>
							  <td colspan="4" style="text-align:center">
							  <a href="javascript:fn_CheckForm();" class="yellowBtn" id="btn_Update">등록</a>
							  </td>
							</tr>
						</tbody>
					</table>
			</div>
		</div>
	</div>
		
    <script type="text/javascript" src="/js/needpopup.min.js"></script>
	<script type="text/javascript">
	 function fn_Porg(mode, progCode){	  
		 if (mode == "Ins"){
			 $("#spTitle").html("프로그램 등록");
			 $("#btn_Update").text = "등록";
		 }else{
			 $("#progCode").val(progCode);
			 $("#spTitle").html("프로그램 수정");
			 $("#btn_Update").text = "수정";
			 
			 uniAjax(	"/backoffice/sub/equiManage/progInfo.do",
						{
							mode: $("#mode").val(),
							progCode : $("#progCode").val()
						},				
						function(result) {	
							if (result.status == "SUCCESS"){
								//리스트 보여주기
								var obj = result.result;
								$("#progTitle").val(obj.progTitle);
								$("#progOstype").val(obj.progOstype);
								$("#fileInfoTxt").html(obj.fileInfoTxt);
								$("#progRemark").val(obj.progRemark);
							}else {
								alert(result.message);
							}							
						},
	                    function (request){
							alert("ERROR:" + request.state);
						}
				 );
		 }
	 }
	 function search_form(){
		 $(":hidden[name=pageIndex]").val("1");	
		 $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/progList.do").submit();		 
	 }
	 function fn_CheckForm(){
		 if (any_empt_line_id("progTitle", "버전을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("progOstype", "os type을 선택 하지 않았습니다.") == false) return;
		 uniAjax(	"/backoffice/sub/equiManage/progInfo.do",
					{
						mode: $("#mode").val(),
						progCode : $("#progCode").val(),
						progTitle : $("#progTitle").val(),
						progOstype : $("#progOstype").val(),
						progRemark : $("#progRemark").val(),
						fileInfo : $("#fileInfo").val()
					},				
					function(result) {	
						if (result.status == "SUCCESS"){
							//리스트 보여주기
							location.reload();
						}else {
							alert(result.message);
						}							
					},
                 function (request){
						alert("ERROR:" + request.state);
					}
			 );
		 
		 
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
		$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/progList.do").submit();
	 }	 
	 //reset
	 $(document).ready(function() {
			//시작시 넣을 파일
		needPopup.config.custom = {
			'removerPlace': 'outside',
			'closeOnOutside': false,
			onShow: function() {},
			onHide: function() {}
		};
		needPopup.init();
		
	 });	  	 
	</script>
</body>
</html>		