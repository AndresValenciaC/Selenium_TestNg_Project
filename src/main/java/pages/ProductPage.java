package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    public Logger logger = LogManager.getLogger();
    @FindBy(css = "div.summary.entry-summary > form > div > input")
    public WebElement inputNumber;
    @FindBy(css = "#wpmenucartli > a > span.cartcontents")
    public WebElement cartItemContents;

    /* Locators */
    protected WebDriver driver;
    private final By productPageItemTitle = By.cssSelector("div.summary.entry-summary > h1");
    private final By productPageViewBasket = By.cssSelector("#content > div.woocommerce-message > a");
    private final By productDescriptionTitle = By.cssSelector("#tab-description > h2");
    private final By inputNumberBox = By.cssSelector("div.summary.entry-summary > form > div > input");
    @FindBy(css = "div.summary.entry-summary > h1")
    private WebElement productPageItemTitleTxt;
    @FindBy(css = "#product-160 > div.summary.entry-summary > form > button")
    private WebElement productPageAddToBasketButton;
    @FindBy(css = "#content > div.woocommerce-message > a")
    private WebElement productPageViewBasketButton;
    @FindBy(css = "#tab-description > h2")
    private WebElement productPageItemDescriptionText;
    @FindBy(css = "div.woocommerce-tabs.wc-tabs-wrapper > ul > li.description_tab.active > a")
    private WebElement descriptionBtn;
    @FindBy(css = "#tab-description > h2")
    private WebElement productDescriptionTxt;

    public ProductPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public void clickProductPageAddBasketBtn() {
        try {
            waitUntilElementToBeClickable(productPageAddToBasketButton);
            productPageAddToBasketButton.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickViewBasketBtn() {
        try {
            waitUntilElementToBeClickable(productPageViewBasketButton);
            productPageViewBasketButton.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickDescriptionBtn() {
        try {
            waitUntilElementToBeClickable(descriptionBtn);
            descriptionBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public String getProductPageTitleTxt() {
        try {
            waitUntilPresenceOfElement(productPageItemTitle);
            return getStringAttribute(productPageItemTitleTxt, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }


    public String getProductPageViewBasketBtnTxt() {
        try {
            waitUntilPresenceOfElement(productPageViewBasket);
            return getStringAttribute(productPageViewBasketButton, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public String getProductDescriptionTxt() {
        try {
            waitUntilPresenceOfElement(productDescriptionTitle);
            return getStringAttribute(productDescriptionTxt, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public Void addNumberInputVerification(String number) {
        try {
            waitUntilElementToBeVisible(inputNumber);
            inputNumber.clear();
            inputNumber.sendKeys(number);
            clickProductPageAddBasketBtn();
            waitUntilElementToBeVisible(inputNumber);
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
        }
        return null;
    }


    public String getInputNumberTxt() {
        try {
            waitUntilPresenceOfElement(inputNumberBox);
            return getStringAttribute(inputNumber, "value");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }


}


