package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterTestNG {
	
	public static ExtentReports getExtentReport() {
		String path = ".\\src\\main\\resources\\reports\\index.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("Test Automation Report");
		spark.config().setReportName("Test Automation Report");
		spark.config().setTheme(Theme.DARK);
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Automobile", "QA Environment");
		return report;
				
	}

}
