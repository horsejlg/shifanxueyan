<@override name="body">
<div region="center" border="false">
<#if user??>
	<div id="tt" class="easyui-tabs" data-options="fit:true,tabPosition:'left',tools:'#tab-tools'">
	<#if user.roles?size gt 0 >
	<#list user.roles as role>
		<#if role.code == "class">
			<#assign student = false>
		</#if>
	</#list>
	<#else>
			<#assign student = false>
	</#if>
	<#if student>
		<div title="个人信息" href="${base}/student/show/${user.id}.html" style="padding:10px;border-top:1px"></div>
		<#else>
		<div title="个人信息" href="${base}/userInfo.html" style="padding:10px;border-top:1px"></div>
		</#if>
		<#if user.classes??>
			<div title="我的评测表" href="/evaluations/my.html" style="padding:10px;border-top:1px"></div>
		</#if>
		<#if user.roles?size gt 0 >
			<#list user.roles as role>
				<#if role.code == "master">
				<div title="用户管理" href="${base}/users.html" style="padding:10px;border-top:1px"></div>
				<div title="字典管理" href="${base}/dict.html" style="padding:10px;border-top:1px"></div>
				</#if>
			</#list>
			<#list user.roles as role>
				<#if role.code == "teacher">
				<div title="学院审核" href="/evaluations/group.html" style="padding:10px;border-top:1px"></div>
				</#if>
			</#list>
			<#list user.roles as role>
				<#if role.code == "class">
				<div title="班级初步审核" href="/evaluations/class.html" style="padding:10px;border-top:1px"></div>
				</#if>
			</#list>
		</#if>
	</div>
</#if>
</div>
<div id="tab-tools">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-config'" onclick="logout()">退出登录</a>
</div>
</@override>
<@override name="script">
<#if user??>
function logout(){
	$.get("${base}/logOut",function(data){if(data){
	window.event.returnValue=false;
	window.location.href="${base}/"}});
}
<#else>

$(function(){
	$("#logon").window({"onClose":function(){window.location.reload();}});
});
</#if>
</@override>
<@extends name="/def.ftl"/>