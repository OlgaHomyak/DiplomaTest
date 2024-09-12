package intershop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import java.util.List;

public class MainPageTests extends TestBase {

    @Test
    public void mainPageClickBooks(){
        driver.navigate().to(url);
        driver.findElement(bookLocator).click();
        Assert.assertEquals(messageInCaseOfFailedTest, "КНИГИ", getSubTitlePage());
    }

    @Test
    public void mainPageClickTablets(){
        driver.navigate().to(url);
        driver.findElement(tabletsLocator).click();
        Assert.assertEquals(messageInCaseOfFailedTest, "ПЛАНШЕТЫ", getSubTitlePage());
    }

    @Test
    public void mainPageClickPhotoAndVideo(){
        driver.navigate().to(url);
        driver.findElement(phAndVidLocator).click();
        Assert.assertEquals(messageInCaseOfFailedTest, "ФОТО/ВИДЕО", getSubTitlePage());
    }

    @Test
    public void mainPageClickCatalog(){
        driver.navigate().to(url);
        driver.findElement(catalogLocator).click();
        Assert.assertEquals(messageInCaseOfFailedTest, "КАТАЛОГ", getSubTitlePage());
    }

    @Test
    public void mainPageClickMyAccount(){
        driver.navigate().to(url);
        driver.findElement(accountLocator).click();
        Assert.assertEquals(messageInCaseOfFailedTest, "Мой Аккаунт", getTitle());
    }

    @Test
    public void mainPageClickBasket(){
        driver.navigate().to(url);
        driver.findElement(basketLocator).click();
        Assert.assertEquals(messageInCaseOfFailedTest, "Корзина", getTitle());
    }

    @Test
    public void mainPage_ClickOnCheckoutLinkInMenuWithItemInCart_CartPageWasOpened(){
        driver.navigate().to(url);
        addProductWithDiscount();
        driver.findElement(checkoutLinkInMenuLocator).click();
        Assert.assertEquals(messageInCaseOfFailedTest, "Корзина", getTitle());
    }

    @Test
    public void mainPageTvSearch(){
        var tvSearch = "телевизор";
        driver.navigate().to(url);
        driver.findElement(searchInputLocator).sendKeys(tvSearch);
        driver.findElement(searchLocator).click();

        List<WebElement> searchResults = driver.findElements(searchResultLocator); // Получаем список всех найденных элементов
        Assert.assertTrue("Результаты поиска не содержат телевизоров", searchResults.size() > 0);

        for (WebElement result : searchResults) { // Проверяем, что каждый результат поиска содержит текст "котик"
            Assert.assertTrue("Заголовок не содержит слово 'телевизор'", result.getText().toLowerCase().contains(tvSearch));
        }
    }

    @Test
    public void mainPageViewedProduct() {
        driver.navigate().to(url);
        driver.findElement(watchProductOnMainPageLocator).click();
        String title1ProductOnOpenPage = driver.findElement(titleProductLocator).getText();
        driver.findElement(returnMainPageLocator).click();
        driver.findElement(firstObjectViewed).click();
        String title2ProductOnOpenPage = driver.findElement(titleProductLocator).getText();
        Assert.assertEquals("Не совпадают названия товаров", title1ProductOnOpenPage, title2ProductOnOpenPage);
    }

    @Test
    public void mainPageOpenMainPage_AllProductsOnSale() {
        driver.navigate().to(url); //productOnSaleLocator
        assertSaleLabelsOnProductsWithDiscount();
    }
    private void assertSaleLabelsOnProductsWithDiscount(){ //метод к тесту mainPageOpenMainPage_AllProductsOnSale
        var allProductsLabelsOnSale = driver.findElements(allProductsLabelsLocator);
        for (var productsLabel : allProductsLabelsOnSale){
            Assert.assertTrue("Не у всех товаров есть лейбл \"Распродажа\"", productsLabel.getAttribute("class").contains("onsale"));
        }
    }
}