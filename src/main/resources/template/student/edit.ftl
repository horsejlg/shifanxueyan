<#assign canSave = false />
<#if user.id == student.user.id>
	<#assign canSave = true />
<#else>
<#if user.roles?size gt 0 >
<#list user.roles as role>
<#if role.code == "assistant">
	<#assign canSave = true />
</#if>
</#list></#if></#if>
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
<div region="center" border="false" class="easyui-panel" title="编辑学生信息">
<form id='studentForm' >
<table class="infotable" align="center" style="border-bottom: 0px">
<tr>
	<td width="100" align="right">姓名</td>
	<td width="240" align="left"><input type="text" name="user.nickName" class="easyui-textbox" data-options="width:120,value:'${student.user.nickName}'" /></td>
	<td width="100" align="right">学号</td>
	<td width="240" align="left">${student.user.loginname}</td>
	<td width="160" rowspan="9" align="center" style="border-bottom: 0px"><a href="javascript:void(0)" onclick="$('#filesuploadWindow').dialog('open');	$('#filesuploadWindow').dialog('center');" ><img id="avatar" src="${base}${avatar(student.user.id)}" width="120" height="160" style="border: 1px silver solid ;"></a></td>
</tr>
<tr>
	<td align="right">出生年月</td>
	<td ><input type="text" name="birthday" class="easyui-datebox" data-options="width:120,value:'<#if student.birthday??>${student.birthday?string("yyyy-MM-dd")}</#if>',showSeconds:false" /></td>
	<td align="right">民族</td>
	<td ><input class="easyui-combobox" name="dict.nation"  data-options="
                    url:'${base}/dicts/nation',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    value:'<#if student.nation??>${student.nation.code}</#if>'
                    "></td>
</tr>
<tr>
	<td align="right">身份证号</td>
	<td><input type="text" class="easyui-textbox" data-options="value:'${student.idCode}'" name="idCode" /></td>
	<td align="right">籍贯</td>
	<td ><input class="easyui-combotree" name="dict.birthplace"  data-options="
                    url:'${base}/dictTree/area',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    value:'<#if student.birthplace??>${student.birthplace.code}</#if>'"></td>
</tr>
<tr>
	<td align="right">家庭住址</td>
	<td ><input type="text" class="easyui-textbox" data-options="value:'${student.address}'" name="address" /></td>
	<td align="right">家庭经济情况</td>
	<td ><input type="text" class="easyui-textbox" data-options="value:'${student.economy}'" name="economy" /></td>
</tr>
<tr>
	<td align="right">受助档次</td>
	<td ><input class="easyui-combobox" name="dict.grantee"  data-options="
                    url:'${base}/dicts/grantee',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    value:'<#if student.grantee??>${student.grantee.code}</#if>'"></td>
	<td align="right">政治面貌</td>
	<td ><input class="easyui-combobox" name="dict.politics"  data-options="
                    url:'${base}/dicts/politics',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    value:'<#if student.politics??>${student.politics.code}</#if>'"></td>
</tr>
<tr>
	<td align="right">专业</td>
	<td ><input class="easyui-combobox" name="user.dict.specialty"  data-options="
                    url:'${base}/dicts/specialty',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    value:'<#if student.user.specialty??>${student.user.specialty.code}</#if>'
                    "></td>
	<td align="right">班级</td>
	<td ><input class="easyui-combobox" name="user.dict.classes"  data-options="
                    url:'${base}/dicts/class',
                    valueField:'code',
                    textField:'label',
                    value:'<#if student.user.classes??>${student.user.classes.code}</#if>', 
                    panelHeight:'auto'
                    "></td>
