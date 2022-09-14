package pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public Logger logger = LogManager.getLogger();
    private final WebDriverWait wait;

    public BasePage(WebDriver driver, int duration) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait getWait() {
        return this.wait;
    }


    public boolean waitUntilElementToBeClickable(WebElement element) {
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element is clickable!! :");
            return true;
        } catch (TimeoutException to) {
            logger.error("TimeOut exception looking for the element to be clickable: " + to);
        } catch (Exception e) {
            logger.error("Error: " + e);
        }
        return false;
    }

    public boolean waitUntilElementToBeVisible(WebElement element) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible!! :");
            return true;
        } catch (TimeoutException to) {
            logger.info("TimeOut exception looking for the element to be visible: " + to);
            return false;
        }
    }

    public void locatorWaitBy(By locator, int duration, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public boolean waitUntilPresenceOfElement(By locator) {
        try {
            getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element is presented!! :");
            return true;
        } catch (TimeoutException to) {
            logger.info("TimeOut exception looking for the element to be presented: " + to);
            return false;
        }
    }

    public void inputBoxSendKeys(WebElement element, String input) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible!! :");
            element.clear();
            element.sendKeys(input);

        } catch (TimeoutException to) {
            logger.info("TimeOut exception looking for the element to be visible: " + to);
        }
    }

    public void ul_liSelect(WebElement element, String text, String tagName) {
        List<WebElement> textList = element.findElements(By.tagName(tagName)); // li - label
        for (WebElement li : textList) {
            if (li.getText().equals(text)) {
                li.click();
            }
        }
    }

    public String getStringAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }


    public String getUl_LiTxt(WebElement element) {
        String liLabelIdAttribute = "";
        String inputIdAttribute = "";
        String labelAttributeTxt = "";
        List<WebElement> liInputs = element.findElements(By.tagName("input"));
        List<WebElement> liLabels = element.findElements(By.tagName("label"));

        for (WebElement liInput : liInputs) {
            getWait().until(ExpectedConditions.visibilityOf(liInput));

            if (liInput.isSelected()) {
                inputIdAttribute = liInput.getAttribute("id");


                for (WebElement labelAttribute : liLabels) {
                    getWait().until(ExpectedConditions.visibilityOf(labelAttribute));

                    liLabelIdAttribute = labelAttribute.getAttribute("for");


                    if (liLabelIdAttribute.equalsIgnoreCase(inputIdAttribute)) {
                        labelAttributeTxt = labelAttribute.getText();
                    }
                }

            }
        }

        return labelAttributeTxt;
    }

    public void clickNavigationMenuBar(WebElement element) {
        try {
            waitUntilElementToBeClickable(element);
            element.click();
        } catch (Exception e) {
            logger.error("Error, user icon: " + e);
        }
    }

    public int convertTxtToInt(WebElement webElement, int sub1, int sub2) {
        int amount = 0;
        try {
            waitUntilElementToBeVisible(webElement);
             amount = Integer.parseInt(webElement.getText().substring(sub1, sub2));

        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);

        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);

        }
        return amount;
    }

    public float convertTxtToFloat(WebElement webElement, int sub1, int sub2) {
        float amount = 0;
        try {
            waitUntilElementToBeVisible(webElement);
            amount = Float.parseFloat(webElement.getText().substring(sub1, sub2));

        } catch (NoSuchElementException e) {
            logger.error("The web element doesn't exists: " + e);

        } catch (TimeoutException t) {
            logger.error("TimeOut, the element doesn't exists: " + t);

        }
        return amount;
    }


}




