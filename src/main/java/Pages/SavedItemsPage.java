package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SavedItemsPage extends BasePage
{
    @FindBy(xpath = "//button[text()='Move to bag']")
    private List<WebElement> savedItems;
    @FindBy(xpath = "//li[1]//select[@aria-label='Size']")
    private WebElement sizeDropdown;
    @FindBy(id = "sortBy")
    private WebElement recentlyAdded;
    @FindBy(xpath = "//li[1]//button[contains(@class,'toBagButton')]")
    private WebElement productAddButton;
    @FindBy(xpath = "//span[contains(@class,'_1z5n7CN')]")
    private WebElement myBagButton;
    @FindBy(xpath = "//a[@data-test-id='bag-link' and @type='button']")
    private WebElement viewBagButton;

    public int savedItemsAmount()
    {
        return savedItems.size();
    }
    public WebElement getSizeDropdown()  { return sizeDropdown; }
    public void selectAnyAvaliableSizeOfSavedProduct()
    {
        Select size = new Select(sizeDropdown);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        size.selectByVisibleText("EU 42.5");
    }
    public WebElement getRecentlyAdded()  { return recentlyAdded; }
    public WebElement getMyBagButton()  { return myBagButton; }
    public int getAmountInBag()  { return Integer.parseInt(myBagButton.getText()); }
    public void isAddToBagButtonEnabled() {productAddButton.isEnabled();}
    public void getAddToBagButton()  { productAddButton.click(); }
    public WebElement getViewBagButton()  { return viewBagButton; }
    public void clickViewBagButton()  { viewBagButton.click(); }
    public void bagHooverOver()
    {
        Actions action = new Actions(driver);
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        action.moveToElement(myBagButton).build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    public SavedItemsPage(WebDriver driver) { super(driver); }
}
