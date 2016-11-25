

window.onload=function(){
	
	
	var flag=true;
	//搜索框部分；
	var searchInput=document.getElementById("searchInput");
	var placeHolder=document.getElementById("placeHolder");
	//信息设置部分；
	var massageConfg=document.getElementById("massageConfg");
	var userConfg=document.getElementById("userConfg");
	//分享发布；
	var publishInput = document.getElementById('publishInput');
	var Tip = document.getElementsByClassName("numTips")[0];
	var numTips=Tip.getElementsByTagName("span")[0];
	var num = parseInt(numTips.textContent);
	var publicOrPrv=document.getElementById("publicOrPrv");
	
	
	
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
	if(massageConfg!=null) {
		massageConfg.onmouseenter = function () {

			var massageConfgHd = document.getElementById("massageConfgHd");
			massageConfgHd.style.display = "inline-block";
		}
		massageConfg.onmouseleave = function () {

			var massageConfgHd = document.getElementById("massageConfgHd");
			massageConfgHd.style.display = "none";
		}
	}
	if(userConfg!=null) {
		userConfg.onmouseenter = function () {

			var userConfgHd = document.getElementById("userConfgHd");
			userConfgHd.style.display = "inline-block";
		}
		userConfg.onmouseleave = function () {

			var userConfgHd = document.getElementById("userConfgHd");
			userConfgHd.style.display = "none";
		}
	}
	
	//分享发布；
	publishInput.onkeyup=function(){
		
		var stringTxt=this.value;
		
		if(stringTxt.length<num){
			
			numTips.innerText = num-stringTxt.length;
		}
		else{
			
			numTips.innerText = 0;
			this.value=stringTxt.substr(0,num-1);
		}
	}
	
	//发布区的公开与私有的
	publicOrPrv.onclick=function(){
		
		
		var span=this.getElementsByTagName("span")[0];
		var ul= document.getElementById("pulishPublicOrPrv");
		var lis=ul.getElementsByTagName("li");

		if(flag==true){

			ul.style.display="inline-block";
			for (var i=0;i<lis.length;i++) {

				lis[i].onclick=function(){

					span.innerText=this.innerText;
					ul.style.display="none";
				}
			}
			flag=false;
		}
		else{
			
			ul.style.display="none";
			flag=true;
		}
		
	}
	//发布区图片上传
	var upload = document.getElementById("upload");
	var uploadDiv=document.getElementById("uploadDiv");
	var confirmBtn=document.getElementById("confirmBtn");
	upload.flag=true;
	upload.onclick=function(){
		
		if(this.flag==true){
			
			uploadDiv.style.display="inline-block";
			this.flag=false;
		}
		else{
			
			uploadDiv.style.display="none";
			this.flag=true;
		}
	}
	// confirmBtn.onclick=function(){
	//
	// 	uploadDiv.style.display="none";
	// }
	
	//每一条分享消息的公开或隐私的再次设置；
	// var Prv=document.getElementsByClassName("publicOrPrv");
	//
	// for(var i=0;i<Prv.length;i++){
	//
	//
	// 	Prv[i].flagY=true;
	// 	Prv[i].onclick=function(){
	//
	// 		//若ul已显示，再点击该Prv则隐藏ul；
	// 		var ul=this.getElementsByTagName("ul")[0];
	// 		var that=this;
	// 		if(this.flagY==true){
	//
	// 			ul.style.display="inline-block";
	// 			var lis=ul.getElementsByTagName("li");
	// 			for(var j=0;j<lis.length;j++){
	//
	// 				if(j==0){
	// 					lis[j].onclick=function(){
	//
	// 						that.style.background="url(../static/icon/litter.png) no-repeat";
	// 					}
	// 				}else{
	//
	// 					lis[j].onclick=function(){
	//
	// 						that.style.background="url(../static/icon/locked.bmp) no-repeat";
	// 					}
	// 				}
	//
	// 			}
	//
	// 			this.flagY=false
	// 		}
	// 		else{
	// 			ul.style.display="none";
	// 			this.flagY=true;
	// 		}
	// 	}
	// 	Prv[i].onmouseenter=function(){
	//
	// 		var val=this.className+" publicOrPrvHover";
	// 		this.setAttribute("class",val);
	// 	}
	// 	Prv[i].onmouseout=function(){
	//
	// 		var val=this.className.replace(" publicOrPrvHover","");
	// 		this.setAttribute("class",val);
	// 	}
	// }
	//点赞效果；
	// var collection3=document.getElementsByClassName("collection3");
	// for(var i=0;i<collection3.length;i++){
	//
	// 	var it=collection3[i];
	// 	it.flag=true;
	// 	it.onclick=function(){
	//
	// 		var text=this.innerText;
	// 		if(this.flag==true){
	//
	// 			this.style.background="url(../static/icon/yizan.bmp) no-repeat";
	// 			if(text=="点赞"){
	//
	// 				this.innerText="1";
	// 			}
	// 			else {
	//
	// 				this.innerText=parseInt(text)+1;
	// 			}
	//
	// 			this.flag=false;
	// 		}
	// 		else{
	//
	// 			this.style.background="url(../static/icon/weizan.bmp) no-repeat";
	// 			if(parseInt(text)==1){
	//
	// 				this.innerText="点赞";
	// 			}
	// 			else{
	//
	// 				this.innerText=parseInt(text)-1;
	// 			}
	// 			this.flag=true;
	//
	// 		}
	// 	}
	// }
	
	
	//评论区
	
	var showPnFuncs=document.getElementsByClassName("showPnFuncs");
	
	for(var i=0;i<showPnFuncs.length;i++){
		
		var collection2=showPnFuncs[i].getElementsByClassName("collection2")[0];
		var comments=showPnFuncs[i].getElementsByClassName("comments")[0];
		collection2.tagNext=comments;
		collection2.flag=true;
		collection2.onclick=function(){
			
			if(this.flag && this.tagNext){
				
				this.style.fontWeight="bold";
				this.tagNext.style.display="block";
				this.flag=false;
			}
			else if(!this.flag && this.tagNext){
				
				this.style.fontWeight="normal";
				this.tagNext.style.display="none";
				this.flag=true;
			}
		}
				
	}
	//评论输入
	var commentsInput=document.getElementsByClassName("commentsInput");
	
	for(var i=0;i<commentsInput.length;i++){
		
		var textarea=commentsInput[i].getElementsByTagName("textarea")[0];
		var tip=commentsInput[i].getElementsByClassName("commentsTip")[0];
		var span=tip.getElementsByTagName("span")[0];
		
		textarea.span=span;
		textarea.num=100;
		
		textarea.onclick=function(){
			
			var stringTxt=this.value;
			if(stringTxt=="评论"){
				
				this.value="";
			}
		}
		textarea.onkeyup=function(){

			var stringTxt=this.value;

			// if(stringTxt.length<this.num){

				this.span.innerText = this.num-stringTxt.length;
			// }
			// else{
			//
			// 	this.span.innerText = 0;
			// 	this.value=stringTxt.substr(0,this.num-1);
			// }
		}
		
	}
	//收藏效果
	// var collection1=document.getElementsByClassName("collection1");
	// for(var i=0;i<collection1.length;i++){
	//
	// 	collection1[i].flag=true;
	// 	collection1[i].onclick=function(){
	//
	// 		if(this.flag){
	//
	// 			this.style.background="url(../static/icon/collected.bmp) no-repeat";
	// 			this.innerText="已收藏";
	// 			this.flag=false;
	// 		}
	// 		else{
	//
	// 			this.style.background="url(../static/icon/uncollected.bmp) no-repeat 2px -2px";
	// 			this.innerText="收藏";
	// 			this.flag=true;
	// 		}
	// 	}
	// }
	
	
	//回复
	var third=document.getElementsByClassName("third");
	
	for(var i=0;i<third.length;i++){
		
		var response=third[i].getElementsByClassName("response")[0];
		var responseDiv=third[i].getElementsByClassName("responseDiv")[0];
		if(responseDiv!=null) {
			//回复按钮
			var responseFun = responseDiv.getElementsByClassName("responseFun")[0];
			var responsePublish = responseFun.getElementsByClassName("responsePublish")[0];
			responsePublish.responseDiv = responseDiv;
			responsePublish.response = response;

			response.flag = true;
			response.responseDiv = responseDiv;
			response.onclick = function () {

				if (this.flag && this.responseDiv) {

					this.style.fontWeight = "bold";
					this.responseDiv.style.display = "block";
					this.flag = false;
				}
				else if (!this.flag && this.responseDiv) {

					this.style.fontWeight = "normal";
					this.responseDiv.style.display = "none";
					this.flag = true;
				}

			}
			responsePublish.onclick = function () {

				this.response.style.fontWeight = "normal";
				this.responseDiv.style.display = "none";
				this.response.flag = true;
			}
		}
			
	}
	//回复输入
	var responseInput=document.getElementsByClassName("responseInput");
	
	for(var i=0;i<responseInput.length;i++){
		
		var textarea=responseInput[i].getElementsByTagName("textarea")[0];
		var tip=responseInput[i].getElementsByClassName("responseTip")[0];
		var span=tip.getElementsByTagName("span")[0];
		
		
		textarea.span=span;
		textarea.num=100;
		
		
		textarea.onkeyup=function(){
			
			var stringTxt=this.value;
			
			if(stringTxt.length<this.num){
				
				this.span.innerText = this.num-stringTxt.length;
			}
			else{
				
				this.span.innerText = 0;
				this.value=stringTxt.substr(0,this.num-1);
			}
		}
		
	} 
	

}
