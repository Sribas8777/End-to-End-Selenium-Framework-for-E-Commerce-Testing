package SrijitLearning.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SrijitLearning.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	public WebDriver d;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException

	{
		// properties class

		 Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//SrijitLearning//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().clearDriverCache().setup();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
				options.addArguments("--headless=new"); // New headless mode
		        options.addArguments("--window-size=1920,1080");
		        options.addArguments("--disable-gpu");
		        options.addArguments("--no-sandbox");
		        
			    }		
			d = new ChromeDriver(options);
			d.manage().window().setSize(new Dimension(1440,900));
			
			
			

		} 
		
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			/**System.setProperty("webdriver.gecko.driver",
					"/Users/rahulshetty//documents//geckodriver");**/
			FirefoxOptions options = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			if(browserName.contains("headless")){
			options.addArguments("--headless");
			
	        //options.addArguments("--width=1920");
	        //options.addArguments("--height=1080");
			}		
			d = new FirefoxDriver();
			d.manage().window().setSize(new Dimension(1920, 1080));//full screen
			
		
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
			/** System.setProperty("webdriver.edge.driver", "edge.exe");
			d = new EdgeDriver();**/
		}
		d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		d.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.manage().window().maximize();
		
		return d;

	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}

	}
	
	public String getScreenshot(String testCaseName, WebDriver d) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) d;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		
		 d = initializeDriver();
		landingPage = new LandingPage(d);
		landingPage.goTo();
		return landingPage;
	
		
	}
	
	@AfterMethod(alwaysRun=true)
	
	public void tearDown()
	{
		d.close();
	}
}
