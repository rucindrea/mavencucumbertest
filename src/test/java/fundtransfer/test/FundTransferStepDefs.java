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
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		//driver = new ChromeDriver();
        this.scenario = scenario;
		
		//driver = new HtmlUnitDriver();
	}
	
	@Given("the user is on Fund Transfer View")
	public void The_user_is_on_fund_transfer_page() {


		driver.get("http://dl.dropbox.com/u/55228056/fundTransfer.html");
	}
		
	
	@When("he enters \"([^\"]*)\" as payee name")
	public void He_enters_payee_name(String payeeName) {
		driver.findElement(By.id("payee")).sendKeys(payeeName);
	}
	
	@And("he enters \"([^\"]*)\" as amount")
	public void He_enters_amount(String amount) {
		driver.findElement(By.id("amount")).sendKeys(amount);
	}
	
	@And("he Submits request for Fund Transfer")
	public void He_submits_request_for_fund_transfer() {
		driver.findElement(By.id("transfer")).click();
	}
	
	@Then("ensure the fund transfer is complete with \"([^\"]*)\" message")
	public void Ensure_the_fund_transfer_is_complete(String msg) {
		WebElement message = driver.findElement(By.id("message"));
		assertEquals(message.getText(),msg);
	}
	
	@Then("ensure a transaction failure message \"([^\"]*)\" is displayed")
	public void Ensure_a_transaction_failure_message(String msg) {
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