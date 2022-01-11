package Manager;

import PageObject.*;
import org.openqa.selenium.WebDriver;


public class PageObjectManager {

    private final WebDriver webDriver;
    private HomePage homePage;
    private LoginPage loginPage;
    private GameProductListPage productListPage;
    private GameProductDetailPage productDetailPage;
    private CartData cartData;
    private CartPage cartPage;
    private SignUpPage signUpPage;
    private ConfirmationPage confirmationPage;
    private SuccessScreenPage successScreenPage;
    private MyAccountPage myAccountPage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(webDriver) : homePage;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(webDriver) : loginPage;
    }

    public GameProductListPage getProductListPage() {
        return (productListPage == null) ? productListPage = new GameProductListPage(webDriver) : productListPage;
    }

    public GameProductDetailPage getProductDetailPage() {
        if (productDetailPage == null) productDetailPage = new GameProductDetailPage(webDriver);
        return productDetailPage;
    }

    public CartData getCartData() {
        if (cartData == null) cartData = new CartData(webDriver);
        return cartData;
    }

    public CartPage getCartPage() {
        if (cartPage == null) cartPage = new CartPage(webDriver);
        return cartPage;
    }

    public SignUpPage getSignUpPage() {
        if (signUpPage == null) signUpPage = new SignUpPage(webDriver);
        return signUpPage;
    }

    public ConfirmationPage getConfirmationPage() {
        if (confirmationPage == null) confirmationPage = new ConfirmationPage(webDriver);
        return confirmationPage;
    }

    public SuccessScreenPage getSuccessScreenPage() {
        if (successScreenPage == null) successScreenPage = new SuccessScreenPage(webDriver);
        return successScreenPage;
    }

    public MyAccountPage getMyAccountPage() {
        if (myAccountPage == null) myAccountPage = new MyAccountPage(webDriver);
        return myAccountPage;
    }
}
