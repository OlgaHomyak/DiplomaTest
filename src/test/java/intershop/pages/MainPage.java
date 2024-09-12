package intershop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    public HeaderPanel header;
    private String url  = "https://intershop6.skillbox.ru/";

    private By titlePageLocator = By.className("current");
    private By subTitlePageLocator = By.cssSelector("header.entry-header h1");

    private By bookLocator = By.xpath("//*[@class='widget-title'][text()='Книги']");
    private By tabletsLocator = By.xpath("//*[@class='widget-title'][text()='Планшеты']");
    private By phAndVidLocator = By.xpath("//*[@class='widget-title'][text()='Фотоаппараты']");

    private By searchInputLocator = By.xpath("//input[@name='s' and @class='search-field']");
    private By searchLocator = By.xpath("//button[@type='submit' and @class='searchsubmit']");
    private By searchResultLocator = By.xpath("//h3[contains(text(), 'телевизор')]");

    private By watchProductOnMainPageLocator = By.xpath("//span[@class='btn promo-link-btn' and text()='Просмотреть товар']");
    private By titleProductLocator = By.xpath("//h1[@class='product_title entry-title']"); //iPad 2020 32gb wi-fi на странице всех товаров. если щелкнуть на "посмотреть"
    private By returnMainPageLocator = By.xpath("//h1[@class='site-title']"); //возвращение на главную страницу
    private By firstObjectViewed = By.xpath("//ul[@class='product_list_widget']/li[1]/a"); //первый элемент из просмотренных
    private By allProductsLabelsLocator = By.cssSelector("#product1 li[aria-hidden='false'] .item-img span");
    private By productOnSaleLocator = By.xpath("//*[@id='product1']//li[@aria-hidden='false'][1]//*[contains(@class, 'item-img')]");
    private String messageInCaseOfFailedTest = "Неверный заголовок открытой страницы";

    public MainPage (WebDriver driver){
        this.driver = driver;
        header = new HeaderPanel(driver);
    }

    public void clickOnBook(){
        driver.findElement(bookLocator).click();
    }
    public void clickOnTablets(){
        driver.findElement(tabletsLocator).click();
    }
    public void clickOnPhAndVidLocator(){
        driver.findElement(phAndVidLocator).click();
    }

    public void open(){
        driver.navigate().to(url);
    }
    public void addProductWithDiscount(){
        driver.findElement(productOnSaleLocator).click();
    }
    public String getTitle(){
        return driver.findElement(titlePageLocator).getText();
    }
    public String getSubTitlePage(){
        return driver.findElement(subTitlePageLocator).getText();
    }
}
