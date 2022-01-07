package com.OrangeHRM.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.OrangeHRM.constants.Constants;

public class excelDataProvider {
	public static WebDriver driver;
	public static File src;
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	
	DataFormatter dFormat=new DataFormatter();
	
	
	DataFormatter format = new DataFormatter();
	@DataProvider(name="loginCredentials")
public Object[][] oPHCredentials(){
		
		try {
			fis = new FileInputStream(Constants.LoginData);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			////sd
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rowCount = sheet.getLastRowNum();
		//System.out.println(rowCount);
		row=sheet.getRow(0);
		int colCount = row.getLastCellNum();
		//System.out.println(colCount);
		
		Object excelData[][] = new Object[rowCount][colCount];
		for(int i=0;i<rowCount;i++) {
			row=sheet.getRow(i);
			for (int j=0;j<colCount;j++) {
				cell = row.getCell(j);
				format.formatCellValue(cell);
				excelData[i][j]=format.formatCellValue(cell);
			}
			
		}
		return excelData;
	}
}