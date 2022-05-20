package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage
{
    @FindBy(xpath = "//input[@id='chrome-search']")
    private WebElement searchInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;
    @FindBy(xpath = "//div[@data-testid='topbar']//button[@data-testid='country-selector-btn']")
    private WebElement countrySelectorButton;
    @FindBy(xpath = "//form//select[@id='country']")
    private WebElement chooseOfCountriesSelector;
    @FindBy(xpath = "//button[@data-testid='save-country-button']")
    private WebElement applyChangesButton;
    @FindBy(xpath = "//div[@class='kx4xyAK']//img")
    private WebElement shoppingFromFlag;
    @FindBy(xpath = "//button[@data-testid='myAccountIcon']")
    private WebElement myAccountButton;
    @FindBy(xpath = "//a[@data-testid='signup-link']")
    private WebElement signupLink;
    @FindBy(xpath = "//nav[@class='_3EAPxMS']//button[@data-testid='primarynav-button']/span")
    private List<WebElement> categoryList;
    @FindBy(xpath = "//li[@class='_1g1PWkA _1rF5dEp']")
    private WebElement shopByAreaOnDropMenu;
    @FindBy(xpath = "//button[@data-testid='primarynav-button' and @aria-expanded='true']/following::ul[1]")
    private WebElement openedProductCategory;
    public void openHomePage(String url) {
        driver.get(url);
    }
    public void isSearchInputIsDisplayed() {searchInput.isDisplayed();}
    public void isCountrySelectorIsDisplayed()  {countrySelectorButton.isDisplayed();}
    public void enterTextToSearchInput(final String searchText)
    {
        searchInput.clear();
        searchInput.sendKeys(searchText);
    }
    public void searchButtonClick()  { searchButton.click(); }
    public void openListOfCountries() {
        chooseOfCountriesSelector.click();
    }
    public void startCountrySelection()  {countrySelectorButton.click();}
    public WebElement getChooseOfCountriesSelector()  { return chooseOfCountriesSelector; }
    public WebElement getApplyChangesButton()  {return applyChangesButton;}
    public void chooseOfCountry(String country)
    {
        Select countryObject = new Select(chooseOfCountriesSelector);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        countryObject.selectByVisibleText(country);
    }
    public void myAccountHooverOver()
    {
        Actions action = new Actions(driver);
        action.moveToElement(myAccountButton).build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    public WebElement categorySelect(String categoryName)
    {
        WebElement category = null;
        for (WebElement element: categoryList )
        {
            if (Objects.equals(element.getText(), categoryName))
            category = element;
        }
        return category;
    }
    public void categoryHooverOver(String categoryName)
    {
        Actions action = new Actions(driver);
        action.moveToElement(categorySelect(categoryName)).build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    public WebElement getOpenedProductCategory()
    {
       return openedProductCategory;
    }
    public void getProductSearchElement(String productName)
    {
        driver.findElement(By.xpath("//button[@data-testid='primarynav-button' and @aria-expanded='true']/following::a[text()='"+productName+"'][1]" )).click();
    }
    public String getShoppingFromCountry()  {return shoppingFromFlag.getAttribute("alt");}
    public WebElement getSignUpLink()  {return signupLink;}
    public void signUp()  {signupLink.click();}
    public WebElement getShopByAreaOnDropMenu()  { return shopByAreaOnDropMenu;}

    public HomePage(WebDriver driver) {super(driver);}

}
