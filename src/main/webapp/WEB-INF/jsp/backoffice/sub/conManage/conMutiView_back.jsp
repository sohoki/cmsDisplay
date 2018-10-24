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
<link href="<c:url value='/'/>css/test/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/test/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/test/reset.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/test/jquery.treemenu.css" rel="stylesheet" type="text/css" >
<!--leftMenu-->
<script type="text/javascript" src="/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/leftMenu.js"></script>

<style type="text/css">
    div.block { width: 100% !important; height: auto !important;  }
    .file_info{display:none;}
    .contentPlaytimeInput{
    	width : 56px;
    	border : none;
    	border-bottom: solid 1px #dcdcdc;
    	text-align: center;
    	
    }
    .timeIntervalBtn{
    	background-color:#ffffff;
    	border : solid 1px #dcdcdc;
    	padding : 4px 16px;
    	margin : 0px 4px;
    	cursor : pointer;
    }
    .timeIntervalBtn:hover{
    	background-color:#dcdcdc;
    	cursor : pointer;
    }
    .whiteBtn{
	    background-color: #fff;
	    border: solid 1px #adadad;
	    color: #000;
	    font-weight: normal;
	    font-size: 14px;
	    padding : 4px 8px;
	    margin : 0px 2px;
    }
    .whiteBtn:hover{
    	background-color: #adadad;
    }
</style>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<span id="backgroundgif"></span>
<!-- 내용시작 -->
 <div id="wrap">
<c:import url="/backoffice/inc/emart_header.do" />
<div class="left-contain">
    <c:import url="/backoffice/inc/cms_left.do" />
    <div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
</div>  
		<div class="container">
			<!--내용시작-->
		    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/conMutiDetail.do">						
			<form:hidden path="conSeq" id="conSeq"/>
			<form:hidden path="mode" id="mode"/>
			<form:hidden path="conScreen" id="conScreen"/>
			<form:hidden path="pageIndex" />
	        <form:hidden path="pageSize" />
	        <form:hidden path="searchCondition" id="searchCondition" />
	        <form:hidden path="searchKeyword" id="searchKeyword" />
	        <form:hidden path="menuGubun" id="menuGubun" />
	        <form:hidden path="conDc" id="conDc" />
	        
	        
	        <input type="hidden" name="contentTotal" id="contentTotal" />
	        <input type="hidden" name="conPageSize" id="conPageSize" />
	        <input type="hidden" name="strPage" id="strPage" />
	        
	        <input type="hidden" name="fileHeight" id="fileHeight" />
	        <input type="hidden" name="playTime" id="playTime" />
	        <input type="hidden" name="fileWidth" id="fileWidth" />
	        <input type="hidden" name="atchFileId" id="atchFileId" />
	        
	        <input type="hidden" name="fileSeq" id="fileSeq" />		
		
			<c:import url="/backoffice/inc/emart_tree.do" />
			<!-- 붙여넣기 끝 -->
			<div class="main-content">				
				<div class="content_back">
					<ul class="topMenu" >
						<li><a href="/backoffice/sub/conManage/mediaLst.do" class="media">미디어 관리</a></li>
						<li class="active"><a href="/backoffice/sub/conManage/conMutiList.do" class="conMutiList">화면 구성</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="">
						<!-- 미디어 정보 -->
						<div class="conDetailMediaLst">
							<table class="conDMLTitle">
								<tbody>
									<tr>
										<td><div class='mediaLstControl'><a href='javascript:pre();'><img src='/img/pre.png' alt='pre'></a></div></td>
										<td><img src="/img/media.png" /></td>
										<td><p class="mediaLstTitle">미디어 리스트</p></td>
										<td><div class='mediaLstControl'><a href='javascript:next()'><img src='/img/next.png' alt='next'></a></div></td>
									</tr>
								</tbody>
							</table>
							<table class="mediaLstTableType">
								<thead>
									<tr>
										<th>
											<select id="mediaType" name="mediaType" style="height:26px;">
							                    <option value="" selected="">콘텐츠전체</option>
							                    <option value="IMAGE">이미지</option>
							                    <option value="MEDIA">동영상</option>
							                </select>
										</th>
										<th>
											<input type="text" max="50" size="16" style="height: 20px; width:128px;" id="searchConName" placeholder="콘텐츠명">
										</th>
										<th>
											<a onclick="detailConSeartch()" class="boxH yellowBtn">조회</a>
										</th>
									</tr>
								</thead>
								<tbody id="con_img_lst" class="scrollStyle1">
								</tbody>
							</table>
						</div>
						<!-- 스케줄 관리 -->
						<div class="con_titleIn whiteBox ">					
							<!--내용시작-->	
							<div class="tableBox editTable sizeTable iframeTd" style='vertical-align:top'>
				
								<table class="conDetailInfoTable" style="width:100%; margin:0px;">
									<tbody>
										<tr>
											<th>화면명</th>
											<td style="text-align:left">${regist.conNm }</td>
											<th>화면타입</th>
											<td style="text-align:left">${regist.codeNm }</td>
											<th>등록일</th>
											<td style="text-align:left">${regist.frstRegistPnttm}</td>
											<th>이용스케줄</th>
											<td style="text-align:left">${regist.schCnt} 개</td>			
										</tr>
										<tr>
											<th>총 송출 시간</th>
											<td style="text-align:left"><span id="conTimeInterval">${regist.conTime}</span>  초</td>
											<th>사용여부</th>
											<td style="text-align:left">
											<c:choose>
											   <c:when test="${regist.conUseYn eq 'Y' }">사용</c:when>
											   <c:otherwise>사용 안함</c:otherwise>
											</c:choose>								
											</td>							
											<th>화면 사이즈</th>
											<td class="smiddleBox" style="text-align:left">
											가로: ${regist.conWidth }  
											세로: ${regist.conHeight }
											분할길이: ${regist.conMid } 						
											</td>
											<th>연결콘텐츠</th>
											<td style="text-align:left">
											<c:choose>
											   <c:when test="${regist.conNextTitle eq 'NO DATA'}">연결 정보 없음</c:when>
											   <c:otherwise>${regist.conNextTitle}</c:otherwise>
											</c:choose>	
											</td>		
										</tr>
										<tr>
										  <th>페이지 구분</th>
										  <td align="right">
										    <select id="comPageInfo"  onChange="javascript:pageChange()"></select>
										  </td>
										  <td colspan="6" style="text-align:right">
										  	<a href="javascript:preView();" class="excel boxH whiteBtn">화면생성</a>
											<a href="javascript:sendSchedule();" class="excel boxH whiteBtn">스케줄전송</a>	
											<a href="javascript:listPage('regist','/backoffice/sub/conManage/conMutiList.do' )" class="excel boxH whiteBtn">목록</a> 					
											<a href="javascript:check_form()" class="modi boxH whiteBtn" id="btnUpdate">수정</a>
											<a href="javascript:del_form('${regist.schCnt}')" class="delkey boxH whiteBtn">삭제</a>	
										  </td>
										</tr>
										<tr border="0">
										  <td colspan="8" style="border-top: none;">
										  <div class="clear"></div>
										   	<div class='conBoxHeader' id='contentBoxHeader'>
										   		<table>
										   			<thead>
										   				<tr>
										   					<th colspan='2' class="contentDetailListTitle_01">순번</th>
										   					<th>썸네일</th>
										   					<th>파일명</th>
										   					<th>송출시간</th>
										   					<th>관리</th>
										   					<th>상세정보</th>
										   				</tr>
										   			</thead>
										   		</table>
										   	</div>
										    <div class="content scrollStyle1 ui-sortable" id="test-list"></div>
										    <div class="file_info" style="display:none;">
										         <table border="1" width="100" height="100">
												        <tr>
												          <td colspan="2">
												           <span id="content_img" />
												          </td>
												        </tr>
												        <tr><td>송출시간</td><td><input style="width:32px;" type="text" id="content_interval" size="2">&nbsp;초&nbsp;&nbsp;<a class="excel boxH blueBtn" style="padding:4px 8px; margin-left:8px;" href="javascript:saveInterval()">입력</a></td></tr>
												        <tr><td>화면크기</td><td>넓이:<span id="size_w" ></span> 높이:<span id="size_h" ></span></td></tr>
												        <tr><td>확장자</td><td><span id="fileExt" /></td></tr>
												        <tr><td>순번</td><td><span id="content_order" /></td></tr>
												        <!-- <tr><td colspan="2" align="center"><a href="javascript:saveInterval()">입력</a></td></tr> -->
										       </table>
										       <div class="btnBox md_btn">
										       		<div style="margin:4px;">					
														<a href="javascript:preView();" class="excel boxH blueBtn">화면생성</a>
														<a href="javascript:sendSchedule();" class="excel boxH blueBtn">스케줄전송</a>	
													</div>	
										       		<div style="margin:4px;">	
														<a href="javascript:listPage('regist','/backoffice/sub/conManage/conMutiList.do' )" class="excel boxH yellowBtn">목록</a> 					
														<a href="javascript:check_form()" class="modi boxH yellowBtn" id="btnUpdate">수정</a>
														<a href="javascript:del_form('${regist.schCnt}')" class="delkey boxH redBtn">삭제</a>
													</div>
												</div>
												<div class="clear"></div>
										    </div>
										    <div class="clear"></div>
										  </div>
										  						  
										  </td>
										</tr>										
									</tbody>
								</table>
								<div id="conDetail"></div>								
							</div>	
							<!-- 붙여넣기 끝 -->					
						</div>
					</div>					
					<!--//하단 콘텐츠 끝 부분 -->
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
	</form:form>
	<div class="clear"></div>
	
