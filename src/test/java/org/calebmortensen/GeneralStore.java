package org.calebmortensen;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.calebmortensen.TestUtils.AndroidBaseSetup;
import org.calebmortensen.pageObjects.android.CartPage;
import org.calebmortensen.pageObjects.android.FormPage;
import org.calebmortensen.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;

public class GeneralStore extends AndroidBaseSetup {

	
	@BeforeMethod
	public void preSetup()
	{
		formPage.setActivity();
		//screen to home page
		//Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		//driver.startActivity(activity);
		//((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
	
	
	
	@Test(dataProvider= "getData")
	public void PopulateForm(HashMap<String, String> input) throws InterruptedException {

		FormPage formPage = new FormPage(driver);
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		
		ProductCatalog productCatalog = formPage.submitForm();
		productCatalog.addItemToCartByIndex(0);
		productCatalog.addItemToCartByIndex(0);
		CartPage cartPage = productCatalog.goToCartPage();

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		AssertJUnit.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{ 
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\calebmortensen\\testdata\\GeneralStore.json");
		return new Object[][] {  {data.get(0)}, {data.get(1)}	};

}
}