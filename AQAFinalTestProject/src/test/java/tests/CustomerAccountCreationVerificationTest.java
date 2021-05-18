package tests;

import helpers.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.LoginPage;
import pages.ManagerPage;

public class CustomerAccountCreationVerificationTest extends BaseTest {

    @Test
    public void customerAccountCreationVerificationTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.managerLogin();
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.addCustomer(firstName, lastName, postCode);
        managerPage.openAccountButton.click();
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(By.id("userSelect")));
        select.click();
        WebElement selectCustomer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'"+firstName+" "+lastName+"')]")));
        selectCustomer.click();
        WebElement selectCurrency = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'Dollar')]")));
        selectCurrency.click();
        managerPage.processButton.click();
        managerPage.clickAlert();
        managerPage.clickHomeButton();
        loginPage.customerLoginButton.click();
        CustomerPage customerPage = new CustomerPage(driver);
        WebElement selectUser = wait.until(ExpectedConditions.elementToBeClickable(By.id("userSelect")));
        selectUser.click();
        WebElement customerLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'"+firstName+" "+lastName+"')]")));
        customerLogin.click();
        customerPage.loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(customerPage.depositButton));
        String[] accountInfo = driver.findElement(By.xpath("//div[contains(text(),'Account')]")).getText().split(",");
        String account = accountInfo[0];
        String[] accountArray = account.split(" : ");
        String accountNumber = accountArray[1];
        String trimAccountNumber = accountNumber.trim();
        int intAccountNumber = Integer.parseInt(trimAccountNumber);
        Assert.assertTrue(intAccountNumber>0);

        System.out.println(accountNumber);

        customerPage.depositButton.click();
    }


}
