package tests;

import helpers.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ManagerPage;

import static javax.swing.text.html.CSS.getAttribute;

public class CustomerCreationValidationTest extends BaseTest {
    @Test
    public void customerCreationValidationTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.managerLogin();
        ManagerPage managerPage = new ManagerPage(driver);
        wait.until(ExpectedConditions.visibilityOf(managerPage.addCustomerMenuButton));
        managerPage.addCustomerMenuButton.click();

        //Check First Name field
        wait.until(ExpectedConditions.visibilityOf(managerPage.addCustomerSubmitButton));
        managerPage.addCustomerSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@placeholder='First Name']"))));
        String expectedMessage = "Please fill in this field.";
        String message = driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("validationMessage");
        Assert.assertEquals(message,expectedMessage);

        //Check Last Name field
        managerPage.firstNameTextField.sendKeys("First");
        managerPage.addCustomerSubmitButton.click();
        message = driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("validationMessage");
        Assert.assertEquals(message,expectedMessage);

        //Check Post Code field
        managerPage.lastNameTextField.sendKeys("Last");
        managerPage.addCustomerSubmitButton.click();
        message = driver.findElement(By.xpath("//input[@placeholder='Post Code']")).getAttribute("validationMessage");
        Assert.assertEquals(message,expectedMessage);
    }
}
