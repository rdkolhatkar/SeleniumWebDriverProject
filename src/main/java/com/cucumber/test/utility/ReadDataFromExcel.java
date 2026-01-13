package com.cucumber.test.utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

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
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
