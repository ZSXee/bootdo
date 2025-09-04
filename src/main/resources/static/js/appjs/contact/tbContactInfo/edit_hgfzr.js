$().ready(function() {
	validateRule();
//	$("input").attr("disabled","disabled");
//已设置会签后面的流程无法继续设置会签
	if($("#countersign").val()=='on'){
		$("#cntReserve1_formgroup").show();
		$("#cntReserve2_formgroup").show();
	}else{
		$("#cntReserve1_formgroup").hide();
		$("#cntReserve2_formgroup").hide();
	}
	//选择不会签，自动隐藏会签部门
	$("#cntReserve2").change(function() {
		if($("#cntReserve2").prop("checked")){
			$("#cntReserve1_formgroup").show();
		}else{
			$("#cntReserve1_formgroup").hide();
		}});
});

$.validator.setDefaults({
	submitHandler : function() {
		commit();
	}
});
function commit() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/contact/tbContactInfo/addComment",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad1();
				parent.reLoad2();
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
				required : icon + "请输入名字"
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
	$("#cntReserve1").val(deptId);
	$("#deptName").val(deptName);
}
var openComment = function(){
	layer.open({
		type:2,
		title:"添加批注",
		area : [ '730px', '530px' ],
		content:'/contact/tbContactInfo/comment/'+$("#procInsId").val()
	})
}