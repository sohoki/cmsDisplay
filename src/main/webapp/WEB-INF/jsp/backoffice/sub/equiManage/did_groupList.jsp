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
	<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/did_groupList.do">
    <input type="hidden"  name="didId" id="didId" >
    <input type="hidden"  name="group_Code" id="group_Code" >
    <input type="hidden"  name="mode" id="mode">
    <input type="hidden"  name="idCheck" id="idCheck">
    <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
    <input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
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
						<li class="active"><a href="/backoffice/sub/equiManage/did_groupList.do" class="didGroup">그룹 관리</a></li>
						<li><a href="/backoffice/sub/equiManage/didSendMessage.do" class="didGroup">메세지 관리</a></li>
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
											<option value="GROUP_NM" <c:if test="${searchVO.searchCondition == 'GROUP_NM' }"> selected="selected" </c:if>>그릅명</option>
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
												<th>수정/삭제</th>		
											</tr>
										</thead>
										<!--테이블내용-->
										<tbody>
										   <c:forEach items="${resultList}" var="groupinfo" varStatus="status">
											<tr>
												<td>
												<a href="javascript:view_DidLst('${groupinfo.groupCode}')">${groupinfo.groupCode }</a>
												</td>
												<td><a href="javascript:view_DidLst('${groupinfo.groupCode}')">${groupinfo.groupNm }</a></td>
												<td class="md_btn">
												<a href="javascript:groupUpdate('EdtD','${groupinfo.groupCode}');" class="modi">수정</a> 
												<a href="javascript:groupUpdate('Del','${groupinfo.groupCode}');" class="delkey">삭제</a></td>
											</tr>
											</c:forEach>						
										</tbody>
									</table>
									<div class="groupBox">
										<form:input path="groupCode" id="groupCode" title="그룹 코드" size="14" />
										<a href="javascript:id_Check();" class="yellowBtn">중복검사</a>
										<form:input path="groupNm" id="groupNm" title="그룹명" size="14" />
										<a href="javascript:groupUpdate('Ins','0');" class="yellowBtn">그룹등록</a>					
									</div>

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
										<select name="cboDidLst" id="cboDidLst">						
                    					</select>	
										<a href="javascript:did_insert();" id="cboDidBtn" class="yellowBtn">단말기 등록</a>
									</div>		
								</div>
											
							</div>
							<div class="clear"></div>
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
			
    	    if($("#searchCondition").val() != "GROUP_ID"){
				$("#searchCondition").val("GROUP_NM");
			}
    	    $("#didInsert").attr("style", "visibility:hidden");
    	    $("#cboDidLst").attr("style", "visibility:hidden");
    	    $("#cboDidBtn").attr("style", "visibility:hidden");
			
			/* 추후 그룹 등록 후 해당 그룹을 바로 볼 수 있도록 수정
			var getUrl = new URL(location.href);
			var getParamStatus = getUrl.searchParams.get("status");
			var getParamMsg = getUrl.searchParams.get("message");
			
			
			if(getParamStatus == "SUCCESS"){
				if(getParamMsg == "입력되었습니다"){
					if($("#mode").val()== "Ins"){
						
					}
				}
			}
			*/
			
	    	if ("${status}" != "") {
	    		if ("${status}" == "SUCCESS") {
	    			alert("정상 처리 되었습니다");    
	    		}else if ("${status}" ==   "didInsertOK"){
	    			alert("정상 처리 되었습니다");    		
	    			view_DidLst ("${groupCode}");	    				
					$("#didId").val("");
					$("#groupCode").val('') ;
					$("#groupNm").val('');
	    		}else if ("${status}" ==   "didInsertFalse"){
	    			alert("작업 도중 문제가 발생 하였습니다.");
	    			view_DidLst ( "${groupCode}"   );
	    		}else{
	    			alert("작업 도중 문제가 발생 하였습니다.");
					$("#groupCode").val('') ;
					$("#groupNm").val('');
					location.href = "/backoffice/sub/equiManage/did_groupList.do";
	    		}
	    		$("#didId").val("");
				$("#groupCode").val('') ;
				$("#groupNm").val('');
	    	}   
            
        });
    </script>
    <script type="text/javascript">
    function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	   }  
       function search_form(){
    	    $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/did_groupList.do").submit();	
        }    
        function did_insert(){        	
        	$("#didId").val(    $("#cboDidLst").val()   );
        	$("#groupCode").val(    $("#group_Code").val()   );
        	
        	$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/DidgroupInsret.do").submit();
			$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/did_groupList.do.do");
			
        	                                     
        }
        function groupUpdate(code, code1){
        	$("#mode").val(code);
        	if (code== "Ins"){
        		$("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didgroupUpdate.do").submit();				
        	}else if (code=="EdtD") {
        		  //속성값 input 박스에 넣기
        		  $("#mode").val('Edt');
        		  $("#groupCode").val(code1) ;
        		  apiExecute(
        				  "POST", 
        				  "/backoffice/sub/equiManage/groupDetail.do",
	       					{
	       						groupCode : $("#groupCode").val()
	       					},
	       					null,				
	       					function(result) {							
	       						if (result != null) {	       							
	       							var groupArray = result.split('/');	       							
	       						    $("#groupNm").val(groupArray[0]);
	       						    $("#sp_update").html("<a href='javascript:groupUpdate(&#39;Edt&#39;,&#39;" + $("#groupCode").val() + "&#39;);' class='excel' >그룹수정</a>");
	       						}
	       					},
	       					function(request){

	       					},
	       					null
	     				);        
        		  
        		  
             }else if (code == "Del") {            	 
            	 $("#groupCode").val(code1) ;
            	 $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didgroupDelete.do").submit();
				 $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/did_groupList.do.do");
             }else {
            	 $("#groupCode").val(code1) ;
            	 $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didgroupUpdate.do").submit();
				 $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/did_groupList.do.do");
            	                                        
             }
        	
        }
        //우측 테이블 보여 주기 
        function view_DidLst(code){
        	
        	$("#detailTable").html('');
        	$("#didInsert").attr("style", "visibility:visible");
    	    $("#cboDidLst").attr("style", "visibility:visible");
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
       						didDetailHtm = "<table><thead><tr><th>단말ID</th><th>단말명</th><th>삭제</th></tr></thead><tbody>";
       						if (result != null) {
       							console.log(result);
     							if (result.didLst != null) {
     									
     								for (var i=0; i<result.didLst.length; i++) {
     									var obj = result.didLst[i];
     									didDetailHtm += "<tr><td>"+ obj.didId  +"</td><td>"+ obj.didNm  +"</td><td><a href='javascript:del_did(&#39;"+ obj.didId  +"&#39;,&#39;"+ code  +"&#39;)' class='delkey'>삭제</a></td></tr>";	     									
     								}	  
     								
     							}    
     							
       						
       						
       						didDetailHtm += "</tbody></table>";
       						$("#detailTable").html(didDetailHtm);
       						
	       						if (result.didCmbLst != null) {
	            					
	            					$("#cboDidLst").empty();
	        						$("#cboDidLst").append("<option value=''>--선택하세요--</option>");						    						
	            					for (var i=0; i<result.didCmbLst.length; i++) {
	            						var obj = result.didCmbLst[i];        						
										if(obj.didNm.substring(0,4) == "음원방송"){
											continue;
										}
	            						$("<option value='"+ obj.didId +"'>"+ obj.didNm +"</option>").appendTo("#cboDidLst");
	            					}        				
	            					
	            				}
       						}
       						
       						
       					},
       					function(request){
       						
       					},
       					null
     				);          
        	     
        }        
        function del_did(code, code1){
        	
        	$("#detailTable").html('');
        	
        	var didDetailHtm = "";
        	
        	apiExecute(
    				  "POST", 
    				  "/backoffice/sub/equiManage/DidgroupDel.do",
       					{
    					    didId : code, 
       						groupCode : code1
       					},
       					null,				
       					function(result) {
       						didDetailHtm = "<table><thead><tr><th>단말기명</th><th>위치</th><th>삭제</th></tr></thead><tbody>";
       						if (result != null) {
     							if (result.didLst != null) {
     								for (var i=0; i<result.didLst.length; i++) {
     									var obj = result.didLst[i];
     									didDetailHtm += "<tr><td>"+ obj.didId  +"</td><td>"+ obj.didNm  +"</td><td><a href='javascript:del_did(&#39;"+ obj.didId  +"&#39;,&#39;"+ code1 +"&#39;)' class='delkey'>삭제</a></td></tr>";	     									
     								}	  
     								
     							}     
								$("#cboDidLst").val("");    							
       						}       						
       						didDetailHtm += "</tbody></table>";
       						$("#detailTable").html(didDetailHtm);
       					},
       					function(request){
       						
       					},
       					null
     				);  
        	
        }

        function id_Check(){
        	 if ( $("#groupCode").val()!= ""   ){
        		    
	       		     apiExecute(
	       					"POST", 
	       					"/backoffice/sub/equiManage/IdCheck.do",
	       					{
	       						groupCode : $("#groupCode").val()
	       					},
	       					null,				
	       					function(result) {							
	       						if (result != null) {	       					
	       							if (result == "0"){
	       								alert("사용 하실수 있는 코드입니다.");
	       								$("#idCheck").val("Y");							
	       							}else {
	       								alert("사용 하실수 없는 코드입니다.");
	       								$("#idCheck").val("N");
	       							}
	       						}
	       					},
	       					null,
	       					null
	       				); 
        		    
	       	  }else {
	       		    	alert ("그룹코드를 입력해 주세요");
	       		    	$("#groupCode").focus();
	       		    	return;
	       	 }        	       
        }       
    </script>
</body>
</html>		