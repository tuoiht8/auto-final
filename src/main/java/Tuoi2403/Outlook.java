package Tuoi2403;

import org.junit.Assert;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Outlook {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void smokeTest() throws InterruptedException {
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

        clickExcel();
        Thread.sleep(3000);
        switchExcel();
        Thread.sleep(3000);
        checkListtabs();
        clickMoretpls();
    }

    @Test
    public void LoginTest() throws InterruptedException {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("hatuoi958@outlook.com", "tuoi080895","pass"));
        accounts.add(new Account("hatuoi959@outlook.com", "tuoi080895", "fail username"));
        accounts.add(new Account("hatuoi958@outlook.com", "tuoi080896","fail password"));
        webDriver.get("https://outlook.live.com/owa/");
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.findElement(By.xpath("(//a[@class = 'internal sign-in-link' and text()= 'Sign in'])[1]")).click();
        Thread.sleep(2000);

        WebElement emailElement = webDriver.findElement(By.xpath("//input[@type='email']"));
        WebElement passElement = webDriver.findElement(By.xpath("//*[@name='passwd']"));

        for (Account account : accounts) {

            switch (account.getOption()) {
                case "pass":
                    emailElement.sendKeys(account.getUsername());
                    //emailElement.sendKeys(Keys.RETURN);
                    webDriver.findElement(By.xpath("//*[@type = 'submit']")).click();
                    Thread.sleep(2000);
                    //wait.until(ExpectedConditions.visibilityOf(passElement));
                    WebElement passwordField = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='passwd']")));

                    passwordField.sendKeys(account.getPassword());
//                  passElement.sendKeys(Keys.RETURN);
                    webDriver.findElement(By.xpath("//*[@type = 'submit']")).click();
                    Thread.sleep(3000);
                    webDriver.findElement(By.xpath("//input[@type='submit']")).click();
                    Thread.sleep(3000);
                    WebElement loginSuccessElement = webDriver.findElement(By.xpath("//div[@title='Folders']"));
                    Assert.assertTrue(loginSuccessElement.isDisplayed());
                    System.out.println("PASSED");

                    // Quay về trang đăng nhập để thực hiện đăng nhập tiếp theo

                    Thread.sleep(3000);
                    WebElement prfImage = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='O365_HeaderRightRegion']")));
                    prfImage.click();
                    Thread.sleep(3000);
                    webDriver.findElement(By.xpath("//a[@id='mectrl_body_signOut']")).click();
                    Thread.sleep(3000);
                    webDriver.get("https://outlook.live.com/owa/");
                    webDriver.findElement(By.xpath("(//a[@class = 'internal sign-in-link' and text()= 'Sign in'])[1]")).click();
                    Thread.sleep(8000);
                    break;

                case "fail username": {
                    WebElement emailField = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));

                    emailField.sendKeys(account.getUsername());
                    webDriver.findElement(By.xpath("//*[@type = 'submit']")).click();
                    WebElement errorUsernameElement = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='usernameError']")));
                    Assert.assertTrue(errorUsernameElement.isDisplayed());
                    System.out.println("PASSED");

                    webDriver.get("https://outlook.live.com/owa/");
                    webDriver.findElement(By.xpath("(//a[@class = 'internal sign-in-link' and text()= 'Sign in'])[1]")).click();
                    Thread.sleep(8000);
                    break;
                }

                case "fail password": {
                    WebElement emailField = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
                    emailField.sendKeys(account.getUsername());
                    webDriver.findElement(By.xpath("//*[@type = 'submit']")).click();
                    Thread.sleep(3000);
                    WebElement passwrdField = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='passwd']")));
                    passwrdField.sendKeys(account.getPassword());
                    Thread.sleep(3000);
                    webDriver.findElement(By.xpath("//*[@type = 'submit']")).click();
                    WebElement errorPasswordElement = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='passwordError']")));

                    Assert.assertTrue(errorPasswordElement.isDisplayed());
                    System.out.println("PASSED");

                    webDriver.get("https://outlook.live.com/owa/");
                    webDriver.findElement(By.xpath("(//a[@class = 'internal sign-in-link' and text()= 'Sign in'])[1]")).click();
                    Thread.sleep(8000);
                    break;
                }

            }
        }
    }

    @After
    public void tearDown() {
//        webDriver.close();
//        webDriver.quit();
    }
    public  void clickExcel() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement wordButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Word']//button")));
        wordButton.click();
        Thread.sleep(5000);

    }
    public void switchExcel(){
        Set<String> windowHandles =webDriver.getWindowHandles();
        List<String> listtab = new ArrayList<>(windowHandles);
        String currenttab = webDriver.getWindowHandle();
        String exceltab = null;
        for ( String tabX : listtab){
            if (!tabX.equals(currenttab)){
                exceltab = tabX;
            }
        }
        System.out.println("excelTab"+ exceltab);
        webDriver.switchTo().window(exceltab);


    }
    public  void checkListtabs(){
        List<WebElement> Listelement;
        Listelement = webDriver.findElements((By.xpath("//*[contains(@class,'tab-list__header__tabs')]//following-sibling::span[contains(@class,'pill-text')]/div")));
        Assert.assertEquals(3, Listelement.size());
        Assert.assertEquals("Recently opened", Listelement.get(0).getText());
        Assert.assertEquals("Shared", Listelement.get(1).getText());
        Assert.assertEquals("Favorites", Listelement.get(2).getText());
    }

    public void clickMoretpls() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement moretplButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='More templates']")));
        moretplButton.click();
        Thread.sleep(1000);
        WebElement headingTxt = webDriver.findElement(By.xpath("//h1[@id='hero-heading']"));
        Assert.assertEquals("Select a template", headingTxt.getText());
    }
}
