package SrijitLearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SrijitLearning.AbstractComponents.AbstractComponent;



public class ConfirmationPage extends AbstractComponent{

	
	WebDriver d;

	public ConfirmationPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
		

	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		CheckoutPage cp = new CheckoutPage(d);	
		return confirmationMessage.getText();
	}
	
	
}


