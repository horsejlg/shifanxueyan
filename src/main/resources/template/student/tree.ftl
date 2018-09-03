<div data-options="region:'east',split:true" style="width:150px;">
<ul id="tt" class="easyui-tree"
            data-options="url:'${base}/dictNodes', onBeforeLoad: beforeLoad" >
</ul>
</div>
<div data-options="region:'center'" >
table id="usersTable" title="学生列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,method:'post',emptyMsg:'暂无数据',fit:true,pagination="true">
		<thead>
			<tr>
				<th data-options="field:'id',width:100">ID</th>
				<th data-options="field:'loginname',width:120">学号</th>
				<th data-options="field:'nickName',width:120,align:'right'">姓名</th>
				<th data-options="field:'lastLoginTime',width:120,align:'right'">最后登录时间</th>
				<th data-options="field:'status',width:40,align:'center',formatter: function(value,row,index){return value==1?'启用':'禁用'}">状态</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript">
function beforeLoad(node, param){
if(node){
	if(!node.id){
		param['type']=node['type']
	}else{
		switch(node['type']){
			case 'grade':
				param['type']= 'specialty';
				break;
			case 'specialty':
				param['type']= 'class';
				param['state']= 'open';
				break;
		}
	}
}
return true;

}

</script>