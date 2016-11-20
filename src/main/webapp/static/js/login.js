$(function() {
			$("#name").focus(function() {
		/*		var text_value = $(this).val();
				if (text_value == "邮箱/会员帐号/手机号") {
					$(this).val("");*/
					$("#xing").addClass("jsXing");
//				}
			});
			$("#name").blur(function() {
//				var text_value = $(this).val();
//				if (text_value == "") {
//					$(this).val("邮箱/会员帐号/手机号");
					$("#xing").removeClass("jsXing");
//				}
			});
			$("#password").focus(function() {
//				var text_value = $(this).val();
//				if (text_value == "请输入密码") {
//					$(this).val("");
					$("#mima").addClass("jsXing");
//				}
			});
			$("#password").blur(function() {
				
//				var text_value = $(this).val();
//				if (text_value == "") {
//					$(this).val("请输入密码");
					$("#mima").removeClass("jsXing");
//				}
			});
			$("#other").click(function(){
				$("#hide").toggle();
			});
	})