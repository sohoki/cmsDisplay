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
    <title>운영관리</title>    
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/paragraph.css">
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<link href="<c:url value='/'/>css/needpopup.min.css" rel="stylesheet" type="text/css" >
	<!--subPop -->
	<script src="/js/popup.js"></script>
	<!--leftMenu-->
	<script src="/js/leftMenu.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<%-- <link href="<c:url value='/'/>css/bootstrap.min.css" rel="stylesheet" type="text/css" > --%>
	<!-- <script type="text/javascript" src="/js/bootstrap.min.js"></script> -->
	<style>
	div table { margin: 0 ; border:0; }
	table a { display: block; margin:auto; text-align: center;}
	.tableBorder{border:1px solid #dfdfdf; border-collapse: collapse;}
	.cursorPoint{cursor: pointer;}
	</style>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<div id="wrap">
		<c:import url="/backoffice/inc/emart_header.do" />
		<div class="left-contain">
			<c:import url="/backoffice/inc/cms_left.do" />
			<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
		</div>	
		<div class="container">
			<div class="main-content">
				<div class="content" >
					<!--//상단 탭메뉴-->
					<ul class="topMenu" >
						<li class="active"><a href="/backoffice/sub/conManage/mediaLst.do" class="media">미디어 관리</a></li>
						<li><a href="/backoffice/sub/conManage/conMutiList.do" class="conMutiList">화면 구성</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">
						<!--미디어등록-->
						<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/conManage/mediaLst.do">				    
	    
				        <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
				        <input type="hidden" name="menuGubun" id="menuGubun" value="${searchVO.menuGubun }" >
				        <input type="hidden" name="mode" id="mode" >
				        <input type="hidden" name="atchFileId" id="atchFileId" >    
				        
				        <div class="con_title whiteBox">
					          <h2><img src="/img/list.png"> 총 등록된 미디어 리스트입니다.</h2>
								<div class="searchBox">
										<span>총 : ${totalCnt}개</span>
										<select name="pageUnit" id="pageUnit" >
											<option value="12" <c:if test="${searchVO.pageUnit == '12' }"> selected="selected" </c:if>>12개씩 보기</option>
											<option value="24" <c:if test="${searchVO.pageUnit == '24' }"> selected="selected" </c:if>>24개</option>
											<option value="36" <c:if test="${searchVO.pageUnit == '36' }"> selected="selected" </c:if>>36개</option>
											<option value="48" <c:if test="${searchVO.pageUnit == '48' }"> selected="selected" </c:if>>48개</option>
										</select>	
										<select name="mediaType" id="mediaType">
										    <option value="">선택</option>
											<option value="IMAGE" <c:if test="${searchVO.conType == 'IMAGE' }"> selected="selected" </c:if>>이미지</option>
											<option value="MUSIC" <c:if test="${searchVO.conType == 'MUSIC' }"> selected="selected" </c:if>>음악</option>
											<option value="MEDIA" <c:if test="${searchVO.conType == 'MEDIA' }"> selected="selected" </c:if>>동영상</option>
										</select>
										<select name="notConType" id="notConType" style="display:none;">
										    <option value="">선택</option>
											<option value="IMAGE" <c:if test="${searchVO.notConType == 'IMAGE' }"> selected="selected" </c:if>>이미지</option>
											<option value="MUSIC" <c:if test="${searchVO.notConType == 'MUSIC' }"> selected="selected" </c:if>>음악</option>
											<option value="MEDIA" <c:if test="${searchVO.notConType == 'MEDIA' }"> selected="selected" </c:if>>동영상</option>
										</select>
										<select name="searchCondition"  id="searchCondition">
											<option value="">선택</option>
											<option value="atchFileId" <c:if test="${searchVO.searchCondition == 'atchFileId' }"> selected="selected" </c:if>>실파일명</option>
											<option value="ORIGNL_FILE_NM" <c:if test="${searchVO.searchCondition == 'ORIGNL_FILE_NM' }"> selected="selected" </c:if>>업로드파일명</option>
										</select>																	
										<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
										<a href="javascript:search_form()" class="yellowBtn">검색</a>
									    <div class="footerBox">
									    <a href="javascript:open_musicPop()" class="blueBtn">음원POP 파일 등록</a>
									    <a href="javascript:open_pop()" class="yellowBtn">사이니지 파일 등록</a>
									    </div>
									<div class="clear"></div>
								</div>
								<table style="border:0;">
									<tbody style="border:0;">
									   <c:set var="i" value="0" />
									   <c:set var="j" value="4" />
									   <c:forEach items="${resultList }" var="fileInfo" varStatus="status">
									   <c:if test="${i%j == 0 }">
									   <tr style="border:0;">
									   </c:if>						
											<td style="border:0; width:300px;">
											   <table class="tableBorder mediaTable">
											      <tr style="height:200px;">
											        <c:choose>
													<c:when test="${null eq fileInfo.fileWidth}">
													<td class="cursorPoint" style="border:0;" onclick="view_Con('${fileInfo.atchFileId}','1024','768');">
													</c:when>
													<c:otherwise>
													<td class="cursorPoint" style="border:0;" onclick="view_Con('${fileInfo.atchFileId}','${fileInfo.fileWidth}','${fileInfo.fileHeight}');">
													</c:otherwise>
													</c:choose>
														<a style="text-decoration: none;">
											        <c:choose>
														<c:when test="${ fileInfo.mediaType eq 'IMAGE'}">
															<c:choose>
																<c:when test="${null eq fileInfo.fileWidth}">
																	<!-- 콘텐츠 활성화 전 상태 -->
																	<!-- <img id="atchFileId" name="atchFileId" src="/img/no_image.png"  style="width:96px; height:96px; position:absolute; z-index:2; opacity:0.7;"/> -->
																	<img id="atchFileId" name="atchFileId" src="<c:url value="${fileInfo.fileStreCours}${fileInfo.streFileNm}"/>" style="width:96px; height:96px; z-index:1; opacity:0.99;"/><br>
																	<span class="active_plz_txt">이미지를 클릭하여<br>콘텐츠를 활성화 해주세요.</span>
																</c:when>
																<c:otherwise>
																	<script>
																		var conSize = ${fileInfo.fileWidth}-${fileInfo.fileHeight};
																		if(conSize > 0){
																			// 가로
																			document.write('<img id="atchFileId" name="atchFileId" src="<c:url value="${fileInfo.fileStreCours}${fileInfo.streFileNm}"/>" onError="this.src=&#39;/img/no_image.png&#39;;" style="width:160px; height:auto;"/>');
																		}else{
																			// 세로
																			document.write('<img id="atchFileId" name="atchFileId" src="<c:url value="${fileInfo.fileStreCours}${fileInfo.streFileNm}"/>"  onError="this.src=&#39;/img/no_image.png&#39;;" style="width:auto; height:160px;"/>');
																		}
																	</script>
																</c:otherwise>
															</c:choose>
										                </c:when>
										                <c:when test="${ fileInfo.mediaType eq 'MUSIC'}">
										                  <img id="atchFileId" name="atchFileId" src="/img/no_image.png"  style="width:160px; height:160px;"/>
										                </c:when>
										        		<c:otherwise>
															<c:choose>
																<c:when test="${null eq fileInfo.fileWidth}">
																	<!-- 콘텐츠 활성화 전 상태 -->
																	<!-- <img id="atchFileId" name="atchFileId" src="/img/no_image.png"  style="width:96px; height:96px; position:absolute; z-index:2; opacity:0.7;"/> -->
																	<img id="atchFileId" name="atchFileId" src="<c:url value="${fileInfo.fileStreCours}${fileInfo.fileThumnail}"/>" onError="this.src='/img/no_image.png';" style="width:96px; height:96px; z-index:1; opacity:0.99;"/>
																	<br>
																	<span class="active_plz_txt">이미지를 클릭하여<br>콘텐츠를 활성화 해주세요.</span>
																</c:when>
																<c:otherwise>
																	<script>
																		var conSize = ${fileInfo.fileWidth}-${fileInfo.fileHeight};
																		if(conSize > 0){
																			// 가로
																			document.write('<img id="atchFileId" name="atchFileId" src="<c:url value="${fileInfo.fileStreCours}${fileInfo.fileThumnail}"/>" onError="this.src=&#39;/img/no_image.png&#39;;" style="width:160px; height:auto;"/>');
																		}else{
																			// 세로
																			document.write('<img id="atchFileId" name="atchFileId" src="<c:url value="${fileInfo.fileStreCours}${fileInfo.fileThumnail}"/>" onError="this.src=&#39;/img/no_image.png&#39;;" style="width:auto; height:160px;"/>');
																		}
																	</script>
																</c:otherwise>
															</c:choose>
														</c:otherwise>					        							        
											        </c:choose>
											        </a>
											        </td>
											        <!-- <td rowspan="3" width="5px"></td> -->
											      </tr>
 											      <tr style="border-bottom: 1px solid #dfdfdf;">
											      	<td style="border:0; white-space: normal; word-break: normal;">
														<p>${fileInfo.orignlFileNm}</p>
														<p>${fileInfo.streFileNm}</p>
													</td>
											      </tr>
											      <tr>
											        <td style="border:0;"><a class="redBtn" href="javascript:delConnectChk('${fileInfo.atchFileId}')">삭제</a></td>
											      </tr>											      
											   </table>							
											</td>
										<c:if test="${ i%j == j -1 }">
										</tr>
										</c:if>								
										<c:set var="i" value="${i+1 }" />
										</c:forEach>						
									</tbody>
								</table>
								
								<div class="clear"></div>
								<div class="pageFooter">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
								<div class="clear"></div>	
							</div>	
						</div>
						</form:form>								
						<!-- 미디어 등록 끝 부분 -->
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
		<form:form name="viewConDetail" commandName="viewConDetail" method="post" action="/backoffice/sub/conManage/conMutiView_back.do">
					<input type="hidden" name="conSeq" id="conSeq" > 
		</form:form>
		
		
		<div id='small-popup-preview' class="needpopup">
		   <div class="contents">
				<div class="header">
					<h2>
						<span id="spTitle" style="color: #fff;font-size: 20px;"></span>
						<br>
						<span id="subSpTitle" style='font-size:12px; color:#ccc;'></span>
					</h2>				
				</div>
				<div class="textT">
					<!--테이블시작-->	    
					<table id="basicList" style="color:#FFFFFF;">
						<!--내용시작-->
					</table>	
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/needpopup.min.js"></script>
    <script type="text/javascript">
      // 등록
      function open_pop(){
    	  var url = "/backoffice/sub/conManage/fileIupload.do"; 
    	  window.open(url,"파일 업로드", 'width=1036,height=512,top=100,left=650,scrollbars=auto');
      }
      function open_musicPop(){
    	  var url = "/backoffice/sub/conManage/fileMupload.do"; 
    	  window.open(url,"파일 업로드", 'width=1036,height=512,top=100,left=650,scrollbars=auto');
      }
      //파일 보기 
      function view_Con(atchId, w, h){
    	  var url = "/backoffice/sub/conManage/fileView.do?atchFileId="+atchId;
    	  
    	  if((w == 1024 && h == 768) || (w==h)){
    		  w = "768";
    		  h = "768";
    	  }else if(w - h > 0){
    		  // 가로
    		  w = "832";
    		  h = "468";
    	  }else{
    		  w = "468";
    		  h = "832";
    	  }
    	  window.open(url,"파일 업로드", 'width='+w+',height='+h+',top=100,left=250,scrollbars=auto');
      }
      function linkPage(pageNo) {
  		$(":hidden[name=pageIndex]").val(pageNo);				
  		$("form[name=regist]").submit();
  	  }
		function del_Con(atchId){
			apiExecute(
				"POST", 
				"/backoffice/sub/conManage/imageFileDel.do",
				{
					atchFileId : atchId
				},
				null,				
   	 			function(result) {							
   	  				if (result != null) {	         							
   	  					if (result == "O"){
   	  						alert("정삭적으로 삭제 되었습니다.");
   	  						document.location.reload();
   	  					}else {
   	  						alert("삭제시 문제가 생겼습니다.");
   	  						document.location.reload();
   	  					}
   	  				}
   	  			},
   	 			null,
   	 			null
			);
		}
		function delConnectChk(atchId){
			if(confirm("미디어파일을 삭제하시겠습니까?\n연결 된 화면이 존재하는 경우 삭제 되지 않습니다.")==true){
				apiExecute(
					"POST", 
					"/backoffice/sub/conManage/mediaFileConnChk.do",
					{
						atchFileId : atchId
					},
					null,				
    	 			function(result) {							
    	  				if (result != null) {	
    	  					
    	  					if(result.jsonCon.length > 0){
    	  						if(confirm("연결 된 콘텐츠가 "+result.jsonCon.length+"개 존재합니다.\n화면정보를 확인하시겠습니까?")==true){
    	  							 // data-needpopup-show="#small-popup-preview"
    	  							connViewListPop(result);
    	  						}
    	  					}else{
    	  						del_Con(atchId);
    	  					}
    	  				}
    	  			},
    	 			null,
    	 			null
				);
			}
		}
		function connViewListPop(resultList){
			
			$(".basicList").empty();

			$(".spTitle").html("연결 콘텐츠 리스트");
			$(".subSpTitle").html("* 모든 리스트에서 제외시 삭제가 가능합니다.");
			var timeHtml = "<table><thead><tr><th>화면구성명</th><th>등록갯수</th></tr></thead><tbody>";
			for (var i=0; i<resultList.jsonCon.length; i++) {
				var obj = resultList.jsonCon[i];	
				timeHtml += "<tr><td><a onclick='view_ConMuti(&#39;"+obj.conSeq+"&#39;)'>"+obj.conNm+"</a></td><td>"+obj.connCount+"개</td></tr>";
			}
			timeHtml += "</tbody></table>";
			$(".basicList").append(timeHtml);		

			$('#myModal').modal('show');

		}   
		function view_ConMuti(conSeq){
			// conSeq : conSeq
			$("#conSeq").val(conSeq);
			$("form[name=viewConDetail]").submit();
		}
		
		$(document).ready(function(){
			needPopup.config.custom = {
				'removerPlace': 'outside',
				'closeOnOutside': false,
				onShow: function() {},
				onHide: function() {}
			};
			needPopup.init();
			$('#basicGroupList tr').css("background-color","");
			
			
		});
		
    </script>
</body>
</html>		