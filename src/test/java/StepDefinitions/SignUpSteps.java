package StepDefinitions;

import PageObject.SignUpPage;
import Utilities.JsonUtility;
import Utilities.SeleniumUtility;
import Utilities.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SignUpSteps {
    TestContext testContext;
    SignUpPage signUpPage;

    public SignUpSteps(TestContext context) {
        testContext = context;
        signUpPage = testContext.getPageObjectManager().getSignUpPage();
    }

    @Then("User should navigate to Sign up page")
    public void userShouldNavigateToSignUpPage() {
        Assert.assertTrue(signUpPage.isUserOnSignUpPage());
    }

    @When("User fill all valid data and do registration")
    public void userFillAllValidDataAndDoRegistration() {
        String filePath = JsonUtility.defaultPath+"/src/main/resources/SignUpData.json";
        String timeStamp = SeleniumUtility.generateTimeStamp();
        String gender =  JsonUtility.getJsonDataWithJsonPath("gender",filePath);
        String firstName =  timeStamp+JsonUtility.getJsonDataWithJsonPath("firstName",filePath);
        String lastName =  timeStamp+JsonUtility.getJsonDataWithJsonPath("lastName",filePath);
        String DOB =  JsonUtility.getJsonDataWithJsonPath("DOB",filePath);
        String emailId =  timeStamp+JsonUtility.getJsonDataWithJsonPath("emailId",filePath);
        String username =  timeStamp+JsonUtility.getJsonDataWithJsonPath("username",filePath);
        String streetAddress =  timeStamp+JsonUtility.getJsonDataWithJsonPath("streetAddress",filePath);
        String postcode =  JsonUtility.getJsonDataWithJsonPath("postcode",filePath);
        String city =  JsonUtility.getJsonDataWithJsonPath("city",filePath);
        String state =  JsonUtility.getJsonDataWithJsonPath("state",filePath);
        String country =  JsonUtility.getJsonDataWithJsonPath("country",filePath);
        String mobileNo =  timeStamp;
        String password =  timeStamp+JsonUtility.getJsonDataWithJsonPath("password",filePath);
        signUpPage.setPersonalDetails(gender,firstName,lastName,DOB,emailId,username);
        signUpPage.setAddressDetails(streetAddress,postcode,city,state,country);
        signUpPage.setContactDetails(mobileNo);
        signUpPage.setPassword(password);
        signUpPage.clickONContinue();
    }
}
