package fundtransfer.test;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

public class FundTransferStepDefs {

	protected WebDriver driver;
    private Scenario scenario;
	
	
	@Before
	public void setUp(Scenario scenario) {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		driver = new ChromeDriver();
        this.scenario = scenario;
		
	}
	@Given("^I am on Fund Transfer Page$")
	public void I_am_on_Fund_Transfer_Page() {
		driver.get("http://dl.dropbox.com/u/55228056/fundTransfer.html");
	}

	@When("^I enter \"([^\"]*)\" as payee name$")
	public void I_enter_as_payee_name(String payeeName) {
		driver.findElement(By.id("payee")).sendKeys(payeeName);
	}

	@When("^I enter \"([^\"]*)\" as amount$")
	public void I_enter_as_amount(String amount) {
		driver.findElement(By.id("amount")).sendKeys(amount);
	}

	@When("^I confirm the transfer$")
	public void I_confirm_the_transfer() {
		driver.findElement(By.id("transfer")).click();
	}

	@Then("^the money is transfered with \"([^\"]*)\" message$")
	public void the_money_is_transfered_with_message(String msg) {
		WebElement message = driver.findElement(By.id("message"));
		assertEquals(message.getText(),msg);
	}

	@Then("^I see a failure message \"([^\"]*)\" displayed$")
	public void I_see_a_failure_message_displayed(String msg) {
		WebElement message = driver.findElement(By.id("message"));
		assertEquals(message.getText(),msg);
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