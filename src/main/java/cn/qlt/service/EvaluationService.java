package cn.qlt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.EvaluationDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Dict;
import cn.qlt.domain.Evaluation;
import cn.qlt.domain.Evaluation1;
import cn.qlt.domain.Evaluation2;
import cn.qlt.domain.User;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

@Service
public class EvaluationService {
	
	@Autowired
	private EvaluationDao evaluationDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DictService dictService;

	//@PostConstruct
	//@Scheduled(cron="0 0 0,6,12,18 * * ?")
	public void reloadEvaluation1Index(){
		List<Dict> years = dictService.getDictForType("year");
		List<Dict> specialtys = dictService.getDictForType("specialty");
		List<Dict> grades = dictService.getDictForType("grade");
		for(Dict year:years){
			for(Dict specialty:specialtys){
				for( Dict grade:grades){
					List<Evaluation> list = evaluationDao.find("from Evaluation2 where status BETWEEN 1 AND 3 and year.code = ? and author.specialty.code=? and author.grade.code=? order by studySorce desc", year.getCode(), specialty.getCode(), grade.getCode());
					for(int i=0;i<list.size();i++){
						Evaluation1 e = (Evaluation1) (list.get(i));
						e.setStudyRanking(i+1);
					}
					evaluationDao.save(list);
					list = evaluationDao.find("from Evaluation2 where status BETWEEN 1 AND 3 and year.code = ? and author.specialty.code=? and author.grade.code=? order by sumSorce desc", year.getCode(), specialty.getCode(), grade.getCode());
					for(int i=0;i<list.size();i++){
						list.get(i).setGsIndex(i+1);
					}
					evaluationDao.save(list);
				}
			}
		}
	}

	
	@Transactional
	public PageResult find(Map<String,String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "from [table] where 1=1 /~issubmit: and status > 0 ~//~authorId: and author.id = {authorId}~//~nickName: and author.nickName like '%[nickName]%'~//~grade: and author.grade.code = {grade} ~//~specialty: and author.specialty.code = {specialty} ~//~classes: and author.classes.code = {classes} ~//~year: and year.code = {year} ~//~status: and status =[status]~/", params, evaluationDao);
		return result;
	}
	
	public List<Evaluation> findByStatus(int status){
		return evaluationDao.findByStatus(status);
	}
	
	/**
	 * @param id
	 * @param status
	 */
	public void updateStatus(String id,int status){
		Evaluation e = evaluationDao.findOne(id);
		e.setStatus(status);
		evaluationDao.save(e);
	}
	
	@Transactional
	public void save(Evaluation e){
		String simpleName = e.getClass().getSimpleName();
		User author = userDao.load(e.getAuthor().getId());
		Dict grade = author.getGrade();
		Dict year = e.getYear();
		Dict specialty = author.getSpecialty();
		e.setGsIndex(ofIndex(simpleName, "sumSorce", grade.getCode(), year.getCode(), specialty.getCode(), e.getSumSorce(), author.getClasses().getCode()).intValue());
		if(e instanceof Evaluation1){
			Evaluation1 e2 = ((Evaluation1)e);
//			e2.setGsIndex(gsIndex);(ofIndex("Evaluation1", "baseEvaluationSorce", grade.getCode(), year.getCode(), specialty.getCode(), e2.getBaseEvaluationSorce()).intValue());
		}
		evaluationDao.save(e);
	}

	public Evaluation loadEvaluation(String id) {
		return evaluationDao.findOne(id);
	}

	public Evaluation findByUser(String userid, String year,String type) {
		List<Evaluation> list = evaluationDao.find(String.format("from %s where author.id=? and year.code = ? ",type), userid, year);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	public Evaluation findByUser(String userid, String year) {
		List<Evaluation> list = evaluationDao.find("from Evaluation where author.id=? and year.code = ? ", userid, year);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public Long ofIndex(String entityName, String indexCol,String grade, String year, String specialty, Float number, String classes) {
		return evaluationDao.count(String.format("from %s where year.code =? and author.grade.code=? and author.specialty.code = ? and %s > ? ",entityName, indexCol),year, grade, specialty, number);
	}

	@Transactional(readOnly=true)
	public void writeExcal(String type, Dict grade, Dict classes, Dict year, OutputStream outputStream) throws Exception {
		List<Evaluation> list = evaluationDao.find(String.format("from %s where year.code=? and author.grade.code=? and author.classes.code=? order by sumSorce desc", type), year.getCode(), grade.getCode(), classes.getCode());
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(new File(String.format("conf/xls/%s.xls",type))));
		HSSFSheet sheet = book.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		HSSFCell cell = row.getCell(0);
		String string = String.format(cell.getStringCellValue(), year.getLabel(), grade.getLabel()+classes.getLabel());
		cell.setCellValue(string);
		int line ;
		switch (type) {
		case "Evaluation1":
			line = 4;
		for(Evaluation evaluation:list){
			Evaluation1 e1 = (Evaluation1) evaluation;
			row = sheet.createRow(line++);
			row.createCell(0).setCellValue(e1.getAuthor().getLoginname());
			row.createCell(1).setCellValue(e1.getAuthor().getNickName());
			row.createCell(2).setCellValue(e1.getStudySorce());
			row.createCell(3);
			row.createCell(4).setCellValue(e1.getBaseEvaluationSorce());
			row.createCell(5).setCellValue(e1.getGrowSource1());
			row.createCell(6).setCellValue(e1.getGrowSource2());
			row.createCell(7).setCellValue(e1.getGrowSource3());
			row.createCell(8).setCellValue(e1.getGrowSource4());
			row.createCell(9).setCellValue(e1.getGrowSource5());
			row.createCell(10).setCellFormula(String.format("SUM(F%d:J%d)/5", line,line));
			row.createCell(11).setCellFormula(String.format("C%d*.45+E%d+K%d",line,line,line));
			row.createCell(12);//.setCellValue(e1.getGsIndex());
		}
		break;
		case "Evaluation2":
			line = 4;
			for(int index=0;index <list.size();index++){
				int i=0;
				Evaluation2 e1 = (Evaluation2) list.get(index);
				row = sheet.createRow(line++);
				row.createCell(i++).setCellValue(e1.getAuthor().getLoginname());
				row.createCell(i++).setCellValue(e1.getAuthor().getNickName());
				row.createCell(i++).setCellValue(e1.getBaseSource1());
				row.createCell(i++).setCellValue(e1.getBaseSource2());
				row.createCell(i++).setCellValue(e1.getBaseSource3());
				row.createCell(i++).setCellValue(e1.getBaseSource4());
				row.createCell(i++).setCellValue(e1.getBaseSource5());
				row.createCell(i++).setCellValue(e1.getGrowSource1());
				row.createCell(i++).setCellValue(e1.getGrowSource2());
				row.createCell(i++).setCellValue(e1.getGrowSource3());
				row.createCell(i++).setCellValue(e1.getGrowSource4());
				row.createCell(i++).setCellValue(e1.getGrowSource5());
				row.createCell(i++).setCellValue(e1.getGrowEvaluationSorce());
				row.createCell(i++).setCellValue(e1.getStudySorce());
				row.createCell(i++).setCellValue(e1.getOtherSource());
				row.createCell(i++).setCellValue(e1.getStudySum());
				row.createCell(i++).setCellValue(e1.isVetoSource()?"有":"无");
				row.createCell(i++).setCellValue(e1.getSumSorce());
				row.createCell(i++).setCellValue(index+1);
			}
		default:
			break;
		}
		book.write(outputStream);
		outputStream.flush();
		outputStream.close();
		book.close();
	}
	
}
