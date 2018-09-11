<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>齐鲁师范学院 教师教育学院<#if evaluation.year??>${evaluation.year.label}<#else>${user.year.label}</#if>学生综合素质测评表</title>
<link rel="stylesheet" type="text/css" href="${base}/js/easyui/themes/metro/easyui.css">
<script type="text/javascript" src="${base}/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
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
<form id="evaluation2" action="${base}/evaluation2" method="post">
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
	<th class="th">爱国守法
<br/>（20分）</th>
	<td colspan="6" align="left">1、热爱祖国，拥护党的领导；得5分；<br>
2、遵纪守法。遵守法律法规，无违法行为者，得3分；<br>
3、理性爱国，以合法方式理性地表达爱国诉求，得3分；<br>
4、维护国家尊严与声誉，敢于同有损于祖国、国格的言行作斗争，得2分；<br>
5、关心国家大事，为祖国和家乡的建设尽义务，得2分；<br>
6、学习了解中华民族的优良传统，继承与发扬中华优秀传统文化，得3分；<br>
7、积极参加学校组织的普法活动；积极参加消防及安全知识的学习和培训，2分
</td>
	<td align="right"><#if edit><input type="text" name="baseSource1" class="easyui-numberbox base" data-options="min:0,max:20,precision:2,width:40,value:'${evaluation.baseSource1?string("#.##")}'" /><#else>${evaluation.baseSource1}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent1" value=evaluation.baseContent1 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark1" style="width: 100%">${evaluation.baseRemark1}</textarea><#else><p>${evaluation.baseRemark1?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">热爱集体<br/>（20分）</th>
	<td colspan="6" align="left">关爱集体，爱护公物，节约水电，得2分；<br>
2、积极树立团结意识，乐于助人，团结互助，服务他人，尊重他人与他人和谐相处，得4分；宿舍关系不和谐，宿舍全体成员减2分；申请调宿舍者减3分。<br>
3、坚持社会主义集体主义，个人利益要服从集体利益，得2分；<br>
4、积极参加集体活动，具有集体荣誉感，得2分；未参加集体活动者，每次减1分；<br>
5、宿舍卫生合格，成员每人加4分，优秀宿舍每人次加0.5分，通报宿舍每人次扣0.5分；<br>
6、教室卫生合格，班级每人加4分，优秀班级每人次加1分，通报班级每人次扣0.5分
</td>
	<td align="right"><#if edit><input type="text" name="baseSource2" class="easyui-numberbox base" data-options="min:0,max:20,precision:2,width:40,value:'${evaluation.baseSource2?string("#.##")}'" /><#else>${evaluation.baseSource2}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent2" value=evaluation.baseContent2 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark2" style="width: 100%">${evaluation.baseRemark2}</textarea><#else><p>${evaluation.baseRemark2?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">社会公益<br/>（15分）</th>
	<td colspan="6" align="left">1、参加国家级、省级、校级的义工活动者（以证书为准），每人次分别加12分、8分、5分；参加支教活动、敬老院慰问、福利院、帮扶结对、无偿献血等志愿服务的学生由爱心社（或青协等志愿者组织）审核后每项加2分；假期参加社会公益活动的同学凭相关证书、材料加2分，累计不超过12分；<br>
2、有见义勇为、同违法分子作斗争的行为，凭相关证明，加5分；<br>
3、积极参加社会实践，具有积极的社会服务和奉献意识，加3分；
</td>
	<td align="right"><#if edit><input type="text" name="baseSource3" class="easyui-numberbox base" data-options="min:0,max:15,precision:2,width:40,value:'${evaluation.baseSource3?string("#.##")}'" /><#else>${evaluation.baseSource3}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent3" value=evaluation.baseContent3 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark3" style="width: 100%">${evaluation.baseRemark3}</textarea><#else><p>${evaluation.baseRemark3?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">学科德育<br/>（20分）</th>
	<td colspan="6" align="left">1、热爱学习，学习态度端正，勤奋刻苦，此项加5分；<br>
