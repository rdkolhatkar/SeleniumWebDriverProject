package com.selenium.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
    @DataProvider(name = "driveTestDataWithExcel")
    public Object[][] getData(){
        Object[][] data = {{},{},{}};
        return data;
    }
    @Test
    public void testCaseDataProvider(){

    }
}
