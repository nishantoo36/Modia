package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    private final WebDriver webDriver;

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(css = "tr .text-link")
    private List<WebElement> productTitles;

    @FindBy(css = "tr .qty-input")
    private List<WebElement> productQty;

    @FindBy(xpath = "//td[@class='right']")
    private List<WebElement> productPrice;

    @FindBy(css = ".total-price.right")
    private List<WebElement> productTotalPrice;

    @FindBy(xpath = "//td[contains(text(),'Flat Rate (Shipping)')]//following-sibling::td")
    private WebElement shippingPrice;

    @FindBy(xpath = "//td[contains(text(),'Total:')]//following-sibling::td")
    private List<WebElement> totalPrices;

    @FindBy(id = "continue-button")
    private WebElement checkOutBtn;



    public boolean isItemQtyPriceAvailable(String exItemName,int exQty,double exPrice){
        for(int i=0;i<productTitles.size();i++){
            if(productTitles.get(i).getText().trim().equals(exItemName)){
                int actQty = Integer.parseInt(productQty.get(i).getAttribute("value").trim());
                double actPrice = Double.parseDouble(productPrice.get(i).getText().trim().replace("$",""))*actQty;
                double totalProductActPrice = Double.parseDouble(productTotalPrice.get(i).getText().replace("$","").trim());
                return actQty==exQty && actPrice==exPrice && totalProductActPrice==exPrice;
            }
        }
        return false;
    }

    public double getSubTotal(){
       return Double.parseDouble(totalPrices.get(0).getText().replace("$","").trim());
    }

    public double getShippingPrice(){
        return Double.parseDouble(shippingPrice.getText().replace("$","").trim());
    }

    public double getTotalPrice(){
        return Double.parseDouble(totalPrices.get(1).getText().replace("$","").trim());
    }

    public void clickCheckout(){
        checkOutBtn.click();
    }

}
