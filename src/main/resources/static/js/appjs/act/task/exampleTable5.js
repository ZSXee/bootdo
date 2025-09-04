//已办任务
var prefix = "/activiti/task"
$(function() {
	load5();
});

function load5() {
	$('#exampleTable5')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/toMyTakeList", // 服务器数据的加载地址
				//showRefresh : true,
				//showToggle : true,
				//showColumns : true,
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
						contactName : $('#searchName5').val(),
						startTime : $('#startTime5').val(),
						endTime : $('#endTime5').val(),
						cntAdminDep : $('#cntAdminDep5').val()
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
                        field : 'id',
                        title : '流程编号'
                    },
                    {
                        field : 'processDefinitionName',
                        title : '流程名称'
                    },
                    {
                        field : 'contactName',
                        title : '事项名称'
                    },
                    {
                        field : 'startTime',
                        title : '开始时间'
                    },
                    {
                        field : 'endTime',
                        title : '结束时间'
                    },
                    {
                        field : 'assignee',
                        title : '执行人'
                    },
                    {
                        field : 'id',
                        title : '跟踪',
						formatter : function(value, row, index) {
							var f = '<a class="btn btn-primary btn-sm " href="#" title="跟踪"  mce_href="#" onclick="track5(\''
								+ row.id
								+ '\')"><i class="fa fa-google"></i></a> ';
							return f;
						}
                    },
					{
						title : '查看',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							if(row.endTime==null)
								return ;
							var f = '<a class="btn btn-primary btn-sm " href="#" title="查看"  mce_href="#" onclick="scan5(\''
								+ row.id+'\',\''+row.processDefinitionId
								+ '\')"><i class="fa fa-eye"></i></a> ';
							return f;
						}
					} ]
			});
}
function reLoad5() {
	$('#exampleTable5').bootstrapTable('getOptions').pageNumber = 1; // 设置到第一页
	$('#exampleTable5').bootstrapTable('getOptions').pageSize = 10; // 设置每页显示10
	$('#exampleTable5').bootstrapTable('refresh');
}
function scan5(id,processDefinitionId) {
	if(processDefinitionId.indexOf('flxz')!= -1){
	    layer.open({
	        type : 2,
	        title : '查看',
	        maxmin : true,
	        shadeClose : false,
	        area : [ '100%', '100%' ],
	        content : '/legalAid/legalAid/scan/'+id
	    })
	}else{
	    layer.open({
	        type : 2,
	        title : '查看',
	        maxmin : true,
	        shadeClose : false,
	        area : [ '100%', '100%' ],
	        content : '/contact/tbContactInfo/scan/'+id+'/'+processDefinitionId
	    })
	}

}
function track5(id) {
    layer.open({
        type : 2,
        title : '跟踪',
        maxmin : true,
        shadeClose : false,
        area : [ '100%', '100%' ],
        content : '/activiti/task/trace/photo/'+ id
    })
}