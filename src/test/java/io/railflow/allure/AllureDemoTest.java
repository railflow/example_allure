package io.railflow.allure;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.model.Status;
import io.railflow.allure.steps.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Web driver,Calculator and Attachment Testing Using JUnit5")
@Owner("Stephen Smith")
public class AllureDemoTest {
    public WebDriver driver;
    LoginPage loginPage;

    @Step("Start web driver")
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test
    @Description("Verify the title of Login Page")
    @Feature("Login Page Tests")
    @Story("Title of Login Page")
    public void verifyLoginPage() {
        setup();
        loginPage = new LoginPage(driver);
        // Verify login page text
        loginPage.verifyPageTitle();
        close();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Description("Login Test with invalid credentials")
    @Feature("Login Page Tests")
    @Story("Unsuccessful Login to Application")
    public void invalidCredentialTest() {
        setup();
        loginPage = new LoginPage(driver);
        loginPage.login("test", "password");
        // Verify login page text
        loginPage.verifyErrorMessage();
        close();
    }

    @Test
    @DisplayName("Calculator Test")
    @Feature("Calculator Tests")
    @Story("Addition and Multiplication should work together")
    public void calculatorTest() {
        step("Add two numbers: C = A+B");
        step("Multiply two numbers: F = D*E", Status.FAILED);
        step("Verify results", () -> {
            step("C should be 5", Status.SKIPPED);
            step("F should be 8");
        });
        int C = 5;
        int F = 8;
        simpleTestMethod(C, F, 13);
        assertTrue(false);
    }

    @Test
    @DisplayName("Attachment Test")
    @Feature("Attachment Tests")
    @Story("Add image attachment successfully")
    public void annotatedAttachmentTest() throws IOException {
        addImgAttachment();
    }

    @Attachment("Image attachment")
    public byte[] addImgAttachment() throws IOException {
        File img = new File(getClass().getClassLoader().getResource("screenshot.png").getFile());
        return Files.readAllBytes(img.toPath());
    }

    @Step("Add two numbers")
    public void simpleTestMethod(int param1, int param2, int result) {
        step("Adding two numbers: " + param1 + "+" + param2);
        step("Final result should be: "+ result);
    }

    @Step("Close web driver")
    public void close() {
        driver.close();
    }
}
