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
	<link href="<c:url value='/'/>css/needpopup.min.css" rel="stylesheet" type="text/css" >
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
<input type="hidden" id="groupSeq" name="groupSeq" />
<input type="hidden" id="mode" name="mode" />

<input type="hidden" id="brodStartday" name="brodStartday" />
<input type="hidden" id="brodEndday" name="brodEndday" />


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
									<!-- <td><a class="blueBtn" href="javascript:conView('M')">음원 관리</a></td>	 -->
									<td><a href="#" onclick="fn_GrougView()" id="btn_GroupFile" class="blueBtn" style="float:right;">편성표 관리</a><td>
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
									<div class="searchBox" style="padding-bottom : 25px;">
										<%-- <span>총 : ${totalCnt }건</span>
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
										<a href="javascript:search_form()" class="yellowBtn">검색</a> --%>
											
										<a href="#" onclick="fn_GroupUpdate('Cpy','0')" id="btn_GroupCopy" data-needpopup-show="#small-popup-reg"  class="yellowBtn" style="float:right; margin-left:10px;">기초음원복사</a>
										<a href="#" onclick="fn_GroupUpdate('Del','0')" id="btn_GroupDel" class="yellowBtn" style="float:right; margin-left:10px;">삭제</a>
										<a href="#" onclick="fn_GroupUpdate('Ins','0')" id="btn_Group" data-needpopup-show="#small-popup-reg" class="yellowBtn" style="float:right; margin-left:10px;">등록</a>
										<a href="#" onclick="basicDayInput('','R')" id="btn_GroupFileCh" class="yellowBtn" style="float:right;display:none;">선택추가</a>
										
									</div>	
									<!--테이블시작-->
									<table id="leftInfo_group">
										<thead>											
											<tr>
												<th>제목</th>
												<th>재생시간</th>
												<th>재생파일수</th>
												<th>상세보기</th>
												<th><input type="checkbox" name="L_checkGroupAll" id="L_checkGroupAll" onChange="javascript:allCheck('G');"></th>
											</tr>
										</thead>										
										<tbody>
										    <c:forEach items="${resultList}" var="groupInfo" varStatus="status">
											<tr class="selectTable" id="${groupInfo.groupSeq}">
												<td><a href="#" onclick="fn_GroupUpdate('Edt','${groupInfo.groupSeq}')" data-needpopup-show="#small-popup-reg" >${groupInfo.groupTitle}</a></td>
												<td>${groupInfo.groupStarttime}~${groupInfo.groupEndtime}</td>
												<td>${groupInfo.fileCnt}</td>
												<td><a class="blueBtn" href="#" onclick="groupFileView('${groupInfo.groupSeq}')" >기본음원등록</a></td>
												
												<td><input type="checkbox" name="ck_GroupId" value="${groupInfo.groupSeq}" ></td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<div class="pageFooter" id="basciPaging">
										<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />	
										<div class="clear"></div>	
									</div>		
									
									
									<!--  파일 리스트 보기  -->
									<table id="leftInfo" style="display:none;">
										<thead>											
											<tr>
												<th>콘텐츠명</th>
												<th>재생시간</th>
												<th>송출추가</th>
												<th><input type="checkbox" name="L_checkAll" id="L_checkAll" onChange="javascript:allCheck('L');"></th>
											</tr>
										</thead>										
										<tbody>
										   
										</tbody>
									</table>
									
									<div class="pageFooter" id="basciPagingInfo" style="display:none;">
										<div class="clear"></div>	
								   </div>							
								   <!--  파일 리스트 보기  끝-->	
								</div>								
							</div>
							<div class="beetCon groupTable" id="view_left01_2">				
							</div>
							<div class="groupCon whiteBox groupTable" id="view_left01_3">
								<div class="innerBox">							
									<!--테이블시작-->
									<div class="searchBox">
										<a href="javascript:fn_move('R')" class="brownBtn" style="float:right; margin-bottom:20px;">선택제외</a>
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
											<option value="Y">배포완료</option>
											<option value="E">미배포</option>
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
                                     <th>지점명</th><th>기본음원</th><th>생성일자</th><th>다운로드확인</th><th>다운로드일자</th><th>재 배포</th>
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
 </form:form>
 
   
	<div id='small-popup-reg' class="needpopup">
	   
	   <div class="contents">
			<div class="header">
				<h2><span id="spTitle" style="color: #fff;font-size: 20px;">기초 음원 시간 관리</span></h2>				
			</div>
			<div class="textT">
				<!--테이블시작-->	    
					<table>
						<!--내용시작-->
						<tbody class="text_left" style="background-color:#fff;">
							
							<tr id="tr_copy">
								<th><span id="spInputTitle">복사할 음원 시간표</span></th>
								<td colspan="3">
								  <select id="cp_copyGroupSeq">
								  
								  </select>
								</td>
							</tr>
							<tr>
								<th><span id="spInputTitle">내용</span></th>
								<td colspan="3"><input type="text" id="groupTitle" name="groupTitle" max="200" size="80" title="내용"></td>
							</tr>
							<tr>
								<th><span id="spInputTitle">운영시간구분</span></th>
								<td>
								   <form:select path="groupTimegubun" id="groupTimegubun" title="기본음원선택">
										         <form:option value="" label="--선택하세요--"/>
						                        <form:options items="${groupTimegubun}" itemValue="code" itemLabel="codeNm"/>
								    </form:select>
								</td>
								<th><span id="spInputTitle">운영시간</span></th>
								<td>
								  <% 
								  String[] orderLsts = {"08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
								  %> 
								  <select id ="groupStarttime">
								        <c:forEach var="orderLst" items="<%=orderLsts%>">
									      <option value='<c:out value="${orderLst}" />' ><c:out value="${orderLst}" /></option>
									    </c:forEach>							   													 
								  </select>/
								   <% 
								  String[] orderLsts_e = {"09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"};
								  %> 
								  <select id ="groupEndtime">
								        <c:forEach var="orderLst_e" items="<%=orderLsts_e%>">
									      <option value='<c:out value="${orderLst_e}" />' ><c:out value="${orderLst_e}" /></option>
									    </c:forEach>							   													 
								  </select>
								</td>
								
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
	 $(document).ready(function() {
			//시작시 넣을 파일
		needPopup.config.custom = {
			'removerPlace': 'outside',
			'closeOnOutside': false,
			onShow: function() {},
			onHide: function() {}
		};
		needPopup.init();
		
 		conView("M");
		$("#schPageSize").val("10");
		$("#schPageIndex").val("1");		
		$("#centerGubun").val("BRANCH01");		
		basciCenterList(); 			
		
	 });
	 //좌측 편성표 보기
	 function fn_GrougView(){
		 location.reload();
		 
	 }
	 function groupFileView(groupSeq){
	     $("#groupSeq").val(groupSeq);
	     //페이지 보여 주기 
	     $("#btn_Group").hide();
	     $("#btn_GroupDel").hide();
		 $("#leftInfo_group").hide();
		 $("#basciPaging").hide();
		 
		 
		 //여기 부분 상세 페이지 리스트 
		 $("#btn_GroupFile").show();
		 $("#leftInfo").show();
		 $("#basciPagingInfo").show();
		 $("#btn_GroupFileCh").show();
		 
		 apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodBasicTimeFileList.do",
					{
						groupSeq : groupSeq	,
						basicCode : $("#basicCode").val(),
						pageIndex : $("#pageIndex").val()
					},
					null,				
					function(result) {	
						if (result.status == "SUCCESS"){
							
							$("#rightInfo > tbody").empty();
							if (result.resultListBasic.length> 0){
								
								for (var i = 0; i <result.resultListBasic.length; i++){
									var obj = result.resultListBasic[i];
									var shtml = "<tr><td><a href=# onclick=fn_intervalFile('"+obj.brodFileseq+"')>"+obj.orignlFileNm+"</a></td>"
									          + " <td>"+obj.brodStartday+"</td>"
									          + " <td>"+obj.brodEndday+"</td>"
									          + " <td><a class='redBtn' onClick='basicFileIn(&#39;"+obj.brodFileseq+"&#39;,&#39;L&#39;)'>제외</a></td>"
									          + " <td><input type='checkbox' name='ck_brodFileseq' id='ck_"+obj.brodFileseq+"' value='"+obj.brodFileseq+"'></td>"
									          + "</tr>";
									$("#rightInfo > tbody:last").append(shtml);	
								}
								
							}
							
							
							$("#leftInfo > tbody").empty();
							if (result.resultList.length> 0){
								for (var a = 0; a <result.resultList.length; a++){
									var obj_L = result.resultList[a];
									var shtml = "<tr class='selectTable' id='"+ obj_L.atchFileId+"''><td>"+obj_L.orignlFileNm+"</a></td>"
									          + "<td>"+obj_L.fileThumnail+"</td>"
									          + "<td><a class='blueBtn' href='javascript:basicDayInput(&#39;"+obj_L.atchFileId+"&#39;,&#39;R&#39;)'>추가</a></td>"
									          + "<td><input type='checkbox' name='ck_fileId' value='"+ obj_L.atchFileId+"'></td>"
									          +"<tr>";
									
									$("#leftInfo > tbody:last").append(shtml);
								}
								//페이징 처리 
								var pageObj = result.paginationInfo;						
								var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, pageObj.totalPageCount, pageObj.pageSize, "ajaxBrodPageChange");
								$("#basciPagingInfo").html(pageHtml+"<div class='clear'></div>");
								
							}
							
						}else {
							alert("처리 도중 문제가 발생 하였습니다.");
						}							
					},
					null,
					null
			 );
		 
		 
		 
	 }
	 //여기 시간별 내용 정리
	 function fn_GroupUpdate(mode, groupSeq){
         $("#mode").val(mode);
		 if (mode == "Ins"){
			 $("#groupTitle").val("");
			 $("#groupTimegubun").val("");
			 $("#spTitle").html("기초 음원 시간 관리");
			 $("#tr_copy").hide();
		 }else if (mode == "Cpy"){
			 $("#groupTitle").val("");
			 $("#groupTimegubun").val("");
			 $("#spTitle").html("기초 음원 시간 관리 복사");
			 $("#tr_copy").show();
			 $("#btn_Update").text("복사");
			 apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodGroupSelect.do",
						null,
						null,				
						function(result) {	
							if (result.status == "SUCESS"){
								$("#cp_copyGroupSeq").empty();
								var option = "";
								for (var i =0; i < result.groupCombo.length; i ++){
									 var obj = result.groupCombo[i];
									 var option = "<option value='"+obj.groupSeq+"'>"+obj.groupTitle+"</option>";
									 
									 $("#cp_copyGroupSeq").append(option);
								}
							}else {
								alert("처리 도중 문제가 발생 하였습니다.");
							}							
						},
						null, null
			  );
		 }else if (mode == "Del"){
			 //기초 음원 삭제
			  var cnt = $("input[name=ck_GroupId]:checkbox:checked").length;
		      var del_atch = "";			
		      if (cnt < 1) {
				 alert("하나 이상의 체크를 선택 하셔야 합니다");
		      } else {
				  for (var i = 0; i < $("input[name=ck_GroupId]:checkbox").length; i++) {
						if (document.getElementsByName("ck_GroupId")[i].checked == true) {						
							del_atch = del_atch + "," + document.getElementsByName("ck_GroupId")[i].value;						
						}
				  }
				  apiExecute(
							"POST", 
							"/backoffice/sub/brodManage/brodBasicTimeDel.do",
							{delBasciSeq : del_atch.substring(1) },
							null,				
							function(result) {	
								if (result.status == "SUCESS"){
									location.reload();						
								}else {
									alert("처리 도중 문제가 발생 하였습니다.");
								}							
							},
							null, null
				  );
		      }
		 }else{
			 $("#groupSeq").val(groupSeq);
			 $("#btn_Update").text("수정");
			 $("#tr_copy").hide();
			 apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodBasicTimeInfo.do",
						{
							groupSeq : groupSeq						
						},
						null,				
						function(result) {	
							if (result.status == "SUCCESS"){
								var group_Info = result.groupInfo;
								$("#groupTitle").val(group_Info.groupTitle);
								$("#groupTimegubun").val(group_Info.groupTimegubun);
								if ($("#groupTimegubun").val() == "TIME_INPUT_1"){
									$("#groupStarttime").append("<option value='00:00'>00:00</option>").val("00:00");
									$("#groupEndtime").append("<option value='23:59'>23:59</option>").val("23:59");
								}else {
									$("#groupStarttime").val(fn_timeSplit(group_Info.groupStarttime));
									$("#groupEndtime").val(fn_timeSplit(group_Info.groupEndtime));	
								}
								
														
							}else {
								alert("처리 도중 문제가 발생 하였습니다.");
							}							
						},
						null,
						null
				 );
		 } 
	 }
	 //등록
	 function fn_CheckForm(){
		 if (any_empt_line_id("groupTitle", "제목을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("groupTimegubun", "시간운영구분을 선택 하지 않았습니다.") == false) return;
		 if ($("#groupTimegubun").val() == "TIME_INPUT_2"){
			 if (any_empt_line_id("groupStarttime", "시작 시간을 선택 하지 않았습니다.") == false) return;
			 if (any_empt_line_id("groupEndtime", "종료시간을 선택 하지 않았습니다.") == false) return;
		 }else {
			 $("#groupStarttime").append("<option value='00:00'>00:00</option>").val("00:00");
			 $("#groupEndtime").append("<option value='23:59'>23:59</option>").val("23:59");
			 
		 }
		 
		 
		 uniAjax(	"/backoffice/sub/brodManage/brodBasicTimeUpdate.do",
					{
						mode: $("#mode").val(),
						basicCode :$("#basicCode").val(),
						groupSeq : $("#groupSeq").val(),
						groupTitle : $("#groupTitle").val(),
						cp_copyGroupSeq : $("#cp_copyGroupSeq").val(),
						groupStarttime : $("#groupStarttime").val().replace(":",""),
						groupEndtime : $("#groupEndtime").val().replace(":",""),
						groupTimegubun : $("#groupTimegubun").val()
					},				
					function(result) {	
						if (result.status == "SUCCESS"){
							//리스트 보여주기
							document.location.reload();
						}else {
							alert(result.message);
						}							
					},
                    function (request){
						alert("ERROR:" + request.state);
					}
			 );
	 }
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
						timeHtml = "";
						for (var i=0; i< result.resultMap.schList.length; i++) {
							
							var obj = result.resultMap.schList[i];
							
							timeHtml += "<tr style='width:100%'><td style='text-align:center'>"+ obj.centerNm+"</td>";
							timeHtml += "<td style='text-align:center'>"+ obj.basicGroupNm+"</td>";
							timeHtml += "<td style='text-align:center'>"+ obj.createRegdate+"</td>";
							if (obj.didDowncheck == "N"){
								timeHtml += "<td style='text-align:center'>대기중</td>";
								timeHtml += "<td style='text-align:center'>&nbsp;</td>";
								timeHtml += "<td style='text-align:center'><a class='grayBtn'>재 배포</a></td>";
							}else {
								timeHtml += "<td style='text-align:center'>다운로드완료</td>";
								timeHtml += "<td style='text-align:center'>"+ obj.didDownloaddate +" </td>";
								timeHtml += "<td style='text-align:center'><a class='grayBtn'>재 배포</a></td>";
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
	 function ajaxBrodPageChange(code){
		 $("#pageIndex").val(code);
		 groupFileView($("#groupSeq").val());		 
	 }
	 
	 function search_form(){
		$("form[name=regist]").submit();
	    $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodBasicTimeDetail.do").submit();
	 }
	 
	 function fn_move(moveGubun){
		 var check_inputNm = "";
		 var all_CheckNm = "";
		 if (moveGubun == "L"){
			 check_inputNm = "ck_fileId";
			 all_CheckNm = "L_checkAll"; 
		 }else {
			 check_inputNm = "ck_brodFileseq";
			 all_CheckNm = "checkAll";
			 
		 }
		 var cnt = $("input[name="+check_inputNm+"]:checkbox:checked").length;
		 var del_atch = "";			
		 if (cnt < 1) {
				alert("하나 이상의 체크를 선택 하셔야 합니다");
		 } else {
			  for (var i = 0; i < $("input[name="+check_inputNm+"]:checkbox").length; i++) {
					if (document.getElementsByName(check_inputNm)[i].checked == true) {						
						del_atch = del_atch + "," + document.getElementsByName(check_inputNm)[i].value;						
					}
			   } 
			   
			   
			   $("#delBasciSeq").val(del_atch);
			  
			   //ajax  하기
			   loadingStart();
			   apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodGroupFileCheckUpdate.do",
					{
						basicCode : $("#basicCode").val(),
						groupSeq : $("#groupSeq").val(),
						brodStartday : $("#brodStartday").val(),
						brodEndday : $("#brodEndday").val(),
						delBasciSeq : $("#delBasciSeq").val().substring(1)		,
						pageIndex : $("#pageIndex").val(),
						pageSize : $("#pageUnit").val(), 
						moveGubun: moveGubun,
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
			   $("#"+all_CheckNm).prop("checked", false);
			   
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
						}else{
							alert("데이터 처리 도중 문제가 발생 하였습니다.");							
						}							
					},
					null,
					null
				);		 
	 }
	 
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
		 if (any_empt_line_id("centerGubun", "검색할 지검 구분값을 선택 하지 않았습니다.") == false) return;
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
		 }else if (code == "G"){
			 if ($("#L_checkGroupAll").prop("checked")) {
					$("input[name=ck_GroupId]").prop("checked", true);
			 } else {
					$("input[name=ck_GroupId]").prop("checked", false);
			 }
		 }else {
			 //우측 체크 박스 
			 if ($("#checkAll").prop("checked")) {
					$("input[name=ck_brodFileseq]").prop("checked", true);
			 } else {
					$("input[name=ck_brodFileseq]").prop("checked", false);
			 }	 
		 }
		 
	 }
	 
	 
	 
	 
	 function basciSchedule(result){
		 var timeHtml = "";
			
			if (result != null){
				if (result.resultMap.schList != null){
/* 					
					
					$("#leftInfo > tbody").empty();
					if (result.resultMap.schList.length> 0){
						for (var a = 0; a <result.resultMap.schList.length; a++){
							var obj_L = result.resultMap.schList[a];
							var shtml = "<tr class='selectTable' id='"+ obj_L.atchFileId+"''><td>"+obj_L.orignlFileNm+"</a></td>"
							          + "<td>"+obj_L.fileThumnail+"</td>"
							          + "<td><a class='blueBtn' href='javascript:basicDayInput(&#39;"+obj_L.atchFileId+"&#39;,&#39;R&#39;)'>추가</a></td>"
							          + "<td><input type='checkbox' name='ck_fileId' value='"+ obj_L.atchFileId+"'></td>"
							          +"<tr>";
							
							$("#leftInfo > tbody:last").append(shtml);
						}						
					} */
					
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
					$("#rightInfo > tbody").empty();
					for (var i=0; i < result.resultMap.atchFileLst.length; i++) {
						
						var obj = result.resultMap.atchFileLst[i];
						var shtml = "<tr><td><a href=# onclick=fn_intervalFile('"+obj.brodFileseq+"')>"+obj.orignlFileNm+"</a></td>"
						          + " <td>"+obj.brodStartday+"</td>"
						          + " <td>"+obj.brodEndday+"</td>"
						          + " <td><a class='redBtn' onClick='basicFileIn(&#39;"+obj.brodFileseq+"&#39;,&#39;L&#39;)'>제외</a></td>"
						          + " <td><input type='checkbox' name='ck_brodFileseq' id='ck_"+obj.brodFileseq+"' value='"+obj.brodFileseq+"'></td>"
						          + "</tr>";
						$("#rightInfo > tbody:last").append(shtml);	
					}
				}
				//여기 수정 
				if (result.resultMap.paging != null){
					var pageObj = result.resultMap.paging;	
					
					var pageHtml = ajaxPaging(pageObj.currentPageNo, p-ageObj.firstPageNo, pageObj.recordCountPerPage, pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, pageObj.totalPageCount, pageObj.pageSize, "ajaxBrodPageChange");
					$("#basciPagingInfo").html(pageHtml+"<div class='clear'></div>");
					
					/* var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, pageObj.totalPageCount, pageObj.pageSize, "linkPage");
					$("#basciPagingInfo").html(pageHtml+"<div class='clear'></div>");	 */						
				}
			}
	 } 
	 function basicFileIn(code, queryGubun){
		 
		 
		 if (queryGubun == "R"){
			 params = "basicOrder=1&"+ $("#groupSeq").serialize() +"&"+ $("#brodStartday").serialize() +"&"+ $("#brodEndday").serialize() +"&"+ $("#basicCode").serialize() +"&atchFileId="+code+"&fileGubun=R";	 
		 }else {
			 params = $("#basicCode").serialize() +"&"+ $("#groupSeq").serialize() +"&brodFileseq="+code+"&fileGubun=L";
			                                                                          
		 }
		 params = params + "&"+ $("#pageIndex").serialize()+"&" + $("#pageUnit").serialize()+"&" + $("#searchCondition").serialize()+"&" + $("#searchKeyword").serialize() ;
		 console.log(params);
		 loadingStart();
		 $.ajax({
			url : '/backoffice/sub/brodManage/brodBasicFileUpdate_Itv.do',
			type : 'POST',
			data : params,
			dataType : 'json',
			success : function(result) {
				loadingFinish();
				if (result != null) {
					basciSchedule(result);										
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
		 
		 /* if (code1 == "L"){
			 basicFileIn(code, 'L');
		 }else { */
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
		 /* } */
		 
	}
	 

 </script>
</body>
</html>