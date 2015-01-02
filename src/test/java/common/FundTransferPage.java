package common;

import fundtransfer.test.FundTransferStepDefs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;

import static org.junit.Assert.assertEquals;

/**
 * Created by rucindrea on 1/1/15.
 */

public class FundTransferPage {

    private final WebDriver driver;

    public FundTransferPage(WebDriver wd) {
        //instantiates the webdriver passed from test class
        this.driver = wd;
    }

    //Fund Transfer Page Web Elements
    public String pageUrl = "http://dl.dropbox.com/u/55228056/fundTransfer.html";
    public By payeeField = By.id("payee");
    public By amountField = By.id("amount");
    public By transferButton = By.id("transfer");
    public By balanceField = By.name("balance");
    public By messageField = By.id("message");
    public By titleField = By.xpath("//header/h2");
    public By subTitleField = By.xpath("//header/div");

    //Constants
    public final String title = "Transfer Funds Online";
    public final String subTitle = "Enter a Payee and amount and transfer funds instantly!!";

    //Fund Transfer Page methods/actions
    public void goToFundsTransferPage() {
        driver.get(pageUrl);
        assertEquals(title, driver.findElement(titleField).getText());
        assertEquals(subTitle, driver.findElement(subTitleField).getText());

    }
    public void setupInitialBalance(int initialBalance) {
        if (initialBalance != 2000) {
            int toTransfer = 0 - (initialBalance - 2000);
            transferAmountTo(toTransfer, "Jim");
        }
    }
    public void transferAmountTo(int amount, String payeeName) {
        driver.findElement(payeeField).clear();
        driver.findElement(payeeField).sendKeys(payeeName);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(String.valueOf(amount));
        driver.findElement(transferButton).click();
    }
    public void checkRemainingBalance(int expectedBalance) {
        if (String.valueOf(expectedBalance).contains(".")) {
            assertEquals(expectedBalance, driver.findElement(balanceField).getAttribute("value"));
        } else {
            String expectedBalanceWithDigits = String.valueOf(expectedBalance) + ".00";
            assertEquals(expectedBalanceWithDigits, driver.findElement(balanceField).getAttribute("value"));
        }
    }
    public void checkTransferMessage(String expectedMessage) {
        String message = driver.findElement(messageField).getText();
        assertEquals(message, expectedMessage);
    }
}



