package io.railflow.allure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.railflow.allure.steps.LoginPage;

@Epic("WebDriver and Allure Railflow Demo")
@Owner("John Doe")
public class AllureDemoTest {
	@RegisterExtension
	WebdriverTestWatcher watcher = new WebdriverTestWatcher();
	private WebDriver driver;

	@BeforeAll
	public static void setupDriver() {
		WebDriverManager.firefoxdriver().setup();
	}

	@Step("Start web driver")
	@BeforeEach
	public void setup() {
		this.driver = new FirefoxDriver();
		this.watcher.setWebDriver(this.driver);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.driver.get("https://rwa.railflow.io/");
	}

	@Severity(SeverityLevel.NORMAL)
	@Test
	@DisplayName("Verify Login Page Title")
	@Description("Verify the title of Login Page")
	@Feature("Login Page Tests")
	@Story("Title of Login Page")
	public void verify_login_page() {
		final LoginPage loginPage = new LoginPage(this.driver);
		loginPage.verifyPageTitle();
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test
	@DisplayName("Unsuccessful Login to Application")
	@Description("Login Test with invalid credentials")
	@Feature("Login Page Tests")
	@Story("Unsuccessful Login to Application")
	public void invalid_credentials_error() {
		final LoginPage loginPage = new LoginPage(this.driver);
		loginPage.login("test", "password");
		loginPage.verifyErrorMessage();

	}

	@Severity(SeverityLevel.NORMAL)
	@Test
	@DisplayName("Verify Success Login")
	@Description("Verify that authorized user can log in to application")
	@Feature("Login Page Tests")
	@Story("Verify Success Login")
	public void verify_success_login() {
		final LoginPage loginPage = new LoginPage(this.driver);
		loginPage.setUserName("Katharina_Bernier");
		loginPage.setPassword("s3cret");
		loginPage.clickLogin();
		assertLoginName();
	}

	@Step("Verify Login Name Displayed Properly")
	private void assertLoginName() {
		assertEquals("Katharina Bernier", this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[2]/h6[2]")).getText());
	}

}
