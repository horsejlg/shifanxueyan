<table id="notPassList" title="字典列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/violations/NotPass',method:'post',emptyMsg:'暂无数据',singleSelect: true,fit:true,toolbar:'#NotPassTools',
			onBeforeLoad:function(params){params['type']=$('#dictType').combobox('getValue')},onDblClickRow:startEdit,
			idField:'code', treeField:'label'" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'label',width:200,editor:'textbox',required:true">描述</th>
				<th data-options="field:'code',width:100,editor:'textbox',required:true">字典代码</th>
				<th data-options="width:100,field:'parent', editor:'textbox'">上级字典代码</th>
			</tr>
		</thead>
	</table>
<div id="NotPassTools" style="padding:3px 10px">
<div>
	<span>字典类型:</span>
	<input id="dictType" class="easyui-combobox" name="dictType"
    data-options="valueField:'code',textField:'label',value:'type',onChange:function(){$('#dictList').datagrid('reload');}">
</div>
</div>