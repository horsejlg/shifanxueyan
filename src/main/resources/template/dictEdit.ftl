<table id="dictList" title="字典列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/dicts',method:'post',emptyMsg:'暂无数据',singleSelect: true,fit:true,toolbar:'#dictTools',
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
<div id="dictTools" style="padding:3px 10px">
<div>
	<span>字典类型:</span>
	<input id="dictType" class="easyui-combobox" name="dictType"
    data-options="valueField:'code',textField:'label',data:[<#list dictType as dict><#if dict_index gt 0 >, </#if>{code:'${dict.code}', label:'${dict.label}'}</#list>],value:'type',onChange:function(){$('#dictList').datagrid('reload');}">
</div>
<div>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addDict()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveDict(editIndex,$('#dictList').datagrid('getSelected'))">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteDict()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelDict()">撤销修改</a>
</div>
</div>
<script type="text/javascript">
var editIndex = undefined;
function addDict(){

var tmp = editIndex;
	if(editIndex != undefined){
	if(endDictEdit()){
		saveDict(tmp,$('#dictList').datagrid('getRows')[tmp]);
		}else{
			$.messager.alert('警告','请先完成或取消之前的修改操作','warning');
			return;
		}
	}
    editIndex = $('#dictList').datagrid('getRows').length;
	$('#dictList').datagrid('appendRow',{type:$('#dictType').combobox('getValue').code});
	$('#dictList').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
}

function deleteDict(){
	var row = $('#dictList').datagrid('getSelected');
	var code = row.code;
	if(!code) code = '_';
	$.ajax({
	url:"${base}/dict/"+code,
	method:"DELETE",
	success: function (data) {
		if(data){
			if(row.type=='type'){
				$.post("/dicts",{type:'type'},function(data){
					$('#dictType').combobox({data:data.rows});
					$('#dictList').datagrid('reload');
				});
			}else{
				$('#dictList').datagrid('reload');
			}
		}
	}});
}

function saveDict(index,row){
	if(endDictEdit()){
	var data = row;
	data.type = $('#dictType').combobox('getValue');
	$.ajax({
	url:"${base}/dict",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(data),
	dataType: "json",
	success: function (data) {
		if(data){
			if(row.type=='type')
				$.post("/dicts",{type:'type'},function(data){$('#dictType').combobox({data:data.rows})});
			$('#dictList').datagrid('acceptChanges');
		}
	}
});
	}else{
		cancelDict();
	}
}

function startEdit(index, row){
	var tmp = editIndex;
	if(editIndex != undefined){
	if(endDictEdit()){
		saveDict(tmp,$('#dictList').datagrid('getRows')[tmp]);
		}else{
			$.messager.alert('警告','请先完成或取消之前的修改操作','warning');
			return;
		}
	}
	editIndex = index;
	$('#dictList').datagrid('beginEdit', index)
}

function endDictEdit(){
	if (editIndex == undefined){return true}
    if ($('#dictList').datagrid('validateRow', editIndex)){
        $('#dictList').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
    	return false;
    }
}

function cancelDict(){
	$('#dictList').datagrid('rejectChanges');
    editIndex = undefined;
}
</script>