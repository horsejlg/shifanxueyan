<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>齐鲁师范学院 教师教育学院<#if evaluation.year??>${evaluation.year.label}<#else>${user.year.label}</#if>学生综合素质测评表</title>
<link rel="stylesheet" type="text/css" href="${base}/css/easyui/metro-gray/easyui.css">
<script type="text/javascript" src="${base}/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	.table {
		border: 2px #000 solid; 
		border-collapse: collapse;
		margin: 0px auto;
		width:973px;
	}
	
	.table td,.table th{
		border: 1px silver solid; 
		/*padding: 5px 10px;*/
		line-height: 1.5em;
	}
	
	.table th{
		/*letter-spacing: 5px;*/
		text-align: center;
	}
	
	.table td{
		font-size: 10px;
		/*letter-spacing: 2px;*/
	}
	.table tr{
		height: 2.5em;
	}
	
	.th{
		font-weight: lighter;
	}
	.table input, .table textarea{
		border: 0px;
		background: none;
		font-size: 14px;
		word-wrap: break-word; 
		resize:none;
		word-break: normal; 
	}	
	h1 input{
		border: 0px;
		background: none;
		font-size: 32px;
		text-align: center;
	}

</style>
</head>
<body>
<form id="evaluation1" action="${base}/evaluation1" method="post">
<div style="float: right;">填表日期：<#if evaluation.createTime??>${evaluation.createTime?string("yyyy年MM月dd日")}</#if></div>
<div align="center" style="width: 1200px;margin: 1em auto;">
	<h1>齐鲁师范学院 教师教育学院<#if evaluation.year?? || !edit>${evaluation.year.label}<input type="hidden" name="year" value="${evaluation.year.code}" /><#else><input class="easyui-combobox" id="year" name="year" data-options="
                    url:'${base}/dicts/year',
                    valueField:'code',
                    textField:'label',
                    value:'${user.year.code}', 
                    panelHeight:'auto',
                    width:100
                    "></#if>学生综合素质测评表</h1>
</div>
<table class="table" style="border-bottom: 0px;">
<colgroup>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
</colgroup>
<tr>
	<th colspan="1" align="center">学号</th>
	<td colspan="2" align="center">${evaluation.author.loginname}</td>
	<th colspan="1" align="center">姓名</th>
	<td colspan="2" align="center">${evaluation.author.nickName}</td>
	<th colspan="1" align="center">政治面貌</th>
	<td colspan="5" align="center">${evaluation.author.politics.label}</td>
</tr>
<tr>
	<th colspan="1" align="center">专业</th>
	<td colspan="2" align="center">${evaluation.author.specialty.label}</td>
	<th colspan="1" align="center">年级班级</th>
	<td colspan="2" align="center">${evaluation.author.classes.label}</td>
	<th colspan="1" align="center">职务</th>
	<td colspan="5" align="center">${evaluation.author.job.label}</td>
</tr>
<tr>
	<th colspan="12" align="center">基　础　性　素　质　测　评　（35分）</th>
</tr>
<tr>
	<th colspan="7" align="center">具体内容</th>
	<th>评分</th>
	<th colspan="4">根据具体内容填写</th>
