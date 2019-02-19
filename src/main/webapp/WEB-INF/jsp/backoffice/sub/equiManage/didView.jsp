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
<link href="<c:url value='/'/>css/needpopup.min.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/popup.js"></script>
<script type="text/javascript" src="/js/aten-common.js"></script>
<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<span id="backgroundgif"></span>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/updateGroup.do">	    
<form:hidden path="mode" id="mode" />		    
<form:hidden path="didId" id="didId" />
<form:hidden path="didMac" id="didMac" />	
<form:hidden path="schCnt" id="schCnt" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${regist.pageIndex }">
<input type="hidden" name="pageUnit" id="pageUnit" value="${regist.pageUnit }">
<form:hidden path="searchCondition" id="searchCondition" />
<form:hidden path="searchKeyword" id="searchKeyword" />
<form:hidden path="menuGubun" id="menuGubun" />		
<form:hidden path="role_Code" id="role_Code" />		
<form:hidden path="author_Code" id="author_Code" />
<form:hidden path="groupCode" id="groupCode" />
		
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
							<a href="/backoffice/sub/equiManage/didList.do" class="yellowBtn">목록</a> 
							<a href="javascript:view_Did();" class="yellowBtn">수정</a> 
							<a href="javascript:del_Did();" class="grayBtn">삭제</a>
						</div>
						<div class="clear"> </div>
					</div>	
							
					   <table>
							<tbody>
								<tr>
									<th colspan="6">단말기 정보</th>
								</tr>
								<tr>	
									<th>단말명</th>
									<td><c:out value="${regist.didNm}" /></td>
									<th>단말 ID</th>
									<td><c:out value="${regist.didId}" /></td>
									<th>단말 MAC</th>
									<td><c:out value="${regist.didMac}" /></td>
									
								</tr>
								<tr>
									<th>단말 IP</th>
									<td><c:out value="${regist.didIpaddr}" /></td>
									<th>IP타입</th>
									<td><c:out value="${regist.didIptype}" /></td>
									<th>단말 OS</th>
									<td><c:out value="${regist.didOs}" /></td>
								</tr>
								<tr>
									<th>관리부서</th>
									<td><c:out value="${regist.roleNm}" /></td>
									<th>설치지점</th>
									<td><c:out value="${regist.centerNm}" /></td>
									<th>그룹정보</th>
									<td>
									<c:if test="${regist.groupNm != null}">
										<a onclick="preview_group('${regist.groupCode}','${regist.groupNm}')" data-needpopup-show="#small-popup-preview" class="blueBtn fontSize12">${regist.groupNm}</a>
									</c:if>
									</td>
								</tr>
								<tr>	
									<th>단말타입</th>
									<td><c:out value="${regist.didModelType}" /></td>
									<th>단말형태</th>
									<td><c:out value="${regist.didType}" /></td>
									<th>운영시간</th>
									<td><c:out value="${regist.didStartTime}" /> ~ <c:out value="${regist.didEndTime}" /></td>												
								</tr>
								<tr>							
									<th>단말해상도</th>
									<td>가로:<c:out value="${regist.didWidth}" /> 세로: <c:out value="${regist.didHeight}" /></td>
									<th>마지막 접속일자</th>
									<td><c:out value="${regist.didEndContime}" /></td>
									<th>사용유무</th>
									<td><c:out value="${regist.didUseYn}" /></td>			
								</tr>
								<tr>
									<th>재부팅 처리</th>
									<td>
									<c:choose>
									   <c:when test="${regist.didOs eq '윈도우' }">
									   <a href="javascript:send_break()" class="grayBtn fontSize12 hideItem">시작</a>
									   <a href="javascript:send_break()" class="grayBtn fontSize12 hideItem">프로그램 재시작</a>
									   <a href="javascript:send_break()" class="grayBtn">장비 재부팅</a>
									   <!-- <a href="javascript:send_DID('WAK')" class="grayBtn fontSize12 hideItem">시작</a>
									   <a href="javascript:send_DID('ReS')" class="grayBtn fontSize12 hideItem">프로그램 재시작</a>
									   <a href="javascript:send_DID('Reb')" class="grayBtn">장비 재부팅</a> -->
									   </c:when>
									   <c:otherwise>
									   <a href="javascript:send_DID('Reb')" class="grayBtn fontSize12">장비 재부팅</a>
									   </c:otherwise>
									</c:choose>
									<th>단말화면확인</th>
									<td>
									<c:choose>
									   <c:when test="${regist.didOs eq '윈도우' }">
									   <a href="javascript:send_break()" class="brownBtn fontSize12">단말 원격지원</a>
									   <!-- <a href="javascript:send_DID('ReM')" class="brownBtn fontSize12">단말 원격지원</a> -->
									   </c:when>
									   <c:otherwise>
									   <!-- <a href="javascript:send_break()" class="brownBtn fontSize12">단말 화면캡처</a>&emsp;<a href="javascript:send_break()" class="brownBtn fontSize12">캡처 화면보기</a> -->
									   <a href="javascript:send_DID('Cap')" class="brownBtn fontSize12">단말 화면캡처</a>&emsp;<a href="javascript:send_break()" class="brownBtn fontSize12">캡처 화면보기</a>
									   </c:otherwise>
									</c:choose>
									</td>
									<th>단말 통신정보</th>
									<td><a href="javascript:view_DidTime();" class="blueBtn fontSize12">장비통신 이력조회</a></td>												
								</tr>
								<tr class="hideItem">
									<th>Agent ver</th>
									<td><c:out value="${regist.didSwver}" /></td>
									<th>지원해상도</th>
									<td><c:out value="${regist.didType}" /></td>				
									<th>시리얼 사용</th>
									<td><c:out value="${regist.didSerialtype}" /></td>
									<th>시리얼포트</th>
									<td><c:out value="${regist.didSerialport}" /></td>					
									<th>연결모니터수량</th>
									<td><c:out value="${regist.didMonitercnt}" /></td>
									<th>스크립트 유지시간</th>
									<td><c:out value="${regist.didTimeInterval}" />분</td>
									<th>시리얼 연동 스크립트</th>
									<td colspan="5"><c:out value="${regist.didSerialjavascript}" /></td>
								</tr>
							</tbody>
						</table>
						<div class="didConSchListBox">
							<span>
								<div onclick="didConSchList_view()" class="didConSchListBtn">
									<img src="/img/fold_view_icon.png" style="width: 24px; vertical-align: middle; margin-bottom: 4px;" />콘텐츠 및 스케줄 정보 보기
								</div>
							</span>
							<did class="didConSchList">
								
							</did>
						</div>
						<table class="hideItem">
							<tbody class="hideItem">
								<tr>
									<th colspan="4">전원관리</th>
								</tr>
								<tr>	
									<th>종료시간</th>
									<td>
									<form:input path="didEndTime" id="didEndTime" title="did 명칭" size="5" value="${regist.didEndTime }" maxLength="5"/>
									ex)21:00
									<a href="javascript:send_break()" class="grayBtn">단말기 전송</a></td>
									<!-- <a href="javascript:send_DID('Tim')" class="grayBtn">단말기 전송</a></td> -->

								</tr>	
								<tr>
									<td>
										<a href="javascript:send_break()" class="grayBtn hideItem">콘텐츠재전송</a>
										<!-- <a href="javascript:send_DID('Red')" class="grayBtn">콘텐츠재전송</a> -->
									</td>
								</tr>																		
							</tbody>
						</table>					
				</div>				
			</div>
		   </div>
		   <div class="clear"></div>
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
        <!--내용끝-->
	</div>    
	<div class="clear"></div>    
    </form:form>
    <script type="text/javascript" src="/js/needpopup.min.js"></script>
    <script type="text/javascript">
       function view_Did(){
    	   $('#mode').val('Edt');
    	   $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didDetail.do").submit();
    	   
       }
       function del_Did(){
    	   $('#mode').val('Del');
    	   $("form[name=regist]").attr("action", "/backoffice/sub/equiManage/didDeletel.do").submit();   
       }
       function view_DidTime(){
    	   var url = "/backoffice/sub/popup/pop_sendLst.do?didId=${regist.didId}";
           window.open(url,"전문현황", 'width=800,height=550,top=100,left=650,scrollbars=auto');
       }
       
       // 단말 전송 검증 안된 사항
       function send_break(){
    	   alert("서비스 점검 중 입니다.");
       }
       //단말기 전송
       function send_DID(code){
    	   
    	   var url  = "" ;
    	   if (code == "Tim"){
    		   url = "/backoffice/sub/equiManage/didEndTime.do";
    		   if ( $("#didEndTime").val() == ""  ){
    			   alert("종료시간을 넣지 않았습니다.");
    			   return;
    		   }
    	   }else if (code == "Cap"){
    		   url = "/backoffice/sub/equiManage/didCapture.do";
    	   }else if (code == 'ReS'){
    		   url = "/backoffice/sub/equiManage/didReSoot.do";
    	   }else if (code == 'ReM'){
    		   alert("협의중 입니다.");
    	   }else if (code == 'Red'){
    		   url = "/backoffice/sub/equiManage/didContentRedown.do";
    	   }else if (code == 'WAK'){
    		   url = "/backoffice/sub/equiManage/didWakeOn.do";
    	   }else {
    		   url = "/backoffice/sub/equiManage/didReBoot.do";
    	   }
    	   apiExecute(
 				  "POST", 
 				   url,
    					{
 					         didId : $("#didId").val(),
 					         didMac : $("#didMac").val(),
 					         didEndTime : $("#didEndTime").val()
    					},
    					null,				
    					function(result) {							
    						if (result == "O") {	       							
    							alert("정상적으로 전문 요청 하셨습니다");
    						}else {
    							alert("등록중 문제가 발생 하였습니다.");
    						}
    					},
    					function(request){
    						alert(request.status );	       						
    					},
    					null
  				);        
    	   
       }
       

    
      
    function didConSchList_view(){
    	loadingStart();
   		apiExecute(
   			"POST", 
   			"/backoffice/sub/equiManage/didConSchList.do",
   			{
   				didId : $("#didId").val()
   			},
   			null,				
   			function(result) {
   				
   				if (result != null) {
   					$(".didConSchList").empty();
   					var timeHtml = "";
   					if (result.resultMap.length == 0){
   						timeHtml += "<table><tbody><tr><th colspan='4'>연결 스케줄 정보<img src='/img/refresh_black_icon.png' onclick='didConSchList_view()' style='float:right; width: 20px; cursor: pointer;'/><img src='/img/fold_hide_icon.png' onclick='didConSchList_fold()' style='float:right; width: 20px; cursor: pointer;'/></th></tr>";
   						timeHtml += "<tr><th>현재 송출 스케줄(콘텐츠)</th><td><a onclick='view_connPage(&#39;ms&#39;,&#39;0&#39;)' class='blueBtn fontSize12'>스케줄 등록하기</a>&emsp;<a onclick='view_connPage(&#39;mc&#39;,&#39;0&#39;)' class='blueBtn fontSize12'>콘텐츠 등록하기</a></td>";
   						timeHtml += "<th>송출 예정 스케줄</th><td>"+$("#schCnt").val()+" 개</td></tr></tbody></table>";
   					}else if (result.resultMap != null){
   						
   						timeHtml += "<table><tbody><tr><th colspan='4'>연결 스케줄 정보<img src='/img/refresh_black_icon.png' onclick='didConSchList_view()' style='float:right; width: 20px; cursor: pointer;'/><img src='/img/fold_hide_icon.png' onclick='didConSchList_fold()' style='float:right; width: 20px; cursor: pointer;'/></th></tr>";
   						timeHtml += "<tr><th>현재 송출 스케줄(콘텐츠)</th><td>"+result.resultMap[0].schNm+"("+result.resultMap[0].conNm+")</td>";
   						timeHtml += "<th>송출 예정 스케줄</th><td>"+result.resultMap.length+" 개</td></tr></tbody></table>";
   						
   						timeHtml += "<div class='searchBox'><div style='float:right; padding: 12px 0px;'><a onclick='view_connPage(&#39;ms&#39;,&#39;0&#39;)' class='yellowBtn fontSize12'>스케줄 등록하기</a>&emsp;<a onclick='view_connPage(&#39;mc&#39;,&#39;0&#39;)' class='yellowBtn fontSize12'>콘텐츠 등록하기</a></div></div>";
   						
   						timeHtml += "<table><tbody><tr><th><span class='didConSchListColumn'>순번</span></th><th><span class='didConSchListColumn'>송출기간</span></th><th><span class='didConSchListColumn'>스케줄</span></th><th><span class='didConSchListColumn'>콘텐츠</span></th></tr>";
   						for (var i=0; i< result.resultMap.length; i++) {
   							var obj = result.resultMap[i];	
   							timeHtml += "<tr><td>"+(i+1)+"</td><td>"+obj.schStartday+" ~ "+obj.schEndday+"</td><td><a class='blueBtn fontSize12' onclick='view_connPage(&#39;s&#39;, &#39;"+obj.schCode+"&#39;)'>"+obj.schNm+"</a></td><td><a class='blueBtn fontSize12' onclick='view_connPage(&#39;c&#39;, &#39;"+obj.conSeq+"&#39;)'>"+obj.conNm+"</a></td></tr>";
   						}
   						timeHtml += "</tbody></table>";
   						
   					} else{
   						$(".didConSchList").text("데이터를  연동중 문제가 발생 하였습니다.");
   					}
   					$(".didConSchList").html(timeHtml);

   				}	
   				loadingFinish();
   			},
   			null,
   			null
   		);	
   		
    }
    
	
    function didConSchList_fold(){
    	var resetHtml = "<img src='/img/fold_view_icon.png' style='width: 24px; vertical-align: middle; margin-bottom: 4px;' />콘텐츠 및 스케줄 정보 보기";
    	$(".didConSchListBtn").attr("style", "");
    	$(".didConSchListBtn").html(resetHtml);
    	$(".didConSchList").empty();
    }
	
	function allLoadingStart(){
	   	var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
	   	$("#backgroundgif").html(loadingimg);
	}
	function allLoadingFinish(){
		$("#backgroundgif").html("");
	}

	function loadingStart(){
		var loadingimg = "<img src='/images/loading_img.gif' style='width:auto; height:186px;'></img>";
		$(".didConSchListBtn").css("height", "186px");
		$(".didConSchListBtn").html(loadingimg);
	}
	function loadingFinish(){
		$(".didConSchListBtn").hide();
	}
	
	$(document).ready(function(){
		$(".hideItem").hide();
		needPopup.config.custom = {
				'removerPlace': 'outside',
				'closeOnOutside': false,
				onShow: function() {},
				onHide: function() {}
			};
			needPopup.init();
			$('#basicGroupList tr').css("background-color","");
	});
    </script>
</body>
</html>		