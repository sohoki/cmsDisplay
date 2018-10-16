
	function menuOpen(menu){
		switch(menu){
			case "dash" 	:
				if($(".leftMenu_dash").is(":visible")){
					menuHidden($(".leftMenuImg_dash"), $(".leftMenu_dash"));
				}else{
					menuVisible($(".leftMenuImg_dash"), $(".leftMenu_dash"));
				}
				break;
			case "device" 	:
				if($(".leftMenu_device").is(":visible")){
					menuHidden($(".leftMenuImg_device"), $(".leftMenu_device"));
				}else{
					menuVisible($(".leftMenuImg_device"), $(".leftMenu_device"));
				}
				break;
			case "content" 	:
				if($(".leftMenu_content").is(":visible")){
					menuHidden($(".leftMenuImg_content"), $(".leftMenu_content"));
				}else{
					menuVisible($(".leftMenuImg_content"), $(".leftMenu_content"));
				}
				break;
			case "music" 	:
				if($(".leftMenu_music").is(":visible")){
					menuHidden($(".leftMenuImg_music"), $(".leftMenu_music"));
				}else{
					menuVisible($(".leftMenuImg_music"), $(".leftMenu_music"));
				}
				break;
			case "network" 	:
				if($(".leftMenu_send").is(":visible")){
					menuHidden($(".leftMenuImg_network"), $(".leftMenu_send"));
				}else{
					menuVisible($(".leftMenuImg_network"), $(".leftMenu_send"));
				}
				break;
		}
	}	
	function menuVisible(imgElement, element){
		$(".leftMenuImg img").attr("src", "/img/list_open_icon.png");
		$(".leftChildMenu").attr("style", "visibility:hidden");
		$(".leftChildMenu").hide();
		
		
		imgElement.attr("src", "/img/list_fold_icon.png");
		element.attr("style", "visibility:visible");
		element.show();
	}
	function menuHidden(imgElement, element){
		console.log(element.is(":visible"));
		imgElement.attr("src", "/img/list_open_icon.png");
		element.attr("style", "visibility:hidden");
		element.hide();
	}

$(window).load(function(){
	$(".leftChildMenu").hide();

$("[data-toggle]").click(function() {
  var toggle_el = $(this).data("toggle");
  $(toggle_el).toggleClass("open-sidebar");
});
 $(".swipe-area").swipe({
	  swipeStatus:function(event, phase, direction, distance, duration, fingers)
		  {
			  if (phase=="move" && direction =="right") {
				   $(".left-contain").addClass("open-sidebar");
				   return false;
			  }
			  if (phase=="move" && direction =="left") {
				   $(".left-contain").removeClass("open-sidebar");
				   return false;
			  }
		  }
  }); 
});