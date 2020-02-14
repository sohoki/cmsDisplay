<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>DID운영관리</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/reset.css" rel="stylesheet" type="text/css" >
	
	<!-- popup -->
	<link rel="stylesheet" href="/new/css/needpopup.css">
	
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
	<script>
	function loadingStart(){
	   	var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
	   	$("#backgroundgif").html(loadingimg);
	}
	function loadingFinish(){
		$("#backgroundgif").html("");
	}
	</script>
	<style>
		#wrap { min-height :1080px;}
	</style>
</head>
<body>
<span id="backgroundgif"></span>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodBasicDetail.do">
<form:hidden path="pageIndex" id="pageIndex"/>
<form:hidden path="basicCode" id="basicCode"/>	
<input type="hidden" id="delBasciSeq" name="delBasciSeq" />

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
						<li class="active"><a href="/backoffice/sub/brodManage/brodBasic.do" class="playMedia">기본음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodPlayInfo.do" class="playShedule">기본음원재생현황</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentList.do" class="playContents">음원콘텐츠관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentPlayList.do" class="playContents">스케줄음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playShedule.do" class="playShedule">음원콘텐츠배포</a></li>
						<li><a href="/backoffice/sub/brodManage/playSheduleStatus.do" class="playShedule">콘텐츠배포현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">					
						<!--콘텐츠스케줄-->
						
						<table>
							<tbody style="background-color:#fff;">
								<tr>
									<th>기본음원명</th>
									<td>${regist.basicGroupNm }</td>
									<th>정보관리</th>
									<td><a class="blueBtn" href="javascript:conView('M')">음원 관리</a></td>								   
									<td><a class="blueBtn" href="javascript:conView('C')">지점 관리</a></td>
									<td><a class="blueBtn" href="javascript:conView('S')">배포 현황</a></td>								   
									<td><a href="javascript:send_form()" class="yellowBtn">배포하기</a></td>
								</tr>
							</tbody>
					    </table>
						
						
						<div class="con_sub">
						    <!--  음원 관련  div 시작 -->
							<div class="groupCon whiteBox groupTable" id="view_left01_1">
								<div class="innerBox">
									<div class="searchBox">
										<span>총 : ${totalCnt }건</span>
										<select name="pageUnit" id="pageUnit">								
											<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
											<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
											<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
											<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
										</select>	
										<select id="searchCondition" name="searchCondition" class="blan">
											<option value="orignlFileNm" <c:if test="${searchVO.searchCondition == 'orignlFileNm' }"> selected="selected" </c:if>>파일명</option>
											<option value="atchFileId" <c:if test="${searchVO.searchCondition == 'atchFileId' }"> selected="selected" </c:if>>파일코드</option>
										</select>
										<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
										<a href="javascript:search_form()" class="yellowBtn">검색</a>	
										<a href="javascript:basicDayInput('','')" class="yellowBtn" style="float:right;">선택추가</a>
									</div>	
									<!--테이블시작-->
									<table id="leftInfo">
										<thead>											
											<tr>
												<th>콘텐츠명</th>
												<th>재생시간</th>
												<!-- <th>송출순서</th> -->
												<th>송출추가</th>
												<th><input type="checkbox" name="L_checkAll" id="L_checkAll" onChange="javascript:allCheck('L');"></th>
											</tr>
										</thead>										
										<tbody>
										    <c:forEach items="${resultList}" var="conFile" varStatus="status">
											<tr class="selectTable" id="${conFile.atchFileId}">
												<td>${conFile.orignlFileNm}</td>
												<td>${conFile.fileThumnail}</td>
												<td style="display:none;">
												  <% 
													  String[] orderLsts = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
												  %> 
												  <select id ="order_${conFile.atchFileId}">
												     <option value="1">1</option>
													    <c:forEach var="orderLst" items="<%=orderLsts%>">
													      <option value='<c:out value="${orderLst}" />' ><c:out value="${orderLst}" /></option>
													    </c:forEach>							   													 
												  </select>
												</td>
												<!-- -->
												<td><a class="blueBtn" href="javascript:basicDayInput('${conFile.atchFileId}','R')">추가</a></td>
												<td><input type="checkbox" name="ck_fileId" value="${conFile.atchFileId}" ></td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="pageFooter" id="basciPaging">
										<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />	
										<div class="clear"></div>	
									</div>								
								</div>								
							</div>
							<div class="beetCon groupTable" id="view_left01_2">				
							</div>
							<div class="groupCon whiteBox groupTable" id="view_left01_3">
								<div class="innerBox">							
									<!--테이블시작-->
									<div class="searchBox">
										<a href="javascript:right_del()" class="brownBtn" style="float:right; margin-bottom:20px;">선택제외</a>
									</div>
									<table id="rightInfo">
										<!--테이블상단-->
										<thead>
											<!--줄시작-->
											<tr>
												<th>콘텐츠명</th>
												<th>시작일</th>
												<th>종료일</th>
												<th>송출제외</th>
												<th><input type="checkbox" name="checkAll" id="checkAll" onChange="javascript:allCheck('R');"></th>	
											</tr>
										</thead>
										<tbody>
										  <c:forEach items="${resultListBasic}" var="conFile" varStatus="status">
										    <tr>
												<td>${conFile.orignlFileNm}_${conFile.basicStartDay}</td>
												<!-- <td>${conFile.basicOrder}</td> -->
												<td>${conFile.basicStartDay}</td>
												<td>${conFile.basicEndDay}</td>
												<td><a class="redBtn" onClick="javascript:basicFileIn('${conFile.basicSeq}','L')">제외</a></td>
												<td><input type="checkbox" name="ck_basicSeq" id="ck_${conFile.basicSeq}" value="${conFile.basicSeq}"></td>
										    </tr>
										  </c:forEach>
										</tbody>										
									</table>	
								</div>
											
							</div>
                            
                            <!--  음원 관련  div 끝 -->
                            
                            <!--  센터 관련  div -->
                            <div class="con_title whiteBox " id="view_02" style="display:none" >
                               <table id="basicCenterInfo">	
                                 <thead>
                                   <tr>
                                     <th colspan='9' style="text-align:left">
                                       <select id="centerGubun" name="centerGubun" onChange="javascript:basciCenterList();">
                                          <option value="">선택</option>
                                          <option value="BRANCH01">음원</option>
                                          <option value="BRANCH02">일렉</option>
                                       </select>                                     
                                     </th>                                     
                                     <th></th>
                                     <th></th>
                                     <th><a href="javascript:basicUpdate()" id="btnUpdate">전체지점적용</a></th>
                                   </tr>
                                 </thead>
                                 <tbody>
                                 
                                 </tbody>		
                               </table>
                            </div>
                            <div class="con_title whiteBox " id="view_03" style="display:none" >
                               <div class="searchBox">
										<span id="schCnt"></span>
										<select name="schPageSize" id="schPageSize">								
											<option value="10">10개씩 보기</option>
											<option value="20">20개</option>
											<option value="30">30개</option>
											<option value="40">40개</option>
											<option value="50">50개</option>							
										</select>	
										<select id="schCreateCheck" name="schCreateCheck" class="blan">
											<option value="">배포선택</option>
											<option value="E">미배포</option>
											<option value="Y">배포완료</option>
										</select>
										<select id="schDidDownCheck" name="schCreateCheck" class="blan">
											<option value="">다운선택</option>
											<option value="Y">다운완료</option>
											<option value="N">미완료</option>
										</select>
										<a href="javascript:ajaxPageChange(1)" class="yellowBtn">검색</a>	
							   </div>
                               <table id="basicScheduleInfo">	
                                 <thead>
                                   <tr>
                                     <th>지점명</th><th>기본음원</th><th>배포여부</th><th>배포생성일자</th><th>다운로드여부</th><th>다운로드일자</th>
                                   </tr>
                                 </thead>
                                 <tbody>
                                    
                                 </tbody>		
                               </table>
                               <div class="pageFooter" id="schedulePaging">
									<div class="clear"></div>	
							   </div>	
                            </div>
                            <!--  센터 관련  div 끝 부분-->
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
	
	<input type="hidden" name="schPageIndex" id="schPageIndex" />
	<input type="hidden" name="schCenterId" id="schCenterId" />
	<input type="hidden" name="basicAtchFileId" id="basicAtchFileId" />
	<input type="hidden" name="code" id="code" />		
 </form:form>
 <script type="text/javascript">
	 $(document).ready(function() {
		 needPopup.config.custom = {
			'removerPlace': 'outside',
			'closeOnOutside': true,
			onShow: function() {},
			onHide: function() {}
		};
		needPopup.init();
		//시작시 넣을 파일
 		conView("M");
		$("#schPageSize").val("10");
		$("#schPageIndex").val("1");
		$("#schCreateCheck").val("");	//20191003 JDH	
		$("#schDidDownCheck").val("");
		$("#centerGubun").val("BRANCH01");		
		basciCenterList(); 			
		
	 });
	 function scheduleList(){
		 apiExecute(
				"POST", 
				"/backoffice/sub/brodManage/brodBasicScheduleList.do",
				{
					schPageIndex : $("#schPageIndex").val(),
					schPageSize : $("#schPageSize").val(),
					schCenterId : $("#schCenterId").val(),
					basicCode : $("#basicCode").val(),
					schCreateCheck : $("#schCreateCheck").val(),
					schDidDownCheck : $("#schDidDownCheck").val()						
				},
				null,				
				function(result) {
					
					if (result.resultMap.schList != null){
						$("#basicScheduleInfo").find('tbody').empty();
						$("#schCnt").html("총 : " + result.resultMap.schTotCnt +" 건"); //20191003 JDH
						timeHtml = "";
						for (var i=0; i< result.resultMap.schList.length; i++) {
							
							var obj = result.resultMap.schList[i];
							
							timeHtml += "<tr style='width:100%'><td style='text-align:center'>"+ obj.centerNm+"</td>";
							timeHtml += "<td style='text-align:center'>"+ obj.basicGroupNm+"</td>";
							if (obj.createCheck == "E"){
								timeHtml += "<td style='text-align:center'>미배포</td>";
								timeHtml += "<td style='text-align:center'></td>";
							}else{
								timeHtml += "<td style='text-align:center'>배포완료</td>";
								timeHtml += "<td style='text-align:center'>"+ obj.createRegdate+"</td>";
							}
							
							if (obj.didDowncheck == "N"){
								timeHtml += "<td style='text-align:center'>&nbsp;</td>";
								timeHtml += "<td style='text-align:center'>&nbsp;</td>";
							}else {
								timeHtml += "<td style='text-align:center'>다운로드완료</td>";
								timeHtml += "<td style='text-align:center'>"+ obj.didDownloaddate +" </td>";
							}						
							timeHtml += "</tr>";
						}
						$("#basicScheduleInfo").find('tbody').append(timeHtml);
						var pageObj = result.resultMap.paging;						
						var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, pageObj.totalPageCount, pageObj.pageSize, "ajaxPageChange");
						$("#schedulePaging").html(pageHtml+"<div class='clear'></div>");						
					}else {
						alert("처리 도중 문제가 발생 하였습니다.");
					}							
				},
				null,
				null
		 );
	 }
	 function ajaxPageChange(code){
		 $("#schPageIndex").val(code);
		 scheduleList();		 
	 }
	 
	 function search_form(){
		$("form[name=regist]").submit();
	    $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodBasicDetail.do").submit();
	 }
	 function left_del(startDay,endDay){
		 var cnt = $("input[name=ck_fileId]:checkbox:checked").length;
		 var del_atch = "";
		 if (cnt < 1) {
				alert("하나 이상의 체크를 선택 하셔야 합니다");
		 } else {
			   
			   for (var i = 0; i < document.getElementsByName("ck_fileId").length; i++) {
					if (document.getElementsByName("ck_fileId")[i].checked == true) {						
						del_atch = del_atch + "," + document.getElementsByName("ck_fileId")[i].value;						
					}
				}
			   $("#delBasciSeq").val(del_atch);
			   //ajax  하기
			   loadingStart();
			   apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodBasicFileCheckUpdate.do",
					{
						basicCode : $("#basicCode").val(),
						delBasciSeq : $("#delBasciSeq").val().substring(1),
						basicStartDay : startDay,
						basicEndDay : endDay,
						pageIndex : $("#pageIndex").val(),
						pageSize : $("#pageUnit").val(),
						searchCondition : $("#searchCondition").val() ,
						searchKeyword :  $("#searchKeyword").val()
					},
					null,				
					function(result) {	
						loadingFinish();
						if (result != null){
							basciSchedule(result);
							alter("등록 성공");
						}else {
							alert("처리 도중 문제가 발생 하였습니다.");
						}					
					},
					null,
					null
				);
			   			   			   
			   $("#delBasciSeq").val("");			   
			   $("#L_checkAll").prop("checked", false);
			   
		}
	 }
	 //배포 파일 새로 업데이트 
	 function send_form(){
		 loadingStart();
		 apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodBasicScheduleUpdateReset.do",
					{
						basicCode : $("#basicCode").val(),
						createCheck : "E",
						preCreateCheck : "Y"
					},
					null,				
					function(result) {		
						loadingFinish();
						if (result == "T") {
							alert("정상적으로 배포 되었습니다.");	
							scheduleList();
						}else{
							alert("데이터 처리 도중 문제가 발생 하였습니다.");							
						}							
					},
					null,
					null
				);		 
	 }
	 //센터 변경 관련 작업 
	 /* function schUpdate(){
		 apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodBasicCenterUpdateS.do",
					{
						basicCode : $("#basicCode").val(),
						checkValue : "Y",
						CenterSeq : del_atch
					},
					null,				
					function(result) {		
						
						if (result == "S") {
							alert("정상적으로 적용 되었습니다.");
							$("#btnUpdate").html("<a href='javascript:schUpdate()'>배포적용</a>");
							basciCenterList();
						}else{
							alert("데이터 처리 도중 문제가 발생 하였습니다.");
							basciCenterList();
						}							
					},
					null,
					null
				);	
	 } */
	 //전체 적용 
	 function basicUpdate(){
		 
		    for (var i =0; i< document.getElementsByName("ck_center").length; i++){    		
    			 document.getElementsByName("ck_center")[i].checked = true;    			 
    	    } 		 
		    var cnt = $("input[name=ck_center]:checkbox:checked").length;
			var del_atch = "";			
			if (cnt < 1) {
				alert("하나 이상의 체크를 선택 하셔야 합니다");
			} else {

				for (var i = 0; i < document.getElementsByName("ck_center").length; i++) {
					if (document.getElementsByName("ck_center")[i].checked == true) {						
						del_atch = del_atch + "," + document.getElementsByName("ck_center")[i].value;						
					}
				}				
				loadingStart();
				  apiExecute(
							"POST", 
							"/backoffice/sub/brodManage/brodBasicCenterUpdate.do",
							{
								basicCode : $("#basicCode").val(),
								checkValue : "Y",
								CenterSeq : del_atch
							},
							null,				
							function(result) {		
								loadingFinish();
								if (result == "S") {
									alert("정상적으로 적용 되었습니다.");
									//$("#btnUpdate").html("<a href='javascript:schUpdate()'>배포적용</a>");
									basciCenterList();
								}else{
									alert("데이터 처리 도중 문제가 발생 하였습니다.");
									basciCenterList();
								}							
							},
							null,
							null
						);							
		   }
	 }
	 function chang_basicCode(){
		 
	 }
	 function conView(code){
		 	if (code == "M"){
		 		$("#view_left01_1").css("display", "");
		 		$("#view_left01_2").css("display", "");
		 		$("#view_left01_3").css("display", "");		 		
		 		$("#view_02").css("display", "none");
		 		$("#view_03").css("display", "none");
		 	}else if (code == "C"){
		 		$("#view_left01_1").css("display", "none");
		 		$("#view_left01_2").css("display", "none");
		 		$("#view_left01_3").css("display", "none");		 		
		 		$("#view_02").css("display", "");
		 		$("#view_03").css("display", "none");
		 	}else {
		 		$("#view_left01_1").css("display", "none");
		 		$("#view_left01_2").css("display", "none");
		 		$("#view_left01_3").css("display", "none");		 		
		 		$("#view_02").css("display", "none");
		 		$("#view_03").css("display", "");
		 		scheduleList();
		 	}
	 }
	 function basciCenterList(){
		 if (any_empt_line_id("centerGubun", "검색할 지점 구분값을 선택 하지 않았습니다.") == false) return;
		 loadingStart();
		 apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodBasicCenterList.do",
					{
						centerGubun : $("#centerGubun").val()
					},
					null,				
					function(result) {		
						loadingFinish();
						if (result.centerInfo != null) {
							
							
							$("#basicCenterInfo").find('tbody').empty();
							var timeHtml = "<tr>";
							var j = 0;
							for (var i=0; i<result.centerInfo.length; i++) {
								var obj = result.centerInfo[i];
								if(obj.centerNm.indexOf("[전문일렉]") == -1){
								if (j > 5 ){
									j = 0;
									timeHtml += "</tr><tr>";		
								}
								
								timeHtml += "<td>"+ obj.centerNm;
								if (obj.basicGroupNm != null){
									timeHtml += ":<Br /> [기초음원]" + 	obj.basicGroupNm;
								}
								timeHtml += "</td>";
								
								if (obj.basicCode == $("#basicCode").val()){
									timeHtml += "<td><input type='checkbox' name='ck_center' value='"+obj.centerId+"' id='ck_"+obj.centerId+"' onClick='javascript:chang_basicCode(&#39;"+obj.centerId+"&#39;)' checked> </td>";	
								} else {
									timeHtml += "<td><input type='checkbox' name='ck_center' value='"+obj.centerId+"' id='ck_"+obj.centerId+"' onClick='javascript:chang_basicCode(&#39;"+obj.centerId+"&#39;)'> </td>";
								}
		                        j = j +1;	
								}
							}							
							timeHtml += "</tr>";
							$("#basicCenterInfo").find('tbody').append(timeHtml);		  
						}else{
							alert("데이터 처리 도중 문제가 발생 하였습니다.");								
						}							
					},
					null,
					null
				);	
	 }
	 //기초 음원 넣기 
	 function chang_basicCode(code){
		 var ck_value = "";
		 if ($("#ck_"+code).prop("checked")){
			 ck_value = "Y";
		 }else {
			 ck_value = "N";
		 }		 
		 loadingStart();
		 apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodBasicCenterUpdate.do",
					{
						basicCode : $("#basicCode").val(),
						CenterSeq : code,
						checkValue : ck_value
					},
					null,				
					function(result) {
						loadingFinish();
						if (result == "S") {
							alert("정상적으로 기본음원이 등록되었습니다.");									  
						}else{
							alert("데이터 처리 도중 문제가 발생 하였습니다.");	
							if (ck_value == "Y"){
								$("#ck_"+code).prop("checked", false);
							}else {
								$("#ck_"+code).prop("checked", true);
							}
						}							
					},
					null,
					null
				);			 		
	 }
     function linkPage(pageNo) {    	
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	 }
	 function allCheck(code){
		 if (code == "L"){
			 //좌측 체크 박스
			 if ($("#L_checkAll").prop("checked")) {
					$("input[name=ck_fileId]").prop("checked", true);
			 } else {
					$("input[name=ck_fileId]").prop("checked", false);
			 }
		 }else {
			 //우측 체크 박스 
			 if ($("#checkAll").prop("checked")) {
					$("input[name=ck_basicSeq]").prop("checked", true);
			 } else {
					$("input[name=ck_basicSeq]").prop("checked", false);
			 }	 
		 }
		 
	 }
	 function basciSchedule(result){
		 var timeHtml = "";
			
			if (result != null){
				if (result.resultMap.schList != null){
					$("#leftInfo").find('tbody').empty();
					for (var i=0; i< result.resultMap.schList.length; i++) {							
						var obj = result.resultMap.schList[i];
						timeHtml += "<tr class='selectTable' id='"+obj.atchFileId+"''><td>"+ obj.orignlFileNm+"</td>";							
						timeHtml += "<td>"+ obj.fileThumnail+"</td>";
						timeHtml += "<td style='display:none;'><select id='order_"+obj.atchFileId+"'><option value='1'>1</option>";
						for (var a=1; a < 21; a++){
							timeHtml += "<option value='"+a+"'>"+a+"</option>";	
						}
						timeHtml += "</select></td>";
						timeHtml += "<td style='text-align:center'><a class='blueBtn' href='javascript:basicDayInput(&#39;"+obj.atchFileId+"&#39;,&#39;R&#39;)'>추가</a></td>";
						timeHtml += "<td><input type='checkbox' name='ck_fileId' value='"+ obj.atchFileId+"' ></td>";
						timeHtml += "</tr>";
					}						
					$("#leftInfo").find('tbody').append(timeHtml);
				}
				if (result.resultMap.atchFileLst != null){								
					timeHtml = "";
					$("#rightInfo").find('tbody').empty();
					for (var i=0; i<result.resultMap.atchFileLst.length; i++) {
						var obj = result.resultMap.atchFileLst[i];								
						timeHtml += "<tr style='width:100%'><td>"+ obj.orignlFileNm+"_"+ obj.basicStartDay+"</td>";
						timeHtml += "<td>"+ obj.basicStartDay+"</td>";
						timeHtml += "<td>"+ obj.basicEndDay+"</td>";
						timeHtml += "<td style='display:none;'><select id='order_"+obj.atchFileId+"'><option value='1'>1</option>";
						// timeHtml += "<td style='text-align:center'>"+ obj.basicOrder+"</td>";
						timeHtml += "<td><a class='redBtn' onClick='javascript:basicFileIn(&#39;"+obj.basicSeq+"&#39;,&#39;L&#39;)'>제외</a></td>";
						timeHtml += "<td><input type='checkbox' name='ck_basicSeq' id='ck_"+obj.basicSeq+"' value='"+obj.basicSeq+"'></td>";
						timeHtml += "</tr>";								
					}
					$("#rightInfo").find('tbody').append(timeHtml);
				}
				if (result.resultMap.paging != null){
					var pageObj = result.resultMap.paging;		
					var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, pageObj.totalPageCount, pageObj.pageSize, "linkPage");
					$("#basciPaging").html(pageHtml+"<div class='clear'></div>");							
				}
			}
	 }
	 function right_del(){
		    var cnt = $("input[name=ck_basicSeq]:checkbox:checked").length;
			var del_atch = "";			
			if (cnt < 1) {
				alert("하나 이상의 체크를 선택 하셔야 합니다");
			} else {

				for (var i = 0; i < document.getElementsByName("ck_basicSeq").length; i++) {
					if (document.getElementsByName("ck_basicSeq")[i].checked == true) {						
						del_atch = del_atch + "," + document.getElementsByName("ck_basicSeq")[i].value;						
					}
				}
				
				$("#delBasciSeq").val(del_atch);
				
				loadingStart();
				 apiExecute(
							"POST", 
							"/backoffice/sub/brodManage/brodBasicFileAllDel.do",
							{
								basicCode : $("#basicCode").val(),
								delBasciSeq : $("#delBasciSeq").val(),
								pageIndex : $("#pageIndex").val(),
								pageSize : $("#pageUnit").val(), 
								searchCondition : $("#searchCondition").val() ,
								searchKeyword :  $("#searchKeyword").val()
							},
							null,				
							function(result) {
								loadingFinish();
								if (result != null){
									basciSchedule(result);
								}else {
									alert("처리 도중 문제가 발생 하였습니다.");
								}						
							},
							null,
							null
						);	
								
						$("#delBasciSeq").val("");
		   }
		   $("#checkAll").prop("checked", false);
		 
	 }
	 function basicFileIn(code,code1,startDay,endDay){
		 
		 if (code1 == "R"){
			 if ($("#order_"+code).val() == ""){
				 alert("정렬 순서를 선택 하지 않았습니다");
				 return ;
			 }
			 params = "basicOrder="+$("#order_"+code).val() + "&"+ $("#basicCode").serialize() +"&atchFileId="+code+
					  "&fileGubun=R&basicStartDay="+startDay+"&basicEndDay="+endDay;	 
		 }else {
			 params = $("#basicCode").serialize() +"&basicSeq="+code+"&fileGubun=L";
		 }
		 params = params + "&"+ $("#pageIndex").serialize()+"&" + $("#pageUnit").serialize()+"&" + $("#searchCondition").serialize()+"&" + $("#searchKeyword").serialize() ;
		 console.log(params);
		 loadingStart();
		 $.ajax({
			url : '/backoffice/sub/brodManage/brodBasicFileUpdate.do',
			type : 'POST',
			data : params,
			dataType : 'json',
			success : function(result) {
				loadingFinish();
				if (result != null) {
					basciSchedule(result);
					alert("등록 성공");
				}else {
					alert("처리 도중 문제가 발생 하였습니다.");
				}
			},
			error : function(e) {
				loadingFinish();
				console.log("falit");
				// console.log(e);
			}
		});	 	 
	 }
	 function basicDayInput(code, code1){
		 var nWidth = "550";
		 var nHeight = "180";
		   
		 // 듀얼 모니터 고려한 윈도우 띄우기
		 var curX = window.screenLeft;
		 var curY = window.screenTop;    
		 var curWidth = document.body.clientWidth;
		 var curHeight = document.body.clientHeight;
		   
		 var nLeft = curX + (curWidth / 2) - (nWidth / 2);
		 var nTop = curY + (curHeight / 2) - (nHeight / 2);

		 var strOption = "";
		 strOption += "left=" + nLeft + "px,";
		 strOption += "top=" + nTop + "px,";
		 strOption += "width=" + nWidth + "px,";
		 strOption += "height=" + nHeight + "px,";
		 strOption += "toolbar=no,menubar=no,location=no,";
		 strOption += "resizable=yes,status=yes";
		 
		 var url = "/backoffice/sub/brodManage/basicDayInput.do?atchFileId="+code+"&mode="+code1;
		 
		 window.open(url,"basicDayInput", strOption);
		 /* var url = "/backoffice/sub/brodManage/basicDayInput.do?atchFileId="+code+"&mode="+code1;
		 var left = (document.body.offsetWidth / 2) - (500 / 2);
		 var top = (document.body.offsetHeight / 2) - (180 / 2);
		 window.open(url,"basicDayInput", 'width=500,height=180,top=200,left=' + left +',top=' + top+ ',scrollbars=no'); */
		 
	}
</script>

 
 
 <!-- 191008 JDH -->
	<script src="/new/js/needpopup.js"></script> 
	<script src="/new/js/datepipck.js"></script>
	<script src="/new/js/jquery-ui.js"></script>
</body>
</html>