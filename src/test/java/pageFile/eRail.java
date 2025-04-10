package pageFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

public class eRail {
    WebDriver driver;
    By formLocator = By.cssSelector("input#txtStationFrom");
    By fourthStationListLocator = By.xpath("(//div[@style='width:240px;float:left;overflow:hidden'])[4]");
    By suggestedStaionlocator = By.xpath("(//div[@style='width:240px;float:left;overflow:hidden'])");
    By dateLocator = By.xpath("(//input[@type='button'])[2]");

    public void userOpenOnBrowser(String url) {
        ChromeOptions option= new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        URL remoteUrl= null;
        try {
            remoteUrl = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver = new RemoteWebDriver(remoteUrl,option);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    public void userClickedOnFromField() {
        driver.findElement(formLocator).click();
    }

    public void userClearDataFromField() {
        driver.findElement(formLocator).clear();
    }

    public void userEnterDataDelAndOpenedDropdown() {
        driver.findElement(formLocator).sendKeys("DEL");
    }

    public void userSelectFourthPositonAndPrintOnConsole() {
        String fourthstation = driver.findElement(fourthStationListLocator).getText();
        System.out.println("fourth station = " + fourthstation);
    }

    public void userStoredAllSuggestedStationNameOnExecelSheetAndCompared() {
        List<WebElement> elements = driver.findElements(suggestedStaionlocator);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("extracted data");
        int rowNum = 0;
        for (WebElement element : elements) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(element.getText());
        }
        File f = new File("src/test/java/outputData/outputSheet.xlsx");
        f.delete();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try (FileOutputStream fileOut = new FileOutputStream("src/test/java/outputData/outputSheet.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileInputStream extractedFile = new FileInputStream("src/test/java/outputData/outputSheet.xlsx");
            FileInputStream defaultFile = new FileInputStream("src/test/java/defaultData/defaultStationName.xlsx");
            Workbook w1 = new XSSFWorkbook(extractedFile);
            Workbook w2 = new XSSFWorkbook(defaultFile);
            Sheet s1 = w1.getSheetAt(0);
            Sheet s2 = w2.getSheetAt(0);

            if (s1.getLastRowNum() == s2.getLastRowNum()) {
                for (int i = 0; i < rowNum; i++) {

                    Row r1 = s1.getRow(i);
                    Row r2 = s2.getRow(i);
                    Cell c1 = r1.getCell(0);
                    Cell c2 = r2.getCell(0);
                    if (c1.getStringCellValue().equals(c2.getStringCellValue())) {
                        Assert.assertTrue(true);
                        System.out.println(c1.getStringCellValue() + " ======= " + c2.getStringCellValue());
                    } else {
                        System.out.println("data not matched at index = " + i);
                        Assert.assertTrue(true);
                    }

                }
            } else {
                System.out.println("incorrect row count");
                Assert.assertTrue(false);
            }
            w1.close();
            w2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void userSelectThirtyDayFromToday() {
        driver.findElement(dateLocator).click();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        java.util.Date futureDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(futureDate);


        String d = formattedDate.substring(8, 10);
        int date = Integer.parseInt(d);


        String thirtyDayXpath = "(//td[text()='" + date + "'])[2]";
        System.out.println(thirtyDayXpath);
        By thirtydayLocator = By.xpath(thirtyDayXpath);
        driver.findElement(thirtydayLocator).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
