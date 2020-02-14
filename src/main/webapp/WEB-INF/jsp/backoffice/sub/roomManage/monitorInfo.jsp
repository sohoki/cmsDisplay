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
    <title>회의실 정보 : 전문점CMS</title>
	<link href="<c:url value='/'/>css/layout.css" rel="stylesheet" type="text/css" >
	<link href="<c:url value='/'/>css/paragraph.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/popup.js"></script>
	<script type="text/javascript" src="/js/leftMenu.js"></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<div id="mhs_wrap">
	<c:import url="/backoffice/inc/mhs_header.do" />
	<div id="mhs_contents">
		<div class="mhs_con_top">
			<div class="mhs_con_top_left">
			<!-- 
				<ul>
					<li><a class="mhs_btn_reg">등록</a></li>
				</ul>
			 -->
			</div>
			<div class="mhs_con_top_right">
				<ul>
					<li><a class="mhs_btn_reg">모니터 재부팅</a></li>
					<li><a class="mhs_btn_reg">정보 수정</a></li>
					<li><a class="mhs_btn_reg">목록으로</a></li>
				</ul>
			<!-- 
				<ul>
					<li><select class="mhs_select"><option>조직전체</option><option>1</option><option>2</option><option>----------</option></select></li>
					<li><select class="mhs_select"><option>점포전체</option><option>1</option><option>2</option><option>----------</option></select></li>
					<li><select class="mhs_select"><option>상태전체</option><option>정상</option><option>불량</option><option>----------</option></select></li>
					<li><select class="mhs_select"><option>검색기준</option><option>단말명</option><option>단말ID</option><option>IP</option><option>MAC</option></select></li>
					<li><input class="mhs_input" type="text" /></li>
					<li><a class="mhs_btn_reg">조회</a></li>
				</ul>
			 -->
			</div>
		</div>
		<div class="mhs_con_middle">
			<table class="mhs_info_table">
				<thead><th colspan="4">모니터 정보</th></thead>
				<tbody>
					<tr><th>조직명</th><td>스타필드시티</td><th>점포명</th><td>옥길점</td></tr>
					<tr><th>단말정보</th><td>메인입구<br>(M19080701001)</td><th>네트워크정보</th><td>192.168.0.1<br>AA:BB:CC:DD:EE:FF</td></tr>
					<tr><th>모니터운영시간</th><td>10:00 ~ 23:00</td><th>최종통신일자</th><td>2019.08.08 15:45:41</td></tr>
					<tr><th>송출방식</th><td>단일강의</td><td colspan="2">여기에 이미지 표출</td></tr>
					<tr><th>비고</th><td colspan="3"></td></tr>
				</tbody>
			</table>
			<div class="mhs_middle_title">
				<h2>모니터 송출 상세정보</h2>
			</div>
			<div class="mhs_con_top_left">
				<ul>
					<li>※ 추가 된 강의를 삭제하려면 시간 블록을 클릭하세요.</li>
				</ul>
			</div>
			<div class="mhs_con_top_right">
				<ul>
					<li>조회일자&nbsp;<input class="mhs_input" type="text" /></li>
				</ul>
			</div>
			<table class="mhs_info_table mhs_viewtime_table">
				<thead>
					<tr><th rowspan="2">구분</th><th colspan="13">모니터 운영시간 ( 10:00 ~ 23:00 )</th></tr>
					<tr><th>10:00 ~</th><th>11:00 ~</th><th>12:00 ~</th><th>13:00 ~</th><th>14:00 ~</th><th>15:00 ~</th><th>16:00 ~</th><th>17:00 ~</th><th>18:00 ~</th><th>19:00 ~</th><th>20:00 ~</th><th>21:00 ~</th><th>22:00 ~</th></tr>
				</thead>
				<tbody>
					<tr><th colspan="14">강의 추가</th></tr>
					<tr><th>강의1</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의2</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의3</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의4</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의1</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의2</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의3</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의4</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의1</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의2</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의3</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의4</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의1</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의2</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의3</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의4</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의1</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의2</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의3</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th>강의4</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><th colspan="14">강의 추가</th></tr>
				</tbody>
			</table>
		</div>
		<div class="mhs_con_bottom">
		</div>
	</div>
</div>	
</body>
</html>		