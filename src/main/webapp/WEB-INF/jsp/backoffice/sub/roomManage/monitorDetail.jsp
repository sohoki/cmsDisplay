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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/roomManage/monitorList.do">
<input type="hidden" id="mhsMonitorcd" name="mhsMonitorcd" value="${searchVO.mhsMonitorcd }" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<form:hidden path="searchCondition" id="searchCondition" />
<form:hidden path="searchKeyword" id="searchKeyword" />
<form:hidden path="pageIndex" id="pageIndex" />
<input type="hidden" name="mode" id="mode" >

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

	<div id="mhs_wrap">
		<c:import url="/backoffice/inc/mhs_header.do" />
		<div id="mhs_contents">
			<div class="mhs_con_top">
				<div class="mhs_con_top_left">
				<!-- 
					<ul>
						<li><a class="mhs_btn_reg">등록</a></li>
					</ul>
				 -->
				</div>
				<div class="mhs_con_top_right">
					<ul>
					    <li><a class="mhs_btn_reg" onClick="fn_preview();">미리보기</a></li>
						<li><a class="mhs_btn_reg">모니터 재부팅</a></li>
						<li><a class="mhs_btn_reg" data-needpopup-show="#moniter_pop" onClick="javascript:fn_MointerEdt()">정보 수정</a></li>
						<li><a class="mhs_btn_reg" onClick="javascript:fn_SearchForm();">목록으로</a></li>
					</ul>				
				</div>
			</div>
			<div class="mhs_con_middle">
				<table class="mhs_info_table">
					<thead><th colspan="4">모니터 정보</th></thead>
					<tbody>
						<tr>
							<th>조직명</th><td>${registInfo.mhsBrandnm }</td>
							<th>점포명</th><td>${registInfo.mhsCenternm }</td>
						</tr>
						<tr>
						    <th>단말정보</th><td>${registInfo.mhsMonitornm }
						    <br>(${registInfo.mhsMonitorcd })</td>
						    <th>네트워크정보</th>
						    <td>${registInfo.mhsIpaddr }
						        <br>${registInfo.mhsMacaddr }
						    </td>
						</tr>
						<tr><th>송출방식</th>
						    <td>${registInfo.mhsMonitorstatus}</td>
						    <th>최종통신일자</th>
						    <td>${registInfo.mhsLastconn}</td>
					    </tr>
						<tr><th>비고</th><td colspan="3">${registInfo.mhsRemark}</td></tr>
					</tbody>
				</table>
				<div class="mhs_middle_title">
					<h2>모니터 송출 상세정보</h2>
				</div>
				<div class="mhs_con_top_left">
					<ul>
						<li>※ 추가 된 강의를 삭제하려면 시간 블록을 클릭하세요.</li>
					</ul>
				</div>
				<div class="mhs_con_top_right">
					<ul>
						<li>조회일자&nbsp;<input class="mhs_input" id="searchDay" type="text" />
						    <a href="javascript:fn_ClassDayChageView()" class="mhs_btn_reg">조회</a>
						</li>
					</ul>
				</div>
				<table class="mhs_info_table mhs_viewtime_table" id="ConnectInfoTable">
					<thead>
						<tr><th rowspan="2">구분</th><th colspan="14">모니터 운영시간</th></tr>
						<tr>
							<th>10:00</th><th>11:00</th><th>12:00</th><th>13:00</th><th>14:00</th><th>15:00</th><th>16:00</th><th>17:00</th>
							<th>18:00</th><th>19:00</th><th>20:00</th><th>21:00</th><th>22:00</th><th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr><th colspan="15"><a onClick="javascript:fn_preview();" >미리보기</a></th></tr>
					</tbody>
				</table>
			</div>
			<div class="mhs_con_bottom">
			</div>
		</div>
	</div>
	<div id='class_pop' class="needpopup">
		<div class="popHead">
	            <h2>강의 등록</h2>
	        </div>
		<div class="popCon">
		    <div class="">
                <div class="padding15">
                    <p class="pop_tit">*강의명<span class="join_id_comment joinSubTxt"></span></p>
                    <select id="classCd" class="popSel">
                    
                    </select>
                </div>                
            </div>   
		</div>
		<div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">강의를 선택 후  등록해주세요.</span><a href="javascript:fn_ClassInsert();" class="top_btn">등록하기</a>
        	<div class="clear"></div>
        </div>
	</div>
	
	
	
	<div id='moniter_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>모니터 수정</h2>
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
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후  클릭해주세요.</span><a href="javascript:joinConfirm();" class="top_btn">수정하기</a>
        	<div class="clear"></div>
        </div>
    </div>
    <script src="/new/js/needpopup.js"></script>    
	<script src="/new/js/jquery-ui.js"></script>
	<script src="/new/js/datepipck.js"></script>
