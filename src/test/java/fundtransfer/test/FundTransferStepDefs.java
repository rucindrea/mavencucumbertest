package fundtransfer.test;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FundTransferStepDefs {

	protected WebDriver driver;
    private Scenario scenario;
	private int counter = 0;
	
	
	@Before
	public void setUp(Scenario scenario) {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		driver = new ChromeDriver();
        this.scenario = scenario;
		
	}




	@Given("^I am on the Transfer Funds Online Page$")
	public void I_am_on_the_Transfer_Funds_Online_Page() throws Throwable {
		driver.get("http://dl.dropbox.com/u/55228056/fundTransfer.html");
	}

	@Given("^I have (\\d+) in my account$")
	public void I_have_in_my_account(int initialBalance) throws Throwable {
		setupInitialBalance(initialBalance);
	}

	@When("^I transfer (\\d+) to \"([^\"]*)\"$")
	public void I_transfer_to(int amount, String payeeName) throws Throwable {
		transferAmountTo(amount, payeeName);
	}


	@Then("^I should see \"([^\"]*)\"$")
	public void I_should_see(String message) throws Throwable {
		checkTransferMessage(message);
	}


	@Then("^I should have (\\d+) left in my account$")
	public void I_should_have_left_in_my_account(int balance) throws Throwable {
		checkRemainingBalance(balance);
	}

	@After
	public void tearDown(Scenario scenario) {
        if(scenario.isFailed()) {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            byte[] screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
		driver.close();
	}


	//helper functions

	private void setupInitialBalance(int initialBalance) throws Throwable {
		if (initialBalance != 2000) {
			int toTransder = 0 - (initialBalance - 2000);
			I_transfer_to(toTransder, "Jim");
		}
	}


	private void transferAmountTo(int amount, String payeeName) {
		WebElement payeeField = driver.findElement(By.id("payee"));
		WebElement amountField = driver.findElement(By.id("amount"));
		payeeField.clear();
		payeeField.sendKeys(payeeName);
		amountField.clear();
		amountField.sendKeys(String.valueOf(amount));
		driver.findElement(By.id("transfer")).click();
	}

	private void checkRemainingBalance(int balance) {
		WebElement actualBalanceElement = driver.findElement(By.name("balance"));
		String actualBalance = actualBalanceElement.getAttribute("value");
		if (String.valueOf(balance).contains(".")) {
			assertEquals(balance, actualBalance);
		}
		else {
			String expectedBalance = String.valueOf(balance) + ".00";
			assertEquals(expectedBalance, actualBalance);
		}
	}


	private void checkTransferMessage(String message) {
		WebElement actualMessage = driver.findElement(By.id("message"));
		assertEquals(actualMessage.getText(),message);
	}

}