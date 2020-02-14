<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	 <title>DID운영관리</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/reset.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
<style>
	#wrap { min-height :1080px;}
</style>
</head>
<body>
<span id="backgroundgif"></span>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/sub/brodManage/brodContentPlayList.do">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="atchFileId" id="atchFileId" >

	<div id="wrap">
		<c:import url="/backoffice/inc/emart_header.do" />	
		<div class="left-contain">
			<c:import url="/backoffice/inc/cms_left.do" />
			<div class="swipe-area"><a href="#" data-toggle=".left-contain" id="sidebar-toggle"> <span class="bar"></span> <span class="bar"></span> <span class="bar"></span> </a></div>
		</div>	
		<div class="container">
			<div class="main-content">
				<div class="content">
					<!--//상단 탭메뉴-->
					<ul class="topMenu" >
						<li><a href="/backoffice/sub/basicManage/centerList.do" class="playCenter">지점 관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playContentList.do" class="playMedia">음원파일관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodBasic.do" class="playMedia">기본음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/brodPlayInfo.do" class="playShedule">기본음원재생현황</a></li>
						<li><a href="/backoffice/sub/brodManage/brodContentList.do" class="playContents">음원콘텐츠관리</a></li>
						<li class="active"><a href="/backoffice/sub/brodManage/brodContentPlayList.do" class="playContents">스케줄음원관리</a></li>
						<li><a href="/backoffice/sub/brodManage/playShedule.do" class="playShedule">음원콘텐츠배포</a></li>
						<li><a href="/backoffice/sub/brodManage/playSheduleStatus.do" class="playShedule">콘텐츠배포현황</a></li>
						<div class="clear"></div>
					</ul>
					<!--//하단 콘텐츠-->
					<div class="con">					
						<!--콘텐츠스케줄-->
						<div class="con_sub ">
							<div class="groupCon whiteBox groupTable">
								<div class="innerBox">
									<div class="searchBox">
										<span>총 : ${totalCnt }건</span>
										<select name="pageUnit" id="pageUnit">								
											<option value="10" <c:if test="${searchVO.pageUnit == '10' }"> selected="selected" </c:if>>10개씩 보기</option>
											<option value="20" <c:if test="${searchVO.pageUnit == '20' }"> selected="selected" </c:if>>20개</option>
											<option value="30" <c:if test="${searchVO.pageUnit == '30' }"> selected="selected" </c:if>>30개</option>
											<option value="40" <c:if test="${searchVO.pageUnit == '40' }"> selected="selected" </c:if>>40개</option>							
										</select>	
										<select id="searchCondition" name="searchCondition" class="blan">
											<option value="orignlFileNm" <c:if test="${searchVO.searchCondition == 'orignlFileNm' }"> selected="selected" </c:if>>음원명</option>
											<option value="streFileNm" <c:if test="${searchVO.searchCondition == 'streFileNm' }"> selected="selected" </c:if>>파일명</option>
  									        <%-- <option value="streFileNm" <c:if test="${searchVO.searchCondition == 'streFileNm' }"> selected="selected" </c:if>>실파일명</option> --%>
										</select>
										<input type="text"  name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">
										<a href="javascript:search_form('/backoffice/sub/brodManage/brodContentPlayList.do')" class="yellowBtn">검색</a>	
									</div>	
									<!--테이블시작-->
									<table id="brodFileLst">
										<!--테이블상단-->
											<colgroup>
												<col style="width:60%" />
												<col style="width:20%" />
												<col style="width:20%" />
											</colgroup>
											<thead>
											<!--줄시작-->
											<tr>
												<th>음원명</th>
												<th>재생시간</th>
												<th>파일명</th>
											</tr>
										</thead>
										<!--테이블내용-->
										<tbody>
										    <c:forEach items="${resultList}" var="fileDetail" varStatus="status">
											<tr class="selectTable" id="${fileDetail.atchFileId}">
												<td style="text-align:left"><a href="javascript:fileCenterView('${fileDetail.atchFileId}')">${fileDetail.orignlFileNm}</a></td>
												<td><a href="javascript:fileCenterView('${fileDetail.atchFileId}')">${fileDetail.fileThumnail}</a></td>
												<td>${fileDetail.streFileNm}</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="pageFooter">
										<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />	
										<div class="clear"></div>	
									</div>								
								</div>								
							</div>
							<div class="beetCon groupTable">				
							</div>
							<div class="groupCon whiteBox groupTable">
								<div class="innerBox">							
									<div class="searchBox">
										<span>지점명 검색 : </span>
										<input type="text" name="centerNm" id="centerNm">
										<a href="javascript:rightSearch()" class="yellowBtn">검색</a>
										<div class="brandSelect" name="brandSelect" style="display: inline-block; margin: 0 8px 0 8px;">
											<span>브랜드 : </span>
											<select id="brand_select" onChange="javascript:brandChange()">
												<option value="brand_basic">일반매장</option>
												<option value="brand_electro">일렉트로마트</option>
											</select>
										</div>
                                        <a href='javascript:ContentProc(&#39;Del&#39;)' class='grayBtn' style="float:right; margin:0 4px 0 4px;">삭제</a>
			                            <a href='javascript:ContentProc(&#39;Ins&#39;)' class='grayBtn' style="float:right; margin:0 4px 0 4px;">등록</a>										
									</div>									
									<!--테이블시작-->
									<table id="rightInfo">
										<!--테이블상단-->
										<thead>
											<!--줄시작-->
											<tr>
												<th><input type="checkbox" name="checkAll" id="checkAll" onChange="javascript:allCheck();"></th>
												<th>지점명</th>
												<th>콘텐츠구분</th>
												<th>음원명</th>
												<th>재생위치</th>
												<th>시간</th>		
											</tr>
										</thead>
										<tbody>
										  
										</tbody>
									</table>
									<!-- 
									<div class="footerBox">
										<a href="javascript:schUpdate()" class="yellowBtn">업데이트</a>
									</div>
									 -->		
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
	</div>
 </form:form>
 <script type="text/javascript">
	 $(document).ready(function() {
		//시작시 넣을 파일
		clearTableColor();
	 });
	
	
 	function brandChange(){
		var selectBrandInfo = $("#brand_select").val();
		// chkBox_electro chkBox_bigten chkBox_basic
		switch(selectBrandInfo){
			case "brand_basic" :
				$(".chkBox_basic").attr("disabled", false);
				$(".chkBox_electro").attr("disabled", true);
				$(".chkBox_bigten").attr("disabled", true);
				break;
			case "brand_electro" : 
				$(".chkBox_electro").attr("disabled", false);
				$(".chkBox_basic").attr("disabled", true);
				$(".chkBox_bigten").attr("disabled", true);
				break;
			default :
				$(".chkBox_basic").attr("disabled", false);
				$(".chkBox_electro").attr("disabled", true);
				$(".chkBox_bigten").attr("disabled", true);
				break;
		}
	}

	
	 function chgTableColor(rowid,chgcolor){
		    $('#brodFileLst tr').css("background-color","");
		    
		    for(var i=0; i<$('#brodFileLst tr').length; i++) {
		        if(i==rowid) {
		            $('#brodFileLst tr:rowindex:eq('+i+')').css("background-color","yellow");
		        }
		    }
	 }
	 function clearTableColor(){
	    $('#brodFileLst tr').css("background-color","");
	 }		
	 //상세 검색
	 function rightSearch(){
		
		var params = $("form[name=regist]").serialize();
			$.ajax({
				url : '/backoffice/sub/brodManage/brodContentPlayListRightInfo.do',
				type : 'POST',
				data : params,
				dataType : 'json',
				success : function(result) {
					if (result) {
						print(result.contentList);
					}
				},
				error : function(e) {
					console.log("falit" + e);
				}
			});
		}
		function allCheck() {
			if ($("#checkAll").prop("checked")) {
//				$("input[type=checkbox]").prop("checked", true);
				
				$("input[type=checkbox]").not(":disabled").prop("checked", true);
				

			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		}
		function linkPage(pageNo) {
			$(":hidden[name=pageIndex]").val(pageNo);
			$("form[name=regist]").submit();
		}
		function fileCenterView(code) {
		    $('#brodFileLst tr').css("background-color","");
		    $("#"+code).css("background-color","#f7a900");
		    
			$("input[type=checkbox]").prop("checked", false);
			$("#atchFileId").val(code);
			$("#rightInfo").find('tbody').empty();

			apiExecute(
					"POST",
					"/backoffice/sub/brodManage/brodContentPlayListRightInfo.do",
					{
						atchFileId : code
					},
					null,
					function(result) {
						if (result.contentList != null) {
							//방송 정보 보여주기 	
							print(result.contentList);
							/* var timeHtml = "";
							for (var i = 0; i < result.contentList.length; i++) {
								var obj = result.contentList[i];
								timeHtml += "<tr style='width:100%'><td style='text-align:center'>";
								timeHtml += "<input type='checkbox' id='brodInfo' name='brodInfo' value='"+ obj.brodCode + "|"+obj.brodSeq +"|"+obj.tbGubun +"' >";
								timeHtml += "</td>";
								timeHtml += "<td style='text-align:center'>"
										+ obj.centerNm + "</td>"
								if (obj.intervalSection == null) {
									timeHtml += "<td style='text-align:center' colspan='4'>곡없음</td>";
								} else {
									if (obj.tbGubun == "D") {

										timeHtml += "<td style='text-align:center'>일반방송</td><td style='text-align:center'>"
												+ obj.orignlFileNm.substring(0,
														10)
												+ "</td><td style='text-align:center'>"
												+ obj.intervalSection
												+ "분</td><td style='text-align:center'>재생시간"
												+ obj.fileThumnail + "</td>";
									} else {
										var timeInfo = "";
										if (obj.intervalSection == "ANNGUBUN01") {
											timeInfo = (parseInt(obj.anniversaryTime) / 60)
													+ "시 마다"
													+ obj.anniversaryStartTime
													+ " 분 실행"
										} else {
											timeInfo = obj.anniversaryTime
													.substring(0, 2)
													+ ":"
													+ obj.anniversaryTime
															.substring(2, 4)
													+ "분 재생";
										}
										timeHtml += "<td style='text-align:center'>특정방송</td><td style='text-align:center'>"
												+ obj.orignlFileNm.substring(0,
														10)
												+ "</td><td style='text-align:center'>"
												+ timeInfo
												+ "</td><td style='text-align:center'>재생시간"
												+ obj.fileThumnail + "</td>";
									}

								}

								timeHtml += "</tr>";

							}
							timeHtml += "<tr><td colspan='6' style='text-align:right'>";
							timeHtml += " <a href='javascript:ContentProc(&#39;Del&#39;)' class='grayBtn'>삭제</a>";
							timeHtml += " <a href='javascript:ContentProc(&#39;Ins&#39;)' class='grayBtn'>등록</a>";
							timeHtml += " </td></tr>";
							$("#rightInfo").find('tbody').append(timeHtml); */
						}
					}, null, null);
		}
		// 수정 또는 삭제
		function ContentProc(code) {
			var cnt = $("input[name=brodInfo]:checkbox:checked").length;
			var del_atch = "";
			var insert_brodCode = "";
			if (cnt < 1) {
				alert("하나 이상의 체크를 선택 하셔야 합니다");
			} else {

				for (var i = 0; i < document.getElementsByName("brodInfo").length; i++) {
					if (document.getElementsByName("brodInfo")[i].checked == true) {

						if (document.getElementsByName("brodInfo")[i].value
								.indexOf("|") !== -1) {
							var strAaary = document
									.getElementsByName("brodInfo")[i].value
									.split('|');
							if (strAaary[1] != "null") {
								del_atch = del_atch + "," + strAaary[1] + "ㅣ"
										+ strAaary[2];

							}
							insert_brodCode = insert_brodCode + ","
									+ strAaary[0];

						}
					}
				}

				if (code == "Del" && del_atch != "") {
					var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
					$("#backgroundgif").html(loadingimg);
					apiExecute(
							"POST",
							"/backoffice/sub/brodManage/rightbrodContentDetailDel.do",
							{
								splitCode : del_atch,
								insert_brodCode : removeArrayDuplicate(insert_brodCode
										.substring(1).split(','))
							}, null, function(result) {
								if (result != null) {
									$("#backgroundgif").html("");
									if (result == "T") {
										alert("정삭적으로 삭제 되었습니다.");
										fileCenterView($("#atchFileId").val());
									} else {
										alert("삭제시 문제가 생겼습니다.");
										fileCenterView($("#atchFileId").val());
									}
								}
							}, null, null);
				} else if (code == "Ins") {
					if (insert_brodCode != "") {
						insert_brodCode = removeArrayDuplicate(insert_brodCode
								.substring(1).split(','));

						var url = "/backoffice/sub/brodManage/brodFileContentCopy.do?insert_brodCode="
								+ insert_brodCode
								+ "&atchFileId="
								+ $("#atchFileId").val();
						window
								.open(url, "contentRegFile",
										'width=800,height=550,top=100,left=650,scrollbars=auto');
					} else {
						alert("선택 하신 지점이 없습니다/")
					}

				}
			}
		}
		//중복 제거
		function removeArrayDuplicate(array) {
			var a = {};
			for (var i = 0; i < array.length; i++) {
				if (typeof a[array[i]] == "undefined")
					a[array[i]] = 1;
			}
			array.length = 0;
			for ( var i in a)
				array[array.length] = i;
			return array.toString();
		}
		function print(data){
			var timeHtml = "";
			$("#rightInfo").find('tbody').empty();
			for (var i = 0; i < data.length; i++) {
				var obj = data[i];
				if(obj.brodCode.substring(0,6) == "BROD_9" ){
					continue;
				}
				
				
				if(obj.centerNm.substr(0,6) == "[전문일렉]"){
					timeHtml += "<tr style='width:100%'><td style='text-align:center'>";
					timeHtml += "<input type='checkbox' id='brodInfo' name='brodInfo' class='chkBox_electro' disabled value='"+ obj.brodCode + "|"+obj.brodSeq +"|"+obj.tbGubun +"' >";
				}else if (obj.centerNm.substr(0,6) == "[전문빅텐]"){
					timeHtml += "<tr style='width:100%'><td style='text-align:center'>";
					timeHtml += "<input type='checkbox' id='brodInfo' name='brodInfo' class='chkBox_bigten' disabled value='"+ obj.brodCode + "|"+obj.brodSeq +"|"+obj.tbGubun +"' >";
				}else{
					timeHtml += "<tr style='width:100%'><td style='text-align:center'>";
					timeHtml += "<input type='checkbox' id='brodInfo' name='brodInfo' class='chkBox_basic' value='"+ obj.brodCode + "|"+obj.brodSeq +"|"+obj.tbGubun +"' >";
				}
				timeHtml += "</td>";
				timeHtml += "<td style='text-align:center'>"
						+ obj.centerNm + "</td>"
				if (obj.intervalSection == null) {
					timeHtml += "<td style='text-align:center' colspan='4'>곡없음</td>";
				} else {
					if (obj.tbGubun == "D") {

						timeHtml += "<td style='text-align:center'>일반방송</td><td style='text-align:center'>"
								+ obj.orignlFileNm.substring(0,
										10)
								+ "</td><td style='text-align:center'>"
								+ obj.intervalSection
								+ "분</td><td style='text-align:center'>재생시간"
								+ obj.fileThumnail + "</td>";
					} else {
						var timeInfo = "";
						if (obj.intervalSection == "ANNGUBUN01") {
							timeInfo = (parseInt(obj.anniversaryTime) / 60)
									+ "시 마다"
									+ obj.anniversaryStartTime
									+ " 분 실행"
						} else {
							timeInfo = obj.anniversaryTime
									.substring(0, 2)
									+ ":"
									+ obj.anniversaryTime
											.substring(2, 4)
									+ "분 재생";
						}
						timeHtml += "<td style='text-align:center'>특정방송</td><td style='text-align:center'>"
								+ obj.orignlFileNm.substring(0,
										10)
								+ "</td><td style='text-align:center'>"
								+ timeInfo
								+ "</td><td style='text-align:center'>재생시간"
								+ obj.fileThumnail + "</td>";
					}

				}

				timeHtml += "</tr>";

			}
			timeHtml += "<tr><td colspan='6' style='text-align:right'>";
			timeHtml += " <a href='javascript:ContentProc(&#39;Del&#39;)' class='grayBtn'>삭제</a>";
			timeHtml += " <a href='javascript:ContentProc(&#39;Ins&#39;)' class='grayBtn'>등록</a>";
			timeHtml += " </td></tr>";
			$("#rightInfo").find('tbody').append(timeHtml);
		}
	</script>
</body>
</html>