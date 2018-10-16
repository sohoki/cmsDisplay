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
    <title>DID운영관리</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/reset.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/codeList.do">	   
	    <input type="hidden"  name="idCheck" id="idCheck">	    
	    <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
        <input type="hidden" name="mode" id="mode" >
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
						<li><a href="/backoffice/sub/basicManage/manageList.do" class="manage">관리자관리</a></li>
						<li class="active"><a href="/backoffice/sub/basicManage/codeList.do" class="code">기초코드관리</a></li>
						<c:if test="${groupCode ne 'EMART_00000000000005' }">
						<li><a href="/backoffice/sub/basicManage/selectGroupLst.do" class="selectG">부서관리</a></li>
						</c:if>
						<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점관리</a></li>				
						<c:if test="${groupCode ne 'EMART_00000000000005' }">
						<li><a href="/backoffice/sub/basicManage/menuList.do" class="tmenu">메뉴관리(지점)</a></li>
						<li><a href="/backoffice/sub/basicManage/tmenuList.do" class="tmenu">메뉴관리(타입)</a></li>
						</c:if>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--단말기 그룹관리-->
						<div class="con_sub ">
							<div class="groupCon whiteBox">
								<div class="innerBox ">
								<div class="groupBox">
								        총 : ${totalCnt}개 &nbsp;&nbsp;
										검색어: <input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
										<a href="javascript:search_form()" class="yellowBtn">검색</a>	
										
										<form:input path="codeId" id="codeId" title="코드" size="14" />
										<a href="javascript:id_Check();" class="yellowBtn">중복검사</a>
										<form:input path="codeIdNm" id="codeIdNm" title="코드명" size="14" />
										<span id="sp_update"><a href="javascript:codeUpdate('Ins','0');" class="yellowBtn" >코드등록</a></span>
									</div>
									
									<!--테이블시작-->
									<table class="">
										<!--테이블상단-->
										<thead>
											<tr>							
												<th>기본코드</th>
												<th>코드명</th>
												<th>수정/삭제</th>					
											</tr>
										</thead>
										<tbody>
										   <c:forEach items="${resultList }" var="codeinfo" varStatus="status">
											<tr>
												<td>
												<a href="javascript:view_CodeLst('${codeinfo.codeId}')">${codeinfo.codeId }</a>
												</td>
												<td><a href="javascript:view_CodeLst('${codeinfo.codeId}')">${codeinfo.codeIdNm }</a></td>
												<td class="md_btn">
												<a href="javascript:codeUpdate('EdtD','${codeinfo.codeId}');" class="modi">수정</a> 
												<a href="javascript:codeUpdate('Del','${codeinfo.codeId}');" class="delkey">삭제</a></td>
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
    function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	   }    
    $(document).ready(function() {        
    	    if ($("#mode").val()== ""){ $("#mode").val("Ins"); }
    	        	    
	    	if ("${status}" != "") {
	    		
	    		if ("${status}" == "SUCCESS") {
	    			alert("정상 처리 되었습니다");    		
	    			$("#CodeId").val('') ;
					$("#codeDNm").val('');
					 location.href = "/backoffice/sub/basicManage/codeList.do";
	    		}else if ("${status}" ==   "codeDInsertOK"){
	    			alert("상세 코드가 정상 처리 되었습니다");    		
	    			$("#code_d").val("");
	    			$("#codeDNm").val("");	    			
	    			view_CodeLst('${codeId}');	    				
	    		}else if ("${status}" ==   "codeDInsertFase"){
	    			alert("작업 도중 문제가 발생 하였습니다.");
	    			$("#code_d").val("");
	    			$("#codeDNm").val("");	    			
	    			view_CodeLst('${codeId}');
	    		}else{
	    			alert("작업 도중 문제가 발생 하였습니다.");
	    			$("#CodeId").val('') ;
					$("#codeDNm").val('');
					 location.href = "/backoffice/sub/basicManage/codeList.do";
	    		}
	    		
	    	}    	
            
        });
    </script>
    <script type="text/javascript">
        function codeDetail_insert(){        	
        	if ( $("#idDetailCheck").val() == "Y" ){
	        		 var input_code = document.createElement("input");
	          		 $(input_code).attr("type","hidden").attr("name","code").attr("id","code").attr("value", $("#code_d").val()) ;       		 
	          		 $("form[name=regist]").append( input_code );
	          		 
	          	     var input_codeNm  = document.createElement("input");
	          	     $(input_codeNm).attr("type","hidden").attr("name","codeNm").attr("id","codeNm").attr("value", $("#codeDNm").val()) ;
	          	     $("form[name=regist]").append( input_codeNm );
	           	     $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/CodeDetailUpdate.do").submit(); 	
        		
        	}else {
        		alert("중복 검사를 확인 하지 않았습니다");
        		$("#code_d").focus();        			
        	}
        		
        }
        function codeUpdate(code, code1){
        	$("#mode").val(code);
        	
        	if (code== "Ins"){
        		$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/codeUpdate.do").submit();
        	}else if (code=="EdtD") {
        		  //속성값 input 박스에 넣기
        		  $("#mode").val('Edt');
        		  $("#codeId").val(code1) ;
        		  apiExecute(
        				  "POST", 
        				  "/backoffice/sub/basicManage/codeDetail.do",
	       					{
        					  codeId : $("#codeId").val()
	       					},
	       					null,				
	       					function(result) {							
	       						if (result != null) {	       							
	       							var groupArray = result.split('/');	       							
	       						    $("#codeIdNm").val(groupArray[0]);
	       						    $("#sp_update").html("<a href='javascript:codeUpdate(&#39;Edt&#39;,&#39;" + $("#codeId").val() + "&#39;);' class='excel' >코드수정</a>");
	       						}
	       					},
	       					function(request){
	       						alert(request.status );	       						
	       					},
	       					null
	     				);        
        		  
        		  
             }else if (code == "Del") {            	 
            	 $("#codeId").val(code1) ;
            	 $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/codeDelete.do").submit();
             }else {
            	 $("#codeId").val(code1) ;
            	 $("form[name=regist]").attr("action", "/backoffice/sub/basicManage/codeUpdate.do").submit();
             }
        	
        }
        //우측 테이블 보여 주기 
        function view_CodeLst(code){
        	
        	$("#detailTable").html('');        	
    	    $("#codeId").val(code);    	    
        	var didDetailHtm = "";
        	
        	apiExecute(
    				  "POST", 
    				  "/backoffice/sub/basicManage/CmmnDetailCodeList.do",
       					{
       						codeId : code
       					},
       					null,				
       					function(result) {
       						didDetailHtm = "<table><thead><tr><th>분류코드</th><th>분류명</th><th>삭제</th></tr></thead><tbody>";
       						if (result != null) {
       							
     							if (result.cmDetailLst != null) {
     									
     								for (var i=0; i<result.cmDetailLst.length; i++) {
     									var obj = result.cmDetailLst[i];
     									didDetailHtm += "<tr><td>"+ obj.code  +"</td><td>"+ obj.codeNm  +"</td><td><a href='javascript:del_code(&#39;"+ obj.code  +"&#39;,&#39;"+ code  +"&#39;)' class='delkey'>삭제</a></td></tr>";	     									
     								}	  
     								
     							}    
     							
     						didDetailHtm += "</tbody></table>";
       						
       						didDetailHtm += "<table><tr><td><div class='btnBox two_box'>";
       						didDetailHtm += "<input  type='text' name='code_d' id='code_d' title='코드' size='5' />";			   
       						didDetailHtm += "<a href='javascript:id_DetailCheck();' class='excel'  id='id_DetailButton'>중복검사</a>";
       						didDetailHtm += "<input  type='text' name='codeDNm' id='codeDNm' title='코드명' size='5' />";
       						didDetailHtm += "<span><a href='javascript:codeDetail_insert(&#39;Ins&#39;,&#39;0&#39;);' class='excel' >등록</a></span>";
       						didDetailHtm += "<input type='hidden' name='idDetailCheck' id='idDetailCheck' />";
       						didDetailHtm += "</div></td></tr></table>";		 
       						
       						$("#detailTable").html(didDetailHtm);
       						}
       					},
       					function(request){
       						alert(request.status );	       						
       					},
       					null
     				);                  	
        }        
        function id_DetailCheck(){
        	
        	 if ( $("#code_d").val()!= ""   ){
     		    
       		     apiExecute(
       					"POST", 
       					"/backoffice/sub/basicManage/codeDetailIDCheck.do",
       					{
       						code : $("#code_d").val()
       					},
       					null,				
       					function(result) {							
       						if (result != null) {	       					
       							if (result == "0"){
       								alert("사용 하실수 있는 코드입니다.");
       								$("#idDetailCheck").val("Y");							
       							}else {
       								alert("사용 하실수 없는 코드입니다.");
       								$("#idDetailCheck").val("N");
       							}
       						}
       					},
       					null,
       					null
       				); 
    		    
       	  }else {
       		    	alert ("코드를 입력해 주세요");
       		    	$("#code_d").focus();
       		    	return;
       	 }        	  
        }
        function del_code(code, code1){
        	
        	$("#detailTable").html('');
        	
        	var didDetailHtm = "";
        	
        	apiExecute(
    				  "POST", 
    				  "/backoffice/sub/basicManage/codeDetailCodeDelete.do",
       					{
    					    code : code, 
       						codeId : code1
       					},
       					null,				
       					function(result) {
       						didDetailHtm = "<table><thead><tr><th>분류코드</th><th>분류명</th><th>삭제</th></tr></thead><tbody>";
       						if (result != null) {
       							
     							if (result.cmDetailLst != null) {
     									
     								for (var i=0; i<result.cmDetailLst.length; i++) {
     									var obj = result.cmDetailLst[i];
     									didDetailHtm += "<tr><td>"+ obj.code  +"</td><td>"+ obj.codeNm  +"</td><td><a href='javascript:del_code(&#39;"+ obj.code  +"&#39;,&#39;"+ code  +"&#39;)' class='delkey'>삭제</a></td></tr>";	     									
     								}	  
     								
     							}    
     							
     						didDetailHtm += "</tbody></table>";
       						
       						didDetailHtm += "<table><tr><td><div class='btnBox two_box'>";
       						didDetailHtm += "<input  type='text' name='code_d' id='code_d' title='코드' size='5' />";			   
       						didDetailHtm += "<a href='javascript:id_DetailCheck();' class='excel'  id='id_DetailButton'>중복검사</a>";
       						didDetailHtm += "<input  type='text' name='codeDNm' id='codeDNm' title='코드명' size='5' />";
       						didDetailHtm += "<span><a href='javascript:codeDetail_insert(&#39;Ins&#39;,&#39;0&#39;);' class='excel' >등록</a></span>";
       						didDetailHtm += "<input type='hidden' name='idDetailCheck' id='idDetailCheck' />";
       						didDetailHtm += "</div></td></tr></table>";		 
       						
       						$("#detailTable").html(didDetailHtm);
       						}
       					},
       					function(request){
       						alert(request.status );	       						
       					},
       					null
     				);  
        	
        }
        function search_form(){
        	$("form[name=regist]").attr("action", "/backoffice/sub/basicManage/codeList.do").submit();        	
        }
        function id_Check(){
        	 if ( $("#codeId").val()!= ""   ){
        		    
	       		     apiExecute(
	       					"POST", 
	       					"/backoffice/sub/basicManage/codeIDCheck.do",
	       					{
	       						CodeId : $("#codeId").val()
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
	       		    	$("#codeId").focus();
	       		    	return;
	       	 }        	       
        }       
    </script>
</body>
</html>		