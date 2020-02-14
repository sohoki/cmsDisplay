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
	<link href="/css/layout.css" rel="stylesheet" type="text/css" >
	<link href="/css/paragraph.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/new/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	<div id="mhs_wrap">
		<c:import url="/backoffice/inc/mhs_header.do" />
		<input type="hidden" name="mhsBrandcd" id="mhsBrandcd" />
		<input type="hidden" name="mhsCentercd" id="mhsCentercd" />
		<input type="hidden" name="subMode" id="subMode" />
		<input type="hidden" name="mode" id="mode" />
		
		<input type="hidden" name="groupId" id="groupId" />
		<input type="hidden" name="groupDc" id="groupDc" />
		<input type="hidden" name="parentGroupId" id="parentGroupId" />
		<div id="mhs_contents">
			<div class="mhs_con_top">
				<div class="mhs_con_top_left">
				<!-- 				
					<ul>
						<li><a>전체</a></li>
					</ul> 
				-->
				</div>
				<div class="mhs_con_top_right">
				<!-- 
					<ul>
						<li><select class="mhs_select"><option>조직전체</option><option>1</option><option>2</option><option>----------</option></select></li>
						<li><input class="mhs_input" type="text" /></li>
						<li><a class="mhs_btn_reg">조회</a></li>
					</ul>
				 -->
				</div>
			</div>
			<div class="mhs_con_middle">
				<div class="mhs_con_middle_left mhs_width35 brand_center_box">
					<div class="mhs_con_middle_top mhs_con_top_right">
						<ul>
							<li><a class="mhs_btn_reg" onClick="javascript:fn_viewGroup('Ins','0')">조직 등록</a></li> <!-- 클릭시 Modal Popup을 이용하여 하위 조직 등록 진행  -->
						</ul>
					</div>
					<!-- BRAND INFO TABLE -->
					<!-- 들여쓰기 | 조직명(조직코드) | 수정 | 삭제 -->
					<table class="mhs_con_table mhs_bnc_list">
						<thead><th>조직정보</th><th colspan="2" style="width: 30%;">조직관리</th></thead>
						<tbody>
						    <tr class="brandLine" id="brandLine">
						       <td class="listMainNm"><input type="text" name="groupNm" id="groupNm"></td>
						       <td colspan="2"><a href="#" id="btn_Group" onClick="javascript:fn_GroupUpdate()">등록</a></td>
						    </tr>
							<c:forEach items="${list}" var="info" varStatus="status">
							<tr class="brandLine B_${info.mhsBrandcd}" onclick="viewCenter('${info.mhsBrandcd}')" />
								<td class="listMainNm">
								<c:forEach var="i" begin="1" end="${info.mhsBrandlv}" step="1">
								<c:if test="${info.mhsBrandlv ne '1'}">
								&nbsp;&nbsp;
								</c:if>
								</c:forEach>
								<c:if test="${info.mhsBrandlv ne '1'}">
								└
								</c:if>
								${info.mhsBrandnm}
								</td>
								<c:choose>
							       <c:when test="${info.mhsBrandcd=='EMART_00000000000023'}">
								       <td colspan="2" class="list_admin_btn" onclick="fn_viewGroup('Edt','${info.mhsBrandcd}');">수정</td></td></td>
							       </c:when>
							       <c:otherwise>
										<td class="list_admin_btn" onclick="fn_viewGroup('Edt','${info.mhsBrandcd}');">수정</td>
										<td class="list_admin_btn" onclick="fn_viewGroup('Del','${info.mhsBrandcd}');">삭제</td>       
							       </c:otherwise>
						       </c:choose>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="mhs_con_middle_right mhs_width60 brand_center_box">
					<div class="mhs_con_middle_top mhs_con_top_right">
						<ul>
							<li class="brand_center_regBtnBox"><a class="mhs_btn_reg mhsCenterRegBtn" href="javascript:fn_CenterInfo('Ins','0')">점포 등록</a></li> <!-- 클릭시 Modal Popup을 이용하여 점포 등록 진행  -->
						</ul>
					</div>
					<!-- CENTER INFO TABLE -->
					<!-- 점포명(점포코드) | 수정 | 삭제  - 점포명(점포코드) | 수정 | 삭제 -->
					
							
					<table class="mhs_con_table mhs_bnc_list" id="mhsCenterTable">
						<thead><th style="width:216px;">점포정보</th>
						       <th colspan="2" style="width:144px;">상위코드</th>
						       <th style="width:216px;">상태</th>
						       <th colspan="2" style="width:144px;">수정|삭제</th></thead>
						<tbody></tbody>
					</table>
					
					
				</div>
			</div>
			<div class="mhs_con_bottom">
			</div>
		</div>
	</div>	
	<!-- 조직 & 부서 등록 POP -->
    <div id='new_list' class="needpopup">
    	<div class="popHead"><h2></h2></div>
    	<div class="popCon">
    		<div class="popConLeft"></div>
    		<div class="popConRight"></div>
    	</div>
	    <div class="pop_footer">
	    	<div class="pop_footer_left"></div>
	    	<div class="pop_footer_right"></div> 
	    </div>
    </div>
    <div>
       
    </div>
