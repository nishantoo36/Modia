package StepDefinitions;

import PageObject.SuccessScreenPage;
import Utilities.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SuccessScreenSteps {
    TestContext testContext;
    SuccessScreenPage successScreenPage;

    public SuccessScreenSteps(TestContext context) {
        testContext = context;
        successScreenPage = testContext.getPageObjectManager().getSuccessScreenPage();
    }

    @Then("User should navigate to success screen")
    public void userShouldNavigateToSuccessScreen() {
       Assert.assertTrue(successScreenPage.isUserConfirmSuccess());
    }

    @When("User click on confirm button from success screen")
    public void userClickOnConfirmButtonFromSuccessScreen() {
        successScreenPage.clickONConfirmBtn();
    }

}
