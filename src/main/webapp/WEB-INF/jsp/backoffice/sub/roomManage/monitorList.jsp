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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/roomManage/monitorList.do">
<input type="hidden" id="mhsMonitorcd" name="mhsMonitorcd">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >



	<div id="mhs_wrap">
		<c:import url="/backoffice/inc/mhs_header.do" />
		<div id="mhs_contents">
			<div class="mhs_con_top">
				<div class="mhs_con_top_left">
					<ul>
						<li><a class="mhs_btn_reg" data-needpopup-show="#moniter_pop" id="join_modal_call_btn" onClick="javascript:fn_MointerInfo('Ins','0')">등록</a></li>
					</ul>
				</div>
				<div class="mhs_con_top_right">
					<ul>
						<li>
						    <form:select path="searchMhsBramdCd" id="searchMhsBramdCd" class="mhs_select" title="기본음원선택" onChange="javascript:fn_ViewCneter('Search')">
					          <form:option value="" label="부서선택"/>
	                          <form:options items="${groupInfo}" itemValue="mhsBrandcd" itemLabel="mhsBrandnm"/>
					        </form:select>
						</li>
						<li>
						    <select id="searchMhsCenterCd" name="searchMhsCenterCd" class="mhs_select">
						    	<option value="" label="점포선택">
					        </select>
						</li>
						<li>
						    <select class="mhs_select">
						      <option>상태전체</option>
						      <option>정상</option>
						      <option>불량</option>
						    </select>
						</li>
						<li>
						     <select class="mhs_select" id="searchCondition" name="searchCondition">
						         <option value="" label="선택하세요"></option>
							     <option value="a.MHS_MONITORNM"  <c:if test="${searchVO.searchCondition eq 'a.MHS_MONITORNM' }"> selected="selected" </c:if>>단말명</option>
							     <option value="a.MHS_MONITORCD" <c:if test="${searchVO.searchCondition eq 'a.MHS_MONITORCD' }"> selected="selected" </c:if>>단말ID</option>
							     <option value="a.MHS_IPADDR" <c:if test="${searchVO.searchCondition eq 'a.MHS_IPADDR' }"> selected="selected" </c:if>>IP</option>
							     <option value="a.MHS_MACADDR" <c:if test="${searchVO.searchCondition eq 'a.MHS_MACADDR' }"> selected="selected" </c:if>>MAC</option>
						     </select>
						</li>
						<li><input class="mhs_input" type="text" type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword }" /></li>
						<li><a class="mhs_btn_reg" onClick="javascript:fn_SearchForm();">조회</a></li>
					</ul>
				</div>
			</div>
			<div class="mhs_con_middle">
				<table class="mhs_con_table">
					<thead>
						<th>조직명</th><th>점포명</th><th>단말명</th><th>네트워크 정보</th><th>운용상태</th><th>비고</th>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="info" varStatus="status">
						<tr>
							<td><a href="#" data-needpopup-show="#moniter_pop" id="join_modal_call_btn" onClick="javascript:fn_MointerInfo('Edt','${info.mhsMonitorcd}')">${info.mhsBrandnm}</a></td>
							<td>${info.mhsCenternm}</td>
							<td><a href="javascript:fn_MoniterView('${info.mhsMonitorcd}')">${info.mhsMonitornm}</a><br>(${info.mhsMonitorcd})</td>
							<td>${info.mhsIpaddr}<br>${info.mhsMacaddr}</td>
							<td>
							<c:choose>
							<c:when test="${info.mhsLastconn eq 'ON' }">
								<span class="mhs_sts_icon mhs_sts_on"></span>
							</c:when>
							<c:otherwise>
								<span class="mhs_sts_icon mhs_sts_off"></span>
							</c:otherwise>
							</c:choose>
							</td>
							<td><c:if test="${info.mhsRemark ne '' && info.mhsRemark ne null}"><a>더보기</a></c:if></td>
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
	<div id='moniter_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>모니터 등록</h2>
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
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*점포명</p>
                    <select class="popSel user_reg_group" id="mhsCentercd"></select>
                </div>                
            </div>
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*모니터명 <span class="join_pw_comment joinSubTxt"></span></p>
                    <input type="text" id="mhsMonitornm" class="input_noti">
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*송출타입 <span class="join_pwChk_comment joinSubTxt"></span></p>
                    <select id="mhsMviewtype" class="popSel">
                        <option value="1">단일</option>
                        <option value="2">분할</option>
                        <option value="3">리스트</option>
                    </select>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*모니터 상태 <span class="join_pw_comment joinSubTxt"></span></p>
                    <select class="popSel user_reg_group" id="mhsMonitorstatus">
                       <option value="Y">정상</option>
                       <option value="N">사용안함</option>
                    </select>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*비고</p>
                    <input type="text" id="mhsRemark" class="input_noti">         
                </div>                
            </div>  
            <div class="clear"></div>                    
        </div>
        
        <div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후  클릭해주세요.</span><a href="javascript:joinConfirm();" class="top_btn">등록하기</a>
        	<div class="clear"></div>
        </div>
    </div>
