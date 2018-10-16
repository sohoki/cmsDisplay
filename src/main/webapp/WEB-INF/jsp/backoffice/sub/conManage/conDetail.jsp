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
	<title>이마트</title>
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/paragraph.css">
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>	
	<script src="/js/popup.js"></script>
	<script src="/js/leftMenu.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/conUpdate.do">						
<form:hidden path="conSeq" id="conSeq"/>
<form:hidden path="mode" id="mode"/>
<form:hidden path="pageIndex" />
<form:hidden path="pageSize" />
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />	
<form:hidden path="menuGubun" id="menuGubun" />
        
        
<c:import url="/backoffice/inc/emart_header.do" />
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
							<li class="active"><a href="/backoffice/sub/conManage/conList.do" class="schedule">그룹&화면 매칭</a></li>
							<div class="clear"></div>
					    </ul>
			            <div class="con">			
				          <div class="tableBox">		
					          <div class="con_title whiteBox" >
					          <div class="searchBox">
					          <!-- 내용 넣을 자리 시작 -->
					          <a href="javascript:listPage('regist','/backoffice/sub/basicManage/centerList.do' )" class="yellowBtn">목록</a> 
							  <a href="javascript:check_form()" class="grayBtn" id="btnUpdate">등록</a>
							  <c:if test = "${regist.mode != 'Ins' }"> 
							  <a href="javascript:del_form()" class="grayBtn">삭제</a>
							  </c:if>
							  </div>
							  
							  <table>
								<tbody>
									<tr>
										<th>콘텐츠명</th>
										<td><form:input path="conNm" id="conNm" title="콘텐츠명" size="15"/>
										</td>
										<th>콘텐츠타입</th>
										<td>
											<form:select path="conType" id="conType" title="전문구분">
										         <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${selectConType}" itemValue="code" itemLabel="codeNm"/>
										   </form:select>							   
										</td>
										<th>파일</th>
										<td>
										<input type="file"  name="conFile" id="conFile" size="5" />							
										</td>							
									</tr>
									<tr>
										<th>재생형식</th>
										<td>
											<form:select path="conPlayType" id="conPlayType" title="전문구분" onChange="javascript:View_Seq()">
										         <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${selectPlayType}" itemValue="code" itemLabel="codeNm"/>
										   </form:select>
										</td>
										<th>콘텐츠<br>Time Interval</th>
										<td class="smallBox">
										<form:input path="conTimeInterval" id="conTimeInterval" title="콘텐츠시간" size="15"  numberonly="true" />분</td>
										<th>섬네일 이미지</th>
										<td class="middleBox"><input type="file"  name="conThumbnail" id="conThumbnail" size="5" /></td>							
									</tr>						
									<tr>
										<th>Text 문구</th>
										<td colspan="5"><form:input path="conText" id="conText" title="Text문구" size="30" /></td>					
									</tr>
									<tr>
										<th>사용여부</th>
										<td>
										<input type="radio" name="conUseYn" value="Y" <c:if test="${regist.conUseYn == 'Y' }"> checked </c:if> />사용<br/>
										<input type="radio" name="conUseYn" value="N" <c:if test="${regist.conUseYn == 'N' }"> checked </c:if> />사용안함
										</td>
										<th>다음 화면</th>
										<td>
											<form:select path="conNextConSeq" id="conNextConSeq" title="전문구분">
											 <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${selectNextCon}" itemValue="conSeq" itemLabel="conNm"/>
										   </form:select>
										</td>
										<th>상세</th>
										<td class="smiddleBox">
										Width :  <form:input path="conWidth" id="conWidth" title="Width" size="5"  numberonly="true" /> <br/>
										Height : <form:input path="conHeight" id="conHeight" title="Height" size="5"  numberonly="true" />
										</td>							
									</tr>														
								</tbody>
							</table>
							  
					             
					          <!-- 내용 넣을 자리 끝 부분 -->
				              </div>
				              
				              
			              </div> 
			              
		               </div>
					</div>
				</div>
		  </div>
   <div class="clear"></div>
</div>		
    
    </form:form>
    
	<script type="text/javascript">
		$(document).ready(function() {
			if (  "${status}" != "" ){
				if ("${status}" == "SUCCESS" ){
					alert("정상처리 되었습니다");				
				}else  {
					alert("처리 도중 문제가 발생 하였습니다.");				
				}			
				location.href="/backoffice/sub/conManage/conList.do";	
			}					
		    if ($("#mode").val() == "Ins"){   	       		 		
		 		$("#btnUpdate").text("등록");
		    }	else {		    	
		    	$("#btnUpdate").text("수정");
		    }
		});	  	  
	  function check_form(){
		  if (any_empt_line_id("conNm", "콘텐츠명를 입력 하지 않았습니다.") == false) return;
		  if (any_empt_line_id("conType", "콘텐츠타입을 선택 하지 않았습니다.") == false) return;
		  document.regist.encoding = "multipart/form-data";
	      document.regist.submit();  
	  }
	  //센터 삭제 
	  function del_form(){
		  if (confirm("삭제 하시겠습니까?") == true){
			  document.regist.action = "/backoffice/sub/conManage/conDel.do";
			  document.regist.encoding = "application/x-www-form-urlencoded"; 
			  document.regist.submit();  
		  }		  	  
	  }	  
	  function View_Seq(){
		  if ($("#conPlayType").val() == "PLAY02"){
			  apiExecute(
						"POST", 
						"/backoffice/sub/conManage/conNextSeq.do",
						{
							conSeq : $("#conSeq").val()
						},
						null,				
						function(result) {				
							if (result.conNextSeqCombo != null) {
								$("#conNextConSeq").empty();
								$("#conNextConSeq").append("<option value=''>--선택하세요--</option>");						
								for (var i=0; i<result.conNextSeqCombo.length; i++) {
									var obj = result.conNextSeqCombo[i];
									$("<option value='"+ obj.conSeq +"'>"+ obj.conSeq +"</option>");
								}						
							}							
						},
						null,
						null
					);		  
			  
		  }
		  
	  }
	</script>
</body>
</html>		