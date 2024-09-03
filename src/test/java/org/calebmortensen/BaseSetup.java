package org.calebmortensen;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import org.calebmortensen.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseSetup {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException {

		// code to start automation instead of running appium from command line
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\caleb\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("API35"); // emulator
		// NOTE: options.setDeviceName("Android Device"); --> PHYSICALLY CONNECTED
		// DEVICE via USB
		// NOTE: MUST enabled DEVELOPER OPTIONS 7 TIMES on Android Phone & ALLOW USB
		// DEBUGGING
		// NOTE: adb devices
		options.setChromedriverExecutable(
				"C:\\Users\\caleb\\EWS\\Appium\\src\\main\\java\\resources\\chromedriver.exe");
		options.setApp(
				"C:\\Users\\caleb\\EWS\\AppiumFrameworkDesign\\src\\main\\java\\org\\calebmortensen\\resources\\General-Store.apk");

		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();

	}

}
