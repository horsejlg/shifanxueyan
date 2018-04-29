<ul class="easyui-datalist" id="rolelist" data-options="singleSelect:false" style="width:306px;height:150px">
<#list roles as role>
	<li value="${role.code}">${role.label}</li>
</#list>
</ul