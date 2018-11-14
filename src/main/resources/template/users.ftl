<table id="usersTable" title="人员列表" class="easyui-datagrid"
			data-options="pageList:[50,100],pageSize:50,rownumbers:true,singleSelect:true,pagination:true,url:'/console/users',method:'post',emptyMsg:'暂无数据',fit:true,toolbar:'#userTools'" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'id',width:100">ID</th>
				<th data-options="field:'loginname',width:120">学号</th>
				<th data-options="field:'nickName',width:120,align:'right'">姓名</th>
				<th data-options="field:'specialty',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label;}">专业</th>
				<th data-options="field:'grade',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">年级</th>
				<th data-options="field:'classes',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">班级</th>
				<th data-options="field:'year',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">年度</th>
				<th data-options="field:'politics',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">政治面貌</th>
				<th data-options="field:'job',width:120,align:'right',formatter:function(value,row,index){if(value)return value.label}">职务</th>
				<th data-options="field:'lastLoginTime',width:120,align:'right'">最后登录时间</th>
				<th data-options="field:'roles',width:120,formatter: function(value,row,index){var ret=[];for(var i=0;i<value.length;i++){ret.push(value[i].label);} return ret.toString()}">角色</th>
				<th data-options="field:'status',width:40,align:'center',formatter: function(value,row,index){return value==1?'启用':'禁用'}">状态</th>
			</tr>
		</thead>
	</table>
<div id="userTools" style="padding:3px 10px">

<div>
<form id="userQuery" method="post" >
	<span>学号:</span>
	<input id="loginname" class="easyui-textbox" prompt="学号">
	<span>&nbsp;姓名:</span>
	<input id="nickName" class="easyui-textbox" prompt="姓名">
                <input class="easyui-combobox" id="qspecialty" data-options="
                    url:'${base}/dicts/specialty',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '专业:'
                    ">
                <input class="easyui-combobox" id="qgrade" data-options="
                    url:'${base}/dicts/grade',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '年级:'
                    ">
                <input class="easyui-combobox" id="qclasses" data-options="
                    url:'${base}/dicts/class',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '班级:'
                    ">
                <input class="easyui-combobox" id="qrole" data-options="
                    url:'${base}/console/roles',
                    method:'GET',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    labelWidth:32,
                    label: '角色:'
                    ">
	<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" onclick="$('#usersTable').datagrid('load',{loginname: $('#loginname').val(),nickName: $('#nickName').val(), specialty:$('#qspecialty').combobox('getValue'), grade:$('#qgrade').combobox('getValue'), classes:$('#qclasses').combobox('getValue'), roles:$('#qrole').combobox('getValue')});">查询</a>
</form>
</div>
<div>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteUser()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-man" plain="true" onclick="setRole()">设置角色</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="updatePwd()">修改密码</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="$('#userImport').window('open');">用户导入</a>
</div>
</div>
<div id="userEdit" class="easyui-window" title="用户信息" data-options="iconCls:'icon-man',minimizable:false,maximizable:false,collapsible:false,modal:true,resizable:false,draggable:false,closed:true" style="width:300px;padding:20px;">
	<form id="userInfo" method="post" >
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" id="loginname" name="loginname" style="width:100%" data-options="label:'学号:',required:true,validType:{length:[6,20]},validateOnCreate:false,validateOnBlur:true" >
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="nickName" style="width:100%" data-options="label:'姓名:',required:true,validType:length[2,20]">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="specialty" style="width:100%;" data-options="
                    url:'${base}/dicts/specialty',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '专业:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="grade" style="width:100%;" data-options="
                    url:'${base}/dicts/grade',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '年级:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="classes" style="width:100%;" data-options="
                    url:'${base}/dicts/class',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '班级:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="year" style="width:100%;" data-options="
                    url:'${base}/dicts/year',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '年度:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="politics" style="width:100%;" data-options="
                    url:'${base}/dicts/politics',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '政治面貌:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="job" style="width:100%;" data-options="
                    url:'${base}/dicts/job',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '职务:'
                    ">
                <input class="easyui-combobox" name="role" style="width:100%;" data-options="
                    url:'${base}/console/roles',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '角色:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="status" style="width:100%;" data-options="
                    data:[{code:'1',label:'启用'},{code:'0',label:'禁用'}],
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    value:'1',
                    label: '状态:'
                    ">
            </div>
            <input type="text" style="display:none;" name="id"/>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveUser()" style="width:80px">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#userInfo').form('disableValidation').form('reset');$('#userEdit').window('close');$('#usersTable').datagrid('reload');" style="width:80px">撤销</a>
        </div>
</div>
<div id="role-dialog" class="easyui-dialog" title="角色分配" style="width:320px;"
            data-options="modal:true,resizable:false,closed:true,
                buttons: '#role-buttons',href:'${base}/roles.html'
            ">
</div>
<div id="role-buttons">
   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveRoles()">保存</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#role-dialog').dialog('close')">取消</a>
