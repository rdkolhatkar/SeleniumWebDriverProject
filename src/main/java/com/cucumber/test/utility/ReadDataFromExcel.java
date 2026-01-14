package com.cucumber.test.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.Iterator;

public class ReadDataFromExcel {
    public static void main(String[] args) {
        try {
            // Defining the Excel file path Using FileInputStream
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/files/TestData.xlsx");
            // Apache POI dependency to read the data from Excel Sheet
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            // We have multiple sheets in the same Excel File,
            // So first we have to get the sheet count, and then we have to read each sheet one by one.
            int sheetCount = workbook.getNumberOfSheets(); // This code will return the sheet count
            // Now we will iterate over each sheet
            for(int i = 0; i<sheetCount; i++){
                // Fetching Specific Sheet By name
                if(workbook.getSheetName(i).equalsIgnoreCase("Data")){
                    XSSFSheet sheet = workbook.getSheetAt(i);
                    //Identify the userName column by scanning the entire first row
                    Iterator<Row> rows = sheet.iterator();
                    Row firstRow = rows.next(); // Now we are on the first row
                    // Now we have to read each and every cell present in the first row
                    Iterator<Cell> cell = firstRow.cellIterator();
                    // Now to check each and every cell we will be using while loop
                    while (cell.hasNext()){
                       Cell cellValue = cell.next();
                       if(cellValue.getStringCellValue().equalsIgnoreCase("userName")){
                           // This is our Desired Column
                       }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
