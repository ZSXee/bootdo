var data_comment;

$(function() {
	load_comments();
});

function load_comments() {
	$('#comments')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : '/activiti/task/listComment', // 服务器数据的加载地址
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
				sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						procInsId : $('#procInsId').val(),
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
                        title : '批注ID'
                    },
                    {
                        field : 'time',
                        title : '批注时间'
                    },
                    
                    {
                        field : 'userId',
                        title : '批注人员'
                    },
                    {
                        field : 'message',
                        title : '批注信息',
                        formatter: function (value, row, index){ // 单元格格式化函数
                            try{
                            	var val = JSON.parse(value);
                                return val.taskComment;
                            }catch(error){
                            	window.console.log("数据移植，无法点击查看。");
                            	return value;
                            }

                        }
					} ],
		        onClickRow: function (row) {
		        	data_comment = row.message;
                    try{
                    	var tmp = JSON.parse(row.message);
                    }catch(error){
                    	alert("数据移植，无法点击查看。");
                    	window.console.log("数据移植，无法点击查看。");
                    	return;
                    }
		        	var tmp = JSON.parse(row.message);
		        	var type;
		        	if(tmp.lvxing==null&&tmp.yuedu!=null)
		        		type=12;
		        	else if(tmp.lvxing!=null&&tmp.yuedu!=null)
		        		type=11
		        	else
		        		type=13
		        	layer.open({
		        		type:2,
		        		title:"查看批注",
		        		area : [ '730px', '530px' ],
		        		content:'/contact/tbContactInfo/scanComment/'+type
		        	})
		        }
			});
}
function reLoad_filelist() {
	$('#comments').bootstrapTable('refresh');
}