function selectTime(element, defaultValue, defaultText, existValue){
	var newOptions = {"01:00": "01:00","02:00": "02:00","03:00": "03:00","04:00": "04:00","05:00": "05:00","06:00": "06:00",
			"07:00": "07:00","08:00": "08:00","09:00": "09:00","10:00": "10:00","11:00": "11:00","12:00": "12:00","13:00": "13:00","14:00": "14:00",
			"15:00": "15:00","16:00": "16:00","17:00": "17:00","18:00": "18:00","19:00": "19:00","20:00": "20:00","21:00": "21:00","22:00": "22:00","23:00": "23:00","24:00":"24:00"
	};
	var e1 = element;
	e1.empty(); // remove old options
	e1.append($("<option></option>").attr("value", defaultValue).text(defaultText));
	$.each(newOptions, function(key,value) {
		if(value == existValue){
			e1.append($("<option></option>").attr("value", value).attr("selected", "selected").text(key));
		} else if(value == "24:00" && existValue == "00:00") {
			e1.append($("<option></option>").attr("value", value).attr("selected", "selected").text(key));
		} else {
			e1.append($("<option></option>").attr("value", value).text(key));
		}
	  
	});
}

	function grp_search(element, searchValue, require){
		// require ? 1 : require, 2 : not require

		$.ajax({
			url: "/backoffice/sub/equiManage/groupSearch.do",
			async : false,
			type: "POST",
			data : {
				"grpSearchKeyword" : searchValue
			},
			success: function(result){
				if (result != null) {
					if(result.resultMap != null){
						
						element.empty();
						if(require == 2){
							element.append("<option value=''>선택안함</option>");
						}
						for(var i = 0; i < result.resultMap.length; i++){
							var obj = result.resultMap[i];
							var groupName = obj.groupNm;
							if (obj.groupNm == null){
								groupName = "";
							} else {
								groupName = obj.groupNm;
							}
							
							
							$("<option value='"+ obj.groupCode +"' selected>"+ groupName +"</option>").appendTo(element);
							
							
						}
					}
				} else {
					alert("조회에 실패하였습니다.");
				}	
			}
		});

	}
	
	function open_grpReg(){
		alert("그룹을 등록 후 '검색'시 신규 등록한 그룹이 표출 됩니다.");
    	window.open("/backoffice/sub/equiManage/did_groupList.do", "_blank");
	}
	
	function preview_group(code, gNm){
		var didDetailHtm = "";
  	
		apiExecute(
			"POST", 
			"/backoffice/sub/equiManage/DidgroupLst.do",
			{
				groupCode : code
			},
			null,				
			function(result) {
				$("#spTitle").html("<a onclick='view_connPage(&#39;g&#39;,&#39;"+code+"&#39;)' style='cursor:pointer; color:#fff;'>["+gNm+"]</a>" + " 그룹 단말 리스트");
				$("#subSpTitle").html("* 그룹 단말 클릭시 단말 정보 확인이 가능합니다.");
				didDetailHtm = "<tbody>";
				if (result != null) {
					if (result.didLst != null) {
						for (var i=0; i<result.didLst.length; i++) {
							var obj = result.didLst[i];
							didDetailHtm += "<tr><td>"+ (i+1)  +"</td><td><a style='color:#fff; cursor:pointer;' onclick='view_connPage(&#39;d&#39;,&#39;"+obj.didId+"&#39;)'>"+ obj.didNm  +"</a></td></tr>";	     									
						}	  
					}    
					didDetailHtm += "</tbody>";
					$("#basicList").html(didDetailHtm);
				}
			},
			function(request){
						
			},
			null
		);   
	}

    function view_connPage(req, seq){
    	var reqLink = "";
    	switch(req){
    		case "d"  : reqLink = "/backoffice/sub/equiManage/didView.do?didId="+seq; 		   break;
    		case "c"  : reqLink = "/backoffice/sub/conManage/conMutiView_back.do?conSeq="+seq; break;
    		case "s"  : reqLink = "/backoffice/sub/equiManage/schView.do?schSeq="+seq;		   break;    							   
    		case "mc" : reqLink = "/backoffice/sub/conManage/conMutiDetail.do?mode=Ins&conSeq="+0; 	   break;
    		case "ms" : reqLink = "/backoffice/sub/equiManage/schDetail.do?mode=Ins&schCode="+0;		   break;
    		case "g"  : reqLink = "/backoffice/sub/equiManage/did_groupList.do?searchCondition=GROUP_ID&searchKeyword="+seq; break;
    	}
    	window.open(reqLink, "_blank");
    }

    
    function center_search(element, searchValue){

		$.ajax({
			url: "/backoffice/sub/equiManage/centerSearch.do",
			async : false,
			type: "POST",
			data : {
				"cenSearchKeyword" : searchValue
			},
			success: function(result){
				if (result != null) {
					if(result.resultMap != null){
						
						element.empty();
						 
						for(var i = 0; i < result.resultMap.length; i++){
							var obj = result.resultMap[i];
							
							var centerNm = obj.centerNm;
							if (centerNm == null){
								centerNm = "";
							}
							$("<option value='"+ obj.centerId +"'>"+ centerNm +"</option>").appendTo(element);
						}
					}
				} else {
					alert("조회에 실패하였습니다.");
				}	
			}
		});

	}