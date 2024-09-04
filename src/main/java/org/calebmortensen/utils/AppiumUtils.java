package org.calebmortensen.utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppiumUtils {

	//Works for BOTH Android & IOS
	AppiumDriver driver;
	
	//Constructor
		public AppiumUtils(AndroidDriver driver) {
			//super(driver);
			this.driver = driver;
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
	
	public void waitForElementToAppear(WebElement element) {
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.attributeContains(element, "text", "Cart"));
		
	}
}
