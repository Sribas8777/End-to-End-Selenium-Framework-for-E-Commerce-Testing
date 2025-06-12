package SrijitLearning.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SrijitLearning.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
        WebDriver d=new ChromeDriver();
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        d.get("https://rahulshettyacademy.com/client");
        LandingPage g=new LandingPage(d);
        d.findElement(By.id("userEmail")).sendKeys("dsribas623@gmail.com");
        d.findElement(By.id("userPassword")).sendKeys("Srijit@87");
        d.findElement(By.cssSelector("#login")).click();
        List<WebElement> products=d.findElements(By.cssSelector(".mb-3 "));
        WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
       WebDriverWait wait=new WebDriverWait(d,Duration.ofSeconds(5));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
       wait.until(ExpectedConditions.invisibilityOf(d.findElement(By.cssSelector(".ng-animating"))));
       d.findElement(By.cssSelector("button[routerlink*='cart']")).click();
       List <WebElement> cartProducts=d.findElements(By.cssSelector(".cartSection h3"));
       
      boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
      Assert.assertTrue(match);
      d.findElement(By.cssSelector("li .btn-primary:last-of-type")).click();
      Actions a=new Actions(d);
      a.sendKeys(d.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
      d.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
      d.findElement(By.cssSelector(".action__submit ")).click();
      Assert.assertTrue(d.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));
      d.close();
      
        
        

	}

}
