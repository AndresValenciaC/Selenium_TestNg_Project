package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {

    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    private final By cartItemProductName = By.cssSelector("tr.cart_item > td.product-name > a");

    /* Locators */
    private final By couponCodeBx = By.cssSelector("#coupon_code");
    private final By ByCartPageMessage = By.cssSelector("div .woocommerce-message");
    @FindBy(css = "div .woocommerce-message")
    private WebElement cartPageMessage;
    @FindBy(css = "#coupon_code")
    private WebElement couponCodeBox;
    @FindBy(css = "input[name=apply_coupon]")
    private WebElement applyCouponBtn;
    @FindBy(css = "input[name = update_cart]")
    private WebElement updateBasketBtn;
    @FindBy(css = "div.woocommerce-message")
    private WebElement couponTableBox;
    @FindBy(css = "table > tbody > tr.cart_item > td.product-quantity > div > input")
    private WebElement inputQuantityBox;
    @FindBy(css = "product-remove > a")
    private WebElement productName;
    @FindBy(css = "table > tbody > tr.cart_item > td.product-remove > a")
    private WebElement deleteBtn;
    @FindBy(css = "a[class=\"checkout-button button alt wc-forward\"]")
    private WebElement proceedToCheckOutBtn;

    @FindBy(css = "table > tbody > tr.cart-subtotal > td > span ")
    private WebElement subTotalAmount;

    @FindBy(css = "table > tbody > tr.order-total > td > strong > span")
    private WebElement TotalAmount;


    public ShoppingCartPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public void clickApplyCouponBtn() {
        try {
            waitUntilElementToBeClickable(applyCouponBtn);
            applyCouponBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickUpdateBasketBtn() {
        try {
            waitUntilElementToBeClickable(updateBasketBtn);
            updateBasketBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickDeleteBtn() {
        try {
            waitUntilElementToBeClickable(deleteBtn);
            deleteBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public void clickProceedToCheckOutBtn() {
        try {
            waitUntilElementToBeClickable(proceedToCheckOutBtn);
            proceedToCheckOutBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void productProceedCheckout() {
        HomePage homePage = new HomePage(this.driver);
        homePage.addFirstItemToCart();
        clickProceedToCheckOutBtn();
    }

    public String getProductNameTxt() {
        try {
            waitUntilPresenceOfElement(cartItemProductName);
            return getStringAttribute(productName, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public String shoppingCartMessage() {
        try {
            waitUntilPresenceOfElement(ByCartPageMessage);
            return getStringAttribute(cartPageMessage, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public void addCouponToProduct(String coupon) {
        try {
            waitUntilPresenceOfElement(couponCodeBx);
            couponCodeBox.sendKeys(coupon);
            clickApplyCouponBtn();
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
        }
    }


    public void updateProductQuantity(String updateNumber) {
        try {
            waitUntilElementToBeVisible(inputQuantityBox);
            inputQuantityBox.clear();
            inputQuantityBox.sendKeys(updateNumber);

        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
        }

    }

    public void deleteProduct(String productName, String productNameCart) {
        try {
            waitUntilPresenceOfElement(cartItemProductName);
            if (productName == productNameCart) {
                clickDeleteBtn();
            }

        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
        }

    }


    public boolean comparePriceAmounts() {
        boolean compareComply = false;
        int TotalInt = convertTxtToInt(TotalAmount,1,4);
        int subTotalInt = convertTxtToInt(subTotalAmount,1,4);

        if (subTotalInt < TotalInt) {
            compareComply = true;
        }

        return compareComply;

    }

}
