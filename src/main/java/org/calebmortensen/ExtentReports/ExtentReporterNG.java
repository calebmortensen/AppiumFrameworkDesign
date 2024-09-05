package org.calebmortensen.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	
	WebDriver driver;
	static ExtentReports extent;
	
	@BeforeTest
	public static ExtentReports getReporterObject() {
		//ExtentReports, ExtentSparkReporter
		
		String path = System.getProperty("user.dir")	+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Caleb Mortensen");
		
		return extent;
		
	}
	
	
	
	
	@Test
	public void initialDemo() {
		
		ExtentTest test = extent.createTest("Initial Demo");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\org\\calebmortensen\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://rahushettyacademy.com");
		System.out.println(driver.getTitle());
		//test.addScreenCaptureFromBase64String();
		driver.close();
		//test.fail("Results do not match");
		
		extent.flush();
		
		
	}
	
	
	
}
