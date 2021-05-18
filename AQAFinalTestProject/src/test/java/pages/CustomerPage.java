package pages;

import helpers.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    public WebElement loginButton;

    @FindBy(xpath = "//button[contains(text(),'Home')]")
    public WebElement homeButtom;

    @FindBy(xpath = "//button[contains(text(),'Deposit')]")
    public WebElement depositButton;

    @FindBy(xpath = "//button[contains(text(),'Transactions')]")
    public WebElement transactionsButton;

    @FindBy(xpath = "//button[contains(text(),'Withdrawl')]")
    public WebElement withdrawlButton;

    @FindBy(xpath = "(//button[contains(text(),'Withdraw')])[2]")
    public WebElement makeWithdrawlButton;

    @FindBy(xpath = "//input[@placeholder='amount']")
    public WebElement amountField;

    @FindBy(xpath = "(//button[contains(text(),'Deposit')])[2]")
    public WebElement makeDepositButton;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement depositAmountCell;

    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    public WebElement depositTransactionTypeCell;

    @FindBy(xpath = "//tbody/tr[2]/td[2]")
    public WebElement withdrawAmountCell;

    @FindBy(xpath = "//tbody/tr[2]/td[3]")
    public WebElement withdrawTransactionTypeCell;

    public CustomerPage(WebDriver driver) {
        super(driver);
    }
}