</form:form>
</body>
<script type="text/javascript">
   function fn_SearchForm(){
		$("form[name=regist]").attr("action", "/backoffice/sub/roomManage/monitorList.do").submit();	
   }
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
		  $("#searchDay").datepicker(clareCalendar);		  
		  $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
		  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
		  fn_ClassList();	  
		  
	});
   //강의 시간 
   function fn_ClassDayChageView(){
	   fn_ClassList();
   }
   function fn_ClassList(){
		if($("#searchDay").val()  == ""){
			$("#searchDay").val(getFormatDate(new Date));
		}
	   var data = {
			   mhsBrandcd : "${registInfo.mhsBrandcd}",
			   mhsCentercd : "${registInfo.mhsCentercd}",
			   mhsMonitorcd : "${registInfo.mhsMonitorcd}",
			   searchDay : $("#searchDay").val()
		};
	   $.ajax({
			url : '/backoffice/sub/roomManage/viewConnInfo.do',
			type : 'POST',				
			contentType : 'application/json',
			data : JSON.stringify(data) ,
			dataType : 'json',
			success : function(result) {					
				if (result.status == "SUCCESS"){								    
		    	    
		    	    var data = result.resultList;
		    	    $("#ConnectInfoTable > tbody").empty();
		    	    $("#ConnectInfoTable >  tbody:last").append("<tr><th colspan='15'><a onClick='javascript:fn_preview();' class='mhs_btn_reg'>미리보기</a></th></tr>");
					if(data.length > 0){
					
						for (var i = 0; i < data.length; i++ ){
							setHtml = "<tr><th>"+ data[i].mhsClassnm +"</th>";
							
							var startTime = data[i].mhsClassstarttime.substring(0,2);
							var endTime = data[i].mhsClassendtime.substring(0,2);
							var mhsConnSeq = data[i].mhsConnSeq;
							
							for (var a = 10; a <23; a++){
								if (a >= parseInt(startTime) && parseInt(endTime) >= a ){
									setHtml += "<td style='background-color:#4285F5;'></td>";
								}else {
									setHtml += "<td></td>";		
								}
							}
							setHtml += "<td><a href='javascript:fn_ClassDel(&#39;"+ data[i].mhsClasscd +"&#39;,&#39;"+ mhsConnSeq +"&#39;)'>[삭제]</a></td></tr>";
							/* alert(data[i].mhsClasscd); */
							$("#ConnectInfoTable >  tbody:last").append(setHtml);	
						}
					} 	 					
			     }else{
			    	 //로그인 fail 날때 처리 
			    	 
			     }	
			},
			error : function(e) {
				console.log(e);
			}
		});	
	   
   }
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

	function fn_ViewDel(code){
		var url = "/backoffice/sub/roomManage/viewConnDelete.do?mhsConnSeq="+code;
		apiExecute(
			"POST", 
			url,
			{mhsConnSeq :code},
			null,				
			function(result) {
				if (result == "SUCCESS"){
					alert("정상적으로 삭제 되었습니다.");
					fn_ClassList();	  
   		       	}else{						    	 
   		       		alert("삭제 도중 장애가 발생 하였습니다.");
			    }							
			},
			null,
			null
		);
	}
	
	
	function fn_ClassDel(code1,code2){
		if (confirm("삭제 하시겠습니까?")== true){
			var url = "/backoffice/sub/roomManage/classDelete.do?mhsClasscd="+code1;
			$.ajax({
				url : url,
				type : 'POST',				
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				data : {mhsClasscd :code1},
				success : function(result) {					
					if (result == "SUCCESS"){
						fn_ViewDel(code2);
					}else{
					    alert("삭제 도중 장애가 발생하였습니다.");
					}
					
				},	
				error : function(e) {
					console.log(e);
				}
			});	
	    }
    }
	
	
	
	
	
	
