package StepDefinitions;

import PageObject.ConfirmationPage;
import Utilities.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class OrderConfirmationSteps {
    TestContext testContext;
    ConfirmationPage confirmationPage;

    public OrderConfirmationSteps(TestContext context) {
        testContext = context;
        confirmationPage = testContext.getPageObjectManager().getConfirmationPage();
    }

    @Then("User should navigate to order confirmation page")
    public void userShouldNavigateToOrderConfirmationPage() {
        Assert.assertTrue(confirmationPage.isUserConfirmOrderPage());
    }

    @When("User click on confirm order button")
    public void userClickOnConfirmOrderButton() {
        confirmationPage.clickONConfirmBtn();
    }

}
