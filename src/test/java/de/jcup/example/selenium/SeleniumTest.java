package de.jcup.example.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.net.URLConnection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


class SeleniumTest {

	private static ChromeDriver driver;

	@BeforeAll
	static void beforeAll() {
		driver = new ChromeDriver();
	}
	
	@Test
	void open_localhost_url_enter_text_click_and_check_title_changed() throws Exception {
		/* execute  */
		driver.get("http://localhost:8080");
		
		String titleBeforeClick = driver.getTitle();
		WebElement inputField = driver.findElement(By.xpath("html/body/form/p[1]/input[1]"));
		WebElement submitButton = driver.findElement(By.xpath("html/body/form/p[2]/input[1]"));
		
		inputField.sendKeys("de-jcup");
		submitButton.click();
		
		String titleAfterClick = driver.getTitle();
		
		/* test */
		assertEquals("Welcome",titleBeforeClick);
		assertEquals("Welcome de-jcup",titleAfterClick);
		
	}
	
	@AfterAll
	static void afterAll() { 
		driver.quit();
	}

}
