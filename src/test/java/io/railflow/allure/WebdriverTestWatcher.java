package io.railflow.allure;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class WebdriverTestWatcher implements TestWatcher {

	private WebDriver webDriver;

	@Override
	public void testFailed(final ExtensionContext context, final Throwable cause) {
		try {
			if (this.webDriver != null) {
				Allure.addAttachment("failure.png", new ByteArrayInputStream(((TakesScreenshot) this.webDriver).getScreenshotAs(OutputType.BYTES)));
			}
		} finally {
			disposeDriver();
		}
	}

	public void setWebDriver(final WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@Override
	public void testDisabled(final ExtensionContext context, final Optional<String> reason) {
		disposeDriver();
	}

	@Override
	public void testSuccessful(final ExtensionContext context) {
		disposeDriver();
	}

	@Override
	public void testAborted(final ExtensionContext context, final Throwable cause) {
		disposeDriver();
	}

	@Step("Close WebDriver")
	private void disposeDriver() {
		if (this.webDriver != null) {
			this.webDriver.quit();
			this.webDriver = null;
		}
	}

}
