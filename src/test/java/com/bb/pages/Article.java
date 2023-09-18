package com.bb.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bb.util.GlobalData;
import com.bb.util.ReusableFunctions;

public class Article {

	private WebDriver driver;

	// Page Object Design Pattern - web elements for the pages are defined in the
	// page layer itself
	public Article(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private static Logger log = LoggerFactory.getLogger(Article.class);

	@FindBy(xpath = "//a[@class='nav-link']//i[@class='ion-compose']")
	private WebElement newArticle;

	@FindBy(xpath = "//input[@placeholder='Article Title']")
	private WebElement articleTitle;

	@FindBy(xpath = "//input[@formcontrolname='description']")
	private WebElement articleAbout;

	@FindBy(xpath = "//textarea[@formcontrolname='body']")
	private WebElement articleBody;

	@FindBy(xpath = "//input[@placeholder='Enter tags']")
	private WebElement articleTags;

	@FindBy(xpath = "//button[contains(text(),'Publish Article')]")
	private WebElement publishArticle;

	@FindBy(xpath = "//button[contains(text(),'Post Comment')]")
	private WebElement verifyArticle;

	@FindBy(xpath = "//a[contains(text(),'Global Feed')]")
	private WebElement globalFeed;

	@FindBy(xpath = "//h1[contains(text(),'Back Base- the Bank')]")
	private WebElement verifyArticleTitle;

	@FindBy(xpath = "//div[contains(text(),' No articles are here... yet.')]")
	private WebElement deleteVerify;

	// Function to add the Article post logging into the application
	public String addArticle() {
		try {
			newArticle.click();
			ReusableFunctions.inputText(articleTitle, GlobalData.DataElements.get("Article_Title"));
			ReusableFunctions.inputText(articleAbout, GlobalData.DataElements.get("Article_About"));
			ReusableFunctions.inputText(articleBody, GlobalData.DataElements.get("Article_Body"));
			ReusableFunctions.inputText(articleTags, GlobalData.DataElements.get("Article_Tags"));
			ReusableFunctions.captureScreenshot(driver);
			publishArticle.click();
			if (ReusableFunctions.waitForElementVisibility(driver, verifyArticle, 20).equalsIgnoreCase("pass")) {
				ReusableFunctions.captureScreenshot(driver);
				return "pass";
			} else {
				System.out.println("Article creation failed");
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// Function to fetch the Article post logging into the application
	public String fetchArticle() {
		try {
			globalFeed.click();
//				WebDriverWait dyWait = new WebDriverWait(driver,20);
//				dyWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("h1")));
//				List<WebElement> articles = driver.findElements(By.xpath("//h1[contains(text(),'Back Base- the Bank')]"));
//				articles.get(0).click();	

			if (ReusableFunctions.waitforElementList(driver, "h1", "//h1[contains(text(),'Back Base- the Bank')]")
					.equals("pass")) {
				ReusableFunctions.captureScreenshot(driver);

				if (ReusableFunctions.waitForElementVisibility(driver, verifyArticle, 20).equalsIgnoreCase("pass")) {
					ReusableFunctions.captureScreenshot(driver);
					System.out.println("Article fetched successfully");
					return "pass";
				} else {
					System.out.println("Opening Article failed");
					return "fail";
				}
			} else {
				System.out.println("Article fetch failed");
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Article fetch failed");
			return "fail";
		}
	}

	// Function to update the Article post logging into the application
	public String updateArticle() {
		try {
			globalFeed.click();

			/*
			 * WebDriverWait dyWait = new WebDriverWait(driver, 20);
			 * dyWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(
			 * "h1"))); List<WebElement> articles =
			 * driver.findElements(By.xpath("//h1[contains(text(),'Back Base- the Bank')]"))
			 * ; articles.get(0).click();
			 */

			if (ReusableFunctions.waitforElementList(driver, "h1", "//h1[contains(text(),'Back Base- the Bank')]")
					.equals("pass")) {

				if (ReusableFunctions.waitForElementVisibility(driver, verifyArticle, 20).equalsIgnoreCase("pass")) {
					WebElement editArticleButton = driver.findElements(By.cssSelector("a.btn")).get(0);
					editArticleButton.click();
					ReusableFunctions.waitForElementClickable(driver, articleTitle, 20);
					ReusableFunctions.inputText(articleTitle, GlobalData.DataElements.get("Article_Title"));
					ReusableFunctions.waitForElementClickable(driver, articleAbout, 20);
					ReusableFunctions.inputText(articleAbout, GlobalData.DataElements.get("Article_About"));
					ReusableFunctions.waitForElementClickable(driver, articleBody, 20);
					ReusableFunctions.inputText(articleBody, GlobalData.DataElements.get("Article_Body"));
					ReusableFunctions.waitForElementClickable(driver, articleTags, 20);
					ReusableFunctions.inputText(articleTags, GlobalData.DataElements.get("Article_Tags"));
					ReusableFunctions.captureScreenshot(driver);
					publishArticle.click();
					if (ReusableFunctions.waitForElementVisibility(driver, verifyArticle, 20)
							.equalsIgnoreCase("pass")) {
						ReusableFunctions.captureScreenshot(driver);
						return "pass";
					} else {
						System.out.println("Article update failed");
						return "fail";
					}
				} else {
					System.out.println("Article opening failed");
					return "fail";
				}

			} else {
				System.out.println("Article fetch failed");
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// Function to delete the Article post logging into the application
	public String deleteArticle() {
		try {
			globalFeed.click();

			/*
			 * WebDriverWait dyWait = new WebDriverWait(driver, 20);
			 * dyWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(
			 * "h1"))); List<WebElement> articles = driver
			 * .findElements(By.xpath("//h1[contains(text(),'Back Base- updated Title')]"));
			 * articles.get(0).click();
			 */

			if (ReusableFunctions.waitforElementList(driver, "h1", "//h1[contains(text(),'Back Base- updated Title')]").equals("pass")) {

				if (ReusableFunctions.waitForElementClickable(driver, verifyArticle, 20).equalsIgnoreCase("pass")) {

					WebElement deleteArticleButton = driver
							.findElements(By.xpath("//button[contains(@class,'btn btn-sm btn-outline-danger')]"))
							.get(0);
					System.out.println("Element enabled: " + deleteArticleButton.isEnabled());
					deleteArticleButton.click();
					if (ReusableFunctions.waitForElementVisibility(driver, deleteVerify, 20).equalsIgnoreCase("pass")) {
						ReusableFunctions.captureScreenshot(driver);
						return "pass";
					} else {
						System.out.println("Article deletion failed");
						return "fail";
					}
				} else {
					System.out.println("Article opening failed");
					return "fail";
				}
			} else {
				System.out.println("Article fetch failed");
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
