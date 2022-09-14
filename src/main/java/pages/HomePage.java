package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends BasePage {

    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }
    private final By homeImageLinkTitle = By.xpath("//*[@id=\"text-22-sub_row_1-0-2-0-0\"]/div/ul/li/a[1]/h3");

    /* Locators */
    @FindBy(xpath = "//*[@id=\"text-22-sub_row_1-0-2-0-0\"]/div/ul/li/a[2]")
    private WebElement firstAddToBasketBtn;
    @FindBy(css = "li > a.added_to_cart.wc-forward")
    private WebElement viewBasketBtn;
    @FindBy(css = "#text-22-sub_row_1-0-2-0-0 .woocommerce-LoopProduct-link")
    private WebElement homeImageLink;
    @FindBy(xpath = "//*[@id=\"text-22-sub_row_1-0-2-0-0\"]/div/ul/li/a[1]/h3")
    private WebElement homeImageLinkTitleTxt;
    @FindBy(id = "wpmenucartli")
    private WebElement cartMenuLink;



    public void clickFirstAddToBasketBtn() {
        try {
            waitUntilElementToBeClickable(firstAddToBasketBtn);
            firstAddToBasketBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickFirstViewBasketBtn() {
        try {
            waitUntilElementToBeClickable(viewBasketBtn);
            viewBasketBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickICartMenuLink() {
        try {
            waitUntilElementToBeClickable(cartMenuLink);
            cartMenuLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public void clickFirstIHomeImageLink() {
        try {
            waitUntilElementToBeClickable(homeImageLink);
            homeImageLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public String getHomeProductTitleTxt() {
        try {
            waitUntilPresenceOfElement(homeImageLinkTitle);
            return homeImageLinkTitleTxt.getText();
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }

    }

    public int sliderImageCount() {
        List<WebElement> countImages = driver.findElements(By.className("woocommerce-LoopProduct-link"));
        return countImages.size();
    }


    public void addFirstItemToCart() {
        clickFirstAddToBasketBtn();
        clickFirstViewBasketBtn();
    }

    public void addItemsToCart() {
        List<WebElement> imagesList = driver.findElements(By.cssSelector("a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart"));
        for (WebElement image : imagesList) {
            waitUntilElementToBeClickable(image);
            image.click();
        }
        clickICartMenuLink();
    }


}
