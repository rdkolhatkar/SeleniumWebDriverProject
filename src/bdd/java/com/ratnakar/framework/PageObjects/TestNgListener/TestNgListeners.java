// Package declaration: organizes the class under your project’s structure
package com.ratnakar.framework.PageObjects.TestNgListener;

// Importing ExtentReports classes for reporting
import com.aventstack.extentreports.ExtentReports;  // Main class to create and manage the report
import com.aventstack.extentreports.ExtentTest;     // Represents an individual test log entry
import com.aventstack.extentreports.Status;         // Enum to define test status: PASS, FAIL, SKIP, etc.

// Importing your custom report engine class that configures ExtentReports
import com.ratnakar.framework.PageObjects.ExtentReports.ExtentReporterEngine;

// Importing TestNG listener interfaces and related result/context classes
import org.testng.ITestContext;  // Provides information about the current test context
import org.testng.ITestListener; // Interface that allows you to listen to test events (start, success, failure, etc.)
import org.testng.ITestResult;   // Holds details about a specific test result (like name, status, exception, etc.)

// This class implements TestNG's ITestListener interface
// It allows you to perform custom actions when tests start, pass, fail, or finish.
// Commonly used to integrate reporting tools such as ExtentReports.
public class TestNgListeners implements ITestListener {

    // Declares an ExtentTest reference for logging individual test case information
    ExtentTest test;

    // Initializes a single ExtentReports instance by calling your custom engine method
    // This ensures the same report object is shared across all tests
    ExtentReports extentReports = ExtentReporterEngine.getReportObject();


    // This method runs automatically BEFORE each test method starts execution.
    // It helps in creating a new test entry in the Extent Report.
    @Override
    public void onTestStart(ITestResult result) {
        // Calls the default implementation (optional)
        ITestListener.super.onTestStart(result);

        // Creates a new test node in the Extent report using the test method's name
        // Example: if the test method name is "verifyLogin", it will create a section titled "verifyLogin" in the report
        test = extentReports.createTest(result.getMethod().getMethodName());
    }


    // This method runs automatically AFTER a test method passes successfully.
    @Override
    public void onTestSuccess(ITestResult result) {
        // Calls the default implementation (optional)
        ITestListener.super.onTestSuccess(result);

        // Logs a PASS status message into the Extent Report
        // The message will appear under the respective test node in the report
        test.log(Status.PASS, "Test Execution is Successful");
    }


    // This method runs automatically AFTER a test method fails.
    @Override
    public void onTestFailure(ITestResult result) {
        // Calls the default implementation (optional)
        ITestListener.super.onTestFailure(result);

        // Logs the exception or error that caused the failure
        // 'getThrowable()' returns the actual exception that was thrown
        test.fail(result.getThrowable());
    }


    // This method runs automatically when a test method is skipped.
    // Example: if it depends on another test that failed or was disabled.
    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);

        // You can also log skipped status like:
        // test.log(Status.SKIP, "Test Skipped: " + result.getName());
    }


    // This method runs when a test fails but still falls within the success percentage
    // (used when 'successPercentage' attribute is set in @Test annotation)
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }


    // This method runs when a test fails due to a timeout (if set using timeOut attribute)
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }


    // This method runs ONCE before any test methods from the current test class start executing.
    // It can be used to perform setup operations or resource initialization.
    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);

        // Example usage: you could log suite name or environment setup here
        // System.out.println("Starting test suite: " + context.getName());
    }


    // This method runs ONCE after all the test methods in the current test class finish execution.
    // It is typically used to clean up resources and finalize reports.
    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        // You should flush the ExtentReports object here to ensure all logs are written to the report file.
        extentReports.flush();
    }
}
