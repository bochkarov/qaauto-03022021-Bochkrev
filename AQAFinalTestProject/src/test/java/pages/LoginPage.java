package pages;

import helpers.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//button[contains(text(),'Bank Manager Login')]")
    public WebElement bankManagerLoginButton;

    @FindBy(xpath = "//button[contains(text(),'Customer Login')]")
    public WebElement customerLoginButton;

    WebDriverWait wait = new WebDriverWait(driver,10);

    public LoginPage(WebDriver driver) { super(driver); }

    public ManagerPage managerLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(bankManagerLoginButton));
        bankManagerLoginButton.click();
        if (driver.getCurrentUrl().contains("/manager"))
            return new ManagerPage(driver);
        return null;
    }

    public CustomerPage customerLogin(String firstName, String lastName) {
        customerLoginButton.click();
        CustomerPage customerPage = new CustomerPage(driver);
        WebElement selectUser = wait.until(ExpectedConditions.elementToBeClickable(By.id("userSelect")));
        selectUser.click();
        WebElement customerLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'"+firstName+" "+lastName+"')]")));
        customerLogin.click();
        customerPage.loginButton.click();
        if (driver.getCurrentUrl().contains("/customer"))
            return new CustomerPage(driver);
        return null;
    }
}
