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
    <title>DID운영관리</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/reset.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/needpopup.min.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
	<style>
		.play table{margin:0; border:0;border-right:1px solid #e1e1e1;border-left:1px solid #e1e1e1; }
		#spTitle{color:#fff; font-weight: bold;}
	</style>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
		<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodContentUpdate.do">
		<form:hidden path="mode" id="mode" />	
		<form:hidden path="pageUnit" id="pageUnit" />
		<form:hidden path="searchCondition" id="searchCondition" />
		<form:hidden path="searchKeyword" id="searchKeyword" />
		<form:hidden path="pageIndex" id="pageIndex" />
		<form:hidden path="brodCode" id="brodCode" />
		
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
						<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점 관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playContentList.do" class="playMedia">음원파일관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodBasic.do" class="playMedia">기본음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodPlayInfo.do" class="playShedule">기본음원재생현황</a></li>
						<li class="active"><a href="/backoffice/sub/brodManage/brodContentList.do" class="playContents">음원콘텐츠관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentPlayList.do" class="playContents">스케줄음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playShedule.do" class="playShedule">음원콘텐츠배포</a></li>
						<li><a href="/backoffice/sub/brodManage/playSheduleStatus.do" class="playShedule">콘텐츠배포현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					
					<div class="con">
						<!--음원파일관리-->
						<div class="con_title whiteBox ">
						   <div class="searchBox">									
								<div class="footerBox">
								<a href="javascript:list_View()" class="yellowBtn">목록</a> 
								<a href="javascript:check_form()" class="grayBtn">수정</a>
								<a href="javascript:contentReg('Ins','0');" class="grayBtn">콘텐츠 등록</a>
								<a href="javascript:contentSpReg('Ins','0');" class="blueBtn">특정 방송 등록</a>
								<a href="javascript:contentCopy();" class="blueBtn">음원 콘텐츠 복사</a>
								<!-- 편성표 관련 내용 배치 적용 -->
								<a href="javascript:batchConfirm();" class="blueBtn">배치 적용</a>
								<!-- 편성표 관련 내용 배치 적용 -->
								<a href="javascript:contentConfirm();" class="blueBtn">편성표생성</a>
								<a href="javascript:contentList();" class="blueBtn">방송표보기</a>
								<div class="clear"></div>			
							    </div>
						   </div>
						   <table>
								<tbody>
									<tr>
										<th colspan="12">콘텐츠 정보</th>
									</tr>
									<tr>	
										<th>콘텐츠코드</th>
										<td>${regist.brodCode }</td>
										<th>콘텐츠명</th>
										<td>${regist.brodName }</td>
										<th>이벤트유무</th>
										<td>
										<c:choose>
										   <c:when test="${regist.secGubun eq 'SECGUBUN01' }">일반스케줄</c:when>
										   <c:otherwise>이벤트스케줄</c:otherwise>
										</c:choose>
										<th>사용유무</th>
										<td>
										<c:choose>
										   <c:when test="${regist.brodUseYn eq 'Y' }">사용</c:when>
										   <c:otherwise>사용 안함</c:otherwise>
										</c:choose>	
										
										</td>
										<th>반복재생간격</th>
										<td>${regist.codeNm }</td>
										<th>기본음원</th>
										<td><a href="#" class="yellowBtn" id="dialog" data-needpopup-show="#small-popup" onClick="javascript:js_basicView('${fn:trim(regist.basicFileId)}')">리스트 미리보기</a></td>										
									</tr>
									<tr>		
									
								</tbody>
							</table>
							<table class="play play_cont">
								<thead>
									<tr>
									    <c:set var="codeDc" value="${regist.codeDc}"></c:set>
									    <%
									      for (int i =0; i < (Integer.parseInt(pageContext.getAttribute("codeDc").toString())/10) ; i++ ){						    	  
									    %>
										<th><%=i%>0분</th>
										<% } %>							
									</tr>
									<tr>
									    <c:set var="media" value="media${status.count }" ></c:set>
									    <%
									      for (int i =0; i < (Integer.parseInt(pageContext.getAttribute("codeDc").toString())/10) ; i++ ){						    	  
									    %>
										<td valign="top">
										 <span id="brodContent<%=i%>"></span>
										</td>
										<% } %>
									</tr>
								</thead>
			               </table>	
			               <table>
								<thead>
									<tr>							
										<th>콘텐츠ID</th>
										<th>콘텐츠명</th>
										<th>특정방송여부</th>
										<th>적용기간</th>
										<th>반복재생</th>							
										<th>삭제</th>				
									</tr>
								</thead>
								
								<c:set value="0000" var="timeCheck" />
								<c:set value="0" var="totalTime" />
								<c:forEach items="${brodAnniver}" var="brodAnnerInfo" varStatus="status">
								<c:choose>
								        <c:when test="${brodAnnerInfo.anniversaryTime == timeCheck }">
						                  <c:set value="${totalTime + brodAnnerInfo.playTime}" var="totalTime" />
						                </c:when>
						        		<c:otherwise>
						        		  <c:set value="${brodAnnerInfo.anniversaryTime}" var="timeCheck" />
						        		  <c:set value="${brodAnnerInfo.playTime}" var="totalTime" />
						        		</c:otherwise>					        							        						        
								</c:choose>
								<tr <c:if test="${totalTime > 600}" > style="background-color: #bbdefb;" </c:if>>
								  <td>${brodAnnerInfo.brodAnnSeq}
								 
								  </td>
								  <td><a href="javascript:contentSpReg('Edt','${brodAnnerInfo.brodAnnSeq}')">${brodAnnerInfo.anniverName}</a></td>
								  <td>${brodAnnerInfo.codeNm}</td>
								  <td>${brodAnnerInfo.anniverStartDay}~${brodAnnerInfo.anniverEndDay}</td>
								  <td>${brodAnnerInfo.anniversaryTime}
								      <c:if test="${not empty brodAnnerInfo.anniversaryStartTime  }" >
								                간격으로     ${brodAnnerInfo.anniversaryStartTime}분 마다 재생
								      </c:if>
								   </td>
								  <td><a href="javascript:del_anniver('${brodAnnerInfo.brodAnnSeq}')" >[삭제]</a></td>
								</tr>
								</c:forEach>
							</table>	
							<input type="hidden"  name="mediaCnt" id="mediaCnt" value="<%=(Integer.parseInt(pageContext.getAttribute("codeDc").toString())/10)%>" />
					    </div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
    </form:form>					
	<div id='small-popup' class="needpopup">
	   
	   <div class="contents">
			<div class="header">
				<h2>
					<span id="spTitle" style="color: #fff;font-size: 20px;">기본 음원 리스트</span>
					<br>
					<span id="subSpTitle" style='font-size:12px; color:#ccc;'></span>	
				</h2>
			</div>
			<div class="textT">
				<!--테이블시작-->	    
					<table id="basicList" style="color:#FFFFFF;">
						<!--내용시작-->
						<tbody class="text_left">
						</tbody>
					</table>				
			</div>
		</div>
	</div>					
	<script type="text/javascript" src="/js/needpopup.min.js"></script>					
					
		
    <script type="text/javascript">
	   $(document).ready(function() {     
		    
			if ("${status}" != ""){
				if ("${status}" == "SUCCESS") {
					alert("정상 처리 되었습니다");    			    			
				}else{
					alert("작업 도중 문제가 발생 하였습니다.");
				}		
				 location.href = "/backoffice/sub/brodManage/brodContentList.do";	
			}
			//needPopup생성
			needPopup.config.custom = {
				'removerPlace': 'outside',
				'closeOnOutside': false,
				onShow: function() {},
				onHide: function() {}
			};
			needPopup.init();
			//여기 부분에 동적 데이터 넣기
			for (var i=0; i < $("#mediaCnt").val(); i++){
				spinHtml(i);					  
			}			
	   });    
	   function js_basicView(code){
			  
			  apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodBasicFileList.do",
						{
							basicCode : code
						},
						null,				
						function(result) {				
							if (result != null) {
								$("#basicList").find('tbody').empty();	
								if (result.resultMap != null){
									$("#subSpTitle").html("* 랜덤재생으로 리스트 순서는 무관합니다.");
									var timeHtml = "";
									for (var i=0; i< result.resultMap.length; i++) {
										var obj = result.resultMap[i];	
										timeHtml += "<tr><td>"+obj.orignlFileNm+"</td><td>"+obj.basicOrder+"</td><td>"+obj.useYn+"</td></tr>"
									}
									$("#basicList").find('tbody').append(timeHtml);								
								}else {
									alert("데이터를  연동중 문제가 발생 하였습니다.");
								}
							}							
						},
						null,
						null
			  );	
		  }
	  function contentSpReg(code, code1){
		  var url = "/backoffice/sub/brodManage/ContentSpReg.do?mode="+code+"&brodCode="+$("#brodCode").val()+"&brodAnnSeq="+code1;	      
	      window.open(url,"contentSpReg", 'width=800,height=550,top=100,left=650,scrollbars=auto');
	  }
	  function contentList(){
		  var url = "/backoffice/sub/brodManage/ContentBrodReport.do?brodCode="+$("#brodCode").val();	      
	      window.open(url,"contentReport", 'width=800,height=550,top=100,left=650,scrollbars=auto');
	  }
	  //배치 관련 변경 내용 적용 출후 변경 내용 버튼 부분 색부분 정리 하기 
	  function batchConfirm(){		  
			  apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodScheduleConfirm.do",
						{
							brodCode : $("#brodCode").val()
						},
						null,				
						function(result) {				
							if (result != null) {
								if (result == "O"){
									alert("배포 현황에 변경 내역이 정상적으로 저장 되었습니다.");
								}else {
									alert("배포 현황에 변경 내역처리중 연결된 지점이 없습니다.");
								}
							}							
						},
						null,
						null
					);	
	  }
	  function list_View(){
		  $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentList.do").submit();
	  }
	  function contentCopy(){
		  var url = "/backoffice/sub/brodManage/brodContentDetailCopy.do?brodCode="+$("#brodCode").val();	      
	      window.open(url,"contentSpReg", 'width=800,height=550,top=100,left=650,scrollbars=auto');
	  }
	  
	  function del_anniver(code){
		  apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/ContentSpRegDel.do",
					{
						brodAnnSeq : code						
					},
					null,				
					function(result) {				
						if (result != null && result != "F") {
							alert("정상적으로 삭제 되었습니다.");
							document.location.reload();
						}else {
							alert("삭제시 문제가 발생 하였습니다.");
						}						
					},
					null,
					null
				);	
	  }
	  //기념일 관련 내용 정리
	  function annverHtml(){
		  apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/timeContentLst.do",
					{
						brodCode : $("#brodCode").val(),
						timeInterval : code1
					},
					null,				
					function(result) {				
						if (result.contentList != null) {
							var timeHtml = "<table>"
							var totalTime = "0";						
							for (var i=0; i<result.contentList.length; i++) {
								var obj = result.contentList[i];
								timeHtml += "<tr><Td>"+ obj.orignlFileNm+"</a>&nbsp;&nbsp;재생시간:"+obj.fileThumnail+"<br>"+dayConvert(obj.contentStartDay)+"~"+dayConvert(obj.contentEndDay)+"</td><tr>";
								timeHtml += "<tr><Td><a href='javascript:delContent(&#39;"+obj.brodSeq +"&#39;,&#39;"+(parseInt(obj.intervalSection)/10)+"&#39;)' >[삭제]</a></td><tr>";
								totalTime = parseInt(totalTime) + parseInt(obj.playTime);
							}
							var min = parseInt(parseInt(totalTime)/60);
							var sec = parseInt(totalTime) - ( parseInt(min) * 60  );							
							timeHtml += "<tr><td>총재생시간:"+min+":"+sec+"<td></tr>";
							timeHtml += "</table>";
														
							$("#brodContent"+code).html(timeHtml);
							
						}							
					},
					null,
					null
				);	
		  
	  }
	  function spinHtml(code){
		  var code1 = "";
		  if (code != "0"){ 
			  code1 = code + "0"; 
		  }else {
			  code1 = code;
		  }
		  apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/timeContentLst.do",
					{
						brodCode : $("#brodCode").val(),
						timeInterval : code1
					},
					null,				
					function(result) {				
						if (result.contentList != null) {
							var timeHtml = "<table>"
							var totalTime = "0";						
							for (var i=0; i<result.contentList.length; i++) {
								var obj = result.contentList[i];
								timeHtml += "<tr><Td><a href='javascript:contentReg(&#39;Edt&#39;,&#39;"+ obj.brodSeq +"&#39;)'>"+ obj.orignlFileNm+"</a>&nbsp;&nbsp;재생시간:"+obj.fileThumnail+"<br>"+dayConvert(obj.contentStartDay)+"~"+dayConvert(obj.contentEndDay)+"</td><tr>";
								timeHtml += "<tr><Td><a href='javascript:delContent(&#39;"+obj.brodSeq +"&#39;,&#39;"+(parseInt(obj.intervalSection)/10)+"&#39;)' class='grayBtn'>삭제</a></td><tr>";
								totalTime = parseInt(totalTime) + parseInt(obj.playTime);
							}
							var min = parseInt(parseInt(totalTime)/60);
							var sec = parseInt(totalTime) - ( parseInt(min) * 60  );							
							timeHtml += "<tr><td style='background:#D2E1FF'>총재생시간:"+min+":"+sec+"<td></tr>";
							timeHtml += "</table>";
														
							$("#brodContent"+code).html(timeHtml);
							
						}							
					},
					null,
					null
				);	
	  }
	  function contentReg(code, code1){
		    var url = "/backoffice/sub/brodManage/ContentReg.do?mode="+code+"&&brodCode="+$("#brodCode").val()+"&brodSeq="+code1;	      
	        window.open(url,"contentReg", 'width=800,height=550,top=100,left=650,scrollbars=auto');
	  }
      function check_form(){
    	  $('#mode').val("Edt");
    	  $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentDetail.do").submit();      	 
      }
      
      function contentConfirm(){
    	  apiExecute(
  				"POST", 
  				"/backoffice/sub/brodManage/ContentBrodConfirm.do",
  				{
  					brodCode : $("#brodCode").val(),
  					centerId : null,
  					centerSearchDay : "20991231"
  				},
  				null,				
  				function(result) {				
  					if (result != null) {
  						if (result == "O"){
  							alert("정상적으로 입력 되었습니다.");
  						}else {
  							alert("작업 도중 문제가 발생 하였습니다.");
  						}
  					}							
  				},
  				null,
  				null
  			);	
      }
      function delContent(code, code1){
    	  apiExecute(
				"POST", 
				"/backoffice/sub/brodManage/brodContentDetailDel.do",
				{
					brodCode :$("#brodCode").val(),
					brodSeq : code
				},
				null,				
				function(result) {				
					if (result != null) {
					   if (result =="T"){
						   spinHtml(code1);
					   }else {
						   alert("삭제 도중 문제가 발생 하였습니다");
					   }	
					}							
				},
				null,
				null
		 );	
    	  
      }
     
    </script>
</body>
</html>		