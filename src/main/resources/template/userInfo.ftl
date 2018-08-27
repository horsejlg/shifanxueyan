<div style="width:300px;margin:0px auto"><div id="userInfoView" class="easyui-panel" title="个人信息" style="width:300px;padding:10px;">
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">学号:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;">${user.loginname}</label>
    </div>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">姓名:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;">${user.nickName}</label>
    </div>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">专业:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px; width:120px;">${user.specialty.label}</label>
    </div>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">年级:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;">${user.grade.label}</label>
    </div>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">班级:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;">${user.classes.label}</label>
    </div>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">年度:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;">${user.year.label}</label>
    </div>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">政治面貌:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;">${user.politics.label}</label>
    </div>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">职务:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;">${user.job.label}</label>
    </div>
    <#if user.roles?size gt 0>
	<div style="margin-bottom:20px">
                <label class="textbox-label textbox-label-before" style="text-align: left; height: 24px; line-height: 24px;">角色:</label>
                <label class="textbox-label" style="text-align: left; height: 24px; line-height: 24px;width:120px;"><#list user.roles as role><#if role_index gt 0>, </#if>${role.label}</#list></label>
    </div>
    </#if>
    <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#userInfoView').panel('close');$('#userInfoEdit').panel('open');" style="width:100px">修改帐户信息</a>
            <a href="${base}/student/show/${user.id}.html" class="easyui-linkbutton" style="width:100px" target="_blank">查看人员信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#passwordEdit').window('open');" style="width:100px">修改密码</a>
    </div>
</div>
<div id="userInfoEdit" class="easyui-panel" title="编辑用户信息" data-options="iconCls:'icon-man',closed:true" style="width:300px;padding:20px;">
	<form id="userInfoForm" method="post" >
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="nickName" style="width:100%" data-options="label:'姓名:',value:'${user.nickName}',required:true,validType:length[2,20]">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="specialty" style="width:100%;" data-options="
                    url:'${base}/dicts/specialty',
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    value:'${user.specialty.code}',
                    label: '专业:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="grade" style="width:100%;" data-options="
                    url:'${base}/dicts/grade',
                    valueField:'code',
                    textField:'label', 
                    value:'${user.grade.code}',
                    panelHeight:'auto',
                    label: '年级:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="classes" style="width:100%;" data-options="
                    url:'${base}/dicts/class',
                    valueField:'code',
                    textField:'label',
                    value:'${user.classes.code}', 
                    panelHeight:'auto',
                    label: '班级:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="year" style="width:100%;" data-options="
                    url:'${base}/dicts/year',
                    valueField:'code',
                    textField:'label',
                    value:'${user.year.code}', 
                    panelHeight:'auto',
                    label: '年度:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="politics" style="width:100%;" data-options="
                    url:'${base}/dicts/politics',
                    valueField:'code',
                    textField:'label',
                    value:'${user.politics.code}', 
                    panelHeight:'auto',
                    label: '政治面貌:'
                    ">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="job" style="width:100%;" data-options="
                    url:'${base}/dicts/job',
                    valueField:'code',
                    textField:'label', 
                    value:'${user.job.code}', 
                    panelHeight:'auto',
                    label: '职务:'
                    ">
            </div>
            <input type="hidden" name="id" value="${user.id}"/>
            <input type="hidden" name="loginname" value="${user.loginname}"/>
            <input type="hidden" name="status" value="${user.status}"/>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveUserInfo()" style="width:80px">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelUserInfo()" style="width:80px">撤销</a>
        </div>
</div>
</div>
<div id="passwordEdit" class="easyui-window" title="用户信息" data-options="iconCls:'icon-man',minimizable:false,maximizable:false,collapsible:false,modal:true,resizable:false,draggable:false,closed:true" style="width:300px;padding:20px;">
<form id="passwordFrom" method="post" data-options="ajax:true,url:'${base}/password'">
			<div style="margin-bottom:20px">
                <input class="easyui-textbox" id="oldPwd" name="oldPwd" style="width:100%" data-options="label:'原密码:',required:true,validType:{length:[6,20]},validateOnCreate:false,validateOnBlur:true" >
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" id="newPwd" name="newPwd" style="width:100%" data-options="label:'新密码:',required:true,validType:{length:[6,20]},validateOnCreate:false,validateOnBlur:true" >
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" name="check" style="width:100%" data-options="label:'确认密码:',required:true,validType:{length:[6,20],equals:'#newPwd'},validateOnCreate:false,validateOnBlur:true" >
            </div>
</form>
<div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitPwd()" style="width:80px">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#passwordFrom').form('disableValidation').form('reset');$('#passwordEdit').window('close');" style="width:80px">撤销</a>
        </div>
</div>
<script type="text/javascript">
function saveUserInfo(){
var fromDate = $("#userInfoForm").serializeArray();
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
$.ajax({
	url:"${base}/me/user",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(data),
	dataType: "json",
	success: function (data) {
		if(data){
			$.messager.alert('信息','用户信息修改成功','info');
			cancelUserInfo();
		}
	}
});
}

function cancelUserInfo(){
	$('#userInfoEdit').panel('close');
	$('#userInfoView').panel('open');
}

function submitPwd(){
if($("#passwordFrom").form('enableValidation').form('validate')){
	$.post('${base}/password',{oldPwd:$("#oldPwd").val(),newPwd:$("#newPwd").val()},function(data){
		if(data){
			$('#passwordFrom').form('disableValidation').form('reset');
			$('#passwordEdit').window('close');
		}
	});
}
}
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '两次输入不同，请重新输入'
    }
});
</script>