</tr>
<tr>
	<td colspan="7" align="center">热爱祖国，关心国家大事，遵纪守法、遵守校规校纪 加3分</td>
	<td align="right"><#if edit><input type="text" name="baseSource1" class="easyui-numberbox base" data-options="min:0,max:3,precision:2,width:40,value:'${evaluation.baseSource1?string("#.##")}'" /><#else>${evaluation.baseSource1}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent1" value=evaluation.baseContent1 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark1" style="width: 100%">${evaluation.baseRemark1}</textarea><#else><p>${evaluation.baseRemark1?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">参加班会、政治学习全勤者加5分。缺席一次扣0.5分</td>
	<td align="right"><#if edit><input type="text" name="baseSource2" class="easyui-numberbox base" data-options="min:0,max:5,precision:2,width:40,value:'${evaluation.baseSource2?string("#.##")}'" /><#else>${evaluation.baseSource2}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent2" value=evaluation.baseContent2 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark2" style="width: 100%">${evaluation.baseRemark2}</textarea><#else><p>${evaluation.baseRemark2?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">学年满勤（含早操、晚自习及其他教学活动）者加5分，每旷一次扣0.5分</td>
	<td align="right"><#if edit><input type="text" name="baseSource3" class="easyui-numberbox base" data-options="min:0,max:5,precision:2,width:40,value:'${evaluation.baseSource3?string("#.##")}'" /><#else>${evaluation.baseSource3}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent3" value=evaluation.baseContent3 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark3" style="width: 100%">${evaluation.baseRemark3}</textarea><#else><p>${evaluation.baseRemark3?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">宿舍卫生合格，成员每人加5分，优秀宿舍每人次加0.5分，通报宿舍每人次扣0.5分</td>
	<td align="right"><#if edit><input type="text" name="baseSource4" class="easyui-numberbox base" data-options="min:0,max:5,precision:2,width:40,value:'${evaluation.baseSource4?string("#.##")}'" /><#else>${evaluation.baseSource4}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent4" value=evaluation.baseContent4 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark4" style="width: 100%">${evaluation.baseRemark4}</textarea><#else><p>${evaluation.baseRemark4?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">教室管理合格，成员每人加5分，管理良好每人次加0.5分，通报人员每人次扣0.5分</td>
	<td align="right"><#if edit><input type="text" name="baseSource5" class="easyui-numberbox base" data-options="min:0,max:5,precision:2,width:40,value:'${evaluation.baseSource5?string("#.##")}'" /><#else>${evaluation.baseSource5}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent5" value=evaluation.baseContent5 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark5" style="width: 100%">${evaluation.baseRemark5}</textarea><#else><p>${evaluation.baseRemark5?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">体育成绩合格、体质测试合格加5分。不合格此项分数为0</td>
	<td align="right"><#if edit><input type="text" name="baseSource6" class="easyui-numberbox base" data-options="min:0,max:5,precision:0,width:40,value:'${evaluation.baseSource6}'" /><#else>${evaluation.baseSource6}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent6" value=evaluation.baseContent6 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark6" style="width: 100%">${evaluation.baseRemark6}</textarea><#else><p>${evaluation.baseRemark6?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">校级“先进班集体”班级成员每人加4分，校级“先进团支部”班级成员每人加4分；省级“先进班集体”班级成员每人加6分；省级“先进团支部”班级成员每人加6分</td>
	<td align="right"><#if edit><input type="text" name="baseSource7" class="easyui-numberbox base" data-options="min:0,max:6,precision:0,width:40,value:'${evaluation.baseSource7}'" /><#else>${evaluation.baseSource7}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent7" value=evaluation.baseContent7 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark7" style="width: 100%">${evaluation.baseRemark7}</textarea><#else><p>${evaluation.baseRemark7?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">无故夜不归宿者每次扣3分</td>
	<td align="right"><#if edit><input type="text" name="baseSource8" class="easyui-numberbox base" data-options="min:0,max:35,precision:0,width:40,value:'${evaluation.baseSource8}'" /><#else>${evaluation.baseSource8}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent8" value=evaluation.baseContent8 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark8" style="width: 100%">${evaluation.baseRemark8}</textarea><#else><p>${evaluation.baseRemark8?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">本年度参加无偿献血加 2分（仅加一次）；爱心奉献、好人好事加4分（具有表彰凭证）</td>
	<td align="right"><#if edit><input type="text" name="baseSource9" class="easyui-numberbox base" data-options="min:0,max:4,precision:0,width:40,value:'${evaluation.baseSource9}'" /><#else>${evaluation.baseSource9}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent9" value=evaluation.baseContent9 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark9" style="width: 100%">${evaluation.baseRemark9}</textarea><#else><p>${evaluation.baseRemark9?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">受警告、严重警告、记过、留校察看处分者，基础性素质测评定为差，基础性素质测评成绩分为0；考试作弊受处分者，基础性素质测评定为差，基础性素质测评成绩为0</td>
	<td align="right"><#if edit><input type="text" name="baseSource10" class="easyui-numberbox base" data-options="min:0,max:1,precision:0,width:40,value:'${evaluation.baseSource10}'" /><#else>${evaluation.baseSource10}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent10" value=evaluation.baseContent10 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark10" style="width: 100%">${evaluation.baseRemark10}</textarea><#else><p>${evaluation.baseRemark10?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="center">本学年图书借阅量30本以上每人加5分，20本以上每人加3分,20本以下不得加分。（以图书馆统计为准）</td>
	<td align="right"><#if edit><input type="text" name="baseSource11" class="easyui-numberbox base" data-options="min:0,max:5,precision:0,width:40,value:'${evaluation.baseSource11}'" /><#else>${evaluation.baseSource11}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent11" value=evaluation.baseContent11 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark11" style="width: 100%">${evaluation.baseRemark11}</textarea><#else><p>${evaluation.baseRemark11?replace("\r","<br>")}</p></#if></td>
