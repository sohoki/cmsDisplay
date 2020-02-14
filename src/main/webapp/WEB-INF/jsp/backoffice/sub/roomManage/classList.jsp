<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="egovframework.let.sts.mhs.web.Culter_UniFunction" %>
<%
   Culter_UniFunction uniF = new Culter_UniFunction(); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="ko" >
    <title>회의실 정보 : 전문점CMS</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>	
	<link rel="stylesheet" href="/new/css/needpopup.css">
	<style>
		.joinSubTxt{
			float:right;
		} 
		.id-pw-warning{
			font-weight: bold;
			color : #EA4335;
		}
		.id-pw-posible{
			font-weight: bold;
			color : #34A853;
		}
		.join_pop_main_subTxt{
			float:left;
			font-weight: bold;
		}
		.pw_pop_main_subTxt{
			float:left;
			font-weight: bold;
		}		
	</style>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/roomManage/classList.do">
<input type="hidden" id="mhsClasscd" name="mhsClasscd">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >

<div id="mhs_wrap">
	<c:import url="/backoffice/inc/mhs_header.do" />
	<div id="mhs_contents">
		<div class="mhs_con_top">
			<div class="mhs_con_top_left">
				<ul>
					<li><a class="mhs_btn_reg" data-needpopup-show="#class_pop" id="join_modal_call_btn" onClick="javascript:fn_ClassInfo('Ins','0')">등록</a></li>
				</ul>
			</div>
			<div class="mhs_con_top_right">
				<ul>
					<li>
					    <form:select path="searchMhsBramdCd" id="searchMhsBramdCd" class="mhs_select" title="기본음원선택" onChange="javascript:fn_ViewCneter('Search')">
					         <form:option value="" label="선택하세요"/>
	                        <form:options items="${groupInfo}" itemValue="mhsBrandcd" itemLabel="mhsBrandnm"/>
					    </form:select>
					</li>
					<li>
					    <select id="searchMhsCenterCd" name="searchMhsCenterCd" class="mhs_select">
					    	<option value="">선택하세요</option>
					    </select>
					</li>
					<li><select id="searchCondition" name="searchCondition" class="mhs_select">
					      <option value="">선택하세요</option>
					      <option value="a.MHS_CLASSROOMNM"  <c:if test="${searchVO.searchCondition eq 'a.MHS_CLASSROOMNM' }"> selected="selected" </c:if>>강의실</option>
					      <option value="a.MHS_CLASSNM" <c:if test="${searchVO.searchCondition eq 'a.MHS_CLASSNM' }"> selected="selected" </c:if>>강의명</option>
					      <option value="a.MHS_TEACHERNM" <c:if test="${searchVO.searchCondition eq 'a.MHS_TEACHERNM' }"> selected="selected" </c:if>>강사명</option>
					     </select>
					</li>
					<li><input class="mhs_input" type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword }" /></li>
					<li><a class="mhs_btn_reg" onClick="fn_List();">조회</a></li>
				</ul>
			</div>
		</div>
		<div class="mhs_con_middle">
			<table class="mhs_con_table">
				<thead>
					<th>조직명</th><th>점포명</th><th>강의실</ht><th>강의명</th><th>강사명</th><th>강의일자</th><th>강의요일</th><th>강의시간</th><th>삭제</th>
				</thead>
				<tbody>
				    <c:forEach items="${resultList}" var="classInfo" varStatus="status">
					<tr>
					  <td>${classInfo.mhsBrandnm}</td>
					  <td><a href="#" onClick="javascript:fn_ClassInfo('Edt','${classInfo.mhsClasscd}')" data-needpopup-show="#class_pop">${classInfo.mhsCenternm}</a></td>
					  <td><a href="#" onClick="javascript:fn_ClassInfo('Edt','${classInfo.mhsClasscd}')" data-needpopup-show="#class_pop"> ${classInfo.mhsMonitornm} </a></td>
					  <td><a href="#" onClick="javascript:fn_ClassInfo('Edt','${classInfo.mhsClasscd}')" data-needpopup-show="#class_pop">${classInfo.mhsClassnm} </a></td>
					  <td><a href="#" onClick="javascript:fn_ClassInfo('Edt','${classInfo.mhsClasscd}')" data-needpopup-show="#class_pop">${classInfo.mhsTeachernm} </a></td>
					  <td>시작 :  ${classInfo.mhsClassstartday}<br>종료 : ${classInfo.mhsClassendday}</td>
					  <td>
					  <c:set var="mhsClassdayofweek" value="${classInfo.mhsClassdayofweek}" ></c:set>
					  <%
					  String myVariable = (String)pageContext.getAttribute("mhsClassdayofweek");
					  out.println( uniF.week_return(myVariable) );
					  %>
					  </td>
					  <td>${classInfo.mhsClassstarttime} ~ ${classInfo.mhsClassendtime}</td>
					  <td>
					  <a href="#" onClick="javascript:fn_ClassInfo('Del','${classInfo.mhsClasscd}')">[삭제]</a>
					  </td>
					 </tr>
					</c:forEach>					
				</tbody>
			</table>
		</div>
		<div class="mhs_con_bottom">
			<div class="paging">
			    
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />			
				
				<div class="clear"></div>	
			</div>
		</div>
	</div>
