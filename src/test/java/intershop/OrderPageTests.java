package intershop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class OrderPageTests extends TestBase  {
    private String urlMain = "https://intershop6.skillbox.ru/";

    private By firstNameLocator = By.xpath("//input[@id='billing_first_name']");
    private By lastNameLocator = By.xpath("//input[@id='billing_last_name']");
    private By addressLocator = By.xpath("//input[@id='billing_address_1']");
    private By cityLocator = By.xpath("//input[@id='billing_city']");
    private By stateLocator = By.xpath("//input[@id='billing_state']");
    private By postcodeLocator = By.xpath("//input[@id='billing_postcode']");
    private By phoneLocator = By.xpath("//input[@id='billing_phone']");
    private By emailLocator = By.xpath("//input[@id='billing_email']");
    private By buttonOrderLocator = By.xpath("//button[text()='Оформить заказ']");
    private By successOrderLocator = By.xpath("//h2[contains(text(), 'Заказ получен')]");
    private By cashOnDeliveryLocator = By.xpath("//input[@id='payment_method_cod']");
    private By errorNameLocator = By.xpath("//li[@data-id='billing_first_name']");
    private By errorNotInfoLocator = By.xpath("//ul[@class='woocommerce-error' and @role='alert']");
    private By errorPhoneLocator = By.xpath("//li[@data-id='billing_phone']");
    private By errorEmailLocator = By.xpath("//ul[@class='woocommerce-error' and @role='alert']");

    private String messageSuccessOrder = "Информация об успешном получении заказа не появилась";
    private String messageErrorOrder = "Информация об ошибке не появилось";

    public void authorization() {
        //предварительно перед каждым тестом заходим на страницу магазина,
        // в личный кабинет, делаем покупку и переходим к оформлению
        var loginLocator = By.xpath("//a[contains(text(), 'Войти')]");
        var loginInputLocator = By.xpath("//input[@name='username']");
        var passwordInputLocator = By.xpath("//input[@name='password']");
        var buttonLoginLocator = By.xpath("//button[text()='Войти']");
        var searchInputLocator = By.xpath("//input[@name='s' and @class='search-field']");
        var searchLocator = By.xpath("//button[@type='submit' and @class='searchsubmit']");
        var buttonBasket = By.xpath("//button[@class='single_add_to_cart_button button alt' and text()='В корзину']");
        var interestingBook = "Кошкодевочка с сюрпризом";
        var login = "Ola";
        var password = "1234562";
        var orderLocator = By.xpath("//li[@id='menu-item-31']/a");
        //заходим в личный кабинет
        driver.navigate().to(urlMain);
        driver.findElement(loginLocator).click();
        driver.findElement(loginInputLocator).sendKeys(login);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(buttonLoginLocator).click();
        //делаем покупку, чтобы перейти к оформлению
        driver.findElement(searchInputLocator).sendKeys(interestingBook);
        driver.findElement(searchLocator).click();
        driver.findElement(buttonBasket).click();
        //переходим к оформлению
        driver.findElement(orderLocator).click();
    }
    public void cleanUp() {
        // избавляемся от автозаполнения полей
        var firstNameLocator = By.xpath("//input[@id='billing_first_name']");
        var lastNameLocator = By.xpath("//input[@id='billing_last_name']");
        var addressLocator = By.xpath("//input[@id='billing_address_1']");
        var cityLocator = By.xpath("//input[@id='billing_city']");
        var stateLocator = By.xpath("//input[@id='billing_state']");
        var postcodeLocator = By.xpath("//input[@id='billing_postcode']");
        var phoneLocator = By.xpath("//input[@id='billing_phone']");
        var emailLocator = By.xpath("//input[@id='billing_email']");

        driver.findElement(firstNameLocator).clear();
        driver.findElement(lastNameLocator).clear();
        driver.findElement(addressLocator).clear();
        driver.findElement(cityLocator).clear();
        driver.findElement(stateLocator).clear();
        driver.findElement(postcodeLocator).clear();
        driver.findElement(phoneLocator).clear();
        driver.findElement(emailLocator).clear();
        driver.findElement(emailLocator).clear();
    }

    @Test
    public void orderRegistrationPaymentByCard() {
        authorization();
        cleanUp();

        var firstName = "Олег";
        var lastName = "Михалев";
        var address = "Нефтяников, 1";
        var city = "Яйва";
        var state = "Пермский край";
        var postcode = "122333";
        var phone = "89992220909";
        var email = "OlegAv@moil.ro";

        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(addressLocator).sendKeys(address);
        driver.findElement(cityLocator).sendKeys(city);
        driver.findElement(stateLocator).sendKeys(state);
        driver.findElement(postcodeLocator).sendKeys(postcode);
        driver.findElement(phoneLocator).sendKeys(phone);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(successOrderLocator).getText();
        Assert.assertEquals(messageSuccessOrder, "ЗАКАЗ ПОЛУЧЕН", pageTitle);
    }

    @Test
    public void orderRegistrationCashOnDelivery() {
        authorization();
        cleanUp();

        var firstName = "Олег";
        var lastName = "Михалев";
        var address = "Нефтяников, 1";
        var city = "Яйва";
        var state = "Пермский край";
        var postcode = "122333";
        var phone = "89992220909";
        var email = "OlegAv@moil.ro";

        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(addressLocator).sendKeys(address);
        driver.findElement(cityLocator).sendKeys(city);
        driver.findElement(stateLocator).sendKeys(state);
        driver.findElement(postcodeLocator).sendKeys(postcode);
        driver.findElement(phoneLocator).sendKeys(phone);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(cashOnDeliveryLocator).click();
        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(successOrderLocator).getText();
        Assert.assertEquals(messageSuccessOrder, "ЗАКАЗ ПОЛУЧЕН", pageTitle);
    }
    @Test
    public void incorrectNotNameOrderRegistration() {
        authorization();
        cleanUp();

        var lastName = "Михалев";
        var address = "Нефтяников, 1";
        var city = "Яйва";
        var state = "Пермский край";
        var postcode = "122333";
        var phone = "89992220909";
        var email = "OlegAv@moil.ro";

        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(addressLocator).sendKeys(address);
        driver.findElement(cityLocator).sendKeys(city);
        driver.findElement(stateLocator).sendKeys(state);
        driver.findElement(postcodeLocator).sendKeys(postcode);
        driver.findElement(phoneLocator).sendKeys(phone);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(errorNameLocator).getText();
        Assert.assertEquals(messageErrorOrder, "Имя для выставления счета обязательное поле.", pageTitle);
    }
    @Test
    public void incorrectNotInfoOrderRegistration() {
        authorization();
        cleanUp();

        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(errorNotInfoLocator).getText();
        Assert.assertEquals(messageErrorOrder,
                "Имя для выставления счета обязательное поле.\n" +
                        "Фамилия для выставления счета обязательное поле.\n" +
                        "Адрес для выставления счета обязательное поле.\n" +
                        "Город / Населенный пункт для выставления счета обязательное поле.\n" +
                        "Область для выставления счета обязательное поле.\n" +
                        "Почтовый индекс для выставления счета обязательное поле.\n" +
                        "неверный номер телефона.\n" +
                        "Телефон для выставления счета обязательное поле.\n" +
                        "Адрес почты для выставления счета обязательное поле.", pageTitle);
    }
    @Test
    public void incorrectPhone1OrderRegistration() {
        authorization();
        cleanUp();

        var firstName = "Олег";
        var lastName = "Михалев";
        var address = "Нефтяников, 1";
        var city = "Яйва";
        var state = "Пермский край";
        var postcode = "122333";
        var phone = "s79125902406"; // в номере присутствует буква
        var email = "OlegAv@moil.ro";

        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(addressLocator).sendKeys(address);
        driver.findElement(cityLocator).sendKeys(city);
        driver.findElement(stateLocator).sendKeys(state);
        driver.findElement(postcodeLocator).sendKeys(postcode);
        driver.findElement(phoneLocator).sendKeys(phone);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(errorPhoneLocator).getText();
        Assert.assertEquals(messageErrorOrder, "Телефон для выставления счета не валидный номер телефона.", pageTitle);
    }
    @Test
    public void incorrectPhone2OrderRegistration() {
        authorization();
        cleanUp();

        var firstName = "Олег";
        var lastName = "Михалев";
        var address = "Нефтяников, 1";
        var city = "Яйва";
        var state = "Пермский край";
        var postcode = "122333";
        var phone = "791256"; // мало цифр для корректно указанного номера
        var email = "OlegAv@moil.ro";

        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(addressLocator).sendKeys(address);
        driver.findElement(cityLocator).sendKeys(city);
        driver.findElement(stateLocator).sendKeys(state);
        driver.findElement(postcodeLocator).sendKeys(postcode);
        driver.findElement(phoneLocator).sendKeys(phone);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(errorPhoneLocator).getText();
        Assert.assertEquals(messageErrorOrder, "неверный номер телефона.", pageTitle);
    }
    @Test
    public void incorrectEmail1OrderRegistration() {
        authorization();
        cleanUp();

        var firstName = "Олег";
        var lastName = "Михалев";
        var address = "Нефтяников, 1";
        var city = "Яйва";
        var state = "Пермский край";
        var postcode = "122333";
        var phone = "89992220909";
        var email = "OlegAvmoil.ro"; // пропустили "@"

        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(addressLocator).sendKeys(address);
        driver.findElement(cityLocator).sendKeys(city);
        driver.findElement(stateLocator).sendKeys(state);
        driver.findElement(postcodeLocator).sendKeys(postcode);
        driver.findElement(phoneLocator).sendKeys(phone);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(errorEmailLocator).getText();
        Assert.assertEquals(messageErrorOrder, "Invalid billing email address", pageTitle);
    }
    @Test
    public void incorrectEmail2OrderRegistration() {
        authorization();
        cleanUp();

        var firstName = "Олег";
        var lastName = "Михалев";
        var address = "Нефтяников, 1";
        var city = "Яйва";
        var state = "Пермский край";
        var postcode = "122333";
        var phone = "89992220909";
        var email = "Oleg@Avmoilro"; // пропустили "точку"

        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(addressLocator).sendKeys(address);
        driver.findElement(cityLocator).sendKeys(city);
        driver.findElement(stateLocator).sendKeys(state);
        driver.findElement(postcodeLocator).sendKeys(postcode);
        driver.findElement(phoneLocator).sendKeys(phone);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(buttonOrderLocator).click();

        String pageTitle = driver.findElement(errorEmailLocator).getText();
        Assert.assertEquals(messageErrorOrder, "Invalid billing email address", pageTitle);
    }
}
