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
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
	
	<link href="<c:url value='/'/>css/reset.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/needpopup.min.css" rel="stylesheet" type="text/css" >
	
	<link rel="stylesheet" type="text/css" href="/css/calendar.css">
    <script type="text/javascript" src="/js/new_calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    
	<style type="text/css">
		#wrap {
			min-height: 1080px;
		}
	</style>
	</head>
<body>

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodPlayInfo.do">
    <form:hidden path="pageIndex" id="pageIndex"/>		
    <input type="hidden" name="mode" id="mode" >
    <input type="hidden" name="searchCenterId" id="searchCenterId" >
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
							<h2><img src="/img/list.png"> 기본음원 재생 파일 현황 입니다.</h2>	
							<div class="searchBox">
							       
							       
								<span>총 : ${totalCnt }개</span>
								<input type="text" id="searchStartDay" name="searchStartDay" value="${searchVO.searchStartDay}">
								<input type="text" id=searchEndDay name="searchEndDay" value="${searchVO.searchEndDay}">
								<a href="#" data-needpopup-show="#small-popup-reg"  class="yellowBtn">지점 선택</a>
								<a href="javascript:search_form()" class="yellowBtn">검색</a>
								<div class="footerBox">
								<a href="javascript:fn_excelDown('1')" class="yellowBtn">Excel Down</a>			
								<a href="javascript:fn_excelDown('2')" class="yellowBtn">Excel Down 지점필드 제외</a>
								<div class="clear"></div>			
							</div>
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										<th>지점명</th>
										<th>일자</th>
										<th>파일명</th>
										<th>앨범</th>
										<th>가수</th>
										<th>음원재생</th>			
									</tr>
								</thead>
								<tbody>
								
								  <c:forEach items="${resultList}" var="basicPlay" varStatus="status">
									<tr>
										<td>${basicPlay.centerNm }</td>
										<td>${basicPlay.playDay }</td>
										<td>${basicPlay.orignlFileNm }</td>
										<td>${basicPlay.fileAlbum }</td>
										<td>${basicPlay.singerNm }</td>
										<td>${basicPlay.playCnt }</td>
									</tr>			
								  </c:forEach>
								</tbody>
							</table>						
									
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
	
</form:form>
<div id='small-popup-reg' class="needpopup" style="width:801px;">
	   
	   <div class="contents" style="width:800px;">
			<div class="header">
				<h2><span id="spTitle" style="color: #fff;font-size: 20px;">지점 선택</span></h2>				
			</div>
			<div class="textT">
				<!--테이블시작-->	    
					<table>
						<!--내용시작-->
						<tbody class="text_left" style="background-color:#fff;">
						      <tr>
                                     <th colspan='4' style="text-align:left"> 
                                     </th>                                     
                                     <th>
                                     <input type="checkbox" name="checkAll" id="checkAll" onChange="javascript:allCheck();">전체선택
                                     <a href="javascript:fn_centerSearch()" class="yellowBtn">검색</a>
                                     </th>
                              </tr>
						      <tr>
								<c:forEach items="${centerList}" var="centerList" varStatus="status">
								   <c:if test="${status.index%5==0}">
									</tr><tr>
									</c:if>
                                    <td>
                                    <input type='checkbox' name='ck_CenterId' value='${centerList.centerId}' id='ck_${centerList.centerId}'> 
                                    ${centerList.centerNm}
                                    </td>
								</c:forEach>
							   </tr>
						</tbody>
					</table>
			</div>
		</div>
	</div>
   <script type="text/javascript" src="/js/needpopup.min.js"></script>
   <script type="text/javascript">
    $(document).ready(function() {
		 var clareCalendar = {
		 monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		 weekHeader: 'Wk',
		 dateFormat: 'yymmdd', //형식(20120303)
		 autoSize: false, //오토리사이즈(body등 상위태그의 설정에 따른다)
		 changeMonth: true, //월변경가능
		 changeYear: true, //년변경가능
		 showMonthAfterYear: true, //년 뒤에 월 표시
		 buttonImageOnly: true, //이미지표시
		 buttonText: '달력선택', //버튼 텍스트 표시
		 buttonImage: '/images/calendar.gif', //이미지주소
		 showOn: "both", //엘리먼트와 이미지 동시 사용(both,button)
		 showAnim: "slide",
		 yearRange: '1990:2099', //1990년부터 2099년까지
		 beforeShow: function () { 
			 window.resizeTo(550,500);
		 },
		 beforeHide: function () { 
			 window.resizeTo(550,180);
		 }
		
	   };	
		 needPopup.config.custom = {
					'removerPlace': 'outside',
					'closeOnOutside': false,
					onShow: function() {},
					onHide: function() {}
		 };
		 needPopup.init();
	  $("#searchStartDay").datepicker(clareCalendar);
	  $("#searchEndDay").datepicker(clareCalendar);
	  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
	  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
    });  
    function fn_excelDown(code){
    	var url = ""
    	if (code == "1"){
    		url = "/backoffice/sub/brodManage/brodPlayInfoExelDown.do";
    	}else {
    		url = "/backoffice/sub/brodManage/brodPlayInfoNotCenterExelDown.do";
    	}
    	$("form[name=regist]").attr("action", url).submit();   
    }
    function fn_centerSearch(){
    	 var cnt = $("input[name=ck_CenterId]:checkbox:checked").length;
	      var del_atch = "";			
	      if (cnt < 1) {
			 alert("하나 이상의 체크를 선택 하셔야 합니다");
	      } else {
			  for (var i = 0; i < $("input[name=ck_CenterId]:checkbox").length; i++) {
					if (document.getElementsByName("ck_CenterId")[i].checked == true) {						
						del_atch = del_atch + "," + document.getElementsByName("ck_CenterId")[i].value;						
					}
			  }
			  $("#searchCenterId").val(del_atch.substring(1));
	      }
	      search_form();
    }
    function allCheck(){		 
		 //좌측 체크 박스
		 if ($("#checkAll").prop("checked")) {
				$("input[name=ck_CenterId]").prop("checked", true);
		 } else {
				$("input[name=ck_CenterId]").prop("checked", false);
		 }	 
	 }
    function search_form(){
    	$("form[name=regist]").submit();
    	$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodPlayInfo.do").submit();
    }
    
    
	</script>

</body>
</html>