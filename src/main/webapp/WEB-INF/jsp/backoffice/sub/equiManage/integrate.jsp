<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta content="initial-scale=1, maximum-scale=1.0, minimum-scale=1.0, width=device-width, user-scalable=no" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="format-detection" content="telephone=no">
    <title>이마트CMS</title>
    <link rel="stylesheet" href="/new/css/reset.css">
    <link rel="stylesheet" href="/new/css/layout.css">    
    <link rel="stylesheet" href="/new/css/paragraph.css"> 
    <script src="/new/js/jquery.min.js"></script>
    <!--[if lte IE 8]>
    <script src="js/poly-checked.min.js"></script> 
    <![endif]-->
    <!--popup-->
    <link rel="stylesheet" href="/new/css/needpopup.css">
    <!--datepicker-->
    <link rel="stylesheet" href="/new/css/calendar.css">
	

	<script>
	var groupLstFirstIdx	= "0";
	var groupLstLastIdx 	= "0";
	var groupLstRecordCnt 	= "10";
	
	var centerLstFirstIdx	= "0";
	var centerLstLastIdx 	= "0";
	var centerLstRecordCnt 	= "10";
	
	var euqipLstFirstIdx	= "0";
	var euqipLstLastIdx 	= "0";
	var euqipLstRecordCnt 	= "10";
	
	var selectGroupId;
	var selectCenterId;
	var loginAuthorCode;
	var loginAuthorType;
	var nowViewSystemType;
	
	$(document).ready(function(){
		/* 페이지 최초 호출 간 작업 사항 순서 절대 수정금지 */
		
		
		roleSetting();
		
		selectGroupId	= "${groupId}";
		selectCenterId	= "${centerId}";
		loginAuthorCode	= "${authorCode}";
				
		if(loginAuthorCode == "ROLE_ADMIN" || loginAuthorCode == "ROLE_ANONYMOUS" || loginAuthorCode == "ROLE_USER_MEMBER" ){
			// 통합
			nowViewSystemType = "SIGNAGE";
			loginAuthorType = "ALL";
		} else if(loginAuthorCode == "ROLE_DID_ADMIN" || loginAuthorCode == "ROLE_DID_USER"){
			// 사이니지
			nowViewSystemType = "SIGNAGE";
			loginAuthorType = "SIGNAGE";
		} else if(loginAuthorCode == "ROLE_MUSIC_ADMIN" || loginAuthorCode == "ROLE_MUSIC_USER"){
			// 음원방송
			nowViewSystemType = "MUSIC";
			loginAuthorType = "MUSIC";
		}

		
		// 부서 내 점포 리스트 호출
		
		roleInCenterListCall(selectGroupId);
		callEquipList(selectGroupId, selectCenterId); // 단말 리스트 호출
		
		// select_device_orderBtn  select_device_info   device_select_before
		
		$(".select_device_orderBtn").attr("style", "opacity:0;");
		
		$("#selectEquipNm").text("단말기를 선택해주세요.");
		
		/* 페이지 최초 호출 간 작업 사항 종료 */
		
	});
	
	function centerListSetting(code){
		if(code == "0"){
			$("#centerList_close_btn").addClass("noneDisplaySet");
			$("#centerList_open_btn").removeClass("noneDisplaySet");
			
			$(".leftRoleList").attr("style", "height:700px;");
			$(".leftCenterList").addClass("noneDisplaySet");
			
		} else {
			$("#centerList_open_btn").addClass("noneDisplaySet");
			$("#centerList_close_btn").removeClass("noneDisplaySet");
			
			$(".leftRoleList").attr("style", "");
			$(".leftCenterList").removeClass("noneDisplaySet");
		}
	}
	
	function systemType(type){
		
		if(type == "SIGNAGE"){
			if(loginAuthorType == "MUSIC"){alert("열람권한이 없습니다.");return false;}
			
		} else if(type == "MUSIC"){
			if(loginAuthorType == "SIGNAGE"){alert("열람권한이 없습니다.");return false;}

		}
	}
	
	function roleInCenterListCall(groupId){
		// centerList
		
		centerListSetting("1");
		
		$(".equipListBody").html("");
		$(".roleGroupList.select").removeClass("select");
		$("#roleGroupInfo_"+groupId).addClass("select");
		
		$.ajax({
			url : '/backoffice/sub/conManage/selectIntegrateCetnerList.do',
			type : 'POST',
			data : {
				'groupId'	: groupId, 
				'firstIdx'	: centerLstFirstIdx,
				'lastIdx'	: centerLstLastIdx,
				'recordCnt'	: centerLstRecordCnt
				// 'systemType'	: nowViewSystemType
			},
			dataType : 'json',
			success : function(result) {
				if (result) {
					console.log(result.centerList);
					var centerListAppend = "";
					
					if(result.centerList.length != 0){
						
						var data = result.centerList;
						
						for(var i = 0; i < result.centerList.length; i++){
							centerListAppend = "<li id='centerInfo_"+data[i].centerId+"' class='centerList' onclick='javascript:callEquipList(&#39;"+groupId+"&#39;, &#39;"+data[i].centerId+"&#39;)'>"+data[i].centerNm+"</li>";
						}
					} else {
						centerListAppend = "<div style='text-align:center;'>등록 된 점포가 없습니다.</div>";
					}
					$(".centerListBody").html(centerListAppend);
				}
			},
			error : function(e) {
				console.log("fail");
				console.log(e);
			}
		});
	}
	
	function callEquipList(groupId, centerId){
		
		$(".equipListBody").html("");
		$(".centerList.select").removeClass("select");
		$("#centerInfo_"+centerId).addClass("select");
		
		$.ajax({
			url : '/backoffice/sub/conManage/selectIntegrateEquipList.do',
			type : 'POST',
			data : {
				'groupId'	: groupId,
				'centerId'	: centerId,
				'firstIdx'	: euqipLstFirstIdx,
				'lastIdx'	: euqipLstLastIdx,
				'recordCnt'	: euqipLstRecordCnt
			},
			dataType : 'json',
			success : function(result) {
				//if (result.) {
					console.log(result.equipList);
					
					if(result.equipList.length > 0){
						
						$(".roleGroupList.select").removeClass("select");
						$("#roleGroupInfo_"+groupId).addClass("select");
						
						
						var equipListAppend = "";
						for(var i = 0; i < result.equipList.length; i++){
							
							var osType = "";
							var onOffStatus = "";
							var useStatus = "";
							switch(result.equipList[i].didOs){
								case "안드로이드"	: osType = '<span class="androidIcon"></span>'; break;
								case "윈도우" 	: osType = '<span class="windowsIcon"></span>'; break;
								case "IOS"		: osType = '<span class="iosIcon"></span>'; break;
								default 		: osType = '<span class="androidIcon"></span>'; break;
							}
							switch(result.equipList[i].didSttus){
								case "ON"	: onOffStatus = '<span class="onIcon"></span>'; break;
								case "OFF"	: onOffStatus = '<span class="offIcon"></span>'; break;
								default 	: onOffStatus = '<span class="offIcon"></span>'; break;
							}
							switch(result.equipList[i].didUseYn){
								case "Y"	: useStatus = '<span class="onIcon"></span>'; break;
								case "N"	: useStatus = '<span class="offIcon"></span>'; break;
								default 	: useStatus = '<span class="offIcon"></span>'; break;
							}
							
							
							equipListAppend += '<tr class="equipList list_'+result.equipList[i].didId+'">';
							equipListAppend += '<td><input type="checkbox" id="equipChk_'+i+'" class="equipChkList"><label for="equipChk_'+i+'"></label></td>';
							equipListAppend += '<td>'+result.equipList[i].didId+'</td>';
							equipListAppend += '<td onclick="javascript:equipDetailCall(&#39;'+result.equipList[i].didId+'&#39;);">'+result.equipList[i].didNm+'</td>';
							equipListAppend += '<td>'+result.equipList[i].schCnt+'개</td>';
							equipListAppend += '<td>'+onOffStatus+'</td>';
							equipListAppend += '<td>'+osType+'</td>';
							equipListAppend += '<td>'+useStatus+'</td>';
							equipListAppend += '<td>'+result.equipList[i].didWidth+'*'+result.equipList[i].didHeight+'</td>';        
							equipListAppend += "</tr>";
						}
						$(".equipListBody").html(equipListAppend);
					}

				//}
			},
			error : function(e) {
				console.log("fail");
				console.log(e);
				$(".equipListBody").html("<tr><td colspan='8'><div style='text-align:center;'>데이터 호출간 장애가 발생하였습니다.<br>재시도 부탁드립니다.</div></td></tr>");
			}
		});
	}
	
	
	function equipDetailCall(id){
		
		/* $(".equipListBody").html("");
		$(".centerList.select").removeClass("select");
		$("#centerInfo_"+centerId).addClass("select"); */
		
		$(".equipListSelect").removeClass("equipListSelect");
		
		$.ajax({
			url : '/backoffice/sub/conManage/selectIntegrateEquipInfo.do',
			type : 'POST',
			data : {
				'didId'	: id
			},
			dataType : 'json',
			success : function(result) {
				
				$(".list_"+id).addClass("equipListSelect");
				
				console.log(result);	
				
				if(result.equipInfo.didId != ""){
					$("#selectEquipId").text(result.equipInfo.didId);
					$("#selectEquipNm").text(result.equipInfo.didNm);
					$("#selectEquipGroupNm").text(result.equipInfo.groupNm);
					$("#selectEquipIp").text(result.equipInfo.didIpaddr);
					$("#selectEquipMac").text(result.equipInfo.didMac);
					$("#selectEquipIpType").text(result.equipInfo.didIptype);
					$("#selectEquipType").text(result.equipInfo.didType);
					$("#selectEquipW").text(result.equipInfo.didWidth);
					$("#selectEquipH").text(result.equipInfo.didHeight);
					$("#selectEquipModelType").text(result.equipInfo.didModelType);
					$("#selectEquipRuntime").text(result.equipInfo.didStartTime+"~"+result.equipInfo.didEndTime);
					$("#selectEquipLastconn").text(result.equipInfo.didEndContime);
					
					var remarkTxt = "";
					if(!result.equipInfo.didRemark){
						remarkTxt = "기록 된 내용이 없습니다.";
					} else {
						remarkTxt = result.equipInfo.didRemark;
					}
					
					$("#selectEquipMsg").html("<span onclick='euqipRemarkWrite(&#39;"+result.equipInfo.didId+"&#39;);'>"+remarkTxt+"</span>");	
					
					
				}
				
				if(result.equipSchList.length > 0){
					
					//$("#selectEquipSchInfo") // 날짜삽입
					/* $("#selectSchName").text();
					$("#selectConName").text();
					$("#selectSch").text(); */
					
				}
				
				
			},
			error : function(e) {
				console.log("fail");
				console.log(e);
				$(".equipListBody").html("<tr><td colspan='8'><div style='text-align:center;'>데이터 호출간 장애가 발생하였습니다.<br>재시도 부탁드립니다.</div></td></tr>");
			}
		});
		
	}
	
	function euqipRemarkWrite(id){
		// 단말 내 비고 작성
		
		
		
	}
	
	function remarkBtnAction(el){
		var val = $(el).attr("value");
		switch(val){
			case "0" : $(el).text("등록하기"); remarkModifyMode(el); break;
			case "1" : $(el).text("수정하기"); remarkSubmitMode(el); break;
		}
	}
	function remarkModifyMode(el){
		$(el).attr("value", "1");
	}
	function remarkSubmitMode(el){
		
		
		$(el).attr("value", "0");
		/* $.ajax({
			url : '/backoffice/sub/conManage/selectIntegrateEquipInfo.do',
			type : 'POST',
			data : {
				'didId'	: id
			},
			dataType : 'json',
			success : function(result) {				
				console.log(result);
				
			},
			error : function(e) {
				console.log("fail");
				console.log(e);
			}
		}); */
	}
	
	function roleSetting(){
		console.log("${jsonView }");
		
	}
	
	
	</script>
	<style>
	 	.equipList:hover{
			background-color: #e1e2e3;
		}
		.centerList{
			cursor: pointer;
		}
		.roleGroupList{
			cursor: pointer;
		}
		.equipListSelect{
			color:#FF0000;
			background-color: #fafbfc;
		}
		.equipList {
			cursor: pointer;
		}
		#selectEquipMsg{
			cursor: pointer;
		}
	</style>
