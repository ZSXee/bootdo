$().ready(function() {
	validateRule();
	$("#cntReserve2").change(function() {
		if($("#cntReserve2").prop("checked")){
			$("#cntReserve_formgroup").show();
			$("#cntReserve2").val("on");
		}else{
			$("#cntReserve_formgroup").hide();
			$("#cntReserve2").val("off");
		}});
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/contact/tbContactInfo/save",
		//data : $('#signupForm').serialize(),
		data : $(':input').serialize(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad5();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "会签部门不能为空"
			}
		}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView2"
	})
}
function loadDept2( deptId,deptName){
	$("#site").val(deptName);
}

