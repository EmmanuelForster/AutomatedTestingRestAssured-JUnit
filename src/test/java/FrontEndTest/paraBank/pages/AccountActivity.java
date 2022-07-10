package FrontEndTest.paraBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import FrontEndTest.base.BasePage;

import java.time.Duration;

public class AccountActivity extends BasePage {

    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(1000));

    protected static final String accountNumber = "//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a";
    protected static final String titleAccount = "//*[@id=\"rightPanel\"]/div/div[1]/h1";
    protected static final String to = "month";
    protected static final String toAll = "//*[@id=\"month\"]/option[1]";
    protected static final String type = "transactionType";
    protected static final String typeAll = "//*[@id=\"transactionType\"]/option[1]";
    protected static final String goBtn = "//*[@id=\"rightPanel\"]/div/div[2]/form/table/tbody/tr[3]/td[2]/input";

    public String goToAccountDetail() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountNumber)));
        WebElement accountNumberOption = getWebElement(By.xpath(accountNumber));
        accountNumberOption.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(titleAccount)));
        WebElement title = getWebElement(By.xpath(titleAccount));
        return title.getText();
    }

    public void checkActivity(){

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(to)));
        WebElement selectTo = getWebElement(By.id(to));
        selectTo.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(toAll)));
        WebElement toAllOption = getWebElement(By.xpath(toAll));
        toAllOption.click();

        WebElement selectType = getWebElement(By.id(type));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(type)));
        selectType.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(typeAll)));
        WebElement typeAllOption = getWebElement(By.xpath(typeAll));
        typeAllOption.click();

        WebElement btn_go = getWebElement(By.xpath(goBtn));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btn_go));
        btn_go.click();
    }
}
