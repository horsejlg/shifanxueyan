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
<table class="infotable" align="center" style="border-bottom: 0px">
<tr>
	<td width="100" align="right">标题</td>
	<td width="744" align="left" colspan="5"><input type="text" class="easyui-textbox" data-options="value:'${topic.title}'" name="title" /></td>
</tr>
<tr>
	<td align="right">约定时间</td>
	<td ><input type="text" name="birthday" class="easyui-datebox" data-options="width:120,value:'<#if topic.promiseTime??>${topic.promiseTime?string("yyyy-MM-dd")}</#if>',showSeconds:false" /></td>
	<td align="right">截止时间</td>
	<td ><input type="text" name="birthday" class="easyui-datebox" data-options="width:120,value:'<#if topic.promiseTime??>${topic.promiseTime?string("yyyy-MM-dd")}</#if>',showSeconds:false" /></td>
	<td><input class="easyui-checkbox" name="fruit" value="Apple" label="发布:" ></td>
</tr>
<tr>
	<td align="right">地点</td>
	<td colspan="3"><input type="text" class="easyui-textbox" data-options="value:'${topic.title}'" name="title" /></td>
	<td><input class="easyui-checkbox" name="fruit" value="Apple" label="要求交作业:"></td>
</tr>
<tr>
	<td align="right">备注</td>
	<td colspan="4"><input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:300px"></td>
</tr>
<tr>
	<td colspan="6"><textarea id="content" name="content" style="width:100%;height:300px;">
</textarea></td>
</tr>
</table>
</@override>