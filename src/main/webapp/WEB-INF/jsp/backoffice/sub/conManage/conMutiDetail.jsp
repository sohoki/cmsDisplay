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
<link href="<c:url value='/'/>css/jquery-ui.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<!--leftMenu-->
<script src="/js/leftMenu.js"></script>
<script type="text/javascript">
  $( function() {
    $( "#slider-vertical" ).slider({
      orientation: "vertical",
      range: "min",
      min: 0,
      max: 10,
      value: 1,
      slide: function( event, ui ) {
        $( "#conTime" ).val( ui.value );
      }
    });
    $( "#conTime" ).val( $( "#slider-vertical" ).slider( "value" ) );
  } );
  
  $(document).ready(function(){
	  
	  // two-select-tap con_signage con_musicPop conSelect
	  
	  function conTypeSelect(type){
		  if (type == "s"){
			  $("#con_musicPop").removeClass("conSelect");
			  $("#con_signage").addClass("conSelect");
		  } else {
			  $("#con_signage").removeClass("conSelect");
			  $("#con_musicPop").addClass("conSelect");
		  }
	  }
	  
	  
  });
  
  
  function conTypeSelect(type){
	  if (type == "s"){
		  $("#con_musicPop").removeClass("conSelect");
		  $("#con_signage").addClass("conSelect");
	  } else {
		  $("#con_signage").removeClass("conSelect");
		  $("#con_musicPop").addClass("conSelect");
	  }
  }
  
  
  </script>
  
  <style>
  	.two-select-tap{
  		border: 1px solid #d4d4d4;
  	}
  	.two-select-tap li{
  		width:50%;
  		float:left;
  	}
  	.two-select-tap li a{
	  	display: block;
	    text-align: center;
	    color: #8f8f8f;
	    padding: 10px;
	    font-size: 14px;
  	}
  	.two-select-tap li a.conSelect{
	  	color: #fff;
	    background: #dc2c33;
	    font-weight: bold;
  	}
  	
  	
  	
  </style>



