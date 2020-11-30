package br.ce.samucka.tasks.prod;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HealtCheckIT {
	
	
	
	@Test
	public void HealtCheck() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://172.20.10.2:9999/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		try {
			driver.navigate().to("http://172.20.10.2:9999/tasks/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			//fechar o browser
			driver.quit();
		}
						
	}
}
