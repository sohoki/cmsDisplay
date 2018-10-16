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
<title> DID운영관리</title>
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/popup.js"></script>
<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/didSendMessageLst.do">
        
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
        
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
				<li><a href="/backoffice/sub/equiManage/didList.do" class="did">단말기 관리</a></li>
				<li><a href="/backoffice/sub/equiManage/did_groupList.do" class="didGroup">그룹 관리</a></li>
				<li><a href="/backoffice/sub/equiManage/didSendMessage.do" class="didGroup">메세지 관리</a></li>
				<li class="active"><a href="/backoffice/sub/equiManage/didSendMessageLst.do" class="didGroup">메세지 현황</a></li>
				<div class="clear"></div>
		</ul>
		<div class="con">			
			<div class="con_title whiteBox ">
					<h2><img src="/img/list.png"> 총 등록된 메세지 리스트입니다.</h2>
					<div class="searchBox">
						<span>총 : ${totalCnt}개</span>
						<select name="pageUnit" id="pageUnit" >
						<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
						<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
						<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
						<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
					</select>
						<select name="searchCondition"  id="searchCondition">
						<option value>선택</option>
						<option value="groupNm" <c:if test="${searchVO.searchCondition == 'groupNm' }"> selected="selected" </c:if>>그룹명</option>
						<option value="didNm" <c:if test="${searchVO.searchCondition == 'didNm' }"> selected="selected" </c:if>>단말기명</option>
					</select>
						
						<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
						<a href="javascript:search_form()" class="blueBtn">검색</a>
						<div class="footerBox">
						<a href="javascript:Delete_Message()" class="grayBtn">삭제</a>	
						</div>
						<div class="clear"> </div>
					</div>			
				<table>
					<thead>
						<tr>							
							<th>번호</th>
							<th>그룹명</th>
							<th>단말기명</th>
							<th>메세지</th>
							<th>운영일</th>
							<th>시작시간</th>
							<th>단말기 전송여부</th>
							<th>수정</th>
							<th><input type="checkbox" id="checkAll" onClick="javascript:ch_all();"></th>				
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList }" var="sendMsgInfo" varStatus="status">
						<tr>
							<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
							<td>${sendMsgInfo.groupNm  }</td>
							<td>${sendMsgInfo.didNm }</td>
							<td>
							<c:choose>
							   <c:when test="${fn:length(sendMsgInfo.sendMessage) > 10}">
							   ${fn:substring(sendMsgInfo.sendMessage, 0, 10) }...</c:when>
							   <c:otherwise>${sendMsgInfo.sendMessage }</c:otherwise>
							</c:choose>
							</td>
							<td>${sendMsgInfo.sendMessageStartDay}~${sendMsgInfo.sendMessageEndDay}</td>
							<td>${sendMsgInfo.sendMessageStartTime}~${sendMsgInfo.sendMessageEndTime}</td>
							<td>
							<c:choose>
							   <c:when test="${fn:length(sendMsgInfo.sendDidCheckDate) > 5}">
							   ${sendMsgInfo.sendDidCheckDate }</c:when>
							   <c:otherwise>미전송</c:otherwise>
							</c:choose>
							</td>
							<td>
							<a href="javascript:Edt_View('${sendMsgInfo.sendMsgId  }')" class="grayBtn">수정</a>
							</td>							
							<td><input type="checkbox" name="didCheck" value="<c:out value="${sendMsgInfo.sendDidId  }"/> "></td>
						</tr>	
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0 }">
						<tr>
						  <td colspan="8">등록된 메세지가 없습니다</td>
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
	 function search_form(){
		 $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didSendMessageLst.do").submit();		 
	 }
	 function linkPage(pageNo) {
			$(":hidden[name=pageIndex]").val(pageNo);		
			$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didSendMessageLst.do").submit();
	 }		 
	 function ch_all(){
			if ($("#checkAll").prop("checked")){
				$("input[type=checkbox]").prop("checked", true);
			}else {
				$("input[type=checkbox]").prop("checked", false);
			}
	 }
	 //reset
	 function Delete_Message(){
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
	  				   "/backoffice/sub/equiManage/didDeleteMessageReg.do",
	   					{
	  					 delMessage : del_atch
	   					},
	   					null,				
	   					function(result) {							
	    						if (result != null) {	         							
	    							if (result == "O"){
	    								alert("정삭적으로 삭제 되었습니다.");
	    								document.location.reload();
	    							}else {
	    								alert("삭제시 문제가 생겼습니다.");
	    								document.location.reload();
	    							}
	    						}
	    					},
	   					null,
	   					null
	 				);	    		
	    		
	    	}		 
	 }
	 function Edt_View(code1){
		  var url = "/backoffice/sub/equiManage/didSendMessageReg.do?sendMsgId="+code1+"&mode=Edt";
	      window.open(url,"contentReg", 'width=800,height=550,top=100,left=650,scrollbars=auto');
	 }
	 //시작
	 $(document).ready(function() {
		
	 });	  	 
	</script>
</body>
</html>		