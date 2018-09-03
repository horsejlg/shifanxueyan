<div class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" style="width:200px;">
<ul id="tt" class="easyui-tree"
            data-options="url:'${base}/dictNodes', onBeforeLoad: beforeLoad, onClick:onclick" >
</ul>
</div>
<div data-options="region:'center'" >
<table id="studentTable" title="学生列表"  class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,method:'post',emptyMsg:'暂无数据',fit:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'loginname',width:120">学号</th>
				<th data-options="field:'nickName',width:120,align:'right'">姓名</th>
				<th data-options="field:'status',width:40,align:'center',formatter: function(value,row,index){return value==1?'启用':'禁用'}">状态</th>
				<th data-options="field:'id',width:100,formatter:optionFormatter">操作</th>
			</tr>
		</thead>
	</table>
</div>

</div>
<script type="text/javascript">
var queryParams = {};
function beforeLoad(node, param){
if(node){
	if(!node.id){
		param['type']=node['type']
	}else{
		switch(node['type']){
			case 'grade':
				param['type']= 'specialty';
				queryParams['grade']= node["id"];
				break;
			case 'specialty':
				param['type']= 'class';
				param['state']= 'open';
				queryParams['specialty']= node["id"];
				break;
		}
	}
}
return true;

}

function optionFormatter(value,row,index){
	return '<a href="${base}/student/show/'+row.id+'.html" target="_blank">查看</a>&nbsp;<a href="${base}/student/edit/'+row.id+'.html" target="_blank">编辑</a>';
}

function onclick(node){
	if(node && node.type == 'class'){
		queryParams['classes']= node["id"];
		$("#studentTable").datagrid({url:'${base}/students',queryParams:queryParams});
	} 
}

</script>