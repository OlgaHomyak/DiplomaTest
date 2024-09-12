package intershop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPanel {

    private By catalogLocator = By.xpath("//*[contains(@class,'menu-item')]/a[text()='Каталог']");
    private By accountLocator = By.xpath("//*[contains(@class,'menu-item')]/a[text()='Мой аккаунт']");
    private By basketLocator = By.xpath("//*[contains(@class,'menu-item-29')]/a[text()='Корзина']");
    private By checkoutLinkInMenuLocator = By.xpath("//*[contains(@class,'menu-item')]/a[text()='Оформление заказа']");

    private WebDriver driver;

    public HeaderPanel (WebDriver driver){
        this.driver = driver;
    }
    public void clickOnCatalogLocator(){
        driver.findElement(catalogLocator).click();
    }
    public void clickOnAccount(){
        driver.findElement(accountLocator).click();
    }
    public void clickOnBasket(){
        driver.findElement(basketLocator).click();
    }
    public void clickOnCheckout(){
        driver.findElement(checkoutLinkInMenuLocator).click();
    }
}
