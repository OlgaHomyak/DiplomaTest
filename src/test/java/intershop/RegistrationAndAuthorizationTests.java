package intershop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.Random;


public class RegistrationAndAuthorizationTests extends TestBase  {
    private String urlMain = "https://intershop6.skillbox.ru/";

    private By loginLocator = By.xpath("//a[contains(text(), 'Войти')]");
    private By loginInputLocator = By.xpath("//input[@name='username']");
    private By passwordInputLocator = By.xpath("//input[@name='password']");
    private By buttonLoginLocator = By.xpath("//button[text()='Войти']");
    private By buttonRegistrationLocator = By.xpath("//li[@class='page_item page-item-141']/a[text()='Регистрация']");
    private By mailInputLocator = By.xpath("//input[@id='reg_email']");
    private By goRegistrationLocator = By.xpath("//button[text()='Зарегистрироваться']");
    private By successAuthorizationLocator = By.xpath(" //div[@class='woocommerce-MyAccount-content']/p[strong[text()='Ola']]");
    private By errorAuthorizationLocator = By.xpath("//li[contains(text(),'Веденный пароль для пользователя')]");
    private By successRegistrationLocator = By.xpath("//div[@class='content-page']//div[text()='Регистрация завершена']");
    private By errorRegistrationLocator = By.xpath("//li/strong[text()='Error:']");

    @Test
    public void authorization() {
        var login = "Ola";
        var password = "1234562";
        driver.navigate().to(urlMain);
        driver.findElement(loginLocator).click();
        driver.findElement(loginInputLocator).sendKeys(login);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(buttonLoginLocator).click();
        String pageTitle = driver.findElement(successAuthorizationLocator).getText();
        Assert.assertEquals("Информация об успешной авторизации не появилась", "Привет Ola (Выйти)", pageTitle);
    }

    @Test
    public void incorrectAuthorization() { //
        var login = "Ola";
        var falsePassword = "123456";

        driver.navigate().to(urlMain);
        driver.findElement(loginLocator).click();
        driver.findElement(loginInputLocator).sendKeys(login);
        driver.findElement(passwordInputLocator).sendKeys(falsePassword);
        driver.findElement(buttonLoginLocator).click();
        String pageTitle = driver.findElement(errorAuthorizationLocator).getText();
        Assert.assertEquals("Информация об ошибке при авторизации не появилась",
                            "Веденный пароль для пользователя Ola неверный. Забыли пароль?", pageTitle);
    }

    //тест registration() работал лишь единожды, повторная регистрация того же логина невозможна,
    // пытаюсь это обойти методом для генерации случайного логина (а заодно и почты) из 7 латинских букв:

    public String generateRandomLogin(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder login = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            login.append(alphabet.charAt(index));
        }
        return login.toString();
    }

    @Test
    public void registration() {
        var login = generateRandomLogin(7);
        var mail = generateRandomLogin(7) + "@mail.ro";
        var password = "123456";

        driver.navigate().to(urlMain);
        driver.findElement(loginLocator).click();
        driver.findElement(buttonRegistrationLocator).click();
        driver.findElement(loginInputLocator).sendKeys(login);
        driver.findElement(mailInputLocator).sendKeys(mail);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(goRegistrationLocator).click();

        String pageTitle = driver.findElement(successRegistrationLocator).getText();
        Assert.assertEquals("Информация об успешной регистрации не появилась", "Регистрация завершена", pageTitle);
    }
    @Test
    public void incorrectRegistration() {
        var login = "Ola";
        var mail = "Ola@mail.ri";
        var password = "1234562";

        driver.navigate().to(urlMain);
        driver.findElement(loginLocator).click();
        driver.findElement(buttonRegistrationLocator).click();
        driver.findElement(loginInputLocator).sendKeys(login);
        driver.findElement(mailInputLocator).sendKeys(mail);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(goRegistrationLocator).click();

        String pageTitle = driver.findElement(errorRegistrationLocator).getText();
        Assert.assertEquals("Информация об ошибке при регистрации не появилась",
                            "Error:", pageTitle);
    }
}
