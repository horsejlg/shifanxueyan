<div class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'center'" >
<table id="assTable" title="辅导员列表"  class="easyui-datagrid"
			data-options="rownumbers:true,singleSelect:true,pagination:true,method:'post',emptyMsg:'暂无数据',fit:true",pagination:true">
		<thead>
			<tr>
				<th data-options="field:'user.nickName',formatter:showName,width:100">姓名</th>
				<th data-options="field:'id',width:100,formatter:optionFormatter">操作</th>
			</tr>
		</thead>
	</table>
</div>

</div>



</div>

<script type="text/javascript">

function nickNameFormatter(value,row,index){
	return row.user.nickName;
}

function optionFormatter(value,row,index){
	return '<a href="${base}/assistant/show/'+row.id+'.html" target="_blank">查看</a>';
}

$("#assTable").datagrid({data:${jsonFormat(assistants)}});

function showName(value,row,index){
	return "<a href='javascript:void(0)' onclick='sendMessage(\""+row.user.id+"\", \""+row.user.nickName+"\")'>"+row.user.nickName+"</a>"
}
</script>