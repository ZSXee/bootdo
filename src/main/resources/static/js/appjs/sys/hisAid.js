
var prefix = "/legalAid/hisAid"
$(function() {
	load();
});

layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  laydate.render({
		  elem: '#startTime'
	  });
	  laydate.render({
		  elem: '#endTime'
	  });
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
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
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								startTime:$('#startTime').val(),
								endTime:$('#endTime').val(),
					            deptId:$('#reserve1').val(),
					            startUserId:$('#startUserId').val(),
					            dept_id:$('#dept_id').val(),
					            dept_name:$('#dept_name').val(),
					            name:$('#name').val()
							};
						},
						columns : [
								{
									checkbox : true
								},{
									field : 'procInstId', 
									title : '流程ID' 
								},{
									field : 'businessKey', 
									title : '合同ID',
									visible :false
								},{
									field : 'procDefId', 
									title : '流程定义ID',
									visible :false
								},{
									field : 'startTime', 
									title : '开始时间' 
								},{
									field : 'endTime', 
									title : '结束时间' 
								},{
//									field : 'duration', 
//									title : '耗时(分钟)',
//					                formatter : function(value, row, index) {
//					                    return Math.round(value/1000/60);
//					                } 
//								},{
									field : 'taskName', 
									title : '任务节点' 
								},{
									field : 'name', 
									title : '申请事项' 
								},{
									field : 'startUserId', 
									title : '发起人工号' 
								},{
									field : 'userName', 
									title : '发起人姓名' 
								},{
									field : 'reserve1', 
									title : '发起机构',
									visible :false
								},{
									field : 'deptName', 
									title : '发起机构名称'
								},{
									field : 'startActId', 
									title : '开始环节ID',
									visible :false
								},{
									field : 'endActId', 
									title : '结束环节ID',
									visible :false
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function export_action() {
	
    var data = {
			limit: '99999',
			offset: '0',
			startTime:$('#startTime').val(),
			endTime:$('#endTime').val(),
            deptId:$('#reserve1').val(),
            startUserId:$('#startUserId').val(),
            dept_name:$('#dept_name').val(),
            dept_id:$('#dept_id').val(),
            name:$('#name').val()
        }
	
	$.post(prefix + "/export", data, function(result) {
		if (result.code == 0) {
			window.location.href = prefix + "/download?fileName=" + result.msg + "&delete=" + true;;
		} else {
			alert(result.msg);
		}
	});
	
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
	$("#dept_name").val(deptName);
	$("#dept_id").val(deptId);
}
function reset() {
	document.getElementById("myForm").reset();
}

