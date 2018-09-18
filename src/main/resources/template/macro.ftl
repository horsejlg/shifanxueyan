<#macro filesupload name value="" label="管理附件" edit=true >
<#if edit>
<button class="easyui-linkbutton" type="button" onclick="reload('${name}')">${label}</button>
<input id="${name}" name="${name}" type="hidden" value="${value?html}"/>
<#else>
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
<textarea id="txt_row"  rows="2" style="width:95%" ></textarea>
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
	$("#attachments").datagrid({data:{rows:[],total:0}});
	if(window.updatefiles.val()){
	var list = $.parseJSON(window.updatefiles.val());
	for(var i=0;i<list.length;i++){
		if(list[i].path){
			//list1.push(list[i]);
			$("#attachments").datagrid("appendRow",list[i]);
		}else{
			$("#txt_row").val(list[i].label);
		}
	}
		
	}
	$("#filesuploadWindow").dialog("open");
	$("#filesuploadWindow").dialog("center");
}
function fileListSubmit(){
	if(window.updatefiles){
	var list = $("#attachments").datagrid("getData");
	var list_div=$("#"+window.updatefiles.attr("id")+"_list");
	list_div.empty();
	list = list.rows.slice(0);
	var txt = $("#txt_row").val();
	if(txt){
		list.push({"label":txt});
	}
	if(list && list.length > 0){
		window.updatefiles.val(JSON.stringify(list));
		$(list).each(function(){
		if(this.path){
			list_div.append('<a href="'+this.path+'" style="margin-right:10px;" target="_blank">'+this.label+'</a>');
		}else{
			list_div.append('<span style="margin-right:10px;" >'+this.label+'</span>');
		}
		});
		}else{
		window.updatefiles.val("");
		}
		$("#txt_row").val("");
		$("#filesuploadWindow").dialog("close");
		delete window.updatefiles;
	}
}

</script>
<#assign hasUploadScript=true >
</#if>
</#macro>