</div>	
<div id='class_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>강사 등록</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*조직명 <span class="join_id_comment joinSubTxt"></span></p>
                   <form:select path="mhsBrandcd" id="mhsBrandcd" title="기본음원선택" class="popSel user_reg_group" onChange="javascript:fn_ViewCneter('Model')">
					         <form:option value="" label="--선택하세요--"/>
	                        <form:options items="${groupInfo}" itemValue="mhsBrandcd" itemLabel="mhsBrandnm"/>
					</form:select>
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*점포명</p>
                    <select class="popSel user_reg_group" id="mhsCentercd"></select>
                </div>                
            </div>
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*강의실명 <span class="join_pw_comment joinSubTxt"></span></p>
                    <select class="popSel user_reg_group" id="mhsClassroomnm">
                    		<option value="">선택하세요</option>
                       <c:forEach var="moniterInfo" items="${moniterInfo}" >
                       		<option value="${moniterInfo.mhsMonitorcd}">${moniterInfo.mhsMonitornm}</option>
                       </c:forEach>
                    </select>
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*강의명 <span class="join_pwChk_comment joinSubTxt"></span></p>
                    <input type="text" id="mhsClassnm" class="input_noti" value="">
                </div>                
            </div>
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*강사명 <span class="join_pwChk_comment joinSubTxt"></span></p>
                    <input type="text" id="mhsTeachernm" class="input_noti" value="" >
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*강의 상태 <span class="join_pw_comment joinSubTxt"></span></p>
                    <select class="popSel user_reg_group" id="mhsClassstatus">
                       <option value="Y">정상</option>
                       <option value="N">사용안함</option>
                    </select>
                </div>                
            </div>
            <!--팝업 필드박스 //-->                       
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*강의일자</p>
                    <input type="text" id="mhsClassstartday" class="input_noti input_noti_x" value="" size="10" >~
                    <input type="text" id="mhsClassendday" class="input_noti input_noti_x" value=""  size="10">
                </div>                
            </div>            
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*강의시간</p>                     
                    
                    
                    <select id="mhsClassstarttime1" name="mhsClassstarttime1" class="input_noti input_noti_x2" title="시간">
				      <option value="">선택</option>
				      <c:forEach var="item" varStatus="i" begin="10" end="23" step="1">
				       <option value="${item}">
				        <c:if test="${item < 10}">0</c:if><c:out value="${item}시" />
				       </option>
				      </c:forEach>
				     </select>
				     <select id="mhsClassstarttime2" name="mhsClassstarttime2" class="input_noti input_noti_x2" title="시간">
				      <option value="">선택</option>
				      <option value="00">00분</option>
				      <c:forEach var="item" varStatus="i" begin="5" end="55" step="5">
				      	<c:choose>
				      		<c:when test="${item <= 5}">	
				       			<option value="0${item}">
				        			<c:out value="0${item}분" />
				       			</option>
				       		</c:when>
							<c:otherwise>
								<option value="${item}">
								<c:out value="${item}분" />
								</option>
							</c:otherwise>
						</c:choose>
				      </c:forEach>
				     </select>
				     ~
				     <select id="mhsClassendtime1" name="mhsClassendtime1" class="input_noti input_noti_x2" title="시간">
				      <option value="">선택</option>
				      <c:forEach var="item" varStatus="i" begin="10" end="23" step="1">
				       <option value="${item}">
				        <c:if test="${item < 10}">0</c:if><c:out value="${item}시" />
				       </option>
				      </c:forEach>
				     </select>
				     <select id="mhsClassendtime2" name="mhsClassendtime2" class="input_noti input_noti_x2" title="시간">
				      <option value="">선택</option>
				      <option value="00">00분</option>
				      <c:forEach var="item" varStatus="i" begin="5" end="55" step="5">
				      	<c:choose>
				      		<c:when test="${item <= 5}">	
				       			<option value="0${item}">
				        			<c:out value="0${item}분" />
				       			</option>
				       		</c:when>
							<c:otherwise>
								<option value="${item}">
								<c:out value="${item}분" />
								</option>
							</c:otherwise>
						</c:choose>
				      </c:forEach>
				     </select>
				     
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*강의요일</p>
                    <input type="checkbox" id="mhsClassdayofweek" name="mhsClassdayofweek" value="2" >월
                    <input type="checkbox" id="mhsClassdayofweek" name="mhsClassdayofweek" value="3" >화
                    <input type="checkbox" id="mhsClassdayofweek" name="mhsClassdayofweek" value="4" >수
                    <input type="checkbox" id="mhsClassdayofweek" name="mhsClassdayofweek" value="5" >목
                    <input type="checkbox" id="mhsClassdayofweek" name="mhsClassdayofweek" value="6" >금
                    <input type="checkbox" id="mhsClassdayofweek" name="mhsClassdayofweek" value="7" >토
                    <input type="checkbox" id="mhsClassdayofweek" name="mhsClassdayofweek" value="1" >일
                </div>                
            </div>  
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">비고</p>
                    <input type="text" id="mhsClassintro" class="input_noti" value="" >            
                </div>                
            </div>     
            <div class="clear"></div>                 
        </div>
        <div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후  클릭해주세요.</span><a href="javascript:joinConfirm();" id="btn_text" class="top_btn">등록하기</a>
        	<div class="clear"></div>
        </div>
    </div>
