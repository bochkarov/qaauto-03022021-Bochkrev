package tests;

import helpers.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.LoginPage;
import pages.ManagerPage;

public class CustomerCreationVerificationTest extends BaseTest {


    @Test
    public void customerCreationVerificationTest() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.managerLogin();
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.addCustomer(firstName, lastName, postCode);
        managerPage.isCustomerCreated(driver, firstName, lastName, postCode);
        managerPage.clickHomeButton();
        loginPage.customerLoginButton.click();
        CustomerPage customerPage = new CustomerPage(driver);
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(By.id("userSelect")));
        select.click();
        WebElement selectCustomer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'"+firstName+" "+lastName+"')]")));
        selectCustomer.click();
        customerPage.loginButton.click();
        isElementPresent(By.xpath("//span[contains(text(),'Please open an account with us.')]"));
    }


}
