package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountOrdersPage extends BasePage {

    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    @FindBy(css = "div.woocommerce > div > h2")
    private WebElement orderDetailsTitle;
    @FindBy(css = " div.woocommerce > div > header:nth-child(4) > h2")
    private WebElement customerDetailsTitle;
    @FindBy(css = "div.woocommerce > div > header.title > h3")
    private WebElement billingAddressTitle;
    @FindBy(css = "ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--orders > a")
    private WebElement OrdersLink;
    @FindBy(css = " nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--edit-address > a")
    private WebElement AddressesLink;
    @FindBy(css = "div.u-column1.col-1.woocommerce-Address > header > a")
    private WebElement AddressesEditLink;
    @FindBy(css = "nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--edit-account > a")
    private WebElement AccountDetailsLink;
    @FindBy(css = "nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--customer-logout > a")
    private WebElement LogOutLink;
    @FindBy(css = "div > form > p:nth-child(7) > input.woocommerce-Button.button")
    private WebElement SaveChangesEditAccountBtn;
    @FindBy(css = "div > p > mark.order-number")
    private WebElement View_Order_Number_Value;
    @FindBy(css = "div > p > mark.order-date")
    private WebElement View_DateTime_Value;
    @FindBy(css = "div > p > mark.order-status")
    private WebElement View_OrderStatus_Value;
    @FindBy(css = "table > tbody > tr:nth-child(1) > td.order-actions > a")
    private WebElement ViewFirstBtn;
    @FindBy(css = "#menu-item-50 > a")
    private WebElement myAccountLoginNavigationLink;
    @FindBy(css = "input[id=\"password_current\"] ")
    private WebElement currentPasswordInputBox;
    @FindBy(css = "input[id=\"password_1\"] ")
    private WebElement newPasswordInputBox;
    @FindBy(css = "input[id=\"password_2\"] ")
    private WebElement confirmNewPasswordInputBox;

    public MyAccountOrdersPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public void navigateToLoginMyAccount() {
        clickNavigationMenuBar(myAccountLoginNavigationLink);
    }


    public void clickAccountDetailsLink() {
        try {
            waitUntilElementToBeClickable(AccountDetailsLink);
            AccountDetailsLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickLogOutLink() {
        try {
            waitUntilElementToBeClickable(LogOutLink);
            LogOutLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickAddressesLink() {
        try {
            waitUntilElementToBeClickable(AddressesLink);
            AddressesLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickAddressesEditLink() {
        try {
            waitUntilElementToBeClickable(AddressesEditLink);
            AddressesEditLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickViewBtn() {
        try {
            waitUntilElementToBeClickable(ViewFirstBtn);
            ViewFirstBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void ClickSaveChangesEditAccountBtnBtn() {
        try {
            waitUntilElementToBeClickable(SaveChangesEditAccountBtn);
            SaveChangesEditAccountBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickOrdersLink() {
        try {
            waitUntilElementToBeClickable(OrdersLink);
            OrdersLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public boolean viewOrdersPageConfirmation() {
        boolean viewElementsVisible = View_Order_Number_Value.isDisplayed() && View_DateTime_Value.isDisplayed() && View_OrderStatus_Value.isDisplayed();
        return viewElementsVisible;
    }

    public boolean ordersPageConfirmation() {
        boolean urlMatch = false;
        String strUrl = "https://practice.automationtesting.in/my-account/orders/";
        if (driver.getCurrentUrl().equalsIgnoreCase(strUrl))
            urlMatch = true;
        return urlMatch;
    }

    public boolean billingAddressesEditPageConfirmation() {
        boolean urlMatch = false;
        String strUrl = "https://practice.automationtesting.in/my-account/edit-address/billing/";
        if (driver.getCurrentUrl().equalsIgnoreCase(strUrl))
            urlMatch = true;
        return urlMatch;
    }

    public String viewOrderDetailsTitle() {
        try {
            waitUntilElementToBeVisible(orderDetailsTitle);
            return orderDetailsTitle.getText();
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public String viewCustomerDetailsTitle() {
        try {
            waitUntilElementToBeVisible(customerDetailsTitle);
            return customerDetailsTitle.getText();
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }


    public String viewBillingAddressTitle() {
        try {
            waitUntilElementToBeVisible(billingAddressTitle);
            return billingAddressTitle.getText();
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public boolean changePassAccountDetails() {
        boolean viewElementsVisible = currentPasswordInputBox.isDisplayed() && newPasswordInputBox.isDisplayed()
                && confirmNewPasswordInputBox.isDisplayed();

        return viewElementsVisible;

    }


}



