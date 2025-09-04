$().ready(function() {
	var data = JSON.parse(parent.data_comment);
	
	//$('#comment').form('load',val);
	
	$("input[name=yuedu][value="+data.yuedu+"]").attr("checked",true);
	$("input[name=hegui][value="+data.hegui+"]").attr("checked",true);
	$("input[name=yiyi][value="+data.yiyi+"]").attr("checked",true);
	$("input[name=shenhe][value="+data.shenhe+"]").attr("checked",true);
	$("input[name=lvxing][value="+data.lvxing+"]").attr("checked",true);
	$("input[name=yewu][value="+data.yewu+"]").attr("checked",true);
	$("input[name=xiangfu][value="+data.xiangfu+"]").attr("checked",true);
	
	$('#taskComment').text(data.taskComment);
});