</tr>
<#-- 
<tr>
	<td colspan="7" align="left">通过全国计算机等级考试二级者加2分，三级者加3分；通过普通话等级考试者（二甲或二甲以上）加2分</td>
	<td align="right"><#if edit><input type="text" name="baseSource12" class="easyui-numberbox base" data-options="min:0,max:5,precision:1,width:40,value:'${evaluation.baseSource12}'" /><#else>${evaluation.baseSource12}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent12" value=evaluation.baseContent12 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark12" style="width: 100%">${evaluation.baseRemark12}</textarea><#else><p>${evaluation.baseRemark12}</p></#if></td>
</tr>
<tr>
	<td colspan="7" align="left">受警告、严重警告、记过、留校察看处分者，基础性素质测评定为差，基础性素质测评成绩分为0；考试作弊受处分者，基础性素质测评定为差，基础性素质测评成绩为0</td>
	<td align="right"><#if edit><input type="text" name="baseSource13" class="easyui-numberbox base" data-options="min:0,max:1,precision:0,width:40,value:'${evaluation.baseSource13}'" /><#else>${evaluation.baseSource13}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent13" value=evaluation.baseContent13 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark13" style="width: 100%">${evaluation.baseRemark13}</textarea><#else><p>${evaluation.baseRemark13}</p></#if></td>
</tr>
 -->
<tr>
	<th colspan="3">基础性素质测评成绩</th>
	<td colspan="3" align="right"><#if edit><input type="text" id="baseEvaluationSorce" name="baseEvaluationSorce" class="easyui-numberbox" data-options="min:0,max:35,precision:2,width:40,readonly:true,value:'${evaluation.baseEvaluationSorce?string("#.##")}'" /><#else>${evaluation.baseEvaluationSorce}</#if></td>
	<th colspan="3">基础性素质测评等级</th>
	<td colspan="3" align="center"><#if edit><input type="text" id="baseEvaluationLevel"  name="baseEvaluationLevel" class="easyui-combobox" data-options="data:[{label:'优',code:'4'},{label:'优',code:'3'},{label:'良',code:'2'},{label:'中',code:'1'},{label:'差',code:'0'}],width:40,readonly:true,valueField:'code',textField:'label',value:'${evaluation.baseEvaluationLevel}'" /><#else>${["差","中","良","优"][evaluation.baseEvaluationLevel]}</#if></td>
</tr>
<tr>
	<td colspan="12">注：基础性素质测评最高为35分。根据基础性素质成绩确定等级，等级分为“优”、“良”、“中”、“差”四等。成绩30分（含30分）以上评定等级为“优”；成绩20分-29分评定等级为“良”；成绩11分-19分评定等级为“中”；成绩0分-10分评定等级为“差”。所在学年评定等级为“中”、“差”者，取消各级、各类评优资格。</td>
</tr>
<tr>
	<th colspan="12">发　 展　性　素　 质　 测　 评　 （20分）</th>
</tr>
<tr>
	<th colspan="7" align="center">具体内容</th>
	<th>评分</th>
	<th colspan="4">根据实际情况填写支撑材料</th>
