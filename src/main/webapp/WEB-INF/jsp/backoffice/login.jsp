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
<link rel="stylesheet" href="/new/css/reset.css">
<link rel="stylesheet" href="/new/css/layout.css">    
<link rel="stylesheet" href="/new/css/paragraph.css"> 
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<!--[if lte IE 8]>
<script src="js/poly-checked.min.js"></script> 
<![endif]-->
<!--popup-->
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
	<div id="header">
		<!-- 
		<div class="login">
			<p><a>&nbsp;&nbsp;&nbsp;</a></p>
		</div>
		 -->	
		 <h1><img src="/img/logo.png" alt="이마트"></h1>
		<!-- <div class="conIn topmenu" id="header">
			<h1><img src="/img/logo.png" alt="이마트"></h1>
		</div> -->
	</div>	
	<div class="pageTop">
		<div class="conIn">
			<h2>DID현황</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home"> 
				DID현황
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="container">
		<!--내용시작-->
		<form name="regist" method="post" action="/backoffice/sub/SecurityLogin.do" onsubmit="return form_check();">
		<input name="userSe" type="hidden" value="GNR"/>		
		<div class="mbLogin">			
			<p class="loginPageTitle">EMART CMS</p>
			<div> 
				<div class="mainLoginBox">
					<div class="loginInputBox">
						<div class="idBox">
							<p class="login_input_id_display">아이디</p>
							<input type="text" name="mberId" id="mberId" class="loginInputTag" size="30" tabindex="1" autofocus>
						</div>
						<div class="pwBox">
							<p class="login_input_pw_display">비밀번호</p>
							<input type="password" name=password id="password" size="30" class="loginInputTag" tabindex="2">
						</div>
						<div class="btnBox">
							<input type="submit" value="로그인" class="loginBtn loginPageBtn"/>
							<a data-needpopup-show="#pw_search_pop" class="idSearchBtn loginPageBtn">비밀번호 찾기</a>
							<a data-needpopup-show="#join_pop" class="joinBtn loginPageBtn">이용신청</a>
						</div>
					</div>
				</div>
			</div>
			   
				<!-- <table class="loginTable">
					<tbody>
						<tr>
							<td style="text-align:left"><input type="text" name="mberId" id="mberId" size="30" tabindex="1"></td>
						</tr>
						<tr>
							<td style="text-align:left"><input type="password" name=password id="password" size="30" tabindex="2"></td>
						</tr>
						<tr>
							<td style="text-align:left">
								<input type="submit" value="LOGIN"  class="loginB yellowBtn" />
								<a class="blueBtn" style="padding: 8px 24px;">이용신청</a>
							</td>							
						</tr>
					</tbody>
					<div class="btnLogin">
						
					</div>
				
					
					<tbody>
						<th>Username</th>
						<td style="text-align:left"><input type="text" name="mberId" id="mberId" size="15"></td>
						<tr></tr>
						<th>Password</th>
						<td style="text-align:left"><input type="password" name=password id="password" size="15"></td>

						
					</tbody>
					
				</table> -->
				
