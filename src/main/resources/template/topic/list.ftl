<table id="topicList" title="专题列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/topics',method:'post',emptyMsg:'暂无数据',fit:true,toolbar:'#topicButton'" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'title',width:360,formatter:function(value,row,index){return '<a href=/topic/edit/'+row.id+'.html target=_blank>'+value+'</a>'}">标题</th>
				<th data-options="field:'promiseTime',width:120">约定时间</th>
				<th data-options="field:'endTime',width:120">结束时间</th>
				<th data-options="field:'location',width:360">地点</th>
				<th data-options="field:'publish',width:120,align:'center',formatter: function(value,row,index){return value==0?'未发布':'已发布'}">状态</th>
				<th data-options="field:'homework',width:120,align:'center',formatter: function(value,row,index){return value==0?'不需要':'需要'}">是否需要交作业</th>
			</tr>
		</thead>
	</table>
<div id="topicButton">
	<a href="${base}/topic/create.html" class="easyui-linkbutton" iconCls="icon-add" plain="true" target="_blank">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteTopic()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="showTopic(1)">只看我创建的</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="showTopic(2)">只看我参与的</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="showTopic(3)">只看我可见的</a>
</div>
<script>
function deleteTopic(){
var row = $('#topicList').datagrid('getSelected');
var code = row.code;
if(!code) code = '_';
$.ajax({
	url:"${base}/dict/"+code,
	method:"DELETE",
	success: function (data) {
		$('#topicList').datagrid("reload");
	}
})
}
function showTopic(mode){
var param = {};
switch(mode){
	case 1:
		param["author_id"]=true;
		break;
	case 2:
		param["participants"]=true;
		break;
	default:
		param["visibleUsers"]=true;
}
$('#topicList').datagrid("load", param);
}
</script>