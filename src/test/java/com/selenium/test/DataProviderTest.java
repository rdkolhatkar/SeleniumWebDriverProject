package com.selenium.test;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DataProviderTest {
    @DataProvider(name = "driveTestDataWithExcel")
    public Object[][] getData() throws IOException {
        // While fetching the data from Excel Sheet we have to send every excel row as one array
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/dataFiles/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        // In our Excel we have two worksheets, at index 0 we have sheet called 'Data' and at index 1 we have sheet called 'Test'
        XSSFSheet sheet = workbook.getSheetAt(1); // Opening the Sheet at index 1 which is 'Test'
        // Find the number of columns and number of rows present in the sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        // To find the number of columns we have to first check how many cells in the first row have the values, exclude empty cells.
        // These Values from the first row are called Column Headers
        XSSFRow row = sheet.getRow(0); // Reading the first row at 0th index
        int columnCount = row.getLastCellNum(); // getLastCellNum() means get last cell from first row which contains some value written inside it
        // Declaring the size of our multidimensional Object array with rowCount & columnCount
        // Here we are mentioning the [rowCount -1] & [columnCount -1] because we have to skip first row as it contains the headers not the actual values
        Object[][] data = new Object[rowCount -1][columnCount -1];
        // Now we have to write the two for loops first is iteration over the rows and inner for loop will iterate over the columns for each row
        for(int i = 0; i<rowCount-1; i++){
            System.out.println("Outer loop started to get the rows by their row index values");
            row = sheet.getRow(i+1);// i+1 is written because we need to skip the first row as it contains the column titles and headers
            for(int j = 0; j<columnCount-1; j++){
                System.out.println("Inner loop started to get the Cells by their column index values");
                // But in an excel sheet there can be different data values like Integer value, String Value etc.
                // We have to fetch each cell value and convert it into a String before inserting it into multidimensional data[][] array
                // Here we will use DataFormatter class for converting data into String
                DataFormatter dataFormatter = new DataFormatter();
                XSSFCell cellValue = row.getCell(j);// Fetching each cell value from 'i' th index row ans Storing it into variable
                System.out.println(cellValue);
                data[i][j] = dataFormatter.formatCellValue(cellValue); // Storing the extracted data into an multidimensional array
                System.out.println("Printing the Multidimensional array");
                System.out.println(Arrays.deepToString(data));
            }
        }
        return data;
    }
    @Test(dataProvider = "driveTestDataWithExcel")
    public void testCaseDataProvider() throws IOException {
        DataProviderTest dataProviderTest = new DataProviderTest();
        dataProviderTest.getData();
    }
}
