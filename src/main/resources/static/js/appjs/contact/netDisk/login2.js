var login = "http://127.0.0.1:188/app/user/login"
var sync = "http://127.0.0.1:188/app/user/sync"
$(function() {
	load();
});
function load() {
	var data1 = {
		username : 'admin',
		password : '123456',
		version : '9999',
		system : 'web'
	}
	$.post(login, data1, function(result) {
		if (0 != result.code) {
			alert("登录失败");
		} else {
			sessionStorage.setItem("token", result.data.token);
			sessionStorage.setItem("name", result.data.name); 
			sessionStorage.setItem("realname", result.data.realname);
			var data2 = {
					token : result.data.token
				};
			$.post(sync, data2, function(result) {
				if (0 != result.code) {
					alert("登录失败");
				} else {
					window.location.href = "http://127.0.0.1:188/webapp/#/";
				}
			});
		}
	});

}
