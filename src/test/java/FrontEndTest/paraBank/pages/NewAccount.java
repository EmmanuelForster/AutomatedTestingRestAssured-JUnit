package FrontEndTest.paraBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import FrontEndTest.base.BasePage;

import java.time.Duration;

public class NewAccount extends BasePage {

    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(1500));

    //Locators
    private static final String btnCreateAccount = "//*[@id=\"leftPanel\"]/ul/li[1]/a";
    private static final String accountOptions = "//*[@id=\"type\"]";
    private static final String accountType = "//*[@id=\"type\"]/option[2]";
    private static final String btnCreate = "//*[@id=\"rightPanel\"]/div/div/form/div/input";
    public static final String success = "//*[@id=\"rightPanel\"]/div/div/p[1]";


    //Metodos
    public void goToCreateAccount (){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(btnCreateAccount)));
        WebElement createAccount = getWebElement(By.xpath(btnCreateAccount));

        createAccount.click();
    }

    public void createAccount(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountOptions)));
        WebElement selectAccountOptions = getWebElement(By.xpath(accountOptions));
        selectAccountOptions.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountType)));
        WebElement optionAccountType = getWebElement(By.xpath(accountType));
        optionAccountType.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(btnCreate)));
        WebElement btnCreateAccount = getWebElement(By.xpath(btnCreate));
        btnCreateAccount.click();
    }

    public String confirmAccount() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success)));
        WebElement message = getWebElement(By.xpath(success));
        return message.getText();
    }
}
