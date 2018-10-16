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
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/playContentList.do">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
        
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
						<li class="active"><a href="/backoffice/sub/brodManage/playContentList.do" class="playMedia">음원파일관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodBasic.do" class="playMedia">기본음원관리</a></li>
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
							<h2><img src="/img/list.png"> 총 등록된 파일리스트입니다.</h2>	
							<div class="searchBox">
									<span>총 : ${totalCnt }개</span>
									<select name="pageUnit" id="pageUnit">								
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
									</select>	
									
									<select id="fileGubun" name="fileGubun">
									    <option value="">선택</option>
									    <option value="fileGubun01" <c:if test="${searchVO.fileGubun == 'fileGubun01' }"> selected="selected" </c:if>>일반음원</option>
									    <option value="fileGubun02" <c:if test="${searchVO.fileGubun == 'fileGubun02' }"> selected="selected" </c:if>>기초음원</option>
									</select>
									<select id="searchCondition" name="searchCondition" class="blan">
										<option value="">선택</option>
										<option value="orignlFileNm" <c:if test="${searchVO.searchCondition == 'orignlFileNm' }"> selected="selected" </c:if>>파일명</option>
  									    <option value="streFileNm" <c:if test="${searchVO.searchCondition == 'streFileNm' }"> selected="selected" </c:if>>실파일명</option>
									</select>	
									<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
									<a href="javascript:search_form()" class="yellowBtn">검색</a>
								<div class="footerBox">
								<a href="javascript:mp3Upload('Ins','0')" class="yellowBtn">등록</a></a>
								<a href="javascript:fileState('Y')" class="grayBtn">사용</a></a>
								<a href="javascript:fileState('N')" class="grayBtn">사용안함</a></a>
								<a href="javascript:delMp3()" class="grayBtn">삭제</a>				
								<div class="clear"></div>										
							</div>
							<!--테이블시작-->
							<table>								
								<thead>
									<tr>							
										<th>번호</th>
										<th>음원명</th>
										<th>실제파일명</th>
										<th>재생시간</th>
										<th>사용유무</th>
										<th>
										<input type="checkbox" id="checkAll" onClick="javascript:ch_all();">
										</th>				
									</tr>
								</thead>
								<tbody>
								  <c:forEach items="${resultList}" var="fileDetail" varStatus="status">
									<tr>
										<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
										<td style="text-align:left"><a href="javascript:mp3Upload('Edt','${fileDetail.atchFileId }')">${fileDetail.orignlFileNm }</a></td>
										<td><a href="javascript:mp3Upload('Edt','${fileDetail.atchFileId }')">${fileDetail.streFileNm }</a></td>
										<td>${fileDetail.fileThumnail }</td>
										<td>							
										<c:choose>
										   <c:when test="${fileDetail.useYn eq 'Y' }">사용</c:when>
										   <c:otherwise>사용 안함</c:otherwise>
										</c:choose>							
										</td>
										<td><input type="checkbox" id="file_ID" name="file_ID" value="${fileDetail.atchFileId }"></td>
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
    function fileState(code){
    	var cnt = $("input[name=file_ID]:checkbox:checked").length;
    	var del_atch = "";
    	if (cnt< 1){
    		alert("하나 이상의 체크를 선택 하셔야 합니다");
    	}else {
    		for (var i= 0; i < document.getElementsByName("file_ID").length; i++){
    			if (document.getElementsByName("file_ID")[i].checked == true){
    				del_atch = del_atch+","+document.getElementsByName("file_ID")[i].value;	
    			}    			
    		}     		
    		apiExecute(
  				   "POST", 
  				   "/backoffice/sub/brodManage/playContentStateChange.do",
   					{
 					  atchFileId : del_atch,
 					  useYn : code
   					},
   					null,				
   					function(result) {							
    						if (result != null) {
    							if (result == "0"){
    								alert("정삭적으로 변경 되었습니다.");
    								document.location.reload();
    							}else {
    								alert("변경시 문제가 생겼습니다.");
    								document.location.reload();
    							}
    						}
    					},
   					null,
   					null
 				);	    		
    		
    	}
    }
    function delMp3(){
    	var cnt = $("input[name=file_ID]:checkbox:checked").length;
    	var del_atch = "";
    	if (cnt< 1){
    		alert("하나 이상의 체크를 선택 하셔야 합니다");
    	}else {
    		for (var i= 0; i < document.getElementsByName("file_ID").length; i++){
    			if (document.getElementsByName("file_ID")[i].checked == true){
    				del_atch = del_atch+","+document.getElementsByName("file_ID")[i].value;	
    			}    			
    		}     		
    		apiExecute(
  				   "POST", 
  				   "/backoffice/sub/brodManage/playContentDel.do",
   					{
 					  atchFileId : del_atch
   					},
   					null,				
   					function(result) {							
    						if (result != null) {	         							
    							if (result == "O"){
    								alert("정삭적으로 삭제 되었습니다.");
    								document.location.reload();
    							}else {
    								alert("삭제시 문제가 생겼습니다.");
    								document.location.reload();
    							}
    						}
    					},
   					null,
   					null
 				);	    		
    		
    	}
    	
    }
    function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);				
		$("form[name=regist]").submit();
	}
    function mp3Upload(code, fileId){
    	var url = "/backoffice/sub/agentManage/mp3Detail.do?mode="+code+"&atchFileId="+fileId;	      
        window.open(url,"mp3Form", 'width=800,height=550,top=100,left=650,scrollbars=auto')
    }
	  //검색
	function search_form(){		  
		  $("form[name=regist]").attr("action", "/backoffice/sub/brodManage/playContentList.do").submit();		  
	}
	//전체 선택 및 
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