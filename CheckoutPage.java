package SrijitLearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SrijitLearning.AbstractComponents.AbstractComponent;



public class CheckoutPage extends AbstractComponent {

	WebDriver d;

	public CheckoutPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);

	}

	@FindBy(css = ".action__submit")
	 private WebElement submit;// encapsulation this webelement can't be invoked any other class

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;

	private By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions a = new Actions(d);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(d);
		
		
	}

}

