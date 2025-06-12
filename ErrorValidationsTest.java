package SrijitLearning.tests;
import org.testng.annotations.Test;

import SrijitLearning.TestComponents.BaseTest;
import SrijitLearning.pageobjects.CartPage;
import SrijitLearning.pageobjects.LandingPage;
import SrijitLearning.pageobjects.ProductCataloge;
import SrijitLearning.TestComponents.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;



public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

	
		//LandingPage landingPage = launchApplication();
		landingPage.loginApplication("dsribas623@gmail.com", "Srijit@8");
		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
		
		ProductCataloge productCatalogue = landingPage.loginApplication("dsribas623@gmail.com", "Srijit@87");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}

	
	

}

