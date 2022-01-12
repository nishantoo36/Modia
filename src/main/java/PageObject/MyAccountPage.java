package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyAccountPage {
    private final WebDriver webDriver;

    public MyAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "page-title")
    private WebElement pageTitle;

    @FindBy(id = "continue-button")
    private WebElement confirmBtn;

    @FindBy(xpath = "//div[@class='last-order']/child::table")
    private List<WebElement> orderInfo;

    @FindBy(css = ".last-order table .label.pending")
    private List<WebElement> orderStatus;

    public boolean isUserOnMyAccountPage(){
        return pageTitle.getText().equals("My Account Information");
    }

    public boolean isOrderStatus(List<String>productList,String status,int orderCount){
       int tempCount =0;
       outer: for (int i=0;i<orderInfo.size();i++){
            for(String product:productList){
                if (!orderInfo.get(i).getText().contains(product)){
                    continue outer;
                }
            }
            if(orderStatus.get(i).getText().equals(status)) {
                tempCount = tempCount + 1;
            }
        }
        return tempCount==orderCount;
    }
}
