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
	<link rel="stylesheet" type="text/css" href="/css/calendar.css">
	<link href="<c:url value='/'/>css/needpopup.min.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/new_calendar.js"></script>    	
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/aten-common.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>	
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<span id="backgroundgif"></span>
	  	   <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/schList.do">	   	   
        <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
        <input type="hidden" name="mode" id="mode" >
        <input type="hidden" name="schCode" id="schCode" >    
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
					<ul class="topMenu" >
						<li class="active"><a href="/backoffice/sub/equiManage/schList.do" class="schedule">그룹&화면 매칭</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--스케줄 관리-->
						<div class="con_title whiteBox ">
							<h2><img src="../../img/list.png"> 단말기 그룹 및 화면 매칭 리스트입니다.</h2>
							<div class="searchBox">
									<span>총 : ${totalCnt}개</span>
									<select name="pageUnit" id="pageUnit" >
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
									</select>
									<select name="searchCondition"  id="searchCondition">
										<option value="schName" <c:if test="${searchVO.searchCondition == 'schName' }"> selected="selected" </c:if>>스케줄명</option>
										<option value="schCode" <c:if test="${searchVO.searchCondition == 'schCode' }"> selected="selected" </c:if>>스케줄코드</option>
									</select>
									<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
									<a href="javascript:search_form();" class="yellowBtn">검색</a>
								<div class="footerBox">
									<a href="javascript:view_Sch('Ins','0');" class="yellowBtn">등록</a>
									<a href="javascript:check_del();" class="redBtn">삭제</a>
								</div>
								<div class="clear"></div>
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										<th>번호</th>
										<th>스케줄명</th>
										<th>기간</th>
										<th>DID Group</th>
										<th>콘텐츠명</th>
										<th>긴급</th>
										<th>사용유무</th>
										<th><input type="checkbox" name="all_check_t" id="all_check_t"  style="width:15px"></th>				
									</tr>
								</thead>
								<tbody>
								   <c:forEach items="${resultList }" var="scheduleInfo" varStatus="status"> 
									<tr>
										<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
										<td><a href="javascript:view_Sch('Edt','${scheduleInfo.schCode}')"> <c:out value="${scheduleInfo.schName}" /></a></td>						
										<td><c:out value="${scheduleInfo.schStartDay}" /> ~ <c:out value="${scheduleInfo.schEndDay}" /></td>
										<input type="hidden" id="schStartDay_${status.count}" value="${scheduleInfo.schStartDay}"/>
										<input type="hidden" id="schEndDay_${status.count}" value="${scheduleInfo.schEndDay}"/>
										<td><a onclick="preview_group('${scheduleInfo.groupCode}','${scheduleInfo.groupNm}')" data-needpopup-show="#small-popup-preview" class="blueBtn" style="font-size: 12px;" id="grp_${ status.count}" value="${scheduleInfo.groupCode}">${scheduleInfo.groupNm}</a></td>
										<td><a onclick="view_connPage('c', '${scheduleInfo.contentCode}')" class="blueBtn" style="font-size:12px;"> ${scheduleInfo.conNm }</a></td>
										<td>
										<c:choose>
										   <c:when test="${scheduleInfo.schEmerGubun eq 'Y' }">긴급</c:when>
										   <c:otherwise>일반</c:otherwise>
										</c:choose>
										</td>
										<td>
										<c:choose>
										   <c:when test="${scheduleInfo.schUseYn eq 'Y' }">사용</c:when>
										   <c:otherwise>사용 안함</c:otherwise>
										</c:choose>
										</td>
										<td><input type="checkbox" name="delCk" id="delCk_${ status.count}" value="${scheduleInfo.schCode }"></td>
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
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
	<div id='small-popup-preview' class="needpopup">
	   <div class="contents">
			<div class="header">
				<h2>
					<span id="spTitle" style="color: #fff;font-size: 20px;"></span>
					<br>
					<span id="subSpTitle" style='font-size:12px; color:#ccc;'></span>
				</h2>				
			</div>
			<div class="textT">
				<!--테이블시작-->	    
				<table id="basicList" style="color:#FFFFFF;">
					<!--내용시작-->
				</table>	
			</div>
		</div>
	</div>
    
    
    </form:form>
    <script type="text/javascript" src="/js/needpopup.min.js"></script>
    <script type="text/javascript">
    	
	  $(document).ready(function () {		   
          $("#all_check").click(function () {
              if ($("#all_check").is(":checked")) {
                  $('input:checkbox[id^=delCk]:not(checked)').prop("checked", true);
              } else {
                  $('input:checkbox[id^=delCk]:checked').prop("checked", false);
              }
          });
          $("#all_check_t").click(function () {
              if ($("#all_check_t").is(":checked")) {
                  $('input:checkbox[id^=delCk]:not(checked)').prop("checked", true);
              } else {
                  $('input:checkbox[id^=delCk]:checked').prop("checked", false);
              }
          });
          
		//시작시 넣을 파일
			needPopup.config.custom = {
				'removerPlace': 'outside',
				'closeOnOutside': false,
				onShow: function() {},
				onHide: function() {}
			};
			needPopup.init();
			$('#basicGroupList tr').css("background-color","");

      });
	  
		function preview_group(code, gNm){
			var didDetailHtm = "";
		    	
			apiExecute(
				"POST", 
				"/backoffice/sub/equiManage/DidgroupLst.do",
				{
					groupCode : code
				},
				null,				
				function(result) {
					$("#spTitle").html("<a onclick='view_connPage(&#39;g&#39;,&#39;"+code+"&#39;)' style='cursor:pointer; color:#fff;'>["+gNm+"]</a>" + " 그룹 단말 리스트");
					$("#subSpTitle").html("* 그룹 단말 클릭시 단말 정보 확인이 가능합니다.");
					didDetailHtm = "<tbody>";
					if (result != null) {
						if (result.didLst != null) {
							for (var i=0; i<result.didLst.length; i++) {
								var obj = result.didLst[i];
								didDetailHtm += "<tr><td>"+ (i+1)  +"</td><td><a style='color:#fff; cursor:pointer;' onclick='view_connPage(&#39;d&#39;,&#39;"+obj.didId+"&#39;)'>"+ obj.didNm  +"</a></td></tr>";	     									
							}	  
						}    
						didDetailHtm += "</tbody>";
						$("#basicList").html(didDetailHtm);
					}
				},
				function(request){
							
				},
				null
			);   
		}
		

	  
      function view_Sch(code, code1){
		  $('#mode').val(code);
		  $('#schCode').val(code1);
		  if (code == "Ins"){			   
			  $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schDetail.do").submit();		       
		  }else {
			  $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schView.do").submit();			  
		  }		    	  
      }
      function linkPage(pageNo) {
  		$(":hidden[name=pageIndex]").val(pageNo);				
  		$("form[name=regist]").submit();
  	   }      
      function search_form(){
    	  
    	  $(":hidden[name=pageIndex]").val("1");	
    	  $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/schList.do").submit();
      }
		function check_del(){
			loadingStart();
			// 로딩화면 표출
			var check_del = "";
			var check_del_num = 0;
			var check_del_schCode = "";
			var check_del_grpCode = "";
			var check_del_len = $("input[name=delCk]:checked").length;
			var startDay = "";
			var endDay = "";
			var todayGet = today_get();
			var deleteResult = "";
			var deleteFail = 0;
			var deleteSuccess = 0;
			
			if (check_del_len == 0){
				alert("체크 하신 스케줄이 없습니다.");
			} else {
				
				$("input[name=delCk]:checked").each(function(){
					check_del = $(this); 
					check_del_num = $(this).attr("id").substring(6);
					check_del_schCode = check_del.attr("value");
					check_del_grpCode = $("#grp_"+check_del_num).attr("value");
					startDay = $("#schStartDay_"+check_del_num).val().replace(/-/g, "");
					endDay = $("#schEndDay_"+check_del_num).val().replace(/-/g, "");
					
					console.log(check_del_schCode + ", " + check_del_grpCode + " Schedule Delete Tying...");

					// 0. 스케줄 송출 일자 확인
					if(startDay <= todayGet && todayGet <= endDay){
						// 날짜가 포함됨
						// 1. 그룹 내 단말 정보 확인
						$.ajax({
							url: "/backoffice/sub/equiManage/DidgroupLst.do",
							async : false,
							type: "POST",
							data : {
								"groupCode" : check_del_grpCode
							},
							success: function(grpResult){
								if (grpResult != null) {
			   						if (grpResult.didLst != null) {
			   							if(grpResult.didLst.length > 0){
			   								// 그룹 내 단말 정보가 존재하므로 삭제 거부
			   								deleteFail++;
			   								console.log(check_del_grpCode + ", " + check_del_schCode + " Schedule Delete Fail, Exist DID Device...");
			   							}else{
			   								// 그룹 내 단말이 없으므로 삭제 진행
   											$.ajax({
												url: "/backoffice/sub/equiManage/delSchedule.do",
												async : false,
												type: "POST",
												data : {
													"sch_code" : check_del_schCode
												},
												success: function(schResult){
													if (schResult != null) {
														if(schResult == "T"){
															deleteSuccess++;		
						   									console.log(check_del_grpCode + ", " + check_del_schCode + " Schedule Delete Success...");
														}else{
															deleteFail++;  							
						   									console.log(check_del_grpCode + ", " + check_del_schCode + " Schedule Delete Fail, Schedule System...");
														}	
													}
												}
											});
					        			}
			   						}
								}
							}
						});
					} else {
						// 날짜가 포함 되지 않음
						$.ajax({
							url: "/backoffice/sub/equiManage/delSchedule.do",
							async : false,
							type: "POST",
							data : {
								"sch_code" : check_del_schCode
							},
							success: function(schResult){
								if (schResult != null) {
									if(schResult == "T"){
										deleteSuccess++;		
										console.log(check_del_grpCode + ", " + check_del_schCode + " Schedule Delete Success...");
									}else{
										deleteFail++;  							
										console.log(check_del_grpCode + ", " + check_del_schCode + " Schedule Delete Fail, Schedule System...");
									}	
								}
							}
						});
					}
				});
				alert("삭제 완료 : " + deleteSuccess +"개, 삭제 거부 : "+ deleteFail + "개");
				if(deleteSuccess > 0){
					location.reload();
				}
			}
			loadingFinish();
		}
		function loadingStart(){
		   	var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
		   	$("#backgroundgif").html(loadingimg);
		}
		function loadingFinish(){
			$("#backgroundgif").html("");
		}
		function today_get(){
			var now = new Date();
		    var today_day = fnLPAD(String(now.getDate()), "0", 2); //일자를 구함
		    var today_month = fnLPAD(String((now.getMonth() + 1)), "0", 2); // 월(month)을 구함    
		    var today_year = String(now.getFullYear()); //년(year)을 구함
		    var today = today_year + today_month + today_day;
		    
		    return today;
		}
    </script>
</body>
</html>		