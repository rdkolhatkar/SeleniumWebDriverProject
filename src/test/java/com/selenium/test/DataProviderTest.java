package com.selenium.test;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        return data;
    }
    @Test
    public void testCaseDataProvider(){

    }
}
