package cn.qlt.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;

import cn.qlt.dao.DictDao;
import cn.qlt.dao.Evaluation2Dao;
import cn.qlt.dao.RoleDao;
import cn.qlt.dao.StudentDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Dict;
import cn.qlt.domain.Evaluation2;
import cn.qlt.domain.Role;
import cn.qlt.domain.Student;
import cn.qlt.domain.User;

@Component
public class FileImportUtils {
	
	@Autowired
	private DictDao dictDao;
	
	@Autowired
	private UserDao userDao;
		
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private Evaluation2Dao evaluation2Dao;

	@PostConstruct
	public void init(){
		File file = new File("excel");
		Role role = null;
		if(file.exists()){
			try{
				role= roleDao.findSingle("code", "student");
				}catch (Exception e) {}
				if(role==null){
					role = new Role();
					role.setCode("student");
					role.setLabel("学生");
					roleDao.save(role);
				}
			int index = 1;
			
			Dict year = dictDao.findByTypeAndLabel("year", "2017-2018学年");
			if(year == null){
				year = new Dict("2017-2018", "2017-2018学年");
				year.setType("year");
				dictDao.save(year);
			}
			try{
				System.out.println("=============通过excel导入数据开始=============");
				String[] list = file.list();
				for(String grade: list){
					Dict dictGrade = dictDao.findByTypeAndLabel("grade", grade);
					if(dictGrade==null){
						dictGrade=new Dict("g_".concat(grade.replaceAll("[\\D]+", "")), grade);
						dictGrade.setType("grade");
						dictDao.save(dictGrade);
					}
					File dir = new File(file,grade);
					for(String clz: dir.list()){
						String className = clz.replaceFirst(grade, "").replace(".xlsx", "");
						Dict classGrade = dictDao.findByTypeAndLabel("grade", className);
						if(classGrade==null){
							classGrade=new Dict("clz_"+(index++), grade);
							classGrade.setType("class");
							dictDao.save(classGrade);
						}
						try {
							XSSFWorkbook work = new XSSFWorkbook(new File(dir,clz));
							XSSFSheet sheet = work.getSheetAt(0);
							Iterator<Row> iterator = sheet.rowIterator();
							iterator.next();
							iterator.next();
							iterator.next();
							iterator.next();
							while(iterator.hasNext()){
								Row row = iterator.next();
								String username = row.getCell(0).getStringCellValue();
								
								User user = null;
								try{
									user = userDao.findSingle("loginname", username);
								}catch (Exception e) {
								}
								if(user == null){
									user = new User();
									user.setLoginname(username);
									user.setNickName(row.getCell(1).getStringCellValue());
									user.setPassword("123456");
									user.encodedPassword();
									user.setGrade(dictGrade);
									user.setClasses(classGrade);
									user.getRoles().add(role);
									userDao.save(user);
									Student student = new Student();
									student.setUser(user);
									student.setId(student.getUser().getId());
									studentDao.save(student);
								}
								Evaluation2 eval = new Evaluation2();
								eval.setTitle("齐鲁师范学院 教师教育学院2017-2018学年学生综合素质测评表——".concat(user.getNickName()));
								eval.setAuthor(user);
								eval.setYear(year);
								eval.setBaseSource1((float) row.getCell(2).getNumericCellValue());
								eval.setBaseSource2((float) row.getCell(3).getNumericCellValue());
								eval.setBaseSource3((float) row.getCell(4).getNumericCellValue());
								eval.setBaseSource4((float) row.getCell(5).getNumericCellValue());
								eval.setBaseSource5((float) row.getCell(6).getNumericCellValue());
								eval.setGrowSource1((float) row.getCell(7).getNumericCellValue());
								eval.setGrowSource2((float) row.getCell(8).getNumericCellValue());
								eval.setGrowSource3((float) row.getCell(9).getNumericCellValue());
								eval.setGrowSource4((float) row.getCell(10).getNumericCellValue());
								eval.setGrowSource5((float) row.getCell(11).getNumericCellValue());
								eval.setGrowEvaluationSorce((float) row.getCell(12).getNumericCellValue());
								eval.setStudySorce((float) row.getCell(13).getNumericCellValue());
								eval.setOtherSource((float) row.getCell(15).getNumericCellValue());
								eval.setStudySum((float) row.getCell(16).getNumericCellValue());
								eval.setVetoSource("无".equals(row.getCell(17).getStringCellValue())?false:true);
								eval.setSumSorce(((float) row.getCell(18).getNumericCellValue()));
								eval.setGsIndex(((int) row.getCell(18).getNumericCellValue()));
								evaluation2Dao.save(eval);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}finally{
				System.out.println("=============通过excel导入数据结束=============");
				//file.delete();
			}
		}
	}
	
	public static void main(String [] args){
		new FileImportUtils().init();
	}
}
