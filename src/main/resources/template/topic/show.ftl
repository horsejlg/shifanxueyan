<div style="width: 598px; margin:0 auto">
<table id="topicList" class="easyui-datagrid" width="100%"
	data-options="pageList:[20,100],pageSize:20,rownumbers:true,singleSelect:true,pagination:true,url:'/topicReply',method:'post',queryParams:{topicId:'${topic.id}'},emptyMsg:'暂无回复'" >
	<tr>
		<th data-options="field:'id',width:598, formatter:function(value,row,index){}"></th>
	</tr>
</table>
		<a name="sub"></a>
		<div >
			<div >发表回复</div>
		</div>
		<div >
			<div >
				<div >内&nbsp;&nbsp;容:</div>
				<div >
					<div style="width: 598px;">
						<div >
							<div >
								<div id="ueditor_replace"
									style="width: 568px; min-height: 220px; z-index: 0; border: 1px solid silver;" contenteditable="true">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div >
					<a href="javascript:void(0)" onclick="alert(document.getElementById('ueditor_replace').innerHTML)" ><span><em>发 表</em></span></a><span
						></span>
					<div >
						<span></span><span title="清空草稿"
							></span>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
		
</script>