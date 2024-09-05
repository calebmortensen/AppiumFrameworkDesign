package org.calebmortensen.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {

	
	WebDriver driver;
	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		//ExtentReports, ExtentSparkReporter
		
		String path = System.getProperty("user.dir")	+"\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Caleb Mortensen");
		
		
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
