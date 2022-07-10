package FrontEndTest.paraBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import FrontEndTest.base.BasePage;

import java.time.Duration;

public class TransferFounds extends BasePage {

    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(1000));

    protected static final String btnGoToTransfer = "//*[@id=\"leftPanel\"]/ul/li[3]/a";
    protected static final String titleTransfer = "title";
    protected static final String amount = "amount";
    protected static final String fromAccount = "fromAccountId";
    protected static final String originAccount = "//*[@id=\"toAccountId\"]/option[1]";
    protected static final String toAccount = "toAccountId";
    protected static final String accountToTransfer = "//*[@id=\"toAccountId\"]/option[2]";

    protected static final String btnTransfer = "//*[@id=\"rightPanel\"]/div/div/form/div[2]/input";

    protected static final String success = "//*[@id=\"rightPanel\"]/div/div/h1";

    public String goToTransfer() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(btnGoToTransfer)));
        WebElement btnTransfer = getWebElement(By.xpath(btnGoToTransfer));
        btnTransfer.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(titleTransfer)));
        WebElement title = getWebElement(By.className(titleTransfer));
        return title.getText();
    }

    public void doTransfer (String amountToTransfer){

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(amount)));
        WebElement inputAmount = getWebElement(By.id(amount));
        inputAmount.clear();
        inputAmount.sendKeys(amountToTransfer);

        WebElement selectFromAccount = getWebElement(By.id(fromAccount));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(fromAccount)));
        selectFromAccount.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(originAccount)));
        WebElement originAccountOption = getWebElement(By.xpath(originAccount));
        originAccountOption.click();

        WebElement selectToAccount = getWebElement(By.id(toAccount));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(toAccount)));
        selectToAccount.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountToTransfer)));
        WebElement ToAccountOption = getWebElement(By.xpath(accountToTransfer));
        ToAccountOption.click();
    }

    public void sendTransfer(){
        WebElement btn_transfer = getWebElement(By.xpath(btnTransfer));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btn_transfer));
        btn_transfer.click();
    }

    public String confirmTransfer() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success)));
        WebElement message = getWebElement(By.xpath(success));
        return message.getText();
    }
}
