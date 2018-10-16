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
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>


<form name="listForm" action="<c:url value='/backoffice/popup/zipSearch.do'/>" method="post">
    <table style="width:550px" cellpadding="8" class="table-search" border="0">
        <tr>
            <td style="width:35%" class="title_left">
                <img src="<c:url value='/images/tit_icon.gif'/>" width="16" height="16" hspace="3" align="middle" alt="제목"/> 우편번호 찾기
            </td>
            <td style="width:60%" class="title_right">
                동명을 입력하시오. <input name="searchKeyword" id="searchKeyword" type="text" size="20" value="${searchVO.searchKeyword}"  maxlength="20" title="동명"/> 
            </td>
            <th style="width:5%">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                        <td><img src="<c:url value='/images/bu2_left.gif'/>" alt="조회" width="8" height="20" /></td>
                        <td class="btnBackground" nowrap="nowrap">
                            <input type="submit" value="조회" onclick="javascript:fn_search_Zip();" class="btnNew" style="height:20px;width:26px;padding:0px 0px 0px 0px;" >
                        </td>
                        <td><img src="<c:url value='/images/bu2_right.gif'/>" alt="조회" width="8" height="20" /></td>
                        <td width="10"></td>
                    </tr>
                </table>
            </th>  
        </tr>
    </table>
    
    <table style="width:550px" cellpadding="0" class="table-line" border="0" summary="우편번호 건색 결과를 알려주는 테이블입니다.우편번호 및 주소 내용을 담고 있습니다">
        <thead>
            <tr>  
            	<th class="title" style="width:25%" scope="col" nowrap="nowrap">우편번호</th>
            	<th class="title" style="width:75%" scope="col" nowrap="nowrap">주소</th>
            </tr>
        </thead>    
        <tbody>
            <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
                <tr style="cursor:pointer;cursor:hand;" onclick="javascript:fn_return_Zip( '${fn:substring(resultInfo.zip, 0,3)}','${fn:substring(resultInfo.zip, 3,6)}', '${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.rdmn} ${resultInfo.bdnbrMnnm} ${resultInfo.bdnbrSlno} ${resultInfo.buldNm}');">
                	<td class="lt_text3" nowrap="nowrap" >
                	<c:out value='${fn:substring(resultInfo.zip, 0,3)}'/>-<c:out value='${fn:substring(resultInfo.zip, 3,6)}'/></td>
                	<td class="lt_text" nowrap="nowrap" >${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.rdmn} ${resultInfo.bdnbrMnnm} ${resultInfo.bdnbrSlno} ${resultInfo.buldNm}</td>
                </tr>   
            </c:forEach>
        </tbody>  
    </table>
    
    <table style="width:550px" cellspacing="0" cellpadding="0" border="0">
        <tr>
        	<td height="3px"></td>
        </tr>
    </table>
    
    
     
    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>

<script type="text/javascript">
   function fn_search_Zip(){
	   if ($("#searchKeyword").val == ""  ){
		    alert("찾으실 동명을 입력해 주세요");
		    $("#searchKeyword").focus();
		    return;
	   }else {
		   $("form[name=listForm]").attr("action", "/backoffice/popup/zipSearch.do").submit();
			return;
	   }
   }
   //
   function fn_return_Zip(zip1, zip2, addr1 ){
	   $(opener.document).find("#centerZipcode1").val(zip1);
	   $(opener.document).find("#centerZipcode2").val(zip2); //방식 3
	   $(opener.document).find("#centerAddr1").val(addr1); //방식 3
	   self.close();	   
   }
</script>
</body>
</html>		