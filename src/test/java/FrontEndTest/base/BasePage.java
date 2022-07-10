package FrontEndTest.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    public static WebDriver webDriver;
    protected static final String URL = "https://parabank.parasoft.com/parabank/index.htm";

    public BasePage() {
        if (webDriver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
        }
    }

    public void openApp (){
        webDriver.get(URL);
    }

    public WebDriver getWebDriver(){
        return webDriver;
    }

    public WebElement getWebElement(By locator){
        WebElement webElement = null;
        try {
            webElement = webDriver.findElement(locator);
        }
        catch (Exception exception){
            System.out.println("We can't find the element: " + locator);
            System.out.println("Exception: " + exception);
        }
        return webElement;
    };
}
