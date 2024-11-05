package com.vcentry.utils;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Function {
       public static String propFile="src\\main\\resources\\config.properties";
       public static String testData="src\\test\\resources\\TestData.xlsx";
	public static String getProp(String data)  {
		String propValue=null;
		try {
		FileInputStream fi=new FileInputStream(propFile);
	     Properties prop=new Properties();
	     prop.load(fi);
	     propValue=prop.get(data).toString();
	     
		}catch (Exception e) {
			e.printStackTrace();
		}
		return propValue;
	}
	
	public static String currentDateandTime() {
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now=LocalDateTime.now();
		return dt.format(now);
	}
	
	public static String[][] getTestData(String sheetName,String test){
		String[][] data = null;
		try {
			FileInputStream fi=new FileInputStream(testData);
			XSSFWorkbook wb= new XSSFWorkbook(fi);
			XSSFSheet sheet=wb.getSheet(sheetName);
			int row=sheet.getLastRowNum();
			for (int i = 0; i <= row; i++) {
				String cell0=sheet.getRow(i).getCell(0).getStringCellValue();
				if(cell0.equals(test)) {
					int col=sheet.getRow(i).getLastCellNum();
					data=new String[1][col-1];
					for (int j = 1; j <col ; j++) {
						DataFormatter formatter= new DataFormatter();
						Cell cell=sheet.getRow(i).getCell(j);
						data[0][j-1]=formatter.formatCellValue(cell);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
}
