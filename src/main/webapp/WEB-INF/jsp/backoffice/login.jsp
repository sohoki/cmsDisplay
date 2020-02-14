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
<script type="text/javascript" src="/js/aten-emartcms-loginpage.js"></script>
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
							<a data-needpopup-show="#pw_search_pop" class="idSearchBtn loginPageBtn">비밀번호 초기화</a>
							<a data-needpopup-show="#join_pop" id="join_modal_call_btn" class="joinBtn loginPageBtn">이용신청</a>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		</form>
		<!--내용끝-->
	</div>
	
	<!-- 팝업 구현 부 -->
	<div id='pw_search_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>이마트CMS 비밀번호 초기화</h2>
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
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*부서</p>
                    <select class="popSel pw_search_group pw_search_checkInfo" onchange='centerInfoSetting(this);'></select>               
                </div>                
            </div>
            <!--// 팝업 필드박스-->
            <div class="pop_box50 pwSearch_input">
                <div class="padding15">
                    <p class="pop_tit">*비밀번호 <span class="search_pw_comment joinSubTxt"></span></p>
                    <input type="password" id="user_modify_pw" class="input_noti" value="" placeholder="영문+숫자+특수문자 10~20자">
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50 pwSearch_input">
                <div class="padding15">
                    <p class="pop_tit">*비밀번호 확인 <span class="search_pwChk_comment joinSubTxt"></span></p>
                    <input type="password" id="user_modify_pwChk" class="input_noti" value="" placeholder="영문+숫자+특수문자 10~20자">
                </div>                
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="pop_footer">
            <span id="pw_confirm_comment" class="pw_pop_main_subTxt">이용신청 및 수정 된 계정 정보를 입력/선택 해주세요</span><a onclick="pw_search_check(this);" id="pw_search_btn" class="top_btn" value="0">회원정보 확인</a>
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
                    <select class="popSel user_reg_group" onchange='centerInfoSetting(this);'></select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">점포</p>
                    <select class="popSel user_reg_center" disabled>
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
	var modify_pw_chk = false;
	function form_check(){
		if (any_empt_line_id("mberId", "아이디를 입력 하지 않았습니다.") == false) return;
		if (any_empt_line_id("password", "패스워드를 입력 하지 않았습니다.") == false) return;
		$("form[name=regist]").attr("action", "/backoffice/sub/SecurityLogin.do").submit();
	}   
    </script>
</body>
</html>		