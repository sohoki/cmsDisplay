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
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<style>
#dragandrophandler{
	border:2px dotted #555555;
	width:400px;
	height:384px;
	line-height:112px;
	color:#555555;
	text-align:center;
	vertical-align:middle;
	font-size:16px;
	font-weight: bold;
	margin:32px;
}
.statusbar{
    border-top:1px solid #A9CCD1;
    min-height:25px;
    width:460px;
    padding:12px;
}
.filename{
	display:inline-block;
	width:256px;
	font-weight: bold;
}
.filesize{
	display:inline-block;
	color:#30693D;
	width:96px;
	margin-left:8px;
	margin-right:4px;
}
.abort{
    background-color:#A8352F;
    -moz-border-radius:4px;
    -webkit-border-radius:4px;
    border-radius:4px;display:inline-block;
    color:#fff;
    font-family:arial;
    font-size:12px;
    font-weight:normal;
    padding:2px 6px;
    cursor:pointer;
    float:right;
}
.progressBar {
    width: 460px;
    height: 4px;
    border: 1px solid #ddd;
    border-radius: 5px; 
    overflow: hidden;
    display:inline-block;
    margin:8px 0px;
}
.progressBar div {
	width: 460px;
    height: 100%;
    overflow: hidden;
    background-color: #0ba1b5; 
    border-radius: 3px; 
}
.progressValue{

}
.uploadTitle{
	padding: 16px 16px;
    border-bottom: solid 2px #B3B3B3;
    margin: 0px 8px;
    text-align: center;
}
.uploadTitle span{
	font-size: 14px;
    font-weight: bold;
}
.pageCloseBtn{
    float: right;
    background-color:#FFFFFF;
    color: #DC5353;
    font-size: 12px;
    border: solid 1px #EF3C3C;
    padding: 2px 10px;
    cursor:pointer;
}
.pageCloseBtn:hover{
	background-color:#EF3C3C;
	color:#FFFFFF;
}
.leftUpload{
    position : fixed;
    width: 500px;
    height: 416px;
    margin-left: 8px;
    text-align: center;
}
.rightUpload{
    position : fixed;
    left: 516px;
    width: 500px;
    height: 416px;
    margin-right: 8px;
}
#dragandrophandler img{
	width:128px;
	opacity:0.7;
	margin-top:32px;
}
#uploadStatus{
	margin: 32px 0px;
	height: 386px;
	overflow-y: auto;
}
</style>
<script type="text/javascript">
	function sendFileToServer(formData,status)
	{
		var conSeq = $("#conSeq").val();
		var detailSeq = $("#detailSeq").val()
	    var uploadURL ="/backoffice/sub/conManage/fileUpload.do?conSeq="+conSeq+"&detailSeq="+detailSeq; //Upload URL
	    var extraData ={}; //Extra Data.
	    var jqXHR=$.ajax({
	            xhr: function() {
	            var xhrobj = $.ajaxSettings.xhr();
	            if (xhrobj.upload) {
	                    xhrobj.upload.addEventListener('progress', function(event) {
	                        var percent = 0;
	                        var position = event.loaded || event.position;
	                        var total = event.total;
	                        if (event.lengthComputable) {
	                            percent = Math.ceil(position / total * 100);
	                        }                        
	                        status.setProgress(percent);
	                    }, false);
	                }
	            return xhrobj;
	        },
	    url: uploadURL,
	    type: "POST",
	    contentType:false,
	    processData: false,
	        cache: false,
	        data: formData,
	        success: function(data){
	            status.setProgress(100); 
	            console.log(status);
	            console.log(status.progressBar[0].innerHTML);
	            
	            status.progressBar[0].innerHTML = '<div style="width: 460px; overflow: hidden; background-color: #f7a900;"></div>';
	            status.progressValue[0].textContent = '100%';
	            status.abort.hide();
	            
	            
	            $(".pageCloseBtnStr").html("완료");
	        }
	    });     
	    status.setAbort(jqXHR);	
	}
	 
	var rowCount=0;
	function createStatusbar(obj)
	{
	     rowCount++;
	     var row="odd";
	     if(rowCount %2 ==0) row ="even";
	     this.statusbar = $("<div class='statusbar "+row+"'></div>");
	     this.filename = $("<div class='filename'></div>").appendTo(this.statusbar);
	     this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);
	     this.abort = $("<div class='abort'>X</div>").appendTo(this.statusbar);
	     this.statusbar.append("<br>");
	     this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);
	     this.statusbar.append("<br>");
	     this.progressValue =$("<div class='progressValue'>대기 중 입니다.</div>").appendTo(this.statusbar);
	     obj.append(this.statusbar);
	 
	    this.setFileNameSize = function(name,size)
	    {
	        var sizeStr="";
	        var sizeKB = size/1024;
	        if(parseInt(sizeKB) > 1024)
	        {
	            var sizeMB = sizeKB/1024;
	            sizeStr = sizeMB.toFixed(2)+" MB";
	        }
	        else
	        {
	            sizeStr = sizeKB.toFixed(2)+" KB";
	        }
	 
	        this.filename.html(name);
	        this.size.html(sizeStr);
	    }
	    this.setProgress = function(progress)
	    {       
	        
	        // this.progressBar.find('div').animate({ width: progressBarWidth }, 10).html(progress + "% ");
	        if(parseInt(progress) < 100){
	        	var progressBarWidth =progress*this.progressBar.width()/ 100;  
	        	this.progressBar.find('div').animate({ width: progressBarWidth }, 10);
	        	this.progressValue.text(progress + "% ");
	        }else if(parseInt(progress) >= 100){
	        	var progressBarWidth =99*this.progressBar.width()/ 100;  
	        	this.progressBar.find('div').animate({ width: progressBarWidth }, 10);
	        	this.progressValue.text(99 + "% ");
	        	// this.abort.hide();
	        }
	    }
	    this.setAbort = function(jqxhr)
	    {
	        var sb = this.statusbar;
	        this.abort.click(function()
	        {
	            jqxhr.abort();
	            sb.hide();
	        });
	    }
	}
	function handleFileUpload(files,obj)
	{
	   for (var i = 0; i < files.length; i++) 
	   {
	        var fd = new FormData();
	        fd.append('file', files[i]);
	 
	        var status = new createStatusbar(obj); //Using this we can set progress.
	        status.setFileNameSize(files[i].name,files[i].size);
	       	//console.log("name : " + files[i].name + "size : "  + files[i].size);
	       	validation(files[i]);
	        sendFileToServer(fd,status);
	 
	   }
	   // $("#status1").append("<a href='javascript:pageClose()'>[닫기]</a>");
	}
	function validation(obj){
		console.log("obj : " + obj.name);
		console.log("filename : " + $('.filename').text());
	}
	$(document).ready(function(){
		/* file upload _ drag & drop */	
		var obj = $("#dragandrophandler");
		obj.on('dragenter', function(e){
	    	e.stopPropagation();
	    	e.preventDefault();
	    	$(this).css('border', '2px solid #555555');
		});
		obj.on('dragover', function(e){
			e.stopPropagation();
			e.preventDefault();
		});
		obj.on('drop', function(e){ 
			$(this).css('border', '2px dotted #555555');
			e.preventDefault();
			var files = e.originalEvent.dataTransfer.files;
	 
			//We need to send dropped files to Server
			var createBar = $("#uploadStatus");
			handleFileUpload(files,createBar);
		});
		$(document).on('dragenter', function(e){
			e.stopPropagation();
	    	e.preventDefault();
		});
		$(document).on('dragover', function(e){
			e.stopPropagation();
			e.preventDefault();
			obj.css('border', '2px dotted #555555');
		});
		$(document).on('drop', function(e){
	    	e.stopPropagation();
	    	e.preventDefault();
		});
	});

	//페이지 닫기 
	function pageClose(){
		//window.opener.FileLst();
		if (confirm("콘텐츠 업로드를 끝내시겠습니까?\n완료되지 않은 콘텐츠는 등록되지 않습니다.")== true){
			opener.document.location.reload();
			self.close();		
		}
	}
</script>
</head>
<body class="scrollStyle1" style="background:#FFF;">
	<div class="wrap">
        <div class="uploadTitle">
            <span>File Upload</span>
            <div class="pageCloseBtn" onclick="pageClose();">
    			<span class="pageCloseBtnStr">닫기</span>
			</div>
        </div>
        <div class="leftUpload">
			<div id="dragandrophandler">
				<img src="/img/cloud_upload.png"><br>
    			<span>업로드할 파일을 마우스로 끌어놓으세요.</span>
			</div>
    		<!-- <a class="blueBtn">파일 첨부하기</a> -->
        </div>
        <div class="rightUpload">
			<div id="status1"></div>
			<div id="uploadStatus" class="scrollStyle1"></div>     	
        </div>
    </div>
</body>
</html>		