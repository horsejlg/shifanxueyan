<head>
  <meta charset="utf-8">
  <title>辅导员详细信息</title>
  	<link rel="stylesheet" type="text/css" href="/css/easyui/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="/css/easyui/icon.css">
	<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
 .infotable{
 	border-collapse: collapse;
 }
 .infotable td{
 	border: 1px silver solid;
 	height: 1.5em;
 	padding: 3px;
 }
 .button{
    position: absolute;
    right: 20px;
 }
</style>
</head>
<style media=print>
.Noprint{display:none;}
</style>
<body>
<table class="infotable" align="center" style="border-bottom: 0px">
<tr>
	<td width="100" align="right">姓名</td>
	<td width="240" align="left">${assistant.user.nickName}</td>
	<td width="100"  align="right">账号</td>
	<td width="240" align="left">${assistant.user.loginname}</td>
	<td width="160" rowspan="3" align="center" ><img src="${base}${avatar(assistant.user.id)}" width="120" height="160" style="border: 1px silver solid ;"></td>
</tr>
<#--
<tr>
	<td align="right">专业</td>
	<td ><#if assistant.user.specialty??>${assistant.user.specialty.label}</#if></td>
	<td align="right">班级</td>
	<td ><#if assistant.user.classes??>${assistant.user.classes.label}</#if></td>
</tr>
 -->
<tr>
	<td align="right">联系电话</td>
	<td>${assistant.phone}</td>
	<td align="right">QQ/微信/微博</td>
	<td>${assistant.qq}</td>
</tr>

<tr>
	<td colspan=1 align="right">研究方向和成果</td>
	<td colspan=3><#if assistant.researchResults??>${assistant.researchResults}</#if></td>
</tr>
</table>

<#if assistant.grades?? && assistant.grades?size gt 0>
<table class="infotable" align="center">
<tr>
<td width="884" align="center" style="border-top: 0px;">管理的年级</td>
</tr>
<#list assistant.grades as grade>
<tr>
	<td align="center" >${grade.label}</td>
</tr>
</#list>
</table>
<#else>
<table class="infotable" align="center">
<tr>
<td width="884" align="center" style="border-top: 0px;">年级</td>
</tr>
<tr>
	<td align="center" >&nbsp;</td>
</tr>
</table>
</#if>

<#if assistant.classess?? && assistant.classess?size gt 0>
<table class="infotable" align="center">
<tr>
<td width="299" colspan=3 align="center" style="border-top: 1px;">管理的班级</td>
</tr>

<#list assistant.classess as cl>
<tr>
	<td width="299"  align="center" >${cl.grade.label}</td>
	<td width="284"  align="center" >${cl.specialty.label}</td>
	<td width="287"  align="center" >${cl.classes.label}</td>
</tr>
</#list>

</table>
<#else>
<table class="infotable" align="center">
<tr>
<td width="299" align="center" style="border-top: 0px;">年级</td>
<td width="284" align="center" style="border-top: 0px;">专业</td>
<td width="287" align="center" style="border-top: 0px;">班级</td>
</tr>
<tr>
	<td align="center" >&nbsp;</td>
	<td align="center" >&nbsp;</td>
	<td align="center" >&nbsp;</td>
</tr>
</table>
</#if>

<#if assistant.awards?? && assistant.awards?size gt 0>
<table class="infotable" align="center">
<tr>
<td width="299" align="center" style="border-top: 0px;">奖励情况</td>
<td width="284" align="center" style="border-top: 0px;">级别</td>
<td width="287" align="center" style="border-top: 0px;">获奖时间</td>
</tr>
<#list assistant.awards as award>
<tr>
	<td align="center" >${award.awards}</td>
	<td align="center" >${award.level}</td>
	<td align="center" ><#if award.timed??>${award.timed?string("yyyy年MM月dd日")}</#if></td>
</tr>
</#list>
</table>
<#else>
<table class="infotable" align="center">
<tr>
<td width="299" align="center" style="border-top: 0px;">奖励情况</td>
<td width="284" align="center" style="border-top: 0px;">级别</td>
<td width="287" align="center" style="border-top: 0px;">获奖时间</td>
</tr>
<tr>
	<td align="center" >&nbsp;</td>
	<td align="center" >&nbsp;</td>
	<td align="center" >&nbsp;</td>
</tr>
</table>
</#if>
</body>
