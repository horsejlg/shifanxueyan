<table id="notPassList" title="字典列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/violations/NotPass',method:'post',emptyMsg:'暂无数据',singleSelect: true,fit:true,toolbar:'#NotPassTools',
			onBeforeLoad:function(params){params['type']=$('#dictType').combobox('getValue')},onDblClickRow:startEdit,
			idField:'code', treeField:'label'" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'loginname',width:200,formatter:function(value,row,index){if(row.student)return row.student.user.loginname;}">学号</th>
				<th data-options="field:'nickName',width:200,formatter:function(value,row,index){if(row.student.user.specialty)return row.student.user.nickName;}">姓名</th>
				<th data-options="width:200,field:'specialty',formatter:function(value,row,index){if(row.student.user.specialty)return row.student.user.specialty.label;}">专业</th>
				<th data-options="field:'classes',width:200,formatter:function(value,row,index){if(row.student.user.classes)return row.student.user.classes.label;}">班级</th>
				<th data-options="width:200,field:'subject', formatter:function(value,row,index){if(value)return value.label;}">挂科科目</th>
				<th data-options="width:200,field:'term', formatter:function(value,row,index){if(value)return value.label;}">挂科学期</th>
			</tr>
		</thead>
	</table>
<div id="NotPassTools" style="padding:3px 10px">
<div>
	<span>字典类型:</span>
	<input id="dictType" class="easyui-combobox" name="dictType"
    data-options="valueField:'code',textField:'label',value:'type'">
</div>
</div>