package stepdefs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GooglePageStepDef {
	WebDriver driver;
				
		@Given("I launch google page")
		public void i_launch_google_page() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.google.com/");
		}

		@When("I search Java Tutorial")
		public void i_search_java_tutorial() {
			driver.findElement(By.name("q")).sendKeys("Java Tutorial");
			driver.findElement(By.name("q")).submit();
		}

		@Then("Should display Java search result")
		public void should_display_java_search_result() {
			Assert.assertEquals("Java Tutorial - Google Search", driver.getTitle());
		}
	
			@When("I search Selenium Tutorial")
			public void i_search_selenium_tutorial() {
				driver.findElement(By.name("q")).sendKeys("Selenium Tutorial");
				driver.findElement(By.name("q")).submit();
			}

			@Then("Should display Selenium search result")
			public void should_display_selenium_search_result() {
				Assert.assertEquals("Selenium Tutorial - Google Search", driver.getTitle());
			}
		
		@Then("Close the browser")
		public void close_the_browser() {
			driver.close();
		}
		@After
		public void generateScrshot(Scenario scenario) throws IOException
		{
			if(scenario.isFailed()) {
				TakesScreenshot screen = (TakesScreenshot)driver;
				File scrshot = screen.getScreenshotAs(OutputType.FILE);
				byte[] fileContent = FileUtils.readFileToByteArray(scrshot);
				scenario.attach(fileContent, "image/png", "image1");
				
			}
		}
}
