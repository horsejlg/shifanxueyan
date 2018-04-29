package test.cn.qlt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestEvaluation1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(new File("conf/xls/Evaluation1.xls")));
		HSSFSheet sheet = book.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		HSSFCell cell = row.getCell(0);
		cell.setCellValue(String.format(cell.getStringCellValue(), "2016--2017"));
		HSSFRow row2 = sheet.createRow(4);
		row2.createCell(0).setCellValue("aaaa");
		row2.createCell(1).setCellValue("bbbb");
		row2.createCell(2).setCellValue("cccc");
		row2.createCell(3).setCellValue("dddd");
		row2.createCell(4).setCellValue("eeee");
		book.write(new FileOutputStream("test.xls"));
		book.close();
	}
}
