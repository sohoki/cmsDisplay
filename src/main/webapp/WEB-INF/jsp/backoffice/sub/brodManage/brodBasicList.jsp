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
    <style>
		#wrap { min-height :1080px;}
	</style>
</head>
<body>

<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodBasic.do">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" id="basicCode" name="basicCode" >
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
						<!--음원파일관리-->
						<div class="con_title whiteBox ">
							<h2><img src="/img/list.png"> 총 등록된 콘텐츠 리스트입니다..</h2>	
							<div class="searchBox">
							       							       
									<span>총 : ${totalCnt }개</span>
									<select name="pageUnit" id="pageUnit" class="blan">								
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
									</select>
									<select id="searchCondition" name="searchCondition" class="blan">
										<option value="BASIC_GROUP_NM" <c:if test="${searchVO.searchCondition == 'BASIC_GROUP_NM' }"> selected="selected" </c:if>>기본음원명</option>
  									    <%-- <option value="centerId" <c:if test="${searchVO.searchCondition == 'centerId' }"> selected="selected" </c:if>>센터명</option> --%>
									</select>
									<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
									<a href="javascript:search_form()" class="yellowBtn">검색</a>
								<div class="footerBox">
								<a href="#" class="yellowBtn" id="dialog" data-needpopup-show="#small-popup-reg" onClick="javascript:brodBasicReg('Ins','0')">등록</a>			
								<a href="javascript:delContent()" class="grayBtn">삭제</a>	
								<div class="clear"></div>			
							</div>
							</div>
							<!--테이블시작-->
							<table id="basicGroupList">
								<thead>
									<tr>
										<th>기본음원관리</th>							
										<th>기본음원명</th>
										<th>연결지점수</th>
										<th>등록일자</th>
										<th>음원상세리스트</th>
										<th>배포현황</th>										
										<!--<th>기초음원복사</th>-->
										<th><input type="checkbox" id="checkAll" onClick="javascript:ch_all();"></th>				
									</tr>
								</thead>
								<tbody>								
								  <c:forEach items="${resultList}" var="brodBasicInfo" varStatus="status">
									<tr>
										<td>
											<a href="javascript:brodBasicReg('Edt_N','${brodBasicInfo.basicCode}')" class="yellowBtn">신규음원관리</a>
										</td>	
										<td>
											<a href="javascript:brodBasicReg('Edt','${brodBasicInfo.basicCode}')">${brodBasicInfo.basicGroupNm}</a>
										</td>								
										<td>
										    <a href="javascript:brodBasicList('Cnt','${brodBasicInfo.basicCode}')">${brodBasicInfo.basicGroupCnt}</a>
										</td>
										<td>${brodBasicInfo.lastUpdtPnttm }</td>
										<td>
										  <a class="yellowBtn" onClick="javascript:js_basicView('${brodBasicInfo.basicCode}')" data-needpopup-show="#small-popup-preview">리스트 미리보기</a>
										</td>
										<td>
										  <a class="yellowBtn" onClick="javascript:brodBasicList('Sch','${brodBasicInfo.basicCode}')" data-needpopup-show="#small-popup-preview">현황 미리보기</a>
										</td>
										<!--<td>										
										<a href="#" onClick="javascript:copy_Content('${brodBasicInfo.basicCode }')" data-needpopup-show="#small-popup">[복사]</a>										
										</td>-->
										<td>
										<input type="checkbox" id="basic_Code" name="basic_Code" value="${brodBasicInfo.basicCode }">										
										</td>
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
	
	<div id='small-popup-reg' class="needpopup">
	   
	   <div class="contents">
			<div class="header">
				<h2><span id="spTitle" style="color: #fff;font-size: 20px;">기본음원 등록</span></h2>				
			</div>
			<div class="textT">
				<!--테이블시작-->	    
					<table>
						<!--내용시작-->
						<tbody class="text_left" style="background-color:#fff;">
							<tr>
								<th><span id="spInputTitle">기본음원명</span></th>
								<td style="border:none;"><input type="text" id="basicGroupNm" name="basicGroupNm" max="50" size="50" title="기초음원명"></td>
								<td style="border:none;"><a href="javascript:check_form();" class="yellowBtn" id="btnUpdate">등록</a></td>
							</tr>					
						</tbody>
					</table>
				<!-- <div class="footerBox">
								
				</div>		 -->
			</div>
		</div>
	</div>
	
	<div id='small-popup-preview' class="needpopup">
	   
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
	
 </form:form>		
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
		$('#basicGroupList tr').css("background-color","");
	});
	function delContent(){
		var cnt = $("input[name=basic_Code]:checkbox:checked").length;
		 var del_atch = "";			
		 if (cnt < 1) {
				alert("하나 이상의 체크를 선택 하셔야 합니다");
		 } else {
			 for (var i = 0; i < document.getElementsByName("basic_Code").length; i++) {
					if (document.getElementsByName("basic_Code")[i].checked == true) {						
						del_atch = del_atch + "," + document.getElementsByName("basic_Code")[i].value;						
					}
		 	}
			 apiExecute(
						"POST", 
						"/backoffice/sub/brodManage/brodBasicDel.do",
						{						
							delBasciSeq : del_atch.substring(1)						
						},
						null,				
						function(result) {								
							if (result == "T"){
								alert("정상적으로 처리 되었습니다.");
								$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodBasic.do").submit();																
							}else {
								alert("처리 도중 문제가 발생 하였습니다.");
							}							
						},
						null,
						null
					);	 
			 
		 }
	}
	function copy_Content(code){
		$("#mode").val("Cpy");
		$("#basicCode").val(code);
		$("#spTitle").html("기초 음원 복사");
		$("#spInputTitle").html("복사음원명");
	}	
	function search_form(){
	 	$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodBasic.do").submit();
	}
	function check_form(){
		if (any_empt_line_id("basicGroupNm", "기초음원명을 입력 하지 않았습니다.") == false) return;
		apiExecute(
				"POST",
				"/backoffice/sub/brodManage/brodBasicUpdate.do",
				{
					basicCode : $("#basicCode").val(),
					basicGroupNm : $("#basicGroupNm").val(),
					mode : $("#mode").val()
				},
				null,
				function(result) {
					if (result != null) {
						if (result != "F"){
							//상세 페이지 보기 
							brodBasicReg('Edt', result);														
						}else {
							alert("작업 도중 문제가 발생 하였습니다.");
						}
					}
				}, 
			null, 
			null);
	}
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
						$("#spTitle").html("기본 음원 리스트");
						$("#subSpTitle").html("* 랜덤재생으로 리스트 순서는 무관합니다.");
						var timeHtml = "";
						for (var i=0; i< result.resultMap.length; i++) {
							var obj = result.resultMap[i];	
							timeHtml += "<tr><td>"+obj.orignlFileNm+"</td>";
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
	//등록 
	function brodBasicReg(code, code1){	   
	   $("#mode").val(code);
	   if (code == "Ins"){
		   $("#spTitle").html("기본음원 등록");
		   $("#spInputTitle").html("기본음원명");
	   }else if (code == "Edt_N"){
		   $("#basicCode").val(code1);		   
		   $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodBasicTimeDetail.do").submit();
	   } else {
		   $("#basicCode").val(code1);		   
		   $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodBasicDetail.do").submit();
	   }
	}
	function allCheck() {
			if ($("#checkAll").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
	}
	function linkPage(pageNo) {
			$(":hidden[name=pageIndex]").val(pageNo);
			$("form[name=regist]").submit();
	}	
	</script>
	
</body>
</html>