</div>

<div id="userImport" class="easyui-window" title="用户信息导入" data-options="iconCls:'icon-man',minimizable:false,maximizable:false,collapsible:false,modal:true,resizable:false,draggable:false,closed:true" style="width:300px;padding:20px;">
<form id="importUser" style="float: right;" enctype="multipart/form-data" action="${base}/console/import/users" method="post" data-options="success:function(data){if(data){$('#usersTable').datagrid('reload',{});$('#userImport').window('close');$('#importUser').form('reset')}}">
	<div><input class="easyui-combobox" name="grade" style="width:100%;" data-options="
                    url:'${base}/dicts/grade',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    required:true,
                    label:'年级'
                    "><input class="easyui-combobox" name="years" style="width:100%;" data-options="
                    url:'${base}/dicts/year',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    required:true,
                    label:'年度'
                    ">
                    <input class="easyui-combobox" name="specialty" style="width:100%;" data-options="
                    url:'${base}/dicts/specialty',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    required:true,
                    label: '专业:'
                    "><input class="easyui-combobox" name="classes" style="width:100%;" data-options="
                    url:'${base}/dicts/class',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    required:true,
                    label: '班级:'
                    "><input class="easyui-filebox" data-options="prompt:'选择excel文件',label: 'excel文件:',labelPosition:'top'" style="width:230px" name="file"/>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#importUser').form('submit')" style="width:80px">批量添加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#userImport').window('close');" style="width:80px">撤销</a>
        </div>
	
</form>
</div>

<script type="text/javascript">
function updatePwd(){
var date = $("#usersTable").datagrid('getSelected');
if(date){
	$.messager.prompt('修改密码', '你将要修改用户【'+date.nickName+'】的密码<br>请输入要修改的密码：', function(r){
		if (r){
			$.post("${base}/console/password",{"id":date.id,"pwd":r},function(data){if(data){$.messager.alert("提示信息","密码修改成功","info")}});
		}
	});
}
}

function addUser(){
	$('#userInfo').form('disableValidation').form('reset');
	$('#userEdit').window('open');
}
function editUser(){
var date = $("#usersTable").datagrid('getSelected');
if(date){
for(var i=0;i<date.roles.length;i++){
	date.roles[i]=date.roles[i].code;
}
date.specialty = date.specialty?date.specialty.code:"";
date.grade = date.grade?date.grade.code:"";
date.classes = date.classes?date.classes.code:"";
date.year = date.year?date.year.code:"";
date.job = date.job?date.job.code:"";
date.politics = date.politics?date.politics.code:"";
	$('#userInfo').form('disableValidation').form('load',date);
	$('#userEdit').window('open');
	}
}

function deleteUser(){
	var date = $("#usersTable").datagrid('getSelected');
	if(date){
	$.ajax({
	url:"${base}/console/user/"+date.id,
	method:"delete",
	success: function (data) {
		if(data){
			$.messager.alert('信息','用户信息删除成功','info');
			$("#usersTable").datagrid('reload');
		}
	}});
	}
}
function setRole(){
var date = $("#usersTable").datagrid('getSelected');
	if(date){
	var map = {};
	$(date.roles).each(function(){map[this.code]=true});
	var onOpen = function(){
		$("#rolelist").datalist("unselectAll");
		$($("#rolelist").datalist("getData").rows).each(function(index){
			if(map[this.value]){
				$("#rolelist").datalist("selectRow",index);
			}
		});
	}
	if($("#rolelist").length){
		onOpen();
	}else{
		$("#role-dialog").dialog({"onLoad":onOpen});
	}
	$("#role-dialog").dialog('open');
	}
}

function saveRoles(){
 var id = $("#usersTable").datagrid('getSelected').id;
 
 var roles =[];
  $($("#rolelist").datalist("getSelections")).each(function(){roles.push(this.value)});
 $.post("${base}/console/roles",{"id":id,"roles":roles.toString()},function(data){if(data){
 $("#role-dialog").dialog('close');
 $("#usersTable").datagrid('reload');}})
}

function saveUser(){
if($("#userInfo").form('enableValidation').form('validate')){
var fromDate = $("#userInfo").serializeArray();
var data = {};
$(fromDate).each(function(){
switch (this.name){
	case "specialty":
	case "grade":
	case "class":
	case "year":
	case "politics":
	case "job":
		data[this.name]={code:this.value}
        break;
	default:
		data[this.name]=this.value;
}
});
$.post("${base}/console/checkUser",{id:data.id, loginname:data.loginname},function(b){
if(b){
$.ajax({
	url:"${base}/console/user",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(data),
	dataType: "json",
	success: function (data) {
		if(data){
			$.messager.alert('信息','用户信息修改成功','info');
			$('#userEdit').window('close');
			$("#usersTable").datagrid('reload');
		}
	}
});}else{$.messager.alert('错误信息','用户名已经被占用','error');}
});

}
}
</script>