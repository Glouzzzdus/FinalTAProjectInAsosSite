package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingBagPage extends BasePage
{
    @FindBy(xpath = "//button[@class='bag-item-remove']")
    private WebElement itemRemoveButton;
    @FindBy(xpath = "//div[contains(@class,'bag-contents-holder')]//h2[@class='empty-bag-title']")
    private WebElement bagContentsHolder;
    @FindBy(xpath = "//div[@class='bag-holder']//a[contains(@class,'bag-total-button--checkout')]")
    private WebElement checkoutButton;

    public void clickRemoveButton()  { itemRemoveButton.click(); }
    public WebElement getItemRemoveButton()  { return itemRemoveButton; }
    public void clickCheckoutButton()  { checkoutButton.click(); }
    public WebElement getCheckoutButton()  { return checkoutButton; }
    public WebElement getBagContentsHolder()  { return bagContentsHolder; }
    public String valueOfBagContentsHolder()  { return bagContentsHolder.getText(); }
    public ShoppingBagPage(WebDriver driver) { super(driver); }
}
