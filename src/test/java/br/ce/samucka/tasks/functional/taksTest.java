package br.ce.samucka.tasks.functional;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class taksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("30/12/2020");
			
			//clicar salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			//fechar o browser
			driver.quit();
		}					
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		//clicar no botão Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever a descrição
		driver.findElement(By.id("task")).sendKeys("Erro");
		
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("30/12/2010");
		
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
		} finally {
			//fechar o browser
			driver.quit();
		}
	}
		
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
			
		try {
		//clicar no botão Add Todo
		driver.findElement(By.id("addTodo")).click();
			
		//escrever a descrição
		driver.findElement(By.id("task")).sendKeys("Cad. sem data");
			
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("");
			
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
			
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
		} finally {
			//fechar o browser
			driver.quit();
		}	
	}
		
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
			
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Data antiga");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("30/12/2019");
				
			//clicar salvar
			driver.findElement(By.id("saveButton")).click();
				
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			//fechar o browser
			driver.quit();
		}		
	}
	
}
