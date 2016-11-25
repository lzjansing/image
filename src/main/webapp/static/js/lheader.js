/*
 * header；
 */
/*
 * 还没有优化js代码；
 */

window.onload=function(){
	
	//搜索框部分；
	var searchInput=document.getElementById("searchInput");
	var placeHolder=document.getElementById("placeHolder");
	//信息设置部分；
	var massageConfg=document.getElementById("massageConfg");
	var userConfg=document.getElementById("userConfg");
	
	
	
	//搜索Input获得焦点时
	searchInput.onfocus=function(){
		
		var value=placeHolder.getElementsByTagName("b")[0].innerText;
		var searchDiv=document.getElementById("searchDiv");//包围搜索区的div
		searchDiv.style.border="1px solid #fa7d3c"
		placeHolder.style.display="none";
		this.value=value;
		
	}
	//失去焦点时
	searchInput.onblur=function(){
		
		var searchDiv=document.getElementById("searchDiv");//包围搜索区的div
		searchDiv.style.border="1px solid #999";
	}
	
	massageConfg.onmouseenter=function(){
		
		var massageConfgHd=document.getElementById("massageConfgHd");
		massageConfgHd.style.display="inline-block";
	}
	massageConfg.onmouseleave=function(){
		
		var massageConfgHd=document.getElementById("massageConfgHd");
		massageConfgHd.style.display="none";
	}
	
	userConfg.onmouseenter=function(){
		
		var userConfgHd=document.getElementById("userConfgHd");
		userConfgHd.style.display="inline-block";
	}
	userConfg.onmouseleave=function(){
		
		var userConfgHd=document.getElementById("userConfgHd");
		userConfgHd.style.display="none";
	}
	
}
