package com.ratnakar.framework.PageObjects.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterEngine {
        public static ExtentReports getReportObject(){
        String reportsPath = "src/test/resources/TestReports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportsPath);
        reporter.config().setReportName("Ecommerce Web Application Test Results"); // Shown on the report page
        reporter.config().setDocumentTitle("Test Results"); // Title of the browser tab or HTML document
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        // Add environment or tester information (metadata) to the report
        extentReports.setSystemInfo("Tester", "Ratnakar Kolhatkar");
        return extentReports;
    }
}
