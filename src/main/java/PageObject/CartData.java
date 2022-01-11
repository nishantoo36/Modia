package PageObject;

import Utilities.SeleniumUtility;
import Utilities.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartData {
    private final WebDriver webDriver;

    public CartData(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(css = ".shopping-cart-item-title")
    private List<WebElement> productTitle;

    @FindBy(css = ".shopping-cart-item-price")
    private List<WebElement> productQtyAndPrice;

    @FindBy(css = ".price")
    private List<WebElement> prices;

    @FindBy(css = ".item-title")
    private List<WebElement> itemTitles;

    @FindBy(id = "shopping-cart")
    private WebElement shoppingCartOption;

    public boolean isProductAddedInCart(String productName, int productQuantity, double productPrice) {
        Wait.untilAjaxCallIsDone(webDriver,10L);
        for (int i = 0; i < productTitle.size(); i++) {
            String text = productTitle.get(i).getAttribute("textContent").trim();
            if (text.equals(productName)) {
                text = productQtyAndPrice.get(i).getAttribute("textContent").replaceAll("\n","").replaceAll("\t","").trim();
                double price = Double.parseDouble(SeleniumUtility.getStringByReg("(\\d+.\\d+)", text).get(0));
                System.out.println(text);
                int qty = Integer.parseInt(SeleniumUtility.getStringByReg("(\\d+)$", text).get(0));
                System.out.println("price: "+price+" : proPrice: "+productPrice+": qty: "+qty+": proqty: "+productQuantity);
                return price == productPrice && productQuantity == qty;
            }else {
                System.out.println(text+"actfind and expfind"+productName);
            }
        }
        return false;
    }

    public void openCart(){
        shoppingCartOption.click();
    }
}
