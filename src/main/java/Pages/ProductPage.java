package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ProductPage extends BasePage
{
    @FindBy(xpath = "//div[@class='product-hero']/h1")
    private WebElement productName;
    @FindBy(id = "main-size-select-0")
    private WebElement sizeDropDown;
    @FindBy(id = "product-add-button")
    private WebElement productAddButton;
    @FindBy(xpath = "//button[@data-testid='miniBagIcon']")
    private WebElement miniBagIcon;
    @FindBy(xpath = "//a[@data-test-id='bag-link']")
    private WebElement viewBagButton;

    public void bagIconHooverOver()
    {
        action.moveToElement(miniBagIcon).perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public String getSelectProductName()
    {
        return productName.getText();
    }
    public void chooseOfProductSize(String productSize)
    {
        sizeDropDown.click();
        Select sortObject = new Select(sizeDropDown);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        sortObject.selectByVisibleText(productSize);
    }
    public void isAddToBagButtonDisplayed() {productAddButton.isDisplayed();}
    public void addToBagButtonClick()  {productAddButton.click();}
    public void viewBagButtonClick()  {viewBagButton.click();}

    public WebElement viewBagButton() { return viewBagButton; }
    public ProductPage(WebDriver driver) { super(driver); }
    Actions action = new Actions(driver);
}
