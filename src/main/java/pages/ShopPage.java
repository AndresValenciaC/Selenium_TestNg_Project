package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class ShopPage extends BasePage {

    private final By productCategoriesTitle = By.cssSelector("#woocommerce_product_categories-2 > h4");
    public Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    /* Locators*/
    @FindBy(css = "#menu-item-40 > a")
    private WebElement shopBarMenuLink;
    @FindBy(css = "#woocommerce_product_categories-2 > h4")
    private WebElement productCategoryText;
    @FindBy(css = "#content > nav > a")
    private WebElement shopPageHomeLink;

    @FindBy(css = "div.price_slider.ui-slider.ui-corner-all.ui-slider-horizontal.ui-widget.ui-widget-content > span:nth-child(2)")
    private WebElement filterSliderLeft;

    @FindBy(css = "div.price_slider.ui-slider.ui-corner-all.ui-slider-horizontal.ui-widget.ui-widget-content > span:nth-child(3)")
    private WebElement filterSliderRight;

    @FindBy(css = "div > div.price_slider_amount > button")
    private WebElement filterBtn;
    @FindBy(css = "#content > ul")
    private WebElement ulProducts;

    @FindBy(xpath = "//a[text()=\"Add to basket\"]")
    private WebElement addToBasketBtn;

    @FindBy(css = "input[id = \"s2id_autogen1_search\"]")
    private WebElement billingCountrySearchBox;

    public ShopPage(WebDriver driver) {
        super(driver, 10);
        this.driver = driver;
    }

    public void dragSliderRight(int x) {

        Actions actions = new Actions(this.driver);
        actions.dragAndDropBy(filterSliderLeft, x, 0).perform();

    }

    public void dragSliderLeft(int x) {

        Actions actions = new Actions(this.driver);
        actions.dragAndDropBy(filterSliderRight, x, 0).perform();

    }

    public void clickShopBarMenuLink() {
        try {
            waitUntilElementToBeClickable(shopBarMenuLink);
            shopBarMenuLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickShopPageHomeLink() {
        try {
            waitUntilElementToBeClickable(shopPageHomeLink);
            shopPageHomeLink.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public void clickFilterBtn() {
        try {
            waitUntilElementToBeClickable(filterBtn);
            filterBtn.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }


    public String getShopCategoryText() {
        try {
            waitUntilPresenceOfElement(productCategoriesTitle);
            return getStringAttribute(productCategoryText, "innerHTML");
        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);
            return "";
        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);
            return "";
        }
    }

    public boolean priceFunctionality() {
        boolean testPass = false;
        dragSliderLeft(-20);
        clickFilterBtn();
        List<WebElement> spanPriceAmounts = ulProducts.findElements(By.cssSelector("ul span[class=\"woocommerce-Price-amount amount\"]"));
        List<WebElement> delTag = ulProducts.findElements(By.tagName("del"));

        List<String> spanPriceAmountStrings = new ArrayList<>();
        List<String> delPriceAmountStrings = new ArrayList<>();
        List<String> listFinal = new ArrayList<String>();

        for (WebElement element : spanPriceAmounts) {
            spanPriceAmountStrings.add(element.getText());
        }

        for (WebElement element : delTag) {
            delPriceAmountStrings.add(element.getText());
        }

        spanPriceAmountStrings.removeAll(delPriceAmountStrings);

        for (String element : spanPriceAmountStrings) {
            String subStElement = element.substring(1, 4);
            listFinal.add(subStElement);
        }

        List<Integer> newList = listFinal.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (Integer element : newList) {
            testPass = element >= 150 && element <= 450;
        }

        return testPass;
    }

    public String productsLinkClickValidation() {
        String finalTitle;
        List<WebElement> liProducts = ulProducts.findElements(By.tagName("li"));

        WebElement randomElement = liProducts.get(new Random().nextInt(liProducts.size()));

        WebElement title = getWait().until(ExpectedConditions.visibilityOf(randomElement.findElement(By.cssSelector("a.woocommerce-LoopProduct-link > h3"))));
        finalTitle = getStringAttribute(title, "innerHTML");

        randomElement.click();
        return finalTitle;

    }

    public boolean sortingDropDownProduct(String visibleTxt) {
        Boolean equals = false;
        Select dropDown = new Select(driver.findElement(By.cssSelector("#content > form > select")));
        List<WebElement> options = dropDown.getOptions();
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(visibleTxt)) {
                option.click();
                equals = true;
                break;
            }
        }
        return equals;
    }


    public boolean addToBasket_ViewBasket() throws InterruptedException {
        boolean itPass = false;

        List<WebElement> aTags1 = ulProducts.findElements(By.tagName("a"));

        List<WebElement> addBasketTags = new ArrayList<>();


        for (WebElement element : aTags1) {

            if (element.getText().equalsIgnoreCase("ADD TO BASKET")) {
                addBasketTags.add(element);
            }
        }

        WebElement randomElement = addBasketTags.get(new Random().nextInt(addBasketTags.size()));

        randomElement.click();
        Thread.sleep(2000);

        WebElement viewBasketLink = driver.findElement(By.cssSelector("#content > ul > li > a[class=\"added_to_cart wc-forward\""));
        viewBasketLink.click();

        String endUrl1 = driver.getCurrentUrl();
        String endUrl = "https://practice.automationtesting.in/basket/";
        if (endUrl.equalsIgnoreCase(endUrl1)) {
            itPass = true;
        }
        return itPass;

    }




}










