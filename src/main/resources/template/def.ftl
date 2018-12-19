<!DOCTYPE html>
<html>
  <head>
  <meta charset="utf-8">
  <title>教师教育学院综合素质评价系统</title>
  	<link rel="stylesheet" type="text/css" href="${base}/js/easyui/themes/<#if session??>${session.theme!"bootstrap"}<#else>bootstrap</#if>/easyui.css">
	<link rel="stylesheet" type="text/css" href="${base}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${base}/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<@block name="css">
</@block>
  </head>
  <body class="easyui-layout" style="text-align:left">
  <@block name="head">
  <div region="north" border="false" class="group wrap header" style="height:66px;font-size:100%">

  </div>
  </@>block>
  <@block name="body">
  没有引用
  </@block>
  <div id="logon" class="easyui-window" title="登录" data-options="iconCls:'icon-man',minimizable:false,maximizable:false,closable:false,collapsible:false,modal:true,resizable:false,draggable:false,zIndex:9999">
		<form id="logonForm" method="post">
<table cellpadding="10" width="90%" align="center" valign="middle" border="0" >
			<tr>
				<td align="right" width="80" for="loginname">账号：</td>
				<td><input name="loginname" type="text" data-options="validType:'minLength[6]'"></td>
			</tr>
			<tr>
				<td align="right" for="password">密码：</td>
				<td><input name="password" type="password" data-options="validType:'minLength[6]'"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 80px;" id="logonButton">登陆</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 80px;" id="clearButton">清空</a>
				</td>
			</tr>
		</table>
</form>
</div>
<script type="text/javascript">
<@block name="script">
</@block>
$(function(){
$.ajaxSetup({
	error: function(jqXHR, textStatus, errorThrown){
	
		switch (jqXHR.status){  
			case(401):
				alert("未登录或登录以超时，请重新登录！");  
				$("#logon").window('open');  
				break;  
			case(403):  
				alert("无权限执行此操作");
				break;  
			case(408):  
				alert("请求超时");  
				break;  
			default:  
				alert(jqXHR.responseJSON.message);  
			}  
		}
});
$("#logonButton").click(function(){
if($("#logonForm").form('enableValidation').form('validate')){
	$.post("${base}/logon",$("#logonForm").serialize(),function(data){
	//alert("登录成功，请继续操作！");
	$('#logon').window('close')}, "json");
}});
$("clearButton").click(function(){
	$("logonForm").form('disableValidation').form('reset');
});
<#if user??>
$("#logon").window('close');
</#if>
});
<#if user??>
window.messageData={}
function loadMessage(){
	$.get("/console/message/news",function(rows){
		if(rows && rows.length){
			$(rows).each(function(index, data){
				if(!window.messageData[data.id]){
				window.messageData[data.id]=true;
				$.messager.show({
					title:'来自'+data.userName+'的消息',
					msg:data.content,
					showType:'slide',
					timeout:0,
					onClose:function(){
						$.post("/console/message/state",{id:data.id,state:2},function(){
							delete window.messageData[data.id];
						})
						
					}
				});
				}
			});
		}
		setTimeout("loadMessage();",300000);
	});
}
loadMessage();
function sendMessage(id, name){
	$.messager.prompt("发送消息","请输入你要发送给"+name+"的消息",function(r){
		if(r){
			$.ajax({
			url: "/console/message",
			method:"post",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data:JSON.stringify({tos:{id:id},content:r}),
			success:function(data){
				if(data){
					$.messager.alert('发送成功','已成功将消息发送给'+name,'info');
				}else{
					$.messager.alert('发送失败','向'+name+'消息失败！','warning');
				}
			}})
		}
	})
}
</#if>
</script>
  </body>
</html>