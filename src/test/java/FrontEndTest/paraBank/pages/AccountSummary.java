package FrontEndTest.paraBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import FrontEndTest.base.BasePage;

import java.time.Duration;

public class AccountSummary extends BasePage {

    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(1000));

    //Locators
    private static final String btnSummary = "//*[@id=\"leftPanel\"]/ul/li[2]/a";
    public static final String success = "//*[@id=\"accountTable\"]/tfoot/tr/td";


    //Metodos
    public void goToSummary(){
        WebElement accountSummary = getWebElement(By.xpath(btnSummary));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(btnSummary)));
        accountSummary.click();
    }

    public String confirm() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success)));
        WebElement message = getWebElement(By.xpath(success));
        return message.getText();
    }
}
