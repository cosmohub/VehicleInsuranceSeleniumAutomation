package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtentReporterTestNG;

import baseTest.BaseTest;

public class ExtentReportListener extends BaseTest implements ITestListener {
	ExtentReports extReport = ExtentReporterTestNG.getExtentReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	    test = extReport.createTest(result.getMethod().getMethodName());
	    extTest.set(test);
	  }

	@Override
	public void onTestSuccess(ITestResult result) {
	    extTest.get().log(Status.PASS, "Test Case passed succesfully");
	  }

	@Override 
	public void onTestFailure(ITestResult result) {
		  String filePath = null;
	   extTest.get().fail(result.getThrowable());
	   try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
		e.printStackTrace();
	}
	   try {
		 filePath = takeScreenshot(result.getMethod().getMethodName(),driver);
		 System.out.println(filePath);
		 
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   extTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	   
	  }

	@Override 
	  public void onFinish(ITestContext context) {
	    extReport.flush();
	  }
}