2、严守考纪，诚信考试，杜绝作弊，真实地反映自己的学习成绩，得5分；<br>
3、爱护教学、科研设备，得3分；<br>
4、尊重教师劳动，积极参与教学活动，得2分；<br>
5、体育成绩合格、体质测试合格加5分；不合格此项分数为0；<br>
6、学生手册考试不合格者扣2分；
</td>
	<td align="right"><#if edit><input type="text" name="baseSource4" class="easyui-numberbox base" data-options="min:0,max:20,precision:2,width:40,value:'${evaluation.baseSource4?string("#.##")}'" /><#else>${evaluation.baseSource4}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent4" value=evaluation.baseContent4 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark4" style="width: 100%">${evaluation.baseRemark4}</textarea><#else><p>${evaluation.baseRemark4?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">日常行为<br/>（25分）</th>
	<td colspan="6" align="left">1、严格遵守学校作息制度，按时作息，生活有规律，生活方式健康，积极参加有益于身心健康的各种集体活动，营造风朗气晴的网络环境，得3分；<br>
2、自尊自爱，注重仪表，行为举止文明，男女交往公共场合举止得体，得3分；<br>
3、热爱家庭，有家庭责任意识，孝敬父母，得3分；<br>
4、不使用违规电器，不出现违规现象，宿舍内禁烟禁酒，禁止带食物进入学习场所，得3分；发现一次扣0.5分；<br>
5、有强烈的纪律观念，自觉遵守校内各项纪律；自觉抵制各种违纪行为，得3分。<br>
6、学年满勤（含早操、晚自习及其他教学活动）者加5分，每旷一次扣0.5分<br>
7、参加班会、政治学习全勤者加5分。缺席一次扣0.5分<br>
8、错误观点坚决反对，不信谣不传谣，不造谣
</td>
	<td align="right"><#if edit><input type="text" name="baseSource5" class="easyui-numberbox base" data-options="min:0,max:25,precision:2,width:40,value:'${evaluation.baseSource5?string("#.##")}'" /><#else>${evaluation.baseSource5}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="baseContent5" value=evaluation.baseContent5 edit=edit ></@m.filesupload><#if edit><textarea name="baseRemark5" style="width: 100%">${evaluation.baseRemark5}</textarea><#else><p>${evaluation.baseRemark5?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">一票否决</th>
	<td colspan="6" align="left">1、违法乱纪，具有严重政治问题者；<br>
2、无视学校各项规章制度，受警告、严重警告、记过、留校察看处分者；<br>
3、通过不良手段（作弊、协助他人作弊等）获得学年所修课程成绩者；<br>
4、通过网络散布谣言，造成不良影响者；
</td>
	<td align="right"><#if edit><input type="text" id="vetoSource" name="vetoSource" class="easyui-checkbox" value="true" <#if evaluation.vetoSource>checked</#if>" /><#else><#if evaluation.vetoSource>是<#else>否</#if></#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="vetoContent" value=evaluation.vetoContent edit=edit ></@m.filesupload><#if edit><textarea name="vetoRemark" style="width: 100%">${evaluation.vetoRemark}</textarea><#else><p>${evaluation.vetoRemark?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th colspan="3">基础性素质测评成绩</th>
	<td colspan="9" align="right"><#if edit><input type="text" id="baseEvaluationSorce" name="baseEvaluationSorce" class="easyui-numberbox" data-options="min:0,max:35,precision:2,width:40,readonly:true,value:'${evaluation.baseEvaluationSorce?string("#.##")}'" /><#else>${evaluation.baseEvaluationSorce}</#if></td>
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
	<td colspan="6" align="left">获国家、省、校表彰的社会实践团队、每人次分别为10分、6分、3分；获国家、省、校表彰的社会实践先进个人，每人次分别为12分、8分、5分；获国家、省、校、系（院）奖励的社会实践调查报告，每人次分别为8分、6分、4分、2分。上述项目，按最高项计分，不重复加分。<br>
