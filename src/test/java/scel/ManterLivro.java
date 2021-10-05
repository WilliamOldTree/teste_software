package scel;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManterLivro {
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void cadastrarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("1313");
		driver.findElement(By.id("autor")).sendKeys("Romario");
		driver.findElement(By.id("titulo")).sendKeys("O Baixinho");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		assertTrue(driver.findElements(By.xpath("//tr[contains(.,\'1313\')]")).size() == 1);
		
		driver.findElement(By.xpath("//tr[contains(.,\'1313\')]/td[5]/div/a[2]")).click();
	}

	@Test
	public void editarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("1314");
		driver.findElement(By.id("autor")).sendKeys("Mateus");
		driver.findElement(By.id("titulo")).sendKeys("Principe");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("a[href=\"/sig/livros/1314\"]")).click();
		driver.findElement(By.id("autor")).clear();
		driver.findElement(By.id("autor")).sendKeys("Luiz");
		driver.findElement(By.id("titulo")).clear();
		driver.findElement(By.id("titulo")).sendKeys("Romeu");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		assertTrue(driver.findElement(By.xpath("//tr[contains(.,\'1314\')]/td[3]")).getText().equals("Romeu"));
		assertTrue(driver.findElement(By.xpath("//tr[contains(.,\'1314\')]/td[4]")).getText().equals("Luiz"));

		driver.findElement(By.xpath("//tr[contains(.,\'1314\')]/td[5]/div/a[2]")).click();
	}

	@Test
	public void buscarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("1315");
		driver.findElement(By.id("autor")).sendKeys("Maquiavel");
		driver.findElement(By.id("titulo")).sendKeys("O Principe");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		assertTrue(driver.findElements(By.xpath("//tr[contains(.,\'1315\')]")).size() == 1);
		
		driver.findElement(By.xpath("//tr[contains(.,\'1315\')]/td[5]/div/a[2]")).click();
	}

	@Test
	public void deletarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("1316");
		driver.findElement(By.id("autor")).sendKeys("Sonhos de verão");
		driver.findElement(By.id("titulo")).sendKeys("Corinthians tem 2 mundiais");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//tr[contains(.,\'1316\')]/td[5]/div/a[2]")).click();
		assertTrue(driver.findElements(By.xpath("//tr[contains(.,\'1316\')]")).size() == 0);
	}
}