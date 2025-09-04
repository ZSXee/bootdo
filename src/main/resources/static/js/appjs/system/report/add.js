$().ready(function() {
	validateRule();
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
		url : "/system/report/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#upload', //绑定元素
        url: '/file/tbContactFiles/upload', //上传接口
        size: 5000,
        accept: 'file',
        before: function(obj){
        	//携带额外的数据,type=1合同附件
            this.data.params={
            		"cntId": $("#cntId").val(),
            		"type": "1"
            	};
            this.data.params=JSON.stringify(this.data.params);
        },
        done: function (r) {
            layer.msg(r.msg);
            $('#filelist_add').bootstrapTable('refresh');
        },
        error: function (r) {
            layer.msg(r.msg);
            $('#filelist_add').bootstrapTable('refresh');
        }
    });
});

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
				required : icon + "请输入姓名"
			}
		}
	})
}