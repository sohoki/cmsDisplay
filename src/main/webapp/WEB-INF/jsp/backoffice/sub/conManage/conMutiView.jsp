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
<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/jquery.treemenu.css" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="/js/common.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>

<style type="text/css">
  .clear { clear: both; }
  .con { position: relative; width: 770px;  }
  .items { height: 108px; overflow-x: scroll; white-space: nowrap; width: 700px;margin-right: 15px; float: left;}
  .items div{display: inline-block; padding:3px 10px; background:#f9f9f9; height:108; }
  .infor { width: 100%; position: relative; margin-top: 30px;}
  .content{width:500px;background:#f9f9f9;position:relative;padding:10px; max-height: 500px; overflow-y: scroll; float: left; margin-right: 18px;}
  .items .block,
  .content .block{height:100px!important;background:#dadada;margin-bottom:10px;width:480px;color:#000; overflow: hidden;}    
  .content .block-placeholder{height:98px!important;border:1px dotted black;margin-bottom:10px;width:498px;}
  .file_info {background:#FFF;color:#000; float: left;}    
  .file_info table, .file_info tr { border: none; background:#f9f9f9; margin: 0; padding: 0; border-collapse: collapse;} 
  .file_info table td { border-right:none; border-left: none; border-bottom: dashed 1px #777; line-height: 1.8em; border-top: none;}
  .control { float: left; margin-right: 15px; margin-top: 30px;}
  a { color: #1b1b1b; text-decoration: none; } 
</style>

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<c:import url="/backoffice/inc/emart_header.do" />
<div class="pageTop">
		<div class="conIn">
			<h2>화면&콘텐츠관리</h2>
			<div class="pageLocation"> 
				<img src="/img/icon_home.png" alt="home"> 
				기초관리 > 화면&콘텐츠관리
			</div>
			<div class="clear"></div>
		</div>
	</div>		
	<div id="container">
		<!--내용시작-->
	    <form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/conMutiDetail.do">						
		<form:hidden path="conSeq" id="conSeq"/>
		<form:hidden path="mode" id="mode"/>
		<form:hidden path="conScreen" id="conScreen"/>
		<form:hidden path="pageIndex" />
        <form:hidden path="pageSize" />
        <form:hidden path="searchCondition" />
        <form:hidden path="searchKeyword" />
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
        <div class="con">			
			<div class="tableBox editTable sizeTable iframeTd" style='vertical-align:top'>
				
				<table style="width:100%">
					<tbody>
						<tr>
							<th>화면명</th>
							<td>${regist.conNm }</td>
							<th>화면타입</th>
							<td>${regist.codeNm }</td>
							<th>가로/세로</th>
							<td>${regist.conType }</td>							
						</tr>
						<tr>
							<th>사용여부</th>
							<td>
							<c:choose>
							   <c:when test="${regist.conUseYn eq 'Y' }">사용</c:when>
							   <c:otherwise>사용 안함</c:otherwise>
							</c:choose>								
							</td>							
							<th>화면 사이즈</th>
							<td class="smiddleBox" colspan="3">
							가로: ${regist.conWidth }  
							세로: ${regist.conHeight }
							분할길이: ${regist.conMid } 						
							</td>
						</tr>
						<tr>
							<th>콘텐츠구분</th>
							<td>${regist.conPlayType }</td>							
							<th>URL구분</th>
							<td>${regist.conUrlType }</td>
							<th>기본URL</th>
							<td>${regist.conBasicUrl }</td>							
						</tr>
						<tr>
							<th>운영시간</th>
							<td><span id="conTimeInterval">${regist.conTime }</span></td>
							<th>다음시퀀스</th>
							<td colspan="3">${regist.conNextTitle }</td>														 					
						</tr>
						<tr>
						  <td>페이지 구분</td>
						  <td colspan="5" align="right">
						    <select id="comPageInfo"  onChange="javascript:pageChange()"></select>
						  </td>
						</tr>
						<tr>
						  <td colspan="6">
						  <div class="con">
						     <div class='control'><a href='javascript:pre();'><img src='/img/prev.png' alt='pre'></a></div>  
						     <div id="imgLst" class='items'></div>				
						     <div class='control'><a href='javascript:next()'><img src='/img/next.png' alt='next'></a></div>		     
						  </div>
						  
						  <div class="clear"></div>
						   
						    <div class="content" id="test-list">		
						       	      
						    </div>
						    <div class="file_info">
						         <table border="1" width="100" height="100">
								        <tr>
								          <td colspan="2">
								           <span id="content_img" />
								          </td>
								        </tr>
								        <tr><td>TIME</td><td><input type="text" id="content_interval" size="2"></td></tr>
								        <tr><td>SIZE</td><td>넓이:<span id="size_w" ></span> 높이:<span id="size_h" ></span></td></tr>
								        <tr><td>확장자</td><td><span id="fileExt" /></td></tr>
								        <tr><td>순서</td><td><span id="content_order" /></td></tr>
								        <tr><td colspan="2" align="center"><a href="javascript:saveInterval()">입력</a></td></tr>
						       </table>
						    </div>
						    <div class="clear"></div>
						  </div>
						  						  
						  </td>
						</tr>										
					</tbody>
				</table>
				<div id="conDetail"></div>
				<div class="btnBox md_btn">	
					<a href="javascript:listPage('regist','/backoffice/sub/conManage/conMutiList.do' )" class="excel boxH">목록</a> 					
					<a href="javascript:check_form()" class="modi boxH" id="btnUpdate">수정</a>					
					<a href="javascript:preView();" class="excel boxH">미리보기</a>					
					<a href="javascript:del_form()" class="delkey boxH">삭제</a>					
				</div>
				<div class="clear"></div>
			</div>
		</div>
		</form:form>
		<!--내용끝-->
	</div>
	<div class="clear"></div>
    
    <script type="text/javascript">
    function saveInterval(){    	
    	if (any_empt_line_id("content_interval", "운영시간을 입력 하지 않았습니다.") == false) {
    	}else {    		
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
        					$("#conTimeInterval").html(result);        						
        					}
        				}
        			},
        			null,
        			null
        		);	
    	}
    	
    }
    function preView(){
    	var PageWidth = "${regist.conWidth }";
    	var PageHeight = "${regist.conHeight }";
    	
    	apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/preViewCheck.do",
    			{
    				conSeq : $("#conSeq").val()
    			},
    			null,
    			function(result) {
    				if (result != null) {    					
    					//시간 변경값 가지고 오기    					
    					if (result != "F"){
    						var url = "/backoffice/sub/conManage/contentPreview.do?conSeq="+$("#conSeq").val();	      	
    				  	    window.open(url,"파일 업로드", "width="+ PageWidth +",height="+PageHeight+",top=50,left=50,scrollbars=auto")	;      						
    					}else {
    						alert("이미지 중 타임값을 넣지 않은 콘텐츠가 있습니다.");
    					}
    				}
    			},
    			null,
    			null
    		);	    	
  	    
    }
    function del_form(){
    	if (confirm("삭제 하시겠습니까?")== true){
    		  document.regist.action = "/backoffice/sub/conManage/conMutiDel.do";
     		  document.regist.encoding = "application/x-www-form-urlencoded"; 
     		  document.regist.submit();	  
    	}else {
    		
    	}
    }
    function check_form(){
    	$("#mode").val("Edt");
    	$("form[name=regist]").attr("action", "/backoffice/sub/conManage/conMutiDetail.do").submit();
    }
    //
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
    				conSeq : $("#conSeq").val()    				
    			},
    			null,
    			function(result) {
    				if (result != null) {    					
    					for (var i=0; i<result.jobCombo.length; i++) {
    						var obj = result.jobCombo[i];
    						$("<option value='"+ obj.detailSeq +"'>"+ obj.detailOrder +"페이지</option>").appendTo("#comPageInfo");
    					}    					
    					//이쪽 부분 다시 정리 하기
    					setTimeout( contentListDetail($("#comPageInfo").val()), 400);
    					
    					
    				}else {
    					alert("없음");
    				}
    			},
    			null,
    			null
    		);
    	   
    });
    function contentListDetail(code){    	
    	apiExecute(
    			"POST", 
    			"/backoffice/sub/conManage/conDetailInfoTable.do",
    			{
    				conSeq : $("#conSeq").val(),
    				detailSeq : code
    			},
    			null,
    			function(result) {
    				if (result != null) {    					
    					if (result.detailContentInfo != null) {
    						var condivLst = "";
		    					 for (var i=0; i<result.detailContentInfo.length; i++) {
		    						var obj = result.detailContentInfo[i];   
		    						   if (obj.mediaType == "IMAGE"){
		    							   condivLst += "<div id='listItem_"+i+"'><table border='0'><tr><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.streFileNm +"' width='100px;' height='100px;' id='file_"+obj.fileSeq+"' ></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.streFileNm+"</a></td><td><a href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+code+"&#39; )'>[삭제]</a></td></tr></table></div>";   
		    						   }else {
		    							   condivLst += "<div id='listItem_"+i+"'><table border='0'><tr><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.fileThumnail +"' width='100px;' height='100px;' id='file_"+obj.fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.streFileNm+"</a></td><td><a href='javascript:del_Content(&#39;"+obj.fileSeq+"&#39;, &#39;"+code+"&#39;)'>[삭제]</a></td></tr></table></div>";
		    						   }
		    					 }		    					 
		    					$("#test-list").html(condivLst); 
		    					item_order();
		    				}else{
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
    				    	  if  (obj.mediaType == "IMAGE"){	    				      
	    				           $("#content_img").html("<img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.streFileNm +"' width='200px;' height='200px;'>");
    				    	  }else {    				    		  
    				    		  $("#content_img").html("<img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.fileThumnail +"' width='200px;' height='200px;'>");
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
    	    					  
    				    	  }else {
    				    		  
    				    		var fileUrl = "http://"+$(location).attr('host')+"/upload/"+ obj.streFileNm.substring(0,6) +"/"+ obj.streFileNm;
    				    		  
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
 							}else {
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
								$("#conTimeInterval").html(result);        									
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
    					$("#conTimeInterval").html(result);        						    			
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
    	 strPage = parseInt((parseInt($("#strPage").val()) -1) * parseInt($("#conPageSize").val()));
    	 
    	 apiExecute(
				  "POST", 
				  "/backoffice/sub/conManage/jsonContentLst.do",
					{
						strPage : strPage ,					  
	    				conPageSize : $("#conPageSize").val()		
					},
					null,				
					function(result) {							
 						if (result != null) {	   
 							 
 							if (result.jsonCon != null) {
 		    					
 		    					var imgSlider = " ";
 		    					 for (var i=0; i<result.jsonCon.length; i++) {
 		    						var obj = result.jsonCon[i];
 		    						   if (obj.mediaType == "IMAGE"){
 		    							   imgSlider  += "<div id='" + obj.atchFileId +"' style='width:100px;background:#444;color:#fff;margin-bottom:4px;padding:6px;text-align:center; margin-right: 15px; display: inline-block;'><img data-u='thumb' src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.streFileNm +"'  style='width:100px; height:100px' /></div>";   
 		    						   }else {
 		    							   imgSlider  += "<div id='" + obj.atchFileId +"' style='width:100px;background:#444;color:#fff;margin-bottom:4px;padding:6px;text-align:center; margin-right: 15px; display: inline-block;'><img data-u='thumb' src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.fileThumnail +"'  style='width:100px; height:100px' /></div>";
 		    						   }    					
 		    					}
 		    					imgSlider += "";
 		    					$("#imgLst").html(imgSlider); 
 		    					//드레그 관련 내용 
 		    					$(".items div").draggable({			
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
 		    				    });
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
    		contentLst();
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
    		contentLst();
    	}    	
    }
    </script>
     <script>
		$(".content").sortable({
		    revert: true,
			placeholder: "block-placeholder",
			update: function (event, ui) {
				ui.item.addClass("block");
				//업데이트 할때 리스트 보여 주기 								
			}, 
			stop:function (event, ui) { 
			    var json = "";			    
			    $('.content div').each(function(i, item){			    				    	
				})
				item_order();
			}
		});
		function item_order(){
	        var length = $("#test-list").find('img').length;   			
			for (i=0; i < length ; i++ ){	 		    
				var img_id =$("#test-list").find('img').eq(i).attr('id').replace('file_','');
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
	    					    						    					
	    				}else {
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
	    				conSeq: $("#conSeq").val(),
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
	    					$("#conTimeInterval").html(strArray[2]);
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
	    				    				           content_block = "<table border='0'><tr><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.streFileNm +"' width='100px;' height='100px;' id='"+strArray[1]+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"'</a></td><td><a href='javascript:del_Content(&#39;"+strArray[1]+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>[삭제]</a></td></tr></table>";
	    				    				           $("#content_img").html("<img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.streFileNm +"' width='200px;' height='200px;'>"); 
	    			    				    	  }else {
	    			    				    		   content_block = "<table border='0'><tr><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'><img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.fileThumnail +"' width='100px;' height='100px;' id='"+strArray[1]+"'></a></td><td><a href='javascript:view_info(&#39;"+obj.fileSeq+"&#39;)'>"+obj.orignlFileNm+"'</a></td><td><a href='javascript:del_Content(&#39;"+strArray[1]+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>[삭제]</a></td></tr></table>";
	    			    				    		   $("#content_img").html("<img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.fileThumnail +"' width='200px;' height='200px;'>"); 
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
		    				           content_block = "<table border='0'><tr><td><a href='javascript:view_info(&#39;"+code+"&#39;)'><img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.streFileNm +"' width='100px;' height='100px;' id='"+fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+code+"&#39;)'>"+obj.orignlFileNm+"'</a></td><td><a href='javascript:del_Content(&#39;"+fileSeq+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>[삭제]</a></td></tr></table>";
		    				           $("#content_img").html("<img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.streFileNm +"' width='200px;' height='200px;'>"); 
	    				    	  }else {
	    				    		   content_block = "<table border='0'><tr><td><a href='javascript:view_info(&#39;"+code+"&#39;)'><img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.fileThumnail +"' width='100px;' height='100px;' id='"+fileSeq+"'></a></td><td><a href='javascript:view_info(&#39;"+code+"&#39;)'>"+obj.orignlFileNm+"'</a></td><td><a href='javascript:del_Content(&#39;"+fileSeq+"&#39;, &#39;"+$("#comPageInfo").val()+"&#39; )'>[삭제]</a></td></tr></table>";
	    				    		   $("#content_img").html("<img src='/upload/"+obj.streFileNm.substring(0,6)+"/" + obj.fileThumnail +"' width='200px;' height='200px;'>"); 
	    				    	  }
	    				    	  
	    				    	  //여기 부분 해결 하기 
		    					  $("#content_interval").val(obj.timeInterval);
		    					  $("#size_w").text(obj.fileWidth);
		    					  $("#size_h").text(obj.fileHeight);
		    					  $("#content_order").text(obj.fileOrder);  
	    				      }	    					   						    					
	    				}else {
	    					alert("없음");
	    				}
	    				
	    			},
	    			null,
	    			null
	    		);		
			
			if (content_block!= ""){ return content_block; } else { setTimeout(function() { return content_block;  }, 100); }
		} 
	</script>
</body>
</html>		