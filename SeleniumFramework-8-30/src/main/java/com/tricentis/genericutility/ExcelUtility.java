package com.tricentis.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getStringDataFromExcel(String sheetName,int rowNum,int colNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestscriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		return workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();
	}
	
	public boolean getBooleanDataFromExcel(String sheetName,int rowNum,int colNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestscriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		return workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum).getBooleanCellValue();
	}
	
	public int getNumberDataFromExcel(String sheetName,int rowNum,int colNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestscriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		return (int)workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum).getNumericCellValue();
	}
	
	public LocalDateTime getLocalDateTimeDataFromExcel(String sheetName,int rowNum,int colNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestscriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		return workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum).getLocalDateTimeCellValue();
	}
	
	
}