</tr>
<tr>
	<td align="right">班级职务</td>
	<td ><input class="easyui-combobox" name="user.dict.job"  data-options="
                    url:'${base}/dicts/job',
                    valueField:'code',
                    textField:'label', 
                    value:'<#if student.user.job??>${student.user.job.code}</#if>', 
                    panelHeight:'auto'
                    "></td>
	<td align="right">班外职务</td>
	<td ><input class="easyui-combobox" name="dict.outJob"  data-options="
                    url:'${base}/dicts/job',
                    valueField:'code',
                    textField:'label', 
                    value:'<#if student.outJob??>${student.outJob.code}</#if>', 
                    panelHeight:'auto'
                    "></td>
</tr>
<tr>
	<td align="right">参加的社团</td>
	<td ><input type="text" class="easyui-textbox" data-options="value:'${student.community}'" name="community" /></td>
	<td align="right">宿舍号</td>
	<td ><input type="text" class="easyui-textbox" data-options="value:'${student.dormCode}'" name="dormCode" /></td>
</tr>
<tr>
	<td align="right" style="border-bottom: 0px">联系电话</td>
	<td style="border-bottom: 0px"><input type="text" class="easyui-textbox" data-options="value:'${student.phone}'" name="phone" /></td>
	<td align="right" style="border-bottom: 0px">QQ/微信/微博</td>
	<td style="border-bottom: 0px"><input type="text" class="easyui-textbox" data-options="value:'${student.qq}'" name="qq" /></td>
</tr>
</table>
<table class="infotable" align="center">
	<tr>
		<td width="120" align="right" >入党时间</td>
		<td width="160" align="left" ><input type="text" name="joinParty" class="easyui-datebox" data-options="width:120,value:'<#if student.joinParty??>${student.joinParty?string("yyyy-MM-dd")}</#if>'"></td>
		<td width="117" align="right" >入团时间</td>
		<td width="158" align="left" ><input type="text" name="joinUs" class="easyui-datebox" data-options="width:120,value:'<#if student.joinUs??>${student.joinUs?string("yyyy-MM-dd")}</#if>'"></td>
		<td width="118" align="right" >性别</td>
		<td width="160" align="left" ><input class="easyui-combobox" name="sex"  data-options="
                    data:[{'code':'f', 'label':'男'},{'code':'m','label':'女'}],
                    valueField:'code',
                    textField:'label', 
                    value:'${student.sex}', 
                    panelHeight:'auto'
                    "></td>
	</tr>
</table>
<div style="margin:20px auto; width:882px" align="center">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveStudent()" style="width:80px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#studentForm').from('reset');" style="width:80px">重置</a>
</div>
<div style="margin:auto; width:882px">
<table id="sociogramList" class="easyui-datagrid" data-options="title:'主要社会关系',singleSelect:true,fit:false,onDblClickRow:startSociogramEdit,toolbar:'#sociogramsBtn'">
	<thead><tr>
		<th width="160" align="center" field="name" data-options="editor:'textbox',required:true" >姓名</td>
		<th width="200" align="center" field="relation" data-options="editor:'textbox',required:true" >与本人关系</td>
		<th width="200" align="center" field="unit" data-options="editor:'textbox',required:true">工作单位</td>
		<th width="160" align="center" field="phone" data-options="editor:'textbox',required:true">联系方式</td>
	</tr></thead>
</table>
</div>
<div id='sociogramsBtn'>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addSociogram()" style="width:80px">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveSociogram()" style="width:80px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelSociogram()">撤销修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteSociogram()" style="width:80px">删除</a>
</div>
<div style="margin:auto; width:882px">
<table id="awardsList" class="easyui-datagrid" data-options="title:'奖励情况',singleSelect:true,fit:false,onDblClickRow:startawardsEdit,toolbar:'#awardsBtn'">
<thead><tr>
<th width="287" align="center" field="awards" data-options="editor:'textbox',required:true">奖励情况</td>
<th width="282" align="center"  field="level" data-options="editor:'textbox',required:true">级别</td>
<th width="285" align="center" field="timed" data-options="editor:'datebox',formatter:formatDate,required:true">获奖时间</td>
</tr></thead>
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
//
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
	$('#sociogramList').datagrid({data:${jsonFormat(student.sociograms)}});
	$('#awardsList').datagrid({data:${jsonFormat(student.awards)}});
})

