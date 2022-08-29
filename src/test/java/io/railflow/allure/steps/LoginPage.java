package io.railflow.allure.steps;

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
		this.driver.findElement(By.name("username")).sendKeys(strUserName);
	}

	@Step("Set password")
	public void setPassword(final String strPassword) {
		this.driver.findElement(By.name("password")).sendKeys(strPassword);
	}

	@Step("Click login button")
	public void clickLogin() {
		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[1]/form/button")).click();
	}

	@Step("Verify title of Login Page")
	public void verifyPageTitle() {
		final String loginPageTitle = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[1]/h1")).getText();
		assertTrue(loginPageTitle.contains("Sign in"));
	}

	@Step("Verify error message when invalid credential is provided")
	public void verifyErrorMessage() {
		final String invalidCredentialErrorMessage = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[1]/div[1]/div[2]")).getText();
		assertTrue(invalidCredentialErrorMessage.contains("Username or password is invalid"));
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