<!-- 내용 종료 -->


		<!-- 
        <div class="con">			
			<div class="tableBox editTable sizeTable iframeTd" style='vertical-align:top'>
				
			</div>
		</div>
		 -->
    
	<script type="text/javascript">

		var conSeqValue = "${regist.conSeq}";
	
		function loadingStart(){
	    	var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
	    	$("#backgroundgif").html(loadingimg);
		}
		function loadingFinish(){
			$("#backgroundgif").html("");
		}
		function conSchReg(codeId){
			
			
			
			loadingStart();
	    	var newOrder=$("#test-list div").length-1;
	    	
	    	if(newOrder == "-1"){ newOrder = "0"; }
	    	
	    	
	    	
	    	var detailSeqCode = $("#comPageInfo").val();
    		apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/ContentReg.do",
    			{ 
    				conSeq: conSeqValue,
    				detailSeq: detailSeqCode,
    				atchFileId : codeId, 
    				fileOrder : newOrder
    			},
    			null,
    			function(result) {
    				if (result != "F") {    
    					
    					var strArray = result.split('|');
    					apiExecute(
   			    			"POST", 
   			    			"/backoffice/sub/conManage/conDetailFileInfoTableSeq.do",
   			    			{ 	    				
   			    				fileSeq : strArray[1] 	    				
   			    			},
   			    			null,
   			    			function(result) {
   			    				if (result.detailFileInfo != "") {    
									var obj = result.detailFileInfo;
									if (obj != ""){
										var condivLst = "";
										var newConWidth = "";
										var newConHeight = "";
										if((obj.fileWidth-obj.fileHeight)>0){
											// 가로
											newConWidth = "180px";
											newConHeight = "auto";
										}else{
											// 세로
											newConWidth = "auto";
											newConHeight = "180px";
										} 
										
										var timeIntervalInfo = "";
										
										if(obj.timeInterval == null){
											
											timeIntervalInfo = "<input class='contentPlaytimeInput' type='text' id='conInterval_"+obj.fileSeq+"' size='2' value='' placeholder='미등록'> 초 <a class='time_"+obj.fileSeq+" timeIntervalBtn' href='javascript:saveInterval("+obj.fileSeq+")'>적용</a>";
			    						}else{
			    							timeIntervalInfo = "<input class='contentPlaytimeInput' type='text' id='conInterval_"+obj.fileSeq+"' size='2' value='"+obj.timeInterval+"' placeholder='미등록'> 초 <a class='time_"+obj.fileSeq+" timeIntervalBtn' href='javascript:saveInterval("+obj.fileSeq+")'>적용</a>";
			    						}
										if (obj.mediaType == "IMAGE"){
											condivLst += "<div id='listItem_"+obj.fileOrder+"' class='listDiv'><table border='0'><tr><td><a class='dragHandle'></a></td><td class='orderSeq'></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img class='listImg' src='"+obj.fileStreCours + obj.streFileNm +"' style='width:"+newConWidth+"; height:"+newConHeight+";' id='file_"+obj.fileSeq+"' ></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td class='mediaTime"+obj.fileSeq+"'>"+timeIntervalInfo+"</td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+detailSeqCode+"&#39; )'>제외</a></td><td>"+obj.fileWidth+"x"+obj.fileHeight+"</td></tr></table></div>";
 			    				    	} else {
 			    				    		condivLst += "<div id='listItem_"+obj.fileOrder+"' class='listDiv'><table border='0'><tr><td><a class='dragHandle'></a></td><td class='orderSeq'></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img class='listImg' src='"+obj.fileStreCours + obj.fileThumnail +"' style='width:"+newConWidth+"; height:"+newConHeight+";' id='file_"+obj.fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td class='mediaTime"+obj.fileSeq+"'>"+timeIntervalInfo+"</td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+detailSeqCode+"&#39;)'>제외</a></td><td>"+obj.fileWidth+"x"+obj.fileHeight+"</td></tr></table></div>";
 			    				    	}
										
										
										/*
										UP/DOWN Button
										
										if (obj.mediaType == "IMAGE"){
										condivLst += "<div id='listItem_"+obj.fileOrder+"' class='listDiv'><table border='0'><tr><td><span class='UDbtn' style='cursor: pointer;'><b class='Ubtn' id='uOrign_"+obj.fileOrder+"' onclick='divUp(&#39;uOrign_"+obj.fileOrder+"&#39;)'>▲</b><b class='Dbtn' id='dOrign_"+obj.fileOrder+"' onclick='divDown(&#39;dOrign_"+obj.fileOrder+"&#39;)'>▼</b></span></td><td class='orderSeq'></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img class='dragHandle' src='"+obj.fileStreCours + obj.streFileNm +"' style='width:"+newConWidth+"; height:"+newConHeight+";' id='file_"+obj.fileSeq+"' ></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td class='mediaTime"+obj.fileSeq+"'>"+timeIntervalInfo+"</td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+detailSeqCode+"&#39; )'>제외</a></td></tr></table></div>";
			    				    	} else {
			    				    		condivLst += "<div id='listItem_"+obj.fileOrder+"' class='listDiv'><table border='0'><tr><td><span class='UDbtn' style='cursor: pointer;'><b class='Ubtn' id='uOrign_"+obj.fileOrder+"' onclick='divUp(&#39;uOrign_"+obj.fileOrder+"&#39;)'>▲</b><b class='Dbtn' id='dOrign_"+obj.fileOrder+"' onclick='divDown(&#39;dOrign_"+obj.fileOrder+"&#39;)'>▼</b></span></td><td class='orderSeq'></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img class='dragHandle' src='"+obj.fileStreCours + obj.fileThumnail +"' style='width:"+newConWidth+"; height:"+newConHeight+";' id='file_"+obj.fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td class='mediaTime"+obj.fileSeq+"'>"+timeIntervalInfo+"</td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+detailSeqCode+"&#39;)'>제외</a></td></tr></table></div>";
			    				    	}
										
										
										*/
										
										$("#test-list").append(condivLst);
				    					// conDetailOrderSeqSetting("uOrign_"+obj.fileOrder, "dOrign_"+obj.fileOrder);
				    					item_order();
				    					totalTimeCheck();
									}
			    				}
   			    				loadingFinish();
   			    			},
			    			null,
			    			null
			    		);	
    				} else {
    					loadingFinish();
    				}
    			},
    			null,
    			null
   			);
		}
	    function detailConSeartch(){
	    	$("#searchKeyword").val($("#searchConName").val());
	    	var strPage = 0;
	    	var conPageSize = 8;
	    	
	    	apiExecute(
	   			"POST", 
	   			"/backoffice/sub/conManage/jsonDetailContentLst.do",
	   			{
					strPage : strPage ,					  
	   				conPageSize : $("#conPageSize").val(),
	   				mediaType : $("#mediaType").val(),
	   				originFileNm : $("#searchKeyword").val()
				},
				null,				
				function(result) {							
					if (result != null) {	   						 
						if (result.jsonCon != null) {
			    					
		   					var imgSlider = "";
		   					var thumW = "";
							var thumH = "";
							for (var i=0; i<result.jsonCon.length; i++) {
								var obj = result.jsonCon[i];
	    						var onclickValue = "";
	    						var imgCreate = "";
	    						if(obj.fileWidth == null){
	    							// 로딩되지 않은 콘텐츠
	    							onclickValue = "view_Con(&#39;"+obj.atchFileId+"&#39;)";
	    							if (obj.mediaType == "IMAGE"){
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.streFileNm +"' style='width:120px; height:120px;'/><br><span style='color:#FF4b4b'>미디어 로딩이 필요합니다.</span>";  
									} else {
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.fileThumnail +"' style='width:120px; height:120px;'/><br><span style='color:#FF4b4b'>미디어 로딩이 필요합니다.</span>";
									}
	    						}else{
	    							onclickValue = "conSchReg(&#39;"+obj.atchFileId+"&#39;)";
		    						if((obj.fileWidth - obj.fileHeight) > 0){
										// 가로
										thumW = "140px";
										thumH = "auto";
									}else{
										// 세로
										thumW = "auto";
										thumH = "140px";
									}
		    						if (obj.mediaType == "IMAGE"){
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.streFileNm +"'  style='width:"+thumW+"; height:"+thumH+";' />";   
									}else{
	    								imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.fileThumnail +"'  style='width:"+thumW+"; height:"+thumH+";' />";
									}
	    						}
								if (obj.mediaType == "IMAGE"){
									imgSlider  += "<tr><td colspan='3' style='border-bottom:0;'><div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='"+onclickValue+"'>"+imgCreate+"<br>"+obj.orignlFileNm+"</div></td></tr>";   
								} else {
									imgSlider  += "<tr><td colspan='3' style='border-bottom:0;'><div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='"+onclickValue+"'>"+imgCreate+"<br>"+obj.orignlFileNm+"</div></td></tr>";
								}			
								thumW = null;
								thumH = null;
							}
							imgSlider += "";
							$("#con_img_lst").html(imgSlider); 
	    					//드레그 관련 내용 
	    					/* $(".items div").draggable({			
	    					    helper: function(e) {
	    					    	var thum = $(this).attr("id");
	    							var id = "<div id='listItem_"+$("div[id^='listItem_']").length+"'>";	
	    							var divTable = setTimeout("contentUplade('"+thum+"','" + $("div[id^='listItem_']").length+"')", 1000);
	    							//var divTable = contentUplade(thum);
	    							//여기 부분에 값 넣기  		    							 		    							
	    							return $(id).addClass("block").html( divTable );
	    					       //확인 작업 필요 
	    					    },
	    					    connectToSortable: ".content"
	    				    }); */
	    				}else{
	    					alert("없음");
	    				}
					}
				},
				null,
				null
	   		);	
		}
	    
	    function saveInterval(selectFileSeq){
	    	
	    	/* alert($("#conInterval_"+fileSeq).val() + "초 입력"); */
	    	
	    	var inputInterval = $("#conInterval_"+selectFileSeq).val();
	    	
	    	if(typeof inputInterval === "undefined"){
	    		alert(selectFileSeq + "에서 에러가 발생하였습니다. " + inputInterval);
	    		return false;	
	    	}
	    	
	    	if(inputInterval == "" || inputInterval == "0"){
	    		alert("송출시간을 입력하지 않았습니다.\n(1초 이상 입력)");
	    	} else {
	    		apiExecute(
	       			"POST", 
	       			"/backoffice/sub/conManage/ContentUpdateOrder.do",
	       			{
	       				fileSeq : selectFileSeq,
	       				timeInterval : inputInterval,
	       				detailSeq : $("#comPageInfo").val()
	       			},
	       			null,
	       			function(result) {
	       				if (result != null) {    					
	       					//시간 변경값 가지고 오기    					
	       					if (result != "F"){
	       						$("#conTimeInterval").text(result);
	       						$(".mediaTime"+$("#fileSeq").val()).html("<span class='fileTime"+$("#fileSeq").val()+"'>"+$("#content_interval").val()+"초 <a class='time_"+$("#fileSeq").val()+" timeIntervalBtn' href='javascript:saveInterval("+selectFileSeq+")'>적용</a></span>");
	       					}
	       				}
	       			},
	       			null,
	       			null
	       		);	
	    	}
	    	
	    	// id='conInterval_"+obj.fileSeq+"'
	    }
	    
	    /* function saveInterval(){ // 기존    	
			if (any_empt_line_id("content_interval", "운영시간을 입력 하지 않았습니다.") == false) {
			} else if ($("#content_interval").val() < 1){
				alert("최소 입력시간은 1초 입니다.");
				
			} else {   		
				apiExecute(
	       			"POST", 
	       			"/backoffice/sub/conManage/ContentUpdateOrder.do",
	       			{
	       				fileSeq : $("#fileSeq").val(),
	       				timeInterval :$("#content_interval").val(),
	       				detailSeq : $("#comPageInfo").val()
	       			},
	       			null,
	       			function(result) {
	       				if (result != null) {    					
	       					//시간 변경값 가지고 오기    					
	       					if (result != "F"){
	       						$("#conTimeInterval").html('<input class="contentPlaytimeInput" type="text" id="conInterval_'+$("#fileSeq").val()+'" size="2" value='+result+'>'+' 초 <a class="time_'+$("#fileSeq").val()+' timeIntervalBtn" href="javascript:saveInterval()">적용</a>');
	       						$(".mediaTime"+$("#fileSeq").val()).html("<span class='fileTime"+$("#fileSeq").val()+"'>"+$("#content_interval").val()+"초 <a class='time_"+$("#fileSeq").val()+" timeIntervalBtn' href='javascript:saveInterval()'>적용</a></span>");
	       					}
	       				}
	       			},
	       			null,
	       			null
	       		);	
	    	}
	   	} */
		function preView(){
    		var PageWidth = "${regist.conWidth }";
    		var PageHeight = "${regist.conHeight }";
    		apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/preViewCheck.do",
    			{
    				conSeq : conSeqValue
    			},
    			null,
    			function(result) {
    				if (result != null) {    					
    					//시간 변경값 가지고 오기    					
    					if (result != "F"){
    						var url = "/backoffice/sub/conManage/contentPreview.do?conSeq="+conSeqValue;	      	
    				  	    window.open(url,"파일 업로드", "width="+ PageWidth +",height="+PageHeight+",top=50,left=50,scrollbars=auto")	;      						
    					}else {
    						alert("송출시간을 입력하지 않은 콘텐츠가 있습니다.");
    					}
    				}
    			},
    			null,
    			null
    		);	    	
    	}
    	function sendSchedule(){
    		var PageWidth = "${regist.conWidth }";
    		var PageHeight = "${regist.conHeight }";
    		apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/preViewCheck.do",
    			{
    				conSeq : conSeqValue
    			},
    			null,
    			function(result) {
    				// 데이터 전송 
    				if (result != null) {    					
    					//시간 변경값 가지고 오기    					
    					if (result != "F"){
    						sendScheduleUpdate();  						
    					}else {
    						alert("이미지 중 타임값을 넣지 않은 콘텐츠가 있습니다.");
    					}
    				}
    			},
    			null,
    			null
    		);	
    	}
    	function sendScheduleUpdate(){
    		apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/contentScheduleSend.do",
    			{
    				conSeq : conSeqValue
    			},
    			null,
    			function(result) {
    				// 데이터 전송 
    				if (result != null) {    					
    					//시간 변경값 가지고 오기    					
    					if (result != "F"){
    						alert("스케줄 정상적으로 등록되었습니다.");				
    					}else {
    						alert("스케줄 등록 도중 문제가 발생하였습니다.");
    					}
    				}
    			},
    			null,
    			null
    		);	
	    }

    	function del_form(schExist){
    		if(schExist > 0){
    			if (confirm("송출 중이거나 예정의 연결 된 스케줄이 존재합니다.\n연결 해제 또는  스케줄을 삭제하세요.\n\n스케줄 페이지를 여시겠습니까?")== true){
    				window.open("/backoffice/sub/equiManage/schList.do", "_blank");
    			}
    		}else{
    			if (confirm("본 콘텐츠를 삭제 하시겠습니까?")== true){
    				document.regist.action = "/backoffice/sub/conManage/conMutiDel.do";
    				document.regist.encoding = "application/x-www-form-urlencoded"; 
    				document.regist.submit();	  
    			}
    		}
    		
   		}
    	
    	function check_form(){
    		$("#mode").val("Edt");
    		$("form[name=regist]").attr("action", "/backoffice/sub/conManage/conMutiDetail.do").submit();	
    	}
    	
    	function divUp(uBtnId){

			var srcDiv = $("#"+uBtnId).parents(".listDiv");
			var tgtDiv = srcDiv.prev();
			var clickDivId = srcDiv.attr("id");
			var nonClickDivId = tgtDiv.attr("id");
			
			if(nonClickDivId == "contentBoxHeader"){
				alert("첫번째 콘텐츠 입니다.");	
			} else {
				if (tgtDiv[0]) {
					tgtDiv.before(srcDiv);
			 		srcDiv.attr("id", nonClickDivId);
			 	    tgtDiv.attr("id", clickDivId);
				}
				var order = getOrder(".listDiv", $("#test-list")[0]);
				item_order();	
			}
    	}
    	
    	function divDown(dBtnId){
    		
			var srcDiv = $("#"+dBtnId).parents(".listDiv");
			var tgtDiv = srcDiv.next();
			var clickDivId = srcDiv.attr("id");
			var nonClickDivId = tgtDiv.attr("id");
			
			
			
			if(typeof nonClickDivId == "undefined"){
				alert("마지막 콘텐츠 입니다.");	
			}else{
				if (tgtDiv[0]) {
					tgtDiv.after(srcDiv);
			 		srcDiv.attr("id", nonClickDivId);
			 	    tgtDiv.attr("id", clickDivId);
				}
				var order = getOrder(".listDiv", $("#test-list")[0]);
				item_order();
			}
    	}
    	
		function getOrder(selector, container) {
			var order = [];
			$(selector, container).each(function () {
				order.push(this.id);
			});
			return order;
		}
    	
		$(document).ready(function(){
			//컨텐츠 총 수량    	
			$("#conPageSize").val("8");    
			var startPage = $("#strPage").val(); 
			if (startPage == ""  ){
				 $("#strPage").val("1");
			}    	
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/contentTotalCnt.do",
				null,
				null,
				function(result) {
					if (result != null) {       					
						$("#contentTotal").val(result);    					
						contentLst();
					}
				},
				null,
				null
			);
			// 페이지 콤보 박스 리스트 보여 주기 
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/conMutiSelect.do",
				{
					code: $("#conScreen").val(),
					conSeq : conSeqValue    				
				},
				null,
				function(result) {
					if (result != null) {    					
						for (var i=0; i<result.jobCombo.length; i++) {
							var obj = result.jobCombo[i];
							$("<option value='"+ obj.detailSeq +"'>"+ (Number(obj.detailOrder)+1) +"페이지</option>").appendTo("#comPageInfo");
						}    					
						//이쪽 부분 다시 정리 하기
						setTimeout( contentListDetail($("#comPageInfo").val()), 400);
					} else {
						alert("없음");
					}
				},
				null,
				null
			);
			
			

			var agent = navigator.userAgent.toLowerCase();
			if ( (navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) {
			     // ie일 경우
			     
			     $(".contentDetailListTitle_01").attr("colspan", "");
			     
			}else{
			     // ie가 아닐 경우
				$(".contentDetailListTitle_01").attr("colspan", "2");
			}
			
			
	    }); // $(document).ready finish
		
    	function contentListDetail(code){    	
    		apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/conDetailInfoTable.do",
    			{
    				conSeq : conSeqValue,
    				detailSeq : code
    			},
    			null,
    			function(result) {
    				if (result != null) {    					
    					if (result.detailContentInfo != null) {
    						var condivLst = "";
    						var setConWidth = "";
							var setConHeight = "";
							
							for (var i=0; i<result.detailContentInfo.length; i++) {
	    						var obj = result.detailContentInfo[i];
	    						if((obj.fileWidth-obj.fileHeight)>0){
									// 가로
									setConWidth = "180px";
									setConHeight = "auto";
								} else{
									// 세로
									setConWidth = "auto";
									setConHeight = "180px";
								}
	    						
								var timeIntervalInfo = "";
								
								if(obj.timeInterval == null){
									// $("#conTimeInterval").text("0");
									timeIntervalInfo = "<span style='color:#FF4b4b; font-weight:bold;'><input class='contentPlaytimeInput' type='text' id='conInterval_"+obj.fileSeq+"' size='2' value='' placeholder='미등록'> 초 <a class='time_"+obj.fileSeq+" timeIntervalBtn' href='javascript:saveInterval("+obj.fileSeq+")'>적용</a></span>";
	    						}else{
	    							// $("#conTimeInterval").text();
	    							timeIntervalInfo = "<span><input class='contentPlaytimeInput' type='text' id='conInterval_"+obj.fileSeq+"' size='2' value="+obj.timeInterval+"> 초 <a class='time_"+obj.fileSeq+" timeIntervalBtn' href='javascript:saveInterval("+obj.fileSeq+")'>적용</a></span>";
	    						}
	    						
	    						if (obj.mediaType == "IMAGE"){
									condivLst += "<div id='listItem_"+i+"' class='listDiv'><table border='0'><tr><td><a class='dragHandle'></a></td><td class='orderSeq'></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img class='listImg' src='"+obj.fileStreCours + obj.streFileNm +"' onError='this.src=&#39;/img/no_image.png&#39;;' style='width:"+setConWidth+"; height:"+setConHeight+";' id='file_"+obj.fileSeq+"' ></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td class='mediaTime"+obj.fileSeq+"'>"+timeIntervalInfo+"</td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+code+"&#39; )'>제외</a></td><td>"+obj.fileWidth+"x"+obj.fileHeight+"</td></tr></table></div>";   
								} else {
									condivLst += "<div id='listItem_"+i+"' class='listDiv'><table border='0'><tr><td><a class='dragHandle'></a></td><td class='orderSeq'></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img class='listImg' src='"+obj.fileStreCours + obj.fileThumnail +"' onError='this.src=&#39;/img/no_image.png&#39;;' style='width:"+setConWidth+"; height:"+setConHeight+";' id='file_"+obj.fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td class='mediaTime"+obj.fileSeq+"'>"+timeIntervalInfo+"</td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+code+"&#39;)'>제외</a></td><td>"+obj.fileWidth+"x"+obj.fileHeight+"</td></tr></table></div>";
								}
	    						
	    					 }		    					 
	    					$("#test-list").html(condivLst);
	    					// conDetailOrderSeqSetting('uOrign_'+i, 'dOrign_'+i);
	    					item_order();
	    				} else{
	    					alert("없음");
	    				}
    				}
    			},
    			null,
    			null
    		);
	    	//총 재생 시간 변경
	    	totalTimeCheck();
    	}
    	function del_Content(fileSeq, code){
    		loadingStart();
    		apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/ContentDel.do",
    			{    				
    				fileSeq : fileSeq,
    				detailSeq : code
    			},
    			null,
    			function(result) {
    				if (result != null) {    					
    					if (result != "F"){    						
    						contentListDetail(code);
    						
    						$("#content_img").html("");
    				    	$("#fileWidth").val("");
    						$("#fileHeight").val("");
    						$("#playTime").val("");
    						$("#size_w").text("");
    				   	    $("#size_h").text("");   	 
    				   	    $("#content_interval").val("");
    				   	    
    					}else {
    						alert("컨텐츠 등록 삭제시 문제가 생겼습니다.")
    					}    					
    				}
    				loadingFinish();
    			},
    			null,
    			null
    		);
   		}
	    //파일 상세 정보 콘텐츠 코드로 변경 작업  
	    function view_info(code){
			$("#fileSeq").val(code);         	
			apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/conDetailFileInfoTableSeq.do",
    			{ 	    				
    				fileSeq : code 	    				
    			},
    			null,
    			function(result) {
    				if (result.detailFileInfo != "") {   
						var obj = result.detailFileInfo;
						if (obj != ""){
							var detailConWidth = "";
							var detailConHeight = "";
					
							if((obj.fileWidth-obj.fileHeight)>0){
								// 가로
								detailConWidth = "200px";
				  				detailConHeight = "auto";
				  			}else{
				  				// 세로
				   				detailConWidth = "auto";
				  				detailConHeight = "200px";
							}
								
    				    	if  (obj.mediaType == "IMAGE"){	    				      
	    				         $("#content_img").html("<img src='"+obj.fileStreCours + obj.streFileNm +"' onError='this.src=&#39;/img/no_image.png&#39;;' style='width:"+detailConWidth+"; height:"+detailConHeight+";'>");
	    				         $("#content_interval").attr("disabled", false);
   				    	  	}else {    				    		  
   				    			$("#content_img").html("<img src='"+obj.fileStreCours + obj.fileThumnail +"' onError='this.src=&#39;/img/no_image.png&#39;;' style='width:"+detailConWidth+"; height:"+detailConHeight+";'>");
   				    			$("#content_interval").attr("disabled", true);
   				    	  	}		
    				    	$("#atchFileId").val(obj.atchFileId);
    				    	$("#fileExt").text(obj.fileExtsn);
    				    	  
							if ( obj.fileHeight != null){
								if (obj.timeInterval == "" && obj.playTime !=""){
									$("#content_interval").val(obj.playTime);  
								}else {    				    			  
	    				    		$("#content_interval").val(obj.timeInterval);
								}    				    		  
								$("#size_w").text(obj.fileWidth);
								$("#size_h").text(obj.fileHeight);	    					  
    	    					$("#content_order").text(obj.fileOrder);  
							} else {
    				    		  
    				    		var fileUrl = "http://"+$(location).attr('host')+ obj.fileStreCours + obj.streFileNm;
    				    		  
    				          	if (obj.mediaType == "IMAGE" ){
    				          		 $("img").each(function() {        			 
    				          	         $(this).load(function(){
    				          	        	$("#size_w").val(this.naturalWidth);
    				          	 			$("#size_h").val(this.naturalHeight);    	
    				          	 			$("#fileWidth").val(this.naturalWidth);
    				          	 		    $("#fileHeight").val(this.naturalHeight);
    				          	     		callback();    	         
    				          	         });
    				          		 });
    				          	}else {    				          		
    				          		mediaUpdate(fileUrl);
								}
							}  
						}	    					   						    					
    				}
    			},
    			null,
				null
			);	 
    	}
	    //미디어 관련 정보 
	    function mediaUpdate(url){    	
			var video=document.createElement("video");
	  	    video.autoplay=true;
	  	    video.oncanplay=function(){
		  	     
		  	    $("#fileWidth").val(this.offsetWidth);
				$("#fileHeight").val(this.offsetHeight);
				$("#playTime").val(this.duration);
				$("#size_w").text($("#fileWidth").val() );
		    	$("#size_h").text($("#fileHeight").val() );    	 
		    	$("#content_interval").val($("#playTime").val() );
		    	 
		    	callback();
		    		
		  		this.src="about:blank";
		  		document.body.removeChild(video);   
			};
	  	  	document.body.appendChild(video);
	  	  	video.src=url;
	    }
	    //업데이트
	    function callback(){
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/conFileUpdate.do",
				{
					atchFileId : $("#atchFileId").val(),
					playTime : $("#playTime").val(),
					fileWidth : $("#fileWidth").val(),
					fileHeight : $("#fileHeight").val()
				},
				null,				
				function(result) {							
					if (result != null) {	         							 							
						if (result == "O"){
							//동영상시  자동으로 입력되게 하기  								
							media_timeInterval();
						} else {
							alert("등록시 문제가 생겼습니다.");
						}
					}
				},
				null,
				null
			);	
		}
	    //동영상 파일 자동으로 저장 하게 하기 
	    function media_timeInterval(){
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/ContentUpdateOrder.do",
				{
					fileSeq : $("#fileSeq").val(),
					playTime : $("#playTime").val()
				},
				null,				
				function(result) {							
					if (result != null) {	         							 							
						if (result != "F"){
							//동영상시  자동으로 입력되게 하기 
							$("#conTimeInterval").text(result);        									
						}else {
							alert("등록시 문제가 생겼습니다.");
						}
					}
				},
				null,
				null
			);	
    	}
	    //총 재생 시간 
	    function totalTimeCheck(){    	
	    	apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/ContentTotalTime.do",
    			{    				
    				detailSeq : $("#comPageInfo").val()    				
    			},
    			null,
    			function(result) {
    				if (result != null) {    					
    					//시간 변경값 가지고 오기    		
    					/* console.log(result); */
    					$("#conTimeInterval").text(result);        						    			
    				}
    			},
    			null,
    			null
    		);	
    	}
	    //페이지 정보 변경 
	    function pageChange(){
	    	var detailSeq = $("#comPageInfo").val();
	    	//우측 화면 빈값으로 
	    	contentListDetail(detailSeq);
	    	$("#content_img").html("");
	    	$("#fileWidth").val("");
			$("#fileHeight").val("");
			$("#playTime").val("");
			$("#size_w").text("");
	   	    $("#size_h").text("");   	 
	   	    $("#content_interval").val("");	
	    }
    	function contentLst(){    	
    		
    		var callConType = "";
    		if("${regist.codeNm}" == "음원POP"){
    			callConType = "MUSIC";
    		}
    		
			strPage = parseInt((parseInt($("#strPage").val()) -1) * parseInt($("#conPageSize").val()));
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/jsonContentLst.do",
				{
					mediaType 	: callConType,
					strPage 	: strPage,			  
					conPageSize : $("#conPageSize").val()
					
				},
				null,				
				function(result) {							
					if (result != null) {	   
						if (result.jsonCon != null) {
							var imgSlider = " ";
							var thumW = "";
							var thumH = "";
 		    				for (var i=0; i<result.jsonCon.length; i++) {
	    						var obj = result.jsonCon[i];
	    						var onclickValue = "";
	    						var imgCreate = "";
	    						if(obj.fileWidth == null){
	    							// 로딩되지 않은 콘텐츠
	    							onclickValue = "view_Con(&#39;"+obj.atchFileId+"&#39;)";
									if (obj.mediaType == "IMAGE"){
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.streFileNm +"' onError='this.src=&#39;/img/no_image.png&#39;;' style='width:120px; height:120px;'/><br><span style='color:#FF4b4b'>미디어 로딩이 필요합니다.</span>";  
									} else {
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.fileThumnail +"' onError='this.src=&#39;/img/no_image.png&#39;;' style='width:120px; height:120px;'/><br><span style='color:#FF4b4b'>미디어 로딩이 필요합니다.</span>";
									}
	    						}else{
	    							onclickValue = "conSchReg(&#39;"+obj.atchFileId+"&#39;)";
		    						if((obj.fileWidth - obj.fileHeight) > 0){
										// 가로
										thumW = "140px";
										thumH = "auto";
									}else{
										// 세로
										thumW = "auto";
										thumH = "140px";
									}
		    						if (obj.mediaType == "IMAGE"){
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.streFileNm +"'  style='width:"+thumW+"; height:"+thumH+";' />";   
									}else{
	    								imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.fileThumnail +"'  style='width:"+thumW+"; height:"+thumH+";' />";
									}
	    						}
								if (obj.mediaType == "IMAGE"){
									imgSlider  += "<tr><td colspan='3' style='border-bottom:0;'><div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='"+onclickValue+"'>"+imgCreate+"<br>"+obj.orignlFileNm+"</div></td></tr>";   
								} else {
									imgSlider  += "<tr><td colspan='3' style='border-bottom:0;'><div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='"+onclickValue+"'>"+imgCreate+"<br>"+obj.orignlFileNm+"</div></td></tr>";
								}
								
								
								/* if (obj.mediaType == "IMAGE"){
									imgSlider  += "<div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='conSchReg(&#39;"+obj.atchFileId+"&#39;)'><img data-u='thumb' src='"+obj.fileStreCours + obj.streFileNm +"'  style='width:"+thumW+"; height:"+thumH+";' /><br>"+obj.orignlFileNm+"</div>";   
								} else {
									imgSlider  += "<div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='conSchReg(&#39;"+obj.atchFileId+"&#39;)'><img data-u='thumb' src='"+obj.fileStreCours + obj.fileThumnail +"'  style='width:"+thumW+"; height:"+thumH+";' /><br>"+obj.orignlFileNm+"</div>";
								}  */   					
								thumW = null;
								thumH = null;
							}
	    					imgSlider += "";
	    					$("#con_img_lst").html(imgSlider); 
	    				}else{
	    					alert("없음");
	    				}
					}
				},
				null,
				null
			);	
    	}
    	function contentSearchLst(){    	
			strPage = parseInt((parseInt($("#strPage").val()) -1) * parseInt($("#conPageSize").val()));
			$("#searchKeyword").val($("#searchConName").val());
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/jsonDetailContentLst.do",
				{
					strPage : strPage ,					  
					conPageSize : $("#conPageSize").val(),
					mediaType : $("#mediaType").val(),
					originFileNm : $("#searchKeyword").val()	
				},
				null,				
				function(result) {							
					if (result != null) {	   
						if (result.jsonCon != null) {
							var imgSlider = " ";
							var thumW = "";
							var thumH = "";
							for (var i=0; i<result.jsonCon.length; i++) {
								var obj = result.jsonCon[i];
	    						var onclickValue = "";
	    						var imgCreate = "";
	    						if(obj.fileWidth == null){
	    							// 로딩되지 않은 콘텐츠
	    							onclickValue = "view_Con(&#39;"+obj.atchFileId+"&#39;)";
	    							if (obj.mediaType == "IMAGE"){
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.streFileNm +"' style='width:120px; height:120px;'/><br><span style='color:#FF4b4b'>미디어 로딩이 필요합니다.</span>";  
									} else {
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.fileThumnail +"' style='width:120px; height:120px;'/><br><span style='color:#FF4b4b'>미디어 로딩이 필요합니다.</span>";
									}
	    						}else{
	    							onclickValue = "conSchReg(&#39;"+obj.atchFileId+"&#39;)";
		    						if((obj.fileWidth - obj.fileHeight) > 0){
										// 가로
										thumW = "140px";
										thumH = "auto";
									}else{
										// 세로
										thumW = "auto";
										thumH = "140px";
									}
		    						if (obj.mediaType == "IMAGE"){
										imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.streFileNm +"'  style='width:"+thumW+"; height:"+thumH+";' />";   
									}else{
	    								imgCreate = "<img data-u='thumb' src='"+obj.fileStreCours + obj.fileThumnail +"'  style='width:"+thumW+"; height:"+thumH+";' />";
									}
	    						}
								if (obj.mediaType == "IMAGE"){
									imgSlider  += "<tr><td colspan='3' style='border-bottom:0;'><div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='"+onclickValue+"'>"+imgCreate+"<br>"+obj.orignlFileNm+"</div></td></tr>";   
								} else {
									imgSlider  += "<tr><td colspan='3' style='border-bottom:0;'><div id='" + obj.atchFileId +"' style='width:150px; margin-right: 10px; display: inline-block; white-space: normal; word-break:normal;' onclick='"+onclickValue+"'>"+imgCreate+"<br>"+obj.orignlFileNm+"</div></td></tr>";
								}			
								thumW = null;
								thumH = null;
	    					}
							imgSlider += "";
							$("#con_img_lst").html(imgSlider); 
	    				}else{
	    					alert("없음");
	    				}
					}
				},
				null,
				null
			);	
		}
    	//이전 가기
    	function pre(){
	    	if ($("#strPage").val() == "1"){
    			alert("이전 페이지가 없습니다");
    		}else {
	    		var strPage = $("#strPage").val();
	    		strPage =  (parseInt(strPage) - 1);
	    		$("#strPage").val(strPage);
    		
    			if($("#searchKeyword").val() != ""){
	    			contentSearchLst();
	    		} else {
	    			if($("#mediaType").val() != ""){
	    				contentSearchLst();
	    			} else {
	    				contentLst();    				
	    			}
    			}
    		}
    	}
	    //다음 가기 
	    function next(){
	    	var endPage = Math.ceil(parseInt($("#contentTotal").val())/parseInt($("#conPageSize").val()));
	    	if ($("#strPage").val() == endPage){
	    		alert("다음 페이지가 없습니다");
	    	}else {
	    		var strPage = parseInt((parseInt($("#strPage").val()) + 1));    		
	    		$("#strPage").val(strPage);
	    		
	    		if($("#searchKeyword").val() != ""){
	    			contentSearchLst();
	    		} else {
	    			if($("#mediaType").val() != ""){
	    				contentSearchLst();
	    			} else {
	    				contentLst();    				
	    			}
	    		}
    		}    	
    	}
    </script>
	<script>
		$(".content").sortable({
		    revert: true,
			placeholder: "block-placeholder",
			handle: ".dragHandle",
			update: function (event, ui) {
				ui.item.addClass("block");
				//업데이트 할때 리스트 보여 주기 								
			}, 
			stop:function (event, ui) { 
			    var json = "";			    
			    $('.content div').each(function(i, item){	
			    	if(i == 0){
			    		if($(this).attr("class") != "conBoxHeader"){
			    			
			    		}
			    	}
				})
				item_order();
			}
		});
		function item_order(){
	        var length = $("#test-list").find('.listImg').length;   			
			for (i=0; i < length ; i++ ){	 		    
				var orderTagId = $("#test-list").find('.orderSeq').eq(i).html(i+1);
				var img_id =$("#test-list").find('.listImg').eq(i).attr('id').replace('file_','');
				fn_orderUpdate(img_id, i);
			}	
		}		
		function fn_orderUpdate(fileSeq, order_s){			
			apiExecute(
	   			"POST", 
	   			"/backoffice/sub/conManage/ContentUpdateOrderFile.do",
	   			{ 
	   				fileSeq : fileSeq,	    				 
					fileOrder : order_s
				},
				null,
				function(result) {
					if (result == "O") {    					
		    					    						    					
					} else {
						alert("콘텐츠 업데이트 애러");
					}
				},
				null,
				null
			);
		}
			
		//콘텐츠 드레그 후 데이터 베이스에 입력 
		function contentUplade( itemSeq, div_id){
			var content_block = "";
			apiExecute(
				"POST", 
	   			"/backoffice/sub/conManage/ContentReg.do",
	   			{ 
	   				conSeq: conSeqValue,
	   				detailSeq: $("#comPageInfo").val(),
	   				atchFileId : itemSeq, 
	   				fileOrder : "0"
	   			},
	   			null,
	   			function(result) {
	   				if (result != "F") {    		
	   					var strArray = result.split('|');
	   					//여기 부분 다시 정리 하기	    					
	   					//contentBlock = content_blockInfo(itemSeq, strArray[1]);
	   					$("#conTimeInterval").text(strArray[2]);
	   					apiExecute(
							"POST", 
							"/backoffice/sub/conManage/conDetailFileInfoTableSeq.do",
	 			    		{ 	    				
	   			    			fileSeq : strArray[1] 	    				
	   			    		},
	   			    		null,
	   			    		function(result) {
	   			    				    				
	   			    			if (result.detailFileInfo != "") {    
	   			    				var obj = result.detailFileInfo;
	   			    			    if (obj != ""){    	 			    	  
	   			    			   		if  (obj.mediaType == "IMAGE"){
											content_block = "<table border='0'><tr style='background-color:white;'><td class='orderSeq'></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img src='"+obj.fileStreCours + obj.streFileNm +"' width='100px;' height='100px;' id='"+strArray[1]+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+strArray[1]+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>삭제</a></td></tr></table>";
											$("#content_img").html("<img src='"+obj.fileStreCours + obj.streFileNm +"' width='200px;' height='200px;'>"); 
										}else {
											content_block = "<table border='0'><tr style='background-color:white;'><td class='orderSeq'></td><td style='background-color:white;'><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img src='"+obj.fileStreCours + obj.fileThumnail +"' width='100px;' height='100px;' id='"+strArray[1]+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"<br>("+obj.streFileNm+")</a></td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+strArray[1]+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>삭제</a></td></tr></table>";
											$("#content_img").html("<img src='"+obj.fileStreCours + obj.fileThumnail +"' width='200px;' height='200px;'>"); 
										}
	   			    			    	  
										//여기 부분 해결 하기 
										$("#content_interval").val(obj.timeInterval);
										$("#size_w").text(obj.fileWidth);
										$("#size_h").text(obj.fileHeight);
										$("#content_order").text(obj.fileOrder);  	    				    					  
										$("#listItem_"+div_id).html(content_block);
									}	    					   						    					
	   			    			}else {
	   			    				alert("없음");
	   			    			}
	   			    		},
	   			    		null,
	   			    		null
	   			    	);		
	   				}else {
	   					alert("콘텐츠 업데이트 애러");
	   				}
	   			},
	   			null,
	   			null
	   		);
			totalTimeCheck();
			if (content_block!= ""){ return content_block; } else { setTimeout(function() { return content_block;  }, 300); }
		}
			
		function content_blockInfo(code, fileSeq){
			var content_block = "";
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/conDetailFileInfoTable.do",
				{ 	    				
					atchFileId : code 	    				
				},
				null,
				function(result) {
					if (result.detailFileInfo != "") {    
						var obj = result.detailFileInfo;
						if (obj != ""){    	 			    	  
							if  (obj.mediaType == "IMAGE"){
								content_block = "<table border='0'><tr><td><a href='javascript:view_info(&#39;"+code+"&#39;)'><img src='"+obj.fileStreCours + obj.streFileNm +"' width='100px;' height='100px;' id='"+fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+code+"&#39;)'>"+obj.orignlFileNm+"</a></td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+fileSeq+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>삭제</a></td></tr></table>";
								$("#content_img").html("<img src='"+obj.fileStreCours + obj.streFileNm +"' width='200px;' height='200px;'>"); 
							}else {
								content_block = "<table border='0'><tr><td><a href='javascript:view_info(&#39;"+code+"&#39;)'><img src='"+obj.fileStreCours + obj.fileThumnail +"' width='100px;' height='100px;' id='"+fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+code+"&#39;)'>"+obj.orignlFileNm+"</a></td><td><a class='delkey boxH grayBtn' href='javascript:del_Content(&#39;"+fileSeq+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>삭제</a></td></tr></table>";
								$("#content_img").html("<img src='"+obj.fileStreCours + obj.fileThumnail +"' width='200px;' height='200px;'>"); 
							}
					    	  
							//여기 부분 해결 하기 
							$("#content_interval").val(obj.timeInterval);
							$("#size_w").text(obj.fileWidth);
							$("#size_h").text(obj.fileHeight);
							$("#content_order").text(obj.fileOrder);  
						}	    					   						    					
					} else {
						alert("없음");
					}
				},
				null,
				null
			);		
				
			if (content_block!= ""){ return content_block; } else { setTimeout(function() { return content_block;  }, 100); }
		} 
		function view_Con(atchId){
	    	  var url = "/backoffice/sub/conManage/fileView.do?atchFileId="+atchId;
	    	  window.open(url,"파일 미리보기", 'width=768,height=768,top=100,left=250,scrollbars=auto');
	      }
	</script>
	
</body>
</html>		