package io.railflow.allure.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class LoginPage {
	private final WebDriver driver;

	public LoginPage(final WebDriver driver) {
		this.driver = driver;
	}

	@Step("Set username")
	public void setUserName(final String strUserName) {
		this.driver.findElement(By.id("email")).sendKeys(strUserName);
	}

	@Step("Set password")
	public void setPassword(final String strPassword) {
		this.driver.findElement(By.id("password")).sendKeys(strPassword);
	}

	@Step("Click login button")
	public void clickLogin() {
		this.driver.findElement(By.id("loginButton")).click();
	}

	@Step("Verify title of Login Page")
	public void verifyPageTitle() {
		final String loginPageTitle = this.driver.findElement(By.xpath("/html/body/div[4]/form/h2")).getText();
		assertEquals("Account Login", loginPageTitle);
	}

	@Step("Verify error message when invalid credential is provided")
	public void verifyErrorMessage() {
		final String invalidCredentialErrorMessage = this.driver.findElement(By.xpath("/html/body/div[4]/form/div[1]/li")).getText();
		assertTrue(invalidCredentialErrorMessage.contains("These credentials do not match our records"));
	}

	@Step("Enter username and password")
	public void login(final String username, final String password) {

		// Fill username
		this.setUserName(username);

		// Fill password
		this.setPassword(password);

		// Click Login button
		this.clickLogin();
	}
}
