package FrontEndTest.paraBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import FrontEndTest.base.BasePage;

import java.time.Duration;

public class Registry extends BasePage {

    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(1000));

    protected static final String registry = "//*[@id=\"loginPanel\"]/p[2]/a";
    protected static final String firstName = "customer.firstName";
    protected static final String lastName = "customer.lastName";
    protected static final String address = "customer.address.street";
    protected static final String city = "customer.address.city";
    protected static final String state = "customer.address.state";
    protected static final String zipCode = "customer.address.zipCode";
    protected static final String inputPhone = "customer.phoneNumber";
    protected static final String ssn = "customer.ssn";
    protected static final String inputUserName= "customer.username";
    protected static final String inputPassword = "customer.password";
    protected static final String confirmPassword = "repeatedPassword";

    protected static final String btnRegister = "//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input";

    //Confirm Registry
    protected static final String success = "rightPanel";

    //Metodos
    public void goToRegister() {
        WebElement btnRegistry = getWebElement(By.xpath(registry));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(registry)));
        btnRegistry.click();
    }

    public void doRegister (String name, String last_Name, String user_address, String user_city,String user_state, String user_zipcode, String phone, String user_ssn, String user_userName, String password, String confirmPass){

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(firstName)));
        WebElement inputFirstName = getWebElement(By.id(firstName));
        inputFirstName.clear();
        inputFirstName.sendKeys(name);

        WebElement inputLastName = getWebElement(By.id(lastName));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(lastName)));
        inputLastName.clear();
        inputLastName.sendKeys(last_Name);

        WebElement inputAddress = getWebElement(By.id(address));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(address)));
        inputAddress.clear();
        inputAddress.sendKeys(user_address);

        WebElement inputCity = getWebElement(By.id(city));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(city)));
        inputCity.clear();
        inputCity.sendKeys(user_city);

        WebElement inputState = getWebElement(By.id(state));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(state)));
        inputState.clear();
        inputState.sendKeys(user_state);

        WebElement inputZipCode = getWebElement(By.id(zipCode));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(zipCode)));
        inputZipCode.clear();
        inputZipCode.sendKeys(user_zipcode);

        WebElement input_phone = getWebElement(By.id(inputPhone));
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(inputPhone)));
        input_phone.clear();
        input_phone.sendKeys(phone);

        WebElement input_ssn = getWebElement(By.id(ssn));
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(ssn)));
        input_ssn.clear();
        input_ssn.sendKeys(user_ssn);

        WebElement input_username = getWebElement(By.id(inputUserName));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(inputUserName)));
        input_username.clear();
        input_username.sendKeys(user_userName);

        WebElement pass = getWebElement(By.id(inputPassword));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(inputPassword)));
        pass.clear();
        pass.sendKeys(password);

        WebElement passConfirm = getWebElement(By.id(confirmPassword));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(confirmPassword)));
        passConfirm.clear();
        passConfirm.sendKeys(confirmPass);
    }

    public void sendCredentials(){
        WebElement btn_register = getWebElement(By.xpath(btnRegister));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btn_register));
        btn_register.click();
    }

    public String confirmRegistry() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(success)));
        WebElement message = getWebElement(By.id(success));
        return message.getText();
    }
}