</tr>
<tr>
	<th class="th">社会实践<br/>（20分）</th>
	<td colspan="6" align="left">1、参加国家级、省级、校级的义工活动者（以证书为准），每人次分别加12分、8分、5分；参加旭升小学支教活动、敬老院慰问等志愿服务的学生由爱心社（或青协等志愿者组织）审核后每项加2分；假期参加社会公益活动的同学凭相关证书、材料加1分；<br/>
2、获国家、省、校表彰的社会实践团队、每人次分别为10分、6分、3分；获国家、省、校表彰的社会实践先进个人，每人次分别为12分、8分、5分；获国家、省、校、系（院）奖励的社会实践调查报告，每人次分别为8分、6分、4分、2分。上述项目，按最高项计分，不重复加分。<br/>
3、获国家、省、校、系（院）表彰的优秀社团，其主要成员（5人以内，由社团自行推选）；分值分别为6分、5分、4分、3分。获国家、省、校表彰的社团先进个人，分值分别为7分、6分、5分、4分。上述项目，按最高项计分，不重复加分。所有学生参加社团活动一次加1分，依次递增，社内正常开设的课程不加分，如：书画课、舞蹈课。
</td>
	<td align="right"><#if edit><input type="text" name="growSource1" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource1}'" /><#else>${evaluation.growSource1}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent1" value=evaluation.growContent1 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark1" style="width: 100%">${evaluation.growRemark1}</textarea><#else><p>${evaluation.growRemark1?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">社会工作<br/>（20分）</th>
	<td colspan="6" align="left">学生会在老师的监督下由主席团和各部门负责人根据相应规章制度和实际情况酌情加减分。<br/>
1、校学生会干部及成员<br/>
须由校学生会开具证明（不提供证明者不给予加分），<br/>
学生会主席团加0—8分；部长每人加0—7分；副部长每人加0—6分，干事每人加0-5分。<br/>
校社联、自管会、广播站加分细则同校学生会。<br/>
2、院级学生会干部<br/>
（1）院学生会主席团加0—8分；部长每人加0—7分；副部长每人加0—6分，干事每人加0-5分。<br/>
（2）社团联合会主席团干部加0--8分；各社团社长每人加0--6分，副社长每人加0—5分；部长加0-4分，副部长加0-3分；干事每人加0--2分。<br/>
3、学年末，由辅导员班主任为班委打分，加分分值为0—8分不等；<br/>
（1）辅导员助理加0--8分。<br/>
（2）各班班长、团支书加0--6分，副班长0—4分，及其他班委（含舍长）加0--2分。<br/>
可按照实际情况酌情加分。<br/>
注：上述项目可重复加分，具体分数由各级负责人协商决定，最终由教师教育学院全体辅导员老师审核，有权酌情加减分。<br/>

</td>
	<td align="right"><#if edit><input type="text" name="growSource2" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource2}'" /><#else>${evaluation.growSource2}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent2" value=evaluation.growContent2 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark2" style="width: 100%">${evaluation.growRemark2}</textarea><#else><p>${evaluation.growRemark2?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">科技学术<br/>（20分）</th>
	<td colspan="6" align="left">1、国家级、省级、校级、院系刊物发表文章10分、8分、6分、4分/篇。课题、论文参照相应级别加分（分值同上），第一作者加全分（其余作者根据顺序依次递减一分）按最高级别给与奖励，不重复奖励。<br/>
