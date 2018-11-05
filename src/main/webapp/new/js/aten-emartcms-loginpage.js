	$(document).ready(function() {
		groupInfoSetting();
		$("body").on('click','#pw_search_pop .needpopup_remover', function() {
			// 비밀번호 초기화 작업 취소
			$("#pw_search_id").val("");
			$("#pw_search_id").attr("disabled", false);
			$("#pw_search_btn").attr("value","0");
			$("#pw_search_btn").text("회원정보 확인");
			$(".pw_search_checkInfo").attr("disabled", false);
			//pw_changeConfirm();
			$(".pwSearch_input").attr("style", "display:none");	
			$("#pw_confirm_comment").text("이용신청 및 수정 된 계정 정보를 입력/선택 해주세요");
			$("#pw_confirm_comment").removeClass("id-pw-warning");
		});
		
		
		$("body").on('click','#join_pop .needpopup_remover', function() {
			// 이용신청 작업 취소
			$("#join_pop input").val("");
			$("#user_reg_id").attr("disabled", false);
			$("#user_reg_name").attr("disabled", false);

			$("#join_confirm_comment").text("내용을 모두 입력/선택 후 신청하기를 클릭해주세요.");
			$("#join_confirm_comment, .join_id_comment, .join_pw_comment, .join_pwChk_comment").removeClass("id-pw-warning");
			$("#join_confirm_comment, .join_id_comment, .join_pw_comment, .join_pwChk_comment").removeClass("id-pw-posible");
			
			$("#join_pop .joinSubTxt").text("");
		});
		
		
		$(".pwSearch_input").attr("style", "display:none");
		
		
	   
		if ("${message}" != "${message}" && "${message}" != "") {
			if ("${message}" == "login_ok"){
				location.href="/backoffice/sub/equiManage/didList.do";  
			}else {
				alert("아이디 또는 패스워드가 잘못 입력 하였습니다.");
				$("#mberId").focus() ;	    			  
			}				
		}
    	
    	
    	// /backoffice/sub/operManage/json/webData.do
    		
    	
    	
    	
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
		
		
		
		  
		
		
		$("#user_modify_pw").focusout(function(){
    		if(pwCheck($(this), $(".search_pw_comment"))){
    			$(".search_pw_comment").text("확인");
    			$(".search_pw_comment").removeClass("id-pw-warning"); 
    			$(".search_pw_comment").addClass("id-pw-posible"); 
    			modify_pw_chk = true;
    		} else {
    			$(".search_pw_comment").removeClass("id-pw-posible"); 
    			$(".search_pw_comment").addClass("id-pw-warning");
    			modify_pw_chk = false;
    		}
    	});

		$("#user_modify_pwChk").focusout(function(){
			if(pwCheck($(this), $(".search_pwChk_comment"))){
				if($(this).val() == $("#user_modify_pw").val()){
					$(".search_pwChk_comment").text("확인");
					$(".search_pwChk_comment").removeClass("id-pw-warning"); 
	    			$(".search_pwChk_comment").addClass("id-pw-posible"); 
	    			modify_pw_chk = true;
				}else{
					$(".search_pwChk_comment").text("비밀번호를 확인해주세요.");
					$(".search_pwChk_comment").removeClass("id-pw-posible"); 
	    			$(".search_pwChk_comment").addClass("id-pw-warning");
	    			modify_pw_chk = false;
				}
    		} else {
    			$(".search_pwChk_comment").removeClass("id-pw-posible"); 
    			$(".search_pwChk_comment").addClass("id-pw-warning");
    			modify_pw_chk = false;
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
		 * 1 : 입력사항 확인, 비밀번호 입력 & 아이디/사용자명/부서/점포 변경 불가, "비밀번호 초기화" -> "비밀번호 변경"
		 */
		 
		 var btnValue = $(el).attr("value");
		 
		 switch(btnValue){
			 case "0" : $(el).attr("value","1"); pw_formCheck(); 	break;
			 case "1" : $(el).attr("value","0"); pw_changeConfirm(); break;
		 }
	}
	function pw_formCheck(){ // 먼저 입력정보를 넘겨서 변경을 할지 결정
		
			var callData = "{'request_type':'pwSearch-userInfo', 'request_data':{'mberId':'"+$("#pw_search_id").val()+"', 'groupId':'"+$(".pw_search_group").val()+"'}}";
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
							$("#pw_search_btn").text("비밀번호 초기화");
							$(".pwSearch_input").attr("style", "");
							$(".pw_search_checkInfo").attr("disabled", true);
							$("#pw_confirm_comment").removeClass("id-pw-warning");
							$("#pw_confirm_comment").text("변경하실 비밀번호를 입력해주세요.");
						} else {
							
						}
					} else {
						$("#pw_search_btn").text("회원정보 확인");
						$("#pw_search_btn").attr("value","0");
						$(".pw_search_checkInfo").attr("disabled", false);
						$("#pw_confirm_comment").addClass("id-pw-warning"); 
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
		
		// user_modify_pw Chk
		
		if($("#user_modify_pw").val() == $("#user_modify_pwChk").val()){
			var callData = "{'request_type':'pwSearch-change', 'request_data':{'mberId':'"+$("#pw_search_id").val()+"', 'groupId':'"+$(".pw_search_group").val()+"', 'password':'"+$(".user_modify_pw").val()+"'}}";
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
  						if(result.data[0].success == "Y"){
  							// 비밀번호 변경창 노출 및 타 입력사항 수정 불가변경

  						} else {
  							$(".pw_search_checkInfo").attr("disabled", true);	
  						}
  					} else {

  						$(".pw_search_checkInfo").attr("disabled", true);
  					}
  				},
  				error : function(e) {
  					console.log(e);
  					$(".pw_search_checkInfo").attr("disabled", true);
  				}
			});
		}
		
		
		
		
		// 데이터 전달 간 장애시 
	}		
    
    
    
    
    
    