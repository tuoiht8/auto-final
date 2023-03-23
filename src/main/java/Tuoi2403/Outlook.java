package Tuoi2403;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Outlook {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void LoginPageTest() throws InterruptedException {
        webDriver.get("https://outlook.live.com/owa/");
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.findElement(By.xpath("(//a[@class = 'internal sign-in-link' and text()= 'Sign in'])[1]")).click();
        Thread.sleep(2000);
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='email']")));
        emailElement.sendKeys("hatuoi958@outlook.com");
        WebElement nextButton = webDriver.findElement(By.xpath("//*[@id='idSIButton9']"));
        nextButton.click();
        Thread.sleep(5000);
        WebElement passElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='passwd']")));
        passElement.sendKeys("tuoi080895");
        WebElement signinButton = webDriver.findElement(By.xpath("//*[@id='idSIButton9']"));
        signinButton.click();
        Thread.sleep(10000);
        webDriver.findElement(By.xpath("//input[@type='submit']")).click();
//        webDriver.get("https://login.live.com/ppsecure/post.srf");
//        SessionId sessionId =((RemoteWebDriver) webDriver).getSessionId();
//        System.out.println("Session ID : "+ sessionId);
    }

    @After
    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }
}
