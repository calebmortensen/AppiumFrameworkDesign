package org.calebmortensen;

import org.calebmortensen.pageObjects.android.CartPage;
import org.calebmortensen.pageObjects.android.FormPage;
import org.calebmortensen.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeneralStore extends BaseSetup {

	@Test
	public void PopulateForm() throws InterruptedException {

		FormPage formPage = new FormPage(driver);
		formPage.setNameField("Caleb");
		formPage.setGender("female");
		formPage.setCountrySelection("Argentina");
		ProductCatalog productCatalog = formPage.submitForm();
		productCatalog.addItemToCartByIndex(0);
		productCatalog.addItemToCartByIndex(0);
		CartPage cartPage = productCatalog.goToCartPage();

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),
		// "text", "Cart"));

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

	}

}