2、获国家、省、校、系（院）表彰的优秀社团，其主要成员（5人以内，由社团自行推选）；分值分别为6分、5分、4分、3分。获国家、省、校表彰的社团先进个人，分值分别为7分、6分、5分、4分。上述项目，按最高项计分，不重复加分。所有学生参加社团活动一次加1分，依次递增，社内正常开设的课程不加分，如：书画课、舞蹈课。
</td>
	<td align="right"><#if edit><input type="text" name="growSource1" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource1}'" /><#else>${evaluation.growSource1}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent1" value=evaluation.growContent1 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark1" style="width: 100%">${evaluation.growRemark1}</textarea><#else><p>${evaluation.growRemark1?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">社会工作<br/>（30分）</th>
	<td colspan="6" align="left">学生会在老师的监督下由主席团和各部门负责人根据相应规章制度和实际情况酌情加减分。<br>
1、校学生会干部及成员<br>
须由校学生会开具证明（不提供证明者不给予加分），学生会主席团加0—10分；部长每人加0—7分；副部长每人加0—6分，干事每人加0-5分；校社联、自管会、广播站加分细则同校学生会；<br>
2、院级学生会干部<br>
（1）院学生会主席团加0—8分；部长每人加0—7分；副部长每人加0—6分，干事每人加0-5分；<br>
（2）社团联合会主席团干部加0--8分；各社团社长每人加0--6分，副社长每人加0—5分；部长加0-4分，副部长加0-3分；干事每人加0--2分；<br>
3、学年末，由辅导员班主任为班委打分，加分分值为0—8分不等；<br>
（1）辅导员助理加0--8分；<br>
（2）各班班长、团支书加0--6分，副班长0—4分，及其他班委（含舍长）加0--2分；<br>
可按照实际情况酌情加分。<br>
注：上述项目可重复加分，具体分数由各级负责人协商决定，最终由教师教育学院全体辅导员老师审核，有权酌情加减分；<br>

</td>
	<td align="right"><#if edit><input type="text" name="growSource2" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource2}'" /><#else>${evaluation.growSource2}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent2" value=evaluation.growContent2 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark2" style="width: 100%">${evaluation.growRemark2}</textarea><#else><p>${evaluation.growRemark2?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">科技学术<br/>（10分）</th>
	<td colspan="6" align="left">1、国家级、省级、校级、院系刊物发表文章10分、8分、6分、4分/篇。课题、论文参照相应级别加分（分值同上），第一作者加全分（其余作者根据顺序依次递减一分）按最高级别给与奖励，不重复奖励；<br>
注：发表性文章指创作型文章，和本职工作相关的通讯稿文章不做发表类文章加分。<br>
2、获得国家发明专利、获得国家实用新型和外观设计专利可加6分。<br>
学生拥有科研成果或研制出产品，经高级职称专家推荐并通过校科研处审定，可加6分；<br>
3、学生进行自主创业、创新活动并取得一定成果，可借助有效凭证加6分。<br>
4、积极参加挑战杯比赛，进入省级比赛者加10分；进入校级竞赛者加6分；<br>
注：鉴定依据为有创业基地、拥有合作投资协议、实体店面或网络官方平台认定的虚拟店铺；

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
	<td colspan="6" align="left">1、校通报表扬者，每人次加4分，院通报表扬者，每人次加2分（有文件按文件加分）；<br>
2、获得国家级、省级、校级和院级的个人荣誉（如各级各类奖学金和优秀党员、优秀学生干部、支教优秀青年志愿者、优秀心理健康工作者等荣誉称号）分别加16分、12分、8分、4分；<br>
2、校级“优秀学生干部”“优秀学生”“优秀团员”“优秀团干部”每人加2分；省级“优秀学生干部”“优秀学生”“优秀团员”“优秀团干部”每人加3分；校级“先进班集体”班级成员每人加1分，校级“先进团支部”班级成员每人加1分；省级“先进班集体”班级成员每人加3分；省级“先进团支部”班级成员每人加3分；<br>
3、技能展示（黑板字、毛笔字、硬笔字、简笔画等），一经展示，每次加2分；以《学习部展览作品名单》为准；
</td>
	<td align="right"><#if edit><input type="text" name="growSource5" class="easyui-numberbox grow" data-options="min:0,max:20,precision:0,width:40,value:'${evaluation.growSource5}'" /><#else>${evaluation.growSource5}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="growContent5" value=evaluation.growContent5 edit=edit ></@m.filesupload><#if edit><textarea name="growRemark5" style="width: 100%">${evaluation.growRemark5}</textarea><#else><p>${evaluation.growRemark5?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th colspan="8">凡是获得一项（及以上）荣誉、在省级（及以上）刊物发表作品的学生，发展性素质测评成绩为满分</th>
	<td colspan="4" align="right"><#if edit><input type="text" id="growFull" name="growFull" class="easyui-checkbox" value="true" <#if evaluation.growFull>checked</#if>/><#else>${evaluation.growFull}</#if></td>