var sociogramIndex = undefined;
function addSociogram(){
var tmp = sociogramIndex;
	if(sociogramIndex != undefined){
	if(endSociogramEdit()){
		saveSociogram(tmp,$('#sociogramList').datagrid('getRows')[tmp]);
		}else{
			$.messager.alert('警告','请先完成或取消之前的修改操作','warning');
			return;
		}
	}
    sociogramIndex = $('#sociogramList').datagrid('getRows').length;
	$('#sociogramList').datagrid('appendRow',{user:{id:"${student.id}"}});
	$('#sociogramList').datagrid('selectRow', sociogramIndex).datagrid('beginEdit', sociogramIndex);
}
function endSociogramEdit(){
	if (sociogramIndex == undefined){return true}
    if ($('#sociogramList').datagrid('validateRow', sociogramIndex)){
        $('#sociogramList').datagrid('endEdit', sociogramIndex);
        sociogramIndex = undefined;
        return true;
    } else {
    	return false;
    }
}

function startSociogramEdit(index, row){
	var tmp = sociogramIndex;
	if(sociogramIndex != undefined){
	if(endSociogramEdit()){
		saveSociogram(tmp,$('#sociogramList').datagrid('getRows')[tmp]);
		}else{
			$.messager.alert('警告','请先完成或取消之前的修改操作','warning');
			return;
		}
	}
	sociogramIndex = index;
	$('#sociogramList').datagrid('beginEdit', index)
}
function cancelSociogram(){
	$('#sociogramList').datagrid('rejectChanges');
    editIndex = undefined;
}
function deleteSociogram(){
	var row = $('#sociogramList').datagrid('getSelected');
	var index = $('#sociogramList').datagrid('getRowIndex', row);
	$.ajax({
	url:"${base}/student/sociogram/"+row.id,
	method:"DELETE",
	success: function (data) {
		if(data){
			$('#sociogramList').datagrid('deleteRow', index);
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
	$('#awardsList').datagrid('appendRow',{user:{id:"${student.id}"}});
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


<#if canSave >
function saveawards(){
	var tmp = awardsIndex;
	if(endawardsEdit()){
	var row = $('#awardsList').datagrid('getRows')[tmp];
	$.ajax({
	url:"${base}/student/awards/${student.user.id}",
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

function saveSociogram(){
	var tmp = sociogramIndex;
	if(endSociogramEdit()){
	var row = $('#sociogramList').datagrid('getRows')[tmp];
	$.ajax({
	url:"${base}/student/sociogram/${student.user.id}",
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
function saveStudent(){
	var formdata = $("#studentForm").serializeArray();
	var student = {id:"${student.id}"};
	student["user"]={id:"${student.user.id}"};
	$(formdata).each(function(){
		if(this.value){
		var obj = student;
		var key='';
		if(this.name.match('^user\\.')){
			key = this.name.substr(5);
			obj = student['user'];
		}else{
			key = this.name;
		}
		if(key.match('^dict\\.')){
			obj[key.substr(5)] = {code:this.value}
		}else{
			obj[key] = this.value;
		}
		}
	});
	$.ajax({
	url:"${base}/student",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(student),
	success: function (data) {
		if(data){
			$.messager.alert('信息','学生信息修改成功','info');
			$(location).attr('href', '${base}/student/show/'+data+'.html');
		}
	}
});
}

function fileupload(value){
	if(value){
	$.messager.progress();
	$('#uploadForm').form('submit', {
	url: "${base}/avatars/${student.user.id}",
	
	success: function(data){
		$("#avatar").attr("src", "${base}${avatar(student.user.id)}?_t="+Math.random());
		$.messager.progress('close');	// hide progress bar while submit successfully
	}
});
}}
</#if>
</@override>
<@extends name="/def.ftl"/>