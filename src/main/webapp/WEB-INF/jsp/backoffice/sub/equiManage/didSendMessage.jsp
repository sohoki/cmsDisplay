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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/didSendMessage.do">
    <input type="hidden"  name="didId" id="didId" >
    <input type="hidden"  name="group_Code" id="group_Code" >
    <input type="hidden"  name="mode" id="mode">
    <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
    
    	<div id="wrap">
		<c:import url="/backoffice/inc/emart_header.do" />
		<div class="left-contain">
				<c:import url="/backoffice/inc/cms_left.do" />
				<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
			</div>
		<div class="container">
			
			<div class="main-content">
				<div class="content">
					<!--//상단 탭메뉴-->
					<ul class="topMenu" >
						<li><a href="/backoffice/sub/equiManage/didList.do" class="did">단말기 관리</a></li>
						<li><a href="/backoffice/sub/equiManage/did_groupList.do" class="didGroup">그룹 관리</a></li>
						<li class="active"><a href="/backoffice/sub/equiManage/didSendMessage.do" class="didGroup">메세지 관리</a></li>
						<li><a href="/backoffice/sub/equiManage/didSendMessageLst.do" class="didGroup">메세지 현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--단말기 그룹관리-->
						<div class="con_sub ">
							<div class="groupCon whiteBox">
								<div class="innerBox ">
									<div class="groupBox">
										총 : ${totalCnt}개
										<select name="searchCondition"  id="searchCondition">
											<option value>선택</option>
											<option value="GROUP_ID" <c:if test="${searchVO.searchCondition == 'GROUP_ID' }"> selected="selected" </c:if>>그룹코드</option>
											<option value="GROUP_NM" <c:if test="${searchVO.searchCondition == 'GROUP_NM' }"> selected="selected" </c:if>>그룹명</option>
										</select>
										<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
										<a href="javascript:search_form()" class="yellowBtn">검색</a>	
															
									</div>
									<!--테이블시작-->
									<table class="">
										<!--테이블상단-->
										<thead>
											<!--줄시작-->
											<tr>
												<th>그룹코드</th>
												<th>그룹명</th>
											</tr>
										</thead>
										<!--테이블내용-->
										<tbody>
										   <c:forEach items="${resultList }" var="groupinfo" varStatus="status">
											<tr>
												<td>
												<a href="javascript:view_DidLst('${groupinfo.groupCode}')">${groupinfo.groupCode }</a>
												</td>
												<td><a href="javascript:view_DidLst('${groupinfo.groupCode}')">${groupinfo.groupNm }</a></td>
											</tr>
											</c:forEach>						
										</tbody>
									</table>
									<div class="pageFooter">
										<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
										<div class="clear"></div>	
									</div>			
								</div>
														
							</div>
							<div class="beetCon groupTable">				
							</div>
							<div class="groupCon whiteBox groupTable">
								<div class="innerBox">
									<!--테이블시작-->
									<span id="detailTable" ></span>
									<div class="footerBox">
										<a href="javascript:sendMessage();" id="cboDidBtn" class="yellowBtn">메세지 등록</a>
									</div>		
								</div>
											
							</div>

						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>    
    </form:form>
    <script type="text/javascript">
    $(document).ready(function() {        
	    if ($("#mode").val()== ""){ $("#mode").val("Ins"); }
    	if ("${status}" != "") {
    		if ("${status}" == "SUCCESS") {
    			alert("정상 처리 되었습니다");    			    			
    		}else{
    			alert("작업 도중 문제가 발생 하였습니다.");
    		}
    		$("#groupCode").val('') ;
			$("#groupNm").val('');
			 location.href = "/backoffice/sub/equiManage/didSendMessage.do";
    	}    
    	$("#cboDidBtn").attr("style", "visibility:hidden");
    });
    function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	}  
    
    function search_form(){
  	     $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didSendMessage.do").submit();	
    }   
	function ch_all(){
			if ($("#checkAll").prop("checked")){
				$("input[type=checkbox]").prop("checked", true);
			}else {
				$("input[type=checkbox]").prop("checked", false);
			}
	}
	function sendMessage(){
    	var cnt = $("input[name=didCheck]:checkbox:checked").length;
    	var message_atch = "";
    	if (cnt< 1){
    		alert("하나 이상의 체크를 선택 하셔야 합니다");
    	}else {
    		for (var i= 0; i < document.getElementsByName("didCheck").length; i++){
    			if (document.getElementsByName("didCheck")[i].checked == true){
    				message_atch = message_atch+","+document.getElementsByName("didCheck")[i].value;	
    			}    			
    		}     		
    		var url = "/backoffice/sub/equiManage/didSendMessageReg.do?message_atch="+message_atch+"&groupCode="+$("#group_Code").val()+"&mode=Ins";	      
	        window.open(url,"contentReg", 'width=800,height=550,top=100,left=650,scrollbars=auto');
    	}
    }
    function view_DidLst(code){
    	
    	$("#detailTable").html('');    	
	    $("#cboDidBtn").attr("style", "visibility:visible");
	    $("#group_Code").val(code);
    	var didDetailHtm = "";
    	
    	apiExecute(
				  "POST", 
				  "/backoffice/sub/equiManage/DidgroupLst.do",
   					{
   						groupCode : code
   					},
   					null,				
   					function(result) {
   						didDetailHtm = "<table><thead><tr><th><input type='checkbox' id='checkAll' onClick='javascript:ch_all();'></th><th>단말기명</th><th>위치</th></tr></thead><tbody>";
   						if (result != null) {
 							if (result.didLst != null) {
 								for (var i=0; i<result.didLst.length; i++) {
 									var obj = result.didLst[i];
 									didDetailHtm += "<tr><td><input type='checkbox' name='didCheck' value='"+ obj.didId  +"'></td><td>"+ obj.didId  +"</td><td>"+ obj.didNm  +"</td></tr>";	     									
 								}	   								
 							}    
 							
   						didDetailHtm += "</tbody></table>";
   						$("#detailTable").html(didDetailHtm);
   							
   						}   						
   					},
   					function(request){
   						alert(request.status );	       						
   					},
   					null
 				);          
    	     
    }
    </script>    
</body>
</html>