/* 	function fn_ClassDel(code1,code2){
		if (confirm("삭제 하시겠습니까?")== true){
			var url = "/backoffice/sub/roomManage/classDelete.do?mhsClasscd="+code1;
			apiExecute(
				"POST", 
				url,
				{mhsClasscd :code1},
				null,				
				function(result) {		
					if (result == "SUCCESS"){
						alert("진입1");
						fn_ViewDel(code2);
				    }else{
				    	alert("삭제 도중 장애가 발생하였습니다.");
					}							
				},
				null,
				null
			);
		}
    } */
   
   
   
   
   function fn_preview(){
	   /* var url="/backoffice/sub/roomManage/preView.do?mhsMonitorcd="+ $("#mhsMonitorcd").val(); */
	   var url="/backoffice/sub/roomManage/preView.do?mhsMonitorcd="+ $("#mhsMonitorcd").val() + "&searchDay=" + $("#searchDay").val();
	   /* alert($("#searchDay").val()); */
       window.open(url,"","width=1920,height=1080,left=0");

   }
   function fn_ClassInsert(){
	   var data = {
			    mhsMonitorcd :  $("#mhsMonitorcd").val(),
			    mhsClasscd : $("#classCd").val()
			}; 
	   $.ajax({
			url : '/backoffice/sub/roomManage/viewConnInsert.do',
			type : 'POST',				
			contentType : 'application/json',
			data : JSON.stringify(data) ,
			dataType : 'json',
			success : function(result) {					
				if (result.status == "SUCCESS"){
					 fn_ClassList();
			     }else{
			    	 //로그인 fail 날때 처리
			    	 alert(result.message);
			     }	
			},
			error : function(e) {
				console.log(e);
			}
		}); 
   }
   function fn_MointerInfo(){

	//수정 구문 만들기
    	
	var data = {
			  mhsBrandcd : "${registInfo.mhsBrandcd}",
			  mhsCentercd : "${registInfo.mhsCentercd}"
			}; 
	   $.ajax({
			url : '/backoffice/sub/roomManage/classCombo.do',
			type : 'POST',				
			contentType : 'application/json',
			data : JSON.stringify(data) ,
			dataType : 'json',
			success : function(result) {					
				if (result.status == "SUCCESS"){								    
			    	    var setHtml = "";
			    	    var data = result.result;
						if(data.length > 0){
							$("#classCd").empty();
							for(var i = 0; i < data.length; i++){
								var option =  $("<option value='"+ data[i].mhsClasscd+"'>"+data[i].mhsClassroomnm+":"+data[i].mhsClassnm+":"+data[i].mhsTeachernm+":</option>");
								$('#classCd').append(option);
							}
						} 	 
			     }else{
			    	 //로그인 fail 날때 처리 
			     }		
			},
			error : function(e) {
				console.log(e);
			}
		}); 	
	}
	//등록상태
	function fn_MointerEdt(){
		$("#mode").val("Edt");				
	    var url = "/backoffice/sub/roomManage/monitorInfo.do";
	    apiExecute(
				"POST", 
				url,
				{ mhsMonitorcd : $("#mhsMonitorcd").val() },
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
					      	$("#mhsBrandcd").val(result.result.mhsBrandcd);
					      	$("#mhsCentercd").val(result.result.mhsCentercd);
					      	$("#mhsMonitorcd").val(result.result.mhsMonitorcd);
					      	$("#mhsMonitornm").val(result.result.mhsMonitornm);
					      	$("#mhsMviewtype").val(result.result.mhsMviewtype);
					      	$("#mhsRemark").val(result.result.mhsRemark);
						      	
						    
							
							    	 
				     }else{
				    	 //로그인 fail 날때 처리 
				     }							
				},
				null,
				null
			);	
		
	}
	function joinConfirm(){
		 if (any_empt_line_id("mhsBrandcd", "조직명을 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsCentercd", "점포명을 선택 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsMonitornm", "모니터명을 입력 하지 않았습니다.") == false) return;
		 if (any_empt_line_id("mhsMviewtype", "송출 상태를 선택 하지 않았습니다.") == false) return;
		 
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
	
	function getFormatDate(date){ 
		var year = date.getFullYear(); //yyyy 
		var month = (1 + date.getMonth()); //M 
		month = month >= 10 ? month : '0' + month; //month 두자리로 저장
		var day = date.getDate(); //d
		day = day >= 10 ? day : '0' + day; //day 두자리로 저장 
		return year + '' + month + '' + day; 
	}


</script>
</html>		