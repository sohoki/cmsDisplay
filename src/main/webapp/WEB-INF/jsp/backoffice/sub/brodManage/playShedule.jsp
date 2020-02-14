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
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
<style>
		#wrap { min-height :1080px;}
	</style>
</head>
<body>

<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/playShedule.do">
<%-- <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }"> --%>
<form:hidden path="pageIndex" id="pageIndex"/>	
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="brodCode" id="brodCode" >

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
						<li><a href="/backoffice/sub/brodManage/brodContentList.do" class="playContents">음원콘텐츠관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentPlayList.do" class="playContents">스케줄음원관리</a></li>
						<li class="active"><a href="/backoffice/sub/brodManage/playShedule.do" class="playShedule">음원콘텐츠배포</a></li>
						<li><a href="/backoffice/sub/brodManage/playSheduleStatus.do" class="playShedule">콘텐츠배포현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">					
						<!--콘텐츠스케줄-->
						<div class="con_sub ">
							<div class="groupCon whiteBox groupTable">
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
											<option value="CON_NM" <c:if test="${searchVO.searchCondition == 'CON_NM' }"> selected="selected" </c:if>>콘텐츠명</option>
	  									    <option value="centerId" <c:if test="${searchVO.searchCondition == 'centerId' }"> selected="selected" </c:if>>기본음원</option>
										</select>
										<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
										<a href="javascript:search_form('/backoffice/sub/brodManage/playShedule.do')" class="yellowBtn">검색</a>	
									</div>	
									<!--테이블시작-->
									<table>
										<!--테이블상단-->
										<thead>
											<!--줄시작-->
											<tr>
												<th>콘텐츠명</th>
												<th>이벤트</th>
												<th>기본음원</th>	
											</tr>
										</thead>
										<!--테이블내용-->
										<tbody>
										    <c:forEach items="${resultList}" var="brodInfo" varStatus="status">
											<tr class="selectTable">
												<td><a href="javascript:brodSch('${brodInfo.brodCode}')">${brodInfo.brodName}</a></td>
												<td><a href="javascript:brodSch('${brodInfo.brodCode}')">${brodInfo.secGubun}</a></td>
												<td>${brodInfo.orignlFileNm }</td>
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
									<div class="searchBox">
										<span>지점명 검색 : </span>
										<input type="text" name="centerNm" id="centerNm">
										<a href="javascript:rightSearch()" class="yellowBtn">검색</a>	
									</div>									
									<!--테이블시작-->
									<table id="rightInfo">
										<!--테이블상단-->
										<thead>
											<!--줄시작-->
											<tr>
												<th>
													<!-- <input type="checkbox" name="checkAll" id="checkAll" onChange="javascript:allCheck();"> -->
													적용 
												</th>
												<th>지점명</th>
												<th>오픈시간</th>
												<th>폐점시간</th>		
											</tr>
										</thead>
										<tbody>
										  
										</tbody>
										
									</table>
									<!-- 
									<div class="footerBox">
										<a href="javascript:schUpdate()" class="yellowBtn">업데이트</a>
									</div>
									 -->		
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
			//시작시 넣을 파일
	 });
	 function search_form(){
		
	    $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/playShedule.do").submit();
	 }
     function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	 }
	 //지점 검색  추가 작업 해야함 
	 function rightSearch(){
		 var params = $("form[name=regist]").serialize();
			$.ajax({
				url : '/backoffice/sub/brodManage/playRightShedule.do',
				type : 'POST',
				data : params,
				dataType : 'json',
				success : function(result) {
					if (result) {
						print(result.schList);
						console.log("success" + result.schList.length);
					}
				},
				error : function(e) {
					console.log("falit" + e);
				}
			});
		 
	 }
	 function brodSch(code){
		 $("#rightInfo").find('tbody').empty();
		 $("#brodCode").val(code);
		 apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/playRightShedule.do",
					{
						searchKeyword : $("#searchKeyword").val(),
						brodCode : $("#brodCode").val()
					},
					null,				
					function(result) {				
						if (result.schList != null) {
							//방송 정보 보여주기 
							print(result.schList);
							/* var timeHtml = ""	
							for (var i=0; i<result.schList.length; i++) {
									var obj = result.schList[i];
									timeHtml += "<tr style='width:100%'><td style='text-align:center'>";
									if (obj.brodCode != "N" ){
										timeHtml += "<input type='checkbox' id='brodCodeCenter' name='brodCodeCenter' value='"+ obj.centerId + "' checked onChange='javascript:updateSch(&#39;"+obj.centerId +"&#39;,&#39;"+obj.centerStartTime +"&#39;,&#39;"+obj.centerEndTime +"&#39;)'>";  
									}else {
										timeHtml += "<input type='checkbox' id='brodCodeCenter' name='brodCodeCenter' value='"+ obj.centerId + "' onChange='javascript:updateSch(&#39;"+obj.centerId +"&#39;,&#39;"+obj.centerStartTime +"&#39;,&#39;"+obj.centerEndTime +"&#39;)'>";
									}
									timeHtml += "</td>";
									timeHtml += "<td style='text-align:center'>"+ obj.centerNm+"</td><td style='text-align:center'>"+ obj.centerStartTime+"</td><td style='text-align:center'>"+ obj.centerEndTime+"</td>";
									timeHtml += "</tr>";
									
							}
							//alert(timeHtml);
							$("#rightInfo").find('tbody').append(timeHtml); */
						}							
					},
					null,
					null
				);
	 }
	 function updateSch( code1, code2, code3){
		  var checkbox = $("input[value="+code1+"]");
		  var checkVal = "";
		  var checkMessage = "";
		  if (checkbox.prop("checked") == true){ checkVal = "Y"; }else { checkVal = "N"; }
		  if (checkbox.prop("checked") == true){ checkMessage = "기본  음원이 등록 되었습니다."; }else { checkMessage = "기본 음원이 삭제 되었습니다."; }
		  apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/playRightSheduleUpdate.do",
					{
						checkVal : checkVal,
						centerStartTime : code2,
						centerEndTime : code3,
						centerId : code1,
						brodCode : $("#brodCode").val()
					},
					null,				
					function(result) {				
						if (result != null && result != "") {
							//방송 정보 보여주기 							
							if (result == "0"){
								alert("작업 도중 문제가 발생 하였습니다.");
								
							}
							else {
								alert(checkMessage);
							}
							brodSch($("#brodCode").val());
						}							
					},
					null,
					null
				);		 
	 }
	 function schUpdate(){
			var cnt = $("input[name=brodCodeCenter]:checkbox:checked").length;
	    	var sch_update = "";
	    	if (cnt< 1){
	    		alert("하나 이상의 체크를 선택 하셔야 합니다");
	    	}else {
	    		for (var i= 0; i < document.getElementsByName("brodCodeCenter").length; i++){
	    			if (document.getElementsByName("brodCodeCenter")[i].checked == true){
	    				sch_update = sch_update+","+document.getElementsByName("brodCodeCenter")[i].value;	
	    			}    			
	    		} 
	    		 apiExecute(
	 					"POST", 
	 					"/backoffice/sub/brodManage/playRightSheduleUpdate.do",
	 					{
	 						centerInfo : sch_update,
	 						brodCode : $("#brodCode").val()
	 					},
	 					null,				
	 					function(result) {				
	 						if (result != null && result != "") {
	 							//방송 정보 보여주기 							
	 							
	 						}							
	 					},
	 					null,
	 					null
	 				);
	    		
	    	}
	 }
	 function print(data){
		 $("#rightInfo").find('tbody').empty();
		 var timeHtml = ""	
				for (var i=0; i<data.length; i++) {
						var obj = data[i];
						timeHtml += "<tr style='width:100%'><td style='text-align:center'>";
						if (obj.brodCode != "N" ){
							timeHtml += "<input type='checkbox' id='brodCodeCenter' name='brodCodeCenter' value='"+ obj.centerId + "' checked onChange='javascript:updateSch(&#39;"+obj.centerId +"&#39;,&#39;"+obj.centerStartTime +"&#39;,&#39;"+obj.centerEndTime +"&#39;)'>";  
						}else {
							timeHtml += "<input type='checkbox' id='brodCodeCenter' name='brodCodeCenter' value='"+ obj.centerId + "' onChange='javascript:updateSch(&#39;"+obj.centerId +"&#39;,&#39;"+obj.centerStartTime +"&#39;,&#39;"+obj.centerEndTime +"&#39;)'>";
						}
						timeHtml += "</td>";
						timeHtml += "<td style='text-align:center'>"+ obj.centerNm+"</td><td style='text-align:center'>"+ obj.centerStartTime+"</td><td style='text-align:center'>"+ obj.centerEndTime+"</td>";
						timeHtml += "</tr>";
						
				}
				//alert(timeHtml);
				$("#rightInfo").find('tbody').append(timeHtml);
	 }
 </script>
</body>
</html>