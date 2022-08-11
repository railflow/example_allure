package io.railflow.allure.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {
    WebDriver driver;

    By userName = By.name("txtUsername");
    By password = By.name("txtPassword");
    By titleText = By.id("logInPanelHeading");
    By login = By.id("btnLogin");
    By errorMessage = By.id("spanMessage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Set user name in textbox
    public void setUserName(String strUserName) {
        driver.findElement(userName).sendKeys(strUserName);
    }

    // Set password in password textbox
    public void setPassword(String strPassword) {
        driver.findElement(password).sendKeys(strPassword);
    }

    // Click on login button
    public void clickLogin() {
        driver.findElement(login).click();
    }

    @Step("Verify title of Login Page")
    public void verifyPageTitle() {
        String loginPageTitle = driver.findElement(titleText).getText();
        assertTrue(loginPageTitle.contains("LOGIN Panel"));
    }

    @Step("Verify error message when invalid credentail is provided")
    public void verifyErrorMessage() {
        String invalidCredentialErrorMessage = driver.findElement(errorMessage).getText();
        assertTrue(invalidCredentialErrorMessage.contains("Incorrect Credentials"));
    }

    @Step("Enter username and password")
    public void login(String strUserName, String strPasword) {

        // Fill user name
        this.setUserName(strUserName);

        // Fill password
        this.setPassword(strPasword);

        // Click Login button
        this.clickLogin();
    }
}
