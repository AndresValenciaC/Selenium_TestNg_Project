package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetails extends BasePage {
    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    // Locators
    @FindBy(css = "#site-logo > a[title = \"Automation Practice Site\"] > img")
    private WebElement homeSiteBtn;
    @FindBy(xpath = "//*[@id=\"page-35\"]/div/div[1]/ul/li[3]/strong/span")
    private WebElement totalAmount;
    @FindBy(css = "div.woocommerce > ul > li.method > strong")
    private WebElement paymentMethod;
    @FindBy(css = "div.woocommerce > h2")
    private WebElement orderDetailsPageTitle;

    public OrderDetails(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public String getTotalAmountTxt() {
        try {
            waitUntilElementToBeVisible(totalAmount);
            return totalAmount.getText();
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public String getOrderDetailsPageTitleTxt() {
        try {
            waitUntilElementToBeVisible(orderDetailsPageTitle);
            return getStringAttribute(orderDetailsPageTitle, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public String getPaymentMethodTxt() {
        try {
            waitUntilElementToBeVisible(paymentMethod);
            return getStringAttribute(paymentMethod, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }


}
