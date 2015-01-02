package fundtransfer.test;

import common.FundTransferPage;
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
    public FundTransferPage fundsTransfer;

	@Given("^I am on the Transfer Funds Online Page$")
	public void I_am_on_the_Transfer_Funds_Online_Page() {
		fundsTransfer.goToFundsTransferPage();
	}
	@Given("^I have (\\d+) in my account$")
	public void I_have_in_my_account(int initialBalance) {
		fundsTransfer.setupInitialBalance(initialBalance);
	}
	@When("^I transfer (\\d+) to \"([^\"]*)\"$")
	public void I_transfer_to(int amount, String payeeName) {
		fundsTransfer.transferAmountTo(amount, payeeName);
	}
	@Then("^I should see \"([^\"]*)\"$")
	public void I_should_see(String message) {
		fundsTransfer.checkTransferMessage(message);
	}
	@Then("^I should have (\\d+) left in my account$")
	public void I_should_have_left_in_my_account(int balance) {
		fundsTransfer.checkRemainingBalance(balance);
	}
	@Before
	public void setUp(Scenario scenario) {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		driver = new ChromeDriver();
		this.scenario = scenario;
		fundsTransfer = new FundTransferPage(driver);

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
}


