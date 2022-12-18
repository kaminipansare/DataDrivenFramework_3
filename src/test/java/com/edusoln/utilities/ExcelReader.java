package com.edusoln.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	// go to location //open the excel file //got hold of excelfile //get hold of
	// sheet //no of rows //no of cols //access data/read data using row and col
	static String homepath=System.getProperty("user.dir");
	static File file = new File(homepath+"\\src\\test\\resources\\TestData\\ExecutionData.xlsx");
	static FileInputStream fis;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public static Object[][] readExcel()  {		
		Map<Object, Object> map;	
		try {
			fis= new FileInputStream(file);		
		workbook= new XSSFWorkbook(fis);
		} catch (IOException e) {		
			e.printStackTrace();
		}
		sheet= workbook.getSheet("Data");
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data=new Object[rows-1][1];
		for (int i = 1; i < rows; i++) {
			map=new HashMap<Object, Object>();		
			for (int j = 0; j < cols; j++) {
				Object value = getCellValue(i,j);
				System.out.println(value);
				Object key=getCellValue(0,j);
				map.put(key, value);   
				data[i-1][0]=map;						
			}
		}		
		return data;
	}
	
	public static Object getCellValue(int row, int col) {
		Object value = null;
		Cell cell=sheet.getRow(row).getCell(col);
		if(cell.getCellType()==CellType.NUMERIC) {		
			value=(int)sheet.getRow(row).getCell(col).getNumericCellValue();
		}
		else if(cell.getCellType()==CellType.STRING) {		
			value=sheet.getRow(row).getCell(col).getStringCellValue();		
	}		
		return value;
}
}
