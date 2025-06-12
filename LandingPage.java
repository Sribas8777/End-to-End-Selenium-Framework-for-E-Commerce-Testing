package SrijitLearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SrijitLearning.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
WebDriver d;
	
	public LandingPage(WebDriver d)
	{
		super(d);
		//initialization
		this.d=d;
		PageFactory.initElements(d, this);
		
	}
		
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	public ProductCataloge loginApplication(String email,String password) throws InterruptedException
	{
		userEmail.sendKeys(email);
		Thread.sleep(10);
		passwordEle.sendKeys(password);
		Thread.sleep(10);
		submit.click();
		ProductCataloge productCataloge = new ProductCataloge(d);
		Thread.sleep(10);
		return productCataloge;
		
		
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		d.get("https://rahulshettyacademy.com/client");
	}
	
	
}
	
