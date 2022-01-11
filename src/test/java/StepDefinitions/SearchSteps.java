package StepDefinitions;

import PageObject.HomePage;
import PageObject.GameProductListPage;
import Utilities.TestContext;

public class SearchSteps {

    TestContext testContext;
    HomePage homePage;
    GameProductListPage productListPage;

    public SearchSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        productListPage = testContext.getPageObjectManager().getProductListPage();
    }








}
