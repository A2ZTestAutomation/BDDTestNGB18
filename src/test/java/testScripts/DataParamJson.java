package testScripts;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataParamJson {
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10,  TimeUnit.SECONDS);
		driver.get("https://ui.cogmento.com/");
	}
	@Test(dataProvider="loginData")
	public void login(String strUser, String strPwd) throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.name("email")).sendKeys(strUser);
		driver.findElement(By.name("password")).sendKeys(strPwd);
		driver.findElement(By.xpath("//div[text()='Login']")).click();
	}
	
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		String path = System.getProperty("user.dir")
				+"//src//test//resources//testData//login.json";
		FileReader reader = new FileReader(path);
		JSONObject jsonObj = (JSONObject)parser.parse(reader);
		JSONArray userArray = (JSONArray)jsonObj.get("userLogins");
		String arr[][] = new String[userArray.size()][];
		for(int i=0; i< userArray.size(); i++) {
			JSONObject user = (JSONObject)userArray.get(i);
			String username = (String)user.get("username");
			String password = (String)user.get("password");
			String record[] = new String[2];
			record[0] = username;
			record[1] = password;
			arr[i] = record;
		}
		return arr;
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
	}

}
