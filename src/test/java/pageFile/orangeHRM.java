package pageFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class orangeHRM {
    WebDriver driver;
    FileInputStream credFile;
    Workbook credsWB;
    Sheet credSheet;

    {
        try {
            credFile= new FileInputStream("src/test/java/defaultData/orangeHRMCreds.xlsx");
            credsWB = new XSSFWorkbook(credFile);
            credSheet=credsWB.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void userOpenOrangehrmWebpage(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless");

        URL u;
        try {
            u= new URL("http://192.168.0.66:4444");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver= new RemoteWebDriver(u,options);
//        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }
    public void userEnterValidUsername(){
        By usernameLocator=By.xpath("//input[@name='username']");
        Row r=credSheet.getRow(0);
        Cell c=r.getCell(0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(usernameLocator).sendKeys(c.getStringCellValue());
    }
    public void userEnterValidPassword(){
        By passwordLocator=By.xpath("//input[@name='password']");
        Row r=credSheet.getRow(0);
        Cell c=r.getCell(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(passwordLocator).sendKeys(c.getStringCellValue());
    }
    public void userClickOnLoginButton(){
        By submitLocator=By.xpath("//button[@type='submit']");
        driver.findElement(submitLocator).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void userVerifyLoginSucces(){
        String actualtitle= driver.getTitle();
        Assert.assertEquals("OrangeHRM",actualtitle);
        driver.quit();
    }
    public void userEnterInValidUsername(){
        By usernameLocator=By.xpath("//input[@name='username']");
        Row r=credSheet.getRow(1);
        Cell c=r.getCell(0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(usernameLocator).sendKeys(c.getStringCellValue());
    }
    public void userEnterInValidPassword(){
        By passwordLocator=By.xpath("//input[@name='password']");
        Row r=credSheet.getRow(1);
        Cell c=r.getCell(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(passwordLocator).sendKeys(c.getStringCellValue());
    }
    public void userVerifyInvalidUiResponse(){
        By invalidMsg=By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(driver.findElement(invalidMsg).isDisplayed());
        driver.quit();
    }
}
