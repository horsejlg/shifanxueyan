<@override name="head">
</@override>
<@override name="css">
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
<div region="center" border="false" class="easyui-panel" title="编辑辅导员信息">
<form id='assistantForm' >
<table class="infotable" align="center" style="border-bottom: 0px">
<tr>
	<td width="100" align="right">姓名</td>
	<td width="240" align="left">${assistant.user.nickName}</td>
	<td width="100" align="right">账号</td>
	<td width="240" align="left">${assistant.user.loginname}</td>
	<td width="160" rowspan="9" align="center" style="border-bottom: 0px"><a href="javascript:void(0)" onclick="$('#filesuploadWindow').dialog('open');	$('#filesuploadWindow').dialog('center');" ><img id="avatar" src="${base}${avatar(assistant.user.id)}" width="120" height="160" style="border: 1px silver solid ;"></a></td>
</tr>
<tr>
	<td align="right" style="border-bottom: 0px">联系电话</td>
	<td style="border-bottom: 0px"><input type="text" class="easyui-textbox" data-options="value:'${assistant.phone}'" name="phone" /></td>
	<td align="right" style="border-bottom: 0px">QQ/微信/微博</td>
	<td style="border-bottom: 0px"><input type="text" class="easyui-textbox" data-options="value:'${assistant.qq}'" name="qq" /></td>
</tr>
<tr>
	<td colspan=1 align="right">研究方向和成果</td>
	<td colspan=3><textarea name="researchResults" rows="10" cols="75">${assistant.researchResults}</textarea></td>
</tr>
</table>
<div style="margin:20px auto; width:882px" align="center">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveAssistant()" style="width:80px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#assistantForm').from('reset');" style="width:80px">重置</a>
</div>

<#-- 管理的年级 -->
<div style="margin:auto; width:882px">
<table id="gradesList" class="easyui-datagrid" data-options="title:'管理的年级',singleSelect:true,fit:false,toolbar:'#gradesBtn'">
<thead><tr>
<th width="287" align="center" field="label",required:true">年级</td>
</tr></thead>
</table>
</div>
<div id='gradesBtn'>
	<input class="easyui-combobox" id="grades_grade" name="grades_grade" data-options="
                    url:'${base}/dicts/grade',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '年级:'
                    ">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addGrades()" style="width:80px">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteGrades()" style="width:80px">删除</a>
</div>

<#-- 管理的班级 -->
<div style="margin:auto; width:882px">
<table id="classessList" class="easyui-datagrid" data-options="title:'管理的班级',singleSelect:true,fit:false,toolbar:'#classessBtn'">
<thead><tr>
<th width="287" align="center" field="awards" data-options="editor:'textbox',required:true">年级</td>
<th width="282" align="center"  field="level" data-options="editor:'textbox',required:true">班级</td>
</tr></thead>
</table>
</div>
<div id='classessBtn'>
	<input class="easyui-combobox" id="classess_grade" data-options="
                    url:'${base}/dicts/grade',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '年级:'
                    ">
    <input class="easyui-combobox" name="classess_specialty" data-options="
                    url:'${base}/dicts/specialty',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '专业:'
                    ">
    <input class="easyui-combobox" id="classess_classes" data-options="
                    url:'${base}/dicts/class',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '班级:'
                    ">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addClassess()" style="width:80px">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteClassess()" style="width:80px">删除</a>
</div>

<div style="margin:auto; width:882px">
<table id="awardsList" class="easyui-datagrid" data-options="title:'获得奖项',singleSelect:true,fit:false,toolbar:'#awardsBtn'">
<thead>
<tr>
<th width="287" align="center" field="awards" data-options="editor:'textbox',required:true">奖励情况</td>
<th width="282" align="center"  field="level" data-options="editor:'textbox',required:true">级别</td>
<th width="285" align="center" field="timed" data-options="editor:'datebox',formatter:formatDate,required:true">获奖时间</td>
</tr>
</thead>
</table>
</div>
<div id='awardsBtn'>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addawards()" style="width:80px">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveawards()" style="width:80px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelawards()">撤销修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteawards()" style="width:80px">删除</a>
</div>
</form></div>
<div id="filesuploadWindow" style="width:400px;padding:20px" class="easyui-dialog"
        data-options="iconCls:'icon-save',title:'上传照片',resizable:false, closed: true, modal:true">
<form  id="uploadForm" enctype="multipart/form-data" method="post"  action="${base}/console/info/fileUpload2" >
<input class="easyui-filebox" id="file" name="file" buttonText="选择本地图片" style="width:100%" data-options="onChange:fileupload"/ >
</form>
<p>照片要求 120x160，格式为.jpg</p>
</div>
</@override>
<@override name="script">
$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
};

$.fn.datebox.defaults.parser = function(s) {
if (s) {
if(s instanceof String){
var a = s.split('-');
var d = new Date(parseInt(a[0]), parseInt(a[1]) - 1, parseInt(a[2]));
return d;
}else{
return new Date(s);
}
} else {
return new Date();
}
 
};


function formatDate(value,row,index){
	if(value instanceof String){
		return value;
	}else{
		var date = new Date(value);
		return date.toJSON().substr(0,10); 
	}
}
$(function(){
	$('#gradesList').datagrid({data:${jsonFormat(assistant.grades)}});
	$('#classessList').datagrid({data:${jsonFormat(assistant.classess)}});
	$('#awardsList').datagrid({data:${jsonFormat(assistant.awards)}});
})

