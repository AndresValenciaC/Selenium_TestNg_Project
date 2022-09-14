package homePageTests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.ShoppingCartPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeTests extends BaseTests {

    @Test
    public void sliderCount_Test() {

        int countSliderDivs = 3;
        HomePage homePage = new HomePage(this.driver);
        if (countSliderDivs != homePage.sliderImageCount()) {
            throw new RuntimeException("There is not a user logged in. this is mandatory");
        }
    }

    @Test
    public void productImageClick() {
        String homeProductTitle, productPageTitle, viewBasketTxt,
                productDescriptionTitleTxt;

        HomePage homePage = new HomePage(this.driver);
        ProductPage productPage = new ProductPage(this.driver);
        homeProductTitle = homePage.getHomeProductTitleTxt();

        homePage.clickFirstIHomeImageLink();
        productPageTitle = productPage.getProductPageTitleTxt();
        assertEquals(homeProductTitle, productPageTitle);

        productPage.clickProductPageAddBasketBtn();
        viewBasketTxt = productPage.getProductPageViewBasketBtnTxt();
        assertEquals(viewBasketTxt, "View Basket");

        productDescriptionTitleTxt = productPage.getProductDescriptionTxt();
        productPage.clickDescriptionBtn();
        assertEquals(productDescriptionTitleTxt, "Product Description");

    }

    @Test
    public void productAddedNumberVerification() {
        String inputNumberBefore = "8";
        String inputNumberAfter;

        HomePage homePage = new HomePage(this.driver);
        ProductPage productPage = new ProductPage(this.driver);

        homePage.clickFirstIHomeImageLink();
        productPage.addNumberInputVerification(inputNumberBefore);
        inputNumberAfter = productPage.getInputNumberTxt();
        assertEquals(inputNumberBefore, inputNumberAfter);

    }

    @Test
    public void addCouponVerification() {

        String coupon = "krishnasakinala";
        String addCouponMessage;

        HomePage homePage = new HomePage(this.driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(this.driver);

        homePage.addFirstItemToCart();
        shoppingCartPage.addCouponToProduct(coupon);
        addCouponMessage = shoppingCartPage.shoppingCartMessage();
        assertEquals(addCouponMessage, "Coupon code applied successfully.");

    }

    @Test
    public void updateCartProduct() {
        String updateNumber = "4";
        String updateMessage;

        HomePage homePage = new HomePage(this.driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(this.driver);

        homePage.addFirstItemToCart();
        shoppingCartPage.updateProductQuantity(updateNumber);
        shoppingCartPage.clickUpdateBasketBtn();
        updateMessage = shoppingCartPage.shoppingCartMessage();
        assertEquals(updateMessage, "Basket updated.");

    }

    @Test
    public void deleteCartProduct() {
        String deleteMessage;
        HomePage homePage = new HomePage(this.driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(this.driver);

        homePage.addFirstItemToCart();
        shoppingCartPage.clickDeleteBtn();
        deleteMessage = shoppingCartPage.shoppingCartMessage();
        assertTrue(deleteMessage.contains("Selenium Ruby removed. "));

    }


}
