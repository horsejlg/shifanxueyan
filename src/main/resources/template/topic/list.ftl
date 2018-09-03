<table id="evaluations" title="专题列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/topics',method:'post',emptyMsg:'暂无数据',fit:true," pagination="true">
		<thead>
			<tr>
				<th data-options="field:'title',width:360,formatter:function(value,row,index){return '<a href=/topic/'+row.id+'.html target=_blank>'+value+'</a>'}">标题</th>
				<th data-options="field:'promiseTime',width:360,formatter:function(value,row,index){return '<a href=/topic/'+row.id+'.html target=_blank>'+value+'</a>'}">标题</th>
				<th data-options="field:'endTime',width:360,formatter:function(value,row,index){return '<a href=/topic/'+row.id+'.html target=_blank>'+value+'</a>'}">标题</th>
				<th data-options="field:'publish',width:120,align:'center',formatter: function(value,row,index){return value==0?'未发布':'已发布'}">状态</th>
				<th data-options="field:'publish',width:120,align:'center',formatter: function(value,row,index){return value==0?'未发布':'已发布'}">状态</th>
			</tr>
		</thead>
	</table>