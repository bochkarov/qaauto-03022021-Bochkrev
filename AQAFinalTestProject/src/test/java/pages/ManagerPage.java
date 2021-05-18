package pages;

import helpers.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ManagerPage extends BasePage {

    @FindBy(xpath = "(//button[contains(text(),'Add Customer')])[1]")
    public WebElement addCustomerMenuButton;

    @FindBy(xpath = "//button[contains(text(),'Customers')]")
    WebElement customersMenuButton;

    @FindBy(xpath = "//button[contains(text(),'Open Account')]")
    public WebElement openAccountButton;

    @FindBy(xpath = "//button[contains(text(),'Process')]")
    public WebElement processButton;

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    WebElement deleteCustomerButton;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement firstNameTextField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lastNameTextField;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    public WebElement postCodeTextField;

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    WebElement searchBar;

    @FindBy(xpath = "(//button[contains(text(),'Add Customer')])[2]")
    public WebElement addCustomerSubmitButton;

    @FindBy(xpath = "//button[contains(text(),'Home')]")
    WebElement homeButton;

    WebDriverWait wait = new WebDriverWait(driver, 10);

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        wait.until(ExpectedConditions.elementToBeClickable(addCustomerMenuButton));
        addCustomerMenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(firstNameTextField));
        firstNameTextField.sendKeys(firstName);
        lastNameTextField.sendKeys(lastName);
        postCodeTextField.sendKeys(postCode);
        addCustomerSubmitButton.click();
        clickAlert();
        wait.until(ExpectedConditions.elementToBeClickable(customersMenuButton));
        customersMenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(firstName);
    }

    public void openAccount(String firstName, String lastName) {
        openAccountButton.click();
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(By.id("userSelect")));
        select.click();
        WebElement selectCustomer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'"+firstName+" "+lastName+"')]")));
        selectCustomer.click();
        WebElement selectCurrency = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'Dollar')]")));
        selectCurrency.click();
        processButton.click();
    }

    public boolean isCustomerCreated(WebDriver driver, String firstname, String lastName, String postCode) {
        try {
            String firstNameCell = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
            String lastNameCell = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
            String postCodeCell = driver.findElement(By.xpath("//tbody/tr[1]/td[3]")).getText();
            Assert.assertEquals(firstNameCell, firstname);
            Assert.assertEquals(lastNameCell, lastName);
            Assert.assertEquals(postCodeCell, postCode);
            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickHomeButton() {
        homeButton.click();
    }
}
