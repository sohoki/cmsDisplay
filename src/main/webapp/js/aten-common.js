function selectTime(element, defaultValue, defaultText, existValue){
	var newOptions = {"00:00": "00:00","01:00": "01:00","02:00": "02:00","03:00": "03:00","04:00": "04:00","05:00": "05:00","06:00": "06:00",
			"07:00": "07:00","08:00": "08:00","09:00": "09:00","10:00": "10:00","11:00": "11:00","12:00": "12:00","13:00": "13:00","14:00": "14:00",
			"15:00": "15:00","16:00": "16:00","17:00": "17:00","18:00": "18:00","19:00": "19:00","20:00": "20:00","21:00": "21:00","22:00": "22:00","23:00": "23:00"
	};
	var e1 = element;
	e1.empty(); // remove old options
	e1.append($("<option></option>").attr("value", defaultValue).text(defaultText));
	$.each(newOptions, function(key,value) {
		if(value == existValue){
			e1.append($("<option></option>").attr("value", value).attr("selected", "selected").text(key));
		} else {
			e1.append($("<option></option>").attr("value", value).text(key));
		}
	  
	});
}

	function grp_search(element, searchValue, require){
		// require ? 1 : require, 2 : not require

		$.ajax({
			url: "/backoffice/sub/equiManage/groupSearch.do",
			async : false,
			type: "POST",
			data : {
				"grpSearchKeyword" : searchValue
			},
			success: function(result){
				if (result != null) {
					if(result.resultMap != null){
						
						element.empty();
						if(require == 2){
							element.append("<option value=''>선택안함</option>");
						}
						for(var i = 0; i < result.resultMap.length; i++){
							var obj = result.resultMap[i];
							var groupName = obj.groupNm;
							if (obj.groupNm == null){
								groupName = "";
							} else {
								groupName = obj.groupNm;
							}
							
							
							$("<option value='"+ obj.groupCode +"' selected>"+ groupName +"</option>").appendTo(element);
							
							
						}
					}
				} else {
					alert("조회에 실패하였습니다.");
				}	
			}
		});

	}
	
	function open_grpReg(){
		alert("그룹을 등록 후 '검색'시 신규 등록한 그룹이 표출 됩니다.");
    	window.open("/backoffice/sub/equiManage/did_groupList.do", "_blank");
	}
	
	function preview_group(code, gNm){
		var didDetailHtm = "";
  	
		apiExecute(
			"POST", 
			"/backoffice/sub/equiManage/DidgroupLst.do",
			{
				groupCode : code
			},
			null,				
			function(result) {
				$("#spTitle").html("<a onclick='view_connPage(&#39;g&#39;,&#39;"+code+"&#39;)' style='cursor:pointer; color:#fff;'>["+gNm+"]</a>" + " 그룹 단말 리스트");
				$("#subSpTitle").html("* 그룹 단말 클릭시 단말 정보 확인이 가능합니다.");
				didDetailHtm = "<tbody>";
				if (result != null) {
					if (result.didLst != null) {
						for (var i=0; i<result.didLst.length; i++) {
							var obj = result.didLst[i];
							didDetailHtm += "<tr><td>"+ (i+1)  +"</td><td><a style='color:#fff; cursor:pointer;' onclick='view_connPage(&#39;d&#39;,&#39;"+obj.didId+"&#39;)'>"+ obj.didNm  +"</a></td></tr>";	     									
						}	  
					}    
					didDetailHtm += "</tbody>";
					$("#basicList").html(didDetailHtm);
				}
			},
			function(request){
						
			},
			null
		);   
	}

    function view_connPage(req, seq){
    	var reqLink = "";
    	switch(req){
    		case "d"  : reqLink = "/backoffice/sub/equiManage/didView.do?didId="+seq; 		   break;
    		case "c"  : reqLink = "/backoffice/sub/conManage/conMutiView_back.do?conSeq="+seq; break;
    		case "s"  : reqLink = "/backoffice/sub/equiManage/schView.do?schSeq="+seq;		   break;
    		case "mc" : reqLink = "/backoffice/sub/conManage/conMutiDetail.do?mode=Ins&conSeq="+0; 	   break;
    		case "ms" : reqLink = "/backoffice/sub/equiManage/schDetail.do?mode=Ins&schCode="+0;		   break;
    		case "g"  : reqLink = "/backoffice/sub/equiManage/did_groupList.do?searchCondition=GROUP_ID&searchKeyword="+seq; break;
    	}
    	window.open(reqLink, "_blank");
    }

    
    function center_search(element, searchValue){

		$.ajax({
			url: "/backoffice/sub/equiManage/centerSearch.do",
			async : false,
			type: "POST",
			data : {
				"cenSearchKeyword" : searchValue
			},
			success: function(result){
				if (result != null) {
					if(result.resultMap != null){
						
						element.empty();
						 
						for(var i = 0; i < result.resultMap.length; i++){
							var obj = result.resultMap[i];
							
							var centerNm = obj.centerNm;
							if (centerNm == null){
								centerNm = "";
							}
							$("<option value='"+ obj.centerId +"'>"+ centerNm +"</option>").appendTo(element);
						}
					}
				} else {
					alert("조회에 실패하였습니다.");
				}	
			}
		});

	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    var join_id_chk = false;
	var join_pw_chk = false;
	var modify_pw_chk = false;
    
	$(document).ready(function() {
		
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
    
    
    
    
    
    