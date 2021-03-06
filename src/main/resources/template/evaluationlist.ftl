<table id="evaluations" title="测评报告列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/evaluations/${type}',method:'post',emptyMsg:'暂无数据',fit:true,toolbar:'#${type}Tools'" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'title',width:360,formatter:function(value,row,index){return '<a href=/evaluation/'+row.id+'.html target=_blank>'+value+'</a>'}">标题</th>
				<th data-options="field:'author',width:80,align:'right',formatter:function(value,row,index){if(value)return value.nickName;}">填报人</th>
				<th data-options="field:'specialty',width:100,align:'right',formatter:function(value,row,index){return row.author.specialty.label;}">专业</th>
				<th data-options="field:'grade',width:60,align:'right',formatter:function(value,row,index){return row.author.grade.label}">年级</th>
				<th data-options="field:'classes',width:60,align:'right',formatter:function(value,row,index){return row.author.classes.label}">班级</th>
				<th data-options="field:'year',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">年度</th>
				<th data-options="field:'sumSorce',align:'right',width:60">总分</th>
				<th data-options="field:'status',width:120,align:'center',formatter: function(value,row,index){return value==0?'个人未提交':(value==1?'班级审核':(value==2?'学院审核':'审核通过'))}">状态</th>
				<th data-options="field:'createTime',width:120,align:'right',formatter: function(value,row,index){return value.substring(0,19)}">创建时间</th>
			</tr>
		</thead>
	</table>
<div id="${type}Tools" style="padding:3px 10px">
<form id="evalquery">
<div>
<#if type != 'my'>	<span>填表人:</span>
	<input id="nickName" name="nickName" class="easyui-textbox" prompt="填表人">
	<span>&nbsp;专业:</span>
	<input class="easyui-combobox" id="specialty" name="specialty" style="width:120px;" data-options="
                    <#if type == 'class'>data:[{code:'${user.specialty.code}',label:'${user.specialty.label}'}],
                    value:'${user.specialty.code}',<#else>
                    url:'${base}/dicts/specialty',</#if>
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto'
                    ">
                    <span>&nbsp;年级:</span>
	<input class="easyui-combobox" id="grade" name="grade" style="width:120px;" data-options="
                    <#if type == 'class'>data:[{code:'${user.grade.code}',label:'${user.grade.label}'}],
                    value:'${user.grade.code}',<#else>
                    url:'${base}/dicts/grade',</#if>
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto'
                    ">
                    <span>&nbsp;班级:</span>
	<input class="easyui-combobox" id="classes" name="classes" style="width:120px;" data-options="
                    <#if type == 'class'>data:[{code:'${user.classes.code}',label:'${user.classes.label}'}],
                    value:'${user.classes.code}',<#else>
                    url:'${base}/dicts/class',</#if>
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto'
                    "></#if>
   	                <span>&nbsp;填报年度:</span>
	<input class="easyui-combobox" id="year" name="year" style="width:120px;" data-options="
                    url:'${base}/dicts/year',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto'
                    ">
                    <span>&nbsp;报告类型:</span>
	<input class="easyui-combobox" id="type" name="type" style="width:120px;" data-options="
					data:[{code:'Evaluation3',label:'学生综合素质测评表(2019)'},{code:'Evaluation2',label:'学生综合素质测评表(2018)'},{code:'Evaluation1',label:'学生综合素质测评表(2016)'}],
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto'
                    ">
                    <span>&nbsp;报告状态:</span>
	<input class="easyui-combobox" id="status" name="status" style="width:120px;" data-options="
					data:[<#if type == 'my'>{code:'0',label:'个人未上报'},</#if>{code:'1',label:'班级审核'},{code:'2',label:'学院审核'},{code:'3',label:'审核通过'}],
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto'
                    ">
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" onclick="query()">查询</a>
</div></form>
<div>
<#if type == 'my'>
	<a href="javascript:void(0)"  onclick="openEvaluation()" class="easyui-linkbutton" iconCls="icon-add" plain="true" target="_blank">填报学生综合素质测评表</a>
<#else>
	<button class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="downloadExcel()">下载综合素质测评登记表</button>
</#if>
</div>
</div>
<script type="text/javascript">
function openEvaluation(){
	var year = $("#year").combobox("getValue");
	if(!year){
		$.messager.alert('Warning','请选择年度');
		return;
	}
	window.open("/"+year+"/evaluation.html");
}

function downloadExcel(){
	var query = $("#evalquery").serializeArray();
	var data = {};
	$(query).each(function(){
		data[this.name]=this.value;
	});
	if(!data['grade']) {
		$.messager.alert('Warning','请选择年级');
		return;
	}
	if(!data['classes']){
		$.messager.alert('Warning','请选择班');
		return;
	}
	if(!data['year']){
		$.messager.alert('Warning','请选择年度');
		return;
	}
	if(!data['type']){
		$.messager.alert('Warning','请选择类型');
		return;
	}
	window.open("/evaluation/download/excel/"+data['type']+"/"+data['grade']+"/"+data['classes']+"/"+data['year']);
}

function query(){
	//var fromDate = $("#evalquery").serializeArray(); //这个方法取不出来隐藏input的val 有问题
	var data = {};
	data.nickName = $("#nickName").val();
	data.specialty = $("#specialty").val();
	data.grade = $("#grade").val();
	data.classes = $("#classes").val();
	data.year = $("#year").val();
	data.type = $("#type").val();
	data.status = $("#status").val();
	
	//$(fromDate).each(function(){
	//	data[this.name]=this.value;
	//});
	$('#evaluations').datagrid('load', data);
}
</script>