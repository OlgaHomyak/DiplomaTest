package intershop;

import org.openqa.selenium.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketPageTests extends TestBase  {
    private String urlMain = "https://intershop6.skillbox.ru/";

    private By buttonRemoveProductBasketLocator = By.xpath("//a[@class='remove' and text()='×']");
    private By successRemoveAllLocator = By.xpath("//div[contains(text(), '“Кошкодевочка с сюрпризом” удален')]");
    private By searchInputLocator = By.xpath("//input[@name='s' and @class='search-field']");
    private By searchLocator = By.xpath("//button[@type='submit' and @class='searchsubmit']");
    private By buttonAddProductBasket = By.xpath("//button[text()='В корзину']");
    private By basketLocator = By.xpath("//*[contains(@class,'menu-item-29')]/a[text()='Корзина']");
    private By successRemove1Locator = By.xpath("//div[contains(text(), '“Кошкодевочка с сюрпризом” удален')]");
    private By buttonReturnRemoveProductBasketLocator = By.xpath("//a[contains(@class, 'restore-item') and text()='Вернуть?']");
    private By atlantReturnedInBasketLocator = By.xpath(" //tr[@class='woocommerce-cart-form__cart-item cart_item']");
    private By couponInputLocator = By.xpath("//input[@id='coupon_code']");
    private By buttonCouponLocator = By.xpath("//button[@name='apply_coupon']");
    private By successDiscountLocator = By.xpath("//div[contains(text(),'Coupon code applied successfully.')]");
    private By buttonRemoveDiscountLocator = By.xpath("//a[@class='woocommerce-remove-coupon' and @data-coupon='sert500']");
    private By successRemoveDiscountLocator = By.xpath("//div[contains(text(),'Coupon has been removed.')]");
    private By errorDiscountLocator = By.xpath("//ul[@class='woocommerce-error']//li[contains(text(), 'Coupon code already applied!')]");
    private By error2DiscountLocator = By.xpath("//ul[@class='woocommerce-error']//li[contains(text(),'Неверный купон.')]");
    private By buttonCheckoutLocator = By.xpath("//a[contains(@class, 'checkout-button') and contains(text(),'ПЕРЕЙТИ К ОПЛАТЕ')]");
    private By titleCheckoutPageLocator = By.xpath("//*[contains(@class,'menu-item')]/a[text()='Оформление заказа']");

    private void addProductBasket(){
        var interestingBook = "Кошкодевочка с сюрпризом";
        var searchInputLocator = By.xpath("//input[@name='s' and @class='search-field']");
        var searchLocator = By.xpath("//button[@type='submit' and @class='searchsubmit']");
        var buttonAddProductBasket = By.xpath("//button[text()='В корзину']");
        var basketLocator = By.xpath("//*[contains(@class,'menu-item-29')]/a[text()='Корзина']");
        driver.navigate().to(urlMain);
        driver.findElement(searchInputLocator).sendKeys(interestingBook);
        driver.findElement(searchLocator).click();
        driver.findElement(buttonAddProductBasket).click();
        driver.findElement(basketLocator).click();
    }

    @Test
    public void removeProductToBasket1() { // удаляем товар из корзины, корзина пуста
        addProductBasket();
        driver.findElement(buttonRemoveProductBasketLocator).click();
        String pageTitle = driver.findElement(successRemoveAllLocator).getText();
        Assert.assertEquals("Не появилась информация об успешном опустошении корзины", "“Кошкодевочка с сюрпризом” удален. Вернуть?", pageTitle);
    }

    @Test
    public void removeProductToBasket2() { // добавляем второй товар, удаляем первый товар из корзины
        addProductBasket();
        var interestingBook = "запах заката";
        driver.findElement(searchInputLocator).sendKeys(interestingBook);
        driver.findElement(searchLocator).click();
        driver.findElement(buttonAddProductBasket).click();
        driver.findElement(basketLocator).click();
        driver.findElement(buttonRemoveProductBasketLocator).click();

        String pageTitle = driver.findElement(successRemove1Locator).getText();
        Assert.assertEquals("Не появилась информация об успешном удалении позиции из корзины", "“Кошкодевочка с сюрпризом” удален. Вернуть?", pageTitle);
    }

    @Test
    public void returnProductToBasket1() { // удаляем единственный товар из корзины, потом возвращаем его
        addProductBasket();
        driver.findElement(buttonRemoveProductBasketLocator).click();
        driver.findElement(buttonReturnRemoveProductBasketLocator).click();
        WebElement successMessage = wait5.until(ExpectedConditions.visibilityOfElementLocated(atlantReturnedInBasketLocator));
        Assert.assertTrue("Товар в корзину не вернулся", successMessage.isDisplayed());
    }

    @Test
    public void discountCoupon500() { //применяем скидочный купон
        addProductBasket();
        var coupon500 = "sert500";
        driver.findElement(couponInputLocator).sendKeys(coupon500);
        driver.findElement(buttonCouponLocator).click();
        String pageTitle = driver.findElement(successDiscountLocator).getText();
        Assert.assertEquals("Информация об успешном использовании купона не появилась", "Coupon code applied successfully.", pageTitle);
    }

    @Test
    public void removeDiscountCoupon500() { //применяем скидочный купон, затем убираем его
        addProductBasket();
        var coupon500 = "sert500";
        driver.findElement(couponInputLocator).sendKeys(coupon500);
        driver.findElement(buttonCouponLocator).click();
        driver.findElement(buttonRemoveDiscountLocator).click();

        String pageTitle = driver.findElement(successRemoveDiscountLocator).getText();
        Assert.assertEquals("Информация об успешном удалении купона не появилась", "Coupon has been removed.", pageTitle);
    }

    @Test
    public void discountCoupon500x2() { //применяем скидочный действующий купон дважды
        addProductBasket();
        var coupon500 = "sert500";
        driver.findElement(couponInputLocator).sendKeys(coupon500);
        driver.findElement(buttonCouponLocator).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(couponInputLocator).sendKeys(coupon500);
        driver.findElement(buttonCouponLocator).click();
        String pageTitle = driver.findElement(errorDiscountLocator).getText();
        Assert.assertEquals("Информация о неуспешном повторном использовании купона не появилась", "Coupon code already applied!", pageTitle);
    }

    @Test
    public void discountCoupon1000() { //применяем неверный скидочный купон
        addProductBasket();
        var coupon1000 = "sert1000";
        driver.findElement(couponInputLocator).sendKeys(coupon1000);
        driver.findElement(buttonCouponLocator).click();
        String pageTitle = driver.findElement(error2DiscountLocator).getText();
        Assert.assertEquals("Информация об ошибке не появилась", "Неверный купон.", pageTitle);
    }

    @Test
    public void basketCheckout() { // переходим к оформлению
        addProductBasket();
        driver.findElement(buttonCheckoutLocator).click();
        String pageTitle = driver.findElement(titleCheckoutPageLocator).getText();
        Assert.assertEquals("Информация об ошибке не появилась", "ОФОРМЛЕНИЕ ЗАКАЗА", pageTitle);
    }
}



