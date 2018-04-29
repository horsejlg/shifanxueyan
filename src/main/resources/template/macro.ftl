<#macro filesupload name value="" label="管理附件" edit=true >
<#if edit>
<button class="easyui-linkbutton" type="button" onclick="reload('${name}')">${label}</button>
<input id="${name}" name="${name}" type="hidden" value="${value?html}"/>
</#if>
<div id="${name}_list">
<#-- if value?? && value!="">
<#assign items = jsonParser(value)>
<#if items??>
<#list items as item>
	<a href="${item!""}" style="margin-right:10px;" target="_blank">${item!"==="}</a>
</#list>
</#if>
</#if-->
</div>
<script>$(function(){
showfilelist('${name}');
})</script>
</#macro>
<#macro filesuploadScript title="" label="">
<#if !hasUploadScript?? >
<div id="filesuploadWindow" style="width:400px;height:400px" class="easyui-dialog"
        data-options="iconCls:'icon-save',title:'${title}',resizable:false, closed: true, modal:true">
<div class="easyui-layout" fit="true"><div data-options="region:'north',border:false">
<form  id="uploadForm" enctype="multipart/form-data" method="post"  action="${base}/console/info/fileUpload2" >
<input class="easyui-filebox" id="file" name="file" buttonText="上传" style="width:100%" data-options="onChange:fileupload"/ >
</form></div>
<div data-options="region:'center',border:false">
<table id="attachments" title="${label}" class="easyui-datagrid"
			data-options="ingleSelect:true,emptyMsg:'暂无数据',fit:true,value:[]">
		<thead>
			<tr>
				<th data-options="field:'path',width:340,formatter:attachment_formater">附件</th>
				<th data-options="field:'label',width:44,align:'right',formatter:option_formater">操作</th>
			</tr>
		</thead>
	</table>
</div>
<div data-options="region:'south',border:false">
<button class="easyui-linkbutton" type="button" onclick="fileListSubmit()">确定</button>
</div></div>
	</div>
<script>
function option_formater(value,row,index){
return '<button type="button" onclick="deleteFile('+index+')">删除</button>';
}


function attachment_formater(value,row,index){
return '<a href="'+value+'" target="_blank">'+row.label+'</a>'
}

function fileupload(value){
	if(value){
	$.messager.progress();
	$('#uploadForm').form('submit', {
	url: "${base}/console/info/fileUpload2",
	
	success: function(data){
		$("#attachments").datagrid("appendRow",$.parseJSON(data));
		$("#file").filebox("clear");
		$.messager.progress('close');	// hide progress bar while submit successfully
	}
});
}}
function deleteFile(index){
	$("#attachments").datagrid("deleteRow",index);
}
function reload(id){
	window.updatefiles = $("#"+id);
	if(window.updatefiles.val()){
	var list = $.parseJSON(window.updatefiles.val());
		list = {rows:list,total:list.length}
		$("#attachments").datagrid({data:list});
	}else{
	$("#attachments").datagrid({data:{rows:[],total:0}});
	}
	$("#filesuploadWindow").dialog("open");
	$("#filesuploadWindow").dialog("center");
}
function fileListSubmit(){
	if(window.updatefiles){
	var list = $("#attachments").datagrid("getData");
	var list_div=$("#"+window.updatefiles.attr("id")+"_list");
	list_div.empty();
	if(list){
		window.updatefiles.val(JSON.stringify(list.rows));
		$(list.rows).each(function(){
		list_div.append('<a href="'+this.path+'" style="margin-right:10px;" target="_blank">'+this.label+'</a>');
		});
		}else{
		window.updatefiles.val("");
		}
		$("#filesuploadWindow").dialog("close");
		delete window.updatefiles;
	}
}
function showfilelist(id){
	if($("#"+id).val()){
	var list = $.parseJSON($("#"+id).val());
	var list_div=$("#"+id+"_list");
	list_div.empty();
	if(list){
	$(list).each(function(){
		list_div.append('<a href="'+this.path+'" style="margin-right:10px;" target="_blank">'+this.label+'</a>');
		});
	}}
}
</script>
<#assign hasUploadScript=true >
</#if>
</#macro>