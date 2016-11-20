$(function() {
	$(".for-center-header>ul>li").hover(function() {
		if (!$(this).find("#setSlide").is(":animated")) {
			$(this).find("#setSlide").fadeIn("fast");
		}
	}, function() {
		$(this).find("#setSlide").fadeOut("fast");
	})
})
window.onscroll=function() {
	var h = document.documentElement.scrollTop;  
	if (h>0) {
		var b = document.getElementById("backTop");
		b.style.position = "fixed";
		b.style.left = 1020 + (document.body.offsetWidth - 1020) / 2 + "px";
		b.style.bottom = 40 + "px";
		$("#backTop").fadeIn("fast");
	}else{
		$("#backTop").fadeOut("fast");
	}
}