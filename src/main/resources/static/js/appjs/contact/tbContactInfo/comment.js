$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		commit();
	}
});

function commit() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var d = {};
	var t = $('form').serializeArray();
    $.each(t, function() {
        d[this.name] = this.value;
      });
    parent.$('#taskComment').text(JSON.stringify(d));
    parent.$('#taskComment_tmp').text(d.taskComment);
    parent.layer.close(index);
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#comment").validate({
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
