package com.cucumber.test.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadExcelData {

    public ArrayList<String> getExcelData(String columnName, String matchValue) {

        ArrayList<String> data = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream("src/main/resources/files/TestData.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("Data");
            Iterator<Row> rows = sheet.iterator();

            Row headerRow = rows.next();
            int columnIndex = -1;

            // 🔹 Find column index by column name
            for (Cell cell : headerRow) {
                if (formatter.formatCellValue(cell)
                        .equalsIgnoreCase(columnName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }

            System.out.println("Index value of column is : " + columnIndex);

            // 🔹 SAFETY CHECK
            if (columnIndex == -1) {
                throw new RuntimeException(
                        "Column '" + columnName + "' not found in Excel");
            }

            // 🔹 Find matching row value
            while (rows.hasNext()) {
                Row row = rows.next();
                String cellValue = formatter.formatCellValue(row.getCell(columnIndex));

                if (cellValue.equalsIgnoreCase(matchValue)) {
                    for (Cell cell : row) {
                        data.add(formatter.formatCellValue(cell));
                    }
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    public static void main(String[] args) {
        ReadExcelData readExcelData = new ReadExcelData();
        ArrayList<String> extractedData = readExcelData.getExcelData("UserId", "101");
        System.out.println(extractedData.get(0));
        System.out.println(extractedData.get(1));
        System.out.println(extractedData.get(2));
    }
}
