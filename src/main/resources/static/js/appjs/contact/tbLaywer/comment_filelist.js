
$(function() {
	load_comment_filelist();
});

function load_comment_filelist() {
	$('#comment_filelist')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : '/file/tbContactFiles/listPZ', // 服务器数据的加载地址
				// showRefresh : true,
				// showToggle : true,
				// showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				// search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						cntId : $('#cntId').val()
					};
				},
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				columns : [
					{
						checkbox : true
					},
                    {
                        field : 'name',
                        title : '文件名称'
                    },
                    {
                        field : 'reserve2',
                        title : '上传人员'
                    },
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var f = '<a class="btn btn-primary btn-sm " href="#" title="下载"  mce_href="#" onclick="download_file(\''
								+ row.name+'\',\''+row.path.replace(/\\/g,"/")+'\',\''+row.reserve1
								+ '\')">下载<i class="fa fa-download"></i></a> ';
							
							//只有合规风险部的人员可以编辑批注附件
							if($("#comment_file").val()=='on'){
								var e = '<a class="btn btn-primary btn-sm " href="#" title="删除"  mce_href="#" onclick="delete_add_file(\''
									+ row.processDefinitionId+'\',\''+row.id
									+ '\')">删除<i class="fa fa-times"></i></a> ';
							}else{
								var e = '';
							}
							

							return f+e;
						}
					} ]
			});
}
function comment_reLoad_filelist() {
	$('#comment_filelist').bootstrapTable('refresh');
}
function delete_comment_file(proId,id) {
    $.ajax({
        url: "/file/tbContactFiles/remove",
        type: "post",
        data: {
            'id': id
        },
        success: function (r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                comment_reLoad_filelist()
            } else {
                layer.msg(r.msg);
                comment_reLoad_filelist()
            }
        }
    });
}
layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#test3', //绑定元素
        url: '/file/tbContactFiles/upload', //上传接口
        size: 5000,
        accept: 'file',
        before: function(obj){
        	//携带额外的数据,type=1合同附件type=2批注附件
            this.data.params={
            		"cntId": $("#cntId").val(),
            		"type": "2"
            	};
            this.data.params=JSON.stringify(this.data.params);
        },
        done: function (r) {
            layer.msg(r.msg);
            $('#comment_filelist').bootstrapTable('refresh');
        },
        error: function (r) {
            layer.msg(r.msg);
            $('#comment_filelist').bootstrapTable('refresh');
        }
    });
});