function addClassess(){
	var gradvalue = $('#classess_grade').combobox('getValue');
	var gradlabel = $('#classess_grade').combobox('getText');
	
	var specialtyvalue = $('#classess_specialty').combobox('getValue');
	var specialtylabel = $('#classess_specialty').combobox('getText');
	
	var classessvalue = $('#classess_classes').combobox('getValue');
	var classesslabel = $('#classess_classes').combobox('getText');
	
	if(gradvalue){
		$.messager.alert('警告','请选择一个年级','warning');
		return;
	}
	
	if(specialtyvalue){
		$.messager.alert('警告','请选择一个专业','warning');
		return;
	}
	
	if(classessvalue){
		$.messager.alert('警告','请选择一个班级','warning');
		return;
	}
	
	$.ajax({
		url:"${base}/assistant/grade/${assistant.user.id}",
		method:"post",
		contentType: "application/json; charset=utf-8",
		data:JSON.stringify({code:grad,label:label}),
		success: function (data) {
			$('#gradesList').datagrid('appendRow',{code:grad,label:label});
		}
	});
}

function addGrades(){
	var grad = $('#grades_grade').combobox('getValue');
	var label = $('#grades_grade').combobox('getText');
	if(grad){
		$.ajax({
			url:"${base}/assistant/grade/${assistant.user.id}",
			method:"post",
			contentType: "application/json; charset=utf-8",
			data:JSON.stringify({code:grad,label:label}),
			success: function (data) {
				$('#gradesList').datagrid('appendRow',{code:grad,label:label});
			}
		});
	}else{
		$.messager.alert('警告','请选择一个年级','warning');
	}
}

function deleteGrades(){
	var row = $('#awardsList').datagrid('getSelected');
	var index = $('#awardsList').datagrid('getRowIndex', row);
	$.ajax({
	url:"${base}/assistant/grade/"+row.id,
	method:"DELETE",
	success: function (data) {
		if(data){
			$('#awardsList').datagrid('deleteRow', index);
		}
	}});
}

var awardsIndex = undefined;
function addawards(){
var tmp = awardsIndex;
	if(awardsIndex != undefined){
	if(endawardsEdit()){
		saveawards(tmp,$('#awardsList').datagrid('getRows')[tmp]);
		}else{
			$.messager.alert('警告','请先完成或取消之前的修改操作','warning');
			return;
		}
	}
    awardsIndex = $('#awardsList').datagrid('getRows').length;
	$('#awardsList').datagrid('appendRow',{user:{id:"${assistant.id}"}});
	$('#awardsList').datagrid('selectRow', awardsIndex).datagrid('beginEdit', awardsIndex);
}
function endawardsEdit(){
	if (awardsIndex == undefined){return true}
    if ($('#awardsList').datagrid('validateRow', awardsIndex)){
        $('#awardsList').datagrid('endEdit', awardsIndex);
        awardsIndex = undefined;
        return true;
    } else {
    	return false;
    }
}

function startawardsEdit(index, row){
	var tmp = awardsIndex;
	if(awardsIndex != undefined){
	if(endawardsEdit()){
		saveawards(tmp,$('#awardsList').datagrid('getRows')[tmp]);
		}else{
			$.messager.alert('警告','请先完成或取消之前的修改操作','warning');
			return;
		}
	}
	awardsIndex = index;
	$('#awardsList').datagrid('beginEdit', index)
}
function saveawards(){
	var tmp = awardsIndex;
	if(endawardsEdit()){
	var row = $('#awardsList').datagrid('getRows')[tmp];
	$.ajax({
	url:"${base}/student/awards/${assistant.user.id}",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(row),
	success: function (data) {
		if(data){
			row["id"]=data;
		}
	}
});
}}
function cancelawards(){
	$('#awardsList').datagrid('rejectChanges');
    editIndex = undefined;
}
function deleteawards(){
	var row = $('#awardsList').datagrid('getSelected');
	var index = $('#awardsList').datagrid('getRowIndex', row);
	$.ajax({
	url:"${base}/student/awards/"+row.id,
	method:"DELETE",
	success: function (data) {
		if(data){
			$('#awardsList').datagrid('deleteRow', index);
		}
	}});
}


function saveAssistant(){
	var formdata = $("#assistantForm").serializeArray();
	var assistant = {id:"${assistant.id}"};
	assistant["user"]={id:"${assistant.user.id}"};
	$(formdata).each(function(){
		if(this.value){
		var obj = assistant;
		var key = this.name;
		obj[key] = this.value;
		}
	});
	$.ajax({
	url:"${base}/assistant",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(assistant),
	success: function (data) {
		if(data){
			$.messager.alert('信息','修改成功','info');
			$(location).attr('href', '${base}/assistant/show/'+data+'.html');
		}
	}
});
}

function fileupload(value){
	if(value){
	$.messager.progress();
	$('#uploadForm').form('submit', {
	url: "${base}/avatars/${assistant.user.id}",
	
	success: function(data){
		$("#avatar").attr("src", "${base}${avatar(assistant.user.id)}?_t="+Math.random());
		$.messager.progress('close');	// hide progress bar while submit successfully
	}
});
}}
</@override>
<@extends name="/def.ftl"/>