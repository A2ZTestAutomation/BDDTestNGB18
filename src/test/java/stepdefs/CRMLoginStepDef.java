package stepdefs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CRMLoginStepDef {
	WebDriver driver;
		@Given("User is on Login Page")
		public void user_is_on_login_page() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10,  TimeUnit.SECONDS);
			driver.get("https://ui.cogmento.com/");
			System.out.println("Launched URL");
			System.out.println("Modified by Tester1 for CR1");
			System.out.println("Modified By Tester2 for CR3");
			System.out.println("Modified in Phase2 for new CR");
			
		}

		@When("User enters login credentials")
		public void user_enters_login_credentials() {
			driver.findElement(By.name("email")).sendKeys("corpdevops@gmail.com");
			driver.findElement(By.name("password")).sendKeys("CrmPro123");
			driver.findElement(By.xpath("//div[text()='Login']")).click();
		}
		
		@Then("User is navigated to Home Page")
		public void user_is_navigated_to_home_page() {
			boolean isUserValid = driver.findElement(By.xpath("//span[@class='user-display']")).isDisplayed();
			Assert.assertTrue(isUserValid);
		}
		
		@Then("Close the Browser")
		public void close_the_browser() {
			driver.close();
		}
}
