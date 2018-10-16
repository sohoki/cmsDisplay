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
	<style type="text/css">
		#wrap {
			min-height: 1080px;
		}
	</style>
	</head>
<body>

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodContentList.do">
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
							<h2><img src="/img/list.png"> 총 등록된 콘텐츠 리스트입니다..</h2>	
							<div class="searchBox">
							       
							       
									<span>총 : ${totalCnt }개</span>
									<select name="pageUnit" id="pageUnit" class="blan">								
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
									</select>
									<select id="centerGubun" name="centerGubun" class="blan">
										<option value="">전체 콘텐츠</option>
										<option value="content" <c:if test="${searchVO.centerGubun == 'content' }"> selected="selected" </c:if>>일반콘텐츠</option>
  									    <option value="center" <c:if test="${searchVO.centerGubun == 'center' }"> selected="selected" </c:if>>지점콘텐츠</option>
									</select>
									<select id="secGubun" name="secGubun" class="blan">
										<option value="">전체 스케줄</option>
										<option value="SECGUBUN01" <c:if test="${searchVO.secGubun == 'SECGUBUN01' }"> selected="selected" </c:if>>일반스케줄</option>
  									    <option value="SECGUBUN02" <c:if test="${searchVO.secGubun == 'SECGUBUN02' }"> selected="selected" </c:if>>이벤트스케줄</option>
									</select>	
									<select id="searchCondition" name="searchCondition" class="blan">
										<option value="CON_NM" <c:if test="${searchVO.searchCondition == 'CON_NM' }"> selected="selected" </c:if>>콘텐츠명</option>
  									    <%-- <option value="centerId" <c:if test="${searchVO.searchCondition == 'centerId' }"> selected="selected" </c:if>>기본음원</option> --%>
									</select>
									<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
									<a href="javascript:search_form()" class="yellowBtn">검색</a>
								<div class="footerBox">
								<a href="javascript:brodReg('Ins','0')" class="yellowBtn">등록</a>			
								<a href="javascript:delContent()" class="grayBtn">삭제</a>	
								<div class="clear"></div>			
							</div>
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										<th>콘텐츠타입</th>
										<th>콘텐츠명</th>
										<th>스케줄</th>
										<th>기본음원</th>
										<!-- <th>반복재생</th> -->
										<th>사용유무</th>
										<!-- <th>연결수량</th> -->
										<th>콘텐츠복사</th>
										<th><input type="checkbox" id="checkAll" onClick="javascript:ch_all();"></th>				
									</tr>
								</thead>
								<tbody>
								
								  <c:forEach items="${resultList}" var="brodInfo" varStatus="status">
									<tr>
										<%-- <td>${brodInfo.brodCode }</td> --%>
										<td>
											<c:if test="${brodInfo.centerId ne null}">
											[지점 콘텐츠]
											</c:if>	
											<c:if test="${brodInfo.centerId eq null}">
											[일반 콘텐츠]
											</c:if>
										</td>
										<td style="text-align:left">		
										&nbsp;<a href="javascript:brodReg('Edt','${brodInfo.brodCode}')">${brodInfo.brodName}</a>
										</td>
										<td><a href="javascript:brodReg('Edt','${brodInfo.brodCode}')">${brodInfo.secGubun}</a></td>
										<td>${brodInfo.orignlFileNm }</td>
										<!-- <td>${brodInfo.codeNm }</td> -->
										<td>							
										<c:choose>
										   <c:when test="${brodInfo.brodUseYn eq 'Y' }">사용</c:when>
										   <c:otherwise>사용 안함</c:otherwise>
										</c:choose>							
										</td>
										<%-- <td>
										<c:if test="${brodInfo.centerId eq null}">
										${brodInfo.centerRelCnt }
										</c:if>
										</td> --%>
										<!-- -->
										<td>
										<c:if test="${brodInfo.centerId eq null}">
										<a class="brownBtn" href="javascript:copy_Content('${brodInfo.brodCode }')">복사</a>
										</c:if>
										</td>
										<td>
										<c:if test="${brodInfo.centerId eq null}">
										<input type="checkbox" id="brod_Code" name="brod_Code" value="${brodInfo.brodCode }">
										</c:if>
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
</form:form>
    <script type="text/javascript">
    $(document).ready(function() {
		if ("${status}" != "" ){
			if ("${status}" == "SUCCESS" ){
				alert("정상처리 되었습니다");					
			}else  {
				alert("처리 도중 문제가 발생 하였습니다.");				
			}	
		}				
	});	     
    function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	}   
    function search_form(){
    	$("form[name=regist]").submit();
    	$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentList.do").submit();
    }
    function copy_Content(code){
    	var url = "/backoffice/sub/brodManage/brodContentCopy.do?brodCode="+code;	      
	    window.open(url,"contentSpReg", 'width=800,height=550,top=100,left=650,scrollbars=auto');
    }
    function brodReg(code, code1){
    	$('#mode').val(code);
		$('#brodCode').val(code1);
		if (code == "Ins"){
			  location.href="/backoffice/sub/brodManage/brodContentDetail.do?mode=Ins&brodCode="+code1;
			  //$("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentDetail.do").submit();		 			  
		}else {
			  $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/brodContentView.do").submit();			  
		}		  	
    }
    function brodReSet(){
    	var cnt = $("input[name=brod_Code]:checkbox:checked").length;
    	var del_atch = "";
    	if (cnt< 1){
    		alert("하나 이상의 체크를 선택 하셔야 합니다");
    	}else {
    		for (var i= 0; i < document.getElementsByName("brod_Code").length; i++){
    			if (document.getElementsByName("brod_Code")[i].checked == true){
    				del_atch = del_atch+","+document.getElementsByName("brod_Code")[i].value;	
    			}    			
    		}
    		apiExecute(
   				   "POST", 
   				   "/backoffice/sub/brodManage/brodContentDel.do",
    					{
   					 brodCode : del_atch
    					},
    					null,				
    					function(result) {							
     						if (result != null) {	         							
     							if (result == "O"){
     								alert("재 전송 요청이 되었습니다.");
     								document.location.reload();
     							}else {
     								alert("재전송 요청시 문제가 발생 하였습니다.");
     								document.location.reload();
     							}
     						}
     					},
    					null,
    					null
  				);	
    	}
    }
    //삭제
    function delContent(){
    	var cnt = $("input[name=brod_Code]:checkbox:checked").length;
    	var del_atch = "";
    	if (cnt< 1){
    		alert("하나 이상의 체크를 선택 하셔야 합니다");
    	}else {
    		for (var i= 0; i < document.getElementsByName("brod_Code").length; i++){
    			if (document.getElementsByName("brod_Code")[i].checked == true){
    				del_atch = del_atch+","+document.getElementsByName("brod_Code")[i].value;	
    			}    			
    		}     		
    		apiExecute(
  				   "POST", 
  				   "/backoffice/sub/brodManage/brodContentDel.do",
   					{
  					 brodCode : del_atch
   					},
   					null,				
   					function(result) {							
    						if (result != null) {	         							
    							if (result == "O"){
    								alert("삭제 되었습니다.");
    								document.location.reload();
    							}else {
    								alert("삭제 되었습니다.");
    								document.location.reload();
    							}
    						}
    					},
   					null,
   					null
 				);	    		
    		
    	}
    }
    function ch_all(){
		if ($("#checkAll").prop("checked")){
			$("input[type=checkbox]").prop("checked", true);
		}else {
			$("input[type=checkbox]").prop("checked", false);
		}
	}
	</script>

</body>
</html>