<script>
	$("document").ready(function(){
		$(".mhsCenterRegBtn").hide();
		$("#brandLine").hide();
	});
	
	/* $(".popHead h2").text("신규 점포 등록");
	$(".popHead h2").text("점포 정보 수정"); */
	// <a class="mhs_btn_reg">조직 등록</a>
	//지점 등록 및 
	function fn_viewGroup(mode, groupId){
		$("#brandLine").css("display", "");
		$("#mode").val(mode);
		if (mode == "Edt"){
			var url = "/backoffice/sub/roomManage/mhsGroupInfo.do";
			 apiExecute(
						"POST", 
						url,
						{	groupId : groupId},
						null,				
						function(result) {				
							if (result.status == "SUCCESS"){
								$("#groupNm").val(result.groupInfo.groupNm);
								$("#groupDc").val(result.groupInfo.groupDc);
								$("#parentGroupId").val(result.groupInfo.parentGroupId);
								$("#groupId").val(result.groupInfo.groupId);
						     }else{
						    	 //로그인 fail 날때 처리 
						    	 
						     }							
						},
						null,
						null
					);	
			$("#btn_Group").text("수정");
		}else if (mode == "Del"){
			if (confirm("삭제 하시겠습니까?")== true){
				 var url = "/backoffice/sub/roomManage/mhsGroupDelete.do?groupId="+groupId;
				 apiExecute(
							"POST", 
							url,
							{groupId :groupId},
							null,				
							function(result) {
								//alert(result);							
								if (result == "SUCCESS"){
									//리로드
									document.location.reload();
							     }else if (result == "LOGIN FAIL"){
							    	 location.href="/backoffice/login.do";
							     }else{						    	 
							    	 alert("삭제 도중 장애가 발생 하였습니다.");
							     }							
							},
							null,
							null
						);
			}
		}else {
			$("#parentGroupId").val("EMART_00000000000023");
			$("#btn_Group").text("등록");
		}
	}
	function fn_GroupUpdate(){
		if (any_empt_line_id("groupNm", "조직 정보를 입력 하지 않았습니다.") == false) return;
		 var data = {
					groupNm :  $("#groupNm").val(),
					groupDc : $("#groupDc").val(),
					parentGroupId : $("#parentGroupId").val(),
					groupId : $("#groupId").val(),
					useYn : "Y",
					mode : $("#mode").val()
				}; 
		 $.ajax({
				url : '/backoffice/sub/roomManage/mhsGroupUpdate.do',
				type : 'POST',				
				contentType : 'application/json',
				data : JSON.stringify(data) ,
				dataType : 'json',
				success : function(result) {
					if (result.status == "SUCCESS"){

				     }else{
				    	 //로그인 fail 날때 처리
				    	 location.href="/backoffice/login.do";
				     }	
					 document.location.reload();
				},
				error : function(e) {
					console.log(e);
				}
			});
	}
	function fn_CenterInfo(mode, code){
		
		$("#mhs_Updatetr").css("display", "");
		$("#subMode").val(mode);	
		$("#mhsCentercd").val(code);
		 
		
		   //상위 부서 코드 가지고 오기 
			 var url = "/backoffice/sub/roomManage/parentCenterInfo.do";
			 apiExecute(
						"POST", 
						url,
						{
							mhsBrandcd : $("#mhsBrandcd").val(),
							mhsCentercd : $("#mhsCentercd").val(),
							subMode : $("#subMode").val()
						},
						null,				
						function(result) {				
							if (result.status == "SUCCESS"){
								    if ($("#subMode").val() == "Edt"){								      	 
								      	$("#mhsCenternm").val(result.centerInfo.mhsCenternm);
										$("#mhsParentcentercd").val(result.centerInfo.mhsParentcentercd);
										$("#mhsCenterstatus").val(result.centerInfo.mhsCenterstatus);										
								    }
								    
						    	    var setHtml = "";
						    	    var data = result.result;
									if(data.length > 0){
										$("#mhsParentcentercd").empty();
										for(var i = 0; i < data.length; i++){
											var option =  $("<option value='"+ data[i].mhsCentercd+"'>"+data[i].mhsCenternm+"</option>");
											$('#mhsParentcentercd').append(option);
										}
									} else {
										var option =  $("<option value='0'>최상위점포</option>");
										$('#mhsParentcentercd').append(option);
									}
									    	 
						     }else{
						    	 //로그인 fail 날때 처리 
						     }							
						},
						null,
						null
					);		
	}
	function fn_Save(){
		
		 if (any_empt_line_id("mhsBrandcd", "조직정보을 입력 하지 않았습니다.") == false) return;
		 var data = {
				    mhsBrandcd :  $("#mhsBrandcd").val(),
				    mhsCentercd : $("#mhsCentercd").val(),
				    mhsCenternm : $("#mhsCenternm").val(),
				    mhsParentcentercd : $("#mhsParentcentercd").val(),
				    subMode : $("#subMode").val(),
				    mhsCenterstatus : $("#mhsCenterstatus").val()
				}; 		 
		 $.ajax({
				url : '/backoffice/sub/roomManage/centerUpdate.do',
				type : 'POST',				
				contentType : 'application/json',
				data : JSON.stringify(data) ,
				dataType : 'json',
				success : function(result) {
					
					if (result.status == "SUCCESS"){
						makeCenterList($("#mhsBrandcd").val());      	 
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
	/* function brandWork(mode, cd, pCd){
		
		var popSetHTML = "";
		popSetHTML += "<div class='popConLeft'></div>";
		popSetHTML += "<div class='popConRight'></div>";
		if(mode == "i"){
			alert("조직 등록 프로세스");
			$(".popHead h2").text("신규 조직 등록");
			$(".pop_footer_right").html("<ul><li><a class='mhs_btn_reg'>등록</a><li></ul>");
			$(".popCon").html(popSetHTML);
		} else if(mode == "u"){
			alert("조직 수정 프로세스");	
			$(".popHead h2").text("조직 정보 수정");
			$(".popCon").html(popSetHTML);
		} else if (mode == "d"){
			$(".popHead h2").text("조직 정보 삭제");
			popSetHTML += "<p>선택하신 조직을 정말로 삭제하시겠습니까?<br>조직 삭제 시 연동 된 점포, 모니터, 관리자 계정에 영향이 미칠 수 있습니다.<br><br><span>조직 정보 삭제를 진행 시 기존 정보를 모두 확인 후 진행해주세요.</span></p>";
			$(".popCon").html(popSetHTML);
		} else {
			$(".popHead h2").text("조직 시스템");
			popSetHTML += "<p>정상 처리가 되지 않았습니다.<br>페이지를 새로고침 후 재시도를 부탁드립니다.<br><br><span>지속적으로 장애 발생시 시스템 관리자에게 문의해주세요.</span></p>";
			$(".popCon").html(popSetHTML);
		}
	} */
	
	function centerWork(mode, cd, brandcd){
		if(mode == "i"){
			alert("조직 등록 프로세스");
		} else if (mode == "d"){
			 var url = "/backoffice/sub/roomManage/centerDelete.do?mhsCentercd="+cd;
			 apiExecute(
						"POST", 
						url,
						{mhsCentercd :cd},
						null,				
						function(result) {
							if (result == "SUCCESS"){
								makeCenterList($("#mhsBrandcd").val());
						     }else{						    	 
						    	 alert("삭제 도중 장애가 발생 하였습니다.");
						     }							
						},
						null,
						null
					);			
		} else {
			alert("시스템 관리자에게 문의");
		}
		
		
		
	}
	
	function viewCenter(cd){
		if($.inArray("select", $(".B_"+cd)[0].classList) > 0){
			// 동일 조직 중복 선택시 점포 리스트 clear
			$(".brandLine").removeClass("select");
			$("#mhsCenterTable tbody").html("");
			$(".mhsCenterRegBtn").hide();
			$("#mhsBrandcd").val("");			
		} else {
			// 점포 리스트 새로 세팅
			$(".brandLine").removeClass("select");		
			$(".B_"+cd).addClass("select");
			
			makeCenterList(cd);			
			$(".mhsCenterRegBtn").show();
			$("#mhsBrandcd").val(cd);			
		}
		$("#groupNm").val(cd);
		
	}
	
	function makeCenterList(data){		
		$.ajax({
			url : '/backoffice/sub/roomManage/actionMhsCenter.do',
			type : 'POST',
			data : {
				mode : 's',
				brand : data
			},
			dataType : 'json',
			success : function(result) {
				console.log(result);
				var data = result.centerList;
				var setHtml = "";
				if(data.length > 0){
					setHtml += "<tr id='mhs_Updatetr' style='display:none;'>";
					setHtml += " <td><input type='text' id='mhsCenternm' name='mhsCenternm'></td>";
				    setHtml += " <td colspan='2'>";
				    setHtml +=    "<select id='mhsParentcentercd' name='mhsParentcentercd' >";
				    setHtml +=    "  <option value=''>---</option>"      ;
				    setHtml +=    "</select>";
				    setHtml +=  "</td>";
				    setHtml +=  "<td>";
				    setHtml +=    "<select id='mhsCenterstatus' name='mhsCenterstatus' >";
				    setHtml +=    "  <option value='Y'>Y</option>";
				    setHtml +=    "  <option value='N'>N</option>";
				    setHtml	+=    "</select>"						;        
				    setHtml += " </td>";
				    setHtml += "<td colspan='2'>";
				    setHtml += " <a href='javascript:fn_Save();'>등록</a>";
				    setHtml += "</td>";
				    setHtml += "</tr>";
					for(var i = 0; i < data.length; i++){
						setHtml += "<tr><td>"+data[i].mhsCenternm+"</td>";
						if (data[i].mhsParentcentercd == "0"){
							setHtml += "<td colspan='2'>지점</td>";
						}else {
							setHtml += "<td colspan='2'>"+data[i].mhsParentcenterNm+"</td>";	
						}						
						setHtml += "<td>"+data[i].mhsCenterstatus+"</td>";
						setHtml += "<td class='list_admin_btn' onclick=\"fn_CenterInfo('Edt','"+data[i].mhsCentercd+"') \">수정</td>";
						setHtml += "<td class='list_admin_btn' onclick=\"centerWork('d','"+data[i].mhsCentercd+"','"+data[i].mhsBrandcd+"')\">삭제</td>";
						if((((data.length-1)%2) == 0) && (i == (data.length-1))){
							setHtml += "<td colspan='3'></td></tr>";
						}
					
					}
				} else {
					setHtml = "<tr><td colspan='6'>생성 된 점포가 없습니다.</td></tr>";
				}
				$("#mhsCenterTable tbody").html(setHtml);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
</script>
</body>

</html>		