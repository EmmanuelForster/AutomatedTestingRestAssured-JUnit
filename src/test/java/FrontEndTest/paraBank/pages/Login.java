package FrontEndTest.paraBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import FrontEndTest.base.BasePage;

import java.time.Duration;

public class Login extends BasePage {

    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(1000));

    protected static final String LogOutBtn = "//*[@id=\"leftPanel\"]/ul/li[8]/a";

    protected static final String LogginBtn = "//*[@id=\"loginPanel\"]/form/div[3]/input";

    protected static final String UsernameInput = "//*[@id=\"loginPanel\"]/form/div[1]/input";

    protected static final String PassInput = "//*[@id=\"loginPanel\"]/form/div[2]/input";

    protected static final String success = "//*[@id=\"leftPanel\"]/p";


    public void login(String user, String password) {

        try{

            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LogOutBtn)));
            WebElement logoutBtn = getWebElement(By.xpath(LogOutBtn));
            logoutBtn.click();

        }catch (Exception e){
            System.out.println("No se pudo cerrar la sesion");
        }

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(UsernameInput)));
        WebElement usernameInput = getWebElement(By.xpath(UsernameInput));
        usernameInput.sendKeys(user);

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PassInput)));
        WebElement passwordInput = getWebElement(By.xpath(PassInput));
        passwordInput.sendKeys(password);

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LogginBtn)));
        WebElement logginBtn = getWebElement(By.xpath(LogginBtn));
        logginBtn.click();
    }

    public String confirmLogin(){
        WebElement message = getWebElement(By.xpath(success));
        return message.getText();
    }

}
