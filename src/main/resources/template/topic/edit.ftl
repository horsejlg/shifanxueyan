<@override name="head">
</@override>
<@override name="css">
<script type="text/javascript" src="${base}/js/kindeditor/kindeditor-all-min.js"></script>
<style type="text/css">
 .infotable{
 	border-collapse: collapse;
 }
 .infotable td{
 	border: 0px silver solid;
 	height: 1.5em;
 	padding: 3px;
 }
</style>
</@override>
<@override name="body">
<div region="center" border="false" class="easyui-panel" title="专题:${topic.title!"新建"}">
<form id='topicForm' >
<table class="infotable" align="center" style="border-bottom: 0px">
<tr>
	<td width="100" align="right">标题</td>
	<td width="744" align="left" colspan="5"><input type="text" class="easyui-textbox" data-options="value:'${topic.title}'" name="title" /></td>
</tr>
<tr>
	<td align="right">约定时间</td>
	<td ><input type="text" name="promiseTime" class="easyui-datebox" data-options="width:120,value:'<#if topic.promiseTime??>${topic.promiseTime?string("yyyy-MM-dd")}</#if>',showSeconds:false" /></td>
	<td align="right">截止时间</td>
	<td ><input type="text" name="endTime" class="easyui-datebox" data-options="width:120,value:'<#if topic.endTime??>${topic.endTime?string("yyyy-MM-dd")}</#if>',showSeconds:false" /></td>
	<td><input class="easyui-checkbox" id="publish" name="publish" value="true" label="发布:" <#if topic.publish == 1>checked</#if> ></td>
</tr>
<tr>
	<td align="right">地点</td>
	<td colspan="3"><input type="text" class="easyui-textbox" data-options="value:'${topic.location}'" name="location" /></td>
	<td><input class="easyui-checkbox" id="homework" name="homework" value="true" label="要求交作业:" <#if topic.homework == 1>checked</#if>></td>
</tr>
<tr>
	<td align="right">备注</td>
	<td colspan="4"><input class="easyui-textbox" name="remark" data-options="value:'${topic.remark}'" style="width:300px"></td>
</tr>
<tr>
	<td colspan="6"><textarea id="content" name="content" style="width:100%;height:300px;">
	${topic.content}
</textarea></td>
</tr>
</table>
<div style="margin:20px auto; width:882px" align="center">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveTopic()" style="width:80px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#topicForm').from('reset');" style="width:80px">重置</a>
</div>
<input type="hidden" name="id" id="topicId" value="${topic.id}" />
</form>
<div align="center">
<table id="participants" class="easyui-datagrid" data-options="url:'${base}/topic/participants/${topic.id}',method:'get',toolbar:'#participantsButton',width:800">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true"></th>
			<th data-options="field:'loginname',width:120">学号</th>
			<th data-options="field:'nickName',width:120,align:'right'">姓名</th>
			<th data-options="field:'specialty',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label;}">专业</th>
			<th data-options="field:'grade',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">年级</th>
			<th data-options="field:'classes',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">班级</th>
		</tr>
	</thead>
</table>
</div>
</div>
<div id="participantsButton" >
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser('participants')">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteUser('participants')">删除</a>
</div>
<div id="userSelect" class="easyui-window" title="用户信息" data-options="iconCls:'icon-man',minimizable:false,maximizable:false,collapsible:false,modal:true,resizable:false,draggable:false,closed:true" style="width:850px;padding:20px;">
<table id="userList" class="easyui-datagrid" data-options="url:'/console/users',toolbar:'#userTools'">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true"></th>
			<th data-options="field:'loginname',width:120">学号</th>
			<th data-options="field:'nickName',width:120,align:'right'">姓名</th>
			<th data-options="field:'specialty',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label;}">专业</th>
			<th data-options="field:'grade',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">年级</th>
			<th data-options="field:'classes',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">班级</th>
		</tr>
	</thead>
</table>
<div id="userTools" style="padding:3px 10px">
<form id="userQuery" method="post" >
	<span>学号:</span>
	<input id="loginname" class="easyui-textbox" prompt="学号">
	<span>&nbsp;姓名:</span>
	<input id="nickName" class="easyui-textbox" prompt="姓名">
                <input class="easyui-combobox" id="qspecialty" data-options="
                    url:'${base}/dicts/specialty',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '专业:'
                    ">
                <input class="easyui-combobox" id="qgrade" data-options="
                    url:'${base}/dicts/grade',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '年级:'
                    ">
                <input class="easyui-combobox" id="qclasses" data-options="
                    url:'${base}/dicts/class',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '班级:'
                    ">
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" onclick="$('#userList').datagrid('load',{loginname: $('#loginname').val(),nickName: $('#nickName').val(), specialty:$('#qspecialty').combobox('getValue'), grade:$('#qgrade').combobox('getValue'), classes:$('#qclasses').combobox('getValue')});">查询</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addTo()">添加</a>
</form>
</div>
</div>

</@override>
<@override name="script">
KindEditor.ready(function(K) {
                window.editor = K.create('#content');
        });
function saveTopic(){
editor.sync();
var formdata = $("#topicForm").serializeArray();
var topic = {};
$(formdata).each(function(){
	if(this.value){
		topic[this.name] = this.value;
	}
});
if(topic["publish"]){
	topic["publish"]=1;
}else{
	topic["publish"]=0;
}

if(topic["homework"]){
	topic["homework"]=1;
}else{
	topic["homework"]=0;
}
$.ajax({
	url:"${base}/topic",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(topic),
	success: function (data) {
		if(data){
			$.messager.alert('信息','专题保存成功','info', function(){
			$(location).attr('href', '${base}/topic/edit/'+data.id+'.html');
			});
		}
	}
});
}

function addUser(type){
window.targetType = type;
	$('#userSelect').window('open');
}

function addTo(){
	var users = $('#userList').datagrid("getSelections");
	if(users){
		$.ajax({
			url:"${base}/topic/"+window.targetType+"/${topic.id}",
			method:"post",
			contentType: "application/json; charset=utf-8",
			data:JSON.stringify(users),
			dataType: "json",
			success: function (data) {
				if(data){
					$.messager.alert('信息','添加用户成功','info');
					$('#userSelect').window('close');
					$("#"+window.targetType).datagrid('reload');
					window.targetType = undefined;
				}
			}
		});
	}
}

function deleteUser(type){
	var users = $('#'+type).datagrid("getSelections");
	if(users){
		$.ajax({
			url:"${base}/topic/"+type+"/${topic.id}/false",
			method:"delete",
			contentType: "application/json; charset=utf-8",
			data:JSON.stringify(users),
			dataType: "json",
			success: function (data) {
				$.messager.alert('信息','删除用户成功','info');
				$("#"+type).datagrid('reload');
			}
		});
	}
}
</@override>
<@extends name="/def.ftl"/>