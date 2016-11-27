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
		
		
		var searchDiv=document.getElementById("searchDiv");//包围搜索区的div
		searchDiv.style.border="1px solid #fa7d3c"
		
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
		massageConfg.onmouseleave=function(){

			var massageConfgHd=document.getElementById("massageConfgHd");
			massageConfgHd.style.display="none";
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



	//评论区

	var showPnFuncs=document.getElementsByClassName("showPnFuncs");
	if(showPnFuncs!=null) {
		for (var i = 0; i < showPnFuncs.length; i++) {

			var collection2 = showPnFuncs[i].getElementsByClassName("collection2")[0];
			var comments = showPnFuncs[i].getElementsByClassName("comments")[0];
			collection2.tagNext = comments;
			collection2.flag = true;
			collection2.onclick = function () {

				if (this.flag && this.tagNext) {

					this.style.fontWeight = "bold";
					this.tagNext.style.display = "block";
					this.flag = false;
				}
				else if (!this.flag && this.tagNext) {

					this.style.fontWeight = "normal";
					this.tagNext.style.display = "none";
					this.flag = true;
				}
			}

		}
		//评论输入
		var commentsInput = document.getElementsByClassName("commentsInput");

		for (var i = 0; i < commentsInput.length; i++) {

			var textarea = commentsInput[i].getElementsByTagName("textarea")[0];
			var tip = commentsInput[i].getElementsByClassName("commentsTip")[0];
			var span = tip.getElementsByTagName("span")[0];

			textarea.span = span;
			textarea.num = 100;

			textarea.onclick = function () {
				var stringTxt = this.value;
				if (stringTxt == "评论") {
					this.value = "";
				}
			}
			textarea.onkeyup = function () {
				this.span.innerText = this.num - this.value.length;
			}
		}


		//回复
		var third = document.getElementsByClassName("third");

		for (var i = 0; i < third.length; i++) {

			var response = third[i].getElementsByClassName("response")[0];
			var responseDiv = third[i].getElementsByClassName("responseDiv")[0];
			if (responseDiv != null) {
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
		var responseInput = document.getElementsByClassName("responseInput");
		for (var i = 0; i < responseInput.length; i++) {
			var textarea = responseInput[i].getElementsByTagName("textarea")[0];
			var tip = responseInput[i].getElementsByClassName("responseTip")[0];
			var span = tip.getElementsByTagName("span")[0];
			textarea.span = span;
			textarea.num = 100;
			textarea.onkeyup = function () {
				var stringTxt = this.value;
				if (stringTxt.length < this.num) {
					this.span.innerText = this.num - stringTxt.length;
				}
				else {
					this.span.innerText = 0;
					this.value = stringTxt.substr(0, this.num - 1);
				}
			}
		}
	}
}