</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

      <div id="wrap">
		<!--//HEADER-->
		<%-- <c:import url="/backoffice/inc/cms_header.do" /> --%>
		<c:import url="/backoffice/inc/emart_header.do" />
		<!--HEADER//-->
		<div class="left-contain">
			<c:import url="/backoffice/inc/cms_left.do" />
			<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
		</div>	
		<div class="container">
			<div class="main-content">				
				<div class="content">
					<ul class="topMenu" >
						<li><a href="/backoffice/sub/conManage/mediaLst.do" class="media">미디어 관리</a></li>
						<li class="active"><a href="/backoffice/sub/conManage/conMutiList.do" class="conMutiList">화면 구성</a></li>
						<div class="clear"></div>
					</ul>
						<!--내용시작-->
					    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/conMutiUpdate.do">						
						<form:hidden path="conSeq" id="conSeq"/>
						<form:hidden path="mode" id="mode"/>
						<form:hidden path="pageIndex" />
				        <form:hidden path="pageSize" />
				        <form:hidden path="searchCondition" />
				        <form:hidden path="searchKeyword" />
				        <form:hidden path="menuGubun" id="menuGubun" />
						        		
						
				        <div class="con">
						<!--스케줄 관리-->
							<div class="con_title whiteBox ">
								<h2>화면 상세 관리.</h2>
								<div class="searchBox">
									<div style="float:left; width: 256px;">
										<ul class="two-select-tap">
											<li><a class="conSelect" id="con_signage" href="javascript:conTypeSelect('s');">사이니지 편성</a></li>
											<li><a id="con_musicPop" href="javascript:conTypeSelect('m');">음원POP 편성</a></li>
										</ul>
										
										
									</div>
									<div class="footerBox">
									 	
										<a href="javascript:listPage('regist','/backoffice/sub/conManage/conMutiList.do' )" class="yellowBtn">목록</a> 					
										<a href="javascript:check_form()" class="grayBtn" id="btnUpdate">등록</a>
										<c:if test = "${regist.mode == 'Edt' }">
										<a href="javascript:preView();" class="blueBtn">미리보기</a> 
										<a href="javascript:del_form()" class="grayBtn">삭제</a>
										</c:if>
									</div>
									<div class="clear"></div>
								</div>
								<table>
									<tbody>
										<tr>
											<th>화면명</th>
											<td style="text-align:left"><form:input path="conNm" id="conNm" title="콘텐츠명" size="15"/></td>
											<th>화면타입</th>
											<td style="text-align:left">
												<form:select path="conScreen" id="conScreen" title="전문구분" onChange="javascript:ViewPage()">
											         <form:option value="" label="--선택하세요--"/>
							                        <form:options items="${selectScreenType}" itemValue="code" itemLabel="codeNm"/>
											   </form:select>
											</td>
											<th>가로/세로</th>
											<td style="text-align:left">
												<form:select path="conType" id="conType" title="전문구분" >
											         <form:option value="" label="--선택하세요--"/>
							                        <form:options items="${selectConType}" itemValue="code" itemLabel="codeNm"/>
											   </form:select>
											</td>							
										</tr>
										<tr>
											<th>사용여부</th>
											<td style="text-align:left">
											<input type="radio" name="conUseYn" value="Y" <c:if test="${regist.conUseYn == 'Y' }"> checked </c:if> checked />사용
											<input type="radio" name="conUseYn" value="N" <c:if test="${regist.conUseYn == 'N' }"> checked </c:if> />미사용
											</td>							
											<th>화면 사이즈</th>
											<td class="smiddleBox" style="text-align:left">
												넓이 : <form:input path="conWidth" id="conWidth" title="Width" size="5"  numberonly="true" /> 
												높이 : <form:input path="conHeight" id="conHeight" title="Height" size="5"  numberonly="true" />
											</td>
											<th>다음시퀀스</th>
										    <td style="text-align:left">
										    <form:select path="conNextSeq" id="conNextSeq" title="소속">
											         <form:option value="" label="--선택하세요--"/>
							                        <form:options items="${selectNextSeq}" itemValue="conSeq" itemLabel="conNm"/>
											</form:select>							
										    </td>							
										</tr>
										<tr>
										    <th>분할사이즈</th>
										    <td  style="text-align:left">
										    	<form:input path="conMid" id="conMid" title="중간넓이" size="5"  numberonly="true" /> (px)
										    	<span> *가로 : 좌측(넓이) / 세로 : 상단(높이)의 사이즈를 입력</span>
										    </td>
											<th>분할재생기준</th>
											<td colspan="3" style="text-align:left">
										       	<select id="conPartPlayMethod" title="콘텐츠플레이방식">
										    		<option value="PAGE01">1번페이지기준</option>
													<option value="PAGE02">2번페이지기준</option>
													<option value="PAGE00">최종플레이대기</option>
										    	</select>
										    	<%-- <form:select path="conPartPlayMethod" id="conPartPlayMethod" title="콘텐츠플레이방식">
													<form:options items="1번페이지기준" itemValue="PAGE01" itemLabel="1번페이지기준"/>
													<form:options items="2번페이지기준" itemValue="PAGE02" itemLabel="2번페이지기준"/>
													<form:options items="최종플레이대기" itemValue="PAGE00" itemLabel="최종플레이대기"/>
												</form:select> --%>
											</td>
										</tr>												
									</tbody>
								</table>				
						</div>
						</form:form>
						
	                    <!--//하단 콘텐츠 끝 부분 -->
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
      <script type="text/javascript">
			$(document).ready(function() {
				if (  "${status}" != "" ){
					if ("${status}" == "SUCCESS" ){
						alert("정상처리 되었습니다");				
					}else  {
						alert("처리 도중 문제가 발생 하였습니다.");				
					}			
					location.href="/backoffice/sub/conManage/conMutiList.do";	
				}					
			    if ($("#mode").val() == "Ins"){   	       		 		
			 		$("#btnUpdate").text("등록");
			    }	else {		    	
			    	$("#btnUpdate").text("수정");
			    }
		 });
		 //	
		 function UrlView(){
			if ($("#conUrlType").val().is("URL02") ){
				$("#conBasicUrl").show();
			}else {
				$("#conBasicUrl").hide();
			}
		 }
	     //화면 설정 
		 function ViewPage(){	
			 $("input:radio[name='conUseYn']:radio[value='Y']").prop('checked', true);
			 $("input:radio[name='conUseYn']:radio[value='N']").prop('checked', false);
			 if ($("#conScreen").val() ==  "W11" ){
				 $("#conType").val("DIDTYPE01");
				 $("#conWidth").val("1920");
				 $("#conHeight").val("1080");
				 $("#conMid").val("0");		
				 $("#conTime").val("1");
			 }else if ($("#conScreen").val() ==  "H11" ){
				 $("#conType").val("DIDTYPE02");
				 $("#conWidth").val("1080");
				 $("#conHeight").val("1920");
				 $("#conMid").val("0");	
				 $("#conTime").val("1");
			 }else if ($("#conScreen").val() ==  "W21" ){
				 $("#conType").val("DIDTYPE01");
				 $("#conWidth").val("1920");
				 $("#conHeight").val("1080");
				 $("#conMid").val("960");
				 $("#conTime").val("1");
			 }else if ($("#conScreen").val() ==  "H21" ){
				 $("#conType").val("DIDTYPE02");
				 $("#conWidth").val("1080");
				 $("#conHeight").val("1920");
				 $("#conMid").val("960");
				 $("#conTime").val("1");
			 }
			 
		 }
         function preView(){
        	 
         }
         function check_form(){
        	 if (any_empt_line_id("conNm", "콘텐츠명를 입력 하지 않았습니다.") == false) return;
   		     if (any_empt_line_id("conType", "콘텐츠타입을 선택 하지 않았습니다.") == false) return;   		  
   	         document.regist.submit();  
         }
         function del_form(){
   		  document.regist.action = "/backoffice/sub/conManage/conMutiDel.do";
   		  document.regist.encoding = "application/x-www-form-urlencoded"; 
   		  document.regist.submit();	  
   	  }	  
      </script>
</body>
</html>		