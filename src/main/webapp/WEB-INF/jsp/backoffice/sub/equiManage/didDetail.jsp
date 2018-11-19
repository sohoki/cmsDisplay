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
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/popup.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/aten-common.js"></script>
<script type="text/javascript" src="/js/leftMenu.js"></script>

</head>
<body>

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/didUpdate.do">
<form:hidden path="mode" id="mode" />	
<form:hidden path="pageUnit" id="pageUnit" />
<form:hidden path="searchCondition" id="searchCondition" />
<form:hidden path="searchKeyword" id="searchKeyword" />
<form:hidden path="menuGubun" id="menuGubun" />
<form:hidden path="pageIndex" id="pageIndex" />

<form:hidden path="role_Code" id="role_Code" />		
<form:hidden path="author_Code" id="author_Code" />
<form:hidden path="groupCode" id="groupCode" />		
<input type=hidden id="startTime" value="${regist.didStartTime }" />
<input type=hidden id="endTime" value="${regist.didEndTime }" />
<input type=hidden id="groupInfo" value="${regist.groupId }" />

	
<div id="wrap">
<span id="backgroundgif"></span>
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
				<li class="active"><a href="/backoffice/sub/equiManage/didList.do" class="did">단말기 관리</a></li>
				<li><a href="/backoffice/sub/equiManage/did_groupList.do" class="didGroup">그룹 관리</a></li>
				<li><a href="/backoffice/sub/equiManage/didSendMessage.do" class="didGroup">메세지 관리</a></li>
				<li><a href="/backoffice/sub/equiManage/didSendMessageLst.do" class="didGroup">메세지 현황</a></li>
				<div class="clear"></div>				
		</ul>
        <div class="con">			
			<div class="con_title whiteBox ">
					<div class="searchBox">
						<div class="footerBox">
							<a href="/backoffice/sub/equiManage/didList.do" class="grayBtn">취소</a> 
							<a href="javascript:check_form();" class="yellowBtn">등록</a>
						</div>
						<div class="clear"> </div>
					</div>					
				
							
				<table class="didRegTable">
					<tbody>
						<tr>
							<th colspan="6">단말기 정보</th>
						</tr>
						<tr>	
							<th>단말명</th>
							<td  style="text-align:left">
								<form:input path="didNm" id="didNm" title="did 명칭" size="15" value="${regist.didNm }"/>
							</td>
							<th>단말 ID</th>
							<td style="text-align:left">
								<%-- <c:if test="${regist.didId != '0'}"> value=""</c:if> --%>
				            	<form:input path="didId" id="didId" title="did아이디" size="15" value="${regist.didId }"  placeholder="자동할당"/>			
							</td>
							<th>단말 MAC</th>
							<td style="text-align:left">
								<form:input path="didMac" id="didMac" title="did Mac" size="15" value="${regist.didMac }" placeholder="자동등록"/>
							</td>
							
						</tr>
						<tr>
							<th>단말 IP</th>
							<td  style="text-align:left">
								<form:input path="didIpaddr" id="didIpaddr" title="아이피" size="15" value="${regist.didIpaddr }" placeholder="자동등록"/>
							</td>
							<th>IP타입</th>
							<td style="text-align:left">
								<form:select path="didIptype" id="didIptype" title="소속">
									<form:options items="${selectIpType}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
							</td>
							<th>단말 OS</th>
							<td style="text-align:left">
							  <form:select path="didOs" id="didOs" title="OSType">
							        <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectOs}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							</td>
						</tr>
						<tr>	
							<th>관리 부서</th>
							<td  style="text-align:left">
							  <form:select path="roleCode" id="roleCode" title="소속">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectRole}" itemValue="groupId" itemLabel="groupNm"/>
							   </form:select>
							</td>							
							<th>설치지점</th>
							<td style="text-align:left">
								<input id="center_searchKeyword" name="center_searchKeyword" placeholder="지점명 검색" size="15" />&nbsp;<a onclick="centerSearchCall()" class="blueBtn fontSize12">검색</a>&emsp;
								<form:select path="centerId" id="centerId" name="centerId" title="소속">
							        <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectCenter}" itemValue="centerId" itemLabel="centerNm"/>
							   </form:select>	
							</td>
							<th>그룹정보<a style="font-size: 12px">(선택)</a></th>
							<td style="text-align:left">
								<input id="grp_searchKeyword" name="grp_searchKeyword"  placeholder="그룹명 검색" size="15" />&nbsp;<a onclick="grpSearchCall()" class="blueBtn fontSize12">검색</a>&emsp;
								<form:select path="groupId" id="groupId" name="groupId" title="소속">
							        <form:option value="" label="선택안함"/>
							        <form:options items="${selectGroup}" itemValue="groupCode" itemLabel="groupNm"/>
							   	</form:select>
							   	&emsp;<a class="brownBtn fontSize12" onclick="open_grpReg()">그룹등록</a>
							</td>
						</tr>
						
						<tr>
							<th>단말타입</th>
							<td style="text-align:left">
								<form:select path="didModelType" id="didModelType" title="소속">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectModelType}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							</td>						
							<th>단말형태</th>
							<td style="text-align:left">
								<form:select path="didType" id="didType" title="소속">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectType}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							</td>
							<th><span id=os_txt01">운영시간</span></th>
							<td style="text-align:left">
								<form:select path="didStartTime" id="didStartTime" title="did 명칭"> <%-- value="${regist.didStartTime }" --%>
									<option value=""></option>
								</form:select>
								 ~ 
								<form:select path="didEndTime" id="didEndTime" title="did 명칭"> <%-- value="${regist.didStartTime }" --%>
									<option value=""></option>
								</form:select>
							</td>
						</tr>						
						<tr>
							<th>단말 해상도</th>
							<td style="text-align:left" colspan="3">
								<form:select path="didResolution" id="didResolution" title="소속">
			                        <form:options items="${selectResolution}" itemValue="code" itemLabel="codeNm"/>
								</form:select>&emsp;&emsp;|&emsp;
								가로 :&nbsp;<form:input path="didWidth" id="didWidth" title="넓이" size="12" value="${regist.didWidth }" placeholder="1920" />&emsp;
								세로 :&nbsp;<form:input path="didHeight" id="didHeight" title="높이" size="12" value="${regist.didHeight }" placeholder="1080" />
							</td>
							<th>사용유무</th>
							<td style="text-align:left">
								<c:choose>
									<c:when test="${regist.didUseYn == 'N' }">
										<input type="radio" name="didUseYn" value="Y" />사용
										<input type="radio" name="didUseYn" value="N" checked />사용안함
									</c:when>
									<c:otherwise>
										<input type="radio" name="didUseYn" value="Y" checked />사용
										<input type="radio" name="didUseYn" value="N" />사용안함		
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th class="hideItem">모니터 갯수</th>
							<td class="hideItem" style="text-align:left">
							  <form:select path="didMonitercnt" id="didMonitercnt" title="소속">
							        <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectMonitercnt}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							</td> 
							<th class="hideItem">시리얼포트 사용</th>
							<td class="hideItem" style="text-align:left">
							  <form:select path="didSerialtype" id="didSerialtype" title="사용여부" onChange="javascript:SerailView()">
							        <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectSerialUse}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							</td>
							<th class="hideItem">Com port</th>
							<td class="hideItem" style="text-align:left">
							   <form:select path="didSerialport" id="didSerialport" title="시리얼포트">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectComPort}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							</td>
							<th class="hideItem">시리얼 연동 스크립트</th>
						  	<td class="hideItem" colspan="5" style="text-align:left"><form:input path="didSerialjavascript" id="didSerialjavascript" title="did 명칭" /></td>
							<th class="hideItem">스크립트 유지시간</th>
							<td class="hideItem" style="text-align:left">
								<form:input path="didTimeInterval" id="didTimeInterval" title="did 명칭" size="5" maxLength="5"/>분
							</td>
							<th class="hideItem">Agent ver</th>
							<td class="hideItem" style="text-align:left">
							   <form:select path="didSwver" id="didSwver" title="소속">
							         <form:option value="" label="--선택하세요--"/>
			                        <form:options items="${selectSwver}" itemValue="code" itemLabel="codeNm"/>
							   </form:select>
							   
							</td>
						</tr>
					</tbody>
				</table>
			  </div>				
			</div>
		   </div>
		   <div class="clear"></div>
		</div>
        <!--내용끝-->
	</div>    
	<div class="clear"></div> 
    
    </form:form>		
    <script type="text/javascript">
		$(document).ready(function() { 
		   
			/* Layout and Data Setting :: START */
			
			$(".hideItem").hide();
			
			// 단말 해상도 데이터
			$("#didResolution").change(function(){
				if($(this).val() != "DIDRES00"){
					$("#didWidth").attr("readonly", true);
					$("#didHeight").attr("readonly", true);
				}
				switch($(this).val()){
					case "DIDRES00" : $("#didWidth").attr("readonly", false); $("#didHeight").attr("readonly", false); break;
					case "DIDRES01" : $("#didWidth").val("1920"); $("#didHeight").val("1080"); break;
				    case "DIDRES02" : $("#didWidth").val("3840"); $("#didHeight").val("1080"); break;
				    case "DIDRES03" : $("#didWidth").val("1080"); $("#didHeight").val("1920"); break;
				    case "DIDRES04" : $("#didWidth").val("1080"); $("#didHeight").val("3840"); break;
			    }
		    });
			 
			// 시작 - 종료시간
			// selectTime(element, defaultValue, defaultText)
			selectTime($("#didStartTime"), "00:00", "시작시간", $("#startTime").val());
			selectTime($("#didEndTime"), "00:00", "종료시간", $("#endTime").val());
			
			/* Layout and Data Setting :: FINISH */
			
			if ("${status}" != ""){
				if ("${status}" == "SUCCESS") {
					alert("정상 처리 되었습니다");    			    			
				}else{
					alert("작업 도중 문제가 발생 하였습니다.");
				}		
				 location.href = "/backoffice/sub/equiManage/didList.do";	
			}
			
			
		    if ($("#mode").val() == "Ins"){
		    	$("input[name=didId]").val("");
		 		$("input[name=didId]").attr("disabled", true);
		 		$("input[name=didMac]").attr("disabled", true);
		 		$("input[name=didIpaddr]").attr("disabled", true);
		 		$("input[name=didType]").attr("disabled", true);
		    }	else {
		    	$("input[name=didId]").attr("disabled", false);
		    	$("input[name=didId]").attr("readonly", true);
		    	$("input[name=didMac]").attr("readonly", true);
		 		$("input[name=didIpaddr]").attr("readonly", true);
		    }
		    // SerailView();
		    // osChange();
		});    
      function check_form(){
    	  
    	  // didMoniterCnt 1대 선택 / didSerialType 미사용 선택 / didSerialPort 선택 안해도 됨 / didSerialjavascript 공백 / didTimeInterval 공백 / didSwver 1.0ver
    	  
    	  $("#didMonitercnt").val("Mointer01");
    	  $("#didSerialtype").val("SERIAL_N");

    	  $("#didSwver").val("DIDSW02");

    	  $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didUpdate.do").submit();		           	 
      }
      //os 변경 관련 사항
      function osChange(){
    	  // 의도 ? 2018.06.27 : 답변 전 상태
    	  /* if ($("#didOs").val() == "DID_OS_02"){
    		  $(".os_txt01").css("display","block");
    		  $(".os_txt02").css("display","block");
    		  $("input[name=didStartTime]").attr("disabled", true);    		  
    	  }else {    		  
    		  $(".os_txt01").css("display","none");
    		  $(".os_txt02").css("display","none");
    		  $("input[name=didStartTime]").attr("disabled", false);
    	  }    	   */
      }
      function SerailView(){
    	  if ($("#didSerialtype").val() == "SERIAL_Y"){
    		  $("input[name=didSerialport]").attr("disabled", false);
    	  }else {
    		  $("input[name=didSerialport]").attr("disabled", true);
    	  }
    	  
      }
      	function centerSearchCall(){
      		loadingStart();
      		center_search($("select[name=centerId]"), $("#center_searchKeyword").val());
      		loadingFinish();
		}
		function grpSearchCall(){
			loadingStart();
			grp_search($("select[name=groupId]"), $("#grp_searchKeyword").val(), $("#groupInfo").val(), 2);
			loadingFinish();
		}
		function loadingStart(){
		   	var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
		   	$("#backgroundgif").html(loadingimg);
		}
		function loadingFinish(){
			$("#backgroundgif").html("");
		}
    </script>
</body>
</html>		