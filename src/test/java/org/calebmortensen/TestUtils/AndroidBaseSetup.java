package org.calebmortensen.TestUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.calebmortensen.pageObjects.android.FormPage;
import org.calebmortensen.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseSetup extends AppiumUtils{
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass
	public void ConfigureAppium() throws URISyntaxException, IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\org\\calebmortensen\\resources\\globaldata.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
						
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDevice")); // emulator
		
		options.setChromedriverExecutable(
				System.getProperty("user.dir")+ "\\src\\main\\java\\org\\calebmortensen\\resources\\chromedriver.exe");
		options.setApp(
				System.getProperty("user.dir")+ "\\src\\main\\java\\org\\calebmortensen\\resources\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);

	}

	@AfterMethod
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();

	}

}