</form:form>
   
    
    <script src="/new/js/needpopup.js"></script> 
    <!--data-->
    <script src="/new/js/jquery-ui.js"></script>
    <script src="/new/js/datepipck.js"></script>
</body>
<script type="text/javascript">
		
	$(document).ready(function () {
		
	  //******************************************************************************
	  // 상세검색 달력 스크립트
	  //******************************************************************************
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
		yearRange: '2019:2099' //1990년부터 2020년까지
	  };			  
	  $("#mhsClassstartday").datepicker(clareCalendar);
	  $("#mhsClassendday").datepicker(clareCalendar);
	  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
	  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
	  if ($("#searchMhsBramdCd").val() != ""){
		  fn_ViewCneter("Search");
	  }
	});
	function linkPage(page){
		$("#pageIndex").val(page);
		$("form[name=regist]").attr("action", "/backoffice/sub/roomManage/classList.do").submit();
	}
	function fn_List(){
		$("#pageIndex").val("1");
		$("form[name=regist]").attr("action", "/backoffice/sub/roomManage/classList.do").submit();
	}
	//지점 보여주기 
	function fn_ViewCneter(code ){
		if ($("#searchMhsBramdCd").val() != "" || $("#mhsBrandcd").val() != ""){
			var data =  null;
			if (code == "Search"){
				data = {mode : 's', brand : $("#searchMhsBramdCd").val() };
			}else {
				data = {mode : 's', brand : $("#mhsBrandcd").val() };
			}
			$.ajax({
				url : '/backoffice/sub/roomManage/actionMhsCenter.do',
				type : 'POST',
				data : data,
				dataType : 'json',
				success : function(result) {
					
					console.log(result);
					var data = result.centerList;
					if(data.length > 0){
						if (code == "Search"){
							$("#searchMhsCenterCd").empty();
							/* var option =  $("<option value=''>없음</option>");
							$('#searchMhsCenterCd').append(option); */
							var mCenterCd = "${searchVO.searchMhsCenterCd}";
							for(var i = 0; i < data.length; i++){
								if ( mCenterCd != "" && data[i].mhsCentercd ==  mCenterCd){
									var option =  $("<option value='"+ data[i].mhsCentercd+"' selected>"+data[i].mhsCenternm+"</option>");	
								}else {
									var option =  $("<option value='"+ data[i].mhsCentercd+"'>"+data[i].mhsCenternm+"</option>");
								}								
								$('#searchMhsCenterCd').append(option);
							}	
						}else {
							$("#mhsCentercd").empty();
							/* var option =  $("<option value=''>없음</option>");
							$('#mhsCentercd').append(option); */
							for(var i = 0; i < data.length; i++){
								var option =  $("<option value='"+ data[i].mhsCentercd+"'>"+data[i].mhsCenternm+"</option>");
								$('#mhsCentercd').append(option);
							}
						}
						
					} else {
						
					}				
				},
				error : function(e) {
					console.log(e);
				}
			});
		}
		
	}
	function joinConfirm(){
	
	     if (any_empt_line_id("mhsBrandcd", "조직명을 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsCentercd", "점포명을 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsClassroomnm", "강의실명을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsClassnm", "강의명을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsTeachernm", "감사명을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsClassstartday", "강의일자을 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsClassendday", "강의일자을 입력 하지 않았습니다.") == false) return;
		 /* if (any_empt_line_id("mhsClassintro", "강의 관련 비고를 입력 하지 않았습니다.") == false) return; */
		 
		 if ( parseInt($("#mhsClassstartday").val()) > parseInt($("#mhsClassendday").val()) ){
			 alert("시작일이 종료일 보다 빠를수 없습니다.");
			 return;
		 } 
		 
		 if (any_empt_line_id("mhsClassstarttime1", "강의시작시간을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsClassstarttime2", "강의시작시간을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsClassendtime1", "강의시작시간을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsClassendtime2", "강이시작시간을 입력 하지 않았습니다.") == false) return;
		 if ( parseInt($("#mhsClassstarttime1").val()+$("#mhsClassstarttime2").val()) > parseInt($("#mhsClassendtime1").val()+$("#mhsClassendtime2").val()) ){
			 alert("강의 시작 시간이 종료시간보다 빠를수 없습니다.");
			 return;
		 }
		 
		 
		 var data = {
					mhsBrandcd :  $("#mhsBrandcd").val(),
					mhsCentercd : $("#mhsCentercd").val(),
					mhsClasscd :  $("#mhsClasscd").val(),
					mhsClassroomnm : $("#mhsClassroomnm").val(),
					mhsClassnm : $("#mhsClassnm").val(),
					mhsTeachernm : $("#mhsTeachernm").val(),
					mhsClassstartday : $("#mhsClassstartday").val(),
					mhsClassendday : $("#mhsClassendday").val(),
					mhsParentcentercd : $("#mhsParentcentercd").val(),
					mhsClassstarttime : $("#mhsClassstarttime1").val()+$("#mhsClassstarttime2").val(),
					mhsClassendtime : $("#mhsClassendtime1").val()+$("#mhsClassendtime2").val(),
					mhsClassstatus :  $("#mhsClassstatus").val(),
					mhsClassdayofweek :checkbox_value('mhsClassdayofweek'),
					mhsClassintro :  $("#mhsClassintro").val(),
					mode :  $("#mode").val()
				}; 
		 $.ajax({
				url : '/backoffice/sub/roomManage/classUpdate.do',
				type : 'POST',				
				contentType : 'application/json',
				data : JSON.stringify(data) ,
				async : false,
				dataType : 'json',
				success : function(result) {					
					if (result.status == "SUCCESS"){
						if($("#mode").val() == "Ins"){
							var data2 = {
								mhsMonitorcd : result.classInfo.mhsClassroomnm,
						    	mhsClasscd : result.classInfo.mhsClasscd
							}; 
							$.ajax({
								url : '/backoffice/sub/roomManage/viewConnInsert.do',
								type : 'POST',				
								contentType : 'application/json',
								data : JSON.stringify(data2) ,
								dataType : 'json',
								success : function(result) {					
									if (result.status == "SUCCESS"){
										alert("강의정보가 등록 되었습니다.");
							     	}else{
							    		 //로그인 fail 날때 처리
							    	 	alert(result.message);
							     	}	
								},
								error : function(e) {
									console.log(e);
								}
							}); 
						}else{
							alert("강의정보가 수정 되었습니다.");
						}
						location.reload();    	 
				   	}else{
				    	//로그인 fail 날때 처리
				      	alert(result.message);
				      	location.reload();  
					}	
					},
					error : function(e) {
						console.log(e);
					}
				});
		}
	function fn_ClassInfo(mode, code){
		$("#mode").val(mode);
		//수정 구문 만들기
		
		if (mode == "Edt"){
			    $("#btn_text").text("수정하기");
			    var url = "/backoffice/sub/roomManage/classInfo.do";
		
			    
			    apiExecute(
						"POST", 
						url,
						{ mhsClasscd : code },
						null,				
						function(result) {				
							if (result.status == "SUCCESS"){
								    
								    
						    	    var setHtml = "";
						    	    var data = result.centerList;
									if(data.length > 0){
										$("#mhsCentercd").empty();
										for(var i = 0; i < data.length; i++){
											var option =  $("<option value='"+ data[i].mhsCentercd+"'>"+data[i].mhsCenternm+"</option>");
											$('#mhsCentercd').append(option);
										}
									} 
									if (mode == "Edt"){								      	 
								      	$("#mhsBrandcd").val(result.result.mhsBrandcd);
								      	$("#mhsCentercd").val(result.result.mhsCentercd);
								      	$("#mhsClasscd").val(result.result.mhsClasscd);
								      	$("#mhsClassroomnm").val(result.result.mhsClassroomnm);
								      	$("#mhsClassnm").val(result.result.mhsClassnm);
								      	$("#mhsTeachernm").val(result.result.mhsTeachernm);
								      									      	
								      	value_checkbox("mhsClassdayofweek", result.result.mhsClassdayofweek);								      	
								      	$("#mhsClassstartday").val(result.result.mhsClassstartday);
								      	$("#mhsClassendday").val(result.result.mhsClassendday);
								      	
								      	
								      	$("#mhsClassstarttime1").val(result.result.mhsClassstarttime.substring(0,2));
								      	$("#mhsClassstarttime2").val(result.result.mhsClassstarttime.substring(2,4));
								      	
								      	$("#mhsClassendtime1").val(result.result.mhsClassendtime.substring(0,2));
								      	$("#mhsClassendtime2").val(result.result.mhsClassendtime.substring(2,4));
								      	
								      	
								      	$("#mhsClassstatus").val(result.result.mhsClassstatus);
								      	$("#mhsClassintro").val(result.result.mhsClassintro);
								    }
									
									    	 
						     }else{
						    	 //로그인 fail 날때 처리 
						     }							
						},
						null,
						null
					);	
		}else if (mode == "Del") {
			if (confirm("삭제 하시겠습니까?")== true){
				 var url = "/backoffice/sub/roomManage/classDelete.do?mhsClasscd="+code;
				 apiExecute(
							"POST", 
							url,
							{mhsClasscd :code},
							null,				
							function(result) {		
								if (result == "SUCCESS"){
									alert("정상적으로 삭제 되었습니다.");	
									document.location.reload();
							     }else{
							    	 alert("삭제 도중 장애가 발생 하였습니다.");
							     }							
							},
							null,
							null
						);
			}
		}else{
			$("#btn_text").text("등록하기");
			$("#mhsBrandcd").val("");
	      	$("#mhsCentercd").val("");
	      	$("#mhsClasscd").val("");
	      	$("#mhsClassroomnm").val("");
	      	$("#mhsClassnm").val("");
	      	$("#mhsTeachernm").val("");
	      	$("#mhsClassstartday").val("");
	      	$("#mhsClassendday").val("");
	      	value_checkbox("mhsClassdayofweek", "");								      	
	      	$("#mhsClassstarttime1").val("");
	      	$("#mhsClassstarttime2").val("");
	      	$("#mhsClassendtime1").val("");
	      	$("#mhsClassendtime2").val("");
	      	/* $("#mhsClassstatus").val(""); */
	      	$("#mhsClassintro").val("");
			
		}
		
	}
</script>	
</html>		