<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div id="footer">
		<div class="conIn ">
			<span><img src="/img/emartLogo.png"></span>
			<address><span>서울특별시 성동구 뚝섬로 377(성수동2가)</span></address>
		</div>
	</div>
<script src="/js/nav.js"></script>

<script type="text/javascript">
       
	   /* $(document).ready(function() {    
		   if ($("#menuGubun").val() == "" ){
			   $("#menuGubun").val("Mnu");			   
		   }
		   
		   $("#menu_Gubun").val( $("#menuGubun").val() );
		   
		   apiExecute(
					"POST", 
					"/backoffice/inc/emart_treeMu.do",
					{
						menuGubun : $("#menuGubun").val()
					},
					null,
					function(result) {
						
						if (result.menuTree != null) {
							
							$("#treeMenu").html("");
												
							var lvtCnt = "0";
							var lvCode = "";
							didDetailHtm = ""
							for (var i=0; i<result.menuTree.length; i++) {
								var obj = result.menuTree[i];
								if (obj.lv == "1"){
									didDetailHtm += "<span> "+ obj.menuNm+"</span>";
									didDetailHtm += "<p>";
									didDetailHtm += "   <select name='menu_Gubun' id='menu_Gubun' onChange='javascript:view_menu()'>";
									didDetailHtm += "       <option value='Mnu'>지점별 Type</option>";
									didDetailHtm += "       <option value='TMnu'>기기 Type</option>	";		    		
									didDetailHtm += "   </select>";
									didDetailHtm += "</p>				";
									didDetailHtm += "<ul>";
								}else if (obj.lv == "2" && lvtCnt == "0" ){
									   if ($("#menuGubun").val()=="Mnu" && $("#centerId").val()== obj.centerId){
											didDetailHtm += "<li><a href='javascript:didList(&#39;"+ obj.centerId + "&#39;, &#39;"+ obj.menuGubun + "&#39;)'  class='active'>"+ obj.menuNm+"</a><ul>";
										}else{
											didDetailHtm += "<li><a href='javascript:didList(&#39;"+ obj.centerId + "&#39;, &#39;"+ obj.menuGubun + "&#39;)'>"+ obj.menuNm+"</a><ul>";
										} 
										lvtCnt= "1";
								}else if (obj.lv == "2" && lvtCnt == "1" ){
									if ($("#menuGubun").val()=="Mnu" && $("#centerId").val()== obj.centerId){
											didDetailHtm += "<li><a href='javascript:didList(&#39;"+ obj.centerId + "&#39;, &#39;"+ obj.menuGubun + "&#39;)'  class='active'>"+ obj.menuNm+"</a><ul>";
										}else{
											didDetailHtm += "<li><a href='javascript:didList(&#39;"+ obj.centerId + "&#39;, &#39;"+ obj.menuGubun + "&#39;)'>"+ obj.menuNm+"</a><ul>";
										} 
								        //didDetailHtm += "</ul></li><li><a href='javascript:didList(&#39;"+ obj.centerId + "&#39; , &#39;"+ obj.menuGubun + "&#39; )'>"+ obj.menuNm+"</a><ul>";
									    lvtCnt= "0";								    
							   }else  if (obj.lv == "3"){
								   if ($("#didId").val() == obj.didId){										    	   
							    	   didDetailHtm += "<li><a href='javascript:didView(&#39;"+ obj.didId + "&#39;,&#39;"+ obj.menuGubun +"&#39; )'  class='active'>&nbsp;&nbsp;"+ obj.menuNm+"</a></li>";
							       }else {										    	   
							    	   didDetailHtm += "<li><a href='javascript:didView(&#39;"+ obj.didId + "&#39;,&#39;"+ obj.menuGubun +"&#39; )'>&nbsp;&nbsp;"+ obj.menuNm+"</a></li>";   
							       }
								}					
						
								
							}
							didDetailHtm += "</ul>";
						    
							$("#treeMenu").html(didDetailHtm);	 
						
							
						}
					},
					null,
					null
				);
		});     */
	   function view_menu(){
		   
		           $("#menuGubun").val($("#menu_Gubun").val());
		   
		           apiExecute(
							"POST", 
							"/backoffice/inc/emart_treeMu.do",
							{
								menuGubun : $("#menuGubun").val(),
								authorCode :  $("#authorCode").val(),
								roleCode :  $("#roleCode").val(),
								mberId :  $("#mberId").val()
							},
							null,
							function(result) {
								
								if (result.menuTree != null) {
									
									$("#treeMenu").html("");
									
									var lvtCnt = "0";
									var lvCode = "";
									didDetailHtm = ""
									for (var i=0; i<result.menuTree.length; i++) {
										var obj = result.menuTree[i];
										
										if (obj.lv == "1"){											
											didDetailHtm += "<span>"+ obj.menuNm+"</span>";
											didDetailHtm += "<p>";
											didDetailHtm += "   <select name='menu_Gubun' id='menu_Gubun' onChange='javascript:view_menu()'>";
											didDetailHtm += "       <option value='Mnu'>지점별 Type</option>";
											didDetailHtm += "       <option value='TMnu'>기기 Type</option>	";		    		
											didDetailHtm += "   </select>";
											didDetailHtm += "</p>				";
											didDetailHtm += "<ul>";
											
										}else if (obj.lv == "2" && lvtCnt == "0" ){		
											   if ($("#menuGubun").val()=="Mnu" && $("#centerId").val()== obj.centerId){
												   didDetailHtm += "<li><a href='javascript:didList(&#39;"+ obj.centerId + "&#39;,&#39;"+ obj.menuGubun +"&#39;)' class='active'>"+ obj.menuNm+"</a><ul>";   
											   }else {
												   didDetailHtm += "<li><a href='javascript:didList(&#39;"+ obj.code + "&#39;,&#39;"+ obj.menuGubun +"&#39;)'>"+ obj.menuNm+"</a><ul>";
											   }
											    
												lvtCnt= "1";
											    
										}else if (obj.lv == "2" && lvtCnt == "1" ){
											if ($("#menuGubun").val()=="Mnu" && $("#centerId").val()== obj.centerId){
												didDetailHtm += "</ul></li><li><a href='javascript:didList(&#39;"+ obj.centerId + "&#39;,&#39;"+ obj.menuGubun +"&#39; )'  class='active'>"+ obj.menuNm+"</a><ul>";	
											}else {
												didDetailHtm += "</ul></li><li><a href='javascript:didList(&#39;"+ obj.code + "&#39;,&#39;"+ obj.menuGubun +"&#39; )'>"+ obj.menuNm+"</a><ul>";
											}
										    
											lvtCnt= "0";								    
									   }else  if (obj.lv == "3"){
										       if ($("#didId").val() == obj.didId){										    	   
										    	   didDetailHtm += "<li><a href='javascript:didView(&#39;"+ obj.didId + "&#39;,&#39;"+ obj.menuGubun +"&#39; )'  class='active'>&nbsp;&nbsp;"+ obj.menuNm+"</a></li>";
										       }else {										    	   
										    	   didDetailHtm += "<li><a href='javascript:didView(&#39;"+ obj.didId + "&#39;,&#39;"+ obj.menuGubun +"&#39; )'>&nbsp;&nbsp;"+ obj.menuNm+"</a></li>";   
										       }
											    
											    
										}								
									}
									didDetailHtm += "</ul>";
								    
									$("#treeMenu").html(didDetailHtm);
									
									
									
								}
							},
							null,
							null
						);						
		           setTimeout('$(".tree").treemenu({delay:1000}).openActive()', 500);	
	   }
	   function didList(code, code1){
		   if (code1 == "C"){
		       location.href="/backoffice/sub/equiManage/didList.do?centerId="+code+"&menuGubun=Mnu";
		   } else {
			   location.href="/backoffice/sub/equiManage/didList.do?code="+code+"&menuGubun=TMnu";			   
		   }
	   }
	   function didView(code, code1){
		   if (code1 == "C"){
		       location.href="/backoffice/sub/equiManage/didView.do?didId="+code+"&menuGubun=Mnu";
		   } else {
			   location.href="/backoffice/sub/equiManage/didView.do?didId="+code+"&menuGubun=TMnu";			   
		   }
		  
	   }
    </script>
    <input type="hidden" name="#menu_Gubun" id="#menu_Gubun" /> 
    <script src="/js/jquery.treemenu.js"></script>
    <script>
	$(function(){	        
	        setTimeout('$(".tree").treemenu({delay:1000}).openActive()', 500);			
	    });
    </script>	