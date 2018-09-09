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
</@override>
<@extends name="/def.ftl"/>