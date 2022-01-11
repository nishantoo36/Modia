package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//span[text()='Register an account']")
    private WebElement registerBtn;

    @FindBy(id = "page-title")
    private WebElement pageTitle;

    public boolean isUserOnLoginPage(){
       return pageTitle.getText().equals("Sign In");
    }

    public void clickOnRegisterBtn(){
        registerBtn.click();
    }

}