</tr>
<tr>
	<th colspan="4">发展性素质测评成绩</th>
	<td colspan="4" align="right"><#if edit><input type="text" id="growEvaluationSorce" name="growEvaluationSorce" class="easyui-numberbox" data-options="min:0,max:20,precision:2,width:40,value:'${evaluation.growEvaluationSorce?string("#.##")}',readonly:true"/><#else>${evaluation.growEvaluationSorce?string("#.#")}</#if></td>
	<td colspan="4" align="left"><@m.filesupload label="编辑材料" name="growEvaluation" value=evaluation.growEvaluation edit=edit ></@m.filesupload></td>
</tr>
<tr>
	<th colspan="12">学    业   成   绩  （60分）</th>
</tr>
<tr>
	<th colspan="7" align="center">具体内容</th>
	<th>评分</th>
	<th colspan="4">根据实际情况填写支撑材料</th>
</tr>
<tr>
	<th class="th">学习成绩</th>
	<td colspan="6" align="left">
</td>
	<td align="right"><#if edit><input type="text" id="studySorce" name="studySorce" class="easyui-numberbox" data-options="min:0,max:100,precision:2,width:40,value:'${evaluation.studySorce?string("#.##")}'" /><#else>${evaluation.studySorce?string("#.##")}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="studyContent" value=evaluation.studyContent edit=edit ></@m.filesupload><#if edit><textarea name="studyRemark" style="width: 100%">${evaluation.studyRemark}</textarea><#else><p>${evaluation.studyRemark?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th class="th">其他</th>
	<td colspan="6" align="left">1、学年总成绩位居所在班级前10%加30分，前20%加20分，前30%加10分；
2、本学年图书借阅量30本以上每人加10分，20本以上每人加6分,20本以下不得加分；
3、通过普通话等级考试者（二甲或二甲以上）加5分
4、通过国家英语四级加10分、六级加20分；通过托福、雅思者加30分；计算机二级加10分、三级加15分；
5、获得教师资格证书加10分；其他专业技能需获劳动技术鉴定部门颁发或行业认定的各类专业技能资格证书可加10分；
</td>
	<td align="right"><#if edit><input type="text" id="otherSource" name="otherSource" class="easyui-numberbox" data-options="min:0,max:100,precision:2,width:40,value:'${evaluation.otherSource}'" /><#else>${evaluation.otherSource}</#if></td>
	<td colspan="4"><@m.filesupload label="编辑材料" name="otherContent" value=evaluation.otherContent edit=edit ></@m.filesupload><#if edit><textarea name="otherRemark" style="width: 100%">${evaluation.growRemark5}</textarea><#else><p>${evaluation.otherRemark?replace("\r","<br>")}</p></#if></td>
</tr>
<tr>
	<th colspan="6">学业成绩=【（学习成绩×80%）+（其他×20%）】×60%</th>
	<td colspan="1">得分</td>
	<td colspan="5" align="right"><#if edit><input type="text" id="studySum" name="studySum" class="easyui-numberbox" data-options="min:0,max:100,precision:2,width:40,value:'${evaluation.studySum?string("#.##")}',readonly:true" /><#else>${evaluation.studySorce?string("#.##")}</#if></td>
</tr>
<tr>
	<th colspan="12">综合测评成绩=基础性测评成绩+发展性素质测评成绩+学业成绩</th>
