<table id="topicList" title="专题列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/topics',method:'post',emptyMsg:'暂无数据',fit:true,toolbar:'#topicButton'<#if RequestParameters["auth"]??>,queryParams:{author_id:'true'}<#elseif RequestParameters["participants"]?? >,queryParams:{participants:'true'}<#elseif RequestParameters["visibleUsers"]?? >,queryParams:{visibleUsers:'true'}</#if>" pagination="true">
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
<#if user.roles?size gt 0 >
	<#list user.roles as role>
		<#if role.code == "assistant">
	<a href="javascript:void(0)" onclick="creatTopic()" class="easyui-linkbutton" iconCls="icon-add" plain="true" target="_blank">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteTopic()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="showTopic(1)">只看我创建的</a>
		</#if>
	</#list>
</#if>
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="showTopic(2)">只看我参与的</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="showTopic(3)">只看我可见的</a>
</div>
<script>

function createTopic(){
	if(undefined===queryParams['type']){
		$.messager.alert('信息','请先选择分类!','info');
		return;
	}
	window.open("${base}/topic/create.html?type="+queryParams['type']);
}

function deleteTopic(){
var row = $('#topicList').datagrid('getSelected');
var code = row.id;
if(!code) code = '_';
$.ajax({
	url:"${base}/topic/"+code,
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