</head>
<body>
	<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/equiManage/integrate.do">
	</form:form>
    <div id="wrapper">
        <!--//HEADER-->
        <c:import url="/backoffice/inc/cms_header.do" />
        <!--HEADER//-->
        <div class="container">
            <div class="content">

                <!--//통합관리좌측메뉴끝-->
                <div class="box03-1">
                    <!--분야분류-->
                    <ul class="total_tab">
                        <li><a href="javascript:systemType('SIGNAGE');" class="select">사이니지</a></li>
                        <li><a href="javascript:systemType('MUSIC');">음원방송</a></li>
                        <div class="clearfix"></div>
                    </ul>   
                    <div class="total_left leftRoleList">
                        <ul class="total_list">
                        <!-- <li class="select">스타필드</li> 선택시 addClass select -->
                        <c:forEach items="${roleList }" var="roleinfo" varStatus="status">
							<li class="roleGroupList <c:if test="${status.first}">select</c:if>" id="roleGroupInfo_${roleinfo.groupId}"onclick="javascript:roleInCenterListCall('${roleinfo.groupId}')">${roleinfo.roleNm}</li>
						</c:forEach>
                        </ul>
                        <!-- <div class="total_num">
                            <span class="page_icon01"></span>
                            <span class="page_icon02"></span>
                            <ul>
                                <li class="select">1</li>
                                <li>2</li>
                                <li>3</li>
                                <li>4</li>
                                <li>5</li>
                            </ul>
                            <span class="page_icon03"></span>
                            <span class="page_icon04"></span>
                        </div> -->
                    </div> 
                    <!--점포분류-->
                    <div class="total_leftB">
                    <span class="total_settingBtn"><a href=""></a></span>
                    <span class="leftIconWithTxt">점포</span>
                    <img src="/img/close_fold_icon.png" id="centerList_close_btn" class="total_foldBtn" onclick="centerListSetting('0');" />
                    <img src="/img/open_fold_icon.png" id="centerList_open_btn" class="total_foldBtn noneDisplaySet" onclick="centerListSetting('1');" />
                    </div>
                    <div class="total_left border-bottom leftCenterList">
                        <ul class="total_list centerListBody">