</tr>
<tr>
	<th colspan="3">综合测评成绩</th>
	<td colspan="3" align="right"><#if edit><input type="text" name="sumSorce" id="sumSorce"  class="easyui-numberbox" data-options="min:0,max:100,precision:2,width:40,value:'${evaluation.sumSorce?string("#.##")}',readonly:true" /><#else>${evaluation.sumSorce?string("#.##")}</#if></td>
	<th colspan="3">总排名</th>
	<td colspan="3" align="right"><#if edit><input type="text" id="gsIndex" name="gsIndex"  class="easyui-numberbox" data-options="min:0,precision:0,width:40,value:'${evaluation.gsIndex},readonly:true'" /><#else>${evaluation.gsIndex}</#if></td>
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
<@m.filesuploadScript title="材料管理" label="材料列表"></@m.filesuploadScript>
<#if edit>
<div style="text-align:center;padding:5px 0" id="buttons">
	<#if evaluation.status==0>
            <a href="javascript:void(0)" class="easyui-linkbutton" id="evaluation_submit" on_click="$('#evaluation1').form('submit')" style="width:100px">保存并上交</a>
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
</body>
<script>

$(function(){

	$("#vetoSource").checkbox({"onChange":function(checked){
		var count = 0;
		if(!checked){
			$('.base').each(function(){
				count += parseFloat($(this).val());
			});
		}
		$("#baseEvaluationSorce").numberbox("setValue",count/5);
		sumAll();
	}});
	
	$("#growFull").checkbox({"onChange":function(checked){
		var count = 0;
		if(checked){
			count = 100;
		}else{
			$('.grow').each(function(){
				count += Number($(this).numberbox("getValue"));
			});
		}
		$("#growEvaluationSorce").numberbox("setValue", count/5);
		sumAll();
	}});
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
						count += parseFloat($(this).val());
					});
					if($("#vetoSource").attr('checked'))count=0;
					$("#baseEvaluationSorce").numberbox("setValue", count/5);
					//$("#baseEvaluationLevel").combobox("select", ""+Number(Number($("#baseEvaluationSorce").numberbox("getValue"))/10));
					sumAll();
				}
		}});
	})
	$('.grow').each(function(){
			$(this).numberbox({"onChange":function(newValue, oldValue){
	if(newValue==""){
					$(this).numberbox("setValue",0);
				}
			var count = 0;
			$('.grow').each(function(){
				count += Number($(this).numberbox("getValue"));
			});
			if($("#growFull").attr("checked"))count=100
				$("#growEvaluationSorce").numberbox("setValue", count/5);
				sumAll();
			}});
	});
	$('#studySorce').numberbox({"onChange":function(newValue,oldValue){
	if(newValue==""){
		$('#studySorce').numberbox("setValue", 0);
	}else{
		sumAll();
		}
	}});
	
	$('#otherSource').numberbox({"onChange":function(newValue,oldValue){
	if(newValue==""){
		$('#otherSource').numberbox("setValue", 0);
	}else{
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
		var fromDate = $("#evaluation2").serializeArray();
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
		data["growFull"]=$("#growFull").attr("checked");
		data["vetoSource"]=$("#vetoSource").attr('checked');
		$.ajax({
	url:"${base}/evaluation2",
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
		$("#studySum").numberbox("setValue",(parseFloat($("#studySorce").numberbox("getValue"))*0.8+parseFloat($("#otherSource").numberbox("getValue"))*0.2)*0.6);
		$("#sumSorce").numberbox("setValue",parseFloat($("#growEvaluationSorce").numberbox("getValue"))+parseFloat($("#baseEvaluationSorce").numberbox("getValue"))+parseFloat($("#studySum").numberbox("getValue")));
		var sum = $("#sumSorce").numberbox("getValue")
		$.get("/evaluation/index/Evaluation2/sumSorce/${evaluation.author.grade.code}/<#if evaluation.year??>${evaluation.author.year.code}<#else>"+$("#year").combobox("getValue")+"</#if>/${evaluation.author.specialty.code}/${evaluation.author.classes.code}/"+
		sum
		,function(data){
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