注：发表性文章指创作型文章，和本职工作相关的通讯稿文章不做发表类文章加分。<br/>
2、考试类：通过国家英语四级加4分、六级加6分；计算机二级加3分、三级加6分。其他专业技能需获劳动技术鉴定部门颁发或行业认定的各类专业技能资格证书可加2分。（获得后每年都加最高分）<br/>
3、学年总成绩位居所在班级前15%加10分，前30%加6分。<br/>
4、获得国家发明专利、获得国家实用新型和外观设计专利可加6分。<br/>
学生拥有科研成果或研制出产品，经高级职称专家推荐并通过校科研处审定，可加6分。<br/>
5、学生进行自主创业、创新活动并取得一定成果，可借助有效凭证加6分。<br/>
注：鉴定依据为有创业基地、拥有合作投资协议、实体店面或网络官方平台认定的虚拟店铺。
</td>
	<td align="right"><#if edit><input type="text" name="growSource3" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource3}'" /><#else>${evaluation.growSource3}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent3" value=evaluation.growContent3 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark3" style="width: 100%">${evaluation.growRemark3}</textarea><#else><p>${evaluation.growRemark3?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">竞赛活动<br/>（20分）</th>
	<td colspan="6" align="left">1、在国家级竞赛活动中，个人奖：一等奖加20分，二等奖加18分，三等奖加16分，优秀奖14分。集体奖：一等奖加18分，二等奖加16分，三等奖加14分，优秀奖12分<br/>
2、在省级竞赛活动中，个人奖：一等奖加16分，二等奖加14分，三等奖12分，优秀奖10分。集体奖：一等奖加14分，二等奖加12分，三等奖10分，优秀奖8分<br/>
3、在校级竞赛活动中，个人奖：一等奖加10分，二等奖加8分，三等奖6分，优秀奖4分。集体奖：一等奖加5分，二等奖加4分，三等奖3分，优秀奖2分<br/>
4、在院级竞赛活动中，个人奖：一等奖加8分，二等奖加6分，三等奖4分，优秀奖2分。集体奖：一等奖加4分，二等奖加3分，三等奖2分，优秀奖1分。<br/>
5、在各级各类文艺演出活动中，表演节目每人每次加2分，主持每人每次加3分<br/>
注：竞赛类活动含有：辩论赛、运动会、马拉松比赛、广播操、合唱比赛、球类比赛等。
</td>
	<td align="right"><#if edit><input type="text" name="growSource4" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource4}'" /><#else>${evaluation.growSource4}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent4" value=evaluation.growContent4 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark4" style="width: 100%">${evaluation.growRemark4}</textarea><#else><p>${evaluation.growRemark4?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">通报表扬、获得个人荣誉称号等加分<br/>（20分）</th>
	<td colspan="6" align="left">1.、校通报表扬者，每人次加4分，院通报表扬者，每人次加2分（有文件按文件加分）。<br/>
2、获得国家级、省级、校级和院级的个人荣誉（如各级各类奖学金和优秀党员、优秀学生干部、支教优秀青年志愿者、优秀心理健康工作者等荣誉称号）分别加16分、12分、8分、4分。
</td>
	<td align="right"><#if edit><input type="text" name="growSource5" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource5}'" /><#else>${evaluation.growSource5}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent5" value=evaluation.growContent5 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark5" style="width: 100%">${evaluation.growRemark5}</textarea><#else><p>${evaluation.growRemark5?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th colspan="4">发展性素质测评成绩</th>
	<td colspan="4" align="right"><#if edit><input type="text" id="growEvaluationSorce" name="growEvaluationSorce" class="easyui-numberbox" data-options="min:0,max:20,precision:2,width:40,value:'${evaluation.growEvaluationSorce?string("#.##")}',readonly:true"/><#else>${evaluation.growEvaluationSorce?string("#.#")}</#if></td>
	<td colspan="4" rowspan="5" align="left"><@m.filesupload label="编辑材料" name="growEvaluation" value=evaluation.growEvaluation edit=edit ></@m.filesupload></td>
</tr>
<tr>
	<td colspan="8" align="center">注：发展性素质测评成绩满分一百分，按照20%进行折合计算，最终成绩为满分20分（同一学年中，若该生获得两项国家级最高荣誉，则直接认定其发展性测评成绩为20分）。</th>
</tr>
<tr>
	<th colspan="3">学习成绩</th>
	<td align="right"><#if edit><input type="text" id="studySorce" name="studySorce" class="easyui-numberbox" data-options="min:0,max:100,precision:2,width:40,value:'${evaluation.studySorce?string("#.##")}'" /><#else>${evaluation.studySorce?string("#.##")}</#if></td>
	<th colspan="3">同年级同专业排名</th>
	<td align="right"><#if edit><input type="text" id="studyRanking" name="studyRanking" class="easyui-numberbox" data-options="min:0,max:2,precision:0,width:25,value:'${evaluation.studyRanking}',readonly:true" /><#else>${evaluation.studyRanking}</#if></td>
