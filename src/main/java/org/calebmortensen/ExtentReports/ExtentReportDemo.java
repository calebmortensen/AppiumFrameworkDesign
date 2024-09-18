package org.calebmortensen.ExtentReports;

import org.calebmortensen.utils.AppiumUtils;
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

import io.appium.java_client.AppiumDriver;

public class ExtentReportDemo extends AppiumUtils {

	
	public WebDriver driver;
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
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\org\\calebmortensen\\resources\\chromedriver.exe");
		
	}
	
	
	
	
	@Test
	public void initialDemo() throws InterruptedException {
		
		ExtentTest test = extent.createTest("Initial Demo");
		
		driver = new ChromeDriver();
		driver.get("https://www.cnn.com/");
		//Thread.sleep(5000);
		System.out.println(driver.getTitle());
		driver.close();
		extent.flush();
		
		
	}
	
	@Test
	public void initialfailOnPurpose() {
		
		ExtentTest test = extent.createTest("Initial Fail");
		
		driver = new ChromeDriver();
		driver.get("https://FAILonPURPOSEforTEST.com");
		//System.out.println(driver.getTitle());
		//test.addScreenCaptureFromBase64String(null);
		test.fail("Results do not match");
		driver.close();
		extent.flush();
		
		
	}
	
	
}
