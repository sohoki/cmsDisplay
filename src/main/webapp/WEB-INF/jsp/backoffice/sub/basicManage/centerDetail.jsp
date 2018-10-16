<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
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
	<script type="text/javascript" src="/js/aten-common.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>

</head>
 <style>
   table.play td {
	padding: 5px 0;
	font-size: 12px;
	border-top: none;
	border-bottom: none;
	cursor: pointer;
	text-align:center;
   }
 </style>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/basicManage/centerUpdate.do">						
                
<form:hidden path="centerId" id="centerId"/>
<form:hidden path="mode" id="mode"/>
<form:hidden path="pageIndex" />
<form:hidden path="pageSize" />
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="menuGubun" id="menuGubun" />
<input type="hidden" id="brodCodeH" name="brodCodeH" value="${regist.brodCode}" />
<input type=hidden id="startTime" value="${regist.centerStartTime }" />
<input type=hidden id="endTime" value="${regist.centerEndTime }" />

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
				        <li><a href="/backoffice/sub/basicManage/manageList.do" class="manage">관리자관리</a></li>
				        <li><a href="/backoffice/sub/basicManage/codeList.do" class="code">기초코드관리</a></li>
						<c:if test="${groupCode ne 'EMART_00000000000005' }">
						<li><a href="/backoffice/sub/basicManage/selectGroupLst.do" class="selectG">부서관리</a></li>
						</c:if>
						<li class="active"><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점관리</a></li>				
						<c:if test="${groupCode ne 'EMART_00000000000005' }">
						<li><a href="/backoffice/sub/basicManage/menuList.do" class="tmenu">메뉴관리(지점)</a></li>
						<li><a href="/backoffice/sub/basicManage/tmenuList.do" class="tmenu">메뉴관리(타입)</a></li>
						</c:if>
				<div class="clear"></div>
			</ul>
	        <div class="con">			
			 <div class="con">			
				          <div class="tableBox">		
					          <div class="con_title whiteBox ">
					          <!-- 내용 넣을 자리 시작 -->
					             
								<table >
									<tbody>
										<tr>
											<th>지점명</th>
											<td style="text-align:left">
											<form:input title="센터명" path="centerNm"  />
											</td>
											<th>관리 부서</th>
											<td style="text-align:left">
											<form:select path="roleCode" title="상위그룹">
				                                    <form:option value='' label="--선택하세요--" />
				                                    <form:options items="${selectRoleGroup}"   itemValue="groupId" itemLabel="groupNm"/>
				                                </form:select>   
											</td>
											<th>기념일</th>
											<td style="text-align:left">
											<c:if test="${regist.mode != 'Ins' }">
											<a href="javascript:center_Anniver();" class="grayBtn">기념일 등록</a>
											</c:if>
											</td>
										</tr>
										<tr>
											<th>영업 시간</th>
											<td style="text-align:left" colspan="3">
											<form:select path="centerStartTime" id="centerStartTime" title="영업 시작 시간">
												<option value=""></option>
											</form:select>
											 ~ 
											<form:select path="centerEndTime" id="centerEndTime" title="영업 종료 시간">
												<option value=""></option>
											</form:select>
											</td>
											<th>지점이미지</th>
											<td style="text-align:left">
											<input name="centerImg" id="centerImg" type="file"  size="20"/>
											</td>
										</tr>
											<th>주소</th>
											<td colspan="5" style="text-align:left">
											<form:input title="우편번호" path="centerZipcode1"  id="centerZipcode1" size="3" />-<form:input title="우편번호" path="centerZipcode2" id="centerZipcode2"   size="3"/>
											<a href="javascript:zipcode()" class="grayBtn">찾기</a>
											<br>
											<form:input title="주소1" path="centerAddr1"  id="centerAddr1" size="60" />&nbsp;&nbsp;<form:input title="주소2" path="centerAddr2" id="centerAddr2"  size="60"/>
											</td>
										</tr>
										<tr>	
											<th>지점구분</th>
											<td colspan="2" style="text-align:left">
											<form:select path="centerGubun" title="지점 구분" onChange="javascript:brodCheck()">
				                                    <form:option value='' label="--선택하세요--" />
				                                    <form:options items="${selectBranchGubun}"   itemValue="code" itemLabel="codeNm"/>
				                            </form:select>
											</td>
											<th>기본음원</th>
											<td colspan="2" style="text-align:left">
											   <select id="brodCode" name="brodCode"></select>
											</td>
                                        </tr>											
										<tr>
											<th colspan="6">지점정보</th>
										</tr>
										<tr>
											<th>운영DID</th>
											<td style="text-align:left">${regist.centerEquipmentCmt } 개 사용</td>
											<th>지점코드</th>
											<td style="text-align:left">${regist.centerId }</td>
											<th>사용유무</th>
											<td style="text-align:left">
											 <select id="centerUseYn" name="centerUseYn">
													<option value="">선택</option>
													<c:choose>
														<c:when test="${regist.centerUseYn == 'N' }">
															<option value="Y">사용</option>
															<option value="N" selected>사용안함</option>	
														</c:when>
														<c:otherwise>
															<option value="Y" selected>사용</option>
															<option value="N">사용안함</option>	
														</c:otherwise>
													</c:choose>
												</select>
											</td>												
										</tr>							
									</tbody>
								</table>
								
								<div class="footerBox">
									<a href="javascript:listPage('regist','/backoffice/sub/basicManage/centerList.do' )" class="yellowBtn">목록</a>
									<a href="javascript:check_form()" id="btnUpdate" class="yellowBtn">수정</a>
									<c:if test = "${regist.mode != 'Ins' }">	
									<a href="javascript:del_form()" class="grayBtn">삭제</a>
									</c:if>			
							    </div>
					          
					          
					          <!-- 내용 넣을 자리 끝 부분 -->
				              </div>
			              </div> 
		               </div>
					</div>
				</div>
		  </div>
   <div class="clear"></div>
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
		    if ($("#mode").val() == "Ins"){   	  		    	
		 		$("#btnUpdate").text("등록");
		    }	else {		    	
		    	$("#btnUpdate").text("수정");
		    }
		    
			selectTime($("#centerStartTime"), "00:00", "시작시간", $("#startTime").val());
			selectTime($("#centerEndTime"), "00:00", "종료시간", $("#endTime").val());
		    
		    brodCheck();
		});	  
	  function list_page(){
			  
	  }
	  function brodCheck(){
		  if ($("#centerGubun").val() == "BRANCH01"){
			  $("#brodCode").css("display", "block"); 
			  selectBrodCode();
		  }else {
			  $("#brodCode").css("display","none");
			  $("#brodCode").empty();
		  }
	  }
	  function center_Anniver(){
		  var url = "/backoffice/sub/basicManage/centerAnniList.do?centerId="+$("#centerId").val();
          window.open(url,"우편번호", 'width=800,height=550,top=100,left=650,scrollbars=auto');
	  }
	  function check_form(){
		  if (any_empt_line_id("centerNm", "지점명를 입력 하지 않았습니다.") == false) return;		  
		  document.regist.encoding = "multipart/form-data";
	      document.regist.submit();  
	  }
	  //센터 삭제 
	  function del_form(){
		  if (confirm("삭제 하시겠습니까?")== true){
			  document.regist.action = "/backoffice/sub/basicManage/centerDeletel.do";
			  document.regist.encoding = "application/x-www-form-urlencoded"; 
			  document.regist.submit();  			  
		  }		  	  
	  }
	  function zipcode(){		  
	      var url = "/backoffice/popup/zipPop.do";	      
          window.open(url,"우편번호", 'width=600,height=550,top=100,left=650,scrollbars=auto')	   
	  }
	  //음원 방송 콘텐츠
	  function selectBrodCode(){
		  apiExecute(
					"POST", 
					"/backoffice/sub/brodManage/brodComboLst.do",
					{
												
					},
					null,				
					function(result) {				
						//combobox 설정
						if (result.brodInfoLst != null) {
							$("#brodCode").empty();
							$("#brodCode").append("<option value=''>--선택하세요--</option>");						
							for (var i=0; i<result.brodInfoLst.length; i++) {
								var obj = result.brodInfoLst[i];
								if ($("#brodCodeH").val() == obj.brodCode){
									$("<option value='"+ obj.brodCode +"'>"+ obj.brodName +"</option>").appendTo("#brodCode").attr("selected", "selected");
								}else {
									$("<option value='"+ obj.brodCode +"'>"+ obj.brodName +"</option>").appendTo("#brodCode");	
								}
								
								
							}
						}
						
					},
					null,
					null
				);	
	  }
	</script>
</body>
</html>		