</tr>
<tr>
	<th colspan="8">综合测评成绩=基础性测评成绩+发展性素质测评成绩+学习成绩（45%）</th>
</tr>
<tr>
	<th colspan="3">综合测评成绩</th>
	<td align="right"><#if edit><input type="text" name="sumSorce" id="sumSorce"  class="easyui-numberbox" data-options="min:0,precision:2,width:40,value:'${evaluation.sumSorce?string("#.##")}',readonly:true" /><#else>${evaluation.sumSorce?string("#.##")}</#if></td>
	<th colspan="3">同年级同专业排名</th>
	<td align="right"><#if edit><input type="text" id="gsIndex" name="gsIndex"  class="easyui-numberbox" data-options="min:0,precision:0,width:40,value:'${evaluation.gsIndex},readonly:true'" /><#else>${evaluation.gsIndex}</#if></td>
</tr>
</table>
<table class="table" style="border-top:0px;">
<colgroup>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
	<col width="80px"/>
</colgroup>
<tr>
	<th colspan="12" style="border-top:0px;">学生自我评价总结</th>
</tr>
<tr>
	<td colspan="12">
	<div align="left">自我评价：</div>
	<div style="min-height:112px;"><#if edit && evaluation.status=0 ><textarea name="selfEvaluation"  rows="4" style="width:95%" >${evaluation.selfEvaluation}</textarea><#else>${evaluation.selfEvaluation?replace("\r","<br>")}</#if></div>
	<div>以上测评内容、成绩真实，个人无异议</div>
	<div align="right"><#if evaluation.selfEvaluationDate?? && evaluation.selfEvaluation!="">${evaluation.selfEvaluationDate?string("yyyy年MM月dd日")}</#if></div>
	</td>
</tr>
<tr>
	<th colspan="12">学院综合测评工作小组审核意见</th>
</tr>
<tr>
	<td colspan="12">
	<div style="height:112px;"><#if evaluation.status==2 && edit ><textarea name="selfEvaluation"  rows="4" style="width:95%" >${evaluation.groupEvaluation}</textarea><#else>${evaluation.groupEvaluation?replace("\r","<br>")}</#if></div>
	<div>&nbsp;</div>
	<div align="right"><#if evaluation.groupEvaluationDate?? && evaluation.groupEvaluation!="">${evaluation.groupEvaluationDate?string("yyyy年MM月dd日")}</#if></div>
	</td>
</tr>

</table>
<input type="hidden" name="id" value="${evaluation.id}" />
<input type="hidden" name="author" value="${evaluation.author.id}" />
<input type="hidden" id="status" name="status" value="${evaluation.status}" />
</form>
<#if edit>
<div style="text-align:center;padding:5px 0" id="buttons">
	<#if evaluation.status==0>
            <a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_submit" on_click="$('#evaluation1').form('submit')" style="width:80px">保存并上交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_save" on_click="$('#evaluation1').form('submit')" style="width:120px">保存暂不上交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.location.reload()" style="width:80px">撤销修改</a>
    <#elseif evaluation.status==1>
    	<a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_submit"  style="width:80px">审核通过</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_nosubmit"  style="width:40px">退回</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_save"  style="width:80px">保存修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.location.reload()" style="width:80px">撤销修改</a>
    <#elseif evaluation.status==2>
    	<a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_submit"  style="width:80px">审核通过</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_nosubmit"  style="width:140px">退回到班级审核</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_save"  style="width:80px">保存修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.location.reload()" style="width:80px">撤销修改</a>
    </#if>
        </div>
