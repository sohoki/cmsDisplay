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
    <script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/operManage/xmlList.do">	    	   
	<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
	<input type="hidden" name="mode" id="mode" >
	<input type="hidden" name="xmlSeq" id="xmlSeq" >	    
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
					<!--//상단 탭메뉴-->
					<ul class="topMenu" >
						<li class="active"><a href="/backoffice/sub/operManage/xmlList.do" class="conMutiList">전문관리</a></li>
						<li><a href="/backoffice/sub/operManage/sendResultList.do" class="xmlList">전문통신현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--전문관리-->
						<div class="con_title whiteBox ">
							<h2><img src="/img/mediaText.png"> 전체 전문관리 리스트입니다.</h2>
							    <div class="searchBox">
									<span>총 : ${totalCnt}개</span>
									<select name="pageUnit" id="pageUnit" >
										<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
										<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
										<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
										<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>
									</select>
									<form:select path="workGubun" id="workGubun" title="전문구분">
											<form:option value="" label="--선택하세요--"/>
											<form:options items="${selectWorkgubun}" itemValue="code" itemLabel="codeNm"/>
									</form:select>	
									<select name="searchCondition"  id="searchCondition">
										<option value>선택</option>
										<option value="xmlProcessName" <c:if test="${searchVO.searchCondition == 'xmlProcessName' }"> selected="selected" </c:if>>전문명</option>
										<option value="processRemark" <c:if test="${searchVO.searchCondition == 'processRemark' }"> selected="selected" </c:if>>전문설명</option>
									</select>

									<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
									<a href="javascript:search_form()" class="yellowBtn">검색</a>				
								       <div class="footerBox">
									<a href="javascript:view_Xml('Ins', '0')" class="yellowBtn">등록</a>			
								</div>	
							</div>
							<!--테이블시작-->
							<table>
								<thead>
									<tr>							
										<th>번호</th>
										<th>구분</th>
										<th>프로세스 ID</th>
										<th>프로세스 업무</th>
										<th>요청전문</th>									
										<th>결과전문</th>
										<th>확인</th>	
										<th>삭제</th>	
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${resultList }" var="xmlinfo" varStatus="status">
									<tr>
										<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
										<td style="text-align:left"><A href="javascript:view_Xml( 'Viw', '${xmlinfo.xmlSeq  }' )">  ${xmlinfo.codeNm  }</A></td>
										<td style="text-align:left"><A href="javascript:view_Xml( 'Viw', '${xmlinfo.xmlSeq  }' )"  title="<c:out value="${  xmlinfo.xmlProcessName }" /> ">  <c:out value="${ fn:substring( xmlinfo.xmlProcessName, 0, 20) }" /> </A></td>
										<td style="text-align:left"><c:out value="${ fn:substring( xmlinfo.processRemark, 0, 15) }" /> </td>
										<td class="md_btn"><a href="javascript:open_JsonView('I','${xmlinfo.xmlSeq  }' )" class="brownBtn">미리보기</a></td>							
										<td class="md_btn"><a href="javascript:open_JsonView('O','${xmlinfo.xmlSeq  }' )" class="brownBtn">미리보기</a></td>
										<td>
										<c:choose>
										   <c:when test="${xmlinfo.testOk eq 'Y' }">확인</c:when>
										   <c:otherwise>미확인</c:otherwise>
										</c:choose>		
										</td>
										<td class="md_btn"><a href="javascript:view_Xml( 'Del', '${xmlinfo.xmlSeq  }' )"" class="delkey">삭제</a></td>
									</tr>			
									</c:forEach>
									<c:if test="${fn:length(resultList) == 0 }">
									<tr>
									  <td colspan="8">등록된 전문이  없습니다</td>
									</tr>
									</c:if>
								</tbody>
							</table>										
							<div class="pageFooter">
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
								<div class="clear"></div>	
							</div>				
						</div>			
					</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
</form:form>
    <script type="text/javascript">
       function view_Xml(code, code1){
    	  $('#mode').val(code);
 		  $('#xmlSeq').val(code1);
 		  
 		  if (code == "Ins"){			    			 
 			  $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlDetail.do").submit(); 			  
 		  }else if (code == "Del"){
 			   if (confirm("삭제 하시겠습니까") == true){
 	    		   $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlDel.do").submit(); 	    		   
 	    	   }else {
 	    		   return ;    		   
 	    	   } 			  
 		  }else {
 			  $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlView.do").submit();			  
 		  }		    	   
       }
       
     //검색 
     function search_form(){
    	 $(":hidden[name=pageIndex]").val("1");
         $("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlList.do").submit();
     }   
  	 function linkPage(pageNo) {
 		$(":hidden[name=pageIndex]").val(pageNo);		
 		$("form[name=regist]").attr("action", "/backoffice/sub/operManage/xmlList.do").submit();
 	 }	
     function open_XmlView(code, code1){
 	      var url = "";
		  if (code == "I")
	      {
	           url = "/backoffice/sub/operManage/xmlAuthView.do?xmlSeq="+code1;
	      }else {
	           url = "/backoffice/sub/operManage/xmlAuthReq.do?xmlSeq="+code1;
		  }
          window.open(url,"xml전문확인", 'width=600,height=860,top=100,left=650,scrollbars=auto')	   
    	   
       }
       function open_JsonView(code, code1){
  	      var url = "";
 		  if (code == "I")
 	      {
 	           url = "/backoffice/sub/operManage/jsonAuthView.do?xmlSeq="+code1;
 	      }else {
 	           url = "/backoffice/sub/operManage/jsonAuthReq.do?xmlSeq="+code1;
 		  }
           window.open(url,"xml전문확인", 'width=600,height=860,top=100,left=650,scrollbars=auto')	        	   
        }
    </script>
    <script type="text/javascript">
    
    /* $(document).ready(function() {        	    	    
    	if ("${status}" != "") {
    		if ("${status}" == "SUCCESS") {
    			alert("정상 처리 되었습니다");    			    			
    		}else{
    			alert("작업 도중 문제가 발생 하였습니다.");
    		}    		
			 location.href = "/backoffice/sub/equiManage/did_groupList.do";
    	}    	        
    });*/
    </script>
</body>
</html>		