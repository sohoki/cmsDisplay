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


$(document).ready(function() {
	$("#conCreate").on("click", function(){
		var appendPopupHtml = "";
		
		appendPopupHtml += "";
		appendPopupHtml += "";
		
	});
});

function equipRegist(){
	var appendHtml;
	appendHtml = '<div class="loadingView"><img src="/new/img/loading.gif"/></div>';
	appendHtml += '<div class="popHead">';
	appendHtml += '<h2>단말기 등록</h2></div>';
	appendHtml += '<div class="popCon">';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">단말기명</p>';
	appendHtml += '<input id="equip_regist_nm" type="text" class="input_noti" placeholder="단말기명을 입력해주세요.">';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">단말ID</p>';
	appendHtml += '<input id="equip_regist_id" type="text" class="input_noti" placeholder="자동생성 됩니다." disabled>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">단말IP</p>';
	appendHtml += '<input id="equip_regist_ip" type="text" class="input_noti" placeholder="자동생성 됩니다." disabled>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">단말MAC</p>';
	appendHtml += '<input id="equip_regist_mac" type="text" class="input_noti" placeholder="자동생성 됩니다." disabled>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">IP형태</p>';
	appendHtml += '<select id="equip_regist_iptype" class="popSel" id="equip_regist_id"><option value="">유선고정</option><option value="">무선고정</option><option value="">유선유동</option><option value="">무선유동</option></select>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">OS타입</p>';
	appendHtml += '<select id="equip_regist_os" class="popSel"><option value="">안드로이드</option><option value="">윈도우</option><option value="">IOS</option></select>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">관리부서</p>';
	appendHtml += '<select id="equip_regist_role" class="popSel"><option value>선택하세요.</option></select>         ';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">관리점포</p>';
	appendHtml += '<select id="equip_regist_center" class="popSel"><option value>선택하세요.</option></select>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">그룹정보</p>';
	appendHtml += '<select id="equip_regist_group" class="popSel"><option value>선택하세요.</option></select>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">운영시간</p>';
	appendHtml += '<select id="equip_regist_openTime" class="popSel" style="width: 49%;margin-right: 1%;"><option value>시작시간</option></select>';
	appendHtml += '<select id="equip_regist_closeTime" class="popSel" style="width: 49%;margin-right: 1%;"><option value>종료시간</option></select>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">송출사항</p>';
	appendHtml += '<select id="equip_regist_systemType" class="popSel"><option value>일반</option><option value="">미러</option><option value="">보이스POP</option><option value="">음원방송</option></select>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">단말형태</p>';
	appendHtml += '<select id="equip_regist_deviceType" class="popSel"><option value="">가로</option><option value="">세로</option><option value="">보이스POP</option><option value="">음원방송</option></select>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<div style="display: flow-root; margin-right: 4px;"><p class="pop_tit" style="float:left;">단말해상도</p><p id="resolution_write_btn"class="pop_tit" style="float:right;">수기입력</p></div>';
	appendHtml += '<select id="equip_regist_resolution" class="popSel"><option value>선택하세요.</option><option value="">1920*1080</option></select>';
	appendHtml += '<div class="equip_regist_resolution_write"><input type="text" class="input_noti input_noti2" placeholder="가로 사이즈 입력"><input type="text" class="input_noti input_noti2" placeholder="세로 사이즈 입력"></div>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="pop_box50"><div class="padding15">';
	appendHtml += '<p class="pop_tit">사용유무</p>';
	appendHtml += '<input type="radio" name="pop_radio2" id="equip_regist_useY"><label for="equip_regist_useY">사용</label>';
	appendHtml += '<input type="radio" name="pop_radio2" id="equip_regist_useN"><label for="equip_regist_useN">사용안함 </label>';
	appendHtml += '</div></div>';
	appendHtml += '<div class="clearfix"></div></div>';
	appendHtml += '<div class="pop_footer"><div class="needpopup_search_area"><p class="search_area_title">점포검색</p><input type="text" class="input_noti pop_search_input" placeholder="점포명을 입력해주세요."></div><a class="top_btn">단말기 등록</a></div>';

	$("#new_Equip").html(appendHtml);
	
	$(".loadingView").on("click", function(){
		$(this).hide();
	});
	
	
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
	
	
	
	
	
	
	
	
	
}