<!-- 				<div class="btnLogin">
					<input type="submit" value="LOGIN"  class="loginB"  >
				</div> -->
			<div class="clearfix"></div>
		</div>
		</form>
		<!--내용끝-->
	</div>
	
	
	
	
	
	
	
	<div id='pw_search_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>이마트CMS 비밀번호 찾기</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*아이디</p>
                    <input type="text" id="pw_search_id" class="input_noti pw_search_checkInfo" value="" placeholder="등록한 사번 또는 아이디" />
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*사용자명</p>
                    <input type="text" id="pw_search_name" class="input_noti pw_search_checkInfo" value="" placeholder="등록한 이름" />
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*부서</p>
                    <select id="popSel" class="pw_search_group pw_search_checkInfo" onchange='centerInfoSetting(this);'></select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">점포</p>
                    <select id="popSel" class="pw_search_center pw_search_checkInfo"></select>               
                </div>                
            </div>          
            <!--// 팝업 필드박스-->
            <div class="pop_box50 pwSearch_input">
                <div class="padding15">
                    <p class="pop_tit">*비밀번호 <span class="join_pw_comment joinSubTxt"></span></p>
                    <input type="password" id="user_modify_pw" class="input_noti" value="" placeholder="영문+숫자+특수문자 10~20자">
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50 pwSearch_input">
                <div class="padding15">
                    <p class="pop_tit">*비밀번호 확인 <span class="join_pwChk_comment joinSubTxt"></span></p>
                    <input type="password" id="user_modify_pwChk" class="input_noti" value="" placeholder="영문+숫자+특수문자 10~20자">
                </div>                
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="pop_footer">
            <span id="pw_confirm_comment" class="pw_pop_main_subTxt">이용신청 및 수정 된 시스템 정보를 입력/선택 해주세요</span><a onclick="pw_search_check(this);" id="pw_search_btn" class="top_btn" value="0">비밀번호 찾기</a>
        </div>
    </div>
	
	
	
	
	
	
	
	
	
	<div id='join_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>이마트CMS 이용신청</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*아이디 <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="text" id="user_reg_id" class="input_noti" value="" placeholder="사번 또는 아이디" />
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*사용자명</p>
                    <input type="text" id="user_reg_name" class="input_noti" value="" placeholder="표출 될 이름" />
                </div>                
            </div>
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*비밀번호 <span class="join_pw_comment joinSubTxt"></span></p>
                    <input type="password" id="user_reg_pw" class="input_noti" value="" placeholder="영문+숫자+특수문자 10~20자">
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*비밀번호 확인 <span class="join_pwChk_comment joinSubTxt"></span></p>
                    <input type="password" id="user_reg_pwChk" class="input_noti" value="" placeholder="영문+숫자+특수문자 10~20자">
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*부서</p>
                    <select id="popSel" class="user_reg_group" onchange='centerInfoSetting(this);'></select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">점포</p>
                    <select id="popSel" class="user_reg_center" disabled>
                    	<option value>부서를 선택해주세요</option>
                    </select>               
                </div>                
            </div>          
            <div class="clearfix"></div>
        </div>
        <div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력/선택 후 신청하기를 클릭해주세요.</span><a href="javascript:joinConfirm();" class="top_btn">신청하기</a>
        </div>
    </div>
	
	
	<div class="clear"></div>
	<div id="footer">
		<div class="conIn ">
			<span><img src="/img/emartLogo.png"></span>
			<address><span>서울특별시 성동구 뚝섬로 377(성수동2가)</span></address>
		</div>
	</div>
	
	<script src="/new/js/needpopup.js"></script> 
    <!--data-->
    <script src="/new/js/jquery-ui.js"></script>
    <script src="/new/js/datepipck.js"></script>
	
    <script type="text/javascript">
		var join_id_chk = false;
		var join_pw_chk = false;
		function form_check(){
			if (any_empt_line_id("mberId", "아이디를 입력 하지 않았습니다.") == false) return;
			if (any_empt_line_id("password", "패스워드를 입력 하지 않았습니다.") == false) return;
			$("form[name=regist]").attr("action", "/backoffice/sub/SecurityLogin.do").submit();
		}       
		$(document).ready(function() {
			
			$(".pwSearch_input").attr("style", "display:none");
			
			
    	   
	    	if ("${message}" != "") {
	    		  if ("${message}" == "login_ok"){
	    			  location.href="/backoffice/sub/equiManage/didList.do";  
	    		  }else {
	    			  alert("아이디 또는 패스워드가 잘못 입력 하였습니다.");
		    		  $("#mberId").focus() ;	    			  
	    		  }				
	    	}
	    	
	    	
	    	// /backoffice/sub/operManage/json/webData.do
	    		
	    	groupInfoSetting();
	    	
	    	
	    	$("#user_reg_id").focusout(function (){
	    		// 아이디 중복확인 진행
	    		var callData = "{'request_type':'join-idCheck', 'request_data':{'mberId':'"+$(this).val()+"'}}";
		   	   	$.ajax({
	  				url : '/backoffice/sub/operManage/jsonRequest.do',
	  				type : 'POST',
	  				data : {
	  					requestData : callData
	  				},
	  				dataType : 'json',
	  				success : function(result) {
	  					// console.log(result);
	  					var appendOption;
	  					if(result.result.length > 0){
	  							
	  						var resultData = result.data;
	  						switch(resultData[0].exist){
		  						case "N" : // 중복없음 
		  							$(".join_id_comment").text("이용 가능한 사번/아이디 입니다.");
		  							$(".join_id_comment").removeClass("id-pw-warning");
		  							$(".join_id_comment").addClass("id-pw-posible"); 
		  							join_id_chk = true;
	  								break;
		  						case "Y" : 
		  							$(".join_id_comment").text("이미 존재하는 사번/아이디 입니다.");
		  							$(".join_id_comment").removeClass("id-pw-posible"); 
		  							$(".join_id_comment").addClass("id-pw-warning");
		  							join_id_chk = false;
		  							break; // 중복있음
	  						}
	  						
	  					}
	  				},
	  				error : function(e) {
	  					console.log(e);
	  				}
	  			});
	    	});
	    	
	    	 
	    	
	    	  
	    	$("#user_reg_pw").focusout(function(){
	    		console.log("111");
	    		if(pwCheck($(this), $(".join_pw_comment"))){
	    			$(".join_pw_comment").text("확인");
	    			$(".join_pw_comment").removeClass("id-pw-warning"); 
	    			$(".join_pw_comment").addClass("id-pw-posible"); 
	    			join_pw_chk = true; 
	    		} else {
	    			$(".join_pw_comment").removeClass("id-pw-posible"); 
	    			$(".join_pw_comment").addClass("id-pw-warning");
	    			join_pw_chk = false;
	    		}
	    	});

			$("#user_reg_pwChk").focusout(function(){
				console.log("222");
				if(pwCheck($(this), $(".join_pwChk_comment"))){
					if($(this).val() == $("#user_reg_pw").val()){
						$(".join_pwChk_comment").text("확인");
						$(".join_pwChk_comment").removeClass("id-pw-warning"); 
		    			$(".join_pwChk_comment").addClass("id-pw-posible"); 
		    			join_pw_chk = true;
					}else{
						$(".join_pwChk_comment").text("비밀번호를 확인해주세요.");
						$(".join_pwChk_comment").removeClass("id-pw-posible"); 
		    			$(".join_pwChk_comment").addClass("id-pw-warning");
		    			join_pw_chk = false;
					}
	    		} else {
	    			$(".join_pwChk_comment").removeClass("id-pw-posible"); 
	    			$(".join_pwChk_comment").addClass("id-pw-warning");
	    			join_pw_chk = false;
	    		}
	    	});


	    	
		});   
       
		function pwCheck(el, commentEl){
			var inPw = el.val();
	    	var num = inPw.search(/[0-9]/g);
	    	var eng = inPw.search(/[a-z]/ig);
	    	var spe = inPw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	    	if(inPw.length >= 10 && inPw.length <= 20){
	    		if(inPw.search(/\s/) != -1){
	    			commentEl.text("공백은 입력이 불가능합니다.");
	    			return false;
	    		} else {
	    			if(num < 0 || eng < 0 || spe < 0){
	    				commentEl.text("비밀번호 규칙을 확인해주세요.");
	    				return false;
	    			} else {
	    				return true;
	    			}
	    		}
    		}else{
    			commentEl.text("10~20자로 입력해주세요.");
	    		return false;
	    	}
		} 
       
	   	function centerInfoSetting(el){
			// $(el).val()
			$(".user_reg_center").html(null);
			$(".pw_search_center").html(null);
			 
			$(".user_reg_center").attr("disabled", false);
			$(".pw_search_center").attr("disabled", false);
			var callData = "{'request_type':'join-centerData', 'request_data':{'roleCode':'"+$(el).val()+"'}}";
	   	   	$.ajax({
  				url : '/backoffice/sub/operManage/jsonRequest.do',
  				type : 'POST',
  				data : {
  					requestData : callData
  				},
  				dataType : 'json',
  				success : function(result) {
  					//console.log(result);
  					var appendOption;
  					if(result.result.length > 0){
  						var resultData = result.data;
  						appendOption += "<option value>점포전체</option>";
  						for(var i = 0; i < result.result.length; i ++){
  							appendOption += "<option value='"+resultData[i].CENTER_ID+"'>"+resultData[i].CENTER_NM+"</option>";	
  						}
  					} else {
  						appendOption += "<option value>점포전체</option>";
  					}
  					$(".user_reg_center").html(appendOption);
  					$(".pw_search_center").html(appendOption);
  				},
  				error : function(e) {
  					console.log(e);
  				}
  			});
		}
	   	
		function groupInfoSetting (){
			
			
			
			var callData = "{'request_type':'join-groupData', 'request_data':{'groupId':'', 'parentGroupId':'EMART_00000000000001'}}";
	   	   	$.ajax({
  				url : '/backoffice/sub/operManage/jsonRequest.do',
  				type : 'POST',
  				data : {
  					requestData : callData
  				},
  				dataType : 'json',
  				success : function(result) {
  					if(result.result.length > 0){
  						// console.log(result);	
  						var resultData = result.data;
  						var appendOption;
  						appendOption += "<option value=''>부서선택</option>";
  						for(var i = 0; i < result.result.length; i ++){
  							appendOption += "<option id='P_"+resultData[i].PARENT_GROUP_ID+"' value='"+resultData[i].GROUP_ID+"'>"+resultData[i].GROUP_NM+"</option>";	
  						}
  						
  						$(".user_reg_group").html(appendOption);
  						$(".pw_search_group").html(appendOption);
  						
  					}
  				},
  				error : function(e) {
  					console.log(e);
  				}
  			});
		}
		
		function joinConfirm(){
			if(!join_id_chk){
				$("#join_confirm_comment").text("아이디를 확인해주세요.");
				$(".join_confirm_comment").removeClass("id-pw-posible"); 
		    	$(".join_confirm_comment").addClass("id-pw-warning");
			} else if (!join_pw_chk) {
				$("#join_confirm_comment").text("비밀번호를 확인해주세요.");
				$(".join_confirm_comment").removeClass("id-pw-posible"); 
		    	$(".join_confirm_comment").addClass("id-pw-warning");
			} else {
				$("#join_confirm_comment").text("잠시만 기다려주세요.");
				$(".join_confirm_comment").removeClass("id-pw-warning");
				$(".join_confirm_comment").addClass("id-pw-posible"); 
				var callData = "{'request_type':'join-confirm', 'request_data':{'mberId':'"+$("#user_reg_id").val()+"', 'password':'"+$("#user_reg_pw").val()+"', 'mberNm':'"+$("#user_reg_name").val()+"', 'groupId':'"+$(".user_reg_group").val()+"', 'centerId':'"+$(".user_reg_center").val()+"'}}";
				$.ajax({
	  				url : '/backoffice/sub/operManage/jsonRequest.do',
	  				type : 'POST',
	  				data : {
	  					requestData : callData
	  				},
	  				dataType : 'json',
	  				success : function(result) {
	  					if(result.result.length > 0){
	  						event.preventDefault();
	  						needPopup.hide();
	  						alert(result.data[0].mberId+"의 이용신청이 완료되었습니다.");
	  					}
	  				},
	  				error : function(e) {
	  					console.log(e);
	  				}
	  			});
			}
			
		}
		
		function pw_search_check(el){
			/*
			 * pw_search_btn value
			 * 0 : 입력사항 확인 전
			 * 1 : 입력사항 확인, 비밀번호 입력 & 아이디/사용자명/부서/점포 변경 불가, "비밀번호 찾기" -> "비밀번호 변경"
			 */
			 
			 var btnValue = $(el).attr("value");
			 
			 switch(btnValue){
				 case "0" : $(el).attr("value","1"); pw_formCheck(); 	break;
				 case "1" : $(el).attr("value","0"); pw_changeConfirm(); break;
			 }
		}
		function pw_formCheck(){ // 먼저 입력정보를 넘겨서 변경을 할지 결정
			
 			var callData = "{'request_type':'pwSearch-userInfo', 'request_data':{'mberId':'"+$("#pw_search_id").val()+"', 'mberNm':'"+$("#pw_search_name").val()+"', 'groupId':'"+$(".pw_search_group").val()+"', 'centerId':'"+$(".pw_search_center").val()+"'}}";
			$.ajax({
  				url : '/backoffice/sub/operManage/jsonRequest.do',
  				type : 'POST',
  				data : {
  					requestData : callData
  				},
  				dataType : 'json',
  				success : function(result) {
  					console.log(result);
  					if(result.result.length > 0){
  						if(result.data[0].exist == "Y"){
  							// 비밀번호 변경창 노출 및 타 입력사항 수정 불가변경
  							$(".pwSearch_input").attr("style", "");
  							$(".pw_search_checkInfo").attr("disabled", true);
  							$("#pw_confirm_comment").text("변경하실 비밀번호를 입력해주세요.");
  						} else {
  							
  						}
  					} else {
  						$(".pw_search_checkInfo").attr("disabled", false);
  						$("#pw_confirm_comment").text("계정정보를 확인해주세요.기억이 나지 않는다면 시스템 담당자에게 연락 바랍니다");
  					}
  				},
  				error : function(e) {
  					console.log(e);
  				}
			});
			
			

		}
		function pw_changeConfirm(){
			$(".pw_search_checkInfo").attr("disabled", false);
			
			// 데이터 전달 간 장애시 $(".pw_search_checkInfo").attr("disabled", true);
		}		
		
    </script>
</body>
</html>		