<%-- 						<c:forEach items="${centerList }" var="centerinfo" varStatus="status">
							<li onclick="javascript:centerSelect('${centerinfo.centerId}')">${centerinfo.centerNm}</li>
						</c:forEach> --%>
                        </ul>
                        <!-- <div class="total_num">
                            <span class="page_icon01"></span>
                            <span class="page_icon02"></span>
                            <ul>
                                <li class="select">1</li>
                                <li>2</li>
                                <li>3</li>
                                <li>4</li>
                                <li>5</li>
                            </ul>
                            <span class="page_icon03"></span>
                            <span class="page_icon04"></span>
                        </div> -->
                    </div> 
                </div>
                <!--통합관리좌측메뉴//-->
                
                <!--//통합관리 list-->
                <div class="box03-2">
                    <div class="padding30">
                        <!--// 상단 메뉴-->
                        <div class="list_box01">
                            <div class="float-left">
                                <a href="" class="top_btn">단말기 삭제</a>
                                <a data-needpopup-show="#new_Equip" class="top_btn">단말기 등록</a>
                                <a href="" class="top_btn" title="선택 된 단말들을 한 그룹에 추가합니다.">그룹등록</a>
								<a data-needpopup-show="#did_pop02" class="top_btn">스케줄 관리</a>
                        		<a href="" class="top_btn">화면구성</a>
                                <a data-needpopup-show="#did_pop03" class="top_btn">메세지전송</a>
                            </div>
                            <div class="float-right">
                                <select id="searchSel">
                                    <option value="" selected="selected" disabled="disabled">조건검색</option>
                                    <option value="1">단말기ID</option>
                                    <option value="2">단말기명</option>
                                </select>
                                <input type="text" name="" class="list_searchT">
                                <a href="" class="list_searchBtn"></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <!--// 단말기리스트-->
                        <table class="list01">
                            <thead>
                                <tr>
                                    <th>
                                        <input type="checkbox" id="did_chk1">
                                        <label for="did_chk1"></label>
                                    </th>
                                    <th>단말기ID</th>
                                    <th>단말기명</th>
                                    <th>스케줄현황</th>
                                    <th>네트워크</th>
                                    <th>OS버전</th>
                                    <th>사용현황</th>
                                    <th>해상도</th>
                                </tr>
                            </thead>
                            <tbody class="equipListBody">
                            </tbody>
                        </table>
                        <!--단말기리스트 //-->
                    </div>
                </div>
                <!--통합관리 list//-->
                
                <!--// 통합관리 우측 정보list-->
                <div class="box03-3">
                    <div class="list_box01 select_device_orderBtn">
                        <a href="" class="top_btn">수정</a>
                        <a data-needpopup-show="#did_pop01" class="top_btn">장비통신 이력</a>
                        <a href="" class="top_btn">재부팅</a>
                        <!-- <a href="" class="top_btn">원격지원</a> -->

                        <!-- <a href="" class="top_btn">메세지 전송</a> -->
                    </div>
                    <!--// 해당 단말기 정보-->
                    <table class="list02 select_device_info">
                        <tbody>
                            <tr>
                                <th>단말기ID</th>
                                <td id="selectEquipId"></td>
                            </tr>
                            <tr>
                                <th>단말기명</th>
                                <td id="selectEquipNm"></td>
                            </tr>
                            <tr>
                                <th>그룹명</th>
                                <td id="selectEquipGroupNm"></td>
                            </tr>
                            <tr>
                                <th>단말 IP</th>
                                <td id="selectEquipIp"></td>
                            </tr>
                            <tr>
                                <th>단말 MAC</th>
                                <td id="selectEquipMac"></td>
                            </tr>
                            <tr>
                                <th>IP형태</th>
                                <td id="selectEquipIpType"></td>
                            </tr>
                            <tr>
                                <th>DID형태</th>
                                <td id="selectEquipType"></td>
                            </tr>
                            <tr>
                                <th>단말해상도</th>
                                <td>
                                	가로 <span id="selectEquipW"></span>
                                	 세로 <span id="selectEquipH"></span>
                                </td>
                            </tr>
                            <tr>
                                <th>기기타입</th>
                                <td id="selectEquipModelType"></td>
                            </tr>
                            <tr>
                                <th>운영시간</th>
                                <td id="selectEquipRuntime"></td>
                            </tr>
                            <tr>
                                <th>마지막 접속일자</th>
                                <td id="selectEquipLastconn"></td>
                            </tr>
                            <tr>
                                <th>비고</th>
                                <td data-needpopup-show="#equip_remark_modify" id="selectEquipMsg"></td>
                            </tr>
                            <tr>
                            	<td colspan="2" style="text-align: center;">연동 콘텐츠</td>
                            </tr>
                            <tr>
                            	<th>스케줄명<br>(콘텐츠명)</th>
                            	<td id="selectEquipSchInfo" title="날짜삽입">
                            		<span id="selectSchName"></span><br>
                            		<span id="selectConName"></span>
                            	</td>
                            </tr>
                            <tr>
                            	<th>예정 스케줄</th>
                            	<td id="selectEquipSchExpect"></td>
                            </tr>
                        </tbody>                        
                    </table>
                </div>
                <!--통합관리 우측 정보list //-->
                <div class="clearfix"></div>    
            </div>            
        </div>
    </div>

    <!--// 단말기등록pop-->
    <div id='new_Equip' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>단말기 등록</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">단말기명</p>
                    <input type="text" class="input_noti" placeholder="단말기명을 입력해주세요.">
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">부서명</p>
                    <input type="text" class="input_noti" placeholder="부서명을 입력해주세요.">
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">모니터 갯수</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">1개</option>
                        <option value="">2개</option>
                        <option value="">3개</option>
                        <option value="">4개</option>
                        <option value="">5개</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">OS타입</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">안드로이드</option>
                        <option value="">윈도우</option>
                        <option value="">IOS</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">시리얼포트 사용</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">사용</option>
                        <option value="">사용안함</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">Com port</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">com01</option>
                        <option value="">com02</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">Agent ver</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">sw0.9</option>
                        <option value="">sw1.0</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">설치지점</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">[음원]가든파이브</option>
                        <option value="">[음원]가양</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">DID형태</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">가로</option>
                        <option value="">세로</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">기기타입</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">com01</option>
                        <option value="">com02</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">지원해상도</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">1920*1080</option>
                        <option value="">1080*1920</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">사이즈</p>
                    <input type="text" class="input_noti input_noti2" placeholder="가로 사이즈 입력">        
                    <input type="text" class="input_noti input_noti2" placeholder="세로 사이즈 입력">             
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">고정IP여부</p>
                    <input type="radio" name="pop_radio1" id="pop_radio1y">
                    <label for="pop_radio1y">사용</label>
                    <input type="radio" name="pop_radio1" id="pop_radio1n">
                    <label for="pop_radio1n">사용안함 </label>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">사용유무</p>
                    <input type="radio" name="pop_radio2" id="pop_radio2y">
                    <label for="pop_radio2y">사용</label>
                    <input type="radio" name="pop_radio2" id="pop_radio2n">
                    <label for="pop_radio2n">사용안함 </label>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">운영시작시간</p>
                    <select id="popSel" class="popSel2">
                        <option value>시간 선택</option>
                        <option value="">09</option>
                        <option value="">10</option>
                    </select>    
                    <select id="popSel" class="popSel2">
                        <option value>분 선택</option>
                        <option value="">10</option>
                        <option value="">20</option>
                    </select>          
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">운영종료시간</p>
                    <select id="popSel" class="popSel2">
                        <option value>시간 선택</option>
                        <option value="">09</option>
                        <option value="">10</option>
                    </select>    
                    <select id="popSel" class="popSel2">
                        <option value>분 선택</option>
                        <option value="">10</option>
                        <option value="">20</option>
                    </select>          
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">스크립트 유지시간 </p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">1시간</option>
                        <option value="">2시간</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">시리얼연동스크립트</p>
                    <input type="text" class="input_noti" placeholder="시리얼연동 스크립트 입력해주세요.">              
                </div>                
            </div>

            <div class="clearfix"></div>
        </div>
        <div class="pop_footer">
            <a href="" class="top_btn">단말기 등록</a>
        </div>
    </div>
    <!-- 단말기등록pop //-->

    <!--// 장비통신이력pop-->
    <div id='did_pop01' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>단말기 등록</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <table class="list01">
                <thead>
                    <tr>
                        <th>단말기ID</th>
                        <th>IP</th>
                        <th>전문명</th>
                        <th>등록시간</th>
                        <th>처리여부</th>
                        <th>처리시간</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>음원방송</td>
                        <td>192.168.1.100</td>
                        <td>종료시간 설정</td>
                        <td>2018-09-14.14.55.10.0</td>
                        <td><span class="onIcon"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>음원방송</td>
                        <td>192.168.1.100</td>
                        <td>종료시간 설정</td>
                        <td>2018-09-14.14.55.10.0</td>
                        <td><span class="onIcon"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>음원방송</td>
                        <td>192.168.1.100</td>
                        <td>종료시간 설정</td>
                        <td>2018-09-14.14.55.10.0</td>
                        <td><span class="onIcon"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>음원방송</td>
                        <td>192.168.1.100</td>
                        <td>종료시간 설정</td>
                        <td>2018-09-14.14.55.10.0</td>
                        <td><span class="onIcon"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>음원방송</td>
                        <td>192.168.1.100</td>
                        <td>종료시간 설정</td>
                        <td>2018-09-14.14.55.10.0</td>
                        <td><span class="onIcon"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>음원방송</td>
                        <td>192.168.1.100</td>
                        <td>종료시간 설정</td>
                        <td>2018-09-14.14.55.10.0</td>
                        <td><span class="onIcon"></span></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <div class="page_num">
                <span class="page_icon01"></span>
                <span class="page_icon02"></span>
                <ul>
                    <li>1</li> 
                    <li>2</li>    
                    <li class="active">3</li>       
                    <li>4</li>    
                    <li>5</li>    
                    <li>6</li>    
                    <li>7</li>    
                </ul>
                <span class="page_icon03"></span>
                <span class="page_icon04"></span>
            </div>
        </div>
    </div>
    <!-- 장비통신이력pop //-->

    <!--// 스케줄등록pop-->
    <div id='did_pop02' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>스케줄 등록</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">스케줄ID</p>
                    <input type="text" class="input_noti" disabled value="SH1234">
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">스케줄명</p>
                    <input type="text" class="input_noti" value="미러테스트">
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">사이니지 그룹</p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="" selected>에이텐</option>
                        <option value="">에이텐</option>
                        <option value="">에이텐</option>
                        <option value="">에이텐</option>
                        <option value="">에이텐</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">연동 콘텐츠 </p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="" selected>콘텐츠1</option>
                        <option value="">콘텐츠1</option>
                        <option value="">콘텐츠1</option>
                    </select>               
                </div>                
            </div>          
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">긴급유무</p>
                    <input type="radio" name="pop_radio3" id="pop_radio3y">
                    <label for="pop_radio3y">사용</label>
                    <input type="radio" name="pop_radio3" id="pop_radio3n">
                    <label for="pop_radio3n">사용안함</label>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">사용유무</p>
                    <input type="radio" name="pop_radio4" id="pop_radio4y">
                    <label for="pop_radio4y">사용</label>
                    <input type="radio" name="pop_radio4" id="pop_radio4n">
                    <label for="pop_radio4n">사용안함 </label>
                </div>                
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="pop_footer">
            <a href="" class="top_btn">스케줄 등록</a>
        </div>
    </div>
    <!-- 스케줄등록pop //-->

    <!--// 메세지전송pop-->
    <div id='did_pop03' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>메세지 전송</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">메세지</p>
                    <input type="text" class="input_noti" placeholder="메세지 내용을 입력해주세요.">
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">운영 기간</p>
                    <input type="text" id="schStartDay" class="input_noti input_noti2 datepick" name="resStartDate" value size="15">
                    <input type="text" id="schEndDay" class="input_noti input_noti2 datepick" name="resEndDate" value size="15">    
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">운영 시작시간</p>
                    <select id="popSel" class="popSel2">
                        <option value>시간 선택</option>
                        <option value="">09</option>
                        <option value="">10</option>
                    </select>    
                    <select id="popSel" class="popSel2">
                        <option value>분 선택</option>
                        <option value="">10</option>
                        <option value="">20</option>
                    </select>              
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">운영 종료시간</p>
                    <select id="popSel" class="popSel2">
                        <option value>시간 선택</option>
                        <option value="">09</option>
                        <option value="">10</option>
                    </select>    
                    <select id="popSel" class="popSel2">
                        <option value>분 선택</option>
                        <option value="">10</option>
                        <option value="">20</option>
                    </select>              
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">폰트 크기 </p>
                    <select id="popSel">
                        <option value>선택하세요.</option>
                        <option value="">10</option>
                        <option value="">20</option>
                    </select>               
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">사용유무</p>
                    <input type="radio" name="pop_radio5" id="pop_radio5y">
                    <label for="pop_radio5y">사용</label>
                    <input type="radio" name="pop_radio5" id="pop_radio5n">
                    <label for="pop_radio5n">사용안함 </label>
                </div>                
            </div>        
            <div class="clearfix"></div>
        </div>
        <div class="pop_footer">
            <a href="" class="top_btn">메세지 전송</a>
        </div>
    </div>
    <!-- 메세지등록pop //-->
    
    
    
    
    <!-- 비고 등록 POP ::  START -->
	<div id='equip_remark_modify' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>단말기 비고</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">단말 ID</p>
                    <input type="text" id="equip_remark_id" class="input_noti" disabled/>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">단말명</p>
                    <input type="text" id="equip_remark_name" class="input_noti" disabled/>               
                </div>                
            </div>
            <!--// 팝업 필드박스-->
            <div class="pop_box100 pwSearch_input">
                <div class="padding15">
                    <p class="pop_tit">단말 비고 <span class=""></span></p>
                    <input type="text" id="equip_remark_content" class="input_noti" value="" placeholder="200자 이내">
                </div>                
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="pop_footer">
            <a onclick="remarkBtnAction(this);" id="equip_remark_btn" class="top_btn" value="0">수정하기</a>
        </div>
    </div>
    <!-- 비고 등록 POP :: FINISH -->

    <!--popup js-->
    <script src="/new/js/needpopup.js"></script> 
     <!--data-->
    <script src="/new/js/jquery-ui.js"></script>
    <script src="/new/js/datepipck.js"></script>

</body>
</html> 