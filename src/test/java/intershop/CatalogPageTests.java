package intershop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CatalogPageTests extends TestBase  {
    private String url = "https://intershop6.skillbox.ru/product-category/catalog/";

    private By searchInputLocator = By.xpath("//input[@name='s' and @class='search-field']");
    private By searchLocator = By.xpath("//button[@type='submit' and @class='searchsubmit']");
    private By searchResultLocator = By.xpath("//h3[contains(text(), 'iPad')]");
    private By resultNotFind = By.xpath("//p[text()='По вашему запросу товары не найдены.']");
    private By buttonBasket = By.xpath("//button[@class='single_add_to_cart_button button alt' and text()='В корзину']");
    private By successBasket = By.xpath("//div[@class='woocommerce-notices-wrapper']//div[@class='woocommerce-message' and @role='alert']");
    private By resultApple = By.xpath("//h1[@class='product_title entry-title' and text()='Apple Watch 6']");

    @Test
    public void mainPageBookSearch() { //ищем в магазине существующую позицию (с несколькими результатами)
        var iPadSearch = "iPad";
        driver.navigate().to(url);
        driver.findElement(searchInputLocator).sendKeys(iPadSearch);
        driver.findElement(searchLocator).click();

        List<WebElement> searchResults = driver.findElements(searchResultLocator); // Получаем список всех найденных элементов, их больше, чем 0 должно быть
        Assert.assertTrue("Результаты поиска не содержат позиций со словами 'Война и мир' в названии или описании", searchResults.size() > 0);

        for (WebElement result : searchResults) { // Проверяем, что каждый результат поиска содержит текст "Война и мир"
            Assert.assertTrue("Не все результаты поиска являются результатом поиска  'Война и мир'", result.getText().contains(iPadSearch));
        }
    }

    @Test
    public void mainPageHamsterSearch() { //ищем в магазине несуществующую позицию
        var hamsterSearch = "хомяк";
        driver.navigate().to(url);
        driver.findElement(searchInputLocator).sendKeys(hamsterSearch);
        driver.findElement(searchLocator).click();
        String pageTitle = driver.findElement(resultNotFind).getText();
        Assert.assertEquals("Никаких хомяков в магазине нет", "По вашему запросу товары не найдены.", pageTitle);
    }

    @Test
    public void mainPageATLANTSearch() { //ищем в магазине часы АТЛАНТ (1)
        var appleWatch6 = "Apple Watch 6";
        driver.navigate().to(url);
        driver.findElement(searchInputLocator).sendKeys(appleWatch6);
        driver.findElement(searchLocator).click();
        String pageTitle = driver.findElement(resultApple).getText();
        Assert.assertEquals("Название найденного товара не совпадает с запросом", "Apple Watch 6", pageTitle);
    }

    @Test
    public void mainPageATLANTaddToBasket() { //ищем в магазине часы АТЛАНТ, добавляем в корзину
        var appleWatch6 = "Apple Watch 6";
        driver.navigate().to(url);
        driver.findElement(searchInputLocator).sendKeys(appleWatch6);
        driver.findElement(searchLocator).click();
        driver.findElement(buttonBasket).click();
        //табличка об успешном добавлении появилась:
        WebElement successMessage = wait5.until(ExpectedConditions.visibilityOfElementLocated(successBasket));
        Assert.assertTrue("Оповещение об успешном помещении товара в корзину не появилось", successMessage.isDisplayed());
    }

    // ряд избыточных тестов, мб нужно оптимизировать до 2-3
    private By titlePageLocator = By.cssSelector("header.entry-header h1");

    private By withoutCategoryLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Без категории']");
    private By homeAppliancesLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Бытовая техника']");
    private By catalogLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Каталог']");
    private By booksLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Книги']");
    private By clothesLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Одежда']");
    private By padLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Планшеты']");
    private By washLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Стиральные машины']");
    private By TVLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Телевизоры']");
    private By phonesLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Телефоны']");
    private By photoVideoLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Фото/видео']");
    private By refrigeratorsLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Холодильники']");
    private By watchLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Часы']");
    private By electronicsLocator = By.xpath("//div[@id='woocommerce_product_categories-2']//a[text()='Электроника']");
    private String messageErrorOrder = "Неверный заголовок открытой страницы";

    private String getTitle(){
        return driver.findElement(titlePageLocator).getText();
    }

    @Test
    public void catalogClickWithoutCategory() {
        driver.navigate().to(url);
        driver.findElement(withoutCategoryLocator).click();
        Assert.assertEquals(messageErrorOrder, "БЕЗ КАТЕГОРИИ", getTitle());
    }
    @Test
    public void catalogClickHomeAppliances() {
        driver.navigate().to(url);
        driver.findElement(homeAppliancesLocator).click();
        Assert.assertEquals(messageErrorOrder, "БЫТОВАЯ ТЕХНИКА", getTitle());
    }
    @Test
    public void catalogClickCatalog() {
        driver.navigate().to(url);
        driver.findElement(catalogLocator).click();
        Assert.assertEquals(messageErrorOrder, "КАТАЛОГ", getTitle());
    }
    @Test
    public void catalogClickBooks() {
        driver.navigate().to(url);
        driver.findElement(booksLocator).click();
        Assert.assertEquals(messageErrorOrder, "КНИГИ", getTitle());
    }
    @Test
    public void catalogClickClothes() {
        driver.navigate().to(url);
        driver.findElement(clothesLocator).click();
        Assert.assertEquals(messageErrorOrder, "ОДЕЖДА", getTitle());
    }
    @Test
    public void catalogClickPad() {
        driver.navigate().to(url);
        driver.findElement(padLocator).click();
        Assert.assertEquals(messageErrorOrder, "ПЛАНШЕТЫ", getTitle());
    }
    @Test
    public void catalogClickWash() {
        driver.navigate().to(url);
        driver.findElement(washLocator).click();
        Assert.assertEquals(messageErrorOrder, "СТИРАЛЬНЫЕ МАШИНЫ", getTitle());
    }
    @Test
    public void catalogClickTV() {
        driver.navigate().to(url);
        driver.findElement(TVLocator).click();
        Assert.assertEquals(messageErrorOrder, "ТЕЛЕВИЗОРЫ", getTitle());
    }
    @Test
    public void catalogClickPhones() {
        driver.navigate().to(url);
        driver.findElement(phonesLocator).click();
        Assert.assertEquals(messageErrorOrder, "ТЕЛЕФОНЫ", getTitle());
    }
    @Test
    public void catalogClickPhotoVideo() {
        driver.navigate().to(url);
        driver.findElement(photoVideoLocator).click();
        Assert.assertEquals(messageErrorOrder, "ФОТО/ВИДЕО", getTitle());
    }
    @Test
    public void catalogClickRefrigerators() {
        driver.navigate().to(url);
        driver.findElement(refrigeratorsLocator).click();
        Assert.assertEquals(messageErrorOrder, "ХОЛОДИЛЬНИКИ", getTitle());
    }
    @Test
    public void catalogClickWatch() {
        driver.navigate().to(url);
        driver.findElement(watchLocator).click();
        Assert.assertEquals(messageErrorOrder, "ЧАСЫ", getTitle());
    }
    @Test
    public void catalogClickElectronics() {
        driver.navigate().to(url);
        driver.findElement(electronicsLocator).click();
        Assert.assertEquals(messageErrorOrder, "ЭЛЕКТРОНИКА", getTitle());
    }
}