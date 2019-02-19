// 단말기 관리
var storage_con_type;
var storage_con_nm;
var storage_con_viewType;
var storage_con_width;
var storage_con_height;
var storage_con_imgTime;
var storage_con_nextCon;
var storage_con_halfSize;
var storage_con_halfBase;
var storage_con_seq;

var select_groupId;
var select_centerId; 
var device_select_getId; 

// 그룹관리
var group_in_select_deviceId;


$(document).ready(function() {
	$("#conCreate").on("click", function(){
		var appendPopupHtml = "";
		appendPopupHtml += "";
		appendPopupHtml += "";
	});
	
	$(".change_view_area").hide();
	
	$("#device_deleteBtn").on("click", function(){
		deviceDelCall(device_select_getId);
	});
	
	$("#equip_allChk").on("click", function(){
		console.log($(this).is(":checked"));
		if($(this).is(":checked")){
			$("input[name=equip_chk_info]").prop("checked", true);
		} else {
			$("input[name=equip_chk_info]").prop("checked", false);
		}
	});
	
});

// 단말기 관리 영역 -------------------------------------------------------------
function deviceDelCall(id){
	if(confirm(id+"를 삭제하시겠습니까?")){
		$.ajax({
			url : '/backoffice/sub/equiManage/modifyEquipDetail.do',
			type : 'POST',
			data : {
				work : 'delete',
				didId : id
			},
			dataType : 'text',
			success : function(result) {
				// console.log(result);
				if(result == "SUCCESS"){
					alert("단말 삭제에 성공하였습니다.");
					callEquipList(select_groupId, select_centerId);
				} else {
					alert("단말 삭제에 실패하였습니다.");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
}

function deviceAddCall(reqData){
	
}



/**
 * #################### 메인 영역 분리 ####################
 * */ 
function mainListAreaFadeIn(type, work){
	if(type == "close"){
		$(".basicBox").show();
		$(".change_view_area").hide();
	
		groupListView(false);
	} else {
		group_in_select_deviceId = "";
		$("input[name=equip_chk_info]:checked").each(function(){
			group_in_select_deviceId += $(this).attr("id");
		});
		group_in_select_deviceId = group_in_select_deviceId.split("equipChk_").filter(Boolean);
		console.log(group_in_select_deviceId);
		$(".basicBox").hide();
		$(".change_view_area").show();
		groupListView(true);
	}
}

/**
 * ##################################################
 * */ 


/**
 * #################### 그룹관리 영역 ####################
 * */
function groupAdminDoAction(){
	var existChk = 0;
	$("input[name=equip_chk_info]:checked").each(function(){
		//$(this).attr("id");
		if($(".GROUPEXIST_"+$(this).attr("id").split("_")[1]).length){
			existChk++;
		}
	});
	if(existChk){
		alert(existChk + "개의 단말이 그룹이 존재합니다.\n그룹 등록진행시 그룹이 변경됩니다.");
	}
}
function groupListView(flag){
	var settinghtml = "";
	if(flag){
		// 표출, 그룹리스트 긁어와서 표출
		$.ajax({
			url : '/backoffice/sub/equiManage/groupListInfo.do',
			type : 'POST',
			data : {
				firstIndex : 0,
				recordCountPerPage : 50,
				searchCondition : "",
				searchKeyword : ""
			},
			dataType : 'json',
			success : function(result) {
				// console.log(result);
				settinghtml = "<tr><th>그룹코드</th><th>그룹명</th><th>단말갯수</th></tr>";
				for(var i = 0; i < result.resultList.length; i++){
					settinghtml += "<tr id='gMain_"+result.resultList[i].groupCode+"' onclick='view_change_gDetail(\""+result.resultList[i].groupCode+"\")' class='groupBasicList' value='0'><td>"+result.resultList[i].groupCode+"</td><td>"+result.resultList[i].groupNm+"</td><td>"+result.resultList[i].didCnt+"개</td></tr>";
				}
				$(".change_view_list_area").html(settinghtml);
			},
			error : function(e) {
				console.log(e);
			}
		});
	} else {
		// 숨기기
		settinghtml = "";
		$(".change_view_list_area").html(settinghtml);
	}
}
function view_change_gDetail(id){
	$(".groupBasicListSelect").removeClass("groupBasicListSelect");
	$(".gDetail").remove();
	if($("#gMain_"+id).attr("value") == "0"){
		// 내용을 불러와 표출
		
		// 
		$.ajax({
			url : '/backoffice/sub/equiManage/DidgroupLst.do',
			type : 'POST',
			data : {
				groupCode : id
			},
			dataType : 'json',
			success : function(result) {
				console.log(result);
				$("#gMain_"+id).attr("value", "1");
				var settinghtml = "<tr class='gDetail_"+id+" gDetail'><td colspan='3'><a onclick='groupInDeviceInsert(\""+id+"\")' class='top_btn' style='padding:3px 32px; margin:0px;'>이 그룹에 단말 등록</a></td></tr>";
				for(var i = 0; i < result.didLst.length; i++){
					settinghtml += "   <tr class='gDetail_"+id+" gDetail'><td colspan='2'>"+result.didLst[i].didNm+"("+result.didLst[i].didId+")</td><td><a class='top_btn' style='padding:3px 12px; margin:0px;'>제외</a></td></tr>";
				}
				$("#gMain_"+id).after(settinghtml);
				$("#gMain_"+id).addClass("groupBasicListSelect");
				$(".groupBasicList").not($("#gMain_"+id)).attr("value", "0");
			},
			error : function(e) {
				console.log(e);
			}
		});
	} else {
		// 내용 제거
		$("#gMain_"+id).attr("value", "0");
	}
}

function groupInDeviceInsert(code){
	$.ajax({
		url : '/backoffice/sub/equiManage/insertGroupInDid.do',
		type : 'POST',
		data : {
			groupCode : code,
			addDeviceId : group_in_select_deviceId.toString()
		},
		dataType : 'text',
		success : function(result) {
			console.log(result);
			var reqArray1 = result.split(",");
			var reqArray2 = "";
			var successCnt = 0;
			for(var i = 0; i < reqArray1.length; i++){
				reqArray2 = reqArray1[i].split("|");
				if(reqArray2[1] == "SUCCESS"){
					successCnt++;
				} 
			}
			alert(successCnt+"개의 단말의 그룹추가를 완료하였습니다.");
		},
		error : function(e) {
			console.log(e);
		}
	});
}
/**
 * ##################################################
 * */ 

function equipRegist(){
	var boxOpen150_p15 = '<div class="pop_box50"><div class="padding15">';
	var appendHtml;
	appendHtml = '<form id="device_addForm" name="device_addForm"><div class="loadingView"><img src="/new/img/loading.gif"/></div>';
	appendHtml += '<div class="popHead">';
	appendHtml += '<h2>단말기 등록</h2></div>';
	appendHtml += '<div class="popCon">';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">단말기명</p>';
	appendHtml += '<input id="equip_regist_nm" name="equip_regist_nm" type="text" class="input_noti" placeholder="단말기명을 입력해주세요.">';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">단말ID</p>';
	appendHtml += '<input id="equip_regist_id" name="equip_regist_id" type="text" class="input_noti" placeholder="자동생성 됩니다." disabled>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">단말IP</p>';
	appendHtml += '<input id="equip_regist_ip" name="equip_regist_ip" type="text" class="input_noti" placeholder="자동생성 됩니다." disabled>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">단말MAC</p>';
	appendHtml += '<input id="equip_regist_mac" name="equip_regist_mac" type="text" class="input_noti" placeholder="자동생성 됩니다." disabled>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">IP형태</p>';
	appendHtml += '<select id="equip_regist_iptype" name="equip_regist_iptype" class="popSel" id="equip_regist_id"><option value="">필수선택</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">OS타입</p>';
	appendHtml += '<select id="equip_regist_os" name="equip_regist_os" class="popSel"><option value="">필수선택</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">관리부서</p>';
	appendHtml += '<select id="equip_regist_role" name="equip_regist_role" class="popSel" onchange="deviceAddSetting(\'centerInfo\', this.options[this.selectedIndex].value)"><option value="">필수선택</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">관리점포</p>';
	appendHtml += '<select id="equip_regist_center" name="equip_regist_center" class="popSel"><option value="">필수선택</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit" style="display:inline-block;">그룹정보</p><img class="detailSearchBtn" src="/new/img/searchicon.png" />';
	appendHtml += '<select id="equip_regist_group" name="equip_regist_group" class="popSel"><option value>선택하세요.</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">운영시간</p>';
	appendHtml += '<select id="equip_regist_openTime" name="equip_regist_openTime" class="popSel" style="width: 49%;margin-right: 1%;"><option value>시작시간</option></select>';
	appendHtml += '<select id="equip_regist_closeTime" name="equip_regist_closeTime" class="popSel" style="width: 49%;margin-right: 1%;"><option value>종료시간</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">송출사항</p>';
	appendHtml += '<select id="equip_regist_systemType" name="equip_regist_systemType" class="popSel"><option value="">필수선택</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">단말형태</p>';
	appendHtml += '<select id="equip_regist_deviceType" name="equip_regist_deviceType" class="popSel"><option value="">필수선택</option></select>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<div style="display: flow-root; margin-right: 4px;"><p class="pop_tit" style="float:left;">단말해상도</p><p id="resolution_write_btn"class="pop_tit" style="float:right;">수기입력</p></div>';
	appendHtml += '<select id="equip_regist_resolution" name="equip_regist_resolution" class="popSel" onchange="deviceAddResolutionSet(this.options[this.selectedIndex].value)"><option value="">필수선택</option></select>';
	appendHtml += '<div class="equip_regist_resolution_write"><input type="text" id="equip_regist_resolution_w_width" name="equip_regist_resolution_w_width" class="input_noti input_noti2" placeholder="가로 사이즈 입력"><input type="text" id="equip_regist_resolution_w_height" name="equip_regist_resolution_w_height" class="input_noti input_noti2" placeholder="세로 사이즈 입력"></div>';
	appendHtml += '</div></div>';
	appendHtml += boxOpen150_p15;
	appendHtml += '<p class="pop_tit">사용유무</p>';
	appendHtml += '<input type="radio" name="equip_regist_use" id="equip_regist_useY"><label for="equip_regist_useY">사용</label>';
	appendHtml += '<input type="radio" name="equip_regist_use" id="equip_regist_useN"><label for="equip_regist_useN">사용안함 </label>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="clearfix"></div></div>';
	appendHtml += '<div class="pop_footer"><div class="needpopup_search_area"><p class="search_area_title">점포검색</p><input type="text" class="input_noti pop_search_input" placeholder="점포명을 입력해주세요."></div><a class="top_btn" onclick="deviceAddSubmit()">단말기 등록</a></div></form>';
	
	$("#new_Equip").html(appendHtml);
	
	$(".needpopup_search_area").hide();
	$(".equip_regist_resolution_write").hide();
	
	/* 단말 해상도 입력 부 */
	var resolution_write = false;
	$("#resolution_write_btn").css("cursor", "pointer");
	$("#resolution_write_btn").on("click", function(){
		resolution_write = !resolution_write;
		if(resolution_write){
			$("#resolution_write_btn").text("선택입력");
			$("#equip_regist_resolution").hide();
			$(".equip_regist_resolution_write").show();
		} else {
			$("#resolution_write_btn").text("수기입력");
			$("#equip_regist_resolution").show();
			$(".equip_regist_resolution_write").hide();	
		}
	});
	
	
	 
	// 이후 각 항목별 ID 값을 가지고 데이터 수정 작업 진행
	// 팝업창 표출 및 데이터 정의 간 로딩바 표출
	selectTime($("#equip_regist_openTime"), "00:00", "시작시간", "");
	selectTime($("#equip_regist_closeTime"), "00:00", "종료시간", "");
   	$.ajax({
		url : '/backoffice/sub/equiManage/selectEquipRegistComboData.do',
		type : 'POST',
		data : {
			callType : "basicInfo"
		},
		dataType : 'json',
		success : function(result) {
			console.log(result);
			
			var ipTypeInfo = result.selectIpType;
			var modelTypeInfo = result.selectModelType;
			var osInfo = result.selectOs;
			var resolutionInfo = result.selectResolution;
			var roleListInfo = result.selectRole;
			var typeInfo = result.selectType;
			
			
			// IP형태 세팅
			if(ipTypeInfo.length > 0){
				var appendData = "";
				for(var i = 0; i < ipTypeInfo.length; i++){
					appendData += "<option value='"+ipTypeInfo[i].code+"'>"+ipTypeInfo[i].codeNm+"</option>";
				}
				$("#equip_regist_iptype").html(appendData);
			}
			// 송출사항 세팅
			if(modelTypeInfo.length > 0){
				var appendData = "";
				for(var i = 0; i < modelTypeInfo.length; i++){
					appendData += "<option value='"+modelTypeInfo[i].code+"'>"+modelTypeInfo[i].codeNm+"</option>";
				}
				$("#equip_regist_systemType").html(appendData);
			}
			// OS타입 세팅
			if(osInfo.length > 0){
				var appendData = "";
				for(var i = 0; i < osInfo.length; i++){
					appendData += "<option value='"+osInfo[i].code+"'>"+osInfo[i].codeNm+"</option>";
				}
				$("#equip_regist_os").html(appendData);
			}
			// 단말해상도 세팅
			if(resolutionInfo.length > 0){
				var appendData = "";
				for(var i = 0; i < resolutionInfo.length; i++){
					if(resolutionInfo[i].code != "DIDRES00"){ // DIDRES00은 기타로써 기존 수기입력 기준 값으로 이용되었으나 별도 버튼화 함
						appendData += "<option value='"+resolutionInfo[i].code+"'>"+resolutionInfo[i].codeNm+"</option>";	
					}
				}
				$("#equip_regist_resolution").html(appendData);
				deviceAddResolutionSet("DIDRES01");
			}
			// 단말형태 세팅
			if(typeInfo.length > 0){
				var appendData = "";
				for(var i = 0; i < typeInfo.length; i++){
					appendData += "<option value='"+typeInfo[i].code+"'>"+typeInfo[i].codeNm+"</option>";
				}
				$("#equip_regist_deviceType").html(appendData);
			}
			// 부서리스트 세팅
			if(roleListInfo.length > 0){
				var appendData = "";
				var levelNbsp;
				//console.log(clickGroupId);
				for(var i = 0; i < roleListInfo.length; i++){
					levelNbsp = "";
					for(var j = 1; j < parseInt(roleListInfo[i].lv); j++){
						levelNbsp += "&nbsp&nbsp";
					}
					if(roleListInfo[i].groupId == clickGroupId){
						appendData += "<option value='"+roleListInfo[i].groupId+"' selected>["+roleListInfo[i].lv+"]"+levelNbsp+roleListInfo[i].groupNm+"</option>";	
					} else {
						appendData += "<option value='"+roleListInfo[i].groupId+"'>["+roleListInfo[i].lv+"]"+levelNbsp+roleListInfo[i].groupNm+"</option>";	
					}
				}
				$("#equip_regist_role").html(appendData);
				deviceAddSetting("centerInfo", $("#equip_regist_role").val());
			}
			$(".loadingView").hide();
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function deviceAddSetting(mCallType, mCallDetail){
	//console.log("deviceAddSetting ! " + mCallType + " | " + mCallDetail);
   	$.ajax({
		url : '/backoffice/sub/equiManage/selectEquipRegistComboData.do',
		type : 'POST',
		data : {
			callType : mCallType,
			callDetail : mCallDetail
		},
		dataType : 'json',
		success : function(result) {
			//console.log(result);
			if(mCallType == "centerInfo"){
				if(result.selectCenter.length > 0){
					var appendData = "";
					for(var i = 0; i < result.selectCenter.length; i++){
						appendData += "<option value='"+result.selectCenter[i].centerId+"'>"+result.selectCenter[i].centerNm+"</option>";
					}
					$("#equip_regist_center").html(appendData);	
				} else {
					alert("해당 부서에는 소속 점포가 없습니다.\n점포를 생성 후 단말기를 추가할 수 있습니다.")
				}
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function deviceAddResolutionSet(data){
	// equip_regist_resolution equip_regist_resolution_w_height
	// console.log(data);
	switch(data){
		case "DIDRES01" : $("#equip_regist_resolution_w_width").val("1920"); $("#equip_regist_resolution_w_height").val("1080"); break;
	    case "DIDRES02" : $("#equip_regist_resolution_w_width").val("3840"); $("#equip_regist_resolution_w_height").val("1080"); break;
	    case "DIDRES03" : $("#equip_regist_resolution_w_width").val("1080"); $("#equip_regist_resolution_w_height").val("1920"); break;
	    case "DIDRES04" : $("#equip_regist_resolution_w_width").val("1080"); $("#equip_regist_resolution_w_height").val("3840"); break;
	}
}

function deviceAddSubmit(){
	// console.log($("form[name=device_addForm]").serialize());
   	$.ajax({
		url : '/backoffice/sub/equiManage/modifyEquipDetail.do',
		type : 'POST',
		data : $("form[name=device_addForm]").serialize() + "&work=insert",
		dataType : 'text',
		success : function(result) {
			console.log(result);
		},
		error : function(e) {
			console.log(e);
		}
	});
	
}


// ==========================================================================================

function newGroupAdd(){
	
	console.log("newGroupAdd in");
	
	var createPop = "";
	createPop = "<div class='popHead'><h2>그룹 생성</h2></div>";
	createPop += "<div class='popCon'><form id='group_addForm' name='group_addForm'>";
	createPop += "    <div class='pop_box50'><div class='padding15'><p class='pop_tit'>그룹 코드</p><input type='text' id='new_group_code' name='new_group_code' class='input_noti' placeholder='자동생성됩니다' readonly/></div></div>";
	createPop += "    <div class='pop_box50'><div class='padding15'><p class='pop_tit'>그룹 명</p><input type='text' id='new_group_nm' name='new_group_nm' class='input_noti'/></div></div>";
	createPop += "</form></div>";
	createPop += "<div class='clearfix'></div>";
	createPop += "<div class='pop_footer'><a class='top_btn' onclick='newGroupAddSubmit()'>등록</a></div>";
	$("#new_group_add").html(createPop);
	
}

function newGroupAddSubmit(){
   	$.ajax({
		url : '/backoffice/sub/equiManage/modifyGroupInfo.do',
		type : 'POST',
		data : $("form[name=group_addForm]").serialize() + "&work=INSERT",
		dataType : 'text',
		success : function(result) {
			console.log(result);
		},
		error : function(e) {
			console.log(e);
		}
	});
}

