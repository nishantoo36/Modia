package StepDefinitions;

import PageObject.GameProductDetailPage;
import Utilities.JsonUtility;
import Utilities.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;



public class ProductDetailSteps {

    TestContext testContext;
    GameProductDetailPage productDetailPage;

    public ProductDetailSteps(TestContext context) {
        testContext = context;
        productDetailPage = testContext.getPageObjectManager().getProductDetailPage();
    }


    @Then("Verify that user is on the same product detail page")
    public void verifyThatUserIsOnTheSameProductDetailPage() {
        String title =  JsonUtility.getJsonDataWithJsonPath("Selected_product",JsonUtility.commonConfigFilePath);
        Assert.assertTrue(productDetailPage.isUserOnDetailsPageOf(title));
    }

    @And("Verify that user is able to see {int} product screenshots")
    public void verifyThatUserIsAbleToSeeProductScreenshots(int ssCount) {
        Assert.assertTrue(productDetailPage.isImageCount(ssCount));
    }

    @When("User select {int} quantity")
    public void userSelectQuantity(int qty) {
        productDetailPage.setProductQty(qty);
    }

    @And("Click on Add to cart button")
    public void clickOnAddToCartButton() {
        productDetailPage.addProductToCart();
    }
}