</form:form>
</body>
<script src="/new/js/needpopup.js"></script>    
<script src="/new/js/jquery-ui.js"></script>
<script src="/new/js/datepipck.js"></script>
<script type="text/javascript">
		
	$(document).ready(function () {
	  
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
	  
	  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
	  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
	  if ($("#searchMhsBramdCd").val() != ""){
		  fn_ViewCneter("Search");
	  }
	});
	//검색 
	function fn_MoniterView(code){
		$('#mhsMonitorcd').val(code);
		$("form[name=regist]").attr("action", "/backoffice/sub/roomManage/monitorDetail.do").submit();
	}
	function fn_SearchForm(){
		$("#pageIndex").val("1");
		$("form[name=regist]").attr("action", "/backoffice/sub/roomManage/monitorList.do").submit();	
	}
	function fn_MointerInfo(mode, code){
		$("#mode").val(mode);
		//수정 구문 만들기
		
		if (mode == "Edt"){
			    var url = "/backoffice/sub/roomManage/monitorInfo.do";
			    apiExecute(
						"POST", 
						url,
						{ mhsMonitorcd : code },
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
								      	$("#mhsMonitorcd").val(result.result.mhsMonitorcd);
								      	$("#mhsMonitornm").val(result.result.mhsMonitornm);
								      	$("#mhsMviewtype").val(result.result.mhsMviewtype);
								      	$("#mhsRemark").val(result.result.mhsRemark);
								      	
								    }
									
									    	 
						     }else{
						    	 //로그인 fail 날때 처리 
						     }							
						},
						null,
						null
					);	
		}else {
			$('#mhsBrandcd').val("");
		}
		
	}
	//등록상태
	function joinConfirm(){
		 if (any_empt_line_id("mhsBrandcd", "조직명을 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsCentercd", "점포명을 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsMonitornm", "모니터명을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsMviewtype", "송출 상태를 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsRemark", "비고을 입력 하지 않았습니다") == false) return;
		 
		 var data = {
				    mhsMonitorcd :  $("#mhsMonitorcd").val(),
				    mhsMonitornm : $("#mhsMonitornm").val(),
				    mhsBrandcd :  $("#mhsBrandcd").val(),
				    mhsCentercd : $("#mhsCentercd").val(),
				    mhsMviewtype : $("#mhsMviewtype").val(),
					mhsTeachernm : $("#mhsTeachernm").val(),
					mhsRemark : $("#mhsRemark").val(),
					mhsMonitorstatus : $("#mhsMonitorstatus").val(),
					mode :  $("#mode").val()
				}; 
		 $.ajax({
				url : '/backoffice/sub/roomManage/mointerUpdate.do',
				type : 'POST',				
				contentType : 'application/json',
				data : JSON.stringify(data) ,
				dataType : 'json',
				success : function(result) {					
					if (result.status == "SUCCESS"){
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
					
					var data = result.centerList;
					if(data.length > 0){
						if (code == "Search"){
							$("#searchMhsCenterCd").empty();
							var option =  $("<option value=''>없음</option>");
							$('#searchMhsCenterCd').append(option);
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
							var option =  $("<option value=''>없음</option>");
							$('#mhsCentercd').append(option);
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
</script>	

</html>		