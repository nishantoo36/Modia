package PageObject;

import Utilities.SeleniumUtility;
import Utilities.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {
    WebDriver webDriver;
    public SignUpPage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(name = "gender")
    private List<WebElement> genderEle;

    @FindBy(id = "firstName")
    private WebElement firstNameEle;

    @FindBy(id = "lastName")
    private WebElement lastNameEle;

    @FindBy(id = "datepicker")
    private WebElement datePicker;

    @FindBy(id = "emailAddr")
    private WebElement emailEle;

    @FindBy(id = "username")
    private WebElement usernameEle;

    @FindBy(id = "streetAddress")
    private WebElement streetAddressEle;

    @FindBy(id = "postcode")
    private WebElement postcodeEle;

    @FindBy(id = "city")
    private WebElement cityEle;

    @FindBy(id = "state")
    private WebElement stateEle;

    @FindBy(id = "countryId")
    private WebElement countryEle;

    @FindBy(id = "telephoneNumber")
    private WebElement telephoneNumber;

    @FindBy(id = "password")
    private WebElement passwordEle;

    @FindBy(id = "passwordConfirmation")
    private WebElement passwordConfirmationEle;

    @FindBy(id = "page-title")
    private WebElement pageTitle;

    @FindBy(id = "continue-button")
    private WebElement continueBtn;


    public boolean isUserOnSignUpPage(){
        return pageTitle.getText().equals("New Account");
    }

    public void setPersonalDetails(String gender,String firstName,String lastName,String dateOfBirth, String emailAddress, String userName){
        setGender(gender);
        firstNameEle.sendKeys(firstName);
        lastNameEle.sendKeys(lastName);
        datePicker.sendKeys(dateOfBirth);
        datePicker.sendKeys(Keys.RETURN);
        emailEle.sendKeys(emailAddress);
        usernameEle.sendKeys(userName);
    }

    public void setAddressDetails(String streetAddress,String postcode,String city,String state, String country){
      streetAddressEle.sendKeys(streetAddress);
      postcodeEle.sendKeys(postcode);
      cityEle.sendKeys(city);
      SeleniumUtility.getSelector(countryEle).selectByVisibleText(country);
      Wait.untilAjaxCallIsDone(webDriver,10L);
      SeleniumUtility.getSelector(stateEle).selectByVisibleText(state);
    }

    public void setContactDetails(String mobileNumber){
        telephoneNumber.sendKeys(mobileNumber);
    }

    public void setPassword(String password){
        passwordEle.sendKeys(password);
        passwordConfirmationEle.sendKeys(password);
    }


    private void setGender(String gender){
        if(genderEle.get(0).getText().contains(gender)){
            genderEle.get(0).click();
        }else {
            genderEle.get(1).click();
        }
    }

    public void clickONContinue(){
        continueBtn.click();
    }


}
