package com.automationpractice.testcases;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


	 
	// Get all type of excel cell value as String using POI
	public class ReadExcelTest {
	 
	    public static void main(String[] args) throws Exception {
	        // Get the input stream of excel file
	        InputStream inputStream = new FileInputStream(
	        		"C:\\Users\\Ganesh\\workspace\\Ganesh_Ecommerce_Automation_1\\src\\main\\java\\com\\automationpractice\\testdata\\TestDatas.xlsx");
	        // Create a workbook object.
	        Workbook wb = WorkbookFactory.create(inputStream);
	        Sheet sheet = wb.getSheetAt(0);
	        // Iterate over all the row and cells
	        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
	            Row row = rit.next();
	            for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext();) {
	                Cell cell = cit.next();
	                // Print the each cell value
	                System.out.println("Output : " + getCellValueAsString(cell));
	            }
	        }
	    }
	 
	 
	    /**
	     * This method for the type of data in the cell, extracts the data and
	     * returns it as a string.
	     */
	    public static String getCellValueAsString(Cell cell) {
	        String strCellValue = null;
	        if (cell != null) {
	            switch (cell.getCellType()) {
	            case Cell.CELL_TYPE_STRING:
	                strCellValue = cell.toString();
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat(
	                            "dd/MM/yyyy");
	                    strCellValue = dateFormat.format(cell.getDateCellValue());
	                } else {
	                    Double value = cell.getNumericCellValue();
	                    Long longValue = value.longValue();
	                    strCellValue = new String(longValue.toString());
	                }
	                break;
	            case Cell.CELL_TYPE_BOOLEAN:
	                strCellValue = new String(new Boolean(
	                        cell.getBooleanCellValue()).toString());
	                break;
	            case Cell.CELL_TYPE_BLANK:
	                strCellValue = "";
	                break;
	            }
	        }
	        return strCellValue;
	    }
	}
