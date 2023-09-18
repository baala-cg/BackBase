package com.bb.stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.pages.Article;
import com.bb.pages.LoginApp;
import com.bb.util.Configuration;
import com.bb.util.GlobalData;
import com.bb.util.ReusableFunctions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.core.cli.Main;
import junit.framework.Assert;

public class StepDefinition {
	public WebDriver driver;
	public StepDefinition def;
	
	private LoginApp loginApp; 
	private Article article; 

	private static final Logger log = LoggerFactory.getLogger(StepDefinition.class);
	
	//Cucumber Hooks - @After()
	   @After()
	   public void tornDown() 
	   { 
		driver.quit(); 
	   }
	 	
		//Cucumber Hooks - @Before()
	   @Before()
	   public void beforeScenario(Scenario scenario) {
		GlobalData.scenario = scenario;

		System.out.println("Reading Config...");
		Configuration config = new Configuration(".\\config.properties");
		config.ReadProperty();

		GlobalData.DataFilePath = GlobalData.ConfigData.get("testDataFilePath");

		if (GlobalData.ConfigData.get("browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", GlobalData.ConfigData.get("ChromePath"));
			driver = new ChromeDriver();
		} else if (GlobalData.ConfigData.get("browser").equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", GlobalData.ConfigData.get("FirefoxPath"));
			driver = new FirefoxDriver();
		} else if (GlobalData.ConfigData.get("browser").equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", GlobalData.ConfigData.get("IEPath"));
			driver = new InternetExplorerDriver();
		} else
			System.out.println("Invalid browser");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Given("Scenario Name as {string}")
	public void scenario_Name_as(String scenarioName) {
//		GlobalData.TestCaseName = scenarioName;
		GlobalData.TestScenarioName = scenarioName;
		System.out.println("Am here: "+scenarioName);	
		}

	@Given("DataSheet as {string}")
	public void datasheet_as(String DataSheetName) {
		GlobalData.DataSheetName = DataSheetName;
		ReusableFunctions.readTestData(GlobalData.TestScenarioName);
	}

	@Given("User navigates to Url")
	public void user_navigates_to_Url() {
		driver.get(GlobalData.ConfigData.get("backbase_url"));
		String title = driver.getTitle();
		System.out.println("Title of page is: "+title);
		Assert.assertEquals(title, "Conduit");
	}

	@Given("User logs into the application")
	public void user_logs_into_the_application() {
		loginApp = new LoginApp(driver);
		String status = loginApp.login();
		Assert.assertEquals(status, "pass");
	}

	@When("User tries to add an Article then Article should be added successfully")
	public void user_tries_to_add_an_Article_then_Article_should_be_added_successfully() {
		article = new Article(driver);
		String status = article.addArticle();
		Assert.assertEquals(status, "pass");
	}

	@When("User tries to fetch an Article then Article should be retrieved successfully")
	public void user_tries_to_fetch_an_Article_then_Article_should_be_retrieved_successfully() {
		article = new Article(driver);
		String status = article.fetchArticle();
		Assert.assertEquals(status, "pass");
	}

	@When("User tries to update an Article then Article should be modified successfully")
	public void user_tries_to_update_an_Article_then_Article_should_be_modified_successfully() {
		article = new Article(driver);
		String status = article.updateArticle();
		Assert.assertEquals(status, "pass");
	}
	
	@When("User tries to delete an Article then Article should be deleted successfully")
	public void user_tries_to_delete_an_Article_then_Article_should_be_deleted_successfully() {
		article = new Article(driver);
		String status = article.deleteArticle();
		Assert.assertEquals(status, "pass");
	}
	
	@Then("User logs out of the application")
	public void user_logs_out_of_the_application() {
		loginApp = new LoginApp(driver);
		String status = loginApp.logout();
		Assert.assertEquals(status, "pass");
	}

	

}
