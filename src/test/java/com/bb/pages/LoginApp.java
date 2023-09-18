package com.bb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.util.GlobalData;
import com.bb.util.ReusableFunctions;

public class LoginApp {

private WebDriver driver;
	
	// Page Object Design Pattern - web elements for the pages are defined in the page layer itself
	public LoginApp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private static Logger log = LoggerFactory.getLogger(LoginApp.class);
	
	@FindBy(linkText = "Sign in")
	private WebElement signIn;
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInButton;
	
	@FindBy(xpath = "//a[@class='nav-link']//i[@class='ion-compose']")
	private WebElement newArticle;
	
	@FindBy(xpath = "//a[@routerlink='/settings']")
	private WebElement settings;
	
	@FindBy(xpath = "//button[contains(text(),' Or click here to logout. ')]")
	private WebElement logoutButton;
	
	//Function to login into the application
	public String login() {
		try {		
		signIn.click();
		ReusableFunctions.waitForElementClickable(driver, email, 20);
		ReusableFunctions.inputText(email, GlobalData.ConfigData.get("username"));
		ReusableFunctions.inputText(password, GlobalData.ConfigData.get("passcode"));
		ReusableFunctions.inputText(password, GlobalData.ConfigData.get("passcode"));
		signInButton.click();
		if(ReusableFunctions.waitForElementClickable(driver, newArticle, 20).equalsIgnoreCase("pass")) {
			ReusableFunctions.captureScreenshot(driver);
			System.out.println("Login successful");
			return "pass";			
		}
		}
		catch(Exception e) {
		e.printStackTrace();
		return "fail";
		}
		return "fail";
	}
	
	
	//Function to logout from the application
	public String logout() {
		try {		
		settings.click();
		logoutButton.click();
		if(ReusableFunctions.waitForElementClickable(driver, signIn, 20).equalsIgnoreCase("pass")) {
			ReusableFunctions.captureScreenshot(driver);
			System.out.println("Logout successful");
			return "pass";			
		  }
		}
		catch(Exception e) {
		e.printStackTrace();
		return "fail";
		}
		return "fail";
	}

}
