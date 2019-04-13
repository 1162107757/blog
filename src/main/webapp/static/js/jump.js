$(".sidebar-scroll a").click(function(){
	window.location.href="index?loginCode=admin&pageCode=" + $(this).attr("id");
});