package tests;

import helpers.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.LoginPage;
import pages.ManagerPage;

public class DepositTest extends BaseTest {
    @Test
    public void depositTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver);

        //Create Customer
        loginPage.managerLogin();
        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.addCustomer(firstName, lastName, postCode);

        //Open account
        managerPage.openAccount(firstName, lastName);
        managerPage.clickAlert();

        //Customer login
        managerPage.clickHomeButton();
        loginPage.customerLogin(firstName,lastName);
        CustomerPage customerPage = new CustomerPage(driver);

        //Make deposit
        wait.until(ExpectedConditions.visibilityOf(customerPage.depositButton));
        customerPage.depositButton.click();
        wait.until(ExpectedConditions.visibilityOf(customerPage.amountField));
        customerPage.amountField.sendKeys("10");
        customerPage.makeDepositButton.click();

        //Check balance
        customerPage.homeButtom.click();
        loginPage.customerLogin(firstName,lastName);
        wait.until(ExpectedConditions.visibilityOf(customerPage.depositButton));
        customerPage.depositButton.click();
        String[] accountInfo = driver.findElement(By.xpath("//div[contains(text(),'Account')]")).getText().split(",");
        String balance = accountInfo[1];
        String[] balanceArray = balance.split(" : ");
        String userBalance = balanceArray[1];
        String trimAccountNumber = userBalance.trim();
        int intUserBalance = Integer.parseInt(trimAccountNumber);
        Assert.assertEquals(intUserBalance, 10);

        //Check Transactions
        customerPage.transactionsButton.click();
        wait.until(ExpectedConditions.visibilityOf(customerPage.depositAmountCell));
        Assert.assertEquals(customerPage.depositAmountCell.getText(),"10");
        Assert.assertEquals(customerPage.depositTransactionTypeCell.getText(),"Credit");
    }
}