<@m.filesuploadScript title="材料管理" label="材料列表"></@m.filesuploadScript>
</body>
<script>
$(function(){
	/*$('#evaluation1').form({
		success:function(data){
			$.messager.alert('Info', data, 'info');
		},
		onsubmit:function(data){
		
		}
	});*/
	$('.base').each(function(){
			$(this).numberbox({"onChange":function(newValue, oldValue){
				if(newValue==""){
					$(this).numberbox("setValue",0);
				}else{
					var count = 0;
					$('.base').each(function(){
						var name = $(this).context.attributes['textboxname'].textContent;
						if(name=='baseSource10' && $(this).val()!='0'){
						count=-100;
						}else if(name=='baseSource8'){
							count -= parseFloat($(this).val())
						}else{
							count += parseFloat($(this).val())
						}
						});
					if(count<0)count=0;
					$("#baseEvaluationSorce").numberbox("setValue", count);
					$("#baseEvaluationLevel").combobox("select", ""+Number(Number($("#baseEvaluationSorce").numberbox("getValue"))/10));
					sumAll();
				}
			}});
	})
	$('.grow').each(function(){
			$(this).numberbox({"onChange":function(newValue, oldValue){
	if(newValue==""){
					$(this).numberbox("setValue",0);
				}else{
			var count = 0;
			$('.grow').each(function(){
				count += Number($(this).numberbox("getValue"));
			});
				$("#growEvaluationSorce").numberbox("setValue", count/5);
				sumAll();
			}
			}});
	});
	$('#studySorce').numberbox({"onChange":function(newValue,oldValue){
	if(newValue==""){
		$('#studySorce').numberbox("setValue", 0);
	}else{
		$.get("/evaluation/index/Evaluation1/studySorce/${evaluation.author.grade.code}/<#if evaluation.year??>${evaluation.author.year.code}<#else>"+$("#year").combobox("getValue")+"</#if>/${evaluation.author.specialty.code}/${evaluation.author.classes.code}/"+$("#studySorce").numberbox("getValue"),function(data){
			$("#studyRanking").numberbox("setValue", data);
		});
		sumAll();
		}
	}});
/*	$('.base').numberbox("change",function(){
	var count = 0;
		$('#baseEvaluationSorce').val(count);
	});*/
	var toSave = function(){
	$.messager.progress({
                title:'请稍等',
                msg:'正在保存...'
            });
		var fromDate = $("#evaluation1").serializeArray();
		var data = {};
		$(fromDate).each(function(){
		switch (this.name){
		case "author":
			data[this.name]={id:this.value}
			break;
		case "year":
			data[this.name]={code:this.value}
        	break;
		default:
			data[this.name]=this.value;
		}});
		$.ajax({
	url:"${base}/evaluation1",
	method:"post",
	contentType: "application/json; charset=utf-8",
	data:JSON.stringify(data),
	dataType: "json",
	success: function (data) {
		if(data){
			$.messager.progress('close');
			$.messager.alert('信息','综合素质测评表修改成功','info',function(){window.location.reload()});
		}
	},
	error:function (data) {
		$.messager.progress('close');
		$.messager.alert('错误','综合素质测评表保存失败，请尝试重新提交！','error');
	}
});
	};
	function sumAll(){
		$("#sumSorce").numberbox("setValue",parseFloat($("#growEvaluationSorce").numberbox("getValue"))+parseFloat($("#baseEvaluationSorce").numberbox("getValue"))+(parseFloat($("#studySorce").numberbox("getValue"))*.45));
		$.get("/evaluation/index/Evaluation1/sumSorce/${evaluation.author.grade.code}/<#if evaluation.year??>${evaluation.author.year.code}<#else>"+$("#year").combobox("getValue")+"</#if>/${evaluation.author.specialty.code}/${evaluation.author.classes.code}/"+$("#sumSorce").numberbox("getValue"),function(data){
			$("#gsIndex").numberbox("setValue", data);
		});
	}
	
	$('#evaluation_submit').click(function(){
		$('#status').val('${evaluation.status+1}');
		toSave();
	});
	$('#evaluation_nosubmit').click(function(){
		$('#status').val('${evaluation.status-1}');
		toSave();
	});
	$('#evaluation_save').click(toSave);
	});
</script>
</#if>
</html>