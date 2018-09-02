<style type="text/css">
 .infotable{
 	border-collapse: collapse;
 }
 .infotable td{
 	border: 1px silver solid;
 	height: 1.5em;
 	padding: 3px;
 }
</style>
<table class="infotable" align="center" style="border-bottom: 0px">
<tr>
	<td width="100" align="right">姓名</td>
	<td width="240" align="left">${student.user.nickName}</td>
	<td width="100"  align="right">学号</td>
	<td width="240" align="left">${student.user.loginname}</td>
	<td width="160" rowspan="9" align="center" style="border-bottom: 0px"><img src="" width="120" height="160" style="border: 1px silver solid ;"></td>
</tr>
<tr>
	<td align="right">出生年月</td>
	<td ><#if student.birthday??>${student.birthday?string("yyyy年MM月dd日")}</#if></td>
	<td align="right">民族</td>
	<td ><#if student.nation??>${student.nation.label}</#if></td>
</tr>
<tr>
	<td align="right">身份证号</td>
	<td>${student.idCode}</td>
	<td align="right">籍贯</td>
	<td ><#if student.birthplace??>${student.birthplace.label}</#if></td>
</tr>
<tr>
	<td align="right">家庭住址</td>
	<td >${student.address}</td>
	<td align="right">家庭经济情况</td>
	<td >${student.economy}</td>
</tr>
<tr>
	<td align="right">受助档次</td>
	<td ><#if student.grantee??>${student.grantee.label}</#if></td>
	<td align="right">政治面貌</td>
	<td ><#if student.politics??>${student.politics.label}</#if></td>
</tr>
<tr>
	<td align="right">专业</td>
	<td ><#if student.user.specialty??>${student.user.specialty.label}</#if></td>
	<td align="right">班级</td>
	<td ><#if student.user.classes??>${student.user.classes.label}</#if></td>
</tr>
<tr>
	<td align="right">班级职务</td>
	<td ><#if student.user.job??>${student.user.job.label}</#if></td>
	<td align="right">班外职务</td>
	<td ><#if student.outJob??>${student.outJob.label}</#if></td>
</tr>
<tr>
	<td align="right">参加的社团</td>
	<td >${student.community}</td>
	<td align="right">宿舍号</td>
	<td >${student.dormCode}</td>
</tr>
<tr>
	<td align="right" style="border-bottom: 0px">联系电话</td>
	<td style="border-bottom: 0px">${student.phone}</td>
	<td align="right" style="border-bottom: 0px">QQ/微信/微博</td>
	<td style="border-bottom: 0px">${student.qq}</td>
</tr>
</table>
<table class="infotable" align="center">
	<tr>
		<td width="120" align="right" >入党时间</td>
		<td width="160" align="left" ><#if student.joinParty??>${student.joinParty?string("yyyy年MM月dd日")}</#if></td>
		<td width="117" align="right" >入团时间</td>
		<td width="160" align="left" ><#if student.joinUs??>${student.joinUs?string("yyyy年MM月dd日")}</#if></td>
		<td width="120" align="right" >性别</td>
		<td width="160" align="left" ><#if student.sex = 'f'>男<#elseif student.sex = 'm'>女</#if></td>
	</tr>
</table>
<#if student.sociograms?? && student.sociograms?size gt 0>
<table class="infotable" align="center">
	<tr>
		<td width="120" align="center" rowspan="${student.sociograms?size + 1}" style="border-top: 0px;">主要社会关系</td>
		<td width="160" align="center" style="border-top: 0px;">姓名</td>
		<td width="200" align="center" style="border-top: 0px;">与本人关系</td>
		<td width="204" align="center" style="border-top: 0px;">工作单位</td>
		<td width="160" align="center" style="border-top: 0px;">联系方式</td>
	</tr>
	<#list student.sociograms as sociogram>
	<tr>
		<td align="center" >${sociogram.name}</td>
		<td align="center" >${sociogram.relation}</td>
		<td align="center" >${sociogram.unit}</td>
		<td align="center" >${sociogram.phone}</td>
	</tr>
	</#list>
</table>
<#else>
<table class="infotable" align="center">
	<tr>
		<td width="120" align="center" rowspan="2" style="border-top: 0px;">主要社会关系</td>
		<td width="160" align="center" style="border-top: 0px;">姓名</td>
		<td width="200" align="center" style="border-top: 0px;">与本人关系</td>
		<td width="204" align="center" style="border-top: 0px;">工作单位</td>
		<td width="160" align="center" style="border-top: 0px;">联系方式</td>
	</tr>
	<tr>
		<td align="center" >&nbsp;</td>
		<td align="center" >&nbsp;</td>
		<td align="center" >&nbsp;</td>
		<td align="center" >&nbsp;</td>
	</tr>
</table>
</#if>
<#if student.awards?? && student.awards?size gt 0>
<table class="infotable" align="center">
<tr>
<td width="287" align="center" style="border-top: 0px;">奖励情况</td>
<td width="284" align="center" style="border-top: 0px;">级别</td>
<td width="287" align="center" style="border-top: 0px;">获奖时间</td>
</tr>
<#list student.awards as award>
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
<td width="287" align="center" style="border-top: 0px;">奖励情况</td>
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