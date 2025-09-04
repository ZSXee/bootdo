//已办任务
var prefix = "/activiti/task"
$(function() {
	load6();
});
function load6() {
	$('#exampleTable6')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/toFinnishListAll", // 服务器数据的加载地址
				//showRefresh : true,
				// showToggle : true,
				// showColumns : true,
				iconSize : 'outline',
				toolbar : '#toolbar2',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				//设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				//发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				//search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						contactName : $('#contactName').val(),
						startTime : $('#startTime').val(),
						endTime : $('#endTime').val(),
						startUser : $('#startUser').val(),
						cntAdminDep : $('#cntAdminDep').val()
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
                        title : '任务'
                    },
                    {
                        field : 'key',
                        title : '任务key',
                        visible : false
                    },
                    {
                        field : 'processDefinitionId',
                        title : 'processDefinitionId',
                        visible : false
                    },
                    {
                        field : 'procInstId',
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
						field : 'startUser',
						title : '发起人姓名'
                    },
                    {
						field : 'cntAdminDep',
						title : '发起机构名称'
                    },
                    {
                        field : 'processId',
                        title : '跟踪',
						formatter : function(value, row, index) {
							var f = '<a class="btn btn-primary btn-sm " href="#" title="跟踪"  mce_href="#" onclick="track6(\''
								+ row.procInstId
								+ '\')"><i class="fa fa-google"></i></a> ';
							return f;
						}
                    },
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var f = '<a class="btn btn-primary btn-sm " href="#" title="查看"  mce_href="#" onclick="scan6(\''
								+ row.procInstId+'\',\''+row.processDefinitionId
								+ '\')">查看<i class="fa fa-key"></i></a> ';
							return f;
						}
					} ]
			});
}
function reLoad6() {
	$('#exampleTable6').bootstrapTable('getOptions').pageNumber = 1; // 设置到第一页
	$('#exampleTable6').bootstrapTable('getOptions').pageSize = 10; // 设置每页显示10
	$('#exampleTable6').bootstrapTable('refresh');
}
function scan6(processId,processDefinitionId) {
	if(processDefinitionId.indexOf('flxz')!= -1){
	    layer.open({
	        type : 2,
	        title : '查看',
	        maxmin : true,
	        shadeClose : false,
	        area : [ '100%', '100%' ],
	        content : '/legalAid/legalAid/scan/'+processId
	    })
	}else{
	    layer.open({
	        type : 2,
	        title : '查看',
	        maxmin : true,
	        shadeClose : false,
	        area : [ '100%', '100%' ],
	        content : '/contact/tbContactInfo/scan/'+processId+'/'+processDefinitionId
	    })
	}
}
function track6(processId) {
    layer.open({
        type : 2,
        title : '跟踪',
        maxmin : true,
        shadeClose : false,
        area : [ '100%', '100%' ],
        content : '/activiti/task/trace/photo/'+ processId
    })
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#cntAdminDep").val(deptName);
}
