<form id="userInfo" method="post" >
	<input class="easyui-combobox" name="specialty" style="width:100%;" data-options="
                    url:${jsonFormat(assistant.grades)},
                    valueField:'code',
                    textField:'label', 
                    panelHeight:'auto',
                    label: '学科:'
                    ">
</form>