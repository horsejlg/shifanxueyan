package cn.qlt.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.qlt.dao.DictDao;
import cn.qlt.dao.StudentDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Dict;
import cn.qlt.domain.Student;
import cn.qlt.domain.User;
import cn.qlt.utils.web.BusinessException;

@Service
public class ImportService {

	@Autowired
	private DictDao dictDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StudentDao studentDao;

	@SuppressWarnings("resource")
	public void importUsers(String grade, String years, String classes, String specialty, InputStream inputStream)
			throws IOException {
		byte[] b = { 0, 0 };
		try {
			inputStream.read(b);
			inputStream.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int head = 255 & b[0];
		head = (head << 8) | (255 & b[1]);
		Workbook workBook;
		if (head == 53455) {
			workBook = new HSSFWorkbook(inputStream);
		} else if (head == 20555) {
			workBook = new XSSFWorkbook(inputStream);
		} else {
			throw new BusinessException(500, "上传文件不是excel文件");
		}
		Dict gradeDict = dictDao.load(grade);
		Dict yearsDict = dictDao.load(years);
		Dict classDict = dictDao.load(classes);
		Dict specialtyDict = dictDao.load(specialty);
		Sheet sheet = workBook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		List<User> list = new ArrayList<User>();
		rowIterator.next();
		rowIterator.next();
		rowIterator.next(); //表格多了行备注
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			User user = new User();
			Cell cell = row.getCell(2);
			if(cell !=null && StringUtils.isEmpty(cell.getStringCellValue().trim())){ //为啥有行名字空的
				continue;
			}
			Cell cell2 = row.getCell(1);
			if(cell2==null)
				continue;
			int type = cell2.getCellType();
			if(CellType.NUMERIC.getCode()==type){
				user.setLoginname(cell2.getNumericCellValue()+"");
			}else{
				user.setLoginname(cell2.getStringCellValue().trim());
			}
			
			user.setPassword("123456");
			user.setNickName(cell.getStringCellValue().trim());
			user.setGrade(gradeDict);
			user.setYear(yearsDict);
			user.setClasses(classDict);
			user.setSpecialty(specialtyDict);
			user.encodedPassword();
			list.add(user);
		}
		userDao.save(list);

	}
	@PostConstruct
	public void appendData(){
		File dictDir = new File("conf/insert/dict");
		FileFilter jsonFilter = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getName().endsWith(".json");
			}
		};
		ObjectMapper mapper = new ObjectMapper();
		if(dictDir.exists()&&dictDir.isDirectory()){
			for(File file:dictDir.listFiles(jsonFilter)){
				try {
					Dict[] dicts = mapper.readValue(file, Dict[].class);
					for(Dict dict:dicts){
						dictDao.save(dict);
					}
					file.renameTo(new File("conf/insert/bak/dict",file.getName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		File userDir = new File("conf/insert/student");
		if(userDir.exists()&&dictDir.isDirectory()){
			for(File file:userDir.listFiles(jsonFilter)){
				try {
					Student[] students = mapper.readValue(file, Student[].class);
					for(Student student:students){
						student.getUser().setPassword("123456");
						student.getUser().encodedPassword();
						userDao.save(student.getUser());
						student.setId(student.getUser().getId());
						studentDao.save(student);
					}
					file.renameTo(new File("conf/insert/bak/student",file.getName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
