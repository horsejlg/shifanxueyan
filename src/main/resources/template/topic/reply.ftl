<div style="width: 798px; margin:0 auto">
<table id="topicReplys" class="easyui-datagrid" width="100%"
	data-options="onClickRow:function(){$('#topicReplys').datagrid('clearSelections');},pageList:[20,100],pageSize:20,pagination:true,url:'/topicReplys',method:'post',queryParams:{topicId:'${topic.id}'},emptyMsg:'暂无回复'" >
	<thead>
	<tr>
		<th data-options="field:'content',width:796,formatter:showReply">回复列表</th>
	</tr>
	<thead>
</table>
		<a name="sub"></a>
		<div >
			<div >发表回复</div>
		</div>
		<div >
			<div >
				<div >内&nbsp;&nbsp;容:</div>
				<div >
					<div style="width: 790px;">
						<div >
							<div >
								<div id="ueditor_replace"
									style="width: 790px; min-height: 120px; z-index: 0; border: 1px solid silver;" contenteditable="true">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div >
					<a class="easyui-linkbutton" href="javascript:void(0)" onclick="saveReply()" ><span><em>发 表</em></span></a>
					<#-- 
					 <a class="easyui-linkbutton" href="javascript:void(0)" ><span
						>清空回复内容</span></a>
					 -->
					<div >
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
function showReply(value,row,index){
	var hash = hashCode(row.id);
	return "<div style='height:auto;width: 100px;display: table-cell;'><div ><p><img width='60' height='80' src='${base}/avatars/"
	+ (hash%100) + "/" + parseInt(hash/100) + "/"+ row.id + 
	".jpg' /></p><p ><a href='javascript:void(0)'><span>"
	+ row.author.nickName +
	"</span></a></p><p>"
	+ (row.author.grade?row.author.grade.label:"") + (row.author.classes?row.author.classes.label:"") +
	"</p></div></div><div style='width: 690px; display: table-cell;vertical-align: top;'><div style='text-align: right;'>"
	+ row.createTime.substring(0,19) +
	"</div><div >"
	+ row.content + 
	"</div></div>"+
<#if permissions == 2>
	"<div align='right'><a href='javascript:void(0)' onclick='delRelpy(\""+row.id+"\")'>删除</a></div>"+
</#if>	"</div>";
}

function hashCode(str){
    var hash = 0;
    if (str.length == 0) return hash;
    for (i = 0; i < str.length; i++) {
        char = str.charCodeAt(i);
        hash = ((hash<<5)-hash)+char;
        hash = hash & hash; // Convert to 32bit integer
    }
    return hash;
}

function saveReply(){
$.post("${base}/topicReply",{topicId:"${topic.id}", content:$("#ueditor_replace").html()},function(data){
	if(data){
		$("#ueditor_replace").html("");
		$("#topicReplys").datagrid("reload");
	}
});
}		

function delRelpy(id){
$.messager.confirm({
title: '提示信息',
	msg: '你确认要删除这条回复吗?',
	fn: function(r){
	if(r){
	$.ajax({
		url:"${base}/topicReply/"+id,
		type: "DELETE",
		success: function (retval){
        	$("#topicReplys").datagrid("reload");
        }
	});
	}
	}
	});
}
</script>